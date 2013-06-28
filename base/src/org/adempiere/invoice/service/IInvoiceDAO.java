package org.adempiere.invoice.service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.adempiere.util.ISingletonService;
import org.compiere.model.I_AD_Org;
import org.compiere.model.I_C_Invoice;
import org.compiere.model.I_C_InvoiceLine;
import org.compiere.model.I_C_InvoiceTax;
import org.compiere.model.I_C_LandedCost;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;

public interface IInvoiceDAO extends ISingletonService
{

	/**
	 * 
	 * @param invoice
	 * @return
	 * @throws IllegalArgumentException if invoice is not an {@link MInvoice}
	 */
	I_C_InvoiceLine createInvoiceLine(I_C_Invoice invoice);

	List<I_C_InvoiceLine> retrieveLines(I_C_Invoice invoice, String trxName);

	List<I_C_LandedCost> retrieveLandedCosts(I_C_InvoiceLine invoiceLine,
			String whereClause, String trxName);

	I_C_LandedCost createLandedCost(String trxName);

	I_C_InvoiceLine createInvoiceLine(String trxName);

	Collection<MInvoiceLine> retrieveReferringLines(Properties ctx, int invoiceLineId,
			String trxName);

	/**
	 * Search by the invoice when the document number and the bpartner id are known.
	 * 
	 * @param ctx
	 * @param invoiceNo
	 * @param bPartnerID
	 * @return the I_C_Invoice object if the value was found, null otherwise
	 */
	I_C_Invoice retrieveInvoiceByInvoiceNoAndBPartnerID(Properties ctx, String invoiceNo, int bPartnerID);

	/**
	 * Gets all open invoices for the specific organization.<br>
	 * Not guaranteed iterator. Do not use if modifying the "IsPaid" column.
	 * 
	 * @param adOrg
	 * @return
	 */
	Iterator<I_C_Invoice> retrieveOpenInvoicesByOrg(I_AD_Org adOrg);

	/**
	 * Gets invoice open amount (not paid amount) by calling {@link #retrieveOpenAmt(org.compiere.model.I_C_Invoice, boolean)} with <code>creditMemoAdjusted == true</code>. Please not that the value
	 * is:
	 * <ul>
	 * <li>relative regarding if is a sales or purchase transaction ({@link I_C_Invoice#isSOTrx()})
	 * <li>absolute regarding if is a credit memo or not
	 * </ul>
	 * 
	 * @param invoice
	 * @return open amount
	 */
	BigDecimal retrieveOpenAmt(org.compiere.model.I_C_Invoice invoice);

	List<I_C_InvoiceTax> retrieveTaxes(I_C_Invoice invoice);
}
