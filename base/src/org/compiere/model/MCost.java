/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
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
 * Contributor(s): Teo Sarca                                                  *
 *****************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import org.adempiere.engine.CostComponent;
import org.adempiere.engine.IDocumentLine;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.DBException;
import org.compiere.Adempiere;
import org.compiere.util.*;

/**
 * 	Product Cost Model
 *
 *  @author Jorg Janke
 *  @version $Id: MCost.java,v 1.6 2006/07/30 00:51:02 jjanke Exp $
 *
 *  @author Carlos Ruiz - globalqss
 *		<li>integrate bug fix from Teo Sarca - [ 1619112 ] Posible problem for LastPO costing, Batch/Lot level
 *
 *  @author Red1
 *  	<li>FR: [ 2214883 ] Remove SQL code and Replace for Query - red1 (only non-join query)
 *  
 *  @author Teo Sarca
 *  	<li>BF [ 2847648 ] Manufacture & shipment cost errors
 *  		https://sourceforge.net/tracker/?func=detail&aid=2847648&group_id=176962&atid=934929
 */
public class MCost extends X_M_Cost
{
    /**
     *
     * @param product
     * @param as
     * @param M_CostType_ID
     * @param AD_Org_ID
     * @param M_Warehouse_ID
     * @param M_AttributeSetInstance_ID
     * @param M_CostElement_ID
     * @return
     */
        public static List<MCost> getByElement(MProduct product, MAcctSchema as,
                                        int M_CostType_ID, int AD_Org_ID,int M_Warehouse_ID ,int M_AttributeSetInstance_ID,
                                        int M_CostElement_ID) {
            org.adempiere.engine.CostDimension cd = new org.adempiere.engine.CostDimension(product, as, M_CostType_ID,
                    AD_Org_ID,  M_Warehouse_ID, M_AttributeSetInstance_ID, M_CostElement_ID);
            return cd.toQuery(MCost.class, product.get_TrxName())
                    .setOnlyActiveRecords(true).list();
        }

		public static MCost getDimension(MProduct product , int C_AcctSchema_ID , int  AD_Org_ID , int  M_Warehouse_ID , int M_AttributeSetInstance_ID, int M_CostType_ID , int M_CostElement_ID)
		{
			MAcctSchema acctSchema = new  MAcctSchema(product.getCtx(), C_AcctSchema_ID , product.get_TrxName());

			ArrayList<Object> parameters = new ArrayList<Object>();
			StringBuilder whereClause = new StringBuilder();
			
			whereClause.append(I_M_Cost.COLUMNNAME_C_AcctSchema_ID).append("=? AND ");
			whereClause.append(I_M_Cost.COLUMNNAME_M_Product_ID).append("=? AND ");
			whereClause.append(I_M_Cost.COLUMNNAME_M_CostType_ID).append("=? AND ");
			whereClause.append(I_M_Cost.COLUMNNAME_M_CostElement_ID ).append("=? ");
			
			parameters.add(C_AcctSchema_ID);
			parameters.add(product.getM_Product_ID());
			parameters.add(M_CostType_ID);
			parameters.add(M_CostElement_ID);

			if (M_CostElement_ID == 0)
				throw new IllegalArgumentException(
						"No Costing Element Material Type");

			if (M_CostType_ID == 0)
				throw new AdempiereException(
						"Error do not exist material cost element for method cost "
								+ acctSchema.getCostingMethod());

			String CostingLevel = product.getCostingLevel(acctSchema, AD_Org_ID);
			MCostType costType =  new MCostType(product.getCtx() , M_CostType_ID , product.get_TrxName());
			String costingMethod = costType.getCostingMethod();

			if (MAcctSchema.COSTINGLEVEL_Client.equals(CostingLevel)) {
				//Ignore organization, warehouse , asi
				AD_Org_ID = 0;
				M_Warehouse_ID = 0;
				M_AttributeSetInstance_ID = 0;
			}
			else if (MAcctSchema.COSTINGLEVEL_Organization.equals(CostingLevel))
			{
				if (AD_Org_ID <= 0)
					throw new AdempiereException("@AD_Org_ID@ @NotFound@");
				//Ignore  warehouse , asi
				M_Warehouse_ID = 0;
				M_AttributeSetInstance_ID = 0;
			}
			else if (MAcctSchema.COSTINGLEVEL_Warehouse.equals(CostingLevel))
			{
				if (M_Warehouse_ID <= 0)
					throw new AdempiereException("@M_Warehouse_ID@ @NotFound@");
				//Ignore organization asi
				M_AttributeSetInstance_ID = 0;
			}
			else if (MAcctSchema.COSTINGLEVEL_BatchLot.equals(CostingLevel))
			{
				//Ignore organization, warehouse
				AD_Org_ID = 0;
				M_Warehouse_ID = 0;
			}
			// Costing Method
			if (costingMethod == null) {
				costingMethod = product.getCostingMethod(acctSchema, AD_Org_ID);
				if (costingMethod == null) {
					throw new IllegalArgumentException("No Costing Method");
				}
			}

			if (AD_Org_ID > 0 )
			{
				whereClause.append(" AND ").append(I_M_Cost.COLUMNNAME_AD_Org_ID).append("=? ");
				parameters.add(AD_Org_ID);
			}
			if (M_Warehouse_ID > 0 )
			{
				whereClause.append(" AND ").append(I_M_Cost.COLUMNNAME_M_Warehouse_ID).append("=? ");
				parameters.add(M_Warehouse_ID);
			}
			 
			if (M_AttributeSetInstance_ID > 0)
			{
				whereClause.append(" AND ").append(I_M_Cost.COLUMNNAME_M_AttributeSetInstance_ID).append("=? ");
				parameters.add(M_AttributeSetInstance_ID);
			}

			return new Query(product.getCtx(), I_M_Cost.Table_Name, whereClause.toString(), product.get_TrxName())
			.setClient_ID()
			.setOnlyActiveRecords(true)
			.setParameters(parameters)	
			.first();
		}
		
		public static MCost validateCostForCostType(MAcctSchema as, MCostType ct,
				MCostElement ce, int M_Product_ID, int AD_Org_ID, int M_Warehouse_ID,
				int M_AttributeSetInstance_ID, String trxName) {
			
			if (ce == null)
				throw new IllegalArgumentException(
						"No Costing Element Material Type");

			if (ct == null)
				throw new AdempiereException(
						"Error do not exist material cost element for method cost "
								+ as.getCostingMethod());

			MProduct product = new MProduct(ct.getCtx(), M_Product_ID,trxName);
			
			String CostingLevel = product.getCostingLevel(as, AD_Org_ID);
			String costingMethod = ct.getCostingMethod();

			if (MAcctSchema.COSTINGLEVEL_Client.equals(CostingLevel)) {
				//Ignore organization, warehouse , asi
				AD_Org_ID = 0;
				M_Warehouse_ID = 0;
				M_AttributeSetInstance_ID = 0;
			} 
			else if (MAcctSchema.COSTINGLEVEL_Organization.equals(CostingLevel))
			{
				if (AD_Org_ID <= 0)
					throw new AdempiereException("@AD_Org_ID@ @NotFound@");
				//Ignore  warehouse , asi
				M_Warehouse_ID = 0;
				M_AttributeSetInstance_ID = 0;
			}	
			else if (MAcctSchema.COSTINGLEVEL_Warehouse.equals(CostingLevel))
			{	
				//Ignore organization asi
				if (M_Warehouse_ID <= 0)
					throw new AdempiereException("@M_Warehouse_ID@ @NotFound@");
				M_AttributeSetInstance_ID = 0;
			}	
			else if (MAcctSchema.COSTINGLEVEL_BatchLot.equals(CostingLevel))
			{	
				//Ignore organization, warehouse
				AD_Org_ID = 0;
				M_Warehouse_ID = 0;
			}	
			// Costing Method
			if (costingMethod == null) {
				costingMethod = product.getCostingMethod(as, AD_Org_ID);
				if (costingMethod == null) {
					throw new IllegalArgumentException("No Costing Method");
				}
			}		
			
			// Get or Create MCost
			return MCost.getOrCreate(product, M_AttributeSetInstance_ID, as,
					AD_Org_ID, M_Warehouse_ID, ct.getM_CostType_ID(), ce.getM_CostElement_ID());
		}

    	/**
    	 * Get MCost (Cost Dimension) for this Product 
    	 * @param as Account Schema
    	 * @param model Document Line Model
    	 * @return Cost Dimension List for this product
    	 */
    	public static List<MCost> getForProduct(MAcctSchema as, IDocumentLine model)
    	{
    		int AD_Org_ID = 0;
    		int M_Warehouse_ID = 0;
    		int M_AttributeSetInstance_ID = 0;
    		String CostingLevel = MProduct.get(as.getCtx(), model.getM_Product_ID()).getCostingLevel(as,model.getAD_Org_ID());
    		if (MAcctSchema.COSTINGLEVEL_Client.equals(CostingLevel))
    		{
    			AD_Org_ID = 0;
    			M_AttributeSetInstance_ID = 0;
    			M_Warehouse_ID = 0;
    		}
    		else if (MAcctSchema.COSTINGLEVEL_Organization.equals(CostingLevel))
    		{	
    			AD_Org_ID = model.getAD_Org_ID();
    			M_Warehouse_ID = 0;
    			M_AttributeSetInstance_ID = 0;
    		}	
    		else if (MAcctSchema.COSTINGLEVEL_Warehouse.equals(CostingLevel))
    		{	
    			AD_Org_ID = model.getM_Locator_ID();
    			M_Warehouse_ID = MLocator.get(model.getCtx(), model.getM_Locator_ID()).getM_Warehouse_ID();
    			M_AttributeSetInstance_ID = 0;
    		}	
    		else if (MAcctSchema.COSTINGLEVEL_BatchLot.equals(CostingLevel))
    		{	
    			AD_Org_ID = model.getAD_Org_ID();
    			M_Warehouse_ID = 0;
    			M_AttributeSetInstance_ID = model.getM_AttributeSetInstance_ID();
    		}

            final String whereClause = "M_Product_ID=?  AND AD_Org_ID=? AND (M_Warehouse_ID=? or m_Warehouse_ID IS NULL) AND M_AttributeSetInstance_ID=?" ;
    		
    		return new Query(as.getCtx(), Table_Name, whereClause, model.get_TrxName())
    			.setParameters(model.getM_Product_ID(), AD_Org_ID, M_Warehouse_ID , M_AttributeSetInstance_ID)
    			.setClient_ID()
    			.setOrderBy("AD_Client_ID, AD_Org_ID, M_Warehouse_ID , M_AttributeSetInstance_ID")
    			.list();
       	}   

