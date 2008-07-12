package org.adempiere.webui.component;

import java.util.Collection;
import java.util.List;

import org.zkoss.zul.AbstractListModel;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.ListitemRendererExt;

public class SimpleListModel extends AbstractListModel implements ListitemRenderer, ListitemRendererExt {

	private List list;

	public SimpleListModel(List list) {
		this.list = list;
	}
	
	public Object getElementAt(int index) {
		if (index >= 0 && index < list.size())
			return list.get(index);
		else
			return null;
	}

	public int getSize() {
		return list.size();
	}

	public void render(Listitem item, Object data) throws Exception {
		if (data instanceof Object[]) {
			renderArray(item, (Object[])data);
		} else if (data instanceof Collection) {
			renderCollection(item, (Collection)data);
		} else {
			ListCell listCell = new ListCell(data != null ? data.toString() : "");
			listCell.setParent(item);
		}		
	}

	private void renderCollection(Listitem item, Collection data) {
		for (Object col : data) {
			ListCell listCell = new ListCell(col != null ? col.toString() : "");
			listCell.setParent(item);
		}
	}

	private void renderArray(Listitem item, Object[] data) {
		for (Object col : data) {
			ListCell listCell = new ListCell(col != null ? col.toString() : "");
			listCell.setParent(item);
		}
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
