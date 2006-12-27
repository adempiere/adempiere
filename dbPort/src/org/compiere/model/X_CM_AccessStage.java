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
/** Generated Model for CM_AccessStage
 *  @author Jorg Janke (generated) 
 *  @version Release 3.1.3 - $Id$ */
public class X_CM_AccessStage extends PO
{
/** Standard Constructor
@param ctx context
@param CM_AccessStage_ID id
@param trxName transaction
*/
public X_CM_AccessStage (Properties ctx, int CM_AccessStage_ID, String trxName)
{
super (ctx, CM_AccessStage_ID, trxName);
/** if (CM_AccessStage_ID == 0)
{
setCM_AccessProfile_ID (0);
setCM_CStage_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_CM_AccessStage (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=889 */
public static final int Table_ID=MTable.getTable_ID("CM_AccessStage");

/** TableName=CM_AccessStage */
public static final String Table_Name="CM_AccessStage";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"CM_AccessStage");

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
StringBuffer sb = new StringBuffer ("X_CM_AccessStage[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Web Access Profile.
@param CM_AccessProfile_ID Web Access Profile */
public void setCM_AccessProfile_ID (int CM_AccessProfile_ID)
{
if (CM_AccessProfile_ID < 1) throw new IllegalArgumentException ("CM_AccessProfile_ID is mandatory.");
set_ValueNoCheck ("CM_AccessProfile_ID", Integer.valueOf(CM_AccessProfile_ID));
}
/** Get Web Access Profile.
@return Web Access Profile */
public int getCM_AccessProfile_ID() 
{
Integer ii = (Integer)get_Value("CM_AccessProfile_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Web Container Stage.
@param CM_CStage_ID Web Container Stage contains the staging content like images, text etc. */
public void setCM_CStage_ID (int CM_CStage_ID)
{
if (CM_CStage_ID < 1) throw new IllegalArgumentException ("CM_CStage_ID is mandatory.");
set_ValueNoCheck ("CM_CStage_ID", Integer.valueOf(CM_CStage_ID));
}
/** Get Web Container Stage.
@return Web Container Stage contains the staging content like images, text etc. */
public int getCM_CStage_ID() 
{
Integer ii = (Integer)get_Value("CM_CStage_ID");
if (ii == null) return 0;
return ii.intValue();
}
}
