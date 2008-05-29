/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.eevolution.model;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for PP_Product_Planning
 *  @author Adempiere (generated) 
 *  @version Release 3.5.1a - $Id$ */
public class X_PP_Product_Planning extends PO implements I_PP_Product_Planning, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_PP_Product_Planning (Properties ctx, int PP_Product_Planning_ID, String trxName)
    {
      super (ctx, PP_Product_Planning_ID, trxName);
      /** if (PP_Product_Planning_ID == 0)
        {
			setIsCreatePlan (false);
			setIsIssue (true);
// Y
			setIsPhantom (false);
			setIsRequiredDRP (false);
			setIsRequiredMRP (false);
			setM_Product_ID (0);
			setPP_Product_Planning_ID (0);
        } */
    }

    /** Load Constructor */
    public X_PP_Product_Planning (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_PP_Product_Planning[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_Workflow getAD_Workflow() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_AD_Workflow.Table_Name);
        I_AD_Workflow result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_Workflow)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_Workflow_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Workflow.
		@param AD_Workflow_ID 
		Workflow or combination of tasks
	  */
	public void setAD_Workflow_ID (int AD_Workflow_ID)
	{
		if (AD_Workflow_ID < 1) 
			set_Value (COLUMNNAME_AD_Workflow_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Workflow_ID, Integer.valueOf(AD_Workflow_ID));
	}

	/** Get Workflow.
		@return Workflow or combination of tasks
	  */
	public int getAD_Workflow_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Workflow_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_DD_NetworkDistribution getDD_NetworkDistribution() throws Exception 
    {
        Class<?> clazz = MTable.getClass(org.eevolution.model.I_DD_NetworkDistribution.Table_Name);
        org.eevolution.model.I_DD_NetworkDistribution result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (org.eevolution.model.I_DD_NetworkDistribution)constructor.newInstance(new Object[] {getCtx(), new Integer(getDD_NetworkDistribution_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Network Distribution.
		@param DD_NetworkDistribution_ID Network Distribution	  */
	public void setDD_NetworkDistribution_ID (int DD_NetworkDistribution_ID)
	{
		if (DD_NetworkDistribution_ID < 1) 
			set_Value (COLUMNNAME_DD_NetworkDistribution_ID, null);
		else 
			set_Value (COLUMNNAME_DD_NetworkDistribution_ID, Integer.valueOf(DD_NetworkDistribution_ID));
	}

	/** Get Network Distribution.
		@return Network Distribution	  */
	public int getDD_NetworkDistribution_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DD_NetworkDistribution_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Promised Delivery Time.
		@param DeliveryTime_Promised 
		Promised days between order and delivery
	  */
	public void setDeliveryTime_Promised (BigDecimal DeliveryTime_Promised)
	{
		set_Value (COLUMNNAME_DeliveryTime_Promised, DeliveryTime_Promised);
	}

	/** Get Promised Delivery Time.
		@return Promised days between order and delivery
	  */
	public BigDecimal getDeliveryTime_Promised () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DeliveryTime_Promised);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Is Create Plan.
		@param IsCreatePlan Is Create Plan	  */
	public void setIsCreatePlan (boolean IsCreatePlan)
	{
		set_Value (COLUMNNAME_IsCreatePlan, Boolean.valueOf(IsCreatePlan));
	}

	/** Get Is Create Plan.
		@return Is Create Plan	  */
	public boolean isCreatePlan () 
	{
		Object oo = get_Value(COLUMNNAME_IsCreatePlan);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Is Issue.
		@param IsIssue Is Issue	  */
	public void setIsIssue (boolean IsIssue)
	{
		set_Value (COLUMNNAME_IsIssue, Boolean.valueOf(IsIssue));
	}

	/** Get Is Issue.
		@return Is Issue	  */
	public boolean isIssue () 
	{
		Object oo = get_Value(COLUMNNAME_IsIssue);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Is MPS.
		@param IsMPS Is MPS	  */
	public void setIsMPS (boolean IsMPS)
	{
		set_Value (COLUMNNAME_IsMPS, Boolean.valueOf(IsMPS));
	}

	/** Get Is MPS.
		@return Is MPS	  */
	public boolean isMPS () 
	{
		Object oo = get_Value(COLUMNNAME_IsMPS);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Phantom.
		@param IsPhantom 
		Phantom Component
	  */
	public void setIsPhantom (boolean IsPhantom)
	{
		set_Value (COLUMNNAME_IsPhantom, Boolean.valueOf(IsPhantom));
	}

	/** Get Phantom.
		@return Phantom Component
	  */
	public boolean isPhantom () 
	{
		Object oo = get_Value(COLUMNNAME_IsPhantom);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Required Calculate DRP.
		@param IsRequiredDRP Required Calculate DRP	  */
	public void setIsRequiredDRP (boolean IsRequiredDRP)
	{
		set_ValueNoCheck (COLUMNNAME_IsRequiredDRP, Boolean.valueOf(IsRequiredDRP));
	}

	/** Get Required Calculate DRP.
		@return Required Calculate DRP	  */
	public boolean isRequiredDRP () 
	{
		Object oo = get_Value(COLUMNNAME_IsRequiredDRP);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Required Calculate MRP.
		@param IsRequiredMRP Required Calculate MRP	  */
	public void setIsRequiredMRP (boolean IsRequiredMRP)
	{
		set_ValueNoCheck (COLUMNNAME_IsRequiredMRP, Boolean.valueOf(IsRequiredMRP));
	}

	/** Get Required Calculate MRP.
		@return Required Calculate MRP	  */
	public boolean isRequiredMRP () 
	{
		Object oo = get_Value(COLUMNNAME_IsRequiredMRP);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public I_M_Product getM_Product() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_M_Product.Table_Name);
        I_M_Product result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_Product)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_Product_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1)
			 throw new IllegalArgumentException ("M_Product_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Product.
		@return Product, Service, Item
	  */
	public int getM_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_Warehouse getM_Warehouse() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_M_Warehouse.Table_Name);
        I_M_Warehouse result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_Warehouse)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_Warehouse_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Warehouse.
		@param M_Warehouse_ID 
		Storage Warehouse and Service Point
	  */
	public void setM_Warehouse_ID (int M_Warehouse_ID)
	{
		if (M_Warehouse_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_Warehouse_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_Warehouse_ID, Integer.valueOf(M_Warehouse_ID));
	}

	/** Get Warehouse.
		@return Storage Warehouse and Service Point
	  */
	public int getM_Warehouse_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Warehouse_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Order_Max.
		@param Order_Max Order_Max	  */
	public void setOrder_Max (BigDecimal Order_Max)
	{
		set_Value (COLUMNNAME_Order_Max, Order_Max);
	}

	/** Get Order_Max.
		@return Order_Max	  */
	public BigDecimal getOrder_Max () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Order_Max);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Minimum Order Qty.
		@param Order_Min 
		Minimum order quantity in UOM
	  */
	public void setOrder_Min (BigDecimal Order_Min)
	{
		set_Value (COLUMNNAME_Order_Min, Order_Min);
	}

	/** Get Minimum Order Qty.
		@return Minimum order quantity in UOM
	  */
	public BigDecimal getOrder_Min () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Order_Min);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Order Pack Qty.
		@param Order_Pack 
		Package order size in UOM (e.g. order set of 5 units)
	  */
	public void setOrder_Pack (BigDecimal Order_Pack)
	{
		set_Value (COLUMNNAME_Order_Pack, Order_Pack);
	}

	/** Get Order Pack Qty.
		@return Package order size in UOM (e.g. order set of 5 units)
	  */
	public BigDecimal getOrder_Pack () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Order_Pack);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Order Period.
		@param Order_Period Order Period	  */
	public void setOrder_Period (BigDecimal Order_Period)
	{
		set_Value (COLUMNNAME_Order_Period, Order_Period);
	}

	/** Get Order Period.
		@return Order Period	  */
	public BigDecimal getOrder_Period () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Order_Period);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Order_Policy AD_Reference_ID=53228 */
	public static final int ORDER_POLICY_AD_Reference_ID=53228;
	/** Order Fixed Quantity = FQ */
	public static final String ORDER_POLICY_OrderFixedQuantity = "FQ";
	/** Lote For Lote = LFL */
	public static final String ORDER_POLICY_LoteForLote = "LFL";
	/** Period Order Quantity = POQ */
	public static final String ORDER_POLICY_PeriodOrderQuantity = "POQ";
	/** Set Order Policy.
		@param Order_Policy Order Policy	  */
	public void setOrder_Policy (String Order_Policy)
	{

		if (Order_Policy == null || Order_Policy.equals("FQ") || Order_Policy.equals("LFL") || Order_Policy.equals("POQ")); else throw new IllegalArgumentException ("Order_Policy Invalid value - " + Order_Policy + " - Reference_ID=53228 - FQ - LFL - POQ");
		if (Order_Policy != null && Order_Policy.length() > 3)
		{
			log.warning("Length > 3 - truncated");
			Order_Policy = Order_Policy.substring(0, 3);
		}
		set_Value (COLUMNNAME_Order_Policy, Order_Policy);
	}

	/** Get Order Policy.
		@return Order Policy	  */
	public String getOrder_Policy () 
	{
		return (String)get_Value(COLUMNNAME_Order_Policy);
	}

	/** Set Order Qty.
		@param Order_Qty Order Qty	  */
	public void setOrder_Qty (BigDecimal Order_Qty)
	{
		set_Value (COLUMNNAME_Order_Qty, Order_Qty);
	}

	/** Get Order Qty.
		@return Order Qty	  */
	public BigDecimal getOrder_Qty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Order_Qty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public org.eevolution.model.I_PP_Product_BOM getPP_Product_BOM() throws Exception 
    {
        Class<?> clazz = MTable.getClass(org.eevolution.model.I_PP_Product_BOM.Table_Name);
        org.eevolution.model.I_PP_Product_BOM result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (org.eevolution.model.I_PP_Product_BOM)constructor.newInstance(new Object[] {getCtx(), new Integer(getPP_Product_BOM_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set BOM & Formula.
		@param PP_Product_BOM_ID 
		BOM & Formula
	  */
	public void setPP_Product_BOM_ID (int PP_Product_BOM_ID)
	{
		if (PP_Product_BOM_ID < 1) 
			set_Value (COLUMNNAME_PP_Product_BOM_ID, null);
		else 
			set_Value (COLUMNNAME_PP_Product_BOM_ID, Integer.valueOf(PP_Product_BOM_ID));
	}

	/** Get BOM & Formula.
		@return BOM & Formula
	  */
	public int getPP_Product_BOM_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PP_Product_BOM_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set PP_Product_Planning_ID.
		@param PP_Product_Planning_ID PP_Product_Planning_ID	  */
	public void setPP_Product_Planning_ID (int PP_Product_Planning_ID)
	{
		if (PP_Product_Planning_ID < 1)
			 throw new IllegalArgumentException ("PP_Product_Planning_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_PP_Product_Planning_ID, Integer.valueOf(PP_Product_Planning_ID));
	}

	/** Get PP_Product_Planning_ID.
		@return PP_Product_Planning_ID	  */
	public int getPP_Product_Planning_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PP_Product_Planning_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Planner_ID AD_Reference_ID=110 */
	public static final int PLANNER_ID_AD_Reference_ID=110;
	/** Set Planner.
		@param Planner_ID Planner	  */
	public void setPlanner_ID (int Planner_ID)
	{
		if (Planner_ID < 1) 
			set_Value (COLUMNNAME_Planner_ID, null);
		else 
			set_Value (COLUMNNAME_Planner_ID, Integer.valueOf(Planner_ID));
	}

	/** Get Planner.
		@return Planner	  */
	public int getPlanner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Planner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_S_Resource getS_Resource() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_S_Resource.Table_Name);
        I_S_Resource result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_S_Resource)constructor.newInstance(new Object[] {getCtx(), new Integer(getS_Resource_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Resource.
		@param S_Resource_ID 
		Resource
	  */
	public void setS_Resource_ID (int S_Resource_ID)
	{
		if (S_Resource_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_S_Resource_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_S_Resource_ID, Integer.valueOf(S_Resource_ID));
	}

	/** Get Resource.
		@return Resource
	  */
	public int getS_Resource_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_S_Resource_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Safety Stock Qty.
		@param SafetyStock 
		Safety stock is a term used to describe a level of stock that is maintained below the cycle stock to buffer against stock-outs
	  */
	public void setSafetyStock (BigDecimal SafetyStock)
	{
		set_Value (COLUMNNAME_SafetyStock, SafetyStock);
	}

	/** Get Safety Stock Qty.
		@return Safety stock is a term used to describe a level of stock that is maintained below the cycle stock to buffer against stock-outs
	  */
	public BigDecimal getSafetyStock () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_SafetyStock);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Time Fence.
		@param TimeFence Time Fence	  */
	public void setTimeFence (BigDecimal TimeFence)
	{
		set_Value (COLUMNNAME_TimeFence, TimeFence);
	}

	/** Get Time Fence.
		@return Time Fence	  */
	public BigDecimal getTimeFence () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TimeFence);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Transfert Time.
		@param TransfertTime Transfert Time	  */
	public void setTransfertTime (BigDecimal TransfertTime)
	{
		set_Value (COLUMNNAME_TransfertTime, TransfertTime);
	}

	/** Get Transfert Time.
		@return Transfert Time	  */
	public BigDecimal getTransfertTime () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TransfertTime);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Working Time.
		@param WorkingTime 
		Workflow Simulation Execution Time
	  */
	public void setWorkingTime (BigDecimal WorkingTime)
	{
		set_Value (COLUMNNAME_WorkingTime, WorkingTime);
	}

	/** Get Working Time.
		@return Workflow Simulation Execution Time
	  */
	public BigDecimal getWorkingTime () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_WorkingTime);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Yield.
		@param Yield Yield	  */
	public void setYield (int Yield)
	{
		set_Value (COLUMNNAME_Yield, Integer.valueOf(Yield));
	}

	/** Get Yield.
		@return Yield	  */
	public int getYield () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Yield);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}