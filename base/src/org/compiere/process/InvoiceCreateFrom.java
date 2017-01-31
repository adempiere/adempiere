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
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MProduct;
import org.compiere.model.MRMA;
import org.compiere.model.MRMALine;
import org.compiere.model.MUOMConversion;
import org.compiere.model.PO;
import org.compiere.util.Env;

/** Generated Process for (Invoice Create From)
 *  @author ADempiere (generated)
 * 	@author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *	@author Victor Perez , victor.perez@e-evolution.com, http://e-evolution.com
 *  @version Release 3.8.0
 */
public class InvoiceCreateFrom extends InvoiceCreateFromAbstract {

	/**	Create From Type of RMA		*/
	private static final String RMA = "A";
	/**	Create From Type of Order	*/
	private static final String ORDER = "O";
	/**	Create From Type of Order	*/
	private static final String RECEIPT = "R";
	/**	Create From Type of Invoice	*/
	private static final String INVOICE = "I";
	@Override
	protected void prepare() {
		super.prepare();
	}

	@Override
	protected String doIt() throws Exception {
		// Valid Record Identifier
		if(getRecord_ID() == 0)
			return "";
		//	Get Shipment
		MInvoice invoice = new MInvoice(getCtx(), getRecord_ID(), get_TrxName());
		AtomicInteger referenceId = new AtomicInteger(0);
		AtomicInteger 	created = new AtomicInteger(0);
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
			BigDecimal qtyEntered = getSelectionAsBigDecimal(key, "CF_QtyEntered"); // Qty
			// If a locator is specified on the product, choose that otherwise default locator
			log.fine("Line QtyEntered=" + qtyEntered
					+ ", Product=" + productId
					+ ", CreateFromType=" + createFromType + ", Key=" + key);

			//	Create new Invoice Line
			MInvoiceLine invoiceLine = new MInvoiceLine(invoice);
			BigDecimal qtyInvoiced = null;
			//	Precision of Qty UOM
			int precision = 2;
			if (productId > 0) {
				MProduct product = MProduct.get(Env.getCtx(), productId);
				if (product != null) {
					invoiceLine.setM_Product_ID(product.getM_Product_ID(), uomId);
					precision = product.getUOMPrecision();
					if (product.getC_UOM_ID() != uomId) {
						qtyEntered = qtyEntered.setScale(precision, BigDecimal.ROUND_HALF_DOWN);
						qtyInvoiced = MUOMConversion.convertProductFrom(Env.getCtx(), productId, uomId, qtyEntered);
					}
				}
			} else if(chargeId != 0) {
				invoiceLine.setC_Charge_ID(chargeId);
			}

			qtyEntered = qtyEntered.setScale(precision, BigDecimal.ROUND_HALF_DOWN);
			if (qtyInvoiced == null)
				qtyInvoiced = qtyEntered;

			invoiceLine.setQty(qtyEntered);							//	Movement/Entered
			invoiceLine.setQtyInvoiced(qtyInvoiced);
			if(createFromType.equals(ORDER)) {
				MOrderLine orderLine = new MOrderLine (getCtx(), key, get_TrxName());
				//	Set reference
				referenceId.set(orderLine.getC_Order_ID());
				//	Set InOut
				String whereClause = "EXISTS (SELECT 1 "
						+ "FROM M_InOut io "
						+ "WHERE io.M_InOut_ID = M_InOutLine.M_InOut_ID "
						+ "AND io.DocStatus IN ('CO','CL'))";
				MInOutLine[] inOutLines = MInOutLine.getOfOrderLine(Env.getCtx(), key, whereClause, get_TrxName());
				log.fine ("Receipt Lines with OrderLine = #" + inOutLines.length);
				final BigDecimal qty = qtyEntered;
				MInOutLine inOutLine = Arrays.stream(inOutLines)
						.filter(ioLine -> ioLine != null && ioLine.getQtyEntered().compareTo(qty) == 0)
						.findFirst().orElse(inOutLines.length > 0 ? inOutLines[0] : null);
				//	Set From
				if(inOutLine != null)
					invoiceLine.setShipLine(inOutLine);
				else
					invoiceLine.setOrderLine(orderLine);
			} else if(createFromType.equals(INVOICE)) {
				MInvoiceLine fromLine = new MInvoiceLine(getCtx(), key, get_TrxName());
				//	Set reference
				referenceId.set(invoiceLine.getParent().getC_Invoice_ID());
				//	Copy Values
				PO.copyValues(fromLine, invoiceLine);
				invoiceLine.setC_Invoice_ID(invoiceLine.getParent().getC_Invoice_ID());
				invoiceLine.setAD_Org_ID(fromLine.getAD_Org_ID());
				//	Reset
				invoiceLine.setC_OrderLine_ID(0);
				invoiceLine.setRef_InvoiceLine_ID(0);
				invoiceLine.setM_InOutLine_ID(0);
				invoiceLine.setA_Asset_ID(0);
				invoiceLine.setM_AttributeSetInstance_ID(0);
				invoiceLine.setS_ResourceAssignment_ID(0);
				//	New Tax
				if (invoiceLine.getParent().getC_BPartner_ID() != fromLine.getC_Invoice().getC_BPartner_ID())
					invoiceLine.setTax();	//	recalculate
				//
				invoiceLine.setProcessed(false);
			} else if(createFromType.equals(RMA)) {
				MRMALine rmaLine = new MRMALine(getCtx(), key, get_TrxName());
				//	Set reference
				referenceId.set(rmaLine.getM_RMA_ID());
				//
				invoiceLine.setRMALine(rmaLine);
			} else if(createFromType.equals(RECEIPT)) {
				MInOutLine inOutLine = new MInOutLine(getCtx(), key, get_TrxName());
				//	Set reference
				referenceId.set(inOutLine.getM_InOut_ID());
				invoiceLine.setShipLine(inOutLine);
			}
			//	Save
			invoiceLine.saveEx();
			if(createFromType.equals(INVOICE)) {
				MInvoiceLine fromLine = new MInvoiceLine(getCtx(), key, get_TrxName());
				// MZ Goodwill
				// copy the landed cost
				invoiceLine.copyLandedCostFrom(fromLine);
				invoiceLine.allocateLandedCosts();
				// end MZ
			}
			//	Add to created
			created.updateAndGet(createNo -> createNo + 1);
		});
		//	Add reference to Order / Invoice / RMA
		addReference(invoice, createFromType , referenceId.get());
		return "@Created@ " + created.get();
	}

	/**
	 * Add Reference to Order / Invoice / RMA
	 * @param invoice
	 * @param createFromType
	 * @param referenceId
     */
	private void addReference(MInvoice invoice, String createFromType , int referenceId ) {
		//	Valid Reference
		if(referenceId == 0)
			return;
		if(createFromType.equals(ORDER)) {
			MOrder order = new MOrder(getCtx(), referenceId, get_TrxName());
			invoice.setOrder(order);
		} else if(createFromType.equals(INVOICE)) {
			MInvoice fromInvoice = new MInvoice(getCtx(), referenceId, get_TrxName());
			invoice.setAD_OrgTrx_ID(fromInvoice.getAD_OrgTrx_ID());
			invoice.setC_Project_ID(fromInvoice.getC_Project_ID());
			invoice.setC_Campaign_ID(fromInvoice.getC_Campaign_ID());
			invoice.setC_Activity_ID(fromInvoice.getC_Activity_ID());
			invoice.setUser1_ID(fromInvoice.getUser1_ID());
			invoice.setUser2_ID(fromInvoice.getUser2_ID());
			invoice.setUser3_ID(fromInvoice.getUser3_ID());
			invoice.setUser4_ID(fromInvoice.getUser4_ID());
		} else if(createFromType.equals(RMA)) {
			MRMA rma = new MRMA(getCtx(), referenceId, get_TrxName());
			invoice.setRMA(rma);
		} else if(createFromType.equals(RECEIPT)) {
			MInOut inOut = new MInOut(getCtx(), referenceId, get_TrxName());
			invoice.setShipment(inOut);
		}
		//	Save
		invoice.saveEx();
	}
}