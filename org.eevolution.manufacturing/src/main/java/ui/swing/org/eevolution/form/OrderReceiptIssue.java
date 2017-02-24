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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;

import org.adempiere.exceptions.DBException;
import org.compiere.apps.form.GenForm;
import org.compiere.minigrid.IDColumn;
import org.compiere.minigrid.IMiniTable;
import org.compiere.model.MAttributeSetInstance;
import org.compiere.model.MOrder;
import org.compiere.model.MProduct;
import org.compiere.model.MStorage;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.eevolution.model.MPPOrder;
import org.eevolution.model.MPPOrderBOMLine;
import org.eevolution.model.MPPProductBOMLine;

/**
 * 
 * @author victor.perez@e-evolution.com http://www.e-evolution.com
 */

public class OrderReceiptIssue extends GenForm {

	/** Logger */
	private static CLogger log = CLogger.getCLogger(OrderReceiptIssue.class);
	String m_sql = "";

	private boolean m_isOnlyReceipt = false;

	private boolean m_OnlyIssue = false;

	protected boolean m_IsBackflush = false;

	protected Timestamp m_movementDate = null;

	protected BigDecimal m_orderedQty = Env.ZERO;

	protected BigDecimal m_DeliveredQty = Env.ZERO;

	protected BigDecimal m_toDeliverQty = Env.ZERO;

	protected BigDecimal m_scrapQty = Env.ZERO;

	protected BigDecimal m_rejectQty = Env.ZERO;

	protected BigDecimal m_openQty = Env.ZERO;

	protected BigDecimal m_qtyBatchs = Env.ZERO;

	protected BigDecimal m_qtyBatchSize = Env.ZERO;

	protected int m_M_AttributeSetInstance_ID = 0;

	protected int m_M_Locator_ID = 0;

	private int m_PP_Order_ID = 0;

	private MPPOrder m_PP_order = null;

