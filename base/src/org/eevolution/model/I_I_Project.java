/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.										*
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/
package org.eevolution.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for I_Project
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1
 */
public interface I_I_Project 
{

    /** TableName=I_Project */
    public static final String Table_Name = "I_Project";

    /** AD_Table_ID=54312 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_Color_ID */
    public static final String COLUMNNAME_AD_Color_ID = "AD_Color_ID";

	/** Set System Color.
	  * Color for backgrounds or indicators
	  */
	public void setAD_Color_ID (int AD_Color_ID);

	/** Get System Color.
	  * Color for backgrounds or indicators
	  */
	public int getAD_Color_ID();

	public org.compiere.model.I_AD_Color getAD_Color() throws RuntimeException;

    /** Column name AD_OrgTrx_ID */
    public static final String COLUMNNAME_AD_OrgTrx_ID = "AD_OrgTrx_ID";

	/** Set Trx Organization.
	  * Performing or initiating organization
	  */
	public void setAD_OrgTrx_ID (int AD_OrgTrx_ID);

	/** Get Trx Organization.
	  * Performing or initiating organization
	  */
	public int getAD_OrgTrx_ID();

	public org.compiere.model.I_AD_Org getAD_OrgTrx() throws RuntimeException;

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name AD_User_ID */
    public static final String COLUMNNAME_AD_User_ID = "AD_User_ID";

	/** Set User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID);

	/** Get User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID();

	public org.compiere.model.I_AD_User getAD_User() throws RuntimeException;

    /** Column name ActivityValue */
    public static final String COLUMNNAME_ActivityValue = "ActivityValue";

	/** Set Activity Value	  */
	public void setActivityValue (String ActivityValue);

	/** Get Activity Value	  */
	public String getActivityValue();

    /** Column name Address1 */
    public static final String COLUMNNAME_Address1 = "Address1";

	/** Set Address 1.
	  * Address line 1 for this location
	  */
	public void setAddress1 (String Address1);

	/** Get Address 1.
	  * Address line 1 for this location
	  */
	public String getAddress1();

    /** Column name Address2 */
    public static final String COLUMNNAME_Address2 = "Address2";

	/** Set Address 2.
	  * Address line 2 for this location
	  */
	public void setAddress2 (String Address2);

	/** Get Address 2.
	  * Address line 2 for this location
	  */
	public String getAddress2();

    /** Column name Address3 */
    public static final String COLUMNNAME_Address3 = "Address3";

	/** Set Address 3.
	  * Address Line 3 for the location
	  */
	public void setAddress3 (String Address3);

	/** Get Address 3.
	  * Address Line 3 for the location
	  */
	public String getAddress3();

    /** Column name Address4 */
    public static final String COLUMNNAME_Address4 = "Address4";

	/** Set Address 4.
	  * Address Line 4 for the location
	  */
	public void setAddress4 (String Address4);

	/** Get Address 4.
	  * Address Line 4 for the location
	  */
	public String getAddress4();

    /** Column name BPName */
    public static final String COLUMNNAME_BPName = "BPName";

	/** Set BP Name	  */
	public void setBPName (String BPName);

	/** Get BP Name	  */
	public String getBPName();

    /** Column name BPValue */
    public static final String COLUMNNAME_BPValue = "BPValue";

	/** Set BP Search Key.
	  * Business Partner Key Value
	  */
	public void setBPValue (String BPValue);

	/** Get BP Search Key.
	  * Business Partner Key Value
	  */
	public String getBPValue();

    /** Column name BPartnerValue */
    public static final String COLUMNNAME_BPartnerValue = "BPartnerValue";

	/** Set Business Partner Key.
	  * Key of the Business Partner
	  */
	public void setBPartnerValue (String BPartnerValue);

	/** Get Business Partner Key.
	  * Key of the Business Partner
	  */
	public String getBPartnerValue();

    /** Column name C_Activity_ID */
    public static final String COLUMNNAME_C_Activity_ID = "C_Activity_ID";

	/** Set Activity.
	  * Business Activity
	  */
	public void setC_Activity_ID (int C_Activity_ID);

	/** Get Activity.
	  * Business Activity
	  */
	public int getC_Activity_ID();

	public org.compiere.model.I_C_Activity getC_Activity() throws RuntimeException;

