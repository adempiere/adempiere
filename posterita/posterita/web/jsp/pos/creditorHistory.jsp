<%--
 *  Product: Posterita Web-Based POS and Adempiere Plugin
 *  Copyright (C) 2008  Posterita Ltd
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
--%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>	
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>
<%@page import="org.compiere.model.MBPartner"%>
<%@page import="org.posterita.core.TmkJSPEnv"%>
<%@page import="java.util.Properties"%>

<%@ include file="/jsp/include/posHeader.jsp" %>
<!-- page contents -->

<%@page import="org.posterita.businesslogic.administration.BPartnerManager"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="org.posterita.Constants"%>
<%
BPartnerBean bean = (BPartnerBean)request.getSession().getAttribute(Constants.BPARTNER);
int c_bpartner_id = bean.getBpartnerId().intValue();
MBPartner partner = MBPartner.get(ctx, c_bpartner_id);
String bpName = partner.getName();

if(partner.getName2() != null)
{
	bpName = bpName + " " + partner.getName2();
	bpName = bpName.trim();
}
%>

<%@page import="org.posterita.beans.BPartnerBean"%>
<div style="padding:8px">
<h2><pos:message key="creditor.history"/></h2>
<pos:message key="bpartner"/> : <strong><%=bpName.toUpperCase()%></strong>
<br><br>
<table class="display" border="1">
	<tr>
		<th><pos:message key="Date"/></th>
		<th><pos:message key="Ref"/></th>
		<th><pos:message key="Details"/></th>
		<th><pos:message key="Debit"/></th>
		<th><pos:message key="Credit"/></th>
		<th><pos:message key="Balance"/></th>
	</tr>
	<logic:notEmpty name="<%= Constants.CREDITOR_HISTORY %>">
	<logic:iterate id="element" name="<%= Constants.CREDITOR_HISTORY %>" type="org.posterita.beans.StatementOfAccountBean">
	<tr>
		<td><bean:write name="element" property="date" format="dd/MM/yyyy"/></td>
		<td>
			<logic:equal value="Order" name="element" property="docType">
			<html:link action="<%="/ViewPOSOrderAction.do?action=viewPOSOrders&orderId=" + element.getDocId()%>"><bean:write name="element" property="reference"/></html:link>
			</logic:equal>
			
			<logic:equal value="Payment" name="element" property="docType">
			<html:link action="<%="/ViewWebstorePaymentAction.do?action=viewOrder&documentId=" + element.getDocId()%>"><bean:write name="element" property="reference"/></html:link>
			</logic:equal>
			
			<logic:equal value="CashLine" name="element" property="docType">
			<html:link action="<%="/ViewWebstoreCashPaymentAction.do?action=viewOrder&documentId=" + element.getDocId()%>"><bean:write name="element" property="reference"/></html:link>
			</logic:equal>
		</td>
		<td><bean:write name="element" property="details"/></td>
		<td><bean:write name="element" property="credit" format="0.00"/></td>
		<td><bean:write name="element" property="debit" format="0.00"/></td>
		<td><bean:write name="element" property="balance" format="0.00"/></td>
	</tr>
	</logic:iterate>
	</logic:notEmpty>
</table>
</div>	
<%@ include file="/jsp/include/posFooter.jsp" %>