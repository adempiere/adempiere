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
 * Created on Oct 13, 2006
 */


package org.posterita.struts.pos;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.compiere.model.MCurrency;
import org.compiere.model.MInOut;
import org.compiere.model.MInvoice;
import org.compiere.model.MOrder;
import org.compiere.model.MPayment;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.compiere.util.WebUser;
import org.posterita.Constants;
import org.posterita.beans.DocumentHistoryBean;
import org.posterita.beans.LoginBean;
import org.posterita.beans.OpenItemBean;
import org.posterita.beans.OrderLineBean;
import org.posterita.beans.PaymentAllocationBean;
import org.posterita.beans.PaymentTypeBean;
import org.posterita.beans.ShoppingCartBean;
import org.posterita.beans.WebDocumentBean;
import org.posterita.businesslogic.BPartnerManager;
import org.posterita.businesslogic.CreditOrderManager;
import org.posterita.businesslogic.LoginManager;
import org.posterita.businesslogic.MenuManager;
import org.posterita.businesslogic.OrderManager;
import org.posterita.businesslogic.OrderReferenceManager;
import org.posterita.businesslogic.POSBpartnerInfoManager;
import org.posterita.businesslogic.POSManager;
import org.posterita.businesslogic.POSTerminalManager;
import org.posterita.businesslogic.PaymentAllocationManager;
import org.posterita.businesslogic.PaymentManager;
import org.posterita.businesslogic.PaymentTermManager;
import org.posterita.businesslogic.RoleManager;
import org.posterita.core.SessionStorage;
import org.posterita.core.TmkJSPEnv;
import org.posterita.core.TrxPrefix;
import org.posterita.exceptions.ApplicationException;
import org.posterita.exceptions.BPartnerNotFoundException;
import org.posterita.exceptions.BPartnerOverCreditLimitException;
import org.posterita.exceptions.CanNotCreatePaymentAllocationException;
import org.posterita.exceptions.DiscountLimitException;
import org.posterita.exceptions.DuplicatePINException;
import org.posterita.exceptions.InsufficientQtyException;
import org.posterita.exceptions.InvalidAddressException;
import org.posterita.exceptions.InvalidPINException;
import org.posterita.exceptions.LimitPriceVioletException;
import org.posterita.exceptions.MandatoryException;
import org.posterita.exceptions.NoAllocateeInvoiceException;
import org.posterita.exceptions.NoPaymentAmountException;
import org.posterita.exceptions.NotLoggedInException;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.TooManyAllocateInvoiceException;
import org.posterita.exceptions.UserInactiveException;
import org.posterita.exceptions.UserNotFoundException;
import org.posterita.factory.POSMenuFactory;
import org.posterita.form.DocumentHistoryForm;
import org.posterita.form.OpenItemForm;
import org.posterita.struts.core.DefaultForm;


