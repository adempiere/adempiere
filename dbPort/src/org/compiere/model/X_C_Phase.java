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
/** Generated Model for C_Phase
 *  @author Jorg Janke (generated) 
 *  @version Release 3.1.3 - $Id$ */
public class X_C_Phase extends PO
{
/** Standard Constructor
@param ctx context
@param C_Phase_ID id
@param trxName transaction
*/
public X_C_Phase (Properties ctx, int C_Phase_ID, String trxName)
{
super (ctx, C_Phase_ID, trxName);
/** if (C_Phase_ID == 0)
{
setC_Phase_ID (0);
setC_ProjectType_ID (0);
setName (null);
setSeqNo (0);	// @SQL=SELECT NVL(MAX(SeqNo),0)+10 AS DefaultValue FROM C_Phase WHERE C_ProjectType_ID=@C_ProjectType_ID@
setStandardQty (Env.ZERO);	// 1
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_Phase (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=577 */
public static final int Table_ID=MTable.getTable_ID("C_Phase");

/** TableName=C_Phase */
public static final String Table_Name="C_Phase";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"C_Phase");

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
StringBuffer sb = new StringBuffer ("X_C_Phase[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Standard Phase.
@param C_Phase_ID Standard Phase of the Project Type */
public void setC_Phase_ID (int C_Phase_ID)
{
if (C_Phase_ID < 1) throw new IllegalArgumentException ("C_Phase_ID is mandatory.");
set_ValueNoCheck ("C_Phase_ID", Integer.valueOf(C_Phase_ID));
}
/** Get Standard Phase.
@return Standard Phase of the Project Type */
public int getC_Phase_ID() 
{
Integer ii = (Integer)get_Value("C_Phase_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Project Type.
@param C_ProjectType_ID Type of the project */
public void setC_ProjectType_ID (int C_ProjectType_ID)
{
if (C_ProjectType_ID < 1) throw new IllegalArgumentException ("C_ProjectType_ID is mandatory.");
set_ValueNoCheck ("C_ProjectType_ID", Integer.valueOf(C_ProjectType_ID));
}
/** Get Project Type.
@return Type of the project */
public int getC_ProjectType_ID() 
{
Integer ii = (Integer)get_Value("C_ProjectType_ID");
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
/** Set Product.
@param M_Product_ID Product, Service, Item */
public void setM_Product_ID (int M_Product_ID)
{
if (M_Product_ID <= 0) set_Value ("M_Product_ID", null);
 else 
set_Value ("M_Product_ID", Integer.valueOf(M_Product_ID));
}
/** Get Product.
@return Product, Service, Item */
public int getM_Product_ID() 
{
Integer ii = (Integer)get_Value("M_Product_ID");
if (ii == null) return 0;
return ii.intValue();
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
/** Set Sequence.
@param SeqNo Method of ordering records;
 lowest number comes first */
public void setSeqNo (int SeqNo)
{
set_Value ("SeqNo", Integer.valueOf(SeqNo));
}
/** Get Sequence.
@return Method of ordering records;
 lowest number comes first */
public int getSeqNo() 
{
Integer ii = (Integer)get_Value("SeqNo");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Standard Quantity.
@param StandardQty Standard Quantity */
public void setStandardQty (BigDecimal StandardQty)
{
if (StandardQty == null) throw new IllegalArgumentException ("StandardQty is mandatory.");
set_Value ("StandardQty", StandardQty);
}
/** Get Standard Quantity.
@return Standard Quantity */
public BigDecimal getStandardQty() 
{
BigDecimal bd = (BigDecimal)get_Value("StandardQty");
if (bd == null) return Env.ZERO;
return bd;
}
}
