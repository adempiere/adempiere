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
 * Created on Jul 29, 2005 by praveen
 *
 */
package org.posterita.core;

import java.util.ArrayList;
import java.util.Properties;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;

import org.compiere.model.MOrder;
import org.compiere.model.MOrg;
import org.compiere.util.Env;
import org.posterita.Constants;
import org.posterita.beans.AttributeValuesBean;
import org.posterita.beans.FilterBean;
import org.posterita.beans.OrderLineBean;
import org.posterita.beans.ProductAttributeBean;
import org.posterita.beans.ProductInfo;
import org.posterita.beans.ReportBean;
import org.posterita.beans.StockBean;
import org.posterita.beans.UDIPair;
import org.posterita.beans.WebDocumentBean;
import org.posterita.beans.WebstoreUserBean;
import org.posterita.businesslogic.ApplicationManager;
import org.posterita.businesslogic.HistoryFilterBean;
import org.posterita.businesslogic.MenuManager;
import org.posterita.businesslogic.OrganisationManager;
import org.posterita.businesslogic.POSManager;
import org.posterita.businesslogic.POSSalesReportManager;
import org.posterita.businesslogic.PriceListManager;
import org.posterita.businesslogic.UserManager;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.SystemException;
import org.posterita.factory.SystemObjectsFactory;
import org.posterita.lib.UdiConstants;
import org.posterita.model.UDIMProductCategory;

public class SessionStorage 
{
   
    public static void putProductAttributes(HttpServletRequest request, ProductAttributeBean productAttributeBean)
    {
        //extract parameters from request
        //1.attributeSetId
        //2.attributeId
        request.getSession().setAttribute(Constants.ATTRIBUTE_NAME, productAttributeBean.getAttributes());
        request.getSession().setAttribute(Constants.ATTRIBUTE_SET, productAttributeBean.getAttributeSets());
        request.getSession().setAttribute(Constants.ATTRIBUTE_VALUES, productAttributeBean.getAttributeValues());
    }
    
    @SuppressWarnings("unchecked")
	public static void putProductAttributeValues(HttpServletRequest request,AttributeValuesBean bean)
    {
        
        TreeSet modelSet = new TreeSet(bean.getModel());
        TreeSet colourSet = new TreeSet(bean.getColour());
        TreeSet trxSet = new TreeSet(bean.getTrx());
        TreeSet yearSet = new TreeSet(bean.getYears());
        TreeSet makeSet = new TreeSet(bean.getMake());
        
        request.getSession().setAttribute(Constants.MODEL_ATTRIBUTE_VALUES,modelSet);
        request.getSession().setAttribute(Constants.COLOUR_ATTRIBUTE_VALUES,colourSet);
        request.getSession().setAttribute(Constants.TRX_ATTRIBUTE_VALUES,trxSet);
        request.getSession().setAttribute(Constants.YEAR_ATTRIBUTE_VALUES,yearSet);
        request.getSession().setAttribute(Constants.MAKE_ATTRIBUTE_VALUES,makeSet);
    }
    
    @SuppressWarnings("unchecked")
	public static void putTextileProductInfo(HttpServletRequest request, ProductInfo info)
    {
        //Properties ctx = TmkJSPEnv.getCtx(request);
       
        TreeSet brandSet = new TreeSet(info.getAttributeValuesBean().getBrand()); 
        TreeSet modelSet = new TreeSet(info.getAttributeValuesBean().getModel()); 
        TreeSet designSet = new TreeSet(info.getAttributeValuesBean().getDesign());
        TreeSet colourSet = new TreeSet(info.getAttributeValuesBean().getColour());
        TreeSet sizeSet = new TreeSet(info.getAttributeValuesBean().getSize());
        TreeSet trxSet = new TreeSet(info.getAttributeValuesBean().getTrx());
        TreeSet yearSet = new TreeSet(info.getAttributeValuesBean().getYears());
        
        request.getSession().setAttribute(Constants.POS_STOCK,info.getProducts());
        request.getSession().setAttribute(Constants.BRAND_ATTRIBUTE_VALUES, brandSet);
        request.getSession().setAttribute(Constants.MODEL_ATTRIBUTE_VALUES, modelSet);
        request.getSession().setAttribute(Constants.DESIGN_ATTRIBUTE_VALUES, designSet);
        request.getSession().setAttribute(Constants.COLOUR_ATTRIBUTE_VALUES, colourSet);
        request.getSession().setAttribute(Constants.SIZE_ATTRIBUTE_VALUES, sizeSet);
        request.getSession().setAttribute(Constants.TRX_ATTRIBUTE_VALUES, trxSet);
        request.getSession().setAttribute(Constants.YEAR_ATTRIBUTE_VALUES, yearSet);
    }
    
