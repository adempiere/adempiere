package org.adempiere.webui.component;

import java.util.ArrayList;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.webui.window.FDialog;
import org.compiere.model.MMenu;
import org.compiere.model.MTreeFavorite;
import org.compiere.model.MTreeFavoriteNode;
import org.compiere.model.MTreeNode;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
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

public class ADTreeFavoriteOnDropListener implements EventListener
{
	private SimpleFavoriteTreeModel	treeModel;
	private int						windowNo;
	private Tree					tree;
	private int						mTreeFavoriteID;
	private int						AD_Role_ID			= Env.getAD_Role_ID(Env.getCtx());
	private int						AD_Client_ID		= Env.getAD_Client_ID(Env.getCtx());
	private int						AD_Org_ID			= Env.getContextAsInt(Env.getCtx(), "#AD_Org_ID");
	private int						AD_User_ID			= Env.getContextAsInt(Env.getCtx(), "#AD_User_ID");
	private static final CLogger	log					= CLogger.getCLogger(ADTreeFavoriteOnDropListener.class);
	public static final String		MENU_ITEM_DELETE	= "DELETE";
	public static final String		MENU_ITEM_EXPAND	= "StartExpanded";
	public static final String		MENU_ITEM_COLLAPSE	= "StartCollapsed";
	
	public ADTreeFavoriteOnDropListener(Tree tree, SimpleFavoriteTreeModel treeModel, MTreeFavorite mTreeFavorite,
			int windowNo)
	{
		this.tree = tree;
		this.treeModel = treeModel;
		this.windowNo = windowNo;
		mTreeFavoriteID = mTreeFavorite.getTreeID(AD_Role_ID, AD_User_ID, AD_Client_ID);
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
				if (!strDraggable.equals("favourite"))
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
							menuAvailable = mTreeFavorite.isMenuAvailable(menuID, nd_target.getParent_ID(),	mTreeFavoriteID);
							if (nd_src.getParent_ID() == nd_target.getParent_ID())
								moveNode((SimpleTreeNode) src.getValue(), (SimpleTreeNode) target.getValue());
							else if (menuAvailable)
								showDialog();
							else
								moveNode((SimpleTreeNode) src.getValue(), (SimpleTreeNode) target.getValue());
						}
						else
						{
							menuAvailable = mTreeFavorite.isMenuAvailable(menuID, nd_target.getNode_ID(), mTreeFavoriteID);
							if (menuAvailable)
								showDialog();
							else
								moveNode((SimpleTreeNode) src.getValue(), (SimpleTreeNode) target.getValue());
						}
					}
					else
					{
						moveNode((SimpleTreeNode) src.getValue(), (SimpleTreeNode) target.getValue());
					}
				}
				else
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
							showDialog();
						else
							insertNodeMenu(mID, nd.getNode_ID(), stn_target);
					}
					else
					{
						SimpleTreeNode stn_target_parent = treeModel.getParent(stn_target);
						int pID = ((MTreeNode) stn_target_parent.getData()).getNode_ID();

						if (mTreeFavorite.isMenuAvailable(mID, pID, mTreeFavoriteID))
							showDialog();
						else
							insertNodeMenu(mID, pID, stn_target_parent);
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
		FDialog.warn(0, Msg.getMsg(Env.getCtx(),"its.already.there"));
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
		}
		
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
					deleteNodeItem(toNode);
				}
			}
		}

		/**
		 * Delete a Specifically Node From Tree as well as Database
		 * 
		 * @param toNode
		 */
		private void deleteNodeItem(SimpleTreeNode toNode)
		{
			int nodeID = ((MTreeNode) toNode.getData()).getNode_ID();
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
				treeModel.removeNode(toNode);
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
	 * Insert Node into Tree it's contains only Menu type node, Dragged from
	 * Menu Tab.
	 * 
	 * @param menuID
	 * @param parentNodeID
	 * @param stn
	 */
	public void insertNodeMenu(int menuID, int parentNodeID, SimpleTreeNode stn)
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
		int index = 0;
		if (mNode.isSummary())
			index = stn.getChildren().indexOf(node) + 1;
		
		treeModel.addNode(stn, node, index);
		int[] path = treeModel.getPath(treeModel.getRoot(), node);
		Treeitem ti =	tree.renderItemByPath(path);
		tree.setSelectedItem(ti);		
		Events.sendEvent(tree, new Event(Events.ON_SELECT, tree));
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
