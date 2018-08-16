/******************************************************************************
 * Copyright (C) 2008 Low Heng Sin  All Rights Reserved.                      *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *****************************************************************************/

package org.adempiere.webui.part;

import java.util.List;

import org.adempiere.webui.component.Tab;
import org.adempiere.webui.component.Tabbox;
import org.adempiere.webui.component.Tabpanel;
import org.adempiere.webui.component.Tabpanels;
import org.adempiere.webui.component.Tabs;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menupopup;

/**
 * 
 * @author Low Heng Sin
 * @author Raul Munoz, rmunoz@erpcya.com, ERPCyA http://www.erpcya.com
 *    <li>  FR [ 1711 ] Add popup to close tabs on zk 
 */
public class WindowContainer extends AbstractUIPart 
{
	private static final int MAX_TITLE_LENGTH = 30;
    
    private Tabbox           tabbox;

    public WindowContainer()
    {
    }
    
    /**
     * 
     * @param tb
     * @return WindowContainer
     */
    public static WindowContainer createFrom(Tabbox tb) 
    {
    	WindowContainer wc = new WindowContainer();
    	wc.tabbox = tb;
    	
    	return wc;
    }

    protected Component doCreatePart(Component parent)
    {
        tabbox = new Tabbox();
        
        Tabpanels tabpanels = new Tabpanels();
        Tabs tabs = new Tabs();

        tabbox.appendChild(tabs);
        tabbox.appendChild(tabpanels);
        tabbox.setWidth("100%");
        tabbox.setHeight("100%");
        
        if (parent != null)
        	tabbox.setParent(parent);
        else
        	tabbox.setPage(page);
        
        return tabbox;
    }
    
    /**
     * 
     * @param comp
     * @param title
     * @param closeable
     */
    public void addWindow(Component comp, String title, boolean closeable)
    {
        addWindow(comp, title, closeable, true);
    }
    
    /**
     * 
     * @param comp
     * @param title
     * @param closeable
     * @param enable
     */
    public void addWindow(Component comp, String title, boolean closeable, boolean enable) 
    {
    	insertBefore(null, comp, title, closeable, enable);
    }
    
