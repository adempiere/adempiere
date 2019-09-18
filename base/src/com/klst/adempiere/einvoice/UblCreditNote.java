package com.klst.adempiere.einvoice;

import java.math.BigDecimal;

import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;

import com.klst.einvoice.CoreInvoiceLine;
import com.klst.einvoice.ubl.GenericInvoice;
import com.klst.einvoice.ubl.GenericLine;
import com.klst.einvoice.unece.uncefact.Amount;
import com.klst.einvoice.unece.uncefact.UnitPriceAmount;
import com.klst.untdid.codelist.DocumentNameCode;
import com.klst.untdid.codelist.TaxCategoryCode;

public class UblCreditNote extends UblImpl {
	
	private Object ublObject;

	@Override
	public String getDocumentNo() {
		return ublInvoice.getId();
	}
 
	void mapLine(MInvoiceLine invoiceLine) {
		int lineId = invoiceLine.getLine(); //Id
		BigDecimal taxRate = invoiceLine.getC_Tax().getRate();
		CoreInvoiceLine line = GenericLine.createCreditNoteLine(Integer.toString(lineId)
				, this.mapping.mapToQuantity(invoiceLine.getC_UOM().getX12DE355(), invoiceLine.getQtyInvoiced())
				, new Amount(mInvoice.getCurrencyISO(), invoiceLine.getLineNetAmt())
				, new UnitPriceAmount(mInvoice.getCurrencyISO(), invoiceLine.getPriceActual())
				, invoiceLine.getProduct().getName()
				, TaxCategoryCode.StandardRate, taxRate
				);
		line.setDescription(invoiceLine.getDescription());
		ublInvoice.addLine(line);
	}

	Object mapToEModel(MInvoice adInvoice) {
		mInvoice = adInvoice;
		ublInvoice = GenericInvoice.createCreditNote(DEFAULT_PROFILE, null, DocumentNameCode.CreditNote);
		ublInvoice.setId(mInvoice.getDocumentNo());
		ublInvoice.setIssueDate(mInvoice.getDateInvoiced());
		ublInvoice.setDocumentCurrency(mInvoice.getC_Currency().getISO_Code());
		this.ublObject = ublInvoice.get();
		super.mapBuyerReference();
//
//		makeOptionals();

		super.mapSellerGroup(); 
		super.mapBuyerGroup();
		
		super.mapPaymentGroup();
		super.mapDocumentTotals();
		super.mapVatBreakDownGroup();
		super.mapLineGroup();
		return ublObject;
	}

}
