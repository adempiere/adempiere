<!-- orderScreen.jsp -->
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
	String orderType = configuration.getOrderType();
	boolean isSOTrx = configuration.getIsSOTrx();
	String paymentRule = configuration.getPaymentRule();
	String searchProductBy = configuration.getSearchProductBy();
	boolean isCustomerCompulsory = configuration.isCustomerCompulsory();
	boolean isTerminalLocked = POSTerminalManager.isTerminalLocked(ctx);
	
	Integer priceListId = PriceListManager.getDefaultPriceListId(ctx, isSOTrx);
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
	
	String bpartnerType = "Customer";
 	if (!isSOTrx)
	{
		bpartnerType = "Vendor";
	}
 	
 	String showComponent = "";
 	
 	if(orderType.equals(UDIOrderTypes.CUSTOMER_RETURN_ORDER.getOrderType()) ||
 			orderType.equals(UDIOrderTypes.POS_GOODS_RETURN_NOTE.getOrderType())
 			)
 	{
 		showComponent = "style='display:none'";
 	}
 	
 	String advancedAction = "/LoadAdvancedOrderScreen.do?action=loadAdvancedOrderScreen&isSOTrx="+isSOTrx;
 	
 	int roleId = Env.getAD_Role_ID(ctx);                
    float discountAllowed = RoleManager.getDiscountAllowed(ctx, roleId, null).floatValue(); 
    boolean isAllowedOverridePriceLimit = RoleManager.isOverridePriceLimitAllowed(ctx, roleId, null);
    boolean isDiscountUptoLimitPrice = RoleManager.isDiscountUptoPriceLimit(ctx, roleId, null);
    boolean isDiscountAllowedOnTotal = RoleManager.isDiscountAllowedOnTotal(ctx, roleId, null);
    
    int menuId = MenuManager.getMenuId(ctx, "pmenu.cash.sales");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="org.posterita.Constants"%>
<%@page import="java.util.Properties"%>
<%@page import="org.posterita.core.TmkJSPEnv"%>
<%@page import="org.compiere.util.Env"%>
<%@page import="org.posterita.lib.UdiConstants"%>
<%@page import="org.compiere.model.MPriceList"%>
<%@page import="org.compiere.model.MOrg"%>
<%@page import="org.compiere.model.MRole"%>
<%@page import="org.posterita.businesslogic.ShoppingcartManager"%>
<%@page import="org.posterita.core.Configuration"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="org.compiere.model.MCurrency"%>
<%@page import="org.compiere.util.WebSessionCtx"%>
<%@page import="org.posterita.user.WebUserInfo"%>
<%@page import="org.posterita.order.UDIOrderTypes.POSOrder"%>
<%@page import="org.posterita.order.UDIOrderTypes"%>
<%@page import="org.posterita.businesslogic.administration.PriceListManager"%>
<%@page import="org.posterita.businesslogic.POSTerminalManager"%>

<%@page import="org.posterita.businesslogic.administration.RoleManager"%>
<%@page import="org.posterita.businesslogic.MenuManager"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<base href="<%=basePath%>">
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
 	<script src="js/shoppingCart.js"></script>
 	<script src="js/shortcut.js"></script>
 	<script src="js/orderScreen.js"></script>
 	<link rel="shortcut icon" href="images/posterita.jpg" type="image/jpg" /> 
	<link type="text/css" href="css/orderScreen.css" rel="stylesheet" />
	<link type="text/css" href="css/modalbox.css" rel="stylesheet" />
