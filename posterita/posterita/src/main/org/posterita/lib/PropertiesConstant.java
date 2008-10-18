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
 * Created on 25-Mar-2005 by fred
 * 
 */
package org.posterita.lib;


public class PropertiesConstant
{
	public static final String LOGO_HOME="logo.home";
    public static final String UDI_HOME = "udi.home";
	
/*    public static final String CLIENT_NAME = "client.name";
    public static final String CLIENT_ID = "client.id";
    public static final String NTIER_HOME = "ntier.home";
    public static final String DCS_HOME = "dcs.home";
    //public static final String HONDA_HEAD_OFFICE_AUTO_ID = "honda.headoffice.auto.id";
    public static final String HONDA_HEAD_OFFICE_MOTO_ID = "honda.headoffice.moto.id";

    public static final String AUTO_METALLIC_PRICE_ID = "auto.metallic.price.list.id";
    public static final String MOTO_METALLIC_PRICE_ID = "wing.metallic.price.list.id";
    public static final String PRODUCT_CATEGORY_CAR = "productCategory.car.id";
    public static final String FACTORY_HOME = "factory.home";

    
    public static final String CSV_HOME="csv.home";
  
    public static final String ATTRIBUTE_SET_CAR = "car.attributeSet.id";
    public static final String BPARTNER_HSAF_ID = "bpartner.hsaf.id";
    public static final String USER_ROLE_SUPER_USER_ID = "user.role.superUser.id";
    public static final String ROLE_MENU_SUPER_USER_ID = "role.menu.superUser.id";
    public static final String SUPER_USER_ID = "superUser.id";
    public static final String USER_ROLE_UDI_ADMIN_ID = "user.role.udiAdmin.id";
    public static final String ROLE_MENU_UDI_ADMIN_ID = "role.menu.udiAdmin.id";
    public static final String UDI_ADMIN_ID = "udiAdmin.id";
    //public static final String HSAFAUTO_ADMIN_ID = "hsafAuto.admin.id";
    public static final String HSAFWING_ADMIN_ID = "hsafWing.Admin.id";
    public static final String ATTRIBUTE_SET_VIN_NO="vinNo.attributeVinNo.id";
    public static final String MODEL_ATTRIBUTE = "attribute.model.id";
    public static final String COLOUR_ATTRIBUTE = "attribute.colour.id";
    public static final String TRX_ATTRIBUTE = "attribute.transmisson.id";
    public static final String YEAR_ATTRIBUTE = "attribute.year.id";
    public static final String VIN_NO_ATTRIBUTE = "attribute.vinNo.id";
    public static final String UOM_EACH = "uom.each";
    public static final String TAX_CATEGORY_DEFAULT = "taxCategory.default.id";
    public static final String DEFAULT_BPGROUP = "default.bpartner.group";
    
    //ROLES
    public static final String ROLE_DEALER_PRINCIPAL_ID = "role.dealer.principal.id";
	public static final String ROLE_SALES_MANAGER_ID = "role.sales.manager.id";
	public static final String ROLE_SALES_PERSON_ID  = "role.sales.person.id";
	public static final String ROLE_STOCK_CONTROLLER_ID = "role.stock.controller.id";   
    public static final String ROLE_TEST_ID = "role.test.id";
    public static final String ROLE_SYSTEM_ADMINISTRATOR_ID = "role.system.administrator.id";
    public static final String ROLE_UDI_ADMIN_ID = "role.udiAdmin.id";
    public static final String ROLE_HSAFAUTO_ADMIN_ID = "role.hsafAutoAdmin.id";
    public static final String ROLE_HSAFWING_ADMIN_ID = "role.hsafWingAdmin.id";
    //MENUS
    public static final String PMENU_HOME_ID = "pmenu.home.id";
    public static final String PMENU_CREATEMENU_ID = "pmenu.createmenu.id";
    //testing
	public static final String SMENU_ORGANISATION_ID = "smenu.organisation.id";
	//end of test
    public static final String SMENU_WAREHOUSE_ID= "smenu.warehouse.id";
    public static final String SMENU_CREATEUSER_ID= "smenu.createuser.id";
    public static final String PMENU_STOCK_INQUIRY_ID = "pmenu.stock.inquiry.id";
    public static final String SMENU_INQUIRY_ID = "smenu.inquiry.id";
    public static final String SMENU_MY_STOCK_ID = "smenu.my.stock.id";
    
    public static final String PMENU_DEALER_ORDER_ID = "pmenu.dealer.order.id";
    public static final String SMENU_CREATE_ORDER_ID = "smenu.create.order.id";
    
    public static final String PMENU_NATIS_RELEASE_ID = "pmenu.natis.release.id";
    public static final String SMENU_RELEASE_VEHICLE_ID = "smenu.release.vehicle.id";
    
    public static final String PMENU_ALLOCATIONS_ID = "pmenu.allocations.id";
    
    public static final String PMENU_DEALER_TRANSFER_ID = "pmenu.dealer.transfer.id";
    public static final String PMENU_DEALER_ADMIN_ID = "pmenu.dealer.admin.id";
    public static final String PMENU_MESSAGES_ID = "pmenu.messages.id";
    public static final String SMENU_MESSAGESINBOX_ID="smenu.messagesinbox.id";
    public static final String SMENU_MESSAGESSENT_ID = "smenu.messagessent.id";
    public static final String SMENU_MESSAGESCOMPOSE_ID = "smenu.messagescompose.id";
    public static final String PMENU_LOGOUT_ID = "pmenu.logout.id";
    public static final String CAR_ASSET_GRP = "car.assetgroup.id";
    
    public static final String SSL_CERT_PATH = "ssl.cert.path";  
    public static final String NATIS_POST_PROCESSOR = "natis.post.processor";  
    public static final String NATIS_OPERATIONAL_MODE = "natis.operational.mode"; 
    public static final String NATIS_URL = "natis.url";
    public static final String SMTP_SERVER = "smtp.server";
    
    public static final String FTP_SERVER_URL = "ftp.server.url";
    public static final String FTP_USERNAME = "ftp.user.name";
    public static final String FTP_PASSWORD = "ftp.user.password";
    
    public static final String NATIS_AUTOMATION_TIME = "natis.automation.time";
    
    public static final String FTP_IMPORT_AUTOMATION = "ftp.import.automation";
    public static final String FTP_IMPORT_TIME = "ftp.import.time";
    
    public static final String WEBSTORE_AUTOMATED_IMPORT = "webstore.automated.import";
    public static final String WEBSTORE_AUTOMATED_TIME = "webstore.automated.time";
    public static final String WEBSTORE_IMAGE_FOLDER_TOBEPROCESSED = "webstore.folder.tobeprocessed";
    public static final String WEBSTORE_IMAGE_FOLDER_PROCESSED = "webstore.folder.processed";
    public static final String WEBSTORE_IMAGE_FOLDER_ERRORS = "webstore.folder.errors";
    public static final String WEBSTORE_IMAGE_COLLECTION_FOLDER = "webstore.collection.folder";
    public static final String WEBSTORE_IMAGE_INACTIVE_FOLDER = "webstore.inactive.folder";

    public static final String APPLICATION_TYPE = "application.type";
	public static final String SMTP_AUTH = "smtp.authorisation";
	public static final String SMTP_USERNAME = "smtp.username";
	public static final String SMTP_PASSWORD = "smtp.password";
*/}
