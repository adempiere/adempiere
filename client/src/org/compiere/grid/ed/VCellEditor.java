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

package org.compiere.grid.ed;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.VetoableChangeListener;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.plaf.ComponentUI;
import javax.swing.table.TableCellEditor;

import org.adempiere.plaf.AdempiereEditorAbstractUI;
import org.adempiere.plaf.AdempierePLAF;
import org.compiere.grid.VTable;
import org.compiere.model.GridField;
import org.compiere.model.GridTable;
import org.compiere.swing.CComboBox;
import org.compiere.util.CLogger;

import sun.swing.DefaultLookup;

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
 *  @author Michael McKay, mckayERP@gmail.com
 *  	<li><a href="https://github.com/adempiere/adempiere/issues/2908">#2908</a>Updates to ADempiere Look and Feel
 *  @version 	3.9.4
 */
public final class VCellEditor extends AbstractCellEditor
	implements TableCellEditor, VetoableChangeListener, ActionListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1406944397509282968L;
	private int displayType;
	private Class<?> editorClass;

	/**
	 *	Constructor for Grid
	 *  @param mField
	 */
	public VCellEditor (GridField mField)
	{
		super();
		m_mField = mField;
		this.displayType = mField.getDisplayType();
		createEditor();
	}	//	VCellEditor

	/**
	 *	Constructor for Grid
	 *  @param Class the class to use. Ignored is the 
	 *  display type is > 0
	 *  @param displayType the displayType to use
	 */
	public VCellEditor (Class<?> c, int displayType)
	{
		super();
		m_mField = null;
		this.displayType = displayType;	
		this.editorClass = c;
		createEditor();
		
	}	//	VCellEditor

	/** The Field               */
	private GridField          m_mField = null;
	/** The Table Editor        */
	private VEditor	        m_editor = null;
	private JComponent		editorComponent = null;
	
	/** Table                   */
	private JTable          table = null;
	private ActionListener actionListener;
	private int currentRow;
	private int currentColumn;
	
	/** ClickCount              */
	private static int      CLICK_TO_START = 1;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(VCellEditor.class);

    /** The look and feel delegate for the editor component. */
    protected transient ComponentUI ui;
    
    
	private static void setBorder(JComponent c, Border border) {
		
		if (c instanceof VEditorAbstract && ((VEditorAbstract) c).getUI() instanceof AdempiereEditorAbstractUI)
		{
			AdempiereEditorAbstractUI ui = (AdempiereEditorAbstractUI) ((VEditorAbstract) c).getUI();
			ui.setBorder(border);
		}
		else
		{
			c.setBorder(border);
		}
	}
	
	public static JPanel getBorderPanel(JComponent c) {
		
		if (c instanceof VEditorAbstract && ((VEditorAbstract) c).getUI() instanceof AdempiereEditorAbstractUI)
		{
			AdempiereEditorAbstractUI ui = (AdempiereEditorAbstractUI) ((VEditorAbstract) c).getUI();
			return ui.getBorderPanel();
		}
		else
		{
			return null;
		}
	}


	/**
	 *  Create Editor
	 */
	private void createEditor()
	{
		if (m_editor != null)
			return;
		
		// Create the editor as a table cell editor
		if (m_mField != null)
			m_editor = VEditorFactory.getEditor(m_mField, true);
		else 
			m_editor = VEditorFactory.getEditor(editorClass, displayType, true);
		
		if (m_editor == null)
		{
			log.severe("Not enough info to define an editor!");
			return;
		}
		m_editor.addVetoableChangeListener(this);
		m_editor.addActionListener(this);
		if (table instanceof VTable)
		{
			if (((VTable) table).getGridController() != null)
				m_editor.addVetoableChangeListener(((VTable) table).getGridController());
		}
		
		editorComponent = m_editor.getComponent();
		if (editorComponent instanceof CComboBox)
		{
			((JComponent) editorComponent).putClientProperty("JComboBox.isTableCellEditor", true);
			((CComboBox) m_editor.getComponent()).init();
		}
		
		// Remove the default enter behaviour to allow the table action (move down) to happen
		editorComponent.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "none");
				
				
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
		//	not enough mouse clicks
		if (anEvent instanceof MouseEvent 
			&& ((MouseEvent)anEvent).getClickCount() < CLICK_TO_START)
			return false;
		
		if (m_mField != null)
		{
			if (!m_mField.isEditable (true, true))	//	row data is in context
				return false;
			log.fine(m_mField.getHeader());		//	event may be null if CellEdit
		}
			
		if (m_editor == null)
			createEditor();
		
		return m_editor.isReadWrite();
		
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
		this.table = table;
		currentRow = row; 
		currentColumn = col;
				
		createEditor();
		
		log.info(m_editor.getName() + ": Value=" + value + ", row=" + row + ", col=" + col);

		if ( m_editor instanceof VLookup) 
		{
			// May have been set to true the previous time
			// this column was edited.
			((VLookup) m_editor).stopEditing(false, false);
			
			ui = ((JPanel) m_editor).getUI();
		}
		else if (m_editor instanceof VEditorAbstract)
		{
			ui = ((VEditorAbstract) m_editor).getUI();
		}

		
		if (ui instanceof AdempiereEditorAbstractUI)
		{
			((AdempiereEditorAbstractUI) ui).setHasFocus(true);
		}
		
		if (m_mField != null) // Otherwise, leave it as set.
			m_editor.setReadWrite(m_mField.isEditable (true, false));
		
		//  When the gridField is null, there is no controller logic to 
		//  manage mandatory fields.  In this case, set the editor flag
		//  to manage the background color.  
		if (m_mField == null && table instanceof VTable && ((VTable) table).isCellMandatory(row, col))
			m_editor.setMandatory(true);
			
		//	Set Value
		m_editor.setValue(value);
		

		//	Set Background/Foreground to "normal" (unselected) colors
		m_editor.setBackground(m_mField == null ? false : m_mField.isError());

		//  Override the UI to use the same font as the table.
		m_editor.setFont(table.getFont());
		
		// Update the checkbox look and feel
		if (m_editor instanceof JCheckBox)
		{
			((JCheckBox) m_editor).setBackground(AdempierePLAF.getFieldBackground_Normal());
			((JCheckBox) m_editor).setOpaque(true);
			((JCheckBox) m_editor).setBorderPainted(true);
		}

		// The editor is always in focus so set the border as focused.
        Border border = UIManager.getBorder("Table.focusSelectedCellHighlightBorder");
        if (border == null) {	
            border = DefaultLookup.getBorder((JComponent) m_editor, ui, "Table.focusCellHighlightBorder");
        }

		setBorder((JComponent) m_editor, border);
		
		return (Component) m_editor;
	}	//	getTableCellEditorComponent

	/**
	 *	The editing cell should be selected or not
	 *  @param e event
	 *  @return true (constant)
	 */
	public boolean shouldSelectCell(EventObject e)
	{
		log.finest(m_mField == null ? e.toString() : m_mField.getColumnName() + " " + e);
		return true;
	}	//	shouldSelectCell

	/**
	 *	Returns the value contained in the editor
	 *  @return value
	 */
	public Object getCellEditorValue()
	{
//		log.finest(m_mField.getColumnName() + ": " + m_editor.getValue());
		log.info((m_mField == null ? "" : m_mField.getColumnName()) + " (row/col): (" + currentRow +"/"+currentColumn+ ") "+ m_editor.getValue());
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
		if (table == null)
			return;
		log.fine(e.getPropertyName() + "=" + e.getNewValue());
		//
		if (table.getModel() instanceof GridTable)
			((GridTable)table.getModel()).setChanged(true);
		
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
		log.finer((m_mField == null ? "" : m_mField.getColumnName()) + ": Value=" + m_editor.getValue());
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
		table = null;
	}	//	dispose

	@Override
	public boolean stopCellEditing() {
		
		//  Have to bind data values here as the stop editing event can occur before the
		//  focus lost event
		boolean canStop = true;
		
		if (m_editor instanceof VLookup) {
			canStop = ((VLookup)m_editor).stopEditing(true, true);
		}
		if (m_editor instanceof VEditorAbstract) {
			canStop = ((VEditorAbstract)m_editor).stopEditing(true, true);
			if (!((VEditorAbstract) m_editor).isCurrentValueValid())
			{
				// Flag the error
				m_editor.setBackground(true);
			}
		}

		if (canStop)
		{
			log.info("Stopping editing for cell row/col " + currentRow + "/" + currentColumn + " " + getCellEditorValue());		
			return super.stopCellEditing();
		}
		else
		{
			log.info("Can't stop editing for cell row/col " + currentRow + "/" + currentColumn + " " + getCellEditorValue());		
			return false;
		}
	}

	@Override
	public void cancelCellEditing() {
		super.cancelCellEditing();
		if (m_editor instanceof VLookup) {
			((VLookup)m_editor).stopEditing(true, false);
		}
	}

	public void setActionListener(ActionListener listener) {
		actionListener = listener;
	}
	
}	//	VCellEditor
