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

import java.util.HashMap;

/**
 * Factory of Forecast 
 * @author victor.perez@e-evolution.com, www.e-Evolution.com
 */
public class ForecastFactory {

	/**
	 * get Forecast Rule Factory
	 * 
	 * @param name
	 * @return Forecast Rule Interface Rule Implementation
	 */
	public static ForecastRule createForecastRule(String name) {
		ForecastRule rule = null;
		if ("DoubleExponentialSmoothing".equals(name)) {
			rule = new DoubleExponentialSmoothing();
		}
		return rule;
	}

	public ForecastFactory() {
	}

	private static final HashMap<Integer, ForecastEngine> s_engines = new HashMap<Integer, ForecastEngine>();

	public static ForecastEngine getForecastEngine(int AD_Client_ID) {
		ForecastEngine engine = s_engines.get(AD_Client_ID);
		// Fallback to global engine
		if (engine == null && AD_Client_ID > 0) {
			engine = s_engines.get(0);
		}
		// Create Default Engine
		if (engine == null) {
			engine = new ForecastEngine();
			s_engines.put(AD_Client_ID, engine);
		}
		return engine;
	}

	public static void registerForecastEngine(int AD_Client_ID,
			ForecastEngine engine) {
		s_engines.put(AD_Client_ID, engine);
	}
}
