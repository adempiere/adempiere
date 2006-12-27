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
/** Generated Model for AD_Package_Exp_Detail
 *  @author Jorg Janke (generated) 
 *  @version Release 3.1.3 - $Id$ */
public class X_AD_Package_Exp_Detail extends PO
{
/** Standard Constructor
@param ctx context
@param AD_Package_Exp_Detail_ID id
@param trxName transaction
*/
public X_AD_Package_Exp_Detail (Properties ctx, int AD_Package_Exp_Detail_ID, String trxName)
{
super (ctx, AD_Package_Exp_Detail_ID, trxName);
/** if (AD_Package_Exp_Detail_ID == 0)
{
setAD_Package_Exp_Detail_ID (0);
setAD_Package_Exp_ID (0);
setDescription (null);
setPK_Name (null);	// @SQL=SELECT PK_Name FROM AD_Package_Exp_Detail WHERE AD_Package_Exp_ID=@AD_Package_Exp_ID@
setProcessing (false);
setType (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_Package_Exp_Detail (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=50006 */
public static final int Table_ID=MTable.getTable_ID("AD_Package_Exp_Detail");

/** TableName=AD_Package_Exp_Detail */
public static final String Table_Name="AD_Package_Exp_Detail";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"AD_Package_Exp_Detail");

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
StringBuffer sb = new StringBuffer ("X_AD_Package_Exp_Detail[").append(get_ID()).append("]");
return sb.toString();
}
/** Set AD_Form_ID.
@param AD_Form_ID AD_Form_ID */
public void setAD_Form_ID (int AD_Form_ID)
{
if (AD_Form_ID <= 0) set_Value ("AD_Form_ID", null);
 else 
set_Value ("AD_Form_ID", Integer.valueOf(AD_Form_ID));
}
/** Get AD_Form_ID.
@return AD_Form_ID */
public int getAD_Form_ID() 
{
Integer ii = (Integer)get_Value("AD_Form_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set AD_ImpFormat_ID.
@param AD_ImpFormat_ID AD_ImpFormat_ID */
public void setAD_ImpFormat_ID (int AD_ImpFormat_ID)
{
if (AD_ImpFormat_ID <= 0) set_Value ("AD_ImpFormat_ID", null);
 else 
set_Value ("AD_ImpFormat_ID", Integer.valueOf(AD_ImpFormat_ID));
}
/** Get AD_ImpFormat_ID.
@return AD_ImpFormat_ID */
public int getAD_ImpFormat_ID() 
{
Integer ii = (Integer)get_Value("AD_ImpFormat_ID");
if (ii == null) return 0;
return ii.intValue();
}

/** AD_Menu_ID AD_Reference_ID=105 */
public static final int AD_MENU_ID_AD_Reference_ID=105;
/** Set AD_Menu_ID.
@param AD_Menu_ID AD_Menu_ID */
public void setAD_Menu_ID (int AD_Menu_ID)
{
if (AD_Menu_ID <= 0) set_Value ("AD_Menu_ID", null);
 else 
set_Value ("AD_Menu_ID", Integer.valueOf(AD_Menu_ID));
}
/** Get AD_Menu_ID.
@return AD_Menu_ID */
public int getAD_Menu_ID() 
{
Integer ii = (Integer)get_Value("AD_Menu_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set AD_Package_Code_New.
@param AD_Package_Code_New AD_Package_Code_New */
public void setAD_Package_Code_New (String AD_Package_Code_New)
{
if (AD_Package_Code_New != null && AD_Package_Code_New.length() > 2000)
{
log.warning("Length > 2000 - truncated");
AD_Package_Code_New = AD_Package_Code_New.substring(0,1999);
}
set_Value ("AD_Package_Code_New", AD_Package_Code_New);
}
/** Get AD_Package_Code_New.
@return AD_Package_Code_New */
public String getAD_Package_Code_New() 
{
return (String)get_Value("AD_Package_Code_New");
}
/** Set AD_Package_Code_Old.
@param AD_Package_Code_Old AD_Package_Code_Old */
public void setAD_Package_Code_Old (String AD_Package_Code_Old)
{
if (AD_Package_Code_Old != null && AD_Package_Code_Old.length() > 2000)
{
log.warning("Length > 2000 - truncated");
AD_Package_Code_Old = AD_Package_Code_Old.substring(0,1999);
}
set_Value ("AD_Package_Code_Old", AD_Package_Code_Old);
}
/** Get AD_Package_Code_Old.
@return AD_Package_Code_Old */
public String getAD_Package_Code_Old() 
{
return (String)get_Value("AD_Package_Code_Old");
}
/** Set AD_Package_Exp_Detail_ID.
@param AD_Package_Exp_Detail_ID AD_Package_Exp_Detail_ID */
public void setAD_Package_Exp_Detail_ID (int AD_Package_Exp_Detail_ID)
{
if (AD_Package_Exp_Detail_ID < 1) throw new IllegalArgumentException ("AD_Package_Exp_Detail_ID is mandatory.");
set_Value ("AD_Package_Exp_Detail_ID", Integer.valueOf(AD_Package_Exp_Detail_ID));
}
/** Get AD_Package_Exp_Detail_ID.
@return AD_Package_Exp_Detail_ID */
public int getAD_Package_Exp_Detail_ID() 
{
Integer ii = (Integer)get_Value("AD_Package_Exp_Detail_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getAD_Package_Exp_Detail_ID()));
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
/** Set AD_Process_ID.
@param AD_Process_ID AD_Process_ID */
public void setAD_Process_ID (int AD_Process_ID)
{
if (AD_Process_ID <= 0) set_Value ("AD_Process_ID", null);
 else 
set_Value ("AD_Process_ID", Integer.valueOf(AD_Process_ID));
}
/** Get AD_Process_ID.
@return AD_Process_ID */
public int getAD_Process_ID() 
{
Integer ii = (Integer)get_Value("AD_Process_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set AD_ReportView_ID.
@param AD_ReportView_ID Contain the Report View ID */
public void setAD_ReportView_ID (int AD_ReportView_ID)
{
if (AD_ReportView_ID <= 0) set_Value ("AD_ReportView_ID", null);
 else 
set_Value ("AD_ReportView_ID", Integer.valueOf(AD_ReportView_ID));
}
/** Get AD_ReportView_ID.
@return Contain the Report View ID */
public int getAD_ReportView_ID() 
{
Integer ii = (Integer)get_Value("AD_ReportView_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set AD_Role_ID.
@param AD_Role_ID Role */
public void setAD_Role_ID (int AD_Role_ID)
{
if (AD_Role_ID <= 0) set_Value ("AD_Role_ID", null);
 else 
set_Value ("AD_Role_ID", Integer.valueOf(AD_Role_ID));
}
/** Get AD_Role_ID.
@return Role */
public int getAD_Role_ID() 
{
Integer ii = (Integer)get_Value("AD_Role_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set AD_Table_ID.
@param AD_Table_ID AD_Table_ID */
public void setAD_Table_ID (int AD_Table_ID)
{
if (AD_Table_ID <= 0) set_Value ("AD_Table_ID", null);
 else 
set_Value ("AD_Table_ID", Integer.valueOf(AD_Table_ID));
}
/** Get AD_Table_ID.
@return AD_Table_ID */
public int getAD_Table_ID() 
{
Integer ii = (Integer)get_Value("AD_Table_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set AD_Window_ID.
@param AD_Window_ID AD_Window_ID */
public void setAD_Window_ID (int AD_Window_ID)
{
if (AD_Window_ID <= 0) set_Value ("AD_Window_ID", null);
 else 
set_Value ("AD_Window_ID", Integer.valueOf(AD_Window_ID));
}
/** Get AD_Window_ID.
@return AD_Window_ID */
public int getAD_Window_ID() 
{
Integer ii = (Integer)get_Value("AD_Window_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set AD_Workbench_ID.
@param AD_Workbench_ID AD_Workbench_ID */
public void setAD_Workbench_ID (int AD_Workbench_ID)
{
if (AD_Workbench_ID <= 0) set_Value ("AD_Workbench_ID", null);
 else 
set_Value ("AD_Workbench_ID", Integer.valueOf(AD_Workbench_ID));
}
/** Get AD_Workbench_ID.
@return AD_Workbench_ID */
public int getAD_Workbench_ID() 
{
Integer ii = (Integer)get_Value("AD_Workbench_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set AD_Workflow_ID.
@param AD_Workflow_ID AD_Workflow_ID */
public void setAD_Workflow_ID (int AD_Workflow_ID)
{
if (AD_Workflow_ID <= 0) set_Value ("AD_Workflow_ID", null);
 else 
set_Value ("AD_Workflow_ID", Integer.valueOf(AD_Workflow_ID));
}
/** Get AD_Workflow_ID.
@return AD_Workflow_ID */
public int getAD_Workflow_ID() 
{
Integer ii = (Integer)get_Value("AD_Workflow_ID");
if (ii == null) return 0;
return ii.intValue();
}

/** DBType AD_Reference_ID=50003 */
public static final int DBTYPE_AD_Reference_ID=50003;
/** All Database Types = ALL */
public static final String DBTYPE_AllDatabaseTypes = "ALL";
/** DB2 = DB2 */
public static final String DBTYPE_DB2 = "DB2";
/** Firebird = Firebird */
public static final String DBTYPE_Firebird = "Firebird";
/** MySQL = MySQL */
public static final String DBTYPE_MySQL = "MySQL";
/** Oracle = Oracle */
public static final String DBTYPE_Oracle = "Oracle";
/** Postgres = Postgres */
public static final String DBTYPE_Postgres = "Postgres";
/** SQL Server = SQL */
public static final String DBTYPE_SQLServer = "SQL";
/** Sybase = Sybase */
public static final String DBTYPE_Sybase = "Sybase";
/** Set DBType.
@param DBType Indicates type of database */
public void setDBType (String DBType)
{
if (DBType == null || DBType.equals("ALL") || DBType.equals("DB2") || DBType.equals("Firebird") || DBType.equals("MySQL") || DBType.equals("Oracle") || DBType.equals("Postgres") || DBType.equals("SQL") || DBType.equals("Sybase"));
 else throw new IllegalArgumentException ("DBType Invalid value - " + DBType + " - Reference_ID=50003 - ALL - DB2 - Firebird - MySQL - Oracle - Postgres - SQL - Sybase");
if (DBType != null && DBType.length() > 22)
{
log.warning("Length > 22 - truncated");
DBType = DBType.substring(0,21);
}
set_Value ("DBType", DBType);
}
/** Get DBType.
@return Indicates type of database */
public String getDBType() 
{
return (String)get_Value("DBType");
}
/** Set Description.
@param Description Description */
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
@return Description */
public String getDescription() 
{
return (String)get_Value("Description");
}
/** Set Destination_Directory.
@param Destination_Directory Destination_Directory */
public void setDestination_Directory (String Destination_Directory)
{
if (Destination_Directory != null && Destination_Directory.length() > 255)
{
log.warning("Length > 255 - truncated");
Destination_Directory = Destination_Directory.substring(0,254);
}
set_Value ("Destination_Directory", Destination_Directory);
}
/** Get Destination_Directory.
@return Destination_Directory */
public String getDestination_Directory() 
{
return (String)get_Value("Destination_Directory");
}
/** Set Destination_FileName.
@param Destination_FileName Destination_FileName */
public void setDestination_FileName (String Destination_FileName)
{
if (Destination_FileName != null && Destination_FileName.length() > 255)
{
log.warning("Length > 255 - truncated");
Destination_FileName = Destination_FileName.substring(0,254);
}
set_Value ("Destination_FileName", Destination_FileName);
}
/** Get Destination_FileName.
@return Destination_FileName */
public String getDestination_FileName() 
{
return (String)get_Value("Destination_FileName");
}
/** Set FileName.
@param FileName FileName */
public void setFileName (String FileName)
{
if (FileName != null && FileName.length() > 255)
{
log.warning("Length > 255 - truncated");
FileName = FileName.substring(0,254);
}
set_Value ("FileName", FileName);
}
/** Get FileName.
@return FileName */
public String getFileName() 
{
return (String)get_Value("FileName");
}
/** Set File_Directory.
@param File_Directory File_Directory */
public void setFile_Directory (String File_Directory)
{
if (File_Directory != null && File_Directory.length() > 255)
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
/** Set Line.
@param Line Line */
public void setLine (int Line)
{
set_Value ("Line", Integer.valueOf(Line));
}
/** Get Line.
@return Line */
public int getLine() 
{
Integer ii = (Integer)get_Value("Line");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Name2.
@param Name2 Name2 */
public void setName2 (String Name2)
{
if (Name2 != null && Name2.length() > 60)
{
log.warning("Length > 60 - truncated");
Name2 = Name2.substring(0,59);
}
set_Value ("Name2", Name2);
}
/** Get Name2.
@return Name2 */
public String getName2() 
{
return (String)get_Value("Name2");
}
/** Set PK_Name.
@param PK_Name Name of package */
public void setPK_Name (String PK_Name)
{
if (PK_Name == null) throw new IllegalArgumentException ("PK_Name is mandatory.");
if (PK_Name.length() > 60)
{
log.warning("Length > 60 - truncated");
PK_Name = PK_Name.substring(0,59);
}
set_Value ("PK_Name", PK_Name);
}
/** Get PK_Name.
@return Name of package */
public String getPK_Name() 
{
return (String)get_Value("PK_Name");
}
/** Set Processed.
@param Processed Processed */
public void setProcessed (boolean Processed)
{
set_Value ("Processed", Boolean.valueOf(Processed));
}
/** Get Processed.
@return Processed */
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
/** Set Processing.
@param Processing Processing */
public void setProcessing (boolean Processing)
{
set_Value ("Processing", Boolean.valueOf(Processing));
}
/** Get Processing.
@return Processing */
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
/** Set ReleaseNo.
@param ReleaseNo ReleaseNo */
public void setReleaseNo (String ReleaseNo)
{
if (ReleaseNo == null || ReleaseNo.equals("Release 2.5.2a") || ReleaseNo.equals("Release 2.5.2b") || ReleaseNo.equals("Release 2.5.2c") || ReleaseNo.equals("Release 2.5.2d") || ReleaseNo.equals("Release 2.5.2e") || ReleaseNo.equals("Release 2.5.3a") || ReleaseNo.equals("Release 2.5.3b") || ReleaseNo.equals("all"));
 else throw new IllegalArgumentException ("ReleaseNo Invalid value - " + ReleaseNo + " - Reference_ID=50002 - Release 2.5.2a - Release 2.5.2b - Release 2.5.2c - Release 2.5.2d - Release 2.5.2e - Release 2.5.3a - Release 2.5.3b - all");
if (ReleaseNo != null && ReleaseNo.length() > 20)
{
log.warning("Length > 20 - truncated");
ReleaseNo = ReleaseNo.substring(0,19);
}
set_Value ("ReleaseNo", ReleaseNo);
}
/** Get ReleaseNo.
@return ReleaseNo */
public String getReleaseNo() 
{
return (String)get_Value("ReleaseNo");
}
/** Set SQLStatement.
@param SQLStatement SQLStatement */
public void setSQLStatement (String SQLStatement)
{
if (SQLStatement != null && SQLStatement.length() > 2000)
{
log.warning("Length > 2000 - truncated");
SQLStatement = SQLStatement.substring(0,1999);
}
set_Value ("SQLStatement", SQLStatement);
}
/** Get SQLStatement.
@return SQLStatement */
public String getSQLStatement() 
{
return (String)get_Value("SQLStatement");
}
/** Set Target_Directory.
@param Target_Directory Directory where the file to be distributed will be copied to */
public void setTarget_Directory (String Target_Directory)
{
if (Target_Directory != null && Target_Directory.length() > 255)
{
log.warning("Length > 255 - truncated");
Target_Directory = Target_Directory.substring(0,254);
}
set_Value ("Target_Directory", Target_Directory);
}
/** Get Target_Directory.
@return Directory where the file to be distributed will be copied to */
public String getTarget_Directory() 
{
return (String)get_Value("Target_Directory");
}

/** Type AD_Reference_ID=50004 */
public static final int TYPE_AD_Reference_ID=50004;
/** Workbench = B */
public static final String TYPE_Workbench = "B";
/** File - Code or other = C */
public static final String TYPE_File_CodeOrOther = "C";
/** Data = D */
public static final String TYPE_Data = "D";
/** Workflow = F */
public static final String TYPE_Workflow = "F";
/** Import Format = IMP */
public static final String TYPE_ImportFormat = "IMP";
/** Application or Module = M */
public static final String TYPE_ApplicationOrModule = "M";
/** Process/Report = P */
public static final String TYPE_ProcessReport = "P";
/** ReportView = R */
public static final String TYPE_ReportView = "R";
/** Role = S */
public static final String TYPE_Role = "S";
/** Code Snipit = SNI */
public static final String TYPE_CodeSnipit = "SNI";
/** SQL Statement = SQL */
public static final String TYPE_SQLStatement = "SQL";
/** Table = T */
public static final String TYPE_Table = "T";
/** Window = W */
public static final String TYPE_Window = "W";
/** Form = X */
public static final String TYPE_Form = "X";
/** Set Type.
@param Type Type */
public void setType (String Type)
{
if (Type == null) throw new IllegalArgumentException ("Type is mandatory");
if (Type.equals("B") || Type.equals("C") || Type.equals("D") || Type.equals("F") || Type.equals("IMP") || Type.equals("M") || Type.equals("P") || Type.equals("R") || Type.equals("S") || Type.equals("SNI") || Type.equals("SQL") || Type.equals("T") || Type.equals("W") || Type.equals("X"));
 else throw new IllegalArgumentException ("Type Invalid value - " + Type + " - Reference_ID=50004 - B - C - D - F - IMP - M - P - R - S - SNI - SQL - T - W - X");
if (Type.length() > 10)
{
log.warning("Length > 10 - truncated");
Type = Type.substring(0,9);
}
set_Value ("Type", Type);
}
/** Get Type.
@return Type */
public String getType() 
{
return (String)get_Value("Type");
}
}
