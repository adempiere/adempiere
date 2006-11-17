<%@ page import="java.net.URLEncoder,
				org.jboss.mq.*,
				org.jboss.mq.server.*"
%>
<%@ taglib uri="/webconsole" prefix="jb" %>
<jb:mbean id="topic" intf="org.jboss.mq.server.jmx.TopicMBean"/>
<% 	
	String objParameter = "&ObjectName=" + URLEncoder.encode(request.getParameter("ObjectName"));
	String myUrl = response.encodeURL(request.getRequestURI()) + "?" + objParameter;			
%>

<?xml version="1.0" encoding="iso-8859-1"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>JBoss Management Console - JMS Topic</title>
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
	<table width="95%" height="95%" border="0" cellspacing="0" cellpadding="0">
		<tr align="center" valign="bottom">
			<td height="24">
				<jsp:include page="TopicNavigation.jsp" >
					<jsp:param name="selectedTabName" value="JMS Topic"/>
				</jsp:include>
			</td>
		</tr>
		<tr>
		  <td colspan="5" valign="top" class="container">			
			<h4>Name</h4>
				<p><%=topic.getTopicName()%></p>
			<h4>JNDI Name</h4>
				<p><%=topic.getJNDIName()%></p>																	
			<h4>Message statistics:</h4>
			<table border="1" cellpadding="0" cellspacing="0" class="data_table" bordercolor="#111111" width="95%" align="center" id="AutoNumber1">
                  <tr>
                    <td>
                    <h4>All Message Count:</h4>
                    </td>      
                    <td>
                    <h4>Durable Message Count:</h4>
                    </td>                                     
                    <td>
                    <h4>Non Durable Message Count:</h4>
                    </td>                    
                    <td>
                    <h4>Maximum Depth:</h4>
                    </td>                    
                  </tr>	
                  <tr align="center">
                  	<td><%=topic.getAllMessageCount()%></td>  
                  	<td><%=topic.getDurableMessageCount()%></td>               	
                  	<td><%=topic.getNonDurableMessageCount()%></td>    
                  	<td><%=topic.getMaxDepth()%></td>	
                  </tr>
            </table>
            <p>&nbsp;</p>
			<p>&nbsp;</p>
            <h4>Subscription statistics:</h4>
			<table border="1" cellpadding="0" cellspacing="0" class="data_table" bordercolor="#111111" width="95%" align="center" id="AutoNumber1">
                  <tr>
                    <td width="33%" >
                    <h4>All Subscriptions Count:</h4>
                    </td>                    
                    <td width="33%">
                    <h4>Durable Subcriptions Count</h4>                    
                    </td>                    
                    <td width="33%">
                    <h4>Non Durable Subscriptions Count:</h4>
                    </td>
                  </tr>	
                  <tr align="center">                  	
                  	<td><%=topic.getAllSubscriptionsCount()%></td>
                  	<td><%=topic.getDurableSubscriptionsCount()%></td>
                  	<td><%=topic.getNonDurableSubscriptionsCount()%></td>
                  </tr>
            </table>
            <p align="center"><a href="<%=myUrl%>">Refresh</a></p>            					
            <p>&nbsp;</p>
			<p>&nbsp;</p>						
			              		
		  </td>
	    </tr>		
	</table>  
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