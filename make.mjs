import {readdir, rm} from 'node:fs/promises';

import {Manager} from '@shockpkg/core';
import {
	Plist,
	ValueDict,
	ValueString,
	ValueBoolean
} from '@shockpkg/plist-dom';
import {
	BundleHtml,
	BundleSaWindows,
	BundleSaMac,
	BundleSaLinux,
	loader
} from '@shockpkg/swf-projector';

import {
	appName,
	appDomain,
	version,
	author,
	copyright,
	appFile,
	appDmgTitle,
	versionShort,
	distName
} from './util/meta.mjs';
import {docs} from './util/doc.mjs';
import {zip, tgz} from './util/archive.mjs';
import {isMac, codesign, dmg} from './util/mac/index.mjs';
import {exe} from './util/windows/index.mjs';

async function * resources() {
	for (const dir of [
		'original/files/VahkiSWF_RC1',
		'src/shared'
	]) {
		for (const f of (await readdir(dir)).sort()) {
			if (/[^.].*\.swf$/i.test(f)) {
				yield [f, `${dir}/${f}`];
			}
		}
	}
}

function projected(delay) {
	const swfv = 6;
	const [w, h] = [720, 540];
	const fps = 30;
	const bg = 0x000000;
	const url = 'vahkianimations.swf';
	return loader(swfv, w, h, fps, bg, url, delay ? Math.round(fps / 2) : 0);
}

const task = {'': _ => Object.keys(task).map(t => t && console.error(t)) && 1};

task['clean'] = async () => {
	await rm('dist', {force: true, recursive: true});
	await rm('build', {force: true, recursive: true});
};

for (const [type, flat] of Object.entries({
	'pages': true,
	'browser': false
})) {
	const build = `build/${type}`;
	task[`build:${type}`] = async () => {
		await rm(build, {force: true, recursive: true});
		const file = flat ? 'index.html' : `${appFile}.html`;
		const b = new BundleHtml(`${build}/${file}`, flat);
		b.projector.lang = 'en-US';
		b.projector.title = appName;
		b.projector.background = '#000000';
		b.projector.color = '#999999';
		b.projector.src = 'vahkianimations.swf';
		b.projector.width = 720;
		b.projector.height = 540;
		b.projector.bgcolor = '#000000';
		await b.write(async b => {
			for await (const [file, data] of resources()) {
				await b.copyResourceFile(file, data);
			}
		});
		await docs('docs', build);
	};
}

for (const [type, make] of Object.entries({
	'zip': zip,
	'tgz': tgz
})) {
	task[`dist:browser:${type}`] = async () => {
		await make(`dist/${distName}-Browser.${type}`, 'build/browser');
	};
}

for (const [type, pkg] of Object.entries({
	'i386': 'flash-player-35.0.0.204-windows-i386-sa-2022-08-13',
	'x86_64': 'flash-player-35.0.0.204-windows-x86_64-sa-2022-08-13',
	'x86_64-debug': 'flash-player-35.0.0.204-windows-x86_64-sa-debug-2022-08-13'
})) {
	const build = `build/windows-${type}`;
	task[`build:windows-${type}`] = async () => {
		await rm(build, {force: true, recursive: true});
		const file = `${appFile}.exe`;
		const b = new BundleSaWindows(`${build}/${file}`);
		b.projector.player = await new Manager().file(pkg);
		b.projector.movieData = projected(false);
		b.projector.versionStrings = {
			FileVersion: version,
			ProductVersion: versionShort,
			CompanyName: author,
			FileDescription: appName,
			LegalCopyright: copyright,
			ProductName: appName,
			LegalTrademarks: '',
			OriginalFilename: file,
			InternalName: appFile,
			Comments: ''
		};
		b.projector.iconFile = 'res/app-icon-windows.ico';
		b.projector.patchWindowTitle = appName;
		b.projector.patchOutOfDateDisable = true;
		b.projector.removeCodeSignature = true;
		await b.write(async b => {
			for await (const [file, data] of resources()) {
				await b.copyResourceFile(file, data);
			}
		});
		await docs('docs', build);
	};
	task[`dist:windows-${type}:zip`] = async () => {
		await zip(`dist/${distName}-Windows-${type}.zip`, build);
	};
	task[`dist:windows-${type}:exe`] = async () => {
		await exe(
			`dist/${distName}-Windows-${type}.exe`,
			/x86_64/.test(type) ? 'x64 arm64' : '',
			appDomain,
			appName,
			appFile,
			version,
			author,
			copyright,
			'LICENSE.txt',
			'res/inno-icon.ico',
			'res/inno-header/*.bmp',
			'res/inno-sidebar/*.bmp',
			`${build}/*`,
			[
				[`${appFile}.exe`, appFile, true, true],
				['README.html', `${appFile} - README`]
			]
		);
	};
}

