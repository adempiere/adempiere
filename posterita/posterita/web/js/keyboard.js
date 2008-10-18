/**
 *  Product: Posterita Web-Based POS and Adempiere Plugin
 *  Copyright (C) 2007  Posterita Ltd
 *  This file is part of POSterita
 *  
 *  POSterita is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program; if not, write to the Free Software Foundation, Inc.,
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

/**
	@author praveen
 */


function insertAtCursor(myField, myValue) 
{
	//IE support
	if (document.selection) 
	{
		myField.focus();
		sel = document.selection.createRange();
		sel.text = myValue;
	}
	//MOZILLA/NETSCAPE support
	else if (myField.selectionStart || myField.selectionStart == 0) 
	{
		var startPos = myField.selectionStart;
		var endPos = myField.selectionEnd;
		myField.value = myField.value.substring(0, startPos) + myValue + myField.value.substring(endPos, myField.value.length);
	} 
	else 
	{
		myField.value += myValue;
	}
}

function init()
{
	var textfield = this.output;
	var keyboard = document.getElementById('keyboard');
	var keys = keyboard.getElementsByTagName('td');
	
	for(var i=0; i<keys.length; i++){
		var key = keys[i];
		key.keyboard = this;
		
		key.onclick = function(e){			
			var value = this.innerHTML;
			
			if(value == '&nbsp;'){
				textfield.focus();
				return;
			}
			
			if(value == 'SPACE'){
				value = ' ';
			}
			
			if(value == 'CAPS'){
				this.keyboard.caps = !this.keyboard.caps;
				this.keyboard.setCaps();
				textfield.focus();
				return;
			}
			
			if(value == 'BACK'){
				value = textfield.value;
				
				if(value.length == 0){
					textfield.focus();
					return;
				}					
				else{
					value = value.substring(0,value.length-1);
					textfield.value = value;
					
					//hacking autocompleter
					if(textfield.Autocompleter)
					{
						var autocompleter = textfield.Autocompleter;					
						var onKeyPress = autocompleter.onKeyPress;
						onKeyPress.call(autocompleter,event);
					}
					return;
				}					
			}
			
			if(value == 'ENTER'){			
				value = '\n';
				
				//simulating press ENTER
				//window.event.keyCode = 13;
				var event = {keyCode:13};
				
				if(textfield.onkeyup)
				{
					toConsole('simulating enter');
					var func = textfield.onkeyup;
					func.call(textfield,event);	
				}
				
				//hacking autocompleter
				if(textfield.Autocompleter)
				{
					var autocompleter = textfield.Autocompleter;					
					var onKeyPress = autocompleter.onKeyPress;
					onKeyPress.call(autocompleter,event);
				}
					else
				{
					if(textfield.form)
					{
						textfield.form.submit();
					}
				}
				return false;
							
			}
			
			if(value == 'HIDE'){
				if(textfield) textfield.focus();				
				this.keyboard.hide();
				return;
			}
			
			//insertAtCursor(textfield,value);
			textfield.value = textfield.value.toString() + value.toString();
			
			//hacking autocompleter
			if(textfield.Autocompleter)
			{
				var autocompleter = textfield.Autocompleter;
				var event = {keyCode:65};
				
				var onKeyPress = autocompleter.onKeyPress;
				onKeyPress.call(autocompleter,event);
			}
			else
			{
				textfield.focus();
			}
						
			return false;
		};
	}
	
}

