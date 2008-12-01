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
<%@ page import="org.posterita.user.WebUserInfo" %>
<%@ page import="org.compiere.process.DocumentEngine" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/dcs.tld" prefix="dcs" %>

<logic:notPresent name="<%=WebUserInfo.NAME%>" scope="session">
	<jsp:forward page="/LoginHome.do"/>
</logic:notPresent>


<tiles:insert page="/jsp/include/headerTableTop.jsp">
  	<tiles:put name="title"><bean:message key = "shipment.material.receipt"/></tiles:put>
</tiles:insert>
<%@ include file="/jsp/include/tabTop.jsp" %><bean:write name="<%=Constants.WEB_DOCUMENT_HEADER_BEAN%>" property="documentHeader"/><%@ include file="/jsp/include/tabBottom.jsp" %>
<html:form action="/ChangeMInOutStatusAction" onsubmit="return getAcceptPermission()">	

	<table width="100%" border="0" cellpadding="5" cellspacing="0">
            <%@ include file="/jsp/include/errors.jsp" %>
		<tr>
			<td>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">									
					<tr>
						<td>
							
						</td>
						
						<td colspan="2" align="right">
							<html:link href="#" onclick="window.open('PrintShipment.do','Print Shipment','width=700,height=600,scrollbars=yes,menubar=yes,toolbar=yes,resizable=no');">
				  				<img src="images/ico_printer.gif" border="0"/>
				  			</html:link>						
						</td>			
					</tr>
				</table>
			</td>
		</tr>
		
		<%@ include file="/jsp/inOut/webStoreMinOutDetails.jsp" %>
		
		<tr>
	    	<td>
				<table width="100%" border="0" cellpadding="5" cellspacing="0">
					<tr>
						<td align="right">
							<table>
								<tr>
									<logic:present name="<%=Constants.SIMPLE_COMMAND%>">
							    	<logic:iterate name="<%=Constants.SIMPLE_COMMAND%>" id="simpleElement" type="java.lang.String">								    	
								    	<td align="right">
											<!--<html:submit property="submit" value="<%=simpleElement%>" styleClass="button"/>-->
										</td>
							    	</logic:iterate>
							    	</logic:present>
							    	
							    	<logic:present name="<%=Constants.COMPLEX_COMMAND%>">
							    	<logic:iterate name="<%=Constants.COMPLEX_COMMAND%>" id="complexElement" type="java.lang.String">								    	
								    	<td align="right">
											<!--<html:submit property="submit" value="<%=complexElement%>" styleClass="button"/>-->
										</td>
							    	</logic:iterate>
							    	</logic:present>
							    	
							    	</html:form>		
								</tr>
							</table>	
						</td>	
					</tr>
				</table>	
			</td>
		</tr>
		
	</table>

<%@ include file="/jsp/include/footerTableBottom.jsp" %>
