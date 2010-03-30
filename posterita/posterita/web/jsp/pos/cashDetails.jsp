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
 * @author Ashley
--%>

<!--cashDetails.jsp-->
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.beans.ProductBean" %>
<%@ page import="org.posterita.user.WebUserInfo" %>
<%@ page import="org.posterita.lib.UdiConstants" %>
<%@ page import="org.posterita.struts.pos.CashAction" %>
<%@ page import="org.posterita.struts.pos.POSGoodsAction" %>
<%@ page import="org.compiere.process.DocAction" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

<logic:notPresent name="<%=WebUserInfo.NAME%>" scope="session">
	<jsp:forward page="/LoginHome.do"/>
</logic:notPresent>

<bean:define id="title"><pos:message textOnly="true" key="cash.book.history"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %> 

<table class="main">
	<tr>
		<td>		
			<%@ include file="/jsp/include/errors.jsp" %>	 
							   	
			<html:form action="/CashReportAction">
			<html:hidden property="action" value="<%=CashAction.GET_CASH_DETAILS_HISTORY %>"/>			
			<table align="right">
				<tr>
					<td>
						<html:select property="cashBookId" styleClass="text" onchange="submit()">
							<html:options collection="accessible.cashbooks" property="cashBookId" labelProperty="cashBookName"/>
						</html:select>
					</td>
					<%@ include file="/jsp/include/historyMonthYearFilter.jsp"%>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td>
		<logic:present name="<%=Constants.CASH_DETAILS%>">
		<logic:notEmpty name="<%=Constants.CASH_DETAILS%>">
		<%
			String url = "CashDetails.do";
			String collection = Constants.CASH_DETAILS;
		%>		
			<table class="display" border="1">
				<tr>
					<th><pos:message key="C_Cash_ID"/></th>
					<th><pos:message key="DocStatus"/></th>
					<th><pos:message key="Created"/></th>
					<th><pos:message key="Updated"/></th>
					<th class="numeric"><pos:message key="BeginningBalance" /></th>
					<th class="numeric"><pos:message key="ending.balance" /></th>
				</tr>					
				 <bean:define id="currSymbole" name="<%= Constants.CURRENCY_SYMBOLE %>"/>
				<logic:iterate offset="<%=offset%>" length="<%=length%>" id="element" indexId="count" name="<%=Constants.CASH_DETAILS %>" type="org.posterita.beans.CashBean">
				<tr>	
					<%
						String styleClass = "label";
						if ((count.intValue()%2) != 0)
							styleClass = "contentname";
					%>
									
				   <td class=<%=styleClass%>>
				   		<html:link href="CashLineReportAction.do?action=getCashLineDetails&cashId=<%= element.getCashId() %>">
				   			<bean:write name="element" property="name"/>
				   		</html:link>
				   </td>
				   <td class=<%=styleClass%>>
				   		<bean:write name="element" property="docStatus"/>
				   </td>		
				   <td class=<%=styleClass%>>
				   		<!--
				   		<bean:write name="element" property="dateCreated"/>
				   		-->
				   		<fmt:formatDate value='${element.dateCreated}' type="both" dateStyle="short" timeStyle="medium"/>
				   </td>	
				   <td class=<%=styleClass%>>
				   		<fmt:formatDate value='${element.dateUpdated}'  type="both" dateStyle="short" timeStyle="medium"/>
				   		<!--
				   		<bean:write name="element" property="dateUpdated"/>
				   		-->
				   </td>	

				    <td class=<%=styleClass%>>
						<fmt:formatNumber value='${element.beginingBalance}' type="currency" currencySymbol='${currSymbole}'/>
					</td>
				   
				   <td class=<%=styleClass%>>
						<fmt:formatNumber value='${element.endingBalance}' type="currency" currencySymbol='${currSymbole}'/>
					</td>	
				   
				</tr>
		</logic:iterate> 		
		</table>
		<%@ include file="/jsp/include/pager.jsp" %>	
		</logic:notEmpty>
		</logic:present>						
		</html:form>
		<!--
		Locale: <c:out value="${pageContext.request.locale.language}" />_<c:out value="${pageContext.request.locale.country}" />
		-->
		</td>
	</tr>
</table>

<%@ include file="/jsp/include/posFooter.jsp" %>