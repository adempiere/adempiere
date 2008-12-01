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

<!--customPOSInfoReport.jsp-->
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

<bean:define id="title"><pos:message textOnly="true" key="smenu.sales.report.per.terminal"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %> 
<%@ include file="/jsp/include/errors.jsp" %>
 
<html:form action="/CustomPOSInfoAction">
<html:hidden property="action" value="getCustomReport"/>

<% String data = (String) request.getSession().getAttribute(Constants.TABULAR_REPORT_DATA) ;%>

<%@ include file="/jsp/pos/customReportTable.jsp" %>

<!-- <table width="100%" border="0">
	<tr>
		<td colspan="3" valign="top">
		<div>					
			<% if (data != null)
			
			{
			%>
			
			
				<bean:define id="pdfFile" name="<%=Constants.PDF_FILE%>"/>
				<p align="right">
					<bean:define id="pdfFile" name="<%=Constants.PDF_FILE%>"/>
					<a href="<%=pdfFile%>">
						<html:button property="btn" styleClass="saveaspdf smallbutton">&nbsp;</html:button>
					</a>
				</p>

			<% 
			}
			%>
		</div>	
		</td>
	</tr>			
</table>				
 -->
</html:form>
<%@ include file="/jsp/include/posFooter.jsp" %>