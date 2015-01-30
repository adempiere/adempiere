/**********************************************************************
* This file is part of Adempiere ERP Bazaar                           *
* http://www.adempiere.org                                            *
*                                                                     *
* Copyright (C) Carlos Ruiz - globalqss                               *
* Copyright (C) Contributors                                          *
*                                                                     *
* This program is free software; you can redistribute it and/or       *
* modify it under the terms of the GNU General Public License         *
* as published by the Free Software Foundation; either version 2      *
* of the License, or (at your option) any later version.              *
*                                                                     *
* This program is distributed in the hope that it will be useful,     *
* but WITHOUT ANY WARRANTY; without even the implied warranty of      *
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the        *
* GNU General Public License for more details.                        *
*                                                                     *
* You should have received a copy of the GNU General Public License   *
* along with this program; if not, write to the Free Software         *
* Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,          *
* MA 02110-1301, USA.                                                 *
*                                                                     *
* Contributors:                                                       *
* - Carlos Ruiz  (globalqss@users.sourceforge.net)                    *
*                                                                     * 
* Sponsors:                                                           *
* - GlobalQSS (http://www.globalqss.com)                              *
***********************************************************************/

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
