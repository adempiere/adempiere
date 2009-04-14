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
package org.adempiere.webui.apps.form;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;

import org.adempiere.webui.LayoutUtils;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Checkbox;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Listbox;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.SimpleListModel;
import org.adempiere.webui.component.SimpleTreeModel;
import org.adempiere.webui.panel.ADForm;
import org.adempiere.webui.session.SessionManager;
import org.adempiere.webui.window.FDialog;
import org.compiere.model.MRole;
import org.compiere.model.MTree;
import org.compiere.model.MTreeNode;
import org.compiere.model.MTree_Node;
import org.compiere.model.MTree_NodeBP;
import org.compiere.model.MTree_NodeMM;
import org.compiere.model.MTree_NodePR;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zkex.zul.Borderlayout;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.East;
import org.zkoss.zkex.zul.North;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.SimpleTreeNode;
import org.zkoss.zul.Space;
import org.zkoss.zul.Splitter;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treeitem;

/**
 *	Tree Maintenance
 *	
 *  @author Jorg Janke
 *  @version $Id: VTreeMaintenance.java,v 1.3 2006/07/30 00:51:28 jjanke Exp $
 */
public class WTreeMaintenance extends ADForm implements EventListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3630156132596215136L;
	/**	Active Tree				*/
	private MTree		 	m_tree;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(WTreeMaintenance.class);
	
	
	private Borderlayout	mainLayout	= new Borderlayout ();
	private Panel 			northPanel	= new Panel ();
	private Label			treeLabel	= new Label ();
	private Listbox			treeField;
	private Button			bAddAll		= new Button ();
	private Button			bAdd		= new Button ();
	private Button			bDelete		= new Button ();
	private Button			bDeleteAll	= new Button ();
	private Checkbox		cbAllNodes	= new Checkbox ();
	private Label			treeInfo	= new Label ();
	//
	private Splitter		splitPane	= new Splitter();
	private Tree			centerTree;
	private Listbox			centerList	= new Listbox();

	
	@Override
	public void initForm()
	{
		try
		{
			preInit();
			jbInit ();
			action_loadTree();
			LayoutUtils.sendDeferLayoutEvent(mainLayout, 100);
		}
		catch (Exception ex)
		{
			log.log(Level.SEVERE, "VTreeMaintenance.init", ex);
		}
	}	//	init
	
	/**
	 * 	Fill Tree Combo
	 */
	private void preInit()
	{
		KeyNamePair[] trees = DB.getKeyNamePairs(MRole.getDefault().addAccessSQL(
			"SELECT AD_Tree_ID, Name FROM AD_Tree WHERE TreeType NOT IN ('BB','PC') ORDER BY 2", 
			"AD_Tree", MRole.SQL_NOTQUALIFIED, MRole.SQL_RW), false);
		treeField = new Listbox(trees);
		treeField.setMold("select");
		treeField.addActionListener(this);
		treeField.setSelectedIndex(0);
		//
		centerTree = new Tree();
		centerTree.addEventListener(Events.ON_SELECT, this);
	}	//	preInit
	
	/**
	 * 	Static init
	 *	@throws Exception
	 */
	private void jbInit () throws Exception
	{
		bAddAll.setSrc("images/FastBack24.png");
		bAdd.setSrc("images/StepBack24.png");
		bDelete.setSrc("images/StepForward24.png");
		bDeleteAll.setSrc("images/FastForward24.png");
		
		this.setWidth("99%");
		this.setHeight("100%");
		this.setStyle("position: absolute; padding: 0; margin: 0");
		this.appendChild (mainLayout);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setStyle("position: absolute");
		
		treeLabel.setText (Msg.translate(Env.getCtx(), "AD_Tree_ID"));
		cbAllNodes.setEnabled (false);
		cbAllNodes.setText (Msg.translate(Env.getCtx(), "IsAllNodes"));
		treeInfo.setText (" ");
		bAdd.setTooltiptext("Add to Tree");
		bAddAll.setTooltiptext("Add ALL to Tree");
		bDelete.setTooltiptext("Delete from Tree");
		bDeleteAll.setTooltiptext("Delete ALL from Tree");
		bAdd.addActionListener(this);
		bAddAll.addActionListener(this);
		bDelete.addActionListener(this);
		bDeleteAll.addActionListener(this);
		
		North north = new North();
		mainLayout.appendChild(north);
		north.appendChild(northPanel);
		north.setHeight("28px");
		//
		northPanel.appendChild (treeLabel);
		northPanel.appendChild (new Space());
		northPanel.appendChild (treeField);
		northPanel.appendChild (new Space());
		northPanel.appendChild (cbAllNodes);
		northPanel.appendChild (new Space());
		northPanel.appendChild (treeInfo);
		northPanel.appendChild (new Space());
		northPanel.appendChild (bAddAll);
		northPanel.appendChild (new Space());
		northPanel.appendChild (bAdd);
		northPanel.appendChild (new Space());
		northPanel.appendChild (bDelete);
		northPanel.appendChild (new Space());
		northPanel.appendChild (bDeleteAll);
		//
		Center center = new Center();
		mainLayout.appendChild(center);	
		center.appendChild(centerTree);
		center.setFlex(true);
		center.setAutoscroll(true);
		
		East east = new East();
		mainLayout.appendChild(east);
		east.appendChild(centerList);
		east.setCollapsible(false);
		east.setSplittable(true);
		east.setWidth("45%");
		centerList.setVflex(true);
		centerList.setFixedLayout(true);
		centerList.addEventListener(Events.ON_SELECT, this);
	}	//	jbInit

	/**
	 * 	Dispose
	 */
	public void dispose()
	{
		SessionManager.getAppDesktop().closeActiveWindow();
	}	//	dispose

	/**
	 * 	Action Listener
	 *	@param e event
	 */
	public void onEvent (Event e)
	{
		if (e.getTarget() == treeField)
		{
			action_loadTree();
			LayoutUtils.sendDeferLayoutEvent(mainLayout, 100);
		}
		else if (e.getTarget() == bAddAll)
			action_treeAddAll();
		else if (e.getTarget() == bAdd)
		{
			SimpleListModel model = (SimpleListModel) centerList.getModel();
			int i = centerList.getSelectedIndex();
			if (i >= 0) {
				action_treeAdd((ListItem)model.getElementAt(i));
			}
		}
			
		else if (e.getTarget() == bDelete)
		{
			SimpleListModel model = (SimpleListModel) centerList.getModel();
			int i = centerList.getSelectedIndex();
			if (i >= 0) {
				action_treeDelete((ListItem)model.getElementAt(i));
			}
		}			
		else if (e.getTarget() == bDeleteAll)
			action_treeDeleteAll();
		else if (e.getTarget() == centerList)
			onListSelection(e);
		else if (e.getTarget() == centerTree)
			onTreeSelection(e);
	}	//	actionPerformed

	
	/**
	 * 	Action: Fill Tree with all nodes
	 */
	private void action_loadTree()
	{
		KeyNamePair tree = treeField.getSelectedItem().toKeyNamePair();
		log.info("Tree=" + tree);
		if (tree.getKey() <= 0)
		{
			SimpleListModel tmp = new SimpleListModel();
			centerList.setItemRenderer(tmp);
			centerList.setModel(tmp);
			return;
		}
		//	Tree
		m_tree = new MTree (Env.getCtx(), tree.getKey(), null);
		cbAllNodes.setSelected(m_tree.isAllNodes());
		bAddAll.setEnabled(!m_tree.isAllNodes());
		bAdd.setEnabled(!m_tree.isAllNodes());
		bDelete.setEnabled(!m_tree.isAllNodes());
		bDeleteAll.setEnabled(!m_tree.isAllNodes());
		//
		String fromClause = m_tree.getSourceTableName(false);	//	fully qualified
		String columnNameX = m_tree.getSourceTableName(true);
		String actionColor = m_tree.getActionColorName();
		//	List
		SimpleListModel model = new SimpleListModel();
		String sql = "SELECT t." + columnNameX 
			+ "_ID,t.Name,t.Description,t.IsSummary,"
			+ actionColor
			+ " FROM " + fromClause
		//	+ " WHERE t.IsActive='Y'"	//	R/O
			+ " ORDER BY 2";
		sql = MRole.getDefault().addAccessSQL(sql, 
			"t", MRole.SQL_FULLYQUALIFIED, MRole.SQL_RO);
		log.config(sql);
		//	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, null);
			rs = pstmt.executeQuery ();
			while (rs.next ())
			{
				ListItem item = new ListItem(rs.getInt(1), rs.getString(2),
					rs.getString(3), "Y".equals(rs.getString(4)), rs.getString(5));
				model.addElement(item);
			}
 		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		//	List
		log.config("#" + model.getSize());
		centerList.setItemRenderer(model);
		centerList.setModel(model);
		
		//	Tree
		try {
			centerTree.setModel(null);
		} catch (Exception e) {
		}
		if (centerTree.getTreecols() != null)
			centerTree.getTreecols().detach();
		if (centerTree.getTreefoot() != null)
			centerTree.getTreefoot().detach();
		if (centerTree.getTreechildren() != null)
			centerTree.getTreechildren().detach();
		
		SimpleTreeModel.initADTree(centerTree, m_tree.getAD_Tree_ID(), m_WindowNo);
	}	//	action_fillTree
	
	/**
	 * 	List Selection Listener
	 *	@param e event
	 */
	private void onListSelection(Event e)
	{
		ListItem selected = null;
		try		
		{	
			SimpleListModel model = (SimpleListModel) centerList.getModel();
			int i = centerList.getSelectedIndex();
			selected = (ListItem)model.getElementAt(i);
		}
		catch (Exception ex)
		{
		}
		log.info("Selected=" + selected);
		if (selected != null)	//	allow add if not in tree
		{
			SimpleTreeModel tm = (SimpleTreeModel) centerTree.getModel();
			SimpleTreeNode stn = tm.find(tm.getRoot(), selected.id);
			if (stn != null) {
				int[] path = tm.getPath(tm.getRoot(), stn);
				Treeitem ti = centerTree.renderItemByPath(path);
				ti.setSelected(true);
			}
			bAdd.setEnabled(stn == null);
		}
	}	//	valueChanged
	
	/**
	 * 	Tree selection
	 *	@param e event
	 */
	private void onTreeSelection (Event e)
	{
		Treeitem ti = centerTree.getSelectedItem();
		SimpleTreeNode stn = (SimpleTreeNode) ti.getValue();
		MTreeNode tn = (MTreeNode)stn.getData();
		log.info(tn.toString());
		if (tn == null)
			return;
		ListModel model = centerList.getModel();
		int size = model.getSize();
		int index = -1;
		for (index = 0; index < size; index++)
		{
			ListItem item = (ListItem)model.getElementAt(index);
			if (item.id == tn.getNode_ID())
				break;
		}
		centerList.setSelectedIndex(index);
	}	//	propertyChange

	/**
	 * 	Action: Add Node to Tree
	 * 	@param item item
	 */
	private void action_treeAdd(ListItem item)
	{
		log.info("Item=" + item);
		if (item != null)
		{
			SimpleTreeModel model = (SimpleTreeModel) centerTree.getModel();
			SimpleTreeNode stn = model.find(model.getRoot(), item.id);
			if (stn != null) {
				MTreeNode tNode = (MTreeNode) stn.getData();
				tNode.setName(item.name);
				tNode.setAllowsChildren(item.isSummary);
				tNode.setImageIndicator(item.imageIndicator);
				model.nodeUpdated(stn);
				Treeitem ti = centerTree.renderItemByPath(model.getPath(model.getRoot(), stn));
				ti.setTooltiptext(item.description);
			} else {
				stn = new SimpleTreeNode(new MTreeNode(item.id, 0, item.name, item.description, 0, item.isSummary,
						item.imageIndicator, false, null), new ArrayList<Object>());
				model.addNode(stn);
			}
			//	May cause Error if in tree
			if (m_tree.isProduct())
			{
				MTree_NodePR node = new MTree_NodePR (m_tree, item.id);
				node.save();
			}
			else if (m_tree.isBPartner())
			{
				MTree_NodeBP node = new MTree_NodeBP (m_tree, item.id);
				node.save();
			}
			else if (m_tree.isMenu())
			{
				MTree_NodeMM node = new MTree_NodeMM (m_tree, item.id);
				node.save();
			}
			else
			{
				MTree_Node node = new MTree_Node (m_tree, item.id);
				node.save();
			}
		}
	}	//	action_treeAdd
	
	/**
	 * 	Action: Delete Node from Tree
	 * 	@param item item
	 */
	private void action_treeDelete(ListItem item)
	{
		log.info("Item=" + item);
		if (item != null)
		{
			SimpleTreeModel model = (SimpleTreeModel) centerTree.getModel();
			SimpleTreeNode stn = model.find(model.getRoot(), item.id);
			if (stn != null)
				model.removeNode(stn);
			
			//
			if (m_tree.isProduct())
			{
				MTree_NodePR node = MTree_NodePR.get (m_tree, item.id);
				if (node != null)
					node.delete(true);
			}
			else if (m_tree.isBPartner())
			{
				MTree_NodeBP node = MTree_NodeBP.get (m_tree, item.id);
				if (node != null)
					node.delete(true);
			}
			else if (m_tree.isMenu())
			{
				MTree_NodeMM node = MTree_NodeMM.get (m_tree, item.id);
				if (node != null)
					node.delete(true);
			}
			else
			{
				MTree_Node node = MTree_Node.get (m_tree, item.id);
				if (node != null)
					node.delete(true);
			}
		}
	}	//	action_treeDelete

	
	/**
	 * 	Action: Add All Nodes to Tree
	 */
	private void action_treeAddAll()
	{
		log.info("");
		ListModel model = centerList.getModel();
		int size = model.getSize();
		int index = -1;
		for (index = 0; index < size; index++)
		{
			ListItem item = (ListItem)model.getElementAt(index);
			action_treeAdd(item);
		}
	}	//	action_treeAddAll
	
	/**
	 * 	Action: Delete All Nodes from Tree
	 */
	private void action_treeDeleteAll()
	{
		log.info("");
		//TODO: translation
		if (FDialog.ask(m_WindowNo, null, "Remove all item(s) from tree?")) {
			ListModel model = centerList.getModel();
			int size = model.getSize();
			int index = -1;
			for (index = 0; index < size; index++)
			{
				ListItem item = (ListItem)model.getElementAt(index);
				action_treeDelete(item);
			}
		}
	}	//	action_treeDeleteAll
	
	/**************************************************************************
	 * 	Tree Maintenance List Item
	 */
	class ListItem
	{
		/**
		 * 	ListItem
		 *	@param ID
		 *	@param Name
		 *	@param Description
		 *	@param summary
		 *	@param ImageIndicator
		 */
		public ListItem (int ID, String Name, String Description, 
			boolean summary, String ImageIndicator)
		{
			id = ID;
			name = Name;
			description = Description;
			isSummary = summary;
			imageIndicator = ImageIndicator;
		}	//	ListItem
		
		/**	ID			*/
		public int id;
		/** Name		*/
		public String name;
		/** Description	*/
		public String description;
		/** Summary		*/
		public boolean isSummary;
		/** Indicator	*/
		public String imageIndicator;  //  Menu - Action
		
		/**
		 * 	To String
		 *	@return	String Representation
		 */
		public String toString ()
		{
			String retValue = name;
			if (description != null && description.length() > 0)
				retValue += " (" + description + ")";
			return retValue;
		}	//	toString
		
	}	//	ListItem

}	//	VTreeMaintenance