public class CreditOrderAction extends POSDispatchAction
{
    
    
    public static final String GET_BPARTNER_PAYMENT_STATUS="getBpartnerPaymentStatus";
    public ActionForward getBpartnerPaymentStatus(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
    	 ActionForward fwd= init(mapping,form,request,response);
         if(fwd!=null)
             return fwd;
         
         Properties ctx=TmkJSPEnv.getCtx(request);
         DefaultForm df= (DefaultForm) form;
         OpenItemBean bean=(OpenItemBean) df.getBean();
         boolean access=MenuManager.getRoleMenuAccess(ctx,POSMenuFactory.SMENU_SETTLE_PAYMENT_ID);
         if(access==false)
         {           
             postGlobalError("error.access.not.allowed",request);             
             return new ActionForward(bean.getForward());
         }
      
         ArrayList openItemList = null;
         ArrayList agingList = null;
         ArrayList paymentList = null;
         try
         {
         	 openItemList=CreditOrderManager.getOpenItems(ctx,bean.getBpartnerId());
              agingList = CreditOrderManager.getAging(ctx,bean.getBpartnerId());
              paymentList = CreditOrderManager.getUnallocatedPayments(ctx,bean.getBpartnerId());
              
         }
         catch(BPartnerNotFoundException e)
         {
         	  postGlobalError("error.customer.not.exists",request);
               return mapping.getInputForward();
         }
         
        
         request.getSession().setAttribute(Constants.OPEN_ITEMS,openItemList);
         request.getSession().setAttribute(Constants.AGING_ITEMS,agingList);
         request.getSession().setAttribute(Constants.UNALLOCATED_PAYMENTS,paymentList);
         return mapping.findForward(GET_BPARTNER_PAYMENT_STATUS);
    }
    
    
    public static final String MATCH_INVOICE = "matchInvoice";
    public  ActionForward matchInvoice(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx=TmkJSPEnv.getCtx(request);
        DefaultForm df= (DefaultForm) form;
        OpenItemBean bean=(OpenItemBean) df.getBean();
     
        Trx trx = Trx.get(TrxPrefix.getPrefix(),true);
        try
        {
            trx.start();  
               PaymentAllocationManager.allocate(ctx,bean.getInvoiceIds(),bean.getPaymentIds(),trx.getTrxName());
            trx.commit();
        }
        
        catch(TooManyAllocateInvoiceException e)
        {
            trx.rollback();
            postGlobalError("error.too.many.invoice",request);
            return mapping.getInputForward();
        }
        catch(CanNotCreatePaymentAllocationException e1)
        {
            trx.rollback();
            postGlobalError("error.allocation.amt.exceeds.openamt",request);
            return mapping.getInputForward();
        }
        catch(NoAllocateeInvoiceException e2)
        {
            trx.rollback();
            postGlobalError("error.no.invoice.to.allocate",request);
            return mapping.getInputForward();
        }
        finally
        {
            trx.close();
        }
     
        ArrayList openItemList=CreditOrderManager.getOpenItems(ctx,bean.getBpartnerId());
        ArrayList agingList = CreditOrderManager.getAging(ctx,bean.getBpartnerId());
        ArrayList paymentList = CreditOrderManager.getUnallocatedPayments(ctx,bean.getBpartnerId());
        
        request.getSession().setAttribute(Constants.UNALLOCATED_PAYMENTS,paymentList);
        request.getSession().setAttribute(Constants.OPEN_ITEMS,openItemList);
        request.getSession().setAttribute(Constants.AGING_ITEMS,agingList);
       
        df.setInvoiceIds(null);
        df.setPaymentIds(null);
       
       return mapping.findForward(MATCH_INVOICE);
    }
    
    
    public static final String INIT_GET_BPARTNER_PAYMENT_STATUS = "initGetBpartnerPaymentStatus";
    public  ActionForward initGetBpartnerPaymentStatus(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx=TmkJSPEnv.getCtx(request);
        
        //DefaultForm df= (DefaultForm) form;
        ArrayList list = POSBpartnerInfoManager.getPartnerInfo(ctx,true);
        request.getSession().removeAttribute(Constants.UNALLOCATED_PAYMENTS);
        request.getSession().removeAttribute(Constants.OPEN_ITEMS);
        request.getSession().removeAttribute(Constants.AGING_ITEMS);
        request.getSession().setAttribute(Constants.BUSINESSPARTNER,list);
        return mapping.findForward(INIT_GET_BPARTNER_PAYMENT_STATUS);
    }
    
    
    public static final String INIT_CREATE_PAYMENT = "initCreatePayment";
    public  ActionForward initCreatePayment(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        // Properties ctx=TmkJSPEnv.getCtx(request);
        DefaultForm df= (DefaultForm) form;
        OpenItemBean bean=(OpenItemBean) df.getBean();
        request.getSession().setAttribute(Constants.CREDIT_INVOICE_INFO,bean);
        return mapping.findForward(INIT_CREATE_PAYMENT);
    }
    
