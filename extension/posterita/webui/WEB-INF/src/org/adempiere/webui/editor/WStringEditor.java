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

import org.adempiere.webui.component.Textbox;
import org.adempiere.webui.event.ContextMenuEvent;
import org.adempiere.webui.event.ValueChangeEvent;
import org.compiere.grid.ed.ValuePreference;
import org.compiere.model.GridField;
import org.compiere.model.MRole;
import org.compiere.util.DisplayType;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;

/**
 *
 * @author  <a href="mailto:agramdass@gmail.com">Ashley G Ramdass</a>
 * @date    Mar 11, 2007
 * @version $Revision: 0.10 $
 */
public class WStringEditor extends WEditor
{
    private static final String[] LISTENER_EVENTS = {Events.ON_CHANGE};
    
    protected Textbox textbox;
    
    private String oldText;
    
    
    public WStringEditor(GridField gridField)
    {
        super(new Textbox(), gridField);
        textbox = (Textbox)super.component;
        
        init();
    }
    
    private void init()
    {
        textbox.setMaxlength(gridField.getFieldLength());
        int displayLength = gridField.getDisplayLength();
        if (displayLength <= 0 || displayLength > MAX_DISPLAY_LENGTH)
        {
            displayLength = MAX_DISPLAY_LENGTH;
        }
        textbox.setCols(displayLength);    
        
        if (gridField.getDisplayType() == DisplayType.Text)
        {
            textbox.setMultiline(true);
            textbox.setRows(3);
        }
        else if (gridField.getDisplayType() == DisplayType.TextLong)
        {
            textbox.setMultiline(true);
            textbox.setRows(5);
        }
        else if (gridField.getDisplayType() == DisplayType.Memo)
        {
            textbox.setMultiline(true);
            textbox.setRows(8);
        }
    }

    public void onEvent(Event event)
    {
        String newText = textbox.getValue();
        ValueChangeEvent changeEvent = new ValueChangeEvent(this, this.getColumnName(), oldText, newText);
        super.fireValueChange(changeEvent);
        oldText = newText;
    }

    @Override
    public String getDisplay()
    {
        return textbox.getValue();
    }

    @Override
    public Object getValue()
    {
        return textbox.getValue();
    }

    @Override
    public void setValue(Object value)
    {
        if (value != null)
        {
            textbox.setValue(value.toString());
        }
        else
        {
            textbox.setValue("");
        }
        oldText = textbox.getValue();
    }
    
    protected void setTypePassword(boolean password)
    {
        if (password)
        {
            textbox.setType("password");
        }
        else
        {
            textbox.setType("text");
        }
    }
    
    @Override
    public String[] getEvents()
    {
        return LISTENER_EVENTS;
    }
    
    public void onMenu(ContextMenuEvent evt) 
	{
		if (WEditorPopupMenu.PREFERENCE_EVENT.equals(evt.getContextEvent()))
		{
			if (MRole.getDefault().isShowPreference())
				ValuePreference.start (this.getGridField(), getValue());
			return;
		}
	}
}
