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

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.adempiere.webui.component.Column;
import org.adempiere.webui.component.Columns;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.editor.WEditor;
import org.adempiere.webui.editor.WEditorPopupMenu;
import org.adempiere.webui.editor.WebEditorFactory;
import org.adempiere.webui.event.ContextMenuListener;
import org.adempiere.webui.event.ValueChangeEvent;
import org.adempiere.webui.event.ValueChangeListener;
import org.compiere.model.GridField;
import org.eevolution.grid.BrowserSearch;
import org.zkoss.zul.Div;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Row;

/**
 * @author victor.perez@e-evolution.com , eEvolution Consultants
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * 		<li>BR [ 340 ] Smart Browse context is changed from table
 * 		@see https://github.com/adempiere/adempiere/issues/340
 */
public class WBrowserSearch extends BrowserSearch implements ValueChangeListener {
	
	/**
	 *	Dynamic generated Parameter panel.
	 *  @param WindowNo window
	 *  @param p_AD_Browse_ID Smart Browse ID
	 */
	public WBrowserSearch(int WindowNo, int p_AD_Browse_ID) {
		this(WindowNo, p_AD_Browse_ID, COLUMNS_1);
		initComponents();
	}	//	WBrowserSearch
	
	/**
	 * With Columns
	 * @param WindowNo
	 * @param p_AD_Browse_ID
	 * @param columns
	 */
	public WBrowserSearch(int WindowNo, int p_AD_Browse_ID, int columns) {
		super(WindowNo, p_AD_Browse_ID, columns);
		initComponents();
	}
	
	/** Parameters */
	private LinkedHashMap<Object, Object> m_search = new LinkedHashMap<Object, Object>();
	private String width;

	/**
	 * Initialize components
	 */
	@Override
	public void initComponents() {
		if(mainPanel != null)
			return;
		//	
		m_wEditors = new ArrayList<WEditor>();
		m_wEditors_To = new ArrayList<WEditor>();
		m_separators = new ArrayList<Label>();
		rows = new Rows();
		//
		mainPanel = new Panel();
		centerPanel = GridFactory.newGridLayout();
		centerPanel.setInnerWidth(width);
		mainPanel.appendChild(centerPanel);
		
		//setup columns
    	Columns columns = new Columns();
    	centerPanel.appendChild(columns);
    	int colN = getColumns() * 2;
    	if(colN != 0) {
    		int percent = 100 / colN;
    		for(int i = 0; i < colN; i++) {
    			Column col = new Column();
	        	col.setWidth((i == 0
	        			? ((int) percent / 2)
	        			: percent) + "%");
	        	columns.appendChild(col);
	    	}
    	}
    	//	Add Rows
    	centerPanel.appendChild(rows);
	}
	
	/**
	 * Get Main Panel
	 * @return
	 */
	public Panel getPanel() {
		return mainPanel;
	}

	//Layout Mode
	private int cols = 0;
	//
	private ArrayList<WEditor>	m_wEditors;
	private ArrayList<WEditor>	m_wEditors_To;		//	for ranges
	private ArrayList<Label> 	m_separators;
	/**	Rows for Parameters	*/
	private Rows 	rows;
	private Row		currentRow;
	//
	private Grid 	centerPanel;
	private Panel	mainPanel;

	/**
	 *  Dispose
	 */
	public void dispose() {
		super.dispose();
		m_wEditors.clear();
		m_wEditors_To.clear();
	}   //  dispose
	
	@Override
	public void createViewField(GridField field, GridField field_To) {
		//	
		configColumns(field, field_To);
		WEditorPopupMenu popupMenu;
		//	
		if(field == null) {
			m_wEditors.add (null);
			return;
		}
		//	The Editor
		WEditor editor = WebEditorFactory.getEditor(field, false);
		editor.addValueChangeListener(this);
		editor.dynamicDisplay();
		//  MField => VEditor - New Field value to be updated to editor
		field.addPropertyChangeListener(editor);
		//	Set Default Value
		Object defaultObject = field.getDefault();
		editor.setValue(defaultObject);
		//streach component to fill grid cell
		editor.fillHorizontal();
        //setup editor context menu
        popupMenu = editor.getPopupMenu();                    
        if (popupMenu != null) {
        	popupMenu.addMenuListener((ContextMenuListener)editor);
            mainPanel.appendChild(popupMenu);
        }
        setParameter(field.getColumnNameAlias(), editor);
		//
        m_wEditors.add (editor);                   //  add to Editors
		
    	Div div = new Div();
        div.setAlign("right");
        
        Label label = editor.getLabel();
        div.appendChild(label);
        if (label.getDecorator() != null)
        	div.appendChild(label.getDecorator());
        //	
        currentRow.appendChild(div);
        //	Add Child
        cols += 2;
		//	
        Hbox box;
        if(field_To != null) {
        	box = new Hbox();
        	box.appendChild(editor.getComponent());
        } else {
            currentRow.appendChild(editor.getComponent());
        	m_separators.add(null);
        	m_wEditors_To.add(null);
        	return;
        }
		//	The Editor
		WEditor editor2 = WebEditorFactory.getEditor(field_To, false);
		//  New Field value to be updated to editor
		field_To.addPropertyChangeListener(editor2);
		editor2.dynamicDisplay();
		//	
		Object defaultObject2 = field_To.getDefault();
		editor2.setValue(defaultObject2);
		//	
		editor2.fillHorizontal();
		//setup editor context menu
        popupMenu = editor2.getPopupMenu();                    
        if (popupMenu != null) {
        	popupMenu.addMenuListener((ContextMenuListener)editor2);
            mainPanel.appendChild(popupMenu);
        }
        //
		m_wEditors_To.add (editor2);
		Label separator = new Label(" - ");
		m_separators.add(separator);
		box.appendChild(separator);
		box.appendChild(editor2.getComponent());
		setParameter(field_To.getColumnNameAlias(), editor2);
        //	Add
        currentRow.appendChild(box);
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
		//	Verify if is new row or not
		if((cols + columnsToAdd) > maxToAdd
				|| currentRow == null) {
			cols = 0;
			currentRow = new Row();
			rows.appendChild(currentRow);
		}
	}

