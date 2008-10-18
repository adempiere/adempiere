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
<%
	boolean isFullDetails = false;
	
	if(request.getParameter("isFullDetails") != null)
	{
		isFullDetails = Boolean.parseBoolean(request.getParameter("isFullDetails"));
	}
%>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>
		<%@page import="org.posterita.Constants"%>
<table class="cart" border="0">
		<thead>
			<tr>		        
				
				<th align="left" width="25%"><pos:message key="ProductName"/></th>
				<th align="left"><pos:message key="PriceList"/></th>
				<th align="left"><pos:message key="Discount"/></th>
				<th align="right"><pos:message key="QtyEntered"/></th>
				<th align="right"><pos:message key="Uom"/></th>
				<th align="right"><pos:message key="PriceActual"/></th>
				<th align="right"><pos:message key="vat"/></th>
				<th align="right"><pos:message key="LineTotalAmt"/></th>
				<% 
					if(isFullDetails)
					{
				%>
				<th align="left"><pos:message key="PurchasePrice"/></th>
				<th align="right"><pos:message key="GrossProfit"/></th>
				<th align="right"><pos:message key="% GP"/></th>
				<%
					}
				%>									
			</tr>
		<thead>
		<tbody>		
		<%String styleClass = "label"; %>	   
			<logic:present name="<%=orderlines%>">
			<logic:iterate indexId="count" name="<%=orderlines%>" id="element">
							 
			<%
					if ((count.intValue()%2) != 0)
						styleClass = "contentname";
			%>
			<tr>									
				
				<td class=<%=styleClass%>><bean:write name="element" property="productName"/></td>
				<td class=<%=styleClass%> align="right"><bean:write name="element" property="unitPrice"/></td>
				<td class=<%=styleClass%> align="right"><bean:write name="element" property="discountPercentage"/></td>
				<td class=<%=styleClass%> align="right"><bean:write name="element" property="qtyOrdered"/></td>
				<td class=<%=styleClass%> align="right"><bean:write name="element" property="uom"/></td>
			    <td class=<%=styleClass%> align="right"><bean:write name="element" property="lineNetAmt"/></td>
				<td class=<%=styleClass%> align="right"><bean:write name="element" property="taxAmt"/></td>
				<td class=<%=styleClass%> align="right"><bean:write name="element" property="lineTotalAmt"/></td>
				<% 
					if(isFullDetails)
					{
				%>
				<td class=<%=styleClass%> align="right"><bean:write name="element" property="unitPurchasePrice"/></td>
				<td class=<%=styleClass%> align="right"><bean:write name="element" property="grossProfit"/></td>																						
				<td class=<%=styleClass%> align="right"><bean:write name="element" property="grossProfitPercentage"/></td>
				<%
					}
				%>
				
			</tr>	
			</logic:iterate>
			</tbody>
			
			<tfoot>	
				<td class="total"> 	
					<pos:message key="GrandTotal"/>
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
				<td nowrap class="total" align="right">  		  
					<fmt:formatNumber value='${subTotal}'type="currency" currencySymbol='${currSymbole}'/>
				</td>
				<% 
					if(isFullDetails)
					{
				%>
				<td nowrap class="total">&nbsp;</td>
				<td nowrap class="total" align="right">
					<bean:write name="element" property="totalGrossProfit"/>
				</td>
				<td nowrap class="total" align="right">
					<bean:write name="element" property="totalGrossProfitPercentage"/>
				</td>
				<%
					}
				%>									
			</tr>
			<tr>
			<% styleClass = styleClass.equals("label")? "contentName":"label"; %>
				<td class=<%=styleClass%>> 	
					<pos:message key="discount.amt.order.level"/>
				</td>
				<td class=<%=styleClass%>>&nbsp;</td>
				<td class=<%=styleClass%>>&nbsp;</td>
				<td class=<%=styleClass%>>&nbsp;</td>
				<td class=<%=styleClass%>>&nbsp;</td>
				<td class=<%=styleClass%>>&nbsp;</td>
				<td class=<%=styleClass%>>&nbsp;</td>
				<td class=<%=styleClass%> align="right">
					<bean:write name="element" property="discountAmt"/>
				</td>
				<% 
					if(isFullDetails)
					{
				%>
				<td class=<%=styleClass%>>&nbsp;</td>
				<td class=<%=styleClass%>>&nbsp;</td>
				<td class=<%=styleClass%>>&nbsp;</td>
				<%
					}
				%>
			</tr>
			<tr>
			<% styleClass = styleClass.equals("label")? "contentName":"label"; %>
				<td class=<%=styleClass%>> 	
					<pos:message key="write.off.amt.order.level"/>
				</td>
				<td class=<%=styleClass%>>&nbsp;</td>
				<td class=<%=styleClass%> >&nbsp;</td>
				<td class=<%=styleClass%>>&nbsp;</td>
				<td class=<%=styleClass%>>&nbsp;</td>
				<td class=<%=styleClass%>>&nbsp;</td>
				<td class=<%=styleClass%>>&nbsp;</td>
				<td class=<%=styleClass%> align="right">
					<bean:write name="element" property="writeOffAmt"/>
				</td>
				<% 
					if(isFullDetails)
					{
				%>
				<td class=<%=styleClass%>>&nbsp;</td>
				<td class=<%=styleClass%>>&nbsp;</td>
				<td class=<%=styleClass%>>&nbsp;</td>
				<%
					}
				%>
			</tr>
			<td class="total"> 	
					<pos:message key="Amount Paid"/>
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
				<td nowrap class="total" align="right">  		  
					<fmt:formatNumber value='${grandTotal}'type="currency" currencySymbol='${currSymbole}'/>
				</td>
				<% 
					if(isFullDetails)
					{
				%>
				<td nowrap class="total">&nbsp;</td>
				<td nowrap class="total">&nbsp;</td>													
				<td nowrap class="total">&nbsp;</td>
				<%
					}
				%>
			</tr>
			<tfoot>			
			</logic:present>
		</table>