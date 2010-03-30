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
<bean:define id="title">Product</bean:define>
<%@ include file="/jsp/include/posHeaderAdministration.jsp" %>
<%@ include file="/jsp/include/errorMsg.jsp" %> 
<!-- page contents -->

<script type="text/javascript">
	
	$('mainTitle').innerHTML = '<pos:message textOnly="true" key="Edit Product"/>';
	
	$('newItem').href = 'POSProductAction.do?action=viewProduct&productId=0';
	$('newItem').title = 'Create Product';
	$('editItem').title = 'Edit Product';
	$('editImg').src = 'images/newUI/butn-edit-off.gif';
	$('copyItem').title = 'Copy Product';
	$('deleteItem').title = 'Delete Product';
	$('importItem').href = 'ImportPOSProducts.do';
	$('importItem').title = 'Import Products';
	$('nextItem').title = 'View Next Product';
	$('previousItem').title = 'View Previous Product';
	
</script>

<body bgcolor="#ffffff">
	<html:form action="/POSProductAction" enctype="multipart/form-data">
		<html:hidden property="action" value="<%=POSProductAction.CREATE_OR_UPDATE_PRODUCT%>"/>
		<bean:define id="productId" name="POSProductForm" property="productId" ></bean:define>
		<script>
		$('previousItem').onclick = function(e){
			get('POSProductAction.do?action=viewProduct&isNext=false&productId=<%= productId %>');
			};
			
		$('nextItem').onclick = function(e){
			get('POSProductAction.do?action=viewProduct&isNext=true&productId=<%= productId %>');
		}
			
		</script>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td valign="top" width="75%" bgcolor="white">
					<div id="productEditFieldsContainer">
						<table width="100%" border="0" cellspacing="3" cellpadding="2">
							<tr>
								<td width="15%">
									<span class="bold11">
										<label>
											<pos:message key="barcode"/>
										</label>
									</span>
								</td>
								<td>
									<html:text property="barCode" styleClass="prodFields"/>
								</td>
								<td rowspan="11" valign="top">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td>
												<span class="bold11">
													Image Upload
												</span>
											</td>
											<td>
												<html:hidden property="file"/>
												<html:file property="file"/> 
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td width="15%">
									<span class="bold11">
										<label>
											<pos:message key="ProductName"/>
										</label>
										<mandatory>
											*
										</mandatory>
									</span>
								</td>
								<td>
									<html:text  property="productName" styleClass="prodFields" />		    
				   					<html:hidden  property="productId"/>
								</td>
							</tr>
							<tr>
								<td width="15%">
									<span class="bold11">
										<label>
											<pos:message key="Description"/>
										</label>
										<mandatory>
											*
										</mandatory>
									</span>
								</td>
								<td>
									<html:text property="description" styleClass="prodDescription"/>
								</td>
							</tr>
							<tr>
								<td width="15%">
									<span class="bold11">
										<label>
											<pos:message key="smenu.organisation"/>:
										</label>
										<mandatory>
											*
										</mandatory>
									</span>
								</td>
								<td>
									<html:select property="orgId" styleClass="text">
										<html:options collection="<%=Constants.USER_ORGS%>" property="key" labelProperty="name"/>
				 					</html:select>
								</td>
							</tr>
							<tr>
								<td width="15%">
									<span class="bold11">
										<label>
											<pos:message key="C_TaxCategory_ID"/>
										</label>
										<mandatory>
											*
										</mandatory> 
									</span>
								</td>
								<td>
									<html:select property="taxCategoryId" styleClass="text" onchange="updateTax()">										
										<html:options collection="<%= Constants.TAX_CATEGORY_ID %>" property="taxCategoryId" labelProperty="taxCategoryName" />
									</html:select>
								</td>
							</tr>
							<tr>
								<td width="15%">
									<span class="bold11">
										<label>
											<pos:message key="uom"/>
										</label>
										<mandatory>
											*
										</mandatory> 
									</span>
								</td>
								<td>
									<html:select property="uomId" styleClass="text">										
									<html:options collection="<%= Constants.UOM_LIST %>" property="ID" labelProperty="name"/>																																	
				  					</html:select>
								</td>
							</tr>
							<tr>
								<td width="15%">
									<span class="bold11">
										<label>
											<pos:message key="Units per Pack"/>
										</label>
									</span>
								</td>
								<td>
									<html:text property="unitsPerPack" styleClass="prodFields"/>
								</td>
							</tr>
							<tr>
								<td width="15%">
									<span class="bold11">
										<label>
											<pos:message key="C_RevenueRecognition_ID"/>
										</label>
										<mandatory>
											*
										</mandatory>
									</span>
								</td>
	
								<td>
									<html:text  property="revenueRecognition" styleClass="prodFields"/>
								</td>
							</tr>
							<tr>
								<td width="15%">
									<span class="bold11">
										<label>
											<pos:message key="IsActive"/>
										</label>
									</span>
								</td>
								<td>
									<html:checkbox  property="isActive" value="true" styleClass="checkBox"/>
								</td>
							</tr>
							<tr>
								<td width="15%">
									<span class="bold11">
										<label>
											<pos:message key="ProductType"/>
										</label>
									</span>
								</td>
								<td>
									<html:radio property="productType"  value="<%=MProduct.PRODUCTTYPE_Item%>"/>
			    					<pos:message key="product.type.item"/>
			    					<html:radio property="productType" value="<%=MProduct.PRODUCTTYPE_Service%>"/>
		    						<pos:message key="product.type.services"/>
		    					</td>
							</tr>
							<tr>
								<td width="15%">
									<span class="bold11">
										<label>
											<pos:message key="group1"/>
										</label>
									</span>
								</td>
								<td>
									<html:text  property="group1" styleClass="prodFields"/>
								</td>
							</tr>
							<tr>
								<td width="15%">
									<span class="bold11">
										<label>
											<pos:message key="group2"/>
										</label>
									</span>
								</td>
								<td>
									<html:text property="group2" styleClass="prodFields"/>
								</td>
							</tr>
						</table>
						<br>
						<logic:present name="<%=Constants.PRODUCT_PRICE_LISTS%>">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr class="itemTitleList">
									<th width="100">
										<pos:message key="smenu.price.list"/>
									</th>
									<th width="100">
										<pos:message key="price.list.type"/>
									</th>
									<th colspan="2">
										<div align="center">
											<pos:message key="standard.price"/>
										</div>
									</th>
									<td colspan="2">
										<div align="center">
											<pos:message key="list.price"/>
										</div>
									</td>
									<td colspan="2">
										<div align="center">
											<pos:message key="limit.price"/>
										</div>
									</td>
								</tr>
		
								<tr>
									<td width="100"></td>
									<td width="100"></td>
									<td>
										<div align="center">
											<pos:message key="tax.excl"/>
										</div>
									</td>
									<td>
										<div align="center">
											<pos:message key="tax.incl"/>
										</div>
									</td>
									<td>
										<div align="center">
											<pos:message key="tax.excl"/>
										</div>
									</td>
									<td>
		
										<div align="center">
											<pos:message key="tax.incl"/>
										</div>
									</td>
									<td>
										<div align="center">
											<pos:message key="tax.excl"/>
										</div>
									</td>
									<td>
		
										<div align="center">
											<pos:message key="tax.incl"/>
										</div>
									</td>
								</tr>
								
								<logic:iterate id="productBean" name="<%=Constants.PRODUCT_PRICE_LISTS%>" type="org.posterita.beans.ProductBean" indexId="index">
								<html:hidden name="productBean" property="priceListId" value="<%=productBean.getPriceListId().toString() %>" indexed="true"/>
								<html:hidden name="productBean" property="productId" value="<%=productBean.getProductId().toString()%>" indexed="true" styleId="productId"/>
								<html:hidden name="productBean" property="priceListVersionId" value="<%=productBean.getPriceListVersionId().toString()%>" indexed="true"/>
								
								<%
									String styleClass = "oddRow";
								
									if(index.intValue()%2 == 0)
									{
										styleClass = "evenRow";
									}
									
								%>
								
								<tr id="row<%= (index.intValue()+1) %>" class="<%=styleClass%>" onMouseOver="this.className='highlight'" onMouseOut="this.className='oddRow'"">
									<td class="bold11" width="100">
										<bean:write name="productBean" property="priceListName"/>
										<logic:equal name="productBean" property="isMandatory" value="true">
											<mandatory>
												*
											</mandatory>
										</logic:equal>
									</td>
									<td width="100">
										<logic:equal name="productBean" property="isSOPriceList" value="true">
											<label>
												<pos:message key="sales"/>
											</label>
										</logic:equal>
										<logic:equal name="productBean" property="isSOPriceList" value="false">
											<label>
												<pos:message key="purchase"/>
											</label>
										</logic:equal>
									</td>
									<td>
										<html:text property="stdPrice" name="productBean" value="<%=productBean.getStdPrice().toString()%>" indexed="true" size="10" styleId="stdPrice" styleClass="prodTaxFields"/>
									</td>
									<td>	
										<html:text property="stdPriceIncl" name="productBean" value="<%=productBean.getStdPriceIncl().toString()%>" indexed="true" size="10" styleId="stdPriceIncl" styleClass="prodTaxFields"/>
									</td>
									<logic:equal name="productBean" property="isSOPriceList" value="true">
										<td>
											<html:text property="listPrice" name="productBean" value="<%=productBean.getListPrice().toString()%>" indexed="true" size="10" styleId="listPrice" styleClass="prodTaxFields"/>
										</td>
										<td>
											<html:text property="listPriceIncl" name="productBean" value="<%=productBean.getListPriceIncl().toString()%>" indexed="true" size="10" styleId="listPriceIncl" styleClass="prodTaxFields"/>
										</td>
										<td>
											<html:text property="limitPrice" name="productBean" value="<%=productBean.getLimitPrice().toString()%>" indexed="true" size="10" styleId="limitPrice" styleClass="prodTaxFields"/>
										</td>			
										<td>
											<html:text property="limitPriceIncl" name="productBean" value="<%=productBean.getLimitPriceIncl().toString()%>" indexed="true" size="10" styleId="limitPriceIncl" styleClass="prodTaxFields"/>
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
				</div>
			</td>
			<td valign="top" width="25%">
				<div id="priceListDetailsContainer">
					<div id="priceListDetails">
						<div id="productName"></div>
					</div>
				</div>
				<div id="productDetailsContainer">
					<div id="productDetails">
						<div>
							Stock Qty:<span id="qtyOnHand" class="productValue"></span>
						</div>
						<div class="rightColSubContainer">
							<table border="0" cellspacing="0" cellpadding="2"  class="alignDetails" width="100%">
								<caption class="captionStyle">Sales Figures</caption>
								<tr class="salesFiguresHeader">
									<th>
										Period
									</th>
									<th>
										Sales
									</th>
									<th>
										Total Amt
									</th>
								</tr>
								<tr>
									<td>
										Today
									</td>
									<td>
										<span id="todaySales" class="productValue"></span>
									</td>
									<td>
										<span id="todayTotal" class="productValue"></span>
									</td>
								</tr>
								<tr class="salesFiguresRow">
									<td>
										Current Week
									</td>
									<td>
										<span id="currentWeekSales" class="productValue"></span>
									</td>
									<td>
										<span id="currentWeekTotal" class="productValue"></span>
									</td>
								</tr>
								<tr>
									<td>
										Current Month
									</td>
									<td>
										<span id="currentMonthSales" class="productValue"></span>
									</td>
									<td>
										<span id="currentMonthTotal" class="productValue"></span>
									</td>
								</tr>
								<tr class="salesFiguresRow">
									<td>
										6 Months
									</td>
									<td>
										<span id="6MonthSales" class="productValue"></span>
									</td>
									<td>
										<span id="6MonthTotal" class="productValue"></span>
									</td>
								</tr>
								<tr>
									<td>
										Current Year
									</td>
									<td>
										<span id="currentYearSales" class="productValue"></span>
									</td>
									<td>
										<span id="currentYearTotal" class="productValue"></span>
									</td>
								</tr>
							</table>
							<div class="rightColSubContainer">
								<div id="productImage">
									<img id="image" src="">
								</div>
							</div>
						</div>
					</div>
				</div>
			</td>
		</tr>
		<tr>
			<td bgcolor="white" width="75%"></td>
			<td width="25%">
				<div id="checkoutContainer">
					<div id="checkout">
						<img class="pointer" src="images/newUI/butn-save.gif" alt="Save Changes" height="25" width="76" border="0" onclick="document.forms[0].submit();">
					</div>
				</div>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<div id="controls">
					<B>Ctrl-N</B> New Product | <B>Ctrl-I</B> Import Products | <B>Ctrl+M</B> Menu | 
					<B>Ctrl-D</B> Open Drawer | <B>Ctrl-Left</B> Previous Product | <B>Ctrl-Right</B> Next Product | <B>F4</B> Save
				</div>
			</td>
		</tr>
	</table>
