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



<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.Constants" %>


<%@ page import="org.posterita.struts.pos.POSOrderAction" %>
<%@ page import="org.posterita.beans.ProductBean" %>
<%@ page import="org.posterita.user.WebUserInfo" %>
<%@ page import="org.posterita.lib.UdiConstants" %>
<%@ page import="org.posterita.struts.pos.POSGoodsAction" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos" %>

	
<logic:present name="<%=Constants.SHOPPING_ORDER_CART_ITEMS%>">
<table width="100%" border="0" cellpadding="5" cellspacing="0" class="content">	


<logic:notEmpty name="<%=Constants.SHOPPING_ORDER_CART_ITEMS%>">
	<tr>					
		<th>
			<B><pos:message key="Description"/></B>
		</th>					
		<th>
			<B><pos:message key="Qty"/></B>
		</th>					
		<th>
			<B><pos:message key="Price"/></B>
		</th>	
		<th>
			<B><pos:message key="vat"/></B>
		</th>
		<th>
			<B><pos:message key="total"/></B>
		</th>			
		<th>
			&nbsp;
		</th>
		<th>
			&nbsp;
		</th>
		<th>
			&nbsp;
		</th>
	</tr>
				
	<logic:iterate indexId="count" id="element" name="<%=Constants.SHOPPING_ORDER_CART_ITEMS%>" type="org.posterita.beans.ItemBean">
	<%
	String styleClass = "label";
	if ((count.intValue()%2) != 0)
		styleClass = "contentname";
	%>
	<tr>				
		<td class="<%=styleClass%>">
			<bean:write name="element" property="description"/>
			<html:hidden property="productId"/>
		</td>
	
		<td class="<%=styleClass%>">
			<bean:write name="element" property="qty"/>
		</td>
		
		<td class="<%=styleClass%>">
			<bean:define id="standardPrice" name="element" property="standardPrice"/>
			<fmt:formatNumber value='${standardPrice}'type="currency" currencySymbol='${currSymbole}'/>
			<bean:define id="priceTotal" name="element" property="priceTotal"/>
		</td>
	
		<td class="<%=styleClass%>">
			<bean:define id="taxAmt" name="element" property="taxAmt"/>
			<fmt:formatNumber value='${taxAmt}'type="currency" currencySymbol='${currSymbole}'/>
			<bean:define id="taxTotal" name="element" property="taxTotal"/>
		</td>	
		
		<td class="<%=styleClass%>" align="right">
			<bean:define id="price" name="element" property="price"/>
			<fmt:formatNumber value='${price}'type="currency" currencySymbol='${currSymbole}'/>
			<bean:define id="grandTotal" name="element" property="grandTotal"/>
		</td>		
		
		</tr>
	</logic:iterate>
	<tr>
		<bean:define id="currSymbole" name="<%= Constants.CURRENCY_SYMBOLE %>"/>
		<td class="total"><pos:message key="GrandTotal"/></td>
		<td class="total">&nbsp;</td>
		<td nowrap class="total">  		  
			<fmt:formatNumber value='${priceTotal}'type="currency" currencySymbol='${currSymbole}'/>
		</td>
		<td nowrap class="total">  		  
			<fmt:formatNumber value='${taxTotal}'type="currency" currencySymbol='${currSymbole}'/>
		</td>
		<td nowrap class="total">  		  
			<fmt:formatNumber value='${grandTotal}'type="currency" currencySymbol='${currSymbole}'/>
		</td>
		<td class="total"></td>
		<td class="total">&nbsp;</td>
		<td class="total">&nbsp;</td>
	
	
	
	 <%--<td class="darkColour">
				<bean:write name="<%=Constants.SHOPPING_ORDER_CART%>" property="totalPrice"/>
	</td>	
	--%></tr>
</logic:notEmpty>	
</table>
</logic:present>	

		