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
package org.compiere.apps.search;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;

import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;

import org.adempiere.plaf.AdempierePLAF;
import org.adempiere.plaf.AdempiereTaskPaneUI;
import org.compiere.apps.AEnv;
import org.compiere.apps.ALayout;
import org.compiere.apps.ALayoutConstraint;
import org.compiere.apps.AppsAction;
import org.compiere.apps.ConfirmPanel;
import org.compiere.grid.ed.VCheckBox;
import org.compiere.grid.ed.VComboBox;
import org.compiere.grid.ed.VLookup;
import org.compiere.grid.ed.VPAttribute;
import org.compiere.grid.ed.VPAttributeDialog;
import org.compiere.minigrid.ColumnInfo;
import org.compiere.minigrid.IDColumn;
import org.compiere.minigrid.MiniTable;
import org.compiere.model.GridTab;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MPAttributeLookup;
import org.compiere.model.MProduct;
import org.compiere.model.MQuery;
import org.compiere.swing.CButton;
import org.compiere.swing.CLabel;
import org.compiere.swing.CPanel;
import org.compiere.swing.CTabbedPane;
import org.compiere.swing.CTextArea;
import org.compiere.swing.CTextField;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Util;
import org.jdesktop.swingx.JXTaskPane;


/**
 *	Search Product and return selection
 *
 *  @author     Jorg Janke
 *  @version    $Id: InfoProduct.java,v 1.4 2006/07/30 00:51:27 jjanke Exp $
 * 
 * @author Bogdan Ioan, SC ARHIPAC SERVICE SRL
 * 				<li>FR [ 2012362 ] Info Product: Add Product Category 
 * 
 * @author Michael McKay, 
 * 				<li>ADEMPIERE-72 VLookup and Info Window improvements
 * 					https://adempiere.atlassian.net/browse/ADEMPIERE-72
 */
