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

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.swing.table.AbstractTableModel;

import org.adempiere.webui.panel.ADWindowPanel;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.GridTable;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.NamePair;
import org.zkforge.yuiext.grid.Column;
import org.zkforge.yuiext.grid.Columns;
import org.zkforge.yuiext.grid.Grid;
import org.zkforge.yuiext.grid.Label;
import org.zkforge.yuiext.grid.Row;
import org.zkforge.yuiext.grid.Rows;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Separator;

public class GridPanel extends Panel implements EventListener
{
	private static final long serialVersionUID = 1L;
	
	private Grid grid = new Grid();
	private Rows rows = new Rows();
	private Row row = new Row();
	private Columns columns = new Columns();
	private Column column = new Column();
	private Label label = new Label();
	
	private int pageSize = 10;
	private long numPages;
	
	private GridField[] gridField;
	private AbstractTableModel tableModel;
	
	private int numColumns = 5;
	private int numRows;
	
	private Button[] buttons;	
	private Hbox boxButtons = new Hbox();
	
	private ADWindowPanel windowPanel;
	private int windowNo;
	
	private GridTab gridTab;
	
	private int pageId = 0;
	
	public GridPanel()
	{
		super();
	}
	
	public GridPanel(int windowNo, ADWindowPanel windowPanel)
	{
		this.windowNo = windowNo;
		this.windowPanel = windowPanel;
	}
	
	public void init(GridTab gridTab)
	{
		this.gridTab = gridTab;
		tableModel = gridTab.getTableModel();
		
		numColumns = tableModel.getColumnCount();
		numRows = tableModel.getRowCount();
		
		gridField = ((GridTable)tableModel).getFields();
				
		loadButtons();
		loadGridHeader();
		loadRecords(0, pageSize);
		loadDisplay();
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
	
	private void loadGridHeader()
	{
		if (grid.getColumns() != null)
			return;
		
		columns = new Columns();
		
		for (int i = 0; i < numColumns; i++)
		{
			if (gridField[i].isDisplayed())
			{
				column = new Column(gridField[i].getHeader());
				columns.appendChild(column);
			}
		}
		
		grid.appendChild(columns);
	}
	
	private void loadButtons()
	{
		double pages = (double)numRows / (double)pageSize;
		pages = Math.ceil(pages);
		numPages = Math.round(pages);
		
		if (numPages == 1)
			return;
		
		buttons = new Button[(int)numPages];
		
		if (boxButtons.getChildren() != null)
			boxButtons.getChildren().clear();
		
		for (int i = 0; i < buttons.length; i++)
		{
			Integer count = i;
			buttons[i] = new Button();
			buttons[i].setId(count.toString());
			buttons[i].addEventListener(Events.ON_CLICK, this);
			buttons[i].setLabel(count.toString());
			boxButtons.appendChild(buttons[i]);
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
    			return "Lookup";
    	}
    	else if (tableModel.getColumnClass(columnIndex).equals(Timestamp.class))
    	{
    		SimpleDateFormat dateFormat = DisplayType.getDateFormat(DisplayType.Date);
    		return dateFormat.format((Timestamp)obj);
    	}
    	else
    		return obj.toString();
	}
	
	private void loadRecords(int start, int end)
	{
		if (grid.getRows() != null)
			grid.getRows().getChildren().clear();

		if (rows.getChildren() != null)
			rows.getChildren().clear();
		
		if (end > numRows)
			end = numRows;
		
		for (int i = start; i < end; i++)
		{
			row = new Row();
			
			for (int j = 0; j < numColumns; j++)
			{
				if (!gridField[j].isDisplayed())
    				break;
				
				label = new Label(getCell(tableModel.getValueAt(i, j), j));
				row.appendChild(label);
			}
		
			rows.appendChild(row);
		}
		
		if (grid.getRows() == null)
			grid.appendChild(rows);
	}
	
	private void loadDisplay()
	{
		this.setWidth("800px");
		//this.setHeight("1000px");
		
		grid.setWidth("100%");
		grid.setHeight("800px");
		grid.addEventListener(Events.ON_SELECT, this);
		
		boxButtons.setWidth("60%");
		
		this.appendChild(grid);
		this.appendChild(new Separator());
		this.appendChild(boxButtons);
	}

	public void onEvent(Event event) throws Exception
	{
		if (event == null)
			return;
		
		if (event.getTarget() instanceof Button)
		{
			Button btn = (Button)event.getTarget();
		
			pageId = new Integer(btn.getId());
			
			int start = pageId * pageSize;
			
			int end = start + pageSize;
			
			loadRecords(start, end);
		}
		else if (event.getTarget() == grid)
		{
			int keyRecordId = grid.getSelectedIndex() + (pageId * pageSize);
	        	
			gridTab.navigate(keyRecordId);
	        	
			this.setVisible(false);
			windowPanel.showTabbox(true);
        }
	}
}
