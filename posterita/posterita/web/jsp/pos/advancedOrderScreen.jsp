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
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="org.posterita.Constants"%>
<%@page import="java.util.Properties"%>
<%@page import="org.posterita.core.TmkJSPEnv"%>
<%@page import="org.compiere.util.Env"%>
<%@page import="org.posterita.lib.UdiConstants"%>
<%@page import="org.compiere.model.MPriceList"%>
<%@page import="org.posterita.businesslogic.administration.RoleManager"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="org.compiere.model.MOrg"%>
<%@page import="org.compiere.model.MRole"%>
<%@page import="org.posterita.businesslogic.POSTerminalManager"%>
<%@page import="org.posterita.TangoColors"%>
<%@page import="org.compiere.model.MCurrency"%>
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
 	<script src="js/shoppingCart.js"></script>
 	<script src="js/shortcut.js"></script>
 	<script src="js/advancedOrderScreen.js"></script>
 	<link rel="shortcut icon" href="images/posterita.jpg" type="image/jpg" /> 
	<link type="text/css" href="css/orderScreen.css" rel="stylesheet">
</head>

<body> 
<bean:parameter id="priceListId" name="priceListId"/>
<% 
	MPriceList priceList = MPriceList.get(ctx, Integer.valueOf(priceListId).intValue(), null);
	String priceListName = priceList.getName();
	int currencyId = priceList.getC_Currency_ID();
	MCurrency currency =  MCurrency.get(ctx, currencyId);
	String curSymbole = currency.getCurSymbol();
%>
<bean:parameter id="discountAllowed" name="discountAllowed"/>
<bean:parameter id="isAllowedOverridePriceLimit" name="isAllowedOverridePriceLimit"/>
<bean:parameter id="orderType" name="orderType"/>

