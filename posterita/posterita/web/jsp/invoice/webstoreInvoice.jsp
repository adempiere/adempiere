<!-- webstoreInvoice.jsp -->
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.struts.stock.StockAction" %>

<%@ page import="org.posterita.beans.ProductBean" %>
<%@ page import="org.posterita.beans.OrderLineBean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/dcs.tld" prefix="dcs" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<bean:define id="title">Invoice</bean:define>

<%@ include file="/jsp/include/posHeader.jsp" %> 
<%@ include file="/jsp/include/errors.jsp" %> 

<div align="right">
	<a href="javascript:showInvoiceDocumentPDF(<c:out value='${minvoice._ID}'/>);">
		<img style="cursor:pointer" src="images/ico_printer.gif" border="0"/>
	</a>
</div>


<table width="100%" border="0" cellspacing="0" class="orderheader">


<bean:define id="currSymbole" name="<%= Constants.CURRENCY_SYMBOLE %>"/>


  		   			<tr>
			   			<td colspan="2">
			  				Order Ref No: 
			  				<a href="ViewPOSOrderAction.do?action=viewPOSOrders&orderId=<c:out value="${morder._ID}"/>">
							<c:out value="${morder.documentNo}"/>
			  				</a>		  				
			  				
			  				<br>		  			
			  				Invoice Ref No: <bean:write name="<%=Constants.MINVOICE%>" property="c_Invoice_ID"/>
			  				<br>
							Doc Status: <dcs:orderStatus name="<%=Constants.MINVOICE%>" property="docStatus"/>
							
						</td>
						
			   		</tr>			   		
			   					   					   				   		
			  	</table>
			  </td>			
   		</tr>
  		
		<tr class="white">
  			<td valign="top">
  				<div class="space"></div>	
				<table class="cart">
			  		<tr>
					   	<th align="left" width="60%">Name</th>
					   	<th align="right">Qty</th>
					   	<th align="right">Price</th>
					   	<th align="right">VAT</th>
					   	<th align="right">Total</th>
			  		</tr>

	   				<logic:iterate indexId="count" name="<%=Constants.MINVOICE_LINES_COLLECTION%>" id="element" type="org.posterita.beans.WebInvoiceLineBean">
						<%
							String styleClass = "label";
							if ((count.intValue()%2) != 0)
								styleClass = "contentname";
						%>
		   					
		   				<tr>
		   					<td class="<%=styleClass%>">
		   						<bean:write name="element" property="description"/>
		   					</td>

		   					<td class="<%=styleClass%>" align="right" >
		   						<bean:write name="element" property="qtyOrdered"/>
		   					</td>

		   					<td class="<%=styleClass%>" align="right" >
			   					<fmt:formatNumber value='${element.lineNetAmt}' type="currency" currencySymbol=""/> 
		   					</td>

		   					<td class="<%=styleClass%>" align="right" >
			   					<fmt:formatNumber value='${element.taxAmt}' maxFractionDigits="2" type="currency" currencySymbol=""/> 
		   					</td>
				   					
		   					<td class="<%=styleClass%>" align="right" >
			   					<fmt:formatNumber value='${element.lineTotalAmt}' maxFractionDigits="2" type="currency" currencySymbol=""/> 
		   					</td>

		   					<logic:equal name="element" property="isInvoiced" value="false">
			   					<td class="<%=styleClass%>">
				   					<html:checkbox property="checkBox" value="<%=element.getOrderLineId().toString()%>"/>
			   					</td>	
		   					</logic:equal>
		   				</tr>
		   			</logic:iterate>
				   				
				   	<tr>
						<td class="total" colspan="2"> 	
							<bean:message key="orderView.orderPriceDetails.orderTotal"/>
						</td>

						<td class="total" align="right" >
						 	<fmt:formatNumber value='${totalLines}'type="currency" currencySymbol=""/>
			 			</td>
					  									  						
						<td class="total" align="right" > 		  
							<fmt:formatNumber value='${invoiceTax}' type="currency" currencySymbol=""/>
					  	</td>
		
						<td class="total" align="right" > 		  
							<fmt:formatNumber value='${grandTotal}'type="currency" currencySymbol='${currSymbole}'/>
					  	</td>
		  			</tr>
				 </table>
			</td>
		</tr>

</table>

<%@ include file="/jsp/include/posFooter.jsp" %>