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
package org.compiere.model;

import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import javax.sql.RowSet;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.print.MPrintColor;
import org.compiere.util.CCache;
import org.compiere.util.CLogMgt;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Util;

/**
 *  Builds Tree.
 *  Creates tree structure - maintained in VTreePanel
 *
 *  @author     Jorg Janke
 *  @author victor.perez@e-evoluton.com, www.e-evolution.com
 *  	<li>FR [ 3426137 ] Smart Browser
 * 		https://sourceforge.net/tracker/?func=detail&aid=3426137&group_id=176962&atid=879335 
 * 	@author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 9223372036854775807 ] Add Dynamic-Tree Functionality
 *		@see https://adempiere.atlassian.net/browse/ADEMPIERE-442
 *	@author Trifon Trifonov
 *		<li> FR [ #351 ] Add Account number to Account Tree view
 *		@see https://github.com/adempiere/adempiere/issues/351
 */
public class MTree extends X_AD_Tree
{
	
	private static final long serialVersionUID = -6412057411585787707L;

	/** Is Tree editable    	*/
	private boolean 	isTreeEditable = false;

	/** Buffer while loading tree   */
	private ArrayList<MTreeNode> treeNodes = new ArrayList<>();

	/** Prepared Statement for Node Details */
	private RowSet 		nodeRowSet;
	/** Root Node                   */
	private MTreeNode           rootNode = null;
	
	private HashMap<Integer, ArrayList<Integer>> nodeIdMap;

	/**	Logger			*/
	private static CLogger logger = CLogger.getCLogger(MTree.class);
	/**	Cache						*/
	private static CCache<Integer,MTree> s_cache = new CCache<Integer,MTree>("AD_Tree", 10);
	/**	Cache from String Key	*/
	private static CCache<String, Integer> treeIdFromStringKey = new CCache<String, Integer>("AD_Tree_Table_ID", 10);
	
