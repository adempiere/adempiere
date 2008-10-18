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
 * @author Servansingh
--%>


<%String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; %>

<%@ page import="org.posterita.Constants" %>

<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>	
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
		<title>POSterita:. User Registration</title>				
		<link type="text/css" href="css/newPOS.jsp" rel="stylesheet">	
		<link type="text/css" href="css/common.jsp" rel="stylesheet">
		<link rel="shortcut icon" href="images/posterita.jpg" type="image/jpg" /> 
		<base href="<%=basePath%>">
	</head>
	
<body>
		<div align="center">
		<table width="70%" >
			<tr>
				<td>
					<img src="images/logo.jpg" >
				</td>
			</tr>
			<tr>
				<td>
					<br>
					<font class="title">User Registration Form</font>
				</td>
			</tr>
		</table>
		
		<TABLE border="0" width="70%" >
		<tr>
			<td>
				<html:form action= "/RegisterUserAction" method="post" enctype="multipart/form-data">
				<html:hidden property="action" value="registerUser"/>
				<%@ include file="/jsp/include/errors.jsp" %>
					
				<br>
				<br>
				
					<TR>
						<TD>
							<fieldset>
								<legend><font class="title">User Details</font></legend>
							<table width="100%" border="0" class="display" cellspacing="15">
								<tr>
									<td>First Name</td>
									<td><html:text property="name"/></td>
								</tr>
								<tr>
									<td>Last Name</td>
									<td><html:text property="userSurname" /></td>
								</tr>
								<tr>
									<td>Address</td>
									<td><html:text property="address1"/></td>
								</tr>
								<tr>
									<td>City</td>
									<td><html:text property="city" /></td>
								</tr>
								<tr>
									<td>Country</td>
									<td>
										<html:select property="countryId" value="100" style="width:168px">
										<html:options collection="<%=Constants.COUNTRIES%>" property="key" labelProperty="name"/>																						
										</html:select>
									</td>
								</tr>
								<tr>
									<td>Company</td>
									<td><html:text property="company"/></td>
								</tr>
								<tr>
									<td>Industry</td>
									<td><html:text property="industry"/></td>
								</tr>
								<tr>
									<td>Email</td>
									<td><html:text property="email"/></td>
								</tr>
								<tr>
									<td>Remarks</td>
									<td><html:textarea property="comments" style="height: 200px; width: 500px; "/></td>
								</tr>
								<tr>
									<td align="right">
										<html:button property="button"  onclick="get('ChooseApp.do')" tabindex="10" accesskey="c" value="Cancel">
										
								        </html:button>
									</td>
									<td>
										<html:button property="button" onclick="newsubmit(this)" tabindex="10" accesskey="r" value="Register">
										
								        </html:button>
									</td>
								</tr>
								</table>
							</fieldset>
						</TD>
					</TR>
					</html:form>
				</td>
			</tr>
		</TABLE>
		</div>
<SCRIPT>

function newsubmit(button)
{	
	form=document.forms[0];
	button.disabled=true;
	form.submit();
}

function get(URL)
{
	window.location = URL;
}

</SCRIPT>
		
		
	</body>
</html>
