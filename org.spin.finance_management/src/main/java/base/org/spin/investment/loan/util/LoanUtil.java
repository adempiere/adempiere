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
import java.math.MathContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MCharge;
import org.compiere.model.MTax;
import org.compiere.model.MTaxCategory;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;
import org.compiere.util.Util;
import org.spin.investment.model.MFMAccount;
import org.spin.investment.model.MFMAgreement;
import org.spin.investment.model.MFMAmortization;
import org.spin.investment.model.MFMDunning;
import org.spin.investment.model.MFMDunningLevel;
import org.spin.investment.model.MFMProduct;
import org.spin.investment.model.MFMRate;

/**
 * Loan French Method
 * FixedFeeAmt = Loan [(Interest (1 + Interest)n) / ((1 + Interest)n – 1)]
 *
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 *      <li> FR [ 1588 ] Add support to Payment Period for Loan
 *		@see https://github.com/adempiere/adempiere/issues/1588
 */
public class LoanUtil {

	/**	It is hardcode and must be changed	*/
	private final static BigDecimal YEAR_DAY = new BigDecimal(360);
	/**	Logger			*/
	private static CLogger	log = CLogger.getCLogger(LoanUtil.class);
	
	/**
	 * Get End Date from Frequency and Start Date
	 * @param startDate
	 * @param paymentFrequency
	 * @param feesQty
	 * @return
	 */
	public static Timestamp getEndDateFromFrequency(Timestamp startDate, String paymentFrequency, int feesQty) {
		if(startDate == null
				|| paymentFrequency == null) {
			return null;
		}
		//	Add by frequency
		if(paymentFrequency.equals("D")) {	//	Daily
			return TimeUtil.addDays(startDate, feesQty);
		} else if(paymentFrequency.equals("W")) {	//	Weekly
			return TimeUtil.addDays(startDate, feesQty * 7);
		} else if(paymentFrequency.equals("T")) {	//	Twice Monthly
			return TimeUtil.addDays(startDate, feesQty * 15);
		} else if(paymentFrequency.equals("M")) {	//	Monthly
			return TimeUtil.addMonths(startDate, feesQty);
		} else if(paymentFrequency.equals("Q")) {	//	Quarterly
			return TimeUtil.addMonths(startDate, feesQty * 3);
		} else if(paymentFrequency.equals("S")) {	//	Semi-yearly
			return TimeUtil.addMonths(startDate, feesQty * 6);
		} else if(paymentFrequency.equals("Y")) {	//	Yearly
			return TimeUtil.addYears(startDate, feesQty);
		} else if(paymentFrequency.equals("F")) {	//	Single Fee
			return startDate;
		}
		//	
		return null;
	}
	
	/**
	 * Get Fees Quantity from Start and End Date
	 * @param startDate
	 * @param endDate
	 * @param paymentFrequency
	 * @return
	 */
	public static int getFeesQtyFromFrequency(Timestamp startDate, Timestamp endDate, String paymentFrequency) {
		if(startDate == null
				|| endDate == null
				|| paymentFrequency == null) {
			return 0;
		}
		//	Add by frequency
		if(paymentFrequency.equals("D")) {	//	Daily
			return TimeUtil.getDaysBetween(startDate, endDate);
		} else if(paymentFrequency.equals("W")) {	//	Weekly
			return TimeUtil.getDaysBetween(startDate, endDate) / 7;
		} else if(paymentFrequency.equals("T")) {	//	Twice Monthly
			return TimeUtil.getDaysBetween(startDate, endDate) / 15;
		} else if(paymentFrequency.equals("M")) {	//	Monthly
			return TimeUtil.getMonthsBetween(startDate, endDate);
		} else if(paymentFrequency.equals("Q")) {	//	Quarterly
			return TimeUtil.getMonthsBetween(startDate, endDate) / 3;
		} else if(paymentFrequency.equals("S")) {	//	Semi-yearly
			return TimeUtil.getMonthsBetween(startDate, endDate) / 6;
		} else if(paymentFrequency.equals("Y")) {	//	Yearly
			return TimeUtil.getYearsBetween(startDate, endDate);
		} else if(paymentFrequency.equals("F")) {	//	Single Fee
			return 1;
		}
		//	Default
		return 0;
	}
	
