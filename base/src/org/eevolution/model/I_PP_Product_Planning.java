/**********************************************************************
 * This file is part of Adempiere ERP Bazaar                          *
 * http://www.adempiere.org                                           *
 *                                                                    *
 * Copyright (C) Trifon Trifonov.                                     *
 * Copyright (C) Contributors                                         *
 *                                                                    *
 * This program is free software;
 you can redistribute it and/or      *
 * modify it under the terms of the GNU General Public License        *
 * as published by the Free Software Foundation;
 either version 2     *
 * of the License, or (at your option) any later version.             *
 *                                                                    *
 * This program is distributed in the hope that it will be useful,    *
 * but WITHOUT ANY WARRANTY;
 without even the implied warranty of     *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the       *
 * GNU General Public License for more details.                       *
 *                                                                    *
 * You should have received a copy of the GNU General Public License  *
 * along with this program;
 if not, write to the Free Software        *
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,         *
 * MA 02110-1301, USA.                                                *
 *                                                                    *
 * Contributors:                                                      *
 * - Trifon Trifonov (trifonnt@users.sourceforge.net)                 *
 *                                                                    *
 * Sponsors:                                                          *
 * - Company (http://www.site.com)                                    *
 **********************************************************************/
package org.eevolution.model;

import java.math.BigDecimal;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for PP_Product_Planning
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.5.1a
 */
public interface I_PP_Product_Planning 
{

    /** TableName=PP_Product_Planning */
    public static final String Table_Name = "PP_Product_Planning";

    /** AD_Table_ID=53020 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AD_Workflow_ID */
    public static final String COLUMNNAME_AD_Workflow_ID = "AD_Workflow_ID";

	/** Set Workflow.
	  * Workflow or combination of tasks
	  */
	public void setAD_Workflow_ID (int AD_Workflow_ID);

	/** Get Workflow.
	  * Workflow or combination of tasks
	  */
	public int getAD_Workflow_ID();

	public I_AD_Workflow getAD_Workflow() throws Exception;

    /** Column name DD_NetworkDistribution_ID */
    public static final String COLUMNNAME_DD_NetworkDistribution_ID = "DD_NetworkDistribution_ID";

	/** Set Network Distribution	  */
	public void setDD_NetworkDistribution_ID (int DD_NetworkDistribution_ID);

	/** Get Network Distribution	  */
	public int getDD_NetworkDistribution_ID();

	public org.eevolution.model.I_DD_NetworkDistribution getDD_NetworkDistribution() throws Exception;

    /** Column name DeliveryTime_Promised */
    public static final String COLUMNNAME_DeliveryTime_Promised = "DeliveryTime_Promised";

	/** Set Promised Delivery Time.
	  * Promised days between order and delivery
	  */
	public void setDeliveryTime_Promised (BigDecimal DeliveryTime_Promised);

	/** Get Promised Delivery Time.
	  * Promised days between order and delivery
	  */
	public BigDecimal getDeliveryTime_Promised();

    /** Column name IsCreatePlan */
    public static final String COLUMNNAME_IsCreatePlan = "IsCreatePlan";

	/** Set Is Create Plan	  */
	public void setIsCreatePlan (boolean IsCreatePlan);

	/** Get Is Create Plan	  */
	public boolean isCreatePlan();

    /** Column name IsIssue */
    public static final String COLUMNNAME_IsIssue = "IsIssue";

	/** Set Is Issue	  */
	public void setIsIssue (boolean IsIssue);

	/** Get Is Issue	  */
	public boolean isIssue();

    /** Column name IsMPS */
    public static final String COLUMNNAME_IsMPS = "IsMPS";

	/** Set Is MPS	  */
	public void setIsMPS (boolean IsMPS);

	/** Get Is MPS	  */
	public boolean isMPS();

    /** Column name IsPhantom */
    public static final String COLUMNNAME_IsPhantom = "IsPhantom";

	/** Set Phantom.
	  * Phantom Component
	  */
	public void setIsPhantom (boolean IsPhantom);

	/** Get Phantom.
	  * Phantom Component
	  */
	public boolean isPhantom();

    /** Column name IsRequiredDRP */
    public static final String COLUMNNAME_IsRequiredDRP = "IsRequiredDRP";

	/** Set Required Calculate DRP	  */
	public void setIsRequiredDRP (boolean IsRequiredDRP);

	/** Get Required Calculate DRP	  */
	public boolean isRequiredDRP();

    /** Column name IsRequiredMRP */
    public static final String COLUMNNAME_IsRequiredMRP = "IsRequiredMRP";

	/** Set Required Calculate MRP	  */
	public void setIsRequiredMRP (boolean IsRequiredMRP);

	/** Get Required Calculate MRP	  */
	public boolean isRequiredMRP();

    /** Column name M_Product_ID */
    public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";

	/** Set Product.
	  * Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID);

	/** Get Product.
	  * Product, Service, Item
	  */
	public int getM_Product_ID();

	public I_M_Product getM_Product() throws Exception;

    /** Column name M_Warehouse_ID */
    public static final String COLUMNNAME_M_Warehouse_ID = "M_Warehouse_ID";

	/** Set Warehouse.
	  * Storage Warehouse and Service Point
	  */
	public void setM_Warehouse_ID (int M_Warehouse_ID);

