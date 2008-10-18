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


		<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>
		<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
		<table class="cart" border="1">
		<thead>
			<tr>		        
				
				<th align="left" width="40%"><pos:message key="Name"/></th>
				<th align="left"><pos:message key="PriceActual"/></th>
				<th align="left"><pos:message key="Discount"/></th>
				<th align="right"><pos:message key="Qty"/></th>
				<th align="right"><pos:message key="Uom"/></th>
				<th align="right"><pos:message key="Price"/></th>
				<th align="right"><pos:message key="vat"/></th>
				<th align="right"><pos:message key="total"/></th>								
			</tr>
		<thead>
		<tbody>			   
			<logic:present name="<%=orderlines%>">
			<logic:iterate indexId="count" name="<%=orderlines%>" id="element" type="org.posterita.beans.WebOrderLineBean">
							 
			<%
					String styleClass = "label";
					if ((count.intValue()%2) != 0)
						styleClass = "contentname";
			%>
			<tr>									
				
				<td class=<%=styleClass%>><bean:write name="element" property="productName"/></td>
				<td class=<%=styleClass%> align="center"><bean:write name="element" property="unitPrice"/></td>
				<td class=<%=styleClass%> align="center"><bean:write name="element" property="discountPercentage"/></td>
				<td class=<%=styleClass%> align="right"><bean:write name="element" property="qtyOrdered"/></td>
				<td class=<%=styleClass%> align="right"><bean:write name="element" property="uom"/></td>
				<td class=<%=styleClass%> align="right"><bean:write name="element" property="lineNetAmt"/></td>
				<td class=<%=styleClass%> align="right"><bean:write name="element" property="taxAmt"/></td>
				<td class=<%=styleClass%> align="right"><bean:write name="element" property="lineTotalAmt"/></td>																							
			</tr>			
			</logic:iterate>
			</tbody>
			
			<tfoot>	
			<tr>
				
				<td class="total"> 	
					<bean:message key="orderView.orderPriceDetails.orderTotal"/>
					<bean:define id="currSymbole" name="<%= Constants.CURRENCY_SYMBOLE %>"/>
				</td>
				<td class="total"> 	
					&nbsp;
				</td>
				<td class="total"> 	
					&nbsp;
				</td>				
				<td nowrap class="total" align="right">
						<bean:write name="element" property="qtyTotal"/>
						<bean:define id="qtyTotal" name="element" property="qtyTotal"/>
					</td>
				<td class="total"> 	
					&nbsp;
				</td>
				<td nowrap class="total" align="right">
					<fmt:formatNumber value='${totalLines}' type="currency" currencySymbol=''/>
				</td>								  						
				<td nowrap class="total" align="right"> 		  
					<fmt:formatNumber value='${orderTax}' type="currency" currencySymbol=''/>
				</td>
				<td colspan="1" nowrap class="total" align="right">  		  
					<fmt:formatNumber value='${grandTotal}'type="currency" currencySymbol='${currSymbole}'/>
				</td>														
			</tr>
			<tfoot>			
			</logic:present>
		</table>