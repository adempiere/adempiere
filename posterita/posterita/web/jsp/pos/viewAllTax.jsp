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
 * @author Alok
--%>


<!-- viewAllTax.jsp -->
<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.compiere.model.MOrg" %>
<%@ page import="org.posterita.struts.pos.TaxAction" %>


<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

<bean:define id="title"><pos:message textOnly="true" key="C_Tax_ID"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %>

	 
<div align="right">
	<html:link href="CreateTax.do">
		<img src="images/tango/document-new.png" title="Add Payment Term" alt="Add Payment Term" border="0">
	</html:link>
</div>

<table class="display sortable" border="1" id="1111"> 		
	<tr>
		<th align="left"><pos:message key="C_Tax_ID"/></th>
		<th><pos:message key="Description"/></th>
		<th><pos:message key="Rate"/></th>
		<th><pos:message key="IsTaxExempt"/></th>
		<th><pos:message key="IsActive"/></th>
		<th>&nbsp;</th>
	</tr>
	<logic:iterate indexId="count" name="<%=Constants.ALL_TAX_RATE%>" id="element" type="org.posterita.beans.TaxBean">	
		<%
			String styleClass = "label";
			
			if ((count.intValue()%2) != 0)
				styleClass = "contentname";
		%>
		<tr>
			<td class="<%=styleClass%>">
				<bean:write name="element" property="taxName"/>
			</td>
			
			<td class="<%=styleClass%>" width="15%" align="center">
				<bean:write name="element" property="description"/>
			</td>
			<td class="<%=styleClass%>" width="10%" align="center">
				<bean:write name="element" property="taxRate"/>
			</td>
			<td class="<%=styleClass%>" width="10%" align="center">
				<bean:write name="element" property="isTaxExempted"/>
			</td>
			
			<td class="<%=styleClass%>" width="10%" align="center">
				<bean:write name="element" property="isActive"/>
			</td>
			
			<td class="<%=styleClass%>" width="10%" align="center">
				<html:link href="EditTaxAction.do?action=initEditTax&taxId=<%= element.getTaxId() %>">
					<img src="images/tango/accessories-text-editor.png" title="Edit" alt="Edit" border="0">
				</html:link>
				&nbsp;
				
				<logic:equal name="element" property="isActive" value="true">
					<html:link href="ActivateDeactivateTaxAction.do?action=deActivateTax&taxId=<%= element.getTaxId() %>">
						<img src="images/tango/edit-redo.png" title="Deactivate" alt="Deactivate" border="0">
					</html:link>
				</logic:equal>

				<logic:notEqual name="element" property="isActive" value="true">
					<html:link href="ActivateDeactivateTaxAction.do?action=activateTax&taxId=<%= element.getTaxId() %>">
						<img src="images/tango/edit-undo.png" title="Activate" alt="Activate" border="0">
					</html:link>
				</logic:notEqual>
			</td>
		</tr>
	</logic:iterate>					
	
</table>
					
						
<%@ include file="/jsp/include/posFooter.jsp" %>    
	
												