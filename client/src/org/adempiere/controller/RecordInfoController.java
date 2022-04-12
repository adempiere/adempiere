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
 * Copyright (C) 2003-2015 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpcya.com                                 *
 *****************************************************************************/
package org.adempiere.controller;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.logging.Level;

import org.compiere.model.DataStatusEvent;
import org.compiere.model.GridField;
import org.compiere.model.MChangeLog;
import org.compiere.model.MColumn;
import org.compiere.model.MEntityType;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MRole;
import org.compiere.model.MTable;
import org.compiere.model.MUser;
import org.compiere.model.X_AD_Reference;
import org.compiere.model.X_AD_Val_Rule;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.NamePair;
import org.compiere.util.Util;

/**
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 146 ] Added like controller for RecordInfo
 *		@see https://github.com/adempiere/adempiere/issues/146
 * @author Michael McKay, mckayERP@gmail.com
 * 		<li><A hfre="https://github.com/adempiere/adempiere/issues/2387">#2387</a>Remove Ampersand from translation 
 */
public class RecordInfoController {
	
	/**
	 * Standard Contructor
	 * @param title
	 * @param dse
	 * @param mField
	 */
	public RecordInfoController(String title, DataStatusEvent dse, GridField mField) {
		dynInit(dse, title, mField);
	}

	/**	Logger			*/
	private CLogger		log = CLogger.getCLogger(getClass());
	/** The Data		*/
	private Vector<Vector<String>>	m_data = new Vector<Vector<String>>();
	/** Info			*/
	private StringBuffer	m_info = new StringBuffer();
	/**	Specific field	*/
	private GridField 		m_Field;
	/**	Title			*/
	private String 			m_Title;
	/**	Is Loaded Ok	*/
	private boolean 		m_IsLoaded = false;
	
