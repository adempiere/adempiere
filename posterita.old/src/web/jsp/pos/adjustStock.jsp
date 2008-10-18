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

<!--adjustStock.jsp-->

<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.compiere.model.MOrg" %>
<%@ page import="org.posterita.struts.pos.InventoryAction" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.posterita.beans.InventoryBean" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos" %>

<bean:define id="title"><pos:element columnName="W_InvActualAdjust_Acct" textOnly="true"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %> 


<html:form action="/AdjustStockAction" focus="barCode" >
<html:hidden property="action" value="<%=InventoryAction.CREATE_INVENTORY%>"/>
	<div>Bar Code</div>
	<html:text property="barCode" value=""/>
	<html:submit>
		 add
	 </html:submit>
	<div>Inventory Name</div>
	<html:text property="description"/>
	
	<logic:present name="<%=Constants.INVENTORY_LINE_LIST%>">
	<logic:notEmpty name="<%=Constants.INVENTORY_LINE_LIST%>"> 
	 <logic:iterate  indexId="count" name="<%=Constants.INVENTORY_LINE_LIST%>" id="element" type="org.posterita.beans.InventoryBean" >	
	 	<html:hidden property="inventoryLineId" value="<%=element.getInventoryLineId().toString()%>"/>
	 	<html:hidden property="inventoryId" value="<%=element.getInventoryId().toString()%>"/>
	 </logic:iterate>	
	 </logic:notEmpty>
	 </logic:present>
</html:form>

<div>
	<table border="0" cellpadding="0" cellspacing="0" width="50%">
		<tr>		
			<td align="left">
				<html:form action="AdjustStockAction">
				<html:hidden property="action" value=""/>
				<label>
					<pos:element columnName="ProductName"/>
				</label>
				<%@ include file="/jsp/include/searchProductPanel.jsp" %>
				<html:hidden property="barCode"/>
				<html:hidden property="productId"/>
			</td>
			<td>
				&nbsp;
			</td>  
		</tr>
	</table>
</div>


	<logic:present name="<%=Constants.INVENTORY_LINE_LIST%>">
	<logic:notEmpty name="<%=Constants.INVENTORY_LINE_LIST%>">
			<table class="display sortable scrollpane" border="1" id="vendor"> 		  		
				<tr>
					<th><pos:element columnName="ProductName"/></th>	
					<th><pos:element columnName="barcode"/></th>	
					<th><pos:element columnName="QtyBook"/></th>
					<th><pos:element columnName="QtyCount"/></th>
					<th><pos:element columnName="bookQtyValue"/></th>
					<th><pos:element columnName="countQtyValue"/></th>
					
					<th>&nbsp;</th>
					
					<th>&nbsp;</th>
				</tr>
			
			<%
				double SumQtyBook=0.0d;
				double SumQtyCount=0.0d;
				double SumBookValue=0.0d;
				double SumCountValue=0.0d;
			%>
			
			<logic:iterate  indexId="count" name="<%=Constants.INVENTORY_LINE_LIST%>" id="element" type="org.posterita.beans.InventoryBean" >	
			<%
				String styleClass = "label";
				
				if ((count.intValue()%2) != 0)
					styleClass = "contentname";
			%>
			<form>
			<tr>
				<td class="<%=styleClass%>">
					<bean:write name="element" property="productName"/>
					<html:hidden property="inventoryLineId" value="<%=element.getInventoryLineId().toString()%>"/>
					<html:hidden property="inventoryId" value="<%=element.getInventoryId().toString()%>"/>
					
				</td>	
				<td class="<%=styleClass%>">
					<bean:write name="element" property="barCode"/>
				</td>
				<td class="<%=styleClass%>">
					<bean:write name="element" property="qtyBook"/>
				</td>
				<td class="<%=styleClass%>">
					<html:text name="element" property="qtyCount" styleClass="text"/>
				</td>
				<td class="<%=styleClass%>">
					<bean:write name="element" property="bookQtyValue"/>
				</td>
				<td class="<%=styleClass%>">
					<bean:write name="element" property="countQtyValue"/>
				</td>
				<td class="<%=styleClass%>">
					<html:button property="btn" styleClass="newbutton" onclick="saveQty(this.form)">
					Save
					</html:button>
				</td>
				<td class="<%=styleClass%>">
					<html:link href="<%="DeleteInventoryLineAction.do?action=deleteInventoryLine&inventoryId=" + element.getInventoryId().toString()+"&inventoryLineId="+element.getInventoryLineId().toString() %>">
			   			<pos:element columnName="delete"/>
			   		</html:link>
				</td>
				
				<%
					SumQtyBook  =SumQtyBook + element.getQtyBook().doubleValue();
					SumQtyCount = SumQtyCount + element.getQtyCount().doubleValue();
					SumBookValue = SumBookValue + element.getBookQtyValue().doubleValue();
					SumCountValue= SumCountValue + element.getCountQtyValue().doubleValue();
				%>
			
			</tr>
			</form>
			</logic:iterate>
			
			<tr>
					<td colspan="2">
						Total
					</td>
					<td>
						<%=SumQtyBook%>
					</td>
					<td>
						<%=SumQtyCount%>
					</td>
					<td>
						<%=SumBookValue%>
					</td>
					<td>
						<%=SumCountValue%>
					</td>
				</tr>
			
			</table>
		
				
	</logic:notEmpty>
	</logic:present>
