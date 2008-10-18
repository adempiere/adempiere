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

<!-- AllPOSCustomer.jsp --> 
<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.compiere.model.MOrg" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

<bean:define id="title"><pos:message textOnly="true" key="smenu.customers"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<div align="right">
	<TABLE border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
		
			<td align="left">
				<html:form action="POSCustomerAction" focus="partnerName">
					<html:hidden property="action" value="searchPOSCustomer"/>
					<html:text property="partnerName" styleClass="text"/>
				
					<html:submit styleClass="tangoSearch tangoButton">&nbsp;</html:submit>
					
					<pos:message key="filter.by"/>
					<html:select property="day" onchange="submit()">
						<html:option value=""><pos:message key="all"/></html:option>		
						<%@include file="/jsp/include/dayList.jsp"%>						
					</html:select>	
					<html:select property="month" onchange="submit()">
						<html:option value=""><pos:message key="all"/></html:option>	
						<%@include file="/jsp/include/month.jsp"%>							
					</html:select>	
				
					<html:select property="year" onchange="submit()">
						<html:option value=""><pos:message key="all"/></html:option>	
						<%
							int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
						%>
						<html:option value="<%= String.valueOf(currentYear) %>"><%= currentYear %></html:option>
						<html:option value="<%= String.valueOf(currentYear - 1) %>"><%= currentYear - 1 %></html:option>							
			   		</html:select>
			   		<pos:message key="view"/>
			   		<html:select property="isActive" onchange="submit()">
			   		    <html:option value="">All</html:option>	
						<html:option value="Y">Active</html:option>
						<html:option value="N">Not Active</html:option>							
			   		</html:select>			   									
				</html:form>
			</td>
			
				
			<td align="right">	
				<html:link href="InitCreatePOSCustomer.do?action=initCreatePOSCustomer">
					<img src="images/tango/document-new.png" title='<pos:message textOnly="true" key="add"/>' alt='<pos:message textOnly="true" key="add"/>' border="0">
				</html:link>
			</td>
			<td>
				<html:link href="<%="ImportPOSCustomers.do"%>">
					<img src="images/tango/applications-system.png" title="Import POS Customers" alt="Import POS Customers" border="0">
				</html:link>			
			</td>
<!--
			<html:link href="BlackListChequeAction.do?action=initSearchCheque"> <pos:message key="black.listed.cheques"/> </html:link>
-->		
		
		</tr>
		
	</TABLE>
</div>

<logic:present name="<%=Constants.CUSTOMER_CART%>">
	<%
		org.posterita.businesslogic.CustomerCart cart = (org.posterita.businesslogic.CustomerCart)request.getSession().getAttribute(Constants.CUSTOMER_CART);
	%>	
</logic:present>

<div align="right">
	<logic:present name="<%= Constants.CUSTOMER_CART %>">		
		<span id="customerCount">
			<pos:message key="cart.has"/> <strong>
			<bean:write name="<%= Constants.CUSTOMER_CART %>" property="noOfCustomers"/>
			</strong> <pos:message key="items"/>
		</span>		
	</logic:present>
	<logic:notPresent name="<%= Constants.CUSTOMER_CART %>">
		<span id="customerCount">
			<pos:message key="card.is.empty"/>
		</span>
	</logic:notPresent>
	<span id="cartLinkPanel">
		<input type="button" class="newbutton" onclick="get('POSCustomerAction.do?action=viewCart')" value="<pos:message key='view' textOnly='true'/>">
		<input type="button" id="clearCart" class="newbutton" value="<pos:message key='clear' textOnly='true'/>">
	</span>
	<span>
		<input type="button" class="newbutton" onclick="get('POSCustomerAction.do?action=addAllCustomers')" value="<pos:message key='select.all' textOnly='true'/>">
	</span>
</div>
<table>
  
