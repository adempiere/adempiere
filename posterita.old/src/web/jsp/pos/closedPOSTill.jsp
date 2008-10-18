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

<!--closePOSTill.jsp-->
<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.beans.*" %>
<%@ page import="org.posterita.struts.pos.CashBookAction" %>
<%@ page import="org.posterita.businesslogic.POSTerminalManager" %>


<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/posterita.tld" prefix="posterita"%>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>


<logic:notPresent name="<%=WebUserInfo.NAME%>" scope="session">
	<jsp:forward page="/LoginHome.do"/>
</logic:notPresent>

<bean:define id="title"><pos:element textOnly="true" columnName="close.till"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>	   
<%@ include file="/jsp/include/errors.jsp" %>
<logic:present name="<%=Constants.END_OF_THE_DAY_DETAILS%>">
	
	<table border="0">
	<bean:define id="currSymbole" name="<%= Constants.CURRENCY_SYMBOLE %>"/>
  <tr>
    <td>
  		  <pos:element columnName="till.no"/>:
    </td>
    <td>
   		 <bean:write name="<%=Constants.END_OF_THE_DAY_DETAILS%>" property="cashBookName"/>
    </td>
  </tr>
  
  <tr>
    <td>
  		 <pos:element columnName="BeginningBalance"/>:
    </td>
    <td>
   		
   		 <bean:define id="beginingBalance" name="<%=Constants.END_OF_THE_DAY_DETAILS%>" property="beginingBalance"/>
		<fmt:formatNumber value='${beginingBalance}'type="currency" currencySymbol='${currSymbole}'/>
    </td>
  </tr>
  
  
  
  <tr>
    <td>
  		  <pos:element  columnName="net.cash.trx"/>:
    </td>
    <td>
    		<bean:define id="statementDifference" name="<%=Constants.END_OF_THE_DAY_DETAILS%>" property="statementDifference"/>
			<fmt:formatNumber value='${statementDifference}'type="currency" currencySymbol='${currSymbole}'/>
   			
    </td>
  </tr>
  
  <tr>
    <td>
  		  <pos:element  columnName="till.balance.entered"/>:
    </td>
    <td>
      		<bean:define id="transferAmount" name="<%=Constants.END_OF_THE_DAY_DETAILS%>" property="transferAmount"/>
			<fmt:formatNumber value='${transferAmount}'type="currency" currencySymbol='${currSymbole}'/>
    </td>
  </tr>
  
  <tr>
    <td>
  		  <pos:element columnName="DifferenceAmt"/>:
    </td>
    <td>
    	<bean:define id="differenceAmt" name="<%=Constants.END_OF_THE_DAY_DETAILS%>" property="differenceAmt"/>
			<fmt:formatNumber value='${differenceAmt}'type="currency" currencySymbol='${currSymbole}'/>
    </td>
  </tr>
  
  <tr>
    <td>
  		  <pos:element columnName="EndingBalance"/>:
    </td>
    <td>
    	 <bean:define id="endingBalance" name="<%=Constants.END_OF_THE_DAY_DETAILS%>" property="endingBalance"/>
		<fmt:formatNumber value='${endingBalance}'type="currency" currencySymbol='${currSymbole}'/>
   		
    </td>
  </tr>
  
  	
  
  
		<logic:present name="<%=Constants.CURRENT_TILL_AMOUNT_POS%>">
		<tr>
    	<td nowrap="nowrap">	
       		<pos:element columnName="TotalAmt"/>
    	</td>
   		<td>
			<fmt:formatNumber value='${currentTillAmountPOS.cashTotal}' type="currency" currencySymbol='${currSymbole}'/>
		</td>
  	</tr>


	<tr>
		<td nowrap="nowrap">
			<pos:element columnName="card.amt.entered"/>:
		</td>
		<td>				
			<fmt:formatNumber value='${endOfTheDayDetails.cardTotal}' type="currency" currencySymbol='${currSymbole}'/>
		</td>
	</tr>
			

	<tr>
		<td nowrap="nowrap">
			<pos:element columnName="card.amt.total"/>:
		</td>
		<td>				
			<fmt:formatNumber value='${currentTillAmountPOS.cardTotal}' type="currency" currencySymbol='${currSymbole}'/>
		</td>
	</tr>
	
	<tr>
		<td nowrap="nowrap">
			<pos:element columnName="DifferenceAmt"/>:
		</td>
		<td>				
			<fmt:formatNumber value='${endOfTheDayDetails.cardDifference}' type="currency" currencySymbol='${currSymbole}'/>
		</td>
	</tr>
			
	<tr>
		<td nowrap="nowrap">	
			<pos:element columnName="cheque.amt.entered"/>:
		</td>
		<td>								
			<fmt:formatNumber value='${endOfTheDayDetails.chequeTotal}' type="currency" currencySymbol='${currSymbole}'/>
		</td>
	</tr>
		   					   
	<tr>
		<td nowrap="nowrap">	
			<pos:element columnName="cheque.amt.total"/>:
		</td>
		<td>								
			<fmt:formatNumber value='${currentTillAmountPOS.chequeTotal}' type="currency" currencySymbol='${currSymbole}'/>
		</td>
	</tr>
	
	<tr>
		<td nowrap="nowrap">	
			<pos:element columnName="DifferenceAmt"/>:
		</td>
		<td>								
			<fmt:formatNumber value='${endOfTheDayDetails.chequeDifference}' type="currency" currencySymbol='${currSymbole}'/>
		</td>
	</tr>
	

	

	<tr>
		<td nowrap="nowrap">	
			<pos:element columnName="GrandTotal"/>:
		</td>
		<td>
			<fmt:formatNumber value='${currentTillAmountPOS.tillGrandTotal}' type="currency" currencySymbol='${currSymbole}'/>
		</td>
	</tr>
</logic:present>  



	   

</table>
<bean:define id="reportURL" name="<%= Constants.REPORT_URL %>"/>
<a href="<%=reportURL%>"><pos:element columnName="view.report"/>:</a>
</logic:present>


	    			

<script>
function printTillData()
{
	var url = '<%=basePath + "ClosePOSTillAction.do?action=getClosedTillData"%>';
	document.applets[0].printURL(url);
}

window.onload = function(e){printTillData()};
</script>

<%@ include file="/jsp/include/printOrderApplet2.jsp" %>
<%@ include file="/jsp/include/posFooter.jsp" %>