    /** Column name C_BPartnerSR_ID */
    public static final String COLUMNNAME_C_BPartnerSR_ID = "C_BPartnerSR_ID";

	/** Set BPartner (Agent).
	  * Business Partner (Agent or Sales Rep)
	  */
	public void setC_BPartnerSR_ID (int C_BPartnerSR_ID);

	/** Get BPartner (Agent).
	  * Business Partner (Agent or Sales Rep)
	  */
	public int getC_BPartnerSR_ID();

	public org.compiere.model.I_C_BPartner getC_BPartnerSR() throws RuntimeException;

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Business Partner .
	  * Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Business Partner .
	  * Identifies a Business Partner
	  */
	public int getC_BPartner_ID();

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException;

    /** Column name C_BPartner_Location_ID */
    public static final String COLUMNNAME_C_BPartner_Location_ID = "C_BPartner_Location_ID";

	/** Set Partner Location.
	  * Identifies the (ship to) address for this Business Partner
	  */
	public void setC_BPartner_Location_ID (int C_BPartner_Location_ID);

	/** Get Partner Location.
	  * Identifies the (ship to) address for this Business Partner
	  */
	public int getC_BPartner_Location_ID();

	public org.compiere.model.I_C_BPartner_Location getC_BPartner_Location() throws RuntimeException;

    /** Column name C_Campaign_ID */
    public static final String COLUMNNAME_C_Campaign_ID = "C_Campaign_ID";

	/** Set Campaign.
	  * Marketing Campaign
	  */
	public void setC_Campaign_ID (int C_Campaign_ID);

	/** Get Campaign.
	  * Marketing Campaign
	  */
	public int getC_Campaign_ID();

	public org.compiere.model.I_C_Campaign getC_Campaign() throws RuntimeException;

    /** Column name C_Currency_ID */
    public static final String COLUMNNAME_C_Currency_ID = "C_Currency_ID";

	/** Set Currency.
	  * The Currency for this record
	  */
	public void setC_Currency_ID (int C_Currency_ID);

	/** Get Currency.
	  * The Currency for this record
	  */
	public int getC_Currency_ID();

	public org.compiere.model.I_C_Currency getC_Currency() throws RuntimeException;

    /** Column name C_PaymentTerm_ID */
    public static final String COLUMNNAME_C_PaymentTerm_ID = "C_PaymentTerm_ID";

	/** Set Payment Term.
	  * The terms of Payment (timing, discount)
	  */
	public void setC_PaymentTerm_ID (int C_PaymentTerm_ID);

	/** Get Payment Term.
	  * The terms of Payment (timing, discount)
	  */
	public int getC_PaymentTerm_ID();

	public org.compiere.model.I_C_PaymentTerm getC_PaymentTerm() throws RuntimeException;

    /** Column name C_ProjectCategory_ID */
    public static final String COLUMNNAME_C_ProjectCategory_ID = "C_ProjectCategory_ID";

	/** Set Project Category.
	  * Project Category
	  */
	public void setC_ProjectCategory_ID (int C_ProjectCategory_ID);

	/** Get Project Category.
	  * Project Category
	  */
	public int getC_ProjectCategory_ID();

	public org.eevolution.model.I_C_ProjectCategory getC_ProjectCategory() throws RuntimeException;

    /** Column name C_ProjectClass_ID */
    public static final String COLUMNNAME_C_ProjectClass_ID = "C_ProjectClass_ID";

	/** Set Project Class.
	  * Project Class
	  */
	public void setC_ProjectClass_ID (int C_ProjectClass_ID);

	/** Get Project Class.
	  * Project Class
	  */
	public int getC_ProjectClass_ID();

	public org.eevolution.model.I_C_ProjectClass getC_ProjectClass() throws RuntimeException;

    /** Column name C_ProjectGroup_ID */
    public static final String COLUMNNAME_C_ProjectGroup_ID = "C_ProjectGroup_ID";

	/** Set Project Group.
	  * Project Group
	  */
	public void setC_ProjectGroup_ID (int C_ProjectGroup_ID);

	/** Get Project Group.
	  * Project Group
	  */
	public int getC_ProjectGroup_ID();

	public org.eevolution.model.I_C_ProjectGroup getC_ProjectGroup() throws RuntimeException;

