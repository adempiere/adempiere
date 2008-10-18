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



<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.Constants" %>


<%@ page import="org.posterita.struts.pos.POSProductAction" %>
<%@ page import="org.posterita.beans.ProductBean" %>
<%@ page import="org.posterita.user.WebUserInfo" %>
<%@ page import="org.posterita.lib.UdiConstants" %>
<%@ page import="org.posterita.struts.pos.POSGoodsAction" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

<logic:notPresent name="<%=WebUserInfo.NAME%>" scope="session">
	<jsp:forward page="/LoginHome.do"/>
</logic:notPresent>

<bean:define id="title"><pos:message textOnly="true" key="Product"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %> 

										
<table class="view" width="300px">
<tr>
<td>
	<label><pos:message key="ProductName"/></label>
</td>
<td>
   <bean:write name="<%=Constants.PRODUCT_DETAILS%>" property="productName"/>
</td>
</tr>

<tr>
	<td>
		<label><pos:message key="barcode"/></label>
	</td>
	<td>
		<bean:write name="<%=Constants.PRODUCT_DETAILS%>" property="barCode"/>
	</td>
</tr>
	
<tr>		
	<td>
		<label><pos:message key="Description"/></label>
	</td>
	<td>
	 <bean:write name="<%=Constants.PRODUCT_DETAILS%>" property="description"/>
	</td>
</tr>
<tr>		
	<td>
		<label><pos:message key="purchase.price"/></label>
	</td>
	<td>
	 <bean:write name="<%=Constants.PRODUCT_DETAILS%>" property="purchasePriceStandard"/>
	</td>
</tr>
<tr>		
	<td>
		<label><pos:message key="sales.price"/></label>
   </td>
   <td>
	  <bean:write name="<%=Constants.PRODUCT_DETAILS%>" property="salesPriceStandard"/>
	</td>
</tr>
	
<tr>		
	<td>
		<label><pos:message key="C_RevenueRecognition_ID"/></label>
   </td>
   <td>
	  <bean:write name="<%=Constants.PRODUCT_DETAILS%>" property="revenueRecognition"/>
	</td>
</tr>
<tr>		
	<td>
		<label><pos:message key="IsActive"/></label>
   </td>
   <td>
	  <bean:write name="<%=Constants.PRODUCT_DETAILS%>" property="isActive"/>
	</td>
</tr>
<tr>		
	<td>
		<label><pos:message key="ProductType"/></label>
   </td>
   <td>
	  <bean:write name="<%=Constants.PRODUCT_DETAILS%>" property="productType"/>
	</td>
</tr>
<tr>		
	<td>
		<label><pos:message key="Group1"/></label>
   </td>
   <td>
	  <bean:write name="<%=Constants.PRODUCT_DETAILS%>" property="group1"/>
	</td>
</tr>
<tr>		
	<td>
		<label><pos:message key="Group2"/></label>
   </td>
   <td>
	  <bean:write name="<%=Constants.PRODUCT_DETAILS%>" property="group2"/>
	</td>
</tr>		  	
</table>	
			    									 					 
<%@ include file="/jsp/include/posFooter.jsp" %>
