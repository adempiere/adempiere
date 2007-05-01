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
/** Generated Model for M_DistributionListLine
 *  @author Adempiere (generated) 
 *  @version Release 3.2.0 - $Id$ */
public class X_M_DistributionListLine extends PO
{
/** Standard Constructor
@param ctx context
@param M_DistributionListLine_ID id
@param trxName transaction
*/
public X_M_DistributionListLine (Properties ctx, int M_DistributionListLine_ID, String trxName)
{
super (ctx, M_DistributionListLine_ID, trxName);
/** if (M_DistributionListLine_ID == 0)
{
setC_BPartner_ID (0);
setC_BPartner_Location_ID (0);
setM_DistributionListLine_ID (0);
setM_DistributionList_ID (0);
setMinQty (Env.ZERO);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_M_DistributionListLine (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=665 */
public static final int Table_ID=MTable.getTable_ID("M_DistributionListLine");

/** TableName=M_DistributionListLine */
public static final String Table_Name="M_DistributionListLine";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"M_DistributionListLine");

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
StringBuffer sb = new StringBuffer ("X_M_DistributionListLine[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Business Partner .
@param C_BPartner_ID Identifies a Business Partner */
public void setC_BPartner_ID (int C_BPartner_ID)
{
if (C_BPartner_ID < 1) throw new IllegalArgumentException ("C_BPartner_ID is mandatory.");
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
/** Set Partner Location.
@param C_BPartner_Location_ID Identifies the (ship to) address for this Business Partner */
public void setC_BPartner_Location_ID (int C_BPartner_Location_ID)
{
if (C_BPartner_Location_ID < 1) throw new IllegalArgumentException ("C_BPartner_Location_ID is mandatory.");
set_Value ("C_BPartner_Location_ID", Integer.valueOf(C_BPartner_Location_ID));
}
/** Get Partner Location.
@return Identifies the (ship to) address for this Business Partner */
public int getC_BPartner_Location_ID() 
{
Integer ii = (Integer)get_Value("C_BPartner_Location_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_BPartner_Location_ID */
public static final String COLUMNNAME_C_BPartner_Location_ID = "C_BPartner_Location_ID";
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
/** Set Distribution List Line.
@param M_DistributionListLine_ID Distribution List Line with Business Partner and Quantity/Percentage */
public void setM_DistributionListLine_ID (int M_DistributionListLine_ID)
{
if (M_DistributionListLine_ID < 1) throw new IllegalArgumentException ("M_DistributionListLine_ID is mandatory.");
set_ValueNoCheck ("M_DistributionListLine_ID", Integer.valueOf(M_DistributionListLine_ID));
}
/** Get Distribution List Line.
@return Distribution List Line with Business Partner and Quantity/Percentage */
public int getM_DistributionListLine_ID() 
{
Integer ii = (Integer)get_Value("M_DistributionListLine_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name M_DistributionListLine_ID */
public static final String COLUMNNAME_M_DistributionListLine_ID = "M_DistributionListLine_ID";
/** Set Distribution List.
@param M_DistributionList_ID Distribution Lists allow to distribute products to a selected list of partners */
public void setM_DistributionList_ID (int M_DistributionList_ID)
{
if (M_DistributionList_ID < 1) throw new IllegalArgumentException ("M_DistributionList_ID is mandatory.");
set_ValueNoCheck ("M_DistributionList_ID", Integer.valueOf(M_DistributionList_ID));
}
/** Get Distribution List.
@return Distribution Lists allow to distribute products to a selected list of partners */
public int getM_DistributionList_ID() 
{
Integer ii = (Integer)get_Value("M_DistributionList_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getM_DistributionList_ID()));
}
/** Column name M_DistributionList_ID */
public static final String COLUMNNAME_M_DistributionList_ID = "M_DistributionList_ID";
/** Set Minimum Quantity.
@param MinQty Minimum quantity for the business partner */
public void setMinQty (BigDecimal MinQty)
{
if (MinQty == null) throw new IllegalArgumentException ("MinQty is mandatory.");
set_Value ("MinQty", MinQty);
}
/** Get Minimum Quantity.
@return Minimum quantity for the business partner */
public BigDecimal getMinQty() 
{
BigDecimal bd = (BigDecimal)get_Value("MinQty");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name MinQty */
public static final String COLUMNNAME_MinQty = "MinQty";
/** Set Ratio.
@param Ratio Relative Ratio for Distributions */
public void setRatio (BigDecimal Ratio)
{
set_Value ("Ratio", Ratio);
}
/** Get Ratio.
@return Relative Ratio for Distributions */
public BigDecimal getRatio() 
{
BigDecimal bd = (BigDecimal)get_Value("Ratio");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name Ratio */
public static final String COLUMNNAME_Ratio = "Ratio";
}
