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
<%@ page import="org.compiere.model.MOrg" %>
<%@ page import="org.posterita.struts.bPartner.BPartnerAction" %>
<%@ page import="java.util.Calendar" %>


<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt" %>

<bean:define id="title"><bean:message key="dealer.details"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %> 

<table width="100%" border="0" class="content" cellpadding="5" cellspacing="1" align="center" cols="5"> 		  		
	<tr>
		<th colspan="1"><bean:message key="dealer.name"/></th>
		<th colspan="1"><bean:message key="dealer.location"/></th>
		<th colspan="1"><bean:message key="dealer.phone"/></th>
		<th colspan="1"><bean:message key="dealer.fax"/></th>
	</tr>

	<logic:iterate indexId="count" name="<%=Constants.DEALER_DETAILS%>" id="element" type="org.posterita.beans.DealerDetailBean">
	<tr>
		<%
			String styleClass = "label";
			if ((count.intValue()%2) != 0)
				styleClass = "contentname";											
		%>
		
		<td class="<%=styleClass%>">	
			<bean:write name="element" property="dealerName"/>
		</td>
		
		<td class="<%=styleClass%>">	
			<bean:write name="element" property="location"/>
		</td>
			
		<td class="<%=styleClass%>">
			<bean:write name="element" property="phone1"/>
		</td>						
								
		<td class="<%=styleClass%>">	
			<bean:write name="element" property="fax"/>
		</td>						
	</tr>
	</logic:iterate>
</table>
					
<%@ include file="/jsp/include/posFooter.jsp" %>
	   
	
												

