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
/** Generated Model for GL_Category
 *  @author Jorg Janke (generated) 
 *  @version Release 2.5.3d - 2006-10-10 21:55:59.125 */
public class X_GL_Category extends PO
{
/** Standard Constructor
@param ctx context
@param GL_Category_ID id
@param trxName transaction
*/
public X_GL_Category (Properties ctx, int GL_Category_ID, String trxName)
{
super (ctx, GL_Category_ID, trxName);
/** if (GL_Category_ID == 0)
{
setCategoryType (null);	// M
setGL_Category_ID (0);
setIsDefault (false);
setName (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_GL_Category (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=218 */
public static final int Table_ID=218;

/** TableName=GL_Category */
public static final String Table_Name="GL_Category";

protected static KeyNamePair Model = new KeyNamePair(218,"GL_Category");

protected BigDecimal accessLevel = new BigDecimal(2);
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
StringBuffer sb = new StringBuffer ("X_GL_Category[").append(get_ID()).append("]");
return sb.toString();
}

/** CategoryType AD_Reference_ID=207 */
public static final int CATEGORYTYPE_AD_Reference_ID=207;
/** Document = D */
public static final String CATEGORYTYPE_Document = "D";
/** Import = I */
public static final String CATEGORYTYPE_Import = "I";
/** Manual = M */
public static final String CATEGORYTYPE_Manual = "M";
/** System generated = S */
public static final String CATEGORYTYPE_SystemGenerated = "S";
/** Set Category Type.
@param CategoryType Source of the Journal with this category */
public void setCategoryType (String CategoryType)
{
if (CategoryType == null) throw new IllegalArgumentException ("CategoryType is mandatory");
if (CategoryType.equals("D") || CategoryType.equals("I") || CategoryType.equals("M") || CategoryType.equals("S"));
 else throw new IllegalArgumentException ("CategoryType Invalid value - " + CategoryType + " - Reference_ID=207 - D - I - M - S");
if (CategoryType.length() > 1)
{
log.warning("Length > 1 - truncated");
CategoryType = CategoryType.substring(0,0);
}
set_Value ("CategoryType", CategoryType);
}
/** Get Category Type.
@return Source of the Journal with this category */
public String getCategoryType() 
{
return (String)get_Value("CategoryType");
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
/** Set GL Category.
@param GL_Category_ID General Ledger Category */
public void setGL_Category_ID (int GL_Category_ID)
{
if (GL_Category_ID < 1) throw new IllegalArgumentException ("GL_Category_ID is mandatory.");
set_ValueNoCheck ("GL_Category_ID", new Integer(GL_Category_ID));
}
/** Get GL Category.
@return General Ledger Category */
public int getGL_Category_ID() 
{
Integer ii = (Integer)get_Value("GL_Category_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Default.
@param IsDefault Default value */
public void setIsDefault (boolean IsDefault)
{
set_Value ("IsDefault", new Boolean(IsDefault));
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
}
