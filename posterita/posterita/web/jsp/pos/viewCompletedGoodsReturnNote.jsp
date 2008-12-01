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


<!--viewCompletedGoodsReturnNote.jsp-->
<%@ page import="org.posterita.Constants" %>
<%@ page import="org.compiere.model.MOrder" %>
<%@ page import="org.posterita.user.WebUserInfo" %>
<%@ page import="java.util.ArrayList" %> 
<%@ page import="org.compiere.process.DocAction" %>


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

<bean:define id="title"><pos:message key="goods.returned.note" textOnly="true"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %> 

<div align="right">
	<a href="javascript:showDocumentPDF(<c:out value='${morder._ID}'/>);">
		<img style="cursor:pointer" src="images/ico_printer.gif" border="0"/>
	</a>
</div>

<%@ include file="/jsp/pos/orderHeader.jsp" %> 
<table class="cart">
<tr>	        
	<th align="left" colspan="2"><pos:message key="Name"/></th>
	<th align="left"><pos:message key="PriceActual"/></th>
	<th align="left"><pos:message key="Discount"/></th>
	<th align="right"><pos:message key="Qty"/></th>
	<th align="right"><pos:message key="Price"/></th>
	<th align="right"><pos:message key="vat"/></th>
	<th align="right"><pos:message key="total"/>Total</th>			
</tr>
   		   		
<logic:present name="<%=Constants.GOODS_RETURN_NOTE_LINES%>">
<logic:iterate indexId="count" name="<%=Constants.GOODS_RETURN_NOTE_LINES%>" id="element">
	 
<%
		String styleClass = "label";
		if ((count.intValue()%2) != 0)
			styleClass = "contentname";
%>
<tr>											
	
	<td align="left" class=<%=styleClass%> colspan="2">
		<bean:write name="element" property="productName"/>
	</td>
	
	<td class=<%=styleClass%> align="center">
		<bean:write name="element" property="unitPrice"/>
	</td>
	
	<td class=<%=styleClass%> align="center">
		<bean:write name="element" property="discountPercentage"/>
	</td>
	
	<td align="right" class=<%=styleClass%>>
		<bean:write name="element" property="qtyOrdered"/>
		<bean:define id="qtyTotal" name="element" property="qtyTotal"/>
	</td>
	
	
	
	<td align="right" class=<%=styleClass%>>
		<bean:define id="lineNetAmt" name="element" property="lineNetAmt"/>
		<fmt:formatNumber value='${lineNetAmt}' type="currency" currencySymbol=" "/>
	</td>
	<td align="right" class=<%=styleClass%>>
		<bean:define id="taxAmt" name="element" property="taxAmt"/>
		<fmt:formatNumber value='${taxAmt}' type="currency" currencySymbol=" "/>
	</td>
	<td align="right" class=<%=styleClass%>>
		<bean:define id="lineTotalAmt" name="element" property="lineTotalAmt"/>
		<fmt:formatNumber value='${lineTotalAmt}' type="currency" currencySymbol=" "/>
	</td>		
</tr>
				
</logic:iterate>	 
</logic:present>

<tr>
	<bean:define id="currSymbole" name="<%= Constants.CURRENCY_SYMBOLE %>"/>
 		<td align="left" class="total" colspan=2 > 	
		<bean:message key="orderView.orderPriceDetails.orderTotal"/>
	</td>
	<td class="total"> 	
					&nbsp;
   </td>
   <td class="total"> 	
					&nbsp;
   </td>
	
	<td nowrap class="total" align="right">  		  
			<fmt:formatNumber value='${qtyTotal}'/>
	</td>

  		<td align="right" nowrap="nowrap" class="total" >
 			<fmt:formatNumber value='${totalLines}' type="currency" currencySymbol='${currSymbole}'/>
 		</td>
  									  						
	<td align="right" nowrap="nowrap" class="total" > 		  
		<fmt:formatNumber value='${orderTax}' type="currency" currencySymbol='${currSymbole}'/>
  	</td>

	<td align="right" nowrap="nowrap" class="total" > 		  
		<fmt:formatNumber value='${grandTotal}'type="currency" currencySymbol='${currSymbole}'/>
	</td>
</tr>
	<logic:equal name="<%=Constants.GOODS_RETURN_NOTE%>" property="docStatus" value="<%=DocAction.STATUS_Drafted%>">	
		<%
			MOrder order = (MOrder) request.getSession().getAttribute(Constants.GOODS_RETURN_NOTE);
		%>		
		<tr>
			<td colspan="6" align="right">
				<html:form action="/POSDeleteOrderAction" onsubmit="return confirmDelete(this)">
					<html:hidden property="action" value="deleteOrder"/>
					<html:hidden property="orderId" value="<%=order.get_ID()+""%>"/>
					<html:submit property="btn" styleClass="blank smallbutton" value="Delete">&nbsp;</html:submit>
				</html:form>				
			</td>
		</tr>
	</logic:equal>

</table>

<%@ include file="/jsp/include/posFooter.jsp" %>
