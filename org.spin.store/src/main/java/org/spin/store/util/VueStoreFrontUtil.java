/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 or later of the                                  *
 * GNU General Public License as published                                    *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * Copyright (C) 2003-2019 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpya.com                                  *
 *****************************************************************************/
package org.spin.store.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.I_M_Package;
import org.compiere.model.I_W_Basket;
import org.compiere.model.I_W_BasketLine;
import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MClient;
import org.compiere.model.MDocType;
import org.compiere.model.MPackage;
import org.compiere.model.MPackageLine;
import org.compiere.model.MPriceList;
import org.compiere.model.MProduct;
import org.compiere.model.MProductPricing;
import org.compiere.model.MStore;
import org.compiere.model.MTax;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.model.X_W_Basket;
import org.compiere.model.X_W_BasketLine;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.TimeUtil;
import org.compiere.util.Util;

/**
 * Added for handle custom columns for ADempiere core
 * @author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 */
public class VueStoreFrontUtil {
	/**	Store Definition	*/
	/**	Cache Server Provider	*/
	public static final String COLUMNNAME_CacheServerProvider_ID = "CacheServerProvider_ID";
	/**	Customer Role	*/
	public static final String COLUMNNAME_CustomerRole_ID = "CustomerRole_ID";
	/**	Template for Business Partner	*/
	public static final String COLUMNNAME_C_TemplateBPartner_ID = "C_TemplateBPartner_ID";
	/**	Basket	*/
	/**	Line Tax ID	*/
	public static final String COLUMNNAME_C_Tax_ID = "C_Tax_ID";
	/**	Delivery Via	*/
	public static final String COLUMNNAME_DeliveryViaRule = "DeliveryViaRule";
	/**	Freight Cost Rule	*/
	public static final String COLUMNNAME_FreightCostRule = "FreightCostRule";
	/**	Line Discount %	*/
	public static final String COLUMNNAME_LineDiscount = "LineDiscount";
	/**	Line Discount	*/
	public static final String COLUMNNAME_LineDiscountAmt = "LineDiscountAmt";
	/**	Line List Amount	*/
	public static final String COLUMNNAME_LineListAmt = "LineListAmt";
	/**	Line Amount	*/
	public static final String COLUMNNAME_LineNetAmt = "LineNetAmt";
	/**	Line Total	*/
	public static final String COLUMNNAME_LineTotalAmt = "LineTotalAmt";
	/**	Freight Category	*/
	public static final String COLUMNNAME_M_FreightCategory_ID = "M_FreightCategory_ID";
	/**	Shipper	*/
	public static final String COLUMNNAME_M_Shipper_ID = "M_Shipper_ID";
	/**	Warehouse	*/
	public static final String COLUMNNAME_M_Warehouse_ID = "M_Warehouse_ID";
	/**	List Price	*/
	public static final String COLUMNNAME_PriceList = "PriceList";
	/**	Tax Amount	*/
	public static final String COLUMNNAME_TaxAmt = "TaxAmt";
	/**	Last Name for User	*/
	public static final String COLUMNNAME_LastName = "LastName";
	/**	Default Shipping Address	*/
	public static final String COLUMNNAME_IsDefaultShipping = "IsDefaultShipping";
	/**	Default Billing Address	*/
	public static final String COLUMNNAME_IsDefaultBilling = "IsDefaultBilling";
	
	/**
	 * Get Default Store
	 * @param organizationId
	 * @return
	 */
	public static MStore getDefaultStore(int organizationId) {
		List<MStore> stores = Arrays.asList(MStore.getOfClient(MClient.get(Env.getCtx())));
		AtomicInteger organizationForSearchId = new AtomicInteger(organizationId);
		if(organizationId == 0) {
			organizationForSearchId.set(Env.getAD_Org_ID(Env.getCtx()));
		}
		//	Reference
		AtomicReference<MStore> storeReceived = new AtomicReference<MStore>();
		stores
			.stream()
			.filter(store -> store.getAD_Org_ID() == organizationForSearchId.get())
			.findFirst()
			.ifPresent(store -> storeReceived.set(store));
		if(storeReceived.get() == null) {
			stores
				.stream()
				.findFirst()
				.ifPresent(store -> storeReceived.set(store));
		}
		return storeReceived.get();
	}
	
	/**
	 * Get price from product
	 * @param product
	 * @return
	 */
	public static BigDecimal getPriceStd(MProduct product, int priceListId) {
		MProductPricing productPricing = getProductPricing(product, priceListId);
		if(productPricing == null) {
			return Env.ZERO;
		}
		return productPricing.getPriceStd().setScale(productPricing.getPrecision());
	}
	
