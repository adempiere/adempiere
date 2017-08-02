package org.adempiere.webui.dashboard;

import java.util.ArrayList;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.webui.component.Checkbox;
import org.adempiere.webui.component.SimpleFavoriteTreeModel;
import org.adempiere.webui.util.TreeItemAction;
import org.adempiere.webui.util.TreeUtils;
import org.adempiere.webui.window.FDialog;
import org.compiere.model.MTreeFavorite;
import org.compiere.model.MTreeFavoriteNode;
import org.compiere.model.MTreeNode;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Box;
import org.zkoss.zul.Button;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Panelchildren;
import org.zkoss.zul.SimpleTreeNode;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Vbox;

public class DPUserFavorites extends DashboardPanel implements EventListener
{
	private static final long		serialVersionUID	= 1L;
	public static final String		FAVOURITE_DROPPABLE	= "favourite";
	private Box						bxFav;
	private Checkbox				chkExpand;
	private Checkbox				addAsRoot;
	private Textbox					textbox;
	public Tree						tree				= null;
	public MTreeFavorite			mTreeFav;
	private int						m_AD_FavTree_ID;
	private int						AD_Role_ID;
	private int						AD_Client_ID;
	private int						AD_Org_ID;
	private int						AD_User_ID;
	private SimpleFavoriteTreeModel	tModel;

	public DPUserFavorites()
	{
		super();

		AD_Client_ID = Env.getAD_Client_ID(Env.getCtx());
		AD_Role_ID = Env.getAD_Role_ID(Env.getCtx());
		AD_User_ID = Env.getAD_User_ID(Env.getCtx());
		AD_Org_ID = Env.getAD_Org_ID(Env.getCtx());

		Panel panel = new Panel();
		this.appendChild(panel);

		Panelchildren favContent = new Panelchildren();
		panel.appendChild(favContent);

		favContent.appendChild(createFavouritesPanel());

		Toolbar favToolbar = new Toolbar();

		chkExpand = new Checkbox();
		chkExpand.setText(Msg.getMsg(Env.getCtx(), "ExpandTree"));
		chkExpand.addEventListener(Events.ON_CHECK, this);
		chkExpand.setStyle("margin-left:10px");
		favToolbar.appendChild(chkExpand);

		addAsRoot = new Checkbox();
		addAsRoot.setText(Msg.getMsg(Env.getCtx(), "add.as.root"));
		addAsRoot.setStyle("margin-left:10px");
		favToolbar.appendChild(addAsRoot);

		textbox = new Textbox();
		textbox.setName("TreeNode");
		textbox.setStyle("margin-left:10px");
		textbox.addEventListener(Events.ON_OK, this);
		favToolbar.appendChild(textbox);

		Button btn_add = new Button(Msg.getMsg(Env.getCtx(), "add.folder"));
		btn_add.addEventListener(Events.ON_CLICK, this);
		btn_add.setStyle("margin-left:10px");
		favToolbar.appendChild(btn_add);

		this.appendChild(favToolbar);

		favContent.setDroppable(FAVOURITE_DROPPABLE);
		favContent.addEventListener(Events.ON_DROP, this);
	}

	private Box createFavouritesPanel()
	{
		bxFav = new Vbox();
		bxFav.setWidth("100%");
		bxFav.setHeight("100%");

		tree = new Tree();
		tree.setMultiple(false);
		tree.setWidth("100%");
		tree.setFixedLayout(false);
		tree.setStyle("border:none");
		tree.setClass("menu-tree");

		mTreeFav = new MTreeFavorite(Env.getCtx(), 0, null);
		int AD_FavTree_ID = mTreeFav.getTreeID(AD_Role_ID, AD_User_ID, AD_Client_ID);

		if (AD_FavTree_ID == -1)
		{
			mTreeFav.set_ValueOfColumn(MTreeFavorite.COLUMNNAME_AD_Client_ID, AD_Client_ID);
			mTreeFav.setAD_Org_ID(AD_Org_ID);
			mTreeFav.setAD_Role_ID(AD_Role_ID);
			mTreeFav.setAD_User_ID(AD_User_ID);

			if (!mTreeFav.save())
				throw new AdempiereException("Could not create Tree.");
			m_AD_FavTree_ID = mTreeFav.getAD_Tree_Favorite_ID();
		}
		else
		{
			m_AD_FavTree_ID = AD_FavTree_ID;
		}

		initTree();
		bxFav.appendChild(tree);
		return bxFav;
	}

	/**
	 * Creating Tree structure
	 */
	public void initTree()
	{
		tModel = SimpleFavoriteTreeModel.initADTree(tree, m_AD_FavTree_ID, 0);

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
	 * When Adding a New Node into Tree Then after call this method for ReCreate
	 * Tree.
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

		if (eventName.equals(Events.ON_CLICK))
		{
			if (comp instanceof Button)
				addNodeBtnPressed();
		}
		else if (eventName.equals(Events.ON_OK))
		{
			addNodeBtnPressed();
		}
		else if (eventName.equals(Events.ON_CHECK) && event.getTarget() == chkExpand)
		{
			expandOnCheck();
		}
	}

	/**
	 * When Button Or Enter Key Pressed Add Node Into Tree.
	 */
	private void addNodeBtnPressed()
	{
		String nodeName = textbox.getText().toString();
		if (nodeName.isEmpty())
			textbox.setFocus(true);
		else
			insertNode(nodeName);
	}

	/**
	 * Insert Folder as Node in Tree on Button clicked event then after call
	 * this method
	 */
	private void insertNode(String nodeName)
	{
		MTreeFavoriteNode mTreeFavoriteNode = new MTreeFavoriteNode(Env.getCtx(), 0, null);
		mTreeFavoriteNode.set_ValueOfColumn(MTreeFavoriteNode.COLUMNNAME_AD_Client_ID, AD_Client_ID);
		mTreeFavoriteNode.setAD_Org_ID(AD_Org_ID);
		mTreeFavoriteNode.setAD_Tree_Favorite_ID(m_AD_FavTree_ID);
		mTreeFavoriteNode.setIsSummary(true);
		mTreeFavoriteNode.setNodeName(nodeName);
		if (addAsRoot.isChecked())
			mTreeFavoriteNode.setParent_ID(0);
		else
			mTreeFavoriteNode.setParent_ID(SimpleFavoriteTreeModel.getSelectedFolderID());
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
			SimpleTreeNode parentNode = tModel.find(null, mtnNew.getParent_ID());

			try
			{
				tModel.addNode(parentNode, newNode, 0);
				int[] path = tModel.getPath(tModel.getRoot(), newNode);
				Treeitem ti = tree.renderItemByPath(path);
				tree.setSelectedItem(ti);
				Events.sendEvent(tree, new Event(Events.ON_SELECT, tree));
				textbox.setText("");
			}
			catch (Exception e)
			{
				FDialog.warn(0, Msg.getMsg(Env.getCtx(), "SelectMenuItem"));
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
	 * collapse all node
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
}