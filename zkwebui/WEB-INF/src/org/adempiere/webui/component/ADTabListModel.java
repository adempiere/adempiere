package org.adempiere.webui.component;

import java.util.List;

import org.zkoss.zul.AbstractListModel;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.ListitemRendererExt;

public class ADTabListModel extends AbstractListModel implements ListitemRenderer, ListitemRendererExt {
	
	List<ADTabLabel> listItems = null;
	private IADTab tabbox;
	
	public ADTabListModel(List<ADTabLabel> listItems, IADTab tabbox) {
		this.listItems = listItems;
		this.tabbox = tabbox;
	}

	public Object getElementAt(int index) {
		ADTabLabel item = index < listItems.size() ? listItems.get(index) : null;
		return item;
	}

	public int getSize() {
		return listItems.size();
	}
	
	public static class ADTabLabel {
		public String label;
		public int tabLevel;
		
		public ADTabLabel(String label, int tabLevel) {
			this.label = label;
			this.tabLevel = tabLevel;
		}
	}

	public void render(Listitem item, Object data) throws Exception {
		ADTabLabel tabLabel = (ADTabLabel)data;
		Listcell cell = new Listcell(tabLabel.label, null);
		if (tabLabel.tabLevel > 0) {
			cell.setStyle("padding-left:" + (tabLabel.tabLevel*15+5) + "px");
		}
		cell.setParent(item);
		int i = listItems.indexOf(tabLabel);
		int s = tabbox.getSelectedIndex();
		if (!tabbox.canNavigateTo(s, i))
			cell.setVisible(false);
	}
	
	public int getControls() {
		return DETACH_ON_RENDER;
	}

	public Listcell newListcell(Listitem item) {
		return null;
	}

	public Listitem newListitem(Listbox listbox) {
		ListItem item = new ListItem();
		item.applyProperties();
		return item;
	}

}
