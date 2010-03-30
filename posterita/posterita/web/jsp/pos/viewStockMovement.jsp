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
 * @author Alok Pathak
--%>


<!--viewStockMovement.jsp-->
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.beans.ProductBean" %>
<%@ page import="org.posterita.user.WebUserInfo" %>
<%@ page import="org.posterita.lib.UdiConstants" %>
<%@ page import="org.posterita.struts.pos.POSReportAction" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

<logic:notPresent name="<%=WebUserInfo.NAME%>" scope="session">
	<jsp:forward page="/LoginHome.do"/>
</logic:notPresent>

<bean:define id="title"><pos:message key="stock.movement" textOnly="true"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %> 

<table class="display" border="1">
<tr>		
	<th><pos:message key="Name"/></th>					
	<th><pos:message key="opening.balance"/></th>			
	<th><pos:message key="qty.received"/></th>				
	<th><pos:message key="qty.sold"/></th>				
	<th><pos:message key="qty.returned.to.supplier"/></th>
	<th><pos:message key="qty.returned.by.customer"/></th>				
	<th><pos:message key="closing.balance"/></th>
</tr>		
<logic:iterate id="element" indexId="count" name="<%=Constants.STOCK_MOVEMENT%>" type="org.posterita.beans.POSReportBean">
<%
	String styleClass = "label";
	if ((count.intValue()%2) != 0)
	styleClass = "contentname";
%>
<tr>
	<td class=<%=styleClass%>>
		<html:link action="POSProductSalesAnalysisAction.do?action=viewProductSalesDetails&productId=<%= element.getProductId() %>">
				<bean:write name="element" property="productName"/>
		</html:link>
	</td>
	<td class=<%=styleClass%>>
		<bean:write name="element" property="openingBalanceQty"/>
	</td>	      
	<td class=<%=styleClass%>>
	    <bean:write name="element" property="qtyOfGoodsReceived"/>
	</td>	   
	<td class=<%=styleClass%>>
	    <bean:write name="element" property="qtyOfGoodsSold"/>
	</td>	
	<td class=<%=styleClass%>>
	    <bean:write name="element" property="qtyOfGoodsReturned"/>
	</td>	
	<td class=<%=styleClass%>>
	    <bean:write name="element" property="qtyReturnedByCustomer"/>
	</td>	
	<td class=<%=styleClass%>>
	    <bean:write name="element" property="closeingBalanceQty"/>
	</td>
</tr>
</logic:iterate>	
</table>
<br>		
	    									 					 
<%@ include file="/jsp/include/posFooter.jsp" %>
