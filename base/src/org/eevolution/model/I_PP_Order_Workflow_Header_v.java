/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.eevolution.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.adempiere.model.*;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for PP_Order_Workflow_Header_v
 *  @author Adempiere (generated) 
 *  @version Release 3.5.3a
 */
public interface I_PP_Order_Workflow_Header_v 
{

    /** TableName=PP_Order_Workflow_Header_v */
    public static final String Table_Name = "PP_Order_Workflow_Header_v";

    /** AD_Table_ID=53199 */
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

    /** Column name AD_Language */
    public static final String COLUMNNAME_AD_Language = "AD_Language";

	/** Set Language.
	  * Language for this entity
	  */
	public void setAD_Language (String AD_Language);

	/** Get Language.
	  * Language for this entity
	  */
	public String getAD_Language();

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

	public I_AD_Workflow getAD_Workflow();

    /** Column name Assay */
    public static final String COLUMNNAME_Assay = "Assay";

	/** Set Quantity Assay.
	  * Indicated the Quantity Assay to use into Quality Order
	  */
	public void setAssay (BigDecimal Assay);

	/** Get Quantity Assay.
	  * Indicated the Quantity Assay to use into Quality Order
	  */
	public BigDecimal getAssay();

    /** Column name Author */
    public static final String COLUMNNAME_Author = "Author";

	/** Set Author.
	  * Author/Creator of the Entity
	  */
	public void setAuthor (String Author);

	/** Get Author.
	  * Author/Creator of the Entity
	  */
	public String getAuthor();

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

	public I_C_Activity getC_Activity();

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

	public I_C_Campaign getC_Campaign();

    /** Column name C_DocType_ID */
    public static final String COLUMNNAME_C_DocType_ID = "C_DocType_ID";

	/** Set Document Type.
	  * Document type or rules
	  */
	public void setC_DocType_ID (int C_DocType_ID);

	/** Get Document Type.
	  * Document type or rules
	  */
	public int getC_DocType_ID();

	public I_C_DocType getC_DocType();

    /** Column name C_OrderLine_ID */
    public static final String COLUMNNAME_C_OrderLine_ID = "C_OrderLine_ID";

	/** Set Sales Order Line.
	  * Sales Order Line
	  */
	public void setC_OrderLine_ID (int C_OrderLine_ID);

	/** Get Sales Order Line.
	  * Sales Order Line
	  */
	public int getC_OrderLine_ID();

	public I_C_OrderLine getC_OrderLine();

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

	public I_C_Project getC_Project();

    /** Column name C_UOM_ID */
    public static final String COLUMNNAME_C_UOM_ID = "C_UOM_ID";

	/** Set UOM.
	  * Unit of Measure
	  */
	public void setC_UOM_ID (int C_UOM_ID);

	/** Get UOM.
	  * Unit of Measure
	  */
	public int getC_UOM_ID();

	public I_C_UOM getC_UOM();

    /** Column name Cost */
    public static final String COLUMNNAME_Cost = "Cost";

	/** Set Cost.
	  * Cost information
	  */
	public void setCost (BigDecimal Cost);

	/** Get Cost.
	  * Cost information
	  */
	public BigDecimal getCost();

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

    /** Column name DateConfirm */
    public static final String COLUMNNAME_DateConfirm = "DateConfirm";

	/** Set DateConfirm	  */
	public void setDateConfirm (Timestamp DateConfirm);

	/** Get DateConfirm	  */
	public Timestamp getDateConfirm();

    /** Column name DateDelivered */
    public static final String COLUMNNAME_DateDelivered = "DateDelivered";

	/** Set Date Delivered.
	  * Date when the product was delivered
	  */
	public void setDateDelivered (Timestamp DateDelivered);

	/** Get Date Delivered.
	  * Date when the product was delivered
	  */
	public Timestamp getDateDelivered();

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

	/** Set DateFinishSchedule	  */
	public void setDateFinishSchedule (Timestamp DateFinishSchedule);

	/** Get DateFinishSchedule	  */
	public Timestamp getDateFinishSchedule();

    /** Column name DateOrdered */
    public static final String COLUMNNAME_DateOrdered = "DateOrdered";

