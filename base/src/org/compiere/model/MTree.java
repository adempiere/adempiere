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
import org.compiere.util.CLogMgt;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

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
public class MTree extends MTree_Base
{
	private static final long serialVersionUID = -6412057411585787707L;

	/** Is Tree editable    	*/
	private boolean 	isTreeEditable = false;

	/** Root Node                   */
	private MTreeNode 	treeNode = null;

	/** Buffer while loading tree   */
	private ArrayList<MTreeNode> treeNodes = new ArrayList<>();

	/** Prepared Statement for Node Details */
	private RowSet 		nodeRowSet;

	/** The tree is displayed on the Java Client (i.e. not web)	*/
	private boolean 	clientTree = true;
	
	private HashMap<Integer, ArrayList<Integer>> nodeIdMap;

	/**	Logger			*/
	private static CLogger logger = CLogger.getCLogger(MTree.class);


	/**
	 *  Default Constructor.
	 * 	Need to call loadNodes explicitly
	 * 	@param ctx context for security
	 *  @param treeId   The tree to build
	 *  @param trxName transaction
	 */
	public MTree (Properties ctx, int treeId, String trxName)
	{
		super (ctx, treeId, trxName);
	}   //  MTree

	/**
	 *  Construct & Load Tree
	 *  @param treeId   The tree to build
	 *  @param editable     True, if tree can be modified
	 *  - includes inactive and empty summary nodes
	 * 	@param ctx context for security
	 *	@param clientTree the tree is displayed on the java client (not on web)
	 *  @param trxName transaction
	 */
	public MTree (Properties ctx, int treeId, boolean editable, boolean clientTree, String trxName)
	{
		this (ctx, treeId, editable, clientTree, false, trxName);
	}   //  MTree

	public MTree (Properties ctx, int treeId, boolean editable, boolean clientTree, boolean allNodes, String trxName)
	{
		this (ctx, treeId, trxName);
		isTreeEditable = editable;
		int AD_User_ID;
		if (allNodes)
			AD_User_ID = -1;
		else
			AD_User_ID = Env.getContextAsInt(ctx, "AD_User_ID");
		this.clientTree = clientTree;
		log.info("AD_Tree_ID=" + treeId
				+ ", AD_User_ID=" + AD_User_ID 
				+ ", Editable=" + editable
				+ ", OnClient=" + clientTree);
		//
		loadNodes(AD_User_ID);
	}   //  MTree

	/**************************************************************************
	 *  Get default (oldest) complete AD_Tree_ID for KeyColumn.
	 *  Called from GridController
	 *  @param keyColumnName key column name, eg. C_Project_ID
	 *  @param clientId client
	 *  @return AD_Tree_ID
	 */
	public static int getDefaultAD_Tree_ID (int clientId, String keyColumnName)
	{
		logger.config(keyColumnName);
		if (keyColumnName == null || keyColumnName.length() == 0)
			return 0;

		String treeType = null;
		if (keyColumnName.equals("AD_Menu_ID"))
			treeType = TREETYPE_Menu;
		else if (keyColumnName.equals("C_ElementValue_ID"))
			treeType = TREETYPE_ElementValue;
		else if (keyColumnName.equals("M_Product_ID"))
			treeType = TREETYPE_Product;
		else if (keyColumnName.equals("C_BPartner_ID"))
			treeType = TREETYPE_BPartner;
		else if (keyColumnName.equals("AD_Org_ID"))
			treeType = TREETYPE_Organization;
		else if (keyColumnName.equals("C_Project_ID"))
			treeType = TREETYPE_Project;
		else if (keyColumnName.equals("M_ProductCategory_ID"))
			treeType = TREETYPE_ProductCategory;
		else if (keyColumnName.equals("M_BOM_ID"))
			treeType = TREETYPE_BoM;
		else if (keyColumnName.equals("C_SalesRegion_ID"))
			treeType = TREETYPE_SalesRegion;
		else if (keyColumnName.equals("C_Campaign_ID"))
			treeType = TREETYPE_Campaign;
		else if (keyColumnName.equals("C_Activity_ID"))
			treeType = TREETYPE_Activity;
		else if (keyColumnName.equals("CM_CStage_ID"))
			treeType = TREETYPE_CMContainerStage;
		else if (keyColumnName.equals("CM_Container_ID"))
			treeType = TREETYPE_CMContainer;
		else if (keyColumnName.equals("CM_Media_ID"))
			treeType = TREETYPE_CMMedia;
		else if (keyColumnName.equals("CM_Template_ID"))
			treeType = TREETYPE_CMTemplate;
		else {
			logger.log(Level.SEVERE, "Could not map " + keyColumnName);
			return 0;
		}
		String sql = "SELECT AD_Tree_ID, Name FROM AD_Tree "
			+ "WHERE AD_Client_ID=? AND TreeType=? AND IsActive='Y' AND IsAllNodes='Y' "
			+ "ORDER BY IsDefault DESC, AD_Tree_ID";
		int treeId = DB.getSQLValueEx(null ,sql , clientId , treeType);
		return treeId;
	}   //  getDefaultAD_Tree_ID



