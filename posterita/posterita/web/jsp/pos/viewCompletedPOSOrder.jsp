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


<!--viewCompletedPOSOrder.jsp-->
<%@ page import="org.posterita.Constants" %>
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

<logic:notPresent name="<%=WebUserInfo.NAME%>" scope="session">
	<jsp:forward page="/LoginHome.do"/>
</logic:notPresent>

<bean:define id="title">POS Order</bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %> 

<div align="right">
	<a href="javascript:showDocumentPDF(<c:out value='${morder._ID}'/>);">
		<img style="cursor:pointer" src="images/ico_printer.gif" border="0"/>
	</a>
</div>		

<%@ include file="/jsp/pos/orderHeader.jsp" %>
<table class="content">	   
 <bean:define id="currSymbole" name="<%= Constants.CURRENCY_SYMBOLE %>"/>		
	<tr>
		<td valign="top"> 
		<table class="cart">
		<tr>    
			
			<th align="left" colspan="2">
				<pos:message key="Name"/>
			</th>
			<th align="left">
				<pos:message key="PriceActual"/>
			</th>
			<th align="left">
				<pos:message key="Discount"/>
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
				<pos:message key="total"/>
			</th>
		</tr>
	    <logic:present name="<%=Constants.POS_ORDER_LINES%>">
		<logic:iterate indexId="count" name="<%=Constants.POS_ORDER_LINES%>" id="element">
		<bean:define id="currSymbole" name="<%= Constants.CURRENCY_SYMBOLE %>"/>		 
		<%
			String styleClass = "label";
			if ((count.intValue()%2) != 0)
				styleClass = "contentname";
		%>
		<tr>															
			
			<td class=<%=styleClass%> colspan="2"><bean:write name="element" property="productName"/></td>
			<td class=<%=styleClass%> align="center"><bean:write name="element" property="unitPrice"/></td>
			<td class=<%=styleClass%> align="center"><bean:write name="element" property="discountPercentage"/></td>
			<td class=<%=styleClass%> align="right"><bean:write name="element" property="qtyOrdered"/></td>
			<bean:define id="qtyTotal" name="element" property="qtyTotal"/>
			<td class=<%=styleClass%> align="right"><bean:write name="element" property="lineNetAmt"/></td>
			<td class=<%=styleClass%> align="right"><bean:write name="element" property="taxAmt"/></td>
			<td class=<%=styleClass%> align="right"><bean:write name="element" property="lineTotalAmt"/></td>	
		</tr>								
		</logic:iterate>	 
	   		</logic:present>
	   
		<tr>
			<td align="left" class="total" colspan=3 > 	
				<bean:message key="orderView.orderPriceDetails.orderTotal"/>
			</td>
			<td class="total"> 	
				&nbsp;
			</td>
			<td class="total"> 	
				&nbsp;
			</td>
			<td nowrap class="total" align="right">  		  
					<fmt:formatNumber value='${qtyTotal}'/>
			</td>
			<td nowrap class="total" align="right">
				<fmt:formatNumber value='${totalLines}' type="currency" currencySymbol='${currSymbole}'/>
			</td>									  						
			<td nowrap class="total" align="right"> 		  
				<fmt:formatNumber value='${orderTax}' type="currency" currencySymbol='${currSymbole}'/>
			</td>
			<td nowrap class="total" align="right"> 		  
				<fmt:formatNumber value='${grandTotal}'type="currency" currencySymbol='${currSymbole}'/>
			</td>
		</tr>
		</table>
		</td>
	</tr>
</table> 
<div class="space"></div>
<div>
	<label>
		Cash Tendered : <fmt:formatNumber value='${morder.amountTendered}'type="currency" currencySymbol='${currSymbole}'/>	
	</label>
	<br>
	<label>
		Cash Refunded : <fmt:formatNumber value='${morder.amountRefunded}'type="currency" currencySymbol='${currSymbole}'/>	
	</label>	
</div>			
		
<%@ include file="/jsp/include/posFooter.jsp" %>
