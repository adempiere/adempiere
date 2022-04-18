package org.compiere.apps.form;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.VetoableChangeListener;
import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;

import javax.swing.JCheckBox;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeSelectionModel;
import javax.swing.tree.TreePath;

import org.adempiere.plaf.AdempierePLAF;
import org.compiere.apps.ADialog;
import org.compiere.apps.ConfirmPanel;
import org.compiere.apps.StatusBar;
import org.compiere.grid.ed.VLookup;
import org.compiere.minigrid.MiniTable;
import org.compiere.model.MColumn;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MProduct;
import org.compiere.model.MProductBOM;
import org.compiere.model.MUOM;
import org.compiere.model.Query;
import org.compiere.swing.CCheckBox;
import org.compiere.swing.CLabel;
import org.compiere.swing.CMenuItem;
import org.compiere.swing.CPanel;
import org.compiere.swing.CTextField;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Language;
import org.compiere.util.Msg;

/**
 * BOM Tree Maintenance
 *	
 *  @author Victor Perez,Sergio Ramazzinag
 *  @version $Id: VTreeMaintenance.java,v 1.1 2004/03/20 04:35:51 jjanke Exp $
 * 
 *	4Layers - MODIFICATIONS --------------------------------------------------------
 * 	 2005/04/12	Various improvements to the standard form (Sergio Ramazzina)
 *	4Layers -------------------------------------------------------------------- end
 *
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 * @author Paul Bowden, adaxa modified for manufacturing light
 */