    @SuppressWarnings("unchecked")
	public static void putProductInfo(HttpServletRequest request, ProductInfo info, StockBean bean) throws OperationException, SystemException
    {
        if (bean.getFirst()!=null)
            info.getProducts().clear();
      
        request.getSession().setAttribute(Constants.PRODUCTS_IN_STOCK, info.getProducts());
           
        Properties ctx = TmkJSPEnv.getCtx(request);
         
        TreeSet modelSet = new TreeSet(info.getAttributeValuesBean().getModel()); 
        
        TreeSet colourSet = new TreeSet(info.getAttributeValuesBean().getColour());;
        TreeSet trxSet = new TreeSet(info.getAttributeValuesBean().getTrx());;
        TreeSet yearSet = new TreeSet(info.getAttributeValuesBean().getYears());;  
        TreeSet orgSet = new TreeSet(info.getOrganisations());
        TreeSet ETASet = new TreeSet(info.getAttributeValuesBean().getETAList());
          
        TreeSet regionsSet = new TreeSet(info.getRegions()); 
        
        request.getSession().setAttribute(Constants.MODEL_ATTRIBUTE_VALUES, modelSet);
        request.getSession().setAttribute(Constants.COLOUR_ATTRIBUTE_VALUES, colourSet);
        request.getSession().setAttribute(Constants.TRX_ATTRIBUTE_VALUES, trxSet);
        request.getSession().setAttribute(Constants.YEAR_ATTRIBUTE_VALUES, yearSet);
        request.getSession().setAttribute(Constants.ETA_LIST, ETASet);
        
        request.getSession().setAttribute(Constants.REGION_VALUES, regionsSet);
        request.getSession().setAttribute(Constants.ORGANISATION_VALUES, orgSet);
        
        request.getSession().setAttribute(Constants.RESERVE_STATUS_VALUES, info.getReservedStatusValues());
        request.getSession().setAttribute(Constants.WAREHOUSE_TYPES, info.getWarehouseTypes());
        request.getSession().setAttribute(Constants.WAREHOUSE_NAMES, info.getWarehouseNames());
        
        if (info.getProductCategoryId() == null)
        {
            request.getSession().setAttribute(Constants.MAKE_ATTRIBUTE_VALUES, null);
            return;
        }
        
        Integer productCategoryId = info.getProductCategoryId();        
        
        UDIMProductCategory usedCarPc = (UDIMProductCategory) SystemObjectsFactory.getFactoryInstance().get(ctx,SystemObjectsFactory.PRODUCT_CATEGORY_USED_CAR_ID);
        UDIMProductCategory usedBikePc = (UDIMProductCategory) SystemObjectsFactory.getFactoryInstance().get(ctx,SystemObjectsFactory.PRODUCT_CATEGORY_USED_BIKE_ID);
        
        
        if (productCategoryId.equals(Integer.valueOf(usedCarPc.getID())) ||productCategoryId.equals(Integer.valueOf(usedBikePc.getID())))
        {
            TreeSet makeSet = new TreeSet(info.getAttributeValuesBean().getMake()); 
            request.getSession().setAttribute(Constants.MAKE_ATTRIBUTE_VALUES, makeSet);
        }
        else
        {
            request.getSession().setAttribute(Constants.MAKE_ATTRIBUTE_VALUES, null);
        }
            
        
    }
   
