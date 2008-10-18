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
<!-- chooseApplication.jsp -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>
<%@ page import="org.posterita.Constants" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
	<head>
		<base href="<%=basePath%>">
		<meta http-equiv="content-type" content="text/html;charset=iso-8859-1">
		<title>Welcome to Posterita</title>
		<script src="js/enableButton.js"></script>	
		<link rel="shortcut icon" href="images/posterita.jpg" type="image/jpg" /> 	
		<link type="text/css" href="css/mainMenu.css" rel="stylesheet">
	</head>

<body id="body">
	<table width="100%" cellspacing="0px" cellpadding="0px" border="0px">
		<tr>
			<td width="75%">
				<div id="posHeader">
					<div id="logo">
						<img src="images/newUI/logo.gif" alt="Powered by Posterita POS" width="133px" height="41px" border="0px"/>
					</div>
					<div id="mainTitle">Posterita POS</div>
				</div>
			</td>
			<td width="25%">
				<div id="headerContainerRight">
					<div id="headerRight">
						<div id="choosePOS">
							<span class="reg10UC">Choose your</span>
							<br/>
							<span class="bold13">POS APPLICATION</span>
						</div>
					</div>
				</div>
			</td>
		</tr>
		<tr height="100%">
			<td class="mainContainer" valign="top" width="75%" height="100%">
				<div id="mainContainer">
					<div id="liquid-roundFull">
						<div class="top">
							<span></span>
						</div>
						
						<div class="center-content">
							<div id="posSubNavContainer">
								<div class="posNews">
									<h1>Recent News</h1>
								</div>
								<div class="newsContainer">
									<div class="dateTag">
										<span class="date">08</span>
										<span class="month">SEPT</span>
									</div>
									<div class="newsContent">
										<a href="http://sourceforge.net/project/showfiles.php?group_id=187090&package_id=218271">Posterita 1.7.3 released on 03 August 2008</a>
									</div>
								</div>
								<div class="newsContainer">
									<div class="dateTag">
										<span class="date">06</span>
										<span class="month">AUG</span>
									</div>
									<div class="newsContent">
										<a href="http://sourceforge.net/project/showfiles.php?group_id=187090&package_id=218271">Posterita 1.7.2b released on 03 August 2008</a>
									</div>
								</div>
							</div>
						</div><!-- center-content-->
						
						<div class="bottom">
							<span></span>
						</div>
					</div>
					
					<div id="liquid-roundHalf">
						<div class="top">
							<span></span>
						</div>
						<div class="center-content">
							<div id="posSubNavContainer">
								<div class="posHelp">
									<h1>Help us better Posterita POS</h1>
								</div>
								<div class="infoContainer">
									We are constantly trying to improve the product, and we are interested to hear from you. There are several ways you can help us. 
									<div id="bulletList">
										<ul>
											<li>
												<a href="http://jira.posterita.org">Report your bugs on JIRA</a>
											</li>
											<li>
												<a href="http://www.surveymonkey.com/s.aspx?sm=kvBQqTsS_2fbkRmMFRh_2f9gNg_3d_3d">Fill out this survey</a>
											</li>
											<li>
												<a href="http://labs.mozilla.com/featured-projects/#prism">Use our POS with Prism</a>
											</li>
										</ul>
									</div>
								</div>
							</div>
						</div>
						<div class="bottom">
							<span></span>
						</div>
					</div>
		
					<div id="liquid-roundHalf">
						<div class="top">
							<span></span>
						</div>
						<div class="center-content">
							<div id="posSubNavContainer">
								<div class="posDonate">
									<h1>Donate</h1>
								</div>
								<div class="infoContainer">
									If you find this software helpful, kindly donate and help us support it.<br><br>
									<div id="donate">
										<a href="https://www.paypal.com/cgi-bin/webscr?cmd=_donations&business=fredtsang%40hotmail%2ecom&item_name=Posterita%20Donation&no_shipping=0&no_note=1&tax=0&currency_code=USD&lc=US&bn=PP%2dDonationsBF&charset=UTF%2d8">
											<img src="images/newUI/butn-donate.gif" alt="" height="26" width="92" border="0">
										</a><br>
									</div>
								</div>
							</div>
						</div> <!-- center-content -->
						
						<div class="bottom">
							<span></span>
						</div>
					</div>
				</div>
				<!-- 
				<div id="liquid-roundHalf">
						<div class="top">
							<span></span>
						</div>
						<div class="center-content">
							<div id="posSubNavContainer">
								<div class="posHelp">
									<h1>POSTERITA DEMO ACCESS DETAILS</h1>
								</div>
								<div class="infoContainer">
								<b>Please use the following details to log into the system.</b>
									<div id="bulletList">
										<ul>
											<li>
												<b>Username:</b> admin
											</li>
											<li>
												<b>Password:</b> 1234
											</li>
											<li>
												<b>UserPIN:</b> 1234
											</li>
										</ul>
									</div>
								</div>
							</div>
						</div>
						<div class="bottom">
							<span></span>
						</div>
					</div>
				-->
			</td>
			<td width="25%" height="100%" class="rightContainer">
				<div id="rightContainer">
					<div id="rightHeaderTitleContainer">
						<a href="(EmptyReference!)">Need help to set up POS <img src="images/newUI/icon-help.gif" alt=""border="0"></a>
					</div>
					<logic:present name="<%=Constants.WEB_APPLICATIONS%>">
						<div id="bulletlist2">
							<ul>
								<logic:iterate id="store" name="<%=Constants.WEB_APPLICATIONS%>" indexId="count">
									<li>
									<html:link href="SetApplicationParametersAction.do?action=setApplicationParameters" paramName="store" paramId="storeId" paramProperty="storeId" styleClass="submenu">
										<bean:write name="store" property="applicationName"/>
									</html:link>
									</li>
								</logic:iterate>
							</ul>	
						</div>
					</logic:present>
				</div>
			</td>
		</tr>
		<tr height="100%">
			<td class="mainContainer" width="75%" valign="top" height="100%">
			</td>
			<td valign="top" width="25%" height="100%" class="rightContainer">
				<div id="rightBtnContainer">
					<div id="rightBtn">
						<html:link href="Client.do">
							<img src="images/newUI/butn-createpos.gif" alt="" height="25" width="156" border="0">
						</html:link>
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
</body>
</html>
