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

<!--createPOSGarmentProducts.jsp-->
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.Constants" %>


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

<bean:define id="title"><pos:message textOnly="true" key="create.garment"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %> 

<html:form action="/CreatePOSGarmentsProductsAction" focus="barCode">
<html:hidden property="action" value="<%=POSProductAction.CREATE_POS_GARMENT_PRODUCT%>"/>
<div class="container">
<fieldset>
<legend>Garment Details</legend>
<table class="view" width="100%">
	<tr>
		<td align="left">			
		<table class="view" width="100%">
			<tr>
				<td><label><pos:message key="description"/>:</label><mandatory>*</mandatory></td>
				<td>
					<html:text property="description" style="width:400px"  styleClass="text"/>
				</td>
			</tr>
			<tr>
				<td><label><pos:message key="brand"/>:</label><mandatory>*</mandatory></td>
				<td>
					<input type="text" id="brand" name="brand" class="text"/>
					<html:hidden property="brandName"/>
					<div id="brandDiv" class="autocomplete"></div>
				</td>
			</tr>
			
			<tr>
				<td><label><pos:message key="model"/>:</label><mandatory>*</mandatory></td>
				<td>
					<input type="text" id="model" name="model" class="text"/>
					<html:hidden property="modelName"/>
					<div id="modelDiv" class="autocomplete"></div>
				</td>
			</tr>
			
			<tr>
				<td><label><pos:message key="design"/>:</label><mandatory>*</mandatory></td>
				<td>
					<input type="text" id="design" name="design" class="text"/>
					<html:hidden property="designName"/>
					<div class="autocomplete" id="designDiv"></div>
				</td>
			</tr>							
			
			<tr>		
				<td><label><pos:message key="colour"/></label><mandatory>*</mandatory></td>
				<td>
					<input type="text" id="colour" name="colour" class="text"/>
					<html:hidden property="colourName"/>
					<div class="autocomplete" id="colourDiv"></div>
				</td>
			</tr>
			
			<tr>		
				<td><label><pos:message key="C_RevenueRecognition_ID"/></label><mandatory>*</mandatory></td>
				<td>									
					<input type="text" id="revenue_recognition" name="revenue_recognition" class="text"/>						
					<html:hidden property="revenueRecognition"/>
					<div class="autocomplete" id="revenue_recognitionDiv"></div>								
				</td>									
			</tr>
			<tr>
				<td>
  					<label><pos:message key="C_TaxCategory_ID"/></label>
				</td>
			   	<td>
					<html:select property="taxCategoryId" style="text">
							<html:options collection="<%= Constants.TAX_CATEGORY_ID %>" property="taxCategoryId" labelProperty="taxCategoryName"/>																																	
				   </html:select>
				 </td>	   
   		   	</tr> 
   		   	<tr>
	      		 <td>
				 	<label><pos:message key="group1"/></label>
				</td>
			
		    	<td>
		    		<html:text  property="group1" styleClass="text"/>
		    	</td>
	    	</tr>
	    	<tr>
	      		 <td>
				 	<label><pos:message key="group2"/></label>
				</td>
			
		    	<td>
		    		<html:text property="group2" styleClass="text"/>
		    	</td>
	    	</tr>
			<tr>		
				<td colspan="2">
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
							
							<td align="right">
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
			
			<tr>		
				<td colspan="2">
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
		</td>
	</tr>
	<tr>	
		<td>				
			<table class="view">
				<tr>
					<td colspan="2">
						<fieldset>
							<legend><pos:message key="size"/><mandatory>*</mandatory> <pos:message key="at.least.one"/></legend>
							<table  align="center" border="0" cellpadding="5" cellspacing="0">
							<tr>
								<td valign="top">
									<div style="width:150px;">
									Select:
									<span class="link" onclick="selectAllChk(this)">All</span>
									<span class="link" onclick="clearAllChk(this)">None</span>
									<table align="center"  border="0" cellpadding="5" cellspacing="0">					
									   <tr>
								   			<td width="100px">
								   				<html:checkbox property="sizes" value="S">
								   					<label>S</label>
								   				</html:checkbox>
								   			</td>
									   </tr>
									   
									   <tr>
								   			<td>
								   				<html:checkbox property="sizes" value="M">
								   					<label>M</label>
								   				</html:checkbox>
								   			</td>
									   </tr>
									   
									   <tr>
								   			<td>
								   				<html:checkbox property="sizes" value="L">
								   					<label>L</label>
								   				</html:checkbox>
								   			</td>
									   </tr>
									   
									   <tr>
								   			<td>
								   				<html:checkbox property="sizes" value="XL">
								   					<label>XL</label>
								   				</html:checkbox>
								   			</td>
									   </tr>
									   
									   <tr>
								   			<td>
								   				<html:checkbox property="sizes" value="XXL">
								   					<label>XXL</label>
								   				</html:checkbox>
								   			</td>
									   </tr>
									   
									   <tr>
								   			<td>
								   				<html:checkbox property="sizes" value="XXXL">
								   					<label>XXXL</label>
								   				</html:checkbox>
								   			</td>
									   </tr>									
							  		</table>
							  		</div>
								</td>
								<td valign="top">
									<div style="width:150px;">
									Select:
									<span class="link" onclick="selectAllChk(this)">All</span>
									<span class="link" onclick="clearAllChk(this)">None</span>
									<table align="center"  border="0" cellpadding="5" cellspacing="0">					
									   <tr>
								   			<td width="100px">
								   				<html:checkbox property="sizes" value="26">
								   					<label>26</label>
								   				</html:checkbox>
								   			</td>
								   			<td width="100px">
								   				<html:checkbox property="sizes" value="38">
								   					<label>38</label>
								   				</html:checkbox>
								   			</td>
									   </tr>
									   
									   <tr>
								   			<td>
								   				<html:checkbox property="sizes" value="28">
								   					<label>28</label>
								   				</html:checkbox>
								   			</td>
								   			<td>
								   				<html:checkbox property="sizes" value="40">
								   					<label>40</label>
								   				</html:checkbox>
								   			</td>
									   </tr>
									   
									   <tr>
								   			<td>
								   				<html:checkbox property="sizes" value="30">
								   					<label>30</label>
								   				</html:checkbox>
								   			</td>
								   			<td>
								   				<html:checkbox property="sizes" value="42">
								   					<label>42</label>
								   				</html:checkbox>
								   			</td>
									   </tr>
									   
									   <tr>
								   			<td>
								   				<html:checkbox property="sizes" value="32">
								   					<label>32</label>
								   				</html:checkbox>
								   			</td>
								   			<td>
								   				<html:checkbox property="sizes" value="44">
								   					<label>44</label>
								   				</html:checkbox>
								   			</td>
									   </tr>
									   
									   <tr>
								   			<td>
								   				<html:checkbox property="sizes" value="34">
								   					<label>34</label>
								   				</html:checkbox>
								   			</td>
									   </tr>
									   
									   <tr>
								   			<td>
								   				<html:checkbox property="sizes" value="36">
								   					<label>36</label>
								   				</html:checkbox>
								   			</td>
									   </tr>						
							  		</table>
							  		</div>
								</td>
								<td valign="top">
									<div style="width:150px;">
									Select:
									<span class="link" onclick="selectAllChk(this)">All</span>
									<span class="link" onclick="clearAllChk(this)">None</span>
									<table align="center"  border="0" cellpadding="5" cellspacing="0">					
									   <tr>
								   			<td  width="100px">
								   				<html:checkbox property="sizes" value="2">
								   					<label>2</label>
								   				</html:checkbox>
								   			</td>
									   </tr>
									   
									   <tr>
								   			<td>
								   				<html:checkbox property="sizes" value="4">
								   					<label>4</label>
								   				</html:checkbox>
								   			</td>
									   </tr>
									   
									   <tr>
								   			<td>
								   				<html:checkbox property="sizes" value="6">
								   					<label>6</label>
								   				</html:checkbox>
								   			</td>
									   </tr>
									   
									   <tr>
								   			<td>
								   				<html:checkbox property="sizes" value="8">
								   					<label>8</label>
								   				</html:checkbox>
								   			</td>
									   </tr>
									   
									   <tr>
								   			<td>
								   				<html:checkbox property="sizes" value="10">
								   					<label>10</label>
								   				</html:checkbox>
								   			</td>
									   </tr>
									   
									   <tr>
								   			<td>
								   				<html:checkbox property="sizes" value="12">
								   					<label>12</label>
								   				</html:checkbox>
								   			</td>
									   </tr>	
									   <tr>
								   			<td>
								   				<html:checkbox property="sizes" value="14">
								   					<label>14</label>
								   				</html:checkbox>
								   			</td>
									   </tr>									   									
							  		</table>
							  		</div>
					  			</td>
					  		</tr>
					  		<tr>
					  			<td colspan="4">
								   	<label>Custom</label><html:text property="customSize"/>
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
			<html:button property="btn" styleClass="save smallbutton" onclick="setValues(this.form)">&nbsp;</html:button>		  
		 </td>	
	</tr>			
