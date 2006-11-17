<%@ page import="java.util.Iterator,
                 java.util.Map,
                 org.jboss.aop.metadata.SimpleMetaData,
                 org.jboss.aop.metadata.MetaDataResolver" %>
                 <table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="95%" align="center" id="AutoNumber1">
                  <tr>
                    <td width="50%" align="center" colspan="4">
                    <h4 style="text-align: center"><font size="3"><%=group%></font></h4>
                    </td>
                  </tr>
                  <tr>
                    <td>
                    <h4>Attribute Name</h4>
                    </td>
                    <td>
                    <h4>Type</h4>
                    </td>
                    <td>
                    <h4>Value (as String)</h4>
                    </td>
                    <td>
                    <h4>Serialization</h4>
                    </td>
                  </tr>
<%
    boolean hasValues = false;
         
    Iterator it = groupAttrs.entrySet().iterator();
    while (it.hasNext()) 
    {
       Map.Entry entry = (Map.Entry)it.next();
       String attr = (String)entry.getKey();
       if (!attr.equals(MetaDataResolver.EMPTY_TAG))
       {
          hasValues = true;
          SimpleMetaData.MetaDataValue value = (SimpleMetaData.MetaDataValue)entry.getValue();
%>
                  <tr>
                    <td><font size="1"><%=attr%></font>
                    <td><font size="1"><%=value.value.getClass().getName()%></font>
                    <td><font size="1"><%=value.value.toString()%></font>
                    <td><font size="1"><%=value.type%></font>
                    </td>
                  </tr>
<%     }
    } 
    
    if (!hasValues)
    {
%>    
                  <tr>
                     <td colspan="4">empty</td>
                  </tr>
<%  }
    
%>
	
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
		<div id="credits">JBoss™ Management Console</div>
		<div id="footer_bar">&nbsp;</div>
	</div>
<!-- footer end -->
</body>
</html>