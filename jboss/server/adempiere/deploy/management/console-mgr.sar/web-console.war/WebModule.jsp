<%@ page import="javax.management.ObjectName"%>
 <%--
 |
 |  Author: Sacha Labourey    (sacha@jboss.org)
 |
 | Distributable under LGPL license.
 | See terms of license at gnu.org.
 +--%>
<%@ taglib uri="/webconsole" prefix="jb" %>
<jb:mbean id="webModule" intf="org.jboss.management.j2ee.WebModuleMBean" />
<?xml version="1.0" encoding="iso-8859-1"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>JBoss Management Console - Web Module</title>
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
			<h3>Web Module: '<%=new ObjectName(webModule.getobjectName()).getKeyProperty("name")%>'</h3>
					<p>&nbsp;</p>
				<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="95%" align="center" id="AutoNumber1">
                  <tr>
                    <td width="50%" align="center">
                    <h4 style="text-align: center"><font size="3">Servlets</font></h4>
                    </td>
                  </tr>
                  <tr>
                    <td width="50%" align="center" valign="top">
                    <h4>This Web Module is composed of the following Servlets:</h4>
                    <%
                        String[] namesStr = webModule.getservlets();
                        ObjectName[] names = null;
                        if (namesStr != null) {
                            names = new ObjectName[namesStr.length];
                            for (int i = 0; i < namesStr.length; i++) {
                                names[i] = new ObjectName(namesStr[i]);
                            }
                        }
                        if (names != null)
                    %>
					<ul>
                    <%
                            for (int i = 0; i < names.length; i++)
                            {
                    %>
						<li>
						<p align="left"><font size="1"><b><%=names[i].getKeyProperty("name")%></b></font></p>
						</li>
                        <%
                            }
                        %>
					</ul>
                    <p align="left"><font size="1"><b>Number of Servlets: </b><%=(names!=null?names.length:0)%></font></p>
                    </td>
                  </tr>

            </table>
            <p>&nbsp;</p>
				<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="95%" align="center" id="AutoNumber1">
                  <tr>
                    <td width="50%" align="center">
                    <h4 style="text-align: center"><font size="3">Deployment
					Descriptor</font></h4>
                    </td>
                  </tr>
                  <tr>
                    <td width="50%" align="left" valign="top"><pre>
                    <%=org.jboss.console.plugins.helpers.servlet.ServletHelper.filter(webModule.getdeploymentDescriptor())%>
                    </pre>
                    </td>
                  </tr>

            </table>
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
