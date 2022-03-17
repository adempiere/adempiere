/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2008 SC ARHIPAC SERVICE SRL. All Rights Reserved.            *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *****************************************************************************/
package org.eevolution.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.model.I_M_Product;
import org.compiere.model.I_M_Warehouse;
import org.compiere.model.I_S_Resource;
import org.compiere.model.MLocation;
import org.compiere.model.MProduct;
import org.compiere.model.MResource;
import org.compiere.model.MWarehouse;
import org.compiere.model.Query;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * Many helper methods for producing different entities
 * @author Teo Sarca, www.arhipac.ro
 */
public class MRPUtil
{
	
	public static int getFirst_Org_ID()
	{
		String sql = "SELECT MIN(AD_Org_ID) FROM AD_Org WHERE AD_Client_ID=?";
		return DB.getSQLValueEx(null, sql, Env.getAD_Client_ID(Env.getCtx()));
	}

	/**
	 * Helper Method : Create Warehouse
	 */
	public static I_M_Warehouse getCreateWarehouse(int AD_Org_ID, String value)
	{
		Properties ctx = Env.getCtx();
		int AD_Client_ID = Env.getAD_Client_ID(ctx);
		String whereClause = "AD_Client_ID=? AND AD_Org_ID=? AND Value=?";
		MWarehouse wh = new Query(ctx, MWarehouse.Table_Name, whereClause, null)
							.setParameters(new Object[]{AD_Client_ID, AD_Org_ID, value})
							.firstOnly();
		if (wh != null)
			return wh;
		wh = new MWarehouse(ctx, 0, null);
		wh.setAD_Org_ID(AD_Org_ID);
		wh.setValue(value);
		wh.setName(value);
		
		MLocation loc = new MLocation(ctx, 0, null);
		loc.saveEx();
		wh.setC_Location_ID(loc.get_ID());
		wh.saveEx();
		return wh;
	}
	
	/**
	 * Helper Method : Create Plant (S_Resource_ID)
	 */
	public static I_S_Resource getCreatePlant(String value, int M_Warehouse_ID, int PlanningHorizon)
	{
		Properties ctx = Env.getCtx();
		int AD_Client_ID = Env.getAD_Client_ID(ctx);
		String whereClause = MResource.COLUMNNAME_Value+"=? AND AD_Client_ID=?";
		MResource r = new Query(ctx, MResource.Table_Name, whereClause, null)
			.setParameters(new Object[]{value, AD_Client_ID})
			.firstOnly();
		if (r == null)
		{
			r = new MResource(ctx, 0, null);
			int S_ResourceType_ID = DB.getSQLValueEx(null,
					"SELECT MIN(S_ResourceType_ID) FROM S_Resource WHERE AD_Client_ID=? AND IsAvailable=?",
					AD_Client_ID, true);
			r.setS_ResourceType_ID(S_ResourceType_ID);
		}
		r.setValue(value);
		r.setName(value);
		r.setIsManufacturingResource(true);
		r.setManufacturingResourceType(MResource.MANUFACTURINGRESOURCETYPE_Plant);
		r.setIsAvailable(true);
		r.setM_Warehouse_ID(M_Warehouse_ID);
		r.setPlanningHorizon(PlanningHorizon);
		r.setPercentUtilization(Env.ONEHUNDRED);
		r.saveEx();
		return r;
	}
	
