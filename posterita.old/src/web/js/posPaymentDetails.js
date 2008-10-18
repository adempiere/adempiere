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


//registering shortcut keys
//keyMap['ALT-1'] = "showCash()";
//keyMap['ALT-2'] = "showCard()";
//keyMap['ALT-3'] = "showCheque()";
//keyMap['ALT-4'] = "showMixed()";
//keyMap['ALT-S'] = "createOrder()";
//if($F(trxType))
var func = function(){

	init();
	
	var type = document.forms[0].trxType.value;
	
	if(type=='Mixed')
		showMixed();	
	else if(type=='Card')
		showCard();	
	else if(type=='Cheque')
		showCheque();	
	else
		showCash();	
	};
	
if(window.onload)
{
	var f = window.onload;
	window.onload = function()
	{
		f();
		func();
	};
}
else
{
	window.onload = func;
}


function show(elementId){
	document.getElementById(elementId).style.display="inline";
}

function hide(elementId){
	document.getElementById(elementId).style.display="none";
}					

function showMixed()
{
	show("cashLabel");show("cashTxt");
	show("cardLabel");show("cardTxt");
	show("cardNoLabel");show("cardNoTxt");
	show("chequeLabel");show("chequeTxt");
	show("chequeNoLabel");show("chequeNoTxt");
	
	setTrxType("Mixed");
	document.getElementsByName('paymentByCard')[0].focus();
	
	
	var cashInput = document.getElementsByName('paymentByCash')[0];
	var cardInput = document.getElementsByName('paymentByCard')[0];
	var chequeInput = document.getElementsByName('paymentByChq')[0];
	
	cashInput.initialAmount = cashInput.value;
	cardInput.previousAmount = 0;
	chequeInput.previousAmount = 0;
	
	
	cardInput.onkeyup = function()
	{
		try
		{
			var newValue = new Number(cashInput.initialAmount - cardInput.value - chequeInput.value).toFixed(2);
			
			if(newValue >= 0)
			{
				cashInput.value = newValue;
				cardInput.previousAmount = newValue;
				
			}
			else
			{
				var max = cashInput.initialAmount - chequeInput.value;
				alert('Card Amount should not exceed ' + max);
				
				cardInput.value = 0.0;
				
				newValue = new Number(cashInput.initialAmount - cardInput.value - chequeInput.value).toFixed(2);
				cashInput.value = newValue;
			}
		}
		catch(e)
		{
			toConsole(e);
		}
		
	}
	
	chequeInput.onkeyup = function()
	{
		try
		{
			var newValue = new Number(cashInput.initialAmount - cardInput.value - chequeInput.value).toFixed(2);
			
			if(newValue >= 0)
			{
				cashInput.value = newValue;
				chequeInput.previousAmount = newValue;
				
			}
			else
			{
				var max = cashInput.initialAmount - cardInput.value;
				alert('Cheque Amount should not exceed ' + max);
				
				chequeInput.value = 0.0;
				
				newValue = new Number(cashInput.initialAmount - cardInput.value - chequeInput.value).toFixed(2);
				cashInput.value = newValue;
			}
		}
		catch(e)
		{
			toConsole(e);
		}
	}
	
	return false;
}

function showCheque()
{
	show("chequeNoLabel");show("chequeNoTxt");
		
	hide("cashLabel");hide("cashTxt");
	hide("cardLabel");hide("cardTxt");
	hide("cardNoLabel");hide("cardNoTxt");
	hide("chequeLabel");hide("chequeTxt");
	
	setTrxType("Cheque");
	
	var chequeTf = document.getElementsByName('chequeNo')[0];
	
	chequeTf.focus();
	
	return false;
}

function showCard()
{
	show("cardNoLabel");show("cardNoTxt");
	
	hide("cashLabel");hide("cashTxt");
	hide("cardLabel");hide("cardTxt");
	hide("chequeLabel");hide("chequeTxt");
	hide("chequeNoLabel");hide("chequeNoTxt");
	
	setTrxType("Card");
	
	var cardTf = document.getElementsByName('creditCardNumber')[0];
	
	cardTf.focus();
			
	return false;	
}

function showCash()
{
	hide('cashLabel');hide('cashTxt');
	hide('cardLabel');hide('cardTxt');
	hide('cardNoLabel');hide('cardNoTxt');
	hide('chequeLabel');hide('chequeTxt');
	hide('chequeNoLabel');hide('chequeNoTxt');
	
	setTrxType("Cash");
	
	return false;	
}

