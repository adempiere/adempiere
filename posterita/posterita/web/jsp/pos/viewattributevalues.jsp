<!--viewattributevalues.jsp-->
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
 * @author Ashley
--%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.beans.*" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos" %>

<logic:notPresent name="<%=WebUserInfo.NAME%>" scope="session">
	<jsp:forward page="/POSLogin.do"/>
</logic:notPresent>

<bean:define id="title"><pos:message key="Attribute" textOnly="true"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %>

	<html:form action="/POSAttributesAction">
	<html:hidden property="action" value="listAttributeValues"/>
	<div>
		<label><pos:message key="choose.attribute"/></label>
		<html:select property="attributeId" onchange="submit()">
			<logic:iterate indexId="count" id="element" name="<%=Constants.ATTRIBUTE_LIST%>" type="org.compiere.util.KeyNamePair">
				<html:option value="<%=String.valueOf(element.getKey())%>"><%=element.getName()%></html:option>
			</logic:iterate>
		</html:select>
		<html:submit><pos:message textOnly="true" key="list"/></html:submit>
	</div>
	<div class="space"></div>
	<div class="space"></div>	
	<div style="width:500px">
	<table class="display" border="1">
	<logic:present name="<%= Constants.ATTRIBUTE_VALUES_LIST%>">
		<logic:iterate id="element" indexId="count" name="<%= Constants.ATTRIBUTE_VALUES_LIST%>" type="org.compiere.util.KeyNamePair">
		<%
			String style = "label";
			if((count.intValue() % 2) == 0)
				style = "contentname";
		%>
			<tr>
				<td class="<%= style%>">
					<bean:write name="element" property="name"/>
				</td>
				<td align="center" class="<%= style%>" width="50px;">
					
					<%
						String editLink = "EditPOSAttributeValueAction.do?action=initEditAttributeValue";
						editLink += "&attributeValueId=" + element.getKey() + "&name=" + element.getName()+ "&attributeId=";
					%>
					<a href="<%= editLink%><bean:write name='AttributeValuesForm' property='attributeId'/>"><pos:message key="edit"/></a>
				</td>
				
			</tr>
		</logic:iterate>
	</logic:present>
	</table>
	</div>
	
	</html:form>
<%@ include file="/jsp/include/posFooter.jsp" %>					