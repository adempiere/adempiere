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

import java.net.URL;

import org.adempiere.webui.component.Urlbox;
import org.adempiere.webui.event.ValueChangeEvent;
import org.adempiere.webui.window.FDialog;
import org.compiere.model.GridField;
import org.compiere.util.Env;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;

public class WUrlEditor extends WEditor 
{
	private static final String[] LISTENER_EVENTS = {Events.ON_CLICK, Events.ON_CHANGE};
	
	private Urlbox urlbox;

	public WUrlEditor(GridField gridField) 
	{
		super(new Urlbox(), gridField);
		this.urlbox = (Urlbox)super.component;
		urlbox.setButtonImage("/images/Online16.gif");
	}
	

	@Override
	public void setValue(Object value) 
	{
        if (value == null)
        {
            urlbox.setText("");
        }
        else
        {
            urlbox.setText(String.valueOf(value));
        }
	}

	@Override
	public Object getValue() 
	{
		return urlbox.getText();
	}

	@Override
	public String getDisplay() 
	{
		return urlbox.getText();
	}

	public void onEvent(Event event) 
	{
		if (Events.ON_CHANGE.equals(event.getName()))
		{
			ValueChangeEvent changeEvent = new ValueChangeEvent(this, this.getColumnName(), urlbox.getText(), urlbox.getText());
			fireValueChange(changeEvent);
		}
		else if (Events.ON_CLICK.equals(event.getName()))
		{
			String urlString =urlbox.getText();
            String message = null;
			if (urlString != null && urlString.length() > 0)
			{
				try
                {
                    URL url = new URL(urlString);
                    Env.startBrowser(urlString);
                    return;
                }
                catch(Exception e)
                {
                    message = e.getMessage();
                }
			}
            FDialog.warn(0, this.getComponent(), "URLnotValid", message);
                       
		}
	}
	
	public String[] getEvents()
    {
        return LISTENER_EVENTS;
    }
	
	
}
