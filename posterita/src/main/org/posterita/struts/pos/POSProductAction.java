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
 * Created on May 22, 2006
 */


package org.posterita.struts.pos;

import java.io.File;
import java.io.FileOutputStream;
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
import org.posterita.beans.ProductDetailsBean;
import org.posterita.beans.ProductSalesInfoBean;
import org.posterita.beans.ProductSalesSummaryBean;
import org.posterita.businesslogic.POSGoodsManager;
import org.posterita.businesslogic.POSProductManager;
import org.posterita.businesslogic.ProductCart;
import org.posterita.businesslogic.ProductManager;
import org.posterita.businesslogic.ReportManager;
import org.posterita.core.RandomStringGenerator;
import org.posterita.core.TmkJSPEnv;
import org.posterita.core.TrxPrefix;
import org.posterita.exceptions.ApplicationException;
import org.posterita.exceptions.BarcodeAlreadyExistsException;
import org.posterita.exceptions.CannotInactivateProductException;
import org.posterita.exceptions.InvalidBarcodeException;
import org.posterita.exceptions.InvalidContentTypeException;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.ProductAlreadyExistException;
import org.posterita.exceptions.ProductNotFoundException;
import org.posterita.form.ProductForm;
import org.posterita.struts.core.DefaultForm;


public class POSProductAction extends POSDispatchAction
{
    
    public static final String CREATE_POS_PRODUCT="createPOSProduct";
    public ActionForward createPOSProduct(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd=init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        Properties ctx=TmkJSPEnv.getCtx(request);
        DefaultForm df = (DefaultForm)form;
        ProductBean bean = (ProductBean)df.getBean();
        
        //setting the image
        bean.setFile(df.getFile());
        
        ArrayList list=null;
        request.getSession().removeAttribute(Constants.PRODUCT_CREATED);
        boolean error=false;
       
       
       
           if(bean.getRevenueRecognition()==null)
           {
               postGlobalError("error.required.revenue.recognition",request);             
               error = true;
           
           }
         
           if(error)
           {
               return mapping.getInputForward();
           }
        
            
           Trx trx = Trx.get(TrxPrefix.getPrefix(),true);
        
        try
        {
        	trx.start();
            list=new ArrayList();
            list=POSProductManager.createSinglePOSProduct(ctx,bean,trx.getTrxName());
            trx.commit();
        }
        catch(ProductAlreadyExistException e1)
        {
            postGlobalError("error.product.already.exists",request);
            trx.rollback();
            return mapping.getInputForward();
        }
        catch(BarcodeAlreadyExistsException e2)
        {
            postGlobalError("error.barcode.already.exists",request);
            trx.rollback();
            return mapping.getInputForward();
        }
        catch(NumberFormatException e3)
        {
            postGlobalError("error.numberformatexception.price",request);
            trx.rollback();
            trx.close();
            return mapping.getInputForward();
            
        }
        finally
        {
            trx.close();
        }
       
        
        request.getSession().setAttribute(Constants.PRODUCT_CREATED,list); 
        return mapping.findForward(CREATE_POS_PRODUCT);
    }
    
    
    public static final String VIEW_PRODUCT_FOR_UPDATE="viewProductForUpdate";
    public ActionForward viewProductForUpdate(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd=init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        Properties ctx=TmkJSPEnv.getCtx(request);
        ProductForm pf = (ProductForm)form;
        
        pf.validate(mapping,request);
        
        ProductBean bean = (ProductBean)pf.getBean();        
        bean = POSProductManager.viewPOSProduct(ctx,bean.getProductId().intValue());
        pf.populate(bean);
        
        //ArrayList list = POSGoodsManager.getAllProductCategory(ctx);
        //ArrayList supList=POSGoodsManager.getAllSuppliers(ctx);
        ArrayList taxList = POSGoodsManager.getAllTaxCategory(ctx);   
        
        
       // request.getSession().setAttribute(Constants.BP_LIST,supList);
        request.getSession().setAttribute(Constants.EXISTING_BAR_CODE,bean.getBarCode());
        request.getSession().setAttribute(Constants.TAX_CATEGORY_ID,taxList);
        //request.getSession().setAttribute(Constants.PRODUCT_CATEGORY_ID,list);
        //request.getSession().setAttribute(Constants.PRODUCT_DETAILS,bean);
        return mapping.findForward(VIEW_PRODUCT_FOR_UPDATE);
    }
    
    
    public static final String UPDATE_PRODUCT_DETAILS="updateProductDetails";
    public ActionForward updateProductDetails(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd=init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        Properties ctx=TmkJSPEnv.getCtx(request);
        DefaultForm df = (DefaultForm)form;
        ProductBean bean = (ProductBean)df.getBean();
        bean.setFile(df.getFile());
        String existingBarCode = (String)request.getSession().getAttribute(Constants.EXISTING_BAR_CODE);
        
        Trx trx = Trx.get(TrxPrefix.getPrefix(),true);
        
        try
        {
        	trx.start();
            POSProductManager.editProduct(ctx,bean,existingBarCode,trx.getTrxName());
            trx.commit();
        }
        catch(BarcodeAlreadyExistsException e1)
        {
            
            trx.rollback();
            postGlobalError("error.barcode.already.exists",request);
            return mapping.getInputForward();
        }
        catch(CannotInactivateProductException e2)
        {
            trx.rollback();
            postGlobalError("error.product.cannot.inactivate", e2.getMessage(), request);
            return mapping.getInputForward();
           
        }
        catch(InvalidContentTypeException e3)
        {
        	trx.rollback();
            postGlobalError("error.invalid.content", e3.getMessage(), request);
            return mapping.getInputForward();
        }
        finally
        {
            trx.close();
        }
        ProductBean productBean= POSProductManager.viewPOSProduct(ctx,bean.getProductId().intValue());
        request.getSession().setAttribute(Constants.PRODUCT_DETAILS,productBean);
        return mapping.findForward(UPDATE_PRODUCT_DETAILS);
    }
    
    
    
