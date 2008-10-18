package org.posterita.form;

import org.posterita.beans.PaymentBean;
import org.posterita.struts.core.DefaultForm;

public class GeneralPaymentForm extends DefaultForm
{
	private static final long serialVersionUID = 1L;
	
	public GeneralPaymentForm()
	{
		setBean(new PaymentBean());
		addRequiredFields(new String[]{"amount","bpartnerId"});
	}

}
