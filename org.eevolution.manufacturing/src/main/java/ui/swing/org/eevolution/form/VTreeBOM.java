/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2010 e-Evolution,SC. All Rights Reserved.              *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/

package org.eevolution.form;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.VetoableChangeListener;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;

import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;

import org.compiere.apps.ConfirmPanel;
import org.compiere.apps.StatusBar;
import org.compiere.apps.form.FormFrame;
import org.compiere.apps.form.FormPanel;
import org.compiere.grid.ed.VLookup;
import org.compiere.grid.ed.VNumber;
import org.compiere.minigrid.MiniTable;
import org.compiere.model.MColumn;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MProduct;
import org.compiere.swing.CCheckBox;
import org.compiere.swing.CLabel;
import org.compiere.swing.CPanel;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Language;
import org.compiere.util.Msg;
import org.eevolution.model.MPPProductBOM;
import org.eevolution.model.MPPProductBOMLine;

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
 */
public class VTreeBOM extends TreeBOM implements FormPanel, ActionListener,
		ListSelectionListener, PropertyChangeListener, VetoableChangeListener,
		TreeSelectionListener, TableModelListener
{
	private static final long serialVersionUID = 1L;

	CPanel panel = new CPanel();
	/**
	 *	Tree Maintenance 
	 */
	public VTreeBOM ()
	{
	}	//	VTreeMaintenance

	/**	Window No				*/
	private int         	m_WindowNo = 0;
	/**	FormFrame				*/
	private FormFrame 		m_frame;
	/**	Active Tree				*/
	private JTree		 	m_tree;
	/** Level	**/
	private int m_level = 0;
	/** Level	Limit **/
	private int m_level_limit = 0;


	private static CLogger log = CLogger.getCLogger(VTreeBOM.class);
	private BorderLayout	mainLayout	= new BorderLayout ();
	private CPanel 			northPanel	= new CPanel ();

	private FlowLayout		northLayout	= new FlowLayout ();
	private CLabel			labelProduct	= new CLabel ();
	private VLookup 		fieldProduct;
	private CLabel labelLevel = new CLabel();
	private VNumber fieldLevel = new VNumber("LevelNo", false,
			false, true, DisplayType.Integer, "LevelNo");
	//private CButton			bAddAll		= new CButton (Env.getImageIcon("FastBack24.gif"));
	//private CButton			bAdd		= new CButton (Env.getImageIcon("StepBack24.gif"));
	//private CButton			bDelete		= new CButton (Env.getImageIcon("StepForward24.gif"));
	//private CButton			bDeleteAll	= new CButton (Env.getImageIcon("FastForward24.gif"));
	private CCheckBox		implosion	= new CCheckBox ();
	private CLabel			treeInfo	= new CLabel ();
	//
	private JSplitPane		splitPane	= new JSplitPane ();
	//private VTreePanel		centerTree;
	//private JList			centerList	= new JList ();
	private JScrollPane dataPane = new JScrollPane();
	private JScrollPane treePane = new JScrollPane();
	//private CLabel           labelUOM = new CLabel();
	//private CTextField       fieldUOM = new CTextField(10);
	//private CLabel     labelDocument  = new CLabel();
	//private CTextField  fieldDocument = new CTextField(10);
	//private CLabel     labelRevision  = new CLabel();
	//private CTextField  fieldRevision = new CTextField(8);
	//private CLabel          labelECN  = new CLabel();
	//private CTextField       fieldECN = new CTextField(10);
	//private VDate            dateFrom = new VDate ("DateFrom", false, false, true, DisplayType.Date, Msg.translate(getCtx(), "DateFrom"));
	//private VDate              dateTo = new VDate ("DateTo", false, false, true, DisplayType.Date, Msg.translate(getCtx(), "DateTo"));
	private CPanel southPanel = new CPanel();
	private BorderLayout southLayout = new BorderLayout();
	private ConfirmPanel confirmPanel = new ConfirmPanel(true);
	protected StatusBar statusBar = new StatusBar();

	private MiniTable tableBOM = new MiniTable();
	private Vector<Vector<Object>> dataBOM = new Vector<Vector<Object>>();
	private Vector<String> columnNames;

	private final int DIVIDER_LOCATION = 240;

	
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
			frame.getContentPane().add(panel, BorderLayout.CENTER);
			
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
		Properties ctx = getCtx();
		Language language = Language.getLoginLanguage(); // Base Language
		MLookup m_fieldProduct = MLookupFactory.get(ctx, m_WindowNo,
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
		columnNames.add(Msg.translate(getCtx(), "ValidFrom"));      // 3
		columnNames.add(Msg.translate(getCtx(), "ValidTo"));        // 4
		columnNames.add(Msg.translate(getCtx(), "M_Product_ID"));   // 5
		columnNames.add(Msg.translate(getCtx(), "C_UOM_ID"));       // 6
		columnNames.add(Msg.translate(getCtx(), "IsQtyPercentage"));  // 7                       
		columnNames.add(Msg.translate(getCtx(), "QtyBatch"));   // 8
		columnNames.add(Msg.translate(getCtx(), "QtyBOM"));   		// 9
		columnNames.add(Msg.translate(getCtx(), "IsCritical"));     // 10
		columnNames.add(Msg.translate(getCtx(), "LeadTimeOffset"));       // 11
		columnNames.add(Msg.translate(getCtx(), "Assay"));          // 12
		columnNames.add(Msg.translate(getCtx(), "Scrap"));          // 13
		columnNames.add(Msg.translate(getCtx(), "IssueMethod"));    // 14
		columnNames.add(Msg.translate(getCtx(), "BackflushGroup")); // 15
		columnNames.add(Msg.translate(getCtx(), "Forecast"));       // 16

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
		tableBOM.setColumnClass( 3, Timestamp.class,false);    //  3 ValidFrom
		tableBOM.setColumnClass( 4, Timestamp.class,false);    //  4 ValidTo
		tableBOM.setColumnClass( 5, KeyNamePair.class,false);  //  5 M_Product_ID
		tableBOM.setColumnClass( 6, KeyNamePair.class,false);  //  6 C_UOM_ID
		tableBOM.setColumnClass( 7, Boolean.class,false);      //  7 QtyPorcentage                        
		tableBOM.setColumnClass( 8, BigDecimal.class,false);   //  8 BatchPercent
		tableBOM.setColumnClass( 9, BigDecimal.class,false);   //  9 QtyBOM
		tableBOM.setColumnClass( 10, Boolean.class,false);      // 10 IsCritical                                           
		tableBOM.setColumnClass( 11, BigDecimal.class,false);   // 11 LTOffSet
		tableBOM.setColumnClass( 12, BigDecimal.class,false);   // 12 Assay
		tableBOM.setColumnClass( 13, Integer.class,false);      // 13 Scrap
		tableBOM.setColumnClass( 14, String.class,false);       // 14 IssueMethod
		tableBOM.setColumnClass( 15, String.class,false);       // 15 BackflushGroup
		tableBOM.setColumnClass( 16, BigDecimal.class,false);   // 16 Forecast
		tableBOM.autoSize();

	}   //  dynInit


	/**
	 * 	Static init
	 *	@throws Exception
	 */
	private void jbInit () 
	{

		panel.setLayout (mainLayout);
		panel.setPreferredSize(new Dimension(640, 480));


		//labelUOM.setText (Msg.getElement(getCtx(), "C_UOM_ID"));
		//fieldUOM.setEditable(false);
		//labelDocument.setText (Msg.translate(getCtx(), "Document"));
		//labelRevision.setText (Msg.translate(getCtx(), "Revision"));
		//labelECN.setText (Msg.translate(getCtx(), "ECN"));

		labelProduct.setText (Msg.getElement(getCtx(), "M_Product_ID"));
		//implosion.setEnabled (false);
		implosion.setText (Msg.getElement(getCtx(), "Implosion"));
		//treeInfo.setText (" ");
		//bAdd.setToolTipText("Add to Tree");
		//bAddAll.setToolTipText("Add ALL to Tree");
		//bDelete.setToolTipText("Delete from Tree");
		//bDeleteAll.setToolTipText("Delete ALL from Tree");
		//bAdd.addActionListener(this);
		//bAddAll.addActionListener(this);
		//bDelete.addActionListener(this);
		//bDeleteAll.addActionListener(this);
		northPanel.setLayout (northLayout);
		northLayout.setAlignment (FlowLayout.LEFT);
		//
		//BEGIN AJC E-EVOLUTION 26 OCT 2010
		//this.add (northPanel, BorderLayout.NORTH);
		panel.add (northPanel, BorderLayout.NORTH);
		//END AJC
		northPanel.add (labelProduct, null);
		northPanel.add (fieldProduct, null);
		labelLevel.setText(Msg.translate(Env.getCtx(), "LevelNo"));
		northPanel.add (labelLevel, null);
		northPanel.add (fieldLevel, null);

		northPanel.add (implosion, null);
		//northPanel.add (cbAllNodes, null);
		northPanel.add (treeInfo, null);

		//northPanel.add (labelUOM, null);
		//northPanel.add (fieldUOM, null);
		//northPanel.add (labelDocument, null);
		//northPanel.add (fieldDocument, null);
		//northPanel.add (labelRevision, null);
		//northPanel.add (fieldRevision, null);
		//northPanel.add (fieldECN, null);

		//northPanel.add (bAddAll, null);
		//northPanel.add (bAdd, null);
		//northPanel.add (bDelete, null);
		//northPanel.add (bDeleteAll, null);
		//


		panel.add(southPanel, BorderLayout.SOUTH);

		southPanel.setLayout(southLayout);
		confirmPanel.addActionListener(this);
		southPanel.add(confirmPanel, BorderLayout.SOUTH);

		panel.add (splitPane, BorderLayout.CENTER);
		splitPane.setDividerLocation(DIVIDER_LOCATION);
	}	//	jbInit

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
		// 4Layers - End

		/*else if (e.getSource() == bAddAll)
			action_treeAddAll();
		else if (e.getSource() == bAdd)
			action_treeAdd((ListItem)centerList.getSelectedValue());
		else if (e.getSource() == bDelete)
			action_treeDelete((ListItem)centerList.getSelectedValue());
		else if (e.getSource() == bDeleteAll)
			action_treeDeleteAll();*/
		//super.actionPerformed(e);
	}	//	actionPerformed


	/**
	 * 	Action: Fill Tree with all nodes
	 */
	private void action_loadBOM()
	{
		int M_Product_ID = getM_Product_ID(); 
		if (M_Product_ID == 0)
			return;
		MProduct product = MProduct.get(getCtx(), M_Product_ID);
		DefaultMutableTreeNode parent = new DefaultMutableTreeNode(productSummary(product, false));

		dataBOM.clear();
		m_level = 0;

		if (isImplosion())
		{
			for (MPPProductBOMLine bomline : MPPProductBOMLine.getByProduct(product))
			{
				parent.add(parent(bomline));
			}     
			m_tree = new JTree(parent);
		}
		else
		{
			for (MPPProductBOM bom : MPPProductBOM.getProductBOMs(product))
			{
				parent.add(parent(bom));                    
			}      
			m_tree = new JTree(parent);
		}

		m_tree.addTreeSelectionListener(this);

		treePane.getViewport().add (m_tree, null);

		loadTableBOM();
		dataPane.getViewport().add(tableBOM, null);
		// 4Layers - Set divider location
		splitPane.setDividerLocation(DIVIDER_LOCATION);
		// 4Layers - end
		
	}	//	action_fillTree

	public DefaultMutableTreeNode parent(MPPProductBOMLine bomline) 
	{

		//System.out.println("-------------------------Parent:" + bom.getName());
		MProduct M_Product = MProduct.get(getCtx(), bomline.getM_Product_ID());
		MPPProductBOM bomproduct = new MPPProductBOM(getCtx(), bomline.getPP_Product_BOM_ID(), null);
		DefaultMutableTreeNode parent = new DefaultMutableTreeNode(productSummary(M_Product, false));

		Vector<Object> line = new Vector<Object>(17);
		line.add(Boolean.FALSE);  //  0 Select
		line.add(Boolean.TRUE);   //  1 IsActive
		line.add(Integer.valueOf(bomline.getLine())); // 2 Line                
		line.add( (Timestamp) bomline.getValidFrom()); //  3 ValidDrom
		line.add( (Timestamp) bomline.getValidTo()); //  4 ValidTo
		KeyNamePair pp = new KeyNamePair(M_Product.getM_Product_ID(),M_Product.getName());
		line.add(pp); //  5 M_Product_ID
		KeyNamePair uom = new KeyNamePair(bomline.getC_UOM_ID(),bomline.getC_UOM().getUOMSymbol());
		line.add(uom); //  6 C_UOM_ID
		line.add(Boolean.valueOf(bomline.isQtyPercentage())); //  7 IsQtyPorcentage
		line.add((BigDecimal) bomline.getQtyBatch());  //  8 BatchPercent
		line.add((BigDecimal) ((bomline.getQtyBOM()!=null) ? bomline.getQtyBOM() : new BigDecimal(0)));  //  9 QtyBOM
		line.add(Boolean.valueOf(bomline.isCritical())); //  10 IsCritical                  
		line.add( (Integer) bomline.getLeadTimeOffset()); // 11 LTOffSet
		line.add( (BigDecimal) bomline.getAssay()); // 12 Assay
		line.add( (BigDecimal) (bomline.getScrap())); // 13 Scrap
		line.add( (String) bomline.getIssueMethod()); // 14 IssueMethod
		line.add( (String) bomline.getBackflushGroup());  // 15 BackflushGroup
		line.add( (BigDecimal) bomline.getForecast()); // 16 Forecast
		dataBOM.add(line);

		for (MPPProductBOM bom : MPPProductBOM.getProductBOMs((MProduct) bomproduct.getM_Product()))
		{
			MProduct component = MProduct.get(getCtx(), bom.getM_Product_ID());
			return component(component);
		}
		return parent;
	}

	public DefaultMutableTreeNode parent(MPPProductBOM bom) 
	{

		// System.out.println("Parent:" + bom.getName());
		// X_M_Product product = new X_M_Product(getCtx(), bom.getM_Product_ID(),"M_Product");

		//vparent.setValue(m_product_id);
		DefaultMutableTreeNode parent = new DefaultMutableTreeNode(productSummary(bom)); 
		if(m_level == getLevelNo())
		{
			m_level --;
			return parent;
		}	
			
		for (MPPProductBOMLine bomline : bom.getLines())
		{
			MProduct component = MProduct.get(getCtx(), bomline.getM_Product_ID());
			//System.out.println("Componente :" + component.getValue() + "[" + component.getName() + "]");
			//component(component);
			Vector<Object> line = new Vector<Object>(17);
			line.add(Boolean.FALSE);  //  0 Select
			line.add(Boolean.TRUE);   //  1 IsActive
			line.add(Integer.valueOf(bomline.getLine())); // 2 Line                
			line.add( (Timestamp) bomline.getValidFrom()); //  3 ValidDrom
			line.add( (Timestamp) bomline.getValidTo()); //  4 ValidTo
			KeyNamePair pp = new KeyNamePair(component.getM_Product_ID(),component.getName());
			line.add(pp); //  5 M_Product_ID
			KeyNamePair uom = new KeyNamePair(bomline.getC_UOM_ID(),bomline.getC_UOM().getUOMSymbol());
			line.add(uom); //  6 C_UOM_ID
			line.add(Boolean.valueOf(bomline.isQtyPercentage())); //  7 IsQtyPercentage
			line.add((BigDecimal) bomline.getQtyBatch());  //  8 BatchPercent
			line.add((BigDecimal) bomline.getQtyBOM());  //  9 QtyBom
			line.add(Boolean.valueOf(bomline.isCritical())); //  10 IsCritical       
			line.add( (Integer) bomline.getLeadTimeOffset()); // 11 LTOffSet
			line.add( (BigDecimal) bomline.getAssay()); // 12 Assay
			line.add( (BigDecimal) (bomline.getScrap())); // 13 Scrap
			line.add( (String) bomline.getIssueMethod()); // 14 IssueMethod
			line.add( (String) bomline.getBackflushGroup());  // 15 BackflushGroup
			line.add( (BigDecimal) bomline.getForecast()); // 16 Forecast
			dataBOM.add(line);	
			parent.add(component(component));			
		}
		m_level --;
		return parent;
	}

	public DefaultMutableTreeNode component(MProduct product) 
	{   

		if (isImplosion())
		{
			DefaultMutableTreeNode parent = new DefaultMutableTreeNode(productSummary(product, false));
			for (MPPProductBOMLine bomline : MPPProductBOMLine.getByProduct(product))
			{
				parent.add(parent(bomline));
			}  
			return parent;  
		}
		else
		{				
			for (MPPProductBOM bom : MPPProductBOM.getProductBOMs(product))
			{
				m_level ++;
				return parent(bom);	
			}
			return new DefaultMutableTreeNode(productSummary(product, true));
		}
	}


	public void valueChanged(TreeSelectionEvent event) 
	{
		//currentSelectionField.setText("Current Selection: " +  tree.getLastSelectedPathComponent().toString());
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
	 * 	VTreePanel Changed
	 *	@param e event
	 */
	public void propertyChange (PropertyChangeEvent e)
	{
	}	//	propertyChange

	/**
	 * 	Action: Add Node to Tree
	 */ 
	private void action_treeAdd(ListItem item)
	{
		log.info( "VTreeMaintenance.action_treeAdd " + item);
		if (item != null)
		{
		}
	}	//	action_treeAdd

	/**
	 * 	Action: Delete Node from Tree
	 */
	private void action_treeDelete(ListItem item)
	{
		log.info( "VTreeMaintenance.action_treeDelete" + item);
		if (item != null)
		{
		}
	}	//	action_treeDelete


	/**
	 * 	Action: Add All Nodes to Tree
	 */ 
	private void action_treeAddAll()
	{
		log.info( "VTreeMaintenance.action_treeAddAll");
	}	//	action_treeAddAll

	/**
	 * 	Action: Delete All Nodes from Tree
	 */
	private void action_treeDeleteAll()
	{
		log.info( "VTreeMaintenance.action_treeDeleteAll");
	}

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
	

	
	private boolean isImplosion() {
		return implosion.isSelected();
	}
	
	private int getM_Product_ID() {
		Integer Product = (Integer)fieldProduct.getValue();
		if (Product == null)
			return 0;
		return Product.intValue(); 
	}
	
	protected int getLevelNo() {
		Integer l =  fieldLevel.getValue() != null ? (Integer) fieldLevel.getValue() : 0;
		return l;
	}

	protected void setLevelNo(int level) {
		fieldLevel.setValue(level);
	}

}	//	VTreeMaintenance