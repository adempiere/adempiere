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
	if($('shoppingCart'))
	{
		$('shoppingCart').scrollTop = $('shoppingCart').scrollHeight;
	}	
}


//adding product to cart
function addToCart(barcode)
{
	cartIndex = null;
	if(barcode == null) return;
	if(barcode == '') return;
	
	try
	{
			
		var url = 'InventoryCartAction.do';
		var pars = 'action=addProduct&barCode='+barcode+'&priceListId='+priceList;
				
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

//incrementing, decrementing and deleting the shopping cart items using ajax
// 1.Increment the qty for the product	
function incrementCart(productId)
{
	if(productId == null) return;
	
	try
	{
			
		var url = 'InventoryCartAction.do';
		var pars = 'action=incrementQty&productId='+productId+ '&ifAdd=true&priceListId='+priceList;
				
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
	if(productId == null) return;
	
	try
	{	
	
			
		var url = 'InventoryCartAction.do';
		var pars = 'action=decrementQty&productId='+productId+ '&ifAdd=false&priceListId='+priceList;
		
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
	if(productId == null) return;
	
	cartIndex = null;
	updateQty(productId, '0');
}

//Refreshs the content of the shopping cart
function refreshShoppingCart(request)
{
	try
	{
		var response = request.responseText;	
		$('shoppingCart').innerHTML = response;
		$('total').innerHTML = $('cartTotalCount').innerHTML;
		$('product.total.lines').innerHTML = cartLines.length;
		$('product.total.count').innerHTML = $('cartTotalCount').innerHTML;
		$('product.total.book').innerHTML = $('cartTotalBook').innerHTML;
		$('product.total.csv').innerHTML = $('cartTotalCsv').innerHTML;
		$('product.discrepancy').innerHTML = $('cartTotalCount').innerHTML - $('cartTotalBook').innerHTML;
		scrollDownCart();	
		response.evalScripts(); //display error messages
	} 
	catch(e)
	{
		showErrorMessage('Failed to resfresh cart! Cause:' + e);
	}
	
	addBehaviourToCart();
}

//Reports an error
function reportShoppingCartError(request)
{
	alert('Some error occured while communicating with the server. Please try again.');
	alert(request.responseText);
	var win = window.open();
	win.document.write(request.responseText);
	win.document.close();
}

//addRequiredLibrary('js/test.js');
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

function completeInventory()
{
	$('inventoryForm').submit();
}

//Update the qty for the product
function updateQty(productId, quantity)
{
	if(productId == null) return;
	
	if (quantity == "")
	{
		return;
	}
	
	var amount = parseFloat(quantity);
	
	if (isNaN(amount) || amount < 0)
	{
		showErrorMessage('Invalid quantity! ' + quantity, 'qty');
		$('qty').value = "";
		return;
	}
	
	try
	{		
		var url = 'InventoryCartAction.do';
		var pars = 'action=updateQty&productId=' + productId + '&ifAdd=false' + '&priceListId='+priceList +'&qtyCount=' + amount;
		
		var myAjax = new Ajax.Request( url, 
		{ 
			method: 'get', 
			parameters: pars, 
			onSuccess: refreshShoppingCart, 
			onFailure: reportShoppingCartError
		});	
		
		return false;
	}
	catch(e)
	{
		toConsole(e);
	}
	
	return false;

}

//Reload shopping cart
function reloadCart()
{
	try
	{				
		var url = 'InventoryCartAction.do';
		var pars = 'action=reloadCart';
		
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


function clearCart()
{
	maxRows = 0;
	excessRows = 0;
	
	try
	{				
		var url = 'InventoryCartAction.do';
		var pars = 'action=clearCart';
		
		$('inventory.docno').innerHTML = 'N/A';	
		$('inventory.description').innerHTML = description;
		$('inventory.status').innerHTML = 'N/A';
		$('inventoryId').value = inventoryId = 0;
		
		var myAjax = new Ajax.Request( url, 
		{ 
			method: 'get', 
			parameters: pars, 
			onSuccess: refreshShoppingCart,
			onComplete: resetInfo,
			onFailure: reportShoppingCartError
		});	
		
	}
	catch(e)
	{
		toConsole(e);
	}		

}

function handleCheckout(request)
{
	var response = request.responseText;
	var result = eval('(' + response + ')');
	
	if(result.error)
	{
		showErrorMessage(result.error);
	}
	else
	{
		printOrder(result.orderID);
		reloadCart();
	}
}


function addBehaviourToCart()
{
	var rows = new Array();
	var count = 1;
	
	while(true)
	{
		var row = $('row' + count);
		if(row == null) break;
		
		rows.push(row);
		count ++;
	}
	
	for(var i=0; i<rows.length; i++)
	{
		var tr = rows[i];
		var productId = tr.getAttribute('productId');
		var qty = tr.getAttribute('qty');
		var qtyStock = tr.getAttribute('qtybook');
		
		tr.style.cursor = 'pointer';
		tr.productId = productId;
		tr.qty = qty;
		tr.index = i;
		
		if(productId == product_id)
		{
			$('qty').value = qty;
		}
		
		/*
		tr.onmouseover = function(){
			this.style.backgroundColor = '#ffb';
		};
		
		tr.onmouseout = function(){
			this.style.backgroundColor = '#FFF';
		};
		*/
		
		tr.onclick = function(e){
			product_id = this.productId;
			qty = this.qty;
			
			$('qty').value = this.qty;
			
			getProductInfo(this.productId);	
			
			for(var j=0; j<cartLines.length; j++)
			{
				cartLines[j].style.backgroundColor = '#FFFFFF';
			}
			
			this.style.backgroundColor = '#9ACF30';
			cartIndex = this.index;		
			
		};
		
		/*
		if(product_id == null)
		{
			//set the active product the last in the cart
			product_id = rows[rows.length-1].productId;
			$('qty').value = rows[rows.length-1].qty			
		}
		*/		
						
	}
	
	cartLines = rows;
	if(cartIndex == null)
	{
		cartIndex = rows.length - 1;	
	}
	
	if(cartLines.length > 0)
	{
		var line = cartLines[cartIndex];
		simulateOnClick(line);	
		
	}

	if(cartLines.length > maxRows)
	{
		var shoppingCart = $('shoppingCart');
		shoppingCart.scrollTop = shoppingCart.scrollHeight;
	}
	
}

//Reload the shopping cart. Prices are calculated for the pricelist 
function setShoppingCartPriceList()
{	
	try
	{				
		var url = 'AddToPOSShoppingCartAction.do';
		var pars = 'action=updatePriceList&priceListId='+priceList;
		
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

function scrollCart()
{
	var shoppingCart = $('shoppingCart');	
	
	if(cartIndex > maxRows)
	{
		shoppingCart.scrollTop = shoppingCart.scrollHeight;
	}
	else
	{
		shoppingCart.scrollTop = 0;
	}
}

	
//Event.observe(window,'load',init,false);
//---------------------------------------------------------------------------------------
//calling methods