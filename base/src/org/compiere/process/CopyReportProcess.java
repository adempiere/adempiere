/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2007 ADempiere, Inc. All Rights Reserved.                    *
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
 * Adempiere, Inc.                                                            *
 *****************************************************************************/
package org.compiere.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MColumn;
import org.compiere.model.MProcess;
import org.compiere.model.MProcessPara;
import org.compiere.model.PO;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;

/**
 * 
 * @author Paul Bowden (phib)
 * Adaxa Pty Ltd
 * Copy settings and parameters from source "Report and Process" to target
 * overwrites existing data (including translations)
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 243 ] Create Process parameter from Report View
 *		@see https://github.com/adempiere/adempiere/issues/243
 */
public class CopyReportProcess extends SvrProcess {

	/**	SQL					*/
	private StringBuffer	sql = new StringBuffer();
	/**	Sequence			*/
	private int				m_SeqNo = 10;
	
	@Override
	protected String doIt() throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		//	Instance current process
		MProcess process = MProcess.get(getCtx(), getRecord_ID());
		//	
		ps = DB.prepareStatement(sql.toString(), get_TrxName());
		ps.setInt(1, getAD_PInstance_ID());
		rs = ps.executeQuery();
		//	
		while(rs.next()) {
			copyFrom(process, rs);
		}
		//	Default Ok
		return "@OK@";
	}
	
	/**
	 * Copy parameters to process
	 * @param process
	 * @param rs
	 * @throws SQLException 
	 */
	private void copyFrom(MProcess process, ResultSet rs) throws SQLException {
		//	Get Values
		int m_AD_Column_ID = rs.getInt("AD_Column_ID");
		int m_AD_ReportView_ID = rs.getInt("AD_ReportView_ID");
		int m_AD_Process_Para_ID = rs.getInt("AD_Process_Para_ID");
		boolean m_IsMandatory = rs.getString("IsMandatory").equals("Y");
		boolean m_IsRange = rs.getString("IsRange").equals("Y");
		String m_DefaultValue = rs.getString("DefaultValue");
		String m_DefaultValue2 = rs.getString("DefaultValue2");
		//	Do it
		MProcessPara newParameter = new MProcessPara(process);
		if(m_AD_ReportView_ID != 0) {	//	For Create from View
			MColumn column = MColumn.get(getCtx(), m_AD_Column_ID);
			//	
			if (column.getAD_Reference_ID() == DisplayType.ID) {
				return;
			}
			//	For Process
			if(process.getAD_ReportView_ID() != m_AD_ReportView_ID) {
				process.setAD_ReportView_ID(m_AD_ReportView_ID);
				process.saveEx();
			}
			//	Set Values
			newParameter.setEntityType(process.getEntityType());
			newParameter.setAD_Element_ID(column.getAD_Element_ID());
			newParameter.setAD_Reference_ID(column.getAD_Reference_ID());
			newParameter.setAD_Reference_Value_ID(column.getAD_Reference_Value_ID());
			newParameter.setAD_Val_Rule_ID(column.getAD_Val_Rule_ID());
			newParameter.setName(column.getName());
			newParameter.setColumnName(column.getColumnName());
			newParameter.setDescription(column.getDescription());
			newParameter.setFieldLength(column.getFieldLength());
			newParameter.setHelp(column.getHelp());
			newParameter.setIsCentrallyMaintained(true);
		} else if(m_AD_Process_Para_ID != 0) {	//	For Copy from Process
			MProcessPara fromParameter = new MProcessPara(getCtx(), m_AD_Process_Para_ID, get_TrxName());
			PO.copyValues(fromParameter, newParameter);
		} else {
			return;
		}
		//	Fill values
		newParameter.setIsMandatory(m_IsMandatory);
		newParameter.setIsRange(m_IsRange);
		newParameter.setDefaultValue(m_DefaultValue);
		newParameter.setDefaultValue2(m_DefaultValue2);
		newParameter.setSeqNo(m_SeqNo);
		//	Save
		newParameter.saveEx();
		//	Add new Sequence
		m_SeqNo += 10;
		addLog("@AD_Process_Para_ID@ @" + newParameter.getColumnName() + "@ @Added@");
	}
	
	@Override
	protected void prepare() {
		//	Valid Record Identifier
		if(getRecord_ID() <= 0)
			throw new AdempiereException("@AD_Process_ID@ @NotFound@");
		//	Make Query
		sql.append("SELECT "
				+ "	ts.AD_PInstance_ID, "
				+ " tsb.AD_Column_ID, "
				+ " COALESCE(tsb.IsMandatory, 'N') IsMandatory, "
				+ " COALESCE(tsb.IsRange, 'N') IsRange, "
				+ " tsb.DefaultValue, "
				+ " tsb.DefaultValue2,"
				+ " tsb.AD_ReportView_ID,"
				+ " tsb.AD_Process_Para_ID"
				+ " FROM T_Selection ts "
				+ " INNER JOIN ( "
				+ " SELECT tsb.AD_PInstance_ID, tsb.T_Selection_ID,"
				+ " 	MAX(CASE WHEN tsb.ColumnName = 'PARAMETER_AD_Column_ID' THEN tsb.Value_Number ELSE NULL END) AS AD_Column_ID,"
				+ " 	MAX(CASE WHEN tsb.ColumnName = 'PARAMETER_IsMandatory' THEN tsb.Value_String ELSE NULL END) AS IsMandatory,"
				+ " 	MAX(CASE WHEN tsb.ColumnName = 'PARAMETER_IsRange' THEN tsb.Value_String ELSE NULL END) AS IsRange,"
				+ " 	MAX(CASE WHEN tsb.ColumnName = 'PARAMETER_DefaultValue' THEN tsb.Value_String ELSE NULL END) AS DefaultValue,"
				+ " 	MAX(CASE WHEN tsb.ColumnName = 'PARAMETER_DefaultValue2' THEN tsb.Value_String ELSE NULL END) AS DefaultValue2,"
				+ " 	MAX(CASE WHEN tsb.ColumnName = 'PARAMETER_AD_ReportView_ID' THEN tsb.Value_Number ELSE NULL END) AS AD_ReportView_ID,"
				+ " 	MAX(CASE WHEN tsb.ColumnName = 'PARAMETER_AD_Process_Para_ID' THEN tsb.Value_Number ELSE NULL END) AS AD_Process_Para_ID"
				+ " FROM T_Selection_Browse tsb "
				+ " GROUP BY tsb.AD_PInstance_ID, tsb.T_Selection_ID"
				+ ") tsb ON(ts.AD_PInstance_ID = tsb.AD_PInstance_ID AND ts.T_Selection_ID = tsb.T_Selection_ID) "
				+ " WHERE ts.AD_PInstance_ID = ?");
		//	Log
		log.fine(sql.toString());
	}
}
