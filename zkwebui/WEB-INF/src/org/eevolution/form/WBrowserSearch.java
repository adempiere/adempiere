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

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.adempiere.model.MBrowseField;
import org.adempiere.webui.apps.ProcessParameterPanel;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.editor.WEditor;
import org.adempiere.webui.editor.WebEditorFactory;
import org.adempiere.webui.event.ValueChangeEvent;
import org.adempiere.webui.event.ValueChangeListener;
import org.compiere.model.GridField;
import org.compiere.model.GridFieldVO;
import org.compiere.model.MLookup;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.zul.Div;
import org.zkoss.zul.Row;

/**
 * @author victor.perez@e-evolution.com , eEvolution Consultants
 * 
 */
public class WBrowserSearch extends  Grid implements ValueChangeListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7952373172724992710L;
	private int p_WindowNo;
	private ArrayList<WEditor> m_wEditors = new ArrayList<WEditor>();
	private ArrayList<WEditor> m_wEditors2 = new ArrayList<WEditor>(); // for ranges
	private ArrayList<GridField> m_mFields = new ArrayList<GridField>();
	private ArrayList<GridField> m_mFields2 = new ArrayList<GridField>();
	/** Logger */
	private static CLogger log = CLogger.getCLogger(ProcessParameterPanel.class);

	/** Parameters */
	protected LinkedHashMap<Object, Object> m_search = new LinkedHashMap<Object, Object>();

	/**
	 * 
	 */
	public WBrowserSearch() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	public WBrowserSearch(int windowNo) {
		p_WindowNo = windowNo;
    	makeNoStrip();
	}

	public void addField(MBrowseField field, Row row, String name, String title) {
		GridFieldVO voBase = GridFieldVO.createStdField(field.getCtx(),
				p_WindowNo, 0, 0, 0, false, false, false);

		String uniqueName =  field.getAD_View_Column().getColumnSQL();
		
		voBase.isProcess = true;
		voBase.IsDisplayed = true;
		voBase.IsReadOnly = false;
		voBase.IsUpdateable = true;
		voBase.WindowNo = p_WindowNo;
		
		voBase.AD_Column_ID = field.getAD_View_Column().getAD_Column_ID();
		voBase.AD_Table_ID = field.getAD_View_Column().getAD_Column()
				.getAD_Table_ID();
		voBase.ColumnName = field.getAD_View_Column().getAD_Column()
				.getColumnName();
		voBase.displayType = field.getAD_Reference_ID();
		voBase.AD_Reference_Value_ID = field.getAD_Reference_Value_ID();
		voBase.IsMandatory = field.isMandatory();
		voBase.IsAlwaysUpdateable = false;
		voBase.IsKey = field.isKey();

		voBase.DefaultValue = field.getDefaultValue();
		voBase.DefaultValue2 = field.getDefaultValue2();
		voBase.FieldLength = field.getFieldLength();
		voBase.ReadOnlyLogic = field.getReadOnlyLogic();
		voBase.DisplayLogic = field.getDisplayLogic();
		voBase.VFormat = field.getVFormat();
		voBase.ValueMin = field.getValueMin();
		voBase.ValueMax = field.getValueMax();
		voBase.ValidationCode = field.getAD_Val_Rule().getCode();

		voBase.isRange = field.isRange();
		voBase.Description = field.getDescription();
		voBase.Help = uniqueName;
		voBase.Header = title;

		GridField gField = new GridField(GridFieldVO.createParameter(voBase));
		//  Set Default
		Object defaultObject = gField.getDefault();
		gField.setValue (defaultObject, true);
		gField.lookupLoadComplete();
		m_mFields.add(gField);
		
		WEditor editor = WebEditorFactory.getEditor(gField, false);
		editor.setReadWrite(true);
		editor.addValueChangeListener(this);
		editor.dynamicDisplay();
		m_wEditors.add (editor);
		
		if (DisplayType.YesNo != field.getAD_Reference_ID()) {
			Div div = new Div();
			div.setAlign("right");
			org.adempiere.webui.component.Label label = editor.getLabel();
			label.setValue(title);
			div.appendChild(label);
			if (label.getDecorator() != null)
				div.appendChild(label.getDecorator());
			row.appendChild(div);
		}
		row.appendChild(editor.getComponent());
		setParameter(name, editor);
		
		if (field.isRange())
		{		
			title = Msg.getMsg(Env.getCtx(), "To");
			name = name + "_To";
			voBase.Header = title;
			voBase.DefaultValue = field.getDefaultValue2();
			GridField gField2 = new GridField(GridFieldVO.createParameter(voBase));
			//  Set Default
			Object defaultObject2 = gField2.getDefault();
			gField2.setValue (defaultObject2, true);
			gField2.lookupLoadComplete();
			m_mFields2.add(gField2);

			WEditor editor2 = WebEditorFactory.getEditor(gField2, false);
			editor.setReadWrite(true);
			editor.addValueChangeListener(this);
			editor.dynamicDisplay();
			m_wEditors2.add (editor2);
			
			Div div = new Div();
			div.setAlign("right");
			org.adempiere.webui.component.Label label = editor2.getLabel();
			label.setValue(title);
			div.appendChild(label);
			if (label.getDecorator() != null)
				div.appendChild(label.getDecorator());
			row.appendChild(div);
			
			row.appendChild(editor2.getComponent());
			setParameter(name, editor2);
		}
		else
		{
			m_mFields2.add(null);
			m_wEditors2.add (null);
		}
	}

	/**
	 *	Editor Listener
	 *	@param evt Event
	 * 	@exception valueChangeException if the recipient wishes to roll back.
	 */
	public void valueChange(ValueChangeEvent evt) {
		if (evt.getSource() instanceof WEditor) {
			GridField changedField = ((WEditor) evt.getSource()).getGridField();
			if (changedField != null) {
				processDependencies(changedField);
				// future processCallout (changedField);
			}
		}
		String columnName = "";
		if(evt.getSource() instanceof WEditor)
		{
			WEditor wEditor = (WEditor)evt.getSource();
			columnName = wEditor.getGridField().getVO().Help;
		}
		processNewValue(evt.getNewValue(), columnName);
	} // valueChange

	public void setParameter(Object name, Object value) {
		if (value != null) {
			m_search.put(name, value);
		}
	}

	public LinkedHashMap<Object, Object> getParamenters() {
		return m_search;
	}

	/**
	 * get Parameter Value
	 * 
	 * @param id
	 * @return Object Value
	 */
	public Object getParamenterValue(Object key) {
		WEditor editor = (WEditor) m_search.get(key);
		if (editor != null)
			return editor.getValue();
		else
			return null;
	}
	
	/**
	 * Evaluate Dependencies
	 * @param changedField changed field
	 */
	private void processDependencies(GridField changedField) 
	{
		
		String columnName = changedField.getVO().Help;;

		for (GridField field : m_mFields) {
			if (field == null || field == changedField)
				continue;
			verifyChangedField(field, columnName);
		}
		for (GridField field : m_mFields2) {
			if (field == null || field == changedField)
				continue;
			verifyChangedField(field, columnName);
		}
	} // processDependencies

	private void verifyChangedField(GridField field, String columnName) {
		ArrayList<String> list = field.getDependentOn();
		if (list.contains(columnName)) {
			if (field.getLookup() instanceof MLookup) {
				MLookup mLookup = (MLookup) field.getLookup();
				// if the lookup is dynamic (i.e. contains this columnName as variable)
				if (mLookup.getValidation().indexOf("@" + columnName + "@") != -1) {
					log.fine(columnName + " changed - " + field.getColumnName()
							+ " set to null");
					// invalidate current selection
					field.setValue(null, true);
				}
			}
		}
	}

	private void processNewValue(Object value, String name) {
		if (value == null)
			value = new String("");

		if (value instanceof String)
			Env.setContext(Env.getCtx(), p_WindowNo, name, (String) value);
		else if (value instanceof Integer)
			Env.setContext(Env.getCtx(), p_WindowNo, name,
					((Integer) value).intValue());
		else if (value instanceof Boolean)
			Env.setContext(Env.getCtx(), p_WindowNo, name,
					((Boolean) value).booleanValue());
		else if (value instanceof Timestamp)
			Env.setContext(Env.getCtx(), p_WindowNo, name, (Timestamp) value);
		else
			Env.setContext(Env.getCtx(), p_WindowNo, name, value.toString());

		dynamicDisplay();
	}

	/**
	 * Dynamic Display.
	 * 
	 **/
	public void dynamicDisplay() {
		for (int i = 0; i < m_wEditors.size(); i++) {
			WEditor editor = m_wEditors.get(i);
			GridField mField = editor.getGridField();
			if (mField.isDisplayed(true)) {
				if (!editor.isVisible()) {
					editor.setVisible(true);
					if (mField.getVO().isRange) {
					// m_separators.get(i).setVisible(true);
					 m_wEditors2.get(i).setVisible(true);
					}
				}
				boolean rw = mField.isEditablePara(true); // r/w - check if
															// field is Editable
				editor.setReadWrite(rw);
				editor.dynamicDisplay();
				if (mField.getVO().isRange) {
					m_wEditors2.get(i).setReadWrite(rw);
					m_wEditors2.get(i).dynamicDisplay();
				}
			} else if (editor.isVisible()) {
				editor.setVisible(false);
				 if (mField.getVO().isRange) {
				// m_separators.get(i).setVisible(false);
				 m_wEditors2.get(i).setVisible(false);
				}
			}
		}
	}
	
	/**
	 *  Dispose
	 */
	public void dispose()
	{
		cleanContext();
		m_wEditors.clear();
		m_wEditors2.clear();
		m_mFields.clear();
		m_mFields2.clear();		
	}   //  dispose
	
	/**
	 * Restore window context.
	 * @author teo_sarca [ 1699826 ]
	 * @see org.compiere.model.GridField#restoreValue()
	 */
	protected void cleanContext() {
		for (GridField f : m_mFields) {
			if (f != null)
			{	
				f.restoreValue();
				Env.setContext(f.getVO().ctx, p_WindowNo, f.getVO().Help, "");
			}	
		}
		for (GridField f : m_mFields2) {
			if (f != null)
			{				
				f.restoreValue();
				Env.setContext(f.getVO().ctx, p_WindowNo, f.getVO().Help, "");
			}	
		}
	}

}
