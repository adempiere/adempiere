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

<%@ page import="org.posterita.struts.attachment.AttachmentAction" %>
<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.beans.UDIBean" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	


<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>


<tiles:insert page="/jsp/include/headerTableTop.jsp">
<tiles:put name="title">Attach Logo</tiles:put>
</tiles:insert>
	 
<html:form action="/CreateAttachmentAction" enctype="multipart/form-data">
<html:hidden property="action" value="<%=AttachmentAction.CREATE_ATTACHMENT%>"/>
	 
	<table width="50%" border="0" cellpadding="5" cellspacing="0">
	 	<tr>
   			<td colspan="2">
   				<%@ include file="/jsp/include/tabTop.jsp" %>Attach Logo<%@ include file="/jsp/include/tabBottom.jsp" %>
   				<%@ include file="/jsp/include/errors.jsp" %>
   			</td>
   		</tr>
   		
   		<tr>
   			 <td>	
					find logo
   			 </td>	
   			 	
   		
 			  <td>
 				  	<html:file property="logo"/>
 			  </td>  		
   		</tr>
   		
		
		<tr>
					<td>Name</td>
					<td colspan="2">
						<html:text property="logoName" size="30"/>
					</td>
		</tr>
		
		<tr>
				<td colspan="3" align="center">
					<html:submit>
						<bean:message key="button.save"/>
					</html:submit>
				</td>
		</tr>
	</table>
</html:form>
<%@ include file="/jsp/include/footerTableBottom.jsp" %>
	   
	
												