public class VTreeBOM extends CPanel implements FormPanel, ActionListener,
		ListSelectionListener, PropertyChangeListener, VetoableChangeListener,
		TreeSelectionListener, TableModelListener
{

	/*****************************************************************************
	 *	Mouse Listener for Popup Menu
	 */
	final class VTreeBOM_mouseAdapter extends java.awt.event.MouseAdapter
	{
		/**
		 *	Constructor
		 *  @param adaptee adaptee
		 */
		VTreeBOM_mouseAdapter(VTreeBOM adaptee)
		{
			m_adaptee = adaptee;
		}	//	VLookup_mouseAdapter

		private VTreeBOM m_adaptee;

		/**
		 *	Mouse Listener
		 *  @param e MouseEvent
		 */
		public void mouseClicked(MouseEvent e)
		{
		//	System.out.println("mouseClicked " + e.getID() + " " + e.getSource().getClass().toString());
			//	popup menu
			if (SwingUtilities.isRightMouseButton(e))
				m_adaptee.popupMenu.show((Component)e.getSource(), e.getX(), e.getY());
			// Hide the popup if not right click - teo_sarca [ 1734802 ]
			else
				m_adaptee.popupMenu.setVisible(false);
		}	//	mouse Clicked

	}	//	VTreeBOM_mouseAdapter
	

	/**
	 *  Key Pressed
	 */
	class VTreeBOM_keyAdapter extends java.awt.event.KeyAdapter
	{
		VTreeBOM m_adaptee;

		/**
		 * 	VTreePanel_keyAdapter
		 *	@param adaptee
		 */
		VTreeBOM_keyAdapter(VTreeBOM adaptee)
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

	
	
	private static final long serialVersionUID = 1L;

	/**
	 *	Tree Maintenance 
	 */
	//public VTreeBOM ()
//	{
//	}	//	VTreeMaintenance

	/**	Window No				*/
	private int         	m_WindowNo = 0;
	/**	FormFrame				*/
	private FormFrame 		m_frame;
	/**	Active Tree				*/
	private myJTree		 	m_tree;

	private static CLogger log = CLogger.getCLogger(VTreeBOM.class);

	private BorderLayout	mainLayout	= new BorderLayout ();
	private CPanel 			northPanel	= new CPanel ();

	private FlowLayout		northLayout	= new FlowLayout ();
	//private BorderLayout		northLayout	= new BorderLayout ();
	private CPanel			southPanel = new CPanel();
	private CPanel			southPanel2 = new CPanel ();
	private BorderLayout 	southLayout = new BorderLayout();
	private FlowLayout	southLayout2 = new FlowLayout ();
	
	private CLabel			labelProduct	= new CLabel ();
//	private PopupMenu popMenuTree = new PopupMenu();
	private VLookup fieldProduct;
	private CCheckBox		implosion	= new CCheckBox ();
	private CLabel			treeInfo	= new CLabel ();
	private CLabel			spacer	= new CLabel ();
	//
	private JSplitPane		splitPane	= new JSplitPane ();
	private JScrollPane dataPane = new JScrollPane();
	private JScrollPane treePane = new JScrollPane();
//	private CPanel southPanel = new CPanel();
//	private BorderLayout southLayout = new BorderLayout();
	private DefaultTreeSelectionModel treeSelect = new DefaultTreeSelectionModel();
	private CCheckBox treeExpand = new CCheckBox();
	private CTextField treeSearch = new CTextField(10);
	private CLabel treeSearchLabel = new CLabel();
	private String      m_search = "";
	private Enumeration<?> m_nodeEn;
	private Enumeration<?> m_childEn;
	private DefaultMutableTreeNode   m_selectedNode;	//	the selected model node
	private int   m_selected_id = 0;
	private MouseListener mouseListener = new VTreeBOM_mouseAdapter(this);
	private KeyListener keyListener = new VTreeBOM_keyAdapter(this);
	/**	Property Listener NodeSelected	by Left Click		*/
	public static final String NODE_SELECTION = "NodeSelected";
	private DefaultMutableTreeNode  	m_root = null;
	
	private ConfirmPanel confirmPanel = new ConfirmPanel(true);
	protected StatusBar statusBar = new StatusBar();

	private MiniTable tableBOM = new MiniTable();
	private Vector<Vector<Object>> dataBOM = new Vector<Vector<Object>>();
	private Vector<String> columnNames;

	private final int DIVIDER_LOCATION = 300;
	private boolean reload = false;
	private Language language = Language.getLoginLanguage(); // Base Language
	
	JPopupMenu 					popupMenu = new JPopupMenu();
	private CMenuItem 			mBOM;
	private CMenuItem 			mImplosion;
	private MLookup 			m_fieldProduct;


	public Properties getCtx() {
		return Env.getCtx();
	}
	
	/**
	 *	Initialize Panel
	 *  @param WindowNo window
	 *  @param frame frame
	 */
	public void init (int WindowNo, FormFrame frame)
	{
		m_WindowNo = WindowNo;
		m_frame = frame;
		log.info( "VMerge.init - WinNo=" + m_WindowNo);
		try
		{
			preInit();
			jbInit ();

			frame.getContentPane().add(this, BorderLayout.CENTER);
		}
		catch (Exception ex)
		{
			log.log(Level.SEVERE,"VTreeMaintenance.init", ex);
		}
	}	//	init

	/**
	 * 	Fill Tree Combo
	 */
	private void preInit() throws Exception
	{
		m_fieldProduct = MLookupFactory.get(getCtx(), m_WindowNo,
				MColumn.getColumn_ID(MProduct.Table_Name, "M_Product_ID"),
				DisplayType.Search, language, MProduct.COLUMNNAME_M_Product_ID, 0, false,
				" M_Product.IsSummary = 'N'");
		fieldProduct = new VLookup ("M_Product_ID", false, false, true,  m_fieldProduct) {
			private static final long serialVersionUID = 1L;
			public void setValue(Object value) {
				super.setValue(value);
				action_loadBOM();
			}
		};
		
		implosion.addActionListener(this);
		splitPane.add (dataPane, JSplitPane.RIGHT);
		splitPane.add (treePane, JSplitPane.LEFT);
	}	//	preInit

	/**
	 *  Static Init.
	 *  <pre>
	 *  mainPanel
	 *      northPanel
	 *      centerPanel
	 *          xMatched
	 *          xPanel
	 *          xMathedTo
	 *      southPanel
	 *  </pre>
	 *  @throws Exception
	 */
	private void loadTableBOM()
	{
		//  Header Info
		columnNames = new Vector<String>(18);

		columnNames.add(Msg.translate(getCtx(), "Select"));	        // 0		
		columnNames.add(Msg.translate(getCtx(), "IsActive"));       // 1
		columnNames.add(Msg.translate(getCtx(), "Line"));           // 2
		columnNames.add(Msg.translate(getCtx(), "M_Product_ID"));   // 3
		columnNames.add(Msg.translate(getCtx(), "C_UOM_ID"));       // 4
		columnNames.add(Msg.translate(getCtx(), "QtyBOM"));   		// 5

		//  Remove previous listeners
		tableBOM.getModel().removeTableModelListener(this);
		//  Remove previous listeners
		tableBOM.getModel().removeTableModelListener(this);
		//  Set Model
		DefaultTableModel model = new DefaultTableModel(dataBOM, columnNames);
		model.addTableModelListener(this);
		tableBOM.setModel(model);

		tableBOM.setColumnClass( 0, Boolean.class, false);     //  0 Select
		tableBOM.setColumnClass( 1, Boolean.class, false);     //  1 IsActive
		tableBOM.setColumnClass( 2, Integer.class,false);      //  2 Line
		tableBOM.setColumnClass( 3, KeyNamePair.class,false);  //  3 M_Product_ID
		tableBOM.setColumnClass( 4, KeyNamePair.class,false);  //  4 C_UOM_ID
		tableBOM.setColumnClass( 5, BigDecimal.class,false);   //  5 QtyBOM

		tableBOM.setMultiSelection(false);
		tableBOM.setColumnVisibility(tableBOM.getColumn(0),false);
		tableBOM.autoSize();

	}   //  dynInit


	/**
	 * 	Static init
	 *	@throws Exception
	 */
	private void jbInit () 
	{
		this.setLayout (mainLayout);

		// 4Layers - Set initial window dimension 
		this.setPreferredSize(new Dimension(640, 480));

		labelProduct.setText (Msg.getElement(getCtx(), "M_Product_ID"));
		implosion.setText (Msg.getElement(getCtx(), "Implosion"));
		treeInfo.setText ("    ");
		spacer.setText ("    ");
		northPanel.setLayout (northLayout);
		northLayout.setAlignment (FlowLayout.LEFT);

		//
		this.add (northPanel, BorderLayout.NORTH);

		northPanel.add (labelProduct, null);
		northPanel.add (fieldProduct, null);
		northPanel.add (implosion, null);
		northPanel.add (spacer, null);
		northPanel.add (spacer, null);
		northPanel.add (treeInfo, null);

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

		this.add(southPanel, BorderLayout.SOUTH);
		southPanel.setLayout(southLayout);
		confirmPanel.addActionListener(this);
		southPanel.add(confirmPanel, BorderLayout.SOUTH);

		southPanel2.setLayout(southLayout2);
		southLayout2.setAlignment (FlowLayout.LEFT);
		
		southPanel.add(southPanel2, BorderLayout.NORTH);
		southPanel2.add(treeExpand, null);//BorderLayout.EAST);
		southPanel2.add(spacer, null);
		southPanel2.add(treeSearchLabel, null);//BorderLayout.WEST);
		southPanel2.add(treeSearch, null);//BorderLayout.CENTER);


				
		this.add (splitPane, BorderLayout.CENTER);

		// 4Layers - Set divider location
		splitPane.setDividerLocation(DIVIDER_LOCATION);
		
		mBOM = new CMenuItem(Msg.getMsg(Env.getCtx(), "BOM"), Env.getImageIcon("Detail16.gif"));
		mBOM.addActionListener(this);
		popupMenu.add(mBOM);

		mImplosion = new CMenuItem(Msg.getMsg(Env.getCtx(), "Implosion"), Env.getImageIcon("Parent16.gif"));
		mImplosion.addActionListener(this);
		popupMenu.add(mImplosion);



	}	//	jbInit

	/**
	 *  Enter Key
	 *  @param e event
	 */
	protected void keyPressed(KeyEvent e)
	{

		//  *** Tree ***
		if (e.getSource() instanceof myJTree
			|| (e.getSource() == treeSearch && e.getModifiers() != 0))	//	InputEvent.CTRL_MASK
		{
			TreePath tp = m_tree.getSelectionPath();
			if (tp == null)
				ADialog.beep();
			else
			{
				DefaultMutableTreeNode tn = (DefaultMutableTreeNode)m_tree.getLastSelectedPathComponent();
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
				DefaultMutableTreeNode nd = (DefaultMutableTreeNode)m_nodeEn.nextElement();
				Vector <?> nodeInfo = (Vector <?>)(nd.getUserObject());
				String uoName = ((KeyNamePair)nodeInfo.elementAt(3)).getName() ;
				//	compare in upper case
				if (uoName.toUpperCase().indexOf(search.toUpperCase()) != -1)
				{
					found = true;
					TreePath treePath = new TreePath(nd.getPath());
					m_tree.setSelectionPath(treePath);
					m_tree.makeVisible(treePath);			//	expand it
					m_tree.scrollPathToVisible(treePath);
				}
			}
			if (!found)
				ADialog.beep();
		}   //  treeSearch

	}   //  keyPressed


	/**
	 *  Set the selected node & initiate all listeners
	 *  @param nd node
	 */
	private void setSelectedNode (DefaultMutableTreeNode nd)
	{
		log.config("Node = " + nd);
		m_selectedNode = nd;
		//
		firePropertyChange(NODE_SELECTION, null, nd);
	}   //  setSelectedNode

	

	/**
	 * 	Dispose
	 */
	public void dispose()
	{
		if (m_frame != null)
			m_frame.dispose();
		m_frame = null;
	}	//	dispose

	public void vetoableChange (PropertyChangeEvent e)
	{
		String name = e.getPropertyName();
		Object value = e.getNewValue();
		log.info( "VAllocation.vetoableChange - " + name + "=" + value);
		if (value == null)
			return;

		//  BPartner
		if (name.equals("M_Product_ID"))
		{
			if (fieldProduct != null)
				action_loadBOM();	
		}		
	}   //  vetoableChange

	/**
	 * 	Action Listener
	 *	@param e event
	 */
	public void actionPerformed (ActionEvent e)
	{
		if (e.getSource() == mBOM)
		{
			fieldProduct.setValue(m_selected_id);
			if (implosion.isSelected())
				implosion.doClick();
		} 
		if (e.getSource() == mImplosion)
		{
			fieldProduct.setValue(m_selected_id);
			if (!implosion.isSelected())
				implosion.doClick();
		}

		if (e.getSource() == implosion) 
		{
			action_loadBOM();
		}
		if (e.getActionCommand().equals(ConfirmPanel.A_OK))
		{
			action_loadBOM();
		}
		// 4Layers - Close window when cancel is pressed
		if (e.getActionCommand().equals(ConfirmPanel.A_CANCEL)) 
		{
			dispose();
		}
		else if (e.getSource() instanceof JCheckBox)
		{
			if (e.getActionCommand().equals("Expand"))
				expandTree();
		}
		// 4Layers - End
	}	//	actionPerformed

	/**
	 * 	Action: Fill Tree with all nodes
	 */
	private void action_loadBOM()
	{
		//m_frame.setBusy(true);
		m_frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		
		reload = false;

		int M_Product_ID = getM_Product_ID(); 
		if (M_Product_ID == 0)
			return;
		MProduct M_Product = MProduct.get(getCtx(), M_Product_ID);
		treeInfo.setText ("    Current Selection: "+M_Product.getValue());

		
		Vector<Object> line = new Vector<Object>(17);
		line.add(Boolean.FALSE);  //  0 Select
		line.add(Boolean.valueOf(M_Product.isActive()));   //  1 IsActive
		line.add(Integer.valueOf(0)); // 2 Line
		KeyNamePair pp = new KeyNamePair(M_Product.getM_Product_ID(),M_Product.getValue().concat("_").concat(M_Product.getName()));
		line.add(pp); //  3 M_Product_ID
		MUOM u = new MUOM(M_Product.getCtx(), M_Product.getC_UOM_ID(), M_Product.get_TrxName());
		KeyNamePair uom = new KeyNamePair(M_Product.getC_UOM_ID(),u.getUOMSymbol());
		line.add(uom); //  4 C_UOM_ID
		line.add(Env.ONE);  //  5 QtyBOM
		
		
		DefaultMutableTreeNode parent = new DefaultMutableTreeNode(line);
		m_root = (DefaultMutableTreeNode)parent.getRoot();

		dataBOM.clear();

		if (isImplosion())
		{
			for (MProductBOM bomline : getParentBOMs(M_Product_ID))
			{
				addParent(bomline, parent);
			}     
			m_tree = new myJTree(parent);

			m_tree.addMouseListener(mouseListener);
		}
		else
		{
			for (MProductBOM bom : getChildBOMs(M_Product_ID, true))
			{
				addChild(bom, parent);                    
			}      
			m_tree = new myJTree(parent);
			m_tree.addMouseListener(mouseListener);
		}

/*		MouseListener ml = new MouseAdapter() {
		     public void mousePressed(MouseEvent e) {
		         int selRow = m_tree.getRowForLocation(e.getX(), e.getY());
		         TreePath selPath = m_tree.getPathForLocation(e.getX(), e.getY());
		         if(selRow != -1) {
		             if(e.getClickCount() == 1) {
		                 mySingleClick(selRow, selPath);
		             }
//		             else if(e.getClickCount() == 2) {
//		                 myDoubleClick(selRow, selPath);
//		             }
		         }
		     }

			private void mySingleClick(int selRow, TreePath selPath) {
				// TODO Auto-generated method stub
				
			}
		};
		m_tree.addMouseListener(ml);
*/

		m_tree.addTreeSelectionListener(this);
		
		treePane.getViewport().add (m_tree, null);

		loadTableBOM();
		dataPane.getViewport().add(tableBOM, null);
		// 4Layers - Set divider location
		splitPane.setDividerLocation(DIVIDER_LOCATION);
		// 4Layers - end
		
		//	Popup
//		if (m_selected_id != 0)
//		{
//		}


		//m_frame.setBusy(false);
		m_frame.setCursor(Cursor.getDefaultCursor());
	}	//	action_fillTree

	private void action_reloadBOM()
	{
		//m_frame.setBusy(true);
		m_frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		
		reload = true;

		//int M_Product_ID = getM_Product_ID();
		int M_Product_ID = m_selected_id;

		if (M_Product_ID == 0)
			return;
		MProduct M_Product = MProduct.get(getCtx(), M_Product_ID);
		treeInfo.setText ("    Current Selection: "+M_Product.getValue());

		
		Vector<Object> line = new Vector<Object>(17);
		line.add(Boolean.FALSE);  //  0 Select
		line.add(Boolean.valueOf(M_Product.isActive()));   //  1 IsActive
		line.add(Integer.valueOf(0)); // 2 Line
		KeyNamePair pp = new KeyNamePair(M_Product.getM_Product_ID(),M_Product.getValue().concat("_").concat(M_Product.getName()));
		line.add(pp); //  3 M_Product_ID
		MUOM u = new MUOM(M_Product.getCtx(), M_Product.getC_UOM_ID(), M_Product.get_TrxName());
		KeyNamePair uom = new KeyNamePair(M_Product.getC_UOM_ID(),u.getUOMSymbol());
		line.add(uom); //  4 C_UOM_ID
		line.add(Env.ONE);  //  5 QtyBOM

		
		DefaultMutableTreeNode parent = new DefaultMutableTreeNode(line);

		dataBOM.clear();

		if (isImplosion())
		{
			for (MProductBOM bomline : getParentBOMs(M_Product_ID))
			{
				addParent(bomline, parent);
			}     
			//m_tree = new myJTree(parent);
		}
		else
		{
/*			m_childEn = m_selectedNode.children();
			Vector<Object> line = new Vector<Object>(17);
			while (m_childEn != null && m_childEn.hasMoreElements())
			{
				DefaultMutableTreeNode nd = (DefaultMutableTreeNode)m_childEn.nextElement();
				//String[] uo = (String[])nd.getUserObject();
				line.add(nd.getUserObject());
				dataBOM.add( line);
			}
*/			
			for (MProductBOM bom : getChildBOMs(M_Product_ID, true))
			{
				addChild(bom, parent);                    
			}      
			//m_tree = new myJTree(parent);
		}

		//m_tree.addTreeSelectionListener(this);

		//treePane.getViewport().add (m_tree, null);

		loadTableBOM();

		//m_frame.setBusy(false);
		m_frame.setCursor(Cursor.getDefaultCursor());
	}	//	action_fillTree

	public void addChild(MProductBOM bomline, DefaultMutableTreeNode parent) 
	{

		//System.out.println("-------------------------Parent:" + bom.getName());
		MProduct M_Product = MProduct.get(getCtx(), bomline.getM_ProductBOM_ID());

		Vector<Object> line = new Vector<Object>(17);
		line.add(Boolean.FALSE);  //  0 Select
		line.add(Boolean.valueOf(bomline.isActive()));   //  1 IsActive
		line.add(Integer.valueOf(bomline.getLine())); // 2 Line
		KeyNamePair pp = new KeyNamePair(M_Product.getM_Product_ID(),M_Product.getValue().concat("_").concat(M_Product.getName()));
		line.add(pp); //  3 M_Product_ID
		MUOM u = new MUOM(M_Product.getCtx(), M_Product.getC_UOM_ID(), M_Product.get_TrxName());
		KeyNamePair uom = new KeyNamePair(M_Product.getC_UOM_ID(),u.getUOMSymbol());
		line.add(uom); //  4 C_UOM_ID
		line.add((BigDecimal) ((bomline.getBOMQty()!=null) ? bomline.getBOMQty() : new BigDecimal(0)));  //  5 QtyBOM

		DefaultMutableTreeNode child = new DefaultMutableTreeNode(line);
		parent.add(child);
		
		if(m_selected_id == bomline.getM_Product_ID() || getM_Product_ID() == bomline.getM_Product_ID())		
			dataBOM.add(line);
		
		if(reload)  return;

		for (MProductBOM bom : getChildBOMs(bomline.getM_ProductBOM_ID(), false))
		{
			addChild(bom, child);
		}
	}

	public void addParent(MProductBOM bom, DefaultMutableTreeNode parent) 
	{
		MProduct M_Product = MProduct.get(getCtx(), bom.getM_Product_ID());

		Vector<Object> line = new Vector<Object>(17);
		line.add(Boolean.FALSE);  //  0 Select
		line.add(Boolean.valueOf(M_Product.isActive()));   //  1 IsActive
		line.add(Integer.valueOf(bom.getLine())); // 2 Line
		KeyNamePair pp = new KeyNamePair(M_Product.getM_Product_ID(),M_Product.getValue().concat("_").concat(M_Product.getName()));
		line.add(pp); //  3 M_Product_ID
		MUOM u = new MUOM(M_Product.getCtx(), M_Product.getC_UOM_ID(), M_Product.get_TrxName());
		KeyNamePair uom = new KeyNamePair(M_Product.getC_UOM_ID(),u.getUOMSymbol());
		line.add(uom); //  6 C_UOM_ID
		line.add((BigDecimal) ((bom.getBOMQty()!=null) ? bom.getBOMQty() : new BigDecimal(0)));  //  9 QtyBOM
		
		dataBOM.add(line);

		DefaultMutableTreeNode child = new DefaultMutableTreeNode(line); 
		parent.add(child);

		if(reload)  return;
		
		for (MProductBOM bomline : getParentBOMs(bom.getM_Product_ID()))
		{
			addParent(bomline, child);
		}
		//dataBOM.add(line);
	}



	public void valueChanged(TreeSelectionEvent event) 
	{
		m_selectedNode = (DefaultMutableTreeNode)m_tree.getLastSelectedPathComponent();

        /* if nothing is selected */ 
        if (m_selectedNode == null) return;
        
       	Vector <?> nodeInfo = (Vector <?>)(m_selectedNode.getUserObject());
        
        m_selected_id =  ((KeyNamePair)nodeInfo.elementAt(3)).getKey() ;
    
		action_reloadBOM();
		 
	}

	/**
	 * 	List Selection Listener
	 *	@param e event
	 */
	public void valueChanged (ListSelectionEvent e)
	{
		if (e.getValueIsAdjusting())
			return;
	}	//	valueChanged

	/**
	 *  Clicked on Expand All
	 */
	private void expandTree()
	{
		if (treeExpand.isSelected())
		{
			for (int row = 0; row < m_tree.getRowCount(); row++)
				m_tree.expandRow(row);
		}
		else
		{
//			patch: 1654055 +jgubo Changed direction of collapsing the tree nodes
			for (int row = m_tree.getRowCount(); row > 0; row--)
				m_tree.collapseRow(row);
		}
	}   //  expandTree

	
	/**
	 * 	VTreePanel Changed
	 *	@param e event
	 */
	public void propertyChange (PropertyChangeEvent e)
	{
	}	//	propertyChange

	public void tableChanged(TableModelEvent e) {
	}        


	//	action_treeDeleteAll

	/**************************************************************************
	 * 	Tree Maintenance List Item
	 */
	class ListItem
	{
		public ListItem (int id, String name, String description, boolean isSummary, String imageIndicator)
		{
			this.id = id;
			this.name = name;
			this.description = description;
			this.isSummary = isSummary;
			this.imageIndicator = imageIndicator;
		}	//	ListItem

		public int id;
		public String name;
		public String description;
		public boolean isSummary;
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
	
/*	private String[] productSummary(MProduct product, boolean isLeaf) {
		MUOM muom = MUOM.get(getCtx(), product.getC_UOM_ID());
		String value = product.getValue();
		String name = product.get_Translation(MProduct.COLUMNNAME_Name);
		String uom = new StringBuffer(" [")
					.append(muom.get_Translation(MUOM.COLUMNNAME_UOMSymbol)).append("]").toString();
		//
		if (value != null && value.equals(name))
			name = null;
		String[] treeObject = {value,name,uom};
		//
		return treeObject;
	}
*/	
	private boolean isImplosion() {
		return implosion.isSelected();
	}
	
	private int getM_Product_ID() {
		Integer Product = (Integer)fieldProduct.getValue();
		if (Product == null)
			return 0;
		return Product.intValue(); 
	}
	
	private List<MProductBOM> getChildBOMs(int M_Product_ID, boolean onlyActiveRecords)
	{
		String filter = MProductBOM.COLUMNNAME_M_Product_ID+"=?"
						+(onlyActiveRecords ? " AND IsActive='Y'" : "");
		return new Query(getCtx(), MProductBOM.Table_Name, filter, null)
					.setParameters(new Object[]{M_Product_ID})
					.list();
	}
	
	private List<MProductBOM> getParentBOMs(int M_Product_ID) 
	{
		String filter = MProductBOM.COLUMNNAME_M_ProductBOM_ID+"=?";
		return new Query(getCtx(), MProductBOM.Table_Name, filter, null)
						.setParameters(new Object[]{M_Product_ID})
						.list();
	}
}	//	VTreeMaintenance


/**************************************************************************
 * 	Tree convertValueToText
 */
class myJTree extends JTree
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6313465838547930491L;

	public myJTree(DefaultMutableTreeNode parent) {
		
		super(parent);
	}

	//mouseAdapter = new VLookup_mouseAdapter(this);    //  popup

	@Override
	public String convertValueToText(Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {

		DefaultMutableTreeNode nd = (DefaultMutableTreeNode)value; 
		
		Vector <?> userObject = (Vector <?>)nd.getUserObject();
		
		StringBuffer sb = new StringBuffer(((KeyNamePair)userObject.elementAt(3)).getName());
		
		return sb.toString();
	}
}
