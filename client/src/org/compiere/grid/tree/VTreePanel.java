/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.datatransfer.StringSelection;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragGestureRecognizer;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
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
import java.util.Map;
import java.util.logging.Level;

import javax.swing.BorderFactory;
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
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.SplitPaneUI;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultTreeSelectionModel;
import javax.swing.tree.TreePath;

import org.adempiere.plaf.AdempierePLAF;
import org.adempiere.plaf.AdempiereTaskPaneUI;
import org.compiere.apps.ADialog;
import org.compiere.model.MTree;
import org.compiere.model.MTreeNode;
import org.compiere.swing.CButton;
import org.compiere.swing.CCheckBox;
import org.compiere.swing.CLabel;
import org.compiere.swing.CMenuItem;
import org.compiere.swing.CPanel;
import org.compiere.swing.CTextField;
import org.compiere.swing.ShadowBorder;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.util.TrxRunnable;
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
 */
public final class VTreePanel extends CPanel
	implements ActionListener, DragGestureListener, DragSourceListener, DropTargetListener
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

	protected boolean m_lookAndFeelChanged = false;

	/**
	 *  Tree Panel for browsing and editing of a tree.
	 *  Need to call initTree
	 *  @param  WindowNo	WindowNo
	 *  @param  editable    if true you can edit it
	 *  @param  hasBar      has OutlookBar
	 */
	public VTreePanel(int WindowNo, boolean hasBar, boolean editable)
	{
		super();
		toolbar = new ArrayList<JToolBar>();
		log.config("Bar=" + hasBar + ", Editable=" + editable);
		m_WindowNo = WindowNo;
		m_hasBar = hasBar;
		m_editable = editable;

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
			popMenuTree.remove(mBarAdd);
		}
		else {
			centerSplitPane.setDividerLocation(80);
			UIManager.addPropertyChangeListener(new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent evt) {
					if ("lookAndFeel".equals(evt.getPropertyName()))
						m_lookAndFeelChanged = true;
				}

			});
		}
		//  base settings
		if (editable)
			tree.setDropTarget(dropTarget);
		else
		{
			popMenuTree.remove(mFrom);
			popMenuTree.remove(mTo);
		}
	}   //  VTreePanel

	/**
	 *  Tree initialization.
	 * 	May be called several times
	 *	@param	AD_Tree_ID	tree to load
	 *  @return true if loaded ok
	 */
	public boolean initTree (int AD_Tree_ID)
	{
		log.config("AD_Tree_ID=" + AD_Tree_ID);
		//
		m_AD_Tree_ID = AD_Tree_ID;

		//  Get Tree
		MTree vTree = new MTree (Env.getCtx(), AD_Tree_ID, m_editable, true, null);
		m_root = vTree.getRoot();
		m_root.setName(Msg.getMsg(Env.getCtx(), vTree.getName() ) ); // translate name of menu.
		// m_root.setName(Msg.getMsg(Env.getCtx(), "Menu") ); // @Trifon; this is the hardcoded way.

		log.config("root=" + m_root);
		m_nodeTableName = vTree.getNodeTableName();
		treeModel = new DefaultTreeModel(m_root, true);
		tree.setModel(treeModel);

		//  Shortcut Bar
		if (m_hasBar)
		{
			for (JToolBar jt : toolbar)
				jt.removeAll();
			toolbarMap = new HashMap<Integer, JToolBar>();
			Enumeration<?> enTop = m_root.children();
			JToolBar jt = null;
			Map<JToolBar,String> titleMap = new HashMap<JToolBar, String>();
			while (enTop.hasMoreElements())
			{
				MTreeNode ndTop = (MTreeNode)enTop.nextElement();
				Enumeration<?> en = ndTop.preorderEnumeration();
				boolean labelDrawn=false;
				while (en.hasMoreElements())
				{
					MTreeNode nd = (MTreeNode)en.nextElement();
					if (nd.isOnBar()) {
						if (!labelDrawn) {
							jt = new JToolBar(JToolBar.VERTICAL);
							titleMap.put(jt, ndTop.toString().trim());
							labelDrawn=true;
							toolbarMap.put(ndTop.getNode_ID(), jt);
						}
						addToBar(nd, jt, false);
					}
				}
				if (jt!=null)
					toolbar.add(jt);
				jt=null;
			}
			//jbInit();
			for (JToolBar jt2 : toolbar) {
				jt2.setOpaque(false);
				//jt2.setLayout(new GridBagLayout());
				jt2.setFloatable(false);
				jt2.setRollover(true);
				jt2.setBorder(BorderFactory.createEmptyBorder());

				JXTaskPane barPart = new JXTaskPane();
				//Begin - [FR 1953769]
				barPart.setUI(new AdempiereTaskPaneUI());
				barPart.getContentPane().setBackground(AdempierePLAF.getFormBackground());
				//End - [FR 1953769]
				barPart.setAnimated(true);
				barPart.setLayout(new BorderLayout());
				barPart.add(jt2, BorderLayout.NORTH);
				barPart.setTitle(titleMap.get(jt2));

				bar.add(barPart);
				//Begin - [FR 1953769]
				bar.setBackground(AdempierePLAF.getFormBackground());
				//End - [FR 1953769]
			}
		}
		return true;
	}   //  initTree

	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(VTreePanel.class);

	private BorderLayout mainLayout = new BorderLayout();
	private JTree tree = new JTree();
	private DefaultTreeModel treeModel;
	private DefaultTreeSelectionModel treeSelect = new DefaultTreeSelectionModel();
	private CPanel southPanel = new CPanel();
	private CCheckBox treeExpand = new CCheckBox();
	private CTextField treeSearch = new CTextField(10);
	private CLabel treeSearchLabel = new CLabel();
	private JPopupMenu popMenuTree = new JPopupMenu();
	private JPopupMenu popMenuBar = new JPopupMenu();
	private CMenuItem mFrom = new CMenuItem();
	private CMenuItem mTo = new CMenuItem();
	private JXTaskPaneContainer bar = new JXTaskPaneContainer();
	private java.util.List<JToolBar> toolbar;
	private HashMap<Integer, JToolBar> toolbarMap;
	private CMenuItem mBarAdd = new CMenuItem();
	private CMenuItem mBarRemove = new CMenuItem();
	private BorderLayout southLayout = new BorderLayout();
	private JSplitPane centerSplitPane = new JSplitPane();
	private JScrollPane treePane = new JScrollPane();
	private MouseListener mouseListener = new VTreePanel_mouseAdapter(this);
	private KeyListener keyListener = new VTreePanel_keyAdapter(this);

	//
	private int			m_WindowNo;
	/** Tree ID                     */
	private int			m_AD_Tree_ID = 0;
	/** Table Name for TreeNode     */
	private String      m_nodeTableName = null;
	/** Tree is editable (can move nodes) - also not active shown   */
	private boolean     m_editable;
	/** Tree has a shortcut Bar     */
	private boolean     m_hasBar;
	/** The root node               */
	private MTreeNode  	m_root = null;


	private MTreeNode   m_moveNode;    	//	the node to move
	private String      m_search = "";
	private Enumeration<?> m_nodeEn;
	private MTreeNode   m_selectedNode;	//	the selected model node
	private CButton     m_buttonSelected;

	private JScrollPane barScrollPane;

	/**	Property Listener NodeSelected	by Left Click		*/
	public static final String NODE_SELECTION = "NodeSelected";
	/**	Property Listener NodeSelected	by Right Click		*/
	public static final String NODE_SELECTION_RIGHT = "NodeSelectedRight";

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
		mFrom.setActionCommand("From");
		mFrom.addActionListener(this);
		mTo.setEnabled(false);
		mTo.setText(Msg.getMsg(Env.getCtx(), "ItemInsert"));
		mTo.setActionCommand("To");
		mTo.addActionListener(this);

		mBarAdd.setText(Msg.getMsg(Env.getCtx(), "BarAdd"));
		mBarAdd.setActionCommand("BarAdd");
		mBarAdd.addActionListener(this);
		mBarRemove.setText(Msg.getMsg(Env.getCtx(), "BarRemove"));
		mBarRemove.setActionCommand("BarRemove");
		mBarRemove.addActionListener(this);
		//
		popMenuTree.setLightWeightPopupEnabled(false);
		popMenuTree.add(mBarAdd);
		popMenuTree.add(mFrom);
		if(!m_hasBar){
			popMenuTree.addSeparator();
		}
		popMenuTree.add(mTo);
		popMenuBar.setLightWeightPopupEnabled(false);
		popMenuBar.add(mBarRemove);
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


	/*************************************************************************
	 *	Drag & Drop
	 */
	protected DragSource dragSource
		= DragSource.getDefaultDragSource();
	protected DropTarget dropTarget
		= new DropTarget(tree, DnDConstants.ACTION_MOVE, this, true, null);
	protected DragGestureRecognizer recognizer
		= dragSource.createDefaultDragGestureRecognizer(tree, DnDConstants.ACTION_MOVE, this);


	/**
	 *	Drag Gesture Interface	** Start **
	 *  @param e event
	 */
	public void dragGestureRecognized(DragGestureEvent e)
	{
		if (!m_editable)
			return;
		//
		try
		{
			m_moveNode = (MTreeNode)tree.getSelectionPath().getLastPathComponent();
		}
		catch (Exception ex)	//	nothing selected
		{
			return;
		}
		//	start moving
		StringSelection content = new StringSelection(m_moveNode.toString());
		e.startDrag(DragSource.DefaultMoveDrop,		//	cursor
					content,						//	Transferable
					this);
		log.fine( "Drag: " + m_moveNode.toString());
	}	//	dragGestureRecognized


	/**
	 *	DragSourceListener interface - noop
	 *  @param e event
	 */
	public void dragDropEnd(DragSourceDropEvent e)	{}
	/**
	 *	DragSourceListener interface - noop
	 *  @param e event
	 */
	public void dragEnter(DragSourceDragEvent e)	{}
	/**
	 *	DragSourceListener interface - noop
	 *  @param e event
	 */
	public void dragExit(DragSourceEvent e)	{}
	/**
	 *	DragSourceListener interface - noop
	 *  @param e event
	 */
	public void dragOver(DragSourceDragEvent e)	{}
	/**
	 *	DragSourceListener interface - noop
	 *  @param e event
	 */
	public void dropActionChanged(DragSourceDragEvent e)	{}

	/**
	 *	DropTargetListener interface
	 *  @param e event
	 */
	public void dragEnter(DropTargetDragEvent e)
	{
		e.acceptDrag(DnDConstants.ACTION_MOVE);
	}
	/**
	 *	DragSourceListener interface - noop
	 *  @param e event
	 */
	public void dropActionChanged(DropTargetDragEvent e)	{}
	/**
	 *	DragSourceListener interface - noop
	 *  @param e event
	 */
	public void dragExit(DropTargetEvent e)	{}


	/**
	 *	Drag over 				** Between **
	 *  @param e event
	 */
	public void dragOver(DropTargetDragEvent e)
	{
		Point mouseLoc = e.getLocation(); 	//	where are we?
		TreePath path = tree.getClosestPathForLocation(mouseLoc.x, mouseLoc.y);
		tree.setSelectionPath(path);		//	show it by selecting
		MTreeNode toNode = (MTreeNode)path.getLastPathComponent();
		//
	//	log.fine( "Move: " + toNode);
		if (m_moveNode == null				//	nothing to move
			||	toNode == null)				//	nothing to drop on
			e.rejectDrag();
		else
			e.acceptDrag(DnDConstants.ACTION_MOVE);
	}	//	dragOver


	/**
	 *	Drop					** End **
	 *  @param e event
	 */
	public void drop(DropTargetDropEvent e)
	{
		Point mouseLoc = e.getLocation(); 	//	where are we?
		TreePath path = tree.getClosestPathForLocation(mouseLoc.x, mouseLoc.y);
		tree.setSelectionPath(path);		//	show it by selecting
		MTreeNode toNode = (MTreeNode)path.getLastPathComponent();
		//
		log.fine( "Drop: " + toNode);
		if (m_moveNode == null				//	nothing to move
			||	toNode == null)				//	nothing to drop on
		{
			e.rejectDrop();
			return;
		}
		//
		e.acceptDrop(DnDConstants.ACTION_MOVE);
		moveNode(m_moveNode, toNode);

		e.dropComplete(true);
		m_moveNode = null;
	}	//	drop


	/**
	 *	Move TreeNode
	 *	@param	movingNode	The node to be moved
	 *	@param	toNode		The target node
	 */
	private void moveNode(MTreeNode movingNode, MTreeNode toNode)
	{
		log.info(movingNode.toString() + " to " + toNode.toString());

		if (movingNode == toNode)
			return;

		//  remove
		final MTreeNode oldParent = (MTreeNode)movingNode.getParent();
		movingNode.removeFromParent();
		treeModel.nodeStructureChanged(oldParent);

		//  insert
		final MTreeNode newParent;
		int index;
		if (!toNode.isSummary())	//	drop on a child node
		{
			newParent = (MTreeNode)toNode.getParent();
			index = newParent.getIndex(toNode) + 1;	//	the next node
		}
		else									//	drop on a summary node
		{
			newParent = toNode;
			index = 0;                   			//	the first node
		}
		newParent.insert(movingNode, index);
		treeModel.nodeStructureChanged(newParent);

		//	***	Save changes to disk
		try
		{
			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			Trx.run(new TrxRunnable() {
				public void run(String trxName) {
					for (int i = 0; i < oldParent.getChildCount(); i++)
					{
						MTreeNode nd = (MTreeNode)oldParent.getChildAt(i);
						updateTreeNode(nd.getNode_ID(), oldParent.getNode_ID(), i, trxName);
					}
					if (oldParent != newParent)
					{
						for (int i = 0; i < newParent.getChildCount(); i++)
						{
							MTreeNode nd = (MTreeNode)newParent.getChildAt(i);
							updateTreeNode(nd.getNode_ID(), newParent.getNode_ID(), i, trxName);
						}
					}
				}
			});
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "move", e);
			ADialog.error(m_WindowNo, this, "TreeUpdateError", e.getLocalizedMessage());
		}
		finally
		{
			setCursor(Cursor.getDefaultCursor());
		}
		log.config("complete");
	}	//	moveNode
	
	private int updateTreeNode(int Node_ID, int Parent_ID, int SeqNo, String trxName)
	{
		// We do not use parametrized statements because we need to log these migration scripts
		StringBuffer sql = new StringBuffer("UPDATE ");
		sql.append(m_nodeTableName)
			.append(" SET Parent_ID=").append(Parent_ID)
			.append(", SeqNo=").append(SeqNo)
			.append(", Updated=SysDate")
			.append(", UpdatedBy=").append(Env.getAD_User_ID(Env.getCtx()))
			.append(" WHERE AD_Tree_ID=").append(m_AD_Tree_ID)
			.append(" AND Node_ID=").append(Node_ID);
		log.fine(sql.toString());
		int no = DB.executeUpdateEx(sql.toString(), trxName);
		return no;
	}


	/*************************************************************************/

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
				m_nodeEn = m_root.preorderEnumeration();
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
			else if ((m_editable || m_hasBar)
				&& SwingUtilities.isRightMouseButton(e)
				&& tree.getSelectionPath() != null)         //  need select first
			{
				int selRow = tree.getRowForLocation(e.getX(), e.getY());
				TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
				if(selRow != -1){
					tree.setSelectionPath(selPath);
					
					Rectangle r = tree.getPathBounds(tree.getSelectionPath());
					if(r!=null){
						popMenuTree.show(tree, (int)r.getMaxX(), (int)r.getY());
					}else{
					  popMenuTree.show(tree, e.getX(), e.getY());
					}
					if(m_hasBar){
						MTreeNode nd = (MTreeNode)tree.getSelectionPath().getLastPathComponent();
						firePropertyChange(NODE_SELECTION_RIGHT, null, nd);
					}
				}
			}
		}   //  JTree

		//  *** JButton ***
		else if (e.getSource() instanceof JButton)
		{
			if (SwingUtilities.isRightMouseButton(e))
			{
				m_buttonSelected = (CButton)e.getSource();
				popMenuBar.show(m_buttonSelected, e.getX(), e.getY());
			}
		}   //  JButton

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
			return selectID(nodeID, true);     //  show selection
		return false;
	}   //  setSelectedNode

	/**
	 *  Select ID in Tree
	 *  @param nodeID	Node ID
	 *  @param show	scroll to node
	 * 	@return true if selected
	 */
	private boolean selectID (int nodeID, boolean show)
	{
		if (m_root == null)
			return false;
		log.config("NodeID=" + nodeID
			+ ", Show=" + show + ", root=" + m_root);
		//  try to find the node
		MTreeNode node = m_root.findNode (nodeID);
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
			+ ", root=" + m_root);
		//	if ID==0=root - don't update it
		if (keyID == 0)
			return;

		//  try to find the node
		MTreeNode node = m_root.findNode(keyID);

		//  Node not found and saved -> new
		if (node == null && save)
		{
			node = new MTreeNode (keyID, 0, name, description,
				m_root.getNode_ID(), isSummary, imageIndicator, false, null);
			m_root.add (node);
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
			//  Find Node - don't show
			selectID(Integer.parseInt(e.getActionCommand()), false);
			//  Select it
			MTreeNode tn = (MTreeNode)tree.getSelectionPath().getLastPathComponent();
			setSelectedNode(tn);
		}

		//  popup menu commands
		else if (e.getSource() instanceof JMenuItem)
		{
			if (e.getActionCommand().equals("From"))
				moveFrom();
			else if (e.getActionCommand().equals("To"))
				moveTo();
			else if (e.getActionCommand().equals("BarAdd"))
				barAdd();
			else if (e.getActionCommand().equals("BarRemove"))
				barRemove();
		}

		else if (e.getSource() instanceof JCheckBox)
		{
			if (e.getActionCommand().equals("Expand"))
				expandTree();
		}
	}   //  actionPerformed


	/*************************************************************************/

	/**
	 *  Copy Node into buffer
	 */
	private void moveFrom()
	{
		m_moveNode = (MTreeNode)tree.getSelectionPath().getLastPathComponent();
		if (m_moveNode != null)
			mTo.setEnabled(true);		//	enable menu
	}   //  mFrom_actionPerformed

	/**
	 *  Move Node
	 */
	private void moveTo()
	{
		mFrom.setEnabled(true);
		mTo.setEnabled(false);
		if (m_moveNode == null)
			return;

		MTreeNode toNode = (MTreeNode)tree.getSelectionPath().getLastPathComponent();
		moveNode(m_moveNode, toNode);
		//	cleanup
		m_moveNode = null;
	}   //  mTo_actionPerformed

	/**
	 *  Add selected TreeNode to Bar
	 */
	private void barAdd()
	{
		MTreeNode nd = (MTreeNode)tree.getSelectionPath().getLastPathComponent();
		if (barDBupdate(true, nd.getNode_ID()))
			addToBar(nd,getParentToolBar(nd), false);
		else if (CLogger.retrieveException().getMessage().indexOf("ORA-00001")!=-1)
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
			Enumeration<?> enTop =m_root.children();
			while (enTop.hasMoreElements()) {
				MTreeNode ndTop = (MTreeNode)enTop.nextElement();
				if(ndTop.getNode_ID()==topParentId){
					log.fine("add new category: " + ndTop);
					parent = new JToolBar(JToolBar.VERTICAL);

					toolbarMap.put(ndTop.getNode_ID(), parent);
					toolbar.add(parent);
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
	 *  Add TreeNode to Bar
	 *  @param nd node
	 */
	private void addToBar(MTreeNode nd, JToolBar currentToolBar, boolean isLabel)
	{
		//	Only first word of Label
		String label = nd.toString().trim();
	//	int space = label.indexOf(' ');
	//	if (space != -1)
	//		label = label.substring(0, space);

		if (!isLabel) {
			CButton button = new CButton(label);
			button.setOpaque(false);
			button.setHorizontalAlignment(JButton.LEFT);
			button.setMargin(new Insets(0, 0, 0, 0));
			button.setIcon(nd.getIcon());
			button.setRequestFocusEnabled(false);
			button.setToolTipText(nd.getDescription());
			button.setActionCommand(String.valueOf(nd.getNode_ID()));
			button.addActionListener(this);
			button.addMouseListener(mouseListener);
			currentToolBar.add(button);
	    } else {
	    	currentToolBar.add(new JLabel("<html><u><b>" +label+"</b></u></html>"));
	    }
		bar.validate();
		//if (centerSplitPane.getDividerLocation() == -1)
		//	centerSplitPane.setDividerLocation(button.getPreferredSize().width);
		bar.repaint();
	}   //  addToBar

	/**
	 *  Remove from Bar
	 */
	private void barRemove()
	{
		//the button in on a JToolBar which is on a CPanel
		JToolBar parentBar = (JToolBar) m_buttonSelected.getParent();
		Container parentPanel = null;
		if(parentBar!=null){
			parentPanel = parentBar.getParent();
		}
		for (JToolBar jt : toolbar) {
			jt.remove(m_buttonSelected);
		}

		if(parentPanel != null && parentBar.getComponentCount()==1){
			//only label left
			bar.remove(parentPanel);
			//remove from toolBarMap..
			toolbarMap.values().remove(parentBar);

		}
		bar.validate();
		bar.repaint();
		barDBupdate(false, Integer.parseInt(m_buttonSelected.getActionCommand()));
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
				.append(m_AD_Tree_ID).append(",").append(AD_User_ID).append(",").append(Node_ID).append(",")
				.append(AD_Client_ID).append(",").append(AD_Org_ID).append(",")
				.append("'Y',SysDate,").append(AD_User_ID).append(",SysDate,").append(AD_User_ID).append(")");
			//	if already exist, will result in ORA-00001: unique constraint (ADEMPIERE.AD_TREEBAR_KEY)
		else
			sql.append("DELETE AD_TreeBar WHERE AD_Tree_ID=").append(m_AD_Tree_ID)
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
		if (m_lookAndFeelChanged) {
			m_lookAndFeelChanged = false;
			if (m_hasBar) removeSplitPaneBorder();
		}
		super.paint(g);
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


}   //  VTreePanel


