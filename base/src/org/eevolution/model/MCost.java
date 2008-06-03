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
import java.math.*;
import java.util.*;
import java.util.logging.*;

import org.compiere.*;
import org.compiere.model.*;
import org.compiere.util.*;

/**
 * 	Product Cost Model
 *	
 *  @author Victor Perez www.e-evolution.com      
 *  @version $Id: MCost.java,v 1.19 2005/12/20 04:21:02 vpj-cd Exp $
 */
public class MCost extends org.compiere.model.MCost
{
	
	
	/**	Logger	*/
	private static CLogger 	s_log = CLogger.getCLogger (MCost.class);
	 static MCost[] m_lines = null;
	
	/**************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param ignored multi-key
	 *	@param trxName trx
	 */
	public MCost (Properties ctx, int ignored, String trxName)
	{
		super (ctx, ignored, trxName);
	}	//	MCost

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName trx
	 */
	public MCost (Properties ctx, ResultSet rs, String trxName)
	{
		super (ctx, rs, trxName);
	}	//	MCost

	/**
	 * 	Parent Constructor
	 *	@param product Product
	 *	@param M_AttributeSetInstance_ID asi
	 *	@param as Acct Schema
	 *	@param AD_Org_ID org
	 *	@param M_CostElement_ID cost element
	 */
	public MCost (MProduct product, int M_AttributeSetInstance_ID, 
		MAcctSchema as, int AD_Org_ID, int M_CostElement_ID)
	{
		super(product,M_AttributeSetInstance_ID, as,  AD_Org_ID,M_CostElement_ID);
	}	//	MCost
        
        /**
	 * 	Get Element Cost
	 * 	@return lines
	 */
	public static MCost[] getElements (int M_Product_ID, int C_AcctSchema_ID, int M_CostType_ID)
	{
		
		ArrayList list = new ArrayList();
                
                
		String sql = "SELECT * FROM M_Cost WHERE AD_Client_ID = ? AND M_Product_ID=? AND  C_Acctschema_ID = ? AND M_CostType_ID = ? ";
		PreparedStatement pstmt = null;
		try
		{
			int AD_Client_ID = Integer.parseInt(Env.getContext(Env.getCtx(), "#AD_Client_ID"));			
			pstmt = DB.prepareStatement(sql);
			pstmt.setInt(1, AD_Client_ID);
			pstmt.setInt(2, M_Product_ID);
                        pstmt.setInt(3, C_AcctSchema_ID);
                        pstmt.setInt(4, M_CostType_ID);
                        //pstmt.setInt(5, M_Warehouse_ID);
                        //pstmt.setInt(6, S_Resource_ID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
                        {    
			   list.add(new  MCost(Env.getCtx(), rs,"M_Cost"));                          
                        }                        
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (SQLException ex)
		{
			//log.error("getLines", ex);
                        s_log.fine("getLines" +  ex);
                        //System.out.println("getLines" +  ex);
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
		m_lines = new  MCost[list.size()];
		list.toArray(m_lines);
		return m_lines;
	}	//	getMInOutLines
        
        /**
	 * 	Get Element Cost
	 * 	@return lines
	 */
	public boolean getElement(int M_Product_ID, int C_AcctSchema_ID, int M_CostType_ID , int M_CostElement_ID)
	{
		//if (m_lines != null && !requery)
		//return m_lines;
		//ArrayList list = new ArrayList();
                MCost pc = null; 
		String sql = "SELECT * FROM M_Cost WHERE AD_Client_ID =? AND  M_Product_ID=? AND ( C_Acctschema_ID = ? AND M_Costtype_ID = ? AND M_CostElement_ID = ? )";
		PreparedStatement pstmt = null;
		try
		{
			int AD_Client_ID = Integer.parseInt(Env.getContext(Env.getCtx(), "#AD_Client_ID"));			
			pstmt = DB.prepareStatement(sql);
			pstmt.setInt(1, AD_Client_ID);
			pstmt.setInt(2, M_Product_ID);
                        pstmt.setInt(3, C_AcctSchema_ID);
                        pstmt.setInt(4, M_CostType_ID);
                        pstmt.setInt(5, M_CostElement_ID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
                        {
                            return true;
                        }
				//pc = new  MMPCProductCosting(getCtx(), rs);
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

        
        	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true if can be saved
	 */
	protected boolean beforeSave (boolean newRecord)
	{
            boolean result = super.beforeSave(newRecord);
            //setCumulatedAmt(getCumulatedAmt().add(getCurrentCostPriceLL()));
            setCumulatedAmt(getCumulatedAmt().add(getCurrentCostPrice()));
            return result;
        }

}	//	MCost
