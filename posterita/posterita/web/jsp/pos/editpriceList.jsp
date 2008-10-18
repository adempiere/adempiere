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


<!-- editpriceList.jsp -->
<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.beans.*" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

<logic:notPresent name="<%=WebUserInfo.NAME%>" scope="session">
	<jsp:forward page="/POSLogin.do"/>
</logic:notPresent>

<bean:define id="title"><pos:message key="edit.price.list" textOnly="true" /></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %> 

<table class="view">
<html:form action="/EditPOSPriceListAction">
<html:hidden property="action" value="editPriceList"/>				
	<tr>
		<td><label><pos:message key="new.price"/></label></td>
		<td><html:text property="price" styleClass="text"/></td>
	</tr>
	<tr>
		<td colspan="2">
			<html:submit styleClass="save smallbutton">&nbsp;</html:submit>
		</td>
	</tr>
</html:form>
</table>
<%@include file="/jsp/include/posFooter.jsp"%>		