function setTrxType(type)
{
	document.forms[0].trxType.value = type;
}



//adding behaviour to discount&actual price textfields
var price = document.getElementsByName('price');
var discount = document.getElementsByName('discountPercent');
var actualPrice = document.getElementsByName('actualPrice');

/*
var totalDiscount = $('totalDiscount');
var totalPrice = $('totalPrice');

totalPrice.initialAmount = 0;
for(var i=0;i<actualPrice.length;i++)
{
	totalPrice.initialAmount += parseFloat(actualPrice[i].value);
}

totalPrice.onkeyup = function()
{
	var newDiscount = 100-((this.value*100)/totalPrice.initialAmount);
	newDiscount = new Number(newDiscount).toFixed(2); //rounding calculate discount
	totalDiscount.value = newDiscount;
	
	if(newDiscount > discountAllowed)
	{
		var errormsg = 'You are authorised to give ' + discountAllowed + '% discount only!';
					
		this.className = 'text medium error';
		this.title = errormsg;
		
		totalDiscount.className = 'text medium error';
		totalDiscount.title = errormsg;
		
		//alert(errormsg);	
	}
	else
	{
		var className = 'text medium';
		
		
		this.className = className;
		this.title = '';
		
		totalDiscount.className = className;
		totalDiscount.title = '';
	}
};

totalDiscount.onkeyup = function()
{
	var newprice = ((100-this.value)*totalPrice.initialAmount)/100;
	newprice = new Number(newprice).toFixed(2); //rounding calculate price
	totalPrice.value = newprice;
	
	if(this.value > discountAllowed)
	{
		var errormsg = 'You are authorised to give ' + discountAllowed + '% discount only!';			
		
		this.className = 'text medium error';
		this.title = errormsg;
		
		totalPrice.className = 'text medium error';
		totalPrice.title = errormsg;
		
		//alert(errormsg);	
	}
	else
	{
		var className = 'text medium';
		
		this.className = className;
		this.title = '';
		
		totalPrice.className = className;
		totalPrice.title = '';
	}
};
*/ 

for(var i=0;i<price.length;i++)	
{
	var element = null;
	
	element = discount[i];
	element.position = i; //adding attribute position
	element.onkeyup = function(e)
	{
		var index = this.position;		
		
		var calculatedPrice = ((100-this.value)*price[index].value)/100;
		calculatedPrice = new Number(calculatedPrice).toFixed(2); //rounding calaculate value
		
		actualPrice[index].value = calculatedPrice;
		updateTotal();
		
		if(this.value > discountAllowed)
		{
			var errormsg = 'You are authorised to give ' + discountAllowed + '% discount only!';			
			
			this.className = 'text medium error';
			this.title = errormsg;
			
			actualPrice[index].className = 'text medium error';
			actualPrice[index].title = errormsg;
			
			//alert(errormsg);	
		}
		 else if(this.value < 0)
		{
			var errormsg = 'Discount cannot be negative!';			
			
			this.className = 'text medium error';
			this.title = errormsg;
			
			actualPrice[index].className = 'text medium error';
			actualPrice[index].title = errormsg;
			
			//alert(errormsg);	
		}
		else
		{
			var className = 'text medium';
			
			this.className = className;
			this.title = '';
			
			actualPrice[index].className = className;
			actualPrice[index].title = '';
		}
					
	}
	
	element = actualPrice[i];
	element.position = i; //adding attribute position
	
	element.onkeypress = setNumericInputMask;
	element.onkeyup = function(e)
	{	
		var index = this.position;
		
		var calculateDiscount = 100-((this.value*100)/price[index].value);
		calculateDiscount = new Number(calculateDiscount).toFixed(2); //rounding calculate discount
				
		discount[index].value = calculateDiscount;
		updateTotal();
		
		if(calculateDiscount > discountAllowed)
		{
			var errormsg = 'You are authorised to give ' + discountAllowed + '% discount only!';
						
			this.className = 'text medium error';
			this.title = errormsg;
			
			discount[index].className = 'text medium error';
			discount[index].title = errormsg;
			
			//alert(errormsg);	
		}
		else if(calculateDiscount < 0)
		{
			var errormsg = 'Discount cannot be negative!';
						
			this.className = 'text medium error';
			this.title = errormsg;
			
			discount[index].className = 'text medium error';
			discount[index].title = errormsg;
			
			//alert(errormsg);
		}
		else
		{
			var className = 'text medium';
			
			
			this.className = className;
			this.title = '';
			
			discount[index].className = className;
			discount[index].title = '';
		}
	}
	
}

