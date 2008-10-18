<script>
/*
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

/*
	@author praveen
 */




		
	document.getElementsByName('barCode')[0].value="";
	document.getElementsByName('quantity')[0].value="1";
	
	focusBarcode();	
	
	function incrementQty()
	{
		var q = document.getElementsByName('quantity')[0];
		if(q.value == '')
			q.value = 1;
		
		q.value = parseInt(q.value) + 1;
		
		focusBarcode()
	}
	
	function decrementQty()
	{
		var q = document.getElementsByName('quantity')[0];
		if(q.value > 1)
			q.value = parseInt(q.value) - 1;
			
		focusBarcode();
	}
	
	function setQuantity()
	{
		document.getElementsByName('quantity')[0].value=prompt("Enter Quantity");		
	}
	
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
	
	function focusBarcode()
	{
		//document.getElementsByName('barCode')[0].focus();
		toConsole("Setting focus to barcode");
	}	
	
	function getExistingCustomers()
	{
		setAction(document.forms[0],'GetExistingPOSCustomersAction.do','getExistingPOSCustomers');
	}
	
	function setTrxType(type)
	{
		toConsole(type);
		
		document.forms[0].trxType.value = type;
		
		var divCash = $('cashNoTxt');
		var divCard = $('cardNoTxt');
		var divCheque = $('chequeNoTxt');
		
		Element.hide(divCash);
		Element.hide(divCard);
		Element.hide(divCheque);
		
		if(type == 'Cash')
		{
			Element.show(divCash);
		}			
			
		if(type == 'Card')
		{
			Element.show(divCard);
			$focus('creditCardNumber');
		}
			
		if(type == 'Cheque')
		{
			Element.show(divCheque);
			$focus('chequeNo');
		}
		
		return false;
	}
	
	try
	{
		Element.hide($('cashNoTxt'));
		Element.hide($('cardNoTxt'));
		Element.hide($('chequeNoTxt'));
		
		if(document.forms[0].trxType.value)
			setTrxType(document.forms[0].trxType.value);
		else
			setTrxType('Cash');
	}
	catch (e)
	{
		toConsole(e);
	}
	
	document.forms[0].barCode.onkeyup = function(e){
	
		try
		{
			var event = e||window.event;
		
			if(event.keyCode == 13)
			{
				addToCart();
			}
		}
		catch (e)
		{
			toConsole(e);
		}
	};
	
	focusBarcode();
		
	try
	{
		//initDisableButtons();
		addRequiredLibrary('js/shoppingCart.js');
		//addRequiredLibrary('js/customer.js');
	}
	catch(e)
	{
		alert(e);
	}
	
	
	function addToCart()
	{
		try
		{
			var method = "addToShoppingCart";
			var myForm = document.forms[0];
			var actionElement = document.getElementsByName('action')[0];
			
			var productIdElement = document.getElementsByName('productId')[0];
			var ifAddElement =	document.getElementsByName('ifAdd')[0];	
			
			actionElement.value = method;
			//productIdElement = "";
			ifAddElement = "true";
			
			myForm.submit();
		}
		catch(e)
		{
			toConsole(e);
		}	
	} 	

</script> 
