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
/** Generated Model for C_DocTypeCounter
 *  @author Adempiere (generated) 
 *  @version Release 3.1.6 - $Id$ */
public class X_C_DocTypeCounter extends PO
{
/** Standard Constructor
@param ctx context
@param C_DocTypeCounter_ID id
@param trxName transaction
*/
public X_C_DocTypeCounter (Properties ctx, int C_DocTypeCounter_ID, String trxName)
{
super (ctx, C_DocTypeCounter_ID, trxName);
/** if (C_DocTypeCounter_ID == 0)
{
setC_DocTypeCounter_ID (0);
setC_DocType_ID (0);
setCounter_C_DocType_ID (0);
setIsCreateCounter (true);	// Y
setIsValid (false);
setName (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_DocTypeCounter (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=718 */
public static final int Table_ID=MTable.getTable_ID("C_DocTypeCounter");

/** TableName=C_DocTypeCounter */
public static final String Table_Name="C_DocTypeCounter";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"C_DocTypeCounter");

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
StringBuffer sb = new StringBuffer ("X_C_DocTypeCounter[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Counter Document.
@param C_DocTypeCounter_ID Counter Document Relationship */
public void setC_DocTypeCounter_ID (int C_DocTypeCounter_ID)
{
if (C_DocTypeCounter_ID < 1) throw new IllegalArgumentException ("C_DocTypeCounter_ID is mandatory.");
set_ValueNoCheck ("C_DocTypeCounter_ID", Integer.valueOf(C_DocTypeCounter_ID));
}
/** Get Counter Document.
@return Counter Document Relationship */
public int getC_DocTypeCounter_ID() 
{
Integer ii = (Integer)get_Value("C_DocTypeCounter_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_DocTypeCounter_ID */
public static final String COLUMNNAME_C_DocTypeCounter_ID = "C_DocTypeCounter_ID";
/** Set Document Type.
@param C_DocType_ID Document type or rules */
public void setC_DocType_ID (int C_DocType_ID)
{
if (C_DocType_ID < 0) throw new IllegalArgumentException ("C_DocType_ID is mandatory.");
set_Value ("C_DocType_ID", Integer.valueOf(C_DocType_ID));
}
/** Get Document Type.
@return Document type or rules */
public int getC_DocType_ID() 
{
Integer ii = (Integer)get_Value("C_DocType_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_DocType_ID */
public static final String COLUMNNAME_C_DocType_ID = "C_DocType_ID";

/** Counter_C_DocType_ID AD_Reference_ID=170 */
public static final int COUNTER_C_DOCTYPE_ID_AD_Reference_ID=170;
/** Set Counter Document Type.
@param Counter_C_DocType_ID Generated Counter Document Type (To) */
public void setCounter_C_DocType_ID (int Counter_C_DocType_ID)
{
if (Counter_C_DocType_ID < 1) throw new IllegalArgumentException ("Counter_C_DocType_ID is mandatory.");
set_Value ("Counter_C_DocType_ID", Integer.valueOf(Counter_C_DocType_ID));
}
/** Get Counter Document Type.
@return Generated Counter Document Type (To) */
public int getCounter_C_DocType_ID() 
{
Integer ii = (Integer)get_Value("Counter_C_DocType_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name Counter_C_DocType_ID */
public static final String COLUMNNAME_Counter_C_DocType_ID = "Counter_C_DocType_ID";
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

/** DocAction AD_Reference_ID=135 */
public static final int DOCACTION_AD_Reference_ID=135;
/** <None> = -- */
public static final String DOCACTION_None = "--";
/** Approve = AP */
public static final String DOCACTION_Approve = "AP";
/** Close = CL */
public static final String DOCACTION_Close = "CL";
/** Complete = CO */
public static final String DOCACTION_Complete = "CO";
/** Invalidate = IN */
public static final String DOCACTION_Invalidate = "IN";
/** Post = PO */
public static final String DOCACTION_Post = "PO";
/** Prepare = PR */
public static final String DOCACTION_Prepare = "PR";
/** Reverse - Accrual = RA */
public static final String DOCACTION_Reverse_Accrual = "RA";
/** Reverse - Correct = RC */
public static final String DOCACTION_Reverse_Correct = "RC";
/** Re-activate = RE */
public static final String DOCACTION_Re_Activate = "RE";
/** Reject = RJ */
public static final String DOCACTION_Reject = "RJ";
/** Void = VO */
public static final String DOCACTION_Void = "VO";
/** Wait Complete = WC */
public static final String DOCACTION_WaitComplete = "WC";
/** Unlock = XL */
public static final String DOCACTION_Unlock = "XL";
/** Set Document Action.
@param DocAction The targeted status of the document */
public void setDocAction (String DocAction)
{
if (DocAction == null || DocAction.equals("--") || DocAction.equals("AP") || DocAction.equals("CL") || DocAction.equals("CO") || DocAction.equals("IN") || DocAction.equals("PO") || DocAction.equals("PR") || DocAction.equals("RA") || DocAction.equals("RC") || DocAction.equals("RE") || DocAction.equals("RJ") || DocAction.equals("VO") || DocAction.equals("WC") || DocAction.equals("XL"));
 else throw new IllegalArgumentException ("DocAction Invalid value - " + DocAction + " - Reference_ID=135 - -- - AP - CL - CO - IN - PO - PR - RA - RC - RE - RJ - VO - WC - XL");
if (DocAction != null && DocAction.length() > 2)
{
log.warning("Length > 2 - truncated");
DocAction = DocAction.substring(0,1);
}
set_Value ("DocAction", DocAction);
}
/** Get Document Action.
@return The targeted status of the document */
public String getDocAction() 
{
return (String)get_Value("DocAction");
}
/** Column name DocAction */
public static final String COLUMNNAME_DocAction = "DocAction";
/** Set Create Counter Document.
@param IsCreateCounter Create Counter Document */
public void setIsCreateCounter (boolean IsCreateCounter)
{
set_Value ("IsCreateCounter", Boolean.valueOf(IsCreateCounter));
}
/** Get Create Counter Document.
@return Create Counter Document */
public boolean isCreateCounter() 
{
Object oo = get_Value("IsCreateCounter");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsCreateCounter */
public static final String COLUMNNAME_IsCreateCounter = "IsCreateCounter";
/** Set Valid.
@param IsValid Element is valid */
public void setIsValid (boolean IsValid)
{
set_Value ("IsValid", Boolean.valueOf(IsValid));
}
/** Get Valid.
@return Element is valid */
public boolean isValid() 
{
Object oo = get_Value("IsValid");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsValid */
public static final String COLUMNNAME_IsValid = "IsValid";
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
}
