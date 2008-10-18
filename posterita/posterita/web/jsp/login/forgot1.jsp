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

<%@ page import="org.posterita.Constants" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<tiles:insert page="/jsp/include/simpleHeader.jsp">
  <tiles:put name="title"><bean:message key="login.home.title"/></tiles:put>
</tiles:insert>
	 
	 	   <td colspan="1" bgcolor="#FFFFFF">
	    		<h2><bean:message key="login.home.title"/></h2>
			   	
			 	<table width="420" align="center" cellpadding="5" border="0" cellspacing="0" cols="2">
				 	<tr>
				 		<td colspan="1" valign="top">
							<table width="100%" border="0" cellpadding="5" cellspacing="0">
								<tr>
									<td valign="top">
										<table width="400" align="center" cellpadding="5" cellspacing="0">
											<tr>
												<td valign="top">
													<div class="nb">
														<logic:messagesPresent>
															<ul>
																<html:messages id="error">
																	<li><bean:write name="error"/></li>
																</html:messages>
															</ul>
														</logic:messagesPresent>
												    </div>
												</td>
											</tr>
										</table>
										<html:form action="/LoginActionDefault">
											<html:hidden property="action" value="<%=Constants.PASSWORD%>"/>
												<table class="loginbox" width="420" align="center" cellpadding="5" cellspacing="0" cols="2">
											 		<tr>
													  <td colspan="2"><br><bean:message key="login.forgot.forgotPassword"/><br><br></td>
													</tr>
													<tr>
														<td colspan="1" align="right" width="171"><bean:message key="login.forgot.username"/></td>
														<td colspan="1" align="left"><html:text property="username"/></td>
													</tr>
													
													<tr>
													<td align="center">&nbsp;</td>
														<td  align="left">
											     			<html:submit styleClass="buttonwide"><bean:message key="login.forgot.sendPassword"/></html:submit>
														<br><br>
														</td>
													 </tr>
												</table>
									    </html:form>
    								</td>
								</tr>
			
							</table>
						</td>
					</tr>
				</table>
			 
	   	<%@ include file="/jsp/include/footerTableBottom.jsp" %>													