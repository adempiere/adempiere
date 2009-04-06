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
import java.math.RoundingMode;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.util.logging.Level;

import javax.management.relation.RoleNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.MessageResources;
import org.compiere.model.MBPartner;
import org.compiere.model.MCurrency;
import org.compiere.model.MOrder;
import org.compiere.model.MPayment;
import org.compiere.model.MRole;
import org.compiere.print.ReportEngine;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.compiere.util.WebUser;
import org.posterita.Constants;
import org.posterita.beans.CheckoutBean;
import org.posterita.beans.CurrentTillAmountBean;
import org.posterita.beans.ItemBean;
import org.posterita.beans.LoginBean;
import org.posterita.beans.OrderBean;
import org.posterita.beans.OrderLineBean;
import org.posterita.beans.POSInfoBean;
import org.posterita.beans.RemoveSessionBean;
import org.posterita.beans.ReportBean;
import org.posterita.beans.ShoppingCartBean;
import org.posterita.beans.WebDocumentBean;
import org.posterita.businesslogic.LoginManager;
import org.posterita.businesslogic.OrderManager;
import org.posterita.businesslogic.OrderReferenceManager;
import org.posterita.businesslogic.POSManager;
import org.posterita.businesslogic.POSTerminalManager;
import org.posterita.businesslogic.PrintManager;
import org.posterita.businesslogic.ShoppingcartManager;
import org.posterita.businesslogic.administration.BPartnerManager;
import org.posterita.businesslogic.administration.PriceListManager;
import org.posterita.businesslogic.administration.RoleManager;
import org.posterita.businesslogic.administration.UserManager;
import org.posterita.businesslogic.performanceanalysis.ReportDateManager;
import org.posterita.businesslogic.stock.StockManager;
import org.posterita.core.Configuration;
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
import org.posterita.exceptions.LimitPriceViolatedException;
import org.posterita.exceptions.NoOrderLineSelectedException;
import org.posterita.exceptions.NotLoggedInException;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.ProductNotFoundException;
import org.posterita.exceptions.ProductNotOnPriceListException;
import org.posterita.exceptions.QuantityNotAvailableException;
import org.posterita.exceptions.UOMValuePrecisionNotValidException;
import org.posterita.exceptions.UserInactiveException;
import org.posterita.exceptions.UserNotFoundException;
import org.posterita.form.CheckoutForm;
import org.posterita.form.OrderLineForm;
import org.posterita.form.RemoveSessionForm;
import org.posterita.lib.UdiConstants;
import org.posterita.order.UDIOrderTypes;
import org.posterita.struts.core.DefaultForm;
import org.posterita.util.TmkPrinterConstants;

public class POSOrderAction extends POSDispatchAction
{
    /** Logger */
    private static final CLogger logger = CLogger.getCLogger(POSOrderAction.class);
    
    public static final String CREATE_POS_ORDER="createPOSOrder";
    public ActionForward createPOSOrder(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx=TmkJSPEnv.getCtx(request); 
        DefaultForm df= (DefaultForm) form;
        OrderLineBean bean=(OrderLineBean) df.getBean();
        
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
            
            bPartnerId=Integer.valueOf(POSTerminalManager.getCashBPartner(ctx).get_ID());
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
        catch(LimitPriceViolatedException e3)
        {
            trx.rollback();
            postGlobalError("error.limit.price.violated",e3.getMessage(),request);
            return mapping.getInputForward();
        }
        
        finally
        {
            trx.close();
        }
        
        // Reset transaction as it has already been committed and closed
        posOrder.set_TrxName(null);
        
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
            completedPOSOrder = POSManager.completePOSOrder(ctx,orderId.intValue(),bean,null);            
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
            bPartnerId=Integer.valueOf(POSTerminalManager.getCashBPartner(ctx).get_ID());
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
        
        Timestamp fromdate = ReportDateManager.getFromDate(bean);
        Timestamp todate = ReportDateManager.getToDate(bean);
        
        ArrayList<POSInfoBean> list = POSManager.getPOSInfo(ctx, fromdate, todate, null);
        request.getSession().setAttribute(Constants.POS_INFO,list);
        //df.populate(new OrderLineBean());
        
        return mapping.findForward(VIEW_POS_INFO);
    } 
    
    
  
