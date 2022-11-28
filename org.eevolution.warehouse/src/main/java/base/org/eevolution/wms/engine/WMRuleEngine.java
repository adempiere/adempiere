/**********************************************************************
 * This file is part of Adempiere ERP Bazaar                          * 
 * http://www.adempiere.org                                           * 
 *                                                                    * 
 * Copyright (C) Victor Perez	                                      * 
 * Copyright (C) Contributors                                         * 
 *                                                                    * 
 * This program is free software; you can redistribute it and/or      * 
 * modify it under the terms of the GNU General Public License        * 
 * as published by the Free Software Foundation; either version 2     * 
 * of the License, or (at your option) any later version.             * 
 *                                                                    * 
 * This program is distributed in the hope that it will be useful,    * 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of     * 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the       * 
 * GNU General Public License for more details.                       * 
 *                                                                    * 
 * You should have received a copy of the GNU General Public License  * 
 * along with this program; if not, write to the Free Software        * 
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,         * 
 * MA 02110-1301, USA.                                                * 
 *                                                                    * 
 * Contributors:                                                      * 
 *  - Victor Perez (victor.perez@e-evolution.com	 )                *
 *                                                                    *
 * Sponsors:                                                          *
 *  - e-Evolution (http://www.e-evolution.com/)                       *
 **********************************************************************/

package org.eevolution.wms.engine;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.TreeSet;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.Adempiere;
import org.compiere.model.MBPartner;
import org.compiere.model.MLocator;
import org.compiere.model.MProduct;
import org.compiere.model.MStorage;
import org.compiere.model.Query;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.Login;
import org.eevolution.wms.model.MWMDefinition;
import org.eevolution.wms.model.MWMInOutBound;
import org.eevolution.wms.model.MWMInOutBoundLine;
import org.eevolution.wms.model.MWMRule;
import org.eevolution.wms.model.MWMStrategy;
import org.eevolution.wms.model.MWMStrategyDetail;

/**
 * Warehouse Engine
 * 
 * @author victor.perez@e-evolution.com, www.e-evolution.com
 * 
 */
public class WMRuleEngine {

	/** Logger */
	protected CLogger log = CLogger.getCLogger(getClass());
	private static WMRuleEngine s_instance = null;
	/** WMRule (negative cache) */
	private static TreeSet<String> ruleNoImplementation = new TreeSet<String>();
	/** Cache Rules */
	private HashMap<String, WMRuleInterface> rulesImplementation = new HashMap<String, WMRuleInterface>();

	/** Package for Warehouse Rule classes */
	private String packageName = "org.eevolution.wms.engine";

	/**
	 * Get Warehouse Rules
	 * @param ctx
	 * @param clientId
	 * @param trxName
	 * @return Collection of the MWMRules
	 */
	public static List<MWMRule> getWMRules(Properties ctx,
			int clientId, String trxName) {
		String whereClause = "";
		ArrayList<Object> parameters = new ArrayList();
		if (clientId > 0) {
			whereClause = MWMRule.COLUMNNAME_AD_Client_ID + "=?";
			parameters.add(clientId);
		} else {
			whereClause = null;
		}

		return new Query(ctx, MWMRule.Table_Name, whereClause, trxName)
				.setOnlyActiveRecords(true)
				.setParameters(new Object[] { parameters }).<MWMRule> list();
	}

	/**
	 * Apply the Warehouse Rule by InOut Bound line and Bound Type
	 * 
	 * @param inOutBounds
	 * @param boundType
	 */
	public static void applyWMRule(List<MWMInOutBound> inOutBounds,
			String boundType) {
		for (MWMInOutBound inOutBound : inOutBounds) {
			for (MWMDefinition definition : MWMDefinition.getAll(inOutBound.getCtx(),
					inOutBound.get_TrxName())) {
				WMRuleEngine.validateDefinition(inOutBound, definition, boundType);
			}
		}

	}

	/**
	 * Validate Definition for InOut Bound
	 * 
	 * @param inOutBound
	 * @param definition
	 * @param boundType
	 */
	private static void validateDefinition(MWMInOutBound inOutBound,
			MWMDefinition definition, String boundType) {
		for (MWMStrategy strategy : MWMStrategy.getByBoundType(inOutBound.getCtx(),
				boundType, inOutBound.get_TrxName())) {
			for (MWMStrategyDetail strategyLine : strategy.getStrategyDetail()) {
				MWMRule rule = new MWMRule(strategyLine.getCtx(), strategyLine.getWM_Rule_ID(), strategyLine.get_TrxName());
			}
		}
	}

	public WMRuleEngine() {
		/*
		 * CacheMgt.get().register(new CacheInterface() { public int reset() {
		 * registerRules(true); return 1; } public int size() { return
		 * m_WMRules.size(); } });
		 */
		registerRules(true);
	}

