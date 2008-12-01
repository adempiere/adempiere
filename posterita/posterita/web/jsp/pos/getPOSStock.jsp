<%--
 *  Product: Posterita Web-Based POS and Adempiere Plugin
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
 * @author Alok
--%>


<!-- getPOSStock.jsp -->
<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.beans.*" %>
<%@ page import="org.posterita.struts.pos.POSStockAction" %>
<%@ page import="org.compiere.process.DocumentEngine" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.posterita.core.UDIPair" %>
<%@ page import="java.math.RoundingMode" %> 
<%@ page import= "java.math.BigDecimal"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<logic:notPresent name="<%=WebUserInfo.NAME%>" scope="session">
	<jsp:forward page="/LoginHome.do"/>
</logic:notPresent>

<bean:define id="title"><pos:message textOnly="true" key="stock.inquiry"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %> 

<html:form action="/GetStockAction" focus="productName">
<html:hidden property="action" value="<%=POSStockAction.GET_STOCK_FROM_SEARCH%>"/>
<table class="main">
	<tr>
		<td>
			<label><pos:message key="Name"/> :</label>
			<html:text property="productName" styleClass="text"/>
			
			
			<label><pos:message key="barcode"/> :</label>   	 		
			<html:text property="barCode" styleClass="text" />
			
			
	        <html:submit styleClass="tangoSearch tangoButton">
	        	&nbsp;
	        </html:submit>
  	 
  	 		&nbsp;&nbsp;
  	  
  	 		<label><pos:message key="Qty"/> :</label>		
			<html:select property="qtyFilter" onchange="setActionMethod(this.form,'getStockFromSearch');" styleClass="text">
			    <html:option value=""><pos:message key="all"/></html:option>
				<html:option value="> 0"> > 0</html:option>
				<html:option value="< 0"> < 0</html:option>
				<html:option value="= 0"> = 0</html:option>
				<html:option value="> 25"> > 25</html:option>
				<html:option value="> 50"> > 50</html:option>
				<html:option value="> 100"> > 100</html:option>
			</html:select>
	   	</td>		
    </tr>
 	
	<logic:present name="<%=Constants.PRODUCT_ATTRIBUTE_LIST%>">
		<tr>
			<td>
				<table border="0" cellpadding="5" cellspacing="0">		
					<logic:iterate indexId="count" id="element" name="<%=Constants.PRODUCT_ATTRIBUTE_LIST%>" type="org.posterita.beans.AttributeBean">		
						<tr>
							<td>
								 <label><bean:write name="element" property="attributeName"/> :</label>
							</td>
							<td>
								<html:select property="attributeValueIds" onchange="setActionMethod(this.form,'getProducts');" styleClass="text">
								     <html:option value=""><pos:message key="all"/></html:option> 
												<logic:iterate id="element1" collection="<%=element.getAttributeValueList()%>" type="org.posterita.beans.AttributeBean">	
													<html:option  value="<%=element1.getAttributeValueId().toString()%>">
														<bean:write name="element1" property="attributeValue"/>
												    </html:option>
									</logic:iterate>
								</html:select>	
							</td>
						</tr>	
					</logic:iterate>
				</table>
			</td>
		</tr>
	</logic:present>
	<logic:present name="<%=Constants.PRODUCTS%>">
	<% 
		String url = "GetPOSStock.do";
		String collection = Constants.PRODUCTS;
	%>
	<tr>
		<td>
			<%
			 	org.displaytag.decorator.TotalTableDecorator totals = new org.displaytag.decorator.TotalTableDecorator();
		     	totals.setTotalLabel("Total");
		     	pageContext.setAttribute("totals", totals);
			%>
			<logic:notEmpty name="<%=Constants.PRODUCTS%>">
			<display:table id="posStock" name="sessionScope.products" class="displaytag" defaultsort="1" sort="list" 
				export="true" defaultorder="ascending" pagesize="<%=pageSize.intValue() %>" 
				requestURI="GetStockAction.do?displayTagTableId=posStock" decorator="totals">
				<display:column titleKey="Product" property="productName" sortable="true" sortName="productName"/>
				<display:column titleKey="description" property="description" sortable="true"/>
				<display:column titleKey="barcode" property="barCode" sortable="true"/>
				<display:column titleKey="uom" property="uom" sortable="true"/>
				
				<logic:lessEqual name="posStock" property="quantity" value="0.0">
					<display:column titleKey="Qty" property="quantity" class="negativenumeric" sortable="true"/>
				</logic:lessEqual>
				<logic:greaterThan name="posStock" property="quantity" value="0.0">
					<display:column titleKey="Qty" property="quantity" class="numeric" total="true" sortable="true"/>
				</logic:greaterThan>
				<display:column titleKey="unitPrice" property="unitPrice" format="{0,number,#,##0.00;(#,##0.00)}" class="amount" sortable="true"/>
				<display:column titleKey="price.x" property="price" format="{0,number,#,##0.00;(#,##0.00)}" class="amount" total="true" sortable="true"/>
			</display:table>
			</logic:notEmpty>
		</td>
	</tr>
</logic:present>
</table>	
</html:form>

<tr>
	<td>&nbsp;</td>
</tr>
<script>
function alok(select)
{
	var attr = document.getElementsByName('attributeValueIds');
	var query = "";
	
	for(i=0;i<attr.length;i++)
	{
		if(attr[i].selectedIndex != '-1')
		{
			var str = decodeURI(attr[i].options[attr[i].selectedIndex].innerHTML);
			
			if(str!="All")
			query = query + str + "+";
		}
		
	}  
	
	document.getElementsByName('productName')[0].value = query;	
	
	select.form.submit();
}
</script>	   	
<%@ include file="/jsp/include/posFooter.jsp" %>
