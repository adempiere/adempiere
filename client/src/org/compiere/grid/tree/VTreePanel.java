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


import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.DropMode;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;
import javax.swing.UIManager;
import javax.swing.plaf.SplitPaneUI;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import javax.swing.tree.DefaultTreeSelectionModel;
import javax.swing.tree.TreePath;

import org.adempiere.exceptions.DBException;
import org.adempiere.plaf.AdempierePLAF;
import org.adempiere.plaf.AdempiereTaskPaneUI;
import org.compiere.apps.ADialog;
import org.compiere.apps.AEnv;
import org.compiere.apps.AMenu;
import org.compiere.apps.AMenuStartItem;
import org.compiere.apps.ConfirmPanel;
import org.compiere.model.MMenu;
import org.compiere.model.MQuery;
import org.compiere.model.MRecentItem;
import org.compiere.model.MTable;
import org.compiere.model.MTree;
import org.compiere.model.MTreeNode;
import org.compiere.swing.CButton;
import org.compiere.swing.CCheckBox;
import org.compiere.swing.CFrame;
import org.compiere.swing.CLabel;
import org.compiere.swing.CMenuItem;
import org.compiere.swing.CPanel;
import org.compiere.swing.CTextField;
import org.compiere.swing.ShadowBorder;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Util;
import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.JXTaskPaneContainer;

import de.schaeffer.compiere.tools.DocumentSearch;

/**
 *  Tree Panel displays trees.
 *  <br>
 *	When a node is selected by Left Click, a propertyChange (NODE_SELECTION) event is fired
 *  <pre>
 *		PropertyChangeListener -
 *			treePanel.addPropertyChangeListener(VTreePanel.NODE_SELECTION, this);
 *			calls: public void propertyChange(PropertyChangeEvent e)
 *  </pre>
 *  To select a specific node call
 *      setSelectedNode(NodeID);
 *
 *  @author 	Jorg Janke
 *  @version 	$Id: VTreePanel.java,v 1.3 2006/07/30 00:51:28 jjanke Exp $
 *
 * @author kthiemann / Carlos Ruiz
 * 			<li>2761420 - Advanced Search
 *  
 * @author Teo Sarca
 *   		<li>BF [ 2866493 ] VTreePanel is not saving who did the node move
 *			https://sourceforge.net/tracker/?func=detail&atid=879332&aid=2866493&group_id=176962
 *
 * @author Paul Bowden
 *          <li>FR [ 2032092 ] Java 6 improvements to tree drag and drop
 *          https://sourceforge.net/tracker/index.php?func=detail&aid=2032092&group_id=176962&atid=879335
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * 		<a href="https://github.com/adempiere/adempiere/issues/884">
 * 		@see FR [ 884 ] Recent Items in Dashboard (Add new functionality)</a>
 */
