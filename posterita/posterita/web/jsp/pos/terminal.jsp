<%--
 *  Product: Posterita Web-Based POS and Adempiere Plugin
 *  Copyright (C) 2008  Posterita Ltd
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
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.beans.*" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

<%@page import="org.posterita.struts.pos.TerminalAction"%>
<logic:notPresent name="<%=WebUserInfo.NAME%>" scope="session">
	<jsp:forward page="/LoginHome.do"/>
</logic:notPresent>

<bean:define id="title"><pos:message textOnly="true" key="terminal"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>	   
<%@ include file="/jsp/include/errors.jsp" %>

<html:form action="TerminalAction" focus="name">
	<html:hidden property="action"/>
	<html:hidden property="terminalId"/>
	<bean:define id="strReadOnly" name="TerminalForm" property="readOnly"/>	
	<bean:define id="terminalId" name="TerminalForm" property="terminalId"/>
	<%
		boolean readonly = new Boolean((String)strReadOnly).booleanValue();
		boolean orgReadOnly = true;
		
		if (terminalId != null)
		{
			try
			{
				orgReadOnly = (Integer.parseInt((String)terminalId) > 0);
			}
			catch (Exception ex)
			{}
		}
	%>
	<table align="left" cellpadding="5" width="100%">
		<tr>
			<td>
				<table align="left">
					<tr>
						<td>
							<label><pos:message key="Name"/>:</label><mandatory>*</mandatory>
						</td>
						<td>
							<html:text property="name" readonly="<%=readonly%>" size="40"/>
						</td>
					</tr>
					<tr>
						<td>
							<label><pos:message key="Organization"/>:</label><mandatory>*</mandatory>
						</td>
					
						<td>
							<html:select property="orgId" styleClass="text" disabled="<%=readonly%>" onchange="javascript:orgChanged()">
								<html:options collection="<%=Constants.ACCESSIBLE_ORGS%>" property="key" labelProperty="name"/>
						 	</html:select>
						</td>
					</tr>
					<tr>
						<td>
							<label><pos:message key="IsActive"/>:</label>
						</td>
						<td>
							<logic:equal name="TerminalForm" property="readOnly" value="true">
								<bean:write name="TerminalForm" property="isActive"/>
							</logic:equal>
							<logic:notEqual name="TerminalForm" property="readOnly" value="true">
								<html:checkbox property="isActive" value="true" disabled="<%=readonly%>"/>
							</logic:notEqual>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<fieldset style="height:160px">
					<legend><pos:message key="defaults"/></legend>
						<table>
							<tr>
								<td>
									<label><pos:message key="cash.customer"/>:</label>
								</td>
								<td>
									<pos:bpartner name="TerminalForm" property="bpartnerId" isCustomer="true" disabled="<%=readonly%>" size="40"/>
								</td>
								<td>
									<label><pos:message key="purchasepricelist"/>:</label>
								</td>
								<td>
									<html:select property="purchasePriceListId" styleClass="text" disabled="<%=readonly%>">
										<html:options collection="<%=Constants.PURCHASE_PRICELISTS%>" property="priceListId" labelProperty="name"/>
						 			</html:select>
								</td>
							</tr>
							<tr>
								<td>
									<label><pos:message key="warehouse"/>:</label>
								</td>
								<td>
									<html:select property="warehouseId" styleClass="text" disabled="<%=readonly%>">
										<html:options collection="<%=Constants.WAREHOUSES%>" property="warehouseId" labelProperty="warehouseName"/>
						 			</html:select>
								</td>
								<td>
									<label><pos:message key="salespricelist"/>:</label>
								</td>
								<td>
									<html:select property="salesPriceListId" styleClass="text" disabled="<%=readonly%>">
										<html:options collection="<%=Constants.SALES_PRICELISTS%>" property="priceListId" labelProperty="name"/>
						 			</html:select>
								</td>
							</tr>
							<tr>
								<td>
									<label><pos:message key="cashbook"/>:</label>
								</td>
								<td>
									<html:select property="cashBookId" styleClass="text" disabled="<%=readonly%>">
										<html:options collection="<%=Constants.CASH_BOOK%>" property="cashBookId" labelProperty="cashBookName"/>
						 			</html:select>
								</td>
								<td>
									<label><pos:message key="cardBankAcct"/>:</label>
								</td>
								<td>
									<html:select property="cardBankAccountId" styleClass="text" disabled="<%=readonly%>">
										<html:options collection="<%=Constants.BANK_ACCOUNTS%>" property="bankAccountId" labelProperty="summary"/>
						 			</html:select>
								</td>
							</tr>
							<tr>
								<td>
									
								</td>
								<td>
								</td>
								<td>
									<label><pos:message key="checkBankAcct"/>:</label>
								</td>
								<td>
									<html:select property="checkBankAccountId" styleClass="text" disabled="<%=readonly%>">
										<html:options collection="<%=Constants.BANK_ACCOUNTS%>" property="bankAccountId" labelProperty="summary"/>
						 			</html:select>
								</td>
							</tr>
						</table>
				</fieldset>
			</td>
		</tr>
		<tr>
			<td>
				<fieldset style="height:160px">
					<legend><pos:message key="transfers"/></legend>
					<table>
						<tr>
							<td>
								<label><pos:message key="cashbook"/>:</label><mandatory>*</mandatory>
							</td>
							<td>
								<html:select property="cashbookTransferType" styleClass="text" disabled="<%=readonly%>" onchange="javascript:transferTypeChanged('cashbook')">
									<html:options collection="<%=Constants.TERMINAL_TRANSFER_TYPE%>" property="value" labelProperty="name"/>
					 			</html:select>
							</td>
							<td>
								<html:select property="cashbookTransferCashbookId" styleClass="text" disabled="<%=readonly%>" style="display:none">
									<html:options collection="<%=Constants.ACCESSIBLE_CASHBOOKS%>" property="cashBookId" labelProperty="cashBookName"/>
					 			</html:select>
								<html:select property="cashbookTransferBankAccountId" styleClass="text" disabled="<%=readonly%>" style="display:none">
									<html:options collection="<%=Constants.ACCESSIBLE_BANKACCOUNTS%>" property="bankAccountId" labelProperty="summary"/>
					 			</html:select>
							</td>
						</tr>
						<tr>
							<td>
								<label><pos:message key="Card"/>:</label>
							</td>
							<td>
								<html:select property="cardTransferType" styleClass="text" disabled="<%=readonly%>" onchange="javascript:transferTypeChanged('card')">
									<html:options collection="<%=Constants.TERMINAL_TRANSFER_TYPE%>" property="value" labelProperty="name"/>
					 			</html:select>
							</td>
							<td>
								<html:select property="cardTransferCashbookId" styleClass="text" disabled="<%=readonly%>" style="display:none">
									<html:options collection="<%=Constants.ACCESSIBLE_CASHBOOKS%>" property="cashBookId" labelProperty="cashBookName"/>
					 			</html:select>
								<html:select property="cardTransferBankAccountId" styleClass="text" disabled="<%=readonly%>" style="display:none">
									<html:options collection="<%=Constants.ACCESSIBLE_BANKACCOUNTS%>" property="bankAccountId" labelProperty="summary"/>
					 			</html:select>
							</td>
						</tr>
						<tr>
							<td>
								<label><pos:message key="check"/>:</label>
							</td>
							<td>
								<html:select property="checkTransferType" styleClass="text" disabled="<%=readonly%>" onchange="javascript:transferTypeChanged('check')">
									<html:options collection="<%=Constants.TERMINAL_TRANSFER_TYPE%>" property="value" labelProperty="name"/>
					 			</html:select>
							</td>
							<td>
								<html:select property="checkTransferCashbookId" styleClass="text" disabled="<%=readonly%>" style="display:none">
									<html:options collection="<%=Constants.ACCESSIBLE_CASHBOOKS%>" property="cashBookId" labelProperty="cashBookName"/>
					 			</html:select>
								<html:select property="checkTransferBankAccountId" styleClass="text" disabled="<%=readonly%>" style="display:none">
									<html:options collection="<%=Constants.ACCESSIBLE_BANKACCOUNTS%>" property="bankAccountId" labelProperty="summary"/>
					 			</html:select>
							</td>
						</tr>
					</table>
				</fieldset>
			</td>
		</tr>
		<tr>
			<td>
				<fieldset style="height:160px">
					<legend><pos:message key="settings"/></legend>
					<table>
						<tr>
							<td>
								<label><pos:message key="locked"/>:</label>
							</td>
							<td>
								<logic:equal name="TerminalForm" property="readOnly" value="true">
									<bean:write name="TerminalForm" property="locked"/>
								</logic:equal>
								<logic:notEqual name="TerminalForm" property="readOnly" value="true">
									<html:checkbox property="locked" disabled="true"/>
								</logic:notEqual>
								<span style="width:50px">&nbsp;</span>
								<logic:equal name="TerminalForm" property="action" value="<%=TerminalAction.VIEW_TERMINAL%>">
									<span>
										<logic:equal name="TerminalForm" property="locked" value="true">
											<input type="button" value="Unlock" onclick="javascript:lockTerminal(false)"/>
										</logic:equal>
										<logic:notEqual name="TerminalForm" property="locked" value="true">
											<input type="button" value="Lock" onclick="javascript:lockTerminal(true)"/>
										</logic:notEqual>
									</span>
								</logic:equal>
							</td>
						</tr>
						<tr>
							<td>
								<label><pos:message key="autolock"/>:</label>
							</td>
							<td>
								<logic:equal name="TerminalForm" property="readOnly" value="true">
									<bean:write name="TerminalForm" property="autoLock"/>
									<html:checkbox property="autoLock" value="true" disabled="<%=readonly%>" onchange="javascript:autoLockChanged()" style="display:none"/>
								</logic:equal>
								<logic:notEqual name="TerminalForm" property="readOnly" value="true">
									<html:checkbox property="autoLock" value="true" disabled="<%=readonly%>" onchange="javascript:autoLockChanged()"/>
								</logic:notEqual>
								<span style="width:50px">&nbsp;</span>
								<span id="lockingTimePanel" style="display:none">Time to keep locked <html:text property="lockingTime" readonly="<%=readonly%>" size="3" maxlength="3"/> mins</span>
							</td>
							
						</tr>
					</table>
				</fieldset>
			</td>
		</tr>
		<tr>
			<td></td>
			<td align="right">
			<logic:equal name="TerminalForm" property="action" value="<%=TerminalAction.VIEW_TERMINAL%>">
				<html:button property="button" styleClass="newbutton" accesskey="s" onclick="javascript:copyTerminal()">
					Copy As
	   			</html:button>
   			</logic:equal>
   			<logic:equal name="TerminalForm" property="action" value="<%=TerminalAction.VIEW_TERMINAL%>">
				<html:button property="button" styleClass="newbutton" accesskey="s" onclick="javascript:editTerminal()">
					Edit
	   			</html:button>
   			</logic:equal>
   			<logic:equal name="TerminalForm" property="readOnly" value="false">
				<html:button property="button" styleClass="newbutton" onclick="submit()" accesskey="s" disabled="<%=readonly%>">
					Save
	   			</html:button>
	   		</logic:equal>
	   		<logic:equal name="TerminalForm" property="readOnly" value="false">
				<html:button property="button" styleClass="newbutton" disabled="<%=readonly%>" onclick="javascript:cancelTerminalChanges()">
					Cancel
	   			</html:button>
	   		</logic:equal>
			</td>
		</tr>
	</table>