</table>	
</fieldset>				
</html:form>
</div>			
	
 							
<script>
$focus('brand');

copyValueInto('brandName','brand');
copyValueInto('modelName','model');
copyValueInto('designName','design');
copyValueInto('colourName','colour');
copyValueInto('revenueRecognition','revenue_recognition');

new Ajax.Autocompleter('brand','brandDiv','SearchPOSGarmentAttributes.do',{
							paramName:'brand',
							frequency:TROTTLE_TIME,
							afterUpdateElement:function(e1,e2){
												$FF('brandName').value = e1.value;																					
												}												
							});
							
new Ajax.Autocompleter('model','modelDiv','SearchPOSGarmentAttributes.do',{
							paramName:'model',
							frequency:TROTTLE_TIME,
							afterUpdateElement:function(e1,e2){
												$FF('modelName').value = e1.value;																					
												}												
							});

new Ajax.Autocompleter('design','designDiv','SearchPOSGarmentAttributes.do',{
							paramName:'design',
							frequency:TROTTLE_TIME,
							afterUpdateElement:function(e1,e2){
												$FF('designName').value = e1.value;																					
												}												
							});
							
new Ajax.Autocompleter('colour','colourDiv','SearchPOSGarmentAttributes.do',{
							paramName:'colour',
							frequency:1.0,
							afterUpdateElement:function(e1,e2){
												$FF('colourName').value = e1.value;																					
												}												
							});
							
