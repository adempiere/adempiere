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
 * Copyright (C) 2016 ADempiere Foundation All Rights Reserved.               *
 *****************************************************************************/

package org.adempiere.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.ValueChangeEvent;
import org.adempiere.exceptions.ValueChangeListener;
import org.adempiere.model.MBrowseField;
import org.compiere.model.GridField;
import org.compiere.model.GridFieldVO;
import org.compiere.model.MLookup;
import org.compiere.model.MProcessPara;
import org.compiere.model.M_Element;
import org.compiere.swing.CEditor;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Util;

/**
 * The SmallViewController provides an generic MVC connection between the "view" of editor fields
 * in Swing or ZK and the models.  The code in this package has been drawn from the implementations 
 * of process parameter panels and smart browsers contributed by Victor Perez and Yamel Senih and 
 * is aimed at simplifying the view layer of the MVC model by moving all connections with the model
 * into the controller.<br>
 * <br>
 * The SmallViewController is a simplification of the GridController and assumes that the view will 
 * contain a display of single fields and/or ranges.<br>
 * <br>
 * View displays that contain fields should implement the SmallViewEditable interface.  
 *  
 * @author mckayERP michael.mckay@mckayerp.com
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpya.com
 * 		<li>FR [ 1742 ] Dependent field do not refresh automatically
 * 		@see https://github.com/adempiere/adempiere/issues/1742
 *
 */
public abstract class SmallViewController implements SmallViewEditable, VetoableChangeListener, PropertyChangeListener, ValueChangeListener {

	public SmallViewController() {
		// Need to know which interface is being used as the events may be different and the proper
		// listeners have to be activated.  Test the calling stack trace for "webui".
		// If not found, assume the SWING interface
		StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
        for (int i=1; i<stElements.length; i++) {
            StackTraceElement ste = stElements[i];
            if (ste.getClassName().contains("webui")
            		|| ste.getClassName().contains("zk.ui")) {
                m_IsSwing = false;
                break;
            }
        }
	}	

	/**
	 * The list of model fields (gridFields).  These are not known to the view.  The elements of 
	 * fields represent a single field or the first element in a range. 
	 */
	private ArrayList<GridField>			fields = new ArrayList<GridField>();
	/**
	 * The list of model fields (gridFields) that form the second part of a range.  These are 
	 * not known to the view.  The size of the fieldsTo array will be identical to the {@link #fields}
	 * array.  If the field represents a range, the fieldsTo entry will be not null. 
	 */
	private ArrayList<GridField>			fieldsTo = new ArrayList<GridField>();
	/**
	 * A HashMap indicating if the field with that column name is used for info only and should 
	 * be read only in the view.
	 */
	private HashMap<String, Boolean>		fieldsInfoOnly = new HashMap<String, Boolean>();
	
	/**
	 * The ArrayList of editors used by the view.  The interface used is {@link org.compiere.swing.CEditor}
	 * which exists in the "base". All user interface editors at the View level implement this interface.<br>
	 * <br>
	 * The editors ArrayList represents the single editor or the first element of a range. The index will
	 * be the same as the matching {@link #fields} ArrayList.<br>
	 * <br>
	 * TODO - a better approach would be to make an abstract editor class that would be common to
	 * all user interface implementations of the editors and include the common code there.  As it 
	 * currently exists, the editors for swing and zk duplicate a lot of code.
	 *  
	 */
	private ArrayList<CEditor> 			editors = new ArrayList<CEditor>();
	/**
	 * An ArrayList of the second part of the range, if required, or null.  See {@link #editors} and {@link #fieldsTo}.
	 * The index of the ArrayList entry will match the editors, fields and fieldsTo ArrayLists.   
	 */
	private ArrayList<CEditor> 			editorsTo = new ArrayList<CEditor>();
	
	private LinkedHashMap<String, Object> m_search;
	protected boolean m_IsLoaded = false;
	private boolean m_IsSwing = true;
	private boolean m_HasParameters = false;

	private CLogger log = CLogger.getCLogger(this.getClass());
	/** Max Display Length = 60		*/
	public static final int MAXDISPLAY_LENGTH = 30;
	
