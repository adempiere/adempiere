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


<!-- viewAllPOSProducts.jsp -->
<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.beans.*" %>
<%@ page import="org.posterita.struts.pos.POSProductAction" %>
<%@ page import="org.compiere.process.DocumentEngine" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt" %>

<%@ page import="org.compiere.model.MOrder" %>
<%@ page import="org.posterita.user.WebUserInfo" %>
<%@ page import="org.posterita.struts.pos.POSOrderAction" %>
<%@ page import="java.util.ArrayList" %> 

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/dcs.tld" prefix="dcs" %>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos" %>



<logic:notPresent name="<%=WebUserInfo.NAME%>" scope="session">
	<jsp:forward page="/LoginHome.do"/>
</logic:notPresent>

<bean:define id="title"><pos:element textOnly="true" columnName="smenu.products"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %> 

<table class="content" border="0">

	<html:form action="/ViewAllPOSProductsAction">
	<html:hidden property="action" value="<%=POSProductAction.VIEW_ALL_POS_PRODUCTS%>"/>
	
	<tr>	
		<td>
			<pos:element columnName="Name"/>
			&nbsp;
			<html:text property="productName" styleClass="text" />
			&nbsp;&nbsp;
			<pos:element columnName="barcode"/>
			&nbsp;
			<html:text property="barCode" styleClass="text" />

	        <html:submit styleClass="tangoSearch tangoButton">
	        	&nbsp;
	        </html:submit>
		</td>
		
		<td align="right">
			<html:link href="<%="GetProductCategory.do?action=getProductCategory"%>">
				<img src="images/tango/document-new.png" title="Create Product" alt="Create Product" border="0">
			</html:link>

			<html:link href="<%="GetTaxCategoryAction.do?action=getTaxCategory"%>">
				<img src="images/tango/document-new.png" title="Create Textile Product" alt="Create Textile Product" border="0">
			</html:link>

			<html:link href="<%="ImportPOSProducts.do?"%>">
				<img src="images/tango/applications-system.png" title="Import POS Products" alt="Import POS Products" border="0">
			</html:link>			
			<%--
			<html:link href="SearchPOSProducts.do">
				<img src="images/tango/preferences-system.png" title="Product Mgt" alt="Product Mgt" border="0">
			</html:link>
			--%>			
		</td>		
	</tr>   	

	<tr>
		<td colspan="2">
			<img src="images/tshirt/1pixel.gif" width="1" height="5" border="0">
		</td>
	</tr>		
</html:form>	
</table>

<logic:present name="<%=Constants.VIEW_POS_PRODUCTS%>">
<logic:empty name="<%=Constants.VIEW_POS_PRODUCTS%>">
	<div>No products were found for : <b><bean:write name="ViewProductForm" property="productName"/></b></div>
	<div class="space"></div>
</logic:empty>
</logic:present>

<div align="right">
	<logic:present name="<%= Constants.PRODUCT_CART %>">		
		<span id="productCount">
			<pos:element columnName="cart.has" textOnly="true"/><strong>
			<bean:write name="<%= Constants.PRODUCT_CART %>" property="noOfProducts"/>
			</strong><pos:element columnName="cart.items" textOnly="true"/>
		</span>		
	</logic:present>
	<logic:notPresent name="<%= Constants.PRODUCT_CART %>">
		<span id="productCount">
			<pos:element columnName="cart.empty" textOnly="true"/>
		</span>
	</logic:notPresent>
	<span id="cartLinkPanel">
		<input type="button" value="<pos:element columnName="view" textOnly="true"/>" class="newbutton" onclick="get('ViewPOSProductCartAction.do?action=viewCart')">			
		<input type="button" id="clearCart" value="<pos:element columnName="clear" textOnly="true"/>" class="newbutton" onclick="get('SearchPOSProductsAction.do?action=addAllProducts')">
	</span>
	<span>
		<input type="button" value="<pos:element columnName="select.all" textOnly="true"/>" class="newbutton" onclick="get('SearchPOSProductsAction.do?action=addAllProducts')">
	</span>
