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
	@author tamak
 */


window.status="";
function refreshList(varDoc)
{
    window.location="NatisReleaseAction.do?action=prepareRelease&tradeInDetailMake=" + varDoc.tradeInDetailMake.value + "&assetID=" + varDoc.assetID.value + "&financeOptionId=" + varDoc.financeOptionId.value + "&licensingOptionId=" + varDoc.licensingOptionId.value + "&userSalesRepId=" + varDoc.userSalesRepId.value;
}

function cancelRelease()
{
	window.location="NatisReleaseAction.do?action=home";
}

function updateAttributeList(form,id)
{
	form.updateAttrValueId.value = id;
	form.action.value = "updateProductAttributeValue";
	form.submit();
}

function setActionMethod(form,action)
{
	toConsole("setting action dynamically");
	toConsole("Form :" + form);
	toConsole("action :" + action);
	
	try
	{
		document.getElementsByName('action')[0].value = action;	
		form.submit();
	}
	catch(e)
	{
		toConsole("Unable to set Action " + e);
	}
			
}


/*function setAction(form,action,method)
{
	form.action = action;
	form.action.value = method;
	//form.bpartnerId.value=100;
	//alert("Calling action: " + action + "/n Method="+method);
	form.submit();
	
}
*/
function setAction(form,action,method)
{
	var elements = form.elements;

	var getStr = action + "?action=" + method;

	for(i=0;i<elements.length;i++)
	{
		switch(elements[i].type)
		{
			case "text" : 
				getStr = getStr + "&" + elements[i].name + "=" + escape(elements[i].value);
				break;

			case "textarea" :  
				getStr = getStr + "&" + elements[i].name + "=" + escape(elements[i].value);				
				break;

			case "hidden" :  
				getStr = getStr + "&" + elements[i].name + "=" + escape(elements[i].value);				
				break;

			case "password" :  
				getStr = getStr + "&" + elements[i].name + "=" + escape(elements[i].value);				
				break;
			
			case "radio" :
				if(elements[i].checked)
				{
					getStr = getStr + "&" + elements[i].name + "=" + escape(elements[i].value);			
				} 
				break;
			
			case "checkbox" :
				if(elements[i].checked)
				{
					getStr = getStr + "&" + elements[i].name + "=" + escape(elements[i].value);			
				} 
				break;
	
			case "select" : 
				var options = elements[i].options;
				for(i=0;elements[i].length;i++)
				{
					if(options[i].selected)
					{
						getStr = getStr + "&" + options[i].name + "=" + escape(elements[i].value);
					}
				}
				break;
			
		}
	}

	//alert(getStr);
	window.location = getStr;
} 


function selectAll(form)
{	
	for(var i=0;i<form.checkBox.length;i++)
	{
		form.checkBox[i].checked = true;
	}	
}

function selectAllProductIds(form)
{	
	for(var i=0;i<form.productIds.length;i++)
	{
		form.productIds[i].checked = true;
	}	
}

function disableButtons(form)
{
    for(var i=0;i<form.button.length;i++)
	{
		form.button[i].disabled = true;
	}
}

function setActionWithNoDupRequest(form,action)
{
    form.action.value = action;	
	form.submit();	
	disableButtons(form);	
}


function clearAll(form)
{
	for(var i=0;i<form.checkBox.length;i++)
	{
		form.checkBox[i].checked = false;
	}	
}

function confirmDelete(link)
{
	return confirm("Are you sure you want to delete this record?");		
}

function getPermission(form)
{
	return confirm("Are you sure you want to cancel this transaction?");

}

function getAcceptPermission(form)
{
	return confirm("Are you sure you want to proceed with this transaction?");
}

function getSwapPermission(form)
{
	return confirm("Are you sure you want to put this transaction for swap?");
}


function getCancelSwapPreferencePermission(form)
{
	return confirm("Are you sure you want to cancel your swap preference?");
}

function getCancelSwapPermission(form)
{
	return confirm("Are you sure you want to cancel this swap request received from another dealer?");
}

function getSwapWithPermission()
{
	return confirm("Are you sure you want to swap this transaction?");
} 

function getCreditDealerStockWithPermission()
{
	return confirm("Are you sure you want to credit this vehicle?");
}

function getRejectPermission(form)
{
	return confirm("Are you sure you want to reject this transaction?");
}

function getOpenAllocation(form)
{
	return confirm("Are you sure you want to Open the Allocation? Opening the allocation will prepare the orders and reserve stock");
}





