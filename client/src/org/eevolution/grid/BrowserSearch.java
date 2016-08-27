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
 * Copyright (C) 2003-2016 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpcya.com                                 *
 *****************************************************************************/
package org.eevolution.grid;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Level;

import org.adempiere.model.MBrowse;
import org.adempiere.model.MBrowseField;
import org.compiere.model.GridField;
import org.compiere.model.GridFieldVO;
import org.compiere.model.MLookup;
import org.compiere.model.M_Element;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 *	Controller for Browser Search, it allow to developer create different views from it
 *	@author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * 		<li>BR [ 344 ] Smart Browse Search View is not MVC
 * 		@see https://github.com/adempiere/adempiere/issues/344
 * 		<li><a href="https://github.com/adempiere/adempiere/issues/556">
 * 		FR [ 556 ] Criteria Search on SB don't have a parameter like only information</a>
 */
public abstract class BrowserSearch {
	
	/**
	 *	Dynamic generated Parameter panel.
	 *  @param WindowNo window
	 *  @param p_AD_Browse_ID browser
	 *  @param columns
	 */
	public BrowserSearch(int WindowNo, int p_AD_Browse_ID, int columns) {
		//	Get parameters
		m_WindowNo = WindowNo;
		m_AD_Browse_ID = p_AD_Browse_ID;
		fields = new ArrayList<GridField>();
		fieldsTo = new ArrayList<GridField>();
		m_search = new LinkedHashMap<String, Object>();
		m_Columns = columns;
		try {
			//	Start Init
			m_HasParameters = loadData();
		} catch(Exception ex) {
			m_IsError = true;
			log.log(Level.SEVERE, ex.getMessage());
		}
	}	//	ProcessParameterPanel
	
	/**
	 * Standard Constructor
	 * @param WindowNo
	 * @param p_AD_Browse_ID
	 */
	public BrowserSearch(int WindowNo, int p_AD_Browse_ID) {
		this(WindowNo, p_AD_Browse_ID, COLUMNS_1);
	}

	private int			m_WindowNo;
	private int 		m_AD_Browse_ID;
	private boolean 	m_IsError;
	private boolean 	m_HasParameters;
	private int 		m_Columns;
	private boolean		m_IsLoaded = false;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(BrowserSearch.class);
	//
	private ArrayList<GridField>			fields;
	private ArrayList<GridField>			fieldsTo;
	/** Parameters */
	private LinkedHashMap<String, Object> 	m_search;
	//	Constants
	/**	For one Column		*/
	public static final int COLUMNS_1 = 1;
	/**	For two Column		*/
	public static final int COLUMNS_2 = 2;
	//
		
	/**
	 *	Static Layout, instance view components
	 */
	public abstract void initComponents();
		
	/**
	 * Create the editor
	 * @param field
	 * @param field_To
	 * @param to is a parameter to
	 */
	public abstract void createViewField(GridField field, GridField field_To);
		
	/**
	 * Add and show fields in view
	 */
	public abstract void dynamicDisplay();
		
	/**
	 * Refresh Context
	 */
	public abstract void refreshContext();
	
	/**
	 * Get Display from view
	 * @param index
	 * @return
	 */
	public abstract String getDisplay(int index);
		
	/**
	 * Get Display from view To
	 * @param index
	 * @return
	 */
	public abstract String getDisplay_To(int index);
	
	/**
	 * Get Value of Editor
	 * @param index
	 * @return
	 */
	public abstract Object getValue(int index);
	
	/**
	 * Get Value of Editor To
	 * @param index
	 * @return
	 */
	public abstract Object getValue_To(int index);
	
	
	/**
	 * Set value to Editor
	 * @param index
	 * @param value
	 */
	public abstract void setValue(int index, Object value);
	
	/**
	 * Set to values to Editor
	 * @param index
	 * @param value
	 */
	public abstract void setValue_To(int index, Object value);
	
