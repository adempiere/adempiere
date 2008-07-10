package org.adempiere.webui.component;

import java.util.ArrayList;
import java.util.List;

import org.adempiere.webui.LayoutUtils;
import org.adempiere.webui.component.ADTabListModel.ADTabLabel;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;

/**
 * 
 * @author <a href="mailto:hengsin@gmail.com">Low Heng Sin</a>
 *
 */
public class ADButtonTabList extends Panel implements IADTabList, EventListener {

	private List<ADTabLabel> listItems = new ArrayList<ADTabLabel>();
	private List<EventListener> listeners = new ArrayList<EventListener>();	
	private IADTab tabbox;
	private int selectedIndex = 0;
	
	public ADButtonTabList() {
		this.setStyle("margin:0;padding:0");
	}
	
	public void refresh() {
		this.getChildren().clear();
		int i = 0;
		for (ADTabLabel tabLabel : listItems) {
			Button button = new Button();
			button.setLabel(tabLabel.label);
			int s = tabbox.getSelectedIndex();
			
			if ( s == i)
				LayoutUtils.addSclass("adwindow-navbtn-sel", button); 
			else {
				if (!tabbox.canNavigateTo(s, i)) {
					button.setDisabled(true);
					LayoutUtils.addSclass("adwindow-navbtn-dis", button);
				} else {
					LayoutUtils.addSclass("adwindow-navbtn-uns", button);
				}
			}
			
			String style = "margin-left:" + (tabLabel.tabLevel*15+5) + "px";
			button.setStyle(style);
			
			button.setParent(this);
			button.setWidth((195 - tabLabel.tabLevel*15)+"px");
			button.addEventListener(Events.ON_CLICK, this);
			i++;
		}
	}

	public int getSelectedIndex() {
		return selectedIndex;
	}

	public void setSelectedIndex(int index) {
		this.selectedIndex = index;		
	}
	
	public void setItems(List<ADTabLabel> listItems) {
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
			if (tabLabel.label.equals(button.getLabel())) {
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
