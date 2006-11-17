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
/** Generated Model for AD_UserDef_Field
 *  @author Jorg Janke (generated) 
 *  @version Release 2.5.3d - 2006-10-10 21:55:54.484 */
public class X_AD_UserDef_Field extends PO
{
/** Standard Constructor
@param ctx context
@param AD_UserDef_Field_ID id
@param trxName transaction
*/
public X_AD_UserDef_Field (Properties ctx, int AD_UserDef_Field_ID, String trxName)
{
super (ctx, AD_UserDef_Field_ID, trxName);
/** if (AD_UserDef_Field_ID == 0)
{
setAD_Field_ID (0);
setAD_UserDef_Field_ID (0);
setAD_UserDef_Tab_ID (0);
setDefaultValue (null);
setIsDisplayed (false);
setIsReadOnly (false);
setIsSameLine (false);
setName (null);
setSeqNo (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_UserDef_Field (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=464 */
public static final int Table_ID=464;

/** TableName=AD_UserDef_Field */
public static final String Table_Name="AD_UserDef_Field";

protected static KeyNamePair Model = new KeyNamePair(464,"AD_UserDef_Field");

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
StringBuffer sb = new StringBuffer ("X_AD_UserDef_Field[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Field.
@param AD_Field_ID Field on a database table */
public void setAD_Field_ID (int AD_Field_ID)
{
if (AD_Field_ID < 1) throw new IllegalArgumentException ("AD_Field_ID is mandatory.");
set_Value ("AD_Field_ID", new Integer(AD_Field_ID));
}
/** Get Field.
@return Field on a database table */
public int getAD_Field_ID() 
{
Integer ii = (Integer)get_Value("AD_Field_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set User defined Field.
@param AD_UserDef_Field_ID User defined Field */
public void setAD_UserDef_Field_ID (int AD_UserDef_Field_ID)
{
if (AD_UserDef_Field_ID < 1) throw new IllegalArgumentException ("AD_UserDef_Field_ID is mandatory.");
set_ValueNoCheck ("AD_UserDef_Field_ID", new Integer(AD_UserDef_Field_ID));
}
/** Get User defined Field.
@return User defined Field */
public int getAD_UserDef_Field_ID() 
{
Integer ii = (Integer)get_Value("AD_UserDef_Field_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set User defined Tab.
@param AD_UserDef_Tab_ID User defined Tab */
public void setAD_UserDef_Tab_ID (int AD_UserDef_Tab_ID)
{
if (AD_UserDef_Tab_ID < 1) throw new IllegalArgumentException ("AD_UserDef_Tab_ID is mandatory.");
set_ValueNoCheck ("AD_UserDef_Tab_ID", new Integer(AD_UserDef_Tab_ID));
}
/** Get User defined Tab.
@return User defined Tab */
public int getAD_UserDef_Tab_ID() 
{
Integer ii = (Integer)get_Value("AD_UserDef_Tab_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Default Logic.
@param DefaultValue Default value hierarchy, separated by ;
 */
public void setDefaultValue (String DefaultValue)
{
if (DefaultValue == null) throw new IllegalArgumentException ("DefaultValue is mandatory.");
if (DefaultValue.length() > 2000)
{
log.warning("Length > 2000 - truncated");
DefaultValue = DefaultValue.substring(0,1999);
}
set_Value ("DefaultValue", DefaultValue);
}
/** Get Default Logic.
@return Default value hierarchy, separated by ;
 */
public String getDefaultValue() 
{
return (String)get_Value("DefaultValue");
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
/** Set Display Length.
@param DisplayLength Length of the display in characters */
public void setDisplayLength (int DisplayLength)
{
set_Value ("DisplayLength", new Integer(DisplayLength));
}
/** Get Display Length.
@return Length of the display in characters */
public int getDisplayLength() 
{
Integer ii = (Integer)get_Value("DisplayLength");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Display Logic.
@param DisplayLogic If the Field is displayed, the result determines if the field is actually displayed */
public void setDisplayLogic (String DisplayLogic)
{
if (DisplayLogic != null && DisplayLogic.length() > 2000)
{
log.warning("Length > 2000 - truncated");
DisplayLogic = DisplayLogic.substring(0,1999);
}
set_Value ("DisplayLogic", DisplayLogic);
}
/** Get Display Logic.
@return If the Field is displayed, the result determines if the field is actually displayed */
public String getDisplayLogic() 
{
return (String)get_Value("DisplayLogic");
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
/** Set Read Only.
@param IsReadOnly Field is read only */
public void setIsReadOnly (boolean IsReadOnly)
{
set_Value ("IsReadOnly", new Boolean(IsReadOnly));
}
/** Get Read Only.
@return Field is read only */
public boolean isReadOnly() 
{
Object oo = get_Value("IsReadOnly");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Same Line.
@param IsSameLine Displayed on same line as previous field */
public void setIsSameLine (boolean IsSameLine)
{
set_Value ("IsSameLine", new Boolean(IsSameLine));
}
/** Get Same Line.
@return Displayed on same line as previous field */
public boolean isSameLine() 
{
Object oo = get_Value("IsSameLine");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Updateable.
@param IsUpdateable Determines, if the field can be updated */
public void setIsUpdateable (boolean IsUpdateable)
{
set_Value ("IsUpdateable", new Boolean(IsUpdateable));
}
/** Get Updateable.
@return Determines, if the field can be updated */
public boolean isUpdateable() 
{
Object oo = get_Value("IsUpdateable");
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
/** Set Record Sort No.
@param SortNo Determines in what order the records are displayed */
public void setSortNo (int SortNo)
{
set_Value ("SortNo", new Integer(SortNo));
}
/** Get Record Sort No.
@return Determines in what order the records are displayed */
public int getSortNo() 
{
Integer ii = (Integer)get_Value("SortNo");
if (ii == null) return 0;
return ii.intValue();
}
}
