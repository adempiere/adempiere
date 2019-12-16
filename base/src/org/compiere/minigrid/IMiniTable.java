/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2019 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/
package org.compiere.minigrid;

import java.sql.ResultSet;
import java.util.ArrayList;

import org.compiere.model.PO;

/**
 *	An interface for the minitable - a table intended for row selection but not
 *  editing. 
 *
 */
public interface IMiniTable 
{
	public boolean isCellEditable(int row, int column);
	
	public Object getValueAt(int row, int column);
	
	public void setValueAt(Object value, int row, int column);
	
	public int convertColumnIndexToModel(int viewColumnIndex);
	
	public int convertColumnIndexToView(int modelColumnIndex);
	
	public void setColumnReadOnly (int index, boolean readOnly);
	
	public String prepareTable(ColumnInfo[] layout, String from, String where, boolean multiSelection, String tableName);
	
	public void addColumn (String header);
	
	public void setColumnClass (int index, Class<?> classType, boolean readOnly, String header);
	
	public void setColumnClass (int index, Class<?> classType, boolean readOnly);
	
	public void loadTable(ResultSet rs);
	
	public void loadTable(PO[] pos);
	
	public void setKeyColumnIndex (int keyColumnIndex);

	public int getKeyColumnIndex();
	
	public ArrayList<Integer> getSelectedKeys();
	
	public Integer getSelectedRowKey();
	
	public int getSelectedRow();
	
	public int getRowKey(int row);
	
	public boolean isRowChecked(int row);
	
	public void setRowCount (int rowCount);
	
	public ColumnInfo[] getLayoutInfo();

	public int getColumnCount();
	
	public int getRowCount();
	
	public boolean getShowTotals();
	
	public void setMultiSelection(boolean multiSelection);
	
	public boolean isMultiSelection();
	
	public int getColorCode (int row);
	
	public void setColorCompare (Object dataCompare);
	
	public void repaint();
	
	public void autoSize();

	public int convertRowIndexToModel(int row);
	
	public void setRowChecked(int row, boolean value);

	/** 
	 * Recreate the list header. (Only applies to ZK)
	 */
	public void recreateListHead();

	/** 
	 * Clear the table of data
	 */
	public void clear();
	
	/**
	 * Add a table selection listener
	 * @param l - the listener to add
	 */
	public void addTableSelectionListener(SelectionListener l);
	
	/**
	 * Remove a table selection listener
	 * @param l - the listener to remove
	 */
	public void removeTableSelectionListener(SelectionListener l);

	/**
	 * Add a table change listener
	 * @param l the listener to add
	 */
	public void addTableChangeListener(TableChangeListener l);

	/**
	 * Remove a table change listener
	 * @param l the listener to remove
	 */
	public void removeTableChangeListener(TableChangeListener l);
	
	/**
	 * Set by the VHeaderRenderer, this flag indicates a Select All event 
	 * is underway.  The flag can be checked to limit processes that 
	 * run on row selection events in cases where there are many rows
	 * to process.
	 * @param eventUnderWay
	 */
	public void setSelectingAll(boolean eventUnderway);
	
	/**
	 * Is a Select All event underway?
	 * @return true if an event is in process.
	 */
	public boolean isSelectingAll();

	/**
	 * Set by the VHeaderRenderer, this flag indicates a Deselect All event 
	 * is underway.  The flag can be checked to limit processes that 
	 * run on row deselection events in cases where there are many rows
	 * to process.
	 * @param eventUnderWay
	 */
	public void setDeselectingAll(boolean eventUnderway);

	/**
	 * Is a Deselect All event underway?
	 * @return true if an event is in process.
	 */
	public boolean isDeselectingAll();

	/**
	 * Sets the column as mandatory
	 * @param column the index of the column
	 * @param mandatory true to set the column as mandatory
	 */
	void setColumnMandatory(int column, boolean mandatory);

	/**
	 * Returns true if the cell at <code>row</code> and <code>column</code>
	 * is mandatory
	 * @param row
	 * @param col
	 * @return
	 */
	public boolean isCellMandatory(int row, int col);

}
