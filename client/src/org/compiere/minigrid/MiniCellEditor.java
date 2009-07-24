/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
package org.compiere.minigrid;

import java.awt.Component;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import org.compiere.grid.ed.VDate;
import org.compiere.grid.ed.VEditor;
import org.compiere.grid.ed.VNumber;
import org.compiere.grid.ed.VString;
import org.compiere.util.DisplayType;

/**
 *  MiniTable Cell Editor based on class - Timestamp, BigDecimal
 *
 *  @author     Jorg Janke
 *  @version    $Id: MiniCellEditor.java,v 1.2 2006/07/30 00:51:28 jjanke Exp $
 */
public class MiniCellEditor extends AbstractCellEditor implements TableCellEditor
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4431508736596874253L;

	/**
	 *  Default Constructor
	 *  @param c Class
	 */
	public MiniCellEditor(Class c)
	{
		super();
		//  Date
		if (c == Timestamp.class)
			m_editor = new VDate();
		else if (c == BigDecimal.class)
			m_editor = new VNumber("Amount", false, false, true, DisplayType.Amount, "Amount");
		else if (c == Double.class)
			m_editor = new VNumber("Number", false, false, true, DisplayType.Number, "Number");
		else if (c == Integer.class)
			m_editor = new VNumber("Integer", false, false, true, DisplayType.Integer, "Integer");
		else
			m_editor = new VString();

	}   //  MiniCellEditor

	private VEditor m_editor = null;

	/**
	 *	Sets an initial value for the editor. This will cause the editor to
	 *	stopEditing and lose any partially edited value if the editor is editing
	 *	when this method is called.
	 *	Returns the component that should be added to the client's Component hierarchy.
	 *	Once installed in the client's hierarchy this component
	 *	will then be able to draw and receive user input.
	 *  @param table
	 *  @param value
	 *  @param isSelected
	 *  @param row
	 *  @param column
	 *  @return Component
	 */
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)
	{
	//	ADebug.trace(ADebug.l5_DData, "VCellEditor.getTableCellEditorComponent - " + value == null ? "null" : value.toString());

		//	Set Value
		m_editor.setValue(value);

		//	Set UI
		m_editor.setBorder(null);
	//	m_editor.setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
		m_editor.setFont(table.getFont());
		return (Component)m_editor;
	}	//	getTableCellEditorComponent

	/**
	 *	Returns the value contained in the editor
	 *  @return value
	 */
	public Object getCellEditorValue()
	{
	//	ADebug.trace(ADebug.l5_DData, "VCellEditor.getCellEditorValue - ");

		if (m_editor != null)
			return m_editor.getValue();
		return null;
	}	//	getCellEditorValue

}   //  MiniCellEditor
