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
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>


<html> 
	<head>		
		<title><pos:message textOnly="true" key="print.order"/></title>							
		<script src="js/pos.js"></script>			
		<link rel="stylesheet" href="css/pos.css" type="text/css">			
	</head>
	<body style="background-color:#FFFFFF">		
	<table width="100%" border="0" cellpadding="5" cellspacing="0">  		
	<tr>
		<td>
			<bean:write name="<%=Constants.WEB_DOCUMENT_HEADER_BEAN%>" property="to"/>
			<c:out value='${me.name}'/>			
			<br>
			<br>
			<%-- Caters for previously sold vehicles where sales rep was not available --%>
			<logic:present name="<%=Constants.SALES_REP%>">
			Sales Representative:<bean:write name="<%=Constants.SALES_REP%>"/>
			</logic:present>
		</td>		
		<td align="right">
			<bean:write name="<%=Constants.WEB_DOCUMENT_HEADER_BEAN%>" property="from"/>			
			<c:out value='${you.name}'/>
			<c:out value='${you.name2}'/>			
		</td>
	</tr>  					
	<tr>
		<logic:present name="<%=Constants.WEB_DOCUMENT_HEADER_BEAN%>" property="dealerRef">
		<td width="50%" colspan="2" align="left">
			<bean:write name="<%=Constants.WEB_DOCUMENT_HEADER_BEAN%>" property="dealerRef"/><c:out value='${morder.POReference}'/>
		</td>
		</logic:present>
		
		<logic:present name="<%=Constants.WEB_DOCUMENT_HEADER_BEAN%>" property="dealerCounterRef">
		<td width="50%" colspan="2" align="right">
			<bean:write name="<%=Constants.WEB_DOCUMENT_HEADER_BEAN%>" property="dealerCounterRef"/><c:out value='${morder.POReference}'/>
		</td>
		</logic:present>
	</tr>	
	<tr>
		<td width="50%">
			<bean:write name="<%=Constants.WEB_DOCUMENT_HEADER_BEAN%>" property="dcsRef"/>
			<c:out value='${morder._ID}'/>
		</td>
	</tr>			   		
	<tr>
		<td>
			<pos:message key="DocStatus"/>:<dcs:orderStatus name="<%=Constants.MORDER%>" property="docStatus"/>
		</td>
	</tr>
	</table>

	<table width="100%" cellspacing="0" cellpadding="5" border="0">	   		
	<tr>
		<td valign="top">
			<table width="100%" class="content" cellpadding="5" cellspacing="1" align="center" border="1" cols="8">
			<tr>    
				<th>
					<pos:message key="SerNo"/>
				</th>
				<th>
					<pos:message key="Name"/>
				</th>
				<th>
					<pos:message key="Qty"/>
				</th>
				<th>
					<pos:message key="Price"/>
				</th>
				<th>
					<pos:message key="vat"/>
				</th>
				<th>
					<pos:message key="total"/>
				</th>
			</tr>
		    <logic:present name="<%=Constants.POS_ORDER_LINES%>">
			<logic:iterate indexId="count" name="<%=Constants.POS_ORDER_LINES%>" id="element">
					 
			<%
				String styleClass = "label";
				if ((count.intValue()%2) != 0)
					styleClass = "contentname";
			%>
			<tr>															
				<td class=<%=styleClass%>><bean:write name="element" property="count"/></td>
				<td class=<%=styleClass%>><bean:write name="element" property="productName"/></td>
				<td class=<%=styleClass%>><bean:write name="element" property="qtyOrdered"/></td>
				<td class=<%=styleClass%>><bean:write name="element" property="lineNetAmt"/></td>
				<td class=<%=styleClass%>><bean:write name="element" property="taxAmt"/></td>
				<td class=<%=styleClass%>><bean:write name="element" property="lineTotalAmt"/></td>		
			</tr>								
			</logic:iterate>	 
	   		</logic:present>
	   
			<tr>
				<td align="right" class="label" colspan=3 > 	
					<font size="1pt"><B><bean:message key="orderView.orderPriceDetails.orderTotal"/></font>
				</td>
				<td nowrap>
					<font size="1pt"><B><fmt:formatNumber value='${totalLines}' type="currency" currencySymbol="Rs "/></B></font>
				</td>									  						
				<td nowrap> 		  
					<font size="1pt"><B><fmt:formatNumber value='${orderTax}' type="currency" currencySymbol="Rs "/></B></font>
				</td>
				<td nowrap> 		  
					<font size="1pt"><B><fmt:formatNumber value='${grandTotal}'type="currency" currencySymbol="Rs "/></B></font>
				</td>
			</tr>
			</table>
		</td>
	</tr>
	</table>
	</body>
</html>	 			
		