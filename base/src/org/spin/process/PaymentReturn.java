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

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import org.compiere.model.MAllocationHdr;
import org.compiere.model.MAllocationLine;
import org.compiere.model.MDocType;
import org.compiere.model.MPayment;

/** Generated Process for (Return Payment)
 *  @author Carlos Parada, cparada@erpya.com, ERPCyA http://www.erpya.com
 */
public class PaymentReturn extends PaymentReturnAbstract
{
	@Override
	protected void prepare()
	{
		super.prepare();
	}

	@Override
	protected String doIt() throws Exception
	{
		String result = "";
		if (getRecord_ID()!=0) {
			MPayment payment = new MPayment(getCtx(), getRecord_ID(), get_TrxName());
			result = generateReturn(payment, getDocTypeId(), getDocumentNo(), getPayDate());
		}
		return result;
	}
	
	/**
	 * Generate Payment Returned
	 * @param parentPayment
	 * @param documentType_ID
	 * @param documentNo
	 * @param payDate
	 * @return
	 */
	private String generateReturn(MPayment parentPayment, int documentType_ID, String documentNo, Timestamp payDate) {
		
		Optional<MPayment> paymentReturn = Optional.empty();
		Optional<MAllocationHdr> paymentAllocation = Optional.empty();
		ArrayList<Optional<MPayment>> payments = new ArrayList<>();
		AtomicReference<String> result = new AtomicReference<>();
		AtomicReference<String> msg = new AtomicReference<>();
		
		result.set("");
		msg.set("");
		
		if (!(parentPayment.getDocStatus().equals(MPayment.DOCSTATUS_Completed)
				|| parentPayment.getDocStatus().equals(MPayment.DOCSTATUS_Closed))) {
			return "@Invalid@ @DocStatus@ -> @C_Payment_ID@ " + parentPayment.getDocumentNo();
		}
		
		MDocType parentDocType = (MDocType) parentPayment.getC_DocType();
		MDocType returnDocType = MDocType.get(getCtx(), documentType_ID);
		
		if (parentDocType.getDocBaseType().equals(returnDocType.getDocBaseType()))
			return "@PaymentDocTypeInvoiceInconsistent@";
		
		Optional<MPayment> paymentReference = Optional.ofNullable(parentPayment.getPaymentReference());
		if (paymentReference.isPresent())
			return "@Invalid@ @C_Payment_ID@ -> @Ref_Payment_ID@  = " + paymentReference.get().getDocumentNo();
		
		
		// Payment Return
		if (parentPayment.getC_Charge_ID() != 0) 
			paymentReturn = Optional.ofNullable(getPayment(parentPayment, documentType_ID, documentNo, payDate));
		else {
			// Payment Return 
			paymentReturn = Optional.ofNullable(getPayment(parentPayment, documentType_ID, documentNo, payDate));
			
			//Unallocated Parent Payment
			List<MAllocationHdr> allocations = Arrays.asList(MAllocationHdr.getOfPayment(getCtx(), parentPayment.getC_Payment_ID(), get_TrxName()));
			allocations.forEach(mAllocationHdr ->{
				if (mAllocationHdr.getDocStatus().equals(MAllocationHdr.DOCSTATUS_Completed)) {
					mAllocationHdr.reverseAccrualIt();
					mAllocationHdr.saveEx();
				}
			});
			
			payments.add(paymentReturn);
			payments.add(Optional.ofNullable(parentPayment));
			paymentAllocation = Optional.ofNullable(getAllocation(payments, payDate));
			
		}
		paymentReturn.ifPresent(payment -> {
			msg.set("@Created@ @C_Payment_ID@ " + payment.getDocumentNo());
			addLog(msg.get());
			result.set(result.get() + msg.get() + "\n");
		});
		
		
		paymentAllocation.ifPresent(allocation -> {
			msg.set("@Created@ @C_AllocationHdr_ID@ " + allocation.getDocumentNo());
			addLog(msg.get());
			result.set(result.get() + msg.get() + "\n");
		});
		
		return result.get();
	}
	
	/**
	 * Get / Create Payment 
	 * @param parentPayment
	 * @param documentType_ID
	 * @param documentNo
	 * @param payDate
	 * @return
	 */
	private MPayment getPayment(MPayment parentPayment, int documentType_ID, String documentNo, Timestamp payDate) {
		MPayment paymentReturn = new MPayment(getCtx(), 0, get_TrxName());
		MPayment.copyValues(parentPayment, paymentReturn);
		paymentReturn.setC_DocType_ID(documentType_ID);
		paymentReturn.setDocumentNo(documentNo);
		paymentReturn.setDateTrx(payDate);
		paymentReturn.setDateAcct(payDate);
		paymentReturn.setRef_Payment_ID(parentPayment.getC_Payment_ID());
		paymentReturn.saveEx();
		return paymentReturn;
	}
	
	/**
	 * Get / Create Payment Allocation
	 * @param payments
	 * @param payDate
	 * @return
	 */
	private MAllocationHdr getAllocation(List<Optional<MPayment>> payments, Timestamp payDate) {
		AtomicReference<MAllocationHdr> currentAlloc = new AtomicReference<>();
		payments.stream().forEach(optPayment ->{
			optPayment.ifPresent(payment -> {
				if (currentAlloc.get()==null) {
					currentAlloc.set(new MAllocationHdr(getCtx(), false, payDate, payment.getC_Currency_ID(), "", get_TrxName()));
					currentAlloc.get().save();
				}
				MAllocationLine parentLine = new MAllocationLine(currentAlloc.get());
				parentLine.setAmount(payment.getPayAmt(true));
				parentLine.setC_Payment_ID(payment.getC_Payment_ID());
				parentLine.setC_BPartner_ID(payment.getC_BPartner_ID());
				parentLine.saveEx();	
			});
		});
		
		if (currentAlloc.get()!=null) {
			currentAlloc.get().processIt(MAllocationHdr.DOCSTATUS_Completed);
			currentAlloc.get().saveEx();
		}
		return currentAlloc.get();
	}
}