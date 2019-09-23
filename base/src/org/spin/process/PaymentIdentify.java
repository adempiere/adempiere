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
import org.compiere.model.MPayment;
import org.compiere.model.PO;
import org.compiere.model.Query;

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
			identifiedPayment.setIsAllocated(false);
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
		reversePayment.setIsAllocated(true);
		reversePayment.setPayAmt(reversePayment.getPayAmt().negate());
		reversePayment.setDocStatus(MPayment.DOCSTATUS_Drafted);
		reversePayment.saveEx();
		reversePayment.processIt(MPayment.DOCACTION_Complete);
		reversePayment.saveEx();
		return "@Created@";
	}
}