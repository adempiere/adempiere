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
		
		<tr>
			<td>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
			  			<td width="50%" class="label">
			  				<bean:write name="<%=Constants.WEB_DOCUMENT_HEADER_BEAN%>" property="to"/> 
			  				<B><c:out value='${me.name}'/></B>
			  			</td>
			  			
						<td align="right" class="label">
							<bean:write name="<%=Constants.WEB_DOCUMENT_HEADER_BEAN%>" property="from"/>
							<c:out value='${you.name}'/>
			  			</td>
			  		</tr>
			  	</table>
			</td>			
  		</tr>
  		
		<tr>
   			<td>
   				<table cellspacing="0" cellpadding="0" border="0">
   					<tr>
			   			<td class="label">
			   				Document Type: <bean:write name="<%=Constants.WEB_DOCUMENT_HEADER_BEAN%>" property="documentHeader"/>
				   		</td>
				   	</tr>
				</table>
			</td>
   		</tr>

  		<tr>
  			<td>
				<%@ include file="/jsp/include/errors.jsp" %>
				<table width="100%" border="0 cellspacing="0" cellspacing="0">
  		   			<tr>
			   			<td colspan="2" class="label">
							
						    <c:url value="ViewInvoiceAction.do" var="invoiceId">
							    <c:param name="action" value="viewInvoice"/>
							   	<c:param name="documentId" value="${minvoice.c_Invoice_ID}"/>
						    </c:url>		
						    	     						  				
			  				Invoice Ref No:
			  				
						    <a href='<c:out value="${invoiceId}"/>'>
						     <c:out value="${minvoice.c_Invoice_ID}"/>
						    </a> 			  				
			  				
			  			</td>
			   		</tr>	
			   		
  		   			<tr>
			   			<td width="50%" class="label">
			  				 Payment Ref No: <bean:write name="<%=Constants.MPAYMENT%>" property="c_Payment_ID"/>
			  			</td>
			  			
			  			<td align="right" class="label">
			  				Counter Payment Ref No: <bean:write name="<%=Constants.MPAYMENT%>" property="ref_Payment_ID"/>
			  			</td>
			   		</tr>	

			   		<tr>
			   			<td colspan="2" class="label">
							Status: <dcs:orderStatus name="<%=Constants.MPAYMENT%>" property="docStatus"/>
						</td>
			   		</tr>
			   		
  		   			<tr>
			   			<td colspan="2">
							&nbsp;
			  			</td>
			   		</tr>	
			   					   					   				   		
			  	</table>
			  </td>			
   		</tr>
   		
  		
		<tr class="white">
  			<td valign="top">
  				<table width="100%" class="content" cellpadding="5" cellspacing="1" align="center" border="1" cols="6">
					<tr>
					   	<th colspan="1" align="left" class="label">Date Paid</th>
					   <logic:notEqual name="<%=Constants.MORDER%>" property="orderType" value="<%=UDIOrderTypes.USED_CAR_PURCHASE_ORDER.getOrderType()%>">						   	
					   	<th colspan="1" align="left" class="label" width="12%">Pay Amount</th>
					   	</logic:notEqual>
					</tr>

					<tr>
						<td class="label">
							<fmt:formatDate value='${payment.dateTrx}' pattern='MMM dd yyyy, HH:mm'/>
						</td>	
				   			
				   		<logic:notEqual name="<%=Constants.MORDER%>" property="orderType" value="<%=UDIOrderTypes.USED_CAR_PURCHASE_ORDER.getOrderType()%>">						   				
						<td class="label">
						<bean:define id="amount" name="<%=Constants.MPAYMENT%>" property="payAmt"/>
							
							<fmt:formatNumber value='${amount}' maxFractionDigits="2" type="currency" currencySymbol="R "/>
						</td>
						</logic:notEqual>
					</tr>
				   				
				</table>
			</td>
		</tr>
