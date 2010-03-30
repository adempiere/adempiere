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


<!--viewCommissionDetails.jsp-->
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.beans.ProductBean" %>
<%@ page import="org.posterita.user.WebUserInfo" %>
<%@ page import="org.posterita.lib.UdiConstants" %>
<%@ page import="org.posterita.struts.pos.CommissionAction" %>



<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

<logic:notPresent name="<%=WebUserInfo.NAME%>" scope="session">
	<jsp:forward page="/LoginHome.do"/>
</logic:notPresent>

<bean:define id="title"><pos:message key="commission.details" textOnly="true"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %> 

<table class="main">
<tr>
					<td>
						<html:link href="ViewCommissionAction.do?action=viewCommission">
							<pos:message key="back"/>
						</html:link>	
							
					</td>
				</tr>
	<tr>
		<td>		
			<%@ include file="/jsp/include/errors.jsp" %>	 
				<table class="display scrollpane sortable" id="1111" border="1">
				
				
					<tr>
						<th><pos:message key="Reference"/></th>
						<th width="80px"><pos:message key="Info"/></th>
						<th><pos:message key="Price"/></th>
						<th><pos:message key="Qty"/></th>
					</tr>	
						<logic:present name="<%=Constants.COMMISSION_AMT_DETAILS%>">
							<logic:iterate id="element" indexId="count" name="<%=Constants.COMMISSION_AMT_DETAILS %>" type="org.posterita.beans.CommissionBean">
								<tr>	
										<%
											String styleClass = "label";
											if ((count.intValue()%2) != 0)
												styleClass = "contentname";
										%>
									
										<logic:notEqual name="element" property="orderLineId" value="0">
									   		<td class=<%=styleClass%> >
									   			<html:link href="ViewPOSOrderAction.do?action=viewPOSOrders&orderId=<%= element.getOrderId() %>">
													<bean:write name="element" property="reference"/>
												</html:link>	
									   		</td>
									   	</logic:notEqual>	
									   	
									   	<logic:notEqual name="element" property="invoiceLineId" value="0">
									   		<td class=<%=styleClass%> >
									   			<html:link href="ViewInvoiceAction.do?action=viewOrder&documentId=<%= element.getInvoiceId() %>">
													<bean:write name="element" property="reference"/>
												</html:link>
									   		</td>
									   	</logic:notEqual>	
										   <td class="<%=styleClass%>">
												<bean:write name="element" property="info"/>
										   </td>						
										   <td class=<%=styleClass%>>
												<bean:write name="element" property="actualAmt"/>
										   </td>	
										   <td class=<%=styleClass%>>
												<bean:write name="element" property="actualQty"/>
										   </td>
									   					   
									   					
								</tr>
							</logic:iterate>    
						</logic:present>
				</table>							
		</td>
	</tr>			
</table>						
	    									 					 
<%@ include file="/jsp/include/posFooter.jsp" %>
