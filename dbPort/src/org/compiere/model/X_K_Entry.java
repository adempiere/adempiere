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
/** Generated Model for K_Entry
 *  @author Jorg Janke (generated) 
 *  @version Release 2.5.3d - 2006-10-10 21:56:00.421 */
public class X_K_Entry extends PO
{
/** Standard Constructor
@param ctx context
@param K_Entry_ID id
@param trxName transaction
*/
public X_K_Entry (Properties ctx, int K_Entry_ID, String trxName)
{
super (ctx, K_Entry_ID, trxName);
/** if (K_Entry_ID == 0)
{
setIsPublic (true);	// Y
setK_Entry_ID (0);
setK_Topic_ID (0);
setName (null);
setRating (0);
setTextMsg (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_K_Entry (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=612 */
public static final int Table_ID=612;

/** TableName=K_Entry */
public static final String Table_Name="K_Entry";

protected static KeyNamePair Model = new KeyNamePair(612,"K_Entry");

protected BigDecimal accessLevel = new BigDecimal(3);
/** AccessLevel
@return 3 - Client - Org 
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
StringBuffer sb = new StringBuffer ("X_K_Entry[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Session.
@param AD_Session_ID User Session Online or Web */
public void setAD_Session_ID (int AD_Session_ID)
{
if (AD_Session_ID <= 0) set_ValueNoCheck ("AD_Session_ID", null);
 else 
set_ValueNoCheck ("AD_Session_ID", new Integer(AD_Session_ID));
}
/** Get Session.
@return User Session Online or Web */
public int getAD_Session_ID() 
{
Integer ii = (Integer)get_Value("AD_Session_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Description URL.
@param DescriptionURL URL for the description */
public void setDescriptionURL (String DescriptionURL)
{
if (DescriptionURL != null && DescriptionURL.length() > 120)
{
log.warning("Length > 120 - truncated");
DescriptionURL = DescriptionURL.substring(0,119);
}
set_Value ("DescriptionURL", DescriptionURL);
}
/** Get Description URL.
@return URL for the description */
public String getDescriptionURL() 
{
return (String)get_Value("DescriptionURL");
}
/** Set Public.
@param IsPublic Public can read entry */
public void setIsPublic (boolean IsPublic)
{
set_Value ("IsPublic", new Boolean(IsPublic));
}
/** Get Public.
@return Public can read entry */
public boolean isPublic() 
{
Object oo = get_Value("IsPublic");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Entry.
@param K_Entry_ID Knowledge Entry */
public void setK_Entry_ID (int K_Entry_ID)
{
if (K_Entry_ID < 1) throw new IllegalArgumentException ("K_Entry_ID is mandatory.");
set_ValueNoCheck ("K_Entry_ID", new Integer(K_Entry_ID));
}
/** Get Entry.
@return Knowledge Entry */
public int getK_Entry_ID() 
{
Integer ii = (Integer)get_Value("K_Entry_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Knowledge Source.
@param K_Source_ID Source of a Knowledge Entry */
public void setK_Source_ID (int K_Source_ID)
{
if (K_Source_ID <= 0) set_Value ("K_Source_ID", null);
 else 
set_Value ("K_Source_ID", new Integer(K_Source_ID));
}
/** Get Knowledge Source.
@return Source of a Knowledge Entry */
public int getK_Source_ID() 
{
Integer ii = (Integer)get_Value("K_Source_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Knowledge Topic.
@param K_Topic_ID Knowledge Topic */
public void setK_Topic_ID (int K_Topic_ID)
{
if (K_Topic_ID < 1) throw new IllegalArgumentException ("K_Topic_ID is mandatory.");
set_ValueNoCheck ("K_Topic_ID", new Integer(K_Topic_ID));
}
/** Get Knowledge Topic.
@return Knowledge Topic */
public int getK_Topic_ID() 
{
Integer ii = (Integer)get_Value("K_Topic_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Keywords.
@param Keywords List of Keywords - separated by space, comma or semicolon */
public void setKeywords (String Keywords)
{
if (Keywords != null && Keywords.length() > 255)
{
log.warning("Length > 255 - truncated");
Keywords = Keywords.substring(0,254);
}
set_Value ("Keywords", Keywords);
}
/** Get Keywords.
@return List of Keywords - separated by space, comma or semicolon */
public String getKeywords() 
{
return (String)get_Value("Keywords");
}
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
/** Set Rating.
@param Rating Classification or Importance */
public void setRating (int Rating)
{
set_Value ("Rating", new Integer(Rating));
}
/** Get Rating.
@return Classification or Importance */
public int getRating() 
{
Integer ii = (Integer)get_Value("Rating");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Text Message.
@param TextMsg Text Message */
public void setTextMsg (String TextMsg)
{
if (TextMsg == null) throw new IllegalArgumentException ("TextMsg is mandatory.");
if (TextMsg.length() > 2000)
{
log.warning("Length > 2000 - truncated");
TextMsg = TextMsg.substring(0,1999);
}
set_Value ("TextMsg", TextMsg);
}
/** Get Text Message.
@return Text Message */
public String getTextMsg() 
{
return (String)get_Value("TextMsg");
}
/** Set Valid to.
@param ValidTo Valid to including this date (last day) */
public void setValidTo (Timestamp ValidTo)
{
set_Value ("ValidTo", ValidTo);
}
/** Get Valid to.
@return Valid to including this date (last day) */
public Timestamp getValidTo() 
{
return (Timestamp)get_Value("ValidTo");
}
}
