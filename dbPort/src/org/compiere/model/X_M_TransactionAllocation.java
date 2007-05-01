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
/** Generated Model for M_TransactionAllocation
 *  @author Adempiere (generated) 
 *  @version Release 3.2.0 - $Id$ */
public class X_M_TransactionAllocation extends PO
{
/** Standard Constructor
@param ctx context
@param M_TransactionAllocation_ID id
@param trxName transaction
*/
public X_M_TransactionAllocation (Properties ctx, int M_TransactionAllocation_ID, String trxName)
{
super (ctx, M_TransactionAllocation_ID, trxName);
/** if (M_TransactionAllocation_ID == 0)
{
setAllocationStrategyType (null);
setIsAllocated (false);	// N
setIsManual (false);	// N
setM_AttributeSetInstance_ID (0);
setM_Product_ID (0);
setM_Transaction_ID (0);
setQty (Env.ZERO);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_M_TransactionAllocation (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=636 */
public static final int Table_ID=MTable.getTable_ID("M_TransactionAllocation");

/** TableName=M_TransactionAllocation */
public static final String Table_Name="M_TransactionAllocation";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"M_TransactionAllocation");

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
StringBuffer sb = new StringBuffer ("X_M_TransactionAllocation[").append(get_ID()).append("]");
return sb.toString();
}

