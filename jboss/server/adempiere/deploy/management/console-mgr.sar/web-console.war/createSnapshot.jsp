<%@ page import="javax.management.MBeanServer,
                 org.jboss.mx.util.MBeanServerLocator,
                 org.jboss.mx.util.InstanceOfQueryExp,
                 java.util.Set,
                 java.util.Iterator,
                 javax.management.ObjectInstance,
                 javax.management.ObjectName,
                 java.util.HashSet"%>
 <%--
 |
 |  Author: Bill Burke    (bill@jboss.org)
 |
 | Distributable under LGPL license.
 | See terms of license at gnu.org.
 +--%>
<%
   String error = (String)request.getAttribute("error");
   String attribute = request.getParameter("attribute");
   if (attribute == null) attribute = "";
   String monitorName = request.getParameter("monitorName");
   if (monitorName == null) monitorName = attribute + " Snapshot";
   String objectName = request.getParameter("objectName");
   if (objectName == null) objectName = "";
   String period = request.getParameter("period");
   if (period == null) period = "";
   String maxSize = request.getParameter("maxSize");
   if (maxSize == null) maxSize = "";
%>
<?xml version="1.0" encoding="iso-8859-1"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>JBoss Management Console - Create Snapshot</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="StyleSheet" href="css/jboss.css" type="text/css"/>
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
	<center>
	<div id="content">
		<div class="content_block" style="width: 100%; height: 247">
			<h3>Create Snapshot MBean Monitor</h3>
					<p>&nbsp;</p>
<%
   if (error != null)
   {
%>
					<p><font color="red" size ="-2"><%=error%></font> </p>
<%
   }
%>
<form action="CreateSnapshot" method="post">
<table cellspacing="2" cellpadding="2" border="0">
<tr>
    <td><b>Monitor Name</b></td>
    <td><input type="text" name="monitorName" size="35" value="<%=monitorName%>"></td>
    <td><i>The name of the monitor and how it will be references within web console</i></td>
</tr>
<tr>
    <td><b>Object Name</b></td>
    <td><input type="text" name="objectName" value="<%=objectName%>" size="35"></td>
    <td><i>The MBean javax.management.ObjectName of the MBean you are monitoring</i></td>
</tr>
<tr>
    <td><b>Attribute</b></td>
    <td><input type="text" name="attribute" value="<%=attribute%>"  size="35"></td>
    <td><i>The MBean Attribute you are monitoring</i></td>
</tr>
<tr>
    <td><b>Time Period</b></td>
    <td><input type="text" name="period" size="35" value="<%=period%>"></td>
    <td><i>How often should threshold be tested.</i></td>
</tr>
</table>
<input type="submit" value="Create">
</form>
		</div>
		<div class="spacer"><hr/></div>
	</div>
	</center>
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
