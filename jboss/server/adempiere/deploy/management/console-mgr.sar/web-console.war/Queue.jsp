<%@ page import="org.jboss.mq.*"%>
<%@ page import="org.jboss.mq.server.*"%>
<%@ page import="java.text.DateFormat"%>
<%@ page import="java.util.Date"%>
<%@ taglib uri="/webconsole" prefix="jb" %>
<jb:mbean id="queue" intf="org.jboss.mq.server.jmx.QueueMBean"/>
<% 	
	String resetUrl = response.encodeURL(request.getRequestURI()) + "?doReset=true&ObjectName=" + java.net.URLEncoder.encode(request.getParameter("ObjectName"));
	String myUrl = response.encodeURL(request.getRequestURI()) + "?" + "&ObjectName=" + java.net.URLEncoder.encode(request.getParameter("ObjectName"));	
	MessageCounter counter = queue.getMessageCounter()[0];
	
	//check reset first
	String doReset = request.getParameter("doReset");
	if (doReset != null && doReset.equals("true"))
    {
   	 	counter.resetCounter();
    }
    
	String lastModified = "N/A";
	DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM);
	if (counter.getLastUpdate() > 0)
	{		
		lastModified = dateFormat.format(new Date(counter.getLastUpdate()));
	}
	 
    
%>

<?xml version="1.0" encoding="iso-8859-1"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>JBoss Management Console - JMS Queue</title>
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

<!-- content begin -->
	<center>
	<div id="content">
		<div class="content_block" style="width: 95%; height: 95%">
			<h3>JMS Queue</h3>
			<h4>Name</h4>
				<p><%=queue.getQueueName()%></p>
			<h4>JNDI Name</h4>
				<p><%=queue.getJNDIName()%></p>		
			<h4>Receiver Count</h4>
				<p><%=queue.getReceiversCount()%></p>													
			<h4>Message statistics:</h4>
			<table border="1" cellpadding="0" cellspacing="0" class="data_table" bordercolor="#111111" width="95%" align="center" id="AutoNumber1">
                  <tr>
                    <td width="33%" >
                    <h4>Queue Depth:</h4>
                    </td>      
                    <td width="33%" >
                    <h4>Queue Depth Delta:</h4>
                    </td>                                     
                    <td width="33%">
                    <h4>Maximum Depth:</h4>
                    </td>                    
                  </tr>	
                  <tr align="center">
                  	<td><%=queue.getQueueDepth()%></td>  
                  	<td><%=counter.getDepthDelta()%></td>               	
                  	<td><%=queue.getMaxDepth()%></td>                  	
                  </tr>
            </table>
            <p>&nbsp;</p>
			<p>&nbsp;</p>
            <h4>Message statistics since startup or reset:</h4>
			<table border="1" cellpadding="0" cellspacing="0" class="data_table" bordercolor="#111111" width="95%" align="center" id="AutoNumber1">
                  <tr>
                    <td width="33%" >
                    <h4>Message Count:</h4>
                    </td>                    
                    <td width="33%">
                    <h4>Message Count Delta:</h4>                    
                    </td>                    
                    <td width="33%">
                    <h4>Last Message Add Date:</h4>
                    </td>
                  </tr>	
                  <tr align="center">                  	
                  	<td><%=counter.getCount()%></td>
                  	<td><%=counter.getCountDelta()%></td>
                  	<td><%=lastModified%></td>
                  </tr>
            </table>
            <p align="center"><a href="<%=resetUrl%>">Reset Stats</a> / <a href="<%=myUrl%>">Refresh Stats</a></p>            					
            <p>&nbsp;</p>
			<p>&nbsp;</p>						
			              
		</div>
		<div class="spacer"><hr/></div>
	</div>
	</center>
<!-- content end -->

<!-- header end -->
<hr class="hide"/>

<hr class="hide"/>
<!-- footer begin -->
	<div id="footer">
		<div id="credits">JBoss Management Console</div>
		<div id="footer_bar">&nbsp;</div>
	</div>
<!-- footer end -->
</body>
</html>