/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
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
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.grid;

import java.awt.*;
import java.beans.*;
import javax.swing.table.*;
import org.compiere.grid.ed.*;
import org.compiere.model.*;
import org.compiere.swing.*;
import org.compiere.util.*;

/**
 *  Table Grid based on CTable.
 * 	Used in GridController
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: VTable.java,v 1.2 2006/07/30 00:51:28 jjanke Exp $
 */
public final class VTable extends CTable 
	implements PropertyChangeListener
{
	/**
	 *	Default Constructor
	 */
	public VTable()
	{
		super();
		setAutoscrolls(true);
		putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
	}	//	VTable

	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(VTable.class);
	
	/**
	 *  Property Change Listener for CurrentRow.
	 *  - Selects the current row if not already selected
	 *  - Required when navigating via Buttons
	 *  @param evt event
	 */
	public void propertyChange(PropertyChangeEvent evt)
	{
	//	log.config(evt);
		if (evt.getPropertyName().equals(GridTab.PROPERTY))
		{
			int row = ((Integer)evt.getNewValue()).intValue();
			int selRow = getSelectedRow();
			if (row == selRow)
				return;
			log.config(GridTab.PROPERTY + "=" + row + " from " + selRow);
			setRowSelectionInterval(row,row);
		    Rectangle cellRect = getCellRect(row, 1, false);
		    if (cellRect != null)
		    	scrollRectToVisible(cellRect);
			log.config(GridTab.PROPERTY + "=" + row + " from " + selRow);
		}
	}   //  propertyChange

	/**
	 *	Get ColorCode for Row.
	 *  <pre>
	 *	If numerical value in compare column is
	 *		negative = -1,
	 *      positive = 1,
	 *      otherwise = 0
	 *  </pre>
	 *  @param row row
	 *  @return color code
	 */
	public int getColorCode (int row)
	{
		return ((GridTable)getModel()).getColorCode(row);
	}   //  getColorCode

	/**
	 *  Sort Table
	 *  @param modelColumnIndex model column sort index
	 */
	protected void sort (int modelColumnIndex)
	{
		int rows = getRowCount();
		if (rows == 0)
			return;
		//
		TableModel model = getModel();
		if (!(model instanceof GridTable))
		{
			super.sort(modelColumnIndex);
			return;
		}

		sorting = true;
		//  other sort column
		if (modelColumnIndex != p_lastSortIndex)
			p_asc = true;
		else
			p_asc = !p_asc;

		p_lastSortIndex = modelColumnIndex;
		//
		log.config("#" + modelColumnIndex
			+ " - rows=" + rows + ", asc=" + p_asc);

		((GridTable)model).sort(modelColumnIndex, p_asc);
		
		sorting = false;
		//  table model fires "Sorted" DataStatus event which causes MTab to position to row 0
	}   //  sort

	/**
	 *  Transfer focus explicitly to editor due to editors with multiple components
	 *
	 *  @param row row
	 *  @param column column
	 *  @param e event
	 *  @return true if cell is editing
	 */
	public boolean editCellAt (int row, int column, java.util.EventObject e)
	{
		if (!super.editCellAt(row, column, e))
			return false;
	//	log.fine( "VTable.editCellAt", "r=" + row + ", c=" + column);

		Object ed = getCellEditor();
		if (ed instanceof VEditor)
			((Component)ed).requestFocus();
		else if (ed instanceof VCellEditor)
		{
			ed = ((VCellEditor)ed).getEditor();
			((Component)ed).requestFocus();
		}
		return true;
	}   //  editCellAt

	/**
	 *  toString
	 *  @return String representation
	 */
	public String toString()
	{
		return new StringBuffer("VTable[")
			.append(getModel()).append("]").toString();
	}   //  toString
	
}	//	VTable
