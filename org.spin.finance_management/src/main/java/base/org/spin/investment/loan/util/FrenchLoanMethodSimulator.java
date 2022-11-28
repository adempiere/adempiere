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

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;

import org.spin.investment.model.MFMFunctionalSetting;
import org.spin.investment.util.AbstractFunctionalSetting;
import org.spin.investment.util.FinancialSetting;

/**
 * Loan French Method
 * FixedFeeAmt = Loan [(Interest (1 + Interest)n) / ((1 + Interest)n – 1)]
 *
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 *      <li> FR [ 1583 ] New Definition for loan
 *		@see https://github.com/adempiere/adempiere/issues/1583
 */
public class FrenchLoanMethodSimulator extends AbstractFunctionalSetting {

	public FrenchLoanMethodSimulator(MFMFunctionalSetting setting) {
		super(setting);
	}
	
	@Override
	public String run() {
		int financialProductId = getParameterAsInt("FINANCIAL_PRODUCT_ID");
		BigDecimal capitalAmt = getParameterAsBigDecimal("CAPITAL_AMT");
		int feesQty = getParameterAsInt("FEES_QTY");
		Timestamp startDate = (Timestamp) getParameter("START_DATE");
		Timestamp endDate = (Timestamp) getParameter("END_DATE");
		Timestamp payDate = (Timestamp) getParameter("PAYMENT_DATE");
		String paymentFrequency = (String) getParameter("PAYMENT_FREQUENCY");
		String transactionName = (String) getParameter(FinancialSetting.PARAMETER_TRX_NAME);
//		boolean isDueFixed = (boolean) getParameter("DUE_FIXED");
		
		HashMap<String, Object> returnValues = new HashMap<String, Object>();
		returnValues = LoanUtil.calculateFrenchAmortization(financialProductId, capitalAmt, 
																	feesQty, startDate, 
																		endDate, payDate, 
																			paymentFrequency, getCtx(), transactionName);
		//	Put Return Values
		setReturnValue("FIXED_FEE_AMT", returnValues.get("FIXED_FEE_AMT"));
		setReturnValue("INTEREST_FEE_AMT", returnValues.get("INTEREST_FEE_AMT"));
		setReturnValue("TAX_FEE_AMT", returnValues.get("TAX_FEE_AMT"));
		setReturnValue("GRAND_TOTAL", returnValues.get("GRAND_TOTAL"));
		setReturnValue("AMORTIZATION_LIST", returnValues.get("AMORTIZATION_LIST"));
		return null;
	}	
}
