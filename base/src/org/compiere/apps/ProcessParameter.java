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
package org.compiere.apps;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;

import org.adempiere.exceptions.DBException;
import org.compiere.model.GridField;
import org.compiere.model.GridFieldVO;
import org.compiere.model.MClient;
import org.compiere.model.MLookup;
import org.compiere.model.MPInstance;
import org.compiere.model.MPInstancePara;
import org.compiere.process.ProcessInfo;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 *	Controller for Process Parameter, it allow to developer create different views from it
 *	@author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li>FR [ 265 ] ProcessParameterPanel is not MVC
 *		@see https://github.com/adempiere/adempiere/issues/265
 *		<li>FR [ 295 ] Report viewer re-query
 *		@see https://github.com/adempiere/adempiere/issues/295
 */
public abstract class ProcessParameter {
	
	/**
	 *	Dynamic generated Parameter panel.
	 *  @param WindowNo window
	 *  @param pi process info
	 *  @param columns
	 */
	public ProcessParameter(int WindowNo, ProcessInfo pi, int columns) {
		//	Get parameters
		m_WindowNo = WindowNo;
		m_processInfo = pi;
		m_mFields = new ArrayList<GridField>();
		m_mFields_To = new ArrayList<GridField>();
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
	 * @param pi
	 */
	public ProcessParameter(int WindowNo, ProcessInfo pi) {
		this(WindowNo, pi, COLUMNS_1);
	}

	private int			m_WindowNo;
	private ProcessInfo m_processInfo;
	private boolean 	m_IsError;
	private boolean 	m_HasParameters;
	private int 		m_Columns;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(ProcessParameter.class);
	//
	private ArrayList<GridField>	m_mFields;
	private ArrayList<GridField>	m_mFields_To;
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
		m_mFields.clear();
		m_mFields_To.clear();
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
		// ASP
		MClient client = MClient.get(Env.getCtx());
		String ASPFilter = "";
		if (client.isUseASP())
			ASPFilter =
				  "   AND (   p.AD_Process_Para_ID IN ( "
				// Just ASP subscribed process parameters for client "
				+ "              SELECT pp.AD_Process_Para_ID "
				+ "                FROM ASP_Process_Para pp, ASP_Process p, ASP_Level l, ASP_ClientLevel cl "
				+ "               WHERE p.ASP_Level_ID = l.ASP_Level_ID "
				+ "                 AND cl.AD_Client_ID = " + client.getAD_Client_ID()
				+ "                 AND cl.ASP_Level_ID = l.ASP_Level_ID "
				+ "                 AND pp.ASP_Process_ID = p.ASP_Process_ID "
				+ "                 AND pp.IsActive = 'Y' "
				+ "                 AND p.IsActive = 'Y' "
				+ "                 AND l.IsActive = 'Y' "
				+ "                 AND cl.IsActive = 'Y' "
				+ "                 AND pp.ASP_Status = 'S') " // Show
				+ "        OR p.AD_Process_Para_ID IN ( "
				// + show ASP exceptions for client
				+ "              SELECT AD_Process_Para_ID "
				+ "                FROM ASP_ClientException ce "
				+ "               WHERE ce.AD_Client_ID = " + client.getAD_Client_ID()
				+ "                 AND ce.IsActive = 'Y' "
				+ "                 AND ce.AD_Process_Para_ID IS NOT NULL "
				+ "                 AND ce.AD_Tab_ID IS NULL "
				+ "                 AND ce.AD_Field_ID IS NULL "
				+ "                 AND ce.ASP_Status = 'S') " // Show
				+ "       ) "
				+ "   AND p.AD_Process_Para_ID NOT IN ( "
				// minus hide ASP exceptions for client
				+ "          SELECT AD_Process_Para_ID "
				+ "            FROM ASP_ClientException ce "
				+ "           WHERE ce.AD_Client_ID = " + client.getAD_Client_ID()
				+ "             AND ce.IsActive = 'Y' "
				+ "             AND ce.AD_Process_Para_ID IS NOT NULL "
				+ "             AND ce.AD_Tab_ID IS NULL "
				+ "             AND ce.AD_Field_ID IS NULL "
				+ "             AND ce.ASP_Status = 'H')"; // Hide
		//
		String sql = null;
		if (Env.isBaseLanguage(Env.getCtx(), "AD_Process_Para"))
			sql = "SELECT p.Name, p.Description, p.Help, "
				+ "p.AD_Reference_ID, p.AD_Process_Para_ID, "
				+ "p.FieldLength, p.IsMandatory, p.IsRange, p.ColumnName, "
				+ "p.DefaultValue, p.DefaultValue2, p.VFormat, p.ValueMin, p.ValueMax, "
				+ "p.SeqNo, p.AD_Reference_Value_ID, vr.Code AS ValidationCode, p.ReadOnlyLogic, p.DisplayLogic "
				+ "FROM AD_Process_Para p"
				+ " LEFT OUTER JOIN AD_Val_Rule vr ON (p.AD_Val_Rule_ID=vr.AD_Val_Rule_ID) "
				+ "WHERE p.AD_Process_ID=?"		//	1
				+ " AND p.IsActive='Y' "
				+ ASPFilter + " ORDER BY SeqNo";
		else
			sql = "SELECT t.Name, t.Description, t.Help, "
				+ "p.AD_Reference_ID, p.AD_Process_Para_ID, "
				+ "p.FieldLength, p.IsMandatory, p.IsRange, p.ColumnName, "
				+ "p.DefaultValue, p.DefaultValue2, p.VFormat, p.ValueMin, p.ValueMax, "
				+ "p.SeqNo, p.AD_Reference_Value_ID, vr.Code AS ValidationCode, p.ReadOnlyLogic, p.DisplayLogic "
				+ "FROM AD_Process_Para p"
				+ " INNER JOIN AD_Process_Para_Trl t ON (p.AD_Process_Para_ID=t.AD_Process_Para_ID)"
				+ " LEFT OUTER JOIN AD_Val_Rule vr ON (p.AD_Val_Rule_ID=vr.AD_Val_Rule_ID) "
				+ "WHERE p.AD_Process_ID=?"		//	1
				+ " AND t.AD_Language='" + Env.getAD_Language(Env.getCtx()) + "'"
				+ " AND p.IsActive='Y' "
				+ ASPFilter + " ORDER BY SeqNo";
		
		//	Create Fields
		boolean hasFields = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, m_processInfo.getAD_Process_ID());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				hasFields = true;
				createField (rs);
			}
		} catch(SQLException e) {
			throw new DBException(e, sql);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		//	
		return hasFields;
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
			for (int i = 0; i < m_mFields.size(); i++) {
				//	Get Values
				GridField field = (GridField) m_mFields.get(i);
				GridField field_To = (GridField) m_mFields_To.get(i);
				//	Create Fields
				createViewField(field, field_To);
			}
			//	
			dynamicDisplay();
			return true;
		}
		//	
		dispose();
		//	Default
		return false;
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
	 * @param rs result set
	 */
	private void createField (ResultSet rs) {
		//  Create Field
		GridFieldVO voF = GridFieldVO.createParameter(Env.getCtx(), m_WindowNo, rs);
		GridField field = new GridField (voF);
		GridField field_To = null;
		m_mFields.add(field);                      //  add to Fields
		//
		if (voF.isRange) {
			//
			GridFieldVO voF2 = GridFieldVO.createParameter(voF);
			//	Change Name
			voF2.ColumnName = voF2.ColumnName + "_To";
			//	
			field_To = new GridField (voF2);
			//	
			m_mFields_To.add (field_To);
		} else {
			m_mFields_To.add (null);
		}
	}	//	createField

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
		String columnName = changedField.getColumnName();

		for (GridField field : m_mFields) {
			if (field == null || field == changedField)
				continue;
			verifyChangedField(field, columnName);
		}
		for (GridField field : m_mFields_To) {
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
					field.setValue(null, true);
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
		if (value == null)
			value = new String("");

		if (value instanceof String)
			Env.setContext(Env.getCtx(), m_WindowNo, name, (String) value);
		else if (value instanceof Integer)
			Env.setContext(Env.getCtx(), m_WindowNo, name, ((Integer) value)
					.intValue());
		else if (value instanceof Boolean)
			Env.setContext(Env.getCtx(), m_WindowNo, name, ((Boolean) value)
					.booleanValue());
		else if (value instanceof Timestamp)
			Env.setContext(Env.getCtx(), m_WindowNo, name, (Timestamp) value);
		else
			Env.setContext(Env.getCtx(), m_WindowNo, name, value.toString());

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
		int size = m_mFields.size();
		for (int i = 0; i < size; i++) {
			GridField mField = (GridField) m_mFields.get(i);
			//	Validate
			mField.validateValue();
			//	check context
			if (mField.isMandatory(true)) {
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
				GridField mField_To = (GridField) m_mFields_To.get(i);
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
			m_processInfo.setSummary(sb.toString());
			m_processInfo.setError(true);
			return sb.toString();
		}
		//	Nothing happened
		return null;
	}	//	validateParameters

	/**
	 * Validate and save parameters if not happened error
	 * @return null if nothing happened
	 */
	public String saveParameters() {
		log.config("");
		//	Valid parameters
		String validError = validateParameters();
		if(validError != null)
			return validError;

		//	Save Process instance if it is not saved
		//	FR [ 295 ]
		if(m_processInfo.getAD_PInstance_ID() == 0) {
			MPInstance instance = null; 
			try { 
				instance = new MPInstance(Env.getCtx(), 
						m_processInfo.getAD_Process_ID(), m_processInfo.getRecord_ID());
				instance.saveEx();
				//	Set Instance
				m_processInfo.setAD_PInstance_ID(instance.getAD_PInstance_ID());
			} catch (Exception e) { 
				m_processInfo.setSummary (e.getLocalizedMessage()); 
				m_processInfo.setError (true); 
				log.warning(m_processInfo.toString()); 
				return m_processInfo.getSummary(); 
			}
		}
		
		/**********************************************************************
		 *	Save Now
		 */
		for (int i = 0; i < m_mFields.size(); i++) {
			//	Get Values
			GridField mField = (GridField) m_mFields.get(i);
			GridField mField_To = (GridField) m_mFields_To.get(i);
			//	Validate
			mField.validateValue();
			Object result = getValue(i);
			Object result2 = null;
			if (mField_To != null) {
				result2 = getValue_To(i);
			}
			
			//	Create Parameter
			MPInstancePara para = new MPInstancePara (Env.getCtx(), m_processInfo.getAD_PInstance_ID(), i);
			para.setParameterName(mField.getColumnName());
			//	
			if (result instanceof Timestamp || result2 instanceof Timestamp) {	//	Date
				para.setP_Date((Timestamp)result);
				if (mField_To != null && result2 != null)
					para.setP_Date_To((Timestamp)result2);
			} else if (result instanceof Integer || result2 instanceof Integer) {	//	Integer
				if (result != null) {
					Integer ii = (Integer)result;
					para.setP_Number(ii.intValue());
				} if (mField_To != null && result2 != null) {
					Integer ii = (Integer)result2;
					para.setP_Number_To(ii.intValue());
				}
			} else if (result instanceof BigDecimal || result2 instanceof BigDecimal) {	//	BigDecimal
				para.setP_Number ((BigDecimal)result);
				if (mField_To != null && result2 != null)
					para.setP_Number_To ((BigDecimal)result2);
			} else if (result instanceof Boolean) {	//	Boolean
				Boolean bb = (Boolean)result;
				String value = bb.booleanValue() ? "Y" : "N";
				para.setP_String (value);
				//	to does not make sense
			} else {	//	String
				if (result != null)
					para.setP_String (result.toString());
				if (mField_To != null && result2 != null)
					para.setP_String_To (result2.toString());
			}
			//  Info
			para.setInfo (getDisplay(i));
			if (mField_To != null)
				para.setInfo_To (getDisplay_To(i));
			//
			para.saveEx();
			log.fine(para.toString());
		}	//	for every parameter

		return null;
	}	//	saveParameters
	
	/**
	 * Load values from saved parameters
	 * @param instance
	 * @return
	 */
	public boolean loadParameters(MPInstance instance) {
		log.config("");
		//	
		MPInstancePara[] params = instance.getParameters();
		for (int j = 0; j < m_mFields.size(); j++)
		{
			//	Get Values
			GridField mField = (GridField) m_mFields.get(j);
			GridField mField_To = (GridField) m_mFields_To.get(j);
			//	Set Values
			setValue(j, null);
			if (mField_To != null)
				setValue_To(j, null);

			for ( int i = 0; i < params.length; i++)
			{
				MPInstancePara para = params[i];
				para.getParameterName();

				if ( mField.getColumnName().equals(para.getParameterName()) )
				{
					if (para.getP_Date() != null || para.getP_Date_To() != null )
					{
						setValue(j, para.getP_Date());
						if (mField_To != null)
							setValue_To(j, para.getP_Date_To());
					}

					//	String
					else if ( para.getP_String() != null || para.getP_String_To() != null )
					{
						setValue(j, para.getP_String());
						if (mField_To != null)
							setValue_To(j, para.getP_String_To());
					}
					else if ( !Env.ZERO.equals(para.getP_Number()) || !Env.ZERO.equals(para.getP_Number_To()) )
					{
						setValue(j, para.getP_Number());
						if (mField_To != null)
							setValue_To(j, para.getP_Number_To());
					}

					log.fine(para.toString());
					break;
				}
			} // for every saved parameter
		}	//	for every field
		return true;
	}
	
	/**
	 * Restore window context.
	 * @author teo_sarca [ 1699826 ]
	 * @see org.compiere.model.GridField#restoreValue()
	 */
	public void restoreContext() {
		for (int i = 0; i < m_mFields.size(); i++) {
			//	Get Values
			GridField mField = (GridField) m_mFields.get(i);
			GridField mField_To = (GridField) m_mFields_To.get(i);
			//	Restore
			if (mField != null)
				mField.restoreValue();
			//	Restore To Value
			if (mField_To != null)
				mField_To.restoreValue();
		}
	}
	
	/**
	 * getIndex. Get m_mFields index from columnName
	 * 
	 * @param columnName
	 * @return int
	 **/
	public int getIndex(String columnName) {

		for (int i = 0; i < m_mFields.size(); i++) {
			if (m_mFields.get(i).getColumnName().equals(columnName)) {
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

		for (int i = 0; i < m_mFields_To.size(); i++) {
			if (m_mFields_To.get(i).getColumnName().equals(columnName)) {
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
		return m_mFields.get(index);
	}
	
	/**
	 * Get Grid Field To
	 * @param index
	 * @return
	 */
	public GridField getField_To(int index) {
		return m_mFields.get(index);
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
	
	/**
	 * Set Process Info
	 * @param processInfo
	 */
	public void setProcessInfo(ProcessInfo processInfo) {
		m_processInfo = processInfo;
	}
		
}	//	ProcessParameterPanel
