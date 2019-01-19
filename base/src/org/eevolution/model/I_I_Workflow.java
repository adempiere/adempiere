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

/** Generated Interface for I_Workflow
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1
 */
public interface I_I_Workflow 
{

    /** TableName=I_Workflow */
    public static final String Table_Name = "I_Workflow";

    /** AD_Table_ID=53380 */
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

    /** Column name AD_Column_ID */
    public static final String COLUMNNAME_AD_Column_ID = "AD_Column_ID";

	/** Set Column.
	  * Column in the table
	  */
	public void setAD_Column_ID (int AD_Column_ID);

	/** Get Column.
	  * Column in the table
	  */
	public int getAD_Column_ID();

	public org.compiere.model.I_AD_Column getAD_Column() throws RuntimeException;

    /** Column name AD_Form_ID */
    public static final String COLUMNNAME_AD_Form_ID = "AD_Form_ID";

	/** Set Special Form.
	  * Special Form
	  */
	public void setAD_Form_ID (int AD_Form_ID);

	/** Get Special Form.
	  * Special Form
	  */
	public int getAD_Form_ID();

	public org.compiere.model.I_AD_Form getAD_Form() throws RuntimeException;

    /** Column name AD_Image_ID */
    public static final String COLUMNNAME_AD_Image_ID = "AD_Image_ID";

	/** Set Image.
	  * Image or Icon
	  */
	public void setAD_Image_ID (int AD_Image_ID);

	/** Get Image.
	  * Image or Icon
	  */
	public int getAD_Image_ID();

	public org.compiere.model.I_AD_Image getAD_Image() throws RuntimeException;

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

    /** Column name AD_Process_ID */
    public static final String COLUMNNAME_AD_Process_ID = "AD_Process_ID";

	/** Set Process.
	  * Process or Report
	  */
	public void setAD_Process_ID (int AD_Process_ID);

	/** Get Process.
	  * Process or Report
	  */
	public int getAD_Process_ID();

	public org.compiere.model.I_AD_Process getAD_Process() throws RuntimeException;

    /** Column name AD_Table_ID */
    public static final String COLUMNNAME_AD_Table_ID = "AD_Table_ID";

	/** Set Table.
	  * Database Table information
	  */
	public void setAD_Table_ID (int AD_Table_ID);

	/** Get Table.
	  * Database Table information
	  */
	public int getAD_Table_ID();

	public org.compiere.model.I_AD_Table getAD_Table() throws RuntimeException;

    /** Column name AD_Task_ID */
    public static final String COLUMNNAME_AD_Task_ID = "AD_Task_ID";

	/** Set OS Task.
	  * Operation System Task
	  */
	public void setAD_Task_ID (int AD_Task_ID);

	/** Get OS Task.
	  * Operation System Task
	  */
	public int getAD_Task_ID();

	public org.compiere.model.I_AD_Task getAD_Task() throws RuntimeException;

    /** Column name AD_WF_Node_ID */
    public static final String COLUMNNAME_AD_WF_Node_ID = "AD_WF_Node_ID";

	/** Set Node.
	  * Workflow Node (activity), step or process
	  */
	public void setAD_WF_Node_ID (int AD_WF_Node_ID);

	/** Get Node.
	  * Workflow Node (activity), step or process
	  */
	public int getAD_WF_Node_ID();

	public org.compiere.model.I_AD_WF_Node getAD_WF_Node() throws RuntimeException;

    /** Column name AD_WF_Responsible_ID */
    public static final String COLUMNNAME_AD_WF_Responsible_ID = "AD_WF_Responsible_ID";

	/** Set Workflow Responsible.
	  * Responsible for Workflow Execution
	  */
	public void setAD_WF_Responsible_ID (int AD_WF_Responsible_ID);

	/** Get Workflow Responsible.
	  * Responsible for Workflow Execution
	  */
	public int getAD_WF_Responsible_ID();

	public org.compiere.model.I_AD_WF_Responsible getAD_WF_Responsible() throws RuntimeException;

    /** Column name AD_Window_ID */
    public static final String COLUMNNAME_AD_Window_ID = "AD_Window_ID";

	/** Set Window.
	  * Data entry or display window
	  */
	public void setAD_Window_ID (int AD_Window_ID);

	/** Get Window.
	  * Data entry or display window
	  */
	public int getAD_Window_ID();

	public org.compiere.model.I_AD_Window getAD_Window() throws RuntimeException;

    /** Column name AD_WorkflowProcessor_ID */
    public static final String COLUMNNAME_AD_WorkflowProcessor_ID = "AD_WorkflowProcessor_ID";

