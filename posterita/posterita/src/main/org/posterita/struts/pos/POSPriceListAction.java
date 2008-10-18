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
package org.posterita.struts.pos;

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
import org.posterita.beans.ProductPriceBean;
import org.posterita.businesslogic.ProductCart;
import org.posterita.businesslogic.administration.PriceListManager;
import org.posterita.core.TmkJSPEnv;
import org.posterita.core.TrxPrefix;
import org.posterita.exceptions.ApplicationException;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.SystemException;
import org.posterita.form.PriceListForm;
import org.posterita.lib.UdiConstants;
import org.posterita.struts.core.BaseDispatchAction;
import org.posterita.struts.core.DefaultForm;

public class POSPriceListAction extends BaseDispatchAction
{
    public static final String INIT_PRICE_LIST = "initPriceList";
    public ActionForward initPriceList(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
    	Properties ctx = TmkJSPEnv.getCtx(request);
        
        DefaultForm df = (DefaultForm) form;
        
        String searchText = df.getSearchText();
        
        int priceListId = Env.getContextAsInt(ctx, UdiConstants.PRICELIST_CTX_PARAM);
        int priceListVersionId = PriceListManager.getPriceListVersionID(ctx, priceListId, null);
        
        ArrayList<ProductPriceBean> productPriceList = PriceListManager.getProductPriceList(ctx, priceListVersionId, searchText, null, null);
        
        request.getSession().setAttribute(Constants.POS_SALES_PRICE_LIST, productPriceList);
        request.getSession().setAttribute(Constants.PRICE_LIST, productPriceList);
        return mapping.findForward(INIT_PRICE_LIST);
    }
        
    public static final String INIT_EDIT_PRICE_LIST = "initEditPriceList";
    public ActionForward initEditPriceList(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws OperationException, ApplicationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
    	PriceListForm df = (PriceListForm) form;
    	
    	df.validate(mapping,request);
        
        PriceListBean bean = (PriceListBean) df.getBean();       

        if(bean.getProductIds() == null || bean.getProductIds().length == 0)
	    {
	    	postGlobalError("error.product.not.selected", request);
	    	return mapping.getInputForward();
	    }
        
        request.getSession().setAttribute(Constants.PRODUCT_IDS, bean.getProductIds());
        
         
        return mapping.findForward(INIT_EDIT_PRICE_LIST);
    }

    public static final String EDIT_PRICELIST = "editPriceList";
    public ActionForward editPriceList(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws OperationException, ApplicationException, SystemException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
    	Properties ctx = TmkJSPEnv.getCtx(request);
        
        DefaultForm df = (DefaultForm) form;
        
        EditPriceBean bean = (EditPriceBean) df.getBean();
        
        BigDecimal priceEntered = bean.getPrice();
         
        ProductCart cart = (ProductCart) request.getSession().getAttribute(Constants.PRODUCT_CART);        
        Integer productIds[] = cart.getProductIDs();
        
        
        int priceListId = Env.getContextAsInt(ctx, UdiConstants.PRICELIST_CTX_PARAM);
        int priceListVersionId = PriceListManager.getPriceListVersionID(ctx, priceListId, null);                
        
        Trx trx = Trx.get(TrxPrefix.getPrefix(), true);
        
        
        try
		{
        	trx.start();
			PriceListManager.editProductPrices(ctx,productIds, priceListVersionId, priceEntered, trx.getTrxName());
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
    	
        return mapping.findForward(EDIT_PRICELIST);
    }
      
}
