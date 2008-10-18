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
 *  @author sendy
--%>
<!-- viewMMovementHistory.jsp -->
<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.compiere.model.MOrg" %>
<%@ page import="org.compiere.process.DocumentEngine" %>
<%@ page import="org.posterita.struts.stock.StockMovementAction" %>
<%@ page import="org.compiere.process.DocAction" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<%@page import="org.posterita.core.Configuration"%>
<%@page import="org.compiere.util.Env"%>
<%@page import="org.compiere.model.MPriceListVersion"%>
<%@page import="org.posterita.lib.UdiConstants"%>
<%@page import="org.posterita.core.TmkJSPEnv"%>
<%@page import="java.util.Properties"%>
<%@page import="org.posterita.businesslogic.administration.PriceListManager"%>
<bean:define id="title"><pos:message key="M_Movement_ID" textOnly="true"/> History</bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %> 
<%
	
%>

<html:form action="/StockMovementAction" method="get">
<html:hidden property="action" value="viewMMovementHistory"/>
<table>

<tr>
	<td align="right">
		<label class="greencolor"><pos:message key="filter.by"/>:</label>
	</td>
	
	<td width="3%">
		<label><pos:message key="DocStatus"/></label>
	</td>
	
	<td width="3%">
		<label><pos:message key="month"/></label>
	</td>
	
	<td width="3%">
		<label><pos:message key="year"/></label>						
	</td>
	
	<logic:present name="<%=Constants.PAYMENT_RULES%>">
	<td width="3%">
		<label><pos:message key="PaymentRule"/></label>
	</td>	
	</logic:present>
										
</tr>

<tr>
	<td>
		&nbsp;
	</td>
	
	<td align="right">
		<html:select property="docStatus" onchange="submit()">
			<html:option value=""><pos:message key="all"/></html:option>	
				<html:option value="<%= DocAction.STATUS_Drafted%>"><pos:message key="drafted"/></html:option>
				<html:option value="<%= DocAction.STATUS_InProgress%>"><pos:message key="inprogress"/></html:option>
				<html:option value="<%= DocAction.STATUS_Completed%>"><pos:message key="completed"/></html:option>
				<html:option value="<%= DocAction.STATUS_Closed%>"><pos:message key="closed"/></html:option>
				<html:option value="<%= DocAction.STATUS_Voided%>"><pos:message key="Void"/></html:option>
				<html:option value="<%= DocAction.STATUS_Invalid%>"><pos:message key="invalid"/></html:option>
		</html:select>
	</td>
	
	<%@ include file="/jsp/include/historyMonthYearFilter.jsp"%>		

	<logic:present name="<%=Constants.PAYMENT_RULES%>">						
	<td align="right">
		<html:select property="paymentRule" onchange="submit()">
			<html:option value=""><pos:message key="all"/></html:option>	
			<logic:present name="<%=Constants.PAYMENT_RULES%>">
			<%@include file="/jsp/include/paymentRuleFilter.jsp"%>							
			</logic:present>
   		</html:select>																									
	</td>
	</logic:present>

</tr>
</table>
</html:form>

<display:table id="row" name="materialMovementList" class="displaytag" defaultsort="1" export="true" excludedParams="delete" defaultorder="descending" pagesize="<%=pageSize.intValue() %>"
			requestURI="StockMovementAction.do" sort="page" decorator="org.posterita.decorator.InventoryMoveHistoryWrapper">
	<display:column property="documentNo" titleKey="DocumentNo" sortable="true"/>
	<display:column property="description" titleKey="reference" sortable="true"/>
	<display:column property="movementDate" titleKey="movementDate" sortable="true"/>
	<display:column property="isActive" titleKey="isActive" sortable="true"/>
	<display:column property="isApproved" titleKey="isApproved" sortable="true"/>
	<display:column property="docStatus" titleKey="status" sortable="true"/>
	<display:column property="documentType" titleKey="documentType" sortable="true"/>
	<display:column property="edit" titleKey="edit" sortable="false"/>
	<display:column property="delete" titleKey="delete" sortable="true"/>
</display:table>

<%@ include file="/jsp/include/posFooter.jsp" %>