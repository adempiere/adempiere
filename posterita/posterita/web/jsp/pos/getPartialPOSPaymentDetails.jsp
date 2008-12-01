<%--
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
 *
 * @author Praveen
--%>


<!--getPartialPOSPaymentDetails.jsp-->
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.Constants" %>

<%@ page import="org.posterita.struts.pos.POSOrderAction" %>
<%@ page import="org.posterita.beans.ProductBean" %>
<%@ page import="org.posterita.user.WebUserInfo" %>
<%@ page import="org.posterita.lib.UdiConstants" %>
<%@ page import="org.posterita.form.OrderLineForm" %>
<%@ page import="org.compiere.model.MOrder" %>
<%@ page import="java.math.BigDecimal" %>


<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/dcs.tld" prefix="dcs" %>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

<bean:define id="title"><pos:message textOnly="true" key="smenu.prepare.order"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>  
<%@ include file="/jsp/include/errors.jsp" %> 
	  						   
<html:form action="/SetPartialPOSOrderPaymentDetailsAction">
<html:hidden property="action" value="setPOSPaymentDetails"/>

<table class="content">
				
<logic:present name="<%=Constants.PARTIAL_ORDER_SHOPPING_CART_ITEMS%>">
	<logic:notEmpty name="<%=Constants.PARTIAL_ORDER_SHOPPING_CART_ITEMS%>">
	<table class="cart">
	<tr>									
		<th align="left">
			<pos:message key="Description"/>
		</th>
		
		<th align="right">
			<pos:message key="Qty"/>
		</th>
		
		<th align="right">
			<pos:message key="Price"/>
		</th>
		
		<th align="right">
			<pos:message key="vat"/>
		</th>
		
		<th align="right">
			<pos:message key="total.price"/>
		</th>
		
		<th align="right">
			<pos:message key="Discount"/>
		</th>
	
	    <th align="right">
			<pos:message key="actual.price"/>
		</th>
	</tr>
	
	<logic:iterate indexId="count" id="element" name="<%=Constants.PARTIAL_ORDER_SHOPPING_CART_ITEMS%>" type="org.posterita.beans.ItemBean">
	<%
		String styleClass = "label";
		if ((count.intValue()%2) != 0)
			styleClass = "contentname";
	%>
	<tr>		
		<td class="<%=styleClass%>" align="left">
			<bean:write name="element" property="description"/>
		</td>
	
		<td class="<%=styleClass%>" align="right">
			<bean:write name="element" property="qty"/>
		</td>	
		
		<td class="<%=styleClass%>" align="right">
			<bean:define id="standardPrice" name="element" property="standardPrice"/>
			<fmt:formatNumber value='${standardPrice}'type="currency" currencySymbol=""/>
			<bean:define id="priceTotal" name="element" property="priceTotal"/>
		</td>
		
		<td class="<%=styleClass%>" align="right">
			<bean:define id="taxAmt" name="element" property="taxAmt"/>
			<fmt:formatNumber value='${taxAmt}'type="currency" currencySymbol=""/>
			<bean:define id="taxTotal" name="element" property="taxTotal"/>
		</td>	
		
		<td class="<%=styleClass%>" align="right">
			<bean:define id="price" name="element" property="price"/>
			<fmt:formatNumber value='${price}'type="currency" currencySymbol=""/>
			<html:hidden property="price" value="<%= element.getPrice() + ""%>"/>					
			<bean:define id="grandTotal" name="element" property="grandTotal"/>
		</td>
		
		<%
			String defaultDiscount = "0";
			String defaultPrice = element.getPrice().toString();
			
			OrderLineForm form = (OrderLineForm)request.getAttribute("OrderLineForm");
			
			if(form!=null)
			{
				String[] discountPercentage = form.getDiscountPercent();
				String[] actualPrice = form.getActualPrice();		
				
				if(discountPercentage!= null)
				if(discountPercentage.length!=0) 
					defaultDiscount = discountPercentage[count.intValue()];
					
				if(actualPrice!=null)
				if(actualPrice.length!=0)
					defaultPrice = actualPrice[count.intValue()];
			}
		%>	
	
	   	<td class="<%=styleClass%>" align="right">
	    	<html:text property="discountPercent" styleClass="text medium" value="<%=defaultDiscount%>"/>				
		</td>	
		
		<td class="<%=styleClass%>" align="right">
			<html:text property="actualPrice" styleClass="text medium" value="<%=defaultPrice%>"/>							
		</td>				
	</tr>
	
	</logic:iterate>
	 <tr>
	 	<bean:define id="currSymbole" name="<%= Constants.CURRENCY_SYMBOLE %>"/>
		<td class="total" colspan="2"><pos:message key="GrandTotal"/></td>
		<td nowrap class="total" align="right">  		  
			<fmt:formatNumber value='${priceTotal}'type="currency" currencySymbol='${currSymbole}'/>
		</td>
		<td nowrap class="total" align="right">  		  
			<fmt:formatNumber value='${taxTotal}'type="currency" currencySymbol='${currSymbole}'/>
		</td>
		<td nowrap class="total" align="right">  		  
			<fmt:formatNumber value='${grandTotal}'type="currency" currencySymbol='${currSymbole}'/>
		</td>
		<td class="total">&nbsp;</td>
		<td class="total" align="right">
			<html:hidden property="totalActualPrice"/>
			<div id="actualPriceTotal">
				<bean:write name="grandTotal"/>
			</div>
		</td>
	</tr>
	<tr>
		<td class="total" colspan="5">&nbsp;</td>
		<td class="total" align="right"><input id="discountTotal" type="text" class="text medium"></td>
		<td class="total" align="right"><input id="grandTotal" type="text" class="text medium"></td>
	</tr>
	</table>
	 
	</logic:notEmpty>
