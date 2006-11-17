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
/** Generated Model for GL_FundRestriction
 *  @author Jorg Janke (generated) 
 *  @version Release 2.5.3d - 2006-10-10 21:55:59.265 */
public class X_GL_FundRestriction extends PO
{
/** Standard Constructor
@param ctx context
@param GL_FundRestriction_ID id
@param trxName transaction
*/
public X_GL_FundRestriction (Properties ctx, int GL_FundRestriction_ID, String trxName)
{
super (ctx, GL_FundRestriction_ID, trxName);
/** if (GL_FundRestriction_ID == 0)
{
setC_ElementValue_ID (0);
setGL_FundRestriction_ID (0);
setGL_Fund_ID (0);
setName (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_GL_FundRestriction (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=824 */
public static final int Table_ID=824;

/** TableName=GL_FundRestriction */
public static final String Table_Name="GL_FundRestriction";

protected static KeyNamePair Model = new KeyNamePair(824,"GL_FundRestriction");

protected BigDecimal accessLevel = new BigDecimal(2);
/** AccessLevel
@return 2 - Client 
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
StringBuffer sb = new StringBuffer ("X_GL_FundRestriction[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Account Element.
@param C_ElementValue_ID Account Element */
public void setC_ElementValue_ID (int C_ElementValue_ID)
{
if (C_ElementValue_ID < 1) throw new IllegalArgumentException ("C_ElementValue_ID is mandatory.");
set_Value ("C_ElementValue_ID", new Integer(C_ElementValue_ID));
}
/** Get Account Element.
@return Account Element */
public int getC_ElementValue_ID() 
{
Integer ii = (Integer)get_Value("C_ElementValue_ID");
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
/** Set Fund Restriction.
@param GL_FundRestriction_ID Restriction of Funds */
public void setGL_FundRestriction_ID (int GL_FundRestriction_ID)
{
if (GL_FundRestriction_ID < 1) throw new IllegalArgumentException ("GL_FundRestriction_ID is mandatory.");
set_ValueNoCheck ("GL_FundRestriction_ID", new Integer(GL_FundRestriction_ID));
}
/** Get Fund Restriction.
@return Restriction of Funds */
public int getGL_FundRestriction_ID() 
{
Integer ii = (Integer)get_Value("GL_FundRestriction_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set GL Fund.
@param GL_Fund_ID General Ledger Funds Control */
public void setGL_Fund_ID (int GL_Fund_ID)
{
if (GL_Fund_ID < 1) throw new IllegalArgumentException ("GL_Fund_ID is mandatory.");
set_ValueNoCheck ("GL_Fund_ID", new Integer(GL_Fund_ID));
}
/** Get GL Fund.
@return General Ledger Funds Control */
public int getGL_Fund_ID() 
{
Integer ii = (Integer)get_Value("GL_Fund_ID");
if (ii == null) return 0;
return ii.intValue();
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
}
