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



<!-- viewDocumentHistory.jsp -->

<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.order.UDIOrderTypes" %>
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.compiere.model.MOrg" %>

<%@ page import="java.util.Calendar" %>
<%@ page import="org.posterita.beans.OrderHistoryBean" %>
<%@ page import="java.util.*" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

<bean:define id="title"><pos:message textOnly="true" key="smenu.document.history"/></bean:define>

<%
	String url = "ViewDocumentHistory.do";
	String collection = Constants.DOCUMENT_HISTORY;
%>	
	
<%@ include file="/jsp/include/posHeader.jsp" %>
	<table class="main">

	   	<html:form action="DocumentHistoryAction">
	   	<html:hidden property="action" value="getHistory"/>
	   	

	   	<tr>
	   		<td>
	   			<table cellpadding="0" cellspacing="0" border="0">
	   				<%@ include file="/jsp/pos/filter.jsp" %>
	   			</table>
	   		</td>
	   	</tr>
	   	</html:form>
	

		   	<tr>
		   		<td>
		   			<table class="display sortable" id="1111" border="1"> 		  		
						<tr>
							<th class="tableHeader" nowrap><pos:message key="ordered.by"/></th>
							<th class="tableHeader date" nowrap><pos:message key="date.ordered"/></th>						
							<th class="tableHeader" nowrap><pos:message key="order.no"/></th>
							<th class="tableHeader"><pos:message key="payment.num"/></th>
							<th class="tableHeader"><pos:message key="invoice.no"/></th>
							<th class="tableHeader"><pos:message key="shipment.no"/></th>
						</tr>
				
						<logic:iterate offset = "<%=offset%>" length="<%=length%>" indexId="count" name="<%=collection%>" id="element" type="org.posterita.beans.DocumentHistoryBean">		
							<tr>
								<%
									String styleClass = "label";
									if ((count.intValue()%2) != 0)
										styleClass = "contentname";											
								%>
								
								<td class="<%=styleClass%>">
		   						<% 
								   	String partnerId= element.getBpartnerId() + ""; 
								%>
								   
									<logic:equal name="element" property="isCustomer" value="true">
										<html:link href="<%="POSCustomerAction.do?action=viewPOSCustomer&bpartnerId=" + partnerId %>">
								   			<bean:write name="element" property="partnerName"/>
								   		</html:link>			
									</logic:equal>
									
									
									<logic:equal name="element" property="isCustomer" value="false">
										<html:link href="<%="POSVendorAction.do?action=viewVendorDetails1&bpartnerId=" + partnerId %>">
								   			<bean:write name="element" property="partnerName"/>
								   		</html:link>			
									</logic:equal>
		   						
								
								</td>
								
				
								<td class="<%=styleClass%>">	
									<fmt:formatDate value='${element.dateOrdered}'  type="both" dateStyle="short" timeStyle="medium"/>
								</td>
	
								<td class="<%=styleClass%>"> 
								
									<html:link href="<%="ViewPOSOrderAction.do?action=viewPOSOrders&orderId=" + element.getOrderId() %>">
					     				<bean:write name="element" property="documentNo"/>
					     			</html:link>					
								</td>	

								<td align="left" class="<%=styleClass%>">
									<html:link href="<%="ViewWebstorePaymentAction.do?action=viewOrder&documentId=" + element.getPaymentId() %>">
										<bean:write name="element" property="paymentId"/>
									</html:link>
								</td>
						
								<td align="left" class="<%=styleClass%>">
									<html:link href="<%="ViewInvoiceAction.do?action=viewOrder&documentId=" + element.getInvoiceId() %>">
										<bean:write name="element" property="invoiceNo"/>
									</html:link>
								</td>
								
								<logic:notPresent name="element" property="shipmentNo">	
								 	<logic:present name="element" property="invoiceNo">	
										<td align="left" class="<%=styleClass%>">		
										<html:link href="<%="CreateShipmentForCreditOrderAction.do?action=createShipmentForCreditOrder&invoiceId=" + element.getInvoiceId() %>">
											Create Shipment
										</html:link>
										</td>	
									</logic:present>		
								</logic:notPresent>	
						
							<td align="left" class="<%=styleClass%>">
									<html:link href="<%="ViewMinOutAction.do?action=viewOrder&documentId=" + element.getMinOutId() %>">
										<bean:write name="element" property="shipmentNo"/>
									</html:link>
								</td>	
						</logic:iterate>
											
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<%@ include file="/jsp/include/pager.jsp" %>
	<%@ include file="/jsp/include/posFooter.jsp" %>
