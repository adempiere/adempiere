package org.adempiere.ad.modelvalidator.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation used to mark which methods shall be invoked when the validator is initialized.
 * 
 * Initializer method can have following formats:
 * <ul>
 * <li>void myInitMethod()
 * <li>void myInitMethod({@link org.compiere.model.ModelValidationEngine} engine)
 * </ul>
 * 
 * @author tsa
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface Init
{
}