	/*************************************************************************
	 *  Load Nodes and Bar
	 * 	@param userId user for tree bar
	 */
	private void loadNodes (int userId)
	{
		//  SQL for TreeNodes
		StringBuffer sql = new StringBuffer("SELECT "
			+ "tn.Node_ID, tn.Parent_ID, tn.SeqNo, tb.IsActive " // @Trifon
			+ "FROM ").append(getNodeTableName()).append(" tn"
			+ " LEFT OUTER JOIN AD_TreeBar tb ON (tn.AD_Tree_ID=tb.AD_Tree_ID"
			+ " AND tn.Node_ID=tb.Node_ID "
			+ (userId != -1 ? " AND tb.AD_User_ID=? ": "") 	//	#1 (conditional)
			+ ") "
			+ "WHERE tn.AD_Tree_ID=?");								//	#2
		if (!isTreeEditable)
			sql.append(" AND tn.IsActive='Y'");
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
			treeNode = new MTreeNode (0, 0, getName(), getDescription(), 0, true, null, false, null);
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
			log.finest("clearing buffer - Adding to: " + treeNode);
			for (int i = 0; i < treeNodes.size(); i++)
			{
				MTreeNode node = (MTreeNode) treeNodes.get(i);
				MTreeNode parent = treeNode.findNode(node.getParent_ID());
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
				treeNode.add(node);
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
		if (!isTreeEditable && treeNode.getChildCount() > 0)
			trimTree();
//		diagPrintTree();
		if (CLogMgt.isLevelFinest() || treeNode.getChildCount() == 0)
			log.fine("ChildCount=" + treeNode.getChildCount());
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
		if (treeNode != null)
			parent = treeNode.findNode (parentId);
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

	
	
	/**************************************************************************
	 *  Get Node Detail.
	 * 	Loads data into RowSet nodeRowSet
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
				sqlNode.append("SELECT m.AD_Menu_ID, m.Name, m.Description, m.IsSummary, m.Action, "
					+ "m.AD_Window_ID, m.AD_Process_ID, m.AD_Form_ID, m.AD_Workflow_ID, m.AD_Task_ID, m.AD_Workbench_ID "
					+ ", m.AD_Browse_ID "
					+ "FROM AD_Menu m");
			else
				sqlNode.append("SELECT m.AD_Menu_ID, t.Name, t.Description, m.IsSummary, m.Action, "
					+ "m.AD_Window_ID, m.AD_Process_ID, m.AD_Form_ID, m.AD_Workflow_ID, m.AD_Task_ID, m.AD_Workbench_ID "
					+ ", m.AD_Browse_ID "
					+ "FROM AD_Menu m, AD_Menu_Trl t");
			if (!base)
				sqlNode.append(" WHERE m.AD_Menu_ID=t.AD_Menu_ID AND t.AD_Language='")
					.append(Env.getAD_Language(p_ctx)).append("'");
			if (!isTreeEditable)
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
			if (!isTreeEditable)
			{
				boolean hasWhere = sqlNode.indexOf(" WHERE ") != -1;
				sqlNode.append(hasWhere ? " AND " : " WHERE ");
				sqlNode.append("(m.AD_Form_ID IS NULL OR EXISTS (SELECT * FROM AD_Form f WHERE m.AD_Form_ID=f.AD_Form_ID AND ");
				if (clientTree)
					sqlNode.append("f.Classname");
				else
					sqlNode.append("f.JSPURL");
				sqlNode.append(" IS NOT NULL))");
			}
		}
		else
		{
			if (columnNameX == null)
				throw new IllegalArgumentException("Unknown TreeType=" + getTreeType());

			sqlNode.append("SELECT t.").append(columnNameX).append("_ID")
				.append(", t.Name, t.Description, t.IsSummary, ").append(color)
			;
			if ( containsValueColumn( columnNameX ) ) {
				sqlNode.append(", t.Value"); //@Trifon
			}
			sqlNode.append(" FROM ").append(fromClause);
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
		try {
			nodeRowSet.beforeFirst();
			int i = 0;
			while (nodeRowSet.next()) {
				i++;
				int node = nodeRowSet.getInt(1);
				Integer nodeId = Integer.valueOf(node);
				ArrayList<Integer> list = nodeIdMap.get(nodeId);
				if (list == null) {
					list = new ArrayList<Integer>(5);
					nodeIdMap.put(nodeId, list);
				}
				list.add(Integer.valueOf(i));
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, "", e);
		}
	}
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
				if (getTreeType().equals(TREETYPE_Menu) && !isSummary)
				{
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
				//	else if (X_AD_Menu.ACTION_Workbench.equals(action))
				//		access = role.getWorkbenchAccess(AD_Window_ID);
				//	log.fine("getNodeDetail - " + name + " - " + actionColor + " - " + access);
					//
					if (access != null		//	rw or ro for Role 
						|| isTreeEditable)		//	Menu Window can see all
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
					// @Trifon-begin
					if ( getTreeType().equals(TREETYPE_ElementValue) ) {
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
		boolean needsTrim = treeNode != null;
		while (needsTrim)
		{
			needsTrim = false;
			Enumeration<MTreeNode> en = treeNode.preorderEnumeration();
			while (treeNode.getChildCount() > 0 && en.hasMoreElements())
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
		Enumeration en = treeNode.preorderEnumeration();
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
		System.out.println("Count=" + count);
	}   //  diagPrintTree

	/**
	 *  Get Root node
	 *  @return root
	 */
	public MTreeNode getRoot()
	{
		return treeNode;
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
