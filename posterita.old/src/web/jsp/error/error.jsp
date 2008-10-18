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
 * @author Tamak
--%>


<%@ page import="org.apache.struts.Globals" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	




<tiles:insert page="/jsp/include/posHeader.jsp">
  	<tiles:put name="title"><bean:message key="errorpage.title"/></tiles:put>
</tiles:insert>

				 	
					
  		<table width="100%" border="0" cellpadding="5" cellspacing="0">
   			<tr>
    			<td valign="top">
	 			
	 			
		 			<%@ include file="/jsp/include/tabTop.jsp" %><bean:message key="errorpage.heading"/><%@ include file="/jsp/include/tabBottom.jsp" %>
		  		
					    <html:base/>
					 	
					    <!--<html:errors />-->
					    
					    <B><bean:write name="<%=Globals.EXCEPTION_KEY%>" property="localizedMessage"/></B>
					    <br>
					    
				<!-- TODO taglib-->   
				
				<%--
					Exception xception = (Exception) request.getAttribute(Globals.EXCEPTION_KEY);
					out.println(xception.getMessage());
					
				--%>
				
				<%--
					Exception xception = (Exception) request.getAttribute(Globals.EXCEPTION_KEY);
					StackTraceElement[] stackTraceElem = xception.getStackTrace();
					
					for(int i = 0;i<stackTraceElem.length; i++)
					{
						out.println(stackTraceElem[i].toString());
					}
				--%>
					
					
				</td>
			</tr>
		</table>

				
<%@ include file="/jsp/include/posFooter.jsp" %>
