/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2019 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.									  *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/
package org.adempiere.webui.component;

import java.util.ArrayList;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.webui.panel.MenuPanel;
import org.adempiere.webui.session.SessionManager;
import org.adempiere.webui.window.FDialog;
import org.adempiere.webui.window.WStringEditorDialog;
import org.compiere.model.MMenu;
import org.compiere.model.MTreeFavorite;
import org.compiere.model.MTreeFavoriteNode;
import org.compiere.model.MTreeNode;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.DropEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menupopup;
import org.zkoss.zul.SimpleTreeNode;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Treerow;

/**
 * A controller for the DPUserFavorite dashboard panel.  Handles the drag, drop and 
 * other events that occur.
 * 
 * @author jtrinidad Adaxa - source of the original code
 * @author marcalwestf Mario Calderone, Westfalia
 * 	<li><a href="https://github.com/adempiere/adempiere/issues/911">#911 User Favorite Tree Panel</a> - add Adaxa contribution
 * @author Michael McKay, mckayERP@gmail.com
 *   <li><a href="https://github.com/adempiere/adempiere/issues/2324">#2324 User Favorites will not accept entry without folder</a> 
 *
 */
public class ADTreeFavoriteOnDropListener implements EventListener
{
	private static SimpleFavoriteTreeModel	treeModel;
	private static int				windowNo;
	private static Tree				tree;
	private static int				mTreeFavoriteID;
	private static int				AD_Role_ID			= Env.getAD_Role_ID(Env.getCtx());
	private static int				AD_Client_ID		= Env.getAD_Client_ID(Env.getCtx());
	private static int				AD_Org_ID			= Env.getContextAsInt(Env.getCtx(), "#AD_Org_ID");
	private static int				AD_User_ID			= Env.getContextAsInt(Env.getCtx(), "#AD_User_ID");
	
	private static ADTreeFavoriteOnDropListener thisListener	= null;
	
	private static final CLogger	log					= CLogger.getCLogger(ADTreeFavoriteOnDropListener.class);
	public static final String		MENU_ITEM_DELETE	= "DELETE";
	public static final String		MENU_ITEM_EXPAND	= "StartExpanded";
	public static final String		MENU_ITEM_COLLAPSE	= "StartCollapsed";
	public static final String		MENU_ITEM_ADD_FOLDER = "add.folder";
	public static final String		MENU_ITEM_RENAME_FOLDER = "SimpleFavoriteTreeModel.rename";
	
	/**
	 * Create a new instance of this listener. Called once per user login.
	 * @param tree
	 * @param treeModel
	 * @param mTreeFavorite
	 * @param windowNo
	 * @return
	 */
	public static ADTreeFavoriteOnDropListener create(Tree tree, SimpleFavoriteTreeModel treeModel, MTreeFavorite mTreeFavorite,
			int windowNo)
	{
		ADTreeFavoriteOnDropListener.tree = tree;
		ADTreeFavoriteOnDropListener.treeModel = treeModel;
		ADTreeFavoriteOnDropListener.windowNo = windowNo;
		ADTreeFavoriteOnDropListener.mTreeFavoriteID = mTreeFavorite.getTreeID(AD_Role_ID, AD_User_ID, AD_Client_ID);

		return new ADTreeFavoriteOnDropListener();
	}
	
