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
 * @author sendy
--%>

<!--salesDetailsPerProduct.jsp-->
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
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

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


<bean:parameter id="orderType" name="orderType" value="<%= request.getSession().getAttribute(Constants.POS_HISTORY_ORDER_TYPE).toString() %>"/>

<bean:define id="title">	
	<logic:equal name="orderType" value="<%=UDIOrderTypes.CUSTOMER_RETURN_ORDER.getOrderType()%>">
		<pos:message key="smenu.customer.return.history.id" textOnly="true"/>				
	</logic:equal>					
	<logic:equal name="orderType" value="<%=UDIOrderTypes.POS_ORDER.getOrderType()%>">
		<pos:message key="smenu.cash.sales.history" textOnly="true"/>				
	</logic:equal>
	<logic:equal name="orderType" value="<%=UDIOrderTypes.CREDIT_ORDER.getOrderType()%>">
		<pos:message key="smenu.credit.sales.history" textOnly="true"/>			
	</logic:equal>
	<logic:equal name="orderType" value="<%=UDIOrderTypes.POS_GOODS_RECEIVE_NOTE.getOrderType()%>">
		<pos:message key="smenu.goods.received.note.history" textOnly="true"/>				
	</logic:equal>
	<logic:equal name="orderType" value="<%=UDIOrderTypes.POS_GOODS_RETURN_NOTE.getOrderType()%>">
		<pos:message key="smenu.goods.returned.note.history" textOnly="true"/>				
	</logic:equal>
	<logic:equal name="orderType" value="<%=UDIOrderTypes.CREDIT_MEMO.getOrderType()%>">
		<pos:message key="smenu.credit.memo.history.id" textOnly="true"/>			
	</logic:equal>	
</bean:define>

<%@ include file="/jsp/include/posHeader.jsp" %>

<%
	String url = "ViewPOSHistory.do?orderType=" + orderType;
	String collection = Constants.POS_HISTORY;
%>

<table class="main">
<tr>
	<td>		
		<display:table id="row1" name="sessionScope.posHistory" class="displaytag" defaultsort="1" export="true" 
			excludedParams="reprint" defaultorder="descending" pagesize="<%=pageSize.intValue() %>"
			requestURI="ViewPOSHistoryAction.do" sort="list">
		  <display:column property="documentNo" titleKey="DocumentNo" sortable="true"  
		  		href="ViewPOSOrderAction.do?action=viewPOSOrders" paramId="orderId" paramProperty="orderId" media="html"/>
		  <display:column property="documentNo" titleKey="DocumentNo" sortable="true" media="csv pdf rtf"/>
		  <display:column property="invoiceDocumentNo" titleKey="invoice.no" sortable="true"/>
  		  <display:column property="docStatus" titleKey="status" sortable="true"/>
		  <display:column property="dateOrdered" titleKey="date.ordered" sortable="true" format="{0,date,medium}"/>
		  <display:column property="partnerName" titleKey="customervendor" media="pdf csv rtf"/>
		  <display:column media="html" titleKey="customervendor" sortable="true">
		  	<logic:equal name="row1" property="isCustomer" value="true">
		  		<html:link href="POSCustomerAction.do?action=viewPOSCustomer" paramName="row1" paramId="bpartnerId" paramProperty="bpartnerId">
		  			<bean:write name="row1" property="partnerName"/>
		  		</html:link>
		  	</logic:equal>
		  	<logic:equal name="row1" property="isCustomer" value="false">
		  		<html:link href="POSVendorAction.do?action=viewVendorDetails1" paramName="row1" paramId="bpartnerId" paramProperty="bpartnerId">
		  			<bean:write name="row1" property="partnerName"/>
		  		</html:link>
		  	</logic:equal>
		  </display:column>
		  <display:column property="paymentRule" titleKey="payment.by" sortable="true" headerClass="sortable"/>
		  <display:column property="orderGrandTotal" titleKey="GrandTotal" sortable="true" format="{0,number,#,##0.00;(#,##0.00)}" class="numeric"/>
		  <display:column property="amountPaid" titleKey="AmountPaid" sortable="true" format="{0,number,#,##0.00;(#,##0.00)}" class="numeric"/>
		  <display:column property="reprint" titleKey="" media="html"/>
		</display:table>	
	</td>
</tr>			
</table>			
<%@ include file="/jsp/include/printOrderApplet2.jsp" %>	    									 					 
<%@ include file="/jsp/include/posFooter.jsp" %>
