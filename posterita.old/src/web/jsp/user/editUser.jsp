<%--
 *  Product: Posterita Web-Based POS (an Adempiere Plugin)
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

<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.beans.UDIBean" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	

<logic:notPresent name="<%=WebUserInfo.NAME%>" scope="session">
	<jsp:forward page="/LoginHome.do"/>
</logic:notPresent>

<tiles:insert page="/jsp/include/headerTableTop.jsp">
  	<tiles:put name="title">Edit User</tiles:put>
</tiles:insert>


 <table width="100%" border="0" cellpadding="5" cellspacing="1" align="center">
			<tr>
				<td> 
					<%@ include file="/jsp/include/tabTop.jsp" %>Edit User<%@ include file="/jsp/include/tabBottom.jsp" %>
				</td>
			</tr>
			
			<tr>
				<td>		
					<%@ include file="/jsp/include/errors.jsp" %>	
					
					<html:form action="/EditUserAction">
						<html:hidden property="action" value="editUser"/>
						
							<table align="center" width="350" border="0" cellpadding="5" cellspacing="0" cols="2">
							   <tr>
									<td>Role</td>
									<td colspan="2">
										<html:select property="roleId">
											<html:options collection="<%=Constants.ROLES%>" property="roleId" labelProperty="name"/>
									   	</html:select>
									</td>
								</tr>
								<tr>			
									<td colspan="1"><bean:message key="user.username"/></td>
									<td colspan="1"><bean:write name="<%=Constants.USER_DETAILS%>" property="username"/>
									<html:hidden property="userId"/>
									</td>
								</tr>
								
								<tr>			
									<td colspan="1"><bean:message key="user.password"/></td>
									<td colspan="1">
										<html:password property="password"/>
									</td>
								</tr>
								
								<tr>			
									<td colspan="1"><bean:message key="user.confirmPassword"/></td>
									<td colspan="1">
										<html:password property="confirmPassword"/>
									</td>
								</tr>
								
								
								<tr>
									<td colspan="1"><bean:message key="user.address"/></td>
									<td colspan="1">
										<html:text property="address1"/>
									</td>
								</tr>
								
								<tr>
									<td colspan="1"><bean:message key="user.city"/></td>
									<td colspan="1">
										<html:text property="city"/>
									</td>
								</tr>
								
								<tr>
									<td colspan="1"><bean:message key="user.postaladdress"/></td>
									<td colspan="1">
										<html:text property="postalAddress"/>
										<html:hidden property="locationId"/>
									</td>
								</tr>
								<tr>
									<td><bean:message key="user.region"/></td>
									<td colspan="2">
									<html:hidden property="regionId"/>	
									<bean:write name="<%=Constants.USER_DETAILS%>" property="region"/>
									</td>
								</tr>
								
								
								<tr>
									<td colspan="1"><bean:message key="user.email"/></td>
									<td colspan="1">
										<html:text property="email"/>
									</td>
								</tr>
								
								<tr>
									<td colspan="1"><bean:message key="user.phone"/></td>
									<td colspan="1">
										<html:text property="phone"/>
									</td>
								</tr>
								
								<tr>
									<td>
					                      <bean:message key="user.isActive"/>
                    				</td>
									
									<td>
				                          <html:checkbox property="isActive" value="true"/>				                           
				                    </td>
				                    
								</tr>
								
								<tr>
									<td>
					                      <bean:message key="user.isSalesRep"/>
                    				</td>
									
									<td>
				                          <html:checkbox property="isSalesRep" value="true"/>				                           
				                    </td>
				                    
								</tr>
								
								
								<tr>
									<td colspan="2" align="right">
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