	/**
	 *  Events For Right Click And Menu Item Dragged Source to Target Folder
	 */
	@Override
	public void onEvent(Event event) throws Exception
	{
		if (Events.ON_RIGHT_CLICK.equals(event.getName()))
		{
			MouseEvent me = (MouseEvent) event;
			Treeitem target = (Treeitem) ((Treerow) me.getTarget()).getParent();
			SimpleTreeNode toNode = (SimpleTreeNode) target.getValue();
			menuItemList(toNode);
		}
		
		if (event instanceof DropEvent)
		{
			DropEvent de = (DropEvent) event;
			log.fine("Source=" + de.getDragged() + " Target=" + de.getTarget());
			
			if (de.getDragged() != de.getTarget())
			{
				Treeitem src = (Treeitem) ((Treerow) de.getDragged()).getParent();
				Treeitem target = (Treeitem) ((Treerow) de.getTarget()).getParent();

				Treerow tr = (Treerow) de.getDragged();
				String strDraggable = tr.getDraggable();

				MTreeFavorite mTreeFavorite = new MTreeFavorite(Env.getCtx(), 0, null);

				/*
				 *  Here Condition True when Node Moving Internally, False when Node Drag from Menu Bar to User Favorite Panel  
				 */
				if (strDraggable.equals(SimpleFavoriteTreeModel.USER_FAVORITE_DRAGGABLE_TYPE))
				{
					SimpleTreeNode stn_src = (SimpleTreeNode) src.getValue();
					SimpleTreeNode stn_target = (SimpleTreeNode) target.getValue();
					MTreeNode nd_src = (MTreeNode) stn_src.getData();
					MTreeNode nd_target = (MTreeNode) stn_target.getData();

					/*
					 * True When Source is a Menu Item, Otherwise its Folder Item.
					 */
					if (!nd_src.isSummary())
					{
						int menuID = nd_src.getMenu_ID();
						boolean menuAvailable;
						/*
						 * True when Target is Menu Item, Otherwise its Folder Item.
						 */
						if (!nd_target.isSummary())
						{
							// The target is a folder item
							// menuAvailable = true if the item already exists within the target parent folder
							menuAvailable = mTreeFavorite.isMenuAvailable(menuID, nd_target.getParent_ID(),	mTreeFavoriteID);
							
							// If moving within the same folder, just move the node.
							if (nd_src.getParent_ID() == nd_target.getParent_ID())
							{
								moveNode((SimpleTreeNode) src.getValue(), (SimpleTreeNode) target.getValue());
							}
							else if (menuAvailable)
							{
								// If not the same folder and the item already exists here, show a dialog.
								showDialog();
							}
							else
							{
								moveNode((SimpleTreeNode) src.getValue(), (SimpleTreeNode) target.getValue());
							}
						}
						else
						{
							// Target is a folder
							// If the folder is the same as the current on, move the node
							// otherwise, check if the node already exists in the target folder
							menuAvailable = mTreeFavorite.isMenuAvailable(menuID, nd_target.getNode_ID(), mTreeFavoriteID);
							if (menuAvailable && nd_target.getNode_ID() != nd_src.getParent_ID())
							{
								showDialog();  // Don't allow the duplicate.
							}
							else
							{
								moveNode((SimpleTreeNode) src.getValue(), (SimpleTreeNode) target.getValue());
							}
						}
					}
					else
					{
						moveNode((SimpleTreeNode) src.getValue(), (SimpleTreeNode) target.getValue());
					}
				}
				else if(strDraggable.equals(MenuPanel.MENU_ITEM_DRAGGABLE_TYPE))
				{
					int mID = Integer.valueOf((src.getValue().toString()));
					SimpleTreeNode stn_target = (SimpleTreeNode) target.getValue();
					MTreeNode nd = (MTreeNode) stn_target.getData();
					/*
					 * True when Target is Folder, Otherwise its Menu item.
					 */
					if (nd.isSummary())
					{
						if (mTreeFavorite.isMenuAvailable(mID, nd.getNode_ID(), mTreeFavoriteID))
						{
							showDialog();
						}
						else
						{
							// Add after last child
							int index = stn_target.getChildCount();
							insertNodeMenu(mID, nd.getNode_ID(), stn_target, index);
						}
					}
					else
					{
						SimpleTreeNode stn_target_parent = treeModel.getParent(stn_target);
						int pID = ((MTreeNode) stn_target_parent.getData()).getNode_ID();

						if (mTreeFavorite.isMenuAvailable(mID, pID, mTreeFavoriteID))
							showDialog();
						else
						{
							int index = stn_target_parent.getChildren().indexOf(stn_target);							
							insertNodeMenu(mID, pID, stn_target_parent, index+1);
						}
					}
				}
			}
		}
	}

	/**
	 * Show Dialog for Warning
	 */
	private void showDialog()
	{
		FDialog.warn(0, "its.already.there");  // Don't need to translate - the dialog does it.
	}

