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
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import org.compiere.model.MInOutLine;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MProduct;
import org.compiere.model.MUOMConversion;
import org.compiere.util.Env;

/** 
 * 	Generated Process for (Order (RMA) Create From)
 *  @author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 *  @version Release 3.9.2
 */
public class OrderRMACreateFrom extends OrderRMACreateFromAbstract {
	
	@Override
	protected String doIt() throws Exception {
		// Valid Record Identifier
		if(getRecord_ID() == 0)
			return "@C_Order_ID@ @NotFound@";
		//	Get Shipment
		MOrder order = new MOrder(getCtx(), getRecord_ID(), get_TrxName());
		if(order.isProcessed()) {
			return "@C_Order_ID@ @Processed@";
		}
		AtomicInteger created = new AtomicInteger(0);
		AtomicBoolean isHeaderUpdated = new AtomicBoolean(false);
		List<Integer> recordIds =  getSelectionKeys();
		//	Loop
		recordIds.stream().forEach( key -> {
			// variable values
			int productId = getSelectionAsInt(key, "CF_M_Product_ID");
			int chargeId = getSelectionAsInt(key, "CF_C_Charge_ID");
			int uomId = getSelectionAsInt(key, "CF_C_UOM_ID");
			BigDecimal qtyEntered = getSelectionAsBigDecimal(key, "CF_QtyEntered"); // Qty
			// If a locator is specified on the product, choose that otherwise default locator
			log.fine("Line QtyEntered=" + qtyEntered
					+ ", Product=" + productId
					+ ", Key=" + key);

			//	Create new Invoice Line
			MOrderLine orderLine = new MOrderLine(order);
			BigDecimal qtyOrdered = null;
			//	Precision of Qty UOM
			int precision = 2;
			if (productId > 0) {
				MProduct product = MProduct.get(Env.getCtx(), productId);
				if (product != null) {
					orderLine.setM_Product_ID(product.getM_Product_ID(), uomId);
					precision = product.getUOMPrecision();
					if (product.getC_UOM_ID() != uomId) {
						qtyEntered = qtyEntered.setScale(precision, RoundingMode.HALF_DOWN);
						qtyOrdered = MUOMConversion.convertProductFrom(Env.getCtx(), productId, uomId, qtyEntered);
					}
				}
			} else if(chargeId != 0) {
				orderLine.setC_Charge_ID(chargeId);
			}
			//	
			qtyEntered = qtyEntered.setScale(precision, RoundingMode.HALF_DOWN);
			if (qtyOrdered == null) {
				qtyOrdered = qtyEntered;
			}
			MInOutLine inOutLine = new MInOutLine(getCtx(), key, get_TrxName());
			//	Get source Order
			MOrderLine sourceOrderLine = (MOrderLine) inOutLine.getC_OrderLine();
			//	Change header
			if(isHeaderUpdated.compareAndSet(false, true)) {
				MOrder sourceOrder = sourceOrderLine.getParent();
				if(sourceOrder.getAD_OrgTrx_ID() != 0) {
					order.setAD_OrgTrx_ID(sourceOrder.getAD_OrgTrx_ID());
				}
				if(sourceOrder.getC_Project_ID() != 0) {
					order.setC_Project_ID(sourceOrder.getC_Project_ID());
				}
				if(sourceOrder.getC_Campaign_ID() != 0) {
					order.setC_Campaign_ID(sourceOrder.getC_Campaign_ID());
				}
				if(sourceOrder.getC_Activity_ID() != 0) {
					order.setC_Activity_ID(sourceOrder.getC_Activity_ID());
				}
				if(sourceOrder.getUser1_ID() != 0) {
					order.setUser1_ID(sourceOrder.getUser1_ID());
				}
				if(sourceOrder.getUser2_ID() != 0) {
					order.setUser2_ID(sourceOrder.getUser2_ID());
				}
				if(sourceOrder.getUser3_ID() != 0) {
					order.setUser3_ID(sourceOrder.getUser3_ID());
				}
				if(sourceOrder.getUser4_ID() != 0) {
					order.setUser4_ID(sourceOrder.getUser4_ID());
				}
				order.setM_PriceList_ID(sourceOrder.getM_PriceList_ID());
				order.saveEx();
			}
			//	Set Reference
			orderLine.setRef_InOutLine(inOutLine);
			//	Set quantity
			orderLine.setQty(qtyEntered);							//	Movement/Entered
			orderLine.setQtyOrdered(qtyOrdered);
			orderLine.setC_UOM_ID(uomId);
			//	Save
			orderLine.saveEx();
			//	Add to created
			created.updateAndGet(createNo -> createNo + 1);
		});
		return "@Created@ " + created.get();
	}
}