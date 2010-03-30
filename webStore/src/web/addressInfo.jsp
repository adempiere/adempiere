<%@ include file="/WEB-INF/jspf/page.jspf" %>
<html>
<head>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<title><c:out value='${ctx.name}'/> - My Address Info</title>
</head>
<body><div id="page">
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<div id="main">
	<%@ include file="/WEB-INF/jspf/menu.jspf" %>
    <%@ include file="/WEB-INF/jspf/vendor.jspf" %>
	<div id="content">
		<h1>My Address</h1>
      <h3>Please confirm your address:</h3>
      <form action="loginServlet" method="post" enctype="application/x-www-form-urlencoded" name="addressForm" id="address"
	    onSubmit="checkForm(this, new Array ('Name','Address'));">
	    
	    <fieldset>
	    <legend>Address on File</legend>
	    
        <input name="Mode" type="hidden" value="Submit"/>
        
        <label id="LBL_Name" for="Name">Name</label>
        <input class="mandatory" size="40" id="ID_Name" value='<c:out value="${webUser.name}"/>' name="Name" maxlength="60" type="text"/>
        <br/>
        
		<label id="LBL_Company" for="Company">Company</label>
		<input size="40" id="ID_Company" value='<c:out value="${webUser.company}"/>' name="Company" maxlength="60" type="text"/> 
		<br/>
		
		<label id="LBL_Title" for="Title">Title</label>
		<input size="40" id="ID_Title" value='<c:out value="${webUser.title}"/>' name="Title" maxlength="60" type="text"/> 
		<br/>

		<label id="LBL_Address" for="Address">Address</label>
		<input class="mandatory" size="40" id="ID_Address" value='<c:out value="${webUser.address}"/>' name="Address" maxlength="60" type="text"/> 
		<br/>

		<label id="LBL_Address2" for="Address">Address2</label>
		<input size="40" id="ID_Address2" value='<c:out value="${webUser.address2}"/>' name="Address2" maxlength="60" type="text"/> 
		<br/>

		<cws:location countryID="${webUser.countryID}" regionID="${webUser.regionID}" regionName="${webUser.regionName}" city="${webUser.city}" postal="${webUser.postal}" /> 

		<label id="LBL_Phone" for="Phone">Phone</label>
		<input size="20" id="ID_Phone" value='<c:out value="${webUser.phone}"/>' name="Phone" maxlength="20" type="text"/>
		<br/>
		
		<label id="LBL_Fax" for="Fax">Fax</label>
		<input size="20" id="ID_Fax" value='<c:out value="${webUser.fax}"/>' name="Fax" maxlength="20" type="text"/>
		<br/>

		<div class="buttons">
			<input type="reset" name="Reset" value="Reset">
			<input name="AddressConfirm" type="hidden" id="AddressConfirm" value="Y">
			<input type="submit" name="Submit" id="Submit" value="Submit Info"> 
		</div>

        <c:if test="${not empty webUser.saveErrorMessage}">
            <div class="error"><c:out value="${webUser.saveErrorMessage}"/></div>
        </c:if>
		<div id="processingDiv" style="display:none"><strong>Processing ...</strong></div>
<!-- 
		<div id="submitDiv" style="display:inline">
			<input type="submit" name="Submit" id="Submit" value="Submit Info"> 
		</div>
-->
        <div align="center">Enter all <b class="mandatory">mandatory</b> data. </div>
        
        </fieldset>
      </form>
      <p>&nbsp;</p></div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div></body>
</html>
