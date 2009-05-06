/******************************************************************************
 * Copyright (C) 2008 Low Heng Sin                                            *
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
import org.zkoss.zhtml.Button;
import org.zkoss.zhtml.Text;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;

/**
 *
 * @author <a href="mailto:hengsin@gmail.com">Low Heng Sin</a>
 *
 */
public class ADButtonTabList extends Panel implements IADTabList, EventListener {

	/**
	 *
	 */
	private static final long serialVersionUID = -9203013067784673646L;
	private List<ADTabLabel> listItems = new ArrayList<ADTabLabel>();
	private List<EventListener> listeners = new ArrayList<EventListener>();
	private IADTab tabbox;
	private int selectedIndex = 0;
	private int tabPlacement = IADTab.LEFT;

	public ADButtonTabList() {
		this.setStyle("margin:0;padding:0");
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
		List childs = getChildren();
		int childCount = childs.size();
		for (int c = childCount - 1; c >=0; c--) {
			removeChild((Component) childs.get(c));
		}
		Object[] items = listItems.toArray();
		for (int i = 0; i < items.length; i++) {
			ADTabLabel tabLabel = (ADTabLabel) items[i];
			Button button = new Button();
			Text text = new Text(tabLabel.label);
			button.appendChild(text);
			int s = tabbox.getSelectedIndex();

			if ( s == i) {
				button.setSclass("adwindow-navbtn-sel");
				button.setDynamicProperty("disabled", null);
			} else {
				if (!tabbox.canNavigateTo(s, i)) {
					button.setDynamicProperty("disabled", "disabled");
					button.setSclass("adwindow-navbtn-dis");
					if (!tabbox.isDisplay(i))
						button.setVisible(false);
					else
						button.setVisible(true);
				} else {
					button.setDynamicProperty("disabled", null);
					button.setSclass("adwindow-navbtn-uns");
					button.setVisible(true);
				}
			}

			String style = (tabPlacement == IADTab.LEFT ? "margin-left:" : "margin-right:") + (tabLabel.tabLevel*15+5) + "px";
			String width = (195 - tabLabel.tabLevel*15)+"px";
			style = style + "; width:" + width;
			button.setStyle(style);

			button.setParent(this);
			button.addEventListener(Events.ON_CLICK, this);
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
		Event selectEvent = new Event(Events.ON_SELECT, this);
		Button button = (Button) event.getTarget();
		int i = 0;
		for (ADTabLabel tabLabel : listItems) {
			Text text = (Text) button.getFirstChild();
			if (tabLabel.label.equals(text.getValue())) {
				break;
			}
			i++;
		}

		selectedIndex = i;
		for (EventListener listener : listeners) {
			listener.onEvent(selectEvent);
		}
	}
}
