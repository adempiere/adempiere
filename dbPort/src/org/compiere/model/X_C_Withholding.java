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
/** Generated Model for C_Withholding
 *  @author Jorg Janke (generated) 
 *  @version Release 3.1.3 - $Id$ */
public class X_C_Withholding extends PO
{
/** Standard Constructor
@param ctx context
@param C_Withholding_ID id
@param trxName transaction
*/
public X_C_Withholding (Properties ctx, int C_Withholding_ID, String trxName)
{
super (ctx, C_Withholding_ID, trxName);
/** if (C_Withholding_ID == 0)
{
setBeneficiary (0);
setC_PaymentTerm_ID (0);
setC_Withholding_ID (0);
setIsPaidTo3Party (false);
setIsPercentWithholding (false);
setIsTaxProrated (false);
setIsTaxWithholding (false);
setName (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_Withholding (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=304 */
public static final int Table_ID=MTable.getTable_ID("C_Withholding");

/** TableName=C_Withholding */
public static final String Table_Name="C_Withholding";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"C_Withholding");

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
StringBuffer sb = new StringBuffer ("X_C_Withholding[").append(get_ID()).append("]");
return sb.toString();
}

/** Beneficiary AD_Reference_ID=138 */
public static final int BENEFICIARY_AD_Reference_ID=138;
/** Set Beneficiary.
@param Beneficiary Business Partner to whom payment is made */
public void setBeneficiary (int Beneficiary)
{
set_Value ("Beneficiary", Integer.valueOf(Beneficiary));
}
/** Get Beneficiary.
@return Business Partner to whom payment is made */
public int getBeneficiary() 
{
Integer ii = (Integer)get_Value("Beneficiary");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Payment Term.
@param C_PaymentTerm_ID The terms of Payment (timing, discount) */
public void setC_PaymentTerm_ID (int C_PaymentTerm_ID)
{
if (C_PaymentTerm_ID < 1) throw new IllegalArgumentException ("C_PaymentTerm_ID is mandatory.");
set_Value ("C_PaymentTerm_ID", Integer.valueOf(C_PaymentTerm_ID));
}
/** Get Payment Term.
@return The terms of Payment (timing, discount) */
public int getC_PaymentTerm_ID() 
{
Integer ii = (Integer)get_Value("C_PaymentTerm_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Withholding.
@param C_Withholding_ID Withholding type defined */
public void setC_Withholding_ID (int C_Withholding_ID)
{
if (C_Withholding_ID < 1) throw new IllegalArgumentException ("C_Withholding_ID is mandatory.");
set_ValueNoCheck ("C_Withholding_ID", Integer.valueOf(C_Withholding_ID));
}
/** Get Withholding.
@return Withholding type defined */
public int getC_Withholding_ID() 
{
Integer ii = (Integer)get_Value("C_Withholding_ID");
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
/** Set Fix amount.
@param FixAmt Fix amounted amount to be levied or paid */
public void setFixAmt (BigDecimal FixAmt)
{
set_Value ("FixAmt", FixAmt);
}
/** Get Fix amount.
@return Fix amounted amount to be levied or paid */
public BigDecimal getFixAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("FixAmt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Paid to third party.
@param IsPaidTo3Party Amount paid to someone other than the Business Partner */
public void setIsPaidTo3Party (boolean IsPaidTo3Party)
{
set_Value ("IsPaidTo3Party", Boolean.valueOf(IsPaidTo3Party));
}
/** Get Paid to third party.
@return Amount paid to someone other than the Business Partner */
public boolean isPaidTo3Party() 
{
Object oo = get_Value("IsPaidTo3Party");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Percent withholding.
@param IsPercentWithholding Withholding amount is a percentage of the invoice amount */
public void setIsPercentWithholding (boolean IsPercentWithholding)
{
set_Value ("IsPercentWithholding", Boolean.valueOf(IsPercentWithholding));
}
/** Get Percent withholding.
@return Withholding amount is a percentage of the invoice amount */
public boolean isPercentWithholding() 
{
Object oo = get_Value("IsPercentWithholding");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Prorate tax.
@param IsTaxProrated Tax is Prorated */
public void setIsTaxProrated (boolean IsTaxProrated)
{
set_Value ("IsTaxProrated", Boolean.valueOf(IsTaxProrated));
}
/** Get Prorate tax.
@return Tax is Prorated */
public boolean isTaxProrated() 
{
Object oo = get_Value("IsTaxProrated");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Tax withholding.
@param IsTaxWithholding This is a tax related withholding */
public void setIsTaxWithholding (boolean IsTaxWithholding)
{
set_Value ("IsTaxWithholding", Boolean.valueOf(IsTaxWithholding));
}
/** Get Tax withholding.
@return This is a tax related withholding */
public boolean isTaxWithholding() 
{
Object oo = get_Value("IsTaxWithholding");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Max Amount.
@param MaxAmt Maximum Amount in invoice currency */
public void setMaxAmt (BigDecimal MaxAmt)
{
set_Value ("MaxAmt", MaxAmt);
}
/** Get Max Amount.
@return Maximum Amount in invoice currency */
public BigDecimal getMaxAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("MaxAmt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Min Amount.
@param MinAmt Minimum Amount in invoice currency */
public void setMinAmt (BigDecimal MinAmt)
{
set_Value ("MinAmt", MinAmt);
}
/** Get Min Amount.
@return Minimum Amount in invoice currency */
public BigDecimal getMinAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("MinAmt");
if (bd == null) return Env.ZERO;
return bd;
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
/** Set Percent.
@param Percent Percentage */
public void setPercent (BigDecimal Percent)
{
set_Value ("Percent", Percent);
}
/** Get Percent.
@return Percentage */
public BigDecimal getPercent() 
{
BigDecimal bd = (BigDecimal)get_Value("Percent");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Threshold max.
@param ThresholdMax Maximum gross amount for withholding calculation  (0=no limit) */
public void setThresholdMax (BigDecimal ThresholdMax)
{
set_Value ("ThresholdMax", ThresholdMax);
}
/** Get Threshold max.
@return Maximum gross amount for withholding calculation  (0=no limit) */
public BigDecimal getThresholdMax() 
{
BigDecimal bd = (BigDecimal)get_Value("ThresholdMax");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Threshold min.
@param Thresholdmin Minimum gross amount for withholding calculation */
public void setThresholdmin (BigDecimal Thresholdmin)
{
set_Value ("Thresholdmin", Thresholdmin);
}
/** Get Threshold min.
@return Minimum gross amount for withholding calculation */
public BigDecimal getThresholdmin() 
{
BigDecimal bd = (BigDecimal)get_Value("Thresholdmin");
if (bd == null) return Env.ZERO;
return bd;
}
}
