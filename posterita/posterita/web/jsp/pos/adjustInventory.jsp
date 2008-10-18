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

<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.compiere.model.MOrg" %>
<%@ page import="org.posterita.struts.pos.InventoryAction" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos" %>

<bean:define id="title"><pos:message key="W_InvActualAdjust_Acct" textOnly="true"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %> 
<html:form action="CompleteAdjustStockAction">
<html:hidden property="action" value="completeInventory"/>
	<logic:present name="<%=Constants.WHOLE_INVENTORY_LINE_LIST%>">
	<logic:notEmpty name="<%=Constants.WHOLE_INVENTORY_LINE_LIST%>">
			<table class="display sortable scrollpane" border="1" id="vendor"> 		  		
				<tr>
					<th><pos:message key="ProductName"/></th>		
					<th><pos:message key="QtyBook"/></th>
					<th><pos:message key="QtyCount"/></th>
					<th>&nbsp;</th>
				</tr>
				
			<logic:iterate length="10" indexId="count" name="<%=Constants.WHOLE_INVENTORY_LINE_LIST%>" id="element" type="org.posterita.beans.InventoryBean" >	
			<%
				String styleClass = "label";
				
				if ((count.intValue()%2) != 0)
					styleClass = "contentname";
			%>
			
			<form>
			<tr>
				<td class="<%=styleClass%>">
					<bean:write name="element" property="productName"/>
					<html:hidden property="inventoryLineId" value="<%=element.getInventoryLineId().toString()%>"/>
					<html:hidden property="inventoryId" value="<%=element.getInventoryId().toString()%>"/>
				</td>	
				
				<td class="<%=styleClass%>">
					<bean:write name="element" property="qtyBook"/>
				</td>
				<td class="<%=styleClass%>">
					<html:text name="element" property="qtyCount"/>
				</td>
				<td class="<%=styleClass%>">
					<html:button property="btn" onclick="saveQty(this.form)">
					Save
					</html:button>
				</td>
			
			</tr>
			</form>
			
			</logic:iterate>
			<td colspan="4" align="right">
				<html:submit >
					Complete
				</html:submit>
			 </td>  
			</table>
			
	</logic:notEmpty>
	</logic:present>
				
</html:form>	
<script>
function saveQty(form)
{
	this.form = form;
	var action = 'AdjustWholeInventoryAction.do?action=saveCountQty&inventoryLineId=' + this.form.inventoryLineId.value + '&qtyCount=' + this.form.qtyCount.value + '&inventoryId=' + this.form.inventoryId.value;
	window.location = action;
	toConsole(action);
}
</script>	
<%@ include file="/jsp/include/posFooter.jsp" %>    
								