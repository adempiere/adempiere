<%--
 *  Product: Posterita Web-Based POS and Adempiere Plugin
 *  Copyright (C) 2008  Posterita Ltd
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
 *  @author sendy
--%>

<!-- listPriceLists.jsp -->
<!-- HELLO -->
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>	
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>
<%@page import="org.posterita.struts.pos.PriceListAction"%>
<%@page import="org.posterita.Constants"%>
<%@page import="org.posterita.struts.pos.POSProductAction"%>
<bean:define id="title">Price Lists</bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %>
<!-- page contents -->

<table width = "100%">
	<tr>
		<td>
			<html:form action="/PriceListAction">
				<html:text property="name"></html:text>
				<logic:notPresent name = "<%= Constants.IS_FROM_DELETE_PRODUCT_PRICE %>">
					<html:hidden property="action" value="<%=PriceListAction.LIST_PRICE_LISTS%>"/>
					<html:submit></html:submit>
				</logic:notPresent>
				<logic:present name = "<%= Constants.IS_FROM_DELETE_PRODUCT_PRICE %>">
					<html:button property = "button" value = "Submit" onclick="get('PriceListAction.do?action=listPriceLists&isFromDeletePriceOnPriceList=true')"/>
				</logic:present>
				
			</html:form>
		</td>
		<logic:notPresent name = "<%= Constants.IS_FROM_DELETE_PRODUCT_PRICE %>" >
			<td align = "right"><html:link href="<%="PriceListAction.do?action=viewPriceList&priceListId=0"%>">
					<img src="images/tango/document-new.png" border="0">
				</html:link>
			</td>
		</logic:notPresent>
	</tr>
</table>

<div class="scrollpane">
	<table class="display sortable" border="1" width="900px" >
		<tr>
			<th class="string"><span><pos:message key="name"/></span></th>
		    <th class="string" nowrap="nowrap"><span><pos:message key="smenu.organisation"/></span></th>
	   	    <th class="string" nowrap="nowrap"><span><pos:message key="isActive"/></span></th>  
   	       	<th class="string" nowrap="nowrap"><span><pos:message key="isDefault"/></span></th>
   	       	<th class="string" nowrap="nowrap"><span><pos:message key="isPresentForProduct"/></span></th>       
	   	    <th class="string" nowrap="nowrap"><span><pos:message key="isSOPriceList"/></span></th> 
	   	    <logic:present name = "<%= Constants.IS_FROM_DELETE_PRODUCT_PRICE %>">  
	   	    	 <th nowrap="nowrap"><span><pos:message key="Delete Product Prices on PriceList"/></span></th>
	   	    </logic:present>
	   	    
	   	    <logic:notPresent name = "<%= Constants.IS_FROM_DELETE_PRODUCT_PRICE %>">
	   	    	<th nowrap="nowrap"><span><pos:message key="update.details"/></span></th>
	   	    </logic:notPresent>
	   	</tr> 	
	
		<logic:present name="<%=Constants.LIST_PRICE_LISTS %>">

		<logic:iterate id="priceList" name="<%=Constants.LIST_PRICE_LISTS %>" type="org.posterita.beans.PriceListBean">
		<tr>
			<td class = "label">
				<bean:write name="priceList" property="name"/>
			</td>
			<td class = "label">
				<bean:write name="priceList" property="orgName"/>
			</td>
			<td class = "label">
				<bean:write name="priceList" property="isActive"/>
			</td>
			<td class = "label">
				<bean:write name="priceList" property="isDefault"/>
			</td>
			<td class = "label">
				<bean:write name="priceList" property="isPresentForProduct"/>
			</td>
			<td class = "label">
				<bean:write name="priceList" property="isSOPriceList"/>
			</td>
			<td  align="center" class = "label">
				<logic:present name = "<%= Constants.IS_FROM_DELETE_PRODUCT_PRICE %>">
					<html:link href="<%="POSProductAction2.do?action=viewAllPOSProducts&isFromDeletePriceOnPriceList=true&priceListId=" + priceList.getPriceListId()%>">
						<img src="images/tango/edit-delete.png"  border="0"> 
					</html:link>
				</logic:present>
				
				<logic:notPresent name = "<%= Constants.IS_FROM_DELETE_PRODUCT_PRICE %>">
					<html:link href="<%="PriceListAction.do?action=viewPriceList&priceListId=" + priceList.getPriceListId().toString()%>">
						<img src="images/tango/accessories-text-editor.png"  border="0"> 
					</html:link>
					
					<logic:equal name="priceList" property="isDefault" value="true">
						<img src="images/pos/success.gif" border="0" title="Default Price List"> 
					</logic:equal>
					
					<logic:equal name="priceList" property="isDefault" value="false">
						<html:link href="<%="PriceListAction.do?action=setDefaultPriceList&priceListId=" + priceList.getPriceListId().toString()%>">
							<img src="images/tango/edit-redo.png"  border="0" title="Set Default Price List">
						</html:link> 
					</logic:equal>
					
				</logic:notPresent>
		 	</td>
		</tr>
		</logic:iterate>

		</logic:present>
	</table>
</div>
<SCRIPT>


function get(URL)
{
	window.location = URL;
}

</SCRIPT>
<%@ include file="/jsp/include/posFooter.jsp" %>
