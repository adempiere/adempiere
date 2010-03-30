<%@ include file="/WEB-INF/jspf/page.jspf" %>
<c:if test='${empty webUser || !webUser.loggedIn}'>
  <c:redirect url='loginServlet?ForwardTo=template.jsp'/>
</c:if>
<html>
<head>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<title><c:out value='${ctx.name}'/> - My Template</title>
</head>
<body><div id="page">
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<div id="main">
	<%@ include file="/WEB-INF/jspf/menu.jspf" %>
    <%@ include file="/WEB-INF/jspf/vendor.jspf" %>
	<div id="content">
      <h1>My Template </h1>
      <c:if test='${not empty info.info}'>
	    <p><b><c:out value='${info.message}'/></b></p>
	  </c:if>
      <table class="contentTable">
        <tr> 
          <th>Name</th>
          <th>Description</th>
          <th>Date</th>
        </tr>
        <c:forEach items='${info.template}' var='asset' varStatus='status'>
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
          <td class="<c:out value='${rowClass}' />"><c:out value='${asset.name}'/></td>
          <td class="<c:out value='${rowClass}' />"><c:out value='${asset.description}'/>&nbsp;</td>
          <td class="<c:out value='${rowClass}' />"><fmt:formatDate value='${asset.guaranteeDate}'/></td>
        </tr>
        </c:forEach> 
      </table>
	  <p>&nbsp;</p>
    </div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div></body>
</html>
