package org.adempiere.webui.panel;

import org.adempiere.webui.theme.ThemeUtils;

public class CustomForm extends ADForm
{
	public CustomForm() {
		super();
		ThemeUtils.addSclass("ad-customform", this);
	}


	private static final long serialVersionUID = 1L;

	
	@Override
	protected void initForm() 
	{
		
	}
}
