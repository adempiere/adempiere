<%@ taglib uri="/webconsole" prefix="jb" %>
<%@ page import="org.jboss.aop.*,org.jboss.aop.advice.*,java.util.*,java.lang.reflect.Field,
                 java.lang.reflect.Constructor,
                 org.jboss.console.plugins.AOPLister,
                 gnu.trove.TLongObjectHashMap,
                 java.lang.reflect.Method"%>
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
    String classname = request.getParameter("classname");
    String calledClassname = request.getParameter("calledclassname");
    long hash  = Long.parseLong(request.getParameter("hash"));
    long callingHash  = Long.parseLong(request.getParameter("callinghash"));
    ClassAdvisor advisor = (ClassAdvisor)AspectManager.instance().getAdvisor(classname);
    Method method = (Method)advisor.getAdvisedMethods().get(callingHash);
    HashMap called = (HashMap)advisor.getConCalledByMethodInterceptors().get(callingHash);
    TLongObjectHashMap map = (TLongObjectHashMap)called.get(calledClassname);
    CallerConstructorInfo info = (CallerConstructorInfo)map.get(hash);
    Interceptor[] interceptors = info.interceptors;

%>
<hr class="hide"/>
	<center>
	<div id="content">
		<div class="content_block" style="width: 100%">
			<h3><%=classname%></h3>
	    <p>&nbsp;</p>
		<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="95%" align="center" id="AutoNumber1">
                  <tr>
                    <td width="50%" align="center" colspan="2">
                    <h4 style="text-align: center"><font size="3">Method Caller: <%=AOPLister.shortenMethod(classname, method)%></font></h4>
                    <h4 style="text-align: center"><font size="3">Called Constructor: <%=info.constructor.toString()%></font></h4>
                    </td>
                  </tr>
                  <tr>
                    <td>
                    <h4>Type</h4>
                    </td>
                    <td>
                    <h4>Description</h4>
                    </td>
                  </tr>
<%
   if (interceptors != null)
   {
   String chain = AOPLister.outputChain(interceptors);
%>
<%=chain%>
<% } %>
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
		<div id="credits">JBoss Management Console</div>
		<div id="footer_bar">&nbsp;</div>
	</div>
<!-- footer end -->
</body>
</html>