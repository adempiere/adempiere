<%--
 *  Product: Posterita Web-Based POS and Adempiere Plugin
 *  Copyright (C) 2008  Posterita Ltd
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
 *  @author sendy
--%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>	
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>
<%@page import="org.posterita.struts.pos.POSProductAction"%>
<%@page import="org.posterita.Constants"%>
<%@page import="org.compiere.model.MProduct"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="org.posterita.beans.ProductBean"%>
	
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

	<base href="http://localhost:8080/posterita/">
	<title>Ashish</title>				
	<script src="js/enableButton.js"></script>				
	<script src="js/pos.js"></script>		
	<script src="javascripts/prototype.js"></script>
 	<script src="javascripts/scriptaculous.js"></script>

 	<script src="js/shortcut.js"></script>
 	<script src="js/tooltip.js"></script>
 	
 	<link rel="shortcut icon" href="images/posterita.jpg" type="image/jpg" /> 
 	<link type="text/css" href="css/common.jsp" rel="stylesheet">
 	<link type="text/css" href="css/js-calendar/calendar-win2k-1.css" rel="stylesheet">
 	<link href="/stylesheets/themes/default.css" rel="stylesheet" type="text/css"/>
 	<!--  link type="text/css" href="css/screen.css" rel="stylesheet"  -->

</head>
<body>	
<div id="content">

<logic:equal value="0" name="POSProductForm" property="productId">
	<font class="pagetitle">Create Product</font>
</logic:equal>
<logic:notEqual value="0" name="POSProductForm" property="productId">
	<font class="pagetitle">Update Product</font>
</logic:notEqual>
	
