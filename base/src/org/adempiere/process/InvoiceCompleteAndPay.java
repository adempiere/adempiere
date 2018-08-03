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

package org.adempiere.process;

import org.compiere.model.*;
import org.compiere.print.MPrintFormat;
import org.compiere.print.ServerReportCtl;
import org.compiere.process.ProcessInfo;
import org.compiere.util.Env;

import java.math.BigDecimal;

/** Generated Process for (Invoice Complete And Pay)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.1
 */
public class InvoiceCompleteAndPay extends InvoiceCompleteAndPayAbstract

{
	@Override
	protected void prepare()
	{
		super.prepare();
		if (getCheckNo()==0){
            X_C_BankAccountDoc cBankAccountDoc = new X_C_BankAccountDoc(getCtx(),getBankAccountDocId(), get_TrxName());
            Integer checkNo = cBankAccountDoc.getCurrentNext();
            setCheckNo(checkNo);
        }
	}

	@Override
	protected String doIt() throws Exception
	{
		MInvoice invoice = new MInvoice(getCtx(), getRecord_ID(), get_TrxName());
		MPaymentTerm paymentTerm = (MPaymentTerm)invoice.getC_PaymentTerm();
		Boolean notappropriate = false;
		if (paymentTerm.getSchedule(true).length>0 || paymentTerm.getDiscount().compareTo(Env.ZERO) != 0
                || paymentTerm.getDiscount2().compareTo(Env.ZERO) != 0)
		    notappropriate = true;
		if (notappropriate)
		    return "@An Invoice with a payment Schedule or a discount cannot be used is this process";

		if ((invoice.getDocStatus().equals("DR") || invoice.getDocStatus().equals("IP"))){
			if (!invoice.processIt("CO")) return "@Could not complete Invoice";
			invoice.saveEx();
		}
		MBankAccount bankAccount = new MBankAccount(getCtx(), getCBankAccountId(), get_TrxName());
		BigDecimal payAmount = getPayAmt();
		BigDecimal convertedAmount=payAmount;

		if (bankAccount.getC_Currency_ID() !=  invoice.getC_Currency_ID()){
			convertedAmount = MConversionRate.convert(getCtx(), payAmount, invoice.getC_Currency_ID(),
					bankAccount.getC_Currency_ID(), invoice.getDateInvoiced(),
					getConversionTypeId(), invoice.getAD_Client_ID(),
					invoice.getAD_Org_ID());
			if (convertedAmount == null)
				return "@No ConversionRate@";
	}
		MPayment payment = new MPayment(getCtx(), 0, get_TrxName());
		payment.setC_Invoice_ID(invoice.getC_Invoice_ID());
		payment.setC_DocType_ID(getDocTypeId());
		payment.setC_BPartner_ID(invoice.getC_BPartner_ID());
		payment.setPayAmt(convertedAmount);
		payment.setC_BankAccount_ID(getCBankAccountId());
		payment.setC_Currency_ID(invoice.getC_Currency_ID());
		payment.setTenderType(paymentRuleConvertTenderType(getPaymentRule()));
		payment.setDescription(getDescription());
        X_C_BankAccountDoc bankAccountDoc = new X_C_BankAccountDoc(getCtx(), getBankAccountDocId(), null);
		if (MPayment.TENDERTYPE_CreditCard.equals(payment.getTenderType())){
			payment.setCreditCardNumber(getCreditCardNumber());
			payment.setCreditCardType(getCreditCardType());
		}
        if (MPayment.TENDERTYPE_Check.equals(payment.getTenderType())){
		    if (getCheckNo()> 0)
		        payment.setCheckNo(((Integer)getCheckNo()).toString());
		    else payment.setCheckNo(((Integer)bankAccountDoc.getCurrentNext()).toString());
        }
		payment.saveEx();
		if (!payment.processIt("CO"))
			return "Payment could not be completed";
		payment.saveEx();
		if (isPrinted()){
			commitEx();
            ProcessInfo processInfo = getProcessInfo();
			MPaySelectionCheck paySelectionCheck =
                    MPaySelectionCheck.createForPayment(getCtx(), payment.getC_Payment_ID(), get_TrxName());
			commitEx();
            ServerReportCtl.startDocumentPrint(6,
                    (MPrintFormat) bankAccountDoc.getCheck_PrintFormat(),
                   paySelectionCheck.getC_PaySelectionCheck_ID(),null,processInfo );
            bankAccountDoc.setCurrentNext(getCheckNo()+1);
            bankAccountDoc.saveEx();
		}

		return "";
	}

	private String paymentRuleConvertTenderType(String paymentRule){
		String tenderType = "";
		if (X_C_Invoice.PAYMENTRULE_Cash.equals(paymentRule))
			tenderType = X_C_Payment.TENDERTYPE_Cash;
		else if (X_C_Invoice.PAYMENTRULE_Check.equals(paymentRule))
			tenderType = X_C_Payment.TENDERTYPE_Check;
		else if (X_C_Invoice.PAYMENTRULE_DirectDebit.equals(paymentRule))
			tenderType = X_C_Payment.TENDERTYPE_DirectDebit;
		else if (X_C_Invoice.PAYMENTRULE_CreditCard.equals(paymentRule))
			tenderType = X_C_Payment.TENDERTYPE_CreditCard;
		else if (X_C_Invoice.PAYMENTRULE_DirectDeposit.equals(paymentRule))
			tenderType = X_C_Payment.TENDERTYPE_DirectDeposit;
		return tenderType;
	}
}