</div>
<div class="space"></div>
<logic:present name="<%=Constants.VIEW_POS_PRODUCTS%>">
<logic:notEmpty name="<%=Constants.VIEW_POS_PRODUCTS%>">
<div class="scrollpane">
<table class="display sortable" border="1" width="900px" id="1111">
	<tr>
		<th class="string"><pos:element columnName="Name"/></th>
		<th class="string"><pos:element columnName="Barcode"/></th> 
	    <th class="string"><pos:element columnName="Description"/></th>
	    <th class="string" nowrap="nowrap"><pos:element columnName="C_RevenueRecognition_ID"/></th>
   	    <th class="numeric" nowrap="nowrap"><pos:element columnName="purchase.price"/></th>  
   	    <th class="numeric" nowrap="nowrap"><pos:element columnName="sales.price"/></th>   
   	    <th class="string" nowrap="nowrap"><pos:element columnName="IsActive"/></th>   
   	    <th nowrap="nowrap"><pos:element columnName="update.details"/></th>
   	    <th>&nbsp;</th>
 	</tr>

	<bean:define id="editmsg">
		<pos:element columnName="edit.product" textOnly="true"/>
	</bean:define>
	<bean:define id="viewmsg">
		<pos:element columnName="view.product" textOnly="true"/>
	</bean:define>
	<bean:define id="activatemsg">
		<pos:element columnName="activate.product" textOnly="true"/>
	</bean:define>
	<bean:define id="deactivatemsg">
		<pos:element columnName="deactivate.product" textOnly="true"/>
	</bean:define>
	
	<%
		String url = "ViewAllPOSProduct.do";
		String collection = Constants.VIEW_POS_PRODUCTS;
	%>
	
	<logic:iterate offset="<%=offset%>" length="<%=length%>" indexId="count" id="element" name="<%=collection%>" type="org.posterita.beans.ProductBean">
	<%
	String styleClass = "label";
	if ((count.intValue()%2) != 0)
	styleClass = "contentname";
   	%>
	
	<tr>
		<td class=<%=styleClass%> width="400px">
			<html:link action="<%= "POSProductSalesAnalysisAction.do?action=viewProductSalesDetails&productId=" + element.getProductId() %>">
				<bean:write name="element" property="productName" filter="false"/>
			</html:link>
		</td>
		<td class=<%=styleClass%> width="100px">
		   	<html:link href="<%="BarcodeAction.do?action=getBarcodeImage&barCode=" + element.getBarCode() %>">
				<bean:write name="element" property="barCode"/>
			</html:link>		
		 </td>
			
		<td class=<%=styleClass%> width="150px">
			<bean:write name="element" property="description" filter="false"/>
		</td>
		<td class=<%=styleClass%>>
			<bean:write name="element" property="revenueRecognition" filter="false"/>
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
			<bean:write name="element" property="isActive"/>
		</td>
		<td  align="right" class=<%=styleClass%>>
			<html:link href="<%="ViewProductForUpdateAction.do?action=viewProductForUpdate&productId="+ element.getProductId()%>">
				<img src="images/tango/accessories-text-editor.png" title="<%=editmsg%>" alt="<%=editmsg%>" border="0"> 
			</html:link>
			<html:link href="<%="ViewProductForUpdateAction.do?action=viewProduct&productId="+ element.getProductId()%>">
				<img src="images/tango/edit-find.png" title="<%=viewmsg%>" alt="<%=viewmsg%>" border="0"> 
			</html:link>
			
			<logic:equal name="element" property="isActive" value="true">					    
			<html:link href="<%="ViewProductForUpdateAction.do?action=deactivateProduct&productId="+ element.getProductId()%>">
				<img src="images/tango/edit-redo.png" title="<%=activatemsg%>" alt="<%=activatemsg%>" border="0"> 
			</html:link>
			</logic:equal>
			
			<logic:notEqual name="element" property="isActive" value="true">
			<html:link href="<%="ViewProductForUpdateAction.do?action=activateProduct&productId="+ element.getProductId()%>">
				<img src="images/tango/edit-undo.png" title="<%=deactivatemsg%>" alt="<%=deactivatemsg%>" border="0"> 
			</html:link>
			</logic:notEqual>
	 	</td>	 	
	 	<td class="<%=styleClass%>" nowrap>	
			<logic:present name="<%=Constants.PRODUCT_CART%>">
				<%
					if(((org.posterita.businesslogic.ProductCart)request.getSession().getAttribute(Constants.PRODUCT_CART)).hasProduct(Integer.parseInt(element.getProductId()+"")))
					{
				%>
						<img style="cursor: pointer;" src="images/tango/list-remove.png" title='<pos:element textOnly="true" columnName="remove.customer.fidelity.card"/>' id="<%=element.getProductId()%>" name="removeBtn">
						
				<%
					}
					else
					{
				%>
						<img style="cursor: pointer;" src="images/tango/list-add.png" title='<pos:element textOnly="true" columnName="add.customer.fidelity.card"/>' id="<%=element.getProductId()%>" name="addBtn">
				<%
					} 
				%>
			</logic:present>
			<logic:notPresent name="<%=Constants.PRODUCT_CART%>">
				<img style="cursor: pointer;" src="images/tango/list-add.png" title='<pos:element textOnly="true" columnName="add.customer.fidelity.card"/>' id="<%=element.getProductId()%>" name="addBtn">
			</logic:notPresent>		
		</td>	 	 
	</tr>		
