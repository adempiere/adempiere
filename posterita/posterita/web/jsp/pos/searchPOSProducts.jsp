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



<!-- searchPOSProducts.jsp -->
<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.beans.*" %>
<%@ page import="org.posterita.struts.pos.POSProductAction" %>
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

<bean:define id="title"><pos:message key="all.products" textOnly="true"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %> 

<div>
	<html:form action="/SearchPOSProductsAction">
	<html:hidden property="action" value="<%=POSProductAction.SEARCH_POS_PRODUCTS%>"/>
	<html:text property="productName" styleClass="text" />
    <html:submit styleClass="search smallbutton">
    	&nbsp;
    </html:submit>
	</html:form>	
</div>
<div align="right">
	<logic:present name="<%= Constants.PRODUCT_CART %>">		
		<span id="productCount">
			<pos:message key="cart.has" textOnly="true"/><strong>
			<bean:write name="<%= Constants.PRODUCT_CART %>" property="noOfProducts"/>
			</strong><pos:message key="cart.items" textOnly="true"/>
		</span>		
	</logic:present>
	<logic:notPresent name="<%= Constants.PRODUCT_CART %>">
		<span id="productCount">
			<pos:message key="cart.empty" textOnly="true"/>
		</span>
	</logic:notPresent>
	<span id="cartLinkPanel">
		<html:link action="/ViewPOSProductCartAction.do?action=viewCart" styleClass="button"><pos:message key="view" textOnly="true"/></html:link>			
		<a href="javascript:void(0);" id="clearCart" class="button"><pos:message key="clear" textOnly="true"/></a>
	</span>
	<span>
		<html:link action="/SearchPOSProductsAction.do?action=addAllProducts" styleClass="button"><pos:message key="select.all" textOnly="true"/></html:link>
	</span>
</div>


<div class="space"></div>
<logic:present name="<%=Constants.VIEW_POS_PRODUCTS%>">
<% 
	String url = "SearchPOSProducts.do";
	String collection = Constants.VIEW_POS_PRODUCTS;
%>
<logic:notEmpty name="<%=Constants.VIEW_POS_PRODUCTS%>">
<div class="scrollpane">
<table class="display sortable" border="1" id="1111">
<thead>
	<tr>		
		<th><pos:message key="product.id"/></th>
		<th><pos:message key="Name"/></th>
		<th><pos:message key="Barcode"/></th> 
		<th>&nbsp;</th>  	       	    
 	</tr>
</thead>
<tbody>	
	<logic:iterate offset="<%=offset%>" length="<%=length%>" indexId="count" id="element" name="<%=Constants.VIEW_POS_PRODUCTS%>" type="org.posterita.beans.ProductBean">
	<%
	String styleClass = "label";
	if ((count.intValue()%2) != 0)
	styleClass = "contentname";
   	%>
	
	<tr>		
		<td class=<%=styleClass%> width="100px">
		   	<bean:write name="element" property="productId"/>				
		</td>	
		<td class=<%=styleClass%> width="400px">
			<bean:write name="element" property="productName"/>
		</td>
		<td class=<%=styleClass%>>
		   	<bean:write name="element" property="barCode"/>				
		</td>
		<td class=<%=styleClass%>>			
		   	<img src="images/pos/buttons/button_cart.jpeg" id="<%= element.getProductId() %>" name="cart">				
		   	<span name="qtycount">&nbsp;</span>
		</td>							 	 
	</tr>		
</logic:iterate> 
</tbody>
</table>
<%@ include file="/jsp/include/pager.jsp" %>
</logic:notEmpty>
</logic:present>
</div>
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

$('clearCart').onclick = clearCart;

function clearCart()
{
	var url = "SearchPOSProductsAction.do";
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
	var url = "SearchPOSProductsAction.do";
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

function incrementCount(productId)
{
	try
	{
		var btn = document.getElementById(productId);		
		var count = btn.parentNode.getElementsByTagName('span')[0];
		if(count != null)
		{
			var c = -1;		
			c = parseInt(count.innerHTML);
			
			if( isNaN(c) )
			{
				c = 0;
			}		
			c = c + 1;
			
			count.innerHTML = c;
			productCount = productCount + 1;
			$('productCount').innerHTML = 'Cart has <strong>' + productCount + '</strong> items';
			Element.show('cartLinkPanel');
		}
	}
	catch(e)
	{
		toConsole(e);
	}
}

function decrementCount(productId)
{
	try
	{
		var btn = document.getElementById(productId);		
		var count = btn.parentNode.getElementsByTagName('span')[0];
		if(count != null)
		{
			var c = -1;		
			c = parseInt(count.innerHTML);
			
			if( isNaN(c) )
			{
				c = 0;
			}		
			c = c - 1;
			
			count.innerHTML = c;
			productCount = productCount - 1;
			$('productCount').innerHTML = 'Cart has <strong>' + productCount + '</strong> items';
			Element.show('cartLinkPanel');
		}
	}
	catch(e)
	{
		toConsole(e);
	}
}

function clearAll()
{
	var spans = document.getElementsByTagName('span');
	
	for(var i = 0; i < spans.length; i++)
	{		
		if (spans[i].getAttribute('name') == 'qtycount')
		{
			spans[i].innerHTML = "";	
		}
	}
	
	Element.hide('cartLinkPanel');
	$('productCount').innerHTML = "Cart is empty!";
	productCount = 0;
}

function reportError(request)
{
	alert('Oops! Some errors occured.');
	var win = window.open();
	win.document.write(request.responseText);
	win.document.close();
}
</script>
<%@ include file="/jsp/include/posFooter.jsp" %>
