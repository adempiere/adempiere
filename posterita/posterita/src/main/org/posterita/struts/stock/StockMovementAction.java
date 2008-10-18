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
 * Created on May 22, 2006 by alok
 */
package org.posterita.struts.stock;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.MessageResources;
import org.compiere.model.MMovement;
import org.compiere.model.MProduct;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.posterita.Constants;
import org.posterita.beans.ItemBean;
import org.posterita.beans.MMovementCartBean;
import org.posterita.beans.StockMovementBean;
import org.posterita.businesslogic.administration.PriceListManager;
import org.posterita.businesslogic.stock.MMovementManager;
import org.posterita.businesslogic.stock.StockManager;
import org.posterita.core.TimestampConvertor;
import org.posterita.core.TmkJSPEnv;
import org.posterita.core.TrxPrefix;
import org.posterita.exceptions.ApplicationException;
import org.posterita.exceptions.InputQuantityLessThanZeroException;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.ProductNotFoundException;
import org.posterita.exceptions.ProductNotOnPriceListException;
import org.posterita.exceptions.QuantityNotAvailableException;
import org.posterita.form.StockMovementForm;
import org.posterita.lib.UdiConstants;
import org.posterita.struts.core.BaseDispatchAction;
import org.posterita.struts.core.DefaultForm;

