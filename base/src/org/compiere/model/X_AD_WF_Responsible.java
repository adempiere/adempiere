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
/** Generated Model for AD_WF_Responsible
 *  @author Adempiere (generated) 
 *  @version Release 3.3.0 - $Id$ */
public class X_AD_WF_Responsible extends PO
{
/** Standard Constructor
@param ctx context
@param AD_WF_Responsible_ID id
@param trxName transaction
*/
public X_AD_WF_Responsible (Properties ctx, int AD_WF_Responsible_ID, String trxName)
{
super (ctx, AD_WF_Responsible_ID, trxName);
/** if (AD_WF_Responsible_ID == 0)
{
setAD_Role_ID (0);
setAD_WF_Responsible_ID (0);
setEntityType (null);	// U
setName (null);
setResponsibleType (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_WF_Responsible (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** TableName=AD_WF_Responsible */
public static final String Table_Name="AD_WF_Responsible";

/** AD_Table_ID=646 */
public static final int Table_ID=MTable.getTable_ID(Table_Name);

protected static KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

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
StringBuffer sb = new StringBuffer ("X_AD_WF_Responsible[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Role.
@param AD_Role_ID Responsibility Role */
public void setAD_Role_ID (int AD_Role_ID)
{
if (AD_Role_ID < 0) throw new IllegalArgumentException ("AD_Role_ID is mandatory.");
set_Value ("AD_Role_ID", Integer.valueOf(AD_Role_ID));
}
/** Get Role.
@return Responsibility Role */
public int getAD_Role_ID() 
{
Integer ii = (Integer)get_Value("AD_Role_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_Role_ID */
public static final String COLUMNNAME_AD_Role_ID = "AD_Role_ID";

/** AD_User_ID AD_Reference_ID=286 */
public static final int AD_USER_ID_AD_Reference_ID=286;
/** Set User/Contact.
@param AD_User_ID User within the system - Internal or Business Partner Contact */
public void setAD_User_ID (int AD_User_ID)
{
if (AD_User_ID <= 0) set_Value ("AD_User_ID", null);
 else 
set_Value ("AD_User_ID", Integer.valueOf(AD_User_ID));
}
/** Get User/Contact.
@return User within the system - Internal or Business Partner Contact */
public int getAD_User_ID() 
{
Integer ii = (Integer)get_Value("AD_User_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_User_ID */
public static final String COLUMNNAME_AD_User_ID = "AD_User_ID";
/** Set Workflow Responsible.
@param AD_WF_Responsible_ID Responsible for Workflow Execution */
public void setAD_WF_Responsible_ID (int AD_WF_Responsible_ID)
{
if (AD_WF_Responsible_ID < 1) throw new IllegalArgumentException ("AD_WF_Responsible_ID is mandatory.");
set_ValueNoCheck ("AD_WF_Responsible_ID", Integer.valueOf(AD_WF_Responsible_ID));
}
/** Get Workflow Responsible.
@return Responsible for Workflow Execution */
public int getAD_WF_Responsible_ID() 
{
Integer ii = (Integer)get_Value("AD_WF_Responsible_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_WF_Responsible_ID */
public static final String COLUMNNAME_AD_WF_Responsible_ID = "AD_WF_Responsible_ID";
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

/** EntityType AD_Reference_ID=389 */
public static final int ENTITYTYPE_AD_Reference_ID=389;
/** Set Entity Type.
@param EntityType Dictionary Entity Type;
 Determines ownership and synchronization */
public void setEntityType (String EntityType)
{
if (EntityType.length() > 4)
{
log.warning("Length > 4 - truncated");
EntityType = EntityType.substring(0,3);
}
set_Value ("EntityType", EntityType);
}
/** Get Entity Type.
@return Dictionary Entity Type;
 Determines ownership and synchronization */
public String getEntityType() 
{
return (String)get_Value("EntityType");
}
/** Column name EntityType */
public static final String COLUMNNAME_EntityType = "EntityType";
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

/** ResponsibleType AD_Reference_ID=304 */
public static final int RESPONSIBLETYPE_AD_Reference_ID=304;
/** Human = H */
public static final String RESPONSIBLETYPE_Human = "H";
/** Organization = O */
public static final String RESPONSIBLETYPE_Organization = "O";
/** Role = R */
public static final String RESPONSIBLETYPE_Role = "R";
/** System Resource = S */
public static final String RESPONSIBLETYPE_SystemResource = "S";
/** Set Responsible Type.
@param ResponsibleType Type of the Responsibility for a workflow */
public void setResponsibleType (String ResponsibleType)
{
if (ResponsibleType == null) throw new IllegalArgumentException ("ResponsibleType is mandatory");
if (ResponsibleType.equals("H") || ResponsibleType.equals("O") || ResponsibleType.equals("R") || ResponsibleType.equals("S"));
 else throw new IllegalArgumentException ("ResponsibleType Invalid value - " + ResponsibleType + " - Reference_ID=304 - H - O - R - S");
if (ResponsibleType.length() > 1)
{
log.warning("Length > 1 - truncated");
ResponsibleType = ResponsibleType.substring(0,0);
}
set_Value ("ResponsibleType", ResponsibleType);
}
/** Get Responsible Type.
@return Type of the Responsibility for a workflow */
public String getResponsibleType() 
{
return (String)get_Value("ResponsibleType");
}
/** Column name ResponsibleType */
public static final String COLUMNNAME_ResponsibleType = "ResponsibleType";
}
