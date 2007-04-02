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
/** Generated Model for C_CycleStep
 *  @author Adempiere (generated) 
 *  @version Release 3.1.6 - $Id$ */
public class X_C_CycleStep extends PO
{
/** Standard Constructor
@param ctx context
@param C_CycleStep_ID id
@param trxName transaction
*/
public X_C_CycleStep (Properties ctx, int C_CycleStep_ID, String trxName)
{
super (ctx, C_CycleStep_ID, trxName);
/** if (C_CycleStep_ID == 0)
{
setC_CycleStep_ID (0);
setC_Cycle_ID (0);
setName (null);
setRelativeWeight (Env.ZERO);	// 1
setSeqNo (0);	// @SQL=SELECT NVL(MAX(SeqNo),0)+10 AS DefaultValue FROM C_CycleStep WHERE C_Cycle_ID=@C_Cycle_ID@
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_CycleStep (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=590 */
public static final int Table_ID=MTable.getTable_ID("C_CycleStep");

/** TableName=C_CycleStep */
public static final String Table_Name="C_CycleStep";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"C_CycleStep");

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
StringBuffer sb = new StringBuffer ("X_C_CycleStep[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Cycle Step.
@param C_CycleStep_ID The step for this Cycle */
public void setC_CycleStep_ID (int C_CycleStep_ID)
{
if (C_CycleStep_ID < 1) throw new IllegalArgumentException ("C_CycleStep_ID is mandatory.");
set_ValueNoCheck ("C_CycleStep_ID", Integer.valueOf(C_CycleStep_ID));
}
/** Get Cycle Step.
@return The step for this Cycle */
public int getC_CycleStep_ID() 
{
Integer ii = (Integer)get_Value("C_CycleStep_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_CycleStep_ID */
public static final String COLUMNNAME_C_CycleStep_ID = "C_CycleStep_ID";
/** Set Project Cycle.
@param C_Cycle_ID Identifier for this Project Reporting Cycle */
public void setC_Cycle_ID (int C_Cycle_ID)
{
if (C_Cycle_ID < 1) throw new IllegalArgumentException ("C_Cycle_ID is mandatory.");
set_ValueNoCheck ("C_Cycle_ID", Integer.valueOf(C_Cycle_ID));
}
/** Get Project Cycle.
@return Identifier for this Project Reporting Cycle */
public int getC_Cycle_ID() 
{
Integer ii = (Integer)get_Value("C_Cycle_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_Cycle_ID */
public static final String COLUMNNAME_C_Cycle_ID = "C_Cycle_ID";
/** Set Name.
@param Name Alphanumeric identifier of the entity */
public void setName (String Name)
{
if (Name == null) throw new IllegalArgumentException ("Name is mandatory.");
if (Name.length() > 60)
{
log.warning("Length > 60 - truncated");
Name = Name.substring(0,59);
}
set_Value ("Name", Name);
}
/** Get Name.
@return Alphanumeric identifier of the entity */
public String getName() 
{
return (String)get_Value("Name");
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), getName());
}
/** Column name Name */
public static final String COLUMNNAME_Name = "Name";
/** Set Relative Weight.
@param RelativeWeight Relative weight of this step (0 = ignored) */
public void setRelativeWeight (BigDecimal RelativeWeight)
{
if (RelativeWeight == null) throw new IllegalArgumentException ("RelativeWeight is mandatory.");
set_Value ("RelativeWeight", RelativeWeight);
}
/** Get Relative Weight.
@return Relative weight of this step (0 = ignored) */
public BigDecimal getRelativeWeight() 
{
BigDecimal bd = (BigDecimal)get_Value("RelativeWeight");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name RelativeWeight */
public static final String COLUMNNAME_RelativeWeight = "RelativeWeight";
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