	/**
	 * Based on French
	 * @param financialProductId
	 * @param capitalAmt
	 * @param feesQty
	 * @param startDate
	 * @param endDate
	 * @param payDate
	 * @param paymentFrequency
	 * @param ctx
	 * @return
	 */
	public static HashMap<String, Object> calculateFrenchAmortization(int financialProductId, BigDecimal capitalAmt, 
																			int feesQty,Timestamp startDate,
																				Timestamp endDate, Timestamp payDate,
																					String paymentFrequency, Properties ctx, String transactionName){
		/**	Return Value */
		HashMap<String, Object> returnValues = new HashMap<String, Object>();
		
		if(startDate == null) {
			startDate = new Timestamp(System.currentTimeMillis());
		}
		if(payDate == null
				|| payDate.before(startDate)) {
			payDate = startDate;
		}
		//	Calculate it
		MFMProduct financialProduct = MFMProduct.getById(ctx, financialProductId, transactionName);
		//	Get Interest Rate
		int rateId = financialProduct.get_ValueAsInt("FM_Rate_ID");
		BigDecimal interestRate = Env.ZERO;
		BigDecimal interestRateAndTaxRate = Env.ZERO;
		BigDecimal taxRate = Env.ZERO;
		MCharge charge = null;
		if(rateId != 0) {
			MFMRate rate = MFMRate.getById(ctx, rateId);
			//	
			interestRate = rate.getValidRate(startDate);
			charge = MCharge.get(ctx, rate.getC_Charge_ID());
		}
		//	Validate Charge
		if(charge != null) {
			//	Get Tax Rate
			MTaxCategory taxCategory = (MTaxCategory) charge.getC_TaxCategory();
			MTax tax = taxCategory.getDefaultTax();
			//	Calculate rate for fee (Year Interest + (Tax Rate * Year Interest))
			interestRate = interestRate.divide(Env.ONEHUNDRED);
			taxRate = tax.getRate().divide(Env.ONEHUNDRED);
			//	Calculate
			interestRateAndTaxRate = interestRate.add(interestRate.multiply(taxRate));
		}
		//	Hash Map for Amortization
		List<AmortizationValue> amortizationList = new ArrayList<AmortizationValue>();
		//	Cumulative
		//	Current Date
		Timestamp currentDate = startDate;
		Timestamp currentDueDate = payDate;
		int cumulativeDays = 0;
		BigDecimal currentInterest = Env.ZERO;
		BigDecimal remainingCapital = capitalAmt;
		//	First Iteration for it
		for(int i = 0; i < feesQty; i++) {
			AmortizationValue row = new AmortizationValue();
			//	Period No
			row.setPeriodNo(i + 1);
			//	Start Date
			row.setStartDate(currentDate);
			//	End Date
			if(!paymentFrequency.equals("F")) {
				currentDate = LoanUtil.getEndDateFromFrequency(currentDate, paymentFrequency, 1);
			} else {
				currentDate = endDate;
			}
			row.setEndDate(currentDate);
			//	Due Date
			if(currentDueDate.before(row.getEndDate())) {
				currentDueDate = row.getEndDate();
			}
			row.setDueDate(currentDueDate);
			if(!paymentFrequency.equals("F")) {
				currentDueDate = LoanUtil.getEndDateFromFrequency(currentDate, paymentFrequency, 1);
			}
			//	Set Cumulative Days
			cumulativeDays += row.getDayOfMonth();
			row.setCumulativeDays(cumulativeDays);
			//	Calculate Monthly Interest
			BigDecimal monthlyInterest = calculateMonthlyInterest(row.getCumulativeDays(), interestRateAndTaxRate);
			BigDecimal dailyInterest = calculateDailyInterest(row.getDayOfMonth(), interestRateAndTaxRate);
			row.setMonthInterest(monthlyInterest);
			row.setDailyInterest(dailyInterest);
			currentInterest = currentInterest.add(row.getMonthInterest());
			row.setCumulativeInterest(currentInterest);
			//	Add to hash
			amortizationList.add(row);
		}
		//	Second Iteration for it (Calculate Remaining Capital, )
		//	Get Fee
		BigDecimal fixedFeeAmt = capitalAmt.divide(currentInterest, MathContext.DECIMAL128);
		BigDecimal summaryInterestAmt = Env.ZERO;
		BigDecimal summaryTaxAmt = Env.ZERO;
		for(int i = 0; i < amortizationList.size(); i++) {
			AmortizationValue row = amortizationList.get(i);
			//	Set Interest Amount Fee
			BigDecimal interestFeeAmt = row.getDailyInterest().multiply(remainingCapital);
			interestFeeAmt = interestFeeAmt.divide(Env.ONE.add(taxRate), MathContext.DECIMAL128);
			row.setInterestAmtFee(interestFeeAmt);
			//	Set Tax Fee Amount
			BigDecimal taxAmtFee = interestFeeAmt.multiply(taxRate);
			row.setTaxAmtFee(taxAmtFee);
			//	Set Capital Amount Fee
			row.setCapitalAmtFee(fixedFeeAmt.subtract(interestFeeAmt.add(taxAmtFee)));
			//	Set Remaining Capital
			remainingCapital = remainingCapital.subtract(row.getCapitalAmtFee());
			row.setRemainingCapital(remainingCapital);
			//	Set Daily Interest Amount
			row.setDailyInterestAmt(row.getInterestAmtFee().divide(new BigDecimal(row.getCumulativeDays()), MathContext.DECIMAL128));
			//	Set Fixed Fee
			row.setFixedFeeAmt(fixedFeeAmt);
			//	Set Object
			amortizationList.set(i, row);
			//	Summarize
			summaryInterestAmt = summaryInterestAmt.add(row.getInterestAmtFee());
			summaryTaxAmt = summaryTaxAmt.add(taxAmtFee);
		}
		
		returnValues.put("FIXED_FEE_AMT", fixedFeeAmt);
		returnValues.put("INTEREST_FEE_AMT", summaryInterestAmt);
		returnValues.put("TAX_FEE_AMT", summaryTaxAmt);
		returnValues.put("GRAND_TOTAL", summaryInterestAmt.add(summaryTaxAmt).add(capitalAmt));
		returnValues.put("AMORTIZATION_LIST", amortizationList);
		
		return returnValues;
	}
	
