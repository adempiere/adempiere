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
 *****************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * 	Product Cost Model.
 *	Summarizes Info in MCost
 *	
 *  @author Jorg Janke
 *  @version $Id: ProductCost.java,v 1.3 2006/07/30 00:51:03 jjanke Exp $
 */
public class ProductCost
{
	/**
	 * 	Constructor
	 *	@param ctx context
	 *	@param M_Product_ID product
	 *	@param M_AttributeSetInstance_ID asi
	 *	@param trxName trx
	 */
	public ProductCost (Properties ctx, int M_Product_ID, int M_AttributeSetInstance_ID, 
		String trxName)
	{
		m_M_Product_ID = M_Product_ID;
		if (m_M_Product_ID != 0)
			m_product = MProduct.get (ctx, M_Product_ID);
		m_M_AttributeSetInstance_ID = M_AttributeSetInstance_ID;
		m_trxName = trxName;
	}	//	ProductCost
	
	/** The ID					*/
	private int				m_M_Product_ID = 0;
	/** ASI						*/
	private int				m_M_AttributeSetInstance_ID = 0;
	/** The Product				*/
	private MProduct		m_product = null;
	/** Transaction				*/
	private String 			m_trxName = null;
	
	private int             m_C_UOM_ID = 0;
	private BigDecimal      m_qty = Env.ZERO;

	/**	Logger					*/
	private static CLogger 	log = CLogger.getCLogger (ProductCost.class);

	/**
	 *  Get Product
	 *  @return Product might be null
	 */
	public MProduct getProduct()
	{
		return m_product;
	}   //  getProduct

	/**
	 * 	Is this a Service
	 *	@return true if service
	 */
	public boolean isService()
	{
		if (m_product != null)
			return m_product.isService();
		return false;
	}	//	isService
	
	/**
	 *  Set Quantity in Storage UOM
	 *  @param qty quantity
	 */
	public void setQty (BigDecimal qty)
	{
		m_qty = qty;
	}   //  setQty

	/**
	 *  Set Quantity in UOM
	 *  @param qty quantity
	 *  @param C_UOM_ID UOM
	 */
	public void setQty (BigDecimal qty, int C_UOM_ID)
	{
		m_qty = MUOMConversion.convert (C_UOM_ID, m_C_UOM_ID, qty, true);    //  StdPrecision
		if (qty != null && m_qty == null)   //  conversion error
		{
			log.severe ("Conversion error - set to " + qty);
			m_qty = qty;
		}
		else
			m_C_UOM_ID = C_UOM_ID;
	}   //  setQty

	
	
	
	/** Product Revenue Acct    */
	public static final int ACCTTYPE_P_Revenue      = 1;
	/** Product Expense Acct    */
	public static final int ACCTTYPE_P_Expense      = 2;
	/** Product Asset Acct      */
	public static final int ACCTTYPE_P_Asset        = 3;
	/** Product COGS Acct       */
	public static final int ACCTTYPE_P_Cogs         = 4;
	/** Purchase Price Variance */
	public static final int ACCTTYPE_P_PPV          = 5;
	/** Invoice Price Variance  */
	public static final int ACCTTYPE_P_IPV          = 6;
	/** Trade Discount Revenue  */
	public static final int ACCTTYPE_P_TDiscountRec = 7;
	/** Trade Discount Costs    */
	public static final int ACCTTYPE_P_TDiscountGrant = 8;
	/** Cost Adjustment			*/
	public static final int ACCTTYPE_P_CostAdjustment = 9;
	/** Inventory Clearing		*/
	public static final int ACCTTYPE_P_InventoryClearing = 10;
	/** Work in Process  */
	public static final int ACCTTYPE_P_WorkInProcess = 11;
	/** Method Change Variance  */
	public static final int ACCTTYPE_P_MethodChangeVariance = 12;
	/** Material Usage Variance  */
	public static final int ACCTTYPE_P_UsageVariance = 13;
	/** Material Rate Variance  */
	public static final int ACCTTYPE_P_RateVariance = 14;
	/** Mix Variance  */
	public static final int ACCTTYPE_P_MixVariance = 15;
	/** Floor Stock  */
	public static final int ACCTTYPE_P_FloorStock = 16;
	/** Cost Production */
	public static final int ACCTTYPE_P_CostOfProduction = 17;
	/** Labor  */
	public static final int ACCTTYPE_P_Labor = 18;
	/** Burden  */
	public static final int ACCTTYPE_P_Burden = 19;	
	/** Outside Processing  */
	public static final int ACCTTYPE_P_OutsideProcessing = 20;	
	/** Outside Overhead  */
	public static final int ACCTTYPE_P_Overhead = 21;	
	/** Outside Processing  */
	public static final int ACCTTYPE_P_Scrap = 22;	
	/** Outside Processing  */
	public static final int ACCTTYPE_P_AverageCostVariance = 23;	

