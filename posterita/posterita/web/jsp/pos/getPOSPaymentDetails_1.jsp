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


<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/dcs.tld" prefix="dcs" %>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>


<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/tabTop.jsp" %><pos:message key="pos.order"/><%@ include file="/jsp/include/tabBottom.jsp" %>	   
<%@ include file="/jsp/include/errors.jsp" %> 
	  						   
<form action="/posterita/CreatePOSOrderAction.do" method="get">
<input type="hidden" name="action" value="createPOSOrder"/>

<tr>
	<td>&nbsp;</td>
</tr>

<tr>
	<td>
	<table width="80%" border="0" cellpadding="5" cellspacing="0" class="content">
		<tr>
		   <logic:present name="<%=Constants.SHOPPING_ORDER_CART_ITEMS%>">
				<logic:notEmpty name="<%=Constants.SHOPPING_ORDER_CART_ITEMS%>">
				<tr>					
					<th>
						<B><pos:message key="Description"/></B>
					</th>
					
					<th>
						<B><pos:message key="Qty"/></B>
					</th>
					
					<th>
						<B><pos:message key="Price"/></B>
					</th>
					
					<th>
						<B><pos:message key="Discount"/></B>
					</th>
				
				    <th>
						<B><pos:message key="actual.price"/></B>
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
				
					<td class="<%=styleClass%>">
						<bean:write name="element" property="qty"/>
					</td>	
					
					<td class="<%=styleClass%>">
						<bean:write name="element" property="price" />
						<input type="hidden" name="price" value="<%= element.getPrice() + ""%>" id="price<%= count%>"/>													
					</td>	
				
				    <td class="<%=styleClass%>">												
						<input type="text" name="discountPercent" value="0" id="discountPercent<%= count%>" onchange="setPriceValue('discountPercent<%= count%>', 'price<%= count%>','actualPrice<%= count%>')"/>
					</td>	
					
					 <td class="<%=styleClass%>">													
						<input type="text" name="actualPrice" value="<%= element.getActualPrice() + "" %>" id="actualPrice<%= count%>" onchange="setDiscountValue('discountPercent<%= count%>', 'price<%= count%>','actualPrice<%= count%>')"/>													
					</td>													
				</tr>
				</logic:iterate>
				</logic:notEmpty>
		</logic:present>	
		</tr>
	</table>	
	</td>
</tr>

<tr>
	<td>&nbsp;</td>
</tr>

<tr>
	<td>&nbsp;</td>
</tr>	
	
<tr>
	<td>
	<table border="0" cellpadding="5" cellspacing="0">
		<tr>
			<td>
				<input type="button" id="cashBtn" value="Cash" style="width:100px;height:40px" onclick="showCash()"/>
			</td>
			<td>
				<input type="button" id="cardBtn" value="Card" style="width:100px;height:40px" onclick="showCard()"/>
			</td>
			<td>
				<input type="button" id="chequeBtn" value="Cheque" style="width:100px;height:40px" onclick="showCheque()"/>
			</td>
			<td>
				<input type="button" id="MixedBtn" value="Mixed" style="width:100px;height:40px" onclick="showMixed()"/>
			</td>
		</tr>
	</table>
	<br>
	<script>
		function showCash()
		{
			document.getElementById("paymentDiv").innerHTML = "<input type='hidden' id='trxType' name='trxType' value='cash'/><table border='0' cellpadding='5' cellspacing='0' class='content'><tr><th><b>Tender Type :</b></th><th>Cash</th></tr><tr><td class='contentname'>Cash Amt :</td><td class='contentname'><input id='paymentByCash' type='text'></td></tr></table>";			
		}
		
		function showCard()
		{
			document.getElementById("paymentDiv").innerHTML = "<input type='hidden' id='trxType' value='card'/><table border='0' cellpadding='5' cellspacing='0' class='content'><tr><th><b>Tender Type :</b></th><th colspan='3'>Card</th></tr><tr><td class='contentname'>Card Amt :</td><td class='contentname'><input id='paymentByCard' type='text'></td><td class='contentname'>Card No :</td><td class='contentname'><input type='text'></td></tr></table>";
			//prompt("Card Number :");
			
		}
		
		function showCheque()
		{
			document.getElementById("paymentDiv").innerHTML = "<input type='hidden' id='trxType' value='cheque'/><table border='0' cellpadding='5' cellspacing='0' class='content'><tr><th><b>Tender Type :</b></th><th colspan='3'>Cheque</th></tr><tr><td class='contentname'>Cheque Amt :</td><td class='contentname'><input id='paymentByChq' type='text'></td><td class='contentname'>Card No :</td><td class='contentname'><input type='text'></td></tr></table>";
			//prompt("Cheque Number :");
		}
		
		function showMixed()
		{
			document.getElementById("paymentDiv").innerHTML = "<input type='hidden' id='trxType' value='mixed'/><table border='0' cellpadding='5' cellspacing='0' class='content'><tr><th><b>Tender Type :</b></th><th colspan='3'>Mixed</th></tr><tr><td class='contentname'>Cash Amt :</td><td colspan='3' class='contentname'><input id='paymentByCash' type='text'></td></tr><tr><td colspan='4' class='label'>&nbsp;</td></tr><tr><td class='contentname'>Card Amt :</td><td class='contentname'><input id='paymentByCard' type='text'></td><td class='contentname'>Card No :</td><td class='contentname'><input type='text'></td></tr><tr><td colspan='4' class='label'>&nbsp;</td></tr><tr><td class='contentname'>Cheque Amt :</td><td class='contentname'><input id='paymentByChq' type='text'></td><td class='contentname'>Cheque No :</td><td class='contentname'><input type='text'></td></tr></table>";
			
		}
		
		function hide(element)
		{
			document.getElementById(element).style.display = "none";
		}
		
		function show(element)
		{
			document.getElementById(element).style.display = "inline";
		}
		
		function setPaymentType(type)
		{
			document.getElementById("trxType").value = type;
		}
		
		function copyValueTo(elementFrom,elementToId)
		{
			document.getElementById(elementToId).value = elementFrom.value;
		}
		
		function setValue(elementId,value)
		{
			document.getElementById(elementId).value = value;
		}		
		
	</script>
	
	<div id="paymentDiv">
		 <!-- 
		<input type="hidden" id="trxType" value="cash">
		<table border='0' cellpadding='5' cellspacing='0' class='content'><tr><th><b>Tender Type :</b></th><th>Cash</th></tr><tr><td class='contentname'>Cash Amt :</td><td class='contentname'><input id="paymentByCash" type='text'></td></tr></table>
		 -->
	</div>	
	
	<table border="0" cellpadding="5" cellspacing="0">	  
		<tr>			
			<td align="right">
		   		<html:button property="btn" styleClass="button" onclick="submit()">
		   			<pos:message key="submit"/>
		   		</html:button>		   	 	  	
			</td>			
	    </tr>	     
	</table>
	</td>
</tr>	
	
</form>	
       		
<%@ include file="/jsp/include/posFooter.jsp" %>	
