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



<!-- listRoles.jsp -->
<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.beans.*" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

<bean:define id="title"><pos:message textOnly="true" key="AD_Role_ID"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>		
<%@ include file="/jsp/include/errors.jsp" %> 


<div align="right">
	<TABLE border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td align="left">
				<html:form action="POSRoleAction" focus="name">
					<html:hidden property="action" value="listRoles"/>
					<html:text property="name" styleClass="text"/>
				
					<html:submit styleClass="tangoSearch tangoButton">
					&nbsp;
					</html:submit>
				</html:form>
			</td>
			
				
			<td align="right">	
				<html:link href="POSRoleAction.do?action=initRole">
					<img src="images/tango/document-new.png" title="Add Customer" alt="Add Customer" border="0">
				</html:link>
			</td>
		</tr>
		
	</TABLE>
</div>

<logic:present name="<%= Constants.ALL_ROLES %>">
<logic:empty name="<%=Constants.ALL_ROLES%>">
	<pos:message key="no.record.found.for" textOnly="true"/>
	<strong><bean:write name="DefaultForm" property="name"/></strong>
</logic:empty>
<logic:notEmpty name="<%= Constants.ALL_ROLES %>">

<table class="display sortable" border="1" id="1111">
<tr>
	<th><pos:message key="AD_Role_ID"/></th>
	<th><pos:message key="userDiscount"/></th>
	<th><pos:message key="IsActive"/></th>
	<th>&nbsp;</th>
</tr>
<%
	String url = "ListPOSRoles.do";
	String collection = Constants.ALL_ROLES;
%>
<logic:iterate offset="<%=offset%>" length="<%=length%>" id="element" indexId="count" name="<%= collection %>">
	<%
		String styleClass = "label";
		
		if ((count.intValue()%2) != 0)
			styleClass = "contentname";
	%>
	<tr>
		<td class="<%=styleClass%>"><bean:write name="element" property="name" /></td>
		<td class="<%=styleClass%>"><bean:write name="element" property="userDiscount"/></td>
		<td class="<%=styleClass%>" align="center">
			<bean:write name="element" property="isActive" />
			<bean:define id="roleID" name="element" property="roleId"/>						
			<bean:define id="name" name="element" property="name"/>	
		</td>
		<td class="<%=styleClass%>" width="120px">
			<html:link href="POSRoleAction.do?action=initEditRole&roleId=<%= roleID %>">
				<img src="images/tango/accessories-text-editor.png" title="Edit Role" alt="Edit Role" border="0">
			</html:link>

			<html:link href="POSRoleAction.do?action=viewRole&roleId=<%= roleID %>">
				<img src="images/tango/edit-find.png" title="View Role" alt="View Role" border="0">
			</html:link>
			
			<html:link href="RoleOrgAccessAction.do?action=initEditRoleOrgAccess&roleId=<%= roleID %>&name=<%= name %>">
				<img src="images/tango/edit-find.png" title="Edit Organisation Access" alt="Edit Organisation Access" border="0">
			</html:link>			
			
			<%--<html:link href="POSRoleAction.do?action=deleteRole&roleId=<%= roleID %>">Delete</html:link>--%>								
		</td>
	</tr>
</logic:iterate>					
</table>							

<%@ include file="/jsp/include/pager.jsp" %>								 					 

</logic:notEmpty>
</logic:present>

								 					 
<%@ include file="/jsp/include/posFooter.jsp" %>
