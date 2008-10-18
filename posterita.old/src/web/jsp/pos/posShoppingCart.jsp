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
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

<div id="divProdDetails" class="popup"> </div>
<table class="cart" border="0">
				
<logic:present name="<%= items %>">
<logic:notEmpty name="<%= items %>">
<bean:define id="currSymbole" name="<%= Constants.CURRENCY_SYMBOLE %>"/>
	<tr>					
		<th align="left" width="40%">
			<B><pos:element columnName="Description"/></B>
		</th>	
		
		<th align="right" width="50%">
			<B><pos:element columnName="PriceActual"/></B>
		</th>
		
		<th align="right" width="50%">
			<B><pos:element columnName="Discount"/></B>
		</th>	
						
		<th align="right">
			<B><pos:element columnName="Qty"/></B>
		</th>					
		<th align="right">
			<B><pos:element columnName="Price"/></B>
		</th>	
		<th align="right">
			<B><pos:element columnName="vat"/></B>
		</th>
		<th align="right">
			<B><pos:element columnName="total"/></B>
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
		
				<logic:iterate indexId="count" id="element" name="<%= items %>" type="org.posterita.beans.ItemBean">
				<%
				String styleClass = "label";
				if ((count.intValue()%2) != 0)
					styleClass = "contentname";
				%>	
				 
				<tr>
					
					<td class="<%=styleClass%>" onclick="javascript:displayDetails('<%= element.getProductId()%>',event)">
						<div class="popupDiv">
							<bean:write name="element" property="description"/>
						</div>
					</td>
				
					<td class="<%=styleClass%>" align="right">
						<bean:define id="unitPrice" name="element" property="unitPrice"/>
						<fmt:formatNumber value='${unitPrice}' type="currency" currencySymbol=""/>
					</td>
					<td class="<%=styleClass%>" align="right">
						<bean:define id="discountPercent" name="element" property="discountPercent"/>
						<fmt:formatNumber value='${discountPercent}' type="currency" currencySymbol=""/>
					</td>
					<td class="<%=styleClass%>" align="right">
						<bean:write name="element" property="qty"/>
						<bean:define id="qtyTotal" name="element" property="qtyTotal"/>
					</td>
					
					<td class="<%=styleClass%>" align="right">
						<bean:define id="standardPrice" name="element" property="standardPrice"/>
						<fmt:formatNumber value='${standardPrice}' type="currency" currencySymbol=""/>
						<bean:define id="priceTotal" name="element" property="priceTotal"/>
					</td>
					
					<td class="<%=styleClass%>" align="right">
						<bean:define id="taxAmt" name="element" property="taxAmt"/>
						<fmt:formatNumber value='${taxAmt}' type="currency" currencySymbol=""/>
						<bean:define id="taxTotal" name="element" property="taxTotal"/>
					</td>	
					
					<td class="<%=styleClass%>" align="right">
						<bean:define id="price" name="element" property="price"/>
						<fmt:formatNumber value='${price}' type="currency" currencySymbol=""/>
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
				
					<td class="total" align="left"><pos:element columnName="GrandTotal"/></td>
					<td class="total">&nbsp;</td>
					<td class="total">&nbsp;</td>
					<td nowrap class="total" align="right">  		  
						<fmt:formatNumber value='${qtyTotal}'/>
					</td>
					<td nowrap class="total" align="right">  		  
						<fmt:formatNumber value='${priceTotal}' type="currency" currencySymbol=""/>
					</td>
					<td nowrap class="total" align="right">  		  
						<fmt:formatNumber value='${taxTotal}' type="currency" currencySymbol=""/>
					</td>
					<td nowrap class="total" colspan="2">  		  
						<fmt:formatNumber value='${grandTotal}'type="currency" currencySymbol='${currSymbole}'/>
					</td>
					<td class="total">&nbsp;</td>
					<td class="total">&nbsp;</td>
					
					
				</tr>
	</logic:notEmpty>
</logic:present>	
		
</table>
<script type="text/javascript">
<!--
	var popup = $('divProdDetails');
	popup.style.display= 'none';
	
	popup.onclick = function(e)
	{		
		popup.innerHTML = 'Loading product details.....';
		popup.style.display= 'none';		
	};
	var xmlHttpRequest;
	
	function createXMLHttpRequest() 
	{
	   try { return new ActiveXObject("Msxml2.XMLHTTP"); } catch (e) {}
	   try { return new ActiveXObject("Microsoft.XMLHTTP"); } catch (e) {}
	   try { return new XMLHttpRequest(); } catch(e) {}
	   alert("XMLHttpRequest not supported");
	   return null;
 	}
 
	function displayDetails(productId,e)
	{
		if(popup.style.display != 'none') return;
				
		xmlHttpRequest = createXMLHttpRequest();
		var url = "ProductDetailInfoAction.do?action=viewPOSProductDetailInfo&productId=" + productId;
		xmlHttpRequest.onreadystatechange=stateChanged ;
		xmlHttpRequest.open("GET",url,true);
		xmlHttpRequest.send(null);		
		
		popup.style.display = "inline";
		popup.style.left = e.clientX;
		popup.style.top = e.clientY;
	}
	
	function hideDetails()
	{
		popup.style.display = "none";
	}
	
	function stateChanged()
	{
		if (xmlHttpRequest.readyState==4 || xmlHttpRequest.readyState=="complete")
		{
			popup.innerHTML=xmlHttpRequest.responseText; 
		}
	}
	
//-->
</script>	
		