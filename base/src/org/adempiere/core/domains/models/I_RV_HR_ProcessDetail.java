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
package org.adempiere.core.domains.models;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for RV_HR_ProcessDetail
 *  @author Adempiere (generated) 
 *  @version Release 3.9.0
 */
public interface I_RV_HR_ProcessDetail 
{

    /** TableName=RV_HR_ProcessDetail */
    public static final String Table_Name = "RV_HR_ProcessDetail";

    /** AD_Table_ID=54345 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AccountSign */
    public static final String COLUMNNAME_AccountSign = "AccountSign";

	/** Set Account Sign.
	  * Indicates the Natural Sign of the Account as a Debit or Credit
	  */
	public void setAccountSign (boolean AccountSign);

	/** Get Account Sign.
	  * Indicates the Natural Sign of the Account as a Debit or Credit
	  */
	public boolean isAccountSign();

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

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

    /** Column name Amount */
    public static final String COLUMNNAME_Amount = "Amount";

	/** Set Amount.
	  * Amount in a defined currency
	  */
	public void setAmount (BigDecimal Amount);

	/** Get Amount.
	  * Amount in a defined currency
	  */
	public BigDecimal getAmount();

    /** Column name BPName */
    public static final String COLUMNNAME_BPName = "BPName";

	/** Set BP Name	  */
	public void setBPName (String BPName);

	/** Get BP Name	  */
	public String getBPName();

    /** Column name BPTaxID */
    public static final String COLUMNNAME_BPTaxID = "BPTaxID";

	/** Set Partner Tax ID.
	  * Tax ID of the Business Partner
	  */
	public void setBPTaxID (String BPTaxID);

	/** Get Partner Tax ID.
	  * Tax ID of the Business Partner
	  */
	public String getBPTaxID();

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

    /** Column name CategoryName */
    public static final String COLUMNNAME_CategoryName = "CategoryName";

	/** Set Category Name.
	  * Name of the Category
	  */
	public void setCategoryName (String CategoryName);

	/** Get Category Name.
	  * Name of the Category
	  */
	public String getCategoryName();

    /** Column name CategoryValue */
    public static final String COLUMNNAME_CategoryValue = "CategoryValue";

	/** Set Category Value	  */
	public void setCategoryValue (String CategoryValue);

	/** Get Category Value	  */
	public String getCategoryValue();

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

	public org.adempiere.core.domains.models.I_C_BPartner getC_BPartner() throws RuntimeException;

    /** Column name ColumnType */
    public static final String COLUMNNAME_ColumnType = "ColumnType";

	/** Set Column Type	  */
	public void setColumnType (boolean ColumnType);

	/** Get Column Type	  */
	public boolean isColumnType();

    /** Column name ConceptAmt */
    public static final String COLUMNNAME_ConceptAmt = "ConceptAmt";

	/** Set Concept Amount	  */
	public void setConceptAmt (BigDecimal ConceptAmt);

	/** Get Concept Amount	  */
	public BigDecimal getConceptAmt();

    /** Column name ConceptValue */
    public static final String COLUMNNAME_ConceptValue = "ConceptValue";

	/** Set Concept Value.
	  * Value of the Concept
	  */
	public void setConceptValue (String ConceptValue);

	/** Get Concept Value.
	  * Value of the Concept
	  */
	public String getConceptValue();

    /** Column name ConvertedAmt */
    public static final String COLUMNNAME_ConvertedAmt = "ConvertedAmt";

	/** Set Converted Amount.
	  * Converted Amount
	  */
	public void setConvertedAmt (BigDecimal ConvertedAmt);

	/** Get Converted Amount.
	  * Converted Amount
	  */
	public BigDecimal getConvertedAmt();

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

    /** Column name DateAcct */
    public static final String COLUMNNAME_DateAcct = "DateAcct";

	/** Set Account Date.
	  * Accounting Date
	  */
	public void setDateAcct (Timestamp DateAcct);

	/** Get Account Date.
	  * Accounting Date
	  */
	public Timestamp getDateAcct();

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

    /** Column name DocStatus */
    public static final String COLUMNNAME_DocStatus = "DocStatus";

	/** Set Document Status.
	  * The current status of the document
	  */
	public void setDocStatus (String DocStatus);

	/** Get Document Status.
	  * The current status of the document
	  */
	public String getDocStatus();

