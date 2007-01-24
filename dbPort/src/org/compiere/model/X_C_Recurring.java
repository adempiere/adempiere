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
/** Generated Model for C_Recurring
 *  @author Adempiere (generated) 
 *  @version Release 3.1.4 - $Id$ */
public class X_C_Recurring extends PO
{
/** Standard Constructor
@param ctx context
@param C_Recurring_ID id
@param trxName transaction
*/
public X_C_Recurring (Properties ctx, int C_Recurring_ID, String trxName)
{
super (ctx, C_Recurring_ID, trxName);
/** if (C_Recurring_ID == 0)
{
setC_Recurring_ID (0);
setDateNextRun (new Timestamp(System.currentTimeMillis()));
setFrequencyType (null);	// M
setName (null);
setRecurringType (null);
setRunsMax (0);
setRunsRemaining (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_Recurring (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=574 */
public static final int Table_ID=MTable.getTable_ID("C_Recurring");

/** TableName=C_Recurring */
public static final String Table_Name="C_Recurring";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"C_Recurring");

protected BigDecimal accessLevel = BigDecimal.valueOf(3);
/** AccessLevel
@return 3 - Client - Org 
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
StringBuffer sb = new StringBuffer ("X_C_Recurring[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Invoice.
@param C_Invoice_ID Invoice Identifier */
public void setC_Invoice_ID (int C_Invoice_ID)
{
if (C_Invoice_ID <= 0) set_Value ("C_Invoice_ID", null);
 else 
set_Value ("C_Invoice_ID", Integer.valueOf(C_Invoice_ID));
}
/** Get Invoice.
@return Invoice Identifier */
public int getC_Invoice_ID() 
{
Integer ii = (Integer)get_Value("C_Invoice_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Order.
@param C_Order_ID Order */
public void setC_Order_ID (int C_Order_ID)
{
if (C_Order_ID <= 0) set_Value ("C_Order_ID", null);
 else 
set_Value ("C_Order_ID", Integer.valueOf(C_Order_ID));
}
/** Get Order.
@return Order */
public int getC_Order_ID() 
{
Integer ii = (Integer)get_Value("C_Order_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Payment.
@param C_Payment_ID Payment identifier */
public void setC_Payment_ID (int C_Payment_ID)
{
if (C_Payment_ID <= 0) set_Value ("C_Payment_ID", null);
 else 
set_Value ("C_Payment_ID", Integer.valueOf(C_Payment_ID));
}
/** Get Payment.
@return Payment identifier */
public int getC_Payment_ID() 
{
Integer ii = (Integer)get_Value("C_Payment_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Project.
@param C_Project_ID Financial Project */
public void setC_Project_ID (int C_Project_ID)
{
if (C_Project_ID <= 0) set_Value ("C_Project_ID", null);
 else 
set_Value ("C_Project_ID", Integer.valueOf(C_Project_ID));
}
/** Get Project.
@return Financial Project */
public int getC_Project_ID() 
{
Integer ii = (Integer)get_Value("C_Project_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Recurring.
@param C_Recurring_ID Recurring Document */
public void setC_Recurring_ID (int C_Recurring_ID)
{
if (C_Recurring_ID < 1) throw new IllegalArgumentException ("C_Recurring_ID is mandatory.");
set_ValueNoCheck ("C_Recurring_ID", Integer.valueOf(C_Recurring_ID));
}
/** Get Recurring.
@return Recurring Document */
public int getC_Recurring_ID() 
{
Integer ii = (Integer)get_Value("C_Recurring_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Date last run.
@param DateLastRun Date the process was last run. */
public void setDateLastRun (Timestamp DateLastRun)
{
set_ValueNoCheck ("DateLastRun", DateLastRun);
}
/** Get Date last run.
@return Date the process was last run. */
public Timestamp getDateLastRun() 
{
return (Timestamp)get_Value("DateLastRun");
}
/** Set Date next run.
@param DateNextRun Date the process will run next */
public void setDateNextRun (Timestamp DateNextRun)
{
if (DateNextRun == null) throw new IllegalArgumentException ("DateNextRun is mandatory.");
set_Value ("DateNextRun", DateNextRun);
}
/** Get Date next run.
@return Date the process will run next */
public Timestamp getDateNextRun() 
{
return (Timestamp)get_Value("DateNextRun");
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
/** Set Frequency.
@param Frequency Frequency of events */
public void setFrequency (int Frequency)
{
set_Value ("Frequency", Integer.valueOf(Frequency));
}
/** Get Frequency.
@return Frequency of events */
public int getFrequency() 
{
Integer ii = (Integer)get_Value("Frequency");
if (ii == null) return 0;
return ii.intValue();
}

/** FrequencyType AD_Reference_ID=283 */
public static final int FREQUENCYTYPE_AD_Reference_ID=283;
/** Daily = D */
public static final String FREQUENCYTYPE_Daily = "D";
/** Monthly = M */
public static final String FREQUENCYTYPE_Monthly = "M";
/** Quarterly = Q */
public static final String FREQUENCYTYPE_Quarterly = "Q";
/** Weekly = W */
public static final String FREQUENCYTYPE_Weekly = "W";
/** Set Frequency Type.
@param FrequencyType Frequency of event */
public void setFrequencyType (String FrequencyType)
{
if (FrequencyType == null) throw new IllegalArgumentException ("FrequencyType is mandatory");
if (FrequencyType.equals("D") || FrequencyType.equals("M") || FrequencyType.equals("Q") || FrequencyType.equals("W"));
 else throw new IllegalArgumentException ("FrequencyType Invalid value - " + FrequencyType + " - Reference_ID=283 - D - M - Q - W");
if (FrequencyType.length() > 1)
{
log.warning("Length > 1 - truncated");
FrequencyType = FrequencyType.substring(0,0);
}
set_Value ("FrequencyType", FrequencyType);
}
/** Get Frequency Type.
@return Frequency of event */
public String getFrequencyType() 
{
return (String)get_Value("FrequencyType");
}
/** Set Journal Batch.
@param GL_JournalBatch_ID General Ledger Journal Batch */
public void setGL_JournalBatch_ID (int GL_JournalBatch_ID)
{
if (GL_JournalBatch_ID <= 0) set_Value ("GL_JournalBatch_ID", null);
 else 
set_Value ("GL_JournalBatch_ID", Integer.valueOf(GL_JournalBatch_ID));
}
/** Get Journal Batch.
@return General Ledger Journal Batch */
public int getGL_JournalBatch_ID() 
{
Integer ii = (Integer)get_Value("GL_JournalBatch_ID");
if (ii == null) return 0;
return ii.intValue();
}
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

/** RecurringType AD_Reference_ID=282 */
public static final int RECURRINGTYPE_AD_Reference_ID=282;
/** GL Journal = G */
public static final String RECURRINGTYPE_GLJournal = "G";
/** Invoice = I */
public static final String RECURRINGTYPE_Invoice = "I";
/** Project = J */
public static final String RECURRINGTYPE_Project = "J";
/** Order = O */
public static final String RECURRINGTYPE_Order = "O";
/** Set Recurring Type.
@param RecurringType Type of Recurring Document */
public void setRecurringType (String RecurringType)
{
if (RecurringType == null) throw new IllegalArgumentException ("RecurringType is mandatory");
if (RecurringType.equals("G") || RecurringType.equals("I") || RecurringType.equals("J") || RecurringType.equals("O"));
 else throw new IllegalArgumentException ("RecurringType Invalid value - " + RecurringType + " - Reference_ID=282 - G - I - J - O");
if (RecurringType.length() > 1)
{
log.warning("Length > 1 - truncated");
RecurringType = RecurringType.substring(0,0);
}
set_Value ("RecurringType", RecurringType);
}
/** Get Recurring Type.
@return Type of Recurring Document */
public String getRecurringType() 
{
return (String)get_Value("RecurringType");
}
/** Set Maximum Runs.
@param RunsMax Number of recurring runs */
public void setRunsMax (int RunsMax)
{
set_Value ("RunsMax", Integer.valueOf(RunsMax));
}
/** Get Maximum Runs.
@return Number of recurring runs */
public int getRunsMax() 
{
Integer ii = (Integer)get_Value("RunsMax");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Remaining Runs.
@param RunsRemaining Number of recurring runs remaining */
public void setRunsRemaining (int RunsRemaining)
{
set_ValueNoCheck ("RunsRemaining", Integer.valueOf(RunsRemaining));
}
/** Get Remaining Runs.
@return Number of recurring runs remaining */
public int getRunsRemaining() 
{
Integer ii = (Integer)get_Value("RunsRemaining");
if (ii == null) return 0;
return ii.intValue();
}
}
