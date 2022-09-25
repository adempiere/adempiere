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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.adempiere.core.domains.models.I_C_Invoice;
import org.adempiere.core.domains.models.I_C_Order;
import org.adempiere.core.domains.models.I_M_RMA;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MInvoice;
import org.compiere.model.PO;
import org.compiere.process.InvoiceGenerate;
import org.compiere.process.ProcessInfo;
import org.eevolution.services.dsl.ProcessBuilder;

/** 
 * 	Generated Process for (Generate Invoices from Sales Orders)
 *  @author @author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 *  @version Release 3.9.3
 */
public class GenerateInvoiceFromSalesOrders extends GenerateInvoiceFromSalesOrdersAbstract {
	/**	Generated Documents	*/
	private StringBuffer generatedDocuments = new StringBuffer();
	
	@Override
	protected String doIt() throws Exception {
		boolean isOldRMA = getSelectionAsBoolean(getSelectionKeys().stream().findFirst().get(), "DT_IsShowRMA");
		int processId = 134;  // HARDCODED    C_Invoice_Generate (manual)
		int tableId = I_C_Order.Table_ID;
		if(isOldRMA) {
			processId = 52002; // C_Invoice_GenerateRMA - org.adempiere.process.InvoiceGenerateRMA
			tableId = I_M_RMA.Table_ID; // HARDCODED    C_Invoice_Generate (manual)
		}
		ProcessInfo processInfo = ProcessBuilder
				.create(getCtx())
		        .process(processId)
		        .withParameter(InvoiceGenerate.DATEINVOICED, getDateInvoiced())
		        .withParameter(InvoiceGenerate.DOCACTION, getDocAction())
		        .withParameter(InvoiceGenerate.CONSOLIDATEDOCUMENT, isConsolidateDocument())
		        .withParameter(C_DOCTYPETARGET_ID, getDocTypeTargetId())
		        .withSelectedRecordsIds(tableId, getSelectionKeys())
		        .withoutTransactionClose()
		        .execute(get_TrxName());
		List<PO> documents = new ArrayList<PO>();
		//	Validate Running
		if(processInfo != null) {
			if(processInfo.isError()) {
				throw new AdempiereException(processInfo.getSummary());
			}
			//	
			if(processInfo.getIDs() != null) {
				PO.getInstances(I_C_Invoice.Table_ID, Arrays.stream(processInfo.getIDs()).boxed().collect(Collectors.toList()), get_TrxName())
				.forEach(invoice -> {
					documents.add((PO) invoice);
					addToMessage(((MInvoice) invoice).getDocumentNo());
				});
				//	Print all
				printDocument(documents, true);
			}
		}
		//	
		return "@Created@ " + documents.size() + (generatedDocuments.length() > 0? " [" + generatedDocuments + "]": "");
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
}