</logic:iterate>
</table>
<%@ include file="/jsp/include/pager.jsp" %>
</logic:notEmpty> 
</logic:present> 
</div>
<script>
var addBtns = document.getElementsByName('addBtn');
var removeBtns = document.getElementsByName('removeBtn');
var productCount = 0;

<logic:present name="<%= Constants.PRODUCT_CART %>">
productCount = <bean:write name="<%= Constants.PRODUCT_CART %>" property="noOfProducts"/>;
</logic:present>

if(productCount == 0)
{
	Element.hide('cartLinkPanel');
}

for(var i=0; i<addBtns.length; i++)
{
	try
	{
		var btn = addBtns[i];
	
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

for(var i=0; i<removeBtns.length; i++)
{
	try
	{
		var btn = removeBtns[i];
	
		btn.style.cursor = 'pointer';
		btn.onclick = function(e)
		{		
			removeFromCart(this.id);		
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

function removeFromCart(productId)
{
	var url = "SearchPOSProductsAction.do";
	var pars = "action=removeFromCart&productId=" + productId;
	
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
	if(productCount > 0)
	{
		$('productCount').innerHTML = '<pos:element columnName="cart.has" textOnly="true"/> <strong>' + productCount + '</strong> <pos:element columnName="cart.items" textOnly="true"/>';
		Element.show('cartLinkPanel');
	}
	else
	{
		$('productCount').innerHTML = '<pos:element columnName="cart.empty" textOnly="true"/>';
		Element.hide('cartLinkPanel');
	}
	
}

function productAdded(productId)
{
	productId = productId + '';
	$(productId).src = 'images/tango/list-remove.png';
	$(productId).title = 'Remove Product from cart';
	$(productId).onclick = function(){
		removeFromCart(this.id);
	};
	
}

function productRemoved(productId)
{
	productId = productId + '';
	$(productId).src = 'images/tango/list-add.png';
	$(productId).title = 'Add Product to Cart';
	$(productId).onclick = function(){
		addToCart(this.id);
	};
}


function clearAll()
{
	try
	{
		toConsole('Clearing cart');
		
		var qtyCounters = document.getElementsByName('qtyCount');
	
		for(var i = 0; i < qtyCounters.length; i++)
		{
			qtyCounters[i].innerHTML = "";
		}
		
		Element.hide('cartLinkPanel');
		$('productCount').innerHTML = "<pos:element columnName="cart.empty" textOnly="true"/>";
		customerCount = 0;
		
		toConsole('add btn :' + addBtns.length);
		for(var i=0; i<addBtns.length; i++)
		{
			var btn = addBtns[i];
			btn.src = 'images/tango/list-add.png';
			btn.title = 'Add Product to Cart';
			btn.onclick = function(){
				addToCart(this.id);
			};
		}
		
		toConsole('remove btn :' + removeBtns.length);
		for(var j=0; j<removeBtns.length; j++)
		{
			var btn = removeBtns[j];
			btn.src = 'images/tango/list-add.png';
			btn.title = 'Add Product to Cart';
			btn.onclick = function(){
				addToCart(this.id);
			};
		}
		
		
	}
	catch(e)
	{
		toConsole(e);
	}
}

function reportError(request)
{
	alert('Oops! Some errors occured.');
	var win = window.open();
	win.document.write(request.responseText);
	win.document.close();
	
}

//setCartCounter(customerCount)
</script>	
<%@ include file="/jsp/include/posFooter.jsp" %>
