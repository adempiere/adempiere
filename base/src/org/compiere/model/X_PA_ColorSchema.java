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
/** Generated Model for PA_ColorSchema
 *  @author Adempiere (generated) 
 *  @version Release 3.3.0 - $Id$ */
public class X_PA_ColorSchema extends PO
{
/** Standard Constructor
@param ctx context
@param PA_ColorSchema_ID id
@param trxName transaction
*/
public X_PA_ColorSchema (Properties ctx, int PA_ColorSchema_ID, String trxName)
{
super (ctx, PA_ColorSchema_ID, trxName);
/** if (PA_ColorSchema_ID == 0)
{
setAD_PrintColor1_ID (0);
setAD_PrintColor2_ID (0);
setEntityType (null);	// U
setMark1Percent (0);
setMark2Percent (0);
setName (null);
setPA_ColorSchema_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_PA_ColorSchema (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** TableName=PA_ColorSchema */
public static final String Table_Name="PA_ColorSchema";

/** AD_Table_ID=831 */
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
StringBuffer sb = new StringBuffer ("X_PA_ColorSchema[").append(get_ID()).append("]");
return sb.toString();
}

/** AD_PrintColor1_ID AD_Reference_ID=266 */
public static final int AD_PRINTCOLOR1_ID_AD_Reference_ID=266;
/** Set Color 1.
@param AD_PrintColor1_ID First color used */
public void setAD_PrintColor1_ID (int AD_PrintColor1_ID)
{
if (AD_PrintColor1_ID < 1) throw new IllegalArgumentException ("AD_PrintColor1_ID is mandatory.");
set_Value ("AD_PrintColor1_ID", Integer.valueOf(AD_PrintColor1_ID));
}
/** Get Color 1.
@return First color used */
public int getAD_PrintColor1_ID() 
{
Integer ii = (Integer)get_Value("AD_PrintColor1_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_PrintColor1_ID */
public static final String COLUMNNAME_AD_PrintColor1_ID = "AD_PrintColor1_ID";

/** AD_PrintColor2_ID AD_Reference_ID=266 */
public static final int AD_PRINTCOLOR2_ID_AD_Reference_ID=266;
/** Set Color 2.
@param AD_PrintColor2_ID Second color used */
public void setAD_PrintColor2_ID (int AD_PrintColor2_ID)
{
if (AD_PrintColor2_ID < 1) throw new IllegalArgumentException ("AD_PrintColor2_ID is mandatory.");
set_Value ("AD_PrintColor2_ID", Integer.valueOf(AD_PrintColor2_ID));
}
/** Get Color 2.
@return Second color used */
public int getAD_PrintColor2_ID() 
{
Integer ii = (Integer)get_Value("AD_PrintColor2_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_PrintColor2_ID */
public static final String COLUMNNAME_AD_PrintColor2_ID = "AD_PrintColor2_ID";

/** AD_PrintColor3_ID AD_Reference_ID=266 */
public static final int AD_PRINTCOLOR3_ID_AD_Reference_ID=266;
/** Set Color 3.
@param AD_PrintColor3_ID Third color used */
public void setAD_PrintColor3_ID (int AD_PrintColor3_ID)
{
if (AD_PrintColor3_ID <= 0) set_Value ("AD_PrintColor3_ID", null);
 else 
set_Value ("AD_PrintColor3_ID", Integer.valueOf(AD_PrintColor3_ID));
}
/** Get Color 3.
@return Third color used */
public int getAD_PrintColor3_ID() 
{
Integer ii = (Integer)get_Value("AD_PrintColor3_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_PrintColor3_ID */
public static final String COLUMNNAME_AD_PrintColor3_ID = "AD_PrintColor3_ID";

/** AD_PrintColor4_ID AD_Reference_ID=266 */
public static final int AD_PRINTCOLOR4_ID_AD_Reference_ID=266;
/** Set Color 4.
@param AD_PrintColor4_ID Forth color used */
public void setAD_PrintColor4_ID (int AD_PrintColor4_ID)
{
if (AD_PrintColor4_ID <= 0) set_Value ("AD_PrintColor4_ID", null);
 else 
set_Value ("AD_PrintColor4_ID", Integer.valueOf(AD_PrintColor4_ID));
}
/** Get Color 4.
@return Forth color used */
public int getAD_PrintColor4_ID() 
{
Integer ii = (Integer)get_Value("AD_PrintColor4_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_PrintColor4_ID */
public static final String COLUMNNAME_AD_PrintColor4_ID = "AD_PrintColor4_ID";
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
/** Column name EntityType */
public static final String COLUMNNAME_EntityType = "EntityType";
/** Set Mark 1 Percent.
@param Mark1Percent Percentage up to this color is used */
public void setMark1Percent (int Mark1Percent)
{
set_Value ("Mark1Percent", Integer.valueOf(Mark1Percent));
}
/** Get Mark 1 Percent.
@return Percentage up to this color is used */
public int getMark1Percent() 
{
Integer ii = (Integer)get_Value("Mark1Percent");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name Mark1Percent */
public static final String COLUMNNAME_Mark1Percent = "Mark1Percent";
/** Set Mark 2 Percent.
@param Mark2Percent Percentage up to this color is used */
public void setMark2Percent (int Mark2Percent)
{
set_Value ("Mark2Percent", Integer.valueOf(Mark2Percent));
}
/** Get Mark 2 Percent.
@return Percentage up to this color is used */
public int getMark2Percent() 
{
Integer ii = (Integer)get_Value("Mark2Percent");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name Mark2Percent */
public static final String COLUMNNAME_Mark2Percent = "Mark2Percent";
/** Set Mark 3 Percent.
@param Mark3Percent Percentage up to this color is used */
public void setMark3Percent (int Mark3Percent)
{
set_Value ("Mark3Percent", Integer.valueOf(Mark3Percent));
}
/** Get Mark 3 Percent.
@return Percentage up to this color is used */
public int getMark3Percent() 
{
Integer ii = (Integer)get_Value("Mark3Percent");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name Mark3Percent */
public static final String COLUMNNAME_Mark3Percent = "Mark3Percent";
/** Set Mark 4 Percent.
@param Mark4Percent Percentage up to this color is used */
public void setMark4Percent (int Mark4Percent)
{
set_Value ("Mark4Percent", Integer.valueOf(Mark4Percent));
}
/** Get Mark 4 Percent.
@return Percentage up to this color is used */
public int getMark4Percent() 
{
Integer ii = (Integer)get_Value("Mark4Percent");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name Mark4Percent */
public static final String COLUMNNAME_Mark4Percent = "Mark4Percent";
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
/** Column name Name */
public static final String COLUMNNAME_Name = "Name";
/** Set Color Schema.
@param PA_ColorSchema_ID Performance Color Schema */
public void setPA_ColorSchema_ID (int PA_ColorSchema_ID)
{
if (PA_ColorSchema_ID < 1) throw new IllegalArgumentException ("PA_ColorSchema_ID is mandatory.");
set_ValueNoCheck ("PA_ColorSchema_ID", Integer.valueOf(PA_ColorSchema_ID));
}
/** Get Color Schema.
@return Performance Color Schema */
public int getPA_ColorSchema_ID() 
{
Integer ii = (Integer)get_Value("PA_ColorSchema_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name PA_ColorSchema_ID */
public static final String COLUMNNAME_PA_ColorSchema_ID = "PA_ColorSchema_ID";
}
