/******************************************************************************
 * Product: Compiere ERP & CRM Smart Business Solution                        *
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
/** Generated Model for T_DistributionRunDetail
 *  @author Jorg Janke (generated) 
 *  @version Release 2.6.0a - $Id$ */
public class X_T_DistributionRunDetail extends PO
{
/** Standard Constructor
@param ctx context
@param T_DistributionRunDetail_ID id
@param trxName transaction
*/
public X_T_DistributionRunDetail (Properties ctx, int T_DistributionRunDetail_ID, String trxName)
{
super (ctx, T_DistributionRunDetail_ID, trxName);
/** if (T_DistributionRunDetail_ID == 0)
{
setC_BPartner_ID (0);
setC_BPartner_Location_ID (0);
setM_DistributionListLine_ID (0);
setM_DistributionList_ID (0);
setM_DistributionRunLine_ID (0);
setM_DistributionRun_ID (0);
setM_Product_ID (0);
setMinQty (Env.ZERO);
setQty (Env.ZERO);
setRatio (Env.ZERO);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_T_DistributionRunDetail (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=714 */
public static final int Table_ID=714;

/** TableName=T_DistributionRunDetail */
public static final String Table_Name="T_DistributionRunDetail";

protected static KeyNamePair Model = new KeyNamePair(714,"T_DistributionRunDetail");

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
StringBuffer sb = new StringBuffer ("X_T_DistributionRunDetail[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Business Partner .
@param C_BPartner_ID Identifies a Business Partner */
public void setC_BPartner_ID (int C_BPartner_ID)
{
if (C_BPartner_ID < 1) throw new IllegalArgumentException ("C_BPartner_ID is mandatory.");
set_Value ("C_BPartner_ID", new Integer(C_BPartner_ID));
}
/** Get Business Partner .
@return Identifies a Business Partner */
public int getC_BPartner_ID() 
{
Integer ii = (Integer)get_Value("C_BPartner_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Partner Location.
@param C_BPartner_Location_ID Identifies the (ship to) address for this Business Partner */
public void setC_BPartner_Location_ID (int C_BPartner_Location_ID)
{
if (C_BPartner_Location_ID < 1) throw new IllegalArgumentException ("C_BPartner_Location_ID is mandatory.");
set_Value ("C_BPartner_Location_ID", new Integer(C_BPartner_Location_ID));
}
/** Get Partner Location.
@return Identifies the (ship to) address for this Business Partner */
public int getC_BPartner_Location_ID() 
{
Integer ii = (Integer)get_Value("C_BPartner_Location_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Distribution List Line.
@param M_DistributionListLine_ID Distribution List Line with Business Partner and Quantity/Percentage */
public void setM_DistributionListLine_ID (int M_DistributionListLine_ID)
{
if (M_DistributionListLine_ID < 1) throw new IllegalArgumentException ("M_DistributionListLine_ID is mandatory.");
set_ValueNoCheck ("M_DistributionListLine_ID", new Integer(M_DistributionListLine_ID));
}
/** Get Distribution List Line.
@return Distribution List Line with Business Partner and Quantity/Percentage */
public int getM_DistributionListLine_ID() 
{
Integer ii = (Integer)get_Value("M_DistributionListLine_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Distribution List.
@param M_DistributionList_ID Distribution Lists allow to distribute products to a selected list of partners */
public void setM_DistributionList_ID (int M_DistributionList_ID)
{
if (M_DistributionList_ID < 1) throw new IllegalArgumentException ("M_DistributionList_ID is mandatory.");
set_ValueNoCheck ("M_DistributionList_ID", new Integer(M_DistributionList_ID));
}
/** Get Distribution List.
@return Distribution Lists allow to distribute products to a selected list of partners */
public int getM_DistributionList_ID() 
{
Integer ii = (Integer)get_Value("M_DistributionList_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Distribution Run Line.
@param M_DistributionRunLine_ID Distribution Run Lines define Distribution List, the Product and Quantiries */
public void setM_DistributionRunLine_ID (int M_DistributionRunLine_ID)
{
if (M_DistributionRunLine_ID < 1) throw new IllegalArgumentException ("M_DistributionRunLine_ID is mandatory.");
set_ValueNoCheck ("M_DistributionRunLine_ID", new Integer(M_DistributionRunLine_ID));
}
/** Get Distribution Run Line.
@return Distribution Run Lines define Distribution List, the Product and Quantiries */
public int getM_DistributionRunLine_ID() 
{
Integer ii = (Integer)get_Value("M_DistributionRunLine_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Distribution Run.
@param M_DistributionRun_ID Distribution Run create Orders to distribute products to a selected list of partners */
public void setM_DistributionRun_ID (int M_DistributionRun_ID)
{
if (M_DistributionRun_ID < 1) throw new IllegalArgumentException ("M_DistributionRun_ID is mandatory.");
set_ValueNoCheck ("M_DistributionRun_ID", new Integer(M_DistributionRun_ID));
}
/** Get Distribution Run.
@return Distribution Run create Orders to distribute products to a selected list of partners */
public int getM_DistributionRun_ID() 
{
Integer ii = (Integer)get_Value("M_DistributionRun_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getM_DistributionRun_ID()));
}
/** Set Product.
@param M_Product_ID Product, Service, Item */
public void setM_Product_ID (int M_Product_ID)
{
if (M_Product_ID < 1) throw new IllegalArgumentException ("M_Product_ID is mandatory.");
set_Value ("M_Product_ID", new Integer(M_Product_ID));
}
/** Get Product.
@return Product, Service, Item */
public int getM_Product_ID() 
{
Integer ii = (Integer)get_Value("M_Product_ID");
if (ii == null) return 0;
return ii.intValue();
}
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
/** Set Quantity.
@param Qty Quantity */
public void setQty (BigDecimal Qty)
{
if (Qty == null) throw new IllegalArgumentException ("Qty is mandatory.");
set_Value ("Qty", Qty);
}
/** Get Quantity.
@return Quantity */
public BigDecimal getQty() 
{
BigDecimal bd = (BigDecimal)get_Value("Qty");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Ratio.
@param Ratio Relative Ratio for Distributions */
public void setRatio (BigDecimal Ratio)
{
if (Ratio == null) throw new IllegalArgumentException ("Ratio is mandatory.");
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
}
