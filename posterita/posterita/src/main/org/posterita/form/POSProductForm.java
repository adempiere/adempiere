package org.posterita.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.posterita.beans.ProductBean;
import org.posterita.struts.core.DefaultForm;
import org.posterita.struts.pos.POSProductAction;

public class POSProductForm extends DefaultForm
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public POSProductForm()
	{
		setBean(new ProductBean());
	}
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)
	{
		if(POSProductAction.VIEW_PRODUCT.equals(action) || POSProductAction.GET_CREATE_PRODUCT_FORM.equals(action))
		{
			
		}
		else if (POSProductAction.CREATE_OR_UPDATE_PRODUCT.equals(action))
		{
			addRequiredFields(new String[] {"productName","revenueRecognition","description"}); 
		}
		else
		{
			addRequiredFields(new String[] {"productName","purchasePriceStandard","description","salesPriceStandard","salesPriceLimit","salesPriceList"});
		}
		
		return super.validate(mapping, request);
	}
}
