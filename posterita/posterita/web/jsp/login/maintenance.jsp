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

<!--
	<map name="map">
		<area shape="rect" coords="335,180,430,200" href="javascript:fullScreen();" />
	</map>
-->
	<body>
		<div align="center" id="content">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
				<tr>
					<td>
						<div align="center" id="content">
							<table width="778" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td class="main">

										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr height="65">
												<td width="10" height="65" class="iconmenuLeft"></td>
												<td height="65" class="iconmenuBg" valign="top">
													<div align="center">
														<img src="images/logo.gif" alt="" border="0"></div>
												</td>
												<td width="10" height="65" class="iconmenuRight"></td>
											</tr>

										</table>
										<table width="100%" cellpadding=0 cellspacing=0 border=0>
										<tr>
											<td valign="top" width="286"><img src="images/Maint_Image.jpg">
											</td>
											<td align="center" valign="middle"> This Server is Down for Maintenance <br>
											                      We will be back shortly <br>
											                      Thank You!
											</td>
										</tr>
										</table>
										<br>
									</td>
								</tr>
							</table>
							
							<p class="disclaimer">All Content &copy; 2007 Posterita Ltd</p>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>