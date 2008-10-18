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

<%@ page import="org.posterita.Constants" %>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>
<tr>
	<td align="right">
		<label class="greencolor"><pos:message key="filter.by"/>:</label>
	</td>
	
	<td width="3%">
		<label><pos:message key="DocStatus"/></label>
	</td>
	
	<td width="3%">
		<label><pos:message key="month"/></label>
	</td>
	
	<td width="3%">
		<label><pos:message key="year"/></label>						
	</td>
	
	<logic:present name="<%=Constants.PAYMENT_RULES%>">
	<td width="3%">
		<label><pos:message key="PaymentRule"/></label>
	</td>	
	</logic:present>
										
</tr>

<tr>
	<td>
		&nbsp;
	</td>
	
	<td align="right">
		<html:select property="docStatus" onchange="submit()">
			<html:option value=""><pos:message key="all"/></html:option>	
			<%@include file="/jsp/include/documentStatusFilter.jsp"%>
		</html:select>
	</td>
	
	<%@ include file="/jsp/include/historyMonthYearFilter.jsp"%>		

	<logic:present name="<%=Constants.PAYMENT_RULES%>">						
	<td align="right">
		<html:select property="paymentRule" onchange="submit()">
			<html:option value=""><pos:message key="all"/></html:option>	
			<logic:present name="<%=Constants.PAYMENT_RULES%>">
			<%@include file="/jsp/include/paymentRuleFilter.jsp"%>							
			</logic:present>
   		</html:select>																									
	</td>
	</logic:present>

</tr>