    public static void putWebOrderBean(Properties ctx,HttpServletRequest request, WebDocumentBean bean) throws OperationException
    {
    	request.getSession().setAttribute(Constants.ORDER_LINES,bean.getLines());
    	request.getSession().setAttribute(Constants.MORDER, bean.getOrder());
    }
    
    public static void putHistoryFilterBeans(HttpServletRequest request, ArrayList orderHistory)
    {
        HistoryFilterBean orderHistoryBeans = new HistoryFilterBean(orderHistory);        
        
        TreeSet bPartners = orderHistoryBeans.getBPartnerList();
        TreeSet docStatus = orderHistoryBeans.getDocStatusList();
        
        request.getSession().setAttribute(Constants.BPARTNER, bPartners);
        request.getSession().setAttribute(Constants.DOC_STATUS, docStatus);
    }
    
    public static WebDocumentBean getWebOrderBean(Properties ctx, HttpServletRequest request)
    {
        ArrayList orderLines = (ArrayList) request.getSession().getAttribute(Constants.ORDER_LINES);
        
        MOrder order = (MOrder) request.getSession().getAttribute(Constants.MORDER);
       
        WebDocumentBean webOrderBean = new WebDocumentBean();
        webOrderBean.setLines(orderLines);
        webOrderBean.setOrder(order);
        return webOrderBean;
    }
    
    public static void putPriceList(HttpServletRequest request, ArrayList priceList)
    {
        AttributeValuesBean attrs = PriceListManager.getAttributeValues(priceList);
        
        ArrayList modelSet = attrs.getModel();
        ArrayList colourSet = attrs.getColour();
        ArrayList trxSet = attrs.getTrx();
        ArrayList yearSet = attrs.getYears();
        
        request.getSession().setAttribute(Constants.PRICE_LIST, priceList);
        request.getSession().setAttribute(Constants.MODEL_ATTRIBUTE_VALUES, modelSet);
        request.getSession().setAttribute(Constants.COLOUR_ATTRIBUTE_VALUES, colourSet);
        request.getSession().setAttribute(Constants.TRX_ATTRIBUTE_VALUES, trxSet);
        request.getSession().setAttribute(Constants.YEAR_ATTRIBUTE_VALUES, yearSet);
        
    }
    
    
    

    
	
	@SuppressWarnings({"unchecked","unchecked"})
	public static void putOrderFiltering(Properties ctx, HttpServletRequest request, FilterBean filterBean) 
	{
		ArrayList bpList = (ArrayList) filterBean.getBpList();
		ArrayList docStatusList = (ArrayList) filterBean.getDocStatusList();
		
		if ((bpList!=null) && (bpList.size()!=0))
		{
			TreeSet bpSet = new TreeSet(bpList);
			request.setAttribute(Constants.BPARTNER, bpSet);
		}
		
		if ((docStatusList!=null) && (docStatusList.size()!=0))
		{
			TreeSet bpSet = new TreeSet(docStatusList);
			request.setAttribute(Constants.DOC_STATUS, bpSet);
		}
	}
	
	public static void putSCOrderFiltering(Properties ctx, HttpServletRequest request, FilterBean filterBean) throws OperationException
	{
		putOrderFiltering(ctx, request, filterBean);
		
		MOrg myOrg = OrganisationManager.getMyOrg(ctx);
		
		/*if (!myOrg.isWholesaler())
		{
			request.setAttribute(Constants.BPARTNER, null);
		}*/
		
	}
	