	// Abstract methods
	
	/**
	 * Load the fields required by the view.  This function should be overridden 
	 * by the view.  The loadData function should generate ResultSets that match 
	 * the AD_Process_Para fields and should call {@link #createField(ResultSet, int) createField(ResultSet, int)}
	 * or (@link {@link #createField(MBrowseField, int)} to create the necessary fields.
	 */
	public abstract boolean loadData();

	/**
	 * A function that must be implemented by the View to create the appropriate editor
	 * for the given gridField.  The editor should implement the CEditor interface and 
	 * return the CEditor object. 
	 * @param the GridField that requires an editor.
	 * @return The CEditor object representing the editor.
	 */
	public abstract CEditor createEditor(GridField field);

	/**
	 * This function will be called to set the visibility of components in the view.  The index is the 
	 * order of the calls to {@link #createEditor(GridField)} which can be used to set the visibility
	 * of other elements of the view as required.
	 */
	public abstract void setComponentVisibility(int index, Boolean visible, Boolean isRange);

	
	// Main methods

	/**
	 * Initialize the controller.  This routine will call the View to load the specific set of
	 * fields and create the editors.  The View software has to provide the function {@link org.adempiere.controller.SmallViewEditable#loadData() loadData()}
	 * which will provided the data necessary to generate the fields.  The View will also have 
	 * to create editors to match the fields using the appropriate editor factory.  When the 
	 * fields and editors are created, the init() function will also call {@link org.adempiere.controller.SmallViewEditable#initComponents() initComponents}
	 * to instruct the View to build the interface structure.  Then, the controller will ask 
	 * the View to format each editor in turn by calling {@link org.adempiere.controller.SmallViewEditable#formatEditor(CEditor, CEditor) formatEditor}.
	 * The event handling is set by the controller to ensure that model/field updates are passed
	 * to the editors and user changes of the editors are passed back to the model/fields.
	 * Default values will be set in each field and these will propagate to the editors via the
	 * events.
	 * 
	 * @return true if the view has fields. 
	 */
	public Boolean init() {
		
		m_IsLoaded = false;

		//	Start Init
		if (loadData()) {  // This will load the data and create the model gridFields
			m_search = new LinkedHashMap<String, Object>();
			setHasParameters(true);
			createEditors(); // Create the UI editors for each gridField
			//	Instance Default view
			initComponents(); // Instruct the view to initialize
			for (int i = 0; i < editors.size(); i++) {
				formatEditor(editors.get(i),editorsTo.get(i)); // Instruct the view to add  to the view and format the editor. 
			}
			//	
			dynamicDisplay();  // Set the default values
			setLoaded(true);
			return hasParameters();
		} else {
			initComponents();
		}
		//	
		dispose();
		return false;
	}
	
	/**
	 * Create editors for each field/fieldTo.  The function calls the View to create
	 * the appropriate editor for the field.
	 * 
	 * TODO The GridFields are Model level entities and the view should not have to 
	 * deal with the Model. An intermediate factory could be used to create Controller 
	 * level editors common to all interfaces.  The View would then only have to implement
	 * the actual editor.  There is a CEditor interface used but an abstract class would be 
	 * better. 
	 */
	private void createEditors() {
        for (int i = 0; i < fields.size(); i++) {
            //    Get Values
            GridField field = (GridField) fields.get(i);
            GridField field_To = (GridField) fieldsTo.get(i);
        	createEditors(field, field_To);
        }
	}

