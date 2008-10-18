<script>
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
//keyMap['ALT-D'] = "deleteSelected()";
//keyMap['ALT-C'] = "completeOrder()";

//showing the amt given textfield depending on the trxType

var trxType = '<bean:write name="<%=Constants.WEB_DOCUMENT_HEADER_BEAN%>" property="paymentType"/>';

var mixedCashAmount = 0;
<logic:present name="<%=Constants.PAYMENT_BY_CASH%>">
	mixedCashAmount = '<bean:write name="<%=Constants.PAYMENT_BY_CASH%>"/>';
</logic:present>

var amtGiven = $('amtGiven');

function initAmountGiven()
{
	amtGiven.onkeyup = function(e){
	
		try
		{
			var event = e||window.event;
		
			if(event.keyCode == 13)
			{
				completeOrder( $FElement( 'deleteBtn' ) );
			}
		}
		catch (e)
		{
			toConsole(e);
		}
	};
	
	amtGiven.style.display = "none";
	if(trxType == "Cash" || trxType == "Mixed")
		amtGiven.style.display = "inline";
}

//function declarations


function updateAmountToBeReturned(txt)
{
	var refundAmt = "";
	if(trxType == "Cash") 
		refundAmt = parseFloat(txt.form.amountGiven.value) - parseFloat(txt.form.grandTotal.value);
	else if(trxType == "Mixed")
		refundAmt = parseFloat(txt.form.amountGiven.value) - parseFloat(mixedCashAmount);
	
	
	if(isNaN(refundAmt))
		refundAmt = "";
	else
	{
		refundAmt = Math.round(refundAmt*100)/100;
		var index = refundAmt.toString().indexOf('.');
		if(index > 0 && ((refundAmt.toString().length - index) > 2))
			refundAmt = refundAmt.toString().substring(0, index + 3);
		
	}
	
	txt.form.amountRefunded.value = refundAmt;
}

function validateAmountGiven(btn, totalAmount)
{
	var form = btn.form;
	var valid = false;
	
	var flAmountGiven;
	var flTotalAmount = parseFloat(totalAmount);
	try
	{
		flAmountGiven = parseFloat(form.amountGiven.value);

		if(isNaN(flAmountGiven))
			alert("Amount given is invalid!!!");
		else if(flAmountGiven < flTotalAmount)
			alert("Amount given cannot be less than the cash Grand Total of the order");
		else
			valid = true;
	}
	catch(ex)
	{
		alert("Amount given is invalid!!!");
		$focus('amountGiven');
	}
		
	return valid;
}

function setDefaultAmount(btn, amount)
{
	var form = btn.form;
	
	var amountGiven = form.amountGiven.value;
	
	if(amountGiven == "")
	{
		form.amountGiven.value = amount;
		form.amountRefunded.value = "0.00";
	}
	
}



/*
function isAllOrderLinesSelected()
{
	var checkboxes = document.getElementsByName('posOrderLineIds');
	
	var count = 0;
	for( var i=0; i<checkboxes.length; i++)
	{
		if(checkboxes[i].checked == true)
		{ 
			++count; 
		}
	}
		
	toConsole('Found ' + checkboxes.length + ' checkboxes');
	toConsole('Found ' + count + ' selected');
	
	return (checkboxes.length == count);
}
*/

if(window.onload)
{
	var func = window.onload;
	window.onload = function(){
		func();
		enableDelete();
	};
}
else
{
	window.onload = function(){
		enableDelete()
	};
}

$focus('amountGiven');
initAmountGiven();
</script>
