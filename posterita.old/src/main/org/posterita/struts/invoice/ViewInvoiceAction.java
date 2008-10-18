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
package org.posterita.struts.invoice;

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
import org.compiere.model.MDocType;
import org.compiere.model.MInvoice;
import org.compiere.model.MOrder;
import org.compiere.process.DocumentEngine;
import org.compiere.util.Env;
import org.posterita.Constants;
import org.posterita.beans.DocumentBean;
import org.posterita.beans.WebDocumentBean;
import org.posterita.businesslogic.InvoiceManager;
import org.posterita.core.TmkJSPEnv;
import org.posterita.exceptions.ApplicationException;
import org.posterita.exceptions.OperationException;
import org.posterita.order.UDIOrderTypes;
import org.posterita.struts.core.BaseAction;
import org.posterita.struts.core.DefaultForm;


public class ViewInvoiceAction extends BaseAction
{
 
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws OperationException, ApplicationException
    {
        ActionForward fwd = init(mapping, form, request, response);

		if (fwd != null)
			return fwd;	

		MessageResources resources = (MessageResources) request.getAttribute(Globals.MESSAGES_KEY);
        
        //String securityException =  resources.getMessage("error.securityException");
        String operationException =  resources.getMessage("error.operationException");
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        
        DefaultForm df = (DefaultForm) form;
        DocumentBean bean = (DocumentBean) df.getBean();
        Integer invoiceId = bean.getDocumentId();
       
        // retrieve from cache
        WebDocumentBean webBean = null;
        
        MInvoice invoice = null;
        if (invoiceId!= null && (invoiceId.intValue()!=0))
        {
            invoice = new MInvoice(ctx, invoiceId.intValue(), null);
            webBean = InvoiceManager.getWebInvoiceBean(invoice);
            request.getSession().setAttribute(Constants.WEB_INVOICE_BEAN, webBean);
        }
        
        //int orgID = Env.getAD_Org_ID(ctx);
        
        if (webBean == null)
            webBean = (WebDocumentBean) request.getSession().getAttribute(Constants.WEB_INVOICE_BEAN);
        
        if (webBean == null)
            throw new OperationException(operationException);

        request.getSession().setAttribute(Constants.ME_LOCATION, webBean.getMeLocation());
        request.getSession().setAttribute(Constants.YOU_LOCATION, webBean.getYouLocation());
        request.getSession().setAttribute(Constants.YOU_BP_LOCATION, webBean.getYoubpLocation());
        request.getSession().setAttribute(Constants.ME, webBean.getMe());
        request.getSession().setAttribute(Constants.YOU,webBean.getYou());
        request.getSession().setAttribute(Constants.MINVOICE, webBean.getInvoice());
        request.getSession().setAttribute(Constants.MORDER, webBean.getOrder());
        request.getSession().setAttribute(Constants.MINVOICE_LINES_COLLECTION,webBean.getLines());
        request.getSession().setAttribute(Constants.SIMPLE_COMMAND,webBean.getSimpleCommand());
        request.getSession().setAttribute(Constants.COMPLEX_COMMAND,webBean.getComplexCommand());
        request.getSession().setAttribute(Constants.WEB_DOCUMENT_HEADER_BEAN, webBean.getHeaderBean());
        request.getSession().setAttribute(Constants.INVOICE_TAX,webBean.getTotalTax());
        request.getSession().setAttribute(Constants.TOTAL_LINES,webBean.getTotalLines());
        request.getSession().setAttribute(Constants.GRAND_TOTAL,webBean.getGrandTotal());
        request.setAttribute(Constants.CURRENCY_SYMBOLE,webBean.getCurrencySymbole());
      
        
        ForwardConfig forwardConfig = getForward(ctx,webBean,mapping,form);
        
        ActionForward forward = new ActionForward(forwardConfig.getPath());
              
        return forward;
    }
    
    private ForwardConfig getForward(Properties ctx, WebDocumentBean webBean, ActionMapping mapping, ActionForm form) throws OperationException
    {
        ActionConfig config = mapping.getModuleConfig().findActionConfig("/ViewInvoiceAction");
            
        MInvoice invoice = webBean.getInvoice();
      
        //vendor invoice -- isSoTrx is false
        //customer invoice -- isSoTrx true
        
        if (webBean.getOrder().getOrderType().equals(UDIOrderTypes.WEBSTORE_ORDER.getOrderType()))
        {
            //Webstore CI--customer invoice
            if (invoice.isSOTrx())
                return getWebstoreCI_Forward(webBean,config);
        }
        else
        {
	        //VI--vendor invoice
	        if (!invoice.isSOTrx())
	            return getVI_Forward(webBean,config);
	        
	        //CI--customer invoice
	        if (invoice.isSOTrx())
	            return getCI_Forward(webBean,config);
        }
        
        // DEFAULT
        return config.findForwardConfig(Constants.FINISHED_DO);
        
    } 
    
    public ForwardConfig getVI_Forward(WebDocumentBean invoiceBean, ActionConfig config) throws OperationException
    {
    	return config.findForwardConfig(Constants.WEBSTORE_INVOICE);
    }
    
    public ForwardConfig getWebstoreCI_Forward(WebDocumentBean invoiceBean, ActionConfig config) throws OperationException
    {
        MInvoice invoice = invoiceBean.getInvoice();
        
        String status = invoice.getDocStatus();
        
        if(status.equals(DocumentEngine.STATUS_Completed)) 
        {
            return config.findForwardConfig(Constants.WEBSTORE_INVOICE);
        }
    	
        return config.findForwardConfig(Constants.WEBSTORE_INVOICE);
    }
    
    public ForwardConfig getCI_Forward(WebDocumentBean invoiceBean, ActionConfig config) throws OperationException
    {
        MInvoice invoice = invoiceBean.getInvoice();
        
        MOrder order = new MOrder(invoice.getCtx(), invoice.getC_Order_ID(), null);
        
        
        int[] docTypes = MDocType.getAllIDs(MDocType.Table_Name, " ad_client_id="+ Env.getAD_Client_ID(invoice.getCtx()) + " and DOCSUBTYPESO='" + MDocType.DOCSUBTYPESO_POSOrder+ "'", null );
        
        
        //POS order
        if (order.getC_DocType_ID() == docTypes[0])
            return config.findForwardConfig(Constants.PAID_INVOICE);
     
        String status = invoice.getDocStatus();
        
        if (!invoice.isActive())
            return config.findForwardConfig(Constants.CANCEL_INVOICE);
      
        if(status.equals(DocumentEngine.STATUS_Completed)) 
        {
           return config.findForwardConfig(Constants.WEBSTORE_INVOICE);
        }
        return config.findForwardConfig(Constants.UNPAID_INVOICE);
    }

}
