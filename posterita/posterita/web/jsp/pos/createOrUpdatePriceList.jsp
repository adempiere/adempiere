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
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>	
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>
<%@page import="org.posterita.Constants"%>
<%@page import="org.posterita.struts.pos.PriceListAction"%>
<bean:define id="title">Price List</bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %>
<!-- page contents -->
<!-- createOrUpdatePriceList.jsp -->

<html:form action="PriceListAction">
<table class="main">		
	<tr>
		<td>
				<html:hidden property="action" value="<%=PriceListAction.CREATE_OR_UPDATE_PRICE_LIST%>"/>
				<bean:define id="priceListId" name="PriceListForm" property="priceListId" ></bean:define>
				<html:hidden property="priceListId" />
			<table align="left" cellpadding="5" width="100%">	
				<tr>
			<td colspan="3" align="right">
				<html:link href='<%="PriceListAction.do?action=viewPriceList&isNext=false&priceListId=" + priceListId%>'>
					<img src="images/tango/go-previous.png" title="Previous" alt="Previous" border="0">
				</html:link>
				<html:link href="<%="PriceListAction.do?action=viewPriceList&isNext=true&priceListId=" + priceListId%>">
					<img src="images/tango/go-next.png" title="Next" alt="Next" border="0">
				</html:link>			
				<html:link href="<%="PriceListAction.do?action=viewPriceList&priceListId=0"%>">
					<img src="images/tango/document-new.png" title="Create PriceList" alt="Create PriceList" border="0">
				</html:link>
			</td>
		</tr>
				<tr>
					<td width="30%">	
						<label><pos:message key="name"/></label>
					</td>
					<td>
						<html:text property="name" style="width:50%"></html:text>
					</td>
				</tr>
				<tr>
					<td width="20%">	
						<label><pos:message key="smenu.organisation"/></label>
					</td>
					<td width="20%">
						<label><html:select property="orgId" styleClass="text">
							<html:options collection="<%=Constants.USER_ORGS%>" property="key" labelProperty="name" />
						</html:select></label>
					</td>
					<td align="left">
						<label><pos:message key="IsActive"/></label>
					
						<html:checkbox  property="isActive" value="true"/>
					</td>
				</tr>
				<tr>
					<td>
						<label><pos:message key="type.of.price.list"/></label>
					</td>
					<td>
						<html:radio property="isSOPriceList" value="false"/><label><pos:message key="purchase"/></label>
						<html:radio property="isSOPriceList" value="true"/><label><pos:message key="sales"/></label>
					</td>
				</tr>
				<tr>
					<td>
						<label><pos:message key="isDefault"/></label>
					</td>
					<td>
						<html:checkbox  property="isDefault" value="true"/>
					</td>
				</tr>
				<tr>
					<td>
						<label><pos:message key="isPresentForProduct"/></label>
					</td>
					<td>
						<html:checkbox  property="isPresentForProduct" value="true"/>
					</td>
				</tr>
				<tr>
					<td>
						<label><pos:message key="isMandatory"/></label>
					</td>
					<td>
						<html:checkbox  property="isMandatory" value="true"/>
					</td>
				</tr>
				<tr>
					<td>
						<label><pos:message key="isTaxIncluded"/></label>
					</td>
					<td>
						<html:checkbox  property="isTaxIncluded" value="true"/>
					</td>
				</tr>
				<tr>
					<td>
						<label><pos:message key="isCreatePriceList"/></label>
					</td>
					<td>
						<html:checkbox  property="isCreatePriceList" value="true" onclick="togglePanel()"/>
					</td>
				</tr>
			</table>		
			
		</td>
	</tr>
</table>
<table id="CopyPriceListPanel" width="100%" style= "display:none;">
	<tr>			
		<td width="30%"><label><pos:message key="basePriceList"/></label></td>
		<td>
			<label><html:select property="basePriceListId" styleClass="text">
			<html:options collection="<%=Constants.USER_PRICE_LISTS%>" property="key" labelProperty="name" />
			</html:select></label>
		</td>
	</tr>
	<tr>
		<td width="30%"><label><pos:message key="isDeleteOldRecords"/></label></td>
		<td><html:checkbox  property="isDeleteOldRecords" value="true"/></td>
	</tr>
</table>
<table>
	<tr>
		<td align="left">
			<html:submit styleClass="save smallbutton">&nbsp;</html:submit>
		</td>
	</tr>
</table>
</html:form>
<script type="text/javascript">
function togglePanel()
{
	var isCreatePriceList = document.PriceListForm.isCreatePriceList.checked;
	
	if (isCreatePriceList)
	{
		showPanel($('CopyPriceListPanel'));
	}
	else
	{
		hidePanel($('CopyPriceListPanel'));
	}
}
</script>
<%@ include file="/jsp/include/posFooter.jsp" %>