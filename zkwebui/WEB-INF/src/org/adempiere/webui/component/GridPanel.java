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
import org.adempiere.webui.util.SortComparator;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.GridTable;
import org.compiere.model.MSysConfig;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zkex.zul.Borderlayout;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.South;
import org.zkoss.zul.Column;
import org.zkoss.zul.Paging;
import org.zkoss.zul.event.ZulEvents;

/**
 * Grid view implemented using the Grid component.
 * @author Low Heng Sin
 *
 */
public class GridPanel extends Borderlayout implements EventListener
{
	private static final int MIN_COLUMN_WIDTH = 100;

	private static final int MAX_COLUMN_WIDTH = 300;

	private static final long serialVersionUID = 1L;
	
	private Grid listbox = null;
	
	private int pageSize = 100;
	
	private GridField[] gridField;
	private AbstractTableModel tableModel;
	
	private int numColumns = 5;
	
	private int windowNo;
	
	private GridTab gridTab;
	
	private boolean init;

	private GridTableListModel listModel;

	private Paging paging;

	private GridTabRowRenderer renderer;

	private South south;
	
	public static final String PAGE_SIZE_KEY = "ZK_PAGING_SIZE";
	
	//copy from org.zkoss.zul.Grid
	private static final String ATTR_ON_INIT_RENDER_POSTED =
		"org.zkoss.zul.Grid.onInitLaterPosted";
	
	public GridPanel()
	{
		this(0);
	}
	
	/**
	 * @param windowNo
	 */
	public GridPanel(int windowNo)
	{
		this.windowNo = windowNo;
		listbox = new Grid();
		south = new South();
		this.appendChild(south);
		
		//default paging size
		pageSize = MSysConfig.getIntValue(PAGE_SIZE_KEY, 100);
	}

	/**
	 * 
	 * @param gridTab
	 */
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
	
	/**
	 * 
	 * @return boolean
	 */
	public boolean isInit() {
		return init;
	}
	
	/**
	 * call when tab is activated
	 * @param gridTab
	 */
	public void activate(GridTab gridTab) {		
		if (!isInit()) {
			init(gridTab);
		}
	}
	
	/**
	 * refresh after switching from form view
	 * @param gridTab
	 */
	public void refresh(GridTab gridTab) {
		if (this.gridTab != gridTab)
		{
			init = false;
			init(gridTab);
		}
		else
		{
			if (renderer != null)
				renderer.stopEditing(false);
			
			listbox.setModel(listModel);
			updateListIndex();
		}
	}

	/**
	 * Update current row from model
	 */
	public void updateListIndex() {
		int rowIndex  = gridTab.isOpen() ? gridTab.getCurrentRow() : -1;
		if (pageSize > 0) {			
			if (paging.getTotalSize() != gridTab.getRowCount())
				paging.setTotalSize(gridTab.getRowCount());
			int pgIndex = rowIndex >= 0 ? rowIndex % pageSize : 0;
			int pgNo = rowIndex >= 0 ? (rowIndex - pgIndex) / pageSize : 0;
			
			if (listModel.getPage() != pgNo) {
				listModel.setPage(pgNo);
			}
			if (paging.getActivePage() != pgNo) {
				paging.setActivePage(pgNo);
			}			
			renderer.stopEditing(false);
			if (rowIndex >= 0 && pgIndex >= 0) {
				listModel.updateComponent(pgIndex);
				//don't have to call renderRow if render event have been posted
				if (listbox.getAttribute(ATTR_ON_INIT_RENDER_POSTED) == null) {
					//this is needed to make focus and auto scroll work
					org.zkoss.zul.Row row = (org.zkoss.zul.Row)listbox.getRows().getChildren().get(pgIndex);
					listbox.renderRow(row);
				}
				Events.echoEvent("onPostRenderSelectedRow", this, null);
			}
		} else {
			renderer.stopEditing(false);
			if (rowIndex >= 0) {
				listModel.updateComponent(rowIndex);
				//don't have to call renderRow if render event have been posted
				if (listbox.getAttribute(ATTR_ON_INIT_RENDER_POSTED) == null) {
					//this is needed to make focus and auto scroll work
					org.zkoss.zul.Row row = (org.zkoss.zul.Row)listbox.getRows().getChildren().get(rowIndex);
					listbox.renderRow(row);
				}
				Events.echoEvent("onPostRenderSelectedRow", this, null);
			}
		}
	}
	
