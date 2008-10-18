<%--
 *  Product: Posterita Web-Based POS and Adempiere Plugin
 *  Copyright (C) 2008  Posterita Ltd
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
 *  @author Servansingh
--%>


<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>	
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>
<%@page import="org.posterita.struts.pos.CurrencyAction"%>
<%@page import="org.posterita.Constants"%>
<bean:define id="title">Currency</bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %>
<!-- page contents -->

<table width = "100%">
	<tr>
		<td>
			<html:form action="/CurrencyAction">
				<html:hidden property="action" value="<%=CurrencyAction.LIST_CURRENCIES%>"/>
				<html:text property="isoCode"></html:text>
				<html:submit></html:submit>
			</html:form>
		</td>
	</tr>
</table>

<div class="scrollpane">
	<table class="display sortable" border="1" width="900px" >
		<tr>
			<th class="string"><span><pos:message key="ISO Code"/></span></th>
		    <th class="string" nowrap="nowrap"><span><pos:message key="curSymbol"/></span></th>
		    <th class="string" nowrap="nowrap"><span><pos:message key="Description"/></span></th>
	   	    <th class="string" nowrap="nowrap"><span><pos:message key="Standard Precision"/></span></th>
   	       	<th class="string" nowrap="nowrap"><span><pos:message key="Round Off Factor"/></span></th> 
   	       	<th class="string" nowrap="nowrap"><span><pos:message key="Is Active"/></span></th>      
	   	    <th nowrap="nowrap"><span><pos:message key="update.details"/></span></th>
		</tr> 	
	
		<logic:present name="<%=Constants.CURRENCY_LIST %>">

		<logic:iterate id="currencyList" name="<%=Constants.CURRENCY_LIST %>" type="org.posterita.beans.CurrencyBean">
		<tr>
			<td class = "label">
				<bean:write name="currencyList" property="isoCode"/>
			</td>
			<td class = "label">
				<bean:write name="currencyList" property="curSymbol"/>
			</td>
			<td class = "label">
				<bean:write name="currencyList" property="description"/>
			</td>
			<td class = "label">
				<bean:write name="currencyList" property="stdPrecision"/>
			</td>
			<td class = "label">
				<bean:write name="currencyList" property="roundOffFactor"/>
			</td>
			<td class = "label">
				<bean:write name="currencyList" property="isActive"/>
			</td>
			<td  align="center" class = "label">
				<html:link href="<%="CurrencyAction.do?action=viewCurrency&currencyId=" + currencyList.getCurrencyId()%>">
					<img src="images/tango/accessories-text-editor.png"  border="0"> 
				</html:link>
		 	</td>	 	
		</tr>
		</logic:iterate>

		</logic:present>
	</table>
</div>

<%@ include file="/jsp/include/posFooter.jsp" %>