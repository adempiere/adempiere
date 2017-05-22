/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2016 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
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

package org.compiere.process;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MLocator;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MProduct;
import org.compiere.model.MRMA;
import org.compiere.model.MRMALine;
import org.compiere.model.MWarehouse;

/** Generated Process for (In Out Create From)
 *  @author ADempiere (generated) 
 *  @version Release 3.8.0
 * 	@author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *  <li> FR [ 327 ] Create From in M_InOut change to Smart Browse
 *  @see https://github.com/adempiere/adempiere/issues/327
 *	@author Victor Perez , victor.perez@e-evolution.com, http://e-evolution.com
 */
public class InOutCreateFrom extends InOutCreateFromAbstract {

	/**	Create From Type of RMA		*/
	private static final String RMA = "A";
	/**	Create From Type of Order	*/
	private static final String ORDER = "O";
	/**	Create From Type of Invoice	*/
	private static final String INVOICE = "I";

	@Override
	protected void prepare() {
		super.prepare();
	}
	
	/**
	 * Get a valid locator
	 * @param locatorId
	 * @return
	 */
	private int getValidLocator(int locatorId, MLocator defaultLocator) {
		// If a locator is specified on the product, choose that otherwise default locator
		if(getLocator() != 0)
			locatorId = getLocator();
		//	Validate Locator
		if(locatorId != 0) {
			MLocator locator = MLocator.get(getCtx(), locatorId);
			//	Set from default if it is distinct
			if(locator == null) {
				locatorId = 0;
			} else if(defaultLocator != null 
					&& locator.getM_Warehouse_ID() != defaultLocator.getM_Warehouse_ID()) {
				locatorId = 0;
			}
		}
		//	Valid locator
		if(locatorId == 0) {
			if(defaultLocator != null)
				locatorId = defaultLocator.getM_Locator_ID();
			else
				throw new AdempiereException("@M_Locator_ID@ @NotFound@");
		}
		//	Return
		return locatorId;
	}
	