    /** Column name C_ProjectStatus_ID */
    public static final String COLUMNNAME_C_ProjectStatus_ID = "C_ProjectStatus_ID";

	/** Set Project Status.
	  * Status for Project, Phase or Task
	  */
	public void setC_ProjectStatus_ID (int C_ProjectStatus_ID);

	/** Get Project Status.
	  * Status for Project, Phase or Task
	  */
	public int getC_ProjectStatus_ID();

	public org.eevolution.model.I_C_ProjectStatus getC_ProjectStatus() throws RuntimeException;

    /** Column name C_ProjectType_ID */
    public static final String COLUMNNAME_C_ProjectType_ID = "C_ProjectType_ID";

	/** Set Project Type.
	  * Type of the project
	  */
	public void setC_ProjectType_ID (int C_ProjectType_ID);

	/** Get Project Type.
	  * Type of the project
	  */
	public int getC_ProjectType_ID();

	public org.compiere.model.I_C_ProjectType getC_ProjectType() throws RuntimeException;

    /** Column name C_Project_ID */
    public static final String COLUMNNAME_C_Project_ID = "C_Project_ID";

	/** Set Project.
	  * Financial Project
	  */
	public void setC_Project_ID (int C_Project_ID);

	/** Get Project.
	  * Financial Project
	  */
	public int getC_Project_ID();

	public org.compiere.model.I_C_Project getC_Project() throws RuntimeException;

    /** Column name C_SalesRegion_ID */
    public static final String COLUMNNAME_C_SalesRegion_ID = "C_SalesRegion_ID";

	/** Set Sales Region.
	  * Sales coverage region
	  */
	public void setC_SalesRegion_ID (int C_SalesRegion_ID);

	/** Get Sales Region.
	  * Sales coverage region
	  */
	public int getC_SalesRegion_ID();

	public org.compiere.model.I_C_SalesRegion getC_SalesRegion() throws RuntimeException;

    /** Column name CampaignValue */
    public static final String COLUMNNAME_CampaignValue = "CampaignValue";

	/** Set Campaign Value	  */
	public void setCampaignValue (String CampaignValue);

	/** Get Campaign Value	  */
	public String getCampaignValue();

    /** Column name City */
    public static final String COLUMNNAME_City = "City";

	/** Set City.
	  * Identifies a City
	  */
	public void setCity (String City);

	/** Get City.
	  * Identifies a City
	  */
	public String getCity();

    /** Column name Comments */
    public static final String COLUMNNAME_Comments = "Comments";

	/** Set Comments.
	  * Comments or additional information
	  */
	public void setComments (String Comments);

	/** Get Comments.
	  * Comments or additional information
	  */
	public String getComments();

    /** Column name CommittedAmt */
    public static final String COLUMNNAME_CommittedAmt = "CommittedAmt";

	/** Set Committed Amount.
	  * The (legal) commitment amount
	  */
	public void setCommittedAmt (BigDecimal CommittedAmt);

	/** Get Committed Amount.
	  * The (legal) commitment amount
	  */
	public BigDecimal getCommittedAmt();

    /** Column name CommittedQty */
    public static final String COLUMNNAME_CommittedQty = "CommittedQty";

	/** Set Committed Quantity.
	  * The (legal) commitment Quantity
	  */
	public void setCommittedQty (BigDecimal CommittedQty);

	/** Get Committed Quantity.
	  * The (legal) commitment Quantity
	  */
	public BigDecimal getCommittedQty();

    /** Column name ContactDescription */
    public static final String COLUMNNAME_ContactDescription = "ContactDescription";

	/** Set Contact Description.
	  * Description of Contact
	  */
	public void setContactDescription (String ContactDescription);

	/** Get Contact Description.
	  * Description of Contact
	  */
	public String getContactDescription();

    /** Column name ContactName */
    public static final String COLUMNNAME_ContactName = "ContactName";

	/** Set Contact Name.
	  * Business Partner Contact Name
	  */
	public void setContactName (String ContactName);

	/** Get Contact Name.
	  * Business Partner Contact Name
	  */
	public String getContactName();

    /** Column name CountryCode */
    public static final String COLUMNNAME_CountryCode = "CountryCode";

