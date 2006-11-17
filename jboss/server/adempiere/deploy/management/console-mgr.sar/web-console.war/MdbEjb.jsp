<%@ page language="java" 
         import="java.util.Map"
         import="java.util.HashMap"
	 import="java.util.Iterator"
	 import="org.jboss.invocation.InvocationStatistics"
	 import="org.jboss.invocation.InvocationStatistics.*" %>

<%@ taglib uri="/webconsole" prefix="jb" %>
<jb:mbean id="ejb" intf="org.jboss.management.j2ee.MessageDrivenBeanMBean" />
<jb:mbean id="container" mbean='<%="jboss.j2ee:service=EJB,jndiName=" + (new javax.management.ObjectName(request.getParameter("ObjectName")).getKeyProperty ("name"))%>' intf="org.jboss.ejb.ContainerMBean" />

<%
   // Author: Boris Cinkler cinkler@users.sourceforge.net
   
   String ejbName = new javax.management.ObjectName(request.getParameter("ObjectName")).getKeyProperty ("name");
   String ejbModule = new javax.management.ObjectName(request.getParameter("ObjectName")).getKeyProperty ("EJBModule");
   String containerUrl = "jboss.j2ee:service=EJB,jndiName=" + ejbName;
   containerUrl = java.net.URLEncoder.encode(containerUrl);
   containerUrl = "../jmx-console/HtmlAdaptor?action=inspectMBean&name=" + containerUrl;

   org.jboss.invocation.InvocationStatistics invokeStats= container.getInvokeStats ();

   String doReset = request.getParameter("doReset");
   if (doReset != null && doReset.equals("true"))
   {
   	 ejb.resetStats ();
   }
   String doResetInvoc= request.getParameter("doResetInvoc");
   if (doResetInvoc!= null && doResetInvoc.equals("true"))
   {
   	 invokeStats.resetStats ();
   }

   String resetUrl = response.encodeURL(request.getRequestURI()) + "?doReset=true&ObjectName=" + java.net.URLEncoder.encode(request.getParameter("ObjectName"));
   String resetInvocUrl = response.encodeURL(request.getRequestURI()) + "?doResetInvoc=true&ObjectName=" + java.net.URLEncoder.encode(request.getParameter("ObjectName"));
   String myUrl = response.encodeURL(request.getRequestURI()) + "?" + "&ObjectName=" + java.net.URLEncoder.encode(request.getParameter("ObjectName"));
   org.jboss.metadata.BeanMetaData beanMetaData = container.getBeanMetaData();

   %>

   <%!
public String addStatRow (javax.management.j2ee.statistics.Statistic stat)
{
   String start = new java.util.Date(stat.getStartTime ()).toString ();
   String lastSample = new java.util.Date(stat.getLastSampleTime ()).toString ();
	
   String result = "<tr><td width=\"25%\"><font size=\"1\">" + stat.getName() + " (unit: " + stat.getUnit () + ")</font></td>" +
                   "<td width=\"25%\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse: collapse\" bordercolor=\"#111111\" width=\"100%\"><tr>";
	                
   if (stat instanceof javax.management.j2ee.statistics.CountStatistic)
   {
      result += "<td width=\"33%\" align=\"center\"><font size=\"1\">" + ((javax.management.j2ee.statistics.CountStatistic)stat).getCount() + "</font></td><td width=\"33%\"></td><td width=\"34%\"></td>";
   }
   else if (stat instanceof javax.management.j2ee.statistics.RangeStatistic)
   {
      javax.management.j2ee.statistics.RangeStatistic rStat = (javax.management.j2ee.statistics.RangeStatistic)stat;
		
      result += "<td width=\"33%\" align=\"center\"><font size=\"1\">" + rStat.getCurrent ()  + "</font></td><td width=\"33%\" align=\"center\"><font size=\"1\">" + rStat.getLowWaterMark () + "</font></td>" +
                "<td width=\"34%\" align=\"center\"><font size=\"1\">" + rStat.getHighWaterMark() + "</font></td>";
   }
	
   result += "</tr></table></td><td width=\"25%\"><font size=\"1\">" + start + "</font></td><td width=\"25%\"><font size=\"1\">" + lastSample + "</font>&nbsp;</td></tr>";
	
   return result;
}
%>

<?xml version="1.0" encoding="iso-8859-1"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>JBoss Management Console - Message Driven Bean</title>
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
		<div class="content_block" style="width: 831; height: 247">
			<h3>Message Driven Bean</h3>
				<h4>Name</h4>
					<p><%=beanMetaData.getEjbName()%> (JNDI: <%=ejbName%>)</p>
				<h4>EJB Module</h4>
					<p><%=ejbModule %></p>
				<h4>Bean Statistics:</h4>
					<p>&nbsp;</p>