    /** Column name DocumentNo */
    public static final String COLUMNNAME_DocumentNo = "DocumentNo";

	/** Set Document No.
	  * Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo);

	/** Get Document No.
	  * Document sequence number of the document
	  */
	public String getDocumentNo();

    /** Column name DocumentNote */
    public static final String COLUMNNAME_DocumentNote = "DocumentNote";

	/** Set Document Note.
	  * Additional information for a Document
	  */
	public void setDocumentNote (String DocumentNote);

	/** Get Document Note.
	  * Additional information for a Document
	  */
	public String getDocumentNote();

    /** Column name EmployeeStatus */
    public static final String COLUMNNAME_EmployeeStatus = "EmployeeStatus";

	/** Set Employee Status	  */
	public void setEmployeeStatus (String EmployeeStatus);

	/** Get Employee Status	  */
	public String getEmployeeStatus();

    /** Column name EmployeeStatusName */
    public static final String COLUMNNAME_EmployeeStatusName = "EmployeeStatusName";

	/** Set Employee Status Name	  */
	public void setEmployeeStatusName (String EmployeeStatusName);

	/** Get Employee Status Name	  */
	public String getEmployeeStatusName();

    /** Column name EndDate */
    public static final String COLUMNNAME_EndDate = "EndDate";

	/** Set End Date.
	  * Last effective date (inclusive)
	  */
	public void setEndDate (Timestamp EndDate);

	/** Get End Date.
	  * Last effective date (inclusive)
	  */
	public Timestamp getEndDate();

    /** Column name HeaderPrintName */
    public static final String COLUMNNAME_HeaderPrintName = "HeaderPrintName";

	/** Set Header Print Name	  */
	public void setHeaderPrintName (String HeaderPrintName);

	/** Get Header Print Name	  */
	public String getHeaderPrintName();

    /** Column name HR_Concept_Category_ID */
    public static final String COLUMNNAME_HR_Concept_Category_ID = "HR_Concept_Category_ID";

	/** Set Global Payroll Concept Category.
	  * Global Payroll Concept Category allows to grouping of Global Concept to reports and queries
	  */
	public void setHR_Concept_Category_ID (int HR_Concept_Category_ID);

	/** Get Global Payroll Concept Category.
	  * Global Payroll Concept Category allows to grouping of Global Concept to reports and queries
	  */
	public int getHR_Concept_Category_ID();

    /** Column name HR_Concept_ID */
    public static final String COLUMNNAME_HR_Concept_ID = "HR_Concept_ID";

	/** Set Global Payroll Concept.
	  * The Payroll Concept allows to define all the perception and deductions elements needed to define a payroll.
	  */
	public void setHR_Concept_ID (int HR_Concept_ID);

	/** Get Global Payroll Concept.
	  * The Payroll Concept allows to define all the perception and deductions elements needed to define a payroll.
	  */
	public int getHR_Concept_ID();
	
    /** Column name HR_Concept_Type_ID */
    public static final String COLUMNNAME_HR_Concept_Type_ID = "HR_Concept_Type_ID";

	/** Set Global Payroll Concept Type.
	  * Allows define types for concepts
	  */
	public void setHR_Concept_Type_ID (int HR_Concept_Type_ID);

	/** Get Global Payroll Concept Type.
	  * Allows define types for concepts
	  */
	public int getHR_Concept_Type_ID();


    /** Column name HR_Contract_ID */
    public static final String COLUMNNAME_HR_Contract_ID = "HR_Contract_ID";

	/** Set Payroll Contract	  */
	public void setHR_Contract_ID (int HR_Contract_ID);

	/** Get Payroll Contract	  */
	public int getHR_Contract_ID();

    /** Column name HR_Department_ID */
    public static final String COLUMNNAME_HR_Department_ID = "HR_Department_ID";

	/** Set Payroll Department	  */
	public void setHR_Department_ID (int HR_Department_ID);

	/** Get Payroll Department	  */
	public int getHR_Department_ID();


    /** Column name HR_Employee_ID */
    public static final String COLUMNNAME_HR_Employee_ID = "HR_Employee_ID";

	/** Set Payroll Employee	  */
	public void setHR_Employee_ID (int HR_Employee_ID);

	/** Get Payroll Employee	  */
	public int getHR_Employee_ID();

