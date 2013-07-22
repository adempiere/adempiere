package org.adempiere.ad.modelvalidator.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used for specifying more then one {@link DocValidate} annotations for one method
 * 
 * @author tsa
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface DocValidates
{
	DocValidate[] value();
}
