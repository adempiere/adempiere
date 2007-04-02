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
/** Generated Model for C_ElementValue
 *  @author Adempiere (generated) 
 *  @version Release 3.1.6 - $Id$ */
public class X_C_ElementValue extends PO
{
/** Standard Constructor
@param ctx context
@param C_ElementValue_ID id
@param trxName transaction
*/
public X_C_ElementValue (Properties ctx, int C_ElementValue_ID, String trxName)
{
super (ctx, C_ElementValue_ID, trxName);
/** if (C_ElementValue_ID == 0)
{
setAccountSign (null);	// N
setAccountType (null);	// E
setC_ElementValue_ID (0);
setC_Element_ID (0);
setIsSummary (false);
setName (null);
setPostActual (true);	// Y
setPostBudget (true);	// Y
setPostEncumbrance (true);	// Y
setPostStatistical (true);	// Y
setValue (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_ElementValue (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=188 */
public static final int Table_ID=MTable.getTable_ID("C_ElementValue");

/** TableName=C_ElementValue */
public static final String Table_Name="C_ElementValue";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"C_ElementValue");

protected BigDecimal accessLevel = BigDecimal.valueOf(2);
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
StringBuffer sb = new StringBuffer ("X_C_ElementValue[").append(get_ID()).append("]");
return sb.toString();
}

/** AccountSign AD_Reference_ID=118 */
public static final int ACCOUNTSIGN_AD_Reference_ID=118;
/** Credit = C */
public static final String ACCOUNTSIGN_Credit = "C";
/** Debit = D */
public static final String ACCOUNTSIGN_Debit = "D";
/** Natural = N */
public static final String ACCOUNTSIGN_Natural = "N";
/** Set Account Sign.
@param AccountSign Indicates the Natural Sign of the Account as a Debit or Credit */
public void setAccountSign (String AccountSign)
{
if (AccountSign == null) throw new IllegalArgumentException ("AccountSign is mandatory");
if (AccountSign.equals("C") || AccountSign.equals("D") || AccountSign.equals("N"));
 else throw new IllegalArgumentException ("AccountSign Invalid value - " + AccountSign + " - Reference_ID=118 - C - D - N");
if (AccountSign.length() > 1)
{
log.warning("Length > 1 - truncated");
AccountSign = AccountSign.substring(0,0);
}
set_Value ("AccountSign", AccountSign);
}
/** Get Account Sign.
@return Indicates the Natural Sign of the Account as a Debit or Credit */
public String getAccountSign() 
{
return (String)get_Value("AccountSign");
}
/** Column name AccountSign */
public static final String COLUMNNAME_AccountSign = "AccountSign";

