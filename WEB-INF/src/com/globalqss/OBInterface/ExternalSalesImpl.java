package com.globalqss.OBInterface;

import org.codehaus.xfire.fault.XFireFault;
import org.compiere.util.CLogger;

import com.globalqss.obInterface.ProductsPlusCatalogRequest;
import com.globalqss.obInterface.ProductsPlusCatalogResponse;
import com.globalqss.obInterface.UploadOrdersRequest;
import com.globalqss.obInterface.UploadOrdersResponse;

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