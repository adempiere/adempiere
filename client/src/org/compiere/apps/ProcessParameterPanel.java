/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 Adempiere, Inc. All Rights Reserved.               *
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
package org.compiere.apps;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.util.ArrayList;

import javax.swing.Box;

import org.compiere.grid.ed.VEditor;
import org.compiere.grid.ed.VEditorFactory;
import org.compiere.model.GridField;
import org.compiere.process.ProcessInfo;
import org.compiere.swing.CLabel;
import org.compiere.swing.CPanel;
import org.compiere.util.CLogger;

/**
 *	Process Parameter Panel, based on existing ProcessParameter dialog.
 *	- Embedded in ProcessDialog
 *	- checks, if parameters exist and inquires and saves them
 *
 * @author Low Heng Sin
 * @author Juan David Arboleda (arboleda), GlobalQSS, [ 1795398 ] Process
 *         Parameter: add display and readonly logic
 * @author Teo Sarca, www.arhipac.ro
 * 			<li>BF [ 2548216 ] Process Param Panel is not showing any parameter if error 
 * @author victor.perez@e-evoluton.com, www.e-evolution.com 
 * 			<li>FR [ 3426137 ] Smart Browser
 * 			 https://sourceforge.net/tracker/?func=detail&aid=3426137&group_id=176962&atid=879335
 * 			<li> https://adempiere.atlassian.net/browse/ADEMPIERE-97
 * 			<li> The parameters is not show when use display logic, the parameters dialog window is not automatically resize.
 * @version 	2006-12-01
 * @author Michael McKay (mjmckay)
 * 			<li>BF3423098 - Labels for process parameters with display logic false are still displayed
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *			<li>FR [ 265 ] ProcessParameterPanel is not MVC
 *			@see https://github.com/adempiere/adempiere/issues/265
 *			<li>FR [ 349 ] GridFieldVO attribute is ambiguous
 * 			@see https://github.com/adempiere/adempiere/issues/349
 * 			<li>FR [ 298 ] Process Parameter Panel not set default value correctly into parameters
 *			@see https://github.com/adempiere/adempiere/issues/298
 */
public class ProcessParameterPanel extends ProcessParameter implements VetoableChangeListener, PropertyChangeListener {

	/**
	 *	Dynamic generated Parameter panel.
	 *  @param WindowNo window
	 *  @param pi process info
	 */
	public ProcessParameterPanel(int WindowNo, ProcessInfo pi) {
		super(WindowNo, pi);
	}	//	ProcessParameterPanel

	/**
	 * With Columns
	 * @param WindowNo
	 * @param pi
	 * @param column
	 */
	public ProcessParameterPanel(int WindowNo, ProcessInfo pi, int column) {
		super(WindowNo, pi, column);
	}	//	ProcessParameterPanel
	
	//Layout Mode
	private int cols = 0;
	private int row = 0;
	//
	private ArrayList<VEditor>	m_vEditors;
	private ArrayList<VEditor>	m_vEditors_To;		//	for ranges
	private ArrayList<CLabel> 	m_separators;
	//
	private BorderLayout 	mainLayout;
	private CPanel 			centerPanel;
	//	Main Panel
	private CPanel			mainPanel;
	private int 			labelMinWidth = 0;
	private int 			fieldMinWidth = 0;
	private final int		SEPARATOR = 25;
	
	private CLogger log	= CLogger.getCLogger(this.getClass());

