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
/** Generated Model for M_Forecast
 *  @author Adempiere (generated) 
 *  @version Release 3.1.4 - $Id$ */
public class X_M_Forecast extends PO
{
/** Standard Constructor
@param ctx context
@param M_Forecast_ID id
@param trxName transaction
*/
public X_M_Forecast (Properties ctx, int M_Forecast_ID, String trxName)
{
super (ctx, M_Forecast_ID, trxName);
/** if (M_Forecast_ID == 0)
{
setC_Calendar_ID (0);
setC_Year_ID (0);
setIsDefault (false);
setM_Forecast_ID (0);
setName (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_M_Forecast (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=720 */
public static final int Table_ID=MTable.getTable_ID("M_Forecast");

/** TableName=M_Forecast */
public static final String Table_Name="M_Forecast";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"M_Forecast");

protected BigDecimal accessLevel = BigDecimal.valueOf(2);
/** AccessLevel
@return 2 - Client 
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
StringBuffer sb = new StringBuffer ("X_M_Forecast[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Calendar.
@param C_Calendar_ID Accounting Calendar Name */
public void setC_Calendar_ID (int C_Calendar_ID)
{
if (C_Calendar_ID < 1) throw new IllegalArgumentException ("C_Calendar_ID is mandatory.");
set_ValueNoCheck ("C_Calendar_ID", Integer.valueOf(C_Calendar_ID));
}
/** Get Calendar.
@return Accounting Calendar Name */
public int getC_Calendar_ID() 
{
Integer ii = (Integer)get_Value("C_Calendar_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Year.
@param C_Year_ID Calendar Year */
public void setC_Year_ID (int C_Year_ID)
{
if (C_Year_ID < 1) throw new IllegalArgumentException ("C_Year_ID is mandatory.");
set_ValueNoCheck ("C_Year_ID", Integer.valueOf(C_Year_ID));
}
/** Get Year.
@return Calendar Year */
public int getC_Year_ID() 
{
Integer ii = (Integer)get_Value("C_Year_ID");
if (ii == null) return 0;
return ii.intValue();
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
/** Set Default.
@param IsDefault Default value */
public void setIsDefault (boolean IsDefault)
{
set_Value ("IsDefault", Boolean.valueOf(IsDefault));
}
/** Get Default.
@return Default value */
public boolean isDefault() 
{
Object oo = get_Value("IsDefault");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Forecast.
@param M_Forecast_ID Material Forecast */
public void setM_Forecast_ID (int M_Forecast_ID)
{
if (M_Forecast_ID < 1) throw new IllegalArgumentException ("M_Forecast_ID is mandatory.");
set_ValueNoCheck ("M_Forecast_ID", Integer.valueOf(M_Forecast_ID));
}
/** Get Forecast.
@return Material Forecast */
public int getM_Forecast_ID() 
{
Integer ii = (Integer)get_Value("M_Forecast_ID");
if (ii == null) return 0;
return ii.intValue();
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
}
