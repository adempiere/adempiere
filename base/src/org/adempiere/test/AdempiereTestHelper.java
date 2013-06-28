package org.adempiere.test;

import org.adempiere.ad.wrapper.POJOLookupMap;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.util.Check;
import org.adempiere.util.Services;
import org.adempiere.util.UnitTestServiceNamePolicy;
import org.compiere.Adempiere;

/**
 * Helper to be used in order to setup ANY test which depends on ADempiere.
 * 
 * @author tsa
 * 
 */
public class AdempiereTestHelper
{
	private static final AdempiereTestHelper instance = new AdempiereTestHelper();

	public static AdempiereTestHelper get()
	{
		return instance;
	}

	private boolean staticInitialized = false;

	public void staticInit()
	{
		if (staticInitialized)
		{
			return;
		}

		Adempiere.enableUnitTestMode();

		Check.setDefaultExClass(AdempiereException.class);

		//
		// Configure services
		Services.setAutodetectServices(true);
		Services.setServiceNameAutoDetectPolicy(new UnitTestServiceNamePolicy()); // mo73_04113

		staticInitialized = true;
	}

	public void init()
	{
		// final Properties ctx = Env.getCtx();

		// Make sure database is clean
		POJOLookupMap.get().clear();

		// Make sure we don't have custom registered services
		// Each test shall init it's services if it wants
		Services.clear();

		//
		// Base Language
		//Language.setBaseLanguage(Language.getLanguage("de_DE"));
	}
}