	@SuppressWarnings("unchecked")
	public static void putPriceListProducts(HttpServletRequest request, ProductInfo info)
	{
		TreeSet<ArrayList> modelSet = new TreeSet(info.getAttributeValuesBean().getModel());
		TreeSet colourSet = new TreeSet(info.getAttributeValuesBean().getColour());
		TreeSet trxSet = new TreeSet(info.getAttributeValuesBean().getTrx());
		TreeSet yearSet = new TreeSet(info.getAttributeValuesBean().getYears());       
		
		request.getSession().setAttribute(Constants.MODEL_ATTRIBUTE_VALUES, modelSet);
		request.getSession().setAttribute(Constants.COLOUR_ATTRIBUTE_VALUES, colourSet);
		request.getSession().setAttribute(Constants.TRX_ATTRIBUTE_VALUES, trxSet);
		request.getSession().setAttribute(Constants.YEAR_ATTRIBUTE_VALUES, yearSet);
	}
	
	public static void putMenus(Properties ctx, HttpServletRequest request) throws SystemException, OperationException
	{ 
		ArrayList menuList = MenuManager.getMenus(ctx);
		MenuItem menus = MenuManager.buildMenuTree(ctx, menuList);
		ArrayList topMenusList = menus.getTopMenus();
		ArrayList leftMenusList = menus.getLeftMenus();
		
		request.getSession().setAttribute(Constants.MENUS, menus);
		request.getSession().setAttribute(Constants.TOP_MENUS, topMenusList);
		request.getSession().setAttribute(Constants.LEFT_MENUS, leftMenusList);
		
	}
	
	public static void putOrg(Properties ctx, HttpServletRequest request) 
	{
		MOrg org = new MOrg(ctx, Env.getAD_Org_ID(ctx), null);
		
		request.getSession().setAttribute(Constants.ORGANISATION, org);
	}   
	
	
	
	
/*	public static void putProductList(HttpServletRequest request, ArrayList productList) 
    {
        AttributeValuesBean bean = ProductManager.getAttributeValues(productList);
        
        putProductAttributeValues(request,bean);
        
        request.getSession().setAttribute(Constants.PRODUCT_LIST,productList);        
    }
*/	
	public static void putProductAttributeValueIds(HttpServletRequest request, StockBean bean)
	{
	    request.getSession().setAttribute(Constants.MODEL_ATTRIBUTE_VALUE_ID, bean.getModel());
	    request.getSession().setAttribute(Constants.COLOUR_ATTRIBUTE_VALUE_ID, bean.getColour());
	    request.getSession().setAttribute(Constants.TRX_ATTRIBUTE_VALUE_ID, bean.getTransmission());
	    request.getSession().setAttribute(Constants.YEAR_ATTRIBUTE_VALUE_ID, bean.getYear());	    
	}
	
	public static StockBean getProductAttributeValueIds(HttpServletRequest request, StockBean bean)
	{
	    Integer modelAttributeValueId = (Integer) request.getSession().getAttribute(Constants.MODEL_ATTRIBUTE_VALUE_ID);
	    Integer colourAttributeValueId = (Integer) request.getSession().getAttribute(Constants.COLOUR_ATTRIBUTE_VALUE_ID);
	    Integer trxAttributeValueId = (Integer) request.getSession().getAttribute(Constants.TRX_ATTRIBUTE_VALUE_ID);
	    Integer yearAttributeValueId = (Integer) request.getSession().getAttribute(Constants.YEAR_ATTRIBUTE_VALUE_ID);
	    
	    bean.setModel(modelAttributeValueId);
	    bean.setColour(colourAttributeValueId);
	    bean.setTransmission(trxAttributeValueId);
	    bean.setYear(yearAttributeValueId);
	    	    
	    return bean;
	    
	}
	
