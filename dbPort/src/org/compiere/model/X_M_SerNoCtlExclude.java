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
/** Generated Model for M_SerNoCtlExclude
 *  @author Jorg Janke (generated) 
 *  @version Release 3.1.3 - $Id$ */
public class X_M_SerNoCtlExclude extends PO
{
/** Standard Constructor
@param ctx context
@param M_SerNoCtlExclude_ID id
@param trxName transaction
*/
public X_M_SerNoCtlExclude (Properties ctx, int M_SerNoCtlExclude_ID, String trxName)
{
super (ctx, M_SerNoCtlExclude_ID, trxName);
/** if (M_SerNoCtlExclude_ID == 0)
{
setAD_Table_ID (0);
setIsSOTrx (false);
setM_SerNoCtlExclude_ID (0);
setM_SerNoCtl_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_M_SerNoCtlExclude (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=811 */
public static final int Table_ID=MTable.getTable_ID("M_SerNoCtlExclude");

/** TableName=M_SerNoCtlExclude */
public static final String Table_Name="M_SerNoCtlExclude";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"M_SerNoCtlExclude");

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
StringBuffer sb = new StringBuffer ("X_M_SerNoCtlExclude[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Table.
@param AD_Table_ID Database Table information */
public void setAD_Table_ID (int AD_Table_ID)
{
if (AD_Table_ID < 1) throw new IllegalArgumentException ("AD_Table_ID is mandatory.");
set_Value ("AD_Table_ID", Integer.valueOf(AD_Table_ID));
}
/** Get Table.
@return Database Table information */
public int getAD_Table_ID() 
{
Integer ii = (Integer)get_Value("AD_Table_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Sales Transaction.
@param IsSOTrx This is a Sales Transaction */
public void setIsSOTrx (boolean IsSOTrx)
{
set_Value ("IsSOTrx", Boolean.valueOf(IsSOTrx));
}
/** Get Sales Transaction.
@return This is a Sales Transaction */
public boolean isSOTrx() 
{
Object oo = get_Value("IsSOTrx");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Exclude SerNo.
@param M_SerNoCtlExclude_ID Exclude the ability to create Serial Numbers in Attribute Sets */
public void setM_SerNoCtlExclude_ID (int M_SerNoCtlExclude_ID)
{
if (M_SerNoCtlExclude_ID < 1) throw new IllegalArgumentException ("M_SerNoCtlExclude_ID is mandatory.");
set_ValueNoCheck ("M_SerNoCtlExclude_ID", Integer.valueOf(M_SerNoCtlExclude_ID));
}
/** Get Exclude SerNo.
@return Exclude the ability to create Serial Numbers in Attribute Sets */
public int getM_SerNoCtlExclude_ID() 
{
Integer ii = (Integer)get_Value("M_SerNoCtlExclude_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Serial No Control.
@param M_SerNoCtl_ID Product Serial Number Control */
public void setM_SerNoCtl_ID (int M_SerNoCtl_ID)
{
if (M_SerNoCtl_ID < 1) throw new IllegalArgumentException ("M_SerNoCtl_ID is mandatory.");
set_ValueNoCheck ("M_SerNoCtl_ID", Integer.valueOf(M_SerNoCtl_ID));
}
/** Get Serial No Control.
@return Product Serial Number Control */
public int getM_SerNoCtl_ID() 
{
Integer ii = (Integer)get_Value("M_SerNoCtl_ID");
if (ii == null) return 0;
return ii.intValue();
}
}
