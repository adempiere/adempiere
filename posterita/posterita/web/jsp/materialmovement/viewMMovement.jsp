<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>
<%
	String path = request.getContextPath();
	String appName = (String) request.getSession().getAttribute(Constants.APP_NAME);
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	Properties ctx = TmkJSPEnv.getCtx(request);
	String posName = POSTerminalManager.getTerminalName(ctx);
	String orgName = MOrg.get(ctx, Env.getContextAsInt(ctx, UdiConstants.ORG_ID_CTX_PARAM)).getName();
	String roleName = MRole.get(ctx, Env.getContextAsInt(ctx, UdiConstants.AD_ROLE_ID)).getName();
	
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
<%@page import="org.posterita.util.HtmlOrderPrintFormatter"%>
<%@page import="org.compiere.model.MOrder"%>
<%@page import="org.posterita.businesslogic.POSManager"%>
<%@page import="org.posterita.businesslogic.POSTerminalManager"%>
<%@page import="org.compiere.model.MPayment"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="org.compiere.model.I_M_PriceList"%>
<%@page import="org.posterita.order.UDIOrderTypes"%>
<%@page import="org.posterita.TangoColors"%>
<%@page import="org.posterita.businesslogic.administration.PriceListManager"%>
<%@page import="org.posterita.businesslogic.stock.MMovementManager"%>
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
 	<script src="js/keyboard2.js"></script> 	
 	<script src="js/shortcut.js"></script> 	
 	<link rel="shortcut icon" href="images/posterita.jpg" type="image/jpg" /> 
	<link type="text/css" href="css/orderScreen.css" rel="stylesheet">
</head>

<body onload='self.focus'>
<!-- viewPOSOrder.jsp -->
<bean:parameter id="movementId" name="movementId"/>
<%
int priceListId = PriceListManager.getDefaultPriceListId(ctx, false);
MPriceList priceList = new MPriceList(ctx, priceListId, null);
String priceListName = priceList.getName(); 
%>
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
				Stock Transfer
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
      				<div id="total">
	      			
      				</div>
      			</div>
      		</div>
      	</div>
      </td>
    </tr>
    <tr>
	    <td width="75%">
	    	&nbsp;
	    </td>
	    <td width="25%">
	    	<div id="priceListDetailsContainer">
	    			<div id="priceListDetails">
	    				<%=priceListName%>
	    			</div>
	    	</div>
	    </td>
   </tr>
   <tr>
	   <td width="75%">
	  		<div id="contentContainer">
	  			<div id="content">
		   			<div id="shoppingCart">
		   				<%= MMovementManager.getMMovementCartFromInventoryMove(ctx, Integer.parseInt(movementId), null) %>
		   			</div>
	   			</div>
		    </div>
	  	</td>
	  	<td width="25%">
	  		<div id="contentContainer">
	  			<div id="content">
		   			
	   			</div>
		    </div>
	  	</td>
  	</tr>
  	<tr>
	  	<td width="75%">
	  		<div id="leftBtnContainer">
	  			
			</div>	
	  	</td>
	  	<td width="25%">
			<div id="rightBtnContainer">
				<%String newOrderAction = "/StockMovementAction.do?action=createNewStockTransfer"; %>
				<div class="buttonRight" id="newOrderBtn" onclick="get('<html:rewrite action='<%=newOrderAction%>'/>')">NEW ORDER</div>
			</div>
	  	</td>	
  	</tr>
  	<tr>
	  	<td colspan="2">
			<div id="centerShortcuts">
		  	<B>Ctrl+Space</B> New Order | <B>Ctrl-M</B> Menu</div> </div> 
		</td>
  	</tr>
  </table>
	<%@ include file="/jsp/include/printOrderApplet2.jsp" %>	
	</body>
</html>
