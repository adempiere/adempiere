/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2010 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): victor.perez@e-evolution.com, www.e-evolution.com                           *
 *****************************************************************************/

package org.eevolution.form;

import java.beans.PropertyChangeEvent;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;

import org.adempiere.webui.component.Checkbox;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.ListModelTable;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.SimpleTreeModel;
import org.adempiere.webui.component.WListbox;
import org.adempiere.webui.editor.WSearchEditor;
import org.adempiere.webui.event.WTableModelEvent;
import org.adempiere.webui.event.WTableModelListener;
import org.adempiere.webui.panel.ADForm;
import org.adempiere.webui.panel.CustomForm;
import org.adempiere.webui.panel.IFormController;
import org.compiere.model.MColumn;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MProduct;
import org.compiere.util.DisplayType;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Language;
import org.compiere.util.Msg;
import org.eevolution.model.MPPProductBOM;
import org.eevolution.model.MPPProductBOMLine;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.Center;
import org.zkoss.zul.North;
import org.zkoss.zul.South;
import org.zkoss.zul.West;
import org.zkoss.zul.DefaultTreeNode;
import org.zkoss.zul.Space;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treecol;
import org.zkoss.zul.Treecols;




public class WTreeBOM extends TreeBOM implements IFormController, EventListener, WTableModelListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8534705083972399511L;
	
	private int         	m_WindowNo = 0;
	private CustomForm		m_frame = new CustomForm();
	private Tree			m_tree = new Tree();
	private Borderlayout 	mainLayout = new Borderlayout();
	private Panel			northPanel = new Panel();
	private Label			labelProduct = new Label();
	private WSearchEditor   fieldProduct;
	
	private Checkbox		implosion	= new Checkbox ();
	private Label			treeInfo	= new Label ();
	
	
	private Panel dataPane = new Panel();
	private Panel treePane = new Panel();

	private ConfirmPanel confirmPanel = new ConfirmPanel(true);


	private WListbox tableBOM = new WListbox();
	private Vector<Vector<Object>> dataBOM = new Vector<Vector<Object>>();


	
	
	public WTreeBOM(){
		try{
			preInit();
			jbInit ();
		}
		catch(Exception e)
		{
			log.log(Level.SEVERE, "VTreeBOM.init", e);
		}
	}
	
	private void loadTableBOM()
	{

	//  Header Info
		
		Vector<String> columnNames = new Vector<String>();
		
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
		
		tableBOM.clear();

		//  Remove previous listeners
		tableBOM.getModel().removeTableModelListener(this);

		//  Set Model
		ListModelTable model = new ListModelTable(dataBOM);
		model.addTableModelListener(this);
		
		tableBOM.setData(model, columnNames);

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
	
	private void preInit() throws Exception
	{
		Properties ctx = getCtx();
		Language language = Language.getLoginLanguage(); // Base Language
		MLookup m_fieldProduct = MLookupFactory.get(ctx, m_WindowNo,
				MColumn.getColumn_ID(MProduct.Table_Name, "M_Product_ID"),
				DisplayType.Search, language, MProduct.COLUMNNAME_M_Product_ID, 0, false,
				" M_Product.IsSummary = 'N'");
		
		fieldProduct = new WSearchEditor("M_Product_ID", true, false, true, m_fieldProduct)
		{
			public void setValue(Object value) {
				super.setValue(value);
				action_loadBOM();
			}
		};
		
		implosion.addActionListener(this);
	}
	
	private void jbInit()
	{
	
		m_frame.setWidth("99%");
		m_frame.setHeight("100%");
		m_frame.setStyle("position: absolute; padding: 0; margin: 0");
		m_frame.appendChild (mainLayout);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setStyle("position: absolute");
		
		labelProduct.setText (Msg.getElement(getCtx(), "M_Product_ID"));

		implosion.setText (Msg.getElement(getCtx(), "Implosion"));
		
		North north = new North();
		mainLayout.appendChild(north);
		north.appendChild(northPanel);
		north.setHeight("28px");
		northPanel.appendChild(labelProduct);
		northPanel.appendChild(new Space());
		fieldProduct.getComponent().setWidth("20%");
		northPanel.appendChild(fieldProduct.getComponent());
		northPanel.appendChild(new Space());
		northPanel.appendChild(implosion);
		northPanel.appendChild(new Space());
		northPanel.appendChild(treeInfo);
		
		South south = new South();
		mainLayout.appendChild(south);
		south.appendChild(confirmPanel);
		confirmPanel.addActionListener(this);
		
		West west = new West();
		mainLayout.appendChild(west);
		west.setSplittable(true);
		west.appendChild(treePane);
		treePane.appendChild(m_tree);
		m_tree.setStyle("border: none");
		west.setWidth("25%");
		west.setAutoscroll(true);
		
		Center center = new Center();
		mainLayout.appendChild(center);
		center.appendChild(dataPane);
		dataPane.appendChild(tableBOM);
		tableBOM.setVflex(true);
		tableBOM.setFixedLayout(true);
		center.setFlex(true);
		center.setAutoscroll(true);
	}
	
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
		
		if (value == null)
			return;

		//  BPartner
		if (name.equals("M_Product_ID"))
		{
			if (fieldProduct != null)
				action_loadBOM();	
		}		
	}
	
	@Override
	public void onEvent(Event event) throws Exception {
		
		if (event.getTarget().equals(implosion)) 
		{
			action_loadBOM();
		}
		if (event.getName().equals(Events.ON_OK))
		{
			action_loadBOM();
		}
		// 4Layers - Close window when cancel is pressed
		if (event.getName().equals(Events.ON_CANCEL)) 
		{
			dispose();
		}
	}
	
	
	
	private void action_loadBOM()
	{
		int M_Product_ID = getM_Product_ID(); 
		if (M_Product_ID == 0)
			return;
		MProduct product = MProduct.get(getCtx(), M_Product_ID);
		DefaultTreeNode parent = new DefaultTreeNode(productSummary(product, false), new ArrayList());

		dataBOM.clear();

		if (isImplosion())
		{
			try{
				m_tree.setModel(null);
			}catch(Exception e)
			{}
			
			if (m_tree.getTreecols() != null)
				m_tree.getTreecols().detach();
			if (m_tree.getTreefoot() != null)
				m_tree.getTreefoot().detach();
			if (m_tree.getTreechildren() != null)
				m_tree.getTreechildren().detach();
			
			for (MPPProductBOMLine bomline : MPPProductBOMLine.getByProduct(product))
			{
				parent.getChildren().add(parent(bomline));
			} 
			
			Treecols treeCols = new Treecols();
			m_tree.appendChild(treeCols);
			Treecol treeCol = new Treecol();
			treeCols.appendChild(treeCol);
			
			SimpleTreeModel model = new SimpleTreeModel(parent);
			m_tree.setPageSize(-1);
			m_tree.setTreeitemRenderer(model);
			m_tree.setModel(model);
			
		}
		else
		{
			try{
				m_tree.setModel(null);
			}catch(Exception e)
			{}
			
			if (m_tree.getTreecols() != null)
				m_tree.getTreecols().detach();
			if (m_tree.getTreefoot() != null)
				m_tree.getTreefoot().detach();
			if (m_tree.getTreechildren() != null)
				m_tree.getTreechildren().detach();
			for (MPPProductBOM bom : MPPProductBOM.getProductBOMs(product))
			{
				parent.getChildren().add(parent(bom));                    
			} 
			
			Treecols treeCols = new Treecols();
			m_tree.appendChild(treeCols);
			Treecol treeCol = new Treecol();
			treeCols.appendChild(treeCol);
			
			SimpleTreeModel model = new SimpleTreeModel(parent);
			m_tree.setPageSize(-1);
			m_tree.setTreeitemRenderer(model);
			m_tree.setModel(model);
			
		}
		m_tree.addEventListener(Events.ON_SELECTION, this);
		loadTableBOM();
	}
	
	public DefaultTreeNode parent(MPPProductBOMLine bomline) 
	{

		
		MProduct M_Product = MProduct.get(getCtx(), bomline.getM_Product_ID());
		MPPProductBOM bomproduct = new MPPProductBOM(getCtx(), bomline.getPP_Product_BOM_ID(), null);
		DefaultTreeNode parent = new DefaultTreeNode(productSummary(M_Product, false), new ArrayList());

		Vector<Object> line = new Vector<Object>(17);
		line.add( new Boolean(false));  //  0 Select
		line.add( new Boolean(true));   //  1 IsActive
		line.add( new Integer(bomline.getLine())); // 2 Line                
		line.add( (Timestamp) bomline.getValidFrom()); //  3 ValidDrom
		line.add( (Timestamp) bomline.getValidTo()); //  4 ValidTo
		KeyNamePair pp = new KeyNamePair(M_Product.getM_Product_ID(),M_Product.getName());
		line.add(pp); //  5 M_Product_ID
		KeyNamePair uom = new KeyNamePair(bomline.getC_UOM_ID(),bomline.getC_UOM().getUOMSymbol());
		line.add(uom); //  6 C_UOM_ID
		line.add(new Boolean(bomline.isQtyPercentage())); //  7 IsQtyPorcentage
		line.add((BigDecimal) bomline.getQtyBatch());  //  8 BatchPercent
		line.add((BigDecimal) ((bomline.getQtyBOM()!=null) ? bomline.getQtyBOM() : new BigDecimal(0)));  //  9 QtyBOM
		line.add(new Boolean(bomline.isCritical())); //  10 IsCritical                  
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
	
	public DefaultTreeNode parent(MPPProductBOM bom) 
	{
		DefaultTreeNode parent = new DefaultTreeNode(productSummary(bom), new ArrayList()); 

		for (MPPProductBOMLine bomline : bom.getLines())
		{
			MProduct component = MProduct.get(getCtx(), bomline.getM_Product_ID());
			Vector<Object> line = new Vector<Object>(17);
			line.add( new Boolean(false));  //  0 Select
			line.add( new Boolean(true));   //  1 IsActive
			line.add( new Integer(bomline.getLine())); // 2 Line                
			line.add( (Timestamp) bomline.getValidFrom()); //  3 ValidDrom
			line.add( (Timestamp) bomline.getValidTo()); //  4 ValidTo
			KeyNamePair pp = new KeyNamePair(component.getM_Product_ID(),component.getName());
			line.add(pp); //  5 M_Product_ID
			KeyNamePair uom = new KeyNamePair(bomline.getC_UOM_ID(),bomline.getC_UOM().getUOMSymbol());
			line.add(uom); //  6 C_UOM_ID
			line.add(new Boolean(bomline.isQtyPercentage())); //  7 IsQtyPercentage
			line.add((BigDecimal) bomline.getQtyBatch());  //  8 BatchPercent
			line.add((BigDecimal) bomline.getQtyBOM());  //  9 QtyBom
			line.add(new Boolean(bomline.isCritical())); //  10 IsCritical       
			line.add( (Integer) bomline.getLeadTimeOffset()); // 11 LTOffSet
			line.add( (BigDecimal) bomline.getAssay()); // 12 Assay
			line.add( (BigDecimal) (bomline.getScrap())); // 13 Scrap
			line.add( (String) bomline.getIssueMethod()); // 14 IssueMethod
			line.add( (String) bomline.getBackflushGroup());  // 15 BackflushGroup
			line.add( (BigDecimal) bomline.getForecast()); // 16 Forecast
			dataBOM.add(line);
			parent.getChildren().add(component(component));

		}
		return parent;
	}
	
	public DefaultTreeNode component(MProduct product) 
	{   

		if (isImplosion())
		{
			DefaultTreeNode parent = new DefaultTreeNode(productSummary(product, false), new ArrayList());
			for (MPPProductBOMLine bomline : MPPProductBOMLine.getByProduct(product))
			{
				parent.getChildren().add(parent(bomline));
			}  
			return parent;  
		}
		else
		{
			for (MPPProductBOM bom : MPPProductBOM.getProductBOMs(product))
			{
				return parent(bom);
			}  
			return new DefaultTreeNode(productSummary(product, true), new ArrayList());
		}
	}

	private int getM_Product_ID() {
		Integer Product = (Integer)fieldProduct.getValue();
		if (Product == null)
			return 0;
		return Product.intValue(); 
	}
	
	private boolean isImplosion() {
		return implosion.isSelected();
	}
	
	@Override
	public ADForm getForm() {
		return m_frame;
	}

	

	@Override
	public void tableChanged(WTableModelEvent event) {
	}

	
}
