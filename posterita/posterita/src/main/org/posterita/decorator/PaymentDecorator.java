package org.posterita.decorator;

import javax.servlet.jsp.PageContext;

import org.compiere.model.MOrder;
import org.compiere.util.Env;
import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;
import org.posterita.Constants;
import org.posterita.core.bean.ElementBean;
import org.posterita.core.businesslogic.ElementManager;
import org.posterita.lib.UdiConstants;

public class PaymentDecorator implements DisplaytagColumnDecorator
{

    public Object decorate(Object columnValue, PageContext pageContext, MediaTypeEnum media) throws DecoratorException
    {
        String paymentRule = (String) columnValue;
        
        ElementBean bean = ElementManager.getMsg(Env.getCtx(), getPaymentRuleDisplayMessage(paymentRule));
        
        return bean.getName();
    }	
	
    
    public static String getPaymentRuleDisplayMessage(String paymentRule)
    {
    	if (MOrder.PAYMENTRULE_Cash.equals(paymentRule))
    		return Constants.PAYMENT_RULE_CASH_MSG;
    	else
    	if (MOrder.PAYMENTRULE_CreditCard.equals(paymentRule))
    		return Constants.PAYMENT_RULE_CARD_MSG;
    	else
    	if (MOrder.PAYMENTRULE_Check.equals(paymentRule))
    		return Constants.PAYMENT_RULE_CHEQUE_MSG;
    	else
    	if (UdiConstants.PAYMENTRULE_MIXED.equals(paymentRule))
    		return Constants.PAYMENT_RULE_MIXED_MSG;
    	else
    	if (MOrder.PAYMENTRULE_OnCredit.equals(paymentRule))
    		return Constants.PAYMENT_RULE_CREDIT_MSG;
    	
    	return "";
    }
    
    
	
}