	@Override
	public void initComponents() {
		mainLayout 		= new BorderLayout();
		centerPanel 	= new CPanel();
		mainPanel 		= new CPanel();
		//	Editors
		m_vEditors 		= new ArrayList<VEditor>();
		m_vEditors_To 	= new ArrayList<VEditor>();
		m_separators 	= new ArrayList<CLabel>();
		//	
		mainPanel.setLayout(mainLayout);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		//	
		centerPanel.setLayout(new ALayout());
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
		CLabel label = VEditorFactory.getLabel(field);
		if (label == null) {
			centerPanel.add(Box.createHorizontalStrut(12), new ALayoutConstraint(row, cols++));   	//	left gap
		} else {
			int currentWidth = label.getPreferredSize().width;
			labelMinWidth = currentWidth > labelMinWidth ? currentWidth : labelMinWidth;
//			label.setSize(label.getPreferredSize());
//			label.setMinimumSize(label.getPreferredSize());
//			label.putClientProperty("LabelUI.truncationString", ">");
			centerPanel.add(label, new ALayoutConstraint(row, cols++));
		}
		//	The Editor
		VEditor vEditor = VEditorFactory.getEditor(field, false);
		//	Set Default Value
		Object defaultObject = field.getDefault();
		vEditor.setValue(defaultObject);
		//	Add Event on the change of the field - sets the field value.
		vEditor.addVetoableChangeListener(this);
		//  MField => VEditor - New Field value to be updated to editor
		field.addPropertyChangeListener(vEditor);
		//  Listen to field property changes to allow sync of the other
		//  fields
		field.addPropertyChangeListener(this);
		//	
		Component component = (Component) vEditor;
		fieldMinWidth = component.getPreferredSize().width > fieldMinWidth 
				? component.getPreferredSize().width : fieldMinWidth;
		centerPanel.add ((Component)vEditor, new ALayoutConstraint(row, cols++));
		m_vEditors.add (vEditor);                   //  add to Editors
		//	To
		if(field_To == null) {
			m_separators.add(null);
			m_vEditors_To.add(null);
			return;
		}
		//	Add
		CLabel dash = new CLabel(" - ");
		centerPanel.add (dash, new ALayoutConstraint(row, cols++));
		m_separators.add(dash);
		//	The Editor
		VEditor vEditor2 = VEditorFactory.getEditor(field_To, false);
		//  New Field value to be updated to editor
		field_To.addPropertyChangeListener(vEditor2);
		//	Set Default Value
		Object defaultObject2 = field_To.getDefault();
		vEditor2.setValue(defaultObject2);
		vEditor2.addVetoableChangeListener(this);
		//
		centerPanel.add ((Component)vEditor2, new ALayoutConstraint(row, cols++));
		m_vEditors_To.add (vEditor2);
	}
	
	/**
	 * Congure columns
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
		if((cols + columnsToAdd) > maxToAdd) {
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
		
		log.fine(evt.getPropertyName() + "=" + evt.getNewValue() + " (" + evt.getOldValue() + ") "
			+ (evt.getOldValue() == null ? "" : evt.getOldValue().getClass().getName()));

		GridField changedField = null;
		//	Set GridField
		if (evt.getSource() instanceof VEditor) {
			changedField = ((VEditor) evt.getSource()).getField();
		}
		else
			return;

		//  Multiple selection should not be enabled

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
		//	
		centerPanel.setPreferredSize(new Dimension(labelMinWidth + fieldMinWidth + SEPARATOR, (row + 1) * SEPARATOR)); // Row height
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
							//	FR [ 349 ]
							if (field.isRange())
								m_separators.get(index).setText(" - ");
						}
						
						// Don't deal with field values here - use the vetoable change listener
//						Object value = field.getValue();
//						Object defaultValue = field.getDefault();
//						if ((value == null || value.toString().length() == 0)
//								&& defaultValue != null) {
//							field.setValue(defaultValue, false);  // Not inserting - overwriting the current value
//							// m_vEditors.get(index).setValue(defaultValue); // Not required, happens through the property listener
//						}
						boolean rw = field.isEditablePara(true); // r/w - check if field is Editable
						m_vEditors.get(index).setReadWrite(rw);

						if (field.isRange()) {
							m_vEditors_To.get(index).setReadWrite(rw);
							GridField gridFieldTo = m_vEditors_To.get(index).getField();
//							Object valueTo = gridFieldTo.getValue();
//							Object defaultValueTo = gridFieldTo.getDefault();
//							if ((valueTo == null || valueTo.toString().length() == 0)
//									&& defaultValueTo != null) {
//								//	BR [ 298 ]
//								gridFieldTo.setValue(defaultValueTo, true);
//								m_vEditors_To.get(index).setValue(defaultValueTo);
//							}
							rw = gridFieldTo.isEditablePara(true);
							m_vEditors_To.get(index).setReadWrite(rw);
						}
					} else {
						if (comp.isVisible()) {
							comp.setVisible(false);
							if (field.isRange())
								m_separators.get(index).setText("");
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
	public String saveParameters() {
		String validError = super.saveParameters();
		if(validError != null) {
			ADialog.error(getWindowNo(), getPanel(), "FillMandatory", validError);
		}
		//	
		return validError;
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

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		GridField changedField = null;
		//	Set GridField
		if (evt.getSource() instanceof GridField) {
			changedField = ((GridField) evt.getSource());
		}
		else
			return;
		
		//	Change Dependents
		fieldChange(changedField, evt.getNewValue(), evt.getPropertyName());
		
	}
		
}	//	ProcessParameterPanel