/** AllocationStrategyType AD_Reference_ID=294 */
public static final int ALLOCATIONSTRATEGYTYPE_AD_Reference_ID=294;
/** FiFo = F */
public static final String ALLOCATIONSTRATEGYTYPE_FiFo = "F";
/** LiFo = L */
public static final String ALLOCATIONSTRATEGYTYPE_LiFo = "L";
/** Set Allocation Strategy.
@param AllocationStrategyType Allocation Strategy */
public void setAllocationStrategyType (String AllocationStrategyType)
{
if (AllocationStrategyType == null) throw new IllegalArgumentException ("AllocationStrategyType is mandatory");
if (AllocationStrategyType.equals("F") || AllocationStrategyType.equals("L"));
 else throw new IllegalArgumentException ("AllocationStrategyType Invalid value - " + AllocationStrategyType + " - Reference_ID=294 - F - L");
if (AllocationStrategyType.length() > 1)
{
log.warning("Length > 1 - truncated");
AllocationStrategyType = AllocationStrategyType.substring(0,0);
}
set_Value ("AllocationStrategyType", AllocationStrategyType);
}
/** Get Allocation Strategy.
@return Allocation Strategy */
public String getAllocationStrategyType() 
{
return (String)get_Value("AllocationStrategyType");
}
/** Column name AllocationStrategyType */
public static final String COLUMNNAME_AllocationStrategyType = "AllocationStrategyType";
/** Set Allocated.
@param IsAllocated Indicates if the payment has been allocated */
public void setIsAllocated (boolean IsAllocated)
{
set_Value ("IsAllocated", Boolean.valueOf(IsAllocated));
}
/** Get Allocated.
@return Indicates if the payment has been allocated */
public boolean isAllocated() 
{
Object oo = get_Value("IsAllocated");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsAllocated */
public static final String COLUMNNAME_IsAllocated = "IsAllocated";
/** Set Manual.
@param IsManual This is a manual process */
public void setIsManual (boolean IsManual)
{
set_Value ("IsManual", Boolean.valueOf(IsManual));
}
/** Get Manual.
@return This is a manual process */
public boolean isManual() 
{
Object oo = get_Value("IsManual");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsManual */
public static final String COLUMNNAME_IsManual = "IsManual";
/** Set Attribute Set Instance.
@param M_AttributeSetInstance_ID Product Attribute Set Instance */
public void setM_AttributeSetInstance_ID (int M_AttributeSetInstance_ID)
{
if (M_AttributeSetInstance_ID < 0) throw new IllegalArgumentException ("M_AttributeSetInstance_ID is mandatory.");
set_Value ("M_AttributeSetInstance_ID", Integer.valueOf(M_AttributeSetInstance_ID));
}
/** Get Attribute Set Instance.
@return Product Attribute Set Instance */
public int getM_AttributeSetInstance_ID() 
{
Integer ii = (Integer)get_Value("M_AttributeSetInstance_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name M_AttributeSetInstance_ID */
public static final String COLUMNNAME_M_AttributeSetInstance_ID = "M_AttributeSetInstance_ID";
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
/** Set Phys.Inventory Line.
@param M_InventoryLine_ID Unique line in an Inventory document */
public void setM_InventoryLine_ID (int M_InventoryLine_ID)
{
if (M_InventoryLine_ID <= 0) set_Value ("M_InventoryLine_ID", null);
 else 
set_Value ("M_InventoryLine_ID", Integer.valueOf(M_InventoryLine_ID));
}
/** Get Phys.Inventory Line.
@return Unique line in an Inventory document */
public int getM_InventoryLine_ID() 
{
Integer ii = (Integer)get_Value("M_InventoryLine_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name M_InventoryLine_ID */
public static final String COLUMNNAME_M_InventoryLine_ID = "M_InventoryLine_ID";
/** Set Product.
@param M_Product_ID Product, Service, Item */
public void setM_Product_ID (int M_Product_ID)
{
if (M_Product_ID < 1) throw new IllegalArgumentException ("M_Product_ID is mandatory.");
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
/** Set Production Line.
@param M_ProductionLine_ID Document Line representing a production */
public void setM_ProductionLine_ID (int M_ProductionLine_ID)
{
if (M_ProductionLine_ID <= 0) set_Value ("M_ProductionLine_ID", null);
 else 
set_Value ("M_ProductionLine_ID", Integer.valueOf(M_ProductionLine_ID));
}
/** Get Production Line.
@return Document Line representing a production */
public int getM_ProductionLine_ID() 
{
Integer ii = (Integer)get_Value("M_ProductionLine_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name M_ProductionLine_ID */
public static final String COLUMNNAME_M_ProductionLine_ID = "M_ProductionLine_ID";
/** Set Inventory Transaction.
@param M_Transaction_ID Inventory Transaction */
public void setM_Transaction_ID (int M_Transaction_ID)
{
if (M_Transaction_ID < 1) throw new IllegalArgumentException ("M_Transaction_ID is mandatory.");
set_ValueNoCheck ("M_Transaction_ID", Integer.valueOf(M_Transaction_ID));
}
/** Get Inventory Transaction.
@return Inventory Transaction */
public int getM_Transaction_ID() 
{
Integer ii = (Integer)get_Value("M_Transaction_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name M_Transaction_ID */
public static final String COLUMNNAME_M_Transaction_ID = "M_Transaction_ID";

/** Out_M_InOutLine_ID AD_Reference_ID=295 */
public static final int OUT_M_INOUTLINE_ID_AD_Reference_ID=295;
/** Set Out Shipment Line.
@param Out_M_InOutLine_ID Outgoing Shipment/Receipt */
public void setOut_M_InOutLine_ID (int Out_M_InOutLine_ID)
{
if (Out_M_InOutLine_ID <= 0) set_Value ("Out_M_InOutLine_ID", null);
 else 
set_Value ("Out_M_InOutLine_ID", Integer.valueOf(Out_M_InOutLine_ID));
}
/** Get Out Shipment Line.
@return Outgoing Shipment/Receipt */
public int getOut_M_InOutLine_ID() 
{
Integer ii = (Integer)get_Value("Out_M_InOutLine_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name Out_M_InOutLine_ID */
public static final String COLUMNNAME_Out_M_InOutLine_ID = "Out_M_InOutLine_ID";

/** Out_M_InventoryLine_ID AD_Reference_ID=296 */
public static final int OUT_M_INVENTORYLINE_ID_AD_Reference_ID=296;
/** Set Out Inventory Line.
@param Out_M_InventoryLine_ID Outgoing Inventory Line */
public void setOut_M_InventoryLine_ID (int Out_M_InventoryLine_ID)
{
if (Out_M_InventoryLine_ID <= 0) set_Value ("Out_M_InventoryLine_ID", null);
 else 
set_Value ("Out_M_InventoryLine_ID", Integer.valueOf(Out_M_InventoryLine_ID));
}
/** Get Out Inventory Line.
@return Outgoing Inventory Line */
public int getOut_M_InventoryLine_ID() 
{
Integer ii = (Integer)get_Value("Out_M_InventoryLine_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name Out_M_InventoryLine_ID */
public static final String COLUMNNAME_Out_M_InventoryLine_ID = "Out_M_InventoryLine_ID";

/** Out_M_ProductionLine_ID AD_Reference_ID=297 */
public static final int OUT_M_PRODUCTIONLINE_ID_AD_Reference_ID=297;
/** Set Out Production Line.
@param Out_M_ProductionLine_ID Outgoing Production Line */
public void setOut_M_ProductionLine_ID (int Out_M_ProductionLine_ID)
{
if (Out_M_ProductionLine_ID <= 0) set_Value ("Out_M_ProductionLine_ID", null);
 else 
set_Value ("Out_M_ProductionLine_ID", Integer.valueOf(Out_M_ProductionLine_ID));
}
/** Get Out Production Line.
@return Outgoing Production Line */
public int getOut_M_ProductionLine_ID() 
{
Integer ii = (Integer)get_Value("Out_M_ProductionLine_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name Out_M_ProductionLine_ID */
public static final String COLUMNNAME_Out_M_ProductionLine_ID = "Out_M_ProductionLine_ID";

/** Out_M_Transaction_ID AD_Reference_ID=298 */
public static final int OUT_M_TRANSACTION_ID_AD_Reference_ID=298;
/** Set Out Transaction.
@param Out_M_Transaction_ID Outgoing Transaction */
public void setOut_M_Transaction_ID (int Out_M_Transaction_ID)
{
if (Out_M_Transaction_ID <= 0) set_Value ("Out_M_Transaction_ID", null);
 else 
set_Value ("Out_M_Transaction_ID", Integer.valueOf(Out_M_Transaction_ID));
}
/** Get Out Transaction.
@return Outgoing Transaction */
public int getOut_M_Transaction_ID() 
{
Integer ii = (Integer)get_Value("Out_M_Transaction_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name Out_M_Transaction_ID */
public static final String COLUMNNAME_Out_M_Transaction_ID = "Out_M_Transaction_ID";
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
/** Column name Qty */
public static final String COLUMNNAME_Qty = "Qty";
}
