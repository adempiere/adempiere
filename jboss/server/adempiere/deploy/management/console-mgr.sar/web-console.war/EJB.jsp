<%@ taglib uri="/webconsole" prefix="jb" %>
<jb:mbean id="ejb" intf="org.jboss.management.j2ee.StatelessSessionBeanMBean" />

<%
   String ejbName = new javax.management.ObjectName(request.getParameter("ObjectName")).getKeyProperty ("name");
   String containerUrl = "jboss.j2ee:service=EJB,jndiName=" + ejbName;
   containerUrl = java.net.URLEncoder.encode(containerUrl);
   containerUrl = "../jmx-console/HtmlAdaptor?action=inspectMBean&name=" + containerUrl;
%>
<html>
<META HTTP-EQUIV="expires" CONTENT="0"/>
<head>
<title>EJB: <%=ejbName%></title>
</head>

  <body>
  
  <h1><center>EJB '<%=ejbName%>'</center></h1>
  
  <p/>
  <p/>
  
  <table border="1">
   <tr>
      <td><b>Management Object Name:</b></td>
   </tr>
   <tr>
      <td><%=ejb.getobjectName()%>&nbsp;</td>
   </tr>
   <tr>
      <td><b>Provides Statistics:</b></td>
   </tr>
   <tr>
      <td><%=ejb.isstatisticsProvider()%>&nbsp;</td>
   </tr>
  </table>

   <a href="<%=containerUrl%>">Visit associated container MBean...</a>

  </body>

</html>