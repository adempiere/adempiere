package org.adempiere.webui.panel;


public interface IFormController 
{
	/**
	 * Called by org.adempiere.webui.panel.ADForm.openForm(int)
	 * @return
	 */
	public ADForm getForm();
}
