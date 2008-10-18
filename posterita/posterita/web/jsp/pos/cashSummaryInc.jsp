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
<%@ page import="org.posterita.Constants" %>

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<table class="main">
	<tr>
		<td width="75%" valign="top">
			<logic:present name="<%= Constants.CASH_SUMMARY%>"> 
				<table class="display" border="1" width="300px">
					<tr>
						<th><pos:message key="Type"/></th>
						<th class="numeric" colspan="2"><pos:message key="Amt"/></th>
					</tr>
					<logic:present name="<%= Constants.CASH_SUMMARY%>" property="beginingBalance">
						<tr>
							<td>
								<pos:message key="Beginning Balance"/>
							</td>
							<td>
								<bean:define id="beginingBalance" name="<%= Constants.CASH_SUMMARY%>" property="beginingBalance"/>
								<fmt:formatNumber value='${beginingBalance}' type="currency" currencySymbol='${currSymbole}'/>
							</td>
						</tr>
					</logic:present>
					<tr>
						<td>
							<pos:message key="Invoice"/>
						</td>
						<td>
							<bean:define id="invoiceAmount" name="<%= Constants.CASH_SUMMARY%>" property="invoiceAmount"/>
							<fmt:formatNumber value='${invoiceAmount}' type="currency" currencySymbol='${currSymbole}'/>
						</td>
					</tr>
					<tr>
						<td>
							<pos:message key="amount.receipt"/>
						</td>
						<td>
							<bean:define id="generalReceiptsAmount" name="<%= Constants.CASH_SUMMARY%>" property="generalReceiptsAmount"/>
							<fmt:formatNumber value='${generalReceiptsAmount}' type="currency" currencySymbol='${currSymbole}'/>
						</td>
					</tr>
					<tr>
						<td>
							<pos:message key="amount.expense"/>
						</td>
						<td>
							<bean:define id="generalExpenseAmount" name="<%= Constants.CASH_SUMMARY%>" property="generalExpenseAmount"/>
							<fmt:formatNumber value='${generalExpenseAmount}' type="currency" currencySymbol='${currSymbole}'/>
						</td>
					</tr>
					<tr>
						<td>
							<pos:message key="amount.difference"/>
						</td>
						<td>
							<bean:define id="differenceAmount" name="<%= Constants.CASH_SUMMARY%>" property="differenceAmount"/>
							<fmt:formatNumber value='${differenceAmount}' type="currency" currencySymbol='${currSymbole}'/>
						</td>
					</tr>
					<tr>
						<td>
							<pos:message key="amount.transfer"/>
						</td>
						<td>
							<bean:define id="bankAcctTransferAmount" name="<%= Constants.CASH_SUMMARY%>" property="bankAcctTransferAmount"/>
							<fmt:formatNumber value='${bankAcctTransferAmount}' type="currency" currencySymbol='${currSymbole}'/>
						</td>
					</tr>
					<tr>
						<td>
							<pos:message key="ChargeAmt"/>
						</td>
						<td>
							<bean:define id="chargeAmount" name="<%= Constants.CASH_SUMMARY%>" property="chargeAmount"/>
							<fmt:formatNumber value='${chargeAmount}' type="currency" currencySymbol='${currSymbole}'/>
						</td>
					</tr>
					<logic:present name="<%= Constants.CASH_SUMMARY%>" property="endingBalance">
						<tr>
							<td>
								<pos:message key="Ending Balance"/>
							</td>
							<td>
								<bean:define id="endingBalance" name="<%= Constants.CASH_SUMMARY%>" property="endingBalance"/>
								<fmt:formatNumber value='${endingBalance}' type="currency" currencySymbol='${currSymbole}'/>
							</td>
						</tr>
					</logic:present>
				</table>
			</logic:present>
		</td>
		<td width="25%" align="center">
			<logic:present name="<%=Constants.CLOSE_TILL_PRINT_DATA%>">
			<pre>
			<bean:write name="<%=Constants.CLOSE_TILL_PRINT_DATA%>" filter="false"/>
			</pre>
			</logic:present>
		</td>
	</tr>
	<tr>
		<td>
			<table>
				<tr>
					<td>
						&nbsp;
						<span id="sDets">
							<a href="javascript:showDetails()"><pos:message key="show.details"/></a>
						</span>
					</td>
					<td>
						&nbsp;
						<span id="hDets" style="display: none">
							<a href="javascript:hideDetails()"><pos:message key="hide.details"/></a>
						</span>
					</td>
				</tr>
			</table>
		</td>
	</tr>	
</table>

<div id="dets" style="display: none">
	<display:table id="row" name="<%=Constants.CASH_LINE_DETAILS%>" class="displaytag" defaultsort="2" export="true" defaultorder="descending" requestURI="<%=requestURI%>">
		<display:column property="cashTypeName" titleKey="CashType" media="pdf csv rtf" sortable="true" style="width:150px"/>
		<display:column media="html" titleKey="CashType" sortable="true">
			<logic:notEqual name="row" property="invoiceId" value="0">
				<html:link href="ViewInvoiceAction.do?action=viewOrder" paramName="row" paramId="documentId" paramProperty="invoiceId">
					<bean:write name="row" property="cashTypeName"/>
				</html:link>
			</logic:notEqual>
			<logic:equal name="row" property="invoiceId" value="0">
				<bean:write name="row" property="cashTypeName"/>
			</logic:equal>
		</display:column>
		<display:column property="dateCreated" titleKey="Created" format="{0,date,medium} {0,time,short}" media="all" sortable="true" style="width:200px"/>
		<display:column property="description" titleKey="description" media="all"/>
		<display:column property="amount" class="amount" titleKey="Amt" format="{0,number,#,##0.00;(#,##0.00)}" style="width:150px" media="all"/>
	</display:table>
</div>

<script type="text/javascript">
<!--
	function showDetails()
	{
		document.getElementById("hDets").style.display = "inline";
		document.getElementById("sDets").style.display = "none";
		document.getElementById("dets").style.display = "inline";
	}
	
	function hideDetails()
	{
		document.getElementById("hDets").style.display = "none";
		document.getElementById("sDets").style.display = "inline";
		document.getElementById("dets").style.display = "none";
	}
	
	function initScreen()
	{
		var location = document.location.href;
		if(location != null && location.indexOf("-s=") != -1 && location.indexOf("-o=") != -1)
		{
			showDetails();
		}
	}
	
	initScreen();
//-->
</script>		