	/** Get Warehouse.
	  * Storage Warehouse and Service Point
	  */
	public int getM_Warehouse_ID();

	public I_M_Warehouse getM_Warehouse() throws Exception;

    /** Column name Order_Max */
    public static final String COLUMNNAME_Order_Max = "Order_Max";

	/** Set Order_Max	  */
	public void setOrder_Max (BigDecimal Order_Max);

	/** Get Order_Max	  */
	public BigDecimal getOrder_Max();

    /** Column name Order_Min */
    public static final String COLUMNNAME_Order_Min = "Order_Min";

	/** Set Minimum Order Qty.
	  * Minimum order quantity in UOM
	  */
	public void setOrder_Min (BigDecimal Order_Min);

	/** Get Minimum Order Qty.
	  * Minimum order quantity in UOM
	  */
	public BigDecimal getOrder_Min();

    /** Column name Order_Pack */
    public static final String COLUMNNAME_Order_Pack = "Order_Pack";

	/** Set Order Pack Qty.
	  * Package order size in UOM (e.g. order set of 5 units)
	  */
	public void setOrder_Pack (BigDecimal Order_Pack);

	/** Get Order Pack Qty.
	  * Package order size in UOM (e.g. order set of 5 units)
	  */
	public BigDecimal getOrder_Pack();

    /** Column name Order_Period */
    public static final String COLUMNNAME_Order_Period = "Order_Period";

	/** Set Order Period	  */
	public void setOrder_Period (BigDecimal Order_Period);

	/** Get Order Period	  */
	public BigDecimal getOrder_Period();

    /** Column name Order_Policy */
    public static final String COLUMNNAME_Order_Policy = "Order_Policy";

	/** Set Order Policy	  */
	public void setOrder_Policy (String Order_Policy);

	/** Get Order Policy	  */
	public String getOrder_Policy();

    /** Column name Order_Qty */
    public static final String COLUMNNAME_Order_Qty = "Order_Qty";

	/** Set Order Qty	  */
	public void setOrder_Qty (BigDecimal Order_Qty);

	/** Get Order Qty	  */
	public BigDecimal getOrder_Qty();

    /** Column name PP_Product_BOM_ID */
    public static final String COLUMNNAME_PP_Product_BOM_ID = "PP_Product_BOM_ID";

	/** Set BOM & Formula.
	  * BOM & Formula
	  */
	public void setPP_Product_BOM_ID (int PP_Product_BOM_ID);

	/** Get BOM & Formula.
	  * BOM & Formula
	  */
	public int getPP_Product_BOM_ID();

	public org.eevolution.model.I_PP_Product_BOM getPP_Product_BOM() throws Exception;

    /** Column name PP_Product_Planning_ID */
    public static final String COLUMNNAME_PP_Product_Planning_ID = "PP_Product_Planning_ID";

	/** Set PP_Product_Planning_ID	  */
	public void setPP_Product_Planning_ID (int PP_Product_Planning_ID);

	/** Get PP_Product_Planning_ID	  */
	public int getPP_Product_Planning_ID();

    /** Column name Planner_ID */
    public static final String COLUMNNAME_Planner_ID = "Planner_ID";

	/** Set Planner	  */
	public void setPlanner_ID (int Planner_ID);

	/** Get Planner	  */
	public int getPlanner_ID();

    /** Column name S_Resource_ID */
    public static final String COLUMNNAME_S_Resource_ID = "S_Resource_ID";

	/** Set Resource.
	  * Resource
	  */
	public void setS_Resource_ID (int S_Resource_ID);

	/** Get Resource.
	  * Resource
	  */
	public int getS_Resource_ID();

	public I_S_Resource getS_Resource() throws Exception;

    /** Column name SafetyStock */
    public static final String COLUMNNAME_SafetyStock = "SafetyStock";

	/** Set Safety Stock Qty.
	  * Safety stock is a term used to describe a level of stock that is maintained below the cycle stock to buffer against stock-outs
	  */
	public void setSafetyStock (BigDecimal SafetyStock);

	/** Get Safety Stock Qty.
	  * Safety stock is a term used to describe a level of stock that is maintained below the cycle stock to buffer against stock-outs
	  */
	public BigDecimal getSafetyStock();

    /** Column name TimeFence */
    public static final String COLUMNNAME_TimeFence = "TimeFence";

	/** Set Time Fence	  */
	public void setTimeFence (BigDecimal TimeFence);

	/** Get Time Fence	  */
	public BigDecimal getTimeFence();

    /** Column name TransfertTime */
    public static final String COLUMNNAME_TransfertTime = "TransfertTime";

	/** Set Transfert Time	  */
	public void setTransfertTime (BigDecimal TransfertTime);

	/** Get Transfert Time	  */
	public BigDecimal getTransfertTime();

    /** Column name WorkingTime */
    public static final String COLUMNNAME_WorkingTime = "WorkingTime";

	/** Set Working Time.
	  * Workflow Simulation Execution Time
	  */
	public void setWorkingTime (BigDecimal WorkingTime);

	/** Get Working Time.
	  * Workflow Simulation Execution Time
	  */
	public BigDecimal getWorkingTime();

    /** Column name Yield */
    public static final String COLUMNNAME_Yield = "Yield";

	/** Set Yield	  */
	public void setYield (int Yield);

	/** Get Yield	  */
	public int getYield();
}
