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

<bean:define id="title"><pos:message textOnly="true" key="smenu.stock.enquiry.report"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %> 
<%@ include file="/jsp/include/errors.jsp" %>
 
<html:form action="/GetStockEnquiryReport">
<html:hidden property="action" value="getStockEnquiryReport"/>
<div padding="10px">&nbsp;</div>
<table class="display sortable">
<tr>
	<td width="10%">
		<pos:message key="productName"/>
	</td>
	<td>
		 <html:text property="productName"></html:text>
	</td>
</tr>
<tr>
	<td width="10%">
		<pos:message key="description"/> 
	</td>
	<td>
		<html:text  property="description"></html:text>
	</td>
</tr>
</table>
<table>
	<tr>
		<td align="right">
				<html:submit styleClass="smallbutton refresh">&nbsp;</html:submit>
			</td>
	</tr>
</table>
</html:form>
<%@ include file="/jsp/include/posFooter.jsp" %>