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

import org.compiere.util.TimeUtil;
import org.spin.investment.model.MFMAmortization;

/**
 * Used for values on amortization
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 *      <li> FR [ 1583 ] New Definition for loan
 *		@see https://github.com/adempiere/adempiere/issues/1583
 */
public class AmortizationValue {

	public AmortizationValue() {
		
	}
	
	/**
	 * From saved amortization
	 * @param amortization
	 */
	public AmortizationValue(MFMAmortization amortization) {
		setPeriodNo(amortization.getPeriodNo());
		setStartDate(amortization.getStartDate());
		setEndDate(amortization.getEndDate());
		setDueDate(amortization.getDueDate());
		setCapitalAmtFee(amortization.getCapitalAmt());
		setTaxAmtFee(amortization.getTaxAmt());
		setInterestAmtFee(amortization.getInterestAmt());
		setAmortizationId(amortization.getFM_Amortization_ID());
		setPaid(amortization.isPaid());
	}
	
	/**	Period No	*/
	private int periodNo = 0;
	/**	Start Date	*/
	private Timestamp startDate;
	/**	End Date	*/
	private Timestamp endDate;
	/**	Due Date	*/
	private Timestamp dueDate;
	/**	Days of Month	*/
	private int dayOfMonth = 0;
	/**	Cumulative Days	*/
	private int cumulativeDays = 0;
	/**	Remaining Capital	*/
	private BigDecimal remainingCapital;
	/**	Month Interest	*/
	private BigDecimal monthInterest;
	/**	Cumulative Interest	*/
	private BigDecimal cumulativeInterest;
	/**	Daily Interest	*/
	private BigDecimal dailyInterest;
	/**	Interest Amount	*/
	private BigDecimal interestAmtFee;
	/**	Capital Amount	*/
	private BigDecimal capitalAmtFee;
	/**	Tax Amount	*/
	private BigDecimal taxAmtFee;
	/**	Daily Interest	*/
	private BigDecimal dailyInterestAmt;
	/**	Fee Amount	*/
	private BigDecimal fixedFeeAmt;
	/**	Amortization ID	*/
	private int amortizationId;
	/**	Days Due	*/
	private int daysDue;
	/**	Dunning daily interest	*/
	private BigDecimal dunningDailyInterest;
	/**	Dunning Interest Amount	*/
	private BigDecimal dunningInterestAmount;
	/**	Dunning Tax Rate	*/
	private BigDecimal dunningTaxRate;
	/**	Dunning Tax Amount	*/
	private BigDecimal dunningTaxAmt;
	/**	Is Paid	*/
	private boolean isPaid;
	/**	Provision Amount	*/
	private BigDecimal provisionAmt;
	
