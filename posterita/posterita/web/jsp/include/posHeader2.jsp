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
<!-- posHeader.jsp -->
<%@ page import="org.posterita.Constants" %>
<%@ page import="org.compiere.model.MOrg" %>
<%@ page import="org.compiere.model.MRole" %>
<%@ page import="java.util.Properties" %>
<%@ page import="org.posterita.core.TmkJSPEnv" %>
<%@ page import="org.compiere.util.Env" %>
<%@ page import="org.posterita.lib.UdiConstants" %>
<%@ page import="org.posterita.user.WebUserInfo" %>
<%@page import="org.posterita.businesslogic.POSTerminalManager"%>
<%@ page import="org.posterita.lib.UdiConstants" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>	
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<logic:notPresent name="<%=WebUserInfo.NAME%>" scope="session">
	<jsp:forward page="/POSLogin.do"/>
</logic:notPresent>

<bean:parameter id="offset" name="offset" value="0"/>
<bean:parameter id="length" name="length" value="50"/>

<logic:present parameter="length">
	<bean:parameter id="length" name="length"/>
</logic:present>

<logic:notPresent parameter="length">
	<logic:present cookie="preference.display">
		<bean:cookie id="cookie" name="preference.display"/>
		<bean:parameter id="length" name="length" value="<%=cookie.getValue()%>"/>
	</logic:present>
	<logic:notPresent cookie="preference.display">
		<bean:parameter id="length" name="length" value="50"/>
	</logic:notPresent>
</logic:notPresent>
<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Cache-Control","no-store"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server

String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Properties ctx = TmkJSPEnv.getCtx(request);
String posName = POSTerminalManager.getTerminalName(ctx);
String orgName = MOrg.get(ctx, Env.getContextAsInt(ctx, UdiConstants.ORG_ID_CTX_PARAM)).getName();
String roleName = MRole.get(ctx, Env.getContextAsInt(ctx, UdiConstants.AD_ROLE_ID)).getName();
Integer userId = new Integer(Env.getAD_User_ID(ctx));
String version = UdiConstants.POS_VERSION;
String appName = (String) request.getSession().getAttribute(Constants.APP_NAME);
boolean isTerminalLocked = POSTerminalManager.isTerminalLocked(ctx);

if (isTerminalLocked)
{
	posName += "<span style=\"color:red\"> (Locked)</span>";
}
%>

<%--
<logic:notPresent name="<%=WebUserInfo.NAME%>" scope="session">
	<jsp:forward page="/LoginHome.do"/>
</logic:notPresent>
--%>

<bean:define id="displayPageSize" value="10"/>
<logic:present cookie="preference.display">
	<bean:cookie id="displayCookie" name="preference.display"/>
	<%
		if (displayCookie != null && displayCookie.getValue() != null)
		{
			displayPageSize = displayCookie.getValue().toString();
		}
	%>
</logic:present>
<%
	Integer pageSize = new Integer(displayPageSize);
%>


