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
/** Generated Model for CM_Template
 *  @author Adempiere (generated) 
 *  @version Release 3.1.4 - $Id$ */
public class X_CM_Template extends PO
{
/** Standard Constructor
@param ctx context
@param CM_Template_ID id
@param trxName transaction
*/
public X_CM_Template (Properties ctx, int CM_Template_ID, String trxName)
{
super (ctx, CM_Template_ID, trxName);
/** if (CM_Template_ID == 0)
{
setCM_Template_ID (0);
setIsInclude (false);
setIsNews (false);
setIsSummary (false);
setIsUseAd (false);
setIsValid (false);
setName (null);
setValue (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_CM_Template (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=854 */
public static final int Table_ID=MTable.getTable_ID("CM_Template");

/** TableName=CM_Template */
public static final String Table_Name="CM_Template";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"CM_Template");

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
StringBuffer sb = new StringBuffer ("X_CM_Template[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Template.
@param CM_Template_ID Template defines how content is displayed */
public void setCM_Template_ID (int CM_Template_ID)
{
if (CM_Template_ID < 1) throw new IllegalArgumentException ("CM_Template_ID is mandatory.");
set_ValueNoCheck ("CM_Template_ID", Integer.valueOf(CM_Template_ID));
}
/** Get Template.
@return Template defines how content is displayed */
public int getCM_Template_ID() 
{
Integer ii = (Integer)get_Value("CM_Template_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Web Project.
@param CM_WebProject_ID A web project is the main data container for Containers, URLs, Ads, Media etc. */
public void setCM_WebProject_ID (int CM_WebProject_ID)
{
if (CM_WebProject_ID <= 0) set_ValueNoCheck ("CM_WebProject_ID", null);
 else 
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
/** Set Elements.
@param Elements Contains list of elements seperated by CR */
public void setElements (String Elements)
{
if (Elements != null && Elements.length() > 2000)
{
log.warning("Length > 2000 - truncated");
Elements = Elements.substring(0,1999);
}
set_Value ("Elements", Elements);
}
/** Get Elements.
@return Contains list of elements seperated by CR */
public String getElements() 
{
return (String)get_Value("Elements");
}
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
/** Set Included.
@param IsInclude Defines whether this content / template is included into another one */
public void setIsInclude (boolean IsInclude)
{
set_Value ("IsInclude", Boolean.valueOf(IsInclude));
}
/** Get Included.
@return Defines whether this content / template is included into another one */
public boolean isInclude() 
{
Object oo = get_Value("IsInclude");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Uses News.
@param IsNews Template or container uses news channels */
public void setIsNews (boolean IsNews)
{
set_Value ("IsNews", Boolean.valueOf(IsNews));
}
/** Get Uses News.
@return Template or container uses news channels */
public boolean isNews() 
{
Object oo = get_Value("IsNews");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
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
/** Set Use Ad.
@param IsUseAd Whether or not this templates uses Ad's */
public void setIsUseAd (boolean IsUseAd)
{
set_Value ("IsUseAd", Boolean.valueOf(IsUseAd));
}
/** Get Use Ad.
@return Whether or not this templates uses Ad's */
public boolean isUseAd() 
{
Object oo = get_Value("IsUseAd");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Valid.
@param IsValid Element is valid */
public void setIsValid (boolean IsValid)
{
set_Value ("IsValid", Boolean.valueOf(IsValid));
}
/** Get Valid.
@return Element is valid */
public boolean isValid() 
{
Object oo = get_Value("IsValid");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
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
/** Set TemplateXST.
@param TemplateXST Contains the template code itself */
public void setTemplateXST (String TemplateXST)
{
set_Value ("TemplateXST", TemplateXST);
}
/** Get TemplateXST.
@return Contains the template code itself */
public String getTemplateXST() 
{
return (String)get_Value("TemplateXST");
}
/** Set Search Key.
@param Value Search key for the record in the format required - must be unique */
public void setValue (String Value)
{
if (Value == null) throw new IllegalArgumentException ("Value is mandatory.");
if (Value.length() > 40)
{
log.warning("Length > 40 - truncated");
Value = Value.substring(0,39);
}
set_Value ("Value", Value);
}
/** Get Search Key.
@return Search key for the record in the format required - must be unique */
public String getValue() 
{
return (String)get_Value("Value");
}
}
