<%@ taglib uri="/webconsole" prefix="jb" %>
<jb:mbean id="jndiview" intf="org.jboss.naming.JNDIViewMBean" mbean="jboss:service=JNDIView" />

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>JBoss Management Console - JNDI View</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="StyleSheet" href="css/jboss.css" type="text/css"/>
<link rel="StyleSheet" href="css/dtree.css" type="text/css" />
</head>
<body>
<!-- header begin -->
	<img src="images/logo.gif" alt="JBoss" id="logo" width="226" height="105" />
	<div id="header">

		&nbsp;</div>
	<div id="navigation_bar">
	</div>
<!-- header end -->
<hr class="hide"/>
			<h3>JNDI View</h3>
			<p>&nbsp;</p>

         <%=org.jboss.console.util.XMLToHTMLTreeBuilder.convertJNDIXML(jndiview.listXML())%>

         <p>&nbsp;</p>
<!-- content end -->

<hr class="hide"/>
<!-- footer begin -->
	<div id="footer">

		<div id="credits">JBoss&trade; Management Console</div>
		<div id="footer_bar">&nbsp;</div>
	</div>
<!-- footer end -->
</body>
</html>