	/**
	 * register the WM Rule implemetation
	 * 
	 * @param reset
	 */
	protected void registerRules(boolean reset) {
		if (reset) {
			rulesImplementation.clear();
		}

		WMRuleInterface rule = getWMRuleFactory(WMRuleFIFO.class
				.getSimpleName());
	}

	/**
	 * get WMRule Engine like singleton instance
	 * 
	 * @return WM Rule Engine
	 */
	public static WMRuleEngine get() {
		if (s_instance == null) {
			s_instance = new WMRuleEngine();
		}
		return s_instance;
	}

	/**
	 * WMRule factory instance
	 * 
	 * @return document factory instance
	 */
	public WMRuleInterface getWMRuleFactory(String className) {
		if (rulesImplementation.containsKey(className)) {
			return (WMRuleInterface) rulesImplementation.get(className);
		}
		// Check negative cache:
		/*
		 * if (s_RuleNoImplement.contains(rule.getInOutboundClass())) {
		 * log.info("Ignore "+rule.getInOutboundClass()+
		 * " doesn't have accounting implementation (WMRule* class)"); }
		 */
		//
		try {
			Class<? extends WMRuleInterface> cl = getClass(className);
			//
			// Check <constructor>():
			Constructor<?> constructor = null;
			try {
				constructor = cl.getDeclaredConstructor();
			} catch (Exception e) {
				log.fine("Not found <WMRule>()");
			}
			if (constructor != null) {
				WMRuleInterface rule = (WMRuleInterface) constructor
						.newInstance();
				rulesImplementation.put(className, rule);
				return rule;
			}
			//
			// Check <constructor>():
			constructor = cl.getDeclaredConstructor();
			WMRuleInterface rule = (WMRuleInterface) constructor.newInstance();
			rulesImplementation.put(className, rule);
			return rule;
		} catch (ClassNotFoundException e) {
			ruleNoImplementation.add(className); // Add to negative cache (won't
												// try to load again)
		} catch (Throwable e) {
			throw new AdempiereException(e);
		}
		return null;
	}

	/**
	 * @return package name used for warehouse rule classes
	 */
	public String getPackageName() {
		return this.packageName;
	}

	@SuppressWarnings("unchecked")
	protected <T extends WMRuleInterface> Class<T> getClass(String className)
			throws ClassNotFoundException {
		return (Class<T>) Class.forName(getPackageName() + "." + className);
	}

	/**
	 * getClassName for WM Rule
	 * 
	 * @param rule
	 * @return String ClassName implementation
	 */
	protected String getClassName(MWMRule rule) {
		if (MWMRule.INOUTBOUNDRULE_ForTheMaterialOldestUsingFIFO.equals(rule
				.getInOutBoundRule())) {
			return WMRuleFIFO.class.getSimpleName();
		} else if (MWMRule.INOUTBOUNDRULE_CustumInterface.equals(rule
				.getInOutBoundRule())) {
			return rule.getInOutboundClass();
		} else {
			throw new AdempiereException(rule.getInOutBoundRule()
					+ "is not implement");
		}
	}

	/**
	 * Get Locators based on InOut Bound Order Line and Area Type and Section Type
	 * @param inOutBoundLine InOutBound Line
	 * @param warehouseAreaTypeId Warehouse Area Type Id
	 * @param warehouseSectionTypeId Warehouse Section Type Id
     * @return List<MLocator>
     */
	public List<MLocator> getLocator(
			MWMInOutBoundLine inOutBoundLine,
			int warehouseAreaTypeId,
			int warehouseSectionTypeId) {
		List<MLocator> locators = new ArrayList<>();
		List<MWMStrategy> strategies = applyDefinition(inOutBoundLine, warehouseAreaTypeId,
				warehouseSectionTypeId);

		strategies.forEach(strategy ->
				locators.addAll(getLocator(strategy, inOutBoundLine.getProduct().getM_Product_ID(),
						warehouseSectionTypeId, warehouseSectionTypeId)));
		return locators;
	}

	/**
	 * Get Storage based InOut Bound Line based on Area Type Id and Section Type Id
	 * @param inOutBoundLine InOutBound Line
	 * @param warehouseAreaTypeId Warehouse Area Type Id
	 * @param warehouseSectionTypeId Warehouse Section Type Id
	 * */
	public List<MStorage> getStorage(MWMInOutBoundLine inOutBoundLine,
									 int warehouseAreaTypeId,
									 int warehouseSectionTypeId) {
		List<MStorage> storageList = new ArrayList<>();
		List<MWMStrategy> strategies = applyDefinition(inOutBoundLine, warehouseAreaTypeId,
				warehouseSectionTypeId);

		/*if (strategy.getM_Warehouse_ID() != inOutBoundLine.getM_Warehouse_ID()) {
			return null;
		}*/

		strategies.forEach(strategy -> {
			storageList.addAll(
					getStorage(strategy, inOutBoundLine.getProduct().getM_Product_ID(),
							inOutBoundLine.getM_AttributeSetInstance_ID(), inOutBoundLine.getMovementQty(),
							warehouseSectionTypeId, warehouseSectionTypeId)
			);
		});
		return storageList;
	}