	/**
	 * Calculate Daily interest
	 * @param days
	 * @param interestRate
	 * @return
	 */
	public static BigDecimal calculateDailyInterest(int days, BigDecimal interestRate) {
		//	Calculate Daily Interest
		//				(A Variable)					(B Variable)
		//	((1 + InterestRate) ^ (MonthlyDays / YEAR_DAY)) - 1
		BigDecimal _A_Variable = (Env.ONE.add(interestRate));
		BigDecimal _B_Variable = (new BigDecimal(days).divide(YEAR_DAY, MathContext.DECIMAL128));
		BigDecimal _Result_A_pow_B = new BigDecimal(Math.pow(_A_Variable.doubleValue(), _B_Variable.doubleValue()));
		BigDecimal dailyInterest = _Result_A_pow_B.subtract(Env.ONE);
		return dailyInterest;
	}
	
	/**
	 * Calculate Monthly Interest
	 * @param cumulativeDays
	 * @param interestRate
	 * @return
	 */
	public static BigDecimal calculateMonthlyInterest(int cumulativeDays, BigDecimal interestRate) {
		//	Calculate Monthly Interest
		//				(A Variable)					(B Variable)
		//	1 / ((1 + InterestRateAndTaxRate ) ^ (CumulativeDays / YEAR_DAY))
		BigDecimal _A_Variable = (Env.ONE.add(interestRate));
		BigDecimal _B_Variable = (new BigDecimal(cumulativeDays).divide(LoanUtil.YEAR_DAY, MathContext.DECIMAL128));
		BigDecimal _Result_A_pow_B = new BigDecimal(Math.pow(_A_Variable.doubleValue(), _B_Variable.doubleValue()));
		BigDecimal monthInterest = Env.ONE.divide(_Result_A_pow_B, MathContext.DECIMAL128);
		return monthInterest;
	}
	
