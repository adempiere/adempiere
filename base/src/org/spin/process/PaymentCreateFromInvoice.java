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

package org.spin.process;

import java.math.BigDecimal;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MInvoice;
import org.compiere.model.MPaySelection;
import org.compiere.model.MPayment;
import org.compiere.model.MPaymentAllocate;
import org.compiere.model.MUser;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Util;

/**
 * 	Payment Create From Invoice, used for Smart Browse (Create Payment From Invoice)
 *	@author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 1616 ] Create collect from Invoice list
 *		@see https://github.com/adempiere/adempiere/issues/1616
 */
public class PaymentCreateFromInvoice extends PaymentCreateFromInvoiceAbstract {
	
	/**	Is New				*/
	private boolean			isNew = false;
	/**	Payment Selection	*/
	private MPayment		payment = null;
	/**	Remaining			*/
	private BigDecimal		remaining = Env.ZERO;
	/**	Total Amount		*/
	private BigDecimal		totalPayAmt = Env.ZERO;
	
	@Override
	protected void prepare() {
		super.prepare();
		//	Valid Record Identifier
		if(getRecord_ID() <= 0
				&& getBankAccountId() == 0
				&& getPayDate() == null)
			throw new AdempiereException("@C_Payment_ID@ @NotFound@");
	}

	@Override
	protected String doIt() throws Exception {
		//	Sum all
		for(int invoiceId : getSelectionKeys()) {
			BigDecimal payAmt = getSelectionAsBigDecimal(invoiceId, "INV_PayAmt");
			BigDecimal discountAmt = getSelectionAsBigDecimal(invoiceId, "INV_DiscountAmt");
			//	Validate discount
			if(discountAmt == null) {
				discountAmt = Env.ZERO;
			}
			totalPayAmt = totalPayAmt.add(payAmt.subtract(discountAmt));
		}
		//	Verify remaining
		if(!isOverUnderPayment()
				&& getPayAmt() != null
				&& getPayAmt().compareTo(Env.ZERO) > 0) {
			//	Validate
			if(totalPayAmt.compareTo(getPayAmt()) != 0) {
				throw new AdempiereException("@PaymentAllocateSumInconsistent@");
			}
		}
		//	Set Total Payment
		if(getPayAmt() == null
				|| getPayAmt().compareTo(Env.ZERO) <= 0) {
			setPayAmt(totalPayAmt);
		}
		//	Loop for keys
		for(int invoiceId : getSelectionKeys()) {
			if(payment == null) {
				MInvoice invoice = new MInvoice(getCtx(), invoiceId, get_TrxName());
				//	Create and fill payment
				createPayment(invoice.getC_BPartner_ID(), invoice.getC_Currency_ID());
			}
			//	get values from result set
			BigDecimal openAmt = getSelectionAsBigDecimal(invoiceId, "INV_OpenAmt");
			BigDecimal payAmt = getSelectionAsBigDecimal(invoiceId, "INV_PayAmt");
			BigDecimal discountAmt = getSelectionAsBigDecimal(invoiceId, "INV_DiscountAmt");
			//	Validate discount
			if(discountAmt == null) {
				discountAmt = Env.ZERO;
			}
			//	Add invoice to invoice pay
			MPaymentAllocate invoicePayAllocate = new MPaymentAllocate(getCtx(), 0, get_TrxName());
			//	
			invoicePayAllocate.setC_Payment_ID(payment.getC_Payment_ID());
			invoicePayAllocate.setC_Invoice_ID(invoiceId);
			//	For Pay amount
			payAmt = payAmt.subtract(discountAmt);
			BigDecimal overUnderAmt = openAmt.subtract(payAmt);
			//	Set Remaining
			if(remaining.compareTo(Env.ZERO) > 0) {
				remaining = remaining.subtract(payAmt);
				if(remaining.compareTo(Env.ZERO) < 0) {
					overUnderAmt = payAmt;
					payAmt = payAmt.add(remaining);
					overUnderAmt = overUnderAmt.subtract(payAmt);
				}
			}
			invoicePayAllocate.setInvoiceAmt(openAmt);
			invoicePayAllocate.setAmount(payAmt);
			invoicePayAllocate.setDiscountAmt(discountAmt);
			invoicePayAllocate.setOverUnderAmt(overUnderAmt);
			//	Save
			invoicePayAllocate.saveEx();
			//	Last invoice
			if(overUnderAmt.compareTo(Env.ZERO) > 0) {
				break;
			}
		}
		//	For new
		if(isNew) {
			//	Load Record
			payment.load(get_TrxName());
			//	Process Selection
			if(!payment.processIt(MPaySelection.DOCACTION_Complete)) {
				throw new AdempiereException("@Error@ " + payment.getProcessMsg());
			}
			//	
			payment.saveEx();
			//	Notify
			return payment.getDescription();
		}
		//	Add log
		addLog(payment.getC_Payment_ID(), payment.getDateTrx(), null, payment.getDocumentNo());
		//	Default Ok
		return "@Created@ @C_Payment_ID@ " + payment.getDocumentInfo();
	}
	