	/**
	 * When Right click on Item show Delete Menupopup for Delete a node.
	 * @param toNode
	 */
	private void menuItemList(SimpleTreeNode toNode)
	{
		int path[] = treeModel.getPath(treeModel.getRoot(), toNode);
		Treeitem toItem = tree.renderItemByPath(path);

		tree.setSelectedItem(toItem);
		Events.sendEvent(tree, new Event(Events.ON_RIGHT_CLICK, tree));
		
		Menupopup popup = new Menupopup();
		Menuitem menuItem = new Menuitem(Msg.getMsg(Env.getCtx() , "delete"), "/images/dark/Delete16.png");
		menuItem.setValue(MENU_ITEM_DELETE);
		menuItem.setParent(popup);
		menuItem.addEventListener(Events.ON_CLICK, new DeleteListener(toNode));
		
		MTreeNode mtn = (MTreeNode) toNode.getData();
		if (mtn.isSummary())
		{
			MTreeFavoriteNode favNode = new MTreeFavoriteNode(Env.getCtx(), mtn.getNode_ID(), null);
			if (favNode.isCollapsible())
			{
				menuItem = new Menuitem(Msg.getMsg(Env.getCtx(), MENU_ITEM_EXPAND), "/images/Minus16.png");
				menuItem.setValue(MENU_ITEM_EXPAND);
			}
			else
			{
				menuItem = new Menuitem(Msg.getMsg(Env.getCtx(), MENU_ITEM_COLLAPSE), "/images/Plus16.png");
				menuItem.setValue(MENU_ITEM_COLLAPSE);
			}
			menuItem.setParent(popup);
			menuItem.addEventListener(Events.ON_CLICK, new CollExpdListener(favNode));

			menuItem = new Menuitem(Msg.getMsg(Env.getCtx(), MENU_ITEM_RENAME_FOLDER), "/images/dark/FolderPlain16.png");
			menuItem.setValue(MENU_ITEM_RENAME_FOLDER);
			menuItem.setParent(popup);
			menuItem.addEventListener(Events.ON_CLICK, new RenameFolderListener(toNode));

		}

		menuItem = new Menuitem(Msg.getMsg(Env.getCtx(), MENU_ITEM_ADD_FOLDER), "/images/dark/FolderAdd16.png");
		menuItem.setValue(MENU_ITEM_ADD_FOLDER);
		menuItem.setParent(popup);
		menuItem.addEventListener(Events.ON_CLICK, new AddFolderListener(toNode));

		popup.setPage(tree.getPage());
		popup.open(toItem.getTreerow(), "after_pointer");
	}

	/**
	 * Listener for Delete Node on Right click on MouseEvent
	 * 
	 * @author Logilite
	 */
	class DeleteListener implements EventListener
	{
		private SimpleTreeNode	toNode;

		DeleteListener(SimpleTreeNode toNode)
		{
			this.toNode = toNode;
		}

		public void onEvent(Event event) throws Exception
		{
			if (Events.ON_CLICK.equals(event.getName()) && event.getTarget() instanceof Menuitem)
			{
				Menuitem menuItem = (Menuitem) event.getTarget();
				if (MENU_ITEM_DELETE.equals(menuItem.getValue()))
				{
					deleteNodeMenu(toNode);
				}
			}
		}

	}

	/**
	 * Listener for Adding Folders on Right click on MouseEvent
	 * 
	 */
	public class AddFolderListener implements EventListener
	{
		private SimpleTreeNode	toNode;

		public AddFolderListener(SimpleTreeNode toNode)
		{
			this.toNode = toNode;
		}

		public void onEvent(Event event) throws Exception
		{
			if (Events.ON_CLICK.equals(event.getName()) && event.getTarget() instanceof Menuitem)
			{
				Menuitem menuItem = (Menuitem) event.getTarget();
				if (MENU_ITEM_ADD_FOLDER.equals(menuItem.getValue()))
				{
					addNewFolder(toNode);
				}
				else if (event.getTarget() instanceof Component)  // The DPUserFavorites Hint box (Div)
				{
					// Add as root.
					addNewFolder(null);
				}
			}
		}
	}

	/**
	 * Listener to rename folders on Right click on MouseEvent
	 * 
	 */
	class RenameFolderListener implements EventListener
	{
		private SimpleTreeNode	toNode;

		RenameFolderListener(SimpleTreeNode toNode)
		{
			this.toNode = toNode;
		}

		public void onEvent(Event event) throws Exception
		{
			if (Events.ON_CLICK.equals(event.getName()) && event.getTarget() instanceof Menuitem)
			{
				Menuitem menuItem = (Menuitem) event.getTarget();
				if (MENU_ITEM_RENAME_FOLDER.equals(menuItem.getValue()))
				{
					MTreeNode mtn = (MTreeNode) toNode.getData();
					WStringEditorDialog dialog = new WStringEditorDialog("Edit folder text",
							mtn.getName() == null ? "" : mtn.getName(), true, 60);
					
					dialog.setAttribute(Window.MODE_KEY, Window.MODE_MODAL);
					SessionManager.getAppDesktop().showWindow(dialog);
					if (!dialog.isCancelled())
					{
						treeModel.renameNode(toNode, dialog.getText());
					}
				}
			}
		}
	}
	
	
	/**
	 * Listener for set default start Collapse/Expand Node by Right click on
	 * MouseEvent.
	 * 
	 * @author Sachin
	 */
	class CollExpdListener implements EventListener
	{
		private MTreeFavoriteNode favNode;

