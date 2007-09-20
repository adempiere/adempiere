/*
 * Created on Mar 4, 2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.posterita.struts.order;

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
import org.compiere.model.MOrder;
import org.compiere.model.MUser;
import org.compiere.process.DocumentEngine;
import org.compiere.util.Env;

import org.posterita.Constants;
import org.posterita.beans.DocumentBean;
import org.posterita.beans.WebDocumentBean;
import org.posterita.businesslogic.OrderReferenceManager;
import org.posterita.core.TmkJSPEnv;
import org.posterita.exceptions.ApplicationException;
import org.posterita.exceptions.OperationException;
import org.posterita.order.UDIOrderTypes;
import org.posterita.struts.core.BaseAction;
import org.posterita.struts.core.DefaultForm;


public class ViewOrderAction extends BaseAction
{
    
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws OperationException, ApplicationException
    {
        
        MessageResources resources = (MessageResources) request.getAttribute(Globals.MESSAGES_KEY);
        
        //String securityException =  resources.getMessage("error.securityException");
        String operationException =  resources.getMessage("error.operationException");
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        
        DefaultForm df = (DefaultForm) form;
        DocumentBean bean = (DocumentBean) df.getBean();
        Integer orderID = bean.getDocumentId();
       
        // retrieve from cache
        WebDocumentBean webBean = null;
        
        MOrder order = null;
        if (orderID!= null)
        {
            order = new MOrder(ctx, orderID.intValue(), null);
            webBean = OrderReferenceManager.getWebOrderBean(ctx,order);
            request.getSession().setAttribute(Constants.WEB_ORDER_BEAN, webBean);
        }
        
        
        if (webBean == null)
            webBean = (WebDocumentBean) request.getSession().getAttribute(Constants.WEB_ORDER_BEAN);
        
        if (webBean == null)
            throw new OperationException(operationException);

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
        request.getSession().setAttribute(Constants.IS_PAID, webBean.getIsPaid());
        
        ForwardConfig forwardConfig = getForward(ctx,webBean,mapping,form);
        
        ActionForward forward = new ActionForward(forwardConfig.getPath());
      
        return forward;
    }
    
    
    private ForwardConfig getForward(Properties ctx, WebDocumentBean webBean, ActionMapping mapping, ActionForm form) throws OperationException
    {
        ActionConfig config = mapping.getModuleConfig().findActionConfig("/ViewOrderAction");
            
        MOrder order = webBean.getOrder();
      
        String orderType = order.getOrderType();
        
        webBean.setCtx(ctx);
        
        if (orderType == null)
            throw new OperationException("Cannot  determine type of order. Type is null");
        
   
        //Tamak POS Order
        if(orderType.equals(UDIOrderTypes.POS_ORDER.getOrderType()))
            return getCustomerOrderForward(webBean,config);
      
      
        
        //Material Return Order
        if(orderType.equals(UDIOrderTypes.CUSTOMER_RETURN_ORDER.getOrderType()))
            return getMROForward(webBean, config);        
        
        // WEBSTORE ORDER
        if (orderType.equals(UDIOrderTypes.WEBSTORE_ORDER.getOrderType()))
            return getWebstoreOrderForward(ctx, webBean,config);        
            
        // DEFAULT
        return config.findForwardConfig(Constants.FINISHED_DO);  
    }     






	public ForwardConfig getAllocationOrderForward(WebDocumentBean bean, ActionConfig config)
    {
        MOrder order = bean.getOrder();
        
        String docStatus = order.getDocStatus();
        
        if (!order.isActive())
            return config.findForwardConfig(Constants.CANCEL_DO);
        
        if (docStatus.equals(DocumentEngine.STATUS_Drafted))
            return config.findForwardConfig(Constants.UNFINISHED_AL);
        
        if (docStatus.equals(DocumentEngine.STATUS_InProgress))
            return config.findForwardConfig(Constants.SWAP_VIN);
        
        if (docStatus.equals(DocumentEngine.STATUS_Voided))
            return config.findForwardConfig(Constants.UNFINISHED_AL);
        
        return config.findForwardConfig(Constants.GENERATE_INVOICE);
    }
    
    // same as for customer order
    private ForwardConfig getUsedCarPurchaseOrderForward(WebDocumentBean webBean, ActionConfig config) throws OperationException
    {
        MOrder order = webBean.getOrder();
             
        String status = order.getDocStatus();
             
        if (status.equals(DocumentEngine.STATUS_Completed))
        	return config.findForwardConfig(Constants.PROGRESS_TI);
             
        if (!order.isActive())
            return config.findForwardConfig(Constants.CANCEL_DO);
          
     
        return config.findForwardConfig(Constants.PROGRESS_TI);  
    }


    private ForwardConfig getCustomerOrderForward(WebDocumentBean webBean, ActionConfig config) throws OperationException
    {
        MOrder order = webBean.getOrder();      
     
        String status = order.getDocStatus();
        
        
        if (status.equals(DocumentEngine.STATUS_Drafted))
        {         
            return config.findForwardConfig(Constants.UNFINISHED_NR);
        }
        
        if(status.equals(DocumentEngine.STATUS_InProgress))
        	return config.findForwardConfig(Constants.PROGRESS_NR);
        	
        if (status.equals(DocumentEngine.STATUS_Completed))
           return config.findForwardConfig(Constants.FINISHED_NR);
       
        
        if (!order.isActive())
            return config.findForwardConfig(Constants.CANCEL_DO);
       
        return config.findForwardConfig(Constants.UNFINISHED_NR);  
    }
    
	private ForwardConfig getMROForward(WebDocumentBean webBean, ActionConfig config)
	{
        MOrder order = webBean.getOrder();
        
        if (order.isSOTrx())
            return getSO_MROForward(webBean, config);
        
        return getPO_MROForward(webBean, config);
	}    
    



	private ForwardConfig getSO_MROForward(WebDocumentBean webBean, ActionConfig config)
	{
        MOrder order = webBean.getOrder();
        
        if (!order.isActive())
        {
            return config.findForwardConfig(Constants.CANCEL_DO);
        }
      
        return config.findForwardConfig(Constants.PROGRESS_DT);
	}

    private ForwardConfig getPO_MROForward(WebDocumentBean orderBean, ActionConfig config)
	{
        MOrder order = orderBean.getOrder();
        
        
        if (order == null)
        {	

            return config.findForwardConfig(Constants.CANCEL_DO);
        }
     
        String status = order.getDocStatus();
        
        if (status.equals(DocumentEngine.STATUS_Drafted))
        {
            //orderStatusMessage = "status drafted";
        }
        
        if (!order.isActive())
            return config.findForwardConfig(Constants.CANCEL_DO);
          
     
        return config.findForwardConfig(Constants.PROGRESS_DT);        
		
	}

	private ForwardConfig getWebstoreOrderForward(Properties ctx, WebDocumentBean webBean, ActionConfig config) throws OperationException
    {
        
        int userId = Env.getAD_User_ID(ctx);
        new MUser(ctx, userId, null);
        
        
        return getWebstoreSOForward(webBean, config);
    }
    
    public ForwardConfig getWebstoreSOForward(WebDocumentBean orderBean, ActionConfig config) throws OperationException
    {

        MOrder order = orderBean.getOrder();
        
        
        if (order == null)
            throw new OperationException("Order cannot be NULL!");
     
        
        return config.findForwardConfig(Constants.WEBSTORE_PROGRESS_DO);        
    }	


    public ForwardConfig getPO_SCO_Forward(WebDocumentBean webBean, ActionConfig config)
    {
        MOrder order = webBean.getOrder();
        
        if (order == null)
        {
            return config.findForwardConfig(Constants.CANCEL_SCO);
        }
        
        if (!order.isActive())
        {
            return config.findForwardConfig(Constants.CANCEL_SCO);
        }
       
        
        String status = order.getDocStatus();
        
        if (status.equals(DocumentEngine.STATUS_Drafted))
        {
            return config.findForwardConfig(Constants.UNFINISHED_SCO);
        }
        
        
        if (status.equals(DocumentEngine.STATUS_Voided))
        {
            return config.findForwardConfig(Constants.PROGRESS_SCO);
        }
        
       
        return config.findForwardConfig(Constants.PROGRESS_SCO);
        
    }
    
}
