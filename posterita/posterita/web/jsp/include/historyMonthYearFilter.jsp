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

<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

<td align="right">
	<html:select property="month" onchange="submit()">
		<html:option value=""><pos:message key="all"/></html:option>	
		<%@include file="/jsp/include/month.jsp"%>							
	</html:select>	
</td>

<td align="right">
	<html:select property="year" onchange="submit()">
		<html:option value=""><pos:message key="all"/></html:option>	
		<%
			int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
		%>
		<html:option value="<%= String.valueOf(currentYear) %>"><%= currentYear %></html:option>
		<html:option value="<%= String.valueOf(currentYear - 1) %>"><%= currentYear - 1 %></html:option>							
	</html:select>											
</td>			