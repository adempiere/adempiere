package org.adempiere.util;

/**
 * If {@link Services#isAutodetectServices()} is <code>true</code> and no service implementation has been registered for a given {@link IService} class, then an implementor of this class constructs
 * the classname of the service implementation class.
 * 
 * @see Services#setServiceNameAutoDetectPolicy(IServiceNameAutoDetectPolicy)
 * 
 */
public interface IServiceNameAutoDetectPolicy
{
	String getServiceImplementationClassName(Class<? extends IService> clazz);
}
