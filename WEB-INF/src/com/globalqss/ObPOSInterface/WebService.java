package com.globalqss.ObPOSInterface;

import org.codehaus.xfire.fault.XFireFault;
import org.openbravo.erpCommon.ws.externalSales.GetCustomersResponseDocument;

public interface WebService {

    /* methods to implement */
	
	public GetCustomersResponseDocument getCustomers(int clientId, String username, String password) throws XFireFault;

	/*
	public GetCustomerResponseDocument getCustomer(GetCustomerRequestDocument req) throws XFireFault;
	
	public GetCustomerResponse1Document getCustomer(GetCustomerRequest1Document req) throws XFireFault;
	
	public UpdateCustomerResponseDocument updateCustomer(UpdateCustomerRequestDocument req) throws XFireFault;
	
	public GetCustomerAddressesResponseDocument getCustomerAddresses(GetCustomerAddressesRequestDocument req) throws XFireFault;

	public GetCustomerLocationResponseDocument getCustomerLocation(GetCustomerLocationRequestDocument req) throws XFireFault;
	
	public UpdateAddressResponseDocument updateAddress(UpdateAddressRequestDocument req) throws XFireFault;
	
	public GetCustomerContactResponseDocument getCustomerContact(GetCustomerContactRequestDocument req) throws XFireFault;
	
	public UpdateContactResponseDocument updateContact(UpdateContactRequestDocument req) throws XFireFault;

    */
    
}
