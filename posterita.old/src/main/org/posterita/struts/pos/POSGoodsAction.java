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
 * Created on 27-Apr-2006
 */


package org.posterita.struts.pos;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.compiere.model.MBPartner;
import org.compiere.model.MOrder;
import org.compiere.model.MPayment;
import org.compiere.model.MProduct;
import org.compiere.process.DocAction;
import org.compiere.process.DocumentEngine;
import org.compiere.util.Trx;

import org.posterita.Constants;
import org.posterita.beans.OrderBean;
import org.posterita.beans.OrderLineBean;
import org.posterita.beans.POSSupplierBean;
import org.posterita.beans.ProductBean;
import org.posterita.beans.ShoppingCartBean;
import org.posterita.beans.WebDocumentBean;
import org.posterita.businesslogic.BPartnerManager;
import org.posterita.businesslogic.OrderReferenceManager;
import org.posterita.businesslogic.OrderManager;
import org.posterita.businesslogic.POSGoodsManager;
import org.posterita.businesslogic.POSManager;
import org.posterita.businesslogic.POSTerminalManager;
import org.posterita.businesslogic.StockManager;
import org.posterita.core.SessionStorage;
import org.posterita.core.TmkJSPEnv;
import org.posterita.core.TrxPrefix;
import org.posterita.exceptions.ApplicationException;
import org.posterita.exceptions.BPartnerNoLocationException;
import org.posterita.exceptions.BPartnerOverCreditLimitException;
import org.posterita.exceptions.InputQuantityLessThanZeroException;
import org.posterita.exceptions.InvalidOrderIDException;
import org.posterita.exceptions.NoOrderLineFoundException;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.ProductNotFoundException;
import org.posterita.exceptions.ProductNotOnPriceListException;
import org.posterita.exceptions.QuantityNotAvailableException;
import org.posterita.form.OrderLineForm;
import org.posterita.lib.UdiConstants;
import org.posterita.order.UDIOrderTypes;
import org.posterita.struts.core.DefaultForm;
import org.posterita.core.bean.ElementBean;
import org.posterita.core.businesslogic.ElementManager;


