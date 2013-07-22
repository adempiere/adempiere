package org.adempiere.document.service.impl;

import org.adempiere.document.model.IDocumentBillLocation;
import org.adempiere.document.model.IDocumentDeliveryLocation;
import org.adempiere.document.model.IDocumentLocation;
import org.adempiere.document.service.IDocumentLocationBL;

public class DocumentLocationBL implements IDocumentLocationBL
{

	@Override
	public void setBPartnerAddress(IDocumentLocation order)
	{
		// materialized addresses strings are not supported yet in adempiere
	}

	@Override
	public void setBillToAddress(IDocumentBillLocation billLocation)
	{
		// materialized addresses strings are not supported yet in adempiere
	}

	@Override
	public void setDeliveryToAddress(IDocumentDeliveryLocation order)
	{
		// materialized addresses strings are not supported yet in adempiere
	}

}
