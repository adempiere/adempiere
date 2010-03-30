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
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

<table class="cart">				
<logic:present name="<%=Constants.SHOPPING_GRN_CART_ITEMS%>">
<logic:notEmpty name="<%=Constants.SHOPPING_GRN_CART_ITEMS%>">
<bean:define id="currSymbole" name="<%= Constants.CURRENCY_SYMBOLE %>"/>
<tr>	
	<th align="left">
		<pos:message key="Description"/>
	</th>
	<th align="right">
		<pos:message key="Qty"/>
	</th>
	<th align="right">
		<pos:message key="Price"/>
	</th>
	<th align="right">
		<pos:message key="vat"/>
	</th>
	<th align="right">
		<pos:message key="total"/>
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

<logic:iterate indexId="count" id="element" name="<%=Constants.SHOPPING_GRN_CART_ITEMS%>" type="org.posterita.beans.ItemBean">
<%
	String styleClass = "label";
	if ((count.intValue()%2) != 0)
		styleClass = "contentname";
%>
<tr>	
	<td class="<%=styleClass%>">
		<bean:write name="element" property="description"/>
	</td>
	<td align="right" class="<%=styleClass%>">
		<bean:write name="element" property="qty"/>
	</td>	
	<td align="right" class="<%=styleClass%>">
		<bean:define id="standardPrice" name="element" property="standardPrice"/>
		<fmt:formatNumber value='${standardPrice}' type="currency" currencySymbol=""/>
		<bean:define id="priceTotal" name="element" property="priceTotal"/>
	</td>
	
	<td align="right" class="<%=styleClass%>">
		<bean:define id="taxAmt" name="element" property="taxAmt"/>
		<fmt:formatNumber value='${taxAmt}' type="currency" currencySymbol=""/>
		<bean:define id="taxTotal" name="element" property="taxTotal"/>
	</td>	
	
	<td align="right"  class="<%=styleClass%>">
		<bean:define id="price" name="element" property="price"/>
		<fmt:formatNumber value='${price}' type="currency" currencySymbol=""/>
		<bean:define id="grandTotal" name="element" property="grandTotal"/>
	</td>				
	
	<td align="right" class="<%=styleClass%>">
	<html:form action="/CreateGoodsReturnNoteAction">
		<html:hidden property="action" value="addToShoppingCart"/>
		<html:hidden property="ifAdd" value="true"/> 
		<html:hidden property="isSales" value="false"/>
   		<html:hidden property="productId" value="<%=element.getProductId()+""%>"/> 
   		<html:hidden property="bpartnerId" value=""/>
		<html:image property="btn" src="images/pos/buttons/button_plus.gif" onclick="this.form.submit();" style="width:28px;height:28px;border:0px"/>
	</html:form>
	</td>
	
	<td align="right" class="<%=styleClass%>">
	<html:form action="/CreateGoodsReturnNoteAction">
		<html:hidden property="action" value="addToShoppingCart"/>
		<html:hidden property="ifAdd" value="false"/> 
		<html:hidden property="isSales" value="false"/>
   		<html:hidden property="productId" value="<%=element.getProductId()+""%>"/> 
   		<html:hidden property="bpartnerId" value=""/>
	 	<html:image property="btn" src="images/pos/buttons/button_minus.gif" onclick="this.form.submit();" style="width:28px;height:28px;border:0px"/>
	</html:form>
	</td>
	
		<td class="<%=styleClass%>" align="center">
							<html:link href="<%="DeleteFromShoppingCartAction.do?action=deleteFromPOSRetCart&productId="
							+ element.getProductId()%>">X</html:link>
		</td>
</tr>
</logic:iterate>
	<tr>
		<td  align="left" class="total" colspan="2"><pos:message key="GrandTotal"/></td>					
		<td align="right" nowrap class="total">  		  
			<fmt:formatNumber value='${priceTotal}'type="currency" currencySymbol='${currSymbole}'/>
		</td>
		<td align="right" nowrap class="total">  		  
			<fmt:formatNumber value='${taxTotal}'type="currency" currencySymbol='${currSymbole}'/>
		</td>
		<td align="right" nowrap class="total">  		  
			<fmt:formatNumber value='${grandTotal}'type="currency" currencySymbol='${currSymbole}'/>
		</td>
		<td class="total">&nbsp;</td>
		<td class="total">&nbsp;</td>
		<td class="total">&nbsp;</td>					
	</tr>
</logic:notEmpty>
</logic:present>
</table>		