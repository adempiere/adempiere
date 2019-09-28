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

package org.spin.process;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.I_C_Payment;
import org.compiere.model.MAllocationHdr;
import org.compiere.model.MAllocationLine;
import org.compiere.model.MBPartner;
import org.compiere.model.MOrgInfo;
import org.compiere.model.MPayment;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/** Generated Process for (Identify Payment)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.2
 */
public class PaymentIdentify extends PaymentIdentifyAbstract {

	@Override
	protected String doIt() throws Exception {
		if(getRecord_ID() == 0) {
			throw new AdempiereException("@Record_ID@ @NotFound@");
		}
		//	Unidentified payment
		MPayment unidentifiedPayment = new MPayment(getCtx(), getRecord_ID(), get_TrxName());
		if(!unidentifiedPayment.isUnidentifiedPayment()
				|| unidentifiedPayment.getC_Charge_ID() != 0
				|| (unidentifiedPayment.isUnidentifiedPayment() && unidentifiedPayment.getRef_Payment_ID() != 0)) {
			throw new AdempiereException("@IsUnidentifiedPayment@ @Invalid@");
		}
		if(!unidentifiedPayment.getDocStatus().equals(MPayment.DOCSTATUS_Completed)
				&& !unidentifiedPayment.getDocStatus().equals(MPayment.DOCSTATUS_Closed)) {
			throw new AdempiereException("@C_Payment_ID@ @NoCompleted@");
		}
		MPayment relatedPayment = new Query(getCtx(), I_C_Payment.Table_Name, "Ref_Payment_ID = ? AND DocStatus IN('CO', 'CL') AND IsUnidentifiedPayment = 'Y'", get_TrxName())
				.setParameters(unidentifiedPayment.getC_Payment_ID())
				.first();
		if(relatedPayment != null
				&& relatedPayment.getC_Payment_ID() != 0) {
			throw new AdempiereException("@UnidentifiedReverseCreated@");
		}
		MPayment identifiedPayment = null;
		//	Validate selected payment
		if(getTrxType().equals("S")) {
			identifiedPayment = new MPayment(getCtx(), getRelatedPaymentId(), get_TrxName());
			//	Receipt
			if(identifiedPayment.isReceipt() != unidentifiedPayment.isReceipt()) {
				throw new AdempiereException("@IsReceipt@ @Mismatch@");
			}
			//	Bank Account
			if(identifiedPayment.getC_BankAccount_ID() != unidentifiedPayment.getC_BankAccount_ID()) {
				throw new AdempiereException("@C_BankAccount_ID@ @Mismatch@");
			}
			//	Currency
			if(identifiedPayment.getC_Currency_ID() != unidentifiedPayment.getC_Currency_ID()) {
				throw new AdempiereException("@C_Currency_ID@ @Mismatch@");
			}
			//	Amount
			if(!identifiedPayment.getPayAmt().equals(unidentifiedPayment.getPayAmt())) {
				throw new AdempiereException("@PayAmt@ @Mismatch@");
			}
			//	Document Status
			if(!identifiedPayment.getDocStatus().equals(MPayment.STATUS_Completed)) {
				throw new AdempiereException("@PayAmt@ @Mismatch@");
			}
		} else {
			identifiedPayment = new MPayment(getCtx(), 0, get_TrxName());
			PO.copyValues(unidentifiedPayment, identifiedPayment);
			identifiedPayment.setDateTrx(getDateTrx());
			identifiedPayment.setDateAcct(getDateTrx());
			identifiedPayment.setC_BPartner_ID(getBPartnerId());
			identifiedPayment.setIsUnidentifiedPayment(false);
			identifiedPayment.setC_Charge_ID(-1);
			identifiedPayment.setC_Invoice_ID(-1);
			identifiedPayment.setC_Order_ID(-1);
			identifiedPayment.setIsReconciled(false);
			//	Order
			if(getOrderId() != 0) {
				identifiedPayment.setC_Order_ID(getOrderId());
			}
			//	Invoice
			if(getInvoiceId() != 0) {
				identifiedPayment.setC_Invoice_ID(getInvoiceId());
			}
			identifiedPayment.setDocStatus(MPayment.DOCSTATUS_Drafted);
			identifiedPayment.saveEx();
			identifiedPayment.processIt(MPayment.DOCACTION_Complete);
			identifiedPayment.saveEx();
		}
		identifiedPayment.setRef_Payment_ID(unidentifiedPayment.getC_Payment_ID());
		identifiedPayment.saveEx();
		//	Create reversed for Unidentified
		MPayment reversePayment = new MPayment(getCtx(), 0, get_TrxName());
		PO.copyValues(unidentifiedPayment, reversePayment);
		//	
		reversePayment.setRef_Payment_ID(unidentifiedPayment.getC_Payment_ID());
		reversePayment.setIsReconciled(false);
		reversePayment.setIsUnidentifiedPayment(true);
		reversePayment.setIsReceipt(!unidentifiedPayment.isReceipt());
		reversePayment.setPayAmt(reversePayment.getPayAmt());
		//	Get from organization
		MOrgInfo organizationInfo = MOrgInfo.get(getCtx(), reversePayment.getAD_Org_ID(), get_TrxName());
		if(organizationInfo.getUnidentifiedDocumentType(reversePayment.isReceipt()) != 0) {
			reversePayment.setC_DocType_ID(organizationInfo.getUnidentifiedDocumentType(reversePayment.isReceipt()));
		} else {
			reversePayment.setC_DocType_ID(reversePayment.isReceipt());
		}
		reversePayment.setDocStatus(MPayment.DOCSTATUS_Drafted);
		reversePayment.saveEx();
		reversePayment.processIt(MPayment.DOCACTION_Complete);
		reversePayment.saveEx();
		//	Create Allocation
		createAllocation(unidentifiedPayment, reversePayment);
		return "@Created@";
	}
	
