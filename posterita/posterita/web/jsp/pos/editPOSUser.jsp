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


<!--editPosUser.jsp-->
<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.beans.UDIBean" %>
<%@ page import="org.compiere.model.MCommission" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

<logic:notPresent name="<%=WebUserInfo.NAME%>" scope="session">
	<jsp:forward page="/LoginHome.do"/>
</logic:notPresent>
<bean:define id="title"><pos:message textOnly="true" key="edit.user"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
		
<%@ include file="/jsp/include/errors.jsp" %>	
			<html:form action="/EditPOSUserAction">
				<html:hidden property="action" value="editUser"/>
					<table align="left" cellpadding="5" width="100%">
							<tr>
								<td width="50%" height="100%">
									<fieldset>
										<legend><pos:message key="user"/></legend>
										<table>
										<tr>
										<td><label><pos:message key="AD_Role_ID"/></label><mandatory>*</mandatory></td>
										<td align="right">
											<html:select property="roleId" styleClass="text">
												<html:options collection="<%=Constants.ROLES%>" property="roleId" labelProperty="name"/>
										   	</html:select>
										</td>
									</tr>
									
									<tr>
										<td>
											<label><pos:message key="organisation.name"/>:</label><mandatory>*</mandatory>
										</td>
									
										<td>
											<html:select property="orgId" styleClass="text">
												<html:options collection="<%=Constants.USER_ORGS%>" property="key" labelProperty="name"/>
										 	</html:select>
										</td>
									</tr>									
									
									<tr>			
										<td colspan="2"><label><pos:message key="Name"/></label><mandatory>*</mandatory>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<label><bean:write name="<%=Constants.USER_DETAILS%>" property="username"/></label>
										<html:hidden property="userId"/>
										</td>
									</tr>
									
									<tr>			
										<td><label><pos:message key="Password"/></label><mandatory>*</mandatory></td>
										<td align="right">
											<html:password property="password" styleClass="text"/>
										</td>
									</tr>
									
									<tr>			
										<td colspan="2"><label><bean:message key="user.confirmPassword"/></label><mandatory>*</mandatory>
										
											<html:password property="confirmPassword" styleClass="text"/>
										</td>
									</tr>
									
									<tr>			
										<td><label><pos:message key="UserPIN"/></label><mandatory>*</mandatory></td>
										<td align="right">
											<html:password property="userPIN" styleClass="text"/>
										</td>
									</tr>
				
									</table>
									</fieldset>
									</td>
									<td width="50%" height="100%">
									
									<fieldset>
									<legend>Address</legend>
									<table>
										
						
										<tr>
											<td><label><pos:message key="Address1"/></label></td>
											<td>
												<html:text property="address1" styleClass="text"/>
											</td>
										</tr>
										
										<tr>
											<td><label><pos:message key="City"/></label></td>
											<td>
												<html:text property="city"  styleClass="text"/>
											</td>
										</tr>
										
										<tr>
											<td><label><pos:message key="Postal"/></label></td>
											<td>
												<html:text property="postalAddress" styleClass="text"/>
												<html:hidden property="locationId"/>
											</td>
										</tr>
											
										<tr>
											<td><label><pos:message key="Email"/></label></td>
											<td>
												<html:text property="email" styleClass="text"/>
											</td>
										</tr>
										
										<tr>
											<td><label><pos:message key="Phone"/></label></td>
											<td>
												<html:text property="phone" styleClass="text"/>
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
									<legend>User's Details</legend>
									<table>
						
										<tr>
											<td>
							                      <label><pos:message key="IsActive"/></label>
				            				</td>
											
											<td>
						                          <html:checkbox property="isActive" value="true"  styleClass="text"/>				                           
						                    </td>
						                    
										</tr>
										
										<tr>
											<td>
							                      <label><pos:message key="IsSalesRep"/></label>
				            				</td>
											
											<td>
						                          <html:checkbox property="isSalesRep" value="true" styleClass="text"/>				                           
						                    </td>
						                    
										</tr>
										
										
										<tr>
											<td>
							                      <label><bean:message key="user.docBasisType"/></label>
				            				</td>
											
											<td>
												<html:select property="docBasisType"  styleClass="text">
													    <html:option value="<%=MCommission.DOCBASISTYPE_Invoice %>"> Invoice </html:option>
														<html:option value="<%=MCommission.DOCBASISTYPE_Order %>"> Order </html:option>
														<html:option value="<%=MCommission.DOCBASISTYPE_Receipt %>">Payment Receipts </html:option>
														
												</html:select>				                           
						                    </td>
						                    
										</tr>
										
										<tr>
											<td>
							                      <label><bean:message key="user.substract.amt"/></label>
				            				</td>
											<td>
												<html:text property="subtractAmt" styleClass="text"/>
											</td>
											
										</tr>
										
										<tr>
											<td>
							                      <label><bean:message key="user.comm.per"/></label>
				            				</td>
											<td>
												<html:text property="amtMultiplier" styleClass="text"/>
											</td>
										</tr>
										
										<tr>
											<td>
							                      <label><bean:message key="user.comm.fre"/></label>
				            				</td>
											<td>
												<html:select property="frequencyType"  styleClass="text">
														<html:option value="<%=MCommission.FREQUENCYTYPE_Weekly %>">Weekly </html:option>
													    <html:option value="<%=MCommission.FREQUENCYTYPE_Monthly %>"> Monthly </html:option>
														<html:option value="<%=MCommission.FREQUENCYTYPE_Quarterly %>"> Quarterly </html:option>
														<html:option value="<%=MCommission.FREQUENCYTYPE_Yearly %>">Yearly </html:option>
														
												</html:select>				                           
						                    </td>
										</tr>
										<tr>
											<td>
												&nbsp;
											</td>
										
										</tr>
										
									</table>
									</fieldset>
									</td>
									</tr>
									</table>
									</td>
									</tr>
								
																
										<tr>
										
										<td align="right">
							
											<html:submit styleClass="save smallbutton">
			   	 		                       &nbsp;
					                         </html:submit> 
										</td>
					   			</tr>
								<tr>
									<td>
									<table align="left" width="100%">
									<tr>	
										<td>
											<tr>	
												<td>
												<fieldset>
												<legend>Information</legend>
												<table>
					                     		 <label><bean:message key="user.substract.exp"/></label>
					                     		 </table>
												</fieldset>
												</td>
												</tr>
											</td>
										</tr>			
										</table>
		            				</td>
								</tr>
								
							</table>
					
				</html:form>					
 							
	    									 					 
<%@ include file="/jsp/include/posFooter.jsp" %>   