	/**
	 * Get price from product
	 * @param product
	 * @return
	 */
	public static BigDecimal getPriceList(MProduct product, int priceListId) {
		MProductPricing productPricing = getProductPricing(product, priceListId);
		if(productPricing == null) {
			return Env.ZERO;
		}
		return productPricing.getPriceList().setScale(productPricing.getPrecision());
	}
	
	/**
	 * Get Product Pricing
	 * @param product
	 * @param priceListId
	 * @return
	 */
	public static MProductPricing getProductPricing(MProduct product, int priceListId) {
		MPriceList priceList = MPriceList.get(product.getCtx(), priceListId, product.get_TrxName());
		//	Get Valid From
		Timestamp validFrom = TimeUtil.getDay(System.currentTimeMillis());
		//	Get Price
		MProductPricing productPricing = new MProductPricing(product.getM_Product_ID(), 0, Env.ZERO, true, product.get_TrxName());
		productPricing.setM_PriceList_ID(priceList.getM_PriceList_ID());
		productPricing.setPriceDate(validFrom);
		return productPricing;
	}
	
	/**
	 * Get Tax Rate
	 * @param taxCategoryId
	 * @return
	 */
	public static MTax getTax(int taxCategoryId) {
		Optional<MTax> optionalTax = Arrays.asList(MTax.getAll(Env.getCtx()))
			.stream()
			.filter(tax -> tax.getC_TaxCategory_ID() == taxCategoryId 
								&& (tax.isSalesTax() 
										|| (!Util.isEmpty(tax.getSOPOType()) 
												&& (tax.getSOPOType().equals(MTax.SOPOTYPE_Both) 
														|| tax.getSOPOType().equals(MTax.SOPOTYPE_SalesTax)))))
			.findFirst();
			//	Validate
			if(optionalTax.isPresent()) {
				return optionalTax.get();
			}
		return null;
	}
	
	/**
	 * Get Tax Rate
	 * @param taxCategoryId
	 * @return
	 */
	public static BigDecimal getTaxRate(int taxCategoryId) {
		MTax tax = getTax(taxCategoryId);
		BigDecimal taxRate = Env.ZERO;
		if(tax == null) {
			return taxRate;
		}
		return tax.getRate();
	}
	
	/**
	 * Set default values to basket line from environment
	 * @param basketLine
	 */
	public static void setBasketLineDefaultValues(X_W_BasketLine basketLine) {
		if(basketLine == null) {
			return;
		}
		//	
		MStore store = getDefaultStore(basketLine.getAD_Org_ID());
		MProduct product = MProduct.get(basketLine.getCtx(), basketLine.getM_Product_ID());
		MTax tax = getTax(product.getC_TaxCategory_ID());
		BigDecimal quantity = basketLine.getQty();
		//	
		MProductPricing productPricing = getProductPricing(product, store.getM_PriceList_ID());
		BigDecimal priceListAmount = productPricing.getPriceList().setScale(productPricing.getPrecision());
		BigDecimal priceAmount = productPricing.getPriceStd().setScale(productPricing.getPrecision());
		BigDecimal discount = productPricing.getDiscount();
		BigDecimal lineListAmount = priceListAmount.multiply(quantity).setScale(productPricing.getPrecision(), RoundingMode.HALF_UP);
		BigDecimal lineDiscountAmount = lineListAmount.multiply(discount).divide(Env.ONEHUNDRED, RoundingMode.HALF_UP).setScale(productPricing.getPrecision(), RoundingMode.HALF_UP);
		BigDecimal lineNetAmount = priceAmount.multiply(quantity).setScale(productPricing.getPrecision(), RoundingMode.HALF_UP);
		BigDecimal taxAmount = lineNetAmount.multiply(tax.getRate()).divide(Env.ONEHUNDRED, RoundingMode.HALF_UP);
		BigDecimal lineTotalAmount = lineNetAmount.add(taxAmount);
		//	Set values
		basketLine.set_ValueOfColumn(COLUMNNAME_M_Warehouse_ID, store.getM_Warehouse_ID());
		basketLine.set_ValueOfColumn(COLUMNNAME_PriceList, priceListAmount);
		basketLine.set_ValueOfColumn(COLUMNNAME_LineDiscount, discount);
		basketLine.set_ValueOfColumn(COLUMNNAME_LineDiscountAmt, lineDiscountAmount);
		basketLine.set_ValueOfColumn(COLUMNNAME_LineListAmt, lineListAmount);
		basketLine.set_ValueOfColumn(COLUMNNAME_LineNetAmt, lineNetAmount);
		basketLine.set_ValueOfColumn(COLUMNNAME_TaxAmt, taxAmount);
		basketLine.set_ValueOfColumn(COLUMNNAME_LineTotalAmt, lineTotalAmount);
		basketLine.set_ValueOfColumn(COLUMNNAME_C_Tax_ID, tax.getC_Tax_ID());
	}
	