	/** Set ISO Country Code.
	  * Upper-case two-letter alphanumeric ISO Country code according to ISO 3166-1 - http://www.chemie.fu-berlin.de/diverse/doc/ISO_3166.html
	  */
	public void setCountryCode (String CountryCode);

	/** Get ISO Country Code.
	  * Upper-case two-letter alphanumeric ISO Country code according to ISO 3166-1 - http://www.chemie.fu-berlin.de/diverse/doc/ISO_3166.html
	  */
	public String getCountryCode();

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created.
	  * Date this record was created
	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

    /** Column name DateContract */
    public static final String COLUMNNAME_DateContract = "DateContract";

	/** Set Contract Date.
	  * The (planned) effective date of this document.
	  */
	public void setDateContract (Timestamp DateContract);

	/** Get Contract Date.
	  * The (planned) effective date of this document.
	  */
	public Timestamp getDateContract();

    /** Column name DateDeadline */
    public static final String COLUMNNAME_DateDeadline = "DateDeadline";

	/** Set Deadline.
	  * Deadline
	  */
	public void setDateDeadline (Timestamp DateDeadline);

	/** Get Deadline.
	  * Deadline
	  */
	public Timestamp getDateDeadline();

    /** Column name DateFinish */
    public static final String COLUMNNAME_DateFinish = "DateFinish";

	/** Set Finish Date.
	  * Finish or (planned) completion date
	  */
	public void setDateFinish (Timestamp DateFinish);

	/** Get Finish Date.
	  * Finish or (planned) completion date
	  */
	public Timestamp getDateFinish();

    /** Column name DateFinishSchedule */
    public static final String COLUMNNAME_DateFinishSchedule = "DateFinishSchedule";

	/** Set Finish Schedule.
	  * Scheduled Finish date for this Order
	  */
	public void setDateFinishSchedule (Timestamp DateFinishSchedule);

	/** Get Finish Schedule.
	  * Scheduled Finish date for this Order
	  */
	public Timestamp getDateFinishSchedule();

    /** Column name DateStart */
    public static final String COLUMNNAME_DateStart = "DateStart";

	/** Set Date Start.
	  * Date Start for this Order
	  */
	public void setDateStart (Timestamp DateStart);

	/** Get Date Start.
	  * Date Start for this Order
	  */
	public Timestamp getDateStart();

    /** Column name DateStartSchedule */
    public static final String COLUMNNAME_DateStartSchedule = "DateStartSchedule";

	/** Set Start Schedule.
	  * Scheduled start date for this Order
	  */
	public void setDateStartSchedule (Timestamp DateStartSchedule);

	/** Get Start Schedule.
	  * Scheduled start date for this Order
	  */
	public Timestamp getDateStartSchedule();

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name DurationUnit */
    public static final String COLUMNNAME_DurationUnit = "DurationUnit";

	/** Set Duration Unit.
	  * Unit of Duration
	  */
	public void setDurationUnit (String DurationUnit);

	/** Get Duration Unit.
	  * Unit of Duration
	  */
	public String getDurationUnit();

    /** Column name EMail */
    public static final String COLUMNNAME_EMail = "EMail";

	/** Set EMail Address.
	  * Electronic Mail Address
	  */
	public void setEMail (String EMail);

	/** Get EMail Address.
	  * Electronic Mail Address
	  */
	public String getEMail();

    /** Column name Fax */
    public static final String COLUMNNAME_Fax = "Fax";

	/** Set Fax.
	  * Facsimile number
	  */
	public void setFax (String Fax);

	/** Get Fax.
	  * Facsimile number
	  */
	public String getFax();

    /** Column name ISO_Code */
    public static final String COLUMNNAME_ISO_Code = "ISO_Code";

	/** Set ISO Currency Code.
	  * Three letter ISO 4217 Code of the Currency
	  */
	public void setISO_Code (String ISO_Code);

	/** Get ISO Currency Code.
	  * Three letter ISO 4217 Code of the Currency
	  */
	public String getISO_Code();

    /** Column name I_ErrorMsg */
    public static final String COLUMNNAME_I_ErrorMsg = "I_ErrorMsg";

	/** Set Import Error Message.
	  * Messages generated from import process
	  */
	public void setI_ErrorMsg (String I_ErrorMsg);

	/** Get Import Error Message.
	  * Messages generated from import process
	  */
	public String getI_ErrorMsg();

