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
 * Created on Dec 4, 2006
 */


package org.posterita.struts.pos;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.compiere.model.MInventory;
import org.compiere.process.DocumentEngine;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.posterita.Constants;
import org.posterita.beans.InventoryBean;
import org.posterita.beans.InventoryCartBean;
import org.posterita.beans.InventoryLineBean;
import org.posterita.businesslogic.performanceanalysis.ReportManager;
import org.posterita.businesslogic.stock.InventoryCartManager;
import org.posterita.businesslogic.stock.InventoryManager;
import org.posterita.businesslogic.stock.StockManager;
import org.posterita.core.TmkJSPEnv;
import org.posterita.core.TrxPrefix;
import org.posterita.exceptions.ApplicationException;
import org.posterita.exceptions.CannotCreateInventoryLineException;
import org.posterita.exceptions.NoCheckBoxSelectedException;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.ProductNotFoundException;
import org.posterita.form.InventoryForm;
import org.posterita.form.InventoryLineForm;
import org.posterita.struts.core.BaseDispatchAction;
import org.posterita.struts.core.DefaultForm;


public class InventoryAction extends BaseDispatchAction
{
    
    public static final String CREATE_INVENTORY="createInventory"; 
    public ActionForward createInventory(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx =TmkJSPEnv.getCtx(request);
        InventoryForm If = (InventoryForm)form;
        InventoryBean bean = (InventoryBean) If.getBean();
        MInventory inventory=null;
        
        if(bean.getBarCode()!=null && bean.getProductId()==null)
        {
            int productId=StockManager.getProductIdFromBarCode(ctx,bean.getBarCode());
            bean.setProductId(productId);
        }
        
       
        Trx trx = Trx.get(TrxPrefix.getPrefix(),true);
        try
        {   
            if(bean.getInventoryId()==null || bean.getInventoryId()==0)
            {
                inventory=InventoryManager.createInventory(ctx,bean.getDescription(),null);
                bean.setInventoryId(inventory.get_ID());
            }
           if(bean.getDescription()!=null)
           {
        	   MInventory inventory2=new MInventory(ctx,bean.getInventoryId(),null);
        	   inventory2.setDescription(bean.getDescription());
        	   inventory2.save();
           }
           
            
            InventoryManager.addInventoryLine(ctx,bean.getInventoryId(),bean.getProductId(), new BigDecimal(0), new BigDecimal(1), true,null);
            trx.commit();
        }
        catch(CannotCreateInventoryLineException e)
        {
            trx.rollback();
            ArrayList list = InventoryManager.getInventoryLines(ctx,bean.getInventoryId());
            request.setAttribute(Constants.INVENTORY_LINE_LIST,list);
            request.setAttribute(Constants.INVENTORY_ID,bean.getInventoryId());
            postGlobalError("error.cannot.create.inventory.line",request);
            return mapping.getInputForward();
        }
        catch(ProductNotFoundException e)
        {
            postGlobalError("error.product.name.required",request);
            ArrayList list = InventoryManager.getInventoryLines(ctx,bean.getInventoryId());
            request.setAttribute(Constants.INVENTORY_LINE_LIST,list);
            request.setAttribute(Constants.INVENTORY_ID,bean.getInventoryId());
            return mapping.getInputForward();
        }
     
        catch(OperationException e)
        {
            trx.rollback();
            ArrayList list = InventoryManager.getInventoryLines(ctx,bean.getInventoryId());
            request.setAttribute(Constants.INVENTORY_LINE_LIST,list);
            request.setAttribute(Constants.INVENTORY_ID,bean.getInventoryId());
            throw new OperationException(e);
        }
        finally
        {
            trx.close();
        }
        
        If.populate(bean);
        
        ArrayList list = InventoryManager.getInventoryLines(ctx,bean.getInventoryId());
        request.setAttribute(Constants.INVENTORY_LINE_LIST,list);
        request.setAttribute(Constants.INVENTORY_ID,bean.getInventoryId());
      
        return mapping.findForward(CREATE_INVENTORY);
    }
    
