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
	
	private int[] maxLength;

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

	protected StringBuffer truncate(String src, int maxLength) {
		int j = maxLength;
		while (j > 0 && Character.isWhitespace(src.charAt(j - 1)))
			--j;
		return new StringBuffer(j + 3)
			.append(src.substring(0, j)).append("...");
	}
	
	public void render(Listitem item, Object data) throws Exception {
		if (data instanceof Object[]) {
			renderArray(item, (Object[])data);
		} else if (data instanceof Collection) {
			renderCollection(item, (Collection)data);
		} else {
			String value = data != null ? data.toString() : "";
			renderCell(0, item, value);
		}		
	}
	
	protected void renderCell(int col, Listitem item, String value) {
		String tooltip = null;
		if (maxLength != null && maxLength.length > col && maxLength[col] > 0 && value.length() > maxLength[col]) {
			tooltip = value;
			value = truncate(value, maxLength[col]).toString();
		}
		ListCell listCell = new ListCell(value);
		listCell.setParent(item);			
		if (tooltip != null)
			listCell.setTooltiptext(tooltip);
	}

	private void renderCollection(Listitem item, Collection data) {
		int i = 0;
		for (Object col : data) {
			String value = (col != null ? col.toString() : "");
			renderCell(i, item, value);
			i++;
		}
	}

	private void renderArray(Listitem item, Object[] data) {
		int i = 0;
		for (Object col : data) {			
			String value = (col != null ? col.toString() : "");
			renderCell(i, item, value);
			i++;
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
	
	public void setMaxLength(int[] maxLength) {
		this.maxLength = maxLength;
	}
}
