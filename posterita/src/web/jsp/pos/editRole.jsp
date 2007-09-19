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

<bean:define id="title"><pos:element textOnly="true" columnName="Role"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %>

<html:form action="/EditPOSRoleAction">
<%
	RoleBean roleBean = (RoleBean)request.getSession().getAttribute(Constants.ROLE);
	String roleName = roleBean.getName();
	String roleId = String.valueOf(roleBean.getRoleId().intValue());
	boolean isAccAllOrgs = roleBean.getIsAccessAllOrgs().booleanValue();
	String accAllOrgs = "";
	if(isAccAllOrgs)
		accAllOrgs = "checked=\"checked\"";
	
%>
<html:hidden property="action" value="editRole"/>
<html:hidden property="roleId" value="<%= roleId%>"/>
		
<table class="display" border="1">
   <tr>
   		<td align="left" class="contentName">
   			<strong><pos:element columnName="Name"/></strong>
   		</td>
   		<td  class="contentName"><html:text property="name" styleClass="text" value="<%= roleName%>"/></td>
   </tr>
   
<%--
   <tr>
   		<td align="left"><pos:element columnName="IsAccessAllOrgs"/></td>
   		<td><html:checkbox property="isAccessAllOrgs" name="<%=Constants.ROLE%>" value="true"/></td>
   </tr>
--%>
   
</table>
<table class="main">	
	<tr>
		<td>
		<label class="green"><pos:element columnName="available.menu"/></label>
			<table class="display" border="1">
			   <logic:present name="<%= Constants.ROLE_MENUS%>">	
				   <logic:iterate id="element" indexId="count" name="<%= Constants.ROLE_MENUS%>" type="org.posterita.core.MenuItem">								   	
					   <logic:equal name="element" property="hasSubMenu" value="true">
						   <tr>
						   		<td class="contentName" colspan="2">
		   							<strong><pos:element columnName="<%=element.getName()%>" textOnly="true"/></strong>
		   						</td>				   		
						   </tr>				   
							<logic:iterate id="ele" collection="<%=element.getSubMenus()%>" type="org.posterita.core.MenuItem">
								<%
									String checked = "";
									if(((MenuItem)ele).isAvailable())
										checked = "checked=\"checked\"";
								%>
								<tr>
									<td>
										<pos:element columnName="<%=ele.getName()%>"  textOnly="true"/>
									</td>
									<td>
										<input type="checkbox" name="checkBox" value="<bean:write name='ele' property='menuId'/>" <%= checked%>/>
									</td>
								</tr>
							</logic:iterate>							   						
						</logic:equal>	
						<logic:equal name="element" property="hasSubMenu" value="false">
						   <tr>
						   		<td class="contentName" colspan="2">
		   							<strong><pos:element columnName="<%=element.getName()%>" textOnly="true"/></strong>
		   						</td>				   		
						   </tr>				   
							<tr>
								<td>
									<pos:element columnName="<%=element.getName()%>" textOnly="true"/>
								</td>
								<td>
									<!-- N.B: Should always keep the following box disabled -->
									<input type="checkbox" name="checkBox" value="<bean:write name='element' property='menuId'/>"  checked="checked" disabled="disabled"/>
								</td>
							</tr>
						</logic:equal>
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