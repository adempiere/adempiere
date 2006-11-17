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
package org.compiere.grid.ed;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import org.compiere.util.*;

/**
 * 	No Table Edit/Renderer
 *	
 *  @author Jorg Janke
 *  @version $Id: TableCellNone.java,v 1.2 2006/07/30 00:51:28 jjanke Exp $
 */
public class TableCellNone
	implements TableCellRenderer, TableCellEditor
{
	/**
	 * 	Table Cell None constructor
	 *	@param ColumnName name
	 */
	public TableCellNone (String ColumnName)
	{
		m_ColumnName = ColumnName;
	}	//	TableCellNone
	
	/** Column Name				*/
	private String		m_ColumnName;
	/** Object					*/
	private Object		m_value = null;
	
	/**	Logger	*/
	private static CLogger log = CLogger.getCLogger (TableCellNone.class);

	/**
	 * 	Get Table Cell Renderer Component
	 *	@param table
	 *	@param value
	 *	@param isSelected
	 *	@param hasFocus
	 *	@param row
	 *	@param col
	 *	@return null
	 */
	public Component getTableCellRendererComponent (JTable table, Object value,
		boolean isSelected, boolean hasFocus, int row, int col)
	{
		log.finest(m_ColumnName + ": Value=" + value + ", row=" + row + ", col=" + col);
		m_value = value;
		return null;
	}

	/**
	 * 	Get Table Cell Editor Component
	 *	@param table
	 *	@param value
	 *	@param isSelected
	 *	@param row
	 *	@param col
	 *	@return null
	 */
	public Component getTableCellEditorComponent (JTable table, Object value,
		boolean isSelected, int row, int col)
	{
		log.finest(m_ColumnName + ": Value=" + value + ", row=" + row + ", col=" + col);
		m_value = value;
		return null;
	}

	/**
	 * 	Get Cell Editor Value
	 *	@return null
	 */
	public Object getCellEditorValue ()
	{
		log.finest(m_ColumnName + "=" + m_value);
		return m_value;
	}

	/**
	 * 	Is Cell Editable
	 *	@param anEvent
	 *	@return false
	 */
	public boolean isCellEditable (EventObject anEvent)
	{
		log.finest(m_ColumnName);
		return false;
	}

	/**
	 * 	Should Select Cell
	 *	@param anEvent
	 *	@return false
	 */
	public boolean shouldSelectCell (EventObject anEvent)
	{
		log.finest(m_ColumnName);
		return false;
	}

	/**
	 * 	Stop Cell Editing
	 *	@return true
	 */
	public boolean stopCellEditing ()
	{
		return true;
	}

	/**
	 * 	Cancel Cell Editing
	 */
	public void cancelCellEditing ()
	{
	}

	
	public void addCellEditorListener (CellEditorListener l)
	{
	}

	public void removeCellEditorListener (CellEditorListener l)
	{
	}
	
}	//	TableCellNone
