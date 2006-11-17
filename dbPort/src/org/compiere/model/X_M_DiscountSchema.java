/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
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
/** Generated Model for M_DiscountSchema
 *  @author Jorg Janke (generated) 
 *  @version Release 2.5.3d - 2006-10-10 21:56:00.781 */
public class X_M_DiscountSchema extends PO
{
/** Standard Constructor
@param ctx context
@param M_DiscountSchema_ID id
@param trxName transaction
*/
public X_M_DiscountSchema (Properties ctx, int M_DiscountSchema_ID, String trxName)
{
super (ctx, M_DiscountSchema_ID, trxName);
/** if (M_DiscountSchema_ID == 0)
{
setDiscountType (null);
setIsBPartnerFlatDiscount (false);
setIsQuantityBased (true);	// Y
setM_DiscountSchema_ID (0);
setName (null);
setValidFrom (new Timestamp(System.currentTimeMillis()));
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_M_DiscountSchema (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=475 */
public static final int Table_ID=475;

/** TableName=M_DiscountSchema */
public static final String Table_Name="M_DiscountSchema";

protected static KeyNamePair Model = new KeyNamePair(475,"M_DiscountSchema");

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
StringBuffer sb = new StringBuffer ("X_M_DiscountSchema[").append(get_ID()).append("]");
return sb.toString();
}

/** CumulativeLevel AD_Reference_ID=246 */
public static final int CUMULATIVELEVEL_AD_Reference_ID=246;
/** Line = L */
public static final String CUMULATIVELEVEL_Line = "L";
/** Set Accumulation Level.
@param CumulativeLevel Level for accumulative calculations */
public void setCumulativeLevel (String CumulativeLevel)
{
if (CumulativeLevel == null) throw new IllegalArgumentException ("CumulativeLevel is mandatory");
if (CumulativeLevel == null || CumulativeLevel.equals("L"));
 else throw new IllegalArgumentException ("CumulativeLevel Invalid value - " + CumulativeLevel + " - Reference_ID=246 - L");
if (CumulativeLevel != null && CumulativeLevel.length() > 1)
{
log.warning("Length > 1 - truncated");
CumulativeLevel = CumulativeLevel.substring(0,0);
}
set_Value ("CumulativeLevel", CumulativeLevel);
}
/** Get Accumulation Level.
@return Level for accumulative calculations */
public String getCumulativeLevel() 
{
return (String)get_Value("CumulativeLevel");
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

/** DiscountType AD_Reference_ID=247 */
public static final int DISCOUNTTYPE_AD_Reference_ID=247;
/** Breaks = B */
public static final String DISCOUNTTYPE_Breaks = "B";
/** Flat Percent = F */
public static final String DISCOUNTTYPE_FlatPercent = "F";
/** Pricelist = P */
public static final String DISCOUNTTYPE_Pricelist = "P";
/** Formula = S */
public static final String DISCOUNTTYPE_Formula = "S";
/** Set Discount Type.
@param DiscountType Type of trade discount calculation */
public void setDiscountType (String DiscountType)
{
if (DiscountType == null) throw new IllegalArgumentException ("DiscountType is mandatory");
if (DiscountType.equals("B") || DiscountType.equals("F") || DiscountType.equals("P") || DiscountType.equals("S"));
 else throw new IllegalArgumentException ("DiscountType Invalid value - " + DiscountType + " - Reference_ID=247 - B - F - P - S");
if (DiscountType.length() > 1)
{
log.warning("Length > 1 - truncated");
DiscountType = DiscountType.substring(0,0);
}
set_Value ("DiscountType", DiscountType);
}
/** Get Discount Type.
@return Type of trade discount calculation */
public String getDiscountType() 
{
return (String)get_Value("DiscountType");
}
/** Set Flat Discount %.
@param FlatDiscount Flat discount percentage  */
public void setFlatDiscount (BigDecimal FlatDiscount)
{
set_Value ("FlatDiscount", FlatDiscount);
}
/** Get Flat Discount %.
@return Flat discount percentage  */
public BigDecimal getFlatDiscount() 
{
BigDecimal bd = (BigDecimal)get_Value("FlatDiscount");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set B.Partner Flat Discount.
@param IsBPartnerFlatDiscount Use flat discount defined on Business Partner Level */
public void setIsBPartnerFlatDiscount (boolean IsBPartnerFlatDiscount)
{
set_Value ("IsBPartnerFlatDiscount", new Boolean(IsBPartnerFlatDiscount));
}
/** Get B.Partner Flat Discount.
@return Use flat discount defined on Business Partner Level */
public boolean isBPartnerFlatDiscount() 
{
Object oo = get_Value("IsBPartnerFlatDiscount");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Quantity based.
@param IsQuantityBased Trade discount break level based on Quantity (not value) */
public void setIsQuantityBased (boolean IsQuantityBased)
{
set_Value ("IsQuantityBased", new Boolean(IsQuantityBased));
}
/** Get Quantity based.
@return Trade discount break level based on Quantity (not value) */
public boolean isQuantityBased() 
{
Object oo = get_Value("IsQuantityBased");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Discount Schema.
@param M_DiscountSchema_ID Schema to calculate the trade discount percentage */
public void setM_DiscountSchema_ID (int M_DiscountSchema_ID)
{
if (M_DiscountSchema_ID < 1) throw new IllegalArgumentException ("M_DiscountSchema_ID is mandatory.");
set_ValueNoCheck ("M_DiscountSchema_ID", new Integer(M_DiscountSchema_ID));
}
/** Get Discount Schema.
@return Schema to calculate the trade discount percentage */
public int getM_DiscountSchema_ID() 
{
Integer ii = (Integer)get_Value("M_DiscountSchema_ID");
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
/** Set Process Now.
@param Processing Process Now */
public void setProcessing (boolean Processing)
{
set_Value ("Processing", new Boolean(Processing));
}
/** Get Process Now.
@return Process Now */
public boolean isProcessing() 
{
Object oo = get_Value("Processing");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Script.
@param Script Dynamic Java Language Script to calculate result */
public void setScript (String Script)
{
if (Script != null && Script.length() > 2000)
{
log.warning("Length > 2000 - truncated");
Script = Script.substring(0,1999);
}
set_Value ("Script", Script);
}
/** Get Script.
@return Dynamic Java Language Script to calculate result */
public String getScript() 
{
return (String)get_Value("Script");
}
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
}
