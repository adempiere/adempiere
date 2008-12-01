<!-- productBarcodeCart.jsp -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	Properties ctx = TmkJSPEnv.getCtx(request);
	String posName = POSTerminalManager.getTerminalName(ctx);
	int menu_id = MenuManager.getMenuId(ctx, "pmenu.administration");
	String orderType = "Barcode Printing";
	String orgName = MOrg.get(ctx, Env.getContextAsInt(ctx, UdiConstants.ORG_ID_CTX_PARAM)).getName();
	String roleName = MRole.get(ctx, Env.getContextAsInt(ctx, UdiConstants.AD_ROLE_ID)).getName();
	int m_pricelist_id = Env.getContextAsInt(ctx, UdiConstants.PRICELIST_CTX_PARAM);
	MPriceList priceList = MPriceList.get(ctx, m_pricelist_id, null);
	String priceListName = priceList.getName();
	String appName = (String) request.getSession().getAttribute(Constants.APP_NAME);
	
	//set configuration
	Configuration configuration = Configuration.getConfiguration(request);
	String searchProductBy = configuration.getSearchProductBy();
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
<%@page import="org.posterita.businesslogic.POSTerminalManager"%>
<%@page import="org.posterita.TangoColors"%>
<%@page import="org.posterita.businesslogic.MenuManager"%>
<%@page import="org.posterita.core.Configuration"%>
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
 	<script src="js/tooltip.js"></script>
 	<script src="js/dom-drag.js"></script>
 	<script src="js/shortcut.js"></script>
 	<script src="js/barcodeCart.js"></script>
 	<script src="js/barcode.js"></script>
 	<link rel="shortcut icon" href="images/posterita.jpg" type="image/jpg" /> 
	<link type="text/css" href="css/orderScreen.css" rel="stylesheet" />
</head>

<body>

<div id="indicator"><img src="images/pos/indicator.gif"/> Please wait...</div>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="75%">
	    <div id="posHeader">
	    	<div id="menuBtn" class="backButn" onclick="get('<html:rewrite action='/GetMenuItemsAction.do?action=getMenuItems'/>')">
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
      				<div id="total"></div>
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
	 	<html:form action="/BarcodeAction" styleId="checkoutForm">
			<html:hidden property="action" value="printProductBarcode"/>
			<html:hidden property="isPrintPrices" value="true" styleId="isPrintPrices"/>
			<html:hidden property="priceListId" value="<%=m_pricelist_id + ""%>" styleId="priceListId"/>
			<html:hidden property="orderType" value="<%=orderType%>" styleId="orderType"/>
			<input type="hidden" name="searchProductBy" value="<%=searchProductBy%>" id="searchProductBy"/>
		<td width="75%" valign="top" height="100%">
    		<div id="contentContainer">
    			<div id="content">
	    			<div id="shoppingCart">
	    				<%= ShoppingcartManager.getBarcodecartAsHTML(request)%>
	    			</div>
	    		</div>
    		</div>
	    </td>
	  	<td width="25%" valign="top" height="100%">
	    		<div id="productDetailsContainer">
	    			<div id="productDetails">
	    				<div id="productImage">
	    					<div class="barcodeImage">
	    						<span id="barcode.product.name"></span>
								<span id="barcode.product.description1"></span><br/>
								<span id="barcode.product.description2"></span><br/>
								<img src="" id="product.image"/><br/>
								<div id="barcodePrice">
									<span id="terminal.currency.sysmbol"></span>
									<span id="barcode.product.price.std"></span>
								</div>
							</div>
	    				</div>
	    				<table class="alignDetails" cellspacing="0px" cellpadding="0px">
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
	    			
	    			<div id="editProductContainer">
	    				<div id="editProductDetails">
	    					<input type="text" id="qty" size="5"/>
	    					<img id="plusBtn" src="images/newUI/butn-add.gif" alt="Increase item quantity" width="30px" height="30px" border="0px"/>
	    					<img id="minusBtn" src="images/newUI/butn-subtract.gif" alt="Decrease item quantity" width="30px" height="30px" border="0px"/>
							<img id="deleteBtn" src="images/newUI/butn-delete.gif" alt="Remove item" width="30px" height="30px" border="0px"/>
						</div>
	    			</div>
	    			<div id="choosePrintDetailsContainer">
    					<table border="0" cellpadding="0" cellspacing="0" width="100%">
    						<tr>
    							<td>
    								<span>Do you want to print labels with:</span>
    								<br/><input type="checkbox" id="isProductName" value="isProductName" checked="checked" onclick="checkPrintOptions()" />&nbsp;Product Name</br>
    								<br/><input type="checkbox" id="isProductDescription" value="isProductDescription" onclick="checkPrintOptions()" />&nbsp;Product Description</br>
    								<br/><input type="checkbox" id="isProductPrices" value="isProductPrices" checked="checked" onclick="checkPrintOptions2()" />&nbsp;Product Prices</br>
    							</td>
    						</tr>
    					</table>
					</div>
	    			<div id="changePricelistContainer">
	    				<div id="changePricelist">
					    	<table border="0" cellpadding="0" cellspacing="0" width="100%">
					    		<tr>
					    			<td id="priceListName">
					    				<%=priceListName%> 
					    			</td>
					    			<td id="priceLists">
					    			 	<div class="buttonLeft" id="changePriceList" onclick="loadPriceLists()">CHANGE</div>
					    			</td>
					    		</tr>
					    	</table>
				    	</div>
					</div>
				</div>
	    	</td>
    	</html:form>
    </tr>
	<tr>
    	<td width="75%">
    		<div id="systemButnContainer">
    			<div id="systemButn">
    				<img id="openDrawerBtn" src="images/newUI/butn-opendrawer.gif" alt="Open Drawer" width="95px" height="23px" border="0px"/>
    				<img id="clearCartBtn" src="images/newUI/butn-clearall.gif" alt="Clear all items" width="95px" height="23px" border="0px"/>
				</div>
    		</div>
    	</td>
    	<td width="25%">
    		<div id="rightBtnContainer">
    			<div id="printBarcodeBtn" class="buttonRight">PRINT</div>
			</div>
    	</td>
    </tr> 
	<tr>
	  	<td colspan="2">
	  		<div id="controls">
			  	<B>F1</B> Barcode | <B>F2</B> Name | <B>F3</B> Description | <B>F4</B> Qty | <B>Ctrl+Up</B> MoveUp | <B>Ctrl+Down</B> MoveDown | <B>Ctrl+Left</B>
			  	Increment | <B>Ctrl-Right</B> Decrement | <B>Ctrl-Backspace</B> Remove | <B>Ctrl-Space</B> Open Drawer | <B>Ctrl-Delete</B> Clear | <B>Ctrl-P</B>
			  	Print Barcode | <B>Ctrl+M</B> Menu
		  	</div>
	  	</td>
  	</tr>
