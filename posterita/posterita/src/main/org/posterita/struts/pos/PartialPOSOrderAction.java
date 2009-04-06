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
 *  */


package org.posterita.struts.pos;

import java.util.ArrayList;
import java.util.Properties;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.compiere.model.MOrder;
import org.compiere.util.Env;
import org.compiere.util.Trx;

import org.posterita.Constants;
import org.posterita.beans.ItemBean;
import org.posterita.beans.OrderLineBean;
import org.posterita.beans.ShoppingCartBean;
import org.posterita.beans.WebDocumentBean;
import org.posterita.businesslogic.OrderReferenceManager;
import org.posterita.businesslogic.OrderManager;
import org.posterita.businesslogic.POSTerminalManager;
import org.posterita.businesslogic.PartialPOSManager;
import org.posterita.businesslogic.administration.BPartnerManager;
import org.posterita.businesslogic.administration.RoleManager;
import org.posterita.businesslogic.stock.StockManager;
import org.posterita.core.SessionStorage;
import org.posterita.core.TmkJSPEnv;
import org.posterita.core.TrxPrefix;
import org.posterita.exceptions.ApplicationException;
import org.posterita.exceptions.BPartnerNoLocationException;
import org.posterita.exceptions.BPartnerOverCreditLimitException;
import org.posterita.exceptions.DiscountLimitException;
import org.posterita.exceptions.InputQuantityLessThanZeroException;
import org.posterita.exceptions.InsufficientQtyException;
import org.posterita.exceptions.InvalidInvokeOrderStatusException;
import org.posterita.exceptions.InvalidOrderIDException;
import org.posterita.exceptions.InvalidOrderTypeException;
import org.posterita.exceptions.InvalidTenderedAmountException;
import org.posterita.exceptions.NoOrderLineFoundException;
import org.posterita.exceptions.NoOrderLineSelectedException;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.ProductNotFoundException;
import org.posterita.exceptions.ProductNotOnPriceListException;
import org.posterita.exceptions.QuantityNotAvailableException;
import org.posterita.exceptions.UOMValuePrecisionNotValidException;
import org.posterita.form.InvokeOrderForm;
import org.posterita.lib.UdiConstants;
import org.posterita.struts.core.DefaultForm;


