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
		<table class="cart" border="0">
		<thead>
			<tr>		        
				
				<th align="left" width="40%"><pos:element columnName="ProductName"/></th>
				<th align="left"><pos:element columnName="PriceList"/></th>
				<th align="left"><pos:element columnName="Discount"/></th>
				<th align="right"><pos:element columnName="QtyEntered"/></th>
				<th align="right"><pos:element columnName="PriceActual"/></th>
				<th align="right"><pos:element columnName="vat"/></th>
				<th align="right"><pos:element columnName="LineTotalAmt"/></th>								
			</tr>
		<thead>
		<tbody>			   
			<logic:present name="<%=orderlines%>">
			<logic:iterate indexId="count" name="<%=orderlines%>" id="element">
							 
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
				<bean:define id="qtyTotal" name="element" property="qtyTotal"/>
				<td class=<%=styleClass%> align="right"><bean:write name="element" property="lineNetAmt"/></td>
				<td class=<%=styleClass%> align="right"><bean:write name="element" property="taxAmt"/></td>
				<td class=<%=styleClass%> align="right"><bean:write name="element" property="lineTotalAmt"/></td>																						
			</tr>			
			</logic:iterate>
			</tbody>
			
			<tfoot>	
			<tr>
				<td class="total"> 	
					<pos:element columnName="GrandTotal"/>
					<bean:define id="currSymbole" name="<%= Constants.CURRENCY_SYMBOLE %>"/>
				</td>
				<td class="total"> 	
					&nbsp;
				</td>
				<td class="total"> 	
					&nbsp;
				</td>
				<td nowrap class="total" align="right">  		  
					<fmt:formatNumber value='${qtyTotal}'/>
				</td>
				<td nowrap class="total" align="right">
					<fmt:formatNumber value='${totalLines}' type="currency" currencySymbol=''/>
				</td>								  						
				<td nowrap class="total" align="right"> 		  
					<fmt:formatNumber value='${orderTax}' type="currency" currencySymbol=''/>
				</td>
				<td nowrap class="total" align="right">  		  
					<fmt:formatNumber value='${grandTotal}'type="currency" currencySymbol='${currSymbole}'/>
				</td>											
			</tr>
			<tfoot>			
			</logic:present>
		</table>