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
/** Generated Model for CM_WebProject
 *  @author Adempiere (generated) 
 *  @version Release 3.2.0 - $Id$ */
public class X_CM_WebProject extends PO
{
/** Standard Constructor
@param ctx context
@param CM_WebProject_ID id
@param trxName transaction
*/
public X_CM_WebProject (Properties ctx, int CM_WebProject_ID, String trxName)
{
super (ctx, CM_WebProject_ID, trxName);
/** if (CM_WebProject_ID == 0)
{
setCM_WebProject_ID (0);
setMeta_Author (null);	// @AD_User_Name@
setMeta_Content (null);	// 'text/html;
 charset=UTF-8'
setMeta_Copyright (null);	// @AD_Client_Name@
setMeta_Publisher (null);	// @AD_Client_Name@
setMeta_RobotsTag (null);	// 'INDEX,FOLLOW'
setName (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_CM_WebProject (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=853 */
public static final int Table_ID=MTable.getTable_ID("CM_WebProject");

/** TableName=CM_WebProject */
public static final String Table_Name="CM_WebProject";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"CM_WebProject");

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
StringBuffer sb = new StringBuffer ("X_CM_WebProject[").append(get_ID()).append("]");
return sb.toString();
}

/** AD_TreeCMC_ID AD_Reference_ID=184 */
public static final int AD_TREECMC_ID_AD_Reference_ID=184;
/** Set Container Tree.
@param AD_TreeCMC_ID Container Tree */
public void setAD_TreeCMC_ID (int AD_TreeCMC_ID)
{
if (AD_TreeCMC_ID <= 0) set_ValueNoCheck ("AD_TreeCMC_ID", null);
 else 
set_ValueNoCheck ("AD_TreeCMC_ID", Integer.valueOf(AD_TreeCMC_ID));
}
/** Get Container Tree.
@return Container Tree */
public int getAD_TreeCMC_ID() 
{
Integer ii = (Integer)get_Value("AD_TreeCMC_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_TreeCMC_ID */
public static final String COLUMNNAME_AD_TreeCMC_ID = "AD_TreeCMC_ID";

/** AD_TreeCMM_ID AD_Reference_ID=184 */
public static final int AD_TREECMM_ID_AD_Reference_ID=184;
/** Set Media Tree.
@param AD_TreeCMM_ID Media Tree */
public void setAD_TreeCMM_ID (int AD_TreeCMM_ID)
{
if (AD_TreeCMM_ID <= 0) set_ValueNoCheck ("AD_TreeCMM_ID", null);
 else 
set_ValueNoCheck ("AD_TreeCMM_ID", Integer.valueOf(AD_TreeCMM_ID));
}
/** Get Media Tree.
@return Media Tree */
public int getAD_TreeCMM_ID() 
{
Integer ii = (Integer)get_Value("AD_TreeCMM_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_TreeCMM_ID */
public static final String COLUMNNAME_AD_TreeCMM_ID = "AD_TreeCMM_ID";

/** AD_TreeCMS_ID AD_Reference_ID=184 */
public static final int AD_TREECMS_ID_AD_Reference_ID=184;
/** Set Stage Tree.
@param AD_TreeCMS_ID Stage Tree */
public void setAD_TreeCMS_ID (int AD_TreeCMS_ID)
{
if (AD_TreeCMS_ID <= 0) set_ValueNoCheck ("AD_TreeCMS_ID", null);
 else 
set_ValueNoCheck ("AD_TreeCMS_ID", Integer.valueOf(AD_TreeCMS_ID));
}
/** Get Stage Tree.
@return Stage Tree */
public int getAD_TreeCMS_ID() 
{
Integer ii = (Integer)get_Value("AD_TreeCMS_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_TreeCMS_ID */
public static final String COLUMNNAME_AD_TreeCMS_ID = "AD_TreeCMS_ID";

/** AD_TreeCMT_ID AD_Reference_ID=184 */
public static final int AD_TREECMT_ID_AD_Reference_ID=184;
/** Set Template Tree.
@param AD_TreeCMT_ID Template Tree */
public void setAD_TreeCMT_ID (int AD_TreeCMT_ID)
{
if (AD_TreeCMT_ID <= 0) set_ValueNoCheck ("AD_TreeCMT_ID", null);
 else 
set_ValueNoCheck ("AD_TreeCMT_ID", Integer.valueOf(AD_TreeCMT_ID));
}
/** Get Template Tree.
@return Template Tree */
public int getAD_TreeCMT_ID() 
{
Integer ii = (Integer)get_Value("AD_TreeCMT_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_TreeCMT_ID */
public static final String COLUMNNAME_AD_TreeCMT_ID = "AD_TreeCMT_ID";
/** Set Web Project.
@param CM_WebProject_ID A web project is the main data container for Containers, URLs, Ads, Media etc. */
public void setCM_WebProject_ID (int CM_WebProject_ID)
{
if (CM_WebProject_ID < 1) throw new IllegalArgumentException ("CM_WebProject_ID is mandatory.");
set_ValueNoCheck ("CM_WebProject_ID", Integer.valueOf(CM_WebProject_ID));
}
/** Get Web Project.
@return A web project is the main data container for Containers, URLs, Ads, Media etc. */
public int getCM_WebProject_ID() 
{
Integer ii = (Integer)get_Value("CM_WebProject_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name CM_WebProject_ID */
public static final String COLUMNNAME_CM_WebProject_ID = "CM_WebProject_ID";
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
/** Set Meta Author.
@param Meta_Author Author of the content */
public void setMeta_Author (String Meta_Author)
{
if (Meta_Author == null) throw new IllegalArgumentException ("Meta_Author is mandatory.");
if (Meta_Author.length() > 2000)
{
log.warning("Length > 2000 - truncated");
Meta_Author = Meta_Author.substring(0,1999);
}
set_Value ("Meta_Author", Meta_Author);
}
/** Get Meta Author.
@return Author of the content */
public String getMeta_Author() 
{
return (String)get_Value("Meta_Author");
}
/** Column name Meta_Author */
public static final String COLUMNNAME_Meta_Author = "Meta_Author";
/** Set Meta Content Type.
@param Meta_Content Defines the type of content i.e. "text/html;
 charset=UTF-8" */
public void setMeta_Content (String Meta_Content)
{
if (Meta_Content == null) throw new IllegalArgumentException ("Meta_Content is mandatory.");
if (Meta_Content.length() > 2000)
{
log.warning("Length > 2000 - truncated");
Meta_Content = Meta_Content.substring(0,1999);
}
set_Value ("Meta_Content", Meta_Content);
}
/** Get Meta Content Type.
@return Defines the type of content i.e. "text/html;
 charset=UTF-8" */
public String getMeta_Content() 
{
return (String)get_Value("Meta_Content");
}
/** Column name Meta_Content */
public static final String COLUMNNAME_Meta_Content = "Meta_Content";
/** Set Meta Copyright.
@param Meta_Copyright Contains Copyright information for the content */
public void setMeta_Copyright (String Meta_Copyright)
{
if (Meta_Copyright == null) throw new IllegalArgumentException ("Meta_Copyright is mandatory.");
if (Meta_Copyright.length() > 2000)
{
log.warning("Length > 2000 - truncated");
Meta_Copyright = Meta_Copyright.substring(0,1999);
}
set_Value ("Meta_Copyright", Meta_Copyright);
}
/** Get Meta Copyright.
@return Contains Copyright information for the content */
public String getMeta_Copyright() 
{
return (String)get_Value("Meta_Copyright");
}
/** Column name Meta_Copyright */
public static final String COLUMNNAME_Meta_Copyright = "Meta_Copyright";
/** Set Meta Publisher.
@param Meta_Publisher Meta Publisher defines the publisher of the content */
public void setMeta_Publisher (String Meta_Publisher)
{
if (Meta_Publisher == null) throw new IllegalArgumentException ("Meta_Publisher is mandatory.");
if (Meta_Publisher.length() > 2000)
{
log.warning("Length > 2000 - truncated");
Meta_Publisher = Meta_Publisher.substring(0,1999);
}
set_Value ("Meta_Publisher", Meta_Publisher);
}
/** Get Meta Publisher.
@return Meta Publisher defines the publisher of the content */
public String getMeta_Publisher() 
{
return (String)get_Value("Meta_Publisher");
}
/** Column name Meta_Publisher */
public static final String COLUMNNAME_Meta_Publisher = "Meta_Publisher";
/** Set Meta RobotsTag.
@param Meta_RobotsTag RobotsTag defines how search robots should handle this content */
public void setMeta_RobotsTag (String Meta_RobotsTag)
{
if (Meta_RobotsTag == null) throw new IllegalArgumentException ("Meta_RobotsTag is mandatory.");
if (Meta_RobotsTag.length() > 2000)
{
log.warning("Length > 2000 - truncated");
Meta_RobotsTag = Meta_RobotsTag.substring(0,1999);
}
set_Value ("Meta_RobotsTag", Meta_RobotsTag);
}
/** Get Meta RobotsTag.
@return RobotsTag defines how search robots should handle this content */
public String getMeta_RobotsTag() 
{
return (String)get_Value("Meta_RobotsTag");
}
/** Column name Meta_RobotsTag */
public static final String COLUMNNAME_Meta_RobotsTag = "Meta_RobotsTag";
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
}