public final class VTreePanel extends CPanel
	implements ActionListener
{
/******************************************************************************
	 *  Mouse Clicked
	 */
	class VTreePanel_mouseAdapter extends java.awt.event.MouseAdapter
	{
		VTreePanel m_adaptee;

		/**
		 * 	VTreePanel_mouseAdapter
		 *	@param adaptee
		 */
		VTreePanel_mouseAdapter(VTreePanel adaptee)
		{
			m_adaptee = adaptee;
		}

		/**
		 *	Mouse Clicked
		 *	@param e
		 */
		public void mouseClicked(MouseEvent e)
		{
			m_adaptee.mouseClicked(e);
		}


	}   //  VTreePanel_mouseAdapter

	/**
	 *  Key Pressed
	 */
	class VTreePanel_keyAdapter extends java.awt.event.KeyAdapter
	{
		VTreePanel m_adaptee;

		/**
		 * 	VTreePanel_keyAdapter
		 *	@param adaptee
		 */
		VTreePanel_keyAdapter(VTreePanel adaptee)
		{
			m_adaptee = adaptee;
		}

		/**
		 * 	Key Pressed
		 *	@param e
		 */
		public void keyPressed(KeyEvent e)
		{
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
				m_adaptee.keyPressed(e);
		}
	}   //  VTreePanel_keyAdapter
	
	
	/**
	 *
	 */
	private static final long serialVersionUID = -6798614427038652192L;

	private static final String PREFIX_DOCUMENT_SEARCH = "/";
	private static final String PREFIX_RECENT_ITEM = "RecentItem_";
	private static final String PREFIX_BAR_ITEM = "ItemBar_";

	protected boolean lookAndFeelChanged = false;

	private VTreeTransferHandler handler = new VTreeTransferHandler();

	/**
	 *  Tree Panel for browsing and editing of a tree.
	 *  Need to call initTree
	 *  @param  windowNo	WindowNo
	 *  @param  editable    if true you can edit it
	 *  @param  hasBar      has OutlookBar
	 */
	public VTreePanel(int windowNo, boolean hasBar, boolean editable) {
		super();
		toolbarList = new ArrayList<JToolBar>();
		log.config("Bar=" + hasBar + ", Editable=" + editable);
		this.hasBar = hasBar;
		this.editable = editable;
		this.windowNo = windowNo;

		//	static init
		jbInit();
		if (!hasBar)
		{
			barScrollPane.setPreferredSize(new Dimension(0,0));
			barScrollPane.setMaximumSize(new Dimension(0,0));
			barScrollPane.setMinimumSize(new Dimension(0,0));
			//Begin - [FR 1953769]
			bar.setBackground(AdempierePLAF.getFormBackground());
			//End - [FR 1953769]
			centerSplitPane.setDividerLocation(0);
			centerSplitPane.setDividerSize(0);
			popMenuTree.remove(barAdd);
		}
		else {
			centerSplitPane.setDividerLocation(80);
			UIManager.addPropertyChangeListener(new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent evt) {
					if ("lookAndFeel".equals(evt.getPropertyName()))
						lookAndFeelChanged = true;
				}
				
			});
		}
		//  base settings
		if (editable) {
			tree.setDragEnabled(true);
			tree.setTransferHandler(handler);
			tree.setDropMode(DropMode.INSERT);
			setMappings(tree);
		}
		else
		{
			popMenuTree.remove(mFrom);
			popMenuTree.remove(mTo);
		}
	}   //  VTreePanel

	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(VTreePanel.class);

	private BorderLayout mainLayout = new BorderLayout();
	private JTree tree = new JTree();
	private AdempiereTreeModel treeModel;
	private DefaultTreeSelectionModel treeSelect = new DefaultTreeSelectionModel();
	private CPanel southPanel = new CPanel();
	private CCheckBox treeExpand = new CCheckBox();
	private CTextField treeSearch = new CTextField(10);
	private CLabel treeSearchLabel = new CLabel();
	private JPopupMenu popMenuTree = new JPopupMenu();
	private JPopupMenu popMenuBar = new JPopupMenu();
	private JPopupMenu popToolBar = new JPopupMenu();
	private CMenuItem mFrom = new CMenuItem();
	private CMenuItem mTo = new CMenuItem();
	private JXTaskPaneContainer bar = new JXTaskPaneContainer();
	private List<JToolBar> toolbarList;
	private HashMap<Integer, JToolBar> toolbarMap;
	private CMenuItem barAdd = new CMenuItem();
	private CMenuItem refreshBar = new CMenuItem();
	private CMenuItem barRemove = new CMenuItem();
	private BorderLayout southLayout = new BorderLayout();
	private JSplitPane centerSplitPane = new JSplitPane();
	private JScrollPane treePane = new JScrollPane();
	private MouseListener mouseListener = new VTreePanel_mouseAdapter(this);
	private KeyListener keyListener = new VTreePanel_keyAdapter(this);
	
	/** Tree ID                     */
	private int			treeId = 0;
	/** Tree is editable (can move nodes) - also not active shown   */
	private boolean     editable;
	/** Tree has a shortcut Bar     */
	private boolean     hasBar;
	/** The root node               */
	private MTreeNode  	root = null;
	/**	Window No	*/
	private int 		windowNo = 0;


	private String      m_search = "";
	private Enumeration<?> m_nodeEn;
	private MTreeNode   m_selectedNode;	//	the selected model node
	private CButton     buttonSelected;
	private JToolBar	toolSelected;
	private JScrollPane barScrollPane;

	/**	Property Listener NodeSelected	by Left Click		*/
	public static final String NODE_SELECTION = "NodeSelected";

	/**
	 * Int tree without Where Clause
	 * @param AD_Tree_ID
	 * @return
	 */
	public boolean initTree (int AD_Tree_ID) {
		return initTree(AD_Tree_ID, null);
	}
	
	/**
	 *  Tree initialization.
	 * 	May be called several times
	 *	@param	treeId	tree to load
	 *  @return true if loaded ok
	 */
	public boolean initTree (int treeId, String whereClause) {
		log.config("AD_Tree_ID=" + treeId);
		//	Yamel Senih [ 9223372036854775807 ]
		//	Add Where Clause
		if(!Util.isEmpty(whereClause)) {
			whereClause = Env.parseContext(Env.getCtx(), windowNo, whereClause, false, false);
		}
		//  Get Tree
		MTree vTree = new MTree (Env.getCtx(), treeId, editable, false, whereClause, null);
		//	End Yamel Senih
		root = vTree.getRoot();
		root.setName(Msg.getMsg(Env.getCtx(), vTree.getName()).replace("&" , "")); // translate name of menu.
		// m_root.setName(Msg.getMsg(Env.getCtx(), "Menu") ); // @Trifon; this is the hardcoded way.

		log.config("root=" + root);
		treeModel = new AdempiereTreeModel(root, true);
		treeModel.setMTree(vTree);
		tree.setModel(treeModel);

		//  Shortcut Bar
		if (hasBar) {
			for (JToolBar currentToolbar : toolbarList) {
				currentToolbar.removeAll();
			}
			//	Temp
			JToolBar newToolbar = null;	
			Map<JToolBar,String> titleMap = new HashMap<JToolBar, String>();
			//	
			toolbarMap = new HashMap<Integer, JToolBar>();
			boolean first = true;
			//	Delete unnecessary items
			MRecentItem.deleteExtraItems(Env.getCtx());
			List<MRecentItem> recentItemList = MRecentItem.getFromUserAndRole(Env.getCtx());
			if(recentItemList != null
					&& recentItemList.size() != 0) {
				//	Add Recent Items
				for(MRecentItem recentItem : recentItemList) {
					if (first) {
						newToolbar = new JToolBar(JToolBar.VERTICAL);
						MTable table = MTable.get(Env.getCtx(), MRecentItem.Table_ID);
						titleMap.put(newToolbar, table.get_Translation(MTable.COLUMNNAME_Name));
						toolbarMap.put(recentItem.getAD_RecentItem_ID(), newToolbar);
						newToolbar.setName(PREFIX_RECENT_ITEM + recentItem.getAD_RecentItem_ID());
						first = false;
					}
					//	
					addToBar(recentItem, newToolbar, false);
				}
				//	Add to Tool bar
				if (newToolbar!=null) {
					newToolbar.addMouseListener(mouseListener);
					toolbarList.add(newToolbar);
				}
			}
			newToolbar=null;
			//	
			Enumeration<?> enTop = root.children();
			while (enTop.hasMoreElements()) {
				MTreeNode ndTop = (MTreeNode)enTop.nextElement();
				Enumeration<?> en = ndTop.preorderEnumeration();
				boolean labelDrawn=false;
				while (en.hasMoreElements())
				{
					MTreeNode item = (MTreeNode)en.nextElement();
					if (item.isOnBar()) {
						if (!labelDrawn) {
							newToolbar = new JToolBar(JToolBar.VERTICAL);
							titleMap.put(newToolbar, ndTop.toString().trim());
							labelDrawn = true;
							toolbarMap.put(ndTop.getNode_ID(), newToolbar);
							newToolbar.setName(PREFIX_BAR_ITEM + ndTop.getNode_ID());
						}
						addToBar(item, newToolbar, false);
					}
				}
				if (newToolbar!=null) {
					newToolbar.addMouseListener(mouseListener);
					toolbarList.add(newToolbar);
				}
				newToolbar=null;
			}
			//jbInit();
			for (JToolBar toolbarBorder : toolbarList) {
				toolbarBorder.setOpaque(false);
				//jt2.setLayout(new GridBagLayout());
				toolbarBorder.setFloatable(false);
				toolbarBorder.setRollover(true);
				toolbarBorder.setBorder(BorderFactory.createEmptyBorder());
				
				JXTaskPane barPart = new JXTaskPane();
				//Begin - [FR 1953769]
				barPart.setUI(new AdempiereTaskPaneUI());
				barPart.getContentPane().setBackground(AdempierePLAF.getFormBackground());
				//End - [FR 1953769]
				barPart.setAnimated(true);
				barPart.setLayout(new BorderLayout());
				barPart.add(toolbarBorder, BorderLayout.NORTH);
				barPart.setTitle(titleMap.get(toolbarBorder));
				bar.add(barPart);
				//Begin - [FR 1953769]
				bar.setBackground(AdempierePLAF.getFormBackground());
				//End - [FR 1953769]
			}
			bar.validate();
			bar.repaint();
		}
		return true;
	}   //  initTree
	
	/**
	 *  Static Component initialization.
	 *  <pre>
	 *  - centerSplitPane
	 *      - treePane
	 *          - tree
	 *      - bar
	 *  - southPanel
	 *  </pre>
	 */
	private void jbInit()
	{
		this.setLayout(mainLayout);
		mainLayout.setVgap(5);
		//
		//  only one node to be selected
		treeSelect.setSelectionMode(DefaultTreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.setSelectionModel(treeSelect);
		//
		tree.setEditable(false);		            //	allows to change the text
		tree.addMouseListener(mouseListener);
		tree.addKeyListener(keyListener);
		tree.setCellRenderer(new VTreeCellRenderer());
		treePane.getViewport().add(tree, null);
		treePane.setBorder(new ShadowBorder());
		tree.setBorder(BorderFactory.createEmptyBorder());
		
		CPanel treePart = new CPanel();
		treePart.setLayout(new BorderLayout());
		treePart.add(treePane, BorderLayout.CENTER);
		treePart.setBorder(BorderFactory.createEmptyBorder());
		
		//
		treeExpand.setText(Msg.getMsg(Env.getCtx(), "ExpandTree"));
		treeExpand.setActionCommand("Expand");
		treeExpand.addMouseListener(mouseListener);
		treeExpand.addActionListener(this);
		//
		treeSearchLabel.setText(Msg.getMsg(Env.getCtx(), "TreeSearch") + " ");
		treeSearchLabel.setLabelFor(treeSearch);
		treeSearchLabel.setToolTipText(Msg.getMsg(Env.getCtx(), "TreeSearchText"));

		treeSearch.setBackground(AdempierePLAF.getInfoBackground());
		treeSearch.addKeyListener(keyListener);
		southPanel.setLayout(southLayout);
		southPanel.add(treeExpand, BorderLayout.WEST);
		southPanel.add(treeSearchLabel, BorderLayout.CENTER);
		southPanel.add(treeSearch, BorderLayout.EAST);
		treePart.add(southPanel, BorderLayout.SOUTH);
		//
		centerSplitPane.setOpaque(false);
		barScrollPane = new JScrollPane();
		barScrollPane.getViewport().add(bar);
		centerSplitPane.add(barScrollPane, JSplitPane.LEFT); //hengsin, jxtaskpane
		centerSplitPane.add(treePart, JSplitPane.RIGHT);
		centerSplitPane.setBorder(BorderFactory.createEmptyBorder());
		removeSplitPaneBorder();

		this.add(centerSplitPane, BorderLayout.CENTER);
		
		//
		mFrom.setText(Msg.getMsg(Env.getCtx(), "ItemMove"));
		mFrom.setActionCommand((String)TransferHandler.getCutAction().
	             getValue(Action.NAME));
		mFrom.addActionListener(this);
		mFrom.setAccelerator(
			      KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		mTo.setText(Msg.getMsg(Env.getCtx(), "ItemInsert"));
		mTo.setActionCommand((String)TransferHandler.getPasteAction().
	             getValue(Action.NAME));
		mTo.setAccelerator(
			      KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
		mTo.setMnemonic(KeyEvent.VK_V);
		mTo.addActionListener(this);
		
		barAdd.setText(Msg.getMsg(Env.getCtx(), "BarAdd"));
		barAdd.setActionCommand("BarAdd");
		barAdd.setIcon(Env.getImageIcon2(ConfirmPanel.A_NEW + "16"));
		barAdd.addActionListener(this);
		refreshBar.setText(Msg.getMsg(Env.getCtx(), "BarRefresh"));
		refreshBar.setActionCommand("BarRefresh");
		refreshBar.setIcon(Env.getImageIcon2(ConfirmPanel.A_REFRESH + "16"));
		refreshBar.addActionListener(this);
		barRemove.setText(Msg.getMsg(Env.getCtx(), "BarRemove"));
		barRemove.setActionCommand("BarRemove");
		barRemove.setIcon(Env.getImageIcon2(ConfirmPanel.A_DELETE + "16"));
		barRemove.addActionListener(this);
		//
		popMenuTree.setLightWeightPopupEnabled(false);
		popMenuTree.add(barAdd);
		popMenuTree.add(mFrom);
		if(!hasBar){
			popMenuTree.addSeparator();
		}
		popMenuTree.add(mTo);
		popMenuBar.setLightWeightPopupEnabled(false);
		popMenuBar.add(barRemove);
		//	For ToolBar
		popToolBar.setLightWeightPopupEnabled(false);
		popToolBar.add(refreshBar);
	}   //  jbInit


	private void removeSplitPaneBorder() {
		if (centerSplitPane != null) {
			SplitPaneUI splitPaneUI = centerSplitPane.getUI();
	        if (splitPaneUI instanceof BasicSplitPaneUI) {
	            BasicSplitPaneUI basicUI = (BasicSplitPaneUI) splitPaneUI;
	            basicUI.getDivider().setBorder(BorderFactory.createEmptyBorder());
	        }
		}
	}
	
	/**
	 * 	Set Divider Location
	 *	@param location location (80 default)
	 */
	public void setDividerLocation(int location)
	{
		centerSplitPane.setDividerLocation(location);
	}	//	setDividerLocation
	
	/**
	 * 	Get Divider Location
	 *	@return divider location
	 */
	public int getDividerLocation()
	{
		return centerSplitPane.getDividerLocation();
	}	//	getDividerLocation
	

	/**
	 *  Enter Key
	 *  @param e event
	 */
	protected void keyPressed(KeyEvent e)
	{

		//CHANGED - document search
		if (e.getSource() == treeSearch && treeSearch.getText() != null
				&& treeSearch.getText().length() > 0
				&& treeSearch.getText().substring(0, 1).equals(PREFIX_DOCUMENT_SEARCH)) {
			setBusy(true);
			DocumentSearch search = new DocumentSearch();
			if (search.openDocumentsByDocumentNo(treeSearch.getText().substring(1)))
				treeSearch.setText(null);
			setBusy(false);
			return;
		}

		//  *** Tree ***
		if (e.getSource() instanceof JTree
			|| (e.getSource() == treeSearch && e.getModifiers() != 0))	//	InputEvent.CTRL_MASK
		{
			TreePath tp = tree.getSelectionPath();
			if (tp == null)
				ADialog.beep();
			else
			{
				MTreeNode tn = (MTreeNode)tp.getLastPathComponent();
				setSelectedNode(tn);
			}
		}

		//  *** treeSearch ***
		else if (e.getSource() == treeSearch)
		{
			String search = treeSearch.getText();
			boolean found = false;

			//  at the end - try from top
			if (m_nodeEn != null && !m_nodeEn.hasMoreElements())
				m_search = "";

			//  this is the first time
			if (!search.equals(m_search))
			{
				//  get enumeration of all nodes
				m_nodeEn = root.preorderEnumeration();
				m_search = search;
			}

			//  search the nodes
			while(!found && m_nodeEn != null && m_nodeEn.hasMoreElements())
			{
				MTreeNode nd = (MTreeNode)m_nodeEn.nextElement();
				//	compare in upper case
				if (nd.toString().toUpperCase().indexOf(search.toUpperCase()) != -1)
				{
					found = true;
					TreePath treePath = new TreePath(nd.getPath());
					tree.setSelectionPath(treePath);
					tree.makeVisible(treePath);			//	expand it
					tree.scrollPathToVisible(treePath);
				}
			}
			if (!found)
				ADialog.beep();
		}   //  treeSearch

	}   //  keyPressed


	/*************************************************************************/

	/**
	 *  Mouse clicked
	 *  @param e event
	 */
	protected void mouseClicked(MouseEvent e)
	{
		//  *** JTree ***
		if (e.getSource() instanceof JTree)
		{
			//  Left Double Click
			if (SwingUtilities.isLeftMouseButton(e)
				&& e.getClickCount() > 0)
			{
				int selRow = tree.getRowForLocation(e.getX(), e.getY());
				if(selRow != -1)
				{
					MTreeNode tn = (MTreeNode)tree.getPathForLocation
						(e.getX(), e.getY()).getLastPathComponent();
					setSelectedNode(tn);
				}
			}

			//  Right Click for PopUp
			else if ((editable || hasBar)
				&& SwingUtilities.isRightMouseButton(e) )
			{
				int selRow = tree.getRowForLocation(e.getX(), e.getY());
				if(selRow != -1)
				{
					tree.setSelectionRow(selRow);
				}
				if (tree.getSelectionPath() != null)         //  need select first
				{
					MTreeNode nd = (MTreeNode)tree.getSelectionPath().getLastPathComponent();
					if (nd.isLeaf())                    //  only add leaves to bar
						barAdd.setEnabled(true);
					else
						barAdd.setEnabled(false);
					Rectangle r = tree.getPathBounds(tree.getSelectionPath());
					popMenuTree.show(tree, (int)r.getMaxX(), (int)r.getY());
				}
			}
		}   //  JTree

		//  *** JButton ***
		else if (e.getSource() instanceof JButton)
		{
			if (SwingUtilities.isRightMouseButton(e))
			{
				buttonSelected = (CButton) e.getSource();
				popMenuBar.show(buttonSelected, e.getX(), e.getY());
			}
		}   //  JToolBar
		else if(e.getSource() instanceof JToolBar) {
			if(SwingUtilities.isRightMouseButton(e)) {
				toolSelected = (JToolBar) e.getSource();
				popToolBar.show(toolSelected, e.getX(), e.getY());
			}
		}

	}   //  mouseClicked


	/**
	 *  Get currently selected node
	 *  @return MTreeNode
	 */
	public MTreeNode getSelectedNode()
	{
		return m_selectedNode;
	}   //  getSelectedNode

	/**
	 *  Search Field
	 *  @return Search Field
	 */
	public JComponent getSearchField()
	{
		return treeSearch;
	}   //  getSearchField

	/**
	 *  Set Selection to Node in Event
	 *  @param nodeID Node ID
	 * 	@return true if selected
	 */
	public boolean setSelectedNode (int nodeID)
	{
		log.config("ID=" + nodeID);
		if (nodeID != -1)				//	new is -1
			return selectId(nodeID, true);     //  show selection
		return false;
	}   //  setSelectedNode

	/**
	 *  Select ID in Tree
	 *  @param nodeID	Node ID
	 *  @param show	scroll to node
	 * 	@return true if selected
	 */
	private boolean selectId (int nodeID, boolean show)
	{
		if (root == null)
			return false;
		log.config("NodeID=" + nodeID 
			+ ", Show=" + show + ", root=" + root);
		//  try to find the node
		MTreeNode node = root.findNode (nodeID);
		if (node != null)
		{
			TreePath treePath = new TreePath(node.getPath());
			log.config("Node=" + node 
				+ ", Path=" + treePath.toString());
			tree.setSelectionPath(treePath);
			if (show)
			{
				tree.makeVisible(treePath);       	//	expand it
				tree.scrollPathToVisible(treePath);
			}
			return true;
		}
		log.info("Node not found; ID=" + nodeID);
		return false;
	}   //  selectID


	/**
	 *  Set the selected node & initiate all listeners
	 *  @param nd node
	 */
	private void setSelectedNode (MTreeNode nd)
	{
		log.config("Node = " + nd);
		m_selectedNode = nd;
		//
		firePropertyChange(NODE_SELECTION, null, nd);
	}   //  setSelectedNode

	
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
		log.config("Save=" + save + ", KeyID=" + keyID
			+ ", Name=" + name + ", Description=" + description 
			+ ", IsSummary=" + isSummary + ", ImageInd=" + imageIndicator
			+ ", root=" + root);
		//	if ID==0=root - don't update it
		if (keyID == 0)
			return;	
			
		//  try to find the node
		MTreeNode node = root.findNode(keyID);

		//  Node not found and saved -> new
		if (node == null && save)
		{
			node = new MTreeNode (keyID, 0, name, description,
				root.getNode_ID(), isSummary, imageIndicator, false, null);
			root.add (node);
		}

		//  Node found and saved -> change
		else if (node != null && save)
		{
			node.setName (name);
			node.setAllowsChildren(isSummary);
		}

		//  Node found and not saved -> delete
		else if (node != null && !save)
		{
			MTreeNode parent = (MTreeNode)node.getParent();
			node.removeFromParent();
			node = parent;  //  select Parent
		}

		//  Error
		else
		{
			log.log(Level.SEVERE, "Save=" + save + ", KeyID=" + keyID + ", Node=" + node);
			node = null;
		}

		//  Nothing to display
		if (node == null)
			return;

		//  (Re) Display Node
		tree.updateUI();
		TreePath treePath = new TreePath(node.getPath());
		tree.setSelectionPath(treePath);
		tree.makeVisible(treePath);       	//	expand it
		tree.scrollPathToVisible(treePath);
	}   //  nodeChanged


	/**************************************************************************
	 *  ActionListener
	 *  @param e event
	 */
	public void actionPerformed(ActionEvent e)
	{
		//  bar button pressed
		if (e.getSource() instanceof JButton)
		{
			//	Verify if is a Recent Item or a Item for bar
			if(e.getActionCommand().startsWith(PREFIX_BAR_ITEM)) {
				//  Find Node - don't show
				selectId(Integer.parseInt(e.getActionCommand().replaceAll(PREFIX_BAR_ITEM, "")), false);
				//  Select it
				MTreeNode tn = (MTreeNode) tree.getSelectionPath().getLastPathComponent();
				setSelectedNode(tn);
			} else if(e.getActionCommand().startsWith(PREFIX_RECENT_ITEM)) {
				MRecentItem recentItem = MRecentItem.get(Env.getCtx(), 
						Integer.parseInt(e.getActionCommand().replaceAll(PREFIX_RECENT_ITEM, "")));
				//	Launch
				if(recentItem.isOptionMenu()) {
					MMenu menu = MMenu.getFromId(Env.getCtx(), recentItem.getAD_Menu_ID());
					CFrame frame = (CFrame) Env.getFrame((JButton) e.getSource());
					AMenu aMenu = AEnv.getAMenu(frame);
					new AMenuStartItem(menu.getAD_Menu_ID(), true, 
							menu.get_Translation(MMenu.COLUMNNAME_Name), aMenu)
							.start();
				} else {
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					String TableName = MTable.getTableName(Env.getCtx(), recentItem.getAD_Table_ID());
	    			MQuery query = MQuery.getEqualQuery(TableName + "_ID", recentItem.getRecord_ID());
					AEnv.zoom((JButton) e.getSource(), recentItem.getAD_Window_ID(), query);
					setCursor(Cursor.getDefaultCursor());
				}
			}
		}

		//  popup menu commands
		else if (e.getSource() instanceof JMenuItem)
		{
	        String action = (String)e.getActionCommand();
	        Action a = tree.getActionMap().get(action);
	        if (a != null) {
	            a.actionPerformed(new ActionEvent(tree,
	                                              ActionEvent.ACTION_PERFORMED,
	                                              null));
	        } else if (action.equals("BarAdd")) {
	        	barAdd();
	        } else if (action.equals("BarRemove")) {
	        	barRemove();
	        } else if(action.equals("BarRefresh")) {
	        	barRefresh(toolSelected);
	    		//	
	    		bar.validate();
	    		bar.repaint();
	        }
		} else if (e.getSource() instanceof JCheckBox) {
			if (e.getActionCommand().equals("Expand"))
				expandTree();
		}
	}   //  actionPerformed
	
	/**
	 *  Add selected TreeNode to Bar
	 */
	private void barAdd()
	{
		MTreeNode nd = (MTreeNode)tree.getSelectionPath().getLastPathComponent();
		if (barDBupdate(true, nd.getNode_ID()))
			addToBar(nd,getParentToolBar(nd), false);
		else if (DBException.isUniqueContraintError(CLogger.retrieveException()))
			ADialog.error(0, this, "BookmarkExist", null);
	}   //  barAdd
	
	/**
	 * Returns the top level parent JToolBar for the given MTreenode. If the parent is not on 
	 * the CPanel yet a new one is created and added.
	 * @param nd
	 * @return top level parent JToolBar for the given MTreenode
	 */
	private JToolBar getParentToolBar(MTreeNode nd){
		int topParentId = getTopParentId(nd);
		JToolBar parent = toolbarMap.get(topParentId);
		if(parent==null){
			Enumeration<?> enTop =root.children();
			while (enTop.hasMoreElements()) {
				MTreeNode ndTop = (MTreeNode)enTop.nextElement();
				if(ndTop.getNode_ID() == topParentId){
					log.fine("add new category: " + ndTop);
					parent = new JToolBar(JToolBar.VERTICAL);
					parent.setName(PREFIX_BAR_ITEM + ndTop.getNode_ID());
					parent.addMouseListener(mouseListener);
					toolbarMap.put(ndTop.getNode_ID(), parent);
					toolbarList.add(parent);
					parent.setOpaque(false);
					parent.setFloatable(false);
					parent.setRollover(true);
					parent.setBorder(BorderFactory.createEmptyBorder());

					JXTaskPane barPart = new JXTaskPane();
					//Begin - [FR 1953769]
					barPart.setUI(new AdempiereTaskPaneUI());
					barPart.getContentPane().setBackground(AdempierePLAF.getFormBackground());
					//End - [FR 1953769]
					barPart.setTitle(ndTop.toString().trim());
					barPart.setAnimated(true);
					barPart.setLayout(new BorderLayout());
					barPart.add(parent, BorderLayout.NORTH);

					bar.add(barPart);
					return parent;
				}
			}
		} else {
			log.fine("parent found: " + parent);
		}
		return parent;
	}

	/**
	 * Returns the id of the top level parent of the given MTreenode
	 * @param nd
	 * @return
	 */
	private int getTopParentId(MTreeNode nd) {
		MTreeNode parent = (MTreeNode) nd.getParent();
		if(parent!=null && parent.getNode_ID()!=0){
			return getTopParentId(parent);
		}
		return nd.getNode_ID();
	}
	
	/**
	 * Update All info
	 */
	public void updateInfo() {
		//	For tool bar
		barRefresh();
	}
	
	
	/**
	 * Refresh All
	 */
	private void barRefresh() {
		for(JToolBar toolbar : toolbarList) {
			barRefresh(toolbar);
		}
		//	
		bar.validate();
		bar.repaint();
	}
	
	/**
	 * Refresh
	 */
	private void barRefresh(JToolBar toolBar) {
		//	Validate null
		if(toolBar == null)
			return;
		//	
		toolBar.removeAll();
		//	Verify Type
		if(toolBar.getName().startsWith(PREFIX_RECENT_ITEM)) {
			//	Delete unnecessary items
			MRecentItem.deleteExtraItems(Env.getCtx());
			//	
			for(MRecentItem recentItem : MRecentItem.getFromUserAndRole(Env.getCtx())) {
				addToBar(recentItem, toolBar, false);
			}
		} else if(toolBar.getName().startsWith(PREFIX_BAR_ITEM)) {
			try {
				int [] nodeIds = DB.getIDsEx(null, "SELECT Node_ID "
						+ "FROM AD_TreeBar "
						+ "WHERE AD_Client_ID = ? "
						+ "AND AD_User_ID = ? "
						+ "AND AD_Tree_ID = ?", 
						Env.getAD_Client_ID(Env.getCtx()), 
						Env.getAD_User_ID(Env.getCtx()), 
						treeId);
				//	Iterate
				for(int nodeId : nodeIds) {
					MTreeNode node = root.findNode(nodeId);
					int parentId = getTopParentId(node);
					int parentBarId = Integer.parseInt(toolBar.getName().replaceAll(PREFIX_BAR_ITEM, ""));
					if(node != null
							&& parentId == parentBarId) {
						addToBar(node, toolBar, false);
					}
				}
			} catch(Exception e) {
				//	
			}
		}
	}

	/**
	 *  Add TreeNode to Bar
	 *  @param nd node
	 */
	private void addToBar(MTreeNode nd, JToolBar currentToolBar, boolean isLabel)
	{
		//	Only first word of Label
		String label = nd.toString().trim();
		if (!isLabel) {
			CButton button = new CButton(label);
			button.setOpaque(false);
			button.setHorizontalAlignment(JButton.LEFT);
			button.setMargin(new Insets(0, 0, 0, 0));
			button.setIcon(nd.getIcon());
			button.setRequestFocusEnabled(false);
			button.setToolTipText(nd.getDescription());
			button.setActionCommand(PREFIX_BAR_ITEM + String.valueOf(nd.getNode_ID()));
			button.addActionListener(this);
			button.addMouseListener(mouseListener);
			currentToolBar.add(button);
	    } else {
	    	currentToolBar.add(new JLabel("<html><u><b>" +label+"</b></u></html>"));
	    }
	}   //  addToBar
	
	/**
	 *  Add Recent Item to Bar
	 *  @param recentItem node
	 */
	private void addToBar(MRecentItem recentItem, JToolBar currentToolBar, boolean isLabel) {
		//	Only first word of Label
		String label = recentItem.getLabel();
		
		// Item in the meantime not existent ?
		if (label == null) {
			recentItem.deleteEx(true);
			return; // record could have been deleted
		}
		
		if (!isLabel) {
			//	Validate only menu option
			if(recentItem.getAD_Menu_ID() == 0)
				return;
			MMenu menu = MMenu.getFromId(Env.getCtx(), recentItem.getAD_Menu_ID());
			CButton button = new CButton(label);
			button.setOpaque(false);
			button.setHorizontalAlignment(JButton.LEFT);
			button.setMargin(new Insets(0, 0, 0, 0));
			button.setIcon(MTreeNode.getIcon(MTreeNode.getImageIndex(menu.getAction())));
			button.setRequestFocusEnabled(false);
			button.setToolTipText(label);
			button.setActionCommand(PREFIX_RECENT_ITEM + String.valueOf(recentItem.getAD_RecentItem_ID()));
			button.addActionListener(this);
			button.addMouseListener(mouseListener);
			currentToolBar.add(button);
	    } else {
	    	currentToolBar.add(new JLabel("<html><u><b>" + label +"</b></u></html>"));
	    }
	}   //  addToBar

	/**
	 *  Remove from Bar
	 */
	private void barRemove() {
		//the button in on a JToolBar which is on a CPanel
		for (JToolBar jt : toolbarList) {
			jt.remove(buttonSelected);
		}
		bar.validate();
		bar.repaint();
		//	For Recent Item or Item Bar
		if(buttonSelected.getActionCommand().startsWith(PREFIX_BAR_ITEM)) {
			barDBupdate(false, Integer.parseInt(buttonSelected.getActionCommand().replaceAll(PREFIX_BAR_ITEM, "")));
		} else if(buttonSelected.getActionCommand().startsWith(PREFIX_RECENT_ITEM))  {
			MRecentItem item = MRecentItem.get(Env.getCtx(), 
					Integer.parseInt(buttonSelected.getActionCommand().replaceAll(PREFIX_RECENT_ITEM, "")));
			//	Delete item
			item.delete(true);
		}
	}   //  barRemove

	/**
	 *	Make Bar add/remove persistent
	 *  @param add true if add - otherwise remove
	 *  @param Node_ID Node ID
	 *  @return true if updated
	 */
	private boolean barDBupdate (boolean add, int Node_ID)
	{
		int AD_Client_ID = Env.getAD_Client_ID(Env.getCtx());
		int AD_Org_ID = Env.getContextAsInt(Env.getCtx(), "#AD_Org_ID");
		int AD_User_ID = Env.getContextAsInt(Env.getCtx(), "#AD_User_ID");
		StringBuffer sql = new StringBuffer();
		if (add)
			sql.append("INSERT INTO AD_TreeBar "
				+ "(AD_Tree_ID,AD_User_ID,Node_ID, "
				+ "AD_Client_ID,AD_Org_ID, "
				+ "IsActive,Created,CreatedBy,Updated,UpdatedBy)VALUES (")
				.append(treeId).append(",").append(AD_User_ID).append(",").append(Node_ID).append(",")
				.append(AD_Client_ID).append(",").append(AD_Org_ID).append(",")
				.append("'Y',SysDate,").append(AD_User_ID).append(",SysDate,").append(AD_User_ID).append(")");
			//	if already exist, will result in ORA-00001: unique constraint (ADEMPIERE.AD_TREEBAR_KEY)
		else
			sql.append("DELETE AD_TreeBar WHERE AD_Tree_ID=").append(treeId)
				.append(" AND AD_User_ID=").append(AD_User_ID)
				.append(" AND Node_ID=").append(Node_ID);
		int no = DB.executeUpdate(sql.toString(), false, null);
		return no == 1;
	}	//	barDBupdate


	/**
	 *  Clicked on Expand All
	 */
	private void expandTree()
	{
		if (treeExpand.isSelected())
		{
			for (int row = 0; row < tree.getRowCount(); row++)
				tree.expandRow(row);
		}
		else
		{
//			patch: 1654055 +jgubo Changed direction of collapsing the tree nodes
			for (int row = tree.getRowCount(); row > 0; row--)
				tree.collapseRow(row);
		}
	}   //  expandTree

	@Override
	public void paint(Graphics g) {
		if (lookAndFeelChanged) {
			lookAndFeelChanged = false;
			if (hasBar) removeSplitPaneBorder();
		}
		super.paint(g);
	}
	
    private void setMappings(JTree tree) { 
        ActionMap map = tree.getActionMap();
        map.put(TransferHandler.getCutAction().getValue(Action.NAME),
                TransferHandler.getCutAction());
        map.put(TransferHandler.getPasteAction().getValue(Action.NAME),
                TransferHandler.getPasteAction());
    }
    
    //CHANGED - SET BUSY ADDED
	/**
	 *	Indicate Busy
	 *  @param busy busy
	 */
	private void setBusy (boolean busy)
	{
		JFrame frame = Env.getFrame(this);
		log.info("frame: " + frame);
		if (frame == null)  //  during init
			return;
		if (busy)
		{
			this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			treeSearch.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		}
		else
		{
			this.setCursor(Cursor.getDefaultCursor());
			frame.setCursor(Cursor.getDefaultCursor());
			treeSearch.setCursor(Cursor.getDefaultCursor());
		}
	}	//	set Busy

	public int getTreeId()
	{
		return treeId;
	}
}   //  VTreePanel
