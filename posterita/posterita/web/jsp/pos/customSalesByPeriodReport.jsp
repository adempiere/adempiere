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


<%@ include file="/jsp/include/posHeader.jsp" %>	   
<%@ include file="/jsp/include/errors.jsp" %> 
<div class="spacingSubmenu"/>
	<pos:message key="select"/>:<br>
	<html:link action="/CustomSalesReportAction.do?action=initCustomSalesReport&timePeriod=<%= ReportDateManager.TODAY %>" styleClass="submenu"><pos:message key="today"/></html:link><br>					
	<html:link action="/CustomSalesReportAction.do?action=initCustomSalesReport&timePeriod=<%= ReportDateManager.CURRENT_WEEK %>" styleClass="submenu"><pos:message key="current.week"/></html:link><br>							
	<html:link action="/CustomSalesReportAction.do?action=initCustomSalesReport&timePeriod=<%= ReportDateManager.LAST_2WEEKS %>" styleClass="submenu"><pos:message key="last.2.weeks"/></html:link><br>						
	<html:link action="/CustomSalesReportAction.do?action=initCustomSalesReport&timePeriod=<%= ReportDateManager.LAST_3WEEKS %>" styleClass="submenu"><pos:message key="last.3.weeks"/></html:link><br>
	<html:link action="/CustomSalesReportAction.do?action=initCustomSalesReport&timePeriod=<%= ReportDateManager.CURRENT_MONTH %>" styleClass="submenu"><pos:message key="current.month"/></html:link><br>
	<html:link action="/CustomSalesReportAction.do?action=initCustomSalesReport&timePeriod=<%= ReportDateManager.LAST_3MONTHS %>" styleClass="submenu"><pos:message key="last.3.months"/></html:link><br>
	<html:link action="/CustomSalesReportAction.do?action=initCustomSalesReport&timePeriod=<%= ReportDateManager.LAST_6MONTHS %>" styleClass="submenu"><pos:message key="last.6.month"/></html:link><br>
	<html:link action="/CustomSalesReportAction.do?action=initCustomSalesReport&timePeriod=<%= ReportDateManager.CURRENT_YEAR %>" styleClass="submenu"><pos:message key="current.year"/></html:link><br>
	<html:link action="/CustomSalesReport.do" styleClass="submenu"><pos:message key="custom"/></html:link><br>
		    									 					 
<%@ include file="/jsp/include/posFooter.jsp" %>
