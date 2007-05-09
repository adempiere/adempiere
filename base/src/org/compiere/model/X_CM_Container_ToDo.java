/******************************************************************************
 * Product: Compiere ERP & CRM Smart Business Solution                        *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.model;
import java.util.*;
import java.sql.*;
import java.math.*;
import org.compiere.util.*;
/** Generated Model for CM_Container_ToDo
 *  @author Jorg Janke (generated) 
 *  @version Release 2.5.3d - 2006-06-17 17:51:37.297 */
public class X_CM_Container_ToDo extends PO
{
/** Standard Constructor
@param ctx context
@param CM_Container_ToDo_ID id
@param trxName transaction
*/
public X_CM_Container_ToDo (Properties ctx, int CM_Container_ToDo_ID, String trxName)
{
super (ctx, CM_Container_ToDo_ID, trxName);
/** if (CM_Container_ToDo_ID == 0)
{
setAD_User_ID (0);
setCM_Container_ID (0);
setCM_Container_ToDo_ID (0);
setName (null);
setValue (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_CM_Container_ToDo (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=863 */
public static final int Table_ID=863;

/** TableName=CM_Container_ToDo */
public static final String Table_Name="CM_Container_ToDo";

protected static KeyNamePair Model = new KeyNamePair(863,"CM_Container_ToDo");

protected BigDecimal accessLevel = new BigDecimal(6);
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
StringBuffer sb = new StringBuffer ("X_CM_Container_ToDo[").append(get_ID()).append("]");
return sb.toString();
}
/** Set User/Contact.
@param AD_User_ID User within the system - Internal or Business Partner Contact */
public void setAD_User_ID (int AD_User_ID)
{
if (AD_User_ID < 1) throw new IllegalArgumentException ("AD_User_ID is mandatory.");
set_Value ("AD_User_ID", new Integer(AD_User_ID));
}
/** Get User/Contact.
@return User within the system - Internal or Business Partner Contact */
public int getAD_User_ID() 
{
Integer ii = (Integer)get_Value("AD_User_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Web Container.
@param CM_Container_ID Web Container contains content like images, text etc. */
public void setCM_Container_ID (int CM_Container_ID)
{
if (CM_Container_ID < 1) throw new IllegalArgumentException ("CM_Container_ID is mandatory.");
set_Value ("CM_Container_ID", new Integer(CM_Container_ID));
}
/** Get Web Container.
@return Web Container contains content like images, text etc. */
public int getCM_Container_ID() 
{
Integer ii = (Integer)get_Value("CM_Container_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Container ToDo.
@param CM_Container_ToDo_ID Contains internal workflow strcuture */
public void setCM_Container_ToDo_ID (int CM_Container_ToDo_ID)
{
if (CM_Container_ToDo_ID < 1) throw new IllegalArgumentException ("CM_Container_ToDo_ID is mandatory.");
set_ValueNoCheck ("CM_Container_ToDo_ID", new Integer(CM_Container_ToDo_ID));
}
/** Get Container ToDo.
@return Contains internal workflow strcuture */
public int getCM_Container_ToDo_ID() 
{
Integer ii = (Integer)get_Value("CM_Container_ToDo_ID");
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
/** Set HistoryInfo.
@param HistoryInfo Info on the history */
public void setHistoryInfo (String HistoryInfo)
{
if (HistoryInfo != null && HistoryInfo.length() > 2000)
{
log.warning("Length > 2000 - truncated");
HistoryInfo = HistoryInfo.substring(0,1999);
}
set_Value ("HistoryInfo", HistoryInfo);
}
/** Get HistoryInfo.
@return Info on the history */
public String getHistoryInfo() 
{
return (String)get_Value("HistoryInfo");
}
/** Set Name.
@param Name Alphanumeric identifier of the entity */
public void setName (String Name)
{
if (Name == null) throw new IllegalArgumentException ("Name is mandatory.");
if (Name.length() > 120)
{
log.warning("Length > 120 - truncated");
Name = Name.substring(0,119);
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

/** Next_User_ID AD_Reference_ID=110 */
public static final int NEXT_USER_ID_AD_Reference_ID=110;
/** Set Next User.
@param Next_User_ID Info who should handle this case next */
public void setNext_User_ID (int Next_User_ID)
{
if (Next_User_ID <= 0) set_Value ("Next_User_ID", null);
 else 
set_Value ("Next_User_ID", new Integer(Next_User_ID));
}
/** Get Next User.
@return Info who should handle this case next */
public int getNext_User_ID() 
{
Integer ii = (Integer)get_Value("Next_User_ID");
if (ii == null) return 0;
return ii.intValue();
}
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
}
