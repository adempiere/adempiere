/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2019 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.										*
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
package org.adempiere.webui.dashboard;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.webui.component.ADTreeFavoriteOnDropListener;
import org.adempiere.webui.component.Checkbox;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.SimpleFavoriteTreeModel;
import org.adempiere.webui.panel.MenuPanel;
import org.adempiere.webui.theme.ITheme;
import org.adempiere.webui.util.TreeItemAction;
import org.adempiere.webui.util.TreeUtils;
import org.compiere.model.MTreeFavorite;
import org.compiere.model.MTreeNode;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.DropEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zul.Box;
import org.zkoss.zul.Div;
import org.zkoss.zul.Image;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menupopup;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Panelchildren;
import org.zkoss.zul.SimpleTreeNode;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Treerow;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.event.TreeDataEvent;
import org.zkoss.zul.event.TreeDataListener;

/**
 * Creates a panel of user favorites.  These are menu items from the menu tree.
 * The user can create favorites by dragging them to this panel and can create
 * folders and organize the tree.
 * 
 * @author jtrinidad Adaxa - source of the original code
 * @author marcalwestf Mario Calderone, Westfalia
 * 	<li><a href="https://github.com/adempiere/adempiere/issues/911">#911 User Favorite Tree Panel</a> - add Adaxa contribution
 * @author Michael McKay, mckayERP@gmail.com
 *   <li><a href="https://github.com/adempiere/adempiere/issues/2324">#2324 User Favorites will not accept entry without folder</a> 
 *
 */
public class DPUserFavorites extends DashboardPanel implements EventListener, TreeDataListener
{
	private static final long		serialVersionUID	= 1L;
	private Box						bxFav;
	private Checkbox				chkExpand;
	public Tree						tree				= null;
	public MTreeFavorite			mTreeFav;
	private int						m_AD_FavTree_ID	= -1;
	private int						AD_Role_ID;
	private int						AD_Client_ID;
	private int						AD_Org_ID;
	private int						AD_User_ID;
	private Div						hint = null;
	private Image					trashCan = null;
	private SimpleFavoriteTreeModel	tModel;

	/**
	 * Standard constructor
	 */
	public DPUserFavorites()
	{
		super();

		AD_Client_ID = Env.getAD_Client_ID(Env.getCtx());
		AD_Role_ID = Env.getAD_Role_ID(Env.getCtx());
		AD_User_ID = Env.getAD_User_ID(Env.getCtx());
		AD_Org_ID = Env.getAD_Org_ID(Env.getCtx());

		initTree();  // Initializes the tree. Important to call this before creating components.
		
		initCoponents(); // Needs the tree - uses the same mouse event listeners
		
	}

	private void initCoponents() {
		
		Panel panel = new Panel();
		this.appendChild(panel);

		Panelchildren favContent = new Panelchildren();
		panel.appendChild(favContent);

		favContent.appendChild(createHint());
		favContent.appendChild(createFavouritesPanel());

		Toolbar favToolbar = new Toolbar();
		

		chkExpand = new Checkbox();
		chkExpand.setText(Msg.getMsg(Env.getCtx(), "ExpandTree"));
		chkExpand.setTooltiptext(Msg.getMsg(Env.getCtx(), "DPUserFavorites.expandTree.tooltip"));
		chkExpand.addEventListener(Events.ON_CHECK, this);
		chkExpand.setStyle("margin-left:10px");
		favToolbar.appendChild(chkExpand);

		trashCan = new Image(ITheme.DASHBOARD_DELETE_IMAGE);
		favToolbar.appendChild(trashCan);
		trashCan.setAlign("left");
		trashCan.setDroppable(SimpleFavoriteTreeModel.USER_FAVORITE_DRAGGABLE_TYPE);
		trashCan.setStyle("margin: 5px;");
		trashCan.setTooltiptext(Msg.getMsg(Env.getCtx(), "DPUserFavorites.trashcan.tooltip"));
		trashCan.addEventListener(Events.ON_DROP, this);

		this.appendChild(favToolbar);

		// Limit the droppable items to main menu items 
		// and items from the SimpleFavoriteTreeModel
		favContent.setDroppable(MenuPanel.MENU_ITEM_DRAGGABLE_TYPE + "," 
						+ SimpleFavoriteTreeModel.USER_FAVORITE_DRAGGABLE_TYPE);
		favContent.addEventListener(Events.ON_DROP, this);
		
		this.setTooltiptext(Msg.getMsg(Env.getCtx(), "DPUserFavorites.tooltip"));

		int childCount = ((SimpleFavoriteTreeModel) tree.getModel()).getRoot().getChildCount();
		if (childCount == 0)  //Root node only. Add a hint to the user.
		{
			showHint();
		}
		else
		{
			hideHint();
		}

		
	}

