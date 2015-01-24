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
package org.spinsuite.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.compiere.model.MColumn;
import org.compiere.model.MTable;
import org.compiere.model.M_Element;
import org.compiere.process.SvrProcess;
import org.compiere.util.AdempiereSystemError;
import org.compiere.util.DB;
import org.compiere.util.Trx;
import org.spinsuite.model.MSPSColumn;
import org.spinsuite.model.MSPSTable;

/**
 * Copy columns from one table to other
 * 
 * @author Carlos Ruiz - globalqss
 * @author <a href="mailto:yamelsenih@gmail.com">Yamel Senih</a>
 * @contributor <a href="mailto:dixon.22martinez@gmail.com">Dixon Martinez</a>
 * 		<br> <p>If the table is maintained centrally eliminates the translations that have their columns 
 * 		and add the translations maintained centrally. 
 * 		
 * @version $Id: CopyColumnsFromTable
 */
public class CopyColumnsFromTable extends SvrProcess {
	/** Target Table */
	private int p_target_AD_Table_ID = 0;
	/** Source Table */
	private int p_source_AD_Table_ID = 0;

	/** Column Count */
	private int m_count = 0;

	/**
	 * Prepare - e.g., get Parameters.
	 */
	protected void prepare() {
		p_target_AD_Table_ID = getRecord_ID();
	} // prepare

	/**
	 * Process
	 * 
	 * @return info
	 * @throws Exception
	 */
	protected String doIt() throws Exception {

		if (p_target_AD_Table_ID == 0)
			throw new AdempiereSystemError("@NotFound@ @AD_Table_ID@ "
					+ p_target_AD_Table_ID);
		log.info("Source AD_Table_ID=" + p_source_AD_Table_ID
				+ ", Target AD_Table_ID=" + p_target_AD_Table_ID);

		MSPSTable targetTable = new MSPSTable(getCtx(),
				p_target_AD_Table_ID, get_TrxName());
		MSPSColumn[] targetColumns = targetTable.getColumns();

		p_source_AD_Table_ID = targetTable.getAD_Table_ID();

		if (p_source_AD_Table_ID == 0)
			throw new AdempiereSystemError("@NotFound@ @AD_Table_ID@ "
					+ p_source_AD_Table_ID);

		MTable sourceTable = new MTable(getCtx(), p_source_AD_Table_ID,
				get_TrxName());
		MColumn[] sourceColumns = sourceTable.getColumns(true);

		// Dixon Martinez
		// Add the ID of the copy table from which the columns
		targetTable.setAD_Table_ID(p_source_AD_Table_ID);
		targetTable.save();

		for (int i = 0; i < sourceColumns.length; i++) {
			
			
			MSPSColumn colTarget = new MSPSColumn(targetTable);

			//[FR1784588] logic to validate exist columns
			boolean foundColumn = false;
			for(MSPSColumn col:targetColumns)
			{
				String columnName = null;
				if (sourceColumns[i].getColumnName().equals(sourceTable.getTableName()+"_ID")) 
				{
					columnName = new String(targetTable.getTableName()+"_ID");	
				}
				else
				{
					columnName = sourceColumns[i].getColumnName();
				}
				
				if(col.getColumnName().equals(columnName))
				{
					foundColumn = true;
					
					break;
				}
			}
			if(foundColumn)
				continue;
			
			Trx trx = Trx.get(get_TrxName(), false);

			// special case the key -> sourceTable_ID
			if (sourceColumns[i].getColumnName().equals(sourceTable.getTableName() + "_ID")) {
				String targetColumnName = new String(targetTable.getTableName()	+ "_ID");
				colTarget.setColumnName(targetColumnName);
				// if the element don't exist, create it 
				M_Element element = M_Element.get (getCtx (), targetColumnName);
				if (element == null)
				{
					element = new M_Element (getCtx (), targetColumnName, targetTable.getEntityType(), get_TrxName ());
					if (targetColumnName.equalsIgnoreCase (targetTable.getTableName() + "_ID")) {
						element.setColumnName(targetTable.getTableName() + "_ID");
						element.setName(targetTable.getName());
						element.setPrintName(targetTable.getName());
					}
					element.save (get_TrxName());
				}
				colTarget.setAD_Element_ID(element.getAD_Element_ID());
				colTarget.setName(targetTable.getName());
				colTarget.setDescription(targetTable.getDescription());
			} else {
				colTarget.setColumnName(sourceColumns[i].getColumnName());
				colTarget.setName(sourceColumns[i].getName());
				colTarget.setDescription(sourceColumns[i].getDescription());
				M_Element element = M_Element.get (getCtx (), targetTable.getTableName() + "_ID");
				if (element != null){
					colTarget.setAD_Element_ID(sourceColumns[i].getAD_Element_ID());
				} else {
					colTarget.setAD_Element_ID(sourceColumns[i].getAD_Element_ID());
				}
			}
			// Dixon Martinez
			// If you want to reference the columns
			//	Yamel Senih 2014-10-24 20:03:13
			//	Always reference column
			colTarget.setAD_Column_ID(sourceColumns[i].getAD_Column_ID());
			//	
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
			trx.commit(true);
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
				trx.commit(true);
				
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
					trx.commit(true);
				}
				pstmt.close();
				rs.close();
				m_count++;
			}
		}

		return "#" + m_count;
	} // doIt
} // CopyColumnsFromTable