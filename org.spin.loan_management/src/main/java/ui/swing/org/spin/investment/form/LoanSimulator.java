/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.									  *
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
package org.spin.investment.form;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.apps.IStatusBar;
import org.compiere.minigrid.IMiniTable;
import org.compiere.model.MCurrency;
import org.compiere.model.MDocType;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Util;
import org.spin.investment.loan.util.AmortizationValue;
import org.spin.investment.loan.util.LoanUtil;
import org.spin.investment.model.MFMAccount;
import org.spin.investment.model.MFMAgreement;
import org.spin.investment.model.MFMFunctionalApplicability;
import org.spin.investment.model.MFMProduct;
import org.spin.investment.model.X_FM_Agreement;
import org.spin.investment.util.FinancialSetting;

/**
 * Financial Management
 *
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 *      <li> FR [ 1585 ] Loan Simulator
 *		@see https://github.com/adempiere/adempiere/issues/1585
 */
public abstract class LoanSimulator {

	/**	Window No			*/
	public int         	windowNo = 0;
	/**	Business Partner	*/
	public int 			businessPartnerId = 0;
	/**	Financial Product	*/
	public int 			financialProductId = 0;
	/**	Currency			*/
	public int 			currencyId = 0;
	/**	User/Contact		*/
	public int 			userId = 0;
	/**	Capital Amount		*/
	public BigDecimal	capitalAmt;
	/**	Fees Amount		*/
	public int			feesQty;
	/**	Financial Rate	*/
	public BigDecimal	financialRate;
	/**	Interest Amount		*/
	public BigDecimal	interestAmt;
	/**	Tax Amount		*/
	public BigDecimal	taxAmt;	
	/**	Grand Total		*/
	public BigDecimal	grandTotalAmt;
	/**	Start Date		*/
	public Timestamp	startDate;
	/**	End Date		*/
	public Timestamp	endDate;
	/**	Payment Date	*/
	public Timestamp	payDate;
	/**	Payment Frequency*/
	public String		paymentFrequency;
	/**	Due Fixed		*/
	public boolean 		isDueFixed = false;
	//	Round
	private int currencyPrecision = MCurrency.getStdPrecision(Env.getCtx(), Env.getContextAsInt(Env.getCtx(), "#C_Currency_ID"));
	/**	Logger			*/
	public static CLogger log = CLogger.getCLogger(LoanSimulator.class);
	
	/**
	 * Get Precision
	 * @return
	 */
	public int getStdPrecision() {
		return currencyPrecision;
	}
	
	/**
	 *  Dynamic Layout (Grid).
	 */
	public void dynInit(IStatusBar statusBar) {
		statusBar.setStatusLine(" ", false);
		statusBar.setStatusDB(" ");
	}   //  dynInit
	
	/**************************************************************************
	 *  Refresh - Create Query and refresh grid
	 */
	public void refresh(IStatusBar statusBar) {
		statusBar.setStatusLine(" ", false);
	}   //  refresh
	
	/**
	 * Save Data and generate Loan
	 * @param trxName
	 * @return
	 */
	public String saveData(String trxName) {
		String msg = null;
		MFMAgreement agreement = new MFMAgreement(Env.getCtx(), 0, trxName);
		agreement.setC_BPartner_ID(businessPartnerId);
		agreement.setFM_Product_ID(financialProductId);
		if(userId != 0) {
			agreement.set_ValueOfColumn("AD_User_ID", userId);
		}
		agreement.setC_DocType_ID(MDocType.DOCBASETYPE_FinancialAgreement);
		agreement.setFM_AgreementType_ID();
		agreement.setDescription(Msg.parseTranslation(Env.getCtx(), "@Generate@ @from@ @Simulation@"));
		agreement.setValidFrom(startDate);
		agreement.saveEx();
		//	Get Account
		List<MFMAccount> accounts = agreement.getAccounts();
		if(accounts == null
				|| accounts.size() == 0) {
			throw new AdempiereException("@FM_Account_ID@ @NotFound@");
		}
		//	Set Acount
		MFMAccount account = accounts.get(0);
		account.set_ValueOfColumn("CapitalAmt", capitalAmt);
		account.setC_Currency_ID(currencyId);
		account.set_ValueOfColumn("FeesQty", new BigDecimal(feesQty));
		account.set_ValueOfColumn("StartDate", startDate);
		account.set_ValueOfColumn("EndDate", endDate);
		account.set_ValueOfColumn("PayDate", payDate);
		account.set_ValueOfColumn("PaymentFrequency", paymentFrequency);
		account.saveEx();
		//	Complete
		agreement.setDocAction(X_FM_Agreement.DOCACTION_Complete);
		agreement.processIt(X_FM_Agreement.DOCACTION_Complete);
		agreement.saveEx();
		//	Validate Action
		if(agreement.getDocStatus().equals(X_FM_Agreement.DOCACTION_Complete)
				&& !Util.isEmpty(agreement.get_ValueAsString("LastResult"))) {
			msg = "@Error@: @FM_Agreement_ID@ @NotApproved@ [" + agreement.get_ValueAsString("LastResult") + "]";
		} else {
			msg = "@FM_Agreement_ID@ @Generate@ [" + agreement.getDocumentInfo() + "]";
		}
		return Msg.parseTranslation(Env.getCtx(), msg);
	}
	
