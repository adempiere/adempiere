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
/** Generated Model for PA_ReportColumn
 *  @author Adempiere (generated) 
 *  @version Release 3.1.5 - $Id$ */
public class X_PA_ReportColumn extends PO
{
/** Standard Constructor
@param ctx context
@param PA_ReportColumn_ID id
@param trxName transaction
*/
public X_PA_ReportColumn (Properties ctx, int PA_ReportColumn_ID, String trxName)
{
super (ctx, PA_ReportColumn_ID, trxName);
/** if (PA_ReportColumn_ID == 0)
{
setColumnType (null);	// R
setIsPrinted (true);	// Y
setName (null);
setPA_ReportColumnSet_ID (0);
setPA_ReportColumn_ID (0);
setPostingType (null);	// A
setSeqNo (0);	// @SQL=SELECT NVL(MAX(SeqNo),0)+10 AS DefaultValue FROM PA_ReportColumn WHERE PA_ReportColumnSet_ID=@PA_ReportColumnSet_ID@
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_PA_ReportColumn (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=446 */
public static final int Table_ID=MTable.getTable_ID("PA_ReportColumn");

/** TableName=PA_ReportColumn */
public static final String Table_Name="PA_ReportColumn";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"PA_ReportColumn");

protected BigDecimal accessLevel = BigDecimal.valueOf(7);
/** AccessLevel
@return 7 - System - Client - Org 
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
StringBuffer sb = new StringBuffer ("X_PA_ReportColumn[").append(get_ID()).append("]");
return sb.toString();
}

/** AmountType AD_Reference_ID=235 */
public static final int AMOUNTTYPE_AD_Reference_ID=235;
/** Period Balance = BP */
public static final String AMOUNTTYPE_PeriodBalance = "BP";
/** Total Balance = BT */
public static final String AMOUNTTYPE_TotalBalance = "BT";
/** Year Balance = BY */
public static final String AMOUNTTYPE_YearBalance = "BY";
/** Period Credit Only = CP */
public static final String AMOUNTTYPE_PeriodCreditOnly = "CP";
/** Total Credit Only = CT */
public static final String AMOUNTTYPE_TotalCreditOnly = "CT";
/** Year Credit Only = CY */
public static final String AMOUNTTYPE_YearCreditOnly = "CY";
/** Period Debit Only = DP */
public static final String AMOUNTTYPE_PeriodDebitOnly = "DP";
/** Total Debit Only = DT */
public static final String AMOUNTTYPE_TotalDebitOnly = "DT";
/** Year Debit Only = DY */
public static final String AMOUNTTYPE_YearDebitOnly = "DY";
/** Period Quantity = QP */
public static final String AMOUNTTYPE_PeriodQuantity = "QP";
/** Total Quantity = QT */
public static final String AMOUNTTYPE_TotalQuantity = "QT";
/** Year Quantity = QY */
public static final String AMOUNTTYPE_YearQuantity = "QY";
/** Set Amount Type.
@param AmountType Type of amount to report */
public void setAmountType (String AmountType)
{
if (AmountType == null || AmountType.equals("BP") || AmountType.equals("BT") || AmountType.equals("BY") || AmountType.equals("CP") || AmountType.equals("CT") || AmountType.equals("CY") || AmountType.equals("DP") || AmountType.equals("DT") || AmountType.equals("DY") || AmountType.equals("QP") || AmountType.equals("QT") || AmountType.equals("QY"));
 else throw new IllegalArgumentException ("AmountType Invalid value - " + AmountType + " - Reference_ID=235 - BP - BT - BY - CP - CT - CY - DP - DT - DY - QP - QT - QY");
if (AmountType != null && AmountType.length() > 2)
{
log.warning("Length > 2 - truncated");
AmountType = AmountType.substring(0,1);
}
set_Value ("AmountType", AmountType);
}
/** Get Amount Type.
@return Type of amount to report */
public String getAmountType() 
{
return (String)get_Value("AmountType");
}
/** Column name AmountType */
public static final String COLUMNNAME_AmountType = "AmountType";
/** Set Activity.
@param C_Activity_ID Business Activity */
public void setC_Activity_ID (int C_Activity_ID)
{
if (C_Activity_ID <= 0) set_Value ("C_Activity_ID", null);
 else 
set_Value ("C_Activity_ID", Integer.valueOf(C_Activity_ID));
}
/** Get Activity.
@return Business Activity */
public int getC_Activity_ID() 
{
Integer ii = (Integer)get_Value("C_Activity_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_Activity_ID */
public static final String COLUMNNAME_C_Activity_ID = "C_Activity_ID";
/** Set Business Partner .
@param C_BPartner_ID Identifies a Business Partner */
public void setC_BPartner_ID (int C_BPartner_ID)
{
if (C_BPartner_ID <= 0) set_Value ("C_BPartner_ID", null);
 else 
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
/** Set Campaign.
@param C_Campaign_ID Marketing Campaign */
public void setC_Campaign_ID (int C_Campaign_ID)
{
if (C_Campaign_ID <= 0) set_Value ("C_Campaign_ID", null);
 else 
set_Value ("C_Campaign_ID", Integer.valueOf(C_Campaign_ID));
}
/** Get Campaign.
@return Marketing Campaign */
public int getC_Campaign_ID() 
{
Integer ii = (Integer)get_Value("C_Campaign_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_Campaign_ID */
public static final String COLUMNNAME_C_Campaign_ID = "C_Campaign_ID";
/** Set Currency.
@param C_Currency_ID The Currency for this record */
public void setC_Currency_ID (int C_Currency_ID)
{
if (C_Currency_ID <= 0) set_Value ("C_Currency_ID", null);
 else 
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
/** Set Account Element.
@param C_ElementValue_ID Account Element */
public void setC_ElementValue_ID (int C_ElementValue_ID)
{
if (C_ElementValue_ID <= 0) set_Value ("C_ElementValue_ID", null);
 else 
set_Value ("C_ElementValue_ID", Integer.valueOf(C_ElementValue_ID));
}
/** Get Account Element.
@return Account Element */
public int getC_ElementValue_ID() 
{
Integer ii = (Integer)get_Value("C_ElementValue_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_ElementValue_ID */
public static final String COLUMNNAME_C_ElementValue_ID = "C_ElementValue_ID";
/** Set Address.
@param C_Location_ID Location or Address */
public void setC_Location_ID (int C_Location_ID)
{
if (C_Location_ID <= 0) set_Value ("C_Location_ID", null);
 else 
set_Value ("C_Location_ID", Integer.valueOf(C_Location_ID));
}
/** Get Address.
@return Location or Address */
public int getC_Location_ID() 
{
Integer ii = (Integer)get_Value("C_Location_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_Location_ID */
public static final String COLUMNNAME_C_Location_ID = "C_Location_ID";
/** Set Project.
@param C_Project_ID Financial Project */
public void setC_Project_ID (int C_Project_ID)
{
if (C_Project_ID <= 0) set_Value ("C_Project_ID", null);
 else 
set_Value ("C_Project_ID", Integer.valueOf(C_Project_ID));
}
/** Get Project.
@return Financial Project */
public int getC_Project_ID() 
{
Integer ii = (Integer)get_Value("C_Project_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_Project_ID */
public static final String COLUMNNAME_C_Project_ID = "C_Project_ID";
/** Set Sales Region.
@param C_SalesRegion_ID Sales coverage region */
public void setC_SalesRegion_ID (int C_SalesRegion_ID)
{
if (C_SalesRegion_ID <= 0) set_Value ("C_SalesRegion_ID", null);
 else 
set_Value ("C_SalesRegion_ID", Integer.valueOf(C_SalesRegion_ID));
}
/** Get Sales Region.
@return Sales coverage region */
public int getC_SalesRegion_ID() 
{
Integer ii = (Integer)get_Value("C_SalesRegion_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_SalesRegion_ID */
public static final String COLUMNNAME_C_SalesRegion_ID = "C_SalesRegion_ID";

/** CalculationType AD_Reference_ID=236 */
public static final int CALCULATIONTYPE_AD_Reference_ID=236;
/** Add (Op1+Op2) = A */
public static final String CALCULATIONTYPE_AddOp1PlusOp2 = "A";
/** Percentage (Op1 of Op2) = P */
public static final String CALCULATIONTYPE_PercentageOp1OfOp2 = "P";
/** Add Range (Op1 to Op2) = R */
public static final String CALCULATIONTYPE_AddRangeOp1ToOp2 = "R";
/** Subtract (Op1-Op2) = S */
public static final String CALCULATIONTYPE_SubtractOp1_Op2 = "S";
/** Set Calculation.
@param CalculationType Calculation */
public void setCalculationType (String CalculationType)
{
if (CalculationType == null || CalculationType.equals("A") || CalculationType.equals("P") || CalculationType.equals("R") || CalculationType.equals("S"));
 else throw new IllegalArgumentException ("CalculationType Invalid value - " + CalculationType + " - Reference_ID=236 - A - P - R - S");
if (CalculationType != null && CalculationType.length() > 1)
{
log.warning("Length > 1 - truncated");
CalculationType = CalculationType.substring(0,0);
}
set_Value ("CalculationType", CalculationType);
}
/** Get Calculation.
@return Calculation */
public String getCalculationType() 
{
return (String)get_Value("CalculationType");
}
/** Column name CalculationType */
public static final String COLUMNNAME_CalculationType = "CalculationType";

/** ColumnType AD_Reference_ID=237 */
public static final int COLUMNTYPE_AD_Reference_ID=237;
/** Calculation = C */
public static final String COLUMNTYPE_Calculation = "C";
/** Relative Period = R */
public static final String COLUMNTYPE_RelativePeriod = "R";
/** Segment Value = S */
public static final String COLUMNTYPE_SegmentValue = "S";
/** Set Column Type.
@param ColumnType Column Type */
public void setColumnType (String ColumnType)
{
if (ColumnType == null) throw new IllegalArgumentException ("ColumnType is mandatory");
if (ColumnType.equals("C") || ColumnType.equals("R") || ColumnType.equals("S"));
 else throw new IllegalArgumentException ("ColumnType Invalid value - " + ColumnType + " - Reference_ID=237 - C - R - S");
if (ColumnType.length() > 1)
{
log.warning("Length > 1 - truncated");
ColumnType = ColumnType.substring(0,0);
}
set_Value ("ColumnType", ColumnType);
}
/** Get Column Type.
@return Column Type */
public String getColumnType() 
{
return (String)get_Value("ColumnType");
}
/** Column name ColumnType */
public static final String COLUMNNAME_ColumnType = "ColumnType";

/** CurrencyType AD_Reference_ID=238 */
public static final int CURRENCYTYPE_AD_Reference_ID=238;
/** Accounting Currency = A */
public static final String CURRENCYTYPE_AccountingCurrency = "A";
/** Source Currency = S */
public static final String CURRENCYTYPE_SourceCurrency = "S";
/** Set Currency Type.
@param CurrencyType Currency Type */
public void setCurrencyType (String CurrencyType)
{
if (CurrencyType == null || CurrencyType.equals("A") || CurrencyType.equals("S"));
 else throw new IllegalArgumentException ("CurrencyType Invalid value - " + CurrencyType + " - Reference_ID=238 - A - S");
if (CurrencyType != null && CurrencyType.length() > 1)
{
log.warning("Length > 1 - truncated");
CurrencyType = CurrencyType.substring(0,0);
}
set_Value ("CurrencyType", CurrencyType);
}
/** Get Currency Type.
@return Currency Type */
public String getCurrencyType() 
{
return (String)get_Value("CurrencyType");
}
/** Column name CurrencyType */
public static final String COLUMNNAME_CurrencyType = "CurrencyType";
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

/** ElementType AD_Reference_ID=181 */
public static final int ELEMENTTYPE_AD_Reference_ID=181;
/** Account = AC */
public static final String ELEMENTTYPE_Account = "AC";
/** Activity = AY */
public static final String ELEMENTTYPE_Activity = "AY";
/** BPartner = BP */
public static final String ELEMENTTYPE_BPartner = "BP";
/** Location From = LF */
public static final String ELEMENTTYPE_LocationFrom = "LF";
/** Location To = LT */
public static final String ELEMENTTYPE_LocationTo = "LT";
/** Campaign = MC */
public static final String ELEMENTTYPE_Campaign = "MC";
/** Organization = OO */
public static final String ELEMENTTYPE_Organization = "OO";
/** Org Trx = OT */
public static final String ELEMENTTYPE_OrgTrx = "OT";
/** Project = PJ */
public static final String ELEMENTTYPE_Project = "PJ";
/** Product = PR */
public static final String ELEMENTTYPE_Product = "PR";
/** Sub Account = SA */
public static final String ELEMENTTYPE_SubAccount = "SA";
/** Sales Region = SR */
public static final String ELEMENTTYPE_SalesRegion = "SR";
/** User List 1 = U1 */
public static final String ELEMENTTYPE_UserList1 = "U1";
/** User List 2 = U2 */
public static final String ELEMENTTYPE_UserList2 = "U2";
/** User Element 1 = X1 */
public static final String ELEMENTTYPE_UserElement1 = "X1";
/** User Element 2 = X2 */
public static final String ELEMENTTYPE_UserElement2 = "X2";
/** Set Type.
@param ElementType Element Type (account or user defined) */
public void setElementType (String ElementType)
{
if (ElementType == null || ElementType.equals("AC") || ElementType.equals("AY") || ElementType.equals("BP") || ElementType.equals("LF") || ElementType.equals("LT") || ElementType.equals("MC") || ElementType.equals("OO") || ElementType.equals("OT") || ElementType.equals("PJ") || ElementType.equals("PR") || ElementType.equals("SA") || ElementType.equals("SR") || ElementType.equals("U1") || ElementType.equals("U2") || ElementType.equals("X1") || ElementType.equals("X2"));
 else throw new IllegalArgumentException ("ElementType Invalid value - " + ElementType + " - Reference_ID=181 - AC - AY - BP - LF - LT - MC - OO - OT - PJ - PR - SA - SR - U1 - U2 - X1 - X2");
if (ElementType != null && ElementType.length() > 2)
{
log.warning("Length > 2 - truncated");
ElementType = ElementType.substring(0,1);
}
set_Value ("ElementType", ElementType);
}
/** Get Type.
@return Element Type (account or user defined) */
public String getElementType() 
{
return (String)get_Value("ElementType");
}
/** Column name ElementType */
public static final String COLUMNNAME_ElementType = "ElementType";
/** Set Budget.
@param GL_Budget_ID General Ledger Budget */
public void setGL_Budget_ID (int GL_Budget_ID)
{
if (GL_Budget_ID <= 0) set_Value ("GL_Budget_ID", null);
 else 
set_Value ("GL_Budget_ID", Integer.valueOf(GL_Budget_ID));
}
/** Get Budget.
@return General Ledger Budget */
public int getGL_Budget_ID() 
{
Integer ii = (Integer)get_Value("GL_Budget_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name GL_Budget_ID */
public static final String COLUMNNAME_GL_Budget_ID = "GL_Budget_ID";
/** Set Adhoc Conversion.
@param IsAdhocConversion Perform conversion for all amounts to currency */
public void setIsAdhocConversion (boolean IsAdhocConversion)
{
set_Value ("IsAdhocConversion", Boolean.valueOf(IsAdhocConversion));
}
/** Get Adhoc Conversion.
@return Perform conversion for all amounts to currency */
public boolean isAdhocConversion() 
{
Object oo = get_Value("IsAdhocConversion");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsAdhocConversion */
public static final String COLUMNNAME_IsAdhocConversion = "IsAdhocConversion";
/** Set Printed.
@param IsPrinted Indicates if this document / line is printed */
public void setIsPrinted (boolean IsPrinted)
{
set_Value ("IsPrinted", Boolean.valueOf(IsPrinted));
}
/** Get Printed.
@return Indicates if this document / line is printed */
public boolean isPrinted() 
{
Object oo = get_Value("IsPrinted");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsPrinted */
public static final String COLUMNNAME_IsPrinted = "IsPrinted";
/** Set Product.
@param M_Product_ID Product, Service, Item */
public void setM_Product_ID (int M_Product_ID)
{
if (M_Product_ID <= 0) set_Value ("M_Product_ID", null);
 else 
set_Value ("M_Product_ID", Integer.valueOf(M_Product_ID));
}
/** Get Product.
@return Product, Service, Item */
public int getM_Product_ID() 
{
Integer ii = (Integer)get_Value("M_Product_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name M_Product_ID */
public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";
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

/** Oper_1_ID AD_Reference_ID=239 */
public static final int OPER_1_ID_AD_Reference_ID=239;
/** Set Operand 1.
@param Oper_1_ID First operand for calculation */
public void setOper_1_ID (int Oper_1_ID)
{
if (Oper_1_ID <= 0) set_Value ("Oper_1_ID", null);
 else 
set_Value ("Oper_1_ID", Integer.valueOf(Oper_1_ID));
}
/** Get Operand 1.
@return First operand for calculation */
public int getOper_1_ID() 
{
Integer ii = (Integer)get_Value("Oper_1_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name Oper_1_ID */
public static final String COLUMNNAME_Oper_1_ID = "Oper_1_ID";

/** Oper_2_ID AD_Reference_ID=239 */
public static final int OPER_2_ID_AD_Reference_ID=239;
/** Set Operand 2.
@param Oper_2_ID Second operand for calculation */
public void setOper_2_ID (int Oper_2_ID)
{
if (Oper_2_ID <= 0) set_Value ("Oper_2_ID", null);
 else 
set_Value ("Oper_2_ID", Integer.valueOf(Oper_2_ID));
}
/** Get Operand 2.
@return Second operand for calculation */
public int getOper_2_ID() 
{
Integer ii = (Integer)get_Value("Oper_2_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name Oper_2_ID */
public static final String COLUMNNAME_Oper_2_ID = "Oper_2_ID";

/** Org_ID AD_Reference_ID=130 */
public static final int ORG_ID_AD_Reference_ID=130;
/** Set Organization.
@param Org_ID Organizational entity within client */
public void setOrg_ID (int Org_ID)
{
if (Org_ID <= 0) set_Value ("Org_ID", null);
 else 
set_Value ("Org_ID", Integer.valueOf(Org_ID));
}
/** Get Organization.
@return Organizational entity within client */
public int getOrg_ID() 
{
Integer ii = (Integer)get_Value("Org_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name Org_ID */
public static final String COLUMNNAME_Org_ID = "Org_ID";
/** Set Report Column Set.
@param PA_ReportColumnSet_ID Collection of Columns for Report */
public void setPA_ReportColumnSet_ID (int PA_ReportColumnSet_ID)
{
if (PA_ReportColumnSet_ID < 1) throw new IllegalArgumentException ("PA_ReportColumnSet_ID is mandatory.");
set_ValueNoCheck ("PA_ReportColumnSet_ID", Integer.valueOf(PA_ReportColumnSet_ID));
}
/** Get Report Column Set.
@return Collection of Columns for Report */
public int getPA_ReportColumnSet_ID() 
{
Integer ii = (Integer)get_Value("PA_ReportColumnSet_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name PA_ReportColumnSet_ID */
public static final String COLUMNNAME_PA_ReportColumnSet_ID = "PA_ReportColumnSet_ID";
/** Set Report Column.
@param PA_ReportColumn_ID Column in Report */
public void setPA_ReportColumn_ID (int PA_ReportColumn_ID)
{
if (PA_ReportColumn_ID < 1) throw new IllegalArgumentException ("PA_ReportColumn_ID is mandatory.");
set_ValueNoCheck ("PA_ReportColumn_ID", Integer.valueOf(PA_ReportColumn_ID));
}
/** Get Report Column.
@return Column in Report */
public int getPA_ReportColumn_ID() 
{
Integer ii = (Integer)get_Value("PA_ReportColumn_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name PA_ReportColumn_ID */
public static final String COLUMNNAME_PA_ReportColumn_ID = "PA_ReportColumn_ID";

/** PostingType AD_Reference_ID=125 */
public static final int POSTINGTYPE_AD_Reference_ID=125;
/** Actual = A */
public static final String POSTINGTYPE_Actual = "A";
/** Budget = B */
public static final String POSTINGTYPE_Budget = "B";
/** Commitment = E */
public static final String POSTINGTYPE_Commitment = "E";
/** Reservation = R */
public static final String POSTINGTYPE_Reservation = "R";
/** Statistical = S */
public static final String POSTINGTYPE_Statistical = "S";
/** Set PostingType.
@param PostingType The type of posted amount for the transaction */
public void setPostingType (String PostingType)
{
if (PostingType == null) throw new IllegalArgumentException ("PostingType is mandatory");
if (PostingType.equals("A") || PostingType.equals("B") || PostingType.equals("E") || PostingType.equals("R") || PostingType.equals("S"));
 else throw new IllegalArgumentException ("PostingType Invalid value - " + PostingType + " - Reference_ID=125 - A - B - E - R - S");
if (PostingType.length() > 1)
{
log.warning("Length > 1 - truncated");
PostingType = PostingType.substring(0,0);
}
set_Value ("PostingType", PostingType);
}
/** Get PostingType.
@return The type of posted amount for the transaction */
public String getPostingType() 
{
return (String)get_Value("PostingType");
}
/** Column name PostingType */
public static final String COLUMNNAME_PostingType = "PostingType";
/** Set Relative Period.
@param RelativePeriod Period offset (0 is current) */
public void setRelativePeriod (BigDecimal RelativePeriod)
{
set_Value ("RelativePeriod", RelativePeriod);
}
/** Get Relative Period.
@return Period offset (0 is current) */
public BigDecimal getRelativePeriod() 
{
BigDecimal bd = (BigDecimal)get_Value("RelativePeriod");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name RelativePeriod */
public static final String COLUMNNAME_RelativePeriod = "RelativePeriod";
/** Set Sequence.
@param SeqNo Method of ordering records;
 lowest number comes first */
public void setSeqNo (int SeqNo)
{
set_Value ("SeqNo", Integer.valueOf(SeqNo));
}
/** Get Sequence.
@return Method of ordering records;
 lowest number comes first */
public int getSeqNo() 
{
Integer ii = (Integer)get_Value("SeqNo");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name SeqNo */
public static final String COLUMNNAME_SeqNo = "SeqNo";
}
