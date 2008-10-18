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
 * Created on Oct 31, 2006
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
import org.posterita.beans.PaymentTermBean;
import org.posterita.businesslogic.PaymentTermManager;
import org.posterita.core.TmkJSPEnv;
import org.posterita.core.TrxPrefix;
import org.posterita.exceptions.ApplicationException;
import org.posterita.exceptions.CannotDeactivatePaymentTermException;
import org.posterita.exceptions.InvalidNetDaysException;
import org.posterita.exceptions.MandatoryNameException;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.PaymentTermAlreadyExistsException;
import org.posterita.form.PaymentTermForm;
import org.posterita.form.PaymentTermForm2;
import org.posterita.struts.core.BaseDispatchAction;
import org.posterita.struts.core.DefaultForm;


public class PaymentTermAction extends BaseDispatchAction
{
    public static final String VIEW_ALL_PAYMENT_TERMS = "viewAllPaymentTerms";
    public  ActionForward viewAllPaymentTerms(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx=TmkJSPEnv.getCtx(request);
       
        ArrayList paymentTermList = PaymentTermManager.getAllPaymentTerm(ctx,false);
        request.setAttribute(Constants.ALL_PAYMENT_TERMS,paymentTermList);
        return mapping.findForward(VIEW_ALL_PAYMENT_TERMS);
    }
    
    public static final String CREATE_PAYMENT_TERM = "createPaymentTerm";
    public  ActionForward createPaymentTerm(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
    	 ActionForward fwd= init(mapping,form,request,response);
         if(fwd!=null)
             return fwd;
         
         DefaultForm df= (DefaultForm) form;
         PaymentTermBean bean=(PaymentTermBean) df.getBean();
         Properties ctx=TmkJSPEnv.getCtx(request);
         
         Trx trx = Trx.get(TrxPrefix.getPrefix(),true);
        
         try 
         {
             trx.start();
             PaymentTermManager.createEditPaymentTerm(ctx,bean,trx.getTrxName());
             trx.commit();
         }
         catch(MandatoryNameException e)
         {
             trx.rollback();
             postGlobalError("error.name.mandatory",request);
             return mapping.getInputForward();
         }
         catch(InvalidNetDaysException e1)
         {
             trx.rollback();
             postGlobalError("error.net.days.cannot.be.negative",request);
             return mapping.getInputForward();
         }
         catch(PaymentTermAlreadyExistsException e2)
         {
             trx.rollback();
             postGlobalError("error.paymentTermName.duplicate",request);
             return mapping.getInputForward();
         }
         catch(CannotDeactivatePaymentTermException e3)
         {
         	 trx.rollback();
              postGlobalError("error.cannot.deactivate.paymentTermName",request);
              return mapping.getInputForward();
         }
         finally
         {
             trx.close();
         }
         ArrayList paymentTermList = PaymentTermManager.getAllPaymentTerm(ctx,false);
         request.setAttribute(Constants.ALL_PAYMENT_TERMS,paymentTermList);
         return mapping.findForward(CREATE_PAYMENT_TERM);
    }
    
    public static final String DEACTIVATE_PAYMENT_TERM = "deActivatePaymentTerm";
    public  ActionForward deActivatePaymentTerm(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
    	ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        PaymentTermForm2 df= (PaymentTermForm2) form;
        PaymentTermBean bean=(PaymentTermBean) df.getBean();
        Properties ctx=TmkJSPEnv.getCtx(request);
        ArrayList paymentTermList = PaymentTermManager.getAllPaymentTerm(ctx,false);
        request.setAttribute(Constants.ALL_PAYMENT_TERMS,paymentTermList);
       try
       {
    	   PaymentTermManager.activatePaymentTerm(ctx,bean.getPaymentTermId(),false);
       }
       catch(CannotDeactivatePaymentTermException e3)
       {
       	
            postGlobalError("error.cannot.deactivate.paymentTermName",request);
            return mapping.getInputForward();
       }
    
       
        return mapping.findForward(DEACTIVATE_PAYMENT_TERM);
    }
    
    
    public static final String ACTIVATE_PAYMENT_TERM = "activatePaymentTerm";
    public  ActionForward activatePaymentTerm(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
    	ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        PaymentTermForm2 df= (PaymentTermForm2) form;
        PaymentTermBean bean=(PaymentTermBean) df.getBean();
        Properties ctx=TmkJSPEnv.getCtx(request);
        
        PaymentTermManager.activatePaymentTerm(ctx,bean.getPaymentTermId(),true);
        ArrayList paymentTermList = PaymentTermManager.getAllPaymentTerm(ctx,false);
        request.setAttribute(Constants.ALL_PAYMENT_TERMS,paymentTermList);
        return mapping.findForward(ACTIVATE_PAYMENT_TERM);
    }
    
    public static final String INIT_EDIT_PAYMENT_TERM = "initEditPaymentTerm";
    public  ActionForward initEditPaymentTerm(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        PaymentTermForm ptf = (PaymentTermForm) form;
        int paymentTermId = new Integer (ptf.getPaymentTermId()).intValue();
        Properties ctx=TmkJSPEnv.getCtx(request);
        PaymentTermBean bean= PaymentTermManager.getPaymentTerm(ctx,paymentTermId);
        ptf.populate(bean);
        request.setAttribute(Constants.EDIT_PAYMENT_TERM,bean);
        return mapping.findForward(INIT_EDIT_PAYMENT_TERM);
    }
    
    public static final String EDIT_PAYMENT_TERM = "editPaymentTerm";
    public  ActionForward editPaymentTerm(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
    	ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        DefaultForm df= (DefaultForm) form;
        PaymentTermBean bean=(PaymentTermBean) df.getBean();
        Properties ctx=TmkJSPEnv.getCtx(request);
        
        Trx trx = Trx.get(TrxPrefix.getPrefix(),true);
       
        try 
        {
            trx.start();
            PaymentTermManager.createEditPaymentTerm(ctx,bean,trx.getTrxName());
            trx.commit();
        }
        catch(MandatoryNameException e)
        {
            trx.rollback();
            postGlobalError("error.name.mandatory",request);
            return mapping.getInputForward();
        }
        catch(InvalidNetDaysException e1)
        {
            trx.rollback();
            postGlobalError("error.net.days.cannot.be.negative",request);
            return mapping.getInputForward();
        }
       
        catch(PaymentTermAlreadyExistsException e)
        {
            trx.rollback();
            postGlobalError("error.paymentTermName.duplicate",request);
            return mapping.getInputForward();
        }
        catch(CannotDeactivatePaymentTermException e3)
        {
        	 trx.rollback();
             postGlobalError("error.cannot.deactivate.paymentTermName",request);
             return mapping.getInputForward();
        }
        catch(OperationException e)
        {
            trx.rollback();
            throw e;
        }
        finally
        {
            trx.close();
        }
        ArrayList paymentTermList = PaymentTermManager.getAllPaymentTerm(ctx,false);
        request.setAttribute(Constants.ALL_PAYMENT_TERMS,paymentTermList);
        return mapping.findForward(EDIT_PAYMENT_TERM);
    }
}
