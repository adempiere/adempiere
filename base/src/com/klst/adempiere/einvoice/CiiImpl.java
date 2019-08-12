package com.klst.adempiere.einvoice;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MLocation;
import org.compiere.model.MUser;
import org.compiere.util.Env;

import com.klst.einvoice.CoreInvoiceLine;
import com.klst.einvoice.CoreInvoiceVatBreakdown;
import com.klst.einvoice.CreditTransfer;
import com.klst.einvoice.DirectDebit;
import com.klst.einvoice.PaymentCard;
import com.klst.einvoice.unece.uncefact.Amount;
import com.klst.einvoice.unece.uncefact.BICId;
import com.klst.einvoice.unece.uncefact.CrossIndustryInvoice;
import com.klst.einvoice.unece.uncefact.IBANId;
import com.klst.einvoice.unece.uncefact.TradeAddress;
import com.klst.einvoice.unece.uncefact.TradeContact;
import com.klst.einvoice.unece.uncefact.TradeLineItem;
import com.klst.einvoice.unece.uncefact.UnitPriceAmount;
import com.klst.einvoice.unece.uncefact.VatBreakdown;
import com.klst.marshaller.CiiTransformer;
import com.klst.untdid.codelist.DocumentNameCode;
import com.klst.untdid.codelist.PaymentMeansEnum;
import com.klst.untdid.codelist.TaxCategoryCode;

public class CiiImpl extends AbstractEinvoice {

	protected Object ciiObject;

	@Override
	public String getDocumentNo() {
		return ((CrossIndustryInvoice)ciiObject).getId();
	}

	@Override
	public void setupTransformer(boolean isCreditNote) {
		// CII uses same transformer for CreditNote and Invoice
		transformer = CiiTransformer.getInstance();
	}

	@Override
	public byte[] tranformToXML(MInvoice mInvoice) {
		boolean isCreditNote = mInvoice.isCreditMemo();
		setupTransformer(isCreditNote);
		return transformer.fromModel(mapToEModel(mInvoice));			
	}

	@Override
	void setBuyerReference(String buyerReference) {
		((CrossIndustryInvoice)ciiObject).setBuyerReference(buyerReference);
	}

	@Override
	void setPaymentTermsAndDate(String description, Timestamp ts) {
		((CrossIndustryInvoice)ciiObject).setPaymentTermsAndDate(description, ts);
	}

	@Override
	void setPaymentInstructions(PaymentMeansEnum code, String paymentMeansText, String remittanceInformation,
			CreditTransfer creditTransfer, PaymentCard paymentCard, DirectDebit directDebit) {
		// TODO Auto-generated method stub	
	}

	@Override
	void setTotals(Amount lineExtension, Amount taxExclusive, Amount taxInclusive, Amount payable, Amount taxTotal) {
		// TODO Auto-generated method stub
		
	}

	@Override
	CoreInvoiceVatBreakdown createVatBreakdown(Amount taxableAmount, Amount taxAmount, TaxCategoryCode codeEnum, BigDecimal percent) {
		return new VatBreakdown(taxableAmount, taxAmount, codeEnum, percent);
	}

	@Override
	void addVATBreakDown(CoreInvoiceVatBreakdown vatBreakdown) {
		((CrossIndustryInvoice)ciiObject).addVATBreakDown(vatBreakdown);
	}


	protected TradeAddress mapLocationToAddress(int location_ID) {
		MLocation mLocation = new MLocation(Env.getCtx(), location_ID, get_TrxName());
		String countryCode = mLocation.getCountry().getCountryCode();
		String postalCode = mLocation.getPostal();
		String city = mLocation.getCity();
		String street = null;
		String a1 = mLocation.getAddress1();
		String a2 = mLocation.getAddress2();
		String a3 = mLocation.getAddress3();
		String a4 = mLocation.getAddress4();
		TradeAddress address = new TradeAddress(countryCode, postalCode, city, street);
		if(a1!=null) address.setAddressLine1(a1);
		if(a2!=null) address.setAddressLine2(a2);
		if(a3!=null) address.setAddressLine3(a3);
//		if(a4!=null) address.setAdditionalStreet(a4); // TODO ???????????
		return address;
	}
	