<%@ include file="/jsp/include/errors.jsp" %>
<!-- page contents -->
	<html:form action="/POSProductAction3">
	<html:hidden property="action" value="<%=POSProductAction.CREATE_OR_UPDATE_PRODUCT%>"/>	
	<bean:define id="productId" name="POSProductForm" property="productId" ></bean:define>
	<table>	
		<tr>			
			<td colspan="4" align="right">
			<html:link href="<%="POSProductAction3.do?action=getCreateProductForm&productId=0"%>">
				<img src="images/tango/document-new.png" title="Create Product" alt="Create Product" border="0">
			</html:link>
			</td>
		</tr>	
		<tr>
			<td>
				<label><pos:message key="barcode"/></label>
			</td>
			
			<td colspan="3">
				<html:text property="barCode" styleClass="text"/>
			</td>
		</tr>
		<tr>		
			<td>
				<label><pos:message key="ProductName"/></label><mandatory>*</mandatory>
			</td>
			<td colspan="3">
				<html:text  property="productName" styleClass="text" />		    
			    <html:hidden  property="productId"/>
			</td>
		</tr>
		<tr>		
			<td>
				<label><pos:message key="Description"/></label><mandatory>*</mandatory>
			</td>
			<td colspan="3">
			    <html:text property="description"  styleClass="text"/>
			</td>
		</tr>
		<tr>
			<td>
				<label><pos:message key="smenu.organisation"/>:</label><mandatory>*</mandatory>
			</td>
		
			<td>
				<html:select property="orgId" styleClass="text">
					<html:options collection="<%=Constants.USER_ORGS%>" property="key" labelProperty="name"/>
			 	</html:select>
			</td>
		</tr>
		
		<tr>
			<td>
	  			<label><pos:message key="C_TaxCategory_ID"/></label><mandatory>*</mandatory>  								
			</td>
		   	<td colspan="3">	   	  
				<html:select property="taxCategoryId" styleClass="text" onchange="updateTax()">										
					<html:options collection="<%= Constants.TAX_CATEGORY_ID %>" property="taxCategoryId" labelProperty="taxCategoryName" />																																	
			   </html:select>
			   
			 </td>				
	   	</tr>
	   	<tr>
			<td>
	  			<label><pos:message key="uom"/></label><mandatory>*</mandatory>  								
			</td>
		   	<td colspan="3">	   	  
				<html:select property="uomId" styleClass="text">										
					<html:options collection="<%= Constants.UOM_LIST %>" property="ID" labelProperty="name"/>																																	
			   </html:select>
			 </td>				
	   	</tr>
	   	
	   	<tr>
			<td>
			 <label><pos:message key="C_RevenueRecognition_ID"/></label><mandatory>*</mandatory>
			</td>
			<td colspan="3">				
				<html:text  property="revenueRecognition"/>														
		   </td>
       </tr>  
        <tr>		
			<td>
				<label><pos:message key="IsActive"/></label>
			</td>
			<td colspan="3">
				 <html:checkbox  property="isActive" value="true"/>
			</td>
		</tr>
	    <tr>
      		 <td>
			 	<label><pos:message key="ProductType"/></label>
			</td>
		
	    	<td colspan="3">
	    		<html:radio property="productType"  value="<%=MProduct.PRODUCTTYPE_Item%>"/>
	    		<pos:message key="product.type.item"/>
	    	<br>
	    		<html:radio property="productType" value="<%=MProduct.PRODUCTTYPE_Service%>"/>
	    		<pos:message key="product.type.services"/>
	    	</td>
    	</tr>
    	<tr>
      		 <td>
			 	<label><pos:message key="group1"/></label>
			</td>
		
	    	<td colspan="3">
	    		<html:text  property="group1"/>
	    	</td>
    	</tr>
    	<tr>
      		 <td>
			 	<label><pos:message key="group2"/></label>
			</td>
		
	    	<td colspan="3">
	    		<html:text property="group2"/>
	    	</td>
    	</tr>    	
	</table>
	
	 <div class="scrollpane">
	
	<logic:present name="<%=Constants.PRODUCT_PRICE_LISTS%>">
		<table class="display sortable" border="1">
		<th class="String" nowrap="nowrap" title="Name"><pos:message key="smenu.price.list"/></th>
		<th class="String" nowrap="nowrap" title="Name"><pos:message key="price.list.type"/></th>
		<th class="String" nowrap="nowrap" title="Name" colspan="2"><pos:message key="standard.price"/></th>
		<th class="String" nowrap="nowrap" title="Name" colspan="2"><pos:message key="list.price"/></th>		
		<th class="String" nowrap="nowrap" title="Name" colspan="2"><pos:message key="limit.price"/></th>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td><pos:message key="tax.excl"/></td>
			<td><pos:message key="tax.incl"/></td>
			<td><pos:message key="tax.excl"/></td>
			<td><pos:message key="tax.incl"/></td>
			<td><pos:message key="tax.excl"/></td>
			<td><pos:message key="tax.incl"/></td>	
		</tr>
		<logic:iterate id="productBean" name="<%=Constants.PRODUCT_PRICE_LISTS%>" type="org.posterita.beans.ProductBean" indexId="index">
		<html:hidden name="productBean" property="priceListId" value="<%=productBean.getPriceListId().toString() %>" indexed="true"/>
		<html:hidden name="productBean" property="productId" value="<%=productBean.getProductId().toString()%>" indexed="true"/>
		<html:hidden name="productBean" property="priceListVersionId" value="<%=productBean.getPriceListVersionId().toString()%>" indexed="true"/>
		
		<tr id="<%= "row" + (index.intValue()+1)%>">
			<td><bean:write name="productBean" property="priceListName"/>
				<logic:equal name="productBean" property="isMandatory" value="true">
					<mandatory>*</mandatory>
				</logic:equal>
			</td>
			<td>
				<logic:equal name="productBean" property="isSOPriceList" value="true">
					<label><pos:message key="sales"></pos:message></label>
				</logic:equal>
				<logic:equal name="productBean" property="isSOPriceList" value="false">
					<label><pos:message key="purchase"></pos:message></label>
				</logic:equal>
			</td>
			<td>
				<html:text property="stdPrice" name="productBean" value="<%=productBean.getStdPrice().toString()%>" indexed="true" size="10" styleId="stdPrice"></html:text>
			</td>
			<td>	
				<html:text property="stdPriceIncl" name="productBean" value="<%=productBean.getStdPriceIncl().toString()%>" indexed="true" size="10" styleId="stdPriceIncl"></html:text>
			</td>
			<logic:equal name="productBean" property="isSOPriceList" value="true">
			<td>
				<html:text property="listPrice" name="productBean" value="<%=productBean.getListPrice().toString()%>" indexed="true" size="10" styleId="listPrice"></html:text>
			</td>
			<td>
				<html:text property="listPriceIncl" name="productBean" value="<%=productBean.getListPriceIncl().toString()%>" indexed="true" size="10" styleId="listPriceIncl"></html:text>
			</td>
			<td>
				<html:text property="limitPrice" name="productBean" value="<%=productBean.getLimitPrice().toString()%>" indexed="true" size="10" styleId="limitPrice"></html:text>
			</td>			
			<td>
				<html:text property="limitPriceIncl" name="productBean" value="<%=productBean.getLimitPriceIncl().toString()%>" indexed="true" size="10" styleId="limitPriceIncl"></html:text>
			</td>
			</logic:equal>
			<logic:equal name="productBean" property="isSOPriceList" value="false">
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</logic:equal>
		</tr>
		</logic:iterate>				
		</table>
	</logic:present>	
		<table>
		<tr>	
			 <td align="left">
				<html:submit styleClass="save smallbutton" accesskey="c">&nbsp;</html:submit>
			 </td>
		 	<td colspan="2">&nbsp;</td>	
		</tr>  
	</table>	
	
</div>

</html:form>


<script>
addRequiredLibrary('js/productTax.js');
</script>	
</div>
</body>
</html>