	/**
	 * Create editors for each field/fieldTo pair.  Add event listeners as required
	 * by the various user interface types to ensure the fields and editors are synced. 
	 * @param field
	 * @param fieldTo
	 */
	private void createEditors(GridField field, GridField fieldTo) {
		//	
		if(field == null) {
			editors.add (null);
			return;
		}

		//	Make sure the lookup has been fully loaded.
		field.lookupLoadComplete();

		//	Ask the View to create the Editor and give the controller
		//  a reference to it.
		CEditor editor = createEditor(field);
		
		if (editor == null) {
			editors.add (null);
			return;
		}
		
        //  Add to Editors ArrayList
		editors.add(editor);

		//  Add to the list of parameters
		setParameter(field.getColumnNameAlias(), editor);
		
		//	Set the Default Value to match the field default
		//  before the events are set to avoid unnecessary events.
		Object defaultObject = field.getDefault();
		field.setValue(defaultObject, false);
		field.validateValue();
		editor.setValue(field.getValue());

		
		//	Add event handling.  
		//  Swing uses vetoableChangeListeners.
		//  ZK uses ValueChangeListeners.
		if (m_IsSwing) {
			editor.addVetoableChangeListener(this);
		}
		else {
			editor.addValueChangeListener(this);
		}
		//  GridFields use PropertyChange events to propagate changes
		//  back to the editors and controller.
		field.addPropertyChangeListener(editor);
		field.addPropertyChangeListener(this);

		
		//	To
		//  If fieldTo is null, there is no range, set the editorTo to null and return.
		if(fieldTo == null) {
			editorsTo.add(null);
			return;
		}

		//	Ensure the lookup has been fully loaded.
		fieldTo.lookupLoadComplete();

		// Ask the View to create the appropriate editor
		CEditor editorTo = createEditor(fieldTo);
		
		if (editorTo == null) {
			editorsTo.add (null);
			return;
		}

		//	The To editor
		editorsTo.add (editorTo);
		//  Add to the search/process parameter list
		setParameter(fieldTo.getColumnNameAlias(), editorTo);
		//	Set Default Value
		Object defaultObject2 = fieldTo.getDefault();
		fieldTo.setValue(defaultObject2, false);
		fieldTo.validateValue();
		editorTo.setValue(fieldTo.getValue());

		//	Add event handling.  
		//  Swing uses vetoableChangeListeners.
		//  ZK uses ValueChangeListeners.
		if (m_IsSwing) {
			editorTo.addVetoableChangeListener(this);
		}
		else {
			editorTo.addValueChangeListener(this);
		}
		//  GridFields use PropertyChange events to propagate changes
		//  back to the editors and controller.
		fieldTo.addPropertyChangeListener(editorTo);
		fieldTo.addPropertyChangeListener(this);
	}
	

	/**
	 * Does this view have any parameters or fields.
	 * @return true if there are parameters
	 */
	public boolean hasParameters() {
		return m_HasParameters;
	}

	/**
	 * Set the flag that indicates there are parameters
	 * @param hasParameters - set to true if there are parameters
	 */
	public void setHasParameters(boolean hasParameters) {
		m_HasParameters = hasParameters;
	}


	/**
	 * Get number of fields
	 * @param index
	 * @return
	 */
	public int getFieldSize() {
		return fields.size();
	}

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
	 * @return GridField or null
	 */
	public GridField getFieldTo(int index) {
		return fieldsTo.get(index);
	}

	/**
	 * Get the editor with the given index
	 * @param index
	 * @return CEditor editor
	 */
	public CEditor getEditor(int index) {
		return editors.get(index);
	}

	/**
	 * Get the "To" editor with the given index.  If there is no "To" or range
	 * then the return value will be null.
	 * @param index
	 * @return CEditor editor or null
	 */
	public CEditor getEditorTo(int index) {
		return editorsTo.get(index);
	}

	
	/**
	 *	Creates model gridFields which will accessible via the {@link #fields fields} list.
	 *  The {@link #fieldsTo fieldsTo} list will contain the matching "to" range or null.  
	 *  This function is an alternate to {@link #createField(ResultSet, int)}.<br>
	 *  <br>
	 *  This function should be called by the view's {@link #loadData() loadData()} function. 
	 *
	 * 	@param field MBrowserField
	 *  @param windowNo the Window number where the field will appear. This 
	 *  is required to ensure the context variables are set correctly for
	 *  the created field.
	 */
	protected void createField (MBrowseField field, int windowNo) {
		GridFieldVO voBase = createVO(field, false, windowNo);
		GridField gField = new GridField(voBase);
		fields.add(gField);
		//	Field Info Only
		fieldsInfoOnly.put(gField.getColumnNameAlias(), field.isInfoOnly());
		//
		if (voBase.IsRange) {
			GridFieldVO voBase_To = createVO(field, true, windowNo);
			GridField gField_To = new GridField(voBase_To);
			//	
			fieldsTo.add(gField_To);
		} else {
			fieldsTo.add (null);
		}
	}	//	createField

