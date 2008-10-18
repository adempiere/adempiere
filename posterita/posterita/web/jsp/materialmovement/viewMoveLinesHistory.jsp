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
<!-- viewMoveLinesHistory.jsp -->
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>	
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

<%@page import="org.posterita.Constants"%>
<%@page import="org.posterita.struts.stock.StockMovementAction"%>
<%@page import="org.posterita.beans.StockMovementBean"%>
<bean:define id="title"><pos:message key="M_Movement_ID" textOnly="true"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %>
<!-- page contents -->

<div padding = "10px">&nbsp;</div>

<div class="scrollpane">
<html:form action="/StockMovementAction">
<!-- <div>
	<label><pos:message key="refNo"/></label>
	<html:text property="refNo"></html:text>
</div>
<div padding = "5px">&nbsp;</div>
 -->
<%
	StockMovementBean movementBean = (StockMovementBean)request.getSession().getAttribute(Constants.MMOVEMENT);
	String orgFrom = movementBean.getOrgFromName();
	String orgTo = movementBean.getOrgToName();
	String addressFrom = movementBean.getAddress1();
	String addressTo = movementBean.getAddress2();
	String docStatus = movementBean.getDocStatus();
	String date = movementBean.getMovementDate();
	String movementId = movementBean.getDocumentNo().toString();
%>	
<div padding = "5px">&nbsp;</div>
<table class="display sortable" border="0" width="900px">
	<tr>
		<td align="left">
			<div><label>From:</label><%=orgFrom%></div>
			<div><label>Address:</label><%=addressFrom %></div>
			<div><label>Document Status:</label><%=docStatus %></div>
			<div><label>Inventory Move:</label><%=movementId %></div>
		</td>
		<td align="right">
			<div><label>To:</label><%=orgTo %></div>
			<div><label>Address:</label><%=addressTo %></div>
			<div><label><%=date %></label>
			<div>&nbsp;</div>
		</td>
	</tr>
</table>
<div padding = "10px">&nbsp;</div>

<table class="display sortable" border="1" width="900px">
	<tr>
		<th class="string"><span title="Name" name="name" tooltip="Name">Product Name</span></th>
		<th class="string"><span title="Description" name="Description" tooltip="Description">Description</span></th>
		<th class="string"><span title="Uom" name="Uom" tooltip="Uom">Uom</span></th>
		<th class="string" nowrap="nowrap"><span title="Active"> Active</span></th>
	    <th class="string" nowrap="nowrap"><span title="Quantity" name="Quantity" tooltip="Quantity">Quantity</span></th>
	</tr> 	
	

	<html:hidden property="action" value="<%=StockMovementAction.COMPLETE_MATERIAL_MOVEMENT%>"/>
	<html:hidden property="movementId"/>
		<logic:present name="<%=Constants.MATERIAL_MOVEMENT_LINES%>">

		<logic:iterate id="stock" name="<%=Constants.MATERIAL_MOVEMENT_LINES %>" type="org.posterita.beans.StockMovementBean">
		<tr>
			<td class = "label">
				<bean:write name="stock" property="productName"/>
				<html:hidden property="movementId" value="<%=stock.getMovementId().toString()%>"/>
			</td>
			<td class = "label">
				<bean:write name="stock" property="description" />
			</td>
			<td class = "label">
				<bean:write name="stock" property="uom"/>
			</td>
			<td class = "label">
				<bean:write name="stock" property="isActive"/>
			</td>
			<td class = "label">
				<bean:write name="stock" property="quantity"/>
			</td>	
				
		</tr>					
		</logic:iterate>

		</logic:present>
					
		</tr>
		</table>
			
</table>
</html:form>
</div>

<%@ include file="/jsp/include/posFooter.jsp" %>