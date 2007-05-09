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
/** Generated Model for C_UserRemuneration
 *  @author Adempiere (generated) 
 *  @version Release 3.2.0 - $Id$ */
public class X_C_UserRemuneration extends PO
{
/** Standard Constructor
@param ctx context
@param C_UserRemuneration_ID id
@param trxName transaction
*/
public X_C_UserRemuneration (Properties ctx, int C_UserRemuneration_ID, String trxName)
{
super (ctx, C_UserRemuneration_ID, trxName);
/** if (C_UserRemuneration_ID == 0)
{
setAD_User_ID (0);
setC_Remuneration_ID (0);
setC_UserRemuneration_ID (0);
setGrossRAmt (Env.ZERO);
setGrossRCost (Env.ZERO);
setOvertimeAmt (Env.ZERO);
setOvertimeCost (Env.ZERO);
setValidFrom (new Timestamp(System.currentTimeMillis()));
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_UserRemuneration (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=794 */
public static final int Table_ID=MTable.getTable_ID("C_UserRemuneration");

/** TableName=C_UserRemuneration */
public static final String Table_Name="C_UserRemuneration";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"C_UserRemuneration");

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
StringBuffer sb = new StringBuffer ("X_C_UserRemuneration[").append(get_ID()).append("]");
return sb.toString();
}
/** Set User/Contact.
@param AD_User_ID User within the system - Internal or Business Partner Contact */
public void setAD_User_ID (int AD_User_ID)
{
if (AD_User_ID < 1) throw new IllegalArgumentException ("AD_User_ID is mandatory.");
set_ValueNoCheck ("AD_User_ID", Integer.valueOf(AD_User_ID));
}
/** Get User/Contact.
@return User within the system - Internal or Business Partner Contact */
public int getAD_User_ID() 
{
Integer ii = (Integer)get_Value("AD_User_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getAD_User_ID()));
}
/** Column name AD_User_ID */
public static final String COLUMNNAME_AD_User_ID = "AD_User_ID";
/** Set Remuneration.
@param C_Remuneration_ID Wage or Salary */
public void setC_Remuneration_ID (int C_Remuneration_ID)
{
if (C_Remuneration_ID < 1) throw new IllegalArgumentException ("C_Remuneration_ID is mandatory.");
set_ValueNoCheck ("C_Remuneration_ID", Integer.valueOf(C_Remuneration_ID));
}
/** Get Remuneration.
@return Wage or Salary */
public int getC_Remuneration_ID() 
{
Integer ii = (Integer)get_Value("C_Remuneration_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_Remuneration_ID */
public static final String COLUMNNAME_C_Remuneration_ID = "C_Remuneration_ID";
/** Set Employee Remuneration.
@param C_UserRemuneration_ID Employee Wage or Salary Overwrite */
public void setC_UserRemuneration_ID (int C_UserRemuneration_ID)
{
if (C_UserRemuneration_ID < 1) throw new IllegalArgumentException ("C_UserRemuneration_ID is mandatory.");
set_ValueNoCheck ("C_UserRemuneration_ID", Integer.valueOf(C_UserRemuneration_ID));
}
/** Get Employee Remuneration.
@return Employee Wage or Salary Overwrite */
public int getC_UserRemuneration_ID() 
{
Integer ii = (Integer)get_Value("C_UserRemuneration_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_UserRemuneration_ID */
public static final String COLUMNNAME_C_UserRemuneration_ID = "C_UserRemuneration_ID";
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
/** Set Gross Amount.
@param GrossRAmt Gross Remuneration Amount */
public void setGrossRAmt (BigDecimal GrossRAmt)
{
if (GrossRAmt == null) throw new IllegalArgumentException ("GrossRAmt is mandatory.");
set_Value ("GrossRAmt", GrossRAmt);
}
/** Get Gross Amount.
@return Gross Remuneration Amount */
public BigDecimal getGrossRAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("GrossRAmt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name GrossRAmt */
public static final String COLUMNNAME_GrossRAmt = "GrossRAmt";
/** Set Gross Cost.
@param GrossRCost Gross Remuneration Costs */
public void setGrossRCost (BigDecimal GrossRCost)
{
if (GrossRCost == null) throw new IllegalArgumentException ("GrossRCost is mandatory.");
set_Value ("GrossRCost", GrossRCost);
}
/** Get Gross Cost.
@return Gross Remuneration Costs */
public BigDecimal getGrossRCost() 
{
BigDecimal bd = (BigDecimal)get_Value("GrossRCost");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name GrossRCost */
public static final String COLUMNNAME_GrossRCost = "GrossRCost";
/** Set Overtime Amount.
@param OvertimeAmt Hourly Overtime Rate */
public void setOvertimeAmt (BigDecimal OvertimeAmt)
{
if (OvertimeAmt == null) throw new IllegalArgumentException ("OvertimeAmt is mandatory.");
set_Value ("OvertimeAmt", OvertimeAmt);
}
/** Get Overtime Amount.
@return Hourly Overtime Rate */
public BigDecimal getOvertimeAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("OvertimeAmt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name OvertimeAmt */
public static final String COLUMNNAME_OvertimeAmt = "OvertimeAmt";
/** Set Overtime Cost.
@param OvertimeCost Hourly Overtime Cost */
public void setOvertimeCost (BigDecimal OvertimeCost)
{
if (OvertimeCost == null) throw new IllegalArgumentException ("OvertimeCost is mandatory.");
set_Value ("OvertimeCost", OvertimeCost);
}
/** Get Overtime Cost.
@return Hourly Overtime Cost */
public BigDecimal getOvertimeCost() 
{
BigDecimal bd = (BigDecimal)get_Value("OvertimeCost");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name OvertimeCost */
public static final String COLUMNNAME_OvertimeCost = "OvertimeCost";
/** Set Valid from.
@param ValidFrom Valid from including this date (first day) */
public void setValidFrom (Timestamp ValidFrom)
{
if (ValidFrom == null) throw new IllegalArgumentException ("ValidFrom is mandatory.");
set_Value ("ValidFrom", ValidFrom);
}
/** Get Valid from.
@return Valid from including this date (first day) */
public Timestamp getValidFrom() 
{
return (Timestamp)get_Value("ValidFrom");
}
/** Column name ValidFrom */
public static final String COLUMNNAME_ValidFrom = "ValidFrom";
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
/** Column name ValidTo */
public static final String COLUMNNAME_ValidTo = "ValidTo";
}
