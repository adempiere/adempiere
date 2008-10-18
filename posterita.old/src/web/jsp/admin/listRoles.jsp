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

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	

<logic:notPresent name="<%=WebUserInfo.NAME%>" scope="session">
	<jsp:forward page="/LoginHome.do"/>
</logic:notPresent>


<tiles:insert page="/jsp/include/headerTableTop.jsp">
  	<tiles:put name="title"><bean:message key="admin.viewRoles.title" /></tiles:put>
</tiles:insert>
	
	<%@ include file="/jsp/include/tabTop.jsp" %><bean:message key="admin.viewRoles.title" /><%@ include file="/jsp/include/tabBottom.jsp" %>
	<table width="100%" border="0" cellpadding="5" cellspacing="1" align="center">
			<tr>
				<td> 
					
				</td>
			</tr>
			
			<tr>
				<td>		
					<%@ include file="/jsp/include/errors.jsp" %> 
					
					<div align="right">
						<html:link href="RoleAction.do?action=initRole">Add Record</html:link>
					</div>				
					
					<table class="display sortable" id="1111">
					<tr>
						<th><bean:message key="role.name"/></th>
						<th><bean:message key="role.isActive"/></th>
						<th>&nbsp;</th>
					</tr>
					<logic:iterate id="element" name="<%= Constants.ALL_ROLES %>">
						<tr>
							<td><bean:write name="element" property="name" /></td>
							<td>
								<bean:write name="element" property="isActive" />						
							</td>
							
							<bean:define id="roleID" name="element" property="roleId"/>
							
							
							<td><%--
								<html:link href="<%= "RoleAction.do?action=viewRole&roleId=" + roleID %>">Edit</html:link>
								--%>
								<html:link href="<%= "RoleAction.do?action=viewRole&roleId=" + roleID %>">View</html:link>
								<%--<html:link href="<%= "RoleAction.do?action=deleteRole&roleId=" + roleID %>">Delete</html:link>--%>								
							</td>
						</tr>
					</logic:iterate>					
					</table>							
				</td>			
						
			</tr>
			
			
		</table>	    		
			 			        					
 							
	    									 					 
<%@ include file="/jsp/include/footerTableBottom.jsp" %>
