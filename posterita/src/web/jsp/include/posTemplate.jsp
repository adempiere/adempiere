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

<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>	
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<!-- start of header -->
<%
String appName = (String) request.getSession().getServletContext().getAttribute(Constants.APP_NAME);
%>
<html>
	<head>
		<title><%=appName%></title>
		<script src="/js/enableButton.js"></script>
		<!--
		<link rel="stylesheet" href="css/style.css" type="text/css">
		<link rel="stylesheet" href="css/global.css" type="text/css">
		<bean:write name="cssURI" filter="false"/>
		-->
		<style>
			body, td, th, input { /* redundant rules for bad browsers  */
	            font-family: verdana, sans-serif;
	            font-size: x-small;
	            voice-family: "\"}\"";
	            voice-family: inherit;
	            font-size: small;
	    	}
	    	
	    	body{	    		
	    		margin:40px;
	    		background-color:#CCCCCC;
	    	}
	    	
	    	table.header{
	    		background-color:#FFFFFF;
	    	}
	    	
	    	table.content{
	    		background-color:#FFFFFF;
	    	}
	    	
	    	table.footer{
	    		background-color:##CCCCCC;
	    	}
	    	
		</style>
	</head>
<body>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">	
	<tr>
		<td>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="header">	
			<tr>
				<td>
					<img src="">
				</td>
				<td align="right">
					<table>
						<tr>
							<td>User: </td>
							<td>XXXXXX</td>
						</tr>
						<tr>
							<td>Till No: </td>
							<td>XX</td>
						</tr>
					</table>
				</td>
			</tr>
			
			<tr>
				<td colspan="2">
					<table>
						<tr>
							<td>Menu 1</td>
							<td>Menu 2</td>
							<td>Menu 3</td>
							<td>Menu 4</td>
						</tr>
					</table>
				</td>
			</tr>
			
			<tr><td  colspan="2">&nbsp;</td></tr>
			</table>
		</td>
	</tr>
	
	<tr>
		<td>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="content">	
			<tr>
				<td>
				</td>
			</tr>
			</table>
		</td>
	</tr>
	
	<tr>
		<td>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="footer">
			<tr>
				<td>&nbsp;</td>
			</tr>	
			<tr>
				<td align="center">
					<div class="copy">
				 		All Content © <%=java.util.Calendar.getInstance().get(java.util.Calendar.YEAR)%> Tamak ICT
				 	</div>
			 	</td>
			</tr>
			</table>	
		</td>
	</tr>
	</table>	
	
</body>
</html>
<!-- end of footer -->