	/**
	 * Set Parameter
	 * @param name
	 * @param value
	 */
	public void setParameter(String name, Object value) {
		m_search.put(name, value);
	}

	/**
	 * Get Parameters of Search Panel
	 * @return
	 */
	public LinkedHashMap<String, Object> getParameters() {
		return m_search;
	}
	
	/**
	 * Is error
	 * @return
	 */
	public boolean isError() {
		return m_IsError;
	}
		
	/**
	 *  Dispose
	 */
	public void dispose() {
		fields.clear();
		fieldsTo.clear();
	}   //  dispose
	
	/**
	 * Get Columns
	 * @return
	 */
	public int getColumns() {
		return m_Columns;
	}
	
	/**
	 * Set Columns
	 * @param columns
	 */
	public void setColumns(int columns) {
		m_Columns = columns;
	}
	
	/**
	 *	Read Fields to display
	 *  @return true if loaded OK
	 */
	private boolean loadData() {
		log.config("");		
		//	Create Fields
		MBrowse browse = MBrowse.get(Env.getCtx(), m_AD_Browse_ID);
		//	Valid null
		if(browse == null)
			return false;
		//	
		List<MBrowseField> fields = browse.getCriteriaFields();
		if(fields == null
				|| fields.size() == 0)
			return false;
		//	Add fields
		for (MBrowseField field : fields) {
			createField (field);
		}
		//	
		return true;
	}	//	init
	
	/**
	 * Init View
	 * @return
	 */
	public boolean init() {
		//	Instance Default view
		initComponents();		
		//	clean up
		if (hasParameters()) {
			for (int i = 0; i < fields.size(); i++) {
				//	Get Values
				GridField field = (GridField) fields.get(i);
				GridField field_To = (GridField) fieldsTo.get(i);
				//	Create Fields
				createViewField(field, field_To);
			}
			//	
			dynamicDisplay();
			m_IsLoaded = true;
			return true;
		}
		//	
		dispose();
		//	Default
		return false;
	}
	
	/**
	 * Verify if the components is loaded
	 * @return
	 */
	public boolean isLoaded() {
		return m_IsLoaded;
	}
	
	/**
	 *	Create Field.
	 *	- creates Fields and adds it to m_mFields list
	 *  - creates Editor and adds it to m_vEditors list
	 *  Handles Ranges by adding additional mField/vEditor.
	 *  <p>
	 *  mFields are used for default value and mandatory checking;
	 *  vEditors are used to retrieve the value (no data binding)
	 *
	 * @param field field
	 */
	private void createField (MBrowseField field) {
		GridFieldVO voBase = createVO(field, false);
		GridField gField = new GridField(voBase);
		fields.add(gField);
		//
		if (voBase.IsRange) {
			GridFieldVO voBase_To = createVO(field, true);
			GridField gField_To = new GridField(voBase_To);
			//	
			fieldsTo.add(gField_To);
		} else {
			fieldsTo.add (null);
		}
	}	//	createField
	
