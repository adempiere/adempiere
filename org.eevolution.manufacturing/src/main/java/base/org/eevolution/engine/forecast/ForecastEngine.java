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
 * Copyright (C) 2003-2012 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): victor.perez@e-evolution.com www.e-evolution.com   		  *
 *****************************************************************************/

package org.eevolution.engine.forecast;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.TreeSet;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.Adempiere;
import org.compiere.model.Query;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.Login;
import org.eevolution.model.MPPForecastRule;

/**
 * Forecast Engine
 * 
 * @author victor.perez@e-evolution.com, www.e-evolution.com
 * 
 */
public final class ForecastEngine {

	/**
	 * get WMRule Engine like singleton instance
	 * 
	 * @return WM Rule Engine
	 */
	public static ForecastEngine get() {
		if (s_instance == null) {
			s_instance = new ForecastEngine();
		}
		return s_instance;
	}

	/**
	 * get WM Rules
	 * 
	 * @param ctx
	 * @param AD_Client_ID
	 * @param trxName
	 * @return Collection of the MForecastRules
	 */
	public static List<MPPForecastRule> getForecastRules(Properties ctx,
			int AD_Client_ID, String trxName) {
		String whereClause = "";
		ArrayList<Object> parameters = new ArrayList<Object>();
		if (AD_Client_ID > 0) {
			whereClause = MPPForecastRule.COLUMNNAME_AD_Client_ID + "=?";
			parameters.add(AD_Client_ID);
		} else {
			whereClause = null;
		}

		return new Query(ctx, MPPForecastRule.Table_Name, whereClause, trxName)
				.setOnlyActiveRecords(true).setParameters(parameters)
				.<MPPForecastRule> list();
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
		// MWMInOutBoundLine line = new MWMInOutBoundLine(Env.getCtx(), 1000006,
		// null);
		ForecastEngine engine = ForecastEngine.get();
		// MWMInOutBound order = line.getParent();
		// engine.getMLocator(line, 0 , 0 );
	}

	/** Logger */
	protected CLogger log = CLogger.getCLogger(getClass());

	private static ForecastEngine s_instance = null;

	/** MForecastRule (negative cache) */
	private static TreeSet<String> s_RuleNoImplement = new TreeSet<String>();

	/** Cache Rules */
	private HashMap<String, ForecastRule> m_ForecastRules = new HashMap<String, ForecastRule>();

	/** Package for Forecast Rule classes */
	private String m_packageName = "org.eevolution.engine.forecast";

	public ForecastEngine() {
		/*
		 * CacheMgt.get().register(new CacheInterface() { public int reset() {
		 * registerRules(true); return 1; } public int size() { return
		 * m_WMRules.size(); } });
		 */
		registerRules(true);
	}

	@SuppressWarnings("unchecked")
	protected <T extends ForecastRule> Class<T> getClass(String className)
			throws ClassNotFoundException {
		return (Class<T>) Class.forName(getPackageName() + "." + className);
	}

	/**
	 * getClassName for Forecast Rule
	 * 
	 * @param rule
	 * @return String ClassName implementation
	 */
	protected String getClassName(MPPForecastRule rule) {
		return "";
	}

	/**
	 * WMRule factory instance
	 * 
	 * @return document factory instance
	 */
	public ForecastRule getForecastRuleFactory(String className) {
		if (m_ForecastRules.containsKey(className)) {
			return (ForecastRule) m_ForecastRules.get(className);
		}
		// Check negative cache:
		/*
		 * if (s_RuleNoImplement.contains(rule.getInOutboundClass())) {
		 * log.info("Ignore "+rule.getInOutboundClass()+
		 * " doesn't have accounting implementation (WMRule* class)"); }
		 */
		//
		try {
			Class<? extends ForecastRule> cl = getClass(className);
			//
			// Check <constructor>():
			Constructor<?> constructor = null;
			try {
				constructor = cl.getDeclaredConstructor();
			} catch (Exception e) {
				log.fine("Not found <ForecastRule>()");
			}
			if (constructor != null) {
				ForecastRule rule = (ForecastRule) constructor.newInstance();
				m_ForecastRules.put(className, rule);
				return rule;
			}
			//
			// Check <constructor>():
			constructor = cl.getDeclaredConstructor();
			ForecastRule rule = (ForecastRule) constructor.newInstance();
			m_ForecastRules.put(className, rule);
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

	/**
	 * register the Forecast Rule implementation
	 * 
	 * @param reset
	 */
	protected void registerRules(boolean reset) {
		if (reset) {
			m_ForecastRules.clear();
		}

		ForecastRule rule = getForecastRuleFactory(DoubleExponentialSmoothing.class
				.getSimpleName());
	}

	public DataSet getForecast(String implementationClass,
			Integer M_Product_ID, DataSet series, double factorAlpha,
			double factorGamma, double factorBeta, double factorMultiplier, double factorScale, double factor) {
		ForecastRule implementation = getForecastRuleFactory(implementationClass);
		if (implementation == null)
			throw new AdempiereException("@PP_ForecastRule_ID@ "
					+ implementationClass + " @NotFound@ ");

		implementation.setDataSet(series, factorAlpha, factorGamma,factorBeta,
				factorMultiplier, factorScale, factor);
		implementation.setKey(M_Product_ID.toString());

		return applyfactorScale(
				applyfactorMultiplier(implementation.getForecast(),
						factorMultiplier), factorScale);
	}

	private DataSet applyfactorMultiplier(DataSet dataSet,
			double factorMultiplier) {
		Enumeration<DataElement> elements = dataSet.getDataElements();

		while (elements.hasMoreElements()) {
			DataElement element = elements.nextElement();
			BigDecimal qty = (BigDecimal) element.getValue();
			if (factorMultiplier != 0) {
				BigDecimal value = qty.multiply(
						new BigDecimal(factorMultiplier)).divide(
						new BigDecimal(100));
				String desc = element.getDescription() + " Multiplier :"
						+ value.toString();
				element.setValue(qty.add(value));
				element.setDescription(desc);
			}
		}

		return dataSet;
	}

	private DataSet applyfactorScale(DataSet dataSet, double factorScale) {
		Enumeration<DataElement> elements = dataSet.getDataElements();

		while (elements.hasMoreElements()) {
			DataElement element = elements.nextElement();
			if (factorScale != 0) {
				BigDecimal qty = (BigDecimal) element.getValue();
				BigDecimal value = qty.multiply(new BigDecimal(factorScale))
						.divide(new BigDecimal(100));
				String desc = element.getDescription() + " Scale :"
						+ value.toString();
				element.setValue(value);
				element.setDescription(desc);
			}
		}

		return dataSet;
	}
}
