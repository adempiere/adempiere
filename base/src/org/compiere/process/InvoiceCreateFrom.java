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
	/**	Create From Type			*/
	private String 		createFromType = null;
	/**	Created						*/
	private int 		created = 0;
	/**	Invoice						*/
	private MInvoice 	invoice = null;
	/**	Reference Identifier		*/
	private int 		reference_ID = 0;
	
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
		invoice = new MInvoice(getCtx(), getRecord_ID(), get_TrxName());
		log.config(invoice.toString());
		//	Loop
		for(Integer key : getSelectionKeys()) {
			if(createFromType == null) {
				createFromType = getSelectionAsString(key, "CF_CreateFromType");
				//	Valid Mandatory Create From Type
				if(createFromType == null
						|| createFromType.length() == 0)
					throw new AdempiereException("@CreateFromType@ @NotFound@");
			}
			// variable values
			
			int m_M_Product_ID = getSelectionAsInt(key, "CF_M_Product_ID");
			int m_C_Charge_ID = getSelectionAsInt(key, "CF_C_Charge_ID");
			int m_C_UOM_ID = getSelectionAsInt(key, "CF_C_UOM_ID");
			BigDecimal m_QtyEntered = getSelectionAsBigDecimal(key, "CF_QtyEntered"); // Qty
			// If a locator is specified on the product, choose that otherwise default locator
			//	Precision of Qty UOM
			int precision = 2;
			if (m_M_Product_ID != 0) {
				MProduct product = MProduct.get(getCtx(), m_M_Product_ID);
				precision = product.getUOMPrecision();
			}
			m_QtyEntered = m_QtyEntered.setScale(precision, BigDecimal.ROUND_HALF_DOWN);
			//
			log.fine("Line QtyEntered=" + m_QtyEntered
					+ ", Product=" + m_M_Product_ID 
					+ ", CreateFromType=" + createFromType + ", Key=" + key);

			//	Create new Invoice Line
			MInvoiceLine invoiceLine = new MInvoiceLine(invoice);
			BigDecimal QtyInvoiced = null;
			MProduct product = null;
			if (m_M_Product_ID != 0) {
				product = MProduct.get(Env.getCtx(), m_M_Product_ID);
				precision = product.getUOMPrecision();
				invoiceLine.setM_Product_ID(m_M_Product_ID, m_C_UOM_ID);	//	Line UOM
			} else if(m_C_Charge_ID != 0){
				invoiceLine.setC_Charge_ID(m_C_Charge_ID);
			}
			//	
			if (product != null 
					&& product.getC_UOM_ID() != m_C_UOM_ID) {
				QtyInvoiced = MUOMConversion.convertProductFrom(Env.getCtx(), m_M_Product_ID, m_C_UOM_ID, m_QtyEntered);
			}
			//	
			m_QtyEntered = m_QtyEntered.setScale(precision, BigDecimal.ROUND_HALF_DOWN);
			if (QtyInvoiced == null)
				QtyInvoiced = m_QtyEntered;
			//	
			invoiceLine.setQty(m_QtyEntered);							//	Movement/Entered
			invoiceLine.setQtyInvoiced(QtyInvoiced);
			//
			if(createFromType.equals(ORDER)) {
				MOrderLine orderLine = new MOrderLine (getCtx(), key, get_TrxName());
				//	Set reference
				if(reference_ID == 0)
					reference_ID = orderLine.getC_Order_ID();
				//	Set InOut
				String whereClause = "EXISTS (SELECT 1 "
						+ "FROM M_InOut io "
						+ "WHERE io.M_InOut_ID = M_InOutLine.M_InOut_ID "
						+ "AND io.DocStatus IN ('CO','CL'))";
				MInOutLine[] lines = MInOutLine.getOfOrderLine(Env.getCtx(),
					key, whereClause, get_TrxName());
				log.fine ("Receipt Lines with OrderLine = #" + lines.length);
				MInOutLine inoutLine = null;
				if (lines.length > 0) {
					for (int j = 0; j < lines.length; j++) {
						MInOutLine line = lines[j];
						if (line.getQtyEntered().compareTo(m_QtyEntered) == 0) {
							inoutLine = line;
							break;
						}
					}
					if (inoutLine == null) {
						inoutLine = lines[0];	//	first as default
					}
				}
				//	Set From
				if(inoutLine != null)
					invoiceLine.setShipLine(inoutLine);
				else
					invoiceLine.setOrderLine(orderLine);
			} else if(createFromType.equals(INVOICE)) {
				MInvoiceLine fromLine = new MInvoiceLine(getCtx(), key, get_TrxName());
				MInvoice invoice = invoiceLine.getParent();
				//	Set reference
				if(reference_ID == 0)
					reference_ID = invoice.getC_Invoice_ID();
				//	Copy Values
				PO.copyValues(fromLine, invoiceLine);
				invoiceLine.setC_Invoice_ID(invoice.getC_Invoice_ID());
				invoiceLine.setAD_Org_ID(fromLine.getAD_Org_ID());
				//	Reset
				invoiceLine.setC_OrderLine_ID(0);
				invoiceLine.setRef_InvoiceLine_ID(0);
				invoiceLine.setM_InOutLine_ID(0);
				invoiceLine.setA_Asset_ID(0);
				invoiceLine.setM_AttributeSetInstance_ID(0);
				invoiceLine.setS_ResourceAssignment_ID(0);
				//	New Tax
				if (invoice.getC_BPartner_ID() != fromLine.getC_Invoice().getC_BPartner_ID())
					invoiceLine.setTax();	//	recalculate
				//
				invoiceLine.setProcessed(false);
			} else if(createFromType.equals(RMA)) {
				MRMALine rmaLine = new MRMALine(getCtx(), key, get_TrxName());
				//	Set reference
				if(reference_ID == 0)
					reference_ID = rmaLine.getM_RMA_ID();
				//	
				invoiceLine.setRMALine(rmaLine);
			} else if(createFromType.equals(RECEIPT)) {
				MInOutLine inOutLine = new MInOutLine(getCtx(), key, get_TrxName());
				//	Set reference
				if(reference_ID == 0)
					reference_ID = inOutLine.getM_InOut_ID();
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
			created ++;
		}
		//	Add reference to Order / Invoice / RMA
		addReference();
		//	
		return "@Created@ " + created;
	}
	
	/**
	 * Add Reference to Order / Invoice / RMA
	 */
	private void addReference() {
		//	Valid Reference
		if(reference_ID == 0)
			return;
		if(createFromType.equals(ORDER)) {
			MOrder p_order = new MOrder(getCtx(), reference_ID, get_TrxName());
			invoice.setOrder(p_order);
		} else if(createFromType.equals(INVOICE)) {
			MInvoice fromInvoice = new MInvoice(getCtx(), reference_ID, get_TrxName());
			invoice.setAD_OrgTrx_ID(fromInvoice.getAD_OrgTrx_ID());
			invoice.setC_Project_ID(fromInvoice.getC_Project_ID());
			invoice.setC_Campaign_ID(fromInvoice.getC_Campaign_ID());
			invoice.setC_Activity_ID(fromInvoice.getC_Activity_ID());
			invoice.setUser1_ID(fromInvoice.getUser1_ID());
			invoice.setUser2_ID(fromInvoice.getUser2_ID());
		} else if(createFromType.equals(RMA)) {
			MRMA rma = new MRMA(getCtx(), reference_ID, get_TrxName());
			invoice.setRMA(rma);
		} else if(createFromType.equals(RECEIPT)) {
			MInOut inOut = new MInOut(getCtx(), reference_ID, get_TrxName());
			invoice.setShipment(inOut);
		}
		//	Save
		invoice.saveEx();
	}
}