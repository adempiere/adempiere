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
 * 17-Aug-2006 13:49:29 by praveen
 *
 */

package org.posterita.struts.pos;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.MessageResources;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.posterita.Constants;
import org.posterita.beans.ItemBean;
import org.posterita.beans.OrderLineBean;
import org.posterita.beans.ShoppingCartBean;
import org.posterita.businesslogic.ProductCart;
import org.posterita.businesslogic.ShoppingcartManager;
import org.posterita.businesslogic.administration.BarcodeManager;
import org.posterita.businesslogic.administration.PriceListManager;
import org.posterita.businesslogic.stock.StockManager;
import org.posterita.core.TmkJSPEnv;
import org.posterita.core.TrxPrefix;
import org.posterita.exceptions.ApplicationException;
import org.posterita.exceptions.InputQuantityLessThanZeroException;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.ProductNotFoundException;
import org.posterita.exceptions.ProductNotOnPriceListException;
import org.posterita.exceptions.QuantityNotAvailableException;
import org.posterita.exceptions.UOMValuePrecisionNotValidException;
import org.posterita.form.OrderLineForm;
import org.posterita.lib.UdiConstants;
import org.posterita.struts.core.DefaultForm;

import com.lowagie.text.DocumentException;

public class BarcodeAction extends POSDispatchAction
{
	public ActionForward getBarcodeImage(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
	{
		 ActionForward fwd = init(mapping, form, request, response);
	        if (fwd !=null)
	            return fwd;
	     
	    DefaultForm df = (DefaultForm) form;
	    String barcode = df.getBarCode();
	    
	    if(barcode!=null)
	    {    
	    	//send image
	    	BarcodeManager.writeBarcode(barcode,response);
	    }
	    
	    return null;       
	     
	}
	
	//------------------------------------------------------------------------------------------//
	/**
	 * BARCODE IMPLEMENTATION
	 */
	
	public static final String INCREMENT_QTY = "incrementQty";
    public ActionForward incrementQty(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	// Bug fix for null pointer
    	OrderLineForm of = (OrderLineForm) form;
    	if(of.getQuantity() == null)
    	{
    		of.setQuantity("1");
    	}
    	
        addToBarcodeCart(mapping,form,request,response);
        return getBarcodeCart(mapping,form,request,response);
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
    	
        addToBarcodeCart(mapping,form,request,response);
        return getBarcodeCart(mapping,form,request,response);
    }
    
    public ActionForward addProduct(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException, IOException, ParseException
    {
    	// Bug fix for null pointer
    	OrderLineForm of = (OrderLineForm) form;
    	if(of.getQuantity() == null)
    	{
    		of.setQuantity("1");
    	}
    	
        addToBarcodeCart(mapping,form,request,response);
        
        return getBarcodeCart(mapping,form,request,response);
    }
	
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
    	
    	ArrayList items = (ArrayList) request.getSession().getAttribute(Constants.BARCODE_CART_ITEMS);
    	StockManager.updateItemFromPOSList(ctx, priceListId, items, product_id, quantity);
        
        return getBarcodeCart(mapping,form,request,response);
    }
    
    public static final String ADD_TO_BARCODE_CART = "addToBarcodeCart";
    public ActionForward addToBarcodeCart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException
    {
        Properties ctx = TmkJSPEnv.getCtx(request);
        DefaultForm df = (DefaultForm) form;
        OrderLineBean bean = (OrderLineBean) df.getBean();
        
        
        String barcodeCart = Constants.BARCODE_CART;
        String barcodeCartItems = Constants.BARCODE_CART_ITEMS;
        
        
        ShoppingCartBean cartBean = (ShoppingCartBean) request.getSession().getAttribute(barcodeCart);
        
        try
        {
            if(bean.getProductId()==null && bean.getBarCode()==null)
            {
                postGlobalError("error.barcode.required", request);
                return mapping.getInputForward();
            }
            
            BigDecimal qty = bean.getQuantity();
            if(qty != null && qty.doubleValue() < 0.0)
            {
            	throw new InputQuantityLessThanZeroException("");
            }
            
            cartBean = StockManager.addToPOSCart(ctx, bean, cartBean, false,Boolean.parseBoolean(bean.getIfAdd()));            
        }
        catch (InputQuantityLessThanZeroException e)        
        {
            postGlobalError("error.invalid.inputQty", request);
            return mapping.getInputForward();
        }
        catch (ProductNotFoundException e)
        {
            postGlobalError("error.product.not.found", e.getMessage(), request);
            return mapping.getInputForward();
        }
        catch(QuantityNotAvailableException e)
        {
            postGlobalError("error.quantity.notAvailable", e.getMessage(), request);
            return mapping.getInputForward();
        }
        
        catch(ProductNotOnPriceListException e)
        {
            postGlobalError("error.product.price.not.found", e.getMessage(), request);
            return mapping.getInputForward();
        }
        
        catch (UOMValuePrecisionNotValidException e)
        {
        	postGlobalError("error.precision", e.getMessage(), request);
        	return mapping.getInputForward();
        }
        
        
        
        int priceListId = Env.getContextAsInt(ctx, UdiConstants.PRICELIST_CTX_PARAM);    	
        String currSymboleSales = PriceListManager.getCurrency(ctx, priceListId);
        request.setAttribute(Constants.CURRENCY_SYMBOLE,currSymboleSales);        
        
        request.getSession().setAttribute(barcodeCart, cartBean);
        request.getSession().setAttribute(barcodeCartItems, cartBean.getItems());
        
        df.setQtyAndItem("");
        
        return mapping.findForward(ADD_TO_BARCODE_CART);
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
    			writer.write("<script>showErrorMessage('" + errMsg + "', searchElement)</script>");
    		}
    		
    	}    	
    	