</html:form>	
<div class="space"></div>
<html:form action="CompleteAdjustStockAction">
<html:hidden property="action" value="completeInventory"/>
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<logic:present name="<%=Constants.INVENTORY_LINE_LIST%>">
		<logic:notEmpty name="<%=Constants.INVENTORY_LINE_LIST%>">
			<logic:iterate  indexId="count" name="<%=Constants.INVENTORY_LINE_LIST%>" id="element" type="org.posterita.beans.InventoryBean" >	
				<html:hidden property="inventoryLineId" value="<%=element.getInventoryLineId().toString()%>"/>
				<html:hidden property="inventoryId" value="<%=element.getInventoryId().toString()%>"/>
				
			</logic:iterate>
			
			<tr>
						
					<td align="right">
						<%
							Integer inventoryId = (Integer)request.getAttribute(Constants.INVENTORY_ID);
						%>
							<html:link href="<%="CreateCSVFileForInventoryAction.do?action=createCSVFileForInventory&inventoryId=" + inventoryId %>">
				   				generate CSV
				   		  </html:link>	
				     </td>
			</tr>
			
			<td align="right">
				<html:submit styleClass="newbutton">
					Complete
				</html:submit>
			</td>  			
		</logic:notEmpty>
	</logic:present>	
	
</table>	
</html:form>
<%@ include file="/jsp/include/posFooter.jsp" %>    
<script>
function addToCart()
{
	var form = document.forms[0];
	if(form)
	{
		setActionMethod(form,'createInventory');
	}
}
function saveQty(form)
{
	this.form = form;
	var qtyValue = trim(document.getElementsByName("qtyCount")[0].value);
	
	if(qtyValue==null || qtyValue.length==0)
	{
		alert("Quantity cannot be null");
		return;
	}	
	
	var action = 'AdjustStockAction.do?action=saveCountQty&inventoryLineId=' + this.form.inventoryLineId.value + '&qtyCount=' + this.form.qtyCount.value + '&inventoryId=' + this.form.inventoryId.value;
	window.location = action;
	toConsole(action);
}

function trim(sString)
{
	while (sString.substring(0,1) == ' ')
	{
		sString = sString.substring(1, sString.length);
	}
	while (sString.substring(sString.length-1, sString.length) == ' ')
	{
		sString = sString.substring(0,sString.length-1);
	}
	return sString;
}
</script>	
												