<%
if (ejb.isstatisticsProvider())
{
%>				
		<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="95%" align="center" id="AutoNumber1">
                  <tr>
                    <td width="25%" align="center">
                    <h4 style="text-align: center">Name</h4>
                    </td>
                    <td width="25%" align="center">
                    <h4 style="text-align: center">Value</h4>
                    <table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber3">
                      <tr>
                        <td width="33%">
                        <h4 style="text-align: center">Current</h4>
                        </td>
                        <td width="33%">
                        <h4 style="text-align: center">Low</h4>
                        </td>
                        <td width="34%">
                        <h4 style="text-align: center">High</h4>
                        </td>
                      </tr>
                    </table>
                    </td>
                    <td width="25%" align="center">
                    <h4 style="text-align: center">Start Time</h4>
                    </td>
                    <td width="25%" align="center">
                    <h4 style="text-align: center">Last Sample Time</h4>
                    </td>
                  </tr>
	<%
	javax.management.j2ee.statistics.MessageDrivenBeanStats stats = (javax.management.j2ee.statistics.MessageDrivenBeanStats)ejb.getstats ();
%>	
	<%=addStatRow (stats.getCreateCount ()) %>
	<%=addStatRow (stats.getRemoveCount ()) %>
	<%=addStatRow (stats.getMessageCount ()) %>

            </table>
            <p align="center"><a href="<%=resetUrl%>">Reset Stats</a> / <a href="<%=myUrl%>">Refresh Stats</a></p>
<%
}
else
{
%>				
					<p>none.</p>
<%
}
%>				
					<p>&nbsp;</p>
					<p>&nbsp;</p>
				<h4>Invocation Statistics:</h4>
					<p>&nbsp;</p>
<%
	java.util.Iterator iterInvoc = invokeStats.getStats().entrySet().iterator();
	if (iterInvoc.hasNext())
	{	
	
%>

				<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="95%" align="center" id="AutoNumber1">
                  <tr>
                    <td  align="center">
                    <h4 style="text-align: center">Method name</h4>
                    </td>
                    <td align="center">
                    <h4 style="text-align: center">Min (ms)</h4>
                    </td>
                    <td align="center">
                    <h4 style="text-align: center">Max (ms)</h4>
                    </td>
                    <td align="center">
                    <h4 style="text-align: center">Average (ms)</h4>
                    </td>
                    <td align="center">
                    <h4 style="text-align: center">Total (ms)</h4>
                    </td>
                    <td align="center">
                    <h4 style="text-align: center"># Invocations</h4>
                    </td>
                  </tr>
				<%
				while (iterInvoc.hasNext())
				{
               java.util.Map.Entry entry = (java.util.Map.Entry) iterInvoc.next();
               org.jboss.invocation.InvocationStatistics.TimeStatistic stat = (org.jboss.invocation.InvocationStatistics.TimeStatistic) entry.getValue();
				%>
				
                  <tr>
                    <td ><font size="1">
                    <h4 style="text-align: center"><%=((java.lang.reflect.Method)entry.getKey()).getName()%></h4></font>
                    </td>
                    <td ><font size="1">
                    <h4 style="text-align: center"><%=stat.minTime%></h4></font>
                    </td>
                    <td ><font size="1">
                    <h4 style="text-align: center"><%=stat.maxTime%></h4></font>
                    </td>
                    <td ><font size="1">
                    <h4 style="text-align: center"><%=(float)stat.totalTime/(float)stat.count%></h4></font>
                    </td>
                    <td ><font size="1">
                    <h4 style="text-align: center"><%=stat.totalTime%></h4></font>
                    </td>
                    <td ><font size="1">
                    <h4 style="text-align: center"><%=stat.count%></h4></font>
                    </td>
                  </tr>
                  <%
                  }
                  %>
            </table>
            <p align="center"><a href="<%=resetInvocUrl%>">Reset Stats</a> / <a href="<%=myUrl%>">Refresh Stats</a></p>
<%
	}
	else
	{
%>	 
					<p>none.</p>
<%
	}
%>

					<p>&nbsp;</p>
               <p>Actual concurrent invocations: <%=invokeStats.concurrentCalls%> (max: <%=invokeStats.maxConcurrentCalls%>)</p>
					<p>&nbsp;</p>
					<p>&nbsp;</p>
					<p>&nbsp;</p>
					<p><a href="<%=containerUrl%>">View associated container MBean</a></p>
		</div>
		<div class="spacer"><hr/></div>
	</div>
	</center>
<!-- content end -->

<hr class="hide"/>
<!-- footer begin -->
	<div id="footer">
		<div id="credits">JBoss Management Console</div>
		<div id="footer_bar">&nbsp;</div>
	</div>
<!-- footer end -->
</body>
</html>