	/**
	 * Create a hint box to occupy the panel when there are
	 * no user favorites loaded.  This serves as a drop target
	 * for menu items when there are no other menu items or 
	 * folders in the tree. The hint box text gives the user
	 * a visual indication of what to do.
	 * @return
	 */
	private Component createHint() {
		
		hint = new Div();
		hint.setStyle("overflow: hidden; text-align: center");
		hint.setTooltiptext(Msg.getMsg(Env.getCtx(), "DPUserFavorites.tooltip"));
		hint.setDroppable(MenuPanel.MENU_ITEM_DRAGGABLE_TYPE);
		hint.addEventListener(Events.ON_DROP, this);
		hint.addEventListener(Events.ON_RIGHT_CLICK, this);
		
		Label hintLabel = new Label(Msg.getMsg(Env.getCtx(), "DPUserFavorites.hint.text"));
		
		// Ugh! TODO add this to the style sheets
		hintLabel.setStyle("display: block; margin: 2px; padding: 10px; border:1px solid #B1CBD5; color:#888;");
		
		hint.appendChild(hintLabel);
		
		return hint;
		
	}

	/**
	 * Create the favorites panel.  Consists of a Vbox.
	 * @return
	 */
	private Box createFavouritesPanel()
	{
		bxFav = new Vbox();
		bxFav.setWidth("100%");
		bxFav.setHeight("100%");

		bxFav.appendChild(tree);
		return bxFav;
	}

	/**
	 * Hide the hint box and change the alignment of the favorites panel
	 * to "left" for the tree.
	 */
	private void hideHint() {
		if (hint != null)
		{
			bxFav.setAlign("left");
			hint.setVisible(false);
		}
	}

	/**
	 * No tree so make the hint visible and center it in the favorites panel
	 */
	private void showHint() {
		if (hint != null)
		{
			bxFav.setAlign("center");
			hint.setVisible(true);
		}
	}

	/**
	 * Creating Tree structure
	 */
	public void initTree()
	{
		// Not initialized
		if (m_AD_FavTree_ID == -1)
		{
			mTreeFav = new MTreeFavorite(Env.getCtx(), 0, null);
			m_AD_FavTree_ID = mTreeFav.getTreeID(AD_Role_ID, AD_User_ID, AD_Client_ID);
		}

		// Not found
		if (m_AD_FavTree_ID == -1)
		{
			mTreeFav.set_ValueOfColumn(MTreeFavorite.COLUMNNAME_AD_Client_ID, AD_Client_ID);
			mTreeFav.setAD_Org_ID(AD_Org_ID);
			mTreeFav.setAD_Role_ID(AD_Role_ID);
			mTreeFav.setAD_User_ID(AD_User_ID);

			if (!mTreeFav.save())
				throw new AdempiereException("Could not create Tree.");
			m_AD_FavTree_ID = mTreeFav.getAD_Tree_Favorite_ID();
		}

		if (tree == null)
		{
			tree = new Tree();
			tree.setMultiple(false);
			tree.setWidth("100%");
			tree.setFixedLayout(false);
			tree.setStyle("border:none");
			tree.setClass("menu-tree");
		}

		tModel = SimpleFavoriteTreeModel.initADTree(tree, m_AD_FavTree_ID, 0);
		tModel.addTreeDataListener(this);
				
		if (tree.getTreechildren() != null)
		{
			TreeUtils.traverse(tree.getTreechildren(), new TreeItemAction() {

				public void run(Treeitem treeItem)
				{
					SimpleTreeNode simpleTreeNode = (SimpleTreeNode) treeItem.getValue();
					MTreeNode mtn = (MTreeNode) simpleTreeNode.getData();
					if (mtn.IsCollapsible())
						treeItem.setOpen(false);
					else
						treeItem.setOpen(true);
				} // run
			});

		}
	}