		CollExpdListener(MTreeFavoriteNode favNode)
		{
			this.favNode = favNode;
		}

		public void onEvent(Event event) throws Exception
		{
			if (Events.ON_CLICK.equals(event.getName()) && event.getTarget() instanceof Menuitem)
			{
				Menuitem menuItem = (Menuitem) event.getTarget();
				if (MENU_ITEM_EXPAND.equals(menuItem.getValue()))
				{
					favNode.setIsCollapsible(false);
				}
				else if (MENU_ITEM_COLLAPSE.equals(menuItem.getValue()))
				{
					favNode.setIsCollapsible(true);
				}
				favNode.saveEx();
			}

		} // onEvent

	} // ColExpListener
	
	/**
	 * Insert Folder as Node in Tree 
	 * @param toNode - the parent node to attach to. If null, use the root node.
	 */
	private void addNewFolder(SimpleTreeNode toNode)
	{
		if (toNode == null)
		{
			toNode = treeModel.getRoot();
		}
		SimpleTreeNode parentNode = null;
		
		MTreeFavoriteNode mTreeFavoriteNode = new MTreeFavoriteNode(Env.getCtx(), 0, null);
		mTreeFavoriteNode.set_ValueOfColumn(MTreeFavoriteNode.COLUMNNAME_AD_Client_ID, AD_Client_ID);
		mTreeFavoriteNode.setAD_Org_ID(AD_Org_ID);
		mTreeFavoriteNode.setAD_Tree_Favorite_ID(mTreeFavoriteID);
		mTreeFavoriteNode.setIsSummary(true);
		mTreeFavoriteNode.setNodeName(Msg.getMsg(Env.getCtx(), "new.folder"));
		
		if (((MTreeNode) toNode.getData()).isSummary())
			parentNode = toNode;
		else
			parentNode = treeModel.getParent(toNode);
		mTreeFavoriteNode.setParent_ID(((MTreeNode) toNode.getData()).getNode_ID());
		
		mTreeFavoriteNode.setSeqNo(0);

		if (!mTreeFavoriteNode.save())
			throw new AdempiereException(Msg.getMsg(Env.getCtx(), "could.not.create.node"));
		else
		{
			MTreeNode mtnNew = new MTreeNode(mTreeFavoriteNode.getAD_Tree_Favorite_Node_ID(),
					mTreeFavoriteNode.getSeqNo(), mTreeFavoriteNode.getNodeName(), "",
					mTreeFavoriteNode.getParent_ID(), mTreeFavoriteNode.isSummary(), mTreeFavoriteNode.getAD_Menu_ID(),
					null, false);
			SimpleTreeNode newNode = new SimpleTreeNode(mtnNew, new ArrayList());
			
			int index = 0;
			if (parentNode.equals(treeModel.getParent(toNode)))
				index = parentNode.getChildren().indexOf(toNode);  // Add Before

			try
			{
				treeModel.addNode(parentNode, newNode, index);
				int[] path = treeModel.getPath(treeModel.getRoot(), newNode);
				Treeitem ti = tree.renderItemByPath(path);
				tree.setSelectedItem(ti);
				Events.sendEvent(tree, new Event(Events.ON_SELECT, tree));
			}
			catch (Exception e)
			{
				FDialog.warn(0, Msg.getMsg(Env.getCtx(), "SelectMenuItem"));
			}
		}
	}

