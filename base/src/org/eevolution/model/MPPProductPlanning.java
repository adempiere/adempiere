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

import java.sql.*;
import java.util.*;
import java.util.logging.*;


import org.compiere.util.*;
import org.compiere.model.*;

/**
 *	Product Data Planning 
 *	
 *  @author Victor Perez www.e-evolution.com     
 *  @version $Id: MPProductPlannning.java,v 1.4 2004/05/13 06:05:22 vpj-cd Exp $
 */
public class MPPProductPlanning extends X_PP_Product_Planning
{
	/**
	 * 	Get from Cache
	 *	@param ctx context
	 *	@param M_Product_Costing_ID id
	 *	@return
	 */
   
    
	/**	Cache						*/
	private static CCache	s_cache = new CCache ("M_Product_Costing", 20);
	
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
    public static MPPProductPlanning get(Properties ctx, int ad_org_id , int m_product_id, String trxname)               
	{
    	int m_M_Warehouse_ID = MOrgInfo.get(ctx, ad_org_id).getM_Warehouse_ID();
    	if(m_M_Warehouse_ID <= 0)
    		return null;
    	
    	int m_S_Resource_ID = DB.getSQLValue(trxname, "SELECT MAX(S_Resource_ID) FROM S_Resource WHERE IsManufacturingResource='Y' AND ManufacturingResourceType ='" + MResource.MANUFACTURINGRESOURCETYPE_Plant +"' AND M_Warehouse_ID= ?", m_M_Warehouse_ID);
    	
    	if (m_S_Resource_ID <=0 )
    		return null;
    	
        return get(ctx,ad_org_id, m_M_Warehouse_ID, m_S_Resource_ID, m_product_id, trxname);
	}
    
	/**
	 * Get Data Product Planning 
	 * @param ctx Context
	 * @param AD_Org_ID ID Organization
	 * @param M_Warehouse_ID Warehouse
	 * @param S_Resource_ID Resource type Plant
	 * @param M_Product_ID ID Product
	 * @param trxname Trx Name
	 * @return MPPProductPlanning
	 */     
    public static MPPProductPlanning get(Properties ctx, int ad_org_id , int m_warehouse_id, int s_resource_id, int m_product_id, String trxname)
	{
		
        log.info("Ad_Org_ID" + ad_org_id + "M_Product_ID" + m_product_id + "M_Warehouse_ID" + m_warehouse_id + "S_Resource_ID" + s_resource_id );             
        String sql = "SELECT * FROM PP_Product_Planning  pp WHERE pp.AD_Org_ID = ? AND pp.M_Product_ID = ? AND pp.M_Warehouse_ID = ? AND pp.S_Resource_ID = ? ";	
                
        PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement(sql, trxname);
			pstmt.setInt(1, ad_org_id);
			pstmt.setInt(2, m_product_id);
			pstmt.setInt(3, m_warehouse_id);
            pstmt.setInt(4, s_resource_id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
				return new MPPProductPlanning(ctx, rs, trxname);
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE,"getProductPlanning", e);
		}
		try
		{
			if (pstmt != null)
				pstmt.close();
			pstmt = null;
		}
		catch (Exception e)
		{
			pstmt = null;
		}	
		return null;
	}       
	
    
	/**************************************************************************
	 * 	find data planning, try find the specific planning data if do not found then try find data planning general 
	 * @param ctx Context
	 * @param AD_Org_ID Organization ID
	 * @param M_Warehouse_ID Resource ID
	 * @param S_Resource_ID Resource ID
	 * @param M_Product_ID Product ID
	 * @param trxName Transaction Name
	 *	@return MPPProductPlanning Planning Data
	 */     
    public static MPPProductPlanning getMPPProductPlanning(Properties ctx ,int  AD_Client_ID, int  AD_Org_ID ,int M_Warehouse_ID, int S_Resource_ID, int M_Product_ID, String trxName)
	{          
        MPPProductPlanning pp = null;        
        PreparedStatement pstmt = null;
		try
		{
			// Find specific data planning
			String sql = "SELECT * FROM PP_Product_Planning  pp WHERE pp.AD_Client_ID = ? AND pp.AD_Org_ID = ?  AND pp.M_Warehouse_ID=? AND pp.S_Resource_ID = ? AND pp.M_Product_ID = ? ";	
	        
			pstmt = DB.prepareStatement(sql ,trxName);
			pstmt.setInt(1, AD_Client_ID);
			pstmt.setInt(2, AD_Org_ID);		
			pstmt.setInt(3, M_Warehouse_ID);
			pstmt.setInt(4, S_Resource_ID);
			pstmt.setInt(5, M_Product_ID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{	
				return new MPPProductPlanning(ctx, rs, trxName);
			}
			rs.close();
			pstmt.close();
			pstmt = null;
			
			// Find general data planning Org = * , Wharehouse = *  
			sql = "SELECT * FROM PP_Product_Planning  pp WHERE pp.AD_Client_ID = ? AND (pp.AD_Org_ID = 0 OR AD_Org_ID IS NULL) AND (pp.M_Warehouse_ID = 0 OR  pp.M_Warehouse_ID IS NULL) AND pp.S_Resource_ID = ? AND pp.M_Product_ID = ? ";	
			pstmt = DB.prepareStatement(sql ,trxName);
			pstmt.setInt(1, AD_Client_ID);
			pstmt.setInt(2, S_Resource_ID);
			pstmt.setInt(3, M_Product_ID);
			rs = pstmt.executeQuery();
			while (rs.next())
			{	
				return new MPPProductPlanning(ctx, rs, trxName);
			}
			rs.close();
			pstmt.close();
			pstmt = null;
			
			// Find general data planning Org = * , Wharehouse = *  , Resource = *
			sql = "SELECT * FROM PP_Product_Planning  pp WHERE pp.AD_Client_ID = ? AND (pp.AD_Org_ID = 0 OR AD_Org_ID IS NULL) AND (pp.M_Warehouse_ID = 0 OR  pp.M_Warehouse_ID IS NULL) AND (pp.S_Resource_ID =0 OR  pp.S_Resource_ID IS NULL ) AND pp.M_Product_ID = ? ";	
			pstmt = DB.prepareStatement(sql ,trxName);
			pstmt.setInt(1, AD_Client_ID);
			pstmt.setInt(2, M_Product_ID);
			rs = pstmt.executeQuery();
			while (rs.next())
			{	
				return new MPPProductPlanning(ctx, rs, trxName);
			}
			rs.close();
			pstmt.close();
			pstmt = null;
			
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE,"getProductPlanning", e);
		}
		try
		{
			if (pstmt != null)
				pstmt.close();
			pstmt = null;
		}
		catch (Exception e)
		{
			pstmt = null;
		}	
		return null;
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
        String sql = "SELECT * FROM PP_Product_Planning  pp WHERE pp.AD_Org_ID = ? AND pp.M_Product_ID = ?";	
                
                PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement(sql,trxName);
			pstmt.setInt(1, AD_Org_ID);
			pstmt.setInt(2, M_Product_ID);
            //pstmt.setInt(4, S_Resource_ID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
				return new MPPProductPlanning(ctx, rs,trxName);
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE,"getProductPlanning", e);
		}
		try
		{
			if (pstmt != null)
				pstmt.close();
			pstmt = null;
		}
		catch (Exception e)
		{
			pstmt = null;
		}	
		return null;
	}      
}	//	Product Data Planning

