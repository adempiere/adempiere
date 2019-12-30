/******************************************************************************
 * Copyright (C) 2012 Carlos Ruiz                                             *
 * Copyright (C) 2012 GlobalQSS - Quality Systems & Solutions                 *
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
package org.adempiere.webui.dashboard;

import java.util.ArrayList;
import java.util.List;

import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.session.SessionManager;
import org.adempiere.webui.theme.ITheme;
import org.adempiere.webui.util.ServerPushTemplate;
import org.compiere.model.MMenu;
import org.compiere.model.MQuery;
import org.compiere.model.MRecentItem;
import org.compiere.model.MTable;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.DropEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Box;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Panelchildren;
import org.zkoss.zul.SimpleTreeModel;
import org.zkoss.zul.SimpleTreeNode;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.TreeitemRenderer;
import org.zkoss.zul.Treerow;
import org.zkoss.zul.Vbox;

/**
 * Dashboard item: Recent Items
 * @author Carlos Ruiz / GlobalQSS
 * @date January 27, 2012
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * 		<a href="https://github.com/adempiere/adempiere/issues/884">
 * 		@see FR [ 884 ] Recent Items in Dashboard (Add new functionality)</a>
 * @author Michael McKay, mckayERP@gmail.com
 * 		<li> <a href="https://github.com/adempiere/adempiere/pull/2390">Pull Request #2390</a> Add a specific icon for smart browse
 * 			Also changed the look and feel to match the menu tree - replacing tool bar buttons with a flat tree.
 */
