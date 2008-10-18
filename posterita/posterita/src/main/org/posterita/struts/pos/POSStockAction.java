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
 * Created on May 8, 2006
 */


package org.posterita.struts.pos;

import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.posterita.Constants;
import org.posterita.beans.AttributeBean;
import org.posterita.businesslogic.POSStockManager;
import org.posterita.core.TmkJSPEnv;
import org.posterita.exceptions.ApplicationException;
import org.posterita.exceptions.OperationException;
import org.posterita.struts.core.BaseDispatchAction;
import org.posterita.struts.core.DefaultForm;


public class POSStockAction extends BaseDispatchAction
{
	public static final String initPOSStock="initPOSStock"; 
    public ActionForward initPOSStock(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx =TmkJSPEnv.getCtx(request);

        request.getSession().removeAttribute(Constants.PRODUCTS);
        ArrayList list=POSStockManager.getAllAttributeSet(ctx);
        request.getSession().setAttribute(Constants.PRODUCT_ATTRIBUTE_SET,list);
        	       
        return mapping.findForward(GET_POS_STOCK);        
        
    }
    
    public static final String GET_POS_STOCK="getPOSStock"; 
    public ActionForward getPOSStock(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx =TmkJSPEnv.getCtx(request);

        request.removeAttribute(Constants.PRODUCTS);
        ArrayList list=POSStockManager.getAllAttributeSet(ctx);
        request.getSession().setAttribute(Constants.PRODUCT_ATTRIBUTE_SET,list);
        
        ArrayList list2=POSStockManager.getStockFromSearch(ctx, null, null, null);
        request.getSession().setAttribute(Constants.PRODUCTS,list2);
       
        return mapping.findForward(GET_POS_STOCK);
        
        
    }
    
    public static final String GET_STOCK="getStock"; 
    public ActionForward getStock(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx =TmkJSPEnv.getCtx(request);
        DefaultForm df  = (DefaultForm)form;
        AttributeBean bean = (AttributeBean) df.getBean();
        ArrayList list=POSStockManager.getAllAttributeFromAttributeSet(ctx,bean);
        request.getSession().setAttribute(Constants.ATTRIBUTE_SET_ID,bean.getAttributeSetId());
        request.getSession().setAttribute(Constants.PRODUCT_ATTRIBUTE_LIST,list);
        request.removeAttribute(Constants.PRODUCTS);
        ArrayList productList=POSStockManager.getAllProducts(ctx, bean.getAttributeSetId());
        request.getSession().setAttribute(Constants.PRODUCTS,productList);
       
        return mapping.findForward(GET_STOCK);
    }
    
    
    public static final String GET_PRODUCTS="getProducts"; 
    public ActionForward getProducts(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Integer attributeSetId=(Integer)request.getSession().getAttribute(Constants.ATTRIBUTE_SET_ID);
        Properties ctx =TmkJSPEnv.getCtx(request);
        DefaultForm df  = (DefaultForm)form;
        AttributeBean bean = (AttributeBean) df.getBean();
        ArrayList list=POSStockManager.getProducts(ctx,bean);
        request.setAttribute(Constants.PRODUCTS,list);
        
        bean.setAttributeSetId(attributeSetId);
        
        ArrayList attriList=POSStockManager.getAllAttributeFromAttributeSet(ctx,bean);
        request.getSession().setAttribute(Constants.PRODUCT_ATTRIBUTE_LIST,attriList);
        
        return mapping.findForward(GET_PRODUCTS);
    }
    
    
    public static final String GET_STOCK_FROM_SEARCH="getStockFromSearch"; 
    public ActionForward getStockFromSearch(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        if (isDisplayTagAction(request))
        {
            return mapping.findForward(GET_STOCK_FROM_SEARCH);
        }
        
        Properties ctx =TmkJSPEnv.getCtx(request);
        DefaultForm df  = (DefaultForm)form;
        
        String filter=null;
        if(df.getQtyFilter()!=null && df.getQtyFilter().length()!=0) 
          filter=df.getQtyFilter();  
        
        ArrayList list = POSStockManager.getStockFromSearch(ctx,df.getProductName(),df.getBarCode(),filter);
        request.getSession().setAttribute(Constants.PRODUCTS,list);
        
        return mapping.findForward(GET_STOCK_FROM_SEARCH);
    }
    
}
