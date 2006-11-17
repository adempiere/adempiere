<%@ page import="javax.management.ObjectName,
                 javax.management.j2ee.statistics.ServletStats,
                 javax.management.j2ee.statistics.TimeStatistic"%>
<%@ taglib uri="/webconsole" prefix="jb" %>
<jb:mbean id="servlet" intf="org.jboss.management.j2ee.ServletMBean" />
<%
   String doReset = request.getParameter("doReset");
   if (doReset != null && doReset.equals("true"))
   {
       servlet.resetStats();
   }

   String resetUrl = response.encodeURL(request.getRequestURI()) + "?doReset=true&ObjectName=" + java.net.URLEncoder.encode(request.getParameter("ObjectName"));
   String myUrl = response.encodeURL(request.getRequestURI()) + "?" + "&ObjectName=" + java.net.URLEncoder.encode(request.getParameter("ObjectName"));
%>

<?xml version="1.0" encoding="iso-8859-1"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>JBoss Management Console - Servlet</title>
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
			<h3>Servlet</h3>
				<h4>Name</h4>
					<p><%=new ObjectName(servlet.getobjectName()).getKeyProperty("name")%> </p>
				<h4>Servlet Statistics:</h4>
					<p>&nbsp;</p>

				<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="95%" align="center" id="AutoNumber1">
                  <tr>
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
                    ServletStats stats = (ServletStats)servlet.getstats();
                    TimeStatistic stat = stats.getServiceTime();
				%>

                  <tr>
                    <td ><font size="1">
                    <h4 style="text-align: center"><%=stat.getMinTime()%></h4></font>
                    </td>
                    <td ><font size="1">
                    <h4 style="text-align: center"><%=stat.getMaxTime()%></h4></font>
                    </td>
                    <td ><font size="1">
                    <h4 style="text-align: center"><%=(float)stat.getTotalTime()/(float)stat.getCount()%></h4></font>
                    </td>
                    <td ><font size="1">
                    <h4 style="text-align: center"><%=stat.getTotalTime()%></h4></font>
                    </td>
                    <td ><font size="1">
                    <h4 style="text-align: center"><%=stat.getCount()%></h4></font>
                    </td>
                  </tr>
            </table>
            <p align="center"><a href="<%=resetUrl%>">Reset Stats</a> / <a href="<%=myUrl%>">Refresh Stats</a></p>
					<p>&nbsp;</p>
			<p>&nbsp;</p>

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
