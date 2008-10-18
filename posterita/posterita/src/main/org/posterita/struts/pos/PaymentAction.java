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
 * 08-Dec-2006 14:01:58 by praveen
 *
 */

package org.posterita.struts.pos;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.compiere.model.MPayment;
import org.compiere.util.Trx;
import org.posterita.beans.PaymentBean;
import org.posterita.businesslogic.PaymentAllocationManager;
import org.posterita.businesslogic.PaymentManager;
import org.posterita.core.TmkJSPEnv;
import org.posterita.core.TrxPrefix;
import org.posterita.exceptions.AllocatedAmtMoreThanOpenAmtException;
import org.posterita.exceptions.ApplicationException;
import org.posterita.exceptions.BPartnerNotFoundException;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.PayAmtNotEqualToAllocateAmtException;
import org.posterita.form.GeneralPaymentForm;
import org.posterita.form.PaymentForm;

public class PaymentAction extends POSDispatchAction
{
    public static String CREATE_PAYMENT = "createPayment";
    public  ActionForward createPayment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd = init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        GeneralPaymentForm pf = (GeneralPaymentForm) form;
        PaymentBean bean = (PaymentBean) pf.getBean();
        Trx trx = Trx.get(TrxPrefix.getPrefix(),true);
        MPayment payment;
        try
        {
            trx.start(); 	
            payment=  PaymentManager.createPayment(ctx,bean,trx.getTrxName());
            trx.commit();
        }
        catch(BPartnerNotFoundException e)
        {
            trx.rollback();
            postGlobalError("error.required.bpartnerId",request);
            return mapping.getInputForward();
        }
        catch(OperationException e)
        {
            trx.rollback();
            return mapping.getInputForward();
        }
        finally
        {
            trx.close();
        }
        
        return  new ActionForward("/ViewWebstorePaymentAction.do?action=viewOrder&documentId="+payment.get_ID());
    }
    
    
    
    public static String CREATE_PAYMENT_FOR_MULTIPLE_INVOICE = "createPaymentForMultipleInvoice";
    public  ActionForward createPaymentForMultipleInvoice(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd = init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        PaymentForm pf = (PaymentForm) form;
        PaymentBean bean = (PaymentBean) pf.getBean();
        Trx trx = Trx.get(TrxPrefix.getPrefix(),true);
        MPayment payment=null;
        try
        {
            trx.start();    
            payment=  PaymentManager.createPayment(ctx,bean,trx.getTrxName());
            PaymentAllocationManager.allocatepaymentWithInvoices(ctx,bean.getInvoiceIds(),bean.getAllocateAmount(),payment.get_ID(),trx.getTrxName());
            trx.commit();
        }
        catch(AllocatedAmtMoreThanOpenAmtException e1)
        {
            trx.rollback();
            postGlobalError("error.allocate.amt.exceeds",e1.getMessage(),request);
            return mapping.getInputForward();
        }
        catch(PayAmtNotEqualToAllocateAmtException e2)
        {
            trx.rollback();
            postGlobalError("error.allocate.amt.payamt.notequal",e2.getMessage(),request);
            return mapping.getInputForward();
        }
        catch(OperationException e)
        {
            trx.rollback();
            return mapping.getInputForward();
        }
        finally
        {
            trx.close();
        }
        
        return  new ActionForward("/ViewWebstorePaymentAction.do?action=viewOrder&documentId="+payment.get_ID());
    }
}
