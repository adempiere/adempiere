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
 * Contributor(s): Layda Salas (globalqss)
 * Contributor(s): Carlos Ruiz (globalqss)
 *****************************************************************************/

package org.compiere.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.adempiere.core.domains.models.I_M_DiscountSchemaLine;
import org.adempiere.core.domains.models.I_M_ProductPrice;
import org.adempiere.core.domains.models.I_M_Product_PO;
import org.adempiere.core.domains.models.X_M_DiscountSchemaLine;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MConversionRate;
import org.compiere.model.MDiscountSchemaLine;
import org.compiere.model.MPriceList;
import org.compiere.model.MPriceListVersion;
import org.compiere.model.MProductPO;
import org.compiere.model.MProductPrice;
import org.compiere.model.Query;
import org.compiere.util.AdempiereUserError;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.ValueNamePair;

/**
 * Create PriceList by copying purchase prices (M_Product_PO) 
 *		and applying product category discounts (M_CategoryDiscount)
 * 
 * @author Layda Salas (globalqss)
 * @version $Id: M_PriceList_Create,v 1.0 2005/10/09 22:19:00
 *          globalqss Exp $
 * @author Carlos Ruiz (globalqss)
 *         Make T_Selection tables permanent   
 * @author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 *	Improve calculation times
 *	Add Product Group, Class and Classification for criteria      
 */
public class M_PriceList_Create extends M_PriceList_CreateAbstract {

	/** The Price List Version*/
	private MPriceListVersion priceListVersion;
	/**	Price List	*/
	private MPriceList priceList;
	
	/**
	 * Prepare - e.g., get Parameters.
	 */
	protected void prepare() {
		super.prepare();
		//	Validate record ID
		if(getParameterAsInt("PriceList_Version_ID") != 0) {
			priceListVersion = new MPriceListVersion(getCtx(), getParameterAsInt("PriceList_Version_ID"), get_TrxName());
		} else if(getRecord_ID() != 0) { 
			priceListVersion = new MPriceListVersion(getCtx(), getRecord_ID(), get_TrxName());
		}
		//	
		if(priceListVersion == null) {
			throw new AdempiereException("@PriceList_Version_ID@ @NotFound@");
		}
		//	Get price list
		priceList = MPriceList.get(getCtx(), priceListVersion.getM_PriceList_ID(), get_TrxName());
	}

	/**
	 * Process
	 * 
	 * @return message
	 * @throws Exception
	 */

