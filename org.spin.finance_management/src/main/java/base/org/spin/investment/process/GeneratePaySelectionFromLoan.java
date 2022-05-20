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

package org.spin.investment.process;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MBPartner;
import org.compiere.model.MBankAccount;
import org.compiere.model.MConversionRate;
import org.compiere.model.MCurrency;
import org.compiere.model.MPaySelection;
import org.compiere.model.MPaySelectionLine;
import org.compiere.model.MUser;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.spin.investment.model.MFMAccount;
import org.spin.investment.model.MFMAgreement;
import org.spin.investment.model.MFMProduct;

/** Generated Process for (Generate Payment Selection from Loan)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public class GeneratePaySelectionFromLoan extends GeneratePaySelectionFromLoanAbstract {
	
	/**	Sequence			*/
	private int				seqNo = 10;
	/**	Payment Selection	*/
	private MPaySelection	paymentSelection = null;
	
	@Override
	protected void prepare() {
		super.prepare();
		//	Valid Record Identifier
		if(getRecord_ID() <= 0
				&& getBankAccountId() == 0
				&& getPayDate() == null)
			throw new AdempiereException("@C_PaySelection_ID@ @NotFound@");
	}

	@Override
	protected String doIt() throws Exception {
		//	Instance current Payment Selection
		StringBuffer msg = new StringBuffer();
		if(getRecord_ID() > 0
				|| !isSplitPaySelection()) {	//	Already exists
			if(getRecord_ID() > 0) {
				paymentSelection = new MPaySelection(getCtx(), getRecord_ID(), get_TrxName());
				seqNo = paymentSelection.getLastLineNo();
			} else if(!isSplitPaySelection()) {	//	Split Payment Selection
				paymentSelection = new MPaySelection(getCtx(), 0, get_TrxName());
				paymentSelection.setC_BankAccount_ID(getBankAccountId());
				paymentSelection.setDateDoc(getPayDate());
				paymentSelection.setPayDate(getPayDate());
				if(getDocTypeTargetId() > 0)
					paymentSelection.setC_DocType_ID(getDocTypeTargetId());
				MUser user = MUser.get(getCtx(), getAD_User_ID());
				String userName = "";
				if(user != null)
					userName = user.getName();
				//	Set description
				paymentSelection.setDescription(Msg.getMsg(Env.getCtx(), "VPaySelect")
						+ " - " + userName
						+ " - " + DisplayType.getDateFormat(DisplayType.Date).format(getPayDate()));
			}
			//	Get Bank Account
			MBankAccount bankAccount = MBankAccount.get(getCtx(), getBankAccountId());
			//	Loop for keys
			for(Integer key : getSelectionKeys()) {
				//	get values from result set
				int financialAccountId = key;
				MFMAccount account = new MFMAccount(getCtx(), financialAccountId, get_TrxName());
				BigDecimal openCapitalAmt = getSelectionAsBigDecimal(key, "AC_OpenCapitalAmt");
				BigDecimal payAmt = getSelectionAsBigDecimal(key, "AC_PayAmt");
				if(openCapitalAmt.compareTo(payAmt) < 0) {
					throw new AdempiereException("@LoanManagement.OpenCapitalLessPay@");
				}
				MFMAgreement agreement = (MFMAgreement) account.getFM_Agreement();
				MFMProduct financialProduct = MFMProduct.getById(getCtx(), agreement.get_ValueAsInt("FM_Product_ID"), get_TrxName());
				if(financialProduct == null) {
					throw new AdempiereException("@FM_Product_ID@ @NotFound@");
				}
				int chargeId = financialProduct.get_ValueAsInt("C_Charge_ID");
				//	Set Organization
				if(paymentSelection.is_new()) {
					paymentSelection.setAD_Org_ID(agreement.getAD_Org_ID());
					//	Save Payment Selection
					paymentSelection.saveEx();
				}
				seqNo += 10;
				MPaySelectionLine line = new MPaySelectionLine(paymentSelection, seqNo, getPaymentRule());
				//	Account
				line.setC_BPartner_ID(agreement.get_ValueAsInt("C_BPartner_ID"));
				if(chargeId != 0) {
					line.setC_Charge_ID(chargeId);
				}
				line.setIsSOTrx(false);
				line.setAmtSource(payAmt);
				int conversionRateId = MConversionRate.getConversionRateId(account.getC_Currency_ID(), bankAccount.getC_Currency_ID(), 
						getPayDate(), 0, getAD_Client_ID(), paymentSelection.getAD_Org_ID());
				if(conversionRateId <= 0
						&& account.getC_Currency_ID() != bankAccount.getC_Currency_ID()) {
					throw new AdempiereException(MConversionRate.getErrorMessage(getCtx(), "NoCurrencyConversion", 
							account.getC_Currency_ID(), bankAccount.getC_Currency_ID(), 0, getPayDate(), get_TrxName()));
				}
				BigDecimal convertedAmt = convert(conversionRateId, payAmt);
				if(convertedAmt == null
						&& account.getC_Currency_ID() == bankAccount.getC_Currency_ID()) {
					convertedAmt = payAmt;
				}
				line.setOpenAmt(convertedAmt);
				line.setPayAmt (convertedAmt);
				line.setDiscountAmt(Env.ZERO);
				line.setDifferenceAmt(Env.ZERO);
				//	Set Conversion Rate
				if(conversionRateId > 0) {
					line.setC_Conversion_Rate_ID(conversionRateId);
					MConversionRate conversionRate = MConversionRate.get(getCtx(), conversionRateId);
					if(conversionRate.getC_ConversionType_ID() != 0) {
						line.setC_ConversionType_ID(conversionRate.getC_ConversionType_ID());
					}
				}
				//	Reference
				line.set_ValueOfColumn("FM_Account_ID", account.getFM_Account_ID());
				//	Save
				line.saveEx();
			}
			//	Complete Document
			completeDocument();
			//	Notify
			msg.append("@C_PaySelection_ID@: [" + paymentSelection.getDocumentNo() + "]");
		} else {	//	Is a new Payment Selection
			//	Loop for keys
			for(Integer key : getSelectionKeys()) {
				paymentSelection = new MPaySelection(getCtx(), 0, get_TrxName());
				paymentSelection.setC_BankAccount_ID(getBankAccountId());
				paymentSelection.setDateDoc(getPayDate());
				paymentSelection.setPayDate(getPayDate());
				if(getDocTypeTargetId() > 0) {
					paymentSelection.setC_DocType_ID(getDocTypeTargetId());
				}
				//	Get Bank Account
				MBankAccount bankAccount = MBankAccount.get(getCtx(), getBankAccountId());
				//	get values from result set
				int financialAccountId = key;
				MFMAccount account = new MFMAccount(getCtx(), financialAccountId, get_TrxName());
				MFMAgreement agreement = (MFMAgreement) account.getFM_Agreement();
				MFMProduct financialProduct = MFMProduct.getById(getCtx(), agreement.get_ValueAsInt("FM_Product_ID"), get_TrxName());
				if(financialProduct == null) {
					throw new AdempiereException("@FM_Product_ID@ @NotFound@");
				}
				int chargeId = financialProduct.get_ValueAsInt("C_Charge_ID");
				MBPartner partner = MBPartner.get(getCtx(), agreement.get_ValueAsInt("C_BPartner_ID"));
				if(partner == null)
					continue;
				//	Set description
				paymentSelection.setDescription(Msg.parseTranslation(getCtx(), 
						"@GeneratedFromLoan@ - " 
						+ partner.getValue() + " - " + partner.getName() + " " 
						+ DisplayType.getDateFormat(DisplayType.Date).format(getPayDate())));
				//	Set Organization
				paymentSelection.setAD_Org_ID(agreement.getAD_Org_ID());
				//	Save
				paymentSelection.saveEx();
				//	
				MPaySelectionLine line = new MPaySelectionLine(paymentSelection, seqNo, getPaymentRule());
				BigDecimal openCapitalAmt = getSelectionAsBigDecimal(key, "AC_OpenCapitalAmt");
				BigDecimal payAmt = getSelectionAsBigDecimal(key, "AC_PayAmt");
				if(openCapitalAmt.compareTo(payAmt) < 0) {
					throw new AdempiereException("@LoanManagement.OpenCapitalLessPay@");
				}
				seqNo += 10;
				//	Account
				line.setC_BPartner_ID(partner.getC_BPartner_ID());
				if(chargeId != 0) {
					line.setC_Charge_ID(chargeId);
				}
				line.setIsSOTrx(false);
				line.setAmtSource(payAmt);
				int conversionRateId = MConversionRate.getConversionRateId(account.getC_Currency_ID(), bankAccount.getC_Currency_ID(), 
						getPayDate(), 0, getAD_Client_ID(), paymentSelection.getAD_Org_ID());
				if(conversionRateId <= 0
						&& account.getC_Currency_ID() != bankAccount.getC_Currency_ID()) {
					throw new AdempiereException(MConversionRate.getErrorMessage(getCtx(), "NoCurrencyConversion", 
							account.getC_Currency_ID(), bankAccount.getC_Currency_ID(), 0, getPayDate(), get_TrxName()));
				}
				BigDecimal convertedAmt = convert(conversionRateId, payAmt);
				if(convertedAmt == null
						&& account.getC_Currency_ID() == bankAccount.getC_Currency_ID()) {
					convertedAmt = payAmt;
				}
				line.setOpenAmt(convertedAmt);
				line.setPayAmt (convertedAmt);
				line.setDiscountAmt(Env.ZERO);
				line.setDifferenceAmt(Env.ZERO);
				//	Set Conversion Rate
				if(conversionRateId > 0) {
					line.setC_Conversion_Rate_ID(conversionRateId);
					MConversionRate conversionRate = MConversionRate.get(getCtx(), conversionRateId);
					if(conversionRate.getC_ConversionType_ID() != 0) {
						line.setC_ConversionType_ID(conversionRate.getC_ConversionType_ID());
					}
				}
				//	Reference
				line.set_ValueOfColumn("FM_Account_ID", account.getFM_Account_ID());
				//	Save
				line.saveEx();
				//	Complete
				completeDocument();
				//	Add message
				if(msg.length() > 0) {
					msg.append(", ");
				}
				msg.append("@C_PaySelection_ID@: [" + paymentSelection.getDocumentNo() + "]");
			}
		}
		//	Return
		return msg.toString();
	}
	
	/**
	 * Convert from Conversion Rate
	 * @param conversionRateId
	 * @param sourceAmt
	 * @return
	 */
	private BigDecimal convert(int conversionRateId, BigDecimal sourceAmt) {
		if(conversionRateId <= 0
				|| sourceAmt == null) {
			return null;
		}
		//	
		MConversionRate conversionRate = MConversionRate.get(getCtx(), conversionRateId);
		BigDecimal convertedAmt = sourceAmt.multiply(conversionRate.getMultiplyRate());
		int stdPrecision = MCurrency.getStdPrecision(getCtx(), conversionRate.getC_Currency_ID_To());
		if (convertedAmt.scale() > stdPrecision) {
			convertedAmt = convertedAmt.setScale(stdPrecision, RoundingMode.HALF_UP);
		}
		//	Default Return
		return convertedAmt;
	}
	
	/**
	 * Complete Document
	 */
	private void completeDocument() {
		//	For new
		paymentSelection.load(get_TrxName());
		//	Process Selection
		if(!paymentSelection.processIt(MPaySelection.DOCACTION_Complete)) {
			throw new AdempiereException("@Error@ " + paymentSelection.getProcessMsg());
		}
		//	
		paymentSelection.saveEx();
	}
}