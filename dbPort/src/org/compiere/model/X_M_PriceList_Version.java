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
/** Generated Model for M_PriceList_Version
 *  @author Adempiere (generated) 
 *  @version Release 3.2.0 - $Id$ */
public class X_M_PriceList_Version extends PO
{
/** Standard Constructor
@param ctx context
@param M_PriceList_Version_ID id
@param trxName transaction
*/
public X_M_PriceList_Version (Properties ctx, int M_PriceList_Version_ID, String trxName)
{
super (ctx, M_PriceList_Version_ID, trxName);
/** if (M_PriceList_Version_ID == 0)
{
setM_DiscountSchema_ID (0);
setM_PriceList_ID (0);
setM_PriceList_Version_ID (0);
setName (null);	// @#Date@
setValidFrom (new Timestamp(System.currentTimeMillis()));	// @#Date@
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_M_PriceList_Version (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=295 */
public static final int Table_ID=MTable.getTable_ID("M_PriceList_Version");

/** TableName=M_PriceList_Version */
public static final String Table_Name="M_PriceList_Version";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"M_PriceList_Version");

protected BigDecimal accessLevel = BigDecimal.valueOf(3);
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
StringBuffer sb = new StringBuffer ("X_M_PriceList_Version[").append(get_ID()).append("]");
return sb.toString();
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
/** Column name Description */
public static final String COLUMNNAME_Description = "Description";
/** Set Discount Schema.
@param M_DiscountSchema_ID Schema to calculate the trade discount percentage */
public void setM_DiscountSchema_ID (int M_DiscountSchema_ID)
{
if (M_DiscountSchema_ID < 1) throw new IllegalArgumentException ("M_DiscountSchema_ID is mandatory.");
set_Value ("M_DiscountSchema_ID", Integer.valueOf(M_DiscountSchema_ID));
}
/** Get Discount Schema.
@return Schema to calculate the trade discount percentage */
public int getM_DiscountSchema_ID() 
{
Integer ii = (Integer)get_Value("M_DiscountSchema_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name M_DiscountSchema_ID */
public static final String COLUMNNAME_M_DiscountSchema_ID = "M_DiscountSchema_ID";
/** Set Price List.
@param M_PriceList_ID Unique identifier of a Price List */
public void setM_PriceList_ID (int M_PriceList_ID)
{
if (M_PriceList_ID < 1) throw new IllegalArgumentException ("M_PriceList_ID is mandatory.");
set_ValueNoCheck ("M_PriceList_ID", Integer.valueOf(M_PriceList_ID));
}
/** Get Price List.
@return Unique identifier of a Price List */
public int getM_PriceList_ID() 
{
Integer ii = (Integer)get_Value("M_PriceList_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name M_PriceList_ID */
public static final String COLUMNNAME_M_PriceList_ID = "M_PriceList_ID";
/** Set Price List Version.
@param M_PriceList_Version_ID Identifies a unique instance of a Price List */
public void setM_PriceList_Version_ID (int M_PriceList_Version_ID)
{
if (M_PriceList_Version_ID < 1) throw new IllegalArgumentException ("M_PriceList_Version_ID is mandatory.");
set_ValueNoCheck ("M_PriceList_Version_ID", Integer.valueOf(M_PriceList_Version_ID));
}
/** Get Price List Version.
@return Identifies a unique instance of a Price List */
public int getM_PriceList_Version_ID() 
{
Integer ii = (Integer)get_Value("M_PriceList_Version_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name M_PriceList_Version_ID */
public static final String COLUMNNAME_M_PriceList_Version_ID = "M_PriceList_Version_ID";

/** M_Pricelist_Version_Base_ID AD_Reference_ID=188 */
public static final int M_PRICELIST_VERSION_BASE_ID_AD_Reference_ID=188;
/** Set Base Price List.
@param M_Pricelist_Version_Base_ID Source for Price list calculations */
public void setM_Pricelist_Version_Base_ID (int M_Pricelist_Version_Base_ID)
{
if (M_Pricelist_Version_Base_ID <= 0) set_Value ("M_Pricelist_Version_Base_ID", null);
 else 
set_Value ("M_Pricelist_Version_Base_ID", Integer.valueOf(M_Pricelist_Version_Base_ID));
}
/** Get Base Price List.
@return Source for Price list calculations */
public int getM_Pricelist_Version_Base_ID() 
{
Integer ii = (Integer)get_Value("M_Pricelist_Version_Base_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name M_Pricelist_Version_Base_ID */
public static final String COLUMNNAME_M_Pricelist_Version_Base_ID = "M_Pricelist_Version_Base_ID";
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
/** Set Create.
@param ProcCreate Create */
public void setProcCreate (String ProcCreate)
{
if (ProcCreate != null && ProcCreate.length() > 1)
{
log.warning("Length > 1 - truncated");
ProcCreate = ProcCreate.substring(0,0);
}
set_Value ("ProcCreate", ProcCreate);
}
/** Get Create.
@return Create */
public String getProcCreate() 
{
return (String)get_Value("ProcCreate");
}
/** Column name ProcCreate */
public static final String COLUMNNAME_ProcCreate = "ProcCreate";
/** Set Valid from.
@param ValidFrom Valid from including this date (first day) */
public void setValidFrom (Timestamp ValidFrom)
{
if (ValidFrom == null) throw new IllegalArgumentException ("ValidFrom is mandatory.");
set_Value ("ValidFrom", ValidFrom);
}
/** Get Valid from.
@return Valid from including this date (first day) */
public Timestamp getValidFrom() 
{
return (Timestamp)get_Value("ValidFrom");
}
/** Column name ValidFrom */
public static final String COLUMNNAME_ValidFrom = "ValidFrom";
}
