<%--
 *  Product: Posterita Web-Based POS (an Adempiere Plugin)
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


<!--viewVendorDetails.jsp-->
<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.beans.UDIBean" %>
<%@ page import="org.posterita.core.MenuItem" %>


<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

<bean:define id="title"><pos:element textOnly="true" columnName="vendor.details"/></bean:define>
<jsp:include page="/jsp/include/posHeader.jsp"/>

<table class="main">
	<tr>
		<td>
			<fieldset>
				<legend><pos:element columnName="IsMenuContact" textOnly="true"/></legend>
				<table>
					<tr>
						<td><label><pos:element columnName="Name"/>:</label></td>
						<td><bean:write name="<%=Constants.VENDOR_DETAILS%>" property="partnerName"/></td>
					</tr>
				</table>
			</fieldset>
		</td>
	</tr>

<tr>
	<td>
	<fieldset style="height:180px;">
	<legend><pos:element columnName="Address" textOnly="true"/></legend>	
	<table>
		<tr>
			<td><label><pos:element columnName="Address1"/>:</label></td>
			<td><label><bean:write name="<%=Constants.VENDOR_DETAILS%>" property="address1"/></label></td>
		</tr>
		<tr>
			<td><label><pos:element columnName="Address2"/>:</label></td>
			<td><label><bean:write name="<%=Constants.VENDOR_DETAILS%>" property="address2"/></label></td>
		</tr>
		<tr>
			<td><label><pos:element columnName="City"/>:</label></td>
			<td><label><bean:write name="<%=Constants.VENDOR_DETAILS%>" property="city"/></label></td>
		</tr>
	
		<tr>
			<td><label><pos:element columnName="Email"/>:</label></td>
			<td><label><bean:write name="<%=Constants.VENDOR_DETAILS%>"property="email"/></label></td>
		</tr>
	</table>
	</fieldset>
	</td>

	<td>
	<fieldset style="height:180px;">
		<legend><pos:element columnName="Phone" textOnly="true"/></legend>	
		<table>
			<tr>
				<td><label><pos:element columnName="Phone"/>:</label></td>
				<td><label><bean:write name="<%=Constants.VENDOR_DETAILS%>" property="phone"/></label></td>
			</tr>
			<tr>
				<td><label><pos:element columnName="Phone2"/>:</label></td>
				<td><label><bean:write name="<%=Constants.VENDOR_DETAILS%>" property="phone2"/></label></td>
			</tr>
			<tr>
				<td><label><pos:element columnName="Fax"/>:</label></td>
				<td><label><bean:write name="<%=Constants.VENDOR_DETAILS%>" property="fax"/></label></td>
			</tr>
			</tr>
			<tr><td>&nbsp;</td></tr>
	</table>
	</fieldset>
	</td>
</tr>

</table>
							 					 
<%@ include file="/jsp/include/posFooter.jsp" %>