for (const [type, pkg] of Object.entries({
	'x86_64-arm64': 'flash-player-35.0.0.60-mac-x86_64-arm64-sa-2023-09-23',
	'x86_64-arm64-debug': 'flash-player-35.0.0.60-mac-x86_64-arm64-sa-debug-2023-09-23'
})) {
	const build = `build/mac-${type}`;
	task[`build:mac-${type}`] = async () => {
		await rm(build, {force: true, recursive: true});
		const pkgInfo = 'APPL????';
		const b = new BundleSaMac(`${build}/${appFile}.app`);
		b.projector.player = await new Manager().file(pkg);
		b.projector.movieData = projected(false);
		b.projector.binaryName = appFile;
		b.projector.pkgInfoData = pkgInfo;
		b.projector.infoPlistData = (new Plist(new ValueDict(new Map([
			['CFBundleInfoDictionaryVersion', new ValueString('6.0')],
			['CFBundleDevelopmentRegion', new ValueString('en-US')],
			['CFBundleExecutable', new ValueString('')],
			['CFBundleIconFile', new ValueString('')],
			['CFBundleName', new ValueString(appName)],
			['NSHumanReadableCopyright', new ValueString(copyright)],
			['CFBundleGetInfoString', new ValueString(copyright)],
			['CFBundleIdentifier', new ValueString(appDomain)],
			['CFBundleVersion', new ValueString(version)],
			['CFBundleLongVersionString', new ValueString(version)],
			['CFBundleShortVersionString', new ValueString(versionShort)],
			['CFBundlePackageType', new ValueString(pkgInfo.substring(0, 4))],
			['CFBundleSignature', new ValueString(pkgInfo.substring(4))],
			['NSAppTransportSecurity', new ValueDict(new Map([
				['NSAllowsArbitraryLoads', new ValueBoolean(true)]
			]))],
			['NSSupportsAutomaticGraphicsSwitching', new ValueBoolean(true)],
			['NSHighResolutionCapable', new ValueBoolean(true)],
			['CSResourcesFileMapped', new ValueBoolean(true)],
			['LSPrefersCarbon', new ValueString('YES')],
			['NSAppleScriptEnabled', new ValueString('YES')],
			['NSMainNibFile', new ValueString('MainMenu')],
			['NSPrincipalClass', new ValueString('NSApplication')]
		])))).toXml();
		b.projector.iconFile = 'res/app-icon-mac.icns';
		b.projector.patchWindowTitle = appName;
		b.projector.removeInfoPlistStrings = true;
		b.projector.removeCodeSignature = true;
		await b.write(async b => {
			for await (const [file, data] of resources()) {
				await b.copyResourceFile(file, data);
			}
		});
		if (isMac) {
			await codesign(b.projector.path);
			await codesign(b.path);
		}
		await docs('docs', build);
	};
	task[`dist:mac-${type}:tgz`] = async () => {
		await tgz(`dist/${distName}-Mac-${type}.tgz`, build);
	};
	task[`dist:mac-${type}:dmg`] = async () => {
		await dmg(
			`dist/${distName}-Mac-${type}.dmg`,
			appDmgTitle,
			'res/dmg-icon.icns',
			'res/dmg-background/dmg-background.png',
			[640, 512],
			128,
			[
				[-160, -148, 'file', `${build}/${appFile}.app`],
				[160, -148, 'link', '/Applications'],
				[0, 100, 'file', `${build}/README.html`]
			]
		);
	};
}

for (const [type, pkg] of Object.entries({
	'i386': 'flash-player-11.2.202.644-linux-i386-sa',
	'i386-debug': 'flash-player-11.2.202.644-linux-i386-sa-debug',
	'x86_64': 'flash-player-32.0.0.465-linux-x86_64-sa',
	'x86_64-debug': 'flash-player-32.0.0.465-linux-x86_64-sa-debug'
})) {
	const build = `build/linux-${type}`;
	task[`build:linux-${type}`] = async () => {
		await rm(build, {force: true, recursive: true});
		const b = new BundleSaLinux(`${build}/${appFile}`);
		b.projector.player = await new Manager().file(pkg);
		b.projector.movieData = projected(true);
		b.projector.patchProjectorOffset = /x86_64/.test(type);
		b.projector.patchProjectorPath = true;
		b.projector.patchWindowTitle = appName;
		await b.write(async b => {
			for await (const [file, data] of resources()) {
				await b.copyResourceFile(file, data);
			}
		});
		await docs('docs', build);
	};
	task[`dist:linux-${type}:tgz`] = async () => {
		await tgz(`dist/${distName}-Linux-${type}.tgz`, build);
	};
}

process.exitCode = await task[process.argv[2] || '']();
