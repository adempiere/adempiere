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
 * @author Vishee
--%>

<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>
<html:hidden property="isSales" value="true"/>
<html:hidden property="action" value=""/>
<html:hidden property="ifAdd" value="true"/>
<html:hidden property="orderType" value="<%=Constants.POS_ORDER%>"/>

<logic:present name="<%= Constants.CURRENT_POS_ORDER_ID %>">
<bean:define id="orderId">
	<bean:write name="<%= Constants.CURRENT_POS_ORDER_ID %>"/>
</bean:define>
<html:hidden property="orderId" value="<%= orderId %>"/>
</logic:present>

<table class="main">			
   	<tr>	
   		<td>   		
   			<table class="main">

   			<tr>
		   		<td>
		   			<label><u><pos:message key="barcode"/>:</label>
		   		</td>		
		   		<td>
		   			<html:text property="barCode" accesskey="b" styleClass="text"/>
		   			<html:hidden property="productId" value=""/>
		   		</td>
		   		
		   		<td>
		   			<label><u><pos:message key="Qty"/>:</label>
		   		</td>		
		   		<td>
		   			<html:text property="quantity" accesskey="q" styleClass="text"/>
		   		</td> 
		   		
		   		<td class="buttoncell"  align="right">
		   			<html:button property="btn" styleClass="addtocart bigbutton" onclick="addToCart()" accesskey="a">&nbsp</html:button>		   				   			
				</td>  		
			</tr>

   			<tr>
   				<td>
   					<label><pos:message key="search.product"/>:</label>
   				</td>
   				<td colspan="3">
   					<div>
					<%@ include file="/jsp/include/searchProductPanel.jsp" %>
					</div>
				</td>
				<td>&nbsp;</td>
   			</tr>   
		</table>
   	</td>
   </tr>		
   	
	<tr>
		<td>
			<div id="shoppingCart">
				<%@ include file="/jsp/pos/posShoppingCartFirstPage.jsp" %>
			</div>
		</td>
	</tr> 
	
	<tr>
		<td>
			<table class="main">
				<tr>
					<td valign="top" width="300px">						
						<%@ include file="/jsp/include/tenderPanel.jsp" %>											
					</td>
					<td  valign="top" width="300px">
						<%@ include file="/jsp/include/customerInfoPanel.jsp" %>	
					</td>
					<td valign="top">
						<table border="0" width="100%" cellspacing="0" style="padding-right: 0px; padding-left: 5px; padding-top: 5px; padding-bottom: 5px">
							<tr>
								<td class="buttoncell" align="right">
									<html:button property="btn" styleClass="checkout bigbutton" onclick="createOrder()" accesskey="c">&nbsp;</html:button>	
								</td>
							</tr>