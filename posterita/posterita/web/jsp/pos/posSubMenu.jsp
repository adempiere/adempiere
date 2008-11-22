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



<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.core.MenuItem" %>
<!-- posSubMenu.jsp -->
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.lib.UdiConstants" %>
<%@ page import="java.util.Properties"%>
<%@ page import="org.compiere.model.MWebMenu" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.posterita.core.MenuItem" %>
<%@ page import="java.util.Map.Entry"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="org.posterita.core.businesslogic.ElementManager" %>
<%@ page import="org.posterita.core.bean.ElementBean" %>
<%@ page import="org.posterita.core.TmkJSPEnv" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	

<%@page import="org.posterita.businesslogic.MenuManager"%>
<logic:notPresent name="<%=WebUserInfo.NAME%>" scope="session">
	<jsp:forward page="/LoginHome.do"/>
</logic:notPresent>

<%@ include file="/jsp/include/posHeader.jsp" %>

	<form name="home" class="form">
		<html:hidden property="menuIdCode"  value="0"/>
			<table cellspacing="0px" cellpadding="0px" border="0px" width="100%">
				<tr height="100%">
					<td class="mainContainer" width="75%" valign="top">
						<logic:present name="<%= Constants.MENU_ITEMS%>">
							<bean:write name="<%=Constants.MENU_ITEMS%>" filter="false"/>
						</logic:present>
					</td>
					<td class="rightContainer" width="25%" valign="top" height="100%">
						<div id="rightContainer">
							<div id="mainHeaderTitleContainer">
								<h1>POS MAIN MENU</h1>
							</div>
							<div id="posNavContainer">
								<%= MenuManager.getMainMenus(ctx, request) %>				
							</div>
						</div>
					</td>
				</tr>
			</table>
	</form>
</div>

<!-- end of page content -->
		 			        					 									 					 
<%@ include file="/jsp/include/posFooter.jsp" %>
