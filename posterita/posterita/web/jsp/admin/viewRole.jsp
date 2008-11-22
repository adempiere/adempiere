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

<!-- viewRole.jsp -->
<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.compiere.model.MOrg" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<tiles:insert page="/jsp/include/headerTableTop.jsp">
  <tiles:put name="title"><bean:message key="admin.viewUsers.title"/></tiles:put>
</tiles:insert>
	 <%@ include file="/jsp/include/tabTop.jsp" %>View Role<%@ include file="/jsp/include/tabBottom.jsp" %>
	<table width="100%" border="0" cellpadding="5" cellspacing="0">
   		<tr>
    		<td>
				   								
	   		</td>	   		   		
		</tr>
		
		<tr>
			<td>		
				<%@ include file="/jsp/include/errors.jsp" %> 
										
				<table width="100%" border="0" cellpadding="5" cellspacing="0" cols="2">
				<tr>
					<td><strong>Role Name :</strong><bean:write name="<%= Constants.ROLE %>" property="name"/></td>
				</tr>
				<tr>
					<td><hr></td>					
				</tr>
				<tr>
					<td><strong>Menus</strong></td>					
				</tr>
				<tr>
			    	<td>
						<logic:present name="<%= Constants.ROLE_MENUS%>">
							<table>
								<logic:iterate id="element" indexId="count" name="<%= Constants.ROLE_MENUS%>" type="org.posterita.core.MenuItem">
					   				<tr>
					   					<td class="contentName">
				   							<strong><bean:write name="element" property="name"/></strong>
				   						</td>
				   					</tr>
			   						<logic:equal name="element" property="hasSubMenu" value="true">
			   							<logic:iterate id="ele" collection="<%=element.getSubMenus()%>" type="org.posterita.core.MenuItem">
										<tr>
											<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<bean:write name="ele" property="name"/>
											</td>
										</tr>
										</logic:iterate>  						
				   						
			   						</logic:equal>
				   											   		
					   			</logic:iterate>
						   	</table>
						</logic:present>
			   		</td>
				</tr>		
				</table>
			</td>
		</tr>
	</table>
	    
			
						
	<%@ include file="/jsp/include/footerTableBottom.jsp" %>
	   
	
												