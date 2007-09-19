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
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.lib.UdiConstants" %>
<%@ page import="java.util.Properties"%>
<%@ page import="org.compiere.model.X_U_Menu" %>
<%@ page import="org.posterita.model.UDIU_Menu" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.posterita.core.MenuItem" %>
<%@ page import="java.util.Map.Entry"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="org.posterita.core.businesslogic.ElementManager" %>
<%@ page import="org.posterita.core.bean.ElementBean" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	

<logic:notPresent name="<%=WebUserInfo.NAME%>" scope="session">
	<jsp:forward page="/LoginHome.do"/>
</logic:notPresent>

<%@ include file="/jsp/include/posHeader.jsp" %>

	
	
	<form name="home">
	
	<html:hidden property="menuIdCode"  value="0"/>
	<logic:present name="<%= Constants.MENU_ITEMS%>">
	
	<bean:write name="<%=Constants.MENU_ITEMS%>" filter="false"/>
	
	<%--
	<ul class="submenu">
	<logic:iterate id="element" indexId="count" name="<%= Constants.MENU_ITEMS%>" type="org.posterita.core.MenuItem">
		<li class="submenu">		
		<a href="<%=element.getMenuLink()%>" class="submenu">		
		<bean:write name="element" property="name"/>
		</a>	
		</li>	
	</logic:iterate>
	</ul>
	--%>
		
	</logic:present>
				 			        
	</form>			 			        


<%@ include file="/jsp/include/printOrderApplet2.jsp" %>			 			        					 									 					 
<%@ include file="/jsp/include/posFooter.jsp" %>