new Ajax.Autocompleter('revenue_recognition','revenue_recognitionDiv','SearchPOSGarmentAttributes.do',{
							paramName:'revenue_recognition',
							frequency:TROTTLE_TIME,
							afterUpdateElement:function(e1,e2){
												$FF('revenueRecognition').value = e1.value;																					
												}												
							});
							
function $FF(elementName)
{
	return document.getElementsByName(elementName)[0];
}

function copyValueInto(e1,e2)
{
	$FF(e2).value = $FF(e1).value;
	toConsole('Copying '+e1+' into '+e2);
}

function setValues(form)
{
	//putting new values into form
	
	copyValueInto('brand','brandName');
	copyValueInto('model','modelName');
	copyValueInto('design','designName');
	copyValueInto('colour','colourName');
	copyValueInto('revenue_recognition','revenueRecognition');	
	
	form.submit();
}

function selectAllChk(e)
{
	chks = e.parentNode.getElementsByTagName('input');
	for(i=0;i<chks.length;i++)
	{
		chks[i].checked = true;
	}
}

function clearAllChk(e)
{
	chks = e.parentNode.getElementsByTagName('input');
	for(i=0;i<chks.length;i++)
	{
		chks[i].checked = false;
	}
}
</script>
<script>
addRequiredLibrary('js/product2.js');
</script>	    									 					 
<%@ include file="/jsp/include/posFooter.jsp" %>

