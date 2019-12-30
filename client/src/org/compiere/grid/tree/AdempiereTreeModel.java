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

import org.compiere.model.MColumn;
import org.compiere.model.MTable;
import org.compiere.model.MTree;
import org.compiere.model.MTreeNode;
import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Trx;

/**
 *  AdempiereTreeModel provides a persistable tree model based on an MTree.
 *
 *  @author 	phib  2008/07/30
 *  FR [ 2032092 ] Java 6 improvements to tree drag and drop
 *  @author Carlos Parada, cparada@erpya.com, ERPCyA http://www.erpya.com
 *  		<a href="https://github.com/adempiere/adempiere/issues/729">
 *			@see FR [ 729 ] Add Support to Parent Column And Search Column for Tree </a>
 *  
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
			for (int i = 0; i < from.getChildCount(); i++)
			{
				MTreeNode nd = (MTreeNode)from.getChildAt(i);
				String whereClause = "AD_Tree_ID="+AD_Tree_ID+ " AND Node_ID=" + nd.getNode_ID();
				PO tree = MTable.get(Env.getCtx(), m_MTree.getNodeTableName()).getPO(whereClause, trx.getTrxName());
				if (tree.get_ValueAsInt("Parent_ID") != from.getNode_ID() || tree.get_ValueAsInt("SeqNo") != i)
				{
					tree.set_CustomColumn("Parent_ID", from.getNode_ID());
					tree.set_CustomColumn("SeqNo", i);
					tree.saveEx();
				}
			}
			if (from != to)
			{
				// Renumber and set parent ID for the children of the 'to' node.
				int nextSeqNo = 0;
				for (int i = 0; i < to.getChildCount(); i++)
				{
					// Skip the entry of the 'from' node to avoid duplication
					//if ( i==Integer.parseInt(from.getSeqNo())) 
					//	continue;
					
					MTreeNode nd = (MTreeNode)to.getChildAt(i);
					String whereClause = "AD_Tree_ID="+AD_Tree_ID+ " AND Node_ID=" + nd.getNode_ID();
					PO tree = MTable.get(Env.getCtx(), m_MTree.getNodeTableName()).getPO(whereClause, trx.getTrxName());
					if (tree.get_ValueAsInt("Parent_ID") != to.getNode_ID() || tree.get_ValueAsInt("SeqNo") < nextSeqNo)
					{
						tree.set_CustomColumn("Parent_ID", to.getNode_ID());
						tree.set_CustomColumn("SeqNo", nextSeqNo++);
						tree.saveEx();
					}
					else
					{
						nextSeqNo = tree.get_ValueAsInt("SeqNo") + 1;
					}
				}
				//FR [ 729 ]
				//Update Table if has parent Column
				if (m_MTree.getParent_Column_ID() > 0) {
					MColumn parentColumn = MColumn.get(Env.getCtx(), m_MTree.getParent_Column_ID());
					MTable treeTable = MTable.get(Env.getCtx(),m_MTree.getAD_Table_ID());
					String[] keyColumns = treeTable.getKeyColumns();
					if (keyColumns.length>0) {
						String whereClause = keyColumns[0] + "=" + from.getNode_ID();
						PO table = MTable.get(Env.getCtx(), MTable.getTableName(Env.getCtx(), m_MTree.getAD_Table_ID())).getPO(whereClause, trx.getTrxName());
						if (table.get_ID() > 0) {
							if (to.getNode_ID()>0)
								table.set_ValueOfColumn(parentColumn.getColumnName(), to.getNode_ID());
							else 
								table.set_ValueOfColumn(parentColumn.getColumnName(), null);
							table.saveEx();
						}
					}
				}
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