	public static void putOrderInfo(Properties ctx, WebDocumentBean webBean, HttpServletRequest request)
	{
	    request.getSession().setAttribute(Constants.ME_LOCATION, webBean.getMeLocation());
        request.getSession().setAttribute(Constants.YOU_LOCATION, webBean.getYouLocation());
        request.getSession().setAttribute(Constants.YOU_BP_LOCATION, webBean.getYoubpLocation());
        request.getSession().setAttribute(Constants.ME, webBean.getMe());
        request.getSession().setAttribute(Constants.YOU,webBean.getYou());
        request.getSession().setAttribute(Constants.MORDER, webBean.getOrder());
        request.getSession().setAttribute(Constants.MORDER_LINES_COLLECTION,webBean.getLines());
        request.getSession().setAttribute(Constants.SIMPLE_COMMAND,webBean.getSimpleCommand());
        request.getSession().setAttribute(Constants.COMPLEX_COMMAND,webBean.getComplexCommand());
        request.getSession().setAttribute(Constants.WEB_DOCUMENT_HEADER_BEAN, webBean.getHeaderBean());
        request.getSession().setAttribute(Constants.ORDER_TAX,webBean.getTotalTax());
        request.getSession().setAttribute(Constants.TOTAL_LINES,webBean.getTotalLines());
        request.getSession().setAttribute(Constants.GRAND_TOTAL,webBean.getGrandTotal());
        request.getSession().setAttribute(Constants.ALLOCATION,webBean.getAllocations());
        request.getSession().setAttribute(Constants.SALES_REP, webBean.getSalesRep());
	}
	