	/**************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param AD_Tree_ID id
	 *	@param trxName transaction
	 */
	public MTree (Properties ctx, int AD_Tree_ID, String trxName)
	{
		super(ctx, AD_Tree_ID, trxName);
		if (AD_Tree_ID == 0)
		{
		//	setName (null);
		//	setTreeType (null);
			setIsAllNodes (true);	//	complete tree
			setIsDefault(false);
		}
	}	//	MTree_Base

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MTree (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MTree_Base

	/**
	 * 	Parent Constructor
	 *	@param client client
	 *	@param name name
	 *	@param treeType
	 */
	public MTree (MClient client, String name, String treeType)
	{
		this (client.getCtx(), 0, client.get_TrxName());
		setClientOrg (client);
		setName (name);
		setTreeType (treeType);
	}	//	MTree_Base

	/**
	 * 	Full Constructor
	 *	@param ctx context
	 *	@param Name name
	 *	@param TreeType tree type
	 *	@param trxName transaction
	 */
	public MTree (Properties ctx, String Name, String TreeType,  
		String trxName)
	{
		super(ctx, 0, trxName);
		setName (Name);
		setTreeType (TreeType);
		setIsAllNodes (true);	//	complete tree
		setIsDefault(false);
	}	//	MTree_Base

	/**
	 *  Construct & Load Tree
	 *  @param treeId   The tree to build
	 *  @param editable     True, if tree can be modified
	 *  - includes inactive and empty summary nodes
	 * 	@param ctx context for security
	 *	@param clientTree the tree is displayed on the java client (not on web)
	 *  @param trxName transaction
	 */
	public MTree (Properties ctx, int treeId, boolean editable, String trxName) {
		this (ctx, treeId, editable, false, trxName);
	}   //  MTree

	/**
	 * With optional all nodes
	 * @param ctx
	 * @param treeId
	 * @param editable
	 * @param allNodes
	 * @param trxName
	 */
	public MTree (Properties ctx, int treeId, boolean editable, boolean allNodes, String trxName) {
		this(ctx, treeId, editable, allNodes, null, trxName);
	}   //  MTree
	
	/**
	 * Instance tree with where clause
	 * @param ctx
	 * @param treeId
	 * @param editable
	 * @param allNodes
	 * @param whereClause
	 * @param trxName
	 */
	public MTree (Properties ctx, int treeId, 
			boolean editable, boolean allNodes, String whereClause, String trxName)
	{
		this (ctx, treeId, trxName);
		isTreeEditable = editable;
		int userId;
		if (allNodes)
			userId = -1;
		else
			userId = Env.getContextAsInt(ctx, "AD_User_ID");
		log.info("AD_Tree_ID=" + treeId
				+ ", AD_User_ID=" + userId 
				+ ", Editable=" + editable);
		//
		//	Yamel Senih [ 9223372036854775807 ]
		//	Add support to where clause
		loadNodes(userId, whereClause);
	}   //  MTree

	@Override
	public void setTreeType(String TreeType) {
		super.setTreeType(TreeType);
		setTable_ID(TreeType);
	}
	
	/**
	 * Get Table ID from treeType Back support
	 * @param treeType
	 * @return
	 */
	public static int getTableIdFromTreeType(String treeType) {
		int tableId = 0;
		if(TREETYPE_Activity.equals(treeType))
			tableId = X_C_Activity.Table_ID;
		else if (TREETYPE_BPartner.equals(treeType))
			tableId = X_C_BPartner.Table_ID;
		else if (TREETYPE_Campaign.equals(treeType))
			tableId = X_C_Campaign.Table_ID;
		else if (TREETYPE_CMContainer.equals(treeType))
			tableId = X_CM_Container.Table_ID;
		else if (TREETYPE_CMContainerStage.equals(treeType))
			tableId = X_CM_CStage.Table_ID;
		else if (TREETYPE_CMMedia.equals(treeType))
			tableId = X_CM_Media.Table_ID;
		else if (TREETYPE_CMTemplate.equals(treeType))
			tableId = X_CM_Template.Table_ID;
		else if (TREETYPE_ElementValue.equals(treeType)
				|| TREETYPE_User1.equals(treeType)
				|| TREETYPE_User2.equals(treeType)
				|| TREETYPE_User3.equals(treeType)
				|| TREETYPE_User4.equals(treeType))
			tableId = X_C_ElementValue.Table_ID;
		else if (TREETYPE_Organization.equals(treeType))
			tableId = X_AD_Org.Table_ID;
		else if (TREETYPE_Product.equals(treeType))
			tableId = X_M_Product.Table_ID;
		else if (TREETYPE_ProductCategory.equals(treeType))
			tableId = X_M_Product_Category.Table_ID;
		else if (TREETYPE_BoM.equals(treeType))
			tableId = X_M_BOM.Table_ID;
		else if (TREETYPE_Project.equals(treeType))
			tableId = X_C_Project.Table_ID;
		else if (TREETYPE_SalesRegion.equals(treeType))
			tableId = X_C_SalesRegion.Table_ID;
		if(TREETYPE_Menu.equals(treeType))
			tableId = X_AD_Menu.Table_ID;
		//	Default return
		return tableId;
	}
	
	/**
	 * Get Source Table Name
	 * @param treeType
	 * @return
	 */
	public static String getSourceTableName(String treeType) {
		if (treeType == null)
			return null;
		String sourceTable = null;
		if (treeType.equals(TREETYPE_Menu))
			sourceTable = X_AD_Menu.Table_Name;
		else if (treeType.equals(TREETYPE_Organization))
			sourceTable = X_AD_Org.Table_Name;
		else if (treeType.equals(TREETYPE_Product))
			sourceTable = X_M_Product.Table_Name;
		else if (treeType.equals(TREETYPE_ProductCategory))
			sourceTable = X_M_Product_Category.Table_Name;
		else if (treeType.equals(TREETYPE_BoM))
			sourceTable = X_M_BOM.Table_Name;
		else if (treeType.equals(TREETYPE_BPartner))
			sourceTable = X_C_BPartner.Table_Name;
		else if (treeType.equals(TREETYPE_Campaign))
			sourceTable = X_C_Campaign.Table_Name;
		else if (treeType.equals(TREETYPE_Project))
			sourceTable = X_C_Project.Table_Name;
		else if (treeType.equals(TREETYPE_Activity))
			sourceTable = X_C_Activity.Table_Name;
		else if (treeType.equals(TREETYPE_SalesRegion))
			sourceTable = X_C_SalesRegion.Table_Name;
		else if (treeType.equals(TREETYPE_CMContainer))
			sourceTable = X_CM_Container.Table_Name;
		else if (treeType.equals(TREETYPE_CMContainerStage))
			sourceTable = X_CM_CStage.Table_Name;
		else if (treeType.equals(TREETYPE_CMMedia))
			sourceTable = X_CM_Media.Table_Name;
		else if (treeType.equals(TREETYPE_CMTemplate))
			sourceTable = X_CM_Template.Table_Name;
		//	User Trees
		// afalcone [Bugs #1837219]
		else if (treeType.equals(TREETYPE_ElementValue)
				|| treeType.equals(TREETYPE_User1)
				|| treeType.equals(TREETYPE_User2)
				|| treeType.equals(TREETYPE_User3)
				|| treeType.equals(TREETYPE_User4))
			sourceTable = X_C_ElementValue.Table_Name;
		// end afalcone
		return sourceTable;
	}
	
	/**
	 * 	Get Source TableName
	 *	@param treeType tree typw
	 *	@return source table name, e.g. AD_Org or null 
	 */
	public String getSourceTableName() {
		if(getTreeType().equals(TREETYPE_CustomTree)) {
			if(getAD_Table_ID() == 0) {
				throw new AdempiereException("@AD_Table_ID@ @NotFound@");
			}
			MTable table = MTable.get(getCtx(), getAD_Table_ID());
			if(table == null) {
				throw new AdempiereException("@AD_Table_ID@ @NotFound@");
			}
			//	Default
			return table.getTableName();
		}
		//	Get Tree Type
		return getSourceTableName(getTreeType());
	}	//	getSourceTableName
	
	/**************************************************************************
	 * 	Get Node TableName
	 *	@param treeType tree type
	 *	@return node table name, e.g. AD_TreeNode
	 */
	//	Yamel Senih [ 9223372036854775807 ]
	//	Change by Table Identifier
	public static String getNodeTableName(int tableId) {
		String	nodeTableName = tableId == 0? null: "AD_TreeNode";
		if (X_AD_Menu.Table_ID == tableId)
			nodeTableName += "MM";
		else if  (X_C_BPartner.Table_ID == tableId)
			nodeTableName += "BP";
		else if  (X_M_Product.Table_ID == tableId)
			nodeTableName += "PR";
		//
		else if  (X_CM_Container.Table_ID == tableId)
			nodeTableName += "CMC";
		else if  (X_CM_CStage.Table_ID == tableId)
			nodeTableName += "CMS";
		else if  (X_CM_Media.Table_ID == tableId)
			nodeTableName += "CMM";
		else if  (X_CM_Template.Table_ID == tableId)
			nodeTableName += "CMT";
		//	Add Support to Tables
		else {
			if (s_TableIDs == null)
				fillUserTables(null);
			Integer ii = Integer.valueOf(tableId);
			if (s_TableIDs.contains(ii)) {
				if  (s_TableIDs_U1.contains(ii))
					nodeTableName += "U1";
				else if (s_TableIDs_U2.contains(ii))
					nodeTableName += "U2";
				else if (s_TableIDs_U3.contains(ii))
					nodeTableName += "U3";
				else if (s_TableIDs_U4.contains(ii))
					nodeTableName += "U4";
			}
		}
		return nodeTableName;
	}	//	getNodeTableName
	
	/**
	 * Support to old version
	 * @param treeType
	 * @return
	 */
	public static String getNodeTableName(String treeType) {
		
		String	nodeTableName = "AD_TreeNode";
		if (TREETYPE_Menu.equals(treeType))
			nodeTableName += "MM";
		else if  (TREETYPE_BPartner.equals(treeType))
			nodeTableName += "BP";
		else if  (TREETYPE_Product.equals(treeType))
			nodeTableName += "PR";
		//
		else if  (TREETYPE_CMContainer.equals(treeType))
			nodeTableName += "CMC";
		else if  (TREETYPE_CMContainerStage.equals(treeType))
			nodeTableName += "CMS";
		else if  (TREETYPE_CMMedia.equals(treeType))
			nodeTableName += "CMM";
		else if  (TREETYPE_CMTemplate.equals(treeType))
			nodeTableName += "CMT";
		else if  (TREETYPE_User1.equals(treeType))
		nodeTableName += "U1";
		else if  (TREETYPE_User2.equals(treeType))
			nodeTableName += "U2";
		else if  (TREETYPE_User3.equals(treeType))
			nodeTableName += "U3";
		else if  (TREETYPE_User4.equals(treeType))
			nodeTableName += "U4";
		return nodeTableName;
	}	//	getNodeTableName
	
	/**
	 * Set Table ID of type tree
	 * @param treeType
	 * @return void
	 */
	private void setTable_ID(String treeType) {
		if(getAD_Table_ID() > 0) {
			return;
		}
		//	Set Table
		setAD_Table_ID(getTableIdFromTreeType(treeType));
	}
	
	/** All Table Names with tree		*/
	private static ArrayList<String> s_TableNames = null;
	/** All Table IDs with tree			*/
	private static List<Integer> s_TableIDs = null;
	/** U1 Table IDs					*/
	private static List<Integer> s_TableIDs_U1 = null;
	/** U2 Table IDs					*/
	private static List<Integer> s_TableIDs_U2 = null;
	/** U3 Table IDs					*/
	private static List<Integer> s_TableIDs_U3 = null;
	/** U4 Table IDs					*/
	private static List<Integer> s_TableIDs_U4 = null;
	
	/** Tree Type Array		*/
	private static final String[]	TREETYPES = new String[] {
		TREETYPE_Activity,
		TREETYPE_BoM,
		TREETYPE_BPartner,
		TREETYPE_CMContainer,
		TREETYPE_CMMedia,
		TREETYPE_CMContainerStage,
		TREETYPE_CMTemplate,
		TREETYPE_ElementValue,
		TREETYPE_Campaign,
		TREETYPE_Menu,
		TREETYPE_Organization,
		TREETYPE_ProductCategory,
		TREETYPE_Project,
		TREETYPE_Product,
		TREETYPE_SalesRegion,
		TREETYPE_User1,
		TREETYPE_User2,
		TREETYPE_User3,
		TREETYPE_User4,
		TREETYPE_CustomTree
	};
	/** Table ID Array				*/
	private static final int[]		TABLEIDS = new int[] {
		X_C_Activity.Table_ID,
		X_M_BOM.Table_ID,
		X_C_BPartner.Table_ID,
		X_CM_Container.Table_ID,
		X_CM_Media.Table_ID,
		X_CM_CStage.Table_ID,
		X_CM_Template.Table_ID,
		X_C_ElementValue.Table_ID,
		X_C_Campaign.Table_ID,
		X_AD_Menu.Table_ID,
		X_AD_Org.Table_ID,
		X_M_Product_Category.Table_ID,
		X_C_Project.Table_ID,
		X_M_Product.Table_ID,
		X_C_SalesRegion.Table_ID,
		0,0,0,0,0
	};
	
	/**
	 * 	Fill User Tables
	 * 	@param trx transaction
	 */
	static synchronized void fillUserTables (String trxName) {
		s_TableNames = new ArrayList<String>();
		s_TableIDs = new ArrayList<Integer>();
		s_TableIDs_U1 = new ArrayList<Integer>();
		s_TableIDs_U2 = new ArrayList<Integer>();
		s_TableIDs_U3 = new ArrayList<Integer>();
		s_TableIDs_U4 = new ArrayList<Integer>();
		//	
		boolean withTable = MColumn.getColumn_ID(I_AD_Tree.Table_Name, I_AD_Tree.COLUMNNAME_AD_Table_ID) > 0;
		if(!withTable) {
			return;
		}
		//
		boolean error = false;
		//
		KeyNamePair [] treeArray = DB.getKeyNamePairs("SELECT DISTINCT AD_Table_ID, TreeType FROM AD_Tree", false);
		for(KeyNamePair pair : treeArray) {
			int tableId = pair.getKey();
			String treeType = pair.getName();
			if (tableId <= 0)
				continue;
			s_TableIDs.add(tableId);		//	all
			if (treeType.equals ("U1"))
				s_TableIDs_U1.add(tableId);
			else if (treeType.equals ("U2"))
				s_TableIDs_U2.add(tableId);
			else if (treeType.equals ("U3"))
				s_TableIDs_U3.add(tableId);
			else if (treeType.equals ("U4"))
				s_TableIDs_U4.add(tableId);
		}
		//	Not updated
		if (!error && s_TableIDs.size() < 3)
		{
			MTree xx = get (Env.getCtx(), 10, trxName);
			xx.updateTrees();
			fillUserTables(null);
		}
	}	//	fillUserTables
	
	/**
	 * 	Update all Trees with Table_ID
	 */
	public void updateTrees()
	{
		setAD_Table_ID();
		for (int i = 0; i < TREETYPES.length; i++)
		{
			if (!updateTrees (TREETYPES[i], TABLEIDS[i]))
				break;
		}
	}	//	updateTrees
	
	/**
	 * 	Update Trees
	 *	@param treeType tree type
	 *	@param AD_Table_ID table
	 *	@return true if no error
	 */
	private boolean updateTrees(String treeType, int AD_Table_ID)
	{
		if (AD_Table_ID == 0)
			return true;
		StringBuffer sb = new StringBuffer("UPDATE AD_Tree SET AD_Table_ID=")
			.append (AD_Table_ID)
			.append (" WHERE TreeType='").append (treeType).append ("' AND AD_Table_ID IS NULL");
		int no = DB.executeUpdate(sb.toString(), get_TrxName());
		log.fine (treeType + " #" + no);
		return no >= 0;
	}	//	updateTrees
	
	/**************************************************************************
	 * 	Set AD_Table_ID from TreeType
	 *	@return AD_Table_ID
	 */
	private int setAD_Table_ID() {
		int tableId = 0;
		String type = getTreeType();
		if (type == null
			|| type.startsWith ("U")	//	User
			|| type.equals (TREETYPE_CustomTree))
			return 0;
		for (int i = 0; i < TREETYPES.length; i++) {
			if (type.equals (TREETYPES[i])) {
				tableId = TABLEIDS[i];
				break;
			}
		}
		if (tableId != 0)
			setAD_Table_ID (tableId);
		if (tableId == 0)
			log.warning ("Did not find Table for TreeType=" + type);
		return tableId;
	}	//	setAD_Table_ID
	
	/**
	 * 	Table has Tree
	 *	@param testTableId table
	 *	@return true if table has tree
	 */
	static public boolean hasTree (int testTableId) {
		for (int tableId : TABLEIDS) {
			if(tableId == testTableId) {
				return true;
			}
		}
		if(s_TableIDs == null)
			fillUserTables(null);
		Integer ii = Integer.valueOf(testTableId);
		return s_TableIDs.contains(ii);
	}	//	hasTree
	
	/**
	 * 	Table has Tree
	 *	@param tableName table
	 *	@return true if table has tree
	 */
	static public boolean hasTree (String tableName) {
		if (s_TableNames == null)
			fillUserTables(null);
		return s_TableNames.contains(tableName);
	}	//	hasTree

	/**
	 * Get Tree Id from table Id
	 * @param clientId
	 * @param tableId
	 * @return
	 */
	public static int getDefaultTreeIdFromTableId(int clientId, int tableId) {
		return getDefaultTreeIdFromTableId(clientId, tableId, 0);
	}
	
	/**
	 * Get from table Id, can find on cache
	 * @param clientId
	 * @param tableId
	 * @param elementId
	 * @return
	 */
	public static int getDefaultTreeIdFromTableId(int clientId, int tableId, int elementId) {
		if(tableId <= 0) {
			return -1;
		}
		//	
		logger.finer("TableId=" + tableId);
		String key = "TableId" + "|" + clientId + "|" + tableId;
		Integer treeId = treeIdFromStringKey.get(key);
		String whereClause = new String();
		//	Valid Element
		if (elementId != 0) {
			whereClause = " AND EXISTS (SELECT 1 FROM C_Element ae WHERE ae.C_Element_ID="
			+ elementId + " AND tr.AD_Tree_ID=ae.AD_Tree_ID) ";
		}
		if(treeId == null
				|| treeId == 0) {
			String sql = "SELECT tr.AD_Tree_ID "
					+ "FROM AD_Tree tr "
					+ "WHERE tr.AD_Client_ID = ? "
					+ "AND tr.AD_Table_ID = ? "
					+ "AND tr.IsActive='Y' "
					+ "AND tr.IsAllNodes='Y' "
					+ whereClause
					+ "ORDER BY tr.IsDefault DESC";
				//	Get Tree
			treeId = DB.getSQLValue(null, sql, clientId, tableId);
			if(treeId > 0) {
				treeIdFromStringKey.put(key, treeId);
			}
		}
		
		//	Default Return
		return treeId;
	}   //  getDefaultTreeIdFromTableId
	
	/**
	 * Get tree id from table name
	 * @param clientId
	 * @param tableName
	 * @return
	 */
	public static int getDefaultTreeIdFromTableName(int clientId, String tableName) {
		return getDefaultTreeIdFromTableName(clientId, tableName, 0);
	}
	
	/**
	 * Get Tree Id from Table Name
	 * @param clientId
	 * @param tableName
	 * @param elementId
	 * @return
	 */
	public static int getDefaultTreeIdFromTableName(int clientId, String tableName, int elementId) {
		if(Util.isEmpty(tableName)) {
			return -1;
		}
		//	
		logger.finer("TableName=" + tableName);
		String key = "TableName|" + tableName;
		Integer treeId = treeIdFromStringKey.get(key);
		String whereClause = new String();
		//	Valid Element
		if (elementId != 0) {
			whereClause = " AND EXISTS (SELECT 1 FROM C_Element ae WHERE ae.C_Element_ID="
			+ elementId + " AND tr.AD_Tree_ID=ae.AD_Tree_ID) ";
		}
		if(treeId == null
				|| treeId == 0) {
			String sql = "SELECT tr.AD_Tree_ID "
					+ "FROM AD_Tree tr "
					+ "INNER JOIN AD_Table tb ON (tr.AD_Table_ID=tb.AD_Table_ID) "
					+ "WHERE tr.AD_Client_ID IN(0, ?) "
					+ "AND tb.TableName=? "
					+ "AND tr.IsActive='Y' "
					+ "AND tr.IsAllNodes='Y' "
					+ whereClause
					+ "ORDER BY tr.IsDefault DESC, tr.AD_Tree_ID";
				//	Get Tree
			treeId = DB.getSQLValue(null, sql, clientId, tableName);
			if(treeId > 0) {
				treeIdFromStringKey.put(key, treeId);
			}
		}
		//	Default Return
		return treeId;
	}   //  getDefaultTreeIdFromTableName
	
	/*************************************************************************
	 *  Load Nodes and Bar
	 * 	@param userId user for tree bar
	 * 	@param whereClause
	 */
	private void loadNodes (int userId, String whereClause) {
		String fromClause = getSourceTableName();
		//  SQL for TreeNodes
		StringBuffer sql = new StringBuffer("SELECT "
			+ "tn.Node_ID,tn.Parent_ID,tn.SeqNo,tb.IsActive "
			+ "FROM ").append(getNodeTableName()).append(" tn ")
			.append("LEFT JOIN ")
				.append(fromClause).append(" ON(")
					.append(fromClause).append(".").append(fromClause + "_ID").append(" = tn.Node_ID) ")
			//
			.append(" LEFT OUTER JOIN AD_TreeBar tb ON (tn.AD_Tree_ID=tb.AD_Tree_ID"
			+ " AND tn.Node_ID=tb.Node_ID "
			+ (userId != -1 ? " AND tb.AD_User_ID=? ": "") 	//	#1 (conditional)
			+ ") "
			+ "WHERE tn.AD_Tree_ID=?");								//	#2
		if (!isTreeEditable)
			sql.append(" AND tn.IsActive='Y'");
		//	Add GridTab Where Class
		if(whereClause != null
				&& whereClause.length() > 0)
			sql.append(" AND ").append(whereClause);
		//	End Yamel Senih
		sql.append(" ORDER BY COALESCE(tn.Parent_ID, -1), tn.SeqNo");
		log.finest(sql.toString());
		//  The Node Loop
		try
		{
			// load Node details - addToTree -> getNodeDetail
			getNodeDetails(); 
			//
			PreparedStatement pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			int idx = 1;
			if (userId != -1)
				pstmt.setInt(idx++, userId);
			pstmt.setInt(idx++, getAD_Tree_ID());
			//	Get Tree & Bar
			ResultSet rs = pstmt.executeQuery();
			rootNode = new MTreeNode (0, 0, getName(), getDescription(), 0, true, null, false, null);
			while (rs.next())
			{
				int node_ID = rs.getInt(1);
				int parent_ID = rs.getInt(2);
				int seqNo = rs.getInt(3);
				boolean onBar = (rs.getString(4) != null);
				//
				if (node_ID == 0 && parent_ID == 0)
					;
				else
					addToTree (node_ID, parent_ID, seqNo, onBar);	//	calls getNodeDetail
			}
			rs.close();
			pstmt.close();
			//
			//closing the rowset will also close connection for oracle rowset implementation
			//nodeRowSet.close();
			nodeRowSet = null;
			nodeIdMap = null;
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql.toString(), e);
			nodeRowSet = null;
			nodeIdMap = null;
		}
			
		//  Done with loading - add remainder from buffer
		if (treeNodes.size() != 0)
		{
			log.finest("clearing buffer - Adding to: " + rootNode);
			for (int i = 0; i < treeNodes.size(); i++)
			{
				MTreeNode node = (MTreeNode) treeNodes.get(i);
				MTreeNode parent = rootNode.findNode(node.getParent_ID());
				if (parent != null && parent.getAllowsChildren())
				{
					parent.add(node);
					int sizeBeforeCheckBuffer = treeNodes.size();
					checkBuffer(node);
					if (sizeBeforeCheckBuffer == treeNodes.size())
						treeNodes.remove(i);
					i = -1;		//	start again with i=0
				}
			}
		}

		//	Nodes w/o parent
		if (treeNodes.size() != 0)
		{
			log.severe ("Nodes w/o parent - adding to root - " + treeNodes);
			for (int i = 0; i < treeNodes.size(); i++)
			{
				MTreeNode node = (MTreeNode) treeNodes.get(i);
				rootNode.add(node);
				int sizeBeforeCheckBuffer = treeNodes.size();
				checkBuffer(node);
				if (sizeBeforeCheckBuffer == treeNodes.size())
					treeNodes.remove(i);
				i = -1;
			}
			if (treeNodes.size() != 0)
				log.severe ("Still nodes in Buffer - " + treeNodes);
		}	//	nodes w/o parents

		//  clean up
		if (!isTreeEditable && rootNode.getChildCount() > 0)
			trimTree();
//		diagPrintTree();
		if (CLogMgt.isLevelFinest() || rootNode.getChildCount() == 0)
			log.fine("ChildCount=" + rootNode.getChildCount());
	}   //  loadNodes

	
	/**
	 *  Add Node to Tree.
	 *  If not found add to buffer
	 *  @param nodeId Node_ID
	 *  @param parentId Parent_ID
	 *  @param seqNo SeqNo
	 *  @param onBar on bar
	 */
	private void addToTree (int nodeId, int parentId, int seqNo, boolean onBar)
	{
		//  Create new Node
		MTreeNode child = getNodeDetail (nodeId, parentId, seqNo, onBar);
		if (child == null)
			return;

		//  Add to Tree
		MTreeNode parent = null;
		if (rootNode != null)
			parent = rootNode.findNode (parentId);
		//  Parent found
		if (parent != null && parent.getAllowsChildren())
		{
			parent.add(child);
			//  see if we can add nodes from buffer
			if (treeNodes.size() > 0)
				checkBuffer(child);
		}
		else
			treeNodes.add(child);
	}   //  addToTree