	/**
	 * Don't call this directly, use internally to send post render event
	 */
	public void onPostRenderSelectedRow() {
		renderer.setFocusToField();
	}


	
	/**
	 * set paging size
	 * @param pageSize
	 */
	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}
	
	public void clear()
	{
		this.getChildren().clear();
	}
	
	/**
	 * toggle visibility
	 * @param bool
	 */
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
		
		Columns columns = new Columns(); 
		listbox.appendChild(columns);
		columns.setSizable(true);
		columns.setMenupopup("auto");
		columns.setColumnsgroup(false);
		
		Map<Integer, String> colnames = new HashMap<Integer, String>();
		int index = 0;
		for (int i = 0; i < numColumns; i++)
		{
			if (gridField[i].isDisplayed())
			{
				colnames.put(index, gridField[i].getHeader());
				index++;
				org.zkoss.zul.Column column = new Column();
				column.setSortAscending(new SortComparator(i, true, Env.getLanguage(Env.getCtx())));
				column.setSortDescending(new SortComparator(i, false, Env.getLanguage(Env.getCtx())));
				column.setLabel(gridField[i].getHeader());
				int l = DisplayType.isNumeric(gridField[i].getDisplayType()) 
					? 120 : gridField[i].getDisplayLength() * 9;
				if (gridField[i].getHeader().length() * 9 > l)
					l = gridField[i].getHeader().length() * 9;
				if (l > MAX_COLUMN_WIDTH) 
					l = MAX_COLUMN_WIDTH;
				else if ( l < MIN_COLUMN_WIDTH)
					l = MIN_COLUMN_WIDTH;
				column.setWidth(Integer.toString(l) + "px");
				columns.appendChild(column);
			}
		}		
	}
	
	private void render()
	{
		LayoutUtils.addSclass("adtab-grid-panel", this);
		
		listbox.setVflex(true);
		listbox.setFixedLayout(true);
		listbox.addEventListener(Events.ON_CLICK, this);
		
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
			renderer.setPaging(paging);
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
		renderer = new GridTabRowRenderer(gridTab, windowNo);
		renderer.setGridPanel(this);
				
		listbox.setRowRenderer(renderer);
		listbox.setModel(listModel);		
	}
	
	/**
	 * deactivate panel
	 */
	public void deactivate() {
		if (renderer != null)
			renderer.stopEditing(false);
	}

	public void onEvent(Event event) throws Exception
	{		
		if (event == null)
			return;		
		else if (event.getTarget() == listbox && Events.ON_CLICK.equals(event.getName()))
		{
			Object data = event.getData();
			if (data != null && data instanceof org.zkoss.zul.Row)
			{
				int index = listbox.getRows().getChildren().indexOf(data);
				if (index >= 0 ) {
					onSelectedRowChange(index);
				}
			}
        }
		else if (event.getTarget() == paging)
		{
			int pgNo = paging.getActivePage();
			if (pgNo != listModel.getPage())
			{
				listModel.setPage(pgNo);
				onSelectedRowChange(0);
			}
		}
	}

	private void onSelectedRowChange(int index) {
		if (updateModelIndex(index)) {		
			listModel.updateComponent(index);
		} else if (!renderer.isInitialize()) {
			listModel.updateComponent(index);
		}
	}

	private boolean updateModelIndex(int rowIndex) {
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
	
	/**
	 * @return Grid
	 */
	public Grid getListbox() {
		return listbox;
	}

	/**
	 * Validate display properties of fields of current row
	 * @param col
	 */
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

	@Override
	public void focus() {
		if (renderer != null)
			renderer.setFocusToField();
	}
}
