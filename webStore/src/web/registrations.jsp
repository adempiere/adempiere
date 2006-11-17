<%@ include file="/WEB-INF/jspf/page.jspf" %>
<c:if test='${empty webUser || !webUser.loggedIn}'>
  <c:redirect url='loginServlet?ForwardTo=registrations.jsp'/>
</c:if>
<html>
<!--
  - Author:  Jorg Janke
  - Version: $Id: registrations.jsp,v 1.2 2006/05/06 00:41:33 mdeaelfweald Exp $
  - Adempiere ERP & CRM Smart Business Solution - Copyright (c) 1999-2003 Jorg Janke
  - - -
  - Web Store Registration
  -->
<head>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<title><c:out value='${ctx.name}'/> - My Registrations</title>
</head>
<body><div id="page">
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<div id="main">
	<%@ include file="/WEB-INF/jspf/menu.jspf" %>
    <%@ include file="/WEB-INF/jspf/vendor.jspf" %>
	<div id="content">
      <h1>My Registrations</h1>
      <c:if test='${not empty info.info}'>
	    <p><b><c:out value='${info.message}'/></b></p>
	  </c:if>
	  <p><a href="registration.jsp">New Registration</a></p>
      <table class="contentTable">
        <tr> 
          <th>Name</th>
          <th>Description</th>
          <th>Service Date</th>
          <th>In Production</th>
          <th>Allow Publication</th>
        </tr>
        <c:forEach items='${info.registrations}' var='registration' varStatus='status'>
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
          <td class="<c:out value='${rowClass}' />"><a href="registration.jsp?A_Registration_ID=<c:out value='${registration.a_Registration_ID}'/>"><c:out value='${registration.name}'/></a></td>
          <td class="<c:out value='${rowClass}' />"><c:out value='${registration.description}'/>&nbsp;</td>
          <td class="<c:out value='${rowClass}' />"><fmt:formatDate value='${registration.assetServiceDate}'/></td>
          <td class="<c:out value='${rowClass}' />"><c:out value='${registration.inProduction}'/></td>
          <td class="<c:out value='${rowClass}' />"><c:out value='${registration.allowPublish}'/></td>
        </tr>
        </c:forEach> 
      </table>
    </div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div></body>
</html>
