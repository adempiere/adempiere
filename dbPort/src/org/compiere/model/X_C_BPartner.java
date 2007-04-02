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
/** Generated Model for C_BPartner
 *  @author Adempiere (generated) 
 *  @version Release 3.1.6 - $Id$ */
public class X_C_BPartner extends PO
{
/** Standard Constructor
@param ctx context
@param C_BPartner_ID id
@param trxName transaction
*/
public X_C_BPartner (Properties ctx, int C_BPartner_ID, String trxName)
{
super (ctx, C_BPartner_ID, trxName);
/** if (C_BPartner_ID == 0)
{
setC_BP_Group_ID (0);
setC_BPartner_ID (0);
setIsCustomer (false);
setIsEmployee (false);
setIsOneTime (false);
setIsProspect (false);
setIsSalesRep (false);
setIsSummary (false);
setIsVendor (false);
setName (null);
setSO_CreditLimit (Env.ZERO);
setSO_CreditUsed (Env.ZERO);
setSendEMail (false);
setValue (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_BPartner (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=291 */
public static final int Table_ID=MTable.getTable_ID("C_BPartner");

/** TableName=C_BPartner */
public static final String Table_Name="C_BPartner";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"C_BPartner");

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
StringBuffer sb = new StringBuffer ("X_C_BPartner[").append(get_ID()).append("]");
return sb.toString();
}

