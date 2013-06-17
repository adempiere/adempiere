package org.adempiere.util;


/**
 * Default implementation to be used at ADempiere runtime.
 *
 */
public class DefaultServiceNamePolicy implements IServiceNameAutoDetectPolicy
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
		
		final String serviceClassNameFQ = servicePackageName + serviceClassName;
		return serviceClassNameFQ;
		
	}

}
