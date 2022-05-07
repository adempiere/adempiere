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

package org.spin.investment.process;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MBPartner;
import org.compiere.model.MConversionType;
import org.compiere.model.MCurrency;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MPriceList;
import org.compiere.model.MProduct;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.spin.investment.model.MFMAccount;
import org.spin.investment.model.MFMAgreement;
import org.spin.investment.model.MFMAmortization;
import org.spin.investment.model.MFMDunning;
import org.spin.investment.model.MFMProduct;
import org.spin.investment.model.MFMRate;

/** Generated Process for (Generate Invoice from Loan)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public class GenerateInvoiceFromLoan extends GenerateInvoiceFromLoanAbstract {
	/**	Invoice list to be completed	*/
	private List<MInvoice> invoiceList = new ArrayList<MInvoice>();
	/**	Current Invoice	*/
	private MInvoice invoice;
	/**	Precision	*/
	private int precision;
	
	@Override
	protected String doIt() throws Exception {
		precision = MCurrency.getStdPrecision(getCtx(), Env.getContextAsInt(getCtx(), "#C_Currency_ID"));
		//	Get keys from Smart Browse
		int chargeForInterestId = 0;
		int chargeForDunningId = 0;
		MFMAccount account = null;
		MFMAgreement agreement = null;
		MBPartner businessPartner = null;
		MProduct product = null;
		MFMProduct financialProduct = null;
		MFMAmortization lastAmortization = null;
		for(int amortizationId : getSelectionKeys()) {
			MFMAmortization amortization = new MFMAmortization(getCtx(), amortizationId, get_TrxName());
			BigDecimal capitalAmt = getSelectionAsBigDecimal(amortizationId, "LA_CapitalAmt");
			BigDecimal interestAmt = getSelectionAsBigDecimal(amortizationId, "LA_InterestAmt");
			BigDecimal dunningAmt = getSelectionAsBigDecimal(amortizationId, "LA_CurrentDunningAmt");
			boolean isDue = getSelectionAsBoolean(amortizationId, "LA_IsDue");
			//	Create Invoice
			if(isSplitInvoices() 
					|| invoice == null) {
				invoice = new MInvoice(getCtx(), 0, get_TrxName());
				//	Get agreement
				if(account == null) {
					//	Set Document Information
					account = (MFMAccount) amortization.getFM_Account();
					agreement = (MFMAgreement) account.getFM_Agreement();
					if(getParameterAsBoolean("IsPrepayment")) {
						List<MFMAmortization> toInvoiceList = MFMAmortization.getToInvoiceList(account.getFM_Account_ID(), get_TrxName());
						//	Validate to invoice as empty
						if(toInvoiceList == null
								|| toInvoiceList.isEmpty()) {
							throw new AdempiereException("@LoanManagement.NoToInvoice@");
						}
						//	Validate difference
						if(toInvoiceList.size() > getSelectionKeys().size()) {
							throw new AdempiereException("@LoanManagement.AllFeesNotSelected@");
						}
					}
					//	
					lastAmortization = MFMAmortization.getLastAmortizationFromAccount(account.getFM_Account_ID(), get_TrxName());
					//	Get Financial Product for configuration
					financialProduct = MFMProduct.getById(getCtx(), agreement.getFM_Product_ID(), get_TrxName());
					product = MProduct.get(getCtx(), financialProduct.getM_Product_ID());
					businessPartner = MBPartner.get(getCtx(), agreement.getC_BPartner_ID());
					int rateForInterestId = financialProduct.get_ValueAsInt("FM_Rate_ID");
					int rateForDunningInterestId = financialProduct.get_ValueAsInt("DunningInterest_ID");
					int dunningId = financialProduct.get_ValueAsInt("FM_Dunning_ID");
					//	Validate Dunning for it
					if(rateForDunningInterestId == 0
							&& dunningId == 0) {
						throw new AdempiereException("@FM_Dunning_ID@ @for@ @FM_Product_ID@ @NotFound@");
					}
					if(interestAmt != null
							&& rateForInterestId == 0) {
						throw new AdempiereException("@FM_Rate_ID@ @for@ @Interest@ @NotFound@");
					}
					//	For dunning
					if(dunningAmt != null
							&& rateForDunningInterestId == 0
							&& dunningId == 0) {
						throw new AdempiereException("@DunningInterest_ID@ @NotFound@");
					}
					//	For interest
					if(rateForInterestId != 0) {
						MFMRate interestRate = MFMRate.getById(getCtx(), rateForInterestId);
						chargeForInterestId = interestRate.getC_Charge_ID();
					}
					//	For Dunning
					if(rateForDunningInterestId != 0) {
						MFMRate dunningunningInterest = MFMRate.getById(getCtx(), rateForDunningInterestId);
						chargeForDunningId = dunningunningInterest.getC_Charge_ID();
					} else if(dunningId != 0) {
						MFMDunning dunning = MFMDunning.getById(getCtx(), dunningId);
						chargeForDunningId = dunning.getChargeId();
					}
				}
				//	Set Values for document
				invoice.setAD_Org_ID(agreement.getAD_Org_ID());
				//	Set Document Type
				if(getDocTypeTargetId() != 0) {
					invoice.setC_DocTypeTarget_ID(getDocTypeTargetId());
				} else {
					invoice.setIsSOTrx(true);
					invoice.setC_DocTypeTarget_ID();
				}
				//	Set Date for it
				invoice.setDateInvoiced(getDateInvoiced());
				invoice.setDateAcct(getDateInvoiced());
				//	Set Business Partner Information
				invoice.setBPartner(businessPartner);
				//	Add contact from Loan
				if(agreement.get_ValueAsInt("AD_User_ID") != 0) {
					invoice.setAD_User_ID(agreement.get_ValueAsInt("AD_User_ID"));
				}
				//	Set Description
				invoice.setDescription(Msg.parseTranslation(Env.getCtx(), "@Generate@ @from@ @Loan@") 
						+ " - " + agreement.getDocumentNo());
				//	Set Reference
				invoice.set_ValueOfColumn("FM_Account_ID", account.getFM_Account_ID());
				//	Set Currency and Price List
				MCurrency currency = MCurrency.get(getCtx(), account.getC_Currency_ID());
				MPriceList priceList = MPriceList.getDefault(getCtx(), true, currency.getISO_Code());
				if(priceList == null) {
					throw new AdempiereException("@M_PriceList_ID@ @NotFound@ @for@ " + currency.getISO_Code());
				}
				invoice.setM_PriceList_ID(priceList.getM_PriceList_ID());
				invoice.setC_ConversionType_ID(MConversionType.getDefault(getAD_Client_ID()));
				//	Save
				invoice.saveEx();
				//	Add to list
				invoiceList.add(invoice);
			}
			//	For Capital Amount
			if(product != null) {
				//	Create Line
				createLine(capitalAmt, product, 0, agreement, amortization);
				//	Add Description from Quote
				String description = invoice.getDescription();
				description = description + Env.NL + Msg.getMsg(getCtx(), "Fee") + " " + amortization.getPeriodNo() 
									+ (lastAmortization != null
										? "/" + lastAmortization.getPeriodNo()
										: "");
				invoice.setDescription(description);
			}
			//	For Interest
			if(chargeForInterestId != 0) {
				//	For not due fees
				if(!isDue
						&& amortization.getStartDate().after(getDateInvoiced())
						&& getParameterAsBoolean("IsPrepayment")) {
					int prepayFeeRateId = financialProduct.get_ValueAsInt("PrepayFeeRate_ID");
					if(prepayFeeRateId > 0) {
						MFMRate rateToApply = MFMRate.getById(getCtx(), prepayFeeRateId);
						if(rateToApply != null) {
							BigDecimal rateVersion = rateToApply.getValidRate(getDateInvoiced());
							if(rateVersion != null) {
								interestAmt = interestAmt.multiply(rateVersion);
								interestAmt = interestAmt.divide(Env.ONEHUNDRED);
							}
						}
					} else {
						interestAmt = Env.ZERO;
					}
				}
				//	Create Line
				createLine(interestAmt, null, chargeForInterestId, agreement, amortization);
			}
			//	For Dunning
			if(chargeForDunningId != 0) {
				//	Create Line
				createLine(dunningAmt, null, chargeForDunningId, agreement, amortization);
			}
		}
		//	Complete All Invoices
		completeInvoice();
		//	Show Message
		//	
		StringBuffer msg = new StringBuffer("@Created@ (")
									.append(invoiceList.size()).append(")");
		//	
		StringBuffer detail = new StringBuffer();
		//	Return
		for(MInvoice invoice : invoiceList) {
			if(detail.length() > 0)
				detail.append(", ");
			//	
			detail.append(invoice.getDocumentNo());
		}
		//	
		if(detail.length() > 0) {
			msg.append("[").append(detail).append("]");
		}
		//	
		return msg.toString();
	}
	
	/**
	 * Create Invoice line from Amortization
	 * @param amount
	 * @param product
	 * @param chargeId
	 * @param loan
	 * @param amortization
	 */
	private void createLine(BigDecimal amount, MProduct product, int chargeId, MFMAgreement loan, MFMAmortization amortization) {
		//	Validate Amount
		if(amount == null
				|| amount.equals(Env.ZERO)) {
			return;
		}
		//	Set values for line
		MInvoiceLine invoiceLine = new MInvoiceLine(invoice);
		if(product != null) {
			invoiceLine.setProduct(product);
		} else if(chargeId != 0) {
			invoiceLine.setC_Charge_ID(chargeId);
		}
		invoiceLine.setQty(Env.ONE);
		if(precision > 0) {
			amount.setScale(precision, RoundingMode.HALF_UP);
		}
		invoiceLine.setPrice(amount);
		invoiceLine.setDescription(
				Msg.parseTranslation(Env.getCtx(), 
						"@Generate@ @from@ @FM_Amortization_ID@ " + amortization.getPeriodNo() 
						+ " @of@ @Loan@ " + loan.getDocumentNo()));
		//	Set Reference
		invoiceLine.set_ValueOfColumn("FM_Amortization_ID", amortization.getFM_Amortization_ID());
		//	Save
		invoiceLine.saveEx();
	}
	
	/**
	 * 	Complete Invoice
	 */
	private void completeInvoice() {
		for(MInvoice invoice : invoiceList) {
			invoice.setDocAction(getDocAction());
			if (!invoice.processIt(getDocAction())) {
				log.warning("completeInvoice - failed: " + invoice);
				addLog(Msg.getMsg(getCtx(), "Error") + invoice);
			} else {
				addLog("@C_Invoice_ID@ @Created@" + invoice.getDocumentNo());
			}
			//	SAve
			invoice.saveEx();
		}
	}	//	completeInvoice
}