	/**
	 * Get End Date from Frequency and Start Date
	 * @return
	 */
	public Timestamp getEndDateFromFrequency() {
		return LoanUtil.getEndDateFromFrequency(startDate, paymentFrequency, feesQty);
	}
	
	/**
	 * Get Fees Quantity from Start and End Date
	 * @return
	 */
	public int getFeesQtyFromFrequency() {
		return LoanUtil.getFeesQtyFromFrequency(startDate, endDate, paymentFrequency);
	}
	
	/**
	 * Simulate Data
	 * @param trxName
	 * @return
	 */
	public String simulateData(String trxName) {
		HashMap<String, Object> parameters = new HashMap<String, Object>();
		//	Add Parameters
		parameters.put("FINANCIAL_PRODUCT_ID", financialProductId);
		parameters.put("BUSINESS_PARTNER_ID", businessPartnerId);
		parameters.put("CAPITAL_AMT", capitalAmt);
		parameters.put("FEES_QTY", feesQty);
		parameters.put("START_DATE", startDate);
		parameters.put("END_DATE", endDate);
		parameters.put("PAYMENT_FREQUENCY", paymentFrequency);
		parameters.put("PAYMENT_DATE", payDate);
		parameters.put("DUE_FIXED", isDueFixed);
		//	
		FinancialSetting setting = FinancialSetting.get();
		//	Set Values
		String errorMsg = setting.fire(Env.getCtx(), financialProductId, MFMFunctionalApplicability.EVENTTYPE_Simulation, parameters, trxName);
		//	
		HashMap<String, Object> returnValues = setting.getReturnValues();
		//	
		BigDecimal interestFeeAmt = (BigDecimal) returnValues.get("INTEREST_FEE_AMT");
		BigDecimal taxAmt = (BigDecimal) returnValues.get("TAX_FEE_AMT");
		BigDecimal grandTotal = (BigDecimal) returnValues.get("GRAND_TOTAL");
		//	
		List<AmortizationValue> amortizationList = (List<AmortizationValue>) returnValues.get("AMORTIZATION_LIST");
		if(interestFeeAmt != null) {
			setInterestFeeAmt(interestFeeAmt.setScale(currencyPrecision, RoundingMode.HALF_UP));
		}
		if(taxAmt != null) {
			setTaxAmt(taxAmt.setScale(currencyPrecision, RoundingMode.HALF_UP));
		}
		if(grandTotal != null) {
			setGrandToral(grandTotal.setScale(currencyPrecision, RoundingMode.HALF_UP));
		}
		//	Reload table
		reloadAmortization(amortizationList);
		//	Set Error
		return Msg.parseTranslation(Env.getCtx(), errorMsg);
	}
	
	/**
	 * Clear controller values
	 */
	public void clearValues() {
		businessPartnerId = 0;
		financialProductId = 0;
		currencyId = 0;
		userId = 0;
		capitalAmt = Env.ZERO;
		feesQty = 0;
		financialRate = Env.ZERO;
		interestAmt = Env.ZERO;
		taxAmt = Env.ZERO;
		startDate = null;
		endDate = null;
		payDate = null;
		paymentFrequency = null;
	}
	
