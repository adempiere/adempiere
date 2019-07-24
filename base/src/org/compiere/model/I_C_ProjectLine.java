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
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for C_ProjectLine
 *  @author Adempiere (generated) 
 *  @version Release 3.9.2
 */
public interface I_C_ProjectLine 
{

    /** TableName=C_ProjectLine */
    public static final String Table_Name = "C_ProjectLine";

    /** AD_Table_ID=434 */
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

    /** Column name AD_Workflow_ID */
    public static final String COLUMNNAME_AD_Workflow_ID = "AD_Workflow_ID";

	/** Set Workflow.
	  * Workflow or combination of tasks
	  */
	public void setAD_Workflow_ID (int AD_Workflow_ID);

	/** Get Workflow.
	  * Workflow or combination of tasks
	  */
	public int getAD_Workflow_ID();

	public org.compiere.model.I_AD_Workflow getAD_Workflow() throws RuntimeException;

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

    /** Column name C_Order_ID */
    public static final String COLUMNNAME_C_Order_ID = "C_Order_ID";

	/** Set Order.
	  * Order
	  */
	public void setC_Order_ID (int C_Order_ID);

	/** Get Order.
	  * Order
	  */
	public int getC_Order_ID();

	public org.compiere.model.I_C_Order getC_Order() throws RuntimeException;

    /** Column name C_OrderPO_ID */
    public static final String COLUMNNAME_C_OrderPO_ID = "C_OrderPO_ID";

	/** Set Purchase Order.
	  * Purchase Order
	  */
	public void setC_OrderPO_ID (int C_OrderPO_ID);

	/** Get Purchase Order.
	  * Purchase Order
	  */
	public int getC_OrderPO_ID();

	public org.compiere.model.I_C_Order getC_OrderPO() throws RuntimeException;

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

    /** Column name C_ProjectIssue_ID */
    public static final String COLUMNNAME_C_ProjectIssue_ID = "C_ProjectIssue_ID";

	/** Set Project Issue.
	  * Project Issues (Material, Labor)
	  */
	public void setC_ProjectIssue_ID (int C_ProjectIssue_ID);

	/** Get Project Issue.
	  * Project Issues (Material, Labor)
	  */
	public int getC_ProjectIssue_ID();

	public org.compiere.model.I_C_ProjectIssue getC_ProjectIssue() throws RuntimeException;

    /** Column name C_ProjectLine_ID */
    public static final String COLUMNNAME_C_ProjectLine_ID = "C_ProjectLine_ID";

	/** Set Project Line.
	  * Task or step in a project
	  */
	public void setC_ProjectLine_ID (int C_ProjectLine_ID);

	/** Get Project Line.
	  * Task or step in a project
	  */
	public int getC_ProjectLine_ID();

    /** Column name C_ProjectLineType_ID */
    public static final String COLUMNNAME_C_ProjectLineType_ID = "C_ProjectLineType_ID";

	/** Set Project Line Type	  */
	public void setC_ProjectLineType_ID (int C_ProjectLineType_ID);

	/** Get Project Line Type	  */
	public int getC_ProjectLineType_ID();

	public I_C_ProjectLineType getC_ProjectLineType() throws RuntimeException;

    /** Column name C_ProjectPhase_ID */
    public static final String COLUMNNAME_C_ProjectPhase_ID = "C_ProjectPhase_ID";

	/** Set Project Phase.
	  * Phase of a Project
	  */
	public void setC_ProjectPhase_ID (int C_ProjectPhase_ID);

	/** Get Project Phase.
	  * Phase of a Project
	  */
	public int getC_ProjectPhase_ID();

	public org.compiere.model.I_C_ProjectPhase getC_ProjectPhase() throws RuntimeException;

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

    /** Column name C_ProjectTaskCategory_ID */
    public static final String COLUMNNAME_C_ProjectTaskCategory_ID = "C_ProjectTaskCategory_ID";

	/** Set Project Task Category.
	  * Set Category for project task
	  */
	public void setC_ProjectTaskCategory_ID (int C_ProjectTaskCategory_ID);

	/** Get Project Task Category.
	  * Set Category for project task
	  */
	public int getC_ProjectTaskCategory_ID();

	public org.eevolution.model.I_C_ProjectTaskCategory getC_ProjectTaskCategory() throws RuntimeException;

    /** Column name C_ProjectTask_ID */
    public static final String COLUMNNAME_C_ProjectTask_ID = "C_ProjectTask_ID";

	/** Set Project Task.
	  * Actual Project Task in a Phase
	  */
	public void setC_ProjectTask_ID (int C_ProjectTask_ID);

	/** Get Project Task.
	  * Actual Project Task in a Phase
	  */
	public int getC_ProjectTask_ID();

	public org.compiere.model.I_C_ProjectTask getC_ProjectTask() throws RuntimeException;

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

    /** Column name C_StandardProjectLine_ID */
    public static final String COLUMNNAME_C_StandardProjectLine_ID = "C_StandardProjectLine_ID";

