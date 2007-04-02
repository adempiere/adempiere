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
/** Generated Model for AD_Package_Exp
 *  @author Adempiere (generated) 
 *  @version Release 3.1.6 - $Id$ */
public class X_AD_Package_Exp extends PO
{
/** Standard Constructor
@param ctx context
@param AD_Package_Exp_ID id
@param trxName transaction
*/
public X_AD_Package_Exp (Properties ctx, int AD_Package_Exp_ID, String trxName)
{
super (ctx, AD_Package_Exp_ID, trxName);
/** if (AD_Package_Exp_ID == 0)
{
setAD_Package_Exp_ID (0);
setDescription (null);
setEMail (null);
setFile_Directory (null);
setInstructions (null);
setName (null);
setPK_Version (null);
setProcessing (false);
setReleaseNo (null);
setUserName (null);
setVersion (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_Package_Exp (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=50005 */
public static final int Table_ID=MTable.getTable_ID("AD_Package_Exp");

/** TableName=AD_Package_Exp */
public static final String Table_Name="AD_Package_Exp";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"AD_Package_Exp");

protected BigDecimal accessLevel = BigDecimal.valueOf(4);
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
StringBuffer sb = new StringBuffer ("X_AD_Package_Exp[").append(get_ID()).append("]");
return sb.toString();
}
/** Set AD_Package_Exp_ID.
@param AD_Package_Exp_ID AD_Package_Exp_ID */
public void setAD_Package_Exp_ID (int AD_Package_Exp_ID)
{
if (AD_Package_Exp_ID < 1) throw new IllegalArgumentException ("AD_Package_Exp_ID is mandatory.");
set_Value ("AD_Package_Exp_ID", Integer.valueOf(AD_Package_Exp_ID));
}
/** Get AD_Package_Exp_ID.
@return AD_Package_Exp_ID */
public int getAD_Package_Exp_ID() 
{
Integer ii = (Integer)get_Value("AD_Package_Exp_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getAD_Package_Exp_ID()));
}
/** Column name AD_Package_Exp_ID */
public static final String COLUMNNAME_AD_Package_Exp_ID = "AD_Package_Exp_ID";

/** AD_Package_Type AD_Reference_ID=50001 */
public static final int AD_PACKAGE_TYPE_AD_Reference_ID=50001;
/** Local Transfer = L */
public static final String AD_PACKAGE_TYPE_LocalTransfer = "L";
/** Remote Transfer = R */
public static final String AD_PACKAGE_TYPE_RemoteTransfer = "R";
/** XML File = X */
public static final String AD_PACKAGE_TYPE_XMLFile = "X";
/** Set AD_Package_Type.
@param AD_Package_Type AD_Package_Type */
public void setAD_Package_Type (String AD_Package_Type)
{
if (AD_Package_Type == null || AD_Package_Type.equals("L") || AD_Package_Type.equals("R") || AD_Package_Type.equals("X"));
 else throw new IllegalArgumentException ("AD_Package_Type Invalid value - " + AD_Package_Type + " - Reference_ID=50001 - L - R - X");
if (AD_Package_Type != null && AD_Package_Type.length() > 1)
{
log.warning("Length > 1 - truncated");
AD_Package_Type = AD_Package_Type.substring(0,0);
}
set_Value ("AD_Package_Type", AD_Package_Type);
}
/** Get AD_Package_Type.
@return AD_Package_Type */
public String getAD_Package_Type() 
{
return (String)get_Value("AD_Package_Type");
}
/** Column name AD_Package_Type */
public static final String COLUMNNAME_AD_Package_Type = "AD_Package_Type";
/** Set Description.
@param Description Optional short description of the record */
public void setDescription (String Description)
{
if (Description == null) throw new IllegalArgumentException ("Description is mandatory.");
if (Description.length() > 1000)
{
log.warning("Length > 1000 - truncated");
Description = Description.substring(0,999);
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
/** Set EMail Address.
@param EMail Electronic Mail Address */
public void setEMail (String EMail)
{
if (EMail == null) throw new IllegalArgumentException ("EMail is mandatory.");
if (EMail.length() > 30)
{
log.warning("Length > 30 - truncated");
EMail = EMail.substring(0,29);
}
set_Value ("EMail", EMail);
}
/** Get EMail Address.
@return Electronic Mail Address */
public String getEMail() 
{
return (String)get_Value("EMail");
}
/** Column name EMail */
public static final String COLUMNNAME_EMail = "EMail";
/** Set File_Directory.
@param File_Directory File_Directory */
public void setFile_Directory (String File_Directory)
{
if (File_Directory == null) throw new IllegalArgumentException ("File_Directory is mandatory.");
if (File_Directory.length() > 255)
{
log.warning("Length > 255 - truncated");
File_Directory = File_Directory.substring(0,254);
}
set_Value ("File_Directory", File_Directory);
}
/** Get File_Directory.
@return File_Directory */
public String getFile_Directory() 
{
return (String)get_Value("File_Directory");
}
/** Column name File_Directory */
public static final String COLUMNNAME_File_Directory = "File_Directory";
/** Set Instructions.
@param Instructions Instructions */
public void setInstructions (String Instructions)
{
if (Instructions == null) throw new IllegalArgumentException ("Instructions is mandatory.");
if (Instructions.length() > 1000)
{
log.warning("Length > 1000 - truncated");
Instructions = Instructions.substring(0,999);
}
set_Value ("Instructions", Instructions);
}
/** Get Instructions.
@return Instructions */
public String getInstructions() 
{
return (String)get_Value("Instructions");
}
/** Column name Instructions */
public static final String COLUMNNAME_Instructions = "Instructions";
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
/** Set PK_Version.
@param PK_Version PK_Version */
public void setPK_Version (String PK_Version)
{
if (PK_Version == null) throw new IllegalArgumentException ("PK_Version is mandatory.");
if (PK_Version.length() > 20)
{
log.warning("Length > 20 - truncated");
PK_Version = PK_Version.substring(0,19);
}
set_Value ("PK_Version", PK_Version);
}
/** Get PK_Version.
@return PK_Version */
public String getPK_Version() 
{
return (String)get_Value("PK_Version");
}
/** Column name PK_Version */
public static final String COLUMNNAME_PK_Version = "PK_Version";
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

/** ReleaseNo AD_Reference_ID=50002 */
public static final int RELEASENO_AD_Reference_ID=50002;
/** Release 2.5.2a = Release 2.5.2a */
public static final String RELEASENO_Release252a = "Release 2.5.2a";
/** Release 2.5.2b = Release 2.5.2b */
public static final String RELEASENO_Release252b = "Release 2.5.2b";
/** Release 2.5.2c = Release 2.5.2c */
public static final String RELEASENO_Release252c = "Release 2.5.2c";
/** Release 2.5.2d = Release 2.5.2d */
public static final String RELEASENO_Release252d = "Release 2.5.2d";
/** Release 2.5.2e = Release 2.5.2e */
public static final String RELEASENO_Release252e = "Release 2.5.2e";
/** Release 2.5.3a = Release 2.5.3a */
public static final String RELEASENO_Release253a = "Release 2.5.3a";
/** Release 2.5.3b = Release 2.5.3b */
public static final String RELEASENO_Release253b = "Release 2.5.3b";
/** No specific release = all */
public static final String RELEASENO_NoSpecificRelease = "all";
/** Set Release No.
@param ReleaseNo Internal Release Number */
public void setReleaseNo (String ReleaseNo)
{
if (ReleaseNo == null) throw new IllegalArgumentException ("ReleaseNo is mandatory");
if (ReleaseNo.equals("Release 2.5.2a") || ReleaseNo.equals("Release 2.5.2b") || ReleaseNo.equals("Release 2.5.2c") || ReleaseNo.equals("Release 2.5.2d") || ReleaseNo.equals("Release 2.5.2e") || ReleaseNo.equals("Release 2.5.3a") || ReleaseNo.equals("Release 2.5.3b") || ReleaseNo.equals("all"));
 else throw new IllegalArgumentException ("ReleaseNo Invalid value - " + ReleaseNo + " - Reference_ID=50002 - Release 2.5.2a - Release 2.5.2b - Release 2.5.2c - Release 2.5.2d - Release 2.5.2e - Release 2.5.3a - Release 2.5.3b - all");
if (ReleaseNo.length() > 20)
{
log.warning("Length > 20 - truncated");
ReleaseNo = ReleaseNo.substring(0,19);
}
set_Value ("ReleaseNo", ReleaseNo);
}
/** Get Release No.
@return Internal Release Number */
public String getReleaseNo() 
{
return (String)get_Value("ReleaseNo");
}
/** Column name ReleaseNo */
public static final String COLUMNNAME_ReleaseNo = "ReleaseNo";
/** Set Registered EMail.
@param UserName Email of the responsible for the System */
public void setUserName (String UserName)
{
if (UserName == null) throw new IllegalArgumentException ("UserName is mandatory.");
if (UserName.length() > 30)
{
log.warning("Length > 30 - truncated");
UserName = UserName.substring(0,29);
}
set_Value ("UserName", UserName);
}
/** Get Registered EMail.
@return Email of the responsible for the System */
public String getUserName() 
{
return (String)get_Value("UserName");
}
/** Column name UserName */
public static final String COLUMNNAME_UserName = "UserName";
/** Set Version.
@param Version Version of the table definition */
public void setVersion (String Version)
{
if (Version == null) throw new IllegalArgumentException ("Version is mandatory.");
if (Version.length() > 20)
{
log.warning("Length > 20 - truncated");
Version = Version.substring(0,19);
}
set_Value ("Version", Version);
}
/** Get Version.
@return Version of the table definition */
public String getVersion() 
{
return (String)get_Value("Version");
}
/** Column name Version */
public static final String COLUMNNAME_Version = "Version";
}
