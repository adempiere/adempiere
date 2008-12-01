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
<%
	String isSalesReport = request.getParameter("isSalesReport");
	String pageTitle = "smenu.sales.report";
	String reportDetailsStyle = "display: block";
	
	boolean isSalesRPT = Boolean.parseBoolean(isSalesReport);
	
	if(!(isSalesRPT))
	{
		pageTitle = "smenu.purchase.report";
		reportDetailsStyle = "display:none";
	}
%>
<!--salesReport.jsp-->
<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.beans.*" %>
<%@ page import="org.posterita.struts.pos.POSOrderAction" %>
<%@ page import="org.posterita.businesslogic.performanceanalysis.ReportDateManager" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

<logic:notPresent name="<%=WebUserInfo.NAME%>" scope="session">
	<jsp:forward page="/LoginHome.do"/>
</logic:notPresent>

<bean:define id="title"><pos:message textOnly="true" key="<%=pageTitle%>"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %> 
<%@ include file="/jsp/include/errors.jsp" %>
 
<html:form action="/SalesReportAction">
<html:hidden property="action" value="getSalesOrPurchaseReport"/>
<input type="hidden" id="isSalesReport" value="<%= isSalesReport %>" name="isSalesReport"/>
<table width="100%" border="0">
	<tr>		
		<td valign="top">
			<fieldset style="<%=reportDetailsStyle%>">
			<legend><pos:message key="report.details"/></legend>
			<html:select property="fullDetails" onchange="setFilter()">
				<html:option value="<%=Constants.BASIC_DETAILS%>"><pos:message key="basic"/></html:option>
				<html:option value="<%=Constants.FULL_DETAILS%>"><pos:message key="full"/></html:option>
			</html:select>
			</fieldset>
		</td>
	</tr>
</table>
<%@ include file="/jsp/pos/customReportTable.jsp" %>

</html:form>
<%@ include file="/jsp/include/posFooter.jsp" %>