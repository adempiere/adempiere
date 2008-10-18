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

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos" %>

<bean:define id="title"><pos:message key="vendors" textOnly="true"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>



<div align="right">
	<TABLE border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
		
			<td align="left">
				<html:form action="SearchVendor" focus="name">
					<html:hidden property="action" value="searchVendors"/>
					<html:text property="name" styleClass="text"/>
				
					<html:submit styleClass="tangoSearch tangoButton">
					&nbsp;
					</html:submit>
				</html:form>
			</td>
			
				
			<td align="right">	
				<html:link href="SaveVendor.do">
					<img src="images/tango/document-new.png" title="Add Vendor" alt="Add Vendor" border="0">
				</html:link>
			</td>

		</tr>
		
	</TABLE>
</div>

<logic:present name="<%=Constants.VENDOR_LIST%>">
<logic:empty name="<%=Constants.VENDOR_LIST%>">
	<div><pos:message key="no.vendor.was.found.for"/>: <b><bean:write name="DefaultForm" property="name"/></b></div>
	<div class="space"></div>
</logic:empty>
</logic:present>

<logic:present name="<%=Constants.VENDOR_LIST%>">
<logic:notEmpty name="<%=Constants.VENDOR_LIST%>">

<%
	String url = "ViewAllVendors.do";
	String collection = Constants.VENDOR_LIST;
%>	

<table class="display sortable scrollpane" border="1" id="vendor"> 		  		
<tr>
	<th><pos:message key="Name"/></th>		
	<th><pos:message key="Address1"/></th>
	<th><pos:message key="Address2"/></th>
	<th><pos:message key="Phone"/></th>
	<th><pos:message key="Fax"/></th>			
	<th><pos:message key="IsActive"/></th>
	<th>&nbsp;</th>
</tr>
<bean:define id="editmsg">
	<pos:message key="edit.vendor" textOnly="true"/>
</bean:define>
<bean:define id="viewmsg">
	<pos:message key="view.vendor" textOnly="true"/>
</bean:define>
<bean:define id="activatemsg">
	<pos:message key="activate.vendor" textOnly="true"/>
</bean:define>
<bean:define id="deactivatemsg">
	<pos:message key="deactivate.vendor" textOnly="true"/>
</bean:define>

<logic:iterate offset="<%=offset%>" length="<%=length%>" indexId="count" name="<%=collection%>" id="element" type="org.posterita.beans.VendorBean" >	
<%
	String styleClass = "label";
	
	if ((count.intValue()%2) != 0)
		styleClass = "contentname";
%>
<tr>
	<td class="<%=styleClass%>">
		<bean:write name="element" property="partnerName"/>
	</td>	
	
	<td class="<%=styleClass%>">
		<bean:write name="element" property="address1"/>
	</td>
	<td class="<%=styleClass%>">
		<bean:write name="element" property="address2"/>
	</td>
	<td class="<%=styleClass%>">
		<bean:write name="element" property="phone"/>
	</td>
	<td class="<%=styleClass%>">
		<bean:write name="element" property="fax"/>
	</td>
								
	<td class="<%=styleClass%>">
		<logic:equal name="element" property="isActive" value="true">
		Y
		</logic:equal>
		<logic:equal name="element" property="isActive" value="false">
		N
		</logic:equal>
		
	</td>
	
	<td nowrap="nowrap" class="<%=styleClass%>">
		<html:link href="<%="POSVendorAction.do?action=initEditVendor&bpartnerId=" + element.getBpartnerId()%>">
			<img src="images/tango/accessories-text-editor.png" title='<%= editmsg %>' alt='<%=editmsg%>' border="0">
		</html:link>
		
		<html:link href="<%="POSVendorAction.do?action=viewVendorDetails1&bpartnerId=" + element.getBpartnerId()%>">
			<img src="images/tango/edit-find.png" title='<%=viewmsg%>' alt='<%=viewmsg%>' border="0">
		</html:link>
		
		<logic:equal name="element" property="isActive" value="true">
		<html:link href="<%="POSVendorAction.do?action=deactivateVendor&bpartnerId=" + element.getBpartnerId()%>">
			<img src="images/tango/edit-redo.png" title='<%=deactivatemsg%>' alt='<%=deactivatemsg%>' border="0">
		</html:link>
		</logic:equal>

		<logic:notEqual name="element" property="isActive" value="true">
		<html:link href="<%="POSVendorAction.do?action=activateVendor&bpartnerId=" + element.getBpartnerId()%>">
			<img src="images/tango/edit-undo.png" title='<%=activatemsg%>' alt='<%=activatemsg%>' border="0">
		</html:link>
		</logic:notEqual>
	</td>
</tr>
</logic:iterate>
</table>

<%@ include file="/jsp/include/pager.jsp" %>

</logic:notEmpty>

</logic:present>
					
<%@ include file="/jsp/include/posFooter.jsp" %>    
	
												