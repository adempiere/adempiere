/******************************************************************************
 * Copyright (C) 2012 Heng Sin Low                                            *
 * Copyright (C) 2012 Trek Global                 							  *
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
package org.compiere.print.util;

import java.io.Serializable;
import java.util.List;

public interface SerializableMatrix<T extends Serializable> {

	public void addRow(List<T> data);

	public boolean setRowIndex(int row);

	/**
	 * 	Set Row Index to next
	 * 	@return true if success
	 */
	public boolean setRowNext(); //	setRowNext

	/**
	 * 	Get Row Count
	 * 	@return row count
	 */
	public int getRowCount(); //	getRowCount

	/**
	 * 	Get Current Row Index
	 * 	@return row index
	 */
	public int getRowIndex(); //	getRowIndex

	public List<T> getRowData();

	public void setRowData(List<T> data);
}