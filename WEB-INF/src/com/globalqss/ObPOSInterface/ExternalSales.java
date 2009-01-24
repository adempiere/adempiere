package com.globalqss.ObPOSInterface;

import org.codehaus.xfire.fault.XFireFault;

import com.globalqss.obPOSInterface.ProductsPlusCatalogRequest;
import com.globalqss.obPOSInterface.ProductsPlusCatalogResponse;
import com.globalqss.obPOSInterface.UploadOrdersRequest;
import com.globalqss.obPOSInterface.UploadOrdersResponse;

public interface ExternalSales {

    public ProductsPlusCatalogResponse getProductsPlusCatalog(ProductsPlusCatalogRequest req) throws XFireFault;

    public UploadOrdersResponse uploadOrders(UploadOrdersRequest req) throws XFireFault;

    /* methods to implement */

    /*

    public ProductsCatalogResponse getProductsCatalog(ProductsPlusCatalogRequest req) throws XFireFault;

    public GetOrdersResponse getOrders(GetOrdersRequest req) throws XFireFault;
    
    */

}