    public static final String CREATE_POS_GARMENT_PRODUCT="createPOSGarmentProduct";
    public ActionForward createPOSGarmentProduct(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd=init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        Properties ctx=TmkJSPEnv.getCtx(request);
        DefaultForm df = (DefaultForm)form;
        ProductBean bean = (ProductBean)df.getBean();
        
        ArrayList list=new ArrayList();
        request.getSession().removeAttribute(Constants.PRODUCT_CREATED); 
        
    	String customSize = bean.getCustomSize();
    	ArrayList<String> sizeList = new ArrayList<String>();
    	if(bean.getSizes() != null && bean.getSizes().length > 0)
    	{
        	String[] sizes = bean.getSizes();
    		for(int i = 0; i < sizes.length; i++)
    			sizeList.add(sizes[i]);
    	}
    	if(customSize != null && customSize.trim().length() > 0)
    	{
    		customSize = customSize.trim();
    		sizeList.add(customSize);
    	}
    	
    	if(sizeList.size() == 0)
    	{
    		postGlobalError("error.required.sizes",request);             
            return mapping.getInputForward();
    	}
    	else
    	{
    		String nSizes[] = new String[sizeList.size()];
    		sizeList.toArray(nSizes);
    		bean.setSizes(nSizes);
    	}
        
        Trx trx = Trx.get(TrxPrefix.getPrefix(),true);
        
        try
        {
        	trx.start();
            list=POSProductManager.createGarmentProduct(ctx,bean,0,false,null);
            trx.commit();
        }
        catch(ProductAlreadyExistException e1)
        {
            trx.rollback();
            postGlobalError("error.product.already.exists",request);
            return mapping.getInputForward();
        }
        catch(BarcodeAlreadyExistsException e2)
        {
            trx.rollback();
            postGlobalError("error.barcode.already.exists",request);
            return mapping.getInputForward();
        }
        catch(NumberFormatException e3)
        {
            trx.rollback();
            postGlobalError("error.numberformatexception.price",request);
            return mapping.getInputForward();
            
        }
        finally
        {
            trx.close();
        }
        
        request.getSession().setAttribute(Constants.PRODUCT_CREATED,list);
        
        return mapping.findForward(CREATE_POS_GARMENT_PRODUCT);
    }
    
    
    public static final String VIEW_ALL_POS_PRODUCTS="viewAllPOSProducts";
    public ActionForward viewAllPOSProducts(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd=init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        Properties ctx=TmkJSPEnv.getCtx(request);
        DefaultForm df = (DefaultForm)form;
        ProductBean bean = (ProductBean) df.getBean();
        ArrayList<ProductBean> list=null;
        
        try
        {
            list=new ArrayList<ProductBean>();
            list=POSProductManager.viewAllProducts(ctx,bean.getProductName(),bean.getBarCode());
            
        }
        catch(ProductNotFoundException e1)
        {
            postGlobalError("error.product.not.defined.client",request);
            return mapping.getInputForward();
            
        }
        
        request.getSession().setAttribute(Constants.VIEW_POS_PRODUCTS,list); 
        return mapping.findForward(VIEW_ALL_POS_PRODUCTS);
    }
    
    
    public static final String VIEW_POS_PRODUCT_DETAIL="viewPOSProductDetails";
    public static final String ERROR_VIEW_PRODUCT_DETAIL = "errorViewProductDetails";
    