</head>
<body>
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
				<%= orderType %>
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
      				<div id="total">0.00</div>
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
    	<html:form action="/CheckoutAction" styleId="checkoutForm">
	 		<html:hidden property="action" value="checkout"/>
			<html:hidden property="orderType" value="<%=orderType%>" styleId="orderType"/>
			<html:hidden property="priceListId" value="<%=priceListId.toString()%>" styleId="priceListId"/>
			<html:hidden property="tenderType" value="<%=paymentRule%>" styleId="tenderType"/>
			<html:hidden property="amountTendered" value="0"/>	
			<html:hidden property="amountRefunded" value="0"/>
			<html:hidden property="cashAmt" value="0"/>
			<html:hidden property="cardAmt" value="0"/>
			<html:hidden property="chequeAmt" value="0"/>
			<html:hidden property="cardNo" value=""/>
			<html:hidden property="chequeNo" value=""/>
			<html:hidden property="discountOnTotal" styleId="discountOnTotal" value=""/>	
			<html:hidden property="roundOffFactor" value = "<%= roundOffFactor.toString() %>" styleId="roundOffFactor"/>
			<html:hidden property="isDiscountApplied" value="" styleId="isDiscountApplied"/>
			<logic:equal name="<%= Constants.DISCOUNT_ON_ORDER_TOTAL_PERCENT%>" value="true">
				<html:hidden property="discountOnTotalPercent" styleId="discountOnTotalPercent" value="true"/>
			</logic:equal>
			<logic:equal name="<%= Constants.DISCOUNT_ON_ORDER_TOTAL_PERCENT %>" value="false">
				<html:hidden property="discountOnTotalPercent" styleId="discountOnTotalPercent" value="false"/>
			</logic:equal>
			<logic:equal name="<%= Constants.DISCOUNT_ON_ORDER_TOTAL%>" value="true">
				<html:hidden property="discountOnTotal" styleId="discountOnTotal" value="true"/>
			</logic:equal>
			<logic:equal name="<%= Constants.DISCOUNT_ON_ORDER_TOTAL %>" value="false">
				<html:hidden property="discountOnTotal" styleId="discountOnTotal" value="false"/>
			</logic:equal>
			<input type="hidden" name="searchProductBy" value="<%=searchProductBy%>" id="searchProductBy"/>
			<input type="hidden" name="isCustomerCompulsory" value="<%= isCustomerCompulsory %>" id="isCustomerCompulsory"/>
			<input type="hidden" id="bpartnerId" name="bpartnerId" value="<%=bPartnerId%>"/>
    		<td width="75%" valign="top" height="100%">
	    		<div id="contentContainer">
	    			<div id="content">
		    			<div id="shoppingCart">
		    				<%= ShoppingcartManager.getShoppingcartAsHTML(request, orderType)%>
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
	    							Price Std
	    						</td>
	    						<td>
	    							<span class="productValue" id="product.price.std"></span>
	    						</td>
	    					</tr>
	    					<tr>
	    						<td>
	    							Price Limit
	    						</td>
	    						<td>
	    							<span class="productValue" id="product.price.limit"></span>
	    						</td>
	    					</tr>
	    				</table>
	    			<div id="editProductContainer" style="display:block; overflow:hidden; height:50px;">
	    				<div id="editProductDetails">
	    					<input type="text" id="qty" size="5"/>
	    					<img id="plusBtn" src="images/newUI/butn-add.gif" alt="Increase item quantity" width="30px" height="30px" border="0px"/>
	    					<img id="minusBtn" src="images/newUI/butn-subtract.gif" alt="Decrease item quantity" width="30px" height="30px" border="0px"/>
							<img id="deleteBtn" src="images/newUI/butn-delete.gif" alt="Remove item" width="30px" height="30px" border="0px"/>
						</div>
	    			</div>
	    			<div id="customerContainer" style="display:block; height:100%;">
	    				<div id="customerDetails">
	    					<table cellspacing="0px" width="100%" class="alignDetails">
	    						<tr height="35px">
	    							<td>
	    								<%=bpartnerType%>
	    							</td>
	    							<td>
	    								<input type="text" id="customerQuery"/>
	    								<div id="customerSearchResult" class="autocomplete"></div>
	    							</td>
	    						</tr> 
	    						<tr id="customerCode">
	    							<td>
	    								Code
	    							</td>
	    							<td>
	    								<span id="customer.code" class="productValue"><%=bPartnerId%></span>
	    							</td>
	    						</tr>
	    						<tr id="customerName">
	    							<td>
	    								Name
	    							</td>
	    							<td>
	    								<span id="customer.name" class="productValue"><%=bPartnerName%></span>
	    							</td>
	    						</tr>
	    						<tr id="creditAvailable">
	    							<td>
	    								Credit Available
	    							</td>
	    							<td>
	    								<span id="credit.available" class="productValue"><%=bPartnerCredit%></span>
	    							</td>
		    					</tr>
		    					<tr id="creditLimit">
	    							<td>
	    								Credit Limit
	    							</td>
	    							<td>
	    								<span id="credit.limit" class="productValue"></span>
	    							</td>
		    					</tr>
		    					<tr id="openBalance">
	    							<td>
	    								Open Balance
	    							</td>
	    							<td>
	    								<span id="open.balance" class="productValue"></span>
	    							</td>
		    					</tr>
	    					</table>
	    				</div>
	    			</div>
	    			<div id="paymentContainer" style="display:block; height:100px;">
	    				<div id="paymentDetails">
	    					<h1>Payment Method</h1>
	    					<img id="cashBtn" src="images/newUI/butn-cash.gif" alt="Cash Payment" width="50px" height="58px" border="0px"/>
	    					<img id="cardBtn" src="images/newUI/butn-card.gif" alt="Card Payment" width="50px" height="58px" border="0px"/>
							<img id="chequeBtn" src="images/newUI/butn-cheque.gif" alt="Cheque Payment" width="50px" height="58px" border="0px"/>
							<br><img id="mixBtn" src="images/newUI/butn-mix.gif" alt="Mix Payment" width="50px" height="58px" border="0px"/>
							<img id="creditBtn" src="images/newUI/butn-credit.gif" alt="On Credit Payment" width="50px" height="58px" border="0px"/>
							</div>
	    				</div>
	    			</div>
	    		</html:form>
	    		<div id="quickDiscountDetails" style="display:none; overflow:hidden; height:250px;  width: 275px;">
				<hr />
				<h1 align="center" style="font-size: 11px">Quick Discount</h1>
				<html:form action="/CheckoutAction" styleId="QuickDiscountForm" onsubmit="return false;">
				<html:hidden property="action" value="setQuickDiscount"/>
				<input type="hidden" name="roleId" id="roleId" value="<%=roleId%>"/>
				<input type="hidden" name="taxRate" id="taxRate" value=""/>
				<input type="hidden" name="isDiscOnTotal" id="isDiscOnTotal" value="false"/>
				<input type="hidden" name="isDiscOnInclUnitPrice" id="isDiscOnInclUnitPrice" value="false"/>
				<input type="hidden" name="isDiscOnPerc" id="isDiscOnPerc" value="false"/>
				<html:hidden property="orderType" value="<%=orderType%>" styleId="orderType"/>
				<html:hidden property="priceListId" value="<%=priceListId.toString()%>" styleId="priceListId"/>
				<html:hidden property="m_productId" value="" styleId="m_productId"/>
  				<table id="discountPanel" border="0" width="100%">
  				<tr>
  					<td>
   						<label>Inc Price:</label>
   					</td>
   					<td>
   						<label>Disc %:</label>
   					</td>
   					<td>
   						<label>Total:</label>
   					</td>
   				</tr>
   				<tr>
					<td>
 						<input size="8" name="discountedPrice" id="incPrice" value=""/>
   					</td>
   					<td>
   						<input size="4" name="discount" id="disc" value="0.00" />
   					</td>    						
   					<td>
   						<input size="8" name="totalAmount" id="totalAmount" value=""/>
   					</td>
   				</tr>
   				<tr id="linesTotal">
   					<td colspan="2">GRAND TOTAL:</td>
   					<td>
   						<input size="8" name="grandTotal" id="grandTotal" value="0.00"/>
   					</td>
   				</tr>
   				<tr>
  						<td colspan="3">
   						<input id="applyDiscBtn" class="buttonRight" type="submit" value="APPLY" style="background-color: rgb(0, 0, 0); cursor: pointer; display: none"/>
   					</td>
   				</tr>
   				</table>
   				</html:form>
   				<hr />
   				<span id="errorMsg" style="font-size: 11px; color: white;text-decoration: none; background-color: #FF0000;"></span>
   				<hr />
   				<table border="0" style="font-size: 11px; font-weight: normal;">
					<tr id="discountUptoLimitPrice">
		   				<td colspan="2" align="left">
		   					Discount Upto Price Limit:
		   				</td>
		   				<td align="left">
		   					<span id="isDiscountUptoLimitPrice" style="font-weight: bold; color: black"><%=isDiscountUptoLimitPrice%></span>
		   				</td>
 					</tr>
 	  				<tr id="overridePriceLimit">
						<td colspan="2" align="left">
		   					Override Price Limit:
		   				</td>
		   				<td align="left">
		   					<span id="overrideLimit" style="font-weight: bold; color: black"><%=isAllowedOverridePriceLimit%></span>
		   				</td>
  					</tr>

  		   			<tr id="discountAllowedOnTotal">
		   				<td colspan="2" align="left">
		   					Allow discount on total:
		   				</td>
		   				<td align="left">
		   					 <span id="isDiscountOnTotal" style="font-weight: bold; color: black"><%=isDiscountAllowedOnTotal%></span>
		   				</td>
	   				</tr>
  			
		   			<tr id="userDiscLimit">
		   				<td colspan="2" align="left">
		   					Discount Limit:
		   				</td>
		   				<td align="left">
		   					 <span id="discountLimit" style="font-weight: bold; color: black"><%=discountAllowed%>%</span>
		   				</td>
		   			</tr>
		   			<tr>
		   				<td colspan="2" align="left">
		   					Price Limit:
		   				</td>
		   				<td align="left">
		   					 <span id="priceLimit" style="font-weight: bold; color: black"></span>
		   				</td>
		   			</tr>
	   				</table>
  				</div>
	    	</div>
	    </td>
    </tr>
    <tr>
    	<td width="75%">
    		<div id="systemButnContainer">
    			<div id="systemButn">
    				<img id="discountBtn" onclick="get('<html:rewrite action="<%=advancedAction%>"/>')" src="images/newUI/butn-discount.gif" alt="Discount" width="95px" height="23px" border="0px"/>
    				<img id="quickDiscountBtn" src="images/newUI/btn_quickdiscount.gif" alt="Quick Discount" width="95px" height="25px" border="0px"/>	
    				<img id="openDrawerBtn" src="images/newUI/butn-opendrawer.gif" alt="Open Drawer" width="95px" height="23px" border="0px"/>
    				<img id="clearCartBtn" src="images/newUI/butn-clearall.gif" alt="Clear all items" width="95px" height="23px" border="0px"/>
				</div>
    		</div>
    	</td>
    	<td width="25%">
    		<div id="checkoutContainer">
    			<div id="checkout">
    				<img id="changePinBtn" src="images/newUI/btn_changepin.gif" alt="Change PIN" width="126px" height="25px" border="0px" />
    				<img id="resetPinBtn" src="images/newUI/btn_resetuser.gif" alt="Reset USER" width="126px" height="25px" border="0px" onclick="resetUser('<%=roleId%>');"/>
    				<img id="checkoutBtn" src="images/newUI/butn-checkout.gif" alt="Proceed to Checkout" width="126px" height="25px" border="0px" />
				</div>
    		</div>
    	</td>
    </tr>
    <tr>
    	<td colspan="2">
	  		<div id="controls">
		  		<div>
				  	<B>F1</B> Barcode | <B>F2</B> Name | <B>F3</B> Description | <B>F4</B> Qty | <B>F5</B> Customer | <B>F6</B> Discount | <B>F8</B> Toggle Quick Discount | <B>F9</B> Cash | <B>F10</B> Card | <B>F11</B> Check | <B>F12</B> Mix | 
				  	<B>Ctrl+C</B> Credit | <B>Ctrl+Up</B> MoveUp | <B>Ctrl+Down</B> MoveDown | <B>Ctrl+Left</B> Decrement | <B>Ctrl-Right</B> Increment | <B>Ctrl-Backspace</B> Remove | <B>Ctrl+M</B> Menu | 
					<B>Ctrl-D</B> Open Drawer | <B>Ctrl-Delete</B> Clear | <B>Ctrl+Space</B> Checkout | <B>Ctrl+P</B> Change PIN | <span id="resetUser"><B>Ctrl+R</B> Reset User</span>		
			 	 </div>
			 </div>
  		</td>
    </tr>
   </table>
   <div id="cashPanel">
		<div id = "cash">
			<table border="0">
				<tr>
					<td><h3>Total:</h3></td>
					<td><span id="cashTotal"></span></td>
				</tr>
				<tr>
					<td>Amount Tendered:</td>
					<td><input type="text" id="amountTendered"/></td>
				</tr>
				<tr id="writeOffAmt">
					<td>Write Off Amount:</td>
					<td valign="bottom"><span id = "writeOffCash"></span></td>
				</tr>
				<tr id="actualPaymentAmt">
					<td>Payment Amount:</td>
					<td valign="bottom"><span id = "paymentCash"></span></td>
				</tr>
				<tr>
					<td>Amount Refunded:</td>
					<td valign="bottom"><span id="amountRefunded"></span></td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>			
					<td colspan="2" align="right">					
						<input type="button" value="OK" name="OK"/>
						<input type="button" value="Cancel" name="Cancel"/>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<div id="cardPanel">
		<div id = "card">			
			<table border="0">
				<tr>
					<td><h3>Total:</h3></td>
					<td><span id="cardTotal"></span></td>
				</tr>
				<tr>
					<td>Card</td>
					<td>
						<select id="cardList">
							<option value="V">VISA</option>
							<option value="M">MasterCard</option>
							<option value="A">Amex</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>Card No:</td>
					<td><input type="text" id="cardNo"/></td>
				</tr>
				<tr>			
					<td colspan="2" align="right">
						<input type="button" value="OK" name="OK"/>
						<input type="button" value="Cancel" name="Cancel"/>
					</td>
				</tr>
			</table>			
		</div>
	</div>
	<div id="chequePanel">
		<div id = "cheque">			
			<table border="0">
				<tr>
					<td><h3>Total:</h3></td>
					<td><span id="chequeTotal"></span></td>
				</tr>
				<tr>
					<td>Cheque No:</td>
					<td><input type="text" id="chequeNo"/></td>
				</tr>
				<tr>			
					<td colspan="2" align="right">
						<input type="button" value="OK" name="OK"/>
						<input type="button" value="Cancel" name="Cancel"/>
					</td>
				</tr>
			</table>			
		</div>
	</div>
	<div id="debitPanel">
		<div id = "debit">			
			<table border="0">
				<tr>
					<td><h3>Total:</h3></td>
					<td><span id="debitTotal"></span></td>
				</tr>			
				<tr>
					<td>Card No:</td>
					<td><input type="text" id="debit_cardNo"/></td>
				</tr>
				<tr>			
					<td colspan="2" align="right">
						<input type="button" value="OK" name="OK"/>
						<input type="button" value="Cancel" name="Cancel"/>
					</td>
				</tr>
			</table>			
		</div>
	</div>
	<div id="mixPanel">
		<div id = "mix">			
			<table border="0">
				<tr>
					<td><h3>Total:</h3></td>
					<td><span id="mixTotal"></span></td>
				</tr>
				<tr>
					<td>Cash Amt:</td>
					<td><input type="text" id="mix_cashAmt"/></td>
				</tr>
				<tr>
					<td>Cheque Amt:</td>
					<td><input type="text" id="mix_chequeAmt"/></td>
				</tr>
				<tr>
					<td>Cheque No:</td>
					<td><input type="text" id="mix_chequeNo"/></td>
				</tr>
				<tr>
					<td>Card Amt:</td>
					<td><input type="text" id="mix_cardAmt"/></td>
				</tr>
				<tr>
					<td>Card No:</td>
					<td><input type="text" id="mix_cardNo"/></td>
				</tr>
				<tr>			
					<td colspan="2" align="right">
						<input type="button" value="OK" name="OK"/>
						<input type="button" value="Cancel" name="Cancel"/>
					</td>
				</tr>
			</table>			
		</div>
	</div>
	
	<div id="creditPanel" class="displayNone">
	</div>
	<div id="PINPanel">
		<table border="0" width="100%" height="100%">
			<tr>
				<td valign="top" align="left">
					<img src="images/tango/system-password.png"/>
					<pos:message key="please.enter.pin" textOnly="true"/><br/>
				</td>
			</tr>
			<tr>
				<td>
					<input type="password" class="text" id="PIN" style="width:100%"/>
				</td>
			</tr>
			<tr>
				<td align="right">
				<div id="closeBtn">
					<form>
						<input type="button" value="OK"/>
						<input type="button" value="CANCEL"/>
					</form>
				</div>
				</td>
			</tr>
		</table>
	</div> 

	<div>
	<applet archive="<%= basePath + "applets/printOrderApplet.jar"%>" code="org.posterita.core.PrintOrderApplet.class" width="0" height="0"></applet>
	</div>
	<script>
	function printOrder(orderId)
	{
		var url = "<%=basePath %>ReprintOrderAction.do?action=getPrintOrderData&openDrawer=false&orderId=";
		document.applets[0].printURL(url + orderId);
	}
	
	var discount = <%=discountAllowed%>;
	function getDiscountAllowed()
	{
		return discount;
	}
	
	var overrideLimit = <%=isAllowedOverridePriceLimit%>;
	var isDiscountUptoLimitPrice = <%=isDiscountUptoLimitPrice%>
	var isDiscountAllowedOnTotal = <%=isDiscountAllowedOnTotal%>
	var sessionId = '<%=request.getSession().getId()%>';	
	</script>
	<div id='pop_overlay'>
	</div>
	<div id='pop_container'>
    </div>
   </body>
</html>
