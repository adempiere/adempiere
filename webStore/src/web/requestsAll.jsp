<%@ include file="/WEB-INF/jspf/page.jspf" %>
<c:if test='${empty webUser || !webUser.loggedIn}'>
  <c:redirect url='loginServlet?ForwardTo=requestAlls.jsp'/>
</c:if>
<!-- Set Request Type ID and get Request Type		-->
<c:if test='${empty param.R_RequestType_ID}'>
  <c:set target='${info}' property='message' value='RequestType not found' />
  <c:redirect url='requestTypes.jsp'/>
</c:if>
<c:set target='${info}' property='id' value='${param.R_RequestType_ID}' />
<c:set var='requestType' value='${info.requestType}' />
<c:if test='${empty requestType}'>
  <c:set target='${info}' property='message' value='RequestType not found' />
  <c:redirect url='requestTypes.jsp'/>
</c:if>
<html>
<!--
  - Author:  Jorg Janke
  - Version: $Id: requestsAll.jsp,v 1.2 2006/05/06 00:41:33 mdeaelfweald Exp $
  - Adempiere ERP & CRM Smart Business Solution - Copyright (c) 1999-2003 Jorg Janke
  - - -
  - Type all Requests (Summary)
  -->
<head>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<title>- Requests: <c:out value='${requestType.name}'/></title>
</head>
<body><div id="page">
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<div id="main">
	<%@ include file="/WEB-INF/jspf/menu.jspf" %>
    <%@ include file="/WEB-INF/jspf/vendor.jspf" %>
	<div id="content"> 
	<h1>Requests: <c:out value='${requestType.name}'/></h1>
	  <c:if test='${not empty info.info}'>
	    <p><b><c:out value='${info.message}'/></b></p>
	  </c:if>
	  <p><a href="requestTypes.jsp">Request Types</a> - <a href="request.jsp">New Request</a></p>
      <table class="contentTable">
        <tr> 
          <th>Document No</th>
          <th>Summary</th>
          <th>Status</th>
          <th>Assigned</th>
          <th>Created</th>
        </tr>
        <c:forEach items='${requestType.requests}' var='request' varStatus='status'>
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
          <td class="<c:out value='${rowClass}' />"><a href="requestDetails.jsp?R_Request_ID=<c:out value='${request.r_Request_ID}'/>"><c:out value='${request.documentNo}'/></a></td>
          <td class="<c:out value='${rowClass}' />"><c:out value='${request.summary}'/></td>
          <td class="<c:out value='${rowClass}' />"><c:out value='${request.statusName}'/></td>
          <td class="<c:out value='${rowClass}' />"><c:out value='${request.salesRepName}'/></td>
          <td class="<c:out value='${rowClass}' />"><fmt:formatDate value='${request.created}'/> <c:out value='${request.createdByName}'/></td>
        </tr>
        </c:forEach> 
      </table>
      <p>&nbsp;</p></div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div></body>
</html>
