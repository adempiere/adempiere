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



<%@ page import="org.posterita.Constants" %>
<%@ page import="org.compiere.model.MPOS" %>
<%@ page import="java.util.Properties" %>
<%@ page import="org.posterita.core.TmkJSPEnv" %>
<%@ page import="org.compiere.util.Env" %>
<%@ page import="org.posterita.lib.UdiConstants" %>
<%@ page import="org.posterita.user.WebUserInfo" %>

<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>	
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<logic:notPresent name="<%=WebUserInfo.NAME%>" scope="session">
	<jsp:forward page="/POSLogin.do"/>
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
%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
	<base href="<%=basePath%>">
	<title>Tamak POS</title>				
	<script src="js/enableButton.js"></script>				
	<script src="js/pos.js"></script>		
	<script src="javascripts/prototype.js"></script>
 	<script src="javascripts/scriptaculous.js"></script>
 	<script src="javascripts/sorttable.js"></script>
	<link type="text/css" href="css/newPOS.css" rel="stylesheet">	
</head>
<body>
<center>
<div class="main" align="center">
	<!-- start of header layout -->
	<div class="header">
		<table width="100%" class="layout">
			<tr>
				<td align="left">
					<img src="images/pos/small logo.gif"/>
				</td>
				<td align="right" valign="top">
					<font class="redcolor">
						User:
					</font>
					<font>
						<c:out value ='${webUserInfo.user.name}'/>
					</font>
					<font class="redcolor">
						Till No:
					</font>
					<font>
						<%=posName%>
					</font>
				</td>
			</tr>
		</table>
	</div>
	<!-- end of header layout -->
	
	<!-- start of menu layout -->
	<div class="menu">
		<table width="100%" class="topmenu">
			<tr>
				<td>
					<font class="title">
						Header
					</font>
				</td>
				<td align="right">
					<!-- start top menus -->
					<logic:iterate indexId="count" id="element" name="<%= Constants.TOP_MENUS%>" type="org.posterita.core.MenuItem">
					<a class="topmenu" href="<%= element.getMenuLink()%>">											
						<img src="<%= element.getImageLink()%>" alt="<%= element.getName() %>">											
					</a>							
					</logic:iterate>								
					<!-- end top menus -->
				</td>
			</tr>
		</table>
	</div>
	<!-- end of menu layout -->
	
	<!-- start of content layout -->
	<div class="content">
		<table width="100%" class="layout">
			<tr>
				<td>
					<!-- start of page content -->
					<!-- end of page content -->						
				</td>				
			</tr>
		</table>
	</div>
	<!-- end of content layout -->
	
	<!-- start of copyright layout -->
	<div class="copyright">All content &copy;2006 Tamak ICT</div>
	<!-- end of copyright layout -->
</div>
</center>
</body>
</html>
