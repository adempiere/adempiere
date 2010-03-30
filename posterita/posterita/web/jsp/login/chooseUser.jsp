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
<%@ page import="org.posterita.beans.OrgUsersBean"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<tiles:insert page="/jsp/include/simpleHeader.jsp">
  <tiles:put name="title"><bean:message key="login.home.title"/></tiles:put>
</tiles:insert>


<td colspan="1" bgcolor="#FFFFFF">
	<h2>
		<bean:message key="login.choose.title"/>
	</h2>
	
	<table cellspacing="0" cellpadding="0" width="100%">
	<logic:iterate id="element" indexId="count" name="<%=Constants.ORG_USERS%>" type="org.posterita.beans.OrgUsersBean">
	<%							
		if ((count.intValue()%4) == 0)
		{						
		
	%>
		<tr>
		
	<%
		}
	%>
		
		<td valign="top">
			<table cellspacing="0" cellpadding="0">

				<tr>
					<td>
						<B><bean:write name="element" property="orgName"/></B>
						<br>
					</td>
				</tr>			
				<tr>
					<td>
						<!--<ul>-->
							<logic:iterate id="user" collection="<%=element.getOrgUsers()%>" type="org.compiere.model.MUser">
							<!--<li>-->
								&nbsp;&nbsp;&nbsp;&nbsp;<html:link href="LoginUserAction.do?action=loginUser&userId=<%= user.get_ID() %>" styleClass="submenu">
									<bean:write name="user" property="name"/>
								</html:link>	
								<br/>				
							<!--</li>-->
							</logic:iterate>
						<!--</ul>-->
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>

			</table>
		</td>
			
	<%							
		if ((count.intValue()%4-4) == 0)
		{						
		
	%>
		</tr>
		
	<%
		}
	%>	

	</logic:iterate>
	</table>
</td>

<%@ include file="/jsp/include/tablebottom.jsp" %>

</td>
</tr>
</table>
</td>

<%@ include file="/jsp/include/footer.jsp" %>










												