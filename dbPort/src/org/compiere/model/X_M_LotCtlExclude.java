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
/** Generated Model for M_LotCtlExclude
 *  @author Jorg Janke (generated) 
 *  @version Release 2.5.3d - 2006-10-10 21:56:01.281 */
public class X_M_LotCtlExclude extends PO
{
/** Standard Constructor
@param ctx context
@param M_LotCtlExclude_ID id
@param trxName transaction
*/
public X_M_LotCtlExclude (Properties ctx, int M_LotCtlExclude_ID, String trxName)
{
super (ctx, M_LotCtlExclude_ID, trxName);
/** if (M_LotCtlExclude_ID == 0)
{
setAD_Table_ID (0);
setIsSOTrx (false);
setM_LotCtlExclude_ID (0);
setM_LotCtl_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_M_LotCtlExclude (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=810 */
public static final int Table_ID=810;

/** TableName=M_LotCtlExclude */
public static final String Table_Name="M_LotCtlExclude";

protected static KeyNamePair Model = new KeyNamePair(810,"M_LotCtlExclude");

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
StringBuffer sb = new StringBuffer ("X_M_LotCtlExclude[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Table.
@param AD_Table_ID Database Table information */
public void setAD_Table_ID (int AD_Table_ID)
{
if (AD_Table_ID < 1) throw new IllegalArgumentException ("AD_Table_ID is mandatory.");
set_Value ("AD_Table_ID", new Integer(AD_Table_ID));
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
set_Value ("IsSOTrx", new Boolean(IsSOTrx));
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
/** Set Exclude Lot.
@param M_LotCtlExclude_ID Exclude the ability to create Lots in Attribute Sets */
public void setM_LotCtlExclude_ID (int M_LotCtlExclude_ID)
{
if (M_LotCtlExclude_ID < 1) throw new IllegalArgumentException ("M_LotCtlExclude_ID is mandatory.");
set_ValueNoCheck ("M_LotCtlExclude_ID", new Integer(M_LotCtlExclude_ID));
}
/** Get Exclude Lot.
@return Exclude the ability to create Lots in Attribute Sets */
public int getM_LotCtlExclude_ID() 
{
Integer ii = (Integer)get_Value("M_LotCtlExclude_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Lot Control.
@param M_LotCtl_ID Product Lot Control */
public void setM_LotCtl_ID (int M_LotCtl_ID)
{
if (M_LotCtl_ID < 1) throw new IllegalArgumentException ("M_LotCtl_ID is mandatory.");
set_ValueNoCheck ("M_LotCtl_ID", new Integer(M_LotCtl_ID));
}
/** Get Lot Control.
@return Product Lot Control */
public int getM_LotCtl_ID() 
{
Integer ii = (Integer)get_Value("M_LotCtl_ID");
if (ii == null) return 0;
return ii.intValue();
}
}
