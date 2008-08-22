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

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.DBException;
import org.adempiere.plaf.AdempierePLAF;
import org.compiere.apps.ADialog;
import org.compiere.apps.ConfirmPanel;
import org.compiere.apps.StatusBar;
import org.compiere.apps.form.FormFrame;
import org.compiere.apps.form.FormPanel;
import org.compiere.grid.ed.VComboBox;
import org.compiere.grid.ed.VDate;
import org.compiere.grid.ed.VLocator;
import org.compiere.grid.ed.VLookup;
import org.compiere.grid.ed.VNumber;
import org.compiere.grid.ed.VPAttribute;
import org.compiere.minigrid.IDColumn;
import org.compiere.minigrid.MiniTable;
import org.compiere.model.GridField;
import org.compiere.model.GridFieldVO;
import org.compiere.model.MAttributeSetInstance;
import org.compiere.model.MClient;
import org.compiere.model.MColumn;
import org.compiere.model.MDocType;
import org.compiere.model.MLocatorLookup;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MPAttributeLookup;
import org.compiere.model.MProduct;
import org.compiere.model.MStorage;
import org.compiere.model.MTab;
import org.compiere.model.MWindow;
import org.compiere.model.X_C_DocType;
import org.compiere.plaf.CompiereColor;
import org.compiere.swing.CButton;
import org.compiere.swing.CLabel;
import org.compiere.swing.CPanel;
import org.compiere.swing.CTextField;
import org.compiere.swing.CTextPane;
import org.compiere.util.ASyncProcess;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Language;
import org.compiere.util.Msg;
import org.eevolution.model.MPPCostCollector;
import org.eevolution.model.MPPOrder;
import org.eevolution.model.MPPOrderBOMLine;
import org.eevolution.model.MPPProductBOMLine;

/**
 *
 * @author  vpj-cd
 */