	/**
	 *  Check the buffer for nodes which have newNode as Parents
	 *  @param newNode new node
	 */
	private void checkBuffer (MTreeNode newNode)
	{
		//	Ability to add nodes
		if (!newNode.isSummary() || !newNode.getAllowsChildren())
			return;
		//
		for (int i = 0; i < treeNodes.size(); i++)
		{
			MTreeNode node = (MTreeNode) treeNodes.get(i);
			if (node.getParent_ID() == newNode.getNode_ID())
			{
				try
				{
					newNode.add(node);
				}
				catch (Exception e)
				{
					log.severe("Adding " + node.getName() 
						+ " to " + newNode.getName() + ": " + e.getMessage());
				}
				treeNodes.remove(i);
				i--;
			}
		}
	}   //  checkBuffer
	
	/**
	 * 	Get MTree_Base from Cache
	 *	@param ctx context
	 *	@param AD_Tree_ID id
	 *	@param trxName transaction
	 *	@return MTree_Base
	 */
	public static MTree get (Properties ctx, int AD_Tree_ID, String trxName)
	{
		Integer key = new Integer (AD_Tree_ID);
		MTree retValue = (MTree) s_cache.get (key);
		if (retValue != null)
			return retValue;
		retValue = new MTree (ctx, AD_Tree_ID, trxName);
		if (retValue.get_ID () != 0)
			s_cache.put (key, retValue);
		return retValue;
	}	//	get