    public static final String INCREMENT_QTY = "incrementQty";
    public ActionForward incrementQty(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        // Bug fix for null pointer
        OrderLineForm of = (OrderLineForm) form;
        if(of.getQuantity() == null)
        {
            of.setQuantity("1");
        }
        
        addToShoppingCart(mapping,form,request,response);
        return getShoppingCart(mapping,form,request,response);
    }
    
    public static final String DECREMENT_QTY = "decrementQty";
    public ActionForward decrementQty(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        // Bug fix for null pointer
        OrderLineForm of = (OrderLineForm) form;
        if(of.getQuantity() == null)
        {
            of.setQuantity("1");
        }
        
        addToShoppingCart(mapping,form,request,response);
        return getShoppingCart(mapping,form,request,response);
    }
    
    public ActionForward addProduct(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException, IOException, ParseException
    {
        // Bug fix for null pointer
        OrderLineForm of = (OrderLineForm) form;
        if(of.getQuantity() == null)
        {
            of.setQuantity("1");
        }
        
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
        
        if (orderType==null) 
        {   orderType=(String)request.getSession().getAttribute(Constants.ORDER_TYPE);
            bean.setOrderType(orderType);
            df.populate(bean);
        }
        
        String shoppingCart = Constants.SHOPPING_ORDER_CART;
        String shoppingCartItem = Constants.SHOPPING_ORDER_CART_ITEMS;
        String path=null;
        
        if (orderType.equalsIgnoreCase(Constants.POS_ORDER))
        {
            path="/jsp/pos/createPOSOrder.jsp";
        }
        if (orderType.equalsIgnoreCase(Constants.POS_ORDER_WITHOUT_ADVANCED))
        {
            path="/jsp/pos/createPOSOrderWithoutAdvance.jsp";
        }
        if (orderType.equalsIgnoreCase(Constants.QUICK_POS_ORDER))
        {
            path="/jsp/pos/createPOSOrder3.jsp";
        }
        if (orderType.equalsIgnoreCase(Constants.POS_ORDER_CUSTOMER_COMPULSORY))
        {
            path="/jsp/pos/createPOSOrder2.jsp";
        }
        else if(orderType.equalsIgnoreCase(Constants.CREDIT_ORDER))
        {
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
            
            BigDecimal qty = bean.getQuantity();
            if(qty != null && qty.doubleValue() < 0.0)
            {
                throw new InputQuantityLessThanZeroException("");
            }
                                
            cartBean = StockManager.addToPOSCart(ctx, bean, cartBean,true,Boolean.parseBoolean(bean.getIfAdd()));
                        
            try
            {
               // cartBean = ComputeTotalLinePriceForDiscount(request, cartBean);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        
        }
        catch (InputQuantityLessThanZeroException e)        
        {
            postGlobalError("error.invalid.inputQty", request);
            return new ActionForward(path);
        }
        catch (ProductNotFoundException e)
        {
            postGlobalError("error.product.not.found", e.getMessage(), request);
            return new ActionForward(path);
        }
        catch(QuantityNotAvailableException e)
        {
            postGlobalError("error.quantity.notAvailable", e.getMessage(), request);
            return new ActionForward(path);
        }
        
        catch(ProductNotOnPriceListException e)
        {
            postGlobalError("error.product.price.not.found", e.getMessage(), request);
            return new ActionForward(path);
        }
        
        catch (UOMValuePrecisionNotValidException e)
        {
            postGlobalError("error.precision", e.getMessage(), request);
            return new ActionForward(path);
        }
        
        BigDecimal cartTotal = StockManager.setGrandTotal(cartBean.getItems());
        
        String currSymboleSales = POSTerminalManager.getDefaultSalesCurrency(ctx).getCurSymbol();
        request.setAttribute(Constants.CURRENCY_SYMBOLE,currSymboleSales);
        request.getSession().setAttribute(Constants.ORDER_TYPE,orderType);
        
        
        request.getSession().setAttribute(shoppingCart, cartBean);
        request.getSession().setAttribute(shoppingCartItem, cartBean.getItems());
        request.getSession().setAttribute(Constants.SHOPPING_CART_TOTAL, cartTotal);
        request.getSession().setAttribute(Constants.B_PARTNER_ID,bean.getBpartnerId()); 
        
        df.setQtyAndItem("");
        
        return new ActionForward(path);
        //return mapping.findForward(ADD_TO_SHOPPING_CART); 
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
        MCurrency currency = POSTerminalManager.getCurrencyOfTerminalCashBook(ctx);
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
    
    public ActionForward getShoppingCart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws OperationException, IOException, ParseException
    {
        DefaultForm df = (DefaultForm) form;
        String orderType = df.getOrderType();
        
        String shoppingcartHTML = ShoppingcartManager.getShoppingcartAsHTML(request, orderType);        
        
        PrintWriter writer = response.getWriter();
        writer.write(shoppingcartHTML);
        
        
        ActionMessages errors = (ActionMessages) request.getAttribute("org.apache.struts.action.ERROR");
        if(errors != null && !errors.isEmpty())
        {
            MessageResources messageResources = getResources(request);
            
            Iterator<ActionMessage> iter = errors.get();
            while(iter.hasNext())
            {
                ActionMessage message = iter.next(); 
                String key = message.getKey();
                Object[] values = message.getValues();
                
                String errMsg = messageResources.getMessage(key, values);
                writer.write("<script>showErrorMessage('" + errMsg + "',searchElement)</script>");
            }
            
        }       
        
        writer.close();
        
        return null;
    }
    
    public ActionForward getBarcodeCart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws OperationException, IOException, ParseException
    {
        DefaultForm df = (DefaultForm) form;
        String orderType = df.getOrderType();
        
        String shoppingcartHTML = ShoppingcartManager.getBarcodecartAsHTML(request);        
        
        PrintWriter writer = response.getWriter();
        writer.write(shoppingcartHTML);
        
        
        ActionMessages errors = (ActionMessages) request.getAttribute("org.apache.struts.action.ERROR");
        if(errors != null && !errors.isEmpty())
        {
            MessageResources messageResources = getResources(request);
            
            Iterator<ActionMessage> iter = errors.get();
            while(iter.hasNext())
            {
                ActionMessage message = iter.next(); 
                String key = message.getKey();
                Object[] values = message.getValues();
                
                String errMsg = messageResources.getMessage(key, values);
                writer.write("<script>showErrorMessage('" + errMsg + "')</script>");
            }
            
        }       
        
        writer.close();
        
        return null;
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
    
    public ActionForward skipValidation(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws IOException, OperationException
    {
        Properties ctx=TmkJSPEnv.getCtx(request);      
        
        DefaultForm df = (DefaultForm) form;        
        
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
        Properties ctx = TmkJSPEnv.getCtx(request);     
        DefaultForm df = (DefaultForm) form; 
        OrderLineBean bean = (OrderLineBean) df.getBean();
        
        Integer C_Order_ID = bean.getOrderId();
        
        if(C_Order_ID == null)
            return null;
        
        String printingType = POSManager.getPrintingTypeFromCookie(request);
        boolean cashDrawerPresent = POSManager.getCashDrawerStatusFromCookie(request);
        
        if (printingType == null)
        {
            printingType = TmkPrinterConstants.SLIP_PRINTER_9PIN;
        }
        
        byte printData[] = null;
        byte [] openCashDrawer = {10,27,112,48,55,1};
        
        String args = "showFooter=true;lineLength=40;priceWithVat=true;showDiscount=true;showLogo=false;showBarcode=false;";
        
        if (TmkPrinterConstants.SLIP_PRINTER_9PIN.equalsIgnoreCase(printingType))
        {
            String data = PrintManager.getPrintData(ctx, C_Order_ID, args, null);
            response.setContentType("application/octet-stream");
            printData = data.getBytes();
        }
        else if (TmkPrinterConstants.SLIP_PRINTER_THERMAL.equalsIgnoreCase(printingType))
        {
            args = "showFooter=true;lineLength=64;priceWithVat=true;showDiscount=true;showLogo=false;showBarcode=false;";
            String data = PrintManager.getPrintData(ctx, C_Order_ID, args, null);
            response.setContentType("application/octet-stream");
            printData = data.getBytes();
        }
        else if (TmkPrinterConstants.NORMAL_PRINTER.equalsIgnoreCase(printingType))
        {
            printData = PrintManager.getPDFData(ctx, ReportEngine.ORDER, C_Order_ID);
            response.setContentType("application/pdf");
        }
        else
        {
            logger.log(Level.SEVERE, "Printing Type not defined, PrintingType: " + printingType);
            return null;
        }
        
        OutputStream os = response.getOutputStream();
        
        if(bean.getOpenDrawer() && cashDrawerPresent)
        {
            os.write(openCashDrawer);
        }
        
        os.write(printData);
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
    
    //----------------------------------------------------------------------------------------------
        
    public static final String UPDATE_QTY = "updateQty";
    public ActionForward updateQty(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        Properties ctx=TmkJSPEnv.getCtx(request);
                
        // Bug fix for null pointer
        OrderLineForm of = (OrderLineForm) form;
        if(of.getQuantity() == null)
        {
            of.setQuantity("1");
        }
        
        OrderLineBean bean = (OrderLineBean) of.getBean();
        Integer product_id = bean.getProductId();
        BigDecimal quantity = bean.getQuantity();
        Integer priceListId = bean.getPriceListId();
        
        ArrayList<ItemBean> items = (ArrayList<ItemBean>) request.getSession().getAttribute(Constants.SHOPPING_ORDER_CART_ITEMS);
        StockManager.updateItemFromPOSList(ctx, priceListId, items, product_id, quantity);
        ShoppingCartBean cartBean = (ShoppingCartBean) request.getSession().getAttribute(Constants.SHOPPING_ORDER_CART);
        cartBean.setItems(items);
        BigDecimal cartTotal = StockManager.setGrandTotal(items);
        request.getSession().setAttribute(Constants.SHOPPING_CART_TOTAL, cartTotal);
        request.getSession().setAttribute(Constants.SHOPPING_ORDER_CART_ITEMS, items);
        
        if(quantity.doubleValue() == 0.0d)
        {
            request.getSession().setAttribute(Constants.SHOPPING_CART_DISCOUNTED_TOTAL, Env.ZERO);
        }
        return getShoppingCart(mapping,form,request,response);
    }
    
    public static final String RELOAD_CART = "reloadCart";
    public ActionForward reloadCart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {       
        return getShoppingCart(mapping,form,request,response);
    }
    
    public static final String UPDATE_PRICELIST = "updatePriceList";
    public ActionForward updatePriceList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        Properties ctx=TmkJSPEnv.getCtx(request);
        
        OrderLineForm of = (OrderLineForm) form;
        OrderLineBean bean = (OrderLineBean) of.getBean();
        
        Integer priceListId = bean.getPriceListId();
        int oldPriceListId = ShoppingcartManager.getPriceList(request);
        
        if(oldPriceListId != priceListId)
        {
            try 
            {           
                ArrayList<ItemBean> items = (ArrayList<ItemBean>) request.getSession().getAttribute(Constants.SHOPPING_ORDER_CART_ITEMS);
                StockManager.setPOSItemPrices(ctx, priceListId, items, true);
                request.getSession().setAttribute(Constants.SHOPPING_ORDER_CART_ITEMS, items);
                request.getSession().setAttribute(Constants.ORDER_PRICE_LIST, priceListId);
            } 
            catch(ProductNotOnPriceListException e)
            {
                postGlobalError("error.product.price.not.found", e.getMessage(), request);            
            }
            catch(OperationException e)
            {
                postGlobalError("error.process", e.getMessage(), request);            
            }
        }       
        
        return getShoppingCart(mapping,form,request,response);
    }
    
    public static final String RESET_USER = "resetUser";
    public ActionForward resetUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        Properties ctx=TmkJSPEnv.getCtx(request);
        OrderLineForm of = (OrderLineForm) form;
        OrderLineBean bean = (OrderLineBean) of.getBean();
        
        StringBuffer sb = new StringBuffer();
               
        try 
        {
            MRole role = new MRole(ctx, bean.getRoleId(), null);
            float discountAllowed = role.getUserDiscount().floatValue();
            boolean overrideLimit = role.isOverwritePriceLimit();
            boolean discountUptoPriceLimit = role.isDiscountUptoLimitPrice();
            boolean discountOnTotal = role.isDiscountAllowedOnTotal();
            
            CheckoutForm cf = (CheckoutForm) request.getSession().getAttribute("CheckoutForm");
            if(cf == null)
            {
                cf = new CheckoutForm();
            }
            
            cf.setDiscountLimit(discountAllowed + "");
            cf.setIsOverwritePriceLimit(overrideLimit + "");
            cf.setIsDiscountOnTotal(discountOnTotal + "");
            cf.setIsDiscountUptoLimitPrice(discountUptoPriceLimit + "");
            
            request.getSession().setAttribute("CheckoutForm", cf);
            
            sb.append("{ roleId:").append(role.getAD_Role_ID()).append(",");
            sb.append("discountLimit:").append(discountAllowed).append(",");
            sb.append("overrideLimit:").append(overrideLimit).append(",");
            sb.append("discountOnTotal:").append(discountOnTotal).append(",");
            sb.append("discountUptoPriceLimit:").append(discountUptoPriceLimit).append("}");
            
        } 
        catch (Exception e) 
        {
            sb.append("{error:'" + e.getMessage() + "'}");
        }
        
        PrintWriter writer = response.getWriter();
        writer.write(sb.toString());
        writer.close();
        
        return null;
    }
    
    
    public static final String VALIDATE_PIN = "validatePIN";
    public ActionForward validatePIN(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {       
        String pin = request.getParameter("pin");       
        Properties ctx=TmkJSPEnv.getCtx(request);    
        
        StringBuffer sb = new StringBuffer();
               
        try 
        {
            int ad_role_id = UserManager.getRoleId(ctx, pin, null);
        	float discountAllowed = UserManager.getDiscountAllowed(ctx, pin, null).floatValue();
            boolean overrideLimit = UserManager.isOverridePriceLimitAllowed(ctx, pin, null);
            boolean discountUptoPriceLimit = UserManager.isDiscountUptoPriceLimit(ctx, pin, null);
            boolean discountOnTotal = UserManager.isDiscountAllowedOnTotal(ctx, pin, null);
            
            CheckoutForm cf = (CheckoutForm) request.getSession().getAttribute("CheckoutForm");
            if(cf == null)
            {
                cf = new CheckoutForm();
            }
            
            cf.setDiscountLimit(discountAllowed + "");
            cf.setIsOverwritePriceLimit(overrideLimit + "");
            cf.setIsDiscountOnTotal(discountOnTotal + "");
            cf.setIsDiscountUptoLimitPrice(discountUptoPriceLimit + "");
            
            request.getSession().setAttribute("CheckoutForm", cf);
            
            sb.append("{ roleId:").append(ad_role_id).append(",");
            sb.append("discountLimit:").append(discountAllowed).append(",");
            sb.append("overrideLimit:").append(overrideLimit).append(",");
            sb.append("discountOnTotal:").append(discountOnTotal).append(",");
            sb.append("discountUptoPriceLimit:").append(discountUptoPriceLimit).append("}");
            
        } 
        catch (UserNotFoundException e) 
        {
            sb.append("{error:'Invalid PIN!'}");
        }
        catch (RoleNotFoundException e) 
        {
            sb.append("{error:'Invalid PIN!'}");
        } 
        
        PrintWriter writer = response.getWriter();
        writer.write(sb.toString());
        writer.close();
        
        return null;
    }
    
    /**
     * This method is called from the order screens
     * Parameters supplied are:
     * 1. amountTendered
     * 2. cardNo
     * 3. chequeNo
     * 4. cashAmt
     * 5. chequeAmt
     * 6. cardAmount
     * 7. bpartnerId
     * 8. tenderType
     * 9. discounts
     * 10. orderType
     * 11. discountLimit
     */
    public static final String CHECKOUT = "checkout";
    public ActionForward checkout(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    { 
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx=TmkJSPEnv.getCtx(request);
        CheckoutForm cf = (CheckoutForm) form;
        CheckoutBean bean = (CheckoutBean) cf.getBean();
        String input = "";
        
        Trx trx = Trx.get(TrxPrefix.getPrefix(), true);
        int c_order_id = -1;
        
        try
        {
            trx.start();
            //Bug fix for credit sales
            //set order type to credit order if tender type is credit
            /*
            if("Credit".equals(bean.getTenderType()) &&
            		UDIOrderTypes.POS_ORDER.getOrderType().compareTo(bean.getOrderType()) == 0)
            {
            	bean.setOrderType(UDIOrderTypes.CREDIT_ORDER.getOrderType());
            }
            */
            
            if(UDIOrderTypes.POS_GOODS_RECEIVE_NOTE.getOrderType().equalsIgnoreCase(bean.getOrderType()))
            {
                input = "/CreatePOSOrderScreen.do";
                c_order_id = POSManager.createGoodsReceiveNote(ctx, bean, request, trx.getTrxName());               
            }
            
            if(UDIOrderTypes.POS_GOODS_RETURN_NOTE.getOrderType().equalsIgnoreCase(bean.getOrderType()))
            {
                input = "/CreatePOSOrderScreen.do";
                c_order_id = POSManager.createGoodsReturnNote(ctx, bean, request, trx.getTrxName());               
            }
            
            if(UDIOrderTypes.POS_ORDER.getOrderType().equalsIgnoreCase(bean.getOrderType())
            		|| UDIOrderTypes.CREDIT_ORDER.getOrderType().equalsIgnoreCase(bean.getOrderType()))
            {
                input = "/CreatePOSOrderScreen.do";
                //get shopping cart items
                ArrayList<ItemBean> items = (ArrayList<ItemBean>) request.getSession().getAttribute(Constants.SHOPPING_ORDER_CART_ITEMS);
                c_order_id = POSManager.checkoutOrder(ctx, bean, items, trx.getTrxName());
            }
            
            /*
            if(UDIOrderTypes.CREDIT_ORDER.getOrderType().equalsIgnoreCase(bean.getOrderType()))
            {  
                input = "/CreatePOSOrderScreen.do";
              //get shopping cart items
                ArrayList<ItemBean> items = (ArrayList<ItemBean>) request.getSession().getAttribute(Constants.SHOPPING_ORDER_CART_ITEMS);
                c_order_id = POSManager.checkoutOrder(ctx, bean, items, trx.getTrxName());
            }
            */
            
            if(UDIOrderTypes.CUSTOMER_RETURN_ORDER.getOrderType().equalsIgnoreCase(bean.getOrderType()))
            {  
                input = "/CreatePOSOrderScreen.do";
              //get shopping cart items
                ArrayList<ItemBean> items = (ArrayList<ItemBean>) request.getSession().getAttribute(Constants.SHOPPING_ORDER_CART_ITEMS);
                c_order_id = POSManager.createCustomerReturnOrder(ctx, bean, request, trx.getTrxName());
            }
            
            trx.commit();
        }
        catch (Exception e)
        {
            trx.rollback();
            postGlobalError("error.process", e.getMessage(), request);
            e.printStackTrace();
            
            return new ActionForward(input);
        }
        finally
        {
            trx.close();
        }
                
        ShoppingcartManager.clearShoppingcart(request);
        request.getSession().removeAttribute(Constants.BPARTNER_ID);
        request.getSession().removeAttribute(Constants.BPARTNER_NAME);
        request.getSession().removeAttribute(Constants.BPARTNER_CREDIT);
        request.getSession().removeAttribute(Constants.ORDER_PRICE_LIST);
        //return mapping.findForward(CHECKOUT);
        return  new ActionForward("/ViewPOSOrder.do?orderType=" + bean.getOrderType() + "&orderId=" + c_order_id);
    }
    
    public ActionForward clearCart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    { 
        ShoppingcartManager.clearShoppingcart(request);
       
        return getShoppingCart(mapping, form, request, response);       
    }
    
    public static String SET_DISCOUNT = "setDiscount";
    public ActionForward setDiscount(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    { 
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx=TmkJSPEnv.getCtx(request);
        CheckoutForm cf = (CheckoutForm) form;
        CheckoutBean bean = (CheckoutBean) cf.getBean(); 
        
        Trx trx = Trx.get(TrxPrefix.getPrefix(),true);
        try
        {
            trx.start();
            POSManager.setDiscount(ctx, bean, request, trx.getTrxName());
            trx.commit();
        }
        catch (DiscountLimitException e)
        {
        	 postGlobalError("error.process", e.getMessage(), request);
            trx.rollback();
        }
        catch(OperationException e)
        {
        	postGlobalError("error.process", e.getMessage(), request);
            trx.rollback();
        }
        finally
        {
            trx.close();
        }
        return new ActionForward("/CreatePOSOrderScreen.do");
    }
    
    public ActionForward setQuickDiscount(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    { 
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx=TmkJSPEnv.getCtx(request);
        CheckoutForm cf = (CheckoutForm) form;
        CheckoutBean bean = (CheckoutBean) cf.getBean(); 
        StringBuffer sb = new StringBuffer();
        
        Trx trx = Trx.get(TrxPrefix.getPrefix(),true);
        try
        {
            trx.start();
            POSManager.setQuickDiscount(ctx, bean, request, trx.getTrxName());
            sb.append("{status: 'OK'}");
            trx.commit();
        }
        catch (DiscountLimitException e)
        {
            trx.rollback();
            sb.append("{error:'" + e.getMessage() + "'}");
            
        }
        catch(OperationException e)
        {
            trx.rollback();
            sb.append("{error:'" + e.getMessage() + "'}");
        }
        finally
        {
            trx.close();
        }
        
        PrintWriter writer = response.getWriter();
        writer.write(sb.toString());
        writer.close();
        
        return null;
        
    }
    
    
    public static final String LOAD_ORDER_SCREEN = "loadOrderScreen";
    public ActionForward loadOrderScreen(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    { 
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx=TmkJSPEnv.getCtx(request);
        Object isSales = request.getParameter(Constants.IS_SOTRX);
        if (isSales == null)
        {
            return mapping.getInputForward();
        }
        String isSO = String.valueOf(isSales);
        Boolean isSOTrx = Boolean.valueOf(isSO);
        Configuration config = Configuration.getConfiguration(request);
        
        DefaultForm df = (DefaultForm) form;
        String orderType = df.getOrderType();
        
        if(orderType == null) 
        {
	        if (isSOTrx)
	        {
	            orderType = UDIOrderTypes.POS_ORDER.getOrderType();
	        }
	        else
	        {
	            orderType = UDIOrderTypes.POS_GOODS_RECEIVE_NOTE.getOrderType();
	        }
        }
        else
        {
        	if(UDIOrderTypes.POS_GOODS_RETURN_NOTE.getOrderType().equals(orderType))
        	{
        		isSOTrx = false;
        		config.setPaymentRule(Constants.PAYMENT_RULE_CREDIT);
        	}
        	
        	if(UDIOrderTypes.CUSTOMER_RETURN_ORDER.getOrderType().equals(orderType))
        	{
        		isSOTrx = true;
        		config.setPaymentRule(Constants.PAYMENT_RULE_CREDIT);
        	}
        }
        
        config.setOrderType(orderType);
        config.setIsSOTrx(isSOTrx);
        
        return new ActionForward("/CreatePOSOrderScreen.do");
    }
        
    
    public static final String LOAD_ADVANCED_ORDER_SCREEN = "loadAdvancedOrderScreen";
    public ActionForward loadAdvancedOrderScreen(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    { 
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx=TmkJSPEnv.getCtx(request);
        Object isSO = request.getParameter(Constants.IS_SOTRX);
        Boolean isSOTrx = Boolean.valueOf(String.valueOf(isSO));
        Object pListId = request.getSession().getAttribute(Constants.ORDER_PRICE_LIST);
        Integer priceListId = PriceListManager.getDefaultPriceListId(ctx, isSOTrx);
        try
        {
            if (pListId != null)
            {
                String prListId = String.valueOf(pListId);
                priceListId = Integer.valueOf(prListId);
            }
        }
        catch (NumberFormatException e)
        {
        }
        
        int roleId = Env.getAD_Role_ID(ctx);                
        float discountAllowed = RoleManager.getDiscountAllowed(ctx, roleId, null).floatValue(); 
        boolean isAllowedOverridePriceLimit = RoleManager.isOverridePriceLimitAllowed(ctx, roleId, null);
        
        String orderType = String.valueOf(request.getSession().getAttribute(Constants.ORDER_TYPE));
        
        return new ActionForward("/CreateAdvancedPOSOrderScreen.do?discountAllowed="+discountAllowed+
                "&isAllowedOverridePriceLimit="+isAllowedOverridePriceLimit+"&orderType="+orderType+"&priceListId="+ priceListId);
    }   
    
    public ActionForward saveBPartnerInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    { 
        ActionForward fwd= init(mapping,form,request,response);
        if (fwd!=null)
        {
            return fwd;
        }

        Properties ctx=TmkJSPEnv.getCtx(request);
        String bp = String.valueOf(request.getParameter("bPartnerId"));
        String plId = String.valueOf(request.getParameter("priceListId"));
        try
        {
            Integer bPartnerId = Integer.valueOf(bp);
            String name = String.valueOf(request.getParameter("name"));
            String creditLimit = String.valueOf(request.getParameter("creditLimit"));
            Integer priceListId = Integer.valueOf(plId);
            
            request.getSession().setAttribute(Constants.ORDER_PRICE_LIST, priceListId);
            request.getSession().setAttribute(Constants.BPARTNER_ID, bPartnerId);
            request.getSession().setAttribute(Constants.BPARTNER_NAME, name);
            request.getSession().setAttribute(Constants.BPARTNER_CREDIT, creditLimit);
        }
        catch (NumberFormatException e)
        {
        }

        return null;
    }
        
    public static ShoppingCartBean ComputeTotalLinePriceForDiscount(HttpServletRequest request, ShoppingCartBean cartBean) throws Exception
    {        
        CheckoutBean checkoutBean = (CheckoutBean) request.getSession().getAttribute(Constants.CHECKOUT_BEAN);
        
        if(checkoutBean == null)
        {
            return cartBean;
        }
        
        BigDecimal[] totalLinePrices = checkoutBean.getDiscountedPrice();
        BigDecimal[] qtyPerLine = checkoutBean.getQtyPerLine();
        
        ArrayList<ItemBean> itemList = cartBean.getItems();
        Iterator itr = itemList.iterator();
        
        for(int i=0; i< totalLinePrices.length; i++)
        {
            ItemBean itemBean = cartBean.getItems().get(i);
            BigDecimal totalLinePrice = itemBean.getPrice();
                
            
           //When increasing or decreasing quantity after giving discount on percentage, give the same percentage
           //discount on this new totalLine
           if (itemBean.getIsDiscountOnPercentage())
           {
               if (itemBean.getDiscountPercent().doubleValue() > 0)
               {
                   totalLinePrice = totalLinePrice.multiply(((Env.ONEHUNDRED.subtract(itemBean.getDiscountPercent()))).divide(Env.ONEHUNDRED));
                   totalLinePrice = totalLinePrice.setScale(2, RoundingMode.HALF_UP);
                   
               }
           }
           
           //When increasing or decreasing quantity, if discount was given on total line, retain the same 
           //total value if quantity of this item is not changed
           else if (itemBean.getIsDiscountOnTotal() && (qtyPerLine[i].compareTo(itemBean.getQty()) == 0))
           {
               totalLinePrice = totalLinePrices[i];  
           }
           else if (!itemBean.getIsDiscountOnInclUnitPrice())
           {
               itemBean.setDiscountPercent(Env.ZERO);
           }
           
           itemBean.setPrice(totalLinePrice);
           
        }
         
        cartBean.setItems(itemList);
        return cartBean;
      }
   
}