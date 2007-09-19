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
<!-- posHeader.jsp -->
<%@ page import="org.posterita.Constants" %>
<%@ page import="org.compiere.model.MPOS" %>
<%@ page import="java.util.Properties" %>
<%@ page import="org.posterita.core.TmkJSPEnv" %>
<%@ page import="org.compiere.util.Env" %>
<%@ page import="org.posterita.lib.UdiConstants" %>
<%@ page import="org.posterita.user.WebUserInfo" %>
<%@ page import="org.posterita.lib.UdiConstants" %>

<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>	
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

<logic:notPresent name="<%=WebUserInfo.NAME%>" scope="session">
	<jsp:forward page="/POSLogin.do"/>
</logic:notPresent>

<bean:parameter id="offset" name="offset" value="0"/>
<bean:parameter id="length" name="length" value="50"/>

<logic:present parameter="length">
	<bean:parameter id="length" name="length"/>
</logic:present>

<logic:notPresent parameter="length">
	<logic:present cookie="preference.display">
		<bean:cookie id="cookie" name="preference.display"/>
		<bean:parameter id="length" name="length" value="<%=cookie.getValue()%>"/>
	</logic:present>
	<logic:notPresent cookie="preference.display">
		<bean:parameter id="length" name="length" value="50"/>
	</logic:notPresent>
</logic:notPresent>
<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Cache-Control","no-store"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server

String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Properties ctx = TmkJSPEnv.getCtx(request);
String posName = MPOS.get(ctx,Env.getContextAsInt(ctx,UdiConstants.POS_ID)).getName();
Integer userId = new Integer(Env.getAD_User_ID(ctx));
String version = UdiConstants.POS_VERSION;
String appName = (String) request.getSession().getServletContext().getAttribute(Constants.APP_NAME);

%>

<%--
<logic:notPresent name="<%=WebUserInfo.NAME%>" scope="session">
	<jsp:forward page="/LoginHome.do"/>
</logic:notPresent>
--%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<base href="<%=basePath%>">
	<title><%=appName%></title>				
	<script src="js/enableButton.js"></script>				
	<script src="js/pos.js"></script>		
	<script src="javascripts/prototype.js"></script>
 	<script src="javascripts/scriptaculous.js"></script>
 	<script src="javascripts/sorttable.js"></script>
 	<script src="js/tooltip.js"></script>
 	<script src="js/dom-drag.js"></script>
 	<script src="js/keyboard.js"></script>
 	<link rel="shortcut icon" href="images/posterita.jpg" type="image/jpg" /> 
 	<link type="text/css" href="css/common.jsp" rel="stylesheet">
 	<link type="text/css" href="css/newPOS.jsp" rel="stylesheet">  	
</head>
<body>
<div id="keyboard"></div>
<center>
<div class="main" align="center">
	<!-- start of header layout -->
	<div class="header">
		<table width="100%" class="layout">
			<tr>
				<td class="headerLogo">
					
				</td>
				<td align="right" valign="top">
					<html:link href="<%="POSUserAction.do?action=initEditUser&userId="+userId%>">
							<font><c:out value ='${webUserInfo.user.name}'/></font>
					</html:link>	
					<font>
						<%=posName%>
					</font>
					<div id="timer" style="border: 0px #E83530 solid;background-color: #ffffff; color: #666666; font-size: 13px; font-weight: bold;"></div>
					<script type="text/javascript" src="js/timer.js"></script>
				</td>
				<td align="right">	
					<html:link href="<%="POSLogoutAction.do?action=logout"%>">
							<image src="images/tango/system-log-out.png" border="0"/>
					</html:link>	
				</td>	
		</tr>
			
		</table>
	</div>
	<!-- end of header layout -->
	
	<!-- start of menu layout -->
	<div class="menu">
		<table width="100%" class="topmenu">
			<tr>
				<td align="right">
									
					<!-- start top menus -->
					<logic:iterate indexId="count" id="element" name="<%= Constants.TOP_MENUS%>" type="org.posterita.core.MenuItem">
					<a class="topmenu" href="<%= element.getMenuLink()%>" class="nodecoration">	
						<span class="menu"><pos:element columnName="<%= element.getName() %>" textOnly="true"/></span>	
					</a>
					&nbsp;|&nbsp;
					</logic:iterate>												
					<!-- end top menus -->					
				</td>
				<td align="center">
					<img src="images/tango/input-keyboard.png" id="keyboardicon">
				</td>
			</tr>
		</table>
		<script>
			function openCashDrawer()
			{
				var url = '<%=basePath + "CompletePOSOrderAction.do?action=openCashDrawer"%>';
				document.applets[0].printURL(url);
			}					
		</script>
	</div>

	<!-- end of menu layout -->
	
	<!-- start of content layout -->
	<div class="content">		
		<table width="100%" class="layout">
		<%--
			<tr>
				<td width="100%" align="left">
					<logic:present name="<%=Constants.BREADCRUMB%>">
					<div>	
						<ul>
						<logic:iterate id="element" indexId="count" name="<%= Constants.BREADCRUMB%>" type="org.posterita.core.MenuItem">
						<li class="breadcrumb">		
						<a href="<%=element.getMenuLink()%>">
						<pos:element columnName="<%=element.getName()%>" textOnly="true"/>		
						</a>	
						</li>
						</logic:iterate>
						</ul>			
					</div>
					</logic:present>
				</td>
			</tr>
		--%>
			<tr>
				<td>
					<!-- start of page content -->

					<font class="pagetitle">
						<logic:present name="title">
							<bean:write name="title"/>
						</logic:present>
					</font>	