public class POSGoodsAction extends POSDispatchAction
{
    public static final String CREATE_POS_GOODS_RECEIVE_NOTE="createPOSGoodsReceiveNote";
    public ActionForward createPOSGoodsReceiveNote(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException, IOException
    {
        ActionForward fwd= init(mapping,form,request,response);

        if(fwd!=null)
            return fwd;

        Properties ctx=TmkJSPEnv.getCtx(request);
        DefaultForm df= (DefaultForm) form;
        OrderLineBean bean=(OrderLineBean) df.getBean();
 
        Integer bPartnerId = bean.getBpartnerId();
        String supplierRef = bean.getDescription();
        
        MOrder previousOrder= null;
        previousOrder=(MOrder)request.getSession().getAttribute(Constants.PRESENT_GRN_ORDER);
        
        if(previousOrder!=null)
        {
            bean.setOrderId(Integer.valueOf(previousOrder.get_ID()));
        }
        else
        {
            bean.setOrderId(null);
        }
        
        if( bPartnerId == null )
        {
        	//see if we have previously saved bpartnerId
        	bPartnerId= (Integer) request.getSession().getAttribute(Constants.B_PARTNER_ID);
        }
        else
        {
        	//set the new bpartnerId in session
        	request.getSession().setAttribute(Constants.B_PARTNER_ID, bPartnerId);
        }
        
        if( supplierRef == null )
        {
        	//see if we have previously saved supplierRef
        	supplierRef=(String) request.getSession().getAttribute(Constants.DESCRIPTION);
        }
        else
        {
        	//set new supplierRef in session
        	request.getSession().setAttribute(Constants.DESCRIPTION, supplierRef);
        }
        
        if( bPartnerId == null )
        {
        	postGlobalError("error.required.supplierName", request);
        	return mapping.getInputForward(); 
        }
        
        
        ShoppingCartBean cartBean = (ShoppingCartBean) request.getSession().getAttribute(Constants.GOODS_RECEIVE_NOTE_SHOPPING_CART); 
        
        if(cartBean == null||cartBean.getItems() == null||cartBean.getItems().size() == 0)
        {
        	postGlobalError("error.pos.shoppingcart.empty",request);
        	return mapping.getInputForward();        	
        }
        
        MOrder goodsReceiveNote = null;

        Trx trx = Trx.get(TrxPrefix.getPrefix(),true);
     
        try
        {
        	trx.start();
            goodsReceiveNote = POSGoodsManager.createGoodsReceiveNote(ctx,bean,cartBean.getItems(),bPartnerId,supplierRef,trx.getTrxName());
            trx.commit();
        }
        catch(BPartnerNoLocationException ex)
        {
        	trx.rollback();
        	postGlobalError("error.bpartner.nolocation", request);
        	return mapping.getInputForward();
        }
        catch(OperationException e)
        {
           trx.rollback();
           throw (e);
        }
        finally
        {
            trx.close();
        }
      
        bean.setOrderId(Integer.valueOf(goodsReceiveNote.get_ID()));
        
        df.populate(new OrderLineBean());
        WebDocumentBean webBean = OrderReferenceManager.getWebOrderBean(ctx,goodsReceiveNote);
        ArrayList list = POSManager.populateOrderLines(ctx,goodsReceiveNote);
        WebDocumentBean documentBean=POSManager.calculateOrderTotals(list);
        
        request.getSession().setAttribute(Constants.GOODS_RECEIVE_NOTE_ID,goodsReceiveNote.get_ID());
        request.setAttribute(Constants.GOODS_RECEIVE_NOTE_LINES,list);
        SessionStorage.putCompleteOrderInfo(ctx,request,webBean,documentBean);
        SessionStorage.putCompleteOrderInfo(ctx,request,webBean,documentBean);
        request.getSession().removeAttribute(Constants.PRESENT_GRN_ORDER);
        request.getSession().removeAttribute(Constants.PRESENT_GRN_ORDER_ID);
        
        return mapping.findForward(CREATE_POS_GOODS_RECEIVE_NOTE);
    }
    
    public static final String COMPLETE_GOODS_RECEIVE_NOTE="completeGoodsReceiveNote";
    public ActionForward completeGoodsReceiveNote(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx=TmkJSPEnv.getCtx(request);
        DefaultForm df= (DefaultForm) form;
        //OrderLineBean bean=(OrderLineBean) df.getBean();
        Integer goodsReceiveNoteId=(Integer) request.getSession().getAttribute(Constants.GOODS_RECEIVE_NOTE_ID);
        
        MOrder completedGRN=null;
        
        Trx trx =Trx.get(TrxPrefix.getPrefix(),true);
        
        if(goodsReceiveNoteId==null)
        {
           postGlobalError("error.no.order.exists",request);
           return mapping.getInputForward();
        }
        
        try
        {
        	trx.start();
            completedGRN=POSGoodsManager.createAndCompletePODocuments(ctx,goodsReceiveNoteId,null);
            trx.commit();
        }
        catch(NoOrderLineFoundException e)
        {
            trx.rollback();
            postGlobalError("error.pos.no.orderline",request);             
            request.getSession().removeAttribute(Constants.GOODS_RECEIVE_NOTE_SHOPPING_CART);
            request.getSession().removeAttribute(Constants.GOODS_RECEIVE_NOTE_SHOPPING_CART_ITEMS);
            return mapping.getInputForward();
        }
        catch(OperationException e)
        {
            trx.rollback();
            throw new OperationException("Could not complete documents. Reason: " + e);
            
        }
        finally
        {
            trx.close();
        }
       
        
        
        df.populate(new OrderLineBean());
        WebDocumentBean webBean = OrderReferenceManager.getWebOrderBean(ctx,completedGRN);
        ArrayList list = POSManager.populateOrderLines(ctx,completedGRN);
        WebDocumentBean documentBean=POSManager.calculateOrderTotals(list);
        
        //request.getSession().setAttribute(Constants.GOODS_RECEIVE_NOTE_ID,completedGRN.get_ID());
        request.setAttribute(Constants.GOODS_RECEIVE_NOTE_LINES,list);
        /*request.removeAttribute(Constants.POS_ORDER_LINES);*/
        
        
        
        SessionStorage.putCompleteOrderInfo(ctx,request,webBean,documentBean);
        request.getSession().removeAttribute(Constants.ALL_CUSTOMERS);
        request.getSession().removeAttribute(Constants.GOODS_RECEIVE_NOTE_SHOPPING_CART);
        request.getSession().removeAttribute(Constants.GOODS_RECEIVE_NOTE_SHOPPING_CART_ITEMS);
        request.getSession().removeAttribute(Constants.DESCRIPTION);
        request.getSession().removeAttribute(Constants.B_PARTNER_ID);
        request.getSession().removeAttribute(Constants.GOODS_RECEIVE_NOTE_ID);
        request.getSession().removeAttribute(Constants.DESCRIPTION);
        request.getSession().removeAttribute(Constants.PRESENT_GRN_ORDER);
        request.getSession().removeAttribute(Constants.PRESENT_GRN_ORDER_ID);
        
        request.removeAttribute(Constants.DESCRIPTION);
        
        return mapping.findForward(COMPLETE_GOODS_RECEIVE_NOTE);
    }
    
    
    public static final String CREATE_POS_GOODS_RETURN_NOTE="createPOSGoodsReturnNote";
    public ActionForward createPOSGoodsReturnNote(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx=TmkJSPEnv.getCtx(request);
        DefaultForm df= (DefaultForm) form;
        OrderLineBean bean=(OrderLineBean) df.getBean();
        
       
        Integer bPartnerId = bean.getBpartnerId();
        String supplierRef = bean.getDescription();
        Integer quantity = bean.getQuantity();
        
        if( bPartnerId == null )
        {
        	//see if we have previously saved bpartnerId
        	bPartnerId= (Integer) request.getSession().getAttribute(Constants.B_PARTNER_ID);
        }
        else
        {
        	//set the new bpartnerId in session
        	request.getSession().setAttribute(Constants.B_PARTNER_ID, bPartnerId);
        }
        
        if ( quantity == null)
        {
        	throw new OperationException("Quantity cannot be null");
        }
        if( quantity.equals(0) == true)
        {
        	throw new OperationException("Quantity cannot be zero");
        }       
        
        if( supplierRef == null )
        {
        	//see if we have previously saved supplierRef
        	supplierRef=(String) request.getSession().getAttribute(Constants.DESCRIPTION);
        }
        else
        {
        	//set new supplierRef in session
        	request.getSession().setAttribute(Constants.DESCRIPTION, supplierRef);
        }
        
        if( bPartnerId == null )
        {
        	postGlobalError("error.required.supplierName", request);
        	return mapping.getInputForward(); 
        }
        
        ShoppingCartBean cartBean = (ShoppingCartBean) request.getSession().getAttribute(Constants.GOODS_RETURN_NOTE_SHOPPING_CART); 
        
        if(cartBean == null||cartBean.getItems() == null||cartBean.getItems().size() == 0)
        {
        	postGlobalError("error.pos.shoppingcart.empty",request);
        	return mapping.getInputForward();        	
        }
        
        MOrder previousOrder= null;
        previousOrder=(MOrder)request.getSession().getAttribute(Constants.PRESENT_GOODS_RET_ORDER);
        
        if(previousOrder!=null)
        {
            bean.setOrderId(Integer.valueOf(previousOrder.get_ID()));
        }
        else
        {
            bean.setOrderId(null);
        }
        MOrder goodsReturnNote=null;
        
        Trx trx = Trx.get(TrxPrefix.getPrefix(),true);
        
        bean.getDescription();
        try
        {
        	trx.start();
            goodsReturnNote= POSGoodsManager.createGoodsReturnNote(ctx,bean,cartBean.getItems(),bPartnerId,supplierRef,trx.getTrxName());
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
        catch (OperationException e) 
        {
           trx.rollback();
           throw new OperationException("Could not create goods return note: Reason :" + e);
        }
        finally
        {
            trx.close();
        }
        bean.setOrderId(Integer.valueOf(goodsReturnNote.get_ID()));
        
        df.populate(new OrderLineBean());
        WebDocumentBean webBean = OrderReferenceManager.getWebOrderBean(ctx,goodsReturnNote);
        ArrayList list = POSManager.populateOrderLines(ctx,goodsReturnNote);
        WebDocumentBean documentBean=POSManager.calculateOrderTotals(list);
        request.setAttribute(Constants.GOODS_RETURN_NOTE_LINES,list);
        request.getSession().setAttribute(Constants.GOODS_RETURN_NOTE_ID,goodsReturnNote.get_ID());
        SessionStorage.putCompleteOrderInfo(ctx,request,webBean,documentBean);
        
        request.getSession().removeAttribute(Constants.PRESENT_GOODS_RET_ORDER);
        request.getSession().removeAttribute(Constants.PRESENT_GOODS_RET_ORDER_ID);
        
        return mapping.findForward(CREATE_POS_GOODS_RETURN_NOTE);
    }
    
    
    public static final String COMPLETE_GOODS_RETURN_NOTE="completeGoodsReturnNote";
    public ActionForward completeGoodsReturnNote(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx=TmkJSPEnv.getCtx(request);
        DefaultForm df= (DefaultForm) form;
        //OrderLineBean bean=(OrderLineBean) df.getBean();
        Integer goodsReturnNoteId=(Integer) request.getSession().getAttribute(Constants.GOODS_RETURN_NOTE_ID);
        
        Trx trx = Trx.get(TrxPrefix.getPrefix(),true);
        
        MOrder completedGRetN=null;
        
        
        if(goodsReturnNoteId==null)
        {
           postGlobalError("error.no.order.exists",request);
           return mapping.getInputForward();
        }

        try
        {
            trx.start();
            completedGRetN=POSGoodsManager.createAndCompletePODocuments(ctx,goodsReturnNoteId,null);//same sort of order i.e. sales Order
            trx.commit();
        }
        catch(BPartnerOverCreditLimitException e1)
        {
            trx.rollback();
            postGlobalError("error.bpartner.over.credit.limit",e1.getMessage(),request);
            return mapping.getInputForward();
        }
        catch(NoOrderLineFoundException e)
        {
            trx.rollback();
            postGlobalError("error.pos.no.orderline",request);             
            request.getSession().removeAttribute(Constants.GOODS_RETURN_NOTE_SHOPPING_CART);
            request.getSession().removeAttribute(Constants.GOODS_RETURN_NOTE_SHOPPING_CART_ITEMS);
            return mapping.getInputForward();
        }

        catch(OperationException e)
        {
            trx.rollback();
            throw new OperationException("Could not complete documents. Reason : " + e);
        }
        finally
        {
            trx.close();
        }
       
        df.populate(new OrderLineBean());
        WebDocumentBean webBean = OrderReferenceManager.getWebOrderBean(ctx,completedGRetN);
        ArrayList list = POSManager.populateOrderLines(ctx,completedGRetN);
        WebDocumentBean documentBean=POSManager.calculateOrderTotals(list);
        request.setAttribute(Constants.GOODS_RETURN_NOTE_LINES,list);
        //request.getSession().setAttribute(Constants.GOODS_RETURN_NOTE_ID,completedGRetN.get_ID());
        SessionStorage.putCompleteOrderInfo(ctx,request,webBean,documentBean);
        request.getSession().removeAttribute(Constants.ALL_CUSTOMERS);
        request.getSession().removeAttribute(Constants.GOODS_RETURN_NOTE_SHOPPING_CART);
        request.getSession().removeAttribute(Constants.GOODS_RETURN_NOTE_SHOPPING_CART_ITEMS);
        request.getSession().removeAttribute(Constants.GOODS_RETURN_NOTE_ID);
        request.getSession().removeAttribute(Constants.B_PARTNER_ID);
        request.getSession().removeAttribute(Constants.DESCRIPTION);
        request.getSession().removeAttribute(Constants.PRESENT_GOODS_RET_ORDER);
        request.getSession().removeAttribute(Constants.PRESENT_GOODS_RET_ORDER_ID);
        
        
        request.removeAttribute(Constants.DESCRIPTION);
        
        return mapping.findForward(COMPLETE_GOODS_RETURN_NOTE);
    }
    
    //public static final String GET_ALL_VENDORS="getAllVendors";
    public ActionForward getAllVendors(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd=init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx=TmkJSPEnv.getCtx(request);
        HttpSession session = request.getSession();       
       
        DefaultForm df = (DefaultForm) form;
        OrderLineBean b = (OrderLineBean) df.getBean();
        
        OrderLineForm f = (OrderLineForm) form;
        OrderLineBean bean = new OrderLineBean();
        
        Integer bPartnerId = (Integer) session.getAttribute(Constants.B_PARTNER_ID);
        String description = (String) session.getAttribute(Constants.DESCRIPTION);       
        
        bean.setBpartnerId(bPartnerId);
        bean.setDescription(description);
        
        f.populate(bean);
         
        ArrayList list=POSGoodsManager.getAllSuppliers(ctx);
        
        session.setAttribute(Constants.BP_LIST,list);
        return mapping.findForward(POSGoodsManager.getForward(ctx,b.getIsSales()));
    }
    
    public static final String ADD_TO_SHOPPING_CART = "addToShoppingCart";
    public ActionForward addToShoppingCart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        Properties ctx = TmkJSPEnv.getCtx(request);
        DefaultForm df = (DefaultForm) form;
        OrderLineBean bean = (OrderLineBean) df.getBean();
        
        String orderType = bean.getOrderType();
        String shoppingCart = null;
        String shoppingCartItem = null;
        
        if(orderType.equals(Constants.GOODS_RECEIVE_NOTE))
        {
        	shoppingCart     = Constants.GOODS_RECEIVE_NOTE_SHOPPING_CART;
        	shoppingCartItem = Constants.GOODS_RECEIVE_NOTE_SHOPPING_CART_ITEMS;
            
            String currSymbolePurchase=POSTerminalManager.getPOSDefaultPurchaseCurrency(ctx).getCurSymbol();
            request.setAttribute(Constants.CURRENCY_SYMBOLE,currSymbolePurchase);
           
        }
        else if(orderType.equals(Constants.GOODS_RETURN_NOTE))
        {
        	shoppingCart     = Constants.GOODS_RETURN_NOTE_SHOPPING_CART;
        	shoppingCartItem = Constants.GOODS_RETURN_NOTE_SHOPPING_CART_ITEMS;
           
            String currSymbolePurchase=POSTerminalManager.getPOSDefaultPurchaseCurrency(ctx).getCurSymbol();
            request.setAttribute(Constants.CURRENCY_SYMBOLE,currSymbolePurchase);
          
        }
        else if(orderType.equals(Constants.CUSTOMER_RETURN_ORDER))
        {
        	shoppingCart     = Constants.CUSTOMER_RETURN_ORDER_SHOPPING_CART;
        	shoppingCartItem = Constants.CUSTOMER_RETURN_ORDER_SHOPPING_CART_ITEMS;
            String currSymboleSales = POSTerminalManager.getPOSDefaultSellCurrency(ctx).getCurSymbol();
            request.setAttribute(Constants.CURRENCY_SYMBOLE,currSymboleSales);
           
        }
          
        
        ShoppingCartBean cartBean = (ShoppingCartBean) request.getSession().getAttribute(shoppingCart);
        
        try
        {
            //POSManager.getQtyAndItem(bean);
        	if(bean.getProductId()==null && bean.getBarCode()==null)
        	{
        		postGlobalError("error.barcode.required", request);
        		return mapping.getInputForward();
        	}
        	
            cartBean = StockManager.addToPOSCart(ctx, bean, cartBean,Boolean.parseBoolean(bean.getIsSales()),Boolean.parseBoolean(bean.getIfAdd()));            
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
        
        request.getSession().setAttribute(Constants.DESCRIPTION, bean.getDescription());
        bean.setDescription(bean.getDescription());
        request.getSession().setAttribute(shoppingCart, cartBean);
        request.getSession().setAttribute(shoppingCartItem, cartBean.getItems());
        request.getSession().setAttribute(Constants.B_PARTNER_ID,bean.getBpartnerId());
        
        df.setQtyAndItem("");
        
        return mapping.findForward(ADD_TO_SHOPPING_CART);
    }
    
    public static final String SET_SUPPLIER="setSupplier";
    public ActionForward setSupplier(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd=init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        //Properties ctx=TmkJSPEnv.getCtx(request);
        DefaultForm df = (DefaultForm)form;
        POSSupplierBean bean = (POSSupplierBean)df.getBean();
        request.getSession().setAttribute(Constants.B_PARTNER_ID,bean.getBpartnerId());
        return mapping.findForward(SET_SUPPLIER);
    }
    
    //NOTE:this does not select the actualy product category of compiere,its gets all the revenue recognitions
    //revenue reco is used to categorise the proucts
    public static final String GET_PRODUCT_CATEGORY="getProductCategory";
    public ActionForward getProductCategory(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd=init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        Properties ctx=TmkJSPEnv.getCtx(request);
        DefaultForm df = (DefaultForm)form;
        
        ArrayList list = POSGoodsManager.getAllRevenueRecos(ctx);
        ArrayList supList=POSGoodsManager.getAllSuppliers(ctx);
        ArrayList taxList=POSGoodsManager.getAllTaxCategory(ctx);
        request.getSession().setAttribute(Constants.BP_LIST,supList);
        request.getSession().setAttribute(Constants.PRODUCT_CATEGORY_ID,list); 
        request.getSession().setAttribute(Constants.TAX_CATEGORY_ID,taxList); 
       
        ProductBean bean = new ProductBean();
        bean.setProductType(MProduct.PRODUCTTYPE_Item);
        df.populate(bean);
        
        
        return mapping.findForward(GET_PRODUCT_CATEGORY);
    }
    
    
    public static final String CREATE_CUSTOMER_RETURN_ORDER="createCustomerReturnOrder";
    public ActionForward createCustomerReturnOrder(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx=TmkJSPEnv.getCtx(request);
        DefaultForm df= (DefaultForm) form;
        OrderLineBean bean=(OrderLineBean) df.getBean();
        
        HttpSession session = request.getSession();        
        Integer bPartnerId = bean.getBpartnerId();
        
        if( bPartnerId == null)
        {
        	bPartnerId = (Integer) request.getSession().getAttribute(Constants.B_PARTNER_ID);
        }
        else
        {
            request.getSession().setAttribute(Constants.B_PARTNER_ID, bPartnerId);
        }
        
        Integer qty = bean.getQuantity();
        
        if ( (qty.equals(0) == true) || (qty == null) )
        {
        	postGlobalError("error.qty.required", request);
        	return mapping.getInputForward();
        }
        
                
        String reason = bean.getDescription();
        
        if( reason == null )
        {
        	reason = (String) session.getAttribute(Constants.DESCRIPTION);
        	
        	if( reason == null )
            {
                postGlobalError("error.reason.required", request);
                return mapping.getInputForward();
            }
        }
        else
        {
        	session.setAttribute(Constants.DESCRIPTION, reason);
        }     
        MOrder previousOrder= null;
        previousOrder=(MOrder)request.getSession().getAttribute(Constants.PRESENT_CUSTOMER_RET_ORDER);
        
        if(previousOrder!=null)
        {
            bean.setOrderId(Integer.valueOf(previousOrder.get_ID()));
        }
        else
        {
            bean.setOrderId(null);
        }
        
        String posOrderDocNumber = (String) request.getSession().getAttribute(Constants.POS_ORDER_DOC_NUMBER);
        Integer paymentTermId =(Integer) request.getSession().getAttribute(Constants.CREDIT_PAYMENT_TERM_ID);
        String orderType = (String) request.getSession().getAttribute(Constants.ORDER_TYPE);
        
        if(posOrderDocNumber!=null)
            bean.setDocumentNo(posOrderDocNumber);
        if(orderType!=null)
            bean.setOrderType(orderType);
        if(paymentTermId!=null)
            bean.setPaymentTermId(paymentTermId);
        if(bPartnerId==null)
            bPartnerId=Integer.valueOf(POSTerminalManager.getPOSDefaultBpartner(ctx).get_ID());
        
        ShoppingCartBean cartBean = (ShoppingCartBean) request.getSession().getAttribute(Constants.CUSTOMER_RETURN_ORDER_SHOPPING_CART); 
        
        if(cartBean == null||cartBean.getItems() == null||cartBean.getItems().size() == 0)
        {
        	postGlobalError("error.pos.shoppingcart.empty",request);
        	return mapping.getInputForward();        	
        }
       
        MOrder customerReturnOrder=null;
        
        Trx trx = Trx.get(TrxPrefix.getPrefix(),true);
        
        try
        {
        	trx.start();
        	customerReturnOrder= POSGoodsManager.createCustomerReturn(ctx,bean,cartBean.getItems(),bPartnerId,reason,trx.getTrxName());
            trx.commit();
        }
        catch(BPartnerNoLocationException ex)
        {
        	trx.rollback();
        	postGlobalError("error.bpartner.nolocation", request);
        	return mapping.getInputForward();
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
           throw new OperationException("Could not create customer return order!! Reason : " + e);
       }
       finally
       {
           trx.close();
       }
       
        bean.setOrderId(Integer.valueOf(customerReturnOrder.get_ID()));
        
        df.populate(new OrderLineBean());
        WebDocumentBean webBean = OrderReferenceManager.getWebOrderBean(ctx,customerReturnOrder);
        ArrayList list = POSManager.populateOrderLines(ctx,customerReturnOrder);
        WebDocumentBean documentBean=POSManager.calculateOrderTotals(list);
        
        if(customerReturnOrder.getPaymentRule().equalsIgnoreCase(MOrder.PAYMENTRULE_OnCredit))
        {
            ElementBean title=ElementManager.getMsg(ctx,"credit.memo");
            request.getSession().setAttribute(Constants.ORDER_TITLE,title.getName());
        }
            
        else
        {
            ElementBean title=ElementManager.getMsg(ctx,"customer.returned.order");
            request.getSession().setAttribute(Constants.ORDER_TITLE,title.getName());
        }
          
        
        request.getSession().setAttribute(Constants.CUSTOMER_RETURN_ORDER_ID,customerReturnOrder.get_ID());
        request.setAttribute(Constants.CUSTOMER_RETURN_ORDER_LINES,list);
        SessionStorage.putCompleteOrderInfo(ctx,request,webBean,documentBean);
        
        return mapping.findForward(CREATE_CUSTOMER_RETURN_ORDER);
    }
    
    public static final String COMPLETE_CUSTOMER_RETURN_ORDER="completeCustomerReturnOrder";
    public ActionForward completeCustomerReturnOrder(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx=TmkJSPEnv.getCtx(request);
      
        
        Integer customerReturnOrderId=(Integer)request.getSession().getAttribute(Constants.CUSTOMER_RETURN_ORDER_ID);
        MOrder completedCustomerReturn=null;
        
        Trx trx = Trx.get(TrxPrefix.getPrefix(),true);
        
        if(customerReturnOrderId==null)
        {
           postGlobalError("error.no.order.exists",request);
           return mapping.getInputForward();
        }
       
       try
       {
    	  trx.start();
          completedCustomerReturn=POSGoodsManager.completeCustomerReturnOrder(ctx,customerReturnOrderId.intValue(),trx.getTrxName());
          trx.commit();
       }
       catch(NoOrderLineFoundException e)
       {
           trx.rollback();
           postGlobalError("error.pos.no.orderline",request);             
           request.getSession().removeAttribute(Constants.CUSTOMER_RETURN_ORDER_SHOPPING_CART);
           request.getSession().removeAttribute(Constants.CUSTOMER_RETURN_ORDER_SHOPPING_CART_ITEMS);
           return mapping.getInputForward();
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
    	   throw new OperationException("Could not complete customer return order!! Reason : " + e);
       }
       finally
       {
           trx.close();
       }
        
        
        WebDocumentBean webBean = OrderReferenceManager.getWebOrderBean(ctx,completedCustomerReturn);
        ArrayList list = POSManager.populateOrderLines(ctx,completedCustomerReturn);
        WebDocumentBean documentBean=POSManager.calculateOrderTotals(list);
        
        //request.getSession().setAttribute(Constants.CUSTOMER_RETURN_ORDER_ID,completedCustomerReturn.get_ID());
        request.setAttribute(Constants.CUSTOMER_RETURN_ORDER_LINES,list);
        /*request.removeAttribute(Constants.POS_ORDER_LINES);*/
        
      
        SessionStorage.putCompleteOrderInfo(ctx,request,webBean,documentBean);
        
        if(completedCustomerReturn.getPaymentRule().equalsIgnoreCase(MOrder.PAYMENTRULE_OnCredit))
        {
            ElementBean title=ElementManager.getMsg(ctx,"credit.memo");
            request.getSession().setAttribute(Constants.ORDER_TITLE,title.getName());
        }
            
        else
        {
            ElementBean title=ElementManager.getMsg(ctx,"customer.returned.order");
            request.getSession().setAttribute(Constants.ORDER_TITLE,title.getName());
        }
          
        
        request.getSession().removeAttribute(Constants.DESCRIPTION);
        request.removeAttribute(Constants.DESCRIPTION);
        request.getSession().removeAttribute(Constants.ALL_CUSTOMERS);
        request.getSession().removeAttribute(Constants.CUSTOMER_RETURN_ORDER_SHOPPING_CART);
        request.getSession().removeAttribute(Constants.CUSTOMER_RETURN_ORDER_SHOPPING_CART_ITEMS);
        request.getSession().removeAttribute(Constants.DESCRIPTION);
        request.getSession().removeAttribute(Constants.CUSTOMER_RETURN_ORDER_ID);
        request.getSession().removeAttribute(Constants.B_PARTNER_ID);
        
        request.getSession().removeAttribute(Constants.ORDER_TYPE);
        request.getSession().removeAttribute(Constants.POS_ORDER_DOC_NUMBER);
        request.getSession().removeAttribute(Constants.CREDIT_PAYMENT_TERM_ID);
        
        return mapping.findForward(COMPLETE_CUSTOMER_RETURN_ORDER);
    }
    
    public static final String COMPLETE_POS_ORDER="completePOSOrder";
    public static final String VIEW_POS_ORDERS="viewPOSOrders";
    public static final String COMPLETED_CREDIT_ORDER="completedCreditOrder";
   
    
    public ActionForward viewPOSOrders(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx=TmkJSPEnv.getCtx(request);
        DefaultForm df= (DefaultForm) form;
        OrderLineBean bean=(OrderLineBean) df.getBean();
        
        MOrder order= new MOrder(ctx,bean.getOrderId(),null);
        String status = order.getDocStatus();
        
        WebDocumentBean webBean = OrderReferenceManager.getWebOrderBean(ctx,order);
        
        ArrayList list = POSManager.populateOrderLines(ctx,order);
        WebDocumentBean documentBean=POSManager.calculateOrderTotals(list);
        ActionForward forward=null;
        
        if(order.getOrderType().equals(UDIOrderTypes.POS_GOODS_RECEIVE_NOTE.getOrderType())) 
        {
            if( status.equals(DocAction.STATUS_Drafted) )
            {
                int c_BPartner_Id = order.getC_BPartner_ID();
                request.getSession().setAttribute(Constants.BPARTNER, Integer.valueOf(c_BPartner_Id));              
                request.getSession().setAttribute( Constants.GOODS_RECEIVE_NOTE_LINES, list );               
                request.getSession().setAttribute( Constants.GOODS_RECEIVE_NOTE_ID , Integer.valueOf(order.get_ID()) );
                forward =  new ActionForward("/DraftedGoodsReceiveNote.do");
            }
            else
            {
                request.getSession().setAttribute(Constants.GOODS_RECEIVE_NOTE,order);
                request.setAttribute(Constants.GOODS_RECEIVE_NOTE_LINES,list);
                forward= mapping.findForward(COMPLETE_GOODS_RECEIVE_NOTE);
            }
        }
        else  if(order.getOrderType().equals(UDIOrderTypes.POS_GOODS_RETURN_NOTE.getOrderType())) 
        {
            if( status.equals(DocAction.STATUS_Drafted) )
            {
               
                int c_BPartner_Id = order.getC_BPartner_ID();
                request.getSession().setAttribute(Constants.BPARTNER, Integer.valueOf(c_BPartner_Id));              
                request.getSession().setAttribute( Constants.GOODS_RETURN_NOTE_LINES, list );               
                request.getSession().setAttribute( Constants.GOODS_RETURN_NOTE_ID, Integer.valueOf(order.get_ID()) );
                
                forward =  new ActionForward("/DraftedGoodsReturnNote.do");
            }
            else
            {
                request.getSession().setAttribute(Constants.GOODS_RETURN_NOTE,order);
                request.setAttribute(Constants.GOODS_RETURN_NOTE_LINES,list);
                forward= mapping.findForward(COMPLETE_GOODS_RETURN_NOTE);
            }
            
        }   
        else  if(order.getOrderType().equals(UDIOrderTypes.CUSTOMER_RETURN_ORDER.getOrderType())|| order.getOrderType().equals(UDIOrderTypes.CREDIT_MEMO.getOrderType())) 
        {
            if(order.getPaymentRule().equalsIgnoreCase(MOrder.PAYMENTRULE_OnCredit))
            {
                ElementBean title=ElementManager.getMsg(ctx,"credit.memo");
                request.getSession().setAttribute(Constants.ORDER_TITLE,title.getName());
            }
                
            else
            {
                ElementBean title=ElementManager.getMsg(ctx,"customer.returned.order");
                request.getSession().setAttribute(Constants.ORDER_TITLE,title.getName());
            }
             if( status.equals(DocAction.STATUS_Drafted) )
            {
                
                int c_BPartner_Id = order.getC_BPartner_ID();
                request.getSession().setAttribute(Constants.BPARTNER, Integer.valueOf(c_BPartner_Id));              
                request.getSession().setAttribute( Constants.CUSTOMER_RETURN_ORDER_LINES, list );               
                request.getSession().setAttribute( Constants.CUSTOMER_RETURN_ORDER_ID, Integer.valueOf(order.get_ID()) );
                
               
                forward =  new ActionForward("/DraftedCustomerReturnOrder.do");
            }
            else
            {
                
                request.getSession().setAttribute(Constants.CUSTOMER_RETURN_ORDER,order);
                request.setAttribute(Constants.CUSTOMER_RETURN_ORDER_LINES,list);
                forward= mapping.findForward(COMPLETE_CUSTOMER_RETURN_ORDER);
            }
        }   
        else  if(order.getOrderType().equals(UDIOrderTypes.WEBSTORE_ORDER.getOrderType())) 
        {
           
            request.getSession().setAttribute(Constants.CURRENT_POS_ORDER,order);
            request.setAttribute(Constants.POS_ORDER_LINES,list);
            request.setAttribute(Constants.ORDER_NAME,"Webstore Order");
           
            forward= mapping.findForward(COMPLETE_POS_ORDER);
        }   
        
        else if(order.getOrderType().equals(UDIOrderTypes.POS_ORDER.getOrderType()))
        {
        	if( status.equals(DocAction.STATUS_Drafted) )
        	{
        		int c_BPartner_Id = order.getC_BPartner_ID();
        		
        		request.getSession().setAttribute(Constants.BPARTNER, Integer.valueOf(c_BPartner_Id));        		
        		request.getSession().setAttribute( Constants.POS_ORDER_LINES, list );        		
        		request.getSession().setAttribute( Constants.CURRENT_POS_ORDER_ID , Integer.valueOf(order.get_ID()) );
        		forward =  new ActionForward("/DraftedPOSOrder.do");
        	}
            else if( status.equals(DocAction.STATUS_InProgress) )
            {
                int c_BPartner_Id = order.getC_BPartner_ID();
                
                request.getSession().setAttribute(Constants.BPARTNER, Integer.valueOf(c_BPartner_Id));              
                request.getSession().setAttribute( Constants.POS_ORDER_LINES, list ); 
                request.setAttribute(Constants.ORDER_NAME,"POS Order");
                request.getSession().setAttribute( Constants.CURRENT_POS_ORDER_ID , Integer.valueOf(order.get_ID()) );
                forward= mapping.findForward(COMPLETE_POS_ORDER);
            }
        	else
        	{
        		if( status.equals(DocAction.STATUS_Completed) )
        		{ //setting card & check tendered amount
	                BigDecimal cardTendered = POSManager.getPayment(ctx,order.get_ID(),MPayment.TENDERTYPE_CreditCard,null);
	                BigDecimal chequeTendered = POSManager.getPayment(ctx,order.get_ID(),MPayment.TENDERTYPE_Check,null);
	                
	                request.setAttribute(Constants.CARD_AMT_TENDERED, cardTendered);
	                request.setAttribute(Constants.CHEQUE_AMT_TENDERED, chequeTendered);
        		} 
                
        		request.getSession().setAttribute(Constants.CURRENT_POS_ORDER,order);
                request.setAttribute(Constants.POS_ORDER_LINES,list);
                request.setAttribute(Constants.ORDER_NAME,"POS Order");
                forward= mapping.findForward(COMPLETE_POS_ORDER);
        	}
        }
        else if(order.getOrderType().equals(UDIOrderTypes.CREDIT_ORDER.getOrderType()))
        {
            if( status.equals(DocAction.STATUS_Drafted) )
            {
                int c_BPartner_Id = order.getC_BPartner_ID();
                request.getSession().setAttribute(Constants.BPARTNER, Integer.valueOf(c_BPartner_Id));              
                request.getSession().setAttribute( Constants.POS_ORDER_LINES, list );               
                request.getSession().setAttribute( Constants.CURRENT_POS_ORDER_ID , Integer.valueOf(order.get_ID()) );
                
                forward =  new ActionForward("/DraftedCreditOrder.do");
            }
            else
            {
                request.getSession().setAttribute(Constants.CURRENT_POS_ORDER,order);
                request.setAttribute(Constants.POS_ORDER_LINES,list);
                forward= mapping.findForward(COMPLETED_CREDIT_ORDER);
            }
        }
        else
        {
        	String msg = "Unknown orderType : " + order.getOrderType();
            throw new OperationException(msg);
        }
        if(order.getPaymentRule().equals(UdiConstants.PAYMENTRULE_MIXED))
        {
            POSManager.getAmountForMixedPayment(ctx,order,webBean);
        }
        SessionStorage.putCompleteOrderInfo(ctx,request,webBean,documentBean);
        df.populate(new OrderLineBean());
        return forward;
        
    }
    
    public static final String VIEW_COMPLETED_POS_ORDER = "viewCompletedPOSOrder";
    public ActionForward viewCompletedPOSOrder(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
    	 ActionForward fwd= init(mapping,form,request,response);
         if(fwd!=null)
             return fwd;
         
         Properties ctx=TmkJSPEnv.getCtx(request);
         DefaultForm df= (DefaultForm) form;
         OrderLineBean bean=(OrderLineBean) df.getBean();
         
         MOrder order= new MOrder(ctx,bean.getOrderId(),null);        
         WebDocumentBean webBean = OrderReferenceManager.getWebOrderBean(ctx,order);     
         
         
         
         ArrayList list = POSManager.populateOrderLines(ctx,order);
         WebDocumentBean documentBean=POSManager.calculateOrderTotals(list);
    	
         request.getSession().setAttribute(Constants.CURRENT_POS_ORDER,order);
         request.setAttribute(Constants.POS_ORDER_LINES,list);         
         SessionStorage.putCompleteOrderInfo(ctx,request,webBean,documentBean);
         
         return mapping.findForward(VIEW_COMPLETED_POS_ORDER);
    }
    
    public static final String GET_TAX_CATEGORY="getTaxCategory";
    public ActionForward getTaxCategory(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd=init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        Properties ctx=TmkJSPEnv.getCtx(request);
        //DefaultForm df = (DefaultForm)form;
        ArrayList taxList=POSGoodsManager.getAllTaxCategory(ctx);
        
        request.getSession().setAttribute(Constants.TAX_CATEGORY_ID,taxList); 
        return mapping.findForward(GET_TAX_CATEGORY);
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
    
    public ActionForward getShoppingCart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws OperationException
    {
    	DefaultForm df = (DefaultForm) form;
    	String orderType = df.getOrderType();
    	
    	if(orderType == null)
    	{
    		throw new OperationException("Cannot lookup for shopping cart! Cause orderType is null.");
    	}
    	
    	HashMap<String,String> map = new HashMap<String,String>();
    	
    	map.put(Constants.GOODS_RECEIVE_NOTE,   "/jsp/pos/goodsReceiveShoppingCart.jsp");
    	map.put(Constants.GOODS_RETURN_NOTE,    "/jsp/pos/goodsReturnShoppingCart.jsp");
    	map.put(Constants.CUSTOMER_RETURN_ORDER,"/jsp/pos/customerReturnShoppingCart.jsp");
    	
    	String url = map.get(orderType);
    	if(url == null)
    	{
    		throw new OperationException("Cannot lookup for shopping cart! Cause no shopping cart is define for orderType:" + orderType);
    	}
    	
    	return new ActionForward(url);
    }
    
    
    public static final String CREATE_CUSTOMER_RETURN_FROM_POSORDER="createCustomerReturnFromPOSOrder";
    public ActionForward createCustomerReturnFromPOSOrder(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd=init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        Properties ctx=TmkJSPEnv.getCtx(request);
    
        DefaultForm df= (DefaultForm) form;
        OrderLineBean bean=(OrderLineBean) df.getBean();
        ShoppingCartBean cart =null;
        OrderBean orderBean;
        try
        {
            orderBean = POSManager.getPOSOrderDetail(ctx,bean.getDocumentNo());
            
            if(orderBean==null)
            {
                postGlobalError("error.orderid.notpresent",request);
                return mapping.getInputForward();           
            }  
            
            if(!orderBean.getOrderType().equalsIgnoreCase(UDIOrderTypes.POS_ORDER.getOrderType()))
            {
                postGlobalError("error.only.pos.order.supported",request);
                return mapping.getInputForward();           
            }
            
            if(orderBean.getDocStatus().equalsIgnoreCase(DocumentEngine.STATUS_Drafted))
            {
                postGlobalError("error.pos.order.not.completed",request);
                return mapping.getInputForward();           
            }
            
            cart = POSManager.getShoppingCartForOrder(ctx,orderBean.getOrderId(),null);
        }
        catch(InvalidOrderIDException e)
        {
            postGlobalError("error.orderid.notpresent",request);
            return mapping.getInputForward();
        }
        
        if(orderBean.getOrderType().equalsIgnoreCase(UDIOrderTypes.CREDIT_ORDER.getOrderType()))
        {
            ElementBean title=ElementManager.getMsg(ctx,"credit.memo");
            request.getSession().setAttribute(Constants.ORDER_TITLE,title.getName());
        }
            
        else
        {
            ElementBean title=ElementManager.getMsg(ctx,"customer.returned.order");
            request.getSession().setAttribute(Constants.ORDER_TITLE,title.getName());
        }
        
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
        request.getSession().setAttribute(Constants.CUSTOMER_RETURN_ORDER_SHOPPING_CART,cart);
        request.getSession().setAttribute(Constants.CUSTOMER_RETURN_ORDER_SHOPPING_CART_ITEMS,cart.getItems());
        return mapping.findForward(CREATE_CUSTOMER_RETURN_FROM_POSORDER);
    }
    
    
    //--------------------------------------------------credit order------------------------------------------------------------
    public static final String LOAD_CREDIT_ORDER="loadCreditOrder";
    public ActionForward loadCreditOrder(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd=init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        Properties ctx=TmkJSPEnv.getCtx(request);
    
        DefaultForm df= (DefaultForm) form;
        OrderLineBean bean=(OrderLineBean) df.getBean();
        ShoppingCartBean cart =null;
        OrderBean orderBean;

        try
        {
            orderBean = POSManager.getPOSOrderDetail(ctx,bean.getDocumentNo()); 
            
            if(orderBean==null)
            {
                postGlobalError("error.orderid.notpresent",request);
                return mapping.getInputForward();           
            }  
            
            if(!orderBean.getOrderType().equalsIgnoreCase(UDIOrderTypes.CREDIT_ORDER.getOrderType()) && !orderBean.getOrderType().equalsIgnoreCase(UDIOrderTypes.CREDIT_ORDER.getOrderType()))
            {
                postGlobalError("error.only.credit.order.supported",request);
                return mapping.getInputForward();           
            }
            
            if(orderBean.getDocStatus().equalsIgnoreCase(DocumentEngine.STATUS_Drafted))
            {
                postGlobalError("error.credit.order.not.completed",request);
                return mapping.getInputForward();           
            }
            
            cart = POSManager.getShoppingCartForOrder(ctx,orderBean.getOrderId(),null);
        }
        catch(InvalidOrderIDException e)
        {
            postGlobalError("error.orderid.notpresent",request);
            return mapping.getInputForward();
        }
        
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
        request.getSession().setAttribute(Constants.CUSTOMER_RETURN_ORDER_SHOPPING_CART,cart);
        request.getSession().setAttribute(Constants.CUSTOMER_RETURN_ORDER_SHOPPING_CART_ITEMS,cart.getItems());
        return mapping.findForward(LOAD_CREDIT_ORDER);
    }
    
    //####################### Deletion of orders #######################//
    //deleteGoodsReturnOrder
    //deleteGoodsReceiveOrder
    //deleteCustomerReturnOrder  
    
    public static final String DELETE_GOODS_RETURN_ORDER = "deleteGoodsReturnOrder";
    public static final String DELETE_GOODS_RECEIVE_ORDER = "deleteGoodsReceiveOrder";
    public static final String DELETE_CUSTOMER_RETURN_ORDER = "deleteCustomerReturnOrder";
    
    public ActionForward deleteGoodsReturnOrder(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;      
        
        Properties ctx=TmkJSPEnv.getCtx(request);      
        
        Integer orderId = (Integer) request.getSession().getAttribute( Constants.GOODS_RETURN_NOTE_ID );
        
        if( orderId == null )
        {
        	throw new OperationException("Cannot delete order. Reason: order ID not found!");
        }
        
        Trx trx = Trx.get(TrxPrefix.getPrefix(),true);       
        
        try
        {
        	trx.start();
        	OrderManager.deleteDraftedOrder(ctx, orderId.intValue(), null);
        	trx.commit();        	
        }
        catch(OperationException ex)
        {
        	trx.rollback();
        	postGlobalError("error.order.delete", request);
        	return mapping.getInputForward();
        }
        finally
        {
        	trx.close();
        }
        
        //remove previously set values
        request.getSession().removeAttribute(Constants.GOODS_RETURN_NOTE_SHOPPING_CART);
        request.getSession().removeAttribute(Constants.GOODS_RETURN_NOTE_SHOPPING_CART_ITEMS);
        request.getSession().removeAttribute(Constants.GOODS_RETURN_NOTE_ID);
        request.getSession().removeAttribute(Constants.B_PARTNER_ID);
        request.getSession().removeAttribute(Constants.DESCRIPTION);
        
        //return mapping.findForward(DELETE_GOODS_RETURN_ORDER); 
        return new ActionForward( "/GetAllPOSVendor.do?action=getAllVendors&isSales=false" );
    }
    
    public ActionForward deleteGoodsReceiveOrder(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;      
        
        Properties ctx=TmkJSPEnv.getCtx(request);      
        
        Integer orderId = (Integer) request.getSession().getAttribute( Constants.GOODS_RECEIVE_NOTE_ID );
        
        if( orderId == null )
        {
        	throw new OperationException("Cannot delete order. Reason: order ID not found!");
        }
        
        Trx trx = Trx.get(TrxPrefix.getPrefix(),true);       
        
        try
        {
        	trx.start();
        	OrderManager.deleteDraftedOrder(ctx, orderId.intValue(), null);
        	trx.commit();        	
        }
        catch(OperationException ex)
        {
        	trx.rollback();
        	postGlobalError("error.order.delete", request);
        	return mapping.getInputForward();
        }
        finally
        {
        	trx.close();
        }
        
        //remove previously set values
        request.getSession().removeAttribute(Constants.GOODS_RECEIVE_NOTE_SHOPPING_CART);
        request.getSession().removeAttribute(Constants.GOODS_RECEIVE_NOTE_SHOPPING_CART_ITEMS);
        request.getSession().removeAttribute(Constants.GOODS_RECEIVE_NOTE_ID);
        request.getSession().removeAttribute(Constants.B_PARTNER_ID);
        request.getSession().removeAttribute(Constants.DESCRIPTION);
        
        //GetAllPOSVendor.do?action=getAllVendors&isSales=false
        //return mapping.findForward(DELETE_GOODS_RECEIVE_ORDER);
        return new ActionForward( "/GetAllPOSVendor.do?action=getAllVendors&isSales=true" );
    }
    
    public ActionForward deleteCustomerReturnOrder(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;      
        
        Properties ctx=TmkJSPEnv.getCtx(request);      
        
        Integer orderId = (Integer) request.getSession().getAttribute( Constants.CUSTOMER_RETURN_ORDER_ID );
        
        if( orderId == null )
        {
        	throw new OperationException("Cannot delete order. Reason: order ID not found!");
        }
        
        Trx trx = Trx.get(TrxPrefix.getPrefix(),true);       
        
        try
        {
        	trx.start();
        	OrderManager.deleteDraftedOrder(ctx, orderId.intValue(), null);
        	trx.commit();        	
        }
        catch(OperationException ex)
        {
        	trx.rollback();
        	postGlobalError("error.order.delete", request);
        	return mapping.getInputForward();
        }
        finally
        {
        	trx.close();
        }
        
        //remove previously set values
        request.getSession().removeAttribute(Constants.CUSTOMER_RETURN_ORDER_SHOPPING_CART);
        request.getSession().removeAttribute(Constants.CUSTOMER_RETURN_ORDER_SHOPPING_CART_ITEMS);
        request.getSession().removeAttribute(Constants.CUSTOMER_RETURN_ORDER_ID);
        request.getSession().removeAttribute(Constants.B_PARTNER_ID);
        request.getSession().removeAttribute(Constants.DESCRIPTION);
        
        request.getSession().removeAttribute(Constants.ORDER_TYPE);
        request.getSession().removeAttribute(Constants.POS_ORDER_DOC_NUMBER);
        request.getSession().removeAttribute(Constants.CREDIT_PAYMENT_TERM_ID);
        
        return mapping.findForward(DELETE_CUSTOMER_RETURN_ORDER);        
    }
    
}
