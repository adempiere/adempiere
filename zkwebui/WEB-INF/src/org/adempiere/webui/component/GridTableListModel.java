package org.adempiere.webui.component;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.compiere.model.DataStatusEvent;
import org.compiere.model.DataStatusListener;
import org.compiere.model.GridField;
import org.compiere.model.GridTable;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.NamePair;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.AbstractListModel;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.ListitemRendererExt;
import org.zkoss.zul.event.ListDataEvent;

public class GridTableListModel extends AbstractListModel implements ListitemRenderer, ListitemRendererExt, DataStatusListener {

	private GridTable tableModel;
	private GridField[] gridField;
	private int windowNo;
	
	private int pageSize = -1;
	private int pageNo = 0;

	public GridTableListModel(GridTable tableModel, int windowNo) {
		this.tableModel = tableModel;
		this.windowNo = windowNo;
		gridField = tableModel.getFields();
		tableModel.addDataStatusListener(this);
	}

	public Object getElementAt(int rowIndex) {
		int columnCount = tableModel.getColumnCount();
		Object[] values = new Object[columnCount];
		if (pageSize > 0) {
			rowIndex = (pageNo * pageSize) + rowIndex;
		}
		if (rowIndex < tableModel.getRowCount()) {
			for (int i = 0; i < columnCount; i++) {
				values[i] = tableModel.getValueAt(rowIndex, i);
			}
		}
		
		return values;
	}
	
	/**
	 * set current page no ( starting from 0 )
	 * @param pg
	 */
	public void setPage(int pg) {
		if (pageNo != pg) {
			if (pg > 0) {
				int start = pg * pageSize;
				if (start >= tableModel.getRowCount()) {
					return;
				}
			}
			pageNo = pg;
			fireEvent(ListDataEvent.CONTENTS_CHANGED, -1, -1);
		}
	}
	
	/**
	 * @return current page no ( starting from 0 )
	 */
	public int getPage() {
		return pageNo;
	}
	
	public void setPageSize(int pgSize) {
		pageSize = pgSize;
	}

	public int getPageSize() {
		return pageSize;
	}
	
	public int getSize() {
		int total = tableModel.getRowCount(); 
		if (pageSize < 0)
			return total;
		else if ((total - ( pageNo * pageSize)) < 0) {
			pageNo = 0;
			return pageSize > total ? total : pageSize;
		} else {
			int end = (pageNo + 1) * pageSize;
			if (end > total)
				return total - ( pageNo * pageSize);
			else
				return pageSize;
		}
	}

	public void render(Listitem listitem, Object data) throws Exception {
		Object[] values = (Object[])data;
		int columnCount = tableModel.getColumnCount();
		for (int i = 0; i < columnCount; i++) {
			if (!gridField[i].isDisplayed()) {
				continue;
			}
			Listcell cell = null;
			if (gridField[i].getDisplayType() == DisplayType.YesNo) {
				cell = new Listcell("", null);
				cell.setParent(listitem);
				cell.setStyle("text-align:center");
				Checkbox checkBox = new Checkbox();
				if (values[i] != null && "true".equalsIgnoreCase(values[i].toString()))
					checkBox.setChecked(true);
				else
					checkBox.setChecked(false);
				checkBox.setDisabled(true);
				checkBox.setParent(cell);
			} else {
				cell = new Listcell(getCell(values[i], i), null);
				cell.setParent(listitem);
			}
			CellListener listener = new CellListener((Listbox) listitem.getParent());
			cell.addEventListener(Events.ON_DOUBLE_CLICK, listener);
		}
	}
	
	class CellListener implements EventListener {

		private Listbox _listbox;
		
		public CellListener(Listbox listbox) {
			_listbox = listbox;
		}
		
		public void onEvent(Event event) throws Exception {
			if (Events.ON_DOUBLE_CLICK.equals(event.getName())) {
				Event evt = new Event(Events.ON_DOUBLE_CLICK, _listbox);
				Events.sendEvent(_listbox, evt);
			}
		}
		
	}
	
	private String getCell(Object obj, int columnIndex)
	{
		if (obj == null)
			return "";
		
		if (tableModel.getColumnClass(columnIndex).equals(Integer.class))
    	{
    		if (gridField[columnIndex].isLookup())
    		{
    			MLookup lookup = MLookupFactory.get(
    						Env.getCtx(), windowNo, 0, gridField[columnIndex].getAD_Column_ID(), 
    						gridField[columnIndex].getDisplayType());
    					
    			NamePair namepair = lookup.get(obj);
    				
    			if (namepair != null)
    				return namepair.getName();
    			else
    				return "";
    		}
    		else
    			return obj.toString();
    	}
    	else if (tableModel.getColumnClass(columnIndex).equals(Timestamp.class))
    	{
    		SimpleDateFormat dateFormat = DisplayType.getDateFormat(DisplayType.Date);
    		return dateFormat.format((Timestamp)obj);
    	}
    	else
    		return obj.toString();
	}

	public int getControls() {
		return DETACH_ON_RENDER;
	}

	public Listcell newListcell(Listitem item) {
		ListCell listCell = new ListCell();
		listCell.applyProperties();
		listCell.setParent(item);
		return listCell;
	}

	public Listitem newListitem(Listbox listbox) {
		ListItem item = new ListItem();
		item.applyProperties();
		return item;
	}

	public void dataStatusChanged(DataStatusEvent e) {
		if (Executions.getCurrent() != null) {
			fireEvent(ListDataEvent.CONTENTS_CHANGED, -1, -1);
		}
		
	}

}
