/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2007 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/
package org.eevolution.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.MOrgInfo;
import org.compiere.model.MResource;
import org.compiere.model.Query;
import org.compiere.util.CLogger;
import org.compiere.util.DB;

/**
 *	Product Data Planning 
 *	
 *  @author Victor Perez www.e-evolution.com     
 *  @version $Id: MPProductPlannning.java,v 1.4 2004/05/13 06:05:22 vpj-cd Exp $
 * 
 *  @author Teo Sarca, www.arhipac.ro
 */
public class MPPProductPlanning extends X_PP_Product_Planning
{
	/** Log									*/
	private static CLogger log = CLogger.getCLogger(MPPProductPlanning.class); 


	/**************************************************************************
	 * 	Default Constructor
	 *	@param ctx context
	 *	@param pp_product_planning_id id
	 *	@param trxName
	 *  @return MPPProductPlanning Data Product Planning 
	 */
	public MPPProductPlanning(Properties ctx, int pp_product_planning_id,String trxname)
	{
		super(ctx, pp_product_planning_id,trxname);
		if (pp_product_planning_id == 0)
		{    
		}
	}	//	MPPProductPlanning

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName Transaction Name
	 *	@return MPPProductPlanning Data Product Planning 
	 */
	public MPPProductPlanning(Properties ctx, ResultSet rs,String trxname)
	{
		super(ctx, rs,trxname);
	}

	/**
	 * 	Get Data Product Planning to Organization
	 * @param ctx Context
	 * @param ad_org_id Organization ID
	 * @param m_product_id Product ID
	 * @param trxName Transaction Name 
	 * @return MPPProductPlanning
	 */    
	public static MPPProductPlanning get(Properties ctx,int ad_client_id, int ad_org_id , int m_product_id, String trxname)               
	{
		int m_M_Warehouse_ID = MOrgInfo.get(ctx, ad_org_id).getM_Warehouse_ID();
		if(m_M_Warehouse_ID <= 0)
			return null;

		int m_S_Resource_ID = DB.getSQLValue(trxname, "SELECT MAX(S_Resource_ID) FROM S_Resource WHERE IsManufacturingResource='Y' AND ManufacturingResourceType ='" + MResource.MANUFACTURINGRESOURCETYPE_Plant +"' AND AD_Client_ID=? AND M_Warehouse_ID= ?", ad_client_id, m_M_Warehouse_ID);

		if (m_S_Resource_ID <=0 )
			return null;

		return get(ctx, ad_client_id,ad_org_id, m_M_Warehouse_ID, m_S_Resource_ID, m_product_id, trxname);
	}

	/**
	 * Get Data Product Planning 
	 * @param ctx Context
	 * @param AD_Client_ID ID Organization
	 * @param AD_Org_ID ID Organization
	 * @param M_Warehouse_ID Warehouse
	 * @param S_Resource_ID Resource type Plant
	 * @param M_Product_ID ID Product
	 * @param trxname Trx Name
	 * @return MPPProductPlanning
	 */     
	public static MPPProductPlanning get(Properties ctx,int ad_client_id, int ad_org_id , int m_warehouse_id, int s_resource_id, int m_product_id, String trxname)
	{
		log.info("AD_Client_ID="  + ad_client_id + " AD_Org_ID=" + ad_org_id + " M_Product_ID=" + m_product_id + " M_Warehouse_ID=" + m_warehouse_id + " S_Resource_ID=" + s_resource_id );
		String  sql_warehouse = COLUMNNAME_M_Warehouse_ID+"=?";
		if(m_warehouse_id == 0) {
			sql_warehouse += " OR "+COLUMNNAME_M_Warehouse_ID+" IS NULL";
		}

		String whereClause =
			" AD_Client_ID=? AND AD_Org_ID=?"
			+" AND "+COLUMNNAME_M_Product_ID+"=?"
			+" AND ("+sql_warehouse+")"
			+" AND "+COLUMNNAME_S_Resource_ID+"=?";

		return new Query(ctx, MPPProductPlanning.Table_Name, whereClause, trxname)
			.setParameters(new Object[]{ad_client_id, ad_org_id, m_product_id, m_warehouse_id, s_resource_id})
			.first();
	}       


	/**************************************************************************
	 * Find data planning, try find the specific planning data
	 * if do not found then try find data planning general 
	 * @param ctx Context
	 * @param AD_Org_ID Organization ID
	 * @param M_Warehouse_ID Resource ID
	 * @param S_Resource_ID Resource ID
	 * @param M_Product_ID Product ID
	 * @param trxName Transaction Name
	 *	@return MPPProductPlanning Planning Data
	 **/
	public static MPPProductPlanning find(Properties ctx ,int  AD_Client_ID, int  AD_Org_ID ,int M_Warehouse_ID, int S_Resource_ID, int M_Product_ID, String trxName)
	{          
		final String whereClause = "AD_Client_ID=? AND M_Product_ID=?"
								+ " AND (AD_Org_ID IN (0,?) OR AD_Org_ID IS NULL)"
								+ " AND (M_Warehouse_ID IN (0,?) OR M_Warehouse_ID IS NULL)"
								+ " AND (S_Resource_ID IN (0,?) OR S_Resource_ID IS NULL)";
		return new Query(ctx, Table_Name, whereClause, trxName)
				.setParameters(new Object[]{AD_Client_ID, M_Product_ID, AD_Org_ID, M_Warehouse_ID, S_Resource_ID})
				.setOrderBy("AD_Org_ID DESC NULLS LAST, M_Warehouse_ID DESC NULLS LAST, S_Resource_ID DESC NULLS LAST")
				.first();
	}


	/**************************************************************************
	 * 	find planning data demand & supply to this warehouse
	 *	@param ctx Context
	 *	@param AD_Org_ID Organization ID
	 *  @param M_Product_ID Product ID
	 *  @param trxName Transaction Name
	 *  @return MPPProductPlanning Planning Data
	 */       
	public static MPPProductPlanning getDemandSupplyResource(Properties ctx , int  AD_Org_ID , int M_Product_ID, String trxName )
	{           
		String whereClause = "AD_Org_ID = ? AND "+COLUMNNAME_M_Product_ID+"=?";	
		return new Query(ctx, MPPProductPlanning.Table_Name, whereClause, trxName)
			.setParameters(new Object[]{AD_Org_ID, M_Product_ID})
			.first();
	}      
}	//	Product Data Planning

