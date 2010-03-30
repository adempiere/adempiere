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
<%@ page import="org.posterita.form.OrderLineForm" %>
<%@ page import="org.posterita.form.OrderLineForm" %>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>
<%@ page import="java.text.DecimalFormat" %>
<!--pospaymentDetails.jsp-->
<table class="content">

				
	<tr>
		<td>
						
		<logic:present name="<%=Constants.SHOPPING_ORDER_CART_ITEMS%>">
			<logic:notEmpty name="<%=Constants.SHOPPING_ORDER_CART_ITEMS%>">
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
								defaultDiscount = discountPercentage[count.intValue()].toString();
								
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
				<tr>
					<td class="total" colspan="5">&nbsp;</td>
					<td class="total" align="right"><input id="discountTotal" type="text" class="text medium"></td>
					<td class="total" align="right"><input id="grandTotal" type="text" class="text medium"></td>
				</tr>
			</table>
			</logic:notEmpty>
		</logic:present>				
		</td>
		
	</tr>	

		
	<tr>
		<td>&nbsp;</td>
	</tr>
	
	<tr>	
		<td>
			<table align="center" border="0" cellpadding="5" cellspacing="0">
				<tr>
					<td>
						<html:button styleClass="cash smallbutton" property="<%=MOrder.PAYMENTRULE_Cash%>" onclick="showCash();" accesskey="s">
					   		&nbsp;
					   	</html:button>		   	 	  	
					</td>
				  
					<td>
				   		<html:button styleClass="card smallbutton" property="<%=MOrder.PAYMENTRULE_CreditCard%>" onclick="showCard();" accesskey="r">
				   			&nbsp;
				   		</html:button>		   		
					</td>
					
					<td>
						<html:button styleClass="cheque smallbutton" property="<%=MOrder.PAYMENTRULE_Check%>" onclick="showCheque();" accesskey="q">
				   			&nbsp;
				   		</html:button>   		 
					 </td>		   		 	
				
					 <td>
						<html:button styleClass="mixed smallbutton" property="<%=UdiConstants.PAYMENTRULE_MIXED%>" onclick="showMixed();" accesskey="m">
							&nbsp;
						</html:button>				   		 
				 	</td>		   		 	
				</tr>	
			</table>
		</td>
	</tr>

	<tr>
		<td>&nbsp;</td>
	</tr>	
	
	<tr>
	<td>
		<table border="0" cellspacing="0" cellpadding="5">
			<tr>	
				<td> 
				 	<label><pos:message key="TenderType"/></label>
				</td>
				<td colspan="3">
					<html:text property="trxType" readonly="true" styleClass="text"/>
				 	<html:hidden property="bpartnerId"/>
				</td>
			</tr>
		</table>
		<table border="0" cellspacing="0" cellpadding="5">
			<tr>
				<td>
					<div id="cashLabel" class="nodisplay"><label><pos:message key="cash.amount"/></label></div>
				</td>
				<td colspan="3">
					<div id="cashTxt" class="nodisplay"><html:text property="paymentByCash" readonly="true" styleClass="text"/></div>
				</td>
			</tr>
			<tr>
				<td>
					<div id="cardLabel" class="nodisplay"><label><pos:message key="card.amount"/></label></div>
				</td>
				<td>
					<div id="cardTxt" class="nodisplay"><html:text property="paymentByCard" styleClass="text"/></div>
				</td>
				<td>
					<div id="cardNoLabel" class="nodisplay"><label><pos:message key="card.no"/></label></div>
				</td>
				<td>
					<div id="cardNoTxt" class="nodisplay"><html:text property="creditCardNumber" styleClass="text" /></div>
				</td>
			</tr>
			<tr>
				<td>
					<div id="chequeLabel" class="nodisplay"><label><pos:message key="cheque.amount"/></label></div>
				</td>
				<td>
					<div id="chequeTxt" class="nodisplay"><html:text property="paymentByChq" styleClass="text" /></div>
				</td>
				<td>
					<div id="chequeNoLabel" class="nodisplay"><label><pos:message key="cheque.no"/></label></div>
				</td>
				<td>
					<div id="chequeNoTxt" class="nodisplay"><html:text property="chequeNo" styleClass="text"/></div>
				</td>
			</tr>
		</table>
		</td>
	</tr>
	
	<tr><td>&nbsp;</td></tr>  
	<tr>	
		<td align="right">
			<html:link href="CreatePOSOrder.do">
		   		<img src="images/pos/buttons/button_edit.gif" styleClass="continue smallbutton"/>
		   	</html:link>   	 	  	
		</td>
	</tr>	   		
      		
	<tr> 			
		<td align="right">
			<html:button property="btn" styleClass="continue smallbutton" onclick="createOrder()" accesskey="c">
				&nbsp;
			</html:button>		   	 	  	
		</td>			
    </tr>
    <tr>
		<td>&nbsp;</td>
	</tr>					
	<tr>					  
		<td align="center">
			<table border="0" cellpadding="10">
				<tr>
					<td>
						<label><pos:message key="cash"/> -</label><label class="red">Alt-S</label>
					</td>
					<td>
						<label><pos:message key="card"/> -</label><label class="red">Alt-R</label>
					</td>
					<td>
						<label><pos:message key="cheque"/> -</label><label class="red">Alt-Q</label>
					</td>
					<td>
						<label><pos:message key="mixed"/> -</label><label class="red">Alt-M</label>
					</td>
					<td>
						<label><pos:message key="continue"/> -</label><label class="red">Alt-C</label>
					</td>				
				</tr>
			</table> 		   	 	  	
		</td>		   		
	</tr>	
	
</table>