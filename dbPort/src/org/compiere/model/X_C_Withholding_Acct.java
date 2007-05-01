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
/** Generated Model for C_Withholding_Acct
 *  @author Adempiere (generated) 
 *  @version Release 3.2.0 - $Id$ */
public class X_C_Withholding_Acct extends PO
{
/** Standard Constructor
@param ctx context
@param C_Withholding_Acct_ID id
@param trxName transaction
*/
public X_C_Withholding_Acct (Properties ctx, int C_Withholding_Acct_ID, String trxName)
{
super (ctx, C_Withholding_Acct_ID, trxName);
/** if (C_Withholding_Acct_ID == 0)
{
setC_AcctSchema_ID (0);
setC_Withholding_ID (0);
setWithholding_Acct (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_Withholding_Acct (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=400 */
public static final int Table_ID=MTable.getTable_ID("C_Withholding_Acct");

/** TableName=C_Withholding_Acct */
public static final String Table_Name="C_Withholding_Acct";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"C_Withholding_Acct");

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
StringBuffer sb = new StringBuffer ("X_C_Withholding_Acct[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Accounting Schema.
@param C_AcctSchema_ID Rules for accounting */
public void setC_AcctSchema_ID (int C_AcctSchema_ID)
{
if (C_AcctSchema_ID < 1) throw new IllegalArgumentException ("C_AcctSchema_ID is mandatory.");
set_ValueNoCheck ("C_AcctSchema_ID", Integer.valueOf(C_AcctSchema_ID));
}
/** Get Accounting Schema.
@return Rules for accounting */
public int getC_AcctSchema_ID() 
{
Integer ii = (Integer)get_Value("C_AcctSchema_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_AcctSchema_ID */
public static final String COLUMNNAME_C_AcctSchema_ID = "C_AcctSchema_ID";
/** Set Withholding.
@param C_Withholding_ID Withholding type defined */
public void setC_Withholding_ID (int C_Withholding_ID)
{
if (C_Withholding_ID < 1) throw new IllegalArgumentException ("C_Withholding_ID is mandatory.");
set_ValueNoCheck ("C_Withholding_ID", Integer.valueOf(C_Withholding_ID));
}
/** Get Withholding.
@return Withholding type defined */
public int getC_Withholding_ID() 
{
Integer ii = (Integer)get_Value("C_Withholding_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_Withholding_ID */
public static final String COLUMNNAME_C_Withholding_ID = "C_Withholding_ID";
/** Set Withholding.
@param Withholding_Acct Account for Withholdings */
public void setWithholding_Acct (int Withholding_Acct)
{
set_Value ("Withholding_Acct", Integer.valueOf(Withholding_Acct));
}
/** Get Withholding.
@return Account for Withholdings */
public int getWithholding_Acct() 
{
Integer ii = (Integer)get_Value("Withholding_Acct");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name Withholding_Acct */
public static final String COLUMNNAME_Withholding_Acct = "Withholding_Acct";
}
