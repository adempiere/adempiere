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

<!-createPartialPOSOrder.jsp->
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.Constants" %>


<%@ page import="org.posterita.struts.pos.POSOrderAction" %>
<%@ page import="org.posterita.beans.ProductBean" %>
<%@ page import="org.posterita.user.WebUserInfo" %>
<%@ page import="org.posterita.lib.UdiConstants" %>
<%@ page import="org.compiere.model.MOrder" %>


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

	  						   	
<html:form action="/PartialPOSOrderAction">
<html:hidden property="isSales" value="true"/>
<html:hidden property="action" value=""/>
<html:hidden property="ifAdd" value="true"/>

<html:hidden property="orderType" value="<%=Constants.PARTIAL_POS_ORDER%>"/>

<logic:present name="<%= Constants.CURRENT_PARTIAL_POS_ORDER_ID %>">
<bean:define id="orderId">
	<bean:write name="<%= Constants.CURRENT_PARTIAL_POS_ORDER_ID %>"/>
</bean:define>
<html:hidden property="orderId" value="<%= orderId %>"/>
</logic:present>


<table class="main">			
   	<tr>	
   		<td>   		
   			<table class="main">
   			
   			<tr>
		   		<td>
		   			<label><pos:message key="barcode"/>:</label>
		   		</td>		
		   		<td>
		   			<html:text property="barCode" accesskey="b" styleClass="text"/>
		   			<html:hidden property="productId" value=""/>
		   		</td>
		   		
		   		<td>
		   			<label><pos:message key="QtyEntered"/>:</label>
		   		</td>		
		   		<td>
		   			<html:text property="quantity" accesskey="q" styleClass="text"/>
		   		</td> 
		   		
		   		<td class="buttoncell"  align="right">
		   			<html:button property="btn" styleClass="addtocart bigbutton" onclick="addToCart()" accesskey="a">&nbsp</html:button>		   				   			
				</td>  		
		   			   				
			</tr>
   			<tr>
   				<td>
   					<label><pos:message key="search.product"/>:</label>
   				</td>
   				<td colspan="3">
   					<div>
					<%@ include file="/jsp/include/searchProductPanel.jsp" %>
					</div>
				</td>
				<td>&nbsp;</td>
   			</tr>   			
		</table>
   	</td>
   </tr>		
  
	<tr>
		<td>
			<div id="shoppingCart">
				<%@ include file="/jsp/pos/partialPOSOrderShoppingCart.jsp" %>
			</div>
		</td>
	</tr> 
	
	<tr>
		<td>
			<table class="main">
				<tr>
					<td valign="top" width="45%">						
						<%@ include file="/jsp/include/tenderPanel.jsp" %>										
					</td>
					<td  valign="top" width="45%">
						<%@ include file="/jsp/include/customerInfoPanel.jsp" %>	
					</td>
					<td valign="top">
						<table border="0" width="100%" cellspacing="0" style="padding-right: 0px; padding-left: 5px; padding-top: 5px; padding-bottom: 5px">
							<tr>
								<td class="buttoncell" align="right">
									<html:button property="btn" styleClass="checkout bigbutton" onclick="createOrder()" accesskey="c">&nbsp;</html:button>	
								</td>
							</tr>
							<tr>
								<td>
									&nbsp;
								</td>
							</tr>							
						</table>					
					</td>
				</tr>
			</table>
		</td>
	</tr>    		   
					
	<tr>
		<td>&nbsp;</td>
	</tr>					
	<tr>					  
		<td align="center">				
			<span>
			<span class="shortcutkey">
				<label><pos:message key="barcode"/>-</label>
				<label class="red">Alt-B</label>
			</span>	
			<span class="shortcutkey">
				<label><pos:message key="QtyEntered"/>-</label>
				<label class="red">Alt-Q</label>
			</span>
			<span class="shortcutkey">
				<label><pos:message key="add.to.cart"/>-</label>
				<label class="red">Alt-A</label>
			</span>
			<span class="shortcutkey">
				<label><pos:message key="checkout"/>-</label>
				<label class="red">Alt-C</label>
			</span>
			<span class="shortcutkey">
				<label><pos:message key="search.product"/>-</label>
				<label class="red">Alt-P</label>
			</span>
			<span class="shortcutkey">
				<label><pos:message key="search.customer"/>-</label>
				<label class="red">Alt-U</label>
			</span>
			<span class="shortcutkey">
				<label><pos:message key="advanced"/>-</label>
				<label class="red">Alt-O</label>
			</span>		
			<span class="shortcutkey">
				<label><pos:message key="cash"/>-</label>
				<label class="red">Alt-S</label>
			</span>	
			<span class="shortcutkey">
				<label><pos:message key="CreditCardType"/>-</label>
				<label class="red">Alt-R</label>
			</span>	
			<span class="shortcutkey">
				<label><pos:message key="cheque"/>-</label>
				<label class="red">Alt-K</label>
			</span>	
			<span class="shortcutkey">
				<label><pos:message key="new.customer"/>-</label>
				<label class="red">Alt-N</label>
			</span>		
			</span>	
		</td>		   		
	</tr>	
</table>
</html:form>


<script>		
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
	
	
	function checkout()
	{
		//setAction(document.forms[0],'GetPOSCustomersAction.do','getPOSCustomers');
		setAction(document.forms[0],'GetPOSPaymentDetailsAction.do','getPOSPaymentDetails');
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
	
	function setQuantity()
	{
		document.getElementsByName('quantity')[0].value=prompt("Enter Quantity");
		
	}
	
	function focusBarcode()
	{
		document.getElementsByName('barCode')[0].focus();
		toConsole("Setting focus to barcode");
	}
		
	
	function createOrder()
	{
		try
		{			
			var method = "createPOSOrder";
			var myForm = document.forms[0];
			var actionElement = document.getElementsByName('action')[0];
			
			actionElement.value = method;
			myForm.submit();
			
			disableButtons();			
		}
		catch(e)
		{
			toConsole(e);
		}
		
		//setAction(document.forms[0],'CreatePOSOrderAction.do','createPOSOrder');
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
	

	//focusBarcode();
	
	function scrollDownCart()
	{
		$('items').scrollTop = $('items').scrollHeight;
	}
	
	//incrementing, decrementing and deleting the shopping cart items using ajax
	// 1.Increment the qty for the product	
	function incrementCart(productId)
	{
		try
		{
			var url = 'PartialPOSOrderAction.do';
			var pars = 'action=incrementQty&productId='+productId+ '&ifAdd=true';
			
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
			var url = 'PartialPOSOrderAction.do';
			var pars = 'action=decrementQty&productId='+productId+ '&ifAdd=false';
			
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
			var url = 'PartialPOSOrderAction.do';
			var pars = 'action=deleteFromPOSCart&productId='+productId;
			
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
		toConsole('Updating shopping cart');
		
		//var top = $('items').scrollTop;
		$('shoppingCart').innerHTML = request.responseText;
		//$('items').scrollTop = top;
	}
	
	//Reports an error
	function reportShoppingCartError(request)
	{
		alert("<pos:message textOnly='true' key='ajax.error'/>");
	}
	
		
	try
	{
		//addRequiredLibrary('js/shoppingCart.js');
		addRequiredLibrary('js/customer.js');
	}
	catch(e)
	{
		alert(e);
	}
	
	//scrollDownCart();	
	
</script>   
 
<%@ include file="/jsp/include/posFooter.jsp" %>      
   
