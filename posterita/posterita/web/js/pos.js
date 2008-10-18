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


var MAXIMUM_WAITING_TIME = 10000;
//var TROTTLE_PERIOD = 1000;
var TROTTLE_TIME = 0.5;
var ENABLE_LOGGING = true;
var ENABLE_VIRTUAL_KEYBOARD = true;


//The maps stores key-action mappings
var keyMap = new Array();

function addKey(key,action)
{
	keyMap[key] = action;
}

function getAction(key)
{
	if(keyMap[key])
	{
		eval(keyMap[key]);			
				
		return false;
	}
	
	return true;
}

//capture shortcut keys
function captureKey(e)
{
	var evtobj=window.event? event : e; //distinguish between IE's explicit event object (window.event) and Firefox's implicit.
	var unicode=evtobj.charCode? evtobj.charCode : evtobj.keyCode;
	
	var msg = "";
	
	if(evtobj['ctrlKey'])
		msg = msg + "CTRL-";
	
	if(evtobj['altKey'])
		msg = msg + "ALT-";
	
	if(evtobj['shiftKey'])
		msg = msg + "SHIFT ";
		
		
	var key = unicode;
	
	key = String.fromCharCode(unicode);	
	msg = msg + key;	
	msg = msg.toUpperCase();
	
	//perform the action associated with that key				
	return getAction(msg);		
	
}

function hideResult()
{
	displayResult("");
}

function printDocument()
{
	var w = window.open("","POS Order","width=800");
	var d = w.document;
	
	var printDiv =  document.getElementById("printDiv");
	
	d.write("<html><head>");
	d.write("<link rel='stylesheet' href='css/print.css' type='text/css'>	");
	d.write("</head><body bgcolor='#FFFFFF'>");
	d.write(printDiv.innerHTML);
	d.write("</body></head>");
	d.close();	
}

function enableDelete()
{
	var btn =  document.getElementsByName("deleteBtn");	
		
	btn[0].disabled = enableButton(); 	
}

function enableButton()
{
	/*
	var chk = document.getElementsByName("posOrderLineIds");	
	for(i=0;i<chk.length;i++)
	{
		if(chk[i].checked)
			return false;
	}
	
	return true;
	*/
	
	return false;	
}

function createXMLHttpRequest() {
   try { return new ActiveXObject("Msxml2.XMLHTTP"); } catch (e) {}
   try { return new ActiveXObject("Microsoft.XMLHTTP"); } catch (e) {}
   try { return new XMLHttpRequest(); } catch(e) {}
   alert("XMLHttpRequest not supported");
   return null;
 }
 
//var debugwin = window.open();
//var log = debugwin.document; 

/*
function AutoComplete(input,url,placeholder)
{
	this.input = input;		
	this.url = url;
	this.placeholder = document.getElementById(placeholder);
	this.previousQuery = null;
	this.getQuery = function(){return this.input.value;};
	
	this.resultList = null;
	this.resultListPos = 0;
	this.resultListSize = 0;
	
	
	this.input = document.getElementById(input);
	if(this.input == null)
	this.input = document.getElementsByName(input)[0];
	
	//registering events	
	this.input.onkeyup = this.onkeyup;
	this.input.onblur =  function(){this.placeholder.innerHTML = "";};
}

AutoComplete.prototype.onkeyup = function(e){
									alert('performing search');												
								};
								
AutoComplete.prototype.getResult = function(){
									alert('getting results');
								};

AutoComplete.prototype.selectNext = function(){};
AutoComplete.prototype.selectPrevious = function(){};
*/

function addRequiredLibrary(url)
{
	var head = document.getElementsByTagName('head')[0];
	var script = document.createElement('script');
	script.setAttribute('src',url);
	
	head.appendChild(script);
	toConsole('adding required library: ' + url);
}

