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

import java.util.*;
import java.sql.Timestamp;
import java.math.*;
import org.compiere.util.*;

    /** Generated Interface for AD_WF_EventAudit
     *  @author Trifon Trifonov (generated) 
     *  @version Release 3.3.0 - 2007-08-24 11:39:30.609
     */
    public interface I_AD_WF_EventAudit 
{

    /** TableName=AD_WF_EventAudit */
    public static final String Table_Name = "AD_WF_EventAudit";

    /** AD_Table_ID=649 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = new BigDecimal(7);

    /** Load Meta Data */

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

	public I_AD_Table getI_AD_Table() throws Exception;

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

    /** Column name AD_WF_EventAudit_ID */
    public static final String COLUMNNAME_AD_WF_EventAudit_ID = "AD_WF_EventAudit_ID";

	/** Set Workflow Event Audit.
	  * Workflow Process Activity Event Audit Information
	  */
	public void setAD_WF_EventAudit_ID (int AD_WF_EventAudit_ID);

	/** Get Workflow Event Audit.
	  * Workflow Process Activity Event Audit Information
	  */
	public int getAD_WF_EventAudit_ID();

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

	public I_AD_WF_Node getI_AD_WF_Node() throws Exception;

    /** Column name AD_WF_Process_ID */
    public static final String COLUMNNAME_AD_WF_Process_ID = "AD_WF_Process_ID";

	/** Set Workflow Process.
	  * Actual Workflow Process Instance
	  */
	public void setAD_WF_Process_ID (int AD_WF_Process_ID);

	/** Get Workflow Process.
	  * Actual Workflow Process Instance
	  */
	public int getAD_WF_Process_ID();

	public I_AD_WF_Process getI_AD_WF_Process() throws Exception;

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

	public I_AD_WF_Responsible getI_AD_WF_Responsible() throws Exception;

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

    /** Column name ElapsedTimeMS */
    public static final String COLUMNNAME_ElapsedTimeMS = "ElapsedTimeMS";

	/** Set Elapsed Time ms.
	  * Elapsed Time in mili seconds
	  */
	public void setElapsedTimeMS (BigDecimal ElapsedTimeMS);

	/** Get Elapsed Time ms.
	  * Elapsed Time in mili seconds
	  */
	public BigDecimal getElapsedTimeMS();

    /** Column name EventType */
    public static final String COLUMNNAME_EventType = "EventType";

	/** Set Event Type.
	  * Type of Event
	  */
	public void setEventType (String EventType);

	/** Get Event Type.
	  * Type of Event
	  */
	public String getEventType();

    /** Column name NewValue */
    public static final String COLUMNNAME_NewValue = "NewValue";

	/** Set New Value.
	  * New field value
	  */
	public void setNewValue (String NewValue);

	/** Get New Value.
	  * New field value
	  */
	public String getNewValue();

    /** Column name OldValue */
    public static final String COLUMNNAME_OldValue = "OldValue";

	/** Set Old Value.
	  * The old file data
	  */
	public void setOldValue (String OldValue);

	/** Get Old Value.
	  * The old file data
	  */
	public String getOldValue();

    /** Column name Record_ID */
    public static final String COLUMNNAME_Record_ID = "Record_ID";

	/** Set Record ID.
	  * Direct internal record ID
	  */
	public void setRecord_ID (int Record_ID);

	/** Get Record ID.
	  * Direct internal record ID
	  */
	public int getRecord_ID();

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

    /** Column name WFState */
    public static final String COLUMNNAME_WFState = "WFState";

	/** Set Workflow State.
	  * State of the execution of the workflow
	  */
	public void setWFState (String WFState);

	/** Get Workflow State.
	  * State of the execution of the workflow
	  */
	public String getWFState();
}
