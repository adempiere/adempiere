<%@page contentType="text/html"
   import="java.net.*,java.util.*,javax.management.*,javax.management.modelmbean.*,
   org.jboss.jmx.adaptor.control.Server,
   org.jboss.jmx.adaptor.control.AttrResultInfo,
   org.jboss.jmx.adaptor.model.*,
   java.lang.reflect.Array"
%>
<%! public String fixDescription(String desc)
    {
      if (desc == null || desc.equals(""))
      {
        return "(no description)";
      }
      return desc;
    }
%>
<html>
<head>
   <title>MBean Inspector</title>
   <link rel="stylesheet" href="style_master.css" type="text/css">
   <meta http-equiv="cache-control" content="no-cache">
</head>
<body>

<jsp:useBean id='mbeanData' class='org.jboss.jmx.adaptor.model.MBeanData' scope='request'/>

<%
   ObjectName objectName = mbeanData.getObjectName();
   String objectNameString = mbeanData.getName();
   MBeanInfo mbeanInfo = mbeanData.getMetaData();
   MBeanAttributeInfo[] attributeInfo = mbeanInfo.getAttributes();
   MBeanOperationInfo[] operationInfo = mbeanInfo.getOperations();
%>

<table width="100%">
   <table>
      <tr>
         <td><img src="images/logo.gif" align="left" border="0" alt="JBoss"></td>
         <td valign="middle"><h1>JMX MBean View</h1></td>
      <tr/>
   </table>
   <ul>
   <table>
      <tr>
         <td>MBean Name:</td>
         <td><b>Domain Name:</b></td>
         <td><%= objectName.getDomain() %></td>
      </tr>
<%
   Hashtable properties = objectName.getKeyPropertyList();
   Iterator it = properties.keySet().iterator();
   while( it.hasNext() )
   {
      String key = (String) it.next();
      String value = (String) properties.get( key );
%>
      <tr><td></td><td><b><%= key %>: </b></td><td><%= value %></td></tr>
<%
   }
%>
      <tr><td>MBean Java Class:</td><td colspan="3"><jsp:getProperty name='mbeanData' property='className'/></td></tr>
   </table>
</ul>
<table cellpadding="5">
   <tr>
      <td><a href='HtmlAdaptor?action=displayMBeans'>Back to Agent View</a></td>
	  <td>
      <td><a href='HtmlAdaptor?action=inspectMBean&name=<%= URLEncoder.encode(request.getParameter("name")) %>'>Refresh MBean View</a></td>
   </tr>
</table>

<hr>
<h3>MBean description:</h3>
<%= fixDescription(mbeanInfo.getDescription())%>

<hr>
<h3>List of MBean attributes:</h3>

<form method="post" action="HtmlAdaptor">
   <input type="hidden" name="action" value="updateAttributes">
   <input type="hidden" name="name" value="<%= objectNameString %>">
	<table cellspacing="1" cellpadding="1" border="1">
		<tr class="AttributesHeader">
		    <th>Name</th>
		    <th>Type</th>
		    <th>Access</th>
		    <th>Value</th>
		    <th>Description</th>
		</tr>
