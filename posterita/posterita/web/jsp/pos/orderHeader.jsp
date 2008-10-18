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



<%@ page import="org.compiere.model.MBPartner" %>
<%@ page import="org.compiere.model.MOrder" %>
<%@ page import="org.posterita.beans.POSHistoryBean" %>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

<table width="100%" cellspacing="0" class="orderheader">
<tr>
	<td width="50%" valign="top">
		<div>
			<bean:write name="<%=Constants.WEB_DOCUMENT_HEADER_BEAN%>" property="to" />
			<c:out value='${me.name}'/>
		</div>
		
		<logic:present name="<%=Constants.ME_LOCATION%>">
		<div>
			<bean:write name="<%=Constants.ME_LOCATION%>"/>
		</div>
		</logic:present>
		
		<div>
			<pos:message key="DocStatus"/>:
			<span class="draftedcolor">
				<dcs:orderStatus name="<%=Constants.MORDER%>" property="docStatus"/>
			</span>
		</div>
		
		<div>
			<logic:present name="<%=Constants.WEB_DOCUMENT_HEADER_BEAN%>" property="paymentType">
			<pos:message key="PaymentRule"/>:	<bean:write name="<%=Constants.WEB_DOCUMENT_HEADER_BEAN%>" property="paymentType"/>
			</logic:present>		
		
			(
			<logic:present name="<%=Constants.PAYMENT_BY_CASH%>">
				<pos:message key="cash"/>: <bean:write name="<%=Constants.PAYMENT_BY_CASH%>"/>
			</logic:present>
					
			<logic:present name="<%=Constants.PAYMENT_BY_CARD%>">
				, <pos:message key="card"/>:	<bean:write name="<%=Constants.PAYMENT_BY_CARD%>"/>
			</logic:present>
			
			<logic:present name="<%=Constants.PAYMENT_BY_CHEQUE%>">
				, <pos:message key="cheque"/>:	<bean:write name="<%=Constants.PAYMENT_BY_CHEQUE%>"/>
			</logic:present>
			)
		</div>
				
		<logic:present name="<%=Constants.WEB_DOCUMENT_HEADER_BEAN%>" property="dealerRef">	
		<div>	
			<bean:write name="<%=Constants.WEB_DOCUMENT_HEADER_BEAN%>" property="dealerRef"/><c:out value='${morder.POReference}'/>
		</div>
		</logic:present>
		
	</td>
	
	<td width="50%" valign="top">
		<div align="right">
			<bean:define id="partner" name="<%=Constants.YOU%>" type="org.compiere.model.MBPartner"/>
			<bean:write name="<%=Constants.WEB_DOCUMENT_HEADER_BEAN%>" property="from"/>			
			<%
				String url = "";
				String partnerId = partner.get_ID() + ""; 
			%>			
			<logic:equal name="<%=Constants.YOU%>" property="customer" value="true">
				<%url = "POSCustomerAction.do?action=viewPOSCustomer&bpartnerId=" + partnerId; %>
			</logic:equal>
			<logic:equal name="<%=Constants.YOU%>" property="customer" value="false">
				<%url = "POSVendorAction.do?action=viewVendorDetails1&bpartnerId=" + partnerId; %>
			</logic:equal>
			<html:link href="<%=url%>">
				<c:out value='${you.name}'/>
				<c:out value='${you.name2}'/>	
			</html:link>
		</div>
		
		<div align="right">
			<logic:present name="<%=Constants.YOU_LOCATION%>">		
				<bean:write name="<%=Constants.YOU_LOCATION%>"/>		
			</logic:present>
		</div>
		
		<div align="right">
			<logic:present name="<%=Constants.SALES_REP%>">
			  	<pos:message key="SalesRep_Name"/>:<bean:write name="<%=Constants.SALES_REP%>"/>
			</logic:present>
		</div>
		
		<div align="right">
			<bean:write name="<%=Constants.WEB_DOCUMENT_HEADER_BEAN%>" property="dcsRef"/>
			<c:out value='${morder.documentNo}'/>
		</div>
		
		<logic:present name="<%=Constants.DESCRIPTION%>">
		<div align="right">
			<pos:message key="Remark"/>: <bean:write name="<%=Constants.DESCRIPTION%>"/>
		</div>
		</logic:present>
		
		<div align="right">
			<fmt:formatDate value='${morder.created}'  type="both" dateStyle="short" timeStyle="medium"/>			
		</div>
		
	</td>
</tr>
</table>
<div class="space"></div>