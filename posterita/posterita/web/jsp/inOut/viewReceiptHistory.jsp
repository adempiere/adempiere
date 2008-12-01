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
 * @author Vishee
--%>


<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.compiere.model.MOrg" %>
<%@ page import="org.posterita.struts.InOut.InOutAction" %>


<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<tiles:insert page="/jsp/include/headerTableTop.jsp">
  <tiles:put name="title"><bean:message key="inout.receipt.history"/></tiles:put>
  
</tiles:insert>
	 <%@ include file="/jsp/include/tabTop.jsp" %><bean:message key="inout.receipt.history"/><%@ include file="/jsp/include/tabBottom.jsp" %>   
	<table width="100%" border="0" cellpadding="5" cellspacing="0">
   		<tr>
    		<td>
												
	   		</td>
	   	</tr>
	   	
	   	<tr>
	   		<td>
	   			<table width="100%" border="0" cellpadding="0" cellspacing="5" align="right">
	   			
	   				<html:form action="/ViewReceiptHistoryAction">
	   				<html:hidden property="action" value="<%=InOutAction.VIEW_RECEIPT_HISTORY%>"/>
	   				<%@ include file="/jsp/include/filter.jsp"%>
	   				</html:form>
	   				
	   			</table>	   			
	   		</td>
	   	</tr>
	   	
	   	<tr>
	   		<td>
	   			<table width="100%" border="0" class="content" cellpadding="5" cellspacing="1" align="center" cols="5"> 		  		
					<tr>
						<th colspan="1"> <bean:message key="history.receiptNumber"/></th>
						<th colspan="1"> <bean:message key="history.dealerName" /></th>						
						<th colspan="1"> <bean:message key="history.documentStatus" /></th>
						<th colspan="1"> <bean:message key="history.date" /></th>
						<th colspan="1"> <bean:message key="history.orderNumber" /></th>
						<th colspan="1"> <bean:message key="history.invoiceNumber" /></th>
					</tr>
			
					<logic:iterate indexId="count" name="<%=Constants.RECEIPT_HISTORY%>" id="element" type="org.posterita.beans.InOutHistoryBean">
					<tr>
						<%
							String styleClass = "label";
							if ((count.intValue()%2) != 0)
								styleClass = "contentname";
						%>
						
						<td class="<%=styleClass%>">	
							<bean:write name="element" property="inOutId"/>
						</td>
						
						<td class="<%=styleClass%>">	
							<bean:write name="element" property="partnerName"/>
						</td>						
						
							
						<td class="<%=styleClass%>">	
							<bean:write name="element" property="docStatus"/>
						</td>
						
						<td class="<%=styleClass%>">							
						<%--	<fmt:formatDate value='${element.dateMovement}' pattern='MMM dd yyyy, HH:mm'/>	--%>					
						</td>
						
						<td class="<%=styleClass%>">	
							<bean:write name="element" property="orderId"/>
						</td>
																		
						<td class="<%=styleClass%>">	
							<bean:write name="element" property="invoiceId"/>
						</td>
						
						
					</tr>
					</logic:iterate>
				</table>
			</td>
		</tr>
	</table>
						
	<%@ include file="/jsp/include/footerTableBottom.jsp" %>
	   
	
												