/******************************************************************************
 * Product: Compiere ERP & CRM Smart Business Solution                        *
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
/** Generated Model for Test
 *  @author Jorg Janke (generated) 
 *  @version Release 2.6.0a - $Id$ */
public class X_Test extends PO
{
/** Standard Constructor
@param ctx context
@param Test_ID id
@param trxName transaction
*/
public X_Test (Properties ctx, int Test_ID, String trxName)
{
super (ctx, Test_ID, trxName);
/** if (Test_ID == 0)
{
setName (null);
setTest_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_Test (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=135 */
public static final int Table_ID=135;

/** TableName=Test */
public static final String Table_Name="Test";

protected static KeyNamePair Model = new KeyNamePair(135,"Test");

protected BigDecimal accessLevel = new BigDecimal(4);
/** AccessLevel
@return 4 - System 
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
StringBuffer sb = new StringBuffer ("X_Test[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Account_Acct.
@param Account_Acct Account_Acct */
public void setAccount_Acct (int Account_Acct)
{
set_Value ("Account_Acct", new Integer(Account_Acct));
}
/** Get Account_Acct.
@return Account_Acct */
public int getAccount_Acct() 
{
Integer ii = (Integer)get_Value("Account_Acct");
if (ii == null) return 0;
return ii.intValue();
}
/** Set BinaryData.
@param BinaryData Binary Data */
public void setBinaryData (int BinaryData)
{
set_Value ("BinaryData", new Integer(BinaryData));
}
/** Get BinaryData.
@return Binary Data */
public int getBinaryData() 
{
Integer ii = (Integer)get_Value("BinaryData");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Business Partner .
@param C_BPartner_ID Identifies a Business Partner */
public void setC_BPartner_ID (int C_BPartner_ID)
{
if (C_BPartner_ID <= 0) set_Value ("C_BPartner_ID", null);
 else 
set_Value ("C_BPartner_ID", new Integer(C_BPartner_ID));
}
/** Get Business Partner .
@return Identifies a Business Partner */
public int getC_BPartner_ID() 
{
Integer ii = (Integer)get_Value("C_BPartner_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Currency.
@param C_Currency_ID The Currency for this record */
public void setC_Currency_ID (int C_Currency_ID)
{
if (C_Currency_ID <= 0) set_Value ("C_Currency_ID", null);
 else 
set_Value ("C_Currency_ID", new Integer(C_Currency_ID));
}
/** Get Currency.
@return The Currency for this record */
public int getC_Currency_ID() 
{
Integer ii = (Integer)get_Value("C_Currency_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Address.
@param C_Location_ID Location or Address */
public void setC_Location_ID (int C_Location_ID)
{
if (C_Location_ID <= 0) set_Value ("C_Location_ID", null);
 else 
set_Value ("C_Location_ID", new Integer(C_Location_ID));
}
/** Get Address.
@return Location or Address */
public int getC_Location_ID() 
{
Integer ii = (Integer)get_Value("C_Location_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Payment.
@param C_Payment_ID Payment identifier */
public void setC_Payment_ID (int C_Payment_ID)
{
if (C_Payment_ID <= 0) set_Value ("C_Payment_ID", null);
 else 
set_Value ("C_Payment_ID", new Integer(C_Payment_ID));
}
/** Get Payment.
@return Payment identifier */
public int getC_Payment_ID() 
{
Integer ii = (Integer)get_Value("C_Payment_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set UOM.
@param C_UOM_ID Unit of Measure */
public void setC_UOM_ID (int C_UOM_ID)
{
if (C_UOM_ID <= 0) set_Value ("C_UOM_ID", null);
 else 
set_Value ("C_UOM_ID", new Integer(C_UOM_ID));
}
/** Get UOM.
@return Unit of Measure */
public int getC_UOM_ID() 
{
Integer ii = (Integer)get_Value("C_UOM_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Character Data.
@param CharacterData Long Character Field */
public void setCharacterData (String CharacterData)
{
set_Value ("CharacterData", CharacterData);
}
/** Get Character Data.
@return Long Character Field */
public String getCharacterData() 
{
return (String)get_Value("CharacterData");
}
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
/** Set Comment/Help.
@param Help Comment or Hint */
public void setHelp (String Help)
{
if (Help != null && Help.length() > 2000)
{
log.warning("Length > 2000 - truncated");
Help = Help.substring(0,1999);
}
set_Value ("Help", Help);
}
/** Get Comment/Help.
@return Comment or Hint */
public String getHelp() 
{
return (String)get_Value("Help");
}
/** Set Locator.
@param M_Locator_ID Warehouse Locator */
public void setM_Locator_ID (int M_Locator_ID)
{
if (M_Locator_ID <= 0) set_Value ("M_Locator_ID", null);
 else 
set_Value ("M_Locator_ID", new Integer(M_Locator_ID));
}
/** Get Locator.
@return Warehouse Locator */
public int getM_Locator_ID() 
{
Integer ii = (Integer)get_Value("M_Locator_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Product.
@param M_Product_ID Product, Service, Item */
public void setM_Product_ID (int M_Product_ID)
{
if (M_Product_ID <= 0) set_Value ("M_Product_ID", null);
 else 
set_Value ("M_Product_ID", new Integer(M_Product_ID));
}
/** Get Product.
@return Product, Service, Item */
public int getM_Product_ID() 
{
Integer ii = (Integer)get_Value("M_Product_ID");
if (ii == null) return 0;
return ii.intValue();
}
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
/** Set Processed.
@param Processed The document has been processed */
public void setProcessed (boolean Processed)
{
set_Value ("Processed", new Boolean(Processed));
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
/** Set Process Now.
@param Processing Process Now */
public void setProcessing (boolean Processing)
{
set_Value ("Processing", new Boolean(Processing));
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
/** Set Amount.
@param T_Amount Amount */
public void setT_Amount (BigDecimal T_Amount)
{
set_Value ("T_Amount", T_Amount);
}
/** Get Amount.
@return Amount */
public BigDecimal getT_Amount() 
{
BigDecimal bd = (BigDecimal)get_Value("T_Amount");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Date.
@param T_Date Date */
public void setT_Date (Timestamp T_Date)
{
set_Value ("T_Date", T_Date);
}
/** Get Date.
@return Date */
public Timestamp getT_Date() 
{
return (Timestamp)get_Value("T_Date");
}
/** Set DateTime.
@param T_DateTime DateTime */
public void setT_DateTime (Timestamp T_DateTime)
{
set_Value ("T_DateTime", T_DateTime);
}
/** Get DateTime.
@return DateTime */
public Timestamp getT_DateTime() 
{
return (Timestamp)get_Value("T_DateTime");
}
/** Set Integer.
@param T_Integer Integer */
public void setT_Integer (int T_Integer)
{
set_Value ("T_Integer", new Integer(T_Integer));
}
/** Get Integer.
@return Integer */
public int getT_Integer() 
{
Integer ii = (Integer)get_Value("T_Integer");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Number.
@param T_Number Number */
public void setT_Number (BigDecimal T_Number)
{
set_Value ("T_Number", T_Number);
}
/** Get Number.
@return Number */
public BigDecimal getT_Number() 
{
BigDecimal bd = (BigDecimal)get_Value("T_Number");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Qty.
@param T_Qty Qty */
public void setT_Qty (BigDecimal T_Qty)
{
set_Value ("T_Qty", T_Qty);
}
/** Get Qty.
@return Qty */
public BigDecimal getT_Qty() 
{
BigDecimal bd = (BigDecimal)get_Value("T_Qty");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Test ID.
@param Test_ID Test ID */
public void setTest_ID (int Test_ID)
{
if (Test_ID < 1) throw new IllegalArgumentException ("Test_ID is mandatory.");
set_ValueNoCheck ("Test_ID", new Integer(Test_ID));
}
/** Get Test ID.
@return Test ID */
public int getTest_ID() 
{
Integer ii = (Integer)get_Value("Test_ID");
if (ii == null) return 0;
return ii.intValue();
}
}
