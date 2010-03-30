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


<!-- draftedGoodsReturnNote.jsp -->
<%@ page import="org.posterita.user.*"%>
<%@ page import="org.posterita.Constants"%>
<%@ page import="org.posterita.struts.stock.StockAction"%>
<%@ page import="org.posterita.struts.pos.POSOrderAction"%>
<%@ page import="org.posterita.struts.pos.POSGoodsAction"%>
<%@ page import="org.posterita.beans.ProductBean"%>
<%@ page import="org.posterita.user.WebUserInfo"%>
<%@ page import="org.posterita.lib.UdiConstants"%>


<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/dcs.tld" prefix="dcs"%>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

<bean:define id="title"><pos:message textOnly="true" key="goods.returned.note"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %> 


	<html:form action="/CreateGoodsReturnNoteAction">
	<html:hidden property="action" value="<%=POSGoodsAction.CREATE_POS_GOODS_RETURN_NOTE%>" />
	<html:hidden property="orderType" value="<%=Constants.GOODS_RETURN_NOTE%>"/>


<%@ include file="/jsp/pos/orderHeader.jsp" %> 
<% String orderlines = Constants.GOODS_RETURN_NOTE_LINES; %>
<%@ include file="/jsp/pos/draftedPOSShoppingCart.jsp" %> 



<table class="view" width="100%">
	<tr>
		<td align="right">
		<html:button property="deleteBtn" accesskey="d" styleClass="delete smallbutton">
			&nbsp;
		</html:button>
		</td>
	</tr>
</html:form>

<html:form action="/CompleteGoodsReturnNoteAction">
<html:hidden property="action" value="<%=POSGoodsAction.COMPLETE_GOODS_RETURN_NOTE%>" />
	<tr>
		<td align="right">
		<logic:present name="<%= Constants.GOODS_RETURN_NOTE_ID  %>">
		<html:button property="edit" styleClass="edit smallbutton" accesskey="d">&nbsp;</html:button>					
		&nbsp;
		</logic:present>
		<html:submit property="submit" accesskey="c" styleClass="complete smallbutton">&nbsp;</html:submit>
		</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>					
	<tr>					  
		<td align="center">
			<span>
				<span class="shortcutkey">
					<label><pos:message key="IsComplete"/>-</label>
					<label class="red">Alt-C</label>
				</span>	
				<span class="shortcutkey">
					<label><pos:message key="delete.selected"/>-</label>
					<label class="red">Alt-D</label>
				</span>
				<span class="shortcutkey">
					<label><pos:message key="edit"/>-</label>
					<label class="red">Alt-E</label>
				</span>	
			</span>				   	 	  	
		</td>		   		
	</tr>	
</html:form>

</table>
<script>
window.onload = function(e)
{
	enableDelete();
};


<logic:present name="<%= Constants.GOODS_RETURN_NOTE_ID  %>">
<bean:define id="posOrderId">
<bean:write name="<%= Constants.GOODS_RETURN_NOTE_ID %>"/>
</bean:define>
$FElement('edit').onclick = function(e)
{
	window.location = "GetShoppingCartForOrder.do?action=getOrderShoppingCart&orderId=<%= posOrderId %>";
};
</logic:present>

$FElement('submit').onclick = function(e)
{
	try
	{		
		disableButtons();
	}
	catch(e)
	{
		toConsole(e);
	}
	
	
};

$FElement('deleteBtn').onclick = function(e)
{
	window.location = "DeleteGoodsReturnNoteOrderAction.do?action=deleteGoodsReturnOrder";
};
</script>     
<%@ include file="/jsp/include/posFooter.jsp"%>
