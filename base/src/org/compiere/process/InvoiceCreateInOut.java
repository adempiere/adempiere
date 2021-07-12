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
import org.adempiere.exceptions.FillMandatoryException;
import org.adempiere.exceptions.InvoiceFullyMatchedException;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MMatchPO;
import org.compiere.model.MOrderLine;
import org.compiere.util.Env;

import java.math.BigDecimal;
 
/**
 * Create (Generate) Shipment from Invoice
 *	
 * @author Jorg Janke
 * @version $Id: InvoiceCreateInOut.java,v 1.2 2006/07/30 00:51:02 jjanke Exp $
 * 
 * @author Teo Sarca, www.arhipac.ro
 * 			<li>FR [ 1895317 ] InvoiceCreateInOut: you can create many receipts
 */
public class InvoiceCreateInOut extends InvoiceCreateInOutAbstract
{
	public static final String PARAM_M_Warehouse_ID = MInOut.COLUMNNAME_M_Warehouse_ID;
	/** Receipt				*/
	private MInOut inOut = null;

	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		super.prepare();
	}	//	prepare

	
	/**
	 * 	Create Shipment
	 *	@return info
	 *	@throws Exception
	 */
	protected String doIt () throws Exception
	{
		log.info("C_Invoice_ID=" + getRecord_ID() + ", M_Warehouse_ID=" + getWarehouseId());
		if (getRecord_ID() <= 0)
			throw new FillMandatoryException("C_Invoice_ID");
		if (getWarehouseId() == 0)
			throw new FillMandatoryException(PARAM_M_Warehouse_ID);
		//
		MInvoice invoice = new MInvoice (getCtx(), getRecord_ID() , get_TrxName());
		if (invoice.get_ID() <= 0)
			throw new AdempiereException("@NotFound@ @C_Invoice_ID@");
		if (!MInvoice.DOCSTATUS_Completed.equals(invoice.getDocStatus()))
			throw new AdempiereException("@InvoiceCreateDocNotCompleted@");
		//
		for (MInvoiceLine invoiceLine : invoice.getLines(false))
		{
			createLine(invoice, invoiceLine);
		}
		if (inOut == null)
			throw new InvoiceFullyMatchedException();
		//
		return inOut.getDocumentNo();
	}	//	doIt

	/**
	 * Create Shipment/Receipt header
	 * @param invoice Invoice
	 * @return Shipment/Receipt header
	 */
	private MInOut getCreateHeader(MInvoice invoice)
	{
		if (inOut != null)
			return inOut;
		inOut = new MInOut (invoice, 0, null, getWarehouseId());
		inOut.saveEx();
		return inOut;
	}
	
	/**
	 * Create shipment/receipt line
	 * @param invoice Invoice
	 * @param invoiceLine Invoice Line
	 */
	private void createLine(MInvoice invoice, MInvoiceLine invoiceLine)
	{
		if (invoiceLine.getM_InOutLine_ID() > 0)
			return;

		// if is fully delivery don't create anything
		if (invoiceLine.getC_OrderLine_ID() > 0) {
			MOrderLine orderLine = (MOrderLine)invoiceLine.getC_OrderLine();
			BigDecimal qtyNotMatched = orderLine.getQtyOrdered().subtract(MMatchPO.getPOMatchedQuantity(orderLine)).subtract(invoiceLine.getQtyInvoiced());
			// If is fully matched don't create anything
			if (qtyNotMatched.signum() < 0)
				return;
		}
		MInOut inOut = getCreateHeader(invoice);
		MInOutLine inOutLine = new MInOutLine(inOut);
		inOutLine.setInvoiceLine(invoiceLine, 0,	//	Locator
			invoice.isSOTrx() ? invoiceLine.getQtyInvoiced() : Env.ZERO);
		inOutLine.setQtyEntered(invoiceLine.getQtyInvoiced());
		inOutLine.setMovementQty(invoiceLine.getQtyInvoiced());
		if (invoice.isCreditMemo()) {
			inOutLine.setQtyEntered(inOutLine.getQtyEntered().negate());
			inOutLine.setMovementQty(inOutLine.getMovementQty().negate());
		}
		inOutLine.saveEx();
		//
		invoiceLine.setM_InOutLine_ID(inOutLine.getM_InOutLine_ID());
		invoiceLine.saveEx();
		//
	}
}	//	InvoiceCreateInOut