	/** Set Standard Project Line ID	  */
	public void setC_StandardProjectLine_ID (int C_StandardProjectLine_ID);

	/** Get Standard Project Line ID	  */
	public int getC_StandardProjectLine_ID();

	public I_C_StandardProjectLine getC_StandardProjectLine() throws RuntimeException;

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

    /** Column name DateLastRun */
    public static final String COLUMNNAME_DateLastRun = "DateLastRun";

	/** Set Date last run.
	  * Date the process was last run.
	  */
	public void setDateLastRun (Timestamp DateLastRun);

	/** Get Date last run.
	  * Date the process was last run.
	  */
	public Timestamp getDateLastRun();

    /** Column name DateNextRun */
    public static final String COLUMNNAME_DateNextRun = "DateNextRun";

	/** Set Date next run.
	  * Date the process will run next
	  */
	public void setDateNextRun (Timestamp DateNextRun);

	/** Get Date next run.
	  * Date the process will run next
	  */
	public Timestamp getDateNextRun();

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

    /** Column name DoPricing */
    public static final String COLUMNNAME_DoPricing = "DoPricing";

	/** Set Pricing	  */
	public void setDoPricing (String DoPricing);

	/** Get Pricing	  */
	public String getDoPricing();

    /** Column name DurationEstimated */
    public static final String COLUMNNAME_DurationEstimated = "DurationEstimated";

	/** Set Estimated Duration.
	  * Estimated Duration
	  */
	public void setDurationEstimated (BigDecimal DurationEstimated);

	/** Get Estimated Duration.
	  * Estimated Duration
	  */
	public BigDecimal getDurationEstimated();

    /** Column name DurationReal */
    public static final String COLUMNNAME_DurationReal = "DurationReal";

	/** Set Duration Real	  */
	public void setDurationReal (int DurationReal);

	/** Get Duration Real	  */
	public int getDurationReal();

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

    /** Column name Frequency */
    public static final String COLUMNNAME_Frequency = "Frequency";

	/** Set Frequency.
	  * Frequency of events
	  */
	public void setFrequency (int Frequency);

	/** Get Frequency.
	  * Frequency of events
	  */
	public int getFrequency();

    /** Column name FrequencyType */
    public static final String COLUMNNAME_FrequencyType = "FrequencyType";

	/** Set Frequency Type.
	  * Frequency of event
	  */
	public void setFrequencyType (String FrequencyType);

	/** Get Frequency Type.
	  * Frequency of event
	  */
	public String getFrequencyType();

    /** Column name Help */
    public static final String COLUMNNAME_Help = "Help";

	/** Set Comment/Help.
	  * Comment or Hint
	  */
	public void setHelp (String Help);

	/** Get Comment/Help.
	  * Comment or Hint
	  */
	public String getHelp();

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

    /** Column name IsBOM */
    public static final String COLUMNNAME_IsBOM = "IsBOM";

	/** Set Bill of Materials.
	  * Bill of Materials
	  */
	public void setIsBOM (boolean IsBOM);

	/** Get Bill of Materials.
	  * Bill of Materials
	  */
	public boolean isBOM();

    /** Column name IsComplete */
    public static final String COLUMNNAME_IsComplete = "IsComplete";

	/** Set Complete.
	  * It is complete
	  */
	public void setIsComplete (boolean IsComplete);

	/** Get Complete.
	  * It is complete
	  */
	public boolean isComplete();

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

    /** Column name IsMilestone */
    public static final String COLUMNNAME_IsMilestone = "IsMilestone";

	/** Set Is Milestone	  */
	public void setIsMilestone (boolean IsMilestone);

	/** Get Is Milestone	  */
	public boolean isMilestone();

    /** Column name IsPrinted */
    public static final String COLUMNNAME_IsPrinted = "IsPrinted";

	/** Set Printed.
	  * Indicates if this document / line is printed
	  */
	public void setIsPrinted (boolean IsPrinted);

	/** Get Printed.
	  * Indicates if this document / line is printed
	  */
	public boolean isPrinted();

    /** Column name IsPurchased */
    public static final String COLUMNNAME_IsPurchased = "IsPurchased";

	/** Set Purchased.
	  * Organization purchases this product
	  */
	public void setIsPurchased (boolean IsPurchased);

	/** Get Purchased.
	  * Organization purchases this product
	  */
	public boolean isPurchased();

    /** Column name IsRecurrent */
    public static final String COLUMNNAME_IsRecurrent = "IsRecurrent";

	/** Set Is Recurrent.
	  * The flag Is Recurrent, indicates if a project task is recurring
	  */
	public void setIsRecurrent (boolean IsRecurrent);

	/** Get Is Recurrent.
	  * The flag Is Recurrent, indicates if a project task is recurring
	  */
	public boolean isRecurrent();

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

    /** Column name Line */
    public static final String COLUMNNAME_Line = "Line";

	/** Set Line No.
	  * Unique line for this document
	  */
	public void setLine (int Line);

