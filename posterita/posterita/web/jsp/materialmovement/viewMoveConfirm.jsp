<%--
 *  Product: Posterita Web-Based POS and Adempiere Plugin
 *  Copyright (C) 2008  Posterita Ltd
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
<!-- viewMoveConfirm.jsp -->
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>	
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>
<%@page import="org.posterita.Constants"%>
<%@page import="org.posterita.struts.stock.StockMovementAction"%>
<bean:define id="title">Move Confirmation</bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %>
<!-- page contents -->

<div class="scrollpane">
	<%
		String collection = Constants.MOVE_CONFIRM;
	%>
	<table class="display sortable" border="1" width="900px" >
		<tr>
			<th class="string"><span title="MoveConfirmID" name="MoveConfirmId">DocNo</span></th>
			<th class="string"><span title="InventoryMove" name="InventoryMove" tooltip="InventoryMove">Inventory Move</span></th>
			<th class="string" nowrap="nowrap"><span title=OrgFrom> From Organisation</span></th>
			<th class="string" nowrap="nowrap"><span title="Active"> Active</span></th>
			<th class="string" nowrap="nowrap"><span title="DocStatus"> DocStatus</span></th>
	   	    <th class="string" nowrap="nowrap"><span title="Date" name="Date" tooltip="Date">Date</span></th>  
	   	    <th class="string" nowrap="nowrap"><span title="Action" name="Action" tooltip="Document Action">Action</span></th>
		</tr> 	
	
		<html:form action="/StockMovementAction">
		<html:hidden property="action" value="<%=StockMovementAction.COMPLETE_MOVE_CONFIRM%>"/>
		
		<logic:present name="<%=Constants.MOVE_CONFIRM%>">

		<logic:iterate id="stock" name="<%=Constants.MOVE_CONFIRM%>" type="org.posterita.beans.StockMovementBean">
		<tr>
			<td class = "label">
				<html:hidden name="stock" property="moveConfirmId" value="<%=stock.getMoveConfirmId().toString()%>" indexed="true"/>
				<bean:write name="stock" property="moveConfirmId" />
			</td>
			<td class = "label">
				<html:link action="StockMovementAction.do?action=viewMoveConfirmLines&movementId=<%= stock.getMovementId().toString() %>&moveConfirmId=<%= stock.getMoveConfirmId().toString() %>"><bean:write name="stock" property="documentNo"/></html:link>
			</td>
			<td class = "label">
				<bean:write name="stock" property="orgFromName"/>
			</td>
			<td class = "label">
				<bean:write name="stock" property="isActive" />
			</td>
			<td class = "label">
				<bean:write name="stock" property="docStatus" />
			</td>
			<td class = "label">
				<bean:write name="stock" property="movementDate" />
			</td>			
			
			<td class ="label">
			<% if (!stock.getDocStatus().equals("CO"))
				{
			%>
				<html:link action="StockMovementAction.do?action=completeMoveConfirm&moveConfirmId=<%= stock.getMoveConfirmId().toString() %>">Complete</html:link>
			<%
				}
			%>		
			</td>		
						
		</tr>
		</logic:iterate>

		</logic:present>
		
		</html:form>
		</table>
	
</div>
<%@ include file="/jsp/include/posFooter.jsp" %>