	@Override
	protected String doIt() throws Exception {
		// Valid Record Identifier
		if(getRecord_ID() == 0)
			return "";
		AtomicInteger referenceId = new AtomicInteger(0);
		AtomicInteger 	created = new AtomicInteger(0);
		//	Get Shipment
		MInOut inout = new MInOut(getCtx(), getRecord_ID(), get_TrxName());
		log.config(inout + ", C_Locator_ID=" + getLocator());
		//	Get Default Locator
		MLocator defaultLocator = MLocator.getDefault((MWarehouse) inout.getM_Warehouse());
		List<Integer> recordIds =  getSelectionKeys();
		String createFromType = recordIds.size() > 0 ?  getSelectionAsString(recordIds.get(0), "CF_CreateFromType") : null;
		log.fine("CreateFromType=" + createFromType);
		if (createFromType == null || createFromType.length() == 0)
				throw new AdempiereException("@CreateFromType@ @NotFound@");
		//	Loop
		recordIds.stream().forEach( key -> {
			// variable values
			int productId = getSelectionAsInt(key, "CF_M_Product_ID");
			int chargeId = getSelectionAsInt(key, "CF_C_Charge_ID");
			int uomId = getSelectionAsInt(key, "CF_C_UOM_ID");
			int locatorId = getSelectionAsInt(key, "CF_M_Locator_ID");
			BigDecimal qtyEntered = getSelectionAsBigDecimal(key, "CF_QtyEntered"); // Qty
			// If a locator is specified on the product, choose that otherwise default locator
			locatorId = getValidLocator(locatorId, defaultLocator);
			MInvoiceLine invoiceLine = null;
			//	Precision of Qty UOM
			int precision = 2;
			if (productId != 0) {
				MProduct product = MProduct.get(getCtx(), productId);
				precision = product.getUOMPrecision();
			}
			qtyEntered = qtyEntered.setScale(precision, BigDecimal.ROUND_HALF_DOWN);
			//
			log.fine("Line QtyEntered=" + qtyEntered
					+ ", Product=" + productId
					+ ", Key=" + key);

			//	Create new InOut Line
			MInOutLine inOutLine = new MInOutLine (inout);
			inOutLine.setM_Product_ID(productId, uomId);	//	Line UOM
			inOutLine.setQty(qtyEntered);							//	Movement/Entered
			//
			if(createFromType.equals(ORDER)) {
				MOrderLine orderLine = new MOrderLine (getCtx(), key, get_TrxName());
				//	Set reference
				referenceId.set(orderLine.getC_Order_ID());
				//	
				inOutLine.setC_OrderLine_ID(key);
				if (orderLine.getQtyEntered().compareTo(orderLine.getQtyOrdered()) != 0) {
					inOutLine.setMovementQty(qtyEntered
							.multiply(orderLine.getQtyOrdered())
							.divide(orderLine.getQtyEntered(), 12, BigDecimal.ROUND_HALF_UP));
					inOutLine.setC_UOM_ID(orderLine.getC_UOM_ID());
				}
				inOutLine.setM_AttributeSetInstance_ID(orderLine.getM_AttributeSetInstance_ID());
				inOutLine.setDescription(orderLine.getDescription());
				//
				inOutLine.setC_Project_ID(orderLine.getC_Project_ID());
				inOutLine.setC_ProjectPhase_ID(orderLine.getC_ProjectPhase_ID());
				inOutLine.setC_ProjectTask_ID(orderLine.getC_ProjectTask_ID());
				inOutLine.setC_Activity_ID(orderLine.getC_Activity_ID());
				inOutLine.setC_Campaign_ID(orderLine.getC_Campaign_ID());
				inOutLine.setAD_OrgTrx_ID(orderLine.getAD_OrgTrx_ID());
				inOutLine.setUser1_ID(orderLine.getUser1_ID());
				inOutLine.setUser2_ID(orderLine.getUser2_ID());
				inOutLine.setUser3_ID(orderLine.getUser3_ID());
				inOutLine.setUser4_ID(orderLine.getUser4_ID());
			} else if(createFromType.equals(INVOICE)) {
				invoiceLine = new MInvoiceLine (getCtx(), key, get_TrxName());
				MInvoice invoice = invoiceLine.getParent();
				//	Set reference
					referenceId.getAndSet(invoice.getC_Invoice_ID());
				//	Credit Memo - negative Qty
				if (invoice.isCreditMemo()) {
					qtyEntered = qtyEntered.negate();
					inOutLine.setQty(qtyEntered);
				}
				if (invoiceLine.getQtyEntered().compareTo(invoiceLine.getQtyInvoiced()) != 0) {
					inOutLine.setMovementQty(qtyEntered
							.multiply(invoiceLine.getQtyInvoiced())
							.divide(invoiceLine.getQtyEntered(), 12, BigDecimal.ROUND_HALF_UP));
					inOutLine.setC_UOM_ID(invoiceLine.getC_UOM_ID());
				}
				inOutLine.setDescription(invoiceLine.getDescription());
				inOutLine.setC_Project_ID(invoiceLine.getC_Project_ID());
				inOutLine.setC_ProjectPhase_ID(invoiceLine.getC_ProjectPhase_ID());
				inOutLine.setC_ProjectTask_ID(invoiceLine.getC_ProjectTask_ID());
				inOutLine.setC_Activity_ID(invoiceLine.getC_Activity_ID());
				inOutLine.setC_Campaign_ID(invoiceLine.getC_Campaign_ID());
				inOutLine.setAD_OrgTrx_ID(invoiceLine.getAD_OrgTrx_ID());
				inOutLine.setUser1_ID(invoiceLine.getUser1_ID());
				inOutLine.setUser2_ID(invoiceLine.getUser2_ID());
				inOutLine.setUser3_ID(invoiceLine.getUser3_ID());
				inOutLine.setUser4_ID(invoiceLine.getUser4_ID());
			} else if(createFromType.equals(RMA)) {
				MRMALine rmal = new MRMALine(getCtx(), key, get_TrxName());
				//	Set reference
				referenceId.set(rmal.getM_RMA_ID());
				inOutLine.setM_RMALine_ID(key);
				inOutLine.setQtyEntered(qtyEntered);
				inOutLine.setDescription(rmal.getDescription());
				inOutLine.setM_AttributeSetInstance_ID(rmal.getM_AttributeSetInstance_ID());
				inOutLine.setC_Project_ID(rmal.getC_Project_ID());
				inOutLine.setC_ProjectPhase_ID(rmal.getC_ProjectPhase_ID());
				inOutLine.setC_ProjectTask_ID(rmal.getC_ProjectTask_ID());
				inOutLine.setC_Activity_ID(rmal.getC_Activity_ID());
				inOutLine.setAD_OrgTrx_ID(rmal.getAD_OrgTrx_ID());
				inOutLine.setUser1_ID(rmal.getUser1_ID());
				inOutLine.setUser2_ID(rmal.getUser2_ID());
				inOutLine.setUser3_ID(rmal.getUser3_ID());
				inOutLine.setUser4_ID(rmal.getUser4_ID());
			}
			//	Set Charge
			if(chargeId != 0)
				inOutLine.setC_Charge_ID(chargeId);
			// Set locator
			inOutLine.setM_Locator_ID(locatorId);
			inOutLine.saveEx();
			//	Create Invoice Line Link
			if (invoiceLine != null) {
				invoiceLine.setM_InOutLine_ID(inOutLine.getM_InOutLine_ID());
				invoiceLine.saveEx();
			}
			//	Add to created
			created.updateAndGet(createNo -> createNo + 1);
		});
		//	Add reference to Order / Invoice / RMA
		addReference(inout, createFromType, referenceId.get());
		//	
		return "@Created@ " + created.get();
	}

