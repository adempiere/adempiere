/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
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
/** Generated Model for AD_Menu
 *  @author Adempiere (generated) 
 *  @version Release 3.2.0 - $Id$ */
public class X_AD_Menu extends PO
{
/** Standard Constructor
@param ctx context
@param AD_Menu_ID id
@param trxName transaction
*/
public X_AD_Menu (Properties ctx, int AD_Menu_ID, String trxName)
{
super (ctx, AD_Menu_ID, trxName);
/** if (AD_Menu_ID == 0)
{
setAD_Menu_ID (0);
setEntityType (null);	// U
setIsReadOnly (false);	// N
setIsSOTrx (false);
setIsSummary (false);
setName (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_Menu (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=116 */
public static final int Table_ID=MTable.getTable_ID("AD_Menu");

/** TableName=AD_Menu */
public static final String Table_Name="AD_Menu";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"AD_Menu");

protected BigDecimal accessLevel = BigDecimal.valueOf(6);
/** AccessLevel
@return 6 - System - Client 
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
StringBuffer sb = new StringBuffer ("X_AD_Menu[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Special Form.
@param AD_Form_ID Special Form */
public void setAD_Form_ID (int AD_Form_ID)
{
if (AD_Form_ID <= 0) set_Value ("AD_Form_ID", null);
 else 
set_Value ("AD_Form_ID", Integer.valueOf(AD_Form_ID));
}
/** Get Special Form.
@return Special Form */
public int getAD_Form_ID() 
{
Integer ii = (Integer)get_Value("AD_Form_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_Form_ID */
public static final String COLUMNNAME_AD_Form_ID = "AD_Form_ID";
/** Set Menu.
@param AD_Menu_ID Identifies a Menu */
public void setAD_Menu_ID (int AD_Menu_ID)
{
if (AD_Menu_ID < 1) throw new IllegalArgumentException ("AD_Menu_ID is mandatory.");
set_ValueNoCheck ("AD_Menu_ID", Integer.valueOf(AD_Menu_ID));
}
/** Get Menu.
@return Identifies a Menu */
public int getAD_Menu_ID() 
{
Integer ii = (Integer)get_Value("AD_Menu_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_Menu_ID */
public static final String COLUMNNAME_AD_Menu_ID = "AD_Menu_ID";
/** Set Process.
@param AD_Process_ID Process or Report */
public void setAD_Process_ID (int AD_Process_ID)
{
if (AD_Process_ID <= 0) set_Value ("AD_Process_ID", null);
 else 
set_Value ("AD_Process_ID", Integer.valueOf(AD_Process_ID));
}
/** Get Process.
@return Process or Report */
public int getAD_Process_ID() 
{
Integer ii = (Integer)get_Value("AD_Process_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_Process_ID */
public static final String COLUMNNAME_AD_Process_ID = "AD_Process_ID";
/** Set OS Task.
@param AD_Task_ID Operation System Task */
public void setAD_Task_ID (int AD_Task_ID)
{
if (AD_Task_ID <= 0) set_Value ("AD_Task_ID", null);
 else 
set_Value ("AD_Task_ID", Integer.valueOf(AD_Task_ID));
}
/** Get OS Task.
@return Operation System Task */
public int getAD_Task_ID() 
{
Integer ii = (Integer)get_Value("AD_Task_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_Task_ID */
public static final String COLUMNNAME_AD_Task_ID = "AD_Task_ID";
/** Set Window.
@param AD_Window_ID Data entry or display window */
public void setAD_Window_ID (int AD_Window_ID)
{
if (AD_Window_ID <= 0) set_Value ("AD_Window_ID", null);
 else 
set_Value ("AD_Window_ID", Integer.valueOf(AD_Window_ID));
}
/** Get Window.
@return Data entry or display window */
public int getAD_Window_ID() 
{
Integer ii = (Integer)get_Value("AD_Window_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_Window_ID */
public static final String COLUMNNAME_AD_Window_ID = "AD_Window_ID";
/** Set Workbench.
@param AD_Workbench_ID Collection of windows, reports */
public void setAD_Workbench_ID (int AD_Workbench_ID)
{
if (AD_Workbench_ID <= 0) set_Value ("AD_Workbench_ID", null);
 else 
set_Value ("AD_Workbench_ID", Integer.valueOf(AD_Workbench_ID));
}
/** Get Workbench.
@return Collection of windows, reports */
public int getAD_Workbench_ID() 
{
Integer ii = (Integer)get_Value("AD_Workbench_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_Workbench_ID */
public static final String COLUMNNAME_AD_Workbench_ID = "AD_Workbench_ID";
/** Set Workflow.
@param AD_Workflow_ID Workflow or combination of tasks */
public void setAD_Workflow_ID (int AD_Workflow_ID)
{
if (AD_Workflow_ID <= 0) set_Value ("AD_Workflow_ID", null);
 else 
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
/** Column name AD_Workflow_ID */
public static final String COLUMNNAME_AD_Workflow_ID = "AD_Workflow_ID";

/** Action AD_Reference_ID=104 */
public static final int ACTION_AD_Reference_ID=104;
/** Workbench = B */
public static final String ACTION_Workbench = "B";
/** WorkFlow = F */
public static final String ACTION_WorkFlow = "F";
/** Process = P */
public static final String ACTION_Process = "P";
/** Report = R */
public static final String ACTION_Report = "R";
/** Task = T */
public static final String ACTION_Task = "T";
/** Window = W */
public static final String ACTION_Window = "W";
/** Form = X */
public static final String ACTION_Form = "X";
/** Set Action.
@param Action Indicates the Action to be performed */
public void setAction (String Action)
{
if (Action == null || Action.equals("B") || Action.equals("F") || Action.equals("P") || Action.equals("R") || Action.equals("T") || Action.equals("W") || Action.equals("X"));
 else throw new IllegalArgumentException ("Action Invalid value - " + Action + " - Reference_ID=104 - B - F - P - R - T - W - X");
if (Action != null && Action.length() > 1)
{
log.warning("Length > 1 - truncated");
Action = Action.substring(0,0);
}
set_Value ("Action", Action);
}
/** Get Action.
@return Indicates the Action to be performed */
public String getAction() 
{
return (String)get_Value("Action");
}
/** Column name Action */
public static final String COLUMNNAME_Action = "Action";
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
/** Column name Description */
public static final String COLUMNNAME_Description = "Description";

/** EntityType AD_Reference_ID=389 */
public static final int ENTITYTYPE_AD_Reference_ID=389;
/** Set Entity Type.
@param EntityType Dictionary Entity Type;
 Determines ownership and synchronization */
public void setEntityType (String EntityType)
{
if (EntityType.length() > 4)
{
log.warning("Length > 4 - truncated");
EntityType = EntityType.substring(0,3);
}
set_Value ("EntityType", EntityType);
}
/** Get Entity Type.
@return Dictionary Entity Type;
 Determines ownership and synchronization */
public String getEntityType() 
{
return (String)get_Value("EntityType");
}
/** Column name EntityType */
public static final String COLUMNNAME_EntityType = "EntityType";
/** Set Read Only.
@param IsReadOnly Field is read only */
public void setIsReadOnly (boolean IsReadOnly)
{
set_Value ("IsReadOnly", Boolean.valueOf(IsReadOnly));
}
/** Get Read Only.
@return Field is read only */
public boolean isReadOnly() 
{
Object oo = get_Value("IsReadOnly");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsReadOnly */
public static final String COLUMNNAME_IsReadOnly = "IsReadOnly";
/** Set Sales Transaction.
@param IsSOTrx This is a Sales Transaction */
public void setIsSOTrx (boolean IsSOTrx)
{
set_Value ("IsSOTrx", Boolean.valueOf(IsSOTrx));
}
/** Get Sales Transaction.
@return This is a Sales Transaction */
public boolean isSOTrx() 
{
Object oo = get_Value("IsSOTrx");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsSOTrx */
public static final String COLUMNNAME_IsSOTrx = "IsSOTrx";
/** Set Summary Level.
@param IsSummary This is a summary entity */
public void setIsSummary (boolean IsSummary)
{
set_Value ("IsSummary", Boolean.valueOf(IsSummary));
}
/** Get Summary Level.
@return This is a summary entity */
public boolean isSummary() 
{
Object oo = get_Value("IsSummary");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsSummary */
public static final String COLUMNNAME_IsSummary = "IsSummary";
/** Set Name.
@param Name Alphanumeric identifier of the entity */
public void setName (String Name)
{
if (Name == null) throw new IllegalArgumentException ("Name is mandatory.");
if (Name.length() > 60)
{
log.warning("Length > 60 - truncated");
Name = Name.substring(0,59);
}
set_Value ("Name", Name);
}
/** Get Name.
@return Alphanumeric identifier of the entity */
public String getName() 
{
return (String)get_Value("Name");
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), getName());
}
/** Column name Name */
public static final String COLUMNNAME_Name = "Name";
}
