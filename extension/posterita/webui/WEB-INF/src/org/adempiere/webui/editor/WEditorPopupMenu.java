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

import java.util.ArrayList;

import org.adempiere.webui.event.ContextMenuEvent;
import org.adempiere.webui.event.ContextMenuListener;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menupopup;

/**
 *
 * @author  <a href="mailto:agramdass@gmail.com">Ashley G Ramdass</a>
 * @date    Mar 25, 2007
 * @version $Revision: 0.10 $
 */
public class WEditorPopupMenu extends Menupopup implements EventListener
{
    private static final long serialVersionUID = 1L;

    private static final String EVENT_ATTRIBUTE = "EVENT";
    public static final String ZOOM_EVENT = "ZOOM";
    public static final String REQUERY_EVENT = "REQUERY";
    public static final String PREFERENCE_EVENT = "VALUE_PREFERENCE";
    public static final String NEW_EVENT = "NEW_RECORD";
   
    private boolean newEnabled = true;
    private boolean zoomEnabled  = true;
    private boolean requeryEnabled = true;
    private boolean preferencesEnabled = true;
    
    private Menuitem zoomItem;
    private Menuitem requeryItem;
    private Menuitem prefItem;
    private Menuitem newItem;
   
    
    private ArrayList<ContextMenuListener> menuListeners = new ArrayList<ContextMenuListener>();
    
    public WEditorPopupMenu(boolean zoom, boolean requery, boolean preferences)
    {
        super();
        this.zoomEnabled = zoom;
        this.requeryEnabled = requery;
        this.preferencesEnabled = preferences;
        init();
    }
    
    public WEditorPopupMenu(boolean zoom, boolean requery, boolean preferences, boolean newRecord)
    {
        super();
        this.zoomEnabled = zoom;
        this.requeryEnabled = requery;
        this.preferencesEnabled = preferences;
        this.newEnabled = newRecord;
        init();
    }

    
    private void init()
    {
        if (zoomEnabled)
        {
            zoomItem = new Menuitem();
            zoomItem.setAttribute(EVENT_ATTRIBUTE, ZOOM_EVENT);
            zoomItem.setLabel("Zoom");
            zoomItem.setImage("/images/Zoom16.gif");
            zoomItem.addEventListener(Events.ON_CLICK, this);
            
            this.appendChild(zoomItem);
        }
        
        if (requeryEnabled)
        {
            requeryItem = new Menuitem();
            requeryItem.setAttribute(EVENT_ATTRIBUTE, REQUERY_EVENT);
            requeryItem.setLabel("ReQuery");
            requeryItem.setImage("/images/Refresh16.gif");
            requeryItem.addEventListener(Events.ON_CLICK, this);
            this.appendChild(requeryItem);
        }
        
        if (preferencesEnabled)
        {
            prefItem = new Menuitem();
            prefItem.setAttribute(EVENT_ATTRIBUTE, PREFERENCE_EVENT);
            prefItem.setLabel("Value Preference");
            prefItem.setImage("/images/VPreference16.gif");
            prefItem.addEventListener(Events.ON_CLICK, this);
            this.appendChild(prefItem);
        }
        
        if (newEnabled)
        {
        	newItem = new Menuitem();
        	newItem.setAttribute(EVENT_ATTRIBUTE, NEW_EVENT);
        	newItem.setLabel("New Record");
        	newItem.setImage("/images/New16.gif");
        	newItem.addEventListener(Events.ON_CLICK, this);
        	this.appendChild(newItem);
        }
    }
    
    public void addMenuListener(ContextMenuListener listener)
    {
        menuListeners.add(listener);
    }

    public boolean isAsap()
    {
        return true;
    }

    public void onEvent(Event event)
    {
        String evt = (String)event.getTarget().getAttribute(EVENT_ATTRIBUTE);
        
        if (evt != null)
        {
            ContextMenuEvent menuEvent = new ContextMenuEvent(evt);
            
            for (ContextMenuListener listener : menuListeners)
            {
                listener.onMenu(menuEvent);
            }
        }
    }
}