    /** Column name I_IsImported */
    public static final String COLUMNNAME_I_IsImported = "I_IsImported";

	/** Set Imported.
	  * Has this import been processed
	  */
	public void setI_IsImported (boolean I_IsImported);

	/** Get Imported.
	  * Has this import been processed
	  */
	public boolean isI_IsImported();

    /** Column name I_Project_ID */
    public static final String COLUMNNAME_I_Project_ID = "I_Project_ID";

	/** Set Import Project ID	  */
	public void setI_Project_ID (int I_Project_ID);

	/** Get Import Project ID	  */
	public int getI_Project_ID();

    /** Column name InvoicedAmt */
    public static final String COLUMNNAME_InvoicedAmt = "InvoicedAmt";

	/** Set Invoiced Amount.
	  * The amount invoiced
	  */
	public void setInvoicedAmt (BigDecimal InvoicedAmt);

	/** Get Invoiced Amount.
	  * The amount invoiced
	  */
	public BigDecimal getInvoicedAmt();

    /** Column name InvoicedQty */
    public static final String COLUMNNAME_InvoicedQty = "InvoicedQty";

	/** Set Quantity Invoiced .
	  * The quantity invoiced
	  */
	public void setInvoicedQty (BigDecimal InvoicedQty);

	/** Get Quantity Invoiced .
	  * The quantity invoiced
	  */
	public BigDecimal getInvoicedQty();

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active.
	  * The record is active in the system
	  */
	public void setIsActive (boolean IsActive);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean isActive();

    /** Column name IsCommitCeiling */
    public static final String COLUMNNAME_IsCommitCeiling = "IsCommitCeiling";

	/** Set Commitment is Ceiling.
	  * The commitment amount/quantity is the chargeable ceiling 
	  */
	public void setIsCommitCeiling (boolean IsCommitCeiling);

	/** Get Commitment is Ceiling.
	  * The commitment amount/quantity is the chargeable ceiling 
	  */
	public boolean isCommitCeiling();

    /** Column name IsCommitment */
    public static final String COLUMNNAME_IsCommitment = "IsCommitment";

	/** Set Commitment.
	  * Is this document a (legal) commitment?
	  */
	public void setIsCommitment (boolean IsCommitment);

	/** Get Commitment.
	  * Is this document a (legal) commitment?
	  */
	public boolean isCommitment();

    /** Column name IsIndefinite */
    public static final String COLUMNNAME_IsIndefinite = "IsIndefinite";

	/** Set Indefinite.
	  * Indefinite
	  */
	public void setIsIndefinite (boolean IsIndefinite);

	/** Get Indefinite.
	  * Indefinite
	  */
	public boolean isIndefinite();

    /** Column name IsSummary */
    public static final String COLUMNNAME_IsSummary = "IsSummary";

	/** Set Summary Level.
	  * This is a summary entity
	  */
	public void setIsSummary (boolean IsSummary);

	/** Get Summary Level.
	  * This is a summary entity
	  */
	public boolean isSummary();

    /** Column name M_PriceList_Version_ID */
    public static final String COLUMNNAME_M_PriceList_Version_ID = "M_PriceList_Version_ID";

	/** Set Price List Version.
	  * Identifies a unique instance of a Price List
	  */
	public void setM_PriceList_Version_ID (int M_PriceList_Version_ID);

	/** Get Price List Version.
	  * Identifies a unique instance of a Price List
	  */
	public int getM_PriceList_Version_ID();

	public org.compiere.model.I_M_PriceList_Version getM_PriceList_Version() throws RuntimeException;

    /** Column name M_Warehouse_ID */
    public static final String COLUMNNAME_M_Warehouse_ID = "M_Warehouse_ID";

	/** Set Warehouse.
	  * Storage Warehouse and Service Point
	  */
	public void setM_Warehouse_ID (int M_Warehouse_ID);

	/** Get Warehouse.
	  * Storage Warehouse and Service Point
	  */
	public int getM_Warehouse_ID();

	public org.compiere.model.I_M_Warehouse getM_Warehouse() throws RuntimeException;

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

    /** Column name Note */
    public static final String COLUMNNAME_Note = "Note";

	/** Set Note.
	  * Optional additional user defined information
	  */
	public void setNote (String Note);

