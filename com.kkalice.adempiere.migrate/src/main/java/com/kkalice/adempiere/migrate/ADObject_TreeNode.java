/*
 * Name:		ADObject_TreeNode.java
 * Description:	class for maintaining location of custom nodes in a tree
 * Created:		2011-07-06
 * Vendor:		K.K. Alice
 * Author:		Stefan Christians
 *
 * FileTarget:	~/development/sandbox/migrate/src/com/kkalice/adempiere/migrate/ADObject_TreeNode.java
 * FileOwner:	spc.dvp
 * FilePerms:	0644
 *
 */

/**
 * This file is part of Adempiere ERP Bazaar
 * http://www.adempiere.org
 *
 * Copyright (C) Stefan Christians
 * Copyright (C) Contributors
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 *
 * Contributors:
 * - Stefan Christians
 *
 * Sponsors:
 * - K.K. Alice
 *
 */

package com.kkalice.adempiere.migrate;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * maintain location of customized nodes in a tree
 * @author Stefan Christians
 */
public class ADObject_TreeNode {

	// MEMBERS
	// =======

	/** static dbEngine */
	private static DBEngine s_dbEngine = null;


	/** name of the tree containing the node */
	private String m_treeNodeTable = null;

	/** node ID */
	private Integer m_nodeID = null;

	/** tree to which this node belongs */
	private Integer m_treeID = null;

	/** branch to which this node belongs */
	private Integer m_parentID = null;

	/** location of this node in the branch */
	private Integer m_seqNo = null;

	/**
	 * the node which comes before this node
	 * (or null if this is the first node of the branch)
	 * */
	private Integer m_previousNodeID = null;

	/**
	 * the node which comes after this node
	 * (or null if this is the last node of the branch)
	 * */
	private Integer m_nextNodeID = null;

	// CONSTRUCTORS
	// ============

	/**
	 * default constructor
	 */
	public ADObject_TreeNode () {
		this (null, null);
	}

	/**
	 * simple constructor
	 * @param treeNodeTable name of the table containing the tree node
	 * @param nodeID the node's unique identifier
	 */
	public ADObject_TreeNode (String treeNodeTable, Integer nodeID) {
		s_dbEngine = DBEngine.getDBEngine();
		setTreeNodeTable(treeNodeTable);
		setNodeID(nodeID);
	}

	/**
	 * initializing constructor
	 * @param dbConnection database containing the tree
	 * @param treeNodeTable name of the table containing the tree node
	 * @param nodeID the node's unique identifier
	 */
	public ADObject_TreeNode (DBConnection dbConnection, String treeNodeTable, int nodeID) {
		this (treeNodeTable, nodeID);
		loadNodeLocation(dbConnection);
	}

	// METHODS
	// =======