	/** Set Date Ordered.
	  * Date of Order
	  */
	public void setDateOrdered (Timestamp DateOrdered);

	/** Get Date Ordered.
	  * Date of Order
	  */
	public Timestamp getDateOrdered();

    /** Column name DatePromised */
    public static final String COLUMNNAME_DatePromised = "DatePromised";

	/** Set Date Promised.
	  * Date Order was promised
	  */
	public void setDatePromised (Timestamp DatePromised);

	/** Get Date Promised.
	  * Date Order was promised
	  */
	public Timestamp getDatePromised();

    /** Column name DateStart */
    public static final String COLUMNNAME_DateStart = "DateStart";

	/** Set DateStart	  */
	public void setDateStart (Timestamp DateStart);

	/** Get DateStart	  */
	public Timestamp getDateStart();

    /** Column name DateStartSchedule */
    public static final String COLUMNNAME_DateStartSchedule = "DateStartSchedule";

	/** Set DateStartSchedule	  */
	public void setDateStartSchedule (Timestamp DateStartSchedule);

	/** Get DateStartSchedule	  */
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

    /** Column name DocumentType */
    public static final String COLUMNNAME_DocumentType = "DocumentType";

	/** Set Document Type.
	  * Document Type
	  */
	public void setDocumentType (String DocumentType);

	/** Get Document Type.
	  * Document Type
	  */
	public String getDocumentType();

    /** Column name DocumentTypeNote */
    public static final String COLUMNNAME_DocumentTypeNote = "DocumentTypeNote";

	/** Set Document Type Note.
	  * Optional note of a document type
	  */
	public void setDocumentTypeNote (String DocumentTypeNote);

	/** Get Document Type Note.
	  * Optional note of a document type
	  */
	public String getDocumentTypeNote();

    /** Column name Duration */
    public static final String COLUMNNAME_Duration = "Duration";

	/** Set Duration.
	  * Normal Duration in Duration Unit
	  */
	public void setDuration (int Duration);

	/** Get Duration.
	  * Normal Duration in Duration Unit
	  */
	public int getDuration();

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

    /** Column name FloatAfter */
    public static final String COLUMNNAME_FloatAfter = "FloatAfter";

	/** Set Float After	  */
	public void setFloatAfter (BigDecimal FloatAfter);

	/** Get Float After	  */
	public BigDecimal getFloatAfter();

    /** Column name FloatBefored */
    public static final String COLUMNNAME_FloatBefored = "FloatBefored";

	/** Set Float Befored	  */
	public void setFloatBefored (BigDecimal FloatBefored);

	/** Get Float Befored	  */
	public BigDecimal getFloatBefored();

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

    /** Column name Lot */
    public static final String COLUMNNAME_Lot = "Lot";

	/** Set Lot No.
	  * Lot number (alphanumeric)
	  */
	public void setLot (String Lot);

	/** Get Lot No.
	  * Lot number (alphanumeric)
	  */
	public String getLot();

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

	public I_M_Warehouse getM_Warehouse();

    /** Column name MovingTime */
    public static final String COLUMNNAME_MovingTime = "MovingTime";

	/** Set Moving Time	  */
	public void setMovingTime (int MovingTime);

	/** Get Moving Time	  */
	public int getMovingTime();

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

    /** Column name Org_Location_ID */
    public static final String COLUMNNAME_Org_Location_ID = "Org_Location_ID";

	/** Set Org Address.
	  * Organization Location/Address
	  */
	public void setOrg_Location_ID (int Org_Location_ID);

	/** Get Org Address.
	  * Organization Location/Address
	  */
	public int getOrg_Location_ID();

	public I_C_Location getOrg_Location();

    /** Column name OverlapUnits */
    public static final String COLUMNNAME_OverlapUnits = "OverlapUnits";

	/** Set Overlap Units.
	  * Overlap Units are number of units that must be completed before they are moved the next activity
	  */
	public void setOverlapUnits (BigDecimal OverlapUnits);

	/** Get Overlap Units.
	  * Overlap Units are number of units that must be completed before they are moved the next activity
	  */
	public BigDecimal getOverlapUnits();

    /** Column name Planner_ID */
    public static final String COLUMNNAME_Planner_ID = "Planner_ID";

	/** Set Planner	  */
	public void setPlanner_ID (int Planner_ID);