    	/**
    	 * get CostComponets
    	 * @param product
    	 * @param M_ASI_ID
    	 * @param as
    	 * @param Org_ID
    	 * @param M_CostType_ID
    	 * @param costingMethod
    	 * @param qty
    	 * @param C_OrderLine_ID
    	 * @param zeroCostsOK
    	 * @param trxName
    	 * @param cost
    	 * @return get List Cost Component
    	 */
    	private static List<CostComponent> getCurrentCostLayers (MProduct product, int M_ASI_ID, 
    			MAcctSchema as, int Org_ID,int M_Warehouse_ID, int M_CostType_ID,  
    			String costingMethod, BigDecimal qty, int C_OrderLine_ID, 
    			boolean zeroCostsOK, String trxName, MCost cost)
    	{
    		List<CostComponent> list = new ArrayList<CostComponent>();
    		BigDecimal currentCostPrice = null;
    		BigDecimal currentCostPriceLL = null;
    		String costElementType = null;
    		//int M_CostElement_ID = 0;
    		BigDecimal percent = null;
    		//
    		BigDecimal materialCostEach = Env.ZERO;
    		BigDecimal otherCostEach = Env.ZERO;
    		BigDecimal percentage = Env.ZERO;
    		int count = 0;
    		//
    		String sql = "SELECT SUM(c.CurrentCostPrice), ce.CostElementType, c.CostingMethod,"
    			+ " c.Percent, c.M_CostElement_ID , SUM(c.CurrentCostPriceLL) "					//	4..5
    			+ "FROM M_Cost c"
    			+ " LEFT OUTER JOIN M_CostElement ce ON (c.M_CostElement_ID=ce.M_CostElement_ID) "
    			+ "WHERE c.AD_Client_ID=? AND c.AD_Org_ID=?"		//	#1/2
    			+ " AND c.M_Product_ID=?"							//	#3
    			+ " AND (c.M_AttributeSetInstance_ID=? OR c.M_AttributeSetInstance_ID=0)"	//	#4
    			+ " AND c.M_CostType_ID=? AND c.C_AcctSchema_ID=?"	//	#5/6
//    			+ " AND (ce.CostingMethod IS NULL OR ce.CostingMethod=?) "	//	#7
    			+ " AND c.CostingMethod=? "	//	#7
    			+ "GROUP BY ce.CostElementType, c.CostingMethod, c.Percent, c.M_CostElement_ID";
    		PreparedStatement pstmt = null;
    		ResultSet rs = null;
    		try
    		{
    			pstmt = DB.prepareStatement (sql, trxName);
    			pstmt.setInt (1, product.getAD_Client_ID());
    			pstmt.setInt (2, Org_ID);
    			pstmt.setInt (3, product.getM_Product_ID());
    			pstmt.setInt (4, M_ASI_ID);
    			pstmt.setInt (5, M_CostType_ID);
    			pstmt.setInt (6, as.getC_AcctSchema_ID());
    			pstmt.setString (7, costingMethod);
    			rs = pstmt.executeQuery ();
    			while (rs.next ())
    			{
    				currentCostPrice = rs.getBigDecimal(1);
    				currentCostPriceLL = rs.getBigDecimal(6);
    				costElementType = rs.getString(2);
    				String cm = rs.getString(3);
    				percent = rs.getBigDecimal(4);
    				//	M_CostElement_ID = rs.getInt(5);
    				s_log.finest("CurrentCostPrice=" + currentCostPrice 
    						+ ", CostElementType=" + costElementType
    						+ ", CostingMethod=" + cm
    						+ ", Percent=" + percent);
    				//
    				if (currentCostPrice != null && currentCostPrice.signum() != 0)
    				{
    					if (cm != null)
    					{	
    						materialCostEach = materialCostEach.add(currentCostPrice).add(currentCostPriceLL);
    					}	
    					else
    						otherCostEach = otherCostEach.add(currentCostPrice).add(currentCostPriceLL);
    				}
    				if (percent != null && percent.signum() != 0)
    					percentage = percentage.add(percent);
    				count++;
    			}
    		}
    		catch (SQLException e)
    		{
    			throw new DBException(e, sql);
    		}
    		finally
    		{
    			DB.close(rs, pstmt);
    			rs = null; pstmt = null;
    		}

    		if (count > 1)	//	Print summary
    			s_log.finest("MaterialCost=" + materialCostEach 
    					+ ", OtherCosts=" + otherCostEach
    					+ ", Percentage=" + percentage);

    		//	Seed Initial Costs
    		if (materialCostEach.signum() == 0)		//	no costs
    		{
    			if (zeroCostsOK)
    				return new ArrayList<CostComponent>();
    			materialCostEach = getSeedCosts(product, M_ASI_ID,
    					as, Org_ID, M_Warehouse_ID , costingMethod, C_OrderLine_ID);
    		}
    		if (materialCostEach == null)
    			return null;
    		list.add(new CostComponent(qty, materialCostEach));
    		list.add(new CostComponent(qty, otherCostEach));

    		//	Standard costs - just Material Costs
    		if (MCostElement.COSTINGMETHOD_StandardCosting.equals(costingMethod))
    		{
    			return list;
    		}
    		if (MCostElement.COSTINGMETHOD_Fifo.equals(costingMethod)
    				|| MCostElement.COSTINGMETHOD_Lifo.equals(costingMethod))
    		{
    			list = MCostQueue.getCostLayers(cost, qty, null, trxName);
    		}
    		//
    		for (CostComponent cc : list)
    		{
    			cc.setScale(as.getCostingPrecision());
    			cc.setPercent(percentage);
    		}
    		return list;
    	}
    	
    	/**
    	 * get Cost Component
    	 * @param cost
    	 * @param qty
    	 * @param C_OrderLine_ID
    	 * @param zeroCostsOK
    	 * @param trxName
    	 * @return get list Cost Component
    	 */
    	public static List<CostComponent> getCurrentCostLayers (MCost cost, 
    			BigDecimal qty, int C_OrderLine_ID,
    			boolean zeroCostsOK, String trxName)
    	{
    		MProduct product = MProduct.get(cost.getCtx(), cost.getM_Product_ID());
    		MAcctSchema as = MAcctSchema.get(cost.getCtx(), cost.getC_AcctSchema_ID());
    		int AD_Org_ID = cost.getAD_Org_ID();
    		int M_Warehouse_ID = cost.getM_Warehouse_ID();
    		int M_AttributeSetInstance_ID = cost.getM_AttributeSetInstance_ID();
    		String costingMethod = cost.getCostingMethod(); 
    		
    		String CostingLevel = product.getCostingLevel(as,product.getAD_Org_ID());
    		if (MAcctSchema.COSTINGLEVEL_Client.equals(CostingLevel))
    		{
    			AD_Org_ID = 0;
    			M_AttributeSetInstance_ID = 0;
    		}
    		else if (MAcctSchema.COSTINGLEVEL_Organization.equals(CostingLevel))
    			M_AttributeSetInstance_ID = 0;
    		else if (MAcctSchema.COSTINGLEVEL_BatchLot.equals(CostingLevel))
    			AD_Org_ID = 0;
    		//	Costing Method
    		if (costingMethod == null)
    		{
    			costingMethod = product.getCostingMethod(as, AD_Org_ID);
    			if (costingMethod == null)
    			{
    				throw new IllegalArgumentException("No Costing Method");
    			}
    		}

    		//	Create/Update Costs
    	//	MCostDetail.processProduct (product, trxName);

    		return getCurrentCostLayers (
    				product, M_AttributeSetInstance_ID, 
    				as, AD_Org_ID, M_Warehouse_ID, as.getM_CostType_ID(), costingMethod, qty, 
    				C_OrderLine_ID, zeroCostsOK, trxName, cost);
    	}
    	