	/**
	 * load information about the location of this node
	 * @param dbConnection database connection
	 */
	public void loadNodeLocation (DBConnection dbConnection) {
		// reset
		setTreeID(null);
		setParentID(null);
		setSeqNo(null);
		setPreviousNodeID(null);
		setNextNodeID(null);

		// do nothing if this is not a valid node
		if (dbConnection==null || getTreeNodeTable().length()==0 || getNodeID()==0)
			return;

		String vendorName = dbConnection.getVendor();
		String catalogName = dbConnection.getCatalog();
		String schemaName = dbConnection.getSchema();

		// get node information
		String whereClause = new StringBuffer("Node_ID=").append(getNodeID()).toString();
		String sqlCommand = s_dbEngine.sql_select(vendorName, catalogName, schemaName, getTreeNodeTable(), whereClause);
		Statement stmt = dbConnection.setStatement();
		ResultSet rs = dbConnection.executeQuery(stmt, sqlCommand);
		if (dbConnection.getResultSetNext(rs)) {
			setTreeID(dbConnection.getResultSetInt(rs, "AD_Tree_ID"));
			setParentID(dbConnection.getResultSetInt(rs, "Parent_ID"));
			setSeqNo(dbConnection.getResultSetInt(rs, "seqNo"));
		}
		dbConnection.releaseResultSet(rs);
		dbConnection.releaseStatement(stmt);

		// find preceding node
		whereClause = new StringBuffer("AD_Tree_ID=").append(getTreeID()).append(" AND Parent_ID=").append(getParentID()).append(" AND SeqNo=").append("(SELECT MAX(seqNo) FROM ").append(getTreeNodeTable()).append(" WHERE AD_Tree_ID=").append(getTreeID()).append(" AND Parent_ID=").append(getParentID()).append(" AND seqNo<").append(getSeqNo()).append(")").toString();
		sqlCommand = s_dbEngine.sql_select(vendorName, catalogName, schemaName, getTreeNodeTable(), whereClause);
		stmt = dbConnection.setStatement();
		rs = dbConnection.executeQuery(stmt, sqlCommand);
		if (dbConnection.getResultSetNext(rs)) {
			setPreviousNodeID(dbConnection.getResultSetInt(rs, "Node_ID"));
		}
		dbConnection.releaseResultSet(rs);
		dbConnection.releaseStatement(stmt);

		// find following node
		whereClause = new StringBuffer("AD_Tree_ID=").append(getTreeID()).append(" AND Parent_ID=").append(getParentID()).append(" AND SeqNo=").append("(SELECT MIN(seqNo) FROM ").append(getTreeNodeTable()).append(" WHERE AD_Tree_ID=").append(getTreeID()).append(" AND Parent_ID=").append(getParentID()).append(" AND seqNo>").append(getSeqNo()).append(")").toString();
		sqlCommand = s_dbEngine.sql_select(vendorName, catalogName, schemaName, getTreeNodeTable(), whereClause);
		stmt = dbConnection.setStatement();
		rs = dbConnection.executeQuery(stmt, sqlCommand);
		if (dbConnection.getResultSetNext(rs)) {
			setNextNodeID(dbConnection.getResultSetInt(rs, "Node_ID"));
		}
		dbConnection.releaseResultSet(rs);
		dbConnection.releaseStatement(stmt);
	}

