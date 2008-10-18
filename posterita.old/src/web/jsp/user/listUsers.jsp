<%--
 *  Product: Posterita Web-Based POS (an Adempiere Plugin)
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
 * @author Vishee
--%>

<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.compiere.model.MOrg" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<tiles:insert page="/jsp/include/headerTableTop.jsp">
  <tiles:put name="title"><bean:message key="admin.userDetails.title"/></tiles:put>
</tiles:insert>
	 
		<table width="100%" border="0" cellpadding="5" cellspacing="0">
	   		<tr>
	    		<td>
					<%@ include file="/jsp/include/tabTop.jsp" %>All Users<%@ include file="/jsp/include/tabBottom.jsp" %>   								
		   		</td>
		   	</tr>
		   							
		   	<tr>
		   		<td>
		   			<table width="100%" border="0" cellpadding="5" cellspacing="1" align="center" cols="5"> 		  		
					<tr>
						<td colspan="5" align="right"><html:link href="UserAction.do?action=initCreateUser">Add Record</html:link></td>
					</tr>	
					<tr>
						<th>Username</th>
						<th>Role</th>
						<th>Is Sale Representative</th>
						<th>Is Active</th>
						<th>&nbsp;</th>
					</tr>
					<logic:iterate indexId="count" name="<%=Constants.ALL_USERS%>" id="element" type="org.posterita.beans.UserBean">	
						<%
							String styleClass = "label";
							
							if ((count.intValue()%2) != 0)
								styleClass = "contentname";
						%>
						<tr>
							<td class="<%=styleClass%>">
								<bean:write name="element" property="username"/>
							</td>
							
							<td class="<%=styleClass%>">
								<bean:write name="element" property="roleName"/>
							</td>
							
							<td class="<%=styleClass%>">
								<bean:write name="element" property="isSalesRep"/>
							</td>
							
							<td class="<%=styleClass%>">
								<bean:write name="element" property="isActive"/>
							</td>
							
							<td class="<%=styleClass%>">
								<html:link href="<%="UserAction.do?action=initEditUser&userId=" + element.getUserId()%>">Edit</html:link>
								&nbsp;
								<html:link href="<%="UserAction.do?action=viewUser&userId=" + element.getUserId()%>">View</html:link>
								&nbsp;
								<logic:equal name="element" property="isActive" value="true">
								<html:link href="<%="UserAction.do?action=deleteUser&userId=" + element.getUserId()%>">Deactivate</html:link>
								</logic:equal>
								<logic:notEqual name="element" property="isActive" value="true">
								<html:link href="<%="UserAction.do?action=activateUser&userId=" + element.getUserId()%>">Activate</html:link>
								</logic:notEqual>
							</td>
						</tr>
					</logic:iterate>					
					
					</table>
				</td>
			</tr>				
		</table>			
						
		<%@ include file="/jsp/include/footerTableBottom.jsp" %>	   
	
												