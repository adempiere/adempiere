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

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.namespace.QName;

import org.apache.commons.codec.binary.Base64;
import org.codehaus.xfire.fault.XFireFault;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.SecureEngine;
import org.openbravo.erpCommon.ws.externalSales.ArrayOfTns1Order;
import org.openbravo.erpCommon.ws.externalSales.ProductsCatalogResponse;
import org.openbravo.erpCommon.ws.externalSales.ProductsCatalogResponseDocument;
import org.openbravo.erpCommon.ws.externalSales.ProductsPlusCatalogResponse;
import org.openbravo.erpCommon.ws.externalSales.ProductsPlusCatalogResponseDocument;
import org.openbravo.erpCommon.ws.externalSales.UploadOrdersResponse;
import org.openbravo.erpCommon.ws.externalSales.UploadOrdersResponseDocument;

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
	
	private static String webServiceName = new String("ExternalSales");
	
	public ExternalSalesImpl()
	{
		log.info("Creating session object ExternalSales");
	}
	
	public String getVersion() {
		return "0.1.0";
	}

	public UploadOrdersResponseDocument uploadOrders(int entityId,
			int organizationId, int salesChannel, ArrayOfTns1Order newOrders, 
			String username, String password)
			throws XFireFault {
		// TODO Auto-generated method stub
		UploadOrdersResponseDocument resdoc = UploadOrdersResponseDocument.Factory.newInstance();
		UploadOrdersResponse res = resdoc.addNewUploadOrdersResponse();

		authenticate(username, password, webServiceName, "uploadOrders");

		return resdoc;
	}

	public ProductsCatalogResponseDocument getProductsCatalog(int entityId,
			int organizationId, int salesChannel, String username,
			String password) throws XFireFault {
		// TODO Auto-generated method stub
		
		ProductsCatalogResponseDocument resdoc = ProductsCatalogResponseDocument.Factory.newInstance();
		ProductsCatalogResponse res = resdoc.addNewProductsCatalogResponse();

		authenticate(username, password, webServiceName, "getProductsCatalog");

		return resdoc;
	}

	public ProductsPlusCatalogResponseDocument getProductsPlusCatalog(
			int entityId, int organizationId, int salesChannel,
			String username, String password) throws XFireFault {
		// TODO Auto-generated method stub
		ProductsPlusCatalogResponseDocument resdoc = ProductsPlusCatalogResponseDocument.Factory.newInstance();
		ProductsPlusCatalogResponse res = resdoc.addNewProductsPlusCatalogResponse();
		
		authenticate(username, password, webServiceName, "getProductsPlusCatalog");

		/*
		 * Sample of needed work
		 *  
		ArrayOfTns1ProductPlus app = res.addNewArrayOfTns1ProductPlus();
		// Iterate in M_Product for the organization / warehouse?
		
		for (MProduct product : products) ...
		
		ProductPlus prds = app.addNewProducts();
		Category cat = prds.addNewCategory();
		cat.setDescription(product.getM_Category().getDescription());
		cat.setId(product.getM_Category_ID());
		cat.setName(product.getM_Category().getName());
		Tax tax = prds.addNewTax();
		tax.setName(product.getC_Tax().getName());
		tax.setId(product.getC_Tax_ID());
		tax.setPercentage(product.getC_Tax().getRate());
		prds.setDescription(product.getDescription());
		prds.setEan(product.getEAN());
		prds.setId(product.getM_Product_ID());
		prds.setListPrice(product.getPriceList());  // we would need a price list related to the POS
		prds.setName(product.getName());
		prds.setNumber(product.getNumber());  // number?
		prds.setPurchasePrice(product.getPurchasePrice());  //  we would need a purchase price list related to the POS
		prds.setQtyonhand(product.getQtyOnHand());  // we would need a warehouse or locator related to the POS
		*/

		return resdoc;
	}

	public static void authenticate(String username, String password, String webServiceName, String method)
			throws XFireFault {
		String dbpwd = DB.getSQLValueString(null, "SELECT Password FROM AD_User WHERE Name=? AND Password IS NOT NULL", username); // and ad_client_id in (0,?)
		if (dbpwd == null || dbpwd.length() <= 0)
			throw new XFireFault("Invalid user/password", new QName("username"));

		String isencr = DB.getSQLValueString(null, "SELECT IsEncrypted FROM AD_Column WHERE AD_Column_ID=417");
		if ("Y".equals(isencr))
			dbpwd = SecureEngine.decrypt(dbpwd);
		
		String hashPassword = null;
		try {
			hashPassword = new String(Base64.encodeBase64(MessageDigest.getInstance("SHA-1").digest(dbpwd.getBytes("UTF-8"))), "ASCII");
		} catch (UnsupportedEncodingException e1) {
			throw new XFireFault("Error hashing db password", e1, new QName("username"));
		} catch (NoSuchAlgorithmException e1) {
			throw new XFireFault("Error hashing db password", e1, new QName("username"));
		}
		
		if (! hashPassword.equals(password))
			/* Invalid password */
			throw new XFireFault("Invalid user/password", new QName("password"));

		throw new XFireFault("Security not implemented yet", new QName("webServiceName"));
		// TODO: authenticate web service and method
		// TODO: Search for a service type for client and role access with the same value as the method
	}

}