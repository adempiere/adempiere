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
 * @author Praveen
--%>


<!-draftedPartialPOSOrder.jsp-->
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


<bean:define id="title"><pos:message textOnly="true" key="smenu.prepare.order"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>   
<%@ include file="/jsp/include/errors.jsp" %>  
	  						   
<table class="main">	
<html:form action="/PartialPOSOrderAction">
<html:hidden property="action" value="<%=POSOrderAction.CREATE_POS_ORDER%>"/>  

<logic:present name="<%= Constants.CURRENT_PARTIAL_POS_ORDER_ID %>">
<bean:define id="orderId">
	<bean:write name="<%= Constants.CURRENT_PARTIAL_POS_ORDER_ID %>"/>
</bean:define>
<html:hidden property="orderId" value="<%= orderId %>"/>
</logic:present>

<%@ include file="/jsp/pos/orderHeader.jsp" %> 
 <%--
 	<tr>
		<td>
		<div class="space"></div>
		
		<div id="amtGiven" align="center">
		<table border="0" cellpadding="5" cellspacing="0">			
			<tr>
				<td>
					<label><pos:message key="AmountTendered"/>:</label>
				</td>
				<td>
					<html:text property="amountGiven" styleClass="text" onchange="loadFieldValues(this)" accesskey="g"/>
				</td>			
				<td>
					<LABEL><pos:message key="AmountRefunded"/>:</LABEL>
				</td>
				<td>
					<html:text property="amountRefunded" styleClass="text" readonly="true" />	
					<html:hidden property ="trxType"/>
				</td>
			</tr>						
		</table>
		</div>
		<div class="space"></div>
		</td>
	</tr>
--%>
	<tr>
		<td>
		
		<% String orderlines = Constants.PARTIAL_POS_ORDER_LINES; %>
		<%@ include file="/jsp/pos/draftedPOSShoppingCart.jsp" %> 
		
		<input type="hidden" name="grandTotal" value=<fmt:formatNumber value='${grandTotal}' pattern="########.##"/>>		
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
			<html:button property="edit" styleClass="edit smallbutton" accesskey="e">&nbsp;</html:button>
			&nbsp;													
			<html:button property="btn" styleClass="complete smallbutton" onclick="completeOrder(this)" accesskey="c">&nbsp;</html:button>	
		</div>
		<div class="space"></div>
		<div align="center">
			<table border="0" cellpadding="10">
			<tr>
				<td>
					<label><pos:message key="delete.selected"/> -</label><label class="red">Alt-D</label>
				</td>
				<td>
					<label><pos:message key="edit"/> -</label><label class="red">Alt-E</label>
				</td>
				<td>
					<label><pos:message key="AmountTendered"/> -</label><label class="red">Alt-G</label>
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


<script>
//registering shortcut keys
//keyMap['ALT-D'] = "deleteSelected()";
//keyMap['ALT-C'] = "completeOrder()";

//showing the amt given textfield depending on the trxType

var trxType = '<bean:write name="<%=Constants.WEB_DOCUMENT_HEADER_BEAN%>" property="paymentType"/>';

var mixedCashAmount = 0;
<logic:present name="<%=Constants.PAYMENT_BY_CASH%>">
	mixedCashAmount = '<bean:write name="<%=Constants.PAYMENT_BY_CASH%>"/>';
</logic:present>

//function declarations
$FElement('edit').onclick = function(e){window.location = "CreatePartialPOSOrder.do";};

function deleteSelected()
{	
	try
	{
		var method = "deleteOrder";
		var myForm = document.forms[0];
		var actionElement = document.getElementsByName('action')[0];
		
		actionElement.value = method;
		myForm.submit();
	}
	catch(e)
	{
		toConsole(e);
	}
}

function completeOrder(btn)
{
	btn.disabled = true;
	btn.style.cursor = "wait";	
	
	try
	{
		var method = "completePartialPOSOrder";
		var myForm = document.forms[0];
		var actionElement = document.getElementsByName('action')[0];
		
		actionElement.value = method;
		myForm.submit();
		
		disableButtons();
	}
	catch(e)
	{
		toConsole(e);
	}
	//setAction(document.forms[0],'CompletePOSOrderAction.do','completePOSOrder');
}


window.onload = function(){enableDelete();};

$focus('amountGiven');
</script>
       		
<%@ include file="/jsp/include/posFooter.jsp" %>	