/** AccountType AD_Reference_ID=117 */
public static final int ACCOUNTTYPE_AD_Reference_ID=117;
/** Asset = A */
public static final String ACCOUNTTYPE_Asset = "A";
/** Expense = E */
public static final String ACCOUNTTYPE_Expense = "E";
/** Liability = L */
public static final String ACCOUNTTYPE_Liability = "L";
/** Memo = M */
public static final String ACCOUNTTYPE_Memo = "M";
/** Owner's Equity = O */
public static final String ACCOUNTTYPE_OwnerSEquity = "O";
/** Revenue = R */
public static final String ACCOUNTTYPE_Revenue = "R";
/** Set Account Type.
@param AccountType Indicates the type of account */
public void setAccountType (String AccountType)
{
if (AccountType == null) throw new IllegalArgumentException ("AccountType is mandatory");
if (AccountType.equals("A") || AccountType.equals("E") || AccountType.equals("L") || AccountType.equals("M") || AccountType.equals("O") || AccountType.equals("R"));
 else throw new IllegalArgumentException ("AccountType Invalid value - " + AccountType + " - Reference_ID=117 - A - E - L - M - O - R");
if (AccountType.length() > 1)
{
log.warning("Length > 1 - truncated");
AccountType = AccountType.substring(0,0);
}
set_Value ("AccountType", AccountType);
}
/** Get Account Type.
@return Indicates the type of account */
public String getAccountType() 
{
return (String)get_Value("AccountType");
}
/** Column name AccountType */
public static final String COLUMNNAME_AccountType = "AccountType";
/** Set Bank Account.
@param C_BankAccount_ID Account at the Bank */
public void setC_BankAccount_ID (int C_BankAccount_ID)
{
if (C_BankAccount_ID <= 0) set_Value ("C_BankAccount_ID", null);
 else 
set_Value ("C_BankAccount_ID", Integer.valueOf(C_BankAccount_ID));
}
/** Get Bank Account.
@return Account at the Bank */
public int getC_BankAccount_ID() 
{
Integer ii = (Integer)get_Value("C_BankAccount_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_BankAccount_ID */
public static final String COLUMNNAME_C_BankAccount_ID = "C_BankAccount_ID";
/** Set Currency.
@param C_Currency_ID The Currency for this record */
public void setC_Currency_ID (int C_Currency_ID)
{
if (C_Currency_ID <= 0) set_Value ("C_Currency_ID", null);
 else 
set_Value ("C_Currency_ID", Integer.valueOf(C_Currency_ID));
}
/** Get Currency.
@return The Currency for this record */
public int getC_Currency_ID() 
{
Integer ii = (Integer)get_Value("C_Currency_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_Currency_ID */
public static final String COLUMNNAME_C_Currency_ID = "C_Currency_ID";
/** Set Account Element.
@param C_ElementValue_ID Account Element */
public void setC_ElementValue_ID (int C_ElementValue_ID)
{
if (C_ElementValue_ID < 1) throw new IllegalArgumentException ("C_ElementValue_ID is mandatory.");
set_ValueNoCheck ("C_ElementValue_ID", Integer.valueOf(C_ElementValue_ID));
}
/** Get Account Element.
@return Account Element */
public int getC_ElementValue_ID() 
{
Integer ii = (Integer)get_Value("C_ElementValue_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_ElementValue_ID */
public static final String COLUMNNAME_C_ElementValue_ID = "C_ElementValue_ID";
/** Set Element.
@param C_Element_ID Accounting Element */
public void setC_Element_ID (int C_Element_ID)
{
if (C_Element_ID < 1) throw new IllegalArgumentException ("C_Element_ID is mandatory.");
set_ValueNoCheck ("C_Element_ID", Integer.valueOf(C_Element_ID));
}
/** Get Element.
@return Accounting Element */
public int getC_Element_ID() 
{
Integer ii = (Integer)get_Value("C_Element_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_Element_ID */
public static final String COLUMNNAME_C_Element_ID = "C_Element_ID";
/** Set Description.
@param Description Optional short description of the record */
public void setDescription (String Description)
{
if (Description != null && Description.length() > 255)
{
log.warning("Length > 255 - truncated");
Description = Description.substring(0,254);
}
set_Value ("Description", Description);
}
/** Get Description.
@return Optional short description of the record */
public String getDescription() 
{
return (String)get_Value("Description");
}
/** Column name Description */
public static final String COLUMNNAME_Description = "Description";
/** Set Bank Account.
@param IsBankAccount Indicates if this is the Bank Account */
public void setIsBankAccount (boolean IsBankAccount)
{
set_Value ("IsBankAccount", Boolean.valueOf(IsBankAccount));
}
/** Get Bank Account.
@return Indicates if this is the Bank Account */
public boolean isBankAccount() 
{
Object oo = get_Value("IsBankAccount");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsBankAccount */
public static final String COLUMNNAME_IsBankAccount = "IsBankAccount";
/** Set Document Controlled.
@param IsDocControlled Control account - If an account is controlled by a document, you cannot post manually to it */
public void setIsDocControlled (boolean IsDocControlled)
{
set_Value ("IsDocControlled", Boolean.valueOf(IsDocControlled));
}
/** Get Document Controlled.
@return Control account - If an account is controlled by a document, you cannot post manually to it */
public boolean isDocControlled() 
{
Object oo = get_Value("IsDocControlled");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsDocControlled */
public static final String COLUMNNAME_IsDocControlled = "IsDocControlled";
/** Set Foreign Currency Account.
@param IsForeignCurrency Balances in foreign currency accounts are held in the nominated currency */
public void setIsForeignCurrency (boolean IsForeignCurrency)
{
set_Value ("IsForeignCurrency", Boolean.valueOf(IsForeignCurrency));
}
/** Get Foreign Currency Account.
@return Balances in foreign currency accounts are held in the nominated currency */
public boolean isForeignCurrency() 
{
Object oo = get_Value("IsForeignCurrency");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsForeignCurrency */
public static final String COLUMNNAME_IsForeignCurrency = "IsForeignCurrency";
/** Set Summary Level.
@param IsSummary This is a summary entity */
public void setIsSummary (boolean IsSummary)
{
set_Value ("IsSummary", Boolean.valueOf(IsSummary));
}
/** Get Summary Level.
@return This is a summary entity */
public boolean isSummary() 
{
Object oo = get_Value("IsSummary");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsSummary */
public static final String COLUMNNAME_IsSummary = "IsSummary";
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
/** Column name Name */
public static final String COLUMNNAME_Name = "Name";
/** Set Post Actual.
@param PostActual Actual Values can be posted */
public void setPostActual (boolean PostActual)
{
set_Value ("PostActual", Boolean.valueOf(PostActual));
}
/** Get Post Actual.
@return Actual Values can be posted */
public boolean isPostActual() 
{
Object oo = get_Value("PostActual");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name PostActual */
public static final String COLUMNNAME_PostActual = "PostActual";
/** Set Post Budget.
@param PostBudget Budget values can be posted */
public void setPostBudget (boolean PostBudget)
{
set_Value ("PostBudget", Boolean.valueOf(PostBudget));
}
/** Get Post Budget.
@return Budget values can be posted */
public boolean isPostBudget() 
{
Object oo = get_Value("PostBudget");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name PostBudget */
public static final String COLUMNNAME_PostBudget = "PostBudget";
/** Set Post Encumbrance.
@param PostEncumbrance Post commitments to this account */
public void setPostEncumbrance (boolean PostEncumbrance)
{
set_Value ("PostEncumbrance", Boolean.valueOf(PostEncumbrance));
}
/** Get Post Encumbrance.
@return Post commitments to this account */
public boolean isPostEncumbrance() 
{
Object oo = get_Value("PostEncumbrance");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name PostEncumbrance */
public static final String COLUMNNAME_PostEncumbrance = "PostEncumbrance";
/** Set Post Statistical.
@param PostStatistical Post statistical quantities to this account? */
public void setPostStatistical (boolean PostStatistical)
{
set_Value ("PostStatistical", Boolean.valueOf(PostStatistical));
}
/** Get Post Statistical.
@return Post statistical quantities to this account? */
public boolean isPostStatistical() 
{
Object oo = get_Value("PostStatistical");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name PostStatistical */
public static final String COLUMNNAME_PostStatistical = "PostStatistical";
/** Set Valid from.
@param ValidFrom Valid from including this date (first day) */
public void setValidFrom (Timestamp ValidFrom)
{
set_Value ("ValidFrom", ValidFrom);
}
/** Get Valid from.
@return Valid from including this date (first day) */
public Timestamp getValidFrom() 
{
return (Timestamp)get_Value("ValidFrom");
}
/** Column name ValidFrom */
public static final String COLUMNNAME_ValidFrom = "ValidFrom";
/** Set Valid to.
@param ValidTo Valid to including this date (last day) */
public void setValidTo (Timestamp ValidTo)
{
set_Value ("ValidTo", ValidTo);
}
/** Get Valid to.
@return Valid to including this date (last day) */
public Timestamp getValidTo() 
{
return (Timestamp)get_Value("ValidTo");
}
/** Column name ValidTo */
public static final String COLUMNNAME_ValidTo = "ValidTo";
/** Set Search Key.
@param Value Search key for the record in the format required - must be unique */
public void setValue (String Value)
{
if (Value == null) throw new IllegalArgumentException ("Value is mandatory.");
if (Value.length() > 40)
{
log.warning("Length > 40 - truncated");
Value = Value.substring(0,39);
}
set_Value ("Value", Value);
}
/** Get Search Key.
@return Search key for the record in the format required - must be unique */
public String getValue() 
{
return (String)get_Value("Value");
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), getValue());
}
/** Column name Value */
public static final String COLUMNNAME_Value = "Value";
}
