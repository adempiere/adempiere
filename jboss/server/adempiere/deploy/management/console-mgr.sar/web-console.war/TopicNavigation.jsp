<%@ page import="java.net.URLEncoder" %>
<%@ taglib uri="/webconsole" prefix="jb" %>
<% 
	String objParameter = "ObjectName=" + URLEncoder.encode(request.getParameter("ObjectName")); 
%>
<jb:navigation selectedTabName="<%=request.getParameter("selectedTabName")%>">
		<jb:tab name="JMS Topic" href="<%="Topic.jsp?" + objParameter%>" />
		<jb:tab name="Subscriptions" href="<%="TopicSubscriptions.jsp?" + objParameter%>" />	
</jb:navigation>