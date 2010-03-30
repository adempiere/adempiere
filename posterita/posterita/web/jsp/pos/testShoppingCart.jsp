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

	
<logic:present name="<%=Constants.SHOPPING_ORDER_CART_ITEMS%>">
<table class="cart">

<logic:notEmpty name="<%=Constants.SHOPPING_ORDER_CART_ITEMS%>">
	<tr>					
		<th align="left">
			<B>Description</B>
		</th>					
		<th align="right">
			<B>Quantity</B>
		</th>					
		<th align="right">
			<B>Price</B>
		</th>	
		<th align="right">
			<B>VAT</B>
		</th>
		<th align="right">
			<B>Total</B>
		</th>			
		<th>
			&nbsp;
		</th>
		<th>
			&nbsp;
		</th>
		<th>
			&nbsp;
		</th>		
	</tr>
				
	<logic:iterate indexId="count" id="element" name="<%=Constants.SHOPPING_ORDER_CART_ITEMS%>" type="org.posterita.beans.ItemBean">
	<%
	String styleClass = "label";
	if ((count.intValue()%2) != 0)
		styleClass = "contentname";
	%>
	<tr>				
		<td class="<%=styleClass%>">
			<bean:write name="element" property="description"/>			
		</td>
	
		<td class="<%=styleClass%>" align="right">
			<bean:write name="element" property="qty"/>
			<bean:define id="qtyTotal" name="element" property="qtyTotal"/>
		</td>
		
		<td class="<%=styleClass%>" align="right">
			<bean:define id="standardPrice" name="element" property="standardPrice"/>
			<fmt:formatNumber value='${standardPrice}'type="currency" currencySymbol=""/>
			<bean:define id="priceTotal" name="element" property="priceTotal"/>
		</td>
		
		<td class="<%=styleClass%>" align="right">
			<bean:define id="taxAmt" name="element" property="taxAmt"/>
			<fmt:formatNumber value='${taxAmt}'type="currency" currencySymbol=""/>
			<bean:define id="taxTotal" name="element" property="taxTotal"/>
		</td>	
		
		<td class="<%=styleClass%>" align="right">
			<bean:define id="price" name="element" property="price"/>
			<fmt:formatNumber value='${price}'type="currency" currencySymbol=""/>
			<bean:define id="grandTotal" name="element" property="grandTotal"/>
		</td>		
		
		<td class="<%=styleClass%>" align="right">
			<img src="images/pos/buttons/button_plus.gif" class="button" onclick="<%="incrementCart("+element.getProductId()+")"%>">
		</td>
		
		<td class="<%=styleClass%>" align="right">
	  		<img src="images/pos/buttons/button_minus.gif" class="button" onclick="<%="decrementCart("+element.getProductId()+")"%>">
		</td>
		
		<td class="<%=styleClass%>" align="right">
	  		<img src="images/pos/cross.gif" class="button" onclick="<%="deleteItemFromCart("+element.getProductId()+")"%>">
		</td>
		
					
	</tr>
	</logic:iterate>	
	<tr>
		<bean:define id="currSymbole" name="<%= Constants.CURRENCY_SYMBOLE %>"/>
		<td class="total">Grand Total</td>
		<td nowrap class="total" align="right">  		  
			<fmt:formatNumber value='${qtyTotal}'/>
		</td>
		<td nowrap class="total" align="right">  		  
			<fmt:formatNumber value='${priceTotal}'type="currency" currencySymbol='${currSymbole}'/>
		</td>
		<td nowrap class="total" align="right">  		  
			<fmt:formatNumber value='${taxTotal}'type="currency" currencySymbol='${currSymbole}'/>
		</td>
		<td nowrap class="total" align="right">  		  
			<fmt:formatNumber value='${grandTotal}'type="currency" currencySymbol='${currSymbole}'/>
			<input type="hidden" name="grandTotal" value="<c:out value='${grandTotal}'/>">
		</td>
		<td class="total"></td>
		<td class="total">&nbsp;</td>
		<td class="total">&nbsp;</td>
	
	
	 <%--<td class="darkColour">
				<bean:write name="<%=Constants.SHOPPING_ORDER_CART%>" property="totalPrice"/>
	</td>	
	--%></tr>	
</logic:notEmpty>	
</table>
</logic:present>	

		