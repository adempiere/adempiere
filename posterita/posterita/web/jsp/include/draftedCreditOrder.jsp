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

<!-- draftedCreditSOrderIncluded.jsp-->
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>
<html:hidden property="action" value="<%=POSOrderAction.CREATE_POS_ORDER%>"/> 
<html:hidden property="orderType" value="<%=Constants.POS_ORDER%>"/>

<logic:present name="<%= Constants.CURRENT_PARTIAL_POS_ORDER_ID %>">
<bean:define id="orderId">
	<bean:write name="<%= Constants.CURRENT_PARTIAL_POS_ORDER_ID %>"/>
</bean:define>
<html:hidden property="orderId" value="<%= orderId %>"/>
</logic:present>

<%@ include file="/jsp/pos/orderHeader.jsp" %> 
 
 	<tr>
 		<td>&nbsp;</td>
 	</tr>
 	
	<tr>
		<td>
		<% String orderlines = Constants.POS_ORDER_LINES; %>
		<%@ include file="/jsp/pos/draftedPOSShoppingCart.jsp" %> 

		<input type="hidden" name="grandTotal" value=<fmt:formatNumber value='${grandTotal}' pattern="########.##"/>>		
		</td>
	</tr>
	<tr>
		<td>
			<div class="space"></div>
			<div align="right">				
				<html:button property="deleteBtn" styleClass="delete smallbutton" onclick="deleteSelected()" accesskey="d">&nbsp;</html:button>
			</div>
			<div class="space"></div>
			<div align="right">
				<logic:present name="<%= Constants.CURRENT_POS_ORDER_ID  %>">
				<html:button property="edit" styleClass="edit smallbutton" accesskey="e">&nbsp;</html:button>				
				</logic:present>		   
				<html:button property="btn" styleClass="complete smallbutton" onclick="completeOrder(this)" accesskey="c">&nbsp;</html:button>											   				   	 	  	
			</div>
			<div align="center">
				<table border="0" cellpadding="10">
					<tr>
						<td>
							<label><pos:message key="delete"/> -</label><label class="red">Alt-D</label>
						</td>
						<td>
							<label><pos:message key="edit"/> -</label><label class="red">Alt-E</label>
						</td>
						<td>
							<label><pos:message key="AmountTendered"/> -</label><label class="red">Alt-G</label>
						</td>
						<td>
							<label><pos:message key="continue"/> -</label><label class="red">Alt-C</label>
						</td>									
					</tr>
				</table> 		   	 	  	
			</div>
		</td>
	</tr>
</table>