	/** Get Planner	  */
	public int getPlanner_ID();

	public I_AD_User getPlanner();

    /** Column name PP_Order_ID */
    public static final String COLUMNNAME_PP_Order_ID = "PP_Order_ID";

	/** Set Manufacturing Order	  */
	public void setPP_Order_ID (int PP_Order_ID);

	/** Get Manufacturing Order	  */
	public int getPP_Order_ID();

	public org.eevolution.model.I_PP_Order getPP_Order();

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

	public org.eevolution.model.I_PP_Product_BOM getPP_Product_BOM();

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

    /** Column name PublishStatus */
    public static final String COLUMNNAME_PublishStatus = "PublishStatus";

	/** Set Publication Status.
	  * Status of Publication
	  */
	public void setPublishStatus (boolean PublishStatus);

	/** Get Publication Status.
	  * Status of Publication
	  */
	public boolean isPublishStatus();

    /** Column name QtyBatchs */
    public static final String COLUMNNAME_QtyBatchs = "QtyBatchs";

	/** Set Qty Batchs	  */
	public void setQtyBatchs (BigDecimal QtyBatchs);

	/** Get Qty Batchs	  */
	public BigDecimal getQtyBatchs();

    /** Column name QtyBatchSize */
    public static final String COLUMNNAME_QtyBatchSize = "QtyBatchSize";

	/** Set Qty Batch Size	  */
	public void setQtyBatchSize (BigDecimal QtyBatchSize);

	/** Get Qty Batch Size	  */
	public BigDecimal getQtyBatchSize();

    /** Column name QtyDelivered */
    public static final String COLUMNNAME_QtyDelivered = "QtyDelivered";

	/** Set Delivered Quantity.
	  * Delivered Quantity
	  */
	public void setQtyDelivered (BigDecimal QtyDelivered);

	/** Get Delivered Quantity.
	  * Delivered Quantity
	  */
	public BigDecimal getQtyDelivered();

    /** Column name QtyEntered */
    public static final String COLUMNNAME_QtyEntered = "QtyEntered";

	/** Set Quantity.
	  * The Quantity Entered is based on the selected UoM
	  */
	public void setQtyEntered (BigDecimal QtyEntered);

	/** Get Quantity.
	  * The Quantity Entered is based on the selected UoM
	  */
	public BigDecimal getQtyEntered();

    /** Column name QtyOrdered */
    public static final String COLUMNNAME_QtyOrdered = "QtyOrdered";

	/** Set Ordered Quantity.
	  * Ordered Quantity
	  */
	public void setQtyOrdered (BigDecimal QtyOrdered);

	/** Get Ordered Quantity.
	  * Ordered Quantity
	  */
	public BigDecimal getQtyOrdered();

    /** Column name QtyReject */
    public static final String COLUMNNAME_QtyReject = "QtyReject";

	/** Set Qty Reject	  */
	public void setQtyReject (BigDecimal QtyReject);

	/** Get Qty Reject	  */
	public BigDecimal getQtyReject();

    /** Column name QtyReserved */
    public static final String COLUMNNAME_QtyReserved = "QtyReserved";

	/** Set Reserved Quantity.
	  * Reserved Quantity
	  */
	public void setQtyReserved (BigDecimal QtyReserved);

	/** Get Reserved Quantity.
	  * Reserved Quantity
	  */
	public BigDecimal getQtyReserved();

    /** Column name QtyScrap */
    public static final String COLUMNNAME_QtyScrap = "QtyScrap";

	/** Set QtyScrap.
	  * Scrap Quantity for this componet
	  */
	public void setQtyScrap (BigDecimal QtyScrap);

	/** Get QtyScrap.
	  * Scrap Quantity for this componet
	  */
	public BigDecimal getQtyScrap();

    /** Column name QueuingTime */
    public static final String COLUMNNAME_QueuingTime = "QueuingTime";

	/** Set Queuing Time	  */
	public void setQueuingTime (int QueuingTime);

	/** Get Queuing Time	  */
	public int getQueuingTime();

    /** Column name S_Resource_ID */
    public static final String COLUMNNAME_S_Resource_ID = "S_Resource_ID";

