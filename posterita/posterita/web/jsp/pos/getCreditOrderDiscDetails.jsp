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


<!--getCreditOrderDiscDetails.jsp-->
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.Constants" %>

<%@ page import="org.posterita.struts.pos.POSOrderAction" %>
<%@ page import="org.posterita.beans.ProductBean" %>
<%@ page import="org.posterita.user.WebUserInfo" %>
<%@ page import="org.posterita.lib.UdiConstants" %>
<%@ page import="org.posterita.form.OrderLineForm" %>
<%@ page import="org.compiere.model.MOrder" %>
<%@ page import="java.math.BigDecimal" %>


<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/dcs.tld" prefix="dcs" %>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

<bean:define id="title"><pos:message textOnly="true" key="credit.order.discount"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>  
<%@ include file="/jsp/include/errors.jsp" %> 
	  						   
<html:form action="/CreateCreditOrderAction">
<html:hidden property="action" value=""/>
	<table class="content">
				
<logic:present name="<%=Constants.CREDIT_ORDER_SHOPPING_CART_ITEMS%>">
	<logic:notEmpty name="<%=Constants.CREDIT_ORDER_SHOPPING_CART_ITEMS%>">
	<table class="cart">
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
			<pos:message key="total.price"/>
		</th>
		
		<th align="right">
			<pos:message key="Discount"/>
		</th>
	
	    <th align="right">
			<pos:message key="actual.price"/>
		</th>
	</tr>
	
	<logic:iterate indexId="count" id="element" name="<%=Constants.CREDIT_ORDER_SHOPPING_CART_ITEMS%>" type="org.posterita.beans.ItemBean">
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
			<html:hidden property="price" value="<%= element.getPrice() + ""%>"/>					
			<bean:define id="grandTotal" name="element" property="grandTotal"/>
		</td>
		
		<%
			String defaultDiscount = "0";
			String defaultPrice = element.getPrice().toString();
			
			OrderLineForm form = (OrderLineForm)request.getAttribute("OrderLineForm");
			
			if(form!=null)
			{
				String[] discountPercentage = form.getDiscountPercent();
				String[] actualPrice = form.getActualPrice();		
				
				if(discountPercentage!= null)
				if(discountPercentage.length!=0) 
					defaultDiscount = discountPercentage[count.intValue()];
					
				if(actualPrice!=null)
				if(actualPrice.length!=0)
					defaultPrice = actualPrice[count.intValue()];
			}
		%>	
	
	   	<td class="<%=styleClass%>" align="right">
	    	<html:text property="discountPercent" styleClass="text medium" value="<%=defaultDiscount%>"/>				
		</td>	
		
		<td class="<%=styleClass%>" align="right">
			<html:text property="actualPrice" styleClass="text medium" value="<%=defaultPrice%>"/>							
		</td>				
	</tr>
	
	</logic:iterate>
	 <tr>
	 	<bean:define id="currSymbole" name="<%= Constants.CURRENCY_SYMBOLE %>"/>
		<td class="total" colspan="1"><pos:message key="GrandTotal"/></td>
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
		</td>
		<td class="total">&nbsp;</td>
		<td class="total" align="right">
			<html:hidden property="totalActualPrice"/>
			<div id="actualPriceTotal">
				<fmt:formatNumber value='${grandTotal}'type="currency" currencySymbol='${currSymbole}'/>
			</div>
		</td>
	</tr>
	</table>
	 
	</logic:notEmpty>
</logic:present>	
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr> 			
		<td align="right">			
			<html:button property="edit" styleClass="edit smallbutton" accesskey="e">
				&nbsp;
			</html:button>			
			<html:button property="btn" styleClass="continue smallbutton" onclick="createOrder()" accesskey="c">
				&nbsp;
			</html:button>		   	 	  	
		</td>			
    </tr>
		
</html:form>

<script>
<%@ include file="/js/posPaymentDetails.js" %>

$FElement('edit').onclick = function(){
	window.location ='InitCreateCreditOrderAction.do?action=initCreateCreditOrder';
};

function createOrder()
{
	setAction(document.forms[0],'CreateCreditOrderAction.do','createCreditOrder');
}
</script>
      		       		
<%@ include file="/jsp/include/posFooter.jsp" %>	
	