</logic:present>
		
	<tr>
		<td>&nbsp;</td>
	</tr>
		
	<tr>	
		<td>
			<table align="center" border="0" cellpadding="5" cellspacing="0">
				<tr>
					<td>
						<html:button styleClass="cash smallbutton" property="<%=MOrder.PAYMENTRULE_Cash%>" onclick="showCash();" accesskey="s">
					   		&nbsp;
					   	</html:button>		   	 	  	
					</td>
				  
					<td>
				   		<html:button styleClass="card smallbutton" property="<%=MOrder.PAYMENTRULE_CreditCard%>" onclick="showCard();" accesskey="r">
				   			&nbsp;
				   		</html:button>		   		
					</td>
					
					<td>
						<html:button styleClass="cheque smallbutton" property="<%=MOrder.PAYMENTRULE_Check%>" onclick="showCheque();" accesskey="q">
				   			&nbsp;
				   		</html:button>   		 
					 </td>		   		 	
				
					 <td>
						<html:button styleClass="mixed smallbutton" property="<%=UdiConstants.PAYMENTRULE_MIXED%>" onclick="showMixed();" accesskey="m">
							&nbsp;
						</html:button>				   		 
				 	</td>		   		 	
				</tr>	
			</table>
		</td>
	</tr>

	<tr>
		<td>&nbsp;</td>
	</tr>	
	
	<tr>
	<td>
		<table border="0" cellspacing="0" cellpadding="5">
			<tr>	
				<td> 
				 	<label><pos:message key="TenderType"/></label>
				</td>
				<td colspan="3">
					<html:text property="trxType" readonly="true" styleClass="text"/>
				 	<html:hidden property="bpartnerId"/>
				</td>
			</tr>
		</table>
		<table border="0" cellspacing="0" cellpadding="5">
			<tr>
				<td>
					<div id="cashLabel" class="nodisplay"><label><pos:message key="cash.amount"/>:</label></div>
				</td>
				<td colspan="3">
					<div id="cashTxt" class="nodisplay"><html:text property="paymentByCash" readonly="true" styleClass="text"/></div>
				</td>
			</tr>
			<tr>
				<td>
					<div id="cardLabel" class="nodisplay"><label><pos:message key="card.amount"/>:</label></div>
				</td>
				<td>
					<div id="cardTxt" class="nodisplay"><html:text property="paymentByCard" styleClass="text"/></div>
				</td>
				<td>
					<div id="cardNoLabel" class="nodisplay"><label><pos:message key="card.no"/>:</label></div>
				</td>
				<td>
					<div id="cardNoTxt" class="nodisplay"><html:text property="creditCardNumber" styleClass="text" /></div>
				</td>
			</tr>
			<tr>
				<td>
					<div id="chequeLabel" class="nodisplay"><label><pos:message key="cheque.amount"/>:</label></div>
				</td>
				<td>
					<div id="chequeTxt" class="nodisplay"><html:text property="paymentByChq" styleClass="text" /></div>
				</td>
				<td>
					<div id="chequeNoLabel" class="nodisplay"><label><pos:message key="cheque.no"/>:</label></div>
				</td>
				<td>
					<div id="chequeNoTxt" class="nodisplay"><html:text property="chequeNo" styleClass="text"/></div>
				</td>
			</tr>
		</table>
		</td>
	</tr>
	
	<tr><td>&nbsp;</td></tr>     		
      		
	<tr> 			
		<td align="right">
			<html:submit styleClass="continue smallbutton" accesskey="c" property="continue">
				&nbsp;
			</html:submit>		   	 	  	
		</td>			
    </tr>
    <tr>
		<td>&nbsp;</td>
	</tr>					
	<tr>					  
		<td align="center">
			<table border="0" cellpadding="10">
				<tr>
					<td>
						<label><pos:message key="cash"/> -</label><label class="red">Alt-S</label>
					</td>
					<td>
						<label><pos:message key="card"/> -</label><label class="red">Alt-R</label>
					</td>
					<td>
						<label><pos:message key="cheque"/> -</label><label class="red">Alt-Q</label>
					</td>
					<td>
						<label><pos:message key="mixed"/> -</label><label class="red">Alt-M</label>
					</td>
					<td>
						<label><pos:message key="continue"/> -</label><label class="red">Alt-C</label>
					</td>				
				</tr>
			</table> 		   	 	  	
		</td>		   		
	</tr>		 
