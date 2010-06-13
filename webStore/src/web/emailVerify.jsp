<%@ include file="/WEB-INF/jspf/page.jspf" %>
<c:if test='${empty webUser || !webUser.loggedIn}'>
  <c:redirect url='loginServlet?ForwardTo=emailVerify.jsp'/>
</c:if>
<html>
<!--
  - Author:  Jorg Janke
  - Version: $Id: emailVerify.jsp,v 1.2 2006/05/06 00:41:33 mdeaelfweald Exp $
  - Adempiere ERP & CRM Smart Business Solution - Copyright (c) 1999-2003 Jorg Janke
  - - -
  - Web Store Assets
  -->
<head>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<title><c:out value='${ctx.name}'/> - Verify EMail</title>
</head>
<body><div id="page">
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<div id="main">
	<%@ include file="/WEB-INF/jspf/menu.jspf" %>
    <%@ include file="/WEB-INF/jspf/vendor.jspf" %>
	<div id="content">
	  <h1>Verify Your EMail Address</h1>
        <c:if test='${not empty param.Reason}'>
            <div class="error">
                <c:out value='${param.Reason}'/>
            </div>
        </c:if>
      <c:if test='${not empty info.info}'>
	    <p><b><c:out value='${info.message}'/></b></p>
	  </c:if>
	  <c:if test='${not webUser.EMailVerified}'>
        <form action="emailServlet" method="post" enctype="application/x-www-form-urlencoded" 
	  	  name="EMailVerification" target="_top">
          	<input name="AD_Client_ID" type="hidden" value='<c:out value="${initParam.#AD_Client_ID}" default="0"/>'/>
          	<input name="Source" type="hidden" value=""/>
          	<input name="Info" type="hidden" value=""/>
            <c:choose>
                <c:when test="${empty param.ForwardTo}">
                    <input name="ForwardTo" type="hidden" value="emailVerify.jsp">
                </c:when>
                <c:otherwise>
                    <input name="ForwardTo" type="hidden" value="${param.ForwardTo}">
                </c:otherwise>
            </c:choose>
         	<script language="Javascript">
				document.EMailVerification.Source.value=document.referrer;
				document.EMailVerification.Info.value=document.lastModified;
  			</script>
			<p>Enter Verification Code
      	      <input name="VerifyCode" type="text" id="VerifyCode" value="<Check EMail>">
              <input type="submit" name="Submit" value="Submit">
			</p>
			<p>The Verification Code will be sent to <b><c:out value='${webUser.email}'/></b> &nbsp;
              <input type="submit" name="ReSend" id="ReSend" value="Send Verification Code">
			</p>
			<p><c:out value="${webUser.passwordMessage}"/></p>
	    </form>
	  </c:if>
	  <c:if test='${webUser.EMailVerified}'>
	    <p>
	  	Thank you - your email address <b><c:out value='${webUser.email}'/></b> was verified.
		</p>
	  </c:if>
      <c:if test='${webUser.creditCritical}'>
	      <p><strong><font color="#990000">! <c:out value='${webUser.SOCreditStatus}'/> !</font></strong></p>
	  </c:if>
    </div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div></body>
</html>
