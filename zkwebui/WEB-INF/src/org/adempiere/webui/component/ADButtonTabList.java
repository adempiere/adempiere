package org.adempiere.webui.component;

import java.util.ArrayList;
import java.util.List;

import org.adempiere.webui.component.ADTabListModel.ADTabLabel;
import org.zkoss.zhtml.Button;
import org.zkoss.zhtml.Text;
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
				} else {
					button.setDynamicProperty("disabled", null);
					button.setSclass("adwindow-navbtn-uns");
				}
			}
			
			String style = "margin-left:" + (tabLabel.tabLevel*15+5) + "px";
			String width = (195 - tabLabel.tabLevel*15)+"px";
			style = style + "; width:" + width; 
			button.setStyle(style);
			
			button.setParent(this);
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
