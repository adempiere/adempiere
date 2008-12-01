<!-- adjustStock.jsp -->
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
	
	int menu_id = MenuManager.getMenuId(ctx, "pmenu.stock");
	
	String orderType = "Stock Adjustment";
	int purchasePriceListVersionId=Env.getContextAsInt(ctx,UdiConstants.POS_PURCHASE_PL_VERSION);
	MPriceListVersion priceListVersion = new MPriceListVersion(ctx,purchasePriceListVersionId,null);
	int m_pricelist_id = priceListVersion.getM_PriceList_ID();
	MPriceList priceList = new MPriceList(ctx,m_pricelist_id,null);
	
	int inventoryId = Env.getContextAsInt(ctx, Constants.INVENTORY_ID);
	String description = Env.getContext(ctx, Constants.DESCRIPTION);
	long currentTimeInMillis = System.currentTimeMillis();
    Date today = new Date( currentTimeInMillis );
	if(description == "")
	{
		description = "Stock Adjustment - " + today;
	}
	
	String docNo = (String)request.getSession().getAttribute(Constants.DOC_NO);	
	if(docNo == "" || docNo == null)
	{
		docNo = "N/A";
	}
	
	String docStatus = (String)request.getSession().getAttribute(Constants.DOC_STATUS);	
	if(docStatus == "" || docStatus == null)
	{
		docStatus = "N/A";
	}
	else
	{
        
        if (DocAction.STATUS_Drafted.equals(docStatus))
        	docStatus = Constants.DOC_STATUS_DRAFTED;
        else
        if (DocAction.STATUS_InProgress.equals(docStatus))
        	docStatus = Constants.DOC_STATUS_INPROGRESS;
        else
        if (DocAction.STATUS_Completed.equals(docStatus))
        	docStatus = Constants.DOC_STATUS_COMPLETED;
        else
        if (DocAction.STATUS_Closed.equals(docStatus))
        	docStatus = Constants.DOC_STATUS_CLOSED;
        else
        if (DocAction.STATUS_Invalid.equals(docStatus))
        	docStatus = Constants.DOC_STATUS_INVALID;
        else
            if (DocAction.STATUS_Voided.equals(docStatus))
            	docStatus = Constants.DOC_STATUS_VOID;
	}
	
	String priceListName = priceList.getName();
	MCurrency currency = MCurrency.get(ctx, priceList.getC_Currency_ID());
	BigDecimal roundOffFactor = currency.getRoundOffFactor();
	
	//set configuration
	Configuration configuration = Configuration.getConfiguration(request);
	String paymentRule = configuration.getPaymentRule();
	String searchProductBy = configuration.getSearchProductBy();
	boolean isCustomerCompulsory = configuration.isCustomerCompulsory();
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="org.posterita.Constants"%>
<%@page import="java.util.Properties"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="org.posterita.core.TmkJSPEnv"%>
<%@page import="org.compiere.model.MPOS"%>
<%@page import="org.compiere.util.Env"%>
<%@page import="org.posterita.lib.UdiConstants"%>
<%@page import="org.compiere.model.MPriceList"%>
<%@page import="org.compiere.model.MOrg"%>
<%@page import="org.compiere.model.MRole"%>
<%@page import="org.posterita.businesslogic.stock.InventoryCartManager"%>
<%@page import="org.posterita.businesslogic.POSTerminalManager"%>
<%@page import="org.posterita.core.Configuration"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="org.compiere.model.MCurrency"%>
<%@page import="org.compiere.model.MPriceListVersion"%>
<%@page import="org.compiere.util.WebSessionCtx"%>
<%@page import="org.posterita.user.WebUserInfo"%>