	/** Get Note.
	  * Optional additional user defined information
	  */
	public String getNote();

    /** Column name OrgTrxValue */
    public static final String COLUMNNAME_OrgTrxValue = "OrgTrxValue";

	/** Set Trx Org Key.
	  * Key of the Transaction Organization
	  */
	public void setOrgTrxValue (String OrgTrxValue);

	/** Get Trx Org Key.
	  * Key of the Transaction Organization
	  */
	public String getOrgTrxValue();

    /** Column name OrgValue */
    public static final String COLUMNNAME_OrgValue = "OrgValue";

	/** Set Org Key.
	  * Key of the Organization
	  */
	public void setOrgValue (String OrgValue);

	/** Get Org Key.
	  * Key of the Organization
	  */
	public String getOrgValue();

    /** Column name POReference */
    public static final String COLUMNNAME_POReference = "POReference";

	/** Set Order Reference.
	  * Transaction Reference Number (Sales Order, Purchase Order) of your Business Partner
	  */
	public void setPOReference (String POReference);

	/** Get Order Reference.
	  * Transaction Reference Number (Sales Order, Purchase Order) of your Business Partner
	  */
	public String getPOReference();

    /** Column name Phone */
    public static final String COLUMNNAME_Phone = "Phone";

	/** Set Phone.
	  * Identifies a telephone number
	  */
	public void setPhone (String Phone);

	/** Get Phone.
	  * Identifies a telephone number
	  */
	public String getPhone();

    /** Column name Phone2 */
    public static final String COLUMNNAME_Phone2 = "Phone2";

	/** Set 2nd Phone.
	  * Identifies an alternate telephone number.
	  */
	public void setPhone2 (String Phone2);

	/** Get 2nd Phone.
	  * Identifies an alternate telephone number.
	  */
	public String getPhone2();

    /** Column name PlannedAmt */
    public static final String COLUMNNAME_PlannedAmt = "PlannedAmt";

	/** Set Planned Amount.
	  * Planned amount for this project
	  */
	public void setPlannedAmt (BigDecimal PlannedAmt);

	/** Get Planned Amount.
	  * Planned amount for this project
	  */
	public BigDecimal getPlannedAmt();

    /** Column name PlannedMarginAmt */
    public static final String COLUMNNAME_PlannedMarginAmt = "PlannedMarginAmt";

	/** Set Planned Margin.
	  * Project's planned margin amount
	  */
	public void setPlannedMarginAmt (BigDecimal PlannedMarginAmt);

	/** Get Planned Margin.
	  * Project's planned margin amount
	  */
	public BigDecimal getPlannedMarginAmt();

    /** Column name PlannedQty */
    public static final String COLUMNNAME_PlannedQty = "PlannedQty";

	/** Set Planned Quantity.
	  * Planned quantity for this project
	  */
	public void setPlannedQty (BigDecimal PlannedQty);

	/** Get Planned Quantity.
	  * Planned quantity for this project
	  */
	public BigDecimal getPlannedQty();

    /** Column name Postal */
    public static final String COLUMNNAME_Postal = "Postal";

	/** Set ZIP.
	  * Postal code
	  */
	public void setPostal (String Postal);

	/** Get ZIP.
	  * Postal code
	  */
	public String getPostal();

    /** Column name PriorityRule */
    public static final String COLUMNNAME_PriorityRule = "PriorityRule";

	/** Set Priority.
	  * Priority of a document
	  */
	public void setPriorityRule (String PriorityRule);

	/** Get Priority.
	  * Priority of a document
	  */
	public String getPriorityRule();

    /** Column name Processed */
    public static final String COLUMNNAME_Processed = "Processed";

	/** Set Processed.
	  * The document has been processed
	  */
	public void setProcessed (boolean Processed);

	/** Get Processed.
	  * The document has been processed
	  */
	public boolean isProcessed();

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

    /** Column name ProjInvoiceRule */
    public static final String COLUMNNAME_ProjInvoiceRule = "ProjInvoiceRule";

	/** Set Invoice Rule.
	  * Invoice Rule for the project
	  */
	public void setProjInvoiceRule (String ProjInvoiceRule);

	/** Get Invoice Rule.
	  * Invoice Rule for the project
	  */
	public String getProjInvoiceRule();

