<%--
 *  Product: Posterita Web-Based POS (an Adempiere Plugin)
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
 * @author Vishee 
--%>


<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.user.WebUserInfo" %>
<%@ page import="org.posterita.order.UDIOrderTypes" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/dcs.tld" prefix="dcs" %>


<bean:define id="currSymbole" name="<%= Constants.CURRENCY_SYMBOLE %>"/>


	<tiles:insert page="/jsp/include/tshirtHeader.jsp">
	  	<tiles:put name="title"><bean:message key="dealerOrder.results.title"/></tiles:put>
	</tiles:insert>


	<table width="100%" border="0" cellpadding="5" cellspacing="0">
	
		<tr>
			<td>
				<%@ include file="/jsp/include/errors.jsp" %>				
			</td>
		</tr>
		
		<%@ include file="/jsp/webstore/documentHeader.jsp" %>
  		
  		<tr>
  			<td>
				Doc Status:<dcs:orderStatus name="<%=Constants.MORDER%>" property="docStatus"/>
		  </td>			
   		</tr>
  		
		<tr>
  			<td valign="top">
  			
  							<table width="100%" cellpadding="5" cellspacing="1" align="center" border="0">
						  		<tr>
								   	<td class="tableHeader" align="left">Description</td>
								   	<td class="tableHeader" width="5%" align="left">Qty</td>
								   	<td class="tableHeader" align="left">Unit Price</td>
								   	<td class="tableHeader" align="left">Total Price</td>
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
					   					<fmt:formatNumber value='${element.priceActual}' type="currency" currencySymbol='${currSymbole}'/> 
				   					</td>

				   					<td class="<%=styleClass%>">
					   					<fmt:formatNumber value='${element.lineNetAmt}' type="currency" currencySymbol='${currSymbole}'/> 
				   					</td>

				   				</tr>
				   				
				   				</logic:iterate>
				   				
				   				<tr class="noBorder">
						 			<td class="darkColour" align="right" colspan="3"> 	
										&nbsp;<B><bean:message key="orderView.orderPriceDetails.orderTotal"/></B>
								  	</td>

									<td class="darkColour" nowrap> 		  
										<B><fmt:formatNumber value='${grandTotal}'type="currency" currencySymbol="EUR"/></B>
								  	</td>
					  			</tr>				   				
				   				
				 			</table>
			</td>
		</tr>


		
	</table>



	<%@ include file="/jsp/include/tshirtFooter.jsp" %>
