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
 * Created on Oct 25, 2006
 */


package org.posterita.webstore.action;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.config.ActionConfig;
import org.apache.struts.config.ForwardConfig;
import org.apache.struts.util.MessageResources;

import org.posterita.Constants;
import org.posterita.beans.DocumentBean;
import org.posterita.beans.WebDocumentBean;
import org.posterita.businesslogic.CashManager;
import org.posterita.core.TmkJSPEnv;
import org.posterita.exceptions.ApplicationException;
import org.posterita.exceptions.OperationException;
import org.posterita.struts.core.DefaultForm;
import org.posterita.struts.pos.POSDispatchAction;


public class ViewCashPaymentAction extends POSDispatchAction
{
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws OperationException, ApplicationException
    {
        ActionForward fwd = init(mapping, form, request, response);
        
        if (fwd != null)
            return fwd; 
        
        MessageResources resources = (MessageResources) request.getAttribute(Globals.MESSAGES_KEY);
        
        String operationException =  resources.getMessage("error.operationException");
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        
        DefaultForm df = (DefaultForm) form;
        DocumentBean bean = (DocumentBean) df.getBean();
        Integer paymentId = bean.getDocumentId();
        
        // retrieve from cache
        WebDocumentBean webBean = null;
        webBean  = CashManager.getWebCashPaymentBean(ctx, paymentId);
        
        request.getSession().setAttribute(Constants.WEB_PAYMENT_BEAN, webBean);
        if (webBean == null)
            webBean = (WebDocumentBean) request.getSession().getAttribute(Constants.WEB_PAYMENT_BEAN);
        
        if (webBean == null)
            throw new OperationException(operationException);
        
        request.getSession().setAttribute(Constants.ME_LOCATION, webBean.getMeLocation());
        request.getSession().setAttribute(Constants.YOU_LOCATION, webBean.getYouLocation());
        request.getSession().setAttribute(Constants.YOU_BP_LOCATION, webBean.getYoubpLocation());
        request.getSession().setAttribute(Constants.ME, webBean.getMe());
        request.getSession().setAttribute(Constants.YOU,webBean.getYou());
        request.getSession().setAttribute(Constants.MCASHLINE, webBean.getCashLine());
        request.getSession().setAttribute(Constants.WEB_DOCUMENT_HEADER_BEAN, webBean.getHeaderBean());
        request.setAttribute(Constants.CURRENCY_SYMBOLE,webBean.getCurrencySymbole());
        request.getSession().setAttribute(Constants.CASH_PAYMENT_MADE,bean);
        ForwardConfig forwardConfig = getForward(ctx,webBean,mapping,form);
        
        ActionForward forward = new ActionForward(forwardConfig.getPath());
        
        return forward;
    }
    
    private ForwardConfig getForward(Properties ctx, WebDocumentBean webBean, ActionMapping mapping, ActionForm form) throws OperationException
    {
        ActionConfig config = mapping.getModuleConfig().findActionConfig("/ViewWebstoreCashPaymentAction");
        
        return config.findForwardConfig(Constants.VIEW_PAYMENT);
    } 
}