	/** Set Workflow Processor.
	  * Workflow Processor Server
	  */
	public void setAD_WorkflowProcessor_ID (int AD_WorkflowProcessor_ID);

	/** Get Workflow Processor.
	  * Workflow Processor Server
	  */
	public int getAD_WorkflowProcessor_ID();

	public org.compiere.model.I_AD_WorkflowProcessor getAD_WorkflowProcessor() throws RuntimeException;

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

    /** Column name AccessLevel */
    public static final String COLUMNNAME_AccessLevel = "AccessLevel";

	/** Set Data Access Level.
	  * Access Level required
	  */
	public void setAccessLevel (String AccessLevel);

	/** Get Data Access Level.
	  * Access Level required
	  */
	public String getAccessLevel();

    /** Column name Action */
    public static final String COLUMNNAME_Action = "Action";

	/** Set Action.
	  * Indicates the Action to be performed
	  */
	public void setAction (String Action);

	/** Get Action.
	  * Indicates the Action to be performed
	  */
	public String getAction();

    /** Column name AttributeName */
    public static final String COLUMNNAME_AttributeName = "AttributeName";

	/** Set Attribute Name.
	  * Name of the Attribute
	  */
	public void setAttributeName (String AttributeName);

	/** Get Attribute Name.
	  * Name of the Attribute
	  */
	public String getAttributeName();

    /** Column name AttributeValue */
    public static final String COLUMNNAME_AttributeValue = "AttributeValue";

	/** Set Attribute Value.
	  * Value of the Attribute
	  */
	public void setAttributeValue (String AttributeValue);

	/** Get Attribute Value.
	  * Value of the Attribute
	  */
	public String getAttributeValue();

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

    /** Column name ColumnName */
    public static final String COLUMNNAME_ColumnName = "ColumnName";

	/** Set DB Column Name.
	  * Name of the column in the database
	  */
	public void setColumnName (String ColumnName);

	/** Get DB Column Name.
	  * Name of the column in the database
	  */
	public String getColumnName();

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

    /** Column name DocAction */
    public static final String COLUMNNAME_DocAction = "DocAction";

	/** Set Document Action.
	  * The targeted status of the document
	  */
	public void setDocAction (String DocAction);

	/** Get Document Action.
	  * The targeted status of the document
	  */
	public String getDocAction();

    /** Column name DocValueLogic */
    public static final String COLUMNNAME_DocValueLogic = "DocValueLogic";

	/** Set Document Value Logic.
	  * Logic to determine Workflow Start - If true, a workflow process is started for the document
	  */
	public void setDocValueLogic (String DocValueLogic);

	/** Get Document Value Logic.
	  * Logic to determine Workflow Start - If true, a workflow process is started for the document
	  */
	public String getDocValueLogic();

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

    /** Column name DynPriorityChange */
    public static final String COLUMNNAME_DynPriorityChange = "DynPriorityChange";

	/** Set Dynamic Priority Change.
	  * Change of priority when Activity is suspended waiting for user
	  */
	public void setDynPriorityChange (BigDecimal DynPriorityChange);

	/** Get Dynamic Priority Change.
	  * Change of priority when Activity is suspended waiting for user
	  */
	public BigDecimal getDynPriorityChange();

    /** Column name DynPriorityUnit */
    public static final String COLUMNNAME_DynPriorityUnit = "DynPriorityUnit";

	/** Set Dynamic Priority Unit.
	  * Change of priority when Activity is suspended waiting for user
	  */
	public void setDynPriorityUnit (String DynPriorityUnit);

	/** Get Dynamic Priority Unit.
	  * Change of priority when Activity is suspended waiting for user
	  */
	public String getDynPriorityUnit();

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

    /** Column name EMailRecipient */
    public static final String COLUMNNAME_EMailRecipient = "EMailRecipient";

	/** Set EMail Recipient.
	  * Recipient of the EMail
	  */
	public void setEMailRecipient (String EMailRecipient);

	/** Get EMail Recipient.
	  * Recipient of the EMail
	  */
	public String getEMailRecipient();

    /** Column name EntityType */
    public static final String COLUMNNAME_EntityType = "EntityType";

	/** Set Entity Type.
	  * Dictionary Entity Type;
 Determines ownership and synchronization
	  */
	public void setEntityType (String EntityType);

	/** Get Entity Type.
	  * Dictionary Entity Type;
 Determines ownership and synchronization
	  */
	public String getEntityType();

    /** Column name FinishMode */
    public static final String COLUMNNAME_FinishMode = "FinishMode";

	/** Set Finish Mode.
	  * Workflow Activity Finish Mode
	  */
	public void setFinishMode (String FinishMode);

