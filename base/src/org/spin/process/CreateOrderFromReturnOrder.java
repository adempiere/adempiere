/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.										*
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/

package org.spin.process;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Hashtable;
import java.util.List;

import org.compiere.model.MBPartner;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MProduct;
import org.compiere.model.MUOMConversion;
import org.compiere.model.PO;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Util;

/** 
 * 	Generated Process for (Create Order From Return Order)
 *  @author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 *  @version Release 3.9.2
 */
public class CreateOrderFromReturnOrder extends CreateOrderFromReturnOrderAbstract {
	private Hashtable<Integer, MOrder> orders;
	private int created = 0;
	private StringBuffer generatedDocuments = new StringBuffer();
	
	@Override
	protected String doIt() throws Exception {
		if(Util.isEmpty(getDocAction())) {
			setDocAction(MOrder.ACTION_Complete);
		}
		orders  = new Hashtable<Integer, MOrder>();
		List<Integer> recordIds =  getSelectionKeys();
		//	Loop
		recordIds.stream().forEach(key -> {
			// variable values
			MOrderLine returnOrderLine = new MOrderLine(getCtx(), key, get_TrxName());
			int uomId = getSelectionAsInt(key, "CO_C_UOM_ID");
			BigDecimal qtyEntered = getSelectionAsBigDecimal(key, "CO_QtyEntered"); // Qty
			// If a locator is specified on the product, choose that otherwise default locator
			log.fine("Line QtyEntered=" + qtyEntered
					+ ", Product=" + returnOrderLine.getM_Product_ID()
					+ ", Key=" + key);
			createOrder(returnOrderLine, qtyEntered, uomId);
		});
		//	
		processingOrders();
		//	
		return "@Created@ " + created + (generatedDocuments.length() > 0? " [" + generatedDocuments + "]": "");
	}
	
	/**
	 * Create Order from Return Order
	 * @param returnOrderLine
	 * @param qtyEntered
	 * @param uomId
	 */
	private void createOrder(MOrderLine returnOrderLine, BigDecimal qtyEntered, int uomId) {
		MOrder order = getOrder(returnOrderLine);
		MOrderLine orderLine = new MOrderLine(order);
		orderLine.setC_UOM_ID(uomId);
		//	
		BigDecimal qtyOrdered = null;
		//	Precision of Qty UOM
		int precision = 2;
		if (returnOrderLine.getM_Product_ID() > 0) {
			MProduct product = MProduct.get(Env.getCtx(), returnOrderLine.getM_Product_ID());
			if (product != null) {
				orderLine.setM_Product_ID(product.getM_Product_ID(), uomId);
				precision = product.getUOMPrecision();
				if (product.getC_UOM_ID() != uomId) {
					qtyEntered = qtyEntered.setScale(precision, RoundingMode.HALF_DOWN);
					qtyOrdered = MUOMConversion.convertProductFrom(Env.getCtx(), returnOrderLine.getM_Product_ID(), uomId, qtyEntered);
				}
			}
		} else if(returnOrderLine.getC_Charge_ID() != 0) {
			orderLine.setC_Charge_ID(returnOrderLine.getC_Charge_ID());
		}
		//	
		qtyEntered = qtyEntered.setScale(precision, RoundingMode.HALF_DOWN);
		if (qtyOrdered == null) {
			qtyOrdered = qtyEntered;
		}
		orderLine.setQtyEntered(qtyEntered);
		orderLine.setQtyOrdered(qtyOrdered);
		orderLine.setRef_OrderLine_ID(returnOrderLine.getC_OrderLine_ID());
		orderLine.saveEx();
	}
	
	/**
	 * Create Order header
	 * @param orderLine Sales Order Line
	 * @return MInOut return the Shipment header
	 */
	private MOrder getOrder(MOrderLine orderLine) {
		MOrder order = orders.get(orderLine.getC_Order_ID());
		if(order != null)
			return order;

		MOrder returnOrder = orderLine.getParent();
		order = new MOrder(getCtx(), 0, get_TrxName());
		PO.copyValues(returnOrder, order);
		order.setDateOrdered(getDateOrdered());
		order.setDatePromised(getDateOrdered());
		order.addDescription(Msg.parseTranslation(getCtx(), "@Created@ @From@ " + returnOrder.getDocumentNo()));
		order.setDocumentNo(null);
		if(getDocTypeTargetId() > 0) {
			order.setC_DocType_ID(getDocTypeTargetId());
		} else {
			order.setC_DocTypeTarget_ID();
		}
		order.setBPartner((MBPartner) returnOrder.getC_BPartner());
		order.setRef_Order_ID(returnOrder.getC_Order_ID());
		order.setDocAction(getDocAction());
		order.setDocStatus(org.compiere.process.DocAction.STATUS_Drafted);
		order.setProcessed(false);
		order.setIsSOTrx(returnOrder.isSOTrx());
		order.saveEx();
		orders.put(returnOrder.getC_Order_ID(), order);
		return order;
	}
	
	/**
	 * Add Document Info for message to return
	 * @param documentInfo
	 */
	private void addToMessage(String documentInfo) {
		if(generatedDocuments.length() > 0) {
			generatedDocuments.append(", ");
		}
		//	
		generatedDocuments.append(documentInfo);
	}
	
	/**
	 * Process Invoices
	 */
	private void processingOrders() {
		if(orders == null) {
			return;
		}
		orders.entrySet().stream().filter(entry -> entry != null).forEach(entry -> {
			MOrder order = entry.getValue();
			order.setDocAction(getDocAction());
			if (!order.processIt(getDocAction())) {
				addLog("@ProcessFailed@ : " + order.getDocumentInfo());
				log.warning("@ProcessFailed@ :" + order.getDocumentInfo());
			}
			order.saveEx();
			created++;
			addToMessage(order.getDocumentNo());
		});
	}
}