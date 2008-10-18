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
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>	
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>
<%@page import="org.posterita.Constants"%>
<%@page import="org.posterita.struts.pos.POSReportAction"%>
<%@page import="org.posterita.Constants"%>

<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %>


<html:form action="/GenerateReportAction">

<html:hidden property="action" value="<%=POSReportAction.GENERATE_REPORT_OUTPUT%>"/>
<logic:present name="<%=Constants.REPORT_DESCRIPTION%>">
	<bean:define id="reportName" name="<%=Constants.REPORT_DESCRIPTION%>"/>
	<div style="padding:5px">&nbsp;</div>
	<div><font class="pagetitle"><%=reportName%></font><div>
<div style="padding:5px">&nbsp;</div>
</logic:present>
<logic:present name="<%=Constants.REPORT_COMMENT%>">
	<bean:define id="reportComment" name="<%=Constants.REPORT_COMMENT%>"/>
	<div><font class="headertitle"><%=reportComment%></font><div>
	<div style="padding:10px">&nbsp;</div>
</logic:present>
<logic:present name="<%=Constants.WEB_COMPONENTS%>">
	<bean:define id="componentList" name="<%=Constants.WEB_COMPONENTS%>"/>
	<div id="content">
	<table class="content" border="0" width="50%">
			<%=componentList %>
	</table>
</logic:present>
<div style="padding:10px">&nbsp;</div>
<table class="content">
	<tr>
		<td>
			<span><font class="headertitle">Report As:</span>
			<select name="reportType">
				<option value="<%=Constants.PDF%>"><pos:message key="pdf"/></option>
				<option value="<%=Constants.CSV%>"><pos:message key="csv"/></option>
				<option value="<%=Constants.HTML%>"><pos:message key="html"/></option>
			</select>
			<html:submit styleClass="button">Generate</html:submit>
		</td>		
	</tr>
</table>
	
</div>
</html:form>
<%@ include file="/jsp/include/posFooter.jsp" %>