	/** Get Finish Mode.
	  * Workflow Activity Finish Mode
	  */
	public String getFinishMode();

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

    /** Column name I_Workflow_ID */
    public static final String COLUMNNAME_I_Workflow_ID = "I_Workflow_ID";

	/** Set I_Workflow_ID	  */
	public void setI_Workflow_ID (int I_Workflow_ID);

	/** Get I_Workflow_ID	  */
	public int getI_Workflow_ID();

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

    /** Column name IsBetaFunctionality */
    public static final String COLUMNNAME_IsBetaFunctionality = "IsBetaFunctionality";

	/** Set Beta Functionality.
	  * This functionality is considered Beta
	  */
	public void setIsBetaFunctionality (boolean IsBetaFunctionality);

	/** Get Beta Functionality.
	  * This functionality is considered Beta
	  */
	public boolean isBetaFunctionality();

    /** Column name IsCentrallyMaintained */
    public static final String COLUMNNAME_IsCentrallyMaintained = "IsCentrallyMaintained";

	/** Set Centrally maintained.
	  * Information maintained in System Element table
	  */
	public void setIsCentrallyMaintained (boolean IsCentrallyMaintained);

	/** Get Centrally maintained.
	  * Information maintained in System Element table
	  */
	public boolean isCentrallyMaintained();

    /** Column name IsDefault */
    public static final String COLUMNNAME_IsDefault = "IsDefault";

	/** Set Default.
	  * Default value
	  */
	public void setIsDefault (boolean IsDefault);

	/** Get Default.
	  * Default value
	  */
	public boolean isDefault();

    /** Column name IsMilestone */
    public static final String COLUMNNAME_IsMilestone = "IsMilestone";

	/** Set Is Milestone	  */
	public void setIsMilestone (boolean IsMilestone);

	/** Get Is Milestone	  */
	public boolean isMilestone();

    /** Column name IsSubcontracting */
    public static final String COLUMNNAME_IsSubcontracting = "IsSubcontracting";

	/** Set Is Subcontracting	  */
	public void setIsSubcontracting (boolean IsSubcontracting);

	/** Get Is Subcontracting	  */
	public boolean isSubcontracting();

    /** Column name JoinElement */
    public static final String COLUMNNAME_JoinElement = "JoinElement";

	/** Set Join Element.
	  * Semantics for multiple incoming Transitions
	  */
	public void setJoinElement (String JoinElement);

	/** Get Join Element.
	  * Semantics for multiple incoming Transitions
	  */
	public String getJoinElement();

    /** Column name Limit */
    public static final String COLUMNNAME_Limit = "Limit";

	/** Set Duration Limit.
	  * Maximum Duration in Duration Unit
	  */
	public void setLimit (int Limit);

	/** Get Duration Limit.
	  * Maximum Duration in Duration Unit
	  */
	public int getLimit();

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

    /** Column name NodeName */
    public static final String COLUMNNAME_NodeName = "NodeName";

	/** Set Node Name	  */
	public void setNodeName (String NodeName);

	/** Get Node Name	  */
	public String getNodeName();

    /** Column name NodeNextValue */
    public static final String COLUMNNAME_NodeNextValue = "NodeNextValue";

	/** Set Node Next Key.
	  * Next Node in Transition
	  */
	public void setNodeNextValue (String NodeNextValue);

	/** Get Node Next Key.
	  * Next Node in Transition
	  */
	public String getNodeNextValue();

    /** Column name NodeValue */
    public static final String COLUMNNAME_NodeValue = "NodeValue";

	/** Set Node Key	  */
	public void setNodeValue (String NodeValue);

	/** Get Node Key	  */
	public String getNodeValue();

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

    /** Column name OverlapUnits */
    public static final String COLUMNNAME_OverlapUnits = "OverlapUnits";

	/** Set Overlap Units.
	  * Overlap Units are number of units that must be completed before they are moved the next activity
	  */
	public void setOverlapUnits (int OverlapUnits);

	/** Get Overlap Units.
	  * Overlap Units are number of units that must be completed before they are moved the next activity
	  */
	public int getOverlapUnits();

    /** Column name Priority */
    public static final String COLUMNNAME_Priority = "Priority";

	/** Set Priority.
	  * Indicates if this request is of a high, medium or low priority.
	  */
	public void setPriority (int Priority);

	/** Get Priority.
	  * Indicates if this request is of a high, medium or low priority.
	  */
	public int getPriority();

    /** Column name ProcessType */
    public static final String COLUMNNAME_ProcessType = "ProcessType";

