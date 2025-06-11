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
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MUOMConversion;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.eevolution.model.MWMInOutBound;
import org.eevolution.model.MWMInOutBoundLine;

/** 
 * Class for generate invoices from outbound orders
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 * @version Release 3.9.3
 * See: https://github.com/adempiere/adempiere/issues/2730
 */
public class GenerateInvoiceInOutBound extends GenerateInvoiceInOutBoundAbstract {
	
	private Hashtable<Integer, MInvoice> invoices;
	private int created = 0;
	private StringBuffer generatedDocuments = new StringBuffer();
	
	@Override
	protected String doIt() throws Exception {
		invoices  = new Hashtable<Integer, MInvoice>();
		List<MWMInOutBoundLine> outBoundLines = null;
		//	Get from record
		if(getRecord_ID() > 0) {
			outBoundLines = new Query(getCtx(), MWMInOutBoundLine.Table_Name, MWMInOutBound.COLUMNNAME_WM_InOutBound_ID + "=?", get_TrxName())
				.setParameters(getRecord_ID())
				.setOrderBy(MWMInOutBoundLine.COLUMNNAME_C_Order_ID + ", " + MWMInOutBoundLine.COLUMNNAME_DD_Order_ID)
				.list();
		} else if(isSelection()) {
			// Overwrite table RV_WM_InOutBoundLine by WM_InOutBoundLine
			getProcessInfo().setTableSelectionId(MWMInOutBoundLine.Table_ID);
			outBoundLines = (List<MWMInOutBoundLine>) getInstancesForSelection(get_TrxName());
		}
		//	Create
		if(outBoundLines != null) {
			outBoundLines.stream()
			.filter(outBoundLine -> outBoundLine.getC_Invoice_ID() <= 0)
			.forEach(outBoundLine -> createInvoice(outBoundLine));
		}
		//	
		processingInvoices();
		//	
		return "@Created@ " + created + (generatedDocuments.length() > 0? " [" + generatedDocuments + "]": "");
	}
	
	/**
	 * Create Invoice from Outbound Line
	 * @param outboundLine
	 */
	private void createInvoice(MWMInOutBoundLine outboundLine) {
		if (outboundLine.getC_OrderLine_ID() > 0) {
			MOrderLine orderLine = outboundLine.getOrderLine();
			if (orderLine.getQtyOrdered().subtract(orderLine.getQtyInvoiced()).subtract(outboundLine.getPickedQty()).signum() < 0 && !getParameterAsBoolean("IsIncludeNotAvailable")) {
				return;
			}

			BigDecimal qtyInvoiced = outboundLine.getPickedQty();
			MInvoice invoice = getInvoice(orderLine, outboundLine.getParent());
			MInvoiceLine invoiceLine = new MInvoiceLine(outboundLine.getCtx(), 0 , outboundLine.get_TrxName());
			invoiceLine.setOrderLine(orderLine);
			// Set Shipment Line
			if (outboundLine.getM_InOutLine_ID() > 0)
				invoiceLine.setM_InOutLine_ID(outboundLine.getM_InOutLine_ID());
			invoiceLine.setC_Invoice_ID(invoice.get_ID());
			invoiceLine.setC_UOM_ID(outboundLine.getC_UOM_ID());
			invoiceLine.setPrice(MUOMConversion.convertProductTo(getCtx(), outboundLine.getM_Product_ID(), outboundLine.getC_UOM_ID(), orderLine.getPriceActual()));
			invoiceLine.setQtyEntered(qtyInvoiced);
			invoiceLine.setQtyInvoiced(qtyInvoiced);
			invoiceLine.setWM_InOutBoundLine_ID(outboundLine.get_ID());
			invoiceLine.saveEx();
		}
	}
	
	/**
	 * Create Invoice header
	 * @param orderLine Sales Order Line
	 * @param outbound Outbound Order
	 * @return MInOut return the Shipment header
	 */
	private MInvoice getInvoice(MOrderLine orderLine, MWMInOutBound outbound) {
		int key = orderLine.getC_Order_ID();
		if(isConsolidateDocument()) {
			key = orderLine.getC_BPartner_ID();
		}
		//	
		MInvoice invoice = invoices.get(key);
		if(invoice != null)
			return invoice;

		MOrder order = orderLine.getParent();
		invoice = new MInvoice(order, 0, getDateInvoiced());
		if(getDocTypeTargetId() > 0) {
			invoice.setC_DocType_ID(getDocTypeTargetId());
		}
		invoice.setIsSOTrx(true);
		invoice.saveEx();

		invoices.put(key, invoice);
		return invoice;
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
	private void processingInvoices() {
		if(invoices == null) {
			return;
		}
		invoices.entrySet().stream().filter(entry -> entry != null).forEach(entry -> {
			MInvoice invoice = entry.getValue();
			invoice.setDocAction(getDocAction());
			if (!invoice.processIt(getDocAction())) {
				addLog("@ProcessFailed@ : " + invoice.getDocumentInfo());
				log.warning("@ProcessFailed@ :" + invoice.getDocumentInfo());
			}
			invoice.saveEx();
			created++;
			addToMessage(invoice.getDocumentNo());
		});
		List<PO> invoicesToPrint = new ArrayList<PO>();
		//	Print invoices
		invoices.entrySet().stream().filter(entry -> entry != null).forEach(entry -> {
			invoicesToPrint.add(entry.getValue());
		});
		//	Print documents
		printDocument(invoicesToPrint, true);
	}
}