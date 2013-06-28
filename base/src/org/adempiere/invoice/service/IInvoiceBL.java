package org.adempiere.invoice.service;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.util.ISingletonService;
import org.compiere.model.I_C_Invoice;
import org.compiere.model.I_C_InvoiceLine;
import org.compiere.model.I_M_InOut;
import org.compiere.model.MInvoice;

public interface IInvoiceBL extends ISingletonService
{

	MInvoice createAndCompleteForInOut(I_M_InOut inOut, Timestamp dateInvoiced, String trxName);

	/**
	 * Copies a given invoice
	 * 
	 * @param from the copy source
	 * @param dateDoc
	 * @param C_DocTypeTarget_ID
	 * @param isSOTrx parameter is set as the copy's <code>IsSOtrx</code> value
	 * @param counter if <code>true</code>, then the copy shall be the counter document of <code>from</code>
	 * @param setOrder if true, then the copy shall reference the same C_Order that <code>from</code> references
	 * @param copyLines if true, the invoice lines are also copied
	 * @return
	 */
	org.compiere.model.I_C_Invoice copyFrom(
			org.compiere.model.I_C_Invoice from,
			Timestamp dateDoc,
			int C_DocTypeTarget_ID,
			boolean isSOTrx,
			boolean counter,
			boolean setOrder,
			boolean copyLines);

	/**
	 * 
	 * @param fromInvoice
	 * @param toInvoice
	 * @param counter
	 * @param setOrder
	 * @return
	 * @see #copyFrom(I_C_Invoice, Timestamp, int, boolean, boolean, boolean, boolean)
	 */
	int copyLinesFrom(I_C_Invoice fromInvoice, I_C_Invoice toInvoice, boolean counter, boolean setOrder);

	String getSummary(I_C_Invoice invoice);

	/**
	 * 
	 * @param invoice
	 * @return true if the given invoice is a CreditMemo (APC or ARC)
	 */
	boolean isCreditMemo(I_C_Invoice invoice);

	/**
	 * Writes off the given openAmt from the given invoice.
	 * 
	 * @param invoice
	 * @param openAmt open amount (not absolute, the value is relative to IsSOTrx sign)
	 * @param description
	 */
	void writeOffInvoice(org.compiere.model.I_C_Invoice invoice, BigDecimal openAmt, String description);

	/**
	 * Creates a credit memo for the given invoice. The credit memo will have one line per invoice line, each line having qty 1. The credit memo's <code>GrandTotal</code> will be the given invoice's
	 * open amount. If the given invoice has already been partially paid/allocated, then the credit memo lines will only have the the remaining fractions of the invoice's original
	 * <code>LineNetAmt</code> values. The created credit memo will always have <code>IsTaxIncluded='Y'</code>
	 * 
	 * Depending in the <code>completeAndAllocate</code> parameter, the credit memo will also be allocated against the invoice, so that both have <code>IsPaid='Y'</code>.
	 * 
	 * @param invoice the invoice to be credited. May not be fully paid/allocated and may not be a credit memo itself
	 * @param completeAndAllocate if <code>true</code>, then the credit memo is completed and the credit memo's <code>GrandTotal</code> is allocated against the given invoice, so that the given
	 *            invoice has <code>IsPaid='Y'</code> afterwards. If <code>false</code>, then the credit memo is only "prepared", so to that is can still be edited by a user.
	 * @return the created credit memo
	 * @throws AdempiereException if
	 *             <ul>
	 *             <li>the given invoice is <code>null</code> or</li>
	 *             <li>the given C_Charge_ID doesn't have a valid C_Charge or</li>
	 *             <li>the given invoice is a credit memo or</li>
	 *             <li>the given invoice is already fully paid or</li>
	 *             <li>the credit memo line's C_Tax is not tax exempt (due to the charge's tax category and/or the invoice BPartner's location)</li>
	 *             </ul>
	 */
	I_C_Invoice creditInvoice(I_C_Invoice invoice, boolean completeAndAllocate);

	/**
	 * Creates a new invoice line for the given invoice. Note that the new line is not saved.
	 * 
	 * @param invoice
	 * @return
	 */
	I_C_InvoiceLine createLine(I_C_Invoice invoice);

	/**
	 * Test Allocation (and set paid flag)
	 * 
	 * @return true if updated
	 */
	boolean testAllocation(I_C_Invoice invoice);

}