	/** Date Time Format		*/
	private SimpleDateFormat	m_dateTimeFormat = DisplayType.getDateFormat
		(DisplayType.DateTime, Env.getLanguage(Env.getCtx()));
	/** Date Format			*/
	private SimpleDateFormat	m_dateFormat = DisplayType.getDateFormat
		(DisplayType.DateTime, Env.getLanguage(Env.getCtx()));
	/** Number Format		*/
	private DecimalFormat		m_numberFormat = DisplayType.getNumberFormat
		(DisplayType.Number, Env.getLanguage(Env.getCtx()));
	/** Amount Format		*/
	private DecimalFormat		m_amtFormat = DisplayType.getNumberFormat
		(DisplayType.Amount, Env.getLanguage(Env.getCtx()));
	/** Number Format		*/
	private DecimalFormat		m_intFormat = DisplayType.getNumberFormat
		(DisplayType.Integer, Env.getLanguage(Env.getCtx()));
	
	
	/**
	 * 	Dynamic Init
	 *	@param dse data status event
	 *	@param title title
	 *	@param mField field
	 */
	private void dynInit(DataStatusEvent dse, String title, GridField mField) {
		m_Field = mField;
		//	Yamel Senih FR[ 146 ] Add support to change log in a specific column
		//	2015-12-03
		//	For Field
		int m_Record_ID = 0;
		int m_AD_Table_ID = 0;
		int m_AD_Column_ID = 0;
		String UUID = null;
		if(m_Field != null) {
			//	Set Values
			m_Record_ID = m_Field.getGridTab().getRecord_ID();
			UUID = m_Field.getGridTab().getUUID();
			m_AD_Table_ID = m_Field.getGridTab().getAD_Table_ID();
			m_AD_Column_ID = m_Field.getAD_Column_ID();
			//	
			MColumn column = MColumn.get(Env.getCtx(), m_AD_Column_ID);
			X_AD_Reference reference = new X_AD_Reference(Env.getCtx(), m_Field.getDisplayType(), null);
			DecimalFormat format = DisplayType.getNumberFormat(reference.getAD_Reference_ID());
			MTable table = MTable.get(Env.getCtx(), m_AD_Table_ID);
			StringBuilder infoTable = new StringBuilder();
			infoTable.append("SELECT * FROM ").append(table.getTableName()).append(" WHERE ");
			//  Info
			m_info.append(infoTable).append(m_Field.getColumnName()).append("=").append(m_Field.getValue()).append(";");
			if (UUID != null) {
				m_info.append("\n").append(infoTable).append("UUID='").append(UUID).append("';");
			}

			m_info.append("\n").append(Util.cleanAmp(Msg.translate(Env.getCtx(), "Name")))
				.append(": ").append(m_Field.getHeader()).append("\n")
				.append(Msg.translate(Env.getCtx(), "Description"))
				.append(": ").append(m_Field.getDescription()).append("\n")
				.append(Msg.translate(Env.getCtx(), "AD_Reference_ID"))
				.append(": ").append(reference.get_Translation("Name")).append("\n");
			//	For Reference Key
			if(m_Field.getDisplayType() == DisplayType.List
					|| m_Field.getDisplayType() == DisplayType.Table
					|| m_Field.getDisplayType() == DisplayType.Search) {
				//	Valid Reference Value
				if(m_Field.getAD_Reference_Value_ID() != 0) {
					X_AD_Reference referenceKey = new X_AD_Reference(Env.getCtx(), m_Field.getAD_Reference_Value_ID(), null);
					m_info.append(Msg.translate(Env.getCtx(), "AD_Reference_Value_ID"))
						.append(": ").append(referenceKey.get_Translation("Name"))
						.append(" (").append(m_Field.getAD_Reference_Value_ID()).append(")").append("\n");
				}
			}
			//	Valid Dynamic Validation
			if(column.getAD_Val_Rule_ID() != 0) {
				X_AD_Val_Rule validation = (X_AD_Val_Rule) column.getAD_Val_Rule();
				m_info.append(Msg.translate(Env.getCtx(), "AD_Val_Rule_ID"))
					.append(": ").append(validation.get_Translation("Name"))
					.append(" (").append(column.getAD_Val_Rule_ID()).append(")").append("\n");
			}
			m_info.append(Msg.translate(Env.getCtx(), "Length"))
				.append(": ").append(format.format(column.getFieldLength())).append("\n");
			//	Add Entity Type for info
			MEntityType entity = MEntityType.get(Env.getCtx(), column.getEntityType());
			m_info.append(Msg.translate(Env.getCtx(), "EntityType"))
				.append(": ").append(entity.get_Translation("Name"));
			//	Title
			m_Title = title + " - " + m_Field.getHeader();
		} else {
			if (dse.CreatedBy == null)
				return;
			//  Info
			MUser user = MUser.get(Env.getCtx(), dse.CreatedBy.intValue());
			m_info.append(" ")
				.append(Msg.translate(Env.getCtx(), "CreatedBy"))
				.append(": ").append(user.getName())
				.append(" - ").append(m_dateTimeFormat.format(dse.Created)).append("\n");
			
			if (!dse.Created.equals(dse.Updated) 
				|| !dse.CreatedBy.equals(dse.UpdatedBy))
			{
				if (!dse.CreatedBy.equals(dse.UpdatedBy))
					user = MUser.get(Env.getCtx(), dse.UpdatedBy.intValue());
				m_info.append(" ")
					.append(Msg.translate(Env.getCtx(), "UpdatedBy"))
					.append(": ").append(user.getName())
					.append(" - ").append(m_dateTimeFormat.format(dse.Updated)).append("\n");
			}
			if (dse.Info != null && dse.Info.length() > 0) {
				m_info.append("\n").append(dse.Info);
			}
			
			//	Title
			if (dse.AD_Table_ID != 0)
			{
				m_AD_Table_ID = dse.AD_Table_ID;
				MTable table1 = MTable.get (Env.getCtx(), dse.AD_Table_ID);
				m_Title = title + " - " + table1.getName();
			}
			//	Set Record ID
			if (dse.Record_ID instanceof Integer)
				m_Record_ID = ((Integer)dse.Record_ID).intValue();
			else
				log.info("dynInit - Invalid Record_ID=" + dse.Record_ID);
		}
		//	Valid Record Identifier
		if (m_Record_ID == 0)
			return;

		//	Only Client Preference can view Change Log
		if (!MRole.PREFERENCETYPE_Client.equals(MRole.getDefault().getPreferenceType()))
			return;
		
		//	Data
		String sql = "SELECT AD_Column_ID, Updated, UpdatedBy, OldValue, NewValue "
			+ "FROM AD_ChangeLog "
			+ "WHERE AD_Table_ID=? AND Record_ID=? "
			+ (m_AD_Column_ID != 0? "AND AD_Column_ID=? ": "")
			+ "ORDER BY Updated DESC";
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement (sql, null);
			pstmt.setInt (1, m_AD_Table_ID);
			pstmt.setInt (2, m_Record_ID);
			//	Add support to column
			if(m_AD_Column_ID != 0)
				pstmt.setInt (3, m_AD_Column_ID);
			//	
			ResultSet rs = pstmt.executeQuery ();
			while (rs.next ())
			{
				addLine (rs.getInt(1), rs.getTimestamp(2), rs.getInt(3),
					rs.getString(4), rs.getString(5));
			}
			rs.close ();
			pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		try
		{
			if (pstmt != null)
				pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			pstmt = null;
		}
		//	Set Loaded to Ok
		m_IsLoaded = true;
	}	//	dynInit
	
	/**
	 * Verify if is loaded
	 * @return
	 */
	protected boolean isOk() {
		return m_IsLoaded;
	}
	
	/**
	 * Get Column Names
	 * @return
	 */
	public Vector<String> getColumnNames() {
		//
		Vector<String> columnNames = new Vector<String>();
		//	No add for specific column
		if(m_Field == null) {
			columnNames.add(Util.cleanAmp(Msg.translate(Env.getCtx(), "Name")));
		}
		columnNames.add(Msg.translate(Env.getCtx(), "NewValue"));
		columnNames.add(Msg.translate(Env.getCtx(), "OldValue"));
		columnNames.add(Msg.translate(Env.getCtx(), "UpdatedBy"));
		columnNames.add(Msg.translate(Env.getCtx(), "Updated"));
		//	No add for specific column
		if(m_Field == null) {
			columnNames.add(Msg.translate(Env.getCtx(), "AD_Column_ID"));
		}
		//	Default Return
		return columnNames;
	}
	