	/**
	 * After adding a new node to the Tree model, call this method to recreate
	 * the Tree.
	 */
	public void reInitTree()
	{
		tree.clear();
		if (tree.getChildren().size() > 0)
			tree.removeChild((Component) tree.getChildren().get(0));
		initTree();
	}

	/**
	 * Make any Event Like open Menu Window, On Checked Expand Node, Add Node
	 * into Tree
	 */
	public void onEvent(Event event)
	{
        Component comp = event.getTarget();	
		String eventName = event.getName();

		if (eventName.equals(Events.ON_CHECK) && event.getTarget() == chkExpand)
		{
			expandOnCheck();
		}

		if (event instanceof MouseEvent && eventName.equals(Events.ON_RIGHT_CLICK))
		{
			if (comp.equals(hint))
			{
				Menupopup popup = new Menupopup();		

				Menuitem menuItem = new Menuitem(Msg.getMsg(Env.getCtx(), ADTreeFavoriteOnDropListener.MENU_ITEM_ADD_FOLDER), "/images/dark/FolderAdd16.png");
				menuItem.setValue(ADTreeFavoriteOnDropListener.MENU_ITEM_ADD_FOLDER);
				menuItem.setParent(popup);
				menuItem.addEventListener(Events.ON_CLICK, SimpleFavoriteTreeModel.listener.new AddFolderListener(tModel.getRoot()));

				popup.setPage(hint.getPage());
				popup.open(hint, "after_pointer");				
			}
		}
				

		if (event instanceof DropEvent)
		{
			DropEvent de = (DropEvent) event;
			Treerow tr = (Treerow) de.getDragged();
			
			// From the Main Menu. src.getValue is the ID.
			// From the UserFavorites, src.getValue is the SimpleTreeNode
			Treeitem src = (Treeitem) tr.getParent();  			
			
			if (comp.equals(hint))
			{
				
				if (de.getDragged() != de.getTarget())
				{
					int sourceID = Integer.valueOf((String) src.getValue());
					ADTreeFavoriteOnDropListener.insertNodeMenu(sourceID, 0, null, 0);
				}

			}
        	else if(comp.equals(trashCan))
        	{
        		
    			SimpleTreeNode sourceNode = (SimpleTreeNode) src.getValue();
				ADTreeFavoriteOnDropListener.deleteNodeMenu(sourceNode);
        		
        	}
		}
	}
	
	/**
	 * Expand All Node
	 */
	public void expandAll()
	{
		if (!chkExpand.isChecked())
			chkExpand.setChecked(true);
		if (!tree.getChildren().isEmpty())
			TreeUtils.expandAll(tree);
	}

	/**
	 * Collapse all nodes
	 */
	public void collapseAll()
	{
		if (chkExpand.isChecked())
			chkExpand.setChecked(false);
		if (!tree.getChildren().isEmpty())
			TreeUtils.collapseAll(tree);
	}

	/**
	 * On check event for the expand check box
	 */
	private void expandOnCheck()
	{
		if (chkExpand.isChecked())
			expandAll();
		else
			collapseAll();
	}

	@Override
	public void onChange(TreeDataEvent event) 
	{
		if (event.getType() == TreeDataEvent.INTERVAL_ADDED 
			 || event.getType() == TreeDataEvent.INTERVAL_REMOVED)
		{
			int childCount = ((SimpleFavoriteTreeModel) tree.getModel()).getRoot().getChildCount();
			if (childCount == 0)  
			{
				// The tree is empty and won't occupy screen space so it can't serve as a drop target.
				// Make the hint box visible to provide a drop target and give the user a text
				// hint as to what they can do.
				showHint();
			}
			else
			{
				hideHint();
			}
		}
		
	}
}