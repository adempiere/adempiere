/**
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
 * 12-Dec-2006 10:00:02 by praveen
 *
 */

package org.posterita.struts.pos;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.posterita.beans.ProductStatusBean;
import org.posterita.businesslogic.ProductManager;
import org.posterita.core.TmkJSPEnv;

/**
 * This action is called using AJAX form the product panel
 */
public class SearchProductAction extends Action
{
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		String productName = request.getParameter("productName");
		
		productName = productName.replaceAll("#", "&#");
		
		Properties ctx = TmkJSPEnv.getCtx(request); 
				
		ArrayList productList =ProductManager.searchProductsByName(ctx,productName);
		ProductStatusBean bean = null;
		StringWriter sw = new StringWriter();
		PrintWriter out = new PrintWriter(sw);

		if(productList == null || productList.size() == 0)
		{
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
		
		response.getOutputStream().print(sw.toString());
		response.getOutputStream().flush();
		response.getOutputStream().close();		
		
		return null;
	}
}