    public static final String SAVE_COUNT_QTY="saveCountQty";
    public ActionForward saveCountQty(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx =TmkJSPEnv.getCtx(request);
        InventoryForm If = (InventoryForm)form;
        InventoryBean bean = (InventoryBean) If.getBean();
        Trx trx = Trx.get(TrxPrefix.getPrefix(),true);
        try
        {    
            InventoryManager.saveCountQtyInInventoryLine(ctx,bean.getInventoryLineId(), bean.getQtyCsv(), bean.getQtyCount(), trx.getTrxName());
            trx.commit();
        }
        catch(OperationException e)
        {
            trx.rollback();
            ArrayList list = InventoryManager.getInventoryLines(ctx,bean.getInventoryId());
            request.setAttribute(Constants.INVENTORY_LINE_LIST,list);
            request.setAttribute(Constants.INVENTORY_ID,bean.getInventoryId());
            throw new OperationException(e);
        }
        finally
        {
            trx.close();
        }
        
        ArrayList list = InventoryManager.getInventoryLines(ctx,bean.getInventoryId());
        request.setAttribute(Constants.INVENTORY_LINE_LIST,list);
        request.setAttribute(Constants.WHOLE_INVENTORY_LINE_LIST,list);
        return mapping.findForward(SAVE_COUNT_QTY);
    }
    
    
    public static final String COMPLETE_INVENTORY="completeInventory";
    public ActionForward completeInventory(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx =TmkJSPEnv.getCtx(request);
        InventoryForm If = (InventoryForm)form;
        InventoryBean bean = (InventoryBean) If.getBean();
        InventoryManager.completeStockAdjustment(ctx,bean.getInventoryId());
        
        ArrayList list = InventoryManager.getInventoryLines(ctx,bean.getInventoryId());
        request.setAttribute(Constants.COMPLETED_INVENTORY_LINE_LIST,list);
        request.removeAttribute(Constants.INVENTORY_LINE_LIST);
        request.removeAttribute(Constants.WHOLE_INVENTORY_LINE_LIST);
        return mapping.findForward(COMPLETE_INVENTORY);
    }
    
    
    public static final String VIEW_INVENTORY_HISTORY="viewInventoryHistory";
    public ActionForward viewInventoryHistory(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        DefaultForm If = (DefaultForm)form;
        
        Properties ctx =TmkJSPEnv.getCtx(request);
        ArrayList list = InventoryManager.viewInventoryHistory(ctx);
        request.setAttribute(Constants.INVENTORY_HISTORY_LIST,list);
        return mapping.findForward(VIEW_INVENTORY_HISTORY);
    }
    
    public ActionForward getInventoryHistory(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        DefaultForm If = (DefaultForm)form;
        InventoryBean bean = (InventoryBean) If.getBean();
        
        Properties ctx =TmkJSPEnv.getCtx(request);
        ArrayList list = InventoryManager.viewInventory(ctx, bean.getDocStatus(), bean.getMonth(), bean.getYear());
        request.setAttribute(Constants.INVENTORY_HISTORY_LIST,list);
        return mapping.findForward(VIEW_INVENTORY_HISTORY);
    }
    
    public ActionForward searchProductInInventory(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        DefaultForm If = (DefaultForm)form;
        InventoryBean bean = (InventoryBean) If.getBean();
        ArrayList list = null;
        
        Properties ctx =TmkJSPEnv.getCtx(request);
        try
        {
            list = InventoryManager.searchProductInInventory(ctx, bean.getProductId(), bean.getBarCode());
        }
        catch(Exception e)
        {
            postComplexGlobalError("error.notfound", new Object[]{e.getMessage()}, request);
        }
        request.setAttribute(Constants.INVENTORY_HISTORY_LIST, list);
        return mapping.findForward(VIEW_INVENTORY_HISTORY);
    }
    
    public static final String DELETE_INVENTORY="deleteInventory";
    public ActionForward deleteInventory(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx =TmkJSPEnv.getCtx(request);
        InventoryForm If = (InventoryForm)form;
        InventoryBean bean = (InventoryBean) If.getBean();
        InventoryManager.deleteInventory(ctx,bean.getInventoryId().intValue());
        InventoryCartManager.clearInventorycart(ctx, request);
        ArrayList list = InventoryManager.viewInventoryHistory(ctx);
        request.setAttribute(Constants.INVENTORY_HISTORY_LIST,list);
        return mapping.findForward(DELETE_INVENTORY);
    }
    
