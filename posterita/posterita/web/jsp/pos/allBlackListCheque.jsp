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
 * @author Martine
--%>

<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.compiere.model.MOrg" %>
<%@ page import="org.compiere.model.MBlackListCheque" %>
<%@ page import="org.posterita.beans.BlackListedBean" %>
<%@ page import="org.posterita.struts.pos.BlackListedAction" %>


<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

<bean:define id="title"><pos:message textOnly="true" key="black.listed.cheques"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
	 

<div align="right">
	<TABLE>
		<tr>
		<td>
			<html:link href="/jsp/pos/createBlackListCheque.jsp"> <pos:message key="add.record"/> </html:link>
		</td>
		</tr>
		<tr>
		<!-- 
		<td>
			<html:link href="/jsp/pos/importBlackList.jsp"> <pos:message key="import.list"/> </html:link>
		</td> -->
		
		</tr>
		
	</TABLE>
</div>

<div>
	<html:form action="BlackListChequeAction" focus="chequeNo">
	<html:hidden property="action" value="searchCheque"/>
	<html:text property="chequeNo" styleClass="text"/>
	
	<html:submit styleClass="search smallbutton">
	&nbsp;</html:submit>
	</html:form>
</div>


<logic:present name="<%=Constants.BLACKLISTED_LISTS%>">
<logic:empty name="<%=Constants.BLACKLISTED_LISTS%>">
	<div><pos:message key="no.customer.was.found.for"/><b><bean:write name="BlackListForm" property="blackListedChequeNo"/></b></div>
	<div class="space"></div>
</logic:empty>

<div class="scrollpane">		  		
<table class="display sortable" border="1" id="111"> 		  		
<tr>
	<th><pos:message key="BankName"/></th>
	<th><pos:message key="CheckNo"/></th>
	<th>&nbsp;</th>
</tr>
<logic:iterate indexId="count" name="<%=Constants.BLACKLISTED_LISTS%>" type="org.posterita.beans.BlackListedBean" id="element" offset="0">	
	<%
		String styleClass = "label";
		
		if ((count.intValue()%2) != 0)
			styleClass = "contentname";
	%>
	
	<tr>
		<td class="<%=styleClass%>">
			<bean:write name="element" property="blackListedBankName"/>
		</td>	
		
		<td class="<%=styleClass%>">
			<bean:write name="element" property="blackListedChequeNo"/>
		</td>
		
		<td class="<%=styleClass%>">
		<bean:define id="blackListedId" name="element" property="blackListedId"/>
			<html:link href="<%="BlackListChequeAction.do?action=initEditCheque&blackListedId="+ blackListedId%>">Edit</html:link>
			&nbsp;
			<html:link href="<%="BlackListChequeAction.do?action=deactivateCheque&blackListedId=" + blackListedId%>">Delete</html:link>
						
		</td>						
	</tr>
</logic:iterate>					

</table>
</div></logic:present>
			
<%@ include file="/jsp/include/posFooter.jsp" %> 
		  