	protected String doIt() throws Exception {
		String sql;
		String sqlupd;
		String sqldel;
		int cntu = 0;
		int cntd = 0;
		int totu = 0;
		int toti = 0;
		int totd = 0;
		String message = " ";
		//
		//Checking Prerequisites
		//PO Prices must exists
		//
		sqlupd = "UPDATE M_Product_PO " + " SET	PriceList = 0  "
				+ " WHERE	PriceList IS NULL ";

		cntu = DB.executeUpdate(sqlupd, get_TrxName());
		if (cntu == -1)
			raiseError(
					"Update The PriceList to zero of M_Product_PO WHERE	PriceList IS NULL",
					sqlupd);
		totu += cntu;
		log.fine("Updated " + cntu);

		sqlupd = "UPDATE M_Product_PO " + " SET	PriceLastPO = 0  "
				+ " WHERE	PriceLastPO IS NULL ";

		cntu = DB.executeUpdate(sqlupd, get_TrxName());
		if (cntu == -1)
			raiseError(
					"Update  The PriceListPO to zero of  M_Product_PO WHERE	PriceLastPO IS NULL",
					sqlupd);
		totu += cntu;
		log.fine("Updated " + cntu);

		sqlupd = "UPDATE M_Product_PO "
				+ " SET	    PricePO = PriceLastPO  "
				+ " WHERE	(PricePO IS NULL OR PricePO = 0) AND PriceLastPO <> 0 ";

		cntu = DB.executeUpdate(sqlupd, get_TrxName());
		if (cntu == -1)
			raiseError(
					"Update  The PricePO to PriceLastPO of  M_Product_PO WHERE	(PricePO IS NULL OR PricePO = 0) AND PriceLastPO <> 0 ",
					sqlupd);
		totu += cntu;
		log.fine("Updated " + cntu);

		sqlupd = "UPDATE M_Product_PO " + " SET	    PricePO = 0  "
				+ " WHERE	PricePO IS NULL ";

		cntu = DB.executeUpdate(sqlupd, get_TrxName());
		if (cntu == -1)
			raiseError(
					"Update  The PricePO to Zero of  M_Product_PO WHERE	PricePO IS NULL",
					sqlupd);
		totu += cntu;
		log.fine("Updated " + cntu);
		//
		//  Set default current vendor
		//
		sqlupd = "UPDATE M_Product_PO " + " SET	     IsCurrentVendor = 'Y'  "
				+ " WHERE	 IsCurrentVendor = 'N' " + " AND NOT   EXISTS "
				+ " (SELECT   pp.M_Product_ID " + "  FROM     M_Product_PO pp "
				+ "  WHERE    pp.M_Product_ID = M_Product_PO.M_Product_ID"
				+ "  GROUP BY pp.M_Product_ID HAVING COUNT(*) > 1) ";

		cntu = DB.executeUpdate(sqlupd, get_TrxName());
		if (cntu == -1)
			raiseError("Update  IsCurrentVendor to Y of  M_Product_PO ", sqlupd);
		totu += cntu;
		log.fine("Updated " + cntu);

		// let the commit for SvrProcess
		// DB.commit(true, get_TrxName());

		//
		//	Make sure that we have only one active product
		//
		sql = "SELECT DISTINCT M_Product_ID FROM M_Product_PO po "
				+ " WHERE	 IsCurrentVendor='Y' AND IsActive='Y' "
				+ "   AND    EXISTS (SELECT   M_Product_ID "
				                   + " FROM     M_Product_PO x  "
				                   + " WHERE    x.M_Product_ID=po.M_Product_ID "
				                   + "   AND    IsCurrentVendor='Y' AND IsActive='Y' "
				                   + " GROUP BY M_Product_ID " + " HAVING COUNT(*) > 1 ) ";
		
		PreparedStatement Cur_Duplicates = null;
		Cur_Duplicates = DB.prepareStatement(sql, get_TrxName());
		ResultSet dupl = Cur_Duplicates.executeQuery();
		while (dupl.next()) {
			sql = "SELECT	M_Product_ID " + "        ,C_BPartner_ID "
					+ " FROM	M_Product_PO " + " WHERE	IsCurrentVendor = 'Y'  "
					+ " AND     IsActive        = 'Y' "
					+ " AND	M_Product_ID    = " + dupl.getInt("M_Product_ID")
					+ " ORDER BY PriceList DESC";

			PreparedStatement Cur_Vendors = null;
			Cur_Vendors = DB.prepareStatement(sql, get_TrxName());
			ResultSet Vend = Cur_Vendors.executeQuery();

			//
			//	Leave First
			//
			Vend.next();
			
			while (Vend.next()) {
				sqlupd = "UPDATE M_Product_PO "
						+ " SET	IsCurrentVendor = 'N'  "
						+ " WHERE	M_Product_ID= " + Vend.getInt("M_Product_ID")
						+ " AND     C_BPartner_ID= "
						+ Vend.getInt("C_BPartner_ID");

				cntu = DB.executeUpdate(sqlupd, get_TrxName());
				if (cntu == -1)
					raiseError(
							"Update  IsCurrentVendor to N of  M_Product_PO for a M_Product_ID and C_BPartner_ID ingresed",
							sqlupd);
				totu += cntu;
				log.fine("Updated " + cntu);

			}
			Vend.close();
			Cur_Vendors.close();
			Cur_Vendors = null;

		}
		log.fine("Total Updated " + totu);
		log.fine("Total Insert " + toti);
		log.fine("Total Updated " + totu);
		dupl.close();
		Cur_Duplicates.close();
		Cur_Duplicates = null;

		// DB.commit(true, get_TrxName());
		//	Delete Old Data	
		if (isDeleteOld()) {
			sqldel = "DELETE M_ProductPrice "
					+ " WHERE	M_PriceList_Version_ID = "
					+ priceListVersion.getM_PriceList_Version_ID();
			cntd = DB.executeUpdate(sqldel, get_TrxName());
			if (cntd == -1)
				raiseError(" DELETE	M_ProductPrice ", sqldel);
			totd += cntd;
			message = "@Deleted@=" + cntd + " - ";
			log.fine("Deleted " + cntd);
			log.fine(message + ": Total Deleted " + totd);
		}
		//
		// Get PriceList Info
		//	Used for all combination products and price list
		Map<Integer, ProductCombination> combinationMap = new HashMap<>();
		Map<String, BigDecimal> conversionRateMap = new HashMap<String, BigDecimal>();
		//	Get 
		StringBuffer whereClause = new StringBuffer("M_DiscountSchema_ID = ?");
		List<MDiscountSchemaLine> discountSchemaLineList = new Query(getCtx(), I_M_DiscountSchemaLine.Table_Name, whereClause.toString(), get_TrxName())
			.setParameters(priceListVersion.getM_DiscountSchema_ID())
			.setClient_ID()
			.setOnlyActiveRecords(true)
			.setOrderBy(I_M_DiscountSchemaLine.COLUMNNAME_SeqNo)
			.<MDiscountSchemaLine>list();
		//	For purchasing product
		if(priceListVersion.getM_Pricelist_Version_Base_ID() == 0) {
			discountSchemaLineList.forEach(discountSchemaLine -> {
				List<Object> parameters = new ArrayList<>();
				StringBuffer querywhereClause = new StringBuffer("IsCurrentVendor = 'Y'");
				//	Create where clause
				//	for BBusiness Partner
				if(discountSchemaLine.getC_BPartner_ID() != 0) {
					if(querywhereClause.length() > 0) {
						querywhereClause.append(" AND ");
					}
					querywhereClause.append("C_BPartner_ID = ?");
					parameters.add(discountSchemaLine.getC_BPartner_ID());
				}
				//	for product
				if(discountSchemaLine.getM_Product_ID() != 0) {
					if(querywhereClause.length() > 0) {
						querywhereClause.append(" AND ");
					}
					querywhereClause.append("M_Product_ID = ?");
					parameters.add(discountSchemaLine.getM_Product_ID());
				}
				//	for product category
				if(discountSchemaLine.getM_Product_Category_ID() != 0) {
					if(querywhereClause.length() > 0) {
						querywhereClause.append(" AND ");
					}
					querywhereClause.append("EXISTS(SELECT 1 FROM M_Product p WHERE p.M_Product_ID = M_Product_PO.M_Product_ID AND p.M_Product_Category_ID = ?)");
					parameters.add(discountSchemaLine.getM_Product_Category_ID());
				}
				//	for product group
				if(discountSchemaLine.getM_Product_Group_ID() != 0) {
					if(querywhereClause.length() > 0) {
						querywhereClause.append(" AND ");
					}
					querywhereClause.append("EXISTS(SELECT 1 FROM M_Product p WHERE p.M_Product_ID = M_Product_PO.M_Product_ID AND p.M_Product_Group_ID = ?)");
					parameters.add(discountSchemaLine.getM_Product_Group_ID());
				}
				//	for product class
				if(discountSchemaLine.getM_Product_Class_ID() != 0) {
					if(querywhereClause.length() > 0) {
						querywhereClause.append(" AND ");
					}
					querywhereClause.append("EXISTS(SELECT 1 FROM M_Product p WHERE p.M_Product_ID = M_Product_PO.M_Product_ID AND p.M_Product_Class_ID = ?)");
					parameters.add(discountSchemaLine.getM_Product_Class_ID());
				}
				//	for product classification
				if(discountSchemaLine.getM_Product_Classification_ID() != 0) {
					if(querywhereClause.length() > 0) {
						querywhereClause.append(" AND ");
					}
					querywhereClause.append("EXISTS(SELECT 1 FROM M_Product p WHERE p.M_Product_ID = M_Product_PO.M_Product_ID AND p.M_Product_Classification_ID = ?)");
					parameters.add(discountSchemaLine.getM_Product_Classification_ID());
				}
				//	For Product as Parameter
				if(getProductId() > 0) {
					if(querywhereClause.length() > 0) {
						querywhereClause.append(" AND ");
					}
					querywhereClause.append("M_Product_ID = ?");
					parameters.add(getProductId());
				}
				//	for product category
				if(getProductCategoryId() != 0) {
					if(querywhereClause.length() > 0) {
						querywhereClause.append(" AND ");
					}
					querywhereClause.append("EXISTS(SELECT 1 FROM M_Product p WHERE p.M_Product_ID = M_Product_PO.M_Product_ID AND p.M_Product_Category_ID = ?)");
					parameters.add(getProductCategoryId());
				}
				//	for product group
				if(getProductGroupId() != 0) {
					if(querywhereClause.length() > 0) {
						querywhereClause.append(" AND ");
					}
					querywhereClause.append("EXISTS(SELECT 1 FROM M_Product p WHERE p.M_Product_ID = M_Product_PO.M_Product_ID AND p.M_Product_Group_ID = ?)");
					parameters.add(getProductGroupId());
				}
				//	for product class
				if(getProductClassId() != 0) {
					if(querywhereClause.length() > 0) {
						querywhereClause.append(" AND ");
					}
					querywhereClause.append("EXISTS(SELECT 1 FROM M_Product p WHERE p.M_Product_ID = M_Product_PO.M_Product_ID AND p.M_Product_Class_ID = ?)");
					parameters.add(getProductClassId());
				}
				//	for product classification
				if(getProductClassificationId() != 0) {
					if(querywhereClause.length() > 0) {
						querywhereClause.append(" AND ");
					}
					querywhereClause.append("EXISTS(SELECT 1 FROM M_Product p WHERE p.M_Product_ID = M_Product_PO.M_Product_ID AND p.M_Product_Classification_ID = ?)");
					parameters.add(getProductClassificationId());
				}
				//	Get from Product Purchasing
				new Query(getCtx(), I_M_Product_PO.Table_Name, querywhereClause.toString(), get_TrxName())
					.setParameters(parameters)
					.setClient_ID()
					.<MProductPO>list()
					.forEach(productPurchasing -> {
						//	Get conversion for Type
						String conversionRateKey = discountSchemaLine.getC_ConversionType_ID()  + "|" + productPurchasing.getC_Currency_ID() + "|" + priceList.getC_Currency_ID();
						BigDecimal conversionRate = null;
						if(!conversionRateMap.containsKey(conversionRateKey)) {
							conversionRate = MConversionRate.getRate(productPurchasing.getC_Currency_ID(), 
									priceList.getC_Currency_ID(), 
									priceListVersion.getValidFrom(), 
									discountSchemaLine.getC_ConversionType_ID(), 
									getAD_Client_ID(), 
									priceListVersion.getAD_Org_ID());
							if(conversionRate == null) {
								conversionRate = Env.ZERO;
							}
							//	Set
							conversionRateMap.put(conversionRateKey, conversionRate);
						} else {
							conversionRate = conversionRateMap.get(conversionRateKey);
						}
						//	Put combination
						combinationMap.put(productPurchasing.getM_Product_ID(), 
								new ProductCombination(productPurchasing.getM_Product_ID(), 
										conversionRate, 
										productPurchasing.getPriceList(), 
										productPurchasing.getPricePO(), 
										productPurchasing.getPricePO(), 
										discountSchemaLine));
					});
			});
		} else {
			MPriceList basePriceList = MPriceList.get(getCtx(), priceListVersion.getM_Pricelist_Version_Base().getM_PriceList_ID(), get_TrxName());
			discountSchemaLineList.forEach(discountSchemaLine -> {
				List<Object> parameters = new ArrayList<>();
				StringBuffer querywhereClause = new StringBuffer();
				querywhereClause = new StringBuffer("M_PriceList_Version_ID = ?");
				parameters.add(priceListVersion.getM_Pricelist_Version_Base_ID());
				//	Create where clause
				//	for product
				if(discountSchemaLine.getM_Product_ID() != 0) {
					if(querywhereClause.length() > 0) {
						querywhereClause.append(" AND ");
					}
					querywhereClause.append("M_Product_ID = ?");
					parameters.add(discountSchemaLine.getM_Product_ID());
				}
				//	for product category
				if(discountSchemaLine.getM_Product_Category_ID() != 0) {
					if(querywhereClause.length() > 0) {
						querywhereClause.append(" AND ");
					}
					querywhereClause.append("EXISTS(SELECT 1 FROM M_Product p WHERE p.M_Product_ID = M_ProductPrice.M_Product_ID AND p.M_Product_Category_ID = ?)");
					parameters.add(discountSchemaLine.getM_Product_Category_ID());
				}
				//	for product group
				if(discountSchemaLine.getM_Product_Group_ID() != 0) {
					if(querywhereClause.length() > 0) {
						querywhereClause.append(" AND ");
					}
					querywhereClause.append("EXISTS(SELECT 1 FROM M_Product p WHERE p.M_Product_ID = M_ProductPrice.M_Product_ID AND p.M_Product_Group_ID = ?)");
					parameters.add(discountSchemaLine.getM_Product_Group_ID());
				}
				//	for product class
				if(discountSchemaLine.getM_Product_Class_ID() != 0) {
					if(querywhereClause.length() > 0) {
						querywhereClause.append(" AND ");
					}
					querywhereClause.append("EXISTS(SELECT 1 FROM M_Product p WHERE p.M_Product_ID = M_ProductPrice.M_Product_ID AND p.M_Product_Class_ID = ?)");
					parameters.add(discountSchemaLine.getM_Product_Class_ID());
				}
				//	for product classification
				if(discountSchemaLine.getM_Product_Classification_ID() != 0) {
					if(querywhereClause.length() > 0) {
						querywhereClause.append(" AND ");
					}
					querywhereClause.append("EXISTS(SELECT 1 FROM M_Product p WHERE p.M_Product_ID = M_ProductPrice.M_Product_ID AND p.M_Product_Classification_ID = ?)");
					parameters.add(discountSchemaLine.getM_Product_Classification_ID());
				}
				//	For Product as Parameter
				if(getProductId() > 0) {
					if(querywhereClause.length() > 0) {
						querywhereClause.append(" AND ");
					}
					querywhereClause.append("M_Product_ID = ?");
					parameters.add(getProductId());
				}
				//	for product category
				if(getProductCategoryId() != 0) {
					if(querywhereClause.length() > 0) {
						querywhereClause.append(" AND ");
					}
					querywhereClause.append("EXISTS(SELECT 1 FROM M_Product p WHERE p.M_Product_ID = M_Product_PO.M_Product_ID AND p.M_Product_Category_ID = ?)");
					parameters.add(getProductCategoryId());
				}
				//	for product group
				if(getProductGroupId() != 0) {
					if(querywhereClause.length() > 0) {
						querywhereClause.append(" AND ");
					}
					querywhereClause.append("EXISTS(SELECT 1 FROM M_Product p WHERE p.M_Product_ID = M_Product_PO.M_Product_ID AND p.M_Product_Group_ID = ?)");
					parameters.add(getProductGroupId());
				}
				//	for product class
				if(getProductClassId() != 0) {
					if(querywhereClause.length() > 0) {
						querywhereClause.append(" AND ");
					}
					querywhereClause.append("EXISTS(SELECT 1 FROM M_Product p WHERE p.M_Product_ID = M_Product_PO.M_Product_ID AND p.M_Product_Class_ID = ?)");
					parameters.add(getProductClassId());
				}
				//	for product classification
				if(getProductClassificationId() != 0) {
					if(querywhereClause.length() > 0) {
						querywhereClause.append(" AND ");
					}
					querywhereClause.append("EXISTS(SELECT 1 FROM M_Product p WHERE p.M_Product_ID = M_Product_PO.M_Product_ID AND p.M_Product_Classification_ID = ?)");
					parameters.add(getProductClassificationId());
				}
				//	Get from Product Purchasing
				new Query(getCtx(), I_M_ProductPrice.Table_Name, querywhereClause.toString(), get_TrxName())
					.setParameters(parameters)
					.setClient_ID()
					.<MProductPrice>list()
					.forEach(productPrice -> {
						//	Get conversion for Type
						String conversionRateKey = discountSchemaLine.getC_ConversionType_ID()  + "|" + basePriceList.getC_Currency_ID() + "|" + priceList.getC_Currency_ID();
						BigDecimal conversionRate = null;
						if(!conversionRateMap.containsKey(conversionRateKey)) {
							conversionRate = MConversionRate.getRate(basePriceList.getC_Currency_ID(), 
									priceList.getC_Currency_ID(), 
									priceListVersion.getValidFrom(), 
									discountSchemaLine.getC_ConversionType_ID(), 
									getAD_Client_ID(), 
									priceListVersion.getAD_Org_ID());
							if(conversionRate == null) {
								conversionRate = Env.ZERO;
							}
							//	Set
							conversionRateMap.put(conversionRateKey, conversionRate);
						} else {
							conversionRate = conversionRateMap.get(conversionRateKey);
						}
						//	Put combination
						combinationMap.put(productPrice.getM_Product_ID(), 
								new ProductCombination(productPrice.getM_Product_ID(), 
										conversionRate, 
										productPrice.getPriceList(),
										productPrice.getPriceStd(),
										productPrice.getPriceLimit(),
										discountSchemaLine));
					});
			});
		}
		//	Calculate and save map
		combinationMap.values().forEach(combination -> combination.calculate().save(priceListVersion));
		return "OK";

	} // del doIt
	
	
	/**
	 * 
	 *	@author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
	 *	Product combination
	 */
	private class ProductCombination {
		private int productId;
		private BigDecimal priceStandard;
		private BigDecimal priceList;
		private BigDecimal priceLimit;
		private MDiscountSchemaLine discountSchemaLine;
		//	Calculated
		private BigDecimal conversionRate = Env.ZERO;
		private BigDecimal calculatedPriceStandard = Env.ZERO;
		private BigDecimal calculatedPriceList = Env.ZERO;
		private BigDecimal calculatedPriceLimit = Env.ZERO;
		
