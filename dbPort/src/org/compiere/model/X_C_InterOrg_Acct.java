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
/** Generated Model for C_InterOrg_Acct
 *  @author Adempiere (generated) 
 *  @version Release 3.1.6 - $Id$ */
public class X_C_InterOrg_Acct extends PO
{
/** Standard Constructor
@param ctx context
@param C_InterOrg_Acct_ID id
@param trxName transaction
*/
public X_C_InterOrg_Acct (Properties ctx, int C_InterOrg_Acct_ID, String trxName)
{
super (ctx, C_InterOrg_Acct_ID, trxName);
/** if (C_InterOrg_Acct_ID == 0)
{
setAD_OrgTo_ID (0);
setC_AcctSchema_ID (0);
setIntercompanyDueFrom_Acct (0);
setIntercompanyDueTo_Acct (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_InterOrg_Acct (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=397 */
public static final int Table_ID=MTable.getTable_ID("C_InterOrg_Acct");

/** TableName=C_InterOrg_Acct */
public static final String Table_Name="C_InterOrg_Acct";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"C_InterOrg_Acct");

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
StringBuffer sb = new StringBuffer ("X_C_InterOrg_Acct[").append(get_ID()).append("]");
return sb.toString();
}

/** AD_OrgTo_ID AD_Reference_ID=130 */
public static final int AD_ORGTO_ID_AD_Reference_ID=130;
/** Set Inter-Organization.
@param AD_OrgTo_ID Organization valid for intercompany documents */
public void setAD_OrgTo_ID (int AD_OrgTo_ID)
{
if (AD_OrgTo_ID < 1) throw new IllegalArgumentException ("AD_OrgTo_ID is mandatory.");
set_ValueNoCheck ("AD_OrgTo_ID", Integer.valueOf(AD_OrgTo_ID));
}
/** Get Inter-Organization.
@return Organization valid for intercompany documents */
public int getAD_OrgTo_ID() 
{
Integer ii = (Integer)get_Value("AD_OrgTo_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_OrgTo_ID */
public static final String COLUMNNAME_AD_OrgTo_ID = "AD_OrgTo_ID";
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
/** Set Intercompany Due From Acct.
@param IntercompanyDueFrom_Acct Intercompany Due From / Receivables Account */
public void setIntercompanyDueFrom_Acct (int IntercompanyDueFrom_Acct)
{
set_Value ("IntercompanyDueFrom_Acct", Integer.valueOf(IntercompanyDueFrom_Acct));
}
/** Get Intercompany Due From Acct.
@return Intercompany Due From / Receivables Account */
public int getIntercompanyDueFrom_Acct() 
{
Integer ii = (Integer)get_Value("IntercompanyDueFrom_Acct");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name IntercompanyDueFrom_Acct */
public static final String COLUMNNAME_IntercompanyDueFrom_Acct = "IntercompanyDueFrom_Acct";
/** Set Intercompany Due To Acct.
@param IntercompanyDueTo_Acct Intercompany Due To / Payable Account */
public void setIntercompanyDueTo_Acct (int IntercompanyDueTo_Acct)
{
set_Value ("IntercompanyDueTo_Acct", Integer.valueOf(IntercompanyDueTo_Acct));
}
/** Get Intercompany Due To Acct.
@return Intercompany Due To / Payable Account */
public int getIntercompanyDueTo_Acct() 
{
Integer ii = (Integer)get_Value("IntercompanyDueTo_Acct");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name IntercompanyDueTo_Acct */
public static final String COLUMNNAME_IntercompanyDueTo_Acct = "IntercompanyDueTo_Acct";
}