</html:form>
</body>
<script>
var editItemUrl = null;
var createItemUrl = null;
var importItemsUrl = null;
var itemDetailsUrl = null;
var itemDetailsPars = null;

function initScreen()
{	
	initUrls();
	initShortcuts();
	getItemDetails(getItemDetailsUrl(), getItemDetailsPars(), $('productId').value);
}

function initUrls()
{
	createItemUrl = 'POSProductAction.do?action=viewProduct&productId=0';
	editItemUrl = 'POSProductAction.do?action=viewProduct&productId=';
	importItemsUrl = 'ImportPOSProducts.do';
	itemDetailsUrl = 'GetProductSalesDetails.do';
	itemDetailsPars = 'action=viewProductSalesSummary&productId=';
}

function getEditItemUrl()
{
	return editItemUrl;
}

function getCreateItemUrl()
{
	return createItemUrl;
}	

function getImportItemsUrl()
{
	return importItemsUrl;
}

function getItemDetailsUrl()
{
	return itemDetailsUrl;
}

function getItemDetailsPars()
{
	return itemDetailsPars;
}

function showItemDetails(request)
{
	try
	{
		var response = request.responseText;
		var product = eval('(' + response + ')');
		
		
		$('productName').innerHTML = product.name;	
		$('qtyOnHand').innerHTML = product.qtyOnHand;
		$('todaySales').innerHTML = product.qtySold6;
		$('todayTotal').innerHTML = product.totalAmt6;
		$('currentWeekSales').innerHTML =  product.qtySold5;
		$('currentWeekTotal').innerHTML = product.totalAmt5;
		$('currentMonthSales').innerHTML = product.qtySold4;
		$('currentMonthTotal').innerHTML = product.totalAmt4;
		$('6MonthSales').innerHTML = product.qtySold1;
		$('6MonthTotal').innerHTML = product.totalAmt1;
		$('currentYearSales').innerHTML = product.qtySold0;
		$('currentYearTotal').innerHTML = product.totalAmt0;
		$('image').src = 'images/newUI/productpreview.gif';				
	}
	catch(e)
	{
		showErrorMessage(e);
	}
}

//addRequiredLibrary('js/product.js');
//addRequiredLibrary('js/productTax.js');

Event.observe(window,'load',initScreen,false);

</script>	
