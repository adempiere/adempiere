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


<!--viewDebtors.jsp-->
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.beans.BPartnerInfoBean" %>
<%@ page import="org.posterita.user.WebUserInfo" %>
<%@ page import="org.posterita.lib.UdiConstants" %>
<%@ page import="org.posterita.struts.pos.BpartnerInfoAction" %>
<%@ page import="org.posterita.struts.pos.DunningAction" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos" %>

<logic:notPresent name="<%=WebUserInfo.NAME%>" scope="session">
	<jsp:forward page="/LoginHome.do"/>
</logic:notPresent>

<bean:define id="title"><pos:message key="smenu.debtors" textOnly="true"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %> 

<logic:present name="<%=Constants.DEBTORS%>">
<logic:notEmpty name="<%=Constants.DEBTORS%>">

<br><br>

<div class="scrollpane">			
<table class="display sortable" border="1" id="1111">
	<tr>
		<th><pos:message key="Name"/></th>
		<th><pos:message key="IsCustomer"/></th>
		<th><pos:message key="IsVendor"/></th>
		<th class="currency"><pos:message key="Revenue"/></th>
		<th class="currency"><pos:message key="SO_CreditLimit"/></th>
		<th class="currency"><pos:message key="SO_CreditUsed"/></th>
		<th class="currency"><pos:message key="OpenAmt"/></th>
	</tr>	
	
<%
	String collection = Constants.DEBTORS;
	String url = "/jsp/pos/viewDebtors.jsp";
%>	
	
	<logic:iterate id="element" offset="<%=offset%>" length="<%=length%>" indexId="count" name="<%=collection %>" type="org.posterita.beans.BPartnerBean">
	
	<tr>	
		<%
			String styleClass = "label";
			if ((count.intValue()%2) != 0)
				styleClass = "contentname";
		%>						
	   <td class=<%=styleClass%>>
	   			
	   		<html:link href="<%="CreditorDebtorAction.do?action=getDebtorHistory&bpartnerId=" + element.getBpartnerId() %>">
				<bean:write name="element" property="partnerName"/>
			</html:link>		
	   </td>
	   <td class=<%=styleClass%>>
			<bean:write name="element" property="isCustomer"/>
	   </td>
	   <td class=<%=styleClass%>>
			<bean:write name="element" property="isVendor"/>
	   </td>						
	   <td class=<%=styleClass%>>
			<bean:write name="element" property="actualLifetimeValue"/>
	   </td>						   
	   <td class=<%=styleClass%>>
			<bean:write name="element" property="creditLimit"/>
	   </td>						   
	   <td class=<%=styleClass%>>	   		
			<bean:write name="element" property="creditUsed"/>
	   </td>
	   <td class=<%=styleClass%>>
			<bean:write name="element" property="openBalance"/>
	   </td>	  			
	</tr>
	</logic:iterate> 

</table>

<%@ include file="/jsp/include/pager.jsp" %>

</div>

</logic:notEmpty>
</logic:present>


	    									 					 
<%@ include file="/jsp/include/posFooter.jsp" %>
