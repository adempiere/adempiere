<%@ include file="/WEB-INF/jspf/page.jspf" %>
<c:if test='${empty webUser || !webUser.loggedIn}'>
  <c:redirect url='loginServlet?ForwardTo=rfqs.jsp'/>
</c:if>
<html>
<!--
  - Author:  Jorg Janke
  - Version: $Id: rfqs.jsp,v 1.2 2006/05/06 00:41:33 mdeaelfweald Exp $
  - Adempiere ERP & CRM Smart Business Solution - Copyright (c) 1999-2004 Jorg Janke
  - - -
  - Web Store RfQs
  -->
<head>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<title><c:out value='${ctx.name}'/> - My RfQs</title>
</head>
<body><div id="page">
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<div id="main">
	<%@ include file="/WEB-INF/jspf/menu.jspf" %>
    <%@ include file="/WEB-INF/jspf/vendor.jspf" %>
	<div id="content"> 
	<h1>My RfQ's</h1>
	  <c:if test='${not empty info.info}'>
	    <p><b><c:out value='${info.message}'/></b></p>
	  </c:if>
      <table class="contentTable">
        <tr> 
          <th>Name</th>
          <th>Desciption</th>
          <th>Details</th>
          <th>Response by</th>
          <th>Work Start</th>
          <th>Delivery</th>
        </tr>
        <c:forEach items='${info.rfQs}' var='rfq' varStatus='status'>
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
          <td class="<c:out value='${rowClass}' />"><a href="rfqDetails.jsp?C_RfQ_ID=<c:out value='${rfq.c_RfQ_ID}'/>"><c:out value='${rfq.name}'/></a></td>
          <td class="<c:out value='${rowClass}' />"><c:out value='${rfq.description}'/>&nbsp;</td>
          <td class="<c:out value='${rowClass}' />"><c:out value='${rfq.help}'/>&nbsp;</td>
          <td class="<c:out value='${rowClass}' />"><fmt:formatDate value='${rfq.dateResponse}'/></td>
          <td class="<c:out value='${rowClass}' />"><fmt:formatDate value='${rfq.dateWorkStart}'/></td>
          <td class="<c:out value='${rowClass}' />"><fmt:formatDate value='${rfq.dateWorkComplete}'/> - <c:out value='${rfq.deliveryDays}'/> days</td>
        </tr>
        </c:forEach> 
      </table>
      <p>&nbsp;</p></div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div></body>
</html>
