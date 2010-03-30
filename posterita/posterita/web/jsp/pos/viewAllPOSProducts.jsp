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


<!-- viewAllPOSProducts.jsp -->
<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.beans.*" %>
<%@ page import="org.posterita.struts.pos.POSProductAction" %>
<%@ page import="org.compiere.process.DocumentEngine" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	


<%@ page import="org.compiere.model.MOrder" %>
<%@ page import="org.posterita.user.WebUserInfo" %>
<%@ page import="org.posterita.struts.pos.POSOrderAction" %>
<%@ page import="java.util.ArrayList" %> 

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/dcs.tld" prefix="dcs" %>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos" %>



<logic:notPresent name="<%=WebUserInfo.NAME%>" scope="session">
	<jsp:forward page="/LoginHome.do"/>
</logic:notPresent>

<%@ include file="/jsp/include/posHeaderAdministration.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %> 

<script type="text/javascript">
	
	$('mainTitle').innerHTML = '<pos:message textOnly="true" key="smenu.products"/>';
	
	$('newItem').href = 'POSProductAction.do?action=viewProduct&productId=0';
	$('newItem').title = 'Create Product';
	$('editItem').title = 'Edit Product';
	$('copyItem').title = 'Copy Product';
	$('deleteItem').title = 'Delete Product';
	$('importItem').href = 'ImportPOSProducts.do';
	$('importItem').title = 'Import Products';
	$('nextItem').title = 'View Next Product';
	$('previousItem').title = 'View Previous Product';
	
</script>


<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<logic:notPresent name = "<%= Constants.IS_FROM_DELETE_PRODUCT_PRICE %>">
		<tr>	
			<td width="75%">
				<div id="productFieldsContainer">
	    			<div id="productFields">
		    			<html:form action="/ViewAllPOSProductsAction">
							<html:hidden property="action" value="<%=POSProductAction.VIEW_ALL_POS_PRODUCTS%>"/>
			    			<table id="maintop" width="100%">
			    				<tbody>
			    					<tr>
			    						<td>
			    							<pos:message key="barcode"/>
											<html:text property="barCode" styleId="searchField1"/>
			    						</td>
			    						<td>
			    							<pos:message key="Name"/>
											<html:text property="productName" styleId="searchField2"/>
										</td>
			    						<td>
			    							DESC:
			    							<html:text property="description" styleId="searchField3"/>
			    						</td>
			    						<td>
			    							<html:submit styleClass="tangoSearch tangoButton">
		        								&nbsp;
		       								</html:submit>
			    						</td>
			    					</tr>
			    				</tbody>
			    			</table>
		    			</html:form>	
	    			</div>
    			</div>
			</td>
			<td class="paleBlueBg" width="100%">
				<div id="priceListDetailsContainer">
					<div id="priceListDetails">
						<div id="productName"></div>
					</div>
				</div>
			</td>
		</tr> 
	</logic:notPresent>  
	
	<logic:present name="<%=Constants.VIEW_POS_PRODUCTS%>">
		<logic:notEmpty name = "<%=Constants.IS_FROM_DELETE_PRODUCT_PRICE %>">
			<tr>
				<td width="75%">
					<html:form action = "/POSProductAction2">
						<html:hidden property = "action" value = "deleteProductPricesOnPriceList"/>
						<div class="noProduct">
							Are you sure you want to delete the  prices for the following products?
							<html:button property="button" value = "Cancel" onclick = "get('POSSubMenuItems.do')"></html:button>
							<html:submit>Delete</html:submit>
						</div>
					</html:form>
				</td>
				<td class="paleBlueBg" width="100%">
					<div id="priceListDetailsContainer">
						<div id="priceListDetails">
							<div id="productName"></div>
						</div>
					</div>
				</td>
			</tr>
		</logic:notEmpty>
	</logic:present>
	<tr height="100%">
		<td valign="top" width="75%">
			<div id="contentContainer">
				<div id="content">
					<div id="cart">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr class="itemTitleList">
								<th>
									<pos:message key="Name"/>
								</th>
								<th>
									<pos:message key="Barcode"/>
								</th> 
							    <th>
							    	<pos:message key="Description"/>
							    </th>
							    <th>
							    	<pos:message key="Uom"/>
							    </th>
							    <th>
							    	<pos:message key="Units per Pack"/>
							    </th>
							    <th>
							    	<pos:message key="qtyOnHand"/>
							    </th>
							    <th>
							    	<pos:message key="C_RevenueRecognition_ID"/>
							    </th>
						   	    <th>
						   	    	<pos:message key="purchase.price"/>
						   	    </th>  
						   	    <th>
						   	    	<pos:message key="sales.price"/>
						   	    </th>   
						   	    <th>
						   	    	<pos:message key="IsActive"/>
						   	    </th> 
		   	    			</tr>
							<bean:define id="editmsg">
								<pos:message key="edit.product" textOnly="true"/>
							</bean:define>
							<bean:define id="viewmsg">
								<pos:message key="view.product" textOnly="true"/>
							</bean:define>
							<bean:define id="activatemsg">
								<pos:message key="activate.product" textOnly="true"/>
							</bean:define>
							<bean:define id="deactivatemsg">
								<pos:message key="deactivate.product" textOnly="true"/>
							</bean:define>
							
							<%
										String url = "ViewAllPOSProduct.do";
										String collection = Constants.VIEW_POS_PRODUCTS;
							%>
							<logic:present name="<%=Constants.VIEW_POS_PRODUCTS%>">
								<logic:notEmpty name="<%=Constants.VIEW_POS_PRODUCTS%>">
									
									
									<logic:iterate offset="<%=offset%>" length="<%=length%>" indexId="count" id="element" name="<%=collection%>" type="org.posterita.beans.ProductBean">
										<%
										String styleClass = "oddRow";
										if ((count.intValue()%2) != 0)
										styleClass = "evenRow";
									   	%>
										
										<tr id="row<%=count%>" class="<%=styleClass%>" itemId="<%=element.getProductId()%>">
											<td style="color: #669ACF;">
												<html:link action="<%= "POSProductSalesAnalysisAction.do?action=viewProductSalesDetails&productId=" + element.getProductId() %>">
													<bean:write name="element" property="productName" filter="false"/>
												</html:link>
											</td>
											<td>
												<bean:write name="element" property="barCode"/>
											</td>
											<td>
												<bean:write name="element" property="description" filter="false"/>
											</td>
											<td>
												<bean:write name="element" property="uom" filter="false"/>
											</td>
											<td>
												<bean:write name="element" property="unitsPerPack" filter="false"/>
											</td>
											<td>
												<bean:write name="element" property="qtyOnHand" filter="false"/>
											</td>
											<td>
												<bean:write name="element" property="revenueRecognition" filter="false"/>
											</td>
											<td  align="right">
												<bean:define id="purchasePriceStandard" name="element" property="purchasePriceStandard"/>
												<fmt:formatNumber value='${purchasePriceStandard}' type="currency" currencySymbol=" "/>
											</td>
											<td  align="right">
												<bean:define id="salesPriceStandard" name="element" property="salesPriceStandard"/>
												<fmt:formatNumber value='${salesPriceStandard}' type="currency" currencySymbol=" "/>
											</td>
											<td>
												<bean:write name="element" property="isActive"/>
											</td>
										</tr>		
									</logic:iterate>
								</logic:notEmpty>
							</logic:present>
						</table>
					</div>
					<logic:present name="<%=Constants.VIEW_POS_PRODUCTS%>">
						<logic:notEmpty name="<%=Constants.VIEW_POS_PRODUCTS%>">
							<%@ include file="/jsp/include/pager.jsp" %>
						</logic:notEmpty>
					</logic:present>
				</div>
			</div>
		</td>
		<td width="25%">
			<div id="productDetailsContainer">
				<div id="productDetails">
					<logic:present name="<%=Constants.VIEW_POS_PRODUCTS%>">
						<logic:notEmpty name="<%=Constants.VIEW_POS_PRODUCTS%>">
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
							</div>
							<div class="rightColSubContainer" >
								<div id="productImage">
									<img id="image" src="">
								</div>
							</div>
						</logic:notEmpty>
					</logic:present>
					
					<logic:notPresent name = "<%=Constants.IS_FROM_DELETE_PRODUCT_PRICE %>">
						<logic:present name="<%=Constants.VIEW_POS_PRODUCTS%>">
							<logic:empty name="<%=Constants.VIEW_POS_PRODUCTS%>">
								<div class="noProduct">
									No products were found for : <b><bean:write name="ViewProductForm" property="productName"/></b>
								</div>
							</logic:empty>
						</logic:present>			
						<logic:notPresent name="<%=Constants.VIEW_POS_PRODUCTS%>">
							<logic:notPresent name="<%= Constants.PRODUCT_CART %>">
								<span id="productCount">
									<pos:message key="cart.empty" textOnly="true"/>
								</span>
							</logic:notPresent>
						</logic:notPresent>
					</logic:notPresent>
				</div>
			</div>
		</td>
	</tr>
	<logic:present name="<%=Constants.VIEW_POS_PRODUCTS%>">
		<logic:notEmpty name="<%=Constants.VIEW_POS_PRODUCTS%>">
			<tr height="35">
				<td width="75%">
					<div id="systemButnContainer">
    					<div id="systemButn">
							<img id="clearCartBtn" src="images/newUI/butn-clearall.gif" alt="Clear all items" width="95px" height="23px" border="0px"/>
						</div>
					</div>
				</td>
				<td width="25%" ><div id="checkoutContainer">
					<div id="checkout">
				</td>	
			</tr>
		</logic:notEmpty>
	</logic:present>
