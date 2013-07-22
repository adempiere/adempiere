package org.adempiere.ad.modelvalidator.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation used to mark ModelValidator classes
 * 
 * @author tsa
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface Validator
{
	/**
	 * Interface class on which this model validator will be bound
	 * 
	 * @return
	 */
	public Class<?> value();
}
