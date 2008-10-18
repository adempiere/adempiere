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

<!-- createMultipleInvoicePayment.jsp -->
<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.struts.pos.PaymentAction" %>
<%@ page import="org.compiere.model.MProduct" %>
<%@ page import="org.posterita.beans.ProductBean" %>
<%@ page import="org.posterita.user.WebUserInfo" %>
<%@ page import="org.posterita.lib.UdiConstants" %>
<%@ page import="org.posterita.form.PaymentForm" %>
<%@ page import="java.math.BigDecimal" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

<logic:notPresent name="<%=WebUserInfo.NAME%>" scope="session">
	<jsp:forward page="/LoginHome.do"/>
</logic:notPresent>

<bean:define id="title"><pos:message textOnly="true" key="create.payment"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %> 
						   	
<html:form action="/CreateMultipleInvoicePaymentAction">
<html:hidden property="action" value="<%=PaymentAction.CREATE_PAYMENT_FOR_MULTIPLE_INVOICE%>"/>
<html:hidden property="isSoTrx" value="true"/>

<table border="0" class="display">
	<tr>		
		<td>
			<fieldset>
			<label><pos:message key="Amount"/></label>
			<html:text property="amount"/>
			</fieldset>
		</td>		
	</tr>	
	
	<tr>		
		
			<th>
				<label><pos:message key="invoice.no"/></label>
			</th>
			<th>
				<label><pos:message key="DaysDue" textOnly="true"/></label>
			</th>
			
			<th>
				<label><pos:message key="DueDate" textOnly="true"/></label>
			</th>
			<th>
				<label><pos:message key="GrandTotal"/></label>
			</th>
			<th>				
				<label><pos:message key="Discount"/></label>
			</th>
			<th align="right">	
				<label><pos:message key="PayAmt"/></label>
			</th >
			<th align="right">
				<label><pos:message key="OpenAmt"/></label>
			</th>
			<th align="right">				
				<label><pos:message key="allocate.Amt"/></label>
			</th>	
			
		
		
	</tr>
	<%
		double openAmtTotal = 0.0d;
		double paidAmtTotal = 0.0d;
		double discountAmtTotal = 0.0d;
		double invoiceGrandTotal = 0.0d;
	%>	
	<logic:present name="<%=Constants.INVOICE_FOR_ALLOCATION%>">		
		<logic:iterate indexId="count" name="<%=Constants.INVOICE_FOR_ALLOCATION%>" id="element" type="org.posterita.beans.OpenItemBean">
		
			<%
				String styleClass = "label";
				if ((count.intValue()%2) != 0)
					styleClass = "contentname";
			%>
		
	<tr>
	
		<td align="center" class=<%=styleClass%> >
			 <bean:write name="element" property="invoiceNo"/>
			 <html:hidden name="element" property="bpartnerId"/>
			 <html:hidden property="invoiceIds" value="<%=element.getInvoiceId().toString()%>"/>
		</td>
		<td class=<%=styleClass%>>
			<bean:write name="element" property="daysDue"/>
		</td>
		<td class=<%=styleClass%>>
			<bean:write name="element" property="dueDate"/>
		</td>	
		<td align="right" class=<%=styleClass%> >
			<bean:write name="element" property="invoiceGrandTotal"/>
		</td >	
				
		<td align="right" class=<%=styleClass%> >
			<bean:write name="element" property="discountAmt"/>
		</td>	
		<td align="right" class=<%=styleClass%> >
			<bean:write name="element" property="paidAmt"/>
		</td>
		<td align="right" class=<%=styleClass%> >
			<bean:write name="element" property="openAmt"/>
			<input type="hidden" name="openAmt" value="<bean:write name='element' property='openAmt'/>">			
		</td>	
		
				<%
						String defaultAllocateAmount = "0";
					
						
						PaymentForm form = (PaymentForm)request.getAttribute("PaymentForm");
						
						if(form!=null)
						{
							String[] allocateAmount = form.getAllocateAmount();
							
							
							if(allocateAmount!= null)
							if(allocateAmount.length!=0) 
								defaultAllocateAmount = allocateAmount[count.intValue()];
						}
					%>	
				
		
		<td align="right" class=<%=styleClass%> >
			<html:text property="allocateAmount" value="<%=defaultAllocateAmount%>"/>
		</td>	
	
	</tr>	
	<% 
		openAmtTotal += element.getOpenAmt().doubleValue(); 
		paidAmtTotal += element.getPaidAmt().doubleValue();
		discountAmtTotal += element.getDiscountAmt().doubleValue();
		invoiceGrandTotal += element.getInvoiceGrandTotal().doubleValue();
	%>
 </logic:iterate>
 <tr>
 	<th align="left" colspan="3">Total</th>
 	<th align="right"><%=invoiceGrandTotal%></th>
 	<th align="right"><%=discountAmtTotal%></th>
 	<th align="right"><%=paidAmtTotal%></th>
 	<th align="right"><%=openAmtTotal%></th> 
 	<th>&nbsp;</th> 		
 </tr>
 <tr>
		<td>
			<%@ include file="/jsp/include/tenderPanel.jsp" %>
		</td>		
</tr>	
 	 <td colspan="6" align="right">
		<html:submit styleClass="save smallbutton">
			&nbsp;
		</html:submit>	
	 </td>
 </logic:present>	
</table>
</html:form> 							
<%@ include file="/jsp/include/posFooter.jsp" %>
<script>
var amt = $FElement('amount');
var allocAmts = document.getElementsByName('allocateAmount');
var openAmts = document.getElementsByName('openAmt');

function allocateAmt()
{
	try
	{	
		var remainder = amt.value;
	
		for(var i=0; i<allocAmts.length; i++)
		{
			if(remainder - openAmts[i].value > 0)
			{
				allocAmts[i].value = new Number(openAmts[i].value).toFixed(2);
				remainder = remainder - openAmts[i].value;
			}
			else
			{
				allocAmts[i].value = new Number(remainder).toFixed(2);
				remainder = 0;
			}
		}
	}
	catch(e)
	{
		toConsole(e)
	}	
}

amt.onkeyup = allocateAmt;
</script>
