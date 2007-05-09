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
/** Generated Model for C_LandedCost
 *  @author Adempiere (generated) 
 *  @version Release 3.2.0 - $Id$ */
public class X_C_LandedCost extends PO
{
/** Standard Constructor
@param ctx context
@param C_LandedCost_ID id
@param trxName transaction
*/
public X_C_LandedCost (Properties ctx, int C_LandedCost_ID, String trxName)
{
super (ctx, C_LandedCost_ID, trxName);
/** if (C_LandedCost_ID == 0)
{
setC_InvoiceLine_ID (0);
setC_LandedCost_ID (0);
setLandedCostDistribution (null);	// Q
setM_CostElement_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_LandedCost (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=759 */
public static final int Table_ID=MTable.getTable_ID("C_LandedCost");

/** TableName=C_LandedCost */
public static final String Table_Name="C_LandedCost";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"C_LandedCost");

protected BigDecimal accessLevel = BigDecimal.valueOf(1);
/** AccessLevel
@return 1 - Org 
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
StringBuffer sb = new StringBuffer ("X_C_LandedCost[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Invoice Line.
@param C_InvoiceLine_ID Invoice Detail Line */
public void setC_InvoiceLine_ID (int C_InvoiceLine_ID)
{
if (C_InvoiceLine_ID < 1) throw new IllegalArgumentException ("C_InvoiceLine_ID is mandatory.");
set_ValueNoCheck ("C_InvoiceLine_ID", Integer.valueOf(C_InvoiceLine_ID));
}
/** Get Invoice Line.
@return Invoice Detail Line */
public int getC_InvoiceLine_ID() 
{
Integer ii = (Integer)get_Value("C_InvoiceLine_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getC_InvoiceLine_ID()));
}
/** Column name C_InvoiceLine_ID */
public static final String COLUMNNAME_C_InvoiceLine_ID = "C_InvoiceLine_ID";
/** Set Landed Cost.
@param C_LandedCost_ID Landed cost to be allocated to material receipts */
public void setC_LandedCost_ID (int C_LandedCost_ID)
{
if (C_LandedCost_ID < 1) throw new IllegalArgumentException ("C_LandedCost_ID is mandatory.");
set_ValueNoCheck ("C_LandedCost_ID", Integer.valueOf(C_LandedCost_ID));
}
/** Get Landed Cost.
@return Landed cost to be allocated to material receipts */
public int getC_LandedCost_ID() 
{
Integer ii = (Integer)get_Value("C_LandedCost_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_LandedCost_ID */
public static final String COLUMNNAME_C_LandedCost_ID = "C_LandedCost_ID";
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

/** LandedCostDistribution AD_Reference_ID=339 */
public static final int LANDEDCOSTDISTRIBUTION_AD_Reference_ID=339;
/** Costs = C */
public static final String LANDEDCOSTDISTRIBUTION_Costs = "C";
/** Line = L */
public static final String LANDEDCOSTDISTRIBUTION_Line = "L";
/** Quantity = Q */
public static final String LANDEDCOSTDISTRIBUTION_Quantity = "Q";
/** Volume = V */
public static final String LANDEDCOSTDISTRIBUTION_Volume = "V";
/** Weight = W */
public static final String LANDEDCOSTDISTRIBUTION_Weight = "W";
/** Set Cost Distribution.
@param LandedCostDistribution Landed Cost Distribution */
public void setLandedCostDistribution (String LandedCostDistribution)
{
if (LandedCostDistribution == null) throw new IllegalArgumentException ("LandedCostDistribution is mandatory");
if (LandedCostDistribution.equals("C") || LandedCostDistribution.equals("L") || LandedCostDistribution.equals("Q") || LandedCostDistribution.equals("V") || LandedCostDistribution.equals("W"));
 else throw new IllegalArgumentException ("LandedCostDistribution Invalid value - " + LandedCostDistribution + " - Reference_ID=339 - C - L - Q - V - W");
if (LandedCostDistribution.length() > 1)
{
log.warning("Length > 1 - truncated");
LandedCostDistribution = LandedCostDistribution.substring(0,0);
}
set_Value ("LandedCostDistribution", LandedCostDistribution);
}
/** Get Cost Distribution.
@return Landed Cost Distribution */
public String getLandedCostDistribution() 
{
return (String)get_Value("LandedCostDistribution");
}
/** Column name LandedCostDistribution */
public static final String COLUMNNAME_LandedCostDistribution = "LandedCostDistribution";
/** Set Cost Element.
@param M_CostElement_ID Product Cost Element */
public void setM_CostElement_ID (int M_CostElement_ID)
{
if (M_CostElement_ID < 1) throw new IllegalArgumentException ("M_CostElement_ID is mandatory.");
set_Value ("M_CostElement_ID", Integer.valueOf(M_CostElement_ID));
}
/** Get Cost Element.
@return Product Cost Element */
public int getM_CostElement_ID() 
{
Integer ii = (Integer)get_Value("M_CostElement_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name M_CostElement_ID */
public static final String COLUMNNAME_M_CostElement_ID = "M_CostElement_ID";
/** Set Shipment/Receipt Line.
@param M_InOutLine_ID Line on Shipment or Receipt document */
public void setM_InOutLine_ID (int M_InOutLine_ID)
{
if (M_InOutLine_ID <= 0) set_Value ("M_InOutLine_ID", null);
 else 
set_Value ("M_InOutLine_ID", Integer.valueOf(M_InOutLine_ID));
}
/** Get Shipment/Receipt Line.
@return Line on Shipment or Receipt document */
public int getM_InOutLine_ID() 
{
Integer ii = (Integer)get_Value("M_InOutLine_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name M_InOutLine_ID */
public static final String COLUMNNAME_M_InOutLine_ID = "M_InOutLine_ID";
/** Set Shipment/Receipt.
@param M_InOut_ID Material Shipment Document */
public void setM_InOut_ID (int M_InOut_ID)
{
if (M_InOut_ID <= 0) set_Value ("M_InOut_ID", null);
 else 
set_Value ("M_InOut_ID", Integer.valueOf(M_InOut_ID));
}
/** Get Shipment/Receipt.
@return Material Shipment Document */
public int getM_InOut_ID() 
{
Integer ii = (Integer)get_Value("M_InOut_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name M_InOut_ID */
public static final String COLUMNNAME_M_InOut_ID = "M_InOut_ID";
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
/** Column name M_Product_ID */
public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";
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
/** Column name Processing */
public static final String COLUMNNAME_Processing = "Processing";
}
