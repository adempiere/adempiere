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
<html:hidden property="isSales" value="true"/>
<html:hidden property="action" value=""/>
<html:hidden property="ifAdd" value="true"/>
<html:hidden property="orderType" value="<%=Constants.POS_ORDER%>"/>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

<logic:present name="<%= Constants.CURRENT_POS_ORDER_ID %>">
<bean:define id="orderId">
	<bean:write name="<%= Constants.CURRENT_POS_ORDER_ID %>"/>
</bean:define>
<html:hidden property="orderId" value="<%= orderId %>"/>
</logic:present>
<table class="main">			
   	<tr>	
   		<td>   		
   			<table class="main">
   				<%@ include file="/jsp/include/posOrderBarCode.jsp" %>

				<%@ include file="/jsp/include/posOrderSearch.jsp" %>
			</table>
   		</td>
   </tr>		
   	
	<tr>
		<td>
			<div id="shoppingCart">
				<%@ include file="/jsp/pos/creditOrderShoppingCart.jsp" %>
			</div>
		</td>
	</tr> 
	
	<tr>
		<td>
			<table class="main">
				<tr>
					<td valign="top" width="45%">						
						<fieldset>
							<legend><pos:message key="shipment.no" /></legend>
							<div style="height:160px">
							<table border="0" cellpadding="5">
								<tr>
								
									<html:select property="toBeShipped" value="true" onchange="setShipmentRequired(this)" styleClass="text">
										<html:option value="true"><pos:message key="shipment.required" /></html:option>	
										<html:option value="false"><pos:message key="no.shipment" /></html:option>																														
				  				   </html:select>
				  				  <html:hidden property="toBeShipped" />		
								</tr>
							</table>
							</div>
						</fieldset>										
					</td> 
					<td  valign="top" width="45%">
						<%@ include file="/jsp/include/customerInfoPanel.jsp" %>
					</td>
					<td valign="top">
						<table border="0" width="100%" cellspacing="0" style="padding-right: 0px; padding-left: 5px; padding-top: 5px; padding-bottom: 5px">
							<tr>
								<td class="buttoncell" align="right">
									<html:button property="btn" styleClass="checkout bigbutton" onclick="createOrder()" accesskey="c">&nbsp;</html:button>	
								</td>
							</tr>
<script>							
function setPaymentTerm(select)
{
	var paymentTermId = select.options[select.selectedIndex].value;
	var PaymentTerms = document.getElementsByName('paymentTermId');
	for (var i=0;i<PaymentTerms.length;i++)
	{
		PaymentTerms[i].value = paymentTermId;
	}
	
	$focus('barCode');
}

function setShipmentRequired(select)
{
	var toBeShipped = select.options[select.selectedIndex].value;
	var ShipStatus = document.getElementsByName('toBeShipped');
	
	for (var i=0;i<ShipStatus.length;i++)
	{
		ShipStatus[i].value = toBeShipped;
	}
	
	$focus('barCode');
}

var select = document.getElementsByName('bpartnerId')[0];
var paymentTermId = select.options[select.selectedIndex].value;
var PaymentTerms = document.getElementsByName('paymentTermId');
for (var i=0;i<PaymentTerms.length;i++)
{
	PaymentTerms[i].value = paymentTermId;
}
	
$focus('barCode');
</script>
							
							