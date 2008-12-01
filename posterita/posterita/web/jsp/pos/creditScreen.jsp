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
	//String appName = (String) request.getSession().getAttribute(Constants.APP_NAME);
	Properties ctx = TmkJSPEnv.getCtx(request);
	String posName = POSTerminalManager.getTerminalName(ctx);
	String orgName = MOrg.get(ctx, Env.getContextAsInt(ctx, UdiConstants.ORG_ID_CTX_PARAM)).getName();
	String roleName = MRole.get(ctx, Env.getContextAsInt(ctx, UdiConstants.AD_ROLE_ID)).getName();
	int m_pricelist_id = POSTerminalManager.getSOPriceListId(ctx);
	MPriceList priceList = MPriceList.get(ctx, m_pricelist_id, null);
	String priceListName = priceList.getName();
	String orderType = UDIOrderTypes.CREDIT_ORDER.getOrderType();
	request.getSession().setAttribute(Constants.ORDER_TYPE, orderType);
	
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
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="java.util.Properties"%>
<%@page import="org.posterita.core.TmkJSPEnv"%>
<%@page import="org.compiere.util.Env"%>
<%@page import="org.posterita.lib.UdiConstants"%>
<%@page import="org.compiere.model.MPriceList"%>
<%@page import="org.compiere.model.MOrg"%>
<%@page import="org.compiere.model.MRole"%>
<%@page import="org.posterita.businesslogic.ShoppingcartManager"%>
<%@page import="org.posterita.core.Configuration"%>
<%@page import="org.posterita.user.WebUserInfo"%>
<%@page import="org.posterita.businesslogic.POSTerminalManager"%>
<%@page import="org.posterita.order.UDIOrderTypes.CreditOrder"%>
<%@page import="org.posterita.order.UDIOrderTypes"%>
<%@page import="org.posterita.Constants"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<base href="<%=basePath%>">			
	<script src="js/enableButton.js"></script>				
	<script src="js/pos.js"></script>		
	<script src="javascripts/prototype.js"></script>
 	<script src="javascripts/scriptaculous.js"></script>
 	<script src="javascripts/sorttable.js"></script>
 	<script src="js/tooltip.js"></script>
 	<script src="js/dom-drag.js"></script>
 	<script src="js/shoppingCart.js"></script>
 	<script src="js/shortcut.js"></script>
 	<script src="js/creditScreen.js"></script>
 	<link rel="shortcut icon" href="images/posterita.jpg" type="image/jpg" /> 
	<link type="text/css" href="css/orderScreen.css" rel="stylesheet">
</head>