	/** Set Resource.
	  * Resource
	  */
	public void setS_Resource_ID (int S_Resource_ID);

	/** Get Resource.
	  * Resource
	  */
	public int getS_Resource_ID();

	public I_S_Resource getS_Resource();

    /** Column name SalesRep_Name */
    public static final String COLUMNNAME_SalesRep_Name = "SalesRep_Name";

	/** Set Sales Representative	  */
	public void setSalesRep_Name (String SalesRep_Name);

	/** Get Sales Representative	  */
	public String getSalesRep_Name();

    /** Column name SerNo */
    public static final String COLUMNNAME_SerNo = "SerNo";

	/** Set Serial No.
	  * Product Serial Number 
	  */
	public void setSerNo (String SerNo);

	/** Get Serial No.
	  * Product Serial Number 
	  */
	public String getSerNo();

    /** Column name SetupTime */
    public static final String COLUMNNAME_SetupTime = "SetupTime";

	/** Set Setup Time.
	  * Setup time before starting Production
	  */
	public void setSetupTime (int SetupTime);

	/** Get Setup Time.
	  * Setup time before starting Production
	  */
	public int getSetupTime();

    /** Column name TaxID */
    public static final String COLUMNNAME_TaxID = "TaxID";

	/** Set Tax ID.
	  * Tax Identification
	  */
	public void setTaxID (String TaxID);

	/** Get Tax ID.
	  * Tax Identification
	  */
	public String getTaxID();

    /** Column name UnitsCycles */
    public static final String COLUMNNAME_UnitsCycles = "UnitsCycles";

	/** Set Units by Cycles.
	  * The Units by Cycles are defined for process type  Flow Repetitive Dedicated and  indicated the product to be manufactured on a production line for duration unit.
	  */
	public void setUnitsCycles (BigDecimal UnitsCycles);

	/** Get Units by Cycles.
	  * The Units by Cycles are defined for process type  Flow Repetitive Dedicated and  indicated the product to be manufactured on a production line for duration unit.
	  */
	public BigDecimal getUnitsCycles();

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

    /** Column name Version */
    public static final String COLUMNNAME_Version = "Version";

	/** Set Version.
	  * Version of the table definition
	  */
	public void setVersion (int Version);

	/** Get Version.
	  * Version of the table definition
	  */
	public int getVersion();

    /** Column name WaitingTime */
    public static final String COLUMNNAME_WaitingTime = "WaitingTime";

	/** Set Waiting Time.
	  * Workflow Simulation Waiting time
	  */
	public void setWaitingTime (int WaitingTime);

	/** Get Waiting Time.
	  * Workflow Simulation Waiting time
	  */
	public int getWaitingTime();

    /** Column name Warehouse_Location_ID */
    public static final String COLUMNNAME_Warehouse_Location_ID = "Warehouse_Location_ID";

	/** Set Warehouse Address.
	  * Warehouse Location/Address
	  */
	public void setWarehouse_Location_ID (int Warehouse_Location_ID);

	/** Get Warehouse Address.
	  * Warehouse Location/Address
	  */
	public int getWarehouse_Location_ID();

	public I_C_Location getWarehouse_Location();

    /** Column name WorkflowType */
    public static final String COLUMNNAME_WorkflowType = "WorkflowType";

	/** Set Workflow Type.
	  * Type of Worflow
	  */
	public void setWorkflowType (String WorkflowType);

	/** Get Workflow Type.
	  * Type of Worflow
	  */
	public String getWorkflowType();

    /** Column name WorkingTime */
    public static final String COLUMNNAME_WorkingTime = "WorkingTime";

	/** Set Working Time.
	  * Workflow Simulation Execution Time
	  */
	public void setWorkingTime (int WorkingTime);

	/** Get Working Time.
	  * Workflow Simulation Execution Time
	  */
	public int getWorkingTime();

    /** Column name Yield */
    public static final String COLUMNNAME_Yield = "Yield";

	/** Set Yield %.
	  * The Yield is the percentage of a lot that is expected to be of acceptable wuality may fall below 100 percent
	  */
	public void setYield (int Yield);

	/** Get Yield %.
	  * The Yield is the percentage of a lot that is expected to be of acceptable wuality may fall below 100 percent
	  */
	public int getYield();
}