	/** Set Process Type	  */
	public void setProcessType (String ProcessType);

	/** Get Process Type	  */
	public String getProcessType();

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

    /** Column name PublishStatus */
    public static final String COLUMNNAME_PublishStatus = "PublishStatus";

	/** Set Publication Status.
	  * Status of Publication
	  */
	public void setPublishStatus (String PublishStatus);

	/** Get Publication Status.
	  * Status of Publication
	  */
	public String getPublishStatus();

    /** Column name QtyBatchSize */
    public static final String COLUMNNAME_QtyBatchSize = "QtyBatchSize";

	/** Set Qty Batch Size	  */
	public void setQtyBatchSize (BigDecimal QtyBatchSize);

	/** Get Qty Batch Size	  */
	public BigDecimal getQtyBatchSize();

    /** Column name QueuingTime */
    public static final String COLUMNNAME_QueuingTime = "QueuingTime";

	/** Set Queuing Time.
	  * Queue time is the time a job waits at a work center before begin handled.
	  */
	public void setQueuingTime (int QueuingTime);

	/** Get Queuing Time.
	  * Queue time is the time a job waits at a work center before begin handled.
	  */
	public int getQueuingTime();

    /** Column name R_MailText_ID */
    public static final String COLUMNNAME_R_MailText_ID = "R_MailText_ID";

	/** Set Mail Template.
	  * Text templates for mailings
	  */
	public void setR_MailText_ID (int R_MailText_ID);

	/** Get Mail Template.
	  * Text templates for mailings
	  */
	public int getR_MailText_ID();

	public org.compiere.model.I_R_MailText getR_MailText() throws RuntimeException;

    /** Column name ResourceValue */
    public static final String COLUMNNAME_ResourceValue = "ResourceValue";

	/** Set Resource Key.
	  * Key of the Resource
	  */
	public void setResourceValue (String ResourceValue);

	/** Get Resource Key.
	  * Key of the Resource
	  */
	public String getResourceValue();

    /** Column name ResponsibleName */
    public static final String COLUMNNAME_ResponsibleName = "ResponsibleName";

	/** Set Responsible Name	  */
	public void setResponsibleName (String ResponsibleName);

	/** Get Responsible Name	  */
	public String getResponsibleName();

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

	public org.compiere.model.I_S_Resource getS_Resource() throws RuntimeException;

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

    /** Column name SplitElement */
    public static final String COLUMNNAME_SplitElement = "SplitElement";

	/** Set Split Element.
	  * Semantics for multiple outgoing Transitions
	  */
	public void setSplitElement (String SplitElement);

	/** Get Split Element.
	  * Semantics for multiple outgoing Transitions
	  */
	public String getSplitElement();

    /** Column name StartMode */
    public static final String COLUMNNAME_StartMode = "StartMode";

	/** Set Start Mode.
	  * Workflow Activity Start Mode 
	  */
	public void setStartMode (String StartMode);

	/** Get Start Mode.
	  * Workflow Activity Start Mode 
	  */
	public String getStartMode();

    /** Column name SubflowExecution */
    public static final String COLUMNNAME_SubflowExecution = "SubflowExecution";

	/** Set Subflow Execution.
	  * Mode how the sub-workflow is executed
	  */
	public void setSubflowExecution (String SubflowExecution);

	/** Get Subflow Execution.
	  * Mode how the sub-workflow is executed
	  */
	public String getSubflowExecution();

    /** Column name TableName */
    public static final String COLUMNNAME_TableName = "TableName";

	/** Set DB Table Name.
	  * Name of the table in the database
	  */
	public void setTableName (String TableName);

	/** Get DB Table Name.
	  * Name of the table in the database
	  */
	public String getTableName();

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

    /** Column name WFProcessorName */
    public static final String COLUMNNAME_WFProcessorName = "WFProcessorName";

	/** Set WF Processor Name	  */
	public void setWFProcessorName (String WFProcessorName);

	/** Get WF Processor Name	  */
	public String getWFProcessorName();

    /** Column name WaitTime */
    public static final String COLUMNNAME_WaitTime = "WaitTime";

	/** Set Wait Time.
	  * Time in minutes to wait (sleep)
	  */
	public void setWaitTime (int WaitTime);

	/** Get Wait Time.
	  * Time in minutes to wait (sleep)
	  */
	public int getWaitTime();

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

    /** Column name WorkflowType */
    public static final String COLUMNNAME_WorkflowType = "WorkflowType";

	/** Set Workflow Type.
	  * Type of Workflow
	  */
	public void setWorkflowType (String WorkflowType);

	/** Get Workflow Type.
	  * Type of Workflow
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
