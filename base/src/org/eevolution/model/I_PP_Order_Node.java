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
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for PP_Order_Node
 *  @author Adempiere (generated) 
 *  @version Release 3.6.0LTS
 */
public interface I_PP_Order_Node 
{

    /** TableName=PP_Order_Node */
    public static final String Table_Name = "PP_Order_Node";

    /** AD_Table_ID=53022 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

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

	public I_AD_Column getAD_Column() throws RuntimeException;

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

	public I_AD_Form getAD_Form() throws RuntimeException;

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

	public I_AD_Image getAD_Image() throws RuntimeException;

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

	public I_AD_Process getAD_Process() throws RuntimeException;

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

	public I_AD_Task getAD_Task() throws RuntimeException;

    /** Column name AD_WF_Block_ID */
    public static final String COLUMNNAME_AD_WF_Block_ID = "AD_WF_Block_ID";

	/** Set Workflow Block.
	  * Workflow Transaction Execution Block
	  */
	public void setAD_WF_Block_ID (int AD_WF_Block_ID);

	/** Get Workflow Block.
	  * Workflow Transaction Execution Block
	  */
	public int getAD_WF_Block_ID();

	public I_AD_WF_Block getAD_WF_Block() throws RuntimeException;

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

	public I_AD_WF_Node getAD_WF_Node() throws RuntimeException;

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

	public I_AD_WF_Responsible getAD_WF_Responsible() throws RuntimeException;

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

	public I_AD_Window getAD_Window() throws RuntimeException;

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

	public I_AD_Workflow getAD_Workflow() throws RuntimeException;

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

	public I_C_BPartner getC_BPartner() throws RuntimeException;

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

	/** Set Date Finish Schedule.
	  * Scheduled Finish date for this Order
	  */
	public void setDateFinishSchedule (Timestamp DateFinishSchedule);

	/** Get Date Finish Schedule.
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

	/** Set Date Start Schedule.
	  * Scheduled start date for this Order
	  */
	public void setDateStartSchedule (Timestamp DateStartSchedule);

	/** Get Date Start Schedule.
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

    /** Column name DurationReal */
    public static final String COLUMNNAME_DurationReal = "DurationReal";

	/** Set Duration Real	  */
	public void setDurationReal (int DurationReal);

	/** Get Duration Real	  */
	public int getDurationReal();

    /** Column name DurationRequiered */
    public static final String COLUMNNAME_DurationRequiered = "DurationRequiered";

	/** Set Duration Requiered	  */
	public void setDurationRequiered (int DurationRequiered);

	/** Get Duration Requiered	  */
	public int getDurationRequiered();

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

    /** Column name PP_Order_Node_ID */
    public static final String COLUMNNAME_PP_Order_Node_ID = "PP_Order_Node_ID";

	/** Set Manufacturing Order Activity.
	  * Workflow Node (activity), step or process
	  */
	public void setPP_Order_Node_ID (int PP_Order_Node_ID);

	/** Get Manufacturing Order Activity.
	  * Workflow Node (activity), step or process
	  */
	public int getPP_Order_Node_ID();

    /** Column name PP_Order_Workflow_ID */
    public static final String COLUMNNAME_PP_Order_Workflow_ID = "PP_Order_Workflow_ID";

	/** Set Manufacturing Order Workflow	  */
	public void setPP_Order_Workflow_ID (int PP_Order_Workflow_ID);

	/** Get Manufacturing Order Workflow	  */
	public int getPP_Order_Workflow_ID();

	public org.eevolution.model.I_PP_Order_Workflow getPP_Order_Workflow() throws RuntimeException;

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

    /** Column name QtyReject */
    public static final String COLUMNNAME_QtyReject = "QtyReject";

	/** Set Qty Reject	  */
	public void setQtyReject (BigDecimal QtyReject);

	/** Get Qty Reject	  */
	public BigDecimal getQtyReject();

    /** Column name QtyRequiered */
    public static final String COLUMNNAME_QtyRequiered = "QtyRequiered";

	/** Set Qty Requiered	  */
	public void setQtyRequiered (BigDecimal QtyRequiered);

	/** Get Qty Requiered	  */
	public BigDecimal getQtyRequiered();

    /** Column name QtyScrap */
    public static final String COLUMNNAME_QtyScrap = "QtyScrap";

	/** Set Scrap %.
	  * Scrap % Quantity for this componet
	  */
	public void setQtyScrap (BigDecimal QtyScrap);

	/** Get Scrap %.
	  * Scrap % Quantity for this componet
	  */
	public BigDecimal getQtyScrap();

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

    /** Column name SetupTimeReal */
    public static final String COLUMNNAME_SetupTimeReal = "SetupTimeReal";

	/** Set Setup Time Real	  */
	public void setSetupTimeReal (int SetupTimeReal);

	/** Get Setup Time Real	  */
	public int getSetupTimeReal();

    /** Column name SetupTimeRequiered */
    public static final String COLUMNNAME_SetupTimeRequiered = "SetupTimeRequiered";

	/** Set Setup Time Requiered	  */
	public void setSetupTimeRequiered (int SetupTimeRequiered);

	/** Get Setup Time Requiered	  */
	public int getSetupTimeRequiered();

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

	public I_S_Resource getS_Resource() throws RuntimeException;

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

    /** Column name UnitsCycles */
    public static final String COLUMNNAME_UnitsCycles = "UnitsCycles";

	/** Set Units by Cycles.
	  * The Units by Cycles are defined for process type  Flow Repetitive Dedicated and  indicated the product to be manufactured on a production line for duration unit.
	  */
	public void setUnitsCycles (int UnitsCycles);

	/** Get Units by Cycles.
	  * The Units by Cycles are defined for process type  Flow Repetitive Dedicated and  indicated the product to be manufactured on a production line for duration unit.
	  */
	public int getUnitsCycles();

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

    /** Column name Workflow_ID */
    public static final String COLUMNNAME_Workflow_ID = "Workflow_ID";

	/** Set Workflow.
	  * Workflow or tasks
	  */
	public void setWorkflow_ID (int Workflow_ID);

	/** Get Workflow.
	  * Workflow or tasks
	  */
	public int getWorkflow_ID();

	public I_AD_Workflow getWorkflow() throws RuntimeException;

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

    /** Column name XPosition */
    public static final String COLUMNNAME_XPosition = "XPosition";

	/** Set X Position.
	  * Absolute X (horizontal) position in 1/72 of an inch
	  */
	public void setXPosition (int XPosition);

	/** Get X Position.
	  * Absolute X (horizontal) position in 1/72 of an inch
	  */
	public int getXPosition();

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

    /** Column name YPosition */
    public static final String COLUMNNAME_YPosition = "YPosition";

	/** Set Y Position.
	  * Absolute Y (vertical) position in 1/72 of an inch
	  */
	public void setYPosition (int YPosition);

	/** Get Y Position.
	  * Absolute Y (vertical) position in 1/72 of an inch
	  */
	public int getYPosition();
}