	/**
	 * Apply Definition for Warehouse  Strategy
	 * @param inOutBoundLine Order Bound Line
	 * @param warehouseAreaTypeId Area Type Id
	 * @param warehouseSectionTypeId Section Type Id
	 * */
	private List<MWMStrategy> applyDefinition(
			MWMInOutBoundLine inOutBoundLine,
			int warehouseAreaTypeId,
			int warehouseSectionTypeId) {
		List<MWMStrategy> strategies = new ArrayList<>();
		StringBuilder whereClause = new StringBuilder("(");
		whereClause.append(MWMDefinition.COLUMNNAME_M_Product_ID
				+ " IN (0,?) OR ");
		whereClause.append(MWMDefinition.COLUMNNAME_M_Product_ID + " IS NULL");
		whereClause.append(") AND ");
		whereClause.append("(");
		whereClause.append(MWMDefinition.COLUMNNAME_M_Product_Category_ID
				+ " IN (0,?) OR ");
		whereClause.append(MWMDefinition.COLUMNNAME_M_Product_Category_ID
				+ " IS NULL");
		whereClause.append(") AND ");
		whereClause.append("(");
		whereClause.append(MWMDefinition.COLUMNNAME_Group1 + " IN ('',?) OR ");
		whereClause.append(MWMDefinition.COLUMNNAME_Group1 + " IS NULL");
		whereClause.append(") AND ");
		whereClause.append("(");
		whereClause.append(MWMDefinition.COLUMNNAME_Group2 + " IN ('',?) OR ");
		whereClause.append(MWMDefinition.COLUMNNAME_Group2 + " IS NULL");
		whereClause.append(") AND ");
		whereClause.append("(");
		whereClause.append(MWMDefinition.COLUMNNAME_Classification
				+ " IN ('',?) OR ");
		whereClause
				.append(MWMDefinition.COLUMNNAME_Classification + " IS NULL");
		whereClause.append(") AND ");
		whereClause.append("(");
		whereClause.append(MWMDefinition.COLUMNNAME_C_BPartner_ID
				+ " IN (0,?) OR ");
		whereClause.append(MWMDefinition.COLUMNNAME_C_BPartner_ID + " IS NULL");
		whereClause.append(") AND ");
		whereClause.append("(");
		whereClause.append(MWMDefinition.COLUMNNAME_C_BP_Group_ID
				+ " IN (0,?) OR ");
		whereClause.append(MWMDefinition.COLUMNNAME_C_BP_Group_ID + " IS NULL");
		whereClause.append(") AND ");
		whereClause.append("(");
		whereClause.append(MWMDefinition.COLUMNNAME_WM_Area_Type_ID
				+ " IN (0,?) OR ");
		whereClause.append(MWMDefinition.COLUMNNAME_WM_Area_Type_ID
				+ " IS NULL");
		whereClause.append(") AND ");
		whereClause.append("(");
		whereClause.append(MWMDefinition.COLUMNNAME_WM_Section_Type_ID
				+ " IN (0,?) OR ");
		whereClause.append(MWMDefinition.COLUMNNAME_WM_Section_Type_ID
				+ " IS NULL");
		whereClause.append(")");
		whereClause.append(" AND (");
		whereClause.append(MWMDefinition.COLUMNNAME_M_Locator_ID).append(" IN (0,?) OR ")
				   .append(MWMDefinition.COLUMNNAME_M_Locator_ID).append(" IS NULL) ");
		whereClause.append(" AND EXISTS (SELECT 1 FROM "
				+ MWMStrategy.Table_Name);
		whereClause.append(" WHERE ");
		whereClause.append(MWMStrategy.Table_Name + "."
				+ MWMStrategy.COLUMNNAME_WM_Strategy_ID + "=");
		whereClause.append(MWMDefinition.Table_Name + "."
				+ MWMDefinition.COLUMNNAME_WM_Strategy_ID);
		whereClause.append(" AND ");
		whereClause.append(MWMStrategy.COLUMNNAME_InOutBoundType + "=?");
		whereClause.append(")");

		MProduct product = inOutBoundLine.getProduct();
		MBPartner partner = inOutBoundLine.getBPartner();

		List<MWMDefinition> definitionList = new Query(inOutBoundLine.getCtx(),
				MWMDefinition.Table_Name, whereClause.toString(),
				inOutBoundLine.get_TrxName())
				.setClient_ID()
				.setParameters( product.getM_Product_ID(),
								product.getM_Product_Category_ID(),
								product.getGroup1(), product.getGroup2(),
								product.getClassification(),
								partner.getC_BPartner_ID(),
								partner.getC_BP_Group_ID(), warehouseAreaTypeId,
								warehouseSectionTypeId,
								inOutBoundLine.getM_LocatorTo_ID(),
								MWMStrategy.INOUTBOUNDTYPE_OutboundOperation)
				.setOrderBy(MWMDefinition.COLUMNNAME_SeqNo + " , " + MWMDefinition.COLUMNNAME_PriorityRule)
				.list();

		definitionList.forEach(definition -> strategies.add(definition.getWarehouseStrategy()));
		if (strategies.size() == 0 )
			throw new AdempiereException("@WM_Definition_ID@ @NotFound@");

		return strategies;
	}

