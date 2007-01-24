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
/** Generated Model for PA_SLA_Goal
 *  @author Adempiere (generated) 
 *  @version Release 3.1.4 - $Id$ */
public class X_PA_SLA_Goal extends PO
{
/** Standard Constructor
@param ctx context
@param PA_SLA_Goal_ID id
@param trxName transaction
*/
public X_PA_SLA_Goal (Properties ctx, int PA_SLA_Goal_ID, String trxName)
{
super (ctx, PA_SLA_Goal_ID, trxName);
/** if (PA_SLA_Goal_ID == 0)
{
setC_BPartner_ID (0);
setMeasureActual (Env.ZERO);
setMeasureTarget (Env.ZERO);
setName (null);
setPA_SLA_Criteria_ID (0);
setPA_SLA_Goal_ID (0);
setProcessed (false);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_PA_SLA_Goal (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=745 */
public static final int Table_ID=MTable.getTable_ID("PA_SLA_Goal");

/** TableName=PA_SLA_Goal */
public static final String Table_Name="PA_SLA_Goal";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"PA_SLA_Goal");

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
StringBuffer sb = new StringBuffer ("X_PA_SLA_Goal[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Business Partner .
@param C_BPartner_ID Identifies a Business Partner */
public void setC_BPartner_ID (int C_BPartner_ID)
{
if (C_BPartner_ID < 1) throw new IllegalArgumentException ("C_BPartner_ID is mandatory.");
set_ValueNoCheck ("C_BPartner_ID", Integer.valueOf(C_BPartner_ID));
}
/** Get Business Partner .
@return Identifies a Business Partner */
public int getC_BPartner_ID() 
{
Integer ii = (Integer)get_Value("C_BPartner_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Date last run.
@param DateLastRun Date the process was last run. */
public void setDateLastRun (Timestamp DateLastRun)
{
set_Value ("DateLastRun", DateLastRun);
}
/** Get Date last run.
@return Date the process was last run. */
public Timestamp getDateLastRun() 
{
return (Timestamp)get_Value("DateLastRun");
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
/** Set Measure Actual.
@param MeasureActual Actual value that has been measured. */
public void setMeasureActual (BigDecimal MeasureActual)
{
if (MeasureActual == null) throw new IllegalArgumentException ("MeasureActual is mandatory.");
set_Value ("MeasureActual", MeasureActual);
}
/** Get Measure Actual.
@return Actual value that has been measured. */
public BigDecimal getMeasureActual() 
{
BigDecimal bd = (BigDecimal)get_Value("MeasureActual");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Measure Target.
@param MeasureTarget Target value for measure */
public void setMeasureTarget (BigDecimal MeasureTarget)
{
if (MeasureTarget == null) throw new IllegalArgumentException ("MeasureTarget is mandatory.");
set_Value ("MeasureTarget", MeasureTarget);
}
/** Get Measure Target.
@return Target value for measure */
public BigDecimal getMeasureTarget() 
{
BigDecimal bd = (BigDecimal)get_Value("MeasureTarget");
if (bd == null) return Env.ZERO;
return bd;
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
/** Set SLA Criteria.
@param PA_SLA_Criteria_ID Service Level Agreement Criteria */
public void setPA_SLA_Criteria_ID (int PA_SLA_Criteria_ID)
{
if (PA_SLA_Criteria_ID < 1) throw new IllegalArgumentException ("PA_SLA_Criteria_ID is mandatory.");
set_Value ("PA_SLA_Criteria_ID", Integer.valueOf(PA_SLA_Criteria_ID));
}
/** Get SLA Criteria.
@return Service Level Agreement Criteria */
public int getPA_SLA_Criteria_ID() 
{
Integer ii = (Integer)get_Value("PA_SLA_Criteria_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set SLA Goal.
@param PA_SLA_Goal_ID Service Level Agreement Goal */
public void setPA_SLA_Goal_ID (int PA_SLA_Goal_ID)
{
if (PA_SLA_Goal_ID < 1) throw new IllegalArgumentException ("PA_SLA_Goal_ID is mandatory.");
set_ValueNoCheck ("PA_SLA_Goal_ID", Integer.valueOf(PA_SLA_Goal_ID));
}
/** Get SLA Goal.
@return Service Level Agreement Goal */
public int getPA_SLA_Goal_ID() 
{
Integer ii = (Integer)get_Value("PA_SLA_Goal_ID");
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
/** Set Valid from.
@param ValidFrom Valid from including this date (first day) */
public void setValidFrom (Timestamp ValidFrom)
{
set_Value ("ValidFrom", ValidFrom);
}
/** Get Valid from.
@return Valid from including this date (first day) */
public Timestamp getValidFrom() 
{
return (Timestamp)get_Value("ValidFrom");
}
/** Set Valid to.
@param ValidTo Valid to including this date (last day) */
public void setValidTo (Timestamp ValidTo)
{
set_Value ("ValidTo", ValidTo);
}
/** Get Valid to.
@return Valid to including this date (last day) */
public Timestamp getValidTo() 
{
return (Timestamp)get_Value("ValidTo");
}
}
