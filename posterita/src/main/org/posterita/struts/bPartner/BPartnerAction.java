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
package org.posterita.struts.bPartner;

import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.compiere.model.MOrg;
import org.compiere.util.Trx;

import org.posterita.Constants;
import org.posterita.beans.BPartnerBean;
import org.posterita.businesslogic.BPartnerManager;
import org.posterita.businesslogic.OrganisationManager;
import org.posterita.core.TmkJSPEnv;
import org.posterita.core.TrxPrefix;
import org.posterita.exceptions.ApplicationException;
import org.posterita.exceptions.BPartnerAlreadyExistsException;
import org.posterita.exceptions.OperationException;
import org.posterita.form.CreateBPartnerForm;
import org.posterita.keyname.RegionKeyNamePair;
import org.posterita.lib.UdiConstants;
import org.posterita.struts.core.BaseDispatchAction;
import org.posterita.struts.core.DefaultForm;


public class BPartnerAction extends BaseDispatchAction
{
	public static final String CREATE_BPARTNER = "createBPartner";
	public static final String GET_REGION = "getRegion";
	
	public ActionForward getRegion(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
	{
		ActionForward fwd = init(mapping,form,request,response);
		
		if(fwd!=null)
			return fwd;	
		
		Properties ctx = TmkJSPEnv.getCtx(request);
		
		ArrayList region = RegionKeyNamePair.getKeyNamePairs(ctx);
		request.getSession().setAttribute(Constants.REGIONS,region);
		
		return mapping.findForward(GET_REGION);
		
	}
	
	public ActionForward createBPartner(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
	{
		DefaultForm df =  (DefaultForm) form;
		
		BPartnerBean bean = (BPartnerBean) df.getBean();
		
		Properties ctx = TmkJSPEnv.getCtx(request);
		
		MOrg parentOrg = OrganisationManager.getMyOrg(ctx);
		
		Trx trx = Trx.get(TrxPrefix.getPrefix(), true);
		trx.start();
		
		try
		{
            int countryId =UdiConstants.COUNTRY_SOUTH_AFRICA;
			BPartnerManager.createBPartner(ctx, parentOrg.getLinkedC_BPartner_ID(null), bean.getPartnerName(),bean.getName2(),bean.getIsCustomer().booleanValue(),bean.getIsVendor().booleanValue(),bean.getIsEmployee().booleanValue(),bean.getIsSalesRep().booleanValue(),bean.getAddress1(),bean.getAddress2(),bean.getPostalAddress(), bean.getRegionId(), bean.getCity(),bean.getPhone(),bean.getFax(),countryId, true, true, trx.getTrxName());
			
			request.getSession().setAttribute(Constants.BUSINESSPARTNER,bean);
			trx.commit();
		}
		catch(BPartnerAlreadyExistsException exp)
		{
			trx.rollback();
			postGlobalError("error.bp.alreadyexist", request);
			return mapping.getInputForward();
		}
		finally
		{
			trx.close();
		}
		
		
		return mapping.findForward(CREATE_BPARTNER);
	}
	
	
	
	//-------------------------------------------------------------------------------------------------------
	
	public static final String INIT_EDIT_BPARTNER_DETAILS = "initEditBpartnerDetails";
	public ActionForward initEditBpartnerDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException
	{
		ActionForward fwd = init(mapping, form, request, response);
		if (fwd!=null)
			return fwd;
		
		Properties ctx = TmkJSPEnv.getCtx(request);
		
		CreateBPartnerForm bPf = (CreateBPartnerForm) form;
		
		int bPartnerId = Integer.parseInt(bPf.getBpartnerId());
		
		Trx trx = Trx.get(TrxPrefix.getPrefix(), true);
		
		BPartnerBean bean = new BPartnerBean();
		
		try
		{
			trx.start();
			bean = BPartnerManager.getBpartner(ctx,bPartnerId, trx.getTrxName());
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
		
		bPf.populate(bean);	
		
		ArrayList region = RegionKeyNamePair.getKeyNamePairs(ctx);
		request.getSession().setAttribute(Constants.REGIONS,region);
		
		return mapping.findForward(INIT_EDIT_BPARTNER_DETAILS);
	}
	
	public static final String EDIT_BPARTNER_DETAILS = "editBpartnerDetails";
	public ActionForward editBpartnerDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException
	{
		ActionForward fwd = init(mapping, form, request, response);
		if (fwd!=null)
			return fwd;
		
		Properties ctx = TmkJSPEnv.getCtx(request);
		
		DefaultForm df = (DefaultForm) form;
		BPartnerBean bean = (BPartnerBean) df.getBean();
		
		
		BPartnerManager.editBPartner(ctx, bean.getBpartnerId().intValue(), bean.getPartnerName(),bean.getName2(),bean.getIsCustomer().booleanValue(),bean.getIsVendor().booleanValue(),bean.getIsEmployee().booleanValue(),bean.getIsSalesRep().booleanValue(),bean.getAddress1(),bean.getAddress2(),bean.getPostalAddress(),bean.getCity(),bean.getPhone(),null,bean.getFax(),0,null);
		
		
		return mapping.findForward(EDIT_BPARTNER_DETAILS);
	}
	
	public static final String ACTIVATE_BPARTNER = "activateBPartner";
	public ActionForward activateBPartner(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException
	{
		ActionForward fwd = init(mapping, form, request, response);
		if (fwd!=null)
			return fwd;
		
		Properties ctx = TmkJSPEnv.getCtx(request);
		
		DefaultForm df = (DefaultForm) form;
		
		int bpartnerId = Integer.parseInt(df.getBpartnerId());
		boolean isActive = Boolean.parseBoolean(df.getIsActive());
		
		Trx trx = Trx.get(TrxPrefix.getPrefix(), true);
		
		
		try
		{
			trx.start();
			BPartnerManager.activateBPartner(ctx,bpartnerId,isActive,trx.getTrxName());
			trx.commit();
		}
		catch (OperationException e)
		{
			trx.rollback();
			postGlobalError("error.activate.partner", request);
			mapping.getInputForward();
		}		
		finally
		{
			trx.close();
		}
		
		return mapping.findForward(ACTIVATE_BPARTNER);
	}
	
	public static final String SEARCH_BPARTNER  = "searchBPartner";
	public ActionForward searchBPartner(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException
	{
		ActionForward fwd = init(mapping, form, request, response);
		if (fwd!=null)
			return fwd;
		
		Properties ctx = TmkJSPEnv.getCtx(request);		
		DefaultForm df = (DefaultForm) form;
		
		ArrayList<BPartnerBean> bplist = new ArrayList<BPartnerBean>();
		
		bplist = BPartnerManager.searchBpartners(ctx,df.getName(),null);
		
		request.getSession().setAttribute(Constants.ALLBUSINESSPARTNERS,bplist);		
		return mapping.findForward(SEARCH_BPARTNER);
	}
		
}
