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
import org.compiere.util.Trx;

import org.posterita.Constants;
import org.posterita.beans.VendorBean;
import org.posterita.businesslogic.VendorManager;
import org.posterita.core.TmkJSPEnv;
import org.posterita.core.TrxPrefix;
import org.posterita.exceptions.ApplicationException;
import org.posterita.exceptions.OperationException;
import org.posterita.form.VendorForm;
import org.posterita.struts.core.DefaultForm;

public class VendorAction  extends POSDispatchAction
{
	public static final String SAVE_VENDOR = "saveVendor";
	public 	ActionForward saveVendor(ActionMapping mapping, ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
	{
		
		ActionForward fwd=init(mapping,form,request,response);
		if (fwd!=null)
			return fwd;
		
		Properties ctx = TmkJSPEnv.getCtx(request);
		DefaultForm df = (DefaultForm) form;
		VendorBean bean = (VendorBean) df.getBean();
		
		Trx trx = Trx.get(TrxPrefix.getPrefix(), true);
		
		Integer partnerId = 0;
		
		if (bean.getBpartnerId() != null)
			partnerId = bean.getBpartnerId();
		
		try
		{
			trx.start();
			VendorManager.saveVendor(ctx, partnerId, bean, trx.getTrxName());
			trx.commit();
		}
		catch(OperationException ex)
		{
			trx.rollback();
			throw ex;
		}
		finally
		{
			trx.close();
		}
		request.setAttribute(Constants.VENDOR_DETAILS,bean);
		
		return mapping.findForward(SAVE_VENDOR);
	}
	
	public static final String VIEW_VENDOR = "viewVendorDetails";
    public ActionForward viewVendorDetails1(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd = init(mapping,form,request,response);
		
		if (fwd!=null)
			return fwd;
				
		Properties ctx = TmkJSPEnv.getCtx(request);
				
		VendorForm uf = (VendorForm) form;
        
        int vendorId = Integer.valueOf(uf.getBpartnerId()).intValue();
        
        VendorBean vendorBean = VendorManager.getVendor(ctx,vendorId);		
		
		request.getSession().setAttribute(Constants.VENDOR_DETAILS,vendorBean);
		
        return mapping.findForward(VIEW_VENDOR);        
    }
	
    
	public static final String INIT_SEARCH_VENDOR = "initSearchVendors";
	public 	ActionForward initSearchVendors(ActionMapping mapping, ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
	{
		ActionForward fwd=init(mapping,form,request,response);

		if (fwd!=null)
			return fwd;
		
		request.getSession().removeAttribute(Constants.VENDOR_LIST);
		
		return mapping.findForward(INIT_SEARCH_VENDOR);
	}
	
	
	public static final String SEARCH_VENDOR = "searchVendors";
	public 	ActionForward searchVendors(ActionMapping mapping, ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
	{
		ActionForward fwd=init(mapping,form,request,response);

		if (fwd!=null)
			return fwd;
		
		Properties ctx = TmkJSPEnv.getCtx(request);
		
		DefaultForm df = (DefaultForm) form;
		
		ArrayList vendorList = VendorManager.searchVendors(ctx, df.getName());
		
		request.getSession().setAttribute(Constants.VENDOR_LIST,vendorList);
		
		return mapping.findForward(SEARCH_VENDOR);
	}
	
	
	public static final String INIT_EDIT_VENDOR = "initEditVendor";
	public 	ActionForward initEditVendor(ActionMapping mapping, ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
	{
		
		ActionForward fwd=init(mapping,form,request,response);
		if (fwd!=null)
			return fwd;
		Properties ctx = TmkJSPEnv.getCtx(request);
		
		VendorForm f = (VendorForm) form;
		f.validate(mapping,request);
		
		VendorBean bean = (VendorBean) f.getBean();
		
		Integer bpartnerId = bean.getBpartnerId();
		if(bpartnerId==null)
		{
			throw new OperationException("Cannot edit vendor. Cause vendor id cannot be null!");
		}
		
		bean = VendorManager.getVendor(ctx,bpartnerId.intValue());
		f.populate(bean);		
		
		return mapping.findForward(INIT_EDIT_VENDOR);
	}
	
	
	public static final String ACTIVATE_VENDOR = "activateVendor";
	@SuppressWarnings("unchecked")
	public 	ActionForward activateVendor(ActionMapping mapping, ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
	{
		
		ActionForward fwd=init(mapping,form,request,response);
		if (fwd!=null)
			return fwd;
		Properties ctx = TmkJSPEnv.getCtx(request);
		
		VendorForm f = (VendorForm) form;
		f.validate(mapping,request); //copying the value from the form to the bean
		
		VendorBean bean = (VendorBean) f.getBean();
		
		Integer bpartnerId = bean.getBpartnerId();
		
		if(bpartnerId==null)
		{
			throw new OperationException("Cannot edit vendor. Cause: Vendor id cannot be null!");
		}
		
		Trx trx = Trx.get(TrxPrefix.getPrefix(), true);

		
		try
		{
			trx.start();
			VendorManager.activateVendor(ctx,bpartnerId.intValue(), trx.getTrxName());
			trx.commit();
		}
		catch (OperationException e)
		{
			trx.rollback();
			postGlobalError("error.activate.vendor", request);
			mapping.getInputForward();
		}
		finally
		{
			trx.close();
		}
		
		ArrayList list = (ArrayList) request.getSession().getAttribute(Constants.VENDOR_LIST);
		ArrayList<VendorBean> vendorBeanList =  list;
		
		ArrayList<VendorBean> newList = VendorManager.updateVendorListStatus(vendorBeanList, bpartnerId, true);
		
		request.getSession().setAttribute(Constants.VENDOR_LIST, newList);		
		
				
		return mapping.findForward(SEARCH_VENDOR);
	}
	
	public static final String DEACTIVATE_VENDOR = "deactivateVendor";
	@SuppressWarnings("unchecked")
	public 	ActionForward deactivateVendor(ActionMapping mapping, ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
	{
		
		ActionForward fwd=init(mapping,form,request,response);
		if (fwd!=null)
			return fwd;
		Properties ctx = TmkJSPEnv.getCtx(request);
		
		VendorForm f = (VendorForm) form;
		f.validate(mapping,request); //copying the value from the form to the bean
		
		VendorBean bean = (VendorBean) f.getBean();
		
		Integer bpartnerId = bean.getBpartnerId();
		if(bpartnerId==null)
		{
			throw new OperationException("Cannot edit vendor. Cause vendor id cannot be null!");
		}
		
		Trx trx = Trx.get(TrxPrefix.getPrefix(), true);
		
		try
		{
			trx.start();
			VendorManager.deactivateVendor(ctx,bpartnerId.intValue(), trx.getTrxName());
			trx.commit();
		}
		catch (OperationException e)
		{
			trx.rollback();
			postGlobalError("error.deactivate.vendor", request);
			mapping.getInputForward();
		}
		finally
		{
			trx.close();
		}
		
		ArrayList list = (ArrayList) request.getSession().getAttribute(Constants.VENDOR_LIST);
		ArrayList<VendorBean> vendorBeanList =  list;
		
		ArrayList<VendorBean> newList = VendorManager.updateVendorListStatus(vendorBeanList, bpartnerId, false);
		
		request.getSession().setAttribute(Constants.VENDOR_LIST, newList);
		
		return mapping.findForward(SEARCH_VENDOR);
	}
	
	
	
}
