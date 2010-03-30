<!--settleAPPayment.jsp-->

<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.beans.*" %>
<%@ page import="org.posterita.struts.pos.CreditOrderAction" %>
<%@ page import="org.compiere.process.DocumentEngine" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.posterita.core.UDIPair" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos" %>

<logic:notPresent name="<%=WebUserInfo.NAME%>" scope="session">
	<jsp:forward page="/LoginHome.do"/>
</logic:notPresent>

<bean:define id="title"><pos:message key="smenu.settle.payment.credit.purchases" textOnly="true"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>


<html:form action="/GetBpartnerPaymentStatus">
<html:hidden property="action" value="<%=CreditOrderAction.GET_CREDITOR_PAYMENT_STATUS%>"/> 
<table>
<tr>
<td valign="top" width="400px">
	<fieldset>
	<legend><pos:message key="vendor.info" textOnly="true"/></legend>
	<div>
		<div>
			<label><pos:message key="vendor.id" textOnly="true"/></label>
		</div>
		<html:text property="bpartnerId" styleClass="text" accesskey="i" styleId="bpartnerId"/> 
		<div>
			<label><pos:message key="search.vendor" textOnly="true"/></label>
		</div>
		
		<%@ include file="/jsp/include/searchVendorPanel.jsp" %>
		<div class="space"></div>						
		<html:text property="partnerName" readonly="true" styleClass="text"/>
		<div class="space"></div>													
	</div>
	</fieldset>	
</td>
</tr>
</table>
</html:form>
<table>	
	<tr>
		<td class="red" >
			UnPaid 
		</td>
		<td class="yellow" >
			Partially Paid 
		</td>
		<td class="green" >
			Paid 
		</td>
		<td class="blue" >
			Over Paid 
		</td>

	</tr>
	
</table>
<table class="main">
<%--
	<tr>
		<td colspan="3" align="right">
			<label>Customer:</label>
			
			<html:select property="bpartnerId" onchange="submit()"  styleClass="text">
				<html:options collection="<%= Constants.BUSINESSPARTNER %>" property="bpartnerId" labelProperty="partnerName"/>																																	
		   </html:select>
		</td>
	</tr>
