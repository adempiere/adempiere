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
/** Generated Model for AD_Desktop
 *  @author Adempiere (generated) 
 *  @version Release 3.3.0 - $Id$ */
public class X_AD_Desktop extends PO
{
/** Standard Constructor
@param ctx context
@param AD_Desktop_ID id
@param trxName transaction
*/
public X_AD_Desktop (Properties ctx, int AD_Desktop_ID, String trxName)
{
super (ctx, AD_Desktop_ID, trxName);
/** if (AD_Desktop_ID == 0)
{
setAD_Desktop_ID (0);
setName (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_Desktop (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** TableName=AD_Desktop */
public static final String Table_Name="AD_Desktop";

/** AD_Table_ID=458 */
public static final int Table_ID=MTable.getTable_ID(Table_Name);

protected static KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

protected BigDecimal accessLevel = BigDecimal.valueOf(4);
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
StringBuffer sb = new StringBuffer ("X_AD_Desktop[").append(get_ID()).append("]");
return sb.toString();
}
/** Set System Color.
@param AD_Color_ID Color for backgrounds or indicators */
public void setAD_Color_ID (int AD_Color_ID)
{
if (AD_Color_ID <= 0) set_Value ("AD_Color_ID", null);
 else 
set_Value ("AD_Color_ID", Integer.valueOf(AD_Color_ID));
}
/** Get System Color.
@return Color for backgrounds or indicators */
public int getAD_Color_ID() 
{
Integer ii = (Integer)get_Value("AD_Color_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_Color_ID */
public static final String COLUMNNAME_AD_Color_ID = "AD_Color_ID";
/** Set Desktop.
@param AD_Desktop_ID Collection of Workbenches */
public void setAD_Desktop_ID (int AD_Desktop_ID)
{
if (AD_Desktop_ID < 1) throw new IllegalArgumentException ("AD_Desktop_ID is mandatory.");
set_ValueNoCheck ("AD_Desktop_ID", Integer.valueOf(AD_Desktop_ID));
}
/** Get Desktop.
@return Collection of Workbenches */
public int getAD_Desktop_ID() 
{
Integer ii = (Integer)get_Value("AD_Desktop_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_Desktop_ID */
public static final String COLUMNNAME_AD_Desktop_ID = "AD_Desktop_ID";
/** Set Image.
@param AD_Image_ID Image or Icon */
public void setAD_Image_ID (int AD_Image_ID)
{
if (AD_Image_ID <= 0) set_Value ("AD_Image_ID", null);
 else 
set_Value ("AD_Image_ID", Integer.valueOf(AD_Image_ID));
}
/** Get Image.
@return Image or Icon */
public int getAD_Image_ID() 
{
Integer ii = (Integer)get_Value("AD_Image_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_Image_ID */
public static final String COLUMNNAME_AD_Image_ID = "AD_Image_ID";
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
}
