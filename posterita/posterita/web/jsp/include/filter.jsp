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

<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>
<tr>
	<td class="label12pxbold" align="right">
		<pos:message key="filter.by"/> : &nbsp;
	</td>
	
	<td width="3%" class="label">
		<pos:message key="month"/>					
	</td>
	
	<td width="3%" class="label">
		<pos:message key="year"/>
	</td>
	
	<logic:present name="<%=Constants.BPARTNER%>">
	<td width="3%" class="label">
		<pos:message key="dealer.name"/>
	</td>	
	</logic:present>
	
	<td width="3%" class="label">
		<pos:message key="DocStatus"/>
	</td>										
</tr>

<tr>
	<td>
		&nbsp;
	</td>
	
	<td>
		<html:select property="month" onchange="submit()">
			<html:option value=""><pos:message key="all"/></html:option>	
			<%@include file="/jsp/include/month.jsp"%>							
		</html:select>	
	</td>
	
	<td>
		<html:select property="year" onchange="submit()">
			<html:option value=""><pos:message key="all"/></html:option>	
			<%
				int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
			%>
			<html:option value="<%= String.valueOf(currentYear) %>"><%= currentYear %></html:option>
			<html:option value="<%= String.valueOf(currentYear - 1) %>"><%= currentYear - 1 %></html:option>							
   		</html:select>											
	</td>						
		
	<logic:present name="<%=Constants.BPARTNER%>">						
	<td>
		<html:select property="bpartnerId" onchange="submit()">
			<html:option value=""><pos:message key="all"/></html:option>	
			<logic:present name="<%=Constants.BPARTNER%>">
			<html:options collection="<%=Constants.BPARTNER%>" property="key" labelProperty="name"/>							
			</logic:present>
   		</html:select>																									
	</td>
	</logic:present>
			
	<td>
		<html:select property="compiereDocStatus" onchange="submit()">
			<html:option value=""><pos:message key="all"/></html:option>	
			<logic:present name="<%=Constants.DOC_STATUS%>">
			<html:options collection="<%=Constants.DOC_STATUS%>" property="compiereDocStatus" labelProperty="hondaDocStatus"/>							
			</logic:present>
   		</html:select>											
	</td>					   

</tr>