    public static final String CREATE_PAYMENT = "createPayment";
    public  ActionForward createPayment(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx=TmkJSPEnv.getCtx(request);
        DefaultForm df= (DefaultForm) form;
        OpenItemBean bean=(OpenItemBean) df.getBean();
        Trx trx = Trx.get(TrxPrefix.getPrefix(),true);
        PaymentTypeBean pBean =null;
        
        HashMap<String,String> map = new HashMap<String,String>();
        map.put("Cash",MInvoice.PAYMENTRULE_Cash);
        map.put("Card",MPayment.TENDERTYPE_CreditCard);
        map.put("Cheque",MPayment.TENDERTYPE_Check);
        
        bean.setTrxType(map.get(bean.getTrxType()));        
        
        try 
        {
            trx.start();
            pBean  = CreditOrderManager.createPayment(ctx,bean,trx.getTrxName());
            MCurrency currency = POSTerminalManager.getCurrencyOfDefaultCashBook(ctx);
            request.setAttribute(Constants.CURRENCY_SYMBOLE,currency.getCurSymbol());
            
            trx.commit();
            if(!pBean.getPaymentType().equalsIgnoreCase("cash"))
            {
                MPayment payment = new MPayment(ctx,pBean.getId(),null);
                PaymentManager.completePayment(ctx,payment);
                request.getSession().removeAttribute(Constants.OPEN_ITEMS);
                request.getSession().removeAttribute(Constants.AGING_ITEMS);
                trx.close();
                return  new ActionForward("/ViewWebstorePaymentAction.do?action=viewOrder&documentId="+payment.get_ID());
            }
        }
        catch(NoPaymentAmountException ex)
        {
            trx.rollback();
            postGlobalError("error.paymentamount.mandatory",request);
            return mapping.getInputForward();
        }
        catch(OperationException e)
        {
            trx.rollback();
        }
        finally
        {
            trx.close();
        }
        
        request.getSession().removeAttribute(Constants.OPEN_ITEMS);
        request.getSession().removeAttribute(Constants.AGING_ITEMS);
        
        
        return  new ActionForward("/ViewWebstoreCashPaymentAction.do?action=viewOrder&documentId="+pBean.getId());
        //return mapping.findForward(CREATE_PAYMENT);
    }
    
    
    public static final String GET_PAYMENT_DETAILS_FOR_INVOICE = "getPaymentDetailsForInvoice";
    public  ActionForward getPaymentDetailsForInvoice(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx=TmkJSPEnv.getCtx(request);
        DefaultForm df= (DefaultForm) form;
        OpenItemBean bean=(OpenItemBean) df.getBean();
        ArrayList cashPaymentList = CreditOrderManager.getAllCashPaymentForInvoice(ctx,bean.getInvoiceId());
        ArrayList cardChqPaymentList=CreditOrderManager.getAllPaymentForInvoice(ctx,bean.getInvoiceId());
        request.getSession().setAttribute(Constants.CASH_PAYMENT_LIST,cashPaymentList);
        request.getSession().setAttribute(Constants.CARD_CHQ_PAYMENT_LIST,cardChqPaymentList);
        request.getSession().setAttribute(Constants.INVOICE_GRAND_TOTAL,bean.getPaidAmt());
        return mapping.findForward(GET_PAYMENT_DETAILS_FOR_INVOICE);
    }
    
    

    
    public static final String GET_CRDITORDER_DISC_DETAILS="getCreditOrderDiscDetails";
    public ActionForward getCreditOrderDiscDetails(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
       
        DefaultForm df= (DefaultForm) form;
        OrderLineBean bean=(OrderLineBean) df.getBean();
        Properties ctx=TmkJSPEnv.getCtx(request);
        Integer bPartnerId = bean.getBpartnerId();
        MOrder order=null;
        String toBeShipped=bean.getToBeShipped();
        if(toBeShipped==null)
        {
            toBeShipped=(String)request.getSession().getAttribute(Constants.TO_BE_SHIPPED);
            bean.setToBeShipped(toBeShipped);
        }
        if(toBeShipped==null)
        {
            toBeShipped="true";
        }

        if(bPartnerId==null)
        {
            postGlobalError("error.required.bpartnerId",request);
            return mapping.getInputForward();
        }
        if(BPartnerManager.isBPartnerPresent(ctx,bPartnerId,null) == false)
        {
        	 postGlobalError("error.customer.not.exists",request);
             return mapping.getInputForward();
        }
    
       if(bean.getOrderId()!=null)
        {
            order = new MOrder(ctx, bean.getOrderId(), null);
        }
        
        df.populate(new OrderLineBean());
        
        ShoppingCartBean cartBean = (ShoppingCartBean) request.getSession().getAttribute(Constants.CREDIT_ORDER_SHOPPING_CART);
        if(cartBean==null)
        {           
            postGlobalError("error.pos.shoppingcart.empty",request);             
            return mapping.getInputForward();
        }
        request.getSession().setAttribute(Constants.CREDIT_ORDER_SHOPPING_CART, cartBean);
        request.getSession().setAttribute(Constants.PRESENT_CREDIT_ORDER,order);
        request.getSession().setAttribute(Constants.CREDITORDER_BPARTNER,bPartnerId);
        request.getSession().setAttribute(Constants.TO_BE_SHIPPED,bean.getToBeShipped());
        request.getSession().setAttribute(Constants.CREDIT_ORDER_SHOPPING_CART_ITEMS, cartBean.getItems());
        return mapping.findForward(GET_CRDITORDER_DISC_DETAILS);
    }
    
