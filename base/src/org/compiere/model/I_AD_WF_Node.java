/**********************************************************************
 * This file is part of Adempiere ERP Bazaar                          *
 * http://www.adempiere.org                                           *
 *                                                                    *
 * Copyright (C) Trifon Trifonov.                                     *
 * Copyright (C) Contributors                                         *
 *                                                                    *
 * This program is free software;
 you can redistribute it and/or      *
 * modify it under the terms of the GNU General Public License        *
 * as published by the Free Software Foundation;
 either version 2     *
 * of the License, or (at your option) any later version.             *
 *                                                                    *
 * This program is distributed in the hope that it will be useful,    *
 * but WITHOUT ANY WARRANTY;
 without even the implied warranty of     *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the       *
 * GNU General Public License for more details.                       *
 *                                                                    *
 * You should have received a copy of the GNU General Public License  *
 * along with this program;
 if not, write to the Free Software        *
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,         *
 * MA 02110-1301, USA.                                                *
 *                                                                    *
 * Contributors:                                                      *
 * - Trifon Trifonov (trifonnt@users.sourceforge.net)                 *
 *                                                                    *
 * Sponsors:                                                          *
 * - Company (http://www.site.com)                                    *
 **********************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for AD_WF_Node
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.3.1t
 */
public interface I_AD_WF_Node 
{

    /** TableName=AD_WF_Node */
    public static final String Table_Name = "AD_WF_Node";

    /** AD_Table_ID=129 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

    /** Load Meta Data */

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

	public I_AD_Column getAD_Column() throws Exception;

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

	public I_AD_Form getAD_Form() throws Exception;

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

	public I_AD_Image getAD_Image() throws Exception;

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

	public I_AD_Process getAD_Process() throws Exception;

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

	public I_AD_Task getAD_Task() throws Exception;

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

	public I_AD_WF_Block getAD_WF_Block() throws Exception;

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

	public I_AD_WF_Responsible getAD_WF_Responsible() throws Exception;

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

	public I_AD_Window getAD_Window() throws Exception;

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

	public I_AD_Workflow getAD_Workflow() throws Exception;

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

	public I_R_MailText getR_MailText() throws Exception;

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