	/**
	 * Create allocation
	 * @param identifiedPayment
	 * @param unidentifiedPayment
	 */
	private void createAllocation(MPayment identifiedPayment, MPayment unidentifiedPayment) {
		//	Create automatic Allocation
		MAllocationHdr allocationHdr = new MAllocationHdr (getCtx(), false, getDateTrx(), identifiedPayment.getC_Currency_ID(),
				Msg.translate(getCtx(), "C_Payment_ID")	+ ": " + unidentifiedPayment.getDocumentNo(), get_TrxName());
		allocationHdr.setAD_Org_ID(identifiedPayment.getAD_Org_ID());
		allocationHdr.setDateAcct(identifiedPayment.getDateAcct());
		allocationHdr.saveEx(get_TrxName());

		//	Original Allocation
		MAllocationLine allocationLine = new MAllocationLine (allocationHdr, identifiedPayment.getPayAmt(true), Env.ZERO, Env.ZERO, Env.ZERO);
		allocationLine.setDocInfo(identifiedPayment.getC_BPartner_ID(), 0, 0);
		allocationLine.setPaymentInfo(identifiedPayment.getC_Payment_ID(), 0);
		allocationLine.saveEx(get_TrxName());

		//	Reversal Allocation
		allocationLine = new MAllocationLine (allocationHdr, unidentifiedPayment.getPayAmt(true), Env.ZERO, Env.ZERO, Env.ZERO);
		allocationLine.setDocInfo(unidentifiedPayment.getC_BPartner_ID(), 0, 0);
		allocationLine.setPaymentInfo(unidentifiedPayment.getC_Payment_ID(), 0);
		allocationLine.saveEx(get_TrxName());

		if (!allocationHdr.processIt(DocAction.ACTION_Complete)) {
			throw new AdempiereException(allocationHdr.getProcessMsg());
		}

		allocationHdr.saveEx(get_TrxName());
		StringBuffer info = new StringBuffer (unidentifiedPayment.getDocumentNo());
		info.append(" - @C_AllocationHdr_ID@: ").append(allocationHdr.getDocumentNo());

		//	Update BPartner
		if (identifiedPayment.getC_BPartner_ID() != 0) {
			MBPartner partner = new MBPartner (getCtx(), identifiedPayment.getC_BPartner_ID(), get_TrxName());
			partner.setTotalOpenBalance();
			partner.save(get_TrxName());
		}
		identifiedPayment.setIsAllocated(true);
		identifiedPayment.saveEx();
		unidentifiedPayment.setIsAllocated(true);
		unidentifiedPayment.saveEx();
	}
}