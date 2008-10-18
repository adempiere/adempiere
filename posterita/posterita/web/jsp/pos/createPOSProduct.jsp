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
 * @author Alok, Preveen
--%>

<!-- createPOSProduct.jsp -->
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

<bean:define id="title"><pos:message textOnly="true" key="Product"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %> 
						   	
<html:form action="/CreatePOSProducts" focus="barCode" enctype="multipart/form-data">
<html:hidden property="action" value="<%=POSProductAction.CREATE_POS_PRODUCT%>"/>
<div style="height:50px;">
<div id="barcodeError" class="errormsg">Barcode already exists!</div>	
<div id="productError" class="errormsg">Product name already exists!</div>	
</div>
<table align="left" cellpadding="5" width="100%">
	<tr>
		<td width="50%" height="100%">
			<fieldset>
				<legend><pos:message key="ProductName"/></legend>
				<table>
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
						<html:text  property="productName" styleClass="text" style="width:100%"/>		    
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
					
			</table>
			</fieldset>
			</td>
			</tr>
			</table>
			<tr>
			<td>
			<table width="100%">
			<tr>
			<td>
			<fieldset>
			<legend><pos:message key="purchase.price"/></legend>
			<table>
				<tr>		
					<td>
						<label><pos:message key="purchase.price"/> <pos:message key="excl.vat"/></label><mandatory>*</mandatory>
					</td>
					
					<td align="right">
					  <html:text property="purchasePriceStandard"  styleClass="text"/>
					</td>
					
					<td align="right" width="100px">
						<label><pos:message key="incl.vat"/></label>
					</td>
						
					<td align="right">
					  <input type="text" name="purchasePriceStandardTax" class="text"/>
				  	</td>									
				</tr>
					
			</table>
			</fieldset>
			</td>
			</tr>
			</table>
			<tr>
			<td>
			<table width="100%">
			<tr>
			<td>
			<fieldset>
			<legend><pos:message key="sales.price"/></legend>
			<table>
				
				<tr>	
				  	<td>
						<label><pos:message key="PriceList"/><pos:message key="excl.vat"/></label><mandatory>*</mandatory>
				   </td>
				   	<td>
					  <html:text property="salesPriceList"  styleClass="text"/>
				  	</td>  
				
					<td align="right">
						<label><pos:message key="incl.vat"/></label>
				   	</td>
				   	<td>
					   <input type="text" name="salesPriceListTax" class="text"/>
					</td>
				</tr>
					
					
				<tr>		
					<td>
						<label><pos:message key="PriceStd"/> <pos:message key="excl.vat"/> </label><mandatory>*</mandatory>
				   	</td>
				   	<td>
					  	<html:text property="salesPriceStandard"  styleClass="text"/>
				  	</td> 
				  
				  	<td align="right">
						<label><pos:message key="incl.vat"/></label>
				   	</td>
				   	<td>
					   <input type="text" name="salesPriceStandardTax" class="text"/>
				 	</td>
				   
			   </tr>  
				   <tr>
					  <td>
							<label><pos:message key="PriceLimit"/> <pos:message key="excl.vat"/></label><mandatory>*</mandatory>
					   </td>
					   <td>
						  <html:text property="salesPriceLimit"  styleClass="text"/>
					  </td> 
					  
					  <td align="right">
							<label><pos:message key="incl.vat"/></label>
					   	</td>
					   	<td>
						   <input type="text" name="salesPriceLimitTax" class="text"/>
						</td> 
					</tr>
					
			</table>
			</fieldset>
			</td>
			</tr>
			</table>
			<tr>
			<td>
			<table width="100%">
			<tr>
			<td>
			<fieldset>
			<legend><pos:message key="TextDetails"/> </legend>
			<table>
			
				<tr>
					<td>
						<label><pos:message key="organisation.name"/>:</label><mandatory>*</mandatory>
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
						<html:select property="taxCategoryId" onchange="updateTax()" styleClass="text">										
							<html:options collection="<%= Constants.TAX_CATEGORY_ID %>" property="taxCategoryId" labelProperty="taxCategoryName"/>																																	
					   </html:select>
					 </td>				
			   	</tr>
			   	<tr>
					<td>
			  			<label><pos:message key="C_UOM_ID"/></label><mandatory>*</mandatory>  								
					</td>
				   	<td colspan="3">	   	  
						<html:select property="uomId" onchange="updateTax()" styleClass="text">										
							<html:options collection="<%= Constants.UOM_LIST %>" property="ID" labelProperty="name"/>																																	
					   </html:select>
					 </td>				
			   	</tr>	
				<tr>
					<td>
					 <label><pos:message key="C_RevenueRecognition_ID"/></label><mandatory>*</mandatory>
					</td>
					<td colspan="3">				
						<html:hidden  property="revenueRecognition"/>
						<input type="text" id="revenue_recognition" name="revenue_recognition" class="text"/>	
						<div class="autocomplete" id="revenue_recognitionDiv"></div>											
				   </td>
		       </tr>  
		       <tr>		
					<td>
						<label><pos:message key="attach.image"/></label>
					</td>
						<td colspan="3">
						<html:file property="file"/>
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
			</fieldset>
			</td>
			</tr>
			</table>
			</td>
			</tr>	
			    		
			<tr>	
			
			 <td align="right">
				<html:button onclick="setValues(this.form)" property="button" styleClass="save smallbutton">
					&nbsp;
				</html:button>	
			 </td>
			 <td colspan="2">&nbsp;</td>	
			</tr>  	
</table>
</html:form> 							
<script>
addRequiredLibrary('js/product.js');
</script>	    									 					 
<%@ include file="/jsp/include/posFooter.jsp" %>
