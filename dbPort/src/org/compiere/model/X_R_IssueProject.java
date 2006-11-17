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
/** Generated Model for R_IssueProject
 *  @author Jorg Janke (generated) 
 *  @version Release 2.5.3d - 2006-10-10 21:56:02.937 */
public class X_R_IssueProject extends PO
{
/** Standard Constructor
@param ctx context
@param R_IssueProject_ID id
@param trxName transaction
*/
public X_R_IssueProject (Properties ctx, int R_IssueProject_ID, String trxName)
{
super (ctx, R_IssueProject_ID, trxName);
/** if (R_IssueProject_ID == 0)
{
setName (null);
setR_IssueProject_ID (0);
setSystemStatus (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_R_IssueProject (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=842 */
public static final int Table_ID=842;

/** TableName=R_IssueProject */
public static final String Table_Name="R_IssueProject";

protected static KeyNamePair Model = new KeyNamePair(842,"R_IssueProject");

protected BigDecimal accessLevel = new BigDecimal(6);
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
StringBuffer sb = new StringBuffer ("X_R_IssueProject[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Asset.
@param A_Asset_ID Asset used internally or by customers */
public void setA_Asset_ID (int A_Asset_ID)
{
if (A_Asset_ID <= 0) set_Value ("A_Asset_ID", null);
 else 
set_Value ("A_Asset_ID", new Integer(A_Asset_ID));
}
/** Get Asset.
@return Asset used internally or by customers */
public int getA_Asset_ID() 
{
Integer ii = (Integer)get_Value("A_Asset_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Project.
@param C_Project_ID Financial Project */
public void setC_Project_ID (int C_Project_ID)
{
if (C_Project_ID <= 0) set_Value ("C_Project_ID", null);
 else 
set_Value ("C_Project_ID", new Integer(C_Project_ID));
}
/** Get Project.
@return Financial Project */
public int getC_Project_ID() 
{
Integer ii = (Integer)get_Value("C_Project_ID");
if (ii == null) return 0;
return ii.intValue();
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
/** Set Name.
@param Name Alphanumeric identifier of the entity */
public void setName (String Name)
{
if (Name == null) throw new IllegalArgumentException ("Name is mandatory.");
if (Name.length() > 120)
{
log.warning("Length > 120 - truncated");
Name = Name.substring(0,119);
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
/** Set Profile.
@param ProfileInfo Information to help profiling the system for solving support issues */
public void setProfileInfo (String ProfileInfo)
{
if (ProfileInfo != null && ProfileInfo.length() > 60)
{
log.warning("Length > 60 - truncated");
ProfileInfo = ProfileInfo.substring(0,59);
}
set_Value ("ProfileInfo", ProfileInfo);
}
/** Get Profile.
@return Information to help profiling the system for solving support issues */
public String getProfileInfo() 
{
return (String)get_Value("ProfileInfo");
}
/** Set Issue Project.
@param R_IssueProject_ID Implementation Projects */
public void setR_IssueProject_ID (int R_IssueProject_ID)
{
if (R_IssueProject_ID < 1) throw new IllegalArgumentException ("R_IssueProject_ID is mandatory.");
set_ValueNoCheck ("R_IssueProject_ID", new Integer(R_IssueProject_ID));
}
/** Get Issue Project.
@return Implementation Projects */
public int getR_IssueProject_ID() 
{
Integer ii = (Integer)get_Value("R_IssueProject_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Statistics.
@param StatisticsInfo Information to help profiling the system for solving support issues */
public void setStatisticsInfo (String StatisticsInfo)
{
if (StatisticsInfo != null && StatisticsInfo.length() > 60)
{
log.warning("Length > 60 - truncated");
StatisticsInfo = StatisticsInfo.substring(0,59);
}
set_Value ("StatisticsInfo", StatisticsInfo);
}
/** Get Statistics.
@return Information to help profiling the system for solving support issues */
public String getStatisticsInfo() 
{
return (String)get_Value("StatisticsInfo");
}

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
}