function updateTotal()
{
	init();
	
	var cardInput = document.getElementsByName('paymentByCard')[0].value="";
	var chequeInput = document.getElementsByName('paymentByChq')[0].value="";
}

function init()
{
	var total = 0;	
	
	for(var i=0;i<actualPrice.length;i++)
	{
		total = total + parseFloat(actualPrice[i].value);
	}
	
	total = new Number(total).toFixed(2);	
	
	//totalPrice.value = total;
	//totalDiscount.value = 0;
	
	$('actualPriceTotal').innerHTML = total;
	document.getElementsByName('totalActualPrice')[0].value = total;
	
	var el = document.getElementsByName('paymentByCash')[0];
	if(el)
	{
		el.value = total;
		el.initialAmount = total;
	}
	
	discountTotal = $('discountTotal');
	grandTotal = $('grandTotal');
	priceTotal = total;
	
	discount.value = 0;
	discountTotal.onkeyup = function(){
		grandTotal.value = ((100-this.value)*priceTotal)/100;
		grandTotal.value = new Number(grandTotal.value).toFixed(2);
		$('actualPriceTotal').innerHTML = grandTotal.value;
		el.value = grandTotal.value;
		
		for(var i=0;i<actualPrice.length;i++)
		{
			actualPrice[i].value = ((100-this.value)*price[i].value)/100;
			actualPrice[i].value = new Number(actualPrice[i].value).toFixed(2);				
			discount[i].value = this.value;	
		}
		
		validateForm();
	};
	
	grandTotal.value = priceTotal;
	grandTotal.onkeyup = function(){
		discountTotal.value = 100-((this.value*100)/priceTotal);
		discountTotal.value = new Number(discountTotal.value).toFixed(2);
		$('actualPriceTotal').innerHTML = this.value;
		el.value = this.value;
		
		for(var i=0;i<actualPrice.length;i++)
		{
			discount[i].value = discountTotal.value;						
			actualPrice[i].value = ((100-discount[i].value)*price[i].value)/100;
			actualPrice[i].value = new Number(actualPrice[i].value).toFixed(2);
		}
		
		validateForm();
	};
	
	validateForm();
}

function validateDiscount(e)
{
	var evtobj = e || window.event;
	var unicode=evtobj.charCode? evtobj.charCode : evtobj.keyCode;
	var key = String.fromCharCode(unicode);
	
	var regex = /\d\x00\x08\x0D/;
	
	//return regex.test(key);	
	return true;
}

/**
* call on page load
*/
function validateForm()
{
	var errormsg = 'You are authorised to give ' + discountAllowed + '% discount only!';
	var errormsg2 = 'Discount cannot be negative!';
	
	for(var i=0;i<actualPrice.length;i++)
	{
		if(discount[i].value > discountAllowed)
		{						
			actualPrice[i].className = 'error medium';
			actualPrice[i].title = errormsg;
			
			discount[i].className = 'error medium';
			discount[i].title = errormsg;
		}
		else if(discount[i].value < 0)
		{						
			actualPrice[i].className = 'error medium';
			actualPrice[i].title = errormsg2;
			
			discount[i].className = 'error medium';
			discount[i].title = errormsg2;
		}
		else
		{
			actualPrice[i].className = 'text medium';
			actualPrice[i].title = "";
						
			discount[i].className = 'text medium';
			discount[i].title = "";
		}
	}
	
	if(discountTotal.value > discountAllowed)
	{						
		grandTotal.className = 'error medium';
		grandTotal.title = errormsg;
		
		discountTotal.className = 'error medium';
		discountTotal.title = errormsg;
	}
	else if(discountTotal.value < 0)
	{						
		grandTotal.className = 'error medium';
		grandTotal.title = errormsg2;
		
		discountTotal.className = 'error medium';
		discountTotal.title = errormsg2;
	}
	else
	{
		grandTotal.className = 'text medium';
		grandTotal.title = "";
					
		discountTotal.className = 'text medium';
		discountTotal.title = "";
	}
	
		
}


var discountTotal = null;
var grandTotal = null;
var priceTotal = null;

var discountAllowed = <bean:write name="<%= Constants.DISCOUNT_ALLOWED %>"/>;
var errorCount = 0;





