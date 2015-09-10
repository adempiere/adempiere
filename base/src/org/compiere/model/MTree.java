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
import java.util.Properties;
import java.util.logging.Level;

import javax.sql.RowSet;

import org.compiere.print.MPrintColor;
import org.compiere.util.CCache;
import org.compiere.util.CLogMgt;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 *	Base Tree Model.
 *	(see also MTree in project base)
 *	
 *  @author Jorg Janke
 *  @version $Id: MTree_Base.java,v 1.2 2006/07/30 00:58:37 jjanke Exp $
 *  @author victor.perez@e-evoluton.com, www.e-evolution.com
 *  	<li>FR [ 3426137 ] Smart Browser
 * 		https://sourceforge.net/tracker/?func=detail&aid=3426137&group_id=176962&atid=879335 
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com 2015-09-09
 *  	<li>FR [ 9223372036854775807 ] Add Support to Dynamic Tree
 *  @see https://adempiere.atlassian.net/browse/ADEMPIERE-442
 */
public class MTree extends X_AD_Tree
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7657958239525901547L;	
	
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
	 *  @param AD_Tree_ID   The tree to build
	 *  @param editable     True, if tree can be modified
	 *  - includes inactive and empty summary nodes
	 * 	@param ctx context for security
	 *	@param clientTree the tree is displayed on the java client (not on web)
	 *  @param trxName transaction
	 */
	public MTree (Properties ctx, int AD_Tree_ID, 
		boolean editable, boolean clientTree, String trxName)
	{
		//	Yamel Senih [ 9223372036854775807 ]
		//	parameter Where clause like null
		this (ctx, AD_Tree_ID, editable, clientTree, false, null, trxName);
	}   //  MTree

	/**
	 * Instance tree with where clause
	 * @param ctx
	 * @param AD_Tree_ID
	 * @param editable
	 * @param clientTree
	 * @param allNodes
	 * @param whereClause
	 * @param trxName
	 */
	public MTree (Properties ctx, int AD_Tree_ID, 
			boolean editable, boolean clientTree, boolean allNodes, String whereClause, String trxName)
	{
		this (ctx, AD_Tree_ID, trxName);
		m_editable = editable;
		int AD_User_ID;
		if (allNodes)
			AD_User_ID = -1;
		else
			AD_User_ID = Env.getContextAsInt(ctx, "AD_User_ID");
		m_clientTree = clientTree;
		log.info("AD_Tree_ID=" + AD_Tree_ID
				+ ", AD_User_ID=" + AD_User_ID 
				+ ", Editable=" + editable
				+ ", OnClient=" + clientTree);
		//
		//	Yamel Senih [ 9223372036854775807 ]
		//	Add support to where clause
		loadNodes(AD_User_ID, whereClause);
	}   //  MTree	

	/** Is Tree editable    	*/
	private boolean     		m_editable = false;
	/** Root Node                   */
	private MTreeNode           m_root = null;
	/** Buffer while loading tree   */
	private ArrayList<MTreeNode> m_buffer = new ArrayList<MTreeNode>();
	/** Prepared Statement for Node Details */
	private RowSet			   	m_nodeRowSet;
	/** The tree is displayed on the Java Client (i.e. not web)	*/
	private boolean				m_clientTree = true;
	
	private HashMap<Integer, ArrayList<Integer>> m_nodeIdMap;
	/**	Cache						*/
	private static CCache<Integer,MTree> s_cache = new CCache<Integer,MTree>("AD_Tree", 10);

	/**	Logger			*/
	private static CLogger s_log = CLogger.getCLogger(MTree.class);
	
	@Override
	public void setTreeType(String TreeType) {
		super.setTreeType(TreeType);
		setTable_ID(TreeType);
	}
	
	/**
	 * Set Table ID of type tree
	 * @author Dixon Martinez, dmartinez@erpcya.com, ERPCyA http://www.erpcya.com 2015-09-09
	 * @param treeType
	 * @return void
	 */
	private void setTable_ID(String treeType) {
		int table_ID = getAD_Table_ID();
		//	Valid Table
		if(table_ID > 0)
			return;
		if(TREETYPE_Activity.equals(treeType))
			table_ID = X_C_Activity.Table_ID;
		else if (TREETYPE_BPartner.equals(treeType))
			table_ID = X_C_BPartner.Table_ID;
		else if (TREETYPE_Campaign.equals(treeType))
			table_ID = X_C_Campaign.Table_ID;
		else if (TREETYPE_CMContainer.equals(treeType))
			table_ID = X_CM_Container.Table_ID;
		else if (TREETYPE_CMContainerStage.equals(treeType))
			table_ID = X_CM_CStage.Table_ID;
		else if (TREETYPE_CMMedia.equals(treeType))
			table_ID = X_CM_Media.Table_ID;
		else if (TREETYPE_CMTemplate.equals(treeType))
			table_ID = X_CM_Template.Table_ID;
		else if (TREETYPE_ElementValue.equals(treeType))
			table_ID = X_C_ElementValue.Table_ID;
		else if (TREETYPE_Organization.equals(treeType))
			table_ID = X_AD_Org.Table_ID;
		else if (TREETYPE_Product.equals(treeType))
			table_ID = X_M_Product.Table_ID;
		else if (TREETYPE_Project.equals(treeType))
			table_ID = X_C_Project.Table_ID;
		else if (TREETYPE_SalesRegion.equals(treeType))
			table_ID = X_C_SalesRegion.Table_ID;
		//	Set Table
		setAD_Table_ID(table_ID);
	}
	
	/**
	 * 	Add Node to correct tree
	 *	@param ctx cpntext
	 *	@param treeType tree type
	 *	@param Record_ID id
	 *	@param trxName transaction
	 *	@return true if node added
	 */
	public static boolean addNode (Properties ctx, String treeType, int Record_ID, String trxName)
	{
		//	Get Tree
		int AD_Tree_ID = 0;
		MClient client = MClient.get(ctx);
		MClientInfo ci = client.getInfo();
		
		if (TREETYPE_Activity.equals(treeType))
			AD_Tree_ID = ci.getAD_Tree_Activity_ID();
		else if (TREETYPE_BoM.equals(treeType))
			throw new IllegalArgumentException("BoM Trees not supported");
		else if (TREETYPE_BPartner.equals(treeType))
			AD_Tree_ID = ci.getAD_Tree_BPartner_ID();
		else if (TREETYPE_Campaign.equals(treeType))
			AD_Tree_ID = ci.getAD_Tree_Campaign_ID();
		else if (TREETYPE_ElementValue.equals(treeType))
			throw new IllegalArgumentException("ElementValue cannot use this API");
		else if (TREETYPE_Menu.equals(treeType))
			AD_Tree_ID = ci.getAD_Tree_Menu_ID();
		else if (TREETYPE_Organization.equals(treeType))
			AD_Tree_ID = ci.getAD_Tree_Org_ID();
		else if (TREETYPE_Product.equals(treeType))
			AD_Tree_ID = ci.getAD_Tree_Product_ID();
		else if (TREETYPE_ProductCategory.equals(treeType))
			throw new IllegalArgumentException("Product Category Trees not supported");
		else if (TREETYPE_Project.equals(treeType))
			AD_Tree_ID = ci.getAD_Tree_Project_ID();
		else if (TREETYPE_SalesRegion.equals(treeType))
			AD_Tree_ID = ci.getAD_Tree_SalesRegion_ID();

		if (AD_Tree_ID == 0)
			throw new IllegalArgumentException("No Tree found");
		MTree tree = MTree.get(ctx, AD_Tree_ID, trxName);
		if (tree.get_ID() != AD_Tree_ID)
			throw new IllegalArgumentException("Tree found AD_Tree_ID=" + AD_Tree_ID);

		//	Insert Tree in correct tree
		boolean saved = false;
		if (TREETYPE_Menu.equals(treeType))
		{
			MTree_NodeMM node = new MTree_NodeMM (tree, Record_ID);
			saved = node.save();
		}
		else if  (TREETYPE_BPartner.equals(treeType))
		{
			MTree_NodeBP node = new MTree_NodeBP (tree, Record_ID);
			saved = node.save();
		}
		else if  (TREETYPE_Product.equals(treeType))
		{
			MTree_NodePR node = new MTree_NodePR (tree, Record_ID);
			saved = node.save();
		}
		else
		{
			MTree_Node node = new MTree_Node (tree, Record_ID);
			saved = node.save();
		}
		return saved;	
	}	//	addNode
	
	
	/**************************************************************************
	 * 	Get Node TableName
	 *	@param treeType tree type
	 *	@return node table name, e.g. AD_TreeNode
	 */
	//	Yamel Senih [ 9223372036854775807 ]
	//	Change by Table Identifier
	public static String getNodeTableName(int p_AD_Table_ID)
	{
//		String	nodeTableName = "AD_TreeNode";
//		if (TREETYPE_Menu.equals(treeType))
//			nodeTableName += "MM";
//		else if  (TREETYPE_BPartner.equals(treeType))
//			nodeTableName += "BP";
//		else if  (TREETYPE_Product.equals(treeType))
//			nodeTableName += "PR";
//		//
//		else if  (TREETYPE_CMContainer.equals(treeType))
//			nodeTableName += "CMC";
//		else if  (TREETYPE_CMContainerStage.equals(treeType))
//			nodeTableName += "CMS";
//		else if  (TREETYPE_CMMedia.equals(treeType))
//			nodeTableName += "CMM";
//		else if  (TREETYPE_CMTemplate.equals(treeType))
//			nodeTableName += "CMT";
//		//
		String	nodeTableName = "AD_TreeNode";
		if (X_AD_Menu.Table_ID == p_AD_Table_ID)
			nodeTableName += "MM";
		else if  (X_C_BPartner.Table_ID == p_AD_Table_ID)
			nodeTableName += "BP";
		else if  (X_M_Product.Table_ID == p_AD_Table_ID)
			nodeTableName += "PR";
		//
		else if  (X_CM_Container.Table_ID == p_AD_Table_ID)
			nodeTableName += "CMC";
		else if  (X_CM_CStage.Table_ID == p_AD_Table_ID)
			nodeTableName += "CMS";
		else if  (X_CM_Media.Table_ID == p_AD_Table_ID)
			nodeTableName += "CMM";
		else if  (X_CM_Template.Table_ID == p_AD_Table_ID)
			nodeTableName += "CMT";
//		else if  (TREETYPE_User1.equals(treeType))
//			nodeTableName += "U1";
//		else if  (TREETYPE_User2.equals(treeType))
//			nodeTableName += "U2";
//		else if  (TREETYPE_User3.equals(treeType))
//			nodeTableName += "U3";
//		else if  (TREETYPE_User4.equals(treeType))
//			nodeTableName += "U4";
		//	Yamel Senih [ 9223372036854775807 ]
		//	Add Support to Tables
		else {
			if (s_TableIDs == null)
				fillUserTables(null);
			Integer ii = Integer.valueOf(p_AD_Table_ID);
			if (s_TableIDs.contains(ii))
			{
				if  (s_TableIDs_U1.contains(ii))
					nodeTableName += "U1";
				else if (s_TableIDs_U2.contains(ii))
					nodeTableName += "U2";
				else if (s_TableIDs_U3.contains(ii))
					nodeTableName += "U3";
				else if (s_TableIDs_U4.contains(ii))
					nodeTableName += "U4";
			}
			else	//	no tree
				return null;
		}
		return nodeTableName;
	}	//	getNodeTableName

	/** All Table Names with tree		*/
	private static ArrayList<String> s_TableNames = null;
	/** All Table IDs with tree			*/
	private static ArrayList<Integer> s_TableIDs = null;
	/** U1 Table IDs					*/
	private static ArrayList<Integer> s_TableIDs_U1 = null;
	/** U2 Table IDs					*/
	private static ArrayList<Integer> s_TableIDs_U2 = null;
	/** U3 Table IDs					*/
	private static ArrayList<Integer> s_TableIDs_U3 = null;
	/** U4 Table IDs					*/
	private static ArrayList<Integer> s_TableIDs_U4 = null;
	
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
	static synchronized void fillUserTables (String trxName)
	{
		s_TableNames = new ArrayList<String>();
		s_TableIDs = new ArrayList<Integer>();
		s_TableIDs_U1 = new ArrayList<Integer>();
		s_TableIDs_U2 = new ArrayList<Integer>();
		s_TableIDs_U3 = new ArrayList<Integer>();
		s_TableIDs_U4 = new ArrayList<Integer>();
		//
		boolean error = false;
		//
		String sql = "SELECT DISTINCT TreeType, AD_Table_ID FROM AD_Tree";
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement (sql, trxName);
			ResultSet rs = pstmt.executeQuery ();
			while (rs.next ())
			{
				String TreeType = rs.getString(1);
				int AD_Table_ID = rs.getInt(2);
				if (AD_Table_ID == 0)
					continue;
				Integer ii = Integer.valueOf(AD_Table_ID);
				s_TableIDs.add(ii);		//	all
				if (TreeType.equals ("U1"))
					s_TableIDs_U1.add(ii);
				else if (TreeType.equals ("U2"))
					s_TableIDs_U2.add(ii);
				else if (TreeType.equals ("U3"))
					s_TableIDs_U3.add(ii);
				else if (TreeType.equals ("U4"))
					s_TableIDs_U4.add(ii);
			}
			rs.close ();
			pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			s_log.log (Level.SEVERE, sql, e);
			error = true;
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
		int AD_Table_ID = 0;
		String type = getTreeType();
		if (type == null
			|| type.startsWith ("U")	//	User
			|| type.equals (TREETYPE_CustomTree))
			return 0;
		for (int i = 0; i < TREETYPES.length; i++)
		{
			if (type.equals (TREETYPES[i]))
			{
				AD_Table_ID = TABLEIDS[i];
				break;
			}
		}
		if (AD_Table_ID != 0)
			setAD_Table_ID (AD_Table_ID);
		if (AD_Table_ID == 0)
			log.warning ("Did not find Table for TreeType=" + type);
		return AD_Table_ID;
	}	//	setAD_Table_ID
	
	/**
	 * 	Table has Tree
	 *	@param AD_Table_ID table
	 *	@return true if table has tree
	 */
	static public boolean hasTree (int AD_Table_ID) {
		if (s_TableIDs == null)
			fillUserTables(null);
		Integer ii = Integer.valueOf(AD_Table_ID);
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
	 * 	Get Source TableName
	 *	@param treeType tree typw
	 *	@return source table name, e.g. AD_Org or null 
	 */
	public String getSourceTableName()
	{
		//	Get Tree Type
		String treeType = getTreeType();
		if (treeType == null)
			return null;
		String sourceTable = null;
		if (treeType.equals(TREETYPE_Menu))
			sourceTable = "AD_Menu";
		else if (treeType.equals(TREETYPE_Organization))
			sourceTable = "AD_Org";
		else if (treeType.equals(TREETYPE_Product))
			sourceTable = "M_Product";
		else if (treeType.equals(TREETYPE_ProductCategory))
			sourceTable = "M_Product_Category";
		else if (treeType.equals(TREETYPE_BoM))
			sourceTable = "M_BOM";
		else if (treeType.equals(TREETYPE_ElementValue))
			sourceTable = "C_ElementValue";
		else if (treeType.equals(TREETYPE_BPartner))
			sourceTable = "C_BPartner";
		else if (treeType.equals(TREETYPE_Campaign))
			sourceTable = "C_Campaign";
		else if (treeType.equals(TREETYPE_Project))
			sourceTable = "C_Project";
		else if (treeType.equals(TREETYPE_Activity))
			sourceTable = "C_Activity";
		else if (treeType.equals(TREETYPE_SalesRegion))
			sourceTable = "C_SalesRegion";
		//
		else if (treeType.equals(TREETYPE_CMContainer))
			sourceTable = "CM_Container";
		else if (treeType.equals(TREETYPE_CMContainerStage))
			sourceTable = "CM_CStage";
		else if (treeType.equals(TREETYPE_CMMedia))
			sourceTable = "CM_Media";
		else if (treeType.equals(TREETYPE_CMTemplate))
			sourceTable = "CM_Template";
		//	User Trees
		// afalcone [Bugs #1837219]
		else if (treeType.equals(TREETYPE_User1) || 
				 treeType.equals(TREETYPE_User2) || 
				 treeType.equals(TREETYPE_User3) || 
				 treeType.equals(TREETYPE_User4))
			sourceTable = "C_ElementValue";

		//	else if (treeType.equals(TREETYPE_User1))
		//			sourceTable = "??";
		// end afalcone
		//	Yamel Senih [ 9223372036854775807 ]
		//	if tree type is custom then get from table
		else if(treeType.equals(TREETYPE_CustomTree)) {
			sourceTable = MTable.getTableName (getCtx(), getAD_Table_ID());
		}
		//	End Yamel Senih
		return sourceTable;		
	}	//	getSourceTableName

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
		return getNodeTableName(getAD_Table_ID());
	}	//	getNodeTableName
	
	/**
	 * 	Get Source TableName (i.e. where to get the name and color)
	 * 	@param tableNameOnly if false return From clause (alias = t)
	 *	@return source table name, e.g. AD_Org or null
	 */
	public String getSourceTableName (boolean tableNameOnly)
	{
		//	Yamel Senih [ 9223372036854775807 ]
		//	Get Tree Type into method
		String tableName = getSourceTableName();
		//	End Yamel Senih
		if (tableNameOnly)
			return tableName;
		if ("M_Product".equals(tableName))
			return "M_Product t INNER JOIN M_Product_Category x ON (t.M_Product_Category_ID=x.M_Product_Category_ID)";
		if ("C_BPartner".equals(tableName))
			return "C_BPartner t INNER JOIN C_BP_Group x ON (t.C_BP_Group_ID=x.C_BP_Group_ID)";
		if ("AD_Org".equals(tableName))
			return "AD_Org t INNER JOIN AD_OrgInfo i ON (t.AD_Org_ID=i.AD_Org_ID) "
				+ "LEFT OUTER JOIN AD_OrgType x ON (i.AD_OrgType_ID=x.AD_OrgType_ID)";
		if ("C_Campaign".equals(tableName))
			return "C_Campaign t LEFT OUTER JOIN C_Channel x ON (t.C_Channel_ID=x.C_Channel_ID)";
		if (tableName != null)
			tableName += " t";
		return tableName;
	}	//	getSourceTableName

	
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
		if ("M_Product".equals(tableName) || "C_BPartner".equals(tableName) 
			|| "AD_Org".equals(tableName) || "C_Campaign".equals(tableName))
			return "x.AD_PrintColor_ID";
		return "NULL";
	}	//	getSourceTableName
	
	
	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		if (!isActive() || !isAllNodes())
			setIsDefault(false);
		return true;
	}	//	beforeSabe
	
	/**
	 * 	After Save
	 *	@param newRecord new
	 *	@param success success
	 *	@return success
	 */
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		if (newRecord)	//	Base Node
		{
			if (TREETYPE_BPartner.equals(getTreeType()))
			{
				MTree_NodeBP ndBP = new MTree_NodeBP(this, 0);
				ndBP.saveEx();
			}
			else if (TREETYPE_Menu.equals(getTreeType()))
			{
				MTree_NodeMM ndMM = new MTree_NodeMM(this, 0);
				ndMM.saveEx();
			}
			else if (TREETYPE_Product.equals(getTreeType()))
			{
				MTree_NodePR ndPR = new MTree_NodePR(this, 0);
				ndPR.saveEx();
			}
			else
			{
				MTree_Node nd = new MTree_Node(this, 0);
				nd.saveEx();
			}
		}
		
		return success;
	}	//	afterSave
	
	/**************************************************************************
	 *  Get default (oldest) complete AD_Tree_ID for KeyColumn.
	 *  Called from GridController
	 *  @param keyColumnName key column name, eg. C_Project_ID
	 *  @param AD_Client_ID client
	 *  @return AD_Tree_ID
	 */