</td>
</tr>	
<tr>
  	<td colspan="2">
 		<div id="controls">
  		<div>
		  	<B>F1</B> Barcode | <B>F2</B> Name | <B>F3</B> Description | <B>Ctrl+Up</B> MoveUp | <B>Ctrl+Down</B> MoveDown 
		  	| <B>Ctrl-N</B> New Product | <B>Ctrl-E</B> Edit Product | <B>Ctrl-I</B> Import Products | <B>Ctrl+M</B> Menu | 
			<B>Ctrl-D</B> Open Drawer | <B>Ctrl-Delete</B> Clear		
	 	 </div>
	</td>
 </tr>
</table>
<script>

function initScreen()
{
	initUrls();
	initShortcuts();
	addCartStyle();
	initItemNavigation();
	initClearCart();
}

function showItemDetails(request)
{
	try
	{
		var response = request.responseText;
		
		if(response ==  null || response.length == 0)
		{
			return;
		}
		
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

var initClearCart = function(){
	
	var clearBtn = $('clearCartBtn');
	
	if(clearBtn != null)
	{
		clearBtn.onclick = function(e){
			
			var url = 'ClearProductCartAction.do'
			var pars = 'action=clearCart';
			clearCart(url, pars);
		};
	}
};

function resetDetails()
{
	var base = document.getElementsByTagName('base');
	var basePath = base[0].href;
	var url = basePath + 'RefreshProductCart.do';
	itemId = -1;
	cartIndex = -1;
	cartLines = null;
	get(url);
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

Event.observe(window,'load',initScreen,false);
</script>	