<%@page import="org.posterita.struts.login.GoToHomePageAction"%>
<%@page import="org.compiere.model.MStore"%>
<%@page import="org.posterita.businesslogic.StoreManager"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<base href="<%=basePath%>">
		<title><%=appName%></title>				
		<script src="js/enableButton.js"></script>				
		<script src="js/pos.js"></script>		
		<script src="javascripts/prototype.js"></script>
	 	<script src="javascripts/scriptaculous.js"></script>
	 	<script src="javascripts/sorttable.js"></script>
	 	<script src="js/shortcut.js"></script>
	 	<script src="js/tooltip.js"></script>
	 	<script src="js/dom-drag.js"></script>
	 	<script src="js/keyboard.js"></script>
	 	<script src="js/calculator.js"></script>
	 	<script src="js/js-calendar.js"></script>
	 	<link rel="shortcut icon" href="images/posterita.jpg" type="image/jpg" /> 
	 	<link type="text/css" href="css/common.jsp" rel="stylesheet">
	 	<link type="text/css" href="css/js-calendar/calendar-win2k-1.css" rel="stylesheet">
	 	<link type="text/css" href="css/mainMenu.css" rel="stylesheet">
	
	</head>
	<body id="body">
		<div id="keyboard"></div>
		<!-- start of page header -->
		<div id="newHeader" style="display:none">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
			  <tr>
			    <td width="75%">
				    <div id="posHeader">
				    	<div id="backButn" onclick="get('<html:rewrite action='/GetMenuItemsAction.do?action=getMenuItems'/>')">
				    		<img src="images/newUI/back-menu.gif" width="28px" height="41px" border="0px"/>
						</div>
						<div id="logo">
							<img src="images/newUI/logo.gif" alt="Powered by Posterita POS" width="133px" height="41px" border="0px"/>
						</div>
						<div id="acDetailsContainer">
							<div id="acDetailsR">
								<div id="org">
									<pos:message key="smenu.organisation" textOnly="true"/>:
									<span class="strong"><%=orgName%></span>
								</div>
								<div id="terminal">
									<pos:message key="pos.terminal" textOnly="true"/>:
									<span class="strong"><%=posName%></span>
								</div>
							</div>
							<div id="acDetailsL">
								<div id="user">
									<pos:message key="user" textOnly="true"/>:
									<span class="strong"><c:out value ='${webUserInfo.user.name}'/></span>
								</div>
								<div id="role">
									<pos:message key="smenu.role" textOnly="true"/>:
									<span class="strong"><%=roleName%></span>
								</div>
							</div>
						</div>
					</div>
			      </td>
			      <td width="25%">
			      	<div id="systemMenuContainer">
			      		<div id="SystemMenuBg">
			      			<div id="systemMenu">
			   					<img src="images/newUI/butn-touchscreen.gif" id="keyboardicon" tooltip="Logout" class="center">
								<html:link href="PostingAction.do?action=resubmitPosting">
									<image src="images/newUI/butn-system.gif" border="0" class="center"/>
								</html:link>
								<html:link href="POSLogoutAction.do?action=logout">
										<image src="images/newUI/butn-exit.gif" border="0" class="center"/>
								</html:link>
							</div>
			      		</div>
			      	</div>
			      </td>
			    </tr>
			</table>
		
			<script>
				function openCashDrawer()
				{
					var url = '<%=basePath + "CompletePOSOrderAction.do?action=openCashDrawer"%>';
					document.applets[0].printURL(url);
				}					
			</script>
				
			<div id="search">Search<br/><br/>
				<input type="text" id="searchMenu">
				<div id="searchResult" class="autocomplete2"></div>
				<script>
					var menus = new Array();
					var menuNames = new Array();
					var currentSelection = null;
					
					function initMenuShortcuts()
					{
							var links = document.getElementsByTagName('a');
							for(var i=0; i<links.length; i++)
							{
								var text = links[i].innerHTML;
								text = new String(text);
								text = text.stripTags();
								text = text.strip();
								
								if(text != null && text.length > 1)
								{				
									menus.push(links[i]);
									menuNames.push(text);
								}		
								
							}		
							
							new Autocompleter.Local('searchMenu','searchResult',menuNames, {afterUpdateElement:function(e1,e2){
								var index = e2.getAttribute('index');
								var menu = menus[index];
								window.location = menu.href;
							}});
							
							
							shortcut.add("F8", function(e){
								var search = $('searchMenu');
								var searchDiv = $('search')
								if(search.style.display == 'none' || searchDiv.style.display == 'none')
								{
									searchDiv.style.display = 'block'
									search.focus();
								}
								else
								{
									searchDiv.style.display = 'none'
								}
							});
					}
					
					Event.observe(window,'load',initMenuShortcuts,false);
				</script>
			</div>
		</div>
		<!-- end of page header -->
		
		<!-- start of page content -->
	<div id="content">
		<div id="titleContainer">
			<font class="pagetitle">
				<logic:present name="title">
					<bean:write name="title"/>
				</logic:present>
			</font>	
		</div>