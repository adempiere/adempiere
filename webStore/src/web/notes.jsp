<%@ include file="/WEB-INF/jspf/page.jspf" %>
<c:if test='${empty webUser || !webUser.loggedIn}'>
  <c:redirect url='loginServlet?ForwardTo=notes.jsp'/>
</c:if>
<html>
<head>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<title><c:out value='${ctx.name}'/> - My Notices</title>
</head>
<body><div id="page">
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<div id="main">
	<%@ include file="/WEB-INF/jspf/menu.jspf" %>
    <%@ include file="/WEB-INF/jspf/vendor.jspf" %>
	<div id="content">
      <h1>My Notices</h1>
      <c:if test='${not empty info.info}'>
	    <p><b><c:out value='${info.message}'/></b></p>
	  </c:if>
	  <h3>Workflow</h3>
      <table class="contentTable">
        <tr> 
          <th>Created<br><i>Priority</i></th>
          <th>Workflow Step<br><i>Description</i></th>
          <th>History</th>
          <th>Answer</th>
        </tr>
        <c:forEach items='${info.activities}' var='act' varStatus='actStatus'> 
        	<jsp:useBean id="actStatus" type="javax.servlet.jsp.jstl.core.LoopTagStatus" />
        	<c:choose>
        		<c:when test="<%= actStatus.getCount() %2 == 0 %>">
	        		<c:set var="rowClass" value="evenRow"/>
        		</c:when>
        		<c:otherwise>
	        		<c:set var="rowClass" value="oddRow"/>
        		</c:otherwise>
        	</c:choose>        
        <tr> 
		<form action="workflowServlet" method="post" enctype="application/x-www-form-urlencoded" name="Activity">
          <input name="AD_WF_Activity_ID" type="hidden" value="<c:out value='${act.AD_WF_Activity_ID}'/>"/>
          <td class="<c:out value='${rowClass}' />"><fmt:formatDate value='${act.created}'/><br><i><c:out value='${act.priority}'/></i></td>
          <td class="<c:out value='${rowClass}' />"><c:out value='${act.nodeName}'/><br>
          <i><c:out value='${act.nodeDescription}'/></i>
  		  <c:if test='${not empty act.attachment}'>
		    <c:out value='${act.attachment.textMsg}'/>:&nbsp;
		  	<c:forEach items='${act.attachment.entries}' var='entry'>
		  	  <a href="workflowServlet?AD_WF_Activity_ID=<c:out value='${act.AD_WF_Activity_ID}'/>&AttachmentIndex=<c:out value='${entry.index}'/>" target="_blank">
		  	  <c:out value='${entry.name}'/>
		  	  </a>&nbsp;-&nbsp;
			</c:forEach>
	  	  </c:if>
          </td>
          <td class="<c:out value='${rowClass}' />"><c:out value='${act.historyHTML}' escapeXml='false'/></td>
          <td class="<c:out value='${rowClass}' />"><textarea name="textMsg" cols="30" rows="3" id="textMsg"></textarea><br>
			<cws:workflow activityID="${act.AD_WF_Activity_ID}" />
            <input type="submit" name="Submit" value="Submit">
          </td>
		</form>
		</tr>
        </c:forEach> 
      </table>
	  <br>	  
	  <h3>Notices</h3>
      <table class="contentTable">
        <tr> 
          <th>Created</th>
          <th>Message</th>
          <th>Reference</th>
          <th>Description</th>
          <th>Text</th>
          <th>Answer</th>
        </tr>
        <c:forEach items='${info.notes}' var='note' varStatus='noteStatus'> 
        	<jsp:useBean id="noteStatus" type="javax.servlet.jsp.jstl.core.LoopTagStatus" />
        	<c:choose>
        		<c:when test="<%= noteStatus.getCount() %2 == 0 %>">
	        		<c:set var="rowClass" value="evenRow"/>
        		</c:when>
        		<c:otherwise>
	        		<c:set var="rowClass" value="oddRow"/>
        		</c:otherwise>
        	</c:choose>        
        <tr> 
 		<form action="noteServlet" method="post" enctype="application/x-www-form-urlencoded" name="Notice">
          <input name="AD_Note_ID" type="hidden" value="<c:out value='${note.AD_Note_ID}'/>"/>
          <td class="<c:out value='${rowClass}' />"><fmt:formatDate value='${note.created}'/></td>
          <td class="<c:out value='${rowClass}' />"><c:out value='${note.message}'/></td>
          <td class="<c:out value='${rowClass}' />"><c:out value='${note.reference}'/>&nbsp;
		  <c:if test='${not empty note.attachment}'>
		    <c:out value='${note.attachment.textMsg}'/>:&nbsp;
		  	<c:forEach items='${note.attachment.entries}' var='entry'>
		  	  <a href="noteServlet?AD_Note_ID=<c:out value='${note.AD_Note_ID}'/>&AttachmentIndex=<c:out value='${entry.index}'/>" target="_blank">
		  	  <c:out value='${entry.name}'/>
		  	  </a>&nbsp;-&nbsp;
			</c:forEach>
	  	  </c:if>
		  </td>
          <td class="<c:out value='${rowClass}' />"><c:out value='${note.textMsg}'/>&nbsp;</td>
          <td class="<c:out value='${rowClass}' />"><c:out value='${note.description}'/>&nbsp;</td>
          <td class="<c:out value='${rowClass}' />">
            <label>
            <input name="Processed" type="checkbox" id="Processed" value="Processed">
            Acknowledge</label>
            <input name="Update" type="submit" id="Update" value="Update">
		  </td>
		</form>
        </tr>
        </c:forEach> 
      </table>
    </div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div></body>
</html>
