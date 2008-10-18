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

<table class="main">
	<tr>
		<td>
			<logic:present name="<%= Constants.CASH_SUMMARY%>"> 
				<table class="display" border="1" width="300px">
					<tr>
						<th><pos:element columnName="CashType"/></th>
						<th class="numeric" colspan="2"><pos:element columnName="Amt"/></th>
					</tr>
					<tr>
						<td>
							<pos:element columnName="Invoice"/>
						</td>
						<td>
							<bean:define id="invoiceAmount" name="<%= Constants.CASH_SUMMARY%>" property="invoiceAmount"/>
							<fmt:formatNumber value='${invoiceAmount}' type="currency" currencySymbol='${currSymbole}'/>
						</td>
					</tr>
					<tr>
						<td>
							<pos:element columnName="amount.receipt"/>
						</td>
						<td>
							<bean:define id="generalReceiptsAmount" name="<%= Constants.CASH_SUMMARY%>" property="generalReceiptsAmount"/>
							<fmt:formatNumber value='${generalReceiptsAmount}' type="currency" currencySymbol='${currSymbole}'/>
						</td>
					</tr>
					<tr>
						<td>
							<pos:element columnName="amount.expense"/>
						</td>
						<td>
							<bean:define id="generalExpenseAmount" name="<%= Constants.CASH_SUMMARY%>" property="generalExpenseAmount"/>
							<fmt:formatNumber value='${generalExpenseAmount}' type="currency" currencySymbol='${currSymbole}'/>
						</td>
					</tr>
					<tr>
						<td>
							<pos:element columnName="amount.difference"/>
						</td>
						<td>
							<bean:define id="differenceAmount" name="<%= Constants.CASH_SUMMARY%>" property="differenceAmount"/>
							<fmt:formatNumber value='${differenceAmount}' type="currency" currencySymbol='${currSymbole}'/>
						</td>
					</tr>
					<tr>
						<td>
							<pos:element columnName="amount.transfer"/>
						</td>
						<td>
							<bean:define id="bankAcctTransferAmount" name="<%= Constants.CASH_SUMMARY%>" property="bankAcctTransferAmount"/>
							<fmt:formatNumber value='${bankAcctTransferAmount}' type="currency" currencySymbol='${currSymbole}'/>
						</td>
					</tr>
					<tr>
						<td>
							<pos:element columnName="ChargeAmt"/>
						</td>
						<td>
							<bean:define id="chargeAmount" name="<%= Constants.CASH_SUMMARY%>" property="chargeAmount"/>
							<fmt:formatNumber value='${chargeAmount}' type="currency" currencySymbol='${currSymbole}'/>
						</td>
					</tr>
				</table>
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
							<a href="javascript:showDetails()"><pos:element columnName="show.details"/></a>
						</span>
					</td>
					<td>
						&nbsp;
						<span id="hDets" style="display: none">
							<a href="javascript:hideDetails()"><pos:element columnName="hide.details"/></a>
						</span>
					</td>
				</tr>
			</table>
		</td>
	</tr>	
</table>
<table class="display">
<tr><td>
<div id="dets" style="display: none">
	<%@ include file="/jsp/pos/cashLineDetails.jsp" %>
</div>
</td>
</tr>
</table>

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
//-->
</script>		