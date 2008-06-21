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
 * Copyright (C) 2003-2007 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 ******************************************************************************/

package org.eevolution.form;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.math.*;
import java.sql.*;
import java.util.*;

import javax.swing.*;
import javax.swing.event.*;

import org.compiere.util.*;
import org.adempiere.plaf.*;
import org.compiere.plaf.*;
import org.compiere.swing.*;
import org.compiere.apps.*;
import org.compiere.minigrid.*;
import org.compiere.grid.ed.*;
import org.compiere.model.*;
import org.compiere.apps.form.*;

import java.util.logging.*;

import org.eevolution.model.*;

/**
 *
 * @author  vpj-cd
 */

public class VOrderReceiptIssue extends CPanel implements FormPanel,
		ActionListener, VetoableChangeListener, ChangeListener,
		ListSelectionListener, TableModelListener, ASyncProcess {

	/**	Window No			*/
	private int m_WindowNo = 0;
	/**	FormFrame			*/
	private FormFrame m_frame;
	private StatusBar statusBar = new StatusBar();

	private int m_AD_Client_ID = Env.getAD_Client_ID(Env.getCtx());
	private int m_AD_Org_ID = Env.getAD_Org_ID(Env.getCtx());
	private MPPOrder m_PP_order = null;
	private static CLogger log = CLogger.getCLogger(VOrderReceiptIssue.class);
	
	private String m_groupBy = "";
	
	// Variables declaration for visual elements - do not modify
	private CPanel mainPanel = new CPanel();
	private CPanel Generate = new CPanel();
	private CPanel PanelBottom = new CPanel();
	private CPanel PanelCenter = new CPanel();
	private CPanel northPanel = new CPanel();
	private CButton Process = new CButton();
	private CPanel ReceiptIssueOrder = new CPanel();
	private javax.swing.JTabbedPane TabsReceiptsIssue = new JTabbedPane();
	private VPAttribute attribute = null;
	private CLabel attributeLabel = new CLabel();
	private VNumber orderedQty = new VNumber("QtyOrdered", false, false, false, DisplayType.Quantity, "QtyOrdered");
	private CLabel orderedQtyLabel = new CLabel();
	private VNumber deliveredQty = new VNumber("QtyDelivered", false, false, false, DisplayType.Quantity, "QtyDelivered");
	private CLabel deliveredQtyLabel = new CLabel();
	private VNumber openQty = new VNumber("QtyOpen", false, false, false, DisplayType.Quantity, "QtyOpen");
	private CLabel openQtyLabel = new CLabel();
	private VNumber toDeliverQty = new VNumber("QtyToDeliver", true, false, true, DisplayType.Quantity, "QtyToDeliver");
	private CLabel toDeliverQtyLabel = new CLabel();
	private javax.swing.JScrollPane issueScrollPane = new JScrollPane();
	private MiniTable issue = new MiniTable();
	private VDate movementDate = new VDate("MovementDate", true, false, true, DisplayType.Date, "MovementDate");
	private CLabel movementDateLabel = new CLabel();
	private VLookup order = null;
	private CLabel orderLabel = new CLabel();
	private VNumber rejectQty = new VNumber("Qtyreject", false, false, true, DisplayType.Quantity, "QtyReject");
	private CLabel rejectQtyLabel = new CLabel();
	private VLookup resource = null;
	private CLabel resourceLabel = new CLabel();
	private VLookup warehouse = null;
	private CLabel warehouseLabel = new CLabel();
	private VNumber scrapQty = new VNumber("Qtyscrap", false, false, true, DisplayType.Quantity, "Qtyscrap");
	private CLabel scrapQtyLabel = new CLabel();
	private CLabel backflushGroupLabel = new CLabel(Msg.translate(Env.getCtx(), "BackflushGroup"));
	private CTextField backflushGroup = new CTextField(10);
	private CLabel producLabel = new CLabel(Msg.translate(Env.getCtx(),"M_Product_ID"));
	private VLookup product = null;
	private CLabel uomLabel = new CLabel(Msg.translate(Env.getCtx(), "C_UOM_ID"));
	private VLookup uom = null;
	private CLabel uomorderLabel = new CLabel(Msg.translate(Env.getCtx(), "Altert UOM"));
	private VLookup uomorder = null;
	private CLabel locatorLabel = new CLabel(Msg.translate(Env.getCtx(), "M_Locator_ID"));
	private VLocator locator = null;
	private CLabel labelcombo = new CLabel(Msg.translate(Env.getCtx(), "DeliveryRule"));
	private VComboBox pickcombo = new VComboBox();
	private CLabel QtyBatchsLabel = new CLabel();
	private VNumber qtyBatchs = new VNumber("QtyBatchs", false, false, false, DisplayType.Quantity, "QtyBatchs");
	private CLabel QtyBatchSizeLabel = new CLabel();
	private VNumber qtyBatchSize = new VNumber("QtyBatchSize", false, false, false, DisplayType.Quantity, "QtyBatchSize");
	private CTextPane info = new CTextPane();
	
	/**
	 *	Initialize Panel
	 *  @param WindowNo window
	 *  @param frame frame
	 */
	public void init(int WindowNo, FormFrame frame) {
		m_WindowNo = WindowNo;
		m_frame = frame;
		log.info("VOrderReceipIssue.init - WinNo=" + m_WindowNo
				+ "AD_Client_ID=" + m_AD_Client_ID + ", AD_Org_ID="
				+ m_AD_Org_ID);

		try {
			//	UI
			fillPicks();
			jbInit();
			//
			dynInit();
			pickcombo.addActionListener(this);
			frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
			frame.getContentPane().add(statusBar, BorderLayout.SOUTH);
		} catch (Exception e) {
			log.log(Level.SEVERE, "VOrderReceipIssue.init", e);
		}
	} //	init

	/**
	 *	Fill Picks
	 *		Column_ID from C_Order
	 *	This is only run as part of the windows initialisation process
	 *  @throws Exception if Lookups cannot be initialized
	 */
	private void fillPicks() throws Exception {

		Properties ctx = Env.getCtx();

		Language language = Language.getLoginLanguage(); // Base Language
		MLookup orderL = MLookupFactory.get(ctx, m_WindowNo,
				MColumn.getColumn_ID(MPPOrder.Table_Name, "PP_Order_ID"),
				DisplayType.Search, language, "PP_Order_ID", 0, false,
				"PP_Order.DocStatus = '" + MPPOrder.DOCACTION_Complete + "'");

		order = new VLookup("PP_Order_ID", false, false, true, orderL);
		order.setBackground(AdempierePLAF.getInfoBackground());
		order.addVetoableChangeListener(this);

		MLookup resourceL = MLookupFactory.get(ctx, m_WindowNo, 0, MColumn
				.getColumn_ID(MPPOrder.Table_Name, "S_Resource_ID"), DisplayType.TableDir);
		resource = new VLookup("S_Resource_ID", false, false, false, resourceL);

		MLookup warehouseL = MLookupFactory.get(ctx, m_WindowNo, 0, MColumn
				.getColumn_ID(MPPOrder.Table_Name, "M_Warehouse_ID"), DisplayType.TableDir);
		warehouse = new VLookup("M_Warehouse_ID", false, false, false, warehouseL);

		MLookup productL = MLookupFactory.get(ctx, m_WindowNo, 0, MColumn
				.getColumn_ID(MPPOrder.Table_Name, "M_Product_ID"), DisplayType.TableDir);
		product = new VLookup("M_Product_ID", false, false, false, productL);

		MLookup uomL = MLookupFactory.get(ctx, m_WindowNo, 0, MColumn
				.getColumn_ID(MPPOrder.Table_Name, "C_UOM_ID"), DisplayType.TableDir);
		uom = new VLookup("C_UOM_ID", false, false, false, uomL);

		MLookup uomorderL = MLookupFactory.get(ctx, m_WindowNo, 0, MColumn
				.getColumn_ID(MPPOrder.Table_Name, "C_UOM_ID"), DisplayType.TableDir);
		uomorder = new VLookup("C_UOM_ID", false, false, false, uomorderL);

		MLocatorLookup locatorL = new MLocatorLookup(ctx, m_WindowNo);
		locator = new VLocator("M_Locator_ID", true, false, true, locatorL, m_WindowNo);

		MPAttributeLookup attributeL = new MPAttributeLookup(ctx, m_WindowNo);
		attribute = new VPAttribute(false, false, true, m_WindowNo, attributeL);
		attribute.setValue(new Integer(0));
		//  Tab, Window
		int m_Window = MWindow.getWindow_ID("Manufacturing Order");
		GridFieldVO vo = GridFieldVO.createStdField(ctx, m_WindowNo, 0,
				m_Window, MTab.getTab_ID(m_Window, "Order"), false, false, false);
		// M_AttributeSetInstance_ID
		vo.AD_Column_ID = MColumn.getColumn_ID(MPPOrder.Table_Name, "M_AttributeSetInstance_ID");
		GridField field = new GridField(vo);
		attribute.setField(field);
		// 4Layers - Further init
		scrapQty.setValue(Env.ZERO);
		rejectQty.setValue(Env.ZERO);
		// 4Layers - end
		pickcombo.addItem(new KeyNamePair(1, Msg.translate(Env.getCtx(),"IsBackflush")));
		pickcombo.addItem(new KeyNamePair(2, Msg.translate(Env.getCtx(),"OnlyIssue")));
		pickcombo.addItem(new KeyNamePair(3, Msg.translate(Env.getCtx(),"OnlyReceipt")));
		pickcombo.addActionListener(this);
		Process.addActionListener(this);
		toDeliverQty.addActionListener(this);
		scrapQty.addActionListener(this);
	} //	fillPicks
	
	/**
	 *  Static Init.
	 *  Places static visual elements into the window.
	 *  This is only run as part of the windows initialization process
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
	private void jbInit() throws Exception {
		setLayout(new java.awt.BorderLayout());
		mainPanel.setLayout(new java.awt.BorderLayout());
		ReceiptIssueOrder.setLayout(new java.awt.BorderLayout());
		PanelCenter.setLayout(new java.awt.BorderLayout());
		issueScrollPane.setBorder(new javax.swing.border.TitledBorder(""));
		issueScrollPane.setViewportView(issue);
		PanelCenter.add(issueScrollPane, java.awt.BorderLayout.CENTER);
		ReceiptIssueOrder.add(PanelCenter, java.awt.BorderLayout.CENTER);
		Process.setText(Msg.translate(Env.getCtx(), "OK"));
		PanelBottom.add(Process);
		ReceiptIssueOrder.add(PanelBottom, java.awt.BorderLayout.SOUTH);
		northPanel.setLayout(new java.awt.GridBagLayout());
		orderLabel.setText(Msg.translate(Env.getCtx(), "PP_Order_ID"));
		
		northPanel.add(orderLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.EAST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5), 0, 0));
		northPanel.add(order, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));
		resourceLabel.setText(Msg.translate(Env.getCtx(), "S_Resource_ID"));
		northPanel.add(resourceLabel, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.EAST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5), 0, 0));
		northPanel.add(resource, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));
		warehouseLabel.setText(Msg.translate(Env.getCtx(), "M_Warehouse_ID"));
		northPanel.add(warehouseLabel, new GridBagConstraints(4, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.EAST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5), 0, 0));
		northPanel.add(warehouse, new GridBagConstraints(5, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));
		northPanel.add(warehouseLabel, new GridBagConstraints(4, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.EAST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5), 0, 0));

		// Product

		northPanel.add(producLabel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
				GridBagConstraints.EAST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5), 0, 0));
		northPanel.add(product, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));
		northPanel.add(uomLabel, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,
				GridBagConstraints.EAST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5), 0, 0));
		northPanel.add(uom, new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));
		northPanel.add(uomorderLabel, new GridBagConstraints(4, 2, 1, 1, 0.0, 0.0,
				GridBagConstraints.EAST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5), 0, 0));
		northPanel.add(uomorder, new GridBagConstraints(5, 2, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));
		orderedQtyLabel.setText(Msg.translate(Env.getCtx(), "QtyOrdered"));
		northPanel.add(orderedQtyLabel, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
				GridBagConstraints.EAST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5), 0, 0));
		northPanel.add(orderedQty, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));
		deliveredQtyLabel.setText(Msg.translate(Env.getCtx(), "QtyDelivered"));
		northPanel.add(deliveredQtyLabel, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0,
				GridBagConstraints.EAST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5), 0, 0));
		northPanel.add(deliveredQty, new GridBagConstraints(3, 3, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));
		openQtyLabel.setText(Msg.translate(Env.getCtx(), "QtyOpen"));
		northPanel.add(openQtyLabel, new GridBagConstraints(4, 3, 1, 1, 0.0, 0.0,
				GridBagConstraints.EAST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5), 0, 0));
		northPanel.add(openQty, new GridBagConstraints(5, 3, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));
		QtyBatchsLabel.setText(Msg.translate(Env.getCtx(), "QtyBatchs"));
		northPanel.add(QtyBatchsLabel, new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0,
				GridBagConstraints.EAST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5), 0, 0));
		northPanel.add(qtyBatchs, new GridBagConstraints(3, 4, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));
		QtyBatchSizeLabel.setText(Msg.translate(Env.getCtx(), "QtyBatchSize"));
		northPanel.add(QtyBatchSizeLabel, new GridBagConstraints(4, 4, 1, 1, 0.0, 0.0,
				GridBagConstraints.EAST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5), 0, 0));
		northPanel.add(qtyBatchSize, new GridBagConstraints(5, 4, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));
		northPanel.add(labelcombo, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0,
				GridBagConstraints.EAST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5), 0, 0));
		northPanel.add(pickcombo, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0,
				GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));
		toDeliverQtyLabel.setText(Msg.translate(Env.getCtx(), "QtyToDeliver"));
		northPanel.add(toDeliverQtyLabel, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0,
				GridBagConstraints.EAST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5), 0, 0));
		northPanel.add(toDeliverQty, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));
		scrapQtyLabel.setText(Msg.translate(Env.getCtx(), "QtyScrap"));
		northPanel.add(scrapQtyLabel, new GridBagConstraints(2, 6, 1, 1, 0.0, 0.0,
				GridBagConstraints.EAST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5), 0, 0));
		northPanel.add(scrapQty, new GridBagConstraints(3, 6, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));
		rejectQtyLabel.setText(Msg.translate(Env.getCtx(), "QtyReject"));
		northPanel.add(rejectQtyLabel, new GridBagConstraints(4, 6, 1, 1, 0.0, 0.0,
				GridBagConstraints.EAST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5), 0, 0));
		northPanel.add(rejectQty, new GridBagConstraints(5, 6, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));
		movementDateLabel.setText(Msg.translate(Env.getCtx(), "MovementDate"));
		northPanel.add(movementDateLabel, new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0,
				GridBagConstraints.EAST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5), 0, 0));
		northPanel.add(movementDate, new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));
		locatorLabel.setText(Msg.translate(Env.getCtx(), "M_Locator_ID"));
		northPanel.add(locatorLabel, new GridBagConstraints(2, 7, 1, 1, 0.0, 0.0,
				GridBagConstraints.EAST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5), 0, 0));
		northPanel.add(locator, new GridBagConstraints(3, 7, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));
		attributeLabel.setText(Msg.translate(Env.getCtx(), "M_AttributeSetInstance_ID"));
		northPanel.add(attributeLabel, new GridBagConstraints(4, 7, 1, 1, 0.0, 0.0,
				GridBagConstraints.EAST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5), 0, 0));
		northPanel.add(attribute, new GridBagConstraints(5, 7, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));
		northPanel.add(backflushGroupLabel, new GridBagConstraints(4, 5, 1, 1, 0.0, 0.0,
				GridBagConstraints.EAST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5), 0, 0));
		northPanel.add(backflushGroup, new GridBagConstraints(5, 5, 1, 1, 0.0, 0.0,
				GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));

		ReceiptIssueOrder.add(northPanel, java.awt.BorderLayout.NORTH);
		TabsReceiptsIssue.add(ReceiptIssueOrder, Msg.translate(Env.getCtx(), "IsShipConfirm"));
		TabsReceiptsIssue.add(Generate, Msg.translate(Env.getCtx(), "Generate"));
		Generate.setLayout(new BorderLayout());
		Generate.add(info, BorderLayout.CENTER);
		Generate.setEnabled(false);
		info.setBackground(AdempierePLAF.getFieldBackground_Inactive());
		info.setEditable(false);
		TabsReceiptsIssue.addChangeListener(this);
		add(TabsReceiptsIssue, java.awt.BorderLayout.CENTER);
		mainPanel.add(TabsReceiptsIssue, java.awt.BorderLayout.CENTER);
		add(mainPanel, java.awt.BorderLayout.NORTH);
	} //  jbInit    

	/**
	 *  Dynamic Init.
	 *  Table Layout, Visual, Listener
	 *  This is only run as part of the windows initialization process
	 */
	private void dynInit() {
		disableToDeliver();
		issue.addColumn("PP_OrderBOMLine_ID"); //0
		issue.addColumn("IsCritical"); //1
		issue.addColumn("Value"); //2
		issue.addColumn("M_Product_ID"); //3
		issue.addColumn("C_UOM_ID"); //4
		issue.addColumn("M_AttributeSetInstance_ID"); //5      
		issue.addColumn("QtyRequiered"); //6   
		issue.addColumn("QtyDelivered"); //7
		issue.addColumn("QtyToDeliver"); //8
		issue.addColumn("QtyScrap"); //9                
		issue.addColumn("QtyOnHand"); //10               
		issue.addColumn("QtyReserved"); //11
		issue.addColumn("QtyAvailable"); //12
		issue.addColumn("M_Locator_ID"); //13       
		issue.addColumn("M_Warehouse_ID"); //14                                               
		issue.addColumn("QtyBom"); //15
		issue.addColumn("IsQtyPercentage"); //16
		issue.addColumn("QtyBatch"); //17

		issue.setMultiSelection(true);
		issue.setRowSelectionAllowed(true);

		//  set details
		issue.setColumnClass(0, IDColumn.class, false, " ");
		issue.setColumnClass(1, Boolean.class, true, Msg.translate(Env.getCtx(), "IsCritical"));
		issue.setColumnClass(2, String.class, true, Msg.translate(Env.getCtx(), "Value"));
		issue.setColumnClass(3, KeyNamePair.class, true, Msg.translate(Env.getCtx(), "M_Product_ID"));
		issue.setColumnClass(4, KeyNamePair.class, true, Msg.translate(Env.getCtx(), "C_UOM_ID"));
		issue.setColumnClass(5, String.class, true, Msg.translate(Env.getCtx(), "M_AttributeSetInstance_ID"));
		issue.setColumnClass(6, BigDecimal.class, true, Msg.translate(Env.getCtx(), "QtyRequired"));
		issue.setColumnClass(7, BigDecimal.class, true, Msg.translate(Env.getCtx(), "QtyDelivered"));
		issue.setColumnClass(8, BigDecimal.class, false, Msg.translate(Env.getCtx(), "QtyToDeliver"));
		issue.setColumnClass(9, BigDecimal.class, false, Msg.translate(Env.getCtx(), "QtyScrap"));
		issue.setColumnClass(10, BigDecimal.class, true, Msg.translate(Env.getCtx(), "QtyOnHand"));
		issue.setColumnClass(11, BigDecimal.class, true, Msg.translate(Env.getCtx(), "QtyReserved"));
		issue.setColumnClass(12, BigDecimal.class, true, Msg.translate(Env.getCtx(), "QtyAvailable"));
		issue.setColumnClass(13, String.class, true, Msg.translate(Env.getCtx(), "M_Locator_ID"));
		issue.setColumnClass(14, KeyNamePair.class, true, Msg.translate(Env.getCtx(), "M_Warehouse_ID"));
		issue.setColumnClass(15, BigDecimal.class, true, Msg.translate(Env.getCtx(), "QtyBom"));
		issue.setColumnClass(16, Boolean.class, true, Msg.translate(Env.getCtx(), "IsQtyPercentage"));
		issue.setColumnClass(17, BigDecimal.class, true, Msg.translate(Env.getCtx(), "QtyBatch"));
		
		issue.autoSize();
		issue.getModel().addTableModelListener(this);

		CompiereColor.setBackground(this);
		issue.setCellSelectionEnabled(true);
		issue.getSelectionModel().addListSelectionListener(this);
		issue.setRowCount(0);
	} //  dynInit

	/**
	 * Called when events occur in the window
	 */
	public void actionPerformed(ActionEvent e) {
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		
		log.fine("VOrderReceiptIssue.actionPerformed Evet:" + e.getSource());
		if (e.getActionCommand().equals(ConfirmPanel.A_CANCEL)) {
			dispose();
			return;
		}

		if (e.getSource().equals(Process)) {
			if (movementDate.getValue() == null) {
				JOptionPane.showMessageDialog(null, Msg.getMsg(Env.getCtx(), "NoDate"), "Info", JOptionPane.INFORMATION_MESSAGE);
				return;
			}

			if ((isOnlyReceipt() || isBackflush()) && locator.getValue() == null) {
				JOptionPane.showMessageDialog(null, Msg.getMsg(Env.getCtx(), "NoLocator"), "Info", JOptionPane.INFORMATION_MESSAGE);
				return;
			}

			//  Switch Tabs
			TabsReceiptsIssue.setSelectedIndex(1);
			
			generateSummaryTable();
			
			if (ADialog.ask(m_WindowNo, this, "Update")) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				
				if (cmd_process()) {
					dispose();
					return;
				}
				setCursor(Cursor.getDefaultCursor());
			}
			TabsReceiptsIssue.setSelectedIndex(0);
		}

		if (e.getSource().equals(toDeliverQty) || e.getSource().equals(scrapQty)) {
			if (order.getValue() != null && isBackflush()) {
				executeQuery();
			}
		}

		if (e.getSource().equals(pickcombo)) {
			if (isOnlyReceipt()) {
				disableToDeliver();
				locatorLabel.setVisible(true);
				locator.setVisible(true);
				attribute.setVisible(true);
				attributeLabel.setVisible(true);
				issue.setVisible(false);
			}

			if (isOnlyIssue()) {
				disableToDeliver();
				locatorLabel.setVisible(false);
				locator.setVisible(false);
				attribute.setVisible(false);
				attributeLabel.setVisible(false);
				issue.setVisible(true);
				executeQuery();
			}
			
			if (isBackflush()) {
				enableToDeliver();
				locatorLabel.setVisible(true);
				locator.setVisible(true);
				attribute.setVisible(true);
				attributeLabel.setVisible(true);
				issue.setVisible(true);
				executeQuery();
			}
			toDeliverQty.setValue(openQty.getValue()); //reset toDeliverQty to openQty
		}
	}
	
	public void enableToDeliver() {
		setToDeliver(true);
	}

	public void disableToDeliver() {
		setToDeliver(false);
	}
	
	private void setToDeliver(Boolean state) {
		toDeliverQty.getComponent(0).setEnabled(state); //text boxcomponenet
		toDeliverQty.getComponent(1).setEnabled(state); //button component
		scrapQtyLabel.setVisible(state);
		scrapQty.setVisible(state);
		rejectQtyLabel.setVisible(state);
		rejectQty.setVisible(state);
	}

	/**
	 *  Queries for and fills the table in the lower half of the screen
	 *  This is only run if isBackflush() or isOnlyIssue
	 */
	private void executeQuery() {
		issue.removeAll();
		
		StringBuffer sql = new StringBuffer("SELECT obl.PP_Order_BOMLine_ID,obl.IsCritical,p.Value,");
		sql.append("obl.M_Product_ID,p.Name,p.C_UOM_ID,u.Name,obl.QtyRequiered,obl.QtyReserved,");
		sql.append("bomQtyAvailable(obl.M_Product_ID,obl.M_Warehouse_ID,0 ) AS QtyAvailable,");
		sql.append("bomQtyOnHand(obl.M_Product_ID,obl.M_Warehouse_ID,0) AS QtyOnHand,p.M_Locator_ID,");
		sql.append("obl.M_Warehouse_ID,w.Name,obl.QtyBom,obl.isQtyPercentage,obl.QtyBatch,");
		sql.append("obl.ComponentType,obl.QtyRequiered - QtyDelivered AS QtyOpen, obl.QtyDelivered");
		sql.append(" FROM PP_Order_BOMLine obl");
		sql.append(" INNER JOIN M_Product p ON (obl.M_Product_ID = p.M_Product_ID) ");
		sql.append(" INNER JOIN C_UOM u ON (p.C_UOM_ID = u.C_UOM_ID) ");
		sql.append(" INNER JOIN M_Warehouse w ON (w.M_Warehouse_ID = obl.M_Warehouse_ID) ");
		sql.append(" WHERE obl.PP_Order_ID = " + order.getValue());
		sql.append(" ORDER BY bomQtyOnHand(obl.M_Product_ID,obl.M_Warehouse_ID,0) ");

		log.log(Level.INFO, "VOrderReciptIssue.executeQuery - SQL", sql.toString());
		//  reset table
		int row = 0;
		issue.setRowCount(row);
		//  Execute
		try {
			PreparedStatement pstmt = DB.prepareStatement(sql.toString(), null);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				//  extend table
				issue.setRowCount(row + 1);
				//  set values
				//issue.
				IDColumn   id                    = new IDColumn(rs.getInt(1));
				BigDecimal m_QtyBom              = rs.getBigDecimal(15);
				Boolean    m_QtyPercentage       = rs.getString(16).equals("Y");
				BigDecimal m_QtyBatch            = rs.getBigDecimal(17);
				String     m_ComponentType       = rs.getString(18);
				BigDecimal m_onhand              = rs.getBigDecimal(11);
				BigDecimal m_toDeliverQty        = (BigDecimal) toDeliverQty.getValue();
				BigDecimal m_openQty             = (BigDecimal) openQty.getValue();
				BigDecimal m_scrapQty            = (BigDecimal) scrapQty.getValue();
				BigDecimal m_rejectQty           = (BigDecimal) rejectQty.getValue();
				BigDecimal componentToDeliverQty = Env.ZERO;
				BigDecimal componentScrapQty     = Env.ZERO;
				BigDecimal componentQtyReq       = Env.ZERO;
				BigDecimal componentQtytoDel     = Env.ZERO;
				

				if (m_scrapQty == null)
					m_scrapQty = Env.ZERO;

				if (m_rejectQty == null)
					m_rejectQty = Env.ZERO;

				id.setSelected(isOnlyReceipt());

				issue.setValueAt(id, row, 0);                                                // PP_OrderBOMLine_ID
				issue.setValueAt(rs.getString(2).equals("Y"), row, 1);                       // IsCritical
				issue.setValueAt(rs.getString(3), row, 2);                                   // Product's Search key
				issue.setValueAt(new KeyNamePair(rs.getInt(4), rs.getString(5)), row, 3);    // Product
				issue.setValueAt(new KeyNamePair(rs.getInt(6), rs.getString(7)), row, 4);    // UOM
				issue.setValueAt(rs.getBigDecimal(8), row, 6);                               // QtyRequiered
				issue.setValueAt(rs.getBigDecimal(20), row, 7);                              // QtyDelivered
				issue.setValueAt(m_onhand, row, 10);                                         // OnHand
				issue.setValueAt(rs.getBigDecimal(9), row, 11);                              // QtyReserved
				issue.setValueAt(rs.getBigDecimal(10), row, 12);                             // QtyAvailable
				issue.setValueAt(new KeyNamePair(rs.getInt(13), rs.getString(14)), row, 14); // Warehouse
				issue.setValueAt(m_QtyBom, row, 15);                                         // QtyBom
				issue.setValueAt(m_QtyPercentage, row, 16);                                  // isQtyPercentage
				issue.setValueAt(m_QtyBatch, row, 17);                                       // QtyBatch
				

				if (m_ComponentType.equals(MPPProductBOMLine.COMPONENTTYPE_Component)
						|| m_ComponentType.equals(MPPProductBOMLine.COMPONENTTYPE_Packing)) {
					
					// If the there is product on hand and product is required the product should be selected
					if (m_onhand.compareTo(Env.ZERO) > 0 && rs.getBigDecimal(8).compareTo(Env.ZERO) > 0)
						id.setSelected(true);
					else
						id.setSelected(false);

					if (m_QtyPercentage) {
						// If the quantity of product is calculated as a percentage
						VNumber viewToDeliverQty = new VNumber();
						viewToDeliverQty.setDisplayType(DisplayType.Number);

						if (isBackflush()) {	
							// Is Backflush - Calculate Component from Qty To Deliver
							viewToDeliverQty.setValue(m_toDeliverQty.multiply(
									m_QtyBatch.divide(
											new BigDecimal(100.00), 4,BigDecimal.ROUND_HALF_UP)
									)
							);

							componentToDeliverQty = (BigDecimal) viewToDeliverQty.getValue();
							
							if (rs.getBigDecimal(8).compareTo(Env.ZERO) == 0)
								componentToDeliverQty = Env.ZERO;
							
							if (componentToDeliverQty.compareTo(Env.ZERO) != 0) {
								componentQtyReq = m_toDeliverQty.multiply(
										m_QtyBatch.divide(
												new BigDecimal(100.00), 8, BigDecimal.ROUND_HALF_UP));
								
								componentQtytoDel = componentToDeliverQty.divide(
										Env.ONE, 4, BigDecimal.ROUND_HALF_UP);
								
								//  QtyRequiered
								issue.setValueAt(m_toDeliverQty.multiply(
										m_QtyBatch.divide(
												new BigDecimal(100.00), 8,BigDecimal.ROUND_HALF_UP)),
												row, 6);
								
								//  QtyToDelivery
								issue.setValueAt(componentToDeliverQty.divide(
										Env.ONE, 8, BigDecimal.ROUND_HALF_UP),
										row, 8);

							}
						} else {
							// Only Issue - Calculate Component from Open Qty
							componentToDeliverQty = rs.getBigDecimal(19);
							if (componentToDeliverQty.compareTo(Env.ZERO) != 0) {
								componentQtyReq = m_openQty.multiply(m_QtyBatch
										.divide(new BigDecimal(100.00), 4,
												BigDecimal.ROUND_HALF_UP));
								componentQtytoDel = componentToDeliverQty
										.divide(Env.ONE, 4,
												BigDecimal.ROUND_HALF_UP);
								issue.setValueAt(componentToDeliverQty.divide(
										Env.ONE, 8, BigDecimal.ROUND_HALF_UP),
										row, 8); //  QtyToDelivery
								issue.setValueAt(m_openQty.multiply(m_QtyBatch
										.divide(new BigDecimal(100.00), 8,
												BigDecimal.ROUND_HALF_UP)),
										row, 6); //  QtyRequiered
							}
						}

						if (m_scrapQty.compareTo(Env.ZERO) != 0) {
							VNumber viewScrapQty = new VNumber();
							viewScrapQty.setDisplayType(DisplayType.Number);
							viewScrapQty.setValue(m_scrapQty
									.multiply(m_QtyBatch.divide(
											new BigDecimal(100.00), 8,
											BigDecimal.ROUND_HALF_UP)));
							componentScrapQty = (BigDecimal) viewScrapQty.getValue();
							if (componentScrapQty.compareTo(Env.ZERO) != 0) {
								issue.setValueAt(componentScrapQty.divide(
										Env.ONE, 8, BigDecimal.ROUND_HALF_UP),
										row, 9); //  QtyToDelivery
							}
						}
					} else {
						// Normal Calculate Qty
						VNumber viewToDeliverQty = new VNumber();
						viewToDeliverQty.setDisplayType(DisplayType.Number);
 
						if (isBackflush()) {
							//Is Backflush - Calculate Component from Qty To Deliver
							viewToDeliverQty.setValue(m_toDeliverQty.multiply(m_QtyBom));
							componentToDeliverQty = (BigDecimal) viewToDeliverQty.getValue();
							if (componentToDeliverQty.compareTo(Env.ZERO) != 0) {
								componentQtyReq = m_toDeliverQty
										.multiply(m_QtyBom);
								componentQtytoDel = componentToDeliverQty;
								issue.setValueAt(componentQtyReq, row, 6); //  QtyRequiered
								issue.setValueAt(componentToDeliverQty, row, 8); //  QtyToDelivery
							}
						} else {
							//Only Issue - Calculate Component from Open Qty
							componentToDeliverQty = rs.getBigDecimal(19);
							if (componentToDeliverQty.compareTo(Env.ZERO) != 0) {
								componentQtyReq = m_openQty.multiply(m_QtyBom);
								componentQtytoDel = componentToDeliverQty;
								issue.setValueAt(componentQtyReq, row, 6); //  QtyRequiered
								issue.setValueAt(componentToDeliverQty, row, 8); //  QtyToDelivery
							}
						}

						if (m_scrapQty.compareTo(Env.ZERO) != 0) {
							VNumber viewScrapQty = new VNumber();
							viewScrapQty.setDisplayType(DisplayType.Number);
							viewScrapQty.setValue(m_scrapQty.multiply(m_QtyBom));
							componentScrapQty = (BigDecimal) viewScrapQty.getValue();
							if (componentScrapQty.compareTo(Env.ZERO) != 0) {
								issue.setValueAt(componentScrapQty, row, 9); //  ScrapQty
							}
						}

					}
				} else if (m_ComponentType.equals(MPPProductBOMLine.COMPONENTTYPE_Tools)) {
					VNumber viewToDeliverQty = new VNumber();
					viewToDeliverQty.setDisplayType(DisplayType.Number);
					viewToDeliverQty.setValue(m_QtyBom);

					componentToDeliverQty = (BigDecimal) viewToDeliverQty.getValue();
					if (componentToDeliverQty.compareTo(Env.ZERO) != 0) {
						componentQtyReq = m_QtyBom;
						componentQtytoDel = componentToDeliverQty;
						issue.setValueAt(m_QtyBom, row, 6); //  QtyRequiered
						issue.setValueAt(componentToDeliverQty, row, 8); //  QtyToDelivery
					}
				}
				row++;

				if (isOnlyIssue() || isBackflush())
					row = row + lotes(rs.getInt(4), row, id, rs.getInt(13), componentQtyReq, componentQtytoDel);

				//  prepare next

			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			log.log(Level.SEVERE, "VOrderReceipIssue.executeQuery", e);
		}
		issue.autoSize();
	} //  executeQuery
	
	/**
	 * Extension to Execute Query
	 * Adds instance products to the table in the lower half of the screen
	 * 
	 */
	private int lotes(int M_Product_ID, int row, IDColumn id, int Warehouse_ID, BigDecimal qtyreq, BigDecimal qtytodel) {
		int haslot = 0;
		BigDecimal reql = Env.ZERO;
		reql = qtyreq;
		StringBuffer sql = new StringBuffer("SELECT s.M_Product_ID , s.QtyOnHand, s.M_AttributeSetInstance_ID, p.Name, masi.Description, l.Value, w.Value, w.M_warehouse_ID,p.Value ");
		sql.append("  FROM M_Storage s ");
		sql.append(" INNER JOIN M_Product p ON (s.M_Product_ID = p.M_Product_ID) ");
		sql.append(" INNER JOIN C_UOM u ON (u.C_UOM_ID = p.C_UOM_ID) ");
		sql.append(" INNER JOIN M_AttributeSetInstance masi ON (masi.M_AttributeSetInstance_ID = s.M_AttributeSetInstance_ID) ");
		sql.append(" INNER JOIN M_Warehouse w ON (w.M_Warehouse_ID = " + Warehouse_ID + ") ");
		sql.append(" Inner Join M_Locator l ON(l.M_Warehouse_ID=w.M_Warehouse_ID and s.M_Locator_ID=l.M_Locator_ID) ");
		sql.append(" WHERE s.M_Product_ID = " + M_Product_ID + " and s.QtyOnHand > 0 ");
		sql.append(" and s.M_AttributeSetInstance_ID <> 0 ");
		sql.append(" Order by s.Created ");

		log.log(Level.INFO, "VOrderReciptIssue.executeQuery - SQL", sql.toString());
		//  reset table
		//  Execute
		try {
			PreparedStatement pstmt1 = DB.prepareStatement(sql.toString(), null);
			ResultSet rs1 = pstmt1.executeQuery();
			//
			while (rs1.next()) {

				issue.setRowCount(row + 1);

				IDColumn id1 = new IDColumn(rs1.getInt(3));
				id1.setSelected(false);
				issue.setRowSelectionAllowed(true);
				issue.setValueAt(id1, row, 0); //  M_AttributeSetInstance_ID
				KeyNamePair m_productkey = new KeyNamePair(rs1.getInt(1), rs1
						.getString(4));
				issue.setValueAt(m_productkey, row, 3);
				issue.setValueAt(rs1.getBigDecimal(2), row, 10); // Onhand

				issue.setValueAt(rs1.getString(5), row, 5); //attribute
				issue.setValueAt(rs1.getString(6), row, 13); // Locator Value
				KeyNamePair m_warehousekey = new KeyNamePair(rs1.getInt(8), rs1.getString(7));

				issue.setValueAt(m_warehousekey, row, 14);
				if (reql.compareTo(rs1.getBigDecimal(2)) < 0) {
					if (reql.compareTo(Env.ZERO) <= 0)
						issue.setValueAt(Env.ZERO, row, 6);
					else
						issue.setValueAt(reql, row, 6);
					reql = reql.subtract(rs1.getBigDecimal(2));
				} else {

					issue.setValueAt(rs1.getBigDecimal(2), row, 6);
					reql = reql.subtract(rs1.getBigDecimal(2));
				}

				haslot++;
				row++;
			}
			rs1.close();
			pstmt1.close();
		} catch (SQLException el) {
			log.log(Level.SEVERE, "VOrderReceipIssue.lotes", el);
		}
		return haslot;
	}

	public void dispose() {
		if (m_frame != null)
			m_frame.dispose();
		m_frame = null;
	}

	public void vetoableChange(PropertyChangeEvent e) throws PropertyVetoException {
		String name = e.getPropertyName();
		Object value = e.getNewValue();
		log.fine("VOrderReceip.vetoableChange - " + name + "=" + value);
		if (value == null)
			return;

		//  PP_Order_ID
		if (name.equals("PP_Order_ID")) {
			order.setValue(value);

			if (order.getValue() != null) {
				Integer PP_Order_ID = (Integer) order.getValue();
				m_PP_order = new MPPOrder(Env.getCtx(), PP_Order_ID.intValue(), null);
				resource.setValue(new Integer(m_PP_order.getS_Resource_ID()));
				warehouse.setValue(new Integer(m_PP_order.getM_Warehouse_ID()));
				deliveredQty.setValue(m_PP_order.getQtyDelivered());
				orderedQty.setValue(m_PP_order.getQtyOrdered());
				//m_PP_order.getQtyOrdered().subtract(m_PP_order.getQtyDelivered());
				qtyBatchs.setValue(m_PP_order.getQtyBatchs());
				qtyBatchSize.setValue(m_PP_order.getQtyBatchSize());
				openQty.setValue(m_PP_order.getQtyOrdered().subtract(m_PP_order.getQtyDelivered()));
				toDeliverQty.setValue(openQty.getValue());
				product.setValue(new Integer(m_PP_order.getM_Product_ID()));
				MProduct m_product = new MProduct(Env.getCtx(), m_PP_order.getM_Product_ID(), null);
				uom.setValue(new Integer(m_product.getC_UOM_ID()));
				uomorder.setValue(new Integer(m_PP_order.getC_UOM_ID()));
				Integer m_product_id = (Integer) product.getValue();
				Env.setContext(Env.getCtx(), m_WindowNo, "M_Product_ID", m_product_id.intValue());
				attribute.setValue(new Integer(m_PP_order.getM_AttributeSetInstance_ID()));
				pickcombo.setSelectedIndex(0);  //default to first entry - isBackflush
			}
		} //  PP_Order_ID
	}
	
	/**
	 * Performs what ever task is attached to the combo box
	 * TODO Split this up so its easier to understand
	 * @return Whether the process was successful or not
	 */
	private boolean cmd_process() {
		if (isOnlyReceipt() || isBackflush()) {
			if (locator.getValue() == null)
				JOptionPane.showMessageDialog(null, Msg.getMsg(Env.getCtx(),"NoLocator"), "Info", JOptionPane.INFORMATION_MESSAGE);
		}
		
		if (m_PP_order == null || movementDate.getValue() == null) {
			return false;
		}

		Timestamp m_movementDate = (Timestamp) movementDate.getValue();
		BigDecimal m_toDeliverQty = (BigDecimal) toDeliverQty.getValue();
		int m_M_Location_ID = 0;

		int m_M_Product_ID = 0;
		Timestamp minGuaranteeDate = m_movementDate;
		BigDecimal m_qtyToDeliver = Env.ZERO;
		BigDecimal m_scrapQtyComponent = Env.ZERO;

		boolean iscompleteqtydeliver = false;

		if (isBackflush() || isOnlyIssue()) {
			// check available on hand 
			for (int i = 0; i < issue.getRowCount(); i++) {
				IDColumn id = (IDColumn) issue.getValueAt(i, 0);
				if (id != null && id.isSelected()) {

					KeyNamePair m_productkey = (KeyNamePair) issue.getValueAt(i, 3);

					m_M_Product_ID = m_productkey.getKey();
					
					if (issue.getValueAt(i, 8) != null && !issue.getValueAt(i, 8).toString().equals("")) {
						BigDecimal mnb = new BigDecimal(issue.getValueAt(i, 8).toString());
						m_qtyToDeliver = mnb;
					}
					if (issue.getValueAt(i, 9) != null && !issue.getValueAt(i, 9).toString().equals("")) {
						BigDecimal mnbs = new BigDecimal(issue.getValueAt(i, 9).toString());
						m_scrapQtyComponent = mnbs;
					}

					if (m_qtyToDeliver == null)
						m_qtyToDeliver = Env.ZERO;

					if (m_scrapQtyComponent == null)
						m_scrapQtyComponent = Env.ZERO;

					Properties ctx = Env.getCtx();
					MStorage[] storages = null;
					MProduct product = new MProduct(ctx, m_M_Product_ID, null);
					if (product != null && product.get_ID() != 0
							&& product.isStocked()) {
						MProductCategory pc = MProductCategory.get(ctx,
								product.getM_Product_Category_ID());
						String MMPolicy = pc.getMMPolicy();
						if (MMPolicy == null || MMPolicy.length() == 0) {
							MClient client = MClient.get(ctx);
							MMPolicy = client.getMMPolicy();
						}
						storages = MStorage.getWarehouse(Env.getCtx(),
								m_PP_order.getM_Warehouse_ID(),
								m_M_Product_ID,
								m_PP_order.getM_AttributeSetInstance_ID(),
								product.getM_AttributeSet_ID(),
								true, minGuaranteeDate,
								MClient.MMPOLICY_FiFo.equals(MMPolicy), null);
					}

					int M_AttributeSetInstance_ID = 1;

					if (issue.getValueAt(i, 2) == null && id.isSelected()) {
						M_AttributeSetInstance_ID = ((Integer) id
								.getRecord_ID()).intValue();
					}
					MAttributeSetInstance minst = null;
					if (M_AttributeSetInstance_ID == 1) {
						BigDecimal toIssue = m_qtyToDeliver
								.add(m_scrapQtyComponent);
						for (int k = 0; k < storages.length; k++) {
							MStorage storage = storages[k];
							//	TODO Selection of ASI

							if (storage.getQtyOnHand().compareTo(Env.ZERO) == 0)
								continue;

							BigDecimal issueact = toIssue;
							if (issueact.compareTo(storage.getQtyOnHand()) > 0)
								issueact = storage.getQtyOnHand();

							toIssue = toIssue.subtract(issueact);

							minst = new MAttributeSetInstance(Env.getCtx(),
									storage.getM_AttributeSetInstance_ID(), null);
							
							issueact.setScale(4, BigDecimal.ROUND_HALF_UP);

							if (toIssue.compareTo(Env.ZERO) <= 0)
								break;
						}

					} else {
						minst = new MAttributeSetInstance(Env.getCtx(),
								M_AttributeSetInstance_ID, null);
						BigDecimal qtydelivered = m_qtyToDeliver;
						qtydelivered.setScale(4, BigDecimal.ROUND_HALF_UP);
						qtydelivered = Env.ZERO;
					}

					BigDecimal onHand = Env.ZERO;
					for (int j = 0; j < storages.length; j++) {
						MStorage storage = storages[j];
						onHand = onHand.add(storage.getQtyOnHand());
					}

					iscompleteqtydeliver = onHand.compareTo(m_qtyToDeliver.add(m_scrapQtyComponent)) >= 0;

					if (!iscompleteqtydeliver)
						break;

				}
			}

			if (!iscompleteqtydeliver) {
				ADialog.error(m_WindowNo, this, "NoQtyAvailable");
				return false;
			}
			
			if ((isBackflush() || isOnlyIssue()) && iscompleteqtydeliver) {

				for (int ok = 0; ok < issue.getRowCount(); ok++) {

					IDColumn id = (IDColumn) issue.getValueAt(ok, 0);
					if (id != null && id.isSelected()) {

						KeyNamePair m_productkey = (KeyNamePair) issue
								.getValueAt(ok, 3);

						m_M_Product_ID = m_productkey.getKey();

						int PP_Order_BOMLine_ID = 0;
						int M_AttributeSetInstance_ID = 1;
						if (issue.getValueAt(ok, 2) == null
								&& id.isSelected()) {
							M_AttributeSetInstance_ID = ((Integer) id
									.getRecord_ID()).intValue();

							StringBuffer sql = new StringBuffer(
									"SELECT obl.PP_Order_BOMLine_ID");
							sql.append("  FROM PP_Order_BOMLine obl ");

							sql.append(" WHERE obl.M_Product_ID = "
									+ m_M_Product_ID
									+ " and obl.PP_Order_ID="
									+ order.getValue());

							log.log(Level.INFO,
									"VOrderReciptIssue.executeQuery - SQL Orderbom of instances ",
									sql.toString());
							//  reset table
							//  Execute
							try {
								PreparedStatement pstmt1 = DB
										.prepareStatement(sql.toString(),
												null);
								ResultSet rs1 = pstmt1.executeQuery();
								//
								while (rs1.next()) {
									PP_Order_BOMLine_ID = rs1.getInt(1);
								}
								rs1.close();
								pstmt1.close();
							} catch (SQLException obl) {
							}
						} else if (id.isSelected()
								&& issue.getValueAt(ok, 2) != null) {
							PP_Order_BOMLine_ID = ((Integer) id
									.getRecord_ID()).intValue();
						}

						m_qtyToDeliver = Env.ZERO;
						m_scrapQtyComponent = Env.ZERO;
						if (issue.getValueAt(ok, 8) != null
								&& !issue.getValueAt(ok, 8).toString()
										.equals("")) {
							BigDecimal mnb1 = new BigDecimal(issue
									.getValueAt(ok, 8).toString());
							m_qtyToDeliver = mnb1;
						}
						if (issue.getValueAt(ok, 9) != null
								&& !issue.getValueAt(ok, 9).toString()
										.equals("")) {
							BigDecimal mnbs1 = new BigDecimal(issue
									.getValueAt(ok, 9).toString());
							m_scrapQtyComponent = mnbs1;
						}
						if (m_qtyToDeliver == null)
							m_qtyToDeliver = Env.ZERO;

						if (m_scrapQtyComponent == null)
							m_scrapQtyComponent = Env.ZERO;

						BigDecimal onHand = Env.ZERO;

						Properties ctx = Env.getCtx();
						MStorage[] storages = null;
						MProduct product = new MProduct(ctx,
								m_M_Product_ID, null);
						if (product != null && product.get_ID() != 0
								&& product.isStocked()) {

							MProductCategory pc = MProductCategory.get(ctx,
									product.getM_Product_Category_ID());
							String MMPolicy = pc.getMMPolicy();
							if (MMPolicy == null || MMPolicy.length() == 0) {
								MClient client = MClient.get(ctx);
								MMPolicy = client.getMMPolicy();
							}

							if (M_AttributeSetInstance_ID == 1) {
								storages = MStorage
										.getWarehouse(
												Env.getCtx(),
												m_PP_order
														.getM_Warehouse_ID(),
												m_M_Product_ID,
												m_PP_order
														.getM_AttributeSetInstance_ID(),
												product
														.getM_AttributeSet_ID(),
												true, minGuaranteeDate,
												MClient.MMPOLICY_FiFo
														.equals(MMPolicy),
												null);
							} else {
								storages = MStorage.getWarehouse(Env
										.getCtx(), m_PP_order
										.getM_Warehouse_ID(),
										m_M_Product_ID,
										M_AttributeSetInstance_ID, product
												.getM_AttributeSet_ID(),
										false, minGuaranteeDate,
										MClient.MMPOLICY_FiFo
												.equals(MMPolicy), null);
							}
						}

						for (int j = 0; j < storages.length; j++) {
							MStorage storage = storages[j];
							onHand = onHand.add(storage.getQtyOnHand());
						}                              

						createIssue(PP_Order_BOMLine_ID, m_movementDate,
								m_qtyToDeliver, m_scrapQtyComponent,
								Env.ZERO, storages);

					}
				}
			}
		}

		if (isOnlyReceipt() || (isBackflush())) {
			m_M_Location_ID = ((Integer) locator.getValue()).intValue();
			Integer m_M_AttributeSetInstance_ID = (Integer) attribute
					.getValue();
			int AttributeSetInstance = 0;

			if (m_M_AttributeSetInstance_ID != null)
				AttributeSetInstance = m_M_AttributeSetInstance_ID
						.intValue();

			BigDecimal m_scrapQty = (BigDecimal) scrapQty.getValue();
			BigDecimal m_rejectQty = (BigDecimal) rejectQty.getValue();

			if (m_scrapQty == null)
				m_scrapQty = Env.ZERO;

			if (m_rejectQty == null)
				m_rejectQty = Env.ZERO;

			int C_DocType_ID = getDocType(X_C_DocType.DOCBASETYPE_ManufacturingOrderReceipt);
			if (m_toDeliverQty.compareTo(Env.ZERO) > 0
					|| m_scrapQty.compareTo(Env.ZERO) > 0
					|| m_rejectQty.compareTo(Env.ZERO) > 0)
				createCollector(m_PP_order.getM_Product_ID(),
						m_M_Location_ID, AttributeSetInstance,
						m_movementDate, m_toDeliverQty, m_scrapQty,
						m_rejectQty, C_DocType_ID, 0,
						MPPCostCollector.MOVEMENTTYPE_ProductionPlus);

			if (ADialog.ask(m_WindowNo, this, Msg.translate(Env.getCtx(),
					"IsCloseDocument"), Msg.translate(Env.getCtx(),
					"DocumentNo")
					+ m_PP_order.getDocumentNo())) {
				m_PP_order.setDateFinish(m_movementDate);
				m_PP_order.closeIt();
				m_PP_order.save();
			}

			m_PP_order.setDateDelivered(m_movementDate);
			if (m_PP_order.getDateStart() == null)
				m_PP_order.setDateStart(m_movementDate);

			BigDecimal DQ = Env.ZERO;
			BigDecimal SQ = Env.ZERO;
			BigDecimal OQ = Env.ZERO;
			
			if (deliveredQty.getValue() != null)
				DQ = (BigDecimal) deliveredQty.getValue();
			
			if (scrapQty.getValue() != null)
				SQ = (BigDecimal) scrapQty.getValue();
			
			if (toDeliverQty.getValue() != null)
				OQ = (BigDecimal) toDeliverQty.getValue();
			
			if (DQ.add(SQ).compareTo(OQ) >= 0)
				m_PP_order.setDateFinish(m_movementDate);

			m_PP_order.save();
		} 
		ADialog.info(m_WindowNo, this, Msg.translate(Env.getCtx(), "OnlyReceipt"),
				Msg.translate(Env.getCtx(), "DocumentNo") + m_PP_order.getDocumentNo());
		return true;
	}

	/**************************************************************************
	 * 	Create Line
	 *	@param order order
	 *	@param orderLine line
	 *	@param qty qty
	 */
	private void createIssue(int PP_OrderBOMLine_ID, Timestamp movementdate,
			BigDecimal qty, BigDecimal qtyscrap, BigDecimal qtyreject,
			MStorage[] storages) {

		if (qty.compareTo(Env.ZERO) == 0)
			return;

		//	Inventory Lines		
		BigDecimal toIssue = qty.add(qtyscrap);
		for (int i = 0; i < storages.length; i++) {
			MStorage storage = storages[i];
			//	TODO Selection of ASI

			if (storage.getQtyOnHand().compareTo(Env.ZERO) == 0)
				continue;

			BigDecimal issue = toIssue;
			if (issue.compareTo(storage.getQtyOnHand()) > 0)
				issue = storage.getQtyOnHand();

			log.info("createCollector - ToIssue" + issue);
			MPPOrderBOMLine PP_orderbomLine = new MPPOrderBOMLine(Env.getCtx(),
					PP_OrderBOMLine_ID, null);
			if (PP_orderbomLine.getQtyBatch().compareTo(Env.ZERO) == 0
					&& PP_orderbomLine.getQtyBOM().compareTo(Env.ZERO) == 0) {
				// Method Variance
				int C_DocType_ID = getDocType(X_C_DocType.DOCBASETYPE_ManufacturingOrderMethodVariation);
				if (issue.compareTo(Env.ZERO) > 0
						|| qtyscrap.compareTo(Env.ZERO) > 0
						|| qtyreject.compareTo(Env.ZERO) > 0)
					createCollector(PP_orderbomLine.getM_Product_ID(), storage
							.getM_Locator_ID(), storage
							.getM_AttributeSetInstance_ID(), movementdate,
							issue, qtyscrap, qtyreject, C_DocType_ID,
							PP_OrderBOMLine_ID,
							MPPCostCollector.MOVEMENTTYPE_Production_);
			} else {
				int C_DocType_ID = getDocType(X_C_DocType.DOCBASETYPE_ManufacturingOrderIssue);
				
				if (issue.compareTo(Env.ZERO) > 0 || qtyscrap.compareTo(Env.ZERO) > 0 || qtyreject.compareTo(Env.ZERO) > 0) {
					createCollector(
							PP_orderbomLine.getM_Product_ID(),
							storage.getM_Locator_ID(),
							storage.getM_AttributeSetInstance_ID(),
							movementdate,
							issue,
							qtyscrap,
							qtyreject,
							C_DocType_ID,
							PP_OrderBOMLine_ID,
							MPPCostCollector.MOVEMENTTYPE_Production_
							);
				}
			}

			toIssue = toIssue.subtract(issue);
			if (toIssue.compareTo(Env.ZERO) == 0)
				break;  
		}
	}

	private void createCollector(
			int M_Product_ID,
			int M_Locator_ID,
			int M_AttributeSetInstance_ID,
			Timestamp movementdate,
			BigDecimal qty,
			BigDecimal scrap,
			BigDecimal reject,
			int C_DocType_ID,
			int PP_Order_BOMLine_ID,
			String MovementType
			) {
		MPPCostCollector PP_Cost_Collector = new MPPCostCollector(Env.getCtx(), 0, null);
		PP_Cost_Collector.setPP_Order_ID(m_PP_order.getPP_Order_ID());
		PP_Cost_Collector.setPP_Order_BOMLine_ID(PP_Order_BOMLine_ID);
		PP_Cost_Collector.setAD_OrgTrx_ID(m_PP_order.getAD_OrgTrx_ID());
		PP_Cost_Collector.setC_Activity_ID(m_PP_order.getC_Activity_ID());
		PP_Cost_Collector.setC_Campaign_ID(m_PP_order.getC_Campaign_ID());
		PP_Cost_Collector.setC_DocType_ID(C_DocType_ID);
		PP_Cost_Collector.setC_DocTypeTarget_ID(C_DocType_ID);
		PP_Cost_Collector.setMovementType(MovementType);
		PP_Cost_Collector.setC_Project_ID(m_PP_order.getC_Project_ID());
		PP_Cost_Collector.setDescription(m_PP_order.getDescription());
		PP_Cost_Collector.setDocAction(PP_Cost_Collector.ACTION_Complete);
		PP_Cost_Collector.setDocStatus(PP_Cost_Collector.DOCSTATUS_Drafted);
		PP_Cost_Collector.setIsActive(true);
		PP_Cost_Collector.setM_Warehouse_ID(m_PP_order.getM_Warehouse_ID());
		PP_Cost_Collector.setM_Locator_ID(M_Locator_ID);
		PP_Cost_Collector.setM_AttributeSetInstance_ID(M_AttributeSetInstance_ID);
		PP_Cost_Collector.setS_Resource_ID(m_PP_order.getS_Resource_ID());
		PP_Cost_Collector.setMovementDate(movementdate);
		PP_Cost_Collector.setDateAcct(movementdate);
		PP_Cost_Collector.setMovementQty(qty);
		PP_Cost_Collector.setScrappedQty(scrap);
		PP_Cost_Collector.setQtyReject(reject);
		PP_Cost_Collector.setPosted(false);
		PP_Cost_Collector.setProcessed(false);
		PP_Cost_Collector.setProcessing(false);
		PP_Cost_Collector.setUser1_ID(m_PP_order.getUser1_ID());
		PP_Cost_Collector.setUser2_ID(m_PP_order.getUser2_ID());
		PP_Cost_Collector.setM_Locator_ID(M_Locator_ID);
		PP_Cost_Collector.setM_Product_ID(M_Product_ID);
		if (!PP_Cost_Collector.save()) {
			throw new IllegalStateException("Could not create Collector");
		}
		PP_Cost_Collector.completeIt();
	}

	private int getDocType(String DocBaseType) {
		org.compiere.model.MDocType[] Doc = org.compiere.model.MDocType.getOfDocBaseType(Env.getCtx(), DocBaseType);
		int C_DocType_ID = 0;

		if (Doc != null)
			C_DocType_ID = Doc[0].getC_DocType_ID();

		return C_DocType_ID;
	}

	/**
	 *  Generate Summary of Issue/Receipt.
	 */
	private void generateSummaryTable() {
		StringBuffer iText = new StringBuffer();
		iText.append("<b>");
		iText.append(Msg.translate(Env.getCtx(), "IsShipConfirm"));
		iText.append("</b>");
		iText.append("<br />");

		if (isOnlyReceipt() || isBackflush()) {
						
			String[][] table = {
				{Msg.translate(Env.getCtx(), "Name"),
				 Msg.translate(Env.getCtx(), "C_UOM_ID"),
				 Msg.translate(Env.getCtx(), "M_AttributeSetInstance_ID"),
				 Msg.translate(Env.getCtx(), "QtyToDeliver"),
				 Msg.translate(Env.getCtx(), "QtyDelivered"),
				 Msg.translate(Env.getCtx(), "QtyScrap")},
				{product.getDisplay(),
				 uom.getDisplay(),
				 attribute.getDisplay(),
				 toDeliverQty.getDisplay(),
				 deliveredQty.getDisplay(),
				 scrapQty.getDisplay()}
			};
			iText.append(createHTMLTable(table));
		}

		if (isBackflush() || isOnlyIssue()) {
			iText.append("<br /><br />");
			
			ArrayList<String[]> table = new ArrayList<String[]>();
			
			table.add(new String[] {
				Msg.translate(Env.getCtx(), "Value"),
				Msg.translate(Env.getCtx(), "Name"),
				Msg.translate(Env.getCtx(), "C_UOM_ID"),
				Msg.translate(Env.getCtx(), "M_AttributeSetInstance_ID"),
				Msg.translate(Env.getCtx(), "QtyToDeliver"),
				Msg.translate(Env.getCtx(), "QtyDelivered"),
				Msg.translate(Env.getCtx(), "QtyScrap")
			});

			// check available on hand 
			for (int i = 0; i < issue.getRowCount(); i++) {
				IDColumn id = (IDColumn) issue.getValueAt(i, 0);
				
				if (id != null && id.isSelected()) {
					KeyNamePair m_productkey = (KeyNamePair) issue.getValueAt(i, 3);
					int m_M_Product_ID = m_productkey.getKey();
					KeyNamePair m_uomkey = (KeyNamePair) issue.getValueAt(i, 4);

					if (issue.getValueAt(i, 5) == null) {
						Timestamp m_movementDate = (Timestamp) movementDate.getValue();
						Timestamp minGuaranteeDate = m_movementDate;
						Properties ctx = Env.getCtx();
						MStorage[] storages = null;
						MProduct product = new MProduct(ctx, m_M_Product_ID, null);
						
						if (product != null && product.get_ID() != 0 && product.isStocked()) {
							MProductCategory pc = MProductCategory.get(ctx, product.getM_Product_Category_ID());
							String MMPolicy = pc.getMMPolicy();
							
							if (MMPolicy == null || MMPolicy.length() == 0) {
								MClient client = MClient.get(ctx);
								MMPolicy = client.getMMPolicy();
							}
							storages = MStorage.getWarehouse(
									Env.getCtx(),
									m_PP_order.getM_Warehouse_ID(),
									m_M_Product_ID,
									m_PP_order.getM_AttributeSetInstance_ID(),
									product.getM_AttributeSet_ID(),
									true,
									minGuaranteeDate,
									MClient.MMPOLICY_FiFo.equals(MMPolicy),
									null
									);
						}
						BigDecimal todelivery = Env.ZERO;
						BigDecimal scrap = Env.ZERO;
						if (issue.getValueAt(i, 8) != null) {									//QtyOpen
							todelivery = new BigDecimal(issue.getValueAt(i, 8).toString());
						}
						if (issue.getValueAt(i, 9) != null) {									//QtyScrap
							scrap = new BigDecimal(issue.getValueAt(i, 9).toString());
						}
						BigDecimal toIssue = todelivery.add(scrap);
						for (int k = 0; k < storages.length; k++) {
							MStorage storage = storages[k];
							//	TODO Selection of ASI

							if (storage.getQtyOnHand().compareTo(Env.ZERO) == 0)
								continue;
							
							String[] row = {"","","","","0.00","0.00","0.00"};
							
							if (issue.getValueAt(i, 2) != null)
								row[0] = issue.getValueAt(i, 2).toString();

							row[1] = m_productkey.toString();
							
							if (m_uomkey != null)
								row[2] = m_uomkey.toString();
							
							BigDecimal issueact = toIssue;
							if (issueact.compareTo(storage.getQtyOnHand()) > 0)
								issueact = storage.getQtyOnHand();

							toIssue = toIssue.subtract(issueact);

							String desc = new MAttributeSetInstance(Env.getCtx(), storage.getM_AttributeSetInstance_ID(), null).getDescription();

							if (desc != null)
								row[3] = desc;

							if (issue.getValueAt(i, 8) != null && !issue.getValueAt(i, 8).toString().equals(""))
								row[4] = issueact.setScale(2, BigDecimal.ROUND_HALF_UP).toString() ;
							
							if (issue.getValueAt(i, 7) != null && !issue.getValueAt(i, 7).toString().equals(""))
							{
								BigDecimal deliveredQtybd = (BigDecimal)issue.getValueAt(i, 7);
								row[5] =  deliveredQtybd.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
							}
							if (issue.getValueAt(i, 9) != null && !issue.getValueAt(i, 9).toString().equals("")) 
								row[6] = issue.getValueAt(i, 9).toString();
							
							table.add(row);
							
							if (toIssue.compareTo(Env.ZERO) <= 0)
								break;
						}

					} else {												// if M_AttributeSetInstance_ID isn't null
						String[] row = {"","","","","0.00","0.00","0.00"};
						
						if (issue.getValueAt(i, 2) != null)
							row[0] = issue.getValueAt(i, 2).toString();
						
						row[1] = m_productkey.toString();
						
						if (m_uomkey != null)
							row[2] = m_uomkey.toString();

						if (issue.getValueAt(i, 5) != null)
							row[3] = issue.getValueAt(i, 5).toString();
						
						if (issue.getValueAt(i, 8) != null && !issue.getValueAt(i, 8).toString().equals(""))
							row[4] = issue.getValueAt(i, 8).toString();
						
						if (issue.getValueAt(i, 7) != null && !issue.getValueAt(i, 7).toString().equals(""))
							row[5] = issue.getValueAt(i, 7).toString();
						
						if (issue.getValueAt(i, 9) != null && !issue.getValueAt(i, 9).toString().equals(""))
							row[6] = issue.getValueAt(i, 9).toString();
						
						table.add(row);
					}

				}

			}
			
			// TODO For some reason (String[][])table.toArray() doesn't work so we have to do this little loop
			String[][] tableArray = new String[table.size()][];
			for (int i = 0; i < table.size(); i++){
				tableArray[i] = table.get(i);
			}
			iText.append(createHTMLTable(tableArray));
		}
		info.setText(iText.toString());
	} //  generateInvoices_complete

	/**
	 * Deturmines whether the Delivery Rule is set to 'OnlyReciept'
	 * @return	
	 */
	private Boolean isOnlyReceipt() {
		return (pickcombo.getDisplay().equals("OnlyReceipt"));
	}
	/**
	 * Deturmines whether the Delivery Rule is set to 'OnlyIssue'
	 * @return	
	 */
	private Boolean isOnlyIssue() {
		return (pickcombo.getDisplay().equals("OnlyIssue"));
	}

	/**
	 * Deturmines whether the Delivery Rule is set to 'isBackflush'
	 * @return	
	 */
	private Boolean isBackflush() {
		return (pickcombo.getDisplay().equals("IsBackflush"));
	}
	
	/**
	 * Creates a HTML Table out of a two dimensional array.
	 * @param table	A two dimensional array of strings that will be rendered into an html table
	 * @return		The html for the table
	 */
	private String createHTMLTable(String[][] table) {
		StringBuffer html = new StringBuffer("<table width=\"100%\" border=\"1\" cellspacing=\"0\" cellpadding=\"0\">");

		for(int i = 0; i < table.length; i++) {
			if (table[i] != null) {
				html.append("<tr>");
				for(int j = 0; j < table[i].length; j++){
					html.append("<td>");
					if (table[i][j] != null) {
						html.append(table[i][j]);
					}
					html.append("</td>");
				}
				html.append("</tr>");
			}
		}
		html.append("</table>");
		
		return html.toString();
	}
	
	public void valueChanged(ListSelectionEvent e) {
	}
	public void executeASync(org.compiere.process.ProcessInfo processInfo) {
	}
	public boolean isUILocked() {
		return true;
	}
	public void lockUI(org.compiere.process.ProcessInfo processInfo) {
	}
	public void stateChanged(ChangeEvent e) {
	}
	public void tableChanged(TableModelEvent e) {
	}
	public void unlockUI(org.compiere.process.ProcessInfo processInfo) {
	}
}
