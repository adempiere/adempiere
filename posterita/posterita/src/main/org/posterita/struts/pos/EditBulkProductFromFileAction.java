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
 * Created on Sep 28, 2006
 */


package org.posterita.struts.pos;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.compiere.util.Trx;

import org.posterita.Constants;
import org.posterita.beans.ProductBean;
import org.posterita.businesslogic.EditBulkProductFromFileManager;
import org.posterita.businesslogic.POSProductManager;
import org.posterita.businesslogic.ProductCart;
import org.posterita.businesslogic.performanceanalysis.ReportManager;
import org.posterita.core.TmkJSPEnv;
import org.posterita.core.TrxPrefix;
import org.posterita.exceptions.ApplicationException;
import org.posterita.exceptions.BarcodeAlreadyExistsException;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.ProductAlreadyExistException;
import org.posterita.exceptions.ProductNotFoundException;
import org.posterita.exceptions.TaxRateNotDefineException;
import org.posterita.struts.core.DefaultForm;


public class EditBulkProductFromFileAction extends POSDispatchAction
{
    
    /*public static final String VIEW_ALL_POS_PRODUCTS_FOR_UPDATE="viewAllPOSProductsForUpdate";
    public ActionForward viewAllPOSProductsForUpdate(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd=init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        Properties ctx=TmkJSPEnv.getCtx(request);
        DefaultForm df = (DefaultForm)form;
        ProductBean bean = (ProductBean) df.getBean();
        ArrayList list=null;
        
        try
        {
            list = new ArrayList();
            list = POSProductManager.viewAllProducts(ctx,bean.getProductName(),null);
            
        }
        catch(ProductNotFoundException e1)
        {
            postGlobalError("error.product.not.defined.client",request);
            
        }
        
        request.setAttribute(Constants.POS_PRODUCTS_FOR_UPDATE,list); 
        return mapping.findForward(VIEW_ALL_POS_PRODUCTS_FOR_UPDATE);
    }
       
    public static final String CREATE_CSV_FILE="createCSVFile";
    public ActionForward createCSVFile(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx=TmkJSPEnv.getCtx(request);

        ProductCart cart = (ProductCart) request.getSession().getAttribute(Constants.PRODUCT_CART);
        
        if(cart==null)
        {           
            postGlobalError("error.pos.shoppingcart.empty",request);             
            return new ActionForward("/jsp/pos/viewAllPOSProductsForUpdate.jsp");
        }
        String fileName= EditBulkProductFromFileManager.createCSVFile(ctx, cart.getProductIDs());
        String fileURI = ReportManager.getReportURI(fileName,request); 
        
        request.getSession().setAttribute(Constants.FILE_FOR_UPDATE,fileURI);
        return mapping.findForward(CREATE_CSV_FILE);
    }
    
    public static final String UPDATE_ALL_PRODUCTS="updateAllProducts";
    public ActionForward updateAllProducts(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd = init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        request.getSession().removeAttribute(Constants.PRODUCT_CREATED); 
        DefaultForm df = (DefaultForm) form;
        
        Trx trx = Trx.get(TrxPrefix.getPrefix(),true);
        
        try
        {
            trx.start();
            EditBulkProductFromFileManager.updateAllProducts(ctx,df.getFile(),trx.getTrxName());
            trx.commit();
        }
        catch(ProductAlreadyExistException e)
        {
            trx.rollback();
            postGlobalError("error.duplicate.product",e.getMessage(),request);
            return mapping.getInputForward();
            
        }
        catch(BarcodeAlreadyExistsException e)
        {
            trx.rollback();
            postGlobalError("error.duplicate.barcode",e.getMessage(),request);
            return mapping.getInputForward();
        }
        catch(NumberFormatException e)
        {
            trx.rollback();
            postGlobalError("error.invalid.number",request);
            return mapping.getInputForward();
        }
        catch(TaxRateNotDefineException e)
        {
            trx.rollback();
            postGlobalError("tax.rate.not.defined",e.getMessage(),request);
            return mapping.getInputForward();
        }
        catch(Exception e)
        {
            trx.rollback();
            postGlobalError("error.in.file",request);
            return mapping.getInputForward();
        }
        finally
        {
            trx.close();
        }        
      
        return mapping.findForward(UPDATE_ALL_PRODUCTS);
        
    }
    
    public static final String VIEW_CART = "viewCart";
    public ActionForward viewCart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException, IOException
    {
    	 ActionForward fwd = init(mapping,form,request,response);
         if(fwd!=null)
             return fwd;
         
        Properties ctx = TmkJSPEnv.getCtx(request);
        
        ProductCart cart = (ProductCart) request.getSession().getAttribute(Constants.PRODUCT_CART);
        
        if( cart == null )
        {
        	throw new OperationException( "Cart is empty" );        	
        }
        
        ArrayList productList = EditBulkProductFromFileManager.getProductList( ctx, cart.getProductIDs() );
        request.getSession().setAttribute( Constants.PRODUCT_CART_ITEMS, productList );
        
        return mapping.findForward(VIEW_CART);
    }
    
    public static final String REMOVE = "remove";
    public ActionForward remove(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException, IOException
    {
    	 ActionForward fwd = init(mapping,form,request,response);
         if(fwd!=null)
             return fwd;
         
        Properties ctx = TmkJSPEnv.getCtx(request);
        DefaultForm df = (DefaultForm) form;
        ProductBean bean = (ProductBean) df.getBean();
        
        Integer productId = bean.getProductId();
        
        ProductCart cart = (ProductCart) request.getSession().getAttribute(Constants.PRODUCT_CART);
        
        if( cart == null )
        {
        	throw new OperationException( "Cart is empty" );        	
        }
        
        cart.removeProduct( productId.intValue() );
        
        ArrayList productList = EditBulkProductFromFileManager.getProductList( ctx, cart.getProductIDs() );
        request.getSession().setAttribute( Constants.PRODUCT_CART_ITEMS, productList );
        request.getSession().setAttribute( Constants.PRODUCT_CART, cart);
        
        return mapping.findForward(REMOVE);
    }
    
    
    //------------------------------------------------------------------------------------------------------------------------------------------------
    public static final String ADD_TO_CART = "addToCart";
    public ActionForward addToCart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException, IOException
    {
        Properties ctx = TmkJSPEnv.getCtx(request);
        DefaultForm df = (DefaultForm) form;
        ProductBean bean = (ProductBean) df.getBean();
        
        Integer productId = bean.getProductId();
      
        ProductCart cart = (ProductCart) request.getSession().getAttribute(Constants.PRODUCT_CART);
        
        if( cart == null )
        {
        	cart = new ProductCart( ctx );
        }
        
        cart = EditBulkProductFromFileManager.addToCart( ctx, cart, productId.intValue() );        
        request.getSession().setAttribute(Constants.PRODUCT_CART, cart); 
        
        String script = "setCartCounter("+ cart.getNoOfProducts() +");productAdded("+ productId +")";
        
        PrintWriter writer = response.getWriter();        
        writer.print( script );
        writer.flush();
        writer.close();
        
        return null;
    }
    
    public ActionForward clearCart (ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException, IOException
    {      
        HttpSession session = request.getSession();           
        session.removeAttribute(Constants.PRODUCT_CART);        
        
        String script = "clearAll();";        
        PrintWriter writer = response.getWriter();        
        writer.print( script );
        writer.flush();
        writer.close();
    	
    	return null;
    }    
    */
}