    /** Column name ProjectBalanceAmt */
    public static final String COLUMNNAME_ProjectBalanceAmt = "ProjectBalanceAmt";

	/** Set Project Balance.
	  * Total Project Balance
	  */
	public void setProjectBalanceAmt (BigDecimal ProjectBalanceAmt);

	/** Get Project Balance.
	  * Total Project Balance
	  */
	public BigDecimal getProjectBalanceAmt();

    /** Column name ProjectCategoryValue */
    public static final String COLUMNNAME_ProjectCategoryValue = "ProjectCategoryValue";

	/** Set Project Category Value.
	  * Project Category Value
	  */
	public void setProjectCategoryValue (String ProjectCategoryValue);

	/** Get Project Category Value.
	  * Project Category Value
	  */
	public String getProjectCategoryValue();

    /** Column name ProjectClassValue */
    public static final String COLUMNNAME_ProjectClassValue = "ProjectClassValue";

	/** Set Project Class Value.
	  * Project Class Value
	  */
	public void setProjectClassValue (String ProjectClassValue);

	/** Get Project Class Value.
	  * Project Class Value
	  */
	public String getProjectClassValue();

    /** Column name ProjectGroupValue */
    public static final String COLUMNNAME_ProjectGroupValue = "ProjectGroupValue";

	/** Set Project Group Value.
	  * Project Group Value
	  */
	public void setProjectGroupValue (String ProjectGroupValue);

	/** Get Project Group Value.
	  * Project Group Value
	  */
	public String getProjectGroupValue();

    /** Column name ProjectLineLevel */
    public static final String COLUMNNAME_ProjectLineLevel = "ProjectLineLevel";

	/** Set Line Level.
	  * Project Line Level
	  */
	public void setProjectLineLevel (String ProjectLineLevel);

	/** Get Line Level.
	  * Project Line Level
	  */
	public String getProjectLineLevel();

    /** Column name ProjectManager_ID */
    public static final String COLUMNNAME_ProjectManager_ID = "ProjectManager_ID";

	/** Set Project Manager.
	  * Project Manager
	  */
	public void setProjectManager_ID (int ProjectManager_ID);

	/** Get Project Manager.
	  * Project Manager
	  */
	public int getProjectManager_ID();

	public org.compiere.model.I_AD_User getProjectManager() throws RuntimeException;

    /** Column name ProjectStatusValue */
    public static final String COLUMNNAME_ProjectStatusValue = "ProjectStatusValue";

	/** Set Project Status Value.
	  * Project Status Value
	  */
	public void setProjectStatusValue (String ProjectStatusValue);

	/** Get Project Status Value.
	  * Project Status Value
	  */
	public String getProjectStatusValue();

    /** Column name ProjectTypeValue */
    public static final String COLUMNNAME_ProjectTypeValue = "ProjectTypeValue";

	/** Set Project Type Value.
	  * Project Type Value
	  */
	public void setProjectTypeValue (String ProjectTypeValue);

	/** Get Project Type Value.
	  * Project Type Value
	  */
	public String getProjectTypeValue();

    /** Column name RegionName */
    public static final String COLUMNNAME_RegionName = "RegionName";

	/** Set Region.
	  * Name of the Region
	  */
	public void setRegionName (String RegionName);

	/** Get Region.
	  * Name of the Region
	  */
	public String getRegionName();

    /** Column name SalesRegionValue */
    public static final String COLUMNNAME_SalesRegionValue = "SalesRegionValue";

	/** Set Sales Region Value	  */
	public void setSalesRegionValue (String SalesRegionValue);

	/** Get Sales Region Value	  */
	public String getSalesRegionValue();

    /** Column name SalesRep_ID */
    public static final String COLUMNNAME_SalesRep_ID = "SalesRep_ID";

	/** Set Sales Representative.
	  * Sales Representative or Company Agent
	  */
	public void setSalesRep_ID (int SalesRep_ID);

	/** Get Sales Representative.
	  * Sales Representative or Company Agent
	  */
	public int getSalesRep_ID();

	public org.compiere.model.I_AD_User getSalesRep() throws RuntimeException;

    /** Column name Title */
    public static final String COLUMNNAME_Title = "Title";

	/** Set Title.
	  * Name this entity is referred to as
	  */
	public void setTitle (String Title);

