<%--
 *  Product: Posterita Web-Based POS and Adempiere Plugin
 *  Copyright (C) 2007  Posterita Ltd
 *  This file is part of POSterita
 *  
 *  POSterita is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program; if not, write to the Free Software Foundation, Inc.,
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * @author Alok
--%>



<%@ page import="org.posterita.businesslogic.CustomerManager" %>
<%@ page import="org.posterita.core.TmkJSPEnv" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.posterita.beans.CustomerBean" %>
<%@ page import="java.util.Properties" %>
<%@ page import="org.posterita.core.businesslogic.ElementManager" %>
<%@ page import="org.posterita.core.bean.ElementBean" %>
<%
	Properties ctx = TmkJSPEnv.getCtx(request);
	
	String customerName = request.getParameter("customerQuery");
	ElementBean elementBean = null;	
	elementBean = ElementManager.getMsg(ctx, "not.found");
	
	ArrayList customerList = CustomerManager.searchCustomers(ctx,customerName,false);
	
	CustomerBean bean = null;
	
	if (customerList == null || customerList.size() == 0)
	{
		//out.print("<ul>");
		//out.print("<li value=''></li>");
		//out.print("</ul>");
		out.print("<div class='notfound'><label><b>" + elementBean.getName() + " - " + customerName + "</b></label></div>");
	}
	else
	{	
		int size = 15;		
		size = (size > customerList.size())? customerList.size() : size;	
					
		out.print("<ul>");
		
		for(int i=0;i<size;i++)
		{
			bean = (CustomerBean)customerList.get(i);
			
			String name = "";
			String firstname = bean.getPartnerName();
			String lastname = bean.getSurname();
			String bpartnerId = bean.getBpartnerId().toString();
			
			if(firstname != null)
				name = firstname+" ";
				
			if(lastname != null)
				name = name + lastname;					
			
			out.print("<li name='"+ name +"' value='"+ bpartnerId +"'>");
			out.print(name);
			out.print("</li>");
		}
		
		out.print("</ul>");
		
		elementBean = ElementManager.getMsg(ctx, "search.result.displaying");
		String msg = elementBean.getName();
		
		out.print("<div><label><b>");
		out.print(msg + " 1 ");
		
		elementBean = ElementManager.getMsg(ctx, "search.result.to");
		msg = elementBean.getName();
		
		out.print(msg + " " + size);
		
		elementBean = ElementManager.getMsg(ctx, "search.result.of");
		msg = elementBean.getName();
		
		out.print(" " + msg + " ");
		out.print(customerList.size() + " ");
		out.print("</b></label></div>");
	
	}
%>