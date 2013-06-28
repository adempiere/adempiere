package org.adempiere.ad.modelvalidator.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that the annotated method shall be triggered on a particular model change validator event
 * 
 * @author tsa
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface ModelChange
{

	/**
	 * On which model change events shall we call the annotated methods. For more information about events, please check
	 * {@link org.compiere.model.ModelValidator}.TYPE_* constants.
	 * 
	 * At least one event shall be specified.
	 */
	int[] timings() default {};

	/**
	 * Indicate that the method shall be called only if one of the given fields were changed.
	 * 
	 * This is optional.
	 */
	String[] ifColumnsChanged() default {};

}
