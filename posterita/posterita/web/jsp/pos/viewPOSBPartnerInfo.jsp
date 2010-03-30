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
 * @author Alok
--%>



<!--viewPOSBPartnerInfo.jsp-->
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.beans.BPartnerInfoBean" %>
<%@ page import="org.posterita.user.WebUserInfo" %>
<%@ page import="org.posterita.lib.UdiConstants" %>
<%@ page import="org.posterita.struts.pos.BpartnerInfoAction" %>
<%@ page import="org.posterita.struts.pos.POSGoodsAction" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos" %>

<logic:notPresent name="<%=WebUserInfo.NAME%>" scope="session">
	<jsp:forward page="/LoginHome.do"/>
</logic:notPresent>

<bean:define id="title"><pos:message key="smenu.bpartner.sales.details" textOnly="true"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %> 



<logic:present name="<%=Constants.BUSINESSPARTNER%>">
<logic:empty name="<%=Constants.BUSINESSPARTNER%>">
	<div><pos:message key="search.bpartner.notfound"/> : <b><bean:write name="BPartnerInfoForm" property="partnerName"/></b></div>
	<div class="space"></div>
</logic:empty>
</logic:present>

<html:form action="/ViewBPartnerInfoAction">
<html:hidden property="action" value="<%=BpartnerInfoAction.GET_BPARTNER_INFO %>"/>	
<table  border="0">
	<tr>
	<td>
		<label><pos:message key="search"/></label>
		<html:text property="partnerName" styleClass="text"/>
		<html:submit styleClass="search smallbutton">&nbsp;</html:submit>
	</td>
	<td align="right">
		<label><pos:message key="select.bpartner.type"/>:</label>
		<html:select property="isCustomer" onchange="submit()">
			<html:option value="true"><pos:message key="IsCustomer"/></html:option>	
			<html:option value="false"><pos:message key="IsVendor"/></html:option>
		</html:select>
	</td>	
	</tr>	
</table>
<br>
<logic:present name="<%=Constants.BUSINESSPARTNER%>">
<logic:notEmpty name="<%=Constants.BUSINESSPARTNER%>">
<%
	String url = "ViewPOSBPartnerInfo.do";
	String collection = Constants.BUSINESSPARTNER;
%>
<table class="display sortable" border="1" id="1111">
	<tr>
		<th><pos:message key="Name"/></th>
		<th><pos:message key="Address1"/></th>
		<th><pos:message key="Address2"/></th>
		<th><pos:message key="City"/></th>
		<th><pos:message key="Phone"/></th>
		<th><pos:message key="Phone2"/></th>
		<th><pos:message key="Fax"/></th>
	</tr>		
	<logic:iterate offset="<%=offset%>" length="<%=length%>" id="element" indexId="count" name="<%=Constants.BUSINESSPARTNER %>" type="org.posterita.beans.BPartnerInfoBean">
	<tr>	
		<%
			String styleClass = "label";
			if ((count.intValue()%2) != 0)
				styleClass = "contentname";
		%>
		
						
	   <td class=<%=styleClass%>>
	   			
	   		<html:link href="ViewBPartnerTrxDetailsAction.do?action=getBpartnerTrxDetails&isCustomer=<%= element.getIsCustomer() %>&bpartnerId=<%= element.getBpartnerId() %>">
				<bean:write name="element" property="partnerName"/>
				
			</html:link>		
	   </td>						
	   <td class=<%=styleClass%>>
			<bean:write name="element" property="address1"/>
	   </td>						   
	   <td class=<%=styleClass%>>
			<bean:write name="element" property="address2"/>
	   </td>						   
	   <td class=<%=styleClass%>>
			<bean:write name="element" property="city"/>
	   </td>
	   <td class=<%=styleClass%>>
			<bean:write name="element" property="phone"/>
	   </td>
	   <td class=<%=styleClass%>>
			<bean:write name="element" property="phone2"/>
	   </td>
	   <td class=<%=styleClass%>>
			<bean:write name="element" property="fax"/>
	   </td>						
	</tr>
	</logic:iterate> 	
</table>
<%@ include file="/jsp/include/pager.jsp" %>
</logic:notEmpty>
</logic:present>
</html:form>
									 					 
<%@ include file="/jsp/include/posFooter.jsp" %>
