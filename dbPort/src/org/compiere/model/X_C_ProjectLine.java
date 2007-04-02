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
/** Generated Model for C_ProjectLine
 *  @author Adempiere (generated) 
 *  @version Release 3.1.6 - $Id$ */
public class X_C_ProjectLine extends PO
{
/** Standard Constructor
@param ctx context
@param C_ProjectLine_ID id
@param trxName transaction
*/
public X_C_ProjectLine (Properties ctx, int C_ProjectLine_ID, String trxName)
{
super (ctx, C_ProjectLine_ID, trxName);
/** if (C_ProjectLine_ID == 0)
{
setC_ProjectLine_ID (0);
setC_Project_ID (0);
setInvoicedAmt (Env.ZERO);
setInvoicedQty (Env.ZERO);	// 0
setIsPrinted (true);	// Y
setLine (0);	// @SQL=SELECT NVL(MAX(Line),0)+10 AS DefaultValue FROM C_ProjectLine WHERE C_Project_ID=@C_Project_ID@
setPlannedAmt (Env.ZERO);
setPlannedMarginAmt (Env.ZERO);
setPlannedPrice (Env.ZERO);
setPlannedQty (Env.ZERO);	// 1
setProcessed (false);	// N
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_ProjectLine (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=434 */
public static final int Table_ID=MTable.getTable_ID("C_ProjectLine");

/** TableName=C_ProjectLine */
public static final String Table_Name="C_ProjectLine";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"C_ProjectLine");

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
StringBuffer sb = new StringBuffer ("X_C_ProjectLine[").append(get_ID()).append("]");
return sb.toString();
}

