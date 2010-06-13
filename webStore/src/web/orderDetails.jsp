<%@ include file="/WEB-INF/jspf/page.jspf" %>
<c:if test='${empty webUser || !webUser.loggedIn}'>
  <c:redirect url='loginServlet?ForwardTo=orders.jsp'/>
</c:if>
<html>
<!--
  - Author:  Jorg Janke
  - Version: $Id: orderDetails.jsp,v 1.2 2006/05/06 00:41:33 mdeaelfweald Exp $
  - Adempiere ERP & CRM Smart Business Solution - Copyright (c) 1999-2003 Jorg Janke
  - - -
  - Web Store Orders
  -->
<head>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<title><c:out value='${ctx.name}'/> - My Order Details</title>
</head>
<body><div id="page">
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<!-- Set Order ID and get Invoice		-->
<c:set target='${info}' property='id' value='${param.C_Order_ID}' />
<c:set var='order' value='${info.order}' />
<c:if test='${empty order}'>
  <c:set target='${info}' property='message' value='Order not found' />
  <c:redirect url='orders.jsp'/>
</c:if>

<div id="main">
	<%@ include file="/WEB-INF/jspf/menu.jspf" %>
    <%@ include file="/WEB-INF/jspf/vendor.jspf" %>
	<div id="content"> 
	<h1>My Order Details <c:out value='${order.documentNo}'/></h1>
	  <c:if test='${not empty info.info}'>
	    <p><b><c:out value='${info.message}'/></b></p>
	  </c:if>
      <table class="contentTable">
        <tr> 
          <th>Document No</th>
          <th>Description</th>
          <th>Status</th>
          <th>Date</th>
          <th>Total Lines</th>
          <th>Grand Total</th>
          <th>&nbsp;</th>
        </tr>
        <tr> 
          <td class="oddRow"><c:out value='${order.documentNo}'/></td>
          <td class="oddRow"><c:out value='${order.description}'/>&nbsp;</td>
          <td class="oddRow"><c:out value='${order.docStatusName}'/></td>
          <td class="oddRow"><fmt:formatDate value='${order.dateOrdered}'/></td>
          <td class="oddRow amount"><fmt:formatNumber value='${order.totalLines}' type="currency" currencySymbol=""/></td>
          <td class="oddRow amount"><c:out value='${order.currencyISO}'/>&nbsp;<fmt:formatNumber value='${order.grandTotal}' type="currency" currencySymbol=""/></td>
		  <td class="oddRow"> 
			<c:if test='${order.docStatus=="IP"}'>
			  <input name="Void" id="Void" value="Void" 
	    	    onClick="window.top.location.replace('./orderServlet?C_Order_ID=<c:out value='${order.c_Order_ID}'/>&DocAction=VO');" type="button">
			  <input name="Complere" id="Complete" value="Complete" 
	    	    onClick="window.top.location.replace('./orderServlet?C_Order_ID=<c:out value='${order.c_Order_ID}'/>&DocAction=CO');" type="button">
			</c:if>
			<c:if test='${order.docStatus=="WP"}'>
              <input type="submit" name="OrderPay" value="Pay <c:out value='${order.grandTotal}'/>" 
			    onClick="window.top.location.replace('paymentServlet?C_Invoice_ID=<c:out value='${order.c_Invoice_ID}'/>&Amt=<c:out value='${order.grandTotal}'/>');">
			</c:if>&nbsp;
          </td>
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
        <c:forEach items='${order.lines}' var='line' varStatus='status'> 
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
          <td class="<c:out value='${rowClass}' /> quantity"><fmt:formatNumber value='${line.qtyOrdered}' /></td>
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
