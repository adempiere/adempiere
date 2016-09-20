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

import org.adempiere.controller.SmallViewController;
import org.adempiere.exceptions.DBException;
import org.compiere.model.GridField;
import org.compiere.model.MClient;
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
 *		<li>FR [ 352 ] T_Selection is better send to process like a HashMap instead read from disk
 *		@see https://github.com/adempiere/adempiere/issues/352
 *		<li>FR [ 298 ] Process Parameter Panel not set default value correctly into parameters
 *		@see https://github.com/adempiere/adempiere/issues/298
 *	@author Michael Mckay michael.mckay@mckayerp.com
 *		<li>BF [ <a href="https://github.com/adempiere/adempiere/issues/495">495</a> ] Parameter Panel & SmartBrowser criteria do not set gridField value
 */
public abstract class ProcessParameter extends SmallViewController {
	
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
		m_Columns = columns;
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
	private int 		m_Columns;
/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(ProcessParameter.class);
	//	Constants
	/**	For one Column		*/
	public static final int COLUMNS_1 = 1;
	/**	For two Column		*/
	public static final int COLUMNS_2 = 2;
	//
		
		
	/**
	 * Is error
	 * @return
	 */
	public boolean isError() {
		return m_IsError;
	}
		
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
	public boolean loadData() {
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
				createField (rs, m_WindowNo);
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
	public boolean createFieldsAndEditors() {
		//  Create fields and editors and sync them
		return super.init();
	}
		
	
	/**
	 * Validate Parameters
	 * @return null if nothing happens
	 */
	public String validateParameters() {
		log.config("");

		String msg = validateFields();
		//	Valid if there is no message
		if (msg != null && msg.length() > 0) {
			m_processInfo.setSummary(msg);
			m_processInfo.setError(true);
			return msg;
		}
		//	All OK
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
		if(m_processInfo.getAD_PInstance_ID() <= 0) {
			MPInstance instance = null;
			//	Set to null for reload
			//	BR [ 380 ]
			m_processInfo.setParameter(null);
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
		for (int i = 0; i < getFieldSize(); i++) {
			//	Get Values
			GridField field = (GridField) getField(i);
			GridField fieldTo = (GridField) getFieldTo(i);
			//	Validate
			field.validateValue();
			Object result = getValue(i);
			Object result2 = null;
			if (fieldTo != null) {
				result2 = getValue_To(i);
			}
			
			//	Create Parameter
			MPInstancePara para = new MPInstancePara (Env.getCtx(), m_processInfo.getAD_PInstance_ID(), i);
			para.setParameterName(field.getColumnName());
			//	
			if (result instanceof Timestamp || result2 instanceof Timestamp) {	//	Date
				para.setP_Date((Timestamp)result);
				if (fieldTo != null && result2 != null)
					para.setP_Date_To((Timestamp)result2);
			} else if (result instanceof Integer || result2 instanceof Integer) {	//	Integer
				if (result != null) {
					Integer ii = (Integer)result;
					para.setP_Number(ii.intValue());
				} if (fieldTo != null && result2 != null) {
					Integer ii = (Integer)result2;
					para.setP_Number_To(ii.intValue());
				}
			} else if (result instanceof BigDecimal || result2 instanceof BigDecimal) {	//	BigDecimal
				para.setP_Number ((BigDecimal)result);
				if (fieldTo != null && result2 != null)
					para.setP_Number_To ((BigDecimal)result2);
			} else if (result instanceof Boolean) {	//	Boolean
				Boolean bb = (Boolean)result;
				String value = bb.booleanValue() ? "Y" : "N";
				para.setP_String (value);
				//	to does not make sense
			} else {	//	String
				if (result != null)
					para.setP_String (result.toString());
				if (fieldTo != null && result2 != null)
					para.setP_String_To (result2.toString());
			}
			//  Info
			para.setInfo (getDisplay(i));
			if (fieldTo != null)
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
		for (int j = 0; j < getFieldSize(); j++) {
			//	Get Values
			GridField field = (GridField) getField(j);
			GridField fieldTo = (GridField) getFieldTo(j);
			//	Set Values
			setValue(j, null);
			if (fieldTo != null)
				setValue_To(j, null);

			for ( int i = 0; i < params.length; i++)
			{
				MPInstancePara para = params[i];
				para.getParameterName();

				if ( field.getColumnName().equals(para.getParameterName()) )
				{
					if (para.getP_Date() != null || para.getP_Date_To() != null )
					{
						setValue(j, para.getP_Date());
						if (fieldTo != null)
							setValue_To(j, para.getP_Date_To());
					}

					//	String
					else if ( para.getP_String() != null || para.getP_String_To() != null )
					{
						setValue(j, para.getP_String());
						if (fieldTo != null)
							setValue_To(j, para.getP_String_To());
					}
					else if ( !Env.ZERO.equals(para.getP_Number()) || !Env.ZERO.equals(para.getP_Number_To()) )
					{
						setValue(j, para.getP_Number());
						if (fieldTo != null)
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
	 * Get the Window number
	 * @return
	 */
	public int getWindowNo() {
		return m_WindowNo;
	}
		
	/**
	 * Set Process Info
	 * @param processInfo
	 */
	public void setProcessInfo(ProcessInfo processInfo) {
		m_processInfo = processInfo;
	}
	
	/**
	 * Get Process Info
	 * FR [ 352 ]
	 * @return
	 */
	public ProcessInfo getProcessInfo() {
		return m_processInfo;
	}
	
}	//	ProcessParameterPanel
