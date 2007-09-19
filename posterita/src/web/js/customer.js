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


//----------------------------------FUNCTION DECLARATIONS---------------------------------------------
//create new customer
function createCustomer()
{
	window.location = "InitCreatePOSCustomer.do?action=initCreatePOSCustomer&creatingFromOrder=true";
}

//sets the current customer to default
function defaultCustomer()
{
	try
	{
		var bpartner = document.getElementsByName('bpartnerId')[0];
		var bpartnerName = document.getElementsByName('partnerName')[0];
		
		bpartner.value = "";
		bpartnerName.value = "";
	}
	catch(e)
	{
		toConsole('Unable to set default customer');
		toConsole(e);
	}
		
}

//gets the custom name.
function getCustomerName()
{
	var customerID = $FElement('bpartnerId').value;		
	var url = 'POSCustomerAction.do';
	var pars = 'action=getNameByID&bpartnerId=' + customerID;
	
	toConsole('Requesting: ' + url + '?' + pars);
	
	var myAjax = new Ajax.Request( url, 
	{ 
		method: 'get', 
		parameters: pars, 
		onSuccess: evaluateResponse, 
		onFailure: reportError
	});

}

//set customer name	
function setCustomerName(name)
{
	$FElement('partnerName').value = name;	
}

function evaluateResponse(request)
{
	var script = request.responseText;
	eval(script);		
}

function reportError(request)
{
	alert('Some error occured while communicating with the server. Please try again.');
}
//------------------------------REGISTERING SOME EVENTS OR INITIALISATION-----------------------------

$FElement('bpartnerId').onkeyup = function(e)
{
	try
	{
		var event = e||window.event;
	
		if(event.keyCode == 13)
		{
			//request client name
			getCustomerName();
			this.blur();
		}
	}
	catch (e)
	{
		toConsole(e);
	}
};

$('customerQuery').Autocompleter = new Ajax.Autocompleter('customerQuery','customerSearchResult','SearchCustomerAction.do',{
paramName:'customerQuery',
frequency:TROTTLE_TIME,
afterUpdateElement:function(e1,e2){
					var bpartner = $FElement('bpartnerId');
					var bpartnerName = $FElement('partnerName');																							
					
																											
					if(e2.value != '-1')
					{
						toConsole('1');
						if(e2.value)
						{
							toConsole('1.1.1');
							bpartner.value = e2.value;
						}
						else
						{
							toConsole('1.1.2');
							bpartner.value = '';
						}
						
						if(bpartnerName)
						{
							toConsole('1.2');
							bpartnerName.value = e2.getAttribute('name');															
						}//if
					}
					else
					{
						toConsole('2');
						
						bpartner.value = "";	
						if(bpartnerName)
						{
							toConsole('2.1');
							bpartnerName.value = "";														
						}//if
					}//if
					
					toConsole('Setting customer-->' + bpartnerName.value);
					
					$('customerQuery').value = "";
					
					//focusBarcode();
																			
																								
			}												
																	
});

var bpartner = $FElement('bpartnerId');
bpartner.initialValue = bpartner.value;