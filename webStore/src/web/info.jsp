<%@ include file="/WEB-INF/jspf/page.jspf" %>
<c:if test='${empty webUser || !webUser.loggedIn}'>
  <c:redirect url='loginServlet?ForwardTo=info.jsp'/>
</c:if>
<html>
<head>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<title><c:out value='${ctx.name}'/> - My Interest Areas</title>
</head>
<body><div id="page">
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<div id="main">
	<%@ include file="/WEB-INF/jspf/menu.jspf" %>
    <%@ include file="/WEB-INF/jspf/vendor.jspf" %>
	<div id="content"> 
	  <h1>Info - Interest Areas</h1>
	  <c:if test='${not empty info.info}'>
	    <p><b><c:out value='${info.message}'/></b></p>
	  </c:if>
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