    /** Column name HR_Job_ID */
    public static final String COLUMNNAME_HR_Job_ID = "HR_Job_ID";

	/** Set Payroll Job	  */
	public void setHR_Job_ID (int HR_Job_ID);

	/** Get Payroll Job	  */
	public int getHR_Job_ID();

    /** Column name HR_Movement_ID */
    public static final String COLUMNNAME_HR_Movement_ID = "HR_Movement_ID";

	/** Set Payroll Movement	  */
	public void setHR_Movement_ID (int HR_Movement_ID);

	/** Get Payroll Movement	  */
	public int getHR_Movement_ID();

    /** Column name HR_Payroll_ID */
    public static final String COLUMNNAME_HR_Payroll_ID = "HR_Payroll_ID";

	/** Set Payroll	  */
	public void setHR_Payroll_ID (int HR_Payroll_ID);

	/** Get Payroll	  */
	public int getHR_Payroll_ID();

    /** Column name HR_Period_ID */
    public static final String COLUMNNAME_HR_Period_ID = "HR_Period_ID";

	/** Set Payroll Period	  */
	public void setHR_Period_ID (int HR_Period_ID);

	/** Get Payroll Period	  */
	public int getHR_Period_ID();

    /** Column name HR_Process_ID */
    public static final String COLUMNNAME_HR_Process_ID = "HR_Process_ID";

	/** Set Payroll Process	  */
	public void setHR_Process_ID (int HR_Process_ID);

	/** Get Payroll Process	  */
	public int getHR_Process_ID();

    /** Column name HR_ProcessReport_ID */
    public static final String COLUMNNAME_HR_ProcessReport_ID = "HR_ProcessReport_ID";

	/** Set Payroll Process Report	  */
	public void setHR_ProcessReport_ID (int HR_ProcessReport_ID);

	/** Get Payroll Process Report	  */
	public int getHR_ProcessReport_ID();

    /** Column name HR_ProcessReportLine_ID */
    public static final String COLUMNNAME_HR_ProcessReportLine_ID = "HR_ProcessReportLine_ID";

	/** Set Payroll Process Report Line	  */
	public void setHR_ProcessReportLine_ID (int HR_ProcessReportLine_ID);

	/** Get Payroll Process Report Line	  */
	public int getHR_ProcessReportLine_ID();

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

    /** Column name IsAveraged */
    public static final String COLUMNNAME_IsAveraged = "IsAveraged";

	/** Set Calculate Mean (µ).
	  * Calculate Average of numeric content or length
	  */
	public void setIsAveraged (boolean IsAveraged);

	/** Get Calculate Mean (µ).
	  * Calculate Average of numeric content or length
	  */
	public boolean isAveraged();

    /** Column name IsSummarized */
    public static final String COLUMNNAME_IsSummarized = "IsSummarized";

	/** Set Calculate Sum (S).
	  * Calculate the Sum of numeric content or length
	  */
	public void setIsSummarized (boolean IsSummarized);

	/** Get Calculate Sum (S).
	  * Calculate the Sum of numeric content or length
	  */
	public boolean isSummarized();

    /** Column name LineDescription */
    public static final String COLUMNNAME_LineDescription = "LineDescription";

	/** Set Line Description.
	  * Description of the Line
	  */
	public void setLineDescription (String LineDescription);

	/** Get Line Description.
	  * Description of the Line
	  */
	public String getLineDescription();

    /** Column name Multiplier */
    public static final String COLUMNNAME_Multiplier = "Multiplier";

	/** Set Multiplier.
	  * Type Multiplier (Credit = -1)
	  */
	public void setMultiplier (int Multiplier);

	/** Get Multiplier.
	  * Type Multiplier (Credit = -1)
	  */
	public int getMultiplier();

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

    /** Column name Name2 */
    public static final String COLUMNNAME_Name2 = "Name2";

	/** Set Name 2.
	  * Additional Name
	  */
	public void setName2 (String Name2);

	/** Get Name 2.
	  * Additional Name
	  */
	public String getName2();

    /** Column name PayrollName */
    public static final String COLUMNNAME_PayrollName = "PayrollName";

	/** Set Payroll Name	  */
	public void setPayrollName (String PayrollName);

	/** Get Payroll Name	  */
	public String getPayrollName();

    /** Column name PayrollValue */
    public static final String COLUMNNAME_PayrollValue = "PayrollValue";