	public static void putPOSOrderInfo(Properties ctx,OrderLineBean bean,HttpServletRequest request,String forward) throws OperationException
	{
        if (forward == null)
            throw new OperationException("Invalid forward parameter, it is set to null");
	    
		   if (forward.equals(POSManager.DELETE_POS_ORDERLINE))
		   {
	           Integer posOrderId=(Integer)request.getSession().getAttribute(Constants.CURRENT_POS_ORDER_ID);
	           MOrder posOrder = new MOrder(ctx, posOrderId, null);
	           ArrayList POSlist = POSManager.populateOrderLines(ctx,posOrder);
		       WebDocumentBean POSdocumentBean=POSManager.calculateOrderTotals(POSlist);
		       request.getSession().setAttribute(Constants.CURRENT_POS_ORDER_ID,posOrder.get_ID());
		       request.setAttribute(Constants.POS_ORDER_LINES,POSlist);
		       request.setAttribute(Constants.ORDER_TAX,POSdocumentBean.getTotalTax());
		       request.setAttribute(Constants.TOTAL_LINES,POSdocumentBean.getTotalLines());
		       request.setAttribute(Constants.GRAND_TOTAL,POSdocumentBean.getGrandTotal());
		   }
		   else if(forward.equals(POSManager.DELETE_PARTIAL_POS_ORDERLINE))
		   {
			   Integer posOrderId=(Integer)request.getSession().getAttribute(Constants.CURRENT_PARTIAL_POS_ORDER_ID);
	           MOrder posOrder = new MOrder(ctx, posOrderId, null);
	           ArrayList POSlist = POSManager.populateOrderLines(ctx,posOrder);
		       WebDocumentBean POSdocumentBean=POSManager.calculateOrderTotals(POSlist);
		       request.getSession().setAttribute(Constants.CURRENT_PARTIAL_POS_ORDER_ID,posOrder.get_ID());
		       request.setAttribute(Constants.PARTIAL_POS_ORDER_LINES,POSlist);
		       request.setAttribute(Constants.ORDER_TAX,POSdocumentBean.getTotalTax());
		       request.setAttribute(Constants.TOTAL_LINES,POSdocumentBean.getTotalLines());
		       request.setAttribute(Constants.GRAND_TOTAL,POSdocumentBean.getGrandTotal());
		   }
		else if(forward.equals(POSManager.DELETE_GOODS_RECEIVE_ORDERLINE))
		{
	           Integer goodsReceiceNoteId=(Integer)request.getSession().getAttribute(Constants.GOODS_RECEIVE_NOTE_ID);
		       MOrder goodsReceiveNote = new MOrder(ctx,goodsReceiceNoteId,null);
	           goodsReceiveNote = new MOrder(ctx, goodsReceiveNote.get_ID(), null);
		       ArrayList Receivelist = POSManager.populateOrderLines(ctx,goodsReceiveNote);
		        WebDocumentBean receivedocumentBean=POSManager.calculateOrderTotals(Receivelist);
		        request.getSession().setAttribute(Constants.GOODS_RECEIVE_NOTE_ID,goodsReceiveNote.get_ID());
		        request.setAttribute(Constants.GOODS_RECEIVE_NOTE_LINES,Receivelist);
		        request.setAttribute(Constants.ORDER_TAX,receivedocumentBean.getTotalTax());
		        request.setAttribute(Constants.TOTAL_LINES,receivedocumentBean.getTotalLines());
		        request.setAttribute(Constants.GRAND_TOTAL,receivedocumentBean.getGrandTotal());
    	}
    	else if(forward.equals(POSManager.DELETE_CUSTOMER_RETURN_ORDERLINE))
    	{
            Integer customerReturnOrderId=(Integer)request.getSession().getAttribute(Constants.CUSTOMER_RETURN_ORDER_ID);   
            MOrder customerReturnOrder=new MOrder(ctx,customerReturnOrderId,null);
            ArrayList returnList = POSManager.populateOrderLines(ctx,customerReturnOrder);
	        WebDocumentBean receivedocumentBean=POSManager.calculateOrderTotals(returnList);
	        request.getSession().setAttribute(Constants.CUSTOMER_RETURN_ORDER,customerReturnOrder);
	        request.setAttribute(Constants.CUSTOMER_RETURN_ORDER_LINES,returnList);
	        request.setAttribute(Constants.ORDER_TAX,receivedocumentBean.getTotalTax());
	        request.setAttribute(Constants.TOTAL_LINES,receivedocumentBean.getTotalLines());
	        request.setAttribute(Constants.GRAND_TOTAL,receivedocumentBean.getGrandTotal());
    	}
	    else
	    {
            Integer goodsReturnNoteId=(Integer)request.getSession().getAttribute(Constants.GOODS_RETURN_NOTE_ID);   
	        MOrder goodsReturnNote=new MOrder(ctx,goodsReturnNoteId,null);
            goodsReturnNote = new MOrder(ctx, goodsReturnNote.get_ID(), null);
	        ArrayList list = POSManager.populateOrderLines(ctx,goodsReturnNote);
	        WebDocumentBean documentBean=POSManager.calculateOrderTotals(list);
	        
	        request.getSession().setAttribute(Constants.GOODS_RETURN_NOTE,goodsReturnNote);
	        request.setAttribute(Constants.GOODS_RETURN_NOTE_LINES,list);
	        request.setAttribute(Constants.ORDER_TAX,documentBean.getTotalTax());
	        request.setAttribute(Constants.TOTAL_LINES,documentBean.getTotalLines());
	        request.setAttribute(Constants.GRAND_TOTAL,documentBean.getGrandTotal());
	    }
	       
	       
	}
	
