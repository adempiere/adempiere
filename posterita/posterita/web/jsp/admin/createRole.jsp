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
 * @author praveen
--%>

<!-- createRole.jsp -->
<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.beans.UDIBean" %>
<%@ page import="org.posterita.core.MenuItem" %>


<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	

<logic:notPresent name="<%=WebUserInfo.NAME%>" scope="session">
	<jsp:forward page="/LoginHome.do"/>
</logic:notPresent>

<tiles:insert page="/jsp/include/headerTableTop.jsp">
  	<tiles:put name="title"><bean:message key="role.create"/></tiles:put>
</tiles:insert>

<%@ include file="/jsp/include/tabTop.jsp" %><bean:message key="role.create"/><%@ include file="/jsp/include/tabBottom.jsp" %>
<table width="100%" border="0" cellpadding="5" cellspacing="1" align="center">
		<tr>
			<td> 
				
			</td>
		</tr>
		
		<tr>
			<td>		
				<%@ include file="/jsp/include/errors.jsp" %>	
				
				<html:form action="/CreateRoleAction">
					<html:hidden property="action" value="createRole"/>
					
						<table align="left" border="0" cellpadding="5" cellspacing="0" cols="2">
						   <tr>
						   		<td align="left"><bean:message key="role.name"/></td>
						   		<td><html:text property="name"/></td>
						   </tr>
						   
						   <tr>
   							<td align="left"><pos:message key="IsAccessAllOrgs"/></td>
   							<td><html:checkbox property="isAccessAllOrgs" name="<%=Constants.ROLE%>" value="true"/></td>
   							</tr>
						   <tr>			
								<td>
									<label><pos:message key="UserDiscount"/>
								</td>
								<td>
									<html:text property="userDiscount" styleClass="text"/>
								</td>
							</tr>
							<tr>				
								<td>
									<label><pos:message key="over.ride.limit.price"/>
								</td>
								<td>
									<html:checkbox property="isOverwritePriceLimit" value="true"/>
								</td>
					    	</tr>						   						   
						   	<tr>
						   		<td align="left">
						   			Can Create Order
						   		</td>
						   		
						   		<td>
						   			<html:checkbox property="canCreateOrder" value="true"/>
						   		</td>
						   </tr>
						
						   
						   <tr>
						   		<td align="left">
						   			Can Alter Order
						   		</td>
						   		
						   		<td>
						   			<html:checkbox property="canAlterOrder" value="true"/>
						   		</td>
						   </tr>
						   
						   
						   <tr>
						   		<td align="left">
						   			Can View Order
						   		</td>
						   		
						   		<td>
						   			<html:checkbox property="canViewOrder" value="true"/>
						   		</td>
						   </tr>
						   
						  
						   <tr>
						   		<td colspan="2">
						   			<br><br>
						   			<strong>Available Menus</strong>
						   			<hr>							   			 
						   		</td>
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
														<td>
															<input type="checkbox" name="checkBox" value="<bean:write name='ele' property='menuId'/>"/>
														</td>
													</tr>
													</logic:iterate>  						
							   						
						   						</logic:equal>
							   											   		
								   			</logic:iterate>
									   	</table>
									</logic:present>
						   		</td>
						   	</tr>
						   <tr>
								<td colspan="2" align="right">
									<html:button onclick="clearAll(this.form)" property="button">
										Clear All
									</html:button>
									<html:button onclick="selectAll(this.form)" property="button">
										Select All
									</html:button>
									<html:submit>
	   	 		                       <bean:message key="button.save"/>
			                         </html:submit>  
								</td>
							</tr>
						   								
						</table>
						
					</html:form>
			</td>
		</tr>
		
		
	</table>	    		
			 			        					
 							
	    									 					 
<%@ include file="/jsp/include/footerTableBottom.jsp" %> 