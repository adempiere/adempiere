/**
 *  Product: Posterita Web-Based POS and Adempiere Plugin
 *  Copyright (C) 2007  Posterita Ltd
 *  This file is part of POSterita
 *  
 *  POSterita is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program; if not, write to the Free Software Foundation, Inc.,
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
*/
package org.posterita.lib;

import org.compiere.util.Env;


public class UdiConstants 
{
    public static final String ACCOUNTING_FILE="/config/accounting/AccountingUS.csv";
	//public static int CURRENCY_EURO=102;
	public static final int CURRENCY_USD=100;
	public static final int COUNTRY_SOUTH_AFRICA=305;
	public static final int COUNTRY_MAURITIUS = 245;
	public static final int COUNTRY_US = 100;
	public static final int CURRENCY_RUPEES = 280;
  
	public static final int UOM_EACH_ID = 100;
    
    public static String POS_VERSION = "";
    public static String INVALID_LICENSING_REASON = "";
    
    public static final String THUMBNAIL_IMAGE_PREFIX = "THUMB-";
    public static final String FULL_IMAGE_PREFIX = "FULL-";
    public static final String FULL_IMAGE = "fullImage";
    public static final String THUMBNAIL = "thumbnail";
    public static final String DEFAULT_IMAGE = "Default";
    public static final String IMAGE_EXTENSION = ".jpg";
    public static final String IMPORT_AUTOMATATION_ON = "ON";
    
    public static final String CLIENT_ID_CTX_PARAM = "#AD_Client_ID";
    public static final String ORG_ID_CTX_PARAM = "#AD_Org_ID";
    public static final String CSS = "webParam5";
    public static final String FORWARD = "webParam6";
    public static final String MODULE_NAME_CTX_PARAM = "#ModuleName";
    public static final String WEBPARAM6 = "webParam6";
    public static final String PRICELIST_CTX_PARAM = "#M_PriceList_ID";
    public static final String PRICELIST_VERSION_CTX_PARAM = "#M_Pricelist_Version_ID";
    public static final String PRODUCT_CATEGORY_CTX_PARAM = "#M_Product_Category_ID";
    public static final String USER_ID_CTX_PARAM = "#AD_User_ID";
    public static final String USER_ORG_CTX_PARAM = "#User_Org";
    public static final String LANGUAGE_CTX_PARAM = "#AD_Language";
    
    public static final String WEBSTORE_DEFAULT_AD_ORG_ID_CTX_PARAM = "#AD_Org_ID_WebStoreDefault";
    
    public static final String WAREHOUSE_CTX_PARAM = "#M_Warehouse_ID";
    
    public static final String WEBSTORE_APP_SUFFIX = "WEBSTORE";
    
    public static final String REQUEST_UPDATE_EMAIL_PREFIX = "`EMAIL`";
    public static final String REQUEST_UPDATE_EMAIL_EXT = ".txt";
   
    public static final String PRINTFORMAT_ITEM_LOGO = "Logo";
    public static final String FIDELITY_BACKGROUND_IMAGE = "Fidelity";
    
    
    public static final String PAYMENTRULE_MIXED = "T";
    
    
    public static final String SHIPMENT_CHARGE = "Shipment Charge";
    
    /** Set Payment Rule.
    How you pay the invoice */
    public static final String POS_ID = Env.POS_ID;
    public static final String POS_PURCHASE_PL = "#POSPurchasePL";
    public static final String POS_PURCHASE_PL_VERSION = "#POSPurchasePLVersion";
    public static final String WEB_USER_ROLE = "WebStoreUserRole";
    public static final String ORIGINAL_IMAGE = "originalImage";
    public static final String TAMAK_ADMIN_ROLE = "posterita Admin";
    public static final String TAMAK_USER_ROLE = "posterita User";
    public static final String WEBSTORE_USER_ROLE = "WebStoreUserRole";
    
    
    public static final String EMAIL_FOOTER_MESSAGE = "Powered by Tamak ICT - CRM Module for Compiere ERP/CRM";
    
}