	/**
	 * 	Add Line
	 *	@param AD_Column_ID column
	 *	@param Updated updated
	 *	@param UpdatedBy user
	 *	@param OldValue old
	 *	@param NewValue new
	 */
	private void addLine (int AD_Column_ID, Timestamp Updated, int UpdatedBy,
		String OldValue, String NewValue)
	{
		Vector<String> line = new Vector<String>();
		//	Column
		MColumn column = MColumn.get (Env.getCtx(), AD_Column_ID);
		//	No for specific column
		if(m_Field == null) {
			line.add(Util.cleanAmp(Msg.translate(Env.getCtx(), column.getColumnName())));
		}
		//
		if (OldValue != null && OldValue.equals(MChangeLog.NULL))
			OldValue = null;
		String showOldValue = OldValue;
		if (NewValue != null && NewValue.equals(MChangeLog.NULL))
			NewValue = null;
		String showNewValue = NewValue;
		//
		try
		{
			if (DisplayType.isText (column.getAD_Reference_ID ()))
				;
			else if (column.getAD_Reference_ID() == DisplayType.YesNo)
			{
				if (OldValue != null)
				{
					boolean yes = OldValue.equals("true") || OldValue.equals("Y");
					showOldValue = Msg.getMsg(Env.getCtx(), yes ? "Y" : "N");
				}
				if (NewValue != null)
				{
					boolean yes = NewValue.equals("true") || NewValue.equals("Y");
					showNewValue = Msg.getMsg(Env.getCtx(), yes ? "Y" : "N");
				}
			}
			else if (column.getAD_Reference_ID() == DisplayType.Amount)
			{
				if (OldValue != null)
					showOldValue = m_amtFormat
						.format (new BigDecimal (OldValue));
				if (NewValue != null)
					showNewValue = m_amtFormat
						.format (new BigDecimal (NewValue));
			}
			else if (column.getAD_Reference_ID() == DisplayType.Integer)
			{
				if (OldValue != null)
					showOldValue = m_intFormat.format (new Integer (OldValue));
				if (NewValue != null)
					showNewValue = m_intFormat.format (new Integer (NewValue));
			}
			else if (DisplayType.isNumeric (column.getAD_Reference_ID ()))
			{
				if (OldValue != null)
					showOldValue = m_numberFormat.format (new BigDecimal (OldValue));
				if (NewValue != null)
					showNewValue = m_numberFormat.format (new BigDecimal (NewValue));
			}
			else if (column.getAD_Reference_ID() == DisplayType.Date)
			{
				if (OldValue != null)
					showOldValue = m_dateFormat.format (Timestamp.valueOf (OldValue));
				if (NewValue != null)
					showNewValue = m_dateFormat.format (Timestamp.valueOf (NewValue));
			}
			else if (column.getAD_Reference_ID() == DisplayType.DateTime)
			{
				if (OldValue != null)
					showOldValue = m_dateTimeFormat.format (Timestamp.valueOf (OldValue));
				if (NewValue != null)
					showNewValue = m_dateTimeFormat.format (Timestamp.valueOf (NewValue));
			}
			else if (DisplayType.isLookup(column.getAD_Reference_ID ()))
			{
				MLookup lookup = MLookupFactory.get (Env.getCtx(), 0,
					AD_Column_ID, column.getAD_Reference_ID(),
					Env.getLanguage(Env.getCtx()), column.getColumnName(),
					column.getAD_Reference_Value_ID(),
					column.isParent(), null);
				if (OldValue != null)
				{
					Object key = OldValue; 
					NamePair pp = lookup.get(key);
					if (pp != null)
						showOldValue = pp.getName();
				}
				if (NewValue != null)
				{
					Object key = NewValue; 
					NamePair pp = lookup.get(key);
					if (pp != null)
						showNewValue = pp.getName();
				}
			}
			else if (DisplayType.isLOB (column.getAD_Reference_ID ()))
				;
		}
		catch (Exception e)
		{
			log.log(Level.WARNING, OldValue + "->" + NewValue, e);
		}
		//
		line.add(showNewValue);
		line.add(showOldValue);
		//	UpdatedBy
		MUser user = MUser.get(Env.getCtx(), UpdatedBy);
		line.add(user.getName());
		//	Updated
		line.add(m_dateFormat.format(Updated));
		//	Column Name
		//	No for specific column
		if(m_Field == null) {
			line.add(column.getColumnName());
		}
		m_data.add(line);
	}	//	addLine
	
	/**
	 * Get Dialog Title
	 * @return
	 */
	public String getTitle() {
		return m_Title;
	}
	
	/**
	 * getData loaded
	 * @return
	 */
	public Vector<Vector<String>> getData() {
		return m_data;
	}
	
	/**
	 * Get Info
	 * @return
	 */
	public String getInfo() {
		return m_info.toString();
	}
}
