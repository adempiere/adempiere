<%@page contentType="text/html"
   import="java.net.*"
%>
<html>
<head>
   <title>Operation Results</title>
   <link rel="stylesheet" href="style_master.css" type="text/css">
   <meta http-equiv="cache-control" content="no-cache">
</head>
<body>

<jsp:useBean id='opResultInfo' type='org.jboss.jmx.adaptor.control.OpResultInfo' scope='request'/>

<table width="100%">
   <table>
      <tr>
         <td><img src="images/logo.gif" align="left" border="0" alt="JBoss"></td>
         <td valign="middle"><h1>JMX MBean Operation Result <code><%= opResultInfo.name%>()</code></h1></td>
	  <tr/>
   </table>

<tr><td>


<table cellpadding="5">
   <tr>
      <td><a href='HtmlAdaptor?action=displayMBeans'>Back to Agent View</a></td>
      <td>
      <td><a href='HtmlAdaptor?action=inspectMBean&name=<%= URLEncoder.encode(request.getParameter("name")) %>'>Back to MBean View</a></td>
      <td>
      <td><a href=
<%
	out.print("'HtmlAdaptor?action=invokeOpByName");
	out.print("&name=" + URLEncoder.encode(request.getParameter("name")));
	out.print("&methodName=" + opResultInfo.name );

	for (int i=0; i<opResultInfo.args.length; i++)
    {
		out.print("&argType=" + opResultInfo.signature[i]);
		out.print("&arg" + i + "=" + opResultInfo.args[i]);
	}

	out.println("'>Reinvoke MBean Operation");
%>
	  </a></td>
   </tr>
</table>


<hr>
   <span class='OpResult'>
<%
   if( opResultInfo.result == null )
   {
%>
   Operation completed successfully without a return value.
<%
   }
   else
   {
      String opResultString = opResultInfo.result.toString();
      boolean hasPreTag = opResultString.startsWith("<pre>");
      if( hasPreTag == false )
         out.println("<pre>");
      out.println(opResultString);
      if( hasPreTag == false )
         out.println("</pre>");
   }
%>
   </span>
</td></tr>
</table>
</body>
</html>
