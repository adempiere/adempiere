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


<!-- viewInventoryHistory.jsp -->
<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.compiere.model.MOrg" %>
<%@ page import="org.compiere.process.DocumentEngine" %>
<%@ page import="org.posterita.struts.pos.InventoryAction" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos" %>

<bean:define id="title"><pos:element columnName="M_Inventory_ID" textOnly="true"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %> 

<html:form action="/MergeInventoryAction">
<html:hidden property="action" value="mergeInventoryToCreateOne"/>
	<logic:present name="<%=Constants.INVENTORY_HISTORY_LIST%>">
	<logic:notEmpty name="<%=Constants.INVENTORY_HISTORY_LIST%>">
			<table class="display sortable scrollpane" border="1" id="vendor"> 		  		
				<tr>
					<th><pos:element columnName="DocumentNo"/></th>		
					<th><pos:element columnName="DocStatus"/></th>
					<th><pos:element columnName="MovementDate"/></th>
					<th><pos:element columnName="Description"/></th>
					<th><pos:element columnName="delete"/></th>
					<th><pos:element columnName="Select"/></th>
					
				</tr>
				
			<logic:iterate  indexId="count" name="<%=Constants.INVENTORY_HISTORY_LIST%>" id="element" type="org.posterita.beans.InventoryBean" >	
			<%
				String styleClass = "label";
				
				if ((count.intValue()%2) != 0)
					styleClass = "contentname";
			%>
			
			<tr>
				<td class="<%=styleClass%>">
				<html:link href="<%="ViewInventoryAction.do?action=viewInventory&inventoryId="+element.getInventoryId()+"&description="+element.getDescription()%>">
					<bean:write name="element" property="inventoryNo"/>					
				</html:link>	
				
				</td>	
				
				<td class="<%=styleClass%>">
				<span class="<bean:write name='element' property='docStatus'/>">
					<bean:write name="element" property="docStatus"/>
				</span>
				</td>
				
				<td class="<%=styleClass%>">
					<bean:write name="element" property="movementDate"/>
				</td>
				<td class="<%=styleClass%>">
					<bean:write name="element" property="description"/>
				</td>
				
				<td class="<%=styleClass%>">
				<logic:notEqual name="element" property="docStatus" value="Completed">
						<html:link href="<%="DeleteInventoryAction.do?action=deleteInventory&inventoryId=" + element.getInventoryId().toString() %>">
			   				<pos:element columnName="delete"/>
			   			</html:link>
				</logic:notEqual>
				</td>
				
				<td class=<%=styleClass%> >
					<logic:equal value="Drafted" name="element" property="docStatus">
						<html:multibox property="inventoryIds" value="<%=element.getInventoryId().toString()%>">
	           		</html:multibox>	
         		</td>	
				</logic:equal>
				
			</tr>
			
			</logic:iterate>
			<tr>
				 <td colspan="6" align="right">	
					<html:submit>
					
						Merge
					</html:submit>
				 </td>
			 </tr>
			</table>
			
	</logic:notEmpty>
	</logic:present>
	
</html:form>


<%@ include file="/jsp/include/posFooter.jsp" %>    
<script>
</script>	
												