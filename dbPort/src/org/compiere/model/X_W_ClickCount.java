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
/** Generated Model for W_ClickCount
 *  @author Adempiere (generated) 
 *  @version Release 3.1.6 - $Id$ */
public class X_W_ClickCount extends PO
{
/** Standard Constructor
@param ctx context
@param W_ClickCount_ID id
@param trxName transaction
*/
public X_W_ClickCount (Properties ctx, int W_ClickCount_ID, String trxName)
{
super (ctx, W_ClickCount_ID, trxName);
/** if (W_ClickCount_ID == 0)
{
setName (null);
setTargetURL (null);
setW_ClickCount_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_W_ClickCount (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=553 */
public static final int Table_ID=MTable.getTable_ID("W_ClickCount");

/** TableName=W_ClickCount */
public static final String Table_Name="W_ClickCount";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"W_ClickCount");

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
StringBuffer sb = new StringBuffer ("X_W_ClickCount[").append(get_ID()).append("]");
return sb.toString();
}

/** C_BPartner_ID AD_Reference_ID=232 */
public static final int C_BPARTNER_ID_AD_Reference_ID=232;
/** Set Business Partner .
@param C_BPartner_ID Identifies a Business Partner */
public void setC_BPartner_ID (int C_BPartner_ID)
{
if (C_BPartner_ID <= 0) set_Value ("C_BPartner_ID", null);
 else 
set_Value ("C_BPartner_ID", Integer.valueOf(C_BPartner_ID));
}
/** Get Business Partner .
@return Identifies a Business Partner */
public int getC_BPartner_ID() 
{
Integer ii = (Integer)get_Value("C_BPartner_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_BPartner_ID */
public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";
/** Set Counter.
@param Counter Count Value */
public void setCounter (int Counter)
{
throw new IllegalArgumentException ("Counter is virtual column");
}
/** Get Counter.
@return Count Value */
public int getCounter() 
{
Integer ii = (Integer)get_Value("Counter");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name Counter */
public static final String COLUMNNAME_Counter = "Counter";
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
/** Set Target URL.
@param TargetURL URL for the Target */
public void setTargetURL (String TargetURL)
{
if (TargetURL == null) throw new IllegalArgumentException ("TargetURL is mandatory.");
if (TargetURL.length() > 120)
{
log.warning("Length > 120 - truncated");
TargetURL = TargetURL.substring(0,119);
}
set_Value ("TargetURL", TargetURL);
}
/** Get Target URL.
@return URL for the Target */
public String getTargetURL() 
{
return (String)get_Value("TargetURL");
}
/** Column name TargetURL */
public static final String COLUMNNAME_TargetURL = "TargetURL";
/** Set Click Count.
@param W_ClickCount_ID Web Click Management */
public void setW_ClickCount_ID (int W_ClickCount_ID)
{
if (W_ClickCount_ID < 1) throw new IllegalArgumentException ("W_ClickCount_ID is mandatory.");
set_ValueNoCheck ("W_ClickCount_ID", Integer.valueOf(W_ClickCount_ID));
}
/** Get Click Count.
@return Web Click Management */
public int getW_ClickCount_ID() 
{
Integer ii = (Integer)get_Value("W_ClickCount_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name W_ClickCount_ID */
public static final String COLUMNNAME_W_ClickCount_ID = "W_ClickCount_ID";
}
