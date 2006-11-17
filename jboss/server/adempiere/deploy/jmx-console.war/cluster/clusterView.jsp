<%@page contentType="text/html"
   import="java.net.*,java.io.*"
%>
<html>
<head><title>Clustered JBoss Management Console</title>
<link rel="stylesheet" href="../style_master.css" type="text/css">
</head>
<body>
<table>
<tr><td>
<h2>Cluster View Bootstrap</h2>
<a href="bootstrap.html" target="ClusterNodeView">Reinvoke Bootstrap</a>

<h2>Loaded Clusters</h2>
<%
   String partition = (String) request.getAttribute("partition");
%>
   <h3 class='DomainName'><%= partition %></h2>
   <ul class='MBeanList'>
<%
   String[] partitionHosts = (String[]) request.getAttribute("partitionHosts");
   for(int h = 0; h < partitionHosts.length; h ++)
   {
      String host = partitionHosts[h];
      String hostname = "";

      try
      {
         hostname = InetAddress.getByName(host).getHostName();
      }
      catch(IOException e)  {}

      String hostURL = "http://"+host+":8080/jmx-console//HtmlAdaptor?action=displayMBeans";
%>
      <li><a href="<%= hostURL%>" target="ClusterNodeView"><%= hostname %></a></li>
<%
   }
%>
   </ul>
</td></tr>
</table>

</body>
</html>