	/**
	 * Only dunning of loan
	 * @param ctx
	 * @param agreementId
	 * @param runningDate
	 * @param trxName
	 * @return
	 */
	public static HashMap<String, Object> calculateLoanDunning(Properties ctx, int agreementId, Timestamp runningDate, String trxName){
		//	Validate agreement
		if(agreementId <= 0) {
			return null;
		}
		/**	Return Value */
		HashMap<String, Object> returnValues = new HashMap<String, Object>();
		//	if null then is now
		if(runningDate == null) {
			runningDate = new Timestamp(System.currentTimeMillis());
		}
		//	Get agreement
		MFMAgreement agreement = MFMAgreement.getById(ctx, agreementId, trxName);
		//	Calculate it
		MFMProduct financialProduct = MFMProduct.getById(ctx, agreement.getFM_Product_ID(), trxName);
		//	Get Interest Rate
		int dunningRateId = financialProduct.get_ValueAsInt("DunningInterest_ID");
		int dunningId = financialProduct.get_ValueAsInt("FM_Dunning_ID");
		int graceDays = financialProduct.get_ValueAsInt("GraceDays");
		//	Validate Dunning for it
		if(dunningRateId == 0
				&& dunningId == 0) {
			return null;
		}
		//	
		BigDecimal interestRate = Env.ZERO;
		BigDecimal taxRate = Env.ZERO;
		MCharge charge = null;
		if(dunningRateId != 0) {
			MFMRate rate = MFMRate.getById(ctx, dunningRateId);
			//	
			interestRate = rate.getValidRate(runningDate);
			if(interestRate != null) {
				interestRate = interestRate.divide(Env.ONEHUNDRED);
			} else {
				interestRate = Env.ZERO;
			}
			charge = MCharge.get(ctx, rate.getC_Charge_ID());
		}
		//	Validate Charge
		if(charge != null) {
			//	Get Tax Rate
			MTaxCategory taxCategory = (MTaxCategory) charge.getC_TaxCategory();
			MTax tax = taxCategory.getDefaultTax();
			//	Calculate rate for fee (Year Interest + (Tax Rate * Year Interest))
			taxRate = tax.getRate();
			if(taxRate != null) {
				taxRate = taxRate.divide(Env.ONEHUNDRED);
			} else {
				taxRate = Env.ZERO;
			}
		}
		MFMDunning dunning = null;
		//	Get dunning configuration if exist
		if(dunningId > 0) {
			dunning = MFMDunning.getById(ctx, dunningId);
		}
		//	Get
		List<MFMAccount> accounts = MFMAccount.getAccountFromAgreement(agreement);
		MFMAccount account = null;
		if (accounts.isEmpty()){
			account = new MFMAccount(agreement);
			account.saveEx();
		} else {
			account = accounts.get(0);
		}
		//	Hash Map for Amortization
		List<AmortizationValue> amortizationList = new ArrayList<AmortizationValue>();
		//	
		for(MFMAmortization amortization : MFMAmortization.getOpenFromAccount(account.getFM_Account_ID(), trxName)) {
			if(amortization.isInvoiced()
					|| amortization.isPaid()) {
				continue;
			}
			AmortizationValue row = new AmortizationValue(amortization);
			if(row.getDaysDue(runningDate) - graceDays <= 0) {
				continue;
			}
			//	For distinct levels
			MFMDunningLevel level = null;
			int dunningLevelRateId = 0;
			if(dunning != null) {
				level = dunning.getValidLevelInstance(row.getDaysDue());
				if(level == null) {
					continue;
				}
				//	Apply for parent
				if(dunningRateId == 0) {
					dunningLevelRateId = level.getFM_Rate_ID();
					if(dunningLevelRateId > 0) {
						MFMRate rate = MFMRate.getById(ctx, dunningLevelRateId);
						//	
						interestRate = rate.getValidRate(runningDate);
						charge = MCharge.get(ctx, rate.getC_Charge_ID());
						//	Validate Charge
						if(charge != null) {
							//	Get Tax Rate
							MTaxCategory taxCategory = (MTaxCategory) charge.getC_TaxCategory();
							MTax tax = taxCategory.getDefaultTax();
							//	Calculate rate for fee (Year Interest + (Tax Rate * Year Interest))
							interestRate = interestRate.divide(Env.ONEHUNDRED);
							taxRate = tax.getRate().divide(Env.ONEHUNDRED);
						}
					}
				}
				//	Validate Rate
				if(interestRate == null
						|| interestRate.doubleValue() == 0
						|| taxRate == null) {
					continue;
				}
			}
			//	
			BigDecimal dailyInterest = calculateDailyInterest(row.getDaysDue(), interestRate);
			if(dailyInterest != null) {
				row.setDunningDailyInterest(dailyInterest);
				BigDecimal capitalAmt = row.getCapitalAmtFee();
				BigDecimal dunningInteretAmount = capitalAmt.multiply(dailyInterest);
				row.setDunningInterestAmount(dunningInteretAmount);
				//	For Tax
				if(taxRate != null) {
					row.setDunningTaxRate(taxRate);
					row.setDunningTaxAmt(dunningInteretAmount.multiply(taxRate));
				}
			}
			//	Add to list
			amortizationList.add(row);
		}
		//	Add list
		returnValues.put("AMORTIZATION_LIST", amortizationList);
		return returnValues;
	}
	
