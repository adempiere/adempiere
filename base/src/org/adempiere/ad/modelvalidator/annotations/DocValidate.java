package org.adempiere.ad.modelvalidator.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that the annotated method shall be triggered on a particular document action model validator event
 * 
 * @author tsa
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface DocValidate
{
	/**
	 * On which timings shall we call the annotated methods. For more information about timings, please check
	 * {@link org.compiere.model.ModelValidator}.TIMING_* constants.
	 * 
	 * At least one event shall be specified.
	 */
	int[] timings() default {};
}
