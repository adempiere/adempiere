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
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<%@page import="org.posterita.struts.pos.CashBookAction"%>
<logic:notPresent name="<%=WebUserInfo.NAME%>" scope="session">
	<jsp:forward page="/LoginHome.do"/>
</logic:notPresent>

<bean:define id="title"><pos:message textOnly="true" key="smenu.cashbooks"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>	   
<%@ include file="/jsp/include/errors.jsp" %>
<div align="right">
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
		
			<td align="left">
				<html:form action="SearchCashBookAction" focus="searchText">
					<html:hidden property="action" value="<%=CashBookAction.SEARCH_CASHBOOK %>"/>
					<html:text property="searchText" styleClass="text"/>
				
					<html:submit styleClass="tangoSearch tangoButton">&nbsp;</html:submit>
				</html:form>
			</td>
			
			<td align="right">	
				<html:link href="CashBookAction.do?action=initCreateCashBook">
					<img src="images/tango/document-new.png" title='<pos:message textOnly="true" key="add"/>' alt='<pos:message textOnly="true" key="add"/>' border="0">
				</html:link>
			</td>
		</tr>
	</table>
</div>

<logic:present name="<%=Constants.CASHBOOKS%>">
	<display:table id="row" name="sessionScope.cashbooks" class="displaytag" defaultsort="1" export="true" defaultorder="descending" pagesize="<%=pageSize.intValue() %>">
		<display:column property="cashBookName" titleKey="cashBookName" sortable="true"/>
		<display:column property="currency" titleKey="currency" sortable="true" style="text-align:center"/>
		<display:column property="isDefault" titleKey="default" style="text-align: center" sortable="true"/>
		<display:column media="html" style="text-align:center">
			<html:link href="CashReportAction.do?action=getCashDetailsHistory" paramName="row" paramId="cashBookId" paramProperty="cashBookId">
				Journals
			</html:link>
		</display:column>
		<display:column media="html" style="text-align:right">
			<html:link href="CashBookAction.do?action=editCashBook" paramName="row" paramId="cashBookId" paramProperty="cashBookId">
				<img src="images/tango/accessories-text-editor.png" title="Edit CashBook" alt="Edit" border="0">
			</html:link>

			<html:link href="CashBookAction.do?action=viewCashBook" paramName="row" paramId="cashBookId" paramProperty="cashBookId">
				<img src="images/tango/edit-find.png" title="View CashBook" alt="View" border="0">
			</html:link>
			
			<logic:equal name="row" property="isActive" value="true">
				<html:link href='<%="SearchCashBookAction.do?action=deactivateCashBook&cashBookId=" + ((CashBookBean)row).getCashBookId().intValue()%>' paramName="CashBookForm" paramId="searchText" paramProperty="searchText">
					<img src="images/tango/edit-redo.png" title="Deactivate CashBook" alt="Deactivate" border="0">
				</html:link>
			</logic:equal>
			
			<logic:notEqual name="row" property="isActive" value="true">
				<html:link href='<%="SearchCashBookAction.do?action=activateCashBook&cashBookId=" + ((CashBookBean)row).getCashBookId().intValue()%>' paramName="CashBookForm" paramId="searchText" paramProperty="searchText">
					<img src="images/tango/edit-undo.png" title="Activate CashBook" alt="Activate" border="0">
				</html:link>
			</logic:notEqual>
		</display:column>
	</display:table>
</logic:present>
<%@ include file="/jsp/include/posFooter.jsp" %>