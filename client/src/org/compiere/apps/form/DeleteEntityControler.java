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
package org.compiere.apps.form;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Stack;
import java.util.logging.Level;

import org.adempiere.core.domains.models.I_AD_Table;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.util.DeleteEntitiesModel;
import org.compiere.model.MTable;
import org.compiere.model.Query;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Trx;

/**
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/880">
 * 		@see FR [ 880 ] Allow "Delete Entities" form to be accessible only for role "System Administrator"</a>
 *
 */
public abstract class DeleteEntityControler {

	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(DeleteEntityControler.class);
	
	/**	Window No	*/
	private int 	windowNo;
	private int 	clientId;
	private int 	tableId;
	private int 	cleanDefinitionId;
	private boolean isDryRun;
	private boolean isCleanDefinition;
	private Stack<DeleteEntitiesModel> stack = new Stack<DeleteEntitiesModel>();
	
	/**
	 * Init
	 * @param windowNo
	 */
	public void init(int windowNo) {
		this.windowNo = windowNo;
	}
	
	/**
	 * Get Client for Combo
	 * @return
	 */
	public KeyNamePair[] getClients() {
		return DB.getKeyNamePairs("SELECT AD_Client_ID, Name "
				+ "FROM AD_Client "
				+ "WHERE AD_Client_ID <> 0 "
				+ "ORDER BY Name", true);
	}
	
	/**
	 * Get Tables for Combo
	 * @return
	 */
	public KeyNamePair[] getTables() {
		return DB.getKeyNamePairs("SELECT AD_Table_ID, (TableName || '_' || Name) AS Table "
				+ "FROM AD_Table "
				+ "WHERE IsView = 'N' "
				//	Not System Only
				+ "AND AccessLevel <> '4' "
				+ "ORDER BY TableName", true);
	}
	
	/**
	 * Get Clean Definition for Combo
	 * @return
	 */
	public KeyNamePair[] getCleanDefinition() {
		return DB.getKeyNamePairs("SELECT AD_CleanDefinition_ID, Name "
				+ "FROM AD_CleanDefinition "
				+ "ORDER BY Name", true);
	}
	