    public static final String DELETE_INVENTORY_LINE="deleteInventoryLine";
    public ActionForward deleteInventoryLine(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx =TmkJSPEnv.getCtx(request);
        InventoryForm If = (InventoryForm)form;
        InventoryBean bean = (InventoryBean) If.getBean();
        InventoryManager.deleteInventoryLine(ctx,bean.getInventoryId().intValue(),bean.getInventoryLineId().intValue());
        ArrayList list = InventoryManager.getInventoryLines(ctx,bean.getInventoryId());
        request.setAttribute(Constants.INVENTORY_LINE_LIST,list);
        request.setAttribute(Constants.INVENTORY_ID,bean.getInventoryId());
      
        return mapping.findForward(DELETE_INVENTORY_LINE);
    }
        
    public static final String VIEW_INVENTORY="viewInventory";
    public ActionForward viewInventory(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx =TmkJSPEnv.getCtx(request);
        InventoryLineForm If = (InventoryLineForm)form;
        InventoryLineBean bean = (InventoryLineBean) If.getBean();

        ArrayList<InventoryLineBean> list = InventoryManager.getInventoryLines(ctx,bean.getInventoryId());
        If.populate(bean);
        if(InventoryManager.getInventoryDocStatus(ctx,bean.getInventoryId()).equals(DocumentEngine.STATUS_Drafted))
        {
            InventoryCartBean cartBean = null;
            
            for (InventoryLineBean inventoryLineBean : list)
            {
                cartBean = StockManager.addToInventoryCart(ctx, inventoryLineBean, cartBean, false, true);
                bean.setDocStatus(inventoryLineBean.getDocStatus());
                bean.setDocumentNo(inventoryLineBean.getDocumentNo());
            }
            
            If.populate(bean);
            request.getSession().setAttribute(Constants.INVENTORY_CART_ITEMS, cartBean.getItems());
            request.getSession().setAttribute(Constants.INVENTORY_CART, cartBean);
            request.getSession().setAttribute(Constants.INVENTORY_ID,bean.getInventoryId());
            
            Env.setContext(ctx, Constants.INVENTORY_ID, bean.getInventoryId());
            Env.setContext(ctx, Constants.DESCRIPTION, bean.getDescription());
            request.getSession().setAttribute(Constants.DOC_NO, bean.getDocumentNo());
            request.getSession().setAttribute(Constants.DOC_STATUS, bean.getDocStatus());
            
            return new ActionForward("/initInventoryCart.do");
        }
        else
        {
            request.setAttribute(Constants.COMPLETED_INVENTORY_LINE_LIST,list);
            return new ActionForward("/CompletedAdjustmentStock.do");
        }
      
    }
    
    public static final String CREATE_WHOLE_INVENTORY="createWholeInventory"; 
    public ActionForward createWholeInventory(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx =TmkJSPEnv.getCtx(request);
        InventoryForm If = (InventoryForm)form;
        InventoryBean bean = (InventoryBean) If.getBean();
        MInventory inventory=null;
        Trx trx = Trx.get(TrxPrefix.getPrefix(),true);
        try
        {   trx.start();
            if(bean.getInventoryId()==null || bean.getInventoryId()==0)
            {
                inventory=InventoryManager.createInventory(ctx,bean.getDescription(),trx.getTrxName());
                bean.setInventoryId(inventory.get_ID());
            }
        
            InventoryManager.addAllProductsInventoryLine(ctx,bean.getInventoryId(),trx.getTrxName());
            
            trx.commit();
        }
        catch(OperationException e)
        {
            trx.rollback();
            ArrayList list = InventoryManager.getInventoryLines(ctx,bean.getInventoryId());
            request.setAttribute(Constants.INVENTORY_LINE_LIST,list);
            request.setAttribute(Constants.INVENTORY_ID,bean.getInventoryId());
            throw new OperationException(e);
        }
        finally
        {
            trx.close();
        }
        
        ArrayList<InventoryLineBean> list = InventoryManager.getInventoryLines(ctx, inventory.getM_Inventory_ID());
        InventoryCartBean cartBean = null;
        
        for (InventoryLineBean inventoryLineBean : list)
        {
            cartBean = StockManager.addToInventoryCart(ctx, inventoryLineBean, cartBean, false, true);
        }
        
        request.getSession().setAttribute(Constants.INVENTORY_CART_ITEMS, cartBean.getItems());
        request.getSession().setAttribute(Constants.INVENTORY_CART, cartBean);
        request.getSession().setAttribute(Constants.INVENTORY_ID,bean.getInventoryId());
        
        Env.setContext(ctx, Constants.INVENTORY_ID, inventory.getM_Inventory_ID());
        Env.setContext(ctx, Constants.DESCRIPTION, inventory.getDescription());
        request.getSession().setAttribute(Constants.DOC_NO, inventory.getDocumentNo());
        request.getSession().setAttribute(Constants.DOC_STATUS, inventory.getDocStatus());
        
        return new ActionForward("/initInventoryCart.do");
    }
    
