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
 *  
 *  @author shameem
 */

package org.posterita.struts.pos;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;
import org.apache.struts.util.MessageResources;
import org.compiere.model.MInventory;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.posterita.Constants;
import org.posterita.beans.InventoryCartBean;
import org.posterita.beans.InventoryLineBean;
import org.posterita.beans.ItemBean;
import org.posterita.businesslogic.POSTerminalManager;
import org.posterita.businesslogic.performanceanalysis.ReportManager;
import org.posterita.businesslogic.stock.InventoryCartManager;
import org.posterita.businesslogic.stock.InventoryManager;
import org.posterita.businesslogic.stock.StockManager;
import org.posterita.core.TabularReport;
import org.posterita.core.TmkJSPEnv;
import org.posterita.core.TrxPrefix;
import org.posterita.exceptions.ApplicationException;
import org.posterita.exceptions.CannotCreateInventoryLineException;
import org.posterita.exceptions.InputQuantityLessThanZeroException;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.ProductNotFoundException;
import org.posterita.exceptions.ProductNotOnPriceListException;
import org.posterita.exceptions.QuantityNotAvailableException;
import org.posterita.exceptions.UOMValuePrecisionNotValidException;
import org.posterita.form.InventoryLineForm;
import org.posterita.struts.core.DefaultForm;

import com.lowagie.text.DocumentException;

public class InventoryCartAction extends POSDispatchAction
{
    private static final CLogger logger = CLogger.getCLogger(InventoryCartAction.class);
    
    public static final String INCREMENT_QTY = "incrementQty";
    public ActionForward incrementQty(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        // Bug fix for null pointer
        InventoryLineForm of = (InventoryLineForm) form;
        if(of.getQuantity() == null)
        {
            of.setQuantity("1");
        }
        
        addToInventoryCart(mapping,form,request,response);
        return getInventoryCart(mapping,form,request,response);
    }
    
    public static final String DECREMENT_QTY = "decrementQty";
    public ActionForward decrementQty(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        // Bug fix for null pointer
        InventoryLineForm of = (InventoryLineForm) form;
        if(of.getQuantity() == null)
        {
            of.setQuantity("1");
        }
        
        addToInventoryCart(mapping,form,request,response);
        return getInventoryCart(mapping,form,request,response);
    }
    
