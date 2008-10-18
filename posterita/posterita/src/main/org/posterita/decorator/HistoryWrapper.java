package org.posterita.decorator;

import javax.servlet.http.HttpServletRequest;

import org.compiere.model.MOrder;
import org.compiere.process.DocAction;
import org.compiere.util.Env;
import org.displaytag.decorator.TableDecorator;
import org.posterita.Constants;
import org.posterita.beans.InventoryBean;
import org.posterita.beans.POSHistoryBean;
import org.posterita.businesslogic.POSManager;
import org.posterita.core.bean.ElementBean;
import org.posterita.core.businesslogic.ElementManager;
import org.posterita.lib.UdiConstants;

public class HistoryWrapper extends TableDecorator
{
	
    public String getLinkPartnerName()
    {
    	POSHistoryBean bean = (POSHistoryBean) this.getCurrentRowObject();
    	
    	String url = "POSCustomerAction.do?action=viewPOSCustomer&bpartnerId=" + bean.getBpartnerId();
    		
    	if ("No".equals(bean.getIsCustomer()))
    		url = "POSVendorAction.do?action=viewVendorDetails1&bpartnerId=" + bean.getBpartnerId();
    		
    	
    	String urlLink = "<a href=\"" + url + "\">" + bean.getPartnerName() + "</a>";
    	
    	return urlLink;
    }
    
    public String getReprint()
    {
    	POSHistoryBean bean = (POSHistoryBean) this.getCurrentRowObject();
    	
    	HttpServletRequest req = (HttpServletRequest) this.getPageContext().getRequest();
    	
    	String printing = POSManager.getDataFromCookie(req, "preference.printing");
    	ElementBean msgBean = ElementManager.getMsg(Env.getCtx(), "reprint");
    	
    	String link = "<a href=\"ReprintOrderAction.do?action=reprintOrder&orderId=" + bean.getOrderId() + "\">" +
    				  msgBean.getName() +
					  "</a>";
    	
    	if (printing != null && !printing.equals(""))
    	{
    		if ("true".equals(printing))
    		{
    			link = "<a href=\"javascript:void(0);\" onclick=\"printOrder(" + bean.getOrderId() + ")\">" +
    				   msgBean.getName()  +
    				   "</a>";
    		}
    	}
    	
    	return link; 
    }
    
    public String getPaymentRule()
    {
    	POSHistoryBean bean = (POSHistoryBean) this.getCurrentRowObject();
    	
    	String paymentRule = bean.getPaymentRule();
        String paymentRuleMsg = "";
        
    	if (MOrder.PAYMENTRULE_Cash.equals(paymentRule))
    	{
    		paymentRuleMsg = Constants.PAYMENT_RULE_CASH_MSG;
    	}
    	else if (MOrder.PAYMENTRULE_CreditCard.equals(paymentRule))
    	{
    		paymentRuleMsg = Constants.PAYMENT_RULE_CARD_MSG;
    	}
    	else if (MOrder.PAYMENTRULE_Check.equals(paymentRule))
    	{
    		paymentRuleMsg = Constants.PAYMENT_RULE_CHEQUE_MSG;
    	}
    	else if (UdiConstants.PAYMENTRULE_MIXED.equals(paymentRule))
    	{
    		paymentRuleMsg = Constants.PAYMENT_RULE_MIXED_MSG;
    	}
    	else if (MOrder.PAYMENTRULE_OnCredit.equals(paymentRule))
    	{
    		paymentRuleMsg = Constants.PAYMENT_RULE_CREDIT_MSG;
    	}
        
        ElementBean msgBean = ElementManager.getMsg(Env.getCtx(), paymentRuleMsg);
        
        return msgBean.getName();    	
    }
    
    public String getDocStatus()
    {
    	POSHistoryBean bean = (POSHistoryBean) this.getCurrentRowObject();
    	
    	String docStatus = bean.getDocStatus();
        String docStatusMsg = "";
        
    	if (DocAction.STATUS_Drafted.equals(docStatus))
    		docStatusMsg = Constants.DOC_STATUS_DRAFTED;
    	else
    	if (DocAction.STATUS_InProgress.equals(docStatus))
    		docStatusMsg = Constants.DOC_STATUS_INPROGRESS;
    	else
    	if (DocAction.STATUS_Completed.equals(docStatus))
    		docStatusMsg = Constants.DOC_STATUS_COMPLETED;
    	else
    	if (DocAction.STATUS_Closed.equals(docStatus))
    		docStatusMsg = Constants.DOC_STATUS_CLOSED;
    	else
    	if (DocAction.STATUS_Invalid.equals(docStatus))
    		docStatusMsg = Constants.DOC_STATUS_INVALID;
        
        ElementBean msgBean = ElementManager.getMsg(Env.getCtx(), docStatusMsg);
        return msgBean.getName();
    }   
    
    
    public String getDelete()
    {
    	InventoryBean bean = (InventoryBean) this.getCurrentRowObject();
    	
    	String docStatus = bean.getDocStatus();
    	
    	String link = "";
    	
    	if (!DocAction.STATUS_Completed.equals(docStatus))
    	{
    		 link = "<a href=\"DeleteInventoryAction.do?action=deleteInventory&inventoryId=" + bean.getInventoryId().toString() + "\">" +
			  "Delete" +
			  "</a>";
    		
    	}    	
    	return link;
    }
    
    
    public String getInventoryNo()
    {
    	InventoryBean bean = (InventoryBean) this.getCurrentRowObject();
    	
    	String link = "<a href=\"ViewInventoryAction.do?action=viewInventory&inventoryId="+bean.getInventoryId()+"&description="+bean.getDescription()+ "\">" +
		  bean.getInventoryNo() +
		  "</a>";
    	
    	return link;
    }
    
}
