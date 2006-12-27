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
/** Generated Model for C_ProjectIssueMA
 *  @author Jorg Janke (generated) 
 *  @version Release 3.1.3 - $Id$ */
public class X_C_ProjectIssueMA extends PO
{
/** Standard Constructor
@param ctx context
@param C_ProjectIssueMA_ID id
@param trxName transaction
*/
public X_C_ProjectIssueMA (Properties ctx, int C_ProjectIssueMA_ID, String trxName)
{
super (ctx, C_ProjectIssueMA_ID, trxName);
/** if (C_ProjectIssueMA_ID == 0)
{
setC_ProjectIssue_ID (0);
setM_AttributeSetInstance_ID (0);
setMovementQty (Env.ZERO);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_ProjectIssueMA (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=761 */
public static final int Table_ID=MTable.getTable_ID("C_ProjectIssueMA");

/** TableName=C_ProjectIssueMA */
public static final String Table_Name="C_ProjectIssueMA";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"C_ProjectIssueMA");

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
StringBuffer sb = new StringBuffer ("X_C_ProjectIssueMA[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Project Issue.
@param C_ProjectIssue_ID Project Issues (Material, Labor) */
public void setC_ProjectIssue_ID (int C_ProjectIssue_ID)
{
if (C_ProjectIssue_ID < 1) throw new IllegalArgumentException ("C_ProjectIssue_ID is mandatory.");
set_ValueNoCheck ("C_ProjectIssue_ID", Integer.valueOf(C_ProjectIssue_ID));
}
/** Get Project Issue.
@return Project Issues (Material, Labor) */
public int getC_ProjectIssue_ID() 
{
Integer ii = (Integer)get_Value("C_ProjectIssue_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getC_ProjectIssue_ID()));
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
}
