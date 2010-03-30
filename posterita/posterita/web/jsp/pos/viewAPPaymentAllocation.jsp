<!--viewAPPaymentAllocation.jsp-->
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.beans.BPartnerInfoBean" %>
<%@ page import="org.posterita.user.WebUserInfo" %>
<%@ page import="org.posterita.lib.UdiConstants" %>
<%@ page import="org.posterita.struts.pos.CreditOrderAction" %>
<%@ page import="org.posterita.struts.pos.DunningAction" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos" %>

<logic:notPresent name="<%=WebUserInfo.NAME%>" scope="session">
	<jsp:forward page="/LoginHome.do"/>
</logic:notPresent>

<bean:define id="title"><pos:message key="smenu.payment.allocation.history" textOnly="true"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %> 

<html:form action="/ViewAPPaymentAllocationAction">
<html:hidden property="action" value="<%=CreditOrderAction.GET_ALLOCATION_DETAILS_FOR_PARTNER %>"/>
<html:hidden property="isCustomer" value="false"/>	
<html:hidden property="isVendor" value="true"/>	
<table class="display" border="0"> 	
		<tr>
			<td>
				<img src="images/tshirt/1pixel.gif" width="1" height="5" border="0">
			</td>
		</tr>
		
		<tr>
			<td>
				<pos:message key="invoice.no"/>:<html:text property ="invoiceNo"/>
			
				&nbsp;

				<pos:message key="Name"/>: <html:text property ="partnerName"/>
				
				&nbsp;

				<html:submit styleClass="tangoSearch tangoButton">
	        		&nbsp;
   				</html:submit>		
   			</td>		 
		</tr>
		
		<tr>
			<td>
				<img src="images/tshirt/1pixel.gif" width="1" height="5" border="0">
			</td>
		</tr>		
</table>
<logic:present name="<%=Constants.PAYMENT_ALLOCATIONS%>">
<logic:empty name="<%=Constants.PAYMENT_ALLOCATIONS%>">
	<pos:message key="no.allocation.found" textOnly="true"/>
</logic:empty>
<logic:notEmpty name="<%=Constants.PAYMENT_ALLOCATIONS%>">
<%
	String url = "ViewPaymentAllocation.do";
	String collection = Constants.PAYMENT_ALLOCATIONS;
%>
<table class="display sortable" border="1" id="1111">
	<tr>
		<th><pos:message key="Name"/></th>
		<th><pos:message key="C_Order_ID"/></th>
		<th><pos:message key="C_Invoice_ID"/></th>
		<th><pos:message key="C_Payment_ID"/></th>
		<th><pos:message key="cash.payment"/></th>
		<th>memo</th>
		<th><pos:message key="Amount"/></th>
		<th><pos:message key="DiscountAmt"/></th>
		<th><pos:message key="WriteOffAmt"/></th>
		<th><pos:message key="OverUnderAmt"/></th>
		
	</tr>		
	<logic:iterate offset="<%=offset%>" length="<%=length%>" id="element" indexId="count" name="<%=Constants.PAYMENT_ALLOCATIONS %>" type="org.posterita.beans.PaymentAllocationBean">
	
	<tr>	
		<%
			String styleClass = "label";
			if ((count.intValue()%2) != 0)
				styleClass = "contentname";
		%>
		
						
	   <td class=<%=styleClass%>>
	   		
	   		<html:link href="<%="POSCustomerAction.do?action=viewPOSCustomer&bpartnerId="+ element.getBpartnerId() %>">
   						<bean:write name="element" property="partnerName"/>
   			</html:link>	
	   				
	   </td>						
	   						   
	   <td class=<%=styleClass%>>
			<html:link href="<%="ViewPOSOrderAction.do?action=viewPOSOrders&orderId=" + element.getOrderId() %>">
				<bean:write name="element" property="documentNo"/>
			</html:link>
	   </td>	
	   <td class=<%=styleClass%>>
	   		<html:link href="<%="ViewInvoiceAction.do?action=viewOrder&documentId=" + element.getInvoiceId() %>">
				<bean:write name="element" property="invoiceNo"/>
			</html:link>
			
	   </td>					   
	   <td class=<%=styleClass%>>
	   		<html:link href="<%="ViewWebstorePaymentAction.do?action=viewOrder&documentId=" + element.getPaymentId() %>">
				<bean:write name="element" property="paymentId"/>
			</html:link>
			
	   </td>
	   <td class=<%=styleClass%>>
		   <html:link href="<%="ViewWebstoreCashPaymentAction.do?action=viewOrder&documentId=" + element.getCashLineId() %>">
				<bean:write name="element" property="cashLineId"/>
			</html:link>
	   </td>
	   
	   <td class=<%=styleClass%>>
	   		<html:link href="<%="ViewInvoiceAction.do?action=viewOrder&documentId=" + element.getCreditMemoId() %>">
				<bean:write name="element" property="creditMemoNumber"/>
			</html:link>
	   </td>	
	   
	    <td class=<%=styleClass%>>
			<bean:write name="element" property="amount"/>
	   </td>
	   
	    <td class=<%=styleClass%>>
			<bean:write name="element" property="discountAmt"/>
	   </td>
	   
	    <td class=<%=styleClass%>>
			<bean:write name="element" property="writeOffAmt"/>
	   </td>
	   
	    <td class=<%=styleClass%>>
			<bean:write name="element" property="overUnderPayment"/>
	   </td>
	   
	</tr>

	</logic:iterate> 
	
</table>
<%@ include file="/jsp/include/pager.jsp" %>
</logic:notEmpty>
</logic:present>						
</html:form>	    									 					 
<%@ include file="/jsp/include/posFooter.jsp" %>
