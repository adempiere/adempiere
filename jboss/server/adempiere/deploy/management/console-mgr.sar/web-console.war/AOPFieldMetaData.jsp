<%@ taglib uri="/webconsole" prefix="jb" %>
<%@ page import="org.jboss.aop.*,java.util.*"%>
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
    String group = request.getParameter("group");
    String field = request.getParameter("field");	
    ClassAdvisor advisor = (ClassAdvisor)AspectManager.instance().getAdvisor(classname);
    HashMap groupAttrs = advisor.getFieldMetaData().getFieldMetaData(field).tag(group);
%>
<hr class="hide"/>
	<center>
	<div id="content">
		<div class="content_block" style="width: 100%">
			<h3>Metadata for field <%=field%></h3>
	    <p>&nbsp;</p>
<%@ include file="AOPMetaData.jsp" %>
