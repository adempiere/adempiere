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
/** Generated Model for B_Bid
 *  @author Adempiere (generated) 
 *  @version Release 3.1.6 - $Id$ */
public class X_B_Bid extends PO
{
/** Standard Constructor
@param ctx context
@param B_Bid_ID id
@param trxName transaction
*/
public X_B_Bid (Properties ctx, int B_Bid_ID, String trxName)
{
super (ctx, B_Bid_ID, trxName);
/** if (B_Bid_ID == 0)
{
setAD_User_ID (0);
setB_Bid_ID (0);
setB_BuyerFunds_ID (0);
setB_Topic_ID (0);
setIsWillingToCommit (false);
setName (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_B_Bid (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=686 */
public static final int Table_ID=MTable.getTable_ID("B_Bid");

/** TableName=B_Bid */
public static final String Table_Name="B_Bid";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"B_Bid");

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
StringBuffer sb = new StringBuffer ("X_B_Bid[").append(get_ID()).append("]");
return sb.toString();
}
/** Set User/Contact.
@param AD_User_ID User within the system - Internal or Business Partner Contact */
public void setAD_User_ID (int AD_User_ID)
{
if (AD_User_ID < 1) throw new IllegalArgumentException ("AD_User_ID is mandatory.");
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
/** Set Bid.
@param B_Bid_ID Bid for a Topic */
public void setB_Bid_ID (int B_Bid_ID)
{
if (B_Bid_ID < 1) throw new IllegalArgumentException ("B_Bid_ID is mandatory.");
set_ValueNoCheck ("B_Bid_ID", Integer.valueOf(B_Bid_ID));
}
/** Get Bid.
@return Bid for a Topic */
public int getB_Bid_ID() 
{
Integer ii = (Integer)get_Value("B_Bid_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name B_Bid_ID */
public static final String COLUMNNAME_B_Bid_ID = "B_Bid_ID";
/** Set Buyer Funds.
@param B_BuyerFunds_ID Buyer Funds for Bids on Topics */
public void setB_BuyerFunds_ID (int B_BuyerFunds_ID)
{
if (B_BuyerFunds_ID < 1) throw new IllegalArgumentException ("B_BuyerFunds_ID is mandatory.");
set_Value ("B_BuyerFunds_ID", Integer.valueOf(B_BuyerFunds_ID));
}
/** Get Buyer Funds.
@return Buyer Funds for Bids on Topics */
public int getB_BuyerFunds_ID() 
{
Integer ii = (Integer)get_Value("B_BuyerFunds_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name B_BuyerFunds_ID */
public static final String COLUMNNAME_B_BuyerFunds_ID = "B_BuyerFunds_ID";
/** Set Topic.
@param B_Topic_ID Auction Topic */
public void setB_Topic_ID (int B_Topic_ID)
{
if (B_Topic_ID < 1) throw new IllegalArgumentException ("B_Topic_ID is mandatory.");
set_Value ("B_Topic_ID", Integer.valueOf(B_Topic_ID));
}
/** Get Topic.
@return Auction Topic */
public int getB_Topic_ID() 
{
Integer ii = (Integer)get_Value("B_Topic_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name B_Topic_ID */
public static final String COLUMNNAME_B_Topic_ID = "B_Topic_ID";
/** Set Willing to commit.
@param IsWillingToCommit Willing to commit */
public void setIsWillingToCommit (boolean IsWillingToCommit)
{
set_Value ("IsWillingToCommit", Boolean.valueOf(IsWillingToCommit));
}
/** Get Willing to commit.
@return Willing to commit */
public boolean isWillingToCommit() 
{
Object oo = get_Value("IsWillingToCommit");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsWillingToCommit */
public static final String COLUMNNAME_IsWillingToCommit = "IsWillingToCommit";
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
/** Set Private Note.
@param PrivateNote Private Note - not visible to the other parties */
public void setPrivateNote (String PrivateNote)
{
if (PrivateNote != null && PrivateNote.length() > 2000)
{
log.warning("Length > 2000 - truncated");
PrivateNote = PrivateNote.substring(0,1999);
}
set_Value ("PrivateNote", PrivateNote);
}
/** Get Private Note.
@return Private Note - not visible to the other parties */
public String getPrivateNote() 
{
return (String)get_Value("PrivateNote");
}
/** Column name PrivateNote */
public static final String COLUMNNAME_PrivateNote = "PrivateNote";
/** Set Text Message.
@param TextMsg Text Message */
public void setTextMsg (String TextMsg)
{
if (TextMsg != null && TextMsg.length() > 2000)
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
/** Column name TextMsg */
public static final String COLUMNNAME_TextMsg = "TextMsg";
}
