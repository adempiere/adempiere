<%--
 *  Product: Posterita Web-Based POS and Adempiere Plugin
 *  Copyright (C) 2007 - 2008 Posterita Ltd
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
 *  @author shameem
--%>
<!-- viewStock.jsp -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

<logic:notPresent name="<%=WebUserInfo.NAME%>">
	<logic:forward name="chooseApplication"/>
</logic:notPresent>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String appName = (String) request.getSession().getAttribute(Constants.APP_NAME);
	Properties ctx = TmkJSPEnv.getCtx(request);
	String posName = POSTerminalManager.getTerminalName(ctx);
	String orgName = MOrg.get(ctx, Env.getContextAsInt(ctx, UdiConstants.ORG_ID_CTX_PARAM)).getName();
	String roleName = MRole.get(ctx, Env.getContextAsInt(ctx, UdiConstants.AD_ROLE_ID)).getName();
	
	Object bPId = request.getSession().getAttribute(Constants.BPARTNER_ID);
	Object bPName = request.getSession().getAttribute(Constants.BPARTNER_NAME);
	Object bPCredit = request.getSession().getAttribute(Constants.BPARTNER_CREDIT);	
	String bPartnerId = bPId == null? "":bPId.toString();
	String bPartnerName = bPName == null? "": bPName.toString();
	String bPartnerCredit = bPCredit == null? "": bPCredit.toString();
	
	//set configuration
	Configuration configuration = Configuration.getConfiguration(request);
	String paymentRule = configuration.getPaymentRule();
	String searchProductBy = configuration.getSearchProductBy();
	boolean isCustomerCompulsory = configuration.isCustomerCompulsory();
	boolean isTerminalLocked = POSTerminalManager.isTerminalLocked(ctx);
	int movementId = Env.getContextAsInt(ctx, Constants.MMOVEMENT_ID);
	
	Integer priceListId = PriceListManager.getDefaultPriceListId(ctx, false);
	Object plId = request.getSession().getAttribute(Constants.ORDER_PRICE_LIST);
	if (plId != null)
	{
		try
		{
			priceListId = Integer.valueOf(String.valueOf(plId));
		}
		catch (NumberFormatException e){}
	}
	MPriceList priceList = MPriceList.get(ctx, priceListId.intValue(), null);
	String priceListName = priceList.getName();
	MCurrency currency = MCurrency.get(ctx, priceList.getC_Currency_ID());
	BigDecimal roundOffFactor = currency.getRoundOffFactor(); 
	
	if (isTerminalLocked)
	{
		posName += "  (Locked)";
	}
	
 	int roleId = Env.getAD_Role_ID(ctx);                
 	
	Object orgFrom = request.getSession().getAttribute(Constants.ORG_FROM_ID);
	Integer ctxOrg = Integer.valueOf(Env.getContextAsInt(ctx, UdiConstants.ORG_ID_CTX_PARAM));
	String org = orgFrom == null? ctxOrg.toString(): orgFrom.toString(); 
    
    int menuId = MenuManager.getMenuId(ctx, "pmenu.stock");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="org.posterita.struts.stock.StockMovementAction"%>
<%@page import="org.posterita.Constants"%>
<%@page import="org.posterita.core.TmkJSPEnv"%>
<%@page import="org.posterita.lib.UdiConstants"%>
<%@page import="org.compiere.util.Env"%>
<%@page import="org.posterita.businesslogic.stock.StockList"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="org.posterita.businesslogic.POSTerminalManager"%>
<%@page import="java.util.Properties"%>
<%@page import="org.compiere.model.MProductPrice"%>
<%@page import="org.posterita.businesslogic.administration.PriceListManager"%>
<%@page import="org.posterita.user.WebUserInfo"%>
<%@page import="org.compiere.model.MOrg"%>
<%@page import="org.compiere.model.MRole"%>
<%@page import="org.posterita.core.Configuration"%>
<%@page import="org.compiere.model.MPriceList"%>
<%@page import="org.compiere.model.MCurrency"%>
<%@page import="org.posterita.order.UDIOrderTypes"%>
<%@page import="org.posterita.businesslogic.administration.RoleManager"%>
<%@page import="org.posterita.businesslogic.MenuManager"%>
<%@page import="org.posterita.businesslogic.stock.MMovementManager"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<base href="<%=basePath%>" />
	<title><%=appName%></title>						
	<script src="js/enableButton.js"></script>				
	<script src="js/pos.js"></script>		
	<script src="javascripts/prototype.js"></script>
 	<script src="javascripts/scriptaculous.js"></script>
 	<script src="javascripts/sorttable.js"></script>
 	<script src="javascripts/sorttable.js"></script>
 	<script src="javascripts/modalbox.js"></script>
 	<script src="js/tooltip.js"></script>
 	<script src="js/dom-drag.js"></script>
 	<script src="js/stockCart.js"></script>
 	<script src="js/shortcut.js"></script>
 	<script src="js/stockTransfer.js"></script>
 	<link rel="shortcut icon" href="images/posterita.jpg" type="image/jpg" /> 
	<link type="text/css" href="css/orderScreen.css" rel="stylesheet" />
	<link type="text/css" href="css/modalbox.css" rel="stylesheet" />
