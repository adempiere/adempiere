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


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import org.adempiere.webui.LayoutUtils;
import org.adempiere.webui.editor.WEditor;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.GridTable;
import org.compiere.util.DisplayType;
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

	private GridTabListItemRenderer renderer;

	private South south;
	
	public GridPanel()
	{
		this(0);
	}
	
	public GridPanel(int windowNo)
	{
		this.windowNo = windowNo;
		listbox = new Listbox();
		south = new South();
		this.appendChild(south);
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
		int rowIndex  = gridTab.isOpen() ? gridTab.getCurrentRow() : -1;
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
			if (listbox.getSelectedIndex() != pgIndex) {
				renderer.stopEditing(false);
				listModel.updateComponent(listbox.getSelectedIndex());
				listModel.updateComponent(pgIndex);
				listbox.setSelectedIndex(pgIndex);				
			}
		} else {
			if (listbox.getSelectedIndex() != rowIndex) {
				renderer.stopEditing(false);
				listModel.updateComponent(listbox.getSelectedIndex());
				listModel.updateComponent(rowIndex);
				listbox.setSelectedIndex(rowIndex);				
			}
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
				int l = DisplayType.isNumeric(gridField[i].getDisplayType()) 
					? 100 : gridField[i].getDisplayLength() * 9;
				if (gridField[i].getHeader().length() * 9 > l)
					l = gridField[i].getHeader().length() * 9;
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
		
		if (pageSize > 0) 
		{
			paging = new Paging();
			paging.setPageSize(pageSize);
			paging.setTotalSize(tableModel.getRowCount());
			paging.setDetailed(true);
			south.appendChild(paging);
			paging.addEventListener(ZulEvents.ON_PAGING, this);
			this.getParent().invalidate();
		}
		else
		{
			south.setVisible(false);
		}
	}
	
	private void updateModel() {
		listModel = new GridTableListModel((GridTable)tableModel, windowNo);		
		listModel.setPageSize(pageSize);
		if (renderer != null)
			renderer.stopEditing(false);
		renderer = new GridTabListItemRenderer(gridTab, windowNo);
		listbox.setItemRenderer(renderer);
		listbox.setModel(listModel);
	}
	
	public void deactivate() {
		ListitemRenderer lr = null;
		listModel = null;
		listbox.setItemRenderer(lr);
		listbox.setModel(listModel);
	}

	public void onEvent(Event event) throws Exception
	{		
		if (event == null)
			return;		
		else if (event.getTarget() == listbox)
		{
			int index = listbox.getSelectedIndex();
			onSelectedRowChange(index);				
        }
		else if (event.getTarget() == paging)
		{
			int pgNo = paging.getActivePage();
			if (pgNo != listModel.getPage())
			{
				listbox.clearSelection();
				listModel.setPage(pgNo);
				listbox.setSelectedIndex(0);
				onSelectedRowChange(0);
			}
		}
	}

	private void onSelectedRowChange(int index) {
		if (updateModelIndex()) {			
			listModel.updateComponent(index);
			listbox.setSelectedIndex(index);
		} else if (!renderer.isInitialize()) {
			listModel.updateComponent(index);
			listbox.setSelectedIndex(index);
		}
	}

	private boolean updateModelIndex() {
		int rowIndex = listbox.getSelectedIndex();
		if (pageSize > 0) {
			int start = listModel.getPage() * listModel.getPageSize();
			rowIndex = start + rowIndex;
		} 
		
		if (gridTab.getCurrentRow() != rowIndex) {
			renderer.stopEditing(true);
			gridTab.navigate(rowIndex);
			return true;
		}
		return false;
	}
	
	public Listbox getListbox() {
		return listbox;
	}
	
	public void dynamicDisplay(int col) {
		if (!gridTab.isOpen())
        {
            return;
        }
        
        //  Selective
        if (col > 0)
        	return;

        boolean noData = gridTab.getRowCount() == 0;
        List<WEditor> list =  renderer.getEditors();
        for (WEditor comp : list)
        {
            GridField mField = comp.getGridField();
            if (mField != null && mField.getIncluded_Tab_ID() <= 0)
            {
                if (noData)
                {
                    comp.setReadWrite(false);
                }
                else
                {
                	comp.dynamicDisplay();
                    boolean rw = mField.isEditable(true);   //  r/w - check Context
                    comp.setReadWrite(rw);
                }
            }
        }   //  all components
	}

	/**
	 * 
	 * @param windowNo
	 */
	public void setWindowNo(int windowNo) {
		this.windowNo = windowNo;
	}
}
