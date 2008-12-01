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


<!--viewBPartnerTrxDetails.jsp-->
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.beans.BPartnerInfoBean" %>
<%@ page import="org.posterita.user.WebUserInfo" %>
<%@ page import="org.posterita.lib.UdiConstants" %>
<%@ page import="org.posterita.struts.pos.BpartnerInfoAction" %>
<%@ page import="org.posterita.struts.pos.POSGoodsAction" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos" %>

<logic:notPresent name="<%=WebUserInfo.NAME%>" scope="session">
	<jsp:forward page="/POSLogin.do"/>
</logic:notPresent>

<bean:define id="title"><pos:message key="bpartner.trx.details" textOnly="true"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %> 

<div>
	<logic:present name="<%=Constants.BUSINESSPARTNER%>">
		<html:link href="<%="ViewPOSBPartnerInfo.do"%>">
		 <pos:message key="back"/>
		</html:link>
	</logic:present>
	
	<logic:present name="<%=Constants.DEBTORS%>">
		<html:link href="<%="InitPrintDunningAction.do?action=initPrintDunning"%>">
		 <pos:message key="back"/>
	</html:link>
	</logic:present>
</div>	

<logic:present name="<%=Constants.BUSINESSPARTNERINFO%>">
	<logic:iterate id="element" indexId="count" name="<%=Constants.BUSINESSPARTNERINFO %>" type="org.posterita.beans.BPartnerInfoBean">
<table class="display" border="0">

	<tr>
		<td align="left">
		  <b><pos:message key="Name"/>: <bean:write name="element" property="partnerName"/></b>	
		</td>
	</tr>
	<tr>
		<td align="left">
		  <b><pos:message key="OpenAmt"/>: <bean:write name="element" property="openAmt"/></b>	
		</td>
	</tr>
	<tr>
		<td>
		 <b><pos:message key="SO_CreditUsed"/>: <bean:write name="element" property="creditUsed"/></b>	
		</td>
	</tr>
	<tr>
		<td>
		  <b><pos:message key="SO_CreditLimit"/>: <bean:write name="element" property="creditLimit"/></b>	
		</td>
	</tr>
	<tr>
		<td>
		 <b> Revenue: <bean:write name="element" property="revenue"/></b>	
		</td>
	</tr>
	<tr>
		<td>
		  <b><pos:message key="SOCreditStatus"/>: <bean:write name="element" property="soCreditStatus"/></b>	
		</td>
	</tr>
	<tr>
		<td>
		  <b><pos:message key="FirstSale"/>: <bean:write name="element" property="bpfirstSale"/></b>	
		</td>
	</tr>
</table>
</logic:iterate>
</logic:present>
				
<table class="display" border="1">
	<caption>Product Info</caption>					
	<tr>
		<th><pos:message key="Product"/></th>
		<th><pos:message key="Qty"/></th>
		<th><pos:message key="TotalAmt"/></th>		
	</tr>	
	<logic:present name="<%=Constants.BUSINESSPARTNER_TRX_DETAILS%>">
	<logic:iterate id="element" indexId="count" name="<%=Constants.BUSINESSPARTNER_TRX_DETAILS %>" type="org.posterita.beans.BPartnerInfoBean">
	
	<tr>	
		<%
			String styleClass = "label";
			if ((count.intValue()%2) != 0)
				styleClass = "contentname";
		%>
	   					
	   <td class=<%=styleClass%>>
			<bean:write name="element" property="productName"/>
	   </td>
	   
	   <td class=<%=styleClass%>>
			<bean:write name="element" property="quantity"/>
	   </td>
	   						   
	   <td class=<%=styleClass%>>
			<bean:write name="element" property="amount"/>
	   </td>	   						   
	</tr>	
	</logic:iterate> 
	</logic:present>
</table>					
				
<div class="space">
</div>

<table class="display sortable" border="1" id="1111">	
	<caption>Order Info</caption>	
	<tr>		
		<th><pos:message key="DocumentNo"/></th>
		<th><pos:message key="date.created"/></th>
		<th><pos:message key="customervendor"/></th>
		<th><pos:message key="OrderType"/></th>
		<th><pos:message key="Grandtotal"/></th>
	</tr>	
		<logic:present name="<%=Constants.BUSINESSPARTNER_ORDER_DETAILS%>">
		<logic:iterate id="element" indexId="count" name="<%=Constants.BUSINESSPARTNER_ORDER_DETAILS %>" type="org.posterita.beans.POSHistoryBean">
		<tr>	
		<%
			String styleClass1 = "label";
			if ((count.intValue()%2) != 0)
				styleClass1 = "contentname";
		%>							
		   <td class=<%=styleClass1%>>
		   		<html:link href="<%="ViewPOSOrderAction.do?action=viewPOSOrders&orderId=" + element.getOrderId() %>">
					<bean:write name="element" property="documentNo"/>
				</html:link>		
		   </td>						
		   <td class=<%=styleClass1%>>
				<!--
				<bean:write name="element" property="dateAcct"/>
				-->
				<fmt:formatDate value='${element.dateAcct}'  type="both" dateStyle="short" timeStyle="medium"/>
		   </td>						   
		   <td class=<%=styleClass1%>>
				<bean:write name="element" property="partnerName"/>
		   </td>
		   <td class=<%=styleClass1%>>
				<bean:write name="element" property="orderType"/>
		   </td>							   
		   <td class=<%=styleClass1%>>
				<bean:write name="element" property="orderGrandTotal"/>
		   </td>						
		</tr>		
	</logic:iterate>    
	</logic:present>
</table>    									 					 
<%@ include file="/jsp/include/posFooter.jsp" %>
