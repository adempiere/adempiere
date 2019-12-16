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

import java.awt.AWTEvent;

/**
 * A generic table change event.
 * @author Michael McKay, mckayERP@gmail.com
 * 
 * @version 3.9.4
 *
 */
public class TableChangeEvent extends AWTEvent {

    public static final int TABLE_CHANGED = AWTEvent.RESERVED_ID_MAX + 2;
    
	private int row = -1;
	private int column = -1;
	private Object value;
	
	public TableChangeEvent(IMiniTable source, int row, int col, Object value) {
		super(source, TABLE_CHANGED);
		this.row = row;
		this.column = col;
		this.value = value;
	}

	/**
	 * @return the row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @param row the row to set
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * @return the column
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * @param column the column to set
	 */
	public void setColumn(int column) {
		this.column = column;
	}

	/**
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * @param newValue the newValue to set
	 */
	public void setValue(Object value) {
		this.value = value;
	}
	
	

}
