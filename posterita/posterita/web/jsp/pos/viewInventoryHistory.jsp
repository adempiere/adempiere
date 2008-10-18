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


<!-- viewInventoryHistory.jsp -->
<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.compiere.model.MOrg" %>
<%@ page import="org.compiere.process.DocumentEngine" %>
<%@ page import="org.posterita.struts.pos.InventoryAction" %>
<%@ page import="org.compiere.process.DocAction" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<%@page import="org.posterita.core.Configuration"%>
<%@page import="org.compiere.util.Env"%>
<%@page import="org.compiere.model.MPriceListVersion"%>
<%@page import="org.posterita.lib.UdiConstants"%>
<%@page import="org.posterita.core.TmkJSPEnv"%>
<%@page import="java.util.Properties"%>
<%@page import="org.posterita.businesslogic.administration.PriceListManager"%>
<bean:define id="title"><pos:message key="M_Inventory_ID" textOnly="true"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %> 
<%
	Integer priceListId = PriceListManager.getDefaultPriceListId(ctx, false);
	Configuration configuration = Configuration.getConfiguration(request);
	String searchProductBy = configuration.getSearchProductBy();
%>
<style>
<!--
div.autocomplete {
	      position:absolute;
	      height:300px; 
	      display:none;     
	      background-color:white;
	      border:1px solid #888;
	      margin:0px;
	      padding:0px;
	      overflow: scroll;
	      font-size: 12px;
	  	  font-family:arial,sans-serif;
	}
	
	div.autocomplete ul {
	  list-style-type:none;
	  margin:0px;
	  padding:0px;
	}
	div.autocomplete ul li.selected { background-color: #ffb;}
	div.autocomplete ul li {
	  list-style-type:none;
	  display:block;
	  margin:0;
	  padding:2px;  
	  cursor:pointer;
	  letter-spacing: 1px;
	  font-size: 12px;
	  font-family:arial,sans-serif;
	}
	div.notfound {
	background-color: #ffb;	
}

div.selected {
	float:left;
	border:solid 1px #333333;
	margin-right: 4px;
	margin-top: 4px;
	margin-bottom: 4px;
	padding:3px;
	font-size:10px;
	font-weight: bold;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	color:#000000;
	background-color:#ffb;
	cursor:pointer;
}

ul#context{
	height:25px;
	display: block;
	list-style: none;
	margin-left: 90px;
	margin-top: 0px;
}

ul#context li{
	margin-right: 10px;
	float: left;
	line-height: 25px;
}
.displayNone {
	display: none;
}
-->
</style>

<script type="text/javascript">
<!--
	<%@ include file="/js/inventoryHistory.js" %>
//-->
</script>

<table border="0" width="100%">
		<tr>	
			<td align="left">
				<input type="hidden" id="priceListId" value="<%=priceListId%>"/>
				<input type="hidden" name="searchProductBy" value="<%=searchProductBy%>" id="searchProductBy"/>
			</td>
			<td>
				<label>BARCODE:</label><input type="text" id="barcode" />
				<input type="hidden" name="productId" id="productId"/>
			</td>
			<td>
				<label>NAME:</label><input type="text" id="productQuery"/>
				<div id="productSearchResult" class="autocomplete"></div>
			</td>
			<td>
				<label>DESC:</label><input type="text" id="description"/>
				<div id="productSearchByDescriptionResult" class="autocomplete"></div>
			</td>
			<td align="right">
				<html:form action="/ViewInventoryHistoryAction" method="get">
				<html:hidden property="action" value="getInventoryHistory"/>
				<table>
			
				<tr>
					<td align="right">
						<label class="greencolor"><pos:message key="filter.by"/>:</label>
					</td>
					
					<td width="3%">
						<label><pos:message key="DocStatus"/></label>
					</td>
					
					<td width="3%">
						<label><pos:message key="month"/></label>
					</td>
					
					<td width="3%">
						<label><pos:message key="year"/></label>						
					</td>
					
					<logic:present name="<%=Constants.PAYMENT_RULES%>">
					<td width="3%">
						<label><pos:message key="PaymentRule"/></label>
					</td>	
					</logic:present>
														
				</tr>
				
				<tr>
					<td>
						&nbsp;
					</td>
					
					<td align="right">
						<html:select property="docStatus" onchange="submit()">
							<html:option value=""><pos:message key="all"/></html:option>	
								<html:option value="<%= DocAction.STATUS_Drafted%>"><pos:message key="drafted"/></html:option>
								<html:option value="<%= DocAction.STATUS_InProgress%>"><pos:message key="inprogress"/></html:option>
								<html:option value="<%= DocAction.STATUS_Completed%>"><pos:message key="completed"/></html:option>
								<html:option value="<%= DocAction.STATUS_Closed%>"><pos:message key="closed"/></html:option>
								<html:option value="<%= DocAction.STATUS_Voided%>"><pos:message key="Void"/></html:option>
								<html:option value="<%= DocAction.STATUS_Invalid%>"><pos:message key="invalid"/></html:option>
						</html:select>
					</td>
					
					<%@ include file="/jsp/include/historyMonthYearFilter.jsp"%>		
				
					<logic:present name="<%=Constants.PAYMENT_RULES%>">						
					<td align="right">
						<html:select property="paymentRule" onchange="submit()">
							<html:option value=""><pos:message key="all"/></html:option>	
							<logic:present name="<%=Constants.PAYMENT_RULES%>">
							<%@include file="/jsp/include/paymentRuleFilter.jsp"%>							
							</logic:present>
				   		</html:select>																									
					</td>
					</logic:present>
				
				</tr>
				</table>
			</html:form>
			</td>
		</tr>
</table>
<html:form action="/MergeInventoryAction" method="get">
<html:hidden property="action" value="mergeInventoryToCreateOne"/>
	<logic:present name="<%=Constants.INVENTORY_HISTORY_LIST%>">
	<logic:notEmpty name="<%=Constants.INVENTORY_HISTORY_LIST%>">
	<display:table id="row" name="inventoryHistoryList" class="displaytag" defaultsort="1" export="true" excludedParams="delete" defaultorder="descending" pagesize="<%=pageSize.intValue() %>"
			requestURI="ViewInventoryHistoryAction.do" sort="page" decorator="org.posterita.decorator.InventoryHistoryWrapper">
	<display:column property="inventoryNo" titleKey="DocumentNo" sortable="true"/>
	<display:column property="docStatus" titleKey="status" sortable="true"/>
	<display:column property="movementDate" titleKey="movementDate" sortable="true"/>		
	<display:column property="description" titleKey="description" sortable="true"/>
	<display:column property="delete" titleKey="delete" sortable="true"/>
	<display:column property="select" titleKey="select" sortable="true"/>	
	</display:table>
	<p>
	<html:submit style="float: right;">		
		Merge
	</html:submit>
	</p>
	</logic:notEmpty>
	</logic:present>
</html:form>
<p>&nbsp;</p>

<%@ include file="/jsp/include/posFooter.jsp" %>    
<script>
</script>	
												