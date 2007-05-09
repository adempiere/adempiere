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
/** Generated Model for C_ProjectTask
 *  @author Adempiere (generated) 
 *  @version Release 3.2.0 - $Id$ */
public class X_C_ProjectTask extends PO
{
/** Standard Constructor
@param ctx context
@param C_ProjectTask_ID id
@param trxName transaction
*/
public X_C_ProjectTask (Properties ctx, int C_ProjectTask_ID, String trxName)
{
super (ctx, C_ProjectTask_ID, trxName);
/** if (C_ProjectTask_ID == 0)
{
setC_ProjectPhase_ID (0);
setC_ProjectTask_ID (0);
setCommittedAmt (Env.ZERO);
setName (null);
setPlannedAmt (Env.ZERO);
setProjInvoiceRule (null);	// @ProjInvoiceRule@
setSeqNo (0);	// @SQL=SELECT NVL(MAX(SeqNo),0)+10 AS DefaultValue FROM C_ProjectTask WHERE C_ProjectPhase_ID=@C_ProjectPhase_ID@
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_ProjectTask (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=584 */
public static final int Table_ID=MTable.getTable_ID("C_ProjectTask");

/** TableName=C_ProjectTask */
public static final String Table_Name="C_ProjectTask";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"C_ProjectTask");

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
StringBuffer sb = new StringBuffer ("X_C_ProjectTask[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Project Phase.
@param C_ProjectPhase_ID Phase of a Project */
public void setC_ProjectPhase_ID (int C_ProjectPhase_ID)
{
if (C_ProjectPhase_ID < 1) throw new IllegalArgumentException ("C_ProjectPhase_ID is mandatory.");
set_ValueNoCheck ("C_ProjectPhase_ID", Integer.valueOf(C_ProjectPhase_ID));
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
if (C_ProjectTask_ID < 1) throw new IllegalArgumentException ("C_ProjectTask_ID is mandatory.");
set_ValueNoCheck ("C_ProjectTask_ID", Integer.valueOf(C_ProjectTask_ID));
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
/** Set Standard Task.
@param C_Task_ID Standard Project Type Task */
public void setC_Task_ID (int C_Task_ID)
{
if (C_Task_ID <= 0) set_ValueNoCheck ("C_Task_ID", null);
 else 
set_ValueNoCheck ("C_Task_ID", Integer.valueOf(C_Task_ID));
}
/** Get Standard Task.
@return Standard Project Type Task */
public int getC_Task_ID() 
{
Integer ii = (Integer)get_Value("C_Task_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_Task_ID */
public static final String COLUMNNAME_C_Task_ID = "C_Task_ID";
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
/** Column name CommittedAmt */
public static final String COLUMNNAME_CommittedAmt = "CommittedAmt";
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
/** Column name Help */
public static final String COLUMNNAME_Help = "Help";
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
/** Column name Name */
public static final String COLUMNNAME_Name = "Name";
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
/** Column name ProjInvoiceRule */
public static final String COLUMNNAME_ProjInvoiceRule = "ProjInvoiceRule";
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
/** Column name Qty */
public static final String COLUMNNAME_Qty = "Qty";
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
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getSeqNo()));
}
/** Column name SeqNo */
public static final String COLUMNNAME_SeqNo = "SeqNo";
}
