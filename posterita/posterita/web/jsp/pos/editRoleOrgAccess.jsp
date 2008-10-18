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

<!-- editRole.jsp -->
<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.beans.UDIBean" %>
<%@ page import="org.posterita.core.MenuItem" %>
<%@ page import="org.posterita.beans.RoleBean" %>


<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

<bean:define id="title"><pos:message textOnly="true" key="Role"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %>

<html:form action="/EditRoleOrgAccessAction">
<html:hidden property="action" value="editRoleOrgAccess"/>

<%
	RoleBean roleBean = (RoleBean)request.getSession().getAttribute(Constants.ROLE);
	String role = roleBean.getName();
%>	
		
<table class="display" border="1">
   <tr>
   		<td align="left" class="contentName">
   			<strong><pos:message key="Name"/></strong>
   		</td>
   		<td  class="contentName"><html:text property="name" styleClass="text" value="<%=role%>" disabled="true"/></td>
   </tr>
   
</table>

<table class="main">	
	<tr>
		<td>
		<label class="green">Role Organisation Access</label>
			<table class="display" border="1">
			   <logic:present name="<%= Constants.ROLE_ORG_ACCESS_LIST%>">	
			   
				   <logic:iterate indexId="count" name="<%=Constants.ROLE_ORG_ACCESS_LIST%>" id="roleOrgAccessIndexed" type="org.posterita.beans.RoleBean">		

								<%
									String checked1 = "";
									if(((RoleBean)roleOrgAccessIndexed).getIsChecked().booleanValue())
										checked1 = "checked";
								%>
								<tr>
									<td>
										<bean:write name="roleOrgAccessIndexed" property="orgName"/>
									</td>
									<td>
										<html:hidden name="roleOrgAccessIndexed" property="name" value="<%=roleOrgAccessIndexed.getName()%>" indexed="true"/>
										<html:hidden name="roleOrgAccessIndexed" property="roleId" value="<%=roleOrgAccessIndexed.getRoleId().toString()%>" indexed="true"/>
										<html:hidden name="roleOrgAccessIndexed" property="orgId" value="<%=roleOrgAccessIndexed.getOrgId().toString()%>" indexed="true"/>										

										<html:checkbox name="roleOrgAccessIndexed" property="isChecked" value="true" indexed="true"/>
										
									</td>
								</tr>
							
					</logic:iterate>
				</logic:present> 
			</table>
		</td>
	</tr>
					
	 
	<tr align="right">
		<td colspan="2">
			<html:button styleClass="bigbtn" onclick="clearAll(this.form)" property="button">
				Clear All
			</html:button>
			<html:button styleClass="bigbtn" onclick="selectAll(this.form)" property="button">
				Select All
			</html:button>
			<html:submit styleClass="cntbtn">
 		                       <bean:message key="button.save"/>
             </html:submit>  
		</td>
	</tr>
</table>				

</html:form>    									 					 
<%@ include file="/jsp/include/posFooter.jsp" %>