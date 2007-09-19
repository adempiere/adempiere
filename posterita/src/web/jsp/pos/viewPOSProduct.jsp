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



<!-- viewPOSProduct.jsp -->
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.Constants" %>

<%@ page import="org.compiere.model.MProduct" %>


<%@ page import="org.posterita.struts.pos.POSOrderAction" %>
<%@ page import="org.posterita.beans.ProductBean" %>
<%@ page import="org.posterita.user.WebUserInfo" %>
<%@ page import="org.posterita.lib.UdiConstants" %>

<%@ page import="org.posterita.struts.pos.POSProductAction" %>


<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

<logic:notPresent name="<%=WebUserInfo.NAME%>" scope="session">
	<jsp:forward page="/LoginHome.do"/>
</logic:notPresent>

<bean:define id="title"><pos:element textOnly="true" columnName="Product"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %> 
						   	
<html:form action="/UpdatePOSProductAction" enctype="multipart/form-data">
<html:hidden property="action" value="<%=POSProductAction.UPDATE_PRODUCT_DETAILS%>"/>
					
<table align="left" cellpadding="5" width="100%" border="0">
	<tr>
		<td>
			<fieldset>
				<legend><pos:element columnName="ProductName"/></legend>
				<table width="100%">
				<tr>
					<td>
						<table width="100%">						
						<tr>
							<td>
								<label><pos:element columnName="barcode"/></label>
							</td>		
							<td>
								<label><bean:write name="ProductForm" property="barCode"/></label>
							</td>									
						</tr>
						<tr>		
							<td>
								<label><pos:element columnName="ProductName"/></label><mandatory>*</mandatory>
							</td>
							<td>			
							    <label><bean:write name="ProductForm" property="productName"/></label>
							</td>
						</tr>
						<tr>		
							<td>
								<label><pos:element columnName="Description"/></label><mandatory>*</mandatory>
							</td>
							<td>
							    <label><bean:write name="ProductForm" property="description"/></label>
							</td>
						</tr>						
						</table>
					</td>
					<td>
						<logic:present name="ProductForm">
						<bean:define id="ProductForm" name="ProductForm" type="org.posterita.form.ProductForm"/>
						<%
						ProductBean bean = 	(ProductBean)ProductForm.getBean();
						if(bean.getImageInfo() != null)
						if(bean.getImageInfo().getHasAttachment() != null)	
						if(bean.getImageInfo().getHasAttachment().booleanValue()){%>
							<img src="ViewProductInfo.do?size=THUMB&productId=<bean:write name="ProductForm" property="productId"/>">
						<%}%>
						</logic:present>
						<logic:notPresent name="ProductForm">
							&nbsp;
						</logic:notPresent>
					</td>
				</tr>									
				</table>
			</fieldset>
		</td>
	</tr>
	<tr>
		<td>
			<fieldset>
				<legend><pos:element columnName="purchase.price"/></legend>
				<table>
				<tr>		
					<td>
						<label> <pos:element columnName="purchase.price"/> <pos:element columnName="excl.vat"/></label><mandatory>*</mandatory>
					</td>
					
					<td>
					  <label><bean:write name="ProductForm" property="purchasePriceStandard"/></label>					  
					</td>
					
					<td align="right" width="100px">
						&nbsp;
					</td>
						
					<td>
					  &nbsp;
				  	</td>									
				</tr>
				</table>
			</fieldset>
		</td>
	</tr>
	<tr>
		<td>
			<fieldset>
				<legend><pos:element columnName="sales.price"/></legend>
				<table>
				<tr>		
				  	<td>
						<label><pos:element columnName="PriceList"/><pos:element columnName="excl.vat"/></label><mandatory>*</mandatory>
				    </td>
				   	<td>
					    <label><bean:write name="ProductForm" property="salesPriceList"/></label>	
				  	</td>  
				
					<td align="right">
						&nbsp;
				   	</td>
				   	<td>
					   &nbsp;
					</td>
				</tr>					
				<tr>		
					<td>
						<label><pos:element columnName="PriceStd"/> <pos:element columnName="excl.vat"/> </label><mandatory>*</mandatory>
				   	</td>
				   	<td>
					  	<label><bean:write name="ProductForm" property="salesPriceStandard"/></label>	
				  	</td>				  
				  	<td align="right">
						&nbsp;
				   	</td>
				   	<td>
					   &nbsp;
				 	</td>				   
			   </tr>				
			   <tr>
				  	<td>
						<label><pos:element columnName="PriceLimit"/> <pos:element columnName="excl.vat"/></label><mandatory>*</mandatory>
				  	</td>
				  	<td>
						<label><bean:write name="ProductForm" property="salesPriceLimit"/></label>	
				  	</td>				  
				  	<td align="right">
						&nbsp;
				  	</td>
				  	<td>
						&nbsp;
					</td> 
				</tr>
				</table>
			</fieldset>
		</td>
	</tr>
	<tr>
		<td>
			<fieldset>
				<legend><pos:element columnName="tax.details"/> </legend>
				<table>
				<tr>
					<td>
						<label><pos:element columnName="C_TaxCategory_ID"/></label><mandatory>*</mandatory>  								
					</td>
					<td colspan="3">	   	  
						<html:select property="taxCategoryId" onchange="updateTax()" styleClass="text"  disabled="true">										
						<html:options collection="<%= Constants.TAX_CATEGORY_ID %>" property="taxCategoryId" labelProperty="taxCategoryName"/>																																	
					</html:select>
					</td>				
				</tr>
				<tr>
					<td>
						<label><pos:element columnName="C_RevenueRecognition_ID"/></label><mandatory>*</mandatory>
					</td>
					<td colspan="3">				
						<label><bean:write name="ProductForm" property="revenueRecognition"/></label>											
					</td>
				</tr>
				 
				<tr>		
					<td>
						<label><pos:element columnName="IsActive"/></label>
					</td>
					<td colspan="3">
						<label><bean:write name="ProductForm" property="isActive"/></label>
					</td>
				</tr>
				<tr>
					<td>
					<label><pos:element columnName="ProductType"/></label>
					</td>
					<td colspan="3">
						<label>						
						<logic:equal name="ProductForm" property="productType" value="<%=MProduct.PRODUCTTYPE_Item%>">
							<pos:element columnName="product.type.item"/>
						</logic:equal>
						
						<logic:equal name="ProductForm" property="productType" value="<%=MProduct.PRODUCTTYPE_Service%>">
							<pos:element columnName="product.type.services"/>
						</logic:equal>
						</label>
					</td>
				</tr>	
				<tr>
					<td>
						<label><pos:element columnName="Group1"/></label>
					</td>
					<td colspan="3">				
						<bean:write name="ProductForm" property="group1"/>											
					</td>
				</tr>
				<tr>
					<td>
						<label><pos:element columnName="Group2"/></label>
					</td>
					<td colspan="3">				
						<bean:write name="ProductForm" property="group2"/>										
					</td>
				</tr>
				</table>
			</fieldset>
		</td>
	</tr>
</table>
</html:form>	
 							
<script>
addRequiredLibrary('js/product.js');
</script>	    									 					 
<%@ include file="/jsp/include/posFooter.jsp" %>
