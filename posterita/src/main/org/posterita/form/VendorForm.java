package org.posterita.form;

import org.posterita.beans.VendorBean;
import org.posterita.struts.core.DefaultForm;

public class VendorForm extends DefaultForm
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VendorForm()
	{
		setBean(new VendorBean());
		addRequiredFields(new String[]{"partnerName"});
		
	}
}