	public int getPeriodNo() {
		return periodNo;
	}
	public void setPeriodNo(int periodNo) {
		this.periodNo = periodNo;
	}
	public Timestamp getStartDate() {
		return startDate;
	}
	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}
	public Timestamp getEndDate() {
		return endDate;
	}
	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}
	public int getDayOfMonth() {
		if(dayOfMonth == 0
				&& getStartDate() != null
				&& getEndDate() != null) {
			dayOfMonth = TimeUtil.getDaysBetween(getStartDate(), getEndDate());
		}
		return dayOfMonth;
	}
	/**
	 * Get Day of month from current date
	 * @param now
	 * @return
	 */
	public int getDayOfMonth(Timestamp now) {
		if(getStartDate() != null
				&& now != null) {
			return TimeUtil.getDaysBetween(getStartDate(), now);
		}
		return 0;
	}
	public void setDayOfMonth(int dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
	}
	public int getCumulativeDays() {
		return cumulativeDays;
	}
	public void setCumulativeDays(int cumulativeDays) {
		this.cumulativeDays = cumulativeDays;
	}
	public BigDecimal getRemainingCapital() {
		return remainingCapital;
	}
	public void setRemainingCapital(BigDecimal remainingCapital) {
		this.remainingCapital = remainingCapital;
	}
	public BigDecimal getMonthInterest() {
		return monthInterest;
	}
	public void setMonthInterest(BigDecimal monthInterest) {
		this.monthInterest = monthInterest;
	}
	public BigDecimal getCumulativeInterest() {
		return cumulativeInterest;
	}
	public void setCumulativeInterest(BigDecimal cumulativeInterest) {
		this.cumulativeInterest = cumulativeInterest;
	}
	public BigDecimal getDailyInterest() {
		return dailyInterest;
	}
	public void setDailyInterest(BigDecimal dailyRateInterestAmt) {
		this.dailyInterest = dailyRateInterestAmt;
	}
	public BigDecimal getInterestAmtFee() {
		return interestAmtFee;
	}
	public void setInterestAmtFee(BigDecimal interestAmt) {
		this.interestAmtFee = interestAmt;
	}
	public BigDecimal getCapitalAmtFee() {
		return capitalAmtFee;
	}
	public void setCapitalAmtFee(BigDecimal capitalAmt) {
		this.capitalAmtFee = capitalAmt;
	}
	public BigDecimal getDailyInterestAmt() {
		return dailyInterestAmt;
	}
	public void setDailyInterestAmt(BigDecimal dailyInterestAmt) {
		this.dailyInterestAmt = dailyInterestAmt;
	}
	public BigDecimal getTaxAmtFee() {
		return taxAmtFee;
	}
	public void setTaxAmtFee(BigDecimal taxAmtFee) {
		this.taxAmtFee = taxAmtFee;
	}
	public BigDecimal getFixedFeeAmt() {
		return fixedFeeAmt;
	}
	public void setFixedFeeAmt(BigDecimal fixedFeeAmt) {
		this.fixedFeeAmt = fixedFeeAmt;
	}
	public Timestamp getDueDate() {
		return dueDate;
	}
	public void setDueDate(Timestamp dueDate) {
		this.dueDate = dueDate;
	}
	public int getAmortizationId() {
		return amortizationId;
	}
	public void setAmortizationId(int amortizationId) {
		this.amortizationId = amortizationId;
	}
	public int getDaysDue() {
		return daysDue;
	}
	public int getDaysDue(Timestamp now) {
		if(getDueDate() != null
				&& now != null) {
			daysDue = TimeUtil.getDaysBetween(getDueDate(), now);
		}
		return daysDue;
	}
	public void setDaysDue(int daysDue) {
		this.daysDue = daysDue;
	}
	public BigDecimal getDunningDailyInterest() {
		return dunningDailyInterest;
	}
	public void setDunningDailyInterest(BigDecimal dunningDailyInterest) {
		this.dunningDailyInterest = dunningDailyInterest;
	}
	public BigDecimal getDunningInterestAmount() {
		return dunningInterestAmount;
	}
	public void setDunningInterestAmount(BigDecimal dunningInterestAmount) {
		this.dunningInterestAmount = dunningInterestAmount;
	}
	public BigDecimal getDunningTaxRate() {
		return dunningTaxRate;
	}
	public void setDunningTaxRate(BigDecimal dunningTaxRate) {
		this.dunningTaxRate = dunningTaxRate;
	}
	public BigDecimal getDunningTaxAmt() {
		return dunningTaxAmt;
	}
	public void setDunningTaxAmt(BigDecimal dunningTaxAmt) {
		this.dunningTaxAmt = dunningTaxAmt;
	}
	public boolean isPaid() {
		return isPaid;
	}
	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}
	
	public BigDecimal getProvisionAmt() {
		return provisionAmt;
	}

	public void setProvisionAmt(BigDecimal provisionAmt) {
		this.provisionAmt = provisionAmt;
	}

	@Override
	public String toString() {
		return "AmortizationValue [periodNo=" + periodNo + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", dueDate=" + dueDate + ", dayOfMonth=" + dayOfMonth + ", cumulativeDays=" + cumulativeDays
				+ ", remainingCapital=" + remainingCapital + ", monthInterest=" + monthInterest
				+ ", cumulativeInterest=" + cumulativeInterest + ", dailyInterest=" + dailyInterest
				+ ", interestAmtFee=" + interestAmtFee + ", capitalAmtFee=" + capitalAmtFee + ", taxAmtFee="
				+ taxAmtFee + ", dailyInterestAmt=" + dailyInterestAmt + ", fixedFeeAmt=" + fixedFeeAmt
				+ ", amortizationId=" + amortizationId + ", daysDue=" + daysDue + ", dunningDailyInterest="
				+ dunningDailyInterest + ", dunningInteretAmount=" + dunningInterestAmount + ", dunningTaxRate="
				+ dunningTaxRate + ", dunningTaxAmt=" + dunningTaxAmt + "]";
	}
}