package org.posterita.form;

import org.posterita.beans.InventoryLineBean;
import org.posterita.struts.core.DefaultForm;

public class InventoryLineForm extends DefaultForm
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public InventoryLineForm()
    { 
        setBean( new InventoryLineBean()); 
    }
}