public class StockMovementAction extends BaseDispatchAction 
{
	public static final String VIEW_STOCK = "viewStock";
	public ActionForward viewStock(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException, IOException
	{
		ActionForward fwd = init(mapping, form, request, response);
		
		if (fwd != null)
		{
			return fwd;
		}
        
		Properties ctx = TmkJSPEnv.getCtx(request);
		StockMovementForm stockForm = (StockMovementForm)form;
		StockMovementBean bean = (StockMovementBean)stockForm.getBean();
		
			
		Integer orgFromId = bean.getOrgFromId();
		int orgToId = bean.getOrgToId();
		String productName = bean.getProductName() == null? "" : bean.getProductName();
		String description = bean.getDescription() == null? "" : bean.getDescription();
		String barcode = bean.getBarCode() == null? "" : bean.getBarCode();
				
		Integer preOrgFromId = (Integer)request.getSession().getAttribute(Constants.PRE_ORG_FROM_ID);
		preOrgFromId = preOrgFromId == null?0:preOrgFromId;
		HashMap<Integer, StockMovementBean> stockProductList = (HashMap<Integer, StockMovementBean>) request.getSession().getAttribute(Constants.STOCK_PRODUCT_LIST);
		ArrayList<StockMovementBean> stockList = (ArrayList<StockMovementBean>) request.getSession().getAttribute(Constants.MATERIAL_MOVEMENT_ITEMS);
		
		stockProductList = stockProductList == null? new HashMap<Integer, StockMovementBean>(): stockProductList;
		stockList = stockList == null? new ArrayList<StockMovementBean>():stockList;
		
		try 
		{
        	ArrayList<MProduct> productList = MMovementManager.getAvailableProducts(ctx, orgFromId, productName,
					description, barcode, null);
        	
			stockList = MMovementManager.convertToBeanList(ctx,stockList, stockProductList, productList, preOrgFromId, orgFromId, orgToId, null);
		}
        catch (ProductNotFoundException e)
        {
        	postGlobalError("error.product.not.found", e.getMessage(),request);
        }
        
        request.getSession().setAttribute(Constants.MATERIAL_MOVEMENT_ITEMS, stockList);
        request.getSession().setAttribute(Constants.STOCK_PRODUCT_LIST, stockProductList);  
        request.getSession().setAttribute(Constants.PRE_ORG_FROM_ID, orgFromId);
        return mapping.findForward(VIEW_STOCK);
			
	}
	
	public static final String VIEW_OR_EDIT_MMOVEMENT = "viewOrEditMMovement";
	public ActionForward viewOrEditMMovement (ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd = init(mapping, form, request, response);
        
        if (fwd != null)
        {
            return fwd;
        }
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        StockMovementForm stockForm = (StockMovementForm)form;
        StockMovementBean bean = (StockMovementBean)stockForm.getBean();
        
        return null;
    }
	
	public static final String VIEW_MATERIAL_MOVEMENT_HISTORY="viewMMovementHistory";
    public ActionForward viewMMovementHistory(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        DefaultForm sf = (DefaultForm)form;
        StockMovementBean bean = (StockMovementBean) sf.getBean();
        
        Properties ctx =TmkJSPEnv.getCtx(request);
        ArrayList list = MMovementManager.viewMMovementHistory(ctx, bean.getDocStatus(), bean.getMonth(), bean.getYear());
        request.setAttribute(Constants.MATERIAL_MOVEMENT_LIST, list);
        return mapping.findForward(VIEW_MATERIAL_MOVEMENT_HISTORY);
    }
	
	public static final String VIEW_MATERIAL_MOVEMENT = "viewMMovement";
	public ActionForward viewMMovement(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException
	{
		ActionForward fwd = init(mapping, form, request, response);
		
		if (fwd != null)
		{
			return fwd;
		}
		
		Properties ctx = TmkJSPEnv.getCtx(request);
		StockMovementForm stockForm = (StockMovementForm)form;
		StockMovementBean bean = (StockMovementBean)stockForm.getBean();
				
		int orgId = Env.getAD_Org_ID(ctx);
		String docStatus = bean.getDocStatus();
		String f_Date = bean.getFromDate();
		String t_Date = bean.getToDate();
		String docNo = bean.getDocumentNo();
		Timestamp fromDate = TimestampConvertor.getCurrentDateTimeTimestamp();
		Timestamp toDate = TimestampConvertor.getCurrentDateTimestamp();
		
		if (f_Date != null)
		{
			fromDate = TimestampConvertor.getTimestamp(f_Date, TimestampConvertor.BIRTH_DATE);
		}
		if (t_Date!=null)
		{
			toDate = TimestampConvertor.getTimestamp(t_Date, TimestampConvertor.BIRTH_DATE);
		}
		
		ArrayList<StockMovementBean> m_list = MMovementManager.getMovementByOrgFrom(ctx,orgId, docStatus, docNo, fromDate, toDate);
		
		request.getSession().setAttribute(Constants.MATERIAL_MOVEMENT, m_list);
		return mapping.findForward(VIEW_MATERIAL_MOVEMENT);
		
	}
		
	public static final String VIEW_MOVE_CONFIRM = "viewMoveConfirm";
	public ActionForward viewMoveConfirm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws ApplicationException
	{
		ActionForward fwd = init(mapping, form, request, response);
		
		if (fwd != null)
		{
			return fwd;
		}
        
		Properties ctx = TmkJSPEnv.getCtx(request);
		
		int orgToId = Env.getAD_Org_ID(ctx);
		
		ArrayList<StockMovementBean> m_list = MMovementManager.getMoveConfirmByOrgTo(ctx, orgToId, null);
		
		request.getSession().setAttribute(Constants.MOVE_CONFIRM, m_list);
		
		return mapping.findForward(VIEW_MOVE_CONFIRM);
		
	}
	
	public static final String VIEW_MATERIAL_MOVEMENT_LINES = "viewMMovementLines";
	public ActionForward viewMMovementLines(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException
	{
		ActionForward fwd = init(mapping, form, request, response);
		
		if (fwd != null)
		{
			return fwd;
		}
        
		Properties ctx = TmkJSPEnv.getCtx(request);
		StockMovementForm stockForm = (StockMovementForm)form;
		StockMovementBean bean = (StockMovementBean)stockForm.getBean();
		int movementId = bean.getMovementId();
		
		StockMovementBean movementBean = MMovementManager.getBean(ctx, movementId);
		ArrayList<StockMovementBean> m_list = MMovementManager.getMovementLines(ctx,movementId);
		
		request.getSession().setAttribute(Constants.MMOVEMENT, movementBean);
		request.getSession().setAttribute(Constants.MATERIAL_MOVEMENT_LINES, m_list);
		return mapping.findForward(VIEW_MATERIAL_MOVEMENT_LINES);
	}
	
	public static final String VIEW_MATERIAL_MOVEMENT_LINES_HISTORY = "viewMoveLinesHistory";
	public ActionForward viewMoveLinesHistory(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException
	{
		ActionForward fwd = init(mapping, form, request, response);
		
		if (fwd != null)
		{
			return fwd;
		}
        
		Properties ctx = TmkJSPEnv.getCtx(request);
		StockMovementForm stockForm = (StockMovementForm)form;
		StockMovementBean bean = (StockMovementBean)stockForm.getBean();
		int movementId = bean.getMovementId();
		
		StockMovementBean movementBean = MMovementManager.getBean(ctx, movementId);
		ArrayList<StockMovementBean> m_list = MMovementManager.getMovementLines(ctx,movementId);
		
		request.getSession().setAttribute(Constants.MMOVEMENT, movementBean);
		request.getSession().setAttribute(Constants.MATERIAL_MOVEMENT_LINES, m_list);
		return mapping.findForward(VIEW_MATERIAL_MOVEMENT_LINES_HISTORY);
	}
	
	public static final String VIEW_MOVE_CONFIRM_LINES = "viewMoveConfirmLines";
	public ActionForward viewMoveConfirmLines(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException
	{
		ActionForward fwd = init(mapping, form, request, response);
		
		if (fwd != null)
		{
			return fwd;
		}
        
		Properties ctx = TmkJSPEnv.getCtx(request);
		StockMovementForm stockForm = (StockMovementForm)form;
		StockMovementBean bean = (StockMovementBean)stockForm.getBean();
		int movementId = bean.getMovementId();
		int moveConfirmId = bean.getMoveConfirmId();
		StockMovementBean movementBean = MMovementManager.getBean(ctx, movementId);
		movementBean.setMoveConfirmId(moveConfirmId);
		ArrayList<StockMovementBean> m_list = MMovementManager.getMovementLines(ctx,movementId);
		request.getSession().setAttribute(Constants.MMOVEMENT, movementBean);
		request.getSession().setAttribute(Constants.MOVE_CONFIRM_LINES, m_list);
		return mapping.findForward(VIEW_MOVE_CONFIRM_LINES);
	}
	
	public static final String CREATE_MATERIAL_MOVEMENT = "createMMovement";
	public ActionForward createMMovement(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException, IOException
	{
		ActionForward fwd = init(mapping, form, request, response);
		
		if (fwd != null)
		{
			return fwd;
		}
		Properties ctx = TmkJSPEnv.getCtx(request);
		StockMovementForm stockForm = (StockMovementForm)form;
		StockMovementBean stockMovementBean = (StockMovementBean)stockForm.getBean();
		
		String movementCart = Constants.MMOVEMENT_CART;
        String movementCartItems = Constants.MMOVEMENT_CART_ITEMS;
        
        String description = request.getParameter("description");
        String priceListIdAsStr = request.getParameter("priceListId");
        String movementIdAsStr = request.getParameter("movementId");
        String orgFromIdAsStr = request.getParameter("orgFromId");
        String orgToIdAsStr = request.getParameter("orgToId");
        int priceListId = Integer.parseInt(priceListIdAsStr);
        int movementId = Integer.parseInt(movementIdAsStr);
        Integer orgFromId = Integer.parseInt(orgFromIdAsStr);
        Integer orgToId = Integer.parseInt(orgToIdAsStr);
        
        stockMovementBean.setMovementId(movementId);
        stockMovementBean.setDescription(description);
        stockMovementBean.setPriceListId(priceListId);
        stockMovementBean.setOrgFromId(orgFromId);
        stockMovementBean.setOrgToId(orgToId);
        stockForm.populate(stockMovementBean);
        
        Trx trx = Trx.get(TrxPrefix.getPrefix(), true);
        String docNo = null;
        String docStatus = null;
        String docInfo = null;
        
        ArrayList<ItemBean> itemList = (ArrayList<ItemBean>) request.getSession().getAttribute(Constants.MMOVEMENT_CART_ITEMS);
        
        if(stockMovementBean.getMovementId() == null || stockMovementBean.getMovementId() == 0)
        {
            MMovement mmovement = MMovementManager.createInventoryMove(ctx, stockMovementBean.getDescription(), null);
            docNo = mmovement.getDocumentNo();
            docInfo = mmovement.getDocumentInfo();
            docStatus = mmovement.getDocStatus();
            stockMovementBean.setDocumentNo(docNo);
            stockMovementBean.setMovementId(mmovement.getM_Movement_ID());
            
        }
        else
        {
            MMovement mmovement2 = new MMovement(ctx, stockMovementBean.getMovementId(), null);
            mmovement2.setDescription(stockMovementBean.getDescription());
            docNo = mmovement2.getDocumentNo();
            docInfo = mmovement2.getDocumentInfo();
            docStatus = mmovement2.getDocStatus();
            stockMovementBean.setDocumentNo(docNo);
            stockMovementBean.setMovementId(mmovement2.getM_Movement_ID());
            mmovement2.save();
            
            MMovementManager.deleteMovementLines(ctx, mmovement2.getM_Movement_ID());
            
        }
       
		try
        {
        	trx.start();
        	
        	for (ItemBean itemBean : itemList)
            {
                MMovementManager.createOrUpdateMaterialMovementLine(ctx, stockMovementBean.getMovementId(), itemBean.getProductId(), itemBean.getQtyToMove(), stockMovementBean.getOrgFromId(), stockMovementBean.getOrgToId(), trx.getTrxName());
            }
        	       	        
	        trx.commit();
		}
        catch (OperationException e)
        {
        	trx.rollback();
        	postGlobalError("error.process", e.getMessage(), request);
        }
        finally
        {
        	trx.close();
        }
        
        stockForm.populate(stockMovementBean);
        
        request.getSession().setAttribute(Constants.DOC_NO, docNo);
        request.getSession().setAttribute(Constants.DOC_STATUS, docStatus);
        request.getSession().setAttribute(Constants.MMOVEMENT_ID, stockMovementBean.getMovementId());
        request.getSession().setAttribute(Constants.DESCRIPTION, description);
        
        Env.setContext(ctx, Constants.MMOVEMENT_ID, stockMovementBean.getMovementId());
        
   
        if((MMovement.DOCSTATUS_Drafted).equals(docStatus))
        {
            docStatus = "DRAFTED";
        }
        
        if((MMovement.DOCSTATUS_Completed).equals(docStatus))
        {
            docStatus = "COMPLETED";
        }
        
        if((MMovement.DOCSTATUS_Closed).equals(docStatus))
        {
            docStatus = "CLOSED";
        }
        
        if((MMovement.DOCSTATUS_InProgress).equals(docStatus))
        {
            docStatus = "IN PROGRESS";
        }
        
        if((MMovement.DOCSTATUS_Approved).equals(docStatus))
        {
            docStatus = "APPROVED";
        }
        
        
        StringBuffer sb = new StringBuffer();
        sb.append("{");
        sb.append("movementId: \"" + stockMovementBean.getMovementId() + "\"");
        sb.append(", docNo: \"" + docNo + "\"");
        sb.append(", docStatus: \"" + docStatus + "\"");
        sb.append(", docInfo: \"" + docInfo + "\"");
        sb.append(", description: \"" + stockMovementBean.getDescription() + "\"");
        sb.append("}");
        
        PrintWriter writer = response.getWriter();        
        writer.print( sb.toString() );
        writer.flush();
        writer.close();
        
        return null;
	}
	
	public static final String COMPLETE_MATERIAL_MOVEMENT = "completeMMovement";
	public ActionForward completeMMovement(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ActionForward fwd = init(mapping, form, request, response);
		
		if (fwd != null)
		{
			return fwd;
		}
		Properties ctx = TmkJSPEnv.getCtx(request);
		
		StockMovementForm stockForm = (StockMovementForm)form;
		StockMovementBean stockMovementBean = (StockMovementBean)stockForm.getBean();
		
		ArrayList<ItemBean> itemList = (ArrayList<ItemBean>) request.getSession().getAttribute(Constants.MMOVEMENT_CART_ITEMS);
				
        Trx trx = Trx.get(TrxPrefix.getPrefix(), true);

        try
		{
            if(stockMovementBean.getMovementId() == null || stockMovementBean.getMovementId() == 0)
            {
                MMovement mmovement = MMovementManager.createInventoryMove(ctx, stockMovementBean.getReference(), null);
                stockMovementBean.setMovementId(mmovement.get_ID());
                stockMovementBean.setDocumentNo(mmovement.getDocumentNo());
                stockMovementBean.setDocStatus(mmovement.getDocStatus());
                mmovement.save();
                
                trx.start();
                
                for (ItemBean itemBean : itemList)
                {
                    MMovementManager.createOrUpdateMaterialMovementLine(ctx, stockMovementBean.getMovementId(), itemBean.getProductId(), itemBean.getQtyToMove(), stockMovementBean.getOrgFromId(), stockMovementBean.getOrgToId(), trx.getTrxName());
                }
                
                trx.commit();
                
                MMovementManager.completeMaterialMovement(ctx, stockMovementBean.getMovementId(), trx.getTrxName());
            }
            else
            {
                MMovementManager.deleteMovementLines(ctx, stockMovementBean.getMovementId());
                
                trx.start();
                
                for (ItemBean itemBean : itemList)
                {
                    MMovementManager.createOrUpdateMaterialMovementLine(ctx, stockMovementBean.getMovementId(), itemBean.getProductId(), itemBean.getQtyToMove(), stockMovementBean.getOrgFromId(), stockMovementBean.getOrgToId(), trx.getTrxName());
                }
                
                trx.commit();
                
                MMovementManager.completeMaterialMovement(ctx, stockMovementBean.getMovementId(), trx.getTrxName());
                
            }
		}
        catch (OperationException e)
        {
        	trx.rollback();
        	trx.close();
        }
        
        ArrayList list = MMovementManager.getMovementLines(ctx, stockMovementBean.getMovementId());
		request.getSession().setAttribute(Constants.MATERIAL_MOVEMENT, list);
		
		MMovementManager.clearCart(ctx, request);
		return mapping.findForward(VIEW_MATERIAL_MOVEMENT);
				
	}
	
	public static final String COMPLETE_MOVE_CONFIRM = "completeMoveConfirm";
	public ActionForward completeMoveConfirm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws ApplicationException
	{
		ActionForward fwd = init(mapping, form, request, response);
		
		if (fwd != null)
		{
			return fwd;
		}
		Properties ctx = TmkJSPEnv.getCtx(request);
		
		StockMovementForm stockForm = (StockMovementForm)form;
		StockMovementBean stockMovementBean = (StockMovementBean)stockForm.getBean();
				
		int moveConfirmId = stockMovementBean.getMoveConfirmId();
		
        
        Trx trx = Trx.get(TrxPrefix.getPrefix(), true);
        ArrayList<StockMovementBean> bean = null;
        try
		{
        	trx.start();
        	MMovementManager.completeMoveConfirm(ctx, moveConfirmId, trx.getTrxName());
        	
        	trx.commit();
		}
        catch (OperationException e)
        {
        	trx.rollback();
        	trx.close();
        }
        ArrayList<StockMovementBean> list = (ArrayList<StockMovementBean>)request.getSession().getAttribute(Constants.MOVE_CONFIRM);
    	bean = MMovementManager.getConfirmList(ctx, list, moveConfirmId);
		
		request.getSession().setAttribute(Constants.MOVE_CONFIRM, bean);
		return mapping.findForward(VIEW_MOVE_CONFIRM);
				
	}
	
	public ActionForward setOrgFrom(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws ApplicationException
	{
		ActionForward fwd = init(mapping, form, request, response);
		
		if (fwd != null)
		{
			return fwd;
		}
		Properties ctx = TmkJSPEnv.getCtx(request);
		
		StockMovementForm stockForm = (StockMovementForm)form;
		StockMovementBean stockMovementBean = (StockMovementBean)stockForm.getBean();
		Integer orgFromId = stockMovementBean.getOrgFromId();
		
		if (orgFromId != null)
		{
			request.getSession().setAttribute(Constants.ORG_FROM_ID, orgFromId);
		}
		
		return null;
	}
	
	
	public ActionForward setQtyToMove(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException
	{
		ActionForward fwd = init(mapping, form, request, response);
		
		if (fwd != null)
		{
			return fwd;
		}
		Properties ctx = TmkJSPEnv.getCtx(request);
		String qtyToMove = request.getParameter("qtyToMove");
		String index = request.getParameter("index");
		String salesPriceListId = request.getParameter("salesPriceListId");
		
		Double qty = Double.valueOf(qtyToMove);
		BigDecimal qtyToMov = BigDecimal.valueOf(qty);
		Integer ind = Integer.valueOf(index);
		Integer salesPrId = 0;
		if (salesPriceListId != null)
	    {
		    salesPrId = Integer.valueOf(salesPriceListId);
	    }
		ArrayList<StockMovementBean> stockList = (ArrayList<StockMovementBean>)request.getSession().getAttribute(Constants.MATERIAL_MOVEMENT_ITEMS);
		BigDecimal stockValue = MMovementManager.setQtyToMove(ctx, qtyToMov, ind, salesPrId, stockList);
		
		return null;
	}
	
	
	//--------------------------------------------------------------------------------------------------------------//
	
	public static final String ADD_TO_MMOVEMENT_CART = "addToMMovementCart";
    public ActionForward addToMMovementCart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd = init(mapping, form, request, response);
        
        if (fwd != null)
        {
            return fwd;
        }
        Properties ctx = TmkJSPEnv.getCtx(request);
        
        StockMovementForm stockForm = (StockMovementForm)form;
        StockMovementBean stockMovementBean = (StockMovementBean)stockForm.getBean();
      
        String movementCart = Constants.MMOVEMENT_CART;
        String movementCartItems = Constants.MMOVEMENT_CART_ITEMS;
        Trx trx = Trx.get(TrxPrefix.getPrefix(),true);
        
        MMovementCartBean mmovementBean = (MMovementCartBean) request.getSession().getAttribute(movementCart);
        
        try
        {
            if(stockMovementBean.getProductId()==null && stockMovementBean.getBarCode()==null)
            {
                postGlobalError("error.barcode.required", request);
                return mapping.getInputForward();
            }
            
            BigDecimal qty = stockMovementBean.getQtyToMove();
            if(qty != null && qty.doubleValue() < 0.0)
            {
                throw new InputQuantityLessThanZeroException("");
            }
            
            
            trx.start();
            mmovementBean = StockManager.addToMovementCart(ctx, stockMovementBean, mmovementBean, false, Boolean.parseBoolean(stockMovementBean.getIfAdd()));
            trx.commit();
        }
        catch (InputQuantityLessThanZeroException e)        
        {
            postGlobalError("error.invalid.inputQty", request);
            trx.rollback();
            return mapping.getInputForward();
        }
        catch (ProductNotFoundException e)
        {
            postGlobalError("error.product.not.found", e.getMessage(), request);
            trx.rollback();
            return mapping.getInputForward();
        }
        catch(QuantityNotAvailableException e)
        {
            postGlobalError("error.quantity.notAvailable", e.getMessage(), request);
            trx.rollback();
            return mapping.getInputForward();
        }
        
        catch(ProductNotOnPriceListException e)
        {
            postGlobalError("error.product.price.not.found", e.getMessage(), request);
            trx.rollback();
            return mapping.getInputForward();
        }
        catch(OperationException e)
        {
            postGlobalError("error.inventory.not.available", e.getMessage(), request);
            trx.rollback();
            return mapping.getInputForward();
        }
        finally
        {
            trx.close();
        }
        
        int priceListId = Env.getContextAsInt(ctx, UdiConstants.PRICELIST_CTX_PARAM);       
        String currSymboleSales = PriceListManager.getCurrency(ctx, priceListId);
        request.setAttribute(Constants.CURRENCY_SYMBOLE,currSymboleSales);        
        
        request.getSession().setAttribute(movementCart, mmovementBean);
        request.getSession().setAttribute(movementCartItems, mmovementBean.getItems());
        
        return mapping.findForward(ADD_TO_MMOVEMENT_CART);
    }
    
    public ActionForward addProduct(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException, IOException, ParseException
    {
        // Bug fix for null pointer
        StockMovementForm stockForm = (StockMovementForm)form;
        if(stockForm.getQtyToMove() == null)
        {
            stockForm.setQtyToMove("1");
        }
        
        addToMMovementCart(mapping,form,request,response);
        
        return getMMovementCart(mapping,form,request,response);
    }
	
    
    public static final String INCREMENT_QTY = "incrementQty";
    public ActionForward incrementQty(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        StockMovementForm stockForm = (StockMovementForm)form;
        StockMovementBean stockMovementBean = (StockMovementBean)stockForm.getBean();
        stockMovementBean.setQtyToMove(new BigDecimal(1));
        
        addToMMovementCart(mapping,form,request,response);
        return getMMovementCart(mapping,form,request,response);
    }
    
    public static final String DECREMENT_QTY = "decrementQty";
    public ActionForward decrementQty(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        StockMovementForm stockForm = (StockMovementForm)form;
        StockMovementBean stockMovementBean = (StockMovementBean)stockForm.getBean();
        stockMovementBean.setQtyToMove(new BigDecimal(1));
        
        addToMMovementCart(mapping,form,request,response);
        return getMMovementCart(mapping,form,request,response);
    }
    
    public ActionForward getMMovementCart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws OperationException, IOException, ParseException
    {
        DefaultForm df = (DefaultForm) form;
        String orderType = df.getOrderType();
        
        String mmovementCart = MMovementManager.getMMovementCartAsHTML(request);        
        
        PrintWriter writer = response.getWriter();
        writer.write(mmovementCart);
        
        
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
    
    public static final String UPDATE_QTY = "updateQty";
    public ActionForward updateQty(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        Properties ctx=TmkJSPEnv.getCtx(request);
        
        // Bug fix for null pointer
        StockMovementForm stockForm = (StockMovementForm)form;
        Trx trx = Trx.get(TrxPrefix.getPrefix(),true);
        
        if(stockForm.getQtyToMove() == null)
        {
            stockForm.setQtyToMove("1");
        }
        
        StockMovementBean stockMovementBean = (StockMovementBean)stockForm.getBean();
        Integer product_id = stockMovementBean.getProductId();
        Integer quantity = stockMovementBean.getQtyToMove().intValue();
        Integer priceListId = stockMovementBean.getPriceListId();
        
        ArrayList items = (ArrayList) request.getSession().getAttribute(Constants.MMOVEMENT_CART_ITEMS);
        
        try
        {
            trx.start();
            StockManager.updateItemFromMMovementList(ctx, priceListId, items, product_id, quantity);
            trx.commit();
        }
        catch(OperationException e)
        {
            postGlobalError("error.inventory.not.available", e.getMessage(), request);
            trx.rollback();
            return mapping.getInputForward();
        }
        finally
        {
            trx.close();
        }
        
        return getMMovementCart(mapping,form,request,response);
    }
    
    public static final String UPDATE_NO_OF_PACK = "updateNoOfPack";
    public ActionForward updateNoOfPack(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        Properties ctx=TmkJSPEnv.getCtx(request);
        
        // Bug fix for null pointer
        StockMovementForm stockForm = (StockMovementForm)form;
        
        if(stockForm.getNoOfPack() == null)
        {
            stockForm.setNoOfPack("1");
        }
        
        StockMovementBean stockMovementBean = (StockMovementBean)stockForm.getBean();
        Integer product_id = stockMovementBean.getProductId();
        Integer noOfPack = stockMovementBean.getNoOfPack();
        Integer priceListId = stockMovementBean.getPriceListId();
        
        ArrayList items = (ArrayList) request.getSession().getAttribute(Constants.MMOVEMENT_CART_ITEMS);
        StockManager.updateNoOfPack(ctx, priceListId, items, product_id, noOfPack);
        
        return getMMovementCart(mapping,form,request,response);
    }
    
    public static final String RELOAD_BARCODE_CART = "reloadBarcodeCart";    
    public ActionForward reloadBarcodeCart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws OperationException, IOException, ParseException, ApplicationException
    {
        ActionForward fwd=init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        StockMovementForm stockForm = (StockMovementForm)form;
        StockMovementBean stockMovementBean = (StockMovementBean)stockForm.getBean();

        String movementCart = Constants.MMOVEMENT_CART;
        String movementCartItems = Constants.MMOVEMENT_CART_ITEMS;
        
        MMovementCartBean cartBean = (MMovementCartBean) request.getSession().getAttribute(movementCart);            
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
                qty = itemBean.getQtyToMove();
            }
                        
        }
        cartBean = StockManager.addToMovementCart(ctx, stockMovementBean, cartBean, false, Boolean.parseBoolean(stockMovementBean.getIfAdd()));
        
        ArrayList items = (ArrayList) request.getSession().getAttribute(movementCartItems);
        StockManager.updateItemFromMMovementList(ctx, priceListId, items, productId, qty.intValue());
        
        return getMMovementCart(mapping,form,request,response);
    }
    
    public ActionForward clearCart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    { 
        Properties ctx = TmkJSPEnv.getCtx(request);
        MMovementManager.clearCart(ctx, request);        
        return getMMovementCart(mapping,form,request,response);   
    }
    
    
    public ActionForward createNewStockTransfer(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        MMovementManager.clearCart(ctx, request);
        return new ActionForward("/ViewStock.do");
    }
    
    public ActionForward deleteInventoryMove(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx =TmkJSPEnv.getCtx(request);
        StockMovementForm smf = (StockMovementForm) form;
        StockMovementBean smb = (StockMovementBean) smf.getBean();
        MMovementManager.deleteInventoryMove(ctx, smb.getMovementId());        
        
        return new ActionForward("/StockMovementAction.do?action=viewMMovementHistory");
    }
    
    public ActionForward editInventoryMove(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx =TmkJSPEnv.getCtx(request);
        StockMovementForm smf = (StockMovementForm) form;
        StockMovementBean smb = (StockMovementBean) smf.getBean();
        
        String movementCart = Constants.MMOVEMENT_CART;
        String movementCartItems = Constants.MMOVEMENT_CART_ITEMS;
        
        
        int priceListId = PriceListManager.getDefaultPriceListId(ctx, false);
        String currSymboleSales = PriceListManager.getCurrency(ctx, priceListId);
        request.setAttribute(Constants.CURRENCY_SYMBOLE,currSymboleSales); 
        
        MMovementCartBean mmovementBean = MMovementManager.getMMovementCartBean(ctx, smb.getMovementId(), priceListId);
        
        Env.setContext(ctx, Constants.MMOVEMENT_ID, smb.getMovementId());
        request.getSession().setAttribute(Constants.MMOVEMENT_ID, smb.getMovementId());
        request.getSession().setAttribute(movementCart, mmovementBean);
        request.getSession().setAttribute(movementCartItems, mmovementBean.getItems());
        return new ActionForward("/ViewStock.do");
    }
}
