<%@ include file="/WEB-INF/jspf/page.jspf" %>
<cws:priceList priceList_ID="0"/> 
<html>
<!--
  - Author:  Jorg Janke
  - Version: $Id: help.jsp,v 1.3 2006/05/09 22:20:00 mdeaelfweald Exp $
  - Adempiere ERP & CRM Smart Business Solution - Copyright (c) 1999-2003 Jorg Janke
  - - -
  - Web Store Help
  -->
<head>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<title><c:out value='${ctx.name}'/> - Welcome</title>
</head>
<body><div id="page">
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<script language="JavaScript" type="text/JavaScript">
;
</script>
<noscript>
Please enable Java Script to continue. 
</noscript>
<div id="main">
	<%@ include file="/WEB-INF/jspf/menu.jspf" %>
    <%@ include file="/WEB-INF/jspf/vendor.jspf" %>
	<div id="content"> 
	<h1>Web Store Help</h1>
		<h3>New User</h3>
			<p>You create your new account when you create an order or when you click on the 
			"Login" button or "Welcome" link. Enter your email address, a password, and then 
			select "New User" to complete the other mandatory and optional information.</p>
		<h3>Invalid Password or EMail </h3>
			<p>If you received an error, you may select "Send Password to 
			EMail" to have your password sent to the email address you've entered. If you cannot 
			receive an email with that email address (e.g. misspelling), contact us with both 
			the current incorrect email address as well as the correct one. Do NOT login using 
			a different email address or you will create a new account and not be able to access 
			the assets of the original account. If that happens, you will need to contact us to 
			merge the accounts. </p>
		<h3>Change Password, EMail Address or Contact Information</h3>
			<p>To change your password, you must first log in with your old password. Then 
			select "Update User Info" at the top of the page. On the <i>User Information</i> screen, 
			use the <i>Change Password</i> field set to enter your old and new password. Your <i>EMail 
			Address</i> and <i>Contact Information</i> may also be changed on the <i>User Information</i> 
			screen.</p>
		<h3>Additional User of Business Partner</h3>
			<p>If you are an additional user of an existing Business Partner, please ask your 
			supervisor to contact us and we will add you as an additional user to that Business 
			Partner.</p>
		<h3>Payment Not Approved</h3>
			<p>If your payment was not successful, you may use <i>My Orders</i> to either "Void" the 
			order or "Complete" it with the correct payment information. If you need additional 
			help completing the order please use the <i>Contact Us</i> screen to contact the company 
			directly.</p>
    </div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div></body>
</html>
