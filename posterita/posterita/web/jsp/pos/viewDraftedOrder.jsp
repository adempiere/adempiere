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
	//String appName = (String) request.getSession().getAttribute(Constants.APP_NAME);
	Properties ctx = TmkJSPEnv.getCtx(request);
	String posName = POSTerminalManager.getTerminalName(ctx);
	String orgName = MOrg.get(ctx, Env.getContextAsInt(ctx, UdiConstants.ORG_ID_CTX_PARAM)).getName();
	String roleName = MRole.get(ctx, Env.getContextAsInt(ctx, UdiConstants.AD_ROLE_ID)).getName();
	int m_pricelist_id = POSTerminalManager.getSOPriceListId(ctx);
	MPriceList priceList = MPriceList.get(ctx, m_pricelist_id, null);
	String priceListName = priceList.getName();
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
<%@page import="org.posterita.util.HtmlOrderPrintFormatter"%>
<%@page import="org.posterita.businesslogic.POSTerminalManager"%>
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
 	<script src="js/keyboard2.js"></script> 	
 	<script src="js/shortcut.js"></script> 	
 	<link rel="shortcut icon" href="images/posterita.jpg" type="image/jpg" /> 
	<link type="text/css" href="css/orderScreen.css" rel="stylesheet">
</head>

<body>
<bean:parameter id="orderId" name="orderId" value="1000037"/>
<bean:parameter id="orderType" name="orderType" value="POS Order"/>
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
		</ul>
    </div>
    <br class="clear">  	  
  	<div class="spacer"></div>
  	<div id="shoppingCart">
     	<%= ShoppingcartManager.getShoppingCartFromOrder(ctx, Integer.parseInt(orderId), null) %>
     </div>
  
  	<div class="spacer"></div>
   
	<div floatDown>	
		<div class="button floatLeft" id="menuBtn" onclick="get('<html:rewrite action='/GetMenuItemsAction.do?action=getMenuItems'/>')">BACK TO MENU</div>
		<%if(orderType.equalsIgnoreCase("POS Order")){%>					
		<div class="button floatRight" id="newOrderBtn" onclick="get('<html:rewrite action='/CreatePOSOrderScreen.do'/>')">NEW ORDER</div>
		<% }else{ %>
		<div class="button floatRight" id="newOrderBtn" onclick="get('<html:rewrite action='/CreateGoodsReceiveNote.do'/>')">NEW ORDER</div>
		<%}%>
		<div class="button floatRight" id="reprintBtn">REPRINT</div>
		<br class="clear"/>	 
		<br class="clear"/>
	  	<div id="shortcuts">
	  	<B>Ctrl+P</B> Reprint | <B>Ctrl+Enter</B> New Order | <B>Ctrl-Esc</B> Menu</div> </div> 			
	</div>	
	 
	<!-- The right column -->  
    <td width="25%" valign="top" id="rightcolumn">
	<div id="total" class="border">$0.00</div>
	<div class="spacer"></div>
	
    <div id="terminal">
		<%=priceListName%>
	</div>
		
	
	<div id="reciept">
		<%
			String args = "showFooter=true;lineLength=40;priceWithVat=true;showDiscount=true;showLogo=false;showBarcode=false;";
		 
			HtmlOrderPrintFormatter formatter = new HtmlOrderPrintFormatter(args);
			String data = formatter.format(ctx, Integer.parseInt(orderId), null);
			out.print("<pre>");
			out.print(data);
			out.print("</pre>");
		%>
	</div>	
	</div>
	</td>
  </tr>
</table>

<div>
	<applet archive="<%= basePath + "applets/printOrderApplet.jar"%>" code="org.posterita.core.PrintOrderApplet.class" width="0" height="0"></applet>
</div>
<script>
	var orderId = <%=orderId%>;
	
	function printOrder(orderId)
	{
		var url = '<%=basePath + "ReprintOrderAction.do?action=getPrintOrderData&openDrawer=true&orderId="%>';
		document.applets[0].printURL(url + orderId);
	}
	
	$('reprintBtn').onclick = function(){
		printOrder(orderId);
	};
	
	printOrder(orderId);
	
	function initScreen()
	{
		shortcut.add("Ctrl+Enter", function(){simulateOnClick($('newOrderBtn'))});
		shortcut.add("Ctrl+p", function(){simulateOnClick($('reprintBtn'))});
		shortcut.add("Ctrl+M", function(){simulateOnClick($('menuBtn'))});
	}
	
	Event.observe(window,'load',initScreen,false);
</script>
</body>
</html>