<body>
<%@ include file="/jsp/include/showErrors.jsp" %> 
<div id="indicator"><img src="images/pos/indicator.gif"> Please wait...</div>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="75%" height="600" valign="top" id="maincolumn">
    <div id="header" class="border">
    	<div id="logo">
				<img src="images/logo1.jpg" width="80px" height="25px">
		</div>
		<ul id="context">
			<li>
				<b><pos:message key="user" textOnly="true"/></b>:
				<c:out value ='${webUserInfo.user.name}'/>	
			</li>
			<li>
				<b><pos:message key="smenu.role" textOnly="true"/></b>:
				<%=roleName%>
			</li>
			<li>
				<b><pos:message key="smenu.organisation" textOnly="true"/></b>:
				<%=orgName%>
			</li>
			<li>
				<b><pos:message key="pos.terminal" textOnly="true"/></b>:
				<%=posName%>
			</li>
			<li class="floatRight">
				<%= orderType %>
			</li>
		</ul>
    </div>
    <br class="clear">
    
	<form action="" id="mainform" onsubmit="return false">
		<table id="maintop" width="100%">
		  <tr>
			<td>
				<label>BARCODE:</label><input type="text" id="barcode" />
				<input type="hidden" name="productId" id="productId"/>
			</td>
			<td>
				<label>NAME:</label><input type="text" id="productQuery"/>
				<div id="productSearchResult" class="autocomplete"></div>
			</td>
			<td>
				<label>DESC:</label><input type="text" id="description"/>
				<div id="productSearchByDescriptionResult" class="autocomplete"></div>
			</td>
		  </tr>
		</table>
	</form>
	
 <html:form action="/CheckoutAction" styleId="checkoutForm">
	<html:hidden property="action" value="checkout"/>
	<html:hidden property="orderType" value="<%=orderType%>" styleId="orderType"/>
	<html:hidden property="priceListId" value="<%=m_pricelist_id + ""%>" styleId="priceListId"/>
	<html:hidden property="tenderType" value="<%=paymentRule%>" styleId="tenderType"/>
	<html:hidden property="amountTendered" value="0"/>	
	<html:hidden property="amountRefunded" value="0"/>
	<html:hidden property="cashAmt" value="0"/>
	<html:hidden property="cardAmt" value="0"/>
	<html:hidden property="chequeAmt" value="0"/>
	<html:hidden property="cardNo" value=""/>
	<html:hidden property="chequeNo" value=""/>
	
	<input type="hidden" name="searchProductBy" value="<%=searchProductBy%>" id="searchProductBy"/>
	<input type="hidden" name="isCustomerCompulsory" value="true" id="isCustomerCompulsory"/>
	  
	  <div class="spacer"></div>
	  <div id="shoppingCart">
      	<%= ShoppingcartManager.getShoppingcartAsHTML(request)%>
      </div>
	  
	  <div class="spacer"></div>
	   
	  <div>
	  	<div class="button floatLeft" id="menuBtn" onclick="get('<html:rewrite action='/GetMenuItemsAction.do?action=getMenuItems'/>')">BACK TO MENU</div>		
		<div class="button floatLeft" id="clearCartBtn">CLEAR</div>	
		<div class="button floatLeft" id="shortcutBtn">SHORTCUTS</div>	
		
		<div class="button floatRight" id="checkoutBtn">CHECKOUT</div>	
		<div class="button floatRight" id="discountBtn" onclick="get('<html:rewrite action='/LoadAdvancedOrderScreen.do?action=loadAdvancedOrderScreen'/>')">DISCOUNT</div>
	  </div>	
	 <br class="clear"/>
	 <br class="clear"/>	  
	  <div id="shortcuts">
	  	<B>F1</B> Barcode | <B>F2</B> Name | <B>F3</B> Description | <B>F4</B> Qty | <B>F5</B> Customer | 
	  	<B>Ctrl+Up</B> MoveUp | <B>Ctrl+Down</B> MoveDown | <B>Ctrl+Left</B> Increment | <B>Ctrl-Right</B> Decrement | <B>Ctrl-Backspace</B> Remove | <B>Ctrl-Esc</B> Menu | 
	  	<B>Ctrl-Delete</B> Clear | <B>Ctrl-Space</B> Checkout | <B>Ctrl-Esc</B> Menu</div>    		
	  </div>
	  
	
	
	 
	<!-- The right column -->  
    <td width="25%" valign="top" id="rightcolumn">
	<div id="total" class="border">$0.00</div>
	<div class="spacer"></div>
	
    <div id="terminal">
		<%=priceListName%>
	</div>
	<div class="spacer"></div>
	
	<div id="productinfo">
	  <label>BARCODE</label><span id="product.barcode"></span><br class="clear">
	  <label>NAME</label><span id="product.name"></span><br class="clear"> 
	  <label>DESCRIPTION</label><span id="product.description"></span><br class="clear">
	  <label>TAX CATEGORY</label><span id="product.taxCategory"></span><br class="clear">
	  <label>STOCK QTY</label><span id="product.stock.qty"></span><br class="clear">	  
	 
	  <table border="0" cellpadding="0" cellspacing="0" width="100%">
	  	<tr>	  		
	  		<td valign="top">
	  			<label>PRICE STD</label><span id="product.price.std"></span><br class="clear">	  		
	  			<label>PRICE LIST</label><span id="product.price.list"></span><br class="clear">	  		
	  			<label>PRICE LIMIT</label><span id="product.price.limit"></span><br class="clear">
	  		</td>
	  		<td>
	  			<img src="" id="product.image" class="productImage">
	  		</td>
	  	</tr>
	  </table>
	</div>
	<div class="spacer"></div>
	
	<table id="buttons" width="100%" border="1">
		<tr>
			<td width="25%" style="padding:0px;">
				<input type="text" id="qty" size="3"/>
			</td>
			<td width="25%" id="plusBtn">&nbsp;</td>
			<td width="25%" id="minusBtn">&nbsp;</td>
			<td width="25%" id="deleteBtn">&nbsp;</td>
		</tr>
	</table>
	<div class="spacer"></div>
	
	<div id="customerinfo">
	  <input type="hidden" id="bpartnerId" name="bpartnerId" value="<%=bPartnerId%>"/>
	  <label>CUSTOMER</label> <input type="text" id="customerQuery"/><br class="clear"> 
	  <div id="customerSearchResult" class="autocomplete"></div>
	  <label>CODE</label><span id="customer.code"><%=bPartnerId%></span><br class="clear"> 
	  <label>NAME</label><span id="customer.name"><%=bPartnerName%></span><br class="clear"> 
	  <div style="display:none">
	  <label>CREDIT AVAILABLE</label><span id="credit.available"><%=bPartnerCredit%></span><br class="clear">	
	  </div>
	</div>
	
	<div class="spacer"></div><br class="clear"> 
	<!-- 
	<div>
		<fieldset>
			<legend><pos:message key="shipment.no" /></legend>
			<div style="height:40px">
			<table border="0" cellpadding="5">
				<tr>				
					<html:select property="toBeShipped" value="true" onchange="setShipmentRequired(this)" styleClass="text">
						<html:option value="true"><pos:message key="shipment.required" /></html:option>	
						<html:option value="false"><pos:message key="no.shipment" /></html:option>																														
  				   </html:select>		
				</tr>
			</table>
			</div>
		</fieldset>		
	</div>
	 -->
	 <html:hidden property="toBeShipped" value="true"/>
	</html:form>	
	</td>
  </tr>
</table>
</body>
</html>
