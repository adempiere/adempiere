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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import org.adempiere.webui.LayoutUtils;
import org.adempiere.webui.editor.WEditor;
import org.adempiere.webui.panel.ADTabPanel;
import org.adempiere.webui.panel.AbstractADWindowPanel;
import org.adempiere.webui.panel.IADTabPanel;
import org.adempiere.webui.util.SortComparator;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.GridTable;
import org.compiere.model.MSysConfig;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkforge.keylistener.Keylistener;
import org.zkoss.zk.au.out.AuFocus;
import org.zkoss.zk.au.out.AuScript;
import org.zkoss.zk.ui.AbstractComponent;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.KeyEvent;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkex.zul.Borderlayout;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.South;
import org.zkoss.zul.Column;
import org.zkoss.zul.Div;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Row;
import org.zkoss.zul.event.ZulEvents;

/**
 * Grid view implemented using the Grid component.
 * @author Low Heng Sin
 * @author e-Evolution , victor.perez@e-evolution.com
 *    <li>Implement embedded or horizontal tab panel https://adempiere.atlassian.net/browse/ADEMPIERE-319
 *    <li>New ADempiere 3.8.0 ZK Theme Light  https://adempiere.atlassian.net/browse/ADEMPIERE-320 
 *
 */
public class GridPanel extends Borderlayout implements EventListener
{
	/**
	 * generated serial version ID
	 */
	private static final long serialVersionUID = -7151423393713654553L;

	private static final int MIN_COLUMN_WIDTH = 100;

	private static final int MAX_COLUMN_WIDTH = 300;

	private static final int MIN_COMBOBOX_WIDTH = 160;

	private static final int MIN_NUMERIC_COL_WIDTH = 130;

	private static final int KEYBOARD_KEY_S = 83;
	private static final int KEYBOARD_KEY_RETURN = 13;
	private static final int MOVE_SIZE = 7;

	public static final String		CNTRL_KEYS				= "#f5#del^d^s#enter$#enter";
	private static final String		KEYS_MOVE			= "#pgup#pgdn#end#home#up#down#left#right";
	
	private int lastKeyEvent = 0;	

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

	private boolean modeless;

	private String columnOnClick;

	private AbstractADWindowPanel windowPanel;

	public static final String PAGE_SIZE_KEY = "ZK_PAGING_SIZE";

	public static final String MODE_LESS_KEY = "ZK_GRID_EDIT_MODELESS";
	
	private IADTabPanel tabPanel;

	private Keylistener	keyListener;
	
	private int currentCol = 0;
	
	private Center center;
	
	private boolean isNewRecord;
	
	public void setADTabPanel(IADTabPanel panel)
	{
		tabPanel = panel;
	}
	
	public IADTabPanel getADTabPanel()
	{
		return tabPanel;
	}

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
		
		listbox.addEventListener(Events.ON_FOCUS, this);
		
		
		listbox.setOddRowSclass(null);
		south = new South();
		this.appendChild(south);

		center = new Center();
		center.appendChild(listbox);
		this.appendChild(center);
		
		//default paging size
		pageSize = MSysConfig.getIntValue(PAGE_SIZE_KEY, 100);

