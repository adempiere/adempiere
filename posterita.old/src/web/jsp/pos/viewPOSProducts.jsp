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



<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Properties" %>
<%@ page import="org.posterita.core.TmkJSPEnv" %>
<%@ page import="org.posterita.businesslogic.ProductManager" %>
<%@ page import="org.posterita.beans.ProductStatusBean" %>
<%

String productName = request.getParameter("productName");
Properties ctx = TmkJSPEnv.getCtx(request);
		
ArrayList productList =ProductManager.searchProductsByName(ctx,productName);
ProductStatusBean bean = null;

if(productList == null || productList.size() == 0)
{
	
	//out.print("<ul>");
	//out.print("<li value=''></li>");
	//out.print("</ul>");
	out.print("<div class='notfound'><label><b>Not Found - " + productName + "</b></label></div>");	
}
else
{
	int size = 15;
		
	size = (size > productList.size())? productList.size() : size;
	
	if(size > 0)
	{
		out.print("<ul>");
	
		for(int i=0;i< size;i++)
		{
			bean = (ProductStatusBean)productList.get(i);
			
			out.print("<li productId='"+ bean.getProductId() + "' barcode='" + bean.getBarCode() +"'>");
			out.print(bean.getProductName());
			out.print("</li>");		
		}
		
		out.print("</ul>");		
	}
	
	out.print("<div><label><b>Displaying 1 to " + size + " of " + productList.size() + "</b></label></div>");
		
}
%>