	public void configureMiniTable(IMiniTable issue) {
		issue.addColumn(MPPOrderBOMLine.COLUMNNAME_PP_Order_BOMLine_ID); // 0
		issue.addColumn(MPPOrderBOMLine.COLUMNNAME_IsCritical); // 1
		issue.addColumn("Value"); // 2
		issue.addColumn(MPPOrderBOMLine.COLUMNNAME_M_Product_ID); // 3
		issue.addColumn(MPPOrderBOMLine.COLUMNNAME_C_UOM_ID); // 4
		issue.addColumn(MPPOrderBOMLine.COLUMNNAME_M_AttributeSetInstance_ID); // 5
		issue.addColumn(MPPOrderBOMLine.COLUMNNAME_QtyRequired); // 6
		issue.addColumn(MPPOrderBOMLine.COLUMNNAME_QtyDelivered); // 7
		issue.addColumn("QtyToDeliver"); // 8
		issue.addColumn(MPPOrderBOMLine.COLUMNNAME_QtyScrap); // 9
		issue.addColumn("QtyOnHand"); // 10
		issue.addColumn(MPPOrderBOMLine.COLUMNNAME_QtyReserved); // 11
		issue.addColumn("QtyAvailable"); // 12
		issue.addColumn(MPPOrderBOMLine.COLUMNNAME_M_Locator_ID); // 13
		issue.addColumn(MPPOrderBOMLine.COLUMNNAME_M_Warehouse_ID); // 14
		issue.addColumn(MPPOrderBOMLine.COLUMNNAME_QtyBOM); // 15
		issue.addColumn(MPPOrderBOMLine.COLUMNNAME_IsQtyPercentage); // 16
		issue.addColumn(MPPOrderBOMLine.COLUMNNAME_QtyBatch); // 17

		issue.setMultiSelection(true);
		// issue.setRowSelectionAllowed(true);

		// set details
		issue.setColumnClass(0, IDColumn.class, false, " ");
		issue.setColumnClass(1, Boolean.class, true,
				Msg.translate(Env.getCtx(), "IsCritical"));
		issue.setColumnClass(2, String.class, true,
				Msg.translate(Env.getCtx(), "Value"));
		issue.setColumnClass(3, KeyNamePair.class, true,
				Msg.translate(Env.getCtx(), "M_Product_ID"));
		issue.setColumnClass(4, KeyNamePair.class, true,
				Msg.translate(Env.getCtx(), "C_UOM_ID"));
		issue.setColumnClass(5, String.class, true,
				Msg.translate(Env.getCtx(), "M_AttributeSetInstance_ID"));
		issue.setColumnClass(6, BigDecimal.class, true,
				Msg.translate(Env.getCtx(), "QtyRequired"));
		issue.setColumnClass(7, BigDecimal.class, true,
				Msg.translate(Env.getCtx(), "QtyDelivered"));
		issue.setColumnClass(8, BigDecimal.class, false,
				Msg.translate(Env.getCtx(), "QtyToDeliver"));
		issue.setColumnClass(9, BigDecimal.class, false,
				Msg.translate(Env.getCtx(), "QtyScrap"));
		issue.setColumnClass(10, BigDecimal.class, true,
				Msg.translate(Env.getCtx(), "QtyOnHand"));
		issue.setColumnClass(11, BigDecimal.class, true,
				Msg.translate(Env.getCtx(), "QtyReserved"));
		issue.setColumnClass(12, BigDecimal.class, true,
				Msg.translate(Env.getCtx(), "QtyAvailable"));
		issue.setColumnClass(13, String.class, true,
				Msg.translate(Env.getCtx(), "M_Locator_ID"));
		issue.setColumnClass(14, KeyNamePair.class, true,
				Msg.translate(Env.getCtx(), "M_Warehouse_ID"));
		issue.setColumnClass(15, BigDecimal.class, true,
				Msg.translate(Env.getCtx(), "QtyBom"));
		issue.setColumnClass(16, Boolean.class, true,
				Msg.translate(Env.getCtx(), "IsQtyPercentage"));
		issue.setColumnClass(17, BigDecimal.class, true,
				Msg.translate(Env.getCtx(), "QtyBatch"));

		issue.autoSize();
		// issue.getModel().addTableModelListener(this);

		// CompiereColor.setBackground(this);
		// issue.setCellSelectionEnabled(true);
		// issue.getSelectionModel().addListSelectionListener(this);
		issue.setRowCount(0);

		m_sql = "SELECT "
				+ "obl.PP_Order_BOMLine_ID," // 1
				+ "obl.IsCritical," // 2
				+ "p.Value," // 3
				+ "obl.M_Product_ID,p.Name," // 4,5
				+ "p.C_UOM_ID,u.Name," // 6,7
				+ "obl.QtyRequired," // 8
				+ "obl.QtyReserved," // 9
				+ "bomQtyAvailable(obl.M_Product_ID,obl.M_Warehouse_ID,0 ) AS QtyAvailable," // 10
				+ "bomQtyOnHand(obl.M_Product_ID,obl.M_Warehouse_ID,0) AS QtyOnHand," // 11
				+ "p.M_Locator_ID," // 12
				+ "obl.M_Warehouse_ID,w.Name," // 13,14
				+ "obl.QtyBom," // 15
				+ "obl.isQtyPercentage," // 16
				+ "obl.QtyBatch," // 17
				+ "obl.ComponentType," // 18
				+ "obl.QtyRequired - QtyDelivered AS QtyOpen," // 19
				+ "obl.QtyDelivered" // 20
				+ " FROM PP_Order_BOMLine obl"
				+ " INNER JOIN M_Product p ON (obl.M_Product_ID = p.M_Product_ID) "
				+ " INNER JOIN C_UOM u ON (p.C_UOM_ID = u.C_UOM_ID) "
				+ " INNER JOIN M_Warehouse w ON (w.M_Warehouse_ID = obl.M_Warehouse_ID) "
				+ " WHERE obl.PP_Order_ID = ?" + " ORDER BY obl."
				+ MPPOrderBOMLine.COLUMNNAME_Line;
	} // dynInit

