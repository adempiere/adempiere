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
/** Generated Model for RV_BPartner
 *  @author Adempiere (generated) 
 *  @version Release 3.1.5 - $Id$ */
public class X_RV_BPartner extends PO
{
/** Standard Constructor
@param ctx context
@param RV_BPartner_ID id
@param trxName transaction
*/
public X_RV_BPartner (Properties ctx, int RV_BPartner_ID, String trxName)
{
super (ctx, RV_BPartner_ID, trxName);
/** if (RV_BPartner_ID == 0)
{
setC_BP_Group_ID (0);
setC_BPartner_ID (0);
setC_BPartner_Location_ID (0);
setC_Country_ID (0);
setContactName (null);
setCountryName (null);
setIsCustomer (false);
setIsEmployee (false);
setIsOneTime (false);
setIsProspect (false);
setIsSalesRep (false);
setIsSummary (false);
setIsVendor (false);
setLDAPUser (false);
setName (null);
setNotificationType (null);
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
public X_RV_BPartner (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=520 */
public static final int Table_ID=MTable.getTable_ID("RV_BPartner");

/** TableName=RV_BPartner */
public static final String Table_Name="RV_BPartner";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"RV_BPartner");

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
StringBuffer sb = new StringBuffer ("X_RV_BPartner[").append(get_ID()).append("]");
return sb.toString();
}

/** AD_Language AD_Reference_ID=106 */
public static final int AD_LANGUAGE_AD_Reference_ID=106;
/** Set Language.
@param AD_Language Language for this entity */
public void setAD_Language (String AD_Language)
{
if (AD_Language != null && AD_Language.length() > 6)
{
log.warning("Length > 6 - truncated");
AD_Language = AD_Language.substring(0,5);
}
set_ValueNoCheck ("AD_Language", AD_Language);
}
/** Get Language.
@return Language for this entity */
public String getAD_Language() 
{
return (String)get_Value("AD_Language");
}
/** Column name AD_Language */
public static final String COLUMNNAME_AD_Language = "AD_Language";

