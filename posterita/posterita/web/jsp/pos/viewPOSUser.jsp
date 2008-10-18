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
 * @author Alok Pathak
--%>



<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.compiere.model.MOrg" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos" %>

<bean:define id="title"><pos:message key="user.details" textOnly="true"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
		
<table align="left" cellpadding="5" width="100%">
	<tr>
		<td width="50%">
			<fieldset>
				<legend>Contact</legend>
				<table>
					<tr>
						<td>
							<label><pos:message key="Name"/></label>
						</td>
						<td>
							<label><bean:write name="<%=Constants.USER_DETAILS%>" property="username"/></label>
						</td>
					</tr>
					<tr>
						<td>
							<label><pos:message key="AD_Role_ID"/></label>
						</td>
											
						<td>
							<label><bean:write name="<%=Constants.USER_DETAILS%>" property="roleName"/></label>
						</td>
					</tr>
					<TR>
						<TD>
						&nbsp;
						</TD>
					</TR>
					<TR>
						<TD>
						&nbsp;
						</TD>
					</TR>
					<TR>
						<TD>
						&nbsp;
						</TD>
					</TR>
					
				
				</table>
				</fieldset>
				</td>
				<td width="50%" height="100%">
				
				<fieldset>
				<legend><pos:message key="Address"/></legend>
				<table>
					
	
					<tr>
						<td>
							<label><pos:message key="Address1"/></label>
						</td>
											
						<td>
							<label><bean:write name="<%=Constants.USER_DETAILS%>" property="address1"/></label>
						</td>
					</tr>
					<tr>
						<td>
							<label><pos:message key="City"/></label>
						</td>
											
						<td>
							<label><bean:write name="<%=Constants.USER_DETAILS%>" property="city"/></label>
						</td>
					</tr>
					<tr>
						<td>
							<label><pos:message key="Postal"/></label>
						</td>
											
						<td>
							<label><bean:write name="<%=Constants.USER_DETAILS%>" property="postalAddress"/></label>
						</td>
					</tr>
					<!-- 
					<tr>
						<td>
							<label><pos:message key="regionName"/></label>
						</td>
											
						<td>
							<label><bean:write name="<%=Constants.USER_DETAILS%>" property="region"/></label>
						</td>
					</tr>		
					-->					
					<tr>
						<td>
							<label><pos:message key="EMail"/></label>
						</td>
						<td>
							<label><bean:write name="<%=Constants.USER_DETAILS%>" property="email"/></label>
						</td>
					</tr>
					
					<tr>
						<td>
							<label><pos:message key="Phone"/></label>
						</td>
						<td>
							<label><bean:write name="<%=Constants.USER_DETAILS%>" property="phone"/></label>
						</td>
					</tr>

				</table>
				</fieldset>
				</td>
				</tr>
				</table>
				<tr>
				<td>
				<table width="100%">
				<tr>
				<td>
				<fieldset>
				<legend><pos:message key="user.details"/></legend>
				<table>
					<tr>
						<td>
							<label><pos:message key="IsActive"/></label>
						</td>
						<td>
							<label>								
							<logic:equal name="<%=Constants.USER_DETAILS%>" property="isActive" value="true">
								<pos:message key="yes"/>
							</logic:equal>
							<logic:notEqual name="<%=Constants.USER_DETAILS%>" property="isActive" value="true">
								<pos:message key="no"/>
							</logic:notEqual>
							</label>
						</td>
					</tr>	
					<tr>
						<td>
							<label><pos:message key="IsSalesRep"/></label>						
						</td>
						<td>
							<label>								
							<logic:equal name="<%=Constants.USER_DETAILS%>" property="isSalesRep" value="true">
								<pos:message key="yes"/>
							</logic:equal>
							<logic:notEqual name="<%=Constants.USER_DETAILS%>" property="isSalesRep" value="true">
								<pos:message key="no"/>
							</logic:notEqual>
							</label>
						</td>
					</tr>
				</table>
				</fieldset>
				</td>
				</tr>
				</table>
				</td>
				</tr>																															
	
</table>
				
		
						
		<%@ include file="/jsp/include/posFooter.jsp" %>      
	
												