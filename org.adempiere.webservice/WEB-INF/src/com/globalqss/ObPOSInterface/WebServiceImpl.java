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
	
	private static String webServiceName = new String("WebService");
	
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

		ExternalSalesImpl.authenticate(username, password, webServiceName, "getCustomers");

		// TODO Auto-generated method stub

		return resdoc;
	}

}