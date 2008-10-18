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



<!--posPassword.jsp-->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="org.posterita.Constants" %>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
<title><pos:message textOnly="true" key="posPassowrd.welcome"/></title>
<link type="text/css" href="css/newPOS.jsp" rel="stylesheet">
<link type="text/css" href="css/common.jsp" rel="stylesheet">
<link rel="shortcut icon" href="images/posterita.jpg" type="image/jpg" /> 
</head>
<body>

<table width="100%" height="100%">
<tr><td  align="center" valign="middle">
	<table class="login" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td class="loginHeader">
				&nbsp;
			</td>
		</tr>
				
		<tr>
		 		<td height="330px" bgcolor="#CCCCCC" valign="middle" align="center">
					<table class="loginbox" width="300" align="center" cellpadding="5"
						cellspacing="0" cols="1">
						<tr>
							<td class="contentred" colspan="2">
							<pos:message key="login.password.passwordSent"/></td>
						</tr>
						<tr class="white">
							<td align="center"><html:link href="POSLogin.do"
								styleClass="left">
								<html:img src="images/tango/go-previous.png"
									border="0" align="absmiddle" vspace="5" />
								<pos:message key="login.password.backToLogin"/>
							</html:link><br>
							</td>
						</tr>
					</table>								
				</td>
		</tr>				
		<tr>	
			<td>
				<div class="copyright"><pos:message key="login.home.all.contents"/>  &copy;2006 Tamak ICT</div>
			</td>
		</tr>		
	</table>
</body>
</html>