<%@ page language="java"%>
<%@ page import="java.util.Properties" %>
<%@ page import="org.posterita.core.TmkJSPEnv" %>
<%@ page import="org.posterita.core.ImageServer" %>
<%@ page import="org.posterita.businesslogic.ProductManager" %>
<%
	Properties ctx = TmkJSPEnv.getCtx(request);
		
	Integer product_id = new Integer(request.getParameter("productId"));
	String size = request.getParameter("size");
	
	int attachment_id = ProductManager.getAttachment(ctx,product_id.intValue(), null).get_ID();
	
	boolean sent = ImageServer.sendImage(ctx,attachment_id,size,response);
	
	if(!sent)
		ImageServer.sendDefaultImage(response);
%>