	/**
	 * Create and fill Payment from parameters
	 * @param businessPartnerId
	 * @param currencyId
	 */
	private void createPayment(int businessPartnerId, int currencyId) {
		if(getRecord_ID() > 0) {	//	Already exists
			payment = new MPayment(getCtx(), getRecord_ID(), get_TrxName());
		} else {
			payment = new MPayment(getCtx(), 0, get_TrxName());
			//	
			isNew = true;
		}
		//	
		payment.setC_BPartner_ID(businessPartnerId);
		payment.setC_BankAccount_ID(getBankAccountId());
		payment.setDateTrx(getPayDate());
		payment.setDateAcct(getDateDoc());
		if(getDocTypeTargetId() > 0) {
			payment.setC_DocType_ID(getDocTypeTargetId());
		}
		//	Add data for payment amount
		payment.setPayAmt(getPayAmt());
		remaining = getPayAmt();
		MUser user = MUser.get(getCtx(), getAD_User_ID());
		String userName = "";
		if(user != null)
			userName = user.getName();
		//	Set description
		payment.setDescription(Msg.parseTranslation(Env.getCtx(), "@Created@ @from@")
				+ " - " + userName
				+ " - " + DisplayType.getDateFormat(DisplayType.Date).format(getPayDate()));
		//	Tender Type
		payment.setTenderType(getTenderType());
		//	Currency
		payment.setC_Currency_ID(currencyId);
		//	
		if(!Util.isEmpty(getAccountNo())) {
			payment.setAccountNo(getAccountNo());
		}
		//	Routing No
		if(!Util.isEmpty(getRoutingNo())) {
			payment.setRoutingNo(getRoutingNo());
		}
		//	Check No
		if(!Util.isEmpty(getCheckNo())) {
			payment.setCheckNo(getCheckNo());
		}
		//	Micr
		if(!Util.isEmpty(getMicr())) {
			payment.setMicr(getMicr());
		}
		//	Credit Card Type
		if(!Util.isEmpty(getCreditCardType())) {
			payment.setCreditCardType(getCreditCardType());
		}
		//	Trx Type
		if(!Util.isEmpty(getTrxType())) {
			payment.setTrxType(getTrxType());
		}
		//	Credit Card Number
		if(!Util.isEmpty(getCreditCardNumber())) {
			payment.setCreditCardNumber(getCreditCardNumber());
		}
		//	Credit Card VV
		if(!Util.isEmpty(getCreditCardVV())) {
			payment.setCreditCardVV(getCreditCardVV());
		}
		//	Credit Card Exp MM
		if(getCreditCardExpMM() != 0) {
			payment.setCreditCardExpMM(getCreditCardExpMM());
		}
		//	Credit Card Exp YY
		if(getCreditCardExpYY() != 0) {
			payment.setCreditCardExpYY(getCreditCardExpYY());
		}
		//	Name
		if(!Util.isEmpty(getName())) {
			payment.setA_Name(getName());
		}
		//	Street
		if(!Util.isEmpty(getStreet())) {
			payment.setA_Street(getStreet());
		}
		//	City
		if(!Util.isEmpty(getCity())) {
			payment.setA_City(getCity());
		}
		//	Zip
		if(!Util.isEmpty(getZip())) {
			payment.setA_Zip(getZip());
		}
		//	State
		if(!Util.isEmpty(getState())) {
			payment.setA_State(getState());
		}
		//	Country
		if(!Util.isEmpty(getCountry())) {
			payment.setA_Country(getCountry());
		}
		//	IdentDL
		if(!Util.isEmpty(getIdentDL())) {
			payment.setA_Ident_DL(getIdentDL());
		}
		//	IdentSSN
		if(!Util.isEmpty(getIdentSSN())) {
			payment.setA_Ident_SSN(getIdentSSN());
		}
		//	EMail
		if(!Util.isEmpty(getEMail())) {
			payment.setA_EMail(getEMail());
		}
		//	Tax Amt
		if(getTaxAmt() != null) {
			payment.setTaxAmt(getTaxAmt());
		}
		//	PO Num
		if(!Util.isEmpty(getPONum())) {
			payment.setPONum(getPONum());
		}
		//	Voice Auth Code
		if(!Util.isEmpty(getVoiceAuthCode())) {
			payment.setVoiceAuthCode(getVoiceAuthCode());
		}
		//	trx ID
		if(!Util.isEmpty(getTrxID())) {
			payment.setOrig_TrxID(getTrxID());
		}
		//	Save
		payment.saveEx();
	}
}