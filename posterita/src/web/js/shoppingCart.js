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
//scrolls to the bottom of the cart
function scrollDownCart()
{
	if($('items'))
	{
		$('items').scrollTop = $('items').scrollHeight;
	}	
}

//incrementing, decrementing and deleting the shopping cart items using ajax
// 1.Increment the qty for the product	
function incrementCart(productId)
{
	
	try
	{
			
		var url = 'AddToPOSShoppingCartAction.do';
		var pars = 'action=incrementQty&productId='+productId+ '&ifAdd=true'+ '&orderType=' + $FElement('orderType').value;
				
		var myAjax = new Ajax.Request( url, 
		{ 
			method: 'get', 
			parameters: pars, 
			onSuccess: refreshShoppingCart, 
			onFailure: reportShoppingCartError
		});	
			
		
	}
	catch(e)
	{
		toConsole(e);
	}		
}

//2.Decrement the qty for the product
function decrementCart(productId)
{

	try
	{	
	
			
		var url = 'AddToPOSShoppingCartAction.do';
		var pars = 'action=decrementQty&productId='+productId+ '&ifAdd=false'+ '&orderType=' + $FElement('orderType').value;
		
		var myAjax = new Ajax.Request( url, 
		{ 
			method: 'get', 
			parameters: pars, 
			onSuccess: refreshShoppingCart, 
			onFailure: reportShoppingCartError
		});	
		
	}
	catch(e)
	{
		toConsole(e);
	}		
}

//3.Delete all the products with the ID from the cart
function deleteItemFromCart(productId)
{
	try
	{
		var url = 'DeleteFromShoppingCartAction.do';
		var pars = 'action=deleteFromPOSCart&productId='+productId+ '&orderType=' + $FElement('orderType').value;
		
		var myAjax = new Ajax.Request( url, 
		{ 
			method: 'get', 
			parameters: pars, 
			onSuccess: refreshShoppingCart, 
			onFailure: reportShoppingCartError
		});
	}
	catch(e)
	{
		toConsole(e);
	}
}

//Refreshs the content of the shopping cart
function refreshShoppingCart(request)
{
	//var top = $('items').scrollTop;
	$('shoppingCart').innerHTML = request.responseText;
	//$('items').scrollTop = top;
	//scrollDownCart();	
	requestIndicator.hide();
}

//Reports an error
function reportShoppingCartError(request)
{
	alert('Some error occured while communicating with the server. Please try again.');
	alert(request.responseText);
	requestIndicator.hide();
	var win = window.open();
	win.document.write(request.responseText);
	win.document.close();
}

addRequiredLibrary('js/test.js');
var requestIndicator;

/*
var ShoppingCart = {

	init : function(){
		//this.indicator = new AJAXIndicator('Please wait...');
		alert('Shopping Cart');
	},
	
	indicator : null
};
*/

var init = function(){
		requestIndicator = new AJAXIndicator('Please wait...');		
	};
	
Event.observe(window,'load',init,false);
//---------------------------------------------------------------------------------------
//calling methods