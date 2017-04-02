/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
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
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.adempiere.util;

import org.compiere.model.MTable;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trx;

/**
 *	Delete Model.
 *
 *	@author Paul Bowden
 *	@author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/880">
 * 		@see FR [ 880 ] Allow "Delete Entities" form to be accessible only for role "System Administrator"</a>
 */
public class DeleteEntitiesModel {

	/**	Attributes		*/
	private boolean isMandatoryLink;
	private int 	clientId;
	private int 	tableId;
	private int		parentTableId;
	private int 	count;
	private String 	tableName;
	private String 	keyColumn;
	private String 	joinColumn;
	private String 	parentTableName;
	private String 	parentColumn; 
	private String 	whereClause;

	public DeleteEntitiesModel() {
		count = -1;
	}
	
	/**
	 * From Table
	 * @param clientId
	 * @param tableId
	 */
	public DeleteEntitiesModel(int clientId, int tableId) {
		if(tableId > 0) {
			MTable table = MTable.get(Env.getCtx(), tableId);
			this.clientId = clientId;
			this.tableId = tableId;
			this.tableName = table.getTableName();
			this.isMandatoryLink = true;
			String[] keyColumns = table.getKeyColumns();
			if(keyColumns != null
					&& keyColumns.length > 0) {
				this.joinColumn = keyColumns[0];
			} else {
				this.joinColumn = "";
			}
			//	
			createWhereClause(null);
		}
		//	
		count = -1;
	}
	
	/**
	 * Create Where Clause automatically
	 * @param tableId
	 * @param tableName
	 * @param joinColumn
	 * @param isMandatoryLink
	 */
	public DeleteEntitiesModel(int clientId, int tableId, String tableName, String joinColumn, 
			boolean isMandatoryLink, DeleteEntitiesModel parentNode) {
		this.clientId = clientId;
		this.tableId = tableId;
		this.tableName = tableName;
		this.joinColumn = joinColumn;
		this.isMandatoryLink = isMandatoryLink;
		//	
		createWhereClause(parentNode);
		//	
		count = -1;
	}
	
	private void createWhereClause(DeleteEntitiesModel parentNode) {
		//	Create Where clause if have a parent
		if(parentNode != null) {
			whereClause = "EXISTS (SELECT 1 FROM " + parentNode.getTableName() 
					+ " WHERE " + parentNode.getTableName() + "." + parentNode.getTableName() + "_ID"
					+ " = " + getTableName() + "." + getJoinColumn() + " AND " + parentNode.getWhereClause() + ") ";
		} else {
			//	Add Client Criteria
			whereClause = getTableName() + ".AD_Client_ID = " + getClientId();
			//	For User
			if (getTableName().equals("AD_User")) {
				appeandWhereClause("NOT EXISTS (SELECT 1 FROM C_BPartner bp " +
														"WHERE AD_User.Link_BPartner_ID = bp.C_BPartner_ID " +
														"AND (bp.IsEmployee='Y' OR bp.IsSalesRep='Y'))");
			}
		}
	}
	
	
	/**
	 * Add a where clause to current where clause
	 * @param whereClause
	 */
	public void appeandWhereClause(String whereClause) {
		if(whereClause == null)
			return;
		//	
		this.whereClause = this.whereClause + " AND " + whereClause;
	}

	/**
	 * Get Client Selected
	 * @return
	 */
	public int getClientId() {
		return clientId;
	}
	
	/**
	 * Get Table for it
	 * @return
	 */
	public int getTableId() {
		return tableId;
	}
	
	/**
	 * Get Table Name for Node
	 * @return
	 */
	public String getTableName() {
		return tableName;
	}
	
	/**
	 * Get Key Column
	 * @return
	 */
	public String getKeyColumn() {
		return keyColumn;
	}
	
	/**
	 * Join Column
	 * @return
	 */
	public String getJoinColumn() {
		return joinColumn;
	}
	
	/**
	 * Get Parent Table Name
	 * @return
	 */
	public String getParentTableName() {
		return parentTableName;
	}
	
	/**
	 * Get Parent Table ID
	 * @return
	 */
	public int getParentTableId() {
		return parentTableId;
	}
	
	/**
	 * Get Parent Column Name
	 * @return
	 */
	public String getParentColumn() {
		return parentColumn;
	}
	
	/**
	 * Get Where Clause
	 * @return
	 */
	public String getWhereClause() {
		return whereClause;
	}
	
	/**
	 * Is Mandatory Link
	 * @return
	 */
	public boolean isMandatoryLink() {
		return isMandatoryLink;
	}
	
	/**
	 * Retur key column for table
	 * @return
	 */
	public String getKeyColumnForTable() {
		return tableName + "_ID";
	}
	
	/**
	 * Get Node Count
	 * @return
	 */
	public int getCount() {
		if(count == -1) {
			count = DB.getSQLValue(null, "SELECT COUNT(*) FROM " + tableName + " WHERE " + whereClause);
		}
		return count;
	}

	/**
	 * Delete Node
	 * @param m_trx
	 * @return
	 */
	public int delete(Trx m_trx) {
		String sql;
		if (isMandatoryLink) {
			sql = "DELETE FROM " + tableName
			+ " WHERE " + whereClause;
		} else {
			sql = "UPDATE " + tableName + " SET " + joinColumn + " = NULL "
			+ " WHERE " + whereClause;
		}	
		//	
		int count = DB.executeUpdateEx(sql, m_trx.getTrxName());
	
		return count;
	}
	
	@Override
	public String toString() {
		return (tableName == null? "": tableName) + (joinColumn == null ? "" :  "." + joinColumn) + " (" + getCount() + ")";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((tableName == null) ? 0 : tableName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DeleteEntitiesModel other = (DeleteEntitiesModel) obj;
		if (tableName == null) {
			if (other.tableName != null)
				return false;
		} else if (!tableName.equals(other.tableName))
			return false;
		return true;
	}
}