		/**
		 * Default constructor
		 * @param productId
		 * @param currencyId
		 * @param priceStandard
		 * @param priceList
		 * @param priceLimit
		 * @param discountSchemaLine
		 */
		public ProductCombination(int productId, BigDecimal conversionRate, BigDecimal priceList, BigDecimal priceStandard, BigDecimal priceLimit, MDiscountSchemaLine discountSchemaLine) {
			this.productId = productId;
			this.conversionRate = conversionRate;
			BigDecimal defaultPrice = Optional.ofNullable(priceList).orElseGet(() -> {
				//	Standard
				if(Optional.ofNullable(priceStandard).isPresent()) {
					return priceStandard;
				}
				//	Limit
				if(Optional.ofNullable(priceLimit).isPresent()) {
					return priceLimit;
				}
				//	Default
				return Env.ZERO;
			});
			this.priceList = Optional.ofNullable(priceList).orElse(defaultPrice);
			this.priceStandard = Optional.ofNullable(priceStandard).orElse(defaultPrice);
			this.priceLimit = Optional.ofNullable(priceLimit).orElse(defaultPrice);
			this.discountSchemaLine = discountSchemaLine;
		}
		
		/**
		 * Calculate priced based on reference
		 */
		public ProductCombination calculate() {
			//	Old code
			//	PriceList = (DECODE( '', 'S', PriceStd, 'X', PriceLimit, PriceList) + ?) * (1 - ?/100), 
			//	PriceStd = (DECODE('', 'L', PriceList, 'X', PriceLimit, PriceStd)  + ?) * (1 - ?/100), 
			//	PriceLimit = (DECODE('', 'L', PriceList, 'S', PriceStd, PriceLimit)  + ?) * (1 - ? /100)
			//	Get discount schema
			BigDecimal listAddAmt = getDiscountSchemaLine().getList_AddAmt();
			BigDecimal listDiscount = getDiscountSchemaLine().getList_Discount();
			BigDecimal stdAddAmt = getDiscountSchemaLine().getStd_AddAmt();
			BigDecimal stdDiscount = getDiscountSchemaLine().getStd_Discount();
			BigDecimal limitAddAmt = getDiscountSchemaLine().getLimit_AddAmt();
			BigDecimal limitDiscount = getDiscountSchemaLine().getLimit_Discount();
			//	For price list
			if(getDiscountSchemaLine().getList_Base().equals(X_M_DiscountSchemaLine.LIST_BASE_StandardPrice)) {
				calculatedPriceList = getPriceStandard();
			} else if(getDiscountSchemaLine().getList_Base().equals(X_M_DiscountSchemaLine.LIST_BASE_LimitPOPrice)) {
				calculatedPriceList = getPriceLimit();
			} else {
				calculatedPriceList = getPriceList();
			}
			calculatedPriceList = calculatedPriceList.multiply(getConversionRate());
			calculatedPriceList = calculatedPriceList.add(listAddAmt);
			calculatedPriceList = calculatedPriceList.multiply(Env.ONE.subtract(listDiscount.divide(Env.ONEHUNDRED)));
			//	For Price Standard
			if(getDiscountSchemaLine().getStd_Base().equals(X_M_DiscountSchemaLine.STD_BASE_ListPrice)) {
				calculatedPriceStandard = getPriceList();
			} else if(getDiscountSchemaLine().getStd_Base().equals(X_M_DiscountSchemaLine.STD_BASE_LimitPOPrice)) {
				calculatedPriceStandard = getPriceLimit();
			} else {
				calculatedPriceStandard = getPriceStandard();
			}
			calculatedPriceStandard = calculatedPriceStandard.multiply(getConversionRate());
			calculatedPriceStandard = calculatedPriceStandard.add(stdAddAmt);
			calculatedPriceStandard = calculatedPriceStandard.multiply(Env.ONE.subtract(stdDiscount.divide(Env.ONEHUNDRED)));
			//	For Price Limit
			if(getDiscountSchemaLine().getLimit_Base().equals(X_M_DiscountSchemaLine.LIMIT_BASE_ListPrice)) {
				calculatedPriceLimit = getPriceList();
			} else if(getDiscountSchemaLine().getLimit_Base().equals(X_M_DiscountSchemaLine.LIMIT_BASE_StandardPrice)) {
				calculatedPriceLimit = getPriceStandard();
			} else {
				calculatedPriceLimit = getPriceLimit();
			}
			calculatedPriceLimit = calculatedPriceLimit.multiply(getConversionRate());
			calculatedPriceLimit = calculatedPriceLimit.add(limitAddAmt);
			calculatedPriceLimit = calculatedPriceLimit.multiply(Env.ONE.subtract(limitDiscount.divide(Env.ONEHUNDRED)));
			return this;
		}
		
