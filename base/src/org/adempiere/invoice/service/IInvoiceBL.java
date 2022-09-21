package org.adempiere.invoice.service;

import java.math.BigDecimal;

import org.adempiere.util.ISingletonService;

public interface IInvoiceBL extends ISingletonService
{
	/**
	 * Writes off the given openAmt from the given invoice.
	 * 
	 * @param invoice
	 * @param openAmt open amount (not absolute, the value is relative to IsSOTrx sign)
	 * @param description
	 */
	void writeOffInvoice(org.adempiere.core.domains.models.I_C_Invoice invoice, BigDecimal openAmt, String description);
}
