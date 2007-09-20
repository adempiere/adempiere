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
 * Created on 14-Mar-2006
 */


package org.posterita.struts.pos;

import java.io.IOException;
import java.io.OutputStream;
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
import org.compiere.model.MBPartner;
import org.compiere.model.MCurrency;
import org.compiere.model.MOrder;
import org.compiere.model.MPayment;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.compiere.util.WebUser;
import org.posterita.Constants;
import org.posterita.beans.CurrentTillAmountBean;
import org.posterita.beans.LoginBean;
import org.posterita.beans.OrderBean;
import org.posterita.beans.OrderLineBean;
import org.posterita.beans.RemoveSessionBean;
import org.posterita.beans.ReportBean;
import org.posterita.beans.ShoppingCartBean;
import org.posterita.beans.WebDocumentBean;
import org.posterita.businesslogic.BPartnerManager;
import org.posterita.businesslogic.LoginManager;
import org.posterita.businesslogic.OrderManager;
import org.posterita.businesslogic.OrderReferenceManager;
import org.posterita.businesslogic.POSManager;
import org.posterita.businesslogic.POSTerminalManager;
import org.posterita.businesslogic.PrintManager;
import org.posterita.businesslogic.ReportDateManager;
import org.posterita.businesslogic.RoleManager;
import org.posterita.businesslogic.StockManager;
import org.posterita.businesslogic.UserManager;
import org.posterita.core.ContextId;
import org.posterita.core.SessionStorage;
import org.posterita.core.TmkJSPEnv;
import org.posterita.core.TrxPrefix;
import org.posterita.core.businesslogic.ElementManager;
import org.posterita.exceptions.ApplicationException;
import org.posterita.exceptions.BPartnerNoLocationException;
import org.posterita.exceptions.BPartnerOverCreditLimitException;
import org.posterita.exceptions.DiscountLimitException;
import org.posterita.exceptions.DuplicatePINException;
import org.posterita.exceptions.InputQuantityLessThanZeroException;
import org.posterita.exceptions.InsufficientQtyException;
import org.posterita.exceptions.InvalidAddressException;
import org.posterita.exceptions.InvalidPINException;
import org.posterita.exceptions.InvalidTenderedAmountException;
import org.posterita.exceptions.LimitPriceVioletException;
import org.posterita.exceptions.NoOrderLineSelectedException;
import org.posterita.exceptions.NotLoggedInException;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.ProductNotFoundException;
import org.posterita.exceptions.ProductNotOnPriceListException;
import org.posterita.exceptions.QuantityNotAvailableException;
import org.posterita.exceptions.UserInactiveException;
import org.posterita.exceptions.UserNotFoundException;
import org.posterita.form.RemoveSessionForm;
import org.posterita.lib.UdiConstants;
import org.posterita.order.UDIOrderTypes;
import org.posterita.struts.core.DefaultForm;
import org.posterita.util.OrderFormatter;


