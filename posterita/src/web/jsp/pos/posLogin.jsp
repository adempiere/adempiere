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



<!--posLogin.jsp-->

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<%@ page import="org.posterita.Constants" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>


<%
String appName = (String) request.getSession().getServletContext().getAttribute(Constants.APP_NAME);
%>

<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
<title>Welcome to <%=appName%></title>
<script src="js/enableButton.js"></script>				
<script src="js/pos.js"></script>		
<script src="javascripts/prototype.js"></script>
<script src="javascripts/scriptaculous.js"></script>
<script src="javascripts/sorttable.js"></script>
<link rel="shortcut icon" href="images/posterita.jpg" type="image/jpg" />
<link type="text/css" href="css/common.jsp" rel="stylesheet">
<link type="text/css" href="css/newPOS.jsp" rel="stylesheet">
</head>


<body>
<html:form action="/POSLoginAction" focus="username">
<html:hidden property="action" value="success"/>
<table width="100%" height="100%">
<tr><td  align="center" valign="middle">
	<table class="login" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td class="loginHeader" colspan="2">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td height="330px" bgcolor="#CCCCCC" valign="middle" align="center">					
				<table>
				<tr>
					<td colspan="2">
						<%@ include file="/jsp/include/errors.jsp" %>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<label><pos:element columnName="login.home.message1"/></label>
					</td>
				</tr>
				<tr>
					<td>
						<label><pos:element columnName="login.home.username"/></label>
					</td>
					<td>
						<html:text property="username" styleClass="text"/>
					</td>
				</tr>
				<tr>
					<td>
						<label><pos:element columnName="login.home.password"/></label>
					</td>
					<td>
						<html:password property="password" styleClass="text" redisplay="false"/>						
					</td>
				</tr>
				<tr>
					<td>						
						<html:link href="LoginForgot.do" styleClass="forgotpassword">
					  		<pos:element columnName="login.home.loginForgot"/>
					  	</html:link>						
					</td>
					<td>						
						<html:image property="btn" src="images/pos/buttons/button_login.gif" onclick="submit()"/>
					</td>
				</tr>
				</table>							
			</td>
			<td height="330px" bgcolor="#CCCCCC" valign="middle" align="center">
				<table>
				
				<tr>
					<td align="center">
						<label><pos:element columnName="login.home.message2"/></label>
					</td>
				</tr>
				<tr>
					<td>
						<div id="numericpad">
							<table class="numericpad">
								<tr>
									<td colspan="3">
										<html:password property="userPIN" styleClass="text" style="width:100%" redisplay="false"/>
									</td>									
								</tr>
								
								<tr>
									<td><div id="key7" name="number" class="key" value="7">7</div></td>
									<td><div id="key8" name="number" class="key" value="8">8</div></td>
									<td><div id="key9" name="number" class="key" value="9">9</div></td>
								</tr>
								
								<tr>
									<td><div id="key4" name="number" class="key" value="4">4</div></td>
									<td><div id="key5" name="number" class="key" value="5">5</div></td>
									<td><div id="key6" name="number" class="key" value="6">6</div></td>
								</tr>
								
								<tr>
									<td><div id="key1" name="number" class="key" value="1">1</div></td>
									<td><div id="key2" name="number" class="key" value="2">2</div></td>
									<td><div id="key3" name="number" class="key" value="3">3</div></td>
								</tr>
								
								<tr>
									<td><div id="keyC"  name="clear" class="key">C</div></td>
									<td><div id="keyZero"  name="number" class="key" value="0">0</div></td>
									<td><div id="keyOK" name="ok" class="key">OK</div></td>
								</tr>
							</table>
						</div>							
					</td>
				</tr>				
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<div class="copyright">All Contents ©</div>
			</td>
		</tr>		
	</table>
</td></tr>
</table>
</html:form>
</body>
</html>
<script>
	var pin = $FElement('userPIN');	
	
	function add()
	{
		pin.value = pin.value + this.getAttribute('value');
	}
	
	function clear()
	{
		pin.value = "";
	}
	
	function login()
	{
		document.forms[0].submit();
	}
	
	$('key9').onclick = add;
	$('key8').onclick = add;
	$('key7').onclick = add;
	
	$('key6').onclick = add;
	$('key5').onclick = add;
	$('key4').onclick = add;
	
	$('key3').onclick = add;
	$('key2').onclick = add;
	$('key1').onclick = add;
	
	$('keyZero').onclick = add;
	
	$('keyC').onclick = clear;
	$('keyOK').onclick = login;	
	
	
</script>