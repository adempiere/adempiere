package de.metas.adempiere.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Use this annotation on a method parameter if the method has been annotated with {@link Cached}, and the parameter is
 * the actual context. (i.e. ctx).
 * 
 * <p>
 * Note: Unlike {@link Cached}, the implication of this annotation is not configured in a <code>jboss-aop.xml</code>
 * file but hard-coded in the cache implementation ({@link CacheAsp}).
 * 
 * @author ad
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@Inherited
public @interface CacheCtx
{

}