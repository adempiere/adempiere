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
/** Generated Model for C_BPartner_Location
 *  @author Jorg Janke (generated) 
 *  @version Release 2.6.0a - $Id$ */
public class X_C_BPartner_Location extends PO
{
/** Standard Constructor
@param ctx context
@param C_BPartner_Location_ID id
@param trxName transaction
*/
public X_C_BPartner_Location (Properties ctx, int C_BPartner_Location_ID, String trxName)
{
super (ctx, C_BPartner_Location_ID, trxName);
/** if (C_BPartner_Location_ID == 0)
{
setC_BPartner_ID (0);
setC_BPartner_Location_ID (0);
setC_Location_ID (0);
setIsBillTo (true);	// Y
setIsPayFrom (true);	// Y
setIsRemitTo (true);	// Y
setIsShipTo (true);	// Y
setName (null);	// .
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_BPartner_Location (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=293 */
public static final int Table_ID=293;

/** TableName=C_BPartner_Location */
public static final String Table_Name="C_BPartner_Location";

protected static KeyNamePair Model = new KeyNamePair(293,"C_BPartner_Location");

protected BigDecimal accessLevel = new BigDecimal(3);
/** AccessLevel
@return 3 - Client - Org 
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
StringBuffer sb = new StringBuffer ("X_C_BPartner_Location[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Business Partner .
@param C_BPartner_ID Identifies a Business Partner */
public void setC_BPartner_ID (int C_BPartner_ID)
{
if (C_BPartner_ID < 1) throw new IllegalArgumentException ("C_BPartner_ID is mandatory.");
set_ValueNoCheck ("C_BPartner_ID", new Integer(C_BPartner_ID));
}
/** Get Business Partner .
@return Identifies a Business Partner */
public int getC_BPartner_ID() 
{
Integer ii = (Integer)get_Value("C_BPartner_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Partner Location.
@param C_BPartner_Location_ID Identifies the (ship to) address for this Business Partner */
public void setC_BPartner_Location_ID (int C_BPartner_Location_ID)
{
if (C_BPartner_Location_ID < 1) throw new IllegalArgumentException ("C_BPartner_Location_ID is mandatory.");
set_ValueNoCheck ("C_BPartner_Location_ID", new Integer(C_BPartner_Location_ID));
}
/** Get Partner Location.
@return Identifies the (ship to) address for this Business Partner */
public int getC_BPartner_Location_ID() 
{
Integer ii = (Integer)get_Value("C_BPartner_Location_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Address.
@param C_Location_ID Location or Address */
public void setC_Location_ID (int C_Location_ID)
{
if (C_Location_ID < 1) throw new IllegalArgumentException ("C_Location_ID is mandatory.");
set_Value ("C_Location_ID", new Integer(C_Location_ID));
}
/** Get Address.
@return Location or Address */
public int getC_Location_ID() 
{
Integer ii = (Integer)get_Value("C_Location_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Sales Region.
@param C_SalesRegion_ID Sales coverage region */
public void setC_SalesRegion_ID (int C_SalesRegion_ID)
{
if (C_SalesRegion_ID <= 0) set_Value ("C_SalesRegion_ID", null);
 else 
set_Value ("C_SalesRegion_ID", new Integer(C_SalesRegion_ID));
}
/** Get Sales Region.
@return Sales coverage region */
public int getC_SalesRegion_ID() 
{
Integer ii = (Integer)get_Value("C_SalesRegion_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Fax.
@param Fax Facsimile number */
public void setFax (String Fax)
{
if (Fax != null && Fax.length() > 40)
{
log.warning("Length > 40 - truncated");
Fax = Fax.substring(0,39);
}
set_Value ("Fax", Fax);
}
/** Get Fax.
@return Facsimile number */
public String getFax() 
{
return (String)get_Value("Fax");
}
/** Set ISDN.
@param ISDN ISDN or modem line */
public void setISDN (String ISDN)
{
if (ISDN != null && ISDN.length() > 40)
{
log.warning("Length > 40 - truncated");
ISDN = ISDN.substring(0,39);
}
set_Value ("ISDN", ISDN);
}
/** Get ISDN.
@return ISDN or modem line */
public String getISDN() 
{
return (String)get_Value("ISDN");
}
/** Set Invoice Address.
@param IsBillTo Business Partner Invoice/Bill Address */
public void setIsBillTo (boolean IsBillTo)
{
set_Value ("IsBillTo", new Boolean(IsBillTo));
}
/** Get Invoice Address.
@return Business Partner Invoice/Bill Address */
public boolean isBillTo() 
{
Object oo = get_Value("IsBillTo");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Pay-From Address.
@param IsPayFrom Business Partner pays from that address and we'll send dunning letters there */
public void setIsPayFrom (boolean IsPayFrom)
{
set_Value ("IsPayFrom", new Boolean(IsPayFrom));
}
/** Get Pay-From Address.
@return Business Partner pays from that address and we'll send dunning letters there */
public boolean isPayFrom() 
{
Object oo = get_Value("IsPayFrom");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Remit-To Address.
@param IsRemitTo Business Partner payment address */
public void setIsRemitTo (boolean IsRemitTo)
{
set_Value ("IsRemitTo", new Boolean(IsRemitTo));
}
/** Get Remit-To Address.
@return Business Partner payment address */
public boolean isRemitTo() 
{
Object oo = get_Value("IsRemitTo");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Ship Address.
@param IsShipTo Business Partner Shipment Address */
public void setIsShipTo (boolean IsShipTo)
{
set_Value ("IsShipTo", new Boolean(IsShipTo));
}
/** Get Ship Address.
@return Business Partner Shipment Address */
public boolean isShipTo() 
{
Object oo = get_Value("IsShipTo");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
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
/** Set Phone.
@param Phone Identifies a telephone number */
public void setPhone (String Phone)
{
if (Phone != null && Phone.length() > 40)
{
log.warning("Length > 40 - truncated");
Phone = Phone.substring(0,39);
}
set_Value ("Phone", Phone);
}
/** Get Phone.
@return Identifies a telephone number */
public String getPhone() 
{
return (String)get_Value("Phone");
}
/** Set 2nd Phone.
@param Phone2 Identifies an alternate telephone number. */
public void setPhone2 (String Phone2)
{
if (Phone2 != null && Phone2.length() > 40)
{
log.warning("Length > 40 - truncated");
Phone2 = Phone2.substring(0,39);
}
set_Value ("Phone2", Phone2);
}
/** Get 2nd Phone.
@return Identifies an alternate telephone number. */
public String getPhone2() 
{
return (String)get_Value("Phone2");
}
}
