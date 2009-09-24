<%@ include file="/WEB-INF/jspf/page.jspf" %>
<c:if test='${empty webUser || !webUser.loggedIn}'>
  <c:redirect url='loginServlet'/>
</c:if>
<html>
<!--
  - Author:  Jorg Janke
  - Version: $Id: confirm.jsp,v 1.2 2006/05/06 00:41:33 mdeaelfweald Exp $
  - Adempiere ERP & CRM Smart Business Solution - Copyright (c) 1999-2003 Jorg Janke
  - - -
  - Confirmation 
  - webOrder, webUser, payment
  -->
<head>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<title><c:out value='${ctx.name}'/> - Payment Confirmation</title>
</head>
<body><div id="page">
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<div id="main">
	<%@ include file="/WEB-INF/jspf/menu.jspf" %>
    <%@ include file="/WEB-INF/jspf/vendor.jspf" %>
	<div id="content">
	<h1>Thanks - Payment Confirmation</h1>
      <table class="contentTable">
        <tr> 
          <th>Payment</th>
          <th>Invoice</th>
          <th>Details</th>
          <th>Total</th>
        </tr>
        <tr> 
          <td class="oddRow"><c:out value='${payment.documentNo}'/></td>
          <td class="oddRow">
		    <c:choose>
		    <c:when test='${not empty webOrder}'>
			  <a href="invoiceServlet/I_<c:out value='${webOrder.invoiceInfo}'/>.pdf?Invoice_ID=<c:out value='${webOrder.invoice_ID}'/>" target="_blank"><img src="pdf.gif" alt="Get Invoice PDF" width="30" height="30" border="0" align="right" /></a>
			  <c:out value='${webOrder.invoiceInfo}'/>
			</c:when>  
		    <c:otherwise>
			  &nbsp;
			</c:otherwise>  
			</c:choose>
		  </td>
          <td class="oddRow"><c:out value='${payment.r_AuthCode}'/></td>
          <td class="oddRow amount"><c:out value='${payment.currencyISO}'/>&nbsp;<fmt:formatNumber value='${payment.payAmt}' type="currency" currencySymbol=""/></td>
        </tr>
      </table>
      <form action="assets.jsp" method="post" enctype="application/x-www-form-urlencoded" name="confirm" id="confirm">
        For downloads,  please check &nbsp; 
        <input type="submit" name="Submit" value="My Assets">
        &nbsp; for documentation and  other information. 
      </form>
	  <!-- Remove Info			-->
	  <c:remove var='payment' />
	  <c:remove var='webOrder' />
	  
	  <br/><br/>
	  
      <table class="contentTable">
        <tr> 
          <th>Interest Area</th>
          <th>Description</th>
          <th align="right">Subscription</th>
        </tr>
        <c:forEach items='${info.interests}' var='interest' varStatus='status'> 
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
          <td class="<c:out value='${rowClass}' />"><c:out value='${interest.name}'/></td>
          <td class="<c:out value='${rowClass}' />"><c:out value='${interest.description}'/>&nbsp;</td>
          <td class="<c:out value='${rowClass}' />">
		    <c:choose>
		    <c:when test='${interest.subscribed}'>
			<fmt:formatDate value='${interest.subscribeDate}'/>&nbsp;
			<input type="button" name="UnSubscribe_<c:out value='${interest.r_InterestArea_ID}'/>" value="Un-Subscribe" 
		      onClick="window.top.location.replace('infoServlet?mode=unsubscribe&area=<c:out value='${interest.r_InterestArea_ID}'/>&contact=<c:out value='${info.user_ID}'/>');" >
			</c:when>
			<c:otherwise>  
			<input type="button" name="Subscribe_<c:out value='${interest.r_InterestArea_ID}'/>" value="Subscribe" 
		      onClick="window.top.location.replace('infoServlet?mode=subscribe&area=<c:out value='${interest.r_InterestArea_ID}'/>&contact=<c:out value='${info.user_ID}'/>');" >
			</c:otherwise>
			</c:choose>  
		  </td>
        </tr>
        </c:forEach> 
      </table>
      <p>&nbsp;</p></div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div></body>
</html>
