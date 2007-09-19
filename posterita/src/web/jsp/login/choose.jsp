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

<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.beans.OrgBean" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<tiles:insert page="/jsp/include/simpleHeader.jsp">
  <tiles:put name="title"><bean:message key="login.home.title"/></tiles:put>
</tiles:insert>
	   <td colspan="1" bgcolor="#FFFFFF">
	    		<h2><bean:message key="login.choose.title"/></h2>
			   	
			 	<table width="300" align="center" cellpadding="5" cellspacing="0" cols="2">
				 	<tr>
				 		<td colspan="1" valign="top">
							<table width="100%" border="0" cellpadding="5" cellspacing="0">
						 		<logic:iterate indexId="count" id="element" name="<%=Constants.ORGS%>" type="org.posterita.beans.OrgBean">
									<tr>
										<td align="center" valign="top">
											<a href="<%="LoginOrgAction.do?action=chooseOrg&orgID=" + element.getOrgID()%>" border="0">
												<%=element.getDealerName()%>
  											</a>
	    								</td>
									</tr>

								</logic:iterate>
								
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











												