	/**
	 * Insert Node into Tree it's contains only Menu type node, Dragged from
	 * Menu Tab.
	 * 
	 * @param menuID
	 * @param parentNodeID
	 * @param stn
	 */
	public static void insertNodeMenu(int menuID, int parentNodeID, SimpleTreeNode stn, int index)
	{
		MTreeFavoriteNode mTreeFavoriteNode = new MTreeFavoriteNode(Env.getCtx(), 0, null);
		mTreeFavoriteNode.set_ValueOfColumn(MTreeFavoriteNode.COLUMNNAME_AD_Client_ID, AD_Client_ID);
		mTreeFavoriteNode.setAD_Org_ID(AD_Org_ID);
		mTreeFavoriteNode.setAD_Tree_Favorite_ID(mTreeFavoriteID);
		mTreeFavoriteNode.setSeqNo(0);
		mTreeFavoriteNode.setParent_ID(parentNodeID);
		mTreeFavoriteNode.setIsSummary(false);
		mTreeFavoriteNode.setAD_Menu_ID(menuID);
		if (!mTreeFavoriteNode.save())
		{
			throw new AdempiereException(Msg.getMsg(Env.getCtx(), "could.not.create.node"));
		}
		MMenu menu = new MMenu(Env.getCtx(), menuID, null);
		MTreeNode mNode = new MTreeNode(mTreeFavoriteNode.getAD_Tree_Favorite_Node_ID(), mTreeFavoriteNode.getSeqNo(),
				menu.getName(), menu.getName(), mTreeFavoriteNode.getParent_ID(), mTreeFavoriteNode.isSummary(),
				mTreeFavoriteNode.getAD_Menu_ID(), menu.getAction(), false);

		SimpleTreeNode node = new SimpleTreeNode(mNode, new ArrayList());
		
		SimpleTreeNode parentNode = null;
		
		if (stn == null)
		{
			parentNode = treeModel.getRoot();
		}
		else
		{
			parentNode = stn;
		}
		
		if (parentNode.getChildren().indexOf(node) != -1)
			index = parentNode.getChildren().indexOf(node) +1;

		treeModel.addNode(parentNode, node, index);
		int[] path = treeModel.getPath(treeModel.getRoot(), node);
		Treeitem ti =	tree.renderItemByPath(path);
		tree.setSelectedItem(ti);		
		Events.sendEvent(tree, new Event(Events.ON_SELECT, tree));
	}

	/**
	 * Delete a specific node from the tree as well as the database
	 * 
	 * @param node
	 */
	public static void deleteNodeMenu(SimpleTreeNode node)
	{
		int nodeID = ((MTreeNode) node.getData()).getNode_ID();
		String deleteNodeQuery = "DELETE FROM ad_tree_favorite_node " + "WHERE ad_tree_favorite_node_id IN ( "
				+ "	WITH RECURSIVE supplytree(ad_tree_favorite_node_id) AS ("
				+ " 	SELECT ad_tree_favorite_node_id, parent_id 		FROM ad_tree_favorite_node "
				+ "		WHERE ad_tree_favorite_node_id = ?"
				+ "		UNION ALL "
				+ "		SELECT si.ad_tree_favorite_node_id, si.parent_id 	FROM ad_tree_favorite_node As si "
				+ "		INNER JOIN supplytree AS sp	ON (si.parent_id = sp.ad_tree_favorite_node_id) "
				+ ") SELECT  ad_tree_favorite_node_id 	FROM supplytree "
				+ "  ORDER BY ad_tree_favorite_node_id)";

		int noOfRowsDeleted = DB.executeUpdate(deleteNodeQuery, nodeID, null);
		if (noOfRowsDeleted > 0)
			treeModel.removeNode(node);
	}

	/**
	 * Internally movement of Tree node
	 * 
	 * @param movingNode
	 * @param toNode
	 */
	private void moveNode(SimpleTreeNode movingNode, SimpleTreeNode toNode)
	{
		log.info(movingNode.toString() + " to " + toNode.toString());

		if (movingNode == toNode)
			return;

		MTreeNode toMNode = (MTreeNode) toNode.getData();
		if (!toMNode.isSummary()) // drop on a child node
		{
			moveNode(movingNode, toNode, false);
		}
		else // drop on a summary node
		{
			// prompt user to select insert after or drop into the summary node
			int path[] = treeModel.getPath(treeModel.getRoot(), toNode);
			Treeitem toItem = tree.renderItemByPath(path);
			
			tree.setSelectedItem(toItem);
			Events.sendEvent(tree, new Event(Events.ON_SELECT, tree));

			MenuListener listener = new MenuListener(movingNode, toNode);

			Menupopup popup = new Menupopup();
			Menuitem menuItem = new Menuitem( Msg.getMsg(Env.getCtx(), "insert.after") );
			menuItem.setValue("InsertAfter");
			menuItem.setParent(popup);
			menuItem.addEventListener(Events.ON_CLICK, listener);

			menuItem = new Menuitem(Msg.getMsg(Env.getCtx(), "move.into"));
			menuItem.setValue("MoveInto");
			menuItem.setParent(popup);
			menuItem.addEventListener(Events.ON_CLICK, listener);
			popup.setPage(tree.getPage());
			popup.open(toItem.getTreerow(), "overlap");
		}
	} // moveNode

