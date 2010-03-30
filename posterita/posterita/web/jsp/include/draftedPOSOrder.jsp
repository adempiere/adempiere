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

<!-- draftedPOSOrderIncluded.jsp-->
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
		<div id="amtGiven">
		<table border="0" cellpadding="5" cellspacing="0" align="center">			
			<tr>
				<td>
					<label><b><pos:message key="amount.tendered"/>:</b></label>
				</td>
				<td>
					<html:text property="amountGiven" styleClass="text" onkeyup="updateAmountToBeReturned(this)" accesskey="g"/>
				</td>			
				<td>
					<LABEL><b><pos:message key="amount.refunded"/>:</b></LABEL>
				</td>
				<td>
					<html:text property="amountRefunded" styleClass="text" readonly="true" />	
					<html:hidden property ="trxType"/>
				</td>
			</tr>						
		</table>
		</div>
		</td>
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
		 <logic:notPresent name="<%= Constants.PARTIAL_POS_OREDR %>">		
			<div align="right">				
				<html:button property="deleteBtn" styleClass="delete smallbutton" onclick="deleteSelected()" accesskey="d">&nbsp;</html:button>
			</div>
	     </logic:notPresent>		
			<div class="space"></div>
			<div align="right">
					 <logic:notPresent name="<%= Constants.PARTIAL_POS_OREDR %>">			
					 <logic:present name="<%= Constants.CURRENT_POS_ORDER_ID  %>">
							<bean:define id="posOrderId" name="<%= Constants.CURRENT_POS_ORDER_ID  %>"/>								
							<html:link href="<%="GetShoppingCartForOrder.do?action=getOrderShoppingCart&orderId="+posOrderId + "&orderType=" + orderType%>">
						   		<html:button property="btn" styleClass="edit smallbutton" accesskey="c">&nbsp;</html:button>				
						   	</html:link>
					 </logic:present>	
					 </logic:notPresent>	
					  
				
											   							
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
							<label><pos:message key="amount.tendered"/> -</label><label class="red">Alt-G</label>
						</td>
						<td>
							<label><pos:message key="continue"/> -</label><label class="red">Alt-C</label>
						</td>									
					</tr>
				</table> 		   	 	  	
			</div>
		</td>
	</tr>
