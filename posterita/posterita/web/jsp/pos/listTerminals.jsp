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

<%@page import="org.posterita.struts.pos.TerminalAction"%>
<logic:notPresent name="<%=WebUserInfo.NAME%>" scope="session">
	<jsp:forward page="/LoginHome.do"/>
</logic:notPresent>

<bean:define id="title"><pos:message textOnly="true" key="smenu.terminals"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>	   
<%@ include file="/jsp/include/errors.jsp" %>
<div align="right">
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
		
			<td align="left">
				<html:form action="SearchTerminalAction" focus="searchText">
					<html:hidden property="action" value="<%=TerminalAction.SEARCH_TERMINAL %>"/>
					<html:text property="searchText" styleClass="text"/>
				
					<html:submit styleClass="tangoSearch tangoButton">&nbsp;</html:submit>
				</html:form>
			</td>
			
			<td align="right">	
				<html:link href="TerminalAction.do?action=initCreateTerminal">
					<img src="images/tango/document-new.png" title='<pos:message textOnly="true" key="add"/>' alt='<pos:message textOnly="true" key="add"/>' border="0">
				</html:link>
			</td>
			<td align="right" width="50px">
				<html:link href="SearchTerminalAction.do?action=resetCurrentTerminal" paramName="TerminalForm" paramId="searchText" paramProperty="searchText">
					<img src="images/tango/edit-clear.png" title='<pos:message textOnly="true" key="ResetCurrentPOSTerminal"/>' alt='<pos:message textOnly="true" key="ResetCurrentPOSTerminal"/>' border="0">
				</html:link>
			</td>
		</tr>
	</table>
</div>

<logic:present name="<%=Constants.TERMINALS%>">
	<display:table id="row" name="terminals" class="displaytag" defaultsort="1" export="true" 
		defaultorder="descending" pagesize="<%=pageSize.intValue() %>" requestURI="SearchTerminalAction.do">
		<display:column property="name" titleKey="name" sortable="true"/>
		<display:column property="orgName" titleKey="org" sortable="true"/>
		<display:column property="poPriceList" title="POPriceList"/>
		<display:column property="soPriceList" title="SOPriceList"/>
		<display:column property="locked" titleKey="locked" sortable="true"/>
		<display:column property="isCurrentTerminal" titleKey="CurrentTerminal" sortable="true" style="text-align:center"/>
		<display:column media="html" style="text-align:left;width:150px">
			<html:link href="TerminalAction.do?action=editTerminal" paramName="row" paramId="terminalId" paramProperty="terminalId">
				<img src="images/tango/accessories-text-editor.png" title="Edit Terminal" alt="Edit" border="0">
			</html:link>

			<html:link href="TerminalAction.do?action=viewTerminal" paramName="row" paramId="terminalId" paramProperty="terminalId">
				<img src="images/tango/edit-find.png" title="View Terminal" alt="View" border="0">
			</html:link>
			
			<%
				String activateDisplay = "none";
				String deactivateDispay = "none";
				if (((TerminalBean)row).getIsActive().booleanValue()) 
				{
					deactivateDispay = "inline";
				} 
				else 
				{
					activateDisplay = "inline";
				}
			%>

			<logic:equal name="row" property="isActive" value="true">
				<html:link href='<%="SearchTerminalAction.do?action=deactivateTerminal&terminalId=" + ((TerminalBean)row).getTerminalId().intValue()%>' paramName="TerminalForm" paramId="searchText" paramProperty="searchText">
					<img src="images/tango/edit-redo.png" title="Deactivate Terminal" alt="Deactivate" border="0">
				</html:link>
			</logic:equal>
			
			<logic:notEqual name="row" property="isActive" value="true">
				<html:link href='<%="SearchTerminalAction.do?action=activateTerminal&terminalId=" + ((TerminalBean)row).getTerminalId().intValue()%>' paramName="TerminalForm" paramId="searchText" paramProperty="searchText">
					<img src="images/tango/edit-undo.png" title="Activate Terminal" alt="Activate" border="0">
				</html:link>
			</logic:notEqual>
			<logic:notEqual name="row" property="isCurrentTerminal" value="true">
				<html:link href='<%="SearchTerminalAction.do?action=updateCurrentTerminal&terminalId=" + ((TerminalBean)row).getTerminalId().intValue()%>' paramName="TerminalForm" paramId="searchText" paramProperty="searchText">
					<img src="images/tango/go-jump.png" title='<pos:message textOnly="true" key="UpdateCurrentPOSTerminal"/>' alt='<pos:message textOnly="true" key="UpdateCurrentPOSTerminal"/>' border="0">
				</html:link>
			</logic:notEqual>
		</display:column>
	</display:table>
</logic:present>
<%@ include file="/jsp/include/posFooter.jsp" %>