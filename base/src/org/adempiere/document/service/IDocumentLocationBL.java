package org.adempiere.document.service;

import org.adempiere.document.model.IDocumentBillLocation;
import org.adempiere.document.model.IDocumentDeliveryLocation;
import org.adempiere.document.model.IDocumentLocation;
import org.adempiere.util.ISingletonService;

/**
 * 
 * @author tsa
 * @task http://dewiki908/mediawiki/index.php/Fs76_03120:_Error_in_DocumentLocation_callout_%282012080910000142%29
 */
public interface IDocumentLocationBL extends ISingletonService
{

	void setBPartnerAddress(IDocumentLocation order);

	void setBillToAddress(IDocumentBillLocation billLocation);

	void setDeliveryToAddress(IDocumentDeliveryLocation order);

}
