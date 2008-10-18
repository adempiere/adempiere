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


<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.user.WebUserInfo" %>
<%@ page import="org.posterita.order.UDIOrderTypes" %>
<%@ page import="org.compiere.model.MOrder" %>
<%@ page import="org.compiere.model.MInOut" %>
<%@ page import="org.posterita.webstore.businesslogic.CurrencyManager" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/dcs.tld" prefix="dcs" %>
<%@ page import="org.posterita.beans.WebstoreUserBean" %>



	<tiles:insert page="/jsp/include/tshirtHeader.jsp">
	  	<tiles:put name="title"><bean:message key="dealerOrder.results.title"/></tiles:put>
	</tiles:insert>
	
	
<table border="0" cellpadding="4" cellspacing="1" width="100%">
<tr>
	<td height="1"><img src="images/tshirt/1pixel.gif" width="1" height="1" border="0">
	</td>
</tr>
	<tbody>
		<tr>
			<td class="whiteText">Sales Order</td>
		</tr>
	</tbody>
</table>

	<table width="100%" border="0" cellpadding="2" cellspacing="0">
	
		<tr>
			<td>
				<%@ include file="/jsp/include/errors.jsp" %>				
			</td>
		</tr>

<%
	MOrder order = (MOrder) request.getSession().getAttribute(Constants.MORDER);
	WebstoreUserBean userbean = (WebstoreUserBean)	request.getSession().getAttribute(Constants.WEBSTORE_USER_BEAN);
	String isPaid = (String) request.getSession().getAttribute(Constants.IS_PAID);
	
										
	String currSymbole = CurrencyManager.getCurrencySymbol(order.getC_Currency_ID());

	if (isPaid == null)
		isPaid = "N";
