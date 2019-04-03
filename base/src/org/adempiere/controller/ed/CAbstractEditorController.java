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
package org.adempiere.controller.ed;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.Lookup;
import org.compiere.model.MQuery;
import org.compiere.swing.CEditor;

/**
 * An abstract controller of view editors for specific fields.  The controller manages the
 * interactions with the model in a consistent way across all view types.
 * 
 * @author Michael McKay, mckayERP
 *
 */
public abstract class CAbstractEditorController implements CEditorController {

	/** The GridTab where the editor resides */
	protected GridTab gridTab;
	
	/** The GridField associated with the editor */
	protected GridField gridField;
	
	/** The window number used for this editor */
	protected int windowNo;
	
	/** The lookup for this field */
	protected Lookup lookup;

	/** Flag for read only state */
	protected boolean readOnly = false;

	/** Flag for updateable state */
	protected boolean isUpdateable = true;

	/** Flag for the mandatory state */
	protected boolean mandatory = false;

	/**	Data Value				*/
	private Object value = new Object();

	/** Record of the value for comparison at a point in the future */
	protected Object oldValue = 0;
	
	/**	Calling Window Info				*/
	protected int ad_column_id = 0;
	
	/** Column Name	*/
	protected String	columnName;

	protected CEditor editor;

	protected boolean isButtonEnabled = false;

	protected boolean isDisplayEnabled = false;
	
	MQuery zoomQuery = null;
	String zoomKeyTableName;
	String zoomKeyColumnName;
	int zoomAD_Window_ID;

	/** The last display value.  The text displayed can change without the underlying
	 *  value changing so this variable provides a means to test if a change has occurred.
	 */
	protected String lastDisplay;
	
	/**
	 * Standard constructor where the editor is not bound to a tab or model field
	 */
	public CAbstractEditorController() {
		gridTab = null;
		setGridField(null);
		setLookup(null);
		windowNo = 0;
	}

	/**
	 * Standard constructor where the editor is in a tab but the model field has not 
	 * been identified yet.
	 * @param tab - the model GridTab controlling the field
	 * @param windowNo - the window number for this editor
	 */
	public CAbstractEditorController(CEditor editor, GridTab tab, boolean mandatory, boolean isReadOnly,
			boolean isUpdateable, int windowNo, Lookup lookup) {
		gridTab = tab;
		gridField = null;  // set directly rather than using the setter function.
		this.editor = editor;
		this.setMandatory(mandatory);
		this.setReadOnly(isReadOnly);
		this.setUpdateable(isUpdateable);
		this.windowNo = windowNo;
		this.setLookup(lookup); 

	}

	/**
	 * Standard constructor where the editor is in a tab but the model field has not 
	 * been identified yet.
	 * @param editor - the generic editor representing this field
	 * @param tab - the model GridTab controlling the field
	 * @param field - the model GridField for this editor
	 */
	public CAbstractEditorController(CEditor editor, GridTab tab, GridField field) {
		this(tab,field);
		this.editor = editor;
	}

	/**
	 * Private constructor where the editor is in a tab but the model field has not 
	 * been identified yet.  The editor has to be defined by the public constructors
	 * @param tab - the model GridTab controlling the field
	 * @param field - the model GridField for this editor
	 * @param windowNo - the window number for this editor 
	 */
	private CAbstractEditorController(GridTab tab, GridField field) {
		gridTab = tab;
		setGridField(field);

		if (gridTab != null) {
			this.windowNo = gridTab.getWindowNo();
		}
		
		if (gridField != null) {
			this.setLookup(gridField.getLookup());
		}
		
	}
	
	public void init() {
		
		set_oldValue();
		editor.setReadWrite(!readOnly && isUpdateable);
	}
	
	public void dispose() {
		gridTab = null;
		gridField = null;
		lookup.dispose();
		setLookup(null);
	}

	/**
	 * @return the columnName
	 */
	public String getColumnName() {
		return columnName;
	}

	/**
	 * @param columnName the columnName to set
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	/**
	 * 	Set GridField
	 * 	@param field MField
	 */
	public void setGridField(GridField field)
	{
		gridField = field;
		
		if (gridField != null) {
			columnName = gridField.getColumnName();
			ad_column_id = gridField.getAD_Column_ID();
			mandatory = gridField.isMandatory(false);
			readOnly = gridField.isReadOnly();
			setLookup(field.getLookup());
		}
		else {
			columnName = "";
			ad_column_id = 0;
			mandatory = false;
			readOnly = false;
			lookup = null;			
		}
	}	//	setField
	
	public GridField getGridField() {
		return gridField;
	}

	/**
	 * Get the old value of the field explicitly set in the past
	 * @return
	 */
	public Object get_oldValue() {
		return oldValue;
	}