	/**
	 * 	Retrieve/Calculate Current Cost Price
	 *	@param product product
	 *	@param M_AttributeSetInstance_ID real asi
	 *	@param as accounting schema
	 *	@param AD_Org_ID real org
	 *	@param costingMethod AcctSchema.COSTINGMETHOD_*
	 *	@param qty qty
	 *	@param C_OrderLine_ID optional order line
	 *	@param zeroCostsOK zero/no costs are OK
	 *	@param trxName trx
	 *	@return current cost price or null
	 *	@deprecated
	 */
	/*public static BigDecimal getCurrentCost (MProduct product,
		int M_AttributeSetInstance_ID,
		MAcctSchema as, int AD_Org_ID, int M_Warehouse_ID, String costingMethod,
		BigDecimal qty, int C_OrderLine_ID,
		boolean zeroCostsOK, String trxName)
	{
		String CostingLevel = product.getCostingLevel(as);
		if (MAcctSchema.COSTINGLEVEL_Client.equals(CostingLevel))
		{
			AD_Org_ID = 0;
			M_AttributeSetInstance_ID = 0;
		}
		else if (MAcctSchema.COSTINGLEVEL_Organization.equals(CostingLevel))
			M_AttributeSetInstance_ID = 0;
		else if (MAcctSchema.COSTINGLEVEL_BatchLot.equals(CostingLevel))
			AD_Org_ID = 0;
		//	Costing Method
		if (costingMethod == null)
		{
			costingMethod = product.getCostingMethod(as);
			if (costingMethod == null)
			{
				throw new IllegalArgumentException("No Costing Method");
			}
		}

		//	Create/Update Costs
		MCostDetail.processProduct (product, trxName);

		return getCurrentCost (
			product, M_AttributeSetInstance_ID,
			as, AD_Org_ID, M_Warehouse_ID, as.getM_CostType_ID(), costingMethod, qty,
			C_OrderLine_ID, zeroCostsOK, trxName);
	}	//	getCurrentCost
	*/

