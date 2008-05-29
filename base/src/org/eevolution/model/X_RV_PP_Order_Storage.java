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
package org.eevolution.model;

/** Generated Model - DO NOT CHANGE */
import java.util.*;
import java.sql.*;
import java.math.*;
import org.compiere.util.*;
import org.compiere.model.*;
/** Generated Model for RV_PP_Order_Storage
 *  @author Adempiere (generated) 
 *  @version Release 3.1.5 - $Id$ */
public class X_RV_PP_Order_Storage extends PO
{
/** Standard Constructor
@param ctx context
@param RV_PP_Order_Storage_ID id
@param trxName transaction
*/
public X_RV_PP_Order_Storage (Properties ctx, int RV_PP_Order_Storage_ID, String trxName)
{
super (ctx, RV_PP_Order_Storage_ID, trxName);
/** if (RV_PP_Order_Storage_ID == 0)
{
setRV_PP_Order_Storage_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_RV_PP_Order_Storage (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=50057 */
public static final int Table_ID=MTable.getTable_ID("RV_PP_Order_Storage");

/** TableName=RV_PP_Order_Storage */
public static final String Table_Name="RV_PP_Order_Storage";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"RV_PP_Order_Storage");

protected BigDecimal accessLevel = BigDecimal.valueOf(3);
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
StringBuffer sb = new StringBuffer ("X_RV_PP_Order_Storage[").append(get_ID()).append("]");
return sb.toString();
}
/** Set RV_PP_Order_Storage.
@param RV_PP_Order_Storage_ID RV_PP_Order_Storage */
public void setRV_PP_Order_Storage_ID (int RV_PP_Order_Storage_ID)
{
if (RV_PP_Order_Storage_ID < 1) throw new IllegalArgumentException ("RV_PP_Order_Storage_ID is mandatory.");
set_ValueNoCheck ("RV_PP_Order_Storage_ID", Integer.valueOf(RV_PP_Order_Storage_ID));
}
/** Get RV_PP_Order_Storage.
@return RV_PP_Order_Storage */
public int getRV_PP_Order_Storage_ID() 
{
Integer ii = (Integer)get_Value("RV_PP_Order_Storage_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name RV_PP_Order_Storage_ID */
public static final String COLUMNNAME_RV_PP_Order_Storage_ID = "RV_PP_Order_Storage_ID";
}
