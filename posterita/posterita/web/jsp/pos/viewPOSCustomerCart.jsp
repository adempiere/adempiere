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



<!-- viewPOSCustomerCart.jsp --> 
<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.compiere.model.MOrg" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

<bean:define id="title"><pos:message key="selected.customers" textOnly="true"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<div class="space"></div>
<%@ include file="/jsp/include/errors.jsp" %>
	 
<logic:present name="<%=Constants.ALL_CART_CUSTOMERS%>">
<logic:empty name="<%=Constants.ALL_CART_CUSTOMERS%>">
	<div><pos:message key="cart.empty" textOnly="true"/></div>	
	<div class="space"></div>
	<div>
		<input type="button" class="newbutton" onclick="get('ViewAllCustomers.do')" value="<pos:message key='cart.addmore' textOnly='true'/>">
	</div>
</logic:empty>

<logic:notEmpty name="<%=Constants.ALL_CART_CUSTOMERS%>">
<div align="right">
	<logic:present name="<%= Constants.CUSTOMER_CART %>">		
		<span id="customerCount">
			<pos:message key="cart.has" textOnly="true"/><strong>
			<bean:write name="<%= Constants.CUSTOMER_CART %>" property="noOfCustomers"/>
			</strong> <pos:message key="cart.items" textOnly="true"/>
		</span>		
	</logic:present>
	<logic:notPresent name="<%= Constants.CUSTOMER_CART %>">
		<span id="customerCount">
			<pos:message key="cart.empty" textOnly="true"/>
		</span>
	</logic:notPresent>
	<input type="button" class="newbutton" onclick="get('ViewAllCustomers.do')" value="<pos:message key='cart.addmore' textOnly='true'/>">	
</div>
</logic:notEmpty>
<div class="space"></div>

<logic:notEmpty name="<%=Constants.ALL_CART_CUSTOMERS%>">
<div class="scrollpane">		  		
<table class="display sortable" border="1" id="111"> 		  		
<tr>
	<th><pos:message key="BPartnerID"/></th>
	<th><pos:message key="Name"/></th>
	<th><pos:message key="Address"/></th>
	<th><pos:message key="City"/></th>
	<th><pos:message key="Phone"/></th>
	<th><pos:message key="Fax"/></th>
	<th><pos:message key="IsActive"/></th>
	<th>&nbsp;</th>
</tr>
<logic:iterate indexId="count" name="<%=Constants.ALL_CART_CUSTOMERS%>" id="element" offset="0" type="org.posterita.beans.CustomerBean">	
	<%
		String styleClass = "label";
		
		if ((count.intValue()%2) != 0)
			styleClass = "contentname";
	%>
	
	<tr>
		<td class="<%=styleClass%>">
			<bean:write name="element" property="bpartnerId"/>
		</td>	
		<td class="<%=styleClass%>">
			<bean:write name="element" property="partnerName"/> <bean:write name="element" property="surname"/>
		</td>	
					
		<td class="<%=styleClass%>">
			<bean:write name="element" property="address1"/><BR> 
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
			<logic:equal name="element" property="isActive" value="true">
				Y
			</logic:equal>
			<logic:equal name="element" property="isActive" value="false">
				N
			</logic:equal>
		</td>
		<td class="<%=styleClass%>">
			<a href="POSCustomerAction.do?action=remove&bpartnerId=<%= element.getBpartnerId() %>"><pos:message key="cart.remove"/></a>
		</td>				
	</tr>
</logic:iterate>
</table>
</div>
<div class="space"></div>
<div align="right">
	<div class="button floatRight" id="printBarcodeBtn" onclick="printCustomerBarcode()">PRINT FIDELITY</div>	
</div>
</logic:notEmpty>
</logic:present>
<div>
	<applet archive="<%= basePath %>applets/printOrderApplet.jar" code="org.posterita.core.PrintOrderApplet.class" width="0" height="0"></applet>
</div>

<logic:present cookie="preference.printing">
<logic:equal cookie="preference.printing" property="value" value="true">
	<bean:cookie id="cookie" name="preference.printer"/>
	<script>
				
		function printCustomerBarcode()
		{
			
			try
			{				
				
				document.applets[0].setPrinterName('<%= cookie.getValue()%>');
				var url = '<%=basePath %>POSCustomerAction.do?action=generateCustomerBarcode';
				document.applets[0].printURL(url);
			}
			catch(e)
			{
				toConsole(e);
			}
		}
	</script>
</logic:equal>
</logic:present>

<logic:present cookie="preference.printing">
<logic:equal cookie="preference.printing" property="value" value="false">
<bean:cookie id="cookie" name="preference.printer"/>
<script>
	function printCustomerBarcode()
	{		
		try
		{
			
			document.applets[0].setPrinterName('<%= cookie.getValue() %>');
			var url = '<%=basePath %>POSCustomerAction.do?action=generateCustomerBarcode';
			document.applets[0].printURL(url);
		}
		catch(e)
		{
			toConsole(e);
		}
	}
</script>
</logic:equal>
</logic:present>

<logic:notPresent cookie="preference.printing">
<script>
	function printCustomerBarcode()
	{
		alert("Please set your printing preferences!!!");
	}
</script>
</logic:notPresent>
			
<%@ include file="/jsp/include/posFooter.jsp" %> 