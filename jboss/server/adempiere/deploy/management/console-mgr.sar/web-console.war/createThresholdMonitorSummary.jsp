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
   String monitorName = request.getParameter("monitorName");
   if (monitorName == null) monitorName = "";
   String objectName = request.getParameter("objectName");
   if (objectName == null) objectName = "";
   String attribute = request.getParameter("attribute");
   if (attribute == null) attribute = "";
   String threshold = request.getParameter("threshold");
   if (threshold == null) threshold = "";
   String period = request.getParameter("period");
   if (period == null) period = "";
   String compare = request.getParameter("compare");
   if (compare == null) compare = "gt";
   String enabled = request.getParameter("enabled");
   if (enabled == null) enabled = "";
   String[] alertStrings = request.getParameterValues("alerts");
   HashSet alertSet = null;
   if (alertStrings != null)
   {
      alertSet = new HashSet();
      for (int i = 0; i < alertStrings.length; i++)
      {
         alertSet.add(alertStrings[i]);
      }
   }



%>
<?xml version="1.0" encoding="iso-8859-1"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>JBoss Management Console - Server Information</title>
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
			<h3>Threshold MBean Monitor Created</h3>
					<p>&nbsp;</p>
					<p>&nbsp;</p>
<table cellspacing="2" cellpadding="2" border="0">
<tr>
    <td><b>Monitor Name</b></td>
    <td><input type="text" name="monitorName" size="35" value="<%=monitorName%>" readonly></td>
    <td><i>The name of the monitor and how it will be references within web console</i></td>
</tr>
<tr>
    <td><b>Object Name</b></td>
    <td><input type="text" name="objectName" value="<%=objectName%>" size="35" readonly></td>
    <td><i>The MBean javax.management.ObjectName of the MBean you are monitoring</i></td>
</tr>
<tr>
    <td><b>Attribute</b></td>
    <td><input type="text" name="attribute" value="<%=attribute%>"  size="35" readonly></td>
    <td><i>The MBean Attribute you are monitoring</i></td>
</tr>
<tr>
    <td><b>Threshold</b></td>
    <td><input type="text" name="threshold" size="35" value="<%=threshold%>" readonly></td>
    <td><i>The value that will trigger an alert when the Comparison Equation is reached for the attribute value</i></td>
</tr>
<tr>
    <td><b>Time Period</b></td>
    <td><input type="text" name="period" size="35" value="<%=period%>" readonly></td>
    <td><i>How often should threshold be tested.</i></td>
</tr>
<tr>
    <td><b>Comparison Equation</b></td>
    <td><select name="compare" id="compare" size="1">
        <option value="gt" <%=compare.equals("gt") ? "SELECTED" : ""%>>&gt;</option>
        <option value="lt" <%=compare.equals("lt") ? "SELECTED" : ""%>>&lt;</option>
        <option value="eq" <%=compare.equals("eq") ? "SELECTED" : ""%>>=</option>
        </select>
    </td>
    <td><i>Boolean expression to use when testing threshold hit.</i></td>
</tr>
<tr>
    <td><b>Enable Monitor</b></td>
    <td><input type="checkbox" name="enabled" value="" <%=enabled%>></td>
    <td><i>Should this monitor be enabled.</i></td>
</tr>
<tr>
    <td><b>Alerts</b></td>
    <td>
<%
   MBeanServer mbeanServer = MBeanServerLocator.locateJBoss();
   InstanceOfQueryExp queryExp = null;
   queryExp = new InstanceOfQueryExp("org.jboss.monitor.alerts.JBossAlertListener");
   Set alerts = mbeanServer.queryNames(null, queryExp);
   if (alerts.size() > 0)
   {
%>
   <select name="alerts" id="alerts" size="<%=Integer.toString(alerts.size())%>" multiple readonly>
<%
      Iterator it = alerts.iterator();
      while (it.hasNext())
      {
         ObjectName alert = (ObjectName)it.next();
         if (alertSet.contains(alert.toString()))
         {
            String alertName = (String)mbeanServer.getAttribute(alert, "AlertName");
%>
   <option value="<%=alert.toString()%>" ><%=alertName%></option>
<%
         }
      }
   }
%>
</select>

    </td>
    <td><i>Alert Listeners to trigger.</i></td>
</tr>
</table>
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
