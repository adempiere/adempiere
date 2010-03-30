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
<%@ page import="org.posterita.Constants"%>
<%@ page import="org.posterita.user.*"%>
<%@ page import="org.posterita.beans.*"%>
<%@ page import="org.posterita.struts.pos.CashBookAction"%>
<%@ page import="org.posterita.businesslogic.POSTerminalManager"%>


<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/posterita.tld" prefix="posterita"%>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>


<logic:notPresent name="<%=WebUserInfo.NAME%>" scope="session">
	<jsp:forward page="/LoginHome.do" />
</logic:notPresent>

<bean:define id="title">
	<pos:message textOnly="true" key="close.till" />
</bean:define>
<%@ include file="/jsp/include/posHeader.jsp"%>
<%@ include file="/jsp/include/errors.jsp"%>
<logic:present name="<%=Constants.END_OF_THE_DAY_DETAILS%>">

	<table border="0">
		<bean:define id="currSymbole"
			name="<%=Constants.END_OF_THE_DAY_DETAILS%>" property="currency" />
		<tr>
			<td><pos:message key="till.no" />:</td>
			<td><bean:write name="<%=Constants.END_OF_THE_DAY_DETAILS%>"
				property="tillName" /></td>
		</tr>

		<tr>
			<td><pos:message key="BeginningBalance" />:</td>
			<td><bean:define id="beginningBalance"
				name="<%=Constants.END_OF_THE_DAY_DETAILS%>"
				property="beginningBalance" /> <fmt:formatNumber
				value='${beginningBalance}' type="currency"
				currencySymbol='${currSymbole}' /></td>
		</tr>



		<tr>
			<td><pos:message key="net.cash.trx" />:</td>
			<td><bean:define id="statementDifference"
				name="<%=Constants.END_OF_THE_DAY_DETAILS%>" property="netCashTrx" />
			<fmt:formatNumber value='${statementDifference}' type="currency"
				currencySymbol='${currSymbole}' /></td>
		</tr>

		<tr>
			<td><pos:message key="till.balance.entered" />:</td>
			<td><bean:define id="transferAmount"
				name="<%=Constants.END_OF_THE_DAY_DETAILS%>"
				property="balanceEntered" /> <fmt:formatNumber
				value='${transferAmount}' type="currency"
				currencySymbol='${currSymbole}' /></td>
		</tr>

		<tr>
			<td><pos:message key="DifferenceAmt" />:</td>
			<td><bean:define id="differenceAmt"
				name="<%=Constants.END_OF_THE_DAY_DETAILS%>" property="difference" />
			<fmt:formatNumber value='${differenceAmt}' type="currency"
				currencySymbol='${currSymbole}' /></td>
		</tr>

		<tr>
			<td><pos:message key="EndingBalance" />:</td>
			<td><bean:define id="endingBalance"
				name="<%=Constants.END_OF_THE_DAY_DETAILS%>"
				property="endingBalance" /> <fmt:formatNumber
				value='${endingBalance}' type="currency"
				currencySymbol='${currSymbole}' /></td>
		</tr>

		<tr>
			<td nowrap="nowrap"><pos:message key="TotalAmt" /></td>
			<td><fmt:formatNumber value='${endOfTheDayDetails.cashTotal}'
				type="currency" currencySymbol='${currSymbole}' /></td>
		</tr>


		<tr>
			<td nowrap="nowrap"><pos:message key="card.amt.entered" />:</td>
			<td><fmt:formatNumber
				value='${endOfTheDayDetails.cardAmtEntered}' type="currency"
				currencySymbol='${currSymbole}' /></td>
		</tr>


		<tr>
			<td nowrap="nowrap"><pos:message key="card.amt.total" />:</td>
			<td><fmt:formatNumber value='${endOfTheDayDetails.cardTotal}'
				type="currency" currencySymbol='${currSymbole}' /></td>
		</tr>

		<tr>
			<td nowrap="nowrap"><pos:message key="DifferenceAmt" />:</td>
			<td><fmt:formatNumber
				value='${endOfTheDayDetails.cardDifference}' type="currency"
				currencySymbol='${currSymbole}' /></td>
		</tr>

		<tr>
			<td nowrap="nowrap"><pos:message key="cheque.amt.entered" />:</td>
			<td><fmt:formatNumber
				value='${endOfTheDayDetails.chequeAmtEntered}' type="currency"
				currencySymbol='${currSymbole}' /></td>
		</tr>

		<tr>
			<td nowrap="nowrap"><pos:message key="cheque.amt.total" />:</td>
			<td><fmt:formatNumber value='${endOfTheDayDetails.chequeTotal}'
				type="currency" currencySymbol='${currSymbole}' /></td>
		</tr>

		<tr>
			<td nowrap="nowrap"><pos:message key="DifferenceAmt" />:</td>
			<td><fmt:formatNumber
				value='${endOfTheDayDetails.chequeDifference}' type="currency"
				currencySymbol='${currSymbole}' /></td>
		</tr>




		<tr>
			<td nowrap="nowrap"><pos:message key="GrandTotal" />:</td>
			<td><fmt:formatNumber value='${endOfTheDayDetails.grandTotal}'
				type="currency" currencySymbol='${currSymbole}' /></td>
		</tr>


	</table>
	<bean:define id="reportURL" name="<%= Constants.REPORT_URL %>" />
	<a href="<%=reportURL%>"><pos:message key="view.report" />:</a>
</logic:present>

<%@ include file="/jsp/include/printOrderApplet2.jsp"%>
<logic:present cookie="preference.printing">
	<logic:equal cookie="preference.printing" property="value" value="true">
		<bean:cookie id="cookie" name="preference.printer" />
		<script>
		function printTillData()
		{
			document.applets[0].setPrinterName(<%="'" + cookie.getValue() + "'"%>);
			var url = '<%=basePath + "ClosePOSTillAction.do?action=getClosedTillData"%>';
			document.applets[0].printURL(url);
		}
	
		window.onload = function(e){printTillData()};
	</script>
	</logic:equal>
</logic:present>
<%@ include file="/jsp/include/posFooter.jsp"%>