	/** Get Line No.
	  * Unique line for this document
	  */
	public int getLine();

    /** Column name M_Product_Category_ID */
    public static final String COLUMNNAME_M_Product_Category_ID = "M_Product_Category_ID";

	/** Set Product Category.
	  * Category of a Product
	  */
	public void setM_Product_Category_ID (int M_Product_Category_ID);

	/** Get Product Category.
	  * Category of a Product
	  */
	public int getM_Product_Category_ID();

	public org.compiere.model.I_M_Product_Category getM_Product_Category() throws RuntimeException;

    /** Column name M_Product_ID */
    public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";

	/** Set Product.
	  * Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID);

	/** Get Product.
	  * Product, Service, Item
	  */
	public int getM_Product_ID();

	public org.compiere.model.I_M_Product getM_Product() throws RuntimeException;

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

    /** Column name Parent_ID */
    public static final String COLUMNNAME_Parent_ID = "Parent_ID";

	/** Set Parent.
	  * Parent of Entity
	  */
	public void setParent_ID (int Parent_ID);

	/** Get Parent.
	  * Parent of Entity
	  */
	public int getParent_ID();

	public org.compiere.model.I_C_ProjectLine getParent() throws RuntimeException;

    /** Column name PercentageCompleted */
    public static final String COLUMNNAME_PercentageCompleted = "PercentageCompleted";

	/** Set Percentage completed.
	  * Percentage completed
	  */
	public void setPercentageCompleted (BigDecimal PercentageCompleted);

	/** Get Percentage completed.
	  * Percentage completed
	  */
	public BigDecimal getPercentageCompleted();

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

    /** Column name PlannedPrice */
    public static final String COLUMNNAME_PlannedPrice = "PlannedPrice";

	/** Set Planned Price.
	  * Planned price for this project line
	  */
	public void setPlannedPrice (BigDecimal PlannedPrice);

	/** Get Planned Price.
	  * Planned price for this project line
	  */
	public BigDecimal getPlannedPrice();

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

    /** Column name PP_Order_ID */
    public static final String COLUMNNAME_PP_Order_ID = "PP_Order_ID";

	/** Set Manufacturing Order.
	  * Manufacturing Order
	  */
	public void setPP_Order_ID (int PP_Order_ID);

	/** Get Manufacturing Order.
	  * Manufacturing Order
	  */
	public int getPP_Order_ID();

	public org.eevolution.model.I_PP_Order getPP_Order() throws RuntimeException;

    /** Column name PP_Product_BOM_ID */
    public static final String COLUMNNAME_PP_Product_BOM_ID = "PP_Product_BOM_ID";

	/** Set BOM & Formula.
	  * BOM & Formula
	  */
	public void setPP_Product_BOM_ID (int PP_Product_BOM_ID);

	/** Get BOM & Formula.
	  * BOM & Formula
	  */
	public int getPP_Product_BOM_ID();

	public org.eevolution.model.I_PP_Product_BOM getPP_Product_BOM() throws RuntimeException;

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

    /** Column name Responsible_ID */
    public static final String COLUMNNAME_Responsible_ID = "Responsible_ID";

	/** Set Responsible.
	  * Responsible
	  */
	public void setResponsible_ID (int Responsible_ID);

	/** Get Responsible.
	  * Responsible
	  */
	public int getResponsible_ID();

	public org.compiere.model.I_AD_User getResponsible() throws RuntimeException;

    /** Column name RunsMax */
    public static final String COLUMNNAME_RunsMax = "RunsMax";

	/** Set Maximum Runs.
	  * Number of recurring runs
	  */
	public void setRunsMax (int RunsMax);

	/** Get Maximum Runs.
	  * Number of recurring runs
	  */
	public int getRunsMax();

    /** Column name RunsRemaining */
    public static final String COLUMNNAME_RunsRemaining = "RunsRemaining";

	/** Set Remaining Runs.
	  * Number of recurring runs remaining
	  */
	public void setRunsRemaining (int RunsRemaining);

	/** Get Remaining Runs.
	  * Number of recurring runs remaining
	  */
	public int getRunsRemaining();

    /** Column name S_ResourceAssignment_ID */
    public static final String COLUMNNAME_S_ResourceAssignment_ID = "S_ResourceAssignment_ID";

	/** Set Resource Assignment.
	  * Resource Assignment
	  */
	public void setS_ResourceAssignment_ID (int S_ResourceAssignment_ID);

	/** Get Resource Assignment.
	  * Resource Assignment
	  */
	public int getS_ResourceAssignment_ID();

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

    /** Column name Vendor_ID */
    public static final String COLUMNNAME_Vendor_ID = "Vendor_ID";

	/** Set Vendor.
	  * The Vendor of the product/service
	  */
	public void setVendor_ID (int Vendor_ID);

	/** Get Vendor.
	  * The Vendor of the product/service
	  */
	public int getVendor_ID();

	public org.compiere.model.I_C_BPartner getVendor() throws RuntimeException;
}
