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

<!-- viewBPartner.jsp -->
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.beans.*" %>
<%@ page import ="org.posterita.Constants" %>
<%@ page import="org.compiere.model.MOrg" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

<bean:define id="title"><pos:message key="smenu.bpartners" textOnly="true"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %> 

<div>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>		
			<td align="left">
				<html:form action="SearchBPartnerAction" focus="name">
					<html:hidden property="action" value="searchBPartner"/>
					<html:text property="name" styleClass="text searchBox"/>
				
					<html:submit styleClass="tangoSearch tangoButton">
					&nbsp;
					</html:submit>
				</html:form>
			</td>
			
				
			<td align="right">	
				<html:link href="createBPartner.do">
					<img src="images/tango/document-new.png" title="Add Business Partner" alt="Add Business Partner" border="0">
				</html:link>
			</td>
		</tr>
	</table>
</div>
	   	
<logic:present name="<%=Constants.ALLBUSINESSPARTNERS%>">
<table class="display sortable" border="1" id="1111"> 		  		
	<tr>
		<th colspan="1">
			<pos:message key="Name" textOnly="true"/>
		</th>
		<th colspan="1">
			<pos:message key="isCustomer" textOnly="true"/>
		</th>
		<th colspan="1">
			<pos:message key="IsEmployee" textOnly="true"/>
		</th>
		<th colspan="1">
			<pos:message key="IsVendor" textOnly="true"/>
		</th>
		<th colspan="1">
			<pos:message key="IsSalesRep" textOnly="true"/>
		</th>
		<th colspan="1">
			<pos:message key="IsActive" textOnly="true"/>
		</th>
		<th colspan="1">
			&nbsp;
		</th>						
	</tr>
	
	<%
		String url = "BusinessPartners.do";
		String collection = Constants.ALLBUSINESSPARTNERS;
	%>
	
	<logic:iterate offset="<%=offset%>" length="<%=length%>" indexId="count" id="element" name="<%=Constants.ALLBUSINESSPARTNERS%>" type="org.posterita.beans.BPartnerBean">  
	<tr>
		<%
			String styleClass = "label";
			if ((count.intValue()%2) != 0)
				styleClass = "contentname";
		%>
	
	
		<td class="<%=styleClass%>">
			<bean:write name="element" property="partnerName"/>
			&nbsp;<bean:write name="element" property="name2"/>
		</td>
		
		<td class="<%=styleClass%>">
			<logic:equal name="element" property="isCustomer" value="true"> 
				Yes
			</logic:equal>
			<logic:equal name="element" property="isCustomer" value="false"> 
				No
			</logic:equal>						
		</td>
		
		<td class="<%=styleClass%>">
			<logic:equal name="element" property="isEmployee" value="true"> 
				Yes
			</logic:equal>
			<logic:equal name="element" property="isEmployee" value="false"> 
				No
			</logic:equal>						
		</td>
		
		<td class="<%=styleClass%>">
			<logic:equal name="element" property="isVendor" value="true"> 
				Yes
			</logic:equal>
			<logic:equal name="element" property="isVendor" value="false"> 
				No
			</logic:equal>						
		</td>
		
		<td class="<%=styleClass%>">
			<logic:equal name="element" property="isSalesRep" value="true"> 
				Yes
			</logic:equal>
			<logic:equal name="element" property="isSalesRep" value="false"> 
				No
			</logic:equal>						
		</td>
		
		<td class="<%=styleClass%>">
			<logic:equal name="element" property="isActive" value="true"> 
				Yes
			</logic:equal>
			<logic:equal name="element" property="isActive" value="false"> 
				No
			</logic:equal>						
		</td>
		
		<td class="<%=styleClass%>">
			<html:link href="<%="EditBPartnerAction.do?action=viewBpartnerDetails&bpartnerId=" + element.getBpartnerId()%>">
 					<img src="images/tango/edit-find.png" title="View Business Partner" alt="View Business Partner" border="0">
 				</html:link>
 				&nbsp;
 				<html:link href="<%="InitEditBPartnerAction.do?action=initEditBpartnerDetails&bpartnerId=" + element.getBpartnerId()%>">
 					<img src="images/tango/accessories-text-editor.png" title="Edit Business Partner" alt="Edit Business Partner" border="0">
 				</html:link>
 				&nbsp;
 			
 				<logic:equal name="element" property="isActive" value="true">
					<html:link href="<%="ActivateBPartnerAction.do?action=activateBPartner&bpartnerId=" + element.getBpartnerId()+"&isActive=true" %>">
						<img src="images/tango/edit-redo.png" title="Deactivate" alt="Deactivate" border="0">
					</html:link>
				</logic:equal>

				<logic:notEqual name="element" property="isActive" value="true">
					<html:link href="<%="ActivateBPartnerAction.do?action=activateBPartner&bpartnerId=" + element.getBpartnerId()+ "&isActive=false" %>">
						<img src="images/tango/edit-undo.png" title="Activate" alt="Activate" border="0">
					</html:link>
				</logic:notEqual>
		</td>
		
	</tr>		
</logic:iterate>
</table>
<%@ include file="/jsp/include/pager.jsp" %>
</logic:present>

<%@ include file="/jsp/include/posFooter.jsp" %>
	   	