function addRequiredStyleSheet(url)
{
	var head = document.getElementsByTagName('head')[0];
	var styleSheet = document.createElement('link');
	styleSheet.setAttribute('type','text/css');
	styleSheet.setAttribute('rel','stylesheet');
	styleSheet.setAttribute('href',url);
	
	head.appendChild(styleSheet);
	toConsole('adding required stylesheet: ' + url);
}


function toConsole(msg)
{
	try
	{
		if(!ENABLE_LOGGING)
		return;
	
		var console = document.createElement('div');
		var text = document.createTextNode("-----> "+msg);
		
		//console.appendChild(text);	
		//document.body.appendChild(console);
	}
	catch(e)
	{
		alert(e);
	}
	
}

function $focus(element)
{
	var el = document.getElementsByName(element);	
	
	if(el)
	{
		el[0].focus();
		toConsole('Setting focus to :' + element);
	}
	else
	{
		toConsole('Unable to set focus to :' + element);
	}	
	
}

function $FElement(element)
{
	var el = document.getElementsByName(element)[0];
	return el;	
	
}

function focusBarcode()
{
	$FElement('barCode').focus();
	toConsole("Setting focus to barcode");
}

function openCashDrawer()
{
	var url = 'CompletePOSOrderAction.do';
	var param = 'action=openCashDrawer';
	
	var success = function(request){alert('Opening drawer!');};
	var failure = function(request){alert('Oop some problem occured while communicating with server!');};
	
	var myAjax = new Ajax.Request( url, 
		{ 
			method: 'get', 
			parameters: param, 
			onSuccess: success, 
			onFailure: failure
		});
}

function fullScreen(theURL) 
{
	window.open(theURL, '', 'fullscreen=yes, scrollbars=auto');
}

function disableButtons()
{
	try
	{
		var elements = document.getElementsByTagName('input');
	
		for(var i = 0; i < elements.length; i++)
		{	
			if(elements[i].name != 'action')
			{		
				elements[i].disabled = true;
				elements[i].style.cursor = 'wait';
			}			
		}
		
		toConsole('Disable ' + elements.length + ' buttons');
		
		return true;		
		
	}
	catch(e)
	{
		toConsole(e);
	}
}


function initDisableButtons()
{
	toConsole('initDisableButtons');
	
	var elements = document.getElementsByTagName('input');
	
	for(var i = 0; i < elements.length; i++)
	{
		var type = elements[i].type;
		
		if(type == 'button')
		{
			var button = elements[i];
			var accessKey = button.accessKey;
			
			if(accessKey == 'c')
			{
				//button.onclick = disableButtons;
				var action = button.onclick;
				button.onclick = function(e){
					
				};
				
				toConsole('Found 1 button with accesskey: C');
			}
		}
	}
}

function setNumericInputMask(e)
{
	var evtobj = window.event? event : e; //distinguish between IE's explicit event object (window.event) and Firefox's implicit.
	var unicode = evtobj.charCode? evtobj.charCode : evtobj.keyCode;
	
	var validKeys = [8,9,13,20,27,37,38,39,40,45,46,48,49,50,51,52,53,54,55,56,57];
	
	for(var i = 0; i < validKeys.length; i++)
	{
		if(validKeys[i] == unicode)
		{
			 return true;
		}
	}
	
	alert('Accepts numeric values only');
	return false;
	
}

function showDocumentPDF(id)
{
	window.open('ViewDocumentPDFAction.do?action=viewOrderDocumentPDF&documentId=' + id, '_blank', 'width=800, height=600, status=no, toolbar=no, location=no, resizable=yes');
}

function showInventoryPDF(id)
{
	window.open('ViewInventoryPDFAction.do?action=viewInventoryDocumentPDF&inventoryId=' + id, '_blank', 'width=800, height=600, status=no, toolbar=no, location=no, resizable=yes');
}

function showInvoiceDocumentPDF(id)
{
	window.open('ViewDocumentPDFAction.do?action=viewInvoiceDocumentPDF&documentId=' + id, '_blank', 'width=800, height=600, status=no, toolbar=no, location=no, resizable=yes');
}

