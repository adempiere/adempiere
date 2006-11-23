/******************************************************************************
 * Product: Compiere ERP & CRM Smart Business Solution                        *
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
/** Generated Model for M_Warehouse_Acct
 *  @author Jorg Janke (generated) 
 *  @version Release 2.6.0a - $Id$ */
public class X_M_Warehouse_Acct extends PO
{
/** Standard Constructor
@param ctx context
@param M_Warehouse_Acct_ID id
@param trxName transaction
*/
public X_M_Warehouse_Acct (Properties ctx, int M_Warehouse_Acct_ID, String trxName)
{
super (ctx, M_Warehouse_Acct_ID, trxName);
/** if (M_Warehouse_Acct_ID == 0)
{
setC_AcctSchema_ID (0);
setM_Warehouse_ID (0);
setW_Differences_Acct (0);
setW_InvActualAdjust_Acct (0);
setW_Inventory_Acct (0);
setW_Revaluation_Acct (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_M_Warehouse_Acct (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=191 */
public static final int Table_ID=191;

/** TableName=M_Warehouse_Acct */
public static final String Table_Name="M_Warehouse_Acct";

protected static KeyNamePair Model = new KeyNamePair(191,"M_Warehouse_Acct");

protected BigDecimal accessLevel = new BigDecimal(3);
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
StringBuffer sb = new StringBuffer ("X_M_Warehouse_Acct[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Accounting Schema.
@param C_AcctSchema_ID Rules for accounting */
public void setC_AcctSchema_ID (int C_AcctSchema_ID)
{
if (C_AcctSchema_ID < 1) throw new IllegalArgumentException ("C_AcctSchema_ID is mandatory.");
set_ValueNoCheck ("C_AcctSchema_ID", new Integer(C_AcctSchema_ID));
}
/** Get Accounting Schema.
@return Rules for accounting */
public int getC_AcctSchema_ID() 
{
Integer ii = (Integer)get_Value("C_AcctSchema_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Warehouse.
@param M_Warehouse_ID Storage Warehouse and Service Point */
public void setM_Warehouse_ID (int M_Warehouse_ID)
{
if (M_Warehouse_ID < 1) throw new IllegalArgumentException ("M_Warehouse_ID is mandatory.");
set_ValueNoCheck ("M_Warehouse_ID", new Integer(M_Warehouse_ID));
}
/** Get Warehouse.
@return Storage Warehouse and Service Point */
public int getM_Warehouse_ID() 
{
Integer ii = (Integer)get_Value("M_Warehouse_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Warehouse Differences.
@param W_Differences_Acct Warehouse Differences Account */
public void setW_Differences_Acct (int W_Differences_Acct)
{
set_Value ("W_Differences_Acct", new Integer(W_Differences_Acct));
}
/** Get Warehouse Differences.
@return Warehouse Differences Account */
public int getW_Differences_Acct() 
{
Integer ii = (Integer)get_Value("W_Differences_Acct");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Inventory Adjustment.
@param W_InvActualAdjust_Acct Account for Inventory value adjustments for Actual Costing */
public void setW_InvActualAdjust_Acct (int W_InvActualAdjust_Acct)
{
set_Value ("W_InvActualAdjust_Acct", new Integer(W_InvActualAdjust_Acct));
}
/** Get Inventory Adjustment.
@return Account for Inventory value adjustments for Actual Costing */
public int getW_InvActualAdjust_Acct() 
{
Integer ii = (Integer)get_Value("W_InvActualAdjust_Acct");
if (ii == null) return 0;
return ii.intValue();
}
/** Set (Not Used).
@param W_Inventory_Acct Warehouse Inventory Asset Account - Currently not used */
public void setW_Inventory_Acct (int W_Inventory_Acct)
{
set_Value ("W_Inventory_Acct", new Integer(W_Inventory_Acct));
}
/** Get (Not Used).
@return Warehouse Inventory Asset Account - Currently not used */
public int getW_Inventory_Acct() 
{
Integer ii = (Integer)get_Value("W_Inventory_Acct");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Inventory Revaluation.
@param W_Revaluation_Acct Account for Inventory Revaluation */
public void setW_Revaluation_Acct (int W_Revaluation_Acct)
{
set_Value ("W_Revaluation_Acct", new Integer(W_Revaluation_Acct));
}
/** Get Inventory Revaluation.
@return Account for Inventory Revaluation */
public int getW_Revaluation_Acct() 
{
Integer ii = (Integer)get_Value("W_Revaluation_Acct");
if (ii == null) return 0;
return ii.intValue();
}
}