	public static void putCompleteOrderInfo(Properties ctx,HttpServletRequest request,WebDocumentBean webBean,WebDocumentBean documentBean)
	{
	    request.setAttribute(Constants.ORDER_TAX,documentBean.getTotalTax());
        request.setAttribute(Constants.TOTAL_LINES,documentBean.getTotalLines());
        request.setAttribute(Constants.GRAND_TOTAL,documentBean.getGrandTotal());
        request.getSession().setAttribute(Constants.ME_LOCATION, webBean.getMeLocation());
        request.getSession().setAttribute(Constants.YOU_LOCATION, webBean.getYouLocation());
        request.getSession().setAttribute(Constants.YOU_BP_LOCATION, webBean.getYoubpLocation());
        request.getSession().setAttribute(Constants.ME, webBean.getMe());
        request.getSession().setAttribute(Constants.YOU,webBean.getYou());
        request.getSession().setAttribute(Constants.MORDER, webBean.getOrder());
        request.getSession().setAttribute(Constants.MORDER_LINES_COLLECTION,webBean.getLines());
        request.getSession().setAttribute(Constants.SIMPLE_COMMAND,webBean.getSimpleCommand());
        request.getSession().setAttribute(Constants.COMPLEX_COMMAND,webBean.getComplexCommand());
        request.getSession().setAttribute(Constants.WEB_DOCUMENT_HEADER_BEAN, webBean.getHeaderBean());
        request.getSession().setAttribute(Constants.ORDER_TAX,webBean.getTotalTax());
        request.getSession().setAttribute(Constants.TOTAL_LINES,webBean.getTotalLines());
        request.getSession().setAttribute(Constants.GRAND_TOTAL,webBean.getGrandTotal());
        request.getSession().setAttribute(Constants.ALLOCATION,webBean.getAllocations());
        request.getSession().setAttribute(Constants.SALES_REP, webBean.getSalesRep());
        request.getSession().setAttribute(Constants.DESCRIPTION, webBean.getDescription());
        request.getSession().setAttribute(Constants.PAYMENT_BY_CASH, webBean.getPaymentByCash());
        request.getSession().setAttribute(Constants.PAYMENT_BY_CARD, webBean.getPaymentByCard());
        request.getSession().setAttribute(Constants.PAYMENT_BY_CHEQUE, webBean.getPaymentbyCheque());
        request.setAttribute(Constants.CURRENCY_SYMBOLE,webBean.getCurrencySymbole());
       
	}


	public static void putPOSID(Properties ctx,String posId,HttpServletRequest request) throws OperationException 
	{
		Env.setContext(ctx, UdiConstants.POS_ID, posId);
        
        int purchasePLVersion = POSManager.getPurchasePLVersion(ctx);        
        int purchasePList = POSManager.getPurchasePList(ctx,purchasePLVersion);       
        
        Env.setContext(ctx,UdiConstants.POS_PURCHASE_PL_VERSION,purchasePLVersion);
        Env.setContext(ctx,UdiConstants.POS_PURCHASE_PL,purchasePList);
		
	}
	
