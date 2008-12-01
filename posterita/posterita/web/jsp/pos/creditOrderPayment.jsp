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

<!--creditOrderPayment.jsp-->
<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.beans.*" %>
<%@ page import="org.posterita.struts.pos.CreditOrderAction" %>
<%@ page import="org.compiere.process.DocumentEngine" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.posterita.core.UDIPair" %>
<%@ page import="org.compiere.model.MInvoice" %>
<%@ page import="org.compiere.model.MPayment" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

<logic:notPresent name="<%=WebUserInfo.NAME%>" scope="session">
	<jsp:forward page="/LoginHome.do"/>
</logic:notPresent>

<bean:define id="title"><pos:message textOnly="true" key="CreatePayment"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %> 

<html:form action="/CreatePaymentAction" focus="paymentAmt">
	<html:hidden property="action" value="<%=CreditOrderAction.CREATE_PAYMENT%>"/> 
	<table class="main">
		<logic:present name="<%=Constants.CREDIT_INVOICE_INFO%>">
			<tr>
				<td>
					<table border="0" >	
					<tr>
						<td>
							<label><pos:message key="C_Invoice_ID"/>:</label>
						</td>
						<td>						
								<bean:write name="<%= Constants.CREDIT_INVOICE_INFO %>" property="invoiceNo"/>
								<html:hidden name="<%= Constants.CREDIT_INVOICE_INFO %>" property="invoiceNo"/>
								<html:hidden name="<%= Constants.CREDIT_INVOICE_INFO %>" property="invoiceId"/>
						</td>
						<td colspan="2">&nbsp;</td>	
						
					</tr>
					
					<tr>
						<td>
							<label><pos:message key="InvoicedAmt"/>:</label>
						</td>
						<td>						
								<bean:write name="<%= Constants.CREDIT_INVOICE_INFO %>" property="invoiceGrandTotal"/>
						</td>				
						<td colspan="2">&nbsp;</td>	
					</tr>
					<tr>
						<td>
							<label><pos:message key="OpenAmt"/>:</label>
						</td>
						<td>
								<bean:write name="<%= Constants.CREDIT_INVOICE_INFO %>" property="openAmt"/>
						</td>	
						<td colspan="2">&nbsp;</td>	
					</tr>
					
					<tr>
						<td>
							<label><pos:message key="PaidAmt"/>:</label>	
						</td>
						<td>
								<bean:write name="<%= Constants.CREDIT_INVOICE_INFO %>" property="paidAmt"/>
						</td>	
						<td colspan="2">&nbsp;</td>	
					</tr>
					<tr>
						<td>
							<label><pos:message key="PayAmt"/>:</label>		
						</td>
						<td>
									<html:text property="paymentAmt" styleClass="text"/>									
						</td>
						
						<td>
							<label><pos:message key="OverUnderAmt"/>:</label>		
						</td>
						<td>
							<html:text property="overUnderPayment" readonly="true" styleClass="text"/>
						</td>
					</tr>
					<tr>
						<td>
							<label><pos:message key="DiscountAmt"/>:</label>		
						</td>
						<td>
									<html:text property="discountAmt" styleClass="text"/>
									
						</td>
						
						<td>
							<label><pos:message key="WriteOffAmt"/>:</label>		
						</td>
						<td>
									<html:text property="writeOffAmt" styleClass="text"/>
						</td>
					</tr>
					<tr>
							
						<td colspan="4">							
								
								<fieldset>
										<legend><pos:message key="TenderType"/></legend>
										<div style="height:150px">
										<table border="0" cellpadding="5">
											<tr>
												<td>
													<html:button property="btn" styleClass="cash smallbutton" onclick="return setTrxType('Cash');" accesskey="s">&nbsp;</html:button>
												</td>
											  
												<td>
													<html:button property="btn" styleClass="card smallbutton" onclick="return setTrxType('Card');" accesskey="r">&nbsp;</html:button>
											   	</td>
												
												<td>
													<html:button property="btn" styleClass="cheque smallbutton" onclick="return setTrxType('Cheque');" accesskey="k">&nbsp;</html:button>
											   	</td>								   		 	
											</tr>						
											<tr>
												<td colspan="3">
												<table>
													<tbody>
													<tr>
														<td><label><pos:message key="TenderType"/>:</label></td>
														<td><html:text property="trxType" readonly="true" value="Cash"  styleClass="text"/></td>
													</tr>
													</tbody>
													
													<tbody id="cardPanel">
													<tr>
														<td><label><pos:message key="CreditCardNumber"/>:</label></td>
														<td><html:text property="creditCardNumber" styleClass="text"/></td>
													</tr>
													</tbody>
													
													<tbody id="chequePanel">
													<tr>
														<td><label><pos:message key="CheckNo"/>:</label></td>
														<td><html:text property="chequeNo" styleClass="text"/></td>
													</tr>
													</tbody>
												</table>												
												</td>
											</tr>
											<%--
											<tr>
												<td colspan="3">										
													<!--<html:hidden property="trxType"/>-->
													<html:select property="trxType"  styleClass="text">
														    <html:option value="<%=MInvoice.PAYMENTRULE_Cash%>"> Cash</html:option>
															<html:option value="<%=MPayment.TENDERTYPE_CreditCard %>">Card</html:option>
															<html:option value="<%=MPayment.TENDERTYPE_Check%>">Cheque</html:option>
															
														</html:select>
													
													<div id="cashNoTxt">
														<label>Payment By Cash</label>
													</div>
													<div id="cardNoTxt">
														<label>Card No:</label>
														<html:text property="creditCardNumber" styleClass="text"/>
													</div>
													<div id="chequeNoTxt">
														<label>Cheque No:</label>
														<html:text property="chequeNo" styleClass="text"/>
													</div>										
												</td>
											</tr>
											--%>	
										</table>
										</div>
									</fieldset>							
							</td>								
						</tr>						
					</table>
				</td>
			</tr>
		
		</logic:present>
		<tr>
			<td colspan="4">
					<html:submit styleClass="submit smallbutton">
        				&nbsp;
       				 </html:submit>
			</td>	
		</tr>
				
	</table>
