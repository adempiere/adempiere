<%--
 *  Product: Posterita Web-Based POS (an Adempiere Plugin)
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
 * @author Praveen Beekoo
--%>


<!--viewProductSalesDetails.jsp-->
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.beans.BPartnerInfoBean" %>
<%@ page import="org.posterita.user.WebUserInfo" %>
<%@ page import="org.posterita.lib.UdiConstants" %>
<%@ page import="org.posterita.struts.pos.BpartnerInfoAction" %>
<%@ page import="org.posterita.struts.pos.POSGoodsAction" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

<logic:notPresent name="<%=WebUserInfo.NAME%>" scope="session">
	<jsp:forward page="/POSLogin.do"/>
</logic:notPresent>

<bean:define id="title"><pos:message key="product.info" textOnly="true"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %> 

<div>
	<html:link href="ViewAllPOSProduct.do">
	 <pos:message key="back" textOnly="true"/>
	</html:link>
</div>	
	
<table class="display" border="1">
	<caption><pos:message key="Summary" textOnly="true"/></caption>					
	<tr>
		<th><pos:message key="Name"/></th>
		<th><pos:message key="qty.sold"/></th>
		<th><pos:message key="net.amt"/></th>
		<th><pos:message key="QtyOnHand"/></th>
	</tr>
	<tr>		
		<td class="label">
			<bean:write name="<%=Constants.PRODUCT_SALES_SUMMARY%>" property="productName"/>			
		</td>	
		<td class="label">
			<bean:write name="<%=Constants.PRODUCT_SALES_SUMMARY%>" property="qtySold"/>
		</td>
		<td class="label">
			<fmt:formatNumber type="currency" currencySymbol='${currSymbole}' minFractionDigits="2">		
			<bean:write name="<%=Constants.PRODUCT_SALES_SUMMARY%>" property="totalAmount"/>
			</fmt:formatNumber>
		</td>
		<td class="label">
			<bean:write name="<%=Constants.PRODUCT_SALES_SUMMARY%>" property="qtyOnHand"/>
		</td>			
	</tr>		
</table>										
<div class="space">
</div>
<table class="display" border="1">
<caption><pos:message key="summary.by.periods" textOnly="true"/></caption>	
<tr>
	<th><pos:message key="current.year"/></th>
	<th><pos:message key="6 Months"/></th>
	<th><pos:message key="last.3.months"/></th>
	<th><pos:message key="last.2.months"/></th>
	<th><pos:message key="current.month"/></th>
	<th><pos:message key="current.week"/></th>
	<th><pos:message key="today"/></th>
	
</tr>
<tr>
<logic:iterate id="bucket" name="<%=Constants.PRODUCT_SALES_BUCKET %>" type="org.posterita.beans.ProductSalesSummaryBean">
	<td class="label" align="right">
		<pos:message key="qty.sold"/>:
		<bean:write name="bucket" property="qtySold"/>
		<br>
		<pos:message key="TotalAmt"/>:		
		<fmt:formatNumber type="currency" currencySymbol='${currSymbole}' minFractionDigits="2">		
			<bean:write name="bucket" property="totalAmount"/>
		</fmt:formatNumber>			
	</td>
</logic:iterate>
</tr>
</table>
<div class="space">
</div>
<table class="display sortable" border="1" id="111">	
	<caption><pos:message key="sales.details" textOnly="true"/></caption>	
	<tr>		
		<th><pos:message key="DocumentNo"/></th>
		<th><pos:message key="DateOrdered"/></th>
		<th><pos:message key="customer"/></th>
		<th<pos:message key="OrderType"/></th>
		<th class="numeric"><pos:message key="QtyOrdered"/></th>
		<th class="currency"><pos:message key="Amount"/></th>
	</tr>	
		<logic:present name="<%=Constants.PRODUCT_SALES_DETAILS%>">
		<logic:iterate id="element" indexId="count" name="<%=Constants.PRODUCT_SALES_DETAILS %>" type="org.posterita.beans.ProductSalesInfoBean">
		<tr>	
		<%
			String styleClass1 = "label";
			if ((count.intValue()%2) != 0)
				styleClass1 = "contentname";
		%>							
		   <td class=<%=styleClass1%>>
		   		<html:link href="ViewPOSOrderAction.do?action=viewPOSOrders&orderId=<%= element.getOrderId() %>">
					<bean:write name="element" property="documentNo"/>
				</html:link>		
		   </td>						
		   <td class=<%=styleClass1%>>
				<bean:write name="element" property="dateOrdered"/>
		   </td>						   
		   <td class=<%=styleClass1%>>
				<bean:write name="element" property="bpartnerName"/>
		   </td>
		   <td class=<%=styleClass1%>>
				<bean:write name="element" property="orderType"/>
		   </td>							   
		   <td class=<%=styleClass1%> align="right">
				<bean:write name="element" property="qtyOrderded"/>
		   </td>		
		   <td class=<%=styleClass1%> align="right">
		   		<fmt:formatNumber type="currency" currencySymbol='${currSymbole}' minFractionDigits="2">		
					<bean:write name="element" property="lineAmount"/>
				</fmt:formatNumber>				
		   </td>				
		</tr>		
	</logic:iterate>    
	</logic:present>
</table>    									 					 
<%@ include file="/jsp/include/posFooter.jsp" %>
