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

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<tiles:insert page="/jsp/include/simpleHeader.jsp">
  <tiles:put name="title"><bean:message key="login.home.title"/></tiles:put>
</tiles:insert>
	 	   <td colspan="1" bgcolor="#FFFFFF">
				
				<table width="324" align="center" cellpadding="0" cellspacing="0" border="0">
					<tr>
						<td align="center">
				    		<h1>
				    			<bean:message key="login.home.title.part1"/>
				    		</h1>
						</td>
					</tr>
					
					<tr>	
						<td align="center">
				    		<h1>
				    			<bean:message key="login.home.title.part2"/>
				    		</h1>
						</td>						
					</tr>
				</table>
			   
			 	<table width="324" align="center" cellpadding="0" cellspacing="0" border="0">
				 	<tr>
				 		<td valign="top">
							<table width="100%" border="0" cellpadding="5" cellspacing="0">
								<tr>
									<td valign="top" class="boldgraysmall">

										<%@ include file="/jsp/include/errors.jsp" %>

										<html:form action="/LoginAction" focus="username">
										<html:hidden property="action" value="success"/>
											<%@ include file="/jsp/login/loginForm.jsp" %>
										</html:form>	
										
    								</td>
								</tr>
								
								<tr>
									  <td align="right">
									  	<html:link href="LoginForgot.do">
									  		<bean:message key="login.home.loginForgot"/>
									  	</html:link>
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











												