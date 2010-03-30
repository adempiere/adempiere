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


<!--draftedCustomerReturnOrder.jsp-->
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.Constants" %>

<%@ page import="org.posterita.struts.pos.POSOrderAction" %>
<%@ page import="org.posterita.struts.pos.POSGoodsAction" %>
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

<bean:define id="title"><bean:write name="<%= Constants.ORDER_TITLE %>"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %>  
	  						   

<html:form action="/CreateCustomerReturnOrderAction">
<html:hidden property="action" value="<%=POSGoodsAction.CREATE_CUSTOMER_RETURN_ORDER%>"/>
<html:hidden property="orderType" value="<%=Constants.CUSTOMER_RETURN_ORDER%>"/>
<%@ include file="/jsp/pos/orderHeader.jsp" %> 

<% String orderlines = Constants.CUSTOMER_RETURN_ORDER_LINES; %>
<%@ include file="/jsp/pos/draftedPOSShoppingCart.jsp" %> 

<div class="space"></div>
<div align="right"><html:button property="deleteBtn" styleClass="delete smallbutton" accesskey="d">&nbsp;</html:button>
</div>									
</html:form>
<div class="space"></div>
<div align="right">
<html:form action="/CompleteCustomerReturnOrderAction">
	<html:hidden property="action" value="<%=POSGoodsAction.COMPLETE_CUSTOMER_RETURN_ORDER%>"/>
	<logic:present name="<%= Constants.CUSTOMER_RETURN_ORDER_ID  %>">
		<html:button property="edit" accesskey="c" styleClass="edit smallbutton">&nbsp;</html:button>
	</logic:present>	
	&nbsp;	
	<html:button property="complete" accesskey="c" styleClass="complete smallbutton">&nbsp;</html:button>
</html:form>
</div>
<div align="center">
	<table border="0" cellpadding="10" align="center">
		<tr align="center">
			<td>
				<label><pos:message key="IsComplete"/> -</label><label class="red">Alt-C</label> 
			</td>
			<td>
				<label><pos:message key="delete.selected"/> -</label><label class="red">Alt-D</label>
			</td>							
		</tr>
	</table>
</div>

<script>
$FElement('edit').onclick = function(e)
{ 
	<logic:present name="<%= Constants.CUSTOMER_RETURN_ORDER_ID  %>">
	<bean:define id="posOrderId">
		<bean:write name="<%= Constants.CUSTOMER_RETURN_ORDER_ID  %>"/>
	</bean:define>
	window.location = 'GetShoppingCartForOrder.do?action=getCustomerReturnOrderShoppingCart2&orderId=<%= posOrderId %>';				   		
	</logic:present>	
};

$FElement('complete').onclick = function(e)
{
	setAction(this.form,'CompleteCustomerReturnOrderAction.do','completeCustomerReturnOrder');
};

$FElement('deleteBtn').onclick = function(e)
{ 
	window.location = "DeleteCustomerReturnOrderAction.do?action=deleteCustomerReturnOrder"; 
};

</script>      		
<%@ include file="/jsp/include/posFooter.jsp" %>	
