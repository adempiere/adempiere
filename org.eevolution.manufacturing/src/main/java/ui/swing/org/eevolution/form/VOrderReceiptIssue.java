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
 *                 Teo Sarca, http://www.arhipac.ro                           *
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
import java.sql.Timestamp;
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
import org.compiere.minigrid.IMiniTable;
import org.compiere.minigrid.MiniTable;
import org.compiere.model.GridField;
import org.compiere.model.GridFieldVO;
import org.compiere.model.MColumn;
import org.compiere.model.MLocatorLookup;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MPAttributeLookup;
import org.compiere.model.MProduct;
import org.compiere.model.MTab;
import org.compiere.model.MWindow;
import org.compiere.plaf.CompiereColor;
import org.compiere.swing.CButton;
import org.compiere.swing.CLabel;
import org.compiere.swing.CPanel;
import org.compiere.swing.CTextField;
import org.compiere.swing.CTextPane;
import org.compiere.util.ASyncProcess;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Language;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.util.TrxRunnable;
import org.eevolution.model.MPPOrder;

/**
 * 
 * @author victor.perez@e-evolution.com http://www.e-evolution.com
 * @author Teo Sarca, http://www.arhipac.ro
 */

public class VOrderReceiptIssue extends OrderReceiptIssue implements FormPanel,
		ActionListener, VetoableChangeListener, ChangeListener,
		ListSelectionListener, TableModelListener, ASyncProcess {
	
	private CPanel panel = new CPanel();


	/** Window No */
	private int m_WindowNo = 0;
	/** FormFrame */
	private FormFrame m_frame;
	private StatusBar statusBar = new StatusBar();

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
	private VNumber orderedQtyField = new VNumber("QtyOrdered", false, false,
			false, DisplayType.Quantity, "QtyOrdered");
	private CLabel orderedQtyLabel = new CLabel();
	private VNumber deliveredQtyField = new VNumber("QtyDelivered", false,
			false, false, DisplayType.Quantity, "QtyDelivered");
	private CLabel deliveredQtyLabel = new CLabel();
	private VNumber openQtyField = new VNumber("QtyBackOrdered", false, false, false,
			DisplayType.Quantity, "QtyBackOrdered");
	private CLabel openQtyLabel = new CLabel();
	private VNumber toDeliverQty = new VNumber("QtyToDeliver", true, false,
			true, DisplayType.Quantity, "QtyToDeliver");
	private CLabel toDeliverQtyLabel = new CLabel();
	private javax.swing.JScrollPane issueScrollPane = new JScrollPane();
	private MiniTable issue = new MiniTable();
	private VDate movementDateField = new VDate("MovementDate", true, false,
			true, DisplayType.Date, "MovementDate");
	private CLabel movementDateLabel = new CLabel();
	private VLookup orderField = null;
	private CLabel orderLabel = new CLabel();
	private VNumber rejectQty = new VNumber("Qtyreject", false, false, true,
			DisplayType.Quantity, "QtyReject");
	private CLabel rejectQtyLabel = new CLabel();
	private VLookup resourceField = null;
	private CLabel resourceLabel = new CLabel();
	private VLookup warehouseField = null;
	private CLabel warehouseLabel = new CLabel();
	private VNumber scrapQtyField = new VNumber("Qtyscrap", false, false, true,
			DisplayType.Quantity, "Qtyscrap");
	private CLabel scrapQtyLabel = new CLabel();
	private CLabel backflushGroupLabel = new CLabel(Msg.translate(Env.getCtx(),
			"BackflushGroup"));
	private CTextField backflushGroup = new CTextField(10);
	private CLabel productLabel = new CLabel(Msg.translate(Env.getCtx(),
			"M_Product_ID"));
	private VLookup productField = null;
	private CLabel uomLabel = new CLabel(
			Msg.translate(Env.getCtx(), "C_UOM_ID"));
	private VLookup uomField = null;
	private CLabel uomorderLabel = new CLabel(Msg.translate(Env.getCtx(),
			"Altert UOM"));
	private VLookup uomorderField = null;
	private CLabel locatorLabel = new CLabel(Msg.translate(Env.getCtx(),
			"M_Locator_ID"));
	private VLocator locatorField = null;
	private CLabel labelcombo = new CLabel(Msg.translate(Env.getCtx(),
			"DeliveryRule"));
	private VComboBox pickcombo = new VComboBox();
	private CLabel QtyBatchsLabel = new CLabel();
	private VNumber qtyBatchsField = new VNumber("QtyBatchs", false, false,
			false, DisplayType.Quantity, "QtyBatchs");
	private CLabel QtyBatchSizeLabel = new CLabel();
	private VNumber qtyBatchSizeField = new VNumber("QtyBatchSize", false,
			false, false, DisplayType.Quantity, "QtyBatchSize");
	private CTextPane info = new CTextPane();

	/**
	 * Initialize Panel
	 * 
	 * @param WindowNo
	 *            window
	 * @param frame
	 *            frame
	 */
	public void init(int WindowNo, FormFrame frame) {
		m_WindowNo = WindowNo;
		m_frame = frame;
		log.info("VOrderReceipIssue.init - WinNo=" + m_WindowNo);

		try {
			// UI
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
	} // init

	/**
	 * Fill Picks Column_ID from C_Order This is only run as part of the windows
	 * initialization process
	 * 
	 * @throws Exception
	 *             if Lookups cannot be initialized
	 */
	private void fillPicks() throws Exception {

		Properties ctx = Env.getCtx();

		Language language = Language.getLoginLanguage(); // Base Language
		MLookup orderL = MLookupFactory.get(ctx, m_WindowNo, MColumn
				.getColumn_ID(MPPOrder.Table_Name,
						MPPOrder.COLUMNNAME_PP_Order_ID), DisplayType.Search,
				language, "PP_Order_ID", 0, false, "PP_Order.DocStatus = '"
						+ MPPOrder.DOCACTION_Complete + "'");

		orderField = new VLookup("PP_Order_ID", false, false, true, orderL);
		orderField.setBackground(AdempierePLAF.getInfoBackground());
		orderField.addVetoableChangeListener(this);

		MLookup resourceL = MLookupFactory.get(ctx, m_WindowNo, 0, MColumn
				.getColumn_ID(MPPOrder.Table_Name,
						MPPOrder.COLUMNNAME_S_Resource_ID),
				DisplayType.TableDir);
		resourceField = new VLookup("S_Resource_ID", false, false, false,
				resourceL);

		MLookup warehouseL = MLookupFactory.get(ctx, m_WindowNo, 0, MColumn
				.getColumn_ID(MPPOrder.Table_Name,
						MPPOrder.COLUMNNAME_M_Warehouse_ID),
				DisplayType.TableDir);
		warehouseField = new VLookup("M_Warehouse_ID", false, false, false,
				warehouseL);

		MLookup productL = MLookupFactory
				.get(ctx, m_WindowNo, 0, MColumn.getColumn_ID(
						MPPOrder.Table_Name, MPPOrder.COLUMNNAME_M_Product_ID),
						DisplayType.TableDir);
		productField = new VLookup("M_Product_ID", false, false, false,
				productL);

		MLookup uomL = MLookupFactory.get(ctx, m_WindowNo, 0,
				MColumn.getColumn_ID(MPPOrder.Table_Name,
						MPPOrder.COLUMNNAME_C_UOM_ID), DisplayType.TableDir);
		uomField = new VLookup("C_UOM_ID", false, false, false, uomL);

		MLookup uomorderL = MLookupFactory.get(ctx, m_WindowNo, 0,
				MColumn.getColumn_ID(MPPOrder.Table_Name,
						MPPOrder.COLUMNNAME_C_UOM_ID), DisplayType.TableDir);
		uomorderField = new VLookup("C_UOM_ID", false, false, false, uomorderL);

		MLocatorLookup locatorL = new MLocatorLookup(ctx, m_WindowNo);
		locatorField = new VLocator("M_Locator_ID", true, false, true,
				locatorL, m_WindowNo);

		MPAttributeLookup attributeL = new MPAttributeLookup(ctx, m_WindowNo);
		attribute = new VPAttribute(false, false, true, m_WindowNo, attributeL, false);
		attribute.setValue(0);
		// Tab, Window
		int m_Window = MWindow.getWindow_ID("Manufacturing Order");
		GridFieldVO vo = GridFieldVO.createStdField(ctx, m_WindowNo, 0,
				m_Window, MTab.getTab_ID(m_Window, "Order"), false, false,
				false);
		// M_AttributeSetInstance_ID
		vo.AD_Column_ID = MColumn.getColumn_ID(MPPOrder.Table_Name,
				MPPOrder.COLUMNNAME_M_AttributeSetInstance_ID);
		GridField field = new GridField(vo);
		attribute.setField(field);
		// 4Layers - Further init
		scrapQtyField.setValue(Env.ZERO);
		rejectQty.setValue(Env.ZERO);
		// 4Layers - end
		pickcombo.addItem(new KeyNamePair(1, Msg.parseTranslation(Env.getCtx(),
				"@IsBackflush@")));
		pickcombo.addItem(new KeyNamePair(2, Msg.parseTranslation(Env.getCtx(),
				"@OnlyIssue@")));
		pickcombo.addItem(new KeyNamePair(3, Msg.parseTranslation(Env.getCtx(),
				"@OnlyReceiptProduct@")));
		pickcombo.addActionListener(this);
		Process.addActionListener(this);
		toDeliverQty.addActionListener(this);
		scrapQtyField.addActionListener(this);
	} // fillPicks

	/**
	 * Static Init. Places static visual elements into the window. This is only
	 * run as part of the windows initialization process
	 * 
	 * <pre>
	 *  mainPanel
	 *      northPanel
	 *      centerPanel
	 *          xMatched
	 *          xPanel
	 *          xMathedTo
	 *      southPanel
	 * </pre>
	 * 
	 * @throws Exception
	 */
	private void jbInit() throws Exception {
		panel.setLayout(new java.awt.BorderLayout());
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
				GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5,
						5, 5, 5), 0, 0));
		northPanel.add(orderField, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));
		resourceLabel.setText(Msg.translate(Env.getCtx(), "S_Resource_ID"));
		northPanel.add(resourceLabel, new GridBagConstraints(2, 1, 1, 1, 0.0,
				0.0, GridBagConstraints.EAST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5), 0, 0));
		northPanel.add(resourceField, new GridBagConstraints(3, 1, 1, 1, 0.0,
				0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));
		warehouseLabel.setText(Msg.translate(Env.getCtx(), "M_Warehouse_ID"));
		northPanel.add(warehouseLabel, new GridBagConstraints(4, 1, 1, 1, 0.0,
				0.0, GridBagConstraints.EAST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5), 0, 0));
		northPanel.add(warehouseField, new GridBagConstraints(5, 1, 1, 1, 0.0,
				0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));
		northPanel.add(warehouseLabel, new GridBagConstraints(4, 1, 1, 1, 0.0,
				0.0, GridBagConstraints.EAST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5), 0, 0));

		// Product

		northPanel.add(productLabel, new GridBagConstraints(0, 2, 1, 1, 0.0,
				0.0, GridBagConstraints.EAST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5), 0, 0));
		northPanel.add(productField, new GridBagConstraints(1, 2, 1, 1, 0.0,
				0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));
		northPanel.add(uomLabel, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,
				GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5,
						5, 5, 5), 0, 0));
		northPanel.add(uomField, new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));
		northPanel.add(uomorderLabel, new GridBagConstraints(4, 2, 1, 1, 0.0,
				0.0, GridBagConstraints.EAST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5), 0, 0));
		northPanel.add(uomorderField, new GridBagConstraints(5, 2, 1, 1, 0.0,
				0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));
		orderedQtyLabel.setText(Msg.translate(Env.getCtx(), "QtyOrdered"));
		northPanel.add(orderedQtyLabel, new GridBagConstraints(0, 3, 1, 1, 0.0,
				0.0, GridBagConstraints.EAST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5), 0, 0));
		northPanel.add(orderedQtyField, new GridBagConstraints(1, 3, 1, 1, 0.0,
				0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));
		deliveredQtyLabel.setText(Msg.translate(Env.getCtx(), "QtyDelivered"));
		northPanel.add(deliveredQtyLabel, new GridBagConstraints(2, 3, 1, 1,
				0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5), 0, 0));
		northPanel.add(deliveredQtyField, new GridBagConstraints(3, 3, 1, 1,
				0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		openQtyLabel.setText(Msg.translate(Env.getCtx(), "QtyBackOrdered"));
		northPanel.add(openQtyLabel, new GridBagConstraints(4, 3, 1, 1, 0.0,
				0.0, GridBagConstraints.EAST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5), 0, 0));
		northPanel.add(openQtyField, new GridBagConstraints(5, 3, 1, 1, 0.0,
				0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));
		QtyBatchsLabel.setText(Msg.translate(Env.getCtx(), "QtyBatchs"));
		northPanel.add(QtyBatchsLabel, new GridBagConstraints(2, 4, 1, 1, 0.0,
				0.0, GridBagConstraints.EAST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5), 0, 0));
		northPanel.add(qtyBatchsField, new GridBagConstraints(3, 4, 1, 1, 0.0,
				0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));
		QtyBatchSizeLabel.setText(Msg.translate(Env.getCtx(), "QtyBatchSize"));
		northPanel.add(QtyBatchSizeLabel, new GridBagConstraints(4, 4, 1, 1,
				0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5), 0, 0));
		northPanel.add(qtyBatchSizeField, new GridBagConstraints(5, 4, 1, 1,
				0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		northPanel.add(labelcombo, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0,
				GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5,
						5, 5, 5), 0, 0));
		northPanel.add(pickcombo, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0,
				GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));
		toDeliverQtyLabel.setText(Msg.translate(Env.getCtx(), "QtyToDeliver"));
		northPanel.add(toDeliverQtyLabel, new GridBagConstraints(0, 6, 1, 1,
				0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5), 0, 0));
		northPanel.add(toDeliverQty, new GridBagConstraints(1, 6, 1, 1, 0.0,
				0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));
		scrapQtyLabel.setText(Msg.translate(Env.getCtx(), "QtyScrap"));
		northPanel.add(scrapQtyLabel, new GridBagConstraints(2, 6, 1, 1, 0.0,
				0.0, GridBagConstraints.EAST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5), 0, 0));
		northPanel.add(scrapQtyField, new GridBagConstraints(3, 6, 1, 1, 0.0,
				0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));
		rejectQtyLabel.setText(Msg.translate(Env.getCtx(), "QtyReject"));
		northPanel.add(rejectQtyLabel, new GridBagConstraints(4, 6, 1, 1, 0.0,
				0.0, GridBagConstraints.EAST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5), 0, 0));
		northPanel.add(rejectQty, new GridBagConstraints(5, 6, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));
		movementDateLabel.setText(Msg.translate(Env.getCtx(), "MovementDate"));
		northPanel.add(movementDateLabel, new GridBagConstraints(0, 7, 1, 1,
				0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5), 0, 0));
		northPanel.add(movementDateField, new GridBagConstraints(1, 7, 1, 1,
				0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		locatorLabel.setText(Msg.translate(Env.getCtx(), "M_Locator_ID"));
		northPanel.add(locatorLabel, new GridBagConstraints(2, 7, 1, 1, 0.0,
				0.0, GridBagConstraints.EAST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5), 0, 0));
		northPanel.add(locatorField, new GridBagConstraints(3, 7, 1, 1, 0.0,
				0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));
		attributeLabel.setText(Msg.translate(Env.getCtx(),
				"M_AttributeSetInstance_ID"));
		northPanel.add(attributeLabel, new GridBagConstraints(4, 7, 1, 1, 0.0,
				0.0, GridBagConstraints.EAST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5), 0, 0));
		northPanel.add(attribute, new GridBagConstraints(5, 7, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));
		northPanel.add(backflushGroupLabel, new GridBagConstraints(2, 5, 1, 1,
				0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5), 0, 0));
		northPanel.add(backflushGroup, new GridBagConstraints(3, 5, 1, 1, 0.0,
				0.0, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));

		ReceiptIssueOrder.add(northPanel, java.awt.BorderLayout.NORTH);
		TabsReceiptsIssue.add(ReceiptIssueOrder,
				Msg.parseTranslation(Env.getCtx(), "@IsShipConfirm@"));
		TabsReceiptsIssue
				.add(Generate, Msg.translate(Env.getCtx(), "Generate"));
		Generate.setLayout(new BorderLayout());
		Generate.add(info, BorderLayout.CENTER);
		Generate.setEnabled(false);
		info.setBackground(AdempierePLAF.getFieldBackground_Inactive());
		info.setEditable(false);
		TabsReceiptsIssue.addChangeListener(this);
		panel.add(TabsReceiptsIssue, java.awt.BorderLayout.CENTER);
		mainPanel.add(TabsReceiptsIssue, java.awt.BorderLayout.CENTER);
		panel.add(mainPanel, java.awt.BorderLayout.NORTH);
	} // jbInit

	/**
	 * Dynamic Init. Table Layout, Visual, Listener This is only run as part of
	 * the windows initialization process
	 */
	public void dynInit() {
		disableToDeliver();

		configureMiniTable(issue);

		issue.getModel().addTableModelListener(this);

		CompiereColor.setBackground(panel);
		issue.setCellSelectionEnabled(true);
		issue.getSelectionModel().addListSelectionListener(this);
		// issue.setRowCount(0);
	} // dynInit

	/**
	 * Called when events occur in the window
	 */
	public void actionPerformed(ActionEvent e) {
		log.fine("Event:" + e.getSource());
		panel.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

		if (e.getActionCommand().equals(ConfirmPanel.A_CANCEL)) {
			dispose();
			return;
		}

		if (e.getSource().equals(Process)) {
			if (getMovementDate() == null) {
				JOptionPane.showMessageDialog(null,
						Msg.parseTranslation(Env.getCtx(), "@MovementDate@ @NotFound@"), "Info",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}

			if ((isOnlyReceipt() || isBackflush()) && getM_Locator_ID() <= 0) {
				JOptionPane.showMessageDialog(null,
						Msg.parseTranslation(Env.getCtx(), "@M_Locator_ID@ @NotFound@ "), "Info",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}

			// Switch Tabs
			TabsReceiptsIssue.setSelectedIndex(1);

			generateSummaryTable();

			if (ADialog.ask(m_WindowNo, panel, "Update")) {
				panel.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				final boolean isCloseDocument = ADialog.ask(m_WindowNo, panel,
						Msg.parseTranslation(Env.getCtx(),
								"@IsCloseDocument@ : "
										+ getPP_Order().getDocumentNo()));

				if (cmd_process(isCloseDocument, issue)) {
					dispose();
					return;
				}
				panel.setCursor(Cursor.getDefaultCursor());
			}
			TabsReceiptsIssue.setSelectedIndex(0);
		}

		if (e.getSource().equals(toDeliverQty)
				|| e.getSource().equals(scrapQtyField)) {
			if (getPP_Order_ID() > 0 && isBackflush()) {
				executeQuery();
			}
		}

		if (e.getSource().equals(pickcombo)) {
			if (isOnlyReceipt()) {
				enableToDeliver();
				locatorLabel.setVisible(true);
				locatorField.setVisible(true);
				attribute.setVisible(true);
				attributeLabel.setVisible(true);
				issue.setVisible(false);
			} else if (isOnlyIssue()) {
				disableToDeliver();
				locatorLabel.setVisible(false);
				locatorField.setVisible(false);
				attribute.setVisible(false);
				attributeLabel.setVisible(false);
				issue.setVisible(true);
				executeQuery();
			} else if (isBackflush()) {
				enableToDeliver();
				locatorLabel.setVisible(true);
				locatorField.setVisible(true);
				attribute.setVisible(true);
				attributeLabel.setVisible(true);
				issue.setVisible(true);
				executeQuery();
			}
			setToDeliverQty(getOpenQty()); // reset toDeliverQty to openQty
		}
	}

	public void enableToDeliver() {
		setToDeliver(true);
	}

	public void disableToDeliver() {
		setToDeliver(false);
	}

	private void setToDeliver(Boolean state) {
		toDeliverQty.getComponent(0).setEnabled(state); // textbox component
		toDeliverQty.getComponent(1).setEnabled(state); // button component
		scrapQtyLabel.setVisible(state);
		scrapQtyField.setVisible(state);
		rejectQtyLabel.setVisible(state);
		rejectQty.setVisible(state);
	}

	/**
	 * Queries for and fills the table in the lower half of the screen This is
	 * only run if isBackflush() or isOnlyIssue
	 */
	public void executeQuery() {
		issue.removeAll();
		executeQuery(issue);

	} // executeQuery

	public void dispose() {
		if (m_frame != null)
			m_frame.dispose();
		m_frame = null;
	}

	public void vetoableChange(PropertyChangeEvent e)
			throws PropertyVetoException {
		String name = e.getPropertyName();
		Object value = e.getNewValue();
		log.fine("VOrderReceip.vetoableChange - " + name + "=" + value);
		if (value == null)
			return;

		// PP_Order_ID
		if (name.equals("PP_Order_ID")) {
			orderField.setValue(value);

			MPPOrder pp_order = getPP_Order();
			if (pp_order != null) {
				setS_Resource_ID(pp_order.getS_Resource_ID());
				setM_Warehouse_ID(pp_order.getM_Warehouse_ID());
				setDeliveredQty(pp_order.getQtyDelivered());
				setOrderedQty(pp_order.getQtyOrdered());
				// m_PP_order.getQtyOrdered().subtract(m_PP_order.getQtyDelivered());
				setQtyBatchs(pp_order.getQtyBatchs());
				setQtyBatchSize(pp_order.getQtyBatchSize());
				setOpenQty(pp_order.getQtyOrdered().subtract(
						pp_order.getQtyDelivered()));
				setToDeliverQty(getOpenQty());
				setM_Product_ID(pp_order.getM_Product_ID());
				MProduct m_product = MProduct.get(Env.getCtx(),
						pp_order.getM_Product_ID());
				setC_UOM_ID(m_product.getC_UOM_ID());
				setOrder_UOM_ID(pp_order.getC_UOM_ID());
				// Default ASI defined from the Parent BOM Order
				setM_AttributeSetInstance_ID(pp_order.getMPPOrderBOM()
						.getM_AttributeSetInstance_ID());
				pickcombo.setSelectedIndex(0); // default to first entry -
												// isBackflush
			}
		} // PP_Order_ID
	}

	/**
	 * Performs what ever task is attached to the combo box
	 * 
	 * @return Whether the process was successful or not
	 */
	@Override
	public void showMessage(String message, boolean error) {
		if (!error)
			JOptionPane.showMessageDialog(null, message, "Info",
					JOptionPane.INFORMATION_MESSAGE);
		else
			ADialog.error(m_WindowNo, panel, "Error", message);
	}

	/**
	 * Generate Summary of Issue/Receipt.
	 */

	private void generateSummaryTable() {

		info.setText(generateSummaryTable(issue, productField.getDisplay(),
				uomField.getDisplay(), attribute.getDisplay(),
				toDeliverQty.getDisplay(), deliveredQtyField.getDisplay(),
				scrapQtyField.getDisplay(), isBackflush(), isOnlyIssue(),
				isOnlyReceipt()));

	} // generateInvoices_complete

	/**
	 * Determines whether the Delivery Rule is set to 'OnlyReciept'
	 * 
	 * @return
	 */

	protected boolean isOnlyReceipt() {
		super.setIsOnlyReceipt(pickcombo.getValue().equals(3));
		return super.isOnlyReceipt();
	}

	/**
	 * Determines whether the Delivery Rule is set to 'OnlyIssue'
	 * 
	 * @return
	 */

	protected boolean isOnlyIssue() {
		super.setIsOnlyIssue(pickcombo.getValue().equals(2));
		return super.isOnlyIssue();
	}

	/**
	 * Determines whether the Delivery Rule is set to 'isBackflush'
	 * 
	 * @return
	 */
	protected boolean isBackflush() {
		super.setIsBackflush(pickcombo.getValue().equals(1));
		return super.isBackflush();
	}

	protected Timestamp getMovementDate() {
		return (Timestamp) movementDateField.getValue();
	}

	protected BigDecimal getOrderedQty() {
		BigDecimal bd = (BigDecimal) orderedQtyField.getValue();
		return bd != null ? bd : Env.ZERO;
	}

	protected void setOrderedQty(BigDecimal qty) {
		this.orderedQtyField.setValue(qty);
	}

	protected BigDecimal getDeliveredQty() {
		BigDecimal bd = (BigDecimal) deliveredQtyField.getValue();
		return bd != null ? bd : Env.ZERO;
	}

	protected void setDeliveredQty(BigDecimal qty) {
		deliveredQtyField.setValue(qty);
	}

	protected BigDecimal getToDeliverQty() {
		BigDecimal bd = (BigDecimal) toDeliverQty.getValue();
		return bd != null ? bd : Env.ZERO;
	}

	protected void setToDeliverQty(BigDecimal qty) {
		toDeliverQty.setValue(qty);
	}

	protected BigDecimal getScrapQty() {
		BigDecimal bd = (BigDecimal) scrapQtyField.getValue();
		return bd != null ? bd : Env.ZERO;
	}

	protected BigDecimal getRejectQty() {
		BigDecimal bd = (BigDecimal) rejectQty.getValue();
		return bd != null ? bd : Env.ZERO;
	}

	protected BigDecimal getOpenQty() {
		BigDecimal bd = (BigDecimal) openQtyField.getValue();
		return bd != null ? bd : Env.ZERO;
	}

	protected void setOpenQty(BigDecimal qty) {
		openQtyField.setValue(qty);
	}

	protected BigDecimal getQtyBatchs() {
		BigDecimal bd = (BigDecimal) qtyBatchsField.getValue();
		return bd != null ? bd : Env.ZERO;
	}

	protected void setQtyBatchs(BigDecimal qty) {
		qtyBatchsField.setValue(qty);
	}

	protected BigDecimal getQtyBatchSize() {
		BigDecimal bd = (BigDecimal) qtyBatchSizeField.getValue();
		return bd != null ? bd : Env.ZERO;
	}

	protected void setQtyBatchSize(BigDecimal qty) {
		qtyBatchSizeField.setValue(qty);
	}

	protected int getM_AttributeSetInstance_ID() {
		Integer ii = (Integer) attribute.getValue();
		return ii != null ? ii.intValue() : 0;
	}

	protected void setM_AttributeSetInstance_ID(int M_AttributeSetInstance_ID) {
		attribute.setValue(M_AttributeSetInstance_ID);
	}

	protected int getM_Locator_ID() {
		Integer ii = (Integer) locatorField.getValue();
		return ii != null ? ii.intValue() : 0;
	}

	protected void setM_Locator_ID(int M_Locator_ID) {
		locatorField.setValue(M_Locator_ID);
	}

	protected int getPP_Order_ID() {
		Integer ii = (Integer) orderField.getValue();
		return ii != null ? ii.intValue() : 0;
	}

	private MPPOrder m_PP_order = null;

	protected MPPOrder getPP_Order() {
		int id = getPP_Order_ID();
		if (id <= 0) {
			m_PP_order = null;
			return null;
		}
		if (m_PP_order == null || m_PP_order.get_ID() != id) {

			m_PP_order = new MPPOrder(Env.getCtx(), id, null);
		}
		return m_PP_order;
	}

	protected int getS_Resource_ID() {
		Integer ii = (Integer) resourceField.getValue();
		return ii != null ? ii.intValue() : 0;
	}

	protected void setS_Resource_ID(int S_Resource_ID) {
		resourceField.setValue(S_Resource_ID);
	}

	protected int getM_Warehouse_ID() {
		Integer ii = (Integer) warehouseField.getValue();
		return ii != null ? ii.intValue() : 0;
	}

	protected void setM_Warehouse_ID(int M_Warehouse_ID) {
		warehouseField.setValue(M_Warehouse_ID);
	}

	protected int getM_Product_ID() {
		Integer ii = (Integer) productField.getValue();
		return ii != null ? ii.intValue() : 0;
	}

	protected void setM_Product_ID(int M_Product_ID) {
		productField.setValue(M_Product_ID);
		Env.setContext(Env.getCtx(), m_WindowNo, "M_Product_ID", M_Product_ID);
	}

	protected int getC_UOM_ID() {
		Integer ii = (Integer) uomField.getValue();
		return ii != null ? ii.intValue() : 0;
	}

	protected void setC_UOM_ID(int C_UOM_ID) {
		uomField.setValue(C_UOM_ID);
	}

	protected int getOrder_UOM_ID() {
		Integer ii = (Integer) uomorderField.getValue();
		return ii != null ? ii.intValue() : 0;
	}

	protected void setOrder_UOM_ID(int C_UOM_ID) {
		uomorderField.setValue(C_UOM_ID);
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

	public boolean cmd_process(final boolean isCloseDocument,
			final IMiniTable issue) {

		if (isOnlyReceipt() || isBackflush()) {
			if (getM_Locator_ID() <= 0) {
				// JOptionPane.showMessageDialog(null,
				// Msg.getMsg(Env.getCtx(),"NoLocator"), "Info",
				// JOptionPane.INFORMATION_MESSAGE);
				showMessage(Msg.parseTranslation(Env.getCtx(), "@M_Locator_ID@ @NotFound@"), false);
			}
		}
		if (getPP_Order() == null || getMovementDate() == null) {
			return false;
		}
		try {
			Trx.run(new TrxRunnable() {
				public void run(String trxName) {
					MPPOrder order = new MPPOrder(Env.getCtx(),
							getPP_Order_ID(), trxName);
					if (isBackflush() || isOnlyIssue())
						createIssue(order, issue);

					if (isOnlyReceipt() || isBackflush()) {
						MPPOrder.createReceipt(order, getMovementDate(),
								getDeliveredQty(), getToDeliverQty(),
								getScrapQty(), getRejectQty(),
								getM_Locator_ID(),
								getM_AttributeSetInstance_ID());
						if (isCloseDocument) {
							order.setDateFinish(getMovementDate());
							order.closeIt();
							order.saveEx();
						}
					}
				}
			});
		} catch (Exception e) {
			showMessage(e.getLocalizedMessage(), true);
			e.printStackTrace();
			return false;
		} finally {
			m_PP_order = null;
		}

		return true;
	}
}
