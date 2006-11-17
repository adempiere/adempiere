<html>

<head>
</head>

<body>

<p>
<applet 
   width="100%" 
   height="100%" 
   code="org.jboss.console.navtree.AppletBrowser"
   archive="applet.jar"
   >
   <!-- An empty refresh value disables the background refresh thread -->
   <param name="RefreshTime" value="">
   <param name="SessionId" value="<%=request.getSession().getId()%>">
   <param name="PMJMXName" value="jboss.admin:service=PluginManager">
</applet>
</p>

</body>

</html>
