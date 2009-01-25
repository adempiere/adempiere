package com.globalqss.ObPOSInterface;

import org.codehaus.xfire.fault.XFireFault;
import org.compiere.util.CLogger;
import org.openbravo.erpCommon.ws.externalSales.ProductsCatalogResponseDocument;
import org.openbravo.erpCommon.ws.externalSales.ProductsPlusCatalogResponseDocument;
import org.openbravo.erpCommon.ws.externalSales.UploadOrdersRequestDocument;
import org.openbravo.erpCommon.ws.externalSales.UploadOrdersResponseDocument;

/*
 * ADEMPIERE/COMPIERE
 * 
 * Web Service interface for openbravo pos
 * 
 * Contributors: Carlos Ruiz - globalqss
 */


/**
 * 
 * @author Carlos Ruiz - globalqss
 *
 */
public class ExternalSalesImpl implements ExternalSales {

	private static CLogger	log = CLogger.getCLogger(ExternalSalesImpl.class);
	
	public ExternalSalesImpl()
	{
		log.info("Creating session object ExternalSales");
	}
	
	public String getVersion() {
		return "0.1.0";
	}

	public UploadOrdersResponseDocument uploadOrders(UploadOrdersRequestDocument req)
			throws XFireFault {
		// TODO Auto-generated method stub
		String user = req.getUploadOrdersRequest().getUsername();
		return null;
	}

	public ProductsCatalogResponseDocument getProductsCatalog(int entityId,
			int organizationId, int salesChannel, String username,
			String password) throws XFireFault {
		// TODO Auto-generated method stub
		return null;
	}

	public ProductsPlusCatalogResponseDocument getProductsPlusCatalog(
			int entityId, int organizationId, int salesChannel,
			String username, String password) throws XFireFault {
		// TODO Auto-generated method stub
		return null;
	}

}