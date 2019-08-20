package com.klst.adempiere.einvoice;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.logging.Logger;

import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;

import com.klst.einvoice.CoreInvoiceVatBreakdown;
import com.klst.einvoice.CreditTransfer;
import com.klst.einvoice.DirectDebit;
import com.klst.einvoice.PaymentCard;
import com.klst.einvoice.ubl.Address;
import com.klst.einvoice.ubl.CommercialInvoice;
import com.klst.einvoice.ubl.Contact;
import com.klst.einvoice.ubl.Invoice;
import com.klst.einvoice.ubl.InvoiceLine;
import com.klst.einvoice.unece.uncefact.Amount;
import com.klst.einvoice.unece.uncefact.UnitPriceAmount;
import com.klst.untdid.codelist.PaymentMeansEnum;
import com.klst.untdid.codelist.TaxCategoryCode;

public class UblInvoice extends UblImpl {
	
	private static final Logger LOG = Logger.getLogger(UblInvoice.class.getName());

	private Object ublObject;

	@Override
	public String getDocumentNo() {
		return ((Invoice)ublObject).getId();
	}

	@Override
	void setBuyerReference(String buyerReference) {
		((Invoice)ublObject).setBuyerReference(buyerReference);
	}
	
	@Override
	void setPaymentInstructions(PaymentMeansEnum code, String paymentMeansText, String remittanceInformation
			, CreditTransfer creditTransfer, PaymentCard paymentCard, DirectDebit directDebit) {
		((Invoice)ublObject).setPaymentInstructions(code, paymentMeansText, remittanceInformation, creditTransfer, paymentCard, directDebit);
	}
	
	@Override
	void setPaymentTermsAndDate(String description, Timestamp ts) {
		((Invoice)ublObject).setPaymentTermsAndDate(description, ts);
	}

	@Override
	void mapByuer(String buyerName, int location_ID, int user_ID) {
		Address address = mapLocationToAddress(location_ID);
		Contact contact = mapUserToContact(user_ID);
		((Invoice)ublObject).setBuyer(buyerName, address, contact);
	}

	@Override
	void mapSeller(String sellerName, int location_ID, int salesRep_ID, String companyID, String companyLegalForm, String taxCompanyId) {
		Address address = mapLocationToAddress(location_ID);
		Contact contact = mapUserToContact(salesRep_ID);
		((Invoice)ublObject).setSeller(sellerName, address, contact, companyID, companyLegalForm);
		((Invoice)ublObject).getSellerParty().setTaxRegistrationId(taxCompanyId);
	}

	@Override
	void setTotals(Amount lineExtension, Amount taxExclusive, Amount taxInclusive, Amount payable, Amount taxTotal ) {
		((Invoice)ublObject).setDocumentTotals(lineExtension, taxExclusive, taxInclusive, payable);
		((Invoice)ublObject).setInvoiceTax(taxTotal);
	}

	@Override
	void addVATBreakDown(CoreInvoiceVatBreakdown vatBreakdown) {
		((Invoice)ublObject).addVATBreakDown(vatBreakdown);
	}

	void mapLine(MInvoiceLine invoiceLine) {
		int lineId = invoiceLine.getLine(); //Id
		BigDecimal taxRate = invoiceLine.getC_Tax().getRate();
		InvoiceLine line = new InvoiceLine(Integer.toString(lineId)
				, this.mapping.mapToQuantity(invoiceLine.getC_UOM().getX12DE355(), invoiceLine.getQtyInvoiced())
				, new Amount(mInvoice.getCurrencyISO(), invoiceLine.getLineNetAmt())
				, new UnitPriceAmount(mInvoice.getCurrencyISO(), invoiceLine.getPriceActual())
				, invoiceLine.getProduct().getName()
				, TaxCategoryCode.StandardRate, taxRate
				);
		line.setDescription(invoiceLine.getDescription());
		((Invoice)ublObject).addLine(line);		
	}

	Object mapToEModel(MInvoice adInvoice) {
		mInvoice = adInvoice;
		Invoice obj = new CommercialInvoice(XRECHNUNG_12);
		obj.setId(mInvoice.getDocumentNo());
		obj.setIssueDate(mInvoice.getDateInvoiced());
		obj.setDocumentCurrency(mInvoice.getC_Currency().getISO_Code());
		this.ublObject = obj;
		super.mapBuyerReference();

		makeOptionals();

		super.mapSellerGroup(); 
		super.mapBuyerGroup();
		
		super.mapPaymentGroup();
		super.mapDocumentTotals();
		super.mapVatBreakDownGroup();
		super.mapLineGroup();
		return ublObject;
	}

	protected void makeOptionals() {
		// Description ==> optional INVOICE NOTE
		((Invoice)ublObject).setNote(this.mapping.mapNote(mInvoice));
		
	}

}
