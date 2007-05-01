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
/** Generated Model for W_Click
 *  @author Adempiere (generated) 
 *  @version Release 3.2.0 - $Id$ */
public class X_W_Click extends PO
{
/** Standard Constructor
@param ctx context
@param W_Click_ID id
@param trxName transaction
*/
public X_W_Click (Properties ctx, int W_Click_ID, String trxName)
{
super (ctx, W_Click_ID, trxName);
/** if (W_Click_ID == 0)
{
setProcessed (false);
setW_Click_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_W_Click (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=550 */
public static final int Table_ID=MTable.getTable_ID("W_Click");

/** TableName=W_Click */
public static final String Table_Name="W_Click";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"W_Click");

protected BigDecimal accessLevel = BigDecimal.valueOf(3);
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
StringBuffer sb = new StringBuffer ("X_W_Click[").append(get_ID()).append("]");
return sb.toString();
}
/** Set User/Contact.
@param AD_User_ID User within the system - Internal or Business Partner Contact */
public void setAD_User_ID (int AD_User_ID)
{
if (AD_User_ID <= 0) set_Value ("AD_User_ID", null);
 else 
set_Value ("AD_User_ID", Integer.valueOf(AD_User_ID));
}
/** Get User/Contact.
@return User within the system - Internal or Business Partner Contact */
public int getAD_User_ID() 
{
Integer ii = (Integer)get_Value("AD_User_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_User_ID */
public static final String COLUMNNAME_AD_User_ID = "AD_User_ID";
/** Set Accept Language.
@param AcceptLanguage Language accepted based on browser information */
public void setAcceptLanguage (String AcceptLanguage)
{
if (AcceptLanguage != null && AcceptLanguage.length() > 60)
{
log.warning("Length > 60 - truncated");
AcceptLanguage = AcceptLanguage.substring(0,59);
}
set_Value ("AcceptLanguage", AcceptLanguage);
}
/** Get Accept Language.
@return Language accepted based on browser information */
public String getAcceptLanguage() 
{
return (String)get_Value("AcceptLanguage");
}
/** Column name AcceptLanguage */
public static final String COLUMNNAME_AcceptLanguage = "AcceptLanguage";
/** Set EMail Address.
@param EMail Electronic Mail Address */
public void setEMail (String EMail)
{
if (EMail != null && EMail.length() > 60)
{
log.warning("Length > 60 - truncated");
EMail = EMail.substring(0,59);
}
set_Value ("EMail", EMail);
}
/** Get EMail Address.
@return Electronic Mail Address */
public String getEMail() 
{
return (String)get_Value("EMail");
}
/** Column name EMail */
public static final String COLUMNNAME_EMail = "EMail";
/** Set Processed.
@param Processed The document has been processed */
public void setProcessed (boolean Processed)
{
set_Value ("Processed", Boolean.valueOf(Processed));
}
/** Get Processed.
@return The document has been processed */
public boolean isProcessed() 
{
Object oo = get_Value("Processed");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name Processed */
public static final String COLUMNNAME_Processed = "Processed";
/** Set Referrer.
@param Referrer Referring web address */
public void setReferrer (String Referrer)
{
if (Referrer != null && Referrer.length() > 120)
{
log.warning("Length > 120 - truncated");
Referrer = Referrer.substring(0,119);
}
set_Value ("Referrer", Referrer);
}
/** Get Referrer.
@return Referring web address */
public String getReferrer() 
{
return (String)get_Value("Referrer");
}
/** Column name Referrer */
public static final String COLUMNNAME_Referrer = "Referrer";
/** Set Remote Addr.
@param Remote_Addr Remote Address */
public void setRemote_Addr (String Remote_Addr)
{
if (Remote_Addr != null && Remote_Addr.length() > 60)
{
log.warning("Length > 60 - truncated");
Remote_Addr = Remote_Addr.substring(0,59);
}
set_Value ("Remote_Addr", Remote_Addr);
}
/** Get Remote Addr.
@return Remote Address */
public String getRemote_Addr() 
{
return (String)get_Value("Remote_Addr");
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), getRemote_Addr());
}
/** Column name Remote_Addr */
public static final String COLUMNNAME_Remote_Addr = "Remote_Addr";
/** Set Remote Host.
@param Remote_Host Remote host Info */
public void setRemote_Host (String Remote_Host)
{
if (Remote_Host != null && Remote_Host.length() > 120)
{
log.warning("Length > 120 - truncated");
Remote_Host = Remote_Host.substring(0,119);
}
set_Value ("Remote_Host", Remote_Host);
}
/** Get Remote Host.
@return Remote host Info */
public String getRemote_Host() 
{
return (String)get_Value("Remote_Host");
}
/** Column name Remote_Host */
public static final String COLUMNNAME_Remote_Host = "Remote_Host";
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
/** Set User Agent.
@param UserAgent Browser Used */
public void setUserAgent (String UserAgent)
{
if (UserAgent != null && UserAgent.length() > 255)
{
log.warning("Length > 255 - truncated");
UserAgent = UserAgent.substring(0,254);
}
set_Value ("UserAgent", UserAgent);
}
/** Get User Agent.
@return Browser Used */
public String getUserAgent() 
{
return (String)get_Value("UserAgent");
}
/** Column name UserAgent */
public static final String COLUMNNAME_UserAgent = "UserAgent";
/** Set Click Count.
@param W_ClickCount_ID Web Click Management */
public void setW_ClickCount_ID (int W_ClickCount_ID)
{
if (W_ClickCount_ID <= 0) set_ValueNoCheck ("W_ClickCount_ID", null);
 else 
set_ValueNoCheck ("W_ClickCount_ID", Integer.valueOf(W_ClickCount_ID));
}
/** Get Click Count.
@return Web Click Management */
public int getW_ClickCount_ID() 
{
Integer ii = (Integer)get_Value("W_ClickCount_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name W_ClickCount_ID */
public static final String COLUMNNAME_W_ClickCount_ID = "W_ClickCount_ID";
/** Set Web Click.
@param W_Click_ID Individual Web Click */
public void setW_Click_ID (int W_Click_ID)
{
if (W_Click_ID < 1) throw new IllegalArgumentException ("W_Click_ID is mandatory.");
set_ValueNoCheck ("W_Click_ID", Integer.valueOf(W_Click_ID));
}
/** Get Web Click.
@return Individual Web Click */
public int getW_Click_ID() 
{
Integer ii = (Integer)get_Value("W_Click_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name W_Click_ID */
public static final String COLUMNNAME_W_Click_ID = "W_Click_ID";
}
