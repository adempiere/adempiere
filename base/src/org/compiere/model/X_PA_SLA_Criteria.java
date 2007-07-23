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
/** Generated Model for PA_SLA_Criteria
 *  @author Adempiere (generated) 
 *  @version Release 3.3.0 - $Id$ */
public class X_PA_SLA_Criteria extends PO
{
/** Standard Constructor
@param ctx context
@param PA_SLA_Criteria_ID id
@param trxName transaction
*/
public X_PA_SLA_Criteria (Properties ctx, int PA_SLA_Criteria_ID, String trxName)
{
super (ctx, PA_SLA_Criteria_ID, trxName);
/** if (PA_SLA_Criteria_ID == 0)
{
setIsManual (true);	// Y
setName (null);
setPA_SLA_Criteria_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_PA_SLA_Criteria (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** TableName=PA_SLA_Criteria */
public static final String Table_Name="PA_SLA_Criteria";

/** AD_Table_ID=744 */
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
StringBuffer sb = new StringBuffer ("X_PA_SLA_Criteria[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Classname.
@param Classname Java Classname */
public void setClassname (String Classname)
{
if (Classname != null && Classname.length() > 60)
{
log.warning("Length > 60 - truncated");
Classname = Classname.substring(0,59);
}
set_Value ("Classname", Classname);
}
/** Get Classname.
@return Java Classname */
public String getClassname() 
{
return (String)get_Value("Classname");
}
/** Column name Classname */
public static final String COLUMNNAME_Classname = "Classname";
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
/** Set Manual.
@param IsManual This is a manual process */
public void setIsManual (boolean IsManual)
{
set_Value ("IsManual", Boolean.valueOf(IsManual));
}
/** Get Manual.
@return This is a manual process */
public boolean isManual() 
{
Object oo = get_Value("IsManual");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsManual */
public static final String COLUMNNAME_IsManual = "IsManual";
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
/** Set SLA Criteria.
@param PA_SLA_Criteria_ID Service Level Agreement Criteria */
public void setPA_SLA_Criteria_ID (int PA_SLA_Criteria_ID)
{
if (PA_SLA_Criteria_ID < 1) throw new IllegalArgumentException ("PA_SLA_Criteria_ID is mandatory.");
set_ValueNoCheck ("PA_SLA_Criteria_ID", Integer.valueOf(PA_SLA_Criteria_ID));
}
/** Get SLA Criteria.
@return Service Level Agreement Criteria */
public int getPA_SLA_Criteria_ID() 
{
Integer ii = (Integer)get_Value("PA_SLA_Criteria_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name PA_SLA_Criteria_ID */
public static final String COLUMNNAME_PA_SLA_Criteria_ID = "PA_SLA_Criteria_ID";
}