	protected TradeContact mapUserToContact(int user_ID) {
		MUser mUser = new MUser(Env.getCtx(), user_ID, get_TrxName());
		String contactName = mUser.getName();
		String contactTel = mUser.getPhone();
		String contactMail = mUser.getEMail();
		TradeContact contact = new TradeContact(contactName, contactTel, contactMail);
		return contact;
	}

	@Override
	void mapByuer(String buyerName, int location_ID, int user_ID) {
		TradeAddress address = mapLocationToAddress(location_ID);
		TradeContact contact = mapUserToContact(user_ID);
		((CrossIndustryInvoice)ciiObject).setBuyer(buyerName, address, contact);
	}
	
	@Override
	void mapSeller(String sellerName, int location_ID, int salesRep_ID, String companyID, String companyLegalForm, String taxCompanyId) {
		TradeAddress address = mapLocationToAddress(location_ID);
		TradeContact contact = mapUserToContact(salesRep_ID);
		((CrossIndustryInvoice)ciiObject).setSeller(sellerName, address, contact, companyID, companyLegalForm);
//		((CrossIndustryInvoice)ciiObject).setSellerTaxCompanyId(taxCompanyId); TODO
	}

	@Override
	Object mapToEModel(MInvoice mInvoice) {
		this.mInvoice = mInvoice;
		CrossIndustryInvoice obj = new CrossIndustryInvoice(XRECHNUNG_12, DocumentNameCode.CommercialInvoice);
		obj.setId(this.mInvoice.getDocumentNo());
		obj.setIssueDate(this.mInvoice.getDateInvoiced());
		obj.setDocumentCurrency(this.mInvoice.getC_Currency().getISO_Code());
		this.ciiObject = obj;
		super.mapBuyerReference();
//
//		makeOptionals();

		super.mapSellerGroup(); 
		super.mapBuyerGroup(); 
//		
//		makePaymentGroup();
		super.mapDocumentTotals();
		super.mapVatBreakDownGroup();
		super.mapLineGroup();
		return this.ciiObject;
	}

	@Override
	void mapLine(MInvoiceLine invoiceLine) {
		int lineId = invoiceLine.getLine(); //Id
		BigDecimal taxRate = invoiceLine.getC_Tax().getRate(); //.setScale(SCALE, RoundingMode.HALF_UP);
    	CoreInvoiceLine line = new TradeLineItem
    			( Integer.toString(lineId)
    			, this.mapping.mapToQuantity(invoiceLine.getC_UOM().getX12DE355(), invoiceLine.getQtyInvoiced())
    			, new Amount(mInvoice.getCurrencyISO(), invoiceLine.getLineNetAmt())
    			, new UnitPriceAmount(mInvoice.getCurrencyISO(), invoiceLine.getPriceActual())
    			, invoiceLine.getProduct().getName()
    			, TaxCategoryCode.StandardRate, taxRate
    			);
		line.setDescription(invoiceLine.getDescription());
		((CrossIndustryInvoice)ciiObject).addLine(line);		
	}

	// factory methods ----------------- TODO to be implemented in future
	@Override
	CreditTransfer createCreditTransfer(IBANId iban, String accountName, BICId bic) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	CreditTransfer createCreditTransfer(String accountId, String accountName, BICId bic) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	DirectDebit createDirectDebit(String mandateID, String bankAssignedCreditorID, IBANId iban) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	DirectDebit createDirectDebit(String mandateID, String bankAssignedCreditorID, String debitedAccountID) {
		// TODO Auto-generated method stub
		return null;
	}

}