	/**
	 * Only dunning of loan
	 * @param ctx
	 * @param agreementId
	 * @param runningDate
	 * @param trxName
	 * @return
	 */
	public static HashMap<String, Object> calculateLoanProvision(Properties ctx, int agreementId, Timestamp runningDate, String trxName){
		//	Validate agreement
		if(agreementId <= 0) {
			return null;
		}
		/**	Return Value */
		HashMap<String, Object> returnValues = new HashMap<String, Object>();
		//	if null then is now
		if(runningDate == null) {
			runningDate = new Timestamp(System.currentTimeMillis());
		}
		//	Get agreement
		MFMAgreement agreement = MFMAgreement.getById(ctx, agreementId, trxName);
		//	Calculate it
		MFMProduct financialProduct = MFMProduct.getById(ctx, agreement.getFM_Product_ID(), trxName);
		//	Get Interest Rate
		int dunningId = financialProduct.get_ValueAsInt("FM_Dunning_ID");
		//	Validate Dunning for it
		if(dunningId == 0) {
			return null;
		}
		//	
		MFMDunning dunning = null;
		//	Get dunning configuration if exist
		if(dunningId > 0) {
			dunning = MFMDunning.getById(ctx, dunningId);
		}
		//	Get
		List<MFMAccount> accounts = MFMAccount.getAccountFromAgreement(agreement);
		MFMAccount account = null;
		if (accounts.isEmpty()){
			account = new MFMAccount(agreement);
			account.saveEx();
		} else {
			account = accounts.get(0);
		}
		//	Hash Map for Amortization
		List<AmortizationValue> amortizationList = new ArrayList<AmortizationValue>();
		//	
		for(AmortizationValue row : getCurrentAmortizationList(ctx, agreementId, trxName)) {
			if(row.isPaid()) {
				continue;
			}
			if(row.getDaysDue(runningDate) <= 0) {
				continue;
			}
			//	For distinct levels
			MFMDunningLevel level = null;
			if(dunning != null) {
				level = dunning.getValidLevelInstance(row.getDaysDue());
				if(level == null
						|| !level.isAccrual()) {
					continue;
				}
				//	Get Provision Rate
				BigDecimal provisionRate = level.getProvisionPercentage();
				if(provisionRate == null
						|| provisionRate.doubleValue() == 0) {
					continue;
				}
				provisionRate = provisionRate.divide(Env.ONEHUNDRED);
				//	Set Capital
				BigDecimal capitalAmount = row.getCapitalAmtFee();
				//	Set Interest
				BigDecimal interestAmount = row.getInterestAmtFee();
				//	Set Dunning
				BigDecimal dunningInterestAmount = row.getDunningInterestAmount();
				//	Set Provision
				BigDecimal provisionAmount = (capitalAmount.add(interestAmount).add(dunningInterestAmount)).multiply(provisionRate);
				row.setProvisionAmt(provisionAmount);
				amortizationList.add(row);
			}
		}
		//	Add list
		returnValues.put("AMORTIZATION_LIST", amortizationList);
		return returnValues;
	}
	
