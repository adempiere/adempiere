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
/** Generated Model for CM_Media
 *  @author Adempiere (generated) 
 *  @version Release 3.1.6 - $Id$ */
public class X_CM_Media extends PO
{
/** Standard Constructor
@param ctx context
@param CM_Media_ID id
@param trxName transaction
*/
public X_CM_Media (Properties ctx, int CM_Media_ID, String trxName)
{
super (ctx, CM_Media_ID, trxName);
/** if (CM_Media_ID == 0)
{
setCM_Media_ID (0);
setCM_WebProject_ID (0);
setIsSummary (false);
setName (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_CM_Media (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=857 */
public static final int Table_ID=MTable.getTable_ID("CM_Media");

/** TableName=CM_Media */
public static final String Table_Name="CM_Media";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"CM_Media");

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
StringBuffer sb = new StringBuffer ("X_CM_Media[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Image.
@param AD_Image_ID Image or Icon */
public void setAD_Image_ID (int AD_Image_ID)
{
if (AD_Image_ID <= 0) set_Value ("AD_Image_ID", null);
 else 
set_Value ("AD_Image_ID", Integer.valueOf(AD_Image_ID));
}
/** Get Image.
@return Image or Icon */
public int getAD_Image_ID() 
{
Integer ii = (Integer)get_Value("AD_Image_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_Image_ID */
public static final String COLUMNNAME_AD_Image_ID = "AD_Image_ID";
/** Set Media Item.
@param CM_Media_ID Contains media content like images, flash movies etc. */
public void setCM_Media_ID (int CM_Media_ID)
{
if (CM_Media_ID < 1) throw new IllegalArgumentException ("CM_Media_ID is mandatory.");
set_ValueNoCheck ("CM_Media_ID", Integer.valueOf(CM_Media_ID));
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
/** Set Content.
@param ContentText Content */
public void setContentText (String ContentText)
{
set_Value ("ContentText", ContentText);
}
/** Get Content.
@return Content */
public String getContentText() 
{
return (String)get_Value("ContentText");
}
/** Column name ContentText */
public static final String COLUMNNAME_ContentText = "ContentText";
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
/** Set Direct Deploy.
@param DirectDeploy Direct Deploy */
public void setDirectDeploy (String DirectDeploy)
{
if (DirectDeploy != null && DirectDeploy.length() > 1)
{
log.warning("Length > 1 - truncated");
DirectDeploy = DirectDeploy.substring(0,0);
}
set_Value ("DirectDeploy", DirectDeploy);
}
/** Get Direct Deploy.
@return Direct Deploy */
public String getDirectDeploy() 
{
return (String)get_Value("DirectDeploy");
}
/** Column name DirectDeploy */
public static final String COLUMNNAME_DirectDeploy = "DirectDeploy";
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
/** Set Summary Level.
@param IsSummary This is a summary entity */
public void setIsSummary (boolean IsSummary)
{
set_Value ("IsSummary", Boolean.valueOf(IsSummary));
}
/** Get Summary Level.
@return This is a summary entity */
public boolean isSummary() 
{
Object oo = get_Value("IsSummary");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsSummary */
public static final String COLUMNNAME_IsSummary = "IsSummary";

/** MediaType AD_Reference_ID=388 */
public static final int MEDIATYPE_AD_Reference_ID=388;
/** text/css = CSS */
public static final String MEDIATYPE_TextCss = "CSS";
/** image/gif = GIF */
public static final String MEDIATYPE_ImageGif = "GIF";
/** image/jpeg = JPG */
public static final String MEDIATYPE_ImageJpeg = "JPG";
/** text/js = JS */
public static final String MEDIATYPE_TextJs = "JS";
/** application/pdf = PDF */
public static final String MEDIATYPE_ApplicationPdf = "PDF";
/** image/png = PNG */
public static final String MEDIATYPE_ImagePng = "PNG";
/** Set Media Type.
@param MediaType Defines the media type for the browser */
public void setMediaType (String MediaType)
{
if (MediaType == null || MediaType.equals("CSS") || MediaType.equals("GIF") || MediaType.equals("JPG") || MediaType.equals("JS") || MediaType.equals("PDF") || MediaType.equals("PNG"));
 else throw new IllegalArgumentException ("MediaType Invalid value - " + MediaType + " - Reference_ID=388 - CSS - GIF - JPG - JS - PDF - PNG");
if (MediaType != null && MediaType.length() > 3)
{
log.warning("Length > 3 - truncated");
MediaType = MediaType.substring(0,2);
}
set_Value ("MediaType", MediaType);
}
/** Get Media Type.
@return Defines the media type for the browser */
public String getMediaType() 
{
return (String)get_Value("MediaType");
}
/** Column name MediaType */
public static final String COLUMNNAME_MediaType = "MediaType";
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
