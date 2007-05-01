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
/** Generated Model for AD_DesktopWorkbench
 *  @author Adempiere (generated) 
 *  @version Release 3.2.0 - $Id$ */
public class X_AD_DesktopWorkbench extends PO
{
/** Standard Constructor
@param ctx context
@param AD_DesktopWorkbench_ID id
@param trxName transaction
*/
public X_AD_DesktopWorkbench (Properties ctx, int AD_DesktopWorkbench_ID, String trxName)
{
super (ctx, AD_DesktopWorkbench_ID, trxName);
/** if (AD_DesktopWorkbench_ID == 0)
{
setAD_DesktopWorkbench_ID (0);
setAD_Desktop_ID (0);
setAD_Workbench_ID (0);
setSeqNo (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_DesktopWorkbench (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=459 */
public static final int Table_ID=MTable.getTable_ID("AD_DesktopWorkbench");

/** TableName=AD_DesktopWorkbench */
public static final String Table_Name="AD_DesktopWorkbench";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"AD_DesktopWorkbench");

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
StringBuffer sb = new StringBuffer ("X_AD_DesktopWorkbench[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Desktop Workbench.
@param AD_DesktopWorkbench_ID Desktop Workbench */
public void setAD_DesktopWorkbench_ID (int AD_DesktopWorkbench_ID)
{
if (AD_DesktopWorkbench_ID < 1) throw new IllegalArgumentException ("AD_DesktopWorkbench_ID is mandatory.");
set_ValueNoCheck ("AD_DesktopWorkbench_ID", Integer.valueOf(AD_DesktopWorkbench_ID));
}
/** Get Desktop Workbench.
@return Desktop Workbench */
public int getAD_DesktopWorkbench_ID() 
{
Integer ii = (Integer)get_Value("AD_DesktopWorkbench_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_DesktopWorkbench_ID */
public static final String COLUMNNAME_AD_DesktopWorkbench_ID = "AD_DesktopWorkbench_ID";
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
/** Set Workbench.
@param AD_Workbench_ID Collection of windows, reports */
public void setAD_Workbench_ID (int AD_Workbench_ID)
{
if (AD_Workbench_ID < 1) throw new IllegalArgumentException ("AD_Workbench_ID is mandatory.");
set_Value ("AD_Workbench_ID", Integer.valueOf(AD_Workbench_ID));
}
/** Get Workbench.
@return Collection of windows, reports */
public int getAD_Workbench_ID() 
{
Integer ii = (Integer)get_Value("AD_Workbench_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getAD_Workbench_ID()));
}
/** Column name AD_Workbench_ID */
public static final String COLUMNNAME_AD_Workbench_ID = "AD_Workbench_ID";
/** Set Sequence.
@param SeqNo Method of ordering records;
 lowest number comes first */
public void setSeqNo (int SeqNo)
{
set_Value ("SeqNo", Integer.valueOf(SeqNo));
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
/** Column name SeqNo */
public static final String COLUMNNAME_SeqNo = "SeqNo";
}
