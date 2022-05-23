/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.									  *
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

package org.spin.freight.process;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.compiere.model.I_C_Invoice;
import org.compiere.model.MBPartner;
import org.compiere.model.MFreightCategory;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Util;
import org.eevolution.model.I_DD_Freight;
import org.eevolution.model.MDDFreightLine;

/** 
 * 	Generated Process for (Generate Sales Invoice from Freight Order)
 *  @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com 
 *  @version Release 3.9.3
 */
public class GenerateSalesInvoiceFromFreightOrder extends GenerateSalesInvoiceFromFreightOrderAbstract {

	private Hashtable<Integer, MInvoice> invoices;
	private AtomicInteger created = new AtomicInteger();
	private StringBuffer generatedDocuments = new StringBuffer();
	
	@SuppressWarnings("unchecked")
	@Override
	protected String doIt() throws Exception {
		invoices  = new Hashtable<Integer, MInvoice>();
		List<MDDFreightLine> freightLines = null;
		//	Get from record
		if(getRecord_ID() > 0 && getTableName().equals(I_DD_Freight.Table_Name)) {
			freightLines = new Query(getCtx(), MDDFreightLine.Table_Name, MDDFreightLine.COLUMNNAME_DD_Freight_ID + "=?", get_TrxName())
				.setParameters(getRecord_ID())
				.setOrderBy(MDDFreightLine.COLUMNNAME_Line)
				.list();
		} else if(isSelection()) {
			getProcessInfo().setTableSelectionId(MDDFreightLine.Table_ID);
			freightLines = ((List<MDDFreightLine>) getInstancesForSelection(get_TrxName()));
		}
		//	Create
		if(freightLines != null) {
			freightLines.stream()
			.filter(freightLine -> freightLine.isInvoiced())
			.filter(freightLine -> freightLine.getC_BPartner_ID() != 0)
			.forEach(freightLine -> createInvoice(freightLine));
		}
		//	
		processingInvoices();
		//	Open Result
		openResult(I_C_Invoice.Table_Name);
		//	
		return "@Created@ " + created + (generatedDocuments.length() > 0? " [" + generatedDocuments + "]": "");
	}
	
	
	/**
	 * Create Invoice from Freight Line
	 * @param freightLine
	 */
	private void createInvoice(MDDFreightLine freightLine) {
		MFreightCategory freightCategory = MFreightCategory.getById(getCtx(), freightLine.getM_FreightCategory_ID(), get_TrxName());
		BigDecimal qtyInvoiced = Env.ZERO;
		if(Util.isEmpty(freightCategory.getFreightCalculationType())
				|| freightCategory.getFreightCalculationType().equals(MFreightCategory.FREIGHTCALCULATIONTYPE_WeightBased)) {
			qtyInvoiced = freightLine.getWeight();
		} else {
			qtyInvoiced = freightLine.getVolume();
		}
		MInvoice invoice = getInvoice(freightLine);
		MInvoiceLine invoiceLine = new MInvoiceLine(invoice);
		// Freight values
		invoiceLine.setDD_FreightLine_ID(freightLine.getDD_FreightLine_ID());
		invoiceLine.setC_Invoice_ID(invoice.get_ID());
		if(freightLine.getM_Product_ID() != 0) {
			invoiceLine.setM_Product_ID(freightLine.getM_Product_ID());
			invoiceLine.setQty(qtyInvoiced);
			invoiceLine.setPrice();
		} else if(freightLine.getC_Charge_ID() != 0){
			invoiceLine.setC_Charge_ID(freightLine.getC_Charge_ID());
			invoiceLine.setQty(Env.ONE);
			invoiceLine.setPrice(freightLine.getFreightAmt());
			invoiceLine.setTax();
		} else {
			invoiceLine.setQty(Env.ONE);
			invoiceLine.setPrice(freightLine.getFreightAmt());
		}
		//	Add description from freight line
		invoiceLine.addDescription(Msg.getMsg(getCtx(), "FreightLineDetailForSales", new Object[] {
				freightLine.getParent().getDocumentNo(), 
				freightLine.getLine(), 
				freightLine.getWeight(), 
				freightLine.getVolume()
				}));
		invoiceLine.setTax();
		invoiceLine.saveEx();
	}
	
	/**
	 * Create Invoice header
	 * @param freightLine
	 * @return MInOut return the Shipment header
	 */
	private MInvoice getInvoice(MDDFreightLine freightLine) {
		//	
		int key = freightLine.getC_BPartner_ID();
		MInvoice invoice = invoices.get(key);
		if(invoice != null)
			return invoice;

		invoice = new MInvoice(getCtx(), 0, get_TrxName());
		if(getDocTypeTargetId() > 0) {
			invoice.setC_DocType_ID(getDocTypeTargetId());
		}
		MBPartner businessPartner = MBPartner.get(getCtx(), freightLine.getC_BPartner_ID());
		invoice.setBPartner(businessPartner);
		invoice.setIsSOTrx(true);
		if(getSalesRepId() != 0) {
			invoice.setSalesRep_ID(getSalesRepId());
		}
		invoice.saveEx();
		invoices.put(key, invoice);
		addLog(invoice.getC_Invoice_ID(), invoice.getDateInvoiced(), null, null);
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
			created.addAndGet(1);
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