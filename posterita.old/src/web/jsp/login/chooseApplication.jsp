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
 * @author Ashley
--%>
<!-- chooseApplication.jsp -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<%@ page import="org.posterita.Constants" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
	<head>
		<base href="<%=basePath%>">
		<meta http-equiv="content-type" content="text/html;charset=iso-8859-1">
		<title>Welcome to POSterita</title>
		<script src="js/enableButton.js"></script>	
		<link rel="shortcut icon" href="images/posterita.jpg" type="image/jpg" /> 	
		<link type="text/css" href="css/common.jsp" rel="stylesheet">
		<link type="text/css" href="css/newPOS.jsp" rel="stylesheet">
	</head>

<body>
<div id="keyboard"></div>
<center>
<table cellspacing="0">
<tr>
<!--
<td style="width:150px;border:solid 1px #000000">
	&nbsp;
</td>
-->

<td>
<div class="main" align="center">
	<!-- start of header layout -->
	<div class="header">
		<table width="100%" class="layout">
			<tr>
				<td class="headerLogo" align="right" valign="bottom">					
					<font class="redcolor">
					</font>
				</td>
				
			</tr>
			
		</table>
	</div>
	<div class="menu">
		<table width="100%" class="topmenu">
			<tr>
				<td>
					<font class="title">
						Posterita POS Applications
					</font>
				</td>
				
			</tr>
		</table>
	</div>
	
	
	<div class="content">		
		<table width="100%" class="layout">
			<tr>
				<td>
	
	
		<table width="778" border="0" cellspacing="0" cellpadding="0" align="center" valign="center">
			<tr>
				<td>
					<%@ include file="/jsp/include/errors.jsp" %> 
				</td>
			</tr>
			
			<tr>
				<td class="main">
				<table align="center" width="100%">
				<tr>
				<td>
					<Fieldset>
					<legend><font class="title">POS</font></legend>
					<table width="100%" border="0" class="display">
						<logic:present name="<%=Constants.WEB_APPLICATIONS%>">
							<logic:iterate id="store" name="<%=Constants.WEB_APPLICATIONS%>" indexId="count">
								<tr>
									<td class="submenu">
									<li class="submenu">
										<html:link href="SetApplicationParametersAction.do?action=setApplicationParameters" paramName="store" paramId="storeId" paramProperty="storeId" styleClass="submenu">
											<bean:write name="store" property="applicationName"/>
										</html:link>
									</li>
									</td>
								</tr>
							</logic:iterate>
						</logic:present>
					</table>
					</Fieldset>
				</td>
				</tr>
				</table>
				</td>
			</tr>
		</table>
		
		
	</body>
</html>