	/**
	 * adjust the location of this node back to where it was before migration
	 * @param dbConnection database connection
	 * @return true if location was successfully adjusted
	 */
	public boolean adjustNodeLocation (DBConnection dbConnection) {

		// do nothing if this is not a valid node
		if (dbConnection==null || getTreeNodeTable().length()==0 || getNodeID()==0)
			return false;

		String vendorName = dbConnection.getVendor();
		String catalogName = dbConnection.getCatalog();
		String schemaName = dbConnection.getSchema();

		// find location of first node in this branch
		int firstSeqNo=0;
		String whereClause = new StringBuffer("AD_Tree_ID=").append(getTreeID()).append(" AND Parent_ID=").append(getParentID()).append(" AND SeqNo=").append("(SELECT MIN(seqNo) FROM ").append(getTreeNodeTable()).append(" WHERE Parent_ID=").append(getParentID()).append(" AND Node_ID!=").append(getNodeID()).append(")").toString();
		String sqlCommand = s_dbEngine.sql_select(vendorName, catalogName, schemaName, getTreeNodeTable(), whereClause);
		Statement stmt = dbConnection.setStatement();
		ResultSet rs = dbConnection.executeQuery(stmt, sqlCommand);
		if (dbConnection.getResultSetNext(rs)) {
			firstSeqNo=dbConnection.getResultSetInt(rs, "SeqNo");
		}
		dbConnection.releaseResultSet(rs);
		dbConnection.releaseStatement(stmt);

		// find location of last node in this branch
		int lastSeqNo=0;
		whereClause = new StringBuffer("AD_Tree_ID=").append(getTreeID()).append(" AND Parent_ID=").append(getParentID()).append(" AND SeqNo=").append("(SELECT MAX(seqNo) FROM ").append(getTreeNodeTable()).append(" WHERE Parent_ID=").append(getParentID()).append(" AND Node_ID!=").append(getNodeID()).append(")").toString();
		sqlCommand = s_dbEngine.sql_select(vendorName, catalogName, schemaName, getTreeNodeTable(), whereClause);
		stmt = dbConnection.setStatement();
		rs = dbConnection.executeQuery(stmt, sqlCommand);
		if (dbConnection.getResultSetNext(rs)) {
			lastSeqNo=dbConnection.getResultSetInt(rs, "SeqNo");
		}
		dbConnection.releaseResultSet(rs);
		dbConnection.releaseStatement(stmt);

		// find location of previous node
		int previousSeqNo=0;
		whereClause = new StringBuffer("AD_Tree_ID=").append(getTreeID()).append(" AND Parent_ID=").append(getParentID()).append(" AND Node_ID=").append(getPreviousNodeID()).toString();
		sqlCommand = s_dbEngine.sql_select(vendorName, catalogName, schemaName, getTreeNodeTable(), whereClause);
		stmt = dbConnection.setStatement();
		rs = dbConnection.executeQuery(stmt, sqlCommand);
		if (dbConnection.getResultSetNext(rs)) {
			previousSeqNo=dbConnection.getResultSetInt(rs, "SeqNo");
		}
		dbConnection.releaseResultSet(rs);
		dbConnection.releaseStatement(stmt);

		// find location of following node
		int nextSeqNo=0;
		whereClause = new StringBuffer("AD_Tree_ID=").append(getTreeID()).append(" AND Parent_ID=").append(getParentID()).append(" AND Node_ID=").append(getNextNodeID()).toString();
		sqlCommand = s_dbEngine.sql_select(vendorName, catalogName, schemaName, getTreeNodeTable(), whereClause);
		stmt = dbConnection.setStatement();
		rs = dbConnection.executeQuery(stmt, sqlCommand);
		if (dbConnection.getResultSetNext(rs)) {
			nextSeqNo=dbConnection.getResultSetInt(rs, "SeqNo");
		}
		dbConnection.releaseResultSet(rs);
		dbConnection.releaseStatement(stmt);


		// put this node back to its original location
		int newSeqNo=getSeqNo();
		if (getNextNodeID()==0) {
			// if this was the last node, put it back into last position
			newSeqNo=lastSeqNo+1;
		} else if (getPreviousNodeID()==0) {
			// otherwise, if this was the first node, put it back into first position
			newSeqNo=firstSeqNo;
		} else {
			// otherwise, put it between previous and next node
			if (previousSeqNo!=0) {
				// if previous node exists, put it after previous node
				newSeqNo=previousSeqNo+1;
			} else if (nextSeqNo!=0) {
				// otherwise, if following node exists, put it before  following node
				newSeqNo=nextSeqNo;
			} else {
				// otherwise just keep the current location
				newSeqNo=getSeqNo();
			}
		}

		// see if the slot for this node is already occupied
		boolean isOccupied=false;
		whereClause = new StringBuffer("AD_Tree_ID=").append(getTreeID()).append(" AND Parent_ID=").append(getParentID()).append(" AND Node_ID!=").append(getNodeID()).append(" AND SeqNo=").append(newSeqNo).toString();
		sqlCommand = s_dbEngine.sql_select(vendorName, catalogName, schemaName, getTreeNodeTable(), whereClause);
		stmt = dbConnection.setStatement();
		rs = dbConnection.executeQuery(stmt, sqlCommand);
		if (dbConnection.getResultSetNext(rs)) {
			isOccupied=true;
		}
		dbConnection.releaseResultSet(rs);
		dbConnection.releaseStatement(stmt);

		// finalize node location
		boolean isError=false;
		whereClause = new StringBuffer("AD_Tree_ID=").append(getTreeID()).append(" AND Parent_ID=").append(getParentID()).append(" AND Node_ID=").append(getNodeID()).toString();
		sqlCommand = s_dbEngine.sql_update(vendorName, catalogName, schemaName, getTreeNodeTable(), null,
				new ArrayList<String>(Arrays.asList("SeqNo")),
				new ArrayList<String>(Arrays.asList(Integer.toString(newSeqNo))),
				new ArrayList<String>(Arrays.asList(whereClause)));
		stmt = dbConnection.setStatement();
		if (dbConnection.executeUpdate(stmt, sqlCommand, true, false)==null)
			isError=true;
		dbConnection.releaseStatement(stmt);

		// bump up location of all following nodes
		if (isOccupied && !isError) {
			whereClause = new StringBuffer("AD_Tree_ID=").append(getTreeID()).append(" AND Parent_ID=").append(getParentID()).append(" AND Node_ID IN ")
				.append("(SELECT Node_ID FROM ").append(getTreeNodeTable())
				.append(" WHERE AD_Tree_ID=").append(getTreeID())
				.append(" AND Parent_ID=").append(getParentID())
				.append(" AND Node_ID!=").append(getNodeID())
				.append(" AND SeqNo>=").append(newSeqNo).append(")").toString();
			sqlCommand = s_dbEngine.sql_update(vendorName, catalogName, schemaName, getTreeNodeTable(), null,
					new ArrayList<String>(Arrays.asList("SeqNo")),
					new ArrayList<String>(Arrays.asList("SeqNo+1")),
					new ArrayList<String>(Arrays.asList(whereClause)));
			stmt = dbConnection.setStatement();
			if (dbConnection.executeUpdate(stmt, sqlCommand, true, false)==null)
				isError=true;
			dbConnection.releaseStatement(stmt);
		}

		return !isError;
	}