<html:form action="/CheckoutAction" styleId="checkoutForm">
  	<html:hidden property="action" value="setDiscount"/>
	<html:hidden property="orderType"  value="<%=orderType%>" styleId="orderType"/>
	<html:hidden property="tenderType" value="cash"/>
	<html:hidden property="amountTendered" value="0"/>	
	<html:hidden property="amountRefunded" value="0"/>
	<html:hidden property="cashAmt" value="0"/>
	<html:hidden property="cardAmt" value="0"/>
	<html:hidden property="chequeAmt" value="0"/>
	<html:hidden property="cardNo" value=""/>
	<html:hidden property="chequeNo" value=""/>
	<html:hidden property="discountOnTotal" styleId="discountOnTotal" value="false"/>
	<html:hidden property="discountOnTotalPercent" styleId="discountOnTotalPercent" value="false"/>
	<html:hidden property="discountLimit" styleId="discountLimit" />
	<html:hidden property="priceListId" value="<%=priceListId%>"/>  
	<html:hidden property="curSymbole" styleId="curSymbole" value="<%=curSymbole%>"/>
	
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr>
	    <td width="75%">
	    <div id="posHeader">
	    	<div id="menuBtn" class="backButn" onclick="get('/posterita/GetMenuItemsAction.do?action=getMenuItems')">
	    		<img src="images/newUI/back-menu.gif" alt="Back to main system memu" width="28px" height="41px" border="0px"/>
			</div>

			<div id="logo">
				<img src="images/newUI/logo.gif" alt="Powered by Posterita POS" width="133px" height="41px" border="0px"/>
			</div>
			<div id="mainTitle">
				POS Order
			</div>
			<div id="acDetailsContainer">
				<div id="acDetailsR">
					<div id="org">

						Organisation :
						<span class="strong">Posterita</span>
					</div>
					<div id="terminal">
						Terminal :
						<span class="strong">Test teminal</span>
					</div>
				</div>
				<div id="acDetailsL">

					<div id="user">
						User :
						<span class="strong">admin</span>
					</div>
					<div id="role">
						Role :
						<span class="strong">admin</span>
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
	      					<%=curSymbole%>
	      					<logic:present name="<%= Constants.SHOPPING_CART_TOTAL %>">
	      					<bean:write name="<%= Constants.SHOPPING_CART_TOTAL %>" format="0.00"/>
	      					</logic:present>
	      				</div>
	      			</div>
	      		</div>
	      	</div>
	      </td>
	    </tr>
	    <tr>
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
	   		 <div id="shoppingCart">
		      <bean:define id="currSymbole" value="<%=curSymbole%>"/>
				<table width="100%" id="cart" cellspacing="0" cellpadding="0" border="0">
					<tr class="itemTitleList">
					  <th><pos:message key="Description"/></th>
					  <th><pos:message key="Qty"/></th>
					  <th><pos:message key="Uom"/></th>
					  <th><pos:message key="Incl Price"/></th>
					  <th><pos:message key="Price"/></th>
					  <th><pos:message key="vat"/></th>		  
					  <th><pos:message key="total"/></th>
					  <th width="130px"><pos:message key="Discount"/></th>
					  <th width="150px"><pos:message key="total"/></th>
					</tr>
				<tbody style="width:400px;" id="cartBody">
				<logic:present name="<%= Constants.SHOPPING_ORDER_CART_ITEMS %>">
				<logic:iterate indexId="count" id="element" name="<%= Constants.SHOPPING_ORDER_CART_ITEMS %>" type="org.posterita.beans.ItemBean">
				<%
				String styleClass = "label";
				String trStyleClass = "";
				
				if ((count.intValue()%2) == 0)
				{
					//use same color for sales and purchase for time being
					if(orderType.equals("POS Order"))
					{
						trStyleClass = "evenRow";
					}
					else if(orderType.equals("POS Goods Receive Note"))
					{
						trStyleClass = "evenRow";
					}
				}
				else
				{			
					trStyleClass = "oddRow";
				}
				%>	
				<tr class="<%=trStyleClass%> productId="<%=element.getProductId()%>" qty="<%=element.getQty()%>" id="<%= "row" + (count.intValue()+1)%>">
				  <td>
				  	<bean:write name="element" property="description"/>
				  </td>
				  <td>
					<bean:write name="element" property="qty"/>
					<bean:define id="qtyTotal" name="element" property="qtyTotal"/>
					<input type="hidden" value="<bean:write name="element" property="qty"/>" name="qtyPerLine"/>
				  </td>
				  <td>
					<bean:write name="element" property="uom"/>
				  </td>
				   <td>
				  <input class="inputRows" type="text" value="<bean:write name="element" property="inclPrice" format="0.00"/>" name="inclPrice" size="10"/>
				  </td>
				  <td>
				  <bean:write name="element" property="unitPrice" format="0.00" />
				  <input type="hidden" value="<bean:write name="element" property="taxRate" format="0.00" />" name="taxRate"/>
				  </td>
				  <td>
					<bean:write name="element" property="taxAmt" format="0.00"/>
					<bean:define id="taxTotal" name="element" property="taxTotal"/>
				  </td>
				  <td>
					<bean:write name="element" property="price" format="0.00" />
					<bean:define id="grandTotal" name="element" property="grandTotal"/>
					<bean:define id="priceLimitTotal" name="element" property="priceLimitTotal"/>
				  </td>
				   <td>
				   	<input class="inputRows" type="text" value="<bean:write name="element" property="discountPercent" format="0.00" />" name="discount"/>
				  </td>
				  <td>
					<input class="inputRows" type="text" value="<bean:write name="element" property="price" format="0.00" />" name="discountedPrice"/>
					<input type="hidden" value="<bean:write name="element" property="priceLimit" format="0.00" />" name="limitPrice"/>
					<input type="hidden" value="<bean:write name="element" property="isDiscountOnInclUnitPrice"/>" name="isDiscOnInclUnitPrice"/>
					<input type="hidden" value="<bean:write name="element" property="discountedLinePrice" format="0.00"/>" name="discInclUnitPrice"/>
					<input type="hidden" value="<bean:write name="element" property="isDiscountOnPercentage"/>" name="isDiscOnPerc"/>
					<input type="hidden" value="<bean:write name="element" property="isDiscountOnTotal"/>" name="isDiscOnTotal"/>
					<input type="hidden" value="<bean:write name="element" property="listPrice"/>" name="listPrice"/>
				  </td>	
				</tr>
				</logic:iterate>
				</logic:present>
				</tbody>
				<bean:size id="size" name="<%= Constants.SHOPPING_ORDER_CART_ITEMS %>"/>
				<tr class="itemsTotal" id="<%= "row" + (size.intValue() + 1) %>">
					<td>&nbsp;</td>
					<td><fmt:formatNumber value='${qtyTotal}'/></td>
					<td colspan="4">&nbsp;</td>
					<td>
						<div id="cartTotal">
						<fmt:formatNumber value='${grandTotal}'type="currency" currencySymbol='${currSymbole}'/>
						</div>
					</td>
					<td>
					<input type="hidden" />
					<input type="hidden" value="<fmt:formatNumber value='${grandTotal}' pattern="#.##"/>" name="inclPrice"/>
					 <input type="hidden" />
						<logic:present name="<%= Constants.SHOPPING_CART_DISCOUNT_PERCENTAGE %>">
						<input class="inputRows" type="text" value="<bean:write name="<%= Constants.SHOPPING_CART_DISCOUNT_PERCENTAGE %>" format="0.00"/>"id="totalDiscount" name="totalDiscount" class="numeric"/>
						</logic:present>
						<logic:notPresent name="<%= Constants.SHOPPING_CART_DISCOUNT_PERCENTAGE %>">
						<input class="inputRows" type="text" value="0.00" id="totalDiscount" name="totalDiscount" class="numeric"/>
						</logic:notPresent>
					</td>
					<td>
						<logic:present name="<%= Constants.SHOPPING_CART_DISCOUNTED_TOTAL %>">
						<input class="inputRows" type="text" value="<bean:write name="<%= Constants.SHOPPING_CART_DISCOUNTED_TOTAL %>" format="0.00"/>" id="grandTotal" name="discountedTotal"/>
						</logic:present>
						<logic:notPresent name="<%= Constants.SHOPPING_CART_DISCOUNTED_TOTAL %>">
						<input class="inputRows" type="text" value="<bean:write name="grandTotal" format="0.00"/>" id="grandTotal" name="discountedTotal"/>
						</logic:notPresent>		
						<input type="hidden"/>	
						<input type="hidden"/>
						<input type="hidden"/>
						<input type="hidden"/>
						<input type="hidden"/>		
					</td>
				</tr>
				</table>
		      </div>
		     </div>
	   	</td>
	   	<td width="25%">
	   		<div id="discountDetailsContainer">
	   		<div id="discountDetails">
	   		<table class="alignDetails" cellspacing="0px" cellpadding="0px">
	   			<tr>
	   				<td>
	   					Override Price Limit:
	   				</td>
	   				<td>
	   					<span id="overrideLimit" class="discountDetails"><%=isAllowedOverridePriceLimit%></span>
	   				</td>
	   			</tr>
	   			<tr>
	   				<td>
	   					Discount Limit: 
	   				</td>
	   				<td>
	   					<span id="discountLimit" class="discountDetails"><%=discountAllowed%></span>
	   				</td>
	   			</tr>
	   			<tr>
	   				<td>
	   					Price Limit:
	   				</td>
	   				<td>
	   					<span id="priceLimit" class="discountDetails"></span>
	   				</td>
	   			</tr>
	   		</table>
	   		<span id="errormsg"></span>
			</div>
			</div>
	   	</td>
	   
	   </tr>
	   	<tr>
	   		<td width="75%">
	   			 <div id="leftBtnContainer">
					  <% String loadScreen = "CreatePOSOrderScreen.do?orderType="+orderType;
					  %>
					  	<input type="button" class="buttonLeft" id="cancelBtn" onclick="get('<html:rewrite action="<%=loadScreen%>"/>')" value="Cancel"/>		
					  	<input type="button" class="buttonLeft" id="overrideDiscountLimitBtn" value="Change PIN"/>
					  	 	
	  			</div>
	   		</td>
	   		<td width="25%">
	   			<div id="rightBtnContainer">
	   			<input type="button" class="buttonRight" id="applyBtn" value="APPLY" />  
	   			</div>
	   		</td>
	   	</tr>
		<tr>
		<td colspan="2">
			<div id="centerShortcuts">
			<B>Ctrl+Enter</B> APPLY | <B>Ctrl+Space</B> OVERRIDE | <B>Ctrl+Backspace</B> CANCEL
			</div>
		</td>
		</tr>		  
					 
		</table>
	</html:form>			  	  
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
</body>
<script>
	var discount = <%=discountAllowed%>;
	function getDiscountAllowed()
	{
		return discount;
	}
	
	var overrideLimit = <%=isAllowedOverridePriceLimit%>;
</script>
</html>