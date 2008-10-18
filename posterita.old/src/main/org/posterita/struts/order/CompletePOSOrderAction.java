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
 * 
 * Created on 27-Mar-2006
 */


package org.posterita.struts.order;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.compiere.model.MOrder;

import org.posterita.Constants;
import org.posterita.beans.WebDocumentBean;
import org.posterita.businesslogic.OrderReferenceManager;
import org.posterita.businesslogic.POSManager;
import org.posterita.core.TmkJSPEnv;
import org.posterita.exceptions.ApplicationException;
import org.posterita.exceptions.OperationException;


public class CompletePOSOrderAction extends ViewOrderAction
{

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws OperationException, ApplicationException
	{
		ActionForward fwd = init(mapping, form, request, response);

		if (fwd != null)
			return fwd;		
		Properties ctx = TmkJSPEnv.getCtx(request);
		
		WebDocumentBean webBean = null;
	
		Integer posOrderId=(Integer) request.getSession().getAttribute(Constants.CURRENT_POS_ORDER_ID);
		
		request.getSession().setAttribute(Constants.CURRENT_POS_ORDER_ID,posOrderId);
	      
	    MOrder completedPOSOrder=POSManager.completePOSOrder(ctx,posOrderId, null);
	    webBean = OrderReferenceManager.getWebOrderBean(ctx,completedPOSOrder);
	    

		request.getSession().setAttribute(Constants.WEB_ORDER_BEAN, webBean);

		return super.execute(mapping,form,request,response);
	}

    
}
