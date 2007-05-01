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
/** Generated Model for R_MailText
 *  @author Adempiere (generated) 
 *  @version Release 3.2.0 - $Id$ */
public class X_R_MailText extends PO
{
/** Standard Constructor
@param ctx context
@param R_MailText_ID id
@param trxName transaction
*/
public X_R_MailText (Properties ctx, int R_MailText_ID, String trxName)
{
super (ctx, R_MailText_ID, trxName);
/** if (R_MailText_ID == 0)
{
setIsHtml (false);
setMailText (null);
setName (null);
setR_MailText_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_R_MailText (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=416 */
public static final int Table_ID=MTable.getTable_ID("R_MailText");

/** TableName=R_MailText */
public static final String Table_Name="R_MailText";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"R_MailText");

protected BigDecimal accessLevel = BigDecimal.valueOf(7);
/** AccessLevel
@return 7 - System - Client - Org 
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
StringBuffer sb = new StringBuffer ("X_R_MailText[").append(get_ID()).append("]");
return sb.toString();
}
/** Set HTML.
@param IsHtml Text has HTML tags */
public void setIsHtml (boolean IsHtml)
{
set_Value ("IsHtml", Boolean.valueOf(IsHtml));
}
/** Get HTML.
@return Text has HTML tags */
public boolean isHtml() 
{
Object oo = get_Value("IsHtml");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsHtml */
public static final String COLUMNNAME_IsHtml = "IsHtml";
/** Set Subject.
@param MailHeader Mail Header (Subject) */
public void setMailHeader (String MailHeader)
{
if (MailHeader != null && MailHeader.length() > 2000)
{
log.warning("Length > 2000 - truncated");
MailHeader = MailHeader.substring(0,1999);
}
set_Value ("MailHeader", MailHeader);
}
/** Get Subject.
@return Mail Header (Subject) */
public String getMailHeader() 
{
return (String)get_Value("MailHeader");
}
/** Column name MailHeader */
public static final String COLUMNNAME_MailHeader = "MailHeader";
/** Set Mail Text.
@param MailText Text used for Mail message */
public void setMailText (String MailText)
{
if (MailText == null) throw new IllegalArgumentException ("MailText is mandatory.");
if (MailText.length() > 2000)
{
log.warning("Length > 2000 - truncated");
MailText = MailText.substring(0,1999);
}
set_Value ("MailText", MailText);
}
/** Get Mail Text.
@return Text used for Mail message */
public String getMailText() 
{
return (String)get_Value("MailText");
}
/** Column name MailText */
public static final String COLUMNNAME_MailText = "MailText";
/** Set Mail Text 2.
@param MailText2 Optional second text part used for Mail message */
public void setMailText2 (String MailText2)
{
if (MailText2 != null && MailText2.length() > 2000)
{
log.warning("Length > 2000 - truncated");
MailText2 = MailText2.substring(0,1999);
}
set_Value ("MailText2", MailText2);
}
/** Get Mail Text 2.
@return Optional second text part used for Mail message */
public String getMailText2() 
{
return (String)get_Value("MailText2");
}
/** Column name MailText2 */
public static final String COLUMNNAME_MailText2 = "MailText2";
/** Set Mail Text 3.
@param MailText3 Optional third text part used for Mail message */
public void setMailText3 (String MailText3)
{
if (MailText3 != null && MailText3.length() > 2000)
{
log.warning("Length > 2000 - truncated");
MailText3 = MailText3.substring(0,1999);
}
set_Value ("MailText3", MailText3);
}
/** Get Mail Text 3.
@return Optional third text part used for Mail message */
public String getMailText3() 
{
return (String)get_Value("MailText3");
}
/** Column name MailText3 */
public static final String COLUMNNAME_MailText3 = "MailText3";
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
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), getName());
}
/** Column name Name */
public static final String COLUMNNAME_Name = "Name";
/** Set Mail Template.
@param R_MailText_ID Text templates for mailings */
public void setR_MailText_ID (int R_MailText_ID)
{
if (R_MailText_ID < 1) throw new IllegalArgumentException ("R_MailText_ID is mandatory.");
set_ValueNoCheck ("R_MailText_ID", Integer.valueOf(R_MailText_ID));
}
/** Get Mail Template.
@return Text templates for mailings */
public int getR_MailText_ID() 
{
Integer ii = (Integer)get_Value("R_MailText_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name R_MailText_ID */
public static final String COLUMNNAME_R_MailText_ID = "R_MailText_ID";
}
