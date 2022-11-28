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
 * Copyright (C) 2003-2014 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpya.com                                  *
 *****************************************************************************/
package org.spin.investment.loan.util;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import org.adempiere.exceptions.AdempiereException;
import org.spin.investment.model.MFMAgreement;
import org.spin.investment.model.MFMBatch;
import org.spin.investment.model.MFMFunctionalSetting;
import org.spin.investment.model.MFMTransaction;
import org.spin.investment.model.MFMTransactionType;
import org.spin.investment.util.AbstractFunctionalSetting;
import org.spin.investment.util.FinancialSetting;

/**
 * Loan Provision Calculation
 * ProvisionAmt = (CapitalAmt + CurrentInterestAmt + CurrentDunningAmt) * ProvisionRate
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 *      <li> FR [ 1847 ] Create Loan Provision
 *		@see https://github.com/adempiere/adempiere/issues/1847
 */
public class LoanProvisionProcess extends AbstractFunctionalSetting {

	public LoanProvisionProcess(MFMFunctionalSetting setting) {
		super(setting);
	}

	@Override
	public String run() {
		MFMAgreement agreement = (MFMAgreement) getParameter(FinancialSetting.AGREEMENT_PO);
		MFMBatch batch = (MFMBatch) getParameter(FinancialSetting.BATCH_PO);
		String trxName = (String) getParameter(FinancialSetting.PARAMETER_TRX_NAME);
		//	Nothing
		if(agreement == null
				|| batch == null) {
			return null;
		}
		
		MFMTransactionType provisionType = MFMTransactionType.getTransactionTypeFromType(getCtx(), MFMTransactionType.TYPE_LoanProvisionCalculated);
		//	Validate
		if(provisionType == null) {
			throw new AdempiereException("@FM_TransactionType_ID@ @NotFound@ " + MFMTransactionType.TYPE_LoanProvisionCalculated);
		}
		//	
		HashMap<String, Object> returnValues = LoanUtil.calculateLoanProvision(getCtx(), agreement.getFM_Agreement_ID(), 
				new Timestamp(System.currentTimeMillis()), trxName);
		//	Process it
		if(returnValues == null
				|| returnValues.isEmpty()) {
			return null;
		}
		//	Else
		List<AmortizationValue> amortizationList = (List<AmortizationValue>) returnValues.get("AMORTIZATION_LIST");
		if(amortizationList == null) {
			return null;
		}
		//	Iterate
		for (AmortizationValue row : amortizationList) {
			MFMTransaction transaction = batch.addTransaction(provisionType.getFM_TransactionType_ID(), row.getProvisionAmt());
			if(transaction != null) {
				transaction.set_ValueOfColumn("FM_Amortization_ID", row.getAmortizationId());
				transaction.saveEx();
			}
		}
		return null;
	}
}