	/**
	 *	Get Node TableName
	 *	@return node table name, e.g. AD_TreeNode
	 */
	public String getNodeTableName() {
		//	Yamel Senih, 2015-11-14
		//	Add support to old version
		String tableName = getNodeTableName(getTreeType());
		if(tableName == null) {
			tableName = getNodeTableName(getAD_Table_ID());
		}
		//	Return
		return tableName;
	}	//	getNodeTableName
	
	/**
	 * 	Get fully qualified Name of Action/Color Column
	 *	@return NULL or Action or Color
	 */
	public String getActionColorName()
	{
		//	Yamel Senih [ 9223372036854775807 ]
		String tableName = getSourceTableName();
		if ("AD_Menu".equals(tableName))
			return "t.Action";
		return "NULL";
	}	//	getSourceTableName
	
	/**************************************************************************
	 *  Get Node Detail.
	 * 	Loads data into RowSet m_nodeRowSet
	 *  Columns:
	 * 	- ID
	 *  - Name
	 *  - Description
	 *  - IsSummary
	 *  - ImageIndicator
	 * 	- additional for Menu
	 *  Parameter:
	 *  - Node_ID
	 *  The SQL contains security/access control
	 */
	private void getNodeDetails () {
		//  SQL for Node Info
		StringBuffer sqlNode = new StringBuffer();
		String sourceTable = "t";
		String fromClause = getSourceTableName();
		String color = getActionColorName();
		if (getTreeType().equals(TREETYPE_Menu)) {
			boolean base = Env.isBaseLanguage(p_ctx, "AD_Menu");
			sourceTable = "m";
			if (base) {
				sqlNode.append("SELECT m.AD_Menu_ID, m.Name,m.Description,m.IsSummary,m.Action, "
					+ "m.AD_Window_ID, m.AD_Process_ID, m.AD_Form_ID, m.AD_Workflow_ID, m.AD_Task_ID, m.AD_Workbench_ID "
					+ ", m.AD_Browse_ID "
					+ "FROM AD_Menu m");
			} else {
				sqlNode.append("SELECT m.AD_Menu_ID,  t.Name,t.Description,m.IsSummary,m.Action, "
					+ "m.AD_Window_ID, m.AD_Process_ID, m.AD_Form_ID, m.AD_Workflow_ID, m.AD_Task_ID, m.AD_Workbench_ID "
					+ ", m.AD_Browse_ID "
					+ "FROM AD_Menu m "
					+ "LEFT JOIN AD_Menu_Trl t ON(t.AD_Menu_ID = m.AD_Menu_ID AND t.AD_Language='").append(Env.getAD_Language(p_ctx)).append("') ");
			}
			//	
			if (!isTreeEditable) {
				boolean hasWhere = sqlNode.indexOf(" WHERE ") != -1;
				sqlNode.append(hasWhere ? " AND " : " WHERE ").append("m.IsActive='Y' ");
			}
			//	Do not show Beta
			if (!MClient.get(getCtx()).isUseBetaFunctions())
			{
				boolean hasWhere = sqlNode.indexOf(" WHERE ") != -1;
				sqlNode.append(hasWhere ? " AND " : " WHERE ");
				sqlNode.append("(m.AD_Window_ID IS NULL OR EXISTS (SELECT 1 FROM AD_Window w WHERE m.AD_Window_ID=w.AD_Window_ID AND w.IsBetaFunctionality='N'))")
					.append(" AND (m.AD_Process_ID IS NULL OR EXISTS (SELECT 1 FROM AD_Process p WHERE m.AD_Process_ID=p.AD_Process_ID AND p.IsBetaFunctionality='N'))")
					.append(" AND (m.AD_Workflow_ID IS NULL OR EXISTS (SELECT 1 FROM AD_Workflow wf WHERE m.AD_Workflow_ID=wf.AD_Workflow_ID AND wf.IsBetaFunctionality='N'))")
					.append(" AND (m.AD_Form_ID IS NULL OR EXISTS (SELECT 1 FROM AD_Form f WHERE m.AD_Form_ID=f.AD_Form_ID AND f.IsBetaFunctionality='N'))")
					.append(" AND (m.AD_Browse_ID IS NULL OR EXISTS (SELECT 1 FROM AD_Browse b WHERE m.AD_Browse_ID=b.AD_Browse_ID AND b.IsBetaFunctionality='N'))");
			}
			//	In R/O Menu - Show only defined Forms
			if (!isTreeEditable) {
				boolean hasWhere = sqlNode.indexOf(" WHERE ") != -1;
				sqlNode.append(hasWhere ? " AND " : " WHERE ");
				sqlNode.append("(m.AD_Form_ID IS NULL OR EXISTS (SELECT 1 FROM AD_Form f WHERE m.AD_Form_ID=f.AD_Form_ID AND (f.Classname IS NOT NULL OR f.JSPURL IS NOT NULL)))");
			}
		} 
		//	Yamel Senih [ 9223372036854775807 ]
		//	Load for Custom Tree
		else if(getTreeType().equals(TREETYPE_CustomTree)) {
			boolean base = Env.isBaseLanguage(p_ctx, fromClause);
			sourceTable = "t";
			String recordClause = "";
			if (base){
				sqlNode.append("SELECT t." + fromClause + "_ID, ")
					.append("t.Name, t.Description, t.IsSummary, " + color + " AS Action ")
					.append(recordClause.length() > 0 ? ", " + recordClause : "")
					.append(" FROM ").append(fromClause).append(" AS t ")
					;
			} else {
				sqlNode.append("SELECT t." + fromClause + "_ID, ")
					.append("COALESCE(m.Name, t.Name) AS Name, COALESCE(m.Description, t.Description) AS Description, t.IsSummary,  ")
					.append( color + " AS Action ")
					.append(recordClause.length() > 0 ? ", " + recordClause : "")
					.append(" FROM ").append(fromClause).append(" AS t ")
					.append(" LEFT JOIN " + fromClause + "_Trl tt ON (t. " + fromClause + "_ID = t." + fromClause + "_ID AND tt.AD_Language='").append(Env.getAD_Language(p_ctx)).append("'").append(")")
				;
			}
			//	
			if (!isTreeEditable) {
				boolean hasWhere = sqlNode.indexOf(" WHERE ") != -1;
				sqlNode.append(hasWhere ? " AND " : " WHERE ").append("t.IsActive='Y' ");
			}
		}
		//	End Yamel Senih
		else
		{
			if (fromClause == null)
				throw new IllegalArgumentException("Unknown TreeType=" + getTreeType());
			sqlNode.append("SELECT t.").append(fromClause)
				.append("_ID,t.Name,t.Description,t.IsSummary,").append(color);
			if (containsValueColumn(fromClause) ) {
				sqlNode.append(", t.Value"); //@Trifon
			}
			sqlNode.append(" FROM ").append(fromClause).append(" t ");
			if (!isTreeEditable)
				sqlNode.append(" WHERE t.IsActive='Y'");
		}
		String sql = sqlNode.toString();
		if (!isTreeEditable)	//	editable = menu/etc. window
			sql = MRole.getDefault(getCtx(), false).addAccessSQL(sql, 
				sourceTable, MRole.SQL_FULLYQUALIFIED, isTreeEditable);
		log.fine(sql);
		nodeRowSet = DB.getRowSet (sql);
		nodeIdMap = new HashMap<Integer, ArrayList<Integer>>(50);
		try 
		{
			nodeRowSet.beforeFirst();
			int i = 0;
			while (nodeRowSet.next())
			{
				i++;
				int node = nodeRowSet.getInt(1);
				Integer nodeId = Integer.valueOf(node);
				ArrayList<Integer> list = nodeIdMap.get(nodeId);
				if (list == null)
				{
					list = new ArrayList<Integer>(5);
					nodeIdMap.put(nodeId, list);
				}
				list.add(Integer.valueOf(i));
			}
		} catch (SQLException e) 
		{
			log.log(Level.SEVERE, "", e);
		}
	}   //  getNodeDetails

