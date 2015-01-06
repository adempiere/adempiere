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

package org.eevolution.engine.warehouse;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
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
import org.eevolution.engine.warehouse.WMRuleEngine;
import org.eevolution.engine.warehouse.WMRuleFIFO;
import org.eevolution.engine.warehouse.WMRuleInterface;
import org.eevolution.model.MWMDefinition;
import org.eevolution.model.MWMInOutBound;
import org.eevolution.model.MWMInOutBoundLine;
import org.eevolution.model.MWMRule;
import org.eevolution.model.MWMStrategy;
import org.eevolution.model.MWMStrategyDetail;

/**
 * Warehouse Engine
 * 
 * @author victor.perez@e-evolution.com, www.e-evolution.com
 * 
 */
public final class WMRuleEngine {

	/** Logger */
	protected CLogger log = CLogger.getCLogger(getClass());
	private static WMRuleEngine s_instance = null;
	/** WMRule (negative cache) */
	private static TreeSet<String> s_RuleNoImplement = new TreeSet<String>();
	/** Cache Rules */
	private HashMap<String, WMRuleInterface> m_WMRules = new HashMap<String, WMRuleInterface>();

	/** Package for Warehouse Rule classes */
	private String m_packageName = "org.eevolution.engines.Warehouse";

	/**
	 * get WM Rules
	 * 
	 * @param ctx
	 * @param AD_Client_ID
	 * @param trxName
	 * @return Collection of the MWMRules
	 */
	public static Collection<MWMRule> getWMRules(Properties ctx,
			int AD_Client_ID, String trxName) {
		String whereClause = "";
		ArrayList<Object> parameters = new ArrayList();
		if (AD_Client_ID > 0) {
			whereClause = MWMRule.COLUMNNAME_AD_Client_ID + "=?";
			parameters.add(AD_Client_ID);
		} else {
			whereClause = null;
		}

		return new Query(ctx, MWMRule.Table_Name, whereClause, trxName)
				.setOnlyActiveRecords(true)
				.setParameters(new Object[] { parameters }).<MWMRule> list();
	}

	/**
	 * Apply the WM Rule by Bound Type
	 * 
	 * @param lines
	 * @param boundType
	 */
	public static void applyWMRule(Collection<MWMInOutBound> lines,
			String boundType) {
		for (MWMInOutBound line : lines) {
			for (MWMDefinition definition : MWMDefinition.getAll(line.getCtx(),
					line.get_TrxName())) {
				WMRuleEngine.validateDefinition(line, definition, boundType);
			}
		}

	}

	/**
	 * validateDefinition
	 * 
	 * @param line
	 * @param definition
	 * @param boundType
	 */
	private static void validateDefinition(MWMInOutBound line,
			MWMDefinition definition, String boundType) {
		for (MWMStrategy strategy : MWMStrategy.getByBoundType(line.getCtx(),
				boundType, line.get_TrxName())) {
			for (MWMStrategyDetail strategyLine : strategy.getDetail()) {
				MWMRule rule = (MWMRule) strategyLine.getWM_Rule();
			}
		}
	}