<%@page import="org.posterita.businesslogic.MenuManager"%>
<%@page import="org.compiere.process.DocAction"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<base href="<%=basePath%>">
	<title><%=appName%></title>						
	<script src="js/enableButton.js"></script>				
	<script src="js/pos.js"></script>		
	<script src="javascripts/prototype.js"></script>
 	<script src="javascripts/scriptaculous.js"></script>
 	<script src="javascripts/modalbox.js"></script>
 	<script src="javascripts/sorttable_v2.js"></script>
 	<script src="javascripts/tablefilter.js"></script>
 	<script src="javascripts/actb.js"></script>
 	<script src="js/tooltip.js"></script>
 	<script src="js/dom-drag.js"></script>
 	<script src="js/inventoryCart.js"></script>
 	<script src="js/shortcut.js"></script>
 	<script src="js/inventory.js"></script>
 	<link rel="shortcut icon" href="images/posterita.jpg" type="image/jpg" /> 
	<link type="text/css" href="css/inventory.css" rel="stylesheet" />
	<link rel="stylesheet" href="css/modalbox.css" type="text/css" media="screen" />
	<link rel="stylesheet" href="css/orderScreen.css" type="text/css" />
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
    				<div id="cartSearch">
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
	    			<div id="sortPanel" style="display:none">
						<form class="filter">
							<table id="maintop" width="100%">
								<tr>
									<td>
										<label>SEARCH PRODUCT:</label><input id="filter" name="filter" onkeyup="filter2(this, 'inventoryCart', 1)" type="text">
									</td>
									<td>
									</td>
							  	</tr>
							</table>
						</form>
					</div>
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
	 	<html:form action="/InventoryCartAction" styleId="inventoryForm" onsubmit="return false;">
			<html:hidden property="action" value="completeInventoryAdjustment"/>
			<html:hidden property="priceListId" value="<%=m_pricelist_id + ""%>" styleId="priceListId"/>
			<html:hidden property="description" value="<%=description + ""%>" styleId="invdescription"/>
			<html:hidden property="inventoryId" value="<%=inventoryId + ""%>" styleId="inventoryId"/>
			<html:hidden property = "roundOffFactor" value = "<%= roundOffFactor + ""%>" styleId = "roundOffFactor" />
			<input type="hidden" name="searchProductBy" value="<%=searchProductBy%>" id="searchProductBy"/>
			<input type="hidden" name="isCustomerCompulsory" value="<%=isCustomerCompulsory + ""%>" id="isCustomerCompulsory"/>
			<td width="75%" valign="top" height="100%">
	    		<div id="contentContainer">
	    			<div id="content">
		    			<div id="shoppingCart">
		    				<%= InventoryCartManager.getInventoryCartAsHTML(request)%>
		    			</div>
		    		</div>
	    		</div>
	   		 </td>
		  	 <td width="25%" valign="top" height="100%">
	    		<div id="productDetailsContainer">
	    			<div id="productDetails">
	    				<div id="productImage">
	    					<img src="" id="product.image"/><br/>
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
	    							Qty Book
	    						</td>
    						<td>
    							<span class="productValue" id="product.stock.qtyBook"></span>
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
    				</table>
    			
    			<div id="editProductContainer">
    				<div id="editProductDetails">
    					<input type="text" id="qty" size="5"/>
    					<img id="plusBtn" src="images/newUI/butn-add.gif" alt="Increase item quantity" width="30px" height="30px" border="0px"/>
    					<img id="minusBtn" src="images/newUI/butn-subtract.gif" alt="Decrease item quantity" width="30px" height="30px" border="0px"/>
						<img id="deleteBtn" src="images/newUI/butn-delete.gif" alt="Remove item" width="30px" height="30px" border="0px"/>
					</div>
    			</div>
    			<div class="inventoryDetails">
   					<table border="0" cellpadding="0" cellspacing="0" width="50%">
   						<tr>
	    						<td>
	    							Total Products
	    						</td>
	    						<td>
	    							<span class="productValue" id="product.total.lines"></span>
	    						</td>
	    					</tr>
	    					<tr>
	    						<td>
	    							Total Qty CSV
	    						</td>
	    						<td>
	    							<span class="productValue" id="product.total.csv"></span>
	    						</td>
	    					</tr>
	    					<tr>
	    						<td>
	    							Total Qty Book
	    						</td>
	    						<td>
	    							<span class="productValue" id="product.total.book"></span>
	    						</td>
	    					</tr>
	    					<tr>
	    						<td>
	    							Total Qty Count
	    						</td>
	    						<td>
	    							<span class="productValue" id="product.total.count"></span>
	    						</td>
	    					</tr>
	    					<tr>
	    						<td>
	    							Discrepancy
	    						</td>
    						<td>
    							<span class="productValue" id="product.discrepancy"></span>
    						</td>
    					</tr>
    				</table>
				</div>
    			<div class="inventoryDetails">
    				
				    	<table border="0" cellpadding="0" cellspacing="0" width="100%">
				    		<tr>
	    						<td>
	    							Reference
	    						</td>
	    						<td>
	    							<span class="productValue" id="inventory.description" onclick="editDescription()" onmouseover="this.style.backgroundColor='yellow'" onmouseout="this.style.backgroundColor='white'"><%=description + ""%></span>
	    						</td>
	    					</tr>
	    					<tr>
	    						<td>
	    							Doc No
	    						</td>
	    						<td>
	    							<span class="productValue" id="inventory.docno"><%=docNo%></span>
	    						</td>
	    					</tr>
	    					<tr>
	    						<td>
	    							Status
	    						</td>
	    						<td>
	    							<span class="productValue" id="inventory.status"><%=docStatus%></span>
	    						</td>
	    					</tr>
	    				</table>
			    	
				</div>
			</div>
	    	</td>
    	</html:form>
    </tr>
    <tr>
    	<td width="75%">
    		<div id="systemButnContainer">
    			<div id="systemButn">
    				<div class="buttonLeft" id="printBtn" onclick="printInventory()">PRINT</div>
					<div class="buttonLeft" id="csvBtn" onclick="diplayCsvOptions()">CSV</div>
					<div class="buttonLeft" id="saveCsvBtn" onclick="generateCsv()">SAVE AS CSV</div>		
					<div class="buttonLeft" id="importBtn" onclick="">ADD CSV</div>		
					<div class="buttonLeft" id="clearCartBtn">CLEAR</div>	
					<div class="buttonLeft" id="saveBtn" onclick="reloadCart()">SAVE</div>
					<div class="buttonLeft" id="newBtn" onclick="newCart();">NEW</div>
					<div class="buttonLeft" id="searchBtn" onclick="displaySortPanel()">SEARCH</div>	
					<div class="buttonLeft" id="reloatCartBtn" onclick="reloadCart()">REFRESH CART</div>
				</div>
    		</div>
    	</td>
    	<td width="25%">
    		<div id="rightBtnContainer">
    			<div class="buttonRight" id="completeBtn">COMPLETE</div>
			</div>
    	</td>
    </tr> 
    <tr>
	  	<td colspan="2">
	  		<div id="controls">
			  	<B>F1</B> Barcode | <B>F2</B> Name | <B>F3</B> Description | <B>F4</B> Qty | <B>F5</B> Save | <B>F6</B> Search | <B>F7</B> Import CSV | <B>F8</B> CSV | <B>F9</B> Save CSV | <B>F10</B> Refresh Cart | 
	  			<B>Ctrl+Up</B> MoveUp | <B>Ctrl+Down</B> MoveDown | <B>Ctrl+Left</B> Decrement | <B>Ctrl-Right</B> Increment | <B>Ctrl-Backspace</B> Remove | <B>Ctrl+M</B> Menu | <B>Ctrl+N</B> New Cart | <B>Ctrl+R</B> Reference |
	  			<B>Ctrl-P</B> Print | <B>Ctrl-C</B> Complete | <B>Ctrl-Delete</B> Clear
		  	</div>
	  	</td>
  	</tr>
