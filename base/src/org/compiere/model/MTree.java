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
 *  @version    $Id: MTree.java,v 1.2 2006/07/30 00:51:02 jjanke Exp $
 */
public class MTree extends MTree_Base
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6412057411585787707L;


	/**
	 *  Default Constructor.
	 * 	Need to call loadNodes explicitly
	 * 	@param ctx context for security
	 *  @param AD_Tree_ID   The tree to build
	 *  @param trxName transaction
	 */
	public MTree (Properties ctx, int AD_Tree_ID, String trxName)
	{
		super (ctx, AD_Tree_ID, trxName);
	}   //  MTree

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
		this (ctx, AD_Tree_ID, editable, clientTree, false, trxName);
	}   //  MTree

	public MTree (Properties ctx, int AD_Tree_ID, 
			boolean editable, boolean clientTree, boolean allNodes, String trxName)
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
		loadNodes(AD_User_ID);
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

	/**	Logger			*/
	private static CLogger s_log = CLogger.getCLogger(MTree.class);
	
	
	/**************************************************************************
	 *  Get default (oldest) complete AD_Tree_ID for KeyColumn.
	 *  Called from GridController
	 *  @param keyColumnName key column name, eg. C_Project_ID
	 *  @param AD_Client_ID client
	 *  @return AD_Tree_ID
	 */
	public static int getDefaultAD_Tree_ID (int AD_Client_ID, String keyColumnName)
	{
		s_log.config(keyColumnName);
		if (keyColumnName == null || keyColumnName.length() == 0)
			return 0;

		String TreeType = null;
		if (keyColumnName.equals("AD_Menu_ID"))
			TreeType = TREETYPE_Menu; 
		else if (keyColumnName.equals("C_ElementValue_ID"))
			TreeType = TREETYPE_ElementValue;
		else if (keyColumnName.equals("M_Product_ID"))
			TreeType = TREETYPE_Product;
		else if (keyColumnName.equals("C_BPartner_ID"))
			TreeType = TREETYPE_BPartner;
		else if (keyColumnName.equals("AD_Org_ID"))
			TreeType = TREETYPE_Organization;
		else if (keyColumnName.equals("C_Project_ID"))
			TreeType = TREETYPE_Project;
		else if (keyColumnName.equals("M_ProductCategory_ID"))
			TreeType = TREETYPE_ProductCategory;
		else if (keyColumnName.equals("M_BOM_ID"))
			TreeType = TREETYPE_BoM;
		else if (keyColumnName.equals("C_SalesRegion_ID"))
			TreeType = TREETYPE_SalesRegion;
		else if (keyColumnName.equals("C_Campaign_ID"))
			TreeType = TREETYPE_Campaign;
		else if (keyColumnName.equals("C_Activity_ID"))
			TreeType = TREETYPE_Activity;
		//
		else if (keyColumnName.equals("CM_CStage_ID"))
			TreeType = TREETYPE_CMContainerStage;
		else if (keyColumnName.equals("CM_Container_ID"))
			TreeType = TREETYPE_CMContainer;
		else if (keyColumnName.equals("CM_Media_ID"))
			TreeType = TREETYPE_CMMedia;
		else if (keyColumnName.equals("CM_Template_ID"))
			TreeType = TREETYPE_CMTemplate;
		else
		{
			s_log.log(Level.SEVERE, "Could not map " + keyColumnName);
			return 0;
		}

		int AD_Tree_ID = 0;
		String sql = "SELECT AD_Tree_ID, Name FROM AD_Tree "
			+ "WHERE AD_Client_ID=? AND TreeType=? AND IsActive='Y' AND IsAllNodes='Y' "
			+ "ORDER BY IsDefault DESC, AD_Tree_ID";
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, AD_Client_ID);
			pstmt.setString(2, TreeType);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				AD_Tree_ID = rs.getInt(1);
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			s_log.log(Level.SEVERE, sql, e);
		}

		return AD_Tree_ID;
	}   //  getDefaultAD_Tree_ID



	/*************************************************************************
	 *  Load Nodes and Bar
	 * 	@param AD_User_ID user for tree bar
	 */
	private void loadNodes (int AD_User_ID)
	{
		//  SQL for TreeNodes
		StringBuffer sql = new StringBuffer("SELECT "
			+ "tn.Node_ID,tn.Parent_ID,tn.SeqNo,tb.IsActive "
			+ "FROM ").append(getNodeTableName()).append(" tn"
			+ " LEFT OUTER JOIN AD_TreeBar tb ON (tn.AD_Tree_ID=tb.AD_Tree_ID"
			+ " AND tn.Node_ID=tb.Node_ID "
			+ (AD_User_ID != -1 ? " AND tb.AD_User_ID=? ": "") 	//	#1 (conditional)
			+ ") "
			+ "WHERE tn.AD_Tree_ID=?");								//	#2
		if (!m_editable)
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
					+ "FROM AD_Menu m");
			else
				sqlNode.append("SELECT m.AD_Menu_ID,  t.Name,t.Description,m.IsSummary,m.Action, "
					+ "m.AD_Window_ID, m.AD_Process_ID, m.AD_Form_ID, m.AD_Workflow_ID, m.AD_Task_ID, m.AD_Workbench_ID "
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
					.append(" AND (m.AD_Form_ID IS NULL OR EXISTS (SELECT * FROM AD_Form f WHERE m.AD_Form_ID=f.AD_Form_ID AND f.IsBetaFunctionality='N'))");
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
				if (getTreeType().equals(TREETYPE_Menu) && !isSummary)
				{
					int AD_Window_ID = m_nodeRowSet.getInt(index++);
					int AD_Process_ID = m_nodeRowSet.getInt(index++);
					int AD_Form_ID = m_nodeRowSet.getInt(index++);
					int AD_Workflow_ID = m_nodeRowSet.getInt(index++);
					int AD_Task_ID = m_nodeRowSet.getInt(index++);
					int AD_Workbench_ID = m_nodeRowSet.getInt(index++);
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
		System.out.println("Count=" + count);
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