    /**
     * 
     * @param refTab
     * @param comp
     * @param title
     * @param closeable
     * @param enable
     */
    public void insertBefore(Tab refTab, Component comp, String title, boolean closeable, boolean enable)
    {
        Tab tab = new Tab();
        title = title.replaceAll("[&]", "");
        if (title.length() <= MAX_TITLE_LENGTH) 
        {
        	tab.setLabel(title);
        }
        else
        {
        	tab.setTooltiptext(title);
        	title = title.substring(0, 27) + "...";
        	tab.setLabel(title);
        }
        tab.setClosable(closeable);
        
        //	FR [ 1711 ] Add popup to close tabs on zk
        Menupopup popupClose = new Menupopup();
        Menuitem item;
        if(tab.getIndex()!=0){
        	item = new Menuitem(Msg.getMsg(Env.getCtx(), "Close"));
        	popupClose.appendChild(item);
        	item.addEventListener(Events.ON_CLICK, new EventListener() {
        		public void onEvent(Event event) throws Exception {
        			int currentTabIndex = tab.getIndex();
        			int tabsSizeBeforeClose = tabbox.getTabs().getChildren().size();

        			if (tabsSizeBeforeClose == currentTabIndex + 1) {
        				currentTabIndex--;
        			}
        			if ( tab.getPreviousSibling() != null ) {
        				tab.onClose();
        				// Update the current tab index.
        				if ( tabsSizeBeforeClose != tabbox.getTabs().getChildren().size() )
        					tabbox.setSelectedIndex( currentTabIndex );
        			}
        		}
        	});

        	item = new Menuitem(Msg.getMsg(Env.getCtx(), "CloseOtherWindows"));
        	popupClose.appendChild(item);
        	item.addEventListener(Events.ON_CLICK, new EventListener() {
        		public void onEvent(Event event) throws Exception {
        			int focusTabIndex = 1;
        			List<Component> tabs = tabbox.getTabs().getChildren();
        			for ( int i = tabs.size() - 1; i > 0; i-- ) {
        				if(!((Tab)tabs.get( i )).equals(tab)){
        					((Tab)tabs.get( i )).setSelected(false);

        					((Tab)tabs.get( i )).onClose();
        				}
        			}
        			tabbox.setSelectedIndex(focusTabIndex);
        		}
        	});
        }

		item = new Menuitem(Msg.getMsg(Env.getCtx(), "CloseAllWindows"));
		item.addEventListener(Events.ON_CLICK, new EventListener() {
			public void onEvent(Event event) throws Exception {
				int focusTabIndex = 0;
				List<Component> tabs = tabbox.getTabs().getChildren();
				for ( int i = tabs.size() - 1; i > 0; i-- ) {
					((Tab)tabs.get( i )).setSelected(false);
					((Tab)tabs.get( i )).onClose();
				}
				tabbox.setSelectedIndex( focusTabIndex );
			}
		});
		popupClose.appendChild(item);
		popupClose.setWidth("auto");
		tab.setContext(popupClose);
		
		tab.addEventListener(Events.ON_RIGHT_CLICK, new EventListener() {
			public void onEvent(Event event) throws Exception {
				popupClose.setPage(tab.getPage());
				popupClose.open((Tab)event.getTarget());
			}
    });
		// FR [ 1711 ] End
		
        // fix scroll position lost coming back into a grid view tab
        tab.addEventListener(Events.ON_SELECT, new EventListener() {
			public void onEvent(Event event) throws Exception {
				Tab tab = (Tab)event.getTarget();
				org.zkoss.zul.Tabpanel panel = tab.getLinkedPanel();
				Component component = panel.getFirstChild();
				if (component != null && component.getAttribute(ITabOnSelectHandler.ATTRIBUTE_KEY) instanceof ITabOnSelectHandler)
				{
					ITabOnSelectHandler handler = (ITabOnSelectHandler) component.getAttribute(ITabOnSelectHandler.ATTRIBUTE_KEY);
					handler.onSelect();
				}
			}
		});

        Tabpanel tabpanel = null;
        if (comp instanceof Tabpanel) {
        	tabpanel = (Tabpanel) comp;
        } else {
        	tabpanel = new Tabpanel();
        	tabpanel.appendChild(comp);
        }
        tabpanel.setZclass("desktop-tabpanel");
        tabpanel.setHeight("100%");
        tabpanel.setWidth("100%");
        tabpanel.setStyle("position: relative;");
        
        if (refTab == null)  
        {
        	tabbox.getTabs().appendChild(tab);
        	tabbox.getTabpanels().appendChild(tabpanel);
        }
        else
        {
        	org.zkoss.zul.Tabpanel refpanel = refTab.getLinkedPanel();
        	tabbox.getTabs().insertBefore(tab, refTab);
        	tabbox.getTabpanels().insertBefore(tabpanel, refpanel);
        }

        if (enable)
        	setSelectedTab(tab);
        
    }
    
    /**
     * 
     * @param refTab
     * @param comp
     * @param title
     * @param closeable
     * @param enable
     */
    public void insertAfter(Tab refTab, Component comp, String title, boolean closeable, boolean enable)
    {
    	if (refTab == null)
    		addWindow(comp, title, closeable, enable);
    	else
    		insertBefore((Tab)refTab.getNextSibling(), comp, title, closeable, enable);
    }

    /**
     * 
     * @param tab
     */
    public void setSelectedTab(Tab tab)
    {
    	tabbox.setSelectedTab(tab);
    }

    /**
     * 
     * @return true if successfully close the active window
     */
    public boolean closeActiveWindow()
    {
    	Tab tab = (Tab) tabbox.getSelectedTab();
    	tabbox.getSelectedTab().onClose();
    	if (tab.getParent() == null)
    		return true;
    	else
    		return false;
    }
    
    /**
     * 
     * @return Tab
     */
    public Tab getSelectedTab() {
    	return (Tab) tabbox.getSelectedTab();
    }
    
    // Elaine 2008/07/21
    /**
     * @param tabNo
     * @param title
     * @param tooltip 
     */
    public void setTabTitle(int tabNo, String title, String tooltip)
    {
    	org.zkoss.zul.Tabs tabs = tabbox.getTabs();
    	Tab tab = (Tab) tabs.getChildren().get(tabNo);
    	tab.setLabel(title);
    	tab.setTooltiptext(tooltip);
    }
    //

	/**
	 * @return Tabbox
	 */
	public Tabbox getComponent() {
		return tabbox;
	}
}