	/**
	 * Helper Method : Create Product
	 */
	public static I_M_Product getCreateProduct(Properties ctx, String value, boolean isPurchased)
	{
		MProduct[] arr = MProduct.get(ctx, "Value='"+value+"'", null);
		if (arr.length > 0)
		{
			return arr[0];
		}
		MProduct p = new MProduct(ctx, 0, null);
		p.setValue(value);
		p.setName(value);
		p.setAD_Org_ID(0);
		p.setProductType (MProduct.PRODUCTTYPE_Item);      // I
		p.setIsBOM (false);       // N
		p.setIsInvoicePrintDetails (false);
		p.setIsPickListPrintDetails (false);
		p.setIsPurchased (isPurchased);
		p.setIsSold (true);       // Y
		p.setIsStocked (true);    // Y
		p.setIsSummary (false);
		p.setIsVerified (false);  // N
		p.setIsWebStoreFeatured (false);
		p.setIsSelfService(true);
		p.setIsExcludeAutoDelivery(false);
		p.setProcessing (false);  // N
		p.setC_UOM_ID(100); // Each
		p.saveEx();
		return p;
	}
	
	/**
	 * Helper Method : Create Product Planning
	 */
	public static I_PP_Product_Planning getPlanning(String productValue,
			String Order_Policy,
			int Order_Min, int Order_Max, int Order_Pack, int SafetyStock,
			int Order_Period,
			int LeadTime)
	{
		boolean isPurchased = true;
		int PlanningHorizon = 365;
		//
		Properties ctx = Env.getCtx();
//		int AD_Client_ID = Env.getAD_Client_ID(ctx);
		int AD_Org_ID = MRPUtil.getFirst_Org_ID();
		I_M_Warehouse wh = MRPUtil.getCreateWarehouse(AD_Org_ID, productValue);
		I_S_Resource plant = MRPUtil.getCreatePlant(productValue, wh.getM_Warehouse_ID(), PlanningHorizon);
		I_M_Product product = MRPUtil.getCreateProduct(ctx, productValue, isPurchased);
		//
		MPPProductPlanning pp = new MPPProductPlanning(ctx, 0, null);
		pp.setIsCreatePlan(true);
		pp.setIsRequiredMRP(true);
		pp.setIsRequiredDRP(false);
		pp.setM_Product_ID(product.getM_Product_ID());
		pp.setAD_Org_ID(AD_Org_ID);
		pp.setM_Warehouse_ID(wh.getM_Warehouse_ID());
		pp.setS_Resource_ID(plant.getS_Resource_ID());
		//
		pp.setOrder_Policy(Order_Policy);
		pp.setOrder_Min(BigDecimal.valueOf(Order_Min));
		pp.setOrder_Max(BigDecimal.valueOf(Order_Max));
		pp.setOrder_Pack(BigDecimal.valueOf(Order_Pack));
		pp.setSafetyStock(BigDecimal.valueOf(SafetyStock));
		pp.setOrder_Period(BigDecimal.valueOf(Order_Period));
		pp.setDeliveryTime_Promised(BigDecimal.valueOf(LeadTime));
		//
		return pp;
	}
	
	public static I_PP_MRP createMRP(I_PP_Product_Planning planning,
			String TypeMRP, String DocStatus, BigDecimal Qty,
			Timestamp DatePromised, Timestamp DateStartSchedule)
	{
		Properties ctx = Env.getCtx();
		//
		MPPMRP mrp = new MPPMRP(ctx, 0, null);
		mrp.setAD_Org_ID(planning.getAD_Org_ID());
		mrp.setName("MRP");
		mrp.setTypeMRP(TypeMRP);
		mrp.setDocStatus(DocStatus);
		mrp.setQty(Qty);
		mrp.setDatePromised(DatePromised);
		mrp.setDateStartSchedule(DateStartSchedule);
		mrp.setDateFinishSchedule(DatePromised);
		mrp.setDateOrdered(DatePromised);
		mrp.setM_Product_ID(planning.getM_Product_ID());
		mrp.setM_Warehouse_ID(planning.getM_Warehouse_ID());
		return mrp;
	}
	
	public static String toString(Object[] arr)
	{
		if (arr == null)
			return "null";
		//
		StringBuffer sb = new StringBuffer();
		int i = 1;
		sb.append("(size="+arr.length+")");
		for (Object o : arr)	
		{
			sb.append("["+i+":"+o+"]");
			i++;
		}
		return sb.toString();
	}
}