</html:form>
</table>

<script>
//registering shortcut keys
//keyMap['ALT-1'] = "showCash()";
//keyMap['ALT-2'] = "showCard()";
//keyMap['ALT-3'] = "showCheque()";
//keyMap['ALT-4'] = "showMixed()";
//keyMap['ALT-S'] = "createOrder()";
//if($F(trxType))

window.onload = function(){

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
	cardInput.onkeyup = function()
	{
		try
		{
			cashInput.value = new Number(cashInput.initialAmount - cardInput.value - chequeInput.value).toFixed(2);
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
			cashInput.value = new Number(cashInput.initialAmount - chequeInput.value - cardInput.value).toFixed(2);
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

function createOrder()
{
	setAction(document.forms[0],'CreatePOSOrderAction.do','createPOSOrder')
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
		var errormsg = 'You are authorised to give ' + discountAllowed + '% discount only!';]
		
					
		this.className = 'text medium error';
		this.title = errormsg;
		
		totalDiscount.className = 'text medium error';
		totalDiscount.title = errormsg;
		
		//alert(errormsg)
		onError(errormsg);	
	}
	else
	{
		var className = 'text medium';
		
		
		this.className = className;
		this.title = '';
		
		totalDiscount.className = className;
		totalDiscount.title = '';
		continueBtn.disabled = false;
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
		onError(errormsg);	
	}
	else
	{
		var className = 'text medium';
		
		this.className = className;
		this.title = '';
		
		totalPrice.className = className;
		totalPrice.title = '';
		continueBtn.disabled = false;
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
			onError(errormsg);	
		}
		 else if(this.value < 0)
		{
			var errormsg = 'Discount cannot be negative!';			
			
			this.className = 'text medium error';
			this.title = errormsg;
			
			actualPrice[index].className = 'text medium error';
			actualPrice[index].title = errormsg;
			
			//alert(errormsg);
			onError(errormsg);	
		}
		else
		{
			var className = 'text medium';
			
			this.className = className;
			this.title = '';
			
			actualPrice[index].className = className;
			actualPrice[index].title = '';
			continueBtn.disabled = false;
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
			//document.getElementsByName('continue').disabled;
			
			this.className = 'text medium error';
			this.title = errormsg;
			
			discount[index].className = 'text medium error';
			discount[index].title = errormsg;
			
			//alert(errormsg);
			onError(errormsg);	
		}
		else if(calculateDiscount < 0)
		{
			var errormsg = 'Discount cannot be negative!';
						
			this.className = 'text medium error';
			this.title = errormsg;
			
			discount[index].className = 'text medium error';
			discount[index].title = errormsg;
			
			//alert(errormsg);
			onError(errormsg);
		}
		else
		{
			var className = 'text medium';
			
			
			this.className = className;
			this.title = '';
			
			discount[index].className = className;
			discount[index].title = '';
			
			continueBtn.disabled = false;
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
			continueBtn.disabled = true;
		}
		else if(discount[i].value < 0)
		{						
			actualPrice[i].className = 'error medium';
			actualPrice[i].title = errormsg2;
			
			discount[i].className = 'error medium';
			discount[i].title = errormsg2;
			continueBtn.disabled = true;
		}
		else
		{
			actualPrice[i].className = 'text medium';
			actualPrice[i].title = "";
						
			discount[i].className = 'text medium';
			discount[i].title = "";
			continueBtn.disabled = false;
		}
	}
	
	if(discountTotal.value > discountAllowed)
	{						
		grandTotal.className = 'error medium';
		grandTotal.title = errormsg;
		
		discountTotal.className = 'error medium';
		discountTotal.title = errormsg;
		continueBtn.disabled = true;
	}
	else if(discountTotal.value < 0)
	{						
		grandTotal.className = 'error medium';
		grandTotal.title = errormsg2;
		
		discountTotal.className = 'error medium';
		discountTotal.title = errormsg2;
		continueBtn.disabled = true;
	}
	else
	{
		grandTotal.className = 'text medium';
		grandTotal.title = "";
					
		discountTotal.className = 'text medium';
		discountTotal.title = "";
		continueBtn.disabled = false;
	}
	
		
}

function onError(msg)
{
	//toConsole('Error occured ' + msg);	
	continueBtn.disabled = true;
}

var discountTotal = null;
var grandTotal = null;
var priceTotal = null;
var continueBtn = document.getElementsByName('continue')[0];

var discountAllowed = <bean:write name="<%= Constants.DISCOUNT_ALLOWED %>"/>;
var errorCount = 0;
</script>
      		       		
<%@ include file="/jsp/include/posFooter.jsp" %>	
	
