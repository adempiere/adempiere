/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
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
/** Generated Model for C_BP_Relation
 *  @author Adempiere (generated) 
 *  @version Release 3.1.5 - $Id$ */
public class X_C_BP_Relation extends PO
{
/** Standard Constructor
@param ctx context
@param C_BP_Relation_ID id
@param trxName transaction
*/
public X_C_BP_Relation (Properties ctx, int C_BP_Relation_ID, String trxName)
{
super (ctx, C_BP_Relation_ID, trxName);
/** if (C_BP_Relation_ID == 0)
{
setC_BP_Relation_ID (0);
setC_BPartnerRelation_ID (0);
setC_BPartnerRelation_Location_ID (0);
setC_BPartner_ID (0);
setIsBillTo (false);
setIsPayFrom (false);
setIsRemitTo (false);
setIsShipTo (false);	// N
setName (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_BP_Relation (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=678 */
public static final int Table_ID=MTable.getTable_ID("C_BP_Relation");

/** TableName=C_BP_Relation */
public static final String Table_Name="C_BP_Relation";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"C_BP_Relation");

protected BigDecimal accessLevel = BigDecimal.valueOf(2);
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
StringBuffer sb = new StringBuffer ("X_C_BP_Relation[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Partner Relation.
@param C_BP_Relation_ID Business Partner Relation */
public void setC_BP_Relation_ID (int C_BP_Relation_ID)
{
if (C_BP_Relation_ID < 1) throw new IllegalArgumentException ("C_BP_Relation_ID is mandatory.");
set_ValueNoCheck ("C_BP_Relation_ID", Integer.valueOf(C_BP_Relation_ID));
}
/** Get Partner Relation.
@return Business Partner Relation */
public int getC_BP_Relation_ID() 
{
Integer ii = (Integer)get_Value("C_BP_Relation_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_BP_Relation_ID */
public static final String COLUMNNAME_C_BP_Relation_ID = "C_BP_Relation_ID";

/** C_BPartnerRelation_ID AD_Reference_ID=138 */
public static final int C_BPARTNERRELATION_ID_AD_Reference_ID=138;
/** Set Related Partner.
@param C_BPartnerRelation_ID Related Business Partner */
public void setC_BPartnerRelation_ID (int C_BPartnerRelation_ID)
{
if (C_BPartnerRelation_ID < 1) throw new IllegalArgumentException ("C_BPartnerRelation_ID is mandatory.");
set_Value ("C_BPartnerRelation_ID", Integer.valueOf(C_BPartnerRelation_ID));
}
/** Get Related Partner.
@return Related Business Partner */
public int getC_BPartnerRelation_ID() 
{
Integer ii = (Integer)get_Value("C_BPartnerRelation_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_BPartnerRelation_ID */
public static final String COLUMNNAME_C_BPartnerRelation_ID = "C_BPartnerRelation_ID";

/** C_BPartnerRelation_Location_ID AD_Reference_ID=159 */
public static final int C_BPARTNERRELATION_LOCATION_ID_AD_Reference_ID=159;
/** Set Related Partner Location.
@param C_BPartnerRelation_Location_ID Location of the related Business Partner */
public void setC_BPartnerRelation_Location_ID (int C_BPartnerRelation_Location_ID)
{
if (C_BPartnerRelation_Location_ID < 1) throw new IllegalArgumentException ("C_BPartnerRelation_Location_ID is mandatory.");
set_Value ("C_BPartnerRelation_Location_ID", Integer.valueOf(C_BPartnerRelation_Location_ID));
}
/** Get Related Partner Location.
@return Location of the related Business Partner */
public int getC_BPartnerRelation_Location_ID() 
{
Integer ii = (Integer)get_Value("C_BPartnerRelation_Location_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_BPartnerRelation_Location_ID */
public static final String COLUMNNAME_C_BPartnerRelation_Location_ID = "C_BPartnerRelation_Location_ID";
/** Set Business Partner .
@param C_BPartner_ID Identifies a Business Partner */
public void setC_BPartner_ID (int C_BPartner_ID)
{
if (C_BPartner_ID < 1) throw new IllegalArgumentException ("C_BPartner_ID is mandatory.");
set_Value ("C_BPartner_ID", Integer.valueOf(C_BPartner_ID));
}
/** Get Business Partner .
@return Identifies a Business Partner */
public int getC_BPartner_ID() 
{
Integer ii = (Integer)get_Value("C_BPartner_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_BPartner_ID */
public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";
/** Set Partner Location.
@param C_BPartner_Location_ID Identifies the (ship to) address for this Business Partner */
public void setC_BPartner_Location_ID (int C_BPartner_Location_ID)
{
if (C_BPartner_Location_ID <= 0) set_Value ("C_BPartner_Location_ID", null);
 else 
set_Value ("C_BPartner_Location_ID", Integer.valueOf(C_BPartner_Location_ID));
}
/** Get Partner Location.
@return Identifies the (ship to) address for this Business Partner */
public int getC_BPartner_Location_ID() 
{
Integer ii = (Integer)get_Value("C_BPartner_Location_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_BPartner_Location_ID */
public static final String COLUMNNAME_C_BPartner_Location_ID = "C_BPartner_Location_ID";
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
/** Column name Description */
public static final String COLUMNNAME_Description = "Description";
/** Set Invoice Address.
@param IsBillTo Business Partner Invoice/Bill Address */
public void setIsBillTo (boolean IsBillTo)
{
set_Value ("IsBillTo", Boolean.valueOf(IsBillTo));
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
/** Column name IsBillTo */
public static final String COLUMNNAME_IsBillTo = "IsBillTo";
/** Set Pay-From Address.
@param IsPayFrom Business Partner pays from that address and we'll send dunning letters there */
public void setIsPayFrom (boolean IsPayFrom)
{
set_Value ("IsPayFrom", Boolean.valueOf(IsPayFrom));
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
/** Column name IsPayFrom */
public static final String COLUMNNAME_IsPayFrom = "IsPayFrom";
/** Set Remit-To Address.
@param IsRemitTo Business Partner payment address */
public void setIsRemitTo (boolean IsRemitTo)
{
set_Value ("IsRemitTo", Boolean.valueOf(IsRemitTo));
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
/** Column name IsRemitTo */
public static final String COLUMNNAME_IsRemitTo = "IsRemitTo";
/** Set Ship Address.
@param IsShipTo Business Partner Shipment Address */
public void setIsShipTo (boolean IsShipTo)
{
set_ValueNoCheck ("IsShipTo", Boolean.valueOf(IsShipTo));
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
/** Column name IsShipTo */
public static final String COLUMNNAME_IsShipTo = "IsShipTo";
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
/** Column name Name */
public static final String COLUMNNAME_Name = "Name";
}
