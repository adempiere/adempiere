/******************************************************************************
 * Copyright (C) 2008 Low Heng Sin                                            *
 * Copyright (C) 2009 Idalica Corporation                                     *
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
package org.adempiere.webui.component;

import java.util.ArrayList;
import java.util.List;

import org.adempiere.webui.component.ADTabListModel.ADTabLabel;
import org.adempiere.webui.theme.ThemeUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;

/**
 *
 * @author <a href="mailto:hengsin@gmail.com">Low Heng Sin</a>
 *
 */
public class ADButtonTabList extends Panel implements IADTabList, EventListener<Event> {

	/**
	 *
	 */
	private static final long serialVersionUID = -9203013067784673646L;
	private List<ADTabLabel> listItems = new ArrayList<ADTabLabel>();
	private List<EventListener<Event>> listeners = new ArrayList<EventListener<Event>>();
	private IADTab tabbox;
	private int selectedIndex = 0;
	private int tabPlacement = IADTab.LEFT;

	public ADButtonTabList() {
		ThemeUtils.addSclass("ad-adbuttontablist", this);
	}

	/**
	 * Set tab placement ( left or right )
	 * @param tabPlacement
	 */
	public void setTabplacement(int tabPlacement) {
		if (tabPlacement != IADTab.LEFT && tabPlacement != IADTab.RIGHT)
			return;
		else if (tabPlacement == this.tabPlacement)
			return;
		else {
			this.tabPlacement = tabPlacement;
			this.invalidate();
		}
	}

	public synchronized void refresh() {
		List<?> childs = getChildren();
		int childCount = childs.size();
		for (int c = childCount - 1; c >=0; c--) {
			removeChild((Component) childs.get(c));
		}
		Object[] items = listItems.toArray();
		int tabWidth = 100;
		List<Tab> tabList = new ArrayList<Tab>();
		
		// Try a tab box
        Tabbox nav_tb = new Tabbox();
        nav_tb.setVflex("1");				// TODO prepare for other orientations
        this.appendChild(nav_tb);
        nav_tb.setSclass("adwindow-nav-tabbox");
        
        // TODO prepare to handle other tab types
        nav_tb.setOrient(tabPlacement == IADTab.LEFT? "left" : "right");
        
        Tabs tabs = new Tabs();

        // TODO prepare to handle other tab types
        tabs.setVflex("1");  //  This is ok for vertical style tabs (left/right)
        tabs.setHflex("min"); // Minium width.

        nav_tb.appendChild(tabs);
        tabs.setSclass("adwindow-nav-tabbox-tabs");
			
		for (int i = 0; i < items.length; i++) {
			
			ADTabLabel tabLabel = (ADTabLabel) items[i];
			Tab tab = new Tab(tabLabel.label);
			tabs.appendChild(tab);
			ThemeUtils.addSclass(tabPlacement == IADTab.LEFT? "left" : "right", tab);
			
			StringBuffer sclass = new StringBuffer("adwindow-nav-tabbox-tabs-tab");
			ThemeUtils.addSclass(sclass.toString(), tab);
			
			if (tabPlacement == IADTab.LEFT) {
				sclass.append("-left");
			}
			else {
				sclass.append("-right");
			}
			ThemeUtils.addSclass(sclass.toString(), tab);

			int s = tabbox.getSelectedIndex();
			if ( s == i) {
				sclass.append("-selected");
				tab.setDisabled(false);
				//tab.setDynamicProperty("disabled", null);
			} else {
				if (!tabbox.canNavigateTo(s, i)) {
					sclass.append("-disabled");
					//button.setDynamicProperty("disabled", "disabled");
					tab.setDisabled(true);
					if (!tabbox.isDisplay(i))
						tab.setVisible(false);
					else
						tab.setVisible(true);
				} else {
					sclass.append("-unselected");
					tab.setDisabled(false);
					tab.setVisible(true);
					//button.setDynamicProperty("disabled", null);
					//button.setVisible(true);
				}
			}
			ThemeUtils.addSclass(sclass.toString(), tab);
			
			// The following sets the sclass for the tabs and the theme can display the tabs accordingly.
			// TODO - prepare for other orientations			
			if (tabPlacement == IADTab.LEFT) {
				ThemeUtils.addSclass("tab-left-level-" + tabLabel.tabLevel, tab);
			}
			else if (tabPlacement == IADTab.RIGHT) {
				ThemeUtils.addSclass("tab-right-level-" + tabLabel.tabLevel, tab);
			} 

			tabList.add(tab);
			tab.addEventListener(Events.ON_CLICK, this);
			
			// Set the currently selected tab as selected
			tab.setSelected(selectedIndex==i);
		}				
	}

	public int getSelectedIndex() {
		return selectedIndex;
	}

	public void setSelectedIndex(int index) {
		this.selectedIndex = index;
	}

	public synchronized void setItems(List<ADTabLabel> listItems) {
		this.listItems = listItems;
		refresh();
	}

	public void setADTab(IADTab adTab) {
		this.tabbox = adTab;
	}

	public void addSelectionEventListener(EventListener listener) {
		listeners.add(listener);
	}

	public boolean removeSelectionEventListener(EventListener listener) {
		return listeners.remove(listener);
	}

	public void onEvent(Event event) throws Exception {
		Tab tab = (Tab) event.getTarget();
		selectedIndex = tab.getIndex();
		
		/*
		int i = 0;
		for (ADTabLabel tabLabel : listItems) {
			Text text = (Text) tab.getFirstChild();
			if (tabLabel.label.equals(text.getValue())) {
				break;
			}
			i++;
		}

		selectedIndex = i;
		*/
		Event selectEvent = new Event(Events.ON_SELECT, this);		
		for (EventListener listener : listeners) {
			listener.onEvent(selectEvent);
		}
	}
}
