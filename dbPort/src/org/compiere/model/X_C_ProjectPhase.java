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
/** Generated Model for C_ProjectPhase
 *  @author Jorg Janke (generated) 
 *  @version Release 2.5.3d - 2006-10-10 21:55:58.39 */
public class X_C_ProjectPhase extends PO
{
/** Standard Constructor
@param ctx context
@param C_ProjectPhase_ID id
@param trxName transaction
*/
public X_C_ProjectPhase (Properties ctx, int C_ProjectPhase_ID, String trxName)
{
super (ctx, C_ProjectPhase_ID, trxName);
/** if (C_ProjectPhase_ID == 0)
{
setC_ProjectPhase_ID (0);
setC_Project_ID (0);
setCommittedAmt (Env.ZERO);
setIsCommitCeiling (false);
setIsComplete (false);
setName (null);
setPlannedAmt (Env.ZERO);
setProjInvoiceRule (null);	// @ProjInvoiceRule@
setSeqNo (0);	// @SQL=SELECT NVL(MAX(SeqNo),0)+10 AS DefaultValue FROM C_ProjectPhase WHERE C_Project_ID=@C_Project_ID@
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_ProjectPhase (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=576 */
public static final int Table_ID=576;

/** TableName=C_ProjectPhase */
public static final String Table_Name="C_ProjectPhase";

protected static KeyNamePair Model = new KeyNamePair(576,"C_ProjectPhase");

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
StringBuffer sb = new StringBuffer ("X_C_ProjectPhase[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Order.
@param C_Order_ID Order */
public void setC_Order_ID (int C_Order_ID)
{
if (C_Order_ID <= 0) set_ValueNoCheck ("C_Order_ID", null);
 else 
set_ValueNoCheck ("C_Order_ID", new Integer(C_Order_ID));
}
/** Get Order.
@return Order */
public int getC_Order_ID() 
{
Integer ii = (Integer)get_Value("C_Order_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Standard Phase.
@param C_Phase_ID Standard Phase of the Project Type */
public void setC_Phase_ID (int C_Phase_ID)
{
if (C_Phase_ID <= 0) set_ValueNoCheck ("C_Phase_ID", null);
 else 
set_ValueNoCheck ("C_Phase_ID", new Integer(C_Phase_ID));
}
/** Get Standard Phase.
@return Standard Phase of the Project Type */
public int getC_Phase_ID() 
{
Integer ii = (Integer)get_Value("C_Phase_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Project Phase.
@param C_ProjectPhase_ID Phase of a Project */
public void setC_ProjectPhase_ID (int C_ProjectPhase_ID)
{
if (C_ProjectPhase_ID < 1) throw new IllegalArgumentException ("C_ProjectPhase_ID is mandatory.");
set_ValueNoCheck ("C_ProjectPhase_ID", new Integer(C_ProjectPhase_ID));
}
/** Get Project Phase.
@return Phase of a Project */
public int getC_ProjectPhase_ID() 
{
Integer ii = (Integer)get_Value("C_ProjectPhase_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Project.
@param C_Project_ID Financial Project */
public void setC_Project_ID (int C_Project_ID)
{
if (C_Project_ID < 1) throw new IllegalArgumentException ("C_Project_ID is mandatory.");
set_ValueNoCheck ("C_Project_ID", new Integer(C_Project_ID));
}
/** Get Project.
@return Financial Project */
public int getC_Project_ID() 
{
Integer ii = (Integer)get_Value("C_Project_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Committed Amount.
@param CommittedAmt The (legal) commitment amount */
public void setCommittedAmt (BigDecimal CommittedAmt)
{
if (CommittedAmt == null) throw new IllegalArgumentException ("CommittedAmt is mandatory.");
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
/** Set End Date.
@param EndDate Last effective date (inclusive) */
public void setEndDate (Timestamp EndDate)
{
set_Value ("EndDate", EndDate);
}
/** Get End Date.
@return Last effective date (inclusive) */
public Timestamp getEndDate() 
{
return (Timestamp)get_Value("EndDate");
}
/** Set Generate Order.
@param GenerateOrder Generate Order */
public void setGenerateOrder (String GenerateOrder)
{
if (GenerateOrder != null && GenerateOrder.length() > 1)
{
log.warning("Length > 1 - truncated");
GenerateOrder = GenerateOrder.substring(0,0);
}
set_Value ("GenerateOrder", GenerateOrder);
}
/** Get Generate Order.
@return Generate Order */
public String getGenerateOrder() 
{
return (String)get_Value("GenerateOrder");
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
/** Set Commitment is Ceiling.
@param IsCommitCeiling The commitment amount/quantity is the chargeable ceiling  */
public void setIsCommitCeiling (boolean IsCommitCeiling)
{
set_Value ("IsCommitCeiling", new Boolean(IsCommitCeiling));
}
/** Get Commitment is Ceiling.
@return The commitment amount/quantity is the chargeable ceiling  */
public boolean isCommitCeiling() 
{
Object oo = get_Value("IsCommitCeiling");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Complete.
@param IsComplete It is complete */
public void setIsComplete (boolean IsComplete)
{
set_Value ("IsComplete", new Boolean(IsComplete));
}
/** Get Complete.
@return It is complete */
public boolean isComplete() 
{
Object oo = get_Value("IsComplete");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Product.
@param M_Product_ID Product, Service, Item */
public void setM_Product_ID (int M_Product_ID)
{
if (M_Product_ID <= 0) set_Value ("M_Product_ID", null);
 else 
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
/** Set Unit Price.
@param PriceActual Actual Price  */
public void setPriceActual (BigDecimal PriceActual)
{
set_Value ("PriceActual", PriceActual);
}
/** Get Unit Price.
@return Actual Price  */
public BigDecimal getPriceActual() 
{
BigDecimal bd = (BigDecimal)get_Value("PriceActual");
if (bd == null) return Env.ZERO;
return bd;
}

/** ProjInvoiceRule AD_Reference_ID=383 */
public static final int PROJINVOICERULE_AD_Reference_ID=383;
/** None = - */
public static final String PROJINVOICERULE_None = "-";
/** Committed Amount = C */
public static final String PROJINVOICERULE_CommittedAmount = "C";
/** Product  Quantity = P */
public static final String PROJINVOICERULE_ProductQuantity = "P";
/** Time&Material = T */
public static final String PROJINVOICERULE_TimeMaterial = "T";
/** Time&Material max Comitted = c */
public static final String PROJINVOICERULE_TimeMaterialMaxComitted = "c";
/** Set Invoice Rule.
@param ProjInvoiceRule Invoice Rule for the project */
public void setProjInvoiceRule (String ProjInvoiceRule)
{
if (ProjInvoiceRule == null) throw new IllegalArgumentException ("ProjInvoiceRule is mandatory");
if (ProjInvoiceRule.equals("-") || ProjInvoiceRule.equals("C") || ProjInvoiceRule.equals("P") || ProjInvoiceRule.equals("T") || ProjInvoiceRule.equals("c"));
 else throw new IllegalArgumentException ("ProjInvoiceRule Invalid value - " + ProjInvoiceRule + " - Reference_ID=383 - - - C - P - T - c");
if (ProjInvoiceRule.length() > 1)
{
log.warning("Length > 1 - truncated");
ProjInvoiceRule = ProjInvoiceRule.substring(0,0);
}
set_Value ("ProjInvoiceRule", ProjInvoiceRule);
}
/** Get Invoice Rule.
@return Invoice Rule for the project */
public String getProjInvoiceRule() 
{
return (String)get_Value("ProjInvoiceRule");
}
/** Set Quantity.
@param Qty Quantity */
public void setQty (BigDecimal Qty)
{
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
/** Set Sequence.
@param SeqNo Method of ordering records;
 lowest number comes first */
public void setSeqNo (int SeqNo)
{
set_Value ("SeqNo", new Integer(SeqNo));
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
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getSeqNo()));
}
/** Set Start Date.
@param StartDate First effective day (inclusive) */
public void setStartDate (Timestamp StartDate)
{
set_Value ("StartDate", StartDate);
}
/** Get Start Date.
@return First effective day (inclusive) */
public Timestamp getStartDate() 
{
return (Timestamp)get_Value("StartDate");
}
}