	@Override
	public void refreshContext() {
		for(int i = 0; i < m_wEditors.size(); i++) {
			WEditor editor = m_wEditors.get(i);
			GridField field = editor.getGridField();
			Object value = field.getValue();
			Object defaultValue = field.getDefault();
			if ((value == null || value.toString().length() == 0)
					&& defaultValue != null) {
				m_wEditors.get(i).setValue(defaultValue);
				field.setValue(defaultValue, true);
			}
			boolean rw = field.isEditablePara(true); // r/w - check if field is Editable
			m_wEditors.get(i).setReadWrite(rw);
		}
 	}

	@Override
	public String getDisplay(int index) {
		WEditor editor = m_wEditors.get(index);
		if(editor != null)
			return editor.getDisplay();
		//	Default
		return null;
	}
	
	@Override
	public String getDisplay_To(int index) {
		WEditor editor = m_wEditors_To.get(index);
		if(editor != null)
			return editor.getDisplay();
		//	Default
		return null;
	}

	/**
	 *	Editor Listener
	 *	@param evt ValueChangeEvent	 
	 */
	public void valueChange(ValueChangeEvent evt) {
		GridField changedField = null;
		String propertyName = evt.getPropertyName();
		//	Set GridField
		if (evt.getSource() instanceof WEditor) {
			changedField = ((WEditor) evt.getSource()).getGridField();
			propertyName = changedField.getColumnNameAlias();
		}
		//	Change Dependents
		fieldChange(changedField, evt.getNewValue(), propertyName);
	}
	
	/**
	 * Dynamic Display
	 */
	public void dynamicDisplay() {
		for(int i = 0; i < m_wEditors.size(); i++) {
			WEditor editor = m_wEditors.get(i);
			GridField mField = editor.getGridField();
			if (mField.isDisplayed(true)) {
				if (!editor.isVisible()) {
					editor.setVisible(true);
					if (mField.isRange()) {
						m_separators.get(i).setVisible(true);
						m_wEditors_To.get(i).setVisible(true);
					}
				}

				Object value = mField.getValue();
				Object defaultValue = mField.getDefault();
				if ((value == null || value.toString().length() == 0)
						&& defaultValue != null) {
					mField.setValue(defaultValue, true);
					editor.setValue(defaultValue);
				}
				boolean rw = mField.isEditablePara(true); // r/w - check if field is Editable
				editor.setReadWrite(rw);
				editor.dynamicDisplay();
				//	FR [ 349 ]
				if (mField.isRange()) {
					WEditor editorTo = m_wEditors_To.get(i);
					GridField gridFieldTo = editorTo.getGridField();
					Object valueTo = gridFieldTo.getValue();
					Object defaultValueTo = gridFieldTo.getDefault();
					if ((valueTo == null || valueTo.toString().length() == 0)
							&& defaultValueTo != null) {
						gridFieldTo.setValue(defaultValueTo, true);
						editorTo.setValue(defaultValueTo);
					}
					rw = gridFieldTo.isEditablePara(true); // r/w - check if field is Editable
					editorTo.setReadWrite(rw);
					editorTo.dynamicDisplay();
				}
			} else if (editor.isVisible()) {
				editor.setVisible(false);
				if (mField.isRange()) {
					m_separators.get(i).setVisible(false);
					m_wEditors_To.get(i).setVisible(false);
				}
			}
		}			
	}
	
	@Override
	public Object getValue(int index) {
		WEditor editor = m_wEditors.get(index);
		if(editor != null)
			return editor.getValue();
		//	Default
		return null;
	}

	@Override
	public Object getValue_To(int index) {
		WEditor editor = m_wEditors_To.get(index);
		if(editor != null)
			return editor.getValue();
		//	Default
		return null;
	}

	@Override
	public void setValue(int index, Object value) {
		WEditor editor = m_wEditors.get(index);
		if(editor != null)
			editor.setValue(value);
	}

	@Override
	public void setValue_To(int index, Object value) {
		WEditor editor = m_wEditors_To.get(index);
		if(editor != null)
			editor.setValue(value);
	}
	
	/**
	 * Set Parameter
	 * @param name
	 * @param value
	 */
	public void setParameter(Object name, Object value) {
		if (value != null) {
			m_search.put(name, value);
		}
	}

	/**
	 * Get Parameters of Search Panel
	 * @return
	 */
	public LinkedHashMap<Object, Object> getParameters() {
		return m_search;
	}
}
