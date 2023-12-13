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

import java.util.ArrayList;
import java.util.List;

import org.adempiere.core.domains.models.I_C_CommissionAmt;
import org.compiere.model.MBPartner;
import org.compiere.model.MCommission;
import org.compiere.model.MCommissionAmt;
import org.compiere.model.MCommissionLine;
import org.compiere.model.MCommissionRun;
import org.compiere.model.MCurrency;
import org.compiere.model.MDocType;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MPriceList;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.compiere.util.Trx;

/**
 *	Create AP Invoices for Commission
 *	
 *  @author Jorg Janke
 *  @version $Id: CommissionAPInvoice.java,v 1.2 2006/07/30 00:51:01 jjanke Exp $
 */
public class CommissionAPInvoice extends CommissionAPInvoiceAbstract {

	private List<String> created = new ArrayList<String>();
	
	/**
	 *  Perform process.
	 *  @return Message (variables are parsed)
	 *  @throws Exception if not successful
	 */
	protected String doIt() throws Exception {
		log.info("doIt - C_CommissionRun_ID=" + getRecord_ID());
		//	Load Data
		MCommissionRun commissionRun = new MCommissionRun (getCtx(), getRecord_ID(), get_TrxName());
		if (commissionRun.getC_CommissionRun_ID() == 0) {
			throw new IllegalArgumentException("@C_CommissionRun_ID@ @NotFound@");
		}
		if(!commissionRun.getDocStatus().equals(MCommissionRun.DOCSTATUS_Completed)) {
			throw new IllegalArgumentException("@C_CommissionRun_ID@ @document.status.invalid@");
		}
		if (Env.ZERO.compareTo(commissionRun.getGrandTotal()) == 0) {
			throw new IllegalArgumentException("@GrandTotal@ = 0");
		}
		//	
		getCommissionAmtList(commissionRun).forEach(commissionAmount -> {
			Trx.run(transactionName -> createInvoiceFromCommissionAmount(commissionAmount, transactionName));
		});
		//
		return "@Created@: " +  created.toString();
	}	//	doIt
	
	private List<MCommissionAmt> getCommissionAmtList(MCommissionRun commissionRun) {
		if(getBPartnerId() > 0) {
			return new Query(getCtx(), I_C_CommissionAmt.Table_Name, "C_CommissionRun_ID = ? AND C_BPartner_ID = ?", null)
					.setParameters(getRecord_ID(), getBPartnerId())
					.<MCommissionAmt>list();
		}
		return commissionRun.getCommissionAmtList();
	}
	
	private void createInvoiceFromCommissionAmount(MCommissionAmt commissionAmount, String transactionName) {
		MCommissionLine commissionLine = new MCommissionLine(getCtx(), commissionAmount.getC_CommissionLine_ID(), transactionName);
		MCommission commissionDefinition = (MCommission) commissionLine.getC_Commission();
		if(commissionDefinition == null) {
			throw new IllegalArgumentException("@C_Commission_ID@ @NotFound@");
		}
		if(commissionDefinition.getC_Charge_ID() == 0) {
			throw new IllegalArgumentException("@C_Charge_ID@ @NotFound@");
		}
		MBPartner businessPartner = null;
		if(commissionAmount.getC_BPartner_ID() > 0) {
			businessPartner = new MBPartner (getCtx(), commissionAmount.getC_BPartner_ID(), get_TrxName());
		} else if(commissionDefinition.getC_BPartner_ID() > 0) {
			businessPartner = new MBPartner (getCtx(), commissionDefinition.getC_BPartner_ID(), get_TrxName());
		}
		if (businessPartner == null || businessPartner.get_ID() == 0) {
			throw new IllegalArgumentException("@C_BPartner_ID@ @NotFound@");
		}
		//	Create Invoice
		MInvoice invoice = new MInvoice (getCtx(), 0, transactionName);
		invoice.setClientOrg(commissionDefinition.getAD_Client_ID(), commissionDefinition.getAD_Org_ID());
		invoice.setC_DocTypeTarget_ID(MDocType.DOCBASETYPE_APInvoice);	//	API
		invoice.setBPartner(businessPartner);
		invoice.setSalesRep_ID(getAD_User_ID());	//	caller
		String currencyIsoCode = MCurrency.get(getCtx(), commissionDefinition.getC_Currency_ID()).getISO_Code();
		MPriceList priceList = MPriceList.getDefault(getCtx(), true, currencyIsoCode);
		if(priceList == null) {
			throw new IllegalArgumentException("@M_PriceList_ID@ @NotFound@ (@C_Currency_ID@ " + currencyIsoCode + ")");
		}
		invoice.setM_PriceList_ID(priceList.getM_PriceList_ID());
		//		
		invoice.saveEx();
			
 		//	Create Invoice Line
 		MInvoiceLine invoiceLine = new MInvoiceLine(invoice);
		invoiceLine.setC_Charge_ID(commissionDefinition.getC_Charge_ID());
 		invoiceLine.setQty(1);
 		invoiceLine.setPrice(commissionAmount.getCommissionAmt());
		invoiceLine.setTax();
		invoiceLine.saveEx();
		//
		created.add(businessPartner.getValue() + " - " + businessPartner.getName() + ": " + invoice.getDocumentNo());
	}

}	//	CommissionAPInvoice