public class DPRecentItems extends DashboardPanel implements EventListener, TreeitemRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 662950038476166515L;

	public static final String DELETE_RECENTITEMS_DROPPABLE = "deleteRecentItems";
	
	// Messages
	private static final String MSG_RecentItemsTooltip = "@DPRecentItems_RecentItemsTooltip@";
	private static final String MSG_ItemTooltip = "@DPRecentItems_ItemTooltip@";
	private static final String MSG_TrashcanTooltip = "@DPRecentItems_TrashcanTooltip@";
	private static final String MSG_RefreshTooltip = "@DPRecentItems_RefreshToolTip@";

	public Tree				tree = null;
	private SimpleTreeModel	tModel;
	private SimpleTreeNode	mroot = null;

	private Box				bxRecentItems;

	private Toolbarbutton	refreshButton = null;
	private Toolbarbutton	trashCan = null;

	public DPRecentItems()
	{
		super();

		initCoponents();

	}

	private void initCoponents() {
		
		String tooltiptext = Msg.parseTranslation(Env.getCtx(), MSG_RecentItemsTooltip);
		
		this.setTooltiptext(tooltiptext);
		Panel panel = new Panel();
		panel.setTooltiptext(tooltiptext);
		this.appendChild(panel);

		Panelchildren recentItemContent = new Panelchildren();
		panel.appendChild(recentItemContent);

		bxRecentItems = new Vbox();
		bxRecentItems.setWidth("100%");
		bxRecentItems.setHeight("100%");
		bxRecentItems.setTooltiptext(tooltiptext);

		recentItemContent.appendChild(bxRecentItems);

		Toolbar recentItemsToolbar = new Toolbar("horizontal");  // Align start (left) is the default
		recentItemsToolbar.setTooltiptext(tooltiptext);
		recentItemsToolbar.setHeight("18px");
		this.appendChild(recentItemsToolbar);

		trashCan = new Toolbarbutton(null, ITheme.DASHBOARD_DELETE_IMAGE);
		trashCan.setTooltiptext(Msg.parseTranslation(Env.getCtx(), MSG_TrashcanTooltip));
		trashCan.setDroppable(DELETE_RECENTITEMS_DROPPABLE);
		trashCan.addEventListener(Events.ON_DROP, this);
		trashCan.setStyle("margin: 5px");
		recentItemsToolbar.appendChild(trashCan);

		refreshButton = new Toolbarbutton(null, ITheme.DASHBOARD_REFRESH_IMAGE);
		refreshButton.setTooltiptext(Msg.parseTranslation(Env.getCtx(), MSG_RefreshTooltip));
		refreshButton.addEventListener(Events.ON_CLICK, this);
		refreshButton.setStyle("margin: 5px");
		recentItemsToolbar.appendChild(refreshButton);
		//
		refresh();
	}

	/**
	 * Creating Tree structure
	 */
	private void initTree()
	{
		if (tree == null)
		{
			tree = new Tree();
			tree.setMultiple(false);
			tree.setWidth("100%");
			tree.setFixedLayout(false);
			tree.setStyle("border:none");
			tree.setClass("menu-tree");
			tree.setTreeitemRenderer(this);
			bxRecentItems.appendChild(tree);

		}

		tree.clear();
		if (tree.getChildren().size() > 0)
			tree.removeChild((Component) tree.getChildren().get(0));
		
		mroot = new SimpleTreeNode(null, new ArrayList<SimpleTreeNode>());
		tModel = new SimpleTreeModel(mroot);
		tree.setModel(tModel);
		
	}


	/**
	 * After adding a new node to the Tree model, call this method to recreate
	 * the Tree.
	 */
	@SuppressWarnings("unchecked")
	public void refresh()
	{
		
		initTree();

		//	Delete Unnecessary Items
		MRecentItem.deleteExtraItems(Env.getCtx());
		List<MRecentItem> recentItemList = MRecentItem.getFromUserAndRole(Env.getCtx());
		if(recentItemList != null
				&& recentItemList.size() != 0) {
			for (MRecentItem recentItem : recentItemList) {
				String label = recentItem.getLabel();
				if (label == null) {
					recentItem.deleteEx(true);
					continue; // record could have been deleted
				}
				SimpleTreeNode treeNode = new SimpleTreeNode(recentItem, new ArrayList<SimpleTreeNode>());
				((List<SimpleTreeNode>) mroot.getChildren()).add(treeNode);
			}
		}
		
		tree.setModel(tModel);  // Force redraw
	}

	/**
	 * Make any Event Like open Menu Window, On Checked Expand Node, Add Node
	 * into Tree
	 */
	public void onEvent(Event event)
	{
        Component comp = event.getTarget();	
		String eventName = event.getName();

		/**
		 * Click on the menu to open that item
		 */
		if (eventName.equals(Events.ON_CLICK))
		{
			if (comp instanceof Treerow)
			{
				Treerow treerow = (Treerow) comp;
				Treeitem treeitem = (Treeitem) treerow.getParent();
				Object value = treeitem.getValue();

				SimpleTreeNode stn = (SimpleTreeNode) value;
				MRecentItem recentItem = (MRecentItem) stn.getData(); 
            	//	Open
            	if (recentItem != null) {
            		//	Is a window change
            		if(!recentItem.isOptionMenu()) {
            			String TableName = MTable.getTableName(Env.getCtx(), recentItem.getAD_Table_ID());
            			MQuery query = MQuery.getEqualQuery(TableName + "_ID", recentItem.getRecord_ID());
                		SessionManager.getAppDesktop().openWindow(recentItem.getAD_Window_ID(), query);
            		} else {
            			SessionManager.getAppDesktop().onMenuSelected(recentItem.getAD_Menu_ID());
            		}
            	}
			}
		}
		
        if (eventName.equals(Events.ON_CLICK))
        {
            if (comp instanceof Toolbarbutton && comp.equals(refreshButton)) // Refresh button
            {
            	refresh();
            }
        }
        else if (event instanceof DropEvent)
		{
			DropEvent de = (DropEvent) event;
			Treerow tr = (Treerow) de.getDragged();
			
			// src.getValue is the SimpleTreeNode
			Treeitem src = (Treeitem) tr.getParent();  			
			
        	if(comp.equals(trashCan))
        	{
        		
    			SimpleTreeNode sourceNode = (SimpleTreeNode) src.getValue();
    			
				int path[] = tModel.getPath(getRoot(), sourceNode);

				if (path != null && path.length > 0)
				{
					SimpleTreeNode parentNode = (SimpleTreeNode) tModel.getRoot();
					int index = path.length - 1;
					for (int i = 0; i < index; i++)
					{
						parentNode = (SimpleTreeNode) tModel.getChild(parentNode, path[i]);
					}
					parentNode.getChildren().remove(path[index]);
				}

				MRecentItem recentItem = (MRecentItem) sourceNode.getData();
				recentItem.deleteEx(false);
        		
				refresh();
        	}
		}


	}

	@Override
    public void refresh(ServerPushTemplate template)
	{			
    	template.execute(this);
	}

	@Override
	public void updateUI() {
		refresh();
		bxRecentItems.invalidate();
	}

	/* (non-Javadoc)
	 * @see org.zkoss.zul.TreeitemRenderer#render(org.zkoss.zul.Treeitem, java.lang.Object)
	 */
	@Override
	public void render(Treeitem ti, Object node) throws Exception
	{
		SimpleTreeNode stn = (SimpleTreeNode) node;
		MRecentItem recentItem = (MRecentItem) stn.getData();
		String label = recentItem.getLabel();
		String action = "";
		if(recentItem.getAD_Menu_ID() != 0) {
			MMenu menu = MMenu.getFromId(Env.getCtx(), recentItem.getAD_Menu_ID());
			action = menu.getAction();
		}
		String image = AEnv.getMenuIconFile(action);
		Treecell tc = new Treecell(label, image);
		
		Treerow tr = null;
		if (ti.getTreerow() == null)
		{
			tr = new Treerow();
			tr.setParent(ti);
			tr.addEventListener(Events.ON_CLICK, this);
			tr.addEventListener(Events.ON_DOUBLE_CLICK, this);
		}
		else
		{
			tr = ti.getTreerow();
			tr.getChildren().clear();
		}
		tr.setDraggable(DELETE_RECENTITEMS_DROPPABLE);
		tc.setParent(tr);
		ti.setTooltiptext(Msg.parseTranslation(Env.getCtx(), label + "\n\n" + MSG_ItemTooltip));
		ti.setValue(node);

	}

}