<%
   boolean hasWriteable = false;
   for(int a = 0; a < attributeInfo.length; a ++)
   {
      MBeanAttributeInfo attrInfo = attributeInfo[a];
      String attrName = attrInfo.getName();
      String attrType = attrInfo.getType();
      AttrResultInfo attrResult = Server.getMBeanAttributeResultInfo(objectNameString, attrInfo);
      String attrValue = attrResult.getAsText();
      String access = "";
      if( attrInfo.isReadable() )
         access += "R";
      if( attrInfo.isWritable() )
      {
         access += "W";
         hasWriteable = true;
      }
      String attrDescription = fixDescription(attrInfo.getDescription());
%>
		<tr>
		    <td><%= attrName %></td>
		    <td><%= attrType %></td>
		    <td><%= access %></td>
          <td>
<%
      if( attrInfo.isWritable() )
      {
         String readonly = attrResult.editor == null ? "readonly" : "";
         if( attrType.equals("boolean") || attrType.equals("java.lang.Boolean") )
         {
            // Boolean true/false radio boxes
            Boolean value = Boolean.valueOf(attrValue);
            String trueChecked = (value == Boolean.TRUE ? "checked" : "");
            String falseChecked = (value == Boolean.FALSE ? "checked" : "");
%>
            <input type="radio" name="<%= attrName %>" value="True" <%=trueChecked%>>True
            <input type="radio" name="<%= attrName %>" value="False" <%=falseChecked%>>False
<%
         }
         else if( attrInfo.isReadable() )
         {  // Text fields for read-write string values
%>
          <input type="text" name="<%= attrName %>" value="<%= (attrValue != null ? attrValue : "") %>" <%= readonly %>>

<%
         }
         else
         {  // Empty text fields for write-only
%>
		    <input type="text" name="<%= attrName %>" <%= readonly %>>
<%
         }
      }
      else
      {
         if( attrType.equals("[Ljavax.management.ObjectName;") )
         {
            // Array of Object Names
            ObjectName[] names = (ObjectName[]) Server.getMBeanAttributeObject(objectNameString, attrName);
            if( names != null )
            {
%>
                  <table>
<%
               for( int i = 0; i < names.length; i++ )
               {
%>
                  <tr><td>
                  <a href="HtmlAdaptor?action=inspectMBean&name=<%= URLEncoder.encode(( names[ i ] + "" )) %>"><%= ( names[ i ] + "" ) %></a>
                  </td></tr>
<%
               }
%>
                  </table>
<%
            }
         }
         // Array of some objects
         else if( attrType.endsWith("[]"))
         {
            Object arrayObject = Server.getMBeanAttributeObject(objectNameString, attrName);
            if (arrayObject != null) {
%>
                  <table>
<%
               for (int i = 0; i < Array.getLength(arrayObject); ++i) {
%>
                  <tr><td><%=Array.get(arrayObject,i)%></td></tr>
<%
               }
%>
                  </table>
<%
            } 
            
         }
         else
         {
            // Just the value string
%>
		    <%= attrValue %>
<%
         }
      }
      if( attrType.equals("javax.management.ObjectName") )
      {
         // Add a link to the mbean
         if( attrValue != null )
         {
%>
         <a href="HtmlAdaptor?action=inspectMBean&name=<%= URLEncoder.encode(attrValue) %>">View MBean</a>
<%
         }
      }
%>
         </td>
         <td><%= attrDescription%></td>
		</tr>
<%
   }
%>
	</table>
<% if( hasWriteable )
   {
%>
	<input type="submit" value="Apply Changes">
<%
   }
%>
</form>

<hr>
<h3>List of MBean operations:</h3>
<%
   for(int a = 0; a < operationInfo.length; a ++)
   {
      MBeanOperationInfo opInfo = operationInfo[a];
      boolean accept = true;
      if (opInfo instanceof ModelMBeanOperationInfo)
      {
         Descriptor desc = ((ModelMBeanOperationInfo)opInfo).getDescriptor();
         String role = (String)desc.getFieldValue("role");
         if ("getter".equals(role) || "setter".equals(role))
         {
            accept = false;
         }
      }
      if (accept)
      {
         MBeanParameterInfo[] sig = opInfo.getSignature();
%>
<form method="post" action="HtmlAdaptor">
   <input type="hidden" name="action" value="invokeOp">
   <input type="hidden" name="name" value="<%= objectNameString %>">
   <input type="hidden" name="methodIndex" value="<%= a %>">
   <hr align='left' width='80'>
   <h4><%= opInfo.getReturnType() + " " + opInfo.getName() + "()" %></h4>
   <p><%= fixDescription(opInfo.getDescription())%></p>
<%
         if( sig.length > 0 )
         {
%>
	<table cellspacing="2" cellpadding="2" border="1">
		<tr class="OperationHeader">
			<th>Param</th>
			<th>ParamType</th>
			<th>ParamValue</th>
			<th>ParamDescription</th>
		</tr>
<%
            for(int p = 0; p < sig.length; p ++)
            {
               MBeanParameterInfo paramInfo = sig[p];
               String pname = paramInfo.getName();
               String ptype = paramInfo.getType();
               if( pname == null || pname.length() == 0 || pname.equals(ptype) )
               {
                  pname = "arg"+p;
               }
%>
		<tr>
			<td><%= pname %></td>
		   <td><%= ptype %></td>
         <td> 
<%
                if( ptype.equals("boolean") || ptype.equals("java.lang.Boolean") )
                {
                   // Boolean true/false radio boxes
%>
            <input type="radio" name="arg<%= p%>" value="True"checked>True
            <input type="radio" name="arg<%= p%>" value="False">False
<%
                 }
                 else
                 {
%>
            <input type="text" name="arg<%= p%>">
<%
                  }
%>
         </td>
         <td><%= fixDescription(paramInfo.getDescription())%></td>
		</tr>
<%
               }
%>
	</table>
<%
         }
%>
	<input type="submit" value="Invoke">
</form>
<%
      }
   }
%>
</td></tr>
</table>
</body>
</html>
