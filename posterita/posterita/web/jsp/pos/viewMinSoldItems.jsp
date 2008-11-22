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


<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/tabTop.jsp" %><pos:message key="min.sold.item" textOnly="true"/><%@ include file="/jsp/include/tabBottom.jsp" %>	   
<%@ include file="/jsp/include/errors.jsp" %> 

<table align="center" width="100%" border="1" cellpadding="5" cellspacing="0" class="content">
<tr>		
	<th><pos:message key="Name"/></th>	
	<th><pos:message key="Qty"/></th>
</tr>		
<logic:iterate id="element" indexId="count" name="<%=Constants.MIN_SOLD_ITEMS%>" type="org.posterita.beans.ProductBean">
<%
	String styleClass = "label";
	if ((count.intValue()%2) != 0)
	styleClass = "contentname";
%>
<tr>
	<td class=<%=styleClass%> nowrap="nowrap">
		<bean:write name="element" property="productName"/>
	</td>
	<td class=<%=styleClass%>>
		<bean:write name="element" property="quantity"/>
	</td>
</tr>
</logic:iterate>	
</table>
<br> 							
	    									 					 
<%@ include file="/jsp/include/posFooter.jsp" %>
