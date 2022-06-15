.flash
	filename="vahkianimations.swf"
	version=6
	fps=30
	bbox=720x540
	background=#000000
	compress

	.font GillSans "fonts/gillsans.ttf"
	.font TradeMarkerLight "fonts/trademarker_light.ttf"

	.swf icon_Nuurakh "icons/Nuurakh.swf"
	.swf icon_Zadakh  "icons/Zadakh.swf"
	.swf icon_Keerakh "icons/Keerakh.swf"
	.swf icon_Rorzakh "icons/Rorzakh.swf"
	.swf icon_Vorzakh "icons/Vorzakh.swf"
	.swf icon_Bordakh "icons/Bordakh.swf"

	.box button_shape width=202 height=212 line=0 fill=#6F6F6F
	.box button_inner width=200 height=210 line=0 fill=#000000
	.box button_cover width=200 height=210 line=0 fill=#333333E0

	.text button_Nuurakh_title font=GillSans size=100% color=#FFFF99 text="Nuurakh - Vahki of Ta-Metru"
	.text button_Zadakh_title  font=GillSans size=100% color=#FFFF99 text="Zadakh - Vahki of Po-Metru"
	.text button_Keerakh_title font=GillSans size=100% color=#FFFF99 text="Keerakh - Vahki of Ko-Metru"
	.text button_Rorzakh_title font=GillSans size=100% color=#FFFF99 text="Rorzakh - Vahki of Onu-Metru"
	.text button_Vorzakh_title font=GillSans size=100% color=#FFFF99 text="Vorzakh - Vahki of Le-Metru"
	.text button_Bordakh_title font=GillSans size=100% color=#FFFF99 text="Bordakh - Vahki of Ga-Metru"

	.text button_Nuurakh_text font=GillSans size=100% color=#FFFFFF text="Welcome to Nuurakh Central\nTask Force Hive - Metru\nSubdivision. Quick work is\ngood work. A busy Matoran\nis a happy Matoran.\n \nWatch the Ta-Metru Vahki\nin action!"
	.text button_Zadakh_text  font=GillSans size=100% color=#FFFFFF text="Welcome to Zadakh Central\nTask Force Hive - Metru\nSubdivision. First in, last out!\n \nWatch these Po-Metru Vahki\nin action!"
	.text button_Keerakh_text font=GillSans size=100% color=#FFFFFF text="Welcome to Keerakh Central\nTask Force Hive - Metru\nSubdivision. Think ahead. A\npredictable worker is a good\nworker.\n \nWatch the Ko-Metru Vahki\nin action!"
	.text button_Rorzakh_text font=GillSans size=100% color=#FFFFFF text="Welcome to Rorzakh Central\nTask Force Hive - Metru\nSubdivision. Never give up.\nA tired worker is a good\nworker.\n \nWatch the Onu-Metru Vahki\nin action!"
	.text button_Vorzakh_text font=GillSans size=100% color=#FFFFFF text="Welcome to Vorzakh Central\nTask Force Hive - Metru\nSubdivision. Order above\neverything. An obedient\nworker is a happy worker.\n \nWatch this Le-Metru Vahki\nanimation!"
	.text button_Bordakh_text font=GillSans size=100% color=#FFFFFF text="Welcome to Bordakh Central\nTask Force Hive - Metru\nSubdivision. Pride in the elite.\nLive for the chase.\n \nWatch the Ga-Metru Vahki\nin action! "

	.sprite button_Nuurakh_idle
		.put button_shape pin=center
		.put button_inner pin=center
		.put icon_Nuurakh pin=center scale=50%
	.end

	.sprite button_Zadakh_idle
		.put button_shape pin=center
		.put button_inner pin=center
		.put icon_Zadakh  pin=center scale=50%
	.end

	.sprite button_Keerakh_idle
		.put button_shape pin=center
		.put button_inner pin=center
		.put icon_Keerakh pin=center scale=50%
	.end

	.sprite button_Rorzakh_idle
		.put button_shape pin=center
		.put button_inner pin=center
		.put icon_Rorzakh pin=center scale=50%
	.end

	.sprite button_Vorzakh_idle
		.put button_shape pin=center
		.put button_inner pin=center
		.put icon_Vorzakh pin=center scale=50%
	.end

	.sprite button_Bordakh_idle
		.put button_shape pin=center
		.put button_inner pin=center
		.put icon_Bordakh pin=center scale=50%
	.end

	.sprite button_Nuurakh_hover
		.put button_shape pin=center
		.put button_inner pin=center
		.put icon_Nuurakh pin=center scale=50%
		.put button_cover pin=center
		.put button_Nuurakh_title pin=top-center scale=24% x=0 y=-100
		.put button_Nuurakh_text pin=center scale=24% x=0 y=0
	.end

	.sprite button_Zadakh_hover
		.put button_shape pin=center
		.put button_inner pin=center
		.put icon_Zadakh  pin=center scale=50%
		.put button_cover pin=center
		.put button_Zadakh_title pin=top-center scale=24% x=0 y=-100
		.put button_Zadakh_text pin=center scale=24% x=0 y=0
	.end

	.sprite button_Keerakh_hover
		.put button_shape pin=center
		.put button_inner pin=center
		.put icon_Keerakh pin=center scale=50%
		.put button_cover pin=center
		.put button_Keerakh_title pin=top-center scale=24% x=0 y=-100
		.put button_Keerakh_text pin=center scale=24% x=0 y=0
	.end

	.sprite button_Rorzakh_hover
		.put button_shape pin=center
		.put button_inner pin=center
		.put icon_Rorzakh pin=center scale=50%
		.put button_cover pin=center
		.put button_Rorzakh_title pin=top-center scale=24% x=0 y=-100
		.put button_Rorzakh_text pin=center scale=24% x=0 y=0
	.end

	.sprite button_Vorzakh_hover
		.put button_shape pin=center
		.put button_inner pin=center
		.put icon_Vorzakh pin=center scale=50%
		.put button_cover pin=center
		.put button_Vorzakh_title pin=top-center scale=24% x=0 y=-100
		.put button_Vorzakh_text pin=center scale=24% x=0 y=0
	.end

	.sprite button_Bordakh_hover
		.put button_shape pin=center
		.put button_inner pin=center
		.put icon_Bordakh pin=center scale=50%
		.put button_cover pin=center
		.put button_Bordakh_title pin=top-center scale=24% x=0 y=-100
		.put button_Bordakh_text pin=center scale=24% x=0 y=0
	.end

	.button button_Nuurakh
		.show button_shape pin=center as=area
		.show button_Nuurakh_idle as=idle
		.show button_Nuurakh_hover as=hover,pressed
	.end

	.button button_Zadakh
		.show button_shape pin=center as=area
		.show button_Zadakh_idle as=idle
		.show button_Zadakh_hover as=hover,pressed
	.end

	.button button_Keerakh
		.show button_shape pin=center as=area
		.show button_Keerakh_idle as=idle
		.show button_Keerakh_hover as=hover,pressed
	.end

	.button button_Rorzakh
		.show button_shape pin=center as=area
		.show button_Rorzakh_idle as=idle
		.show button_Rorzakh_hover as=hover,pressed
	.end

	.button button_Vorzakh
		.show button_shape pin=center as=area
		.show button_Vorzakh_idle as=idle
		.show button_Vorzakh_hover as=hover,pressed
	.end

	.button button_Bordakh
		.show button_shape pin=center as=area
		.show button_Bordakh_idle as=idle
		.show button_Bordakh_hover as=hover,pressed
	.end

	.text menu_title font=TradeMarkerLight size=40pt color=#FFFFFF text="Vahki Animations"

	.sprite menu
		.put menu_title pin=center x=360 y=28
		.put button_Nuurakh x=130 y=165
		.put button_Zadakh  x=360 y=165
		.put button_Keerakh x=590 y=165
		.put button_Rorzakh x=130 y=405
		.put button_Vorzakh x=360 y=405
		.put button_Bordakh x=590 y=405
	.end

	.sprite episode
	.end

	.put episode

	.put menu

	.action:
		Array(function() {
			var trailingFrames = 0;
			var paddingFrames = 0;
			var showEpisode = function(url, trailing) {
				if (url == null) {
					trailingFrames = 0;
					episode.unloadMovie();
					menu._visible = true;
				}
				else {
					trailingFrames = trailing;
					menu._visible = false;
					episode.loadMovie(url);
				}
			};

			menu.onEnterFrame = function() {
				if (episode._totalframes > 1) {
					// Return to menu if on replay screen for X frames.
					if (episode._currentframe + trailingFrames == episode._totalframes) {
						if (paddingFrames-- < 1) {
							showEpisode(null);
						}
					}
					else {
						// Reset frame count if not on replay screen.
						paddingFrames = 150; // 30 * 5
					}
				}
			};

			menu.button_Nuurakh.onRelease = function() {
				showEpisode("Nuurakh.swf", 26);
			};
			menu.button_Zadakh.onRelease = function() {
				showEpisode("Zadakh.swf", 26);
			};
			menu.button_Keerakh.onRelease = function() {
				showEpisode("Keerakh.swf", 26);
			};
			menu.button_Rorzakh.onRelease = function() {
				showEpisode("Rorzakh.swf", 26);
			};
			menu.button_Vorzakh.onRelease = function() {
				showEpisode("Vorzakh.swf", 26);
			};
			menu.button_Bordakh.onRelease = function() {
				showEpisode("Bordakh.swf", 26);
			};
		})[0]();
	.end
.end
