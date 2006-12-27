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
/** Generated Model for R_IssueKnown
 *  @author Jorg Janke (generated) 
 *  @version Release 3.1.3 - $Id$ */
public class X_R_IssueKnown extends PO
{
/** Standard Constructor
@param ctx context
@param R_IssueKnown_ID id
@param trxName transaction
*/
public X_R_IssueKnown (Properties ctx, int R_IssueKnown_ID, String trxName)
{
super (ctx, R_IssueKnown_ID, trxName);
/** if (R_IssueKnown_ID == 0)
{
setIssueSummary (null);
setR_IssueKnown_ID (0);
setReleaseNo (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_R_IssueKnown (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=839 */
public static final int Table_ID=MTable.getTable_ID("R_IssueKnown");

/** TableName=R_IssueKnown */
public static final String Table_Name="R_IssueKnown";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"R_IssueKnown");

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
StringBuffer sb = new StringBuffer ("X_R_IssueKnown[").append(get_ID()).append("]");
return sb.toString();
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
/** Set Issue Status.
@param IssueStatus Current Status of the Issue */
public void setIssueStatus (String IssueStatus)
{
if (IssueStatus != null && IssueStatus.length() > 2000)
{
log.warning("Length > 2000 - truncated");
IssueStatus = IssueStatus.substring(0,1999);
}
set_Value ("IssueStatus", IssueStatus);
}
/** Get Issue Status.
@return Current Status of the Issue */
public String getIssueStatus() 
{
return (String)get_Value("IssueStatus");
}
/** Set Issue Summary.
@param IssueSummary Issue Summary */
public void setIssueSummary (String IssueSummary)
{
if (IssueSummary == null) throw new IllegalArgumentException ("IssueSummary is mandatory.");
if (IssueSummary.length() > 255)
{
log.warning("Length > 255 - truncated");
IssueSummary = IssueSummary.substring(0,254);
}
set_Value ("IssueSummary", IssueSummary);
}
/** Get Issue Summary.
@return Issue Summary */
public String getIssueSummary() 
{
return (String)get_Value("IssueSummary");
}
/** Set Line.
@param LineNo Line No */
public void setLineNo (int LineNo)
{
set_Value ("LineNo", Integer.valueOf(LineNo));
}
/** Get Line.
@return Line No */
public int getLineNo() 
{
Integer ii = (Integer)get_Value("LineNo");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Logger.
@param LoggerName Logger Name */
public void setLoggerName (String LoggerName)
{
if (LoggerName != null && LoggerName.length() > 60)
{
log.warning("Length > 60 - truncated");
LoggerName = LoggerName.substring(0,59);
}
set_Value ("LoggerName", LoggerName);
}
/** Get Logger.
@return Logger Name */
public String getLoggerName() 
{
return (String)get_Value("LoggerName");
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
/** Set Known Issue.
@param R_IssueKnown_ID Known Issue */
public void setR_IssueKnown_ID (int R_IssueKnown_ID)
{
if (R_IssueKnown_ID < 1) throw new IllegalArgumentException ("R_IssueKnown_ID is mandatory.");
set_ValueNoCheck ("R_IssueKnown_ID", Integer.valueOf(R_IssueKnown_ID));
}
/** Get Known Issue.
@return Known Issue */
public int getR_IssueKnown_ID() 
{
Integer ii = (Integer)get_Value("R_IssueKnown_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Issue Recommendation.
@param R_IssueRecommendation_ID Recommendations how to fix an Issue */
public void setR_IssueRecommendation_ID (int R_IssueRecommendation_ID)
{
if (R_IssueRecommendation_ID <= 0) set_Value ("R_IssueRecommendation_ID", null);
 else 
set_Value ("R_IssueRecommendation_ID", Integer.valueOf(R_IssueRecommendation_ID));
}
/** Get Issue Recommendation.
@return Recommendations how to fix an Issue */
public int getR_IssueRecommendation_ID() 
{
Integer ii = (Integer)get_Value("R_IssueRecommendation_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Issue Status.
@param R_IssueStatus_ID Status of an Issue */
public void setR_IssueStatus_ID (int R_IssueStatus_ID)
{
if (R_IssueStatus_ID <= 0) set_Value ("R_IssueStatus_ID", null);
 else 
set_Value ("R_IssueStatus_ID", Integer.valueOf(R_IssueStatus_ID));
}
/** Get Issue Status.
@return Status of an Issue */
public int getR_IssueStatus_ID() 
{
Integer ii = (Integer)get_Value("R_IssueStatus_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Request.
@param R_Request_ID Request from a Business Partner or Prospect */
public void setR_Request_ID (int R_Request_ID)
{
if (R_Request_ID <= 0) set_Value ("R_Request_ID", null);
 else 
set_Value ("R_Request_ID", Integer.valueOf(R_Request_ID));
}
/** Get Request.
@return Request from a Business Partner or Prospect */
public int getR_Request_ID() 
{
Integer ii = (Integer)get_Value("R_Request_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Release No.
@param ReleaseNo Internal Release Number */
public void setReleaseNo (String ReleaseNo)
{
if (ReleaseNo == null) throw new IllegalArgumentException ("ReleaseNo is mandatory.");
if (ReleaseNo.length() > 4)
{
log.warning("Length > 4 - truncated");
ReleaseNo = ReleaseNo.substring(0,3);
}
set_Value ("ReleaseNo", ReleaseNo);
}
/** Get Release No.
@return Internal Release Number */
public String getReleaseNo() 
{
return (String)get_Value("ReleaseNo");
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), getReleaseNo());
}
/** Set Source Class.
@param SourceClassName Source Class Name */
public void setSourceClassName (String SourceClassName)
{
if (SourceClassName != null && SourceClassName.length() > 60)
{
log.warning("Length > 60 - truncated");
SourceClassName = SourceClassName.substring(0,59);
}
set_Value ("SourceClassName", SourceClassName);
}
/** Get Source Class.
@return Source Class Name */
public String getSourceClassName() 
{
return (String)get_Value("SourceClassName");
}
/** Set Source Method.
@param SourceMethodName Source Method Name */
public void setSourceMethodName (String SourceMethodName)
{
if (SourceMethodName != null && SourceMethodName.length() > 60)
{
log.warning("Length > 60 - truncated");
SourceMethodName = SourceMethodName.substring(0,59);
}
set_Value ("SourceMethodName", SourceMethodName);
}
/** Get Source Method.
@return Source Method Name */
public String getSourceMethodName() 
{
return (String)get_Value("SourceMethodName");
}
}
