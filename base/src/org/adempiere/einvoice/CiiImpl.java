package org.adempiere.einvoice;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;

import com.klst.einvoice.BG23_VatBreakdown;
import com.klst.einvoice.BusinessParty;
import com.klst.einvoice.CoreInvoice;
import com.klst.einvoice.CoreInvoiceLine;
import com.klst.einvoice.CreditTransfer;
import com.klst.einvoice.DirectDebit;
import com.klst.einvoice.IContact;
import com.klst.einvoice.PaymentCard;
import com.klst.einvoice.PaymentInstructions;
import com.klst.einvoice.PostalAddress;
import com.klst.einvoice.unece.uncefact.Amount;
import com.klst.einvoice.unece.uncefact.BICId;
import com.klst.einvoice.unece.uncefact.CrossIndustryInvoice;
import com.klst.einvoice.unece.uncefact.IBANId;
import com.klst.einvoice.unece.uncefact.TradeLineItem;
import com.klst.einvoice.unece.uncefact.UnitPriceAmount;
import com.klst.marshaller.CiiTransformer;
import com.klst.untdid.codelist.DocumentNameCode;
import com.klst.untdid.codelist.PaymentMeansEnum;
import com.klst.untdid.codelist.ReferenceCode;
import com.klst.untdid.codelist.TaxCategoryCode;

public class CiiImpl extends AbstractEinvoice {

	private static final Logger LOG = Logger.getLogger(CiiImpl.class.getName());
	
	protected Object ciiObject;
	private CoreInvoice invoice;

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
	void setPurchaseOrderReference(String poReference) {
		invoice.setPurchaseOrderReference(poReference);
	}

	@Override
	void setPaymentTermsAndDate(String description, Timestamp ts) {
		LOG.info("Payment terms (BT-20) & Payment due date (BT-9): description:"+description + " due date:"+ts);
		invoice.setPaymentTermsAndDate(description, ts);
	}

	@Override
	PaymentInstructions createPaymentInstructions(PaymentMeansEnum code, String paymentMeansText, String remittanceInformation
			, CreditTransfer creditTransfer, PaymentCard paymentCard, DirectDebit directDebit) {
		List<CreditTransfer> creditTransferList = null;
		if(creditTransfer!=null) {
			creditTransferList = new ArrayList<CreditTransfer>();
			creditTransferList.add(creditTransfer);
		}
		return invoice.createPaymentInstructions(code, paymentMeansText, remittanceInformation, creditTransferList, paymentCard, directDebit);
	}
	
	@Override
	void setPaymentInstructions(PaymentMeansEnum code, String paymentMeansText, String remittanceInformation,
			CreditTransfer creditTransfer, PaymentCard paymentCard, DirectDebit directDebit) {
		// [BR-DE-13] In der Rechnung müssen Angaben zu genau einer der drei Gruppen 
		//  "CREDIT TRANSFER" (BG-17), "PAYMENT CARD INFORMATION" (BG-18) oder "DIRECT DEBIT" (BG-19) übermittelt werden
		LOG.info("CREDIT TRANSFER (BG-17):"+creditTransfer + " PAYMENT CARD INFORMATION (BG-18):"+creditTransfer+ " DIRECT DEBIT (BG-19):"+directDebit);
		List<CreditTransfer> creditTransferList = null;
		if(creditTransfer!=null) {
			creditTransferList = new ArrayList<CreditTransfer>();
			creditTransferList.add(creditTransfer);
		}
		invoice.setPaymentInstructions(code, paymentMeansText, remittanceInformation, creditTransferList, paymentCard, directDebit);
	}

	@Override
	void setTotals(Amount lineExtension, Amount taxExclusive, Amount taxInclusive, Amount payable, Amount taxTotal) {
		// https://github.com/klst-de/AD-e-invoice/issues/4 : CII : currencyID should not be present
		invoice.setDocumentTotals( new Amount(null, lineExtension.getValue()) 
				, new Amount(null, taxExclusive.getValue()) 
				, new Amount(null, taxInclusive.getValue()) 
				, new Amount(null, payable.getValue()) );
		invoice.setInvoiceTax(taxTotal);
	}

	@Override
	BG23_VatBreakdown createVatBreakdown(Amount taxableAmount, Amount taxAmount, TaxCategoryCode codeEnum, BigDecimal percent) {
		// https://github.com/klst-de/AD-e-invoice/issues/4 : CII : currencyID should not be present
		return invoice.createVATBreakDown(new Amount(null, taxableAmount.getValue()), new Amount(null, taxAmount.getValue()), codeEnum, percent);
	}