/** C_OrderPO_ID AD_Reference_ID=290 */
public static final int C_ORDERPO_ID_AD_Reference_ID=290;
/** Set Purchase Order.
@param C_OrderPO_ID Purchase Order */
public void setC_OrderPO_ID (int C_OrderPO_ID)
{
if (C_OrderPO_ID <= 0) set_ValueNoCheck ("C_OrderPO_ID", null);
 else 
set_ValueNoCheck ("C_OrderPO_ID", Integer.valueOf(C_OrderPO_ID));
}
/** Get Purchase Order.
@return Purchase Order */
public int getC_OrderPO_ID() 
{
Integer ii = (Integer)get_Value("C_OrderPO_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_OrderPO_ID */
public static final String COLUMNNAME_C_OrderPO_ID = "C_OrderPO_ID";
/** Set Order.
@param C_Order_ID Order */
public void setC_Order_ID (int C_Order_ID)
{
if (C_Order_ID <= 0) set_ValueNoCheck ("C_Order_ID", null);
 else 
set_ValueNoCheck ("C_Order_ID", Integer.valueOf(C_Order_ID));
}
/** Get Order.
@return Order */
public int getC_Order_ID() 
{
Integer ii = (Integer)get_Value("C_Order_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_Order_ID */
public static final String COLUMNNAME_C_Order_ID = "C_Order_ID";
/** Set Project Issue.
@param C_ProjectIssue_ID Project Issues (Material, Labor) */
public void setC_ProjectIssue_ID (int C_ProjectIssue_ID)
{
if (C_ProjectIssue_ID <= 0) set_ValueNoCheck ("C_ProjectIssue_ID", null);
 else 
set_ValueNoCheck ("C_ProjectIssue_ID", Integer.valueOf(C_ProjectIssue_ID));
}
/** Get Project Issue.
@return Project Issues (Material, Labor) */
public int getC_ProjectIssue_ID() 
{
Integer ii = (Integer)get_Value("C_ProjectIssue_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_ProjectIssue_ID */
public static final String COLUMNNAME_C_ProjectIssue_ID = "C_ProjectIssue_ID";
/** Set Project Line.
@param C_ProjectLine_ID Task or step in a project */
public void setC_ProjectLine_ID (int C_ProjectLine_ID)
{
if (C_ProjectLine_ID < 1) throw new IllegalArgumentException ("C_ProjectLine_ID is mandatory.");
set_ValueNoCheck ("C_ProjectLine_ID", Integer.valueOf(C_ProjectLine_ID));
}
/** Get Project Line.
@return Task or step in a project */
public int getC_ProjectLine_ID() 
{
Integer ii = (Integer)get_Value("C_ProjectLine_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_ProjectLine_ID */
public static final String COLUMNNAME_C_ProjectLine_ID = "C_ProjectLine_ID";
/** Set Project Phase.
@param C_ProjectPhase_ID Phase of a Project */
public void setC_ProjectPhase_ID (int C_ProjectPhase_ID)
{
if (C_ProjectPhase_ID <= 0) set_Value ("C_ProjectPhase_ID", null);
 else 
set_Value ("C_ProjectPhase_ID", Integer.valueOf(C_ProjectPhase_ID));
}
/** Get Project Phase.
@return Phase of a Project */
public int getC_ProjectPhase_ID() 
{
Integer ii = (Integer)get_Value("C_ProjectPhase_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_ProjectPhase_ID */
public static final String COLUMNNAME_C_ProjectPhase_ID = "C_ProjectPhase_ID";
/** Set Project Task.
@param C_ProjectTask_ID Actual Project Task in a Phase */
public void setC_ProjectTask_ID (int C_ProjectTask_ID)
{
if (C_ProjectTask_ID <= 0) set_Value ("C_ProjectTask_ID", null);
 else 
set_Value ("C_ProjectTask_ID", Integer.valueOf(C_ProjectTask_ID));
}
/** Get Project Task.
@return Actual Project Task in a Phase */
public int getC_ProjectTask_ID() 
{
Integer ii = (Integer)get_Value("C_ProjectTask_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_ProjectTask_ID */
public static final String COLUMNNAME_C_ProjectTask_ID = "C_ProjectTask_ID";
/** Set Project.
@param C_Project_ID Financial Project */
public void setC_Project_ID (int C_Project_ID)
{
if (C_Project_ID < 1) throw new IllegalArgumentException ("C_Project_ID is mandatory.");
set_ValueNoCheck ("C_Project_ID", Integer.valueOf(C_Project_ID));
}
/** Get Project.
@return Financial Project */
public int getC_Project_ID() 
{
Integer ii = (Integer)get_Value("C_Project_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_Project_ID */
public static final String COLUMNNAME_C_Project_ID = "C_Project_ID";
/** Set Committed Amount.
@param CommittedAmt The (legal) commitment amount */
public void setCommittedAmt (BigDecimal CommittedAmt)
{
set_Value ("CommittedAmt", CommittedAmt);
}
/** Get Committed Amount.
@return The (legal) commitment amount */
public BigDecimal getCommittedAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("CommittedAmt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name CommittedAmt */
public static final String COLUMNNAME_CommittedAmt = "CommittedAmt";
/** Set Committed Quantity.
@param CommittedQty The (legal) commitment Quantity */
public void setCommittedQty (BigDecimal CommittedQty)
{
set_Value ("CommittedQty", CommittedQty);
}
/** Get Committed Quantity.
@return The (legal) commitment Quantity */
public BigDecimal getCommittedQty() 
{
BigDecimal bd = (BigDecimal)get_Value("CommittedQty");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name CommittedQty */
public static final String COLUMNNAME_CommittedQty = "CommittedQty";
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
/** Set Pricing.
@param DoPricing Pricing */
public void setDoPricing (String DoPricing)
{
if (DoPricing != null && DoPricing.length() > 1)
{
log.warning("Length > 1 - truncated");
DoPricing = DoPricing.substring(0,0);
}
set_Value ("DoPricing", DoPricing);
}
/** Get Pricing.
@return Pricing */
public String getDoPricing() 
{
return (String)get_Value("DoPricing");
}
/** Column name DoPricing */
public static final String COLUMNNAME_DoPricing = "DoPricing";
/** Set Invoiced Amount.
@param InvoicedAmt The amount invoiced */
public void setInvoicedAmt (BigDecimal InvoicedAmt)
{
if (InvoicedAmt == null) throw new IllegalArgumentException ("InvoicedAmt is mandatory.");
set_Value ("InvoicedAmt", InvoicedAmt);
}
/** Get Invoiced Amount.
@return The amount invoiced */
public BigDecimal getInvoicedAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("InvoicedAmt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name InvoicedAmt */
public static final String COLUMNNAME_InvoicedAmt = "InvoicedAmt";
/** Set Quantity Invoiced .
@param InvoicedQty The quantity invoiced */
public void setInvoicedQty (BigDecimal InvoicedQty)
{
if (InvoicedQty == null) throw new IllegalArgumentException ("InvoicedQty is mandatory.");
set_Value ("InvoicedQty", InvoicedQty);
}
/** Get Quantity Invoiced .
@return The quantity invoiced */
public BigDecimal getInvoicedQty() 
{
BigDecimal bd = (BigDecimal)get_Value("InvoicedQty");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name InvoicedQty */
public static final String COLUMNNAME_InvoicedQty = "InvoicedQty";
/** Set Printed.
@param IsPrinted Indicates if this document / line is printed */
public void setIsPrinted (boolean IsPrinted)
{
set_Value ("IsPrinted", Boolean.valueOf(IsPrinted));
}
/** Get Printed.
@return Indicates if this document / line is printed */
public boolean isPrinted() 
{
Object oo = get_Value("IsPrinted");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsPrinted */
public static final String COLUMNNAME_IsPrinted = "IsPrinted";
/** Set Line No.
@param Line Unique line for this document */
public void setLine (int Line)
{
set_Value ("Line", Integer.valueOf(Line));
}
/** Get Line No.
@return Unique line for this document */
public int getLine() 
{
Integer ii = (Integer)get_Value("Line");
if (ii == null) return 0;
return ii.intValue();
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getLine()));
}
/** Column name Line */
public static final String COLUMNNAME_Line = "Line";
/** Set Product Category.
@param M_Product_Category_ID Category of a Product */
public void setM_Product_Category_ID (int M_Product_Category_ID)
{
if (M_Product_Category_ID <= 0) set_Value ("M_Product_Category_ID", null);
 else 
set_Value ("M_Product_Category_ID", Integer.valueOf(M_Product_Category_ID));
}
/** Get Product Category.
@return Category of a Product */
public int getM_Product_Category_ID() 
{
Integer ii = (Integer)get_Value("M_Product_Category_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name M_Product_Category_ID */
public static final String COLUMNNAME_M_Product_Category_ID = "M_Product_Category_ID";
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
/** Set Planned Amount.
@param PlannedAmt Planned amount for this project */
public void setPlannedAmt (BigDecimal PlannedAmt)
{
if (PlannedAmt == null) throw new IllegalArgumentException ("PlannedAmt is mandatory.");
set_Value ("PlannedAmt", PlannedAmt);
}
/** Get Planned Amount.
@return Planned amount for this project */
public BigDecimal getPlannedAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("PlannedAmt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name PlannedAmt */
public static final String COLUMNNAME_PlannedAmt = "PlannedAmt";
/** Set Planned Margin.
@param PlannedMarginAmt Project's planned margin amount */
public void setPlannedMarginAmt (BigDecimal PlannedMarginAmt)
{
if (PlannedMarginAmt == null) throw new IllegalArgumentException ("PlannedMarginAmt is mandatory.");
set_Value ("PlannedMarginAmt", PlannedMarginAmt);
}
/** Get Planned Margin.
@return Project's planned margin amount */
public BigDecimal getPlannedMarginAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("PlannedMarginAmt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name PlannedMarginAmt */
public static final String COLUMNNAME_PlannedMarginAmt = "PlannedMarginAmt";
/** Set Planned Price.
@param PlannedPrice Planned price for this project line */
public void setPlannedPrice (BigDecimal PlannedPrice)
{
if (PlannedPrice == null) throw new IllegalArgumentException ("PlannedPrice is mandatory.");
set_Value ("PlannedPrice", PlannedPrice);
}
/** Get Planned Price.
@return Planned price for this project line */
public BigDecimal getPlannedPrice() 
{
BigDecimal bd = (BigDecimal)get_Value("PlannedPrice");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name PlannedPrice */
public static final String COLUMNNAME_PlannedPrice = "PlannedPrice";
/** Set Planned Quantity.
@param PlannedQty Planned quantity for this project */
public void setPlannedQty (BigDecimal PlannedQty)
{
if (PlannedQty == null) throw new IllegalArgumentException ("PlannedQty is mandatory.");
set_Value ("PlannedQty", PlannedQty);
}
/** Get Planned Quantity.
@return Planned quantity for this project */
public BigDecimal getPlannedQty() 
{
BigDecimal bd = (BigDecimal)get_Value("PlannedQty");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name PlannedQty */
public static final String COLUMNNAME_PlannedQty = "PlannedQty";
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
}