	/**
	 * Create VO from field
	 * @param field
	 * @param isTo
	 * @return
	 */
	private GridFieldVO createVO(MBrowseField field, boolean isTo) {
		//  Create Field
		GridFieldVO voBase = GridFieldVO.createStdField(field.getCtx(),
				getWindowNo(), 0, 0, 0, false, false, false);

		String uniqueName = field.getAD_View_Column().getColumnName() + (isTo? "_To": "");
		
		voBase.isProcess = true;
		voBase.IsDisplayed = true;
		voBase.IsReadOnly = false;
		voBase.IsUpdateable = true;
		voBase.WindowNo = getWindowNo();
		M_Element element = (M_Element) field.getAD_Element();
		voBase.ColumnName = element.getColumnName();
		//	FR [ 344 ]
		voBase.ColumnSQL = field.getAD_View_Column().getColumnSQL();
		//	BR [ 318 ]
		if(field.getAD_View_Column().getAD_Column_ID() > 0) {
			voBase.AD_Column_ID = field.getAD_View_Column().getAD_Column_ID();
			voBase.AD_Table_ID = field.getAD_View_Column().getAD_Column()
					.getAD_Table_ID();
		}
		//	Set unique alias
		voBase.ColumnNameAlias = uniqueName;
		voBase.displayType = field.getAD_Reference_ID();
		voBase.AD_Reference_Value_ID = field.getAD_Reference_Value_ID();
		voBase.IsMandatory = field.isMandatory();
		voBase.IsAlwaysUpdateable = true;
		voBase.IsKey = field.isKey();

		voBase.DefaultValue = isTo? field.getDefaultValue2(): field.getDefaultValue();
		voBase.DefaultValue2 = field.getDefaultValue2();
		voBase.InfoFactoryClass = field.getInfoFactoryClass();
		voBase.FieldLength = field.getFieldLength();
		voBase.ReadOnlyLogic = field.getReadOnlyLogic();
		voBase.DisplayLogic = field.getDisplayLogic();
		voBase.VFormat = field.getVFormat();
		voBase.ValueMin = field.getValueMin();
		voBase.ValueMax = field.getValueMax();
		voBase.ValidationCode = field.getAD_Val_Rule().getCode();
		//	FR [ 349 ]
		voBase.IsRange = field.isRange();
		voBase.Description = field.getDescription();
		voBase.Help = field.getHelp();
		voBase.Header = isTo? Msg.getMsg(Env.getCtx(), "To"): field.getName();
		voBase.IsColumnSQLReference = true;
		//	Information Only
		voBase.IsInfoOnly = field.isInfoOnly();
		voBase.initFinish();
		//	Return 
		return voBase;
	}

	/**
	 * When exists a event in a field
	 * @param field
	 * @param newValue
	 * @param propertyName
	 */
	public void fieldChange(GridField field, Object newValue, String propertyName) {
		//	set new value
		if(field != null) {
			//	Process dependences
			processDependencies (field);
		}
		// future processCallout (changedField);
		processNewValue(newValue, propertyName);
	}	//	vetoableChange
	
	
	/**
	 *  Evaluate Dependencies
	 *  @param changedField changed field
	 */
	private void processDependencies (GridField changedField) {
		String columnName = changedField.getColumnNameAlias();

		for (GridField field : fields) {
			if (field == null || field == changedField)
				continue;
			verifyChangedField(field, columnName);
		}
		for (GridField field : fieldsTo) {
			if (field == null || field == changedField)
				continue;
			verifyChangedField(field, columnName);
		}
	}   //  processDependencies

	/**
	 * Verify if a field is changed
	 * @param field
	 * @param columnName
	 */
	private void verifyChangedField(GridField field, String columnName) {
		ArrayList<String> list = field.getDependentOn();
		if (list.contains(columnName)) {
			if (field.getLookup() instanceof MLookup)
			{
				MLookup mLookup = (MLookup)field.getLookup();
				//  if the lookup is dynamic (i.e. contains this columnName as variable)
				if (mLookup.getValidation().indexOf("@"+columnName+"@") != -1)
				{
					log.fine(columnName + " changed - "
						+ field.getColumnName() + " set to null");
					//  invalidate current selection
					mLookup.refresh();
				}
			}
		}
	}

	/**
	 * Set to context Value
	 * @param value
	 * @param name
	 */
	private void processNewValue(Object value, String name) {

		//  Not sure this is required or should be done here.  Env context is set 
		//  by the gridField when its value is changed.
//		if (value == null)
//			value = new String("");
//		if (value instanceof String)
//			Env.setContext(Env.getCtx(), m_WindowNo, name, (String) value);
//		else if (value instanceof Integer)
//			Env.setContext(Env.getCtx(), m_WindowNo, name, ((Integer) value)
//					.intValue());
//		else if (value instanceof Boolean)
//			Env.setContext(Env.getCtx(), m_WindowNo, name, ((Boolean) value)
//					.booleanValue());
//		else if (value instanceof Timestamp)
//			Env.setContext(Env.getCtx(), m_WindowNo, name, (Timestamp) value);
//		else
//			Env.setContext(Env.getCtx(), m_WindowNo, name, value.toString());

		dynamicDisplay();
	}

