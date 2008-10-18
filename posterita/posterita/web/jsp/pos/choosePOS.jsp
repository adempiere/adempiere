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

<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.Constants" %>

<%@ page import="org.posterita.struts.login.POSLoginAction" %>
<%@ page import="org.posterita.beans.ProductBean" %>
<%@ page import="org.posterita.user.WebUserInfo" %>
<%@ page import="org.posterita.lib.UdiConstants" %>


<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

<%
String appName = (String) request.getSession().getAttribute(Constants.APP_NAME);
%>
<!-- choosePos.jsp -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
<title><%=appName%></title>
<link rel="shortcut icon" href="images/posterita.jpg" type="image/jpg" />
<link type="text/css" href="css/common.jsp" rel="stylesheet">
<link type="text/css" href="css/newPOS.jsp" rel="stylesheet">
</head>
<body>
<html:form action="/ChoosePOSAction">
<html:hidden property="action" value="<%=POSLoginAction.CHOOSEPOS%>"/>
	<table class="login" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td class="loginHeader" colspan="2">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td height="330px" bgcolor="#CCCCCC" valign="middle" align="center">
				<form>
				<table>
				<tr>
					<td colspan="2" align="center">
						<label><pos:message key="choose.your.till"/></label>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td>
						<label>Till No:</label>
					</td>
					<td>
						<html:select property="posId" onchange="Submit()">
							<html:options collection="<%= Constants.POSTERMINALS %>" property="terminalId" labelProperty="name"/>																																	
						</html:select>
					</td>
				</tr>				
				<tr>
					<td>
						&nbsp;
					</td>
					<td>
						<input type="image" src="images/pos/buttons/button_continue.gif">
					</td>
				</tr>
				</table>
				</form>
			</td>
		</tr>
		<tr>
			<td>
				<div class="copyright"><%=appName%></div>
			</td>
		</tr>		
	</table>
</html:form>
</body>
</html>