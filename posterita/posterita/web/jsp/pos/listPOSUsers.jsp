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



<!-- listPOSUsers.jsp -->
<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.compiere.model.MOrg" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

<bean:define id="title"><pos:message textOnly="true" key="smenu.users"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>


<div align="right">
	<TABLE border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
		
			<td align="left">
				<html:form action="POSUserAction" focus="name">
					<html:hidden property="action" value="listUsers"/>
					<html:text property="name" styleClass="text"/>
				
					<html:submit styleClass="tangoSearch tangoButton">
					&nbsp;
					</html:submit>
				</html:form>
			</td>
			
				
			<td align="right">	
				<html:link href="POSUserAction.do?action=initCreateUser">
					<img src="images/tango/document-new.png" title="Add Customer" alt="Add Customer" border="0">
				</html:link>
			</td>
		</tr>
	</TABLE>
</div>
	 
<logic:present name="<%=Constants.ALL_USERS%>">.
<logic:empty name="<%=Constants.ALL_USERS%>">
	<pos:message key="no.record.found.for" textOnly="true"/>
	<strong><bean:write name="UserForm" property="name"/></strong>
</logic:empty>
<logic:notEmpty name="<%=Constants.ALL_USERS%>">

<table class="display sortable" border="1" id="1111"> 		
	<tr>
		<th><pos:message key="Name"/></th>
		<th><pos:message key="AD_Role_ID"/></th>
		<th><pos:message key="IsSalesRep"/></th>
		<th><pos:message key="IsActive"/></th>
		<th>&nbsp;</th>
	</tr>
	
	<%
		String url = "ListPOSUsers.do";
		String collection = Constants.ALL_USERS;
	%>	
	
	<logic:iterate offset="<%=offset%>" length="<%=length%>" indexId="count" name="<%=collection%>" id="element" type="org.posterita.beans.UserBean">	
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
			
			<td class="<%=styleClass%>" width="100px">
				<bean:write name="element" property="isSalesRep"/>
			</td>
			
			<td class="<%=styleClass%>" width="90px">
				<bean:write name="element" property="isActive"/>
			</td>
			
			<td class="<%=styleClass%>" width="110px">
				<html:link href="<%="POSUserAction.do?action=initEditUser&userId=" + element.getUserId()%>">
					<img src="images/tango/accessories-text-editor.png" title="Edit Customer" alt="Edit Customer" border="0">
				</html:link>


				<html:link href="<%="POSUserAction.do?action=viewUser&userId=" + element.getUserId()%>">
					<img src="images/tango/edit-find.png" title="View Customer" alt="View Customer" border="0">
				</html:link>

				<logic:equal name="element" property="isActive" value="true">
				<html:link href="<%="POSUserAction.do?action=deleteUser&userId=" + element.getUserId()%>">
					<img src="images/tango/edit-redo.png" title="Deactivate User" alt="User Customer" border="0">
				</html:link>
				</logic:equal>

				<logic:notEqual name="element" property="isActive" value="true">
				<html:link href="<%="POSUserAction.do?action=activateUser&userId=" + element.getUserId()%>">
					<img src="images/tango/edit-undo.png" title="Activate User" alt="Activate User" border="0">
				</html:link>
				</logic:notEqual>
			</td>
		</tr>
	</logic:iterate>					
	
</table>				
<%@ include file="/jsp/include/pager.jsp" %>						

</logic:notEmpty>
</logic:present>

						
<%@ include file="/jsp/include/posFooter.jsp" %>    
	
												