	/**
	 * Add Reference to Order / Invoice / RMA
	 * @param inout
	 * @param createFromType
	 * @param referenceId
     */
	private void addReference(MInOut inout, String createFromType, int referenceId) {
		//	Valid Reference
		if(referenceId == 0)
			return;
		if(createFromType.equals(ORDER)) {
			MOrder order = new MOrder(getCtx(), referenceId, get_TrxName());
			inout.setC_Order_ID (order.getC_Order_ID());
			inout.setAD_OrgTrx_ID(order.getAD_OrgTrx_ID());
			inout.setC_Project_ID(order.getC_Project_ID());
			inout.setC_Campaign_ID(order.getC_Campaign_ID());
			inout.setC_Activity_ID(order.getC_Activity_ID());
			inout.setUser1_ID(order.getUser1_ID());
			inout.setUser2_ID(order.getUser2_ID());
			inout.setUser3_ID(order.getUser3_ID());
			inout.setUser4_ID(order.getUser4_ID());
			//	For Drop Ship
			if(order.isDropShip()) {
				inout.setM_Warehouse_ID( order.getM_Warehouse_ID() );
				inout.setIsDropShip(order.isDropShip());
				inout.setDropShip_BPartner_ID(order.getDropShip_BPartner_ID());
				inout.setDropShip_Location_ID(order.getDropShip_Location_ID());
				inout.setDropShip_User_ID(order.getDropShip_User_ID());
			}
		} else if(createFromType.equals(INVOICE)) {
			MInvoice invoice = new MInvoice(getCtx(), referenceId, get_TrxName());
			if(inout.getC_Order_ID() == 0)
				inout.setC_Order_ID (invoice.getC_Order_ID());
			inout.setC_Invoice_ID (invoice.getC_Invoice_ID());
			inout.setAD_OrgTrx_ID(invoice.getAD_OrgTrx_ID());
			inout.setC_Project_ID(invoice.getC_Project_ID());
			inout.setC_Campaign_ID(invoice.getC_Campaign_ID());
			inout.setC_Activity_ID(invoice.getC_Activity_ID());
			inout.setUser1_ID(invoice.getUser1_ID());
			inout.setUser2_ID(invoice.getUser2_ID());
			inout.setUser3_ID(invoice.getUser3_ID());
			inout.setUser4_ID(invoice.getUser4_ID());
		} else if(createFromType.equals(RMA)) {
			MRMA rma = new MRMA(getCtx(), referenceId, get_TrxName());
			MInOut originalIO = rma.getShipment();
			inout.setIsSOTrx(rma.isSOTrx());
			inout.setC_Order_ID(0);
			inout.setC_Invoice_ID(0);
			inout.setM_RMA_ID(rma.getM_RMA_ID());
			inout.setAD_OrgTrx_ID(originalIO.getAD_OrgTrx_ID());
			inout.setC_Project_ID(originalIO.getC_Project_ID());
			inout.setC_Campaign_ID(originalIO.getC_Campaign_ID());
			inout.setC_Activity_ID(originalIO.getC_Activity_ID());
			inout.setUser1_ID(originalIO.getUser1_ID());
			inout.setUser2_ID(originalIO.getUser2_ID());
			inout.setUser3_ID(originalIO.getUser3_ID());
			inout.setUser4_ID(originalIO.getUser4_ID());
		}
		//	Save
		inout.saveEx();
	}
}