</table>
<logic:present name="<%=Constants.ALL_CUSTOMERS%>">
<logic:empty name="<%=Constants.ALL_CUSTOMERS%>">
	<div><pos:message key="no.customer.was.found.for"/>: <b><bean:write name="CustomerForm" property="partnerName"/></b></div>
	<div class="space"></div>
</logic:empty>
<logic:notEmpty name="<%=Constants.ALL_CUSTOMERS%>">		  		
<table class="display sortable" border="1" id="111">		  		
<tr>
	<th><pos:message key="Name"/></th>
	<th><pos:message key="Address"/></th>
	<th><pos:message key="City"/></th>
	<th><pos:message key="Phone"/></th>
	<th><pos:message key="Fax"/></th>
	<th><pos:message key="birthdate"/></th>
	<th><pos:message key="IsActive"/></th>
	<th>&nbsp;</th>
	<th>&nbsp;</th>
</tr>

<%
	String url = "ViewAllCustomers.do";
	String collection = Constants.ALL_CUSTOMERS;
%>	

<logic:iterate offset="<%=offset%>" length="<%=length%>" indexId="count" name="<%=collection%>" id="element">	
	<%
		String styleClass = "label";
		
		if ((count.intValue()%2) != 0)
			styleClass = "contentname";
	%>
	
	<tr>
		<td class="<%=styleClass%>">
			<bean:write name="element" property="partnerName"/> 
			<bean:write name="element" property="surname"/>
		</td>	
			
		<td class="<%=styleClass%>">
			<bean:write name="element" property="address1"/><br>
			<bean:write name="element" property="address2"/>
		</td>
		<td class="<%=styleClass%>">
			<bean:write name="element" property="city"/>
		</td>
		<td class="<%=styleClass%>">
			<bean:write name="element" property="phone"/>
		</td>
		<td class="<%=styleClass%>">
			<bean:write name="element" property="fax"/>
		</td>
		<td class="<%=styleClass%>">
			<bean:write name="element" property="birthdate"/>
		</td>

		<td class="<%=styleClass%>">
			<logic:equal name="element" property="isActive" value="true">
				Y
			</logic:equal>
			<logic:equal name="element" property="isActive" value="false">
				N
			</logic:equal>
		</td>
								
								
		<td class="<%=styleClass%>" nowrap>
		<bean:define id="bpartnerId" name="element" property="bpartnerId"/>

			<logic:equal name="element" property="isEditable" value="true">		
				<html:link href="<%="POSCustomerAction.do?action=initEditPOSCustomer&bpartnerId=" + bpartnerId%>">
					<img src="images/tango/accessories-text-editor.png" title='<pos:message textOnly="true" key="edit.customer"/>' alt='<pos:message textOnly="true" key="edit.customer"/>' border="0">
				</html:link>
			</logic:equal>
			
			&nbsp;
			<html:link href="<%="POSCustomerAction.do?action=viewPOSCustomer&bpartnerId=" + bpartnerId%>">
				<img src="images/tango/edit-find.png" title='<pos:message textOnly="true" key="view"/>' alt='<pos:message textOnly="true" key="view"/>' border="0">
			</html:link>

			<logic:equal name="element" property="isEditable" value="true">
				&nbsp;
				<logic:equal name="element" property="isActive" value="true">
					<html:link href="<%="POSCustomerAction.do?action=deactivatePOSCustomer&bpartnerId=" + bpartnerId%>">	
						<img src="images/tango/edit-redo.png" title='<pos:message textOnly="true" key="deactivate"/>' alt='<pos:message textOnly="true" key="deactivate"/>' border="0">
					</html:link>
				</logic:equal>
				<logic:notEqual name="element" property="isActive" value="true">
					<html:link href="<%="POSCustomerAction.do?action=activatePOSCustomer&bpartnerId=" + bpartnerId%>">
						<img src="images/tango/edit-undo.png" title='<pos:message textOnly="true" key="activate"/>' alt='<pos:message textOnly="true" key="activate"/>' border="0">
					</html:link>
				</logic:notEqual> 
			</logic:equal>	
		</td>
		<td class="<%=styleClass%>" nowrap>	
			<logic:present name="<%=Constants.CUSTOMER_CART%>">
				<%
					if(((org.posterita.businesslogic.CustomerCart)request.getSession().getAttribute(Constants.CUSTOMER_CART)).hasCustomer(Integer.parseInt(bpartnerId+"")))
					{
				%>
						<img style="cursor: pointer;" src="images/tango/list-remove.png" title='<pos:message textOnly="true" key="remove.customer.fidelity.card"/>' id="<%=bpartnerId%>" name="removeBtn">
						
				<%
					}
					else
					{
				%>
						<img style="cursor: pointer;" src="images/tango/list-add.png" title='<pos:message textOnly="true" key="add.customer.fidelity.card"/>' id="<%=bpartnerId%>" name="addBtn">
				<%
					} 
				%>
			</logic:present>
			<logic:notPresent name="<%=Constants.CUSTOMER_CART%>">
				<img style="cursor: pointer;" src="images/tango/list-add.png" title='<pos:message textOnly="true" key="add.customer.fidelity.card"/>' id="<%=bpartnerId%>" name="addBtn">
			</logic:notPresent>		
		</td>						
	</tr>
