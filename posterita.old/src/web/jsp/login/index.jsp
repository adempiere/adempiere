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
 * @author Vishee
--%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>


<html>

	<head>
		<meta http-equiv="content-type" content="text/html;charset=iso-8859-1">
		<title><bean:message key="login.home.title"/></title>
		<link rel="stylesheet" href="css/global.css" type="text/css">
		<bean:write name="cssURI" filter="false"/>
		<%@ include file="/js/enableButton.js" %>
		<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" /> 	
	</head>

	<body>		
		<table width="778" border="0" cellspacing="0" cellpadding="0" align="center" valign="center">
			<tr>
				<td height="120px">
					
				</td>
			</tr>
			<tr>
				<td class="main">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr height="65">
							<td width="10" height="65" class="iconmenuLeft">
							</td>
							
							<td width="277" height="65" class="iconmenuBg">
							</td>
							
							<td height="65" width="191" class="logo">
								
							</td>
							
							<td width="277" height="65" class="iconmenuBg">
							</td>
							
							
							<td width="10" height="65" class="iconmenuRight">
							</td>
						</tr>
					</table>
					
					<!-- <a href="javascript:fullScreen();" target="_self"> -->
					<div id="dcsLogo" onclick="fullScreen()" style="cursor:pointer">
					<table cellspacing="0" cellpadding="0">
						<tr>
							<td class="dcs" height="216px" width="778">
								
							</td>
						</tr>
					</table>
					</div>
					<!-- </a> -->
				</td>
			</tr>
			
			<tr>
				<td class="disclaimer">
					All Content &copy; 2007 Posterita Ltd
				</td>
			</tr>
		</table>
		
		
	</body>
</html>