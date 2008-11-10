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

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Comparator;

import org.compiere.model.DataStatusEvent;
import org.compiere.model.DataStatusListener;
import org.compiere.model.GridField;
import org.compiere.model.GridTable;
import org.compiere.model.MAccountLookup;
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
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelExt;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemComparator;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.ListitemRendererExt;
import org.zkoss.zul.event.ListDataEvent;

/**
 * 
 * @author Low Heng Sin
 *
 */
public class GridTableListModel extends AbstractListModel implements ListitemRenderer, 
ListitemRendererExt, DataStatusListener, ListModelExt {

	private GridTable tableModel;
	private GridField[] gridField;
	private int windowNo;
	
	private int pageSize = -1;
	private int pageNo = 0;

	/**
	 * 
	 * @param tableModel
	 * @param windowNo
	 */
	public GridTableListModel(GridTable tableModel, int windowNo) {
		this.tableModel = tableModel;
		this.windowNo = windowNo;
		gridField = tableModel.getFields();
		tableModel.addDataStatusListener(this);
	}

	/**
	 * @param rowIndex
	 * @see ListModel#getElementAt(int)
	 */
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
	
	/**
	 * Set number of rows per page
	 * @param pgSize
	 */
	public void setPageSize(int pgSize) {
		pageSize = pgSize;
	}

	/**
	 * Get number of rows per page
	 * @return pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}
	
	/**
	 * Get total number of rows
	 * @return int
	 * @see ListModel#getSize()
	 */
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

	/**
	 * @param listitem
	 * @param data
	 * @see ListitemRenderer#render(Listitem, Object)
	 */
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
    			NamePair namepair = null;
    			if (gridField[columnIndex].getDisplayType() == DisplayType.Account)
    			{
    				MAccountLookup lookup = new MAccountLookup(Env.getCtx(), windowNo);
    				namepair = lookup.get(obj);
    			}
    			else
    			{
	    			MLookup lookup = MLookupFactory.get(
	    						Env.getCtx(), windowNo, 0, gridField[columnIndex].getAD_Column_ID(), 
	    						gridField[columnIndex].getDisplayType());
	    					
	    			namepair = lookup.get(obj);
    			}
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

	/**
	 * @see ListitemRendererExt#getControls()
	 */
	public int getControls() {
		return DETACH_ON_RENDER;
	}

	/**
	 * @param item
	 * @see ListitemRendererExt#newListcell(Listitem)
	 */
	public Listcell newListcell(Listitem item) {
		ListCell listCell = new ListCell();
		listCell.applyProperties();
		listCell.setParent(item);
		return listCell;
	}

	/**
	 * @param listbox
	 * @see ListitemRendererExt#newListitem(Listbox)
	 */
	public Listitem newListitem(Listbox listbox) {
		ListItem item = new ListItem();
		item.applyProperties();
		return item;
	}

	/**
	 * @param e
	 * @see DataStatusListener#dataStatusChanged(DataStatusEvent)
	 */
	public void dataStatusChanged(DataStatusEvent e) {
		if (Executions.getCurrent() != null) {
			fireEvent(ListDataEvent.CONTENTS_CHANGED, -1, -1);
		}
		
	}

	/**
	 * @param cmpr
	 * @param ascending
	 * @see ListModelExt#sort(Comparator, boolean) 
	 */
	public void sort(Comparator cmpr, boolean ascending) {
		//use default zk comparator
		ListitemComparator lic = (ListitemComparator) cmpr;
		tableModel.sort(lic.getListheader().getColumnIndex(), ascending);
		fireEvent(ListDataEvent.CONTENTS_CHANGED, -1, -1);
	}

}
