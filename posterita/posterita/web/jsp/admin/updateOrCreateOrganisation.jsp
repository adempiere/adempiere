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
--%>
<%@ page import="org.posterita.Constants" %>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %>
<!-- page contents -->

<table class="main">			
	<tr>
		<td>				
			<html:form action="/OrganisationAction">
				<html:hidden property="action" value="updateOrCreateOrg"/>
				<html:hidden property="orgId" />
					<table align="left" cellpadding="5" width="100%">
						<tr>
							<td width="50%" height="100%" valign="top">
								<fieldset>
									<legend><pos:message key="Organisation"/></legend>
									<table>
										<tr>			
											<td><label><pos:message key="Name"/></label><mandatory>*</mandatory></td>
											<td align="right"><html:text property="orgName" styleClass="text"/></td>
										</tr>
										<tr>			
											<td><label><pos:message key="Description"/></label></td>
											<td align="right"><html:text property="description" styleClass="text"/></td>
										</tr>
										<tr>		
											<td>
												<label><pos:message key="IsActive"/></label>
											</td>
											<td colspan="3">
												 <html:checkbox  property="isActive" value="true"/>
											</td>
										</tr>
									</table>
								</fieldset>
							</td>
							<td width="50%" height="100%" valign="top">
								<fieldset>
									<legend>Receipt Footer Message</legend>
									<table>
										<tr>
											<td>
												<label><pos:message key="Message"></pos:message></label>
											</td>
											<td>
												<html:textarea property="receiptFooterMsg" style="width:500px;" styleClass="text"/>
											</td>
										</tr>
									</table>
								</fieldset>
							</td>
						</tr>
						<tr>
							<td>
								<fieldset>
								<legend><pos:message key="address"/></legend>
								<table>
									<tr>	
										<td><label><pos:message key="Address1"/></label></td>
										<td>
											<html:text property="address1" styleClass="text"/>
										</td>
									</tr>
									<tr>	
										<td><label><pos:message key="Address2"/></label></td>
										<td>
											<html:text property="address2" styleClass="text"/>
										</td>
									</tr>
									<tr>
										<td><label><pos:message key="City"/></label></td>
										<td>
											<html:text property="city" styleClass="text"/>
										</td>
									</tr>
									<tr>
										<logic:present name="<%=Constants.COUNTRIES %>">
										<td><label><pos:message key="Country"/></label></td>
										<td>
											
											<html:select property="countryId" value="100" style="width:190px">
												<html:options collection="<%=Constants.COUNTRIES%>" property="key" labelProperty="name"/>																						
											</html:select>
										</td>
										</logic:present>
									</tr>
									<tr>
										<td><label><pos:message key="Postal"/></label></td>
										<td>
											<html:text property="postalAddress" styleClass="text"/>
										</td>
									</tr>							
								</table>	
							</fieldset>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="right">
							<html:submit styleClass="save smallbutton">&nbsp;</html:submit>
						</td>
					</tr>
				</table>
			</html:form>
		</td>
	</tr>
</table>	    		
			 			        					
<%@ include file="/jsp/include/posFooter.jsp" %>