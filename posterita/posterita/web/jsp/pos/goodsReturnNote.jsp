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


<%@ page import="org.posterita.user.*"%>
<%@ page import="org.posterita.Constants"%>
<%@ page import="org.posterita.struts.stock.StockAction"%>

<%@ page import="org.posterita.struts.pos.POSOrderAction"%>
<%@ page import="org.posterita.beans.ProductBean"%>
<%@ page import="org.posterita.user.WebUserInfo"%>
<%@ page import="org.posterita.lib.UdiConstants"%>
<%@ page import="org.posterita.struts.pos.POSGoodsAction"%>
<%@ page import="org.posterita.order.UDIOrderTypes" %>


<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/dcs.tld" prefix="dcs"%>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

<bean:define id="title"><pos:message textOnly="true" key="goods.returned.note"/></bean:define>
<table width="100%">
	<td align="right">
		<html:link href="<%="CreateNewOrderAction.do?action=removeSessionForNewOrder&orderType=" + UDIOrderTypes.POS_GOODS_RETURN_NOTE.getOrderType() %>">
			Create New
		</html:link>			
	</td>
</table>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %> 

<table class="main">
	<tr>			
   		<td>
   		<html:form action="/CreateGoodsReturnNoteAction">
		<html:hidden property="action" value="" />	
		<html:hidden property="orderType" value="<%=Constants.GOODS_RETURN_NOTE%>"/>
		
		<logic:present name="<%= Constants.GOODS_RETURN_NOTE_ID %>">
		<bean:define id="orderId">
			<bean:write name="<%= Constants.GOODS_RETURN_NOTE_ID %>"/>
		</bean:define>
		<html:hidden property="orderId" value="<%= orderId %>"/>
		</logic:present>
		   		
		<table class="main">
			<tr>
				<td>
					<label><pos:message textOnly="true" key="vendor.ref"/>:</label>	
				</td>
				<td colspan="1" align="left">
					
					<html:text property="description" styleClass="text" accesskey="r"/>					
				</td>	
				<td colspan="3" align="right">
					<label><pos:message key="Vendor_ID"/>:</label>				
					<html:select property="bpartnerId" onchange="setBPartner(this)" styleClass="text">
						<html:option value=""><pos:message key="select"/></html:option>
						<html:options collection="<%= Constants.BP_LIST %>" property="bpartnerId" labelProperty="partnerName"/>																																	
				   </html:select>
				</td>				
			</tr>
			<tr>
		   		<td>
		   			<label><pos:message key="barcode"/>:</label>
		   		</td>		
		   		<td>
		   			<html:text property="barCode" accesskey="b" styleClass="text"/>
		   			<html:hidden property="productId" value=""/>
		   		</td>
		   		
		   		<td>
		   			<label><pos:message key="Qty"/>:</label>
		   		</td>		
		   		<td>
		   			<html:text property="quantity" accesskey="q" styleClass="text"/>
		   		</td> 
		   		
		   		<td align="right">
		   			<html:button accesskey="a" styleClass="addtocart bigbutton" onclick="addToCart()" property="btn">&nbsp;</html:button>
		   		</td>		   			   				
			</tr>	
			<tr>
   				<td>
   					<label><pos:message key="search.product"/>:</label>
   				</td>
   				<td colspan="3">
					<%@ include file="/jsp/include/searchProductPanel.jsp" %>
				</td>				
   			</tr>					
   		</table>
			
		</td>
	</tr>
	
	<tr>
		<td>
			<div id="shoppingCart">
				<%@ include file="/jsp/pos/posShoppingCart.jsp" %>
			</div>
		</td>
	</tr> 

	
	
	<tr><td>&nbsp;</td></tr>
	<tr>
		<td colspan="2" align="right">
			<html:button property="btn" accesskey="c" styleClass="continue smallbutton" onclick="createGoodReturnNote()">&nbsp;</html:button>
		</td>
	</tr>
	</html:form>
	<tr>					  
		<td align="center">
		<span>
			<span class="shortcutkey">
				<label><pos:message key="barcode"/>-</label>
				<label class="red">Alt-B</label>
			</span>	
			<span class="shortcutkey">
				<label><pos:message key="qty"/>-</label>
				<label class="red">Alt-Q</label>
			</span>	
			<span class="shortcutkey">
				<label><pos:message key="add.to.cart"/>-</label>
				<label class="red">Alt-A</label>
			</span>	
			<span class="shortcutkey">
				<label><pos:message key="Vendor_ID"/>-</label>
				<label class="red">Alt-S</label>
			</span>	
			<span class="shortcutkey">
				<label><pos:message key="search.product"/>-</label>
				<label class="red">Alt-P</label>
			</span>	
			<span class="shortcutkey">
				<label><pos:message key="continue"/>-</label>
				<label class="red">Alt-C</label>
			</span>	
		</span>		
		</td>		   		
	</tr>
