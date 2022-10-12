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
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.core.domains.models.I_M_Cost;
import org.adempiere.engine.CostComponent;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * 	Product Cost Model.
 *	Summarizes Info in MCost
 *	
 *  @author Jorg Janke
 *  @author victor.perez@e-evolution.com, www.e-evolution.com
 *  @version $Id: ProductCost.java,v 1.3 2006/07/30 00:51:03 jjanke Exp $
 */
public class ProductCost
{
	/**
	 * 	Constructor
	 *	@param ctx context
	 *	@param productId product
	 *	@param attributeSetInstanceId asi
	 *	@param trxName trx
	 */
	public ProductCost (Properties ctx, int productId, int attributeSetInstanceId,
		String trxName)
	{
		this.productId = productId;
		if (this.productId != 0)
			product = MProduct.get (ctx, productId);
		this.attributeSetInstanceId = attributeSetInstanceId;
		this.trxName = trxName;
	}	//	ProductCost
	
	/** The ID					*/
	private int productId = 0;
	/** ASI						*/
	private int				attributeSetInstanceId = 0;
	/** The Product				*/
	private MProduct		product = null;
	/** Transaction				*/
	private String 			trxName = null;
	
	private int 			uomId = 0;
	private BigDecimal      quantity = Env.ZERO;

	/**	Logger					*/
	private static CLogger 	log = CLogger.getCLogger (ProductCost.class);

	/**
	 *  Get Product
	 *  @return Product might be null
	 */
	public MProduct getProduct()
	{
		return product;
	}   //  getProduct

	/**
	 * 	Is this a Service
	 *	@return true if service
	 */
	public boolean isService()
	{
		if (product != null)
			return product.isService();
		return false;
	}	//	isService
	
	/**
	 *  Set Quantity in Storage UOM
	 *  @param qty quantity
	 */
	public void setQty (BigDecimal qty)
	{
		this.quantity = qty;
	}   //  setQty

	/**
	 *  Set Quantity in UOM
	 *  @param qty quantity
	 *  @param uomId UOM
	 */
	public void setQty (BigDecimal qty, int uomId)
	{
		this.quantity = MUOMConversion.convert (uomId, this.uomId, qty, true);    //  StdPrecision
		if (qty != null && this.quantity == null)   //  conversion error
		{
			log.severe ("Conversion error - set to " + qty);
			this.quantity = qty;
		}
		else
			this.uomId = uomId;
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
	 *  @param  acctType see ACCTTYPE_* (1..8)
	 *  @param acctSchema Accounting Schema
	 *  @return Requested Product Account
	 */
	public MAccount getAccount(int acctType, MAcctSchema acctSchema)
	{
		if (acctType < 1 || acctType > 23)
			return null;

		//  No Product - get Default from Product Category
		if (productId == 0)
			return getAccountDefault(acctType, acctSchema);

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
		int validCombinationId = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, productId);
			pstmt.setInt(2, acctSchema.getC_AcctSchema_ID());
			rs = pstmt.executeQuery();
			if (rs.next())
				validCombinationId = rs.getInt(acctType);
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		if (validCombinationId == 0)
			return null;
		return MAccount.getValidCombination(acctSchema.getCtx(), validCombinationId, trxName);
	}   //  getAccount
	