--%>
<html:form action="/MatchAPInvoiceAction">
<html:hidden property="action" value="<%=CreditOrderAction.MATCH_INVOICE%>"/>
	<logic:present name="<%=Constants.OPEN_ITEMS%>">
	<tr>
			<th align="left">
				<b><pos:message key="open.invoices" textOnly="true"/></b>
			</th>
		</tr>
		<tr>
			<td>
				<table border="1" class="display">	
				<tr>
						<th>
							Pay
						</th>
						<th>
							<pos:message key="invoice.no" textOnly="true"/>
						</th>
						<th>
							<pos:message key="order.no" textOnly="true"/>
						</th>
					
						<th>
							<pos:message key="Date1" textOnly="true"/>
						</th>
						<th>
							<pos:message key="PaymentTerm" textOnly="true"/>
						</th>
						
						<th>
							<pos:message key="DaysDue" textOnly="true"/>
						</th>
						
						<th>
							<pos:message key="DueDate" textOnly="true"/>
						</th>
						
						<th>
							<pos:message key="C_Currency_ID" textOnly="true"/>
						</th>
						
						<th>
							<pos:message key="discount.amt" textOnly="true"/>
						</th>
						
						<th>
							<pos:message key="GrandTotal" textOnly="true"/>
						</th>
						
						<th>
							<pos:message key="PaidAmt" textOnly="true"/>
						</th>
						
						<th>
							<pos:message key="OpenAmt" textOnly="true"/>
						</th>	
						<th>
							&nbsp;
						</th>						
				</tr>
					<logic:iterate indexId="count" id="element" name="<%=Constants.OPEN_ITEMS%>" type="org.posterita.beans.OpenItemBean">		
						<tr>
							<%	
								String styleClass = "";
							%>
							<logic:equal name="element" property="allocationStatus" value="<%=Constants.PAID%>">
							<%
									styleClass = "green";
							%>	
							</logic:equal>
							
							<logic:equal name="element" property="allocationStatus" value="<%=Constants.UNPAID%>">
							<%
									styleClass = "red";
							%>	
							</logic:equal>
							
							<logic:equal name="element" property="allocationStatus" value="<%=Constants.PARTIALLY_PAID%>">
								
									<%
											styleClass = "yellow";
									%>	
								
							</logic:equal>
							
							
							
							<logic:equal name="element" property="allocationStatus" value="<%=Constants.OVER_PAID%>">
								
									<%
											styleClass = "blue";
									%>	
								
							</logic:equal>
							<logic:lessThan name="element" property="openAmt" value="0">
									<%
											styleClass = "blue";
									%>	
							</logic:lessThan>
							<td class=<%=styleClass%> >
							<logic:notEqual name="element" property="allocationStatus" value="<%=Constants.PAID%>">
				
								<logic:greaterThan name="element" property="openAmt" value="0">
								  	<html:link href="<%="InitCreatePaymentAction.do?action=initCreatePayment&invoiceId=" + element.getInvoiceId()+
								  						"&invoiceNo="+element.getInvoiceNo()+"&invoiceGrandTotal="+element.getInvoiceGrandTotal()+
								  						"&paidAmt="+element.getPaidAmt()+"&openAmt="+element.getOpenAmt()+
								  						"&discountAmt="+element.getDiscountAmt()%>">
									 	Pay
									
									 </html:link>	
								 </logic:greaterThan>
							</logic:notEqual>								 	
							</td>
						
							<td class=<%=styleClass%> >
									<html:link href="<%="ViewInvoiceAction.do?action=viewOrder&documentId=" + element.getInvoiceId() %>">
										<bean:write name="element" property="invoiceNo"/>
									</html:link>
							</td>
							
							<td class=<%=styleClass%> >
								<html:link href="<%="ViewPOSOrderAction.do?action=viewPOSOrders&orderId=" + element.getOrderId() %>">
									<bean:write name="element" property="documentNo"/>
								</html:link>
							</td>
							<td class=<%=styleClass%> >
								<bean:write name="element" property="invoiceDate"/>
								
							</td>
							<td class=<%=styleClass%> >
								<bean:write name="element" property="paymentTermName"/>
								
							</td>
							<td class=<%=styleClass%>>
								<bean:write name="element" property="daysDue"/>
							</td>
							<td class=<%=styleClass%> >
								<bean:write name="element" property="dueDate"/>
							</td>
							
							<td class=<%=styleClass%> >
								<bean:write name="element" property="currencySymbole"/>
							</td>
							
							<td class=<%=styleClass%> >
								<bean:write name="element" property="discountAmt"/>
							</td>
							<td class=<%=styleClass%> >
								<bean:write name="element" property="invoiceGrandTotal"/>
								
							</td>
							<td class=<%=styleClass%> >
							
								<html:link href="<%="ViewPaymentAllocationAction.do?action=getAllocationDetailsForPartner&invoiceNo=" + element.getInvoiceNo()%>">
								 	<bean:write name="element" property="paidAmt"/>
								 </html:link>	
							</td>
							
							<td class=<%=styleClass%> >
								<bean:write name="element" property="openAmt"/>
								<input type="hidden" name="openAmt" value="<bean:write name='element' property='openAmt'/>">
							</td>
							<td class=<%=styleClass%> >
							
								<html:multibox property="invoiceIds" value="<%=element.getInvoiceId().toString()%>">
           						 </html:multibox>	
           						<html:hidden property="bpartnerId" 	value="<%=element.getBpartnerId().toString()%>"/>	
           					
							</td>	
							
						</tr>	
					</logic:iterate>
					
						<td colspan="10" align="right">
							<html:submit onclick="this.form.action.value='getMultipleInvoicesForPayment';" property="paybtn">
								Pay
							</html:submit>
						<td>	
					
						<td align="right">
							<html:submit onclick="this.form.action.value='matchInvoice';">
								Match
							</html:submit>
						<td>	
				</table>
		</td>
	</tr>
	
	</logic:present>
	
	
	<logic:present name="<%=Constants.UNALLOCATED_PAYMENTS%>">	
	
	<tr>
			<th align="left">
				<b><pos:message key="unallocated.payments" textOnly="true"/></b>
			</th>
	</tr>
		<tr>
			<td>
				<table border="1" class="display">	
				<tr>
					
						<th>
							<pos:message key="C_Payment_Id" textOnly="true"/>
						</th>
						<th>
							<pos:message key="DateTrx" textOnly="true"/>
						</th>
					
						<th>
							<pos:message key="TenderType" textOnly="true"/>
						</th>
						<th>
							<pos:message key="payAmt" textOnly="true"/>
						</th>
						<th>
							<pos:message key="OverUnderPayment" textOnly="true"/>
						</th>
						<th>
							<pos:message key="allocatedAmt" textOnly="true"/>
						</th>
						<th>
							<pos:message key="availableAmt" textOnly="true"/>
						</th>
						<th>
							&nbsp;
						</th>
						
				</tr>
					<logic:iterate indexId="count" id="element" name="<%=Constants.UNALLOCATED_PAYMENTS%>" type="org.posterita.beans.OpenItemBean">		
						<tr>
							<td>
						   		<html:link href="<%="ViewWebstorePaymentAction.do?action=viewOrder&documentId=" + element.getPaymentId() %>">
									<bean:write name="element" property="paymentId"/>
								</html:link>
	  					 	</td>
							
							<td>
								<bean:write name="element" property="trxDate"/>
							</td>
							<td>
								<bean:write name="element" property="tenderType"/>
								
							</td>
							<td>
								<bean:write name="element" property="paymentAmt"/>
							</td>
							<td>
								<bean:write name="element" property="overUnderPayment"/>
							</td>
							<td>
								<bean:write name="element" property="paymentAllocatedAmt"/>
							</td>
							<td>
								<bean:write name="element" property="availableAmt"/>
							</td>
							
						
							
							<td>
								<html:multibox property="paymentIds" value="<%=element.getPaymentId().toString()%>">
           						 </html:multibox>	
							</td>	
						</tr>	
					</logic:iterate>
				</table>
		</td>			
 </tr>					
 </logic:present>					