    public ActionForward addProduct(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException, IOException, ParseException
    {
        // Bug fix for null pointer
        InventoryLineForm of = (InventoryLineForm) form;
        if(of.getQtyCount() == null)
        {
            of.setQtyCount("1");
        }
        
        addToInventoryCart(mapping,form,request,response);
        
        return getInventoryCart(mapping,form,request,response);
    }
    
    public static final String UPDATE_QTY = "updateQty";
    public ActionForward updateQty(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        Properties ctx=TmkJSPEnv.getCtx(request);
                
        // Bug fix for null pointer
        InventoryLineForm of = (InventoryLineForm) form;
        if(of.getQtyCount() == null)
        {
            of.setQtyCount("1");
        }
        
        InventoryLineBean bean = (InventoryLineBean) of.getBean();
        Integer product_id = bean.getProductId();
        bean.setQtyCount(new BigDecimal(of.getQtyCount()));
        Integer priceListId = bean.getPriceListId();
        of.populate(bean);
        
        ArrayList<ItemBean> items = (ArrayList<ItemBean>) request.getSession().getAttribute(Constants.INVENTORY_CART_ITEMS);
        StockManager.updateItemFromInventoryList(ctx, priceListId, items, product_id, bean.getQtyCount().intValue());
        request.getSession().setAttribute(Constants.INVENTORY_CART_ITEMS, items);
        
        return getInventoryCart(mapping, form, request, response);
    }
        
    
    public static final String ADD_TO_INVENTORY_CART = "addToInventoryCart";
    public ActionForward addToInventoryCart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException
    {
        Properties ctx = TmkJSPEnv.getCtx(request);
        InventoryLineForm df = (InventoryLineForm) form;
        InventoryLineBean bean = (InventoryLineBean) df.getBean();
        
        String inventoryCart = Constants.INVENTORY_CART;
        String inventoryCartItems = Constants.INVENTORY_CART_ITEMS;
             
        InventoryCartBean cartBean = (InventoryCartBean) request.getSession().getAttribute(inventoryCart);
        
        try
        {
            if(bean.getProductId()==null && bean.getBarCode()==null)
            {
                postGlobalError("error.barcode.required", request);
                return mapping.getInputForward();
            }
            
            BigDecimal qty = bean.getQtyCount();
            if(qty != null && qty.doubleValue() < 0.0)
            {
                throw new InputQuantityLessThanZeroException("");
            }
            
            cartBean = StockManager.addToInventoryCart(ctx, bean, cartBean,true,Boolean.parseBoolean(bean.getIfAdd()));            
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
        
        String currSymboleSales = POSTerminalManager.getDefaultSalesCurrency(ctx).getCurSymbol();
        request.setAttribute(Constants.CURRENCY_SYMBOLE,currSymboleSales);
        
        
        request.getSession().setAttribute(inventoryCart, cartBean);
        request.getSession().setAttribute(inventoryCartItems, cartBean.getItems());
        
        df.setQtyAndItem("");
        
        return mapping.findForward(ADD_TO_INVENTORY_CART);
    }
    
    public ActionForward getInventoryCart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws OperationException, IOException, ParseException
    {
        DefaultForm df = (DefaultForm) form;
        
        String shoppingcartHTML = InventoryCartManager.getInventoryCartAsHTML(request);       
        
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

    public static final String RELOAD_CART = "reloadCart";
    public ActionForward reloadCart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {       
        return getInventoryCart(mapping,form,request,response);
    }
    
    public ActionForward clearCart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    { 
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        Object inventoryIdAsStr = request.getSession().getAttribute(Constants.INVENTORY_ID);
        Integer inventoryId = 0;
        
        if(inventoryIdAsStr != null)
        {
            inventoryId = Integer.parseInt(inventoryIdAsStr.toString());
        }
        
        
        if(inventoryId != 0)
        {
            MInventory inventory = new MInventory(ctx, inventoryId,null);
            inventory.delete(true);
        }
        
        InventoryCartManager.clearInventorycart(ctx, request);
        
        return getInventoryCart(mapping, form, request, response);       
    }
    
    public static final String AUTO_SAVE_INVENTORY_LINES = "autoSaveInventoryLines";
    public ActionForward autoSaveInventoryLines(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException, IOException
    {
        ActionForward fwd=init(mapping,form,request,response);
        
        if(fwd!=null)
        {
            return fwd;
        }
        
        String inventoryCart = Constants.INVENTORY_CART;
        String inventoryCartItems = Constants.INVENTORY_CART_ITEMS;
             
        InventoryCartBean cartBean = (InventoryCartBean) request.getSession().getAttribute(inventoryCart);
        
        Properties ctx=TmkJSPEnv.getCtx(request);
        
        String description = request.getParameter("description");
        String priceListIdAsStr = request.getParameter("priceListId");
        String inventoryIdAsStr = request.getParameter("inventoryId");
        int priceListId = Integer.parseInt(priceListIdAsStr);
        int inventoryId = Integer.parseInt(inventoryIdAsStr);
        
        DefaultForm df = (DefaultForm)form;
        InventoryLineBean bean = (InventoryLineBean) df.getBean();
        bean.setPriceListId(priceListId);
        bean.setDescription(description);
        bean.setInventoryId(inventoryId);
        df.populate(bean);
        
        String docNo = null;
        String docStatus = null;
        String docInfo = null;
        
        MInventory inventory=null;
        Trx trx = Trx.get(TrxPrefix.getPrefix(),true);
        
        ArrayList<ItemBean> items = (ArrayList<ItemBean>) request.getSession().getAttribute(Constants.INVENTORY_CART_ITEMS);

        if(bean.getInventoryId() == null || bean.getInventoryId() == 0)
        {
            inventory= InventoryCartManager.createInventory(ctx,bean.getDescription(),null);
            docNo = inventory.getDocumentNo();
            docStatus = inventory.getDocStatus();
            docInfo = inventory.getDocumentInfo();            
            bean.setDocumentNo(docNo);
            bean.setInventoryId(inventory.get_ID());
        }
        else
        {
            MInventory inventory2 = new MInventory(ctx, bean.getInventoryId(),null);
            inventory2.setDescription(description);
            docNo = inventory2.getDocumentNo();
            docStatus = inventory2.getDocStatus();
            docInfo = inventory2.getDocumentInfo();
            inventory2.save();
            
            InventoryCartManager.deleteInventoryLines(ctx, inventory2.getM_Inventory_ID());
        }
        
        
        try
        {   
            trx.start();
            
            for (ItemBean itemBean : items)
            {
                InventoryCartManager.addInventoryLine(ctx, bean.getInventoryId(), itemBean.getProductId() , itemBean.getQtyBook(), itemBean.getQtyCount(), itemBean.getQtyCsv(), true, trx.getTrxName()); 
            }          
            
            trx.commit();
        }
        catch(CannotCreateInventoryLineException e)
        {
            trx.rollback();
            ArrayList list = InventoryManager.getInventoryLines(ctx,bean.getInventoryId());
            request.getSession().setAttribute(Constants.INVENTORY_LINE_LIST,list);
            request.getSession().setAttribute(Constants.INVENTORY_ID,bean.getInventoryId());
            postGlobalError("error.cannot.create.inventory.line",request);
            return mapping.getInputForward();
        }
        catch(ProductNotFoundException e)
        {
            postGlobalError("error.product.name.required",request);
            ArrayList list = InventoryManager.getInventoryLines(ctx,bean.getInventoryId());
            request.getSession().setAttribute(Constants.INVENTORY_LINE_LIST,list);
            request.getSession().setAttribute(Constants.INVENTORY_ID,bean.getInventoryId());
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
        
        df.populate(bean);
        ArrayList list = InventoryManager.getInventoryLines(ctx,bean.getInventoryId());
        request.getSession().setAttribute(Constants.DOC_NO, docNo);
        request.getSession().setAttribute(Constants.DOC_STATUS, docStatus);
        request.getSession().setAttribute(Constants.INVENTORY_LINE_LIST,list);
        request.getSession().setAttribute(Constants.INVENTORY_ID, bean.getInventoryId());
        request.getSession().setAttribute(Constants.DESCRIPTION, description);
        
        Env.setContext(ctx, Constants.INVENTORY_ID, bean.getInventoryId());
        Env.setContext(ctx, Constants.DESCRIPTION, description);
        
        request.getSession().setAttribute(Constants.INVENTORY_ID, bean.getInventoryId());
        
        if((MInventory.DOCSTATUS_Drafted).equals(docStatus))
        {
            docStatus = "DRAFTED";
        }
        
        if((MInventory.DOCSTATUS_Completed).equals(docStatus))
        {
            docStatus = "COMPLETED";
        }
        
        if((MInventory.DOCSTATUS_Closed).equals(docStatus))
        {
            docStatus = "CLOSED";
        }
        
        StringBuffer sb = new StringBuffer();
        sb.append("{");
        sb.append("inventoryID: \"" + bean.getInventoryId() + "\"");
        sb.append(", docNo: \"" + docNo + "\"");
        sb.append(", docStatus: \"" + docStatus + "\"");
        sb.append(", docInfo: \"" + docInfo + "\"");
        sb.append(", description: \"" + bean.getDescription() + "\"");
        sb.append("}");
        
        PrintWriter writer = response.getWriter();        
        writer.print( sb.toString() );
        writer.flush();
        writer.close();
        
        return null;
    }
    
    public static final String GENERATE_INVENTORY_CSV = "generateInventoryCSV";
    public ActionForward generateInventoryCSV(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException, IOException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx =TmkJSPEnv.getCtx(request);
        InventoryLineForm If = (InventoryLineForm)form;
        InventoryLineBean bean = (InventoryLineBean) If.getBean();
        
        ArrayList<ItemBean> items = (ArrayList<ItemBean>) request.getSession().getAttribute(Constants.INVENTORY_CART_ITEMS);
        String reportName = InventoryCartManager.createCSVForInventory(ctx,items);
        String reportURI = ReportManager.getReportURI(reportName,request);
        response.sendRedirect(reportURI);
        
        return null;

    }
    
    public ActionForward printInventory(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException, IOException, SQLException, DocumentException, ParseException
    {
        Properties ctx = TmkJSPEnv.getCtx(request);     
        DefaultForm df = (DefaultForm) form; 
        InventoryLineBean bean = (InventoryLineBean) df.getBean();
        Trx trx = Trx.get(TrxPrefix.getPrefix(),true);
        String description = Env.getContext(ctx, Constants.DESCRIPTION);
        
        String inventoryCart = Constants.INVENTORY_CART;
        String inventoryCartItems = Constants.INVENTORY_CART_ITEMS;
             
        InventoryCartBean cartBean = (InventoryCartBean) request.getSession().getAttribute(inventoryCart);
                
        ArrayList<ItemBean> items = (ArrayList<ItemBean>) request.getSession().getAttribute(Constants.INVENTORY_CART_ITEMS);
        ArrayList<Object[]> reportData = InventoryCartManager.getInventoryData(ctx, items, trx.getTrxName());
        
        String title = description;
        String subtitle  = null;
        byte printData[] = null;
        
        String sql = "Select Name from AD_Org where AD_org_ID=?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
       
        pstmt = DB.prepareStatement(sql, null); 
        pstmt.setInt(1, Env.getAD_Org_ID(ctx));
       
        try
        {
            rs = pstmt.executeQuery();
           
            if (rs.next())
            {
                subtitle = rs.getString(1);
            }
                       
        }
        catch(SQLException e)
        {
            throw new OperationException(e);
        }
        finally 
        {
            DB.close(rs);
            DB.close(pstmt);
        }
        
        //constructing the table
        TabularReport tReport = new TabularReport(reportData);     
        tReport.setSortable(false);
        tReport.setStyle("display");
        tReport.setTitle(title);
        tReport.setSubtitle(subtitle);
        tReport.createReport();
        
        printData = tReport.getInventoryData();
        response.setContentType("application/pdf");

        OutputStream os = response.getOutputStream();
        os.write(printData);
        os.flush();
        os.close(); 
        
        return null;

    }
    
    public static final String COMPLETE_INVENTORY_ADJUSTMENT="completeInventoryAdjustment";
    public ActionForward completeInventoryAdjustment(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException, IOException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx =TmkJSPEnv.getCtx(request);
        Trx trx = Trx.get(TrxPrefix.getPrefix(),true);
        
        InventoryLineForm If = (InventoryLineForm)form;
        InventoryLineBean bean = (InventoryLineBean) If.getBean();
        
        ArrayList<ItemBean> items = (ArrayList<ItemBean>) request.getSession().getAttribute(Constants.INVENTORY_CART_ITEMS);
        
        try
        {
            
            if(bean.getInventoryId() == null || bean.getInventoryId()==0)
            {
                MInventory inventory= InventoryCartManager.createInventory(ctx,bean.getDescription(),null);
                bean.setInventoryId(inventory.get_ID());
                bean.setDocumentNo(inventory.getDocumentNo());
                bean.setDocStatus(inventory.getDocStatus());
                inventory.save();
                
                trx.start();
                
                for (ItemBean itemBean : items)
                {
                    InventoryCartManager.addInventoryLine(ctx, bean.getInventoryId(), itemBean.getProductId() , itemBean.getQtyBook(), itemBean.getQtyCount(), itemBean.getQtyCsv(), true, trx.getTrxName()); 
                }          
                
                trx.commit();
                
                InventoryManager.completeStockAdjustment(ctx,bean.getInventoryId());
            }
            else
            {           
                InventoryCartManager.deleteInventoryLines(ctx, bean.getInventoryId());
                
                trx.start();
                
                for (ItemBean itemBean : items)
                {
                    InventoryCartManager.addInventoryLine(ctx, bean.getInventoryId(), itemBean.getProductId() , itemBean.getQtyBook(), itemBean.getQtyCount(), itemBean.getQtyCsv(), true, trx.getTrxName()); 
                }          
                
                trx.commit();
                
                
                
                InventoryManager.completeStockAdjustment(ctx,bean.getInventoryId());
            }
        
        }
        catch (OperationException e)
        {
            trx.rollback();
            trx.close();
        }
        
        request.setAttribute(Constants.DOC_NO, null);
        request.setAttribute(Constants.DOC_STATUS, null);
        
        request.setAttribute("cartTotalCsv", null);
        request.setAttribute("cartTotalBook", null);
        request.setAttribute("cartTotalCount", null);
        
        ArrayList list = InventoryManager.getInventoryLines(ctx,bean.getInventoryId());        
        request.setAttribute(Constants.COMPLETED_INVENTORY_LINE_LIST, list);  
        
        request.setAttribute(Constants.INVENTORY_LINE_LIST, null);
        request.setAttribute(Constants.INVENTORY_ID, null);
        
        InventoryCartManager.clearInventorycart(ctx, request);
        
        return mapping.findForward(COMPLETE_INVENTORY_ADJUSTMENT);
    }
    
    public static final String ADD_STOCK_SHEETS = "addStockSheets";
    public ActionForward addStockSheets(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        DefaultForm df = (DefaultForm) form;        
        FormFile file = df.getFile();
        
        if(file.getFileName().trim().equals(""))
        {
            postGlobalError("error.file.not.found", request);
            return mapping.getInputForward();
        }
                
        Properties ctx =TmkJSPEnv.getCtx(request);
        Trx trx = Trx.get(TrxPrefix.getPrefix(),true);
        
        InventoryLineForm If = (InventoryLineForm)form;
        InventoryLineBean bean = (InventoryLineBean) If.getBean();
        request.getSession().removeAttribute(Constants.IMPORT_FAIL_CSV_FILE);
        
        String inventoryCart = Constants.INVENTORY_CART;
        String inventoryCartItems = Constants.INVENTORY_CART_ITEMS;
        
        ArrayList<ItemBean> items = (ArrayList<ItemBean>) request.getSession().getAttribute(inventoryCartItems);
        
        InventoryCartBean cartBean = (InventoryCartBean) request.getSession().getAttribute(inventoryCart);
        
        try
        {
            cartBean = InventoryCartManager.addItemsFromCsv(ctx, file, bean, cartBean, trx.getTrxName());
        }
        catch(Exception e)
        {
            
            String message = e.getMessage();
            
            
            if(message != null)
            {
                int index = message.indexOf("csv");
                String filename = message.substring(0,index+3);
                String error = message.substring(index+3);
                postGlobalError("error.process", error, request);
                String csvURI = ReportManager.getReportURI(filename,request); 
                request.getSession().setAttribute(Constants.IMPORT_FAIL_CSV_FILE, csvURI);
            }
            else
            {
                postGlobalError("error.process", e.toString() , request);
            }            
            
            return new ActionForward("/jsp/pos/inventoryImportErrors.jsp");
        }
        finally
        {
            
        } 
        
        request.getSession().setAttribute(inventoryCart, cartBean);
        request.getSession().setAttribute(inventoryCartItems, cartBean.getItems());
        
        return new ActionForward("/initInventoryCart.do");
        
    }
    
    public ActionForward newInventoryCart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        Properties ctx =TmkJSPEnv.getCtx(request);
        InventoryCartManager.clearInventorycart(ctx, request);
        return new ActionForward("/initInventoryCart.do");
    }
    
    
}
