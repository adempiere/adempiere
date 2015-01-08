package org.adempiere.engine;

import java.util.HashMap;

/**
 * 
 * @author teo.sarca@gmail.com
 */
public class CostEngineFactory
{
	private static final HashMap<Integer, CostEngine> s_engines = new HashMap<Integer, CostEngine>();
	
	public static CostEngine getCostEngine(int AD_Client_ID)
	{
		CostEngine engine = s_engines.get(AD_Client_ID);
		// Fallback to global engine
		if (engine == null && AD_Client_ID > 0)
		{
			engine = s_engines.get(0);
		}
		// Create Default Engine
		if (engine == null)
		{
			engine = new CostEngine();
			s_engines.put(AD_Client_ID, engine);
		}
		return engine;
	}
	
	public static void registerCostEngine(int AD_Client_ID, CostEngine engine)
	{
		s_engines.put(AD_Client_ID, engine);
	}
}
