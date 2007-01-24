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
/** Generated Model for C_CyclePhase
 *  @author Adempiere (generated) 
 *  @version Release 3.1.4 - $Id$ */
public class X_C_CyclePhase extends PO
{
/** Standard Constructor
@param ctx context
@param C_CyclePhase_ID id
@param trxName transaction
*/
public X_C_CyclePhase (Properties ctx, int C_CyclePhase_ID, String trxName)
{
super (ctx, C_CyclePhase_ID, trxName);
/** if (C_CyclePhase_ID == 0)
{
setC_CycleStep_ID (0);
setC_Phase_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_CyclePhase (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=433 */
public static final int Table_ID=MTable.getTable_ID("C_CyclePhase");

/** TableName=C_CyclePhase */
public static final String Table_Name="C_CyclePhase";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"C_CyclePhase");

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
StringBuffer sb = new StringBuffer ("X_C_CyclePhase[").append(get_ID()).append("]");
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
/** Set Standard Phase.
@param C_Phase_ID Standard Phase of the Project Type */
public void setC_Phase_ID (int C_Phase_ID)
{
if (C_Phase_ID < 1) throw new IllegalArgumentException ("C_Phase_ID is mandatory.");
set_ValueNoCheck ("C_Phase_ID", Integer.valueOf(C_Phase_ID));
}
/** Get Standard Phase.
@return Standard Phase of the Project Type */
public int getC_Phase_ID() 
{
Integer ii = (Integer)get_Value("C_Phase_ID");
if (ii == null) return 0;
return ii.intValue();
}
}