	/** Set Payroll Value.
	  * Define the a Search key of a payroll
	  */
	public void setPayrollValue (String PayrollValue);

	/** Get Payroll Value.
	  * Define the a Search key of a payroll
	  */
	public String getPayrollValue();

    /** Column name PrintName */
    public static final String COLUMNNAME_PrintName = "PrintName";

	/** Set Print Text.
	  * The label text to be printed on a document or correspondence.
	  */
	public void setPrintName (String PrintName);

	/** Get Print Text.
	  * The label text to be printed on a document or correspondence.
	  */
	public String getPrintName();

    /** Column name ProcessReport */
    public static final String COLUMNNAME_ProcessReport = "ProcessReport";

	/** Set Process Report	  */
	public void setProcessReport (String ProcessReport);

	/** Get Process Report	  */
	public String getProcessReport();

    /** Column name Qty */
    public static final String COLUMNNAME_Qty = "Qty";

	/** Set Quantity.
	  * Quantity
	  */
	public void setQty (BigDecimal Qty);

	/** Get Quantity.
	  * Quantity
	  */
	public BigDecimal getQty();

    /** Column name ReceiptFooterMsg */
    public static final String COLUMNNAME_ReceiptFooterMsg = "ReceiptFooterMsg";

	/** Set Receipt Footer Msg.
	  * This message will be displayed at the bottom of a receipt when doing a sales or purchase
	  */
	public void setReceiptFooterMsg (String ReceiptFooterMsg);

	/** Get Receipt Footer Msg.
	  * This message will be displayed at the bottom of a receipt when doing a sales or purchase
	  */
	public String getReceiptFooterMsg();

    /** Column name RV_HR_ProcessDetail_ID */
    public static final String COLUMNNAME_RV_HR_ProcessDetail_ID = "RV_HR_ProcessDetail_ID";

	/** Set Human Process Detail	  */
	public void setRV_HR_ProcessDetail_ID (int RV_HR_ProcessDetail_ID);

	/** Get Human Process Detail	  */
	public int getRV_HR_ProcessDetail_ID();

    /** Column name SeqNo */
    public static final String COLUMNNAME_SeqNo = "SeqNo";

	/** Set Sequence.
	  * Method of ordering records;
 lowest number comes first
	  */
	public void setSeqNo (int SeqNo);

	/** Get Sequence.
	  * Method of ordering records;
 lowest number comes first
	  */
	public int getSeqNo();

    /** Column name ServiceDate */
    public static final String COLUMNNAME_ServiceDate = "ServiceDate";

	/** Set Service date.
	  * Date service was provided
	  */
	public void setServiceDate (Timestamp ServiceDate);

	/** Get Service date.
	  * Date service was provided
	  */
	public Timestamp getServiceDate();

    /** Column name SourceDescription */
    public static final String COLUMNNAME_SourceDescription = "SourceDescription";

	/** Set Source Description	  */
	public void setSourceDescription (String SourceDescription);

	/** Get Source Description	  */
	public String getSourceDescription();

    /** Column name StartDate */
    public static final String COLUMNNAME_StartDate = "StartDate";

	/** Set Start Date.
	  * First effective day (inclusive)
	  */
	public void setStartDate (Timestamp StartDate);

	/** Get Start Date.
	  * First effective day (inclusive)
	  */
	public Timestamp getStartDate();

    /** Column name TextMsg */
    public static final String COLUMNNAME_TextMsg = "TextMsg";

	/** Set Text Message.
	  * Text Message
	  */
	public void setTextMsg (String TextMsg);

	/** Get Text Message.
	  * Text Message
	  */
	public String getTextMsg();

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

    /** Column name ValidFrom */
    public static final String COLUMNNAME_ValidFrom = "ValidFrom";

	/** Set Valid from.
	  * Valid from including this date (first day)
	  */
	public void setValidFrom (Timestamp ValidFrom);

	/** Get Valid from.
	  * Valid from including this date (first day)
	  */
	public Timestamp getValidFrom();

    /** Column name ValidTo */
    public static final String COLUMNNAME_ValidTo = "ValidTo";

	/** Set Valid to.
	  * Valid to including this date (last day)
	  */
	public void setValidTo (Timestamp ValidTo);

	/** Get Valid to.
	  * Valid to including this date (last day)
	  */
	public Timestamp getValidTo();
}
