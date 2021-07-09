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
package org.compiere.process;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
 
import java.util.Optional;

/**
 *	Create (Generate) Invoice from Shipment
 *	
 *  @author Jorg Janke
 *  @version $Id: InOutCreateInvoice.java,v 1.2 2006/07/30 00:51:02 jjanke Exp $
 */
public class InOutCreateInvoice extends InOutCreateInvoiceAbstract
{

	/* Invoice Header */
	private MInvoice invoice = null;
	
	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare() {
		super.prepare();
	}	//	prepare

	/**
	 * 	Create Invoice.
	 *	@return document no
	 *	@throws Exception
	 */
	protected String doIt () throws Exception {
		log.info("M_InOut_ID=" + getRecord_ID()
			+ ", M_PriceList_ID=" + getPriceListId()
			+ ", InvoiceDocumentNo=" + getInvoiceDocumentNo());
		if (getRecord_ID() == 0)
			throw new IllegalArgumentException("No Shipment");
		//
		MInOut materialReceipt = new MInOut (getCtx(), getRecord_ID(), get_TrxName());
		if (materialReceipt.get_ID() == 0)
			throw new IllegalArgumentException("Shipment not found");
		if (!MInOut.DOCSTATUS_Completed.equals(materialReceipt.getDocStatus()))
			throw new IllegalArgumentException("Shipment not completed");
		
		MInOutLine[] materialReceiptLines = materialReceipt.getLines(false);
		for (MInOutLine materialReceiptLine : materialReceiptLines) {
			Optional<MInvoiceLine> maybeInvoiceLine = Optional.ofNullable(MInvoiceLine.getOfInOutLine(materialReceiptLine));
			if(maybeInvoiceLine.isPresent())
				continue;

			MInvoice invoice = getCreateHeader(materialReceipt);
			MInvoiceLine invoiceLine = new MInvoiceLine(invoice);
			invoiceLine.setShipLine(materialReceiptLine);
			if (materialReceiptLine.sameOrderLineUOM())
				invoiceLine.setQtyEntered(materialReceiptLine.getQtyEntered());
			else
				invoiceLine.setQtyEntered(materialReceiptLine.getMovementQty());
			invoiceLine.setQtyInvoiced(materialReceiptLine.getMovementQty());
			if (!invoiceLine.save())
				throw new IllegalArgumentException("Cannot save Invoice Line");
		}
		if (invoice == null)
			throw new AdempiereException("@InvoiceFullyMatched@");

		return invoice.getDocumentNo();
	}	//	InOutCreateInvoice
	
	private MInvoice getCreateHeader(MInOut shipment)
	{
		if (invoice != null)
			return invoice;

		invoice = new MInvoice (shipment, null);
		// Should not override pricelist for RMA
		if (getPriceListId() != 0 && shipment.getM_RMA_ID() == 0)
			invoice.setM_PriceList_ID(getPriceListId());
		if (getInvoiceDocumentNo() != null && getInvoiceDocumentNo().length() > 0)
			invoice.setDocumentNo(getInvoiceDocumentNo());
		if (!invoice.save())
			throw new IllegalArgumentException("Cannot save Invoice");
		return invoice;
	}
}	//	InOutCreateInvoice