	/**
	 *	Creates model gridFields which will accessible via the {@link #fields fields} list.
	 *  The {@link #fieldsTo fieldsTo} list will contain the matching "to" range or null.<br>
	 *  <br>
	 *  This function should be called by the view's {@link #loadData() loadData()} function. 
	 *  @param MProcessPara - the process parameter used to create the field
	 *  @param windowNo the Window number where the field will appear. This 
	 *  is required to ensure the context variables are set correctly for
	 *  the created field.
	 *  
	 *   @see org.compiere.model.GridFieldVO#createParameter(java.util.Properties, int, ResultSet)
	 *   
	 */
	protected void createField (MProcessPara processParameter, int windowNo) {
		//  Create Field
		GridFieldVO voF = GridFieldVO.createParameter(Env.getCtx(), windowNo, processParameter);
		GridField field = new GridField (voF);
		GridField fieldTo = null;
		fields.add(field);                      //  add to Fields
		//
		if (voF.IsRange) {
			//
			GridFieldVO voF2 = GridFieldVO.createParameter(voF);
			//	BR [ 298 ]
			voF2.DefaultValue = voF2.DefaultValue2;
			//	Change Name
			voF2.ColumnName = voF2.ColumnName + "_To";
			//	
			fieldTo = new GridField (voF2);
			//	
			fieldsTo.add (fieldTo);
		} else {
			fieldsTo.add (null);
		}
	}	//	createField

	/**
	 * Create GridFieldVO from a MBrowseField field
	 * @param field the MBrowseField
	 * @param isTo true if the MBrowseField represents the "To" part of a range
	 * @param windowNo - the window number that will be used in the context
	 * @return GridFieldVO used to establish a GridField field.
	 */
	private GridFieldVO createVO(MBrowseField field, boolean isTo, int windowNo) {
		//  Create Field
		GridFieldVO voBase = GridFieldVO.createStdField(field.getCtx(),
				windowNo, 0, 0, 0, false, false, false);

		String uniqueName = field.getAD_View_Column().getColumnName() + (isTo? "_To": "");
		
		voBase.isProcess = true;
		voBase.IsDisplayed = true;
		voBase.IsReadOnly = false;
		voBase.IsUpdateable = true;
		voBase.WindowNo = windowNo;
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
		if(field.getFieldLength() == 0) {
			field.setFieldLength(MAXDISPLAY_LENGTH);
		}
		voBase.FieldLength = field.getFieldLength();
		voBase.DisplayLength = field.getFieldLength();
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
		voBase.FieldLength = field.getFieldLength();
		voBase.DisplayLength = field.getFieldLength();
		voBase.initFinish();
		//	Return 
		return voBase;
	}
	
	/**
	 *	Swing Editor Listener. Swing editors fire VetoableChange events when the user makes
	 *  a change.  The controller has the option of accepting the change and setting the 
	 *  field value accordingly or vetoing the change by throwing a PropertyVetoException.<br>
	 *  <br>
	 *  For ZK editors, see the equivalent {@link #valueChange(ValueChangeEvent)}
	 *	@param evt Event
	 * 	@exception PropertyVetoException if the controller wishes to roll back.
	 * 
	 */
	public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
		
		log.fine(evt.getPropertyName() + "=" + evt.getNewValue() + " (" + evt.getOldValue() + ") "
			+ (evt.getOldValue() == null ? "" : evt.getOldValue().getClass().getName()));

		//	Which GridField needs to know about this change?
		GridField changedField = null;
		if (evt.getSource() instanceof CEditor) {
			changedField = ((CEditor) evt.getSource()).getField();
		}
		else
			return;

		//  Multiple selection should not be enabled

