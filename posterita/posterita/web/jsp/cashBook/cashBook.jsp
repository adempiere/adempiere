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
 * @author Ashley
--%>

<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.beans.*" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

<%@page import="org.posterita.struts.pos.CashBookAction"%>
<logic:notPresent name="<%=WebUserInfo.NAME%>" scope="session">
	<jsp:forward page="/LoginHome.do"/>
</logic:notPresent>

<bean:define id="title"><pos:message textOnly="true" key="cashbook"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>	   
<%@ include file="/jsp/include/errors.jsp" %>

<html:form action="CashBookAction" focus="cashBookName">
	<html:hidden property="action"/>
	<html:hidden property="cashBookId"/>
	<bean:define id="strReadOnly" name="CashBookForm" property="readOnly"/>	
	<bean:define id="cashBookId" name="CashBookForm" property="cashBookId"/>
	<%
		boolean readonly = new Boolean((String)strReadOnly).booleanValue();
	
		boolean orgReadOnly = true;
		
		if (cashBookId != null)
		{
			try
			{
				orgReadOnly = (Integer.parseInt((String)cashBookId) > 0);
			}
			catch (Exception ex)
			{}
		}
	%>
	<table align="left" cellpadding="5" width="100%">
		<tr>
			<td>
				<table align="left">
					<tr>
						<td>
							<label><pos:message key="Name"/>:</label><mandatory>*</mandatory>
						</td>
						<td>
							<html:text property="cashBookName" readonly="<%=readonly%>" size="40"/>
						</td>
					</tr>
					<tr>
						<td>
							<label><pos:message key="Organization"/>:</label><mandatory>*</mandatory>
						</td>
					
						<td>
							<html:select property="orgId" styleClass="text" disabled="<%=readonly%>">
								<html:options collection="<%=Constants.ACCESSIBLE_ORGS%>" property="key" labelProperty="name"/>
						 	</html:select>
						</td>
					</tr>
					<tr>
						<td>
							<label><pos:message key="IsActive"/>:</label>
						</td>
						<td>
							<html:multibox property="isActive" value="true" disabled="<%=readonly%>"/>
							<bean:write name="CashBookForm" property="isActive"/>
						</td>
					</tr>
					<tr>
						<td>
							<label><pos:message key="IsDefault"/>:</label>
						</td>
						<td>
							<html:multibox property="isDefault" value="true" disabled="<%=readonly%>"/>
							<bean:write name="CashBookForm" property="isDefault"/>
						</td>
					</tr>
					<tr>
						<td>
							<label><pos:message key="Currency"/>:</label>
						</td>
						<td>
							<html:select property="currencyId" style="width:190px" disabled="true">
								<html:options collection="<%=Constants.CURRENCIES%>" property="key" labelProperty="name"/>																						
							</html:select>
						</td>
					</tr>
					<tr>
						<td>
							<label><pos:message key="Description"/>:</label>
						</td>
						<td>
							<html:textarea property="description" readonly="<%=readonly%>" cols="40" rows="5"/>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td></td>
			<td align="right">
			<logic:equal name="CashBookForm" property="action" value="<%=CashBookAction.VIEW_CASHBOOK%>">
				<html:button property="button" styleClass="newbutton" accesskey="s" onclick="javascript:copyCashBook()">
					Copy As
	   			</html:button>
   			</logic:equal>
   			<logic:equal name="CashBookForm" property="action" value="<%=CashBookAction.VIEW_CASHBOOK%>">
				<html:button property="button" styleClass="newbutton" accesskey="s" onclick="javascript:editCashBook()">
					Edit
	   			</html:button>
   			</logic:equal>
   			<logic:equal name="CashBookForm" property="readOnly" value="false">
				<html:button property="button" styleClass="newbutton" onclick="submit()" accesskey="s" disabled="<%=readonly%>">
					Save
	   			</html:button>
	   		</logic:equal>
	   		<logic:equal name="CashBookForm" property="readOnly" value="false">
				<html:button property="button" styleClass="newbutton" disabled="<%=readonly%>" onclick="javascript:cancelCashBookChanges()">
					Cancel
	   			</html:button>
	   		</logic:equal>
			</td>
		</tr>
	</table>
</html:form>
<script type="text/javascript">
<!--
	function copyCashBook()
	{
		var cashBookId = $FElement('cashBookId').value;
		document.location.href="CashBookAction.do?action=copyCashBook&cashBookId=" +cashBookId;
	}
	
	function editCashBook()
	{
		var cashBookId = $FElement('cashBookId').value;
		document.location.href="CashBookAction.do?action=editCashBook&cashBookId=" +cashBookId;
	}
	
	function cancelCashBookChanges()
	{
		var cashBookId = $FElement('cashBookId').value;
		
		if (cashBookId > 0)
		{
			document.location.href="CashBookAction.do?action=viewCashBook&cashBookId=" +cashBookId;
		}
		else
		{
			document.location.href="ListCashBooks.do";
		}
	}
//-->
</script>
<%@ include file="/jsp/include/posFooter.jsp" %>