/* keylistener.js

{{IS_NOTE
	Purpose:
		zkforge.KeyListener
	Description:
		Keylistener component for ZK.
}}IS_NOTE

Copyright 2007 by Easit AB. All rights reserved.

{{IS_RIGHT
	This program is distributed under GPL Version 2.0 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
*/

zk.$package('zkforge');
zkforge.KeyListener = zk.$extends(zul.Widget, {
	_ctrlKeys: null,
	_autoBlur: true,

	getCtrlKeys: function() {
		return this._ctrlKeys;
	},
	
	setCtrlKeys: function(ctrlKeys) {
		if(this._ctrlKeys != ctrlKeys) {
			this._ctrlKeys = ctrlKeys;
		}
	},
	
	getAutoBlur: function() {
		return this._autoBlur;
	},
	
	setAutoBlur: function(autoBlur) {
		if(this._autoBlur != autoBlur) {
			this._autoBlur = autoBlur;
		}
	},
	
	bind_: function (desktop, skipper, after) {
		this.$supers('bind_', arguments);
		
		var self = this;
		jq(document).ready(function () { 
			jq(document).keydown(function (evt) {
				self.keyDown(evt);
			});
		});
	},
	
	keyDown: function(evt) {
		if (!evt) evt = window.event;

		var keycode = evt.keyCode, zkcode; //zkcode used to search z.ctkeys
		switch (keycode) {
			case 13: //ENTER
				zkcode = 'K';
			break;
			case 27: //ESC
			break;
			case 16: //Shift
			case 17: //Ctrl
			case 18: //Alt
			return true;
			case 44: //Ins
			case 45: //Del
				zkcode = keycode == 44 ? 'I': 'J';
			break;
			default:
				if (keycode >= 33 && keycode <= 40) { //PgUp, PgDn, End, Home, L, U, R, D
					zkcode = String.fromCharCode('A'.charCodeAt(0) + (keycode - 33));
						//A~H: PgUp, ...
					break;
				} else if (keycode >= 112 && keycode <= 123) { //F1: 112, F12: 123
					zkcode = String.fromCharCode('P'.charCodeAt(0) + (keycode - 112));
						//M~Z: F1~F12
					break;
				} else if (evt.ctrlKey || evt.altKey) {
					zkcode = String.fromCharCode(keycode).toLowerCase();
					break;
				}
			return true;
		}
		
		// If keyboard command is registered for this component, send request
		if(this.inCtrlKeys(evt, zkcode, this._ctrlKeys) ) {

			// If autoblur is specified, set focus to keylistener to trigger onBlur for focused component
			if(this._autoBlur == true){
				this.tabIndex = 32000;
				this.focus();
				this.tabIndex = 0;
			}

			zAu.send(new zk.Event(zk.Widget.$(this), 'onCtrlKey', {keyCode: keycode, ctrlKey: evt.ctrlKey, shiftKey: evt.shiftKey, altKey: evt.altKey}, {toServer: true}));
			
			// Do not send request directly, otherwise onChange events won't be fired correctly in IE
			//setTimeout(function () {
			//	zAu.send(new zk.Event(zk.Widget.$(this), 'onCtrlKey', {keyCode: keycode, ctrlKey: evt.ctrlKey, shiftKey: evt.shiftKey, altKey: evt.altKey}, {toServer: true}), 38);
			//}, 10);

			evt.stop();

			// Special handling for IE that Event.stop doesn't support
			if (document.all && window.event && !evt.preventDefault) {
				evt.keyCode = 0;
			}
			return false;
		}
		return true;
	},
	
	inCtrlKeys: function(evt, zkcode, keys) {
		if (keys) {
			//format: ctl+k;alt+k;shft+k;k
			var cc = evt.ctrlKey ? '^': evt.altKey ? '@': evt.shiftKey ? '$': '#';
			var j = keys.indexOf(cc), k = keys.indexOf(';', j + 1);
			if (j >=0 && k >= 0) {
				keys = keys.substring(j + 1, k);
				return keys.indexOf(zkcode) >= 0;
			}
		}
		return false;
	}
});
