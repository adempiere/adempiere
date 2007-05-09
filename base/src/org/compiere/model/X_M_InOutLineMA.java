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
/** Generated Model for M_InOutLineMA
 *  @author Adempiere (generated) 
 *  @version Release 3.2.0 - $Id$ */
public class X_M_InOutLineMA extends PO
{
/** Standard Constructor
@param ctx context
@param M_InOutLineMA_ID id
@param trxName transaction
*/
public X_M_InOutLineMA (Properties ctx, int M_InOutLineMA_ID, String trxName)
{
super (ctx, M_InOutLineMA_ID, trxName);
/** if (M_InOutLineMA_ID == 0)
{
setM_AttributeSetInstance_ID (0);
setM_InOutLine_ID (0);
setMovementQty (Env.ZERO);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_M_InOutLineMA (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=762 */
public static final int Table_ID=MTable.getTable_ID("M_InOutLineMA");

/** TableName=M_InOutLineMA */
public static final String Table_Name="M_InOutLineMA";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"M_InOutLineMA");

protected BigDecimal accessLevel = BigDecimal.valueOf(1);
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
StringBuffer sb = new StringBuffer ("X_M_InOutLineMA[").append(get_ID()).append("]");
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
/** Column name M_AttributeSetInstance_ID */
public static final String COLUMNNAME_M_AttributeSetInstance_ID = "M_AttributeSetInstance_ID";
/** Set Shipment/Receipt Line.
@param M_InOutLine_ID Line on Shipment or Receipt document */
public void setM_InOutLine_ID (int M_InOutLine_ID)
{
if (M_InOutLine_ID < 1) throw new IllegalArgumentException ("M_InOutLine_ID is mandatory.");
set_ValueNoCheck ("M_InOutLine_ID", Integer.valueOf(M_InOutLine_ID));
}
/** Get Shipment/Receipt Line.
@return Line on Shipment or Receipt document */
public int getM_InOutLine_ID() 
{
Integer ii = (Integer)get_Value("M_InOutLine_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getM_InOutLine_ID()));
}
/** Column name M_InOutLine_ID */
public static final String COLUMNNAME_M_InOutLine_ID = "M_InOutLine_ID";
/** Set Movement Quantity.
@param MovementQty Quantity of a product moved. */
public void setMovementQty (BigDecimal MovementQty)
{
if (MovementQty == null) throw new IllegalArgumentException ("MovementQty is mandatory.");
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
/** Column name MovementQty */
public static final String COLUMNNAME_MovementQty = "MovementQty";
}
