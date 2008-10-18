package org.posterita.form;

import org.posterita.beans.StockMovementBean;
import org.posterita.struts.core.DefaultForm;

public class StockMovementForm extends DefaultForm 
{
	private static final long serialVersionUID = 1L;
	
	public StockMovementForm()
	{
		setBean(new StockMovementBean());
	}
	
}
