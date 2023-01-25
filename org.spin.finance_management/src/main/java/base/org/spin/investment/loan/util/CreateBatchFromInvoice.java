/******************************************************************************
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
 * Copyright (C) 2003-2016 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Carlos Parada www.erpya.com                                *
 *****************************************************************************/
package org.spin.investment.loan.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.adempiere.core.domains.models.I_FM_Batch;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MDocType;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MTax;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.spin.investment.model.MFMAccount;
import org.spin.investment.model.MFMAgreement;
import org.spin.investment.model.MFMAmortization;
import org.spin.investment.model.MFMBatch;
import org.spin.investment.model.MFMDunning;
import org.spin.investment.model.MFMFunctionalSetting;
import org.spin.investment.model.MFMProduct;
import org.spin.investment.model.MFMRate;
import org.spin.investment.model.MFMTransaction;
import org.spin.investment.model.MFMTransactionType;
import org.spin.investment.util.AbstractFunctionalSetting;
import org.spin.investment.util.FinancialSetting;

/**
 * Generate Batch from Invoce
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 *      <li> FR [ 1678 ] Add Fact Accounting for Agreement Batch
 *		@see https://github.com/adempiere/adempiere/issues/1678
 */
public class CreateBatchFromInvoice extends AbstractFunctionalSetting {

	public CreateBatchFromInvoice(MFMFunctionalSetting setting) {
		super(setting);
	}

	@Override
	public String run() {
		MInvoice invoice = (MInvoice) getParameter(FinancialSetting.PARAMETER_PO);
		if(invoice != null) {
			return generateBatch(invoice);
		}
		//	
		return null;
	}
	
