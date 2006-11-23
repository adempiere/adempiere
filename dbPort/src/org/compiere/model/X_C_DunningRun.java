/******************************************************************************
 * Product: Compiere ERP & CRM Smart Business Solution                        *
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
/** Generated Model for C_DunningRun
 *  @author Jorg Janke (generated) 
 *  @version Release 2.6.0a - $Id$ */
public class X_C_DunningRun extends PO
{
/** Standard Constructor
@param ctx context
@param C_DunningRun_ID id
@param trxName transaction
*/
public X_C_DunningRun (Properties ctx, int C_DunningRun_ID, String trxName)
{
super (ctx, C_DunningRun_ID, trxName);
/** if (C_DunningRun_ID == 0)
{
setC_DunningLevel_ID (0);
setC_DunningRun_ID (0);
setDunningDate (new Timestamp(System.currentTimeMillis()));	// @#Date@
setProcessed (false);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_DunningRun (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=526 */
public static final int Table_ID=526;

/** TableName=C_DunningRun */
public static final String Table_Name="C_DunningRun";

protected static KeyNamePair Model = new KeyNamePair(526,"C_DunningRun");

protected BigDecimal accessLevel = new BigDecimal(3);
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
StringBuffer sb = new StringBuffer ("X_C_DunningRun[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Dunning Level.
@param C_DunningLevel_ID Dunning Level */
public void setC_DunningLevel_ID (int C_DunningLevel_ID)
{
if (C_DunningLevel_ID < 1) throw new IllegalArgumentException ("C_DunningLevel_ID is mandatory.");
set_ValueNoCheck ("C_DunningLevel_ID", new Integer(C_DunningLevel_ID));
}
/** Get Dunning Level.
@return Dunning Level */
public int getC_DunningLevel_ID() 
{
Integer ii = (Integer)get_Value("C_DunningLevel_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Dunning Run.
@param C_DunningRun_ID Dunning Run */
public void setC_DunningRun_ID (int C_DunningRun_ID)
{
if (C_DunningRun_ID < 1) throw new IllegalArgumentException ("C_DunningRun_ID is mandatory.");
set_ValueNoCheck ("C_DunningRun_ID", new Integer(C_DunningRun_ID));
}
/** Get Dunning Run.
@return Dunning Run */
public int getC_DunningRun_ID() 
{
Integer ii = (Integer)get_Value("C_DunningRun_ID");
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
/** Set Dunning Date.
@param DunningDate Date of Dunning */
public void setDunningDate (Timestamp DunningDate)
{
if (DunningDate == null) throw new IllegalArgumentException ("DunningDate is mandatory.");
set_Value ("DunningDate", DunningDate);
}
/** Get Dunning Date.
@return Date of Dunning */
public Timestamp getDunningDate() 
{
return (Timestamp)get_Value("DunningDate");
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getDunningDate()));
}
/** Set Processed.
@param Processed The document has been processed */
public void setProcessed (boolean Processed)
{
set_Value ("Processed", new Boolean(Processed));
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
set_Value ("Processing", new Boolean(Processing));
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
/** Set Send.
@param SendIt Send */
public void setSendIt (String SendIt)
{
if (SendIt != null && SendIt.length() > 1)
{
log.warning("Length > 1 - truncated");
SendIt = SendIt.substring(0,0);
}
set_Value ("SendIt", SendIt);
}
/** Get Send.
@return Send */
public String getSendIt() 
{
return (String)get_Value("SendIt");
}
}
