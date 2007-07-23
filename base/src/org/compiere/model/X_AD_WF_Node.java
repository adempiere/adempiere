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
/** Generated Model for AD_WF_Node
 *  @author Adempiere (generated) 
 *  @version Release 3.3.0 - $Id$ */
public class X_AD_WF_Node extends PO
{
/** Standard Constructor
@param ctx context
@param AD_WF_Node_ID id
@param trxName transaction
*/
public X_AD_WF_Node (Properties ctx, int AD_WF_Node_ID, String trxName)
{
super (ctx, AD_WF_Node_ID, trxName);
/** if (AD_WF_Node_ID == 0)
{
setAD_WF_Node_ID (0);
setAD_Workflow_ID (0);
setAction (null);	// N
setCost (Env.ZERO);
setDuration (0);
setEntityType (null);	// U
setIsCentrallyMaintained (true);	// Y
setJoinElement (null);	// X
setLimit (0);
setName (null);
setSplitElement (null);	// X
setValue (null);
setWaitingTime (0);
setXPosition (0);
setYPosition (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_WF_Node (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** TableName=AD_WF_Node */
public static final String Table_Name="AD_WF_Node";

/** AD_Table_ID=129 */
public static final int Table_ID=MTable.getTable_ID(Table_Name);

protected static KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

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
StringBuffer sb = new StringBuffer ("X_AD_WF_Node[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Column.
@param AD_Column_ID Column in the table */
public void setAD_Column_ID (int AD_Column_ID)
{
if (AD_Column_ID <= 0) set_Value ("AD_Column_ID", null);
 else 
set_Value ("AD_Column_ID", Integer.valueOf(AD_Column_ID));
}
/** Get Column.
@return Column in the table */
public int getAD_Column_ID() 
{
Integer ii = (Integer)get_Value("AD_Column_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_Column_ID */
public static final String COLUMNNAME_AD_Column_ID = "AD_Column_ID";
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
/** Set Image.
@param AD_Image_ID Image or Icon */
public void setAD_Image_ID (int AD_Image_ID)
{
if (AD_Image_ID <= 0) set_Value ("AD_Image_ID", null);
 else 
set_Value ("AD_Image_ID", Integer.valueOf(AD_Image_ID));
}
/** Get Image.
@return Image or Icon */
public int getAD_Image_ID() 
{
Integer ii = (Integer)get_Value("AD_Image_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_Image_ID */
public static final String COLUMNNAME_AD_Image_ID = "AD_Image_ID";
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
/** Set Workflow Block.
@param AD_WF_Block_ID Workflow Transaction Execution Block */
public void setAD_WF_Block_ID (int AD_WF_Block_ID)
{
if (AD_WF_Block_ID <= 0) set_Value ("AD_WF_Block_ID", null);
 else 
set_Value ("AD_WF_Block_ID", Integer.valueOf(AD_WF_Block_ID));
}
/** Get Workflow Block.
@return Workflow Transaction Execution Block */
public int getAD_WF_Block_ID() 
{
Integer ii = (Integer)get_Value("AD_WF_Block_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_WF_Block_ID */
public static final String COLUMNNAME_AD_WF_Block_ID = "AD_WF_Block_ID";
/** Set Node.
@param AD_WF_Node_ID Workflow Node (activity), step or process */
public void setAD_WF_Node_ID (int AD_WF_Node_ID)
{
if (AD_WF_Node_ID < 1) throw new IllegalArgumentException ("AD_WF_Node_ID is mandatory.");
set_ValueNoCheck ("AD_WF_Node_ID", Integer.valueOf(AD_WF_Node_ID));
}
/** Get Node.
@return Workflow Node (activity), step or process */
public int getAD_WF_Node_ID() 
{
Integer ii = (Integer)get_Value("AD_WF_Node_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_WF_Node_ID */
public static final String COLUMNNAME_AD_WF_Node_ID = "AD_WF_Node_ID";
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
/** Column name AD_WF_Responsible_ID */
public static final String COLUMNNAME_AD_WF_Responsible_ID = "AD_WF_Responsible_ID";
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
/** Set Workflow.
@param AD_Workflow_ID Workflow or combination of tasks */
public void setAD_Workflow_ID (int AD_Workflow_ID)
{
if (AD_Workflow_ID < 1) throw new IllegalArgumentException ("AD_Workflow_ID is mandatory.");
set_ValueNoCheck ("AD_Workflow_ID", Integer.valueOf(AD_Workflow_ID));
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

/** Action AD_Reference_ID=302 */
public static final int ACTION_AD_Reference_ID=302;
/** User Workbench = B */
public static final String ACTION_UserWorkbench = "B";
/** User Choice = C */
public static final String ACTION_UserChoice = "C";
/** Document Action = D */
public static final String ACTION_DocumentAction = "D";
/** Sub Workflow = F */
public static final String ACTION_SubWorkflow = "F";
/** EMail = M */
public static final String ACTION_EMail = "M";
/** Apps Process = P */
public static final String ACTION_AppsProcess = "P";
/** Apps Report = R */
public static final String ACTION_AppsReport = "R";
/** Apps Task = T */
public static final String ACTION_AppsTask = "T";
/** Set Variable = V */
public static final String ACTION_SetVariable = "V";
/** User Window = W */
public static final String ACTION_UserWindow = "W";
/** User Form = X */
public static final String ACTION_UserForm = "X";
/** Wait (Sleep) = Z */
public static final String ACTION_WaitSleep = "Z";
/** Set Action.
@param Action Indicates the Action to be performed */
public void setAction (String Action)
{
if (Action == null) throw new IllegalArgumentException ("Action is mandatory");
if (Action.equals("B") || Action.equals("C") || Action.equals("D") || Action.equals("F") || Action.equals("M") || Action.equals("P") || Action.equals("R") || Action.equals("T") || Action.equals("V") || Action.equals("W") || Action.equals("X") || Action.equals("Z"));
 else throw new IllegalArgumentException ("Action Invalid value - " + Action + " - Reference_ID=302 - B - C - D - F - M - P - R - T - V - W - X - Z");
if (Action.length() > 1)
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
/** Column name AttributeName */
public static final String COLUMNNAME_AttributeName = "AttributeName";
/** Set Attribute Value.
@param AttributeValue Value of the Attribute */
public void setAttributeValue (String AttributeValue)
{
if (AttributeValue != null && AttributeValue.length() > 60)
{
log.warning("Length > 60 - truncated");
AttributeValue = AttributeValue.substring(0,59);
}
set_Value ("AttributeValue", AttributeValue);
}
/** Get Attribute Value.
@return Value of the Attribute */
public String getAttributeValue() 
{
return (String)get_Value("AttributeValue");
}
/** Column name AttributeValue */
public static final String COLUMNNAME_AttributeValue = "AttributeValue";
/** Set Cost.
@param Cost Cost information */
public void setCost (BigDecimal Cost)
{
if (Cost == null) throw new IllegalArgumentException ("Cost is mandatory.");
set_Value ("Cost", Cost);
}
/** Get Cost.
@return Cost information */
public BigDecimal getCost() 
{
BigDecimal bd = (BigDecimal)get_Value("Cost");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name Cost */
public static final String COLUMNNAME_Cost = "Cost";
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

/** DocAction AD_Reference_ID=135 */
public static final int DOCACTION_AD_Reference_ID=135;
/** <None> = -- */
public static final String DOCACTION_None = "--";
/** Approve = AP */
public static final String DOCACTION_Approve = "AP";
/** Close = CL */
public static final String DOCACTION_Close = "CL";
/** Complete = CO */
public static final String DOCACTION_Complete = "CO";
/** Invalidate = IN */
public static final String DOCACTION_Invalidate = "IN";
/** Post = PO */
public static final String DOCACTION_Post = "PO";
/** Prepare = PR */
public static final String DOCACTION_Prepare = "PR";
/** Reverse - Accrual = RA */
public static final String DOCACTION_Reverse_Accrual = "RA";
/** Reverse - Correct = RC */
public static final String DOCACTION_Reverse_Correct = "RC";
/** Re-activate = RE */
public static final String DOCACTION_Re_Activate = "RE";
/** Reject = RJ */
public static final String DOCACTION_Reject = "RJ";
/** Void = VO */
public static final String DOCACTION_Void = "VO";
/** Wait Complete = WC */
public static final String DOCACTION_WaitComplete = "WC";
/** Unlock = XL */
public static final String DOCACTION_Unlock = "XL";
/** Set Document Action.
@param DocAction The targeted status of the document */
public void setDocAction (String DocAction)
{
if (DocAction == null || DocAction.equals("--") || DocAction.equals("AP") || DocAction.equals("CL") || DocAction.equals("CO") || DocAction.equals("IN") || DocAction.equals("PO") || DocAction.equals("PR") || DocAction.equals("RA") || DocAction.equals("RC") || DocAction.equals("RE") || DocAction.equals("RJ") || DocAction.equals("VO") || DocAction.equals("WC") || DocAction.equals("XL"));
 else throw new IllegalArgumentException ("DocAction Invalid value - " + DocAction + " - Reference_ID=135 - -- - AP - CL - CO - IN - PO - PR - RA - RC - RE - RJ - VO - WC - XL");
if (DocAction != null && DocAction.length() > 2)
{
log.warning("Length > 2 - truncated");
DocAction = DocAction.substring(0,1);
}
set_Value ("DocAction", DocAction);
}
/** Get Document Action.
@return The targeted status of the document */
public String getDocAction() 
{
return (String)get_Value("DocAction");
}
/** Column name DocAction */
public static final String COLUMNNAME_DocAction = "DocAction";
/** Set Duration.
@param Duration Normal Duration in Duration Unit */
public void setDuration (int Duration)
{
set_Value ("Duration", Integer.valueOf(Duration));
}
/** Get Duration.
@return Normal Duration in Duration Unit */
public int getDuration() 
{
Integer ii = (Integer)get_Value("Duration");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name Duration */
public static final String COLUMNNAME_Duration = "Duration";
/** Set Dynamic Priority Change.
@param DynPriorityChange Change of priority when Activity is suspended waiting for user */
public void setDynPriorityChange (BigDecimal DynPriorityChange)
{
set_Value ("DynPriorityChange", DynPriorityChange);
}
/** Get Dynamic Priority Change.
@return Change of priority when Activity is suspended waiting for user */
public BigDecimal getDynPriorityChange() 
{
BigDecimal bd = (BigDecimal)get_Value("DynPriorityChange");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name DynPriorityChange */
public static final String COLUMNNAME_DynPriorityChange = "DynPriorityChange";

/** DynPriorityUnit AD_Reference_ID=221 */
public static final int DYNPRIORITYUNIT_AD_Reference_ID=221;
/** Day = D */
public static final String DYNPRIORITYUNIT_Day = "D";
/** Hour = H */
public static final String DYNPRIORITYUNIT_Hour = "H";
/** Minute = M */
public static final String DYNPRIORITYUNIT_Minute = "M";
/** Set Dynamic Priority Unit.
@param DynPriorityUnit Change of priority when Activity is suspended waiting for user */
public void setDynPriorityUnit (String DynPriorityUnit)
{
if (DynPriorityUnit == null || DynPriorityUnit.equals("D") || DynPriorityUnit.equals("H") || DynPriorityUnit.equals("M"));
 else throw new IllegalArgumentException ("DynPriorityUnit Invalid value - " + DynPriorityUnit + " - Reference_ID=221 - D - H - M");
if (DynPriorityUnit != null && DynPriorityUnit.length() > 1)
{
log.warning("Length > 1 - truncated");
DynPriorityUnit = DynPriorityUnit.substring(0,0);
}
set_Value ("DynPriorityUnit", DynPriorityUnit);
}
/** Get Dynamic Priority Unit.
@return Change of priority when Activity is suspended waiting for user */
public String getDynPriorityUnit() 
{
return (String)get_Value("DynPriorityUnit");
}
/** Column name DynPriorityUnit */
public static final String COLUMNNAME_DynPriorityUnit = "DynPriorityUnit";
/** Set EMail Address.
@param EMail Electronic Mail Address */
public void setEMail (String EMail)
{
if (EMail != null && EMail.length() > 60)
{
log.warning("Length > 60 - truncated");
EMail = EMail.substring(0,59);
}
set_Value ("EMail", EMail);
}
/** Get EMail Address.
@return Electronic Mail Address */
public String getEMail() 
{
return (String)get_Value("EMail");
}
/** Column name EMail */
public static final String COLUMNNAME_EMail = "EMail";

/** EMailRecipient AD_Reference_ID=363 */
public static final int EMAILRECIPIENT_AD_Reference_ID=363;
/** Document Business Partner = B */
public static final String EMAILRECIPIENT_DocumentBusinessPartner = "B";
/** Document Owner = D */
public static final String EMAILRECIPIENT_DocumentOwner = "D";
/** WF Responsible = R */
public static final String EMAILRECIPIENT_WFResponsible = "R";
/** Set EMail Recipient.
@param EMailRecipient Recipient of the EMail */
public void setEMailRecipient (String EMailRecipient)
{
if (EMailRecipient == null || EMailRecipient.equals("B") || EMailRecipient.equals("D") || EMailRecipient.equals("R"));
 else throw new IllegalArgumentException ("EMailRecipient Invalid value - " + EMailRecipient + " - Reference_ID=363 - B - D - R");
if (EMailRecipient != null && EMailRecipient.length() > 1)
{
log.warning("Length > 1 - truncated");
EMailRecipient = EMailRecipient.substring(0,0);
}
set_Value ("EMailRecipient", EMailRecipient);
}
/** Get EMail Recipient.
@return Recipient of the EMail */
public String getEMailRecipient() 
{
return (String)get_Value("EMailRecipient");
}
/** Column name EMailRecipient */
public static final String COLUMNNAME_EMailRecipient = "EMailRecipient";

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

/** FinishMode AD_Reference_ID=303 */
public static final int FINISHMODE_AD_Reference_ID=303;
/** Automatic = A */
public static final String FINISHMODE_Automatic = "A";
/** Manual = M */
public static final String FINISHMODE_Manual = "M";
/** Set Finish Mode.
@param FinishMode Workflow Activity Finish Mode */
public void setFinishMode (String FinishMode)
{
if (FinishMode == null || FinishMode.equals("A") || FinishMode.equals("M"));
 else throw new IllegalArgumentException ("FinishMode Invalid value - " + FinishMode + " - Reference_ID=303 - A - M");
if (FinishMode != null && FinishMode.length() > 1)
{
log.warning("Length > 1 - truncated");
FinishMode = FinishMode.substring(0,0);
}
set_Value ("FinishMode", FinishMode);
}
/** Get Finish Mode.
@return Workflow Activity Finish Mode */
public String getFinishMode() 
{
return (String)get_Value("FinishMode");
}
/** Column name FinishMode */
public static final String COLUMNNAME_FinishMode = "FinishMode";
/** Set Comment/Help.
@param Help Comment or Hint */
public void setHelp (String Help)
{
if (Help != null && Help.length() > 2000)
{
log.warning("Length > 2000 - truncated");
Help = Help.substring(0,1999);
}
set_Value ("Help", Help);
}
/** Get Comment/Help.
@return Comment or Hint */
public String getHelp() 
{
return (String)get_Value("Help");
}
/** Column name Help */
public static final String COLUMNNAME_Help = "Help";
/** Set Centrally maintained.
@param IsCentrallyMaintained Information maintained in System Element table */
public void setIsCentrallyMaintained (boolean IsCentrallyMaintained)
{
set_Value ("IsCentrallyMaintained", Boolean.valueOf(IsCentrallyMaintained));
}
/** Get Centrally maintained.
@return Information maintained in System Element table */
public boolean isCentrallyMaintained() 
{
Object oo = get_Value("IsCentrallyMaintained");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsCentrallyMaintained */
public static final String COLUMNNAME_IsCentrallyMaintained = "IsCentrallyMaintained";

/** JoinElement AD_Reference_ID=301 */
public static final int JOINELEMENT_AD_Reference_ID=301;
/** AND = A */
public static final String JOINELEMENT_AND = "A";
/** XOR = X */
public static final String JOINELEMENT_XOR = "X";
/** Set Join Element.
@param JoinElement Semantics for multiple incoming Transitions */
public void setJoinElement (String JoinElement)
{
if (JoinElement == null) throw new IllegalArgumentException ("JoinElement is mandatory");
if (JoinElement.equals("A") || JoinElement.equals("X"));
 else throw new IllegalArgumentException ("JoinElement Invalid value - " + JoinElement + " - Reference_ID=301 - A - X");
if (JoinElement.length() > 1)
{
log.warning("Length > 1 - truncated");
JoinElement = JoinElement.substring(0,0);
}
set_Value ("JoinElement", JoinElement);
}
/** Get Join Element.
@return Semantics for multiple incoming Transitions */
public String getJoinElement() 
{
return (String)get_Value("JoinElement");
}
/** Column name JoinElement */
public static final String COLUMNNAME_JoinElement = "JoinElement";
/** Set Duration Limit.
@param Limit Maximum Duration in Duration Unit */
public void setLimit (int Limit)
{
set_Value ("Limit", Integer.valueOf(Limit));
}
/** Get Duration Limit.
@return Maximum Duration in Duration Unit */
public int getLimit() 
{
Integer ii = (Integer)get_Value("Limit");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name Limit */
public static final String COLUMNNAME_Limit = "Limit";
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
/** Column name Priority */
public static final String COLUMNNAME_Priority = "Priority";
/** Set Mail Template.
@param R_MailText_ID Text templates for mailings */
public void setR_MailText_ID (int R_MailText_ID)
{
if (R_MailText_ID <= 0) set_Value ("R_MailText_ID", null);
 else 
set_Value ("R_MailText_ID", Integer.valueOf(R_MailText_ID));
}
/** Get Mail Template.
@return Text templates for mailings */
public int getR_MailText_ID() 
{
Integer ii = (Integer)get_Value("R_MailText_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name R_MailText_ID */
public static final String COLUMNNAME_R_MailText_ID = "R_MailText_ID";

/** SplitElement AD_Reference_ID=301 */
public static final int SPLITELEMENT_AD_Reference_ID=301;
/** AND = A */
public static final String SPLITELEMENT_AND = "A";
/** XOR = X */
public static final String SPLITELEMENT_XOR = "X";
/** Set Split Element.
@param SplitElement Semantics for multiple outgoing Transitions */
public void setSplitElement (String SplitElement)
{
if (SplitElement == null) throw new IllegalArgumentException ("SplitElement is mandatory");
if (SplitElement.equals("A") || SplitElement.equals("X"));
 else throw new IllegalArgumentException ("SplitElement Invalid value - " + SplitElement + " - Reference_ID=301 - A - X");
if (SplitElement.length() > 1)
{
log.warning("Length > 1 - truncated");
SplitElement = SplitElement.substring(0,0);
}
set_Value ("SplitElement", SplitElement);
}
/** Get Split Element.
@return Semantics for multiple outgoing Transitions */
public String getSplitElement() 
{
return (String)get_Value("SplitElement");
}
/** Column name SplitElement */
public static final String COLUMNNAME_SplitElement = "SplitElement";

/** StartMode AD_Reference_ID=303 */
public static final int STARTMODE_AD_Reference_ID=303;
/** Automatic = A */
public static final String STARTMODE_Automatic = "A";
/** Manual = M */
public static final String STARTMODE_Manual = "M";
/** Set Start Mode.
@param StartMode Workflow Activity Start Mode  */
public void setStartMode (String StartMode)
{
if (StartMode == null || StartMode.equals("A") || StartMode.equals("M"));
 else throw new IllegalArgumentException ("StartMode Invalid value - " + StartMode + " - Reference_ID=303 - A - M");
if (StartMode != null && StartMode.length() > 1)
{
log.warning("Length > 1 - truncated");
StartMode = StartMode.substring(0,0);
}
set_Value ("StartMode", StartMode);
}
/** Get Start Mode.
@return Workflow Activity Start Mode  */
public String getStartMode() 
{
return (String)get_Value("StartMode");
}
/** Column name StartMode */
public static final String COLUMNNAME_StartMode = "StartMode";

/** SubflowExecution AD_Reference_ID=307 */
public static final int SUBFLOWEXECUTION_AD_Reference_ID=307;
/** Asynchronously = A */
public static final String SUBFLOWEXECUTION_Asynchronously = "A";
/** Synchronously = S */
public static final String SUBFLOWEXECUTION_Synchronously = "S";
/** Set Subflow Execution.
@param SubflowExecution Mode how the sub-workflow is executed */
public void setSubflowExecution (String SubflowExecution)
{
if (SubflowExecution == null || SubflowExecution.equals("A") || SubflowExecution.equals("S"));
 else throw new IllegalArgumentException ("SubflowExecution Invalid value - " + SubflowExecution + " - Reference_ID=307 - A - S");
if (SubflowExecution != null && SubflowExecution.length() > 1)
{
log.warning("Length > 1 - truncated");
SubflowExecution = SubflowExecution.substring(0,0);
}
set_Value ("SubflowExecution", SubflowExecution);
}
/** Get Subflow Execution.
@return Mode how the sub-workflow is executed */
public String getSubflowExecution() 
{
return (String)get_Value("SubflowExecution");
}
/** Column name SubflowExecution */
public static final String COLUMNNAME_SubflowExecution = "SubflowExecution";
/** Set Search Key.
@param Value Search key for the record in the format required - must be unique */
public void setValue (String Value)
{
if (Value == null) throw new IllegalArgumentException ("Value is mandatory.");
if (Value.length() > 40)
{
log.warning("Length > 40 - truncated");
Value = Value.substring(0,39);
}
set_Value ("Value", Value);
}
/** Get Search Key.
@return Search key for the record in the format required - must be unique */
public String getValue() 
{
return (String)get_Value("Value");
}
/** Column name Value */
public static final String COLUMNNAME_Value = "Value";
/** Set Wait Time.
@param WaitTime Time in minutes to wait (sleep) */
public void setWaitTime (int WaitTime)
{
set_Value ("WaitTime", Integer.valueOf(WaitTime));
}
/** Get Wait Time.
@return Time in minutes to wait (sleep) */
public int getWaitTime() 
{
Integer ii = (Integer)get_Value("WaitTime");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name WaitTime */
public static final String COLUMNNAME_WaitTime = "WaitTime";
/** Set Waiting Time.
@param WaitingTime Workflow Simulation Waiting time */
public void setWaitingTime (int WaitingTime)
{
set_Value ("WaitingTime", Integer.valueOf(WaitingTime));
}
/** Get Waiting Time.
@return Workflow Simulation Waiting time */
public int getWaitingTime() 
{
Integer ii = (Integer)get_Value("WaitingTime");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name WaitingTime */
public static final String COLUMNNAME_WaitingTime = "WaitingTime";

/** Workflow_ID AD_Reference_ID=174 */
public static final int WORKFLOW_ID_AD_Reference_ID=174;
/** Set Workflow.
@param Workflow_ID Workflow or tasks */
public void setWorkflow_ID (int Workflow_ID)
{
if (Workflow_ID <= 0) set_Value ("Workflow_ID", null);
 else 
set_Value ("Workflow_ID", Integer.valueOf(Workflow_ID));
}
/** Get Workflow.
@return Workflow or tasks */
public int getWorkflow_ID() 
{
Integer ii = (Integer)get_Value("Workflow_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name Workflow_ID */
public static final String COLUMNNAME_Workflow_ID = "Workflow_ID";
/** Set Working Time.
@param WorkingTime Workflow Simulation Execution Time */
public void setWorkingTime (int WorkingTime)
{
set_Value ("WorkingTime", Integer.valueOf(WorkingTime));
}
/** Get Working Time.
@return Workflow Simulation Execution Time */
public int getWorkingTime() 
{
Integer ii = (Integer)get_Value("WorkingTime");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name WorkingTime */
public static final String COLUMNNAME_WorkingTime = "WorkingTime";
/** Set X Position.
@param XPosition Absolute X (horizontal) position in 1/72 of an inch */
public void setXPosition (int XPosition)
{
set_Value ("XPosition", Integer.valueOf(XPosition));
}
/** Get X Position.
@return Absolute X (horizontal) position in 1/72 of an inch */
public int getXPosition() 
{
Integer ii = (Integer)get_Value("XPosition");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name XPosition */
public static final String COLUMNNAME_XPosition = "XPosition";
/** Set Y Position.
@param YPosition Absolute Y (vertical) position in 1/72 of an inch */
public void setYPosition (int YPosition)
{
set_Value ("YPosition", Integer.valueOf(YPosition));
}
/** Get Y Position.
@return Absolute Y (vertical) position in 1/72 of an inch */
public int getYPosition() 
{
Integer ii = (Integer)get_Value("YPosition");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name YPosition */
public static final String COLUMNNAME_YPosition = "YPosition";
}