	/**
	 * Get Packages from a basket
	 * @param basketId
	 * @param transactionName
	 * @return
	 */
	public static List<MPackage> getPackagesFromBasket(Properties context, int basketId, String transactionName) {
		return new Query(context, I_M_Package.Table_Name, I_W_Basket.COLUMNNAME_W_Basket_ID + " = ?", transactionName)
				.setParameters(basketId)
				.list();
	}
	
	/**
	 * Delete Package Line from Basket Line
	 * @param context
	 * @param basketLineId
	 * @param transactionName
	 */
	public static void deletePackageLineFromBasketLine(Properties context, int basketLineId, String transactionName) {
		List<MPackageLine> packageLines = new Query(Env.getCtx(), MPackageLine.Table_Name, X_W_BasketLine.COLUMNNAME_W_BasketLine_ID + " = ?", transactionName)
				.setParameters(basketLineId)
				.list();
		packageLines.forEach(packageLine -> packageLine.deleteEx(true));
	}
	
	/**
	 * Delete Package from Basket
	 * @param context
	 * @param basketId
	 * @param transactionName
	 */
	public static void deletePackageFromBasket(Properties context, int basketId, String transactionName) {
		List<MPackage> packages = new Query(Env.getCtx(), MPackage.Table_Name, X_W_BasketLine.COLUMNNAME_W_Basket_ID + " = ?", transactionName)
				.setParameters(basketId)
				.list();
		packages.forEach(packageToDelete -> packageToDelete.deleteEx(true));
	}
	