public class POSOrderAction extends POSDispatchAction
{
    public static final String CREATE_POS_ORDER="createPOSOrder";
    public ActionForward createPOSOrder(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx=TmkJSPEnv.getCtx(request); 
        DefaultForm df= (DefaultForm) form;
        OrderLineBean bean=(OrderLineBean) df.getBean();
        
        bean.getPaymentByCash();
        bean.getPaymentByCard();
        bean.getPaymentByChq();
        
        ShoppingCartBean cartBean = (ShoppingCartBean) request.getSession().getAttribute(Constants.SHOPPING_ORDER_CART);
        
        if(cartBean==null ||cartBean.getItems() == null||cartBean.getItems().size()==0)
        {           
            postGlobalError("error.pos.shoppingcart.empty",request);             
            return mapping.getInputForward();
        }
        
        String[] discountPercentage = df.getDiscountPercent();
        String[] actualPrice = df.getActualPrice();
        double d = 0.0d;
        
        if(( discountPercentage != null ) && ( discountPercentage.length != 0 ))
        {        	            
            for(String s : discountPercentage)
            {
                if(s == null || s == "")
                {
                    continue;
                }
                
                try 
                {
                    d = Double.parseDouble(s);
                    
                    if(d < 0)
                    {
                    	postGlobalError("error.discount.nagative",request);
                        return mapping.getInputForward();
                    }
                } 
                catch (NumberFormatException e) 
                {
                    postGlobalError("errors.double","Discount(s)",request);
                    return mapping.getInputForward();
                }
            }
            
            for(String s : actualPrice)
            {
                if(s == null || s == "")
                {
                    continue;
                }
                
                try 
                {
                    Double.parseDouble(s);
                } 
                catch (NumberFormatException e) 
                {
                    postGlobalError("errors.double","Price(s)",request);
                    return mapping.getInputForward();
                }
            }
        }
        
        
        
        Integer bPartnerId = bean.getBpartnerId();
        
        if(bPartnerId==null)
            bPartnerId = (Integer) request.getSession().getAttribute(Constants.BPARTNER);
        
        if(bPartnerId==null)
        {
            String orderType = df.getOrderType();
            if(orderType!=null &&  orderType.equalsIgnoreCase( Constants.POS_ORDER_CUSTOMER_COMPULSORY ))
            {
                postGlobalError("error.required" ,"customer" ,request);             
                return mapping.getInputForward();
            }
            
            bPartnerId=Integer.valueOf(POSTerminalManager.getPOSDefaultBpartner(ctx).get_ID());
        }
        else
        {
            boolean isBPartnerPresent = BPartnerManager.isBPartnerPresent(ctx,bPartnerId.intValue(),null);
            
            if( !isBPartnerPresent )
            {
                postGlobalError("error.pos.invalid.customerID",request);             
                return mapping.getInputForward();
            }
        }
        
        // VALIDATING DISCOUNT AUTHORISATION <=========
        BigDecimal discountAllowed = (BigDecimal) request.getSession().getAttribute(Constants.DISCOUNT_ALLOWED);
        
        
        bean.setUserDiscount(discountAllowed);
        bean.setBpartnerId(bPartnerId);
        
        //check if the order is alredy created
        MOrder previousOrder= null;
        previousOrder=(MOrder)request.getSession().getAttribute(Constants.PRESENT_POS_ORDER);
        
        if(previousOrder!=null)
        {
            bean.setOrderId(Integer.valueOf(previousOrder.get_ID()));
        }
        else
        {
            bean.setOrderId(null);
        }
        
        Trx trx = Trx.get(TrxPrefix.getPrefix(),true);
        
        MOrder posOrder=null;
        try
        {
            trx.start();
            posOrder= POSManager.createPOSOrder(ctx,bean,cartBean.getItems(),trx.getTrxName());
            trx.commit();
        }
        catch(BPartnerOverCreditLimitException e1)
        {
            trx.rollback();
            postGlobalError("error.bpartner.over.credit.limit",e1.getMessage(),request);
            return mapping.getInputForward();
        }
        catch(BPartnerNoLocationException ex)
        {
            trx.rollback();
            postGlobalError("error.bpartner.nolocation", request);
            return mapping.getInputForward();
        }
        catch(InvalidTenderedAmountException e)
        {
            trx.rollback();
            postGlobalError("error.pos.incorrect.tendered.amount",request);
            return mapping.getInputForward();
        } 
        catch (DiscountLimitException e) 
        {
            trx.rollback();
            postGlobalError("error.discountLimit.exceeded",request);
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
        
        
        finally
        {
            trx.close();
        }
        
        bean.setOrderId(Integer.valueOf(posOrder.get_ID()));
        
        df.populate(new OrderLineBean());
        WebDocumentBean webBean =null;
        try
        {
            webBean = OrderReferenceManager.getWebOrderBean(ctx, posOrder); 
        }
        catch(InvalidAddressException e)
        {
            postGlobalError("error.invalid.address",request);
            return mapping.getInputForward();
        }
        
        if(posOrder.getPaymentRule().equals(UdiConstants.PAYMENTRULE_MIXED))
        {
            if(bean.getPaymentByCash()!=null)
                webBean.setPaymentByCash(bean.getPaymentByCash());
            if(bean.getPaymentByCard()!=null)
                webBean.setPaymentByCard(bean.getPaymentByCard());
            if(bean.getPaymentByChq()!=null)
                webBean.setPaymentbyCheque(bean.getPaymentByChq());
        }
        
        
        ArrayList list = POSManager.populateOrderLines(ctx,posOrder);
        WebDocumentBean documentBean=POSManager.calculateOrderTotals(list);
        request.getSession().setAttribute(Constants.CURRENT_POS_ORDER_ID,Integer.valueOf(posOrder.get_ID()));
        request.getSession().setAttribute(Constants.PRESENT_POS_ORDER,posOrder);
        request.getSession().setAttribute(Constants.ORDER_LINE_BEAN,bean);
        request.getSession().setAttribute(Constants.BPARTNER,bPartnerId);
        request.setAttribute(Constants.POS_ORDER_LINES,list);
        request.getSession().removeAttribute(Constants.PRESENT_POS_ORDER);
        request.getSession().removeAttribute(Constants.PRESENT_POS_ORDER_ID);
        
        
        SessionStorage.putCompleteOrderInfo(ctx,request,webBean,documentBean);
        
        //remove any previously set discount
        request.getSession().removeAttribute(Constants.DISCOUNT_ALLOWED);         
        
        return mapping.findForward(CREATE_POS_ORDER);
    }
    
    public static final String COMPLETE_POS_ORDER="completePOSOrder";
    public ActionForward completePOSOrder(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx=TmkJSPEnv.getCtx(request);
        DefaultForm df= (DefaultForm) form;
        OrderLineBean b = (OrderLineBean) df.getBean();
        
        OrderLineBean bean=(OrderLineBean) request.getSession().getAttribute(Constants.ORDER_LINE_BEAN);
        
        
        if(bean==null)
        {
            bean = new OrderLineBean();
        }
        bean.setAmountGiven(b.getAmountGiven());
        bean.setAmountRefunded(b.getAmountRefunded());
        
        Integer orderId =  (Integer) request.getSession().getAttribute(Constants.CURRENT_POS_ORDER_ID);
        MOrder completedPOSOrder=null; 
        
        
        if(orderId==null)
        {
            postGlobalError("error.no.order.exists",request);
            return mapping.getInputForward();
        }
        
        
        if(OrderManager.isOrderEmpty(ctx,orderId.intValue(),null))
        {
            postGlobalError("error.pos.no.orderline",request); 
            request.getSession().removeAttribute(Constants.SHOPPING_ORDER_CART);
            request.getSession().removeAttribute(Constants.SHOPPING_ORDER_CART_ITEMS);            
            
            return mapping.getInputForward();
        }
        
        
        
        Trx trx = Trx.get(TrxPrefix.getPrefix(),true);
        
        try
        {
            trx.start();
            completedPOSOrder=POSManager.completePOSOrder(ctx,orderId.intValue(),bean,null);
            trx.commit();           
        }
        catch(BPartnerOverCreditLimitException e1)
        {
            trx.rollback();
            MOrder posOrder = new MOrder(ctx,orderId.intValue(),null);
            ArrayList list = POSManager.populateOrderLines(ctx,posOrder);
            request.setAttribute(Constants.POS_ORDER_LINES,list);
            postGlobalError("error.bpartner.over.credit.limit",e1.getMessage(),request);
            return mapping.getInputForward();
        }
        catch(InvalidTenderedAmountException e)
        {
            trx.rollback();
            MOrder posOrder = new MOrder(ctx,orderId.intValue(),null);
            ArrayList list = POSManager.populateOrderLines(ctx,posOrder);
            request.setAttribute(Constants.POS_ORDER_LINES,list);
            postGlobalError("error.required.amount.given",request); 
            return mapping.getInputForward();
        }
        catch(OperationException e)
        {
            trx.rollback();
            MOrder posOrder = new MOrder(ctx,orderId.intValue(),null);
            ArrayList list = POSManager.populateOrderLines(ctx,posOrder);
            request.setAttribute(Constants.POS_ORDER_LINES,list);
            throw e; 
        }
        finally
        {
            trx.close();
        }      
        
        
        df.populate(new OrderLineBean());
        WebDocumentBean webBean = OrderReferenceManager.getWebOrderBean(ctx, completedPOSOrder);
        
        if(completedPOSOrder.getPaymentRule().equals(UdiConstants.PAYMENTRULE_MIXED))
        {
            POSManager.getAmountForMixedPayment(ctx,completedPOSOrder,webBean);
        }
        
        ArrayList list = POSManager.populateOrderLines(ctx,completedPOSOrder);
        WebDocumentBean documentBean=POSManager.calculateOrderTotals(list);
        request.getSession().setAttribute(Constants.CURRENT_POS_ORDER_ID,completedPOSOrder.get_ID());
        request.setAttribute(Constants.POS_ORDER_LINES,list);
        
        SessionStorage.putCompleteOrderInfo(ctx,request,webBean,documentBean);
        /*request.removeAttribute(Constants.POS_ORDER_LINES);*/
        request.getSession().removeAttribute(Constants.ALL_CUSTOMERS);
        request.getSession().removeAttribute(Constants.BPARTNER);
        request.getSession().removeAttribute(Constants.SHOPPING_ORDER_CART);        
        request.getSession().removeAttribute(Constants.SHOPPING_ORDER_CART_ITEMS);
        request.getSession().removeAttribute(Constants.CURRENT_POS_ORDER_ID);
        
        
        //fix for invoke partial pos order
        request.getSession().removeAttribute(Constants.PARTIAL_ORDER_SHOPPING_CART);
        request.getSession().removeAttribute(Constants.PARTIAL_ORDER_SHOPPING_CART_ITEMS);
        request.getSession().removeAttribute(Constants.CURRENT_PARTIAL_POS_ORDER_ID);
        request.getSession().removeAttribute(Constants.PRESENT_POS_ORDER);
        request.getSession().removeAttribute( Constants.PRESENT_POS_ORDER_ID );
        
        //setting card & check tendered amount
        BigDecimal cardTendered = POSManager.getPayment(ctx,completedPOSOrder.get_ID(),MPayment.TENDERTYPE_CreditCard,trx.getTrxName());
        BigDecimal chequeTendered = POSManager.getPayment(ctx,completedPOSOrder.get_ID(),MPayment.TENDERTYPE_Check,trx.getTrxName());
        
        request.setAttribute(Constants.CARD_AMT_TENDERED, cardTendered);
        request.setAttribute(Constants.CHEQUE_AMT_TENDERED, chequeTendered);
        
        return mapping.findForward(COMPLETE_POS_ORDER);
    }
    
    
    public static final String COMPLETE_POS_ORDER_PRINT_INVOICE="completePOSOrderPrintInvoice";
    public ActionForward completePOSOrderPrintInvoice(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
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
        MOrder completedPOSOrder=null; 
        
        
        if(OrderManager.isOrderEmpty(ctx,orderId.intValue(),null))
        {
            postGlobalError("error.pos.no.orderline",request); 
            request.getSession().removeAttribute(Constants.SHOPPING_ORDER_CART);
            request.getSession().removeAttribute(Constants.SHOPPING_ORDER_CART_ITEMS);
            
            return mapping.getInputForward();
        }
        
        
        
        Trx trx = Trx.get(TrxPrefix.getPrefix(),true);
        
        try
        {
            trx.start();
            completedPOSOrder=POSManager.completePOSOrder2(ctx,orderId.intValue(),bean,null);            
            trx.commit();           
        }
        catch(BPartnerOverCreditLimitException e1)
        {
            trx.rollback();
            postGlobalError("error.bpartner.over.credit.limit",e1.getMessage(),request);
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
        
        
        df.populate(new OrderLineBean());
        WebDocumentBean webBean = OrderReferenceManager.getWebOrderBean(ctx, completedPOSOrder);
        
        if(completedPOSOrder.getPaymentRule().equals(UdiConstants.PAYMENTRULE_MIXED))
        {
            POSManager.getAmountForMixedPayment(ctx,completedPOSOrder,webBean);
        }
        
        ArrayList list = POSManager.populateOrderLines(ctx,completedPOSOrder);
        WebDocumentBean documentBean=POSManager.calculateOrderTotals(list);
        request.getSession().setAttribute(Constants.CURRENT_POS_ORDER_ID,completedPOSOrder.get_ID());
        request.setAttribute(Constants.POS_ORDER_LINES,list);
        SessionStorage.putCompleteOrderInfo(ctx,request,webBean,documentBean);
        
        /*request.removeAttribute(Constants.POS_ORDER_LINES);*/
        request.getSession().removeAttribute(Constants.ALL_CUSTOMERS);
        request.getSession().removeAttribute(Constants.SHOPPING_ORDER_CART);        
        request.getSession().removeAttribute(Constants.SHOPPING_ORDER_CART_ITEMS);
        request.getSession().removeAttribute(Constants.CURRENT_POS_ORDER_ID);
        
        return mapping.findForward(COMPLETE_POS_ORDER_PRINT_INVOICE);
    }
    
    
    public static final String CREATE_AND_COMPLETE_POS_ORDER="createAndCompletePOSOrder";
    public ActionForward createAndCompletePOSOrder(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx=TmkJSPEnv.getCtx(request);
        DefaultForm df= (DefaultForm) form;
        OrderLineBean bean=(OrderLineBean) df.getBean();
        
        bean.getPaymentByCash();
        bean.getPaymentByCard();
        bean.getPaymentByChq();
        
        
        ShoppingCartBean cartBean = (ShoppingCartBean) request.getSession().getAttribute(Constants.SHOPPING_ORDER_CART);
        
        if(cartBean==null ||cartBean.getItems() == null||cartBean.getItems().size()==0)
        {           
            postGlobalError("error.pos.shoppingcart.empty",request);             
            return mapping.getInputForward();
        }
        
        //MOrder posOrder=(MOrder) request.getSession().getAttribute(Constants.CURRENT_POS_ORDER);
        
        Integer bPartnerId = bean.getBpartnerId();
        
        if(bPartnerId==null)
            bPartnerId = (Integer) request.getSession().getAttribute(Constants.BPARTNER);
        
        if(bPartnerId==null)
        {
            bPartnerId=Integer.valueOf(POSTerminalManager.getPOSDefaultBpartner(ctx).get_ID());
        }
        else
        {
            boolean isBPartnerPresent = BPartnerManager.isBPartnerPresent(ctx,bPartnerId.intValue(),null);
            
            if( !isBPartnerPresent )
            {
                postGlobalError("error.pos.invalid.customerID",request);             
                return mapping.getInputForward();
            }
        }
        
        bean.setBpartnerId(bPartnerId);
        
        MOrder completedPOSOrder = null;
        
        try 
        {
            completedPOSOrder = POSManager.createAndCompletePOSOrder(ctx,bean,cartBean.getItems());
        } 
        catch(BPartnerOverCreditLimitException e1)
        {
            
            postGlobalError("error.bpartner.over.credit.limit",e1.getMessage(),request);
            return mapping.getInputForward();
        }
        catch(BPartnerNoLocationException ex)
        {
            postGlobalError("error.bpartner.nolocation", request);
            return mapping.getInputForward();
        }
        catch (DiscountLimitException e)
        {
            postGlobalError("error.discountLimit.exceeded",request);             
            return mapping.getInputForward();
        }
        
        WebDocumentBean webBean = OrderReferenceManager.getWebOrderBean(ctx, completedPOSOrder);
        
        ArrayList list = POSManager.populateOrderLines(ctx,completedPOSOrder);
        WebDocumentBean documentBean=POSManager.calculateOrderTotals(list);
        request.getSession().setAttribute(Constants.CURRENT_POS_ORDER_ID,completedPOSOrder.get_ID());
        request.setAttribute(Constants.POS_ORDER_LINES,list);
        
        /*request.removeAttribute(Constants.POS_ORDER_LINES);*/
        request.getSession().removeAttribute(Constants.ALL_CUSTOMERS);
        request.getSession().removeAttribute(Constants.SHOPPING_ORDER_CART);        
        request.getSession().removeAttribute(Constants.SHOPPING_ORDER_CART_ITEMS);
        request.getSession().removeAttribute(Constants.CURRENT_POS_ORDER_ID);
        
        SessionStorage.putCompleteOrderInfo(ctx,request,webBean,documentBean);
        
        return mapping.findForward(CREATE_AND_COMPLETE_POS_ORDER);
    }    
    
    public static final String GET_POS_PAYMENT_DETAILS="getPOSPaymentDetails";
    public ActionForward getPOSPaymentDetails(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        MOrder order=null;
        Properties ctx=TmkJSPEnv.getCtx(request);
        DefaultForm df= (DefaultForm) form;
        OrderLineBean bean=(OrderLineBean) df.getBean();
        df.populate(new OrderLineBean());
        if(bean.getBpartnerId()!=null)
            request.getSession().setAttribute(Constants.BPARTNER, bean.getBpartnerId());
        
        else if(bean.getOrderId()!=null)
        {
            order = new MOrder(ctx, bean.getOrderId(), null);
            bean.setBpartnerId(order.getC_BPartner_ID());
            request.getSession().setAttribute(Constants.BPARTNER, bean.getBpartnerId());
            
        }
        ShoppingCartBean cartBean = (ShoppingCartBean) request.getSession().getAttribute(Constants.SHOPPING_ORDER_CART);
        if(cartBean==null)
        {           
            postGlobalError("error.pos.shoppingcart.empty",request);             
            return mapping.getInputForward();
        }
        request.getSession().setAttribute(Constants.PRESENT_POS_ORDER,order);
        request.getSession().setAttribute(Constants.SHOPPING_ORDER_CART, cartBean);
        request.getSession().setAttribute(Constants.SHOPPING_ORDER_CART_ITEMS, cartBean.getItems());
        return mapping.findForward(GET_POS_PAYMENT_DETAILS);
    }
    
    public static final String GET_POS_CUSTOMERS="getPOSCustomers";
    public ActionForward getPOSCustomers(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        //Properties ctx=TmkJSPEnv.getCtx(request);
        DefaultForm df= (DefaultForm) form;
        //OrderLineBean bean=(OrderLineBean) df.getBean();
        df.populate(new OrderLineBean());
        ShoppingCartBean cartBean = (ShoppingCartBean) request.getSession().getAttribute(Constants.SHOPPING_ORDER_CART);
        
        if(cartBean == null||cartBean.getItems() == null||cartBean.getItems().size() == 0)
        {
            postGlobalError("error.pos.shoppingcart.empty",request);
            return mapping.getInputForward();
        }
        request.getSession().setAttribute(Constants.SHOPPING_ORDER_CART, cartBean);
        request.getSession().setAttribute(Constants.SHOPPING_ORDER_CART_ITEMS, cartBean.getItems());
        
        
        return mapping.findForward(GET_POS_CUSTOMERS);
    }
    
    public ActionForward deletePOSOrderLine(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx=TmkJSPEnv.getCtx(request);
        DefaultForm df= (DefaultForm) form;
        OrderLineBean bean=(OrderLineBean) df.getBean();
        
        String forward = null;		
        
        Trx trx = Trx.get(TrxPrefix.getPrefix(),true);
        
        try 
        {
            trx.start();
            forward = POSManager.deleteOrderLines(ctx,bean.getPosOrderLineIds(),trx.getTrxName());
            trx.commit();
        } 
        catch (NoOrderLineSelectedException e) 
        {
            trx.rollback();
            SessionStorage.putPOSOrderInfo(ctx,bean,request,forward);
            
            postGlobalError("error.order.noOrderline.selected",request);			
            return mapping.getInputForward();
        }
        catch(OperationException ex)
        {
            trx.rollback();
            throw new OperationException("Could not delete orderline. Reason : " + ex);
        }
        finally
        {
            trx.close();
        }
        
        Integer orderId =  getCurrentOrderId(mapping, form, request, response);		
        //validating order
        //if lines == 0 goto createpartialOrder
        
        //reload shopping cart
        ShoppingCartBean cart = POSManager.getShoppingCartForOrder(ctx,orderId.intValue(),null);
        String shoppingCart = null;
        String shoppingCartItem = null;
        String input = null;
        
        String orderType = bean.getOrderType();
        
        if(orderType.equals(Constants.POS_ORDER))
        {
            input = "/CreatePOSOrder.do";
            
            shoppingCart     = Constants.SHOPPING_ORDER_CART;
            shoppingCartItem = Constants.SHOPPING_ORDER_CART_ITEMS;
        }
        else if(orderType.equals(Constants.GOODS_RECEIVE_NOTE))
        {
            input = "/GetAllPOSVendor.do?action=getAllVendors&isSales=true";
            
            shoppingCart     = Constants.GOODS_RECEIVE_NOTE_SHOPPING_CART;
            shoppingCartItem = Constants.GOODS_RECEIVE_NOTE_SHOPPING_CART_ITEMS;
        }
        else if(orderType.equals(Constants.GOODS_RETURN_NOTE))
        {
            input = "/GetAllPOSVendor.do?action=getAllVendors&isSales=false";
            
            shoppingCart     = Constants.GOODS_RETURN_NOTE_SHOPPING_CART;
            shoppingCartItem = Constants.GOODS_RETURN_NOTE_SHOPPING_CART_ITEMS;
        }
        else if(orderType.equals(Constants.CUSTOMER_RETURN_ORDER))
        {
            input = "/CustomerReturnOrder.do";
            
            shoppingCart     = Constants.CUSTOMER_RETURN_ORDER_SHOPPING_CART;
            shoppingCartItem = Constants.CUSTOMER_RETURN_ORDER_SHOPPING_CART_ITEMS;
        }
        else
        {
            
        }  
        
        request.getSession().setAttribute(shoppingCart,cart);
        request.getSession().setAttribute(shoppingCartItem,cart.getItems());
        
        
        if(OrderManager.isOrderEmpty(ctx,orderId.intValue(),null))
        {
            request.getSession().removeAttribute(Constants.ALL_CUSTOMERS);
            request.getSession().removeAttribute(shoppingCart);
            request.getSession().removeAttribute(shoppingCartItem);
            return new ActionForward(input);
        }
        
        SessionStorage.putPOSOrderInfo(ctx,bean,request,forward);
        df.populate(new OrderLineBean());
        return mapping.findForward(forward);
    }
    
    public static final String VIEW_POS_INFO="viewPOSInfo";
    public ActionForward viewPOSInfo(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx=TmkJSPEnv.getCtx(request);
        DefaultForm df= (DefaultForm) form;
        
        ReportBean bean = (ReportBean) df.getBean();
        
        String fromdate = ReportDateManager.getFromDate(bean);
        String todate = ReportDateManager.getToDate(bean);
        
        ArrayList list=POSManager.getPOSInfo(ctx,fromdate,todate);
        request.getSession().setAttribute(Constants.POS_INFO,list);
        //df.populate(new OrderLineBean());
        
        
        return mapping.findForward(VIEW_POS_INFO);
    } 
    
    
  
    public static final String INCREMENT_QTY = "incrementQty";
    public ActionForward incrementQty(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        addToShoppingCart(mapping,form,request,response);
        return getShoppingCart(mapping,form,request,response);
    }
    
    public static final String DECREMENT_QTY = "decrementQty";
    public ActionForward decrementQty(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        addToShoppingCart(mapping,form,request,response);
        return getShoppingCart(mapping,form,request,response);
    }
    
    
    public static final String ADD_TO_SHOPPING_CART = "addToShoppingCart";
    public ActionForward addToShoppingCart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException
    {
        Properties ctx = TmkJSPEnv.getCtx(request);
        DefaultForm df = (DefaultForm) form;
        OrderLineBean bean = (OrderLineBean) df.getBean();
        // ShoppingCartBean cartBean = (ShoppingCartBean) request.getSession().getAttribute(Constants.SHOPPING_ORDER_CART);
        String orderType = bean.getOrderType();
        if(orderType==null)
        {
            orderType=(String)request.getSession().getAttribute(Constants.ORDER_TYPE);
            bean.setOrderType(orderType);
            df.populate(bean);
        }
        String shoppingCart = null;
        String shoppingCartItem = null;
        String path=null;
        
        
        
        if(orderType.equalsIgnoreCase(Constants.POS_ORDER))
        {
            shoppingCart     = Constants.SHOPPING_ORDER_CART;
            shoppingCartItem = Constants.SHOPPING_ORDER_CART_ITEMS;
            path="/jsp/pos/createPOSOrder.jsp";
            
            
        }
                
        if(orderType.equalsIgnoreCase(Constants.POS_ORDER_WITHOUT_ADVANCED))
        {
            shoppingCart     = Constants.SHOPPING_ORDER_CART;
            shoppingCartItem = Constants.SHOPPING_ORDER_CART_ITEMS;
            path="/jsp/pos/createPOSOrderWithoutAdvance.jsp";
            
            
        }
        
        if(orderType.equalsIgnoreCase(Constants.QUICK_POS_ORDER))
        {
            shoppingCart     = Constants.SHOPPING_ORDER_CART;
            shoppingCartItem = Constants.SHOPPING_ORDER_CART_ITEMS;
            path="/jsp/pos/createPOSOrder3.jsp";
            
            
        }
        
        if(orderType.equalsIgnoreCase(Constants.POS_ORDER_CUSTOMER_COMPULSORY))
        {
            shoppingCart     = Constants.SHOPPING_ORDER_CART;
            shoppingCartItem = Constants.SHOPPING_ORDER_CART_ITEMS;
            path="/jsp/pos/createPOSOrder2.jsp";
            
            
        }
        else if(orderType.equalsIgnoreCase(Constants.CREDIT_ORDER))
        {
            shoppingCart     = Constants.CREDIT_ORDER_SHOPPING_CART;
            shoppingCartItem = Constants.CREDIT_ORDER_SHOPPING_CART_ITEMS;
            path="/jsp/pos/createCreditOrder.jsp";
        }      
        
        ShoppingCartBean cartBean = (ShoppingCartBean) request.getSession().getAttribute(shoppingCart);
        
        try
        {
            // POSManager.getQtyAndItem(bean);
            if(bean.getProductId()==null && bean.getBarCode()==null)
            {
                postGlobalError("error.barcode.required", request);
                return new ActionForward(path);
            }
            
            cartBean = StockManager.addToPOSCart(ctx, bean, cartBean,true,Boolean.parseBoolean(bean.getIfAdd()));            
        }
        catch (InputQuantityLessThanZeroException e1)        
        {
            postGlobalError("error.invalid.inputQty", request);
            return new ActionForward(path);
        }
        catch (ProductNotFoundException e2)
        {
            postGlobalError("error.product.not.found", e2.getMessage(), request);
            return new ActionForward(path);
        }
        catch(QuantityNotAvailableException e3)
        {
            postGlobalError("error.quantity.notAvailable", e3.getMessage(), request);
            return new ActionForward(path);
        }
        
        catch(ProductNotOnPriceListException e4)
        {
            postGlobalError("error.product.price.not.found", e4.getMessage(), request);
            return new ActionForward(path);
        }
        
        
        
        String currSymboleSales = POSTerminalManager.getPOSDefaultSellCurrency(ctx).getCurSymbol();
        request.setAttribute(Constants.CURRENCY_SYMBOLE,currSymboleSales);
        request.getSession().setAttribute(Constants.ORDER_TYPE,orderType);
        
        
        request.getSession().setAttribute(shoppingCart, cartBean);
        request.getSession().setAttribute(shoppingCartItem, cartBean.getItems());
        request.getSession().setAttribute(Constants.B_PARTNER_ID,bean.getBpartnerId());
        
        df.setQtyAndItem("");
        
        return new ActionForward(path);
        //return mapping.findForward(ADD_TO_SHOPPING_CART); 
    }
    
    
    
    public static final String CHOOSE_POS="choosePOS";
    
    public ActionForward choosePOS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx=TmkJSPEnv.getCtx(request);
        DefaultForm df= (DefaultForm) form;
        
        SessionStorage.putPOSID(ctx,df.getPosId(),request);
        POSManager.setPOSIDInCookie(response,df.getPosId());
        String currSymboleSales = POSTerminalManager.getPOSDefaultSellCurrency(ctx).getCurSymbol();
        String currSymbolePurchase=POSTerminalManager.getPOSDefaultPurchaseCurrency(ctx).getCurSymbol();
        request.getSession().setAttribute(Constants.CURRENCY_SYMBOLE,currSymboleSales);
        request.getSession().setAttribute(Constants.CURRENCY_SYMBOLE_PURCHASE,currSymbolePurchase);
        
        return mapping.findForward(CHOOSE_POS); 
    }
    
    public static final String GET_CURRENT_TILL_AMOUNT="getCurrentTillAmount";
    
    public ActionForward getCurrentTillAmount(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx=TmkJSPEnv.getCtx(request);
        //DefaultForm df= (DefaultForm) form;
        
        CurrentTillAmountBean bean = POSManager.getCurrentTillAmount(ctx);
        MCurrency currency = POSTerminalManager.getCurrencyOfDefaultCashBook(ctx);
        request.setAttribute(Constants.CURRENCY_SYMBOLE,currency.getCurSymbol());
        request.getSession().setAttribute(Constants.CURRENT_TILL_AMOUNT,bean);
        return mapping.findForward(GET_CURRENT_TILL_AMOUNT); 
    }
    
    public static final String OPEN_CASH_DRAWER="openCashDrawer";
    
    public ActionForward openCashDrawer(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException, IOException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        String cmd = PrintManager.getOpenDrawerCmd();
        
        response.setContentType("application/octet-stream");
        OutputStream os = response.getOutputStream();
        os.write(cmd.getBytes());
        os.flush();
        os.close();
        
        return null;
        //return mapping.findForward(OPEN_CASH_DRAWER);        
    }
    
    public static final String REPRINT_ORDER="reprintOrder";
    
    public ActionForward reprintOrder(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx=TmkJSPEnv.getCtx(request);
        
        DefaultForm df= (DefaultForm) form;
        
        OrderLineBean bean=(OrderLineBean)df.getBean();
        
        OrderManager.rePrintOrder(ctx,bean.getOrderId());
        
        return mapping.findForward(REPRINT_ORDER);        
    }
    
    public static final String DELETE_ORDER="deleteOrder";
    public ActionForward deleteOrder(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx=TmkJSPEnv.getCtx(request);
        
        DefaultForm df= (DefaultForm) form;
        
        OrderBean orderBean = (OrderBean)df.getBean();
        
        try
        {
            OrderManager.deleteDraftedOrder(ctx, orderBean.getOrderId(), null);
        }
        catch(OperationException ex)
        {
        	MOrder posOrder = new MOrder(ctx,orderBean.getOrderId(),null);
            ArrayList list = POSManager.populateOrderLines(ctx,posOrder);
            request.setAttribute(Constants.POS_ORDER_LINES,list);
            postGlobalError("error.order.delete", request);
            return mapping.getInputForward();
        }
        return mapping.findForward(DELETE_ORDER);        
    }
    
    public static Integer getCurrentOrderId(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
    {
        DefaultForm f = (DefaultForm)form;
        
        String orderType = f.getOrderType();
        Integer orderId = null;
        
        if(orderType.equals(Constants.POS_ORDER))
        {
            orderId = (Integer) request.getSession().getAttribute(Constants.CURRENT_POS_ORDER_ID);
        }
        
        if(orderType.equals(Constants.GOODS_RECEIVE_NOTE))
        {
            orderId = (Integer) request.getSession().getAttribute(Constants.GOODS_RECEIVE_NOTE_ID);
        }
        
        if(orderType.equals(Constants.GOODS_RETURN_NOTE))
        {
            orderId = (Integer) request.getSession().getAttribute(Constants.GOODS_RETURN_NOTE_ID);
        }
        
        if(orderType.equals(Constants.CUSTOMER_RETURN_ORDER))
        {
            orderId = (Integer) request.getSession().getAttribute(Constants.CUSTOMER_RETURN_ORDER_ID);
        }
        
        return orderId;
    }
    
    public static final String DELETE_POS_ORDER = "deletePOSOrder";
    public ActionForward deletePOSOrder(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;      
        
        Properties ctx=TmkJSPEnv.getCtx(request);      
        
        Integer orderId = (Integer) request.getSession().getAttribute( Constants.CURRENT_POS_ORDER_ID );
        
        if( orderId == null )
        {
            throw new OperationException("Cannot delete order. Reason: order ID not found!");
        }
        
        Trx trx = Trx.get(TrxPrefix.getPrefix(),true);       
        
        try
        {
            trx.start();
            OrderManager.deleteDraftedOrder(ctx, orderId.intValue(), trx.getTrxName());
            trx.commit();        	
        }
        catch(OperationException ex)
        {
            trx.rollback();
            MOrder posOrder = new MOrder(ctx,orderId.intValue(),null);
            ArrayList list = POSManager.populateOrderLines(ctx,posOrder);
            request.setAttribute(Constants.POS_ORDER_LINES,list);
            postGlobalError("error.order.delete", request);
            return mapping.getInputForward();
        }
        finally
        {
            trx.close();
        }
        
        //remove previously set values
        request.getSession().removeAttribute(Constants.ALL_CUSTOMERS);
        request.getSession().removeAttribute(Constants.SHOPPING_ORDER_CART);        
        request.getSession().removeAttribute(Constants.SHOPPING_ORDER_CART_ITEMS);
        request.getSession().removeAttribute(Constants.CURRENT_POS_ORDER_ID);
        request.getSession().removeAttribute(Constants.CREDIT_ORDER_SHOPPING_CART);
        request.getSession().removeAttribute(Constants.CREDIT_ORDER_SHOPPING_CART_ITEMS);
        request.getSession().removeAttribute(Constants.CREDITORDER_BPARTNER);
        
        return mapping.findForward(DELETE_POS_ORDER);        
    }
    
    public ActionForward getShoppingCart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws OperationException
    {
        DefaultForm df = (DefaultForm) form;
        String orderType = df.getOrderType();
        
        if(orderType == null)
        {
            throw new OperationException("Cannot lookup for shopping cart! Cause orderType is null.");
        }
        
        HashMap<String,String> map = new HashMap<String,String>();
        
        map.put(Constants.POS_ORDER, 												"/jsp/pos/posShoppingCartFirstPage.jsp");
        map.put(Constants.QUICK_POS_ORDER, 									"/jsp/pos/posShoppingCartFirstPage.jsp");
        map.put(Constants.POS_ORDER_CUSTOMER_COMPULSORY, 	"/jsp/pos/posShoppingCartFirstPage.jsp");
        map.put(Constants.CREDIT_ORDER, 											"/jsp/pos/creditOrderShoppingCart.jsp");
        map.put(Constants.POS_ORDER_WITHOUT_ADVANCED,			"/jsp/pos/posShoppingCartFirstPage.jsp");
        
        
        
        String url = map.get(orderType);
        if(url == null)
        {
            throw new OperationException("Cannot lookup for shopping cart! Cause no shopping cart is define for orderType:" + orderType);
        }
        
        return new ActionForward(url);
    }
    
    
    public ActionForward validateAdvanceOrderPIN(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws IOException, OperationException
    {
        Properties ctx=TmkJSPEnv.getCtx(request);      
        
        DefaultForm df = (DefaultForm) form;    	
        String userPIN = df.getUserPIN();
        
        LoginBean bean = null;
        LoginManager loginManager = new LoginManager();
        String script = null;
        String errorMsg = ElementManager.getMsg(ctx,"not.authorised.to.give.discount").getName();
        
        ShoppingCartBean cartBean = (ShoppingCartBean) request.getSession().getAttribute(Constants.SHOPPING_ORDER_CART);
        
        if (cartBean == null || cartBean.getItems().size() == 0)
        {
        	script = "PINError('No items in cart')";
        }
        else
        {
	        try 
	        {
	            bean = loginManager.getUserNameFromPIN(ctx, userPIN);
	            WebUser wu = loginManager.checkLoginPassword(ctx, bean);
	            
	            int roleId = Env.getAD_Role_ID(ctx);
	            
	            BigDecimal discountAllowed = RoleManager.getDiscountAllowed(ctx, roleId, null);		
	            
	            if(discountAllowed != Env.ZERO)
	            {
	                script = "setAction(document.forms[0],'GetPOSPaymentDetailsAction.do','getPOSPaymentDetails');" +
	                "disableButtons();";	
	                
	                request.getSession().setAttribute(Constants.DISCOUNT_ALLOWED, discountAllowed);
	            }
	            else
	            {
	                script = "PINError('" + errorMsg + "');" ;
	            }
	        } 
	        catch (DuplicatePINException e) 
	        {
	            script = "PINError('" + errorMsg + "');" ;   		
	        }
	        catch (InvalidPINException e) 
	        {
	            script = "PINError('" + errorMsg + "');" ;    		
	        } 
	        catch (UserNotFoundException e) 
	        {
	        	script = "PINError('" + errorMsg + "');" ;  
	        } 
	        catch (UserInactiveException e) 
	        {
	        	 script = "PINError('" + errorMsg + "');" ;  
	        } 
	        catch (NotLoggedInException e) 
	        {
	        	 script = "PINError('" + errorMsg + "');" ;  
	        } 
	        catch (SQLException e) 
	        {
	            throw new OperationException(e);
	        } 
	        catch (OperationException e) 
	        {
	            throw new OperationException(e);
	        }
        }
        PrintWriter writer = response.getWriter();        
        writer.print( script );
        writer.flush();
        writer.close();
    
        return null;
    }
    
    public ActionForward validateAdvanceOrderPIN2(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws IOException, OperationException
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
                script = "setAction(document.forms[0],'GetPOSPaymentDetailsAction2.do','getPOSPaymentDetails');" +
                "disableButtons();";	
                
                request.getSession().setAttribute(Constants.DISCOUNT_ALLOWED, discountAllowed);
            }
            else
            {
                script = "PINError('It seems that you are not authorised to give discounts.');" ;
            }
        } 
        catch (DuplicatePINException e) 
        {
            script = "PINError('It seems that you are not authorised to give discounts.');" ;   		
        }
        catch (InvalidPINException e) 
        {
            script = "PINError('It seems that you are not authorised to give discounts.');" ;    		
        } 
        catch (UserNotFoundException e) 
        {
        	script = "PINError('It seems that you are not authorised to give discounts.');" ; 
        } 
        catch (UserInactiveException e) 
        {
        	script = "PINError('It seems that you are not authorised to give discounts.');" ; 
        } 
        catch (NotLoggedInException e) 
        {
        	script = "PINError('It seems that you are not authorised to give discounts.');" ; 
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
    
    public ActionForward getPrintOrderData(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws IOException, OperationException, ApplicationException
    {
        Properties ctx=TmkJSPEnv.getCtx(request);    	
        DefaultForm df = (DefaultForm) form; 
        OrderLineBean bean = (OrderLineBean) df.getBean();
        
        Integer C_Order_ID = bean.getOrderId();
        
        if(C_Order_ID == null)
            return null;
        
        String printData = PrintManager.getPrintData(ctx, C_Order_ID, null);
               
        byte [] openCashDrawer = {10,27,112,48,55,1};
                
        response.setContentType("application/octet-stream");
        OutputStream os = response.getOutputStream();
        
        if(bean.getOpenDrawer())
        {
        	os.write(openCashDrawer);
        }
        
        os.write(printData.getBytes());
        os.flush();
        os.close();
        
        
        return null;
    }
    
    public static final String GET_ORDER_SHOPPING_CART="getOrderShoppingCart";
    public ActionForward getOrderShoppingCart(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws IOException, OperationException, ApplicationException
    {

        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx=TmkJSPEnv.getCtx(request);       
        
        DefaultForm df = (DefaultForm) form; 
        OrderLineBean bean = (OrderLineBean) df.getBean();
        ActionForward forward=null;
        
        Integer orderId = bean.getOrderId();
        MOrder order = new MOrder(ctx,orderId,null);
        
        ShoppingCartBean cart = (ShoppingCartBean)request.getSession().getAttribute(Constants.SHOPPING_ORDER_CART);
        if(cart == null)
        {
        	cart = POSManager.getShoppingCartForOrder(ctx, order.get_ID(), null);
        }
        
        
        int c_BPartner_Id = order.getC_BPartner_ID();
        request.getSession().setAttribute(Constants.BPARTNER, Integer.valueOf(c_BPartner_Id));
        
        if((Constants.POS_ORDER_WITHOUT_ADVANCED).equalsIgnoreCase(df.getOrderType())) 
        {    
        	cart = POSManager.getShoppingCartForOrder(ctx, order.get_ID(), null);
        	request.getSession().setAttribute(Constants.BPARTNER, Integer.valueOf(c_BPartner_Id));  
            request.getSession().setAttribute( Constants.SHOPPING_ORDER_CART , cart );
            request.getSession().setAttribute( Constants.SHOPPING_ORDER_CART_ITEMS  , cart.getItems() );
            request.getSession().setAttribute( Constants.PRESENT_POS_ORDER , order );
            request.getSession().setAttribute( Constants.PRESENT_POS_ORDER_ID , Integer.valueOf(order.get_ID()) );
          
            forward =  new ActionForward("/CreatePOSOrderWithoutAdvanced.do");
            
            df.populate(new OrderLineBean());
            return forward;
        }
        
        if((Constants.POS_ORDER_CUSTOMER_COMPULSORY).equalsIgnoreCase(df.getOrderType())) 
        {   
        	cart = POSManager.getShoppingCartForOrder(ctx, order.get_ID(), null);
        	request.getSession().setAttribute(Constants.BPARTNER, Integer.valueOf(c_BPartner_Id));  
            request.getSession().setAttribute( Constants.SHOPPING_ORDER_CART , cart );
            request.getSession().setAttribute( Constants.SHOPPING_ORDER_CART_ITEMS  , POSManager.getItemBean(cart.getItems()) );
            request.getSession().setAttribute( Constants.PRESENT_POS_ORDER , order );
            request.getSession().setAttribute( Constants.PRESENT_POS_ORDER_ID , Integer.valueOf(order.get_ID()) );
          
            forward =  new ActionForward("/CreatePOSOrder2.do");
            
            df.populate(new OrderLineBean());
            return forward;
        }
        
        if(order.getOrderType().equals(UDIOrderTypes.POS_ORDER.getOrderType())) 
        {   
        	cart = POSManager.getShoppingCartForOrder(ctx, order.get_ID(), null);
        	request.getSession().setAttribute(Constants.BPARTNER, Integer.valueOf(c_BPartner_Id));  
            request.getSession().setAttribute( Constants.SHOPPING_ORDER_CART , cart );
            request.getSession().setAttribute( Constants.SHOPPING_ORDER_CART_ITEMS  , POSManager.getItemBean(cart.getItems()));
            request.getSession().setAttribute( Constants.PRESENT_POS_ORDER , order );
            request.getSession().setAttribute( Constants.PRESENT_POS_ORDER_ID , Integer.valueOf(order.get_ID()) );
            df.populate(new OrderLineBean());
            forward =  new ActionForward("/CreatePOSOrder.do");
        }        
        else if(order.getOrderType().equals(UDIOrderTypes.POS_GOODS_RECEIVE_NOTE.getOrderType())) 
        {
        	cart = (ShoppingCartBean)request.getSession().getAttribute(Constants.GOODS_RECEIVE_NOTE_SHOPPING_CART);
            request.getSession().setAttribute(Constants.BPARTNER, Integer.valueOf(c_BPartner_Id));              
            request.getSession().setAttribute( Constants.GOODS_RECEIVE_NOTE_SHOPPING_CART , cart );
            
            if (cart != null)
            {
            	request.getSession().setAttribute( Constants.GOODS_RECEIVE_NOTE_SHOPPING_CART_ITEMS  , cart.getItems() );
                request.getSession().setAttribute( Constants.PRESENT_GRN_ORDER , order );
                request.getSession().setAttribute( Constants.PRESENT_GRN_ORDER_ID , Integer.valueOf(order.get_ID()) );
            }            
            
            forward =  new ActionForward("/GetAllPOSVendor.do?action=getAllVendors&isSales=true");
        }
        else  if(order.getOrderType().equals(UDIOrderTypes.POS_GOODS_RETURN_NOTE.getOrderType())) 
        {
        	cart = (ShoppingCartBean)request.getSession().getAttribute(Constants.GOODS_RETURN_NOTE_SHOPPING_CART);
        	request.getSession().setAttribute(Constants.BPARTNER, Integer.valueOf(c_BPartner_Id));  
            request.getSession().setAttribute( Constants.GOODS_RETURN_NOTE_SHOPPING_CART, cart );
            request.getSession().setAttribute( Constants.GOODS_RETURN_NOTE_SHOPPING_CART_ITEMS,cart.getItems() );
            request.getSession().setAttribute( Constants.PRESENT_GOODS_RET_ORDER , order );
            request.getSession().setAttribute( Constants.PRESENT_GOODS_RET_ORDER_ID , Integer.valueOf(order.get_ID()) );
            
            forward =  new ActionForward("/GetAllPOSVendor.do?action=getAllVendors&isSales=false");
        }   
        else  if(order.getOrderType().equals(UDIOrderTypes.CUSTOMER_RETURN_ORDER.getOrderType())) 
        {
        	cart = (ShoppingCartBean)request.getSession().getAttribute(Constants.CUSTOMER_RETURN_ORDER_SHOPPING_CART);
        	request.getSession().setAttribute(Constants.BPARTNER, Integer.valueOf(c_BPartner_Id));  
            request.getSession().setAttribute( Constants.CUSTOMER_RETURN_ORDER_SHOPPING_CART, cart );
            request.getSession().setAttribute( Constants.CUSTOMER_RETURN_ORDER_SHOPPING_CART_ITEMS, POSManager.getItemBean(cart.getItems()) );
            request.getSession().setAttribute( Constants.PRESENT_CUSTOMER_RET_ORDER , order );
            request.getSession().setAttribute( Constants.PRESENT_CUSTOMER_RET_ORDER_ID , Integer.valueOf(order.get_ID()) );
            
            forward =  new ActionForward("/CustomerReturnOrder.do");
            
        }   
        else if(order.getOrderType().equals(UDIOrderTypes.CREDIT_ORDER.getOrderType()))
        {
        	cart = (ShoppingCartBean)request.getSession().getAttribute(Constants.CREDIT_ORDER_SHOPPING_CART);
        	request.getSession().setAttribute(Constants.CREDITORDER_BPARTNER, Integer.valueOf(c_BPartner_Id));  
            request.getSession().setAttribute( Constants.CREDIT_ORDER_SHOPPING_CART , cart );
            request.getSession().setAttribute( Constants.CREDIT_ORDER_SHOPPING_CART_ITEMS  ,  POSManager.getItemBean(cart.getItems()) );
            request.getSession().setAttribute( Constants.PRESENT_CREDIT_ORDER , order );
            request.getSession().setAttribute( Constants.PRESENT_CREDIT_ORDER_ID , Integer.valueOf(order.get_ID()) );
            forward =  new ActionForward("/InitCreateCreditOrderAction.do?action=initCreateCreditOrder");
            
        }
        
        else  if(order.getOrderType().equals(UDIOrderTypes.CREDIT_MEMO.getOrderType())) 
        {
        	cart = (ShoppingCartBean)request.getSession().getAttribute(Constants.CUSTOMER_RETURN_ORDER_SHOPPING_CART);
            OrderBean  orderBean = POSManager.getPOSOrderDetail(ctx,order.getDocumentNo()); 
            int bpartnerId = orderBean.getBpartnerId().intValue();
            MBPartner partner = BPartnerManager.loadBPartner(ctx, bpartnerId,null);
            String partnerName = partner.getName() + " " + partner.getName2();
            partnerName = partnerName.trim();
            
            df.setPartnerName(partnerName);
            df.setBpartnerId(orderBean.getBpartnerId().toString());
            
            request.getSession().setAttribute(Constants.B_PARTNER_ID,orderBean.getBpartnerId());
            request.getSession().setAttribute(Constants.ORDER_TYPE,orderBean.getOrderType());
            request.getSession().setAttribute(Constants.POS_ORDER_DOC_NUMBER,bean.getDocumentNo());
            request.getSession().setAttribute(Constants.CREDIT_PAYMENT_TERM_ID,orderBean.getPaymentTermId());
            if (cart != null)
            {
            	request.getSession().setAttribute(Constants.CUSTOMER_RETURN_ORDER_SHOPPING_CART,cart);
            	request.getSession().setAttribute(Constants.CUSTOMER_RETURN_ORDER_SHOPPING_CART_ITEMS,cart.getItems());
            }
            forward =  new ActionForward("/CreditMemoAction.do");
            
        }   
        else
        {
            String msg = "Unknown orderType : " + order.getOrderType();
            throw new OperationException(msg);
        }
        df.populate(new OrderLineBean());
        return forward;
    }
    
    public static final String GET_CUSTOMER_RETURN_ORDER_SHOPPING_CART = "getCustomerReturnOrderShoppingCart";
    public ActionForward getCustomerReturnOrderShoppingCart(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws IOException, OperationException, ApplicationException
    {
    	getOrderShoppingCart(mapping,form,request,response);
    	
    	Properties ctx=TmkJSPEnv.getCtx(request); 
    	DefaultForm df = (DefaultForm) form;    	
    	Integer bpartnerId = (Integer) request.getSession().getAttribute(Constants.B_PARTNER_ID);
    	
    	MBPartner partner = BPartnerManager.loadBPartner(ctx, bpartnerId.intValue(),null);
        String partnerName = partner.getName() + " " + partner.getName2();
        partnerName = partnerName.trim();
        
        df.setPartnerName(partnerName);
        df.setBpartnerId(bpartnerId.toString());
    	
    	return mapping.findForward(GET_CUSTOMER_RETURN_ORDER_SHOPPING_CART);
    }
    
    public static final String GET_CUSTOMER_RETURN_ORDER_SHOPPING_CART2 = "getCustomerReturnOrderShoppingCart2";
    public ActionForward getCustomerReturnOrderShoppingCart2(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws IOException, OperationException, ApplicationException
    {
    	getOrderShoppingCart(mapping,form,request,response);
    	
    	/*
    	Properties ctx=TmkJSPEnv.getCtx(request); 
    	DefaultForm df = (DefaultForm) form;    	
    	Integer bpartnerId = (Integer) request.getSession().getAttribute(Constants.B_PARTNER_ID);
    	
    	MBPartner partner = BPartnerManager.loadBPartner(ctx, bpartnerId.intValue(),null);
        String partnerName = partner.getName() + " " + partner.getName2();
        partnerName = partnerName.trim();
        
        df.setPartnerName(partnerName);
        df.setBpartnerId(bpartnerId.toString());
        */
    	
    	return mapping.findForward(GET_CUSTOMER_RETURN_ORDER_SHOPPING_CART2);
    }
    
    public static final String GET_CREDIT_MEMO_SHOPPING_CART = "getCreditMemoShoppingCart";
    public ActionForward getCreditMemoShoppingCart(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws IOException, OperationException, ApplicationException
    {
    	getCustomerReturnOrderShoppingCart(mapping,form,request,response);
    	return mapping.findForward(GET_CREDIT_MEMO_SHOPPING_CART);
    }
    
    public static final String REMOVE_SESSION_FOR_NEW_OREDR="removeSessionForNewOrder";
    public ActionForward removeSessionForNewOrder(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws IOException, OperationException, ApplicationException
    {
    	 ActionForward fwd= init(mapping,form,request,response);
         if(fwd!=null)
             return fwd;
    	
    	
    	Properties ctx=TmkJSPEnv.getCtx(request); 
    	RemoveSessionForm df = (RemoveSessionForm) form;    
    	RemoveSessionBean bean = (RemoveSessionBean) df.getBean();
    	
    	SessionStorage.removeSession(ctx,request);
    	String forwd =POSManager.getForward(ctx,bean.getOrderType()); 
    	
    	
    	return new ActionForward(forwd);
    }
}
