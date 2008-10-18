/*
*
* Copyright (c) 2005 UDI Ltd. All Rights Reserved.
*
* This software is the confidential and proprietary information of
* UDI Ltd. ("Confidential Information").  You shall not
* disclose such Confidential Information and shall use it only in
* accordance with the terms of the license agreement you entered into
* with UDI.
*
* UDI MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE
* SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT
* NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR
* A PARTICULAR PURPOSE, OR NON-INFRINGEMENT. UDI SHALL NOT
* BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING,
* MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
*
*/
package org.posterita.struts.inOut;

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
import org.compiere.model.MInOut;
import org.posterita.core.TmkJSPEnv;

import org.posterita.Constants;
import org.posterita.beans.DocumentBean;
import org.posterita.beans.WebDocumentBean;
import org.posterita.businesslogic.MinOutManager;
import org.posterita.exceptions.ApplicationException;
import org.posterita.exceptions.OperationException;
import org.posterita.struts.core.BaseAction;
import org.posterita.struts.core.DefaultForm;


public class ViewMinOutAction extends BaseAction
{
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws OperationException, ApplicationException
    {
        
        MessageResources resources = (MessageResources) request.getAttribute(Globals.MESSAGES_KEY);
        
        String operationException =  resources.getMessage("error.operationException");
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        
        DefaultForm df = (DefaultForm) form;
        DocumentBean bean = (DocumentBean) df.getBean();
        Integer minoutId = bean.getDocumentId();
       
        // retrieve from cache
        WebDocumentBean webBean = null;
        
        MInOut inout = null;
        if (minoutId!= null)
        {
            inout = new MInOut(ctx, minoutId.intValue(), null);
            webBean = MinOutManager.getWebMinOutBean(ctx, inout);
            request.getSession().setAttribute(Constants.WEB_MINOUT_BEAN, webBean);
            
        }
        
        //int orgID = Env.getAD_Org_ID(ctx);
        
        if (webBean == null)
            webBean = (WebDocumentBean) request.getSession().getAttribute(Constants.WEB_MINOUT_BEAN);
        
        if (webBean == null)
            throw new OperationException(operationException);

        request.getSession().setAttribute(Constants.ME_LOCATION, webBean.getMeLocation());
        request.getSession().setAttribute(Constants.YOU_LOCATION, webBean.getYouLocation());
        request.getSession().setAttribute(Constants.YOU_BP_LOCATION, webBean.getYoubpLocation());
        request.getSession().setAttribute(Constants.ME, webBean.getMe());
        request.getSession().setAttribute(Constants.YOU,webBean.getYou());
        request.getSession().setAttribute(Constants.MINOUT_LINES_COLLECTION, webBean.getLines());
        request.getSession().setAttribute(Constants.MINOUT, webBean.getMinOut());
        request.getSession().setAttribute(Constants.MINVOICE, webBean.getInvoice());
        request.getSession().setAttribute(Constants.SIMPLE_COMMAND,webBean.getSimpleCommand());
        request.getSession().setAttribute(Constants.COMPLEX_COMMAND,webBean.getComplexCommand());
        request.getSession().setAttribute(Constants.WEB_DOCUMENT_HEADER_BEAN, webBean.getHeaderBean());
        
        ForwardConfig forwardConfig = getForward(ctx,webBean,mapping,form);
        
        ActionForward forward = new ActionForward(forwardConfig.getPath());
              
        return forward;
    }
    
    private ForwardConfig getForward(Properties ctx, WebDocumentBean webBean, ActionMapping mapping, ActionForm form)
    {
        
        ActionConfig config = mapping.getModuleConfig().findActionConfig("/ViewMinOutAction");
        
        return config.findForwardConfig(Constants.VIEW_MINOUT);
    }
}
