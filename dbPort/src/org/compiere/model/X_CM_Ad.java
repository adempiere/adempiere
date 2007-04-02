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
/** Generated Model for CM_Ad
 *  @author Adempiere (generated) 
 *  @version Release 3.1.6 - $Id$ */
public class X_CM_Ad extends PO
{
/** Standard Constructor
@param ctx context
@param CM_Ad_ID id
@param trxName transaction
*/
public X_CM_Ad (Properties ctx, int CM_Ad_ID, String trxName)
{
super (ctx, CM_Ad_ID, trxName);
/** if (CM_Ad_ID == 0)
{
setActualClick (0);
setActualImpression (0);
setCM_Ad_Cat_ID (0);
setCM_Ad_ID (0);
setCM_Media_ID (0);
setIsAdFlag (false);
setIsLogged (false);
setMaxClick (0);
setMaxImpression (0);
setName (null);
setStartDate (new Timestamp(System.currentTimeMillis()));
setStartImpression (0);
setTarget_Frame (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_CM_Ad (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=858 */
public static final int Table_ID=MTable.getTable_ID("CM_Ad");

/** TableName=CM_Ad */
public static final String Table_Name="CM_Ad";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"CM_Ad");

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
StringBuffer sb = new StringBuffer ("X_CM_Ad[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Actual Click Count.
@param ActualClick How many clicks have been counted */
public void setActualClick (int ActualClick)
{
set_Value ("ActualClick", Integer.valueOf(ActualClick));
}
/** Get Actual Click Count.
@return How many clicks have been counted */
public int getActualClick() 
{
Integer ii = (Integer)get_Value("ActualClick");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name ActualClick */
public static final String COLUMNNAME_ActualClick = "ActualClick";
/** Set Actual Impression Count.
@param ActualImpression How many impressions have been counted */
public void setActualImpression (int ActualImpression)
{
set_Value ("ActualImpression", Integer.valueOf(ActualImpression));
}
/** Get Actual Impression Count.
@return How many impressions have been counted */
public int getActualImpression() 
{
Integer ii = (Integer)get_Value("ActualImpression");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name ActualImpression */
public static final String COLUMNNAME_ActualImpression = "ActualImpression";
/** Set Advertisement Category.
@param CM_Ad_Cat_ID Advertisement Category like Banner Homepage  */
public void setCM_Ad_Cat_ID (int CM_Ad_Cat_ID)
{
if (CM_Ad_Cat_ID < 1) throw new IllegalArgumentException ("CM_Ad_Cat_ID is mandatory.");
set_ValueNoCheck ("CM_Ad_Cat_ID", Integer.valueOf(CM_Ad_Cat_ID));
}
/** Get Advertisement Category.
@return Advertisement Category like Banner Homepage  */
public int getCM_Ad_Cat_ID() 
{
Integer ii = (Integer)get_Value("CM_Ad_Cat_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name CM_Ad_Cat_ID */
public static final String COLUMNNAME_CM_Ad_Cat_ID = "CM_Ad_Cat_ID";
/** Set Advertisement.
@param CM_Ad_ID An Advertisement is something like a banner */
public void setCM_Ad_ID (int CM_Ad_ID)
{
if (CM_Ad_ID < 1) throw new IllegalArgumentException ("CM_Ad_ID is mandatory.");
set_ValueNoCheck ("CM_Ad_ID", Integer.valueOf(CM_Ad_ID));
}
/** Get Advertisement.
@return An Advertisement is something like a banner */
public int getCM_Ad_ID() 
{
Integer ii = (Integer)get_Value("CM_Ad_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name CM_Ad_ID */
public static final String COLUMNNAME_CM_Ad_ID = "CM_Ad_ID";
/** Set Media Item.
@param CM_Media_ID Contains media content like images, flash movies etc. */
public void setCM_Media_ID (int CM_Media_ID)
{
if (CM_Media_ID < 1) throw new IllegalArgumentException ("CM_Media_ID is mandatory.");
set_Value ("CM_Media_ID", Integer.valueOf(CM_Media_ID));
}
/** Get Media Item.
@return Contains media content like images, flash movies etc. */
public int getCM_Media_ID() 
{
Integer ii = (Integer)get_Value("CM_Media_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name CM_Media_ID */
public static final String COLUMNNAME_CM_Media_ID = "CM_Media_ID";
/** Set Content HTML.
@param ContentHTML Contains the content itself */
public void setContentHTML (String ContentHTML)
{
if (ContentHTML != null && ContentHTML.length() > 2000)
{
log.warning("Length > 2000 - truncated");
ContentHTML = ContentHTML.substring(0,1999);
}
set_Value ("ContentHTML", ContentHTML);
}
/** Get Content HTML.
@return Contains the content itself */
public String getContentHTML() 
{
return (String)get_Value("ContentHTML");
}
/** Column name ContentHTML */
public static final String COLUMNNAME_ContentHTML = "ContentHTML";
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
/** Set End Date.
@param EndDate Last effective date (inclusive) */
public void setEndDate (Timestamp EndDate)
{
set_Value ("EndDate", EndDate);
}
/** Get End Date.
@return Last effective date (inclusive) */
public Timestamp getEndDate() 
{
return (Timestamp)get_Value("EndDate");
}
/** Column name EndDate */
public static final String COLUMNNAME_EndDate = "EndDate";
/** Set Comment/Help.
@param Help Comment or Hint */
public void setHelp (String Help)
{
if (Help != null && Help.length() > 2000)
{
log.warning("Length > 2000 - truncated");
Help = Help.substring(0,1999);
}
set_Value ("Help", Help);
}
/** Get Comment/Help.
@return Comment or Hint */
public String getHelp() 
{
return (String)get_Value("Help");
}
/** Column name Help */
public static final String COLUMNNAME_Help = "Help";
/** Set Special AD Flag.
@param IsAdFlag Do we need to specially mention this ad? */
public void setIsAdFlag (boolean IsAdFlag)
{
set_Value ("IsAdFlag", Boolean.valueOf(IsAdFlag));
}
/** Get Special AD Flag.
@return Do we need to specially mention this ad? */
public boolean isAdFlag() 
{
Object oo = get_Value("IsAdFlag");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsAdFlag */
public static final String COLUMNNAME_IsAdFlag = "IsAdFlag";
/** Set Logging.
@param IsLogged Do we need to log the banner impressions and clicks? (needs much performance) */
public void setIsLogged (boolean IsLogged)
{
set_Value ("IsLogged", Boolean.valueOf(IsLogged));
}
/** Get Logging.
@return Do we need to log the banner impressions and clicks? (needs much performance) */
public boolean isLogged() 
{
Object oo = get_Value("IsLogged");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsLogged */
public static final String COLUMNNAME_IsLogged = "IsLogged";
/** Set Max Click Count.
@param MaxClick Maximum Click Count until banner is deactivated */
public void setMaxClick (int MaxClick)
{
set_Value ("MaxClick", Integer.valueOf(MaxClick));
}
/** Get Max Click Count.
@return Maximum Click Count until banner is deactivated */
public int getMaxClick() 
{
Integer ii = (Integer)get_Value("MaxClick");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name MaxClick */
public static final String COLUMNNAME_MaxClick = "MaxClick";
/** Set Max Impression Count.
@param MaxImpression Maximum Impression Count until banner is deactivated */
public void setMaxImpression (int MaxImpression)
{
set_Value ("MaxImpression", Integer.valueOf(MaxImpression));
}
/** Get Max Impression Count.
@return Maximum Impression Count until banner is deactivated */
public int getMaxImpression() 
{
Integer ii = (Integer)get_Value("MaxImpression");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name MaxImpression */
public static final String COLUMNNAME_MaxImpression = "MaxImpression";
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
/** Column name Name */
public static final String COLUMNNAME_Name = "Name";
/** Set Start Date.
@param StartDate First effective day (inclusive) */
public void setStartDate (Timestamp StartDate)
{
if (StartDate == null) throw new IllegalArgumentException ("StartDate is mandatory.");
set_Value ("StartDate", StartDate);
}
/** Get Start Date.
@return First effective day (inclusive) */
public Timestamp getStartDate() 
{
return (Timestamp)get_Value("StartDate");
}
/** Column name StartDate */
public static final String COLUMNNAME_StartDate = "StartDate";
/** Set Start Count Impression.
@param StartImpression For rotation we need a start count */
public void setStartImpression (int StartImpression)
{
set_Value ("StartImpression", Integer.valueOf(StartImpression));
}
/** Get Start Count Impression.
@return For rotation we need a start count */
public int getStartImpression() 
{
Integer ii = (Integer)get_Value("StartImpression");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name StartImpression */
public static final String COLUMNNAME_StartImpression = "StartImpression";
/** Set Target URL.
@param TargetURL URL for the Target */
public void setTargetURL (String TargetURL)
{
if (TargetURL != null && TargetURL.length() > 120)
{
log.warning("Length > 120 - truncated");
TargetURL = TargetURL.substring(0,119);
}
set_Value ("TargetURL", TargetURL);
}
/** Get Target URL.
@return URL for the Target */
public String getTargetURL() 
{
return (String)get_Value("TargetURL");
}
/** Column name TargetURL */
public static final String COLUMNNAME_TargetURL = "TargetURL";
/** Set Target Frame.
@param Target_Frame Which target should be used if user clicks? */
public void setTarget_Frame (String Target_Frame)
{
if (Target_Frame == null) throw new IllegalArgumentException ("Target_Frame is mandatory.");
if (Target_Frame.length() > 20)
{
log.warning("Length > 20 - truncated");
Target_Frame = Target_Frame.substring(0,19);
}
set_Value ("Target_Frame", Target_Frame);
}
/** Get Target Frame.
@return Which target should be used if user clicks? */
public String getTarget_Frame() 
{
return (String)get_Value("Target_Frame");
}
/** Column name Target_Frame */
public static final String COLUMNNAME_Target_Frame = "Target_Frame";
}