function showShipmentDocumentPDF(id)
{
	window.open('ViewDocumentPDFAction.do?action=viewShipmentDocumentPDF&documentId=' + id, '_blank', 'width=800, height=600, status=no, toolbar=no, location=no, resizable=yes');
}

function showPaymentDocumentPDF(id)
{
	window.open('ViewDocumentPDFAction.do?action=viewPaymentDocumentPDF&documentId=' + id, '_blank', 'width=800, height=600, status=no, toolbar=no, location=no, resizable=yes');
}



function enableVirtualKeyboard()
{
	if(!ENABLE_VIRTUAL_KEYBOARD)
	{
		return;
	}
	
	var textfields = document.getElementsByTagName('input');
	
	for(var i=0; i<textfields.length; i++)
	{
		var textfield = textfields[i];
		if(textfield.type == 'text')
		{
			textfield.onclick = function(e){showKeyboard(this);};
		}
	}
}

//some methods for using cookies
function createCookie(name,value,days) {
	if (days) {
		var date = new Date();
		date.setTime(date.getTime()+(days*24*60*60*1000));
		var expires = "; expires="+date.toGMTString();
	}
	else var expires = "";
	document.cookie = name+"="+value+expires+"; path=/";
	
	toConsole("Setting cookie: " + name + " value: " + value);
}

function readCookie(name) {
	toConsole("Reading cookie: " + name);
	
	var nameEQ = name + "=";
	var ca = document.cookie.split(';');
	for(var i=0;i < ca.length;i++) {
		var c = ca[i];
		while (c.charAt(0)==' ') c = c.substring(1,c.length);
		if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
	}
	return null;
}

function eraseCookie(name) {
	createCookie(name,"",-1);
}

//utils
/**
 * Loads the passed URL
 * @param url
 */
function get(url){
	window.location = url;
}

var Utils = {
	center : function(element){
		var eW = element.scrollWidth;
		var eH = element.scrollHeight;
		
		var sW = getViewportWidth();
		var sH = getViewportHeight();
		
		eW = parseInt(eW);
		eH = parseInt(eH);
		
		var x = (sW - eW)/2;
		var y = (sH - eH)/2;
		
		x = x + 'px';
		y = y + 'px';
		
		element.style.top = y;
		element.style.left = x;			
	},
	
	getViewportHeight : function() {
		if (window.innerHeight!=window.undefined) return window.innerHeight;
		if (document.compatMode=='CSS1Compat') return document.documentElement.clientHeight;
		if (document.body) return document.body.clientHeight; 
		return window.undefined;
	},
	
	getViewportWidth : function() {
		if (window.innerWidth!=window.undefined) return window.innerWidth; 
		if (document.compatMode=='CSS1Compat') return document.documentElement.clientWidth; 
		if (document.body) return document.body.clientWidth; 
		return window.undefined; 
	},
	
	getScrollXY :function () {
		  var scrOfX = 0, scrOfY = 0;
		  if( typeof( window.pageYOffset ) == 'number' ) {
		    //Netscape compliant
		    scrOfY = window.pageYOffset;
		    scrOfX = window.pageXOffset;
		  } else if( document.body && ( document.body.scrollLeft || document.body.scrollTop ) ) {
		    //DOM compliant
		    scrOfY = document.body.scrollTop;
		    scrOfX = document.body.scrollLeft;
		  } else if( document.documentElement && ( document.documentElement.scrollLeft || document.documentElement.scrollTop ) ) {
		    //IE6 standards compliant mode
		    scrOfY = document.documentElement.scrollTop;
		    scrOfX = document.documentElement.scrollLeft;
		  }
		  return [ scrOfX, scrOfY ];
	},
	
	about : function(){
		alert('Just a util class');
	}
};

function setInvisible(element){
	$(element).style.visibility = 'hidden';
}