/** AD_Language AD_Reference_ID=327 */
public static final int AD_LANGUAGE_AD_Reference_ID=327;
/** Set Language.
@param AD_Language Language for this entity */
public void setAD_Language (String AD_Language)
{
if (AD_Language != null && AD_Language.length() > 6)
{
log.warning("Length > 6 - truncated");
AD_Language = AD_Language.substring(0,5);
}
set_Value ("AD_Language", AD_Language);
}
/** Get Language.
@return Language for this entity */
public String getAD_Language() 
{
return (String)get_Value("AD_Language");
}
/** Column name AD_Language */
public static final String COLUMNNAME_AD_Language = "AD_Language";
/** Set Linked Organization.
@param AD_OrgBP_ID The Business Partner is another Organization for explicit Inter-Org transactions */
public void setAD_OrgBP_ID (String AD_OrgBP_ID)
{
if (AD_OrgBP_ID != null && AD_OrgBP_ID.length() > 22)
{
log.warning("Length > 22 - truncated");
AD_OrgBP_ID = AD_OrgBP_ID.substring(0,21);
}
set_Value ("AD_OrgBP_ID", AD_OrgBP_ID);
}
/** Get Linked Organization.
@return The Business Partner is another Organization for explicit Inter-Org transactions */
public String getAD_OrgBP_ID() 
{
return (String)get_Value("AD_OrgBP_ID");
}
/** Column name AD_OrgBP_ID */
public static final String COLUMNNAME_AD_OrgBP_ID = "AD_OrgBP_ID";
/** Set Acquisition Cost.
@param AcqusitionCost The cost of gaining the prospect as a customer */
public void setAcqusitionCost (BigDecimal AcqusitionCost)
{
set_Value ("AcqusitionCost", AcqusitionCost);
}
/** Get Acquisition Cost.
@return The cost of gaining the prospect as a customer */
public BigDecimal getAcqusitionCost() 
{
BigDecimal bd = (BigDecimal)get_Value("AcqusitionCost");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name AcqusitionCost */
public static final String COLUMNNAME_AcqusitionCost = "AcqusitionCost";
/** Set Actual Life Time Value.
@param ActualLifeTimeValue Actual Life Time Revenue */
public void setActualLifeTimeValue (BigDecimal ActualLifeTimeValue)
{
set_Value ("ActualLifeTimeValue", ActualLifeTimeValue);
}
/** Get Actual Life Time Value.
@return Actual Life Time Revenue */
public BigDecimal getActualLifeTimeValue() 
{
BigDecimal bd = (BigDecimal)get_Value("ActualLifeTimeValue");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name ActualLifeTimeValue */
public static final String COLUMNNAME_ActualLifeTimeValue = "ActualLifeTimeValue";
/** Set Partner Parent.
@param BPartner_Parent_ID Business Partner Parent */
public void setBPartner_Parent_ID (int BPartner_Parent_ID)
{
if (BPartner_Parent_ID <= 0) set_Value ("BPartner_Parent_ID", null);
 else 
set_Value ("BPartner_Parent_ID", Integer.valueOf(BPartner_Parent_ID));
}
/** Get Partner Parent.
@return Business Partner Parent */
public int getBPartner_Parent_ID() 
{
Integer ii = (Integer)get_Value("BPartner_Parent_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name BPartner_Parent_ID */
public static final String COLUMNNAME_BPartner_Parent_ID = "BPartner_Parent_ID";
/** Set Business Partner Group.
@param C_BP_Group_ID Business Partner Group */
public void setC_BP_Group_ID (int C_BP_Group_ID)
{
if (C_BP_Group_ID < 1) throw new IllegalArgumentException ("C_BP_Group_ID is mandatory.");
set_Value ("C_BP_Group_ID", Integer.valueOf(C_BP_Group_ID));
}
/** Get Business Partner Group.
@return Business Partner Group */
public int getC_BP_Group_ID() 
{
Integer ii = (Integer)get_Value("C_BP_Group_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_BP_Group_ID */
public static final String COLUMNNAME_C_BP_Group_ID = "C_BP_Group_ID";
/** Set Business Partner .
@param C_BPartner_ID Identifies a Business Partner */
public void setC_BPartner_ID (int C_BPartner_ID)
{
if (C_BPartner_ID < 1) throw new IllegalArgumentException ("C_BPartner_ID is mandatory.");
set_ValueNoCheck ("C_BPartner_ID", Integer.valueOf(C_BPartner_ID));
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
/** Set Dunning.
@param C_Dunning_ID Dunning Rules for overdue invoices */
public void setC_Dunning_ID (int C_Dunning_ID)
{
if (C_Dunning_ID <= 0) set_Value ("C_Dunning_ID", null);
 else 
set_Value ("C_Dunning_ID", Integer.valueOf(C_Dunning_ID));
}
/** Get Dunning.
@return Dunning Rules for overdue invoices */
public int getC_Dunning_ID() 
{
Integer ii = (Integer)get_Value("C_Dunning_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_Dunning_ID */
public static final String COLUMNNAME_C_Dunning_ID = "C_Dunning_ID";
/** Set Greeting.
@param C_Greeting_ID Greeting to print on correspondence */
public void setC_Greeting_ID (int C_Greeting_ID)
{
if (C_Greeting_ID <= 0) set_Value ("C_Greeting_ID", null);
 else 
set_Value ("C_Greeting_ID", Integer.valueOf(C_Greeting_ID));
}
/** Get Greeting.
@return Greeting to print on correspondence */
public int getC_Greeting_ID() 
{
Integer ii = (Integer)get_Value("C_Greeting_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_Greeting_ID */
public static final String COLUMNNAME_C_Greeting_ID = "C_Greeting_ID";
/** Set Invoice Schedule.
@param C_InvoiceSchedule_ID Schedule for generating Invoices */
public void setC_InvoiceSchedule_ID (int C_InvoiceSchedule_ID)
{
if (C_InvoiceSchedule_ID <= 0) set_Value ("C_InvoiceSchedule_ID", null);
 else 
set_Value ("C_InvoiceSchedule_ID", Integer.valueOf(C_InvoiceSchedule_ID));
}
/** Get Invoice Schedule.
@return Schedule for generating Invoices */
public int getC_InvoiceSchedule_ID() 
{
Integer ii = (Integer)get_Value("C_InvoiceSchedule_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_InvoiceSchedule_ID */
public static final String COLUMNNAME_C_InvoiceSchedule_ID = "C_InvoiceSchedule_ID";
/** Set Payment Term.
@param C_PaymentTerm_ID The terms of Payment (timing, discount) */
public void setC_PaymentTerm_ID (int C_PaymentTerm_ID)
{
if (C_PaymentTerm_ID <= 0) set_Value ("C_PaymentTerm_ID", null);
 else 
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
/** Column name C_PaymentTerm_ID */
public static final String COLUMNNAME_C_PaymentTerm_ID = "C_PaymentTerm_ID";
/** Set D-U-N-S.
@param DUNS Dun & Bradstreet Number */
public void setDUNS (String DUNS)
{
if (DUNS != null && DUNS.length() > 11)
{
log.warning("Length > 11 - truncated");
DUNS = DUNS.substring(0,10);
}
set_Value ("DUNS", DUNS);
}
/** Get D-U-N-S.
@return Dun & Bradstreet Number */
public String getDUNS() 
{
return (String)get_Value("DUNS");
}
/** Column name DUNS */
public static final String COLUMNNAME_DUNS = "DUNS";

/** DeliveryRule AD_Reference_ID=151 */
public static final int DELIVERYRULE_AD_Reference_ID=151;
/** Availability = A */
public static final String DELIVERYRULE_Availability = "A";
/** Force = F */
public static final String DELIVERYRULE_Force = "F";
/** Complete Line = L */
public static final String DELIVERYRULE_CompleteLine = "L";
/** Manual = M */
public static final String DELIVERYRULE_Manual = "M";
/** Complete Order = O */
public static final String DELIVERYRULE_CompleteOrder = "O";
/** After Receipt = R */
public static final String DELIVERYRULE_AfterReceipt = "R";
/** Set Delivery Rule.
@param DeliveryRule Defines the timing of Delivery */
public void setDeliveryRule (String DeliveryRule)
{
if (DeliveryRule == null || DeliveryRule.equals("A") || DeliveryRule.equals("F") || DeliveryRule.equals("L") || DeliveryRule.equals("M") || DeliveryRule.equals("O") || DeliveryRule.equals("R"));
 else throw new IllegalArgumentException ("DeliveryRule Invalid value - " + DeliveryRule + " - Reference_ID=151 - A - F - L - M - O - R");
if (DeliveryRule != null && DeliveryRule.length() > 1)
{
log.warning("Length > 1 - truncated");
DeliveryRule = DeliveryRule.substring(0,0);
}
set_Value ("DeliveryRule", DeliveryRule);
}
/** Get Delivery Rule.
@return Defines the timing of Delivery */
public String getDeliveryRule() 
{
return (String)get_Value("DeliveryRule");
}
/** Column name DeliveryRule */
public static final String COLUMNNAME_DeliveryRule = "DeliveryRule";

/** DeliveryViaRule AD_Reference_ID=152 */
public static final int DELIVERYVIARULE_AD_Reference_ID=152;
/** Delivery = D */
public static final String DELIVERYVIARULE_Delivery = "D";
/** Pickup = P */
public static final String DELIVERYVIARULE_Pickup = "P";
/** Shipper = S */
public static final String DELIVERYVIARULE_Shipper = "S";
/** Set Delivery Via.
@param DeliveryViaRule How the order will be delivered */
public void setDeliveryViaRule (String DeliveryViaRule)
{
if (DeliveryViaRule == null || DeliveryViaRule.equals("D") || DeliveryViaRule.equals("P") || DeliveryViaRule.equals("S"));
 else throw new IllegalArgumentException ("DeliveryViaRule Invalid value - " + DeliveryViaRule + " - Reference_ID=152 - D - P - S");
if (DeliveryViaRule != null && DeliveryViaRule.length() > 1)
{
log.warning("Length > 1 - truncated");
DeliveryViaRule = DeliveryViaRule.substring(0,0);
}
set_Value ("DeliveryViaRule", DeliveryViaRule);
}
/** Get Delivery Via.
@return How the order will be delivered */
public String getDeliveryViaRule() 
{
return (String)get_Value("DeliveryViaRule");
}
/** Column name DeliveryViaRule */
public static final String COLUMNNAME_DeliveryViaRule = "DeliveryViaRule";
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
/** Set Document Copies.
@param DocumentCopies Number of copies to be printed */
public void setDocumentCopies (int DocumentCopies)
{
set_Value ("DocumentCopies", Integer.valueOf(DocumentCopies));
}
/** Get Document Copies.
@return Number of copies to be printed */
public int getDocumentCopies() 
{
Integer ii = (Integer)get_Value("DocumentCopies");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name DocumentCopies */
public static final String COLUMNNAME_DocumentCopies = "DocumentCopies";
/** Set First Sale.
@param FirstSale Date of First Sale */
public void setFirstSale (Timestamp FirstSale)
{
set_Value ("FirstSale", FirstSale);
}
/** Get First Sale.
@return Date of First Sale */
public Timestamp getFirstSale() 
{
return (Timestamp)get_Value("FirstSale");
}
/** Column name FirstSale */
public static final String COLUMNNAME_FirstSale = "FirstSale";
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
/** Column name FlatDiscount */
public static final String COLUMNNAME_FlatDiscount = "FlatDiscount";

/** FreightCostRule AD_Reference_ID=153 */
public static final int FREIGHTCOSTRULE_AD_Reference_ID=153;
/** Calculated = C */
public static final String FREIGHTCOSTRULE_Calculated = "C";
/** Fix price = F */
public static final String FREIGHTCOSTRULE_FixPrice = "F";
/** Freight included = I */
public static final String FREIGHTCOSTRULE_FreightIncluded = "I";
/** Line = L */
public static final String FREIGHTCOSTRULE_Line = "L";
/** Set Freight Cost Rule.
@param FreightCostRule Method for charging Freight */
public void setFreightCostRule (String FreightCostRule)
{
if (FreightCostRule == null || FreightCostRule.equals("C") || FreightCostRule.equals("F") || FreightCostRule.equals("I") || FreightCostRule.equals("L"));
 else throw new IllegalArgumentException ("FreightCostRule Invalid value - " + FreightCostRule + " - Reference_ID=153 - C - F - I - L");
if (FreightCostRule != null && FreightCostRule.length() > 1)
{
log.warning("Length > 1 - truncated");
FreightCostRule = FreightCostRule.substring(0,0);
}
set_Value ("FreightCostRule", FreightCostRule);
}
/** Get Freight Cost Rule.
@return Method for charging Freight */
public String getFreightCostRule() 
{
return (String)get_Value("FreightCostRule");
}
/** Column name FreightCostRule */
public static final String COLUMNNAME_FreightCostRule = "FreightCostRule";

/** InvoiceRule AD_Reference_ID=150 */
public static final int INVOICERULE_AD_Reference_ID=150;
/** After Delivery = D */
public static final String INVOICERULE_AfterDelivery = "D";
/** Immediate = I */
public static final String INVOICERULE_Immediate = "I";
/** After Order delivered = O */
public static final String INVOICERULE_AfterOrderDelivered = "O";
/** Customer Schedule after Delivery = S */
public static final String INVOICERULE_CustomerScheduleAfterDelivery = "S";
/** Set Invoice Rule.
@param InvoiceRule Frequency and method of invoicing  */
public void setInvoiceRule (String InvoiceRule)
{
if (InvoiceRule == null || InvoiceRule.equals("D") || InvoiceRule.equals("I") || InvoiceRule.equals("O") || InvoiceRule.equals("S"));
 else throw new IllegalArgumentException ("InvoiceRule Invalid value - " + InvoiceRule + " - Reference_ID=150 - D - I - O - S");
if (InvoiceRule != null && InvoiceRule.length() > 1)
{
log.warning("Length > 1 - truncated");
InvoiceRule = InvoiceRule.substring(0,0);
}
set_Value ("InvoiceRule", InvoiceRule);
}
/** Get Invoice Rule.
@return Frequency and method of invoicing  */
public String getInvoiceRule() 
{
return (String)get_Value("InvoiceRule");
}
/** Column name InvoiceRule */
public static final String COLUMNNAME_InvoiceRule = "InvoiceRule";

/** Invoice_PrintFormat_ID AD_Reference_ID=261 */
public static final int INVOICE_PRINTFORMAT_ID_AD_Reference_ID=261;
/** Set Invoice Print Format.
@param Invoice_PrintFormat_ID Print Format for printing Invoices */
public void setInvoice_PrintFormat_ID (int Invoice_PrintFormat_ID)
{
if (Invoice_PrintFormat_ID <= 0) set_Value ("Invoice_PrintFormat_ID", null);
 else 
set_Value ("Invoice_PrintFormat_ID", Integer.valueOf(Invoice_PrintFormat_ID));
}
/** Get Invoice Print Format.
@return Print Format for printing Invoices */
public int getInvoice_PrintFormat_ID() 
{
Integer ii = (Integer)get_Value("Invoice_PrintFormat_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name Invoice_PrintFormat_ID */
public static final String COLUMNNAME_Invoice_PrintFormat_ID = "Invoice_PrintFormat_ID";
/** Set Customer.
@param IsCustomer Indicates if this Business Partner is a Customer */
public void setIsCustomer (boolean IsCustomer)
{
set_Value ("IsCustomer", Boolean.valueOf(IsCustomer));
}
/** Get Customer.
@return Indicates if this Business Partner is a Customer */
public boolean isCustomer() 
{
Object oo = get_Value("IsCustomer");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsCustomer */
public static final String COLUMNNAME_IsCustomer = "IsCustomer";
/** Set Discount Printed.
@param IsDiscountPrinted Print Discount on Invoice and Order */
public void setIsDiscountPrinted (boolean IsDiscountPrinted)
{
set_Value ("IsDiscountPrinted", Boolean.valueOf(IsDiscountPrinted));
}
/** Get Discount Printed.
@return Print Discount on Invoice and Order */
public boolean isDiscountPrinted() 
{
Object oo = get_Value("IsDiscountPrinted");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsDiscountPrinted */
public static final String COLUMNNAME_IsDiscountPrinted = "IsDiscountPrinted";
/** Set Employee.
@param IsEmployee Indicates if  this Business Partner is an employee */
public void setIsEmployee (boolean IsEmployee)
{
set_Value ("IsEmployee", Boolean.valueOf(IsEmployee));
}
/** Get Employee.
@return Indicates if  this Business Partner is an employee */
public boolean isEmployee() 
{
Object oo = get_Value("IsEmployee");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsEmployee */
public static final String COLUMNNAME_IsEmployee = "IsEmployee";
/** Set One time transaction.
@param IsOneTime One time transaction */
public void setIsOneTime (boolean IsOneTime)
{
set_Value ("IsOneTime", Boolean.valueOf(IsOneTime));
}
/** Get One time transaction.
@return One time transaction */
public boolean isOneTime() 
{
Object oo = get_Value("IsOneTime");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsOneTime */
public static final String COLUMNNAME_IsOneTime = "IsOneTime";
/** Set Prospect.
@param IsProspect Indicates this is a Prospect */
public void setIsProspect (boolean IsProspect)
{
set_Value ("IsProspect", Boolean.valueOf(IsProspect));
}
/** Get Prospect.
@return Indicates this is a Prospect */
public boolean isProspect() 
{
Object oo = get_Value("IsProspect");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsProspect */
public static final String COLUMNNAME_IsProspect = "IsProspect";
/** Set Sales Representative.
@param IsSalesRep Indicates if  the business partner is a sales representative or company agent */
public void setIsSalesRep (boolean IsSalesRep)
{
set_Value ("IsSalesRep", Boolean.valueOf(IsSalesRep));
}
/** Get Sales Representative.
@return Indicates if  the business partner is a sales representative or company agent */
public boolean isSalesRep() 
{
Object oo = get_Value("IsSalesRep");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsSalesRep */
public static final String COLUMNNAME_IsSalesRep = "IsSalesRep";
/** Set Summary Level.
@param IsSummary This is a summary entity */
public void setIsSummary (boolean IsSummary)
{
set_Value ("IsSummary", Boolean.valueOf(IsSummary));
}
/** Get Summary Level.
@return This is a summary entity */
public boolean isSummary() 
{
Object oo = get_Value("IsSummary");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsSummary */
public static final String COLUMNNAME_IsSummary = "IsSummary";
/** Set Tax exempt.
@param IsTaxExempt Business partner is exempt from tax */
public void setIsTaxExempt (boolean IsTaxExempt)
{
set_Value ("IsTaxExempt", Boolean.valueOf(IsTaxExempt));
}
/** Get Tax exempt.
@return Business partner is exempt from tax */
public boolean isTaxExempt() 
{
Object oo = get_Value("IsTaxExempt");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsTaxExempt */
public static final String COLUMNNAME_IsTaxExempt = "IsTaxExempt";
/** Set Vendor.
@param IsVendor Indicates if this Business Partner is a Vendor */
public void setIsVendor (boolean IsVendor)
{
set_Value ("IsVendor", Boolean.valueOf(IsVendor));
}
/** Get Vendor.
@return Indicates if this Business Partner is a Vendor */
public boolean isVendor() 
{
Object oo = get_Value("IsVendor");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsVendor */
public static final String COLUMNNAME_IsVendor = "IsVendor";

/** M_DiscountSchema_ID AD_Reference_ID=325 */
public static final int M_DISCOUNTSCHEMA_ID_AD_Reference_ID=325;
/** Set Discount Schema.
@param M_DiscountSchema_ID Schema to calculate the trade discount percentage */
public void setM_DiscountSchema_ID (int M_DiscountSchema_ID)
{
if (M_DiscountSchema_ID <= 0) set_Value ("M_DiscountSchema_ID", null);
 else 
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
/** Set NAICS/SIC.
@param NAICS Standard Industry Code or its successor NAIC - http://www.osha.gov/oshstats/sicser.html */
public void setNAICS (String NAICS)
{
if (NAICS != null && NAICS.length() > 6)
{
log.warning("Length > 6 - truncated");
NAICS = NAICS.substring(0,5);
}
set_Value ("NAICS", NAICS);
}
/** Get NAICS/SIC.
@return Standard Industry Code or its successor NAIC - http://www.osha.gov/oshstats/sicser.html */
public String getNAICS() 
{
return (String)get_Value("NAICS");
}
/** Column name NAICS */
public static final String COLUMNNAME_NAICS = "NAICS";
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
/** Set Name 2.
@param Name2 Additional Name */
public void setName2 (String Name2)
{
if (Name2 != null && Name2.length() > 60)
{
log.warning("Length > 60 - truncated");
Name2 = Name2.substring(0,59);
}
set_Value ("Name2", Name2);
}
/** Get Name 2.
@return Additional Name */
public String getName2() 
{
return (String)get_Value("Name2");
}
/** Column name Name2 */
public static final String COLUMNNAME_Name2 = "Name2";
/** Set Employees.
@param NumberEmployees Number of employees */
public void setNumberEmployees (int NumberEmployees)
{
set_Value ("NumberEmployees", Integer.valueOf(NumberEmployees));
}
/** Get Employees.
@return Number of employees */
public int getNumberEmployees() 
{
Integer ii = (Integer)get_Value("NumberEmployees");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name NumberEmployees */
public static final String COLUMNNAME_NumberEmployees = "NumberEmployees";
/** Set Order Reference.
@param POReference Transaction Reference Number (Sales Order, Purchase Order) of your Business Partner */
public void setPOReference (String POReference)
{
if (POReference != null && POReference.length() > 20)
{
log.warning("Length > 20 - truncated");
POReference = POReference.substring(0,19);
}
set_Value ("POReference", POReference);
}
/** Get Order Reference.
@return Transaction Reference Number (Sales Order, Purchase Order) of your Business Partner */
public String getPOReference() 
{
return (String)get_Value("POReference");
}
/** Column name POReference */
public static final String COLUMNNAME_POReference = "POReference";

/** PO_DiscountSchema_ID AD_Reference_ID=325 */
public static final int PO_DISCOUNTSCHEMA_ID_AD_Reference_ID=325;
/** Set PO Discount Schema.
@param PO_DiscountSchema_ID Schema to calculate the purchase trade discount percentage */
public void setPO_DiscountSchema_ID (int PO_DiscountSchema_ID)
{
if (PO_DiscountSchema_ID <= 0) set_Value ("PO_DiscountSchema_ID", null);
 else 
set_Value ("PO_DiscountSchema_ID", Integer.valueOf(PO_DiscountSchema_ID));
}
/** Get PO Discount Schema.
@return Schema to calculate the purchase trade discount percentage */
public int getPO_DiscountSchema_ID() 
{
Integer ii = (Integer)get_Value("PO_DiscountSchema_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name PO_DiscountSchema_ID */
public static final String COLUMNNAME_PO_DiscountSchema_ID = "PO_DiscountSchema_ID";

/** PO_PaymentTerm_ID AD_Reference_ID=227 */
public static final int PO_PAYMENTTERM_ID_AD_Reference_ID=227;
/** Set PO Payment Term.
@param PO_PaymentTerm_ID Payment rules for a purchase order */
public void setPO_PaymentTerm_ID (int PO_PaymentTerm_ID)
{
if (PO_PaymentTerm_ID <= 0) set_Value ("PO_PaymentTerm_ID", null);
 else 
set_Value ("PO_PaymentTerm_ID", Integer.valueOf(PO_PaymentTerm_ID));
}
/** Get PO Payment Term.
@return Payment rules for a purchase order */
public int getPO_PaymentTerm_ID() 
{
Integer ii = (Integer)get_Value("PO_PaymentTerm_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name PO_PaymentTerm_ID */
public static final String COLUMNNAME_PO_PaymentTerm_ID = "PO_PaymentTerm_ID";

/** PO_PriceList_ID AD_Reference_ID=166 */
public static final int PO_PRICELIST_ID_AD_Reference_ID=166;
/** Set Purchase Pricelist.
@param PO_PriceList_ID Price List used by this Business Partner */
public void setPO_PriceList_ID (int PO_PriceList_ID)
{
if (PO_PriceList_ID <= 0) set_Value ("PO_PriceList_ID", null);
 else 
set_Value ("PO_PriceList_ID", Integer.valueOf(PO_PriceList_ID));
}
/** Get Purchase Pricelist.
@return Price List used by this Business Partner */
public int getPO_PriceList_ID() 
{
Integer ii = (Integer)get_Value("PO_PriceList_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name PO_PriceList_ID */
public static final String COLUMNNAME_PO_PriceList_ID = "PO_PriceList_ID";

/** PaymentRule AD_Reference_ID=195 */
public static final int PAYMENTRULE_AD_Reference_ID=195;
/** Cash = B */
public static final String PAYMENTRULE_Cash = "B";
/** Direct Debit = D */
public static final String PAYMENTRULE_DirectDebit = "D";
/** Credit Card = K */
public static final String PAYMENTRULE_CreditCard = "K";
/** On Credit = P */
public static final String PAYMENTRULE_OnCredit = "P";
/** Check = S */
public static final String PAYMENTRULE_Check = "S";
/** Direct Deposit = T */
public static final String PAYMENTRULE_DirectDeposit = "T";
/** Set Payment Rule.
@param PaymentRule How you pay the invoice */
public void setPaymentRule (String PaymentRule)
{
if (PaymentRule == null || PaymentRule.equals("B") || PaymentRule.equals("D") || PaymentRule.equals("K") || PaymentRule.equals("P") || PaymentRule.equals("S") || PaymentRule.equals("T"));
 else throw new IllegalArgumentException ("PaymentRule Invalid value - " + PaymentRule + " - Reference_ID=195 - B - D - K - P - S - T");
if (PaymentRule != null && PaymentRule.length() > 1)
{
log.warning("Length > 1 - truncated");
PaymentRule = PaymentRule.substring(0,0);
}
set_Value ("PaymentRule", PaymentRule);
}
/** Get Payment Rule.
@return How you pay the invoice */
public String getPaymentRule() 
{
return (String)get_Value("PaymentRule");
}
/** Column name PaymentRule */
public static final String COLUMNNAME_PaymentRule = "PaymentRule";

/** PaymentRulePO AD_Reference_ID=195 */
public static final int PAYMENTRULEPO_AD_Reference_ID=195;
/** Cash = B */
public static final String PAYMENTRULEPO_Cash = "B";
/** Direct Debit = D */
public static final String PAYMENTRULEPO_DirectDebit = "D";
/** Credit Card = K */
public static final String PAYMENTRULEPO_CreditCard = "K";
/** On Credit = P */
public static final String PAYMENTRULEPO_OnCredit = "P";
/** Check = S */
public static final String PAYMENTRULEPO_Check = "S";
/** Direct Deposit = T */
public static final String PAYMENTRULEPO_DirectDeposit = "T";
/** Set Payment Rule.
@param PaymentRulePO Purchase payment option */
public void setPaymentRulePO (String PaymentRulePO)
{
if (PaymentRulePO == null || PaymentRulePO.equals("B") || PaymentRulePO.equals("D") || PaymentRulePO.equals("K") || PaymentRulePO.equals("P") || PaymentRulePO.equals("S") || PaymentRulePO.equals("T"));
 else throw new IllegalArgumentException ("PaymentRulePO Invalid value - " + PaymentRulePO + " - Reference_ID=195 - B - D - K - P - S - T");
if (PaymentRulePO != null && PaymentRulePO.length() > 1)
{
log.warning("Length > 1 - truncated");
PaymentRulePO = PaymentRulePO.substring(0,0);
}
set_Value ("PaymentRulePO", PaymentRulePO);
}
/** Get Payment Rule.
@return Purchase payment option */
public String getPaymentRulePO() 
{
return (String)get_Value("PaymentRulePO");
}
/** Column name PaymentRulePO */
public static final String COLUMNNAME_PaymentRulePO = "PaymentRulePO";
/** Set Potential Life Time Value.
@param PotentialLifeTimeValue Total Revenue expected */
public void setPotentialLifeTimeValue (BigDecimal PotentialLifeTimeValue)
{
set_Value ("PotentialLifeTimeValue", PotentialLifeTimeValue);
}
/** Get Potential Life Time Value.
@return Total Revenue expected */
public BigDecimal getPotentialLifeTimeValue() 
{
BigDecimal bd = (BigDecimal)get_Value("PotentialLifeTimeValue");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name PotentialLifeTimeValue */
public static final String COLUMNNAME_PotentialLifeTimeValue = "PotentialLifeTimeValue";
/** Set Rating.
@param Rating Classification or Importance */
public void setRating (String Rating)
{
if (Rating != null && Rating.length() > 1)
{
log.warning("Length > 1 - truncated");
Rating = Rating.substring(0,0);
}
set_Value ("Rating", Rating);
}
/** Get Rating.
@return Classification or Importance */
public String getRating() 
{
return (String)get_Value("Rating");
}
/** Column name Rating */
public static final String COLUMNNAME_Rating = "Rating";
/** Set Reference No.
@param ReferenceNo Your customer or vendor number at the Business Partner's site */
public void setReferenceNo (String ReferenceNo)
{
if (ReferenceNo != null && ReferenceNo.length() > 40)
{
log.warning("Length > 40 - truncated");
ReferenceNo = ReferenceNo.substring(0,39);
}
set_Value ("ReferenceNo", ReferenceNo);
}
/** Get Reference No.
@return Your customer or vendor number at the Business Partner's site */
public String getReferenceNo() 
{
return (String)get_Value("ReferenceNo");
}
/** Column name ReferenceNo */
public static final String COLUMNNAME_ReferenceNo = "ReferenceNo";

/** SOCreditStatus AD_Reference_ID=289 */
public static final int SOCREDITSTATUS_AD_Reference_ID=289;
/** Credit Hold = H */
public static final String SOCREDITSTATUS_CreditHold = "H";
/** Credit OK = O */
public static final String SOCREDITSTATUS_CreditOK = "O";
/** Credit Stop = S */
public static final String SOCREDITSTATUS_CreditStop = "S";
/** Credit Watch = W */
public static final String SOCREDITSTATUS_CreditWatch = "W";
/** No Credit Check = X */
public static final String SOCREDITSTATUS_NoCreditCheck = "X";
/** Set Credit Status.
@param SOCreditStatus Business Partner Credit Status */
public void setSOCreditStatus (String SOCreditStatus)
{
if (SOCreditStatus == null || SOCreditStatus.equals("H") || SOCreditStatus.equals("O") || SOCreditStatus.equals("S") || SOCreditStatus.equals("W") || SOCreditStatus.equals("X"));
 else throw new IllegalArgumentException ("SOCreditStatus Invalid value - " + SOCreditStatus + " - Reference_ID=289 - H - O - S - W - X");
if (SOCreditStatus != null && SOCreditStatus.length() > 1)
{
log.warning("Length > 1 - truncated");
SOCreditStatus = SOCreditStatus.substring(0,0);
}
set_Value ("SOCreditStatus", SOCreditStatus);
}
/** Get Credit Status.
@return Business Partner Credit Status */
public String getSOCreditStatus() 
{
return (String)get_Value("SOCreditStatus");
}
/** Column name SOCreditStatus */
public static final String COLUMNNAME_SOCreditStatus = "SOCreditStatus";
/** Set Credit Limit.
@param SO_CreditLimit Total outstanding invoice amounts allowed */
public void setSO_CreditLimit (BigDecimal SO_CreditLimit)
{
if (SO_CreditLimit == null) throw new IllegalArgumentException ("SO_CreditLimit is mandatory.");
set_Value ("SO_CreditLimit", SO_CreditLimit);
}
/** Get Credit Limit.
@return Total outstanding invoice amounts allowed */
public BigDecimal getSO_CreditLimit() 
{
BigDecimal bd = (BigDecimal)get_Value("SO_CreditLimit");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name SO_CreditLimit */
public static final String COLUMNNAME_SO_CreditLimit = "SO_CreditLimit";
/** Set Credit Used.
@param SO_CreditUsed Current open balance */
public void setSO_CreditUsed (BigDecimal SO_CreditUsed)
{
if (SO_CreditUsed == null) throw new IllegalArgumentException ("SO_CreditUsed is mandatory.");
set_ValueNoCheck ("SO_CreditUsed", SO_CreditUsed);
}
/** Get Credit Used.
@return Current open balance */
public BigDecimal getSO_CreditUsed() 
{
BigDecimal bd = (BigDecimal)get_Value("SO_CreditUsed");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name SO_CreditUsed */
public static final String COLUMNNAME_SO_CreditUsed = "SO_CreditUsed";
/** Set Order Description.
@param SO_Description Description to be used on orders */
public void setSO_Description (String SO_Description)
{
if (SO_Description != null && SO_Description.length() > 255)
{
log.warning("Length > 255 - truncated");
SO_Description = SO_Description.substring(0,254);
}
set_Value ("SO_Description", SO_Description);
}
/** Get Order Description.
@return Description to be used on orders */
public String getSO_Description() 
{
return (String)get_Value("SO_Description");
}
/** Column name SO_Description */
public static final String COLUMNNAME_SO_Description = "SO_Description";

/** SalesRep_ID AD_Reference_ID=190 */
public static final int SALESREP_ID_AD_Reference_ID=190;
/** Set Sales Representative.
@param SalesRep_ID Sales Representative or Company Agent */
public void setSalesRep_ID (int SalesRep_ID)
{
if (SalesRep_ID <= 0) set_Value ("SalesRep_ID", null);
 else 
set_Value ("SalesRep_ID", Integer.valueOf(SalesRep_ID));
}
/** Get Sales Representative.
@return Sales Representative or Company Agent */
public int getSalesRep_ID() 
{
Integer ii = (Integer)get_Value("SalesRep_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name SalesRep_ID */
public static final String COLUMNNAME_SalesRep_ID = "SalesRep_ID";
/** Set Sales Volume in 1.000.
@param SalesVolume Total Volume of Sales in Thousands of Currency */
public void setSalesVolume (int SalesVolume)
{
set_Value ("SalesVolume", Integer.valueOf(SalesVolume));
}
/** Get Sales Volume in 1.000.
@return Total Volume of Sales in Thousands of Currency */
public int getSalesVolume() 
{
Integer ii = (Integer)get_Value("SalesVolume");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name SalesVolume */
public static final String COLUMNNAME_SalesVolume = "SalesVolume";
/** Set Send EMail.
@param SendEMail Enable sending Document EMail */
public void setSendEMail (boolean SendEMail)
{
set_Value ("SendEMail", Boolean.valueOf(SendEMail));
}
/** Get Send EMail.
@return Enable sending Document EMail */
public boolean isSendEMail() 
{
Object oo = get_Value("SendEMail");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name SendEMail */
public static final String COLUMNNAME_SendEMail = "SendEMail";
/** Set Share.
@param ShareOfCustomer Share of Customer's business as a percentage */
public void setShareOfCustomer (int ShareOfCustomer)
{
set_Value ("ShareOfCustomer", Integer.valueOf(ShareOfCustomer));
}
/** Get Share.
@return Share of Customer's business as a percentage */
public int getShareOfCustomer() 
{
Integer ii = (Integer)get_Value("ShareOfCustomer");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name ShareOfCustomer */
public static final String COLUMNNAME_ShareOfCustomer = "ShareOfCustomer";
/** Set Min Shelf Life %.
@param ShelfLifeMinPct Minimum Shelf Life in percent based on Product Instance Guarantee Date */
public void setShelfLifeMinPct (int ShelfLifeMinPct)
{
set_Value ("ShelfLifeMinPct", Integer.valueOf(ShelfLifeMinPct));
}
/** Get Min Shelf Life %.
@return Minimum Shelf Life in percent based on Product Instance Guarantee Date */
public int getShelfLifeMinPct() 
{
Integer ii = (Integer)get_Value("ShelfLifeMinPct");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name ShelfLifeMinPct */
public static final String COLUMNNAME_ShelfLifeMinPct = "ShelfLifeMinPct";
/** Set Tax ID.
@param TaxID Tax Identification */
public void setTaxID (String TaxID)
{
if (TaxID != null && TaxID.length() > 20)
{
log.warning("Length > 20 - truncated");
TaxID = TaxID.substring(0,19);
}
set_Value ("TaxID", TaxID);
}
/** Get Tax ID.
@return Tax Identification */
public String getTaxID() 
{
return (String)get_Value("TaxID");
}
/** Column name TaxID */
public static final String COLUMNNAME_TaxID = "TaxID";
/** Set Open Balance.
@param TotalOpenBalance Total Open Balance Amount in primary Accounting Currency */
public void setTotalOpenBalance (BigDecimal TotalOpenBalance)
{
set_Value ("TotalOpenBalance", TotalOpenBalance);
}
/** Get Open Balance.
@return Total Open Balance Amount in primary Accounting Currency */
public BigDecimal getTotalOpenBalance() 
{
BigDecimal bd = (BigDecimal)get_Value("TotalOpenBalance");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name TotalOpenBalance */
public static final String COLUMNNAME_TotalOpenBalance = "TotalOpenBalance";
/** Set URL.
@param URL Full URL address - e.g. http://www.adempiere.org */
public void setURL (String URL)
{
if (URL != null && URL.length() > 120)
{
log.warning("Length > 120 - truncated");
URL = URL.substring(0,119);
}
set_Value ("URL", URL);
}
/** Get URL.
@return Full URL address - e.g. http://www.adempiere.org */
public String getURL() 
{
return (String)get_Value("URL");
}
/** Column name URL */
public static final String COLUMNNAME_URL = "URL";
/** Set Search Key.
@param Value Search key for the record in the format required - must be unique */
public void setValue (String Value)
{
if (Value == null) throw new IllegalArgumentException ("Value is mandatory.");
if (Value.length() > 40)
{
log.warning("Length > 40 - truncated");
Value = Value.substring(0,39);
}
set_Value ("Value", Value);
}
/** Get Search Key.
@return Search key for the record in the format required - must be unique */
public String getValue() 
{
return (String)get_Value("Value");
}
/** Column name Value */
public static final String COLUMNNAME_Value = "Value";
}
