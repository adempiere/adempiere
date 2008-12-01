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



<!--viewPOSInfo.jsp-->
<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.beans.*" %>
<%@ page import="org.posterita.struts.pos.POSOrderAction" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos" %>

<logic:notPresent name="<%=WebUserInfo.NAME%>" scope="session">
	<jsp:forward page="/LoginHome.do"/>
</logic:notPresent>

<bean:define id="title"><pos:message key="pos.info" textOnly="true"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %> 

<table class="display" border="1">
<tr>
	<th>
		<pos:message key="Name"/>
	</th>
	<th>
		<pos:message key="payment.type"/>
	</th>
	<th>
		<pos:message key="Amount"/>
	</th>	
</tr>
<logic:iterate indexId="count" id="element" name="<%=Constants.POS_INFO%>" type="org.posterita.beans.POSInfoBean">
	<tr>
		<%
			String styleClass = "label";
			if ((count.intValue()%2) != 0)
				styleClass = "contentname";
		%>
	
		<td class="<%=styleClass%>">
			<bean:write name="element" property="posName"/>
		</td>
	
		<td class="<%=styleClass%>">
			<bean:write name="element" property="paymentRule"/>
		</td>
		
		<td class="<%=styleClass%>">
			<bean:define id="grandTotal" name="element" property="orderGrandTotal"/>
			<fmt:formatNumber value='${grandTotal}' type="currency" currencySymbol="Rs  "/>
		</td>
	</tr>	
</logic:iterate>
	
</table>							
<%@ include file="/jsp/include/posFooter.jsp" %>
