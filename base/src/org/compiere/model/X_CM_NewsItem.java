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
/** Generated Model for CM_NewsItem
 *  @author Adempiere (generated) 
 *  @version Release 3.3.0 - $Id$ */
public class X_CM_NewsItem extends PO
{
/** Standard Constructor
@param ctx context
@param CM_NewsItem_ID id
@param trxName transaction
*/
public X_CM_NewsItem (Properties ctx, int CM_NewsItem_ID, String trxName)
{
super (ctx, CM_NewsItem_ID, trxName);
/** if (CM_NewsItem_ID == 0)
{
setCM_NewsChannel_ID (0);
setCM_NewsItem_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_CM_NewsItem (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** TableName=CM_NewsItem */
public static final String Table_Name="CM_NewsItem";

/** AD_Table_ID=871 */
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
StringBuffer sb = new StringBuffer ("X_CM_NewsItem[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Author.
@param Author Author/Creator of the Entity */
public void setAuthor (String Author)
{
if (Author != null && Author.length() > 255)
{
log.warning("Length > 255 - truncated");
Author = Author.substring(0,254);
}
set_Value ("Author", Author);
}
/** Get Author.
@return Author/Creator of the Entity */
public String getAuthor() 
{
return (String)get_Value("Author");
}
/** Column name Author */
public static final String COLUMNNAME_Author = "Author";
/** Set News Channel.
@param CM_NewsChannel_ID News channel for rss feed */
public void setCM_NewsChannel_ID (int CM_NewsChannel_ID)
{
if (CM_NewsChannel_ID < 1) throw new IllegalArgumentException ("CM_NewsChannel_ID is mandatory.");
set_Value ("CM_NewsChannel_ID", Integer.valueOf(CM_NewsChannel_ID));
}
/** Get News Channel.
@return News channel for rss feed */
public int getCM_NewsChannel_ID() 
{
Integer ii = (Integer)get_Value("CM_NewsChannel_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name CM_NewsChannel_ID */
public static final String COLUMNNAME_CM_NewsChannel_ID = "CM_NewsChannel_ID";
/** Set News Item / Article.
@param CM_NewsItem_ID News item or article defines base content */
public void setCM_NewsItem_ID (int CM_NewsItem_ID)
{
if (CM_NewsItem_ID < 1) throw new IllegalArgumentException ("CM_NewsItem_ID is mandatory.");
set_ValueNoCheck ("CM_NewsItem_ID", Integer.valueOf(CM_NewsItem_ID));
}
/** Get News Item / Article.
@return News item or article defines base content */
public int getCM_NewsItem_ID() 
{
Integer ii = (Integer)get_Value("CM_NewsItem_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name CM_NewsItem_ID */
public static final String COLUMNNAME_CM_NewsItem_ID = "CM_NewsItem_ID";
/** Set Content HTML.
@param ContentHTML Contains the content itself */
public void setContentHTML (String ContentHTML)
{
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
/** Set LinkURL.
@param LinkURL Contains URL to a target */
public void setLinkURL (String LinkURL)
{
if (LinkURL != null && LinkURL.length() > 120)
{
log.warning("Length > 120 - truncated");
LinkURL = LinkURL.substring(0,119);
}
set_Value ("LinkURL", LinkURL);
}
/** Get LinkURL.
@return Contains URL to a target */
public String getLinkURL() 
{
return (String)get_Value("LinkURL");
}
/** Column name LinkURL */
public static final String COLUMNNAME_LinkURL = "LinkURL";
/** Set Publication Date.
@param PubDate Date on which this article will / should get published */
public void setPubDate (Timestamp PubDate)
{
set_Value ("PubDate", PubDate);
}
/** Get Publication Date.
@return Date on which this article will / should get published */
public Timestamp getPubDate() 
{
return (Timestamp)get_Value("PubDate");
}
/** Column name PubDate */
public static final String COLUMNNAME_PubDate = "PubDate";
/** Set Title.
@param Title Name this entity is referred to as */
public void setTitle (String Title)
{
if (Title != null && Title.length() > 255)
{
log.warning("Length > 255 - truncated");
Title = Title.substring(0,254);
}
set_Value ("Title", Title);
}
/** Get Title.
@return Name this entity is referred to as */
public String getTitle() 
{
return (String)get_Value("Title");
}
/** Column name Title */
public static final String COLUMNNAME_Title = "Title";
}
