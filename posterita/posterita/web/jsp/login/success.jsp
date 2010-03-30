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

<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.lib.UdiConstants" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

    

 
<logic:notPresent name="<%=WebUserInfo.NAME%>" scope="session">
	<jsp:forward page="/LoginHome.do"/>
</logic:notPresent>


<tiles:insert page="/jsp/include/headerTableTop.jsp">
  	<tiles:put name="title"><bean:message key="login.home.title"/></tiles:put>
</tiles:insert>


		<table width="100%" border="0" cellpadding="5" cellspacing="1" align="center">
			<tr>
				<td align="center">
					<table>
						<tr>
							<logic:present name="<%= Constants.ORGANISATION %>">
								<logic:equal name="<%=Constants.ORGANISATION%>" property="motorcycle" value="true">	
									<td width="670px" height="130px" align="center" class="motoDashboard">		
									 
							    	</td>
						    	</logic:equal>
						    	
						    	
								<logic:equal name="<%=Constants.ORGANISATION%>" property="automobile" value="true">			
									<td width="670px" height="130px" align="center" class="autoDashboard">
							    		
							    	</td>
						    	</logic:equal>	
							</logic:present>			    
						</tr>
						
						<tr>
							<td>
								<br><br><br>
							</td>
						</tr>						
					</table>
				</td>
			</tr>
			
			<tr>
				<td align="center">
					
					<logic:present name="<%= Constants.MONTH_SALES %>">						
					<bean:size id="noOfReports" name="<%= Constants.MONTH_SALES %>"/>					
					
					<logic:notEqual name="noOfReports" value="0">
						<table border="0">
						<tr>
						<logic:iterate id="report" name="<%= Constants.MONTH_SALES %>">
						<td>
						
							<bean:define id="imgSrc" name="report" property="imagePath"/>
							<bean:define id="imgTitle" name="report" property="imageTitle"/>
							<bean:define id="dateFrom" name="report" property="dateFrom"/>
							<bean:define id="dateTo" name="report" property="dateTo"/>
							<bean:define id="requestParams" name="report" property="requestParams"/>
							
							<table border="0">								
								<tr>
									<td  align="center">
									<html:link action="/ViewReportAction.do?action=viewReport&<%= requestParams %>" target="_blank">
										<img src="<%= imgSrc %>" name="image" width="210" height="140">	
									</html:link>																	
									</td>
								</tr>
								<tr>
									<td align="center">
										<bean:write name="imgTitle"/><br>
										<p align="center">From <bean:write name="dateFrom"/> To <bean:write name="dateTo"/></p>
									</td>
								</tr>
							</table>														
						</td>								
						</logic:iterate>
						</tr>
						</table>
					</logic:notEqual>						
					</logic:present>
					
				</td>
			</tr>
			 		
		</table>
		
<%@ include file="/jsp/include/footerTableBottom.jsp" %>
