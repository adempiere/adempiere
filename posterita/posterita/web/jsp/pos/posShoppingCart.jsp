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



<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.Constants" %>


<%@ page import="org.posterita.struts.pos.POSOrderAction" %>
<%@ page import="org.posterita.beans.ProductBean" %>
<%@ page import="org.posterita.user.WebUserInfo" %>
<%@ page import="org.posterita.lib.UdiConstants" %>
<%@ page import="org.posterita.struts.pos.POSGoodsAction" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

<bean:define id="currSymbole" value="$"/>
<table width="100%" id="cart" cellspacing="0" cellpadding="0" border="1">
<tr>
  <th><pos:message key="Description"/></th>
  <th><pos:message key="Qty"/></th>
  <th><pos:message key="Uom"/></th>
  <th><pos:message key="Price"/></th>
  <th><pos:message key="vat"/></th>
  <th><pos:message key="Discount"/></th>
  <th><pos:message key="total"/></th>
</tr>
<tbody style="width:400px;" id="cartBody">
<logic:present name="<%= Constants.SHOPPING_ORDER_CART_ITEMS %>">
<logic:iterate indexId="count" id="element" name="<%= Constants.SHOPPING_ORDER_CART_ITEMS %>" type="org.posterita.beans.ItemBean">
<%
String styleClass = "label";
if ((count.intValue()%2) != 0)
	styleClass = "contentname";
%>	
<tr productId="<%=element.getProductId()%>" qty="<%=element.getQty()%>">
  <td>
  	<bean:write name="element" property="description"/>
  </td>
  <td class="<%=styleClass%>" align="right">
	<bean:write name="element" property="qty"/>
	<bean:define id="qtyTotal" name="element" property="qtyTotal"/>
  </td>
  <td class="<%=styleClass%>" align="right">
	<bean:write name="element" property="uom"/>
  </td>
  <td class="<%=styleClass%>" align="right">
	<bean:write name="element" property="unitPrice" />
  </td>
  <td class="<%=styleClass%>" align="right">
	<bean:write name="element" property="taxAmt" />
	<bean:define id="taxTotal" name="element" property="taxTotal"/>
  </td>
  <td class="<%=styleClass%>" align="right">
	<bean:write name="element" property="discountPercent"/>
  </td>
  <td class="<%=styleClass%>" align="right">
	<bean:write name="element" property="price" />
	<bean:define id="grandTotal" name="element" property="grandTotal"/>
  </td>
</tr>
</logic:iterate>
</logic:present>
<tbody>
<tfoot>
	<td>&nbsp;</td>
	<td align="right"><fmt:formatNumber value='${qtyTotal}'/></td>
	<td colspan="4">&nbsp;</td>
	<td align="right">
		<div id="cartTotal">
		<fmt:formatNumber value='${grandTotal}'type="currency" currencySymbol='${currSymbole}'/>
		</div>
	</td>
</tfoot>
</table>		