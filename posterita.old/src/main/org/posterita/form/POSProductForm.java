package org.posterita.form;

import org.posterita.beans.ProductBean;
import org.posterita.struts.core.DefaultForm;

public class POSProductForm extends DefaultForm
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public POSProductForm()
	{
		setBean(new ProductBean());
    	addRequiredFields(new String[] {"productName","purchasePriceStandard","description","salesPriceStandard","salesPriceLimit","salesPriceList"});
	}
}
