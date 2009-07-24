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
package org.compiere.grid.ed;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.VetoableChangeListener;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellEditor;

import org.adempiere.plaf.AdempierePLAF;
import org.compiere.model.GridField;
import org.compiere.model.GridTable;
import org.compiere.util.CLogger;

/**
 *  Cell Editor.
 *  <pre>
 *		Sequence of events:
 *			isCellEditable
 *			getTableCellEditor
 *			shouldSelectCell
 *			getCellEditorValue
 *  </pre>
 *  A new Editor is created for editable columns.
 *  @author 	Jorg Janke
 *  @version 	$Id: VCellEditor.java,v 1.2 2006/07/30 00:51:28 jjanke Exp $
 */
public final class VCellEditor extends AbstractCellEditor
	implements TableCellEditor, VetoableChangeListener, ActionListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1406944397509282968L;

	/**
	 *	Constructor for Grid
	 *  @param mField
	 */
	public VCellEditor (GridField mField)
	{
		super();
		m_mField = mField;
		//  Click
	}	//	VCellEditor

	/** The Field               */
	private GridField          m_mField = null;
	/** The Table Editor        */
	private VEditor	        m_editor = null;
	
	/** Table                   */
	private JTable          m_table = null;
	private ActionListener buttonListener;
	private ActionListener actionListener;
	
	/** ClickCount              */
	private static int      CLICK_TO_START = 1;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(VCellEditor.class);

	/**
	 *  Create Editor
	 */
	private void createEditor()
	{
		m_editor = VEditorFactory.getEditor(m_mField, true);
		m_editor.addVetoableChangeListener(this);
		m_editor.addActionListener(this);
				
	}   //  createEditor

	/**
	 *	Ask the editor if it can start editing using anEvent.
	 *	If editing can be started this method returns true.
	 *	Previously called: MTable.isCellEditable
	 *  @param anEvent event
	 *  @return true if editable
	 */
	public boolean isCellEditable (EventObject anEvent)
	{
		if (!m_mField.isEditable (true))	//	row data is in context
			return false;
		log.fine(m_mField.getHeader());		//	event may be null if CellEdit
		//	not enough mouse clicks
		if (anEvent instanceof MouseEvent 
			&& ((MouseEvent)anEvent).getClickCount() < CLICK_TO_START)
			return false;
			
		if (m_editor == null)
			createEditor();
		return true;
	}	//	isCellEditable

	/**
	 *	Sets an initial value for the editor. This will cause the editor to
	 *	stopEditing and lose any partially edited value if the editor is editing
	 *	when this method is called.
	 *	Returns the component that should be added to the client's Component hierarchy.
	 *	Once installed in the client's hierarchy this component
	 *	will then be able to draw and receive user input.
	 *
	 *  @param table
	 *  @param value
	 *  @param isSelected
	 *  @param row
	 *  @param col
	 *  @return component
	 */
	public Component getTableCellEditorComponent (JTable table, Object value, boolean isSelected, int row, int col)
	{
		log.finest(m_mField.getColumnName() + ": Value=" + value + ", row=" + row + ", col=" + col);
		if (row >= 0)
			table.setRowSelectionInterval(row,row);     //  force moving to new row
		if (m_editor == null ) 
			createEditor();
		
		if ( m_editor instanceof VLookup) 
		{
			((VLookup)m_editor).setStopEditing(false);
		}
		
		m_editor.setReadWrite(m_mField.isEditable (true));

		m_table = table;

		//	Set Value
		m_editor.setValue(value);
		
		//	Set Background/Foreground to "normal" (unselected) colors
		m_editor.setBackground(m_mField.isError());
		m_editor.setForeground(AdempierePLAF.getTextColor_Normal());

		//  Other UI
		m_editor.setFont(table.getFont());
		if ( m_editor instanceof VLookup) {
			VLookup lookup = (VLookup)m_editor;
			if (lookup.getComponents()[0] instanceof JComboBox) {
				lookup.setBorder(BorderFactory.createEmptyBorder());
			} else {
				lookup.setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
			}
		} else {
			m_editor.setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
		}
		//
		return (Component)m_editor;
	}	//	getTableCellEditorComponent

	/**
	 *	The editing cell should be selected or not
	 *  @param e event
	 *  @return true (constant)
	 */
	public boolean shouldSelectCell(EventObject e)
	{
		log.finest(m_mField.getColumnName());
		return true;
	}	//	shouldSelectCell

	/**
	 *	Returns the value contained in the editor
	 *  @return value
	 */
	public Object getCellEditorValue()
	{
		log.finest(m_mField.getColumnName() + ": " + m_editor.getValue());
		return m_editor.getValue();
	}	//	getCellEditorValue

	/**
	 *  VEditor Change Listener (property name is columnName).
	 *  - indicate change  (for String/Text/..) <br>
	 *  When editing is complete the value is retrieved via getCellEditorValue
	 *  @param e event
	 */
	public void vetoableChange(PropertyChangeEvent e)
	{
		if (m_table == null)
			return;
		log.fine(e.getPropertyName() + "=" + e.getNewValue());
		//
		((GridTable)m_table.getModel()).setChanged(true);
	}   //  vetoableChange

	/**
	 *  Get Actual Editor.
	 *  Called from GridController to add ActionListener to Button
	 *  @return VEditor
	 */
	public VEditor getEditor()
	{
		return m_editor;
	}   //  getEditor

	/**
	 *  Action Editor - Stop Editor
	 *  @param e event
	 */
	public void actionPerformed (ActionEvent e)
	{
		log.finer(m_mField.getColumnName() + ": Value=" + m_editor.getValue());
		if (e.getSource() == m_editor && actionListener != null)
			actionListener.actionPerformed(e);
	}   //  actionPerformed

	/**
	 * 	Dispose
	 */
	public void dispose()
	{
		m_editor = null;
		m_mField = null;
		m_table = null;
	}	//	dispose

	@Override
	public boolean stopCellEditing() {
		if (super.stopCellEditing()) {
			if (m_editor instanceof VLookup) {
				((VLookup)m_editor).setStopEditing(true);
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void cancelCellEditing() {
		super.cancelCellEditing();
		if (m_editor instanceof VLookup) {
			((VLookup)m_editor).setStopEditing(true);
		}
	}

	public void setActionListener(ActionListener listener) {
		actionListener = listener;
	}
}	//	VCellEditor