</table>

<script>
document.getElementsByName('barCode')[0].value="";
document.getElementsByName('quantity')[0].value="1";
	
function addToCart()
{
	var method = "addToShoppingCart";
	var myForm = document.forms[0];
	var actionElement = document.getElementsByName('action')[0];
	
	actionElement.value = method;
	myForm.submit();
}

function createGoodReturnNote()
{
	var method = "createPOSGoodsReturnNote";
	var myForm = document.forms[0];
	var actionElement = document.getElementsByName('action')[0];
	
	actionElement.value = method;
	myForm.submit();
}

function setBPartner(select)
{
	var bpartnerId = select.options[select.selectedIndex].value;
	var bpartners = document.getElementsByName('bpartnerId');
	for (var i=0;i<bpartners.length;i++)
	{
		bpartners[i].value = bpartnerId;
	}
	
	$focus('barCode');
}

var select = document.getElementsByName('bpartnerId')[0];
var bpartnerId = select.options[select.selectedIndex].value;
var bpartners = document.getElementsByName('bpartnerId');
for (var i=0;i<bpartners.length;i++)
{
	bpartners[i].value = bpartnerId;
}

$focus('barCode');
document.forms[0].barCode.onkeyup = function(e){
	
		try
		{
			var event = e||window.event;
		
			if(event.keyCode == 13)
			{
				addToCart();
			}
		}
		catch (e)
		{
			toConsole(e);
		}
	};
	
	//incrementing and decrementing the shopping cart items using ajax
	var orderType = $FElement('orderType').value;	
	function incrementCart(productId)
	{
		try
		{
			var url = 'CreateGoodsReceiveAction.do';
			var pars = 'action=incrementQty&productId='+productId+ '&ifAdd=true'+ '&isSales=false'+ '&orderType=' + '<%=Constants.GOODS_RETURN_NOTE%>';
			
			var myAjax = new Ajax.Request( url, 
			{ 
				method: 'get', 
				parameters: pars, 
				onSuccess: refreshShoppingCart, 
				onFailure: reportError
			});	
			
			
			
			//myForm.submit();
		}
		catch(e)
		{
			toConsole(e);
		}		
	}
	
	
	
	function refreshShoppingCart(request)
	{
		$('shoppingCart').innerHTML = request.responseText;
	}
	
	function decrementCart(productId)
	{
		try
		{
			var url = 'CreateGoodsReceiveAction.do';
			var pars = 'action=decrementQty&productId='+productId+ '&ifAdd=false'+ '&isSales=false'+ '&orderType=' + '<%=Constants.GOODS_RETURN_NOTE%>';
			
			var myAjax = new Ajax.Request( url, 
			{ 
				method: 'get', 
				parameters: pars, 
				onSuccess: refreshShoppingCart, 
				onFailure: reportError
			});	
			//myForm.submit();
		}
		catch(e)
		{
			toConsole(e);
		}		
	}
	
	function deleteItemFromCart(productId)
	{
		try
		{
			var url = 'DeleteFromShoppingCartAction.do';
			var pars = 'action=deleteFromPOSGRNCart&productId='+productId+ '&orderType=' + '<%=Constants.GOODS_RETURN_NOTE%>';
			
			var myAjax = new Ajax.Request( url, 
			{ 
				method: 'get', 
				parameters: pars, 
				onSuccess: refreshShoppingCart, 
				onFailure: reportError
			});
		}
		catch(e)
		{
			toConsole(e);
		}
	}
	
	function reportError(request)
	{
		alert('Some error occured while communicating with the server. Please try again.');		
	}
	
	function scrollDownCart()
	{
		//$('items').scrollTop = $('items').scrollHeight;
	}
	scrollDownCart();	
	
	focusBarcode();
</script>
<%@ include file="/jsp/include/posFooter.jsp"%>