function fullScreen()
{	
	var screenX = screen.width;
	var screenY = screen.height;
	window.open('LoginSuccess.do','loginSuccess','left=0,top=0,scrollbars=yes,menubar=no,status=no,toolbar=no,resizable=no,width='+screenX+',height='+screenY);
}

function popUpCertificateWindow()
{	
	window.open('LoginSuccess.do','loginSuccess','scrollbars=yes,menubar=no,status=no,toolbar=no,resizable=no');
}

function pop(url,title)
{	
	var w = window.open(url,title,"toolbar=no,menubar=no,resizable=yes");	
}

function popReport(url,title)
{
	var w = window.open(url,title,"toolbar=no,menubar=no");		
}

function rollOn(img_name,img_src)
{
	var s = img_src.split(".");
	
	img_src = s[0] + "1." + s[1];
	
	//alert(img_src);
	
	document.getElementById(img_name).src = img_src ;
	
}

function rollOut(img_name,img_src)
{
	document.getElementById(img_name).src = img_src ;
}





//Disable right mouse click Script
//By Maximus (maximus@nsimail.com) w/ mods by DynamicDrive
//For full source code, visit http://www.dynamicdrive.com

/*

var message="Function Disabled!";

///////////////////////////////////
function clickIE4()
{
	if (event.button==2)
	{
		//alert(message);
		return false;
	}
}

function clickNS4(e)
{
	if (document.layers||document.getElementById&&!document.all)
	{
		if (e.which==2||e.which==3)
		{
			//alert(message);
			return false;
		}
	}
}

if (document.layers)
{
	document.captureEvents(Event.MOUSEDOWN);
	document.onmousedown=clickNS4;
}

else if (document.all&&!document.getElementById)
{
	document.onmousedown=clickIE4;
}

document.oncontextmenu=new Function("return false");
*/


function populateUserName(radio)
{
	var form =radio.form;	
	form.bpartnerId.value=radio.value;

}

function loadTextFieldValue(button)
{
	var form = button.form;
	
	var text = button.value;
	
	if(text == "Cash")
	{
		form.chequeNo.style.visibility = "hidden";
		form.creditCardNumber.style.visibility = "hidden";
		
		form.paymentByCash.style.visibility = "hidden";
		form.paymentByCard.style.visibility = "hidden";
		form.paymentByChq.style.visibility = "hidden";
	}
	else if(text == "Cheque")
	{
		form.chequeNo.style.visibility = "visible";
		form.creditCardNumber.style.visibility = "hidden";
		
		form.paymentByCash.style.visibility = "hidden";
		form.paymentByCard.style.visibility = "hidden";
		form.paymentByChq.style.visibility = "hidden";
	}
	else if(text=="Card")
	{
		form.chequeNo.style.visibility = "hidden";
		form.paymentByCash.style.visibility = "hidden";
		form.paymentByCard.style.visibility = "hidden";
		form.paymentByChq.style.visibility = "hidden";
		
		form.creditCardNumber.style.visibility = "visible";
	}
	
	else
	{
		form.chequeNo.style.visibility = "visible";
		form.creditCardNumber.style.visibility = "visible";
		
		form.paymentByCash.style.visibility = "visible";
		form.paymentByCard.style.visibility = "visible";
		form.paymentByChq.style.visibility = "visible";
	}
	
	form.trxType.value = button.value;	
	
}
function loadFieldValues(text)
{
  var form=text.form;
 
 form.amountRefunded.value=parseFloat(form.amountGiven.value) - parseFloat(form.grandTotal.value);
 
}

function loadFieldValues(text, totalAmount)
{
  var form=text.form;
 
 form.amountRefunded.value=parseFloat(form.amountGiven.value) - parseFloat(totalAmount);
 
}

function setPriceValue(txtDiscountBoxId,txtPriceBoxId,actualPriceBoxId)
{
	txtDiscount = document.getElementById(txtDiscountBoxId);
	txtPrice = document.getElementById(txtPriceBoxId);
	actualPrice =document.getElementById(actualPriceBoxId);
	
	actualPrice.value =txtPrice.value-(txtDiscount.value/100)*txtPrice.value;
}




function setDiscountValue(txtDiscountBoxId,txtPriceBoxId,actualPriceBoxId)
{
	txtDiscount = document.getElementById(txtDiscountBoxId);
	txtPrice = document.getElementById(txtPriceBoxId);
	actualPrice =document.getElementById(actualPriceBoxId);
	
	txtDiscount.value =100-(actualPrice.value/txtPrice.value)*100;
}
