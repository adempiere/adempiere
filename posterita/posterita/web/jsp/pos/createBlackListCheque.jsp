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
 * @author Martine
--%>

<!--createBlackListedCheque-->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

<bean:define id="title"><pos:message textOnly="true" key="add.black.listed.cheque"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %> 




<html:form action="/CreateBlackListAction">
<!-- <html:hidden property="action" value="<%--=CustomerAction.CREATE_POS_CUSTOMER--%>"/> -->

<html:hidden property="action" value="createBlackListed"/>

<table align="left" cellpadding="5">
	<tr>
		<td>
			
			<tr>
				<td>
					<label>	<pos:message key="BankName"/>:</label><mandatory>*</mandatory>
				</td>

				<td>
					<html:text property="blackListedBankName" styleClass="text"/>
				</td>
			</tr>
			<tr>
				<td>
					<label>	<pos:message key="CheckNo"/>:</label><mandatory>*</mandatory>
				</td>

				<td>
					<html:text property="blackListedChequeNo" styleClass="text"/>
				</td>
			</tr>
			
			<tr>
				<td></td>
				<td align="right">
					<html:button property="button" styleClass="save smallbutton" onclick="submit()" tabindex="10" accesskey="s">
					&nbsp;
	        		</html:button>
				</td>
			</tr>
</table>

</html:form>	
			

<%@ include file="/jsp/include/posFooter.jsp" %>
	   
	