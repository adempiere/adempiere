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
/** Generated Model for M_AttributeSetInstance
 *  @author Adempiere (generated) 
 *  @version Release 3.2.0 - $Id$ */
public class X_M_AttributeSetInstance extends PO
{
/** Standard Constructor
@param ctx context
@param M_AttributeSetInstance_ID id
@param trxName transaction
*/
public X_M_AttributeSetInstance (Properties ctx, int M_AttributeSetInstance_ID, String trxName)
{
super (ctx, M_AttributeSetInstance_ID, trxName);
/** if (M_AttributeSetInstance_ID == 0)
{
setM_AttributeSetInstance_ID (0);
setM_AttributeSet_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_M_AttributeSetInstance (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=559 */
public static final int Table_ID=MTable.getTable_ID("M_AttributeSetInstance");

/** TableName=M_AttributeSetInstance */
public static final String Table_Name="M_AttributeSetInstance";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"M_AttributeSetInstance");

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
StringBuffer sb = new StringBuffer ("X_M_AttributeSetInstance[").append(get_ID()).append("]");
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
/** Set Guarantee Date.
@param GuaranteeDate Date when guarantee expires */
public void setGuaranteeDate (Timestamp GuaranteeDate)
{
set_Value ("GuaranteeDate", GuaranteeDate);
}
/** Get Guarantee Date.
@return Date when guarantee expires */
public Timestamp getGuaranteeDate() 
{
return (Timestamp)get_Value("GuaranteeDate");
}
/** Column name GuaranteeDate */
public static final String COLUMNNAME_GuaranteeDate = "GuaranteeDate";
/** Set Lot No.
@param Lot Lot number (alphanumeric) */
public void setLot (String Lot)
{
if (Lot != null && Lot.length() > 40)
{
log.warning("Length > 40 - truncated");
Lot = Lot.substring(0,39);
}
set_Value ("Lot", Lot);
}
/** Get Lot No.
@return Lot number (alphanumeric) */
public String getLot() 
{
return (String)get_Value("Lot");
}
/** Column name Lot */
public static final String COLUMNNAME_Lot = "Lot";
/** Set Attribute Set Instance.
@param M_AttributeSetInstance_ID Product Attribute Set Instance */
public void setM_AttributeSetInstance_ID (int M_AttributeSetInstance_ID)
{
if (M_AttributeSetInstance_ID < 0) throw new IllegalArgumentException ("M_AttributeSetInstance_ID is mandatory.");
set_ValueNoCheck ("M_AttributeSetInstance_ID", Integer.valueOf(M_AttributeSetInstance_ID));
}
/** Get Attribute Set Instance.
@return Product Attribute Set Instance */
public int getM_AttributeSetInstance_ID() 
{
Integer ii = (Integer)get_Value("M_AttributeSetInstance_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getM_AttributeSetInstance_ID()));
}
/** Column name M_AttributeSetInstance_ID */
public static final String COLUMNNAME_M_AttributeSetInstance_ID = "M_AttributeSetInstance_ID";
/** Set Attribute Set.
@param M_AttributeSet_ID Product Attribute Set */
public void setM_AttributeSet_ID (int M_AttributeSet_ID)
{
if (M_AttributeSet_ID < 0) throw new IllegalArgumentException ("M_AttributeSet_ID is mandatory.");
set_Value ("M_AttributeSet_ID", Integer.valueOf(M_AttributeSet_ID));
}
/** Get Attribute Set.
@return Product Attribute Set */
public int getM_AttributeSet_ID() 
{
Integer ii = (Integer)get_Value("M_AttributeSet_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name M_AttributeSet_ID */
public static final String COLUMNNAME_M_AttributeSet_ID = "M_AttributeSet_ID";
/** Set Lot.
@param M_Lot_ID Product Lot Definition */
public void setM_Lot_ID (int M_Lot_ID)
{
if (M_Lot_ID <= 0) set_Value ("M_Lot_ID", null);
 else 
set_Value ("M_Lot_ID", Integer.valueOf(M_Lot_ID));
}
/** Get Lot.
@return Product Lot Definition */
public int getM_Lot_ID() 
{
Integer ii = (Integer)get_Value("M_Lot_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name M_Lot_ID */
public static final String COLUMNNAME_M_Lot_ID = "M_Lot_ID";
/** Set Serial No.
@param SerNo Product Serial Number  */
public void setSerNo (String SerNo)
{
if (SerNo != null && SerNo.length() > 40)
{
log.warning("Length > 40 - truncated");
SerNo = SerNo.substring(0,39);
}
set_Value ("SerNo", SerNo);
}
/** Get Serial No.
@return Product Serial Number  */
public String getSerNo() 
{
return (String)get_Value("SerNo");
}
/** Column name SerNo */
public static final String COLUMNNAME_SerNo = "SerNo";
}
