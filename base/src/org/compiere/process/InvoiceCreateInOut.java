/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
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

import java.util.logging.*;

import org.compiere.util.*;
import org.compiere.model.*;
 
/**
 *	Create (Generate) Shipment from Invoice
 *	
 *  @author Jorg Janke
 *  @version $Id: InvoiceCreateInOut.java,v 1.2 2006/07/30 00:51:02 jjanke Exp $
 */
public class InvoiceCreateInOut extends SvrProcess
{
	/**	Warehouse			*/
	private int p_M_Warehouse_ID = 0;
	/** Invoice				*/
	private int p_C_Invoice_ID = 0;

	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("M_Warehouse_ID"))
				p_M_Warehouse_ID = para[i].getParameterAsInt();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		p_C_Invoice_ID = getRecord_ID();
	}	//	prepare

	
	/**
	 * 	Create Shipment
	 *	@return info
	 *	@throws Exception
	 */
	protected String doIt () throws Exception
	{
		log.info("C_Invoice_ID=" + p_C_Invoice_ID 
			+ ", M_Warehouse_ID=" + p_M_Warehouse_ID);
		if (p_C_Invoice_ID == 0)
			throw new IllegalArgumentException("@NotFound@ @C_Invoice_ID@");
		if (p_M_Warehouse_ID == 0)
			throw new IllegalArgumentException("@NotFound@ @M_Warehouse_ID@");
		//
		MInvoice invoice = new MInvoice (getCtx(), p_C_Invoice_ID, null);
		if (invoice.get_ID() == 0)
			throw new IllegalArgumentException("@NotFound@ @C_Invoice_ID@");
		if (!MInvoice.DOCSTATUS_Completed.equals(invoice.getDocStatus()))
			throw new IllegalArgumentException("@InvoiceCreateDocNotCompleted@");
		//
		MInOut ship = new MInOut (invoice, 0, null, p_M_Warehouse_ID);
		if (!ship.save())
			throw new IllegalArgumentException("@SaveError@ Receipt");
		//
		MInvoiceLine[] invoiceLines = invoice.getLines(false);
		for (int i = 0; i < invoiceLines.length; i++)
		{
			MInvoiceLine invoiceLine = invoiceLines[i];
			MInOutLine sLine = new MInOutLine(ship);
			sLine.setInvoiceLine(invoiceLine, 0,	//	Locator 
				invoice.isSOTrx() ? invoiceLine.getQtyInvoiced() : Env.ZERO);
			sLine.setQtyEntered(invoiceLine.getQtyEntered());
			sLine.setMovementQty(invoiceLine.getQtyInvoiced());
			if (invoice.isCreditMemo())
			{
				sLine.setQtyEntered(sLine.getQtyEntered().negate());
				sLine.setMovementQty(sLine.getMovementQty().negate());
			}
			if (!sLine.save())
				throw new IllegalArgumentException("@SaveError@ @M_InOutLine_ID@");
			//
			invoiceLine.setM_InOutLine_ID(sLine.getM_InOutLine_ID());
			if (!invoiceLine.save())
				throw new IllegalArgumentException("@SaveError@ @C_InvoiceLine_ID@");
		}
		
		return ship.getDocumentNo();
	}	//	doIt
	
}	//	InvoiceCreateInOut
