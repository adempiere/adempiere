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

<!-- password.jsp -->
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
			   	
			 	<table width="300" align="center" cellpadding="5" cellspacing="0" cols="2">
				 	<tr>
				 		<td colspan="1" valign="top">
							<table class="loginbox" width="100%" border="0" cellpadding="5" cellspacing="0">
								<tr>
									<td valign="top">
										<table width="300" align="center" cellpadding="5" cellspacing="0">
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
									
												<table class="loginbox" width="300" align="center" cellpadding="5" cellspacing="0" cols="1">
											 		<tr>
													  <td class="contentred" colspan="2"><bean:message key="login.password.passwordSent"/></td>
													</tr>
													<tr class="white">
													  <td align="center">
													  	<html:link href="LoginHome.do" styleClass="left">
													  		<html:img src="images/back_btn.png" width="15" height="15" border="0" align="absmiddle" vspace="5"/>
													  			<bean:message key="login.password.backToLogin"/>
													  	</html:link><br></td>
													</tr>
												</table>
									    
    								</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			 </td>
	   	
	   	<%@ include file="/jsp/include/tablebottom.jsp" %>
	   
	</td>
   </tr>
  </table>
 </td>
 
<%@ include file="/jsp/include/footer.jsp" %>










												