	/**
	 * Only dunning of loan
	 * @param ctx
	 * @param agreementId
	 * @param runningDate
	 * @param trxName
	 * @return
	 */
	public static HashMap<String, Object> calculateLoanSuspension(Properties ctx, int agreementId, Timestamp runningDate, String trxName){
		//	Validate agreement
		if(agreementId <= 0) {
			return null;
		}
		/**	Return Value */
		HashMap<String, Object> returnValues = new HashMap<String, Object>();
		//	if null then is now
		if(runningDate == null) {
			runningDate = new Timestamp(System.currentTimeMillis());
		}
		//	Get agreement
		MFMAgreement agreement = MFMAgreement.getById(ctx, agreementId, trxName);
		//	Calculate it
		MFMProduct financialProduct = MFMProduct.getById(ctx, agreement.getFM_Product_ID(), trxName);
		//	Get Interest Rate
		int dunningId = financialProduct.get_ValueAsInt("FM_Dunning_ID");
		//	Validate Dunning for it
		if(dunningId == 0) {
			return null;
		}
		//	
		MFMDunning dunning = null;
		//	Get dunning configuration if exist
		if(dunningId > 0) {
			dunning = MFMDunning.getById(ctx, dunningId);
		}
		//	Get
		List<MFMAccount> accounts = MFMAccount.getAccountFromAgreement(agreement);
		MFMAccount account = null;
		if (accounts.isEmpty()){
			account = new MFMAccount(agreement);
			account.saveEx();
		} else {
			account = accounts.get(0);
		}
		//	Hash Map for Amortization
		List<AmortizationValue> amortizationList = new ArrayList<AmortizationValue>();
		//	
		for(AmortizationValue row : getCurrentAmortizationList(ctx, agreementId, trxName)) {
			if(row.isPaid()) {
				continue;
			}
			if(row.getDaysDue(runningDate) <= 0) {
				continue;
			}
			//	For distinct levels
			MFMDunningLevel level = null;
			if(dunning != null) {
				level = dunning.getValidLevelInstance(row.getDaysDue(), false, true);
				if(level == null
						|| !level.isSuspend()) {
					continue;
				}
				//	Get Provision Rate
				amortizationList.add(row);
			}
		}
		//	Add list
		returnValues.put("AMORTIZATION_LIST", amortizationList);
		return returnValues;
	}
	
	
	/**
	 * Only Interest of loan
	 * @param ctx
	 * @param agreementId
	 * @param runningDate
	 * @param trxName
	 * @return
	 */
	public static HashMap<String, Object> calculateLoanInterest(Properties ctx, int agreementId, Timestamp runningDate, String trxName){
		//	Validate agreement
		if(agreementId <= 0) {
			return null;
		}
		/**	Return Value */
		HashMap<String, Object> returnValues = new HashMap<String, Object>();
		//	if null then is now
		if(runningDate == null) {
			runningDate = new Timestamp(System.currentTimeMillis());
		}
		//	Get agreement
		MFMAgreement agreement = MFMAgreement.getById(ctx, agreementId, trxName);
		//	Calculate it
		MFMProduct financialProduct = MFMProduct.getById(ctx, agreement.getFM_Product_ID(), trxName);
		//	Get Interest Rate
		int rateId = financialProduct.get_ValueAsInt("FM_Rate_ID");
		//	Validate Dunning for it
		if(rateId == 0) {
			return null;
		}
		//	
		BigDecimal interestRate = Env.ZERO;
		BigDecimal taxRate = Env.ZERO;
		MCharge charge = null;
		if(rateId != 0) {
			MFMRate rate = MFMRate.getById(ctx, rateId);
			//	
			interestRate = rate.getValidRate(agreement.getDateDoc());
			charge = MCharge.get(ctx, rate.getC_Charge_ID());
		}
		//	Validate Charge
		if(charge != null) {
			//	Get Tax Rate
			MTaxCategory taxCategory = (MTaxCategory) charge.getC_TaxCategory();
			MTax tax = taxCategory.getDefaultTax();
			//	Calculate rate for fee (Year Interest + (Tax Rate * Year Interest))
			interestRate = interestRate.divide(Env.ONEHUNDRED);
			taxRate = tax.getRate().divide(Env.ONEHUNDRED);
			//	Calculate
			interestRate = interestRate.add(interestRate.multiply(taxRate));
		}
		//	Validate Zero
		if(interestRate.equals(Env.ZERO)) {
			return returnValues;
		}
		//	Get
		List<MFMAccount> accounts = MFMAccount.getAccountFromAgreement(agreement);
		MFMAccount account = null;
		if (accounts.isEmpty()){
			account = new MFMAccount(agreement);
			account.saveEx();
		} else {
			account = accounts.get(0);
		}
		//	Hash Map for Amortization
		List<AmortizationValue> amortizationList = new ArrayList<AmortizationValue>();
		//	
		for(MFMAmortization amortization : MFMAmortization.getOpenFromAccount(account.getFM_Account_ID(), trxName)) {
			if(amortization.isInvoiced()
					|| amortization.isPaid()) {
				continue;
			}
			AmortizationValue row = new AmortizationValue(amortization);
			Timestamp calculationDate = runningDate;
			//	Validate after
			if(calculationDate.before(row.getStartDate())) {
				continue;
			}
			//	
			if(row.getEndDate().before(calculationDate)) {
				calculationDate = row.getEndDate();
			}
			//	
			int daysOfMonth = row.getDayOfMonth();
			int daysToNow = row.getDayOfMonth(runningDate);
			if(daysOfMonth == 0) {
				continue;
			}
			//	For back months
			if(daysToNow > daysOfMonth) {
				daysToNow = daysOfMonth;
			}
			BigDecimal interest = getCurrentLoanInterest(row, runningDate);
			if(interest != null
					&& !interest.equals(Env.ZERO)) {
				//	For Tax
				if(taxRate != null) {
					row.setTaxAmtFee(row.getInterestAmtFee().multiply(taxRate));
				}
			}
			//	Add to list
			amortizationList.add(row);
		}
		//	Add list
		returnValues.put("AMORTIZATION_LIST", amortizationList);
		return returnValues;
	}
	
