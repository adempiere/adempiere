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
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
<title><title><pos:message textOnly="true" key="posPassowrd.welcome"/></title></title>
<script src="js/enableButton.js"></script>				
<script src="js/pos.js"></script>		
<script src="javascripts/prototype.js"></script>
<script src="javascripts/scriptaculous.js"></script>
<script src="javascripts/sorttable.js"></script>
<link type="text/css" href="css/newPOS.jsp" rel="stylesheet">
</head>
<body>
<html:form action="/POSLoginAction" focus="username">
<html:hidden property="action" value="success"/>
<table width="100%" height="100%">
<tr><td  align="center" valign="middle">
	<table class="login" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td class="loginHeader">
				<img src="images/pos/POS-LOGIN_01.gif">
			</td>
		</tr>
		<tr>
			<td height="330px" bgcolor="#CCCCCC" valign="middle" align="center">					
				<table>
				<tr>
					<td>
						<%@ include file="/jsp/include/errors.jsp" %>
					</td>
				</tr>
				<tr>
					<td align="center">
						<label><pos:message key="login.home.message2"/></label>
					</td>
				</tr>
				<tr>
					<td>
						<div id="numericpad">
							<table class="numericpad">
								<tr>
									<td colspan="3">
										<input type="password" id="pin" class="text">
									</td>									
								</tr>
								
								<tr>
									<td><div name="number" class="key">7</div></td>
									<td><div name="number" class="key">8</div></td>
									<td><div name="number" class="key">9</div></td>
								</tr>
								
								<tr>
									<td><div name="number" class="key">4</div></td>
									<td><div name="number" class="key">5</div></td>
									<td><div name="number" class="key">6</div></td>
								</tr>
								
								<tr>
									<td><div name="number" class="key">1</div></td>
									<td><div name="number" class="key">2</div></td>
									<td><div name="number" class="key">3</div></td>
								</tr>
								
								<tr>
									<td><div name="clear" class="key">C</div></td>
									<td><div name="number" class="key">0</div></td>
									<td><div name="ok" class="key">OK</div></td>
								</tr>
							</table>
						</div>							
					</td>
				</tr>				
				</table>							
			</td>
		</tr>
		<tr>
			<td>
				<div class="copyright"><pos:message key="login.home.all.contents"/>   &copy;2006 Tamak ICT</div>
			</td>
		</tr>		
	</table>
</td></tr>
</table>
</html:form>
</body>
</html>
<script>
	var pin = $('pin');
	
	var keys = document.getElementsByName('number');
	for(var i=0; i<keys.length; i++)
	{
		var element = keys[i];
		
		element.onclick = function(e)
		{
			pin.value = pin.value + element.innerHTML;
		};
		
	}
	
	var clear = $FElement('clear');
	var ok = $FElement('ok');
	
	clear.onclick = function(e)
	{
		pin.value = "";
	};
	
</script>