</head>
<body onload="$('barcode').focus()">
<%@ include file="/jsp/include/showErrors.jsp" %>
<div id="indicator"><img src="images/pos/indicator.gif"/> Please wait...</div>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="75%">
	    <div id="posHeader">
	    	<div id="menuBtn" class="backButn" onclick="redirect('GetMenuItemsAction.do?action=getMenuItems&menuId=<%=menuId%>')">
	    		<img src="images/newUI/back-menu.gif" alt="Back to main system memu" width="28px" height="41px" border="0px"/>
			</div>
			<div id="logo">
				<img src="images/newUI/logo.gif" alt="Powered by Posterita POS" width="133px" height="41px" border="0px"/>
			</div>
			<div id="mainTitle">
				STOCK TRANSFER
			</div>
			<div id="acDetailsContainer">
				<div id="acDetailsR">
					<div id="org">
						<pos:message key="smenu.organisation" textOnly="true"/>:
						<span class="strong"><%=orgName%></span>
					</div>
					<div id="terminal">
						<pos:message key="pos.terminal" textOnly="true"/>:
						<span class="strong"><%=posName%></span>
					</div>
				</div>
				<div id="acDetailsL">
					<div id="user">
						<pos:message key="user" textOnly="true"/>:
						<span class="strong"><c:out value ='${webUserInfo.user.name}'/></span>
					</div>
					<div id="role">
						<pos:message key="smenu.role" textOnly="true"/>:
						<span class="strong"><%=roleName%></span>
					</div>
				</div>
			</div>
		</div>
		</td>
      <td width="25%">
      	<div id="priceContainer">
      		<div id="priceContainerBg">
      			<div id="priceValue">
      				<div id="total">STATUS: DRAFTED</div>
      			</div>
      		</div>
      	</div>
      </td>
    </tr>
    <tr>
    	<td width="75%">
    		<div id="productFieldsContainer">
    			<div id="productFields">
	    			<form action="" id="mainform" onsubmit="return false">
		    			<table id="maintop" width="100%">
		    				<tbody>
		    					<tr>
		    						<td>
		    							BARCODE:<input type="text" id="barcode" />
										<input type="hidden" name="productId" id="productId"/>
		    						</td>
		    						<td>
		    							NAME:<input type="text" id="productQuery"/>
										<div id="productSearchResult" class="autocomplete"></div>
									</td>
		    						<td>
		    							DESC:<input type="text" id="description"/>
										<div id="productSearchByDescriptionResult" class="autocomplete"></div>
		    						</td>
		    					</tr>
		    				</tbody>
		    			</table>
	    			</form>	
    			</div>
    		</div>
    	</td>
    	<td width="25%">
    		<div id="priceListDetailsContainer">
    			<div id="priceListDetails">
    				<%=priceListName%>
    			</div>
    		</div>
    	</td>
    </tr>
    <tr height="100%">
    	<html:form action="/StockMovementAction" styleId="StockMovementForm" onsubmit="return false;" method="get">
	 		<html:hidden property="action" value="completeMMovement"/>
	 		<html:hidden property="movementId" value="<%=movementId + ""%>" styleId="movementId"/>
			<html:hidden property="priceListId" value="<%=priceListId.toString()%>" styleId="priceListId"/>
			<input type="hidden" name="searchProductBy" value="<%=searchProductBy%>" id="searchProductBy"/>
    		<td width="75%" valign="top" height="100%">
	    		<div id="contentContainer">
	    			<div id="content">
		    			<div id="shoppingCart">
		    				<%= MMovementManager.getMMovementCartAsHTML(request)%>
		    			</div>
		    		</div>
	    		</div>
	    	</td>
	    	<td width="25%" valign="top" height="100%">
	    		<div id="productDetailsContainer">
	    			<div id="productDetails">
	    				<div id="productImage">
	    					<img src="" id="product.image"/>
	    				</div>
	    				<table class="alignDetails" cellspacing="0px" cellpadding="0px" id="productDetailsTable">
	    					<tr>
	    						<td>
	    							Barcode
	    						</td>
	    						<td>
	    							<span class="productValue" id="product.barcode"></span>
	    						</td>
	    					</tr>
	    					<tr>
	    						<td>
	    							Name
	    						</td>
	    						<td>
	    							<span class="productValue" id="product.name"></span>
	    						</td>
	    					</tr>
	    					<tr>
	    						<td>
	    							Description
	    						</td>
	    						<td>
	    							<span class="productValue" id="product.description"></span>
	    						</td>
	    					</tr>
	    					<tr>
	    						<td>
	    							Tax Cat
	    						</td>
	    						<td>
	    							<span class="productValue" id="product.taxCategory"></span>
	    						</td>
	    					</tr>
	    					<tr>
	    						<td>
	    							Stock Qty
	    						</td>
	    						<td>
	    							<span class="productValue" id="product.stock.qty"></span>
	    						</td>
	    					</tr>
	    					<tr>
	    						<td>
	    							Price List
	    						</td>
	    						<td>
	    							<span class="productValue" id="product.price.list"></span>
	    						</td>
	    					</tr>
	    					<tr>
	    						<td>
	    							Units Per Pack
	    						</td>
	    						<td>
	    							<span class="productValue" id="product.units.pack"></span>
	    						</td>
	    					</tr>
	    				</table>
	    			</div>
	    			<div id="editProductContainer" style="display:block; overflow:hidden; height:50px;">
	    				<div id="editProductDetails">
	    					<input type="text" id="qty" size="5"/>
	    					<img id="plusBtn" src="images/newUI/butn-add.gif" alt="Increase item quantity" width="30px" height="30px" border="0px"/>
	    					<img id="minusBtn" src="images/newUI/butn-subtract.gif" alt="Decrease item quantity" width="30px" height="30px" border="0px"/>
							<img id="deleteBtn" src="images/newUI/butn-delete.gif" alt="Remove item" width="30px" height="30px" border="0px"/>
						</div>
	    			</div>
	    			<hr />
	    			<div id="quickDiscountDetails">
			    		<table id="discountPanel" border="0" width="100%">
		  				<tr>
		  					<td  width="30%">
		   						<label>FROM ORG: </label>
		   					</td>
		   					<td  width="70%">
		   						<label>
		   							<html:select property="orgFromId" styleClass="text" value="<%=org%>" styleId="orgFromId" onchange="setOrgFromTo();">
										<html:options collection="<%=Constants.USER_ORGS_STOCK%>" property="key" labelProperty="name" />
								 	</html:select>
		   						</label>
		   					</td>
		   				</tr>
		   				<tr>
							<td width="30%">
		   						<label>TO ORG: </label>
		   					</td>
		   					<td  width="70%">
		   						<label>
		   							<html:select property="orgToId" styleClass="text" styleId="orgToId" onchange="setOrgFromTo();">
										<html:options collection="<%=Constants.USER_ORGS_STOCK%>" property="key" labelProperty="name"/>
									</html:select>
		   						</label>
		   					</td>   						
		   				</tr>
		   				</table>
		   				<hr />
		    		</div>
    				<table id="docStatus">
		    			<tr>
		    				<td>DOCUMENT NO: </td>
		    				<td><span class="productValue" id="docNo"></span></td>
		    			</tr>
		    			<tr>
		    				<td>REFERENCE: </td>
		    				<td><html:text property="reference" value="" styleId="reference"/></td>
		    			</tr>
		    		</table>
	    		</html:form>
	    	</div>
	    </td>
    </tr>
    <tr>
    	<td width="75%">
    		<div id="systemButnContainer">
    			<div id="systemButn">
    				<img id="newStockBtn" src="images/newUI/butn-new.gif" alt="New Stock Tansfer" width="95px" height="23px" border="0px"/>
    				<img id="clearCartBtn" src="images/newUI/butn-clearall.gif" alt="Clear all items" width="95px" height="23px" border="0px"/>
				</div>
    		</div>
    	</td>
    	<td width="25%">
    		<div id="checkoutContainer">
    			<div id="checkout">
    				<img id="saveBtn" src="images/newUI/btun_save.gif" alt="Save" width="126px" height="25px" border="0px" />
    				<img id="completeBtn" src="images/newUI/btun_complete.gif" alt="Complete" width="126px" height="25px" border="0px" />
				</div>
    		</div>
    	</td>
    </tr>
    <tr>
    	<td colspan="2">
	  		<div id="controls">
		  		<div>
				  	<B>F1</B> Barcode | <B>F2</B> Name | <B>F3</B> Description | <B>F4</B> Quantity | <B>Ctrl+Up</B> MoveUp | <B>Ctrl+Down</B> MoveDown | <B>Ctrl+Left</B> Decrement | <B>Ctrl-Right</B> Increment | <B>Ctrl-Backspace</B> Remove | <B>Ctrl+M</B> Menu | 
					<B>Ctrl-S</B> Save | <B>Ctrl-Delete</B> Clear | <B>Ctrl+Space</B> Checkout | <B>Ctrl+C</B> Complete
			 	 </div>
			 </div>
  		</td>
    </tr>
   </table>
	<div>
	<applet archive="<%= basePath + "applets/printOrderApplet.jar"%>" code="org.posterita.core.PrintOrderApplet.class" width="0" height="0"></applet>
	</div>
	<script>
	function printOrder(orderId)
	{
		var url = '<%=basePath + "ReprintOrderAction.do?action=getPrintOrderData&openDrawer=false&orderId="%>';
		document.applets[0].printURL(url + orderId);
	}
	
	var sessionId = '<%=request.getSession().getId()%>';
	
	showErrors();
	</script>
	<div id='pop_overlay'>
	</div>
	<div id='pop_container'>
    </div>
   </body>
</html>