</table>
<div>
	<applet archive="<%= basePath + "applets/printOrderApplet.jar"%>" code="org.posterita.core.PrintOrderApplet.class" width="0" height="0"></applet>
</div>

<logic:present cookie="preference.printing">
<logic:equal cookie="preference.printing" property="value" value="true">
	<bean:cookie id="cookie" name="preference.printer"/>
	<script>
				
		function printProductBarcode()
		{
			var isPrintPrices = $('isProductPrices').checked;
			var isPrintProductName = $('isProductName').checked;
			var isPrintProductDescription = $('isProductDescription').checked;
			
			if(isPrintProductName == false && isPrintProductDescription == false)
			{
				alert('You need to choose either \nproduct name, \nproduct description \nor both!!!');
				return;
			}
			
			try
			{				
				
				document.applets[0].setPrinterName(<%="'" + cookie.getValue() + "'"%>);
				var url = '<%=basePath + "BarcodeAction.do?action=printProductBarcode"%>' + '&isPrintPrices=' + isPrintPrices + '&isPrintProductName=' + isPrintProductName + '&isPrintProductDescription=' + isPrintProductDescription;
				document.applets[0].printURL(url);
			}
			catch(e)
			{
				toConsole(e);
			}
		}
	</script>
</logic:equal>
</logic:present>

<logic:present cookie="preference.printing">
<logic:equal cookie="preference.printing" property="value" value="false">
<bean:cookie id="cookie" name="preference.printer"/>
<script>
	function printProductBarcode()
	{
		var isPrintPrices = $('isProductPrices').checked;
		var isPrintProductName = $('isProductName').checked;
		var isPrintProductDescription = $('isProductDescription').checked;
		
		if(isPrintProductName == false && isPrintProductDescription == false)
		{
			alert('You need to choose either \nproduct name, \nproduct description \nor both!!!');
			return;
		}
		
		try
		{
			
			document.applets[0].setPrinterName(<%="'" + cookie.getValue() + "'"%>);
			var url = '<%=basePath + "BarcodeAction.do?action=printProductBarcode"%>' + '&isPrintPrices=' + isPrintPrices + '&isPrintProductName=' + isPrintProductName + '&isPrintProductDescription=' + isPrintProductDescription;
			document.applets[0].printURL(url);
		}
		catch(e)
		{
			toConsole(e);
		}
	}
</script>
</logic:equal>
</logic:present>

<logic:notPresent cookie="preference.printing">
<script>
	function printProductBarcode()
	{
		alert("Please set your printing preferences!!!");
	}
</script>
</logic:notPresent>

</body>
</html>
