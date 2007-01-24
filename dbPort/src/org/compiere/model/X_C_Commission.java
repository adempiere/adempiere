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
/** Generated Model for C_Commission
 *  @author Adempiere (generated) 
 *  @version Release 3.1.4 - $Id$ */
public class X_C_Commission extends PO
{
/** Standard Constructor
@param ctx context
@param C_Commission_ID id
@param trxName transaction
*/
public X_C_Commission (Properties ctx, int C_Commission_ID, String trxName)
{
super (ctx, C_Commission_ID, trxName);
/** if (C_Commission_ID == 0)
{
setC_BPartner_ID (0);
setC_Charge_ID (0);
setC_Commission_ID (0);
setC_Currency_ID (0);
setDocBasisType (null);	// I
setFrequencyType (null);	// M
setListDetails (false);
setName (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_Commission (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=429 */
public static final int Table_ID=MTable.getTable_ID("C_Commission");

/** TableName=C_Commission */
public static final String Table_Name="C_Commission";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"C_Commission");

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
StringBuffer sb = new StringBuffer ("X_C_Commission[").append(get_ID()).append("]");
return sb.toString();
}

/** C_BPartner_ID AD_Reference_ID=232 */
public static final int C_BPARTNER_ID_AD_Reference_ID=232;
/** Set Business Partner .
@param C_BPartner_ID Identifies a Business Partner */
public void setC_BPartner_ID (int C_BPartner_ID)
{
if (C_BPartner_ID < 1) throw new IllegalArgumentException ("C_BPartner_ID is mandatory.");
set_Value ("C_BPartner_ID", Integer.valueOf(C_BPartner_ID));
}
/** Get Business Partner .
@return Identifies a Business Partner */
public int getC_BPartner_ID() 
{
Integer ii = (Integer)get_Value("C_BPartner_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Charge.
@param C_Charge_ID Additional document charges */
public void setC_Charge_ID (int C_Charge_ID)
{
if (C_Charge_ID < 1) throw new IllegalArgumentException ("C_Charge_ID is mandatory.");
set_Value ("C_Charge_ID", Integer.valueOf(C_Charge_ID));
}
/** Get Charge.
@return Additional document charges */
public int getC_Charge_ID() 
{
Integer ii = (Integer)get_Value("C_Charge_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Commission.
@param C_Commission_ID Commission */
public void setC_Commission_ID (int C_Commission_ID)
{
if (C_Commission_ID < 1) throw new IllegalArgumentException ("C_Commission_ID is mandatory.");
set_ValueNoCheck ("C_Commission_ID", Integer.valueOf(C_Commission_ID));
}
/** Get Commission.
@return Commission */
public int getC_Commission_ID() 
{
Integer ii = (Integer)get_Value("C_Commission_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Currency.
@param C_Currency_ID The Currency for this record */
public void setC_Currency_ID (int C_Currency_ID)
{
if (C_Currency_ID < 1) throw new IllegalArgumentException ("C_Currency_ID is mandatory.");
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
/** Set Create lines from.
@param CreateFrom Process which will generate a new document lines based on an existing document */
public void setCreateFrom (String CreateFrom)
{
if (CreateFrom != null && CreateFrom.length() > 1)
{
log.warning("Length > 1 - truncated");
CreateFrom = CreateFrom.substring(0,0);
}
set_Value ("CreateFrom", CreateFrom);
}
/** Get Create lines from.
@return Process which will generate a new document lines based on an existing document */
public String getCreateFrom() 
{
return (String)get_Value("CreateFrom");
}
/** Set Date last run.
@param DateLastRun Date the process was last run. */
public void setDateLastRun (Timestamp DateLastRun)
{
set_ValueNoCheck ("DateLastRun", DateLastRun);
}
/** Get Date last run.
@return Date the process was last run. */
public Timestamp getDateLastRun() 
{
return (Timestamp)get_Value("DateLastRun");
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

/** DocBasisType AD_Reference_ID=224 */
public static final int DOCBASISTYPE_AD_Reference_ID=224;
/** Invoice = I */
public static final String DOCBASISTYPE_Invoice = "I";
/** Order = O */
public static final String DOCBASISTYPE_Order = "O";
/** Receipt = R */
public static final String DOCBASISTYPE_Receipt = "R";
/** Set Calculation Basis.
@param DocBasisType Basis for the calculation the commission */
public void setDocBasisType (String DocBasisType)
{
if (DocBasisType == null) throw new IllegalArgumentException ("DocBasisType is mandatory");
if (DocBasisType.equals("I") || DocBasisType.equals("O") || DocBasisType.equals("R"));
 else throw new IllegalArgumentException ("DocBasisType Invalid value - " + DocBasisType + " - Reference_ID=224 - I - O - R");
if (DocBasisType.length() > 1)
{
log.warning("Length > 1 - truncated");
DocBasisType = DocBasisType.substring(0,0);
}
set_Value ("DocBasisType", DocBasisType);
}
/** Get Calculation Basis.
@return Basis for the calculation the commission */
public String getDocBasisType() 
{
return (String)get_Value("DocBasisType");
}

/** FrequencyType AD_Reference_ID=225 */
public static final int FREQUENCYTYPE_AD_Reference_ID=225;
/** Monthly = M */
public static final String FREQUENCYTYPE_Monthly = "M";
/** Quarterly = Q */
public static final String FREQUENCYTYPE_Quarterly = "Q";
/** Weekly = W */
public static final String FREQUENCYTYPE_Weekly = "W";
/** Yearly = Y */
public static final String FREQUENCYTYPE_Yearly = "Y";
/** Set Frequency Type.
@param FrequencyType Frequency of event */
public void setFrequencyType (String FrequencyType)
{
if (FrequencyType == null) throw new IllegalArgumentException ("FrequencyType is mandatory");
if (FrequencyType.equals("M") || FrequencyType.equals("Q") || FrequencyType.equals("W") || FrequencyType.equals("Y"));
 else throw new IllegalArgumentException ("FrequencyType Invalid value - " + FrequencyType + " - Reference_ID=225 - M - Q - W - Y");
if (FrequencyType.length() > 1)
{
log.warning("Length > 1 - truncated");
FrequencyType = FrequencyType.substring(0,0);
}
set_Value ("FrequencyType", FrequencyType);
}
/** Get Frequency Type.
@return Frequency of event */
public String getFrequencyType() 
{
return (String)get_Value("FrequencyType");
}
/** Set List Details.
@param ListDetails List document details */
public void setListDetails (boolean ListDetails)
{
set_Value ("ListDetails", Boolean.valueOf(ListDetails));
}
/** Get List Details.
@return List document details */
public boolean isListDetails() 
{
Object oo = get_Value("ListDetails");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
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
}
