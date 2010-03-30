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
 * @author Praveen,Alok
--%>

<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.beans.*" %>
<%@ page import="org.posterita.struts.pos.CashBookAction" %>
<%@ page import="org.compiere.process.DocumentEngine" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

<logic:notPresent name="<%=WebUserInfo.NAME%>" scope="session">
	<jsp:forward page="/LoginHome.do"/>
</logic:notPresent>

<bean:define id="title"><pos:message textOnly="true" key="close.cash.book"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %> 
<%@ include file="/jsp/include/errors.jsp" %>	  

<% int noOfOpenedCashbook = 0;%>
<html:form action="/CloseCashBookAction">
<html:hidden property="action" value="<%=CashBookAction.CLOSE_CASH_BOOK%>"/>	
<table class="display" border="1">
						
	<tr>
	 <th>
	 		Cash Book Name
	 </th>
	 
	  <th>
	 		Beginining Balance
	 </th>
	 
	  <th>
	 		Ending Balance
	 </th>
	 
	  <th>
	 		Net Transection
	 </th>
	  <th>
	 		Status
	 </th>
	 <th>
	 		Amount transfer 
	 </th>
	 
	 <th>
	 		Transfer Full Amount
	 </th>
	 </tr>
	
<logic:iterate indexId="count" id="element" name="<%=Constants.CASH_BOOK%>" type="org.posterita.beans.CashBookDetailBean">
<%
	String styleClass = "label";
	
	if ((count.intValue()%2) != 0)
		styleClass = "contentname";
%>	
<bean:define id="currSymbole" name="<%= Constants.CURRENCY_SYMBOLE %>"/>
<tr>	
	<td class="<%=styleClass%>">
		<bean:write name="element" property="cashBookName"/>
	</td>
	
	<td class="<%=styleClass%>">
		<bean:define id="beginingBalance" name="element" property="beginingBalance"/>
		<fmt:formatNumber value='${beginingBalance}'type="currency" currencySymbol='${currSymbole}'/>
		<html:hidden property="beginingBalanceAsString" value="<%= beginingBalance.toString() %>"/>
	</td>
	
	<td class="<%=styleClass%>">						     
		 <bean:define id="endingBalance" name="element" property="endingBalance"/>
		<fmt:formatNumber value='${endingBalance}' type="currency" currencySymbol='${currSymbole}'/>
		<html:hidden property="endingBalanceAsString" value="<%= endingBalance.toString() %>"/>							
	 </td>
	
	<td class="<%=styleClass%>">
		
		<bean:define id="statementDifference" name="element" property="statementDifference"/>
		<fmt:formatNumber value='${statementDifference}'type="currency" currencySymbol='${currSymbole}'/>
		
	</td>
	
	<td class="<%=styleClass%>">
		<bean:write name="element" property="docStatus"/>
		<html:hidden property="docStatus" value="<%=element.getDocStatus()%>"/>								
	</td>
	
	<td class="<%=styleClass%>">
		<html:text name="element" property="transferAmount" value="0" styleClass="text"/>
		<% noOfOpenedCashbook++; %>
	</td>

	<logic:equal name="element" property="docStatus" value="<%=DocumentEngine.STATUS_Drafted%>">
	<td class="<%=styleClass%>">
		<html:checkbox property="transferAllAmount" value="true"/>
	</td>
	</logic:equal>	
	
	
	</tr>	
	</logic:iterate>						
		
</table>					
<br>		
<div align="right">		
<%
	if (noOfOpenedCashbook > 0)
	{
%>
	<html:submit styleClass="submit smallbutton">
		&nbsp;
	</html:submit>
<%
	}
%>				
</div>	
 							
</html:form>    									 					 
<%@ include file="/jsp/include/posFooter.jsp" %>