	/**
	 * Validate Parameters
	 * @return null if nothing happens
	 */
	public String validateParameters() {
		log.config("");

		/**
		 *	Mandatory fields
		 *  see - MTable.getMandatory
		 */
		StringBuffer sb = new StringBuffer();
		int size = fields.size();
		for (int i = 0; i < size; i++) {
			GridField mField = (GridField) fields.get(i);
			//	Validate
			mField.validateValue();
			//	check context
			if (mField.isMandatory(true)
					&& !mField.isInfoOnly()) {
				Object data = getValue(i);
				if (data == null 
						|| data.toString().length() == 0) {
					mField.setInserting (true);  //  set editable (i.e. updateable) otherwise deadlock
					mField.setError(true);
					if (sb.length() > 0)
						sb.append(", ");
					sb.append(mField.getHeader());
				} else {
					mField.setError(false);
				}
				//  Check for Range
				GridField mField_To = (GridField) fieldsTo.get(i);
				//	Validate
				if (mField_To != null) {
					Object data_To = getValue_To(i);
					if (data_To == null 
							|| data_To.toString().length() == 0) {
						mField.setInserting (true);  //  set editable (i.e. updateable) otherwise deadlock
						mField_To.setError(true);
						if (sb.length() > 0)
							sb.append(", ");
						sb.append(mField.getHeader());
					} else {
						mField_To.setError(false);
					}
				}   //  range field
			}   //  mandatory
		}   //  field loop
		//	Valid if exists a error
		if (sb.length() != 0) {
			return sb.toString();
		}
		//	Nothing happened
		return null;
	}	//	validateParameters
	
	/**
	 * Restore window context.
	 * @author teo_sarca [ 1699826 ]
	 * @see org.compiere.model.GridField#restoreValue()
	 */
	public void restoreContext() {
		for (int i = 0; i < fields.size(); i++) {
			//	Get Values
			GridField mField = (GridField) fields.get(i);
			GridField mField_To = (GridField) fieldsTo.get(i);
			//	Restore
			if (mField != null) {
				mField.restoreValue();
			}
			//	Restore To Value
			if (mField_To != null) {
				mField_To.restoreValue();
			}
		}
	}
	
	/**
	 * getIndex. Get m_mFields index from columnName
	 * 
	 * @param columnName
	 * @return int
	 **/
	public int getIndex(String columnName) {

		for (int i = 0; i < fields.size(); i++) {
			if (fields.get(i).getColumnName().equals(columnName)) {
				return i;
			}
		}
		return 0;
	} // getIndex
	
	/**
	 * getIndex. Get m_mFields index from columnName_To
	 * 
	 * @param columnName
	 * @return int
	 **/
	public int getIndex_To(String columnName) {

		for (int i = 0; i < fieldsTo.size(); i++) {
			if (fieldsTo.get(i).getColumnName().equals(columnName)) {
				return i;
			}
		}
		return 0;
	} // getIndex
	
	/**
	 * Get Grid Field
	 * @param index
	 * @return
	 */
	public GridField getField(int index) {
		return fields.get(index);
	}
	
	/**
	 * Get Grid Field To
	 * @param index
	 * @return
	 */
	public GridField getField_To(int index) {
		return fieldsTo.get(index);
	}
	
	/**
	 * Get the Window number
	 * @return
	 */
	public int getWindowNo() {
		return m_WindowNo;
	}
	
	/**
	 * Verify if has parameter
	 * @return true if has parameters
	 */
	public boolean hasParameters() {
		return m_HasParameters;
	}
		
}	//	BrowserSearch