function setVisible(element){
	toConsole('Setting visible');
	$(element).style.visibility = 'visible';
	//toConsole($(element).style.visibility);
}

var disableMask;

function showDisableMask()
{
	if(!disableMask)
	{
		disableMask = document.createElement('div');			
		disableMask.style.position 	= 'absolute';
		disableMask.style.zIndex 	= '900';
		disableMask.style.top 		= '0';
		disableMask.style.left 		= '0';
		disableMask.style.width 	= Utils.getViewportWidth();
		//disableMask.style.height 	= Utils.getViewportHeight(); IE
		disableMask.style.height 	= document.body.scrollHeight;
		disableMask.className 		= 'disableMask';
		
		document.body.appendChild(disableMask);		
	}
	
	//alert(document.body.scrollHeight);
	disableMask.style.display 	= 'block';		
}

function hideDisableMask()
{
	disableMask.style.display 	= 'none';
}

function openwindow(url,title,width,height)
{
	//var win = window.open(url,title);
	//alert(win);
	//var x = (Utils.getViewportWidth() - width)/2;
	//var y = (Utils.getViewportHeight() - height)/2;
	
	//x = parseInt(x);
	//y = parseInt(y);
	
	//alert([x,y]);	
	
	//win.moveTo(x,y);
}

function popPanel(panel)
{
	panel.style.visibility = 'hidden';
	panel.style.display = 'block';
	
	var width = parseInt(panel.scrollWidth);
	var height = parseInt(panel.scrollHeight);
	
	var screenHeight = parseInt(getViewportHeight());
	var screenWidth = parseInt(getViewportWidth());
	
	var top  = (screenHeight - height)/2;
	var left = (screenWidth - width)/2;
	
	//centering panel
	panel.style.top  = top  + "px";
	panel.style.left = left + "px";
	
	//show panel
	panel.style.visibility = 'visible';
}

function hidePanel(panel)
{
	if(panel != null)
	{
		if(panel.reset)
		{
			panel.reset();
		}
		panel.style.display = 'none';
	}
}

function showPanel(panel)
{
	panel.style.display = 'block';
}


function showErrorMessage(msg, focusElement)
{	
	var mask = document.createElement('div');
	mask.className = "mask";
	mask.style.visibility = 'hidden';		
	
	var div = document.createElement('div');
	div.innerHTML = msg;
	div.className = 'errormsg';
	//div.style.visibility = 'hidden';
	
	var div2 = document.createElement('div');
	div2.innerHTML = "<input type='button' value='ok' class='button'>";
	div2.style.textAlign = 'right';	
	div.appendChild(div2);
	
	//document.body.appendChild(div);
	div.mask = mask;
	mask.appendChild(div);
	document.body.appendChild(mask);
	
	var input = div.getElementsByTagName('input')[0];
	div.okbtn = input;
	div.okbtn.focusElement = focusElement;
	
	kH = parseInt(div.scrollHeight);
	kW = parseInt(div.scrollWidth);
	
	sH = parseInt(getViewportHeight());
	sW = parseInt(getViewportWidth());
	
	cH = (sH - kH)/2;
	cW = (sW - kW)/2;
	
	//centering the keyboard
	div.style.top = cH + "px";
	div.style.left = cW + "px";
	//div.style.visibility = 'visible';	
	mask.style.width = sW + "px";
	mask.style.height = sH + "px";
	mask.style.visibility = 'visible';		
	
	
	div.okbtn.onclick = function(e){
		e.stopPropagation();
		e.cancelBubble = true;
				
		document.body.removeChild(this.parentNode.parentNode.mask);		
		this.onblur = function(e){};
		if(this.focusElement)
		{			
			$(focusElement).focus();
		}
	};	
	
	div.okbtn.focus();	
	div.okbtn.onblur = function(){this.focus();};
	
}

var simulateOnClick = function(element){
	if(element == null) return;
	var event = {keyCode:13};
	var func = element.onclick;
	func.call(element,event);
	document.body.focus();
};

