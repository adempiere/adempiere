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


<!--viewPartialPOSOrderHistory.jsp-->
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.beans.ProductBean" %>
<%@ page import="org.posterita.user.WebUserInfo" %>
<%@ page import="org.posterita.lib.UdiConstants" %>
<%@ page import="org.posterita.struts.pos.POSReportAction" %>
<%@ page import="org.posterita.struts.pos.POSGoodsAction" %>

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

<bean:define id="title"><pos:message key="partial.pos.order.history" textOnly="true"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %> 

<table class="main">
<tr>
	<td>		
		<%@ include file="/jsp/include/errors.jsp" %>	 
						   	
		<html:form action="/ViewPartialPOSOrderHistoryAction">
		<html:hidden property="action" value="<%=POSReportAction.GET_PARTIAL_POS_HISTORY %>"/>		
	   	<%@ include file="/jsp/include/historyfilter.jsp" %>
	   	
	   	<%
	   		String url = "ViewPartialPOSOrderHistory.do";
	   		String collection = Constants.POS_HISTORY;
	   	%>
	   	<logic:present name="<%=Constants.POS_HISTORY%>">
	   	<logic:notEmpty name="<%=Constants.POS_HISTORY%>">	   						
		<table class="display" id="1111" border="1">
		<tr>
			<th><pos:message key="DocumentNo"/></th>
			<th><pos:message key="date.created"/></th>
			<th><pos:message key="customervendor"/></th>
			<th class="numeric"><pos:message key="Grandtotal"/></th>
			<th>&nbsp;</th>
		</tr>		
		<logic:iterate offset="<%=offset%>" length="<%=length%>" id="element" indexId="count" name="<%=Constants.POS_HISTORY %>" type="org.posterita.beans.POSHistoryBean">
		<tr>	
			<%
				String styleClass = "label";
				if ((count.intValue()%2) != 0)
					styleClass = "contentname";
			%>
		
							
		   <td class=<%=styleClass%>>
		   		<html:link href="ViewPOSOrderAction.do?action=viewPOSOrders&orderId=<%= element.getOrderId() %>">
					<bean:write name="element" property="documentNo"/>
				</html:link>		
		   </td>						
		   <td class=<%=styleClass%>>				
				<fmt:formatDate value='${element.dateAcct}'  type="both" dateStyle="short" timeStyle="medium"/>
		   </td>						   
		   <td class=<%=styleClass%>>
				<bean:write name="element" property="partnerName"/>
		   </td>						   
		   <td class=<%=styleClass%>>
				<bean:define id="grandTotal" name="element" property="orderGrandTotal"/>
				<fmt:formatNumber value='${grandTotal}' type="currency" currencySymbol=" "/>
		   </td>
		   			
		</tr>
		</logic:iterate> 
		</table>
		<%@ include file="/jsp/include/pager.jsp" %>	
		</logic:notEmpty>   
		</logic:present>								
		</html:form>
	</td>
</tr>			
</table>						
	    									 					 
<%@ include file="/jsp/include/posFooter.jsp" %>
