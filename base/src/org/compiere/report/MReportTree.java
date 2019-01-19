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
package org.compiere.report;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MAcctSchemaElement;
import org.compiere.model.MHierarchy;
import org.compiere.model.MTree;
import org.compiere.model.MTreeNode;
import org.compiere.util.CCache;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 *	Report Tree Model
 *	
 *  @author Jorg Janke
 *  @version $Id: MReportTree.java,v 1.3 2006/07/30 00:51:05 jjanke Exp $
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/1301">
 * 		@see BR [ 1301 ] Financial Report have a error with report cube</a>
 */
public class MReportTree {
	/**
	 * 	Get Report Tree (cached)
	 *	@param ctx context
	 *	@param hierarchyId optional hierarchy
	 *	@param elementType Account Schema Element Type
	 *	@return tree
	 */
	public static MReportTree get (Properties ctx, int hierarchyId, String elementType)
	{
		String key = hierarchyId + elementType;
		MReportTree tree = reportTreeCCache.get(key);
		if (tree == null)
		{
			tree = new MReportTree (ctx, hierarchyId, elementType);
			reportTreeCCache.put(key, tree);
		}
		return tree;	
	}	//	get

	/**
	 * 	Get Where Clause
	 *	@param ctx context
	 *	@param hierarchyId optional hierarchy
	 *	@param elementType Account Schema Element Type
	 *	@param dimensionId leaf element id
	 *	@return where clause
	 */
	public static String getWhereClause (Properties ctx, int hierarchyId, String elementType, int dimensionId)
	{
		MReportTree tree = get (ctx, hierarchyId, elementType);
		return tree.getWhereClause(dimensionId);
	}	//	getWhereClause

	/**
	 * 	Get Child IDs
	 *	@param ctx context
	 *	@param hierarchyId optional hierarchie
	 *	@param ElementType Account Schema Element Type
	 *	@param ID id
	 *	@return array of IDs
	 */
	public static Integer[] getChildIDs (Properties ctx, int hierarchyId, String ElementType, int ID)
	{
		MReportTree tree = get (ctx, hierarchyId, ElementType);
		return tree.getChildIDs(ID);	
	}	//	getChildIDs

	/**	Map with Tree				*/
	private static CCache<String,MReportTree> reportTreeCCache = new CCache<String,MReportTree>("MReportTree", 20);


	/**************************************************************************
	 * 	Report Tree
	 *	@param ctx context
	 *	@param PA_Hierarchy_ID optional hierarchy
	 *	@param ElementType Account Schema Element Type
	 */
	public MReportTree (Properties ctx, int PA_Hierarchy_ID, String ElementType)
	{
		elementType = ElementType;
		treeType = elementType;
		if (MAcctSchemaElement.ELEMENTTYPE_Account.equals(elementType))
			treeType = MTree.TREETYPE_ElementValue;
		if (MAcctSchemaElement.ELEMENTTYPE_OrgTrx.equals(elementType))
				treeType = MTree.TREETYPE_Organization;
		hierarchyId = PA_Hierarchy_ID;
		this.ctx = ctx;
		//
		int treeId = getAD_Tree_ID();
		//	Not found
		if (treeId == 0)
			throw new IllegalArgumentException("No AD_Tree_ID for TreeType=" + treeType
				+ ", PA_Hierarchy_ID=" + PA_Hierarchy_ID);
		//
		tree = new MTree (ctx, treeId, true, null);  // include inactive and empty summary nodes
		// remove summary nodes without children
		tree.trimTree();
	}	//	MReportTree

	/** Optional Hierarchy		*/
	private int 		hierarchyId = 0;
	/**	Element Type			*/
	private String 		elementType = null;
	/** Context					*/
	private Properties 	ctx = null;
	/** Tree Type				*/
	private String 		treeType = null;
	/**	The Tree				*/
	private MTree 		tree = null;
	/**	Logger					*/
	private CLogger 	logger = CLogger.getCLogger(getClass());

	
	/**
	 * 	Get AD_Tree_ID 
	 *	@return tree
	 */
	protected int getAD_Tree_ID ()
	{
		if (hierarchyId == 0)
			return getDefaultAD_Tree_ID();

		MHierarchy hierarchy = MHierarchy.get(ctx, hierarchyId);
		int treeId = hierarchy.getAD_Tree_ID (treeType);

		if (treeId == 0)
			return getDefaultAD_Tree_ID();
		
		return treeId;
	}	//	getAD_Tree_ID
	
