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



<%@ page import="org.posterita.Constants" %>

<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>	
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
		<title>POSterita:. Create Client</title>				
		<link type="text/css" href="css/newPOS.jsp" rel="stylesheet">	
		<link type="text/css" href="css/common.jsp" rel="stylesheet">
		<link rel="shortcut icon" href="images/posterita.jpg" type="image/jpg" /> 
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
			<tr>
				<td align="left">
					<img src="images/logo.jpg" >
				</td>
			</tr>
		</table>
	</div>
	<div class="menu">
		<table width="100%" class="topmenu">
			<tr>
				<td>
					<font class="title">
						Create Client
					</font>
				</td>
				
			</tr>
		</table>
	</div>
	
	
	<div class="content">		
		<table width="100%" class="layout">
			<tr>
				<td>
		
		<html:form action="/ClientAction" method="post" enctype="multipart/form-data">
		<html:hidden property="action" value="createClient"/>
		<%@ include file="/jsp/include/errors.jsp" %>
		<table>
		<TR>
		<td valign="top">
			<fieldset>
			<legend><font class="title">Client</font></legend>
			<table width="100%" border="0" class="display">
			<tr>
				<td>Client Name</td>
				<td><html:text property="clientName"/></td>
			</tr>
			<tr>
				<td>Organisation Name</td>
				<td><html:text property="orgName"/></td>
			</tr>
			<tr>
				<td>Currency</td>
				<td>
					<html:select property="currencyId" value="100" style="width:190px">
						<html:options collection="<%=Constants.CURRENCIES%>" property="key" labelProperty="name"/>																						
					</html:select>
				</td>
			</tr>
			<tr>
				<td>Address</td>
				<td><html:text property="address1"/></td>
			</tr>
			<tr>
				<td>Postal Address</td>
				<td><html:text property="postalAddress"/></td>
			</tr>
			<tr>
				<td>City</td>
				<td><html:text property="city"/></td>
			</tr>
			<tr>
				<td>Country</td>
				<td>
					<html:select property="countryId" value="100" style="width:190px">
						<html:options collection="<%=Constants.COUNTRIES%>" property="key" labelProperty="name"/>																						
					</html:select>
				</td>
			</tr>
			</table>
			</fieldset>
			</td>
			
			<td valign="top">			
			<fieldset>
			<legend><font class="title">Administrator</font></legend>
			<table width="100%" border="0" class="display">
			<tr>
				<td>User Name</td>
				<td><html:text property="username"/></td>
			</tr>
			<tr>
				<td>Role Name</td>
				<td><html:text property="roleName"/></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><html:password property="password"/></td>
			</tr>
			<tr>
				<td>Confirm Password</td>
				<td><html:password property="confirmPassword"/></td>
			</tr>
			<tr>
				<td>User PIN</td>
				<td><html:password property="userPIN"/></td>
			</tr>
			<tr>
				<td>Email</td>
				<td><html:text property="email"/></td>
			</tr>
			
			</table>
			</fieldset>
		</td>
		</TR>
		<tr>
		<td>
			<fieldset>
			<legend><font class="title">Accounting</font></legend>
			<table width="100%" border="0" class="display">
			<tr>
				<td>Load Accounting Values</td>
				<td><html:file value="" property="file"/></td>
			</tr>
			</table>
		</fieldset>	
		</td>
		<td align="right">
			<html:button property="button" styleClass="save smallbutton" onclick="newsubmit(this)" tabindex="10" accesskey="s">
			&nbsp;
	        </html:button>
		</td>
		</tr>
		</table>		
		</html:form>
		
<SCRIPT>
function newsubmit(button)
{	
	form=document.forms[0];
	button.disabled=true;
	form.submit();
}




</SCRIPT>
		
		</center>
	</body>
</html>