%>
		
		<tr>
			<td class="orderTitle" align="center">
				<h2>Sales Order</h2>
				
				<hr>
			</td>
		</tr>
					
		<tr>
			<td width="100%">
				<table cellpadding="0" cellspacing="0" border="0" width="100%">
					<tr>
						<td width="50%">
							<table width="40%" class="darkColour">
								<tr>
									<td>
										To:
									</td>
								</tr>
							</table>
						</td>
						
						<td align="right">
							<table width="40%" class="darkColour">
								<tr>
									<td>
										From:
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>		
			</td>	
		</tr>					
					
		<tr>
			<td width="100%">
  				<table cellpadding="0" cellspacing="0" border="0" width="100%">
  					<tr>
						<td class="label" width="50%" align="left">
							<table class="lightColour" width="40%">
								<tr>
									<td class="label">
	  									<c:out value='${you.name}'/>&nbsp;
	  									<c:out value='${you.name2}'/>
									</td>
								</tr>

								<tr>
  									<td class="label">
  										<c:out value='${youLocation.address1}'/>
  									</td>
  								</tr>
  								
  								<tr>
  									<td class="label">
  										<c:out value='${youLocation.city}'/>
  									</td>
  								</tr>
  								
  								<tr>
  									<td class="label">
  										<c:out value='${youLocation.country}'/>
  									</td>
  								</tr>
  							</table>
  						</td>
						  			
  						<td class="label" width="50%" align="right">
  							<table class="lightColour" width="40%">
  								<tr>
  									<td class="label">
	  									<c:out value='${me.name}'/>
  									</td>
  								</tr>
  								<tr>
  									<td class="label">
  										<c:out value='${meLocation.address1}'/>
  									</td>
  								</tr>
			  								
  								<tr>
  									<td class="label">
  										<c:out value='${meLocation.city}'/>
  									</td>
  								</tr>
  								
  								<tr>
  									<td class="label">
  										<c:out value='${meLocation.country}'/>
  									</td>
  								</tr>
  							</table>
  						</td>
					</tr>
				</table>
			</td>
  		</tr>
			  		
  		<tr>
  			<td>
  				Ref SO No: <c:out value='${morder._ID}'/>
  			</td>
  		</tr>
	  		
  		<tr>
  			<td>
  				Date Ordered: <fmt:formatDate value='${morder.dateOrdered}'/>
  			</td>
  		</tr>		

 		<tr>
 			<td>
 				Status: <dcs:orderStatus name="<%=Constants.MORDER%>" property="docStatus"/>
 			</td>
		</tr>	
		
 		<tr>
 			<td>
				<logic:present name="<%=Constants.IS_PAID%>">
	 				Is Paid: <bean:write name="<%=Constants.IS_PAID%>"/>
 				</logic:present>	
 			</td>
		</tr>				  			  		
  		
		<tr>
  			<td valign="top">
  			
  							<table width="100%" cellpadding="5" cellspacing="1" align="center" border="0">
						  		<tr>
								   	<td class="whiteText" align="left">Description</td>
								   	<td class="whiteText" width="5%" align="left">Qty</td>
								   	<td class="whiteText" align="left">Unit Price</td>
								   	<td class="whiteText" align="left">Total Price</td>
						  		</tr>

				   				<logic:iterate name="<%=Constants.MORDER_LINES_COLLECTION%>" indexId="count" id="element" type="org.posterita.beans.WebOrderLineBean">

								<%
									String styleClass = "lightColour";
									if ((count.intValue()%2) != 0)
										styleClass = "darkColour";
								%>
						   					
				   				<tr>
				   					<td class="<%=styleClass%>">
				   						<bean:write name="element" property="description"/>
				   					</td>

				   					<td class="<%=styleClass%>">
				   						<bean:write name="element" property="qtyOrdered"/>
				   					</td>

				   					<td class="<%=styleClass%>">
				   						<%=currSymbole%>
					   					<fmt:formatNumber value='${element.priceActual}' type="currency" currencySymbol='${currSymbole}'/> 
				   					</td>

				   					<td class="<%=styleClass%>">
				   						<%=currSymbole%>
					   					<fmt:formatNumber value='${element.lineNetAmt}'/> 
				   					</td>
				   				</tr>
				   				
				   				</logic:iterate>
				   				
				   				<tr class="noBorder">
						 			<td class="darkColour" align="right" colspan="3"> 	
										&nbsp;<B><bean:message key="orderView.orderPriceDetails.orderTotal"/></B>
								  	</td>

									<td class="darkColour" nowrap> 	
										 
										<B>
											<%=currSymbole%>	 
											<fmt:formatNumber value='${grandTotal}'/>
										</B>
								  	</td>
					  			</tr>				   				
				   				
				 			</table>
			</td>
		</tr>
		
		<tr>
			<td>
				<%--
					if ((order.getDeliveryViaRule().equals(MInOut.DELIVERYVIARULE_Shipper)) && (isPaid.equals(Constants.NO_CHAR)))
					{
				
				
				<form action="https://www.sandbox.paypal.com/cgi-bin/webscr" method="post">
				
					<input type="hidden" name="upload" value="1">
					<input type="hidden" name="cmd" value="_cart">
					<input type="hidden" name="business" value="tmk@tmk.com">
					<input type="hidden" name="currency_code" value="EUR">

					<input type="image" src="https://www.sandbox.paypal.com/en_US/i/btn/x-click-but23.gif" border="0" name="submit" alt="Make payments with PayPal - it's fast, free and secure!">
					<input type="hidden" name="return" value="http://www.posterita.com/CheckoutSuccess.do">
					<input type="hidden" name="notify_url" value="<%="http://www.posterita.com/PaypalValidationAction?action=validate&orderId=" + order.get_ID()%>">
					<input type="hidden" name="cancel_return" value="<%="http://www.posterita.com/VoidOrderAction?action=voidOrder&orderId=" + order.get_ID()%>">
					<input type="hidden" name="image_url" value="http://tmkgroup.dyndns.org/posterita/images/tshirt/logo.gif">

					<input type="hidden" name="email" value="<%=userbean.getUserBean().getEmail()%>">
					<input type="hidden" name="first_name" value="<%=userbean.getUserBean().getUsername()%>">
					<input type="hidden" name="last_name" value="<%=userbean.getUserBean().getUserSurname()%>">
					<input type="hidden" name="address1" value="<%=userbean.getUserBean().getAddress1()%>">
					<input type="hidden" name="address2" value="<%=userbean.getUserBean().getAddress2()%>">
					<input type="hidden" name="city" value="<%=userbean.getUserBean().getCity()%>">
					<input type="hidden" name="state" value="<%=userbean.getUserBean().getCountryName()%>">


			
	   				<logic:iterate name="<%=Constants.MORDER_LINES_COLLECTION%>" indexId="count" id="element" type="org.posterita.beans.WebOrderLineBean">
						<%int counter = ((Integer)count).intValue()+1;%>																
					
						<input type="hidden" name="<%="item_name_" + counter%>" value="<bean:write name="element" property="description"/>">
						<input type="hidden" name="<%="amount_" + counter%>" value="<bean:write name="element" property="lineNetAmt"/>">
						<input type="hidden" name="<%="on0_" + counter%>" value="Quantity">
						<input type="hidden" name="<%="os0_" + counter%>" value="<bean:write name="element" property="qtyOrdered"/>">
						<input type="hidden" name="<%="on1_" + counter%>" value="Unit Price">
						<input type="hidden" name="<%="os1_" + counter%>" value="<bean:write name="element" property="priceActual"/>">
					
					</logic:iterate>
				</form>
					
					}
				--%>	
			</td>
		</tr>	
		
	</table>



	<%@ include file="/jsp/include/tshirtFooter.jsp" %>
