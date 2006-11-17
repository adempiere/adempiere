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
/** Generated Model for AD_InfoColumn
 *  @author Jorg Janke (generated) 
 *  @version Release 2.5.3d - 2006-10-10 21:55:52.984 */
public class X_AD_InfoColumn extends PO
{
/** Standard Constructor
@param ctx context
@param AD_InfoColumn_ID id
@param trxName transaction
*/
public X_AD_InfoColumn (Properties ctx, int AD_InfoColumn_ID, String trxName)
{
super (ctx, AD_InfoColumn_ID, trxName);
/** if (AD_InfoColumn_ID == 0)
{
setAD_InfoColumn_ID (0);
setAD_InfoWindow_ID (0);
setAD_Reference_ID (0);
setEntityType (null);	// U
setIsDisplayed (false);
setIsQueryCriteria (false);
setName (null);
setSelectClause (null);
setSeqNo (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_InfoColumn (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=897 */
public static final int Table_ID=897;

/** TableName=AD_InfoColumn */
public static final String Table_Name="AD_InfoColumn";

protected static KeyNamePair Model = new KeyNamePair(897,"AD_InfoColumn");

protected BigDecimal accessLevel = new BigDecimal(4);
/** AccessLevel
@return 4 - System 
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
StringBuffer sb = new StringBuffer ("X_AD_InfoColumn[").append(get_ID()).append("]");
return sb.toString();
}
/** Set System Element.
@param AD_Element_ID System Element enables the central maintenance of column description and help. */
public void setAD_Element_ID (int AD_Element_ID)
{
if (AD_Element_ID <= 0) set_Value ("AD_Element_ID", null);
 else 
set_Value ("AD_Element_ID", new Integer(AD_Element_ID));
}
/** Get System Element.
@return System Element enables the central maintenance of column description and help. */
public int getAD_Element_ID() 
{
Integer ii = (Integer)get_Value("AD_Element_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Info Column.
@param AD_InfoColumn_ID Info Window Column */
public void setAD_InfoColumn_ID (int AD_InfoColumn_ID)
{
if (AD_InfoColumn_ID < 1) throw new IllegalArgumentException ("AD_InfoColumn_ID is mandatory.");
set_ValueNoCheck ("AD_InfoColumn_ID", new Integer(AD_InfoColumn_ID));
}
/** Get Info Column.
@return Info Window Column */
public int getAD_InfoColumn_ID() 
{
Integer ii = (Integer)get_Value("AD_InfoColumn_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Info Window.
@param AD_InfoWindow_ID Info and search/select Window */
public void setAD_InfoWindow_ID (int AD_InfoWindow_ID)
{
if (AD_InfoWindow_ID < 1) throw new IllegalArgumentException ("AD_InfoWindow_ID is mandatory.");
set_ValueNoCheck ("AD_InfoWindow_ID", new Integer(AD_InfoWindow_ID));
}
/** Get Info Window.
@return Info and search/select Window */
public int getAD_InfoWindow_ID() 
{
Integer ii = (Integer)get_Value("AD_InfoWindow_ID");
if (ii == null) return 0;
return ii.intValue();
}

/** AD_Reference_ID AD_Reference_ID=1 */
public static final int AD_REFERENCE_ID_AD_Reference_ID=1;
/** Set Reference.
@param AD_Reference_ID System Reference and Validation */
public void setAD_Reference_ID (int AD_Reference_ID)
{
if (AD_Reference_ID < 1) throw new IllegalArgumentException ("AD_Reference_ID is mandatory.");
set_Value ("AD_Reference_ID", new Integer(AD_Reference_ID));
}
/** Get Reference.
@return System Reference and Validation */
public int getAD_Reference_ID() 
{
Integer ii = (Integer)get_Value("AD_Reference_ID");
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
/** Set Displayed.
@param IsDisplayed Determines, if this field is displayed */
public void setIsDisplayed (boolean IsDisplayed)
{
set_Value ("IsDisplayed", new Boolean(IsDisplayed));
}
/** Get Displayed.
@return Determines, if this field is displayed */
public boolean isDisplayed() 
{
Object oo = get_Value("IsDisplayed");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Query Criteria.
@param IsQueryCriteria The column is also used as a query criteria */
public void setIsQueryCriteria (boolean IsQueryCriteria)
{
set_Value ("IsQueryCriteria", new Boolean(IsQueryCriteria));
}
/** Get Query Criteria.
@return The column is also used as a query criteria */
public boolean isQueryCriteria() 
{
Object oo = get_Value("IsQueryCriteria");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
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
/** Set Sql SELECT.
@param SelectClause SQL SELECT clause */
public void setSelectClause (String SelectClause)
{
if (SelectClause == null) throw new IllegalArgumentException ("SelectClause is mandatory.");
if (SelectClause.length() > 255)
{
log.warning("Length > 255 - truncated");
SelectClause = SelectClause.substring(0,254);
}
set_Value ("SelectClause", SelectClause);
}
/** Get Sql SELECT.
@return SQL SELECT clause */
public String getSelectClause() 
{
return (String)get_Value("SelectClause");
}
/** Set Sequence.
@param SeqNo Method of ordering records;
 lowest number comes first */
public void setSeqNo (int SeqNo)
{
set_Value ("SeqNo", new Integer(SeqNo));
}
/** Get Sequence.
@return Method of ordering records;
 lowest number comes first */
public int getSeqNo() 
{
Integer ii = (Integer)get_Value("SeqNo");
if (ii == null) return 0;
return ii.intValue();
}
}
