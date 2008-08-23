/******************************************************************************
 * Product: Posterita Ajax UI 												  *
 * Copyright (C) 2007 Posterita Ltd.  All Rights Reserved.                    *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * Posterita Ltd., 3, Draper Avenue, Quatre Bornes, Mauritius                 *
 * or via info@posterita.org or http://www.posterita.org/                     *
 *****************************************************************************/

package org.adempiere.webui.component;


import java.util.HashMap;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import org.adempiere.webui.LayoutUtils;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.GridTable;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zkex.zul.Borderlayout;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.South;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Paging;
import org.zkoss.zul.event.ZulEvents;

/**
 * 
 * @author Low Heng Sin
 *
 */
public class GridPanel extends Borderlayout implements EventListener
{
	private static final int MIN_COLUMN_WIDTH = 100;

	private static final int MAX_COLUMN_WIDTH = 300;

	private static final long serialVersionUID = 1L;
	
	private Listbox listbox = null;
	
	private int pageSize = 1000;
	
	private GridField[] gridField;
	private AbstractTableModel tableModel;
	
	private int numColumns = 5;
	
	private int windowNo;
	
	private GridTab gridTab;
	
	private boolean init;

	private GridTableListModel listModel;

	private Paging paging;
	
	public GridPanel()
	{
		super();
		listbox = new Listbox();
	}
	
	public GridPanel(int windowNo)
	{
		this.windowNo = windowNo;
		listbox = new Listbox();
	}
	
	public void init(GridTab gridTab)
	{
		if (init) return;
				
		this.gridTab = gridTab;
		tableModel = gridTab.getTableModel();
		
		numColumns = tableModel.getColumnCount();
		
		gridField = ((GridTable)tableModel).getFields();
				
		setupColumns();
		render();
		
		updateListIndex();
		
		this.init = true;
	}
	
	public boolean isInit() {
		return init;
	}
	
	public void activate(GridTab gridTab) {
		if (isInit())
			refresh(gridTab);
		else
			init(gridTab);
	}
	
	public void refresh(GridTab gridTab) {
		this.gridTab = gridTab;
		tableModel = gridTab.getTableModel();
		gridField = ((GridTable)tableModel).getFields();
		
		updateModel();
		
		updateListIndex();
	}

	/**
	 * Update listbox index to sync with grid current row pointer changes
	 */
	public void updateListIndex() {
		int rowIndex  = gridTab.getCurrentRow();
		if (pageSize > 0) {			
			if (paging.getTotalSize() != gridTab.getRowCount())
				paging.setTotalSize(gridTab.getRowCount());
			int pgIndex = rowIndex % pageSize;
			int pgNo = (rowIndex - pgIndex) / pageSize;
			if (listModel.getPage() != pgNo) {
				listModel.setPage(pgNo);
			}
			if (paging.getActivePage() != pgNo)
				paging.setActivePage(pgNo);
			if (listbox.getSelectedIndex() != pgIndex)
				listbox.setSelectedIndex(pgIndex);
		} else {
			if (listbox.getSelectedIndex() != rowIndex)
				listbox.setSelectedIndex(rowIndex);
		}
	}

	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}
	
	public void clear()
	{
		this.getChildren().clear();
	}
	
	public void showGrid(boolean bool)
	{
		if (bool)
			this.setVisible(true);
		else
			this.setVisible(false);
	}
	
	private void setupColumns()
	{		
		if (init) return;
		
		ListHead header = new ListHead();
		header.setSizable(true);
		
		Map<Integer, String> colnames = new HashMap<Integer, String>();
		int index = 0;
		for (int i = 0; i < numColumns; i++)
		{
			if (gridField[i].isDisplayed())
			{
				colnames.put(index, gridField[i].getHeader());
				index++;
				ListHeader colHeader = new ListHeader();
				colHeader.setSort("auto");
				colHeader.setLabel(gridField[i].getHeader());
				int l = gridField[i].getDisplayLength() * 10;
				if (l > MAX_COLUMN_WIDTH) 
					l = MAX_COLUMN_WIDTH;
				else if ( l < MIN_COLUMN_WIDTH)
					l = MIN_COLUMN_WIDTH;
				colHeader.setWidth(Integer.toString(l) + "px");
				header.appendChild(colHeader);
			}
		}		
		listbox.appendChild(header);
	}
	
	private void render()
	{
		LayoutUtils.addSclass("adtab-grid-panel", this);
		
		listbox.setVflex(true);
		listbox.addEventListener(Events.ON_SELECT, this);
		
		LayoutUtils.addSclass("adtab-grid", listbox);
		
		updateModel();				
		
		Center center = new Center();
		center.appendChild(listbox);
		this.appendChild(center);
		
		if (pageSize > 0) {
			paging = new Paging();
			paging.setPageSize(pageSize);
			paging.setTotalSize(tableModel.getRowCount());
			paging.setDetailed(true);
			South south = new South();
			south.appendChild(paging);
			this.appendChild(south);
			paging.addEventListener(ZulEvents.ON_PAGING, this);
		}
	}

	private void updateModel() {
		listModel = new GridTableListModel((GridTable)tableModel, windowNo);		
		listModel.setPageSize(pageSize);
		listbox.setItemRenderer(listModel);
		listbox.setModel(listModel);
	}
	
	public void deactivate() {
		ListitemRenderer lr = null;
		listbox.setItemRenderer(lr);
		listbox.setModel(null);
	}

	public void onEvent(Event event) throws Exception
	{		
		if (event == null)
			return;		
		else if (event.getTarget() == listbox)
		{
			updateModelIndex();
        }
		else if (event.getTarget() == paging)
		{
			int pgNo = paging.getActivePage();
			if (pgNo != listModel.getPage())
			{
				listbox.clearSelection();
				listModel.setPage(pgNo);
				listbox.setSelectedIndex(0);
				updateModelIndex();
			}
		}
	}

	private void updateModelIndex() {
		int rowIndex = listbox.getSelectedIndex();
		if (pageSize > 0) {
			int start = listModel.getPage() * listModel.getPageSize();
			rowIndex = start + rowIndex;
		} 
		
		if (gridTab.getCurrentRow() != rowIndex)
			gridTab.navigate(rowIndex);
	}
	
	public Listbox getListbox() {
		return listbox;
	}
}
