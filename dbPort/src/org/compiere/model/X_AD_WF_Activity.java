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
/** Generated Model for AD_WF_Activity
 *  @author Jorg Janke (generated) 
 *  @version Release 3.1.3 - $Id$ */
public class X_AD_WF_Activity extends PO
{
/** Standard Constructor
@param ctx context
@param AD_WF_Activity_ID id
@param trxName transaction
*/
public X_AD_WF_Activity (Properties ctx, int AD_WF_Activity_ID, String trxName)
{
super (ctx, AD_WF_Activity_ID, trxName);
/** if (AD_WF_Activity_ID == 0)
{
setAD_Table_ID (0);
setAD_WF_Activity_ID (0);
setAD_WF_Node_ID (0);
setAD_WF_Process_ID (0);
setAD_Workflow_ID (0);
setProcessed (false);
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
public X_AD_WF_Activity (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=644 */
public static final int Table_ID=MTable.getTable_ID("AD_WF_Activity");

/** TableName=AD_WF_Activity */
public static final String Table_Name="AD_WF_Activity";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"AD_WF_Activity");

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
StringBuffer sb = new StringBuffer ("X_AD_WF_Activity[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Message.
@param AD_Message_ID System Message */
public void setAD_Message_ID (int AD_Message_ID)
{
if (AD_Message_ID <= 0) set_Value ("AD_Message_ID", null);
 else 
set_Value ("AD_Message_ID", Integer.valueOf(AD_Message_ID));
}
/** Get Message.
@return System Message */
public int getAD_Message_ID() 
{
Integer ii = (Integer)get_Value("AD_Message_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Table.
@param AD_Table_ID Database Table information */
public void setAD_Table_ID (int AD_Table_ID)
{
if (AD_Table_ID < 1) throw new IllegalArgumentException ("AD_Table_ID is mandatory.");
set_Value ("AD_Table_ID", Integer.valueOf(AD_Table_ID));
}
/** Get Table.
@return Database Table information */
public int getAD_Table_ID() 
{
Integer ii = (Integer)get_Value("AD_Table_ID");
if (ii == null) return 0;
return ii.intValue();
}

/** AD_User_ID AD_Reference_ID=286 */
public static final int AD_USER_ID_AD_Reference_ID=286;
/** Set User/Contact.
@param AD_User_ID User within the system - Internal or Business Partner Contact */
public void setAD_User_ID (int AD_User_ID)
{
if (AD_User_ID <= 0) set_Value ("AD_User_ID", null);
 else 
set_Value ("AD_User_ID", Integer.valueOf(AD_User_ID));
}
/** Get User/Contact.
@return User within the system - Internal or Business Partner Contact */
public int getAD_User_ID() 
{
Integer ii = (Integer)get_Value("AD_User_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Workflow Activity.
@param AD_WF_Activity_ID Workflow Activity */
public void setAD_WF_Activity_ID (int AD_WF_Activity_ID)
{
if (AD_WF_Activity_ID < 1) throw new IllegalArgumentException ("AD_WF_Activity_ID is mandatory.");
set_ValueNoCheck ("AD_WF_Activity_ID", Integer.valueOf(AD_WF_Activity_ID));
}
/** Get Workflow Activity.
@return Workflow Activity */
public int getAD_WF_Activity_ID() 
{
Integer ii = (Integer)get_Value("AD_WF_Activity_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Node.
@param AD_WF_Node_ID Workflow Node (activity), step or process */
public void setAD_WF_Node_ID (int AD_WF_Node_ID)
{
if (AD_WF_Node_ID < 1) throw new IllegalArgumentException ("AD_WF_Node_ID is mandatory.");
set_Value ("AD_WF_Node_ID", Integer.valueOf(AD_WF_Node_ID));
}
/** Get Node.
@return Workflow Node (activity), step or process */
public int getAD_WF_Node_ID() 
{
Integer ii = (Integer)get_Value("AD_WF_Node_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getAD_WF_Node_ID()));
}
/** Set Workflow Process.
@param AD_WF_Process_ID Actual Workflow Process Instance */
public void setAD_WF_Process_ID (int AD_WF_Process_ID)
{
if (AD_WF_Process_ID < 1) throw new IllegalArgumentException ("AD_WF_Process_ID is mandatory.");
set_ValueNoCheck ("AD_WF_Process_ID", Integer.valueOf(AD_WF_Process_ID));
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
if (AD_WF_Responsible_ID <= 0) set_Value ("AD_WF_Responsible_ID", null);
 else 
set_Value ("AD_WF_Responsible_ID", Integer.valueOf(AD_WF_Responsible_ID));
}
/** Get Workflow Responsible.
@return Responsible for Workflow Execution */
public int getAD_WF_Responsible_ID() 
{
Integer ii = (Integer)get_Value("AD_WF_Responsible_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Workflow.
@param AD_Workflow_ID Workflow or combination of tasks */
public void setAD_Workflow_ID (int AD_Workflow_ID)
{
if (AD_Workflow_ID < 1) throw new IllegalArgumentException ("AD_Workflow_ID is mandatory.");
set_Value ("AD_Workflow_ID", Integer.valueOf(AD_Workflow_ID));
}
/** Get Workflow.
@return Workflow or combination of tasks */
public int getAD_Workflow_ID() 
{
Integer ii = (Integer)get_Value("AD_Workflow_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Last Alert.
@param DateLastAlert Date when last alert were sent */
public void setDateLastAlert (Timestamp DateLastAlert)
{
set_Value ("DateLastAlert", DateLastAlert);
}
/** Get Last Alert.
@return Date when last alert were sent */
public Timestamp getDateLastAlert() 
{
return (Timestamp)get_Value("DateLastAlert");
}
/** Set Dyn Priority Start.
@param DynPriorityStart Starting priority before changed dynamically */
public void setDynPriorityStart (int DynPriorityStart)
{
set_Value ("DynPriorityStart", Integer.valueOf(DynPriorityStart));
}
/** Get Dyn Priority Start.
@return Starting priority before changed dynamically */
public int getDynPriorityStart() 
{
Integer ii = (Integer)get_Value("DynPriorityStart");
if (ii == null) return 0;
return ii.intValue();
}
/** Set End Wait.
@param EndWaitTime End of sleep time */
public void setEndWaitTime (Timestamp EndWaitTime)
{
set_Value ("EndWaitTime", EndWaitTime);
}
/** Get End Wait.
@return End of sleep time */
public Timestamp getEndWaitTime() 
{
return (Timestamp)get_Value("EndWaitTime");
}
/** Set Priority.
@param Priority Indicates if this request is of a high, medium or low priority. */
public void setPriority (int Priority)
{
set_Value ("Priority", Integer.valueOf(Priority));
}
/** Get Priority.
@return Indicates if this request is of a high, medium or low priority. */
public int getPriority() 
{
Integer ii = (Integer)get_Value("Priority");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Processed.
@param Processed The document has been processed */
public void setProcessed (boolean Processed)
{
set_Value ("Processed", Boolean.valueOf(Processed));
}
/** Get Processed.
@return The document has been processed */
public boolean isProcessed() 
{
Object oo = get_Value("Processed");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Process Now.
@param Processing Process Now */
public void setProcessing (boolean Processing)
{
set_Value ("Processing", Boolean.valueOf(Processing));
}
/** Get Process Now.
@return Process Now */
public boolean isProcessing() 
{
Object oo = get_Value("Processing");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Record ID.
@param Record_ID Direct internal record ID */
public void setRecord_ID (int Record_ID)
{
if (Record_ID < 0) throw new IllegalArgumentException ("Record_ID is mandatory.");
set_Value ("Record_ID", Integer.valueOf(Record_ID));
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