    public static final String CREATE_CREDIT_ORDER="createCreditOrder";
    public ActionForward createCreditOrder(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx=TmkJSPEnv.getCtx(request);
        DefaultForm df= (DefaultForm) form;
        OrderLineBean bean=(OrderLineBean) df.getBean();
        
        ShoppingCartBean cartBean = (ShoppingCartBean) request.getSession().getAttribute(Constants.CREDIT_ORDER_SHOPPING_CART);
        
        if(cartBean==null ||cartBean.getItems() == null||cartBean.getItems().size()==0)
        {           
            postGlobalError("error.pos.shoppingcart.empty",request);             
            return mapping.getInputForward();
        }
        
        String toBeShipped=bean.getToBeShipped();
        if(toBeShipped==null)
        {
            toBeShipped=(String)request.getSession().getAttribute(Constants.TO_BE_SHIPPED);
            bean.setToBeShipped(toBeShipped);
        }
        if(toBeShipped==null)
        {
            toBeShipped="true";
        }

        Integer bPartnerId = bean.getBpartnerId();
        if(bPartnerId==null)
        {
            bPartnerId = (Integer) request.getSession().getAttribute(Constants.CREDITORDER_BPARTNER);
            bean.setBpartnerId(bPartnerId);
        }
        
        if(bPartnerId==null)
        {
            postGlobalError("error.required.bpartnerId",request);
            return mapping.getInputForward();
        }
        
        if(BPartnerManager.isBPartnerPresent(ctx,bean.getBpartnerId(),null) == false)
        {
        	 postGlobalError("error.customer.not.exists",request);
             return mapping.getInputForward();
        }
        
        BigDecimal discountAllowed = (BigDecimal) request.getSession().getAttribute( Constants.DISCOUNT_ALLOWED );
        bean.setUserDiscount( discountAllowed );
        MOrder previousOrder= null;
        previousOrder=(MOrder)request.getSession().getAttribute(Constants.PRESENT_CREDIT_ORDER);
        
        if(previousOrder!=null)
        {
            bean.setOrderId(Integer.valueOf(previousOrder.get_ID()));
        }
        else
        {
            bean.setOrderId(null);
        }
        
        Trx trx = Trx.get(TrxPrefix.getPrefix(),true);
        
        MOrder creditOrder=null;
        try
        {
            trx.start();            
            creditOrder= CreditOrderManager.createCreditOrder(ctx,bean,cartBean.getItems(),trx.getTrxName());
            trx.commit();
        }
        catch (DiscountLimitException e)         
        {
            trx.rollback();
            postGlobalError("error.discountLimit.exceeded",request);             
            return mapping.getInputForward();
        }
        catch(BPartnerOverCreditLimitException e1)
        {
            trx.rollback();
            postGlobalError("error.bpartner.over.credit.limit",e1.getMessage(),request);
            return mapping.getInputForward();
        }
        
        catch(MandatoryException e2)
        {
            trx.rollback();
            postGlobalError("error.bpartner.no.payment.term",request);
            return mapping.getInputForward();
        }
        catch(InsufficientQtyException e3)
        {
            trx.rollback();
            postGlobalError("error.insufficient.qty",e3.getMessage(),request);
            return mapping.getInputForward();
        }
        catch(LimitPriceVioletException e3)
        {
            trx.rollback();
            postGlobalError("error.limit.price.violet",e3.getMessage(),request);
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
            request.getSession().removeAttribute( Constants.DISCOUNT_ALLOWED );
        }
        bean.setOrderId(Integer.valueOf(creditOrder.get_ID()));
        df.populate(new OrderLineBean());
        WebDocumentBean webBean =null;
        try
        {
            webBean = OrderReferenceManager.getWebOrderBean(ctx, creditOrder); 
        }
        catch(InvalidAddressException e)
        {
            postGlobalError("error.invalid.address",request);
            return mapping.getInputForward();
        }
        
        ArrayList list = POSManager.populateOrderLines(ctx,creditOrder);
        WebDocumentBean documentBean=POSManager.calculateOrderTotals(list);
        request.getSession().setAttribute(Constants.CURRENT_POS_ORDER_ID,Integer.valueOf(creditOrder.get_ID()));
        request.getSession().setAttribute(Constants.PRESENT_CREDIT_ORDER,creditOrder);
        request.getSession().setAttribute(Constants.ORDER_LINE_BEAN,bean);
        request.getSession().setAttribute(Constants.CREDITORDER_BPARTNER,bPartnerId);
        request.setAttribute(Constants.POS_ORDER_LINES,list);
        request.getSession().removeAttribute(Constants.PRESENT_CREDIT_ORDER);
        request.getSession().removeAttribute(Constants.PRESENT_CREDIT_ORDER_ID);
        SessionStorage.putCompleteOrderInfo(ctx,request,webBean,documentBean);
        return mapping.findForward(CREATE_CREDIT_ORDER);
    }
    
    
    public static final String COMPLETE_CREDIT_ORDER="completeCreditOrder";
    public ActionForward completeCreditOrder(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx=TmkJSPEnv.getCtx(request);
        DefaultForm df= (DefaultForm) form;
        OrderLineBean b = (OrderLineBean) df.getBean();
        
        OrderLineBean bean=(OrderLineBean) request.getSession().getAttribute(Constants.ORDER_LINE_BEAN);
        bean.setAmountGiven(b.getAmountGiven());
        bean.setAmountRefunded(b.getAmountRefunded());
        
        Integer orderId =  (Integer) request.getSession().getAttribute(Constants.CURRENT_POS_ORDER_ID);
        MOrder completedCreditOrder=null; 
        
        if(OrderManager.isOrderEmpty(ctx,orderId.intValue(),null))
        {
            postGlobalError("error.pos.no.orderline",request); 
            request.getSession().removeAttribute(Constants.CREDIT_ORDER_SHOPPING_CART);
            request.getSession().removeAttribute(Constants.CREDIT_ORDER_SHOPPING_CART_ITEMS);
            
            return mapping.getInputForward();
        }
        
        Trx trx = Trx.get(TrxPrefix.getPrefix(),true);
        try
        {
            trx.start();
            completedCreditOrder=CreditOrderManager.completeCreditOrder(ctx,orderId.intValue());
            trx.commit();           
        }
        catch(BPartnerOverCreditLimitException e1)
        {
            trx.rollback();
            postGlobalError("error.bpartner.over.credit.limit",e1.getMessage(),request);
            return mapping.getInputForward();
        }
        catch(OperationException e2)
        {
            trx.rollback();
            throw e2; 
        }
        finally
        {
            trx.close();
        }      
        
        
        df.populate(new OrderLineBean());
        WebDocumentBean webBean = OrderReferenceManager.getWebOrderBean(ctx, completedCreditOrder);
        ArrayList list = POSManager.populateOrderLines(ctx,completedCreditOrder);
        WebDocumentBean documentBean=POSManager.calculateOrderTotals(list);
        request.getSession().setAttribute(Constants.CURRENT_POS_ORDER_ID,completedCreditOrder.get_ID());
        request.setAttribute(Constants.POS_ORDER_LINES,list);
        SessionStorage.putCompleteOrderInfo(ctx,request,webBean,documentBean);
        request.getSession().removeAttribute(Constants.ALL_CUSTOMERS);
        request.getSession().removeAttribute(Constants.CREDIT_ORDER_SHOPPING_CART);
        request.getSession().removeAttribute(Constants.CREDIT_ORDER_SHOPPING_CART_ITEMS);
        request.getSession().removeAttribute(Constants.CURRENT_POS_ORDER_ID);
        request.getSession().removeAttribute(Constants.CREDITORDER_BPARTNER);
        request.getSession().removeAttribute(Constants.SHIPMENT_REQUIRED);
        request.getSession().removeAttribute(Constants.TO_BE_SHIPPED);
       // String reportName = POSReportManager.getInvoicePDFReport(ctx,completedCreditOrder.getC_Invoice_ID(),null);
       // String reportURI = ReportManager.getReportURI(reportName,request);
        //request.getSession().setAttribute(Constants.REPORT_URL,reportURI);
        return mapping.findForward(COMPLETE_CREDIT_ORDER);
    }
    
