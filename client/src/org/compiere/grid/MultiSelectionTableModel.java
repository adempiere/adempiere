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
package org.compiere.grid;

import java.util.Vector;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import org.adempiere.exceptions.AdempiereException;
import org.apache.commons.collections.BidiMap;
import org.compiere.minigrid.IDColumn;
import org.compiere.util.CLogger;

/**
 * A class that manages a table with multiselection where editors are read only
 * if the row is not selected.  It also manages the visibility of columns while preserving 
 * the column data.  This allows the rendered table to only have visible columns
 * yet the data can contain hidden columns that do not affect scrolling or tab key
 * behaviour for the user.
 * 
 * Essentially, this is a double wrapped DefaultTableModel.  The interface with the
 * grid controller has all the columns.  The table model used for display only has
 * visible columns.
 * 
 * @author Michael McKay, mckayERP@gmail.com
 *
 */
public class MultiSelectionTableModel extends DefaultTableModel implements TableModelListener {


	/**
	 * The index in the model of the key column used for selection.
	 */
	private int keyColumnIndex;
	
	/**
	 * 
	 */
	public MultiSelectionTableModel() {
		this.addTableModelListener(this);
	}

	/**
	 * @param rowCount
	 * @param columnCount
	 */
	public MultiSelectionTableModel(int rowCount, int columnCount) {
		super(rowCount, columnCount);
	}

	/**
	 * @param columnNames
	 * @param rowCount
	 */
	public MultiSelectionTableModel(Vector columnNames, int rowCount) {
		super(columnNames, rowCount);
	}

	/**
	 * @param columnNames
	 * @param rowCount
	 */
	public MultiSelectionTableModel(Object[] columnNames, int rowCount) {
		super(columnNames, rowCount);
	}

	/**
	 * @param data
	 * @param columnNames
	 */
	public MultiSelectionTableModel(Vector data, Vector columnNames) {
		super(data, columnNames);
	}

	/**
	 * @param data
	 * @param columnNames
	 */
	public MultiSelectionTableModel(Object[][] data, Object[] columnNames) {
		super(data, columnNames);
	}

    /**
     * Returns true regardless of parameter values.
     *
     * @param   row             the row whose value is to be queried in the model
     * @param   column          the column whose value is to be queried in the model
     * @return                  true if the row is selectable and selected
     * @see #setValueAt
     */
    public boolean isCellEditable(int row, int column) {
    	    	
		int keyColumn = this.getKeyColumnIndex();
		
		//  No key column implies editable
		//  Key columns themselves are always editable
		if (keyColumn < 0 || keyColumn == column)
			return true;  // No key column implies always editable
		    	
		//  The selection can be indicated by an IDColumn or 
		//  Boolean in the keyColumn position
		Object data = getValueAt(row, keyColumn); 
		if (data instanceof IDColumn)
			return ((IDColumn) data).isSelected();
		else if (data instanceof Boolean)
			return (Boolean) data;

        return super.isCellEditable(row, column);  // Default
    }

	/**
	 * @return the keyColumnIndex
	 */
	public int getKeyColumnIndex() {
		return keyColumnIndex;
	}

	/**
	 * @param keyColumnIndex the keyColumnIndex to set
	 */
	public void setKeyColumnIndex(int keyColumnIndex) {
		
		this.keyColumnIndex = keyColumnIndex;
		
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		
	}

}