//	public static int getDefaultAD_Tree_ID (int AD_Client_ID, String keyColumnName)
//	{
//		s_log.config(keyColumnName);
//		if (keyColumnName == null || keyColumnName.length() == 0)
//			return 0;
//
//		String TreeType = null;
//		if (keyColumnName.equals("AD_Menu_ID"))
//			TreeType = TREETYPE_Menu; 
//		else if (keyColumnName.equals("C_ElementValue_ID"))
//			TreeType = TREETYPE_ElementValue;
//		else if (keyColumnName.equals("M_Product_ID"))
//			TreeType = TREETYPE_Product;
//		else if (keyColumnName.equals("C_BPartner_ID"))
//			TreeType = TREETYPE_BPartner;
//		else if (keyColumnName.equals("AD_Org_ID"))
//			TreeType = TREETYPE_Organization;
//		else if (keyColumnName.equals("C_Project_ID"))
//			TreeType = TREETYPE_Project;
//		else if (keyColumnName.equals("M_ProductCategory_ID"))
//			TreeType = TREETYPE_ProductCategory;
//		else if (keyColumnName.equals("M_BOM_ID"))
//			TreeType = TREETYPE_BoM;
//		else if (keyColumnName.equals("C_SalesRegion_ID"))
//			TreeType = TREETYPE_SalesRegion;
//		else if (keyColumnName.equals("C_Campaign_ID"))
//			TreeType = TREETYPE_Campaign;
//		else if (keyColumnName.equals("C_Activity_ID"))
//			TreeType = TREETYPE_Activity;
//		//
//		else if (keyColumnName.equals("CM_CStage_ID"))
//			TreeType = TREETYPE_CMContainerStage;
//		else if (keyColumnName.equals("CM_Container_ID"))
//			TreeType = TREETYPE_CMContainer;
//		else if (keyColumnName.equals("CM_Media_ID"))
//			TreeType = TREETYPE_CMMedia;
//		else if (keyColumnName.equals("CM_Template_ID"))
//			TreeType = TREETYPE_CMTemplate;
//		else
//		{
//			s_log.log(Level.SEVERE, "Could not map " + keyColumnName);
//			return 0;
//		}
//
//		int AD_Tree_ID = 0;
//		String sql = "SELECT AD_Tree_ID, Name FROM AD_Tree "
//			+ "WHERE AD_Client_ID=? AND TreeType=? AND IsActive='Y' AND IsAllNodes='Y' "
//			+ "ORDER BY IsDefault DESC, AD_Tree_ID";
//		try
//		{
//			PreparedStatement pstmt = DB.prepareStatement(sql, null);
//			pstmt.setInt(1, AD_Client_ID);
//			pstmt.setString(2, TreeType);
//			ResultSet rs = pstmt.executeQuery();
//			if (rs.next())
//				AD_Tree_ID = rs.getInt(1);
//			rs.close();
//			pstmt.close();
//		}
//		catch (SQLException e)
//		{
//			s_log.log(Level.SEVERE, sql, e);
//		}
//
//		return AD_Tree_ID;
//	}   //  getDefaultAD_Tree_ID
	//	Yamel Senih [ 9223372036854775807 ]
	//	Get Tree from table
	public static int getDefaultAD_Tree_ID (int AD_Client_ID, String tableName) {
		s_log.finer("TableName=" + tableName);
		if (tableName == null)
			return 0;
		int AD_Tree_ID = 0;
		String sql = "SELECT tr.AD_Tree_ID, tr.Name "
			+ "FROM AD_Tree tr INNER JOIN AD_Table tb ON (tr.AD_Table_ID=tb.AD_Table_ID) "
			+ "WHERE tr.AD_Client_ID IN(0, ?) AND tb.TableName=? AND tr.IsActive='Y' AND tr.IsAllNodes='Y' "
			+ "ORDER BY tr.IsDefault DESC, tr.AD_Tree_ID";
		//	Get Tree
		AD_Tree_ID = DB.getSQLValue(null, sql, AD_Client_ID, tableName);
		//	Default Return
		return AD_Tree_ID;
	}   //  getDefaultAD_Tree_ID
	
	/**
	 * Get Default Tree
	 * @param AD_Client_ID
	 * @param p_AD_Table_ID
	 * @return
	 */
	public static int getDefaultAD_Tree_ID (int AD_Client_ID, int p_AD_Table_ID) {
		return getDefaultAD_Tree_ID (AD_Client_ID, p_AD_Table_ID, 0);
	}
	
	/**
	 * Get from Table Identifier
	 * Yamel Senih [ 9223372036854775807 ]
	 * @param AD_Client_ID
	 * @param p_AD_Table_ID
	 * @return
	 */
	public static int getDefaultAD_Tree_ID (int AD_Client_ID, int p_AD_Table_ID, int p_C_Element_ID) {
		s_log.finer("AD_Table_ID=" + p_AD_Table_ID);
		if (p_AD_Table_ID == 0)
			return 0;
		int AD_Tree_ID = 0;
		String whereClause = new String();
		//	Valid Element
		if (p_C_Element_ID != 0)
			whereClause = " AND EXISTS (SELECT 1 FROM C_Element ae WHERE ae.C_Element_ID="
			+ p_C_Element_ID + " AND tr.AD_Tree_ID=ae.AD_Tree_ID) ";
		String sql = "SELECT tr.AD_Tree_ID, tr.Name "
			+ "FROM AD_Tree tr "
			+ "INNER JOIN AD_Table tb ON (tr.AD_Table_ID=tb.AD_Table_ID) "
			+ "WHERE tr.AD_Client_ID IN(0, ?) "
			+ "AND tb.AD_Table_ID = ? "
			+ "AND tr.IsActive='Y' "
			+ "AND tr.IsAllNodes='Y' "
			+ whereClause
			+ "ORDER BY tr.IsDefault DESC, tr.AD_Tree_ID";
		//	Get Tree
		AD_Tree_ID = DB.getSQLValue(null, sql, AD_Client_ID, p_AD_Table_ID);
		//	Default Return
		return AD_Tree_ID;
	}   //  getDefaultAD_Tree_ID	

	/*************************************************************************
	 *  Load Nodes and Bar
	 * 	@param AD_User_ID user for tree bar
	 * 	@param whereClause
	 */
	private void loadNodes (int AD_User_ID, String whereClause)
	{
		//	Yamel Senih [ 9223372036854775807 ]
		//	Add Where Clause
		//	Old Code
		//  SQL for TreeNodes
//		StringBuffer sql = new StringBuffer("SELECT "
//			+ "tn.Node_ID,tn.Parent_ID,tn.SeqNo,tb.IsActive "
//			+ "FROM ").append(getNodeTableName()).append(" tn"
//			+ " LEFT OUTER JOIN AD_TreeBar tb ON (tn.AD_Tree_ID=tb.AD_Tree_ID"
//			+ " AND tn.Node_ID=tb.Node_ID "
//			+ (AD_User_ID != -1 ? " AND tb.AD_User_ID=? ": "") 	//	#1 (conditional)
//			+ ") "
//			+ "WHERE tn.AD_Tree_ID=?");								//	#2
//		if (!m_editable)
//			sql.append(" AND tn.IsActive='Y'");
		String fromClause = getSourceTableName(true);
		//  SQL for TreeNodes
		StringBuffer sql = new StringBuffer("SELECT "
			+ "tn.Node_ID,tn.Parent_ID,tn.SeqNo,tb.IsActive "
			+ "FROM ").append(getNodeTableName()).append(" tn ")
			.append("INNER JOIN ")
				.append(fromClause).append(" ON(")
					.append(fromClause).append(".").append(fromClause + "_ID").append(" = tn.Node_ID) ")
			//
			.append(" LEFT OUTER JOIN AD_TreeBar tb ON (tn.AD_Tree_ID=tb.AD_Tree_ID"
			+ " AND tn.Node_ID=tb.Node_ID "
			+ (AD_User_ID != -1 ? " AND tb.AD_User_ID=? ": "") 	//	#1 (conditional)
			+ ") "
			+ "WHERE tn.AD_Tree_ID=?");								//	#2
		if (!m_editable)
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
			if (AD_User_ID != -1)
				pstmt.setInt(idx++, AD_User_ID);
			pstmt.setInt(idx++, getAD_Tree_ID());
			//	Get Tree & Bar
			ResultSet rs = pstmt.executeQuery();
			m_root = new MTreeNode (0, 0, getName(), getDescription(), 0, true, null, false, null);
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
			//m_nodeRowSet.close();
			m_nodeRowSet = null;
			m_nodeIdMap = null;
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql.toString(), e);
			m_nodeRowSet = null;
			m_nodeIdMap = null;
		}
			
		//  Done with loading - add remainder from buffer
		if (m_buffer.size() != 0)
		{
			log.finest("clearing buffer - Adding to: " + m_root);
			for (int i = 0; i < m_buffer.size(); i++)
			{
				MTreeNode node = (MTreeNode)m_buffer.get(i);
				MTreeNode parent = m_root.findNode(node.getParent_ID());
				if (parent != null && parent.getAllowsChildren())
				{
					parent.add(node);
					int sizeBeforeCheckBuffer = m_buffer.size();
					checkBuffer(node);
					if (sizeBeforeCheckBuffer == m_buffer.size())
						m_buffer.remove(i);
					i = -1;		//	start again with i=0
				}
			}
		}

		//	Nodes w/o parent
		if (m_buffer.size() != 0)
		{
			log.severe ("Nodes w/o parent - adding to root - " + m_buffer);
			for (int i = 0; i < m_buffer.size(); i++)
			{
				MTreeNode node = (MTreeNode)m_buffer.get(i);
				m_root.add(node);
				int sizeBeforeCheckBuffer = m_buffer.size();
				checkBuffer(node);
				if (sizeBeforeCheckBuffer == m_buffer.size())
					m_buffer.remove(i);
				i = -1;
			}
			if (m_buffer.size() != 0)
				log.severe ("Still nodes in Buffer - " + m_buffer);
		}	//	nodes w/o parents

		//  clean up
		if (!m_editable && m_root.getChildCount() > 0)
			trimTree();