/** AD_OrgBP_ID AD_Reference_ID=276 */
public static final int AD_ORGBP_ID_AD_Reference_ID=276;
/** Set Linked Organization.
@param AD_OrgBP_ID The Business Partner is another Organization for explicit Inter-Org transactions */
public void setAD_OrgBP_ID (int AD_OrgBP_ID)
{
if (AD_OrgBP_ID <= 0) set_ValueNoCheck ("AD_OrgBP_ID", null);
 else 
set_ValueNoCheck ("AD_OrgBP_ID", Integer.valueOf(AD_OrgBP_ID));
}
/** Get Linked Organization.
@return The Business Partner is another Organization for explicit Inter-Org transactions */
public int getAD_OrgBP_ID() 
{
Integer ii = (Integer)get_Value("AD_OrgBP_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_OrgBP_ID */
public static final String COLUMNNAME_AD_OrgBP_ID = "AD_OrgBP_ID";

/** AD_OrgTrx_ID AD_Reference_ID=276 */
public static final int AD_ORGTRX_ID_AD_Reference_ID=276;
/** Set Trx Organization.
@param AD_OrgTrx_ID Performing or initiating organization */
public void setAD_OrgTrx_ID (int AD_OrgTrx_ID)
{
if (AD_OrgTrx_ID <= 0) set_ValueNoCheck ("AD_OrgTrx_ID", null);
 else 
set_ValueNoCheck ("AD_OrgTrx_ID", Integer.valueOf(AD_OrgTrx_ID));
}
/** Get Trx Organization.
@return Performing or initiating organization */
public int getAD_OrgTrx_ID() 
{
Integer ii = (Integer)get_Value("AD_OrgTrx_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_OrgTrx_ID */
public static final String COLUMNNAME_AD_OrgTrx_ID = "AD_OrgTrx_ID";
/** Set User/Contact.
@param AD_User_ID User within the system - Internal or Business Partner Contact */
public void setAD_User_ID (int AD_User_ID)
{
if (AD_User_ID <= 0) set_ValueNoCheck ("AD_User_ID", null);
 else 
set_ValueNoCheck ("AD_User_ID", Integer.valueOf(AD_User_ID));
}
/** Get User/Contact.
@return User within the system - Internal or Business Partner Contact */
public int getAD_User_ID() 
{
Integer ii = (Integer)get_Value("AD_User_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_User_ID */
public static final String COLUMNNAME_AD_User_ID = "AD_User_ID";
/** Set Acquisition Cost.
@param AcqusitionCost The cost of gaining the prospect as a customer */
public void setAcqusitionCost (BigDecimal AcqusitionCost)
{
set_ValueNoCheck ("AcqusitionCost", AcqusitionCost);
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
set_ValueNoCheck ("ActualLifeTimeValue", ActualLifeTimeValue);
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
/** Set Address 1.
@param Address1 Address line 1 for this location */
public void setAddress1 (String Address1)
{
if (Address1 != null && Address1.length() > 60)
{
log.warning("Length > 60 - truncated");
Address1 = Address1.substring(0,59);
}
set_ValueNoCheck ("Address1", Address1);
}
/** Get Address 1.
@return Address line 1 for this location */
public String getAddress1() 
{
return (String)get_Value("Address1");
}
/** Column name Address1 */
public static final String COLUMNNAME_Address1 = "Address1";
/** Set Address 2.
@param Address2 Address line 2 for this location */
public void setAddress2 (String Address2)
{
if (Address2 != null && Address2.length() > 60)
{
log.warning("Length > 60 - truncated");
Address2 = Address2.substring(0,59);
}
set_ValueNoCheck ("Address2", Address2);
}
/** Get Address 2.
@return Address line 2 for this location */
public String getAddress2() 
{
return (String)get_Value("Address2");
}
/** Column name Address2 */
public static final String COLUMNNAME_Address2 = "Address2";
/** Set Address 3.
@param Address3 Address Line 3 for the location */
public void setAddress3 (String Address3)
{
if (Address3 != null && Address3.length() > 60)
{
log.warning("Length > 60 - truncated");
Address3 = Address3.substring(0,59);
}
set_ValueNoCheck ("Address3", Address3);
}
/** Get Address 3.
@return Address Line 3 for the location */
public String getAddress3() 
{
return (String)get_Value("Address3");
}
/** Column name Address3 */
public static final String COLUMNNAME_Address3 = "Address3";

/** BPContactGreeting AD_Reference_ID=356 */
public static final int BPCONTACTGREETING_AD_Reference_ID=356;
/** Set BP Contact Greeting.
@param BPContactGreeting Greeting for Business Partner Contact */
public void setBPContactGreeting (int BPContactGreeting)
{
set_ValueNoCheck ("BPContactGreeting", Integer.valueOf(BPContactGreeting));
}
/** Get BP Contact Greeting.
@return Greeting for Business Partner Contact */
public int getBPContactGreeting() 
{
Integer ii = (Integer)get_Value("BPContactGreeting");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name BPContactGreeting */
public static final String COLUMNNAME_BPContactGreeting = "BPContactGreeting";

/** BPartner_Parent_ID AD_Reference_ID=124 */
public static final int BPARTNER_PARENT_ID_AD_Reference_ID=124;
/** Set Partner Parent.
@param BPartner_Parent_ID Business Partner Parent */
public void setBPartner_Parent_ID (int BPartner_Parent_ID)
{
if (BPartner_Parent_ID <= 0) set_ValueNoCheck ("BPartner_Parent_ID", null);
 else 
set_ValueNoCheck ("BPartner_Parent_ID", Integer.valueOf(BPartner_Parent_ID));
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
/** Set Birthday.
@param Birthday Birthday or Anniversary day */
public void setBirthday (Timestamp Birthday)
{
set_ValueNoCheck ("Birthday", Birthday);
}
/** Get Birthday.
@return Birthday or Anniversary day */
public Timestamp getBirthday() 
{
return (Timestamp)get_Value("Birthday");
}
/** Column name Birthday */
public static final String COLUMNNAME_Birthday = "Birthday";
/** Set Business Partner Group.
@param C_BP_Group_ID Business Partner Group */
public void setC_BP_Group_ID (int C_BP_Group_ID)
{
if (C_BP_Group_ID < 1) throw new IllegalArgumentException ("C_BP_Group_ID is mandatory.");
set_ValueNoCheck ("C_BP_Group_ID", Integer.valueOf(C_BP_Group_ID));
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
/** Set Partner Location.
@param C_BPartner_Location_ID Identifies the (ship to) address for this Business Partner */
public void setC_BPartner_Location_ID (int C_BPartner_Location_ID)
{
if (C_BPartner_Location_ID < 1) throw new IllegalArgumentException ("C_BPartner_Location_ID is mandatory.");
set_ValueNoCheck ("C_BPartner_Location_ID", Integer.valueOf(C_BPartner_Location_ID));
}
/** Get Partner Location.
@return Identifies the (ship to) address for this Business Partner */
public int getC_BPartner_Location_ID() 
{
Integer ii = (Integer)get_Value("C_BPartner_Location_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_BPartner_Location_ID */
public static final String COLUMNNAME_C_BPartner_Location_ID = "C_BPartner_Location_ID";
/** Set Country.
@param C_Country_ID Country  */
public void setC_Country_ID (int C_Country_ID)
{
if (C_Country_ID < 1) throw new IllegalArgumentException ("C_Country_ID is mandatory.");
set_ValueNoCheck ("C_Country_ID", Integer.valueOf(C_Country_ID));
}
/** Get Country.
@return Country  */
public int getC_Country_ID() 
{
Integer ii = (Integer)get_Value("C_Country_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_Country_ID */
public static final String COLUMNNAME_C_Country_ID = "C_Country_ID";
/** Set Dunning.
@param C_Dunning_ID Dunning Rules for overdue invoices */
public void setC_Dunning_ID (int C_Dunning_ID)
{
if (C_Dunning_ID <= 0) set_ValueNoCheck ("C_Dunning_ID", null);
 else 
set_ValueNoCheck ("C_Dunning_ID", Integer.valueOf(C_Dunning_ID));
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
if (C_Greeting_ID <= 0) set_ValueNoCheck ("C_Greeting_ID", null);
 else 
set_ValueNoCheck ("C_Greeting_ID", Integer.valueOf(C_Greeting_ID));
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
if (C_InvoiceSchedule_ID <= 0) set_ValueNoCheck ("C_InvoiceSchedule_ID", null);
 else 
set_ValueNoCheck ("C_InvoiceSchedule_ID", Integer.valueOf(C_InvoiceSchedule_ID));
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
if (C_PaymentTerm_ID <= 0) set_ValueNoCheck ("C_PaymentTerm_ID", null);
 else 
set_ValueNoCheck ("C_PaymentTerm_ID", Integer.valueOf(C_PaymentTerm_ID));
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
/** Set Region.
@param C_Region_ID Identifies a geographical Region */
public void setC_Region_ID (int C_Region_ID)
{
if (C_Region_ID <= 0) set_ValueNoCheck ("C_Region_ID", null);
 else 
set_ValueNoCheck ("C_Region_ID", Integer.valueOf(C_Region_ID));
}
/** Get Region.
@return Identifies a geographical Region */
public int getC_Region_ID() 
{
Integer ii = (Integer)get_Value("C_Region_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_Region_ID */
public static final String COLUMNNAME_C_Region_ID = "C_Region_ID";
/** Set City.
@param City Identifies a City */
public void setCity (String City)
{
if (City != null && City.length() > 60)
{
log.warning("Length > 60 - truncated");
City = City.substring(0,59);
}
set_ValueNoCheck ("City", City);
}
/** Get City.
@return Identifies a City */
public String getCity() 
{
return (String)get_Value("City");
}
/** Column name City */
public static final String COLUMNNAME_City = "City";
/** Set Comments.
@param Comments Comments or additional information */
public void setComments (String Comments)
{
if (Comments != null && Comments.length() > 2000)
{
log.warning("Length > 2000 - truncated");
Comments = Comments.substring(0,1999);
}
set_ValueNoCheck ("Comments", Comments);
}
/** Get Comments.
@return Comments or additional information */
public String getComments() 
{
return (String)get_Value("Comments");
}
/** Column name Comments */
public static final String COLUMNNAME_Comments = "Comments";
/** Set Contact Description.
@param ContactDescription Description of Contact */
public void setContactDescription (String ContactDescription)
{
if (ContactDescription != null && ContactDescription.length() > 255)
{
log.warning("Length > 255 - truncated");
ContactDescription = ContactDescription.substring(0,254);
}
set_ValueNoCheck ("ContactDescription", ContactDescription);
}
/** Get Contact Description.
@return Description of Contact */
public String getContactDescription() 
{
return (String)get_Value("ContactDescription");
}
/** Column name ContactDescription */
public static final String COLUMNNAME_ContactDescription = "ContactDescription";
/** Set Contact Name.
@param ContactName Business Partner Contact Name */
public void setContactName (String ContactName)
{
if (ContactName == null) throw new IllegalArgumentException ("ContactName is mandatory.");
if (ContactName.length() > 60)
{
log.warning("Length > 60 - truncated");
ContactName = ContactName.substring(0,59);
}
set_ValueNoCheck ("ContactName", ContactName);
}
/** Get Contact Name.
@return Business Partner Contact Name */
public String getContactName() 
{
return (String)get_Value("ContactName");
}
/** Column name ContactName */
public static final String COLUMNNAME_ContactName = "ContactName";
/** Set Country.
@param CountryName Country Name */
public void setCountryName (String CountryName)
{
if (CountryName == null) throw new IllegalArgumentException ("CountryName is mandatory.");
if (CountryName.length() > 60)
{
log.warning("Length > 60 - truncated");
CountryName = CountryName.substring(0,59);
}
set_ValueNoCheck ("CountryName", CountryName);
}
/** Get Country.
@return Country Name */
public String getCountryName() 
{
return (String)get_Value("CountryName");
}
/** Column name CountryName */
public static final String COLUMNNAME_CountryName = "CountryName";
/** Set D-U-N-S.
@param DUNS Dun & Bradstreet Number */
public void setDUNS (String DUNS)
{
if (DUNS != null && DUNS.length() > 11)
{
log.warning("Length > 11 - truncated");
DUNS = DUNS.substring(0,10);
}
set_ValueNoCheck ("DUNS", DUNS);
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
set_ValueNoCheck ("DeliveryRule", DeliveryRule);
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
set_ValueNoCheck ("DeliveryViaRule", DeliveryViaRule);
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
set_ValueNoCheck ("Description", Description);
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
set_ValueNoCheck ("DocumentCopies", Integer.valueOf(DocumentCopies));
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
/** Set EMail Address.
@param EMail Electronic Mail Address */
public void setEMail (String EMail)
{
if (EMail != null && EMail.length() > 60)
{
log.warning("Length > 60 - truncated");
EMail = EMail.substring(0,59);
}
set_ValueNoCheck ("EMail", EMail);
}
/** Get EMail Address.
@return Electronic Mail Address */
public String getEMail() 
{
return (String)get_Value("EMail");
}
/** Column name EMail */
public static final String COLUMNNAME_EMail = "EMail";
/** Set EMail User ID.
@param EMailUser User Name (ID) in the Mail System */
public void setEMailUser (String EMailUser)
{
if (EMailUser != null && EMailUser.length() > 60)
{
log.warning("Length > 60 - truncated");
EMailUser = EMailUser.substring(0,59);
}
set_ValueNoCheck ("EMailUser", EMailUser);
}
/** Get EMail User ID.
@return User Name (ID) in the Mail System */
public String getEMailUser() 
{
return (String)get_Value("EMailUser");
}
/** Column name EMailUser */
public static final String COLUMNNAME_EMailUser = "EMailUser";
/** Set Verification Info.
@param EMailVerify Verification information of EMail Address */
public void setEMailVerify (String EMailVerify)
{
if (EMailVerify != null && EMailVerify.length() > 40)
{
log.warning("Length > 40 - truncated");
EMailVerify = EMailVerify.substring(0,39);
}
set_ValueNoCheck ("EMailVerify", EMailVerify);
}
/** Get Verification Info.
@return Verification information of EMail Address */
public String getEMailVerify() 
{
return (String)get_Value("EMailVerify");
}
/** Column name EMailVerify */
public static final String COLUMNNAME_EMailVerify = "EMailVerify";
/** Set EMail Verify.
@param EMailVerifyDate Date Email was verified */
public void setEMailVerifyDate (Timestamp EMailVerifyDate)
{
set_ValueNoCheck ("EMailVerifyDate", EMailVerifyDate);
}
/** Get EMail Verify.
@return Date Email was verified */
public Timestamp getEMailVerifyDate() 
{
return (Timestamp)get_Value("EMailVerifyDate");
}
/** Column name EMailVerifyDate */
public static final String COLUMNNAME_EMailVerifyDate = "EMailVerifyDate";
/** Set Fax.
@param Fax Facsimile number */
public void setFax (String Fax)
{
if (Fax != null && Fax.length() > 40)
{
log.warning("Length > 40 - truncated");
Fax = Fax.substring(0,39);
}
set_ValueNoCheck ("Fax", Fax);
}
/** Get Fax.
@return Facsimile number */
public String getFax() 
{
return (String)get_Value("Fax");
}
/** Column name Fax */
public static final String COLUMNNAME_Fax = "Fax";
/** Set First Sale.
@param FirstSale Date of First Sale */
public void setFirstSale (Timestamp FirstSale)
{
set_ValueNoCheck ("FirstSale", FirstSale);
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
set_ValueNoCheck ("FlatDiscount", FlatDiscount);
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
set_ValueNoCheck ("FreightCostRule", FreightCostRule);
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
set_ValueNoCheck ("InvoiceRule", InvoiceRule);
}
/** Get Invoice Rule.
@return Frequency and method of invoicing  */
public String getInvoiceRule() 
{
return (String)get_Value("InvoiceRule");
}
/** Column name InvoiceRule */
public static final String COLUMNNAME_InvoiceRule = "InvoiceRule";

/** Invoice_PrintFormat_ID AD_Reference_ID=259 */
public static final int INVOICE_PRINTFORMAT_ID_AD_Reference_ID=259;
/** Set Invoice Print Format.
@param Invoice_PrintFormat_ID Print Format for printing Invoices */
public void setInvoice_PrintFormat_ID (int Invoice_PrintFormat_ID)
{
if (Invoice_PrintFormat_ID <= 0) set_ValueNoCheck ("Invoice_PrintFormat_ID", null);
 else 
set_ValueNoCheck ("Invoice_PrintFormat_ID", Integer.valueOf(Invoice_PrintFormat_ID));
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
set_ValueNoCheck ("IsCustomer", Boolean.valueOf(IsCustomer));
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
set_ValueNoCheck ("IsDiscountPrinted", Boolean.valueOf(IsDiscountPrinted));
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
set_ValueNoCheck ("IsEmployee", Boolean.valueOf(IsEmployee));
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
set_ValueNoCheck ("IsOneTime", Boolean.valueOf(IsOneTime));
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
set_ValueNoCheck ("IsProspect", Boolean.valueOf(IsProspect));
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
set_ValueNoCheck ("IsSalesRep", Boolean.valueOf(IsSalesRep));
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
set_ValueNoCheck ("IsSummary", Boolean.valueOf(IsSummary));
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
set_ValueNoCheck ("IsTaxExempt", Boolean.valueOf(IsTaxExempt));
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
set_ValueNoCheck ("IsVendor", Boolean.valueOf(IsVendor));
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
/** Set LDAP User Name.
@param LDAPUser User Name used for authorization via LDAP (directory) services */
public void setLDAPUser (boolean LDAPUser)
{
set_ValueNoCheck ("LDAPUser", Boolean.valueOf(LDAPUser));
}
/** Get LDAP User Name.
@return User Name used for authorization via LDAP (directory) services */
public boolean isLDAPUser() 
{
Object oo = get_Value("LDAPUser");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name LDAPUser */
public static final String COLUMNNAME_LDAPUser = "LDAPUser";
/** Set Last Contact.
@param LastContact Date this individual was last contacted */
public void setLastContact (Timestamp LastContact)
{
set_ValueNoCheck ("LastContact", LastContact);
}
/** Get Last Contact.
@return Date this individual was last contacted */
public Timestamp getLastContact() 
{
return (Timestamp)get_Value("LastContact");
}
/** Column name LastContact */
public static final String COLUMNNAME_LastContact = "LastContact";
/** Set Last Result.
@param LastResult Result of last contact */
public void setLastResult (String LastResult)
{
if (LastResult != null && LastResult.length() > 255)
{
log.warning("Length > 255 - truncated");
LastResult = LastResult.substring(0,254);
}
set_ValueNoCheck ("LastResult", LastResult);
}
/** Get Last Result.
@return Result of last contact */
public String getLastResult() 
{
return (String)get_Value("LastResult");
}
/** Column name LastResult */
public static final String COLUMNNAME_LastResult = "LastResult";
/** Set Discount Schema.
@param M_DiscountSchema_ID Schema to calculate the trade discount percentage */
public void setM_DiscountSchema_ID (int M_DiscountSchema_ID)
{
if (M_DiscountSchema_ID <= 0) set_ValueNoCheck ("M_DiscountSchema_ID", null);
 else 
set_ValueNoCheck ("M_DiscountSchema_ID", Integer.valueOf(M_DiscountSchema_ID));
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
if (M_PriceList_ID <= 0) set_ValueNoCheck ("M_PriceList_ID", null);
 else 
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
/** Set NAICS/SIC.
@param NAICS Standard Industry Code or its successor NAIC - http://www.osha.gov/oshstats/sicser.html */
public void setNAICS (String NAICS)
{
if (NAICS != null && NAICS.length() > 6)
{
log.warning("Length > 6 - truncated");
NAICS = NAICS.substring(0,5);
}
set_ValueNoCheck ("NAICS", NAICS);
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
set_ValueNoCheck ("Name", Name);
}
/** Get Name.
@return Alphanumeric identifier of the entity */
public String getName() 
{
return (String)get_Value("Name");
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
set_ValueNoCheck ("Name2", Name2);
}
/** Get Name 2.
@return Additional Name */
public String getName2() 
{
return (String)get_Value("Name2");
}
/** Column name Name2 */
public static final String COLUMNNAME_Name2 = "Name2";

/** NotificationType AD_Reference_ID=344 */
public static final int NOTIFICATIONTYPE_AD_Reference_ID=344;
/** EMail+Notice = B */
public static final String NOTIFICATIONTYPE_EMailPlusNotice = "B";
/** EMail = E */
public static final String NOTIFICATIONTYPE_EMail = "E";
/** Notice = N */
public static final String NOTIFICATIONTYPE_Notice = "N";
/** None = X */
public static final String NOTIFICATIONTYPE_None = "X";
/** Set Notification Type.
@param NotificationType Type of Notifications */
public void setNotificationType (String NotificationType)
{
if (NotificationType == null) throw new IllegalArgumentException ("NotificationType is mandatory");
if (NotificationType.equals("B") || NotificationType.equals("E") || NotificationType.equals("N") || NotificationType.equals("X"));
 else throw new IllegalArgumentException ("NotificationType Invalid value - " + NotificationType + " - Reference_ID=344 - B - E - N - X");
if (NotificationType.length() > 1)
{
log.warning("Length > 1 - truncated");
NotificationType = NotificationType.substring(0,0);
}
set_ValueNoCheck ("NotificationType", NotificationType);
}
/** Get Notification Type.
@return Type of Notifications */
public String getNotificationType() 
{
return (String)get_Value("NotificationType");
}
/** Column name NotificationType */
public static final String COLUMNNAME_NotificationType = "NotificationType";
/** Set Employees.
@param NumberEmployees Number of employees */
public void setNumberEmployees (int NumberEmployees)
{
set_ValueNoCheck ("NumberEmployees", Integer.valueOf(NumberEmployees));
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
set_ValueNoCheck ("POReference", POReference);
}
/** Get Order Reference.
@return Transaction Reference Number (Sales Order, Purchase Order) of your Business Partner */
public String getPOReference() 
{
return (String)get_Value("POReference");
}
/** Column name POReference */
public static final String COLUMNNAME_POReference = "POReference";

/** PO_DiscountSchema_ID AD_Reference_ID=249 */
public static final int PO_DISCOUNTSCHEMA_ID_AD_Reference_ID=249;
/** Set PO Discount Schema.
@param PO_DiscountSchema_ID Schema to calculate the purchase trade discount percentage */
public void setPO_DiscountSchema_ID (int PO_DiscountSchema_ID)
{
if (PO_DiscountSchema_ID <= 0) set_ValueNoCheck ("PO_DiscountSchema_ID", null);
 else 
set_ValueNoCheck ("PO_DiscountSchema_ID", Integer.valueOf(PO_DiscountSchema_ID));
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
if (PO_PaymentTerm_ID <= 0) set_ValueNoCheck ("PO_PaymentTerm_ID", null);
 else 
set_ValueNoCheck ("PO_PaymentTerm_ID", Integer.valueOf(PO_PaymentTerm_ID));
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
if (PO_PriceList_ID <= 0) set_ValueNoCheck ("PO_PriceList_ID", null);
 else 
set_ValueNoCheck ("PO_PriceList_ID", Integer.valueOf(PO_PriceList_ID));
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
set_ValueNoCheck ("PaymentRule", PaymentRule);
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
set_ValueNoCheck ("PaymentRulePO", PaymentRulePO);
}
/** Get Payment Rule.
@return Purchase payment option */
public String getPaymentRulePO() 
{
return (String)get_Value("PaymentRulePO");
}
/** Column name PaymentRulePO */
public static final String COLUMNNAME_PaymentRulePO = "PaymentRulePO";
/** Set Phone.
@param Phone Identifies a telephone number */
public void setPhone (String Phone)
{
if (Phone != null && Phone.length() > 40)
{
log.warning("Length > 40 - truncated");
Phone = Phone.substring(0,39);
}
set_ValueNoCheck ("Phone", Phone);
}
/** Get Phone.
@return Identifies a telephone number */
public String getPhone() 
{
return (String)get_Value("Phone");
}
/** Column name Phone */
public static final String COLUMNNAME_Phone = "Phone";
/** Set 2nd Phone.
@param Phone2 Identifies an alternate telephone number. */
public void setPhone2 (String Phone2)
{
if (Phone2 != null && Phone2.length() > 40)
{
log.warning("Length > 40 - truncated");
Phone2 = Phone2.substring(0,39);
}
set_ValueNoCheck ("Phone2", Phone2);
}
/** Get 2nd Phone.
@return Identifies an alternate telephone number. */
public String getPhone2() 
{
return (String)get_Value("Phone2");
}
/** Column name Phone2 */
public static final String COLUMNNAME_Phone2 = "Phone2";
/** Set ZIP.
@param Postal Postal code */
public void setPostal (String Postal)
{
if (Postal != null && Postal.length() > 10)
{
log.warning("Length > 10 - truncated");
Postal = Postal.substring(0,9);
}
set_ValueNoCheck ("Postal", Postal);
}
/** Get ZIP.
@return Postal code */
public String getPostal() 
{
return (String)get_Value("Postal");
}
/** Column name Postal */
public static final String COLUMNNAME_Postal = "Postal";
/** Set Potential Life Time Value.
@param PotentialLifeTimeValue Total Revenue expected */
public void setPotentialLifeTimeValue (BigDecimal PotentialLifeTimeValue)
{
set_ValueNoCheck ("PotentialLifeTimeValue", PotentialLifeTimeValue);
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
set_ValueNoCheck ("Rating", Rating);
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
set_ValueNoCheck ("ReferenceNo", ReferenceNo);
}
/** Get Reference No.
@return Your customer or vendor number at the Business Partner's site */
public String getReferenceNo() 
{
return (String)get_Value("ReferenceNo");
}
/** Column name ReferenceNo */
public static final String COLUMNNAME_ReferenceNo = "ReferenceNo";
/** Set Region.
@param RegionName Name of the Region */
public void setRegionName (String RegionName)
{
if (RegionName != null && RegionName.length() > 60)
{
log.warning("Length > 60 - truncated");
RegionName = RegionName.substring(0,59);
}
set_ValueNoCheck ("RegionName", RegionName);
}
/** Get Region.
@return Name of the Region */
public String getRegionName() 
{
return (String)get_Value("RegionName");
}
/** Column name RegionName */
public static final String COLUMNNAME_RegionName = "RegionName";

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
set_ValueNoCheck ("SOCreditStatus", SOCreditStatus);
}
/** Get Credit Status.
@return Business Partner Credit Status */
public String getSOCreditStatus() 
{
return (String)get_Value("SOCreditStatus");
}
/** Column name SOCreditStatus */
public static final String COLUMNNAME_SOCreditStatus = "SOCreditStatus";
/** Set Credit Available.
@param SO_CreditAvailable Available Credit based on Credit Limit (not Total Open Balance) and Credit Used */
public void setSO_CreditAvailable (BigDecimal SO_CreditAvailable)
{
set_ValueNoCheck ("SO_CreditAvailable", SO_CreditAvailable);
}
/** Get Credit Available.
@return Available Credit based on Credit Limit (not Total Open Balance) and Credit Used */
public BigDecimal getSO_CreditAvailable() 
{
BigDecimal bd = (BigDecimal)get_Value("SO_CreditAvailable");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name SO_CreditAvailable */
public static final String COLUMNNAME_SO_CreditAvailable = "SO_CreditAvailable";
/** Set Credit Limit.
@param SO_CreditLimit Total outstanding invoice amounts allowed */
public void setSO_CreditLimit (BigDecimal SO_CreditLimit)
{
set_ValueNoCheck ("SO_CreditLimit", SO_CreditLimit);
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
set_ValueNoCheck ("SO_Description", SO_Description);
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
if (SalesRep_ID <= 0) set_ValueNoCheck ("SalesRep_ID", null);
 else 
set_ValueNoCheck ("SalesRep_ID", Integer.valueOf(SalesRep_ID));
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
public void setSalesVolume (BigDecimal SalesVolume)
{
set_ValueNoCheck ("SalesVolume", SalesVolume);
}
/** Get Sales Volume in 1.000.
@return Total Volume of Sales in Thousands of Currency */
public BigDecimal getSalesVolume() 
{
BigDecimal bd = (BigDecimal)get_Value("SalesVolume");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name SalesVolume */
public static final String COLUMNNAME_SalesVolume = "SalesVolume";
/** Set Send EMail.
@param SendEMail Enable sending Document EMail */
public void setSendEMail (boolean SendEMail)
{
set_ValueNoCheck ("SendEMail", Boolean.valueOf(SendEMail));
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
set_ValueNoCheck ("ShareOfCustomer", Integer.valueOf(ShareOfCustomer));
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
set_ValueNoCheck ("ShelfLifeMinPct", Integer.valueOf(ShelfLifeMinPct));
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

/** Supervisor_ID AD_Reference_ID=110 */
public static final int SUPERVISOR_ID_AD_Reference_ID=110;
/** Set Supervisor.
@param Supervisor_ID Supervisor for this user/organization - used for escalation and approval */
public void setSupervisor_ID (int Supervisor_ID)
{
if (Supervisor_ID <= 0) set_ValueNoCheck ("Supervisor_ID", null);
 else 
set_ValueNoCheck ("Supervisor_ID", Integer.valueOf(Supervisor_ID));
}
/** Get Supervisor.
@return Supervisor for this user/organization - used for escalation and approval */
public int getSupervisor_ID() 
{
Integer ii = (Integer)get_Value("Supervisor_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name Supervisor_ID */
public static final String COLUMNNAME_Supervisor_ID = "Supervisor_ID";
/** Set Tax ID.
@param TaxID Tax Identification */
public void setTaxID (String TaxID)
{
if (TaxID != null && TaxID.length() > 20)
{
log.warning("Length > 20 - truncated");
TaxID = TaxID.substring(0,19);
}
set_ValueNoCheck ("TaxID", TaxID);
}
/** Get Tax ID.
@return Tax Identification */
public String getTaxID() 
{
return (String)get_Value("TaxID");
}
/** Column name TaxID */
public static final String COLUMNNAME_TaxID = "TaxID";
/** Set Title.
@param Title Name this entity is referred to as */
public void setTitle (String Title)
{
if (Title != null && Title.length() > 40)
{
log.warning("Length > 40 - truncated");
Title = Title.substring(0,39);
}
set_ValueNoCheck ("Title", Title);
}
/** Get Title.
@return Name this entity is referred to as */
public String getTitle() 
{
return (String)get_Value("Title");
}
/** Column name Title */
public static final String COLUMNNAME_Title = "Title";
/** Set Open Balance.
@param TotalOpenBalance Total Open Balance Amount in primary Accounting Currency */
public void setTotalOpenBalance (BigDecimal TotalOpenBalance)
{
set_ValueNoCheck ("TotalOpenBalance", TotalOpenBalance);
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
set_ValueNoCheck ("URL", URL);
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
set_ValueNoCheck ("Value", Value);
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
