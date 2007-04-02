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
/** Generated Model for I_ElementValue
 *  @author Adempiere (generated) 
 *  @version Release 3.1.6 - $Id$ */
public class X_I_ElementValue extends PO
{
/** Standard Constructor
@param ctx context
@param I_ElementValue_ID id
@param trxName transaction
*/
public X_I_ElementValue (Properties ctx, int I_ElementValue_ID, String trxName)
{
super (ctx, I_ElementValue_ID, trxName);
/** if (I_ElementValue_ID == 0)
{
setI_ElementValue_ID (0);
setI_IsImported (false);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_I_ElementValue (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=534 */
public static final int Table_ID=MTable.getTable_ID("I_ElementValue");

/** TableName=I_ElementValue */
public static final String Table_Name="I_ElementValue";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"I_ElementValue");

protected BigDecimal accessLevel = BigDecimal.valueOf(6);
/** AccessLevel
@return 6 - System - Client 
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
StringBuffer sb = new StringBuffer ("X_I_ElementValue[").append(get_ID()).append("]");
return sb.toString();
}

/** AD_Column_ID AD_Reference_ID=272 */
public static final int AD_COLUMN_ID_AD_Reference_ID=272;
/** Set Column.
@param AD_Column_ID Column in the table */
public void setAD_Column_ID (int AD_Column_ID)
{
if (AD_Column_ID <= 0) set_Value ("AD_Column_ID", null);
 else 
set_Value ("AD_Column_ID", Integer.valueOf(AD_Column_ID));
}
/** Get Column.
@return Column in the table */
public int getAD_Column_ID() 
{
Integer ii = (Integer)get_Value("AD_Column_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_Column_ID */
public static final String COLUMNNAME_AD_Column_ID = "AD_Column_ID";

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
if (AccountSign == null || AccountSign.equals("C") || AccountSign.equals("D") || AccountSign.equals("N"));
 else throw new IllegalArgumentException ("AccountSign Invalid value - " + AccountSign + " - Reference_ID=118 - C - D - N");
if (AccountSign != null && AccountSign.length() > 1)
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
if (AccountType == null || AccountType.equals("A") || AccountType.equals("E") || AccountType.equals("L") || AccountType.equals("M") || AccountType.equals("O") || AccountType.equals("R"));
 else throw new IllegalArgumentException ("AccountType Invalid value - " + AccountType + " - Reference_ID=117 - A - E - L - M - O - R");
if (AccountType != null && AccountType.length() > 1)
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
/** Set Account Element.
@param C_ElementValue_ID Account Element */
public void setC_ElementValue_ID (int C_ElementValue_ID)
{
if (C_ElementValue_ID <= 0) set_Value ("C_ElementValue_ID", null);
 else 
set_Value ("C_ElementValue_ID", Integer.valueOf(C_ElementValue_ID));
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
if (C_Element_ID <= 0) set_Value ("C_Element_ID", null);
 else 
set_Value ("C_Element_ID", Integer.valueOf(C_Element_ID));
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
/** Set Default Account.
@param Default_Account Name of the Default Account Column */
public void setDefault_Account (String Default_Account)
{
if (Default_Account != null && Default_Account.length() > 30)
{
log.warning("Length > 30 - truncated");
Default_Account = Default_Account.substring(0,29);
}
set_Value ("Default_Account", Default_Account);
}
/** Get Default Account.
@return Name of the Default Account Column */
public String getDefault_Account() 
{
return (String)get_Value("Default_Account");
}
/** Column name Default_Account */
public static final String COLUMNNAME_Default_Account = "Default_Account";
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
/** Set Element Name.
@param ElementName Name of the Element */
public void setElementName (String ElementName)
{
if (ElementName != null && ElementName.length() > 60)
{
log.warning("Length > 60 - truncated");
ElementName = ElementName.substring(0,59);
}
set_Value ("ElementName", ElementName);
}
/** Get Element Name.
@return Name of the Element */
public String getElementName() 
{
return (String)get_Value("ElementName");
}
/** Column name ElementName */
public static final String COLUMNNAME_ElementName = "ElementName";
/** Set Import Account.
@param I_ElementValue_ID Import Account Value */
public void setI_ElementValue_ID (int I_ElementValue_ID)
{
if (I_ElementValue_ID < 1) throw new IllegalArgumentException ("I_ElementValue_ID is mandatory.");
set_ValueNoCheck ("I_ElementValue_ID", Integer.valueOf(I_ElementValue_ID));
}
/** Get Import Account.
@return Import Account Value */
public int getI_ElementValue_ID() 
{
Integer ii = (Integer)get_Value("I_ElementValue_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name I_ElementValue_ID */
public static final String COLUMNNAME_I_ElementValue_ID = "I_ElementValue_ID";
/** Set Import Error Message.
@param I_ErrorMsg Messages generated from import process */
public void setI_ErrorMsg (String I_ErrorMsg)
{
if (I_ErrorMsg != null && I_ErrorMsg.length() > 2000)
{
log.warning("Length > 2000 - truncated");
I_ErrorMsg = I_ErrorMsg.substring(0,1999);
}
set_Value ("I_ErrorMsg", I_ErrorMsg);
}
/** Get Import Error Message.
@return Messages generated from import process */
public String getI_ErrorMsg() 
{
return (String)get_Value("I_ErrorMsg");
}
/** Column name I_ErrorMsg */
public static final String COLUMNNAME_I_ErrorMsg = "I_ErrorMsg";
/** Set Imported.
@param I_IsImported Has this import been processed */
public void setI_IsImported (boolean I_IsImported)
{
set_Value ("I_IsImported", Boolean.valueOf(I_IsImported));
}
/** Get Imported.
@return Has this import been processed */
public boolean isI_IsImported() 
{
Object oo = get_Value("I_IsImported");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name I_IsImported */
public static final String COLUMNNAME_I_IsImported = "I_IsImported";
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
if (Name != null && Name.length() > 60)
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

/** ParentElementValue_ID AD_Reference_ID=362 */
public static final int PARENTELEMENTVALUE_ID_AD_Reference_ID=362;
/** Set Parent Account.
@param ParentElementValue_ID The parent (summary) account */
public void setParentElementValue_ID (int ParentElementValue_ID)
{
if (ParentElementValue_ID <= 0) set_Value ("ParentElementValue_ID", null);
 else 
set_Value ("ParentElementValue_ID", Integer.valueOf(ParentElementValue_ID));
}
/** Get Parent Account.
@return The parent (summary) account */
public int getParentElementValue_ID() 
{
Integer ii = (Integer)get_Value("ParentElementValue_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name ParentElementValue_ID */
public static final String COLUMNNAME_ParentElementValue_ID = "ParentElementValue_ID";
/** Set Parent Key.
@param ParentValue Key if the Parent */
public void setParentValue (String ParentValue)
{
if (ParentValue != null && ParentValue.length() > 40)
{
log.warning("Length > 40 - truncated");
ParentValue = ParentValue.substring(0,39);
}
set_Value ("ParentValue", ParentValue);
}
/** Get Parent Key.
@return Key if the Parent */
public String getParentValue() 
{
return (String)get_Value("ParentValue");
}
/** Column name ParentValue */
public static final String COLUMNNAME_ParentValue = "ParentValue";
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
/** Set Processed.
@param Processed The document has been processed */
public void setProcessed (boolean Processed)
{
set_Value ("Processed", Boolean.valueOf(Processed));
}
/** Get Processed.
@return The document has been processed */
public boolean isProcessed() 
{
Object oo = get_Value("Processed");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name Processed */
public static final String COLUMNNAME_Processed = "Processed";
/** Set Process Now.
@param Processing Process Now */
public void setProcessing (boolean Processing)
{
set_Value ("Processing", Boolean.valueOf(Processing));
}
/** Get Process Now.
@return Process Now */
public boolean isProcessing() 
{
Object oo = get_Value("Processing");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name Processing */
public static final String COLUMNNAME_Processing = "Processing";
/** Set Search Key.
@param Value Search key for the record in the format required - must be unique */
public void setValue (String Value)
{
if (Value != null && Value.length() > 40)
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
