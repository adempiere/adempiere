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
 * Created on Aug 18, 2005 by Alok
 */

package org.posterita.struts.bPartner;

import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.compiere.util.Trx;
import org.posterita.core.TmkJSPEnv;

import org.posterita.Constants;
import org.posterita.beans.BPartnerBean;
import org.posterita.businesslogic.administration.BPartnerManager;
import org.posterita.core.TrxPrefix;
import org.posterita.exceptions.ApplicationException;
import org.posterita.exceptions.OperationException;
import org.posterita.struts.core.BaseDispatchAction;
import org.posterita.struts.core.DefaultForm;

public class ViewBPartnerAction extends BaseDispatchAction
{
	public static final String GET_ALL_BPARTNERS = "getAllBpartners";
	public ActionForward getAllBpartners(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException
	{
		ActionForward fwd = init(mapping,form,request,response);
	    if (fwd!=null)
	        return fwd;
	    
	    Properties ctx = TmkJSPEnv.getCtx(request);
	    
	    Trx trx = Trx.get(TrxPrefix.getPrefix(), true);
	    
	    
	    ArrayList bpartners = new ArrayList();
	    
		try
		{
			trx.start();
			bpartners = BPartnerManager.getAllBpartners(ctx, trx.getTrxName());
			trx.commit();
		}
		catch (OperationException e)
		{
			trx.rollback();
			postGlobalError("error.getall.partners", request);
			mapping.getInputForward();
		}
	    finally
	    {
	    	trx.close();
	    }
	    
	    request.getSession().setAttribute(Constants.ALLBUSINESSPARTNERS, bpartners);
	    
		return mapping.findForward(GET_ALL_BPARTNERS);
		
	}
	
	public static final String VIEW_BPARTNER_DETAILS = "viewBpartnerDetails";
	public ActionForward viewBpartnerDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException
	{
		ActionForward fwd = init(mapping, form, request, response);
		if (fwd!=null)
			return fwd;
		
		Properties ctx = TmkJSPEnv.getCtx(request);
		
		DefaultForm df = (DefaultForm) form;
		BPartnerBean bean = (BPartnerBean) df.getBean();
		
		ArrayList bpartners = (ArrayList) request.getSession().getAttribute(Constants.ALLBUSINESSPARTNERS);
		
		Trx trx = Trx.get(TrxPrefix.getPrefix(), true);
		
		
		try
		{
			trx.start();
			
			if(bpartners == null)
				bpartners = BPartnerManager.getAllBpartners(ctx, trx.getTrxName());
			
			trx.commit();
		}
		catch (OperationException e)
		{
			trx.rollback();
			postGlobalError("error.get.partner.detail", request);
			mapping.getInputForward();
		}
		finally
		{
			trx.close();
		}
		
	    try
        {
            bean = BPartnerManager.getBpartner(ctx, bean.getBpartnerId(), trx.getTrxName());
        }
        catch (OperationException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		
		request.getSession().setAttribute(Constants.BUSINESSPARTNERSDETAILS, bean);
		
		
		return mapping.findForward(VIEW_BPARTNER_DETAILS);
		
	}
	
}
