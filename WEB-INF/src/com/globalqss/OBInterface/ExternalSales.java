package com.globalqss.OBInterface;

import org.codehaus.xfire.fault.XFireFault;

import com.globalqss.obInterface.ProductsPlusCatalogRequest;
import com.globalqss.obInterface.ProductsPlusCatalogResponse;
import com.globalqss.obInterface.UploadOrdersRequest;
import com.globalqss.obInterface.UploadOrdersResponse;

public interface ExternalSales {

    public ProductsPlusCatalogResponse getProductsPlusCatalog(ProductsPlusCatalogRequest req) throws XFireFault;

    public UploadOrdersResponse uploadOrders(UploadOrdersRequest req) throws XFireFault;

    /* methods to implement */

    /*

    public ProductsCatalogResponse getProductsCatalog(ProductsPlusCatalogRequest req) throws XFireFault;

    public GetOrdersResponse getOrders(GetOrdersRequest req) throws XFireFault;
    
    */

}