	/**
	 * Load Child from tableName
	 * @param currentNode
	 */
	public void loadChilds(DeleteEntitiesModel currentNode, Object rootNode, boolean isParent) {
		HashSet<String> tablesIgnored = new HashSet<String>(Arrays.asList(new String[] {
				"T_Report", "T_ReportStatement", "AD_Attribute_Value", "AD_PInstance_Log", "A_Valid_Asset_Combinations"
		}));

		if (tablesIgnored.contains(currentNode.getTableName()))
			return;
		//	
		if(cleanDefinitionId != 0
				&& isParent) {
			try {
				new Query(Env.getCtx(), I_AD_Table.Table_Name, 
						"EXISTS(SELECT 1 FROM AD_CleanDefinitionTable cdt WHERE cdt.AD_Table_ID = AD_Table.AD_Table_ID AND cdt.AD_CleanDefinition_ID = ? "
						+ "AND cdt.AD_Table_ID <> ?)", null)
						.setParameters(cleanDefinitionId, currentNode.getTableId())
						.setOrderBy(I_AD_Table.COLUMNNAME_LoadSeq + " DESC")
						.setOnlyActiveRecords(true)
						.<MTable>list()
						.forEach(table -> {
							DeleteEntitiesModel data = new DeleteEntitiesModel(currentNode.getClientId(), table.getAD_Table_ID());
							//	Get Count
							int count = data.getCount();
							//	
							if (count > 0) {
								log.log(Level.FINE, "Adding node: " + data.getTableName() + "." + data.getJoinColumn());
								addToNode(data, rootNode);
							} else {
								log.log(Level.FINE, "No records:" + data.getTableName());
							}
						});
			} catch (Exception e) {
				throw new AdempiereException("Couldn't load child tables", e);
			}
		}
		String sql = "SELECT t.AD_Table_ID, t.TableName, c.ColumnName, c.IsMandatory "
				+ "FROM AD_Table t "
				+ "INNER JOIN AD_Column c ON (t.AD_Table_ID=c.AD_Table_ID) "
				+ "WHERE t.IsView = 'N' "
				+ "AND c.ColumnName NOT IN ('CreatedBy', 'UpdatedBy') "
				+ "AND t.AD_Table_ID <> ? "
				+ "AND ("
				+ "		(c.ColumnName = ? AND c.IsKey='N' AND c.ColumnSQL IS NULL) "
				+ "		OR EXISTS(SELECT 1 "
				+ "					FROM AD_Ref_Table r "
				+ "					WHERE r.AD_Reference_ID = c.AD_Reference_Value_ID "
				+ "					AND r.AD_Table_ID = ?)"
				+ ") "
				+ "ORDER BY t.LoadSeq DESC";
		//	Query to DB
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//	
		try {
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, currentNode.getTableId());
			pstmt.setString(2, currentNode.getKeyColumnForTable());
			pstmt.setInt(3, currentNode.getTableId());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String isMandatory = rs.getString("IsMandatory");
				DeleteEntitiesModel data = new DeleteEntitiesModel(currentNode.getClientId(), 
						rs.getInt("AD_Table_ID"), 
						rs.getString("TableName"), 
						rs.getString("ColumnName"), 
						(isMandatory != null && isMandatory.equals("Y")), 
						currentNode);
				//	Get Count
				int count = data.getCount();
				//	
				if (count > 0) {
					log.log(Level.FINE, "Adding node: " + data.getTableName() + "." + data.getJoinColumn());
					addToNode(data, rootNode);
				} else {
					log.log(Level.FINE, "No records:" + data.getTableName());
				}
			}
			//
		} catch (SQLException e) {
			log.log(Level.INFO, sql);
			throw new AdempiereException("Couldn't load child tables", e);
		} finally {
			DB.close(rs, pstmt);
		}
	}
		
	/**
	 * Get Window No
	 * @return
	 */
	public int getWindowNo() {
		return windowNo;
	}
	
	/**
	 * Get Client ID
	 * @return
	 */
	public int getClientId() {
		return clientId;
	}
	
	/**
	 * Get Table ID
	 * @return
	 */
	public int getTableId() {
		if(!isCleanDefinition()) {
			return tableId;
		} else {
			return new Query(Env.getCtx(), I_AD_Table.Table_Name, 
					"EXISTS(SELECT 1 FROM AD_CleanDefinitionTable cdt WHERE cdt.AD_Table_ID = AD_Table.AD_Table_ID AND AD_CleanDefinition_ID = ?)", null)
					.setParameters(getCleanDefinitionId())
					.setOrderBy(I_AD_Table.COLUMNNAME_LoadSeq + " DESC")
					.setOnlyActiveRecords(true)
					.firstId();
		}
	}
	
	/**
	 * Is Dry Run
	 * @return
	 */
	public boolean isDryRun() {
		return isDryRun;
	}
	
	/**
	 * Set Client ID
	 * @param clientId
	 */
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	
	/**
	 * Set Table Id
	 * @param tableId
	 */
	public void setTableId(int tableId) {
		this.tableId = tableId;
	}
	
	/**
	 * Set Dry Run
	 * @param isDryRun
	 */
	public void setDryRun(boolean isDryRun) {
		this.isDryRun = isDryRun;
	}
	
	public int getCleanDefinitionId() {
		return cleanDefinitionId;
	}

	public void setCleanDefinitionId(int cleanDefinitionId) {
		this.cleanDefinitionId = cleanDefinitionId;
	}

	public boolean isCleanDefinition() {
		return isCleanDefinition;
	}

	public void setCleanDefinition(boolean isCleanDefinition) {
		this.isCleanDefinition = isCleanDefinition;
	}

	/**
	 * Add to Stack for delete
	 * @param node
	 */
	public void addToStackForDelete(DeleteEntitiesModel node) {
		stack.push(node);
	}
	
	/**
	 * De;ete Selected nodes
	 * @param commit
	 * @return
	 */
	public int deleteSelectedNodes() {
		int totalCount = 0;
		Trx trx = Trx.get(Trx.createTrxName("delete"), true);
		String errorMsg = null;
		try {
			//	
			while (!stack.empty()) {
				DeleteEntitiesModel tableData = (DeleteEntitiesModel) stack.pop();
				totalCount += tableData.delete(trx);
			}
			//	Commit changes
			if(!isDryRun()) {
				trx.commit(true);
			} else {
				trx.rollback(true);
			}
		} catch (Exception ex) {
			errorMsg = ex.getLocalizedMessage();
			log.log(Level.WARNING, "Cascade delete failed.", ex);
			totalCount = 0;
			trx.rollback();
		} finally {
			trx.close();
		}
		//	Exception
		if(errorMsg != null)
			throw new AdempiereException(errorMsg);
		//	Return
		return totalCount;
	}
	/**
	 * Add data to view node
	 * @param data
	 * @param rootNode
	 */
	public abstract void addToNode(DeleteEntitiesModel data, Object rootNode);
}
