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


<!--getCurrentTillAmount.jsp-->
<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.beans.*" %>
<%@ page import="org.posterita.struts.pos.CashBookAction" %>


<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/posterita.tld" prefix="posterita"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

<logic:notPresent name="<%=WebUserInfo.NAME%>" scope="session">
	<jsp:forward page="/LoginHome.do"/>
</logic:notPresent>

<bean:define id="title"><pos:message textOnly="true" key="smenu.current.money.in.terminal"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %> 
<div class="space"></div>
<div style="width:400px">		    			
    <table class="display" align="left" border="1">
    <logic:present name="<%=Constants.CURRENT_TILL_AMOUNT%>">
    <bean:define id="currSymbole" name="<%= Constants.CURRENCY_SYMBOLE %>"/>
   	<tr>  	    
		<th nowrap="nowrap">	
			<pos:message key="pos.name"/>
		</th>   			          
        <th>
			<bean:write name="<%=Constants.CURRENT_TILL_AMOUNT%>" property="posName"/>
		</th>
	</tr>
	
	<tr>
    	<td nowrap="nowrap">	
       		<pos:message key="opening.balance"/>
    	</td>
   		<td>
			<fmt:formatNumber value='${currentTillAmount.beginingBalance}' type="currency" currencySymbol='${currSymbole}'/>
		</td>
  	</tr>
  	
  	
  	<tr>
    	<td nowrap="nowrap">	
       		<pos:message key="net.transaction"/>
    	</td>
   		<td>
			<fmt:formatNumber value='${currentTillAmount.statementDifference}' type="currency" currencySymbol='${currSymbole}'/>
		</td>
  	</tr>
				   
	<tr>
    	<td nowrap="nowrap">	
       		<pos:message key="cash.total"/>
    	</td>
   		<td>
			<fmt:formatNumber value='${currentTillAmount.cashTotal}' type="currency" currencySymbol='${currSymbole}'/>
		</td>
  	</tr>

	<tr>
		<td nowrap="nowrap">
			<pos:message key="card.total"/>
		</td>
		<td>				
			<fmt:formatNumber value='${currentTillAmount.cardTotal}' type="currency" currencySymbol='${currSymbole}'/>
		</td>
	</tr>
				   					   
	<tr>
		<td nowrap="nowrap">	
			<pos:message key="cheque.total"/>
		</td>
		<td>								
			<fmt:formatNumber value='${currentTillAmount.chequeTotal}' type="currency" currencySymbol='${currSymbole}'/>
		</td>
	</tr>

	<tr>
		<td nowrap="nowrap">	
			<pos:message key="GrandTotal"/>
		</td>
		<td>
			<fmt:formatNumber value='${currentTillAmount.tillGrandTotal}' type="currency" currencySymbol='${currSymbole}'/>
		</td>
	</tr>
	</logic:present>  		   
	</table>
</div>	    									 					 
<%@ include file="/jsp/include/posFooter.jsp" %>