	/** Get Title.
	  * Name this entity is referred to as
	  */
	public String getTitle();

    /** Column name UUID */
    public static final String COLUMNNAME_UUID = "UUID";

	/** Set Immutable Universally Unique Identifier.
	  * Immutable Universally Unique Identifier
	  */
	public void setUUID (String UUID);

	/** Get Immutable Universally Unique Identifier.
	  * Immutable Universally Unique Identifier
	  */
	public String getUUID();

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated.
	  * Date this record was updated
	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By.
	  * User who updated this records
	  */
	public int getUpdatedBy();

    /** Column name User1_ID */
    public static final String COLUMNNAME_User1_ID = "User1_ID";

	/** Set User List 1.
	  * User defined list element #1
	  */
	public void setUser1_ID (int User1_ID);

	/** Get User List 1.
	  * User defined list element #1
	  */
	public int getUser1_ID();

	public org.compiere.model.I_C_ElementValue getUser1() throws RuntimeException;

    /** Column name User2_ID */
    public static final String COLUMNNAME_User2_ID = "User2_ID";

	/** Set User List 2.
	  * User defined list element #2
	  */
	public void setUser2_ID (int User2_ID);

	/** Get User List 2.
	  * User defined list element #2
	  */
	public int getUser2_ID();

	public org.compiere.model.I_C_ElementValue getUser2() throws RuntimeException;

    /** Column name User3_ID */
    public static final String COLUMNNAME_User3_ID = "User3_ID";

	/** Set User List 3.
	  * User defined list element #3
	  */
	public void setUser3_ID (int User3_ID);

	/** Get User List 3.
	  * User defined list element #3
	  */
	public int getUser3_ID();

	public org.compiere.model.I_C_ElementValue getUser3() throws RuntimeException;

    /** Column name User4_ID */
    public static final String COLUMNNAME_User4_ID = "User4_ID";

	/** Set User List 4.
	  * User defined list element #4
	  */
	public void setUser4_ID (int User4_ID);

	/** Get User List 4.
	  * User defined list element #4
	  */
	public int getUser4_ID();

	public org.compiere.model.I_C_ElementValue getUser4() throws RuntimeException;

    /** Column name UserName */
    public static final String COLUMNNAME_UserName = "UserName";

	/** Set Registered EMail.
	  * Email of the responsible for the System
	  */
	public void setUserName (String UserName);

	/** Get Registered EMail.
	  * Email of the responsible for the System
	  */
	public String getUserName();

    /** Column name UserValue1 */
    public static final String COLUMNNAME_UserValue1 = "UserValue1";

	/** Set User List Value 1.
	  * User value defined list element #1
	  */
	public void setUserValue1 (String UserValue1);

	/** Get User List Value 1.
	  * User value defined list element #1
	  */
	public String getUserValue1();

    /** Column name UserValue2 */
    public static final String COLUMNNAME_UserValue2 = "UserValue2";

	/** Set User List Value 2.
	  * User value defined list element #2
	  */
	public void setUserValue2 (String UserValue2);

	/** Get User List Value 2.
	  * User value defined list element #2
	  */
	public String getUserValue2();

    /** Column name UserValue3 */
    public static final String COLUMNNAME_UserValue3 = "UserValue3";

	/** Set User List Value 3.
	  * User value defined list element #3
	  */
	public void setUserValue3 (String UserValue3);

	/** Get User List Value 3.
	  * User value defined list element #3
	  */
	public String getUserValue3();

    /** Column name UserValue4 */
    public static final String COLUMNNAME_UserValue4 = "UserValue4";

	/** Set User List Value 4.
	  * User value defined list element #3
	  */
	public void setUserValue4 (String UserValue4);

	/** Get User List Value 4.
	  * User value defined list element #3
	  */
	public String getUserValue4();

    /** Column name Value */
    public static final String COLUMNNAME_Value = "Value";

	/** Set Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value);

	/** Get Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public String getValue();

    /** Column name WarehouseValue */
    public static final String COLUMNNAME_WarehouseValue = "WarehouseValue";

	/** Set Warehouse Key.
	  * Key of the Warehouse
	  */
	public void setWarehouseValue (String WarehouseValue);

	/** Get Warehouse Key.
	  * Key of the Warehouse
	  */
	public String getWarehouseValue();
}