    public ActionForward viewPOSProductDetailInfo(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
    	  ActionForward fwd=init(mapping,form,request,response);
          if(fwd!=null)
              return fwd;
          Properties ctx=TmkJSPEnv.getCtx(request);
          DefaultForm df = (DefaultForm)form;
          ProductDetailsBean bean = (ProductDetailsBean) df.getBean();
          
          try
          {
        	  int productId = bean.getProductId().intValue();
        	  ProductDetailsBean prodDetailsBean = ProductManager.getProductDetailInfo(ctx, productId, null);
        	  
        	  request.setAttribute(Constants.PRODUCT_DETAIL_INFO, prodDetailsBean);
        	  
        	  return mapping.findForward(VIEW_POS_PRODUCT_DETAIL);
          }
          catch(Exception ex)
          {
        	  return mapping.findForward(ERROR_VIEW_PRODUCT_DETAIL);
          }
    }
    
    public ActionForward validateProductName(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException, IOException
    {
    	DefaultForm df = (DefaultForm) form;
    	String productName = df.getProductName();
    	Properties ctx=TmkJSPEnv.getCtx(request);
    	 
    	boolean isNameValid = ProductManager.isProductPresent(ctx, productName, null);
    	
    	response.setContentType("text/plain");
    	PrintWriter out = response.getWriter();    	
    	out.print(isNameValid);
    	out.flush();
    	out.close();    	
    	
    	return null;
    }
    
    public ActionForward validateProductBarcode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException, IOException
    {
    	DefaultForm df = (DefaultForm) form;
    	String barcode = df.getBarCode();
    	Properties ctx=TmkJSPEnv.getCtx(request);
    	 
    	boolean isBarcodeValid = ProductManager.isBarCodePresent(ctx, barcode, null);
    	
    	response.setContentType("text/plain");
    	PrintWriter out = response.getWriter();
    	out.print(isBarcodeValid);
    	out.flush();
    	out.close();
    	
    	return null;
    }
    
    public static final String SEARCH_POS_PRODUCTS = "searchPOSProducts" ;
    
    public ActionForward searchPOSProducts (ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException, IOException
    {
    	viewAllPOSProducts(mapping, form, request, response);
    	
    	return mapping.findForward(SEARCH_POS_PRODUCTS);
    }
    
    
    public ActionForward addAllProducts (ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException, IOException
    {
    	Properties ctx=TmkJSPEnv.getCtx(request);
        HttpSession session = request.getSession();
        
        ProductCart cart = (ProductCart) session.getAttribute(Constants.PRODUCT_CART);
        ArrayList<ProductBean> productList = (ArrayList<ProductBean>) session.getAttribute(Constants.VIEW_POS_PRODUCTS); 
        
        if(productList == null)
        {
        	return mapping.findForward(VIEW_ALL_POS_PRODUCTS);
        }
        
        if(cart == null)
        {
        	cart = new ProductCart(ctx);
        }
        
        for(ProductBean bean:productList)
        {
        	Integer productId = bean.getProductId();
        	
        	if(productId == null) continue;
        	if(cart.hasProduct(productId.intValue())) continue;
        	
        	cart.addProduct(productId.intValue());
        }
        
        session.setAttribute(Constants.PRODUCT_CART,cart);
        
        return mapping.findForward(VIEW_ALL_POS_PRODUCTS);
        
    }
    
    public ActionForward addToCart (ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException, IOException
    {
    	Properties ctx=TmkJSPEnv.getCtx(request);
        DefaultForm df = (DefaultForm)form;
        ProductBean bean = (ProductBean) df.getBean();
        
        Integer productId = bean.getProductId();
        HttpSession session = request.getSession();
        
        ProductCart cart = (ProductCart) session.getAttribute(Constants.PRODUCT_CART);
        
        if( cart == null )
        {
        	cart = new ProductCart( ctx );
        }
        
        cart.addProduct( productId.intValue() );        
        session.setAttribute(Constants.PRODUCT_CART, cart);
        
        String script = "productAdded("+ productId +");setCartCounter("+ cart.getNoOfProducts() +")";
        
        PrintWriter writer = response.getWriter();        
        writer.print( script );
        writer.flush();
        writer.close();
    	
    	return null;
    }
    
    public ActionForward removeFromCart (ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException, IOException
    {
    	Properties ctx=TmkJSPEnv.getCtx(request);
        DefaultForm df = (DefaultForm)form;
        ProductBean bean = (ProductBean) df.getBean();
        
        Integer productId = bean.getProductId();
        HttpSession session = request.getSession();
        
        ProductCart cart = (ProductCart) session.getAttribute(Constants.PRODUCT_CART);
        
        if( cart == null )
        {
        	cart = new ProductCart( ctx );
        }
        
        cart.removeProduct( productId.intValue() );        
        session.setAttribute(Constants.PRODUCT_CART, cart);
        
        String script = "productRemoved("+ productId +");setCartCounter("+ cart.getNoOfProducts() +")";
        
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
    
    public static final String VIEW_CART = "viewCart";
    public ActionForward viewCart(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd=init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        HttpSession session = request.getSession();  
        ProductCart cart = (ProductCart) session.getAttribute(Constants.PRODUCT_CART);
        
        ArrayList<ProductDetailsBean> productList = null;
        
        if(cart != null)
        {
        	productList = cart.getProducts();
            session.setAttribute(Constants.PRODUCT_DETAILS, productList);
        }
        
        return mapping.findForward(VIEW_CART);
    }
    
    public static final String REMOVE = "remove";
    public ActionForward remove(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd=init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        HttpSession session = request.getSession();  
        ProductCart cart = (ProductCart) session.getAttribute(Constants.PRODUCT_CART);
        
        DefaultForm df = (DefaultForm) form;
        ProductBean bean = (ProductBean) df.getBean();        
        Integer productId = bean.getProductId();
        
        if(cart != null)
        {
        	cart.removeProduct( productId.intValue() );
        	session.setAttribute(Constants.PRODUCT_CART, cart);
        }
        
        return mapping.findForward(REMOVE);
    }
    
    //----------------------------------------------------------------------------------------------------------------------------------------------------
    
    public static final String EXPORT_CSV = "exportCSV";
    public ActionForward exportCSV(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException, IOException
    {
        ActionForward fwd=init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx=TmkJSPEnv.getCtx(request);
        HttpSession session = request.getSession();  
        ProductCart cart = (ProductCart) session.getAttribute(Constants.PRODUCT_CART);
          
        if(cart == null)
        {
        	String msg = "Cannot print barcode. Reason: cart is empty!";
        	throw new OperationException( msg );
        }
        
        String reportName = POSProductManager.getProductCartAsCSV( ctx, cart );
        String reportURI = ReportManager.getReportURI( reportName, request );        	
        response.sendRedirect(reportURI);        
        
        //return mapping.findForward(EXPORT_CSV);
        return null;
    }
    
    public static final String PRINT_PDF = "printPDF";
    public ActionForward printPDF(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException, IOException
    {
        ActionForward fwd=init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
     
        Properties ctx=TmkJSPEnv.getCtx(request);
        HttpSession session = request.getSession();  
        ProductCart cart = (ProductCart) session.getAttribute(Constants.PRODUCT_CART);        
        //ArrayList<ProductDetailsBean> productList = null;
        
        //ArrayList<ProductDetailsBean> productList = null;
        
        if(cart != null)
        {
        //	productList = cart.getProducts();     
        	String reportName=POSProductManager.productcatalogue(ctx, cart);	
        	String reportURI = ReportManager.getReportURI(reportName,request);
        	response.sendRedirect(reportURI);
        }
        
    //    Properties ctx = TmkJSPEnv.getCtx(request);		
	//	String reportName=POSProductManager.productcatalogue(ctx);	
	//	ReportManager.writeReport(reportName, response);
        
        //TODO by Martine
        
        return mapping.findForward(PRINT_PDF);
    }
    
    public static final String PRINT_BARCODE = "printBarcode";
    public ActionForward printBarcode(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException, IOException
    {
        ActionForward fwd=init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        HttpSession session = request.getSession();  
        ProductCart cart = (ProductCart) session.getAttribute(Constants.PRODUCT_CART);
        
        if(cart == null)
        {
        	String msg = "Cannot print barcode. Reason: cart is empty!";
        	throw new OperationException( msg );
        }
        
        String printData = POSProductManager.getPrintBarcodeData( ctx, cart, null ); 
        //send it to client
        
        String filename 	= RandomStringGenerator.randomstring() + ".txt"; 
        String filepath 		= ReportManager.getReportPath( filename );
        
        FileOutputStream fos = new FileOutputStream( new File( filepath ) );
        fos.write( printData.getBytes() );
        fos.flush();
        fos.close();
        
        String fileURL = ReportManager.getReportURI( filename, request );
        response.sendRedirect( fileURL );  
        
        return null;
    }
    
//    public static final String GENERATE_PROD_PDF ="generateproductpdf";
//	public ActionForward generateproductpdf(ActionMapping mapping, ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
//	{
//		ActionForward fwd = init(mapping,form,request,response);
//		if (fwd!=null)
//			return fwd;
//		
//		Properties ctx = TmkJSPEnv.getCtx(request);
//		
//		String reportName=POSProductManager.productcatalogue(ctx);
//	
//		ReportManager.writeReport(reportName, response);
//		
//		
//		return null;
//	}
//    

 public static final String VIEW_PRODUCT_SALES_DETAILS = "viewProductSalesDetails";
    public ActionForward viewProductSalesDetails(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException, IOException
    {
        ActionForward fwd=init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        HttpSession session = request.getSession();  
        
        DefaultForm df = (DefaultForm) form;
        ProductBean bean = (ProductBean) df.getBean();        
        Integer productId = bean.getProductId();
        
        ProductSalesSummaryBean summaryBean = POSProductManager.getProductSalesInfoSummary( ctx, productId.intValue(), null );
        ArrayList<ProductSalesInfoBean> salesDetails = POSProductManager.getProductSalesInfoDetails( ctx, productId.intValue(), null );
        
        ProductSalesSummaryBean[] bucket = POSProductManager.getSalesBucket(ctx, productId.intValue(), null);
        
        
        session.setAttribute( Constants.PRODUCT_SALES_SUMMARY, summaryBean );
        session.setAttribute( Constants.PRODUCT_SALES_DETAILS, salesDetails );
        session.setAttribute( Constants.PRODUCT_SALES_BUCKET, bucket );
        
        return mapping.findForward( VIEW_PRODUCT_SALES_DETAILS );
    }
    
    
    public static final String UPDATE_BULK_PRODUCT_DETAILS="updateBulkProductDetails";
    public ActionForward updateBulkProductDetails(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd=init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        Properties ctx=TmkJSPEnv.getCtx(request);
        DefaultForm df = (DefaultForm)form;
        df.validate(mapping,request);
        
        ProductBean bean = (ProductBean)df.getBean();
        bean.setFile(df.getFile());
        
        ProductCart cart = (ProductCart) request.getSession().getAttribute(Constants.PRODUCT_CART);
        
        Trx trx = Trx.get(TrxPrefix.getPrefix(),true);
        Integer[] productIds = cart.getProductIDs();
        
        try
        {
        	trx.start();
            POSProductManager.editBulkProduct(ctx,productIds,bean,trx.getTrxName());
            trx.commit();
        }
        catch(BarcodeAlreadyExistsException e1)
        {
            
            trx.rollback();
            postGlobalError("error.barcode.already.exists",request);
            return mapping.getInputForward();
        }
        catch(CannotInactivateProductException e2)
        {
            trx.rollback();
            postGlobalError("error.product.cannot.inactivate", e2.getMessage(), request);
            return mapping.getInputForward();
           
        }
        finally
        {
            trx.close();
        }
        //ProductBean productBean= POSProductManager.viewPOSProduct(ctx,bean.getProductId().intValue());
        //request.getSession().setAttribute(Constants.PRODUCT_DETAILS,productBean);
        return mapping.findForward(UPDATE_BULK_PRODUCT_DETAILS);
    }
    
    
    public static final String INIT_UPDATE_BULK_PRODUCT_DETAILS = "initUpdateBulkProductDetails"; 
    public ActionForward initUpdateBulkProductDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws OperationException
    {
    	Properties ctx=TmkJSPEnv.getCtx(request);
    	ArrayList taxList = POSGoodsManager.getAllTaxCategory(ctx); 
    	
    	request.setAttribute(Constants.TAX_CATEGORY_ID,taxList);    	
    	return mapping.findForward(INIT_UPDATE_BULK_PRODUCT_DETAILS);
    }
    
    public static final String VIEW_PRODUCT = "viewProduct"; 
    public ActionForward viewProduct(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws OperationException, ApplicationException
    {    	
    	viewProductForUpdate(mapping,form,request,response);
    	
    	return mapping.findForward(VIEW_PRODUCT);
    }
    
    public static final String ACTIVATE_PRODUCT = "activateProduct"; 
    public ActionForward activateProduct(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws OperationException, ApplicationException
    {    	
    	ActionForward fwd=init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        Properties ctx=TmkJSPEnv.getCtx(request);
        DefaultForm df = (DefaultForm)form;
        df.validate(mapping,request);
        
        ProductBean bean = (ProductBean)df.getBean();
        Integer productId = bean.getProductId();
        
        ProductManager.activateProducts(ctx, new Integer[]{productId},null);
        
        ArrayList<ProductBean> productList = (ArrayList<ProductBean>) request.getSession().getAttribute(Constants.VIEW_POS_PRODUCTS);
        ProductBean b = null;
        ArrayList<ProductBean> updatedProductList = new ArrayList<ProductBean>();
        
        for (int i=0; i<productList.size(); i++) 
        {
			b = productList.get(i);
			
			if(b.getProductId().equals(productId))
			{
				b.setIsActive(Boolean.valueOf(true));
			}
			
			updatedProductList.add(b);
		}
        
        request.getSession().removeAttribute(Constants.VIEW_POS_PRODUCTS);
        request.getSession().setAttribute(Constants.VIEW_POS_PRODUCTS,updatedProductList);
    	
    	return mapping.findForward(ACTIVATE_PRODUCT);
    }
    
    public static final String DEACTIVATE_PRODUCT = "deactivateProduct"; 
    public ActionForward deactivateProduct(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws OperationException, ApplicationException
    {    	
    	ActionForward fwd=init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        Properties ctx=TmkJSPEnv.getCtx(request);
        DefaultForm df = (DefaultForm)form;
        df.validate(mapping,request);
        
        ProductBean bean = (ProductBean)df.getBean();
        Integer productId = bean.getProductId();
      
        try
        {
            ProductManager.inactivateProducts(ctx, new Integer[]{productId},null);  
        }
        catch(CannotInactivateProductException e2)
        {
            postGlobalError("error.product.cannot.inactivate", e2.getMessage(), request);
            return mapping.getInputForward();
        }
        
        
        ArrayList<ProductBean> productList = (ArrayList<ProductBean>) request.getSession().getAttribute(Constants.VIEW_POS_PRODUCTS);
        ProductBean b = null;
        ArrayList<ProductBean> updatedProductList = new ArrayList<ProductBean>();
        
        for (int i=0; i<productList.size(); i++) 
        {
			b = productList.get(i);
			
			if(b.getProductId().equals(productId))
			{
				b.setIsActive(Boolean.valueOf(false));
			}
			
			updatedProductList.add(b);
		}
        
        request.getSession().removeAttribute(Constants.VIEW_POS_PRODUCTS);
        request.getSession().setAttribute(Constants.VIEW_POS_PRODUCTS,updatedProductList);
    	
    	return mapping.findForward(DEACTIVATE_PRODUCT);
    }
    
    public static final String PRICE_CHECK = "priceCheck";
    public ActionForward priceCheck(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws OperationException, ApplicationException
    {    	
    	ActionForward fwd=init(mapping,form,request,response);
    	
        if(fwd!=null)
        {
            return fwd;
        }
        
        Properties ctx=TmkJSPEnv.getCtx(request);
        DefaultForm df = (DefaultForm)form;        
        String barcode = df.getBarCode();
        
        ProductBean productBean = null;
        try 
        {
			productBean = POSProductManager.getProduct(ctx, barcode, null);
			request.setAttribute(Constants.PRODUCT_DETAILS, productBean);
		} 
        catch (InvalidBarcodeException e) 
		{
			postGlobalError("error.invalid.barcode", "barcode", request);
			return mapping.getInputForward();
		}
        catch (ProductNotFoundException e) 
		{
        	postGlobalError("error.notfound", "Product", request);
        	return mapping.getInputForward();
		}
        
        return mapping.findForward(PRICE_CHECK);
    }
    
    
}