	public static void putSalesGroupFilter(Properties ctx,HttpServletRequest request,ReportBean bean) throws OperationException
	{
		ArrayList<UDIPair> groupList = POSSalesReportManager.getSalesGroupList(ctx,bean);
		ArrayList<UDIPair> priceQtyFilter = POSSalesReportManager.getPriceQtyFilter(ctx);
        
        request.getSession().setAttribute(Constants.SALESGROUP_LIST,groupList);
        request.getSession().setAttribute(Constants.PRICEQTY_FILTER,priceQtyFilter);
        request.getSession().removeAttribute(Constants.REPORT_URL);
	}
	public static void removeSession(Properties ctx,HttpServletRequest request)
	{
		//goods Receive Note
		request.getSession().removeAttribute(Constants.ALL_CUSTOMERS);
        request.getSession().removeAttribute(Constants.GOODS_RECEIVE_NOTE_SHOPPING_CART);
        request.getSession().removeAttribute(Constants.GOODS_RECEIVE_NOTE_SHOPPING_CART_ITEMS);
        request.getSession().removeAttribute(Constants.DESCRIPTION);
        request.getSession().removeAttribute(Constants.B_PARTNER_ID);
        request.getSession().removeAttribute(Constants.GOODS_RECEIVE_NOTE_ID);
        request.getSession().removeAttribute(Constants.DESCRIPTION);
        request.getSession().removeAttribute(Constants.PRESENT_GRN_ORDER);
        request.getSession().removeAttribute(Constants.PRESENT_GRN_ORDER_ID);
        
        //goods return Note
        
        request.getSession().removeAttribute(Constants.ALL_CUSTOMERS);
        request.getSession().removeAttribute(Constants.GOODS_RETURN_NOTE_SHOPPING_CART);
        request.getSession().removeAttribute(Constants.GOODS_RETURN_NOTE_SHOPPING_CART_ITEMS);
        request.getSession().removeAttribute(Constants.GOODS_RETURN_NOTE_ID);
        request.getSession().removeAttribute(Constants.B_PARTNER_ID);
        request.getSession().removeAttribute(Constants.DESCRIPTION);
        request.getSession().removeAttribute(Constants.PRESENT_GOODS_RET_ORDER);
        request.getSession().removeAttribute(Constants.PRESENT_GOODS_RET_ORDER_ID);
        
        //Customer Return Order
        
        request.getSession().removeAttribute(Constants.ALL_CUSTOMERS);
        request.getSession().removeAttribute(Constants.CUSTOMER_RETURN_ORDER_SHOPPING_CART);
        request.getSession().removeAttribute(Constants.CUSTOMER_RETURN_ORDER_SHOPPING_CART_ITEMS);
        request.getSession().removeAttribute(Constants.DESCRIPTION);
        request.getSession().removeAttribute(Constants.CUSTOMER_RETURN_ORDER_ID);
        request.getSession().removeAttribute(Constants.B_PARTNER_ID);
        
        request.getSession().removeAttribute(Constants.ORDER_TYPE);
        request.getSession().removeAttribute(Constants.POS_ORDER_DOC_NUMBER);
        request.getSession().removeAttribute(Constants.CREDIT_PAYMENT_TERM_ID);
        request.getSession().removeAttribute( Constants.PRESENT_CUSTOMER_RET_ORDER );
        request.getSession().removeAttribute( Constants.PRESENT_CUSTOMER_RET_ORDER_ID);
        
        //pos order
        
        request.getSession().removeAttribute(Constants.ALL_CUSTOMERS);
        request.getSession().removeAttribute(Constants.BPARTNER);
        request.getSession().removeAttribute(Constants.SHOPPING_ORDER_CART);        
        request.getSession().removeAttribute(Constants.SHOPPING_ORDER_CART_ITEMS);
        request.getSession().removeAttribute(Constants.CURRENT_POS_ORDER_ID);
        
        
        request.getSession().removeAttribute(Constants.PARTIAL_ORDER_SHOPPING_CART);
        request.getSession().removeAttribute(Constants.PARTIAL_ORDER_SHOPPING_CART_ITEMS);
        request.getSession().removeAttribute(Constants.CURRENT_PARTIAL_POS_ORDER_ID);
        request.getSession().removeAttribute(Constants.PRESENT_POS_ORDER);
        request.getSession().removeAttribute( Constants.PRESENT_POS_ORDER_ID );
        
        //credit order
        
        request.getSession().removeAttribute(Constants.ALL_CUSTOMERS);
        request.getSession().removeAttribute(Constants.CREDIT_ORDER_SHOPPING_CART);
        request.getSession().removeAttribute(Constants.CREDIT_ORDER_SHOPPING_CART_ITEMS);
        request.getSession().removeAttribute(Constants.CURRENT_POS_ORDER_ID);
        request.getSession().removeAttribute(Constants.CREDITORDER_BPARTNER);
        request.getSession().removeAttribute(Constants.SHIPMENT_REQUIRED);
        request.getSession().removeAttribute(Constants.TO_BE_SHIPPED);
        request.getSession().removeAttribute(Constants.CREDITORDER_BPARTNER);
        request.getSession().removeAttribute( Constants.PRESENT_CREDIT_ORDER );
        request.getSession().removeAttribute( Constants.PRESENT_CREDIT_ORDER_ID);
        
        request.getSession().removeAttribute(Constants.ORDER_TYPE);
        request.getSession().removeAttribute(Constants.POS_ORDER_DOC_NUMBER);
	}
}
