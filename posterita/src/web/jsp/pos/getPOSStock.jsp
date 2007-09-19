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

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

<logic:notPresent name="<%=WebUserInfo.NAME%>" scope="session">
	<jsp:forward page="/LoginHome.do"/>
</logic:notPresent>

<bean:define id="title"><pos:element textOnly="true" columnName="stock.inquiry"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %> 

<html:form action="/GetStockAction" focus="productName">
<html:hidden property="action" value="<%=POSStockAction.GET_STOCK_FROM_SEARCH%>"/>
<table class="main">
	<tr>
		<td>
			<label><pos:element columnName="Name"/> :</label>
			<html:text property="productName" styleClass="text"/>
			
			
			<label><pos:element columnName="barcode"/> :</label>   	 		
			<html:text property="barCode" styleClass="text" />
			
			
	        <html:submit styleClass="tangoSearch tangoButton">
	        	&nbsp;
	        </html:submit>
  	 
  	 		&nbsp;&nbsp;
  	  
  	 		<label><pos:element columnName="Qty"/> :</label>		
			<html:select property="qtyFilter" onchange="setActionMethod(this.form,'getStockFromSearch');" styleClass="text">
			    <html:option value=""><pos:element columnName="all"/></html:option>
				<html:option value="> 0"> > 0</html:option>
				<html:option value="< 0"> < 0</html:option>
				<html:option value="= 0"> = 0</html:option>
				<html:option value="> 25"> > 25</html:option>
				<html:option value="> 50"> > 50</html:option>
				<html:option value="> 100"> > 100</html:option>
			</html:select>
	   	</td>		
    </tr>
 		
	<tr>
		<td align="right">
		<table>
		<tr>
			<td><label><pos:element columnName="attribute.set"/> :</label></td>
			<td>
				<html:select property="attributeSetId" onchange="setActionMethod(this.form,'getStock')" styleClass="text">			    
		   			<html:option value=""><pos:element columnName="select"/></html:option>
					<html:options collection="<%=Constants.PRODUCT_ATTRIBUTE_SET%>" property="attributeSetId" labelProperty="attributeSetName"/>
				</html:select>
			</td>
		</tr>
		</table>
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
					     <html:option value=""><pos:element columnName="all"/></html:option> 
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
		<bean:size id="size" name="<%=Constants.PRODUCTS%>"/>
		<logic:equal name="size" value="0">
		<pos:element columnName="found.none"/>
		</logic:equal>
		<logic:notEqual name="size" value="0">
		<table class="display sortable" border="1" id="1111">
		<tr>
			<th align="left"><pos:element columnName="Product"/></th>
			<th align="left"><pos:element columnName="barcode"/></th>
			<th class="numeric"><pos:element columnName="Qty"/></th>
		     <th class="numeric"><pos:element columnName="price.x"/></th>			
		</tr>	
		<%
				  int totalCount = 0;
				  double totalPrice = 0.00;
		%>		  
		<logic:iterate offset="<%=offset%>" length="<%=length%>" indexId="count" id="element" name="<%=Constants.PRODUCTS%>" type="org.posterita.beans.AttributeBean">
		<%
		String styleClass = "label";
		if ((count.intValue()%2) != 0)
		styleClass = "contentname";
		%>		
		<tr>
			<td class=<%=styleClass%>> 
				<bean:write name="element" property="productName"/>
			 </td>
			 <td class=<%=styleClass%>> 
				<bean:write name="element" property="barCode"/>
			 </td>
			<td class=<%=styleClass%>>
				
				<%
				String styleClass1 = "nomalcolor";
				if(element.getQuantity().intValue()<= 0)
				styleClass1= "negativecolor";
				%>
				
				<%
				  totalCount = totalCount + element.getQuantity().intValue();
				  totalPrice = totalPrice + element.getPrice().doubleValue();;
				%>
				
								
				<div class="<%=styleClass1%>" align ="center">
				<bean:write name="element" property="quantity"/>
				</div>
			</td>	
			 <td class=<%=styleClass%> align ="center"> 
			 	
				<bean:write name="element" property="price"/>
			 </td>				
		</tr>	   
		</logic:iterate>
		<tr>
			<td colspan="2" align ="left">
					Total
			</td>
			<td colspan="1" align ="center">
					<%=totalCount%>
			</td>
			<td colspan="1" align ="center">
					<%=totalPrice%>
			</td>
		</tr>
				
				
		</table>
		<%@ include file="/jsp/include/pager.jsp" %>		
		</logic:notEqual>		
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
