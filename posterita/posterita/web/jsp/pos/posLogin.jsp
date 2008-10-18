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
String appName = (String) request.getSession().getAttribute(Constants.APP_NAME);

if(appName == null)
{
	appName = "POSterita";
}

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
<link type="text/css" href="css/mainMenu.css" rel="stylesheet">
</head>


<body id="body">
	<html:form action="/POSLoginAction" focus="username">
	<html:hidden property="action" value="success"/>
	<table width="100%" cellspacing="0px" cellpadding="0px" border="0px">
		<tr>
			<td colspan="2">
				<div id="posHeader">
					<div id="logo">
						<img src="images/newUI/logo.gif" alt="Powered by Posterita POS" width="133px" height="41px" border="0px"/>
					</div>
					<div id="mainTitle">POS Login</div>
				</div>
			</td>
		</tr>
		<tr>
			<td width="50%">
				<div id="loginContainer">
					<div align="center">
						<div id="loginContent">
							<table>
								<tr>
									<td colspan="2">
										<div class="divsystemerror">
											<div class="systemerror"></div>
											<div class="systemMsg">
												<%@ include file="/jsp/include/errors.jsp"%>
											</div>
										</div>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<label class="upperCase">
											<pos:message key="login.home.message1"/>
										</label>
									</td>
								</tr>
								<tr>
									<td width="25%">
										<label class="upperCase">
											<pos:message key="login.home.username"/>
										</label>
									</td>
									<td>
										<html:text property="username" styleClass="input" styleId="username"/>
									</td>
								</tr>
								<tr>
									<td width="25%">
										<label class="upperCase">
											<pos:message key="login.home.password"/>
										</label>
									</td>
									<td>
										<html:password property="password" styleClass="input" redisplay="false" styleId="password"/>
									</td>
								</tr>
								<tr>
									<td width="25%"></td>
									<td>
										<button class="butnLogin" type="submit">login</button>
									</td>
								</tr>
								<tr>
									<td width="25%"></td>
									<td>
										<html:link href="LoginForgot.do">
					  						<pos:message key="login.home.loginForgot"/>
					  					</html:link>
									</td>
								</tr>
							</table>
						</div>
					</div>	
				</div>
			</td>
			<td width="50%">
				<div id="pinLoginContainer">
					<div id="loginPinContent">
						<div align="center">
							<table border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td>
										<div id="wrongPIN" class="divsystemerror" style="display:none">
											<div class="systemerror"></div>
											<div class="systemMsg">
												<%@ include file="/jsp/include/errors.jsp"%>
											</div>
										</div>
									</td>
								</tr>
								<tr>
									<td>
										<label class="upperCase">
											<pos:message key="login.home.message2"/>
										</label>
									</td>
								</tr>
								<tr>
									<td>
										<html:password property="userPIN" redisplay="false" styleId="pin"/>
									</td>
								</tr>
								<tr>
									<td>
										<div class="numPad">
											<div class="systemKey">
												<div id="key7" value="7">
													7
												</div>
											</div>
											<div class="systemKey">
												<div id="key8" value="8">
													8
												</div>
											</div>
											<div class="systemKey">
												<div id="key9" value="9">
													9
												</div>
											</div>
											<div class="systemKey">
												<div id="key4" value="4">
													4
												</div>
											</div>
											<div class="systemKey">
												<div id="key5" value="5">
													5
												</div>
											</div>
											<div class="systemKey">
												<div id="key6" value="6">
													6
												</div>
											</div>
											<div class="systemKey">
												<div id="key1" value="1">
													1
												</div>
											</div>
											<div class="systemKey">
												<div id="key2" value="2">
													2
												</div>
											</div>
											<div class="systemKey" value="3">
												<div id="key3">
													3
												</div>
											</div>
											<div class="systemKey">
												<div id="keyC">
													C
												</div>
											</div>
											<div class="systemKey">
												<div id="keyZero" value="0">
													0
												</div>
											</div>
											<div class="systemKey">
												<div id="keyOK">
													OK
												</div>
											</div>
										</div>
									</td>
								</tr>
							</table>
						</div>
					</div>	
				</div>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<div id="footerContainer">
					<div class="footer">
						<div class="floatLeft">
							<pos:message textOnly="true" key="footer.copyright"/>
							Posterita <%=java.util.Calendar.getInstance().get(java.util.Calendar.YEAR)%>
							<b><pos:message key="pos.version" textOnly="true"/>&nbsp;1.7.3</b>
						</div>
					</div>
		 		</div>
			</td>
		</tr>
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
	
	/*var username = $('username');
	var password = $('password');
	var pin = $('pin');
			
	if(username.value != null && username.value.length > 0 && password.value.length == 0)
	{
		$('wrongUsername').style.display = 'block';
	}
	
	if(pin.value != null && pin.length > 0)
	{
		$('wrongPIN').style.display = 'block';
	}*/

</script>