	private String createHTMLTable(String[][] table) {
		StringBuffer html = new StringBuffer(
				"<table width=\"100%\" border=\"1\" cellspacing=\"0\" cellpadding=\"0\">");

		for (int i = 0; i < table.length; i++) {
			if (table[i] != null) {
				html.append("<tr>");
				for (int j = 0; j < table[i].length; j++) {
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

	/**
	 * Performs what ever task is attached to the combo box
	 * 
	 * @return Whether the process was successful or not
	 */
	/*
	 * public boolean cmd_process(final boolean isCloseDocument, final
	 * IMiniTable issue) {
	 * 
	 * if (isOnlyReceipt() || isBackflush()) { if (getM_Locator_ID() <= 0) {
	 * //JOptionPane.showMessageDialog(null,
	 * Msg.getMsg(Env.getCtx(),"NoLocator"), "Info",
	 * JOptionPane.INFORMATION_MESSAGE); showMessage(
	 * Msg.getMsg(Env.getCtx(),"NoLocator"), false); } } if (getPP_Order() ==
	 * null || getMovementDate() == null) { return false; } try { Trx.run(new
	 * TrxRunnable() { public void run(String trxName) { MPPOrder order = new
	 * MPPOrder(Env.getCtx(), getPP_Order_ID(), trxName); if (isBackflush() ||
	 * isOnlyIssue()) { createIssue(order, issue); } if (isOnlyReceipt() ||
	 * isBackflush()) { MPPOrder.createReceipt(order, getMovementDate(),
	 * getDeliveredQty(), getToDeliverQty(), getScrapQty(), getRejectQty(),
	 * getM_Locator_ID(), getM_AttributeSetInstance_ID() ); if (isCloseDocument)
	 * { order.setDateFinish(getMovementDate()); order.closeIt();
	 * order.saveEx(); } } }}); } catch (Exception e) {
	 * showMessage(e.getLocalizedMessage(), true); return false; } finally {
	 * m_PP_order = null; }
	 * 
	 * return true; }
	 */

	public void createIssue(MPPOrder order, IMiniTable issue) {
		Timestamp movementDate = getMovementDate();
		Timestamp minGuaranteeDate = movementDate;
		boolean isCompleteQtyDeliver = false;

		ArrayList[][] m_issue = new ArrayList[issue.getRowCount()][1];

		int row = 0;
		// Check Available On Hand Qty
		for (int i = 0; i < issue.getRowCount(); i++) {
			ArrayList<Object> data = new ArrayList<Object>();
			IDColumn id = (IDColumn) issue.getValueAt(i, 0);
			KeyNamePair key = new KeyNamePair(id.getRecord_ID(), id.isSelected() ? "Y" : "N");
			data.add(key); // 0 - ID
			data.add(issue.getValueAt(i, 1)); // 1 - IsCritical
			data.add(issue.getValueAt(i, 2)); // 2 - Value
			data.add(issue.getValueAt(i, 3)); // 3 - KeyNamePair Product
			data.add(getValueBigDecimal(issue, i, 8)); // 4 - QtyToDeliver
			data.add(getValueBigDecimal(issue, i, 9)); // 5 - QtyScrapComponent
			KeyNamePair location = (KeyNamePair) issue.getValueAt(i , 13); // 6 - Location Id
			if (location != null)
				data.add(location.getID()); // 7 - Location
			else
				data.add(0);

			m_issue[row][0] = data;
			row++;
		}

		isCompleteQtyDeliver = MPPOrder.isQtyAvailable(order, m_issue,
				minGuaranteeDate);

		for (int i = 0; i < m_issue.length; i++) {
			KeyNamePair key = (KeyNamePair) m_issue[i][0].get(0);
			boolean isSelected = key.getName().equals("Y");
			if (key == null || !isSelected)
				continue;

			Boolean isCritical = (Boolean) m_issue[i][0].get(1);
			String value = (String) m_issue[i][0].get(2);
			KeyNamePair productkey = (KeyNamePair) m_issue[i][0].get(3);
			int productId = productkey.getKey();
			MPPOrderBOMLine orderbomLine = null;
			int orderBOMLineId = 0;
			int attributeSetInstanceId = 0;
			int locatorId = 0;
			BigDecimal qtyToDeliver = (BigDecimal) m_issue[i][0].get(4);
			BigDecimal qtyScrapComponent = (BigDecimal) m_issue[i][0].get(5);
			MProduct product = MProduct.get(order.getCtx(), productId);
			if (product != null && product.get_ID() != 0 && product.isStocked()) {
				if (value == null && isSelected) {
					attributeSetInstanceId =  key.getKey();
					locatorId =  new Integer((String)m_issue[i][0].get(6));
					if (attributeSetInstanceId == 0 )
						attributeSetInstanceId = (Integer) key.getKey();

					orderbomLine = MPPOrderBOMLine.forM_Product_ID(Env.getCtx(), order.get_ID(), productId, order.get_TrxName());
					if (orderbomLine != null)
						orderBOMLineId = orderbomLine.get_ID();

				} else if (value != null && isSelected) {
					orderBOMLineId = (Integer) key.getKey();
					if (orderBOMLineId > 0)
						orderbomLine = new MPPOrderBOMLine(order.getCtx(), orderBOMLineId, order.get_TrxName());attributeSetInstanceId = orderbomLine.getM_AttributeSetInstance_ID();
				}

				MStorage[] totalStorages = MPPOrder.getStorages(Env.getCtx(),
						productId, order.getM_Warehouse_ID(),
						attributeSetInstanceId, minGuaranteeDate,
						order.get_TrxName());

				MStorage[] storages;
				if (locatorId > 0) {
					int finalLocatorId = locatorId;
					storages  = Arrays.stream(totalStorages)
							.filter(storaage -> storaage.getM_Locator_ID() == finalLocatorId)
							.toArray(MStorage[]::new);
				}
				else
					storages = totalStorages;

				boolean forceIssue = false;
				BigDecimal toIssue = qtyToDeliver.add(qtyScrapComponent);

				//allow return quantity order line
				if (storages == null || storages.length == 0 ) {
					if (toIssue.signum() < 0 && toIssue.add(orderbomLine.getQtyDelivered()).signum() >= 0)
						forceIssue = true;
				}

				MPPOrder.createIssue(order, orderbomLine , movementDate,
						qtyToDeliver, qtyScrapComponent, Env.ZERO, storages,
						forceIssue);
			}
		}
	}

	/**
	 * Query Info
	 */
	public void executeQuery(IMiniTable issue) {
		final String sql = "SELECT "
				+ "obl.PP_Order_BOMLine_ID," // 1
				+ "obl.IsCritical," // 2
				+ "p.Value," // 3
				+ "obl.M_Product_ID,p.Name," // 4,5
				+ "p.C_UOM_ID,u.Name," // 6,7
				+ "obl.QtyRequired," // 8
				+ "obl.QtyReserved," // 9
				+ "bomQtyAvailable(obl.M_Product_ID,obl.M_Warehouse_ID,0 ) AS QtyAvailable," // 10
				+ "bomQtyOnHand(obl.M_Product_ID,obl.M_Warehouse_ID,0) AS QtyOnHand," // 11
				+ "p.M_Locator_ID," // 12
				+ "obl.M_Warehouse_ID,w.Name," // 13,14
				+ "obl.QtyBom," // 15
				+ "obl.isQtyPercentage," // 16
				+ "obl.QtyBatch," // 17
				+ "obl.ComponentType," // 18
				+ "obl.QtyRequired - QtyDelivered AS QtyOpen," // 19
				+ "obl.QtyDelivered" // 20
				+ " FROM PP_Order_BOMLine obl"
				+ " INNER JOIN M_Product p ON (obl.M_Product_ID = p.M_Product_ID) "
				+ " INNER JOIN C_UOM u ON (p.C_UOM_ID = u.C_UOM_ID) "
				+ " INNER JOIN M_Warehouse w ON (w.M_Warehouse_ID = obl.M_Warehouse_ID) "
				+ " WHERE obl.PP_Order_ID = ?" + " ORDER BY obl."
				+ MPPOrderBOMLine.COLUMNNAME_Line;
		// reset table
		int row = 0;
		issue.setRowCount(row);
		// Execute
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, getPP_Order_ID());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// extend table
				issue.setRowCount(row + 1);
				// set values
				// issue.
				IDColumn id = new IDColumn(rs.getInt(1));
				BigDecimal qtyBom = rs.getBigDecimal(15);
				Boolean isQtyPercentage = rs.getString(16).equals("Y");
				Boolean isCritical = rs.getString(2).equals("Y");
				BigDecimal qtyBatch = rs.getBigDecimal(17);
				BigDecimal qtyRequired = rs.getBigDecimal(8);
				BigDecimal qtyOnHand = rs.getBigDecimal(11);
				BigDecimal qtyOpen = rs.getBigDecimal(19);
				BigDecimal qtyDelivered = rs.getBigDecimal(20);
				String componentType = rs.getString(18);
				BigDecimal toDeliverQty = getToDeliverQty();
				BigDecimal openQty = getOpenQty();
				BigDecimal scrapQty = getScrapQty();
				BigDecimal componentToDeliverQty = Env.ZERO;
				BigDecimal componentScrapQty = Env.ZERO;
				BigDecimal componentQtyReq = Env.ZERO;
				BigDecimal componentQtyToDel = Env.ZERO;

				id.setSelected(isOnlyReceipt());

				issue.setValueAt(id, row, 0); // PP_OrderBOMLine_ID
				issue.setValueAt(isCritical, row, 1); // IsCritical
				issue.setValueAt(rs.getString(3), row, 2); // Product's Search
															// key
				issue.setValueAt(new KeyNamePair(rs.getInt(4), rs.getString(5)), row, 3); // Product
				issue.setValueAt(new KeyNamePair(rs.getInt(6), rs.getString(7)), row, 4); // UOM
				// ... 5 - ASI
				issue.setValueAt(qtyRequired, row, 6); // QtyRequired
				issue.setValueAt(qtyDelivered, row, 7); // QtyDelivered

				// ... 8, 9, 10 - QtyToDeliver, QtyScrap, QtyOnHand
				issue.setValueAt(qtyOnHand, row, 10); // OnHand
				issue.setValueAt(rs.getBigDecimal(9), row, 11); // QtyReserved
				issue.setValueAt(rs.getBigDecimal(10), row, 12); // QtyAvailable
				// ... 13 - M_Locator_ID
				issue.setValueAt(new KeyNamePair(rs.getInt(13), rs.getString(14)), row, 14); // Warehouse
				issue.setValueAt(qtyBom, row, 15); // QtyBom
				issue.setValueAt(isQtyPercentage, row, 16); // isQtyPercentage
				issue.setValueAt(qtyBatch, row, 17); // QtyBatch

				if (componentType.equals(MPPProductBOMLine.COMPONENTTYPE_Component)
				||  componentType.equals(MPPProductBOMLine.COMPONENTTYPE_Packing)) {
					// If the there is product on hand and product is required
					// the product should be selected
					id.setSelected(qtyOnHand.signum() > 0 && qtyRequired.signum() > 0);
					issue.setValueAt(id, row, 0); // PP_OrderBOMLine_ID

					if (isQtyPercentage) {
						// If the quantity of product is calculated as a
						// percentage
						BigDecimal qtyBatchPerc = qtyBatch.divide(Env.ONEHUNDRED, 8, RoundingMode.HALF_UP);

						if (isBackflush()) { // Is Backflush - Calculate
												// Component from Qty To Deliver
							if (qtyRequired.signum() == 0 || qtyOpen.signum() == 0) {
								componentToDeliverQty = Env.ZERO;
							} else {
								componentToDeliverQty = toDeliverQty.multiply(qtyBatchPerc);

								if (qtyRequired.subtract(qtyDelivered).signum() < 0 | componentToDeliverQty.signum() == 0)
									componentToDeliverQty = qtyRequired.subtract(qtyDelivered);

							}

							if (componentToDeliverQty.signum() != 0) {
								// TODO: arhipac: teo_sarca: is this a bug ?
								// ...instead of toDeliverQty, qtyRequired
								// should be used!
								// componentQtyReq =
								// toDeliverQty.multiply(qtyBatchPerc); // TODO:
								// set scale 4
								componentQtyToDel = componentToDeliverQty.setScale(4, BigDecimal.ROUND_HALF_UP);
								// issue.setValueAt(toDeliverQty.multiply(qtyBatchPerc),
								// row, 6); // QtyRequired
								issue.setValueAt(componentToDeliverQty, row, 8); // QtyToDelivery

							}
						} else { // Only Issue - Calculate Component from Open
									// Qty
							componentToDeliverQty = qtyOpen;
							if (componentToDeliverQty.signum() != 0) {
								componentQtyReq = openQty.multiply(qtyBatchPerc); // scale 4
								componentQtyToDel = componentToDeliverQty.setScale(4, BigDecimal.ROUND_HALF_UP);
								issue.setValueAt(componentToDeliverQty.setScale(8, BigDecimal.ROUND_HALF_UP), row, 8); // QtyToDelivery
								issue.setValueAt(openQty.multiply(qtyBatchPerc), row, 6); // QtyRequired
							}
						}

						if (scrapQty.signum() != 0) {
							componentScrapQty = scrapQty.multiply(qtyBatchPerc);
							if (componentScrapQty.signum() != 0) {
								issue.setValueAt(componentScrapQty, row, 9); // QtyScrap
							}
						}
						else
							issue.setValueAt(componentScrapQty, row, 9); // QtyScrap
					} else { // Absolute Qtys (not Percentage)
						if (isBackflush()) { // Is Backflush - Calculate
							// Component from Qty To Deliver
							if (componentType.equals(MPPProductBOMLine.COMPONENTTYPE_Packing))
								componentToDeliverQty = qtyRequired.subtract(qtyDelivered);
							else
								componentToDeliverQty = toDeliverQty.multiply(qtyBom); // TODO: set Number scale

							if (componentToDeliverQty.signum() != 0) {
								if (componentType.equals(MPPProductBOMLine.COMPONENTTYPE_Packing))
									componentQtyReq = qtyRequired.subtract(qtyDelivered);
								else
									componentQtyReq = toDeliverQty.multiply(qtyBom);
								componentQtyToDel = componentToDeliverQty;
								issue.setValueAt(componentQtyReq, row, 6); // QtyRequired
								issue.setValueAt(componentToDeliverQty, row, 8); // QtyToDelivery
							}
						} else { // Only Issue - Calculate Component from Open
									// Qty
							componentToDeliverQty = qtyOpen;
							if (componentToDeliverQty.signum() != 0) {
								if (componentType.equals(MPPProductBOMLine.COMPONENTTYPE_Packing))
									componentQtyReq = qtyOpen;
								else
									componentQtyReq = openQty.multiply(qtyBom);

								componentQtyToDel = componentToDeliverQty;
								issue.setValueAt(componentQtyReq, row, 6); // QtyRequired
								issue.setValueAt(componentToDeliverQty, row, 8); // QtyToDelivery
							}
						}

						if (scrapQty.signum() != 0) {
							componentScrapQty = scrapQty.multiply(qtyBom); // TODO:
																			// set
																			// Number
																			// scale
							if (componentScrapQty.signum() != 0) {
								issue.setValueAt(componentScrapQty, row, 9); // ScrapQty
							}
						}
						else 
							issue.setValueAt(componentScrapQty, row, 9); // ScrapQty

					}
				} else if (componentType
						.equals(MPPProductBOMLine.COMPONENTTYPE_Tools)) {
					componentToDeliverQty = qtyBom; // TODO; set Number scale
					if (componentToDeliverQty.signum() != 0) {
						componentQtyReq = qtyBom;
						componentQtyToDel = componentToDeliverQty;
						issue.setValueAt(qtyBom, row, 6); // QtyRequired
						issue.setValueAt(componentToDeliverQty, row, 8); // QtyToDelivery
					}
				} else {
					issue.setValueAt(Env.ZERO, row, 6); // QtyRequired
					//issue.setValueAt(Env.ZERO, row, 8); // QtyToDelivery
				}

				row++;

				if (isOnlyIssue() || isBackflush()) {
					int warehouse_id = rs.getInt(13);
					int product_id = rs.getInt(4);
					row += lotes(row, id, warehouse_id, product_id,
							componentQtyReq, componentQtyToDel, issue);
				}
			} // while
		} catch (SQLException e) {
			throw new DBException(e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		issue.autoSize();
	} // executeQuery

	public String generateSummaryTable(IMiniTable issue, String productField,
			String uomField, String attribute, String toDeliverQty,
			String deliveredQtyField, String scrapQtyField,
			boolean isBackflush, boolean isOnlyIssue, boolean isOnlyReceipt) {

		StringBuffer iText = new StringBuffer();

		iText.append("<b>");
		iText.append(Msg.parseTranslation(Env.getCtx(), "@IsShipConfirm@"));
		iText.append("</b>");
		iText.append("<br />");

		if (isOnlyReceipt || isBackflush) {

			String[][] table = {
					{
							Msg.translate(Env.getCtx(), "Name"),
							Msg.translate(Env.getCtx(), "C_UOM_ID"),
							Msg.translate(Env.getCtx(),
									"M_AttributeSetInstance_ID"),
							Msg.translate(Env.getCtx(), "QtyToDeliver"),
							Msg.translate(Env.getCtx(), "QtyDelivered"),
							Msg.translate(Env.getCtx(), "QtyScrap") },
					{ productField, uomField, attribute, toDeliverQty,
							deliveredQtyField, scrapQtyField } };
			iText.append(createHTMLTable(table));
		}

		if (isBackflush || isOnlyIssue) {
			iText.append("<br /><br />");

			ArrayList<String[]> table = new ArrayList<String[]>();

			table.add(new String[] { Msg.translate(Env.getCtx(), "Value"), // 0
					Msg.translate(Env.getCtx(), "Name"), // 1
					Msg.translate(Env.getCtx(), "C_UOM_ID"), // 2
					Msg.translate(Env.getCtx(), "M_AttributeSetInstance_ID"), // 3
					Msg.translate(Env.getCtx(), "QtyToDeliver"), // 4
					Msg.translate(Env.getCtx(), "QtyDelivered"), // 5
					Msg.translate(Env.getCtx(), "QtyScrap") // 6
			});

			// check available on hand
			for (int i = 0; i < issue.getRowCount(); i++) {
				IDColumn id = (IDColumn) issue.getValueAt(i, 0);
				if (id != null && id.isSelected()) {
					KeyNamePair m_productkey = (KeyNamePair) issue.getValueAt(
							i, 3);
					int m_M_Product_ID = m_productkey.getKey();
					KeyNamePair m_uomkey = (KeyNamePair) issue.getValueAt(i, 4);

					if (issue.getValueAt(i, 5) == null) // M_AttributeSetInstance_ID
														// is null
					{
						Timestamp m_movementDate = getMovementDate();
						Timestamp minGuaranteeDate = m_movementDate;
						MStorage[] storages = MPPOrder.getStorages(
								Env.getCtx(), m_M_Product_ID, getPP_Order()
										.getM_Warehouse_ID(), 0,
								minGuaranteeDate, null);


						BigDecimal qtyDelivered = getValueBigDecimal(issue, i, 7); // Qty Delivered
						BigDecimal toDelivery = getValueBigDecimal(issue, i, 8); // QtyOpen
						BigDecimal scrap = getValueBigDecimal(issue, i, 9); // QtyScrap
						BigDecimal toIssue = toDelivery.add(scrap);

						//allow return quantity order line
						if (storages == null || storages.length == 0)
						{
							if (toIssue.signum() < 0 && qtyDelivered.signum() > 0 && qtyDelivered.add(toIssue).signum() >= 0) {
								String[] row = {"", "", "", "", "0.00", "0.00",
										"0.00"};
								row[0] = issue.getValueAt(i, 2) != null ? issue
										.getValueAt(i, 2).toString() : "";
								row[1] = m_productkey.toString();
								row[2] = m_uomkey != null ? m_uomkey.toString()
										: "";
								String desc = null;
								row[3] = desc != null ? desc : "";
								row[4] = toIssue.setScale(2,
										BigDecimal.ROUND_HALF_UP).toString();
								row[5] = getValueBigDecimal(issue, i, 7).setScale(
										2, BigDecimal.ROUND_HALF_UP).toString();
								row[6] = getValueBigDecimal(issue, i, 9).toString();
								table.add(row);
							}
							continue;
						}

						for (MStorage storage : storages) {
							// TODO Selection of ASI

							if (storage.getQtyOnHand().signum() == 0)
								continue;

							BigDecimal issueact = toIssue;
							if (issueact.compareTo(storage.getQtyOnHand()) > 0)
								issueact = storage.getQtyOnHand();
							toIssue = toIssue.subtract(issueact);

							String desc = new MAttributeSetInstance(
									Env.getCtx(),
									storage.getM_AttributeSetInstance_ID(),
									null).getDescription();

							String[] row = { "", "", "", "", "0.00", "0.00",
									"0.00" };
							row[0] = issue.getValueAt(i, 2) != null ? issue
									.getValueAt(i, 2).toString() : "";
							row[1] = m_productkey.toString();
							row[2] = m_uomkey != null ? m_uomkey.toString()
									: "";
							row[3] = desc != null ? desc : "";
							row[4] = issueact.setScale(2,
									BigDecimal.ROUND_HALF_UP).toString();
							row[5] = getValueBigDecimal(issue, i, 7).setScale(
									2, BigDecimal.ROUND_HALF_UP).toString();
							row[6] = getValueBigDecimal(issue, i, 9).toString();
							table.add(row);

							if (toIssue.signum() <= 0)
								break;
						}
					} else // if M_AttributeSetInstance_ID isn't null
					{
						String[] row = { "", "", "", "", "0.00", "0.00", "0.00" };
						row[0] = issue.getValueAt(i, 2) != null ? issue
								.getValueAt(i, 2).toString() : "";
						row[1] = m_productkey.toString();
						row[2] = m_uomkey != null ? m_uomkey.toString() : "";
						row[3] = issue.getValueAt(i, 5) != null ? issue
								.getValueAt(i, 5).toString() : "";
						row[4] = getValueBigDecimal(issue, i, 8).toString();
						row[5] = getValueBigDecimal(issue, i, 7).toString();
						row[6] = getValueBigDecimal(issue, i, 9).toString();
						table.add(row);
					}

				}

			}

			String[][] tableArray = table.toArray(new String[table.size()][]);
			iText.append(createHTMLTable(tableArray));
		}

		return iText.toString();
	}

	protected BigDecimal getDeliveredQty() {
		return m_DeliveredQty;
	}

	protected int getM_AttributeSetInstance_ID() {
		return m_M_AttributeSetInstance_ID;
	}

	protected int getM_Locator_ID() {
		return m_M_Locator_ID;
	}

	protected Timestamp getMovementDate() {
		return m_movementDate;
	}

	protected BigDecimal getOpenQty() {
		return m_openQty;
	}

	protected BigDecimal getOrderedQty() {
		return m_orderedQty;
	}

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

	protected int getPP_Order_ID() {
		return m_PP_Order_ID;
	}

	protected BigDecimal getQtyBatchs() {
		return m_qtyBatchs;
	}

	protected BigDecimal getQtyBatchSize() {
		return m_qtyBatchSize;
	}

	protected BigDecimal getRejectQty() {
		return m_rejectQty;
	}

	protected BigDecimal getScrapQty() {
		return m_scrapQty;
	}

	protected BigDecimal getToDeliverQty() {
		return m_toDeliverQty;
	}

	private BigDecimal getValueBigDecimal(IMiniTable issue, int row, int col) {
		BigDecimal bd = (BigDecimal) issue.getValueAt(row, col);
		return bd == null ? Env.ZERO : bd;
	}

	protected boolean isBackflush() {
		return m_IsBackflush;
	}

	/**
	 * Determines whether the Delivery Rule is set to 'OnlyIssue'
	 * 
	 * @return
	 */
	protected boolean isOnlyIssue() {
		return m_OnlyIssue;
	}

	/**
	 * Determines whether the Delivery Rule is set to 'OnlyReciept'
	 * 
	 * @return
	 */
	protected boolean isOnlyReceipt() {
		return m_isOnlyReceipt;
	}

	/**
	 * Adds Attribute Set Instances Quantities to table. Extension to
	 * {@link #executeQuery()}
	 * 
	 * @return how many lines were added
	 */
	private int lotes(int row, IDColumn id, int Warehouse_ID, int M_Product_ID,
			BigDecimal qtyRequired, BigDecimal qtyToDelivery, IMiniTable issue) {
		int linesNo = 0;
		BigDecimal qtyRequiredActual = qtyRequired;

		final String sql = "SELECT "
				+ "s.M_Product_ID , s.QtyOnHand, s.M_AttributeSetInstance_ID"
				+ ", p.Name, masi.Description, l.M_Locator_ID , l.Value, w.Value, w.M_warehouse_ID,p.Value"
				+ "  FROM M_Storage s "
				+ " INNER JOIN M_Product p ON (s.M_Product_ID = p.M_Product_ID) "
				+ " INNER JOIN C_UOM u ON (u.C_UOM_ID = p.C_UOM_ID) "
				+ " INNER JOIN M_AttributeSetInstance masi ON (masi.M_AttributeSetInstance_ID = s.M_AttributeSetInstance_ID) "
				+ " INNER JOIN M_Warehouse w ON (w.M_Warehouse_ID = ?) "
				+ " INNER JOIN M_Locator l ON(l.M_Warehouse_ID=w.M_Warehouse_ID and s.M_Locator_ID=l.M_Locator_ID) "
				+ " WHERE s.M_Product_ID = ? and s.QtyOnHand > 0 "
				+ " and s.M_AttributeSetInstance_ID <> 0 "
				+ " ORDER BY s.Created ";
		// Execute
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, Warehouse_ID);
			pstmt.setInt(2, M_Product_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				issue.setRowCount(row + 1);

				// Qty On Hand
				final BigDecimal qtyOnHand = rs.getBigDecimal(2);

				// ID/M_AttributeSetInstance_ID
				IDColumn id1 = new IDColumn(rs.getInt(3));
				id1.setSelected(false);
				// issue.setRowSelectionAllowed(true);
				issue.setValueAt(id1, row, 0);
				// Product
				KeyNamePair productkey = new KeyNamePair(rs.getInt(1), rs.getString(4));
				issue.setValueAt(productkey, row, 3);
				// QtyOnHand
				issue.setValueAt(qtyOnHand, row, 10);
				// ASI
				issue.setValueAt(rs.getString(5), row, 5);
				// Locator
				KeyNamePair locatorKey =  new KeyNamePair(rs.getInt(6), rs.getString(7));
				issue.setValueAt(locatorKey, row, 13);
				// Warehouse
				KeyNamePair m_warehousekey = new KeyNamePair(rs.getInt(9), rs.getString(8));
				issue.setValueAt(m_warehousekey, row, 14);
				issue.setValueAt(Env.ZERO, row, 6); // QtyRequired
				issue.setValueAt(Env.ZERO, row, 8); // QtyToDelivery
				issue.setValueAt(Env.ZERO, row, 9); // Srcap
				// Qty Required:
				if (qtyRequiredActual.compareTo(qtyOnHand) < 0) {
					issue.setValueAt(
							qtyRequiredActual.signum() > 0 ? qtyRequiredActual
									: Env.ZERO, row, 6);
				} else {
					issue.setValueAt(qtyOnHand, row, 6);
				}
				qtyRequiredActual = qtyRequiredActual.subtract(qtyOnHand);

				linesNo++;
				row++;
			}
		} catch (SQLException e) {
			throw new DBException(e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		return linesNo;
	}

	/**
	 * Save Selection & return selecion Query or ""
	 * 
	 * @return where clause like C_Order_ID IN (...)
	 */
	public void saveSelection(IMiniTable miniTable) {
		log.info("");
		// Array of Integers
		ArrayList<Integer> results = new ArrayList<Integer>();
		setSelection(null);

		// Get selected entries
		int rows = miniTable.getRowCount();
		for (int i = 0; i < rows; i++) {
			IDColumn id = (IDColumn) miniTable.getValueAt(i, 0); // ID in column
																	// 0
			// log.fine( "Row=" + i + " - " + id);
			if (id != null && id.isSelected())
				results.add(id.getRecord_ID());
		}

		if (results.size() == 0)
			return;
		log.config("Selected #" + results.size());
		setSelection(results);
	} // saveSelection

	protected void setDeliveredQty(BigDecimal qty) {
		m_DeliveredQty = qty;
	}

	protected void setIsBackflush(boolean IsBackflush) {
		m_IsBackflush = IsBackflush;
	}

	protected void setIsOnlyIssue(boolean onlyIssue) {
		m_OnlyIssue = onlyIssue;
	}

	protected void setIsOnlyReceipt(boolean isOnlyReceipt) {
		m_isOnlyReceipt = isOnlyReceipt;
	}

	protected void setM_AttributeSetInstance_ID(int M_AttributeSetInstance_ID) {
		m_M_AttributeSetInstance_ID = M_AttributeSetInstance_ID;
	}

	protected void setM_Locator_ID(int M_Locator_ID) {
		m_M_Locator_ID = M_Locator_ID;
	}

	protected void setMovementDate(Timestamp date) {
		m_movementDate = date;
	}

	protected void setOpenQty(BigDecimal qty) {
		m_openQty = qty;
	}

	protected void setOrderedQty(BigDecimal qty) {
		m_orderedQty = qty;
	}

	protected void setPP_Order_ID(int PP_Order_ID) {
		m_PP_Order_ID = PP_Order_ID;
	}

	protected void setQtyBatchs(BigDecimal qty) {
		m_qtyBatchs = qty;
	}

	protected void setQtyBatchSize(BigDecimal qty) {
		m_qtyBatchSize = qty;
	}

	protected void setRejectQty(BigDecimal qty) {
		m_rejectQty = qty;
	}

	protected void setScrapQty(BigDecimal qty) {
		m_scrapQty = qty;
	}

	protected void setToDeliverQty(BigDecimal qty) {
		m_toDeliverQty = qty;
	}

	public void showMessage(String message, boolean error) {
	}

}