	private WMRuleEngine() {
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
			m_WMRules.clear();
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
		if (m_WMRules.containsKey(className)) {
			return (WMRuleInterface) m_WMRules.get(className);
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
				m_WMRules.put(className, rule);
				return rule;
			}
			//
			// Check <constructor>():
			constructor = cl.getDeclaredConstructor();
			WMRuleInterface rule = (WMRuleInterface) constructor.newInstance();
			m_WMRules.put(className, rule);
			return rule;
		} catch (ClassNotFoundException e) {
			s_RuleNoImplement.add(className); // Add to negative cache (won't
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
		return this.m_packageName;
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
	 * get Locators by WM Area Type and WM Section Type
	 * 
	 * @param MWMInOutBoundLine
	 *            line
	 * @param WM_Area_Type_ID
	 *            WM Area Type
	 * @param WM_Section_Type_ID
	 *            Section Type
	 * @return Collection with Locator to Document Bound
	 */
	public Collection<MLocator> getMLocator(MWMInOutBoundLine line,
			int WM_Area_Type_ID, int WM_Section_Type_ID) {
		MWMStrategy strategy = applyDefinition(line, WM_Area_Type_ID,
				WM_Section_Type_ID);
		return getMLocators(strategy, line.getMProduct().getM_Product_ID(),
				WM_Section_Type_ID, WM_Section_Type_ID);
	}

	/**
	 * get Storages
	 * 
	 * @param MWMInOutBoundLine
	 *            line
	 * @param WM_Area_Type_ID
	 *            Area Type
	 * @param WM_Section_Type_ID
	 *            Section Type
	 * @return get Collection Storages
	 */
	public Collection<MStorage> getMStorage(MWMInOutBoundLine line,
			int WM_Area_Type_ID, int WM_Section_Type_ID) {
		MWMStrategy strategy = applyDefinition(line, WM_Area_Type_ID,
				WM_Section_Type_ID);
		if (strategy.getM_Warehouse_ID() != line.getM_Warehouse_ID()) {
			return null;
		}
		return getMStorages(strategy, line.getMProduct().getM_Product_ID(),
				line.getM_AttributeSetInstance_ID(), line.getMovementQty(),
				WM_Section_Type_ID, WM_Section_Type_ID);
	}

	/**
	 * apply Definition to get WM Strategy
	 * 
	 * @param MWMInOutBoundLine
	 *            line
	 * @param WM_Area_Type_ID
	 *            Area Type
	 * @param WM_Section_Type_ID
	 *            Section Type
	 * @return WMStrategy
	 */
	private MWMStrategy applyDefinition(MWMInOutBoundLine line,
			int WM_Area_Type_ID, int WM_Section_Type_ID) {
		StringBuffer whereClause = new StringBuffer("(");
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

		MProduct product = line.getMProduct();
		MBPartner bpartner = line.getMBPartner();

		MWMDefinition definition = new Query(line.getCtx(),
				MWMDefinition.Table_Name, whereClause.toString(),
				line.get_TrxName())
				.setClient_ID()
				.setParameters(
						new Object[] { product.getM_Product_ID(),
								product.getM_Product_Category_ID(),
								product.getGroup1(), product.getGroup2(),
								product.getClassification(),
								bpartner.getC_BPartner_ID(),
								bpartner.getC_BP_Group_ID(), WM_Area_Type_ID,
								WM_Section_Type_ID,
								MWMStrategy.INOUTBOUNDTYPE_OutboundOperation })
				.first();

		if (definition == null) {
			throw new AdempiereException("Can not found valid Definition");
		}

		return definition.getWMStrategy();
	}

	/**
	 * get MLocator
	 * 
	 * @param MWMStrategy
	 *            strategy
	 * @param M_Product_ID
	 *            Product
	 * @param WM_Area_Type_ID
	 *            Area Type
	 * @param WM_Section_Type_ID
	 *            Section Type
	 * @return Collection of Locators
	 */
	public static Collection<MLocator> getMLocators(MWMStrategy strategy,
			int M_Product_ID, int WM_Area_Type_ID, int WM_Section_Type_ID) {
		ArrayList<MLocator> targetLocators = new ArrayList();
		WMRuleEngine engine = WMRuleEngine.get();
		for (MWMStrategyDetail detail : strategy.getDetail()) {
			MWMRule rule = (MWMRule) detail.getWM_Rule();
			WMRuleInterface implementation = engine.getWMRuleFactory(engine
					.getClassName(rule));
			Collection<MLocator> locators = implementation.getLocator(
					strategy.getCtx(), M_Product_ID,
					strategy.getM_Warehouse_ID(), WM_Area_Type_ID,
					WM_Section_Type_ID, strategy.get_TrxName());

			for (MLocator locator : locators) {
				targetLocators.add(locator);
			}
		}
		return targetLocators;
	}

	/**
	 * get Collection of Storages
	 * 
	 * @param strategy
	 * @param M_Product_ID
	 * @param qtyToDeliver
	 * @param WM_Area_Type_ID
	 * @param WM_Section_Type_ID
	 * @return Collection of Storage
	 */
	public static Collection<MStorage> getMStorages(MWMStrategy strategy,
			int M_Product_ID, int M_AttributeSetInstance_ID,
			BigDecimal qtyToDeliver, int WM_Area_Type_ID, int WM_Section_Type_ID) {
		ArrayList<MStorage> targetStorages = new ArrayList();
		WMRuleEngine engine = WMRuleEngine.get();
		for (MWMStrategyDetail detail : strategy.getDetail()) {
			MWMRule rule = (MWMRule) detail.getWM_Rule();
			WMRuleInterface implementation = engine.getWMRuleFactory(engine
					.getClassName(rule));

			Collection<MStorage> storages = implementation
					.getStorage(strategy.getCtx(),
							strategy.getM_Warehouse_ID(), M_Product_ID,
							M_AttributeSetInstance_ID, qtyToDeliver,
							WM_Area_Type_ID, WM_Section_Type_ID,
							strategy.get_TrxName());

			for (MStorage storage : storages) {
				targetStorages.add(storage);
			}
		}
		return targetStorages;
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
		engine.getMLocator(line, 0, 0);
	}

}