		/**
		 * Save price list, use it after calculate
		 * @param priceListVersion
		 * @return
		 */
		public boolean save(MPriceListVersion priceListVersion) {
			try {
				MProductPrice productPrice = null;
				if(!isDeleteOld()) {
					productPrice = MProductPrice.get(getCtx(), priceListVersion.getM_PriceList_Version_ID(), getProductId(), get_TrxName());
				}
				if(productPrice != null) {
					productPrice.setPrices(getCalculatedPriceList(), 
							getCalculatedPriceStandard(), 
							getCalculatedPriceLimit());
				} else {
					productPrice = new MProductPrice(priceListVersion, 
							getProductId(), 
							getCalculatedPriceList(), 
							getCalculatedPriceStandard(), 
							getCalculatedPriceLimit());
				}
				//	Save
				productPrice.saveEx();
			} catch (Exception e) {
				log.severe(e.getLocalizedMessage());
			}
			return true;
		}
		
		/**
		 * @return the productId
		 */
		public final int getProductId() {
			return productId;
		}
		

		/**
		 * @return the discountSchemaLine
		 */
		public final MDiscountSchemaLine getDiscountSchemaLine() {
			return discountSchemaLine;
		}
		
		
		/**
		 * @return the priceStandard
		 */
		public final BigDecimal getPriceStandard() {
			return priceStandard;
		}
		
