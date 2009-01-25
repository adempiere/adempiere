package com.globalqss.ObPOSInterface;

import org.codehaus.xfire.fault.XFireFault;
import org.openbravo.erpCommon.ws.externalSales.ProductsCatalogRequestDocument;
import org.openbravo.erpCommon.ws.externalSales.ProductsCatalogResponseDocument;
import org.openbravo.erpCommon.ws.externalSales.ProductsPlusCatalogRequestDocument;
import org.openbravo.erpCommon.ws.externalSales.ProductsPlusCatalogResponseDocument;
import org.openbravo.erpCommon.ws.externalSales.UploadOrdersRequestDocument;
import org.openbravo.erpCommon.ws.externalSales.UploadOrdersResponseDocument;

public interface ExternalSales {

    public ProductsPlusCatalogResponseDocument getProductsPlusCatalog(int entityId, int organizationId, int salesChannel, String username, String password) throws XFireFault;

    public UploadOrdersResponseDocument uploadOrders(UploadOrdersRequestDocument req) throws XFireFault;

    public ProductsCatalogResponseDocument getProductsCatalog(int entityId, int organizationId, int salesChannel, String username, String password) throws XFireFault;

    /* methods to implement */

    /*

    public GetOrdersResponseDocument getOrders(GetOrdersRequestDocument req) throws XFireFault;
    
    */

}