	/**
	 * Get Locator based on strategy and product id , area type id , section id
	 * @param strategy strategy
	 * @param productId Product
	 * @param warehouseAreaTypeId Area Type
	 * @param warehouseSectionTypeId Section Type
	 * @return List of Locators
	 */
	public static List<MLocator> getLocator(MWMStrategy strategy,
											int productId,
											int warehouseAreaTypeId,
											int warehouseSectionTypeId) {
		ArrayList<MLocator> targetLocators = new ArrayList<>();
		WMRuleEngine engine = WMRuleEngine.get();
		for (MWMStrategyDetail detail : strategy.getStrategyDetail()) {
			MWMRule rule = new MWMRule(detail.getCtx(), detail.getWM_Rule_ID(), detail.get_TrxName());
			Optional<WMRuleInterface> maybeImplementation = Optional.ofNullable(engine.getWMRuleFactory(engine
					.getClassName(rule)));
			List<MLocator> locators = maybeImplementation.map(implementation -> {
				return implementation.getLocator(
						strategy.getCtx(),
						productId,
						strategy.getM_Warehouse_ID(),
						warehouseAreaTypeId,
						warehouseSectionTypeId,
						strategy.get_TrxName()
				);
			}).orElseGet(ArrayList::new);
			targetLocators.addAll(locators);
		}
		return targetLocators;
	}

	/**
	 * Get Storage list based on strategy , product id , attribute set instance id , area type , section type
	 * @param strategy Strategy
	 * @param productId Product Id
	 * @param attributeSetInstanceId Attribute Set Instance Id
	 * @param qtyToDeliver Qty To Deliver
	 * @param warehouseAreaTypeId Warehouse Area Type Id
	 * @param warehouseSectionTypeId Warehouse Section Type Id
	 * @return List of Storage
	 */
	public static List<MStorage> getStorage(MWMStrategy strategy,
											int productId, int attributeSetInstanceId,
											BigDecimal qtyToDeliver, int warehouseAreaTypeId, int warehouseSectionTypeId) {
		ArrayList<MStorage> targetStorageList = new ArrayList<>();
		WMRuleEngine engine = WMRuleEngine.get();
		for (MWMStrategyDetail detail : strategy.getStrategyDetail()) {
			MWMRule rule = new MWMRule(detail.getCtx(), detail.getWM_Rule_ID(), detail.get_TrxName());
			Optional<WMRuleInterface> maybeImplementation = Optional.ofNullable(engine.getWMRuleFactory(engine
					.getClassName(rule)));

			List<MStorage> storageList = maybeImplementation.map(implementation -> {
				return implementation.getStorage(strategy.getCtx(),
					strategy.getM_Warehouse_ID(), productId,
					attributeSetInstanceId, qtyToDeliver,
					warehouseAreaTypeId, warehouseSectionTypeId,
					strategy.get_TrxName());
			}).orElseGet(ArrayList::new);

			targetStorageList.addAll(storageList);
		}
		return targetStorageList;
	}

	public static void main(String[] args) {
		Adempiere.startup(true);
		Ini.setProperty(Ini.P_UID, "SuperUser");
		Ini.setProperty(Ini.P_PWD, "System");
		Ini.setProperty(Ini.P_ROLE, "GardenWorld Admin");
		Ini.setProperty(Ini.P_CLIENT, "GardenWorld");
		Ini.setProperty(Ini.P_ORG, "HQ");
		Ini.setProperty(Ini.P_WAREHOUSE, "HQ Warehouse");
		Ini.setProperty(Ini.P_LANGUAGE, "English");
		// Ini.setProperty(Ini.P_PRINTER,"MyPrinter");
		Login login = new Login(Env.getCtx());
		login.batchLogin();
		MWMInOutBoundLine line = new MWMInOutBoundLine(Env.getCtx(), 1000006,
				null);
		WMRuleEngine engine = WMRuleEngine.get();
		MWMInOutBound order = line.getParent();
		engine.getLocator(line, 0, 0);
	}
}
