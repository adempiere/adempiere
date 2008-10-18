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

import org.posterita.beans.CustomerBean;
import org.posterita.businesslogic.administration.VendorManager;
import org.posterita.core.TmkJSPEnv;
import org.posterita.core.bean.ElementBean;
import org.posterita.core.businesslogic.ElementManager;

/**
 * This action is called using AJAX form the vendor panel
 */
public class SearchVendorAction extends Action
{
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		Properties ctx = TmkJSPEnv.getCtx(request);
		String customerName = request.getParameter("customerQuery");
		
		ArrayList customerList = VendorManager.searchVendors(TmkJSPEnv.getCtx(request),customerName,true);	
		CustomerBean bean = null;
		
		ElementBean elementBean = null;	
		elementBean = ElementManager.getMsg(ctx, "not.found");
		
		StringWriter sw = new StringWriter();
		PrintWriter out = new PrintWriter(sw);
		
		if (customerList == null || customerList.size() == 0)
		{
			out.print("<ul>");
			out.print("<li value=''></li>");
			out.print("</ul>");
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
				
				String firstname = bean.getPartnerName();
				String lastname = bean.getSurname();
				
				String vendorName = "";
				if(firstname != null)
					vendorName += firstname+" ";
					
				if(lastname != null)
					vendorName += lastname;
				
				out.print("<li value='"+ bean.getBpartnerId() +"' name ='"+vendorName+"'>");
				out.print(vendorName);
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
		
		response.getOutputStream().print(sw.toString());
		response.getOutputStream().flush();
		response.getOutputStream().close();		
		
		return null;
	}
}
