<%--
 *  Product: Posterita Web-Based POS (an Adempiere Plugin)
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
 * @author Praveen Beekoo
--%>


<!-- viewProductCartForUpdate.jsp -->
<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.beans.*" %>
<%@ page import="org.posterita.struts.pos.EditBulkProductFromFileAction" %>
<%@ page import="org.compiere.process.DocumentEngine" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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

<bean:define id="title"><pos:message key="all.products" textOnly="true"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %> 

<logic:present name="<%=Constants.PRODUCT_CART_ITEMS%>">
<logic:empty name="<%=Constants.PRODUCT_CART_ITEMS%>">
	<div><pos:message key="cart.empty" textOnly="true"/></div>	
	<div class="space"></div>
	<div>
		<a href="ViewAllPOSProductsForUpdate.do"><pos:message key="cart.addmore" textOnly="true"/></a>			
	</div>
</logic:empty>
<logic:notEmpty name="<%=Constants.PRODUCT_CART_ITEMS%>">
<div align="right">
<form>
	<logic:present name="<%= Constants.PRODUCT_CART %>">		
		<span id="productCount">
			<pos:message key="cart.has" textOnly="true"/><strong>
			<bean:write name="<%= Constants.PRODUCT_CART %>" property="noOfProducts"/>
			</strong><pos:message key="items" textOnly="true"/>
		</span>		
	</logic:present>
	<logic:notPresent name="<%= Constants.PRODUCT_CART %>">
		<span id="productCount">
			<pos:message key="cart.empty" textOnly="true"/>
		</span>
	</logic:notPresent>
	<a href="ViewAllPOSProductsForUpdate.do"><pos:message key="cart.addmore" textOnly="true"/></a>	
</form>	
</div>
<div class="scrollpane">	
	<table class="display" border="1">
		<tr>
			<th><pos:message key="Name" textOnly="true"/></th>
	   	    <th nowrap="nowrap"><pos:message key="purchase.price" textOnly="true"/></th>  
	   	    <th nowrap="nowrap"><pos:message key="sales.price" textOnly="true"/></th>   
	   	    <th nowrap="nowrap">&nbsp;</th>
	 	</tr>		
		<logic:iterate indexId="count" id="element" name="<%=Constants.PRODUCT_CART_ITEMS%>" type="org.posterita.beans.ProductBean">
		<%
		String styleClass = "label";
		if ((count.intValue()%2) != 0)
		styleClass = "contentname";
	   	%>
		<tr>
			<td class=<%=styleClass%> width="400px">
				<bean:write name="element" property="productName"/>
			</td>
	
			<td  align="right" class=<%=styleClass%>>
				<bean:define id="purchasePriceStandard" name="element" property="purchasePriceStandard"/>
				<fmt:formatNumber value='${purchasePriceStandard}' type="currency" currencySymbol=" "/>
			</td>
			<td  align="right" class=<%=styleClass%>>
				<bean:define id="salesPriceStandard" name="element" property="salesPriceStandard"/>
				<fmt:formatNumber value='${salesPriceStandard}' type="currency" currencySymbol=" "/>
			</td>
			<td class=<%=styleClass%> align="center">			
			   	<html:link action="/ViewAllPOSProductsForUpdateAction.do?action=remove&productId=<%= element.getProductId() %>">
			   		<pos:message key="Remove"/>
			   	</html:link>			   	
			</td>				
		</tr>		
		</logic:iterate>
	</table>	
</div>

<html:form action="/CreateCSVFileAction">
	<html:hidden property="action" value="<%=EditBulkProductFromFileAction.CREATE_CSV_FILE%>"/>
	<html:submit>
		<pos:message key="download.csv" textOnly="true"/>
	</html:submit>		
</html:form>  		 	
</logic:notEmpty>
</logic:present> 
<script>
$FElement('addMore').onclick      = function(e){ window.location = 'ViewAllPOSProductsForUpdate.do'; };
</script>
<%@ include file="/jsp/include/posFooter.jsp" %>