public class VOrderReceiptIssue extends CPanel implements FormPanel,
		ActionListener, VetoableChangeListener, ChangeListener,
		ListSelectionListener, TableModelListener, ASyncProcess
{
	private static final long serialVersionUID = 1L;

	/**	Window No			*/
	private int m_WindowNo = 0;
	/**	FormFrame			*/
	private FormFrame m_frame;
	private StatusBar statusBar = new StatusBar();

	private MPPOrder m_PP_order = null;
	private static CLogger log = CLogger.getCLogger(VOrderReceiptIssue.class);
	
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
		log.info("VOrderReceipIssue.init - WinNo=" + m_WindowNo);

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

		final String sql = "SELECT "
			+ "obl.PP_Order_BOMLine_ID," // 1
			+ "obl.IsCritical," // 2
			+ "p.Value," // 3
			+ "obl.M_Product_ID,p.Name," // 4,5
			+ "p.C_UOM_ID,u.Name," // 6,7
			+ "obl.QtyRequiered," // 8
			+ "obl.QtyReserved," // 9
			+ "bomQtyAvailable(obl.M_Product_ID,obl.M_Warehouse_ID,0 ) AS QtyAvailable," // 10
			+ "bomQtyOnHand(obl.M_Product_ID,obl.M_Warehouse_ID,0) AS QtyOnHand," // 11
			+ "p.M_Locator_ID," // 12
			+ "obl.M_Warehouse_ID,w.Name," // 13,14
			+ "obl.QtyBom," // 15
			+ "obl.isQtyPercentage," // 16
			+ "obl.QtyBatch," // 17
			+ "obl.ComponentType," // 18
			+ "obl.QtyRequiered - QtyDelivered AS QtyOpen," // 19
			+ "obl.QtyDelivered" // 20
			+ " FROM PP_Order_BOMLine obl"
			+ " INNER JOIN M_Product p ON (obl.M_Product_ID = p.M_Product_ID) "
			+ " INNER JOIN C_UOM u ON (p.C_UOM_ID = u.C_UOM_ID) "
			+ " INNER JOIN M_Warehouse w ON (w.M_Warehouse_ID = obl.M_Warehouse_ID) "
			+ " WHERE obl.PP_Order_ID = ?"
			+ " ORDER BY bomQtyOnHand(obl.M_Product_ID,obl.M_Warehouse_ID,0) "
			;
		//  reset table
		int row = 0;
		issue.setRowCount(row);
		//  Execute
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, getPP_Order_ID());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				//  extend table
				issue.setRowCount(row + 1);
				//  set values
				//issue.
				IDColumn   id                    = new IDColumn(rs.getInt(1));
				BigDecimal m_QtyBom              = rs.getBigDecimal(15);
				Boolean    m_isQtyPercentage     = rs.getString(16).equals("Y");
				BigDecimal m_QtyBatch            = rs.getBigDecimal(17);
				BigDecimal m_QtyRequired         = rs.getBigDecimal(8);
				String     m_ComponentType       = rs.getString(18);
				BigDecimal m_onhand              = rs.getBigDecimal(11);
				BigDecimal m_toDeliverQty        = getToDeliverQty();
				BigDecimal m_openQty             = getOpenQty();
				BigDecimal m_scrapQty            = getScrapQty();
				BigDecimal componentToDeliverQty = Env.ZERO;
				BigDecimal componentScrapQty     = Env.ZERO;
				BigDecimal componentQtyReq       = Env.ZERO;
				BigDecimal componentQtytoDel     = Env.ZERO;

				id.setSelected(isOnlyReceipt());

				issue.setValueAt(id, row, 0);                                                // PP_OrderBOMLine_ID
				issue.setValueAt(rs.getString(2).equals("Y"), row, 1);                       // IsCritical
				issue.setValueAt(rs.getString(3), row, 2);                                   // Product's Search key
				issue.setValueAt(new KeyNamePair(rs.getInt(4), rs.getString(5)), row, 3);    // Product
				issue.setValueAt(new KeyNamePair(rs.getInt(6), rs.getString(7)), row, 4);    // UOM
				issue.setValueAt(m_QtyRequired, row, 6);                                     // QtyRequiered
				issue.setValueAt(rs.getBigDecimal(20), row, 7);                              // QtyDelivered
				issue.setValueAt(m_onhand, row, 10);                                         // OnHand
				issue.setValueAt(rs.getBigDecimal(9), row, 11);                              // QtyReserved
				issue.setValueAt(rs.getBigDecimal(10), row, 12);                             // QtyAvailable
				issue.setValueAt(new KeyNamePair(rs.getInt(13), rs.getString(14)), row, 14); // Warehouse
				issue.setValueAt(m_QtyBom, row, 15);                                         // QtyBom
				issue.setValueAt(m_isQtyPercentage, row, 16);                                // isQtyPercentage
				issue.setValueAt(m_QtyBatch, row, 17);                                       // QtyBatch


				if (m_ComponentType.equals(MPPProductBOMLine.COMPONENTTYPE_Component)
						|| m_ComponentType.equals(MPPProductBOMLine.COMPONENTTYPE_Packing))
				{
					// If the there is product on hand and product is required the product should be selected
					id.setSelected(m_onhand.signum() > 0 && m_QtyRequired.signum() > 0);

					if (m_isQtyPercentage)
					{
						// If the quantity of product is calculated as a percentage
						BigDecimal qtyBatchPerc = m_QtyBatch.divide(Env.ONEHUNDRED, 8, RoundingMode.HALF_UP); 

						if (isBackflush()) {
							// Is Backflush - Calculate Component from Qty To Deliver
							if (m_QtyRequired.signum() == 0) {
								componentToDeliverQty = Env.ZERO;
							}
							else {
								componentToDeliverQty = m_toDeliverQty.multiply(qtyBatchPerc);
							}

							if (componentToDeliverQty.signum() != 0) {
								// TODO: arhipac: teo_sarca: is this a bug ? ...instead of m_toDeliverQty, m_qtyRequired should be used!
								componentQtyReq = m_toDeliverQty.multiply(qtyBatchPerc); // TODO: set scale 4
								componentQtytoDel = componentToDeliverQty.setScale(4, BigDecimal.ROUND_HALF_UP);

								issue.setValueAt(m_toDeliverQty.multiply(qtyBatchPerc), row, 6); //  QtyRequiered
								issue.setValueAt(componentToDeliverQty, row, 8); //  QtyToDelivery

							}
						}
						else
						{ // Only Issue - Calculate Component from Open Qty
							componentToDeliverQty = rs.getBigDecimal(19); // QtyOpen
							if (componentToDeliverQty.signum() != 0) {
								componentQtyReq = m_openQty.multiply(qtyBatchPerc); // scale 4
								componentQtytoDel = componentToDeliverQty.setScale(4, BigDecimal.ROUND_HALF_UP);
								issue.setValueAt(componentToDeliverQty.setScale(8, BigDecimal.ROUND_HALF_UP), row, 8); //  QtyToDelivery
								issue.setValueAt(m_openQty.multiply(qtyBatchPerc), row, 6); //  QtyRequiered
							}
						}

						if (m_scrapQty.signum() != 0) {
							componentScrapQty = m_scrapQty.multiply(m_QtyBatch.divide(Env.ONEHUNDRED, 8, BigDecimal.ROUND_HALF_UP));
							if (componentScrapQty.signum() != 0) {
								issue.setValueAt(componentScrapQty, row, 9); //  QtyScrap
							}
						}
					}
					else
					{ // Absolute Qtys (not Percentage)
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
				}
				else if (m_ComponentType.equals(MPPProductBOMLine.COMPONENTTYPE_Tools))
				{
					VNumber viewToDeliverQty = new VNumber();
					viewToDeliverQty.setDisplayType(DisplayType.Number);
					viewToDeliverQty.setValue(m_QtyBom);

					componentToDeliverQty = (BigDecimal) viewToDeliverQty.getValue();
					if (componentToDeliverQty.signum() != 0) {
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

			} // while
		}
		catch (SQLException e) {
			throw new DBException(e);
		}
		finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		issue.autoSize();
	} //  executeQuery

	/**
	 * Extension to {@link #executeQuery()}
	 * Adds instance products to the table in the lower half of the screen
	 */
	private int lotes (int M_Product_ID, int row, IDColumn id, int Warehouse_ID,
			BigDecimal qtyreq, BigDecimal qtytodel)
	{
		int haslot = 0;
		BigDecimal reql = Env.ZERO;
		reql = qtyreq;
		final String sql = "SELECT "
			+ "s.M_Product_ID , s.QtyOnHand, s.M_AttributeSetInstance_ID"
			+ ", p.Name, masi.Description, l.Value, w.Value, w.M_warehouse_ID,p.Value"
			+ "  FROM M_Storage s "
			+ " INNER JOIN M_Product p ON (s.M_Product_ID = p.M_Product_ID) "
			+ " INNER JOIN C_UOM u ON (u.C_UOM_ID = p.C_UOM_ID) "
			+ " INNER JOIN M_AttributeSetInstance masi ON (masi.M_AttributeSetInstance_ID = s.M_AttributeSetInstance_ID) "
			+ " INNER JOIN M_Warehouse w ON (w.M_Warehouse_ID = ?) "
			+ " INNER JOIN M_Locator l ON(l.M_Warehouse_ID=w.M_Warehouse_ID and s.M_Locator_ID=l.M_Locator_ID) "
			+ " WHERE s.M_Product_ID = ? and s.QtyOnHand > 0 "
			+ " and s.M_AttributeSetInstance_ID <> 0 "
			+ " ORDER BY s.Created ";
		//  Execute
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, Warehouse_ID);
			pstmt.setInt(2, M_Product_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				issue.setRowCount(row + 1);

				BigDecimal qtyOnHand = rs.getBigDecimal(2);

				IDColumn id1 = new IDColumn(rs.getInt(3));
				id1.setSelected(false);
				issue.setRowSelectionAllowed(true);
				issue.setValueAt(id1, row, 0); //  M_AttributeSetInstance_ID
				KeyNamePair m_productkey = new KeyNamePair(rs.getInt(1), rs.getString(4));
				issue.setValueAt(m_productkey, row, 3);
				issue.setValueAt(rs.getBigDecimal(2), row, 10); // Onhand

				issue.setValueAt(rs.getString(5), row, 5); //attribute
				issue.setValueAt(rs.getString(6), row, 13); // Locator Value
				KeyNamePair m_warehousekey = new KeyNamePair(rs.getInt(8), rs.getString(7));
				issue.setValueAt(m_warehousekey, row, 14);

				if (reql.compareTo(qtyOnHand) < 0) {
					if (reql.signum() <= 0)
						issue.setValueAt(Env.ZERO, row, 6);
					else
						issue.setValueAt(reql, row, 6);
					reql = reql.subtract(qtyOnHand);
				}
				else {
					issue.setValueAt(qtyOnHand, row, 6);
					reql = reql.subtract(qtyOnHand);
				}

				haslot++;
				row++;
			}
		}
		catch (SQLException e) {
			throw new DBException(e);
		}
		finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
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
				int PP_Order_ID = (Integer) order.getValue();
				m_PP_order = new MPPOrder(Env.getCtx(), PP_Order_ID, null);
				resource.setValue(m_PP_order.getS_Resource_ID());
				warehouse.setValue(m_PP_order.getM_Warehouse_ID());
				deliveredQty.setValue(m_PP_order.getQtyDelivered());
				orderedQty.setValue(m_PP_order.getQtyOrdered());
				//m_PP_order.getQtyOrdered().subtract(m_PP_order.getQtyDelivered());
				qtyBatchs.setValue(m_PP_order.getQtyBatchs());
				qtyBatchSize.setValue(m_PP_order.getQtyBatchSize());
				openQty.setValue(m_PP_order.getQtyOrdered().subtract(m_PP_order.getQtyDelivered()));
				toDeliverQty.setValue(openQty.getValue());
				product.setValue(m_PP_order.getM_Product_ID());
				MProduct m_product = MProduct.get(Env.getCtx(), m_PP_order.getM_Product_ID());
				uom.setValue(m_product.getC_UOM_ID());
				uomorder.setValue(m_PP_order.getC_UOM_ID());
				Integer m_product_id = (Integer) product.getValue();
				Env.setContext(Env.getCtx(), m_WindowNo, "M_Product_ID", m_product_id.intValue());
				attribute.setValue(m_PP_order.getM_AttributeSetInstance_ID());
				pickcombo.setSelectedIndex(0);  //default to first entry - isBackflush
			}
		} //  PP_Order_ID
	}

	/**
	 * Performs what ever task is attached to the combo box
	 * @return Whether the process was successful or not
	 */
	private boolean cmd_process()
	{
		if (isOnlyReceipt() || isBackflush()) {
			if (getM_Locator_ID() <= 0) {
				JOptionPane.showMessageDialog(null,
						Msg.getMsg(Env.getCtx(),"NoLocator"), "Info", JOptionPane.INFORMATION_MESSAGE);
			}
		}

		if (m_PP_order == null || getMovementDate() == null) {
			return false;
		}

		try {
			if (isBackflush() || isOnlyIssue()) {
				createIssue();
			}
			if (isOnlyReceipt() || isBackflush()) {
				createReceipt();
			}
			ADialog.info(m_WindowNo, this,
					Msg.translate(Env.getCtx(), "OnlyReceipt"),
					Msg.translate(Env.getCtx(), "DocumentNo") + m_PP_order.getDocumentNo());
		}
		catch (Exception e) {
			ADialog.error(m_WindowNo, this, e.getLocalizedMessage());
			return false;
		}

		return true;
	}

	private void createIssue()
	{
		Timestamp m_movementDate = getMovementDate();
		Timestamp minGuaranteeDate = m_movementDate;
		boolean iscompleteqtydeliver = false;

		//
		// Check Available On Hand 
		for (int i = 0; i < issue.getRowCount(); i++)
		{
			IDColumn id = (IDColumn) issue.getValueAt(i, 0);
			if (id == null || !id.isSelected()) {
				continue;
			}

			KeyNamePair m_productkey = (KeyNamePair) issue.getValueAt(i, 3);
			int m_M_Product_ID = m_productkey.getKey();
			BigDecimal m_qtyToDeliver = getValueBigDecimal(i, 8);
			BigDecimal m_scrapQtyComponent = getValueBigDecimal(i, 9);

			MStorage[] storages = getStorages(m_M_Product_ID, m_PP_order.getM_AttributeSetInstance_ID(), minGuaranteeDate);
			int M_AttributeSetInstance_ID = 1;

			if (issue.getValueAt(i, 2) == null && id.isSelected()) {
				M_AttributeSetInstance_ID = (Integer)id.getRecord_ID();
			}
			if (M_AttributeSetInstance_ID == 1) {
				BigDecimal toIssue = m_qtyToDeliver.add(m_scrapQtyComponent);
				for (MStorage storage : storages) {
					//	TODO Selection of ASI

					if (storage.getQtyOnHand().signum() == 0)
						continue;
					BigDecimal issueActual = toIssue.min(storage.getQtyOnHand());
					toIssue = toIssue.subtract(issueActual);
					if (toIssue.signum() <= 0)
						break;
				}
			} else {
				BigDecimal qtydelivered = m_qtyToDeliver;
				qtydelivered.setScale(4, BigDecimal.ROUND_HALF_UP);
				qtydelivered = Env.ZERO;
			}

			BigDecimal onHand = Env.ZERO;
			for (MStorage storage : storages) {
				onHand = onHand.add(storage.getQtyOnHand());
			}

			iscompleteqtydeliver = onHand.compareTo(m_qtyToDeliver.add(m_scrapQtyComponent)) >= 0;
			if (!iscompleteqtydeliver)
				break;
		} // for

		if (!iscompleteqtydeliver) {
			ADialog.error(m_WindowNo, this, "NoQtyAvailable");
			throw new AdempiereException("@NoQtyAvailable@");
		}

		//
		// Issue Qty
		for (int ok = 0; ok < issue.getRowCount(); ok++) {
			IDColumn id = (IDColumn) issue.getValueAt(ok, 0);
			if (id == null || !id.isSelected()) {
				continue;
			}

			KeyNamePair m_productkey = (KeyNamePair) issue.getValueAt(ok, 3);
			int m_M_Product_ID = m_productkey.getKey();

			int PP_Order_BOMLine_ID = 0;
			int M_AttributeSetInstance_ID = 1;
			if (issue.getValueAt(ok, 2) == null && id.isSelected()) {
				M_AttributeSetInstance_ID = (Integer) id.getRecord_ID();

				String sql = "SELECT PP_Order_BOMLine_ID FROM PP_Order_BOMLine"
					+" WHERE M_Product_ID=? AND PP_Order_ID=?";
				PP_Order_BOMLine_ID = DB.getSQLValue(null, sql, m_M_Product_ID, order.getValue());
			}
			else if (issue.getValueAt(ok, 2) != null && id.isSelected()) {
				PP_Order_BOMLine_ID = ((Integer) id.getRecord_ID());
			}

			BigDecimal m_qtyToDeliver = getValueBigDecimal(ok, 8);
			BigDecimal m_scrapQtyComponent = getValueBigDecimal(ok, 9);

			BigDecimal onHand = Env.ZERO;
			MStorage[] storages = getStorages(m_M_Product_ID, M_AttributeSetInstance_ID, minGuaranteeDate);
			for (MStorage storage : storages) {
				onHand = onHand.add(storage.getQtyOnHand());
			}                              

			createIssue(PP_Order_BOMLine_ID, m_movementDate,
					m_qtyToDeliver, m_scrapQtyComponent,
					Env.ZERO, storages);
		}
	}

	private void createReceipt()
	{
		Timestamp m_movementDate = getMovementDate();
		BigDecimal m_toDeliverQty = getToDeliverQty();
		BigDecimal m_scrapQty = getScrapQty();
		BigDecimal m_rejectQty = getRejectQty();

		if (m_toDeliverQty.signum() > 0 || m_scrapQty.signum() > 0 || m_rejectQty.signum() > 0)
		{
			createCollector(m_PP_order.getM_Product_ID(),
					getM_Locator_ID(),
					getM_AttributeSetInstance_ID(),
					m_movementDate,
					m_toDeliverQty, m_scrapQty, m_rejectQty,
					getDocType(X_C_DocType.DOCBASETYPE_ManufacturingOrderReceipt),
					0, // PP_Order_BOMLine_ID
					MPPCostCollector.MOVEMENTTYPE_ProductionPlus);
		}

		if (ADialog.ask(m_WindowNo, this,
				Msg.translate(Env.getCtx(), "IsCloseDocument"),
				Msg.translate(Env.getCtx(), "DocumentNo") + m_PP_order.getDocumentNo())
		)
		{
			m_PP_order.setDateFinish(m_movementDate);
			m_PP_order.closeIt();
			m_PP_order.saveEx();
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

		m_PP_order.saveEx();

	}

	/**************************************************************************
	 * 	Create Issue
	 *	@param order order
	 *	@param orderLine line
	 *	@param qty qty
	 */
	private void createIssue(int PP_OrderBOMLine_ID, Timestamp movementdate,
			BigDecimal qty, BigDecimal qtyscrap, BigDecimal qtyreject,
			MStorage[] storages)
	{
		if (qty.signum() == 0)
			return;

		BigDecimal toIssue = qty.add(qtyscrap);
		for (int i = 0; i < storages.length; i++) {
			MStorage storage = storages[i];
			//	TODO Selection of ASI

			if (storage.getQtyOnHand().signum() == 0)
				continue;

			BigDecimal issue = toIssue;
			if (issue.compareTo(storage.getQtyOnHand()) > 0)
				issue = storage.getQtyOnHand();

			log.fine("ToIssue: " + issue);
			MPPOrderBOMLine PP_orderbomLine = new MPPOrderBOMLine(Env.getCtx(), PP_OrderBOMLine_ID, null);
			if (issue.signum() > 0 || qtyscrap.signum() > 0 || qtyreject.signum() > 0)
			{
				int C_DocType_ID = 0;
				// Method Variance
				if (PP_orderbomLine.getQtyBatch().signum() == 0 && PP_orderbomLine.getQtyBOM().signum() == 0)
				{
					C_DocType_ID = getDocType(MDocType.DOCBASETYPE_ManufacturingOrderMethodVariance);
				}
				// Order Issue
				else
				{
					C_DocType_ID = getDocType(MDocType.DOCBASETYPE_ManufacturingOrderIssue);
				}
				//
				createCollector (
						PP_orderbomLine.getM_Product_ID(),
						storage.getM_Locator_ID(),
						storage.getM_AttributeSetInstance_ID(),
						movementdate,
						issue, qtyscrap, qtyreject,
						C_DocType_ID,
						PP_OrderBOMLine_ID,
						MPPCostCollector.MOVEMENTTYPE_Production_
				);

			}

			toIssue = toIssue.subtract(issue);
			if (toIssue.compareTo(Env.ZERO) == 0)
				break;  
		}
	}

	private void createCollector (
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
	)
	{
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
		PP_Cost_Collector.setDocAction(MPPCostCollector.ACTION_Complete);
		PP_Cost_Collector.setDocStatus(MPPCostCollector.DOCSTATUS_Drafted);
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
		PP_Cost_Collector.saveEx();
		if (!PP_Cost_Collector.processIt(MPPCostCollector.DOCACTION_Complete)) {
			throw new AdempiereException(PP_Cost_Collector.getProcessMsg());
		}
	}

	private int getDocType(String DocBaseType)
	{
		MDocType[] doc = MDocType.getOfDocBaseType(Env.getCtx(), DocBaseType);
		return doc.length > 0 ? doc[0].get_ID() : 0;
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
					Msg.translate(Env.getCtx(), "Value"),						// 0
					Msg.translate(Env.getCtx(), "Name"),						// 1
					Msg.translate(Env.getCtx(), "C_UOM_ID"),					// 2
					Msg.translate(Env.getCtx(), "M_AttributeSetInstance_ID"),	// 3
					Msg.translate(Env.getCtx(), "QtyToDeliver"),				// 4
					Msg.translate(Env.getCtx(), "QtyDelivered"),				// 5
					Msg.translate(Env.getCtx(), "QtyScrap")						// 6
			});

			// check available on hand 
			for (int i = 0; i < issue.getRowCount(); i++) {
				IDColumn id = (IDColumn) issue.getValueAt(i, 0);

				if (id != null && id.isSelected()) {
					KeyNamePair m_productkey = (KeyNamePair) issue.getValueAt(i, 3);
					int m_M_Product_ID = m_productkey.getKey();
					KeyNamePair m_uomkey = (KeyNamePair) issue.getValueAt(i, 4);

					if (issue.getValueAt(i, 5) == null) // M_AttributeSetInstance_ID is null
					{ 
						Timestamp m_movementDate = (Timestamp) movementDate.getValue();
						Timestamp minGuaranteeDate = m_movementDate;
						MStorage[] storages = getStorages(m_M_Product_ID, m_PP_order.getM_AttributeSetInstance_ID(), minGuaranteeDate);

						BigDecimal todelivery = getValueBigDecimal(i, 8); //QtyOpen
						BigDecimal scrap = getValueBigDecimal(i, 9); //QtyScrap
						BigDecimal toIssue = todelivery.add(scrap);
						for (MStorage storage : storages) {
							//	TODO Selection of ASI

							if (storage.getQtyOnHand().signum() == 0)
								continue;

							BigDecimal issueact = toIssue;
							if (issueact.compareTo(storage.getQtyOnHand()) > 0)
								issueact = storage.getQtyOnHand();
							toIssue = toIssue.subtract(issueact);

							String desc = new MAttributeSetInstance(Env.getCtx(), storage.getM_AttributeSetInstance_ID(), null).getDescription();

							String[] row = {"","","","","0.00","0.00","0.00"};
							row[0] = issue.getValueAt(i, 2) != null ? issue.getValueAt(i, 2).toString() : "";
							row[1] = m_productkey.toString();
							row[2] = m_uomkey != null ? m_uomkey.toString() : "";
							row[3] = desc != null ? desc : "";
							row[4] = issueact.setScale(2, BigDecimal.ROUND_HALF_UP).toString() ;
							row[5] = getValueBigDecimal(i, 7).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
							row[6] = getValueBigDecimal(i, 9).toString();
							table.add(row);

							if (toIssue.signum() <= 0)
								break;
						}
					}
					else // if M_AttributeSetInstance_ID isn't null
					{
						String[] row = {"","","","","0.00","0.00","0.00"};
						row[0] = issue.getValueAt(i, 2) != null ? issue.getValueAt(i, 2).toString() : "";
						row[1] = m_productkey.toString();
						row[2] = m_uomkey != null ? m_uomkey.toString() : "";
						row[3] = issue.getValueAt(i, 5) != null ? issue.getValueAt(i, 5).toString() : "";
						row[4] = getValueBigDecimal(i, 8).toString();
						row[5] = getValueBigDecimal(i, 7).toString();
						row[6] = getValueBigDecimal(i, 9).toString();
						table.add(row);
					}

				}

			}

			String[][] tableArray = table.toArray(new String[table.size()][]);
			iText.append(createHTMLTable(tableArray));
		}
		info.setText(iText.toString());
	} //  generateInvoices_complete

	/**
	 * Determines whether the Delivery Rule is set to 'OnlyReciept'
	 * @return	
	 */
	private boolean isOnlyReceipt() {
		return (pickcombo.getDisplay().equals("OnlyReceipt"));
	}
	/**
	 * Determines whether the Delivery Rule is set to 'OnlyIssue'
	 * @return	
	 */
	private boolean isOnlyIssue() {
		return (pickcombo.getDisplay().equals("OnlyIssue"));
	}

	/**
	 * Determines whether the Delivery Rule is set to 'isBackflush'
	 * @return	
	 */
	private boolean isBackflush() {
		return (pickcombo.getDisplay().equals("IsBackflush"));
	}

	private Timestamp getMovementDate() {
		return (Timestamp) movementDate.getValue();
	}

	private BigDecimal getToDeliverQty() {
		BigDecimal bd = (BigDecimal) toDeliverQty.getValue();
		return bd != null ? bd : Env.ZERO;
	}

	private BigDecimal getScrapQty() {
		BigDecimal bd = (BigDecimal) scrapQty.getValue();
		return bd != null ? bd : Env.ZERO;
	}

	private BigDecimal getRejectQty() {
		BigDecimal bd = (BigDecimal) rejectQty.getValue();
		return bd != null ? bd : Env.ZERO;
	}

	private BigDecimal getOpenQty() {
		BigDecimal bd = (BigDecimal) openQty.getValue();
		return bd != null ? bd : Env.ZERO;
	}

	private int getM_AttributeSetInstance_ID() {
		Integer asi = (Integer) attribute.getValue();
		return asi != null ? asi.intValue() : 0;
	}

	private int getM_Locator_ID() {
		Integer ii = (Integer) locator.getValue();
		return ii != null ? ii.intValue() : 0;
	}

	private int getPP_Order_ID() {
		Integer ii = (Integer) order.getValue();
		return ii != null ? ii.intValue() : 0;
	}

	/**
	 * Creates a HTML Table out of a two dimensional array.
	 * @param table	A two dimensional array of strings that will be rendered into an html table
	 * @return		The html for the table
	 */
	private String createHTMLTable(String[][] table)
	{
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

	private MStorage[] getStorages(int m_M_Product_ID, int M_ASI_ID, Timestamp minGuaranteeDate)
	{
		Properties ctx = Env.getCtx();
		MProduct product = MProduct.get(ctx, m_M_Product_ID);
		if (product != null && product.get_ID() != 0 && product.isStocked()) {
			String MMPolicy = product.getMMPolicy();
			return MStorage.getWarehouse(ctx,
					m_PP_order.getM_Warehouse_ID(),
					m_M_Product_ID,
					M_ASI_ID == 1 ? m_PP_order.getM_AttributeSetInstance_ID() : M_ASI_ID,
							product.getM_AttributeSet_ID(),
							true, minGuaranteeDate,
							MClient.MMPOLICY_FiFo.equals(MMPolicy), null);
		}
		else {
			return new MStorage[0];
		}
	}

	private BigDecimal getValueBigDecimal(int row, int col) {
		BigDecimal bd = (BigDecimal)issue.getValueAt(row, col);
		return bd == null ? Env.ZERO: bd; 
	}
}
