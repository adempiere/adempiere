<%@ include file="/WEB-INF/jspf/page.jspf" %>
<c:if test='${empty webUser || !webUser.loggedIn}'>
  <c:redirect url='loginServlet?ForwardTo=invoices.jsp'/>
</c:if>
<html>
<!--
  - Author:  Jorg Janke
  - Version: $Id: invoiceLines.jsp,v 1.2 2006/05/06 00:41:33 mdeaelfweald Exp $
  - Adempiere ERP & CRM Smart Business Solution - Copyright (c) 1999-2003 Jorg Janke
  - - -
  - Web Store Invoices
  -->
<head>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<title><c:out value='${ctx.name}'/> - My Invoice Details</title>
</head>
<body><div id="page">
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<!-- Set Invoice ID and get Invoice		-->
<c:set target='${info}' property='id' value='${param.C_Invoice_ID}' />
<c:set var='invoice' value='${info.invoice}' />
<c:if test='${empty invoice}'>
  <c:set target='${info}' property='message' value='Invoice not found' />
  <c:redirect url='invoices.jsp'/>
</c:if>

<div id="main">
	<%@ include file="/WEB-INF/jspf/menu.jspf" %>
    <%@ include file="/WEB-INF/jspf/vendor.jspf" %>
	<div id="content"> 
	  <h1>My Invoice Details <c:out value='${invoice.documentNo}'/></h1>
	  <c:if test='${not empty info.info}'>
	    <p><c:out value='${info.message}'/></p>
	  </c:if>
      <table class="contentTable">
        <tr> 
          <th>Document No</th>
          <th>Description</th>
          <th>Date</th>
          <th>Total Lines</th>
          <th>Grand Total</th>
          <th>Image</th>
          <th>Open</th>
        </tr>
        <tr> 
          <td class="oddRow"><c:out value='${invoice.documentNo}'/></td>
          <td class="oddRow"><c:out value='${invoice.description}'/>&nbsp;</td>
          <td class="oddRow"><fmt:formatDate value='${invoice.dateInvoiced}'/></td>
          <td class="oddRow amount"><fmt:formatNumber value='${invoice.totalLines}' type="currency" currencySymbol=""/></td>
          <td class="oddRow amount"><c:out value='${invoice.currencyISO}'/>&nbsp;<fmt:formatNumber value='${invoice.grandTotal}' type="currency" currencySymbol=""/></td>
          <td class="oddRow"><a href="invoiceServlet/I_<c:out value='${invoice.documentNo}'/>.pdf?Invoice_ID=<c:out value='${invoice.c_Invoice_ID}'/>" target="_blank"><img src="pdf.gif" alt="Get Invoice Image" width="30" height="30" border="0" /></a></td>
          <td class="oddRow"><c:if test='${invoice.paid}'>Paid</c:if><c:if test='${not invoice.paid}'> 
            <input type="submit" name="InvoicePay" value="Pay <c:out value='${invoice.openAmt}'/>" 
			  onClick="window.top.location.replace('paymentServlet?C_Invoice_ID=<c:out value='${invoice.c_Invoice_ID}'/>&Amt=<c:out value='${invoice.openAmt}'/>');">
            </c:if></td>
        </tr>
      </table>
	  <h3>Lines</h3>
      <table class="contentTable">
        <tr> 
          <th>Line</th>
          <th>Name</th>
          <th>Description</th>
          <th>Qty</th>
          <th>Price</th>
          <th>Line Net</th>
        </tr>
        <c:forEach items='${invoice.lines}' var='line' varStatus='status'> 
            <jsp:useBean id="status" type="javax.servlet.jsp.jstl.core.LoopTagStatus" />
        	<c:choose>
        		<c:when test="<%= status.getCount() %2 == 0 %>">
	        		<c:set var="rowClass" value="evenRow"/>
        		</c:when>
        		<c:otherwise>
	        		<c:set var="rowClass" value="oddRow"/>
        		</c:otherwise>
        	</c:choose>
        	 
        <tr> 
          <td class="<c:out value='${rowClass}' />"><c:out value='${line.line}'/></td>
          <td class="<c:out value='${rowClass}' />"><c:out value='${line.name}'/>&nbsp;</td>
          <td class="<c:out value='${rowClass}' />"><c:out value='${line.descriptionText}'/>&nbsp;</td>
          <td class="<c:out value='${rowClass}' /> quantity"><fmt:formatNumber value='${line.qtyInvoiced}' /></td>
          <td class="<c:out value='${rowClass}' /> amount"><fmt:formatNumber value='${line.priceActual}' type="currency" currencySymbol=""/></td>
          <td class="<c:out value='${rowClass}' /> amount"><fmt:formatNumber value='${line.lineNetAmt}' type="currency" currencySymbol=""/></td>
        </tr>
        </c:forEach> 
      </table>
      <p>&nbsp;</p></div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div></body>
</html>
