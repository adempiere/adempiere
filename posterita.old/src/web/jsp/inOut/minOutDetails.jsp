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


<%@ page import="org.posterita.Constants" %>

   		
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
			  				 Shipment Ref No: <bean:write name="<%=Constants.MINOUT%>" property="m_InOut_ID"/>
			  			</td>
			  			
			  			<td align="right" class="label">
			  				Counter Shipment Ref No: <bean:write name="<%=Constants.MINOUT%>" property="ref_InOut_ID"/>
			  			</td>
			   		</tr>	

			   		<tr>
			   			<td colspan="2" class="label">
							Doc Status: <dcs:orderStatus name="<%=Constants.MINOUT%>" property="docStatus"/>
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
								   	<th colspan="1" align="left" class="label"><bean:message key="product.umodel"/></th>
								   	<th colspan="1" align="left" class="label"><bean:message key="product.ucolour"/></th>
								   	<th colspan="1" align="left" class="label"><bean:message key="product.utransmission"/></th>
								   	<th colspan="1" align="left" class="label"><bean:message key="product.year"/></th>
								   	<th colspan="1" align="left" class="label"><bean:message key="product.vinNo"/></th>
								   	<th colspan="1" align="left" class="label" width="12%">Quantity</th>
						  		</tr>

				   				<logic:iterate name="<%=Constants.MINOUT_LINES_COLLECTION%>" id="element" type="org.posterita.beans.WebMinOutLineBean">
				   					
				   				<tr>
				   					<td class="label">
				   						<bean:write name="element" property="attributeValuesPair.modelAttributeValue.name"/>
				   					</td>	
				   									   				
				   					<td class="label">
				   						<bean:write name="element" property="attributeValuesPair.colourAttributeValue.name"/>
				   					</td>	
				   									   				
				   					<td class="label">
				   						<bean:write name="element" property="attributeValuesPair.transmissionAttributeValue.name"/>
				   					</td>
				   					
				   					<td class="label">
				   						<bean:write name="element" property="attributeValuesPair.yearAttributeValue.name"/>
				   					</td>						   				

									
				   					<td class="label">
				   						<bean:write name="element" property="serno"/>
				   					</td>
				   					
				   					<td class="label">
				   						1
				   					</td>
				     										   									   				
				   	
				   				</tr>
				   				</logic:iterate>
				   				
				 			</table>
			</td>
		</tr>