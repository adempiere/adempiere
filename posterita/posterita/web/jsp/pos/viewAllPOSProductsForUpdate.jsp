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
 * @author Alok
--%>



<!-- viewAllPOSProductsForUpdate.jsp -->
<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.beans.*" %>
<%@ page import="org.posterita.struts.pos.EditBulkProductFromFileAction" %>
<%@ page import="org.compiere.process.DocumentEngine" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page import="org.compiere.model.MOrder" %>
<%@ page import="org.posterita.user.WebUserInfo" %>
<%@ page import="org.posterita.struts.pos.POSOrderAction" %>
<%@ page import="java.util.ArrayList" %> 

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/dcs.tld" prefix="dcs" %>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos" %>



<logic:notPresent name="<%=WebUserInfo.NAME%>" scope="session">
	<jsp:forward page="/LoginHome.do"/>
</logic:notPresent>

<bean:define id="title"><pos:message textOnly="true" key="Product"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %> 

<table class="content">

	<html:form action="/ViewAllPOSProductsForUpdateAction">
	<html:hidden property="action" value="<%=EditBulkProductFromFileAction.VIEW_ALL_POS_PRODUCTS_FOR_UPDATE%>"/>
	<tr>
	    <td>
			<html:text property="productName" styleClass="text" />
	        <html:submit styleClass="search smallbutton">
	        	&nbsp;
	        </html:submit>
		</td>
	</tr>   	
	</html:form>
</table>
<div align="right">
	<logic:present name="<%= Constants.PRODUCT_CART %>">		
		<span id="productCount">
			<pos:message key="cart.has" textOnly="true"/><strong>
			<bean:write name="<%= Constants.PRODUCT_CART %>" property="noOfProducts"/>
			</strong><pos:message key="cart.items"/>
		</span>		
	</logic:present>
	<logic:notPresent name="<%= Constants.PRODUCT_CART %>">
		<span id="productCount">
			<pos:message key="cart.empty"/>
		</span>
	</logic:notPresent>
	<span id="cartLinkPanel">
		<html:link action="/ViewAllPOSProductsForUpdateAction.do?action=viewCart"><pos:message key="view"/></html:link>			
		<a href="javascript:void(0);" id="clearCart"><pos:message key="clear"/></a>
	</span>
</div>
<logic:empty name="<%=Constants.POS_PRODUCTS_FOR_UPDATE%>">
	<div><pos:message key="search.product.notfound"/><b><bean:write name="ViewProductForm" property="productName"/></b></div>
	<div class="space"></div>
</logic:empty>
	
	<logic:present name="<%=Constants.POS_PRODUCTS_FOR_UPDATE%>">
	<logic:notEmpty name="<%=Constants.POS_PRODUCTS_FOR_UPDATE%>">
	<div class="scrollpane">	
	<table class="display" border="1" width="900px">
		<tr>
			<th><pos:message key="Name"/></th>
	   	    <th nowrap="nowrap"><pos:message key="purchase.price"/></th>  
	   	    <th nowrap="nowrap"><pos:message key="sales.price"/></th>   
	   	    <th nowrap="nowrap">&nbsp;</th>
	 	</tr>		
		<logic:iterate indexId="count" id="element" name="<%=Constants.POS_PRODUCTS_FOR_UPDATE%>" type="org.posterita.beans.ProductBean">
		<%
		String styleClass = "label";
		if ((count.intValue()%2) != 0)
		styleClass = "contentname";
	   	%>
		<tr>
			<td class=<%=styleClass%> width="400px">
				<bean:write name="element" property="productName"/>
			</td>
	
			<td  align="right" class=<%=styleClass%>>
				<bean:define id="purchasePriceStandard" name="element" property="purchasePriceStandard"/>
				<fmt:formatNumber value='${purchasePriceStandard}' type="currency" currencySymbol=" "/>
			</td>
			<td  align="right" class=<%=styleClass%>>
				<bean:define id="salesPriceStandard" name="element" property="salesPriceStandard"/>
				<fmt:formatNumber value='${salesPriceStandard}' type="currency" currencySymbol=" "/>
			</td>
			<td class=<%=styleClass%>>			
			   	<img src="images/pos/buttons/button_cart.jpeg" id="<%= element.getProductId() %>" name="cart">		   	
			   	<span name="qtyCount">&nbsp;</span>
			</td>				
		</tr>		
		</logic:iterate>
	</table>	
	</div>
	</logic:notEmpty>
	</logic:present> 
<%--
<html:form action="/CreateCSVFileAction">
	<html:hidden property="action" value="<%=EditBulkProductFromFileAction.CREATE_CSV_FILE%>"/>
	<html:submit styleClass="blank smallbutton">
		Create CSV
	</html:submit>		
</html:form>  		 
--%>	
	
			
<script>
var btns = document.getElementsByName('cart');
var productCount = 0;

<logic:present name="<%= Constants.PRODUCT_CART %>">
productCount = <bean:write name="<%= Constants.PRODUCT_CART %>" property="noOfProducts"/>;
</logic:present>

if(productCount == 0)
{
	Element.hide('cartLinkPanel');
}

for(var i=0; i<btns.length; i++)
{
	try
	{
		var btn = btns[i];
	
		btn.style.cursor = 'pointer';
		btn.onclick = function(e)
		{		
			addToCart(this.id);		
		};	
	}
	catch(e)
	{
		toConsole(e);
	}
}
/*

var buttons = $A(btns);

buttons.each(
	function( button ){
		button.style.cursor = 'pointer';
		button.onclick = function(e)
		{		
			addToCart(this.id);		
		};
	}
);

*/

$('clearCart').onclick = clearCart;

function clearCart()
{
	var url = "EditBulkProductFromFileAction.do";
	var pars = "action=clearCart";
	
	var myAjax = new Ajax.Request( url, 
	{ 
		method: 'get', 
		parameters: pars, 
		onSuccess: evaluateResponse, 
		onFailure: reportError
	});
}

function addToCart(productId)
{
	var url = "EditBulkProductFromFileAction.do";
	var pars = "action=addToCart&productId=" + productId;
	
	var myAjax = new Ajax.Request( url, 
	{ 
		method: 'get', 
		parameters: pars, 
		onSuccess: evaluateResponse, 
		onFailure: reportError
	});	
	
}

function evaluateResponse(request)
{
	try
	{
		var script = request.responseText;
		eval(script);
	}
	catch(e)	
	{
		toConsole(e);
	}
}

function setCartCounter(productCount)
{
	$('productCount').innerHTML = 'Cart has <strong>' + productCount + '</strong> items';
	Element.show('cartLinkPanel');
}

function productAdded(productId)
{
	try
	{
		var btn = document.getElementById(productId);		
		var count = btn.parentNode.getElementsByTagName('span')[0];
		if(count != null)
		{			
			count.innerHTML = 'Y';			
		}
	}
	catch(e)
	{
		toConsole(e);
	}
}


function clearAll()
{
	var qtyCounters = document.getElementsByName('qtyCount');
	
	for(var i = 0; i < qtyCounters.length; i++)
	{
		qtyCounters[i].innerHTML = "";
	}
	
	Element.hide('cartLinkPanel');
	$('productCount').innerHTML = "Cart is empty!";
	productCount = 0;
}

function reportError(request)
{
	alert('Oops! Some errors occured.');
}
</script>
<%@ include file="/jsp/include/posFooter.jsp" %>