function Keyboard(){
	this.output = null;
	this.hidden = 'true';
	this.caps = 'true';
	this.keypadpanel = document.getElementById('keyboard');
		
	this.keypadpanel.innerHTML = '<div name="keyboardHandler" class="keyboardHandler"></div>' +
	'<div id="uppercase">' +
	'<table class="keyboard">' +
	'<tr>' +		
		'<td>1</td>' +
		'<td>2</td>' +
		'<td>3</td>' +
		'<td>4</td>' +
		'<td>5</td>' +
		'<td>6</td>' +
		'<td>7</td>' +
		'<td>8</td>' +
		'<td>9</td>' +
		'<td>0</td>' +
		'<td>HIDE</td>' +
	'</tr>' +
	'<tr>' +
		'<td>Q</td>' +
		'<td>W</td>' +
		'<td>E</td>' +
		'<td>R</td>' +
		'<td>T</td>' +
		'<td>Y</td>' +
		'<td>U</td>' +
		'<td>I</td>' +
		'<td>O</td>' +
		'<td>P</td>' +
		'<td>BACK</td>' +
	'</tr>' +
	'<tr>' +
		'<td>A</td>' +
		'<td>S</td>' +
		'<td>D</td>' +
		'<td>F</td>' +
		'<td>G</td>' +
		'<td>H</td>' +
		'<td>J</td>' +
		'<td>K</td>' +
		'<td>L</td>' +
		'<td colspan="2">ENTER</td>' +
	'</tr>' +
	'<tr>' +
		'<td colspan="2">CAPS</td>' +	
		'<td>Z</td>' +
		'<td>X</td>' +
		'<td>C</td>' +
		'<td>V</td>' +
		'<td>B</td>' +
		'<td>N</td>' +
		'<td>M</td>' +
		'<td>,</td>	' +
		'<td>.</td>' +	
	'</tr>' +	
	'<tr>' +
		'<td colspan="11">SPACE</td>' +
	'</tr>' +
	'</table>' +
	'</div>' +
	'<div id="lowercase">' +
	'<table class="keyboard">' +
	'<tr>' +		
		'<td>1</td>' +
		'<td>2</td>' +
		'<td>3</td>' +
		'<td>4</td>' +
		'<td>5</td>' +
		'<td>6</td>' +
		'<td>7</td>' +
		'<td>8</td>' +
		'<td>9</td>' +
		'<td>0</td>' +
		'<td>HIDE</td>' +
	'</tr>' +
	'<tr>' +
		'<td>q</td>' +
		'<td>w</td>' +
		'<td>e</td>' +
		'<td>r</td>' +
		'<td>t</td>' +
		'<td>y</td>' +
		'<td>u</td>' +
		'<td>i</td>' +
		'<td>o</td>' +
		'<td>p</td>' +
		'<td>BACK</td>' +
	'</tr>' +
	'<tr>' +
		'<td>a</td>' +
		'<td>s</td>' +
		'<td>d</td>' +
		'<td>f</td>' +
		'<td>g</td>' +
		'<td>h</td>' +
		'<td>j</td>' +
		'<td>k</td>' +
		'<td>l</td>' +
		'<td colspan="2">ENTER</td>' +
	'</tr>' +
	'<tr>' +
		'<td colspan="2">CAPS</td>' +	
		'<td>z</td>' +
		'<td>x</td>' +
		'<td>c</td>' +
		'<td>v</td>' +
		'<td>b</td>' +
		'<td>n</td>' +
		'<td>m</td>' +
		'<td>,</td>	' +
		'<td>.</td>' +	
	'</tr>' +	
	'<tr>' +
		'<td colspan="11">SPACE</td>' +
	'</tr>' +
	'</table>' +
	'</div>' +
	'<div name="keyboardHandler" class="keyboardHandler"></div>';
		
}

Keyboard.prototype.show = function(e){
	
	if(this.hidden)
	{
		this.keypadpanel.style.visibility = 'hidden';	
		this.keypadpanel.style.display = 'block';
		//this.keypadpanel.style.top = '10px';
		//this.keypadpanel.style.left = '10px';
		this.hidden = false;
		
		try
		{
			//setting keyboard state in cookie
			createCookie('pos_keyboard_on',"true",7);
		}
		catch(e)
		{
			toConsole(e);
		}
		
	}	
};

Keyboard.prototype.hide = function(e){
	this.keypadpanel.style.display = 'none';
	this.hidden = true;
	
	try
	{toConsole
		//setting keyboard state in cookie
		createCookie('pos_keyboard_on',"false",7);
	}
	catch(e)
	{
		toConsole(e);
	}
};

Keyboard.prototype.setCaps = function(e){
		
	var uppercasekeyboard = document.getElementById('uppercase');
	var lowercasekeyboard = document.getElementById('lowercase');
	
	//toConsole(uppercasekeyboard.style.display);
	//toConsole(lowercasekeyboard.style.display);
	
	if(this.caps)
	{
		uppercasekeyboard.style.display = 'block';
		lowercasekeyboard.style.display = 'none';
	}
	else
	{
		uppercasekeyboard.style.display = 'none';
		lowercasekeyboard.style.display = 'block';
	}
	
	
};

Keyboard.prototype.init = init;

function showKeyboard()
{	
	keyboard.show();
	keyboard.init();
	keyboard.setCaps();
	
	kH = parseInt(keyboard.keypadpanel.scrollHeight);
	kW = parseInt(keyboard.keypadpanel.scrollWidth);
	
	sH = parseInt(getViewportHeight());
	sW = parseInt(getViewportWidth());
	
	cH = (sH - kH)/2;
	cW = (sW - kW)/2;
	
	//centering the keyboard
	keyboard.keypadpanel.style.top = cH + "px";
	keyboard.keypadpanel.style.left = cW + "px";
	keyboard.keypadpanel.style.visibility = 'visible';	
	
}

function bindKeyboard(output)
{
	keyboard.output = output;
	keyboard.init();
	keyboard.setCaps();
}

function enableVirtualKeyboard()
{
	keyboard = new Keyboard();
	
	var keyboardicon = document.getElementById('keyboardicon');
	
	keyboardicon.style.display = 'inline';
	keyboardicon.onclick = function(e){
		showKeyboard();		
	};
	
	var textfields = document.getElementsByTagName('input');
	
	for(var i=0; i<textfields.length; i++)
	{
		var textfield = textfields[i];
		if(textfield.type == 'text')
		{
			textfield.onclick = function(e){				
				bindKeyboard(this);
				};
		}
	}
	
	var keyboardHandlers = document.getElementsByName('keyboardHandler');
	var keyboardPanel = document.getElementById('keyboard');
	
	for(var j=0; j<keyboardHandlers.length; j++)
	{
		Drag.init(keyboardHandlers[j],keyboardPanel);
	}
	
	
	//Getting previous keyboard state from cookie	
	var show = readCookie('pos_keyboard_on');
	
	if(show != null && show == 'true')	
	{
		showKeyboard();
	}

	
}

//initialising
var keyboard = null;
Event.observe(window,'load',enableVirtualKeyboard,false);