	/**
	 * convert null value to zero
	 * @param i the Integer to convert
	 * @return value of Integer or 0
	 */
	private int nullToZero(Integer i) {
		if (i==null)
			return 0;
		return i.intValue();
	}

	/**
	 * convert null value to empty string
	 * @param s the String to convert
	 * @return value of String or ''
	 */
	private String nullToBlank (String s) {
		if (s==null)
			return "";
		return s;
	}

	/**
	 * @return the treeNodeTable
	 */
	public String getTreeNodeTable() {
		return nullToBlank(m_treeNodeTable);
	}

	/**
	 * @param treeNodeTable the treeNodeTable to set
	 */
	public void setTreeNodeTable(String treeNodeTable) {
		m_treeNodeTable = treeNodeTable;
	}

	/**
	 * @return the nodeID
	 */
	public int getNodeID() {
		return nullToZero(m_nodeID);
	}

	/**
	 * @param nodeID the nodeID to set
	 */
	public void setNodeID(Integer nodeID) {
		m_nodeID = nullToZero(nodeID);
	}

	/**
	 * @param treeID the treeID to set
	 */
	public void setTreeID(Integer treeID) {
		m_treeID = treeID;
	}

	/**
	 * @return the treeID
	 */
	public Integer getTreeID() {
		return m_treeID;
	}

	/**
	 * @return the parentID
	 */
	public int getParentID() {
		return nullToZero(m_parentID);
	}

	/**
	 * @param parentID the parentID to set
	 */
	public void setParentID(Integer parentID) {
		m_parentID = parentID;
	}

	/**
	 * @return the seqNo
	 */
	public int getSeqNo() {
		return nullToZero(m_seqNo);
	}

	/**
	 * @param seqNo the seqNo to set
	 */
	public void setSeqNo(Integer seqNo) {
		m_seqNo = seqNo;
	}

	/**
	 * @return the previousNodeID
	 */
	public int getPreviousNodeID() {
		return nullToZero(m_previousNodeID);
	}

	/**
	 * @param previousNodeID the previousNodeID to set
	 */
	public void setPreviousNodeID(Integer previousNodeID) {
		m_previousNodeID = previousNodeID;
	}

	/**
	 * @return the nextNodeID
	 */
	public int getNextNodeID() {
		return nullToZero(m_nextNodeID);
	}

	/**
	 * @param nextNodeID the nextNodeID to set
	 */
	public void setNextNodeID(Integer nextNodeID) {
		m_nextNodeID = nextNodeID;
	}

}
