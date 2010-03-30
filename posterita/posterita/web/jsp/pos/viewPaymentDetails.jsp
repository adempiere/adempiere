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


<!--viewPaymentDetails.jsp-->

<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.beans.*" %>
<%@ page import="org.posterita.struts.pos.CreditOrderAction" %>
<%@ page import="org.compiere.process.DocumentEngine" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.posterita.core.UDIPair" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos" %>

<logic:notPresent name="<%=WebUserInfo.NAME%>" scope="session">
	<jsp:forward page="/LoginHome.do"/>
</logic:notPresent>

<bean:define id="title"><pos:message key="payment.details" textOnly="true"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %> 

<table class="main">

<tr>
			<td>
				<table border="1" class="display">	
				<tr>
						<th>
							<pos:message key="Date1"/>
						</th>
						
						<th>
							<pos:message key="DocumentNo"/>
						</th>
						
						<th>
							<pos:message key="TenderType"/>
						</th>
						<th>
							<pos:message key="cheque.no"/>.
						</th>
					
						<th>
							<pos:message key="card.no"/>
						</th>
					
						<th>
							<pos:message key="discount.amt"/>
						</th>

						<th>
							<pos:message key="PaidAmt"/>
						</th>
						
						<th>
							<pos:message key="WriteOffAmt"/>
						</th>
						
						
				</tr>
	<logic:present name="<%=Constants.CARD_CHQ_PAYMENT_LIST%>">
		
					<logic:iterate indexId="count" id="element" name="<%=Constants.CARD_CHQ_PAYMENT_LIST%>" type="org.posterita.beans.CreditPaymentDetailsBean">		
						<tr>
							<td>
							  	<bean:write name="element" property="dateCreated"/>
							</td>
							
							<td>
								<html:link href="ViewWebstorePaymentAction.do?action=viewOrder&documentId=<%= element.getPaymentId() %>">
					     				<bean:write name="element" property="paymentId"/>
					     		</html:link>
								
							</td>
							
							<td>
								<bean:write name="element" property="tenderType"/>
								
							</td>
							
							<td>
								<bean:write name="element" property="chequeNo"/>
							</td>
							<td>
								<bean:write name="element" property="creditCardNumber"/>
							</td>
							<td>
								<bean:write name="element" property="discountAmt"/>
							</td>
							<td>
								<bean:write name="element" property="paidAmt"/>
							</td>
							<td>
								<bean:write name="element" property="writeOffAmt"/>
							</td>
						</tr>	
					</logic:iterate>
				
		</td>
	</tr>
	
	</logic:present>
		<logic:present name="<%=Constants.CASH_PAYMENT_LIST%>">
			
							<logic:iterate indexId="count" id="element" name="<%=Constants.CASH_PAYMENT_LIST%>" type="org.posterita.beans.CreditPaymentDetailsBean">		
						<tr>
							<td>
							  	<bean:write name="element" property="dateCreated"/>
							</td>
							
							<td>
								<html:link href="ViewWebstoreCashPaymentAction.do?action=viewOrder&documentId=<%= element.getCashLineId() %>">
					     				<bean:write name="element" property="cashLineId"/>
					     		</html:link>
							</td>
							<td>
								<bean:write name="element" property="tenderType"/>
								
							</td>
							
							<td>
								<bean:write name="element" property="chequeNo"/>
							</td>
							<td>
								<bean:write name="element" property="creditCardNumber"/>
							</td>
							<td>
								<bean:write name="element" property="discountAmt"/>
							</td>
							<td>
								<bean:write name="element" property="paidAmt"/>
							</td>
							<td>
								<bean:write name="element" property="writeOffAmt"/>
							</td>
						</tr>	
					</logic:iterate>
						</tr>
					<tr>	
						<td colspan="6">
							Total:
						</td>
						<td colspan="1" align="right">
							<bean:write name="<%=Constants.INVOICE_GRAND_TOTAL%>" />
						</td>	
					</tr>	
					</table>
				</td>
			</tr>	
		</logic:present>
</table>

<%@ include file="/jsp/include/posFooter.jsp" %>
