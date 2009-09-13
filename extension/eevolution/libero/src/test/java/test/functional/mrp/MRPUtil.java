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
package test.functional.mrp;

import java.util.Properties;

import org.compiere.model.MLocation;
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
	public static MWarehouse getCreateWarehouse(int AD_Org_ID, String value)
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
	public static MResource getCreatePlant(String value, int M_Warehouse_ID, int PlanningHorizon)
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
