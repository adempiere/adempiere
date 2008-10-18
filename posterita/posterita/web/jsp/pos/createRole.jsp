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
 * @author Praveen
--%>
<!-- createRole.jsp -->
<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.beans.UDIBean" %>
<%@ page import="org.posterita.core.MenuItem" %>


<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>	


<bean:define id="title"><pos:message textOnly="true" key="Role"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %>

<html:form action="/CreatePOSRoleAction" styleId="roleConfig">
<html:hidden property="action" value="createRole"/>
		
<table class="display" border="1">
   <tr>
   		<td align="left" class="contentName">
   			<strong><pos:message key="Name"/></strong>
   		</td>
   		<td  class="contentName"><html:text property="name" styleClass="text"/></td>
   </tr>
   <tr>
		<td align="left"><pos:message key="IsAccessAllOrgs"/></td>
		<td>
			<html:checkbox property="isAccessAllOrgs" value="true"/>
		</td>
	</tr>
	
	<tr>
		<td>
			<label><pos:message key="organisation.name"/>:</label><mandatory>*</mandatory>
		</td>
	
		<td>
			<html:select property="orgId" styleClass="text">
				<html:options collection="<%=Constants.USER_ORGS%>" property="key" labelProperty="name"/>
		 	</html:select>
		</td>
	</tr>	
	
	<tr id="discLimitPrice">				
		<td align="left"><pos:message key="discount.upto.price.limit"/></td>
   		<td><html:checkbox property="isDiscountUptoLimitPrice" styleId="isDiscountUptoLimitPrice" value="true"/></td>
	</tr>
	<tr id="overidePrice">				
			<td>
				<label><pos:message key="over.ride.limit.price"/></label>
			</td>
			<td>
   				<html:checkbox property="isOverwritePriceLimit" styleId="isOverwritePriceLimit" value="true"/>
   			</td>
	</tr>
	<tr id="discountOnTotal">				
			<td>
				<label><pos:message key="discount.allowed.total"/></label>
			</td>
			<td>
   				<html:checkbox property="isDiscountAllowedOnTotal" styleId="isDiscountAllowedOnTotal" value="true"/>
   			</td>
	</tr>
   <tr id="discount">			
		<td class="contentName" >
			<label><pos:message key="UserDiscount"/></label>
		</td>
		<td class="contentName" >
			<html:text property="userDiscount" styleId="userDiscount" styleClass="text"/>
		</td>
	</tr>
	</table>
<table class="main">	
	<tr>
		<td>
		<label class="green"><pos:message key="available.menu"/></label>
			<table class="display" border="1">
			   <logic:present name="<%= Constants.ROLE_MENUS%>">	
				   <logic:iterate id="element" indexId="count" name="<%= Constants.ROLE_MENUS%>" type="org.posterita.core.MenuItem">								   	
					   <logic:equal name="element" property="hasSubMenu" value="true">
						   <tr>
						   		<td class="contentName" colspan="2">
		   							<strong>
		   								<pos:message key="<%=element.getName()%>" textOnly="true"/>
		   							</strong>
		   						</td>				   		
						   </tr>				   
							<logic:iterate id="ele" collection="<%=element.getSubMenus()%>" type="org.posterita.core.MenuItem">
								<tr>
									<td>
										<%--<bean:write name="ele" property="name"/> --%>
										<pos:message key="<%=ele.getName()%>"  textOnly="true"/>
									</td>
									<td>
										<input type="checkbox" name="checkBox" value="<bean:write name='ele' property='menuId'/>"/>
									</td>
								</tr>
							</logic:iterate>							   						
						</logic:equal>	
						<logic:equal name="element" property="hasSubMenu" value="false">
						   <tr>
						   		<td class="contentName" colspan="2">
		   							<strong><pos:message key="<%=element.getName()%>" textOnly="true"/></strong>
		   						</td>				   		
						   </tr>				   
							<tr>
								<td>
									<pos:message key="<%=element.getName()%>" textOnly="true"/>
								</td>
								<td>
									<!-- N.B: Should always keep the following box disabled -->
									<input type="checkbox" name="checkBox" value="<bean:write name='element' property='menuId'/>"  checked="checked" disabled="disabled"/>
								</td>
							</tr>
						</logic:equal>
					</logic:iterate>
				</logic:present> 
			</table>
		</td>
	</tr>
					
	
	<tr align="right">
		<td colspan="2">
			<html:button styleClass="bigbtn" onclick="clearAll(this.form)" property="button">
				Clear All
			</html:button>
			<html:button styleClass="bigbtn" onclick="selectAll(this.form)" property="button">
				Select All
			</html:button>
			<html:submit styleClass="cntbtn">
 		                       <bean:message key="button.save"/>
             </html:submit>  
		</td>
	</tr>	
</table>				
<script type="text/javascript">
<!--
	var isDiscountUptoLimitPrice = $('isDiscountUptoLimitPrice').checked;
	var userDiscount = $('userDiscount');
	var isOverwritePriceLimit = $('isOverwritePriceLimit').checked;
	var isDiscountAllowedOnTotal = $('isDiscountAllowedOnTotal').checked;
	
	if(isDiscountAllowedOnTotal == true)
	{
		$('userDiscount').enable();
	}
		
	$('isOverwritePriceLimit').onclick = function(e)
	{
		if($('isOverwritePriceLimit').checked == true)
		{
			$('isDiscountUptoLimitPrice').checked = false;
			$('userDiscount').enable();
			$('userDiscount').focus();
		}
	};
	
	$('isDiscountAllowedOnTotal').onclick = function(e)
	{
		if($('isDiscountAllowedOnTotal').checked == true)
		{
			$('userDiscount').enable();
			$('userDiscount').focus();
		}
	};
	
	$('isDiscountUptoLimitPrice').onclick = function(e)
	{
		if($('isDiscountUptoLimitPrice').checked == true)
		{
			$('isOverwritePriceLimit').checked = false;
		}
		else
		{
			$('userDiscount').enable();
			$('userDiscount').focus();
		}
		
	};
	
	$('roleConfig').onsubmit = function(e)
	{
		var discount = $('userDiscount').value;
		if(discount == null || discount == '')
		{
			$('userDiscount') = '0.00';
		}
		
		if(isNaN(discount))
		{
			alert("Invalid user discount!");
			$('userDiscount').select();
			$('userDiscount').focus();
			
		}
		
	};
	
//-->
</script>	
</html:form>    									 					 
<%@ include file="/jsp/include/posFooter.jsp" %>