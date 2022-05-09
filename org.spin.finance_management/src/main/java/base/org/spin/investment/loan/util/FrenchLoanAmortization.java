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
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.compiere.model.MCurrency;
import org.compiere.util.Env;
import org.spin.investment.model.MFMAccount;
import org.spin.investment.model.MFMAgreement;
import org.spin.investment.model.MFMAmortization;
import org.spin.investment.model.MFMFunctionalSetting;
import org.spin.investment.model.MFMProduct;
import org.spin.investment.util.AbstractFunctionalSetting;
import org.spin.investment.util.FinancialSetting;

/**
 * Financial Management
 *
 * @author Carlos Parada, cparada@erpya.com , http://www.erpya.com
 *      <li> FR [ 1586 ] Generate Amortization
 *		@see https://github.com/adempiere/adempiere/issues/1586
 */
public class FrenchLoanAmortization extends AbstractFunctionalSetting {
	
	public FrenchLoanAmortization(MFMFunctionalSetting setting) {
		super(setting);
	}

	@Override
	public String run() {
		MFMAgreement loan = (MFMAgreement) getParameter(FinancialSetting.PARAMETER_PO);
		return generateAmortization(loan);
	}
	
	//Generate Loan Amortization 
	private String generateAmortization(MFMAgreement loan){
		String transactionName = (String) getParameter(FinancialSetting.PARAMETER_TRX_NAME);
		List<AmortizationValue> amortizationList = new ArrayList<AmortizationValue>();
		MFMProduct financialProduct = MFMProduct.getById(getCtx(), loan.getFM_Product_ID(), transactionName);
		loan.set_TrxName(transactionName);
		List<MFMAccount> accounts = loan.getAccounts();
		
		for (MFMAccount account : accounts) {
			account.set_TrxName(transactionName);
			if (MFMAmortization.checkAccount(account)) {
				continue;
			}
			
			MFMAmortization.deleteForAccount(account);
			
			MCurrency currency = MCurrency.get(getCtx(), account.getC_Currency_ID());
			BigDecimal capitalAmt = (BigDecimal) account.get_Value("CapitalAmt");
			int feesQty = Optional.ofNullable(((BigDecimal)account.get_Value("FeesQty"))).orElse(Env.ZERO).intValue();
			Timestamp startDate = (Timestamp) account.get_Value("StartDate");
			Timestamp endDate = (Timestamp) account.get_Value("EndDate");
			Timestamp payDate = (Timestamp) account.get_Value("PayDate");
			String paymentFrequency = account.get_ValueAsString("PaymentFrequency");
			
			amortizationList = (List) LoanUtil.calculateFrenchAmortization(financialProduct.getFM_Product_ID(), capitalAmt, 
															feesQty, startDate,
																endDate, payDate,
																	paymentFrequency, account.getCtx(), transactionName)
						.get("AMORTIZATION_LIST");
		
			for (AmortizationValue amortization : amortizationList) {
				//Create Amortization
				MFMAmortization.createAmortization(account.getCtx(), 
													amortization.getCapitalAmtFee().setScale(currency.getStdPrecision(), RoundingMode.HALF_UP), 
													"", 
													amortization.getDueDate(), 
													amortization.getEndDate(), 
													account.getFM_Account_ID(), 
													amortization.getInterestAmtFee().setScale(currency.getStdPrecision(), RoundingMode.HALF_UP), 
													amortization.getPeriodNo(), 
													amortization.getStartDate(), 
													amortization.getTaxAmtFee().setScale(currency.getStdPrecision(), RoundingMode.HALF_UP), 
													transactionName);
			}
			
		}
		return null;
	}
}
