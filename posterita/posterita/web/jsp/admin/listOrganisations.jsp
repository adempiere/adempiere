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
 *  @author sendy
--%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>	
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

<%@page import="org.posterita.struts.admin.OrganisationAction"%>
<%@page import="org.posterita.Constants"%>
<bean:define id="title">Organisations</bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %>
<!-- page contents -->
<table width = "100%">
	<tr>
		<td>
			<html:form action="/OrganisationAction">
				<html:hidden property="action" value="<%=OrganisationAction.LIST_ORGS%>"/>
				<html:text property="orgName"></html:text>
				<html:submit></html:submit>
			</html:form>
		</td>
	
		<td align = "right"><html:link href="<%="UpdateOrCreateOrg.do"%>">
					<img src="images/tango/document-new.png" title="Create Organisation" alt="Create Organisation" border="0">
				</html:link>
		</td>
	</tr>
</table>

<div class="scrollpane">
	<table class="display sortable" border="1" width="900px" >
		<tr>
			<th class="string"><span title="Name" name="help" tooltip="Name">Organisation Name</span></th>
		    <th class="string" nowrap="nowrap"><span title="Address" name="address" tooltip="Address">Address</span></th>
	   	    <th class="string" nowrap="nowrap"><span title="Active" name="help" tooltip="Active">Active</span></th>   
	   	    <th nowrap="nowrap"><span title="Update Details " name="help" tooltip="Update Details ">Update Details </span></th>
		</tr> 	
	
		<logic:present name="<%=Constants.ALL_ORGS %>">

		<logic:iterate id="org" name="<%=Constants.ALL_ORGS %>" type="org.posterita.beans.OrgBean">
		<tr>
			<td class = "label">
				<bean:write name="org" property="orgName"/>
			</td>
			<td class = "label">
				<bean:write name="org" property="address1"/>
				<bean:write name="org" property="address2"/>
			</td>
			<td class = "label">
				<bean:write name="org" property="isActive"/>
			</td>
			<td  align="center" class = "label">
				<html:link href="<%="OrganisationAction.do?action=viewOrg&orgId=" + org.getOrgId()%>">
					<img src="images/tango/accessories-text-editor.png"  border="0" 
					title = "update organisation" alt = "update organisation"> 
				</html:link>
				<html:link href="<%="OrganisationAction.do?action=activateOrg&orgId=" + org.getOrgId()%>">
					<img src="images/tango/edit-redo.png"  border="0"
					title = "activate/deactivate organisation" alt = "activate/deactivate organisation"> 
				</html:link>
		 	</td>	 	
		</tr>
		</logic:iterate>

		</logic:present>
	</table>
</div>

<%@ include file="/jsp/include/posFooter.jsp" %>