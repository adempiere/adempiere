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
<%@ page import="org.compiere.model.MOrg" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<tiles:insert page="/jsp/include/headerTableTop.jsp">
  <tiles:put name="title"><bean:message key="admin.userDetails.title"/></tiles:put>
</tiles:insert>
	 
		<table width="100%" border="0" cellpadding="5" cellspacing="0">
	   		<tr>
	    		<td>
					<%@ include file="/jsp/include/tabTop.jsp" %><bean:message key="admin.userDetails.title"/><%@ include file="/jsp/include/tabBottom.jsp" %>   								
		   		</td>
		   	</tr>
		   							
		   	<tr>
		   		<td>
		   			<table width="100%" border="0" cellpadding="5" cellspacing="1" align="center" cols="5"> 		  		
											
						<tr>
							<td width="100">
								<bean:message key="user.username"/>
							</td>
							<td>
								<bean:write name="<%=Constants.USER_DETAILS%>" property="username"/>
							</td>
						</tr>
						<tr>
							<td>
								<bean:message key="user.role"/>
							</td>
												
							<td>
								<bean:write name="<%=Constants.USER_DETAILS%>" property="roleName"/>
							</td>
						</tr>
						<tr>
							<td>
								<bean:message key="user.address"/>
							</td>
												
							<td>
								<bean:write name="<%=Constants.USER_DETAILS%>" property="address1"/>
							</td>
						</tr>
						<tr>
							<td>
								<bean:message key="user.city"/>
							</td>
												
							<td>
								<bean:write name="<%=Constants.USER_DETAILS%>" property="city"/>
							</td>
						</tr>
						<tr>
							<td>
								<bean:message key="user.postaladdress"/>
							</td>
												
							<td>
								<bean:write name="<%=Constants.USER_DETAILS%>" property="postalAddress"/>
							</td>
						</tr>
						<tr>
							<td>
								<bean:message key="user.region"/>
							</td>
												
							<td>
								<bean:write name="<%=Constants.USER_DETAILS%>" property="region"/>
							</td>
						</tr>							
						<tr>
							<td>
								<bean:message key="user.email"/>
							</td>
							<td>
								<bean:write name="<%=Constants.USER_DETAILS%>" property="email"/>
							</td>
						</tr>
						
						<tr>
							<td>
								<bean:message key="user.phone"/>
							</td>
							<td>
								<bean:write name="<%=Constants.USER_DETAILS%>" property="phone"/>
							</td>
						</tr>
						<tr>
							<td>
								<bean:message key="user.isActive"/>
							</td>
							<td>								
								<logic:equal name="<%=Constants.USER_DETAILS%>" property="isActive" value="true">
									Yes
								</logic:equal>
								<logic:notEqual name="<%=Constants.USER_DETAILS%>" property="isActive" value="true">
									No
								</logic:notEqual>
							</td>
						</tr>	
						<tr>
							<td>
								<bean:message key="user.isSalesRep"/>								
							</td>
							<td>								
								<logic:equal name="<%=Constants.USER_DETAILS%>" property="isSalesRep" value="true">
									Yes
								</logic:equal>
								<logic:notEqual name="<%=Constants.USER_DETAILS%>" property="isSalesRep" value="true">
									No
								</logic:notEqual>
							</td>
						</tr>																																
						
					</table>
				</td>
			</tr>				
		</table>
		
						
		<%@ include file="/jsp/include/footerTableBottom.jsp" %>	   
	
												