	/**
	 * Create packages from basket and shipping method and warehouse
	 * @param basketId
	 * @param transactionName
	 * @return
	 */
	public static List<MPackage> createPackagesFromBasket(Properties context, int basketId, String transactionName) {
		X_W_Basket basket = new X_W_Basket(context, basketId, transactionName);
		List<MPackage> packages = getPackagesFromBasket(context, basketId, transactionName);
		if(basket.getC_BPartner_ID() <= 0) {
			return packages;
		}
		MBPartner customer = MBPartner.get(context, basket.getC_BPartner_ID());
		List<MBPartnerLocation> locations = Arrays.asList(customer.getLocations(true));
		Optional<MBPartnerLocation> maybeLocation = Optional.of(locations.stream().filter(location -> location.isShipTo()).findFirst()).orElse(locations.stream().findFirst());
		if(packages == null) {
			packages = new ArrayList<MPackage>();
		}
		//	Validate
		if(basket.getC_BPartner_ID() <= 0
				|| !maybeLocation.isPresent()) {
			return packages;
		}
		MStore store = getDefaultStore(basket.getAD_Org_ID());
		if(store == null) {
			throw new AdempiereException("@W_Store_ID@ @NotFound@");
		}
		Map<String, MPackage> groupingPackages = new HashMap<String, MPackage>();
		packages.forEach(packageToGrouping -> {
			String key = packageToGrouping.getM_Warehouse_ID()
					+ "|" + packageToGrouping.getDeliveryViaRule() 
					+ "|" + packageToGrouping.getFreightCostRule() 
					+ "|" + packageToGrouping.getM_FreightCategory_ID() 
					+ "|" + packageToGrouping.getM_Shipper_ID(); 
			groupingPackages.put(key, packageToGrouping);
		});
		//	Create
		List<X_W_BasketLine> basketLines = new Query(Env.getCtx(), X_W_BasketLine.Table_Name, X_W_BasketLine.COLUMNNAME_W_Basket_ID + " = ? "
				+ "AND " + COLUMNNAME_M_Shipper_ID + " IS NOT NULL", transactionName)
				.setParameters(basketId)
				.list();
		basketLines.forEach(basketLine -> {
			String key = basketLine.get_ValueAsInt(COLUMNNAME_M_Warehouse_ID)
					+ "|" + basketLine.get_ValueAsString(COLUMNNAME_DeliveryViaRule) 
					+ "|" + basketLine.get_ValueAsString(COLUMNNAME_FreightCostRule)
					+ "|" + basketLine.get_ValueAsInt(COLUMNNAME_M_FreightCategory_ID) 
					+ "|" + basketLine.get_ValueAsInt(COLUMNNAME_M_Shipper_ID);
			MPackage packageToCreate = groupingPackages.get(key);
			//	Create
			if(packageToCreate == null) {
				packageToCreate = new MPackage(context, 0, transactionName);
			}
			//	Set Reference
			if(basketLine.getAD_Org_ID() > 0) {
				packageToCreate.setAD_Org_ID(basketLine.getAD_Org_ID());
			} else {
				packageToCreate.setAD_Org_ID(store.getAD_Org_ID());
			}
			packageToCreate.setM_Warehouse_ID(basketLine.get_ValueAsInt(COLUMNNAME_M_Warehouse_ID));
			packageToCreate.setDateDoc(new Timestamp(System.currentTimeMillis()));
			if(basketLine.get_ValueAsInt(COLUMNNAME_M_Shipper_ID) > 0) {
				packageToCreate.setM_Shipper_ID(basketLine.get_ValueAsInt(COLUMNNAME_M_Shipper_ID));
			}
			//	Set default values for references
			packageToCreate.setC_BPartner_ID(basket.getC_BPartner_ID());
			packageToCreate.setC_BPartner_Location_ID(maybeLocation.get().getC_BPartner_Location_ID());
			packageToCreate.setDeliveryViaRule(basketLine.get_ValueAsString(COLUMNNAME_DeliveryViaRule));
			if(!Util.isEmpty(basketLine.get_ValueAsString(COLUMNNAME_FreightCostRule))) {
				packageToCreate.setFreightCostRule(basketLine.get_ValueAsString(COLUMNNAME_FreightCostRule));
			}
			if(basketLine.get_ValueAsInt(COLUMNNAME_M_FreightCategory_ID) > 0) {
				packageToCreate.setM_FreightCategory_ID(basketLine.get_ValueAsInt(COLUMNNAME_M_FreightCategory_ID));
			}
			//	Custom reference
			packageToCreate.set_ValueOfColumn(I_W_Basket.COLUMNNAME_W_Basket_ID, basket.getW_Basket_ID());
			Optional<Integer> maybeDocumentType = Optional.of(MDocType.getDocType(MDocType.DOCBASETYPE_Package, packageToCreate.getAD_Org_ID()));
			if(maybeDocumentType.isPresent()) {
				packageToCreate.setC_DocType_ID(maybeDocumentType.get());
			}
			packageToCreate.setSalesRep_ID(store.getSalesRep_ID());
			//	
			packageToCreate.saveEx(transactionName);
			//	Set lines
			List<MPackageLine> packageLines = packageToCreate.getLines(true);
			Optional<MPackageLine> maybePackage = packageLines
					.stream()
					.filter(lineOfPackage -> lineOfPackage.get_ValueAsInt(I_W_BasketLine.COLUMNNAME_W_BasketLine_ID) == basketLine.getW_BasketLine_ID())
					.findFirst();
			//	
			MPackageLine lineToCreate = maybePackage.orElse(new MPackageLine(packageToCreate));
			//	Add values
			MProduct product = MProduct.get(context, basketLine.getM_Product_ID());
			lineToCreate.set_ValueOfColumn(I_W_BasketLine.COLUMNNAME_W_BasketLine_ID, basketLine.getW_BasketLine_ID());
			lineToCreate.setQty(basketLine.getQty());
			lineToCreate.setWeight(Optional.ofNullable(product.getWeight()).orElse(Env.ZERO).multiply(basketLine.getQty()));
			lineToCreate.setVolume(Optional.ofNullable(product.getVolume()).orElse(Env.ZERO).multiply(basketLine.getQty()));
			lineToCreate.setDescription(Msg.parseTranslation(context, "@Created@ @from@ @W_Basket_ID@: (" + basketLine.getW_Basket_ID() + ") - " + basketLine.getLine() + ": " + basketLine.getProduct()));
			lineToCreate.saveEx(transactionName);
			//	Set to Group
			groupingPackages.put(key, packageToCreate);
		});
		//	Process all
		return packages;
	}
	
	/**
	 * Get a Template BPartner
	 * @param context
	 * @param templateId
	 * @param transactionaName
	 * @return
	 */
	public static MBPartner getTemplate(Properties context, int templateId, String transactionaName) {
		MBPartner source = MBPartner.get(context, templateId);
		if (source == null) {
			source = new MBPartner (context, 0, null);
		}
		MBPartner target = new MBPartner(context, 0, transactionaName);
		PO.copyValues(source, target);
		//	Reset
		target.setTaxID("");
		target.setValue("");
		target.setName("");
		target.setName2(null);
		target.setDUNS("");
		target.setFirstSale(null);
		//
		target.setSO_CreditLimit (Env.ZERO);
		target.setSO_CreditUsed (Env.ZERO);
		target.setTotalOpenBalance (Env.ZERO);
		//
		target.setActualLifeTimeValue(Env.ZERO);
		target.setPotentialLifeTimeValue(Env.ZERO);
		target.setAcqusitionCost(Env.ZERO);
		target.setShareOfCustomer(0);
		target.setSalesVolume(0);
		//	Return
		return target;
	}
}