</html:form>
<script>
var invoiceAmt = <bean:write name="<%= Constants.CREDIT_INVOICE_INFO %>" property="openAmt"/>;
var paymentAmt = document.getElementsByName('paymentAmt')[0];
var discountAmt = document.getElementsByName('discountAmt')[0];
var writeOffAmt = document.getElementsByName('writeOffAmt')[0];
var overUnderPayment = document.getElementsByName('overUnderPayment')[0];
var trxType = document.getElementsByName('trxType')[0];

Element.hide('cardPanel');
Element.hide('chequePanel');


paymentAmt.onkeyup = function(e){
	var amtDue = new Number(invoiceAmt - this.value).toFixed(2) - new Number(discountAmt.value).toFixed(2);	
	overUnderPayment.value = amtDue;	
};

discountAmt.onkeyup = function(e){
	var amtDue = new Number(invoiceAmt - paymentAmt.value - writeOffAmt.value).toFixed(2);
	amtDue = new Number(amtDue - this.value).toFixed(2);
	
	overUnderPayment.value = amtDue;
};

writeOffAmt.onkeyup = function(e){
	var amtDue = new Number(invoiceAmt - paymentAmt.value - discountAmt.value).toFixed(2);
	amtDue = new Number(amtDue - this.value).toFixed(2);
	
	overUnderPayment.value = amtDue;
};

function setTrxType(trx){
	trxType.value = trx;
	
	if(trx == 'Cash'){
		Element.hide('cardPanel');
		Element.hide('chequePanel');		
	}
	else if(trx == 'Card'){
		Element.show('cardPanel');
		Element.hide('chequePanel');
	}
	else if(trx == 'Cheque'){
		Element.hide('cardPanel');
		Element.show('chequePanel');
	}
	else
	{
		alert('Invalid trx type: ' + trx);
	}
	
	return false;
	
	
}
</script>
<%@ include file="/jsp/include/posFooter.jsp" %>
