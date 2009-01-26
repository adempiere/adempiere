package com.globalqss.ObPOSInterface;

import org.codehaus.xfire.fault.XFireFault;
import org.compiere.util.CLogger;
import org.openbravo.erpCommon.ws.externalSales.GetCustomersResponse;
import org.openbravo.erpCommon.ws.externalSales.GetCustomersResponseDocument;

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
public class WebServiceImpl implements WebService {

	private static CLogger	log = CLogger.getCLogger(WebServiceImpl.class);
	
	public WebServiceImpl()
	{
		log.info("Creating session object ExternalSales");
	}
	
	public String getVersion() {
		return "0.1.0";
	}

	public GetCustomersResponseDocument getCustomers(int clientId, String username, String password)
			throws XFireFault {
		// TODO Auto-generated method stub
		GetCustomersResponseDocument resdoc = GetCustomersResponseDocument.Factory.newInstance();
		GetCustomersResponse res = resdoc.addNewGetCustomersResponse();

		ExternalSalesImpl.authenticate(username, password);

		// TODO Auto-generated method stub

		return resdoc;
	}

}