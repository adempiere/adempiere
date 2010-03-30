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
 * @author Vishee
--%>


<!-- draftedposOrderWA.jsp -->
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.Constants" %>

<%@ page import="org.posterita.struts.pos.POSOrderAction" %>
<%@ page import="org.posterita.beans.ProductBean" %>
<%@ page import="org.posterita.user.WebUserInfo" %>
<%@ page import="org.posterita.lib.UdiConstants" %>


<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/dcs.tld" prefix="dcs" %>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

<bean:define id="title"><pos:message textOnly="true" key="smenu.cash.sales"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>   
<%@ include file="/jsp/include/errors.jsp" %>  
	  						   
<div class="space"></div>
<html:form action="/CreatePOSOrderActionWA">
<bean:define id="orderType" value="posOrderWithoutAdvanced"/>	
<!-- draftedPOSOrderIncluded.jsp-->
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>
<html:hidden property="action" value="<%=POSOrderAction.CREATE_POS_ORDER%>"/> 
<html:hidden property="orderType" value="<%=Constants.POS_ORDER%>"/>

<logic:present name="<%= Constants.CURRENT_PARTIAL_POS_ORDER_ID %>">
<bean:define id="orderId">
	<bean:write name="<%= Constants.CURRENT_PARTIAL_POS_ORDER_ID %>"/>
</bean:define>
<html:hidden property="orderId" value="<%= orderId %>"/>
</logic:present>

<%@ include file="/jsp/pos/orderHeader.jsp" %> 
 
 	<tr>
 		<td>&nbsp;</td>
 	</tr>
 	
 	<tr>
		<td>
		<div id="amtGiven">
		<table border="0" cellpadding="5" cellspacing="0" align="center">			
			<tr>
				<td>
					<label><b><pos:message key="AmountTendered"/>:</b></label>
				</td>
				<td>
					<html:text property="amountGiven" styleClass="text" onkeyup="updateAmountToBeReturned(this)" accesskey="g"/>
				</td>			
				<td>
					<LABEL><b><pos:message key="AmountRefunded"/>:</b></LABEL>
				</td>
				<td>
					<html:text property="amountRefunded" styleClass="text" readonly="true" />	
					<html:hidden property ="trxType"/>
				</td>
			</tr>						
		</table>
		</div>
		</td>
	</tr>
	
	<tr>
		<td>
		<% String orderlines = Constants.POS_ORDER_LINES; %>
		<%@ include file="/jsp/pos/draftedPOSShoppingCart.jsp" %> 

		<input type="hidden" name="grandTotal" value=<fmt:formatNumber value='${grandTotal}' groupingUsed ='No' />>		
		</td>
	</tr>
	<tr>
		<td>
			<div class="space"></div>
			<div align="right">				
				<html:button property="deleteBtn" styleClass="delete smallbutton" onclick="deleteSelected()" accesskey="d">&nbsp;</html:button>
			</div>
			<div class="space"></div>
			<div align="right">				   
					   <logic:present name="orderType">
					   	<logic:present name="<%= Constants.CURRENT_POS_ORDER_ID  %>">
							<bean:define id="posOrderId" name="<%= Constants.CURRENT_POS_ORDER_ID  %>"/>								
							<html:link href="GetShoppingCartForOrder.do?action=getOrderShoppingCart&orderId=<%= posOrderId %>&orderType=<%= orderType %>">
						   		<html:button property="btn" styleClass="edit smallbutton" accesskey="c">&nbsp;</html:button>				
						   	</html:link>
					   </logic:present>	
					   </logic:present>
					   
					   <logic:notPresent name="orderType">
					   <logic:present name="<%= Constants.CURRENT_POS_ORDER_ID  %>">
							<bean:define id="posOrderId" name="<%= Constants.CURRENT_POS_ORDER_ID  %>"/>								
							<html:link href="GetShoppingCartForOrder.do?action=getOrderShoppingCart&orderId=<%= posOrderId %>">
						   		<html:button property="btn" styleClass="edit smallbutton" accesskey="c">&nbsp;</html:button>				
						   	</html:link>
					   </logic:present>	
					   </logic:notPresent>
				
											   							
				<html:button property="btn" styleClass="complete smallbutton" onclick="completeOrder(this)" accesskey="c">&nbsp;</html:button>											   				   	 	  	
			</div>
			<div align="center">
				<table border="0" cellpadding="10">
					<tr>
						<td>
							<label><pos:message key="delete"/> -</label><label class="red">Alt-D</label>
						</td>
						<td>
							<label><pos:message key="edit"/> -</label><label class="red">Alt-E</label>
						</td>
						<td>
							<label><pos:message key="AmountGiven"/> -</label><label class="red">Alt-G</label>
						</td>
						<td>
							<label><pos:message key="continue"/> -</label><label class="red">Alt-C</label>
						</td>									
					</tr>
				</table> 		   	 	  	
			</div>
		</td>
	</tr>
  
</html:form>

<%@ include file="/js/draftedPOSOrder.js" %>

<script>

function completeOrder(btn)
{
	var amountGivenValid = true;
	
	if(trxType == "Cash") 
	{
		setDefaultAmount(btn, btn.form.grandTotal.value);
		amountGivenValid = validateAmountGiven(btn, btn.form.grandTotal.value);
	}
	else if(trxType == "Mixed")
	{
		setDefaultAmount(btn, mixedCashAmount);
		amountGivenValid = validateAmountGiven(btn, mixedCashAmount);
	}
		
	if(amountGivenValid)
	{
		setAction(document.forms[0],'CreatePOSOrderActionWA.do','completePOSOrder');
		disableButtons();
	}
	
	$focus('amountGiven');
}

function recall()
{
	window.location = "CreatePOSOrderWithoutAdvanced.do";
}

function deleteSelected()
{
	if(confirmDelete(''))
	{
		window.location = "DeletePOSOrderActionWA.do?action=deletePOSOrder";
	}
}


</script>
       		
<%@ include file="/jsp/include/posFooter.jsp" %>	

