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


var PINPanel = $('PINPanel');
var divBack  = $('divBack');
var PIN      = $('PIN');
var closeBtn = $('closeBtn');
 
function checkout()
{
	showPINPanel();		
}

function focusBarcode()
{
	document.getElementsByName('barCode')[0].focus();
	//toConsole("Setting focus to barcode");
}
		
function initializeComponents()
{
	PINPanel.style.top	= '' + (getViewportHeight()- 60)/2 ;
	PINPanel.style.left = '' + (getViewportWidth() - 100)/2;
	PINPanel.style.display  = 'none';
	PINPanel.style.position = 'absolute';
	divBack.style.zIndex = '1000';
	
	
	//PIN.focus();
	
	closeBtn.onblur = function(){
		try
		{
			PIN.focus();
		}
		catch(e)
		{
			toConsole(e);
		}
	};
	
	divBack.style.position = 'absolute';
	divBack.style.zIndex = '900';
	divBack.style.display = 'none';
	divBack.style.top = '0';
	divBack.style.left = '0';
	divBack.style.width = getViewportWidth();
	divBack.style.height = getViewportHeight();	
	
	PINPanel.onblur = function(){
		//PIN.focus();
	};
	
	PIN.onkeyup = function(e)
	{
		try
		{
			var event = e||window.event;
		
			if(event.keyCode == 13)
			{
				validatePIN();
			}
		}
		catch (e)
		{
			toConsole(e);
		}
	};
	
	toConsole('PIN Panel initialised');
}

function enablePIN()
{
	PIN.disabled = false;
	closeBtn.style.visibility = 'visible';
}

function disablePIN()
{
	PIN.disabled = true;
	closeBtn.style.visibility = 'hidden';
}

function reportError(request)
{
	alert('Some error occured while communicating with the server. Please try again.')
}

function PINError(msg)
{
	alert(msg);
	hidePINPanel();
	enablePIN();
	PIN.value='';
}
	
function showPINPanel()
{		
	PINPanel.style.display = 'block';		
	divBack.style.display  = 'block';
	PIN.focus();
}

function hidePINPanel()
{		
	PINPanel.style.display = 'none';		
	divBack.style.display  = 'none';
}

/*	
function validatePIN()
{
	var pin  = PIN.value;		
	var url  = 'GetPOSPaymentDetailsAction.do';
	var pars = 'action=validateAdvanceOrderPIN&userPIN=' + pin;
	
	var myAjax = new Ajax.Request( url, 
	{ 
		method: 'get', 
		parameters: pars, 
		onSuccess: evaluateResponse, 
		onFailure: reportError
	});
	
	disablePIN();
}
*/

function evaluateResponse(request)
{
	var scripts = request.responseText;
	eval(scripts);	
}


function getViewportHeight() {
	if (window.innerHeight!=window.undefined) return window.innerHeight;
	if (document.compatMode=='CSS1Compat') return document.documentElement.clientHeight;
	if (document.body) return document.body.clientHeight; 
	return window.undefined; 
}

function getViewportWidth() {
	if (window.innerWidth!=window.undefined) return window.innerWidth; 
	if (document.compatMode=='CSS1Compat') return document.documentElement.clientWidth; 
	if (document.body) return document.body.clientWidth; 
	return window.undefined; 
}

//initialising
Event.observe(window,'load',initializeComponents,false);