public class InfoProduct extends Info implements ActionListener, ChangeListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2076229793041196087L;

	/**
	 *	Standard Constructor
	 * 	@param frame frame
	 * 	@param modal modal
	 * 	@param WindowNo window no
	 * 	@param M_Warehouse_ID warehouse
	 * 	@param M_PriceList_ID price list
	 * 	@param value    Query Value or Name if enclosed in @
	 * 	@param multiSelection multiple selections
	 * 	@param whereClause where clause
	 */
	@Deprecated
	public InfoProduct(Frame frame, boolean modal, int WindowNo,
		int M_Warehouse_ID, int M_PriceList_ID, String value,
		boolean multiSelection, String whereClause)
	{
		this(frame, modal, WindowNo,
				M_Warehouse_ID, M_PriceList_ID, 0, value,
				multiSelection, true, whereClause);
	}
	
	/**
	 *	Standard Constructor
	 * 	@param frame frame
	 * 	@param modal modal
	 * 	@param WindowNo window no
	 * 	@param M_Warehouse_ID warehouse
	 * 	@param M_PriceList_ID price list
	 *  @param record_id The record ID to find
	 *  @param value Query Value or Name if enclosed in @
	 * 	@param multiSelection multiple selections
	 *  @param saveResults  True if results will be saved, false for info only
	 * 	@param whereClause where clause
	 */
	public InfoProduct(Frame frame, boolean modal, int WindowNo,
		int M_Warehouse_ID, int M_PriceList_ID, int record_id, String value,
		boolean multiSelection, boolean saveResults, String whereClause)
	{
		super (frame, modal, WindowNo, "p", "M_Product_ID", multiSelection, saveResults, whereClause);
		log.info(value + ", Wh=" + M_Warehouse_ID + ", PL=" + M_PriceList_ID + ", WHERE=" + whereClause);
		setTitle(Msg.getMsg(Env.getCtx(), "InfoProduct"));
		m_M_Warehouse_ID = M_Warehouse_ID;
		m_M_PriceList_ID = M_PriceList_ID;
		//
		statInit();
		initInfo (record_id, value, M_Warehouse_ID, M_PriceList_ID, false);
		p_loadedOK = true;
		//
		int no = this.p_table.getRowCount();
		setStatusLine(Integer.toString(no) + " " + Msg.getMsg(Env.getCtx(), "SearchRows_EnterQuery"), false);
		setStatusDB(Integer.toString(no));
		
		if(autoQuery() || record_id != 0 || (value != null && value.length() > 0 && value != "%"))
			executeQuery();

		pack();
		
		AEnv.positionCenterWindow(frame, this);
		
		//  To get the focus after the table update
		m_heldLastFocus = fieldValue;
		
	}	//	InfoProduct

	/**  Array of Column Info    */
	private static Info_Column[] s_productLayout = null;
	private static int INDEX_PATTRIBUTE = 0;
	
	private Object m_heldLastFocus = null;

	//
	private int fieldID=0;
	private CButton bReset = new CButton();
	private CLabel labelValue = new CLabel();
	private CTextField fieldValue = new CTextField(10);
	private CLabel labelName = new CLabel();
	private CTextField fieldName = new CTextField(10);
	private CLabel labelUPC = new CLabel();
	private CTextField fieldUPC = new CTextField(10);
	private CLabel labelSKU = new CLabel();
	private CTextField fieldSKU = new CTextField(10);
	private CLabel labelPriceList = new CLabel();
	private VLookup fPriceList_ID;
	private CLabel labelWarehouse = new CLabel();
	private VLookup fWarehouse_ID;
	private CLabel labelVendor = new CLabel();
	private VLookup fVendor_ID;
	private VCheckBox checkOnlyStock = new VCheckBox();
	private VCheckBox checkShowDetail = new VCheckBox();
	private CLabel labelProductCategory = new CLabel();
	private VLookup fProductCategory_ID;
	private CLabel labelAS = new CLabel(); // @Trifon
	private VLookup fAS_ID; // @Trifon
	private CLabel labelASI = new CLabel();
	private VPAttribute fASI_ID;
	private VCheckBox checkAND = new VCheckBox();

	
	//Begin - fer_luck @ centuryon
	private CTextArea fieldDescription = new CTextArea();
	private JXTaskPane warehouseStockPanel = new JXTaskPane();
	private CPanel checkPanel = new CPanel();  // Holder for the show detail checkbox
	private CPanel tablePanel = new CPanel();
	private MiniTable warehouseTbl = new MiniTable();
	private String m_sqlWarehouse;
	private MiniTable substituteTbl = new MiniTable();
	private String m_sqlSubstitute;
	private MiniTable relatedTbl = new MiniTable();
	private CTabbedPane jTab  = new CTabbedPane();
	private String m_sqlRelated;
    //Available to Promise Tab
    private Info_Column[]		m_layoutATP = null;
	private MiniTable 			m_tableAtp = null;
	private DefaultTableModel 	m_modelAtp = null;
	private int 				m_M_Product_ID = 0;
	private int					m_M_Warehouse_ID = 0;
	private int 				m_M_PriceList_ID = 0;
	//End - fer_luck @ centuryon

	/** Instance Button				*/
	private CButton		m_PAttributeButton = null;
	/** ASI							*/
	private int			m_M_AttributeSetInstance_ID = -1;
	/** Locator						*/
	private int			m_M_Locator_ID = 0;
	private CPanel 		ppButtonPanel;
	private CPanel 		ppSubPanel;

	/**
	 *	Static Setup - add fields to parameterPanel
	 */
	private void statInit()
	{
		// Listen to changes in the table
		p_table.addPropertyChangeListener("p_table_update", this);
		((ListSelectionModel) p_table.getSelectionModel()).addListSelectionListener(this);
		
		AppsAction aa = new AppsAction (ConfirmPanel.A_RESET, null, "reset");
		bReset = (CButton)aa.getButton();
		bReset.setMargin(ConfirmPanel.s_insets);
		bReset.setSize(bReset.getHeight(), bReset.getHeight());
		Dimension bSize = bReset.getSize();
		bSize.height = bReset.getHeight();
		bSize.width = bReset.getHeight();
		bReset.setMaximumSize(bSize);  // Make it square
		bReset.addActionListener(this);
		
		labelValue.setText(Msg.getMsg(Env.getCtx(), "Value"));
		fieldValue.setBackground(AdempierePLAF.getInfoBackground());
		fieldValue.addActionListener(this);
		
		labelName.setText(Msg.getMsg(Env.getCtx(), "Name"));
		fieldName.setBackground(AdempierePLAF.getInfoBackground());
		fieldName.addActionListener(this);

		labelUPC.setText(Msg.translate(Env.getCtx(), "UPC"));
		fieldUPC.setBackground(AdempierePLAF.getInfoBackground());
		fieldUPC.addActionListener(this);

		labelSKU.setText(Msg.translate(Env.getCtx(), "SKU"));
		fieldSKU.setBackground(AdempierePLAF.getInfoBackground());
		fieldSKU.addActionListener(this);
		
		labelWarehouse.setText(Msg.getMsg(Env.getCtx(), "Warehouse"));
		fWarehouse_ID = new VLookup("M_Warehouse_ID", false, false, true,
				MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, 1151, DisplayType.TableDir));
		fWarehouse_ID.setBackground(AdempierePLAF.getInfoBackground());
		fWarehouse_ID.addActionListener(this);
		
		checkOnlyStock.setText(Msg.getMsg(Env.getCtx(), "OnlyStock"));
		checkOnlyStock.setName("OnlyStock");
		checkOnlyStock.setToolTipText(Msg.getMsg(Env.getCtx(), "OnlyStockTip"));
		checkOnlyStock.setSelected(false); // Info may open when searching for non-stock as well.
		checkOnlyStock.addActionListener(this);

		checkShowDetail.setText(Msg.getMsg(Env.getCtx(), "ShowDetail"));
		checkShowDetail.setName("ShowDetail");
		checkShowDetail.setToolTipText(Msg.getMsg(Env.getCtx(), "ShowAttributeDetails"));
		checkShowDetail.setSelected(false);  
		checkShowDetail.setEnabled(false);   
		checkShowDetail.addActionListener(this);

		checkAND.setText(Msg.getMsg(Env.getCtx(), "SearchAND"));
		checkAND.setName("SearchAND");
		checkAND.setToolTipText(Msg.getMsg(Env.getCtx(), "SearchANDInfo"));
		checkAND.setSelected(true);
		checkAND.addActionListener(this);

		labelPriceList.setText(Msg.getMsg(Env.getCtx(), "PriceListVersion"));
		fPriceList_ID = new VLookup("M_PriceList_Version_ID", false, false, true,
				MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, 2987, DisplayType.TableDir));						
		fPriceList_ID.setBackground(AdempierePLAF.getInfoBackground());
		fPriceList_ID.addActionListener(this);

		labelProductCategory.setText(Msg.translate(Env.getCtx(), "M_Product_Category_ID"));
		fProductCategory_ID = new VLookup("M_Product_Category_ID", false, false, true,
				MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, 2020, DisplayType.TableDir));
		fProductCategory_ID.setBackground(AdempierePLAF.getInfoBackground());
		fProductCategory_ID.addActionListener(this);
		
		// @Trifon
		labelAS.setText(Msg.translate(Env.getCtx(), "M_AttributeSet_ID"));
		fAS_ID = new VLookup("M_Product_Category_ID", false, false, true,
				MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, 8490, DisplayType.TableDir));
		fAS_ID.setBackground(AdempierePLAF.getInfoBackground());
		fAS_ID.addActionListener(this);
		//
		labelASI.setText(Msg.translate(Env.getCtx(), "M_AttributeSetInstance_ID"));
		MPAttributeLookup mpaLookup = new MPAttributeLookup(Env.getCtx(), p_WindowNo);
		fASI_ID = new VPAttribute((GridTab) null, false, false, true, p_WindowNo, mpaLookup, true);
		fASI_ID.setBackground(AdempierePLAF.getInfoBackground());
		fASI_ID.addActionListener(this);

		labelVendor.setText(Msg.translate(Env.getCtx(), "Vendor"));
		fVendor_ID = new VLookup("C_BPartner_ID", false, false, true,
				MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, 2705, DisplayType.Search));
		fVendor_ID.setBackground(AdempierePLAF.getInfoBackground());
		fVendor_ID.addActionListener(this);
		//
		//m_InfoPAttributeButton.setMargin(new Insets(2,2,2,2));
		//m_InfoPAttributeButton.setToolTipText(Msg.getMsg(Env.getCtx(), "InfoPAttribute"));
		//m_InfoPAttributeButton.addActionListener(this);
		
		ppButtonPanel = new CPanel(new FlowLayout(FlowLayout.CENTER));
		ppButtonPanel.add(bReset,BorderLayout.NORTH);
		ppButtonPanel.setMaximumSize(new Dimension(30, 30));
				
		ppSubPanel = new CPanel();
		//	Line 1
		ppSubPanel.setLayout(new ALayout());
		ppSubPanel.add(labelValue, new ALayoutConstraint(0,0));
		ppSubPanel.add(fieldValue, null);
		ppSubPanel.add(labelWarehouse, null);
		ppSubPanel.add(fWarehouse_ID, null);
		ppSubPanel.add(checkOnlyStock, new ALayoutConstraint(0,5));
		//	Line 2
		ppSubPanel.add(labelName, new ALayoutConstraint(1,0));
		ppSubPanel.add(fieldName, null);
		ppSubPanel.add(labelPriceList, null);
		ppSubPanel.add(fPriceList_ID, null);
		ppSubPanel.add(labelAS, null); // @Trifon
		ppSubPanel.add(fAS_ID, null);  // @Trifon
		// Line 3
		ppSubPanel.add(labelUPC, new ALayoutConstraint(2,0));
		ppSubPanel.add(fieldUPC, null);
		ppSubPanel.add(labelProductCategory, null);
		ppSubPanel.add(fProductCategory_ID, null);
		ppSubPanel.add(labelASI, null);
		ppSubPanel.add(fASI_ID, null);
		//  Line 4
		ppSubPanel.add(labelSKU, new ALayoutConstraint(3,0));
		ppSubPanel.add(fieldSKU, null);
		ppSubPanel.add(labelVendor, null);
		ppSubPanel.add(fVendor_ID, null);
		ppSubPanel.add(checkAND, new ALayoutConstraint(3,5));

		ppSubPanel.validate();
		//
		parameterPanel.setLayout(new BorderLayout());
		parameterPanel.add(ppButtonPanel, BorderLayout.WEST);
		parameterPanel.add(ppSubPanel,BorderLayout.CENTER);

		//	Product Attribute Instance
		m_PAttributeButton = ConfirmPanel.createPAttributeButton(true);
		confirmPanel.addButton(m_PAttributeButton);
		m_PAttributeButton.addActionListener(this);
		m_PAttributeButton.setEnabled(false);
		
		//Begin - fer_luck @ centuryon
		//add taskpane
		fieldDescription.setBackground(AdempierePLAF.getInfoBackground());
		fieldDescription.setEditable(false);
		fieldDescription.setPreferredSize(new Dimension(INFO_WIDTH - 100, 100));

        warehouseStockPanel.setTitle(Msg.translate(Env.getCtx(), "WarehouseStock"));
        warehouseStockPanel.setUI(new AdempiereTaskPaneUI());
        warehouseStockPanel.getContentPane().setBackground(new ColorUIResource(251,248,241));
        warehouseStockPanel.getContentPane().setForeground(new ColorUIResource(251,0,0));
        
        ColumnInfo[] s_layoutWarehouse = new ColumnInfo[]{
        		new ColumnInfo(" ", "M_Warehouse_ID", IDColumn.class),
        		new ColumnInfo(Msg.translate(Env.getCtx(), "Warehouse"), "Warehouse", String.class),
        		new ColumnInfo(Msg.translate(Env.getCtx(), "QtyAvailable"), "sum(QtyAvailable)", Double.class),
        		new ColumnInfo(Msg.translate(Env.getCtx(), "QtyOnHand"), "sum(QtyOnHand)", Double.class),
           		new ColumnInfo(Msg.translate(Env.getCtx(), "QtyReserved"), "sum(QtyReserved)", Double.class),
        		new ColumnInfo(Msg.translate(Env.getCtx(), "DocumentNote"), "DocumentNote", String.class)};
        /**	From Clause							*/
        String s_sqlFrom = " M_PRODUCT_STOCK_V ";
        /** Where Clause						*/
        String s_sqlWhere = "M_Product_ID = ?";
        m_sqlWarehouse = warehouseTbl.prepareTable(s_layoutWarehouse, s_sqlFrom, s_sqlWhere, false, "M_PRODUCT_STOCK_V");
		m_sqlWarehouse += " Group By M_Warehouse_ID, Warehouse, documentnote ";
		warehouseTbl.setRowSelectionAllowed(true);
		warehouseTbl.setMultiSelection(false);
		warehouseTbl.addMouseListener(this);
		warehouseTbl.setShowTotals(true);
        warehouseTbl.autoSize();
        
        ColumnInfo[] s_layoutSubstitute = new ColumnInfo[]{
        		new ColumnInfo(Msg.translate(Env.getCtx(), "Warehouse"), "orgname", String.class),
        		new ColumnInfo(
    					Msg.translate(Env.getCtx(), "Value"),
    					"(Select Value from M_Product p where p.M_Product_ID=M_PRODUCT_SUBSTITUTERELATED_V.Substitute_ID)",
    					String.class), 
    			new ColumnInfo(Msg.translate(Env.getCtx(), "Name"), "Name", String.class),
    			new ColumnInfo(Msg.translate(Env.getCtx(), "QtyAvailable"), "QtyAvailable", Double.class),
  	        	new ColumnInfo(Msg.translate(Env.getCtx(), "QtyOnHand"), "QtyOnHand", Double.class),
    	        new ColumnInfo(Msg.translate(Env.getCtx(), "QtyReserved"), "QtyReserved", Double.class),
  	        	new ColumnInfo(Msg.translate(Env.getCtx(), "PriceStd"), "PriceStd", Double.class)};
        s_sqlFrom = "M_PRODUCT_SUBSTITUTERELATED_V";
        s_sqlWhere = "M_Product_ID = ? AND M_PriceList_Version_ID = ? and RowType = 'S'";
        m_sqlSubstitute = substituteTbl.prepareTable(s_layoutSubstitute, s_sqlFrom, s_sqlWhere, false, "M_PRODUCT_SUBSTITUTERELATED_V");
        substituteTbl.setRowSelectionAllowed(false);
        substituteTbl.setMultiSelection(false);
        substituteTbl.addMouseListener(this);
        substituteTbl.setShowTotals(false);
        substituteTbl.autoSize();
        
        ColumnInfo[] s_layoutRelated = new ColumnInfo[]{
        		new ColumnInfo(Msg.translate(Env.getCtx(), "Warehouse"), "orgname", String.class),
        		new ColumnInfo(
    					Msg.translate(Env.getCtx(), "Value"),
    					"(Select Value from M_Product p where p.M_Product_ID=M_PRODUCT_SUBSTITUTERELATED_V.Substitute_ID)",
    					String.class),
    			new ColumnInfo(Msg.translate(Env.getCtx(), "Name"), "Name", String.class),
    			new ColumnInfo(Msg.translate(Env.getCtx(), "QtyAvailable"), "QtyAvailable", Double.class),
  	        	new ColumnInfo(Msg.translate(Env.getCtx(), "QtyOnHand"), "QtyOnHand", Double.class),
    	        new ColumnInfo(Msg.translate(Env.getCtx(), "QtyReserved"), "QtyReserved", Double.class),
  	        	new ColumnInfo(Msg.translate(Env.getCtx(), "PriceStd"), "PriceStd", Double.class)};
        s_sqlFrom = "M_PRODUCT_SUBSTITUTERELATED_V";
        s_sqlWhere = "M_Product_ID = ? AND M_PriceList_Version_ID = ? and RowType = 'R'";
        m_sqlRelated = relatedTbl.prepareTable(s_layoutRelated, s_sqlFrom, s_sqlWhere, false, "M_PRODUCT_SUBSTITUTERELATED_V");
        relatedTbl.setRowSelectionAllowed(false);
        relatedTbl.setMultiSelection(false);
        relatedTbl.addMouseListener(this);
        relatedTbl.setShowTotals(false);
        relatedTbl.autoSize();
        
        //Available to Promise Tab
        initAtpTab();
        m_tableAtp.setRowSelectionAllowed(false);
        m_tableAtp.setMultiSelection(false);
              
        jTab.addTab(Msg.translate(Env.getCtx(), "Warehouse"), new JScrollPane(warehouseTbl));
        jTab.setPreferredSize(new Dimension(INFO_WIDTH, SCREEN_HEIGHT > 600 ? 250 : 105));
        jTab.addTab(Msg.translate(Env.getCtx(), "Description"), new JScrollPane(fieldDescription));
        jTab.addTab(Msg.translate(Env.getCtx(), "Substitute_ID"), new JScrollPane(substituteTbl));
        jTab.addTab(Msg.translate(Env.getCtx(), "RelatedProduct_ID"), new JScrollPane(relatedTbl));
		jTab.addTab (Msg.getMsg(Env.getCtx(), "ATP"), new JScrollPane(m_tableAtp));
		jTab.addChangeListener(this);
        tablePanel.setPreferredSize(new Dimension(INFO_WIDTH, SCREEN_HEIGHT > 600 ? 255 : 110));
        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(jTab, BorderLayout.CENTER);        

        checkPanel.add(checkShowDetail);
        warehouseStockPanel.setCollapsed(true);
        warehouseStockPanel.setLayout(new BorderLayout());
        warehouseStockPanel.add(checkShowDetail, BorderLayout.NORTH);
        warehouseStockPanel.add(tablePanel, BorderLayout.CENTER);
        this.addonPanel.setLayout(new BorderLayout());
        this.addonPanel.add(warehouseStockPanel, BorderLayout.CENTER); // Allow auto resizing of the panel
	}	//	statInit

	//Begin - fer_luck @ centuryon
	/**
	 * 	Refresh Query
	 */
	private void refresh()
	{
		//  Invoke later to not delay events.
		SwingUtilities.invokeLater(new Runnable(){public void run()
		{
	    	int M_PriceList_Version_ID = 0;
	
	    	if (fPriceList_ID.getValue() != null)
	    		M_PriceList_Version_ID = ((Integer) fPriceList_ID.getValue()).intValue();
	
	    	String sql;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
	    	
	    	if (jTab.getSelectedIndex() == 0 || jTab.getSelectedIndex() == 4)
			{
	    		//  Warehouse tab
				sql = m_sqlWarehouse;
		
				log.finest(sql);
				try
				{
					pstmt = DB.prepareStatement(sql, null);
					pstmt.setInt(1, m_M_Product_ID);
					rs = pstmt.executeQuery();
					warehouseTbl.loadTable(rs);
					rs.close();
				}
				catch (Exception e)
				{
					log.log(Level.WARNING, sql, e);
				}
				finally
				{
					DB.close(rs, pstmt);
					rs = null; pstmt = null;
				}
			}
			
	    	if(jTab.getSelectedIndex() == 1)
			{
	    		fieldDescription.setText("");
				//  Description tab
				if(m_M_Product_ID != 0)
				{
					String eol = System.getProperty("line.separator"); 
					MProduct p = MProduct.get(Env.getCtx(), m_M_Product_ID);
					if (p.getDescription() != null && p.getDescription().length() > 0)
						fieldDescription.setText(p.getDescription());
					if (p.getDocumentNote() != null && p.getDocumentNote().length() > 0)
					{
						if (fieldDescription.getText().length() > 0)
							fieldDescription.setText(fieldDescription.getText() + eol + eol + p.getDocumentNote());
						else
							fieldDescription.setText(fieldDescription.getText() + p.getDocumentNote());
					}
				}
				else
					fieldDescription.setText("");
			}
			
	    	if(jTab.getSelectedIndex() == 2)
			{
				//  Substitute tab
				sql = m_sqlSubstitute;
				log.finest(sql);
				try {
					pstmt = DB.prepareStatement(sql, null);
					pstmt.setInt(1, m_M_Product_ID);
					pstmt.setInt(2, M_PriceList_Version_ID);
					rs = pstmt.executeQuery();
					substituteTbl.loadTable(rs);
					rs.close();
				} catch (Exception e) {
					log.log(Level.WARNING, sql, e);
				}
				finally
				{
					DB.close(rs, pstmt);
					rs = null; pstmt = null;
				}
			}
			
	    	if(jTab.getSelectedIndex() == 3)
			{
				//  Related tab
				sql = m_sqlRelated;
				log.finest(sql);
				try {
					pstmt = DB.prepareStatement(sql, null);
					pstmt.setInt(1, m_M_Product_ID);
					pstmt.setInt(2, M_PriceList_Version_ID);
					rs = pstmt.executeQuery();
					relatedTbl.loadTable(rs);
					rs.close();
				} catch (Exception e) {
					log.log(Level.WARNING, sql, e);
				}
				finally
				{
					DB.close(rs, pstmt);
					rs = null; pstmt = null;
				}
			}
	    	
	    	if(jTab.getSelectedIndex() == 4)		
			{
	    		if (warehouseTbl.getRowCount() > 0)
	    			refreshAtpTab();
	    		else
	    		{
	    			clearAtpTab();
	    		}
			}
		}});
	}	//	refresh
	//End - fer_luck @ centuryon
	
	/**
	 *	Dynamic Init
	 *
	 * @param value value
	 * @param M_Warehouse_ID warehouse
	 * @param M_PriceList_ID price list
	 */
	private void initInfo (int record_id, String value, int M_Warehouse_ID, int M_PriceList_ID, boolean reset)
	{
		if (!(record_id == 0) && value != null && value.length() > 0)
		{
			log.severe("Received both a record_id and a value: " + record_id + " - " + value);
		}

		//  In case of reset, clear all parameters to ensure we are at a known starting point.
		if(reset)
		{
			clearParameters();
			p_resetColumns = true;
		}
		//  Set values
        if (!(record_id == 0))  // A record is defined
        {
        	fieldID = record_id;
        	fWarehouse_ID.setValue(new Integer(M_Warehouse_ID).intValue());
        	fPriceList_ID.setValue(findPLV(M_PriceList_ID));

        } 
        else
        {
        	fieldID = 0;
        	
        	String id;
			if (value != null && value.length() > 0) //  The VLookup failed to find uniqueness across the direct access SQL fields
			{
				//  Match the query performed by the VLookup.  See getDirectAccessSQL().
				if (value.startsWith("@") && value.endsWith("@"))
				{
					fieldName.setText(value.substring(1,value.length()-1));
				}
				else
				{
					fieldValue.setText(value);
					fieldName.setText(value);
					fieldUPC.setText(value);
					fieldSKU.setText(value);
				}
				//
				fWarehouse_ID.setValue(0);
	        	//
	        	fPriceList_ID.setValue(0);
	        	//
	        	checkAND.setSelected(false); //  Use OR
	        	
			}
			else
			{
				//  No field or value - the general case
				//  Try to find other criteria in the context
				//  M_Product_ID - only if visible
				id = Env.getContext(Env.getCtx(), p_WindowNo, p_TabNo, "M_Product_ID", true);
				if (id != null && id.length() != 0 && (new Integer(id).intValue() > 0))
				{
					fieldID = new Integer(id).intValue();
				}
				
				id = Env.getContext(Env.getCtx(), p_WindowNo, p_TabNo, "M_PriceList_Version_ID", true);
				if (id != null && id.length() != 0 && (new Integer(id).intValue() > 0))
				{
					fPriceList_ID.setValue(new Integer(id).intValue());
				}
				else
				{	
						//  OK - make a good guess
						fPriceList_ID.setValue(findPLV(M_PriceList_ID));
				}

				//  M_Warehouse_ID - general context
				if(M_Warehouse_ID == 0)
				{
					id = Env.getContext(Env.getCtx(), "#M_Warehouse_ID");
					if (id != null && id.length() != 0 && (new Integer(id).intValue() > 0))
					{
						fWarehouse_ID.setValue(new Integer(id).intValue());
					}
					else 
					{
						id = Env.getContext(Env.getCtx(), p_WindowNo, "M_Warehouse_ID");
						if (id != null && id.length() != 0 && (new Integer(id).intValue() > 0))
						{
							fWarehouse_ID.setValue(new Integer(id).intValue());
						}
					}
				}
				else
				{
		        	fWarehouse_ID.setValue(new Integer(M_Warehouse_ID).intValue());
				}
				
				id = Env.getContext(Env.getCtx(), p_WindowNo, p_TabNo, "C_BPartner_ID", false);
				boolean isSOTrx = "Y".equals(Env.getContext(Env.getCtx(), p_WindowNo, p_TabNo, "IsSOTrx", false));
				if (id != null && id.length() != 0 && (new Integer(id).intValue() > 0) && !isSOTrx)
				{
					fVendor_ID.setValue(new Integer(id).intValue());
				}			
			}
		}

        if (!reset)
        {
        	//  Don't want to repeat this on reset or the query will grow
			//	Create Grid
			StringBuffer where = new StringBuffer();
			where.append("p.IsActive='Y'");
			//  dynamic Where Clause
			if (p_whereClause != null && p_whereClause.length() > 0)
				where.append(" AND ")   //  replace fully qualified name with alias
					.append(Util.replace(p_whereClause, "M_Product.", "p."));
			p_concreteWhereClause = where.toString();
        }
		//
		prepareTable(getProductLayout(),
			getSQLFrom(),
			p_concreteWhereClause,
			getOrderClause());
		p_table.setShowTotals(false);
		
	}	//	initInfo
	
	/**
	 *	Find Price List Version and update context
	 *
	 * @param M_PriceList_ID price list
	 * @return M_PriceList_Version_ID price list version
	 */
	private int findPLV (int M_PriceList_ID)
	{
		Timestamp priceDate = null;
		//	Sales Order Date  - This window - all tabs
		String dateStr = Env.getContext(Env.getCtx(), p_WindowNo, "DateOrdered"); 
		if (dateStr != null && dateStr.length() > 0)
			priceDate = Env.getContextAsDate(Env.getCtx(), p_WindowNo, "DateOrdered");
		else	//	Invoice Date - This window - all tabs
		{
			dateStr = Env.getContext(Env.getCtx(), p_WindowNo, "DateInvoiced");
			if (dateStr != null && dateStr.length() > 0)
				priceDate = Env.getContextAsDate(Env.getCtx(), p_WindowNo, "DateInvoiced");
		}
		//	Today
		if (priceDate == null) 
			priceDate = new Timestamp(System.currentTimeMillis());
		//
		log.config("M_PriceList_ID=" + M_PriceList_ID + " - " + priceDate);
		int retValue = 0;
		String sql = "SELECT plv.M_PriceList_Version_ID, plv.ValidFrom "
			+ "FROM M_PriceList pl, M_PriceList_Version plv "
			+ "WHERE pl.M_PriceList_ID=plv.M_PriceList_ID"
			+ " AND plv.IsActive='Y'"
			+ " AND pl.M_PriceList_ID=? "					//	1
			+ "ORDER BY plv.ValidFrom DESC";
		//	find newest one
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, M_PriceList_ID);
			rs = pstmt.executeQuery();
			while (rs.next() && retValue == 0)
			{
				Timestamp plDate = rs.getTimestamp(2);
				if (!priceDate.before(plDate))
					retValue = rs.getInt(1);
			}
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		//
		return retValue;
	}	//	findPLV
	
	/**************************************************************************
	 *	Construct SQL From Clause
	 *  @return SQL From clause
	 */
	protected String getSQLFrom()
	{
		/** SQL From				*/
		String s_productFrom = "M_Product p";
		
		if (isValidVObject(fPriceList_ID))
		{
			s_productFrom += " LEFT OUTER JOIN (SELECT mpp.M_Product_ID, mpp.M_PriceList_Version_id, mpp.IsActive, mpp.PriceList, mpp.PriceStd, mpp.PriceLimit" 
			+					" FROM M_ProductPrice mpp, M_PriceList_Version mplv "
			+					" WHERE mplv.M_PriceList_Version_ID = mpp.M_PriceList_Version_ID AND mplv.IsActive = 'Y') pr"
			+ " ON (p.M_Product_ID=pr.M_Product_ID AND pr.IsActive='Y')";
		}
		s_productFrom += " LEFT OUTER JOIN M_AttributeSet pa ON (p.M_AttributeSet_ID=pa.M_AttributeSet_ID)"
			+ " LEFT OUTER JOIN M_Product_PO ppo ON (p.M_Product_ID=ppo.M_Product_ID and ppo.IsCurrentVendor='Y' and ppo.IsActive='Y')"
			+ " LEFT OUTER JOIN M_Product_Category pc ON (p.M_Product_Category_ID=pc.M_Product_Category_ID)"
			+ " LEFT OUTER JOIN C_BPartner bp ON (ppo.C_BPartner_ID=bp.C_BPartner_ID)";
		
		return s_productFrom;
	}
	/**************************************************************************
	 *	Construct SQL Where Clause and define parameters
	 *  (setParameters needs to set parameters)
	 *  Includes first AND
	 *  @return SQL WHERE clause
	 */
	protected String getSQLWhere()
	{
		ArrayList<String> list = new ArrayList<String>();
		
		//  => ID
		if(isResetRecordID())
			fieldID = 0;
		if(!(fieldID == 0))
		{
			list.add("p.M_Product_ID = ?");
		}
		
		//  Warehouse - if defined, don't include summary products
		if (fWarehouse_ID.getValue() != null && ((Integer) fWarehouse_ID.getValue()).intValue() != 0)
			list.add("p.IsSummary='N'");

		//  Only Stock items
		if (checkOnlyStock.isSelected())
			list.add("p.isStocked = ?");
		
		//	Optional Price List Version
		if (fPriceList_ID.getValue() != null)
			list.add("pr.M_PriceList_Version_ID=?");
		
		//  Optional Product Category
		if (fProductCategory_ID.getValue() != null) {
			list.add("(p.M_Product_Category_ID=? OR p.M_Product_Category_ID IN "
			+ 		"(SELECT PPC.M_Product_Category_ID FROM M_Product_Category ppc WHERE "
			+		" ppc.M_Product_Category_Parent_ID = ?))");
		}
		
		//  Optional Attribute Set
		if (fAS_ID.getValue() != null) {
			list.add("p.M_AttributeSet_ID=?");
		}
		
		//	Product Attribute Search
		if (fASI_ID.getAttributeWhere() != null)
		{
			String asiWhere = fASI_ID.getAttributeWhere();
			if (asiWhere.length() > 0)
			{
				if (asiWhere.startsWith(" AND "))
					asiWhere = asiWhere.substring(5);
				list.add(asiWhere);
			}
		}

		//  => Value
		if(isValidSQLText(fieldValue))
			list.add("UPPER(p.Value) LIKE ?");

		//  => Name
		if(isValidSQLText(fieldName))
			list.add("UPPER(p.Name) LIKE ?");

		//  => UPC
		if(isValidSQLText(fieldUPC))
			list.add("UPPER(p.UPC) LIKE ?");

		//  => SKU
		if(isValidSQLText(fieldSKU))
			list.add("UPPER(p.SKU) LIKE ?");

		//	=> Vendor
		if (fVendor_ID.getValue() != null)
			list.add("ppo.C_BPartner_ID=?");
		
		StringBuffer sql = new StringBuffer();
		int size = list.size();
		//	Just one
		if (size == 1)
			sql.append(" AND ").append(list.get(0));
		else if (size > 1)
		{
			boolean AND = checkAND.isSelected();
			sql.append(" AND ");
			if (!AND)
				sql.append("(");
			for (int i = 0; i < size; i++)
			{
				if (i > 0)
					sql.append(AND ? " AND " : " OR ");
				sql.append(list.get(i));
			}
			if (!AND)
				sql.append(")");
		}
		
		return sql.toString();
	}	//	getSQLWhere

	/**
	 *  Set Parameters for Query
	 *  (as defined in getSQLWhere)
	 *
	 * @param pstmt pstmt
	 *  @param forCount for counting records
	 * @throws SQLException
	 */
	protected void setParameters(PreparedStatement pstmt, boolean forCount) throws SQLException
	{
		int index = 1;

		//  => Warehouse
		Integer id;
		if (fWarehouse_ID.getValue() != null)
			id = ((Integer) fWarehouse_ID.getValue());
		else
			id = 0;
		if (!forCount)	//	parameters in select
		{
			for (int i = 0; i < p_layout.length; i++)
			{
				if (p_layout[i].getColSQL().indexOf('?') != -1)
					pstmt.setInt(index++, id.intValue());
			}
		}
		log.fine("M_Warehouse_ID=" + id + " (" + (index-1) + "*)");

		//  => ID
		if(!(fieldID == 0))
		{
			pstmt.setInt(index++, fieldID);
			log.fine("Record ID: " + fieldID);
		}

		//  => Only Stocked
		if(checkOnlyStock.isSelected())
		{
			pstmt.setString(index++, "Y");
			log.fine("Only Stocked: " + "Y");
		}
		
		//  => PriceList
		if (fPriceList_ID.getValue() != null)
		{
			id =  ((Integer) fPriceList_ID.getValue());
			pstmt.setInt(index++, id.intValue());
			log.fine("M_PriceList_Version_ID=" + id);
		}
		//  => Product Category
		if (fProductCategory_ID.getValue() != null) {
			id = ((Integer) fProductCategory_ID.getValue());
			pstmt.setInt(index++, id.intValue());
			pstmt.setInt(index++, id.intValue());  //  Done twice - see getWhere()
			log.fine("M_Product_Category_ID=" + id);
		}
		//  => Attribute Set - @Trifon
		if (fAS_ID.getValue() != null) {
			id = ((Integer) fAS_ID.getValue());
			pstmt.setInt(index++, id.intValue());
			log.fine("M_AttributeSet_ID=" + id);
		}
		//	=> AttributeSetInstance where clause
		if (fASI_ID.getAttributeWhere() != null)
		{
			// No parameter needs to be added
		}
		//  => Value
		if (isValidSQLText(fieldValue))
			pstmt.setString(index++, getSQLText(fieldValue));
		//  => Name
		if (isValidSQLText(fieldName))
			pstmt.setString(index++, getSQLText(fieldName));
		//  => UPC
		if (isValidSQLText(fieldUPC))
			pstmt.setString(index++, getSQLText(fieldUPC));
		//  => SKU
		if (isValidSQLText(fieldSKU))
			pstmt.setString(index++, getSQLText(fieldSKU));
		//  => Vendor
		if (fVendor_ID.getValue() != null)
		{
			id = (Integer)fVendor_ID.getValue();
			pstmt.setInt(index++, id.intValue());
			log.fine("fVendor_ID=" + id);
		}

	}   //  setParameters

	
	/**
	 * Property Change Listener
	 * @param e event
	 */
	public void propertyChange(PropertyChangeEvent e)
	{
		// Handle actions if possible or pass the event to the parent class
						
		// Respond to updates to the table
		if (e.getPropertyName() == "p_table_update")
		{
			//  Try to reselect the record
			if(!setSelectedRow(p_selectedRecordKey))		
			{
				//  Nothing was selected, or the query is empty
				//  - close the panel
				m_M_Product_ID = 0;
				warehouseStockPanel.setCollapsed(true);
			}
			else  //  Found and selected the same record or selected the first record
			{
				m_M_Product_ID = getSelectedRowKey();
				refresh();
				warehouseStockPanel.setCollapsed(false);
			}

			setFieldOldValues();  //  Remember

			p_selectedRecordKey = 0;
			p_refreshRequired = false;

			//  Return focus to where it is expected to be
			parameterPanel.requestFocus();
			if (m_heldLastFocus instanceof CTextField)
				((CTextField) m_heldLastFocus).requestFocus();
			if (m_heldLastFocus instanceof VLookup)
				((VLookup) m_heldLastFocus).requestFocus();
			if (m_heldLastFocus instanceof VCheckBox)
				((VCheckBox) m_heldLastFocus).requestFocus();
	
		}
	}
	
	/**************************************************************************
	 *  Action Listener
	 *	@param e event
	 */
	public void actionPerformed (ActionEvent e)
	{
		// Handle actions if possible or pass the event to the parent class

		if(!p_loadedOK)
			return;
		
		boolean triggerRefresh = false;
		
		String cmd = e.getActionCommand();
		Object source = null;
		
		if(e.getSource() != null)
		{
			source = e.getSource();
			
			//  Keep the focus on the source field if the table updates
			m_heldLastFocus = this.getFocusOwner();

			if (cmd.equals(ConfirmPanel.A_OK))
			{
				//  The enter key is mapped to the Ok button which will close the dialog.
				//  Don't let this happen if there are outstanding changes to any of the 
				//  VLookup fields in the criteria
				if (hasOutstandingChanges())
				{
					return;
				}
				else
				{
					// We might close
					triggerRefresh = false;
				}
			} 
			else if (cmd.equals(ConfirmPanel.A_RESET))
			{
				//  Go back to the defaults
				p_loadedOK = false;  // Prevent other actions
				initInfo(0,"",m_M_Warehouse_ID, m_M_PriceList_ID, true);
				p_loadedOK = true;
				if (autoQuery())
				{
					executeQuery();
				}
				return;
			}
			else if (cmd.equals(ConfirmPanel.A_REFRESH))
			{
				//  Refresh always causes a requery in case there are
				//  changes to the underlying tables - even if the 
				//  criteria haven't changed.
				p_resetColumns = true;
				triggerRefresh = true;
			}
			else if (cmd.equals(ConfirmPanel.A_PATTRIBUTE))
			{
				//  Find the ASI used by the product on the lead row
				MProduct mp = MProduct.get(Env.getCtx(), m_M_Product_ID);
				m_M_AttributeSetInstance_ID = mp.getM_AttributeSetInstance_ID();				
				//  Set title and parameters for the PattributeInstance window
				String title = "";
				int wh_id = 0;
				if (isValidVObject(fWarehouse_ID))
				{
					title = fWarehouse_ID.getDisplay() + " - " + mp.getName();
					wh_id = ((Integer) (fWarehouse_ID.getValue())).intValue();
				}
				//  Get the business partner from the context - it may be different than the Vendor
				int bp_id = 0;
				String s_bp_id = Env.getContext(Env.getCtx(), p_WindowNo, p_TabNo, "C_BPartner_ID", false);
				if (s_bp_id != null && s_bp_id.length() != 0 && (new Integer(s_bp_id).intValue() > 0))
					bp_id = new Integer(s_bp_id).intValue();
				//  Display the window
				PAttributeInstance pai = new PAttributeInstance (this, title, 
						wh_id, 0, p_table.getLeadRowKey(), bp_id);
				//  Get the results
				m_M_AttributeSetInstance_ID = pai.getM_AttributeSetInstance_ID();
				m_M_Locator_ID = pai.getM_Locator_ID();
				
				if (m_M_AttributeSetInstance_ID != -1)
					fASI_ID.setValue(m_M_AttributeSetInstance_ID);
				else
					fASI_ID.setValue(0); //  No instance
				
				//  Saving here is confusing with multi-selection
				if (p_saveResults)  //  If the results are saved, we can save now - an ASI is product specific
				{
				//	dispose(p_saveResults);
				//	return;
				}
				
				triggerRefresh = true;
			}
			else if (source instanceof VComboBox)
			{
				if (((VComboBox) source).getParent() instanceof VLookup)
				{
					source = ((VComboBox) source).getParent();
					VLookup vl = ((VLookup)source);
					m_heldLastFocus = vl;
					
					//  Discard changes from mouse movements and keyboard entries
					//  Respond only to the Enter key which causes "comboBoxEdited"
					if(cmd.equals("comboBoxChanged"))
						return;
					else if(cmd.equals("comboBoxEdited"))
					{
						if (!vl.hasChanged())
						{
							vl.requestFocus();
							return;
						}

						triggerRefresh = true;
						
						//  perform field-specific changes
						if (vl == fWarehouse_ID)
						{
							if (!isValidVObject(fWarehouse_ID))
							{
								//  Disable the stock button
								checkOnlyStock.setSelected(false);
								checkOnlyStock.setEnabled(false);
							}
							else
								checkOnlyStock.setEnabled(true);
						}
					}
				}
			}
			else if (source instanceof CTextField)
			{
				CTextField tf = ((CTextField) source);

				if (tf.getParent() instanceof VLookup)
				{
					// Ignore it.  User typed into the VLookup text field.
					return;
				}
				if (tf.getParent() instanceof VPAttribute)
				{
					source = tf.getParent();
					VPAttribute vpa = ((VPAttribute) source);
					m_heldLastFocus = fieldValue;  //  The VPAttribute field can't hold the focus effectively
					
					if (vpa.hasChanged())
					{
						triggerRefresh = true;
					}
				}
				else if (tf.hasChanged())
				{
					triggerRefresh = true;
				}
				else
				{
					// Special case where text fields don't change but cause an event
					// Interpret this as a click of the OK button and close EXCEPT
					// if the dialog was opened from a menu.
					if (p_TabNo == 0)
						return;
					else
						dispose(true);  //  Save the selection and close;
				}
			}
			else if (e.getSource() instanceof VCheckBox)
			{
				//  Check box changes generally always cause a refresh
				//  Capture changes that don't 
				triggerRefresh = true;
				
				VCheckBox cb = (VCheckBox) e.getSource();
				//  ShowDetail check box
				if (cb.getName().equals("ShowDetail"))
				{
					// Refresh only the ATP tab 
					refreshAtpTab();
					return;
				}
				else if (cb.getName().equals("AutoQuery"))
				{
					//  Only trigger a refresh if the check box is selected
					if(!cb.isSelected())
					{
						return;
					}
				}
			}

			// Check if we need to reset the table.  The flag is reset when
			// the table is reset.  The first change triggers the reset.
			p_resetColumns = p_resetColumns || columnIsDynamic(source);

		} //  e.getSource() is null
		
				
		if (triggerRefresh)
		{
			//  A requery will be performed
			prepForRequery(); 
			m_resetRecordID = true;
		}
		//  Pass anything left to the info class
		//  May have to filter explicit action commands
		super.actionPerformed(e);  //  Let the info class decide what to do.
		
		//  Return focus to where it is expected to be
		parameterPanel.requestFocus();
		if (m_heldLastFocus instanceof CTextField)
			((CTextField) m_heldLastFocus).requestFocus();
		if (m_heldLastFocus instanceof VLookup)
			((VLookup) m_heldLastFocus).requestFocus();
		if (m_heldLastFocus instanceof VCheckBox)
			((VCheckBox) m_heldLastFocus).requestFocus();
		
	}   //  actionPerformed

	/**
	 * Watch for changes in the selection of the main table and update the detail table accordingly.
	 */
	public void valueChanged(ListSelectionEvent lse)
	{
    	int leadRowKey = p_table.getLeadRowKey();
    	if (m_M_Product_ID != leadRowKey)
    	{
    		m_M_Product_ID = leadRowKey;  //  From the main table
    		refresh();  		//  Update the warehouseStockPanel with the current selected record
    		enableButtons();	//  Set the buttons accordingly
    	}
    	warehouseStockPanel.setCollapsed(false);
	}

	/**
	 * Prepare for Requery of the table.  If dynamic changes are pending, prepare the table.
	 */
	protected void prepForRequery()
	{
		
		//  A requery will be performed
		this.p_refreshRequired = true;
		
		//  Find what is currently selected
		//  Re-selection of the column happens in the propertyChange listener
		Integer selectedKey = (Integer) getSelectedRowKey();
        if(selectedKey != null && selectedKey.intValue() != 0)
        	this.p_selectedRecordKey = selectedKey.intValue();  
		
		if (this.p_resetColumns)  //  Reset the table
		{
			prepareTable(getProductLayout(),
					getSQLFrom(),
					p_concreteWhereClause,
					getOrderClause());
			this.p_table.setShowTotals(false);
		}
	}
	/**
	 * Determine if the column causes dynamic changes in the table layout
	 * @param o
	 * @return true if changes result
	 */
	protected boolean columnIsDynamic(Object o)
	{
		// List of search fields that cause changes to the table layout
		// See getProductLayout()
		if (o.equals(fPriceList_ID) 		||
			o.equals(fProductCategory_ID) 	||
			o.equals(fWarehouse_ID))
		{
			return true;
		}
		return false;
	}
	/**
	 *  Enable PAttribute if row selected/changed
	 */
	protected void enableButtons ()
	{
		if (m_PAttributeButton != null)
		{
			if (p_table == null)
				return;
			
			int row = p_table.getSelectionModel().getLeadSelectionIndex();
			int rows = p_table.getRowCount();
			if (p_table.getShowTotals())
				rows = rows-1;
			
			int selectedRows = p_table.getSelectedRowCount();
			boolean enabled = false;
			if (row >= 0 && row < rows && selectedRows == 1)
			{
				try
				{
					Object value = p_table.getValueAt(row, INDEX_PATTRIBUTE);
					enabled = Boolean.TRUE.equals(value);
				}
				catch(Exception e)
				{
					enabled = false;
				}
			}
			
			m_PAttributeButton.setEnabled(enabled);
		}
		super.enableButtons();
	}   //  enableButtons

	/**
	 *	Show History
	 */
	protected void showHistory(int record_id)
	{
		log.info("");
		int M_Product_ID = record_id;
		if (M_Product_ID <= 0)
			return;
		int M_Warehouse_ID = ((Integer) fWarehouse_ID.getValue()).intValue();
		int M_AttributeSetInstance_ID = m_M_AttributeSetInstance_ID;
		if (m_M_AttributeSetInstance_ID < 0)	//	not selected
			M_AttributeSetInstance_ID = 0;
		//
		InvoiceHistory ih = new InvoiceHistory (this, 0, 
			M_Product_ID, M_Warehouse_ID, M_AttributeSetInstance_ID);
		ih.setVisible(true);
		ih = null;
	}	//	showHistory

	/**
	 *	Has History
	 *
	 * @return true (has history)
	 */
	protected boolean hasHistory()
	{
		return true;
	}	//	hasHistory

	/**
	 *	Zoom
	 */
	protected void zoom(int record_id)
	{
		log.info("");
		Integer M_Product_ID = record_id;
		if (M_Product_ID == null)
			return;
		
		MQuery query = new MQuery("M_Product");
		query.addRestriction("M_Product_ID", MQuery.EQUAL, M_Product_ID);
		query.setRecordCount(1);
		int AD_WindowNo = getAD_Window_ID("M_Product", true);	//	SO
		zoom (AD_WindowNo, query);
	}	//	zoom

	/**
	 *	Has Zoom
	 *  @return (has zoom)
	 */
	protected boolean hasZoom()
	{
		return true;
	}	//	hasZoom

	/**
	 *	Customize
	 */
	protected void customize()
	{
		log.info("");
	}	//	customize

	/**
	 *	Has Customize
	 *  @return false (no customize)
	 */
	protected boolean hasCustomize()
	{
		return false;	//	for now
	}	//	hasCustomize

	/**
	 *	Save Selection Settings for PriceList
	 */
	protected void saveSelectionDetail()
	{
		//  publish for Callout to read
		Integer ID = getSelectedRowKey();
		Env.setContext(Env.getCtx(), p_WindowNo, Env.TAB_INFO, "M_Product_ID", ID == null ? "0" : ID.toString());
		if (isValidVObject(fPriceList_ID)) // Prevent NPEs
			Env.setContext(Env.getCtx(), p_WindowNo, Env.TAB_INFO, "M_PriceList_Version_ID", ((Integer) fPriceList_ID.getValue()).toString());
		if (isValidVObject(fWarehouse_ID)) // Prevent NPEs
			Env.setContext(Env.getCtx(), p_WindowNo, Env.TAB_INFO, "M_Warehouse_ID", ((Integer) fWarehouse_ID.getValue()).toString());
		//
		if (m_M_AttributeSetInstance_ID == -1)	//	not selected
		{
			Env.setContext(Env.getCtx(), p_WindowNo, Env.TAB_INFO, "M_AttributeSetInstance_ID", "0");
			Env.setContext(Env.getCtx(), p_WindowNo, Env.TAB_INFO, "M_Locator_ID", "0");
		}
		else
		{
			Env.setContext(Env.getCtx(), p_WindowNo, Env.TAB_INFO, "M_AttributeSetInstance_ID", 
				String.valueOf(m_M_AttributeSetInstance_ID));
			Env.setContext(Env.getCtx(), p_WindowNo, Env.TAB_INFO, "M_Locator_ID", 
				String.valueOf(m_M_Locator_ID));
		}
	}	//	saveSelectionDetail

	/**
	 *  Get Product Layout
	 *
	 * @return array of Column_Info
	 */
	protected Info_Column[] getProductLayout()
	{

		ArrayList<Info_Column> list = new ArrayList<Info_Column>();
		list.add(new Info_Column(" ", "p.M_Product_ID", IDColumn.class, false));
		list.add(new Info_Column(Msg.translate(Env.getCtx(), "Discontinued").substring(0, 1), "p.Discontinued", Boolean.class));
		//if (!isValidVObject(fProductCategory_ID) || (isValidVObject(fProductCategory_ID) && !checkAND.isSelected()))
		//{
			list.add(new Info_Column(Msg.translate(Env.getCtx(), "M_Product_Category_ID"), "pc.Name", String.class));
		//}
		list.add(new Info_Column(Msg.translate(Env.getCtx(), "Value"), "p.Value", String.class));
		list.add(new Info_Column(Msg.translate(Env.getCtx(), "Name"), "p.Name", String.class));
		list.add(new Info_Column(Msg.translate(Env.getCtx(), "UPC"), "p.UPC", String.class));
		list.add(new Info_Column(Msg.translate(Env.getCtx(), "SKU"), "p.SKU", String.class));
		if (isValidVObject(fPriceList_ID))
		{
			list.add(new Info_Column(Msg.translate(Env.getCtx(), "PriceList"), "bomPriceList(p.M_Product_ID, pr.M_PriceList_Version_ID) AS PriceList",  BigDecimal.class));
			list.add(new Info_Column(Msg.translate(Env.getCtx(), "PriceStd"), "bomPriceStd(p.M_Product_ID, pr.M_PriceList_Version_ID) AS PriceStd", BigDecimal.class));
			list.add(new Info_Column(Msg.translate(Env.getCtx(), "PriceLimit"), "bomPriceLimit(p.M_Product_ID, pr.M_PriceList_Version_ID) AS PriceLimit", BigDecimal.class));
			list.add(new Info_Column(Msg.translate(Env.getCtx(), "Margin"), "bomPriceStd(p.M_Product_ID, pr.M_PriceList_Version_ID)-bomPriceLimit(p.M_Product_ID, pr.M_PriceList_Version_ID) AS Margin", BigDecimal.class));
		}
		if (isValidVObject(fWarehouse_ID))
		{
			list.add(new Info_Column(Msg.translate(Env.getCtx(), "IsStocked"), "p.isStocked", Boolean.class));
			list.add(new Info_Column(Msg.translate(Env.getCtx(), "QtyAvailable"), "case when p.IsBOM='N' and (p.ProductType!='I' OR p.IsStocked='N') then to_number(get_Sysconfig('QTY_TO_SHOW_FOR_SERVICES', '99999', p.ad_client_id, 0), '99999999999') else bomQtyAvailable(p.M_Product_ID,?,0) end AS QtyAvailable", Double.class, true, true, null));
			list.add(new Info_Column(Msg.translate(Env.getCtx(), "QtyOnHand"), "case when p.IsBOM='N' and (p.ProductType!='I' OR p.IsStocked='N') then to_number(get_Sysconfig('QTY_TO_SHOW_FOR_SERVICES', '99999', p.ad_client_id, 0), '99999999999') else bomQtyOnHand(p.M_Product_ID,?,0) end AS QtyOnHand", Double.class));
			list.add(new Info_Column(Msg.translate(Env.getCtx(), "QtyReserved"), "bomQtyReserved(p.M_Product_ID,?,0) AS QtyReserved", Double.class));
			list.add(new Info_Column(Msg.translate(Env.getCtx(), "QtyOrdered"), "bomQtyOrdered(p.M_Product_ID,?,0) AS QtyOrdered", Double.class));
			if (isUnconfirmed())
			{
				list.add(new Info_Column(Msg.translate(Env.getCtx(), "QtyUnconfirmed"), "(SELECT SUM(c.TargetQty) FROM M_InOutLineConfirm c INNER JOIN M_InOutLine il ON (c.M_InOutLine_ID=il.M_InOutLine_ID) INNER JOIN M_InOut i ON (il.M_InOut_ID=i.M_InOut_ID) WHERE c.Processed='N' AND i.M_Warehouse_ID=? AND il.M_Product_ID=p.M_Product_ID) AS QtyUnconfirmed", Double.class));
				list.add(new Info_Column(Msg.translate(Env.getCtx(), "QtyUnconfirmedMove"), "(SELECT SUM(c.TargetQty) FROM M_MovementLineConfirm c INNER JOIN M_MovementLine ml ON (c.M_MovementLine_ID=ml.M_MovementLine_ID) INNER JOIN M_Locator l ON (ml.M_LocatorTo_ID=l.M_Locator_ID) WHERE c.Processed='N' AND l.M_Warehouse_ID=? AND ml.M_Product_ID=p.M_Product_ID) AS QtyUnconfirmedMove", Double.class));
			}
		}
		list.add(new Info_Column(Msg.translate(Env.getCtx(), "Vendor"), "bp.Name", String.class));
		list.add(new Info_Column(Msg.translate(Env.getCtx(), "IsInstanceAttribute"), "pa.IsInstanceAttribute", Boolean.class));
		//
		s_productLayout = new Info_Column[list.size()];
		list.toArray(s_productLayout);
		//
		INDEX_PATTRIBUTE = s_productLayout.length - 1;	//	last item
		//
		return s_productLayout;
	}   //  getProductLayout
	
	/**
	 *  Get Order Clause
	 *
	 * @return orderClause  "
	 */
	protected String getOrderClause()
	{
		String orderClause = "";
		if (!isValidVObject(fProductCategory_ID))
		{
			orderClause += ", pc.Name";
		}
		
		orderClause += ", Value";
		
		if (isValidVObject(fWarehouse_ID))
		{
			orderClause += ", QtyAvailable DESC";
		}
		if (isValidVObject(fPriceList_ID))
		{
			orderClause += ", Margin DESC";
		}
		if (orderClause.startsWith(", "))
			orderClause = orderClause.substring(2);
		
		return orderClause;
	}
	/**
	 * 	System has Unconfirmed records
	 *	@return true if unconfirmed
	 */
	private boolean isUnconfirmed()
	{
		int no = DB.getSQLValue(null, 
			"SELECT COUNT(*) FROM M_InOutLineConfirm WHERE AD_Client_ID=?", 
			Env.getAD_Client_ID(Env.getCtx()));
		if (no > 0)
			return true;
		no = DB.getSQLValue(null, 
			"SELECT COUNT(*) FROM M_MovementLineConfirm WHERE AD_Client_ID=?", 
			Env.getAD_Client_ID(Env.getCtx()));
		return no > 0;
	}	//	isUnconfirmed
	
	
	/**
	 * 	Tab Changed
	 * 	@param e event
	 */
	public void stateChanged(ChangeEvent e)
	{		
			if(e.getSource() instanceof CTabbedPane)
			{
				CTabbedPane tab = (CTabbedPane) e.getSource();
				
				if(tab.getSelectedIndex() == 4)
				{	
					checkShowDetail.setEnabled(true);
					refreshAtpTab();
				}
				else
				{
					checkShowDetail.setEnabled(false);				
				}
			}
			
	}	//	stateChanged
	
	/**
	 *	Query ATP
	 */
	private void initAtpTab ()
	{
	
		//  Table
		m_tableAtp = new MiniTable();
		m_tableAtp.setRowSelectionAllowed(true);
		m_tableAtp.setMultiSelection(false);
		m_tableAtp.addMouseListener(this);
		m_tableAtp.setShowTotals(false);
		
		//	Header
		ArrayList<Info_Column> list = new ArrayList<Info_Column>();
		list.add(new Info_Column(" ", "M_Product_ID", IDColumn.class));
		list.add(new Info_Column(Msg.translate(Env.getCtx(), "M_Warehouse_ID"), "Warehouse", String.class));
		list.add(new Info_Column(Msg.translate(Env.getCtx(), "M_Locator_ID"), "Locator", String.class));
		list.add(new Info_Column(Msg.translate(Env.getCtx(), "Date"), "Date", Timestamp.class));
		list.add(new Info_Column(Msg.translate(Env.getCtx(), "QtyAvailable"), "QtyAvailable", Double.class));
		list.add(new Info_Column(Msg.translate(Env.getCtx(), "ExpectedChange"), "DeltaQty", Double.class));
		list.add(new Info_Column(Msg.translate(Env.getCtx(), "C_BPartner_ID"), "BP_Name", String.class));
		list.add(new Info_Column(Msg.translate(Env.getCtx(), "QtyOrdered"), "QtyOrdered", Double.class));
		list.add(new Info_Column(Msg.translate(Env.getCtx(), "QtyReserved"), "QtyReserved", Double.class));
		list.add(new Info_Column(Msg.translate(Env.getCtx(), "M_AttributeSetInstance_ID"), "PASI", String.class));
		list.add(new Info_Column(Msg.translate(Env.getCtx(), "DocumentNo"), "DocumentNo", String.class));

		m_layoutATP = new Info_Column[list.size()];
		list.toArray(m_layoutATP);


}	//	initAtpTab

	/**
	 *	Refresh ATP
	 */
	private void refreshAtpTab ()
	{

		boolean showDetail = checkShowDetail.isSelected();
		
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();

		int M_Warehouse_ID = 0;

		// If no warehouse row is selected in the warehouse tab, use the first warehouse
		// row to prevent array index out of bounds. BF 3051361
		if (warehouseTbl.getRowCount() > 0)
		{
			int selectedRow = warehouseTbl.getSelectedRow();
			if (selectedRow<0) selectedRow = 0;
			Object wh_data = warehouseTbl.getValueAt(selectedRow, warehouseTbl.getKeyColumnIndex());
            if (wh_data != null && wh_data instanceof IDColumn)
            {
            	IDColumn dataColumn = (IDColumn) wh_data;
        		M_Warehouse_ID = dataColumn.getRecord_ID();
            }
		} 
		else
			M_Warehouse_ID = m_M_Warehouse_ID;

		if (M_Warehouse_ID == 0)
		{
			// Do nothing and pass blank data to the table
		}
		else  // Update the table
		{
			//	Create the SELECT ..UNION. clause
			//  This is done in-line rather than using prepareTable() so we can add a running sum to the data.
			String sql;
			if (!showDetail)
				sql = "(SELECT s.M_Product_ID, w.Name as warehouse, l.value as locator, 0 as ID, now() as Date,"
					+ " sum(s.QtyOnHand) as AvailQty, null as DeltaQty, null as QtyOrdered, null as QtyReserved,"
					+ " s.PASI," 
					+ " null as ASI,"
					+ " null as BP_Name, null as DocumentNo, 10 as SeqNo";
			else
				sql = "(SELECT s.M_Product_ID, w.Name as warehouse, l.value as locator, s.M_AttributeSetInstance_ID as ID, now() as Date,"
					+ " s.QtyOnHand as AvailQty, null as DeltaQty, null as QtyOrdered, null as QtyReserved,"
					+ " CASE WHEN PASI  = '' THEN '{' || COALESCE(s.M_AttributeSetInstance_ID,0) || '}' ELSE PASI END as PASI," 
					+ " COALESCE(M_AttributeSetInstance_ID,0) as ASI," 
					+ " null as BP_Name, null as DocumentNo,  10 as SeqNo";
			sql += " FROM (SELECT M_Product_ID, M_Locator_ID, QtyOnHand, QtyReserved, QtyOrdered, COALESCE(productAttribute(M_AttributeSetInstance_ID)::varchar, '') as PASI, COALESCE(M_AttributeSetInstance_ID,0) as M_AttributeSetInstance_ID FROM M_Storage) s "
				+ " INNER JOIN M_Locator l ON (s.M_Locator_ID=l.M_Locator_ID)"
				+ " INNER JOIN M_Warehouse w ON (l.M_Warehouse_ID=w.M_Warehouse_ID)"
				+ " WHERE s.M_Product_ID=?";
			if (M_Warehouse_ID != 0)
				sql += " AND l.M_Warehouse_ID=?";
			if (m_M_AttributeSetInstance_ID > 0)
				sql += " AND s.M_AttributeSetInstance_ID=?";
			if (!showDetail)
			{
				sql += " AND (s.QtyOnHand<>0)";
				sql += " GROUP BY s.M_Product_ID, w.Name, l.value, s.M_Locator_ID, PASI ";
			}
			else
			    sql += " AND (s.QtyOnHand<>0 OR s.QtyReserved<>0 OR s.QtyOrdered<>0) ";

			
			sql += "UNION ALL ";
	
			//	Orders
			sql += "SELECT ol.M_Product_ID, w.Name as warehouse, null as locator, ol.M_AttributeSetInstance_ID as ID, o.DatePromised as date,"
				+ " null as AvailQty,"
				+ " CASE WHEN dt.DocBaseType = 'POO' THEN ol.QtyOrdered ELSE -ol.QtyReserved END as DeltaQty,"
				+ " CASE WHEN dt.DocBaseType = 'POO' THEN ol.QtyOrdered ELSE null END as QtyOrdered,"
				+ " CASE WHEN dt.DocBaseType = 'POO' THEN null ELSE ol.QtyReserved END as QtyReserved,"
				+ " productAttribute(ol.M_AttributeSetInstance_ID) as PASI," 
				+ " ol.M_AttributeSetInstance_ID as ASI,"
				+ " bp.Name as BP_Name, dt.PrintName || ' ' || o.DocumentNo As DocumentNo, 20 as SeqNo "
				+ "FROM C_Order o"
				+ " INNER JOIN C_OrderLine ol ON (o.C_Order_ID=ol.C_Order_ID)"
				+ " INNER JOIN C_DocType dt ON (o.C_DocType_ID=dt.C_DocType_ID)"
				+ " INNER JOIN M_Warehouse w ON (ol.M_Warehouse_ID=w.M_Warehouse_ID)"
				+ " INNER JOIN C_BPartner bp  ON (o.C_BPartner_ID=bp.C_BPartner_ID) "
				+ "WHERE ol.QtyReserved<>0"
				+ " AND ol.M_Product_ID=?";
			if (M_Warehouse_ID != 0)
				sql += " AND ol.M_Warehouse_ID=?";
			if (m_M_AttributeSetInstance_ID > 0)
				sql += " AND ol.M_AttributeSetInstance_ID=?";
			sql += " ORDER BY M_Product_ID, SeqNo, ID, date, locator)";
	
			double qty = 0;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				int index = 1;
				pstmt = DB.prepareStatement(sql, null);
				pstmt.setInt(index++, m_M_Product_ID);
				//  Two queries in union - parameters repeat
				if (M_Warehouse_ID != 0)
					pstmt.setInt(index++, M_Warehouse_ID);
				if (m_M_AttributeSetInstance_ID > 0)
					pstmt.setInt(index++, m_M_AttributeSetInstance_ID);
				pstmt.setInt(index++, m_M_Product_ID);
				if (M_Warehouse_ID != 0)
					pstmt.setInt(index++, M_Warehouse_ID);
				if (m_M_AttributeSetInstance_ID > 0)
					pstmt.setInt(index++, m_M_AttributeSetInstance_ID);
				rs = pstmt.executeQuery();
				while (rs.next())
				{
					//  The order of data matches the layout, not the query
					//  M_Product_ID, warehouse, locator, ID, Date, AvailQty, DelataQty, (1..7) 
					//  QtyOrdered, QtyReserved, (8..9)
					//  PASI, ASI, (10..11)
					//  BP_Name, DocumentNo,  SeqNo (12..14)
					Vector<Object> line = new Vector<Object>(9);
					line.add(rs.getInt(1));							//  M_Product_ID
					line.add(rs.getString(2));						//  warehouse
					line.add(rs.getString(3));      				//  Locator
					line.add(rs.getTimestamp(5));					//  Date
					double deltaQty = rs.getDouble(7);
					qty += +rs.getDouble(6) + deltaQty;
					line.add(new Double(qty));  					//  Qty Available
					line.add(new Double(rs.getDouble(7)));			//  Delta Qty
					line.add(rs.getString(12));						//  BPartner
					line.add(new Double(rs.getDouble(8)));  		//  QtyOrdered
					line.add(new Double(rs.getDouble(9)));  		//  QtyReserved
					line.add(rs.getString(10));						//  ASI
					line.add(rs.getString(13));						//  DocumentNo
					data.add(line);
				}
			}
			catch (SQLException e)
			{
				log.log(Level.SEVERE, sql, e);
			}
			finally {
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
		}
		
		//  Update the table
		
		//	Header
		Vector<String> columnNames = new Vector<String>();
		for (int i = 0; i < m_layoutATP.length; i++)
			columnNames.add(m_layoutATP[i].getColHeader());

		m_modelAtp = new DefaultTableModel();
		m_modelAtp.setDataVector(data, columnNames);

		//  Avoid an exception
		SwingUtilities.invokeLater(new Runnable(){public void run(){
			m_tableAtp.setModel(m_modelAtp);
			//  set editors (two steps)
			for (int i = 0; i < m_layoutATP.length; i++)
				m_tableAtp.setColumnClass(i, m_layoutATP[i].getColClass(), m_layoutATP[i].isReadOnly(), m_layoutATP[i].getColHeader());
			m_tableAtp.autoSize();
		}});


	}	//	refreshAtpTab
	
	/**
	 * clearAtpTab() - wipe the ATP table of data
	 */
	private void clearAtpTab()
	{
		m_modelAtp = new DefaultTableModel();
		m_tableAtp.setRowCount(0);
		m_tableAtp.setModel(m_modelAtp);

	}  //  clearAtpTab

	/**
	 * @return selected Attribute Set ID
	 */
	public int getM_AttributeSet_ID() {
		int M_AttributeSet_ID = 0;
		if (fAS_ID.getValue() != null)
			M_AttributeSet_ID = ((Integer) fAS_ID.getValue()).intValue();
		return M_AttributeSet_ID;
	}
	
	/**
	 * Does the parameter panel have outstanding changes that have not been
	 * used in a query?
	 * @return true if there are outstanding changes.
	 */
	private boolean hasOutstandingChanges()
	{
		//  All the tracked fields
		return(
			fieldValue.hasChanged()	||
			fieldName.hasChanged() ||
			fieldUPC.hasChanged() ||
			fieldSKU.hasChanged() ||
			fPriceList_ID.hasChanged() ||
			fWarehouse_ID.hasChanged() ||
			fVendor_ID.hasChanged() ||
			fProductCategory_ID.hasChanged() ||
			fAS_ID.hasChanged() ||
			fASI_ID.hasChanged() ||
			checkOnlyStock.hasChanged() ||
			checkAND.hasChanged());
	}
	/**
	 * Record outstanding changes by copying the current
	 * value to the oldValue on all fields
	 */
	private void setFieldOldValues()
	{
		fieldValue.set_oldValue();
		fieldName.set_oldValue();
		fieldUPC.set_oldValue();
		fieldSKU.set_oldValue();
		fPriceList_ID.set_oldValue();
		fWarehouse_ID.set_oldValue();
		fVendor_ID.set_oldValue();
		fProductCategory_ID.set_oldValue();
		fAS_ID.set_oldValue();
		fASI_ID.set_oldValue();
		checkOnlyStock.set_oldValue();
		checkAND.set_oldValue();
		return;
	}
	
	/**
	 *  Clear all fields and set default values in check boxes
	 */
	private void clearParameters()
	{
		//  Clear fields and set defaults
		fieldValue.setText("");
		fieldName.setText("");
		fieldUPC.setText("");
		fieldSKU.setText("");
    	fWarehouse_ID.setValue(null);
    	fPriceList_ID.setValue(null);
    	fProductCategory_ID.setValue(null);
    	fVendor_ID.setValue(null);
    	fAS_ID.setValue(null);
    	fASI_ID.setValue(null);
    	checkOnlyStock.setValue(false);  	//  Show everything
		checkAND.setSelected(true); 		//  Use AND
	}
}	//	InfoProduct