		//default false for better performance
		modeless = MSysConfig.getBooleanValue(MODE_LESS_KEY, false);
	}

	/**
	 *
	 * @param gridTab
	 */
	public void init(GridTab gridTab)
	{
		if (init && !gridTab.isQuickEntry()) return; //init && !gridTab.isQuickEntry()

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
		if (this.gridTab != gridTab || !isInit())
		{		 
			init = false;
			init(gridTab);
		}
		else
		{
			listbox.setModel(listModel);
			updateListIndex();
		}
	}

	/**
	 * Update current row from model
	 */
	public void updateListIndex() {
		if (gridTab == null || !gridTab.isOpen() || paging == null ||gridTab.getRowCount() < 0) return;

		int rowIndex  = gridTab.getCurrentRow();
		
		if (pageSize > 0) {
			if (paging.getTotalSize() != gridTab.getRowCount())
				paging.setTotalSize(gridTab.getRowCount());
			int pgIndex = rowIndex >= 0 ? rowIndex % pageSize : 0;
			int pgNo = rowIndex >= 0 ? (rowIndex - pgIndex) / pageSize : 0;
			if (listModel.getPage() != pgNo) {
				listModel.setPage(pgNo);
				if (renderer.isEditing()) {
					renderer.stopColEditing(false);
				}
			} else if (rowIndex == renderer.getCurrentRowIndex()){
				if (modeless && !renderer.isEditing())
					Events.echoEvent("onPostSelectedRowChanged", this, null);
				return;
			} else {
				if (renderer.isEditing()) {
					renderer.stopColEditing(false);
					if (((renderer.getCurrentRowIndex() - pgIndex) / pageSize) == pgNo) {
						listModel.updateComponent(renderer.getCurrentRowIndex() % pageSize);
					}
				}
			}
			if (paging.getActivePage() != pgNo) {
				paging.setActivePage(pgNo);
			}
			if (rowIndex >= 0 && pgIndex >= 0) {
				Events.echoEvent("onPostSelectedRowChanged", this, null);
			}
		} else {
			if (rowIndex >= 0) {
				Events.echoEvent("onPostSelectedRowChanged", this, null);
			}
		}
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
		
		if (init && !gridTab.isQuickEntry()) return; //init && !gridTab.isQuickEntry()

		if(listbox.getColumns() != null)
			listbox.getChildren().clear();
		
		Columns columns = new Columns();
		listbox.appendChild(columns);
		columns.setSizable(true);
		columns.setMenupopup("auto");
		columns.setColumnsgroup(false);
		
		Map<Integer, String> colnames = new HashMap<Integer, String>();
		int index = 0;
		for (int i = 0; i < numColumns; i++)
		{
			if((gridTab.isQuickEntry() 
					&& !gridField[i].isQuickEntry()))
				continue;
			
			if (gridField[i].isDisplayed())
			{
				colnames.put(index, gridField[i].getHeader());
				index++;
				org.zkoss.zul.Column column = new Column();
				column.setSortAscending(new SortComparator(i, true, Env.getLanguage(Env.getCtx())));
				column.setSortDescending(new SortComparator(i, false, Env.getLanguage(Env.getCtx())));
				column.setLabel(gridField[i].getHeader());
				
				int displayLength = gridField[i].getPreferredWidthInListView() > 0 ? gridField[i].getPreferredWidthInListView() : gridField[i].getDisplayLength() * 9 ;
				int l =displayLength ;
				
				if(DisplayType.isNumeric(gridField[i].getDisplayType()))
					l = 165;
				else if(DisplayType.isDate(gridField[i].getDisplayType()))
					l = 135;
				
				if (gridField[i].getHeader().length() * 9 > l)
					l = gridField[i].getHeader().length() * 9;
				if (l > MAX_COLUMN_WIDTH)
					l = MAX_COLUMN_WIDTH;
				else if ( l < MIN_COLUMN_WIDTH && gridField[i].getPreferredWidthInListView() <= 0)
					l = MIN_COLUMN_WIDTH;
				if (gridField[i].getDisplayType() == DisplayType.Table || gridField[i].getDisplayType() == DisplayType.TableDir)
				{
					if (l < MIN_COMBOBOX_WIDTH)
						l = MIN_COMBOBOX_WIDTH;
				}
				else if (DisplayType.isNumeric(gridField[i].getDisplayType()))
				{
					if (l < MIN_NUMERIC_COL_WIDTH)
						l = MIN_NUMERIC_COL_WIDTH;
				}
				column.setWidth(Integer.toString(l) + "px");
				
				// FR 3051618 - Hide in list view
				if (!gridField[i].isDisplayedGrid()) {
					column.setVisible(false);
				}
				
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
		listbox.addEventListener(Events.ON_DOUBLE_CLICK, this);
		listbox.addEventListener(Events.ON_CANCEL, this);
		
		updateModel();
		
		if (pageSize > 0)
		{
			paging = new Paging();
			paging.setPageSize(pageSize);
			paging.setTotalSize(tableModel.getRowCount());
			paging.setDetailed(true);
			if(south.getChildren().isEmpty())
				south.appendChild(paging);
			paging.addEventListener(ZulEvents.ON_PAGING, this);
			renderer.setPaging(paging);
		}
		else
		{
			south.setVisible(false);
		}
		if(keyListener == null) { 
			keyListener = new Keylistener();
			if (windowPanel != null)
				windowPanel.getStatusBar().appendChild(keyListener);
		}
		if(renderer.isEditing()) 
			keyListener.setCtrlKeys(CNTRL_KEYS);
		else 
			keyListener.setCtrlKeys(CNTRL_KEYS+KEYS_MOVE);
		
		keyListener.addEventListener(Events.ON_CTRL_KEY, this);

	}

	private void updateModel() {
		listModel = new GridTableListModel((GridTable)tableModel, windowNo);
		listModel.setPageSize(pageSize);
		if (renderer != null && renderer.isEditing())
			renderer.stopColEditing(false);
		renderer = new GridTabRowRenderer(gridTab, windowNo);
		renderer.setGridPanel(this);
		renderer.setADWindowPanel(windowPanel);
		if (listbox.getRows() == null) 
			listbox.appendChild(new Rows());
		listbox.setRowRenderer(renderer);
		listbox.setModel(listModel);
		
		if(gridTab.isQuickEntry() && gridTab.getRowCount() == 0)
			onNew();

	}

	/**
	 * deactivate panel
	 */
	public void deactivate() {
		if (renderer != null && renderer.isEditing())
			renderer.stopColEditing(true);
	}

	public void onEvent(Event event) throws Exception
	{
		if (event == null || !((ADTabPanel)tabPanel).isGridView()) {
			addKeyListener();
			return;
		}
		else if (Events.ON_CANCEL.equals(event.getName())) {
			if (renderer.isEditing()) {
				renderer.stopColEditing(false);
				onIgnore();
				addKeyListener();

			}
		}
		else if (Events.ON_DOUBLE_CLICK.equals(event.getName())) {
			
			renderer.editCurrentCol(true);
			addKeyListener();

		}
		else if (Events.ON_CLICK.equals(event.getName()))
		{
			if(tabPanel != null)
			{
				if (tabPanel.getGlobalToolbar() != null
						&& tabPanel.getGlobalToolbar().getCurrentPanel() != tabPanel) {		
					tabPanel.getGlobalToolbar().getCurrentPanel().activate(false);
					tabPanel.setUnselected(tabPanel.getGlobalToolbar().getCurrentPanel());
					tabPanel.setSelected(tabPanel);
					tabPanel.activate(true);
					
				}
			}	
			Object data = event.getData();
			org.zkoss.zul.Row row = null;
			boolean changeRow = false;
			
			if (data != null && data instanceof Component) {
				if (data instanceof org.zkoss.zul.Row)
					row = (org.zkoss.zul.Row) data;
				else {
					AbstractComponent cmp = (AbstractComponent) data;
					if (cmp.getParent() instanceof org.zkoss.zul.Row)
					{
						row = (Row) cmp.getParent();
						//columnName = (String) cmp.getAttribute("columnName");
					}
				}
			}
			if (row != null) {
				if(data instanceof Col) {
					Col col = (Col)data;
					currentCol = (Integer)(col).getAttribute("columnNo");
					if(row == renderer.getCurrentRow() && currentCol==renderer.getCurrentColumn()) {
						return;
					}
				}
				//click on selected row to enter edit mode
				if (row != renderer.getCurrentRow()) {
					changeRow=true;
					if(dataSave(0)) {
						renderer.editCurrentCol(true);
						return;
					}
					if(renderer.getCurrentDiv() != null) {
						renderer.stopColEditing(true);
					}
					int index = listbox.getRows().getChildren().indexOf(row);
//					renderer.setCurrentRow(row);
					renderer.setCurrentCell(index);
					if (index >= 0 ) {
//						onSelectedRowChange(index);
					}
				}
			}
			if(data instanceof Col) {
				Col col = (Col)data;
				if(renderer.isEditing())
					renderer.stopColEditing(false);
				currentCol = (Integer)(col).getAttribute("columnNo");
//				gridTab.navigateCurrent();
				renderer.setCurrentColumn(currentCol);
			}
				if(changeRow) {
					
					gridTab.dataIgnore();
				}
//				updateListIndex();
				addKeyListener();
        }
		else if (event.getName().equals(Events.ON_CTRL_KEY) && event.getTarget() == keyListener) {
			KeyEvent keyEvent = (KeyEvent) event;
			int code = keyEvent.getKeyCode();
			boolean isAlt = keyEvent.isAltKey();
			boolean isCtrl = keyEvent.isCtrlKey();
			boolean isShift = keyEvent.isShiftKey();
			lastKeyEvent = code;
			if(renderer == null || gridTab == null) 
				return;
			
			currentCol = gridTab.getCurrentCol();
				
			int row = renderer.getCurrentRowIndex();
			
			int totalRow = gridTab.getRowCount();
			
			if (code == KEYBOARD_KEY_RETURN && !isShift)
			{
				if(totalRow <= 0)
					return;
				if(renderer.getCurrentDiv() != null) {
					if (renderer.isEditing()) { 
						renderer.stopColEditing(true);
						if(renderer.isLastColumn()) {
							currentCol=0;
							row++;
							if (row == totalRow) {
								if(!dataSave(0)) {
									onNew();
								}
							}
							else if(!dataSave(0)) {
								gridTab.navigateRelative(+1);
								renderer.setCurrentCell(row);
							}
						}
						else {
							currentCol++;
						}
						renderer.setCurrentColumn(currentCol);
					}
					if(renderer != null && renderer.getCurrentDiv() != null && 
							renderer.getCurrentDiv().getEditor() != null &&
							renderer.getCurrentDiv().getEditor().getGridField().getDisplayType() == DisplayType.Button)  {
						WEditor editor = renderer.getCurrentDiv().getEditor();
						Event evt = new Event(Events.ON_CLICK, editor.getComponent(),editor.getComponent());
						Events.sendEvent(editor.getComponent(), evt);
					}
					while(!renderer.editCurrentCol(true) && currentCol <= renderer.getTotalColumns()) {
						currentCol++;
						renderer.setCurrentColumn(currentCol);
					}
				
				}
				addKeyListener();
			}
			else if (code == KEYBOARD_KEY_S && isCtrl && !isAlt && !isShift) {
				if (!dataSave(code))
					return;
			}
			else {
				// save data if row changes is made.
				if (code == KeyEvent.RIGHT || code == KeyEvent.LEFT || code == KeyEvent.DOWN || code == KeyEvent.UP || 
						code == KeyEvent.HOME || code == KeyEvent.END) {
					if(totalRow <= 0 )
						return;
					gridTab.dataSave(true);
					if (renderer.isEditing() && !gridTab.needSave(true, true))
						renderer.stopColEditing(true);
					ArrayList<Integer> i = gridTab.getMTable().getRowChanged();
					if (i.contains(row)) {
						//if (!save(code, row, col))
						//	return;
					}
				}

				if (code == KeyEvent.DOWN && !isCtrl && !isAlt && !isShift)	{
					setUpDownRow(1);
				}
				else if ((code == KEYBOARD_KEY_RETURN && isShift) || (code == KeyEvent.LEFT && !isCtrl && !isAlt && !isShift))
				{
					if(currentCol > 0) {
						if(row < 0 || gridTab.getCurrentRow()< 0) {
							renderer.setGrid(listbox);
							renderer.setCurrentCell(0);
						} else {
							renderer.stopColEditing(true);
							renderer.setCurrentColumn(currentCol-1);
						}
						
						addKeyListener();
					}
				}
				else if (code == KeyEvent.DELETE && !isCtrl && !isAlt && !isShift)
				{
					gridTab.dataDelete();
				}
				else if (code == KeyEvent.RIGHT && !isCtrl && !isAlt && !isShift)
				{
					if(row < 0 || gridTab.getCurrentRow()< 0) {
						renderer.setGrid(listbox);
						renderer.setCurrentCell(0);
					} else
						renderer.setCurrentColumn(currentCol+1);
				}
				else if (code == KeyEvent.UP && !isCtrl && !isAlt && !isShift)
				{
					if(totalRow > 0 && !dataSave(0) || !gridTab.isNew()) {
						setUpDownRow(-1);
					}
					return;
				}
				else if (code == KeyEvent.HOME)
				{
					row = 0;
					renderer.setCurrentCell(row);
					renderer.setCurrentColumn(currentCol);
				}
				else if (code == KeyEvent.PAGE_DOWN)
				{
					setUpDownRow(MOVE_SIZE);
				}
				else if (code == KeyEvent.PAGE_UP)
				{
					setUpDownRow(-MOVE_SIZE);
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
		
		keyListener.invalidate();
	}
	

	private void onSelectedRowChange(int index) {
		if (updateModelIndex(index)) {
			updateListIndex();
		}
	}

	/**
	 * Event after the current selected row change
	 */
	public void onPostSelectedRowChanged() {
		if (listbox.getRows().getChildren().isEmpty())
			return;
		int rowIndex  = gridTab.isOpen() ? gridTab.getCurrentRow() : -1;
		if (rowIndex >= 0 && pageSize > 0) {
			int pgIndex = rowIndex >= 0 ? rowIndex % pageSize : 0;
			org.zkoss.zul.Row row = (org.zkoss.zul.Row) listbox.getRows().getChildren().get(pgIndex);
			if (!isRowRendered(row, pgIndex)) {
				listbox.renderRow(row);
			} else {
				Row old = renderer.getCurrentRow();
				int oldIndex = renderer.getCurrentRowIndex();
				renderer.setCurrentRow(row);
				if (old != null && old != row && oldIndex >= 0 && oldIndex != gridTab.getCurrentRow())
				{
					listModel.updateComponent(oldIndex % pageSize);
				}
			}
			if (modeless && !renderer.isEditing()) {
				renderer.editCurrentCol(false);
				if (columnOnClick != null && columnOnClick.trim().length() > 0) {
					setFocusToField(columnOnClick);
					columnOnClick = null;
				} else {
					renderer.setFocusToEditor();
				}
			} else {
				focusToRow(row);

			}
			
			renderer.setCurrentColumn(currentCol);
	} else if (rowIndex >= 0) {
			org.zkoss.zul.Row row = (org.zkoss.zul.Row) listbox.getRows().getChildren().get(rowIndex);
			if (!isRowRendered(row, rowIndex)) {
				listbox.renderRow(row);
			} else {
				Row old = renderer.getCurrentRow();
				int oldIndex = renderer.getCurrentRowIndex();
				renderer.setCurrentRow(row);
				if (old != null && old != row && oldIndex >= 0 && oldIndex != gridTab.getCurrentRow())
				{
					listModel.updateComponent(oldIndex);
					renderer.setCurrentColumn(currentCol);
				}
			}
			if (modeless && !renderer.isEditing()) {
				renderer.editCurrentCol(false);
				if (columnOnClick != null && columnOnClick.trim().length() > 0) {
					setFocusToField(columnOnClick);
					columnOnClick = null;
				} else {
//					renderer.setFocusToEditor();
				}
			} else {
				focusToRow(row);
			}
		}
		if(gridTab.isNew() || lastKeyEvent == KEYBOARD_KEY_RETURN) {
			renderer.setCurrentColumn(0); 
			if(renderer.isEditing())
				renderer.stopColEditing(true);
			renderer.editCurrentCol(true);
		}
		
		// Verify Key Listener
		addKeyListener();
	}

	/**
	 * scroll grid to the current focus row
	 */
	public void scrollToCurrentRow() {
		onPostSelectedRowChanged();
	}
	
	private void focusToRow(org.zkoss.zul.Row row) {
		if (renderer.isEditing()) {
			if (columnOnClick != null && columnOnClick.trim().length() > 0) {
				setFocusToField(columnOnClick);
				columnOnClick = null;
			} else {
				renderer.setFocusToEditor();
			}
		} else {
			Component cmp = null;
			List<?> childs = row.getChildren();
			for (Object o : childs) {
				Component c = (Component) o;
				if (!c.isVisible())
					continue;
				c = c.getFirstChild();
				if (c == null)
					continue;
				if (c.getNextSibling() != null) {
					cmp = c.getNextSibling();
					break;
				}
			}
			if (cmp != null)
				Clients.response(new AuScript(null, "scrollToRow('" + cmp.getUuid() + "');"));

			if (columnOnClick != null && columnOnClick.trim().length() > 0) {
				List<?> list = row.getChildren();
				for(Object element : list) {
					if (element instanceof Col) {
						Col div = (Col) element;
						if (columnOnClick.equals(div.getAttribute("columnName"))) {
							cmp = div.getFirstChild().getNextSibling();
							Clients.response(new AuScript(null, "scrollToRow('" + cmp.getUuid() + "');"));
							break;
						}
					}
				}
				columnOnClick = null;
			}
		}

	}

	private boolean isRowRendered(org.zkoss.zul.Row row, int index) {
		if (row.getChildren().size() == 0) {
			return false;
		} else if (row.getChildren().size() == 1) {
			if (!(row.getChildren().get(0) instanceof Div)) {
				return false;
			}
		}
		return true;
	}

	private boolean updateModelIndex(int rowIndex) {
		if (pageSize > 0) {
			int start = listModel.getPage() * listModel.getPageSize();
			rowIndex = start + rowIndex;
		}

		if (gridTab.getCurrentRow() != rowIndex) {
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
		if (gridTab == null || !gridTab.isOpen())
        {
            return;
        }

        //  Selective
        if (col > 0)
        {
        	GridField changedField = gridTab.getField(col);
            String columnName = changedField.getColumnName();
            ArrayList<?> dependants = gridTab.getDependantFields(columnName);
            if (dependants.size() == 0 && changedField.getCallout().length() > 0)
            {
                return;
            }
        }
        	

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
                    boolean rw = mField.isEditable(true);   //  r/w - check Context
                    comp.setReadWrite(rw);
                    comp.dynamicDisplay();
                }

                comp.repaintComponent(true);
            }
        }   //  all components
	}

	public void repaintComponents() {
		if(renderer!=null)
			for(WEditor editor : renderer.getEditors())
				if(editor!=null)
					editor.repaintComponent(true);
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
		if (renderer != null && renderer.isEditing()) {
			renderer.setFocusToEditor();
		}
		addKeyListener();
	}

	/**
	 * Handle enter key event
	 */
	public boolean onEnterKey() {
		if (!modeless && renderer != null && !renderer.isEditing()) {
			return true;
		}
		return false;
	}

	/**
	 * @param columnName
	 */
	public void setFocusToField(String columnName) {
		boolean found = false;
		for (WEditor editor : renderer.getEditors()) {
			if (found)
				editor.setHasFocus(false);
			else if (columnName.equals(editor.getColumnName())) {
				editor.setHasFocus(true);
				Clients.response(new AuFocus(editor.getComponent()));
				found = true;
			}
		}
	}
	
	public void focusCurrentCol() {
		if(renderer != null && renderer.getCurrentDiv() != null && gridTab.getRowCount() > 0) {
			renderer.getCurrentDiv().setFocus(true);
			renderer.stopColEditing(true);
			renderer.getCurrentDiv().invalidate();
		}

	}
	
	/**
	 * 
	 * @param code
	 * @return
	 */
	public boolean dataSave(int code)
	{
		boolean isSave = gridTab.dataSave(true);

		if (!isSave)
		{
			String msg = CLogger.retrieveErrorString(null);
			if (msg != null)
			{
				windowPanel.getStatusBar().setStatusLine(Msg.getMsg(Env.getCtx(), msg), true, true);
			}
        }
		if(windowPanel.getToolbar().getCurrentPanel() != null)
			windowPanel.getToolbar().getCurrentPanel().afterSave(true);
		isSave = gridTab.needSave(true, true);
		if(!gridTab.isNew()) {
			updateToolbar(true);
			isNewRecord = false;
		}
		return isSave;
	}


	public void createNewLine()
	{
		isNewRecord = gridTab.dataNew(false);
	}
	
	/**
	 * @param winPanel
	 */
	public void setADWindowPanel(AbstractADWindowPanel winPanel) {
		windowPanel = winPanel;
		if (renderer != null)
			renderer.setADWindowPanel(windowPanel);
	}
	
	public void updateCellStyle() {
		if(renderer != null)
			renderer.validCondition();
	}


	/**
	 * 
	 */
	public void addKeyListener() {
		if(renderer == null)
			return;
		if(keyListener == null) { 
			keyListener = new Keylistener();
			if (windowPanel != null)
				windowPanel.getStatusBar().appendChild(keyListener);
		}
		if(renderer.isEditing() || !((ADTabPanel)tabPanel).isGridView() ) 
			keyListener.setCtrlKeys(CNTRL_KEYS);
		else 
			keyListener.setCtrlKeys(CNTRL_KEYS+KEYS_MOVE);
		
		keyListener.addEventListener(Events.ON_CTRL_KEY, this);
	}
	
	protected void createListbox()
	{
		listbox = new Grid();
		listbox.setOddRowSclass(null);
		listbox.setVflex(true);
	} // createListbox
	
	private void onNew() {
		if(renderer.getTotalColumns() != -1) {
			isNewRecord = gridTab.dataNew(false);
	        if (isNewRecord) {
	        	updateToolbar(false);
				updateListIndex();
				addKeyListener();
	        }
		}
	}
	
	public Keylistener getKeyListener() {
		return keyListener;
	}
	
	public void onIgnore() {
		if(gridTab != null) {
			gridTab.dataIgnore();
			gridTab.dataRefresh();
			updateToolbar(true);
			isNewRecord = false;
		}
	
	}
	
	private void updateToolbar(boolean enabled) {
		if(windowPanel.getToolbar().getCurrentPanel() != null)
			windowPanel.getToolbar().getCurrentPanel().dynamicDisplay(0);
    	windowPanel.getToolbar().enableChanges(enabled);
    	windowPanel.getToolbar().enableDelete(enabled);
    	windowPanel.getToolbar().enableDeleteSelection(enabled);
    	windowPanel.getToolbar().enableNavigation(enabled);
    	windowPanel.getToolbar().enableTabNavigation(enabled);
    	windowPanel.getToolbar().enableIgnore(!enabled);
    	windowPanel.getToolbar().enablePrint(gridTab.isPrinted());
    
	}
	
	private void setUpDownRow(int value) {
		int row = renderer.getCurrentRowIndex();
		int totalRow = gridTab.getRowCount();

		if(totalRow <= 0) {
			onNew();
			onPostSelectedRowChanged();
		}
		else if(((GridTable)tableModel).checkField(row)) {
			
			// multiply by 2 to avoid last row
			int verifyRow = row + (value * 2);
			row += value;
			if(row < 0)
				return;
			else if(verifyRow < 0)
				verifyRow = 0;
			else if(verifyRow >= totalRow)
				verifyRow = totalRow-1;
			
			// set next Row for scroll
			org.zkoss.zul.Row r = (org.zkoss.zul.Row) listbox.getRows().getChildren().get(verifyRow);
			
			if(row >= totalRow) {
				row = totalRow;
				gridTab.navigate(totalRow-1);
				renderer.setCurrentColumn(currentCol);
			}
			if (row == totalRow)	{
				if(!isNewRecord && !dataSave(0)) {
					if(renderer.isLastColumn()) {
						renderer.setCurrentColumn(0); 
					}
					onNew();
				}
				return;
			}
			else {
				if(!gridTab.isNew()) {
					gridTab.navigateRelative(value);
					renderer.setCurrentCell(row);
					renderer.setCurrentColumn(currentCol);
					Clients.scrollIntoView(r);
				}
			}
		}
	}
}
