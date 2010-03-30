<%@ include file="/WEB-INF/jspf/page.jspf" %>
<c:if test='${empty webUser || !webUser.loggedIn}'>
  <c:redirect url='loginServlet?ForwardTo=requests.jsp'/>
</c:if>
<html>
<head>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<title><c:out value='${ctx.name}'/> - My Requests</title>
</head>
<body><div id="page">
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<div id="main">
	<%@ include file="/WEB-INF/jspf/menu.jspf" %>
    <%@ include file="/WEB-INF/jspf/vendor.jspf" %>
	<div id="content"> 
	<h1>My Requests</h1>
	  <c:if test='${not empty info.info}'>
	    <p><b><c:out value='${info.message}'/></b></p>
	  </c:if>
	  <p><a href="request.jsp">New Request</a></p>
	  
	  <form action="javascript:void(0)" method="post" enctype="application/x-www-form-urlencoded" name="search" id="search">
		<fieldset>
			<legend>Search</legend>
			
			<table class="internalTable">
				<tr>
					<td class="lineItem">
		                <label for="RequestType_ID">Request Type</label>
						<select name="RequestType_ID" id="ID_RequestType_ID">
							<option value="0" selected="selected">ANY</option>
							<option value="100">Request for Quotation</option>
							<option value="101">Service Request</option>
							<option value="102">Warranty</option>
						</select>
						<br/>

		                <label for="RefOrder_ID">Order Reference</label>
		                <cws:requestOrder bpartnerID='${webUser.bpartnerID}'/>
						<br/>

		                <label>Text in Summary</label>
		                <input type="text" name="Summary" id="ID_Summary"/>
					</td>
					<td class="lineItem">
						<label>Confidential</label>
						<select>
							<option>ANY</option>
							<option>Confidential Only</option>
							<option>Non-Confidential Only</option>
						</select>
						<br/>

		                <label>Importance</label>
						<select>
							<option>ANY</option>
							<option>Low</option>
							<option>Medium</option>
							<option>High</option>
						</select>
						<br/>

		                <label>Priority</label>
						<select>
							<option>ANY</option>
							<option>Low</option>
							<option>Medium</option>
							<option>High</option>
						</select>
						<br/>

		                <label>Status</label>
						<select>
							<option>ANY</option>
							<option>Open</option>
							<option>Closed</option>
							<option>Pending</option>
						</select>
					</td>
					<td class="lineItem">
						<label>Attachments</label>
						<select>
							<option>ANY</option>
							<option>Has Attachments Only</option>
							<option>No Attachments Only</option>
						</select>
						<br/>

						<label>Start Date</label>
						<input type="text" size="8"/>
						<img src="Calendar16.gif" border="0"/>
						<br/>
									
						<label>End Date</label>
						<input type="text" size="8"/>
						<img src="Calendar16.gif" border="0"/>
						<br/>

						<label>Assigned</label>
						<select>
							<option>ANY</option>
							<option>GardenAdmin</option>
							<option>GardenUser</option>
						</select>

					</td>
				</tr>
			</table>
			
			<div class="buttons">
				<input type="button" name="Search" value="Search Requests"/>
				<input type="button" name="Reset" value="Reset"/>
			</div>
		</fieldset>
      </form>
      <br/>
	  
      <table class="contentTable">
        <tr> 
          <th>Document No</th>
          <th>Summary</th>
          <th>Status</th>
          <th>Assigned</th>
          <th>Created</th>
        </tr>
        <c:forEach items='${info.requestsOwn}' var='request' varStatus='status'>
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