	/**
	 *  Line Account from Product
	 *
	 *  @param  AcctType see ACCTTYPE_* (1..8)
	 *  @param as Accounting Schema
	 *  @return Requested Product Account
	 */
	public MAccount getAccount(int AcctType, MAcctSchema as)
	{
		if (AcctType < 1 || AcctType > 23)
			return null;

		//  No Product - get Default from Product Category
		if (m_M_Product_ID == 0)
			return getAccountDefault(AcctType, as);

		String sql = "SELECT P_Revenue_Acct, P_Expense_Acct, P_Asset_Acct, P_Cogs_Acct, "	//	1..4
			+ "P_PurchasePriceVariance_Acct, P_InvoicePriceVariance_Acct, "	//	5..6
			+ "P_TradeDiscountRec_Acct, P_TradeDiscountGrant_Acct,"			//	7..8
			+ "P_CostAdjustment_Acct, P_InventoryClearing_Acct,"			//	9..10
			+ "P_WIP_Acct,P_MethodChangeVariance_Acct,P_UsageVariance_Acct,"	//  11.12.13
			+ "P_RateVariance_Acct,P_MixVariance_Acct,P_FloorStock_Acct," 	//  14.15.16
			+ "P_CostOfProduction_Acct,P_Labor_Acct,P_Burden_Acct,P_OutsideProcessing_Acct,"	//  17.18,19,20
			+ "P_Overhead_Acct,P_Scrap_Acct,P_AverageCostVariance_Acct "	//  21,23
			+ "FROM M_Product_Acct "
			+ "WHERE M_Product_ID=? AND C_AcctSchema_ID=?";
		//
		int validCombination_ID = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, m_M_Product_ID);
			pstmt.setInt(2, as.getC_AcctSchema_ID());
			rs = pstmt.executeQuery();
			if (rs.next())
				validCombination_ID = rs.getInt(AcctType);
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		if (validCombination_ID == 0)
			return null;
		return MAccount.get(as.getCtx(), validCombination_ID);
	}   //  getAccount

	/**
	 *  Account from Default Product Category
	 *
	 *  @param  AcctType see ACCTTYPE_* (1..8)
	 *  @param as accounting schema
	 *  @return Requested Product Account
	 */
	public MAccount getAccountDefault (int AcctType, MAcctSchema as)
	{
		if (AcctType < 1 || AcctType > 23)
			return null;

		String sql = "SELECT P_Revenue_Acct, P_Expense_Acct, P_Asset_Acct, P_Cogs_Acct, "
			+ "P_PurchasePriceVariance_Acct, P_InvoicePriceVariance_Acct, "
			+ "P_TradeDiscountRec_Acct, P_TradeDiscountGrant_Acct, "
			+ "P_CostAdjustment_Acct, P_InventoryClearing_Acct, "
			+ "P_WIP_Acct,P_MethodChangeVariance_Acct,P_UsageVariance_Acct,"		//  11.12.13
			+ "P_RateVariance_Acct,P_MixVariance_Acct,P_FloorStock_Acct," 			//  14.15.16
			+ "P_CostOfProduction_Acct,P_Labor_Acct,P_Burden_Acct,P_OutsideProcessing_Acct,"		//  17.18,19,20
			+ "P_Overhead_Acct,P_Scrap_Acct,P_AverageCostVariance_Acct "			//  21,23
			+ "FROM M_Product_Category pc, M_Product_Category_Acct pca "
			+ "WHERE pc.M_Product_Category_ID=pca.M_Product_Category_ID"
			+ " AND pca.C_AcctSchema_ID=? "
			+ "ORDER BY pc.IsDefault DESC, pc.Created";
		//
		int validCombination_ID = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, as.getC_AcctSchema_ID());
			rs = pstmt.executeQuery();
			if (rs.next())
				validCombination_ID = rs.getInt(AcctType);
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		if (validCombination_ID == 0)
			return null;
		return MAccount.get(as.getCtx(), validCombination_ID);
	}   //  getAccountDefault
	
	
	/**************************************************************************
	 *  Get Total Costs (amt*qty) in Accounting Schema Currency
	 *  @param as accounting schema
	 *  @param AD_Org_ID trx org
	 *  @param costingMethod if null uses Accounting Schema - AcctSchema.COSTINGMETHOD_*
	 *  @param C_OrderLine_ID optional order line
	 *	@param zeroCostsOK zero/no costs are OK
	 *  @return cost or null, if qty or costs cannot be determined
	 */
	public BigDecimal getProductCosts (MAcctSchema as, int AD_Org_ID, 
		String costingMethod, int C_OrderLine_ID, boolean zeroCostsOK)
	{
		if (m_qty == null)
		{
			log.fine("No Qty");
			return null;
		}
		/**	Old Costing
		MClient client = MClient.get(as.getCtx(), as.getAD_Client_ID());
		if (!client.isUseBetaFunctions())
		{
			BigDecimal itemCost = getProductItemCostOld(as, costingMethod);
			BigDecimal cost = m_qty.multiply(itemCost);
			cost = cost.setScale(as.getCostingPrecision(), BigDecimal.ROUND_HALF_UP);
			log.fine("Qty(" + m_qty + ") * Cost(" + itemCost + ") = " + cost);
			return cost;
		}
		**/
		
		//	No Product
		if (m_product == null)
		{
			log.fine("No Product");
			return null;
		}
		//
		BigDecimal cost = MCost.getCurrentCost (m_product, m_M_AttributeSetInstance_ID, 
			as, AD_Org_ID, costingMethod, m_qty, C_OrderLine_ID, zeroCostsOK, m_trxName);
		if (cost == null)
		{
			log.fine("No Costs");
			return null;
		}
		return cost;
	}   //  getProductCosts


	/**
	 *  Get Product Costs per UOM for Accounting Schema in Accounting Schema Currency.
	 *  - if costType defined - cost
	 *  - else CurrentCosts
	 *  @param as accounting schema
	 *  @param costType - if null uses Accounting Schema Costs - see AcctSchema.COSTING_*
	 *  @return product costs
	 */
	private BigDecimal getProductItemCostOld (MAcctSchema as, String costType)
	{
		BigDecimal current = null;
		BigDecimal cost = null;
		String cm = as.getCostingMethod();
		StringBuffer sql = new StringBuffer("SELECT CurrentCostPrice,");	//	1
		//
		if ((costType == null && MAcctSchema.COSTINGMETHOD_AveragePO.equals(cm))
				|| MAcctSchema.COSTINGMETHOD_AveragePO.equals(costType))
			sql.append("COSTAVERAGE");										//	2
	//	else if (AcctSchema.COSTING_FIFO.equals(cm))
	//		sql.append("COSTFIFO");
	//	else if (AcctSchema.COSTING_LIFO.equals(cm))
	//		sql.append("COSTLIFO");
		else if ((costType == null && MAcctSchema.COSTINGMETHOD_LastPOPrice.equals(cm))
				|| MAcctSchema.COSTINGMETHOD_LastPOPrice.equals(costType))
			sql.append("PRICELASTPO");
		else    //  AcctSchema.COSTING_STANDARD
			sql.append("COSTSTANDARD");
		sql.append(" FROM M_Product_Costing WHERE M_Product_ID=? AND C_AcctSchema_ID=?");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, m_M_Product_ID);
			pstmt.setInt(2, as.getC_AcctSchema_ID());
			rs = pstmt.executeQuery();
			if (rs.next())
			{
				current = rs.getBigDecimal(1);
				cost = rs.getBigDecimal(2);
			}
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql.toString(), e);
		}
		finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}

		//  Return Costs
		if (costType != null && cost != null && cost.compareTo(Env.ZERO)!=0)
		{
			log.fine("Costs=" + cost);
			return cost;
		}
		else if (current != null && current.compareTo(Env.ZERO)!=0)
		{
			log.fine("Current=" + current);
			return current;
		}

		//  Create/Update Cost Record
		boolean create = (cost == null && current == null);
		return updateCostsOld (as, create);
	}   //  getProductCostOld

	/**
	 *  Update/Create initial Cost Record.
	 *  Check first for     Purchase Price List,
	 *      then Product    Purchase Costs
	 *      and then        Price List
	 *  @param as accounting schema
	 *  @param create create record
	 *  @return costs
	 */
	private BigDecimal updateCostsOld (MAcctSchema as, boolean create)
	{
		//  Create Zero Record
		if (create)
		{
			StringBuffer sql = new StringBuffer ("INSERT INTO M_Product_Costing "
				+ "(M_Product_ID,C_AcctSchema_ID,"
				+ " AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,"
				+ " CurrentCostPrice,CostStandard,FutureCostPrice,"
				+ " CostStandardPOQty,CostStandardPOAmt,CostStandardCumQty,CostStandardCumAmt,"
				+ " CostAverage,CostAverageCumQty,CostAverageCumAmt,"
				+ " PriceLastPO,PriceLastInv, TotalInvQty,TotalInvAmt) "
				+ "VALUES (");
			sql.append(m_M_Product_ID).append(",").append(as.getC_AcctSchema_ID()).append(",")
				.append(as.getAD_Client_ID()).append(",").append(as.getAD_Org_ID()).append(",")
				.append("'Y',SysDate,0,SysDate,0, 0,0,0,  0,0,0,0,  0,0,0,  0,0,  0,0)");
			int no = DB.executeUpdate(sql.toString(), m_trxName);
			if (no == 1)
				log.fine("CostingCreated");
		}

		//  Try to find non ZERO Price
		String costSource = "PriceList-PO";
		BigDecimal costs = getPriceList (as, true);
		if (costs == null || costs.compareTo(Env.ZERO)==0)
		{
			costSource = "PO Cost";
			costs = getPOCost(as);
		}
		if (costs == null || costs.compareTo(Env.ZERO)==0)
		{
			costSource = "PriceList";
			costs = getPriceList (as, false);
		}

		//  if not found use $1 (to be able to do material transactions)
		if (costs == null || costs.compareTo(Env.ZERO)==0)
		{
			costSource = "Not Found";
			costs = new BigDecimal("1");
		}

		//  update current costs
		StringBuffer sql = new StringBuffer ("UPDATE M_Product_Costing ");
		sql.append("SET CurrentCostPrice=").append(costs)
			.append(" WHERE M_Product_ID=").append(m_M_Product_ID)
			.append(" AND C_AcctSchema_ID=").append(as.getC_AcctSchema_ID());
		int no = DB.executeUpdate(sql.toString(), m_trxName);
		if (no == 1)
			log.fine(costSource + " - " + costs);
		return costs;
	}   //  createCosts

	/**
	 *  Get PO Price from PriceList - and convert it to AcctSchema Currency
	 *  @param as accounting schema
	 *  @param onlyPOPriceList use only PO price list
	 *  @return po price
	 */
	private BigDecimal getPriceList (MAcctSchema as, boolean onlyPOPriceList)
	{
		StringBuffer sql = new StringBuffer (
			"SELECT pl.C_Currency_ID, pp.PriceList, pp.PriceStd, pp.PriceLimit "
			+ "FROM M_PriceList pl, M_PriceList_Version plv, M_ProductPrice pp "
			+ "WHERE pl.M_PriceList_ID = plv.M_PriceList_ID"
			+ " AND plv.M_PriceList_Version_ID = pp.M_PriceList_Version_ID"
			+ " AND pp.M_Product_ID=?");
		if (onlyPOPriceList)
			sql.append(" AND pl.IsSOPriceList='N'");
		sql.append(" ORDER BY pl.IsSOPriceList ASC, plv.ValidFrom DESC");
		int C_Currency_ID = 0;
		BigDecimal PriceList = null;
		BigDecimal PriceStd = null;
		BigDecimal PriceLimit = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, m_M_Product_ID);
			rs = pstmt.executeQuery();
			if (rs.next())
			{
				C_Currency_ID = rs.getInt(1);
				PriceList = rs.getBigDecimal(2);
				PriceStd = rs.getBigDecimal(3);
				PriceLimit = rs.getBigDecimal(4);
			}
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql.toString(), e);
		}
		finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		//  nothing found
		if (C_Currency_ID == 0)
			return null;

		BigDecimal price = PriceLimit;  //  best bet
		if (price == null || price.compareTo(Env.ZERO)==0)
			price = PriceStd;
		if (price == null || price.compareTo(Env.ZERO)==0)
			price = PriceList;
		//  Convert
		if (price != null && price.compareTo(Env.ZERO)!=0)
			price = MConversionRate.convert (as.getCtx(),
				price, C_Currency_ID, as.getC_Currency_ID(), 
				as.getAD_Client_ID(), 0);
		return price;
	}   //  getPOPrice

	/**
	 *  Get PO Cost from Purchase Info - and convert it to AcctSchema Currency
	 *  @param as accounting schema
	 *  @return po cost
	 */
	private BigDecimal getPOCost (MAcctSchema as)
	{
		String sql = "SELECT C_Currency_ID, PriceList,PricePO,PriceLastPO "
			+ "FROM M_Product_PO WHERE M_Product_ID=? "
			+ "ORDER BY IsCurrentVendor DESC";

		int C_Currency_ID = 0;
		BigDecimal PriceList = null;
		BigDecimal PricePO = null;
		BigDecimal PriceLastPO = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, m_M_Product_ID);
			rs = pstmt.executeQuery();
			if (rs.next())
			{
				C_Currency_ID = rs.getInt(1);
				PriceList = rs.getBigDecimal(2);
				PricePO = rs.getBigDecimal(3);
				PriceLastPO = rs.getBigDecimal(4);
			}
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		//  nothing found
		if (C_Currency_ID == 0)
			return null;

		BigDecimal cost = PriceLastPO;  //  best bet
		if (cost == null || cost.compareTo(Env.ZERO)==0)
			cost = PricePO;
		if (cost == null || cost.compareTo(Env.ZERO)==0)
			cost = PriceList;
		//  Convert - standard precision!! - should be costing precision
		if (cost != null && cost.compareTo(Env.ZERO)!=0)
			cost = MConversionRate.convert (as.getCtx(),
				cost, C_Currency_ID, as.getC_Currency_ID(), as.getAD_Client_ID(), as.getAD_Org_ID());
		return cost;
	}   //  getPOCost

	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer ("ProductCost[");
		sb.append ("M_Product_ID=").append(m_M_Product_ID)
			.append(",M_AttributeSetInstance_ID").append(m_M_AttributeSetInstance_ID)
			.append (",Qty=").append(m_qty)
			.append ("]");
		return sb.toString ();
	}	//	toString
	
}	//	ProductCost
