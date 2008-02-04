/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;

import org.compiere.util.DB;
import org.compiere.util.Env;


/**
 *	Product Category Callouts
 *	
 *  @author Karsten Thiemann kthiemann@adempiere.org
 *  
 */
public class CalloutProductCategory extends CalloutEngine
{
	/**
	 *	Loop detection of product category tree.
	 *
	 *  @param ctx context
	 *  @param WindowNo current Window No
	 *  @param mTab Grid Tab
	 *  @param mField Grid Field
	 *  @param value New Value
	 *  @return "" or error message
	 */
	public  String testForLoop (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue)
	{
		if (isCalloutActive() || value == null)
			return "";

		//	get values
		Integer newParentCategoryId = (Integer) mTab.getValue(MProductCategory.COLUMNNAME_M_Product_Category_Parent_ID);
		Integer productCategoryId = (Integer) mTab.getValue(MProductCategory.COLUMNNAME_M_Product_Category_ID);
		if (productCategoryId == null)
			productCategoryId = new Integer(0);
		ResultSet rs = null;
		Statement stmt = null;
		String sql = " SELECT M_Product_Category_ID, M_Product_Category_Parent_ID FROM M_Product_Category";
		final Vector<SimpleTreeNode> categories = new Vector<SimpleTreeNode>(100);
		try {
			stmt = DB.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				if(rs.getInt(1)==productCategoryId.intValue()) {
					categories.add(new SimpleTreeNode(rs.getInt(1), newParentCategoryId));
				}
				categories.add(new SimpleTreeNode(rs.getInt(1), rs.getInt(2)));
			}
 			if (hasLoop(newParentCategoryId, categories, productCategoryId.intValue())) {
				mTab.setValue(MProductCategory.COLUMNNAME_M_Product_Category_Parent_ID, oldValue);
				return "ProductCategoryLoopDetected";
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, sql, e);
			return e.getMessage();
		}
		finally
		{
			DB.close(rs, stmt);
			rs = null; stmt = null;
		}
		return "";
	}	//	testForLoop
	

	/**
	 * Recursive search for parent nodes - climbes the to the root.
	 * If there is a circle there is no root but it comes back to the start node.
	 * @param parentCategoryId
	 * @param categories
	 * @param loopIndicatorId
	 * @return
	 */
	private boolean hasLoop(int parentCategoryId, Vector<SimpleTreeNode> categories, int loopIndicatorId) {
		final Iterator iter = categories.iterator();
		boolean ret = false;
		while (iter.hasNext()) {
			SimpleTreeNode node = (SimpleTreeNode) iter.next();
			if(node.getNodeId()==parentCategoryId){
				if (node.getParentId()==0) {
					//root node, all fine
					return false;
				}
				if(node.getNodeId()==loopIndicatorId){
					//loop found
					return true;
				}
				ret = hasLoop(node.getParentId(), categories, loopIndicatorId);
			}
		}
		return ret;
	}	//hasLoop

	/**
	 * Simple class for tree nodes.
	 * @author Karsten Thiemann, kthiemann@adempiere.org
	 *
	 */
	private class SimpleTreeNode {
		/** id of the node */
		private int nodeId;
		/** id of the nodes parent */
		private int parentId;

		/**
		 * Constructor.
		 * @param nodeId
		 * @param parentId
		 */
		public SimpleTreeNode(int nodeId, int parentId) {
			this.nodeId = nodeId;
			this.parentId = parentId;
		}

		public int getNodeId() {
			return nodeId;
		}

		public int getParentId() {
			return parentId;
		}
	}

}	//	CalloutProductCategory
