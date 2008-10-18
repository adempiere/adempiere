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
 * @author tamak
--%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>

	<head>
		<title><tiles:getAsString name="title" /></title>		
		<meta http-equiv="content-type" content="text/html; charset=ISO-8859-1">
		<!-- <link rel="stylesheet" href="css/style.css" type="text/css"> -->
		<!-- <link rel="stylesheet" href="css/global.css" type="text/css"> -->
		<bean:write name="cssURI" filter="false"/>
		<script src="js/enableButton.js"></script>	
		<base href="<%=basePath%>">		
	</head>

	<body>
	
	<table align="center" width="778" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td class="main" width="100%">	
	
				<table width="100%" height="70" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td>
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr height="65">
									<td width="10" height="65" class="iconmenuLeft">
									</td>
									
									<td width="277" height="65" class="iconmenuBg">
									</td>
									
									<td height="65" width="191" class="iconmenuBg">
									<img src="images/pos/logo_red.jpg">	
									</td>
									
									<td width="277" height="65" class="iconmenuBg">
									</td>
									
									
									<td width="10" height="65" class="iconmenuRight">
									</td>
								</tr>
							</table>			
						</td>
					</tr>
				
					
					<tr>
			
					 	<td colspan="1" valign="top">
					  		
					  		<table width="100%" border="0" cellpadding="5" cellspacing="0">
			
						   		<tr>
						    		
						    		<td valign="top">
							 			<table width="100%" border="0" cellpadding="0" cellspacing="0">
							  				<tr>
							  				
										   		<td width="15" height="15" colspan="1">&nbsp; </td>
										   		<td height="15" colspan="1">&nbsp; </td>
										   		<td height="15" width="17" colspan="1">&nbsp; </td>
							  				
							  				</tr>
							  				
										  	<tr>
										   		<td width="15">&nbsp; </td>
										
