package org.adempiere.einvoice;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;

import com.klst.einvoice.BG23_VatBreakdown;
import com.klst.einvoice.BusinessParty;
import com.klst.einvoice.CoreInvoice;
import com.klst.einvoice.CreditTransfer;
import com.klst.einvoice.DirectDebit;
import com.klst.einvoice.IContact;
import com.klst.einvoice.PaymentCard;
import com.klst.einvoice.PaymentInstructions;
import com.klst.einvoice.PostalAddress;
import com.klst.einvoice.ubl.GenericInvoice;
import com.klst.einvoice.unece.uncefact.Amount;
import com.klst.einvoice.unece.uncefact.BICId;
import com.klst.einvoice.unece.uncefact.IBANId;
import com.klst.marshaller.UblCreditNoteTransformer;
import com.klst.marshaller.UblInvoiceTransformer;
import com.klst.untdid.codelist.PaymentMeansEnum;
import com.klst.untdid.codelist.TaxCategoryCode;

public class UblImpl extends AbstractEinvoice {

//	private static final Logger LOG = Logger.getLogger(UblImpl.class.getName());

	private UblImpl delegate;
	CoreInvoice ublInvoice;
	
	// ctor
	public UblImpl() {
		super();
		delegate = null;
	}

	void setGenericInvoice(GenericInvoice<?> gi) {
		ublInvoice = gi;
	}
	
	@Override
	public String getDocumentNo() {
		return delegate.getDocumentNo();
	}

	@Override
	public void setupTransformer(boolean isCreditNote) {
		if(isCreditNote) {
			transformer = UblCreditNoteTransformer.getInstance();
		} else {
			transformer = UblInvoiceTransformer.getInstance();
		}
	}

	@Override
	public byte[] tranformToXML(MInvoice mInvoice) {
		boolean isCreditNote = mInvoice.isCreditMemo();
		setupTransformer(isCreditNote);
		if(isCreditNote) {
			delegate = new UblCreditNote();
//			return transformer.fromModel(mapToUblCreditNote(mInvoice));			
		} else {
			delegate = new UblInvoice();
//			return transformer.fromModel(mapToEModel(mInvoice));			
		}
		return transformer.fromModel(delegate.mapToEModel(mInvoice));			
	}

	@Override
	Object mapToEModel(MInvoice mInvoice) {
		// implemented in subclass
		return null;
	}

	@Override
	void mapLine(MInvoiceLine line) {
		// implemented in subclass
	}

	@Override
	void setPurchaseOrderReference(String poReference) {
		ublInvoice.setPurchaseOrderReference(poReference);
	}
	
	@Override
	PaymentInstructions createPaymentInstructions(PaymentMeansEnum code, String paymentMeansText, String remittanceInformation
			, CreditTransfer creditTransfer, PaymentCard paymentCard, DirectDebit directDebit) {
		List<CreditTransfer> creditTransferList = null;
		if(creditTransfer!=null) {
			creditTransferList = new ArrayList<CreditTransfer>();
			creditTransferList.add(creditTransfer);
		}
		return ublInvoice.createPaymentInstructions(code, paymentMeansText, remittanceInformation, creditTransferList, paymentCard, directDebit);
	}
	
	@Override
	PaymentInstructions getPaymentInstructions() {
		return ublInvoice.getPaymentInstructions();
	}
	
	@Override
	void setPaymentInstructions(PaymentMeansEnum code, String paymentMeansText, String remittanceInformation
			, CreditTransfer creditTransfer, PaymentCard paymentCard, DirectDebit directDebit) {
		List<CreditTransfer> creditTransferList = null;
		if(creditTransfer!=null) {
			creditTransferList = new ArrayList<CreditTransfer>();
			creditTransferList.add(creditTransfer);
		}
		ublInvoice.setPaymentInstructions(code, paymentMeansText, remittanceInformation, creditTransferList, paymentCard, directDebit);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.klst.einvoice.AbstractEinvoice#createCreditTransfer(com.klst.einvoice.unece.uncefact.IBANId, java.lang.String, com.klst.einvoice.unece.uncefact.BICId)
	 */
	// SEPA Überweisung	
	@Override
	CreditTransfer createCreditTransfer(IBANId iban, String accountName, BICId bic) {
		return ublInvoice.createCreditTransfer(iban, accountName, bic);
	}
	@Override
	CreditTransfer addCreditTransfer(IBANId iban, String accountName, BICId bic) {
		return ublInvoice.addCreditTransfer(iban, accountName, bic);
	}
	// non SEPA
	@Override
	CreditTransfer createCreditTransfer(String accountId, String accountName, BICId bic) {
		return ublInvoice.createCreditTransfer(accountId, accountName, bic);
	}
	@Override
	CreditTransfer addCreditTransfer(String accountId, String accountName, BICId bic) {
		return ublInvoice.addCreditTransfer(accountId, accountName, bic);
	}
	
	@Override
	DirectDebit createDirectDebit(String mandateID, String bankAssignedCreditorID, IBANId iban) {
		return ublInvoice.createDirectDebit(mandateID, bankAssignedCreditorID, iban);
	}

	@Override
	DirectDebit createDirectDebit(String mandateID, String bankAssignedCreditorID, String debitedAccountID) {
		return ublInvoice.createDirectDebit(mandateID, bankAssignedCreditorID, debitedAccountID);
	}

	@Override
	PaymentCard createPaymentCard(String cardAccountID, String cardHolderName) {
		return ublInvoice.createPaymentCard(cardAccountID, cardHolderName);
	}


	@Override
	void setPaymentTermsAndDate(String description, Timestamp ts) {
		ublInvoice.setPaymentTermsAndDate(description, ts);
	}
	
	@Override
	void setTotals(Amount lineExtension, Amount taxExclusive, Amount taxInclusive, Amount payable, Amount taxTotal ) {
		ublInvoice.setDocumentTotals(lineExtension, taxExclusive, taxInclusive, payable);
		ublInvoice.setInvoiceTax(taxTotal);
	}

	@Override
	void mapByuer(String buyerName, int location_ID, int user_ID) {
		PostalAddress address = mapLocationToAddress(location_ID, ublInvoice);
		IContact contact = mapUserToContact(user_ID, ublInvoice);
		ublInvoice.setBuyer(buyerName, address, contact);
	}

	@Override
	void mapSeller(String sellerName, int location_ID, int salesRep_ID, String companyId, String companyLegalForm, String vatRegistrationId) {
		PostalAddress address = mapLocationToAddress(location_ID, ublInvoice);
		IContact contact = mapUserToContact(salesRep_ID, ublInvoice);
		BusinessParty seller = ublInvoice.createParty(sellerName, address, contact);
		seller.setCompanyId(companyId);
		seller.setCompanyLegalForm(companyLegalForm);
		seller.setVATRegistrationId(vatRegistrationId);
		ublInvoice.setSeller(seller);
	}

	@Override
	BG23_VatBreakdown createVatBreakdown(Amount taxableAmount, Amount taxAmount, TaxCategoryCode codeEnum, BigDecimal percent) {
		return ublInvoice.createVATBreakDown(taxableAmount, taxAmount, codeEnum, percent);
	}

	@Override
	void addVATBreakDown(BG23_VatBreakdown vatBreakdown) {
		ublInvoice.addVATBreakDown(vatBreakdown);
	}
}
