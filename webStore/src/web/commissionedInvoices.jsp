<%@ include file="/WEB-INF/jspf/page.jspf" %>
<c:if test='${empty webUser || !webUser.loggedIn}'>
  <c:redirect url='loginServlet?ForwardTo=invoices.jsp'/>
</c:if>
<html>
<head>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<title><c:out value='${ctx.name}'/> - Commissioned Invoices</title>
</head>
<body><div id="page">
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<div id="main">
	<%@ include file="/WEB-INF/jspf/menu.jspf" %>
    <%@ include file="/WEB-INF/jspf/vendor.jspf" %>
	<div id="content">
	  <h1>Commissioned Invoices</h1>
	  <c:if test='${not empty info.info}'>
	  <p><b><c:out value='${info.message}'/></b></p>
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
        <c:forEach items='${info.commissionedInvoices}' var='invoice' varStatus='status'> 
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
          <td class="<c:out value='${rowClass}' />"><a href="invoiceLines.jsp?C_Invoice_ID=<c:out value='${invoice.c_Invoice_ID}'/>"><c:out value='${invoice.documentNo}'/></a></td>
          <td class="<c:out value='${rowClass}' />"><c:out value='${invoice.description}'/>&nbsp;</td>
          <td class="<c:out value='${rowClass}' />"><fmt:formatDate value='${invoice.dateInvoiced}'/></td>
          <td class="<c:out value='${rowClass}' /> amount"><fmt:formatNumber value='${invoice.totalLines}' type="currency" currencySymbol=""/></td>
          <td class="<c:out value='${rowClass}' /> amount"><c:out value='${invoice.currencyISO}'/>&nbsp;<fmt:formatNumber value='${invoice.grandTotal}' type="currency" currencySymbol=""/></td>
          <td class="<c:out value='${rowClass}' />"><a href="invoiceServlet/I_<c:out value='${invoice.documentNo}'/>.pdf?Invoice_ID=<c:out value='${invoice.c_Invoice_ID}'/>" target="_blank"><img src="pdf.gif" alt="Get Invoice Image" width="30" height="30" border="0" /></a></td>
          <td class="<c:out value='${rowClass}' />"><c:if test='${not invoice.paid}'><b>Not </b></c:if>Paid</td>
        </tr>
        </c:forEach> 
      </table>
      <p>&nbsp;</p>
      </div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div></body>
</html>