public class PartialPOSOrderAction extends POSDispatchAction
{
    public static final String CREATE_POS_ORDER="createPOSOrder";
    public ActionForward createPOSOrder(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException, DiscountLimitException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx=TmkJSPEnv.getCtx(request);
        DefaultForm df= (DefaultForm) form;
        OrderLineBean bean=(OrderLineBean) df.getBean();
        
        //validate payment type
        
        
        ShoppingCartBean cartBean = (ShoppingCartBean) request.getSession().getAttribute(Constants.PARTIAL_ORDER_SHOPPING_CART);
        
        if(cartBean==null ||cartBean.getItems() == null||cartBean.getItems().size()==0)
        {           
            postGlobalError("error.pos.shoppingcart.empty",request);             
            return mapping.getInputForward();
        }
            
        MOrder posOrder = null;
       
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
        
        request.getSession().setAttribute(Constants.BPARTNER, bPartnerId);
       
        bean.setBpartnerId(bPartnerId);
        
        Trx trx = Trx.get(TrxPrefix.getPrefix(),true);
        
        try
        {
        	trx.start();
            posOrder= PartialPOSManager.createPOSOrder(ctx,bean,cartBean.getItems(),null/*trx.getTrxName()*/);
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
        catch(InsufficientQtyException e3)
        {
            trx.rollback();
            postGlobalError("error.insufficient.qty",e3.getMessage(),request);
            return mapping.getInputForward();
        }
        finally
        {
        	 trx.close();
        }
     
        bean.setOrderId(Integer.valueOf(posOrder.get_ID()));        
        
        df.populate(new OrderLineBean());
        WebDocumentBean webBean = OrderReferenceManager.getWebOrderBean(ctx, posOrder);
        if(posOrder.getPaymentRule().equals(UdiConstants.PAYMENTRULE_MIXED))
        {
            if(bean.getPaymentByCash()!=null)
                webBean.setPaymentByCash(bean.getPaymentByCash());
            if(bean.getPaymentByCard()!=null)
                webBean.setPaymentByCard(bean.getPaymentByCard());
            if(bean.getPaymentByChq()!=null)
                webBean.setPaymentbyCheque(bean.getPaymentByChq());
        }
       
        
        ArrayList list = PartialPOSManager.populateOrderLines(ctx,posOrder);
        WebDocumentBean documentBean=PartialPOSManager.calculateOrderTotals(list);
        request.getSession().setAttribute(Constants.CURRENT_PARTIAL_POS_ORDER_ID,posOrder.get_ID());
        request.getSession().setAttribute(Constants.ORDER_LINE_BEAN,bean);
        request.setAttribute(Constants.PARTIAL_POS_ORDER_LINES,list);
        /*request.removeAttribute(Constants.POS_ORDER_LINES);*/      
      
        SessionStorage.putCompleteOrderInfo(ctx,request,webBean,documentBean);
        
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
        
        OrderLineBean bean=(OrderLineBean) request.getSession().getAttribute(Constants.ORDER_LINE_BEAN);
        Integer orderId =  (Integer)request.getSession().getAttribute(Constants.CURRENT_PARTIAL_POS_ORDER_ID);
        MOrder completedPOSOrder=null;
                
        Trx trx = Trx.get(TrxPrefix.getPrefix(),true);
        
        try
        {
        	trx.start();
            completedPOSOrder=PartialPOSManager.completePOSOrder(ctx,orderId.intValue(),bean,trx.getTrxName());             
            trx.commit();           
        }
        catch(NoOrderLineFoundException e)
        {
            trx.rollback();
            postGlobalError("error.pos.no.orderline",request);
            request.getSession().removeAttribute(Constants.PARTIAL_ORDER_SHOPPING_CART);
            request.getSession().removeAttribute(Constants.PARTIAL_ORDER_SHOPPING_CART_ITEMS);
            
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
            PartialPOSManager.getAmountForMixedPayment(ctx,completedPOSOrder,webBean);
        }
        
        ArrayList list = PartialPOSManager.populateOrderLines(ctx,completedPOSOrder);
        WebDocumentBean documentBean=PartialPOSManager.calculateOrderTotals(list);        
        request.setAttribute(Constants.POS_ORDER_LINES,list);
        
        /*request.removeAttribute(Constants.POS_ORDER_LINES);*/
        request.getSession().removeAttribute(Constants.BPARTNER);
        request.getSession().removeAttribute(Constants.ALL_CUSTOMERS);
        request.getSession().removeAttribute(Constants.PARTIAL_ORDER_SHOPPING_CART);
        request.getSession().removeAttribute(Constants.PARTIAL_ORDER_SHOPPING_CART_ITEMS);
        request.getSession().removeAttribute(Constants.CURRENT_PARTIAL_POS_ORDER_ID);
        request.setAttribute(Constants.ORDER_NAME,"POS ORDER");
        SessionStorage.putCompleteOrderInfo(ctx,request,webBean,documentBean);
        
        return mapping.findForward(COMPLETE_POS_ORDER);
    }
    
    
    public static final String GET_POS_PAYMENT_DETAILS="getPOSPaymentDetails";
    public ActionForward getPOSPaymentDetails(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
       // Properties ctx=TmkJSPEnv.getCtx(request);
        DefaultForm df= (DefaultForm) form;
        OrderLineBean bean=(OrderLineBean) df.getBean();
        df.populate(new OrderLineBean());
        request.getSession().setAttribute(Constants.BPARTNER, bean.getBpartnerId());
        ShoppingCartBean cartBean = (ShoppingCartBean) request.getSession().getAttribute(Constants.PARTIAL_ORDER_SHOPPING_CART);
       
        if(cartBean==null ||cartBean.getItems() == null||cartBean.getItems().size()==0)
        {           
            postGlobalError("error.pos.shoppingcart.empty",request);             
            return mapping.getInputForward();
        }
        
        request.getSession().setAttribute(Constants.PARTIAL_ORDER_SHOPPING_CART, cartBean);
        request.getSession().setAttribute(Constants.PARTIAL_ORDER_SHOPPING_CART_ITEMS, cartBean.getItems());
        return mapping.findForward(GET_POS_PAYMENT_DETAILS);
        
        
    }    
    
    public static final String DELETE_POS_ORDERLINE = "deletePOSOrderLine";
    public ActionForward deletePOSOrderLine(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx=TmkJSPEnv.getCtx(request);
        DefaultForm df= (DefaultForm) form;
        OrderLineBean bean=(OrderLineBean) df.getBean();
                
        String forward = DELETE_POS_ORDERLINE;		
        
        Trx trx = Trx.get(TrxPrefix.getPrefix(),true);
        
		try 
		{
			trx.start();
			forward = PartialPOSManager.deleteOrderLines(ctx,bean.getPosOrderLineIds(),trx.getTrxName());
            trx.commit();
		} 
		catch (NoOrderLineSelectedException e) 
		{
            trx.rollback();
			SessionStorage.putPOSOrderInfo(ctx,bean,request,forward);
	        
			postGlobalError("error.order.noOrderline.selected",request);			
			return mapping.getInputForward();
		}
		catch (OperationException e)
		{
			trx.rollback();
			throw e;
		}			
		finally
		{
			trx.close();
		}
		
		Integer orderId =  (Integer)request.getSession().getAttribute(Constants.CURRENT_PARTIAL_POS_ORDER_ID);		
		//validating order
		//if lines == 0 goto createpartialOrder
		
		if(OrderManager.isOrderEmpty(ctx,orderId.intValue(),null))
		{
			request.getSession().removeAttribute(Constants.ALL_CUSTOMERS);
	        request.getSession().removeAttribute(Constants.PARTIAL_ORDER_SHOPPING_CART);
	        request.getSession().removeAttribute(Constants.PARTIAL_ORDER_SHOPPING_CART_ITEMS);
			return new ActionForward("/CreatePartialPOSOrder.do");
		}
		
		//reload shopping cart
		ShoppingCartBean cart = PartialPOSManager.getShoppingCartForOrder(ctx,orderId.intValue(),null);
		request.getSession().setAttribute(Constants.PARTIAL_ORDER_SHOPPING_CART,cart);
		request.getSession().setAttribute(Constants.PARTIAL_ORDER_SHOPPING_CART_ITEMS,cart.getItems());
		
		
        
        SessionStorage.putPOSOrderInfo(ctx,bean,request,forward);
        df.populate(new OrderLineBean());
        
        return mapping.findForward(DELETE_POS_ORDERLINE);
    }
    
       
    public static final String ADD_TO_SHOPPING_CART = "addToShoppingCart";
    public ActionForward addToShoppingCart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException
    {
        Properties ctx = TmkJSPEnv.getCtx(request);
        DefaultForm df = (DefaultForm) form;
        OrderLineBean bean = (OrderLineBean) df.getBean();
        ShoppingCartBean cartBean = (ShoppingCartBean) request.getSession().getAttribute(Constants.PARTIAL_ORDER_SHOPPING_CART);
        
        
        try
        {
           // PartialPOSManager.getQtyAndItem(bean);
        	if(bean.getProductId()==null && bean.getBarCode()==null)
        	{
        		postGlobalError("error.barcode.required", request);
        		return mapping.getInputForward();
        	}
        	
            cartBean = StockManager.addToPOSCart(ctx, bean, cartBean,true,Boolean.parseBoolean(bean.getIfAdd()));            
        }
        catch (InputQuantityLessThanZeroException e1)        
        {
            postGlobalError("error.invalid.inputQty", request);
            return mapping.getInputForward();
        }
        catch (ProductNotFoundException e2)
        {
            postGlobalError("error.product.not.found", e2.getMessage(), request);
            return mapping.getInputForward();
        }
        catch(QuantityNotAvailableException e3)
        {
            postGlobalError("error.quantity.notAvailable", e3.getMessage(), request);
            return mapping.getInputForward();
        }
        
        catch(ProductNotOnPriceListException e4)
        {
            postGlobalError("error.product.price.not.found", e4.getMessage(), request);
            return mapping.getInputForward();
        }       
        
        catch (UOMValuePrecisionNotValidException e)
        {
        	postGlobalError("error.precision", e.getMessage(), request);
            return mapping.getInputForward();
        }  
        
        request.getSession().setAttribute(Constants.PARTIAL_ORDER_SHOPPING_CART, cartBean);
        request.getSession().setAttribute(Constants.PARTIAL_ORDER_SHOPPING_CART_ITEMS, cartBean.getItems());
        
        df.setQtyAndItem("");
        
        return mapping.findForward(ADD_TO_SHOPPING_CART);
    }
    
    
    
    public static final String COMPLETE_PARTIAL_POS_ORDER="completePartialPOSOrder";
    public ActionForward completePartialPOSOrder(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx=TmkJSPEnv.getCtx(request);
        DefaultForm df= (DefaultForm) form;
      
        Integer orderId =  (Integer)request.getSession().getAttribute(Constants.CURRENT_PARTIAL_POS_ORDER_ID);	
        
        //MOrder posOrder=(MOrder) request.getSession().getAttribute(Constants.CURRENT_POS_ORDER);
        //MOrder completedPOSOrder=null;
        //boolean error = false;
                
        if (orderId == null)
        {
        	return mapping.getInputForward();
        }
        if(OrderManager.isOrderEmpty(ctx,orderId.intValue(),null))
        {
            postGlobalError("error.pos.no.orderline",request);
            request.getSession().removeAttribute(Constants.PARTIAL_ORDER_SHOPPING_CART);
            request.getSession().removeAttribute(Constants.PARTIAL_ORDER_SHOPPING_CART_ITEMS);
            return mapping.getInputForward();
        } 
        
        //completedPOSOrder=PartialPOSManager.completePOSOrder(ctx,posOrder,bean);           
        MOrder completedPOSOrder = OrderManager.prepareOrder(ctx,orderId.intValue(),null);     
        
        df.populate(new OrderLineBean());
        WebDocumentBean webBean = OrderReferenceManager.getWebOrderBean(ctx, completedPOSOrder);
        
        if(completedPOSOrder.getPaymentRule().equals(UdiConstants.PAYMENTRULE_MIXED))
        {
            PartialPOSManager.getAmountForMixedPayment(ctx,completedPOSOrder,webBean);
        }
        
        ArrayList list = PartialPOSManager.populateOrderLines(ctx,completedPOSOrder);
        WebDocumentBean documentBean=PartialPOSManager.calculateOrderTotals(list);
        request.getSession().setAttribute(Constants.CURRENT_PARTIAL_POS_ORDER_ID,orderId);
        request.setAttribute(Constants.POS_ORDER_LINES,list);
        
        /*request.removeAttribute(Constants.POS_ORDER_LINES);*/
        request.getSession().removeAttribute(Constants.BPARTNER);
        request.getSession().removeAttribute(Constants.ALL_CUSTOMERS);
        request.getSession().removeAttribute(Constants.PARTIAL_ORDER_SHOPPING_CART);
        request.getSession().removeAttribute(Constants.PARTIAL_ORDER_SHOPPING_CART_ITEMS);
        request.getSession().removeAttribute(Constants.CURRENT_PARTIAL_POS_ORDER_ID);
        SessionStorage.putCompleteOrderInfo(ctx,request,webBean,documentBean);
        request.setAttribute(Constants.ORDER_NAME,"POS ORDER");
        
        return mapping.findForward(COMPLETE_PARTIAL_POS_ORDER);
    }
    
       
    public static final String INVOKE_PARTIAL_ORDER = "invokePartialOrder";    
    public ActionForward invokePartialOrder(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
    	ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx=TmkJSPEnv.getCtx(request);
        InvokeOrderForm f = (InvokeOrderForm) form;
        OrderLineBean bean = (OrderLineBean) f.getBean();
        
        String documentNo = bean.getDocumentNo();
        
        MOrder order = null;
        
        Trx trx = Trx.get(TrxPrefix.getPrefix(),true);
        
		try 
		{
			trx.start();
			order = PartialPOSManager.invokePartialOrder(ctx,documentNo,trx.getTrxName());
			trx.commit();
		} 
		catch (InvalidOrderIDException e) 
		{
			trx.rollback();
			postGlobalError("error.invalid.orderId",request);
        	return mapping.getInputForward();			
		} 
		catch (InvalidOrderTypeException e) 
		{
			trx.rollback();
			postGlobalError("error.invalid.orderType",request);
        	return mapping.getInputForward();			
		} 
		catch (NoOrderLineFoundException e) 
		{
			trx.rollback();
			postGlobalError("error.pos.invoke.no.orderline",request);
        	return mapping.getInputForward();
		} 
		catch (InvalidInvokeOrderStatusException e) 
		{
			trx.rollback();
			postGlobalError("error.invoke.status",e.getStatus(),request);
        	return mapping.getInputForward();
		}
		finally
		{
			trx.close();
		}
        
		int bPartnerId = order.getC_BPartner_ID();        
        ShoppingCartBean cart = PartialPOSManager.getShoppingCartForOrder(ctx,order.get_ID(),null);
        ArrayList<ItemBean> cartItems = cart.getItems();
        
        BigDecimal discountAllowed = RoleManager.getDiscountAllowed(ctx, Env.getAD_Role_ID(ctx),null);
        
        //putting cart & other info in session
        request.getSession().setAttribute(Constants.BPARTNER, Integer.valueOf(bPartnerId));
        request.getSession().setAttribute(Constants.PARTIAL_ORDER_SHOPPING_CART, cart);
        request.getSession().setAttribute(Constants.PARTIAL_ORDER_SHOPPING_CART_ITEMS, cartItems);            
        request.getSession().setAttribute(Constants.CURRENT_PARTIAL_POS_ORDER_ID,Integer.valueOf(order.get_ID()));
        request.getSession().setAttribute(Constants.DISCOUNT_ALLOWED, discountAllowed);
        
    	return mapping.findForward(INVOKE_PARTIAL_ORDER);      
        
    }
    
    public static final String SET_POS_PAYMENT_DETAILS = "setPOSPaymentDetails";
    public ActionForward setPOSPaymentDetails(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
    	ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;        
        
        //mapping.setInput("/GetPartialPOSPaymentDetails.do");
        
        Properties ctx=TmkJSPEnv.getCtx(request);
        DefaultForm df= (DefaultForm) form;
        OrderLineBean bean=(OrderLineBean) df.getBean();       
        
        ShoppingCartBean cartBean = (ShoppingCartBean) request.getSession().getAttribute(Constants.PARTIAL_ORDER_SHOPPING_CART);
        
        if(cartBean==null)
        {           
            postGlobalError("error.pos.shoppingcart.empty",request);             
            return mapping.getInputForward();
        }
        
        //validate discount entered
        String[] discountPercentage = df.getDiscountPercent();
        String[] actualPrice = df.getActualPrice();
        
        for(String s : discountPercentage)
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
          
        Integer orderId = (Integer) request.getSession().getAttribute(Constants.CURRENT_PARTIAL_POS_ORDER_ID);
        Integer bPartnerId = bean.getBpartnerId();
        
        if(bPartnerId==null)
        	bPartnerId = (Integer) request.getSession().getAttribute(Constants.BPARTNER);
        if(bPartnerId==null)
        	 bPartnerId=Integer.valueOf(POSTerminalManager.getCashBPartner(ctx).get_ID());
           
        bean.setBpartnerId(bPartnerId);
        MOrder posOrder = null;
       
        Trx trx = Trx.get(TrxPrefix.getPrefix(), true);
        
        try
        {
        	trx.start();
			posOrder= PartialPOSManager.setOrderPaymentDetails(ctx,orderId,bean,cartBean.getItems(), trx.getTrxName());
            trx.commit();
        }
        catch(InvalidTenderedAmountException e)
        {           
        	trx.rollback();
            postGlobalError("error.pos.incorrect.tendered.amount",request);
            return mapping.getInputForward();
        }           
		catch (OperationException e)
		{
			trx.rollback();
			throw e;
		}
		finally
		{
			trx.close();
		}
     
        bean.setOrderId(Integer.valueOf(posOrder.get_ID()));        
        
        df.populate(new OrderLineBean());
        WebDocumentBean webBean = OrderReferenceManager.getWebOrderBean(ctx, posOrder);
        if(posOrder.getPaymentRule().equals(UdiConstants.PAYMENTRULE_MIXED))
        {
            if(bean.getPaymentByCash()!=null)
                webBean.setPaymentByCash(bean.getPaymentByCash());
            if(bean.getPaymentByCard()!=null)
                webBean.setPaymentByCard(bean.getPaymentByCard());
            if(bean.getPaymentByChq()!=null)
                webBean.setPaymentbyCheque(bean.getPaymentByChq());
        }
       
        
        ArrayList list = PartialPOSManager.populateOrderLines(ctx,posOrder);
        WebDocumentBean documentBean=PartialPOSManager.calculateOrderTotals(list);
        //TODO put id only
        request.getSession().setAttribute(Constants.CURRENT_POS_ORDER_ID,Integer.valueOf(orderId));
        request.getSession().setAttribute(Constants.ORDER_LINE_BEAN,bean);
        request.setAttribute(Constants.POS_ORDER_LINES,list);
        request.setAttribute(Constants.PARTIAL_POS_OREDR,"partialPOSOrder");
      
        SessionStorage.putCompleteOrderInfo(ctx,request,webBean,documentBean);       
        
        
    	return mapping.findForward(SET_POS_PAYMENT_DETAILS);
    }
    
    public static final String DELETE_PARTIAL_ORDER = "deletePartialOrder";
    public ActionForward deletePartialOrder(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
    	ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx=TmkJSPEnv.getCtx(request);
        InvokeOrderForm f = (InvokeOrderForm) form;
        OrderLineBean bean = (OrderLineBean) f.getBean();
        
        int orderId = bean.getOrderId().intValue();
        
        Trx trx = Trx.get(TrxPrefix.getPrefix(),true);
        
        try 
        {
        	trx.start();
			OrderManager.deleteDraftedOrder(ctx,orderId,trx.getTrxName());
			trx.commit();
		} 
        catch (OperationException e) 
		{
        	trx.rollback();
			throw e;
		}
        finally
        {
        	trx.close();
        }
        
        return mapping.findForward(DELETE_PARTIAL_ORDER);
    }
    
    //methods called using AJAX
    public static final String INCREMENT_QTY = "incrementQty";
    public ActionForward incrementQty(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException
    {
        addToShoppingCart(mapping,form,request,response);
        return new ActionForward("/jsp/pos/partialPOSOrderShoppingCart.jsp");
    }
    
    public static final String DECREMENT_QTY = "decrementQty";
    public ActionForward decrementQty(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException
    {
        addToShoppingCart(mapping,form,request,response);
        return new ActionForward("/jsp/pos/partialPOSOrderShoppingCart.jsp");
    }
    
    public static final String DELETE_FROM_POS_CART = "deleteFromPOSCart";
    public ActionForward deleteFromPOSCart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException
    {
        Properties ctx = TmkJSPEnv.getCtx(request);        
        DefaultForm df = (DefaultForm) form;
        OrderLineBean bean = (OrderLineBean) df.getBean();                
        ArrayList items = (ArrayList) request.getSession().getAttribute(Constants.PARTIAL_ORDER_SHOPPING_CART_ITEMS);               
        ArrayList itemsInCart = StockManager.deleteItemFromPOSList(ctx,items, bean.getProductId(),true);        
        request.getSession().setAttribute(Constants.PARTIAL_ORDER_SHOPPING_CART_ITEMS, itemsInCart); 
        
        return new ActionForward("/jsp/pos/partialPOSOrderShoppingCart.jsp");
    }
    
    public static final String DELETE_ORDER = "deleteOrder";
    public ActionForward deleteOrder(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException
    {
        Properties ctx = TmkJSPEnv.getCtx(request);        
        DefaultForm df = (DefaultForm) form;
        OrderLineBean bean = (OrderLineBean) df.getBean(); 
        
        int orderId = bean.getOrderId().intValue();
        
        Trx trx = Trx.get(TrxPrefix.getPrefix(),true);
        
        try 
        {
        	trx.start();
			OrderManager.deleteDraftedOrder(ctx,orderId,trx.getTrxName());
			trx.commit();
		} 
        catch (OperationException e) 
		{
        	trx.rollback();
			throw e;
		}
        finally
        {
        	trx.close();
        }
        
        //removing cart & other info in session
        HttpSession session = request.getSession();
        
        session.removeAttribute(Constants.BPARTNER);
        session.removeAttribute(Constants.PARTIAL_ORDER_SHOPPING_CART);
        session.removeAttribute(Constants.PARTIAL_ORDER_SHOPPING_CART_ITEMS);            
        session.removeAttribute(Constants.CURRENT_PARTIAL_POS_ORDER_ID);
        
        return mapping.getInputForward();
    }
    
}