	/**
	 *  Line Account from Product
	 *
	 *  @param  acctType see ACCTTYPE_* (1..8)
	 *  @param acctSchema Accounting Schema
	 *  @return Requested Product Account
	 */
	public MAccount getAccount(int acctType, MAcctSchema acctSchema , int orgId)
	{
		if (acctType < 1 || acctType > 22)
			return null;

		//  No Product - get Default from Product Category
		if (productId == 0)
			return getAccountDefault(acctType, acctSchema, orgId);

		String sql = "SELECT P_Revenue_Acct, P_Expense_Acct, P_Asset_Acct, P_Cogs_Acct, "	//	1..4
			+ "P_PurchasePriceVariance_Acct, P_InvoicePriceVariance_Acct, "	//	5..6
			+ "P_TradeDiscountRec_Acct, P_TradeDiscountGrant_Acct,"			//	7..8
			+ "P_CostAdjustment_Acct, P_InventoryClearing_Acct,"			//	9..10
			+ "P_WIP_Acct,P_MethodChangeVariance_Acct,P_UsageVariance_Acct,"		//  11.12.13
			+ "P_RateVariance_Acct,P_MixVariance_Acct,P_FloorStock_Acct," 					//  14.15.16
			+ "P_CostOfProduction_Acct,P_Labor_Acct,P_Burden_Acct,P_OutsideProcessing_Acct,"	//  17.18,19,20
			+ "P_Overhead_Acct,P_Scrap_Acct "											//  21,22
			+ "FROM M_Product_Acct "
			+ "WHERE M_Product_ID=? AND C_AcctSchema_ID=? AND (AD_Org_ID=? OR AD_Org_ID=0) ORDER BY AD_Org_ID DESC";
		//
		int validCombinationId = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, productId);
			pstmt.setInt(2, acctSchema.getC_AcctSchema_ID());
			pstmt.setInt(3, orgId);
			rs = pstmt.executeQuery();
			if (rs.next())
				validCombinationId = rs.getInt(acctType);
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		if (validCombinationId == 0)
			return null;
		return MAccount.getValidCombination(acctSchema.getCtx(), validCombinationId, trxName);
	}   //  getAccount

	/**
	 *  Account from Default Product Category
	 *
	 *  @param  acctType see ACCTTYPE_* (1..8)
	 *  @param acctSchema accounting schema
	 *  @return Requested Product Account
	 */
	public MAccount getAccountDefault (int acctType, MAcctSchema acctSchema)
	{
		if (acctType < 1 || acctType > 23)
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
		int validCombinationId = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, acctSchema.getC_AcctSchema_ID());
			rs = pstmt.executeQuery();
			if (rs.next())
				validCombinationId = rs.getInt(acctType);
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		if (validCombinationId == 0)
			return null;
		return MAccount.getValidCombination(acctSchema.getCtx(), validCombinationId, trxName);
	}   //  getAccountDefault
	
	/**
	 *  Account from Default Product Category
	 *
	 *  @param  acctType see ACCTTYPE_* (1..8)
	 *  @param acctSchema accounting schema
	 *  @return Requested Product Account
	 */
	public MAccount getAccountDefault (int acctType, MAcctSchema acctSchema, int orgId)
	{
		if (acctType < 1 || acctType > 22)
			return null;

		String sql = "SELECT P_Revenue_Acct, P_Expense_Acct, P_Asset_Acct, P_Cogs_Acct, "
			+ "P_PurchasePriceVariance_Acct, P_InvoicePriceVariance_Acct, "
			+ "P_TradeDiscountRec_Acct, P_TradeDiscountGrant_Acct, "
			+ "P_CostAdjustment_Acct, P_InventoryClearing_Acct, "
			+ "P_WIP_Acct,P_MethodChangeVariance_Acct,P_UsageVariance_Acct,"		//  11.12.13
			+ "P_RateVariance_Acct,P_MixVariance_Acct,P_FloorStock_Acct," 					//  14.15.16
			+ "P_CostOfProduction_Acct,P_Labor_Acct,P_Burden_Acct,P_OutsideProcessing_Acct,"		//  17.18,19,20
			+ "P_Overhead_Acct,P_Scrap_Acct "											//  21,22
			+ "FROM M_Product_Category pc, M_Product_Category_Acct pca "
			+ "WHERE pc.M_Product_Category_ID=pca.M_Product_Category_ID"
			+ " AND pca.C_AcctSchema_ID=? AND (pca.AD_Org_ID=? OR pca.AD_Org_ID=0)"
			+ " ORDER BY pca.AD_Org_ID DESC , pc.IsDefault DESC, pc.Created ";
		//
		int validCombinationId = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, acctSchema.getC_AcctSchema_ID());
			pstmt.setInt(2, orgId);
			rs = pstmt.executeQuery();
			if (rs.next())
				validCombinationId = rs.getInt(acctType);
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		if (validCombinationId == 0)
			return null;
		return MAccount.getValidCombination(acctSchema.getCtx(), validCombinationId, trxName);
	}   //  getAccountDefault
	
	
	/**************************************************************************
	 *  Get Total Costs (amt*qty) in Accounting Schema Currency
	 *  @param as accounting schema
	 *  @param AD_Org_ID trx org
	 *  @param costingMethod if null uses Accounting Schema - AcctSchema.COSTINGMETHOD_*
	 *  @param C_OrderLine_ID optional order line
	 *	@param zeroCostsOK zero/no costs are OK
	 *  @return cost or null, if qty or costs cannot be determined
	 *  @deprecated
	 */
	/*public BigDecimal getProductCosts (MAcctSchema as, int AD_Org_ID, int M_Warehouse_ID,
		String costingMethod, int C_OrderLine_ID, boolean zeroCostsOK)
	{
		if (m_qty == null)
		{
			log.fine("No Qty");
			return null;
		}
		*//**	Old Costing
		MClient client = MClient.get(as.getCtx(), as.getAD_Client_ID());
		if (!client.isUseBetaFunctions())
		{
			BigDecimal itemCost = getProductItemCostOld(as, costingMethod);
			BigDecimal cost = m_qty.multiply(itemCost);
			cost = cost.setScale(as.getCostingPrecision(), BigDecimal.ROUND_HALF_UP);
			log.fine("Qty(" + m_qty + ") * Cost(" + itemCost + ") = " + cost);
			return cost;
		}
		**//*
		
		//	No Product
		if (m_product == null)
		{
			log.fine("No Product");
			return null;
		}
		//
		BigDecimal cost = MCost.getCurrentCost (m_product, m_M_AttributeSetInstance_ID, 
			as, AD_Org_ID,M_Warehouse_ID, costingMethod, m_qty, C_OrderLine_ID, zeroCostsOK, m_trxName);
		if (cost == null)
		{
			log.fine("No Costs");
			return null;
		}
		return cost;
	}   //  getProductCosts*/



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
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, productId);
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
		if (costType != null && cost != null && !cost.equals(Env.ZERO))
		{
			log.fine("Costs=" + cost);
			return cost;
		}
		else if (current != null && !current.equals(Env.ZERO))
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
			sql.append(productId).append(",").append(as.getC_AcctSchema_ID()).append(",")
				.append(as.getAD_Client_ID()).append(",").append(as.getAD_Org_ID()).append(",")
				.append("'Y',SysDate,0,SysDate,0, 0,0,0,  0,0,0,0,  0,0,0,  0,0,  0,0)");
			int no = DB.executeUpdate(sql.toString(), trxName);
			if (no == 1)
				log.fine("CostingCreated");
		}

		//  Try to find non ZERO Price
		String costSource = "PriceList-PO";
		BigDecimal costs = getPriceList (as, true);
		if (costs == null || costs.equals(Env.ZERO))
		{
			costSource = "PO Cost";
			costs = getPOCost(as);
		}
		if (costs == null || costs.equals(Env.ZERO))
		{
			costSource = "PriceList";
			costs = getPriceList (as, false);
		}

		//  if not found use $1 (to be able to do material transactions)
		if (costs == null || costs.equals(Env.ZERO))
		{
			costSource = "Not Found";
			costs = new BigDecimal("1");
		}

		//  update current costs
		StringBuffer sql = new StringBuffer ("UPDATE M_Product_Costing ");
		sql.append("SET CurrentCostPrice=").append(costs)
			.append(" WHERE M_Product_ID=").append(productId)
			.append(" AND C_AcctSchema_ID=").append(as.getC_AcctSchema_ID());
		int no = DB.executeUpdate(sql.toString(), trxName);
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
		int currencyId = 0;
		BigDecimal priceList = null;
		BigDecimal priceStd = null;
		BigDecimal priceLimit = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, productId);
			rs = pstmt.executeQuery();
			if (rs.next())
			{
				currencyId = rs.getInt(1);
				priceList = rs.getBigDecimal(2);
				priceStd = rs.getBigDecimal(3);
				priceLimit = rs.getBigDecimal(4);
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
		if (currencyId == 0)
			return null;

		BigDecimal price = priceLimit;  //  best bet
		if (price == null || price.equals(Env.ZERO))
			price = priceStd;
		if (price == null || price.equals(Env.ZERO))
			price = priceList;
		//  Convert
		if (price != null && !price.equals(Env.ZERO))
			price = MConversionRate.convert (as.getCtx(),
				price, currencyId, as.getC_Currency_ID(),
				as.getAD_Client_ID(), 0);
		return price;
	}   //  getPOPrice

	/**
	 *  Get PO Cost from Purchase Info - and convert it to AcctSchema Currency
	 *  @param acctSchema accounting schema
	 *  @return po cost
	 */
	private BigDecimal getPOCost (MAcctSchema acctSchema)
	{
		String sql = "SELECT C_Currency_ID, PriceList,PricePO,PriceLastPO "
			+ "FROM M_Product_PO WHERE M_Product_ID=? "
			+ "ORDER BY IsCurrentVendor DESC";

		int currencyId = 0;
		BigDecimal PriceList = null;
		BigDecimal PricePO = null;
		BigDecimal PriceLastPO = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, productId);
			rs = pstmt.executeQuery();
			if (rs.next())
			{
				currencyId = rs.getInt(1);
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
		if (currencyId == 0)
			return null;

		BigDecimal cost = PriceLastPO;  //  best bet
		if (cost == null || cost.equals(Env.ZERO))
			cost = PricePO;
		if (cost == null || cost.equals(Env.ZERO))
			cost = PriceList;
		//  Convert - standard precision!! - should be costing precision
		if (cost != null && !cost.equals(Env.ZERO))
			cost = MConversionRate.convert (acctSchema.getCtx(),
				cost, currencyId, acctSchema.getC_Currency_ID(), acctSchema.getAD_Client_ID(), acctSchema.getAD_Org_ID());
		return cost;
	}   //  getPOCost

	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer ("ProductCost[");
		sb.append ("M_Product_ID=").append(productId)
			.append(",M_AttributeSetInstance_ID").append(attributeSetInstanceId)
			.append (",Qty=").append(quantity)
			.append ("]");
		return sb.toString ();
	}	//	toString

	public List<CostComponent> getProductCostsLayers (I_M_Cost cost, int orderLineId, boolean zeroCostsOK)
	{
		if (quantity == null)
		{
			log.fine("No Qty");
			return null;
		}
		//	No Product
		if (product == null)
		{
			log.fine("No Product");
			return null;
		}
		 List<CostComponent> list = MCost.getCurrentCostLayers ((MCost) cost, quantity, orderLineId, zeroCostsOK, trxName);
		if (list == null)
		{
			log.fine("No Costs");
			return null;
		}
		return list;
	}   //  getProductCosts
}	//	ProductCost
