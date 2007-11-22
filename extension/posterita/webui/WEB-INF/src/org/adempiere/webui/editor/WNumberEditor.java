/******************************************************************************
 * Product: Posterita Ajax UI 												  *
 * Copyright (C) 2007 Posterita Ltd.  All Rights Reserved.                    *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * Posterita Ltd., 3, Draper Avenue, Quatre Bornes, Mauritius                 *
 * or via info@posterita.org or http://www.posterita.org/                     *
 *****************************************************************************/

package org.adempiere.webui.editor;

import org.adempiere.webui.component.NumberBox;
import org.adempiere.webui.event.ContextMenuEvent;
import org.adempiere.webui.event.ValueChangeEvent;
import org.compiere.model.GridField;
import org.compiere.util.DisplayType;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;

/**
 *
 * @author  <a href="mailto:agramdass@gmail.com">Ashley G Ramdass</a>
 * @date    Mar 11, 2007
 * @version $Revision: 0.10 $
 */
public class WNumberEditor extends WEditor
{
    public final String[] LISTENER_EVENTS = {Events.ON_CHANGE};
    
    public static final int MAX_DISPLAY_LENGTH = 20;

    private NumberBox comp;
    
    private String oldValue;
    
    private boolean mandatory = false;
    
    public WNumberEditor(GridField gridField)
    {
        super(new NumberBox(gridField.getDisplayType() == DisplayType.Integer),
                gridField);
        comp = (NumberBox)super.component;
        init();
    }
    
    public WNumberEditor(GridField gridField, boolean integral)
    {
        super(new NumberBox(integral), gridField);
        comp = (NumberBox)super.component;
        init();
    }

    private void init()
    {
        comp.setMaxlength(gridField.getFieldLength());
        comp.setCols(MAX_DISPLAY_LENGTH);
        comp.setTooltiptext(gridField.getDescription());
    }
    
    public void onEvent(Event event)
    {
        String newValue = comp.getValue();
        ValueChangeEvent changeEvent = new ValueChangeEvent(this, this.getColumnName(), oldValue, newValue);
        super.fireValueChange(changeEvent);
        oldValue = newValue;
    }

    @Override
    public String getDisplay()
    {
        return comp.getValue();
    }

    @Override
    public Object getValue()
    {
        return comp.getValue();
    }

    @Override
    public boolean isMandatory()
    {
        return mandatory;
    }

    @Override
    public void setMandatory(boolean mandatory)
    {
        this.mandatory = mandatory;
    }

    @Override
    public void setValue(Object value)
    {
        if (value != null)
        {
            comp.setValue(value.toString());
        }
        else
        {
            comp.setValue("0");
        }
    }
    
    public String[] getEvents()
    {
        return LISTENER_EVENTS;
    }
    
    public void onMenu(ContextMenuEvent evt) 
	{
    	/* Wrong implementation for Value Preference - Swing is being called	
	 	if (WEditorPopupMenu.PREFERENCE_EVENT.equals(evt.getContextEvent()))
		{
			if (MRole.getDefault().isShowPreference())
				ValuePreference.start (this.getGridField(), getValue());
			return;
		}
    	 */
	}
}
