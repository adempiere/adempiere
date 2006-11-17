/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
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
/** Generated Model for AD_WF_EventAudit
 *  @author Jorg Janke (generated) 
 *  @version Release 2.5.3d - 2006-10-10 21:55:54.718 */
public class X_AD_WF_EventAudit extends PO
{
/** Standard Constructor
@param ctx context
@param AD_WF_EventAudit_ID id
@param trxName transaction
*/
public X_AD_WF_EventAudit (Properties ctx, int AD_WF_EventAudit_ID, String trxName)
{
super (ctx, AD_WF_EventAudit_ID, trxName);
/** if (AD_WF_EventAudit_ID == 0)
{
setAD_Table_ID (0);
setAD_WF_EventAudit_ID (0);
setAD_WF_Node_ID (0);
setAD_WF_Process_ID (0);
setAD_WF_Responsible_ID (0);
setElapsedTimeMS (Env.ZERO);
setEventType (null);
setRecord_ID (0);
setWFState (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_WF_EventAudit (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=649 */
public static final int Table_ID=649;

/** TableName=AD_WF_EventAudit */
public static final String Table_Name="AD_WF_EventAudit";

protected static KeyNamePair Model = new KeyNamePair(649,"AD_WF_EventAudit");

protected BigDecimal accessLevel = new BigDecimal(7);
/** AccessLevel
@return 7 - System - Client - Org 
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
StringBuffer sb = new StringBuffer ("X_AD_WF_EventAudit[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Table.
@param AD_Table_ID Database Table information */
public void setAD_Table_ID (int AD_Table_ID)
{
if (AD_Table_ID < 1) throw new IllegalArgumentException ("AD_Table_ID is mandatory.");
set_Value ("AD_Table_ID", new Integer(AD_Table_ID));
}
/** Get Table.
@return Database Table information */
public int getAD_Table_ID() 
{
Integer ii = (Integer)get_Value("AD_Table_ID");
if (ii == null) return 0;
return ii.intValue();
}

/** AD_User_ID AD_Reference_ID=110 */
public static final int AD_USER_ID_AD_Reference_ID=110;
/** Set User/Contact.
@param AD_User_ID User within the system - Internal or Business Partner Contact */
public void setAD_User_ID (int AD_User_ID)
{
if (AD_User_ID <= 0) set_Value ("AD_User_ID", null);
 else 
set_Value ("AD_User_ID", new Integer(AD_User_ID));
}
/** Get User/Contact.
@return User within the system - Internal or Business Partner Contact */
public int getAD_User_ID() 
{
Integer ii = (Integer)get_Value("AD_User_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Workflow Event Audit.
@param AD_WF_EventAudit_ID Workflow Process Activity Event Audit Information */
public void setAD_WF_EventAudit_ID (int AD_WF_EventAudit_ID)
{
if (AD_WF_EventAudit_ID < 1) throw new IllegalArgumentException ("AD_WF_EventAudit_ID is mandatory.");
set_ValueNoCheck ("AD_WF_EventAudit_ID", new Integer(AD_WF_EventAudit_ID));
}
/** Get Workflow Event Audit.
@return Workflow Process Activity Event Audit Information */
public int getAD_WF_EventAudit_ID() 
{
Integer ii = (Integer)get_Value("AD_WF_EventAudit_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getAD_WF_EventAudit_ID()));
}
/** Set Node.
@param AD_WF_Node_ID Workflow Node (activity), step or process */
public void setAD_WF_Node_ID (int AD_WF_Node_ID)
{
if (AD_WF_Node_ID < 1) throw new IllegalArgumentException ("AD_WF_Node_ID is mandatory.");
set_Value ("AD_WF_Node_ID", new Integer(AD_WF_Node_ID));
}
/** Get Node.
@return Workflow Node (activity), step or process */
public int getAD_WF_Node_ID() 
{
Integer ii = (Integer)get_Value("AD_WF_Node_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Workflow Process.
@param AD_WF_Process_ID Actual Workflow Process Instance */
public void setAD_WF_Process_ID (int AD_WF_Process_ID)
{
if (AD_WF_Process_ID < 1) throw new IllegalArgumentException ("AD_WF_Process_ID is mandatory.");
set_Value ("AD_WF_Process_ID", new Integer(AD_WF_Process_ID));
}
/** Get Workflow Process.
@return Actual Workflow Process Instance */
public int getAD_WF_Process_ID() 
{
Integer ii = (Integer)get_Value("AD_WF_Process_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Workflow Responsible.
@param AD_WF_Responsible_ID Responsible for Workflow Execution */
public void setAD_WF_Responsible_ID (int AD_WF_Responsible_ID)
{
if (AD_WF_Responsible_ID < 1) throw new IllegalArgumentException ("AD_WF_Responsible_ID is mandatory.");
set_Value ("AD_WF_Responsible_ID", new Integer(AD_WF_Responsible_ID));
}
/** Get Workflow Responsible.
@return Responsible for Workflow Execution */
public int getAD_WF_Responsible_ID() 
{
Integer ii = (Integer)get_Value("AD_WF_Responsible_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Attribute Name.
@param AttributeName Name of the Attribute */
public void setAttributeName (String AttributeName)
{
if (AttributeName != null && AttributeName.length() > 60)
{
log.warning("Length > 60 - truncated");
AttributeName = AttributeName.substring(0,59);
}
set_Value ("AttributeName", AttributeName);
}
/** Get Attribute Name.
@return Name of the Attribute */
public String getAttributeName() 
{
return (String)get_Value("AttributeName");
}
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
/** Set Elapsed Time ms.
@param ElapsedTimeMS Elapsed Time in mili seconds */
public void setElapsedTimeMS (BigDecimal ElapsedTimeMS)
{
if (ElapsedTimeMS == null) throw new IllegalArgumentException ("ElapsedTimeMS is mandatory.");
set_Value ("ElapsedTimeMS", ElapsedTimeMS);
}
/** Get Elapsed Time ms.
@return Elapsed Time in mili seconds */
public BigDecimal getElapsedTimeMS() 
{
BigDecimal bd = (BigDecimal)get_Value("ElapsedTimeMS");
if (bd == null) return Env.ZERO;
return bd;
}

/** EventType AD_Reference_ID=306 */
public static final int EVENTTYPE_AD_Reference_ID=306;
/** Process Created = PC */
public static final String EVENTTYPE_ProcessCreated = "PC";
/** Process Completed = PX */
public static final String EVENTTYPE_ProcessCompleted = "PX";
/** State Changed = SC */
public static final String EVENTTYPE_StateChanged = "SC";
/** Set Event Type.
@param EventType Type of Event */
public void setEventType (String EventType)
{
if (EventType == null) throw new IllegalArgumentException ("EventType is mandatory");
if (EventType.equals("PC") || EventType.equals("PX") || EventType.equals("SC"));
 else throw new IllegalArgumentException ("EventType Invalid value - " + EventType + " - Reference_ID=306 - PC - PX - SC");
if (EventType.length() > 2)
{
log.warning("Length > 2 - truncated");
EventType = EventType.substring(0,1);
}
set_Value ("EventType", EventType);
}
/** Get Event Type.
@return Type of Event */
public String getEventType() 
{
return (String)get_Value("EventType");
}
/** Set New Value.
@param NewValue New field value */
public void setNewValue (String NewValue)
{
if (NewValue != null && NewValue.length() > 2000)
{
log.warning("Length > 2000 - truncated");
NewValue = NewValue.substring(0,1999);
}
set_Value ("NewValue", NewValue);
}
/** Get New Value.
@return New field value */
public String getNewValue() 
{
return (String)get_Value("NewValue");
}
/** Set Old Value.
@param OldValue The old file data */
public void setOldValue (String OldValue)
{
if (OldValue != null && OldValue.length() > 2000)
{
log.warning("Length > 2000 - truncated");
OldValue = OldValue.substring(0,1999);
}
set_Value ("OldValue", OldValue);
}
/** Get Old Value.
@return The old file data */
public String getOldValue() 
{
return (String)get_Value("OldValue");
}
/** Set Record ID.
@param Record_ID Direct internal record ID */
public void setRecord_ID (int Record_ID)
{
if (Record_ID < 0) throw new IllegalArgumentException ("Record_ID is mandatory.");
set_Value ("Record_ID", new Integer(Record_ID));
}
/** Get Record ID.
@return Direct internal record ID */
public int getRecord_ID() 
{
Integer ii = (Integer)get_Value("Record_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Text Message.
@param TextMsg Text Message */
public void setTextMsg (String TextMsg)
{
if (TextMsg != null && TextMsg.length() > 2000)
{
log.warning("Length > 2000 - truncated");
TextMsg = TextMsg.substring(0,1999);
}
set_Value ("TextMsg", TextMsg);
}
/** Get Text Message.
@return Text Message */
public String getTextMsg() 
{
return (String)get_Value("TextMsg");
}

/** WFState AD_Reference_ID=305 */
public static final int WFSTATE_AD_Reference_ID=305;
/** Aborted = CA */
public static final String WFSTATE_Aborted = "CA";
/** Completed = CC */
public static final String WFSTATE_Completed = "CC";
/** Terminated = CT */
public static final String WFSTATE_Terminated = "CT";
/** Not Started = ON */
public static final String WFSTATE_NotStarted = "ON";
/** Running = OR */
public static final String WFSTATE_Running = "OR";
/** Suspended = OS */
public static final String WFSTATE_Suspended = "OS";
/** Set Workflow State.
@param WFState State of the execution of the workflow */
public void setWFState (String WFState)
{
if (WFState == null) throw new IllegalArgumentException ("WFState is mandatory");
if (WFState.equals("CA") || WFState.equals("CC") || WFState.equals("CT") || WFState.equals("ON") || WFState.equals("OR") || WFState.equals("OS"));
 else throw new IllegalArgumentException ("WFState Invalid value - " + WFState + " - Reference_ID=305 - CA - CC - CT - ON - OR - OS");
if (WFState.length() > 2)
{
log.warning("Length > 2 - truncated");
WFState = WFState.substring(0,1);
}
set_Value ("WFState", WFState);
}
/** Get Workflow State.
@return State of the execution of the workflow */
public String getWFState() 
{
return (String)get_Value("WFState");
}
}