	/**
	 * Get Current Amortization Values from View
	 * @param ctx
	 * @param agreementId
	 * @param trxName
	 * @return
	 */
	private static List<AmortizationValue> getCurrentAmortizationList(Properties ctx, int agreementId, String trxName) {
		//	Hash Map for Amortization
		List<AmortizationValue> amortizationList = new ArrayList<AmortizationValue>();
		String sql = new String("SELECT * FROM RV_FM_LoanAmortization WHERE FM_Agreement_ID = ?");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, agreementId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ResultSetMetaData rsmd = rs.getMetaData();
				AmortizationValue row = new AmortizationValue();
				for (int index = 1; index <= rsmd.getColumnCount(); index++) {
					String columnName = rsmd.getColumnName (index);
					if (columnName.equalsIgnoreCase("FM_Amortization_ID")) {
						row.setAmortizationId(rs.getInt(index));
					} else if(columnName.equalsIgnoreCase("PeriodNo")) {
						row.setPeriodNo(rs.getInt(index));
					} else if(columnName.equalsIgnoreCase("CapitalAmt")) {
						row.setCapitalAmtFee(rs.getBigDecimal(index));
					} else if(columnName.equalsIgnoreCase("CurrentInterestAmt")) {
						row.setInterestAmtFee(rs.getBigDecimal(index));
					} else if(columnName.equalsIgnoreCase("CurrentDunningAmt")) {
						row.setDunningInterestAmount(rs.getBigDecimal(index));
					} else if(columnName.equalsIgnoreCase("StartDate")) {
						row.setStartDate(rs.getTimestamp(index));
					} else if(columnName.equalsIgnoreCase("EndDate")) {
						row.setEndDate(rs.getTimestamp(index));
					} else if(columnName.equalsIgnoreCase("DueDate")) {
						row.setDueDate(rs.getTimestamp(index));
					} else if(columnName.equalsIgnoreCase("IsPaid")) {
						String booleanValue = rs.getString(index);
						row.setPaid(!Util.isEmpty(booleanValue) && booleanValue.equals("Y")? true: false);
					}
				}
				amortizationList.add(row);
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		//	Default
		return amortizationList;
	}
	
