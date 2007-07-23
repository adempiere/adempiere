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
/** Generated Model for C_AcctSchema
 *  @author Adempiere (generated) 
 *  @version Release 3.3.0 - $Id$ */
public class X_C_AcctSchema extends PO
{
/** Standard Constructor
@param ctx context
@param C_AcctSchema_ID id
@param trxName transaction
*/
public X_C_AcctSchema (Properties ctx, int C_AcctSchema_ID, String trxName)
{
super (ctx, C_AcctSchema_ID, trxName);
/** if (C_AcctSchema_ID == 0)
{
setAutoPeriodControl (false);
setC_AcctSchema_ID (0);
setC_Currency_ID (0);
setCommitmentType (null);	// N
setCostingLevel (null);	// C
setCostingMethod (null);	// S
setGAAP (null);
setHasAlias (false);
setHasCombination (false);
setIsAccrual (true);	// Y
setIsAdjustCOGS (false);
setIsDiscountCorrectsTax (false);
setIsExplicitCostAdjustment (false);	// N
setIsPostServices (false);	// N
setIsTradeDiscountPosted (false);
setM_CostType_ID (0);
setName (null);
setSeparator (null);	// -
setTaxCorrectionType (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_AcctSchema (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** TableName=C_AcctSchema */
public static final String Table_Name="C_AcctSchema";

/** AD_Table_ID=265 */
public static final int Table_ID=MTable.getTable_ID(Table_Name);

protected static KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

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
StringBuffer sb = new StringBuffer ("X_C_AcctSchema[").append(get_ID()).append("]");
return sb.toString();
}

/** AD_OrgOnly_ID AD_Reference_ID=322 */
public static final int AD_ORGONLY_ID_AD_Reference_ID=322;
/** Set Only Organization.
@param AD_OrgOnly_ID Create posting entries only for this organization */
public void setAD_OrgOnly_ID (int AD_OrgOnly_ID)
{
if (AD_OrgOnly_ID <= 0) set_Value ("AD_OrgOnly_ID", null);
 else 
set_Value ("AD_OrgOnly_ID", Integer.valueOf(AD_OrgOnly_ID));
}
/** Get Only Organization.
@return Create posting entries only for this organization */
public int getAD_OrgOnly_ID() 
{
Integer ii = (Integer)get_Value("AD_OrgOnly_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_OrgOnly_ID */
public static final String COLUMNNAME_AD_OrgOnly_ID = "AD_OrgOnly_ID";
/** Set Automatic Period Control.
@param AutoPeriodControl If selected, the periods are automatically opened and closed */
public void setAutoPeriodControl (boolean AutoPeriodControl)
{
set_Value ("AutoPeriodControl", Boolean.valueOf(AutoPeriodControl));
}
/** Get Automatic Period Control.
@return If selected, the periods are automatically opened and closed */
public boolean isAutoPeriodControl() 
{
Object oo = get_Value("AutoPeriodControl");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name AutoPeriodControl */
public static final String COLUMNNAME_AutoPeriodControl = "AutoPeriodControl";
/** Set Accounting Schema.
@param C_AcctSchema_ID Rules for accounting */
public void setC_AcctSchema_ID (int C_AcctSchema_ID)
{
if (C_AcctSchema_ID < 1) throw new IllegalArgumentException ("C_AcctSchema_ID is mandatory.");
set_ValueNoCheck ("C_AcctSchema_ID", Integer.valueOf(C_AcctSchema_ID));
}
/** Get Accounting Schema.
@return Rules for accounting */
public int getC_AcctSchema_ID() 
{
Integer ii = (Integer)get_Value("C_AcctSchema_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_AcctSchema_ID */
public static final String COLUMNNAME_C_AcctSchema_ID = "C_AcctSchema_ID";
/** Set Currency.
@param C_Currency_ID The Currency for this record */
public void setC_Currency_ID (int C_Currency_ID)
{
if (C_Currency_ID < 1) throw new IllegalArgumentException ("C_Currency_ID is mandatory.");
set_Value ("C_Currency_ID", Integer.valueOf(C_Currency_ID));
}
/** Get Currency.
@return The Currency for this record */
public int getC_Currency_ID() 
{
Integer ii = (Integer)get_Value("C_Currency_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_Currency_ID */
public static final String COLUMNNAME_C_Currency_ID = "C_Currency_ID";
/** Set Period.
@param C_Period_ID Period of the Calendar */
public void setC_Period_ID (int C_Period_ID)
{
if (C_Period_ID <= 0) set_ValueNoCheck ("C_Period_ID", null);
 else 
set_ValueNoCheck ("C_Period_ID", Integer.valueOf(C_Period_ID));
}
/** Get Period.
@return Period of the Calendar */
public int getC_Period_ID() 
{
Integer ii = (Integer)get_Value("C_Period_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_Period_ID */
public static final String COLUMNNAME_C_Period_ID = "C_Period_ID";

/** CommitmentType AD_Reference_ID=359 */
public static final int COMMITMENTTYPE_AD_Reference_ID=359;
/** Commitment & Reservation = B */
public static final String COMMITMENTTYPE_CommitmentReservation = "B";
/** Commitment only = C */
public static final String COMMITMENTTYPE_CommitmentOnly = "C";
/** None = N */
public static final String COMMITMENTTYPE_None = "N";
/** Set Commitment Type.
@param CommitmentType Create Commitment and/or Reservations for Budget Control */
public void setCommitmentType (String CommitmentType)
{
if (CommitmentType == null) throw new IllegalArgumentException ("CommitmentType is mandatory");
if (CommitmentType.equals("B") || CommitmentType.equals("C") || CommitmentType.equals("N"));
 else throw new IllegalArgumentException ("CommitmentType Invalid value - " + CommitmentType + " - Reference_ID=359 - B - C - N");
if (CommitmentType.length() > 1)
{
log.warning("Length > 1 - truncated");
CommitmentType = CommitmentType.substring(0,0);
}
set_Value ("CommitmentType", CommitmentType);
}
/** Get Commitment Type.
@return Create Commitment and/or Reservations for Budget Control */
public String getCommitmentType() 
{
return (String)get_Value("CommitmentType");
}
/** Column name CommitmentType */
public static final String COLUMNNAME_CommitmentType = "CommitmentType";

/** CostingLevel AD_Reference_ID=355 */
public static final int COSTINGLEVEL_AD_Reference_ID=355;
/** Batch/Lot = B */
public static final String COSTINGLEVEL_BatchLot = "B";
/** Client = C */
public static final String COSTINGLEVEL_Client = "C";
/** Organization = O */
public static final String COSTINGLEVEL_Organization = "O";
/** Set Costing Level.
@param CostingLevel The lowest level to accumulate Costing Information */
public void setCostingLevel (String CostingLevel)
{
if (CostingLevel == null) throw new IllegalArgumentException ("CostingLevel is mandatory");
if (CostingLevel.equals("B") || CostingLevel.equals("C") || CostingLevel.equals("O"));
 else throw new IllegalArgumentException ("CostingLevel Invalid value - " + CostingLevel + " - Reference_ID=355 - B - C - O");
if (CostingLevel.length() > 1)
{
log.warning("Length > 1 - truncated");
CostingLevel = CostingLevel.substring(0,0);
}
set_Value ("CostingLevel", CostingLevel);
}
/** Get Costing Level.
@return The lowest level to accumulate Costing Information */
public String getCostingLevel() 
{
return (String)get_Value("CostingLevel");
}
/** Column name CostingLevel */
public static final String COLUMNNAME_CostingLevel = "CostingLevel";

/** CostingMethod AD_Reference_ID=122 */
public static final int COSTINGMETHOD_AD_Reference_ID=122;
/** Average PO = A */
public static final String COSTINGMETHOD_AveragePO = "A";
/** Fifo = F */
public static final String COSTINGMETHOD_Fifo = "F";
/** Average Invoice = I */
public static final String COSTINGMETHOD_AverageInvoice = "I";
/** Lifo = L */
public static final String COSTINGMETHOD_Lifo = "L";
/** Standard Costing = S */
public static final String COSTINGMETHOD_StandardCosting = "S";
/** User Defined = U */
public static final String COSTINGMETHOD_UserDefined = "U";
/** Last Invoice = i */
public static final String COSTINGMETHOD_LastInvoice = "i";
/** Last PO Price = p */
public static final String COSTINGMETHOD_LastPOPrice = "p";
/** _ = x */
public static final String COSTINGMETHOD__ = "x";
/** Set Costing Method.
@param CostingMethod Indicates how Costs will be calculated */
public void setCostingMethod (String CostingMethod)
{
if (CostingMethod == null) throw new IllegalArgumentException ("CostingMethod is mandatory");
if (CostingMethod.equals("A") || CostingMethod.equals("F") || CostingMethod.equals("I") || CostingMethod.equals("L") || CostingMethod.equals("S") || CostingMethod.equals("U") || CostingMethod.equals("i") || CostingMethod.equals("p") || CostingMethod.equals("x"));
 else throw new IllegalArgumentException ("CostingMethod Invalid value - " + CostingMethod + " - Reference_ID=122 - A - F - I - L - S - U - i - p - x");
if (CostingMethod.length() > 1)
{
log.warning("Length > 1 - truncated");
CostingMethod = CostingMethod.substring(0,0);
}
set_Value ("CostingMethod", CostingMethod);
}
/** Get Costing Method.
@return Indicates how Costs will be calculated */
public String getCostingMethod() 
{
return (String)get_Value("CostingMethod");
}
/** Column name CostingMethod */
public static final String COLUMNNAME_CostingMethod = "CostingMethod";
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

/** GAAP AD_Reference_ID=123 */
public static final int GAAP_AD_Reference_ID=123;
/** German HGB = DE */
public static final String GAAP_GermanHGB = "DE";
/** French Accounting Standard = FR */
public static final String GAAP_FrenchAccountingStandard = "FR";
/** International GAAP = UN */
public static final String GAAP_InternationalGAAP = "UN";
/** US GAAP = US */
public static final String GAAP_USGAAP = "US";
/** Custom Accounting Rules = XX */
public static final String GAAP_CustomAccountingRules = "XX";
/** Set GAAP.
@param GAAP Generally Accepted Accounting Principles */
public void setGAAP (String GAAP)
{
if (GAAP == null) throw new IllegalArgumentException ("GAAP is mandatory");
if (GAAP.equals("DE") || GAAP.equals("FR") || GAAP.equals("UN") || GAAP.equals("US") || GAAP.equals("XX"));
 else throw new IllegalArgumentException ("GAAP Invalid value - " + GAAP + " - Reference_ID=123 - DE - FR - UN - US - XX");
if (GAAP.length() > 2)
{
log.warning("Length > 2 - truncated");
GAAP = GAAP.substring(0,1);
}
set_Value ("GAAP", GAAP);
}
/** Get GAAP.
@return Generally Accepted Accounting Principles */
public String getGAAP() 
{
return (String)get_Value("GAAP");
}
/** Column name GAAP */
public static final String COLUMNNAME_GAAP = "GAAP";
/** Set Use Account Alias.
@param HasAlias Ability to select (partial) account combinations by an Alias */
public void setHasAlias (boolean HasAlias)
{
set_Value ("HasAlias", Boolean.valueOf(HasAlias));
}
/** Get Use Account Alias.
@return Ability to select (partial) account combinations by an Alias */
public boolean isHasAlias() 
{
Object oo = get_Value("HasAlias");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name HasAlias */
public static final String COLUMNNAME_HasAlias = "HasAlias";
/** Set Use Account Combination Control.
@param HasCombination Combination of account elements are checked */
public void setHasCombination (boolean HasCombination)
{
set_Value ("HasCombination", Boolean.valueOf(HasCombination));
}
/** Get Use Account Combination Control.
@return Combination of account elements are checked */
public boolean isHasCombination() 
{
Object oo = get_Value("HasCombination");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name HasCombination */
public static final String COLUMNNAME_HasCombination = "HasCombination";
/** Set Accrual.
@param IsAccrual Indicates if Accrual or Cash Based accounting will be used */
public void setIsAccrual (boolean IsAccrual)
{
set_Value ("IsAccrual", Boolean.valueOf(IsAccrual));
}
/** Get Accrual.
@return Indicates if Accrual or Cash Based accounting will be used */
public boolean isAccrual() 
{
Object oo = get_Value("IsAccrual");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsAccrual */
public static final String COLUMNNAME_IsAccrual = "IsAccrual";
/** Set Adjust COGS.
@param IsAdjustCOGS Adjust Cost of Good Sold */
public void setIsAdjustCOGS (boolean IsAdjustCOGS)
{
set_Value ("IsAdjustCOGS", Boolean.valueOf(IsAdjustCOGS));
}
/** Get Adjust COGS.
@return Adjust Cost of Good Sold */
public boolean isAdjustCOGS() 
{
Object oo = get_Value("IsAdjustCOGS");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsAdjustCOGS */
public static final String COLUMNNAME_IsAdjustCOGS = "IsAdjustCOGS";
/** Set Allow Negative Posting.
@param IsAllowNegativePosting Allow to post negative accounting values */
public void setIsAllowNegativePosting (boolean IsAllowNegativePosting)
{
set_Value ("IsAllowNegativePosting", Boolean.valueOf(IsAllowNegativePosting));
}
/** Get Allow Negative Posting.
@return Allow to post negative accounting values */
public boolean isAllowNegativePosting() 
{
Object oo = get_Value("IsAllowNegativePosting");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsAllowNegativePosting */
public static final String COLUMNNAME_IsAllowNegativePosting = "IsAllowNegativePosting";
/** Set Correct tax for Discounts/Charges.
@param IsDiscountCorrectsTax Correct the tax for payment discount and charges */
public void setIsDiscountCorrectsTax (boolean IsDiscountCorrectsTax)
{
set_Value ("IsDiscountCorrectsTax", Boolean.valueOf(IsDiscountCorrectsTax));
}
/** Get Correct tax for Discounts/Charges.
@return Correct the tax for payment discount and charges */
public boolean isDiscountCorrectsTax() 
{
Object oo = get_Value("IsDiscountCorrectsTax");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsDiscountCorrectsTax */
public static final String COLUMNNAME_IsDiscountCorrectsTax = "IsDiscountCorrectsTax";
/** Set Explicit Cost Adjustment.
@param IsExplicitCostAdjustment Post the cost adjustment explicitly */
public void setIsExplicitCostAdjustment (boolean IsExplicitCostAdjustment)
{
set_Value ("IsExplicitCostAdjustment", Boolean.valueOf(IsExplicitCostAdjustment));
}
/** Get Explicit Cost Adjustment.
@return Post the cost adjustment explicitly */
public boolean isExplicitCostAdjustment() 
{
Object oo = get_Value("IsExplicitCostAdjustment");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsExplicitCostAdjustment */
public static final String COLUMNNAME_IsExplicitCostAdjustment = "IsExplicitCostAdjustment";
/** Set Post Services Separately.
@param IsPostServices Differentiate between Services and Product Receivable/Payables */
public void setIsPostServices (boolean IsPostServices)
{
set_Value ("IsPostServices", Boolean.valueOf(IsPostServices));
}
/** Get Post Services Separately.
@return Differentiate between Services and Product Receivable/Payables */
public boolean isPostServices() 
{
Object oo = get_Value("IsPostServices");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsPostServices */
public static final String COLUMNNAME_IsPostServices = "IsPostServices";
/** Set Post Trade Discount.
@param IsTradeDiscountPosted Generate postings for trade discounts */
public void setIsTradeDiscountPosted (boolean IsTradeDiscountPosted)
{
set_Value ("IsTradeDiscountPosted", Boolean.valueOf(IsTradeDiscountPosted));
}
/** Get Post Trade Discount.
@return Generate postings for trade discounts */
public boolean isTradeDiscountPosted() 
{
Object oo = get_Value("IsTradeDiscountPosted");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsTradeDiscountPosted */
public static final String COLUMNNAME_IsTradeDiscountPosted = "IsTradeDiscountPosted";
/** Set Cost Type.
@param M_CostType_ID Type of Cost (e.g. Current, Plan, Future) */
public void setM_CostType_ID (int M_CostType_ID)
{
if (M_CostType_ID < 1) throw new IllegalArgumentException ("M_CostType_ID is mandatory.");
set_Value ("M_CostType_ID", Integer.valueOf(M_CostType_ID));
}
/** Get Cost Type.
@return Type of Cost (e.g. Current, Plan, Future) */
public int getM_CostType_ID() 
{
Integer ii = (Integer)get_Value("M_CostType_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name M_CostType_ID */
public static final String COLUMNNAME_M_CostType_ID = "M_CostType_ID";
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
/** Set Future Days.
@param Period_OpenFuture Number of days to be able to post to a future date (based on system date) */
public void setPeriod_OpenFuture (int Period_OpenFuture)
{
set_Value ("Period_OpenFuture", Integer.valueOf(Period_OpenFuture));
}
/** Get Future Days.
@return Number of days to be able to post to a future date (based on system date) */
public int getPeriod_OpenFuture() 
{
Integer ii = (Integer)get_Value("Period_OpenFuture");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name Period_OpenFuture */
public static final String COLUMNNAME_Period_OpenFuture = "Period_OpenFuture";
/** Set History Days.
@param Period_OpenHistory Number of days to be able to post in the past (based on system date) */
public void setPeriod_OpenHistory (int Period_OpenHistory)
{
set_Value ("Period_OpenHistory", Integer.valueOf(Period_OpenHistory));
}
/** Get History Days.
@return Number of days to be able to post in the past (based on system date) */
public int getPeriod_OpenHistory() 
{
Integer ii = (Integer)get_Value("Period_OpenHistory");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name Period_OpenHistory */
public static final String COLUMNNAME_Period_OpenHistory = "Period_OpenHistory";
/** Set Process Now.
@param Processing Process Now */
public void setProcessing (boolean Processing)
{
set_Value ("Processing", Boolean.valueOf(Processing));
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
/** Column name Processing */
public static final String COLUMNNAME_Processing = "Processing";
/** Set Element Separator.
@param Separator Element Separator */
public void setSeparator (String Separator)
{
if (Separator == null) throw new IllegalArgumentException ("Separator is mandatory.");
if (Separator.length() > 1)
{
log.warning("Length > 1 - truncated");
Separator = Separator.substring(0,0);
}
set_Value ("Separator", Separator);
}
/** Get Element Separator.
@return Element Separator */
public String getSeparator() 
{
return (String)get_Value("Separator");
}
/** Column name Separator */
public static final String COLUMNNAME_Separator = "Separator";

/** TaxCorrectionType AD_Reference_ID=392 */
public static final int TAXCORRECTIONTYPE_AD_Reference_ID=392;
/** Write-off and Discount = B */
public static final String TAXCORRECTIONTYPE_Write_OffAndDiscount = "B";
/** Discount only = D */
public static final String TAXCORRECTIONTYPE_DiscountOnly = "D";
/** None = N */
public static final String TAXCORRECTIONTYPE_None = "N";
/** Write-off only = W */
public static final String TAXCORRECTIONTYPE_Write_OffOnly = "W";
/** Set Tax Correction.
@param TaxCorrectionType Type of Tax Correction */
public void setTaxCorrectionType (String TaxCorrectionType)
{
if (TaxCorrectionType == null) throw new IllegalArgumentException ("TaxCorrectionType is mandatory");
if (TaxCorrectionType.equals("B") || TaxCorrectionType.equals("D") || TaxCorrectionType.equals("N") || TaxCorrectionType.equals("W"));
 else throw new IllegalArgumentException ("TaxCorrectionType Invalid value - " + TaxCorrectionType + " - Reference_ID=392 - B - D - N - W");
if (TaxCorrectionType.length() > 1)
{
log.warning("Length > 1 - truncated");
TaxCorrectionType = TaxCorrectionType.substring(0,0);
}
set_Value ("TaxCorrectionType", TaxCorrectionType);
}
/** Get Tax Correction.
@return Type of Tax Correction */
public String getTaxCorrectionType() 
{
return (String)get_Value("TaxCorrectionType");
}
/** Column name TaxCorrectionType */
public static final String COLUMNNAME_TaxCorrectionType = "TaxCorrectionType";
}