		/**
		 * @return the priceList
		 */
		public final BigDecimal getPriceList() {
			return priceList;
		}

		/**
		 * @return the priceLimit
		 */
		public final BigDecimal getPriceLimit() {
			return priceLimit;
		}

		/**
		 * @return the calculatedPriceStandard
		 */
		public final BigDecimal getCalculatedPriceStandard() {
			return calculatedPriceStandard;
		}

		/**
		 * @return the calculatedPriceList
		 */
		public final BigDecimal getCalculatedPriceList() {
			return calculatedPriceList;
		}

		/**
		 * @return the calculatedPriceLimit
		 */
		public final BigDecimal getCalculatedPriceLimit() {
			return calculatedPriceLimit;
		}
		
		/**
		 * @return the conversionRate
		 */
		public final BigDecimal getConversionRate() {
			return conversionRate;
		}

		@Override
		public String toString() {
			return "ProductCombination [productId=" + productId + ", priceStandard=" + priceStandard + ", priceList="
					+ priceList + ", priceLimit=" + priceLimit + ", discountSchemaLine=" + discountSchemaLine
					+ ", conversionRate=" + conversionRate + ", calculatedPriceStandard=" + calculatedPriceStandard
					+ ", calculatedPriceList=" + calculatedPriceList + ", calculatedPriceLimit=" + calculatedPriceLimit
					+ "]";
		}
	}

	private void raiseError(String string, String sql) throws Exception {
		
		// DB.rollback(false, get_TrxName());
		String msg = string;
		ValueNamePair pp = CLogger.retrieveError();
		if (pp != null)
			msg = pp.getName() + " - ";
		msg += sql;
		throw new AdempiereUserError(msg);
	}
} // M_PriceList_Create