    	writer.close();
        
        return null;
    }
    
    
    public static final String COPY_PRODUCT_TO_SHOPPING_CART = "copyProductToShoppingCart";
    public ActionForward copyProductToShoppingCart(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException, IOException, ParseException
    {
    	ActionForward fwd=init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        HttpSession session = request.getSession();  
        ProductCart cart = (ProductCart) session.getAttribute(Constants.PRODUCT_CART);
        String shoppingcartHTML = ShoppingcartManager.copyShoppingCartAsHTML(ctx, cart);
                
        request.getSession().setAttribute(Constants.BARCODE_CART, cart);
        request.getSession().setAttribute(Constants.BARCODE_CART_ITEMS, cart.getProducts());
    	
    	return new ActionForward("/initProductBarcodeCart.do");
    }    
    
    public static final String RELOAD_BARCODE_CART = "reloadBarcodeCart";    
    public ActionForward reloadBarcodeCart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws OperationException, IOException, ParseException, ApplicationException
    {
    	ActionForward fwd=init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
    	
    	Properties ctx = TmkJSPEnv.getCtx(request);
        DefaultForm df = (DefaultForm) form;
        OrderLineBean bean = (OrderLineBean) df.getBean();

        String barcodeCart = Constants.BARCODE_CART;
        String barcodeCartItems = Constants.BARCODE_CART_ITEMS;
        
        ShoppingCartBean cartBean = (ShoppingCartBean) request.getSession().getAttribute(barcodeCart);            
        ArrayList<ItemBean> itemBeans= cartBean.getItems();
        
        String priceListIdAsStr = request.getParameter("priceListId");
        String productIdAsStr = request.getParameter("productId");
        int productId = Integer.parseInt(productIdAsStr);
        int priceListId = Integer.parseInt(priceListIdAsStr);
        
        BigDecimal qty = BigDecimal.ZERO;
        
        for(int i=0; i<itemBeans.size(); i++)
    	{
    		ItemBean itemBean = itemBeans.get(i);    		
    		
    		if(itemBean.getProductId() == productId)
    		{
    			qty = itemBean.getQty();
    		}
    		    		
    	}
        cartBean = StockManager.addToPOSCart(ctx, bean, cartBean,true,Boolean.parseBoolean(bean.getIfAdd()));
        
    	ArrayList items = (ArrayList) request.getSession().getAttribute(barcodeCartItems);
    	StockManager.updateItemFromPOSList(ctx, priceListId, items, productId, qty);
        
        return getBarcodeCart(mapping,form,request,response);
    }
    
    public static final String PRINT_PRODUCT_BARDCODE = "printProductBarcode";
    public ActionForward printProductBarcode(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException, ParseException, DocumentException, IOException
    {
    	ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx=TmkJSPEnv.getCtx(request);   	
        Trx trx = Trx.get(TrxPrefix.getPrefix(), true);
        String isPrintPrices = request.getParameter("isPrintPrices");
        String isPrintProductName = request.getParameter("isPrintProductName");
        String isPrintProductDescription = request.getParameter("isPrintProductDescription");
        
        byte printData[] = null;
        
        String barcodeData = BarcodeManager.printProductBarcode(ctx, request, Boolean.parseBoolean(isPrintProductName), Boolean.parseBoolean(isPrintProductDescription), Boolean.parseBoolean(isPrintPrices), trx.getTrxName());
        
        response.setContentType("application/octet-stream");
        printData = barcodeData.getBytes();
        
        OutputStream os = response.getOutputStream();
        os.write(printData);
        os.flush();
        os.close();
        
        return mapping.findForward(PRINT_PRODUCT_BARDCODE);
    }
    
    public ActionForward clearCart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    { 
        
        BarcodeManager.clearBarcodecart(request);        
        return getBarcodeCart(mapping,form,request,response);   
    }
    
}
