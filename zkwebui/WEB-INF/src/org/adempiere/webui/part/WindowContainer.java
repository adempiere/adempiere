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

package org.adempiere.webui.part;

import org.adempiere.webui.LayoutUtils;
import org.adempiere.webui.component.Tab;
import org.adempiere.webui.component.Tabbox;
import org.adempiere.webui.component.Tabpanel;
import org.adempiere.webui.component.Tabpanels;
import org.adempiere.webui.component.Tabs;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zkex.zul.Borderlayout;

/**
 *
 * @author <a href="mailto:agramdass@gmail.com">Ashley G Ramdass</a>
 * @date Mar 3, 2007
 * @version $Revision: 0.10 $
 */
public class WindowContainer extends AbstractUIPart implements EventListener
{
    private static final long serialVersionUID = 1L;
    
    private Tabbox           tabbox;

    public WindowContainer()
    {
    }
    
    public static WindowContainer createFrom(Tabbox tb) 
    {
    	WindowContainer wc = new WindowContainer();
    	wc.tabbox = tb;
    	
    	return wc;
    }

    protected Component doCreatePart(Component parent)
    {
        tabbox = new Tabbox();
        tabbox.setSclass("desktop-tb");
        
        Tabpanels tabpanels = new Tabpanels();
        Tabs tabs = new Tabs();

        tabbox.appendChild(tabs);
        tabbox.appendChild(tabpanels);
        tabbox.setWidth("100%");
        tabbox.setHeight("100%");
        
        tabbox.addEventListener(Events.ON_SELECT, this);

        if (parent != null)
        	tabbox.setParent(parent);
        else
        	tabbox.setPage(page);
        
        return tabbox;
    }

    public void addWindow(Component comp, String title, boolean closeable)
    {
        addWindow(comp, title, closeable, true);
    }

    public void addWindow(Component comp, String title, boolean closeable, boolean enable)
    {
        Tab tab = new Tab();
        tab.setLabel(title);
        tab.setClosable(closeable);
//        tab.setHeight("20px");

        Tabpanel tabpanel = null;
        if (comp instanceof Tabpanel) {
        	tabpanel = (Tabpanel) comp;
        } else {
        	tabpanel = new Tabpanel();
        	tabpanel.appendChild(comp);
        }                
        tabpanel.setHeight("100%");
        tabpanel.setWidth("100%");
        tabpanel.setSclass("desktop-tabpanel");
        tabpanel.setStyle("position: absolute;");
        
        tabbox.getTabs().appendChild(tab);
        tabbox.getTabpanels().appendChild(tabpanel);

        if (enable)
        	setSelectedTab(tab);
        
        deferLayout();
    }

    public void setSelectedTab(Tab tab)
    {
    	tabbox.setSelectedTab(tab);
    }

    public void removeWindow()
    {
    	tabbox.getSelectedTab().onClose();
    }
    
    public Tab getSelectedTab() {
    	return (Tab) tabbox.getSelectedTab();
    }

	public void onEvent(Event event) throws Exception {
		if (Events.ON_SELECT.equals(event.getName()))
			deferLayout();
	}
	
	private void deferLayout() {
		Tabpanel panel = (Tabpanel) tabbox.getSelectedPanel();
		if (panel.getFirstChild() instanceof Borderlayout) {
			LayoutUtils.sendDeferLayoutEvent((Borderlayout) panel.getChildren().get(0), 350);
		}
	}

	public Component getComponent() {
		return tabbox;
	}
}
