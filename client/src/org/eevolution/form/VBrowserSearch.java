/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2003-2012 e-Evolution Consultants. All Rights Reserved.      *
 * Copyright (C) 2003-2012 Victor Pérez Juárez 								  * 
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
 * Contributor(s): Victor Pérez Juárez  (victor.perez@e-evolution.com)		  *
 * Sponsors: e-Evolution Consultants (http://www.e-evolution.com/)            *
 *****************************************************************************/

package org.eevolution.form;

import java.awt.BorderLayout;
import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JLabel;

import org.compiere.apps.ALayout;
import org.compiere.apps.ALayoutConstraint;
import org.compiere.grid.ed.VEditor;
import org.compiere.grid.ed.VEditorFactory;
import org.compiere.model.GridField;
import org.compiere.swing.CPanel;
import org.eevolution.grid.BrowserSearch;

/**
 * @author victor.perez@e-evolution.com , eEvolution Consultants
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * 		<li>BR [ 340 ] Smart Browse context is changed from table
 * 		@see https://github.com/adempiere/adempiere/issues/340
 * 		<li><a href="https://github.com/adempiere/adempiere/issues/556">
 * 		FR [ 556 ] Criteria Search on SB don't have a parameter like only information</a>
 * 
 */
public class VBrowserSearch extends BrowserSearch implements VetoableChangeListener, PropertyChangeListener {

	/**
	 *	Dynamic generated Parameter panel.
	 *  @param WindowNo window
	 *  @param p_Browser Browser controller
	 */
	public VBrowserSearch(int WindowNo, int p_AD_Browse_ID) {
		super(WindowNo, p_AD_Browse_ID);
	}	//	VBrowserSearch

	/**
	 * With Columns
	 * @param WindowNo
	 * @param p_Browser
	 * @param column
	 */
	public VBrowserSearch(int WindowNo, int p_AD_Browse_ID, int column) {
		super(WindowNo, p_AD_Browse_ID, column);
	}	//	VBrowserSearch
	
	//Layout Mode
	private int cols = 0;
	private int row = 0;
	//
	private ArrayList<VEditor>	m_vEditors;
	private ArrayList<VEditor>	m_vEditors_To;		//	for ranges
	private ArrayList<JLabel> 	m_separators;
	//
	private BorderLayout 	mainLayout;
	private ALayout			centerLayout;
	private CPanel 			centerPanel;
	private CPanel			mainPanel;

	/**
	 * Initialize components
	 */
	@Override
	public void initComponents() {
		if(mainPanel != null)
			return;
		//	
		mainLayout 		= new BorderLayout();
		centerPanel 	= new CPanel();
		mainPanel 		= new CPanel();
		//	Editors
		m_vEditors 		= new ArrayList<VEditor>();
		m_vEditors_To 	= new ArrayList<VEditor>();
		m_separators 	= new ArrayList<JLabel>();
		//	
		mainPanel.setLayout(mainLayout);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		//	
		centerLayout 	= new ALayout();
		centerPanel.setLayout(centerLayout);
	}

	/**
	 * Get Main Panel
	 * @return
	 */
	public CPanel getPanel() {
		return mainPanel;
	}
	
	/**
	 *  Dispose
	 */
	public void dispose() {
		super.dispose();
		m_vEditors.clear();
		m_vEditors_To.clear();
		m_separators.clear();
		mainPanel.removeAll();
	}   //  dispose

	@Override
	public void createViewField(GridField field, GridField field_To) {
		configColumns(field, field_To);
		//	For Standard
		if(field == null) {
			m_vEditors.add (null);
			return;
		}
		//  Create Field
		JLabel label = VEditorFactory.getLabel(field);
		if (label == null) {
			centerPanel.add(Box.createHorizontalStrut(12), new ALayoutConstraint(row, cols++));   	//	left gap
		} else {
			centerPanel.add(label, new ALayoutConstraint(row, cols++));
		}
		//	The Editor
		VEditor vEditor = VEditorFactory.getEditor(field, false);
		//	Set Default Value
		Object defaultObject = field.getDefault();
		vEditor.setValue(defaultObject);
		//	Add Event
		vEditor.addVetoableChangeListener(this);
		//  MField => VEditor - New Field value to be updated to editor
		field.addPropertyChangeListener(vEditor);
		field.addPropertyChangeListener(this);
		//
		centerPanel.add ((Component)vEditor, new ALayoutConstraint(row, cols++));
		m_vEditors.add (vEditor);                   
		//  add to Editors
		if(!field.isInfoOnly()) {
			setParameter(field.getColumnNameAlias(), vEditor);
		}
		//	Process new Value
		field.lookupLoadComplete();
		//	To
		if(field_To == null) {
			m_separators.add(null);
			m_vEditors_To.add(null);
			return;
		}
		//	Add
		JLabel dash = new JLabel(" - ");
		centerPanel.add (dash, new ALayoutConstraint(row, cols++));
		m_separators.add(dash);
		//	The Editor
		VEditor vEditor2 = VEditorFactory.getEditor(field_To, false);
		//  New Field value to be updated to editor
		field_To.addPropertyChangeListener(vEditor2);
		field_To.addPropertyChangeListener(this);

		//	Set Default Value
		Object defaultObject2 = field_To.getDefault();
		vEditor2.setValue(defaultObject2);
		vEditor2.addVetoableChangeListener(this);
		//
		centerPanel.add ((Component)vEditor2, new ALayoutConstraint(row, cols++));
		m_vEditors_To.add (vEditor2);
		if(!field.isInfoOnly()) {
			setParameter(field_To.getColumnNameAlias(), vEditor2);
		}
		//	Process new Value
		field_To.lookupLoadComplete();
	}
	
	/**
	 * Configure columns
	 * @param field
	 * @param field_To
	 */
	private void configColumns(GridField field, GridField field_To) {
		int maxToAdd = getColumns() * 2;
		int columnsToAdd = getColumns();
		//	for To field
		if(field_To != null) {
			columnsToAdd += 2;
		}
		//	Verify if is new row or not
		if((cols + columnsToAdd) > maxToAdd ) {
			cols = 0;
			row ++;
		}
	}

	/**
	 *	Editor Listener
	 *	@param evt Event
	 * 	@exception PropertyVetoException if the recipient wishes to roll back.
	 */
	public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
		GridField changedField = null;
		//	Set GridField
		if (evt.getSource() instanceof VEditor) {
			changedField = ((VEditor) evt.getSource()).getField();
		}
		else
			return;

		//  Multiple selection should not be enabled for criteria fields
		//  Sync the field with the editor
		//  Deal with new null values. Some editors return "" instead of null
		if ((evt.getNewValue() == null || evt.getNewValue().toString().isEmpty()) 
			&& evt.getOldValue() != null && evt.getOldValue().toString().length() > 0)
		{
			//  #283 Set value to null - veto if the field is mandatory
			if (!changedField.getVO().IsMandatory)
				changedField.setValue(null,false); //	-> PropertyChanged -> dynamicDisplay
			else
				throw new PropertyVetoException("FillMandatory", evt);
		}	
		else
		{
			// The new value is not null or an empty string - save if it is different than the 
			// old value or ignore the change
			if (evt.getNewValue() != null && !evt.getNewValue().equals(evt.getOldValue()))
				changedField.setValue(evt.getNewValue(),false);	//	-> PropertyChanged -> dynamicDisplay
		}	
	}	//	vetoableChange

	@Override
	public void dynamicDisplay() {
		Component[] comps = centerPanel.getComponents();
		for (int i = 0; i < comps.length; i++) {
			Component comp = comps[i];
			// BF3423098 - Labels for process parameters with display logic false are still shown 
			//if (comp instanceof CLabel)
			//	continue;
			String columnName = comp.getName();

			if (columnName != null && columnName.length() > 0) {
				int index = getIndex(columnName);
				GridField field = getField(index);
				if (field != null) {
					if (field.isDisplayed(true)) { // check
						// context
						if (!comp.isVisible()) {
							comp.setVisible(true); // visibility
							if (field.isRange())
								m_separators.get(index).setText(" - ");
								comp.setVisible(true);
						}
						Object value = field.getValue();
						Object defaultValue = field.getDefault();
						if ((value == null || value.toString().length() == 0)
								&& defaultValue != null) {
							field.setValue(defaultValue, true);
							m_vEditors.get(index).setValue(defaultValue);
						}
						boolean rw = field.isEditablePara(true); // r/w - check if field is Editable
						m_vEditors.get(index).setReadWrite(rw);

						if (field.isRange()) {
							m_vEditors_To.get(index).setReadWrite(rw);
							GridField gridFieldTo = getField_To(index);
							Object valueTo = gridFieldTo.getValue();
							Object defaultValueTo = gridFieldTo.getDefault();
							if ((valueTo == null || valueTo.toString().length() == 0)
									&& defaultValueTo != null) {
								gridFieldTo.setValue(defaultValueTo, true);
								m_vEditors_To.get(index).setValue(defaultValueTo);
							}
							rw = gridFieldTo.isEditablePara(true);
							m_vEditors_To.get(index).setReadWrite(rw);
						}
					} else {
						if (comp.isVisible()) {
							comp.setVisible(false);
							if (field.isRange()) {
								m_separators.get(index).setText("");
								m_vEditors_To.get(i).setVisible(false);
							}
						}
					}
				}					
			}
		}
	} // Dynamic Display.
	
	@Override
	public void refreshContext() {
		for(int i = 0; i < m_vEditors.size(); i++) {
			VEditor editor = m_vEditors.get(i);
			GridField field = editor.getField();
			Object value = field.getValue();
			Object defaultValue = field.getDefault();
			if ((value == null || value.toString().length() == 0)
					&& defaultValue != null) {
				m_vEditors.get(i).setValue(defaultValue);
				field.setValue(defaultValue, true);
			}
			boolean rw = field.isEditablePara(true); // r/w - check if field is Editable
			m_vEditors.get(i).setReadWrite(rw);
		}
 	}

	@Override
	public String getDisplay(int index) {
		VEditor editor = m_vEditors.get(index);
		if(editor != null)
			return editor.getDisplay();
		//	Default
		return null;
	}
	
	@Override
	public String getDisplay_To(int index) {
		VEditor editor = m_vEditors_To.get(index);
		if(editor != null)
			return editor.getDisplay();
		//	Default
		return null;
	}

	@Override
	public Object getValue(int index) {
		VEditor editor = m_vEditors.get(index);
		if(editor != null)
			return editor.getValue();
		//	Default
		return null;
	}

	@Override
	public Object getValue_To(int index) {
		VEditor editor = m_vEditors_To.get(index);
		if(editor != null)
			return editor.getValue();
		//	Default
		return null;
	}

	@Override
	public void setValue(int index, Object value) {
		VEditor editor = m_vEditors.get(index);
		if(editor != null)
			editor.setValue(value);
	}

	@Override
	public void setValue_To(int index, Object value) {
		VEditor editor = m_vEditors_To.get(index);
		if(editor != null)
			editor.setValue(value);
	}
	
	/**
	 * get Parameter Value
	 * 
	 * @param id
	 * @return Object Value
	 */
	public Object getParamenterValue(Object key) {
		VEditor editor = (VEditor) getParameters().get(key);
		if (editor != null)
			return editor.getValue();
		else
			return null;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		GridField changedField = null;
		//	Set GridField
		String propertyName = evt.getPropertyName();
		if (evt.getSource() instanceof GridField) {
			changedField = ((GridField) evt.getSource());
			propertyName = changedField.getColumnNameAlias();
		}
		else
			return;
		
		//	Change Dependents
		fieldChange(changedField, evt.getNewValue(), propertyName);
	}
}
