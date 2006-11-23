/******************************************************************************
 * Product: Compiere ERP & CRM Smart Business Solution                        *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software;
 you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY;
 without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program;
 if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.model;

/** Generated Model - DO NOT CHANGE */
import java.util.*;
import java.sql.*;
import java.math.*;
import org.compiere.util.*;
/** Generated Model for W_Store
 *  @author Jorg Janke (generated) 
 *  @version Release 2.6.0a - $Id$ */
public class X_W_Store extends PO
{
/** Standard Constructor
@param ctx context
@param W_Store_ID id
@param trxName transaction
*/
public X_W_Store (Properties ctx, int W_Store_ID, String trxName)
{
super (ctx, W_Store_ID, trxName);
/** if (W_Store_ID == 0)
{
setC_PaymentTerm_ID (0);
setIsDefault (false);
setIsMenuAssets (true);	// Y
setIsMenuContact (true);	// Y
setIsMenuInterests (true);	// Y
setIsMenuInvoices (true);	// Y
setIsMenuOrders (true);	// Y
setIsMenuPayments (true);	// Y
setIsMenuRegistrations (true);	// Y
setIsMenuRequests (true);	// Y
setIsMenuRfQs (true);	// Y
setIsMenuShipments (true);	// Y
setM_PriceList_ID (0);
setM_Warehouse_ID (0);
setName (null);
setSalesRep_ID (0);
setURL (null);
setW_Store_ID (0);
setWebContext (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_W_Store (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=778 */
public static final int Table_ID=778;

/** TableName=W_Store */
public static final String Table_Name="W_Store";

protected static KeyNamePair Model = new KeyNamePair(778,"W_Store");

protected BigDecimal accessLevel = new BigDecimal(2);
/** AccessLevel
@return 2 - Client 
*/
protected int get_AccessLevel()
{
return accessLevel.intValue();
}
/** Load Meta Data
@param ctx context
@return PO Info
*/
protected POInfo initPO (Properties ctx)
{
POInfo poi = POInfo.getPOInfo (ctx, Table_ID);
return poi;
}
/** Info
@return info
*/
public String toString()
{
StringBuffer sb = new StringBuffer ("X_W_Store[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Payment Term.
@param C_PaymentTerm_ID The terms of Payment (timing, discount) */
public void setC_PaymentTerm_ID (int C_PaymentTerm_ID)
{
if (C_PaymentTerm_ID < 1) throw new IllegalArgumentException ("C_PaymentTerm_ID is mandatory.");
set_Value ("C_PaymentTerm_ID", new Integer(C_PaymentTerm_ID));
}
/** Get Payment Term.
@return The terms of Payment (timing, discount) */
public int getC_PaymentTerm_ID() 
{
Integer ii = (Integer)get_Value("C_PaymentTerm_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Description.
@param Description Optional short description of the record */
public void setDescription (String Description)
{
if (Description != null && Description.length() > 255)
{
log.warning("Length > 255 - truncated");
Description = Description.substring(0,254);
}
set_Value ("Description", Description);
}
/** Get Description.
@return Optional short description of the record */
public String getDescription() 
{
return (String)get_Value("Description");
}
/** Set EMail Footer.
@param EMailFooter Footer added to EMails */
public void setEMailFooter (String EMailFooter)
{
if (EMailFooter != null && EMailFooter.length() > 2000)
{
log.warning("Length > 2000 - truncated");
EMailFooter = EMailFooter.substring(0,1999);
}
set_Value ("EMailFooter", EMailFooter);
}
/** Get EMail Footer.
@return Footer added to EMails */
public String getEMailFooter() 
{
return (String)get_Value("EMailFooter");
}
/** Set EMail Header.
@param EMailHeader Header added to EMails */
public void setEMailHeader (String EMailHeader)
{
if (EMailHeader != null && EMailHeader.length() > 2000)
{
log.warning("Length > 2000 - truncated");
EMailHeader = EMailHeader.substring(0,1999);
}
set_Value ("EMailHeader", EMailHeader);
}
/** Get EMail Header.
@return Header added to EMails */
public String getEMailHeader() 
{
return (String)get_Value("EMailHeader");
}
/** Set Comment/Help.
@param Help Comment or Hint */
public void setHelp (String Help)
{
if (Help != null && Help.length() > 2000)
{
log.warning("Length > 2000 - truncated");
Help = Help.substring(0,1999);
}
set_Value ("Help", Help);
}
/** Get Comment/Help.
@return Comment or Hint */
public String getHelp() 
{
return (String)get_Value("Help");
}
/** Set Default.
@param IsDefault Default value */
public void setIsDefault (boolean IsDefault)
{
set_Value ("IsDefault", new Boolean(IsDefault));
}
/** Get Default.
@return Default value */
public boolean isDefault() 
{
Object oo = get_Value("IsDefault");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Menu Assets.
@param IsMenuAssets Show Menu Assets */
public void setIsMenuAssets (boolean IsMenuAssets)
{
set_Value ("IsMenuAssets", new Boolean(IsMenuAssets));
}
/** Get Menu Assets.
@return Show Menu Assets */
public boolean isMenuAssets() 
{
Object oo = get_Value("IsMenuAssets");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Menu Contact.
@param IsMenuContact Show Menu Contact */
public void setIsMenuContact (boolean IsMenuContact)
{
set_Value ("IsMenuContact", new Boolean(IsMenuContact));
}
/** Get Menu Contact.
@return Show Menu Contact */
public boolean isMenuContact() 
{
Object oo = get_Value("IsMenuContact");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Menu Interests.
@param IsMenuInterests Show Menu Interests */
public void setIsMenuInterests (boolean IsMenuInterests)
{
set_Value ("IsMenuInterests", new Boolean(IsMenuInterests));
}
/** Get Menu Interests.
@return Show Menu Interests */
public boolean isMenuInterests() 
{
Object oo = get_Value("IsMenuInterests");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Menu Invoices.
@param IsMenuInvoices Show Menu Invoices */
public void setIsMenuInvoices (boolean IsMenuInvoices)
{
set_Value ("IsMenuInvoices", new Boolean(IsMenuInvoices));
}
/** Get Menu Invoices.
@return Show Menu Invoices */
public boolean isMenuInvoices() 
{
Object oo = get_Value("IsMenuInvoices");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Menu Orders.
@param IsMenuOrders Show Menu Orders */
public void setIsMenuOrders (boolean IsMenuOrders)
{
set_Value ("IsMenuOrders", new Boolean(IsMenuOrders));
}
/** Get Menu Orders.
@return Show Menu Orders */
public boolean isMenuOrders() 
{
Object oo = get_Value("IsMenuOrders");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Menu Payments.
@param IsMenuPayments Show Menu Payments */
public void setIsMenuPayments (boolean IsMenuPayments)
{
set_Value ("IsMenuPayments", new Boolean(IsMenuPayments));
}
/** Get Menu Payments.
@return Show Menu Payments */
public boolean isMenuPayments() 
{
Object oo = get_Value("IsMenuPayments");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Menu Registrations.
@param IsMenuRegistrations Show Menu Registrations */
public void setIsMenuRegistrations (boolean IsMenuRegistrations)
{
set_Value ("IsMenuRegistrations", new Boolean(IsMenuRegistrations));
}
/** Get Menu Registrations.
@return Show Menu Registrations */
public boolean isMenuRegistrations() 
{
Object oo = get_Value("IsMenuRegistrations");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Menu Requests.
@param IsMenuRequests Show Menu Requests */
public void setIsMenuRequests (boolean IsMenuRequests)
{
set_Value ("IsMenuRequests", new Boolean(IsMenuRequests));
}
/** Get Menu Requests.
@return Show Menu Requests */
public boolean isMenuRequests() 
{
Object oo = get_Value("IsMenuRequests");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Menu RfQs.
@param IsMenuRfQs Show Menu RfQs */
public void setIsMenuRfQs (boolean IsMenuRfQs)
{
set_Value ("IsMenuRfQs", new Boolean(IsMenuRfQs));
}
/** Get Menu RfQs.
@return Show Menu RfQs */
public boolean isMenuRfQs() 
{
Object oo = get_Value("IsMenuRfQs");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Menu Shipments.
@param IsMenuShipments Show Menu Shipments */
public void setIsMenuShipments (boolean IsMenuShipments)
{
set_Value ("IsMenuShipments", new Boolean(IsMenuShipments));
}
/** Get Menu Shipments.
@return Show Menu Shipments */
public boolean isMenuShipments() 
{
Object oo = get_Value("IsMenuShipments");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Price List.
@param M_PriceList_ID Unique identifier of a Price List */
public void setM_PriceList_ID (int M_PriceList_ID)
{
if (M_PriceList_ID < 1) throw new IllegalArgumentException ("M_PriceList_ID is mandatory.");
set_Value ("M_PriceList_ID", new Integer(M_PriceList_ID));
}
/** Get Price List.
@return Unique identifier of a Price List */
public int getM_PriceList_ID() 
{
Integer ii = (Integer)get_Value("M_PriceList_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Warehouse.
@param M_Warehouse_ID Storage Warehouse and Service Point */
public void setM_Warehouse_ID (int M_Warehouse_ID)
{
if (M_Warehouse_ID < 1) throw new IllegalArgumentException ("M_Warehouse_ID is mandatory.");
set_Value ("M_Warehouse_ID", new Integer(M_Warehouse_ID));
}
/** Get Warehouse.
@return Storage Warehouse and Service Point */
public int getM_Warehouse_ID() 
{
Integer ii = (Integer)get_Value("M_Warehouse_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Name.
@param Name Alphanumeric identifier of the entity */
public void setName (String Name)
{
if (Name == null) throw new IllegalArgumentException ("Name is mandatory.");
if (Name.length() > 60)
{
log.warning("Length > 60 - truncated");
Name = Name.substring(0,59);
}
set_Value ("Name", Name);
}
/** Get Name.
@return Alphanumeric identifier of the entity */
public String getName() 
{
return (String)get_Value("Name");
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), getName());
}

/** SalesRep_ID AD_Reference_ID=190 */
public static final int SALESREP_ID_AD_Reference_ID=190;
/** Set Sales Representative.
@param SalesRep_ID Sales Representative or Company Agent */
public void setSalesRep_ID (int SalesRep_ID)
{
if (SalesRep_ID < 1) throw new IllegalArgumentException ("SalesRep_ID is mandatory.");
set_Value ("SalesRep_ID", new Integer(SalesRep_ID));
}
/** Get Sales Representative.
@return Sales Representative or Company Agent */
public int getSalesRep_ID() 
{
Integer ii = (Integer)get_Value("SalesRep_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Stylesheet.
@param Stylesheet CSS (Stylesheet) used */
public void setStylesheet (String Stylesheet)
{
if (Stylesheet != null && Stylesheet.length() > 60)
{
log.warning("Length > 60 - truncated");
Stylesheet = Stylesheet.substring(0,59);
}
set_Value ("Stylesheet", Stylesheet);
}
/** Get Stylesheet.
@return CSS (Stylesheet) used */
public String getStylesheet() 
{
return (String)get_Value("Stylesheet");
}
/** Set URL.
@param URL Full URL address - e.g. http://www.compiere.org */
public void setURL (String URL)
{
if (URL == null) throw new IllegalArgumentException ("URL is mandatory.");
if (URL.length() > 120)
{
log.warning("Length > 120 - truncated");
URL = URL.substring(0,119);
}
set_Value ("URL", URL);
}
/** Get URL.
@return Full URL address - e.g. http://www.compiere.org */
public String getURL() 
{
return (String)get_Value("URL");
}
/** Set Web Store EMail.
@param WStoreEMail EMail address used as the sender (From) */
public void setWStoreEMail (String WStoreEMail)
{
if (WStoreEMail != null && WStoreEMail.length() > 60)
{
log.warning("Length > 60 - truncated");
WStoreEMail = WStoreEMail.substring(0,59);
}
set_Value ("WStoreEMail", WStoreEMail);
}
/** Get Web Store EMail.
@return EMail address used as the sender (From) */
public String getWStoreEMail() 
{
return (String)get_Value("WStoreEMail");
}
/** Set WebStore User.
@param WStoreUser User ID of the Web Store EMail address */
public void setWStoreUser (String WStoreUser)
{
if (WStoreUser != null && WStoreUser.length() > 60)
{
log.warning("Length > 60 - truncated");
WStoreUser = WStoreUser.substring(0,59);
}
set_Value ("WStoreUser", WStoreUser);
}
/** Get WebStore User.
@return User ID of the Web Store EMail address */
public String getWStoreUser() 
{
return (String)get_Value("WStoreUser");
}
/** Set WebStore Password.
@param WStoreUserPW Password of the Web Store EMail address */
public void setWStoreUserPW (String WStoreUserPW)
{
if (WStoreUserPW != null && WStoreUserPW.length() > 20)
{
log.warning("Length > 20 - truncated");
WStoreUserPW = WStoreUserPW.substring(0,19);
}
set_Value ("WStoreUserPW", WStoreUserPW);
}
/** Get WebStore Password.
@return Password of the Web Store EMail address */
public String getWStoreUserPW() 
{
return (String)get_Value("WStoreUserPW");
}
/** Set Web Store.
@param W_Store_ID A Web Store of the Client */
public void setW_Store_ID (int W_Store_ID)
{
if (W_Store_ID < 1) throw new IllegalArgumentException ("W_Store_ID is mandatory.");
set_ValueNoCheck ("W_Store_ID", new Integer(W_Store_ID));
}
/** Get Web Store.
@return A Web Store of the Client */
public int getW_Store_ID() 
{
Integer ii = (Integer)get_Value("W_Store_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Web Context.
@param WebContext Web Server Context - e.g. /wstore */
public void setWebContext (String WebContext)
{
if (WebContext == null) throw new IllegalArgumentException ("WebContext is mandatory.");
if (WebContext.length() > 20)
{
log.warning("Length > 20 - truncated");
WebContext = WebContext.substring(0,19);
}
set_Value ("WebContext", WebContext);
}
/** Get Web Context.
@return Web Server Context - e.g. /wstore */
public String getWebContext() 
{
return (String)get_Value("WebContext");
}
/** Set Web Store Info.
@param WebInfo Web Store Header Information */
public void setWebInfo (String WebInfo)
{
if (WebInfo != null && WebInfo.length() > 2000)
{
log.warning("Length > 2000 - truncated");
WebInfo = WebInfo.substring(0,1999);
}
set_Value ("WebInfo", WebInfo);
}
/** Get Web Store Info.
@return Web Store Header Information */
public String getWebInfo() 
{
return (String)get_Value("WebInfo");
}
/** Set Web Order EMail.
@param WebOrderEMail EMail address to receive notifications when web orders were processed */
public void setWebOrderEMail (String WebOrderEMail)
{
if (WebOrderEMail != null && WebOrderEMail.length() > 60)
{
log.warning("Length > 60 - truncated");
WebOrderEMail = WebOrderEMail.substring(0,59);
}
set_Value ("WebOrderEMail", WebOrderEMail);
}
/** Get Web Order EMail.
@return EMail address to receive notifications when web orders were processed */
public String getWebOrderEMail() 
{
return (String)get_Value("WebOrderEMail");
}
/** Set Web Parameter 1.
@param WebParam1 Web Site Parameter 1 (default: header image) */
public void setWebParam1 (String WebParam1)
{
if (WebParam1 != null && WebParam1.length() > 2000)
{
log.warning("Length > 2000 - truncated");
WebParam1 = WebParam1.substring(0,1999);
}
set_Value ("WebParam1", WebParam1);
}
/** Get Web Parameter 1.
@return Web Site Parameter 1 (default: header image) */
public String getWebParam1() 
{
return (String)get_Value("WebParam1");
}
/** Set Web Parameter 2.
@param WebParam2 Web Site Parameter 2 (default index page) */
public void setWebParam2 (String WebParam2)
{
if (WebParam2 != null && WebParam2.length() > 2000)
{
log.warning("Length > 2000 - truncated");
WebParam2 = WebParam2.substring(0,1999);
}
set_Value ("WebParam2", WebParam2);
}
/** Get Web Parameter 2.
@return Web Site Parameter 2 (default index page) */
public String getWebParam2() 
{
return (String)get_Value("WebParam2");
}
/** Set Web Parameter 3.
@param WebParam3 Web Site Parameter 3 (default left - menu) */
public void setWebParam3 (String WebParam3)
{
if (WebParam3 != null && WebParam3.length() > 2000)
{
log.warning("Length > 2000 - truncated");
WebParam3 = WebParam3.substring(0,1999);
}
set_Value ("WebParam3", WebParam3);
}
/** Get Web Parameter 3.
@return Web Site Parameter 3 (default left - menu) */
public String getWebParam3() 
{
return (String)get_Value("WebParam3");
}
/** Set Web Parameter 4.
@param WebParam4 Web Site Parameter 4 (default footer left) */
public void setWebParam4 (String WebParam4)
{
if (WebParam4 != null && WebParam4.length() > 2000)
{
log.warning("Length > 2000 - truncated");
WebParam4 = WebParam4.substring(0,1999);
}
set_Value ("WebParam4", WebParam4);
}
/** Get Web Parameter 4.
@return Web Site Parameter 4 (default footer left) */
public String getWebParam4() 
{
return (String)get_Value("WebParam4");
}
/** Set Web Parameter 5.
@param WebParam5 Web Site Parameter 5 (default footer center) */
public void setWebParam5 (String WebParam5)
{
if (WebParam5 != null && WebParam5.length() > 2000)
{
log.warning("Length > 2000 - truncated");
WebParam5 = WebParam5.substring(0,1999);
}
set_Value ("WebParam5", WebParam5);
}
/** Get Web Parameter 5.
@return Web Site Parameter 5 (default footer center) */
public String getWebParam5() 
{
return (String)get_Value("WebParam5");
}
/** Set Web Parameter 6.
@param WebParam6 Web Site Parameter 6 (default footer right) */
public void setWebParam6 (String WebParam6)
{
if (WebParam6 != null && WebParam6.length() > 2000)
{
log.warning("Length > 2000 - truncated");
WebParam6 = WebParam6.substring(0,1999);
}
set_Value ("WebParam6", WebParam6);
}
/** Get Web Parameter 6.
@return Web Site Parameter 6 (default footer right) */
public String getWebParam6() 
{
return (String)get_Value("WebParam6");
}
}
