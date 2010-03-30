<%@ include file="/WEB-INF/jspf/page.jspf" %>
<html>
<head>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<title><c:out value='${ctx.name}'/> - All Ads</title>
</head>
<body><div id="page">
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<div id="main">
	<%@ include file="/WEB-INF/jspf/menu.jspf" %>
    <%@ include file="/WEB-INF/jspf/vendor.jspf" %>
	<div id="content"> 
      <h1>Partner Info</h1>
	  <c:if test='${not empty info.info}'>
	    <p><b><c:out value='${info.message}'/></b></p>
	  </c:if>
	  <p>Please check with our partners:</p>
	  <!-- Start Copy HERE		-->
	  <p>
        <c:forEach items='${info.allAds}' var='ad'>
		  <c:out value='${ad.description}'/>: 
		  <a href="#<c:out value='${ad.salesRep_ID}'/>">
		    <c:out value='${ad.name}'/>
	  	  </a>
		  <br>
        </c:forEach> 
	  </p>
	  <p>&nbsp;</p>
	  <!-- Start Ads HERE		-->
      <table class="adTable">
        <c:forEach items='${info.allAds}' var='ad'> 
        <tr> 
          <td> 
		    <a name="<c:out value='${ad.salesRep_ID}'/>"></a>
		    <a href="http://www.adempiere.com/wstore/click?<c:out value='${ad.clickTargetURL}'/>" target="_blank"> 
		    <img src="<c:out value='${ad.imageURL}'/>" alt="<c:out value='${ad.name}'/>" border="0" align="left" /></a> 
		    <img src="<c:out value='${ad.webParam2}'/>" alt="<c:out value='${ad.webParam1}'/>" border="0" align="right" /> 
		    &nbsp; <b><c:out value='${ad.description}'/></b>
				<br>
		    &nbsp; <a href="http://www.adempiere.com/wstore/request.jsp?SalesRep_ID=<c:out value='${ad.salesRep_ID}'/>">Contact</a>
				<br>
		    &nbsp; <a href="http://www.adempiere.com/wstore/basketServlet?M_Product_ID=1000018&SalesRep_ID=<c:out value='${ad.salesRep_ID}'/>">Buy Next Step</a>
				<br>
				&nbsp; <i><c:out value='${ad.webParam3}' escapeXml='false'/></i>
				<p><c:out value='${ad.adText}' escapeXml='false'/></p>
				<p><c:out value='${ad.webParam4}' escapeXml='false'/></p>
		  </td>
        </tr>
        </c:forEach> 
      </table>
	  <!-- End Copy HERE		-->
      <p>&nbsp;</p></div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div></body>
</html>