	// @Trifon
	public boolean containsValueColumn(String sourceTableName) {
		boolean result = false;

		MTable treeTable = MTable.get(getCtx(), sourceTableName);
		MColumn valueColumn = treeTable.getColumn("Value");
		if (valueColumn != null && valueColumn.getAD_Column_ID() > 0) {
			result = true;
		}
		return result;
	}

	/**
	 *  Get Menu Node Details.
	 *  As SQL contains security access, not all nodes will be found
	 *  @param  node_ID     Key of the record
	 *  @param  parent_ID   Parent ID of the record
	 *  @param  seqNo       Sort index
	 *  @param  onBar       Node also on Shortcut bar
	 *  @return Node
	 */
	private MTreeNode getNodeDetail (int node_ID, int parent_ID, int seqNo, boolean onBar)
	{
		MTreeNode retValue = null;
		try
		{
			//nodeRowSet.beforeFirst();
			ArrayList<Integer> nodeList = nodeIdMap.get(Integer.valueOf(node_ID));
			int size = nodeList != null ? nodeList.size() : 0;
			int i = 0;
			//while (nodeRowSet.next())
			while (i < size)
			{
				Integer nodeId = nodeList.get(i);
				i++;
				nodeRowSet.absolute(nodeId.intValue());
				int node = nodeRowSet.getInt(1);
				if (node_ID != node)	//	search for correct one
					continue;
				//	ID, Name, Description, IsSummary, Action/Color, Value // @Trifon
				int index = 2;				
				String name = nodeRowSet.getString(index++);
				String description = nodeRowSet.getString(index++);
				boolean isSummary = "Y".equals(nodeRowSet.getString(index++));
				String actionColor = nodeRowSet.getString(index++);
				//	Menu only
				if ((getTreeType().equals(TREETYPE_Menu)) && !isSummary) {
					int AD_Window_ID = nodeRowSet.getInt(index++);
					int AD_Process_ID = nodeRowSet.getInt(index++);
					int AD_Form_ID = nodeRowSet.getInt(index++);
					int AD_Workflow_ID = nodeRowSet.getInt(index++);
					int AD_Task_ID = nodeRowSet.getInt(index++);
					int AD_Workbench_ID = nodeRowSet.getInt(index++);
					int AD_Browse_ID = nodeRowSet.getInt(index++);
					//
					MRole role = MRole.getDefault(getCtx(), false);
					Boolean access = null;
					if (X_AD_Menu.ACTION_Window.equals(actionColor))
						access = role.getWindowAccess(AD_Window_ID);
					else if (X_AD_Menu.ACTION_Process.equals(actionColor) 
						|| X_AD_Menu.ACTION_Report.equals(actionColor))
						access = role.getProcessAccess(AD_Process_ID);
					else if (X_AD_Menu.ACTION_Form.equals(actionColor))
						access = role.getFormAccess(AD_Form_ID);
					else if (X_AD_Menu.ACTION_SmartBrowse.equals(actionColor))
						access = role.getBrowseAccess(AD_Browse_ID);
					else if (X_AD_Menu.ACTION_WorkFlow.equals(actionColor))
						access = role.getWorkflowAccess(AD_Workflow_ID);
					else if (X_AD_Menu.ACTION_Task.equals(actionColor))
						access = role.getTaskAccess(AD_Task_ID);
//					else if (X_AD_Menu.ACTION_Workbench.equals(action))
//						access = role.getWorkbenchAccess(AD_Window_ID);
//					log.fine("getNodeDetail - " + name + " - " + actionColor + " - " + access);
					//
					if (access != null		//	rw or ro for Role 
						|| isTreeEditable)		//	Menu Window can see all
					{
						retValue = new MTreeNode (node_ID, seqNo,
							name, description, parent_ID, isSummary,
							actionColor, onBar, null);	//	menu has no color
					}
				} else if(getTreeType().equals(TREETYPE_CustomTree)) {
					retValue = new MTreeNode (node_ID, seqNo,
							name, description, parent_ID, isSummary,
							actionColor, onBar, null);	//	menu has no color
				}
				else	//	always add
				{
					Color color = null;	//	action
					if (actionColor != null && !getTreeType().equals(TREETYPE_Menu))
					{
						MPrintColor printColor = MPrintColor.get(getCtx(), actionColor);
						if (printColor != null)
							color = printColor.getColor();
					}
					// @Trifon-begin
					if (getTreeType().equals(TREETYPE_ElementValue) ) {
//					String sourceTableName = getSourceTableName(true); // Uncomment it if you want to see Value for all Tree types
//					if ( containsValueColumn( sourceTableName ) ) { // Uncomment it if you want to see Value for all Tree types
						String value = nodeRowSet.getString(index++);
						name = value + " - " + name;
					}// @Trifon-end
					//
					retValue = new MTreeNode (node_ID, seqNo,
						name, description, parent_ID, isSummary,
						null, onBar, color);			//	no action
				}
			}
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, "", e);
		}
		return retValue;
	}   //  getNodeDetails

	
	/**************************************************************************
	 *  Trim tree of empty summary nodes
	 */
	public void trimTree()
	{
		boolean needsTrim = rootNode != null;
		while (needsTrim)
		{
			needsTrim = false;
			Enumeration<MTreeNode> en = rootNode.preorderEnumeration();
			while (rootNode.getChildCount() > 0 && en.hasMoreElements())
			{
				MTreeNode nd = (MTreeNode)en.nextElement();
				if (nd.isSummary() && nd.getChildCount() == 0)
				{
					nd.removeFromParent();
					needsTrim = true;
				}
			}
		}
	}   //  trimTree

	/**
	 *  Diagnostics: Print tree
	 */
	private void dumpTree()
	{
		Enumeration en = rootNode.preorderEnumeration();
		int count = 0;
		while (en.hasMoreElements())
		{
			StringBuffer sb = new StringBuffer();
			MTreeNode nd = (MTreeNode)en.nextElement();
			for (int i = 0; i < nd.getLevel(); i++)
				sb.append(" ");
			sb.append("ID=").append(nd.getNode_ID())
				.append(", SeqNo=").append(nd.getSeqNo())
				.append(" ").append(nd.getName());
			System.out.println(sb.toString());
			count++;
		}
//		System.out.println("Count=" + count);
	}   //  diagPrintTree

	/**
	 *  Get Root node
	 *  @return root
	 */
	public MTreeNode getRoot()
	{
		return rootNode;
	}   //  getRoot

	/**
	 * 	Is Menu Tree
	 *	@return true if menu
	 */
	public boolean isMenu()
	{
		return TREETYPE_Menu.equals(getTreeType());
	}	//	isMenu

	/**
	 * 	Is Product Tree
	 *	@return true if product
	 */
	public boolean isProduct()
	{
		return TREETYPE_Product.equals(getTreeType());
	}	//	isProduct
	
	/**
	 * 	Is Business Partner Tree
	 *	@return true if partner
	 */
	public boolean isBPartner()
	{
		return TREETYPE_BPartner.equals(getTreeType());
	}	//	isBPartner
	
	/**
	 *  String representation
	 *  @return info
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer("MTree[");
		sb.append("AD_Tree_ID=").append(getAD_Tree_ID())
			.append(", Name=").append(getName());
		sb.append("]");
		return sb.toString();
	}
}   //  MTree
