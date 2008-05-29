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
//package org.compiere.mfg.model;
package org.eevolution.model;

import java.sql.*;
import java.util.*;
import java.util.logging.*;


import org.compiere.util.*;
import org.compiere.model.*;

/**
 *	Product Costing Model
 *	
 *  @author Victor Perez www.e-evolution.com     
 *  @version $Id: MProductCosting.java,v 1.4 2004/05/13 06:05:22 vpj-cd Exp $
 */
public class MPPProductCosting extends X_M_Product_Costing
{
	/**
	 * 	Get from Cache
	 *	@param ctx context
	 *	@param M_Product_Costing_ID id
	 *	@return
	 */
        /*
	public static M_ProductCosting get (Properties ctx, int M_Product_Costing_ID)
	{
		Integer ii = new Integer (M_Product_Costing_ID);
		M_ProductCosting pc = (M_ProductCosting)s_cache.get(ii);
		if (pc == null)
			pc = new M_ProductCosting (ctx, M_Product_Costing_ID);
		return pc;
	}	//	get
	*/
    
	/**	Cache						*/
	//private static CCache	s_cache = new CCache ("M_Product_Costing", 20);
	
    
         static MPPProductCosting[] m_lines = null;
	
	/**************************************************************************
	 * 	Default Constructor
	 *	@param ctx context
	 *	@param M_Product_Costing_ID id
	 */
	public MPPProductCosting(Properties ctx, int M_Product_Costing_ID,String trxName)
	{
		super(ctx, M_Product_Costing_ID,trxName);
		if (M_Product_Costing_ID == 0)
		{
                /*    
		setC_AcctSchema_ID(0);
                setCostCumAmt(); 
                setCostCumQty();
                setCostLLAmt();
                setCostTLAmt();
                setM_Product_ID();
                setM_Warehouse_ID();
                setPP_Cost_Element_ID();
                stS_Resource_ID();*/
		}
	}	//	MProductCosting

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 */
	public MPPProductCosting(Properties ctx, ResultSet rs,String trxName)
	{
		super(ctx, rs,trxName);
	}	//	MProductCosting
        
        
        /**
	 * 	Get Element Cost
	 * 	@return lines
	 */
	public static MPPProductCosting[] getElements (int M_Product_ID, int C_AcctSchema_ID, int PP_Cost_Group_ID , int M_Warehouse_ID, int S_Resource_ID)
	{
		
		ArrayList list = new ArrayList();
                
                
		String sql = "SELECT * FROM PP_Product_Costing WHERE AD_Client_ID = ? AND M_Product_ID=? AND  C_Acctschema_ID = ? AND PP_Cost_Group_ID = ? AND M_Warehouse_ID = ? AND S_Resource_ID = ?  ";
		PreparedStatement pstmt = null;
		try
		{
			int AD_Client_ID = Integer.parseInt(Env.getContext(Env.getCtx(), "#AD_Client_ID"));			
			pstmt = DB.prepareStatement(sql);
			pstmt.setInt(1, AD_Client_ID);
			pstmt.setInt(2, M_Product_ID);
            pstmt.setInt(3, C_AcctSchema_ID);
            pstmt.setInt(4, PP_Cost_Group_ID);
            pstmt.setInt(5, M_Warehouse_ID);
            pstmt.setInt(6, S_Resource_ID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
                        {    
			   list.add(new  MPPProductCosting(Env.getCtx(), rs,"PP_Product_Costing"));                          
                        }
                        
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (SQLException ex)
		{
                   // s_log.log (Level.SEVERE, sql, ex);
			//log.error("getLines", ex);
                    System.out.println("getLines" +  ex);
		}
		try
		{
			if (pstmt != null)
				pstmt.close();
		}
		catch (SQLException ex1)
		{
		}
		pstmt = null;
		//
		m_lines = new  MPPProductCosting[list.size()];
		list.toArray(m_lines);
		return m_lines;
	}	//	getMInOutLines
        

        
        
                
         /**
	 * 	Get Element Cost
	 * 	@return lines
	 */
	public boolean getElement(int M_Product_ID, int C_AcctSchema_ID, int PP_Cost_Group_ID , int PP_Cost_Element_ID , int M_Warehouse_ID, int S_Resource_ID)
	{
		//if (m_lines != null && !requery)
		//return m_lines;
		//ArrayList list = new ArrayList();
                MPPProductCosting pc = null; 
		String sql = "SELECT * FROM PP_Product_Costing WHERE AD_Client_ID =? AND  M_Product_ID=? AND ( C_Acctschema_ID = ? AND PP_Cost_Group_ID = ? AND PP_Cost_Element_ID = ?  AND M_Warehouse_ID = ? AND S_Resource_ID = ?)";
		PreparedStatement pstmt = null;
		try
		{
			int AD_Client_ID = Integer.parseInt(Env.getContext(Env.getCtx(), "#AD_Client_ID"));			
			pstmt = DB.prepareStatement(sql);
						pstmt.setInt(1, AD_Client_ID);
						pstmt.setInt(2, M_Product_ID);
                        pstmt.setInt(3, C_AcctSchema_ID);
                        pstmt.setInt(4, PP_Cost_Group_ID);
                        pstmt.setInt(5, PP_Cost_Element_ID);
                        pstmt.setInt(6, M_Warehouse_ID);
                        pstmt.setInt(7, S_Resource_ID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
                        {
                            return true;
                        }
				//pc = new  MPP_ProductCosting(getCtx(), rs);
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (SQLException ex)
		{
			log.log(Level.SEVERE,"getLines", ex);
		}
		try
		{
			if (pstmt != null)
				pstmt.close();
		}
		catch (SQLException ex1)
		{
		}
		pstmt = null;
		return false;
	}	//	getMInOutLines
        


}	//	MProductCosting
