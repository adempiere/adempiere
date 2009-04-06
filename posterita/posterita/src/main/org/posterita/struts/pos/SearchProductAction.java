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
import java.util.logging.Level;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.compiere.util.CLogger;
import org.posterita.beans.ProductStatusBean;
import org.posterita.businesslogic.administration.ProductManager;
import org.posterita.businesslogic.POSTerminalManager;
import org.posterita.core.TmkJSPEnv;

/**
 * This action is called using AJAX form the product panel
 */
public class SearchProductAction extends Action
{
    private static final CLogger logger = CLogger.getCLogger(SearchProductAction.class);
    
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
	    Properties ctx = TmkJSPEnv.getCtx(request); 
		String description = request.getParameter("description");
		String productName = request.getParameter("productName");
		String strPriceListId = request.getParameter("priceListId");
		
		int priceListId = 0;
		if (strPriceListId != null && strPriceListId.trim().length() != 0)
		{
    		try
    		{
    		    priceListId = Integer.parseInt(strPriceListId);
    		}
    		catch (Exception ex)
    		{
    		    logger.log(Level.SEVERE, "Could not parse Price List Id, priceListId: " + strPriceListId, ex);
    		}
		}
		else
		{
		    priceListId = POSTerminalManager.getSOPriceListId(ctx);
		}
			
		int adOrgId = POSTerminalManager.getOrgId(ctx);
		int warehouseId = POSTerminalManager.getWarehouseId(ctx);
		ArrayList<ProductStatusBean> productList = null;
		
		if(productName != null)
		{
			productName = productName.replaceAll("#", "&#");
			productList = ProductManager.searchProducts(ctx, adOrgId, productName, "", priceListId, warehouseId, null);
		}		
		else if(description != null)
		{
			description = description.replaceAll("#", "&#");
			productList = ProductManager.searchProducts(ctx, adOrgId, "", description, priceListId, warehouseId, null);
		}
		else
		{
			productList = ProductManager.searchProducts(ctx, adOrgId, "", "", priceListId, warehouseId, null);
		}
		
		ProductStatusBean bean = null;
		StringWriter sw = new StringWriter();
		PrintWriter out = new PrintWriter(sw);

		if(productList == null || productList.size() == 0)
		{
			out.print("<div class='notfound'><label><b>Not Found - " + productName + "</b><br>Please check if product is on price list</label></div>");	
		}
		else
		{
			int size = productList.size();
				
			size = (size > productList.size())? productList.size() : size;
			
			int i = 0;
			
			if(size > 0)
			{
				out.print("<ul>");
							
				for(i=0;i< size;i++)
				{
					bean = (ProductStatusBean)productList.get(i);
					
					if(i % 2 == 0)
					{
    					 out.print("<li productId='"+ bean.getProductId() + "' barcode='" + bean.getBarCode() +"' description = '" + bean.getDescription() + "'>");
                            
                         out.print(bean.getProductName() + "-" + bean.getDescription()+">qty:"+bean.getQtyOnHand()+ ">price:"+bean.getPriceStandard());
                     
                         out.print("</li>"); 	
					}
					else
					{
					    out.print("<li productId='"+ bean.getProductId() + "' barcode='" + bean.getBarCode() +"' description = '" + bean.getDescription() + "'>");
                        
                        out.print(bean.getProductName() + "-" + bean.getDescription()+">qty:"+bean.getQtyOnHand()+ ">price:"+bean.getPriceStandard());
                    
                        out.print("</li>");    
					    
					}
					
					 
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