</html:form>
		<logic:present name="<%=Constants.AGING_ITEMS%>">
		<tr>
			<th align="left">
				<b><pos:message key="aging" textOnly="true"/></b>
			</th>
		</tr>
			<tr>
				<td>
					<table border="1" class="display">	
						
									<th>
										<pos:message key="InvoicedAmt" textOnly="true"/>
									</th>
									<th>
										<pos:message key="OpenAmt" textOnly="true"/>
									</th>
								
									<th>
										<pos:message key="PastDue91_Plus" textOnly="true"/>
									</th>
									
									<th>
										<pos:message key="PastDue61_90" textOnly="true"/>
									</th>
									
									<th>
										<pos:message key="PastDue31_60" textOnly="true"/>
									</th>
									
									<th>
										<pos:message key="PastDue1_30" textOnly="true"/>
									</th>
									
									<th>
										<pos:message key="PastDue8_30" textOnly="true"/>
									</th>
									
									<th>
										<pos:message key="PastDue1_7" textOnly="true"/>
									</th>
									
									<th>
										<pos:message key="PastDueAmt" textOnly="true"/>
									</th>
									
									
									
						<tr>
							<logic:iterate indexId="count" id="element" name="<%=Constants.AGING_ITEMS%>" type="org.posterita.beans.AgingBean">		
								<tr>
								
							    	<td>
										<bean:write name="element" property="invoicedAmt"/>
									</td>
									<td>
										<bean:write name="element" property="openAmt"/>
									</td>
								
									<td>
										<bean:write name="element" property="pastDue91_plus"/>
									</td>
									
									<td>
										<bean:write name="element" property="pastDue61_90"/>
									</td>
									
									<td>
										<bean:write name="element" property="pastDue31_60"/>
									</td>
									
									<td>
										<bean:write name="element" property="pastDue1_30"/>
									</td>
									
									<td>
										<bean:write name="element" property="pastDue8_30"/>
									</td>
									
									<td>
										<bean:write name="element" property="pastDue1_7"/>
									</td>
									
									<td>
										<bean:write name="element" property="pastDueAmt"/>
									</td>
									
									
							    	
								</tr>
							</logic:iterate>
						</tr>	
					</table>
				</td>
			</tr>	
		</logic:present>
		
		
		
		<logic:present name="<%=Constants.AGING_ITEMS%>">
			<tr>
				<td>
					<table border="1" class="display">	
						
									
									
									<th>
										<pos:message key="DueAmt" textOnly="true"/>
									</th>
									
									
									<th>
										<pos:message key="Due0" textOnly="true"/>
									</th>
									
									
									<th>
										<pos:message key="Due1_7" textOnly="true"/>
									</th>
									
									
									<th>
										<pos:message key="Due8_30" textOnly="true"/>
									</th>
									
									
									<th>
										<pos:message key="Due0_30" textOnly="true"/>
									</th>
									
									
									<th>
										<pos:message key="Due31_60" textOnly="true"/>
									</th>
									
									
									<th>
										<pos:message key="Due61_90" textOnly="true"/>
									</th>
									
									
									<th>
										<pos:message key="Due91_PLUS" textOnly="true"/>
									</th>
									
									
						<tr>
							<logic:iterate indexId="count" id="element" name="<%=Constants.AGING_ITEMS%>" type="org.posterita.beans.AgingBean">		
								<tr>
									
									<td>
										<bean:write name="element" property="dueAmt"/>
									</td>
									
									
									<td>
										<bean:write name="element" property="due0"/>
									</td>
									
									
									<td>
										<bean:write name="element" property="due1_7"/>
									</td>
									
									
									<td>
										<bean:write name="element" property="due8_30"/>
									</td>
									
									
									<td>
										<bean:write name="element" property="due0_30"/>
									</td>
									
									
									<td>
										<bean:write name="element" property="due31_60"/>
									</td>
									
									
									<td>
										<bean:write name="element" property="due61_90"/>
									</td>
									
									
									<td>
										<bean:write name="element" property="due91_PLUS"/>
									</td>
							    
							    	
								</tr>
							</logic:iterate>
						</tr>	
					</table>
				</td>
			</tr>	
		</logic:present>
		


<%@ include file="/jsp/include/errors.jsp" %> 
		
</table>
<%@ include file="/jsp/include/posFooter.jsp" %>
<script>
var chks = document.getElementsByName('invoiceIds');
var amts = document.getElementsByName('openAmt');
var payBtn = $FElement('paybtn');
var negativeCount = 0;


function init(){
	for(var i=0; i<amts.length; i++){	
		chks[i].index = i;			
		chks[i].onclick = function(){
			if(amts[this.index].value < 0){
				if(this.checked){
					negativeCount++;
				}
				else{
					negativeCount--;
				}//if
			}//if
			
			payBtn.disabled = (negativeCount != 0);			
		};
	}//for
	
	
	$('bpartnerId').onkeyup = function(e){
		if(e.keyCode == Event.KEY_RETURN)
		{
			document.forms[0].submit();
		}
	};

}

try{init();}catch(e){toConsole(e);};
</script>