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
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>
<tr>
	<td align="right">
		<label class="greencolor"><pos:message key="filter.by"/>:</label>
	</td>
	
	<td width="3%" align="center">
		<label><pos:message key="month"/></label>
	</td>
	
	<td width="3%" align="center">
		<label><pos:message key="year"/></label>						
	</td>
	
	<logic:present name="<%=Constants.BPARTNERS%>">
	<td width="3%">
		<label>Name</label>
	</td>	
	</logic:present>
					
	<td width="15%" nowrap align="center">
		<label><pos:message key="shipment.status"/></label>
	</td>	
	
	<td width="15%" nowrap align="center">
		<label><pos:message key="order.type"/></label>
	</td>										
</tr>

<tr>
	<td>
		&nbsp;
	</td>
	
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
	
	<logic:present name="<%=Constants.BPARTNERS%>">	
		<td align="right">
			<html:select property="bpartnerId" onchange="submit()" style="width:100%">
				<html:option value=""><pos:message key="all"/></html:option>	
				
					<html:options collection="<%=Constants.BPARTNERS%>" property="key" labelProperty="value"/>								
				
	   		</html:select>																									
		</td>	
	</logic:present>
	
	<td align="right">
		<html:select property="docStatus" onchange="submit()" style="width:100%">
			<html:option value=""><pos:message key="all"/></html:option>	
			<logic:present name="<%=Constants.DOC_STATUS%>">
				<html:options collection="<%=Constants.DOC_STATUS%>" property="key" labelProperty="value"/>								
			</logic:present>
   		</html:select>																									
	</td>
	
	
	<logic:present name="<%=Constants.ORDER_TYPES%>">						
	<td align="right">
		<html:select property="orderType" onchange="submit()">
			<html:option value=""><pos:message key="all"/></html:option>	
			<logic:present name="<%=Constants.ORDER_TYPES%>">
			<html:options collection="<%=Constants.ORDER_TYPES%>" property="orderType"/>							
			</logic:present>
   		</html:select>																									
	</td>
	</logic:present>
</tr>

