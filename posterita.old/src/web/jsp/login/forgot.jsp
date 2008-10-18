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

<!--forgot.jsp-->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="org.posterita.Constants" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
<title>Welcome to Posterita</title>
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
							<table width="100%" border="0" cellpadding="5" cellspacing="0">
								<tr>
									<td valign="top">
										<table width="400" align="center" cellpadding="5" cellspacing="0">
											<tr>
												<td valig2007 Posterita Ltdn="top">
													<div class="nb">
														<logic:messagesPresent>
															<ul>
																<html:messages id="error">
																	<li><bean:write name="error"/></li>
																</html:messages>
															</ul>
														</logic:messagesPresent>
												    </div>
												</td>
											</tr>
										</table>
										<html:form action="/LoginActionForgotPOS">
											<html:hidden property="action" value="<%=Constants.PASSWORD%>"/>
												<table class="loginbox" width="420" align="center" cellpadding="5" cellspacing="0" cols="2">
											 		<tr>
													  <td colspan="2"><br><bean:message key="login.forgot.forgotPassword"/><br><br></td>
													</tr>
													<tr>
														<td colspan="1" align="right" width="171"><bean:message key="login.forgot.username"/></td>
														<td colspan="1" align="left"><html:text property="username"/></td>
													</tr>
													
													<tr>
													<td align="center">&nbsp;</td>
														<td>						
															<html:image property="btn" src="images/pos/buttons/button_submit.gif" onclick="submit()"/>
														</td>
													 </tr>
												</table>
									    </html:form>
    								</td>
								</tr>
			
							</table>
						</td>
					</tr>
				
			<td>
				<div class="copyright">All content &copy;2007 Posterita Ltd</div>
			</td>
		
	</table>
</table>

</body>
</html>