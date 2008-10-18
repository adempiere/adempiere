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
 */
package org.posterita.struts.stock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.posterita.Constants;
import org.posterita.beans.ItemBean;
import org.posterita.beans.ShoppingCartBean;
import org.posterita.businesslogic.stock.StockManager;
import org.posterita.core.TmkJSPEnv;
import org.posterita.exceptions.ApplicationException;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.ProductNotOnPriceListException;
import org.posterita.struts.core.BaseDispatchAction;
import org.posterita.struts.core.DefaultForm;
import org.posterita.struts.pos.POSGoodsAction;


public class StockAction extends BaseDispatchAction
{
  
    
    
    public static final String DELETE_FROM_CART = "deleteFromCart";
    public ActionForward deleteFromCart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException
    {
    	ActionForward fwd = init(mapping,form,request,response);
		if(fwd!=null)
			return fwd;
        
        DefaultForm df = (DefaultForm) form;
        
        ItemBean bean = (ItemBean) df.getBean();
        
        ArrayList cartItems = (ArrayList) request.getSession().getAttribute(Constants.SHOPPING_CART_ITEMS);
        ShoppingCartBean cartBeans = (ShoppingCartBean) request.getSession().getAttribute(Constants.SHOPPING_CART);
        
        if (cartItems == null || cartItems.size() == 0)
        	 return mapping.findForward(DELETE_FROM_CART);
               
        cartItems = StockManager.deleteItemFromList(cartItems, bean.getProductId());
        BigDecimal grandTotal = StockManager.setGrandTotal(cartItems);
        cartBeans.setTotalPrice(grandTotal);
        
        request.getSession().setAttribute(Constants.SHOPPING_CART_ITEMS, cartItems);
        request.getSession().setAttribute(Constants.SHOPPING_CART, cartBeans);
        
        return mapping.findForward(DELETE_FROM_CART);
    }
    
    public static final String DELETE_FROM_POS_CART = "deleteFromPOSCart";
    public ActionForward deleteFromPOSCart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException
    {
    	
    	ActionForward fwd = init(mapping,form,request,response);
		if(fwd!=null)
			return fwd;
		
        Properties ctx = TmkJSPEnv.getCtx(request);
        
        DefaultForm df = (DefaultForm) form;
        
        String orderType = df.getOrderType();
        ItemBean bean = (ItemBean) df.getBean();
        
        if(orderType==null)
        {
            orderType=(String)request.getSession().getAttribute(Constants.ORDER_TYPE);
        }

        String shoppingCartItem = null;
        String path=null;
        if(orderType.equalsIgnoreCase(Constants.CREDIT_ORDER))
        {
            shoppingCartItem = Constants.CREDIT_ORDER_SHOPPING_CART_ITEMS;
            path="/jsp/pos/creditOrderShoppingCart.jsp";
        }    
        else
        {
            shoppingCartItem = Constants.SHOPPING_ORDER_CART_ITEMS;
            path="/jsp/pos/posShoppingCartFirstPage.jsp";
            
        }    
        ArrayList items = (ArrayList) request.getSession().getAttribute(shoppingCartItem);
        ArrayList itemsInCart = StockManager.deleteItemFromPOSList(ctx,items, bean.getProductId(),true);
        request.getSession().setAttribute(shoppingCartItem, itemsInCart);
        return new ActionForward(path);
    }
    
    
    
    public static final String DELETE_FROM_CREDIT_ORDER_CART = "deleteFromCreditOrderCart";
    public ActionForward deleteFromCreditOrderCart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException
    {
        
        ActionForward fwd = init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        
        DefaultForm df = (DefaultForm) form;
        
        //String orderType = df.getOrderType();
        ItemBean bean = (ItemBean) df.getBean();
        
        
        ArrayList items = (ArrayList) request.getSession().getAttribute(Constants.CREDIT_ORDER_SHOPPING_CART_ITEMS);
               
        ArrayList itemsInCart = StockManager.deleteItemFromPOSList(ctx,items, bean.getProductId(),true);
        
        
        request.getSession().setAttribute(Constants.CREDIT_ORDER_SHOPPING_CART_ITEMS, itemsInCart);
        
        return new ActionForward("/jsp/pos/creditOrderShoppingCart.jsp");
    }
    
