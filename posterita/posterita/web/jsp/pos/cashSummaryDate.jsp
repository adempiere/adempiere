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
 * @author Ashley
--%>

<!-- cashSummaryDate.jsp -->
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.beans.ProductBean" %>
<%@ page import="org.posterita.user.WebUserInfo" %>
<%@ page import="org.posterita.lib.UdiConstants" %>
<%@ page import="org.posterita.struts.pos.POSReportAction" %>
<%@ page import="org.posterita.struts.pos.POSGoodsAction" %>
<%@ page import="org.compiere.process.DocAction" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

<%
	String requestURI = "CashSummaryReportAction.do";
%>

<logic:notPresent name="<%=WebUserInfo.NAME%>" scope="session">
	<jsp:forward page="/LoginHome.do"/>
</logic:notPresent>

<bean:define id="title"><pos:message textOnly="true" key="smenu.cashbook.report"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %> </td>

<table class="display">
	<html:form action="/CashSummaryReportAction">
	<html:hidden property="action" value="getCashSummaryByDate"/>
	<html:hidden property="fromDate" name="DateFilterForm"/>
	<html:hidden property="toDate" name="DateFilterForm"/>
	<tr>
		<td>
			<%@ include file="/jsp/include/dateFilter.jsp" %>
		</td>
	</tr>
	<tr>
		<td align="center">
			<html:button property="btn" styleClass="submit smallbutton" onclick="validateDate(),submit()">&nbsp;</html:button>
		</td>
	</tr>
	<tr>
		<td>
			<%@ include file="/jsp/pos/cashSummaryInc.jsp"%>
		</td>
	</tr>
	</html:form>
</table>
<%@ include file="/jsp/include/posFooter.jsp" %>
<script>
	$FElement('startHour').disabled = 'true';
	$FElement('startMinute').disabled = 'true';
	$FElement('endHour').disabled = 'true';
	$FElement('endMinute').disabled = 'true';
</script>
<script language="javascript">
<!--
	function validateDate()
	{
		var myDate = new Date();
		var y = myDate.getFullYear();
		var d = document.DateFilterForm;
	}
//-->
</script>