</logic:iterate>
</table>
<%@ include file="/jsp/include/pager.jsp" %>
</logic:notEmpty>
</logic:present>

<script>
var addBtns = document.getElementsByName('addBtn');
var removeBtns = document.getElementsByName('removeBtn');
var customerCount = 0;

<logic:present name="<%= Constants.CUSTOMER_CART %>">
customerCount = <bean:write name="<%= Constants.CUSTOMER_CART %>" property="noOfCustomers"/>;
</logic:present>

if(customerCount == 0)
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
	var url = "POSCustomerAction.do";
	var pars = "action=clearCart";
	
	var myAjax = new Ajax.Request( url, 
	{ 
		method: 'get', 
		parameters: pars, 
		onSuccess: evaluateResponse, 
		onFailure: reportError
	});
}

function addToCart(customerId)
{
	var url = "POSCustomerAction.do";
	var pars = "action=addToCart&bpartnerId=" + customerId;
	
	var myAjax = new Ajax.Request( url, 
	{ 
		method: 'get', 
		parameters: pars, 
		onSuccess: evaluateResponse, 
		onFailure: reportError
	});	
	
}

function removeFromCart(customerId)
{
	var url = "POSCustomerAction.do";
	var pars = "action=removeFromCart&bpartnerId=" + customerId;
	
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
		//alert(script);
	}
	catch(e)	
	{
		toConsole(e);
	}
}

function setCartCounter(customerCount)
{
	$('customerCount').innerHTML = 'Cart has <strong>' + customerCount + '</strong> items';
	Element.show('cartLinkPanel');
}

function customerAdded(customerId)
{
	customerId = customerId + '';
	$(customerId).src = 'images/tango/list-remove.png';
	$(customerId).title = 'Remove customer from cart';
	$(customerId).onclick = function(){
		removeFromCart(this.id);
	};
	
}

function customerRemoved(customerId)
{
	customerId = customerId + '';
	$(customerId).src = 'images/tango/list-add.png';
	$(customerId).title = 'Add Customer to Cart';
	$(customerId).onclick = function(){
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
		$('customerCount').innerHTML = "Cart is empty!";
		customerCount = 0;
		
		toConsole('add btn :' + addBtns.length);
		for(var i=0; i<addBtns.length; i++)
		{
			var btn = addBtns[i];
			btn.src = 'images/tango/list-add.png';
			btn.title = 'Add Customer to Cart';
			btn.onclick = function(){
				addToCart(this.id);
			};
		}
		
		toConsole('remove btn :' + removeBtns.length);
		for(var j=0; j<removeBtns.length; j++)
		{
			var btn = removeBtns[j];
			btn.src = 'images/tango/list-add.png';
			btn.title = 'Add Customer to Cart';
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