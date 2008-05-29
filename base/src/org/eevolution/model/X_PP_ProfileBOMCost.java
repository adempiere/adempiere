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
package org.eevolution.model;

/** Generated Model - DO NOT CHANGE */
import org.compiere.model.*;
import java.util.*;
import java.sql.*;
import java.math.*;
import org.compiere.util.*;
/** Generated Model for PP_ProfileBOMCost
 *  @author Adempiere (generated) 
 *  @version Release 3.3.0 - $Id$ */
public class X_PP_ProfileBOMCost extends PO
{
/** Standard Constructor
@param ctx context
@param PP_ProfileBOMCost_ID id
@param trxName transaction
*/
public X_PP_ProfileBOMCost (Properties ctx, int PP_ProfileBOMCost_ID, String trxName)
{
super (ctx, PP_ProfileBOMCost_ID, trxName);
/** if (PP_ProfileBOMCost_ID == 0)
{
setPP_ProfileBOMCost_ID (0);
setPP_ProfileBOM_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_PP_ProfileBOMCost (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** TableName=PP_ProfileBOMCost */
public static final String Table_Name="PP_ProfileBOMCost";

/** AD_Table_ID=50015 */
public static final int Table_ID=MTable.getTable_ID(Table_Name);

protected static KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

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
StringBuffer sb = new StringBuffer ("X_PP_ProfileBOMCost[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Date Ordered.
@param DateOrdered Date of Order */
public void setDateOrdered (Timestamp DateOrdered)
{
set_Value ("DateOrdered", DateOrdered);
}
/** Get Date Ordered.
@return Date of Order */
public Timestamp getDateOrdered() 
{
return (Timestamp)get_Value("DateOrdered");
}
/** Column name DateOrdered */
public static final String COLUMNNAME_DateOrdered = "DateOrdered";
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
/** Set Generate To.
@param GenerateTo Generate To */
public void setGenerateTo (String GenerateTo)
{
if (GenerateTo != null && GenerateTo.length() > 1)
{
log.warning("Length > 1 - truncated");
GenerateTo = GenerateTo.substring(0,0);
}
set_Value ("GenerateTo", GenerateTo);
}
/** Get Generate To.
@return Generate To */
public String getGenerateTo() 
{
return (String)get_Value("GenerateTo");
}
/** Column name GenerateTo */
public static final String COLUMNNAME_GenerateTo = "GenerateTo";
/** Set IsPacking.
@param IsPacking IsPacking */
public void setIsPacking (boolean IsPacking)
{
set_Value ("IsPacking", Boolean.valueOf(IsPacking));
}
/** Get IsPacking.
@return IsPacking */
public boolean isPacking() 
{
Object oo = get_Value("IsPacking");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsPacking */
public static final String COLUMNNAME_IsPacking = "IsPacking";
/** Set IsUSD.
@param IsUSD IsUSD */
public void setIsUSD (boolean IsUSD)
{
set_Value ("IsUSD", Boolean.valueOf(IsUSD));
}
/** Get IsUSD.
@return IsUSD */
public boolean isUSD() 
{
Object oo = get_Value("IsUSD");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsUSD */
public static final String COLUMNNAME_IsUSD = "IsUSD";
/** Set Line Amount.
@param LineNetAmt Line Extended Amount (Quantity * Actual Price) without Freight and Charges */
public void setLineNetAmt (BigDecimal LineNetAmt)
{
set_Value ("LineNetAmt", LineNetAmt);
}
/** Get Line Amount.
@return Line Extended Amount (Quantity * Actual Price) without Freight and Charges */
public BigDecimal getLineNetAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("LineNetAmt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name LineNetAmt */
public static final String COLUMNNAME_LineNetAmt = "LineNetAmt";
/** Set Line Total.
@param LineTotalAmt Total line amount incl. Tax */
public void setLineTotalAmt (BigDecimal LineTotalAmt)
{
set_Value ("LineTotalAmt", LineTotalAmt);
}
/** Get Line Total.
@return Total line amount incl. Tax */
public BigDecimal getLineTotalAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("LineTotalAmt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name LineTotalAmt */
public static final String COLUMNNAME_LineTotalAmt = "LineTotalAmt";
/** Set Price List.
@param M_PriceList_ID Unique identifier of a Price List */
public void setM_PriceList_ID (int M_PriceList_ID)
{
if (M_PriceList_ID <= 0) set_Value ("M_PriceList_ID", null);
 else 
set_Value ("M_PriceList_ID", Integer.valueOf(M_PriceList_ID));
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
/** Set M_ProductEt_ID.
@param M_ProductEt_ID M_ProductEt_ID */
public void setM_ProductEt_ID (int M_ProductEt_ID)
{
if (M_ProductEt_ID <= 0) set_Value ("M_ProductEt_ID", null);
 else 
set_Value ("M_ProductEt_ID", Integer.valueOf(M_ProductEt_ID));
}
/** Get M_ProductEt_ID.
@return M_ProductEt_ID */
public int getM_ProductEt_ID() 
{
Integer ii = (Integer)get_Value("M_ProductEt_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name M_ProductEt_ID */
public static final String COLUMNNAME_M_ProductEt_ID = "M_ProductEt_ID";
/** Set Margin %.
@param Margin Margin for a product as a percentage */
public void setMargin (BigDecimal Margin)
{
set_Value ("Margin", Margin);
}
/** Get Margin %.
@return Margin for a product as a percentage */
public BigDecimal getMargin() 
{
BigDecimal bd = (BigDecimal)get_Value("Margin");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name Margin */
public static final String COLUMNNAME_Margin = "Margin";
/** Set Offer Amount.
@param OfferAmt Amount of the Offer */
public void setOfferAmt (BigDecimal OfferAmt)
{
set_Value ("OfferAmt", OfferAmt);
}
/** Get Offer Amount.
@return Amount of the Offer */
public BigDecimal getOfferAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("OfferAmt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name OfferAmt */
public static final String COLUMNNAME_OfferAmt = "OfferAmt";
/** Set Profile BOM Cost.
@param PP_ProfileBOMCost_ID Profile BOM Cost */
public void setPP_ProfileBOMCost_ID (int PP_ProfileBOMCost_ID)
{
if (PP_ProfileBOMCost_ID < 1) throw new IllegalArgumentException ("PP_ProfileBOMCost_ID is mandatory.");
set_ValueNoCheck ("PP_ProfileBOMCost_ID", Integer.valueOf(PP_ProfileBOMCost_ID));
}
/** Get Profile BOM Cost.
@return Profile BOM Cost */
public int getPP_ProfileBOMCost_ID() 
{
Integer ii = (Integer)get_Value("PP_ProfileBOMCost_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name PP_ProfileBOMCost_ID */
public static final String COLUMNNAME_PP_ProfileBOMCost_ID = "PP_ProfileBOMCost_ID";
/** Set Profile BOM.
@param PP_ProfileBOM_ID Profile BOM */
public void setPP_ProfileBOM_ID (int PP_ProfileBOM_ID)
{
if (PP_ProfileBOM_ID < 1) throw new IllegalArgumentException ("PP_ProfileBOM_ID is mandatory.");
set_ValueNoCheck ("PP_ProfileBOM_ID", Integer.valueOf(PP_ProfileBOM_ID));
}
/** Get Profile BOM.
@return Profile BOM */
public int getPP_ProfileBOM_ID() 
{
Integer ii = (Integer)get_Value("PP_ProfileBOM_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name PP_ProfileBOM_ID */
public static final String COLUMNNAME_PP_ProfileBOM_ID = "PP_ProfileBOM_ID";
/** Set QtyEt.
@param QtyEt QtyEt */
public void setQtyEt (BigDecimal QtyEt)
{
set_Value ("QtyEt", QtyEt);
}
/** Get QtyEt.
@return QtyEt */
public BigDecimal getQtyEt() 
{
BigDecimal bd = (BigDecimal)get_Value("QtyEt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name QtyEt */
public static final String COLUMNNAME_QtyEt = "QtyEt";
/** Set Ordered Quantity.
@param QtyOrdered Ordered Quantity */
public void setQtyOrdered (BigDecimal QtyOrdered)
{
set_Value ("QtyOrdered", QtyOrdered);
}
/** Get Ordered Quantity.
@return Ordered Quantity */
public BigDecimal getQtyOrdered() 
{
BigDecimal bd = (BigDecimal)get_Value("QtyOrdered");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name QtyOrdered */
public static final String COLUMNNAME_QtyOrdered = "QtyOrdered";
/** Set Resource.
@param S_Resource_ID Resource */
public void setS_Resource_ID (int S_Resource_ID)
{
if (S_Resource_ID <= 0) set_Value ("S_Resource_ID", null);
 else 
set_Value ("S_Resource_ID", Integer.valueOf(S_Resource_ID));
}
/** Get Resource.
@return Resource */
public int getS_Resource_ID() 
{
Integer ii = (Integer)get_Value("S_Resource_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name S_Resource_ID */
public static final String COLUMNNAME_S_Resource_ID = "S_Resource_ID";
}
