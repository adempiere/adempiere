<%@ include file="/WEB-INF/jspf/page.jspf" %>
<c:if test='${empty webUser || !webUser.loggedIn}'>
  <c:redirect url='loginServlet?ForwardTo=request.jsp&SalesRep_ID=${param.SalesRep_ID}'/> 
</c:if> 
<html>
<head>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<title><c:out value='${ctx.name}'/> - Request</title>
</head>
<body><div id="page">
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<div id="main">
	<%@ include file="/WEB-INF/jspf/menu.jspf" %>
    <%@ include file="/WEB-INF/jspf/vendor.jspf" %>
	<div id="content">
	<h1>New Request</h1>
      <form method="post" name="Request" action="requestServlet" enctype="application/x-www-form-urlencoded"
	  	onSubmit="checkForm(this, new Array ('Summary'));">

            <input name="Source" type="hidden" value=""/>
            <input name="Info" type="hidden" value=""/>
            <script language="Javascript">
              document.Request.Source.value=document.referrer;
              document.Request.Info.value=document.lastModified;
            </script>
            <input name="ForwardTo" type="hidden" value="<c:out value='${param.ForwardTo}'/>"/>
            <c:if test='${not empty param.SalesRep_ID}'>
              <input name="SalesRep_ID" type="hidden" value="<c:out value='${param.SalesRep_ID}'/>"/>
            </c:if>
            <c:if test='${empty param.SalesRep_ID}'>
              <input name="SalesRep_ID" type="hidden" value="<c:out value='${webUser.salesRep_ID}'/>"/>
            </c:if>

              <label>From </label>
              <c:out value='${webUser.name}'/> / <c:out value='${webUser.email}'/>
              <br/>

                <label id="ID_RequestType" for="RequestType">Request Type</label>
                <cws:requestType/>
              <br/>

                <label id="ID_RequestType" for="RequestType">Optional Order Reference</label>
                <cws:requestOrder bpartnerID='${webUser.bpartnerID}'/>
              <br/>

              <!--<label id="ID_Summary" for="Summary">Question - Issue - Request:</label>-->
              <label>&nbsp;</label>
              <input name="Confidential" type="checkbox" id="Confidential" value="Confidential"> Confidential Information
              <br/>

            <fieldset>
                <legend>Summary</legend>
                <textarea name="Summary" cols="80" rows="8" id="ID_Summary" class="wideText"></textarea>
                <div class="entryNote">
                    Summary: 1500 characters max
                    <br/>
                    Attachments: Click on the document number after submitting.
                </div>
            </fieldset>
            <div class="buttons">
                <input name="Reset" type="reset" value="Reset"/>
                <input name="Submit" type="submit" value="Submit">
            </div>

      </form>
      <p>&nbsp;</p></div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div></body>
</html>
