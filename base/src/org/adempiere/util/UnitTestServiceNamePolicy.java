package org.adempiere.util;

/**
 * Implementation to load "plain" (i.e. DB decoupled) service implementations. Mainly used for unit and module testing.
 * 
 * 
 */
public class UnitTestServiceNamePolicy extends DefaultServiceNamePolicy implements IServiceNameAutoDetectPolicy
{

	@Override
	public String getServiceImplementationClassName(Class<? extends IService> clazz)
	{
		final String servicePackageName = clazz.getPackage().getName() + ".impl.";

		String serviceClassName;
		if (clazz.getSimpleName().startsWith("I"))
			serviceClassName = clazz.getSimpleName().substring(1);
		else
			serviceClassName = clazz.getSimpleName();

		final String plainServiceClassNameFQ = servicePackageName + "Plain" + serviceClassName;
		try
		{
			Thread.currentThread().getContextClassLoader().loadClass(plainServiceClassNameFQ);
			return plainServiceClassNameFQ;
		}
		catch (ClassNotFoundException e)
		{
			// logger.log(Level.FINE, "No Plain/Unit service found for "+clazz+". Tried "+plainServiceClassNameFQ);
			// Plain Service not found, use original one

			// TODO also try to load
			return super.getServiceImplementationClassName(clazz);
		}
	}

}
