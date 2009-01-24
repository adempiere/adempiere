package com.globalqss.ObPOSInterface;

import org.codehaus.xfire.fault.XFireFault;

import com.globalqss.obPOSInterface.GetCustomersRequest;
import com.globalqss.obPOSInterface.GetCustomersResponse;

public interface WebService {

    /* methods to implement */
	
	public GetCustomersResponse getCustomers(GetCustomersRequest req) throws XFireFault;

	/*
	public GetCustomerResponse getCustomer(GetCustomerRequest req) throws XFireFault;
	
	public GetCustomerResponse1 getCustomer(GetCustomerRequest1 req) throws XFireFault;
	
	public UpdateCustomerResponse updateCustomer(UpdateCustomerRequest req) throws XFireFault;
	
	public GetCustomerAddressesResponse getCustomerAddresses(GetCustomerAddressesRequest req) throws XFireFault;

	public GetCustomerLocationResponse getCustomerLocation(GetCustomerLocationRequest req) throws XFireFault;
	
	public UpdateAddressResponse updateAddress(UpdateAddressRequest req) throws XFireFault;
	
	public GetCustomerContactResponse getCustomerContact(GetCustomerContactRequest req) throws XFireFault;
	
	public UpdateContactResponse updateContact(UpdateContactRequest req) throws XFireFault;

    */
    
}