    public ActionForward validateAdvanceOrderPIN(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws IOException, OperationException
    {
    	Properties ctx=TmkJSPEnv.getCtx(request);      
    	
    	DefaultForm df = (DefaultForm) form;    	
    	String userPIN = df.getUserPIN();
    	
    	LoginBean bean = null;
    	LoginManager loginManager = new LoginManager();
    	String script = null;
    	
    	try 
    	{
			bean = loginManager.getUserNameFromPIN(ctx, userPIN);
			WebUser wu = loginManager.checkLoginPassword(ctx, bean);
						
			int roleId = Env.getAD_Role_ID(ctx);
			
			BigDecimal discountAllowed = RoleManager.getDiscountAllowed(ctx, roleId, null);		
			
			if(discountAllowed != Env.ZERO)
			{
				script = "setAction(document.forms[0],'GetCreditOrderDiscDetailsAction.do','getCreditOrderDiscDetails');" +
	               			 "disableButtons();";	
				
				request.getSession().setAttribute(Constants.DISCOUNT_ALLOWED, discountAllowed);
			}
			else
			{
				script = "PINError('It seems that you are not authorised to give discounts.');";
			}
		} 
    	catch (DuplicatePINException e) 
    	{
    		script = "PINError('It seems that you are not authorised to give discounts.');";
		}
    	catch (InvalidPINException e) 
    	{
    		script = "PINError('It seems that you are not authorised to give discounts.');";
		} 
    	catch (UserNotFoundException e) 
		{
			
		} 
    	catch (UserInactiveException e) 
    	{
			
		} 
    	catch (NotLoggedInException e) 
		{
			
		} 
    	catch (SQLException e) 
		{
			throw new OperationException(e);
		} 
    	catch (OperationException e) 
		{
    		throw new OperationException(e);
		}
    	
    	PrintWriter writer = response.getWriter();        
		writer.print( script );
		writer.flush();
		writer.close();
		
    	return null;
    }
    
    
    public static final String GET_ALLOCATION_DETAILS_FOR_PARTNER = "getAllocationDetailsForPartner";
    public  ActionForward getAllocationDetailsForPartner(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx=TmkJSPEnv.getCtx(request);
        DefaultForm df= (DefaultForm) form;
        PaymentAllocationBean bean=(PaymentAllocationBean) df.getBean();
       ArrayList allocationList = CreditOrderManager.viewAllocation(ctx,bean.getPartnerName(),bean.getInvoiceNo());
        request.getSession().setAttribute(Constants.PAYMENT_ALLOCATIONS,allocationList);
        return mapping.findForward(GET_ALLOCATION_DETAILS_FOR_PARTNER);
    }
    
    public static final String INIT_CREATE_CREDIT_ORDER = "initCreateCreditOrder";
    public  ActionForward initCreateCreditOrder(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx=TmkJSPEnv.getCtx(request);
    
        ArrayList list = PaymentTermManager.getAllActivePaymentTerm(ctx);
        request.getSession().setAttribute (Constants.PAYMENT_TERM,list);
        return mapping.findForward(INIT_CREATE_CREDIT_ORDER);
    }
    
    
    public static final String CREATE_SHIPMENT_CREDIT_ORDER = "createShipmentForCreditOrder";
    public  ActionForward createShipmentForCreditOrder(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx=TmkJSPEnv.getCtx(request);
        DocumentHistoryForm dhf = (DocumentHistoryForm) form;
        DocumentHistoryBean bean = (DocumentHistoryBean) dhf.getBean();
        MInOut shipment=CreditOrderManager.createAndCompleteShipment(ctx,bean.getInvoiceId());
        return new ActionForward("/ViewMinOutAction.do?action=viewOrder&documentId="+shipment.get_ID());
    }
    
    
    
    public static final String GET_MULTIPLE_INVOICE_FOR_PAYMENT = "getMultipleInvoicesForPayment";
    public  ActionForward getMultipleInvoicesForPayment(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx=TmkJSPEnv.getCtx(request);
        OpenItemForm oif= (OpenItemForm) form;
        OpenItemBean bean=(OpenItemBean) oif.getBean();
        ArrayList list=null;
        try
        {
        	 list= PaymentAllocationManager.getInvoiceToAllocate(ctx,bean.getInvoiceIds(),null);
        }
        catch(NoAllocateeInvoiceException e2)
        {
            postGlobalError("error.no.invoice.to.allocate",request);
            return mapping.getInputForward();
        }
        request.getSession().setAttribute (Constants.INVOICE_FOR_ALLOCATION,list);
        return mapping.findForward(GET_MULTIPLE_INVOICE_FOR_PAYMENT);
    }
}
