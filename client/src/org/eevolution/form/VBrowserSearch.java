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

import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Window;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import org.adempiere.model.MBrowseField;
import org.compiere.apps.ALayoutConstraint;
import org.compiere.apps.ProcessParameterPanel;
import org.compiere.grid.ed.VEditor;
import org.compiere.grid.ed.VEditorFactory;
import org.compiere.model.GridField;
import org.compiere.model.GridFieldVO;
import org.compiere.model.MLookup;
import org.compiere.plaf.CompiereColor;
import org.compiere.swing.CPanel;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 * @author victor.perez@e-evolution.com , eEvolution Consultants
 * 
 */
public class VBrowserSearch extends CPanel implements 
		VetoableChangeListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 165374610556112182L;
	private int p_WindowNo;
	private ArrayList<VEditor>	m_vEditors = new ArrayList<VEditor>();
	private ArrayList<VEditor>	m_vEditors2 = new ArrayList<VEditor>();		//	for ranges
	private ArrayList<GridField>	m_mFields = new ArrayList<GridField>();
	private ArrayList<GridField>	m_mFields2 = new ArrayList<GridField>();
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(ProcessParameterPanel.class);
	
	/** Parameters */
	protected LinkedHashMap<Object, Object> m_search = new LinkedHashMap<Object, Object>();

	/**
	 * @param layout
	 * @param isDoubleBuffered
	 */
	public VBrowserSearch(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
	}

	/**
	 * @param layout
	 */
	public VBrowserSearch(LayoutManager layout) {
		super(layout);
	}

	/**
	 * @param isDoubleBuffered
	 */
	public VBrowserSearch(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
	}

	/**
	 * 
	 */
	public VBrowserSearch(int windowNo) {
		p_WindowNo = windowNo;
	}

	/**
	 * 
	 */
	public VBrowserSearch() {
	}

	/**
	 * @param bc
	 */
	public VBrowserSearch(CompiereColor bc) {
		super(bc);
	}

	public void addField(MBrowseField field, int row, int col, String name, String title) {
		GridFieldVO voBase = GridFieldVO.createStdField(field.getCtx(),
				p_WindowNo, 0, 0, 0, false, false, false);
		
		String uniqueName =  field.getAD_View_Column().getColumnSQL();

		voBase.isProcess = true;
		voBase.IsDisplayed = true;
		voBase.IsReadOnly = true;
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
		voBase.DisplayLogic =  field.getDisplayLogic();
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

		VEditor editor = VEditorFactory.getEditor(gField, false);
		editor.setReadWrite(true);
		editor.addVetoableChangeListener(this);
		m_vEditors.add (editor); //  add to Editors
		
		if (DisplayType.YesNo != field.getAD_Reference_ID()) {
			JLabel label = VEditorFactory.getLabel(gField);
			label.setName(uniqueName);
			add(label, new ALayoutConstraint(row, col));
		}
		add((Component) editor, new ALayoutConstraint(row, col + 1));
		setParameter(name, editor);
		
		if (field.isRange())
		{		
			col++;
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
			
			VEditor editor2 = VEditorFactory.getEditor(gField2, false);
			editor2.setReadWrite(true);
			editor2.addVetoableChangeListener(this);
			m_vEditors2.add (editor2); //  add to Editors
			
			JLabel label = VEditorFactory.getLabel(gField2);
			label.setName(uniqueName);
			col++;
			add(label, new ALayoutConstraint(row, col));
			col++;
			add((Component) editor2, new ALayoutConstraint(row, col));
			setParameter(name, editor2);
		}
		else
		{	
			m_mFields2.add (null);
			m_vEditors2.add (null);
		}
	}


	/**
	 *	Editor Listener
	 *	@param evt Event
	 * 	@exception PropertyVetoException if the recipient wishes to roll back.
	 */
	public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException{
		if (evt.getSource() instanceof VEditor) {
			GridField changedField = ((VEditor) evt.getSource()).getField();
			if (changedField != null) {
				processDependencies (changedField);
				// future processCallout (changedField);
			}
		}
		String columnName = "";
		if(evt.getSource() instanceof VEditor)
		{
			VEditor vEditor = (VEditor)evt.getSource();
			columnName = vEditor.getField().getVO().Help;
		}
		processNewValue(evt.getNewValue(), columnName);
	}	//	vetoableChange

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
		VEditor editor = (VEditor) m_search.get(key);
		if (editor != null)
			return editor.getValue();
		else
			return null;
	}

	
	
	/**
	 *  Evaluate Dependencies
	 *  @param changedField changed field
	 */
	private void processDependencies (GridField changedField)
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
	}   //  processDependencies

	private void verifyChangedField(GridField field, String columnName) {
		ArrayList<String> list = field.getDependentOn();
		if (list.contains(columnName)) {
			if (field.getLookup() instanceof MLookup) {
				MLookup mLookup = (MLookup)field.getLookup();
				//  if the lookup is dynamic (i.e. contains this columnName as variable)
				if (mLookup.getValidation().indexOf("@"+columnName+"@") != -1){
					log.fine(columnName + " changed - "
						+ field.getColumnName() + " set to null");
					//  invalidate current selection
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
			Env.setContext(Env.getCtx(), p_WindowNo, name, ((Integer) value)
					.intValue());
		else if (value instanceof Boolean)
			Env.setContext(Env.getCtx(), p_WindowNo, name, ((Boolean) value)
					.booleanValue());
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
		Component[] comps = getComponents();
		for (int i = 0; i < comps.length; i++) {
			Component comp = comps[i];
			// BF3423098 - Labels for process parameters with display logic false are still shown 
			//if (comp instanceof CLabel)
			//	continue;
			String columnName = "";
			
			if(comp instanceof VEditor)
			{			
				VEditor vEditor = (VEditor) comp;
				columnName = vEditor.getField().getVO().Help;
				//columnName = comp.getName();
			}
			else columnName =  comp.getName();

			if (columnName != null && columnName.length() > 0) {
				int index = getIndex(columnName);
				if (m_mFields.get(index) != null) {
					if (m_mFields.get(index).isDisplayed(true)) { // check
						// context
						if (!comp.isVisible()) {
							comp.setVisible(true); // visibility
							//if (m_mFields.get(index).getVO().isRange)
								//m_separators.get(index).setText(" - ");
						}
						boolean rw = m_mFields.get(index).isEditablePara(true); // r/w - check if field is Editable
						m_vEditors.get(index).setReadWrite(rw);
						if (m_mFields.get(index).getVO().isRange)
							m_vEditors2.get(index).setReadWrite(rw);
					} else {
						if (comp.isVisible()) {
							comp.setVisible(false);
							//if (m_mFields.get(index).getVO().isRange)
								//m_separators.get(index).setText("");
						}
					}
					Window win = SwingUtilities.getWindowAncestor(comp);
				      if (win != null) {
				         win.pack();
				         win.setLocationRelativeTo(null);
				      }
				}					
			}
		}
	} // Dynamic Display.

	/**
	 * getIndex. Get m_mFields index from columnName
	 * 
	 * @param columnName
	 * @return int
	 **/
	private int getIndex(String columnName) {

		for (int i = 0; i < m_mFields.size(); i++) {
			if (m_mFields.get(i).getVO().Help.equals(columnName)) {
				return i;
			}
		}
		return 0;
	} // getIndex
	
	
	/**
	 *  Dispose
	 */
	public void dispose()
	{
		cleanContext();
		m_vEditors.clear();
		m_vEditors2.clear();
		m_mFields.clear();
		m_mFields2.clear();
		this.removeAll();
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