	@Override
	void addVATBreakDown(BG23_VatBreakdown vatBreakdown) {
		invoice.addVATBreakDown(vatBreakdown);
	}

	@Override
	void mapByuer(String buyerName, int location_ID, int user_ID) {
		PostalAddress address = mapLocationToAddress(location_ID, ((CrossIndustryInvoice)ciiObject));
		IContact contact = mapUserToContact(user_ID, (CrossIndustryInvoice)ciiObject);
		invoice.setBuyer(buyerName, address, contact);
	}
	
	@Override
	void mapSeller(String sellerName, int location_ID, int salesRep_ID, String companyId, String companyLegalForm, String taxRegistrationId) {
		PostalAddress address = mapLocationToAddress(location_ID, ((CrossIndustryInvoice)ciiObject));
		IContact contact = mapUserToContact(salesRep_ID, (CrossIndustryInvoice)ciiObject);
		BusinessParty seller = invoice.createParty(sellerName, address, contact);
		seller.setCompanyId(companyId);
		seller.setCompanyLegalForm(companyLegalForm);
		seller.addTaxRegistrationId(taxRegistrationId, ReferenceCode.VATRegistrationNumber.getValue());
		invoice.setSeller(seller);
	}

	@Override
	Object mapToEModel(MInvoice mInvoice) {
		this.mInvoice = mInvoice;
		invoice = new CrossIndustryInvoice(DEFAULT_PROFILE, DocumentNameCode.CommercialInvoice);
		invoice.setId(this.mInvoice.getDocumentNo());
		invoice.setIssueDate(this.mInvoice.getDateInvoiced());
		this.ciiObject = invoice;
		super.mapPOReference();
//
//		makeOptionals();

		super.mapSellerGroup(); 
		super.mapBuyerGroup(); 
//		
		super.mapPaymentGroup();
		
		String documentCurrency = this.mInvoice.getC_Currency().getISO_Code();
		LOG.info("Document currency code (BT-5):"+documentCurrency);
		invoice.setDocumentCurrency(documentCurrency);
		
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
    			, new Amount(null, invoiceLine.getLineNetAmt()) // https://github.com/klst-de/AD-e-invoice/issues/4 : CII : currencyID should not be present
    			, new UnitPriceAmount(mInvoice.getCurrencyISO(), invoiceLine.getPriceActual())
    			, invoiceLine.getProduct().getName()
    			, TaxCategoryCode.StandardRate, taxRate
    			);
		line.setDescription(invoiceLine.getDescription());
		invoice.addLine(line);		
	}

	@Override
	PaymentInstructions getPaymentInstructions() {
		return invoice.getPaymentInstructions();
	}
	
	// factory methods
	@Override
	CreditTransfer createCreditTransfer(IBANId iban, String accountName, BICId bic) {
		LOG.config("iban:"+iban + ", accountName="+accountName + ", bic:"+bic);
		return invoice.createCreditTransfer(iban, accountName, bic);
	}
	CreditTransfer addCreditTransfer(IBANId iban, String accountName, BICId bic) {
		LOG.config("iban:"+iban + ", accountName="+accountName + ", bic:"+bic);
		return invoice.addCreditTransfer(iban, accountName, bic);
	}

	@Override
	CreditTransfer createCreditTransfer(String accountId, String accountName, BICId bic) {
		return invoice.createCreditTransfer(accountId, accountName, bic);
	}
	CreditTransfer addCreditTransfer(String accountId, String accountName, BICId bic) {
		return invoice.addCreditTransfer(accountId, accountName, bic);
	}

	@Override
	DirectDebit createDirectDebit(String mandateID, String bankAssignedCreditorID, IBANId iban) {
		return invoice.createDirectDebit(mandateID, bankAssignedCreditorID, iban);
	}

	@Override
	DirectDebit createDirectDebit(String mandateID, String bankAssignedCreditorID, String debitedAccountID) {
		return invoice.createDirectDebit(mandateID, bankAssignedCreditorID, debitedAccountID);
	}

	@Override
	PaymentCard createPaymentCard(String cardAccountID, String cardHolderName) {
		return invoice.createPaymentCard(cardAccountID, cardHolderName);
	}

}