/** ****************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2016 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 * ****************************************************************************/

package org.adempiere.pos.process;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MBankStatement;
import org.compiere.model.MPOS;
import org.compiere.model.MPayment;
import org.compiere.model.MPaymentBatch;
import org.compiere.model.MRefList;
import org.compiere.util.DisplayType;
import org.compiere.util.Msg;
import org.compiere.util.Util;


/**
 * This process allows create a transfer between bank account
 * eEvolution author Victor Perez <victor.perez@e-evolution.com>, Created by e-Evolution on 04/04/16.
 */
public class GenerateWithdrawal extends GenerateWithdrawalAbstract {

    protected void prepare() {super.prepare();}

    @Override
    protected String doIt() throws Exception {
    	String description = getDescription();
    	if(Util.isEmpty(description)) {
    		description = Msg.parseTranslation(getCtx(), "@Generated@ @from@ @C_POS_ID@") + DisplayType.getDateFormat(DisplayType.Date).format(getTransactionDate());
    	}
        MPaymentBatch paymentBatchFrom = new MPaymentBatch(getCtx() , 0 , get_TrxName());
        paymentBatchFrom.setName(description);
        paymentBatchFrom.setProcessingDate(getTransactionDate());
        paymentBatchFrom.saveEx();

        MPaymentBatch paymentBatchTo = new MPaymentBatch(getCtx() , 0 , get_TrxName());
        paymentBatchTo.setName(description);
        paymentBatchTo.setProcessingDate(getTransactionDate());
        paymentBatchTo.saveEx();

        List<MRefList> refLists = (List<MRefList>) getInstancesForSelection(get_TrxName());
        refLists.stream().forEach( refList -> {
            BigDecimal amount = getSelectionAsBigDecimal(refList.get_ID() , "TT_Amount");
            String referenceNo = getSelectionAsString (refList.get_ID() , "TT_ReferenceNo");
            if (amount.signum() > 0)
            {
                // Create payment withdrawal from form account bank to account bank
                createPayment(
                        getBankAccountId(),
                        getPOSTerminalId(),
                        getBusinessPartnerId() ,
                        getDocumentNo() ,
                        referenceNo,
                        paymentBatchFrom.getName(),
                        getDocumentTypeId(),
                        getChargeId(),
                        refList.getValue(),
                        getCurrencyId(),
                        amount,
                        getTransactionDate(),
                        getAccountDate(),
                        false, paymentBatchFrom.get_ID());
                // Create receipt withdrawal from form account bank to account bank
                createPayment(
                        getTransferCashtrxtoId(),
                        getPOSTerminalId(),
                        getBusinessPartnerId(),
                        getDocumentNo(),
                        referenceNo,
                        paymentBatchTo.getName(),
                        getCounterDocumentTypeId(),
                        getChargeId(),
                        refList.getValue(),
                        getCurrencyId(),
                        amount,
                        getTransactionDate(),
                        getAccountDate(),
                        true, paymentBatchTo.get_ID());
                }
        });
        return "@Ok@";
    }

    private void createPayment(
            Integer bankAccountId,
            Integer pOSTerminalId,
            Integer businessPartnerId ,
            String documentNo ,
            String referenceNo,
            String description,
            Integer documentTypeId,
            Integer chargeId,
            String tenderType ,
            Integer currencyId,
            BigDecimal amount ,
            Timestamp transactionDate ,
            Timestamp accountDate ,
            Boolean isReceipt,
            Integer paymentBatchId)
    {
            MPayment payment = new MPayment(getCtx() ,  0 , get_TrxName());
            payment.setC_POS_ID(pOSTerminalId);
            MPOS terminal = MPOS.get(getCtx(), pOSTerminalId);
            payment.setDateTrx(transactionDate);
            if (documentNo != null && documentNo.length() > 0)
                payment.setDocumentNo(documentNo);
            if (referenceNo != null && referenceNo.length() > 0)
                payment.setVoiceAuthCode(referenceNo);
            payment.setC_BankAccount_ID(bankAccountId);
            payment.setDateAcct(accountDate);
            payment.setC_BPartner_ID(businessPartnerId);
            payment.addDescription(description);
            payment.setTenderType(tenderType);
            payment.setIsReceipt(isReceipt);
            payment.setC_Charge_ID(chargeId);
            payment.setC_DocType_ID(documentTypeId);
            payment.setC_Currency_ID(currencyId);
            if(terminal.get_ValueAsInt("C_ConversionType_ID") > 0) {
            	payment.setC_ConversionType_ID(terminal.get_ValueAsInt("C_ConversionType_ID"));
            }
            payment.setAmount(currencyId , amount);
            payment.setC_PaymentBatch_ID(paymentBatchId);
            payment.saveEx();
            //	Process It
            if(payment.processIt(MPayment.DOCACTION_Complete)) {
    			payment.saveEx();
    			MBankStatement.addPayment(payment);
            } else {
            	throw new AdempiereException("@Error@ " + payment.getErrorMessage());
            }
            addLog(payment.getDocumentInfo());
    }
}