	/**
	 * Validate all data for run
	 * @return
	 */
	public String validateData() {
		StringBuffer message = new StringBuffer();
		//	Business Partner
		if(businessPartnerId == 0) {
			message.append("@C_BPartner_ID@ @NotFound@");
		}
		//	Financial Product
		if(financialProductId == 0) {
			if(message.length() > 0) {
				message.append(Env.NL);
			}
			message.append("@FM_Product_ID@ @NotFound@");
		}
		//	Capital Amount
		if(capitalAmt == null
				|| capitalAmt.compareTo(Env.ZERO) <= 0) {
			if(message.length() > 0) {
				message.append(Env.NL);
			}
			message.append("@CapitalAmt@ <= @0@");
		}
		//	Fees Amount
		if(feesQty <= 0) {
			if(message.length() > 0) {
				message.append(Env.NL);
			}
			message.append("@FeesQty@ <= @0@");
		}
		//	Start Date
		if(startDate == null) {
			if(message.length() > 0) {
				message.append(Env.NL);
			}
			message.append("@StartDate@ @NotFound@");
		}
		//	Validate Max and Min capital
		if(financialProductId != 0) {
			MFMProduct financialProduct = MFMProduct.getById(Env.getCtx(), financialProductId, null);
			//	Capital Amount
			BigDecimal minCapitalAmt = (BigDecimal) financialProduct.get_Value("MinCapitalAmt");
			if(minCapitalAmt == null) {
				minCapitalAmt = Env.ZERO;
			}
			BigDecimal maxCapitalAmt = (BigDecimal) financialProduct.get_Value("MaxCapitalAmt");
			if(maxCapitalAmt == null) {
				maxCapitalAmt = Env.ZERO;
			}
			//	Fees Amount
			int minFeesAmt = financialProduct.get_ValueAsInt("MinFeesAmt");
			int maxFeesAmt = financialProduct.get_ValueAsInt("MaxFeesAmt");
			//	Validate Capital
			if(capitalAmt != null
					&& capitalAmt.compareTo(Env.ZERO) > 0) {
				//	Minimum
				if(capitalAmt.compareTo(minCapitalAmt) < 0) {
					if(message.length() > 0) {
						message.append(Env.NL);
					}
					message.append("@CapitalAmt@ < @MinCapitalAmt@");
				}
				//	Maximum
				if(maxCapitalAmt.compareTo(Env.ZERO) > 0
						&& capitalAmt.compareTo(maxCapitalAmt) > 0) {
					if(message.length() > 0) {
						message.append(Env.NL);
					}
					message.append("@CapitalAmt@ > @MaxCapitalAmt@");
				}
			}
			//	Validate Fees Amount
			if(feesQty > 0) {
				//	Minimum
				if(feesQty < minFeesAmt) {
					if(message.length() > 0) {
						message.append(Env.NL);
					}
					message.append("@FeesQty@ < @MinFeesQty@");
				}
				//	Maximum
				if(maxFeesAmt > 0
						&& feesQty > maxFeesAmt) {
					if(message.length() > 0) {
						message.append(Env.NL);
					}
					message.append("@FeesQty@ > @MinFeesQty@");
				}
			}
		}
		//	Grace Days
		if(payDate == null) {
			payDate = startDate;
		}
		//	
		if(message.length() == 0) {
			return null;
		}
		//	
		return Msg.parseTranslation(Env.getCtx(), message.toString());
	}
	
	/**
	 * Get Column Names
	 * @return
	 */
	public Vector<String> getColumnNames() {	
		//  Header Info
		Vector<String> columnNames = new Vector<String>();
		columnNames.add(Msg.translate(Env.getCtx(), "PeriodNo"));
		columnNames.add(Msg.translate(Env.getCtx(), "StartDate"));
		columnNames.add(Msg.translate(Env.getCtx(), "EndDate"));
		columnNames.add(Msg.translate(Env.getCtx(), "DueDate"));
		columnNames.add(Msg.translate(Env.getCtx(), "CapitalAmt"));
		columnNames.add(Msg.translate(Env.getCtx(), "InterestAmt"));
		columnNames.add(Msg.translate(Env.getCtx(), "TaxAmt"));
		columnNames.add(Msg.translate(Env.getCtx(), "FeeAmt"));
		columnNames.add(Msg.translate(Env.getCtx(), "Balance"));
		//	
		return columnNames;
	}
	
	/**
	 * Set Column Class
	 * @param table
	 */
	protected void setColumnClass(IMiniTable table) {
		int i = 0;
		table.setColumnClass(i++, Integer.class, true);			//  0-Selection
		table.setColumnClass(i++, Timestamp.class, true);		//  1-StarDate
		table.setColumnClass(i++, Timestamp.class, true);		//  2-EndDate
		table.setColumnClass(i++, Timestamp.class, true);		//  2-DueDate
		table.setColumnClass(i++, BigDecimal.class, true);		//  4-CapitalAmt
		table.setColumnClass(i++, BigDecimal.class, true);		//  5-InterestAmt
		table.setColumnClass(i++, BigDecimal.class, true);		//  6-TaxAmt
		table.setColumnClass(i++, BigDecimal.class, true);		//  7-FeeAmt
		table.setColumnClass(i++, BigDecimal.class, true);		//  8-Balance
		//  Table UI
		table.autoSize();
	}
		
	/**
	 * Set Interest Fee
	 * @param interestFeeAmt
	 */
	public abstract void setInterestFeeAmt(BigDecimal interestFeeAmt);
	
	/**
	 * Set Tax Fee Amount
	 * @param taxFeeAmt
	 */
	public abstract void setTaxAmt(BigDecimal taxFeeAmt);
	
	/**
	 * Set Grand Total
	 * @param grandTotal
	 */
	public abstract void setGrandToral(BigDecimal grandTotal);
	
	/**
	 * Load Amortization
	 * @param amortizationList
	 */
	public abstract void reloadAmortization(List<AmortizationValue> amortizationList);

}
