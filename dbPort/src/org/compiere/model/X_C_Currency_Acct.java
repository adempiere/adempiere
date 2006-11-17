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
/** Generated Model for C_Currency_Acct
 *  @author Jorg Janke (generated) 
 *  @version Release 2.5.3d - 2006-10-10 21:55:57.14 */
public class X_C_Currency_Acct extends PO
{
/** Standard Constructor
@param ctx context
@param C_Currency_Acct_ID id
@param trxName transaction
*/
public X_C_Currency_Acct (Properties ctx, int C_Currency_Acct_ID, String trxName)
{
super (ctx, C_Currency_Acct_ID, trxName);
/** if (C_Currency_Acct_ID == 0)
{
setC_AcctSchema_ID (0);
setC_Currency_ID (0);
setRealizedGain_Acct (0);
setRealizedLoss_Acct (0);
setUnrealizedGain_Acct (0);
setUnrealizedLoss_Acct (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_Currency_Acct (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=638 */
public static final int Table_ID=638;

/** TableName=C_Currency_Acct */
public static final String Table_Name="C_Currency_Acct";

protected static KeyNamePair Model = new KeyNamePair(638,"C_Currency_Acct");

protected BigDecimal accessLevel = new BigDecimal(3);
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
StringBuffer sb = new StringBuffer ("X_C_Currency_Acct[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Accounting Schema.
@param C_AcctSchema_ID Rules for accounting */
public void setC_AcctSchema_ID (int C_AcctSchema_ID)
{
if (C_AcctSchema_ID < 1) throw new IllegalArgumentException ("C_AcctSchema_ID is mandatory.");
set_ValueNoCheck ("C_AcctSchema_ID", new Integer(C_AcctSchema_ID));
}
/** Get Accounting Schema.
@return Rules for accounting */
public int getC_AcctSchema_ID() 
{
Integer ii = (Integer)get_Value("C_AcctSchema_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Currency.
@param C_Currency_ID The Currency for this record */
public void setC_Currency_ID (int C_Currency_ID)
{
if (C_Currency_ID < 1) throw new IllegalArgumentException ("C_Currency_ID is mandatory.");
set_ValueNoCheck ("C_Currency_ID", new Integer(C_Currency_ID));
}
/** Get Currency.
@return The Currency for this record */
public int getC_Currency_ID() 
{
Integer ii = (Integer)get_Value("C_Currency_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Realized Gain Acct.
@param RealizedGain_Acct Realized Gain Account */
public void setRealizedGain_Acct (int RealizedGain_Acct)
{
set_Value ("RealizedGain_Acct", new Integer(RealizedGain_Acct));
}
/** Get Realized Gain Acct.
@return Realized Gain Account */
public int getRealizedGain_Acct() 
{
Integer ii = (Integer)get_Value("RealizedGain_Acct");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Realized Loss Acct.
@param RealizedLoss_Acct Realized Loss Account */
public void setRealizedLoss_Acct (int RealizedLoss_Acct)
{
set_Value ("RealizedLoss_Acct", new Integer(RealizedLoss_Acct));
}
/** Get Realized Loss Acct.
@return Realized Loss Account */
public int getRealizedLoss_Acct() 
{
Integer ii = (Integer)get_Value("RealizedLoss_Acct");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Unrealized Gain Acct.
@param UnrealizedGain_Acct Unrealized Gain Account for currency revaluation */
public void setUnrealizedGain_Acct (int UnrealizedGain_Acct)
{
set_Value ("UnrealizedGain_Acct", new Integer(UnrealizedGain_Acct));
}
/** Get Unrealized Gain Acct.
@return Unrealized Gain Account for currency revaluation */
public int getUnrealizedGain_Acct() 
{
Integer ii = (Integer)get_Value("UnrealizedGain_Acct");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Unrealized Loss Acct.
@param UnrealizedLoss_Acct Unrealized Loss Account for currency revaluation */
public void setUnrealizedLoss_Acct (int UnrealizedLoss_Acct)
{
set_Value ("UnrealizedLoss_Acct", new Integer(UnrealizedLoss_Acct));
}
/** Get Unrealized Loss Acct.
@return Unrealized Loss Account for currency revaluation */
public int getUnrealizedLoss_Acct() 
{
Integer ii = (Integer)get_Value("UnrealizedLoss_Acct");
if (ii == null) return 0;
return ii.intValue();
}
}
