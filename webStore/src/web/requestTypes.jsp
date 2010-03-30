<%@ include file="/WEB-INF/jspf/page.jspf" %>
<c:if test='${empty webUser || !webUser.loggedIn}'>
  <c:redirect url='loginServlet?ForwardTo=requestTypes.jsp'/>
</c:if>
<html>
<head>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<title>Request Types</title>
</head>
<body><div id="page">
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<div id="main">
	<%@ include file="/WEB-INF/jspf/menu.jspf" %>
    <%@ include file="/WEB-INF/jspf/vendor.jspf" %>
	<div id="content"> 
	<h1>Request Types</h1>
	  <c:if test='${not empty info.info}'>
	    <p><b><c:out value='${info.message}'/></b></p>
	  </c:if>
	  <p><a href="request.jsp">New Request</a></p>
      <table class="contentTable">
        <tr> 
          <th>Type</th>
          <th>Description</th>
          <th>Open</th>
          <th>Total</th>
          <th>New last 30 days</th>
          <th>Closed last 30 days</th>
        </tr>
        <c:forEach items='${info.requestTypes}' var='type' varStatus='status'>
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
          <td class="<c:out value='${rowClass}' />"><a href="requestsAll.jsp?R_RequestType_ID=<c:out value='${type.r_RequestType_ID}'/>">
		  	<c:out value='${type.name}'/></a></td>
          <td class="<c:out value='${rowClass}' />"><c:out value='${type.description}'/>&ndash;</td>
          <td class="<c:out value='${rowClass}' />"><c:out value='${type.openNo}'/></td>
          <td class="<c:out value='${rowClass}' /> amount"><c:out value='${type.totalNo}'/></td>
          <td class="<c:out value='${rowClass}' />"><c:out value='${type.closed30No}'/></td>
          <td class="<c:out value='${rowClass}' />"><c:out value='${type.new30No}'/></td>
        </tr>
        </c:forEach> 
      </table>
      <p>&nbsp;</p></div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div></body>
</html>
