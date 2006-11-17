<%@ taglib uri="/webconsole" prefix="jb" %>
<%@ page import="org.jboss.aop.*,java.util.*,
                 org.jboss.aop.advice.AdviceBinding" %>                 
<jb:mbean id="server" mbean='jboss.system:type=Server' intf="org.jboss.system.server.ServerImplMBean" />
<jb:mbean id="serverInfo" mbean='jboss.system:type=ServerInfo' intf="org.jboss.system.server.ServerInfoMBean" />
<jb:mbean id="serverConfig" mbean='jboss.system:type=ServerConfig' intf="org.jboss.system.server.ServerConfigImplMBean" />
<%
   String myUrl = response.encodeURL(request.getRequestURI() + "?" + request.getQueryString());
%>
<?xml version="1.0" encoding="iso-8859-1"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>JBoss Management Console - AOP Pointcuts</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link
</head>
<link rel="StyleSheet" href="css/jboss.css" type="text/css"/>
<body>
<!-- header begin -->
	<img src="images/logo.gif" alt="JBoss" id="logo" width="226" height="105" />
	<div id="header">
		&nbsp;</div>
	<div id="navigation_bar">
	</div>
<!-- header end -->
<%
   String name = request.getParameter("binding");
   AdviceBinding binding = (AdviceBinding)AspectManager.instance().getBindings().get(name);
%>
<hr class="hide"/>
	<center>
   <h4 style="text-align: center"><%=name%></h4>
	<div id="content">
		<div class="content_block" style="width: 100%">
			<h3>Advice Binding</h3>
	    <p>&nbsp;</p>
		<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="95%" align="center" id="AutoNumber1">
                  <tr>
                    <td width="50%" align="center" colspan="2">
                    <p align="left"><font size="1"><b>Pointcut Expression: </b><%=binding.getPointcut().getExpr()%></font></p>
                    <% if (binding.getCFlowString() != null) {
                    %>
                    <p align="left"><font size="1"><b>CFlow Expression: </b><%=binding.getCFlowString()%></font></p>
                    <% } %>
                    </td>
                  </tr>

            </table>
					<p>&nbsp;</p>
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
		<div id="credits">JBoss™ Management Console</div>
		<div id="footer_bar">&nbsp;</div>
	</div>
<!-- footer end -->
</body>
</html>
