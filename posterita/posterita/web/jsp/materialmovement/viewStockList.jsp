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
 * @author sendy
--%>


<!-- viewStockList.jsp -->
<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.beans.*" %>
<%@ page import="org.posterita.struts.pos.POSProductAction" %>
<%@ page import="org.compiere.process.DocumentEngine" %>
<%@ page import="org.compiere.model.MOrder" %>
<%@ page import="org.posterita.user.WebUserInfo" %>
<%@ page import="org.posterita.struts.pos.POSOrderAction" %>
<%@ page import="java.util.ArrayList" %> 
<%@page import="org.posterita.struts.stock.StockMovementAction"%>

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

<bean:define id="title"><pos:message key="material.movement" textOnly="true"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %> 


<logic:present name="<%=Constants.MATERIAL_MOVEMENT_DETAILS%>">
<logic:empty name="<%=Constants.MATERIAL_MOVEMENT_DETAILS%>">
	<div><pos:message key="list.empty"/></div>	
	<div class="space"></div>
	<div>
		<input type="button" class="newbutton" onclick="get('ViewStock.do')" value="<pos:message key='list.addmore' textOnly='true'/>">
	</div>
</logic:empty>

<logic:notEmpty name="<%=Constants.MATERIAL_MOVEMENT_DETAILS%>">
<div align="right">
	<logic:present name="<%= Constants.STOCK_LIST %>">		
		<span id="productCount">
			<pos:message key="list.has"/><strong>
			<bean:write name="<%= Constants.STOCK_LIST %>" property="noOfProducts"/>
			</strong> <pos:message key="list.items" textOnly="true"/>
		</span>		
	</logic:present>
	<logic:notPresent name="<%= Constants.STOCK_LIST %>">
		<span id="productCount">
			<pos:message key="list.empty" textOnly="true"/>
		</span>
	</logic:notPresent>
	<input type="button" class="newbutton" onclick="get('ViewStock.do')" value="<pos:message key='list.addmore' textOnly='true'/>">	
</div>
<div class="space"></div>
<div class="scrollpane">
<table class="display" border="1" width="900px">
	<tr>
		<th><pos:message key="Name"/></th>
		<th><pos:message key="Barcode"/></th> 
		<th><pos:message key="organisationFrom"/></th>
		<th><pos:message key="organisationTo"/></th>
		<th><pos:message key="Qty"/></th> 
   	    <th>&nbsp;</th>
 	</tr>
	<html:form action="/StockMovementAction">
		<html:hidden property="action" value="<%=StockMovementAction.CREATE_MATERIAL_MOVEMENT%>"/>
	 	<%
			String url = "ViewStockList.do";
			String collection = Constants.MATERIAL_MOVEMENT_DETAILS;
		%>
		<logic:present name="<%=Constants.MATERIAL_MOVEMENT_DETAILS%>">	
		<logic:iterate offset="<%=offset%>" length="<%=length%>" id="stock" name="<%=Constants.MATERIAL_MOVEMENT_DETAILS%>" type="org.posterita.beans.StockMovementBean" indexId="index">
		<%
		String styleClass = "label";
		if ((index.intValue()%2) != 0)
		styleClass = "contentname";
	   	%>
		
		<tr>
			<td class="<%=styleClass%>">
				<html:hidden name="stock" property="productId" value="<%=stock.getProductId().toString()%>" indexed="true"/>
				
				<html:hidden name="stock" property="movementId" value="<%=stock.getMovementId().toString()%>" indexed="true"/>
				<html:hidden name="stock" property="movementLineId" value="<%=stock.getMovementLineId().toString()%>" indexed="true"/>
				<html:hidden name="stock" property="orgFromId" indexed="true"/>
				<html:hidden name="stock" property="orgToId" indexed="true"/> 
				
				<bean:write name="stock" property="productName"/>
			</td>
			<td class="<%=styleClass%>">
			   <bean:write name="stock" property="barCode"/>
			</td>
			<td class="<%=styleClass%>">			   
				<bean:write name="stock" property="orgFromName"/>				
			</td>
			<td class="<%=styleClass%>">
			   <bean:write name="stock" property="orgToName"/>				
			</td>
			<td  align="center" class = "label">
				<html:text name="stock" property="qtyToMove" indexed="true" value="<%=stock.getQtyToMove().toString()%>"></html:text>
			 </td>
			<td  align="right" class="<%=styleClass%>">					    
				<html:link action="<%= "StockMovementAction.do?action=remove&index=" + index.intValue() %>">Remove</html:link>
		 	</td>	 	 
		</tr>		
	</logic:iterate>
	</logic:present> 
		<table width = "100%" height="100px">
			<tr width = "100%">
				<td padding="25px"  align="right">
					<html:submit styleClass="save smallbutton" onclick="createMMovement()" accesskey="c">&nbsp;</html:submit>
				</td>
			</tr>
		</table>
		</html:form>
</table>
</div>
<div class="space"></div>
<include file="/jsp/include/pager.jsp" %>
</logic:notEmpty>
</logic:present>

<script>

var productCount = 0;

<logic:present name="<%= Constants.STOCK_LIST %>">
productCount = <bean:write name="<%= Constants.STOCK_LIST %>" property="noOfProducts"/>;
</logic:present>

function setCartCounter(productCount)
{
	if(productCount > 0)
	{
		$('productCount').innerHTML = '<pos:message key="cart.has" textOnly="true"/> <strong>' + productCount + '</strong> <pos:message key="cart.items" textOnly="true"/>';
		Element.show('cartLinkPanel');
	}
	else
	{
		$('productCount').innerHTML = '<pos:message key="cart.empty" textOnly="true"/>';
		Element.hide('cartLinkPanel');
	}
	
}
</script>
<%@ include file="/jsp/include/posFooter.jsp" %>
