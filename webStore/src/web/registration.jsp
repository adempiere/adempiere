<%@ include file="/WEB-INF/jspf/page.jspf" %>
<%@ page language="java" import="java.sql.*" %>
<c:if test='${empty webUser || !webUser.loggedIn}'>
  <c:redirect url='loginServlet?ForwardTo=registrations.jsp'/>
</c:if>
<html>
<head>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<title><c:out value='${ctx.name}'/> - Registration</title>
</head>
<body><div id="page">
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<!-- Set Registration ID and get Registration (may not exist) -->
<c:set target='${info}' property='id' value='0' />
<c:if test='${not empty param.A_Registration_ID}'>
  <c:set target='${info}' property='id' value='${param.A_Registration_ID}' />
</c:if>  
<c:set var='registration' value='${info.registration}' />
<c:if test='${empty registration}'>
  <c:set target='${info}' property='message' value='Registration not found' />
  <c:redirect url='registrations.jsp'/>
</c:if>

<div id="main">
	<%@ include file="/WEB-INF/jspf/menu.jspf" %>
    <%@ include file="/WEB-INF/jspf/vendor.jspf" %>
	<div id="content">
      <h1>Registration</h1>
      <c:if test='${not empty info.info}'>
	    <p><b><c:out value='${info.message}'/></b></p>
	  </c:if>
      <form action="registrationServlet" method="post" enctype="application/x-www-form-urlencoded" name="registration" id="registration">
          <fieldset>
              <legend>New Registration</legend>
              
              <label for="Name">Name</label>
              <input name="Name" type="text" id="Name" value="<c:out value='${registration.name}'/>" size="60" maxlength="60" />
              <br/>

              <label for="Description">Description</label>
              <input name="Description" type="text" id="Description" value="<c:out value='${registration.description}'/>" size="60" maxlength="255" />
              <br/>

              <label for="AssetServiceDate">Service Date</label>
              <input name="AssetServiceDate" type="text" id="AssetServiceDate" value="<fmt:formatDate value='${registration.assetServiceDate}'/>" size="20" />
              <br/>

              <label for="InProduction">In Production</label>
              <input <c:if test='${registration.inProduction}'>checked</c:if> name="InProduction" type="checkbox" id="InProduction" value="IsInProduction" />
              <br/>

              <label for="AllowPublish">Allow Publication</label>
              <input <c:if test='${registration.allowPublish}'>checked</c:if> name="AllowPublish" type="checkbox" id="AllowPublish" value="IsAllowPublish" />
              <br/>

                <c:forEach items='${registration.values}' var='rvalue'>
                    <label for="<c:out value='${rvalue.registrationAttribute}'/>"><c:out value='${rvalue.registrationAttributeDescription}'/></label>
                    <input name="<c:out value='${rvalue.registrationAttribute}'/>" type="text" id="<c:out value='${rvalue.registrationAttribute}'/>" value="<c:out value='${rvalue.name}'/>" size="30" maxlength="60" />
                    <br/>
                </c:forEach>

            <div class="buttons">
                <input name="A_Registration_ID" type="hidden" id="A_Registration_ID" value="<c:out value='${registration.a_Registration_ID}'/>">
                <input type="submit" name="Submit" value="Submit">
                <input name="Reset" type="reset" id="Reset" value="Reset">
            </div>
          </fieldset>
      </form>
    </div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div></body>
</html>
