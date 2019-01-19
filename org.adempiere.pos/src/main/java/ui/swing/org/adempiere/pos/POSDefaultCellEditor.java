/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2014 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Raul Muñoz www.erpcya.com					              *
 *****************************************************************************/

package org.adempiere.pos;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.TreeCellEditor;

/**
 * @author Mario Calderon, mario.calderon@westfalia-it.com, Systemhaus Westfalia, http://www.westfalia-it.com
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 */
public class POSDefaultCellEditor extends AbstractCellEditor implements TableCellEditor, TreeCellEditor {
	
	/**
	 * 
	 * *** Constructor ***
	 * @param textField
	 */
	public POSDefaultCellEditor(final JButton textField) {
		  editorComponent = textField;
	        this.clickCountToStart = 2;
	        delegate = new EditorDelegate() {
	            public void setValue(Object value) {
	                textField.setText((value != null) ? value.toString() : "");
	            }

	            public Object getCellEditorValue() {
	                return textField.getText();
	            }
	        };
	        textField.addActionListener(delegate);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 6870214767566702838L;

    /** The Swing component being edited. */
    protected JComponent editorComponent;
    /**
     * The delegate class which handles all methods sent from the
     * <code>CellEditor</code>.
     */
    protected EditorDelegate delegate;
    /**
     * An integer specifying the number of clicks needed to start editing.
     * Even if <code>clickCountToStart</code> is defined as zero, it
     * will not initiate until a click occurs.
     */
    protected int clickCountToStart = 1;

    
	/**
	 * 
	 */

	@Override
	public Object getCellEditorValue() {
		// TODO Auto-generated method stub
        return delegate.getCellEditorValue();
	}

	@Override
	public boolean isCellEditable(EventObject anEvent) {
		// TODO Auto-generated method stub
		 return delegate.isCellEditable(anEvent);
	}

	@Override
	public boolean shouldSelectCell(EventObject anEvent) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean stopCellEditing() {
		// TODO Auto-generated method stub
        return delegate.stopCellEditing();
	}

	@Override
	public void cancelCellEditing() {
		// TODO Auto-generated method stub
		fireEditingCanceled();
	}

	

	

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		 delegate.setValue(value);
	        if (editorComponent instanceof JButton) {
	            //in order to avoid a "flashing" effect when clicking a checkbox
	            //in a table, it is important for the editor to have as a border
	            //the same border that the renderer has, and have as the background
	            //the same color as the renderer has. This is primarily only
	            //needed for JCheckBox since this editor doesn't fill all the
	            //visual space of the table cell, unlike a text field.
	            TableCellRenderer renderer = table.getCellRenderer(row, column);
	            Component c = renderer.getTableCellRendererComponent(table, value,
	                    isSelected, true, row, column);
	            if (c != null) {
	                editorComponent.setOpaque(true);
	                editorComponent.setBackground(c.getBackground());
	                if (c instanceof JComponent) {
	                    editorComponent.setBorder(((JComponent)c).getBorder());
	                }
	            } else {
	                editorComponent.setOpaque(false);
	            }
	        }
	        return editorComponent;
	}
	@Override
	public Component getTreeCellEditorComponent(JTree tree, Object value,
			boolean isSelected, boolean expanded, boolean leaf, int row) {
		String         stringValue = tree.convertValueToText(value, isSelected,
                expanded, leaf, row, false);
		// TODO Auto-generated method stub
		  delegate.setValue(stringValue);
	        return editorComponent;
	}
	

	/**
     * The protected <code>EditorDelegate</code> class.
     */
    protected class EditorDelegate implements ActionListener, ItemListener, Serializable {

        /**  The value of this cell. */
        protected Object value;

       /**
        * Returns the value of this cell.
        * @return the value of this cell
        */
        public Object getCellEditorValue() {
            return value;
        }

       /**
        * Sets the value of this cell.
        * @param value the new value of this cell
        */
        public void setValue(Object value) {
            this.value = value;
        }

       /**
        * Returns true if <code>anEvent</code> is <b>not</b> a
        * <code>MouseEvent</code>.  Otherwise, it returns true
        * if the necessary number of clicks have occurred, and
        * returns false otherwise.
        *
        * @param   anEvent         the event
        * @return  true  if cell is ready for editing, false otherwise
        * @see #setClickCountToStart
        * @see #shouldSelectCell
        */
        public boolean isCellEditable(EventObject anEvent) {
            if (anEvent instanceof MouseEvent) {
                return ((MouseEvent)anEvent).getClickCount() >= clickCountToStart;
            }
            return true;
        }

       /**
        * Returns true to indicate that the editing cell may
        * be selected.
        *
        * @param   anEvent         the event
        * @return  true
        * @see #isCellEditable
        */
        public boolean shouldSelectCell(EventObject anEvent) {
            return true;
        }

       /**
        * Returns true to indicate that editing has begun.
        *
        * @param anEvent          the event
        */
        public boolean startCellEditing(EventObject anEvent) {
            return true;
        }

       /**
        * Stops editing and
        * returns true to indicate that editing has stopped.
        * This method calls <code>fireEditingStopped</code>.
        *
        * @return  true
        */
        public boolean stopCellEditing() {
            fireEditingStopped();
            return true;
        }

       /**
        * Cancels editing.  This method calls <code>fireEditingCanceled</code>.
        */
       public void cancelCellEditing() {
           fireEditingCanceled();
       }

       /**
        * When an action is performed, editing is ended.
        * @param e the action event
        * @see #stopCellEditing
        */
        public void actionPerformed(ActionEvent e) {
            POSDefaultCellEditor.this.stopCellEditing();
        }

       /**
        * When an item's state changes, editing is ended.
        * @param e the action event
        * @see #stopCellEditing
        */
        public void itemStateChanged(ItemEvent e) {
            POSDefaultCellEditor.this.stopCellEditing();
        }
    }

}
