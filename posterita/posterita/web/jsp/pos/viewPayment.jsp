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


<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.user.WebUserInfo" %>
<%@ page import="org.posterita.order.UDIOrderTypes" %>
<%@ page import="org.compiere.model.MPayment" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/dcs.tld" prefix="dcs" %>


<bean:define id="title">Payment</bean:define>

<%@ include file="/jsp/include/posHeader.jsp" %> 
<%@ include file="/jsp/include/errors.jsp" %>
<bean:define id="currSymbole" name="<%= Constants.CURRENCY_SYMBOLE %>"/>

<%--
@TODO by Alok
<div align="right">
	<a href="javascript:showPaymentDocumentPDF(<c:out value='${payment._ID}'/>);">
		<img style="cursor: pointer;" src="images/ico_printer.gif" border="0">
	</a>
</div>
--%>
	<table width="100%" border="0" cellpadding="5" cellspacing="0" class="orderheader">
					
		<%@ include file="/jsp/pos/webDocumentHeader.jsp" %>

		<tr>
			<td>
				<logic:present name="<%=Constants.WEB_DOCUMENT_HEADER_BEAN%>" property="paymentType">
				Payment By:	<bean:write name="<%=Constants.WEB_DOCUMENT_HEADER_BEAN%>" property="paymentType"/>
				<br>
				</logic:present>		   	
  				Ref Payment No: <c:out value='${payment._ID}'/>
  				<br>  			
 				Status: <dcs:orderStatus name="<%=Constants.MPAYMENT%>" property="docStatus"/>
 			</td>
		</tr>			  			  		
  		
		<tr>
  			<td valign="top">
  			<div class="space"></div>
				<table class="cart">
			  		<tr>
					   	<th class="contentname" align="left">Payment Date</th>
					   	<th class="contentname" align="left">Amount</th>
			  		</tr>

	   				<tr>
	   					<td class="label">
	   						<fmt:formatDate value='${payment.dateTrx}' pattern='MMM dd yyyy, HH:mm'/>
	   					</td>

	   					<td class="label">
							<bean:define id="amount" name="<%=Constants.MPAYMENT%>" property="payAmt"/>
							<fmt:formatNumber value='${amount}' maxFractionDigits="2" type="currency" currencySymbol='${currSymbole}'/>
	   					</td>
	   				</tr>
	 			</table>
			</td>
		</tr>

		
	</table>



	<%@ include file="/jsp/include/posFooter.jsp" %>
