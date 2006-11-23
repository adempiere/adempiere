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
/** Generated Model for M_AttributeSet
 *  @author Jorg Janke (generated) 
 *  @version Release 2.6.0a - $Id$ */
public class X_M_AttributeSet extends PO
{
/** Standard Constructor
@param ctx context
@param M_AttributeSet_ID id
@param trxName transaction
*/
public X_M_AttributeSet (Properties ctx, int M_AttributeSet_ID, String trxName)
{
super (ctx, M_AttributeSet_ID, trxName);
/** if (M_AttributeSet_ID == 0)
{
setIsGuaranteeDate (false);
setIsGuaranteeDateMandatory (false);
setIsInstanceAttribute (false);
setIsLot (false);
setIsLotMandatory (false);
setIsSerNo (false);
setIsSerNoMandatory (false);
setM_AttributeSet_ID (0);
setMandatoryType (null);
setName (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_M_AttributeSet (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=560 */
public static final int Table_ID=560;

/** TableName=M_AttributeSet */
public static final String Table_Name="M_AttributeSet";

protected static KeyNamePair Model = new KeyNamePair(560,"M_AttributeSet");

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
StringBuffer sb = new StringBuffer ("X_M_AttributeSet[").append(get_ID()).append("]");
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
/** Set Guarantee Days.
@param GuaranteeDays Number of days the product is guaranteed or available */
public void setGuaranteeDays (int GuaranteeDays)
{
set_Value ("GuaranteeDays", new Integer(GuaranteeDays));
}
/** Get Guarantee Days.
@return Number of days the product is guaranteed or available */
public int getGuaranteeDays() 
{
Integer ii = (Integer)get_Value("GuaranteeDays");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Guarantee Date.
@param IsGuaranteeDate Product has Guarantee or Expiry Date */
public void setIsGuaranteeDate (boolean IsGuaranteeDate)
{
set_Value ("IsGuaranteeDate", new Boolean(IsGuaranteeDate));
}
/** Get Guarantee Date.
@return Product has Guarantee or Expiry Date */
public boolean isGuaranteeDate() 
{
Object oo = get_Value("IsGuaranteeDate");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Mandatory Guarantee Date.
@param IsGuaranteeDateMandatory The entry of a Guarantee Date is mandatory when creating a Product Instance */
public void setIsGuaranteeDateMandatory (boolean IsGuaranteeDateMandatory)
{
set_Value ("IsGuaranteeDateMandatory", new Boolean(IsGuaranteeDateMandatory));
}
/** Get Mandatory Guarantee Date.
@return The entry of a Guarantee Date is mandatory when creating a Product Instance */
public boolean isGuaranteeDateMandatory() 
{
Object oo = get_Value("IsGuaranteeDateMandatory");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Instance Attribute.
@param IsInstanceAttribute The product attribute is specific to the instance (like Serial No, Lot or Guarantee Date) */
public void setIsInstanceAttribute (boolean IsInstanceAttribute)
{
set_Value ("IsInstanceAttribute", new Boolean(IsInstanceAttribute));
}
/** Get Instance Attribute.
@return The product attribute is specific to the instance (like Serial No, Lot or Guarantee Date) */
public boolean isInstanceAttribute() 
{
Object oo = get_Value("IsInstanceAttribute");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Lot.
@param IsLot The product instances have a Lot Number */
public void setIsLot (boolean IsLot)
{
set_Value ("IsLot", new Boolean(IsLot));
}
/** Get Lot.
@return The product instances have a Lot Number */
public boolean isLot() 
{
Object oo = get_Value("IsLot");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Mandatory Lot.
@param IsLotMandatory The entry of Lot info is mandatory when creating a Product Instance */
public void setIsLotMandatory (boolean IsLotMandatory)
{
set_Value ("IsLotMandatory", new Boolean(IsLotMandatory));
}
/** Get Mandatory Lot.
@return The entry of Lot info is mandatory when creating a Product Instance */
public boolean isLotMandatory() 
{
Object oo = get_Value("IsLotMandatory");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Serial No.
@param IsSerNo The product instances have Serial Numbers */
public void setIsSerNo (boolean IsSerNo)
{
set_Value ("IsSerNo", new Boolean(IsSerNo));
}
/** Get Serial No.
@return The product instances have Serial Numbers */
public boolean isSerNo() 
{
Object oo = get_Value("IsSerNo");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Mandatory Serial No.
@param IsSerNoMandatory The entry of a Serial No is mandatory when creating a Product Instance */
public void setIsSerNoMandatory (boolean IsSerNoMandatory)
{
set_Value ("IsSerNoMandatory", new Boolean(IsSerNoMandatory));
}
/** Get Mandatory Serial No.
@return The entry of a Serial No is mandatory when creating a Product Instance */
public boolean isSerNoMandatory() 
{
Object oo = get_Value("IsSerNoMandatory");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Lot Char End Overwrite.
@param LotCharEOverwrite Lot/Batch End Indicator overwrite - default » */
public void setLotCharEOverwrite (String LotCharEOverwrite)
{
if (LotCharEOverwrite != null && LotCharEOverwrite.length() > 1)
{
log.warning("Length > 1 - truncated");
LotCharEOverwrite = LotCharEOverwrite.substring(0,0);
}
set_Value ("LotCharEOverwrite", LotCharEOverwrite);
}
/** Get Lot Char End Overwrite.
@return Lot/Batch End Indicator overwrite - default » */
public String getLotCharEOverwrite() 
{
return (String)get_Value("LotCharEOverwrite");
}
/** Set Lot Char Start Overwrite.
@param LotCharSOverwrite Lot/Batch Start Indicator overwrite - default « */
public void setLotCharSOverwrite (String LotCharSOverwrite)
{
if (LotCharSOverwrite != null && LotCharSOverwrite.length() > 1)
{
log.warning("Length > 1 - truncated");
LotCharSOverwrite = LotCharSOverwrite.substring(0,0);
}
set_Value ("LotCharSOverwrite", LotCharSOverwrite);
}
/** Get Lot Char Start Overwrite.
@return Lot/Batch Start Indicator overwrite - default « */
public String getLotCharSOverwrite() 
{
return (String)get_Value("LotCharSOverwrite");
}
/** Set Attribute Set.
@param M_AttributeSet_ID Product Attribute Set */
public void setM_AttributeSet_ID (int M_AttributeSet_ID)
{
if (M_AttributeSet_ID < 0) throw new IllegalArgumentException ("M_AttributeSet_ID is mandatory.");
set_ValueNoCheck ("M_AttributeSet_ID", new Integer(M_AttributeSet_ID));
}
/** Get Attribute Set.
@return Product Attribute Set */
public int getM_AttributeSet_ID() 
{
Integer ii = (Integer)get_Value("M_AttributeSet_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Lot Control.
@param M_LotCtl_ID Product Lot Control */
public void setM_LotCtl_ID (int M_LotCtl_ID)
{
if (M_LotCtl_ID <= 0) set_Value ("M_LotCtl_ID", null);
 else 
set_Value ("M_LotCtl_ID", new Integer(M_LotCtl_ID));
}
/** Get Lot Control.
@return Product Lot Control */
public int getM_LotCtl_ID() 
{
Integer ii = (Integer)get_Value("M_LotCtl_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Serial No Control.
@param M_SerNoCtl_ID Product Serial Number Control */
public void setM_SerNoCtl_ID (int M_SerNoCtl_ID)
{
if (M_SerNoCtl_ID <= 0) set_Value ("M_SerNoCtl_ID", null);
 else 
set_Value ("M_SerNoCtl_ID", new Integer(M_SerNoCtl_ID));
}
/** Get Serial No Control.
@return Product Serial Number Control */
public int getM_SerNoCtl_ID() 
{
Integer ii = (Integer)get_Value("M_SerNoCtl_ID");
if (ii == null) return 0;
return ii.intValue();
}

/** MandatoryType AD_Reference_ID=324 */
public static final int MANDATORYTYPE_AD_Reference_ID=324;
/** Not Mandatary = N */
public static final String MANDATORYTYPE_NotMandatary = "N";
/** When Shipping = S */
public static final String MANDATORYTYPE_WhenShipping = "S";
/** Always Mandatory = Y */
public static final String MANDATORYTYPE_AlwaysMandatory = "Y";
/** Set Mandatory Type.
@param MandatoryType The specification of a Product Attribute Instance is mandatory */
public void setMandatoryType (String MandatoryType)
{
if (MandatoryType == null) throw new IllegalArgumentException ("MandatoryType is mandatory");
if (MandatoryType.equals("N") || MandatoryType.equals("S") || MandatoryType.equals("Y"));
 else throw new IllegalArgumentException ("MandatoryType Invalid value - " + MandatoryType + " - Reference_ID=324 - N - S - Y");
if (MandatoryType.length() > 1)
{
log.warning("Length > 1 - truncated");
MandatoryType = MandatoryType.substring(0,0);
}
set_Value ("MandatoryType", MandatoryType);
}
/** Get Mandatory Type.
@return The specification of a Product Attribute Instance is mandatory */
public String getMandatoryType() 
{
return (String)get_Value("MandatoryType");
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
/** Set SerNo Char End Overwrite.
@param SerNoCharEOverwrite Serial Number End Indicator overwrite - default empty */
public void setSerNoCharEOverwrite (String SerNoCharEOverwrite)
{
if (SerNoCharEOverwrite != null && SerNoCharEOverwrite.length() > 1)
{
log.warning("Length > 1 - truncated");
SerNoCharEOverwrite = SerNoCharEOverwrite.substring(0,0);
}
set_Value ("SerNoCharEOverwrite", SerNoCharEOverwrite);
}
/** Get SerNo Char End Overwrite.
@return Serial Number End Indicator overwrite - default empty */
public String getSerNoCharEOverwrite() 
{
return (String)get_Value("SerNoCharEOverwrite");
}
/** Set SerNo Char Start Overwrite.
@param SerNoCharSOverwrite Serial Number Start Indicator overwrite - default # */
public void setSerNoCharSOverwrite (String SerNoCharSOverwrite)
{
if (SerNoCharSOverwrite != null && SerNoCharSOverwrite.length() > 1)
{
log.warning("Length > 1 - truncated");
SerNoCharSOverwrite = SerNoCharSOverwrite.substring(0,0);
}
set_Value ("SerNoCharSOverwrite", SerNoCharSOverwrite);
}
/** Get SerNo Char Start Overwrite.
@return Serial Number Start Indicator overwrite - default # */
public String getSerNoCharSOverwrite() 
{
return (String)get_Value("SerNoCharSOverwrite");
}
}
