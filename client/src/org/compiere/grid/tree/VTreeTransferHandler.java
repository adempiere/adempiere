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

import java.awt.datatransfer.Transferable;

import javax.swing.JComponent;
import javax.swing.JTree;
import javax.swing.TransferHandler;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import org.compiere.model.MTreeNode;

/**
 *  VTreeTransferHandler provides the TransferHandler for dragging and dropping
 *  within a tree.  See VTreePanel.
 *  
 *  
 *  @author 	phib  2008/07/30
 *  FR [ 2032092 ] Java 6 improvements to tree drag and drop
 */
public class VTreeTransferHandler extends TransferHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int getSourceActions(JComponent c) {
		return TransferHandler.MOVE;
	}

	protected Transferable createTransferable(JComponent c) {
		JTree tree = (JTree) c;
		MTreeNode node = (MTreeNode) tree.getSelectionPath().getLastPathComponent();
		return new TransferableTreeNode(node);
	}

	protected void exportDone(JComponent c, Transferable t, int action) {
		if (action == MOVE) {
			JTree tree = (JTree) c;
			MTreeNode node = null;
			try {
				node = (MTreeNode) t.getTransferData(TransferableTreeNode.TREE_NODE_FLAVOR);
			} catch (Exception e) {
				// ignore
			}
			
			if ( node != null )
				((DefaultTreeModel) tree.getModel()).removeNodeFromParent(node);
		}
	}

	public boolean canImport(TransferSupport info) {
		// Check for flavor
		if (!info.isDataFlavorSupported(TransferableTreeNode.TREE_NODE_FLAVOR)) {
			return false;
		}
		return true;
	}
	
	public boolean importData(TransferHandler.TransferSupport info) {
		if (!canImport(info))
			return false;

		JTree tree = (JTree) info.getComponent();
		AdempiereTreeModel model = (AdempiereTreeModel) tree.getModel();
		Transferable t = info.getTransferable();
		MTreeNode to = null;
		MTreeNode from = null;
		int index;
		try {
			from = (MTreeNode)t.getTransferData(TransferableTreeNode.TREE_NODE_FLAVOR);
		} 
		catch (Exception e) { return false; }

		if (info.isDrop()) {
			JTree.DropLocation dl = (JTree.DropLocation)info.getDropLocation();
			to = (MTreeNode) dl.getPath().getLastPathComponent();

			if (from == to)
				return false;

			index = dl.getChildIndex();
			if ( index == -1 )
				index = 0;  // insert as first child

		}
		else {              // it's a paste
			MTreeNode selected = (MTreeNode) tree.getSelectionPath().getLastPathComponent();
			if ( selected.isLeaf() ) {
				to = (MTreeNode) selected.getParent();
				index = to.getIndex(selected) + 1;  // insert after selected
			}
			else {
				to = selected;
				index = 0;
			}
		}

		model.insertNodeInto(from, to, index);
		tree.scrollPathToVisible(new TreePath(from.getPath()));   // display from's new location
		model.saveChangedNodes(from, to);

		return true;
	}

}
