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
package org.spin.util;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.compiere.model.MCurrency;
import org.spin.model.MFMAccount;
import org.spin.model.MFMAgreement;
import org.spin.model.MFMAmortization;
import org.spin.model.MFMFunctionalSetting;
import org.spin.model.MFMProduct;

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
		
		//HashMap<String, Object> retValues = new HashMap<String, Object>();
		List<AmortizationValue> amortizationList = new ArrayList<AmortizationValue>();
		MFMProduct financialProduct= (MFMProduct)loan.getFM_Product();
		//int financialProductId = loan.getFM_Product_ID();
		
		List<MFMAccount> accounts = loan.getAccounts();
		
		for (MFMAccount account : accounts) {
			if (MFMAmortization.checkAccount(account))
				continue;
			
			MFMAmortization.deleteForAccount(account);
			
			MCurrency currency = (MCurrency)account.getC_Currency();
			BigDecimal capitalAmt = (BigDecimal) account.get_Value("CapitalAmt");
			int feesQty = ((BigDecimal)account.get_Value("FeesQty")).intValue();
			Timestamp startDate = (Timestamp) account.get_Value("StartDate");
			Timestamp endDate = (Timestamp) account.get_Value("EndDate");
			Timestamp payDate = (Timestamp) account.get_Value("PayDate");
			String paymentFrequency = account.get_ValueAsString("PaymentFrequency");
			
			amortizationList = (List) LoanUtil.calculateFrenchAmortization(financialProduct.getFM_Product_ID(), capitalAmt, 
															feesQty, startDate,
																endDate, payDate,
																	paymentFrequency, account.getCtx())
						.get("AMORTIZATION_LIST");
			
			
			
			//retValues.get("AMORTIZATION_LIST");
		
			for (AmortizationValue amortization : amortizationList) {
				//Create Amortization
				MFMAmortization.createAmortization(account.getCtx(), 
													amortization.getCapitalAmtFee().setScale(currency.getStdPrecision(), BigDecimal.ROUND_HALF_UP), 
													"", 
													amortization.getDueDate(), 
													amortization.getEndDate(), 
													account.getFM_Account_ID(), 
													amortization.getInterestAmtFee().setScale(currency.getStdPrecision(), BigDecimal.ROUND_HALF_UP), 
													amortization.getPeriodNo(), 
													amortization.getStartDate(), 
													amortization.getTaxAmtFee().setScale(currency.getStdPrecision(), BigDecimal.ROUND_HALF_UP), 
													account.get_TrxName());
			}
			
		}
		return null;
	}
}
