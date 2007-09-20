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
 * Created on Aug 19, 2005 by praveen
 *
 */
package org.posterita.struts.product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.posterita.Constants;
import org.posterita.beans.EditPriceBean;
import org.posterita.beans.PriceListBean;
import org.posterita.beans.ProductBean;
import org.posterita.beans.ProductPriceBean;
import org.posterita.businesslogic.ApplicationManager;
import org.posterita.businesslogic.PriceListManager;
import org.posterita.core.TrxPrefix;
import org.posterita.exceptions.ApplicationException;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.SystemException;
import org.posterita.form.PriceListForm;
import org.posterita.lib.UdiConstants;
import org.posterita.struts.core.BaseDispatchAction;
import org.posterita.struts.core.DefaultForm;

public class PriceListAction extends BaseDispatchAction
{
    public static final String INIT_PRICE_LIST = "initPriceList";
    private Properties webstoreCtx = new Properties();
    
    
    private Properties getWebstoreContext(HttpServletRequest request)
    {
    	ApplicationManager.setApplicationParametersInContext(webstoreCtx, "webstore");
    	
    	return webstoreCtx;
    }
    

    public static final String INIT_WEBSTORE_PRICE_LIST = "initWebstorePriceList";
    public ActionForward initWebstorePriceList(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
    {
        ActionForward fwd = init(mapping, form, request, response);
        
        if(fwd!=null)
            return fwd;
    	
		webstoreCtx = getWebstoreContext(request);
		 
		DefaultForm df = (DefaultForm) form;
		ProductBean bean = (ProductBean) df.getBean();
		
        df.setIsWebstoreFeatured("true");
        
		int priceListId = Env.getContextAsInt(webstoreCtx, UdiConstants.PRICELIST_CTX_PARAM);
		int priceListVersionId = PriceListManager.getPriceListVersionID(webstoreCtx, priceListId, null);
		 
		ArrayList<ProductPriceBean> productPriceList = new ArrayList<ProductPriceBean> ();
		
		//if (bean.getSearchText() != null)
		productPriceList = PriceListManager.getProductPriceList(webstoreCtx, priceListVersionId, bean.getSearchText(), bean.getIsSelfService(), bean.getProductClassification());

		request.getSession().setAttribute(Constants.PRICE_LIST, productPriceList);
		
		return mapping.findForward(INIT_WEBSTORE_PRICE_LIST);
    }
    
    
    

    
    public static final String INIT_EDIT_PRICE_LIST = "initEditPriceList";
    
    public ActionForward initEditPriceList(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
        ActionForward fwd = init(mapping, form, request, response);

        if(fwd!=null)
            return fwd;
        
        DefaultForm df = (DefaultForm) form;
        
        PriceListBean bean = (PriceListBean) df.getBean();
        
        request.getSession().setAttribute(Constants.PRODUCT_IDS, bean.getProductIds());
         
        return mapping.findForward(INIT_EDIT_PRICE_LIST);
	}
    
    public static final String INIT_EDIT_WSTORE_PRICE_LIST = "initWebstoreEditPriceList";
    public ActionForward initWebstoreEditPriceList(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws OperationException, ApplicationException
    {
        ActionForward fwd = init(mapping, form, request, response);

        if(fwd!=null)
            return fwd;
    	
    	DefaultForm df = (DefaultForm) form;
        
        PriceListBean bean = (PriceListBean) df.getBean();
        
        if(bean.getProductIds() == null || bean.getProductIds().length == 0)
	    {
	    	postGlobalError("error.product.not.selected", request);
	    	return mapping.getInputForward();
	    }
        
        request.getSession().setAttribute(Constants.PRODUCT_IDS, bean.getProductIds());
         
        return mapping.findForward(INIT_EDIT_WSTORE_PRICE_LIST);
    }
    
    
    public static final String EDIT_PRICE_LIST = "editPriceList";
    public ActionForward editPriceLists(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException, SystemException
	{
        ActionForward fwd = init(mapping, form, request, response);

        if(fwd!=null)
            return fwd;
    	
        webstoreCtx = getWebstoreContext(request);
        
        DefaultForm df = (DefaultForm) form;
        
        EditPriceBean bean = (EditPriceBean) df.getBean();
        
        BigDecimal priceEntered = new BigDecimal(bean.getPrice().toString());
        
        Integer productIds[] = (Integer[]) request.getSession().getAttribute(Constants.PRODUCT_IDS);
        Integer priceListVersionId = (Integer) request.getSession().getAttribute(Constants.PRICE_LIST_VERSION_ID);

        Trx trx = Trx.get(TrxPrefix.getPrefix(), true);
        
        try
		{
        	trx.start();
        	PriceListManager.editProductPrices(webstoreCtx,productIds, priceListVersionId.intValue(), priceEntered, trx.getTrxName());
			trx.commit();
		}
		catch (OperationException e)
		{	
			trx.rollback();
			throw e;
		}
		catch (SystemException e)
		{
			trx.rollback();
			throw e;
		}
		finally
		{
			trx.close();	
		}
        
        PriceListForm plf = (PriceListForm) getForm(Constants.PriceListForm, Constants.InitPriceListAction, request);

        PriceListBean plBean = new PriceListBean();
        plBean.setPriceListVersionId(priceListVersionId);
        plf.populate(plBean);
        
        return mapping.findForward(EDIT_PRICE_LIST);
	}
    
    public static final String EDIT_WEBSTORE_PRICELIST = "editWebstorePriceList";
    public ActionForward editWebstorePriceList(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws OperationException, ApplicationException, SystemException
    {
        ActionForward fwd = init(mapping, form, request, response);

        if(fwd!=null)
            return fwd;
    	
        webstoreCtx = getWebstoreContext(request);
        
        DefaultForm df = (DefaultForm) form;
        
        EditPriceBean bean = (EditPriceBean) df.getBean();
        
        Integer productIds[] = (Integer[])request.getSession().getAttribute(Constants.PRODUCT_IDS);
        
        int priceListId = Env.getContextAsInt(webstoreCtx, UdiConstants.PRICELIST_CTX_PARAM);
        int priceListVersionId = PriceListManager.getPriceListVersionID(webstoreCtx, priceListId, null);
        
        BigDecimal priceEntered = new BigDecimal(bean.getPrice().toString());

        Trx trx = Trx.get(TrxPrefix.getPrefix(), true);
        
        try
		{
        	trx.start();
            PriceListManager.editProductPrices(webstoreCtx,productIds, priceListVersionId, priceEntered, trx.getTrxName());
			trx.commit();
		}
		catch (OperationException e)
		{	
			trx.rollback();
			throw e;
		}
		catch (SystemException e)
		{
			trx.rollback();
			throw e;
		}
		finally
		{
			trx.close();	
		}
        
    	
        return mapping.findForward(EDIT_WEBSTORE_PRICELIST);
    }
    
    
   
}
