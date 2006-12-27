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
/** Generated Model for AD_Package_Imp_Inst
 *  @author Jorg Janke (generated) 
 *  @version Release 3.1.3 - $Id$ */
public class X_AD_Package_Imp_Inst extends PO
{
/** Standard Constructor
@param ctx context
@param AD_Package_Imp_Inst_ID id
@param trxName transaction
*/
public X_AD_Package_Imp_Inst (Properties ctx, int AD_Package_Imp_Inst_ID, String trxName)
{
super (ctx, AD_Package_Imp_Inst_ID, trxName);
/** if (AD_Package_Imp_Inst_ID == 0)
{
setAD_PACKAGE_IMP_INST_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_Package_Imp_Inst (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=50001 */
public static final int Table_ID=MTable.getTable_ID("AD_Package_Imp_Inst");

/** TableName=AD_Package_Imp_Inst */
public static final String Table_Name="AD_Package_Imp_Inst";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"AD_Package_Imp_Inst");

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
StringBuffer sb = new StringBuffer ("X_AD_Package_Imp_Inst[").append(get_ID()).append("]");
return sb.toString();
}
/** Set AD_PACKAGE_IMP_INST_ID.
@param AD_PACKAGE_IMP_INST_ID AD_PACKAGE_IMP_INST_ID */
public void setAD_PACKAGE_IMP_INST_ID (int AD_PACKAGE_IMP_INST_ID)
{
if (AD_PACKAGE_IMP_INST_ID < 1) throw new IllegalArgumentException ("AD_PACKAGE_IMP_INST_ID is mandatory.");
set_ValueNoCheck ("AD_PACKAGE_IMP_INST_ID", Integer.valueOf(AD_PACKAGE_IMP_INST_ID));
}
/** Get AD_PACKAGE_IMP_INST_ID.
@return AD_PACKAGE_IMP_INST_ID */
public int getAD_PACKAGE_IMP_INST_ID() 
{
Integer ii = (Integer)get_Value("AD_PACKAGE_IMP_INST_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Creator.
@param Creator Creator */
public void setCreator (String Creator)
{
if (Creator != null && Creator.length() > 120)
{
log.warning("Length > 120 - truncated");
Creator = Creator.substring(0,119);
}
set_Value ("Creator", Creator);
}
/** Get Creator.
@return Creator */
public String getCreator() 
{
return (String)get_Value("Creator");
}
/** Set CreatorContact.
@param CreatorContact CreatorContact */
public void setCreatorContact (String CreatorContact)
{
if (CreatorContact != null && CreatorContact.length() > 510)
{
log.warning("Length > 510 - truncated");
CreatorContact = CreatorContact.substring(0,509);
}
set_Value ("CreatorContact", CreatorContact);
}
/** Get CreatorContact.
@return CreatorContact */
public String getCreatorContact() 
{
return (String)get_Value("CreatorContact");
}
/** Set Description.
@param Description Optional short description of the record */
public void setDescription (String Description)
{
if (Description != null && Description.length() > 2000)
{
log.warning("Length > 2000 - truncated");
Description = Description.substring(0,1999);
}
set_Value ("Description", Description);
}
/** Get Description.
@return Optional short description of the record */
public String getDescription() 
{
return (String)get_Value("Description");
}
/** Set EMail Address.
@param EMail Electronic Mail Address */
public void setEMail (String EMail)
{
if (EMail != null && EMail.length() > 120)
{
log.warning("Length > 120 - truncated");
EMail = EMail.substring(0,119);
}
set_Value ("EMail", EMail);
}
/** Get EMail Address.
@return Electronic Mail Address */
public String getEMail() 
{
return (String)get_Value("EMail");
}
/** Set Name.
@param Name Alphanumeric identifier of the entity */
public void setName (String Name)
{
if (Name != null && Name.length() > 240)
{
log.warning("Length > 240 - truncated");
Name = Name.substring(0,239);
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
/** Set PK_Status.
@param PK_Status PK_Status */
public void setPK_Status (String PK_Status)
{
if (PK_Status != null && PK_Status.length() > 44)
{
log.warning("Length > 44 - truncated");
PK_Status = PK_Status.substring(0,43);
}
set_Value ("PK_Status", PK_Status);
}
/** Get PK_Status.
@return PK_Status */
public String getPK_Status() 
{
return (String)get_Value("PK_Status");
}
/** Set PK_Version.
@param PK_Version PK_Version */
public void setPK_Version (String PK_Version)
{
if (PK_Version != null && PK_Version.length() > 40)
{
log.warning("Length > 40 - truncated");
PK_Version = PK_Version.substring(0,39);
}
set_Value ("PK_Version", PK_Version);
}
/** Get PK_Version.
@return PK_Version */
public String getPK_Version() 
{
return (String)get_Value("PK_Version");
}
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
/** Set Release No.
@param ReleaseNo Internal Release Number */
public void setReleaseNo (String ReleaseNo)
{
if (ReleaseNo != null && ReleaseNo.length() > 40)
{
log.warning("Length > 40 - truncated");
ReleaseNo = ReleaseNo.substring(0,39);
}
set_Value ("ReleaseNo", ReleaseNo);
}
/** Get Release No.
@return Internal Release Number */
public String getReleaseNo() 
{
return (String)get_Value("ReleaseNo");
}
/** Set Uninstall.
@param Uninstall Uninstall */
public void setUninstall (boolean Uninstall)
{
set_Value ("Uninstall", Boolean.valueOf(Uninstall));
}
/** Get Uninstall.
@return Uninstall */
public boolean isUninstall() 
{
Object oo = get_Value("Uninstall");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Version.
@param Version Version of the table definition */
public void setVersion (String Version)
{
if (Version != null && Version.length() > 40)
{
log.warning("Length > 40 - truncated");
Version = Version.substring(0,39);
}
set_Value ("Version", Version);
}
/** Get Version.
@return Version of the table definition */
public String getVersion() 
{
return (String)get_Value("Version");
}
}
