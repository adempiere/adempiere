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



<!--viewPOSHistory.jsp-->
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
<%@ page import="org.displaytag.decorator.TableDecorator" %>


<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<%@page import="org.posterita.beans.POSHistoryBean"%>
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
<%@ include file="/jsp/include/errors.jsp" %> 

<bean:cookie id="displayCookie" name="preference.display"/>

<%
	String url = "ViewPOSHistory.do?orderType=" + orderType;
	String collection = Constants.POS_HISTORY;
	
	Integer display = new Integer("10");
	if (displayCookie.getValue() != null || !displayCookie.getValue().equals(""))
		display = new Integer(displayCookie.getValue());
%>
<table class="main">
<tr>
	<td>		
		<%@ include file="/jsp/include/errors.jsp" %>	 
						   	
		<html:form action="/ViewPOSHistoryAction">
		<html:hidden property="action" value="<%=POSReportAction.GET_POS_HISTORY %>"/>
	
				
		<table>
	   	<%@ include file="/jsp/include/posHistoryFilter2.jsp" %>
	   	</table>				
	   	
		<html:hidden property="orderType"/>
		
		<display:table id="row1" name="sessionScope.posHistory" class="mars" defaultsort="1" excludedParams="reprint" export="true" defaultorder="descending" pagesize="<%=display %>" decorator="org.posterita.decorator.OrderHistoryWrapper">
		  <display:column property="documentNo" title="Document No" class="idcol" sortable="true" headerClass="sortable" href="ViewPOSOrderAction.do?action=viewPOSOrders" paramId="orderId" paramProperty="orderId"/>
		  <display:column title="Invoice Doc No" property="invoiceRefNo" sortable="true" headerClass="sortable"/>
  		  <display:column property="docStatus" title="Status" sortable="true" headerClass="sortable"/>
		  <display:column property="dateAcct" title="Date Created" sortable="true" headerClass="sortable"/>
		  <display:column property="linkPartnerName" title="Customer/Vendor"/>		  		  
		  <display:column property="paymentRule" title="Payment By" sortable="true" headerClass="sortable" />
		  <display:column property="orderGrandTotal" title="Grand Total" sortable="true" headerClass="sortable" />		  		  
		  <display:column property="reprint" />
		</display:table>	   	

		</html:form>
	</td>
</tr>			
</table>
				
<%@ include file="/jsp/include/printOrderApplet2.jsp" %>	    									 					 
<%@ include file="/jsp/include/posFooter.jsp" %>
