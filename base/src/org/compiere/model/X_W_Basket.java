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
/** Generated Model for W_Basket
 *  @author Adempiere (generated) 
 *  @version Release 3.3.0 - $Id$ */
public class X_W_Basket extends PO
{
/** Standard Constructor
@param ctx context
@param W_Basket_ID id
@param trxName transaction
*/
public X_W_Basket (Properties ctx, int W_Basket_ID, String trxName)
{
super (ctx, W_Basket_ID, trxName);
/** if (W_Basket_ID == 0)
{
setAD_User_ID (0);
setSession_ID (0);
setW_Basket_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_W_Basket (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** TableName=W_Basket */
public static final String Table_Name="W_Basket";

/** AD_Table_ID=402 */
public static final int Table_ID=MTable.getTable_ID(Table_Name);

protected static KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

protected BigDecimal accessLevel = BigDecimal.valueOf(4);
/** AccessLevel
@return 4 - System 
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
StringBuffer sb = new StringBuffer ("X_W_Basket[").append(get_ID()).append("]");
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
/** Set Business Partner .
@param C_BPartner_ID Identifies a Business Partner */
public void setC_BPartner_ID (int C_BPartner_ID)
{
if (C_BPartner_ID <= 0) set_Value ("C_BPartner_ID", null);
 else 
set_Value ("C_BPartner_ID", Integer.valueOf(C_BPartner_ID));
}
/** Get Business Partner .
@return Identifies a Business Partner */
public int getC_BPartner_ID() 
{
Integer ii = (Integer)get_Value("C_BPartner_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_BPartner_ID */
public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";
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
/** Set Price List.
@param M_PriceList_ID Unique identifier of a Price List */
public void setM_PriceList_ID (int M_PriceList_ID)
{
if (M_PriceList_ID <= 0) set_Value ("M_PriceList_ID", null);
 else 
set_Value ("M_PriceList_ID", Integer.valueOf(M_PriceList_ID));
}
/** Get Price List.
@return Unique identifier of a Price List */
public int getM_PriceList_ID() 
{
Integer ii = (Integer)get_Value("M_PriceList_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name M_PriceList_ID */
public static final String COLUMNNAME_M_PriceList_ID = "M_PriceList_ID";
/** Set Session ID.
@param Session_ID Session ID */
public void setSession_ID (int Session_ID)
{
if (Session_ID < 1) throw new IllegalArgumentException ("Session_ID is mandatory.");
set_Value ("Session_ID", Integer.valueOf(Session_ID));
}
/** Get Session ID.
@return Session ID */
public int getSession_ID() 
{
Integer ii = (Integer)get_Value("Session_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getSession_ID()));
}
/** Column name Session_ID */
public static final String COLUMNNAME_Session_ID = "Session_ID";
/** Set W_Basket_ID.
@param W_Basket_ID Web Basket */
public void setW_Basket_ID (int W_Basket_ID)
{
if (W_Basket_ID < 1) throw new IllegalArgumentException ("W_Basket_ID is mandatory.");
set_ValueNoCheck ("W_Basket_ID", Integer.valueOf(W_Basket_ID));
}
/** Get W_Basket_ID.
@return Web Basket */
public int getW_Basket_ID() 
{
Integer ii = (Integer)get_Value("W_Basket_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name W_Basket_ID */
public static final String COLUMNNAME_W_Basket_ID = "W_Basket_ID";
}
