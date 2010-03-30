<%@ include file="/WEB-INF/jspf/page.jspf" %>
<c:if test='${empty webUser || !webUser.loggedIn}'>
  <c:redirect url='loginServlet?ForwardTo=assets.jsp'/>
</c:if>
<%--<c:if test='${not webUser.EMailVerified}'>--%>
    <%--<c:redirect url="emailVerify.jsp">--%>
        <%--<c:param name="ForwardTo" value="assets.jsp"/>--%>
        <%--<c:param name="Reason" value="'My Assets' can not be accessed until email verification has been completed."/>--%>
    <%--</c:redirect>--%>
    <%--<c:redirect url='emailVerify.jsp?ForwardTo=assets.jsp'/>--%>
<%--</c:if>--%>
<html>
<head>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<title><c:out value='${ctx.name}'/> - My Assets</title>
</head>
<body><div id="page">
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<div id="main">
	<%@ include file="/WEB-INF/jspf/menu.jspf" %>
    <%@ include file="/WEB-INF/jspf/vendor.jspf" %>
	<div id="content">
	  <h1>My Assets</h1>
      <c:if test='${not empty info.info}'>
	    <p><b><c:out value='${info.message}'/></b></p>
	  </c:if>
	  <c:if test='${not webUser.EMailVerified}'>
        <form action="emailServlet" method="post" enctype="application/x-www-form-urlencoded"
	  	  name="EMailVerification" target="_top">
          	<input name="AD_Client_ID" type="hidden" value='<c:out value="${initParam.#AD_Client_ID}" default="0"/>'/>
          	<input name="Source" type="hidden" value=""/>
          	<input name="Info" type="hidden" value=""/>
            <input name="ForwardTo" type="hidden" value="assets.jsp">
            <script language="Javascript">
				document.EMailVerification.Source.value=document.referrer;
				document.EMailVerification.Info.value=document.lastModified;
  			</script>
			<p><b>To access your Assets:</b></p>
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
      <table class="contentTable">
        <tr> 
            <th>Name</th>
            <th>Description</th>
            <th class="nobr">Guarantee Date</th>
            <th>Quantity</th>
            <th class="nobr">Your Version</th>
            <th class="nobr">Serial #</th>
            <th>Delivery</th>
            <th>Download</th>
        </tr>
        <c:forEach items='${info.assets}' var='asset' varStatus='status'> 
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
          <td class="<c:out value='${rowClass}' /> quantity"><c:out value='${asset.qty}'/></td>
          <td class="<c:out value='${rowClass}' /> numeric"><c:out value='${asset.versionNo}'/></td>
          <td class="<c:out value='${rowClass}' /> numeric"><c:out value='${asset.serNo}'/></td>
          <td class="<c:out value='${rowClass}' />">
            <c:forEach items='${asset.downloadNames}' var='addlDL'>
                <c:out value='${addlDL}'/><br>
            </c:forEach>&nbsp;
		  </td>
          <td class="<c:out value='${rowClass}' />">
		    <c:if test='${asset.downloadable}'>
		  	  <c:forEach items='${asset.downloadURLs}' var='addlDL'>
		    	<a href="<c:out value='http://${ctx.context}/'/>assetServlet/<c:out value='${addlDL}'/>.zip?Asset_ID=<c:out value='${asset.a_Asset_ID}'/>&PD=<c:out value='${addlDL}'/>" target="_blank">
		  	  	<img src="assetDownload.gif" alt="Download <c:out value='${addlDL}'/>" width="24" height="24" border="0" /></a><br>
			  </c:forEach>
			</c:if>
		    <c:if test='${not asset.downloadable}'>
		  	  n/a
			</c:if>
			&nbsp;
		  </td>
        </tr>
        </c:forEach> 
      </table>
      <c:if test='${webUser.creditCritical}'>
	      <p><strong><font color="#990000">! <c:out value='${webUser.SOCreditStatus}'/> !</font></strong></p>
	  </c:if>
      <p>To download, click on the link - or right-click and &quot;Save Target As
        ...&quot;</p>
    </div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div></body>
</html>