		//  Sync the field with the editor.  Setting the field value will
		//  fire events that will trigger the dynamic display of other fields.
		
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
			// The new value is not null or an empty string - save the new value only if it 
			// is different than the old value or ignore the change
			if (evt.getNewValue() != null && !evt.getNewValue().equals(evt.getOldValue()))
				changedField.setValue(evt.getNewValue(),false);	//	-> PropertyChanged -> dynamicDisplay
		}
	}	//	vetoableChange
	
	/** 
	 *  This is a semi-recursive function to set field default values based on changes in other fields
	 *  or the context.  It cycles through all fields and changes the value to the updated defaults where
	 *  it makes sense to do so.  For read/write enabled fields, user input or initial default values will
	 *  not be overridden.  The recursive part of the function occurs when events are fired as a result of 
	 *  the change in default values which cause other fields to call this function again.<br>
	 *  <br>
	 *  There is a chance of an infinite loop if two or more read-only fields have defaults based on
	 *  the values of each other and the values are not stable.  
	 *
	 */  
	protected void dynamicDisplay() {
		
		for (int index = 0; index < fields.size(); index++) {
			GridField field = getField(index);
			if (field != null) {
				boolean rw = !field.isReadOnly(); // r/w - check if field is Editable
				if (field.isDisplayed(true)) { // check context
					setComponentVisibility(index, true, field.isRange()); // visibility
					rw = field.isEditablePara(true);
					setEditor_RW(index, rw);
					Boolean valueSet = false;
					Object value = field.getValue();
					
					// If the field is null, test the defaults again
					if (value == null || value.toString().length() == 0  					// If the field is not set 
						|| !m_IsLoaded 														// or we are initializing
						|| !rw) {     														// or the field is read only, then ...
						// Try to reset the default value - could be null
						// Don't want to overwrite a user input, so if the field is rw and has
						// a value, don't change it.
						Object defaultValue = field.getDefault();
						if (defaultValue != null && field.getOldValue() == null && (value == null || !value.equals(defaultValue))) {
							// Set the context and fire events if there is a change in value.
							// Setting the field value to null fires events even if the field is
							// already null so don't set null if the value is already null.
							field.setValue(defaultValue, false);  // Not inserting - overwriting the current value
							// The property change events fired will call this function recursively so
							// we can bail out of this loop as it will be completed by the subsequent calls
							valueSet = true;

							// Check for valid values, mandatory (similar to GridController)
							boolean manMissing = false;
							boolean noValue = false;
							noValue = field.getValue() == null || field.getValue().toString().length() == 0;
							if (noValue && field.isEditable(true) && field.isMandatory(true))    //  check context
								manMissing = true;
							CEditor editor = getEditor(index);
							if (editor != null)
								editor.setBackground(manMissing || field.isError());
						}
	
						if (field.isRange() && getFieldTo(index) != null) {
							GridField fieldTo = getFieldTo(index);
							rw = fieldTo.isEditablePara(true);
							setEditorTo_RW(index,rw);
							Object valueTo = fieldTo.getValue();
							if (valueTo == null 
									|| valueTo.toString().length() == 0 
									|| !m_IsLoaded 
									|| (!rw && DisplayType.isNumeric(fieldTo.getVO().displayType))) {
								Object defaultValueTo = fieldTo.getDefault();
								if (defaultValueTo != null && fieldTo.getOldValue() == null && (valueTo == null || !valueTo.equals(defaultValueTo))) {
									fieldTo.setValue(defaultValueTo, false);  // Not inserting - overwriting the current value
									valueSet = true;
									// Check for valid values, mandatory (similar to GridController)
									boolean manMissing = false;
									boolean noValue = false;
									noValue = fieldTo.getValue() == null || fieldTo.getValue().toString().length() == 0;
									if (noValue && fieldTo.isEditable(true) && fieldTo.isMandatory(true))    //  check context
										manMissing = true;
									CEditor editorTo = getEditorTo(index);
									if (editorTo != null)
										editorTo.setBackground(manMissing || fieldTo.isError());
								}
							}
						}
						
						if (valueSet)
							break;
					}
				} else {
					setComponentVisibility(index, false, field.isRange()); // visibility
				}
			}
		}
	} // Dynamic Display.
	
	/**
	 * This function is called when an a field changes value and fires a Property Change Event.
	 * @param field
	 * @param newValue
	 * @param propertyName
	 */
	private void fieldChange(GridField field, Object newValue, String propertyName) {
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
		String columnName = Util.isEmpty(changedField.getColumnNameAlias()) ? changedField.getColumnName() : changedField.getColumnNameAlias();

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
	 * Verify if a field should change because a dependent field has changed.  This
	 * mainly affects the lookup values but may change the selected value if the 
	 * current value is no longer valid.
	 * @param field
	 * @param columnName
	 */
	private void verifyChangedField(GridField field, String columnName) {
		ArrayList<String> list = field.getDependentOn();
		if (list.contains(columnName)) {
			Object value = field.getValue();
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
				if (mLookup.containsKey(value))
				{
					// Try to select the value
					mLookup.setSelectedItem(mLookup.get(value));
				}
				else {
					field.setValue(field.getDefault(),false);
				}
					
			}
		}
	}

	/**
	 * Validate all fields for values and mandatory
	 * @return null if nothing happens
	 */
	public String validateFields() {
		log.config("");

		StringBuffer sb = new StringBuffer();
		int size = fields.size();
		for (int i = 0; i < size; i++) {
			GridField field = fields.get(i);
			//	FR [ 566 ] Only Information
			if(field == null 
					|| field.isInfoOnly()
					|| !field.isDisplayed(true))
				continue;
			// field.validateValue tests for mandatory values and correct lookup selection
			// if there is an error, the field's error flag will be set
			if (!field.validateValue()) {
				if (sb.length() > 0)
					sb.append(", ");
				sb.append(field.getHeader());
				CEditor editor = editors.get(i);
				if (editor != null)
					editor.setBackground(field.isError());
			}
				
			//  Check for Range
			GridField fieldTo = fieldsTo.get(i);
			//	Validate
			if (fieldTo != null && !fieldTo.validateValue()) {
				if (sb.length() > 0)
					sb.append(", ");
				sb.append(fieldTo.getHeader());
				CEditor editor = editorsTo.get(i);
				if (editor != null)
					editor.setBackground(fieldTo.isError());
			}   //  range field
		}   //  field loop
		//	Valid if exists a error
		if (sb.length() != 0) {
			return sb.toString();
		}
		//	Nothing happened
		return null;
	}	//	validateFields

	
	/**
	 * A convenience function for {@link #validateFields()}.
	 * @return An error string or null
	 */
	public String validateParameters() {
		return validateFields();
	}

	
	/**
	 *  Dispose
	 */
	public void dispose() {
		if (isLoaded()) {
			fields.clear();
			fieldsTo.clear();
			editors.clear();
			editorsTo.clear();
			setLoaded(false);
		}
	}   //  dispose	

    /**
     * Set Parameter of Search Panel
     * @param name
     * @param value
     */
    private void setParameter(String name, Object value) {
    	
        if (value != null
                && !isInfoOnly(name)) {
            m_search.put(name, value);
        }
    }

    /**
     * Get Parameters of Search Panel
     * @return the LinkedHasMap<String columnName, Object editor>
     */
    public LinkedHashMap<String, Object> getParameters() {
		if (m_search == null)
			return new LinkedHashMap<String, Object>();
        return m_search;
    }
        
	/**
	 * Is Information only
	 * @param columnName
	 * @return
	 */
	public boolean isInfoOnly(String columnName) {
		if (columnName == null || columnName.length() == 0)
			return false;
		Boolean isInfoOnly = fieldsInfoOnly.get(columnName);
		return isInfoOnly != null && isInfoOnly;
	}
				
	private void setEditor_RW(int index, Boolean readWrite) {
		CEditor editor = (CEditor) getEditor(index);
		editor.setReadWrite(readWrite);
	}

	private void setEditorTo_RW(int index, Boolean readWrite) {
		CEditor editorTo = (CEditor) getEditorTo(index);
		editorTo.setReadWrite(readWrite);
	}
	
	private void processNewValue(Object value, String name) {
		dynamicDisplay();
	}

	/**
	 * @return true if the controller was initialized fully
	 */
	protected boolean isLoaded() {
		return m_IsLoaded;
	}

	/**
	 * @param loaded the m_IsLoaded to set
	 */
	protected void setLoaded(boolean loaded) {
		m_IsLoaded = loaded;
	}

	/** 
	 * This function responds to PropertyChangeEvents that are fired by GridFields when the value is set.
	 * The editors and the SmallViewController are both listeners.  The SmallViewController uses
	 * the property events to change the value in dependent fields and set default values were 
	 * necessary.
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		GridField changedField = null;
		//	Set GridField
		if (evt.getSource() instanceof GridField) {
			changedField = ((GridField) evt.getSource());
		} else {
			return;
		}
		//	Change Dependents
		fieldChange(changedField, evt.getNewValue(), evt.getPropertyName());
	}
	
	/**
	 *	Editor Listener - ZK.  SWING provides vetoable change support but ZK doesn't.  In ZK, the
	 *  ValueChangeEvent is used to pass field changes to the editors.
	 *	@param evt ValueChangeEvent	 
	 */
	public void valueChange(ValueChangeEvent evt) {
		GridField changedField = null;
		//	Set GridField
		if (evt.getSource() instanceof CEditor) {
			changedField = ((CEditor) evt.getSource()).getField();
		}
		
		// Set the field value here for ZK as in the vetoableChange function
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
				throw new AdempiereException("FillMandatory");
		}
		else
		{
			// The new value is not null or an empty string - save if it is different than the
			// old value or ignore the change
			if (evt.getNewValue() != null && !evt.getNewValue().equals(evt.getOldValue()))
				changedField.setValue(evt.getNewValue(),false);	//	-> PropertyChanged -> dynamicDisplay
		}
	}

	/**
	 * Refresh the Context and set the values of fields/editors in the view
	 */
	public void refreshContext() {
		// This is the same as dynamicDisplay()
		dynamicDisplay();
 	}

	/**
	 * Restore window context.
	 * @author teo_sarca [ 1699826 ]
	 * @see org.compiere.model.GridField#restoreValue()
	 */
	public void restoreContext() {
		for (int i = 0; i < fields.size(); i++) {
			//	Get Values
			GridField field = (GridField) fields.get(i);
			GridField fieldTo = (GridField) fieldsTo.get(i);
			//	Restore
			if (field != null)
				field.restoreValue();
			//	Restore To Value
			if (fieldTo != null)
				fieldTo.restoreValue();
		}
	}

	/**
	 * Get the display value of the index editor.  Note that this is not the 
	 * value, just the display text.
	 * @param index
	 * @return A string of the text displayed by the editor.
	 */
	protected String getDisplay(int index) {
		CEditor editor = (CEditor) getEditor(index);
		if(editor != null)
			return editor.getDisplay();
		//	Default
		return null;
	}
	
	/**
	 * Get the display value of the index editor "To".  Note that this is not the 
	 * value, just the display text.
	 * @param index
	 * @return A string of the text displayed by the editor.
	 */	
	protected String getDisplay_To(int index) {
		CEditor editor = (CEditor) getEditorTo(index);
		if(editor != null)
			return editor.getDisplay();
		//	Default
		return null;
	}

	/**
	 * Get the value of the index field.
	 * @param index
	 * @return An object representing the value or null if the field is null.
	 */
	protected Object getValue(int index) {
		if(getField(index) != null)
			return getField(index).getValue();
		//	Default
		return null;
	}

	/**
	 * Get the value of the index field "To".
	 * @param index
	 * @return An object representing the value or null if the field is null.
	 */	
	protected Object getValue_To(int index) {
		if(getFieldTo(index) != null)
			return getFieldTo(index).getValue();
		//	Default
		return null;
	}

	/**
	 * Set the indexed field/editor to the value
	 * @param index
	 * @param value
	 */
	protected void setValue(int index, Object value) {
		if(getField(index) != null)
			getField(index).setValue(value, false); // will fire events to update editors
	}

	/**
	 * Set the indexed fieldTo/editorTo to the value
	 * @param index
	 * @param value
	 */	
	protected void setValue_To(int index, Object value) {
		if(getFieldTo(index) != null)
			getFieldTo(index).setValue(value, false); // will fire events to update editors
	}	
}