	/**
	 * 	Get Current Cost Price for Costing Level
	 *	@param product product
	 *	@param M_ASI_ID costing level asi
	 *	@param Org_ID costing level org
	 *	@param M_CostType_ID cost type
	 *	@param as AcctSchema
	 *	@param costingMethod method
	 *	@param qty quantity
	 *	@param C_OrderLine_ID optional order line
	 *	@param zeroCostsOK zero/no costs are OK
	 *	@param trxName trx
	 *	@return cost price or null
	 *	@deprecated
	 */
	private static BigDecimal getCurrentCost (MProduct product, int M_ASI_ID,
		MAcctSchema as, int Org_ID, int M_Warehouse_ID,  int M_CostType_ID,
		String costingMethod, BigDecimal qty, int C_OrderLine_ID,
		boolean zeroCostsOK, String trxName)
	{
		String costElementType = null;
		BigDecimal percent = null;
		//
		BigDecimal materialCostEach = Env.ZERO;
		BigDecimal otherCostEach = Env.ZERO;
		BigDecimal percentage = Env.ZERO;
		int count = 0;
		//
		String sql = "SELECT"
			+ " COALESCE(SUM(c.CurrentCostPrice),0),"		// 1
			+ " ce.CostElementType, ce.CostingMethod,"		// 2,3
			+ " c.Percent, c.M_CostElement_ID ,"			// 4,5
			+ " COALESCE(SUM(c.CurrentCostPriceLL),0) "		// 6
			+ " FROM M_Cost c"
			+ " LEFT OUTER JOIN M_CostElement ce ON (c.M_CostElement_ID=ce.M_CostElement_ID) "
			+ "WHERE c.AD_Client_ID=? AND c.AD_Org_ID=?"		//	#1/2
			+ " AND c.M_Product_ID=?"							//	#3
			+ " AND (c.M_AttributeSetInstance_ID=? OR c.M_AttributeSetInstance_ID=0)"	//	#4
			+ " AND c.M_CostType_ID=? AND c.C_AcctSchema_ID=?"	//	#5/6
			+ " AND (ce.CostingMethod IS NULL OR ce.CostingMethod=?) "	//	#7
			+ "GROUP BY ce.CostElementType, ce.CostingMethod, c.Percent, c.M_CostElement_ID";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, trxName);
			pstmt.setInt (1, product.getAD_Client_ID());
			pstmt.setInt (2, Org_ID);
			pstmt.setInt (3, product.getM_Product_ID());
			pstmt.setInt (4, M_ASI_ID);
			pstmt.setInt (5, M_CostType_ID);
			pstmt.setInt (6, as.getC_AcctSchema_ID());
			pstmt.setString (7, costingMethod);
			rs = pstmt.executeQuery ();
			while (rs.next ())
			{
				BigDecimal currentCostPrice = rs.getBigDecimal(1);
				BigDecimal currentCostPriceLL = rs.getBigDecimal(6);
				costElementType = rs.getString(2);
				String cm = rs.getString(3);
				percent = rs.getBigDecimal(4);
				//M_CostElement_ID = rs.getInt(5);
				s_log.finest("CurrentCostPrice=" + currentCostPrice
					+ ", CurrentCostPriceLL=" + currentCostPriceLL
					+ ", CostElementType=" + costElementType
					+ ", CostingMethod=" + cm
					+ ", Percent=" + percent);
				//
				if (currentCostPrice.signum() != 0 || currentCostPriceLL.signum() != 0)
				{
					if (cm != null)
					{
						materialCostEach = materialCostEach.add(currentCostPrice).add(currentCostPriceLL);
					}
					else
					{
						otherCostEach = otherCostEach.add(currentCostPrice).add(currentCostPriceLL);
					}
				}
				if (percent != null && percent.signum() != 0)
					percentage = percentage.add(percent);
				count++;
			}
		}
		catch (SQLException e)
		{
			throw new DBException(e, sql);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}

		if (count > 1)	//	Print summary
			s_log.finest("MaterialCost=" + materialCostEach
				+ ", OtherCosts=" + otherCostEach
				+ ", Percentage=" + percentage);

		//	Seed Initial Costs
		if (materialCostEach.signum() == 0)		//	no costs
		{
			if (zeroCostsOK)
				return Env.ZERO;
			materialCostEach = getSeedCosts(product, M_ASI_ID,
				as, Org_ID, M_Warehouse_ID , costingMethod, C_OrderLine_ID);
		}
		if (materialCostEach == null)
			return null;

		//	Material Costs
		BigDecimal materialCost = materialCostEach.multiply(qty);
		//	Standard costs - just Material Costs
		if (MCostElement.COSTINGMETHOD_StandardCosting.equals(costingMethod))
		{
			s_log.finer("MaterialCosts = " + materialCost);
			return materialCost;
		}
		if (MCostElement.COSTINGMETHOD_Fifo.equals(costingMethod)
			|| MCostElement.COSTINGMETHOD_Lifo.equals(costingMethod))
		{
			MCostElement ce = MCostElement.getMaterialCostElement(product);
			materialCost = MCostQueue.getCosts(product, M_ASI_ID,
				as, Org_ID,M_Warehouse_ID , ce, qty, trxName);
		}

		//	Other Costs
		BigDecimal otherCost = otherCostEach.multiply(qty);

		//	Costs
		BigDecimal costs = otherCost.add(materialCost);
		if (costs.signum() == 0)
			return null;

		s_log.finer("Sum Costs = " + costs);
		int precision = as.getCostingPrecision();
		if (percentage.signum() == 0)	//	no percentages
		{
			if (costs.scale() > precision)
				costs = costs.setScale(precision, BigDecimal.ROUND_HALF_UP);
			return costs;
		}
		//
		BigDecimal percentCost = costs.multiply(percentage);
		percentCost = percentCost.divide(Env.ONEHUNDRED, precision, BigDecimal.ROUND_HALF_UP);
		costs = costs.add(percentCost);
		if (costs.scale() > precision)
			costs = costs.setScale(precision, BigDecimal.ROUND_HALF_UP);
		s_log.finer("Sum Costs = " + costs + " (Add=" + percentCost + ")");
		return costs;
	}	//	getCurrentCost

	/**
	 * 	Get Seed Costs
	 *	@param product product
	 *	@param M_ASI_ID costing level asi
	 *	@param as accounting schema
	 *	@param Org_ID costing level org
	 *	@param costingMethod costing method
	 *	@param C_OrderLine_ID optional order line
	 *	@return price or null
	 */
	public static BigDecimal getSeedCosts (MProduct product, int M_ASI_ID,
		MAcctSchema as, int Org_ID, int M_Warehouse_ID, String costingMethod, int C_OrderLine_ID)
	{
		BigDecimal retValue = null;
		//	Direct Data
		if (MCostElement.COSTINGMETHOD_AverageInvoice.equals(costingMethod))
			retValue = calculateAverageInv(product, M_ASI_ID, as, Org_ID);
		else if (MCostElement.COSTINGMETHOD_AveragePO.equals(costingMethod))
			retValue = calculateAveragePO(product, M_ASI_ID, as, Org_ID);
		else if (MCostElement.COSTINGMETHOD_Fifo.equals(costingMethod))
			retValue = calculateFiFo(product, M_ASI_ID, as, Org_ID);
		else if (MCostElement.COSTINGMETHOD_Lifo.equals(costingMethod))
			retValue = calculateLiFo(product, M_ASI_ID, as, Org_ID);
		else if (MCostElement.COSTINGMETHOD_LastInvoice.equals(costingMethod))
			retValue = getLastInvoicePrice(product, M_ASI_ID, Org_ID, as.getC_Currency_ID());
		else if (MCostElement.COSTINGMETHOD_LastPOPrice.equals(costingMethod))
		{
			if (C_OrderLine_ID != 0)
				retValue = getPOPrice(product, C_OrderLine_ID, as.getC_Currency_ID());
			if (retValue == null || retValue.signum() == 0)
				retValue = getLastPOPrice(product, M_ASI_ID, Org_ID, as.getC_Currency_ID());
		}
		else if (MCostElement.COSTINGMETHOD_StandardCosting.equals(costingMethod))
		{
			//	migrate old costs
			MProductCosting pc = MProductCosting.get(product.getCtx(), product.getM_Product_ID(),
				as.getC_AcctSchema_ID(), null);
			if (pc != null)
				retValue = pc.getCurrentCostPrice();
		}
		else if (MCostElement.COSTINGMETHOD_UserDefined.equals(costingMethod))
			;
		else
			throw new IllegalArgumentException("Unknown Costing Method = " + costingMethod);
		if (retValue != null && retValue.signum() != 0)
		{
			s_log.fine(product.getName() + ", CostingMethod=" + costingMethod + " - " + retValue);
			return retValue;
		}

		//	Look for exact Order Line
		if (C_OrderLine_ID != 0)
		{
			retValue = getPOPrice(product, C_OrderLine_ID, as.getC_Currency_ID());
			if (retValue != null && retValue.signum() != 0)
			{
				s_log.fine(product.getName() + ", PO - " + retValue);
				return retValue;
			}
		}

		//	Look for Standard Costs first
		if (!MCostElement.COSTINGMETHOD_StandardCosting.equals(costingMethod))
		{
			/*
			MCostElement ce = MCostElement.getMaterialCostElement(as, MCostElement.COSTINGMETHOD_StandardCosting);
			MCost cost = get(product, M_ASI_ID, as, Org_ID, ce.getM_CostElement_ID());
			if (cost != null && cost.getCurrentCostPrice().signum() != 0)
			{
				s_log.fine(product.getName() + ", Standard - " + cost);
				return cost.getCurrentCostPrice();
			}*/
			
			MCostElement ce = MCostElement.getByMaterialCostElementType(as);
			List<MCostType> costtypes = MCostType.get(product.getCtx(), product.get_TrxName()); 
			for (MCostType mc : costtypes)
			{
				MCost cost = getOrCreate(product, M_ASI_ID, as , Org_ID , M_Warehouse_ID , mc.getM_CostType_ID(), ce.getM_CostElement_ID());
				if (cost != null && cost.getCurrentCostPrice().signum() != 0)
				{
					s_log.fine(product.getName() + ", Standard - " + retValue);
					return cost.getCurrentCostPrice();
				}
			}
		}

		//	We do not have a price
		//	PO first
		if (MCostElement.COSTINGMETHOD_AveragePO.equals(costingMethod)
			|| MCostElement.COSTINGMETHOD_LastPOPrice.equals(costingMethod)
			|| MCostElement.COSTINGMETHOD_StandardCosting.equals(costingMethod))
		{
			//	try Last PO
			retValue = getLastPOPrice(product, M_ASI_ID, Org_ID, as.getC_Currency_ID());
			if (Org_ID != 0 && (retValue == null || retValue.signum() == 0))
				retValue = getLastPOPrice(product, M_ASI_ID, 0, as.getC_Currency_ID());
			if (retValue != null && retValue.signum() != 0)
			{
				s_log.fine(product.getName() + ", LastPO = " + retValue);
				return retValue;
			}
		}
		else	//	Inv first
		{
			//	try last Inv
			retValue = getLastInvoicePrice(product, M_ASI_ID, Org_ID, as.getC_Currency_ID());
			if (Org_ID != 0 && (retValue == null || retValue.signum() == 0))
				retValue = getLastInvoicePrice(product, M_ASI_ID, 0, as.getC_Currency_ID());
			if (retValue != null && retValue.signum() != 0)
			{
				s_log.fine(product.getName() + ", LastInv = " + retValue);
				return retValue;
			}
		}

		//	Still Nothing
		//	Inv second
		if (MCostElement.COSTINGMETHOD_AveragePO.equals(costingMethod)
			|| MCostElement.COSTINGMETHOD_LastPOPrice.equals(costingMethod)
			|| MCostElement.COSTINGMETHOD_StandardCosting.equals(costingMethod))
		{
			//	try last Inv
			retValue = getLastInvoicePrice(product, M_ASI_ID, Org_ID, as.getC_Currency_ID());
			if (Org_ID != 0 && (retValue == null || retValue.signum() == 0))
				retValue = getLastInvoicePrice(product, M_ASI_ID, 0, as.getC_Currency_ID());
			if (retValue != null && retValue.signum() != 0)
			{
				s_log.fine(product.getName() + ", LastInv = " + retValue);
				return retValue;
			}
		}
		else	//	PO second
		{
			//	try Last PO
			retValue = getLastPOPrice(product, M_ASI_ID, Org_ID, as.getC_Currency_ID());
			if (Org_ID != 0 && (retValue == null || retValue.signum() == 0))
				retValue = getLastPOPrice(product, M_ASI_ID, 0, as.getC_Currency_ID());
			if (retValue != null && retValue.signum() != 0)
			{
				s_log.fine(product.getName() + ", LastPO = " + retValue);
				return retValue;
			}
		}

		//	Still nothing try ProductPO
		MProductPO[] pos = MProductPO.getOfProduct(product.getCtx(), product.getM_Product_ID(), null);
		for (int i = 0; i < pos.length; i++)
		{
			BigDecimal price = pos[i].getPricePO();
			if (price == null || price.signum() == 0)
				price = pos[i].getPriceList();
			if (price != null && price.signum() != 0)
			{
				price = MConversionRate.convert(product.getCtx(), price,
					pos[i].getC_Currency_ID(), as.getC_Currency_ID(),
					as.getAD_Client_ID(), Org_ID);
				if (price != null && price.signum() != 0)
				{
					if (pos[i].getC_UOM_ID() != product.getC_UOM_ID())
					{
						price = MUOMConversion.convertProductTo (Env.getCtx(), product.getM_Product_ID(),
								pos[i].getC_UOM_ID(), price);
					}
				}
				if (price != null && price.signum() != 0)
				{
					retValue = price;
					s_log.fine(product.getName() + ", Product_PO = " + retValue);
					return retValue;
				}
			}
		}

		//	Still nothing try Purchase Price List
		//	....

		s_log.fine(product.getName() + " = " + retValue);
		return retValue;
	}	//	getSeedCosts


	/**
	 * 	Get Last Invoice Price in currency
	 *	@param product product
	 *	@param M_ASI_ID attribute set instance
	 *	@param AD_Org_ID org
	 *	@param C_Currency_ID accounting currency
	 *	@return last invoice price in currency
	 */
	public static BigDecimal getLastInvoicePrice (MProduct product,
		int M_ASI_ID, int AD_Org_ID, int C_Currency_ID)
	{
		BigDecimal retValue = null;
		String sql = "SELECT currencyConvert(il.PriceActual, i.C_Currency_ID, ?, i.DateAcct, i.C_ConversionType_ID, il.AD_Client_ID, il.AD_Org_ID) "
			// ,il.PriceActual, il.QtyInvoiced, i.DateInvoiced, il.Line
			+ "FROM C_InvoiceLine il "
			+ " INNER JOIN C_Invoice i ON (il.C_Invoice_ID=i.C_Invoice_ID) "
			+ "WHERE il.M_Product_ID=?"
			+ " AND i.IsSOTrx='N'";
		if (AD_Org_ID != 0)
			sql += " AND il.AD_Org_ID=?";
		else if (M_ASI_ID != 0)
			sql += " AND il.M_AttributeSetInstance_ID=?";
		sql += " ORDER BY i.DateInvoiced DESC, il.Line DESC";
		//
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement (sql, product.get_TrxName());
			pstmt.setInt (1, C_Currency_ID);
			pstmt.setInt (2, product.getM_Product_ID());
			if (AD_Org_ID != 0)
				pstmt.setInt (3, AD_Org_ID);
			else if (M_ASI_ID != 0)
				pstmt.setInt(3, M_ASI_ID);
			ResultSet rs = pstmt.executeQuery ();
			if (rs.next ())
				retValue = rs.getBigDecimal(1);
			rs.close ();
			pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			s_log.log (Level.SEVERE, sql, e);
		}
		try
		{
			if (pstmt != null)
				pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			pstmt = null;
		}

		if (retValue != null)
		{
			s_log.finer(product.getName() + " = " + retValue);
			return retValue;
		}
		return null;
	}	//	getLastInvoicePrice

	/**
	 * 	Get Last PO Price in currency
	 *	@param product product
	 *	@param M_ASI_ID attribute set instance
	 *	@param AD_Org_ID org
	 *	@param C_Currency_ID accounting currency
	 *	@return last PO price in currency or null
	 */
	public static BigDecimal getLastPOPrice (MProduct product,
		int M_ASI_ID, int AD_Org_ID, int C_Currency_ID)
	{
		BigDecimal retValue = null;
		String sql = "SELECT currencyConvert(ol.PriceCost, o.C_Currency_ID, ?, o.DateAcct, o.C_ConversionType_ID, ol.AD_Client_ID, ol.AD_Org_ID),"
			+ " currencyConvert(ol.PriceActual, o.C_Currency_ID, ?, o.DateAcct, o.C_ConversionType_ID, ol.AD_Client_ID, ol.AD_Org_ID) "
			//	,ol.PriceCost,ol.PriceActual, ol.QtyOrdered, o.DateOrdered, ol.Line
			+ "FROM C_OrderLine ol"
			+ " INNER JOIN C_Order o ON (ol.C_Order_ID=o.C_Order_ID) "
			+ "WHERE ol.M_Product_ID=?"
			+ " AND o.IsSOTrx='N'";
		if (AD_Org_ID != 0)
			sql += " AND ol.AD_Org_ID=?";
		else if (M_ASI_ID != 0)
			sql += " AND ol.M_AttributeSetInstance_ID=?";
		sql += " ORDER BY o.DateOrdered DESC, ol.Line DESC";
		//
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, product.get_TrxName());
			pstmt.setInt (1, C_Currency_ID);
			pstmt.setInt (2, C_Currency_ID);
			pstmt.setInt (3, product.getM_Product_ID());
			if (AD_Org_ID != 0)
				pstmt.setInt (4, AD_Org_ID);
			else if (M_ASI_ID != 0)
				pstmt.setInt(4, M_ASI_ID);
			rs = pstmt.executeQuery ();
			if (rs.next ())
			{
				retValue = rs.getBigDecimal(1);
				if (retValue == null || retValue.signum() == 0)
					retValue = rs.getBigDecimal(2);
			}
		}
		catch (SQLException e)
		{
			throw new DBException(e, sql);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}

		if (retValue != null)
		{
			s_log.finer(product.getName() + " = " + retValue);
			return retValue;
		}
		return null;
	}	//	getLastPOPrice

	/**
	 * 	Get PO Price in currency
	 * 	@param product product
	 *	@param C_OrderLine_ID order line
	 *	@param C_Currency_ID accounting currency
	 *	@return last PO price in currency or null
	 */
	public static BigDecimal getPOPrice (MProduct product, int C_OrderLine_ID, int C_Currency_ID)
	{
		BigDecimal retValue = null;
		String sql = "SELECT currencyConvert(ol.PriceCost, o.C_Currency_ID, ?, o.DateAcct, o.C_ConversionType_ID, ol.AD_Client_ID, ol.AD_Org_ID),"
			+ " currencyConvert(ol.PriceActual, o.C_Currency_ID, ?, o.DateAcct, o.C_ConversionType_ID, ol.AD_Client_ID, ol.AD_Org_ID) "
			//	,ol.PriceCost,ol.PriceActual, ol.QtyOrdered, o.DateOrdered, ol.Line
			+ "FROM C_OrderLine ol"
			+ " INNER JOIN C_Order o ON (ol.C_Order_ID=o.C_Order_ID) "
			+ "WHERE ol.C_OrderLine_ID=?"
			+ " AND o.IsSOTrx='N'";
		//
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, product.get_TrxName());
			pstmt.setInt (1, C_Currency_ID);
			pstmt.setInt (2, C_Currency_ID);
			pstmt.setInt (3, C_OrderLine_ID);
			rs = pstmt.executeQuery ();
			if (rs.next ())
			{
				retValue = rs.getBigDecimal(1);
				if (retValue == null || retValue.signum() == 0)
					retValue = rs.getBigDecimal(2);
			}
		}
		catch (Exception e)
		{
			s_log.log (Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}

		if (retValue != null)
		{
			s_log.finer(product.getName() + " = " + retValue);
			return retValue;
		}
		return null;
	}	//	getPOPrice

	/**************************************************************************
	 * 	Create costing for client.
	 * 	Handles Transaction if not in a transaction
	 *	@param client client
	 */
	@Deprecated
	/*public static void create (MClient client)
	{
		MAcctSchema[] ass = MAcctSchema.getClientAcctSchema(client.getCtx(), client.getAD_Client_ID());
		String trxName = client.get_TrxName();
		String trxNameUsed = trxName;
		Trx trx = null;
		if (trxName == null)
		{
			trxNameUsed = Trx.createTrxName("Cost");
			trx = Trx.get(trxNameUsed, true);
		}
		boolean success = true;
		//	For all Products
		String sql = "SELECT * FROM M_Product p "
			+ "WHERE AD_Client_ID=?"
			+ " AND EXISTS (SELECT * FROM M_CostDetail cd "
				+ "WHERE p.M_Product_ID=cd.M_Product_ID AND Processed='N')";
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement (sql, trxNameUsed);
			pstmt.setInt (1, client.getAD_Client_ID());
			ResultSet rs = pstmt.executeQuery ();
			while (rs.next ())
			{
				MProduct product = new MProduct (client.getCtx(), rs, trxNameUsed);
				for (int i = 0; i < ass.length; i++)
				{
					BigDecimal cost = getCurrentCost(product, 0, ass[i], 0, 0 ,
						null, Env.ONE, 0, false, trxNameUsed);		//	create non-zero costs
					s_log.info(product.getName() + " = " + cost);
				}
			}
			rs.close ();
			pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			s_log.log (Level.SEVERE, sql, e);
			success = false;
		}
		try
		{
			if (pstmt != null)
				pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			pstmt = null;
		}
		//	Transaction
		if (trx != null)
		{
			if (success)
				trx.commit();
			else
				trx.rollback();
			trx.close();
		}
	}	//	create
	*/


	/**
	 * 	Create standard Costing records for Product
	 *	@param product product
	 */
	protected static void create (MProduct product)
	{
			s_log.config(product.getName());

			//	Cost Elements
			List <MCostElement> ces = MCostElement.getDefaultElements(product);

			MAcctSchema[] mass = MAcctSchema.getClientAcctSchema(product.getCtx(),
				product.getAD_Client_ID(), product.get_TrxName());
			MOrg[] orgs = null;

			int M_ASI_ID = 0;		//	No Attribute
			for (MAcctSchema as : mass)
			{
				String cl = product.getCostingLevel(as);
				//	Create Std Costing
				if (MAcctSchema.COSTINGLEVEL_Client.equals(cl))
				{
					for (MCostType ct : MCostType.get(product.getCtx(), product.get_TrxName()))
					{	
						for(MCostElement ce : ces)
						{
							MCost cost = MCost.getOrCreate(product, M_ASI_ID,
								as, 0 , 0 , ct.getM_CostType_ID() ,ce.getM_CostElement_ID());
							if (cost.is_new())
							{
								if (cost.save())
									s_log.config("Std.Cost for " + product.getName()
										+ " - " + as.getName());
								else
									s_log.warning("Not created: Std.Cost for " + product.getName()
										+ " - " + as.getName());
							}
						}
					}	
				}
				else if (MAcctSchema.COSTINGLEVEL_Organization.equals(cl))
				{
					if (orgs == null)
						orgs = MOrg.getOfClient(product);
					for (MOrg o : orgs)
					{
						for (MCostType ct : MCostType.get(product.getCtx(), product.get_TrxName()))
						{
							for(MCostElement ce : ces)
							{
								MCost cost = MCost.getOrCreate(product, M_ASI_ID,
									as, o.getAD_Org_ID(), 0 , ct.getM_CostType_ID() , ce.getM_CostElement_ID());
								if (cost.is_new())
								{
									if (cost.save())
										s_log.config("Std.Cost for " + product.getName()
											+ " - " + o.getName()
											+ " - " + as.getName());
									else
										s_log.warning("Not created: Std.Cost for " + product.getName()
											+ " - " + o.getName()
											+ " - " + as.getName());
								}
							}
						}	
					}	//	for all orgs
				}
				else
				{
					s_log.warning("Not created: Std.Cost for " + product.getName()
						+ " - Costing Level on Batch/Lot");
				}
			}	//	accounting schema loop
	}	//	create

	/**
	 * 	Delete standard Costing records for Product
	 *	@param product product
	 **/
	protected static void delete (MProduct product)
	{
		s_log.config(product.getName());
		//	Cost Elements
		List <MCostElement> ces = MCostElement.getCostElement(product.getCtx(), product.get_TrxName());

			MAcctSchema[] mass = MAcctSchema.getClientAcctSchema(product.getCtx(),
				product.getAD_Client_ID(), product.get_TrxName());
			MOrg[] orgs = null;

			int M_ASI_ID = 0;		//	No Attribute
			for (MAcctSchema as : mass)
			{
				String cl = product.getCostingLevel(as);
				//	Create Std Costing
				if (MAcctSchema.COSTINGLEVEL_Client.equals(cl))
				{
					for (MCostType ct : MCostType.get(product.getCtx(), product.get_TrxName()))
					{
						for(MCostElement ce : ces)
						{
							MCost cost = MCost.getOrCreate(product, M_ASI_ID,
								as, 0, 0 , ct.getM_CostType_ID() , ce.getM_CostElement_ID());
							if(cost != null)
							cost.deleteEx(true);
						}
					}	
				}
				else if (MAcctSchema.COSTINGLEVEL_Organization.equals(cl))
				{
					if (orgs == null)
						orgs = MOrg.getOfClient(product);
					for (MOrg o : orgs)
					{
						for (MCostType ct : MCostType.get(product.getCtx(), product.get_TrxName()))
						{
							for(MCostElement ce : ces)
							{
								MCost cost = MCost.getOrCreate (product, M_ASI_ID,
									as, o.getAD_Org_ID(), 0 , ct.getM_CostType_ID() , ce.getM_CostElement_ID());
								if(cost != null)
									cost.deleteEx(true);
							}
						}	
					}	//	for all orgs
				}
				else
				{
					s_log.warning("Not created: Std.Cost for " + product.getName()
						+ " - Costing Level on Batch/Lot");
				}
			}	//	accounting schema loop
	}	//	create

	/**************************************************************************
	 * 	Calculate Average Invoice from Trx
	 *	@param product product
	 *	@param M_AttributeSetInstance_ID optional asi
	 *	@param as acct schema
	 *	@param AD_Org_ID optonal org
	 *	@return average costs or null
	 */
	public static BigDecimal calculateAverageInv (MProduct product, int M_AttributeSetInstance_ID,
		MAcctSchema as, int AD_Org_ID)
	{
		String sql = "SELECT t.MovementQty, mi.Qty, il.QtyInvoiced, il.PriceActual,"
			+ " i.C_Currency_ID, i.DateAcct, i.C_ConversionType_ID, i.AD_Client_ID, i.AD_Org_ID, t.M_Transaction_ID "
			+ "FROM M_Transaction t"
			+ " INNER JOIN M_MatchInv mi ON (t.M_InOutLine_ID=mi.M_InOutLine_ID)"
			+ " INNER JOIN C_InvoiceLine il ON (mi.C_InvoiceLine_ID=il.C_InvoiceLine_ID)"
			+ " INNER JOIN C_Invoice i ON (il.C_Invoice_ID=i.C_Invoice_ID) "
			+ "WHERE t.M_Product_ID=?";
		if (AD_Org_ID != 0)
			sql += " AND t.AD_Org_ID=?";
		else if (M_AttributeSetInstance_ID != 0)
			sql += " AND t.M_AttributeSetInstance_ID=?";
		sql += " ORDER BY t.M_Transaction_ID";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BigDecimal newStockQty = Env.ZERO;
		//
		BigDecimal newAverageAmt = Env.ZERO;
		int oldTransaction_ID = 0;
		try
		{
			pstmt = DB.prepareStatement (sql, null);
			pstmt.setInt (1, product.getM_Product_ID());
			if (AD_Org_ID != 0)
				pstmt.setInt (2, AD_Org_ID);
			else if (M_AttributeSetInstance_ID != 0)
				pstmt.setInt (2, M_AttributeSetInstance_ID);
			rs = pstmt.executeQuery ();
			while (rs.next ())
			{
				BigDecimal oldStockQty = newStockQty;
				BigDecimal movementQty = rs.getBigDecimal(1);
				int M_Transaction_ID = rs.getInt(10);
				if (M_Transaction_ID != oldTransaction_ID)
					newStockQty = oldStockQty.add(movementQty);
				M_Transaction_ID = oldTransaction_ID;
				//
				BigDecimal matchQty = rs.getBigDecimal(2);
				if (matchQty == null)
				{
					s_log.finer("Movement=" + movementQty + ", StockQty=" + newStockQty);
					continue;
				}
				//	Assumption: everything is matched
				BigDecimal price = rs.getBigDecimal(4);
				int C_Currency_ID = rs.getInt(5);
				Timestamp DateAcct = rs.getTimestamp(6);
				int C_ConversionType_ID = rs.getInt(7);
				int Client_ID = rs.getInt(8);
				int Org_ID = rs.getInt(9);
				BigDecimal cost = MConversionRate.convert(product.getCtx(), price,
					C_Currency_ID, as.getC_Currency_ID(),
					DateAcct, C_ConversionType_ID, Client_ID, Org_ID);
				//
				BigDecimal oldAverageAmt = newAverageAmt;
				BigDecimal averageCurrent = oldStockQty.multiply(oldAverageAmt);
				BigDecimal averageIncrease = matchQty.multiply(cost);
				BigDecimal newAmt = averageCurrent.add(averageIncrease);
				newAmt = newAmt.setScale(as.getCostingPrecision(), BigDecimal.ROUND_HALF_UP);
				newAverageAmt = newAmt.divide(newStockQty, as.getCostingPrecision(), BigDecimal.ROUND_HALF_UP);
				s_log.finer("Movement=" + movementQty + ", StockQty=" + newStockQty
					+ ", Match=" + matchQty + ", Cost=" + cost + ", NewAvg=" + newAverageAmt);
			}
		}
		catch (SQLException e)
		{
			throw new DBException(e, sql);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		//
		if (newAverageAmt != null && newAverageAmt.signum() != 0)
		{
			s_log.finer(product.getName() + " = " + newAverageAmt);
			return newAverageAmt;
		}
		return null;
	}	//	calculateAverageInv

	/**
	 * 	Calculate Average PO
	 *	@param product product
	 *	@param M_AttributeSetInstance_ID asi
	 *	@param as acct schema
	 *	@param AD_Org_ID org
	 *	@return costs or null
	 */
	public static BigDecimal calculateAveragePO (MProduct product, int M_AttributeSetInstance_ID,
		MAcctSchema as, int AD_Org_ID)
	{
		String sql = "SELECT t.MovementQty, mp.Qty, ol.QtyOrdered, ol.PriceCost, ol.PriceActual,"	//	1..5
			+ " o.C_Currency_ID, o.DateAcct, o.C_ConversionType_ID,"	//	6..8
			+ " o.AD_Client_ID, o.AD_Org_ID, t.M_Transaction_ID "		//	9..11
			+ "FROM M_Transaction t"
			+ " INNER JOIN M_MatchPO mp ON (t.M_InOutLine_ID=mp.M_InOutLine_ID)"
			+ " INNER JOIN C_OrderLine ol ON (mp.C_OrderLine_ID=ol.C_OrderLine_ID)"
			+ " INNER JOIN C_Order o ON (ol.C_Order_ID=o.C_Order_ID) "
			+ "WHERE t.M_Product_ID=?";
		if (AD_Org_ID != 0)
			sql += " AND t.AD_Org_ID=?";
		else if (M_AttributeSetInstance_ID != 0)
			sql += " AND t.M_AttributeSetInstance_ID=?";
		sql += " ORDER BY t.M_Transaction_ID";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BigDecimal newStockQty = Env.ZERO;
		//
		BigDecimal newAverageAmt = Env.ZERO;
		int oldTransaction_ID = 0;
		try
		{
			pstmt = DB.prepareStatement (sql, null);
			pstmt.setInt (1, product.getM_Product_ID());
			if (AD_Org_ID != 0)
				pstmt.setInt (2, AD_Org_ID);
			else if (M_AttributeSetInstance_ID != 0)
				pstmt.setInt (2, M_AttributeSetInstance_ID);
			rs = pstmt.executeQuery ();
			while (rs.next ())
			{
				BigDecimal oldStockQty = newStockQty;
				BigDecimal movementQty = rs.getBigDecimal(1);
				int M_Transaction_ID = rs.getInt(11);
				if (M_Transaction_ID != oldTransaction_ID)
					newStockQty = oldStockQty.add(movementQty);
				M_Transaction_ID = oldTransaction_ID;
				//
				BigDecimal matchQty = rs.getBigDecimal(2);
				if (matchQty == null)
				{
					s_log.finer("Movement=" + movementQty + ", StockQty=" + newStockQty);
					continue;
				}
				//	Assumption: everything is matched
				BigDecimal price = rs.getBigDecimal(4);
				if (price == null || price.signum() == 0)	//	PO Cost
					price = rs.getBigDecimal(5);			//	Actual
				int C_Currency_ID = rs.getInt(6);
				Timestamp DateAcct = rs.getTimestamp(7);
				int C_ConversionType_ID = rs.getInt(8);
				int Client_ID = rs.getInt(9);
				int Org_ID = rs.getInt(10);
				BigDecimal cost = MConversionRate.convert(product.getCtx(), price,
					C_Currency_ID, as.getC_Currency_ID(),
					DateAcct, C_ConversionType_ID, Client_ID, Org_ID);
				//
				BigDecimal oldAverageAmt = newAverageAmt;
				BigDecimal averageCurrent = oldStockQty.multiply(oldAverageAmt);
				BigDecimal averageIncrease = matchQty.multiply(cost);
				BigDecimal newAmt = averageCurrent.add(averageIncrease);
				newAmt = newAmt.setScale(as.getCostingPrecision(), BigDecimal.ROUND_HALF_UP);
				newAverageAmt = newAmt.divide(newStockQty, as.getCostingPrecision(), BigDecimal.ROUND_HALF_UP);
				s_log.finer("Movement=" + movementQty + ", StockQty=" + newStockQty
					+ ", Match=" + matchQty + ", Cost=" + cost + ", NewAvg=" + newAverageAmt);
			}
		}
		catch (SQLException e)
		{
			throw new DBException(e, sql);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		//
		if (newAverageAmt != null && newAverageAmt.signum() != 0)
		{
			s_log.finer(product.getName() + " = " + newAverageAmt);
			return newAverageAmt;
		}
		return null;
	}	//	calculateAveragePO

	/**
	 * 	Calculate FiFo Cost
	 *	@param product product
	 *	@param M_AttributeSetInstance_ID asi
	 *	@param as acct schema
	 *	@param AD_Org_ID org
	 *	@return costs or null
	 */
	public static BigDecimal calculateFiFo (MProduct product, int M_AttributeSetInstance_ID,
		MAcctSchema as, int AD_Org_ID)
	{
		String sql = "SELECT t.MovementQty, mi.Qty, il.QtyInvoiced, il.PriceActual,"
			+ " i.C_Currency_ID, i.DateAcct, i.C_ConversionType_ID, i.AD_Client_ID, i.AD_Org_ID, t.M_Transaction_ID "
			+ "FROM M_Transaction t"
			+ " INNER JOIN M_MatchInv mi ON (t.M_InOutLine_ID=mi.M_InOutLine_ID)"
			+ " INNER JOIN C_InvoiceLine il ON (mi.C_InvoiceLine_ID=il.C_InvoiceLine_ID)"
			+ " INNER JOIN C_Invoice i ON (il.C_Invoice_ID=i.C_Invoice_ID) "
			+ "WHERE t.M_Product_ID=?";
		if (AD_Org_ID != 0)
			sql += " AND t.AD_Org_ID=?";
		else if (M_AttributeSetInstance_ID != 0)
			sql += " AND t.M_AttributeSetInstance_ID=?";
		sql += " ORDER BY t.M_Transaction_ID";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//
		int oldTransaction_ID = 0;
		ArrayList<QtyCost> fifo = new ArrayList<QtyCost>();
		try
		{
			pstmt = DB.prepareStatement (sql, null);
			pstmt.setInt (1, product.getM_Product_ID());
			if (AD_Org_ID != 0)
				pstmt.setInt (2, AD_Org_ID);
			else if (M_AttributeSetInstance_ID != 0)
				pstmt.setInt (2, M_AttributeSetInstance_ID);
			rs = pstmt.executeQuery ();
			while (rs.next ())
			{
				BigDecimal movementQty = rs.getBigDecimal(1);
				int M_Transaction_ID = rs.getInt(10);
				if (M_Transaction_ID == oldTransaction_ID)
					continue;	//	assuming same price for receipt
				M_Transaction_ID = oldTransaction_ID;
				//
				BigDecimal matchQty = rs.getBigDecimal(2);
				if (matchQty == null)	//	out (negative)
				{
					if (fifo.size() > 0)
					{
						QtyCost pp = (QtyCost)fifo.get(0);
						pp.Qty = pp.Qty.add(movementQty);
						BigDecimal remainder = pp.Qty;
						if (remainder.signum() == 0)
							fifo.remove(0);
						else
						{
							while (remainder.signum() != 0)
							{
								if (fifo.size() == 1)	//	Last
								{
									pp.Cost = Env.ZERO;
									remainder = Env.ZERO;
								}
								else
								{
									fifo.remove(0);
									pp = (QtyCost)fifo.get(0);
									pp.Qty = pp.Qty.add(movementQty);
									remainder = pp.Qty;
								}
							}
						}
					}
					else
					{
						QtyCost pp = new QtyCost (movementQty, Env.ZERO);
						fifo.add(pp);
					}
					s_log.finer("Movement=" + movementQty + ", Size=" + fifo.size());
					continue;
				}
				//	Assumption: everything is matched
				BigDecimal price = rs.getBigDecimal(4);
				int C_Currency_ID = rs.getInt(5);
				Timestamp DateAcct = rs.getTimestamp(6);
				int C_ConversionType_ID = rs.getInt(7);
				int Client_ID = rs.getInt(8);
				int Org_ID = rs.getInt(9);
				BigDecimal cost = MConversionRate.convert(product.getCtx(), price,
					C_Currency_ID, as.getC_Currency_ID(),
					DateAcct, C_ConversionType_ID, Client_ID, Org_ID);

				//	Add Stock
				boolean used = false;
				if (fifo.size() == 1)
				{
					QtyCost pp = (QtyCost)fifo.get(0);
					if (pp.Qty.signum() < 0)
					{
						pp.Qty = pp.Qty.add(movementQty);
						if (pp.Qty.signum() == 0)
							fifo.remove(0);
						else
							pp.Cost = cost;
						used = true;
					}

				}
				if (!used)
				{
					QtyCost pp = new QtyCost (movementQty, cost);
					fifo.add(pp);
				}
				s_log.finer("Movement=" + movementQty + ", Size=" + fifo.size());
			}
		}
		catch (SQLException e)
		{
			throw new DBException(e, sql);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}

		if (fifo.size() == 0)
		{
			return null;
		}
		QtyCost pp = (QtyCost)fifo.get(0);
		s_log.finer(product.getName() + " = " + pp.Cost);
		return pp.Cost;
	}	//	calculateFiFo

	/**
	 * 	Calculate LiFo costs
	 *	@param product product
	 *	@param M_AttributeSetInstance_ID asi
	 *	@param as acct schema
	 *	@param AD_Org_ID org
	 *	@return costs or null
	 */
	public static BigDecimal calculateLiFo (MProduct product, int M_AttributeSetInstance_ID,
		MAcctSchema as, int AD_Org_ID)
	{
		String sql = "SELECT t.MovementQty, mi.Qty, il.QtyInvoiced, il.PriceActual,"
			+ " i.C_Currency_ID, i.DateAcct, i.C_ConversionType_ID, i.AD_Client_ID, i.AD_Org_ID, t.M_Transaction_ID "
			+ "FROM M_Transaction t"
			+ " INNER JOIN M_MatchInv mi ON (t.M_InOutLine_ID=mi.M_InOutLine_ID)"
			+ " INNER JOIN C_InvoiceLine il ON (mi.C_InvoiceLine_ID=il.C_InvoiceLine_ID)"
			+ " INNER JOIN C_Invoice i ON (il.C_Invoice_ID=i.C_Invoice_ID) "
			+ "WHERE t.M_Product_ID=?";
		if (AD_Org_ID != 0)
			sql += " AND t.AD_Org_ID=?";
		else if (M_AttributeSetInstance_ID != 0)
			sql += " AND t.M_AttributeSetInstance_ID=?";
		//	Starting point?
		sql += " ORDER BY t.M_Transaction_ID DESC";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//
		int oldTransaction_ID = 0;
		ArrayList<QtyCost> lifo = new ArrayList<QtyCost>();
		try
		{
			pstmt = DB.prepareStatement (sql, null);
			pstmt.setInt (1, product.getM_Product_ID());
			if (AD_Org_ID != 0)
				pstmt.setInt (2, AD_Org_ID);
			else if (M_AttributeSetInstance_ID != 0)
				pstmt.setInt (2, M_AttributeSetInstance_ID);
			rs = pstmt.executeQuery ();
			while (rs.next ())
			{
				BigDecimal movementQty = rs.getBigDecimal(1);
				int M_Transaction_ID = rs.getInt(10);
				if (M_Transaction_ID == oldTransaction_ID)
					continue;	//	assuming same price for receipt
				M_Transaction_ID = oldTransaction_ID;
				//
				BigDecimal matchQty = rs.getBigDecimal(2);
				if (matchQty == null)	//	out (negative)
				{
					if (lifo.size() > 0)
					{
						QtyCost pp = (QtyCost)lifo.get(lifo.size()-1);
						pp.Qty = pp.Qty.add(movementQty);
						BigDecimal remainder = pp.Qty;
						if (remainder.signum() == 0)
							lifo.remove(lifo.size()-1);
						else
						{
							while (remainder.signum() != 0)
							{
								if (lifo.size() == 1)	//	Last
								{
									pp.Cost = Env.ZERO;
									remainder = Env.ZERO;
								}
								else
								{
									lifo.remove(lifo.size()-1);
									pp = (QtyCost)lifo.get(lifo.size()-1);
									pp.Qty = pp.Qty.add(movementQty);
									remainder = pp.Qty;
								}
							}
						}
					}
					else
					{
						QtyCost pp = new QtyCost (movementQty, Env.ZERO);
						lifo.add(pp);
					}
					s_log.finer("Movement=" + movementQty + ", Size=" + lifo.size());
					continue;
				}
				//	Assumption: everything is matched
				BigDecimal price = rs.getBigDecimal(4);
				int C_Currency_ID = rs.getInt(5);
				Timestamp DateAcct = rs.getTimestamp(6);
				int C_ConversionType_ID = rs.getInt(7);
				int Client_ID = rs.getInt(8);
				int Org_ID = rs.getInt(9);
				BigDecimal cost = MConversionRate.convert(product.getCtx(), price,
					C_Currency_ID, as.getC_Currency_ID(),
					DateAcct, C_ConversionType_ID, Client_ID, Org_ID);
				//
				QtyCost pp = new QtyCost (movementQty, cost);
				lifo.add(pp);
				s_log.finer("Movement=" + movementQty + ", Size=" + lifo.size());
			}
		}
		catch (SQLException e)
		{
			throw new DBException(e, sql);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}

		if (lifo.size() == 0)
		{
			return null;
		}
		QtyCost pp = (QtyCost)lifo.get(lifo.size()-1);
		s_log.finer(product.getName() + " = " + pp.Cost);
		return pp.Cost;
	}	//	calculateLiFo


	/**************************************************************************
	 *	MCost Qty-Cost Pair
	 */
	public static class QtyCost
	{
		/**
		 * 	Constructor
		 *	@param qty qty
		 *	@param cost cost
		 */
		public QtyCost (BigDecimal qty, BigDecimal cost)
		{
			Qty = qty;
			Cost = cost;
		}
		/** Qty		*/
		public BigDecimal	Qty = null;
		/** Cost	*/
		public BigDecimal	Cost = null;

		/**
		 * 	String Representation
		 *	@return info
		 */
		public String toString ()
		{
			StringBuffer sb = new StringBuffer ("Qty=").append(Qty)
				.append (",Cost=").append (Cost);
			return sb.toString ();
		}	//	toString
	}	//	QtyCost
	
	/**
	 * 	Get/Create Cost Record.
	 * 	CostingLevel is not validated
	 *	@param product product
	 *	@param M_AttributeSetInstance_ID costing level asi
	 *	@param as accounting schema
	 *	@param AD_Org_ID costing level org
	 *	@param M_CostElement_ID element
	 *	@return cost price or null
	 *	@deprecated
	 */
	public static MCost get (MProduct product, int M_AttributeSetInstance_ID,
		MAcctSchema as, int AD_Org_ID, int M_Warehouse_ID, int M_CostElement_ID, String trxName)
	{
		return MCost.getOrCreate(product, M_AttributeSetInstance_ID, as, AD_Org_ID, M_Warehouse_ID,  as.getM_CostType_ID(), M_CostElement_ID);
	}
	/**
	 * 	Get/Create Cost Record.
	 * 	CostingLevel is not validated
	 *	@param product product
	 *	@param M_AttributeSetInstance_ID costing level asi
	 *	@param as accounting schema
	 *	@param AD_Org_ID costing level org
	 *	@param M_CostType_ID Cost Type
	 *	@param M_CostElement_ID element
	 *	@return cost price
	 */
	public static MCost getOrCreate (MProduct product, int M_AttributeSetInstance_ID,
		MAcctSchema as , int AD_Org_ID, int M_Warehouse_ID,  int M_CostType_ID , int M_CostElement_ID)
	{
		MCost cost = MCost.getDimension(product, as.getC_AcctSchema_ID(), AD_Org_ID, M_Warehouse_ID, M_AttributeSetInstance_ID, M_CostType_ID, M_CostElement_ID);
		if (cost == null)
		{
			cost = new MCost (product, M_AttributeSetInstance_ID,
					as.getC_AcctSchema_ID(), AD_Org_ID, M_Warehouse_ID, M_CostType_ID, M_CostElement_ID,  product.get_TrxName());
			cost.saveEx();
			s_log.fine("No cost records found.  Creating cost: " + cost.toString());
		}
		else
		{
			s_log.fine("Cost exists: " + cost.toString());
		}
		return cost;	

	}	//	get

	/**	Logger	*/
	private static CLogger 	s_log = CLogger.getCLogger (MCost.class);


	/**************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param ignored multi-key
	 *	@param trxName trx
	 */
	public MCost (Properties ctx, int ignored, String trxName)
	{
		super (ctx, ignored, trxName);
		if (ignored == 0)
		{
		//	setC_AcctSchema_ID (0);
		//	setM_CostElement_ID (0);
		//	setM_CostType_ID (0);
		//	setM_Product_ID (0);
			setM_AttributeSetInstance_ID(0);
			//
			setCurrentCostPrice (Env.ZERO);
			setFutureCostPrice (Env.ZERO);
			setCurrentQty (Env.ZERO);
			setCumulatedAmt (Env.ZERO);
			setCumulatedQty (Env.ZERO);
		}
		else
			throw new IllegalArgumentException("Multi-Key");
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
		m_manual = false;
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
		this (product.getCtx(), 0, product.get_TrxName());
		setClientOrg(product.getAD_Client_ID(), AD_Org_ID);
		setC_AcctSchema_ID(as.getC_AcctSchema_ID());
		setM_CostType_ID(as.getM_CostType_ID());
		setM_Product_ID(product.getM_Product_ID());
		setM_AttributeSetInstance_ID(M_AttributeSetInstance_ID);
		setM_CostElement_ID(M_CostElement_ID);
		//
		m_manual = false;
	}	//	MCost
	
	/**
	 * 	Parent Constructor
	 *	@param product Product
	 * @param M_AttributeSetInstance_ID asi
	 * @param as Acct Schema
	 * @param AD_Org_ID org
	 * @param M_Warehouse_ID warehouse
	 * @param M_CostType_ID TODO
	 * @param M_CostElement_ID cost element
	 */
	public MCost (MProduct product, int M_AttributeSetInstance_ID,
		int C_AcctSchema_ID, int AD_Org_ID, int M_Warehouse_ID ,int M_CostType_ID, int M_CostElement_ID, String trxName)
	{
		this (product.getCtx(), 0, trxName);
		setClientOrg(product.getAD_Client_ID(), AD_Org_ID);
		setM_Warehouse_ID(M_Warehouse_ID);
		setC_AcctSchema_ID(C_AcctSchema_ID);
		setM_CostType_ID(M_CostType_ID);
		setM_Product_ID(product.getM_Product_ID());
		setM_AttributeSetInstance_ID(M_AttributeSetInstance_ID);
		setM_CostElement_ID(M_CostElement_ID);
		//
		m_manual = false;
	}	//	MCost

	/** Data is entered Manually		*/
	private boolean m_manual = true;

	/**
	 * 	Add Cumulative Amt/Qty and Current Qty
	 *	@param amt amt
	 *	@param qty qty
	 */
	public void add (BigDecimal amt, BigDecimal qty)
	{
		setCumulatedAmt(getCumulatedAmt().add(amt));
		setCumulatedQty(getCumulatedQty().add(qty));
		setCurrentQty(getCurrentQty().add(qty));
	}	//	add

	/**
	 * 	Add Amt/Qty and calculate weighted average.
	 * 	((OldAvg*OldQty)+(Price*Qty)) / (OldQty+Qty)
	 *	@param amt total amt (price * qty)
	 *	@param qty qty
	 */
	public void setWeightedAverage (BigDecimal amt, BigDecimal qty)
	{
		BigDecimal oldSum = getCurrentCostPrice().multiply(getCurrentQty());
		BigDecimal newSum = amt;	//	is total already
		BigDecimal sumAmt = oldSum.add(newSum);
		BigDecimal sumQty = getCurrentQty().add(qty);
		if (sumQty.signum() != 0)
		{
			BigDecimal cost = sumAmt.divide(sumQty, getPrecision(), BigDecimal.ROUND_HALF_UP);
			setCurrentCostPrice(cost);
		}
		//
		setCumulatedAmt(getCumulatedAmt().add(amt));
		setCumulatedQty(getCumulatedQty().add(qty));
		setCurrentQty(getCurrentQty().add(qty));
	}	//	setWeightedAverage

	/**
	 * 	Get Costing Precision
	 *	@return precision (6)
	 */
	private int getPrecision()
	{
		MAcctSchema as = MAcctSchema.get(getCtx(), getC_AcctSchema_ID());
		if (as != null)
			return as.getCostingPrecision();
		return 6;
	}	//	gerPrecision

	/**
	 * 	Set Current Cost Price
	 *	@param currentCostPrice if null set to 0
	 */
	public void setCurrentCostPrice (BigDecimal currentCostPrice)
	{
		if (currentCostPrice != null)
			super.setCurrentCostPrice (currentCostPrice);
		else
			super.setCurrentCostPrice (Env.ZERO);
	}	//	setCurrentCostPrice

	/**
	 * 	Get History Average (Amt/Qty)
	 *	@return average if amt/aty <> 0 otherwise null
	 */
	public BigDecimal getHistoryAverage()
	{
		BigDecimal retValue = null;
		if (getCumulatedQty().signum() != 0
			&& getCumulatedAmt().signum() != 0)
			retValue = getCumulatedAmt()
				.divide(getCumulatedQty(), getPrecision(), BigDecimal.ROUND_HALF_UP);
		return retValue;
	}	//	getHistoryAverage

	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("MCost[");
		sb.append ("AD_Client_ID=").append (getAD_Client_ID());
		if (getAD_Org_ID() != 0)
			sb.append (",AD_Org_ID=").append (getAD_Org_ID());
		if (getM_Warehouse_ID() != 0)
			sb.append (",M_Warehouse_ID=").append (getM_Warehouse_ID());
		sb.append (",M_Product_ID=").append (getM_Product_ID());
		if (getM_AttributeSetInstance_ID() != 0)
			sb.append (",AD_ASI_ID=").append (getM_AttributeSetInstance_ID());
	//	sb.append (",C_AcctSchema_ID=").append (getC_AcctSchema_ID());
	//	sb.append (",M_CostType_ID=").append (getM_CostType_ID());
		sb.append (",M_CostElement_ID=").append (getM_CostElement_ID());
		//
		sb.append (", CurrentCost=").append (getCurrentCostPrice())
			.append (", C.Amt=").append (getCumulatedAmt())
			.append (",C.Qty=").append (getCumulatedQty())
			.append ("]");
		return sb.toString ();
	}	//	toString

	/**
	 * 	Get Cost Element
	 *	@return cost element
	 */
	public MCostElement getCostElement()
	{
		int M_CostElement_ID = getM_CostElement_ID();
		if (M_CostElement_ID == 0)
			return null;
		return MCostElement.get(getCtx(), M_CostElement_ID);
	}	//	getCostElement

	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true if can be saved
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		//The method getCostElement() not should be cached because is a transaction
		//MCostElement ce = getCostElement();
		MCostElement ce = (MCostElement)getM_CostElement();
		//	Check if data entry makes sense
		if (m_manual)
		{
			MAcctSchema as = new MAcctSchema (getCtx(), getC_AcctSchema_ID(), null);
			MProduct product = MProduct.get(getCtx(), getM_Product_ID());
			String CostingLevel = product.getCostingLevel(as);
			if (MAcctSchema.COSTINGLEVEL_Client.equals(CostingLevel))
			{
				if (getAD_Org_ID() != 0 || getM_AttributeSetInstance_ID() != 0)
				{
					log.saveError("CostingLevelClient", "");
					return false;
				}
			}
			else if (MAcctSchema.COSTINGLEVEL_BatchLot.equals(CostingLevel))
			{
				if (getM_AttributeSetInstance_ID() == 0
					&& ce.isCostingMethod())
				{
					log.saveError("FillMandatory", Msg.getElement(getCtx(), "M_AttributeSetInstance_ID"));
					return false;
				}
				if (getAD_Org_ID() != 0)
					setAD_Org_ID(0);
			}
		}

		//	Cannot enter calculated
		if (m_manual && ce != null && ce.isCalculated())
		{
			log.saveError("Error", Msg.getElement(getCtx(), "IsCalculated"));
			return false;
		}
		//	Percentage
		if (ce != null)
		{
			if (ce.isCalculated()
				|| MCostElement.COSTELEMENTTYPE_Material.equals(ce.getCostElementType())
				&& getPercent() != 0)
				setPercent(0);
		}
		if (getPercent() != 0)
		{
			if (getCurrentCostPrice().signum() != 0)
				setCurrentCostPrice(Env.ZERO);
			if (getFutureCostPrice().signum() != 0)
				setFutureCostPrice(Env.ZERO);
			if (getCumulatedAmt().signum() != 0)
				setCumulatedAmt(Env.ZERO);
			if (getCumulatedQty().signum() != 0)
				setCumulatedQty(Env.ZERO);
		}
		return true;
	}	//	beforeSave


	/**
	 * 	Before Delete
	 *	@return true
	 */
	protected boolean beforeDelete ()
	{
		return true;
	}	//	beforeDelete


	/**
	 * 	Test
	 *	@param args ignored
	 */
	public static void main (String[] args)
	{
		/**
		DELETE M_Cost c
		WHERE EXISTS (SELECT * FROM M_CostElement ce
		    WHERE c.M_CostElement_ID=ce.M_CostElement_ID AND ce.IsCalculated='Y')
		/
		UPDATE M_Cost
		  SET CumulatedAmt=0, CumulatedQty=0
		/
		UPDATE M_CostDetail
		  SET Processed='N'
		WHERE Processed='Y'
		/
		COMMIT
		/
		**/

		Adempiere.startup(true);
		MClient client = MClient.get(Env.getCtx(), 11);	//	GardenWorld
		//create(client);

	}	//	main

}	//	MCost
