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
/** Generated Model for R_IssueSystem
 *  @author Adempiere (generated) 
 *  @version Release 3.1.5 - $Id$ */
public class X_R_IssueSystem extends PO
{
/** Standard Constructor
@param ctx context
@param R_IssueSystem_ID id
@param trxName transaction
*/
public X_R_IssueSystem (Properties ctx, int R_IssueSystem_ID, String trxName)
{
super (ctx, R_IssueSystem_ID, trxName);
/** if (R_IssueSystem_ID == 0)
{
setDBAddress (null);
setR_IssueSystem_ID (0);
setSystemStatus (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_R_IssueSystem (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=843 */
public static final int Table_ID=MTable.getTable_ID("R_IssueSystem");

/** TableName=R_IssueSystem */
public static final String Table_Name="R_IssueSystem";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"R_IssueSystem");

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
StringBuffer sb = new StringBuffer ("X_R_IssueSystem[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Asset.
@param A_Asset_ID Asset used internally or by customers */
public void setA_Asset_ID (int A_Asset_ID)
{
if (A_Asset_ID <= 0) set_Value ("A_Asset_ID", null);
 else 
set_Value ("A_Asset_ID", Integer.valueOf(A_Asset_ID));
}
/** Get Asset.
@return Asset used internally or by customers */
public int getA_Asset_ID() 
{
Integer ii = (Integer)get_Value("A_Asset_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name A_Asset_ID */
public static final String COLUMNNAME_A_Asset_ID = "A_Asset_ID";
/** Set DB Address.
@param DBAddress JDBC URL of the database server */
public void setDBAddress (String DBAddress)
{
if (DBAddress == null) throw new IllegalArgumentException ("DBAddress is mandatory.");
if (DBAddress.length() > 255)
{
log.warning("Length > 255 - truncated");
DBAddress = DBAddress.substring(0,254);
}
set_ValueNoCheck ("DBAddress", DBAddress);
}
/** Get DB Address.
@return JDBC URL of the database server */
public String getDBAddress() 
{
return (String)get_Value("DBAddress");
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), getDBAddress());
}
/** Column name DBAddress */
public static final String COLUMNNAME_DBAddress = "DBAddress";
/** Set Profile.
@param ProfileInfo Information to help profiling the system for solving support issues */
public void setProfileInfo (String ProfileInfo)
{
if (ProfileInfo != null && ProfileInfo.length() > 60)
{
log.warning("Length > 60 - truncated");
ProfileInfo = ProfileInfo.substring(0,59);
}
set_ValueNoCheck ("ProfileInfo", ProfileInfo);
}
/** Get Profile.
@return Information to help profiling the system for solving support issues */
public String getProfileInfo() 
{
return (String)get_Value("ProfileInfo");
}
/** Column name ProfileInfo */
public static final String COLUMNNAME_ProfileInfo = "ProfileInfo";
/** Set Issue System.
@param R_IssueSystem_ID System creating the issue */
public void setR_IssueSystem_ID (int R_IssueSystem_ID)
{
if (R_IssueSystem_ID < 1) throw new IllegalArgumentException ("R_IssueSystem_ID is mandatory.");
set_ValueNoCheck ("R_IssueSystem_ID", Integer.valueOf(R_IssueSystem_ID));
}
/** Get Issue System.
@return System creating the issue */
public int getR_IssueSystem_ID() 
{
Integer ii = (Integer)get_Value("R_IssueSystem_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name R_IssueSystem_ID */
public static final String COLUMNNAME_R_IssueSystem_ID = "R_IssueSystem_ID";
/** Set Statistics.
@param StatisticsInfo Information to help profiling the system for solving support issues */
public void setStatisticsInfo (String StatisticsInfo)
{
if (StatisticsInfo != null && StatisticsInfo.length() > 60)
{
log.warning("Length > 60 - truncated");
StatisticsInfo = StatisticsInfo.substring(0,59);
}
set_ValueNoCheck ("StatisticsInfo", StatisticsInfo);
}
/** Get Statistics.
@return Information to help profiling the system for solving support issues */
public String getStatisticsInfo() 
{
return (String)get_Value("StatisticsInfo");
}
/** Column name StatisticsInfo */
public static final String COLUMNNAME_StatisticsInfo = "StatisticsInfo";

/** SystemStatus AD_Reference_ID=374 */
public static final int SYSTEMSTATUS_AD_Reference_ID=374;
/** Evaluation = E */
public static final String SYSTEMSTATUS_Evaluation = "E";
/** Implementation = I */
public static final String SYSTEMSTATUS_Implementation = "I";
/** Production = P */
public static final String SYSTEMSTATUS_Production = "P";
/** Set System Status.
@param SystemStatus Status of the system - Support priority depends on system status */
public void setSystemStatus (String SystemStatus)
{
if (SystemStatus == null) throw new IllegalArgumentException ("SystemStatus is mandatory");
if (SystemStatus.equals("E") || SystemStatus.equals("I") || SystemStatus.equals("P"));
 else throw new IllegalArgumentException ("SystemStatus Invalid value - " + SystemStatus + " - Reference_ID=374 - E - I - P");
if (SystemStatus.length() > 1)
{
log.warning("Length > 1 - truncated");
SystemStatus = SystemStatus.substring(0,0);
}
set_Value ("SystemStatus", SystemStatus);
}
/** Get System Status.
@return Status of the system - Support priority depends on system status */
public String getSystemStatus() 
{
return (String)get_Value("SystemStatus");
}
/** Column name SystemStatus */
public static final String COLUMNNAME_SystemStatus = "SystemStatus";
}
