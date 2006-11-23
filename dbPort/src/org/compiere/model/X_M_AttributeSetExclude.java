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
/** Generated Model for M_AttributeSetExclude
 *  @author Jorg Janke (generated) 
 *  @version Release 2.6.0a - $Id$ */
public class X_M_AttributeSetExclude extends PO
{
/** Standard Constructor
@param ctx context
@param M_AttributeSetExclude_ID id
@param trxName transaction
*/
public X_M_AttributeSetExclude (Properties ctx, int M_AttributeSetExclude_ID, String trxName)
{
super (ctx, M_AttributeSetExclude_ID, trxName);
/** if (M_AttributeSetExclude_ID == 0)
{
setAD_Table_ID (0);
setIsSOTrx (false);
setM_AttributeSetExclude_ID (0);
setM_AttributeSet_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_M_AttributeSetExclude (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=809 */
public static final int Table_ID=809;

/** TableName=M_AttributeSetExclude */
public static final String Table_Name="M_AttributeSetExclude";

protected static KeyNamePair Model = new KeyNamePair(809,"M_AttributeSetExclude");

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
StringBuffer sb = new StringBuffer ("X_M_AttributeSetExclude[").append(get_ID()).append("]");
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
/** Set Exclude Attribute Set.
@param M_AttributeSetExclude_ID Exclude the ability to enter Attribute Sets */
public void setM_AttributeSetExclude_ID (int M_AttributeSetExclude_ID)
{
if (M_AttributeSetExclude_ID < 1) throw new IllegalArgumentException ("M_AttributeSetExclude_ID is mandatory.");
set_ValueNoCheck ("M_AttributeSetExclude_ID", new Integer(M_AttributeSetExclude_ID));
}
/** Get Exclude Attribute Set.
@return Exclude the ability to enter Attribute Sets */
public int getM_AttributeSetExclude_ID() 
{
Integer ii = (Integer)get_Value("M_AttributeSetExclude_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Attribute Set.
@param M_AttributeSet_ID Product Attribute Set */
public void setM_AttributeSet_ID (int M_AttributeSet_ID)
{
if (M_AttributeSet_ID < 0) throw new IllegalArgumentException ("M_AttributeSet_ID is mandatory.");
set_ValueNoCheck ("M_AttributeSet_ID", new Integer(M_AttributeSet_ID));
}
/** Get Attribute Set.
@return Product Attribute Set */
public int getM_AttributeSet_ID() 
{
Integer ii = (Integer)get_Value("M_AttributeSet_ID");
if (ii == null) return 0;
return ii.intValue();
}
}
