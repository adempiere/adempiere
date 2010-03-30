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


<!-- viewProductsSalesInfo.jsp -->
<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.beans.*" %>
<%@ page import="org.posterita.struts.pos.POSProductAction" %>
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
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>



<logic:notPresent name="<%=WebUserInfo.NAME%>" scope="session">
	<jsp:forward page="/LoginHome.do"/>
</logic:notPresent>

<bean:define id="title"><pos:message key="all.products" textOnly="true"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %> 

<table class="content">
	<html:form action="/ViewAllPOSProductsAction">
	<html:hidden property="action" value="<%=POSProductAction.VIEW_ALL_POS_PRODUCTS%>"/>
	<tr>
	    <td>
			<html:text property="productName" styleClass="text" />
	        <html:submit styleClass="search smallbutton">
	        	&nbsp;
	        </html:submit>
	    </td>
	</tr>	
</html:form>	
</table>

<div class="scrollpane">
<table class="display sortable" border="1" id="1111">
	<tr>
		<th class="string"><pos:message key="Name"/></th>
		<th class="string"><pos:message key="Barcode"/></th> 
	    <th class="string"><pos:message key="Description"/></th>
	    <th class="string" nowrap="nowrap"><pos:message key="C_RevenueRecognition_ID"/></th>
   	    <th class="numeric" nowrap="nowrap"><pos:message key="purchase.price"/></th>  
   	    <th class="numeric" nowrap="nowrap"><pos:message key="sales.price"/></th>   
   	    <th class="string" nowrap="nowrap"><pos:message key="IsActive"/></th>  	    
 	</tr>
	<logic:present name="<%=Constants.VIEW_POS_PRODUCTS%>">
	<logic:iterate indexId="count" id="element" name="<%=Constants.VIEW_POS_PRODUCTS%>" type="org.posterita.beans.ProductBean">
	<%
	String styleClass = "label";
	if ((count.intValue()%2) != 0)
	styleClass = "contentname";
   	%>
	
	<tr>
		<td class=<%=styleClass%> width="400px">
			<html:link action="<%= "POSProductSalesAnalysisAction.do?action=viewProductSalesDetails&productId=" + element.getProductId() %>">
				<bean:write name="element" property="productName"/>
			</html:link>
		</td>
		<td class=<%=styleClass%> width="100px">
		   	<bean:write name="element" property="barCode"/>
		</td>
			
		<td class=<%=styleClass%> width="150px">
			<bean:write name="element" property="description"/>
		</td>
		<td class=<%=styleClass%>>
			<bean:write name="element" property="revenueRecognition"/>
		</td>
		<td  align="right" class=<%=styleClass%>>
			<bean:define id="purchasePriceStandard" name="element" property="purchasePriceStandard"/>
			<fmt:formatNumber value='${purchasePriceStandard}' type="currency" currencySymbol=" "/>
		</td>
		<td  align="right" class=<%=styleClass%>>
			<bean:define id="salesPriceStandard" name="element" property="salesPriceStandard"/>
			<fmt:formatNumber value='${salesPriceStandard}' type="currency" currencySymbol=" "/>
		</td>
		<td class=<%=styleClass%>>
			<bean:write name="element" property="isActive"/>
		</td>	 	 
	</tr>		
</logic:iterate>
</logic:present> 
</table>
</div>

<%@ include file="/jsp/include/posFooter.jsp" %>
