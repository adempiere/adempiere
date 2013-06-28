package org.adempiere.ad.modelvalidator;

import java.lang.reflect.Method;

import org.compiere.model.ModelValidationEngine;

/**
 * Model Validator initializer definition
 * 
 * @author tsa
 * 
 */
class ValidatorInit
{
	private final Method method;
	private boolean methodRequiresEngine = false;

	public ValidatorInit(Method method)
	{
		super();
		this.method = method;
	}

	public Method getMethod()
	{
		return method;
	}

	/**
	 * 
	 * @return true if method call requires {@link ModelValidationEngine} as first parameter
	 */
	public boolean isMethodRequiresEngine()
	{
		return methodRequiresEngine;
	}

	public void setMethodRequiresEngine(boolean methodRequiresEngine)
	{
		this.methodRequiresEngine = methodRequiresEngine;
	}

	@Override
	public String toString()
	{
		return "ValidatorInit [method=" + method + ", methodRequiresEngine=" + methodRequiresEngine + "]";
	}
}