</html:form>
<script>

	function orgChanged()
	{
		var action = $FElement('action');
		action.value = 'changeOrganisation';
		var form = $FElement('TerminalForm');
		form.submit();
	}

	function transferTypeChanged(changedElementType)
	{
		var transferTag = $FElement(changedElementType + "TransferType");
		var transferCashbookTag = $FElement(changedElementType + "TransferCashbookId");
		var transferBankAccountTag = $FElement(changedElementType + "TransferBankAccountId");
		var transferType = transferTag.value;
		transferCashbookTag.style.display='none';
		transferBankAccountTag.style.display='none';
		if (transferType == 'B')
		{
			transferBankAccountTag.style.display='inline';
		}
		else if (transferType == 'C')
		{
			transferCashbookTag.style.display='inline';
		}
		else
		{
			transferCashbookTag.value = "";
			transferBankAccountTag.value = "";
		}
		transferTag.value = transferType;
	}
	
	function autoLockChanged()
	{
		var field = $FElement('autoLock');
		var pnlLockingTime = $('lockingTimePanel');
		if (field.checked)
		{
			pnlLockingTime.style.display='inline';
		}
		else
		{
			pnlLockingTime.style.display='none';
		}
	}
	
	function initScreen()
	{
		transferTypeChanged('cashbook');
		transferTypeChanged('card');
		transferTypeChanged('check');
		autoLockChanged();
	}
	
	function copyTerminal()
	{
		var terminalId = $FElement('terminalId').value;
		document.location.href="TerminalAction.do?action=copyTerminal&terminalId=" +terminalId;
	}
	
	function editTerminal()
	{
		var terminalId = $FElement('terminalId').value;
		document.location.href="TerminalAction.do?action=editTerminal&terminalId=" +terminalId;
	}
	
	function cancelTerminalChanges()
	{
		var terminalId = $FElement('terminalId').value;
		
		if (terminalId > 0)
		{
			document.location.href="TerminalAction.do?action=viewTerminal&terminalId=" +terminalId;
		}
		else
		{
			document.location.href="SearchTerminalAction.do?action=initSearchTerminal";
		}
	}
	
	function lockTerminal(lock)
	{
		var action = $FElement('action');
		
		if (lock == true)
		{
			action.value = 'lockTerminal';
		}
		else if (lock == false)
		{
			action.value = 'unlockTerminal';
		}
		else
		{
			return ;
		}
		var form = $FElement('TerminalForm');
		form.submit();
	}
	
	initScreen();
</script>
<%@ include file="/jsp/include/posFooter.jsp" %>