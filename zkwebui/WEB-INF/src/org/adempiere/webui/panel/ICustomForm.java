package org.adempiere.webui.panel;


public interface ICustomForm 
{
	/**
	 * Called by org.adempiere.webui.panel.ADForm.openForm(int)
	 * @return
	 */
	public ADForm getForm();
}
