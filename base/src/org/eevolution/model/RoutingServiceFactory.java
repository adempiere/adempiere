package org.eevolution.model;

import java.util.HashMap;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.Env;

/**
 * Routing Service Factory
 * @author Teo Sarca
 */
public class RoutingServiceFactory
{
	public static final String DEFAULT_ServiceName = "org.eevolution.manufacturing.model.impl.DefaultRoutingServiceImpl";

	public static RoutingServiceFactory s_instance = null;
	private static final HashMap<Integer, String> s_serviceClassnames = new HashMap<Integer, String>(5);
	private static final HashMap<Integer, RoutingService> s_services = new HashMap<Integer, RoutingService>(5);
	
	public static RoutingServiceFactory get()
	{
		if (s_instance == null)
		{
			s_instance = new RoutingServiceFactory();
		}
		return s_instance;
	}
	
	public static void registerServiceClassname(int AD_Client_ID, String serviceClassname)
	{
		s_serviceClassnames.put(AD_Client_ID > 0 ? AD_Client_ID : 0, serviceClassname);
	}
	
	private RoutingServiceFactory()
	{
	}
	
	private final String getRoutingServiceClassname(int AD_Client_ID)
	{
		String classname = s_serviceClassnames.get(AD_Client_ID);
		if (classname == null && AD_Client_ID != 0)
			classname = s_serviceClassnames.get(0);
		if (classname == null)
			classname = DEFAULT_ServiceName;
		return classname;
	}
	
	
	@SuppressWarnings("unchecked")
	public RoutingService getRoutingService(int AD_Client_ID)
	{
		RoutingService service = s_services.get(AD_Client_ID);
		if (service != null)
		{
			return service;
		}
		String classname = getRoutingServiceClassname(AD_Client_ID);
		
		try
		{
			Class<? extends RoutingService> cl = (Class<? extends RoutingService>) getClass().getClassLoader().loadClass(classname);
			service = cl.newInstance();
			s_services.put(AD_Client_ID, service);
		}
		catch (Exception e)
		{
			throw new AdempiereException(e);
		}
		return service;
	}
	
	public RoutingService getRoutingService(Properties ctx)
	{
		return getRoutingService(Env.getAD_Client_ID(ctx));
	}
	
	public RoutingService getRoutingService()
	{
		return getRoutingService(Env.getCtx());
	}
}