	/**
	 * Set the old value of the field.  For use in future comparisons.
	 * The old value must be explicitly set though this call.
	 */
	public void set_oldValue() {
		this.oldValue = getValue();
	}

	/**
	 * Has the field changed over time?
	 * @return true if the old value is different than the current.
	 */
	public boolean hasChanged() {
		// Both or either could be null
		
		//Don't think a test of Value is needed as value is not set by the search window
		if(getValue() != null)
			if(oldValue != null)
				return !oldValue.equals(getValue());
			else
				return true;
		else  // getValue() is null
			if(oldValue != null)
				return true;
		return false;
	}
	
	/**
	 * 	Set Mandatory
	 * 	@param mandatory mandatory
	 */
	public void setMandatory (boolean mandatory)
	{
		this.mandatory = mandatory;
	}	//	setMandatory

	/**
	 * 	Get Mandatory
	 *  @return mandatory
	 */
	public boolean isMandatory()
	{
		return mandatory;
	}	//	isMandatory

	/**
	 * @return the readOnly
	 */
	public boolean isReadOnly() {
		return readOnly;
	}

	/**
	 * @param readOnly the readOnly to set
	 */
	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	/**
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(Object value) {
		this.value = value;
	}

	/** 
	 * Set the editor error flags
	 */
	public abstract void setError();

	/**
	 * @return the lastDisplay
	 */
	public String getLastDisplay() {
		return lastDisplay;
	}

	/**
	 * @param lastDisplay the lastDisplay to set
	 */
	public void setLastDisplay(String lastDisplay) {
		this.lastDisplay = lastDisplay;
	}
	
	public void generateZoomQuery() {
		
		if (zoomKeyTableName == null || zoomKeyTableName.isEmpty()
				|| zoomKeyColumnName == null || zoomKeyColumnName.isEmpty())
			throw new AdempiereException("Can't zoom. Unknown zoom table and key column");
		
		zoomQuery = new MQuery();
		Object value = getValue();
		if (value == null)
			value = Integer.valueOf(0);

		zoomQuery.addRestriction(zoomKeyColumnName, MQuery.EQUAL, value);
		zoomQuery.setZoomColumnName(zoomKeyColumnName);
		zoomQuery.setZoomTableName(zoomKeyTableName);
		zoomQuery.setZoomValue(value);
		zoomQuery.setRecordCount(1);	//	guess

		zoomAD_Window_ID = lookup.getZoom(zoomQuery);
	}

	/**
	 * @return the zoomKeyTableName
	 */
	protected String getZoomKeyTableName() {
		return zoomKeyTableName;
	}

	/**
	 * @param zoomKeyTableName the zoomKeyTableName to set
	 */
	protected void setZoomKeyTableName(String zoomKeyTableName) {
		this.zoomKeyTableName = zoomKeyTableName;
	}

	/**
	 * @return the zoomKeyColumnName
	 */
	protected String getZoomKeyColumnName() {
		return zoomKeyColumnName;
	}

	/**
	 * @param zoomKeyColumnName the zoomKeyColumnName to set
	 */
	protected void setZoomKeyColumnName(String zoomKeyColumnName) {
		this.zoomKeyColumnName = zoomKeyColumnName;
	}

	/**
	 * @return the zoomAD_Window_ID
	 */
	public int getZoomAD_Window_ID() {
		return zoomAD_Window_ID;
	}

	/**
	 * @param zoomAD_Window_ID the zoomAD_Window_ID to set
	 */
	protected void setZoomAD_Window_ID(int zoomAD_Window_ID) {
		this.zoomAD_Window_ID = zoomAD_Window_ID;
	}

	/**
	 * @return the zoomQuery
	 */
	public MQuery getZoomQuery() {
		if (zoomQuery == null)
			throw new AdempiereException("Zoom Query not initialized.");
		return zoomQuery;
	}

	/**
	 * @param zoomQuery the zoomQuery to set
	 */
	protected void setZoomQuery(MQuery zoomQuery) {
		this.zoomQuery = zoomQuery;
	}

	/**
	 * @return the windowNo
	 */
	public int getWindowNo() {
		return windowNo;
	}

	/**
	 * @param windowNo the windowNo to set
	 */
	protected void setWindowNo(int windowNo) {
		this.windowNo = windowNo;
	}

	/**
	 * @return the isUpdateable
	 */
	public boolean isUpdateable() {
		return isUpdateable;
	}

	/**
	 * @param isUpdateable the isUpdateable to set
	 */
	protected void setUpdateable(boolean isUpdateable) {
		this.isUpdateable = isUpdateable;
	}

	/**
	 * @return the lookup
	 */
	public Lookup getLookup() {
		return lookup;
	}


	/**
	 * @param lookup the lookup to set
	 */
	protected void setLookup(Lookup lookup) {
		this.lookup = lookup;
	}

}
