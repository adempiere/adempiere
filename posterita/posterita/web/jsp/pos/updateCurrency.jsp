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
<%@page import="org.posterita.Constants"%>
<%@page import="org.posterita.beans.CurrencyBean"%>
<%@page import="org.posterita.core.TmkJSPEnv"%>
<%@page import="org.posterita.lib.UdiConstants"%>
<%@page import="org.compiere.util.Env"%>
<%@page import="org.posterita.struts.pos.CurrencyAction"%>
<bean:define id="title">Currency</bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %>
<!-- page contents -->
<!-- priceLists.jsp -->

<table class="main">		
	<tr>
		<td>
			<html:form action="/CurrencyAction">
				<html:hidden property="action" value="<%=CurrencyAction.UPDATE_CURRENCY%>"/>
				<bean:define id="currencyId" name="CurrencyForm" property="currencyId" ></bean:define>
				<html:hidden property="currencyId" />
			<table align="left" cellpadding="5" width="100%">	
				<tr>
			<td colspan="3" align="right">
				<html:link href='<%="CurrencyAction.do?action=viewCurrency&isNext=false&currencyId=" + currencyId%>'>
					<img src="images/tango/go-previous.png" title="Previous" alt="Previous" border="0">
				</html:link>
				<html:link href="<%="CurrencyAction.do?action=viewCurrency&isNext=true&currencyId=" + currencyId%>">
					<img src="images/tango/go-next.png" title="Next" alt="Next" border="0">
				</html:link>			
			</td>
		</tr>
				<tr>
					<td width="30%">	
						<label><pos:message key="ISO Code" /></label>
					</td>
					<td>
						<html:text property="isoCode" readonly ="true" ></html:text>
					</td>
				</tr>
				<tr>
					<td width="30%">	
						<label><pos:message key="Currency Symbol" /></label>
					</td>
					<td>
						<html:text property="curSymbol" readonly ="true" ></html:text>
					</td>
				</tr>
				<tr>
					<td width="30%">	
						<label><pos:message key="Description" /></label>
					</td>
					<td>
						<html:text property="description" readonly ="true" ></html:text>
					</td>
				</tr>
				<tr>
					<td width="30%">	
						<label><pos:message key="Standard Precision" /></label>
					</td>
					<td>
						<html:text property="stdPrecision" ></html:text>
					</td>
				</tr>
				<tr>
					<td width="30%">	
						<label><pos:message key="Round Off factor" /></label>
					</td>
					<td>
						<html:text property="roundOffFactor" ></html:text>
					</td>
				</tr>
				<tr>
					<td>
						<label><pos:message key="IsActive"/></label>
					</td>
					<td>
						<html:checkbox  property="isActive" value="true"/>
					</td>
				</tr>
				<tr>
					<td align="left">
						<html:submit styleClass="save smallbutton">&nbsp;</html:submit>
					</td>
				</tr>			
			</table>		
			</html:form>
		</td>
	</tr>
</table>

<%@ include file="/jsp/include/posFooter.jsp" %>