//		diagPrintTree();
		if (CLogMgt.isLevelFinest() || m_root.getChildCount() == 0)
			log.fine("ChildCount=" + m_root.getChildCount());
	}   //  loadNodes

	/**
	 *  Add Node to Tree.
	 *  If not found add to buffer
	 *  @param node_ID Node_ID
	 *  @param parent_ID Parent_ID
	 *  @param seqNo SeqNo
	 *  @param onBar on bar
	 */
	private void addToTree (int node_ID, int parent_ID, int seqNo, boolean onBar)
	{
		//  Create new Node
		MTreeNode child = getNodeDetail (node_ID, parent_ID, seqNo, onBar);
		if (child == null)
			return;

		//  Add to Tree
		MTreeNode parent = null;
		if (m_root != null)
			parent = m_root.findNode (parent_ID);
		//  Parent found
		if (parent != null && parent.getAllowsChildren())
		{
			parent.add(child);
			//  see if we can add nodes from buffer
			if (m_buffer.size() > 0)
				checkBuffer(child);
		}
		else
			m_buffer.add(child);
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
		for (int i = 0; i < m_buffer.size(); i++)
		{
			MTreeNode node = (MTreeNode)m_buffer.get(i);
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
				m_buffer.remove(i);
				i--;
			}
		}
	}   //  checkBuffer

	
	
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
	private void getNodeDetails ()
	{
		//  SQL for Node Info
		StringBuffer sqlNode = new StringBuffer();
		String sourceTable = "t";
		String fromClause = getSourceTableName(false);	//	fully qualified
		String columnNameX = getSourceTableName(true);
		String color = getActionColorName();
		if (getTreeType().equals(TREETYPE_Menu))
		{
			boolean base = Env.isBaseLanguage(p_ctx, "AD_Menu");
			sourceTable = "m";
			if (base)
				sqlNode.append("SELECT m.AD_Menu_ID, m.Name,m.Description,m.IsSummary,m.Action, "
					+ "m.AD_Window_ID, m.AD_Process_ID, m.AD_Form_ID, m.AD_Workflow_ID, m.AD_Task_ID, m.AD_Workbench_ID "
					+ ", m.AD_Browse_ID "
					+ "FROM AD_Menu m");
			else
				sqlNode.append("SELECT m.AD_Menu_ID,  t.Name,t.Description,m.IsSummary,m.Action, "
					+ "m.AD_Window_ID, m.AD_Process_ID, m.AD_Form_ID, m.AD_Workflow_ID, m.AD_Task_ID, m.AD_Workbench_ID "
					+ ", m.AD_Browse_ID "
					+ "FROM AD_Menu m, AD_Menu_Trl t");
			if (!base)
				sqlNode.append(" WHERE m.AD_Menu_ID=t.AD_Menu_ID AND t.AD_Language='")
					.append(Env.getAD_Language(p_ctx)).append("'");
			if (!m_editable)
			{
				boolean hasWhere = sqlNode.indexOf(" WHERE ") != -1;
				sqlNode.append(hasWhere ? " AND " : " WHERE ").append("m.IsActive='Y' ");
			}
			//	Do not show Beta
			if (!MClient.get(getCtx()).isUseBetaFunctions())
			{
				boolean hasWhere = sqlNode.indexOf(" WHERE ") != -1;
				sqlNode.append(hasWhere ? " AND " : " WHERE ");
				sqlNode.append("(m.AD_Window_ID IS NULL OR EXISTS (SELECT * FROM AD_Window w WHERE m.AD_Window_ID=w.AD_Window_ID AND w.IsBetaFunctionality='N'))")
					.append(" AND (m.AD_Process_ID IS NULL OR EXISTS (SELECT * FROM AD_Process p WHERE m.AD_Process_ID=p.AD_Process_ID AND p.IsBetaFunctionality='N'))")
					.append(" AND (m.AD_Workflow_ID IS NULL OR EXISTS (SELECT * FROM AD_Workflow wf WHERE m.AD_Workflow_ID=wf.AD_Workflow_ID AND wf.IsBetaFunctionality='N'))")
					.append(" AND (m.AD_Form_ID IS NULL OR EXISTS (SELECT * FROM AD_Form f WHERE m.AD_Form_ID=f.AD_Form_ID AND f.IsBetaFunctionality='N'))")
					.append(" AND (m.AD_Browse_ID IS NULL OR EXISTS (SELECT * FROM AD_Browse b WHERE m.AD_Browse_ID=b.AD_Browse_ID AND b.IsBetaFunctionality='N'))");
			}
			//	In R/O Menu - Show only defined Forms
			if (!m_editable)
			{
				boolean hasWhere = sqlNode.indexOf(" WHERE ") != -1;
				sqlNode.append(hasWhere ? " AND " : " WHERE ");
				sqlNode.append("(m.AD_Form_ID IS NULL OR EXISTS (SELECT * FROM AD_Form f WHERE m.AD_Form_ID=f.AD_Form_ID AND ");
				if (m_clientTree)
					sqlNode.append("f.Classname");
				else
					sqlNode.append("f.JSPURL");
				sqlNode.append(" IS NOT NULL))");
			}
		} 
		//	Yamel Senih [ 9223372036854775807 ]
		//	Load for Custom Tree
		else if(getTreeType().equals(TREETYPE_CustomTree)) {
			boolean base = Env.isBaseLanguage(p_ctx, columnNameX);
			sourceTable = "m";
			String recordClause = "";
			if (base){
				sqlNode.append("SELECT t." + columnNameX + "_ID, ")
					.append("t.Name, t.Description, t.IsSummary, " + color + " AS Action ")
					.append(recordClause.length() > 0 ? ", " + recordClause : "")
					.append(" FROM " + fromClause )
					;
			}else {
				sqlNode.append("SELECT t." + columnNameX + "_ID, ")
					.append("COALESCE(m.Name, t.Name) AS Name, COALESCE(m.Description, t.Description) AS Description, t.IsSummary,  ")
					.append( color + " AS Action ")
					.append(recordClause.length() > 0 ? ", " + recordClause : "")
					.append(" FROM " + fromClause )
					.append(" INNER JOIN " + columnNameX + "_Trl m ON (t. " + columnNameX + "_ID = m." + columnNameX + "_ID)")
				;
			}
				
			
			if (!base)
				sqlNode.append(" WHERE t." + columnNameX + "_ID = m."+ columnNameX + "_ID AND m.AD_Language='")
					.append(Env.getAD_Language(p_ctx)).append("'");
			
			if (!m_editable)
			{
				boolean hasWhere = sqlNode.indexOf(" WHERE ") != -1;
				sqlNode.append(hasWhere ? " AND " : " WHERE ").append("t.IsActive='Y' ");
			}
		}
		//	End Yamel Senih
		else
		{
			if (columnNameX == null)
				throw new IllegalArgumentException("Unknown TreeType=" + getTreeType());
			sqlNode.append("SELECT t.").append(columnNameX)
				.append("_ID,t.Name,t.Description,t.IsSummary,").append(color)
				.append(" FROM ").append(fromClause);
			if (!m_editable)
				sqlNode.append(" WHERE t.IsActive='Y'");
		}
		String sql = sqlNode.toString();
		if (!m_editable)	//	editable = menu/etc. window
			sql = MRole.getDefault(getCtx(), false).addAccessSQL(sql, 
				sourceTable, MRole.SQL_FULLYQUALIFIED, m_editable);
		log.fine(sql);
		m_nodeRowSet = DB.getRowSet (sql);
		m_nodeIdMap = new HashMap<Integer, ArrayList<Integer>>(50);
		try 
		{
			m_nodeRowSet.beforeFirst();
			int i = 0;
			while (m_nodeRowSet.next())
			{
				i++;
				int node = m_nodeRowSet.getInt(1);
				Integer nodeId = Integer.valueOf(node);
				ArrayList<Integer> list = m_nodeIdMap.get(nodeId);
				if (list == null)
				{
					list = new ArrayList<Integer>(5);
					m_nodeIdMap.put(nodeId, list);
				}
				list.add(Integer.valueOf(i));
			}
		} catch (SQLException e) 
		{
			log.log(Level.SEVERE, "", e);
		}
	}   //  getNodeDetails

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
			//m_nodeRowSet.beforeFirst();
			ArrayList<Integer> nodeList = m_nodeIdMap.get(Integer.valueOf(node_ID));
			int size = nodeList != null ? nodeList.size() : 0;
			int i = 0;
			//while (m_nodeRowSet.next())
			while (i < size)
			{
				Integer nodeId = nodeList.get(i);
				i++;
				m_nodeRowSet.absolute(nodeId.intValue());
				int node = m_nodeRowSet.getInt(1);				
				if (node_ID != node)	//	search for correct one
					continue;
				//	ID,Name,Description,IsSummary,Action/Color
				int index = 2;				
				String name = m_nodeRowSet.getString(index++); 
				String description = m_nodeRowSet.getString(index++);
				boolean isSummary = "Y".equals(m_nodeRowSet.getString(index++));
				String actionColor = m_nodeRowSet.getString(index++);
				//	Menu only
				//	Yamel Senih [ 9223372036854775807 ]
				//	Add support to Custom
				if ((getTreeType().equals(TREETYPE_Menu)
						|| getTreeType().equals(TREETYPE_CustomTree)) && !isSummary)
				{	//	End Yamel Senih
					int AD_Window_ID = m_nodeRowSet.getInt(index++);
					int AD_Process_ID = m_nodeRowSet.getInt(index++);
					int AD_Form_ID = m_nodeRowSet.getInt(index++);
					int AD_Workflow_ID = m_nodeRowSet.getInt(index++);
					int AD_Task_ID = m_nodeRowSet.getInt(index++);
					int AD_Workbench_ID = m_nodeRowSet.getInt(index++);
					int AD_Browse_ID = m_nodeRowSet.getInt(index++);
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
				//	else if (X_AD_Menu.ACTION_Workbench.equals(action))
				//		access = role.getWorkbenchAccess(AD_Window_ID);
				//	log.fine("getNodeDetail - " + name + " - " + actionColor + " - " + access);
					//
					if (access != null		//	rw or ro for Role 
						|| m_editable)		//	Menu Window can see all
					{
						retValue = new MTreeNode (node_ID, seqNo,
							name, description, parent_ID, isSummary,
							actionColor, onBar, null);	//	menu has no color
					}
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
		boolean needsTrim = m_root != null;
		while (needsTrim)
		{
			needsTrim = false;
			Enumeration en = m_root.preorderEnumeration();
			while (m_root.getChildCount() > 0 && en.hasMoreElements())
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
		Enumeration en = m_root.preorderEnumeration();
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
		return m_root;
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
	 * 	Get AD_Table_ID
	 * 	@param base base info
	 *	@return table
	 */
	public int getAD_Table_ID(boolean base)
	{
		if (base)
			return super.getAD_Table_ID();
		return getAD_Table_ID();
	}	//	getAD_Table_ID
	
	/**
	 *  String representation
	 *  @return info
	 */
	@Override
	public String toString()
	{
		StringBuffer sb = new StringBuffer ("MTree[");
		sb.append (get_ID ()).append ("-")
			.append(getName())
			.append(",Type=").append(getTreeType())
			.append(",AD_Table_ID=").append(getAD_Table_ID(true))
			.append ("]");
		return sb.toString ();
	}	//	toString
	
}	//	MTree_Base