	/**
	 * Get daily Interest
	 * @param row
	 * @param runningDate
	 * @return
	 */
	public static BigDecimal getDailyLoanInterest(AmortizationValue row, Timestamp runningDate) {
		Timestamp calculationDate = runningDate;
		//	Validate after
		if(calculationDate.before(row.getStartDate())) {
			return null;
		}
		//	
		if(row.getEndDate().before(calculationDate)) {
			calculationDate = row.getEndDate();
		}
		//	
		int daysOfMonth = row.getDayOfMonth();
		int daysToNow = row.getDayOfMonth(runningDate);
		if(daysOfMonth == 0) {
			return null;
		}
		//	For back months
		if(daysToNow > daysOfMonth) {
			daysToNow = daysOfMonth;
		}
		BigDecimal dailyInterest = row.getInterestAmtFee().divide(new BigDecimal(daysOfMonth), MathContext.DECIMAL128);
		if(dailyInterest != null
				&& !dailyInterest.equals(Env.ZERO)) {
			row.setDailyInterest(dailyInterest);
		}
		//	Return
		return dailyInterest;
	}
	
	/**
	 * Get Current Loan Interest
	 * @param amortizationRow
	 * @param runningDate
	 * @return
	 */
	public static BigDecimal getCurrentLoanInterest(AmortizationValue amortizationRow, Timestamp runningDate) {
		BigDecimal dailyInterest = getDailyLoanInterest(amortizationRow, runningDate);
		if(dailyInterest != null
				&& !dailyInterest.equals(Env.ZERO)) {
			int daysOfMonth = amortizationRow.getDayOfMonth();
			int daysToNow = amortizationRow.getDayOfMonth(runningDate);
			if(daysToNow > daysOfMonth) {
				daysToNow = daysOfMonth;
			}
			amortizationRow.setInterestAmtFee(dailyInterest.multiply(new BigDecimal(daysToNow)));
		}
		//	Return
		return amortizationRow.getInterestAmtFee();
	}
}