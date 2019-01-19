/******************************************************************************
 * Copyright (C) 2009 Low Heng Sin                                            *
 * Copyright (C) 2009 Idalica Corporation                                     *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *****************************************************************************/
package org.adempiere.webui.panel;


import org.adempiere.webui.component.Checkbox;
import org.adempiere.webui.component.SimpleTreeModel;
import org.adempiere.webui.util.TreeUtils;
import org.compiere.model.MTreeNode;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Util;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Panelchildren;
import org.zkoss.zul.SimpleTreeNode;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treeitem;

/**
 * 
 * @author hengsin
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com 2015-09-09
 *  	<li>FR [ 9223372036854775807 ] Add Support to Dynamic Tree
 * @see https://adempiere.atlassian.net/browse/ADEMPIERE-442
 */
public class ADTreePanel extends Panel implements EventListener
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 5473705529310157142L;
	private TreeSearchPanel pnlSearch;
    private Tree tree;
    private int treeId = 0 ;
    
    private Checkbox chkExpand; // Elaine 2009/02/27 - expand tree
    
    /**
     * Constructor with window no and editable property
     * @param windowNo
     * @param editable
     */
    public ADTreePanel(int windowNo, boolean editable) {
    	this.editable = editable;
    	this.windowNo = windowNo;
        init();        
    }
    
	/** Tree is editable (can move nodes) - also not active shown   */
	private boolean     editable;
	/**	Window No	*/
	private int 		windowNo = 0;
    
    /**
     * @param AD_Tree_ID
     * @param windowNo
     * @param whereClause
     */
    public void initTree(int AD_Tree_ID, String whereClause) {
    	if(!Util.isEmpty(whereClause)) {
			whereClause = Env.parseContext(Env.getCtx(), windowNo, whereClause, false, false);
		}
    	SimpleTreeModel.initADTree(tree, AD_Tree_ID, windowNo, editable, whereClause, null);
    	pnlSearch.initialise();
    }
    //	End Yamel Senih
    
    private void init()
    {
    	this.setWidth("100%");
    	this.setHeight("100%");
    	
        tree = new Tree();
        tree.setMultiple(false);
        tree.setWidth("100%");
        tree.setVflex(true);
        tree.setPageSize(-1); // Due to bug in the new paging functionality
        
        tree.setStyle("border: none");
        
        pnlSearch = new TreeSearchPanel(tree, Events.ON_SELECT);
        
        Toolbar toolbar = new Toolbar();
        toolbar.appendChild(pnlSearch);
        this.appendChild(toolbar);
        
        Panelchildren pc = new Panelchildren();
        this.appendChild(pc);
        pc.appendChild(tree);  
        
        // Elaine 2009/02/27 - expand tree
        toolbar = new Toolbar();
        chkExpand = new Checkbox();
        chkExpand.setText(Msg.getMsg(Env.getCtx(), "ExpandTree"));
        chkExpand.addEventListener(Events.ON_CHECK, this);
        toolbar.appendChild(chkExpand);
        this.appendChild(toolbar);
    }
    
    /**
     * @param event
     * @see EventListener#onEvent(Event)
     */
    public void onEvent(Event event)
    {
        String eventName = event.getName();
        
        // Elaine 2009/02/27 - expand tree
        if (eventName.equals(Events.ON_CHECK) && event.getTarget() == chkExpand)
        {
        	expandOnCheck();
        }
        //
    }
    
    /**
     * @return tree
     */
	public Tree getTree() 
	{
		return tree;
	}
	
	/**
	* expand all node
	*/
	public void expandAll()
	{
		if (!chkExpand.isChecked())
			chkExpand.setChecked(true);
	
		TreeUtils.expandAll(tree);
	}
	
	/**
	 * collapse all node
	 */
	public void collapseAll()
	{
		if (chkExpand.isChecked())
			chkExpand.setChecked(false);
	
		TreeUtils.collapseAll(tree);
	}
	
	/**
	 *  On check event for the expand checkbox
	 */
	private void expandOnCheck()
	{
		if (chkExpand.isChecked())
			expandAll();
		else
			collapseAll();
	}
	//
	
	/**************************************************************************
	 *  Node Changed - synchronize Node
	 *
	 *  @param  save    true the node was saved (changed/added), false if the row was deleted
	 *  @param  keyID   the ID of the row changed
	 *  @param  name	name
	 *  @param  description	description
	 *  @param  isSummary	summary node
	 *  @param  imageIndicator image indicator
	 */
	public void nodeChanged (boolean save, int keyID,
		String name, String description, boolean isSummary, String imageIndicator)
	{
		if (tree == null)
			return;
		
		//	if ID==0=root - don't update it
		if (keyID == 0)
			return;	

		//  try to find the node
		SimpleTreeModel model = (SimpleTreeModel) tree.getModel();
		SimpleTreeNode root = model.getRoot();
		SimpleTreeNode node = model.find(null, keyID);
		
		//  Node not found and saved -> new
		if (node == null && save)
		{
			MTreeNode rootData = (MTreeNode) root.getData();
			MTreeNode mTreeNode = new MTreeNode (keyID, 0, name, description,
				rootData.getNode_ID(), isSummary, imageIndicator, false, null);
			SimpleTreeNode newNode = new SimpleTreeNode(mTreeNode, null); 
			model.addNode(root, newNode, 0);
			int[] path = model.getPath(model.getRoot(), newNode);
			Treeitem ti = tree.renderItemByPath(path);
			tree.setSelectedItem(ti);
		}

		//  Node found and saved -> change
		else if (node != null && save)
		{
			MTreeNode mTreeNode = (MTreeNode) node.getData();
			mTreeNode.setName (name);
			mTreeNode.setAllowsChildren(isSummary);
			int[] path = model.getPath(model.getRoot(), node);
			Treeitem ti = tree.renderItemByPath(path);
			tree.setSelectedItem(ti);
		}

		//  Node found and not saved -> delete
		else if (node != null && !save)
		{
			model.removeNode(node);
		}

		//  Error
		else
		{
			node = null;
		}

		//  Nothing to display
		if (node == null)
			return;

	}   //  nodeChanged

	public int getTreeId()
	{
		return treeId;
	}

}
