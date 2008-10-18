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
import org.posterita.beans.BlackListedBean;
import org.posterita.businesslogic.administration.BlackListedManager;
import org.posterita.core.TmkJSPEnv;
import org.posterita.core.TrxPrefix;
import org.posterita.exceptions.ApplicationException;
import org.posterita.exceptions.OperationException;
import org.posterita.form.BlackListForm;
import org.posterita.struts.core.DefaultForm;

public class BlackListedAction  extends POSDispatchAction
{
	public static final String CREATE_BLACKLISTED="createBlackListed";
	public ActionForward createBlackListed(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws OperationException, ApplicationException
	{
		 ActionForward fwd = init(mapping, form, request, response);
		 if (fwd != null)
			 return fwd;
		 
		 Properties ctx =TmkJSPEnv.getCtx(request);
		 DefaultForm df = (DefaultForm)form;
		 BlackListedBean bean =(BlackListedBean)df.getBean();
		
		 Trx trx = Trx.get(TrxPrefix.getPrefix(), true);
		 
		 try
		 {
			 trx.start();
			 BlackListedManager.createBlackListed(ctx, bean, trx.getTrxName());
			 trx.commit();
		 }
		 catch (OperationException ex) 
		 {
			 trx.rollback();
			 throw ex;
		
		}
		 finally
		 {
			 trx.close();
		 }
		 request.setAttribute(Constants.BLACKLISTED_DETAILS, bean);
		 return mapping.findForward(CREATE_BLACKLISTED);
		 
		 
	}
	
	public static final String GET_ALL_BLACKLISTED ="getAllBlackListed";
	public ActionForward getAllBlackListed(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws OperationException, ApplicationException
	{
		ActionForward fwd = init(mapping, form, request, response);
		if (fwd!=null)
				return fwd;
		Properties ctx= TmkJSPEnv.getCtx(request);
		ArrayList <BlackListedBean>blackListedList = BlackListedManager.getAllBlackListed(ctx);
		request.getSession().setAttribute(Constants.BLACKLISTED_LISTS , blackListedList);
		return mapping.findForward(GET_ALL_BLACKLISTED);
	}
	
	public static final String INIT_SEARCH_CHEQUE="initSearchCheque"; 
	public ActionForward initSearchCheque(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws OperationException, ApplicationException
	{
		ArrayList Blacklist = new ArrayList();
		request.getSession().setAttribute(Constants.BLACKLISTED_LISTS, Blacklist);
		
		return mapping.findForward(INIT_SEARCH_CHEQUE);
	}
	
	public static final String SEARCH_CHEQUE="searchCheque"; 
	public ActionForward searchCheque(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws OperationException, ApplicationException
	{
		ActionForward fwd=init(mapping,form,request,response);
		if (fwd!=null)
			return fwd;
		Properties ctx = TmkJSPEnv.getCtx(request);
		
		DefaultForm df = (DefaultForm)form;
		
		//BlackListedBean bean = (BlackListedBean)df.getBean();
		
		ArrayList Blacklist = BlackListedManager.searchChequeNo(ctx, df.getChequeNo());
		
		request.getSession().setAttribute(Constants.BLACKLISTED_LISTS, Blacklist);
		
		return mapping.findForward(SEARCH_CHEQUE);
		
	}
	
	public static final String INIT_EDIT_CHEQUE="initEditCheque";
	public 	ActionForward initEditCheque(ActionMapping mapping, ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
	{
		ActionForward fwd=init(mapping,form,request,response);
		if (fwd!=null)
			return fwd;
		Properties ctx = TmkJSPEnv.getCtx(request);
		
		BlackListForm f = (BlackListForm) form;
		f.validate(mapping,request);
		
		BlackListedBean bean = (BlackListedBean) f.getBean();
		Integer blacklistedID = bean.getBlackListedId();
		
		if(blacklistedID==null)
		{
			throw new OperationException("Cannot edit Cheques. Cause BlacklistedID id cannot be null!");
		}		
		
		bean= BlackListedManager.getBlackListedDetails(ctx, blacklistedID);
		f.populate(bean);
		
		return mapping.findForward(INIT_EDIT_CHEQUE);
	}
	
	
	public static final String EDIT_CHEQUE = "editCheque";
	public 	ActionForward editCheque(ActionMapping mapping, ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
	{
		
		ActionForward fwd=init(mapping,form,request,response);
		if (fwd!=null)
			return fwd;
		Properties ctx = TmkJSPEnv.getCtx(request);
		BlackListForm f = (BlackListForm) form;
		BlackListedBean bean=(BlackListedBean)f.getBean();
		
		Integer blacklistedID = bean.getBlackListedId();
		if(blacklistedID==null)
		{
			throw new OperationException("Cannot load Cheque details. Cause BlacklistedId cannot be null!");
		}
	        
         Trx trx = Trx.get(TrxPrefix.getPrefix(), true);
            trx.start();
            
            try
            {
                BlackListedManager.editBlackListed(ctx, bean, trx.getTrxName());
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
     	request.getSession().setAttribute(Constants.BLACKLISTED_DETAILS,bean);
		return mapping.findForward(EDIT_CHEQUE);
	}
	
	
	public static final String DEACTIVATE_CHEQUE = "deactivateCheque";
	public 	ActionForward deactivateCheque(ActionMapping mapping, ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
	{
		
		ActionForward fwd=init(mapping,form,request,response);
		if (fwd!=null)
			return fwd;
		Properties ctx = TmkJSPEnv.getCtx(request);
		BlackListForm f=(BlackListForm)form;
		f.validate(mapping,request);
		
		BlackListedBean bean=(BlackListedBean)f.getBean();
		Integer blackListedId = bean.getBlackListedId();
		
		if(blackListedId==null)
		{
			throw new OperationException("Cannot deactivate Blacklisted Cheques. Cause BlackListed id cannot be null!");
		}
		
		
		Trx trx = Trx.get(TrxPrefix.getPrefix(), true);
				
		try
		{
			trx.start();
			BlackListedManager.deactivateBListedCheque(ctx, blackListedId.intValue(), trx.getTrxName());
			trx.commit();
		}
		catch (OperationException e)
		{
			trx.rollback();
			postGlobalError("error.deactivate.customer", request);
			mapping.getInputForward();
		}
		finally
		{
			trx.close();
		}
		
		return mapping.findForward(DEACTIVATE_CHEQUE);
	}
	
		
}