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
import org.zkoss.zul.Div;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.ListitemRenderer;

public class GridPanel extends Div implements EventListener
{
	private static final long serialVersionUID = 1L;
	
	private Listbox listbox = null;
	
	private int pageSize = 10;
	private long numPages;
	
	private GridField[] gridField;
	private AbstractTableModel tableModel;
	
	private int numColumns = 5;
	private int numRows;
	
	private Button[] buttons;	
	private Hbox boxButtons = new Hbox();
	
	private int windowNo;
	
	private GridTab gridTab;
	
	private int pageId = 0;

	private ListHead listHead;

	private boolean init;
	
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
		numRows = tableModel.getRowCount();
		
		gridField = ((GridTable)tableModel).getFields();
				
		setupColumns();
		render();
		
		listbox.setSelectedIndex(gridTab.getCurrentRow());
		
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
		numRows = tableModel.getRowCount();
		gridField = ((GridTable)tableModel).getFields();
		
		updateModel();
		
		listbox.setSelectedIndex(gridTab.getCurrentRow());
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
		
		Map colnames = new HashMap<Integer, String>();
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
				if (l > 300) 
					l = 300;
				else if ( l < 100)
					l = 100;
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
		 
		this.appendChild(listbox);
	}

	private void updateModel() {
		GridTableListModel listModel = new GridTableListModel((GridTable)tableModel, windowNo);		
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
			gridTab.navigate(listbox.getSelectedIndex());
        }
	}
	
	public Listbox getListbox() {
		return listbox;
	}
}
