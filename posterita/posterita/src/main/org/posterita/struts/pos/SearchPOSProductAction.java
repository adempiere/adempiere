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
 * Created on 25-Jul-2005 by alok
 *
 */

package org.posterita.struts.pos;

import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.posterita.Constants;
import org.posterita.businesslogic.administration.ProductManager;
import org.posterita.core.TmkJSPEnv;

public class SearchPOSProductAction extends POSDispatchAction
{
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
		String productName = request.getParameter("productName");
		
		Properties ctx = TmkJSPEnv.getCtx(request);
		
		ArrayList productList = ProductManager.getProductList(ctx,productName,true,true);
		
		request.getSession().setAttribute(Constants.PRODUCT_LIST,productList);
		
		return mapping.findForward("displayProducts");
	}
}
