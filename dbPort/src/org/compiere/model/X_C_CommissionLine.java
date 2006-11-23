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
/** Generated Model for C_CommissionLine
 *  @author Jorg Janke (generated) 
 *  @version Release 2.6.0a - $Id$ */
public class X_C_CommissionLine extends PO
{
/** Standard Constructor
@param ctx context
@param C_CommissionLine_ID id
@param trxName transaction
*/
public X_C_CommissionLine (Properties ctx, int C_CommissionLine_ID, String trxName)
{
super (ctx, C_CommissionLine_ID, trxName);
/** if (C_CommissionLine_ID == 0)
{
setAmtMultiplier (Env.ZERO);
setAmtSubtract (Env.ZERO);
setC_CommissionLine_ID (0);
setC_Commission_ID (0);
setCommissionOrders (false);
setIsPositiveOnly (false);
setLine (0);	// @SQL=SELECT NVL(MAX(Line),0)+10 AS DefaultValue FROM C_CommissionLine WHERE C_Commission_ID=@C_Commission_ID@
setQtyMultiplier (Env.ZERO);
setQtySubtract (Env.ZERO);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_CommissionLine (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=431 */
public static final int Table_ID=431;

/** TableName=C_CommissionLine */
public static final String Table_Name="C_CommissionLine";

protected static KeyNamePair Model = new KeyNamePair(431,"C_CommissionLine");

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
StringBuffer sb = new StringBuffer ("X_C_CommissionLine[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Multiplier Amount.
@param AmtMultiplier Multiplier Amount for generating commissions */
public void setAmtMultiplier (BigDecimal AmtMultiplier)
{
if (AmtMultiplier == null) throw new IllegalArgumentException ("AmtMultiplier is mandatory.");
set_Value ("AmtMultiplier", AmtMultiplier);
}
/** Get Multiplier Amount.
@return Multiplier Amount for generating commissions */
public BigDecimal getAmtMultiplier() 
{
BigDecimal bd = (BigDecimal)get_Value("AmtMultiplier");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Subtract Amount.
@param AmtSubtract Subtract Amount for generating commissions */
public void setAmtSubtract (BigDecimal AmtSubtract)
{
if (AmtSubtract == null) throw new IllegalArgumentException ("AmtSubtract is mandatory.");
set_Value ("AmtSubtract", AmtSubtract);
}
/** Get Subtract Amount.
@return Subtract Amount for generating commissions */
public BigDecimal getAmtSubtract() 
{
BigDecimal bd = (BigDecimal)get_Value("AmtSubtract");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Business Partner Group.
@param C_BP_Group_ID Business Partner Group */
public void setC_BP_Group_ID (int C_BP_Group_ID)
{
if (C_BP_Group_ID <= 0) set_Value ("C_BP_Group_ID", null);
 else 
set_Value ("C_BP_Group_ID", new Integer(C_BP_Group_ID));
}
/** Get Business Partner Group.
@return Business Partner Group */
public int getC_BP_Group_ID() 
{
Integer ii = (Integer)get_Value("C_BP_Group_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Business Partner .
@param C_BPartner_ID Identifies a Business Partner */
public void setC_BPartner_ID (int C_BPartner_ID)
{
if (C_BPartner_ID <= 0) set_Value ("C_BPartner_ID", null);
 else 
set_Value ("C_BPartner_ID", new Integer(C_BPartner_ID));
}
/** Get Business Partner .
@return Identifies a Business Partner */
public int getC_BPartner_ID() 
{
Integer ii = (Integer)get_Value("C_BPartner_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Commission Line.
@param C_CommissionLine_ID Commission Line */
public void setC_CommissionLine_ID (int C_CommissionLine_ID)
{
if (C_CommissionLine_ID < 1) throw new IllegalArgumentException ("C_CommissionLine_ID is mandatory.");
set_ValueNoCheck ("C_CommissionLine_ID", new Integer(C_CommissionLine_ID));
}
/** Get Commission Line.
@return Commission Line */
public int getC_CommissionLine_ID() 
{
Integer ii = (Integer)get_Value("C_CommissionLine_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Commission.
@param C_Commission_ID Commission */
public void setC_Commission_ID (int C_Commission_ID)
{
if (C_Commission_ID < 1) throw new IllegalArgumentException ("C_Commission_ID is mandatory.");
set_ValueNoCheck ("C_Commission_ID", new Integer(C_Commission_ID));
}
/** Get Commission.
@return Commission */
public int getC_Commission_ID() 
{
Integer ii = (Integer)get_Value("C_Commission_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getC_Commission_ID()));
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
/** Set Commission only specified Orders.
@param CommissionOrders Commission only Orders or Invoices, where this Sales Rep is entered */
public void setCommissionOrders (boolean CommissionOrders)
{
set_Value ("CommissionOrders", new Boolean(CommissionOrders));
}
/** Get Commission only specified Orders.
@return Commission only Orders or Invoices, where this Sales Rep is entered */
public boolean isCommissionOrders() 
{
Object oo = get_Value("CommissionOrders");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
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
/** Set Positive only.
@param IsPositiveOnly Do not generate negative commissions */
public void setIsPositiveOnly (boolean IsPositiveOnly)
{
set_Value ("IsPositiveOnly", new Boolean(IsPositiveOnly));
}
/** Get Positive only.
@return Do not generate negative commissions */
public boolean isPositiveOnly() 
{
Object oo = get_Value("IsPositiveOnly");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Line No.
@param Line Unique line for this document */
public void setLine (int Line)
{
set_Value ("Line", new Integer(Line));
}
/** Get Line No.
@return Unique line for this document */
public int getLine() 
{
Integer ii = (Integer)get_Value("Line");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Product Category.
@param M_Product_Category_ID Category of a Product */
public void setM_Product_Category_ID (int M_Product_Category_ID)
{
if (M_Product_Category_ID <= 0) set_Value ("M_Product_Category_ID", null);
 else 
set_Value ("M_Product_Category_ID", new Integer(M_Product_Category_ID));
}
/** Get Product Category.
@return Category of a Product */
public int getM_Product_Category_ID() 
{
Integer ii = (Integer)get_Value("M_Product_Category_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Product.
@param M_Product_ID Product, Service, Item */
public void setM_Product_ID (int M_Product_ID)
{
if (M_Product_ID <= 0) set_Value ("M_Product_ID", null);
 else 
set_Value ("M_Product_ID", new Integer(M_Product_ID));
}
/** Get Product.
@return Product, Service, Item */
public int getM_Product_ID() 
{
Integer ii = (Integer)get_Value("M_Product_ID");
if (ii == null) return 0;
return ii.intValue();
}

/** Org_ID AD_Reference_ID=130 */
public static final int ORG_ID_AD_Reference_ID=130;
/** Set Organization.
@param Org_ID Organizational entity within client */
public void setOrg_ID (int Org_ID)
{
if (Org_ID <= 0) set_Value ("Org_ID", null);
 else 
set_Value ("Org_ID", new Integer(Org_ID));
}
/** Get Organization.
@return Organizational entity within client */
public int getOrg_ID() 
{
Integer ii = (Integer)get_Value("Org_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Multiplier Quantity.
@param QtyMultiplier Value to multiply quantities by for generating commissions. */
public void setQtyMultiplier (BigDecimal QtyMultiplier)
{
if (QtyMultiplier == null) throw new IllegalArgumentException ("QtyMultiplier is mandatory.");
set_Value ("QtyMultiplier", QtyMultiplier);
}
/** Get Multiplier Quantity.
@return Value to multiply quantities by for generating commissions. */
public BigDecimal getQtyMultiplier() 
{
BigDecimal bd = (BigDecimal)get_Value("QtyMultiplier");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Subtract Quantity.
@param QtySubtract Quantity to subtract when generating commissions */
public void setQtySubtract (BigDecimal QtySubtract)
{
if (QtySubtract == null) throw new IllegalArgumentException ("QtySubtract is mandatory.");
set_Value ("QtySubtract", QtySubtract);
}
/** Get Subtract Quantity.
@return Quantity to subtract when generating commissions */
public BigDecimal getQtySubtract() 
{
BigDecimal bd = (BigDecimal)get_Value("QtySubtract");
if (bd == null) return Env.ZERO;
return bd;
}
}
