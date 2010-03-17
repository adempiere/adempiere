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
package org.compiere.grid.tree;

import java.util.logging.Level;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

import org.compiere.model.MTree;
import org.compiere.model.MTreeNode;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Trx;

/**
 *  AdempiereTreeModel provides a persistable tree model based on an MTree.
 *
 *  @author 	phib  2008/07/30
 *  FR [ 2032092 ] Java 6 improvements to tree drag and drop
 */
public class AdempiereTreeModel extends DefaultTreeModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8503954687681402088L;

	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(AdempiereTreeModel.class);

	private MTree m_MTree;
	public AdempiereTreeModel(TreeNode root) {
		super(root);
	}

	public AdempiereTreeModel(MTreeNode root, boolean b) {
		super(root, b);
	}

	public void setMTree(MTree tree) {
		
		m_MTree = tree;
		
	}
	
	public void saveChangedNodes(MTreeNode from, MTreeNode to) {
		int AD_Tree_ID = m_MTree.getAD_Tree_ID();
		Trx trx = Trx.get (Trx.createTrxName("AdempiereTreeModel"), true);
		try
		{
			int no = 0;
			for (int i = 0; i < from.getChildCount(); i++)
			{
				MTreeNode nd = (MTreeNode)from.getChildAt(i);
				StringBuffer sql = new StringBuffer("UPDATE ");
				sql.append(m_MTree.getNodeTableName())
					.append(" SET Parent_ID=").append(from.getNode_ID())
					.append(", SeqNo=").append(i)
					.append(", Updated=SysDate")
					.append(" WHERE AD_Tree_ID=").append(AD_Tree_ID)
					.append(" AND Node_ID=").append(nd.getNode_ID());
				no = DB.executeUpdate(sql.toString(),trx.getTrxName());
			}
			if (from != to)
				for (int i = 0; i < to.getChildCount(); i++)
				{
					MTreeNode nd = (MTreeNode)to.getChildAt(i);
					StringBuffer sql = new StringBuffer("UPDATE ");
					sql.append(m_MTree.getNodeTableName())
						.append(" SET Parent_ID=").append(to.getNode_ID())
						.append(", SeqNo=").append(i)
						.append(", Updated=SysDate")
						.append(" WHERE AD_Tree_ID=").append(AD_Tree_ID)
						.append(" AND Node_ID=").append(nd.getNode_ID());
					log.fine(sql.toString());
					no = DB.executeUpdate(sql.toString(),trx.getTrxName());
				}
			trx.commit(true);
		}
		catch (Exception e)
		{
			trx.rollback();
			log.log(Level.SEVERE, "move", e);
		}
		trx.close();
		trx = null;
		log.config("complete");
		
	}

	
}
