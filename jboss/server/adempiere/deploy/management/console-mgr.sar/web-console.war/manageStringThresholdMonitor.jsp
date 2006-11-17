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
   String monitorObjectName = request.getParameter("monitorObjectName");
   ObjectName moname = new ObjectName(monitorObjectName);
   String monitorName = (String)mbeanServer.getAttribute(moname, "MonitorName");
   ObjectName observedObject = (ObjectName)mbeanServer.getAttribute(moname, "ObservedObject");
   String attribute = (String)mbeanServer.getAttribute(moname, "ObservedAttribute");
   String threshold = (String)mbeanServer.getAttribute(moname, "Threshold");
   Long period = (Long)mbeanServer.getAttribute(moname, "Period");
   boolean equality = ((Boolean)mbeanServer.getAttribute(moname, "EqualityTriggersAlert")).booleanValue();
   boolean enabled = ((Boolean)mbeanServer.getAttribute(moname, "Enabled")).booleanValue();
   ArrayList alerts = (ArrayList)mbeanServer.getAttribute(moname, "AlertListeners");

   Object[] args = {"monitors", monitorName, "-service.xml"};
   String[] signature = {"java.lang.String", "java.lang.String", "java.lang.String"};
   Object rtn = mbeanServer.invoke(new ObjectName("jboss.admin:service=DeploymentFileRepository"), "isStored", args, signature);
   boolean persisted = ((Boolean)rtn).booleanValue();

   Object[] nullArgs = {};
   String[] nullSig = {};
   boolean alerted = ((Boolean)mbeanServer.invoke(moname, "alerted", nullArgs, nullSig)).booleanValue();
   Object triggeredValue = null;
   if (alerted) triggeredValue = mbeanServer.getAttribute(moname, "TriggeredAttributeValue");
   String color = alerted ? "red" : "green";

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
			<h3><font color="<%=color%>">Manage String MBean Monitor</font></h3>
					<p>&nbsp;</p>
<%
   if (error != null)
   {
%>
					<p><font color="red" size ="-2"><%=error%></font> </p>
<%
   }
%>
<form action="ManageStringThresholdMonitor" method="post">
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
<%
   if (alerted)
   {
%>
<tr>
    <td><font color="red"><b>Triggered Value</b></font></td>
    <td><input type="text" name="trigger" value="<%=triggeredValue.toString()%>" size="35" readonly></td>
    <td><i>The attribute value the triggered the threshold.</i></td>
</tr>
<% } %>
<tr>
    <td><b>Threshold</b></td>
    <td><input type="text" name="threshold" size="35" value="<%=threshold%>"></td>
    <td><i>The value that will trigger an alert when the Comparison Equation is reached for the attribute value</i></td>
</tr>
<tr>
    <td><b>Time Period</b></td>
    <td><input type="text" name="period" size="35" value="<%=period%>"></td>
    <td><i>How often should threshold be tested.</i></td>
</tr>
<tr>
    <td><b>Persist Changes</b></td>
    <td><input type="checkbox" name="persisted" value="" checked></td>
    <td><i>Should changes be reflected in deployment file.</i></td>
</tr>
<tr>
    <td><b>Equality Trigger</b></td>
    <td><input type="checkbox" name="equality" value="" <%=equality ? "checked" : ""%>></td>
    <td><i>Uncheck this box if you want an alert to trigger when attribute changes from threshold value</i></td>
</tr>
<tr>
    <td><b>Enable Monitor</b></td>
    <td><input type="checkbox" name="enabled" value="" <%=enabled ? "checked" : ""%>></td>
    <td><i>Should this monitor be enabled.</i></td>
</tr>
<tr>
    <td><b>Alerts</b></td>
    <td>
<%
   InstanceOfQueryExp queryExp = null;
   queryExp = new InstanceOfQueryExp("org.jboss.monitor.alerts.JBossAlertListener");
   Set allAlertTypes = mbeanServer.queryNames(null, queryExp);

   if (allAlertTypes.size() > 0)
   {
%>
   <select name="alerts" id="alerts" size="<%=Integer.toString(allAlertTypes.size())%>" multiple>
<%
      Iterator it = allAlertTypes.iterator();
      while (it.hasNext())
      {
         ObjectName alert = (ObjectName)it.next();
         String alertName = (String)mbeanServer.getAttribute(alert, "AlertName");
         String selected = "";
         if (alerts != null && alerts.contains(alert)) selected = "SELECTED";
%>
   <option value="<%=alert.toString()%>" <%=selected%>><%=alertName%></option>
<%
      }
   }
%>
</select>

    </td>
    <td><i>Alert Listeners to trigger.</i></td>
</tr>
</table>
<input type="submit" name="action" value="Update Monitor">
<input type="submit" name="action" value="Remove Monitor">
<%
   if (alerted)
   {
%>
<input type="submit" name="action" value="Clear Alert">
<%
   }
%>
</form>
<%
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
