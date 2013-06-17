package org.adempiere.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.adempiere.util.exceptions.ServicesException;

/**
 * ADempiere service registry.
 * 
 * @author ts
 * 
 */
public class Services
{
	private final static Logger logger = Logger.getLogger(Services.class.getName());

	private static Map<Class<? extends IService>, Object> services = new HashMap<Class<? extends IService>, Object>();

	private static boolean isAutodetectServices = true;

	// using the unit test policy by default. that way not all unit tests have to remember this step
	// note that the Adempiere.java sets this to DefaultServiceNamePolicy
	private static IServiceNameAutoDetectPolicy serviceNameAutoDetectPolicy = new UnitTestServiceNamePolicy();
	
	
	public static void setAutodetectServices(boolean enable)
	{
		isAutodetectServices = enable;
	}

	public static boolean isAutodetectServices()
	{
		return isAutodetectServices;
	}

	/**
	 * 
	 * @param clazz
	 * @return if <code>T</code> extends {@link ISingletonService} then this method returns the instance that was
	 *         registered using {@link #registerService(Class, IService)}. If <code>T</code> extends
	 *         {@link IMultitonService}, then this method invokes the default constructor of the registered instance's
	 *         class and returns a fresh instance.
	 */
	@SuppressWarnings("unchecked")
	public static <T extends IService> T get(final Class<T> clazz)
	{
		Check.assumeNotNull(clazz, "Param 'clazz' not null");
		
		final T service = (T)getSingleton(clazz);

		if (service == null)
		{
			logger.severe("No service is registered for " + clazz);
			
			return null;
		}

		if (service instanceof IMultitonService)
		{
			return (T)newMultitonInstance((IMultitonService)service);
		}
		return service;
	}

	@SuppressWarnings("unchecked")
	private static <T extends IService> T getSingleton(final Class<T> clazz)
	{
		T service = (T)services.get(clazz);
		if (service != null)
			return service;

		//
		// Auto-detect service class:
		if (isAutodetectServices)
		{
			final String serviceClassname = serviceNameAutoDetectPolicy.getServiceImplementationClassName(clazz);
			try
			{
				final Class<?> serviceClass = Thread.currentThread().getContextClassLoader().loadClass(serviceClassname);
				service = newClassInstance(serviceClass);

				Constructor<?> serviceClassCtor = null;
				for (final Constructor<?> ctor : serviceClass.getDeclaredConstructors())
				{
					if(ctor.getParameterTypes().length == 0)
					{
						serviceClassCtor = ctor;
						break;
					}
				}
				
				if (serviceClassCtor != null)
				{
					if (!serviceClassCtor.isAccessible())
					{
						serviceClassCtor.setAccessible(true);
					}
					service = (T)serviceClassCtor.newInstance();
				}
				else
				{
					service = (T)serviceClass.newInstance();
				}
				
				services.put(clazz, service);
				logger.info("Autodetected service for " + clazz + ": " + service.getClass());
			}
			catch (Exception e)
			{
				logger.log(Level.WARNING, "Cannot load autodetected class " + serviceClassname, e);
			}
		}

		if (service == null)
		{
			logger.log(Level.SEVERE, "No service is registered for {0}", clazz);
		}
		return service;
	}

	/**
	 * Creates a new instance of given class.
	 * 
	 * If the given class is not publicly available, this method will make the constructor accessible first and then will try to instantiate the object.
	 * 
	 * @param serviceClass
	 * @return
	 * @throws IllegalArgumentException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@SuppressWarnings("unchecked")
	private static final <T> T newClassInstance(Class<?> serviceClass) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException
	{
		// Search for the constructor without parameters (actually in most of the cases it shall be the first and only)
		Constructor<?> serviceClassCtor = null;
		for (final Constructor<?> ctor : serviceClass.getDeclaredConstructors())
		{
			if (ctor.getParameterTypes().length == 0)
			{
				serviceClassCtor = ctor;
				break;
			}
		}

		final T service;
		if (serviceClassCtor != null)
		{
			// make sure the constructor is accessible
			if (!serviceClassCtor.isAccessible())
			{
				serviceClassCtor.setAccessible(true);
			}
			service = (T)serviceClassCtor.newInstance();
		}
		else
		{
			// No constructor available?!
			// Fallback and ask the class to instantiate
			service = (T)serviceClass.newInstance();
		}

		return service;
	}
	
	private static <T extends IMultitonService> T newMultitonInstance(final T inst)
	{
		try
		{
			final Class<?> serviceClass = inst.getClass();
			final T service = newClassInstance(serviceClass);
			return service;
		}
		catch (final InstantiationException e)
		{
			throw new ServicesException(e);
		}
		catch (IllegalAccessException e)
		{
			throw new ServicesException(e);
		}
		catch (IllegalArgumentException e)
		{
			throw new ServicesException(e);
		}
		catch (InvocationTargetException e)
		{
			throw new ServicesException(e.getTargetException());
		}
	}

	public static boolean isRegistered(final Class<?> clazz)
	{
		return services.get(clazz) != null;
	}

	/**
	 * Register a new service class and an implementing instance.
	 * 
	 * @param clazz
	 *            the API class that will later on be used to get the implementation. Must extend
	 *            {@link IMultitonService} or {@link IStatelessService}.
	 * @param implementation
	 *            an actual instance of a class extending 'clazz'.
	 */
	public static <T extends IService> void registerService(final Class<T> clazz, final T implementation)
	{
		Check.assumeNotNull(clazz, "Param 'clazz' not null");
		Check.assumeNotNull(implementation, "Param 'implementation' not null");
		
		checkAndRegister(clazz, implementation);
	}

	private static <T extends IService> void checkAndRegister(final Class<T> clazz, final T implementation)
	{
		logger.info("Registering implementation (class " + implementation.getClass().getName() + ") for API-class " + clazz);

		checkParams(clazz, implementation);
		services.put(clazz, implementation);
	}

	private static <T extends IService> void checkParams(final Class<T> apiClazz, final T impl)
	{
		if (!apiClazz.isInterface())
		{
			throw new IllegalArgumentException("Parameter 'clazz' must be an interface class. clazz is" + apiClazz.getName());
		}

		final Class<?> implClazz = impl.getClass();
		if (!apiClazz.isAssignableFrom(implClazz))
		{
			throw new IllegalArgumentException("Service " + implClazz + " must implement interface " + apiClazz);
		}

		if (!(impl instanceof IMultitonService) && !(impl instanceof ISingletonService))
		{
			throw new IllegalArgumentException("Parameter 'clazz' must extend " + IMultitonService.class + " or " + ISingletonService.class);
		}
	}

	/**
	 * Clears the service registry. Intended use between unit tests.
	 */
	public static void clear()
	{
	//	Util.assume(Adempiere.isUnitTestMode(), "Clearing the service registry is allowed when running in JUnit test mode");
		services.clear();
	}
	
	public static void setServiceNameAutoDetectPolicy(IServiceNameAutoDetectPolicy serviceNameAutoDetectPolicy)
	{
		Check.assumeNotNull(serviceNameAutoDetectPolicy, "Param 'serviceNameAutoDetectPolicy' not null");
		
		Services.serviceNameAutoDetectPolicy = serviceNameAutoDetectPolicy;
	}
}
