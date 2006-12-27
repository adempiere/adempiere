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
/** Generated Model for M_MovementLineMA
 *  @author Jorg Janke (generated) 
 *  @version Release 3.1.3 - $Id$ */
public class X_M_MovementLineMA extends PO
{
/** Standard Constructor
@param ctx context
@param M_MovementLineMA_ID id
@param trxName transaction
*/
public X_M_MovementLineMA (Properties ctx, int M_MovementLineMA_ID, String trxName)
{
super (ctx, M_MovementLineMA_ID, trxName);
/** if (M_MovementLineMA_ID == 0)
{
setM_AttributeSetInstance_ID (0);
setM_MovementLine_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_M_MovementLineMA (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=764 */
public static final int Table_ID=MTable.getTable_ID("M_MovementLineMA");

/** TableName=M_MovementLineMA */
public static final String Table_Name="M_MovementLineMA";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"M_MovementLineMA");

protected BigDecimal accessLevel = new BigDecimal(1);
/** AccessLevel
@return 1 - Org 
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
StringBuffer sb = new StringBuffer ("X_M_MovementLineMA[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Attribute Set Instance.
@param M_AttributeSetInstance_ID Product Attribute Set Instance */
public void setM_AttributeSetInstance_ID (int M_AttributeSetInstance_ID)
{
if (M_AttributeSetInstance_ID < 0) throw new IllegalArgumentException ("M_AttributeSetInstance_ID is mandatory.");
set_ValueNoCheck ("M_AttributeSetInstance_ID", Integer.valueOf(M_AttributeSetInstance_ID));
}
/** Get Attribute Set Instance.
@return Product Attribute Set Instance */
public int getM_AttributeSetInstance_ID() 
{
Integer ii = (Integer)get_Value("M_AttributeSetInstance_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Move Line.
@param M_MovementLine_ID Inventory Move document Line */
public void setM_MovementLine_ID (int M_MovementLine_ID)
{
if (M_MovementLine_ID < 1) throw new IllegalArgumentException ("M_MovementLine_ID is mandatory.");
set_ValueNoCheck ("M_MovementLine_ID", Integer.valueOf(M_MovementLine_ID));
}
/** Get Move Line.
@return Inventory Move document Line */
public int getM_MovementLine_ID() 
{
Integer ii = (Integer)get_Value("M_MovementLine_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getM_MovementLine_ID()));
}
/** Set Movement Quantity.
@param MovementQty Quantity of a product moved. */
public void setMovementQty (BigDecimal MovementQty)
{
set_Value ("MovementQty", MovementQty);
}
/** Get Movement Quantity.
@return Quantity of a product moved. */
public BigDecimal getMovementQty() 
{
BigDecimal bd = (BigDecimal)get_Value("MovementQty");
if (bd == null) return Env.ZERO;
return bd;
}
}
