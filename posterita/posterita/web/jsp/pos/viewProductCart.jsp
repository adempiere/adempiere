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


<!-- viewProductCart.jsp -->
<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.beans.*" %>
<%@ page import="org.posterita.struts.pos.POSProductAction" %>
<%@ page import="org.compiere.process.DocumentEngine" %>
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

<bean:define id="title"><pos:message key="view.product.cart" textOnly="true"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %> 


<logic:present name="<%=Constants.PRODUCT_DETAILS%>">
<logic:empty name="<%=Constants.PRODUCT_DETAILS%>">
	<div><pos:message key="cart.empty"/></div>	
	<div class="space"></div>
	<div>
		<input type="button" class="newbutton" onclick="get('SearchPOSProducts.do')" value="<pos:message key='cart.addmore' textOnly='true'/>">
	</div>
</logic:empty>

<logic:notEmpty name="<%=Constants.PRODUCT_DETAILS%>">
<div align="right">
	<logic:present name="<%= Constants.PRODUCT_CART %>">		
		<span id="productCount">
			<pos:message key="cart.has"/><strong>
			<bean:write name="<%= Constants.PRODUCT_CART %>" property="noOfProducts"/>
			</strong> <pos:message key="cart.items" textOnly="true"/>
		</span>		
	</logic:present>
	<logic:notPresent name="<%= Constants.PRODUCT_CART %>">
		<span id="productCount">
			<pos:message key="cart.empty" textOnly="true"/>
		</span>
	</logic:notPresent>
	<input type="button" class="newbutton" onclick="get('ViewAllPOSProduct.do')" value="<pos:message key='cart.addmore' textOnly='true'/>">	
</div>
<div class="space"></div>
<div class="scrollpane">
<table class="display" border="1" width="900px">
	<tr>
		<th><pos:message key="product.id"/></th>
		<th><pos:message key="Name"/></th>
		<th><pos:message key="Barcode"/></th> 
		<th><pos:message key="Qty"/></th> 
   	    <th>&nbsp;</th>
 	</tr>
 	<%
	String url = "ViewProductCart.do";
	String collection = Constants.PRODUCT_DETAILS;
	%>
	<logic:present name="<%=Constants.PRODUCT_DETAILS%>">	
	<logic:iterate offset="<%=offset%>" length="<%=length%>" indexId="count" id="element" name="<%=Constants.PRODUCT_DETAILS%>" type="org.posterita.beans.ProductDetailsBean">
	<%
	String styleClass = "label";
	if ((count.intValue()%2) != 0)
	styleClass = "contentname";
   	%>
	
	<tr>
		<td class=<%=styleClass%>>
			<bean:write name="element" property="productId"/>
		</td>
		<td class=<%=styleClass%>>
			<bean:write name="element" property="productName"/>
		</td>
		<td class=<%=styleClass%>>
		   <bean:write name="element" property="barCode"/>
		</td>
		<td class=<%=styleClass%>>
			<bean:write name="element" property="quantity"/>
		</td>	
		</td>		
		<td  align="right" class=<%=styleClass%>>					    
			<html:link action="ViewPOSProductCartAction.do?action=remove&productId=<%= element.getProductId() %>">Remove</html:link>
	 	</td>	 	 
	</tr>		
</logic:iterate>
</logic:present> 
</table>
</div>
<div class="space"></div>
<div align="right">
	<input type="button" onclick="get('CreateCSVFileAction.do?action=createCSVFile')" class="newbutton" value="<pos:message key='download.csv' textOnly='true'/>">
	
	<input type="button" onclick="get('ViewPOSProductCartAction.do?action=printBarcode')" class="newbutton" value="<pos:message key='print.barcode' textOnly='true'/>">
	<input type="button" onclick="get('ViewProductForUpdateAction.do?action=initUpdateBulkProductDetails')" class="newbutton" value="<pos:message key='edit.product' textOnly='true'/>">
</div>
<%@ include file="/jsp/include/pager.jsp" %>
</logic:notEmpty>
</logic:present>
<%@ include file="/jsp/include/posFooter.jsp" %>
