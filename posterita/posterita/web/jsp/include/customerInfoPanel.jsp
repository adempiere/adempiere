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

<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>
<fieldset>
<legend><pos:message key="customer.info"/></legend>
<div style="height:160px">
	<div>
		<label><pos:message key="customer"/></label>
	</div>
	<html:text property="bpartnerId" styleClass="text" accesskey="i"/> 
	<div>
		<label><pos:message key="search.customer"/></label>
	</div>
	
	<%@ include file="/jsp/include/searchCustomerPanel.jsp" %>
	<div class="space"></div>						
	<html:text property="partnerName" readonly="true" styleClass="text"/>
	<div class="space"></div>							
	<input type="button" value='<pos:message textOnly="true" key="new.customer"/>' onclick="createCustomer()" class="newbutton" accessKey="n"/>
	<input type="button" value="Default" onclick="defaultCustomer()" class="newbutton"/>							
</div>
</fieldset>