	/**
	 * 	Get Default AD_Tree_ID 
	 * 	see MTree.getDefaultAD_Tree_ID
	 *	@return tree
	 */
	protected int getDefaultAD_Tree_ID()
	{
		int treeId = 0;
		int clientId = Env.getAD_Client_ID(ctx);
		
		String sql = "SELECT AD_Tree_ID, Name FROM AD_Tree "
			+ "WHERE AD_Client_ID=? AND TreeType=? AND IsActive='Y' AND IsAllNodes='Y' "
			+ "ORDER BY IsDefault DESC, AD_Tree_ID";	//	assumes first is primary tree
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, clientId);
			pstmt.setString(2, treeType);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				treeId = rs.getInt(1);
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			logger.log(Level.SEVERE, sql, e);
		}

		return treeId;
	}	//	getDefaultAD_Tree_ID

	/**
	 * 	Get Account Schema Element Type
	 *	@return element Type
	 */
	public String getElementType()
	{
		return elementType;
	}	//	getElementType
	
	/**
	 * 	Get Tree Type
	 *	@return tree type
	 */
	public String getTreeType()
	{
		return treeType;
	}	//	getTreeType
	
	/**
	 * 	Get Tree
	 *	@return tree
	 */
	public MTree getTree()
	{
		return tree;
	}	//	getTreeType

	/**
	 * 	Get Where Clause
	 *	@param id start node
	 *	@return ColumnName = 1 or ColumnName IN(1, 2, 3)
	 */	
	public String getWhereClause (int id)
	{
		logger.fine("(" + elementType + ") ID=" + id);
		String columnName = MAcctSchemaElement.getColumnName(elementType);
		//
		MTreeNode node = tree.getRoot().findNode(id);
		logger.finest("Root=" + node);
		//
		StringBuffer result = null;
		if (node != null && node.isSummary()) {
			Enumeration<MTreeNode> en = node.preorderEnumeration();
			StringBuffer sb = new StringBuffer ();
			while (en.hasMoreElements()) {
				MTreeNode treeNode = en.nextElement ();
				if (!treeNode.isSummary()) {
					if (sb.length () > 0) {
						sb.append (", ");
					}
					sb.append (treeNode.getNode_ID ());
					logger.finest ("- " + treeNode);
				} else {
					logger.finest ("- skipped parent (" + treeNode + ")");
				}
			}
			result = new StringBuffer(" ");
			result.append(columnName).append(" IN(");
			result.append (sb);
			result.append (") ");
		} else {	//	not found or not summary 
			result = new StringBuffer (columnName).append("=").append(id);
		}
		//
		logger.finest(result.toString());
		return result.toString();
	}	//	getWhereClause

	/**
	 * 	Get Child IDs
	 *	@param ID start node
	 *	@return array if IDs
	 */	
	public Integer[] getChildIDs (int ID)
	{
		logger.fine("(" + elementType + ") ID=" + ID);
		ArrayList<Integer> list = new ArrayList<Integer>(); 
		//
		MTreeNode node = tree.getRoot().findNode(ID);
		logger.finest("Root=" + node);
		//
		if (node != null && node.isSummary())
		{
			Enumeration<MTreeNode> enumeration = node.preorderEnumeration();
			while (enumeration.hasMoreElements())
			{
				MTreeNode treeNode = (MTreeNode)enumeration.nextElement();
				if (!treeNode.isSummary())
				{
					list.add(new Integer(treeNode.getNode_ID()));
					logger.finest("- " + treeNode);
				}
				else
					logger.finest("- skipped parent (" + treeNode + ")");
			}
		}
		else	//	not found or not summary 
			list.add(new Integer(ID));
		//
		Integer[] retValue = new Integer[list.size()];
		list.toArray(retValue);
		return retValue;
	}	//	getWhereClause

	
	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer("MReportTree[ElementType=");
		sb.append(elementType).append(",TreeType=").append(treeType)
			.append(",").append(tree)
			.append("]");
		return sb.toString();
	}	//	toString

}	//	MReportTree
