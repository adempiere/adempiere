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
 * Copyright (C) 2003-2014 E.R.P. Consultores y Asociados.                    *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpconsultoresyasociados.com               *
 *****************************************************************************/
package org.spinsuite.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import org.compiere.model.MColumn;
import org.compiere.model.MRule;
import org.compiere.model.MTable;
import org.compiere.model.X_AD_Rule;
import org.compiere.process.SvrProcess;
import org.compiere.util.AdempiereSystemError;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.spinsuite.model.MSPSColumn;
import org.spinsuite.model.MSPSTable;

/**
 * @author <a href="mailto:yamelsenih@gmail.com">Yamel Senih</a>
 *
 */
public class MobileCreateAllTables extends SvrProcess {

	@Override
	protected void prepare() {
		// TODO Auto-generated method stub

	}

	@Override
	protected String doIt() throws Exception {
		String sql = "SELECT * FROM AD_Table WHERE TableName > 'M_Replenish' ORDER BY TableName";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, get_TrxName());
			rs = pstmt.executeQuery ();
			while (rs.next ())
			{
				MTable m_Table = new MTable(getCtx(), rs, get_TrxName());
				int m_SPS_Table_ID = DB.getSQLValue(get_TrxName(), 
						"SELECT SPS_Table_ID FROM SPS_Table WHERE TableName = ?", 
						m_Table.getTableName());
				//	
				MSPSTable m_spsTable = null;
				if(m_SPS_Table_ID < 0)
					m_SPS_Table_ID = 0;
				
				m_spsTable = new MSPSTable(getCtx(), m_SPS_Table_ID, get_TrxName());
				
				m_spsTable.setTableName(m_Table.getTableName());
				m_spsTable.setName(m_Table.getName());
				m_spsTable.setDescription(m_Table.getDescription());
				m_spsTable.setIsView(m_Table.isView());
				m_spsTable.setIsDeleteable(m_Table.isDeleteable());
				m_spsTable.setAD_Table_ID(m_Table.getAD_Table_ID());
				m_spsTable.saveEx();
				System.out.println("Table = " + m_spsTable.getTableName());
				copyColumns(m_spsTable);
				generateScript(m_spsTable);
			}
 		}
		catch (Exception e)
		{
			log.log (Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		return null;
	}
	
	/**
	 * 
	 * @author <a href="mailto:yamelsenih@gmail.com">Yamel Senih</a> 21/04/2014, 23:27:34
	 * @param targetTable
	 * @throws AdempiereSystemError
	 * @throws SQLException
	 * @return void
	 */
	private void copyColumns(MSPSTable targetTable) throws AdempiereSystemError, SQLException{
		MSPSColumn[] targetColumns = targetTable.getColumns();

		int p_source_AD_Table_ID = targetTable.get_ValueAsInt("AD_Table_ID");

		if (p_source_AD_Table_ID == 0)
			throw new AdempiereSystemError("@NotFound@ @AD_Table_ID@ "
					+ p_source_AD_Table_ID);

		MTable sourceTable = new MTable(getCtx(), p_source_AD_Table_ID,
				get_TrxName());
		MColumn[] sourceColumns = sourceTable.getColumns(true);

		targetTable.setAD_Table_ID(p_source_AD_Table_ID);
		targetTable.save();

		for (int i = 0; i < sourceColumns.length; i++) {
			//	Get Current Column
			MSPSColumn colTarget = getExistsColumn(sourceColumns[i].getColumnName(), targetColumns);
			if(colTarget == null)
				colTarget = new MSPSColumn(targetTable);

			// special case the key -> sourceTable_ID
			if (sourceColumns[i].getColumnName().equals(
					sourceTable.getTableName() + "_ID")) {
				String targetColumnName = new String(targetTable.getTableName()
						+ "_ID");
				colTarget.setColumnName(targetColumnName);
				colTarget.setName(targetTable.getName());
				colTarget.setDescription(targetTable.getDescription());
			} else {
				colTarget.setColumnName(sourceColumns[i].getColumnName());
				colTarget.setName(sourceColumns[i].getName());
				colTarget.setDescription(sourceColumns[i].getDescription());
			}
			colTarget.setAD_Column_ID(sourceColumns[i].getAD_Column_ID());
			colTarget.setAD_Element_ID(sourceColumns[i].getAD_Element_ID());
			colTarget.setAD_Val_Rule_ID(sourceColumns[i].getAD_Val_Rule_ID());
			colTarget.setDefaultValue(sourceColumns[i].getDefaultValue());
			colTarget.setFieldLength(sourceColumns[i].getFieldLength());
			colTarget.setIsMandatory(sourceColumns[i].isMandatory());
			colTarget.setIsIdentifier(sourceColumns[i].isIdentifier());
			colTarget.setSeqNo(sourceColumns[i].getSeqNo());
			colTarget.setIsEncrypted(sourceColumns[i].getIsEncrypted());
			colTarget.setAD_Reference_ID(sourceColumns[i].getAD_Reference_ID());
			colTarget.setAD_Reference_Value_ID(sourceColumns[i]
					.getAD_Reference_Value_ID());
			colTarget.setIsActive(sourceColumns[i].isActive());
			colTarget.setCallout(sourceColumns[i].getCallout());
			colTarget.setIsUpdateable(sourceColumns[i].isUpdateable());
			colTarget
					.setIsSelectionColumn(sourceColumns[i].isSelectionColumn());
			colTarget.setIsAlwaysUpdateable(sourceColumns[i]
					.isAlwaysUpdateable());
			colTarget.setColumnSQL(sourceColumns[i].getColumnSQL());
			colTarget.setIsKey(sourceColumns[i].isKey());
			colTarget.setIsParent(sourceColumns[i].isParent());

			colTarget.saveEx(get_TrxName());
			if (targetTable.get_ValueAsBoolean("IsCentrallyMaintained")) {
				String sql = null,delete = null;
				int no = 0;
				sql = "SELECT AD_Language, "
						+ colTarget.get_ID()
						+ ", Name, IsTranslated, AD_Client_ID, AD_Org_ID,Created,Createdby,Updated,UpdatedBy  " 
						+ "FROM AD_Column_Trl WHERE AD_Column_ID = "
						+ sourceColumns[i].getAD_Column_ID();
				
				delete = "DELETE FROM SPS_Column_Trl WHERE SPS_Column_ID = " +colTarget.get_ID();
				no = DB.executeUpdate(delete, false, get_TrxName());
				log.info("  rows delete: " + no);
				
				PreparedStatement pstmt = DB.prepareStatement(sql,
						get_TrxName());
				ResultSet rs = pstmt.executeQuery();
				pstmt = DB.prepareStatement(sql, get_TrxName());
				rs = pstmt.executeQuery();
				while (rs.next()) {
					sql = "INSERT INTO SPS_Column_Trl (AD_Language,SPS_Column_ID, Name, IsTranslated," +
							"AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy)"
							+ "VALUES ('"
							+ rs.getString(1)
							+ "',"
							+ rs.getInt(2)
							+ ",'"
							+ rs.getString(3)
							+ "','"
							+ rs.getString(4)
							+ "',"
							+ rs.getInt(5)
							+ ","
							+ rs.getInt(6)
							+ ",'"
							+ rs.getDate(7)
							+ "',"
							+ rs.getInt(8)
							+ ",'"
							+ rs.getDate(9)
							+ "',"
							+ rs.getInt(10) + ")";
					no = DB.executeUpdate(sql, false, get_TrxName());
					log.info("  rows inserted: " + no);
				}
				pstmt.close();
				rs.close();
			}
		}
	}
	
	/**
	 * Get Current Column If Exists
	 * @author <a href="mailto:yamelsenih@gmail.com">Yamel Senih</a> 22/04/2014, 08:48:43
	 * @param m_ColumnName
	 * @param targetColumns
	 * @return
	 * @return MSPSColumn
	 */
	private MSPSColumn getExistsColumn(String m_ColumnName, MSPSColumn[] targetColumns){
		if(targetColumns == null)
			return null;
		for(int i = 0; i < targetColumns.length; i++){
			if(targetColumns[i].getColumnName().equals(m_ColumnName))
				return targetColumns[i];
		}
		return null;
	}
	
	/**
	 * 
	 * @author <a href="mailto:yamelsenih@gmail.com">Yamel Senih</a> 21/04/2014, 23:30:37
	 * @param m_Table
	 * @throws AdempiereSystemError
	 * @return void
	 */
	private void generateScript(MSPSTable m_Table) throws AdempiereSystemError{
		//	Verify exists columns
		MSPSColumn[] columns = m_Table.getColumns();
		//	
		if (columns == null 
				|| columns.length == 0)
			return;
		
		String sqlCreate = m_Table.getSQLCreate();
		
		int m_AD_Rule_ID = m_Table.getAD_Rule_ID();
		
		MRule ruleSQL = new MRule(getCtx(), m_AD_Rule_ID, get_TrxName());
		//	if not exists
		if(m_AD_Rule_ID == 0){
			ruleSQL.setAD_Org_ID(Env.getAD_Org_ID(getCtx()));
			ruleSQL.setValue("SQL:Create_" + m_Table.getTableName());
			ruleSQL.setName(Msg.translate(getCtx(), "AD_Rule_ID") 
					+ " Create Table " + m_Table.getName());
			ruleSQL.setEventType(X_AD_Rule.EVENTTYPE_Process);
			ruleSQL.setRuleType(X_AD_Rule.RULETYPE_SQL);
			ruleSQL.setAccessLevel(X_AD_Rule.ACCESSLEVEL_SystemOnly);
			ruleSQL.setEntityType("ECA01");
		}
		ruleSQL.setScript(sqlCreate);
		ruleSQL.saveEx();
		//	Set Rule on Sync Table
		m_Table.setAD_Rule_ID(ruleSQL.getAD_Rule_ID());
		m_Table.saveEx();
		System.out.println("SQL Create = " + sqlCreate);
	}

}