</table>
  <!--end -->
<div>
	<applet archive="<%= basePath + "applets/printOrderApplet.jar"%>" code="org.posterita.core.PrintOrderApplet.class" width="0" height="0"></applet>
</div>
<logic:present cookie="preference.printing">
<logic:equal cookie="preference.printing" property="value" value="true">
	<bean:cookie id="cookie" name="preference.printer"/>
	<script>
				
		function printInventory()
		{
			try
			{
				document.applets[0].setPrinterName(<%="'" + cookie.getValue() + "'"%>);
				var url = '<%=basePath + "InventoryCartAction.do?action=printInventory"%>';
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
	function printInventory()
	{
		try
		{
			document.applets[0].setPrinterName(<%="'" + cookie.getValue() + "'"%>);
			var url = '<%=basePath + "InventoryCartAction.do?action=printInventory"%>';
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
	function printInventory()
	{
		alert("Please set your printing preferences!!!");
	}
</script>
</logic:notPresent>
<script>
	function filter2 (phrase, _id){
	var words = phrase.value.toLowerCase().split(" ");
	var table = document.getElementById(_id);
	var ele;
	for (var r = 1; r < table.rows.length; r++){
		ele = table.rows[r].innerHTML.replace(/<[^>]+>/g,"");
	        var displayStyle = 'none';
	        for (var i = 0; i < words.length; i++) {
		    if (ele.toLowerCase().indexOf(words[i])>=0)
			displayStyle = '';
		    else {
			displayStyle = 'none';
			break;
		    }
	        }
		table.rows[r].style.display = displayStyle;
	}
}
</script>
</div>
</body>
</html>