package com.globalqss.ObPOSInterface;

import org.codehaus.xfire.fault.XFireFault;
import org.compiere.util.CLogger;

import com.globalqss.obPOSInterface.ProductsPlusCatalogRequest;
import com.globalqss.obPOSInterface.ProductsPlusCatalogResponse;
import com.globalqss.obPOSInterface.UploadOrdersRequest;
import com.globalqss.obPOSInterface.UploadOrdersResponse;

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

	public ProductsPlusCatalogResponse getProductsPlusCatalog(
			ProductsPlusCatalogRequest req) throws XFireFault {
		// TODO Auto-generated method stub
		return null;
	}

	public UploadOrdersResponse uploadOrders(UploadOrdersRequest req)
			throws XFireFault {
		// TODO Auto-generated method stub
		return null;
	}

}