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
 * @author tamak
--%>


<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.user.*" %>


<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/posterita.tld" prefix="posterita"%>


<tiles:insert page="/jsp/include/headerTableTop.jsp">
  	<tiles:put name="title"><bean:message key="admin.emailChanged.title"/> </tiles:put>
</tiles:insert>
	<%@ include file="/jsp/include/tabTop.jsp" %><bean:message key="admin.emailChanged.message"/><%@ include file="/jsp/include/tabBottom.jsp" %>
      <table width="100%" border="0" cellpadding="5" cellspacing="1" align="center">
			
			<tr>
				<td> 
				
				</td>
			</tr>
      </table>
			
<%@ include file="/jsp/include/footerTableBottom.jsp" %> 