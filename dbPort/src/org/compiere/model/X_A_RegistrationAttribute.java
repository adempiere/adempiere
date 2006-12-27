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
/** Generated Model for A_RegistrationAttribute
 *  @author Jorg Janke (generated) 
 *  @version Release 3.1.3 - $Id$ */
public class X_A_RegistrationAttribute extends PO
{
/** Standard Constructor
@param ctx context
@param A_RegistrationAttribute_ID id
@param trxName transaction
*/
public X_A_RegistrationAttribute (Properties ctx, int A_RegistrationAttribute_ID, String trxName)
{
super (ctx, A_RegistrationAttribute_ID, trxName);
/** if (A_RegistrationAttribute_ID == 0)
{
setAD_Reference_ID (0);
setA_RegistrationAttribute_ID (0);
setIsSelfService (true);	// Y
setName (null);
setSeqNo (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_A_RegistrationAttribute (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=652 */
public static final int Table_ID=MTable.getTable_ID("A_RegistrationAttribute");

/** TableName=A_RegistrationAttribute */
public static final String Table_Name="A_RegistrationAttribute";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"A_RegistrationAttribute");

protected BigDecimal accessLevel = new BigDecimal(2);
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
StringBuffer sb = new StringBuffer ("X_A_RegistrationAttribute[").append(get_ID()).append("]");
return sb.toString();
}

/** AD_Reference_ID AD_Reference_ID=1 */
public static final int AD_REFERENCE_ID_AD_Reference_ID=1;
/** Set Reference.
@param AD_Reference_ID System Reference and Validation */
public void setAD_Reference_ID (int AD_Reference_ID)
{
if (AD_Reference_ID < 1) throw new IllegalArgumentException ("AD_Reference_ID is mandatory.");
set_Value ("AD_Reference_ID", Integer.valueOf(AD_Reference_ID));
}
/** Get Reference.
@return System Reference and Validation */
public int getAD_Reference_ID() 
{
Integer ii = (Integer)get_Value("AD_Reference_ID");
if (ii == null) return 0;
return ii.intValue();
}

/** AD_Reference_Value_ID AD_Reference_ID=4 */
public static final int AD_REFERENCE_VALUE_ID_AD_Reference_ID=4;
/** Set Reference Key.
@param AD_Reference_Value_ID Required to specify, if data type is Table or List */
public void setAD_Reference_Value_ID (int AD_Reference_Value_ID)
{
if (AD_Reference_Value_ID <= 0) set_Value ("AD_Reference_Value_ID", null);
 else 
set_Value ("AD_Reference_Value_ID", Integer.valueOf(AD_Reference_Value_ID));
}
/** Get Reference Key.
@return Required to specify, if data type is Table or List */
public int getAD_Reference_Value_ID() 
{
Integer ii = (Integer)get_Value("AD_Reference_Value_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Registration Attribute.
@param A_RegistrationAttribute_ID Asset Registration Attribute */
public void setA_RegistrationAttribute_ID (int A_RegistrationAttribute_ID)
{
if (A_RegistrationAttribute_ID < 1) throw new IllegalArgumentException ("A_RegistrationAttribute_ID is mandatory.");
set_ValueNoCheck ("A_RegistrationAttribute_ID", Integer.valueOf(A_RegistrationAttribute_ID));
}
/** Get Registration Attribute.
@return Asset Registration Attribute */
public int getA_RegistrationAttribute_ID() 
{
Integer ii = (Integer)get_Value("A_RegistrationAttribute_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set DB Column Name.
@param ColumnName Name of the column in the database */
public void setColumnName (String ColumnName)
{
if (ColumnName != null && ColumnName.length() > 40)
{
log.warning("Length > 40 - truncated");
ColumnName = ColumnName.substring(0,39);
}
set_Value ("ColumnName", ColumnName);
}
/** Get DB Column Name.
@return Name of the column in the database */
public String getColumnName() 
{
return (String)get_Value("ColumnName");
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
/** Set Sequence.
@param SeqNo Method of ordering records;
 lowest number comes first */
public void setSeqNo (int SeqNo)
{
set_Value ("SeqNo", Integer.valueOf(SeqNo));
}
/** Get Sequence.
@return Method of ordering records;
 lowest number comes first */
public int getSeqNo() 
{
Integer ii = (Integer)get_Value("SeqNo");
if (ii == null) return 0;
return ii.intValue();
}
}
