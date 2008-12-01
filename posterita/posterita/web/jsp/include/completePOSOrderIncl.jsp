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

<!--CompletePOSOrderIncl.jsp-->
<%@ page import="org.posterita.Constants" %>
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

<%@ include file="/jsp/include/posHeader.jsp" %>   
<%@ include file="/jsp/include/errors.jsp" %> 

<div align="right">
	<a href="javascript:showDocumentPDF(<c:out value='${morder._ID}'/>);">
		<img style="cursor:pointer" src="images/ico_printer.gif" border="0"/>
	</a>
</div>
<%@ include file="/jsp/pos/orderHeader.jsp" %> 
<% String orderlines = Constants.POS_ORDER_LINES; %>
<%@ include file="/jsp/pos/completePOSShoppingCart.jsp" %> 

<div class="space"></div>

<div>
<table cellpadding="0" cellspacing="0">
	<logic:greaterThan name="morder" property="amountTendered" value="0">
	<tr>
		<td><label><pos:message key="cash.tendered"/></label></td>
		<td><label>:<fmt:formatNumber value='${morder.amountTendered}'type="currency" currencySymbol='${currSymbole}'/></label></td>
	</tr>
	</logic:greaterThan>	
	
	<logic:present name="<%=Constants.CARD_AMT_TENDERED%>">
	<logic:greaterThan name="<%=Constants.CARD_AMT_TENDERED%>" value="0">
	<bean:define id="cardAmtTendered" name="<%=Constants.CARD_AMT_TENDERED%>"/>
	<tr>
		<td><label><pos:message key="card.amt.tendered"/></label></td>
		<td><label>:<fmt:formatNumber value='${cardAmtTendered}'type="currency" currencySymbol='${currSymbole}'/></label></td>
	</tr>
	</logic:greaterThan>
	</logic:present>
	
	<logic:present name="<%=Constants.CHEQUE_AMT_TENDERED%>">
	<logic:greaterThan  name="<%=Constants.CHEQUE_AMT_TENDERED%>" value="0">
	<bean:define id="chequeAmtTendered" name="<%=Constants.CHEQUE_AMT_TENDERED%>"/>
	<tr>
		<td><label><pos:message key="cheque.amt.tendered"/></label></td>
		<td><label>:<fmt:formatNumber value='${chequeAmtTendered}'type="currency" currencySymbol='${currSymbole}'/></label></td>
	</tr>
	</logic:greaterThan>
	</logic:present>
	
	<logic:greaterThan name="morder" property="amountTendered" value="0">
	<tr>
		<td><label><strong><pos:message key="cash.refunded"/></strong></label></td>
		<td><label><strong>:<fmt:formatNumber value='${morder.amountRefunded}'type="currency" currencySymbol='${currSymbole}'/></strong></label></td>
	</tr>
	</logic:greaterThan>
</table>	
</div>


<table cellspacing="0" border="0" width="100%" cellpadding="0">
<tr>
	<td>&nbsp;</td>
</tr>
<tr>
	<td align="right">			
		<input type="button" value="" class="neworder bigbutton" accesskey="n" onclick="createNewOrder()">			
	</td>
</tr>
<tr>					  
	<td align="center">
		<label><pos:message key="new.order"/> -</label>
		<label class="red">Alt-N</label>	   	 	  	
	</td>		   		
</tr>	
</table>
<%@ include file="/jsp/include/printOrderApplet.jsp" %>
<%@ include file="/jsp/include/posFooter.jsp" %>