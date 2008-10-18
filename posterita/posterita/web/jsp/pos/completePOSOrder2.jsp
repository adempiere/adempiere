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

<!--completePOSOrder2.jsp-->
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

<bean:define id="title"><pos:message textOnly="true" key="smenu.cash.sales.customer.complusory"/></bean:define>
<%@ include file="/jsp/include/completePOSOrderIncl.jsp" %>

<script>
function createNewOrder()
{
	window.location = "CreatePOSOrder2.do";
}

function showDocumentPDF(id)
{
	window.open('ViewDocumentPDFAction.do?action=viewInvoiceFromOrderDocumentPDF&documentId=' + id, '_blank', 'width=800, height=600, status=no, toolbar=no, location=no, resizable=yes');
}
</script>

