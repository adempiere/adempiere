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
/** Generated Model for R_RequestType
 *  @author Adempiere (generated) 
 *  @version Release 3.1.5 - $Id$ */
public class X_R_RequestType extends PO
{
/** Standard Constructor
@param ctx context
@param R_RequestType_ID id
@param trxName transaction
*/
public X_R_RequestType (Properties ctx, int R_RequestType_ID, String trxName)
{
super (ctx, R_RequestType_ID, trxName);
/** if (R_RequestType_ID == 0)
{
setConfidentialType (null);	// C
setDueDateTolerance (0);	// 7
setIsAutoChangeRequest (false);
setIsConfidentialInfo (false);	// N
setIsDefault (false);	// N
setIsEMailWhenDue (false);
setIsEMailWhenOverdue (false);
setIsIndexed (false);
setIsSelfService (true);	// Y
setName (null);
setR_RequestType_ID (0);
setR_StatusCategory_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_R_RequestType (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=529 */
public static final int Table_ID=MTable.getTable_ID("R_RequestType");

/** TableName=R_RequestType */
public static final String Table_Name="R_RequestType";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"R_RequestType");

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
StringBuffer sb = new StringBuffer ("X_R_RequestType[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Auto Due Date Days.
@param AutoDueDateDays Automatic Due Date Days */
public void setAutoDueDateDays (int AutoDueDateDays)
{
set_Value ("AutoDueDateDays", Integer.valueOf(AutoDueDateDays));
}
/** Get Auto Due Date Days.
@return Automatic Due Date Days */
public int getAutoDueDateDays() 
{
Integer ii = (Integer)get_Value("AutoDueDateDays");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AutoDueDateDays */
public static final String COLUMNNAME_AutoDueDateDays = "AutoDueDateDays";

/** ConfidentialType AD_Reference_ID=340 */
public static final int CONFIDENTIALTYPE_AD_Reference_ID=340;
/** Public Information = A */
public static final String CONFIDENTIALTYPE_PublicInformation = "A";
/** Partner Confidential = C */
public static final String CONFIDENTIALTYPE_PartnerConfidential = "C";
/** Internal = I */
public static final String CONFIDENTIALTYPE_Internal = "I";
/** Private Information = P */
public static final String CONFIDENTIALTYPE_PrivateInformation = "P";
/** Set Confidentiality.
@param ConfidentialType Type of Confidentiality */
public void setConfidentialType (String ConfidentialType)
{
if (ConfidentialType == null) throw new IllegalArgumentException ("ConfidentialType is mandatory");
if (ConfidentialType.equals("A") || ConfidentialType.equals("C") || ConfidentialType.equals("I") || ConfidentialType.equals("P"));
 else throw new IllegalArgumentException ("ConfidentialType Invalid value - " + ConfidentialType + " - Reference_ID=340 - A - C - I - P");
if (ConfidentialType.length() > 1)
{
log.warning("Length > 1 - truncated");
ConfidentialType = ConfidentialType.substring(0,0);
}
set_Value ("ConfidentialType", ConfidentialType);
}
/** Get Confidentiality.
@return Type of Confidentiality */
public String getConfidentialType() 
{
return (String)get_Value("ConfidentialType");
}
/** Column name ConfidentialType */
public static final String COLUMNNAME_ConfidentialType = "ConfidentialType";
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
/** Set Due Date Tolerance.
@param DueDateTolerance Tolerance in days between the Date Next Action and the date the request is regarded as overdue */
public void setDueDateTolerance (int DueDateTolerance)
{
set_Value ("DueDateTolerance", Integer.valueOf(DueDateTolerance));
}
/** Get Due Date Tolerance.
@return Tolerance in days between the Date Next Action and the date the request is regarded as overdue */
public int getDueDateTolerance() 
{
Integer ii = (Integer)get_Value("DueDateTolerance");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name DueDateTolerance */
public static final String COLUMNNAME_DueDateTolerance = "DueDateTolerance";
/** Set Create Change Request.
@param IsAutoChangeRequest Automatically create BOM (Engineering) Change Request */
public void setIsAutoChangeRequest (boolean IsAutoChangeRequest)
{
set_Value ("IsAutoChangeRequest", Boolean.valueOf(IsAutoChangeRequest));
}
/** Get Create Change Request.
@return Automatically create BOM (Engineering) Change Request */
public boolean isAutoChangeRequest() 
{
Object oo = get_Value("IsAutoChangeRequest");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsAutoChangeRequest */
public static final String COLUMNNAME_IsAutoChangeRequest = "IsAutoChangeRequest";
/** Set Confidential Info.
@param IsConfidentialInfo Can enter confidential information */
public void setIsConfidentialInfo (boolean IsConfidentialInfo)
{
set_Value ("IsConfidentialInfo", Boolean.valueOf(IsConfidentialInfo));
}
/** Get Confidential Info.
@return Can enter confidential information */
public boolean isConfidentialInfo() 
{
Object oo = get_Value("IsConfidentialInfo");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsConfidentialInfo */
public static final String COLUMNNAME_IsConfidentialInfo = "IsConfidentialInfo";
/** Set Default.
@param IsDefault Default value */
public void setIsDefault (boolean IsDefault)
{
set_Value ("IsDefault", Boolean.valueOf(IsDefault));
}
/** Get Default.
@return Default value */
public boolean isDefault() 
{
Object oo = get_Value("IsDefault");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsDefault */
public static final String COLUMNNAME_IsDefault = "IsDefault";
/** Set EMail when Due.
@param IsEMailWhenDue Send EMail when Request becomes due */
public void setIsEMailWhenDue (boolean IsEMailWhenDue)
{
set_Value ("IsEMailWhenDue", Boolean.valueOf(IsEMailWhenDue));
}
/** Get EMail when Due.
@return Send EMail when Request becomes due */
public boolean isEMailWhenDue() 
{
Object oo = get_Value("IsEMailWhenDue");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsEMailWhenDue */
public static final String COLUMNNAME_IsEMailWhenDue = "IsEMailWhenDue";
/** Set EMail when Overdue.
@param IsEMailWhenOverdue Send EMail when Request becomes overdue */
public void setIsEMailWhenOverdue (boolean IsEMailWhenOverdue)
{
set_Value ("IsEMailWhenOverdue", Boolean.valueOf(IsEMailWhenOverdue));
}
/** Get EMail when Overdue.
@return Send EMail when Request becomes overdue */
public boolean isEMailWhenOverdue() 
{
Object oo = get_Value("IsEMailWhenOverdue");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsEMailWhenOverdue */
public static final String COLUMNNAME_IsEMailWhenOverdue = "IsEMailWhenOverdue";
/** Set Indexed.
@param IsIndexed Index the document for the internal search engine */
public void setIsIndexed (boolean IsIndexed)
{
set_Value ("IsIndexed", Boolean.valueOf(IsIndexed));
}
/** Get Indexed.
@return Index the document for the internal search engine */
public boolean isIndexed() 
{
Object oo = get_Value("IsIndexed");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsIndexed */
public static final String COLUMNNAME_IsIndexed = "IsIndexed";
/** Set Invoiced.
@param IsInvoiced Is this invoiced? */
public void setIsInvoiced (boolean IsInvoiced)
{
set_Value ("IsInvoiced", Boolean.valueOf(IsInvoiced));
}
/** Get Invoiced.
@return Is this invoiced? */
public boolean isInvoiced() 
{
Object oo = get_Value("IsInvoiced");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsInvoiced */
public static final String COLUMNNAME_IsInvoiced = "IsInvoiced";
/** Set Self-Service.
@param IsSelfService This is a Self-Service entry or this entry can be changed via Self-Service */
public void setIsSelfService (boolean IsSelfService)
{
set_Value ("IsSelfService", Boolean.valueOf(IsSelfService));
}
/** Get Self-Service.
@return This is a Self-Service entry or this entry can be changed via Self-Service */
public boolean isSelfService() 
{
Object oo = get_Value("IsSelfService");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsSelfService */
public static final String COLUMNNAME_IsSelfService = "IsSelfService";
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
/** Set Request Type.
@param R_RequestType_ID Type of request (e.g. Inquiry, Complaint, ..) */
public void setR_RequestType_ID (int R_RequestType_ID)
{
if (R_RequestType_ID < 1) throw new IllegalArgumentException ("R_RequestType_ID is mandatory.");
set_ValueNoCheck ("R_RequestType_ID", Integer.valueOf(R_RequestType_ID));
}
/** Get Request Type.
@return Type of request (e.g. Inquiry, Complaint, ..) */
public int getR_RequestType_ID() 
{
Integer ii = (Integer)get_Value("R_RequestType_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name R_RequestType_ID */
public static final String COLUMNNAME_R_RequestType_ID = "R_RequestType_ID";
/** Set Status Category.
@param R_StatusCategory_ID Request Status Category */
public void setR_StatusCategory_ID (int R_StatusCategory_ID)
{
if (R_StatusCategory_ID < 1) throw new IllegalArgumentException ("R_StatusCategory_ID is mandatory.");
set_Value ("R_StatusCategory_ID", Integer.valueOf(R_StatusCategory_ID));
}
/** Get Status Category.
@return Request Status Category */
public int getR_StatusCategory_ID() 
{
Integer ii = (Integer)get_Value("R_StatusCategory_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name R_StatusCategory_ID */
public static final String COLUMNNAME_R_StatusCategory_ID = "R_StatusCategory_ID";
}