    public static final String DELETE_FROM_POS_GRN_CART = "deleteFromPOSGRNCart";
    public ActionForward deleteFromPOSGRNCart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException
    {
    	ActionForward fwd = init(mapping,form,request,response);
		if(fwd!=null)
			return fwd;
		
    	Properties ctx = TmkJSPEnv.getCtx(request);
        DefaultForm df = (DefaultForm) form;
        ItemBean bean = (ItemBean) df.getBean();
        
        String orderType = df.getOrderType();
        //String shoppingCart = null;
        String shoppingCartItem = null;
        
        if(orderType.equals(Constants.GOODS_RECEIVE_NOTE))
        {
        	//shoppingCart     = Constants.GOODS_RECEIVE_NOTE_SHOPPING_CART;
        	shoppingCartItem = Constants.GOODS_RECEIVE_NOTE_SHOPPING_CART_ITEMS;
        }
        else if(orderType.equals(Constants.GOODS_RETURN_NOTE))
        {
        	//shoppingCart     = Constants.GOODS_RETURN_NOTE_SHOPPING_CART;
        	shoppingCartItem = Constants.GOODS_RETURN_NOTE_SHOPPING_CART_ITEMS;
        }
        else if(orderType.equals(Constants.CUSTOMER_RETURN_ORDER))
        {
        	//shoppingCart     = Constants.CUSTOMER_RETURN_ORDER_SHOPPING_CART;
        	shoppingCartItem = Constants.CUSTOMER_RETURN_ORDER_SHOPPING_CART_ITEMS;
        }
        
                
        ArrayList items = (ArrayList) request.getSession().getAttribute(shoppingCartItem);
               
        ArrayList itemsInCart = StockManager.deleteItemFromPOSList(ctx,items, bean.getProductId(),false);
        
        request.getSession().setAttribute(shoppingCartItem, itemsInCart);
        
        POSGoodsAction action = new POSGoodsAction();
        ActionForward forward = action.getShoppingCart(mapping,form,request,response);
        
        return forward;
    }
    
    public static final String DELETE_FROM_POS_RET_CART = "deleteFromPOSRetCart";
    public ActionForward deleteFromPOSRetCart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, ProductNotOnPriceListException, OperationException
    {
    	
    	ActionForward fwd = init(mapping,form,request,response);
		if(fwd!=null)
			return fwd;
		
        Properties ctx = TmkJSPEnv.getCtx(request);
        
        DefaultForm df = (DefaultForm) form;
        
        ItemBean bean = (ItemBean) df.getBean();
        
        ArrayList items = (ArrayList) request.getSession().getAttribute(Constants.SHOPPING_GRN_CART_ITEMS);
               
        ArrayList itemsInCart = StockManager.deleteItemFromPOSList(ctx,items, bean.getProductId(),false);
        
        request.getSession().setAttribute(Constants.SHOPPING_GRN_CART_ITEMS, itemsInCart);
        
        return mapping.findForward(DELETE_FROM_POS_RET_CART);
    }
    
    
    public static final String DELETE_FROM_CUST_RET_CART = "deleteFromCustRetCart";
    public ActionForward deleteFromCustRetCart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, ProductNotOnPriceListException, OperationException
    {
    	ActionForward fwd = init(mapping,form,request,response);
		if(fwd!=null)
			return fwd;
		
        Properties ctx = TmkJSPEnv.getCtx(request);
        
        DefaultForm df = (DefaultForm) form;
        
        ItemBean bean = (ItemBean) df.getBean();
        
        ArrayList items = (ArrayList) request.getSession().getAttribute(Constants.SHOPPING_GRN_CART_ITEMS);
               
        ArrayList itemsInCart = StockManager.deleteItemFromPOSList(ctx,items, bean.getProductId(),true);
        
        request.getSession().setAttribute(Constants.SHOPPING_GRN_CART_ITEMS, itemsInCart);
        
        return mapping.findForward(DELETE_FROM_CUST_RET_CART);
    }
    
    
  
 
}
