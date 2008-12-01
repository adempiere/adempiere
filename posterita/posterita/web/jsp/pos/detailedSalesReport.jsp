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



<!--detailedSalesReport.jsp-->
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.beans.ProductBean" %>
<%@ page import="org.posterita.user.WebUserInfo" %>
<%@ page import="org.posterita.lib.UdiConstants" %>
<%@ page import="org.posterita.struts.pos.POSReportAction" %>
<%@ page import="org.posterita.struts.pos.POSGoodsAction" %>
<%@ page import="org.compiere.process.DocAction" %>
<%@ page import="org.compiere.model.MOrder" %>
<%@ page import="org.posterita.order.UDIOrderTypes" %>


<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos" %>

<logic:notPresent name="<%=WebUserInfo.NAME%>" scope="session">
	<jsp:forward page="/LoginHome.do"/>
</logic:notPresent>


<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %> 

<%
	String url = "DetailedSalesReport.do";
	String collection = Constants.DETAILED_SALES_REPORT;
	boolean isFullDetails = false;
	
	if(request.getParameter("isFullDetails") != null)
	{
		isFullDetails = Boolean.parseBoolean(request.getParameter("isFullDetails"));
	}
	
%>	


<table class="main">
<tr>
	<td>		
		<%@ include file="/jsp/include/errors.jsp" %>	 
					
		<table class="display scrollpane sortable" id="1111" border="1">
		<tr>
			<th><pos:message key="DocumentNo"  textOnly="true"/></th>
			<th width="80px"><pos:message key="status"  textOnly="true"/></th>
			<th><pos:message key="date.created"  textOnly="true"/></th>
			<th><pos:message key="customervendor"  textOnly="true"/></th>
			<th><pos:message key="payment.by"  textOnly="true"/></th>
			<th class="currency"><pos:message key="GrandTotal"  textOnly="true"/></th>
			<th class="currency"><pos:message key="AmountPaid"  textOnly="true"/></th>
			<th class="currency"><pos:message key="grossProfit"  textOnly="true"/></th>
			<th >&nbsp;</th>
		</tr>		
		<logic:present name="<%=Constants.DETAILED_SALES_REPORT%>">		
		<logic:iterate offset = "<%=offset%>" length="<%=length%>" indexId="count" name="<%=collection%>" id="element" type="org.posterita.beans.POSHistoryBean">
		<tr>	
			<%
				String styleClass = "label";
				if ((count.intValue()%2) != 0)
					styleClass = "contentname";
			%>
		<logic:equal name="element" property="orderType" value="<%=UDIOrderTypes.CUSTOMER_RETURN_ORDER.getOrderType()%>">
			<html:hidden property="orderType" value="<%=UDIOrderTypes.CUSTOMER_RETURN_ORDER.getOrderType() %>"/>				
		</logic:equal>					
		<logic:equal name="element" property="orderType" value="<%=UDIOrderTypes.POS_ORDER.getOrderType()%>">
			<html:hidden property="orderType" value="<%=UDIOrderTypes.POS_ORDER.getOrderType() %>"/>				
		</logic:equal>
		<logic:equal name="element" property="orderType" value="<%=UDIOrderTypes.CREDIT_ORDER.getOrderType()%>">
			<html:hidden property="orderType" value="<%=UDIOrderTypes.CREDIT_ORDER.getOrderType() %>"/>				
		</logic:equal>
		<logic:equal name="element" property="orderType" value="<%=UDIOrderTypes.POS_GOODS_RECEIVE_NOTE.getOrderType()%>">
			<html:hidden property="orderType" value="<%=UDIOrderTypes.POS_GOODS_RECEIVE_NOTE.getOrderType() %>"/>				
		</logic:equal>
		<logic:equal name="element" property="orderType" value="<%=UDIOrderTypes.POS_GOODS_RETURN_NOTE.getOrderType()%>">
			<html:hidden property="orderType" value="<%=UDIOrderTypes.POS_GOODS_RETURN_NOTE.getOrderType() %>"/>				
		</logic:equal>
		<logic:equal name="element" property="orderType" value="<%=UDIOrderTypes.CREDIT_MEMO.getOrderType()%>">
			<html:hidden property="orderType" value="<%=UDIOrderTypes.CREDIT_MEMO.getOrderType() %>"/>				
		</logic:equal>
		   <td class=<%=styleClass%> >
		   		<html:link href="<%="ViewPOSOrderAction.do?action=viewPOSOrders&orderId=" + element.getOrderId() + "&isFullDetails=" + isFullDetails %>">
					<bean:write name="element" property="documentNo"/>
				</html:link>		
		   </td>
		   <td class="<%=styleClass%>">
		   		<logic:equal name="element" property="docStatus" value="<%=DocAction.STATUS_Drafted%>" >
		   		<div class="draftedcolor">
				
					<bean:message key="document.status.drafted"/>
				</div>
				</logic:equal>				
				
				<logic:equal name="element" property="docStatus" value="<%=DocAction.STATUS_InProgress%>">				
				<div class="inprogresscolor">
					<bean:message key="document.status.inprogress"/>
				</div>
				</logic:equal>				
				
				<logic:equal name="element" property="docStatus" value="<%=DocAction.STATUS_Completed%>">
				<div class="completecolor">				
					<bean:message key="document.status.completed"/>
				</div>				
				</logic:equal>
				
				<div class="draftedcolor">				
				<logic:equal name="element" property="docStatus" value="<%=DocAction.STATUS_Invalid%>">
					<bean:message key="document.status.invalid"/>
				</div>
				</logic:equal>
												
				<logic:equal name="element" property="docStatus" value="<%=DocAction.STATUS_Closed%>">
				<div class="closed">
					<bean:message key="document.status.closed"/>
				</div>
				</logic:equal>				
		   </td>						
		   <td class=<%=styleClass%>>
		   			   		
				<bean:write name="element" property="dateAcct"/>
		   </td>						   
		   <td class=<%=styleClass%>>
		   
		   <% 
		   	String partnerId= element.getBpartnerId() + ""; 
		   %>
		   
			<logic:equal name="element" property="isCustomer" value="Yes">
				<html:link href="<%="POSCustomerAction.do?action=viewPOSCustomer&bpartnerId=" + partnerId %>">
		   			<bean:write name="element" property="partnerName"/>
		   		</html:link>			
			</logic:equal>
			
			
			<logic:equal name="element" property="isCustomer" value="No">
				<html:link href="<%="POSVendorAction.do?action=viewVendorDetails1&bpartnerId=" + partnerId %>">
		   			<bean:write name="element" property="partnerName"/>
		   		</html:link>			
			</logic:equal>

		   </td>
		   <td class=<%=styleClass%>>
				<logic:equal name="element" property="paymentRule" value="<%=MOrder.PAYMENTRULE_Cash%>">
					<bean:message key="payment.rule.cash"/>
				</logic:equal>
				
				<logic:equal name="element" property="paymentRule" value="<%=MOrder.PAYMENTRULE_CreditCard%>">
					<bean:message key="payment.rule.card"/>
				</logic:equal>	

				<logic:equal name="element" property="paymentRule" value="<%=MOrder.PAYMENTRULE_Check%>">
					<bean:message key="payment.rule.cheque"/>
				</logic:equal>	
					
				<logic:equal name="element" property="paymentRule" value="<%=UdiConstants.PAYMENTRULE_MIXED%>">
					<bean:message key="payment.rule.mixed"/>
				</logic:equal>	
				
				<logic:equal name="element" property="paymentRule" value="<%=MOrder.PAYMENTRULE_OnCredit%>">
					Credit
				</logic:equal>					
		   </td>							   
		   <td class=<%=styleClass%>>
				<bean:define id="grandTotal" name="element" property="orderGrandTotal"/>
				<fmt:formatNumber value='${grandTotal}' type="currency" currencySymbol=" "/>
		   </td>
		    <td class=<%=styleClass%>>
				<bean:write name="element" property="amountPaid"/>
		   </td>
		    <td class=<%=styleClass%>>
				<bean:define id="grossProfit" name="element" property="grossProfit"/>
				<fmt:formatNumber value='${grossProfit}' type="currency" currencySymbol=" "/>
		   </td>
		   <td class=<%=styleClass%>>
		   
		   <logic:present cookie="preference.printing">
		   <logic:equal cookie="preference.printing" property="value" value="true">
		   		<a href="javascript:void(0);" onclick="printOrder('<%= element.getOrderId() %>')">
					<pos:message key="reprint"/>
				</a>
		   </logic:equal>
		   <logic:notEqual cookie="preference.printing" property="value" value="true">
		   		<html:link href="<%="ReprintOrderAction.do?action=reprintOrder&orderId=" + element.getOrderId() %>">
					<pos:message key="reprint"/>
				</html:link>
		   </logic:notEqual>
		   </logic:present>
		   
		   <logic:notPresent cookie="preference.printing">
		   		<html:link href="<%="ReprintOrderAction.do?action=reprintOrder&orderId=" + element.getOrderId() %>">
					<pos:message key="reprint"/>
				</html:link>
		   </logic:notPresent>	
		   		
		   </td>						
		</tr>
		</logic:iterate>    
		</logic:present>
		</table>
	</td>
</tr>			
</table>

<input type="button" onclick="history.back()" value="Go Back" class="bigbutton border"/>

<%@ include file="/jsp/include/pager.jsp" %>						
<%@ include file="/jsp/include/printOrderApplet2.jsp" %>	    									 					 
<%@ include file="/jsp/include/posFooter.jsp" %>
