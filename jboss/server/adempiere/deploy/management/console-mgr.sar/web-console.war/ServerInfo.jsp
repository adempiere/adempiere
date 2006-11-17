<%--
 |
 |  Author: Sacha Labourey    (sacha@jboss.org)
 |
 | Distributable under LGPL license.
 | See terms of license at gnu.org.
 +--%>
<%@ taglib uri="/webconsole" prefix="jb" %>
<jb:mbean id="server" mbean='jboss.system:type=Server' intf="org.jboss.system.server.ServerImplMBean" />
<jb:mbean id="serverInfo" mbean='jboss.system:type=ServerInfo' intf="org.jboss.system.server.ServerInfoMBean" />
<jb:mbean id="serverConfig" mbean='jboss.system:type=ServerConfig' intf="org.jboss.system.server.ServerConfigImplMBean" />
<%
   String myUrl = response.encodeURL(request.getRequestURI());
%>
<%!
public String memSize (java.lang.Long lOctets)
{
	long octets = lOctets.longValue();
	int ratio = 3*1024;
	String unit = "";
	if (octets > ratio)
	{
	   unit = "KB";
	   octets/=1024;	
	   
		if (octets > ratio)
		{
		   unit = "MB";
		   octets/=1024;	
		   
			if (octets > ratio)
			{
			   unit = "GB";
			   octets/=1024;	
			   
				if (octets > ratio)
				{
				   unit = "TB";
				   octets/=1024;	
				}
			}
		}
	}
	
	return octets + " " + unit;
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
			<h3>JBoss&trade; Application Server</h3>
					<p>&nbsp;</p>
				<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="95%" align="center" id="AutoNumber1">
                  <tr>
                    <td width="50%" align="center" colspan="2">
                    <h4 style="text-align: center"><font size="3">JBoss</font></h4>
                    </td>
                  </tr>
                  <tr>
                    <td width="25%" align="center" valign="top">
                    <h4>Version</h4>
                    <p align="left"><font size="1"><b>Version: </b><%=server.getVersion()%></font></p>
                    <p align="left"><font size="1"><b>Version Name: </b><%=server.getVersionName()%></font></p>
                    <p align="left"><font size="1"><b>Built on: </b><%=server.getBuildDate()%></font>
                    </td>
                    <td width="25%" align="center" valign="top">
                    <h4>Environment</h4>
                    <p align="left"><font size="1"><b>Start date: </b><%=server.getStartDate()%></font></p>
                    <p align="left"><font size="1"><b>Host: </b><%=serverInfo.getHostName()%> (<%=serverInfo.getHostAddress ()%>)</font></p>
                    <p align="left"><font size="1"><b>Base Location: </b><%=serverConfig.getServerBaseURL()%></font></p>
                    <p align="left"><font size="1"><b>Base Location (local): </b><%=serverConfig.getServerBaseDir()%></font></p>
                    <p align="left"><font size="1"><b>Running config: </b>'<%= serverConfig.getServerHomeDir().getName()%>'</font></td>
                  </tr>
	
            </table>
            <p>&nbsp;</p>
				<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="95%" align="center" id="AutoNumber1">
                  <tr>
                    <td width="50%" align="center" colspan="2">
                    <h4 style="text-align: center"><font size="3">JVM - Hardware</font></h4>
                    </td>
                  </tr>
                  <tr>
                    <td width="25%" align="center" valign="top">
                    <h4>Hardware</h4>
                    <p align="left"><font size="1"><b>#CPU: </b><%=serverInfo.getAvailableProcessors()%></font></p>
                    <p align="left"><font size="1"><b>OS: </b><%=serverInfo.getOSName()%> <%=serverInfo.getOSVersion()%> (<%=serverInfo.getOSArch()%>)</font></td>
                    <td width="25%" align="center" valign="top">
                    <h4>JVM Environment</h4>
                    <p align="left"><font size="1"><b>Free Memory: </b><%=memSize(serverInfo.getFreeMemory())%></font></p>
                    <p align="left"><font size="1"><b>Max Memory: </b><%=memSize(serverInfo.getMaxMemory())%></font></p>
                    <p align="left"><font size="1"><b>Total Memory: </b><%=memSize(serverInfo.getTotalMemory())%></font></p>
                    <p align="left"><font size="1"><b>#Threads: </b><%=serverInfo.getActiveThreadCount()%></font></p>
                    <p align="left"><font size="1"><b>JVM Version: </b><%=serverInfo.getJavaVMVersion()%> (<%=serverInfo.getJavaVMVendor()%>)</font></p>
                    <p align="left"><font size="1"><b>JVM Name: </b><%=serverInfo.getJavaVMName()%></font></td>
                  </tr>

            </table>
					<p>&nbsp;</p>
            <p align="center"> <a href="<%=myUrl%>">Refresh</a></p>

					<p>&nbsp;</p>
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
		<div id="credits">JBoss&trade; Management Console</div>
		<div id="footer_bar">&nbsp;</div>
	</div>
<!-- footer end -->
</body>
</html>