	/**
	 *  Getnerate Batch from invoice
	 * @param invoice
	 * @return
	 */
	private String generateBatch(MInvoice invoice) {
		int financialAccountId = invoice.getFM_Account_ID();
    	if(financialAccountId <= 0) {
    		return null;
    	}
    	//	Get Reversal
    	if(invoice.getReversal_ID() != 0) {
    		List<Object> parameters = new ArrayList<Object>();
    		parameters.add(financialAccountId);
    		parameters.add(MFMBatch.DOCSTATUS_Completed);
    		parameters.add(invoice.getReversal_ID());
    		//	
    		MFMBatch previousBatch = new Query(invoice.getCtx(), I_FM_Batch.Table_Name, 
    						I_FM_Batch.COLUMNNAME_FM_Account_ID + "=? "
    						+ "AND " + I_FM_Batch.COLUMNNAME_DocStatus + "=? " 
    						+ "AND EXISTS(SELECT 1 "
    						+ "					FROM FM_Transaction t "
    						+ "					INNER JOIN C_InvoiceLine il ON(il.C_InvoiceLine_ID = t.C_InvoiceLine_ID) "
    						+ "					WHERE t.FM_Batch_ID = FM_Batch.FM_Batch_ID "
    						+ "					AND il.C_Invoice_ID = ?)", invoice.get_TrxName())
    					.setParameters(parameters)
    					.setOrderBy(I_FM_Batch.COLUMNNAME_DateDoc + " DESC")
    					.first();
    		//	Verify
    		if(previousBatch != null) {
    			previousBatch.processIt(MFMBatch.ACTION_Reverse_Accrual);
    			previousBatch.saveEx();
    		}
    		//	Return
    		return null;
    	}
    	//	Get Account
    	MFMAccount account = new MFMAccount(invoice.getCtx(), financialAccountId, invoice.get_TrxName());
    	setParameter(FinancialSetting.ACCOUNT_PO, account);
    	//	Get Agreement
    	MFMAgreement agreement = (MFMAgreement) account.getFM_Agreement();
    	//	Get Financial Product
    	MFMProduct financialProduct = MFMProduct.getById(getCtx(), agreement.getFM_Product_ID(), invoice.get_TrxName());
    	//	Create Batch
    	MFMBatch batch = createBatch(invoice.getDateInvoiced());
    	if(batch != null) {
    		MDocType documentType = MDocType.get(getCtx(), invoice.getC_DocType_ID());
    		BigDecimal multiplier = Env.ONE;
    		//	Change Multiplier
    		if(documentType.getDocBaseType().endsWith("C")) {
    			multiplier = multiplier.negate();
    		}
    		MFMTransactionType capitalType = MFMTransactionType.getTransactionTypeFromType(getCtx(), MFMTransactionType.TYPE_LoanCapitalInvoiced);
    		//	Validate
    		if(capitalType == null) {
    			throw new AdempiereException("@FM_TransactionType_ID@ @NotFound@ " + MFMTransactionType.TYPE_LoanCapitalInvoiced);
    		}
    		//	Get Interest Rate
    		int rateId = financialProduct.get_ValueAsInt("FM_Rate_ID");
    		int dunningRateId = financialProduct.get_ValueAsInt("DunningInterest_ID");
    		int dunningId = financialProduct.get_ValueAsInt("FM_Dunning_ID");
    		int interestChargeId = 0;
    		int dunningChargeId = 0;
    		//	
    		if(rateId != 0) {
    			MFMRate rate = MFMRate.getById(getCtx(), rateId);
    			interestChargeId = rate.getC_Charge_ID();
    		}
    		//	
    		if(dunningRateId != 0) {
    			MFMRate rate = MFMRate.getById(getCtx(), dunningRateId);
    			dunningChargeId = rate.getC_Charge_ID();
    		} else if(dunningId != 0) {
    			MFMDunning dunning = MFMDunning.getById(getCtx(), dunningId);
    			dunningChargeId = dunning.getChargeId();
    		}
    		MFMTransactionType interetType = MFMTransactionType.getTransactionTypeFromType(getCtx(), MFMTransactionType.TYPE_LoanInterestInvoiced);
    		MFMTransactionType interestTaxType = MFMTransactionType.getTransactionTypeFromType(getCtx(), MFMTransactionType.TYPE_LoanTaxAmountInvoiced);
    		//	Validate
    		if(interetType == null) {
    			throw new AdempiereException("@FM_TransactionType_ID@ @NotFound@ " + MFMTransactionType.TYPE_LoanInterestInvoiced);
    		}
    		MFMTransactionType dunningType = MFMTransactionType.getTransactionTypeFromType(getCtx(), MFMTransactionType.TYPE_LoanDunningInterestInvoiced);
    		MFMTransactionType dunningTaxType = MFMTransactionType.getTransactionTypeFromType(getCtx(), MFMTransactionType.TYPE_LoanDunningTaxAmountInvoiced);
    		//	Validate
    		if(dunningType == null) {
    			throw new AdempiereException("@FM_TransactionType_ID@ @NotFound@ " + MFMTransactionType.TYPE_LoanDunningInterestInvoiced);
    		}
    		//	Add Transactions
    		//	For Capital
    		for(MInvoiceLine line : invoice.getLines()) {
    			MFMTransaction transaction = null;
    			MFMTransaction transactionTax = null;
    			if(line.getM_Product_ID() != 0
    					&& line.getM_Product_ID() == financialProduct.getM_Product_ID()) {	//	Capital
    				transaction = batch.addTransaction(capitalType.getFM_TransactionType_ID(), line.getLineNetAmt().multiply(multiplier));
    			} else if(line.getC_Charge_ID() != 0
    					&& line.getC_Charge_ID() == interestChargeId) {	//	Interest
    				int amortizationId = line.getFM_Amortization_ID();
    				if(amortizationId > 0) {
    					MFMAmortization amortization = new MFMAmortization(getCtx(), amortizationId, invoice.get_TrxName());
    					if(amortization.getEndDate().after(invoice.getDateInvoiced())
    							&& amortization.getStartDate().getTime() <= invoice.getDateInvoiced().getTime()) {
    						BigDecimal currentInterest = LoanUtil.getCurrentLoanInterest(new AmortizationValue(amortization), invoice.getDateInvoiced());
    						if(currentInterest != null
    								&& !currentInterest.equals(Env.ZERO)) {
    							transaction = batch.addTransaction(interetType.getFM_TransactionType_ID(), currentInterest.multiply(multiplier));
    							MTax tax = (MTax) line.getC_Tax();
    							//	Calculate rate for fee (Year Interest + (Tax Rate * Year Interest))
    							BigDecimal taxRate = tax.getRate().divide(Env.ONEHUNDRED);
    							if(!taxRate.equals(Env.ZERO)) {
    								transactionTax = batch.addTransaction(interestTaxType.getFM_TransactionType_ID(), 
    										currentInterest.multiply(taxRate).multiply(multiplier));
    							}
    						}
    					} else if(amortization.getEndDate().getTime() <= invoice.getDateInvoiced().getTime()) {
    						transaction = batch.addTransaction(interetType.getFM_TransactionType_ID(), line.getLineNetAmt().multiply(multiplier));
            				transactionTax = batch.addTransaction(interestTaxType.getFM_TransactionType_ID(), line.getTaxAmt().multiply(multiplier));
    					}
    				} else {
    					transaction = batch.addTransaction(interetType.getFM_TransactionType_ID(), line.getLineNetAmt().multiply(multiplier));
        				transactionTax = batch.addTransaction(interestTaxType.getFM_TransactionType_ID(), line.getTaxAmt().multiply(multiplier));
    				}
    			} else if(line.getC_Charge_ID() != 0
    					&& line.getC_Charge_ID() == dunningChargeId) {	//	Dunning
    				transaction = batch.addTransaction(dunningType.getFM_TransactionType_ID(), line.getLineNetAmt().multiply(multiplier));
    				transactionTax = batch.addTransaction(dunningTaxType.getFM_TransactionType_ID(), line.getTaxAmt().multiply(multiplier));
    			}
    			//	Add reference
    			if(transaction != null) {
					if(line.getFM_Amortization_ID() != 0) {
						transaction.set_ValueOfColumn("FM_Amortization_ID", line.getFM_Amortization_ID());
						transaction.set_ValueOfColumn("C_InvoiceLine_ID", line.getC_InvoiceLine_ID());
    					transaction.saveEx();
					}
					if(transactionTax != null) {
						if(line.getFM_Amortization_ID() != 0) {
							transactionTax.set_ValueOfColumn("FM_Amortization_ID", line.getFM_Amortization_ID());
							transactionTax.set_ValueOfColumn("C_InvoiceLine_ID", line.getC_InvoiceLine_ID());
							transactionTax.saveEx();
						}
					}
				}
    		}
			//	Complete Batch
    		batch.processIt(MFMBatch.ACTION_Complete);
			batch.saveEx();
    	}
		return null;
	}
}
