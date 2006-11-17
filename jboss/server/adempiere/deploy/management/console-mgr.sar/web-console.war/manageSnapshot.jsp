<%@ page import="javax.management.MBeanServer,
                 org.jboss.mx.util.MBeanServerLocator,
                 org.jboss.mx.util.InstanceOfQueryExp,
                 java.util.Set,
                 java.util.Iterator,
                 javax.management.ObjectInstance,
                 javax.management.ObjectName,
                 java.util.HashSet,
                 java.util.ArrayList"%>
 <%--
 |
 |  Author: Bill Burke    (bill@jboss.org)
 |
 | Distributable under LGPL license.
 | See terms of license at gnu.org.
 +--%>
<%
try
{
   MBeanServer mbeanServer = MBeanServerLocator.locateJBoss();
   String error = (String)request.getAttribute("error");
   ObjectName moname = null;
   String monitorName = null;
   String monitorObjectName = request.getParameter("monitorObjectName");
   /*
   if (monitorObjectName == null) // if we're being routed via
   {
      monitorName = (String)request.getAttribute("monitorName");
      moname = new ObjectName("jboss.snapshot:name=" + monitorName);
   }
   */
   moname = new ObjectName(monitorObjectName);
   monitorName = (String)mbeanServer.getAttribute(moname, "MonitorName");
   ObjectName observedObject = (ObjectName)mbeanServer.getAttribute(moname, "ObservedObject");
   String attribute = (String)mbeanServer.getAttribute(moname, "ObservedAttribute");
   Long period = (Long)mbeanServer.getAttribute(moname, "Period");
   boolean recording = ((Boolean)mbeanServer.getAttribute(moname, "Recording")).booleanValue();
%>
<?xml version="1.0" encoding="iso-8859-1"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>JBoss Management Console - Manage Snapshot</title>
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
			<h3>Manage Snapshot</h3>
					<p>&nbsp;</p>
<%
   if (error != null)
   {
%>
					<p><font color="red" size ="-2"><%=error%></font> </p>
<%
   }
%>
<form action="ManageSnapshot" method="post">
<input type="hidden" name="monitorObjectName" value="<%=moname.toString()%>">
<table cellspacing="2" cellpadding="2" border="0">
<tr>
    <td><b>Monitor Name</b></td>
    <td><input type="text" name="monitorName" size="35" value="<%=monitorName%>" readonly></td>
    <td><i>The name of the monitor and how it will be references within web console</i></td>
</tr>
<tr>
    <td><b>Monitor's Object Name</b></td>
    <td><input type="text" name="monitorObjectName" size="35" value="<%=monitorObjectName%>" readonly></td>
    <td><i>The MBean javax.management.ObjectName</i></td>
</tr>
<tr>
    <td><b>Object Name</b></td>
    <td><input type="text" name="objectName" value="<%=observedObject.toString()%>" size="35" readonly></td>
    <td><i>The MBean javax.management.ObjectName of the MBean you are monitoring</i></td>
</tr>
<tr>
    <td><b>Attribute</b></td>
    <td><input type="text" name="attribute" value="<%=attribute%>"  size="35" readonly></td>
    <td><i>The MBean Attribute you are monitoring</i></td>
</tr>
<tr>
    <td><b>Time Period</b></td>
    <td><input type="text" name="period" size="35" value="<%=period%>" readonly></td>
    <td><i>How often should threshold be tested.</i></td>
</tr>
</table>
<% if (recording)
   {
%>
<input type="submit" name="action" value="Stop Snapshot">
<% }  else {%>
<input type="submit" name="action" value="Start Snapshot">
<% } %>
<input type="submit" name="action" value="Graph Dataset">
<input type="submit" name="action" value="Clear Dataset">
<input type="submit" name="action" value="Show Dataset">
<input type="submit" name="action" value="Remove Snapshot">
</form>
<%
}
catch (Exception ex)
{
   %> ERROR  <%
   ex.printStackTrace();
}
%>
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
