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



<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.beans.ProductDetailsBean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

<logic:present name="<%= Constants.PRODUCT_DETAIL_INFO%>">
	<table class="popup">
		<tr>
		<logic:equal name="<%= Constants.PRODUCT_DETAIL_INFO%>" property="hasImage" value="true">
			<td>
				<table cellspacing="0" cellpadding="0">
					<tr>
						<% 
							ProductDetailsBean prodDetBean = (ProductDetailsBean)request.getAttribute(Constants.PRODUCT_DETAIL_INFO);
							String imgSrc = "ViewProductInfo.do?size=THUMB&productId=" + prodDetBean.getProductId();
						%>
						<td><img src="<%= imgSrc%>" border="1" width="120" height="120"></td>
					</tr>
				</table>
			</td>
		</logic:equal>
		<td>
			<table cellspacing="0" cellpadding="0">
				<tr>
					<td><pos:message key="Name"/>: </td>
					<td> <bean:write name="<%= Constants.PRODUCT_DETAIL_INFO%>" property="productName"/> </td>
				</tr>
				<tr>
					<td><pos:message key="Description"/>: </td>
					<td> <bean:write name="<%= Constants.PRODUCT_DETAIL_INFO%>" property="description"/> </td>
				</tr>
				<tr>
					<td><pos:message key="Barcode"/>: </td>
					<td> <bean:write name="<%= Constants.PRODUCT_DETAIL_INFO%>" property="barCode"/> </td>
				</tr>
				<tr>
					<td><pos:message key="C_TaxCategory_ID"/>: </td>
					<td> <bean:write name="<%= Constants.PRODUCT_DETAIL_INFO%>" property="taxCategoryName"/> </td>
				</tr>
				<tr>
					<td><pos:message key="C_RevenueRecognition_ID"/>:</td>
					<td> <bean:write name="<%= Constants.PRODUCT_DETAIL_INFO%>" property="revenueRecognition"/> </td>
				</tr>
			</table>
		</td>
		</tr>
	</table>
</logic:present>