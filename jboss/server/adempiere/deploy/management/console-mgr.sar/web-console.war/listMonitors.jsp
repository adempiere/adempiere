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
%>
<?xml version="1.0" encoding="iso-8859-1"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>JBoss Management Console - Manage Monitor</title>
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
			<h3>Monitors and Monitor Status</h3>
					<p>&nbsp;</p>
<table cellspacing="2" cellpadding="2" border="1">
<tr>
    <td><b>Status</b></td>
    <td><b>Monitor Name</b></td>
    <td><b>Observed MBean</b></td>
    <td><b>Observed Attribute</b></td>
    <td>&nbsp;</td>
</tr>
<%
   MBeanServer mbeanServer = MBeanServerLocator.locateJBoss();
   InstanceOfQueryExp queryExp = null;
   queryExp = new InstanceOfQueryExp("org.jboss.monitor.JBossMonitorMBean");
   Set monitors = mbeanServer.queryNames(null, queryExp);
   Iterator mbeans = monitors.iterator();
   boolean someAlertSet = false;
   while (mbeans.hasNext())
   {
      ObjectName moname = (ObjectName)mbeans.next();
      String monitorName = (String)mbeanServer.getAttribute(moname, "MonitorName");
      ObjectName observedObject = (ObjectName)mbeanServer.getAttribute(moname, "ObservedObject");
      String attribute = (String)mbeanServer.getAttribute(moname, "ObservedAttribute");
      boolean enabled = ((Boolean)mbeanServer.getAttribute(moname, "Enabled")).booleanValue();

      Object[] args = {"monitors", monitorName, "-service.xml"};
      String[] signature = {"java.lang.String", "java.lang.String", "java.lang.String"};
      Object rtn = mbeanServer.invoke(new ObjectName("jboss.admin:service=DeploymentFileRepository"), "isStored", args, signature);
      boolean persisted = ((Boolean)rtn).booleanValue();

      Object[] nullArgs = {};
      String[] nullSig = {};
      boolean alerted = ((Boolean)mbeanServer.invoke(moname, "alerted", nullArgs, nullSig)).booleanValue();
      if (alerted) someAlertSet = true;
      String color = "black";
      if (!enabled) color = "grey";
      String status = "";
      if (alerted)
      {
         status = "<font color=\"red\">ALERT</font>";
      }
      else if (!enabled)
      {
         status = "<font color=\"grey\"><i>disabled</i></font>";
      }
      else
      {
         status = "<font color=\"green\">OK</font>";
      }
      String link = null;
      if (persisted)
      {
         link = "manageThresholdMonitor.jsp?monitorObjectName=" + java.net.URLEncoder.encode(moname.toString());
      }
      else
      {
         link = "/jmx-console/HtmlAdaptor?action=inspectMBean&name=" + java.net.URLEncoder.encode(moname.toString());
      }
      String observedMbeanLink = "/jmx-console/HtmlAdaptor?action=inspectMBean&name=" + java.net.URLEncoder.encode(observedObject.toString());
%>
<tr>
    <td><%=status%></td>
    <td><font color="<%=color%>"><%=monitorName%></font></td>
    <td><font color="<%=color%>"><a href="<%=observedMbeanLink%>"><%=observedObject.toString()%></a></font></td>
    <td><font color="<%=color%>"><%=attribute%></font></td>
    <td><a href="<%=link%>">manage</a></td>
</tr>
<%
   }
   %>
</table>
<%
   if (someAlertSet)
   {
%>
<form action="ClearMonitorAlerts" method="post">
<input type="submit" name="action" value="Clear All Alerts">
</form>
<%
   }
}
catch (Exception ex)
{
   %> ERROR in parsing <%
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