    public static final String CREATE_CSV_FILE_FOR_INVENTORY="createCSVFileForInventory"; 
    public ActionForward createCSVFileForInventory(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException, IOException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx =TmkJSPEnv.getCtx(request);
        InventoryForm If = (InventoryForm)form;
        InventoryBean bean = (InventoryBean) If.getBean();
        ArrayList list = InventoryManager.getInventoryLines(ctx,bean.getInventoryId());
        String reportName = InventoryManager.createCSVForInventory(ctx,list);
        String reportURI=ReportManager.getReportURI(reportName,request);
        response.sendRedirect(reportURI);
        return null;
        
    }
    
    
    public static final String MERGE_INVENTORY_TO_CREATE_ONE="mergeInventoryToCreateOne";
	public ActionForward mergeInventoryToCreateOne(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException, IOException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx =TmkJSPEnv.getCtx(request);
        InventoryForm If = (InventoryForm)form;
        InventoryBean bean = (InventoryBean) If.getBean();
        Trx trx = Trx.get(TrxPrefix.getPrefix(),true);

    	MInventory inventory =null;
        try
        {  
        	inventory = InventoryManager.mergeInventory(ctx,bean,null);
        	trx.commit();
        }
        catch(NoCheckBoxSelectedException e)
        {
        	 trx.rollback();
        	 ArrayList list = InventoryManager.viewInventoryHistory(ctx);
             request.setAttribute(Constants.INVENTORY_HISTORY_LIST,list);
             postGlobalError("error.no.checkbox.selected",request);
             return mapping.getInputForward();
        }
        catch(CannotCreateInventoryLineException e1)
        {
            trx.rollback();
            ArrayList list = InventoryManager.viewInventoryHistory(ctx);
            request.setAttribute(Constants.INVENTORY_HISTORY_LIST,list);
            postGlobalError("error.cannot.create.inventory.line",request);
            return mapping.getInputForward();
        }
     
        catch(OperationException e2)
        {
            trx.rollback();
            throw new OperationException(e2);
        }
        finally
        {
            trx.close();
        }
        
        ArrayList<InventoryLineBean> list = InventoryManager.getInventoryLines(ctx, inventory.getM_Inventory_ID());
        InventoryCartBean cartBean = null;
        
        for (InventoryLineBean inventoryLineBean : list)
        {
            cartBean = StockManager.addToInventoryCart(ctx, inventoryLineBean, cartBean, false, true);
        }
        
        request.getSession().setAttribute(Constants.INVENTORY_CART_ITEMS, cartBean.getItems());
        request.getSession().setAttribute(Constants.INVENTORY_CART, cartBean);
        request.getSession().setAttribute(Constants.INVENTORY_ID, inventory.getM_Inventory_ID());
        
        Env.setContext(ctx, Constants.INVENTORY_ID, inventory.getM_Inventory_ID());
        Env.setContext(ctx, Constants.DESCRIPTION, inventory.getDescription());
        request.getSession().setAttribute(Constants.DOC_NO, inventory.getDocumentNo());
        request.getSession().setAttribute(Constants.DOC_STATUS, inventory.getDocStatus());
        
        return new ActionForward("/initInventoryCart.do");
                
    }	
}
    