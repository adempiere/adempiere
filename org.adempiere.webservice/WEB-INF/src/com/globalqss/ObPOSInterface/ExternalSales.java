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
import org.openbravo.erpCommon.ws.externalSales.ArrayOfTns1Order;
import org.openbravo.erpCommon.ws.externalSales.ProductsCatalogResponseDocument;
import org.openbravo.erpCommon.ws.externalSales.ProductsPlusCatalogResponseDocument;
import org.openbravo.erpCommon.ws.externalSales.UploadOrdersResponseDocument;

public interface ExternalSales {

    public ProductsPlusCatalogResponseDocument getProductsPlusCatalog(int entityId, int organizationId, int salesChannel, String username, String password) throws XFireFault;

	public UploadOrdersResponseDocument uploadOrders(int entityId, int organizationId, int salesChannel, ArrayOfTns1Order newOrders, String username, String password) throws XFireFault;

    public ProductsCatalogResponseDocument getProductsCatalog(int entityId, int organizationId, int salesChannel, String username, String password) throws XFireFault;

    /* methods to implement */

    /*

    public GetOrdersResponseDocument getOrders(GetOrdersRequestDocument req) throws XFireFault;
    
    */

}