	/**
	 * It's specify the Moving node where to inserted... Like Insert After or
	 * Move Into.
	 * 
	 * @param movingNode
	 * @param toNode
	 * @param moveInto
	 */
	private void moveNode(SimpleTreeNode movingNode, SimpleTreeNode toNode, boolean moveInto)
	{
		SimpleTreeNode newParent;
		int index;

		// remove
		SimpleTreeNode oldParent = treeModel.getParent(movingNode);
		treeModel.removeNode(movingNode);

		// get new index
		if (!moveInto)
		{
			newParent = treeModel.getParent(toNode);
			index = newParent.getChildren().indexOf(toNode) + 1; // the next node
		}
		else
		// drop on a summary node
		{
			newParent = toNode;
			index = 0; // the first node
		}

		// insert
		treeModel.addNode(newParent, movingNode, index);

		int path[] = treeModel.getPath(treeModel.getRoot(), movingNode);
		Treeitem movingItem = tree.renderItemByPath(path);
		tree.setSelectedItem(movingItem);
		Events.sendEvent(tree, new Event(Events.ON_SELECT, tree));

		// *** Save changes to disk
		Trx trx = Trx.get(Trx.createTrxName("ADTreeFavoriteNode"), true);
		try
		{
			MTreeNode oldMParent = (MTreeNode) oldParent.getData();
			for (int i = 0; i < oldParent.getChildCount(); i++)
			{
				SimpleTreeNode nd = (SimpleTreeNode) oldParent.getChildAt(i);
				MTreeNode md = (MTreeNode) nd.getData();
				StringBuffer sql = new StringBuffer("UPDATE ");
				sql.append(" AD_Tree_Favorite_Node ").append(" SET Parent_ID=").append(oldMParent.getNode_ID())
						.append(", SeqNo=").append(i).append(", Updated=SysDate")
						.append(" WHERE AD_Tree_Favorite_Node_ID=").append(md.getNode_ID());
				log.fine(sql.toString());
				DB.executeUpdate(sql.toString(), trx.getTrxName());
				md.setParent_ID(oldMParent.getNode_ID());
			}
			if (oldParent != newParent)
			{
				MTreeNode newMParent = (MTreeNode) newParent.getData();
				for (int i = 0; i < newParent.getChildCount(); i++)
				{
					SimpleTreeNode nd = (SimpleTreeNode) newParent.getChildAt(i);
					MTreeNode md = (MTreeNode) nd.getData();
					StringBuffer sql = new StringBuffer("UPDATE ");
					sql.append(" AD_Tree_Favorite_Node ").append(" SET Parent_ID=").append(newMParent.getNode_ID())
							.append(", SeqNo=").append(i).append(", Updated=SysDate")
							.append(" WHERE AD_Tree_Favorite_Node_ID=").append(md.getNode_ID());
					log.fine(sql.toString());
					DB.executeUpdate(sql.toString(), trx.getTrxName());
					md.setParent_ID(newMParent.getNode_ID());
				}
			}
			// COMMIT *********************
			trx.commit(true);
		}
		catch (Exception e)
		{
			trx.rollback();
			FDialog.error(windowNo, tree, "Tree Update Error", e.getLocalizedMessage());
		}
		finally
		{
			trx.close();
			trx = null;
		}
	}
	

	/**
	 * Listener for Movement of Node
	 * 
	 * @author Logilite
	 */
	class MenuListener implements EventListener
	{
		private SimpleTreeNode	movingNode;
		private SimpleTreeNode	toNode;

		MenuListener(SimpleTreeNode movingNode, SimpleTreeNode toNode)
		{
			this.movingNode = movingNode;
			this.toNode = toNode;
		}

		public void onEvent(Event event) throws Exception
		{
			if (Events.ON_CLICK.equals(event.getName()) && event.getTarget() instanceof Menuitem)
			{
				Menuitem menuItem = (Menuitem) event.getTarget();
				if ("InsertAfter".equals(menuItem.getValue()))
				{
					moveNode(movingNode, toNode, false);
				}
				else if ("MoveInto".equals(menuItem.getValue()))
				{
					moveNode(movingNode, toNode, true);
				}
			}
		}
	}
}
